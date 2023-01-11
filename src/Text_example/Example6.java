package Text_example;

public class Example6 {
    public static void main(String []args){
        C c=new C();
        c.m=20;
        System.out.println(c.getM());
        D d=c;
        d.m=-100;
        System.out.println(d.getM());
        System.out.println(c.seeM());

    }
}

class D{
    int m;
    int getM(){
        return m;
    }

    int seeM(){
        return m;
    }
}

class C extends D{
    int m;
    int getM(){
        return m+100;
    }
}
