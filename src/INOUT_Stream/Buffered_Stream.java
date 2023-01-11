package INOUT_Stream;

import java.io.*;
import java.text.FieldPosition;
import java.util.StringTokenizer;

public class Buffered_Stream {
    public static void main(String []args){
        try {
            File fread = new File("C:\\Users\\ASUS\\IdeaProjects\\My_project_J\\src\\INOUT_Stream\\student.txt");
            File fwrite = new File("C:\\Users\\ASUS\\IdeaProjects\\My_project_J\\src\\INOUT_Stream\\a.txt");
            Writer out = new FileWriter(fwrite);
            BufferedWriter bufferedWriter = new BufferedWriter(out);
            Reader in = new FileReader(fread);
            BufferedReader bufferedReader = new BufferedReader(in);
            String str = null;
            while((str=bufferedReader.readLine())!=null){
                StringTokenizer fenxi = new StringTokenizer(str);
                int count =fenxi.countTokens();
                str=str+"The number of word is: "+count;
                bufferedWriter.write(str);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            out.flush();
            bufferedWriter.close();
            out.close();
            in = new FileReader(fwrite);
            bufferedReader =new BufferedReader(in);
            String s=null;
            System.out.println(fwrite.getName()+"are:");
            while((s=bufferedReader.readLine())!=null){
                System.out.println(s);
            }
            bufferedWriter.flush();
            out.flush();
            bufferedWriter.close();
            out.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
