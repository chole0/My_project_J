package ITEM;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Server extends JFrame {

    // 当前客户端是否正在运行
    private boolean KeepRunning = true;

    // 服务器是否是一对一发送
    private boolean ServerP2P = false;

    // 是否按下发送按钮
    private boolean IsPressedSendButton = false;


    // 保存聊天线程：
    private Map<String, ServerThread> ClientMap = new HashMap<>();

    //  给服务器设置
    //  保存所有用户的Button和姓名
    private ArrayList<JButton> ClientsButton = new ArrayList<>();
    private ArrayList<String> ClientsName = new ArrayList<>();
    private ServerThread clientp2p;
    private JPanel ClientList;
    // 获取系统发送消息的时间
    private String MessageInformation = null;

    public static void main(String[] args) {
        Server server = new Server("服务器端");
        server.setVisible(true);
        server.start();
    }

    private ServerSocket serverSocket;
    private int ClientNum = 0;

    private void start() {

        try {
            serverSocket = new ServerSocket(5555, 2);


            //不断循环，一旦有新客户端上线，就给他分配一个ServerThread线程
            while (true) {

                Socket socket = serverSocket.accept();
                ServerThread socketThread = new ServerThread(socket);
                ClientNum++;
                new Thread(socketThread).start();

                SendMsg sendMsg = new SendMsg(socket);
                new Thread(sendMsg).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    // 给服务器发送消息的线程，监控发送信息区，一旦按下发送按钮就out.print一条消息
    private class SendMsg implements Runnable {
        PrintWriter out;
        Socket socket;

        // 获取来自主程序的socket
        SendMsg(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                //  获得socket输出out，自动刷新
                out = new PrintWriter(socket.getOutputStream(), true);

                // 用户保持在线
                while (KeepRunning) {
                    // 休眠10s，否则会出问题，不知道为什么出问题
                    // 不休眠可能会使发送按钮按下去无反应
                    // 休眠程序可以用一句System.out语句代替，都是起到延时作用
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // 是否按下发送按钮
                    if (IsPressedSendButton) {
                        MessageInformation
                                = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":"
                                + Calendar.getInstance().get(Calendar.MINUTE) + ":"
                                + Calendar.getInstance().get(Calendar.SECOND);
                        //  判断是否是p2p模式
                        //  不是的话，群发消息
                        if (!ServerP2P) {


                            out.println("Server to all clients " + MessageInformation + "\n" + sendingMessage);


                            IsPressedSendButton = false;


                        }
                        //  是，发送给某一个客户
                        else {
                            // 判断是否是传输文件
                            if(sendingMessage.length()>8 && sendingMessage.substring(0,8).equals("sendFile")){

                                String path = sendingMessage.substring(sendingMessage.indexOf(":") + 1);

                                // 读取文件，发送出去
                                // 创建客户端本地输入流，从客户本地获取要上传的文件
                                FileInputStream fis=new FileInputStream(path);
                                //获取网络输出字节流，从而向服务器端发送数据
                                OutputStream os = socket.getOutputStream();

                                int len=0;
                                byte[] bytes=new byte[1024];
                                while ((len=fis.read(bytes))!=-1){
                                    //向服务器端发送文件

                                    os.write(bytes,0,len);

                                }

                            }
                            IsPressedSendButton = false;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /*
     *  ServerThread
     *	提供接收消息和传递消息功能
     *
     */
    private class ServerThread implements Runnable {
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;
        private String ClientName;

        // 一对一聊天标志,默认消息群发
        private boolean P2P = false;


        ServerThread(Socket socket) throws IOException {
            this.socket = socket;
            out = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            MessageInformation
                    = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":"
                    + Calendar.getInstance().get(Calendar.MINUTE) + ":"
                    + Calendar.getInstance().get(Calendar.SECOND);

            ClientName = "Client " + ClientNum;

            // 告诉新登陆用户他的用户名
            out.println(ClientName);

            ClientsName.add(ClientName);
            MessageRecords.append("Server " + MessageInformation + "\n" + ClientName + " 上线了!\n");

            // 分配到的用户加入map
            ClientMap.put(ClientName, this);

            JButton NewClient = new JButton(ClientName);
            NewClient.setBackground(SystemColor.BLACK);
            NewClient.setForeground(Color.YELLOW);
            NewClient.setFont(new Font("宋体", Font.BOLD, 16));
            ClientsButton.add(NewClient);

            NewClient.addActionListener(e -> {
                if (!ServerP2P) {
                    MessageInformation
                            = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":"
                            + Calendar.getInstance().get(Calendar.MINUTE) + ":"
                            + Calendar.getInstance().get(Calendar.SECOND);

                    ServerP2P = true;//点击按钮，切换聊天到P2P状态

                    clientp2p = ClientMap.get(NewClient.getText());//根据用户名获取用户专属的服务线程

                    MessageRecords.append("Server " + MessageInformation + " 发消息给 " + clientp2p.ClientName + "\n");

                } else {
                    MessageInformation
                            = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":"
                            + Calendar.getInstance().get(Calendar.MINUTE) + ":"
                            + Calendar.getInstance().get(Calendar.SECOND);

                    ServerP2P = false;//点击按钮，切换聊天到广播状态

                    MessageRecords.append("Server " + MessageInformation + " 停止发送消息给 " + clientp2p.ClientName
                            + " ，开始广播\n");

                }
            });

            //更新界面
            ClientList.removeAll();
            for (JButton NC : ClientsButton) {
                ClientList.add(NC);
            }
            ClientList.updateUI();


            // 通知客户端有用户上线
            send(ClientName + "IsOnline");

            // 先通知用户数量
            send("" + ClientMap.size());

            // 然后将所有用户名输出
            for (String FriendName : ClientsName)
                send(FriendName);

        }

        // 群发消息给所有用户
        void send(String msg) {
            for (ServerThread thread : ClientMap.values()) {
                // 给map内每一个对象发送msg
                thread.out.println(msg);
            }
        }


        void receive() throws IOException {

            String receivedMessage;

            // 为一对一聊天分配线程
            ServerThread Client_friend = null;

            ServerThread Client_self = null;
            // 接收消息
            while ((receivedMessage = in.readLine()) != null) {

                // 从接收到的消息中提取出用户名和消息
                ClientName = receivedMessage.substring(0, 8);
                receivedMessage = receivedMessage.substring(8);

                // 如果消息为quit则退出
                if ("quit".equalsIgnoreCase(receivedMessage)) {
                    stop();
                    // 给客户端发送关闭链接命令
                    out.println("disconnect");
                    break;

                }
                // 判断是否为一对一聊
                if (!P2P) {
                    if (receivedMessage.equals("single")) {

                        System.out.println("Server: " + ClientName + " 申请一对一聊天，一对一好友名：");

                        String FriendName;
                        // 如果为一对一聊天就获得想要跟谁聊
                        if ((FriendName = in.readLine()) != null) {

                            // 提取一对一聊天好友的消息
                            FriendName = FriendName.substring(8);

                            System.out.println(FriendName);

                            MessageInformation
                                    = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":"
                                    + Calendar.getInstance().get(Calendar.MINUTE) + ":"
                                    + Calendar.getInstance().get(Calendar.SECOND);

                            System.out.println("Server:" + MessageInformation + "\n" + ClientName
                                    + " 申请一对一聊天，一对一好友名：" + FriendName);

                            MessageRecords.append("Server:" + MessageInformation + "\n" + ClientName
                                    + " 申请一对一聊天，一对一好友名：" + FriendName + "\n");

                            // 获得另外一个客户端线程
                            Client_friend = ClientMap.get(FriendName);
                            Client_self = ClientMap.get(ClientName);

                            if (Client_friend != null) {
                                P2P = true;

                                MessageRecords.append(ClientName + " 正在与 " + FriendName + " 对话\n");
                                Client_self.out.println(ClientName + " 正在与 " + FriendName + " 对话");

                            }


                        }
                    } else {
                        MessageInformation
                                = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":"
                                + Calendar.getInstance().get(Calendar.MINUTE) + ":"
                                + Calendar.getInstance().get(Calendar.SECOND);


                        MessageRecords.append(ClientName + " send to all clients " + MessageInformation
                                + "\n" + receivedMessage + " \n");

                        // 转发消息
                        //  群发
                        send(ClientName + " send to all clients " + MessageInformation + "\r" + receivedMessage);

                    }

                }
                // 如果是一对一聊天就给对应的客户端发送消息；
                else {
                    // 如果为quitP2P则退出一对一聊天
                    if (receivedMessage.equals("quitP2P")) {
                        MessageInformation
                                = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":"
                                + Calendar.getInstance().get(Calendar.MINUTE) + ":"
                                + Calendar.getInstance().get(Calendar.SECOND);

                        P2P = false;
                        assert Client_friend != null;
                        Client_self.out.println(ClientName + " " + MessageInformation + " \r与 "
                                + Client_friend.ClientName + " 私聊结束");

                        MessageRecords.append(ClientName + " " + MessageInformation + " \r与 "
                                + Client_friend.ClientName + " 私聊结束\n");

                    }
                    // 否则发送消息
                    else {

                        MessageInformation
                                = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":"
                                + Calendar.getInstance().get(Calendar.MINUTE) + ":"
                                + Calendar.getInstance().get(Calendar.SECOND);

                        assert Client_friend != null;

                        MessageRecords.append("Server: " + MessageInformation + "\n" + ClientName + " 对 "
                                + Client_friend.ClientName + " 说：\n" + receivedMessage + "\n");



                        Client_self.out.println("你 " + MessageInformation + " 对 " + Client_friend.ClientName
                                + " 说：\r" + receivedMessage);

                        Client_friend.out.println(ClientName + " " + MessageInformation + " 对你说：\r" + receivedMessage);

                    }
                }

            }
        }

        void stop() {
            KeepRunning = false;
            // 下线;
            ClientMap.remove(ClientName);

            ClientsButton.remove(ClientsName.indexOf(ClientName));
            ClientsName.remove(ClientName);

            ClientList.removeAll();
            for (JButton NC : ClientsButton) {
                ClientList.add(NC);
            }
            ClientList.updateUI();

            MessageInformation
                    = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":"
                    + Calendar.getInstance().get(Calendar.MINUTE) + ":"
                    + Calendar.getInstance().get(Calendar.SECOND);

            send(ClientName + "Offline");

            MessageRecords.append("Server " + MessageInformation + "\n" + ClientName + " 已经下线了" + "\n");
        }

        /**
         * run方法
         */
        @Override
        public void run() {


            try {
                while (KeepRunning) {
                    // 不停接收消息
                    receive();
                }
            } catch (SocketException e) {
                stop();// 客户端直接关闭引发的错误；
            } catch (IOException e) {

                e.printStackTrace();
            } finally {
                // 释放资源
                try {
                    if (socket != null) {
                        socket.close();
                    }
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * Create the frame.
     */
    private String sendingMessage;
    private JTextArea MessageRecords;
    private JTextArea sendingArea;

    private Server(String s) {
        super(s);
        getContentPane().setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 895, 513);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{698, 0, 0};
        gridBagLayout.rowHeights = new int[]{385, 0, 0};
        gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);

        JScrollPane scrollPane = new JScrollPane();
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.insets = new Insets(0, 1, 5, 5);
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 0;
        getContentPane().add(scrollPane, gbc_scrollPane);

        MessageRecords = new JTextArea();
        MessageRecords.setTabSize(15);
        MessageRecords.setForeground(Color.LIGHT_GRAY);
        MessageRecords.setFont(new Font("宋体", Font.PLAIN, 14));
        MessageRecords.setBackground(Color.BLACK);
        MessageRecords.setEditable(false);
        scrollPane.setViewportView(MessageRecords);


        ClientList = new JPanel();
        ClientList.setBackground(Color.BLACK);
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.insets = new Insets(0, 0, 5, 5);
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 1;
        gbc_panel.gridy = 0;
        getContentPane().add(ClientList, gbc_panel);
        ClientList.setLayout(new GridLayout(10, 2, 0, 0));


        JScrollPane scrollPane_2 = new JScrollPane();
        GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
        gbc_scrollPane_2.insets = new Insets(0, 1, 5, 5);
        gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
        gbc_scrollPane_2.gridx = 0;
        gbc_scrollPane_2.gridy = 1;
        getContentPane().add(scrollPane_2, gbc_scrollPane_2);

        sendingArea = new JTextArea();
        sendingArea.setForeground(Color.LIGHT_GRAY);
        sendingArea.setBackground(Color.BLACK);
        sendingArea.setFont(new Font("宋体", Font.PLAIN, 16));
        scrollPane_2.setViewportView(sendingArea);

        JButton btnNewButton = new JButton("发 送");
        btnNewButton.setForeground(Color.YELLOW);
        btnNewButton.setBackground(Color.BLACK);
        btnNewButton.addActionListener(e -> {
            sendingMessage = sendingArea.getText();
            sendingArea.setText("");
            // 当发送区的字符串不为空时按下发送按钮才有效
            if (sendingMessage != null && sendingMessage.length() > 0)
                IsPressedSendButton = true;

            MessageInformation
                    = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":"
                    + Calendar.getInstance().get(Calendar.MINUTE) + ":"
                    + Calendar.getInstance().get(Calendar.SECOND);

            if (!ServerP2P) {
                MessageRecords.append("Server to all clients " + MessageInformation + "\n" + sendingMessage + "\n");

            }
            //  是，发送给某一个客户
            else {
                MessageRecords.append("Server " + MessageInformation + " to " + clientp2p.ClientName + "\n"
                        + sendingMessage + "\n");
                clientp2p.out.println("Server " + MessageInformation + " to " + clientp2p.ClientName
                        + "\r" + sendingMessage);


            }

        });

        btnNewButton.setFont(new Font("宋体", Font.PLAIN, 25));
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton.fill = GridBagConstraints.BOTH;
        gbc_btnNewButton.gridx = 1;
        gbc_btnNewButton.gridy = 1;
        getContentPane().add(btnNewButton, gbc_btnNewButton);

    }

}