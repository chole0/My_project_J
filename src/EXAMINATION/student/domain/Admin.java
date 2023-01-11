package EXAMINATION.student.domain;

import EXAMINATION.student.utils.ProjectUtils;

import java.io.Serializable;
import java.util.Date;

public class Admin implements Serializable{
	private final static long serialVersionUID = -4331796945908894834l;
    private String username;
    private String password;
    private String tel;
    private Date createDate;
    private String flag;
    public Admin(String username, String password, String tel,Date createDate,String flag) {
        this.username = username;
        this.password = password;
        this.tel = tel;
        this.createDate = createDate;
        this.flag = flag;
    }

    public Admin() {
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", tel='" + tel + '\'' +
                ", createDate=" + ProjectUtils.formateDate(createDate) +
                ", flag='" + flag + '\'' +
                '}';
    }
}
