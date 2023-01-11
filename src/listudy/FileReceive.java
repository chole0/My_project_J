package listudy;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.RoundingMode;
import java.net.Socket;
import java.text.DecimalFormat;

/**
 * 文件接收端 Client（由服务器发送）
 *
 * @author qxt
 * @date 2021/3/8 17:38
 */
public class FileReceive extends Socket {
    /**
     * 服务端IP
     */
    private static final String SERVER_IP = "localhost";
    /**
     * 服务端端口号
     */
    private static final int SERVER_PORT = 8888;
    private Socket server;
    private DataOutputStream outputStream;
    /**
     * 保存接收的文件的文件夹路径
     */
    private final String directoryURL;

    public FileReceive(String directoryURL) throws Exception {
        super(SERVER_IP, SERVER_PORT);
        this.directoryURL = directoryURL;
        this.server = this;
        this.outputStream = new DataOutputStream(
                server.getOutputStream());
        System.out.println("this cilent[port:" + this.getLocalPort() + "] attach to server successfully");
    }

    /**
     * 发送参数
     *
     * @throws Exception
     */
    public void send() throws Exception {
        outputStream.writeUTF("数据流");
        //清空数据流
        outputStream.flush();
    }

    /**
     * 接收文件
     */
    public void receive() {
        LoadFile loadFile = new LoadFile(this, directoryURL);
        loadFile.get();
        System.out.println("end of load");
    }

    private static class LoadFile {
        private Socket socket;
        public String fileName;
        public long fileLength;
        private DataInputStream diStream;
        private FileOutputStream foStream;
        private static DecimalFormat dFormat;
        private final String directoryURL;

        static {
            dFormat = new DecimalFormat("#0.0");
            dFormat.setRoundingMode(RoundingMode.HALF_UP);
            dFormat.setMinimumFractionDigits(1);
            dFormat.setMaximumFractionDigits(1);
        }//设置数字格式，保留一位有效数字

        public LoadFile(Socket socket, String directoryURL) {
            this.socket = socket;
            this.directoryURL = directoryURL;
        }

        public void get() {
            try {
                diStream = new DataInputStream(socket.getInputStream());

                //文件名和长度
                fileName = diStream.readUTF();
                fileLength = diStream.readLong();
                File directory = new File(directoryURL);
                if (!directory.exists()) {
                    directory.mkdir();
                }
                File file = new File(directory.getAbsolutePath() + File.separatorChar + fileName);
                foStream = new FileOutputStream(file);

                //开始接收文件
                byte[] bytes = new byte[1024];
                int length = 0;
                while ((length = diStream.read(bytes, 0, bytes.length)) != -1) {
                    foStream.write(bytes, 0, length);
                    foStream.flush();
                }
                System.out.println("File received [ File Name: " + fileName + " ] [ Size: " + getFormatFileSize(fileLength) + " ] ===");

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (foStream != null) {
                        foStream.close();
                    }
                    if (diStream != null) {
                        diStream.close();
                    }
                    socket.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }

            }// end try

        }// end get

        /**
         * 格式化文件大小
         *
         * @param length
         * @return
         */
        private String getFormatFileSize(long length) {
            double size = ((double) length) / (1 << 30);
            if (size >= 1) {
                return dFormat.format(size) + "GB";
            }
            size = ((double) length) / (1 << 20);
            if (size >= 1) {
                return dFormat.format(size) + "MB";
            }
            size = ((double) length) / (1 << 10);
            if (size >= 1) {
                return dFormat.format(size) + "KB";
            }
            return length + "B";
        }
    }

    public static void main(String[] args) {
        String directoryURL = "保存文件的文件夹路径";
        try {
            FileReceive client = new FileReceive(directoryURL);
            client.receive();
            //关闭数据流
            if (client.outputStream != null) {
                try {
                    client.outputStream.close();
                } catch (Exception e) {
                    client.outputStream = null;
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

