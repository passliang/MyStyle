/********************** 版权声明 *************************
 * 文件名: SimpleTable9.java
 * 包名: tables.span
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月4日 下午8:44:41
 * 文件版本：V1.0 
 *
 *******************************************************/
package tables.span;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
public class SimpleTable9 {
    public static final String DEST = "results/tables/simple_table9.pdf";
 
    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new SimpleTable9().createPdf(DEST);
        System.out.println("export Over!");
    }
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        document.add(new Paragraph("With 3 columns:"));
        PdfPTable table = new PdfPTable(3);
        table.setSpacingBefore(5);
        table.setWidths(new int[]{1, 1, 8});
        table.setWidthPercentage(100);
        table.addCell("Col a");
        table.addCell("Col b");
        table.addCell("Col c");
        table.addCell("Value a");
        table.addCell("Value b");
        table.addCell("This is a long description for column c. It needs much more space hence we made sure that the third column is wider.");
        document.add(table);
        document.add(new Paragraph("With 2 columns:"));
        table = new PdfPTable(2);
        table.setSpacingBefore(5);
        table.setWidthPercentage(100);
        table.getDefaultCell().setColspan(1);
        table.addCell("Col a");
        table.addCell("Col b");
        table.addCell("Value a");
        table.addCell("Value b");
        table.getDefaultCell().setColspan(2);
        table.addCell("Value b");
        table.addCell("This is a long description for column c. It needs much more space hence we made sure that the third column is wider.");
        table.getDefaultCell().setColspan(1);
        table.addCell("Col a");
        table.addCell("Col b");
        table.addCell("Value a");
        table.addCell("Value b");
        table.getDefaultCell().setColspan(2);
        table.addCell("Value b");
        table.addCell("This is a long description for column c. It needs much more space hence we made sure that the third column is wider.");
        document.add(table);
        document.close();
    }
 
}