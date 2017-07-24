package com.carey.utils.pdf.pdfbox;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;

/**
 * PDF转图片
 * Created by Carey on 2017/6/20.
 */
public class PDFToImage {

    public static void main( String[] args )
    {
        try {
            test();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public  static void test() throws Exception{
        System.out.println(new Date());
        String filePath = "D:/test.pdf";
        File file = new File(filePath);
        PDDocument document = PDDocument.load(file);
        PDFRenderer renderer = new PDFRenderer(document);
        for (int i = 0; i < 10; i++)
        {

            BufferedImage image = renderer.renderImage(i);
            String name = String.valueOf(i);
            String fileName = "d:/"+name+".jpg";
            ImageIO.write(image, "JPEG", new File(fileName));
        }
        System.out.println(new Date());
        System.out.println("success");

    }
}
