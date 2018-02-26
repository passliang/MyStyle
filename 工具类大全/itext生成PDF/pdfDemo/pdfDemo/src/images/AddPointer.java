/********************** 版权声明 *************************
 * 文件名: AddPointer.java
 * 包名: images
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月8日 上午8:28:08
 * 文件版本：V1.0 
 *
 *******************************************************/
package images;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
public class AddPointer {
 
    public static final String IMG = "resources/images/map_cic.png";
    public static final String DEST = "results/images/map_with_pointer.pdf";
 
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new AddPointer().createPdf(DEST);
        System.out.println("export over!");
    }
    public void createPdf(String dest) throws IOException, DocumentException {
        Image img = Image.getInstance(IMG);
        Document document = new Document(img);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        img.setAbsolutePosition(0, 0);
        document.add(img);
        PdfContentByte canvas = writer.getDirectContent();
        canvas.setColorStroke(BaseColor.RED);
        canvas.setLineWidth(3);
        canvas.moveTo(220, 330);
        canvas.lineTo(240, 370);
        canvas.arc(200, 350, 240, 390, 0, (float) 180);
        canvas.lineTo(220, 330);
        canvas.closePathStroke();
        canvas.setColorFill(BaseColor.RED);
        canvas.circle(220, 370, 10);
        canvas.fill();
        document.close();
    }
}
