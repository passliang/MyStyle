/********************** 版权声明 *************************
 * 文件名: BackgroundImage.java
 * 包名: images
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月8日 上午8:24:24
 * 文件版本：V1.0 
 *
 *******************************************************/
package images;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
 
public class BackgroundImage {
    public static final String IMAGE = "resources/images/berlin2013.jpg";
    public static final String DEST = "results/images/background_image.pdf";
 
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new BackgroundImage().createPdf(DEST);
        System.out.println("export over!");
    }
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        document.add(new Paragraph("Berlin!"));
        PdfContentByte canvas = writer.getDirectContentUnder();
        Image image = Image.getInstance(IMAGE);
        image.scaleAbsolute(PageSize.A4.rotate());
        image.setAbsolutePosition(0, 0);
        canvas.addImage(image);
        document.close();
    }
}