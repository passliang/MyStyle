/********************** 版权声明 *************************
 * 文件名: ArabicExample.java
 * 包名: font
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月6日 下午5:45:01
 * 文件版本：V1.0 
 *
 *******************************************************/
package font;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
public class ArabicExample {
 
    public static final String DEST = "results/font/arabic_rtl.pdf";
    public static final String FONT = "resources/fonts/NotoNaskhArabic-Regular.ttf";
 
    public static final String ARABIC = "\u0627\u0644\u0633\u0639\u0631 \u0627\u0644\u0627\u062c\u0645\u0627\u0644\u064a";
 
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ArabicExample().createPdf(DEST);
        System.out.println("export over!");
    }
 
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        Font f = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Phrase p = new Phrase("This is incorrect: ");
        p.add(new Chunk(ARABIC, f));
        p.add(new Chunk(": 50.00 USD"));
        document.add(p);
 
        p = new Phrase("This is correct: ");
        p.add(new Chunk(ARABIC, f));
        p.add(new Phrase(": 50.00"));
 
        ColumnText canvas = new ColumnText(writer.getDirectContent());
        canvas.setSimpleColumn(36, 750, 559, 780);
        canvas.setRunDirection(PdfWriter.RUN_DIRECTION_LTR);
        canvas.addElement(p);
        canvas.go();
 
        document.close();
    }
}
