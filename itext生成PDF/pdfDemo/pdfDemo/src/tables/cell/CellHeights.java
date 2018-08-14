/********************** 版权声明 *************************
 * 文件名: CellHeights.java
 * 包名: tables.cell
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月4日 下午8:39:06
 * 文件版本：V1.0 
 *
 *******************************************************/
package tables.cell;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
public class CellHeights {
    /** The resulting PDF file. */
    public static final String DEST = "results/tables/cell_heights.pdf";
 
    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new CellHeights().createPdf(DEST);
        System.out.println("export Over!");
    }
 
    public void createPdf(String dest) throws DocumentException, IOException {
    	// step 1
        Document document = new Document(PageSize.A5.rotate());
        // step 2
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        // step 3
        document.open();
        // step 4
        PdfPTable table = new PdfPTable(2);
        // a long phrase
        Phrase p = new Phrase(
            "Dr. iText or: How I Learned to Stop Worrying and Love PDF.");
        PdfPCell cell = new PdfPCell(p);
        // the prhase is wrapped
        table.addCell("wrap");
        cell.setNoWrap(false);
        table.addCell(cell);
        // the phrase isn't wrapped
        table.addCell("no wrap");
        cell.setNoWrap(true);
        table.addCell(cell);
        // a long phrase with newlines
        p = new Phrase(
            "Dr. iText or:\nHow I Learned to Stop Worrying\nand Love PDF.");
        cell = new PdfPCell(p);
        // the phrase fits the fixed height
        table.addCell("fixed height (more than sufficient)");
        cell.setFixedHeight(72f);
        table.addCell(cell);
        // the phrase doesn't fit the fixed height
        table.addCell("fixed height (not sufficient)");
        cell.setFixedHeight(36f);
        table.addCell(cell);
        // The minimum height is exceeded
        table.addCell("minimum height");
        cell = new PdfPCell(new Phrase("Dr. iText"));
        cell.setMinimumHeight(36f);
        table.addCell(cell);
        // The last row is extended
        table.setExtendLastRow(true);
        table.addCell("extend last row");
        table.addCell(cell);
        document.add(table);
        // step 5
        document.close();
    }
}