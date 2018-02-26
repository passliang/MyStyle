/********************** 版权声明 *************************
 * 文件名: MakeJpgMask.java
 * 包名: images
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月8日 上午8:29:22
 * 文件版本：V1.0 
 *
 *******************************************************/
package images;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
import javax.imageio.ImageIO;
 
public class MakeJpgMask {
    public static final String IMAGE = "resources/images/bruno.jpg";
    public static final String MASK = "resources/images/berlin2013.jpg";
    public static final String DEST = "results/images/jpg_mask.pdf";
 
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new MakeJpgMask().createPdf(DEST);
        System.out.println("export over!");
    }
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        Image image = Image.getInstance(IMAGE);
        Image mask = makeBlackAndWhitePng(MASK);
        mask.makeMask();
        image.setImageMask(mask);
        image.scaleAbsolute(PageSize.A4.rotate());
        image.setAbsolutePosition(0, 0);
        document.add(image);
        document.close();
    }
 
    public static Image makeBlackAndWhitePng(String image) throws IOException, DocumentException {
        BufferedImage bi = ImageIO.read(new File(image));
        BufferedImage newBi = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_USHORT_GRAY);
        newBi.getGraphics().drawImage(bi, 0, 0, null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(newBi, "png", baos);
        return Image.getInstance(baos.toByteArray());
    }
}
