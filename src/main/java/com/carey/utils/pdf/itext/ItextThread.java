package com.carey.utils.pdf.itext;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Carey on 2017/7/7.
 */
public class ItextThread extends Thread{

    public static String pdfPath = "d:/tmp/test1.pdf";

    @Override
    public void run() {
        try {
            while (true){
                ItextPdfUtil.splitPDF(new FileInputStream(pdfPath),new FileOutputStream("d:/tmp/1.pdf"),1,1);
                System.out.println(this.getName());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
