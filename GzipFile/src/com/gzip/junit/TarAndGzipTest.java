/**
 * 
 */
package com.gzip.junit;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.gzip.util.GzipUtil;
import com.gzip.util.TarUtil;

/**
 * @description 
 * @author 吴晔冉
 *
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
