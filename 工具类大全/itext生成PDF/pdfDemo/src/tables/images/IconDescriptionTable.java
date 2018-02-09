/********************** 版权声明 *************************
 * 文件名: IconDescriptionTable.java
 * 包名: tables.images
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月4日 下午8:06:31
 * 文件版本：V1.0 
 *
 *******************************************************/
package tables.images;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
public class IconDescriptionTable {
    public static final String DEST = "results/tables/icon_description.pdf";
    public static final String IMG = "resources/images/bulb.gif";
 
    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new IconDescriptionTable().createPdf(DEST);
        System.out.println("export Over!");
    }
 
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(2);
        table.setWidths(new int[]{ 1, 9 });
        Image img = Image.getInstance(IMG);
        table.addCell(new PdfPCell(img, true));
        table.addCell(new Phrase("A light bulb icon"));
        document.add(table);
        document.close();
    }
}