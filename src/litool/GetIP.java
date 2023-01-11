package litool;

import java.net.*;
public class GetIP {
    public static void main(String[] args){
        try{
            InetAddress addr =InetAddress.getLocalHost();
            System.out.println("Local HostAddress: ");
            System.out.println(addr.getHostAddress());
            String hostnamme=addr.getHostName();
            System.out.println("\nLocal HostName: ");
            System.out.println(hostnamme);
        }catch (Exception e)
        {
            System.out.println("wrong~~~");
        }
    }
}
