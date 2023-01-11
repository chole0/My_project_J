package listudy;
import java.io.*;
import java.net.*;

public class ECHOserver {
    public static void main(String[] args)throws IOException {
        Socket clientSocket;
        ServerSocket listenSocket;
        BufferedReader in;
        BufferedWriter out;
        listenSocket = new ServerSocket();
        SocketAddress serverAddr=new InetSocketAddress("localhost",5000);
        listenSocket.bind(serverAddr);
        System.out.println("1.服务器启动成功！开始在 5000 端口侦听连 接...");
        clientSocket = listenSocket.accept();
        System.out.println("2.客户机连接成功！客户机地址和端口： "+clientSocket.getInetAddress());
        in = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));
        out = new BufferedWriter( new OutputStreamWriter( clientSocket.getOutputStream()));
        String recvStr=in.readLine();
        System.out.println("3.1 服务器收到字符串："+recvStr);
        out.write(recvStr); out.newLine(); out.flush();
        try {
                if (in != null) in.close();
                if (out != null) out.close();
                if (listenSocket != null) listenSocket.close();
                if (clientSocket != null) clientSocket.close();
                System.out.println("4.关闭套接字和流成功！");
            } catch (IOException ex) { System.out.println("异常信息"+ex.getMessage());
        }
    }
}

