package litool;

import java.net.*;
import java.io.*;

public class multiThread {
    public static void main(String []args) throws Exception{
        Socket clientScoket = new Socket();
        ServerSocket serversocket,listenSocket;
        BufferedReader in;
        BufferedWriter out;
        serversocket = new ServerSocket();
        listenSocket = new ServerSocket();
        SocketAddress addr = new InetSocketAddress("localhost",5000);
        listenSocket.bind(addr);
        System.out.println("创建套接字成功，正在开启5000端口监听");
        clientScoket=listenSocket.accept();
        System.out.println("监听成功，客户端地址为："+clientScoket.getInetAddress());
        in = new BufferedReader(new InputStreamReader(clientScoket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(clientScoket.getOutputStream()));
        String recvstr = in.readLine();
        System.out.println("收到的字符串为"+recvstr);
        out.write(recvstr);
        out.newLine();
        out.flush();
        try{
            in.close();
            out.close();
            clientScoket.close();
            serversocket.close();
            System.out.println("关闭成功.");
        }catch (Exception e)
        {
            System.out.println("wrong....");
        }
    }
}
