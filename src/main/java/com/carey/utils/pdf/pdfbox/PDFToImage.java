package com.carey.utils.pdf.pdfbox;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Date;


/**
 * PDF转图片
 * Created by Carey on 2017/6/20.
 */
public class PDFToImage {

    public static void main( String[] args )
    {
        try {
//            test();
            getPageImg();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void getPageImg() throws Exception
    {
        String filePath = "D:/EpolicyGetPDFFileServletPort.pdf";
        File file = new File(filePath);
        PDDocument document = PDDocument.load(file);
        PDDocumentCatalog cata = document.getDocumentCatalog();
        PDPage page = cata.getPages().get(35);
        PDResources res = page.getResources();
        PDImageXObject image = (PDImageXObject)res.getXObject(COSName.IMAGE);
        InputStream is = image.createInputStream();
        File  target=new File("d:/outimage.jpg");
        OutputStream out = new BufferedOutputStream(new FileOutputStream(target));
        try
        {
            byte[]b=new byte[1024];
            while(is.read(b)!=-1){
                out.write(b);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            is.close();
            out.close();
        }
    }


    public  static void test() throws Exception{
        System.out.println(new Date());
        String filePath = "D:/EpolicyGetPDFFileServletPort.pdf";
        File file = new File(filePath);
        PDDocument document = PDDocument.load(file);
        PDFRenderer renderer = new PDFRenderer(document);
        for (int i = 35; i < 43; i++)
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
