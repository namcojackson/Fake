/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0870.common;

import static business.blap.NSAL0870.constant.NSAL0870Constant.LIMIT_DOWNLOAD;

import java.sql.ResultSet;
import java.sql.SQLException;

import parts.common.EZDCItem;
import parts.common.EZDMsg;
import business.blap.NSAL0870.NSAL0870CMsg;
import business.blap.NSAL0870.NSAL0870Query;
import business.blap.NSAL0870.NSAL0870SMsg;
import business.file.NSAL0870F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_READ_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Meter Interface Status Inquiry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/17   Hitachi         T.Mizuki        Create          N/A
 * 2017/02/06   Hitachi         N.Arai          Update          QC#17197
 *</pre>
 */
public class NSAL0870CommonLogic {

    /**
     * Create Pull Down
     * @param cMsg NSAL0870CMsg
     */
    public static void createPullDown(NSAL0870CMsg cMsg) {
        ZYPCodeDataUtil.createPulldownList(MTR_READ_SRC_TP.class, cMsg.mtrReadSrcTpCd_1C, cMsg.mtrReadSrcTpDescTxt_1D);
        ZYPCodeDataUtil.createPulldownList(DS_MTR_PROC_STS.class, cMsg.dsMtrProcStsCd_1C, cMsg.dsMtrProcStsDescTxt_1D);

    }

    /**
     * clearMsg
     * @param cMsg NSAL0870CMsg
     * @param sMsg NSAL0870SMsg
     */
    public static void clearMsg(NSAL0870CMsg cMsg, NSAL0870SMsg sMsg) {

        cMsg.dsMtrProcStsCd_1V.clear();
        cMsg.mtrReadSrcTpCd_1V.clear();
        cMsg.serNum_01.clear();
        cMsg.mtrReadDt_FR.clear();
        cMsg.mtrReadDt_TO.clear();
        cMsg.dsMtrProcStsCd_H.clear();
        cMsg.mtrReadDt_FH.clear();
        cMsg.mtrReadDt_TH.clear();
        cMsg.mtrReadSrcTpCd_H.clear();
        cMsg.serNum_H.clear();
        ZYPTableUtil.clear(cMsg.A);
        cMsg.xxPageShowFromNum.clear();
        cMsg.xxPageShowToNum.clear();
        cMsg.xxPageShowOfNum.clear();

        ZYPTableUtil.clear(sMsg.A);

        pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    /**
     * setPagenation <dd>The method explanation: The business
     * peculiarity processing is executed.
     * @param cMsg NSAL0870CMsg
     * @param sMsg NSAL0870SMsg
     * @param pagenationFrom int
     */
    public static void setPagenation(NSAL0870CMsg cMsg, NSAL0870SMsg sMsg, int pagenationFrom) {

        int cnt = pagenationFrom;
        for (; cnt < pagenationFrom + cMsg.A.length(); cnt++) {
            if (cnt < pagenationFrom + cMsg.A.getValidCount()) {
                EZDMsg.copy(cMsg.A.get(cnt - pagenationFrom), null, sMsg.A.get(cnt), null);
            } else {
                break;
            }
        }
    }

    /**
     * pagenation <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param pageFrom int
     */
    public static void pagenation(NSAL0870CMsg cMsg, NSAL0870SMsg sMsg, int pageFrom) {

        int i = pageFrom;
        for (; i < pageFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(i - pageFrom), null);
            } else {
                break;
            }
        }

        cMsg.A.setValidCount(i - pageFrom);
        cMsg.xxPageShowFromNum.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum.setValue(pageFrom + cMsg.A.getValidCount());
    }

    /**
     * HasValue Search Condition
     * @param cMsg NSAL0870CMsg
     * @return boolean
     */
    public static boolean hasValueSearchCondition(NSAL0870CMsg cMsg) {

        if (hasValueItems(cMsg.mtrReadSrcTpCd_H, cMsg.serNum_H, cMsg.dsMtrProcStsCd_H, cMsg.mtrReadDt_FH, cMsg.mtrReadDt_TH)) {
            return true;
        }
        return false;
    }

    /**
     * hasValueItems
     * @param items EZDCItem
     * @return boolean
     */
    public static boolean hasValueItems(EZDCItem... items) {

        for (EZDCItem item : items) {
            if (ZYPCommonFunc.hasValue(item)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get Search Data
     * @param cMsg NSAL0780CMsg
     * @param sMsg NSAL0780SMsg
     */
    public static void getSearchData(NSAL0870CMsg cMsg, NSAL0870SMsg sMsg) {

        S21SsmEZDResult ssmResult = NSAL0870Query.getInstance().getSearchData(cMsg, sMsg, sMsg.A.length() + 1);
        if (ssmResult.isCodeNormal()) {
            // Result > 1000
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo("NZZM0001W");
            } else {
                cMsg.setMessageInfo("NZZM0002I");
            }
        } else {
            // No result
            cMsg.setMessageInfo("ZZZM9001E");
        }
    }

    /**
     * writeCsvFile
     * @param cMsg NSAL0870CMsg
     * @param rs ResultSet
     * @throws SQLException SQLException
     */
    public static void writeCsvFile(NSAL0870CMsg cMsg, ResultSet rs) throws SQLException {

        NSAL0870F00FMsg fMsg = new NSAL0870F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(cMsg));
        fMsg.addExclusionItem("xxChkBox_D1");

        //write header
        final String[] csvHeader = new String[] {"Serial#", "Model Name", "Branch", "Meter Source", "Meter Label Name", "Meter Read Date", "Message", "Status", "Process Date", "Process Time", "Exec User ID" };
        csvOutFile.writeHeader(csvHeader, fMsg, ZYPGUITableColumn.getColOrder(cMsg));

        if (!rs.next()) {
            cMsg.setMessageInfo("ZZZM9001E");
            csvOutFile.close();
            return;
        }

        //write contents
        do {
            if (rs.getRow() >= LIMIT_DOWNLOAD) {
                cMsg.setMessageInfo("NZZM0001W");
                break;
            }

            //resultSet -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.serNum_A,  rs.getString("SER_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.t_MdlNm_A, rs.getString("T_MDL_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.svcContrBrDescTxt_A, rs.getString("SVC_CONTR_BR_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.mtrReadSrcTpDescTxt_A, rs.getString("MTR_READ_SRC_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.mtrLbDescTxt_A, rs.getString("MTR_LB_DESC_TXT"));

            String mtrReadDt = null;
            mtrReadDt = ZYPDateUtil.formatEzd8ToDisp(rs.getString("MTR_READ_DT"));

         // START 2017/02/06 N.Arai [QC#17197, MOD]
            // ZYPEZDItemValueSetter.setValue(fMsg.mtrReadDt_A, mtrReadDt);
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_A, mtrReadDt);
         // END 2017/02/06 N.Arai [QC#17197, MOD]
            ZYPEZDItemValueSetter.setValue(fMsg.dsMsgTxt_A, rs.getString("DS_MSG_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.dsMtrProcStsDescTxt_A, rs.getString("DS_MTR_PROC_STS_DESC_TXT"));

            String yyyymmdd3 = null;
            String hhmmsss = null;

            if (ZYPCommonFunc.hasValue(rs.getString("PROC_TS"))) {
                yyyymmdd3 = ZYPDateUtil.formatEzd8ToDisp(rs.getString("PROC_TS").substring(0, 8));
                hhmmsss = formathhmmss(rs.getString("PROC_TS").substring(8, 14));
            }

            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTm_A1, yyyymmdd3);
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTm_A2, hhmmsss);
            ZYPEZDItemValueSetter.setValue(fMsg.rgtnUsrId_A, rs.getString("RGTN_USR_ID"));

            csvOutFile.write();
        } while (rs.next());

        csvOutFile.close();

    }

    /**
     * 
     * @param sMsg NSAL0870BMsg
     */
    public static final void formatDsMtrProcTs(NSAL0870SMsg sMsg) {

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).dsMtrProcTs_A1)) {

                String yyyymmdd2 = ZYPDateUtil.formatEzd8ToDisp(sMsg.A.no(i).dsMtrProcTs_A1.getValue().substring(0, 8));
                String hhmmss = null;
                hhmmss = formathhmmss(sMsg.A.no(i).dsMtrProcTs_A1.getValue().substring(8, 14));

                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxDtTm_A1, yyyymmdd2);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxDtTm_A2, hhmmss);
            }
        }
    }

    /**
     * 
     * @param str String
     * @return String
     */
    public static final String formathhmmss(String str) {

        StringBuilder hhmmsss = new StringBuilder(8);
        hhmmsss.append(str.substring(0, 2));
        hhmmsss.append(':');
        hhmmsss.append(str.substring(2, 4));
        hhmmsss.append(':');
        hhmmsss.append(str.substring(4, 6));

        String hhmmss2 = hhmmsss.toString();

        return hhmmss2;
    }
}
