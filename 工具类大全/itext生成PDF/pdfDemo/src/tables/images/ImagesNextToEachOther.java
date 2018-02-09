/********************** 版权声明 *************************
 * 文件名: ImagesNextToEachOther.java
 * 包名: tables.images
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月4日 下午8:01:07
 * 文件版本：V1.0 
 *
 *******************************************************/
package tables.images;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
 
public class ImagesNextToEachOther {
    public static final String DEST = "results/tables/images_next_to_each_other.pdf";
    public static final String IMG1 = "resources/images/background.jpg";
    public static final String IMG2 = "resources/images/export.png";
 
    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ImagesNextToEachOther().createPdf(DEST);
        System.out.println("export Over!");
    }
 
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.addCell(createImageCell(IMG1));
        table.addCell(createImageCell(IMG2));
        document.add(table);
        document.close();
    }
 
    public static PdfPCell createImageCell(String path) throws DocumentException, IOException {
        Image img = Image.getInstance(path);
        PdfPCell cell = new PdfPCell(img, true);
        return cell;
    }
}