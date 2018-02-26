/********************** 版权声明 *************************
 * 文件名: SimpleTable10.java
 * 包名: tables.simple
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月4日 下午7:56:24
 * 文件版本：V1.0 
 *
 *******************************************************/
package tables.simple;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
public class SimpleTable10 {
    public static final String DEST = "results/tables/simple_table10.pdf";
 
    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new SimpleTable10().createPdf(DEST);
        System.out.println("export Over!");
    }
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(5);
        PdfPCell sn = new PdfPCell(new Phrase("S/N"));
        sn.setRowspan(2);
        sn.setBackgroundColor(BaseColor.YELLOW);
        table.addCell(sn);
        PdfPCell name = new PdfPCell(new Phrase("Name"));
        name.setColspan(3);
        name.setBackgroundColor(BaseColor.CYAN);
        table.addCell(name);
        PdfPCell age = new PdfPCell(new Phrase("Age"));
        age.setRowspan(2);
        age.setBackgroundColor(BaseColor.GRAY);
        table.addCell(age);
        PdfPCell surname = new PdfPCell(new Phrase("SURNAME"));
        surname.setBackgroundColor(BaseColor.BLUE);
        table.addCell(surname);
        PdfPCell firstname = new PdfPCell(new Phrase("FIRST NAME"));
        firstname.setBackgroundColor(BaseColor.RED);
        table.addCell(firstname);
        PdfPCell middlename = new PdfPCell(new Phrase("MIDDLE NAME"));
        middlename.setBackgroundColor(BaseColor.GREEN);
        table.addCell(middlename);
        PdfPCell f1 = new PdfPCell(new Phrase("1"));
        f1.setBackgroundColor(BaseColor.PINK);
        table.addCell(f1);
        PdfPCell f2 = new PdfPCell(new Phrase("James"));
        f2.setBackgroundColor(BaseColor.MAGENTA);
        table.addCell(f2);
        PdfPCell f3 = new PdfPCell(new Phrase("Fish"));
        f3.setBackgroundColor(BaseColor.ORANGE);
        table.addCell(f3);
        PdfPCell f4 = new PdfPCell(new Phrase("Stone"));
        f4.setBackgroundColor(BaseColor.DARK_GRAY);
        table.addCell(f4);
        PdfPCell f5 = new PdfPCell(new Phrase("17"));
        f5.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(f5);
        document.add(table);
        document.close();
    }
 
}