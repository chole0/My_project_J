package INOUT_Stream;

import java.io.File;
import java.io.RandomAccessFile;

public class Random_Stream {
    public static void main(String []args) {
        String n;
        File file = new File("b.txt");
        RandomAccessFile random_stream=null;
        int []date={1,2,3,4,5,6,7};
        try{
            random_stream = new RandomAccessFile(file,"rw");
            for(int i=0;i<date.length;i++){
                random_stream.writeInt(date[i]);
            }
            while((n=random_stream.readLine())!=null)
            {
                String str= random_stream.readLine();
                System.out.println(str);
            }
            random_stream.close();
        }catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
