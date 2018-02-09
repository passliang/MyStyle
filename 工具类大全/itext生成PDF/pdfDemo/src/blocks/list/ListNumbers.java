/********************** 版权声明 *************************
 * 文件名: ListNumbers.java
 * 包名: export
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月4日 下午5:06:54
 * 文件版本：V1.0 
 *
 *******************************************************/
package blocks.list;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.List;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
/**
 * @author Bruno Lowagie (iText Software)
 */
public class ListNumbers {
 
    public static final String DEST = "results/objects/list_numberst.pdf";
    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ListNumbers().createPdf(DEST);
        System.out.println("export over!");
    }
 
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        document.add(new Paragraph("Numbered list with automatic indentation"));
        List list1 = new List(List.ORDERED);
        list1.setFirst(8);
        for (int i = 0; i < 5; i++) {
            list1.add("item");
        }
        document.add(list1);
        document.add(new Paragraph("Numbered list without indentation"));
        List list2 = new List(List.ORDERED);
        list2.setFirst(8);
        list2.setAlignindent(false);
        for (int i = 0; i < 5; i++) {
            list2.add("item");
        }
        document.add(list2);
        document.add(new Paragraph("Nested numbered list"));
        List list3 = new List(List.ORDERED);
        list3.setFirst(8);
        list3.setAlignindent(false);
        list3.setPostSymbol(" ");
        for (int i = 0; i < 5; i++) {
            list3.add("item");
            List list = new List(List.ORDERED);
            list.setPreSymbol(String.valueOf(8 + i) + "_");
            list.setPostSymbol(" ");
            list.add("item 1");
            list.add("item 2");
            list3.add(list);
        }
        document.add(list3);
        document.close();
    }
 
}