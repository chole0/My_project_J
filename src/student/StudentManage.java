package student;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeMap;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class StudentManage {
    static TreeMap<String,Student> ID =new TreeMap<String,Student>();//定义一个学生的key-value集合,通过TreeMap实现
    //Map<String, String>就是说里面只能放key为String 值为String。定义了ID这个变量
    static Student stu;
    public static void main(String[] args) throws IOException {
        try{
            System.out.println("【---请输入管理员账号：(账号:1;密码:1)】");
            System.out.println("请输入账号：");
            Scanner scanner = new Scanner(System.in);
            String username = scanner.nextLine();
            System.out.println("请输入密码：");
            String pswd = scanner.nextLine();
            if (username.contentEquals("1") && pswd.contentEquals("1")) {//当输入的账号密码均为1时进入if中说明登录成功
                System.out.println("登录系统成功...");
            }
            else{
                System.out.println("登录失败，退出系统请重新登录...");
                ID.clear();System.exit(0);//登录失败退出系统
            }
            FileInputStream fis = new FileInputStream("students.txt");//文件字节输入流，意思指对文件数据以字节的形式进行读取操作
            ObjectInputStream ois = new ObjectInputStream(fis);//对象输入流是用来恢复之前序列化存储的对象，对象输入流可以确保每次从流中读取的对象能匹配Java虚拟机中已经存在的类
            // 根据需求使用标准机制加载类。
            while((stu=(Student)(ois.readObject()))!=null)//遍历读取从文件输入的输入流，直到为空才停止
            {
                ID.put(stu.stuNum,stu);//将读取到的学号信息存入ID这个TreeMap变量中去.
            }
            ois.close();//关闭ois的输入流
        }
        catch(IOException | ClassNotFoundException e){//报错提示
            System.out.println(e);
        }
        try{
            while(true){
                System.out.println("1.添加学生信息");
                System.out.println("2.查询学生信息");
                System.out.println("3.删除学生信息");
                System.out.println("4.修改学生学习");
                System.out.println("5.浏览全部学生信息");
                System.out.println("6.退出");
                System.out.println("欢迎使用学生管理系统，请选择操作：");//功能菜单页面
                InputStreamReader isr = new InputStreamReader(System.in);//从字节流到字符流的桥接器：它使用指定的字符集读取字节并将它们解码为字符。
                // 它使用的字符集可以通过名称指定，也可以明确指定，或者可以接受平台的默认字符集。
                // 每次调用一个InputStreamReader的read（）方法都可能导致从底层字节输入流中读取一个或多个字节。
                // 为了实现字节到字符的有效转换，可以从基础流中提取比满足当前读取操作所需的更多字节。
                //实现从字节流到字符流的转换
                BufferedReader br = new BufferedReader(isr);
                //从字符输入流中读取文本并缓冲字符，以便有效地读取字符，数组和行
                String str = br.readLine();//读取输入
                int cho = Integer.parseInt(str);//将整型数据Integer转换为基本数据类型int
                switch(cho){
                    case 1:add();break;
                    case 2:check();break;
                    case 3:delete();break;
                    case 4:change();break;
                    case 5:show();break;
                    case 6:
                        ID.clear();System.exit(0);break;
                    default: System.out.println("操作有误，请重新输入！");
                }
            }
        }catch(NumberFormatException e){
            System.out.println("输入有误!");
        }
    }

    public static void add(){ //添加学生信息
        String str1=null;
        String str2=null;
        String str3=null;
        String str4=null;
        String str5=null;
        String str6=null;//定义6个变量临时存储信息
        float ave = 0;
        try{
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            System.out.println("请输入学生姓名：");
            str1 = br.readLine();
            System.out.println("请输入学生学号：");
            str2 = br.readLine();
            System.out.println("请输入学生性别：");
            str3 = br.readLine();
            System.out.println("请输入学生语文成绩：");
            str4 = br.readLine();
            System.out.println("请输入学生数学成绩：");
            str5 = br.readLine();
            System.out.println("请输入学生英语成绩：");
            str6 = br.readLine();//按提示输入信息
        }
        catch(IOException e){
            System.out.println(e);
        }
        stu = new Student(str1,str2,str3,str4,str5,str6,ave);//利用Student构造函数
        ID.put(stu.stuNum,stu);//存储入ID的TreeMap中
        try{
            FileOutputStream fos=new FileOutputStream("students.txt");//打开student.txt建立文件输入流
            ObjectOutputStream oos=new ObjectOutputStream(fos);//转化输入流从，字符输入流中读取文本并缓冲字符，以便有效地读取字符，数组和行
            for(Iterator itor = ID.values().iterator(); itor.hasNext();){
                stu=(Student)itor.next();//向下走,往后遍历
                oos.writeObject(stu);//用writeObject方法直接写入对象。读出来的时候可以用readObject方法直接读出来。
            }
            oos.close();
        }catch(IOException e){
            System.out.println(e);
        }
    }
    public static void delete(){  //以学号作为关键字删除学生信息
        String key=null;
        try{
            InputStreamReader isr=new InputStreamReader(System.in);
            BufferedReader br=new BufferedReader(isr);//读取
            System.out.print("请输入学号：");
            key=br.readLine();//用key存储学号
        }catch(IOException e){
            System.out.println(e);
        }
        if(ID.remove(key).equals(null)){//在ID中不存在key显示不存在，若存在，则删去
            System.out.println("您所查询该学生信息不存在！");
        }
        try{
            FileOutputStream fos=new FileOutputStream("students.txt");
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            for(Iterator itor = ID.values().iterator(); itor.hasNext();){//用Iterator迭代器，先定义这个迭代器，再在迭代器中遍历
                stu=(Student)itor.next();//向后遍历
                oos.writeObject(stu);
            }
            oos.close();
        }catch(IOException e){
            System.out.println(e);
        }
    }
    public static void show(){  //显示所有学生信息
        System.out.println("学号 "+" "+" 姓名 "+" "+" 性别 "+" "+" 语文成绩 "+" "+" 数学成绩 "+" "+" 英语成绩 "+" 平均成绩 ");
        try{
            FileOutputStream fos=new FileOutputStream("students.txt");
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            for(Iterator itor = ID.values().iterator(); itor.hasNext();){
                stu=(Student)itor.next();//不停地遍历，显示没个stu指向的学生信息
                System.out.println(stu.stuNum+"   "+stu.stuName+"\t  "+stu.stugender+"\t  "+stu.stuchina+"\t  "+stu.stumath+"\t   "+stu.stueng+"\t   "+stu.ave);
            oos.writeObject(stu);
            }
            oos.close();
        }catch(IOException e){
            System.out.println(e);
        }
    }

    public static void check(){  //以学号作为关键字查询学生信息//和删除一样操作
        String key=null;
        try{
            InputStreamReader isr=new InputStreamReader(System.in);
            BufferedReader br=new BufferedReader(isr);
            System.out.print("请输入所查询的学生学号：");
            key=br.readLine();
        }catch(IOException e){
            System.out.println(e);
        }
        if(ID.containsKey(key)){//在ID中找到key相等的学号相关信息
            stu=(Student) ID.get(key);
            System.out.println("学号 "+" "+" 姓名 "+" "+" 性别 "+" "+" 语文成绩 "+" "+" 数学成绩 "+" "+" 英语成绩 "+" 平均成绩 ");
            System.out.println(stu.stuNum+"   "+stu.stuName+"\t  "+stu.stugender+"\t  "+stu.stuchina+"\t  "+stu.stumath+"\t   "+stu.stueng+"\t   "+stu.ave);        }
        else
            System.out.println("您所查询学生信息不存在！");
    }

    public static void change(){//以学号作为关键字修改学生信息
        String key=null;
        String str1=null;
        String str2=null;
        String str3=null;
        String str4=null;
        String str5=null;
        String str6=null;//定义6个变量临时存储信息
        float ch,en,ma;
        float ave = 0;
        try{
            InputStreamReader isr=new InputStreamReader(System.in);
            BufferedReader br=new BufferedReader(isr);
            System.out.print("请输入需要修改的学生学号：");
            key=br.readLine();
        }catch(IOException e){
            System.out.println(e);
        }
        if(ID.containsKey(key)){//在ID中找到key相等的学号相关信息
            stu=(Student) ID.get(key);
            System.out.println("学号 "+" "+" 姓名 "+" "+" 性别 "+" "+" 语文成绩 "+" "+" 数学成绩 "+" "+" 英语成绩 "+" 平均成绩 ");
            System.out.println(stu.stuNum+"   "+stu.stuName+"\t  "+stu.stugender+"\t  "+stu.stuchina+"\t  "+stu.stumath+"\t   "+stu.stueng+"\t   "+stu.ave);
            try {
                InputStreamReader isr = new InputStreamReader(System.in);
                BufferedReader br = new BufferedReader(isr);
                System.out.println("请重新输入学生姓名：");
                str1 = br.readLine();
                System.out.println("请重新输入学生学号：");
                str2 = br.readLine();
                System.out.println("请重新输入学生性别：");
                str3 = br.readLine();
                System.out.println("请重新输入学生语文成绩：");
                str4 = br.readLine();
                System.out.println("请重新输入学生数学成绩：");
                str5 = br.readLine();
                System.out.println("请重新输入学生英语成绩：");
                str6 = br.readLine();//按提示输入信息
                ch=Float.parseFloat(str4);
                ma=Float.parseFloat(str5);
                en=Float.parseFloat(str6);
                ave=(ch+ma+en)/3;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            stu=(Student) ID.get(key);
            stu.stuName=str1;
            stu.stuNum=str2;
            stu.stugender=str3;
            stu.stuchina=str4;
            stu.stumath=str5;
            stu.stueng=str6;
            stu.ave=ave;
            System.out.println("修改后为:");
            System.out.println("学号 "+" "+" 姓名 "+" "+" 性别 "+" "+" 语文成绩 "+" "+" 数学成绩 "+" "+" 英语成绩 "+" 平均成绩 ");
            System.out.println(stu.stuNum+"   "+stu.stuName+"\t  "+stu.stugender+"\t  "+stu.stuchina+"\t  "+stu.stumath+"\t   "+stu.stueng+"\t   "+stu.ave);

        }
        else
            System.out.println("您所查询学生信息不存在！");
    }
}



class Student implements Serializable{  //序列化学生类
    private static final long serialVersionUID = 1L;
    String stuName;  //学生姓名
    String stuNum;   //学生学号
    String stugender;//学生性别
    String stuchina;//语文成绩
    String stumath;//数学成绩
    String stueng;//英语成绩
    float ch,en,ma;
    float ave;//平均成绩

    public Student(String stuName,String stuNum,String stugender,String stuchina, String stumath, String stueng,float ave)
    { //构造函数
        this.stuName=stuName;
        this.stuNum=stuNum;
        this.stugender=stugender;
        this.stuchina=stuchina;
        this.stumath=stumath;
        this.stueng=stueng;
        ch=Float.parseFloat(stuchina);
        ma=Float.parseFloat(stumath);
        en=Float.parseFloat(stueng);
        //this.ave=(cenh+ma+)/3;
    }


}