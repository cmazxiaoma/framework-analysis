package com.cmazxiaoma.excel;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author wt
 * @version V1.0
 * @Description: TODO
 * @date 2018/3/19 18:49
 */
public class ImportExcelUtil implements Serializable {


    private final static String EXCEL2003L = ".xls";    //2003- 版本的excel
    private final static String EXCEL2007U = ".xlsx";   //2007+ 版本的excel

    /**
     * 描述：获取IO流中的数据，组装成List<List<Object>>对象
     *
     * @param inputStream 输入流
     * @param fileName    文件名
     * @return
     * @throws Exception
     */
    public List<List<Object>> getBankListByExcel(InputStream inputStream, String fileName) throws Exception {
        List<List<Object>> list = null;

        //创建Excel工作薄
        Workbook workbook = this.getWorkbook(inputStream, fileName);
        if (Objects.isNull(workbook)) {
            throw new RuntimeException("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;
        list = Lists.newLinkedList();
        // 遍历Excel中所有的sheet
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            sheet = workbook.getSheetAt(i);

            if (Objects.isNull(sheet)) {
                continue;
            }

            int lastRowNum = sheet.getLastRowNum();
            //excel模板格式错误, 模板前3行是注释
            if (lastRowNum < 3) {
                throw new RuntimeException("excel模板格式错误或者数据为空");
            }

            // 遍历当前sheet中的所有行
            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                // 第一行过滤掉
                if (j < 1) {
                    continue;
                }

                row = sheet.getRow(j);
                if (Objects.isNull(row)) {
                    continue;
                }

                //遍历所有的列
                List<Object> li = Lists.newArrayList();
                for (int y = sheet.getRow(0).getFirstCellNum(); y < sheet.getRow(0).getPhysicalNumberOfCells(); y++) {
                    /*if (getCellValue(row.getCell(0)).equals("end")){
                        break;
                    }*/

                    if (y < 0) {
                        throw new RuntimeException("excel模板格式错误");
                    }

                    cell = row.getCell(y);
                    li.add(this.getCellValue(cell));
                }
                list.add(li);
            }
        }
        inputStream.close();
        workbook.close();
        return list;
    }

    /**
     * 描述：根据文件后缀，自适应上传文件的版本
     *
     * @param inputStream 输入流
     * @param fileName    文件名
     * @return
     * @throws Exception
     */
    public Workbook getWorkbook(InputStream inputStream, String fileName) throws Exception {
        Workbook workbook = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (EXCEL2003L.equals(fileType)) {
            workbook = new HSSFWorkbook(inputStream);  //2003-
        } else if (EXCEL2007U.equals(fileType)) {
            workbook = new XSSFWorkbook(inputStream);  //2007+
        } else {
            throw new RuntimeException("解析的文件格式有误！");
        }
        return workbook;
    }

    /**
     * 描述：对表格中数值进行格式化
     *
     * @param cell
     * @return
     */
    public Object getCellValue(Cell cell) {
        if (Objects.isNull(cell)) {
            return null;
        }
        Object value = null;
        DecimalFormat df = new DecimalFormat("0");  //格式化number String字符
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");  //日期格式化
        DecimalFormat df2 = new DecimalFormat("0.0");  //格式化数字

        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                value = cell.getRichStringCellValue().getString();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                    value = df.format(cell.getNumericCellValue());
                } else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
                    value = sdf.format(cell.getDateCellValue());
                } else {
                    value = df2.format(cell.getNumericCellValue());
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case Cell.CELL_TYPE_BLANK:
                value = "";
                break;
            default:
                break;
        }
        return value;
    }


    public static void main(String[] args) throws Exception {
        String goodStr = "INSERT INTO `qu_exchange`.`qu_piece_good`" +
                "(" +
                "`id`," +
                "`good_id`, " +
                "`source_id`, " +
                "`source_title`, " +
                "`origin_price`, " +
                "`sort`, " +
                "`open_mod_version`, " +
                "`random_factor`, " +
                "`false_mod`, " +
                "`open_num`, " +
                "`remain_num`, " +
                "`create_time`, " +
                "`update_time`) \n"
                + "VALUES ";


        String goodValueStr = "({0}, {1}, {2}, {3}, {4}, {5}, {6}, {7}, {8},{9},{10},now(),now()),";

        String goodSettingStr = "INSERT INTO `qu_exchange`.`qu_piece_good_setting`(" +
                "`id`, " +
                "`source_id`, " +
                "`source_title`, " +
                "`origin_price`, " +
                "`open_num`, " +
                "`open_mod_version`, " +
                "`random_factor`, " +
                "`false_mod`," +
                "`sort`, " +
                "`create_time`," +
                "`update_time`) \n"
                + "VALUES ";

        String goodSettingValueStr = "({0},{1},{2},{3},{4},{5},{6},{7},{8},now(),now()),";

        String updateGoodSettingStr = "update qu_piece_good_setting\n" +
                "set source_title = {0},\n" +
                "sort = {1}\n" +
                "where id = {2};";

        String updateGoodStr = "update qu_piece_good\n" +
                "set source_title = {0},\n" +
                "sort = {1}\n" +
                "where good_id = {2};";

        String pathname = "D:\\piece_3.xlsx";

        String updateGoodSettingResult = "";
        String updateGoodResult = "";

        File filename = new File(pathname);
        FileInputStream fileInputStream = new FileInputStream(filename);

        ImportExcelUtil importExcelUtil = new ImportExcelUtil();
        List<List<Object>> dataList = importExcelUtil.getBankListByExcel(fileInputStream, "piece_1.xlsx");

        for (List<Object> objectList : dataList) {
            if (!CollectionUtils.isEmpty(objectList)) {

                String id = (String) objectList.get(0);

                if (id  == null) {
                    continue;
                }

                String sourceId = (String) objectList.get(1);

                if (sourceId == null) {
                    continue;
                }

                // 去掉 .0
                sourceId = sourceId.substring(0, sourceId.length() - 2);

                String sourceTitle = (String) objectList.get(2);

                String originPrice = (String) objectList.get(4);

                String openNum = (String) objectList.get(5);

                String openModVersion = (String) objectList.get(6);

                String randomFactor = (String) objectList.get(9);

                String falseMod = (String) objectList.get(10);

                String sort = (String) objectList.get(11);

                String goodSettingSqlStr = MessageFormat.format(goodSettingValueStr,
                        "'" + id + "'",
                        "'" + sourceId +  "'",
                        "'" + sourceTitle + "'",
                        "'" + originPrice + "'",
                        "'" + openNum + "'",
                        "'" + openModVersion + "'",
                        "'" + randomFactor + "'",
                        "'" + falseMod + "'",
                        "'" + sort + "'");

                goodSettingStr += "\n" + goodSettingSqlStr;

                String goodSqlStr = MessageFormat.format(goodValueStr,
                        "'" + id + "'",
                        "'" + id + "'",
                        "'" + sourceId + "'",
                        "'" + sourceTitle + "'",
                        "'" + originPrice + "'",
                        "'"  + sort + "'",
                        "'" + openModVersion + "'",
                        "'" + randomFactor + "'",
                        "'" + falseMod + "'",
                        "'" + openNum + "'",
                        "'" + openNum + "'"
                );

                goodStr += "\n" + goodSqlStr;


                String updateGoodSettingTempStr = MessageFormat.format(updateGoodSettingStr,
                        "'" + sourceTitle + "'",
                        "'" + sort + "'",
                        "'" + id + "'");
                updateGoodSettingResult += "\n" + updateGoodSettingTempStr;

                String updateGoodTempStr = MessageFormat.format(updateGoodStr,
                        "'" + sourceTitle + "'",
                        "'" + sort + "'",
                        "'" + id + "'");
                updateGoodResult += "\n" + updateGoodTempStr;
            }
        }

        System.out.println(goodSettingStr.substring(0,  goodSettingStr.length() - 1) + ";");
        System.out.println(goodStr.substring(0, goodStr.length() - 1) + ";");


        System.out.println("updateGoodSetting sql:" + updateGoodSettingResult);
        System.out.println("updateGood sql:" + updateGoodResult);

    }


//    public static void main(String[] args) throws Exception {
//        String str = "INSERT INTO `activity`.`qu_mall_11_lottery_user_record_false`" +
//                "(`id`, `version`, `date_string`, `user_id`, `good_name`, `sort`) \n" +
//                "VALUES ";
//        String valueTemplate = "(null, {0}, {1}, {2}, {3}, {4}),";
//
//        String pathname = "D:\\import.xls";
//        File filename = new File(pathname);
//        FileInputStream fileInputStream = new FileInputStream(filename);
//
//        ImportExcelUtil importExcelUtil = new ImportExcelUtil();
//        List<List<Object>> dataList = importExcelUtil.getBankListByExcel(fileInputStream, "import.xls");
//
//
//        Map<Integer, Integer> map = Maps.newHashMap();
//
//        for (List<Object> objectList : dataList) {
//            if (!CollectionUtils.isEmpty(objectList)) {
//                Integer version = 0;
//                String dateString = (String) objectList.get(0);
//                String user_id = (String) objectList.get(1);
//                String goodName = (String) objectList.get(2);
//
//                if (Objects.equals(dateString, "2019-10-25")) {
//                    version = 1;
//                } else if (Objects.equals(dateString, "2019-10-31")) {
//                    version = 2;
//                } else if (Objects.equals(dateString, "2019-11-10")) {
//                    version = 3;
//                }
//
//                Integer count = 1;
//                if (map.containsKey(version)) {
//                    count = map.get(version) + 1;
//                    map.put(version, count);
//                } else {
//                    map.put(version, count);
//                }
//
//                String sql = MessageFormat.format(valueTemplate, version,
//                        "'" + dateString + "'",
//                        "'" + user_id + "'",
//                        "'" + goodName + "'",
//                        count);
//
//                str += "\n" + sql;
//            }
//        }
//
//        System.out.println("str=" + str);
//
//    }

}
