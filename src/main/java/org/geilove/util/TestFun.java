package org.geilove.util;

/**
 * Created by mfhj-dz-001-424 on 17/1/17.
 */
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.UUID;
public class TestFun {
    public  static  void  main(String[] args){
//        String uuid = UUID.randomUUID().toString();
//        System.out.print(uuid);
//
        Enumeration allNetInterfaces =null;
        try{
            allNetInterfaces= NetworkInterface.getNetworkInterfaces();
        }catch (Exception e){

        }

        InetAddress ip = null;
        while (allNetInterfaces.hasMoreElements())
        {
            NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
            System.out.println(netInterface.getName());
            Enumeration addresses = netInterface.getInetAddresses();
            while (addresses.hasMoreElements())
            {
                ip = (InetAddress) addresses.nextElement();
                if (ip != null && ip instanceof Inet4Address)
                {
                    System.out.println("本机的IP = " + ip.getHostAddress());
                }
            }
        }
    }
}
