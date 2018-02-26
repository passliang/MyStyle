/********************** 版权声明 *************************
 * 文件名: TableSpace.java
 * 包名: tables.alignment
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月4日 下午8:30:05
 * 文件版本：V1.0 
 *
 *******************************************************/
package tables.alignment;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
 
public class TableSpace {
 
    public static final String DEST = "results/objects/spaces_table.pdf";
    public static final String FONT = "resources/fonts/PTM55FT.ttf";
    public static final String[][] DATA = {
        {"John Edward Jr.", "AAA"},
        {"Pascal Einstein W. Alfi", "BBB"},
        {"St. John", "CCC"}
    };
 
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new TableSpace().createPdf(DEST);
        System.out.println("export Over!");
    }
 
    public void createPdf(String dest) throws DocumentException, IOException {
        Document document = new Document();
 
        PdfWriter.getInstance(document, new FileOutputStream(dest));
 
        document.open();
 
        BaseFont bf = BaseFont.createFont(FONT, BaseFont.CP1250, BaseFont.EMBEDDED);
        Font font = new Font(bf, 12);
 
        document.add(createParagraphWithSpaces(font, String.format("%s: %s", "Name", DATA[0][0]), DATA[0][1]));
        document.add(createParagraphWithSpaces(font, String.format("%s: %s", "Surname", DATA[1][0]), DATA[1][1]));
        document.add(createParagraphWithSpaces(font, String.format("%s: %s", "School", DATA[2][0]), DATA[2][1]));
 
        document.close();
    }
 
    public Paragraph createParagraphWithSpaces(Font font, String value1, String value2) {
        Paragraph p = new Paragraph();
        p.setFont(font);
        p.add(String.format("%-35s", value1));
        p.add(value2);
        return p;
    }
}