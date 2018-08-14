/********************** 版权声明 *************************
 * 文件名: TickboxCharacter.java
 * 包名: font
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月8日 上午8:13:23
 * 文件版本：V1.0 
 *
 *******************************************************/
package font;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
/**
 *
 * @author Bruno Lowagie (iText Software)
 */
public class TickboxCharacter {
    public static final String DEST = "results/font/tickbox_character.pdf";
 
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new TickboxCharacter().createPdf(DEST);
        System.out.println("export over!");
    }
 
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        Paragraph p = new Paragraph("This is a tick box character: ");
        Font zapfdingbats = new Font(Font.FontFamily.ZAPFDINGBATS, 14);
        Chunk chunk = new Chunk("o", zapfdingbats);
        p.add(chunk);
        document.add(p);
        document.close();
    }
}