/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWWL0010.common;

import static business.blap.NWWL0010.constant.NWWL0010Constant.*;
import static business.blap.NWWL0010.constant.NWWL0010Constant.TABLE_NM_NTFY_BIZ_AREA_TP;
import static business.blap.NWWL0010.constant.NWWL0010Constant.TABLE_NM_NTFY_SUB_AREA_TP;

import java.sql.ResultSet;
import java.sql.SQLException;

import parts.common.EZDCMsg;
import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import business.blap.NWWL0010.NWWL0010CMsg;
import business.db.NTFY_SUB_AREA_TPTMsg;
import business.db.NTFY_SUB_AREA_TPTMsgArray;
import business.file.NWWL0010F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;

/**
 *<pre>
 * NWWL0010CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/19   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NWWL0010CommonLogic {

    /**
     * loadOnePageToCMsg
     * @param cMsg EZDCMsg
     * @param cMsgArray EZDCMsgArray
     * @param sMsgArray EZDSMsgArray
     */
    public static void loadOnePageToCMsg(EZDCMsg cMsg, EZDCMsgArray cMsgArray, EZDSMsgArray sMsgArray) {

        NWWL0010CMsg bizMsg = (NWWL0010CMsg) cMsg;

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
     * createPulldownList
     * @param bizMsg NWWL0010CMsg
     * @param glblCmpyCd String
     */
    public static void createPulldownList(NWWL0010CMsg bizMsg, String glblCmpyCd) {

        ZYPCodeDataUtil.createPulldownList(TABLE_NM_NTFY_BIZ_AREA_TP, bizMsg.ntfyBizAreaTpCd_L0, bizMsg.ntfyBizAreaTpDescTxt_L0);

        createNtfySubAreaPulldownList(bizMsg, glblCmpyCd);
    }

    /**
     * Create Notification Sub Area Pull down List
     * @param bizMsg NWWL0010CMsg
     * @param glblCmpyCd String
     */
    public static void createNtfySubAreaPulldownList(NWWL0010CMsg bizMsg, String glblCmpyCd) {

        if (!ZYPCommonFunc.hasValue(bizMsg.ntfyBizAreaTpCd_SL)) {
            ZYPCodeDataUtil.createPulldownList(TABLE_NM_NTFY_SUB_AREA_TP, bizMsg.ntfySubAreaTpCd_L0, bizMsg.ntfySubAreaTpDescTxt_L0);
        } else {

            bizMsg.ntfySubAreaTpCd_L0.clear();
            bizMsg.ntfySubAreaTpDescTxt_L0.clear();

            NTFY_SUB_AREA_TPTMsgArray subAreaList = getNtfySubAreaList(bizMsg, glblCmpyCd);

            for (int i = 0; i < subAreaList.length() && i < bizMsg.ntfySubAreaTpCd_L0.length(); i++) {
                bizMsg.ntfySubAreaTpCd_L0.no(i).setValue(subAreaList.no(i).ntfySubAreaTpCd.getValue());
                bizMsg.ntfySubAreaTpDescTxt_L0.no(i).setValue(subAreaList.no(i).ntfySubAreaTpDescTxt.getValue());
            }
        }
    }

    /**
     * Get Notification Sub Area List
     * @param bizMsg NWWL0010CMsg
     * @param glblCmpyCd String
     * @return NTFY_SUB_AREA_TPTMsgArray
     */
    private static NTFY_SUB_AREA_TPTMsgArray getNtfySubAreaList(NWWL0010CMsg bizMsg, String glblCmpyCd) {
        NTFY_SUB_AREA_TPTMsg subAreaTMsg = new NTFY_SUB_AREA_TPTMsg();
        ZYPEZDItemValueSetter.setValue(subAreaTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(subAreaTMsg.ntfyBizAreaTpCd, bizMsg.ntfyBizAreaTpCd_SL);

        return (NTFY_SUB_AREA_TPTMsgArray) S21CodeTableAccessor.findByCondition(subAreaTMsg);
    }

    /**
     * Create Notification Sub Area Pull down List
     * @param bizMsg NWWL0010CMsg
     * @param rs ResultSet
     * @throws SQLException SQLException
     */
    public static void writeCsvFileContInfo(NWWL0010CMsg bizMsg, ResultSet rs) throws SQLException {

        NWWL0010F00FMsg fMsg = new NWWL0010F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);

        // write header
        csvOutFile.writeHeader(CSV_HDR);

        // write contents
        do {
            ZYPEZDItemValueSetter.setValue(fMsg.ntfyHdrId, rs.getString("NTFY_HDR_ID"));
            ZYPEZDItemValueSetter.setValue(fMsg.ntfyHdrNm, rs.getString("NTFY_HDR_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.ntfyHdrDescTxt, rs.getString("NTFY_HDR_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.ntfyBizAreaTpNm, rs.getString("NTFY_BIZ_AREA_TP_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.ntfySubAreaTpNm, rs.getString("NTFY_SUB_AREA_TP_NM"));

            String rsEffFromDt = rs.getString("EFF_FROM_DT");
            if (ZYPCommonFunc.hasValue(rsEffFromDt)) {
                rsEffFromDt = ZYPDateUtil.DateFormatter(rsEffFromDt, DATE_FORMAT_YYYYMMDD, DATE_FORMAT_MM_DD_YYYY);
            }

            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_FR, rsEffFromDt);

            String rsEffThruDt = rs.getString("EFF_THRU_DT");
            if (ZYPCommonFunc.hasValue(rsEffThruDt)) {
                rsEffThruDt = ZYPDateUtil.DateFormatter(rsEffThruDt, DATE_FORMAT_YYYYMMDD, DATE_FORMAT_MM_DD_YYYY);
            }

            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_TH, rsEffThruDt);

            csvOutFile.write();
            fMsg.clear();
        } while (rs.next());

        csvOutFile.close();
    }
}
