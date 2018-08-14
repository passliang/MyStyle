/********************** 版权声明 *************************
 * 文件名: CzechExample.java
 * 包名: font
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月6日 下午5:32:12
 * 文件版本：V1.0 
 *
 *******************************************************/
package font;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
public class CzechExample {
 
    public static final String DEST = "results/font/czech.pdf";
    public static final String FONT = "resources/fonts/FreeSans.ttf";
 
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new CzechExample().createPdf(DEST);
        System.out.println("export over!");
    }
 
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        Font f1 = FontFactory.getFont(FONT, "Cp1250", true);
        Paragraph p1 = new Paragraph("Testing of letters \u010c,\u0106,\u0160,\u017d,\u0110", f1);
        document.add(p1);
        Font f2 = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, true);
        Paragraph p2 = new Paragraph("Testing of letters \u010c,\u0106,\u0160,\u017d,\u0110", f2);
        document.add(p2);
        document.close();
    }
}