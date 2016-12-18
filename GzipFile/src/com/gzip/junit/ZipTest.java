/**
 * 
 */
package com.gzip.junit;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.zip.util.ZipUtils;

/**
 * @description 
 * @author 吴晔冉
 *
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
