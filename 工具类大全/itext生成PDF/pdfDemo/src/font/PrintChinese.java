/********************** 版权声明 *************************
 * 文件名: PrintChina.java
 * 包名: font
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月29日 上午10:38:22
 * 文件版本：V1.0 
 *
 *******************************************************/
package font;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

/** 
 * @类名: font.PrintChina.java 
 * @职责说明: TODO
 * @创建者: peijd
 * @创建时间: 2016年7月29日 上午10:38:22
 */
public class PrintChinese {
	public static void main(String[] args) throws DocumentException, IOException {
		Document document = new Document();
		OutputStream os = new FileOutputStream(new File("d:/chinese.pdf"));
		PdfWriter.getInstance(document, os);
		document.open();
//		BaseFont baseFont = BaseFont.createFont(PrintChinese.class.getResource("/") + "/SIMSUN.TTC,1", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		// 方法一：使用Windows系统字体(TrueType)
//		BaseFont baseFont = BaseFont.createFont("C:/Windows/Fonts/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

		// 方法二：使用iTextAsian.jar中的字体
		// BaseFont baseFont =
		// BaseFont.createFont("STSong-Light",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);

		// 方法三：使用资源字体(ClassPath)
		//// BaseFont baseFont =
		// BaseFont.createFont("/SIMYOU.TTF",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);

		Font font = FontFactory.getFont("resources/fonts/DENG.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
//		Font font = new Font(baseFont);
		document.add(new Paragraph("all text is written in red, 中国骄傲, except the letters b and g; they are written in blue and green. 中古是打过！", font));
		document.close();
		System.out.println("export over!");
	}
}
