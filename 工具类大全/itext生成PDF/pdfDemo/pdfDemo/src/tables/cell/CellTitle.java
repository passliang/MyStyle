/********************** 版权声明 *************************
 * 文件名: CellTitle.java
 * 包名: tables.cell
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月6日 上午8:37:36
 * 文件版本：V1.0 
 *
 *******************************************************/
package tables.cell;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
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
public class CellTitle {
 
    public static final String DEST = "results/tables/cell_title.pdf";
 
    class Title implements PdfPCellEvent {
        protected String title;
 
        public Title(String title) {
            this.title = title;
        }
 
        public void cellLayout(PdfPCell cell, Rectangle position,
            PdfContentByte[] canvases) {
            Chunk c = new Chunk(title);
            c.setBackground(BaseColor.LIGHT_GRAY);
            PdfContentByte canvas = canvases[PdfPTable.TEXTCANVAS];
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, 
                new Phrase(c), position.getLeft(5), position.getTop(5), 0);
        }
    }
 
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new CellTitle().createPdf(DEST);
        System.out.println("export over!");
    }
 
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(1);
        PdfPCell cell = getCell("The title of this cell is title 1", "title 1");
        table.addCell(cell);
        cell = getCell("The title of this cell is title 2", "title 2");
        table.addCell(cell);
        cell = getCell("The title of this cell is title 3", "title 3");
        table.addCell(cell);
        document.add(table);
        document.close();
    }
 
    public PdfPCell getCell(String content, String title) {
        PdfPCell cell = new PdfPCell(new Phrase(content));
        cell.setCellEvent(new Title(title));
        cell.setPadding(5);
        return cell;
    }
 
}
