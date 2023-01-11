package Text_example;

import java.io.*;
import java.io.FileInputStream;
import java.util.Scanner;


public class ScoreList {
	public static void main(String []args) {
		int n=-1;
		byte[]a=new byte[100];
		try{
			File f = new File("ScoreList.java");
			InputStream in = new FileInputStream(f);

		}catch (Exception ex){
			System.out.println(ex);
		}
	}
}
