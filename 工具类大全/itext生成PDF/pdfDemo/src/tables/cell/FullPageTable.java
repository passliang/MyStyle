/********************** 版权声明 *************************
 * 文件名: FullPageTable.java
 * 包名: tables.cell
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月4日 下午8:33:42
 * 文件版本：V1.0 
 *
 *******************************************************/
package tables.cell;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
 
public class FullPageTable {
 
    public static final String DEST = "results/tables/full_page_table.pdf";
 
    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new FullPageTable().createPdf(DEST);
        System.out.println("export Over!");
    }
 
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4, 0, 0, 0, 0);
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(10);
 
        table.setWidthPercentage(100);
        table.setSpacingBefore(0f);
        table.setSpacingAfter(0f);
 
        // first row
        PdfPCell cell = new PdfPCell(new Phrase("DateRange"));
        cell.setColspan(10);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5.0f);
        cell.setBackgroundColor(new BaseColor(140, 221, 8));
        table.addCell(cell);
 
        table.addCell("Calldate");
        table.addCell("Calltime");
        table.addCell("Source");
        table.addCell("DialedNo");
        table.addCell("Extension");
        table.addCell("Trunk");
        table.addCell("Duration");
        table.addCell("Calltype");
        table.addCell("Callcost");
        table.addCell("Site");
 
        for (int i = 0; i < 100; i++) {
            table.addCell("date" + i);
            table.addCell("time" + i);
            table.addCell("source" + i);
            table.addCell("destination" + i);
            table.addCell("extension" + i);
            table.addCell("trunk" + i);
            table.addCell("dur" + i);
            table.addCell("toc" + i);
            table.addCell("callcost" + i);
            table.addCell("Site" + i);
        }
        document.add(table);
        document.close();
    }
}
