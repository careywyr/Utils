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
        if (imgStr == null)
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                {// 调整异常数据
                    if (b[i] < 0)
                        b[i] += 256;
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

    public static void main(String[] args)
    {
        String base64  = getImageStr("d:/test.pdf");
        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        Matcher m = p.matcher(base64);
        String a = m.replaceAll("");
        System.out.print(a);
    }

    public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
}
