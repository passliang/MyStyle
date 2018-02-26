/********************** 版权声明 *************************
 * 文件名: IndentationInCell.java
 * 包名: tables.alignment
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月4日 下午8:25:16
 * 文件版本：V1.0 
 *
 *******************************************************/
package tables.alignment;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
public class IndentationInCell {
    public static final String DEST = "results/tables/indentation_in_cell.pdf";
 
    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new IndentationInCell().createPdf(DEST);
        System.out.println("export Over!");
    }
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(1);
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("TO:\n\n   name"));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("TO:\n\n\u00a0\u00a0\u00a0name"));
        table.addCell(cell);
        cell = new PdfPCell();
        cell.addElement(new Paragraph("TO:"));
        Paragraph p = new Paragraph("name");
        p.setIndentationLeft(10);
        cell.addElement(p);
        table.addCell(cell);
        cell = new PdfPCell();
        cell.addElement(new Paragraph("TO:"));
        p = new Paragraph("name");
        p.setAlignment(Element.ALIGN_RIGHT);
        cell.addElement(p);
        table.addCell(cell);
        document.add(table);
        document.close();
    }
 
}
