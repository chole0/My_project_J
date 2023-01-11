package EXAMINATION.student.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class ProjectUtils {


    /**
     * 产生随机的文件名
     * @return
     */
    public static String createRandomFileName(){
         return UUID.randomUUID().toString().replaceAll("-","").substring(1,25);
    }

    /**
     *格式化日期，按照yyyy-MM-dd格式
     * @param date
     * @return
     */
    public static String formateDate(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        return  format.format(date);
    }

    /**
     * 实现加密的过程
     * @param msg
     * @return
     */
    public static String securityString(String msg){
    	try {
			MessageDigest digest= MessageDigest.getInstance("MD5");
			byte[] data= digest.digest(msg.getBytes());
			return new String(data);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "";
    }

    public static String createRandomNumber() {
    	String msg ="aspq212451aaopzmbgailqzpxigmdsflagas";
    	StringBuilder builder= new StringBuilder();
    	builder.append(msg.charAt(new Random().nextInt(msg.length())))
    	.append(msg.charAt(new Random().nextInt(msg.length())))
    	.append(msg.charAt(new Random().nextInt(msg.length())))
    	.append(msg.charAt(new Random().nextInt(msg.length())));
    	System.out.println(builder.toString());
    	return builder.toString();
    }
    
}
