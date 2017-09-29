package org.geilove.util;

/**
 * Created by mfhj-dz-001-424 on 17/1/17.
 */
import java.math.BigDecimal;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestFun {

    private static String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);

        Random r = new Random();
        key = key + r.nextInt();

        return key;
    }

    public  static  void  main(String[] args) {

        Date date=new   Date();//取时间
        System.out.println(date.toString());

        Calendar   calendar   =   new   GregorianCalendar();
        calendar.setTime(date);
        //calendar.add(calendar.YEAR, 1);//把日期往后增加一年.整数往后推,负数往前移动
        calendar.add(calendar.DAY_OF_MONTH, 180);//把日期往后增加一个月.整数往后推,负数往前移动
        //calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动
       // calendar.add(calendar.WEEK_OF_MONTH, 1);//把日期往后增加一个月.整数往后推,负数往前移动

        date=calendar.getTime();   //这个时间就是日期往后推一天的结果
        System.out.println(date.toString());

//        double a = 418.48d;
//        double b = 7121.88d;
//
//        String d="418.48d";
//        Double dd=Double.valueOf(d.toString());
//        BigDecimal a1 = new BigDecimal(String.valueOf(dd));
//        BigDecimal b1 = new BigDecimal(String.valueOf(b));
//        BigDecimal c = a1.add(b1);
//        System.out.println(c.doubleValue());

        //System.out.print(getOutTradeNo().length());

        //String uuid = UUID.randomUUID().toString();
        //System.out.print(uuid);
        //String uuid = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        //System.out.println(uuid);
        //System.out.println(UUID.randomUUID());
        //System.out.print(UUID.randomUUID().toString().length());
//
//        try{
//            Enumeration allNetInterfaces = NetworkInterface.getNetworkInterfaces();
//            InetAddress ip = null;
//            while (allNetInterfaces.hasMoreElements()) {
//                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
//
//                Enumeration addresses = netInterface.getInetAddresses();
//                while (addresses.hasMoreElements()) {
//                    ip = (InetAddress) addresses.nextElement();
//                    if (ip != null && ip instanceof Inet4Address) {
//
//                        if (ip.getHostAddress().equals("127.0.0.1")){
//                            continue;
//                        }
//                        System.out.println("本机的IP = " + ip.getHostAddress());
//                    }
//                }
//            }
//        }catch (Exception e){
//        }
    }

}

