package Text_interface;

public class interface_work_2 {
    interface Com{
        int add(int a,int b);
    }
    abstract static class  A{
        abstract int add(int a,int b);
    }
    static class B extends A implements Com {
        public int add(int a,int b){
            return a+b;
        }
    }
    public static void main(String[] args) {
        B b = new B();
        Com com = b;
        System.out.println(com.add(12,6));
        A a=b;
        System.out.println(a.add(10,5));
    }
}
