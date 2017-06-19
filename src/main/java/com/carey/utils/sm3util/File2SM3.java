package com.carey.utils.sm3util;

import org.bouncycastle.util.encoders.Hex;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;

/**
 * Created by Carey on 2017/6/19.
 */
public class File2SM3 {

    public static String genrateSM3KeyByFile(File file) {
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
            return null;
        }
        try {
            MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            BigInteger bi = new BigInteger(1, md5.digest());
            byte[] bstr = bi.toString(16).getBytes();
            byte[] md = new byte[32];
            SM3Digest sm3 = new SM3Digest();
            sm3.update(bstr, 0, bstr.length);
            sm3.doFinal(md, 0);
            return new String(Hex.encode(md));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
