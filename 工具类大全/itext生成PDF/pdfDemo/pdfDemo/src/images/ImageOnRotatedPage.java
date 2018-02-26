/********************** 版权声明 *************************
 * 文件名: ImageOnRotatedPage.java
 * 包名: images
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月8日 上午8:32:13
 * 文件版本：V1.0 
 *
 *******************************************************/
package images;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
/**
 * @author Bruno Lowagie (iText Software)
 */
public class ImageOnRotatedPage {
    public static final String IMAGE = "resources/images/cardiogram.png";
    public static final String DEST = "results/images/cardiogram.pdf";
 
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ImageOnRotatedPage().createPdf(DEST);
        System.out.println("export over!");
    }
 
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        Image img = Image.getInstance(IMAGE);
        img.scaleToFit(770, 523);
        float offsetX = (770 - img.getScaledWidth()) / 2;
        float offsetY = (523 - img.getScaledHeight()) / 2;
        img.setAbsolutePosition(36 + offsetX, 36 + offsetY);
        document.add(img);
        document.close();
    }
 
}