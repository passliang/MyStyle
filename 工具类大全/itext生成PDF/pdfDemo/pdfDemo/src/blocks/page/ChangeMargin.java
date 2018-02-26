/********************** 版权声明 *************************
 * 文件名: ChangeMargin.java
 * 包名: page
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月4日 下午6:15:41
 * 文件版本：V1.0 
 *
 *******************************************************/
package blocks.page;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
/**
 * @author iText
 */
public class ChangeMargin {
 
    public static final String DEST = "results/objects/change_margin.pdf";
 
    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ChangeMargin().createPdf(DEST);
        System.out.println("export over!");
    }
 
 
 
    public void createPdf(String dest) throws IOException, DocumentException {
        float left = 30;
        float right = 30;
        float top = 60;
        float bottom = 0;
        Document document = new Document(PageSize.A4, left, right, top, bottom);
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        document.setMargins(left, right, 0, bottom);
        for (int i = 0; i < 60; i++) {
            document.add(new Paragraph("This is a test"));
        }
        document.close();
    }
}