package INOUT_Stream;//读取某个文件中的东西
import java.io.File;
import java.io.FileInputStream;

public class file_input {
    public static void main(String []args){
        int n;
        byte []a = new byte[100];//建立缓存区存储100字节
        try{
            File f = new File("C:\\Users\\ASUS\\IdeaProjects\\My_project_J\\src\\INOUT_Stream\\ll.txt");
            FileInputStream in = new FileInputStream(f);//建立源为f的输入流，然后这个f可用"..\\..\\ll.txt（路径）"来代替就不用写上一句了。
            while((n=in.read(a,0,100))!=-1){//循环读取，一次100，没读完继续读取
                String s = new String(a,0,n);//用s来存储n个字节东西
                System.out.println(s);
            }
            in.close();//关闭输入流
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
