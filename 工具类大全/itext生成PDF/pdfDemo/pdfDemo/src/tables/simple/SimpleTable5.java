/********************** 版权声明 *************************
 * 文件名: SimpleTable5.java
 * 包名: tables.simple
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月4日 下午8:49:49
 * 文件版本：V1.0 
 *
 *******************************************************/
package tables.simple;

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
 
public class SimpleTable5 {
    public static final String DEST = "results/tables/simple_table5.pdf";
 
    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new SimpleTable5().createPdf(DEST);
        System.out.println("export Over!");
    }
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(5);
    	table.setWidthPercentage(100);
    	PdfPCell cell = new PdfPCell(new Phrase("Table XYZ (Continued)"));
        cell.setColspan(5);
    	table.addCell(cell);
    	cell = new PdfPCell(new Phrase("Continue on next page"));
        cell.setColspan(5);
    	table.addCell(cell);
        table.setHeaderRows(2);
        table.setFooterRows(1);
        table.setSkipFirstHeader(true);
        table.setSkipLastFooter(true);
    	for (int i = 0; i < 350; i++) {
            table.addCell(String.valueOf(i+1));
    	}
    	document.add(table);
    	document.close();
    }
 
}