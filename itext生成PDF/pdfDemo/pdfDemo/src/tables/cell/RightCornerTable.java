/********************** 版权声明 *************************
 * 文件名: RightCornerTable.java
 * 包名: tables.cell
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月4日 下午8:35:52
 * 文件版本：V1.0 
 *
 *******************************************************/
package tables.cell;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
public class RightCornerTable {
    public static final String DEST = "results/tables/right_corner_table.pdf";
 
    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new RightCornerTable().createPdf(DEST);
        System.out.println("export Over!");
    }
 
    public void createPdf(String dest) throws IOException, DocumentException {
        Rectangle pagesize = new Rectangle(300, 300);
        Document document = new Document(pagesize, 0, 0, 0, 0);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(1);
        table.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.setWidthPercentage(30);
        Font white = new Font();
        white.setColor(BaseColor.WHITE);
        PdfPCell cell = new PdfPCell(new Phrase(" Date" , white));
        cell.setBackgroundColor(BaseColor.BLACK);
        cell.setBorderColor(BaseColor.GRAY);
        cell.setBorderWidth(2f);
        table.addCell(cell);
        PdfPCell cellTwo = new PdfPCell(new Phrase("10/01/2015"));
        cellTwo.setBorderWidth(2f);
        table.addCell(cellTwo);
        document.add(table);
        document.newPage();
        table.setTotalWidth(90);
        PdfContentByte canvas = writer.getDirectContent();
        table.writeSelectedRows(0, -1, document.right() - 90, document.top(), canvas);
        document.close();
    }
 
}
