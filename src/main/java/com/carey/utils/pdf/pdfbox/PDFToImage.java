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
        System.out.println(new Date());
        BufferedImage image = renderer.renderImage(2);
        System.out.println(new Date());
        ImageIO.write(image, "JPEG", new File("D:/myimage.jpg"));
        System.out.println(new Date());
        System.out.println("success");

    }
}
