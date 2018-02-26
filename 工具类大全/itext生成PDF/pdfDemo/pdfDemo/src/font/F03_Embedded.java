/********************** 版权声明 *************************
 * 文件名: F03_Embedded.java
 * 包名: font
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月6日 下午5:36:43
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
 
/**
 * @author Bruno Lowagie (iText Software)
 */
public class F03_Embedded {
    public static final String DEST = "results/font/f03_embedded.pdf";
    public static final String FONT = "resources/fonts/FreeSans.ttf";
 
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new F03_Embedded().createPdf(DEST);
        System.out.println("export over!");
    }
 
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        Font font = FontFactory.getFont(FONT, "Cp1250", BaseFont.EMBEDDED);
        document.add(new Paragraph("Odkud jste?", font));
        document.add(new Paragraph("Uvid\u00edme se za chvilku. M\u011bj se.", font));
        document.add(new Paragraph("Dovolte, abych se p\u0159edstavil.", font));
        document.add(new Paragraph("To je studentka.", font));
        document.add(new Paragraph("V\u0161echno v po\u0159\u00e1dku?", font));
        document.add(new Paragraph("On je in\u017een\u00fdr. Ona je l\u00e9ka\u0159.", font));
        document.add(new Paragraph("Toto je okno.", font));
        document.add(new Paragraph("Zopakujte to pros\u00edm.", font));
        document.close();
    }
}