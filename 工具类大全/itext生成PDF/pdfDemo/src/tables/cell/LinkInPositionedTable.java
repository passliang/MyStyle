/********************** 版权声明 *************************
 * 文件名: LinkInPositionedTable.java
 * 包名: tables.cell
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月4日 下午8:55:07
 * 文件版本：V1.0 
 *
 *******************************************************/
package tables.cell;

import com.itextpdf.text.Anchor;
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
 * @author iText
 */
public class LinkInPositionedTable {
 
    public static final String DEST = "results/tables/link_in_positioned_table.pdf";
 
    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new LinkInPositionedTable().createPdf(DEST);
        System.out.println("export Over!");
    }
 
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(1);
        table.setTotalWidth(500);
        PdfPCell cell = new PdfPCell();
        Paragraph p = new Paragraph();  
        Anchor anchor = new Anchor("link to top of next page");
        anchor.setReference("#top");
        p.add(anchor);
        cell.addElement(p);
        table.addCell(cell);
        table.writeSelectedRows(0, -1, 36, 700, writer.getDirectContent());
        document.newPage();
        Anchor target = new Anchor("top");
        target.setName("top");
        document.add(target);
        document.close();
    }
}