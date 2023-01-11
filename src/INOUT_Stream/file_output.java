package INOUT_Stream;//写东西进文件

import java.io.File;
import java.io.FileOutputStream;

public class file_output {
    public static void main(String []args) {

        try {
            byte[] a = "你好".getBytes("GBK");//此处是将a[]中的转化为字节，并且以某种编码形式转化按照getBytes（）中来进行
            byte[] b = "Hello".getBytes();
            File f = new File("C:\\Users\\ASUS\\IdeaProjects\\My_project_J\\src\\INOUT_Stream\\ll.txt");
            FileOutputStream out = new FileOutputStream(f,true);//创建对于f源的输出流，为true时，会保存原来文件中的，为false会重置刷新原来的，即清空。
            out.write(a);//从原文末尾开始写
            out.close();//关闭流
            System.out.println(f.getName()+"的大小是: "+f.length());
            out = new FileOutputStream(f,true);//重新建立流（在末尾）
            out.write(b,0,b.length);
            System.out.println(f.getName()+"的大小是: "+f.length());
            out.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
