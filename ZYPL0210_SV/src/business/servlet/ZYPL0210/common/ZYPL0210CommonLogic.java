package business.servlet.ZYPL0210.common;

import parts.common.EZDFMsg;

import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;

public class ZYPL0210CommonLogic {

    /**
     * Convert CSV File To Excel File
     * @param tempFilePath
     * @param strClassName FMsg Class Name
     * @return File Path
     */
    public static String convertCsvToExcel(String tempFilePath, String strClassName){
        EZDFMsg toMsg = null;
        try {
            Class fmsgClass = Class.forName(strClassName);
            toMsg = (EZDFMsg) fmsgClass.newInstance();
        } catch (ClassNotFoundException e) {
        } catch (InstantiationException e) {
        } catch (IllegalAccessException e) {
        }
        // Do not apply number format If Exception occur 
        if(toMsg == null){
            return ZYPExcelUtil.csvFileToExcel(tempFilePath);
        }
        // Apply Number format
        return ZYPExcelUtil.csvFileToExcel(tempFilePath, toMsg);
    }

}
