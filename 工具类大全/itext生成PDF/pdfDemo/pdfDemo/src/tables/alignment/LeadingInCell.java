/********************** 版权声明 *************************
 * 文件名: LeadingInCell.java
 * 包名: tables.alignment
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月4日 下午8:22:17
 * 文件版本：V1.0 
 *
 *******************************************************/
package tables.alignment;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
 
public class LeadingInCell {
 
    public static final String DEST = "results/tables/leading_in_cell.pdf";
 
    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new LeadingInCell().createPdf(DEST);
        System.out.println("export Over!");
    }
 
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(1);
        PdfPCell cell = new PdfPCell();
        Paragraph p;
        p = new Paragraph(16, "paragraph 1: leading 16");
        cell.addElement(p);
        p = new Paragraph(32, "paragraph 2: leading 32");
        cell.addElement(p);
        p = new Paragraph(10, "paragraph 3: leading 10");
        cell.addElement(p);
        p = new Paragraph(18, "paragraph 4: leading 18");
        cell.addElement(p);
        p = new Paragraph(40, "paragraph 5: leading 40");
        cell.addElement(p);
        table.addCell(cell);
        document.add(table);
        document.close();
    }
}