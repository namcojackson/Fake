/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2720.common;

import static business.blap.NMAL2720.constant.NMAL2720Constant.CSV_HEADER_NUM;
import static business.blap.NMAL2720.constant.NMAL2720Constant.CSV_HEADER_CONST_NM_CMNT;
import static business.blap.NMAL2720.constant.NMAL2720Constant.CSV_HEADER_CONST_NM_CUR_ORG_NM;
import static business.blap.NMAL2720.constant.NMAL2720Constant.CSV_HEADER_CONST_NM_CUR_PSN_NUM;
import static business.blap.NMAL2720.constant.NMAL2720Constant.CSV_HEADER_CONST_NM_MOVE_EFF_FROM;
import static business.blap.NMAL2720.constant.NMAL2720Constant.CSV_HEADER_CONST_NM_MOVE_EFF_TO;
import static business.blap.NMAL2720.constant.NMAL2720Constant.CSV_HEADER_CONST_NM_MOVE_PSN_NUM;
import static business.blap.NMAL2720.constant.NMAL2720Constant.NMAM8433E;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.internal.calendar.S21CalendarUtil;

import business.blap.NMAL2720.NMAL2720CMsg;
import business.file.NMAL2720F01FMsg;

/**
 *<pre>
 * NMAL2720CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/22   Fujitsu         M.Nakamura      Create          N/A
 *</pre>
 */
public class NMAL2720CommonLogic {

    /**
     * writeCsvFile
     * @param bizMsg NMAL2720CMsg
     * @param rs ResultSet
     * @param glblCmpyCd String
     * @throws SQLException SQLException
     */
    public static void writeCsvFile(NMAL2720CMsg bizMsg, ResultSet rs, String glblCmpyCd) throws SQLException {

        NMAL2720F01FMsg fMsg = new NMAL2720F01FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);

        String[] csvHeader = createCsvFileHeaderName(glblCmpyCd);
        if (!checkCsvHeader(csvHeader)) {
            bizMsg.setMessageInfo(NMAM8433E, new String[] {"CSV Header" });
            return;
        }

        csvOutFile.writeHeader(csvHeader);
        String massUpdRsnCmntTxt = null;
        while (rs.next()) {

            fMsg.clear();

            // resultSet+bizMsg -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.orgNm, rs.getString("ORG_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.curPsnNum, rs.getString("PSN_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.movePsnNum, bizMsg.psnNum_D1);
            if (ZYPCommonFunc.hasValue(bizMsg.effFromDt_D1)) {
                ZYPEZDItemValueSetter.setValue(fMsg.moveEffFromDtTxt, ZYPDateUtil.convertFormat(bizMsg.effFromDt_D1.getValue(), ZYPDateUtil.TYPE_YYYYMMDD, ZYPDateUtil.getJavaDateFormatString(), S21CalendarUtil.SEPARATOR_SLASH));
            } else {
                fMsg.moveEffFromDtTxt.clear();
            }
            if (ZYPCommonFunc.hasValue(bizMsg.effThruDt_D1)) {
                ZYPEZDItemValueSetter.setValue(fMsg.moveEffThruDtTxt, ZYPDateUtil.convertFormat(bizMsg.effThruDt_D1.getValue(), ZYPDateUtil.TYPE_YYYYMMDD, ZYPDateUtil.getJavaDateFormatString(), S21CalendarUtil.SEPARATOR_SLASH));
            } else {
                fMsg.moveEffThruDtTxt.clear();
            }

            massUpdRsnCmntTxt = replaceLineFeed(bizMsg.massUpdRsnCmntTxt_D1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.massUpdRsnCmntTxt, massUpdRsnCmntTxt);
            csvOutFile.write();
        }

        csvOutFile.close();
    }

    private static String replaceLineFeed(String cmntTxt) {
        if (!ZYPCommonFunc.hasValue(cmntTxt)) {
            return "";
        }

        return ZYPCommonFunc.replaceAll(cmntTxt, "\r\n", " ");
    }

    /**
     * createCsvFileHeaderName.
     * @param glblCmpyCd String
     * @return String[]
     */
    public static String[] createCsvFileHeaderName(String glblCmpyCd) {
        List<String> hdrElementList = new ArrayList<String>();
        hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue(CSV_HEADER_CONST_NM_CUR_ORG_NM, glblCmpyCd));
        hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue(CSV_HEADER_CONST_NM_CUR_PSN_NUM, glblCmpyCd));
        hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue(CSV_HEADER_CONST_NM_MOVE_PSN_NUM, glblCmpyCd));
        hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue(CSV_HEADER_CONST_NM_MOVE_EFF_FROM, glblCmpyCd));
        hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue(CSV_HEADER_CONST_NM_MOVE_EFF_TO, glblCmpyCd));
        hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue(CSV_HEADER_CONST_NM_CMNT, glblCmpyCd));

        return hdrElementList.toArray(new String[hdrElementList.size()]);
    }

    /**
     * checkCsvHeader
     * @param csvHeader String[]
     * @return boolean
     */
    public static boolean checkCsvHeader(String[] csvHeader) {
        if (csvHeader.length == CSV_HEADER_NUM) {
            for (int i = 0; i < csvHeader.length; i++) {
                if (!ZYPCommonFunc.hasValue(csvHeader[i])) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }
}
