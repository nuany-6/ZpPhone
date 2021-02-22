package com.zposed.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class AndroidUtil {
    //android 生成
    public String DidRandom(){
        String uuid = UUID.randomUUID().toString();
        //去掉“-”符号
        String s = uuid.replaceAll("-", "");
        return s.substring(0,16);

    }
    //经度生成
    public  String randomLon(double MinLon, double MaxLon) {
        BigDecimal db = new BigDecimal(Math.random() * (MaxLon - MinLon) + MinLon);
        String lon = db.setScale(6, BigDecimal.ROUND_HALF_UP).toString();// 小数后6位

        return lon;
    }
    //纬度生成
    public  String randomLat(double MinLat, double MaxLat) {
        BigDecimal  db = new BigDecimal(Math.random() * (MaxLat - MinLat) + MinLat);
        String lat = db.setScale(6, BigDecimal.ROUND_HALF_UP).toString();

        return lat;
    }

    /*
    * mac
    *
    * */
    public  String getStringRandom(int length) {
        String val = "";
        Random random = new Random();
        //参数length，表示生成几位随机数
        for(int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if( "char".equalsIgnoreCase(charOrNum) ) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char)(random.nextInt(6) + temp);
            } else if( "num".equalsIgnoreCase(charOrNum) ) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }
    public  String getMac() {
        StringBuffer mac = new StringBuffer();
        for(int i=1; i<=6; i++) {
            String one = getStringRandom(1);
            String two = getStringRandom(1);
            mac.append(one).append(two);
            if(i != 6) mac.append(":");
        }
        System.out.println(mac.toString().toLowerCase());
        return mac.toString().toUpperCase();
    }
}
