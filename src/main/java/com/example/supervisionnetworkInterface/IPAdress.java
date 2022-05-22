package com.example.supervisionnetworkInterface;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class IPAdress {
 public void  getInterfaces (){
      try {
         Enumeration e = NetworkInterface.getNetworkInterfaces();

         while(e.hasMoreElements()) {
            NetworkInterface ni = (NetworkInterface) e.nextElement();
            System.out.println("Net interface: "+ni.getName());

            Enumeration e2 = ni.getInetAddresses();

            while (e2.hasMoreElements()){
               InetAddress ip = (InetAddress) e2.nextElement();
               System.out.println("IP address: "+ ip);
            }
         }
      }
      catch (Exception e) {
         e.printStackTrace();
      }
   }

    public void checkHosts(String subnet) throws IOException {
        int timeout=1000;
        for (int i=1;i<255;i++){
            String host=subnet + "." + i;
            if (InetAddress.getByName(host).isReachable(timeout)){
                System.out.println(host + " is reachable");
            }
        }
    }

   public static void main(String[] args) throws IOException {
    IPAdress ip = new IPAdress();
//    ip.getInterfaces();
       boolean tourne = true;
       byte[] tabAdresse = {(byte) 192,(byte) 168,0,100};
       for(int i = 0 ; i < 1 && tourne ; i++)
       {
           for(int j = 0 ; j < 10 && tourne ; j++)
           {
               InetAddress test = InetAddress.getByAddress(tabAdresse);
//               String txt = test.isReachable(1500)+" = ";
               String chaine = null;
                if(test.isReachable(1000)) {
                    chaine = "Hote : ";
                    for (int a = 0; a < 4; a++) {
//                   txt += tabAdresse[a]+".";
                        chaine += String.valueOf(tabAdresse[a]) + ".";

                    }
                    System.out.println(chaine + " Connecte");
                }

               tabAdresse[3]++;

           }
//           tabAdresse[3] = 1;
//           tabAdresse[2]++;
       }
   }
}