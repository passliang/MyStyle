/********************** 版权声明 *************************
 * 文件名: SimpleTable4.java
 * 包名: tables.alignment
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月4日 下午8:21:22
 * 文件版本：V1.0 
 *
 *******************************************************/
package tables.alignment;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
public class SimpleTable4 {
    public static final String DEST = "results/tables/simple_table4.pdf";
 
    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new SimpleTable4().createPdf(DEST);
        System.out.println("export Over!");
    }
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(1);
        Paragraph wrong = new Paragraph("This is wrong, because an object that was originally a paragraph is reduced to a phrase due to the fact that it's put into a cell that uses text mode.");
        wrong.setIndentationLeft(20);
        PdfPCell wrongCell = new PdfPCell(wrong);
        table.addCell(wrongCell);
        Paragraph right = new Paragraph("This is right, because we create a paragraph with an indentation to the left and as we are adding the paragraph in composite mode, all the properties of the paragraph are preserved.");
        right.setIndentationLeft(20);
        PdfPCell rightCell = new PdfPCell();
        rightCell.addElement(right);
        table.addCell(rightCell);
        document.add(table);
        document.close();
    }
 
}