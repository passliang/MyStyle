/********************** 版权声明 *************************
 * 文件名: FullDottedLine.java
 * 包名: separator
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月4日 下午7:21:04
 * 文件版本：V1.0 
 *
 *******************************************************/
package blocks.separator;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
 
public class FullDottedLine {
    public static final String DEST = "results/objects/full_dotted_line.pdf";
 
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new FullDottedLine().createPdf(DEST);
        System.out.println("export Over!");
    }
 
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        document.add(new Paragraph("Before dotted line"));
        DottedLineSeparator separator = new DottedLineSeparator();
        separator.setPercentage(59500f / 523f);
        Chunk linebreak = new Chunk(separator);
        document.add(linebreak);
        document.add(new Paragraph("After dotted line"));
        document.close();
    }
}