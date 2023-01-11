package Practice_Work;

interface sayhello{
    void speak();
}
class changelange{
    void turnon(sayhello say ){
        say.speak();
    }
}
public class seventh {
    public static void main(String []args){
        changelange change = new changelange();
        change.turnon(new sayhello() {
            @Override
            public void speak() {
                System.out.printf("saa\n");
            }
        });
        change.turnon(new sayhello() {
            @Override
            public void speak() {
                System.out.printf("sadacfav");
            }
        });
    }
}
