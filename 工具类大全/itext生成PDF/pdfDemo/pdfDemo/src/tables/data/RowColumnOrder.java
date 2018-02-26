/********************** 版权声明 *************************
 * 文件名: RowColumnOrder.java
 * 包名: tables.data
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月5日 下午8:58:58
 * 文件版本：V1.0 
 *
 *******************************************************/
package tables.data;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
public class RowColumnOrder {
 
    public static final String DEST = "results/tables/row_column_order.pdf";
 
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new RowColumnOrder().createPdf(DEST);
        System.out.println("export over!");
    }
 
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        document.add(new Paragraph("By design tables are filled row by row:"));
        PdfPTable table = new PdfPTable(5);
        table.setSpacingBefore(10);
        table.setSpacingAfter(10);
        for (int i = 1; i <= 15; i++) {
            table.addCell("cell " + i);
        }
        document.add(table);
 
        document.add(new Paragraph("If you want to change this behavior, you need to create a two-dimensional array first:"));
        String[][] array = new String[3][];
        int column = 0;
        int row = 0;
        for (int i = 1; i <= 15; i++) {
            if (column == 0) {
                array[row] = new String[5];
            }
            array[row++][column] = "cell " + i;
            if (row == 3) {
                column++;
                row = 0;
            }
        }
        table = new PdfPTable(5);
        table.setSpacingBefore(10);
        for (String[] r : array) {
            for (String c : r) {
                table.addCell(c);
            }
        }
        document.add(table);
        document.close();
    }
}
