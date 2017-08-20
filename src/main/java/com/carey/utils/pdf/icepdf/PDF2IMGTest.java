package com.carey.utils.pdf.icepdf;

import org.icepdf.core.exceptions.PDFException;
import org.icepdf.core.exceptions.PDFSecurityException;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.util.GraphicsRenderingHints;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

public class PDF2IMGTest {
    public static void main(String[] args) {
        String filePath = "D:/EpolicyGetPDFFileServletPort.pdf";
        Document document = new Document();

        try {
            document.setFile(filePath);
            float scale = 1.1f;// 缩放比例（大图）
            // float scale = 0.2f;// 缩放比例（小图）
            float rotation = 90f;// 旋转角度
            for (int i = 0; i < document.getNumberOfPages(); i++) {
                BufferedImage image = (BufferedImage) document.getPageImage(i,
                    GraphicsRenderingHints.SCREEN,
                    org.icepdf.core.pobjects.Page.BOUNDARY_CROPBOX,
                    rotation, scale);
                RenderedImage rendImage = image;
                try {
                    File file = new File("D:/ice/test/" + i + ".jpg");
                    // 这里png作用是：格式是jpg但有png清晰度
                    ImageIO.write(rendImage, "png", file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                image.flush();
            }
            document.dispose();
        } catch (PDFException e1) {
            e1.printStackTrace();
        } catch (PDFSecurityException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        System.out.println("======================完成============================");
    }
}