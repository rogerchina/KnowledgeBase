package com.debuglife.codelabs.corejava;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class TestDate {
    
    public static void main(String[] args){
//        getOneHoursAgoTime();
        getLastSixMonth();
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
    
    public static void getLastSixMonth(){
        String[] monthArr = new String[6];
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        for(int i=5; i>=0; i--){
            monthArr[i] = String.valueOf(cal.get(Calendar.MONTH) + 1);
            cal.add(Calendar.MONTH, -1);
        }
        
        for(int i=0; i<monthArr.length; i++){
            System.out.print("\t" + monthArr[i]);
        }
    }
}
