package EXAMINATION;
import java.util.Scanner;

public class Text_1 {
    public static void main(String []args) {
        Scanner reader = new Scanner(System.in);
        int a = reader.nextInt();
        int b = reader.nextInt();
        int c = reader.nextInt();
        int d = 0;
        System.out.println("原来序列为："+a+" "+b+" "+c);

        if(a>b) {
            d = a;
            a = b;
            b = d;
        }
        if(a>c) {
            d=a;
            a=c;
            c=d;
        }
        if(b>c) {
            d=b;
            b=c;
            c=d;
        }
                System.out.println("现在序列为 "+a+" "+b+" "+c);
        }
    }


