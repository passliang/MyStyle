/********************** 版权声明 *************************
 * 文件名: ImagesInChunkInCell.java
 * 包名: tables.images
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月4日 下午8:08:52
 * 文件版本：V1.0 
 *
 *******************************************************/
package tables.images;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
/**
 * @author Bruno Lowagie
 */
public class ImagesInChunkInCell {
    public static final String IMG = "resources/images/bulb.gif";
    public static final String DEST = "results/tables/list_with_images.pdf";
 
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ImagesInChunkInCell().createPdf(DEST);
        System.out.println("export Over!");
    }
 
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        Image image = Image.getInstance(IMG);
        image.setScaleToFitHeight(false);
        PdfPTable table = new PdfPTable(1);
        table.setTotalWidth(new float[]{120});
        table.setLockedWidth(true);
        Phrase listOfDots = new Phrase();
        for (int i = 0; i < 40; i++) {
            listOfDots.add(new Chunk(image, 0, 0));
            listOfDots.add(new Chunk(" "));
        }
        table.addCell(listOfDots);
        document.add(table);
        document.close();
    }
}