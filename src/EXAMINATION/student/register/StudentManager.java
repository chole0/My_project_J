package EXAMINATION.student.register;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

import EXAMINATION.student.domain.Student;

/**
 * @author 90431
 *
 */
/**
 * @author 90431
 *
 */
public class StudentManager {

	/**
	 * 学生注册
	 * 
	 * @param student
	 * @param outPath
	 * @return
	 */
	public boolean registerStudent(Student student, String outPath) {
		boolean flag = false;
		File file = new File(outPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		ObjectOutputStream objectOutputStream = null;
		FileOutputStream fileOutputStream = null;

		try {

			String object_path = file.getAbsolutePath() + File.separator + "stu" + student.getId() + ".tmp";
			fileOutputStream = new FileOutputStream(object_path);
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(student);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fileOutputStream != null) {
					fileOutputStream.close();
				}
				if (objectOutputStream != null) {
					objectOutputStream.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	/**
	 * 为registerStudent方法服务
	 */
	public void addStudentMessage() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请添加学员信息");
		Student stu = new Student();
		System.out.println("ID");
		String stuId = scanner.nextLine();
		System.out.println("姓名：");
		String stuName = scanner.nextLine();
		System.out.println("密码：");
		String stuPass = scanner.nextLine();
		System.out.println("手机：");
		String stuTel = scanner.nextLine();
		System.out.println("年龄：");
		int stuAge = scanner.nextInt();
//		Student stud = new Student(stuId, stuName, stuPass, stuAge, stuTel, "0", new Date());

		stu.setId(stuId);
		stu.setName(stuName);
		stu.setPswd(stuPass);
		stu.setAge(stuAge);
		stu.setTel(stuTel);
		stu.setFlag("0");
		stu.setCreateDate(new Date());

		StudentManager stumanager = new StudentManager();
		stumanager.registerStudent(stu, "D:/student_project/student/");
		System.out.println("添加成功!");
		System.out.println(stu.toString());
	}

	/**
	 * 验证学生帐号密码
	 * 
	 * @return
	 */
	public Student checkStudent(String pro_path) {
//         pro_path = "D:/student_project/student/stu.properties";
		FileInputStream fileInputStream = null;
		Student student = new Student();
		try {
			fileInputStream = new FileInputStream(pro_path);
			Properties properties = new Properties();
			if (fileInputStream != null) {
				properties.load(fileInputStream);
				String username = properties.getProperty("name");
				String pswd = properties.getProperty("pswd");
				String flag = properties.getProperty("flag");

				student.setName(username);
				student.setPswd(pswd);
				student.setFlag(flag);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return student;
	}

	/**
	 * 查看用户信息
	 *
	 * @param outPath
	 * @return
	 */
	public Student viewStudents(String outPath) {
		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;
		Student student = null;
		try {
			File file = new File(outPath);
			File[] files = file.listFiles();

			for (File file1 : files) {
				System.out.println(file1.getPath());
				fileInputStream = new FileInputStream(file1);
				objectInputStream = new ObjectInputStream(fileInputStream);
				student = (Student) objectInputStream.readObject();

				System.out.println(student);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fileInputStream != null) {
					fileInputStream.close();
				}
				if (objectInputStream != null) {
					objectInputStream.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return student;
	}

	/**
	 * @param outPath
	 * @return删除指定用户
	 */
	public void deleteStudents(String id, String outPath) {
//		D:\student_project\student
		File file = new File(outPath + "stu" + id + ".tmp");
		file.delete();
		System.out.println("碍眼学生已开除");

	}

	public boolean updateStudent(String path, String pass, String tel, int age) {
		boolean flag = false;

		FileOutputStream fileOutputStream = null;
		ObjectOutputStream objectOutputStream = null;

		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;
		Student student = null;
		try {

			fileInputStream = new FileInputStream(path);
			objectInputStream = new ObjectInputStream(fileInputStream);
			student = (Student) objectInputStream.readObject();
			student.setPswd(pass);
			student.setAge(age);
			student.setTel(tel);

			fileOutputStream = new FileOutputStream(path);
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(student);
			System.out.println(student.getTel() + "   " + student.getAge() + " " + student.getName());
			System.out.println(student.toString());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (fileOutputStream != null) {
					fileOutputStream.close();
				}
				if (objectOutputStream != null) {
					objectOutputStream.close();
				}
				if (objectInputStream != null) {
					objectInputStream.close();
				}
				if (fileInputStream != null) {
					fileInputStream.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	public Student getStudentMessage(String id) {
		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;

		String path = "D:/student_project/student/stu" + id + ".tmp";
		Student student = null;
		try {
			fileInputStream = new FileInputStream(path);
			objectInputStream = new ObjectInputStream(fileInputStream);
			student = (Student) objectInputStream.readObject();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {

				if (objectInputStream != null) {
					objectInputStream.close();
				}
				if (fileInputStream != null) {
					fileInputStream.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return student;
	}

	public Student changeStuPass(String id, String password) {
		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;

		FileOutputStream fileOutputStream = null;
		ObjectOutputStream objectOutputStream = null;

		String path = "D:/student_project/student/stu" + id + ".tmp";
		Student student = null;
		try {
			fileInputStream = new FileInputStream(path);
			objectInputStream = new ObjectInputStream(fileInputStream);
			student = (Student) objectInputStream.readObject();
			student.setPswd(password);

			fileOutputStream = new FileOutputStream(path);
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(student);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {

				if (objectInputStream != null) {
					objectInputStream.close();
				}
				if (fileInputStream != null) {
					fileInputStream.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return student;
	}

}
