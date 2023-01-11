package EXAMINATION.student.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Scanner;

import EXAMINATION.student.domain.Admin;
import EXAMINATION.student.domain.Student;
import EXAMINATION.student.register.AdminManager;
import EXAMINATION.student.register.StudentManager;
import EXAMINATION.student.utils.ProjectUtils;

public class SystemManager {

	private AdminManager adminManager;

	public SystemManager() {
		adminManager = new AdminManager();
	}

	public void welcomeMsg() {
		System.out.println("【-----学员管理系统-----】");
	}

	/**
	 * 用户登录
	 *
	 * @return
	 */
	public boolean loginUser() {

		boolean flag = false;
		System.out.println("【---请输入超级管理员账号：】");
		System.out.println("请输入账号：");
		Scanner scanner = new Scanner(System.in);
		String username = scanner.nextLine();
		System.out.println("请输入密码：");
		String pswd = scanner.nextLine();
		System.out.println("请输入验证码： ");
		String  randomCode=ProjectUtils.createRandomNumber();
		String validateCode =scanner.nextLine();
		StudentManager stumanager = new StudentManager();
		Admin admin = adminManager.checkAdminUser();
		Student stuUser = new Student();
		if (username.equalsIgnoreCase(admin.getUsername()) && pswd.equalsIgnoreCase(admin.getPassword())
				&& admin.getFlag().equalsIgnoreCase("1")&&validateCode.equalsIgnoreCase(randomCode)) {
			System.out.println("恭喜您，登录系统成功。");
			while (true) {
				System.out.println("请选择操作选项：1、修改用户信息  2、添加学员信息 3、查看学员信息 4、删除指定学员信息 5、修改学员信息");
				String input_value = scanner.nextLine();
				if (input_value.equalsIgnoreCase("1")) {
					System.out.println("进入用户修改界面：");
					System.out.println("请输入新的密码：");
					String new_pswd = scanner.nextLine();
					admin.setPassword(new_pswd);
					boolean flag_update = adminManager.updateAdminPswd(admin);
					if (flag_update) {
						System.out.println("恭喜，修改密码成功！！");
					}
				} else if (input_value.equalsIgnoreCase("2")) {
					stumanager.addStudentMessage();
					
				} else if (input_value.equalsIgnoreCase("3")) {
					System.out.println("查看所有学生： ");
					
					stumanager.viewStudents("D:/student_project/student/");
//            	System.out.println(student);
				} else if (input_value.equalsIgnoreCase("4")) {
					System.out.println("输入删除的学生ID： ");
					String id = scanner.nextLine();
					
					stumanager.deleteStudents(id, "D:/student_project/student/");
					
				} else if (input_value.equalsIgnoreCase("5")) {
					System.out.println("输入修改学生的ID");
					String id = scanner.nextLine();
					String path = "D:/student_project/student/stu" + id + ".tmp";
					System.out.println("依次输入密码、手机号/年龄");
					String pass = scanner.nextLine();
					String tel = scanner.nextLine();
					int age = scanner.nextInt();

					StudentManager sm = new StudentManager();
					sm.updateStudent(path, pass, tel, age);

				}
				System.out.println("输入任意键继续，0退出");
				String key =scanner.nextLine();
					if(key.equals("0")) {
						break;
				}
			}
		}
		else if(stumanager.getStudentMessage(username).getPswd().equals(pswd)&&validateCode.equalsIgnoreCase(randomCode)) {
			System.out.println("hello student ~");
			while(true) {
			System.out.println("输入1查看自己信息，输入2更改自己信息，输入0退出");
			String num =scanner.nextLine();
			if(num.equalsIgnoreCase("1")) {
				String msg=stumanager.getStudentMessage(username).toString();
				System.out.println(msg);
			}if(num.equalsIgnoreCase("2")) {
				System.out.println("输入要改的密码 ");
				String newPass = scanner.nextLine();
				stumanager.changeStuPass(username, newPass);
				
				System.out.println("修改成功请重新登录");
				break;
			}
			System.out.println("输入任意键继续，0退出");
			String key =scanner.nextLine();
				if(key.equals("0")) {
					break;
			}
			}
		}else  {
			System.out.println("wrong password/ID");
		}
		// System.out.println("--->>" + username + "-->>" + pswd);
		return flag;
	}

	public static void main(String[] args) {
		SystemManager manager = new SystemManager();
		manager.welcomeMsg();
		manager.loginUser();
	}
}
