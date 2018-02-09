/********************** 版权声明 *************************
 * 文件名: FlateCompressJPEG1Pass.java
 * 包名: images
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月8日 上午8:36:04
 * 文件版本：V1.0 
 *
 *******************************************************/
package images;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfStream;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
 
public class FlateCompressJPEG1Pass {
    public static final String IMAGE = "resources/images/berlin2013.jpg";
    public static final String DEST = "results/images/flatecompress_image1.pdf";
 
    public static void main(String[] args) throws DocumentException, IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new FlateCompressJPEG1Pass().createPdf(DEST);
        System.out.println("export over!");
    }
 
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        Image img = Image.getInstance(IMAGE);
        img.setCompressionLevel(PdfStream.BEST_COMPRESSION);
        img.scaleAbsolute(PageSize.A4.rotate());
        img.setAbsolutePosition(0, 0);
        document.add(img);
        document.close();
    }
}
