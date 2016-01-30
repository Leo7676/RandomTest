package com.leo;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ExcelOperate {

    public ArrayList<String> getDataList(){
        ArrayList<String> list=new ArrayList<String>();
        File file = new File("spl.xls");
        String[][] result = null;
        try {
            result = getData(file, 0);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
           int rowLength = result.length;
//           System.out.println("rowLength: "+rowLength);
           for(int i=0;i<rowLength;i++) {
//               System.out.println("Row: "+i);
//               System.out.println("---------------------------- Row Start --------------------------");
               for(int j=0;j<result[i].length;j++) {
                   if(j==1){
//                       System.out.println(result[i][j]+"");
                       list.add(i+"~"+result[i][j]);
                   }

//                  System.out.println("Cloumn: "+j);
//                  System.out.println("---------------------------- Cloumn --------------------------");
               }
//               System.out.println();
//               System.out.println("---------------------------- Row End --------------------------");
           }
           return list;
    }

    public static void main(String[] args) throws Exception {
        ArrayList<String> list=new ArrayList<String>();
       File file = new File(RandomTest_UI.textField_import_path.getText().trim());
//       File file = new File("ExcelDemo.xls");
       String[][] result = getData(file, 0);
       int rowLength = result.length;
       System.out.println("rowLength: "+rowLength);
       for(int i=0;i<rowLength;i++) {
           System.out.println("Row: "+i);
           System.out.println("---------------------------- Row Start --------------------------");
           for(int j=0;j<result[i].length;j++) {
               if(j==1){
                   System.out.println(result[i][j]+"");
                   list.add(i+"$"+result[i][j]);
               }

//              System.out.println("Cloumn: "+j);
//              System.out.println("---------------------------- Cloumn --------------------------");
           }
//           System.out.println();
           System.out.println("---------------------------- Row End --------------------------");
       }
       System.out.println("---------------------------- Sepator --------------------------");
//       System.out.println(result[0][1]);

//       for(int i=1;i<list.size();i++){
//           System.out.println(list.get(i));
//       }


    }
    /**
     * 读取Excel的内容，第一维数组存储的是一行中格列的值，二维数组存储的是多少个行
     * @param file 读取数据的源Excel
     * @param ignoreRows 读取数据忽略的行数，比喻行头不需要读入 忽略的行数为1
     * @return 读出的Excel中数据的内容
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String[][] getData(File file, int ignoreRows)
           throws FileNotFoundException, IOException {
       List<String[]> result = new ArrayList<String[]>();
       int rowSize = 0;
       BufferedInputStream in = new BufferedInputStream(new FileInputStream(
              file));
       // 打开HSSFWorkbook
       POIFSFileSystem fs = new POIFSFileSystem(in);
       HSSFWorkbook wb = new HSSFWorkbook(fs);
       HSSFCell cell = null;
       System.out.println("wb.getNumberOfSheets(): "+wb.getNumberOfSheets());
       for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
           HSSFSheet st = wb.getSheetAt(sheetIndex);
           // 第一行为标题，不取
           for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {
              HSSFRow row = st.getRow(rowIndex);
              if (row == null) {
                  continue;
              }
              int tempRowSize = row.getLastCellNum() + 1;
              if (tempRowSize > rowSize) {
                  rowSize = tempRowSize;
              }
              String[] values = new String[rowSize];
              Arrays.fill(values, "");
              boolean hasValue = false;
              for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
                  String value = "";
                  cell = row.getCell(columnIndex);
                  if (cell != null) {
                     // 注意：一定要设成这个，否则可能会出现乱码
//                     cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                     switch (cell.getCellType()) {
                     case HSSFCell.CELL_TYPE_STRING:
                         value = cell.getStringCellValue();
                         break;
                     case HSSFCell.CELL_TYPE_NUMERIC:
                         if (HSSFDateUtil.isCellDateFormatted(cell)) {
                            Date date = cell.getDateCellValue();
                            if (date != null) {
                                value = new SimpleDateFormat("yyyy-MM-dd")
                                       .format(date);
                            } else {
                                value = "";
                            }
                         } else {
                            value = new DecimalFormat("0").format(cell
                                   .getNumericCellValue());
                         }
                         break;
                     case HSSFCell.CELL_TYPE_FORMULA:
                         // 导入时如果为公式生成的数据则无值
                         if (!cell.getStringCellValue().equals("")) {
                            value = cell.getStringCellValue();
                         } else {
                            value = cell.getNumericCellValue() + "";
                         }
                         break;
                     case HSSFCell.CELL_TYPE_BLANK:
                         break;
                     case HSSFCell.CELL_TYPE_ERROR:
                         value = "";
                         break;
                     case HSSFCell.CELL_TYPE_BOOLEAN:
                         value = (cell.getBooleanCellValue() == true ? "Y"
                                : "N");
                         break;
                     default:
                         value = "";
                     }
                  }
                  if (columnIndex == 0 && value.trim().equals("")) {
                     break;
                  }
                  values[columnIndex] = rightTrim(value);
                  hasValue = true;
              }

              if (hasValue) {
                  result.add(values);
              }
           }
       }
       in.close();
       String[][] returnArray = new String[result.size()][rowSize];
       for (int i = 0; i < returnArray.length; i++) {
           returnArray[i] = (String[]) result.get(i);
       }
       return returnArray;
    }

    /**
     * 去掉字符串右边的空格
     * @param str 要处理的字符串
     * @return 处理后的字符串
     */
     public static String rightTrim(String str) {
       if (str == null) {
           return "";
       }
       int length = str.length();
       for (int i = length - 1; i >= 0; i--) {
           if (str.charAt(i) != 0x20) {
              break;
           }
           length--;
       }
       return str.substring(0, length);
    }
}


