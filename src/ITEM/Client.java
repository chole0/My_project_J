package ITEM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;


public class Client extends JFrame {

    private String sendingMessage;


    //获取当前用户姓名
    private String ClientName;
    private PrintWriter out;


    private ArrayList<JButton> MyFriendsButton = new ArrayList<>();
    private ArrayList<String> MyFriends = new ArrayList<>();

    //socket
    private Socket s = null;

    //Socket的输入和输出
    private BufferedReader in = null;

    //当前客户端是否退出
    private boolean KeepingOnline = true;

    //是否按下发送按钮
    private boolean IsPressedSendButton = false;
    private JPanel FriendList;
    //一对一聊天标志
    private static boolean P2P = false;

    public static void main(String[] args) {

        Client client = new Client("客户端");
        client.setVisible(true);
        client.start();
    }


    //启动发送和接受任务
    private void start() {
        try {


            // 建立客户端socket
            s = new Socket("127.0.0.1", 5555);

            // 获得socket输出out，自动刷新
            out = new PrintWriter(s.getOutputStream(), true);

            // 获取输入
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            //服务器分配用户名
            ClientName = in.readLine();

            //在界面显示
            MessageRecords.append("Hello, " + ClientName + "!\n");

            this.FriendList.updateUI();

            //创建接收消息的线程
            new Thread(new ClientThread(this)).start();


            //用户保持在线，且按下了发送按钮
            while (KeepingOnline) {
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (IsPressedSendButton) {
                    //发送消息时附上自己的用户名
                    //客户端发送的消息包含 用户名+消息文本
                    out.println(ClientName + sendingMessage);
                    IsPressedSendButton = false;


                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (s != null) {
                try {
                    s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private class ClientThread implements Runnable {
        Client client;

        ClientThread(Client client) {
            this.client = client;
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                    System.out.println(ClientName+"下线");
                    client.out.println(ClientName+"quit");
                    KeepingOnline=false;

                }
            });
        }

        private void receive() {
            //接收服务端消息
            try {
                //接收到的消息
                //send(ClientName + " " + MessageInformation + "\r" + receivedMessage);
                //包含用户名、时间信息和消息内容
                String receivedMessage = in.readLine();


                if (receivedMessage != null) {
                    // 当接收到的信息中包含"IsOnline"，说明有新的用户上线
                    // 这时处理一下新的用户
                    // 在通知某用户上线时，后面会发来数条信息，这其中包括
                    // 1.当前用户个数
                    // 2.所有用户名称
                    if (receivedMessage.contains("IsOnline")) {


                        String NewFriend;

                        //先提取用户个数
                        int friendNum = Integer.valueOf(in.readLine());

                        //再读取所有用户的用户名
                        for (int i = 0; i < friendNum; i++) {
                            NewFriend = in.readLine();
                            if (!MyFriends.contains(NewFriend) && !NewFriend.equals(ClientName)) {
                                MyFriends.add(NewFriend);
                                JButton Nf = new JButton(NewFriend);
                                String finalNewFriend = NewFriend;
                                Nf.setBackground(SystemColor.textHighlight);
                                Nf.setForeground(Color.WHITE);
                                Nf.setFont(new Font("宋体", Font.BOLD, 16));
                                MyFriendsButton.add(Nf);
                                FriendList.removeAll();
                                for(JButton NewF:MyFriendsButton)
                                {
                                    FriendList.add(NewF);
                                }

                                Nf.addActionListener(e -> {
                                    if(P2P) {
                                        P2P = false;
                                        sendingMessage = "quitP2P";
                                        IsPressedSendButton = true;
                                    }
                                    else{
                                        sendingMessage = "single";
                                        P2P = true;
                                        IsPressedSendButton = true;
                                        try {
                                            Thread.sleep(10);
                                        } catch (InterruptedException e1) {
                                            e1.printStackTrace();
                                        }
                                        sendingMessage = finalNewFriend;
                                        IsPressedSendButton = true;
                                    }

                                });

                            }
                        }

                        client.FriendList.updateUI();
                    }

                    //处理下线问题
                    else if(receivedMessage.contains("Offline")){
                        String offlineFriend=receivedMessage.substring(0,8);

                        MyFriendsButton.remove(MyFriends.indexOf(offlineFriend));
                        //MyFriends.remove(offlineFriend);
                        client.FriendList.removeAll();
                        for(JButton NewF:MyFriendsButton)
                        {
                            client.FriendList.add(NewF);
                        }
                        System.out.println(receivedMessage+Integer.valueOf(receivedMessage.substring(7,8)));
                        //FriendList.getComponent(Integer.valueOf(receivedMessage.substring(7,8))).setVisible(false);
                        client.FriendList.updateUI();
                    }

                    //否则显示消息
                    else {

                        // 判断是否是传输文件
                        if(receivedMessage.length()>8 && receivedMessage.substring(0,8).equals("sendFile")) {

                            MessageRecords.append("开始传输文件，文件名称："+receivedMessage.substring(9));
                            //  接收文件，保存到电脑

                            String suffix = receivedMessage.substring(receivedMessage.lastIndexOf("."));
                            //创建服务器端输出流，从而把从客户端获得的文件保存到服务器本地，并且重新命名
                            FileOutputStream fos=new FileOutputStream("uploadAt"+System.currentTimeMillis() + suffix);
                            //获取网络输出字节流，从而向客户端发送数据
                            InputStream inputStream = s.getInputStream();

                            int len=0;
                            byte[] bytes=new byte[1024];
                            while ((len=inputStream.read(bytes))!=-1){
                                //保存收到的文件

                                fos.write(bytes,0,len);


                            }

                            MessageRecords.append("文件传输结束");

                        }
                        else {
                            MessageRecords.append(receivedMessage + "\n");
                        }

                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        //线程主要任务
        @Override
        public void run() {
            while (KeepingOnline) {
                //接收消息函数调用
                receive();
            }
        }
    }


    private JTextArea MessageRecords;
    private JTextArea sendingArea;

    private Client(String ClientName) {
        super(ClientName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 895, 513);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{698, 0, 0};
        gridBagLayout.rowHeights = new int[]{26, 351, 0, 0};
        gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);

        JScrollPane scrollPane = new JScrollPane();
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.gridheight = 2;
        gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 0;
        getContentPane().add(scrollPane, gbc_scrollPane);

        MessageRecords = new JTextArea();
        MessageRecords.setFont(new Font("宋体", Font.PLAIN, 16));
        MessageRecords.setBackground(Color.WHITE);
        MessageRecords.setEditable(false);
        scrollPane.setViewportView(MessageRecords);

        JLabel lblNewLabel = new JLabel("聊天记录");
        lblNewLabel.setBackground(Color.WHITE);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("宋体", Font.BOLD, 18));
        scrollPane.setColumnHeaderView(lblNewLabel);

        JLabel label = new JLabel("好友列表");
        GridBagConstraints gbc_label = new GridBagConstraints();
        gbc_label.insets = new Insets(0, 0, 5, 0);
        gbc_label.gridx = 1;
        gbc_label.gridy = 0;
        getContentPane().add(label, gbc_label);
        label.setFont(new Font("宋体", Font.BOLD, 18));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        FriendList = new JPanel();
        GridBagConstraints gbc_FriendList = new GridBagConstraints();
        gbc_FriendList.insets = new Insets(0, 0, 5, 5);
        gbc_FriendList.fill = GridBagConstraints.BOTH;
        gbc_FriendList.gridx = 1;
        gbc_FriendList.gridy = 1;
        getContentPane().add(FriendList, gbc_FriendList);
        FriendList.setLayout(new GridLayout(10, 2, 0, 0));

        //FriendList.setViewportView(list);

        JScrollPane scrollPane_2 = new JScrollPane();
        GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
        gbc_scrollPane_2.insets = new Insets(0, 0, 5, 5);
        gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
        gbc_scrollPane_2.gridx = 0;
        gbc_scrollPane_2.gridy = 2;
        getContentPane().add(scrollPane_2, gbc_scrollPane_2);

        sendingArea = new JTextArea();
        sendingArea.setFont(new Font("宋体", Font.PLAIN, 16));
        scrollPane_2.setViewportView(sendingArea);


        /*
         * Create the frame.
         */
        JButton btnNewButton = new JButton("发送(Send)");

        btnNewButton.addActionListener(e -> {
            sendingMessage = sendingArea.getText();
            sendingArea.setText("");

            if (sendingMessage != null && sendingMessage.length() > 0)
                IsPressedSendButton = true;

        });
        btnNewButton.setBackground(SystemColor.textHighlight);
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setFont(new Font("宋体", Font.BOLD, 20));
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton.fill = GridBagConstraints.BOTH;
        gbc_btnNewButton.gridx = 1;
        gbc_btnNewButton.gridy = 2;
        getContentPane().add(btnNewButton, gbc_btnNewButton);

    }

}