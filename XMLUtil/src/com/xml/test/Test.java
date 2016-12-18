/**
 * 
 */
package com.xml.test;

import java.io.File;

/**
 * @description 
 * @author 吴晔冉
 *
 */
public class Test {

	public static void main(String[] args) {
//		String a = "abcg/df";
//		System.out.println(a.substring(0,a.lastIndexOf("/")));
//		System.out.println(a.substring(a.lastIndexOf("/")));
//		deleteDir(new File("e:/ziptest"));
		double a = 1.0;
		System.out.println((int)a);
	
	}
	
	 /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     *                 If a deletion fails, the method stops attempting to
     *                 delete and returns "false".
     */
    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
}
