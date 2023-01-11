package litool;

import java.net.*;
import java.io.*;
public class kehu {
    public static void main(String []args)throws Exception {
        Socket clientsocket = new Socket();
        BufferedWriter out=null;
        BufferedReader in = null;
        try {
            SocketAddress clientaddr = new InetSocketAddress("localhost", 5000);
            System.out.println("创建套接字成功.....");
            clientsocket.connect(clientaddr);
            System.out.println("客户机连接端口5000成功.....");
            System.out.println("取得对方端口及IP为：" + clientsocket.getInetAddress());
            in = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(clientsocket.getOutputStream()));
            String sendstr = "不要回复！";
            String sendstr2 = "不要回复！！";
            String sendstr3 = "不要回复！！！";
            out.write(sendstr);
            out.write(sendstr2);
            out.write(sendstr3);
            out.newLine();
            out.flush();
            System.out.println("发送字节成功");
            String res = in.readLine();
            System.out.println("收到信息为：" + res);
        }catch(Exception e) {
            try {
                out.close();
                in.close();
                clientsocket.close();
                System.out.println("关闭成功....");
            } catch (Exception ex) {

            }
        }
    }

}
