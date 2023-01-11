package Text_example;
import java.util.Scanner;
import java.util.Arrays;

public class Example1 {
    public static void main(String [] args){
        int []a={1,2,3};
        int []b={5,7};
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));

        System.out.println(a);
        System.out.println(b);


        Scanner x=new Scanner(System.in);
        int n=x.nextInt();
            b = a;
        System.out.println(a);
        System.out.println(b);
        if (n==2){
            System.out.println(a);
            System.out.println(b);
        }
    }
}
