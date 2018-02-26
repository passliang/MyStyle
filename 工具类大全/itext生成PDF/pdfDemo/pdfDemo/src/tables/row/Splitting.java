/********************** 版权声明 *************************
 * 文件名: Splitting.java
 * 包名: tables.row
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月6日 上午8:12:29
 * 文件版本：V1.0 
 *
 *******************************************************/
package tables.row;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
/**
 *
 * @author Bruno Lowagie (iText Software)
 */
public class Splitting {
    public static final String DEST = "results/tables/splitting.pdf";
    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new Splitting().createPdf(DEST);
        System.out.println("export over!");
    }
 
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        Paragraph p = new Paragraph("Test");
        PdfPTable table = new PdfPTable(2);
        for (int i = 1; i < 6; i++) {
            table.addCell("key " + i);
            table.addCell("value " + i);
        }
        for (int i = 0; i < 40; i++) {
            document.add(p);
        }
        document.add(table);
        for (int i = 0; i < 38; i++) {
            document.add(p);
        }
        PdfPTable nesting = new PdfPTable(1);
        PdfPCell cell = new PdfPCell(table);
        cell.setBorder(PdfPCell.NO_BORDER);
        nesting.addCell(cell);
        document.add(nesting);
        document.close();
    }
}