package Text_work;

import java.util.Scanner;
public class text1 {
    public static void main (String[] args){
        float[] a;
        a=new float[5];
        Scanner reader=new Scanner(System.in);
        for(int i=0;i<5;i++)
        {
            a[i]=reader.nextFloat();
        }
        for(int i=0;i<5;i++) {
            System.out.println("a["+i+"]=" + a[i]);
        }

    }
}
