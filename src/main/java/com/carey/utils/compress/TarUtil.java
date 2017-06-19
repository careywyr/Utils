package com.carey.utils.compress;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by Carey on 2017/6/19.
 */
public class TarUtil {

    private static final String BASE_DIR = "";
    // 符号"/"用来作为目录标识判断符
    private static final String PATH = "/";
    private static final int BUFFER = 1024;
    private static final String EXT = ".tar";


    /**
     * 归档
     *
     * @param srcPath
     * @param destPath
     * @throws Exception
     */
    public static void archive(String srcPath, String destPath)
            throws Exception {
        File srcFile = new File(srcPath);
        archive(srcFile, destPath);
    }

    /**
     * 归档
     * @param srcFile
     *            源路径
     * @param
     *
     * @throws Exception
     */
    public static void archive(File srcFile, File destFile) throws Exception {
        TarArchiveOutputStream taos = new TarArchiveOutputStream(
                new FileOutputStream(destFile));
        archive(srcFile, taos, BASE_DIR);
        taos.flush();
        taos.close();
    }

    /**
     * 归档
     * @param srcFile
     * @throws Exception
     */
    public static void archive(File srcFile) throws Exception {
        String name = srcFile.getName();
        String basePath = srcFile.getParent();
        String destPath = basePath + PATH+name + EXT;
        archive(srcFile, destPath);
    }

    /**
     * 归档文件
     * @param srcFile
     * @param destPath
     * @throws Exception
     */
    public static void archive(File srcFile, String destPath) throws Exception {
        archive(srcFile, new File(destPath));
    }

    /**
     * 归档 启动方法
     * @param srcPath
     * @throws Exception
     */
    public static void archive(String srcPath) throws Exception {
        File srcFile = new File(srcPath);
        archive(srcFile);
    }

    /**
     * 归档
     * @param srcFile
     *            源路径
     * @param taos
     *            TarArchiveOutputStream
     * @param basePath
     *            归档包内相对路径
     * @throws Exception
     */
    private static void archive(File srcFile, TarArchiveOutputStream taos,
                                String basePath) throws Exception {
        if (srcFile.isDirectory()) {
            archiveDir(srcFile, taos, basePath);
        } else {
            archiveFile(srcFile, taos, basePath);
        }
    }

    /**
     * 目录归档
     * @param dir
     * @param taos
     *            TarArchiveOutputStream
     * @param basePath
     * @throws Exception
     */
    private static void archiveDir(File dir, TarArchiveOutputStream taos,
                                   String basePath) throws Exception {

        File[] files = dir.listFiles();
        if (files.length < 1) {
            TarArchiveEntry entry = new TarArchiveEntry(basePath
                    + dir.getName() + PATH);
            taos.putArchiveEntry(entry);
            taos.closeArchiveEntry();
        }
        for (File file : files) {
            // 递归归档
            archive(file, taos, basePath  + PATH);
        }
        dir.delete();
    }

    /**
     * 数据归档
     * @throws Exception
     */
    private static void archiveFile(File file, TarArchiveOutputStream taos,
                                    String dir) throws Exception {

        /**
         * 归档内文件名定义
         *
         * <pre>
         * 如果有多级目录，那么这里就需要给出包含目录的文件名
         * 如果用WinRAR打开归档包，中文名将显示为乱码
         * </pre>
         */
        TarArchiveEntry entry = new TarArchiveEntry(dir + file.getName());
        entry.setSize(file.length());
        taos.putArchiveEntry(entry);
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
                file));
        int count;
        byte data[] = new byte[BUFFER];
        while ((count = bis.read(data, 0, BUFFER)) != -1) {
            taos.write(data, 0, count);
        }
        bis.close();
        taos.closeArchiveEntry();
        file.delete();
    }

}
