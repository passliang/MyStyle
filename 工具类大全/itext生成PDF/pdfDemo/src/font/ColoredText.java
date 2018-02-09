/********************** 版权声明 *************************
 * 文件名: ColoredText.java
 * 包名: font
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月6日 下午5:31:06
 * 文件版本：V1.0 
 *
 *******************************************************/
package font;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
public class ColoredText {
 
    public static final String DEST = "results/font/colored_text.pdf";
 
 
    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ColoredText().createPdf(DEST);
        System.out.println("export over!");
    }
 
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        Font red = new Font(FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.RED);
        Chunk redText = new Chunk("This text is red. ", red);
        Font blue = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLUE);
        Chunk blueText = new Chunk("This text is blue and bold. ", blue);
        Font green = new Font(FontFamily.HELVETICA, 12, Font.ITALIC, BaseColor.GREEN);
        Chunk greenText = new Chunk("This text is green and italic. ", green);
 
        Paragraph p1 = new Paragraph(redText);
        document.add(p1);
        Paragraph p2 = new Paragraph();
        p2.add(blueText);
        p2.add(greenText);
        document.add(p2);
 
        ColumnText ct = new ColumnText(writer.getDirectContent());
        ct.setSimpleColumn(new Rectangle(36, 600, 144, 760));
        ct.addElement(p1);
        ct.addElement(p2);
        ct.go();
 
        document.close();
    }
}
