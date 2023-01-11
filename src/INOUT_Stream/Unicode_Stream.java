package INOUT_Stream;

import java.io.*;

public class Unicode_Stream {
    public static void main(String[] args) {
        File SourceFile = new File("C:\\Users\\ASUS\\IdeaProjects\\My_project_J\\src\\INOUT_Stream\\a.txt");
        File TargetFile = new File("C:\\Users\\ASUS\\IdeaProjects\\My_project_J\\src\\INOUT_Stream\\b.txt");
        char[] cache = new char[20];
        try {
            Writer out = new FileWriter(TargetFile, true);
            Reader in = new FileReader(SourceFile);
            int n;
            while((n=in.read(cache))!=-1){
                out.write(cache,0,n);
                String s = new String(cache,0,n);
                System.out.println(s);
            }

            out.flush();
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
