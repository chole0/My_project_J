package listudy;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 文件传输server（由服务器发送）
 *
 * @author qxt
 * @date 2021/3/8 17:38
 */
public class FileSend extends ServerSocket {
    /**
     * 服务器端口
     */
    private static final int SERVER_PORT = 8888;
    /**
     * 文件路径
     */
    private String fileURL;

    private ServerSocket server;

    public FileSend(String fileURL) throws Exception {
        super(SERVER_PORT);
        this.server = this;
        this.fileURL = fileURL;
        System.out.println("ip:  "+server.getInetAddress());
    }

    /**
     * 等待连接
     *
     * @throws Exception
     */
    public void waiting() throws Exception {
        File file = new File(fileURL);
        while (true) {
            //当阻塞时接受新的连入请求
            Socket client = this.accept();
            //并建立新的线程进行处理
            new Handler(client, file);
        }
    }

    /**
     * 线程处理类
     *
     * @author Walskor
     */
    private static class Handler implements Runnable {
        private Socket socket;
        private FileInputStream fileIn;
        private DataOutputStream DataOUT;
        private File file;

        public Handler(Socket client, File file) {
            this.socket = client;
            this.file = file;
            new Thread(this).start();
        }

        @Override
        public void run() {
            try {
                sendFile(file);  //传输文件
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (Exception e) {
                        socket = null;
                        System.out.println("Finally error: " + e.getMessage());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * 向客户端传输文件
         *
         * @throws Exception
         */
        private void sendFile(File file) throws Exception {
            try {
                if (file.exists()) {
                    fileIn = new FileInputStream(file);
                    DataOUT = new DataOutputStream(socket.getOutputStream());

                    //文件名和长度
                    DataOUT.writeUTF(file.getName());
                    DataOUT.flush();
                    DataOUT.writeLong(file.length());
                    DataOUT.flush();

                    //开始传输文件
                    System.out.println("=========Start to transfer=========");
                    byte[] bytes = new byte[1024];
                    int length = 0;
                    long progress = 0;
                    while ((length = fileIn.read(bytes, 0, bytes.length)) != -1) {
                        DataOUT.write(bytes, 0, length);
                        DataOUT.flush();
                        progress += length;
                        System.out.println("| " + (100 * progress / file.length()) + "% |");
                    }
                    System.out.println();
                    System.out.println("=====File transferred successfully=====");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {                                //关闭数据流
                if (fileIn != null) {
                    fileIn.close();
                }
                if (DataOUT != null) {
                    DataOUT.close();
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Server starting...");
        try {
            FileSend transfer = new FileSend("文件路径");
            transfer.waiting();
            if (transfer != null) {
                transfer.close();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

