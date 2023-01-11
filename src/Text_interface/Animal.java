package Text_interface;

public class Animal {
    public static void main(String []args){
        RedCowForm form = new RedCowForm("...");
        form.ShowCowMess();
        form.cow.speak();
    }
}
