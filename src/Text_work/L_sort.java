package Text_work;

public class L_sort {
   public static void main(String []args)
   {
       int a=5,b=7,c=1,t=0;
       if(a>b) {
           t=a;
           a=b;
           b=t;
       }
       if(a>c) {
           t=a;
           a=c;
           b=t;
       }
       if(b>c) {
           t=b;
           c=b;
           b=t;
       }
       System.out.println("a="+a+" b="+b+" c="+c);
   }
}
