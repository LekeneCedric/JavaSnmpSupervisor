package com.example.supervisionnetworkInterface;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class LANScanner {

   public static void main(String[] args) {
      try {
//         InetAddress address = InetAddress.getLocalHost();
//         showInformations(address, "Hôte local");
         
//         address = InetAddress.getByAddress(
//               new byte[]{(byte)192, (byte)168, 0, 100}
//         );
//         showInformations(address, "192.168.2.44");
//
//         address = InetAddress.getByName("localhost");
//         showInformations(address, "locahost");
//
//         address = InetAddress.getByName("127.0.0.1");
//         showInformations(address, "127.0.0.1");

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
               if(test.isReachable(500)) {
                  showInformations(test, test.toString());
//                  chaine = "Hote : ";
//                  for (int a = 0; a < 4; a++) {
////                   txt += tabAdresse[a]+".";
//                     chaine += String.valueOf(tabAdresse[a]) + ".";
//
//                  }
//                  System.out.println(chaine + " Connecte");
               }

               tabAdresse[3]++;

            }
//           tabAdresse[3] = 1;
//           tabAdresse[2]++;
         }
         
      } catch (UnknownHostException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   public static void showInformations(InetAddress address, String name){
      System.out.println("-----------------------------------------------");
      System.out.println("INFORMATIONS DE " + name);
      System.out.println("-----------------------------------------------");
      System.out.println("Nom  : " + address.getHostName());
      System.out.println("Adresse : " + address.getHostAddress());
      System.out.println("Nom canonique : " + address.getCanonicalHostName());
      //Cette méthode nous retourne un tableau de byte
      byte[] bAddress = address.getAddress();
      String ip = "";
      for(byte b : bAddress)
           ip +=(b & 0xFF) + ".";//L'instruction & 0xFF permet d'avoir la valeur non signée

      System.out.println("Adresse IP depuis tableau de byte : " + ip);
   }
}