/********************** 版权声明 *************************
 * 文件名: HeaderRowRepeated.java
 * 包名: tables.data
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月5日 下午9:00:30
 * 文件版本：V1.0 
 *
 *******************************************************/
package tables.data;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
/**
 * @author iText
 */
public class HeaderRowRepeated {
    public static final String DEST = "results/tables/repeat_header_row.pdf";
 
    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new HeaderRowRepeated().createPdf(DEST);
        System.out.println("export over!");
    }    
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        // table with 2 columns:
        PdfPTable table = new PdfPTable(2);
        // header row:
        table.addCell("Key");
        table.addCell("Value");
        table.setHeaderRows(1);
        table.setSkipFirstHeader(true);
        // many data rows:
        for (int i = 1; i < 51; i++) {
            table.addCell("key: " + i);
            table.addCell("value: " + i);
        }
        document.add(table);
        document.close();
    }
}