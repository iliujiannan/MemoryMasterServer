package com.zym.memorymasterserver.util;

public class GetCheckCode {
    //获取随机串验证码
    public String getCheckCode(int len) {
        String src="123456789";
        String random="";
        for(int i=0;i<6;i++)
        {
            int temp=(int)((Math.random()*10000)%len);
            random=random + src.charAt(temp);
        }
        return random;
    }
}