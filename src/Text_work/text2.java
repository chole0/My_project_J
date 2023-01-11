package Text_work;
public class text2 {
    public static void main(String [] args){
        int wanshu=0,i,n;
        for( i=1;i<=1000;i++){

            for( n=1;n<=i/2;n++){

                if(i%n==0){

                    wanshu=wanshu+n;
                }

            }
            if(wanshu==i){

                System.out.println(wanshu);
            }

            wanshu=0;
        }

    }
}
