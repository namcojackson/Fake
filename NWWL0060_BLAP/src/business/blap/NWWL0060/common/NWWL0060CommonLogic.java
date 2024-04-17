/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWWL0060.common;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;

import static business.blap.NWWL0060.constant.NWWL0060Constant.*;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import business.blap.NWWL0060.NWWL0060CMsg;
import business.blap.NWWL0060.NWWL0060SMsg;
import business.blap.NWWL0060.NWWL0060_ASMsg;

/**
 *<pre>
 * NWWL0060CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/05   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NWWL0060CommonLogic {

    /**
     * loadOnePageToCMsg
     * @param cMsg EZDCMsg
     * @param cMsgArray EZDCMsgArray
     * @param sMsgArray EZDSMsgArray
     */
    public static void loadOnePageToCMsg(EZDCMsg cMsg, EZDCMsgArray cMsgArray, EZDSMsgArray sMsgArray) {

        NWWL0060CMsg bizMsg = (NWWL0060CMsg) cMsg;

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
     * Update the global Message.
     * @param bizMsg NWWL0060CMsg
     * @param glblMsg NWWL0060SMsg
     */
    public static void updateGlblMsg(NWWL0060CMsg bizMsg, NWWL0060SMsg glblMsg) {

        int ixG = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            EZDMsg.copy(bizMsg.A.no(i), null, glblMsg.A.no(ixG + i), null);
        }
    }

    /**
     * setNtfySbscr
     * @param bizMsg NWWL0060CMsg
     * @param glblMsg NWWL0060SMsg
     * @param ntfySbscrList List<Map<String, Object>>
     */
    public static void setNtfySbscr(NWWL0060CMsg bizMsg, NWWL0060SMsg glblMsg, List<Map<String, Object>> ntfySbscrList) {

        int i = 0;
        String befNtfyId = "";
        StringBuilder distListTxt = new StringBuilder();

        for (int j = 0; j < ntfySbscrList.size(); j++) {

            if (ZYPCommonFunc.hasValue(befNtfyId) && !befNtfyId.equals(ntfySbscrList.get(j).get("NTFY_HDR_ID"))) {
                Map<String, Object> ntfySbscr = ntfySbscrList.get(j - 1);

                setGlblMsg(glblMsg.A.no(i), ntfySbscr, distListTxt);

                distListTxt.delete(0, distListTxt.length());
                distListTxt.append(ntfySbscrList.get(j).get("NTFY_DIST_LIST_NM"));
                i++;

                if (i >= glblMsg.A.length()) {
                    bizMsg.setMessageInfo(NZZM0001W);
                    glblMsg.A.setValidCount(glblMsg.A.length());
                    break;
                }

            } else {

                if (DIST_LIST_NM_MAX_LENGTH >= distListTxt.length() + ((String) ntfySbscrList.get(j).get("NTFY_DIST_LIST_NM")).length() + i) {

                    if (distListTxt.length() > 0) {
                        distListTxt.append(",");
                    }
                    distListTxt.append(ntfySbscrList.get(j).get("NTFY_DIST_LIST_NM"));
                }
            }

            befNtfyId = (String) ntfySbscrList.get(j).get("NTFY_HDR_ID");

        }

        if (i < glblMsg.A.length()) {
            Map<String, Object> ntfySbscr = ntfySbscrList.get(ntfySbscrList.size() - 1);
            setGlblMsg(glblMsg.A.no(i), ntfySbscr, distListTxt);

            i++;

            glblMsg.A.setValidCount(i);
        }

        NWWL0060CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
        EZDMsg.copy(glblMsg.A, null, glblMsg.B, null);
    }

    private static void setGlblMsg(NWWL0060_ASMsg glblAMsg, Map<String, Object> ntfySbscr, StringBuilder distListTxt) {
        ZYPEZDItemValueSetter.setValue(glblAMsg.ntfySbscrPk_A, (BigDecimal) ntfySbscr.get("NTFY_SBSCR_PK"));
        ZYPEZDItemValueSetter.setValue(glblAMsg.ntfyHdrPk_A, (BigDecimal) ntfySbscr.get("NTFY_HDR_PK"));
        ZYPEZDItemValueSetter.setValue(glblAMsg.ntfyHdrId_A, (String) ntfySbscr.get("NTFY_HDR_ID"));
        ZYPEZDItemValueSetter.setValue(glblAMsg.ntfyHdrNm_A, (String) ntfySbscr.get("NTFY_HDR_NM"));
        ZYPEZDItemValueSetter.setValue(glblAMsg.ntfyHdrDescTxt_A, (String) ntfySbscr.get("NTFY_HDR_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(glblAMsg.ntfyBizAreaTpDescTxt_A, (String) ntfySbscr.get("NTFY_BIZ_AREA_TP_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(glblAMsg.ntfySubAreaTpDescTxt_A, (String) ntfySbscr.get("NTFY_SUB_AREA_TP_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(glblAMsg.ntfyDistListNmListTxt_A, distListTxt.toString());
        ZYPEZDItemValueSetter.setValue(glblAMsg.ezUpTime_A, (String) ntfySbscr.get("EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(glblAMsg.ezUpTimeZone_A, (String) ntfySbscr.get("EZUPTIMEZONE"));

        if (!ZYPCommonFunc.hasValue((String) ntfySbscr.get("NTFY_SBSCR_FLG")) || ZYPConstant.FLG_ON_Y.equals(ntfySbscr.get("NTFY_SBSCR_FLG"))) {
            ZYPEZDItemValueSetter.setValue(glblAMsg.ntfySbscrFlg_A, ZYPConstant.FLG_ON_Y);

        } else {
            glblAMsg.ntfySbscrFlg_A.clear();

        }
    }

    /**
     * Changed Value Exist Check
     * @param str1 String
     * @param str2 String
     * @return boolean
     */
    public static Boolean checkChangeValue(String str1, String str2) {
        if (str1 == null) {
            if (str2 == null) {
                return false;
            } else {
                return true;
            }
        } else if (str1.equals(str2)) {
            return false;
        } else {
            return true;
        }
    }
}
