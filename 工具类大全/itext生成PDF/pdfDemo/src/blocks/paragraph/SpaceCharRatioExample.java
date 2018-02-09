/********************** 版权声明 *************************
 * 文件名: SpaceCharRatioExample.java
 * 包名: paragraph
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月4日 下午7:27:59
 * 文件版本：V1.0 
 *
 *******************************************************/
package blocks.paragraph;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
/**
 * @author Bruno Lowagie (iText Software)
 */
public class SpaceCharRatioExample {
 
    public static final String DEST = "results/objects/space_char_ratio.pdf";
 
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new SpaceCharRatioExample().createPdf(DEST);
        System.out.println("export Over!");
    }
 
    public void createPdf(String dest) throws IOException, DocumentException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        // step 3
        document.open();
        // step 4
        writer.setSpaceCharRatio(PdfWriter.NO_SPACE_CHAR_RATIO);
        Paragraph paragraph = new Paragraph();
        paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
        paragraph.setIndentationLeft(20);
        paragraph.setIndentationRight(20);
        paragraph.add("HelloWorld HelloWorld HelloWorld HelloWorld HelloWorld"+ 
            "HelloWorld HelloWorldHelloWorldHelloWorldHelloWorld"+
            "HelloWorld HelloWorld HelloWorld HelloWorldHelloWorldHelloWorld");
        document.add(paragraph);
        // step 5
        document.close();
    }
}