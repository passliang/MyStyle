/********************** 版权声明 *************************
 * 文件名: ReuseFont.java
 * 包名: font
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月6日 下午5:48:11
 * 文件版本：V1.0 
 *
 *******************************************************/
package font;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PRIndirectReference;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
 
public class ReuseFont {
 
    public static final String SRC = "resources/pdfs/form.pdf";
    public static final String DEST = "results/acroforms/form_stamped.pdf";
 
    public static void main(String[] args) throws DocumentException, IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ReuseFont().manipulatePdf(SRC, DEST);
        System.out.println("export over!");
    }
 
    public BaseFont findFontInForm(PdfReader reader, PdfName fontname) {
    	PdfDictionary root = reader.getCatalog();
    	PdfDictionary acroform = root.getAsDict(PdfName.ACROFORM);
    	if (acroform == null) return null;
    	PdfDictionary dr = acroform.getAsDict(PdfName.DR);
    	if (dr == null) return null;
    	PdfDictionary font = dr.getAsDict(PdfName.FONT);
    	if (font == null) return null;
    	for (PdfName key : font.getKeys()) {
    		if (key.equals(fontname)) {
    			return BaseFont.createFont((PRIndirectReference)font.getAsIndirectObject(key));
    		}
    	}
    	return null;
    }
 
    public void manipulatePdf(String src, String dest) throws DocumentException, IOException {
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        BaseFont bf = findFontInForm(reader, new PdfName("Calibri"));
        PdfContentByte canvas = stamper.getOverContent(1);
        Phrase phrase = new Phrase("Some text in Calibri", new Font(bf, 13));
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase, 36, 806, 0);
        stamper.close();
    }
}