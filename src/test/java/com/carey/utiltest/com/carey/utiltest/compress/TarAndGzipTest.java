package com.carey.utiltest.com.carey.utiltest.compress;

import com.carey.utils.compress.GzipUtil;
import com.carey.utils.compress.TarUtil;
import org.junit.Test;

import java.io.File;

/**
 * Created by Carey on 2017/6/19.
 */
public class TarAndGzipTest {

    String srcPath = "E:\\documents\\test";
    String destPath = "E:\\documents\\test2";
    String EXT = ".tar";

    @Test
    public void tartest() {
        TarUtil tarUtil = new TarUtil();

        try {
            tarUtil.archive(srcPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void zipTest(){
        GzipUtil gzipUtil = new GzipUtil();
        try {
            gzipUtil.compress(new File(srcPath+EXT), true);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
