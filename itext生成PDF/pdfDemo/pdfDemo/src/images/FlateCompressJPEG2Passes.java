/********************** 版权声明 *************************
 * 文件名: FlateCompressJPEG2Passes.java
 * 包名: images
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月8日 上午8:36:50
 * 文件版本：V1.0 
 *
 *******************************************************/
package images;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PRStream;
import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
 
public class FlateCompressJPEG2Passes {
    public static final String SRC = "resources/pdfs/image.pdf";
    public static final String DEST = "results/images/flatecompress_image2.pdf";
 
    public static void main(String[] args) throws DocumentException, IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new FlateCompressJPEG2Passes().manipulatePdf(SRC, DEST);
        System.out.println("export over!");
    }
 
    public void manipulatePdf(String src, String dest) throws DocumentException, IOException {
        PdfReader reader = new PdfReader(src);
        // We assume that there's a single large picture on the first page
        PdfDictionary page = reader.getPageN(1);
        PdfDictionary resources = page.getAsDict(PdfName.RESOURCES);
        PdfDictionary xobjects = resources.getAsDict(PdfName.XOBJECT);
        PdfName imgName = xobjects.getKeys().iterator().next();
        PRStream imgStream = (PRStream)xobjects.getAsStream(imgName);
        imgStream.setData(PdfReader.getStreamBytesRaw(imgStream), true);
        PdfArray array = new PdfArray();
        array.add(PdfName.FLATEDECODE);
        array.add(PdfName.DCTDECODE);
        imgStream.put(PdfName.FILTER, array);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        stamper.close();
        reader.close();
    }
}