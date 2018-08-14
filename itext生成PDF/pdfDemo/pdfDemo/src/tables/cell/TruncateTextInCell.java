/********************** 版权声明 *************************
 * 文件名: TruncateTextInCell.java
 * 包名: tables.cell
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月4日 下午8:50:54
 * 文件版本：V1.0 
 *
 *******************************************************/
package tables.cell;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
 
public class TruncateTextInCell {
 
    public static final String DEST = "results/tables/truncate_cell_content.pdf";
 
    public class TruncateContent implements PdfPCellEvent {
        protected String content;
        public TruncateContent(String content) {
            this.content = content;
        }
        public void cellLayout(PdfPCell cell, Rectangle position,
                PdfContentByte[] canvases) {
            try {
                BaseFont bf = BaseFont.createFont();
                Font font = new Font(bf, 12);
                float availableWidth = position.getWidth();
                int contentLength = content.length();
                int leftChar = 0;
                int rightChar = contentLength - 1;
                availableWidth -= bf.getWidthPoint("...", 12);
                while (leftChar < contentLength && rightChar != leftChar) {
                    availableWidth -= bf.getWidthPoint(content.charAt(leftChar), 12);
                    if (availableWidth > 0)
                        leftChar++;
                    else
                        break;
                    availableWidth -= bf.getWidthPoint(content.charAt(rightChar), 12);
                    if (availableWidth > 0)
                        rightChar--;
                    else
                        break;
                }
                String newContent = content.substring(0, leftChar) + "..." + content.substring(rightChar);
                PdfContentByte canvas = canvases[PdfPTable.TEXTCANVAS];
                ColumnText ct = new ColumnText(canvas);
                ct.setSimpleColumn(position);
                ct.addElement(new Paragraph(newContent, font));
                ct.go();
            } catch (DocumentException e) {
                throw new ExceptionConverter(e);
            } catch (IOException e) {
                throw new ExceptionConverter(e);
            }
        }
    }
 
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new TruncateTextInCell().createPdf(DEST);
        System.out.println("export Over!");
    }
 
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        PdfPCell cell;
        for (int r = 'A'; r <= 'Z'; r++) {
            for (int c = 1; c <= 5; c++) {
                cell = new PdfPCell();
                if (r == 'D' && c == 2) {
                    cell.setCellEvent(new TruncateContent("D2 is a cell with more content than we can fit into the cell."));
                }
                else {
                    cell.addElement(new Paragraph(String.valueOf((char) r) + String.valueOf(c)));
                }
                table.addCell(cell);
            }
        }
        document.add(table);
        document.close();
    }
}
