/********************** 版权声明 *************************
 * 文件名: BorderForParagraph2.java
 * 包名: page.event
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月6日 上午8:54:41
 * 文件版本：V1.0 
 *
 *******************************************************/
package page.event;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class BorderForParagraph2 {
    public static final String DEST = "results/events/paragraph_with_border2.pdf";
 
    class ParagraphBorder extends PdfPageEventHelper {
        public boolean active = false;
        public void setActive(boolean active) {
            this.active = active;
        }
 
        public float offset = 5;
        public float startPosition;
 
        @Override
        public void onStartPage(PdfWriter writer, Document document) {
            startPosition = document.top();
        }
 
        @Override
        public void onParagraph(PdfWriter writer, Document document, float paragraphPosition) {
            this.startPosition = paragraphPosition;
        }
 
        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            if (active) {
                PdfContentByte cb = writer.getDirectContentUnder();
                cb.rectangle(document.left(), document.bottom() - offset,
                    document.right() - document.left(), startPosition - document.bottom());
                cb.stroke();
            }
        }
 
        @Override
        public void onParagraphEnd(PdfWriter writer, Document document, float paragraphPosition) {
            if (active) {
                PdfContentByte cb = writer.getDirectContentUnder();
                cb.rectangle(document.left(), paragraphPosition - offset,
                    document.right() - document.left(), startPosition - paragraphPosition);
                cb.stroke();
            }
        }
    }
 
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new BorderForParagraph2().createPdf(DEST);
        System.out.println("export over!");
    }
 
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        ParagraphBorder border = new ParagraphBorder();
        writer.setPageEvent(border);
        document.open();
        document.add(new Paragraph("Hello,"));
        document.add(new Paragraph("In this document, we'll add several paragraphs that will trigger page events. As long as the event isn't activated, nothing special happens, but let's make the event active and see what happens:"));
        border.setActive(true);
        document.add(new Paragraph("This paragraph now has a border. Isn't that fantastic? By changing the event, we can even provide a background color, change the line width of the border and many other things. Now let's deactivate the event."));
        border.setActive(false);
        document.add(new Paragraph("This paragraph no longer has a border."));
        document.add(new Paragraph("Let's repeat:"));
        for (int i = 0; i < 10; i++) {
            border.setActive(true);
            document.add(new Paragraph("This paragraph now has a border. Isn't that fantastic? By changing the event, we can even provide a background color, change the line width of the border and many other things. Now let's deactivate the event."));
            border.setActive(false);
            document.add(new Paragraph("This paragraph no longer has a border."));
        }
        document.close();
    }
}
