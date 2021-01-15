package com.xy.mvs.util;

import com.xy.mvs.request.OrderExcel;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 陈璇
 * @Date 2021/1/15 15:45
 * @Version 1.0
 */
public class ExcelUtil {

    public static void exportExcel(HttpServletResponse response, List<OrderExcel> excelData, String sheetName, String fileName,
                                   int columnWidth) throws IOException {

        //声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        //生成一个表格，设置表格名称
        HSSFSheet sheet = workbook.createSheet(sheetName);
        //创建表头
        createTitle(workbook, sheet);
        //设置为居中加粗

        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        //写入List<OrderExcel>中的数据
        for(int i = 0;i < excelData.size();i++){

            OrderExcel orderExcel = excelData.get(i);

            //创建行
            HSSFRow row = sheet.createRow(i + 1);
            //设置行高
            row.setHeightInPoints(20);

            String[] orderExcelList = new String[12];
            orderExcelList[0] = orderExcel.getOrderNumber();
            orderExcelList[1] = Double.toString(orderExcel.getPayment());
            orderExcelList[2] = Double.toString(orderExcel.getTotalPrice());
            orderExcelList[3] = (orderExcel.getCreateTime().toString()).replaceAll("  ","T");
            orderExcelList[4] = (orderExcel.getPaymentTime().toString()).replaceAll("  ","T");;
            orderExcelList[5] = orderExcel.getProductName();
            orderExcelList[6] = orderExcel.getProductNum().toString();
            orderExcelList[7] = Double.toString(orderExcel.getProductPrice());
            orderExcelList[8] = Double.toString(orderExcel.getProductTotalPrice());
            orderExcelList[9] = orderExcel.getName();
            orderExcelList[10] = orderExcel.getTelephone();
            orderExcelList[11] = orderExcel.getAddress();

            for(int j = 0;j < orderExcelList.length;j++){
                HSSFCell cell = row.createCell(j);;   //设置单元格的数据类型
                //创建一个内容对象
                HSSFRichTextString text = new HSSFRichTextString(orderExcelList[j].toString());
                cell.setCellValue(text);
                cell.setCellStyle(style);
            }

        }

        //准备将Excel的输出流通过response输出到页面下载
        //八进制输出流
        response.setContentType("application/octet-stream");

        //设置导出Excel的名称
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8") + ".xls");

        //刷新缓冲
        response.flushBuffer();

        //workbook将Excel写入到response的输出流中，供页面下载该Excel文件
        workbook.write(response.getOutputStream());

        //关闭workbook
        workbook.close();
    }

    //创建表头
    private static void createTitle(HSSFWorkbook workbook,HSSFSheet sheet){
        HSSFRow row = sheet.createRow(0);
        //设置行高
        row.setHeightInPoints(20);
        //设置表格列宽度
        sheet.setDefaultColumnWidth(30);
        //设置为居中加粗
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        //写入List<List<String>>中的数据
        List<String> head = new ArrayList<>();
        head.add("订单编号");
        head.add("订单实付金额");
        head.add("订单总金额");
        head.add("下单时间");
        head.add("付款时间");
        head.add("商品名称");
        head.add("商品数量");
        head.add("商品单价");
        head.add("商品总价");
        head.add("收货人姓名");
        head.add("收货人手机号");
        head.add("收货人地址");
        head.add("快递名称");
        head.add("快照");
        head.add("物流单号");
        head.add("物流状态");
        for(int i = 0;i < head.size();i++){
            //创建一个单元格
            HSSFCell cell = row.createCell(i);
            //创建一个内容对象
            HSSFRichTextString text = new HSSFRichTextString(head.get(i));
            //将内容对象的文字内容写入到单元格中
            cell.setCellValue(text);
            cell.setCellStyle(style);
        }
    }

}
