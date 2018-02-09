/********************** 版权声明 *************************
 * 文件名: SimpleTable.java
 * 包名: tables.simple
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月4日 下午7:37:05
 * 文件版本：V1.0 
 *
 *******************************************************/
package tables.simple;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
public class SimpleTable {
    public static final String DEST = "results/tables/simple_table.pdf";
 
    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new SimpleTable().createPdf(DEST);
        System.out.println("export Over!");
    }
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(8);
        for(int aw = 0; aw < 16; aw++){
            table.addCell("hi");
        }
        document.add(table);
        document.close();
    }
 
}
