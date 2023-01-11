package listudy;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SessionThread  implements Runnable {
    private final Socket client;
    public SessionThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        //输出流
        PrintWriter writer=null;
        //获取客户端输入流，得到客户端发来的消息
        try {
            //读取客户端的数据
            //读取到的一行数据
            String line=null;
            //客户端发来的数据
            //开启一个读线程
            Thread write_thread= new Thread(new Runnable() {
                @Override
                public void run() {
                    //输入流
                    InputStream inputStream=null;
                    BufferedReader reader=null;
                    try {
                        while (true) {

                            inputStream= client.getInputStream();
                            //为了提高效率，转换成字符流
                            reader=new BufferedReader(new InputStreamReader(inputStream));
                            String line=null;
                            while ((line=reader.readLine())!=null)
                            {
                                System.out.println(line);
                            }
                        }
                    } catch (IOException e) {
                        //流异常
                        e.printStackTrace();
                    }finally {
                        //关闭流
                        try {
                            inputStream.close();
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
            });
            write_thread.start();
            //获取输出流，往客户端发送数据
            writer=new PrintWriter(client.getOutputStream());
            //客户端刚连接的时候，客户端反馈信息
            String send_msg ="您已成功连接到服务器......--[服务器]";
            writer.println(send_msg);
            writer.flush();
            //可以多次发送
            //从键盘输入
            while ("1".equals("1")) {
                Scanner scanner=new Scanner(System.in);
                send_msg =scanner.nextLine()+"--[服务器]";
                writer.println(send_msg);
                //注意（我们发送数据的时候，flush方法会提交我们的数据，但是还不会发送，当数据达到一定容量才会发送，
                // 或者读到换行符的时候发送）
                writer.flush();
            }

        } catch (IOException e) {
            //获取客户端输入流失败
            e.printStackTrace();
        }finally {
            //关闭流
            try {
                client.shutdownInput();
                client.shutdownOutput();
                writer.close();
            } catch (IOException e) {
                //关闭输入输出流失败
                e.printStackTrace();
            }


        }

    }
}
