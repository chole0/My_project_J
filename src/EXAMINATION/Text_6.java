package EXAMINATION;
import java.io.*;
public class Text_6 {
    public static void main(String args[]) {
            File file = new File("D://aa/a.txt");
            File targetFile = new File("D://aa/b.txt");
            try {
                FileReader in = new FileReader(file);
                BufferedReader inother = new BufferedReader(in);
                FileWriter dotfile = new FileWriter(targetFile);
                BufferedWriter out = new BufferedWriter(dotfile);
                String s ;
                int i = 0;
                s = inother.readLine();
                while (s != null) {
                    i++;
                    out.write(i + "." + s+'\n');
                    s = inother.readLine();
                }
                in.close();
                inother.close();
                out.flush();
                out.close();
                dotfile.close();
            } catch (IOException e) {
                System.out.printf("Error");
            }
    }
}
