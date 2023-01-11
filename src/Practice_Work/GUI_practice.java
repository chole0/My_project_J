package Practice_Work;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import static javax.swing.JFrame.*;

public class GUI_practice {
    public static class WindowMenu extends JFrame{
        JMenuBar menubar;
        JMenu menu,sbumenu;
        JMenuItem item1,item2;
        public WindowMenu(String s,int x,int y,int i,int j){
            init(s);
            setLocation(x,y);
            setSize(i,j);
            setVisible(true);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
        void init(String s){
            setTitle(s);
            menubar =new JMenuBar();
            menu = new JMenu("菜单");
            sbumenu = new JMenu("软件项目");
            item1 = new JMenuItem("java话题",new ImageIcon("a.gif"));
            item2 = new JMenuItem("动画话题",new ImageIcon("b.gif"));
            item1.setAccelerator(KeyStroke.getKeyStroke('A'));
            item2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));
            menu.add(item1);
            menu.addSeparator();
            menu.add(item2);
            menu.add(sbumenu);
            sbumenu.add(new JMenuItem("汽车销售系统",new ImageIcon("c.gif")));
            sbumenu.add(new JMenuItem("农场销售系统",new ImageIcon("d.gif")));
            menubar.add(menu);
            setJMenuBar(menubar);
        }
    }
    public static void main(String []args){
        WindowMenu win = new WindowMenu("含有菜单窗口",20,30,200,190);
        JFrame window1 = new JFrame("窗口一");
        JFrame window2 = new JFrame("窗口二");
        Container con = window1.getContentPane();
        window1.setBounds(100,200,300,400);
        con.setBackground(Color.yellow);
        window2.setBounds(100,200,300,400);
        Container com = window2.getContentPane();
        com.setBackground(Color.red);
        window1.setVisible(true);
        window1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window2.setVisible(true);
        window2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }
}
