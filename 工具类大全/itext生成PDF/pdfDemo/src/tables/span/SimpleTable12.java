/********************** 版权声明 *************************
 * 文件名: SimpleTable12.java
 * 包名: tables.span
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月4日 下午8:46:12
 * 文件版本：V1.0 
 *
 *******************************************************/
package tables.span;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
public class SimpleTable12 {
    public static final String DEST = "results/tables/simple_table12.pdf";
 
    protected Font font;
 
    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new SimpleTable12().createPdf(DEST);
        System.out.println("export Over!");
    }
    public void createPdf(String dest) throws IOException, DocumentException {
        font = new Font(FontFamily.HELVETICA, 10);
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100);
        table.addCell(createCell("Examination", 1, 2, PdfPCell.BOX));
        table.addCell(createCell("Board", 1, 2, PdfPCell.BOX));
        table.addCell(createCell("Month and Year of Passing", 1, 2, PdfPCell.BOX));
        table.addCell(createCell("", 1, 1, PdfPCell.TOP));
        table.addCell(createCell("Marks", 2, 1, PdfPCell.TOP));
        table.addCell(createCell("Percentage", 1, 2, PdfPCell.BOX));
        table.addCell(createCell("Class / Grade", 1, 2, PdfPCell.BOX));
        table.addCell(createCell("", 1, 1, PdfPCell.BOX));
        table.addCell(createCell("Obtained", 1, 1, PdfPCell.BOX));
        table.addCell(createCell("Out of", 1, 1, PdfPCell.BOX));
        table.addCell(createCell("12th / I.B. Diploma", 1, 1, PdfPCell.BOX));
        table.addCell(createCell("", 1, 1, PdfPCell.BOX));
        table.addCell(createCell("", 1, 1, PdfPCell.BOX));
        table.addCell(createCell("Aggregate (all subjects)", 1, 1, PdfPCell.BOX));
        table.addCell(createCell("", 1, 1, PdfPCell.BOX));
        table.addCell(createCell("", 1, 1, PdfPCell.BOX));
        table.addCell(createCell("", 1, 1, PdfPCell.BOX));
        table.addCell(createCell("", 1, 1, PdfPCell.BOX));
        document.add(table);
        document.close();
    }
 
    public PdfPCell createCell(String content, int colspan, int rowspan, int border) {
        PdfPCell cell = new PdfPCell(new Phrase(content, font));
        cell.setColspan(colspan);
        cell.setRowspan(rowspan);
        cell.setBorder(border);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        return cell;
    }
 
}