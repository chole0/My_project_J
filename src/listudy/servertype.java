package listudy;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class servertype {
    //设置端口号
    private  final static Integer PORT=8888;
    //设置开启的线程数
    private  final  static  Integer THREAD_SIZE=5;
    //创建线程池
    private  static ExecutorService executorService=null;
    public static void main(String[]args)
    {
        executorService  = Executors.newFixedThreadPool(THREAD_SIZE);
        //创建服务器套接字
        try {
            //创建三个线程放入到线程池中....
            ServerSocket serverSocket=new ServerSocket(PORT);
            //开启服务器，一直处于监听的状态
            System.out.println("[服务器]:服务器已启动.........");
            while (true)
            {
                //服务器根据端口号，监听客户端链接，
                Socket client = serverSocket.accept();
                System.out.println("[服务器]：有客户端链接至服务器.......");
                //将交互放到线程中
                SessionThread session=new SessionThread(client);
                //放入到线程池中
                session.run();
                executorService.execute(session);
            }
        } catch (IOException e) {
            //实例化服务器失败
            e.printStackTrace();
        }

    }
}
