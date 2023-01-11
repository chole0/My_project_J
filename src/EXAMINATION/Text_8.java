package EXAMINATION;

import java.io.*;
import java.lang.*;
public class Text_8 {
    public static void main(String []args){
        try{
            Runtime ex = Runtime.getRuntime();
            File file = new File("C:\\windows","Notepad.exe");
            ex.exec(file.getAbsolutePath());
        }catch (Exception e)
        {

        }
    }
}
