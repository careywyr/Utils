package com.carey.utils.image;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Carey on 2017/8/28.
 */
public class Base64Util
{

    private static final Pattern NOBLANK = Pattern.compile("\\s*|\t|\r|\n");

    /**
     * 将文件转化为字节数组字符串，并对其进行Base64编码处理
     * @param imgFile
     * @return
     */
    public static String getImageStr(String imgFile) {
        InputStream in = null;
        byte[] data = null;
        // 读取文件字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 返回Base64编码过的字节数组字符串
        return encoder.encode(data);
    }

    /**
     * 根据字节数组字符串进行Base64解码并生成文件
     * @param imgStr
     * @param savedImagePath
     * @return
     */
    public static boolean generateImage(String imgStr, String savedImagePath) {
        // 文件字节数组字符串数据为空
        if (imgStr == null){
            return false;}
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                {// 调整异常数据
                    if (b[i] < 0){
                        b[i] += 256;
                    }
                }
            }
            // 生成文件
            // String sangImageStr = "D:/My Documents/ip.jpg" ;  // 要生成文件的路径.
            OutputStream out = new FileOutputStream(savedImagePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 加密
    public static String getBase64(String str) {
        byte[] b = null;
        String s = null;
        try {
            b = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (b != null) {
            s = new BASE64Encoder().encode(b);
        }
        return s;
    }

    // 解密
    public static String getFromBase64(String s) {
        byte[] b = null;
        String result = null;
        if (s != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(s);
                result = new String(b, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args)
    {
        String base64  = getImageStr("d:/test.pdf");
        Matcher matcher = NOBLANK.matcher(base64);
        String result = matcher.replaceAll("");
        System.out.print(result);
    }

    public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Matcher matcher = NOBLANK.matcher(str);
            dest = matcher.replaceAll("");
        }
        return dest;
    }
}
