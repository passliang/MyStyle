/********************** 版权声明 *************************
 * 文件名: Bullets.java
 * 包名: chunk
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月4日 下午6:00:59
 * 文件版本：V1.0 
 *
 *******************************************************/
package blocks.chunk;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
/**
 * @author iText
 */
public class Bullets {
 
    public static final String DEST = "results/objects/bullets.pdf";
    public static final String[] ITEMS = {
        "Insurance system", "Agent", "Agency", "Agent Enrollment", "Agent Settings",
        "Appointment", "Continuing Education", "Hierarchy", "Recruiting", "Contract",
        "Message", "Correspondence", "Licensing", "Party"
    };
 
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new Bullets().createPdf(DEST);
        System.out.println("export over!");
    }
 
 
    public void createPdf(String dest) throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        Font zapfdingbats = new Font(FontFamily.ZAPFDINGBATS, 8);
        Font font = new Font();
        Chunk bullet = new Chunk(String.valueOf((char) 108), zapfdingbats);
 
        Paragraph p = new Paragraph("Items can be split if they don't fit at the end: ", font);
        for (String item: ITEMS) {
            p.add(bullet);
            p.add(new Phrase(" " + item + " ", font));
        }
        document.add(p);
        document.add(Chunk.NEWLINE);
 
        p = new Paragraph("Items can't be split if they don't fit at the end: ", font);
        for (String item: ITEMS) {
            p.add(bullet);
            p.add(new Phrase("\u00a0" + item.replace(' ', '\u00a0') + " ", font));
        }
        document.add(p);
        document.add(Chunk.NEWLINE);
 
        BaseFont bf = BaseFont.createFont("resources/fonts/FreeSans.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font f = new Font(bf, 12);
        p = new Paragraph("Items can't be split if they don't fit at the end: ", f);
        for (String item: ITEMS) {
            p.add(new Phrase("\u2022\u00a0" + item.replace(' ', '\u00a0') + " ", f));
        }
        document.add(p);
 
        document.close();
    }
}