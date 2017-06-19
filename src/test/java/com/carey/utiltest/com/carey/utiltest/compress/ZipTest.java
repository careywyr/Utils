package com.carey.utiltest.com.carey.utiltest.compress;

import com.carey.utils.compress.ZipUtils;
import org.junit.Test;

import java.io.File;

/**
 * Created by Carey on 2017/6/19.
 */
public class ZipTest {
    @Test
    public void test() {
        try {
            ZipUtils.compress(new File("e:/ziptest/ziptest2"),"e:/ziptest/ziptest2.zip");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
