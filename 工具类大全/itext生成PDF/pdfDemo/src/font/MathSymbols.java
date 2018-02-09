/********************** 版权声明 *************************
 * 文件名: RupeeSymbol.java
 * 包名: font
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月8日 上午8:10:59
 * 文件版本：V1.0 
 *
 *******************************************************/
package font;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
/**
 *
 * @author Bruno Lowagie (iText Software)
 */
public class MathSymbols {
    public static final String DEST = "results/font/math_symbols.pdf";
    public static final String FONT = "resources/fonts/FreeSans.ttf";
    public static final String TEXT = "this string contains special characters like this  \u2208, \u2229, \u2211, \u222b, \u2206";
 
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new MathSymbols().createPdf(DEST);
        System.out.println("export over!");
    }
 
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font f = new Font(bf, 12);
        Paragraph p = new Paragraph(TEXT, f);
        document.add(p);
        document.close();
    }
}