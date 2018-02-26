/********************** 版权声明 *************************
 * 文件名: Every25Words.java
 * 包名: page.event
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月6日 上午8:51:13
 * 文件版本：V1.0 
 *
 *******************************************************/
package page.event;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
 
/**
 * @author iText
 */
public class Every25Words {
 
    public static final String DEST = "results/events/every25words.pdf";
 
    public class WordCounter extends PdfPageEventHelper {
 
        public int count = 0;
 
        @Override
        public void onGenericTag(PdfWriter writer, Document document, Rectangle rect, String text) {
            count++;
            if (count % 25 == 0) {
                PdfContentByte canvas = writer.getDirectContent();
                canvas.saveState();
                canvas.setLineDash(5, 5);
                canvas.moveTo(document.left(), rect.getBottom());
                canvas.lineTo(rect.getRight(), rect.getBottom());
                canvas.lineTo(rect.getRight(), rect.getTop());
                canvas.lineTo(document.right(), rect.getTop());
                canvas.stroke();
                canvas.restoreState();
            }
        }
    }
 
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new Every25Words().createPdf(DEST);
        System.out.println("export over!");
    }
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        writer.setPageEvent(new WordCounter());
        writer.setInitialLeading(16);
        document.open();
        String[] words = readFile().split("\\s+");
        Chunk chunk = null;
        for (String word : words) {
            if (chunk != null) {
                document.add(new Chunk(" "));
            }
            chunk = new Chunk(word);
            chunk.setGenericTag("");
            document.add(chunk);
        }
        document.close();
    }
 
    public String readFile() throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader in = new BufferedReader(
            new InputStreamReader(new FileInputStream("resources/text/liber1_1_la.txt"), "UTF8"));
        String str;
        while ((str = in.readLine()) != null) {
            sb.append(str);
        }
        return sb.toString();
    }
}
