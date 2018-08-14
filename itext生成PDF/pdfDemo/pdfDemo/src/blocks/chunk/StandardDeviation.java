/********************** 版权声明 *************************
 * 文件名: StandardDeviation.java
 * 包名: chunk
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月4日 下午5:54:55
 * 文件版本：V1.0 
 *
 *******************************************************/
package blocks.chunk;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
/**
 * @author Bruno Lowagie (iText Software)
 */
public class StandardDeviation {
 
    public static final String DEST = "results/objects/standard_deviation.pdf";
 
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new StandardDeviation().createPdf(DEST);
        System.out.println("export over!");
    }
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        document.add(new Paragraph("The standard deviation symbol doesn't exist in Helvetica."));
        Font symbol = new Font(FontFamily.SYMBOL);
        Paragraph p = new Paragraph("So we use the Symbol font: ");
        p.add(new Chunk("s", symbol));
        document.add(p);
        document.close();
    }
 
}