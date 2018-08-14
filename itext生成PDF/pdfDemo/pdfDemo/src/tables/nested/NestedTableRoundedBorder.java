/********************** 版权声明 *************************
 * 文件名: NestedTableRoundedBorder.java
 * 包名: tables.nested
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月4日 下午9:05:32
 * 文件版本：V1.0 
 *
 *******************************************************/
package tables.nested;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
/**
 * @author Bruno Lowagie (iText Software)
 */
public class NestedTableRoundedBorder {
    class RoundRectangle implements PdfPCellEvent {
        public void cellLayout(PdfPCell cell, Rectangle rect,
                PdfContentByte[] canvas) {
            PdfContentByte cb = canvas[PdfPTable.LINECANVAS];
            cb.roundRectangle(
                rect.getLeft() + 1.5f, rect.getBottom() + 1.5f, rect.getWidth() - 3,
                rect.getHeight() - 3, 4);
            cb.stroke();
        }
    }
 
    public static final String DEST = "results/tables/nested_table_rounded_border.pdf";
 
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new NestedTableRoundedBorder().createPdf(DEST);
        System.out.println("export Over!");
    }
 
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPCell cell;
        PdfPCellEvent roundRectangle = new RoundRectangle();
        // outer table
        PdfPTable outertable = new PdfPTable(1);
        // inner table 1
        PdfPTable innertable = new PdfPTable(5);
        innertable.setWidths(new int[]{8, 12, 1, 4, 12});
        // first row
        // column 1
        cell = new PdfPCell(new Phrase("Record Ref:"));
        cell.setBorder(Rectangle.NO_BORDER);
        innertable.addCell(cell);
        // column 2
        cell = new PdfPCell(new Phrase("GN Staff"));
        cell.setPaddingLeft(2);
        innertable.addCell(cell);
        // column 3
        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        innertable.addCell(cell);
        // column 4
        cell = new PdfPCell(new Phrase("Date: "));
        cell.setBorder(Rectangle.NO_BORDER);
        innertable.addCell(cell);
        // column 5
        cell = new PdfPCell(new Phrase("30/4/2015"));
        cell.setPaddingLeft(2);
        innertable.addCell(cell);
        // spacing
        cell = new PdfPCell();
        cell.setColspan(5);
        cell.setFixedHeight(3);
        cell.setBorder(Rectangle.NO_BORDER);
        innertable.addCell(cell);
        // second row
        // column 1
        cell = new PdfPCell(new Phrase("Hospital:"));
        cell.setBorder(Rectangle.NO_BORDER);
        innertable.addCell(cell);
        // column 2
        cell = new PdfPCell(new Phrase("Derby Royal"));
        cell.setPaddingLeft(2);
        innertable.addCell(cell);
        // column 3
        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        innertable.addCell(cell);
        // column 4
        cell = new PdfPCell(new Phrase("Ward: "));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setPaddingLeft(5);
        innertable.addCell(cell);
        // column 5
        cell = new PdfPCell(new Phrase("21"));
        cell.setPaddingLeft(2);
        innertable.addCell(cell);
        // spacing
        cell = new PdfPCell();
        cell.setColspan(5);
        cell.setFixedHeight(3);
        cell.setBorder(Rectangle.NO_BORDER);
        innertable.addCell(cell);
        // first nested table
        cell = new PdfPCell(innertable);
        cell.setCellEvent(roundRectangle);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setPadding(8);
        outertable.addCell(cell);
        // inner table 2
        innertable = new PdfPTable(4);
        innertable.setWidths(new int[]{3, 17, 1, 16});
        // first row
        // column 1
        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        innertable.addCell(cell);
        // column 2
        cell = new PdfPCell(new Phrase("Name"));
        cell.setBorder(Rectangle.NO_BORDER);
        innertable.addCell(cell);
        // column 3
        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        innertable.addCell(cell);
        // column 4
        cell = new PdfPCell(new Phrase("Signature: "));
        cell.setBorder(Rectangle.NO_BORDER);
        innertable.addCell(cell);
        // spacing
        cell = new PdfPCell();
        cell.setColspan(4);
        cell.setFixedHeight(3);
        cell.setBorder(Rectangle.NO_BORDER);
        innertable.addCell(cell);
        // subsequent rows
        for (int i = 1; i < 4; i++) {
            // column 1
            cell = new PdfPCell(new Phrase(String.format("%s:", i)));
            cell.setBorder(Rectangle.NO_BORDER);
            innertable.addCell(cell);
            // column 2
            cell = new PdfPCell();
            innertable.addCell(cell);
            // column 3
            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            innertable.addCell(cell);
            // column 4
            cell = new PdfPCell();
            innertable.addCell(cell);
            // spacing
            cell = new PdfPCell();
            cell.setColspan(4);
            cell.setFixedHeight(3);
            cell.setBorder(Rectangle.NO_BORDER);
            innertable.addCell(cell);
        }
        // second nested table
        cell = new PdfPCell(innertable);
        cell.setCellEvent(roundRectangle);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setPadding(8);
        outertable.addCell(cell);
        // add the table
        document.add(outertable);
        document.close();
    }
}