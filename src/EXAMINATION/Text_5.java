package EXAMINATION;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class Text_5 {
    public static void main(String args[]) {
        ComputerFrame fr=new ComputerFrame();

    }

}

class ComputerFrame extends JFrame implements ActionListener {
    JTextField text1,text2,text3;

    JButton buttonAdd,buttonSub,buttonMul,buttonDiv;

    JLabel label;

    public ComputerFrame() {
        setLayout(new FlowLayout());

        text1=new JTextField(10);

        text2=new JTextField(10);

        text3=new JTextField(10);

        label=new JLabel(" ",JLabel.CENTER);

        label.setBackground(Color.green);

        add(text1);

        add(label);

        add(text2);

        add(text3);

        buttonAdd=new JButton("加");

        buttonSub=new JButton("减");

        buttonMul=new JButton("乘");

        buttonDiv=new JButton("除");

        add(buttonAdd);

        add(buttonSub);

        add(buttonMul);

        add(buttonDiv);

        buttonAdd.addActionListener(this);

        buttonSub.addActionListener(this);

        buttonMul.addActionListener(this);

        buttonDiv.addActionListener(this);

        setSize(380,320);

        setVisible(true);

        validate();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    public void actionPerformed(ActionEvent e) {
        double n;

        if(e.getSource()==buttonAdd) {
            double n1,n2;

            try{ n1=Double.parseDouble(text1.getText());

                n2=Double.parseDouble(text2.getText());

                n=n1+n2;

                text3.setText(String.valueOf(n));

                label.setText("+");

            }

            catch(NumberFormatException ee)

            { text3.setText("请输入数字字符");

            }

        }

        else if(e.getSource()==buttonSub) {
            double n1,n2;

            try{  n1=Double.parseDouble(text1.getText());

                n2=Double.parseDouble(text2.getText());

                n=n1-n2;

                text3.setText(String.valueOf(n));

                label.setText("-");

            }

            catch(NumberFormatException ee)

            { text3.setText("请输入数字字符");

            }

        }

        else if(e.getSource()==buttonMul)

        {double n1,n2;

            try{ n1=Double.parseDouble(text1.getText());

                n2=Double.parseDouble(text2.getText());

                n=n1*n2;

                text3.setText(String.valueOf(n));

                label.setText("*");

            }

            catch(NumberFormatException ee)

            { text3.setText("请输入数字字符");

            }

        }

        else if(e.getSource()==buttonDiv)

        {double n1,n2;

            try{ n1=Double.parseDouble(text1.getText());

                n2=Double.parseDouble(text2.getText());

                n=n1/n2;

                text3.setText(String.valueOf(n));

                label.setText("/");

            }

            catch(NumberFormatException ee)

            { text3.setText("请输入数字字符");

            }

        }

        validate();

    }

}