/********************** 版权声明 *************************
 * 文件名: NestedTablesAligned.java
 * 包名: tables.nested
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月4日 下午9:04:42
 * 文件版本：V1.0 
 *
 *******************************************************/
package tables.nested;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
public class NestedTablesAligned {
 
    public static final String DEST = "results/tables/nested_tables_aligned.pdf";
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new NestedTablesAligned().createPdf(DEST);
        System.out.println("export Over!");
    }
 
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        float[] columnWidths = {200f, 200f, 200f};
        PdfPTable table = new PdfPTable(columnWidths);
        table.setTotalWidth(600f);
        table.setLockedWidth(true);
        buildNestedTables(table);
        document.add(table);
        document.close();
    }
 
    private void buildNestedTables(PdfPTable outerTable) {
        PdfPTable innerTable1 = new PdfPTable(1);
        innerTable1.setTotalWidth(100f);
        innerTable1.setLockedWidth(true);
        innerTable1.setHorizontalAlignment(Element.ALIGN_LEFT);
        innerTable1.addCell("Cell 1");
        innerTable1.addCell("Cell 2");
        outerTable.addCell(innerTable1);
        PdfPTable innerTable2 = new PdfPTable(2);
        innerTable2.setTotalWidth(100f);
        innerTable2.setLockedWidth(true);
        innerTable2.setHorizontalAlignment(Element.ALIGN_CENTER);
        innerTable2.addCell("Cell 3");
        innerTable2.addCell("Cell 4");
        outerTable.addCell(innerTable2);
        PdfPTable innerTable3 = new PdfPTable(2);
        innerTable3.setTotalWidth(100f);
        innerTable3.setLockedWidth(true);
        innerTable3.setHorizontalAlignment(Element.ALIGN_RIGHT);
        innerTable3.addCell("Cell 5");
        innerTable3.addCell("Cell 6");
        outerTable.addCell(innerTable3);
   }
}