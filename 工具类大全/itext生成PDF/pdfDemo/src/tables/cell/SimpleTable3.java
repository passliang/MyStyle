/********************** 版权声明 *************************
 * 文件名: SimpleTable3.java
 * 包名: tables.cell
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月4日 下午8:37:08
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
 
public class SimpleTable3 {
    public static final String DEST = "results/tables/simple_table3.pdf";
 
    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new SimpleTable3().createPdf(DEST);
        System.out.println("export Over!");
    }
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document(PageSize.A3.rotate());
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(35);
        table.setTotalWidth(document.getPageSize().getWidth() - 80);
        table.setLockedWidth(true);
        PdfPCell contractor = new PdfPCell(new Phrase("XXXXXXXXXXXXX"));
        contractor.setColspan(5);
        table.addCell(contractor);
        PdfPCell workType = new PdfPCell(new Phrase("Refractory Works"));
        workType.setColspan(5);
        table.addCell(workType);
        PdfPCell supervisor = new PdfPCell(new Phrase("XXXXXXXXXXXXXX"));
        supervisor.setColspan(4);
        table.addCell(supervisor);
        PdfPCell paySlipHead = new PdfPCell(new Phrase("XXXXXXXXXXXXXXXX"));
        paySlipHead.setColspan(10);
        table.addCell(paySlipHead);
        PdfPCell paySlipMonth = new PdfPCell(new Phrase("XXXXXXX"));
        paySlipMonth.setColspan(2);
        table.addCell(paySlipMonth);
        PdfPCell blank = new PdfPCell(new Phrase(""));
        blank.setColspan(9);
        table.addCell(blank);
        document.add(table);
        document.close();
    }
 
}
