/********************** 版权声明 *************************
 * 文件名: SimpleTable6.java
 * 包名: tables.simple
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月6日 上午8:42:15
 * 文件版本：V1.0 
 *
 *******************************************************/
package tables.simple;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
public class SimpleTable6 {
    public static final String DEST = "results/tables/simple_table6.pdf";
 
    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new SimpleTable6().createPdf(DEST);
        System.out.println("export over!");
    }
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(2);
        table.addCell(new Phrase("0123456789"));
        Font font = new Font(FontFamily.HELVETICA, 12f, Font.STRIKETHRU);
        table.addCell(new Phrase("0123456789", font));
        Chunk chunk1 = new Chunk("0123456789");
        chunk1.setUnderline(1.5f, -1);
        table.addCell(new Phrase(chunk1));
        Chunk chunk2 = new Chunk("0123456789");
        chunk2.setUnderline(1.5f, 3.5f);
        table.addCell(new Phrase(chunk2));
        document.add(table);
        document.close();
    }
 
}