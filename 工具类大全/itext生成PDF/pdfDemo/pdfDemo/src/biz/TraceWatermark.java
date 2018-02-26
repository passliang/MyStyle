/********************** 版权声明 *************************
 * 文件名: Watermark.java
 * 包名: biz
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月29日 下午2:26:43
 * 文件版本：V1.0 
 *
 *******************************************************/
package biz;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * @类名: TraceWatermark
 * @职责说明: 水印_文字底纹
 * @创建者: peijd
 * @创建时间: 2016年7月29日 下午2:20:15
 */
public class TraceWatermark extends PdfPageEventHelper {
	//标题样式
	public static Font titleFont = FontFactory.getFont("resources/fonts/DENG.TTF", BaseFont.IDENTITY_H, 60, Font.NORMAL, BaseColor.LIGHT_GRAY);
//    protected Phrase watermark = new Phrase("农产品大数据溯源分析平台", titleFont);

    protected Phrase watermark = new Phrase("Xin Guo Ke Ji", new Font(FontFamily.HELVETICA, 60, Font.NORMAL, BaseColor.LIGHT_GRAY));

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        PdfContentByte canvas = writer.getDirectContentUnder();
        ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, watermark, 298, 421, 45);
    }
}
