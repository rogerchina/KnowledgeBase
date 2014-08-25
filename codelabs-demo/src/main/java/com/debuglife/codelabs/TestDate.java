package com.debuglife.codelabs;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class TestDate {
    
    public static void main(String[] args){
        getOneHoursAgoTime();
    }

    public static String getOneHoursAgoTime() {
        String oneHoursAgoTime = "";
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR_OF_DAY, -1);
        //cal.set(Calendar.HOUR, -2); // 把时间设置为当前时间-1小时，同理，也可以设置其他时间
        //cal.set(Calendar.MONTH, Calendar.MONTH - 1); // 当前月前一月
        oneHoursAgoTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime());// 获取到完整的时间
        System.out.println(oneHoursAgoTime);
        
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return oneHoursAgoTime;
    }
}
