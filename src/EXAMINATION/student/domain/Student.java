package EXAMINATION.student.domain;

import java.io.Serializable;
import java.util.Date;

import EXAMINATION.student.utils.ProjectUtils;

public class Student implements Serializable{
	public final static long serialVersionUID = 4978589705877948505l;
	private String id;
    private String name;
    private String pswd;
    private int age;
    private String tel;
    private Date createDate;
    private String flag ="0";
    
    public Student() {
    }

    public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Student( String pswd,  String tel,int age) {
		
		
		this.pswd = pswd;
		this.age = age;
		this.tel = tel;
	}

	public Student(String id,String name, String pswd, int age, String tel,String flag,Date createDate) {
    	this.id=id;
        this.name = name;
        this.pswd = pswd;
        this.age = age;
        this.tel = tel;
        this.createDate = createDate;
        this.flag=flag;
//        this.flag=flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
    public String toString() {
        return "Student{" +
        		"id='" + id + '\'' +
                "name='" + name + '\'' +
                ", pswd='" + ProjectUtils.securityString(pswd) + '\'' +
                ", age=" + age +
                ", tel='" + tel + '\'' +
                "flag='" + flag + '\'' +
                ", createDate=" + ProjectUtils.formateDate(createDate) +
                '}';
    }
}
