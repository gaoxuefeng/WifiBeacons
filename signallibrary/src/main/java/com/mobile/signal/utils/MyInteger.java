package com.mobile.signal.utils;

public class MyInteger {

    private static  int num=0;

    public static int parseInt(String strnum) {
        if(strnum!=null&&!strnum.isEmpty()){
            try {
                num=Integer.parseInt(strnum);
            } catch (Exception e) {
                e.printStackTrace();
                num=0;
            }
        }else {
            num=0;
        }

        return num;

    }
}
