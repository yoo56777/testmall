package com.testmall.Tools;

import com.testmall.properties.CustomProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CharsetTool {
    public String iso2utf8(String s){
        try {
            if (CustomProperty.osType.equals("OSS")){
                byte[] bytes = s.getBytes("ISO-8859-1");
                return new String(bytes, "UTF-8");
            }
            else
                return s;
        }
        catch (Exception e){
            pLogln(e.toString(), e.getClass().getName());
        }
        return s;
    }

    public String utf82iso(String s){
        try {
            if (CustomProperty.osType.equals("OSS")){
                byte[] bytes = s.getBytes("UTF-8");
                return new String(bytes, "ISO-8859-1");
            }
            else
                return s;
        }
        catch (Exception e){
            pLogln(e.toString(), e.getClass().getName());
        }
        return s;
    }

    public boolean isEncoding(String s, String encode){
        try {
            if (s.equals(new String(s.getBytes(), encode)))
                return true;
        }
        catch (Exception e){
            pLogln(e.toString(), e.getClass().getName());
        }
        return false;
    }

    public void pLogln(String log, String method){
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS  ");
        if (CustomProperty.osType.equals("OSS"))
            System.out.println(format.format(now) + "Method:" + method + " [" + utf82iso(log) + "]");
        else
            System.out.println(format.format(now) + "Method:" + method + " [" + log + "]");
    }

    public void pLog(String log, String method){
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS  ");
        if (CustomProperty.osType.equals("OSS"))
            System.out.print(format.format(now) + "Method:" + method + " [" + utf82iso(log) + "]");
        else
            System.out.print(format.format(now) + "Method:" + method + " [" + log + "]");
    }
}
