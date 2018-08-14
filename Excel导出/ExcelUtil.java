package com.iqurong.qjq.cms.common.util.excel;

import com.iqurong.qjq.cms.model.dto.BorrowRepaymentDto;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * excel数据导出
 *
 */

public class ExcelUtil {
	
	 public static final int DEFAULT_WIDTH = 20;
    public static final int DEFAULT_HEIGHT = 8;

    /**
     * @param excelName 表格名字
     * @param clazz     到导入到Excel的Class对象
     * @param list      对出数据的列表
     * @throws Exception
     */
    public static String exportToExcel(String excelName, Class clazz, List list) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = sdf.format(System.currentTimeMillis());
		//PropertiesUtil.getValue("outExcelPath") 文件导出目录 可修改
        String localExcelName = new String((PropertiesUtil.getValue("outExcelPath") + excelName + date + ".xls")
                .getBytes("UTF-8"), "UTF-8");
        if(!new File(localExcelName).exists()){

            new File(localExcelName).createNewFile();
            System.out.println("文件创建成功！！！");
        }
        FileOutputStream outputStream = new FileOutputStream(localExcelName);
        try {
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), clazz, list);
            //设置样式
            //默認sheet的名字
            Sheet sheet = workbook.getSheet("Sheet0");
            //取XSL文件Sheet1页上第1行
            Row row = sheet.getRow(0);
            CellStyle cellStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            //字体大小
            font.setFontHeightInPoints((short) 14);
            font.setFontName("楷体");
            font.setBold(true);
            //紅色字
            font.setColor(HSSFColor.BLACK.index);
            cellStyle.setFont(font);
            cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            cellStyle.setAlignment(CellStyle.ALIGN_CENTER);

            int cells = row.getPhysicalNumberOfCells();
            for (int i = 0; i < cells; i++) {
                row.getCell(i).setCellStyle(cellStyle);
            }

            workbook.write(outputStream);

            return excelName + date;
        } finally {
            outputStream.close();
        }
    }
		
    /**
     *
     * @param excelName 表格名字
     * @param clazz 到导入到Excel的Class对象
     * @param list  对出数据的列表
     * @param response  Response
     * @throws Exception
     */
    public static void  exportToExcel(String excelName , Class clazz, List list, HttpServletResponse response) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(System.currentTimeMillis());
        StringBuffer sb = new StringBuffer();
        String localExcelName = new String(sb.append(excelName + date).toString().getBytes("gbk"), "iso8859-1");
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=" + localExcelName + ".xls");
        OutputStream os = null;
        try {
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), clazz, list);
            //设置样式
            Sheet sheet = workbook.getSheet("Sheet0");//默認sheet的名字
            Row row = sheet.getRow(0); //取XSL文件Sheet1页上第1行
            CellStyle cellStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setFontHeightInPoints((short)14); //字体大小
            font.setFontName("楷体");
            font.setBold(true);
            font.setColor(HSSFColor.BLACK.index);    //紅色字
            cellStyle.setFont(font);
            cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            cellStyle.setAlignment(CellStyle.ALIGN_CENTER);

            int cells = row.getPhysicalNumberOfCells();
            for (int i=0;i<cells ;i++){
                row.getCell(i).setCellStyle(cellStyle);
            }
            os = new BufferedOutputStream(response.getOutputStream());
            workbook.write(os);
        }
         finally {
            os.flush();
            os.close();
        }
    }
}
