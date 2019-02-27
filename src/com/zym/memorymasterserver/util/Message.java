package com.zym.memorymasterserver.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class Message {

    //短信验证码
    private MD5 md5=MD5.getMd5();
    private String username="yanyongjie";
    private String password=md5.getMd5("15296603340yyjqq");
    private GetCheckCode getCheckCode=new GetCheckCode();
    private final OkHttpClient client = new OkHttpClient();
    //phonenum需要发送短信的电话号
    public String mSsendMessage(String phonenum) throws IOException {
        int len=6;
        String checkcode=getCheckCode.getCheckCode(len);
        String content="【小睿科技】，您的验证码为" + checkcode + "在3分钟内有效，请勿泄露验证码给他人";
        String url = "http://www.smsbao.com/sms?u=" + username;
        url = url + "&p=" + password;
        url = url + "&m=";
        url = url + phonenum;
        url = url + "&c=" + encodeUrlString(content,"UTF-8");
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(request);
        return checkcode;
    }
    public static String encodeUrlString(String str, String charset) {
        String strret = null;
        if (str == null)
            return str;
        try {
            strret = java.net.URLEncoder.encode(str, charset);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return strret;
    }
}
