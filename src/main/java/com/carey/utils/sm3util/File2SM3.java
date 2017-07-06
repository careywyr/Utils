package com.carey.utils.sm3util;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.AccessController;
import java.security.MessageDigest;
import java.security.PrivilegedAction;

/**
 * Created by Carey on 2017/6/19.
 */
public class File2SM3 {

    public static String genrateSM3KeyByPath(String filePath) throws Exception {
        if (null == filePath || "".equals(filePath)) {
            throw new Exception("路径不能为空！");
        }
        return genrateSM3KeyByFile(new File(filePath));
    }

    public static String genrateSM3KeyByFile(File file) throws Exception {
        FileInputStream in = null;
        MappedByteBuffer byteBuffer=null;
        FileChannel fileChannel=null;
        try {
            in = new FileInputStream(file);
            fileChannel=in.getChannel();
            byteBuffer =fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            BigInteger bi = new BigInteger(1, md5.digest());
            byte[] bstr = bi.toString(16).getBytes();
            byte[] md = new byte[32];
            SM3Digest sm3 = new SM3Digest();
            sm3.update(bstr, 0, bstr.length);
            sm3.doFinal(md, 0);
            return new String(bytesToHexString(md));
        } finally {
            if(byteBuffer !=null){
                clean(byteBuffer);
            }
            if(fileChannel !=null){
                fileChannel.close();
            }
            if (null != in) {
                in.close();
            }
        }
    }

    /**释放buffer的资源
     * @param buffer
     * @throws Exception
     */
    private static void clean(final Object buffer) throws Exception {
        AccessController.doPrivileged(new PrivilegedAction<Object>() {
            @SuppressWarnings("restriction")
            public Object run() {
                try {
                    Method getCleanerMethod = buffer.getClass().getMethod("cleaner", new Class[0]);
                    getCleanerMethod.setAccessible(true);
                    sun.misc.Cleaner cleaner = (sun.misc.Cleaner) getCleanerMethod.invoke(buffer, new Object[0]);
                    if(null != cleaner){
                        cleaner.clean();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        });

    }

    private static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}
