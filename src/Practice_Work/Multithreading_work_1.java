package Practice_Work;

class Target implements Runnable{
    public void run(){
        for(int i=0;i<=10;i++){
            System.out.print("ok  ");
            try {
                Thread.sleep(1000);
            }
            catch(InterruptedException exp){}
        }
    }
}

public class Multithreading_work_1 {
    public static void main(String []arg){
        Target target = new Target();
        Thread thread = new Thread(target);//必须在()中套implements过runable的类，使其能够使用括号中的类方法。//
        thread.run();
        for(int i=0;i<=10;i++){
            System.out.print("yes  ");
            try {
                Thread.sleep(1000);
            }
            catch(InterruptedException exp){}
        }
    }
}
