/********************** 版权声明 *************************
 * 文件名: MultipleImagesInTable.java
 * 包名: tables.images
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月4日 下午8:19:43
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
public class MultipleImagesInTable {
 
    public static final String DEST = "results/tables/images_in_table_sequence.pdf";
    public static final String IMG1 = "resources/images/brasil.png";
    public static final String IMG2 = "resources/images/dog.bmp";
    public static final String IMG3 = "resources/images/fox.bmp";
 
    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new MultipleImagesInTable().createPdf(DEST);
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
        table.setWidthPercentage(20);
        table.addCell(img1);
        table.addCell("Brazil");
        table.addCell(img2);
        table.addCell("Dog");
        table.addCell(img3);
        table.addCell("Fox");
        document.add(table);
        document.close();
    }
}