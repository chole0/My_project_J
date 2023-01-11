package Text_interface;
public class RedCowForm {
    static String FormName;
    RedCow cow;
    RedCowForm(String s) {
        cow = new RedCow(150, 112, 500);
        FormName = s;
    }

    public void ShowCowMess(){
        cow.speak();
    }

    class RedCow {
        String cowName = "红牛";
        int heigh, weight, price;

        RedCow(int h, int w, int p) {
            heigh = h;
            weight = w;
            price = p;
        }
        void speak() {
            System.out.println("此牛是" + cowName +","+ "身高是" + heigh +","+ "体重是" + weight + ","+"价格是" + price);
        }
    }
}

