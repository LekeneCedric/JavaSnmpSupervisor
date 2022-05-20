/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snmp;

/**
 *
 * @author Arthur
 */

public class NetInfo {
 public static void main(String[] args) {
    new NetInfo().say();
    }

 public void say() {
   try {
   java.net.InetAddress i = java.net.InetAddress.getLocalHost();
   System.out.println(i);                  // name and IP address
   System.out.println(i.getHostName());    // name
   System.out.println(i.getHostAddress()); // IP address only
   }
   catch(Exception e){e.printStackTrace();}
 }

}
