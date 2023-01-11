package Text_interface;
import javax.swing.*;
import java.awt.*;

public class Creat_windows {
    public static void main(String[]args){
        JFrame window1 = new JFrame("第一窗口");
        JFrame window2 = new JFrame("第二窗口");
        Container con = window1.getContentPane();
        con.setBackground(Color.red);
        window1.setBounds(160,100,188,108);
        con.setBackground(Color.red);
        window1.setBounds(100,100,188,108);
        window1.setVisible(true);
        window1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window2.setVisible(true);
        window2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


    }
}
