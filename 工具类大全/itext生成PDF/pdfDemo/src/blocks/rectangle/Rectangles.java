/********************** 版权声明 *************************
 * 文件名: Rectangles.java
 * 包名: rectangle
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月4日 下午7:16:30
 * 文件版本：V1.0 
 *
 *******************************************************/
package blocks.rectangle;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
public class Rectangles {
    public static final String DEST = "results/objects/rectangles.pdf";
 
    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new Rectangles().createPdf(DEST);
        System.out.println("export Over!");
        
    }
 
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        float llx = 36;
        float lly = 700;
        float urx = 200;
        float ury = 806;
        PdfContentByte canvas = writer.getDirectContent();
        Rectangle rect1 = new Rectangle(llx, lly, urx, ury);
        rect1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        rect1.setBorder(Rectangle.BOX);
        rect1.setBorderWidth(1);
        canvas.rectangle(rect1);
        Rectangle rect2 = new Rectangle(llx + 60, lly, urx, ury - 40);
        rect2.setBackgroundColor(BaseColor.DARK_GRAY);
        rect2.setBorder(Rectangle.BOX);
        rect2.setBorderColor(BaseColor.WHITE);
        rect2.setBorderWidth(0.5f);
        canvas.rectangle(rect2);
        document.close();
    }
}