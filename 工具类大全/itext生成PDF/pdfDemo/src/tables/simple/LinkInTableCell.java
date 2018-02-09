/********************** 版权声明 *************************
 * 文件名: LinkInTableCell.java
 * 包名: tables.simple
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月4日 下午7:58:46
 * 文件版本：V1.0 
 *
 *******************************************************/
package tables.simple;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfAnnotation;
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
public class LinkInTableCell {
    public static final String DEST = "results/tables/link_in_table_cell.pdf";
 
    class LinkInCell implements PdfPCellEvent {
        protected String url;
        public LinkInCell(String url) {
            this.url = url;
        }
        public void cellLayout(PdfPCell cell, Rectangle position,
            PdfContentByte[] canvases) {
            PdfWriter writer = canvases[0].getPdfWriter();
            PdfAction action = new PdfAction(url);
            PdfAnnotation link = PdfAnnotation.createLink(writer, position, PdfAnnotation.HIGHLIGHT_INVERT, action);
            writer.addAnnotation(link);
        }
    }
 
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(1);
        // Part of the content is a link:
        Phrase phrase = new Phrase();
        phrase.add("The founders of iText are nominated for a ");
        Chunk chunk = new Chunk("European Business Award!");
        chunk.setAnchor("http://itextpdf.com/blog/european-business-award-kick-ceremony");
        phrase.add(chunk);
        table.addCell(phrase);
        // The complete cell is a link:
        PdfPCell cell = new PdfPCell(new Phrase("Help us win a European Business Award!"));
        cell.setCellEvent(new LinkInCell("http://itextpdf.com/blog/help-us-win-european-business-award"));
        table.addCell(cell);
        document.add(table);
        document.close();
    }
 
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new LinkInTableCell().createPdf(DEST);
        System.out.println("export Over!");
    }
}
