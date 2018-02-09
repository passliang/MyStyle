/********************** 版权声明 *************************
 * 文件名: DottedLineCell2.java
 * 包名: tables.cell
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月6日 上午8:24:57
 * 文件版本：V1.0 
 *
 *******************************************************/
package tables.cell;

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
 
 
public class DottedLineCell2 {
 
    public static final String DEST = "results/tables/dotted_line_cell2.pdf";
 
    class DottedCell implements PdfPCellEvent {
        private int border = 0;
        public DottedCell(int border) {
            this.border = border;
        }
        public void cellLayout(PdfPCell cell, Rectangle position,
            PdfContentByte[] canvases) {
            PdfContentByte canvas = canvases[PdfPTable.LINECANVAS];
            canvas.saveState();
            canvas.setLineDash(0, 4, 2);
            if ((border & PdfPCell.TOP) == PdfPCell.TOP) {
                canvas.moveTo(position.getRight(), position.getTop());
                canvas.lineTo(position.getLeft(), position.getTop());
            }
            if ((border & PdfPCell.BOTTOM) == PdfPCell.BOTTOM) {
                canvas.moveTo(position.getRight(), position.getBottom());
                canvas.lineTo(position.getLeft(), position.getBottom());
            }
            if ((border & PdfPCell.RIGHT) == PdfPCell.RIGHT) {
                canvas.moveTo(position.getRight(), position.getTop());
                canvas.lineTo(position.getRight(), position.getBottom());
            }
            if ((border & PdfPCell.LEFT) == PdfPCell.LEFT) {
                canvas.moveTo(position.getLeft(), position.getTop());
                canvas.lineTo(position.getLeft(), position.getBottom());
            }
            canvas.stroke();
            canvas.restoreState();
        }
    }
 
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new DottedLineCell2().createPdf(DEST);
        System.out.println("export over!");
    }
 
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
 
        PdfPTable table;
        PdfPCell cell;
 
        table = new PdfPTable(4);
        table.setSpacingAfter(30);
        cell = new PdfPCell(new Phrase("left border"));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setCellEvent(new DottedCell(PdfPCell.LEFT));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("right border"));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setCellEvent(new DottedCell(PdfPCell.RIGHT));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("top border"));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setCellEvent(new DottedCell(PdfPCell.TOP));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("bottom border"));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setCellEvent(new DottedCell(PdfPCell.BOTTOM));
        table.addCell(cell);
        document.add(table);
 
        table = new PdfPTable(4);
        table.setSpacingAfter(30);
        cell = new PdfPCell(new Phrase("left and top border"));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setCellEvent(new DottedCell(PdfPCell.LEFT | PdfPCell.TOP));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("right and bottom border"));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setCellEvent(new DottedCell(PdfPCell.RIGHT | PdfPCell.BOTTOM));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("no border"));
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("full border"));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setCellEvent(new DottedCell(PdfPCell.BOX));
        table.addCell(cell);
        document.add(table);
        document.close();
    }
}
