package litool;

import java.io.*;
public class GetFile {
    public static void main(String []args) {
        File dirfile = new File(".");
        FileAccept fielaccept = new FileAccept();
        fielaccept.setExtendName("java");
        String fielName[]=dirfile.list(fielaccept);
        for(String name:fielName){
            System.out.println(name);
        }
    }
}
