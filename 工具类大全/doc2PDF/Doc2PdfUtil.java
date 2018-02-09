package com.zgqb.loan.app.util.wordtopdf;

import com.zgqb.loan.app.util.PathUtil;
import com.zgqb.loan.app.util.PinYinutil;
import org.apache.log4j.Logger;

import com.aspose.words.Document;
import com.aspose.words.HeaderFooter;
import com.aspose.words.HeaderFooterType;
import com.aspose.words.HorizontalAlignment;
import com.aspose.words.Paragraph;
import com.aspose.words.PdfSaveOptions;
import com.aspose.words.RelativeHorizontalPosition;
import com.aspose.words.RelativeVerticalPosition;
import com.aspose.words.SaveFormat;
import com.aspose.words.Section;
import com.aspose.words.Shape;
import com.aspose.words.ShapeType;
import com.aspose.words.VerticalAlignment;
import com.aspose.words.WrapType;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.zgqb.loan.app.util.PathUtil.createDir;


/**
 * doc to pdf 工具类
 *
 * @author liangz
 * @date 2017/12/29 20:39
 *
 **/
public class Doc2PdfUtil {

    private static  Logger logger = Logger.getLogger(Doc2PdfUtil.class);

    /**
     * 文件分隔符
     */
    static String  fileSep = File.separator;

    public static  Boolean convert2Pdf(String srcFilePath,String toFilePath) {
        try {
            FileInputStream fis = new FileInputStream(new File(srcFilePath));
            FileOutputStream fos = new FileOutputStream(new File(toFilePath));

            long start = System.currentTimeMillis();

            Document doc = new Document(fis);

            // insertWatermarkText(doc, "水印水印"); // 添加水印

            PdfSaveOptions pdfSaveOptions = new PdfSaveOptions();
            pdfSaveOptions.setSaveFormat(SaveFormat.PDF);
            //修改 page页面 页数
//            pdfSaveOptions.setPageCount(6);
            // 设置3级doc书签需要保存到pdf的heading中
            pdfSaveOptions.getOutlineOptions().setHeadingsOutlineLevels(3);
            // 设置pdf中默认展开1级
            pdfSaveOptions.getOutlineOptions().setExpandedOutlineLevels(1);
            doc.save(fos, pdfSaveOptions);
            long end = System.currentTimeMillis();
            logger.debug("convert doc2pdf completed, elapsed " + (end - start) / 1000.0 + " seconds!");
            return true;

        } catch (Exception e) {
            logger.error("convert doc2pdf error!", e);
            return false;
        }
    }


    /**
     * Inserts a watermark into a document.
     *
     * @param doc           The input document.
     * @param watermarkText Text of the watermark.
     */
    private void insertWatermarkText(Document doc, String watermarkText) throws Exception {
        // Create a watermark shape. This will be a WordArt shape.
        // You are free to try other shape types as watermarks.
        Shape watermark = new Shape(doc, ShapeType.TEXT_PLAIN_TEXT);

        // Set up the text of the watermark.
        // watermark.getTextPath().setSize(16.0);
        // watermark.getTextPath().setFontFamily("Arial"); // 使用Arial时最后那个字会丢
        watermark.getTextPath().setFontFamily("宋体");
        watermark.getTextPath().setItalic(true);
        watermark.getTextPath().setText(watermarkText);

        // Font size does not have effect if you specify height of the shape.
        // So you can just specify height instead of specifying font size.
        double fontSize = 100.0;
        watermark.setWidth(watermarkText.length() * fontSize);
        watermark.setHeight(fontSize);

        // Text will be directed from the bottom-left to the top-right corner.
        watermark.setRotation(-30);
        // Remove the following two lines if you need a solid black text.
        // Try LightGray to get more Word-style watermark
        watermark.getFill().setColor(Color.lightGray);
        // Try LightGray to get more Word-style watermark
        watermark.setStrokeColor(Color.lightGray);

        // Place the watermark in the page center.
        watermark.setRelativeHorizontalPosition(RelativeHorizontalPosition.PAGE);
        watermark.setRelativeVerticalPosition(RelativeVerticalPosition.PAGE);
        watermark.setWrapType(WrapType.NONE);
        watermark.setVerticalAlignment(VerticalAlignment.CENTER);
        watermark.setHorizontalAlignment(HorizontalAlignment.CENTER);
        // watermark.setHorizontalAlignment(HorizontalAlignment.LEFT);

        // Create a new paragraph and append the watermark to this paragraph.
        Paragraph watermarkPara = new Paragraph(doc);
        watermarkPara.appendChild(watermark);

        // Insert the watermark into all headers of each document section.
        for (Section sect : doc.getSections()) {
            // There could be up to three different headers in each section, since we want
            // the watermark to appear on all pages, insert into all headers.
            insertWatermarkIntoHeader(watermarkPara, sect, HeaderFooterType.HEADER_PRIMARY);
            insertWatermarkIntoHeader(watermarkPara, sect, HeaderFooterType.HEADER_FIRST);
            insertWatermarkIntoHeader(watermarkPara, sect, HeaderFooterType.HEADER_EVEN);
        }
    }

    private void insertWatermarkIntoHeader(Paragraph watermarkPara, Section sect, int headerType) throws Exception {
        HeaderFooter header = sect.getHeadersFooters().getByHeaderFooterType(headerType);

        if (header == null) {
            // There is no header of the specified type in the current section, create it.
            header = new HeaderFooter(sect.getDocument(), headerType);
            sect.getHeadersFooters().add(header);
        }

        // Insert a clone of the watermark into the header.
        header.appendChild(watermarkPara.deepClone(true));
    }

    /**
     * 获取待签署的文件目录
     * @param signFolderPrefix
     * @param name
     * @return
     */
    public  static String getSrcPdfPath(String signFolderPrefix,String name ){
        //当前日期创建文件夹
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(new Date());
        //名字转化位拼音
        String cnName = PinYinutil.ToPinyin(name);
        //文件目录 拼接格式 PDF+/ +时间戳（20171228）+lile/
        String signedFolder = signFolderPrefix+fileSep+"EsignPdf"+fileSep +date+fileSep+cnName;
        //创建目录
        PathUtil.createDir(signedFolder);
        //待签署文件路径
        String srcPdfName = File.separator+cnName+"_tranferPdf.pdf";
        return signedFolder+srcPdfName;
    }
}
