package listudy;

import java.io.IOException;
import java.io.*;
import java.net.*;

public class ECHOlisten {
    public static void main(String[] args) throws IOException {
        Socket clientSocket = null;
        BufferedReader in = null;
        BufferedWriter out = null;
        try {
            clientSocket = new Socket();
            SocketAddress remoteAddr = new InetSocketAddress("localhost", 5000);
            System.out.println("1.创建客户机套接字成功！");
            clientSocket.connect(remoteAddr);
            System.out.println("2.客户机连接服务器 localhost 端口 5000 成功！ ");
            System.out.println("客户机的地址和端口： " + clientSocket.getLocalSocketAddress());
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            String sendStr = "有朋自远方来";
            out.write(sendStr);
            out.newLine();
            out.flush();
            System.out.println("3.1 向服务器发送字符串成功!" + sendStr);
            String recvStr = in.readLine();
            System.out.println("3.2 从服务器接收回送字符串成功！" + recvStr);
        } catch (IOException ex) {
            System.out.println("异常信息：" + ex.getMessage());
            try {
                if (in != null) in.close();
                if (out != null) out.close();
                if (clientSocket != null) clientSocket.close();
                System.out.println("4.关闭套接字和流成功！");
            } catch (IOException e) {
                System.out.println("异常信息：" + ex.getMessage());
            }
        }
    }
}