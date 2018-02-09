/********************** 版权声明 *************************
 * 文件名: NestedTables5.java
 * 包名: tables.nested
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月4日 下午9:07:06
 * 文件版本：V1.0 
 *
 *******************************************************/
package tables.nested;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
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
public class NestedTables5 {
 
    public static final String DEST = "results/tables/nested_tables5.pdf";
 
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new NestedTables5().createPdf(DEST);
        System.out.println("export Over!");
    }
 
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        // Header part
        PdfPTable table = new PdfPTable(2);
        table.setWidths(new int[]{50, 50});
        // first cell
        PdfPTable table1 = new PdfPTable(1);
        table1.getDefaultCell().setMinimumHeight(30);
        table1.addCell("Address 1");
        table1.addCell("Address 2");
        table1.addCell("Address 3");
        table.addCell(new PdfPCell(table1));
        // second cell
        PdfPTable table2 = new PdfPTable(2);
        table2.addCell("Date");
        table2.addCell("Place");
        PdfPCell cell = new PdfPCell(new Phrase("References"));
        cell.setMinimumHeight(40);
        cell.setColspan(2);
        table2.addCell(cell);
        cell = new PdfPCell(new Phrase("destination"));
        cell.setColspan(2);
        table2.addCell(cell);
        table.addCell(new PdfPCell(table2));
        // second row
        cell = new PdfPCell();
        cell.setColspan(2);
        cell.setMinimumHeight(16);
        table.addCell(cell);
        document.add(table);
        // Body part
        table = new PdfPTable(6);
        table.setWidths(new int[]{ 1, 2, 6, 1, 2, 2 });
        table.addCell("sl no");
        table.addCell("qty");
        table.addCell("Product");
        table.addCell("units");
        table.addCell("rate");
        table.addCell("total");
        table.setHeaderRows(1);
        for (int i = 0; i < 6; ) {
            table.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT);
            table.getDefaultCell().setMinimumHeight(16);
            table.addCell(String.valueOf(++i));
            table.addCell("");
            table.addCell("");
            table.addCell("");
            table.addCell("");
            table.addCell("");
        }
        table.getDefaultCell().setFixedHeight(3);
        table.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
        table.addCell("");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        document.add(table);
        document.close();
    }
}
