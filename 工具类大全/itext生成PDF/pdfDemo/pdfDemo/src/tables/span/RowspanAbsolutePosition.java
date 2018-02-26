/********************** 版权声明 *************************
 * 文件名: RowspanAbsolutePosition.java
 * 包名: tables.span
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月4日 下午8:47:00
 * 文件版本：V1.0 
 *
 *******************************************************/
package tables.span;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
public class RowspanAbsolutePosition {
    public static final String DEST = "results/tables/write_selected_colspan.pdf";
    public static final String IMG = "resources/images/background.jpg";
 
    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new RowspanAbsolutePosition().createPdf(DEST);
        System.out.println("export Over!");
    }
 
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table1 = new PdfPTable(3);
        table1.setWidths(new int[]{15,20,20});
        table1.setTotalWidth(555);
        PdfPCell cell = new PdfPCell(new Phrase("{Month}"));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        Image img = Image.getInstance(IMG);
        img.scaleToFit(555f * 20f / 55f, 10000);
        PdfPCell cell2 = new PdfPCell(img, false);
        cell2.setRowspan(2);
        PdfPCell cell3 = new PdfPCell(new Phrase("Mr Fname Lname"));
        cell3.setColspan(2);
        cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
        table1.addCell(cell);
        table1.addCell(cell2);
        table1.addCell(cell3);
        table1.writeSelectedRows(0, -1, 20 , 820, writer.getDirectContent());
        document.close();
    }
}
