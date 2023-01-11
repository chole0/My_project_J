package EXAMINATION;

public class Text_4{
    private interface Animal{
        void run();
        String getAnimalName();
    }

    private static class Bird implements Animal {

        public void run(){
            System.out.println("飞翔的样子是噗嗤噗嗤的煽动着翅膀");
        }

        public String getAnimalName(){
            return "鸟儿";
        }
    }

    private static class Fish implements Animal {

        public void run(){
            System.out.println("游动的样子是哗啦哗啦的嬉戏着水");
        }

        public String getAnimalName(){
            return "鱼儿";
        }
    }

    private static class Simulator{
        void playsound(Animal animal){
            System.out.printf("正在运动着的动物是: %s\n",animal.getAnimalName());
            System.out.print("它运动的姿态是：");
            animal .run();
        }
    }
    public  static void main(String []args){
        Simulator simulator = new Simulator();
        simulator.playsound(new Bird());
        simulator.playsound(new Fish());


    }


}