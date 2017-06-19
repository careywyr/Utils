package com.carey.utils.compress;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by Carey on 2017/6/19.
 */
public class GzipUtil {


    public static final int BUFFER = 1024;
    public static final String EXT = ".gz";

    /**
     * 数据压缩
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] compress(byte[] data) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // 压缩
        compress(bais, baos);

        byte[] output = baos.toByteArray();

        baos.flush();
        baos.close();

        bais.close();

        return output;
    }

    /**
     * 文件压缩
     *
     * @param file
     * @throws Exception
     */
    public static void compress(File file) throws Exception {
        compress(file, true);
    }

    /**
     * 文件压缩 启动
     *
     * @param file
     * @param delete
     *            是否删除原始文件
     * @throws Exception
     */
    public static void compress(File file, boolean delete) throws Exception {
        FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream(file.getPath() + EXT);

        compress(fis, fos);

        fis.close();
        fos.flush();
        fos.close();

        if (delete) {
            file.delete();
        }
    }

    /**
     * 数据压缩
     *
     * @param is
     * @param os
     * @throws Exception
     */
    public static void compress(InputStream is, OutputStream os)
            throws Exception {

        GZIPOutputStream gos = new GZIPOutputStream(os);

        int count;
        byte data[] = new byte[BUFFER];
        while ((count = is.read(data, 0, BUFFER)) != -1) {
            gos.write(data, 0, count);
        }

        gos.finish();

        gos.flush();
        gos.close();
    }

    /**
     * 文件压缩
     *
     * @param path
     * @throws Exception
     */
    public static void compress(String path) throws Exception {
        compress(path, true);
    }

    /**
     * 文件压缩
     *
     * @param path
     * @param delete
     *            是否删除原始文件
     * @throws Exception
     */
    public static void compress(String path, boolean delete) throws Exception {
        File file = new File(path);
        compress(file, delete);
    }
}
