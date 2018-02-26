/********************** 版权声明 *************************
 * 文件名: TableTab.java
 * 包名: tables.alignment
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月4日 下午8:28:53
 * 文件版本：V1.0 
 *
 *******************************************************/
package tables.alignment;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.TabSettings;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
 
 
public class TableTab {
 
    public static final String DEST = "results/objects/tab_table.pdf";
    public static final String[][] DATA = {
        {"John Edward Jr.", "AAA"},
        {"Pascal Einstein W. Alfi", "BBB"},
        {"St. John", "CCC"}
    };
 
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new TableTab().createPdf(DEST);
        System.out.println("export Over!");
    }
 
    public void createPdf(String dest) throws FileNotFoundException, DocumentException {
        Document document = new Document();
 
        PdfWriter.getInstance(document, new FileOutputStream(dest));
 
        document.open();
 
        document.add(createParagraphWithTab("Name: ", DATA[0][0], DATA[0][1]));
        document.add(createParagraphWithTab("Surname: ", DATA[1][0], DATA[1][1]));
        document.add(createParagraphWithTab("School: ", DATA[2][0], DATA[2][1]));
 
        document.close();
    }
 
    public Paragraph createParagraphWithTab(String key, String value1, String value2) {
        Paragraph p = new Paragraph();
        p.setTabSettings(new TabSettings(200f));
        p.add(key);
        p.add(value1);
        p.add(Chunk.TABBING);
        p.add(value2);
        return p;
    }
}
