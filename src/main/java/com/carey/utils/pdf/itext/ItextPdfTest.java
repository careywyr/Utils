package com.carey.utils.pdf.itext;

/**
 * Created by Carey on 2017/7/7.
 */
public class ItextPdfTest {

    public static void main(String[] args) {
        try {
            ItextThread t1 = new ItextThread();
            t1.setName("Thread 1");
            ItextThread t2 = new ItextThread();
            t2.setName("Thread 2");
            ItextThread t3 = new ItextThread();
            ItextThread t4 = new ItextThread();
            t3.setName("Thread 3");
            t4.setName("Thread 4");
            t1.start();
            t2.start();
            t3.start();
            t4.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
