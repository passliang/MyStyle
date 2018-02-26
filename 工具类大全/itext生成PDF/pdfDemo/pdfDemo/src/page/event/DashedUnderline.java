/********************** 版权声明 *************************
 * 文件名: DashedUnderline.java
 * 包名: page.event
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月6日 上午8:50:25
 * 文件版本：V1.0 
 *
 *******************************************************/
package page.event;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
/**
 *
 * @author iText
 */
public class DashedUnderline {
 
    public static final String DEST = "results/events/dashed_underline.pdf";
 
    public class DashedLine extends PdfPageEventHelper {
 
        @Override
        public void onGenericTag(PdfWriter writer, Document document, Rectangle rect, String text) {
            PdfContentByte canvas = writer.getDirectContent();
            canvas.saveState();
            canvas.setLineDash(3, 3);
            canvas.moveTo(rect.getLeft(), rect.getBottom() - 3);
            canvas.lineTo(rect.getRight(), rect.getBottom() - 3);
            canvas.stroke();
            canvas.restoreState();
        }
    }
 
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new DashedUnderline().createPdf(DEST);
        System.out.println("export over!");
    }
 
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        writer.setPageEvent(new DashedLine());
        document.open();
        document.add(new Paragraph("This text is not underlined"));
        Chunk chunk1 = new Chunk("This text is underlined with a solid line");
        chunk1.setUnderline(1, -3);
        document.add(new Paragraph(chunk1));
        Chunk chunk2 = new Chunk("This text is underlined with a dashed line");
        chunk2.setGenericTag("");
        document.add(new Paragraph(chunk2));
        document.close();
    }
}
