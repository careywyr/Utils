package com.carey.utils.pdfbox;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

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
        String filePath = "D:/unit 1 case learning.pdf";
        File file = new File(filePath);
        PDDocument document = PDDocument.load(file);
        PDFRenderer renderer = new PDFRenderer(document);
        BufferedImage image = renderer.renderImage(2);
        ImageIO.write(image, "JPEG", new File("D:/myimage.jpg"));
        System.out.println("success");

    }
}
