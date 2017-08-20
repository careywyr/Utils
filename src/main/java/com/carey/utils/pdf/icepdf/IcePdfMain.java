package com.carey.utils.pdf.icepdf;

import org.icepdf.core.exceptions.PDFException;
import org.icepdf.core.exceptions.PDFSecurityException;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.pobjects.Page;
import org.icepdf.core.util.GraphicsRenderingHints;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


/**
 * Created by Carey on 2017/7/9.
 */
public class IcePdfMain
{
    public static final String FILETYPE_JPG = "jpg";
    public static final String SUFF_IMAGE = "." + FILETYPE_JPG;
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddss-SSS");

    /**
     * 将指定pdf文件的首页转换为指定路径的缩略图
     *@param filepath 原文件路径，例如d:/test.pdf
     *@param imagepath 图片生成路径，例如 d:/test-1.jpg
     *@param zoom     缩略图显示倍数，1表示不缩放，0.3则缩小到30%
     */
    public static void tranfer(String filepath, String imagepath, float zoom)
        throws PDFException, PDFSecurityException, IOException
    {
        System.out.println(sdf.format(new Date()));
        // ICEpdf document class
        Document document = null;
        float rotation = 0f;
        document = new Document();
        document.setFile(filepath);
        // maxPages = document.getPageTree().getNumberOfPages();

        BufferedImage img = (BufferedImage) document.getPageImage(0,
            GraphicsRenderingHints.SCREEN, Page.BOUNDARY_CROPBOX, rotation,
            zoom);


        Iterator iter = ImageIO.getImageWritersBySuffix(FILETYPE_JPG);
        ImageWriter writer = (ImageWriter) iter.next();
        File outFile = new File(imagepath);
        FileOutputStream out = new FileOutputStream(outFile);
        ImageOutputStream outImage = ImageIO.createImageOutputStream(out);
        writer.setOutput(outImage);
        writer.write(new IIOImage(img, null, null));
        System.out.println(sdf.format(new Date()));

    }

    public static void main(String[] args)
    {
        System.out.println(new Date());
        try
        {
//                String fileName = "d:/ice/"+"test.jpg";
//                tranfer("d:/output_34.pdf",fileName,1);
            for (int i = 1; i < 48; i++)
            {
                pageImage("d:/EpolicyGetPDFFileServletPort.pdf",i);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println(new Date());
    }

    public static void pageImage(String filepath,int pageNumber){
        try
        {
            Document document = new Document();
            document.setFile(filepath);
            Page currentPage = document.getPageTree().getPage(pageNumber);
            RenderedImage rendImage;
            int count = 0;
            List<Image> images = currentPage.getImages();
            for (Image image : images)
            {
                count++;
                if (image != null) {
                    rendImage = (BufferedImage) image;
                    System.out.println("Capture page " + pageNumber + " image " + count);
                    File file = new File("d:/ice/imageCapture_" + pageNumber + "_" + count + ".jpg");
                    ImageIO.write(rendImage, "jpg", file);
                    image.flush();
                }
            }
            // clears most resource.
            images.clear();
        }
        catch (PDFException e)
        {
            e.printStackTrace();
        }
        catch (PDFSecurityException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
