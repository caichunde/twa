package com.dchb.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @param
 * @author caichunde
 * @Title: Excel工具类
 * @Description: Excel工具类
 * @return
 * @date 2018-09-29
 */
public class ExcelUtil {
    protected final static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    /**
     * @param MultipartFile multipartFile
     * @return Sheet sheet
     * @Title: 解析excel文件方法
     * @Description: 解析excel文件方法返回一个sheet
     * @author caichunde
     * @date 2018-09-29
     */
    public static Sheet parseExcel2Sheet(MultipartFile multipartFile) {
        //1.  使用HSSFWorkbook 打开或者创建 “Excel对象”
        //2.  用HSSFWorkbook返回对象或者创建sheet对象并返回
        Workbook workbook = null;
        String fileName = multipartFile.getOriginalFilename();//  获取文件名
        try {
            if (fileName.endsWith("XLS") || fileName.endsWith("xls")) {
                workbook = new HSSFWorkbook(multipartFile.getInputStream());//  2003版本
            }
            if (fileName.endsWith("XLSX") || fileName.endsWith("xlsx")) {
                workbook = new XSSFWorkbook(multipartFile.getInputStream());//  2007版本
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sheet sheet = workbook.getSheet("sheet1");
        return sheet;
    }

    /**
     * @param Cell cell
     * @return String string
     * @Title: 获取单元格值方法
     * @Description: 获取单元格值的方法
     * @author caichunde
     * @date 2018-09-29
     */
    public static String getCellValue(Cell cell) {
        String value = "";
        if (cell != null) {
            switch (cell.getCellType()) {
                case HSSFCell.CELL_TYPE_NUMERIC:// 数字
                    value = cell.getNumericCellValue() + " ";
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        Date date = cell.getDateCellValue();
                        if (date != null) {
                            value = new SimpleDateFormat("yyyy-MM-dd").format(date); //  日期格式化
                        } else {
                            value = "";
                        }
                    } else {
                        //  解析cell时候 数字类型默认是double类型的 但是想要获取整数类型 需要格式化
                        value = new DecimalFormat("0").format(cell.getNumericCellValue());
                    }
                    break;
                case HSSFCell.CELL_TYPE_STRING: //  字符串
                    value = cell.getStringCellValue();
                    break;
                case HSSFCell.CELL_TYPE_BOOLEAN:   //  Boolean类型
                    value = cell.getBooleanCellValue() + "";
                    break;
                case HSSFCell.CELL_TYPE_BLANK:   // 空值
                    value = "";
                    break;
                case HSSFCell.CELL_TYPE_ERROR: // 错误类型
                    value = "非法字符";
                    break;
                default:
                    value = "未知类型";
                    break;
            }

        }
        return value.trim();
    }

    /**
     * @param List<Map<String, Object>> data 数据， Map<String, String>  map 表头，Class c 实体的class
     * @return
     * @Title: 创建Excel表
     * @Description: 获取单元格值的方法
     * @author caichunde
     * @date 2019-1-8
     */
    public static void createExcel(HSSFWorkbook workbook, HSSFSheet sheet, Class<?> pojoClass, List<Map<String, Object>> data, Map<String, String> map) {
        HSSFRow row = sheet.createRow(0);
        sheet.createFreezePane(0, 1, 0, 1);
        Field[] fields = pojoClass.getDeclaredFields();
        //设置为居中加粗
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 11);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setFont(font);

        // 解析处理表头
        HashMap<String, String[]> mapTitle = new HashMap<>();
        mapTitle.put("xh", new String[]{"序号", "java.lang.Integer"});
        List<Map<String, String[]>> listTitle = new ArrayList<>();
        listTitle.add(mapTitle);
        for (int j = 0, lenj = fields.length; j < lenj; j++) {
            Field field = fields[j];
            String name = field.getName();
            mapTitle = new HashMap<>();
            String type = field.getAnnotatedType().getType().getTypeName();
            if (map.containsKey(name)) {
                mapTitle.put(name, new String[]{map.get(name), type});
                listTitle.add(mapTitle);
            }
        }

        // 解析处理数据
        List<Map<String, Object>> parseData = new ArrayList<>();
        for (int i = 0, len = data.size(); i < len; i++) {
            Map<String, Object> parseMap = new HashMap<>();
            for (Map.Entry<String, Object> entry : data.get(i).entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (key.indexOf("_") > 0) {
                    key = key.replace("_", "");
                }
                parseMap.put(key, value);
            }
            parseMap.put("XH", i + 1);
            parseData.add(parseMap);
        }

        //新增数据行，并且设置单元格数据
        HSSFCell cell;
        int rowNum = 1;
        for (int i = 0, len = parseData.size(); i < len; i++) {
            Map<String, Object> mapData = parseData.get(i);
            HSSFRow row1 = sheet.createRow(rowNum);
            int colNum = 0;
            for (int j = 0, lenj = listTitle.size(); j < lenj; j++) {
                for (Map.Entry<String, String[]> entry : listTitle.get(j).entrySet()) {
                    String key = entry.getKey().toUpperCase();
                    String[] strArr = entry.getValue();
                    String name = strArr[0];
                    String type = strArr[1];

                    //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
                    sheet.setColumnWidth(colNum, (22 + name.length()) * 256);
                    cell = row.createCell(colNum);
                    cell.setCellValue(name);
                    cell.setCellStyle(style);

                    cell = row1.createCell(colNum);
                    if (mapData.containsKey(key)) {
                        if (type.equalsIgnoreCase("java.util.Date")) {
                            HSSFCell cell1 = row1.createCell(colNum);
                            try {
                                cell1.setCellValue(DateUtil.changeDateFormat(mapData.get(key).toString(), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss"));
                            } catch (Exception e) {
                                logger.info("错误信息：" + e.getMessage());
                            }
                        } else if (type.equalsIgnoreCase("java.lang.Boolean")) {
                            HSSFCell cell1 = row1.createCell(colNum);
                            cell1.setCellValue((Boolean) mapData.get(key));
                        } else if (type.equalsIgnoreCase("java.lang.Integer")) {
                            HSSFCell cell1 = row1.createCell(colNum);
                            cell1.setCellValue((Integer) mapData.get(key));
                        } else if (type.equalsIgnoreCase("java.lang.Double")) {
                            HSSFCell cell1 = row1.createCell(colNum);
                            cell1.setCellValue((Double) mapData.get(key));
                        } else {
                            cell.setCellValue(mapData.get(key).toString());
                        }
                    } else {
                        cell.setCellValue("");
                    }
                }
                colNum++;
            }
            rowNum++;
        }

    }

    /**
     * @param String filename 文件名称，String filepath 文件路径
     * @return
     * @Title: 生成excel文件
     * @Description:
     * @author caichunde
     * @date 2019-1-8
     */
    public static void buildExcelFile(String filepath, String filename, HSSFWorkbook workbook) throws Exception {
        File file = new File(filepath + File.separator + filename);
        FileOutputStream fos = new FileOutputStream(file);
        workbook.write(fos);
        fos.flush();
        fos.close();
    }
}
