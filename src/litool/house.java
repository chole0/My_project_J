package litool;

public class house implements Runnable {
    int waterAmount;
    public void setWaterAmount (int w){
        waterAmount=w;
    }
    public void run(){
        while(true){
            String name = Thread.currentThread().getName();
            if(name.equals("狗")){
                System.out.println(name+"喝水");
                waterAmount-=2;
            }
            if(name.equals("猫")){
                System.out.println(name+"喝水");
                waterAmount-=1;
            }
            System.out.println("剩余:"+waterAmount);
            try{
                Thread.sleep(200);
            }catch (InterruptedException ex){}
                if (waterAmount<0){
                    return;
                }


        }
    }
}
