/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWWL0040.common;

import static business.blap.NWWL0040.constant.NWWL0040Constant.CSV_HDR;
import static business.blap.NWWL0040.constant.NWWL0040Constant.DATE_FORMAT_MM_DD_YYYY;
import static business.blap.NWWL0040.constant.NWWL0040Constant.DATE_FORMAT_YYYYMMDD;
import static business.blap.NWWL0040.constant.NWWL0040Constant.SUB_AREA_TP_TBL_NM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import business.blap.NWWL0040.NWWL0040CMsg;
import business.blap.NWWL0040.NWWL0040SMsg;
import business.blap.NWWL0040.NWWL0040_ASMsg;
import business.db.NTFY_SUB_AREA_TPTMsg;
import business.db.NTFY_SUB_AREA_TPTMsgArray;
import business.file.NWWL0040F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;

/**
 *<pre>
 * NWWL0040CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/27   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NWWL0040CommonLogic {

    /**
     * loadOnePageToCMsg
     * @param cMsg EZDCMsg
     * @param cMsgArray EZDCMsgArray
     * @param sMsgArray EZDSMsgArray
     */
    public static void loadOnePageToCMsg(EZDCMsg cMsg, EZDCMsgArray cMsgArray, EZDSMsgArray sMsgArray) {

        NWWL0040CMsg bizMsg = (NWWL0040CMsg) cMsg;

        ZYPTableUtil.clear(cMsgArray);

        bizMsg.xxPageShowToNum.clear();
        bizMsg.xxPageShowOfNum.clear();

        int maxDisplayRows = cMsgArray.length();
        int startIndex = (bizMsg.xxPageShowFromNum.getValueInt() / maxDisplayRows) * maxDisplayRows;

        int i = startIndex;
        for (; i < startIndex + cMsgArray.length(); i++) {

            if (i < sMsgArray.getValidCount()) {

                EZDMsg sLineMsg = sMsgArray.get(i);
                int indexOfCMsg = i - startIndex;

                EZDMsg.copy(sLineMsg, null, cMsgArray.get(indexOfCMsg), null);

            } else {

                break;
            }
        }

        cMsgArray.setValidCount(i - startIndex);
        bizMsg.xxPageShowFromNum.setValue(startIndex + 1);
        bizMsg.xxPageShowToNum.setValue(startIndex + cMsgArray.getValidCount());
        bizMsg.xxPageShowOfNum.setValue(sMsgArray.getValidCount());
    }

    /**
     * setSeachResult
     * @param glblMsg NWWL0040SMsg
     * @param distListList List<Map<String, String>>
     */
    public static void setSeachResult(NWWL0040SMsg glblMsg, List<Map<String, String>> distListList) {
        int i = 0;
        for (Map<String, String> distList : distListList) {
            NWWL0040_ASMsg glblAMsg = glblMsg.A.no(i);
            ZYPEZDItemValueSetter.setValue(glblAMsg.ntfyDistListId_A, (String) distList.get("NTFY_DIST_LIST_ID"));
            ZYPEZDItemValueSetter.setValue(glblAMsg.ntfyDistListNm_A, (String) distList.get("NTFY_DIST_LIST_NM"));
            ZYPEZDItemValueSetter.setValue(glblAMsg.ntfyDistListDescTxt_A, (String) distList.get("NTFY_DIST_LIST_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(glblAMsg.ntfyBizAreaTpDescTxt_A, (String) distList.get("NTFY_BIZ_AREA_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(glblAMsg.ntfySubAreaTpDescTxt_A, (String) distList.get("NTFY_SUB_AREA_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(glblAMsg.effFromDt_A, (String) distList.get("EFF_FROM_DT"));
            ZYPEZDItemValueSetter.setValue(glblAMsg.effThruDt_A, (String) distList.get("EFF_THRU_DT"));
            i++;

            if (i >= glblMsg.A.length()) {
                break;
            }
        }
    }

    /**
     * Create Notification Sub Area Pull down List For Dist List
     * @param bizMsg NWWL0040CMsg
     * @param glblCmpyCd String
     */
    public static void createNtfySubAreaPulldownListForDist(NWWL0040CMsg bizMsg, String glblCmpyCd) {

        if (!ZYPCommonFunc.hasValue(bizMsg.ntfyBizAreaTpCd_D)) {
            ZYPCodeDataUtil.createPulldownList(SUB_AREA_TP_TBL_NM, bizMsg.ntfySubAreaTpCd_DP, bizMsg.ntfySubAreaTpDescTxt_DP);
        } else {

            bizMsg.ntfySubAreaTpCd_DP.clear();
            bizMsg.ntfySubAreaTpDescTxt_DP.clear();

            NTFY_SUB_AREA_TPTMsgArray subAreaList = getNtfySubAreaList(bizMsg.ntfyBizAreaTpCd_D.getValue(), glblCmpyCd);

            for (int i = 0; i < subAreaList.length() && i < bizMsg.ntfySubAreaTpCd_DP.length(); i++) {
                bizMsg.ntfySubAreaTpCd_DP.no(i).setValue(subAreaList.no(i).ntfySubAreaTpCd.getValue());
                bizMsg.ntfySubAreaTpDescTxt_DP.no(i).setValue(subAreaList.no(i).ntfySubAreaTpDescTxt.getValue());
            }
        }
    }

    /**
     * Create Notification Sub Area Pull down List For Notif List
     * @param bizMsg NWWL0040CMsg
     * @param glblCmpyCd String
     */
    public static void createNtfySubAreaPulldownListForNtfy(NWWL0040CMsg bizMsg, String glblCmpyCd) {

        if (!ZYPCommonFunc.hasValue(bizMsg.ntfyBizAreaTpCd_N)) {
            ZYPCodeDataUtil.createPulldownList(SUB_AREA_TP_TBL_NM, bizMsg.ntfySubAreaTpCd_NP, bizMsg.ntfySubAreaTpDescTxt_NP);
        } else {

            bizMsg.ntfySubAreaTpCd_NP.clear();
            bizMsg.ntfySubAreaTpDescTxt_NP.clear();

            NTFY_SUB_AREA_TPTMsgArray subAreaList = getNtfySubAreaList(bizMsg.ntfyBizAreaTpCd_N.getValue(), glblCmpyCd);

            for (int i = 0; i < subAreaList.length() && i < bizMsg.ntfySubAreaTpCd_NP.length(); i++) {
                bizMsg.ntfySubAreaTpCd_NP.no(i).setValue(subAreaList.no(i).ntfySubAreaTpCd.getValue());
                bizMsg.ntfySubAreaTpDescTxt_NP.no(i).setValue(subAreaList.no(i).ntfySubAreaTpDescTxt.getValue());
            }
        }
    }

    /**
     * Get Notification Sub Area List
     * @param ntfyBizAreaTpCd String
     * @param glblCmpyCd String
     * @return NTFY_SUB_AREA_TPTMsgArray
     */
    private static NTFY_SUB_AREA_TPTMsgArray getNtfySubAreaList(String ntfyBizAreaTpCd, String glblCmpyCd) {
        NTFY_SUB_AREA_TPTMsg subAreaTMsg = new NTFY_SUB_AREA_TPTMsg();
        ZYPEZDItemValueSetter.setValue(subAreaTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(subAreaTMsg.ntfyBizAreaTpCd, ntfyBizAreaTpCd);

        return (NTFY_SUB_AREA_TPTMsgArray) S21CodeTableAccessor.findByCondition(subAreaTMsg);
    }

    /**
     * Create writeCsvFileContInfo
     * @param bizMsg NWWL0040CMsg
     * @param rs ResultSet
     * @throws SQLException SQLException
     */
    public static void writeCsvFileContInfo(NWWL0040CMsg bizMsg, ResultSet rs) throws SQLException {

        NWWL0040F00FMsg fMsg = new NWWL0040F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData_A.getTempFilePath(), fMsg);

        // write header
        csvOutFile.writeHeader(CSV_HDR);

        // write contents
        do {
            ZYPEZDItemValueSetter.setValue(fMsg.ntfyDistListId, rs.getString("NTFY_DIST_LIST_ID"));
            ZYPEZDItemValueSetter.setValue(fMsg.ntfyDistListNm, rs.getString("NTFY_DIST_LIST_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.ntfyDistListDescTxt, rs.getString("NTFY_DIST_LIST_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.ntfyBizAreaTpDescTxt, rs.getString("NTFY_BIZ_AREA_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.ntfySubAreaTpDescTxt, rs.getString("NTFY_SUB_AREA_TP_DESC_TXT"));

            String fromDt = rs.getString("EFF_FROM_DT");
            if (ZYPCommonFunc.hasValue(fromDt)) {
                fromDt = ZYPDateUtil.DateFormatter(fromDt, DATE_FORMAT_YYYYMMDD, DATE_FORMAT_MM_DD_YYYY);
            }

            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_FR, fromDt);

            String thruDt = rs.getString("EFF_THRU_DT");
            if (ZYPCommonFunc.hasValue(thruDt)) {
                thruDt = ZYPDateUtil.DateFormatter(thruDt, DATE_FORMAT_YYYYMMDD, DATE_FORMAT_MM_DD_YYYY);
            }

            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_TH, thruDt);

            csvOutFile.write();
            fMsg.clear();
        } while (rs.next());

        csvOutFile.close();
    }
}
