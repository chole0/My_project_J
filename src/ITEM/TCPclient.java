package ITEM;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/*
    客户端
 */
public class TCPclient {
    public static void main(String[] args) throws IOException {
        //创建客户端，ip地址：127.0.0.1，端口号：8888
        Socket socket=new Socket("127.0.0.1",8888);

        //创建客户端本地输入流，从客户本地获取要上传的文件
        FileInputStream fis=new FileInputStream("C:\\Users\\ASUS\\IdeaProjects\\My_project_J\\src\\ITEM\\ll.jpg");
        //获取网络输出字节流，从而向服务器端发送数据
        OutputStream os = socket.getOutputStream();

        int len=0;
        byte[] bytes=new byte[1024];
        while ((len=fis.read(bytes))!=-1){
            //向服务器端发送文件
            os.write(bytes,0,len);
        }
        //关闭客户端输出流，中断上传
        socket.shutdownOutput();

        //获取网络字节输入流，从而获取服务器端传来的数据
        InputStream is = socket.getInputStream();

        while ((len=is.read(bytes))!=-1){
            //输出从服务器端获取的数据：“上传成功”
            System.out.println(new String(bytes,0,len));
        }

        //关闭客户端
        socket.close();
    }
}

