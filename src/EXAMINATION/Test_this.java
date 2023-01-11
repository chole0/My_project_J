package EXAMINATION;

public class Test_this {
    public static class Animals{
        int age;
        String moving;
        String sex;
        enum bb{red,yellow,blue,purple}
        bb color;
        public void setanimal(int age,String moving,String sex,int choose){
            this.age=age;
            this.moving=moving;
            this.sex=sex;
            color=bb.values()[choose];
            System.out.println("小狗的年龄是:"+age);
            System.out.println("小狗的动作是:"+moving);
            System.out.println("小狗性别是:"+sex);
            System.out.println("小狗的颜色是"+color);
        }


    }

    public  static void main(String args[]) {
        Animals dog = new Animals();
        Animals cat = new Animals();
        Animals pig = new Animals();
        dog.setanimal(20,"站立","公",1);
        cat.setanimal(10,"喝水","母",2);
        pig.setanimal(15,"吃饭","公",3);
        Animals.bb a;
        a= Animals.bb.blue;
        int i;
        for(i=0;i< Animals.bb.values().length;i++){
            System.out.println(Animals.bb.values()[i].compareTo(a));
        }
    }
}
