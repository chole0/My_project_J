package litool;

public class drink {
    public static void main(String []args){
        house ho=new house();
        ho.setWaterAmount(10);
        Thread dog,cat;
        dog=new Thread(ho);
        cat=new Thread(ho);
        dog.setName("狗");
        cat.setName("猫");
        dog.start();
        cat.start();
    }
}
