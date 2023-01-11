package Text_interface;
import java.util.Scanner;

public class Calculator {
    static private float result;
    static void calculate(float op1,char sign,float op2)
                        throws IllegalSignExcepton,ArithmeticException{
        switch(sign){
            case '+':
                result = op1+op2;
                break;
            case '-':
                result = op1-op2;
                break;
            case 'X':
                result = op1*op2;
                break;
            case'/':
                if(op2==0)
                    throw new ArithmeticException();
                result = op1/op2;
                break;
            default:
                throw new IllegalSignExcepton("你输入的运算符不对");
        }
    }
public static void main(String []args) {
        float in0,in2;
        char in1;
    try {
        //in0 = Float.parseFloat(args[0]);
        //in1 = args[1].charAt(0);
        //in2 = Float.parseFloat(args[2]);
        Scanner reader = new Scanner(System.in);
        in0 = reader.nextFloat();
        in2 = reader.nextFloat();
        in1 = reader.next().charAt(0);
        calculate(in0, in1, in2);
        System.out.println(in0 + "" + in1 + "" + in2 + "=" + result);
    } catch (NumberFormatException e) {
        System.out.println("你输入的数非法，" + "乘号以X代替");
    } catch (ArrayIndexOutOfBoundsException aie) {
        System.out.println("未输入数据" + "该程序需要两个数字和一个运算符");
    } catch (ArithmeticException ae) {
        System.out.println("进行除法运算时，第二个数字不能为0。");
    } catch (IllegalSignExcepton ise) {
        System.out.println(ise.getMessage());
        System.out.println("每次只能输入（+，-，X,/）其中的一种" + "注意，乘号用X代替");
    } finally {
        System.out.println("谢谢使用");
    }
}
}

