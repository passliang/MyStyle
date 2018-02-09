/********************** 版权声明 *************************
 * 文件名: NestedTables2.java
 * 包名: tables.nested
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月4日 下午9:02:08
 * 文件版本：V1.0 
 *
 *******************************************************/
package tables.nested;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
public class NestedTables2 {
    public static final String DEST = "results/tables/nested_tables2.pdf";
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new NestedTables2().createPdf(DEST);
        System.out.println("export Over!");
    }
 
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(2);
        table.setSplitLate(false);
        table.setWidths(new int[]{1, 15});
        for (int i = 1; i <= 20; i++) {
            table.addCell(String.valueOf(i));
            table.addCell("It is not smart to use iText 2.1.7!");
        }
        PdfPTable innertable = new PdfPTable(2);
        innertable.setWidths(new int[]{1, 15});
        for (int i = 0; i < 90; i++) {
            innertable.addCell(String.valueOf(i + 1));
            innertable.addCell("Upgrade if you're a professional developer!");
        }
        table.addCell("21");
        table.addCell(innertable);
        for (int i = 22; i <= 40; i++) {
            table.addCell(String.valueOf(i));
            table.addCell("It is not smart to use iText 2.1.7!");
        }
        document.add(table);
        document.close();
    }
}
