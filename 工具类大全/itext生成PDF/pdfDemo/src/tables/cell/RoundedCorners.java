/********************** 版权声明 *************************
 * 文件名: RoundedCorners.java
 * 包名: tables.cell
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月6日 上午8:21:29
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
 
public class RoundedCorners {
 
    public static final String DEST = "results/tables/rounded_corners.pdf";
 
    class SpecialRoundedCell implements PdfPCellEvent {
        public void cellLayout(PdfPCell cell, Rectangle position,
            PdfContentByte[] canvases) {
            PdfContentByte canvas = canvases[PdfPTable.LINECANVAS];
            float llx = position.getLeft() + 2;
            float lly = position.getBottom() + 2;
            float urx = position.getRight() - 2;
            float ury = position.getTop() - 2;
            float r = 4;
            float b = 0.4477f;
            canvas.moveTo(llx, lly);
            canvas.lineTo(urx, lly);
            canvas.lineTo(urx, ury - r);
            canvas.curveTo(urx, ury - r * b, urx - r * b, ury, urx - r, ury);
            canvas.lineTo(llx + r, ury);
            canvas.curveTo(llx + r * b, ury, llx, ury - r * b, llx, ury - r);
            canvas.lineTo(llx, lly);
            canvas.stroke();
        }
    }
 
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new RoundedCorners().createPdf(DEST);
        System.out.println("export over!");
    }
 
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(3);
        PdfPCell cell = getCell("These cells have rounded borders at the top.");
        table.addCell(cell);
        cell = getCell("These cells aren't rounded at the bottom.");
        table.addCell(cell);
        cell = getCell("A custom cell event was used to achieve this.");
        table.addCell(cell);
        document.add(table);
        document.close();
    }
 
    public PdfPCell getCell(String content) {
        PdfPCell cell = new PdfPCell(new Phrase(content));
        cell.setCellEvent(new SpecialRoundedCell());
        cell.setPadding(5);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }
}
