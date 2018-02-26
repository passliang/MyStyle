/********************** 版权声明 *************************
 * 文件名: MultipleImagesInCell.java
 * 包名: tables.images
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月4日 下午8:18:42
 * 文件版本：V1.0 
 *
 *******************************************************/
package tables.images;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
/**
 * @author Bruno Lowagie (iText Software)
 */
public class MultipleImagesInCell {
 
    public static final String DEST = "results/tables/images_in_cell.pdf";
    public static final String IMG1 = "resources/images/brasil.png";
    public static final String IMG2 = "resources/images/dog.bmp";
    public static final String IMG3 = "resources/images/fox.bmp";
 
    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new MultipleImagesInCell().createPdf(DEST);
        System.out.println("export Over!");
    }
 
    public void createPdf(String dest) throws IOException, DocumentException {
        Image img1 = Image.getInstance(IMG1);
        Image img2 = Image.getInstance(IMG2);
        Image img3 = Image.getInstance(IMG3);
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(50);
        table.addCell("Different images, one after the other vertically:");
        PdfPCell cell = new PdfPCell();
        cell.addElement(img1);
        cell.addElement(img2);
        cell.addElement(img3);
        table.addCell(cell);
        document.add(table);
        document.newPage();
        table = new PdfPTable(1);
        table.addCell("Different images, one after the other vertically, but scaled:");
        cell = new PdfPCell();
        img1.setWidthPercentage(20);
        cell.addElement(img1);
        img2.setWidthPercentage(20);
        cell.addElement(img2);
        img3.setWidthPercentage(20);
        cell.addElement(img3);
        table.addCell(cell);
        table.addCell("Different images, one after the other horizontally:");
        Paragraph p = new Paragraph();
        img1.scalePercent(30);
        p.add(new Chunk(img1, 0, 0, true));
        p.add(new Chunk(img2, 0, 0, true));
        p.add(new Chunk(img3, 0, 0, true));
        cell = new PdfPCell();
        cell.addElement(p);
        table.addCell(cell);
        table.addCell("Text and images (mixed):");
        p = new Paragraph("The quick brown ");
        p.add(new Chunk(img3, 0, 0, true));
        p.add(" jumps over the lazy ");
        p.add(new Chunk(img2, 0, 0, true));
        cell = new PdfPCell();
        cell.addElement(p);
        table.addCell(cell);
        document.add(table);
        document.close();
    }
}