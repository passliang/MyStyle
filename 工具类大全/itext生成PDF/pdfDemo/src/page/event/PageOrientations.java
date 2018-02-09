/********************** 版权声明 *************************
 * 文件名: PageOrientations.java
 * 包名: page.event
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月6日 上午9:01:41
 * 文件版本：V1.0 
 *
 *******************************************************/
package page.event;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfNumber;
import com.itextpdf.text.pdf.PdfPage;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PageOrientations {
    public static final String DEST = "results/events/orientations.pdf";
 
    public class Rotate extends PdfPageEventHelper {
 
        protected PdfNumber orientation = PdfPage.PORTRAIT;
 
        public void setOrientation(PdfNumber orientation) {
            this.orientation = orientation;
        }
 
        @Override
        public void onStartPage(PdfWriter writer, Document document) {
            writer.addPageDictEntry(PdfName.ROTATE, orientation);
        }
    }
 
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new PageOrientations().createPdf(DEST);
        System.out.println("export over!");
    }
 
    public void createPdf(String filename) throws IOException, DocumentException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
        Rotate event = new Rotate();
        writer.setPageEvent(event);
        // step 3
        document.open();
        // step 4
        document.add(new Paragraph("A simple page in portrait orientation"));
        event.setOrientation(PdfPage.LANDSCAPE);
        document.newPage();
        document.add(new Paragraph("A simple page in landscape orientation"));;
        event.setOrientation(PdfPage.INVERTEDPORTRAIT);
        document.newPage();
        document.add(new Paragraph("A simple page in inverted portrait orientation"));;
        event.setOrientation(PdfPage.SEASCAPE);
        document.newPage();
        document.add(new Paragraph("A simple page in seascape orientation"));
        // step 5
        document.close();
    }
}
