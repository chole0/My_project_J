package Text_interface;
public class interface_work_1 {
    interface A{
        double f(double x,double y);
    }

    static class B implements A{
        public double f(double x,double y){
            return x*y;
        }

        int g(int a,int b){
            return a+b;
        }
    }
    public static  void  main(String []args){
        A a=new B();
        System.out.println(a.f(3,5));
        B b=(B)a;
        System.out.println(b.g(3,5));

    }
}
