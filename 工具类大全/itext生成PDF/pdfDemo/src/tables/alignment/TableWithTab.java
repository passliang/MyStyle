/********************** 版权声明 *************************
 * 文件名: TableWithTab.java
 * 包名: tables.alignment
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月4日 下午8:26:10
 * 文件版本：V1.0 
 *
 *******************************************************/
package tables.alignment;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
/**
 * @author iText
 */
public class TableWithTab {
    public static final String DEST = "results/tables/table_with_tab.pdf";
 
    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new TableWithTab().createPdf(DEST);
        System.out.println("export Over!");
    }
 
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        Chunk glue = new Chunk(new VerticalPositionMark());
        PdfPTable table = new PdfPTable(1);
        Phrase p = new Phrase();
        p.add("Left");
        p.add(glue);
        p.add("Right");
        table.addCell(p);
        document.add(table);
        document.close();
    }
 
}
