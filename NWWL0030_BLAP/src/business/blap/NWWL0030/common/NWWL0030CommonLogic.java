/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWWL0030.common;

import static business.blap.NWWL0030.constant.NWWL0030Constant.SUB_AREA_TP_TBL_NM;

import java.sql.Clob;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import business.blap.NWWL0030.NWWL0030CMsg;
import business.blap.NWWL0030.NWWL0030SMsg;
import business.blap.NWWL0030.NWWL0030_ASMsg;
import business.blap.NWWL0030.NWWL0030_BSMsg;
import business.db.NTFY_SUB_AREA_TPTMsg;
import business.db.NTFY_SUB_AREA_TPTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;

/**
 *<pre>
 * NWWL0030CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/08   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NWWL0030CommonLogic {

    /**
     * loadOnePageToCMsg
     * @param cMsg EZDCMsg
     * @param cMsgArray EZDCMsgArray
     * @param sMsgArray EZDSMsgArray
     */
    public static void loadOnePageToCMsg(EZDCMsg cMsg, EZDCMsgArray cMsgArray, EZDSMsgArray sMsgArray) {

        NWWL0030CMsg bizMsg = (NWWL0030CMsg) cMsg;

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
     * Create Notification Sub Area Pull down List For Dist List
     * @param bizMsg NWWL0030CMsg
     * @param glblCmpyCd String
     */
    public static void createNtfySubAreaPulldownList(NWWL0030CMsg bizMsg, String glblCmpyCd) {

        if (!ZYPCommonFunc.hasValue(bizMsg.ntfyBizAreaTpCd)) {
            ZYPCodeDataUtil.createPulldownList(SUB_AREA_TP_TBL_NM, bizMsg.ntfySubAreaTpCd_P, bizMsg.ntfySubAreaTpDescTxt_P);
        } else {

            bizMsg.ntfySubAreaTpCd_P.clear();
            bizMsg.ntfySubAreaTpDescTxt_P.clear();

            NTFY_SUB_AREA_TPTMsgArray subAreaList = getNtfySubAreaList(bizMsg.ntfyBizAreaTpCd.getValue(), glblCmpyCd);

            for (int i = 0; i < subAreaList.length() && i < bizMsg.ntfySubAreaTpCd_P.length(); i++) {
                bizMsg.ntfySubAreaTpCd_P.no(i).setValue(subAreaList.no(i).ntfySubAreaTpCd.getValue());
                bizMsg.ntfySubAreaTpDescTxt_P.no(i).setValue(subAreaList.no(i).ntfySubAreaTpDescTxt.getValue());
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
     * setNtfyHist
     * @param bizMsg NWWL0030CMsg
     * @param glblMsg NWWL0030SMsg
     * @param ntfyHistList List<Map<String, String>>
     */
    public static void setNtfyHist(NWWL0030CMsg bizMsg, NWWL0030SMsg glblMsg, List<Map<String, String>> ntfyHistList) {
        int i = 0;
        for (Map<String, String> ntfyHist : ntfyHistList) {
            NWWL0030_ASMsg glblAMsg = glblMsg.A.no(i);

            ZYPEZDItemValueSetter.setValue(glblAMsg.ntfyHdrId_A0, ntfyHist.get("NTFY_HDR_ID"));
            ZYPEZDItemValueSetter.setValue(glblAMsg.ntfyHdrNm_A0, ntfyHist.get("NTFY_HDR_NM"));
            ZYPEZDItemValueSetter.setValue(glblAMsg.ntfyHdrDescTxt_A0, ntfyHist.get("NTFY_HDR_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(glblAMsg.ntfyBizAreaTpDescTxt_A0, ntfyHist.get("NTFY_BIZ_AREA_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(glblAMsg.ntfySubAreaTpDescTxt_A0, ntfyHist.get("NTFY_SUB_AREA_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(glblAMsg.ntfyRunJobId_A0, ntfyHist.get("NTFY_RUN_JOB_ID"));
            ZYPEZDItemValueSetter.setValue(glblAMsg.xxScrItem19Txt_A0, ZYPDateUtil.formatEzd14ToDisp(ntfyHist.get("NTFY_RUN_TS").substring(0, 14)));

            i++;

            if (i >= glblMsg.A.length()) {
                break;
            }
        }

    }

    /**
     * setActioResult
     * @param bizMsg NWWL0030CMsg
     * @param glblMsg NWWL0030SMsg
     * @param actRsltList List<Map<String, String>>
     */
    public static void setActRslt(NWWL0030CMsg bizMsg, NWWL0030SMsg glblMsg, List<Map<String, String>> actRsltList) {
        int i = 0;
        for (Map<String, String> actRslt : actRsltList) {
            NWWL0030_BSMsg glblBMsg = glblMsg.B.no(i);

            ZYPEZDItemValueSetter.setValue(glblBMsg.ntfyHdrId_B0, actRslt.get("NTFY_HDR_ID"));
            ZYPEZDItemValueSetter.setValue(glblBMsg.ntfyRunJobId_B0, actRslt.get("NTFY_RUN_JOB_ID"));
            ZYPEZDItemValueSetter.setValue(glblBMsg.ntfyActId_B0, actRslt.get("NTFY_ACT_ID"));
            ZYPEZDItemValueSetter.setValue(glblBMsg.ntfyActNm_B0, actRslt.get("NTFY_ACT_NM"));
            ZYPEZDItemValueSetter.setValue(glblBMsg.ntfyActDtlHistId_B0, actRslt.get("NTFY_ACT_DTL_HIST_ID"));

            i++;

            if (i >= glblMsg.B.length()) {
                break;
            }
        }

        EZDMsg.copy(glblMsg.B, null, bizMsg.B, null);
    }

    /**
     * setNtfyActDtl
     * @param bizMsg NWWL0030CMsg
     * @param ntfyActDtl Map<String, Object>
     * @throws SQLException SQLException
     */
    public static void setNtfyActDtl(NWWL0030CMsg bizMsg, Map<String, Object> ntfyActDtl) throws SQLException {

        ZYPEZDItemValueSetter.setValue(bizMsg.ntfyActTpDescTxt, (String) ntfyActDtl.get("NTFY_ACT_TP_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.ntfyOtptTpDescTxt, (String) ntfyActDtl.get("NTFY_OTPT_TP_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.ntfyEmlRpyToAddr, (String) ntfyActDtl.get("NTFY_EML_RPY_TO_ADDR"));
        ZYPEZDItemValueSetter.setValue(bizMsg.ntfyEmlToAddr, (String) ntfyActDtl.get("NTFY_EML_TO_ADDR"));
        ZYPEZDItemValueSetter.setValue(bizMsg.ntfyEmlCcAddr, (String) ntfyActDtl.get("NTFY_EML_CC_ADDR"));
        ZYPEZDItemValueSetter.setValue(bizMsg.ntfyEmlBccAddr, (String) ntfyActDtl.get("NTFY_EML_BCC_ADDR"));
        ZYPEZDItemValueSetter.setValue(bizMsg.ntfyDistListNmListTxt, (String) ntfyActDtl.get("NTFY_DIST_LIST_NM_LIST_TXT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.ntfyEmlSubjTxt, (String) ntfyActDtl.get("NTFY_EML_SUBJ_TXT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxNtfyEmlBodyTxt, clobToString((Clob) ntfyActDtl.get("NTFY_EML_BODY_CLOD")));
    }

    private static String clobToString(Clob clob) throws SQLException {
        return clob.getSubString(1, (int) clob.length());
    }

    /**
     * setNtfyActDtl
     * @param bizMsg NWWL0030CMsg
     */
    public static void clearActDtl(NWWL0030CMsg bizMsg) {
        bizMsg.ntfyActTpDescTxt.clear();
        bizMsg.ntfyOtptTpDescTxt.clear();
        bizMsg.ntfyEmlRpyToAddr.clear();
        bizMsg.ntfyEmlToAddr.clear();
        bizMsg.ntfyEmlCcAddr.clear();
        bizMsg.ntfyEmlBccAddr.clear();
        bizMsg.ntfyEmlSubjTxt.clear();
        bizMsg.xxNtfyEmlBodyTxt.clear();
        bizMsg.ntfyDistListNmListTxt.clear();
    }
}
