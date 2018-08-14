/********************** 版权声明 *************************
 * 文件名: NestedTableProblem.java
 * 包名: tables.nested
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月4日 下午9:03:51
 * 文件版本：V1.0 
 *
 *******************************************************/
package tables.nested;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
public class NestedTableProblem {
 
    public static final String DEST = "results/tables/nested_table_problem.pdf";
 
    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new NestedTableProblem().createPdf(DEST);
        System.out.println("export Over!");
    }
 
    public void createPdf(String dest) throws IOException, DocumentException {
    	Document document = new Document(PageSize.LETTER, 21, 21, 30, 35);
    	PdfWriter.getInstance(document, new FileOutputStream(dest));
    	document.open();
    	// table 2
        final PdfPTable table2 = new PdfPTable(1);
    	table2.setHorizontalAlignment(Element.ALIGN_LEFT);
    	table2.getDefaultCell().setBorderColor(BaseColor.RED);
    	table2.getDefaultCell().setBorderWidth(1);
    	table2.addCell("Goodbye World");
      	// table 1
    	final PdfPTable table1 = new PdfPTable(1);
       	table1.setHorizontalAlignment(Element.ALIGN_LEFT);
    	table1.setWidthPercentage(100);
    	// contents
        PdfPCell cell = new PdfPCell();
    	cell.setBorderColor(BaseColor.BLACK);
    	cell.setBorderWidth(1);
        cell.addElement(new Chunk("Hello World"));
    	cell.addElement(table2);
    	cell.addElement(new Chunk("Hello World"));
    	table1.addCell(cell);
    	document.add(table1);
    	document.close();
    }
}