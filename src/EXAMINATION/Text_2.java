package EXAMINATION;

public class Text_2 {
    public static void main(String []args){
        int count = 0;
        for (int i = 10; i<100; i++){
            boolean b = true;
            for(int j = 2; j<i; j++){
                if(i%j==0){
                    b=false;
                    break;
                }
            }
            if(b){
                count++;
                System.out.printf(i+" ");
                if(count%5==0){
                    System.out.println();
                }
            }
        }
    }
}
