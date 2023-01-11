package listudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class peopletype {
    private final  static  String host="localhost";
    private final  static  Integer port=8888;
    public  static  void main(String[] args)  {
        Socket socket=null;
        //输入流
        BufferedReader reader=null;

        //创建客户端对象
        try {
            //连接服务器
            socket=new Socket(host,port);
            //客户端刚连接的时候，客户端反馈信息
            //输出流
            final PrintWriter writer=new PrintWriter(socket.getOutputStream());;
            String  send_msg ="你好，我是客户端......--[客户端]";
            writer.println(send_msg);
            writer.flush();
            //开启一个线程，处理循环
            Thread write_Stream=  new Thread(new Runnable() {
                @Override
                public void run() {
                    //循环输入数据
                    Scanner scanner=new Scanner(System.in);
                    while ("1".equals("1"))
                    {
                        String send_msg=scanner.nextLine()+"--[Client]";
                        writer.println(send_msg);
                        writer.flush();
                    }
                }
            });
            write_Stream.start();
            while ("1".equals("1")) {
                //获取输入流，得服务端的数据
                reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String line=null;
                while ((line=reader.readLine())!=null)
                {
                    System.out.println(line);
                }
            }
            writer.close();
        } catch (IOException e) {
            //链接失败   文件读取失败
            e.printStackTrace();
        }finally {
            //关闭流
            try {
                reader.close();

                socket.shutdownInput();
                socket.shutdownOutput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

