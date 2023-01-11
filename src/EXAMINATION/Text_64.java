package EXAMINATION;
import java.util.Arrays;
import java.util.Base64;

public class Text_64 {
    public static byte[] decode(String base64Str){
        Base64.Decoder decoder = Base64.getDecoder();
        return decoder.decode(base64Str);
    }

    public static void main(String[] args) {

        System.out.println(Arrays.toString(decode("MTI=")));
    }
}

