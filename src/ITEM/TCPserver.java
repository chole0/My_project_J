package ITEM;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
    服务器端
 */
public class TCPserver {
    public static void main(String[] args) throws IOException {
        //创建服务器端，端口号：8888
        ServerSocket serverSocket=new ServerSocket(8888);

        while (true){
            //获取客户端对象
            Socket socket=serverSocket.accept();

            //创建服务器端输出流，从而把从客户端获得的文件保存到服务器本地，并且重新命名
            FileOutputStream fos=new FileOutputStream("upload"+System.currentTimeMillis()+".jpg");
            //获取网络输出字节流，从而向客户端发送数据
            InputStream inputStream = socket.getInputStream();

            int len=0;
            byte[] bytes=new byte[1024];
            while ((len=inputStream.read(bytes))!=-1){
                //保存从客户端收到的文件
                fos.write(bytes,0,len);
            }

            System.out.print("ssssss");
            //获取网络输出字节流，从而向客户端发送数据
            OutputStream outputStream = socket.getOutputStream();

            //向客户端发送信息：“上传成功”
            outputStream.write("上传成功".getBytes());

            //关闭客户端
            socket.close();
        }
    }
}


