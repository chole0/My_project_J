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

import EXAMINATION.student.domain.Admin;
import EXAMINATION.student.utils.ProjectUtils;

/**
 * 鎸囧畾浣跨敤ObjectOutputStream
 */
public class AdminManager {


    public AdminManager() {

    }

    /**
     * 实现用户注册的功能
     *
     * @param admin
     * @param outPath
     * @return
     */
    public boolean registerUser(Admin admin, String outPath) {
        boolean flag = false;
        File file = new File(outPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        ObjectOutputStream objectOutputStream = null;
        FileOutputStream fileOutputStream = null;

        try {
            String object_path = file.getAbsolutePath() + File.separator + ProjectUtils.createRandomFileName() + ".tmp";
            fileOutputStream = new FileOutputStream(object_path);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(admin);

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
     * 查看用户信息
     *
     * @param outPath
     * @return
     */
    public Admin viewUser(String outPath) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        Admin admin = null;
        try {
            fileInputStream = new FileInputStream(outPath);
            objectInputStream = new ObjectInputStream(fileInputStream);
            admin = (Admin) objectInputStream.readObject();
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
            }finally {
                if (fileInputStream!=null){
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return admin;
    }

    /**
     * 获取超级管理员的基本信息
     * @return
     */
    public Admin checkAdminUser(){
        String pro_path = "D:/student_project/admin/admin.properties";
        FileInputStream fileInputStream = null;
        Admin admin = new Admin();
        try {
            fileInputStream = new FileInputStream(pro_path);
            Properties properties = new Properties();
            if (fileInputStream!=null){
                properties.load(fileInputStream);
                String username = properties.getProperty("username");
                String pswd = properties.getProperty("password");
                String flag = properties.getProperty("flag");
               
                admin.setUsername(username);
                admin.setPassword(pswd);
                admin.setFlag(flag);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream!=null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return  admin;
    }

    /**
     * 修改用户基本信息
     * @param admin
     * @return
     */
    public boolean updateAdminPswd(Admin admin){
        boolean flag = false;
        String pro_path = "D:/student_project/admin/admin.properties";
        FileOutputStream fileOutputStream = null;
        Properties properties = new Properties();

        try {
            fileOutputStream = new FileOutputStream(pro_path);
            if (fileOutputStream!=null){
                properties.setProperty("username",admin.getUsername());
                properties.setProperty("password",admin.getPassword());

                properties.setProperty("flag",admin.getFlag());
                properties.store(fileOutputStream,"");
                flag = true;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
             if (fileOutputStream!=null){
                 try {
                     fileOutputStream.close();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }finally {
                     if (fileOutputStream!=null){
                         try {
                             fileOutputStream.close();
                         } catch (IOException e) {
                             e.printStackTrace();
                         }
                     }
                }
             }
        }
        return  flag;
    }


    public static void main(String[] args) {
        AdminManager manager = new AdminManager();
//        Admin admin = new Admin("admin", "admin", "13633382988", new Date(), "1");
//        manager.registerUser(admin, "D:/student_project/admin/");
//Admin{username='admin', password='admin', tel='13633382988', createDate=2018-08-15:09:47:56}
        Admin admin = manager.viewUser("C:\\Users\\ASUS\\IdeaProjects\\My_project_J\\src\\EXAMINATION\\student\\c8740b1a9c741fba6428acab.tmp");
        System.out.println(admin);
    }
}
