/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWWL0050.common;

import static business.blap.NWWL0050.constant.NWWL0050Constant.SUB_AREA_TP_TBL_NM;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import business.blap.NWWL0050.NWWL0050CMsg;
import business.blap.NWWL0050.NWWL0050SMsg;
import business.blap.NWWL0050.NWWL0050_ACMsg;
import business.blap.NWWL0050.NWWL0050_ASMsg;
import business.blap.NWWL0050.NWWL0050_BCMsg;
import business.db.NTFY_DIST_LISTTMsg;
import business.db.NTFY_DIST_LIST_ASGTMsg;
import business.db.NTFY_SUB_AREA_TPTMsg;
import business.db.NTFY_SUB_AREA_TPTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;

/**
 *<pre>
 * NWWL0050CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/05   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NWWL0050CommonLogic {

    /**
     * Create Notification Sub Area Pull down List For Dist List
     * @param bizMsg NWWL0050CMsg
     * @param glblCmpyCd String
     */
    public static void createNtfySubAreaPulldownList(NWWL0050CMsg bizMsg, String glblCmpyCd) {

        if (!ZYPCommonFunc.hasValue(bizMsg.ntfyBizAreaTpCd_D)) {
            ZYPCodeDataUtil.createPulldownList(SUB_AREA_TP_TBL_NM, bizMsg.ntfySubAreaTpCd_P, bizMsg.ntfySubAreaTpDescTxt_P);
        } else {

            bizMsg.ntfySubAreaTpCd_P.clear();
            bizMsg.ntfySubAreaTpDescTxt_P.clear();

            NTFY_SUB_AREA_TPTMsgArray subAreaList = getNtfySubAreaList(bizMsg.ntfyBizAreaTpCd_D.getValue(), glblCmpyCd);

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
     * setDistList
     * @param bizMsg NWWL0050CMsg
     * @param distList Map<String, Object>
     * @param glblMsg NWWL0050SMsg
     */
    public static void setDistList(NWWL0050CMsg bizMsg, Map<String, Object> distList, NWWL0050SMsg glblMsg) {
        ZYPEZDItemValueSetter.setValue(bizMsg.ntfyDistListNm, (String) distList.get("NTFY_DIST_LIST_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.ntfyDistListId, (String) distList.get("NTFY_DIST_LIST_ID"));
        ZYPEZDItemValueSetter.setValue(bizMsg.ntfyDistListDescTxt, (String) distList.get("NTFY_DIST_LIST_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.ntfyBizAreaTpCd_D, (String) distList.get("NTFY_BIZ_AREA_TP_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.ntfySubAreaTpCd_D, (String) distList.get("NTFY_SUB_AREA_TP_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.effFromDt_D, (String) distList.get("EFF_FROM_DT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.effThruDt_D, (String) distList.get("EFF_THRU_DT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.ntfyDistListActvFlg, returnFlg((String) distList.get("NTFY_DIST_LIST_ACTV_FLG")));
        ZYPEZDItemValueSetter.setValue(bizMsg.ntfyDistListPk, (BigDecimal) distList.get("NTFY_DIST_LIST_PK"));
        ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime, (String) distList.get("EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone, (String) distList.get("EZUPTIMEZONE"));

        EZDMsg.copy(bizMsg, null, glblMsg, null);
    }

    private static String setFlg(String flg) {
        if (ZYPCommonFunc.hasValue(flg)) {
            return "Y";
        }
        return "N";
    }

    private static String returnFlg(String flg) {
        if (ZYPConstant.FLG_ON_Y.equals(flg)) {
            return "Y";
        }

        return "";
    }

    /**
     * setDistListAsg
     * @param bizMsg NWWL0050CMsg
     * @param distListAsgList Map<String, Object>
     * @param glblMsg NWWL0050SMsg
     */
    public static void setDistListAsg(NWWL0050CMsg bizMsg, List<Map<String, Object>> distListAsgList, NWWL0050SMsg glblMsg) {
        int i = 0;
        for (Map<String, Object> distListAsg : distListAsgList) {
            NWWL0050_ACMsg bizAMsg = bizMsg.A.no(i);
            ZYPEZDItemValueSetter.setValue(bizAMsg.ntfyDistListAsgPk_A, (BigDecimal) distListAsg.get("NTFY_DIST_LIST_ASG_PK"));
            ZYPEZDItemValueSetter.setValue(bizAMsg.ntfyDistQlfyCd_A, (String) distListAsg.get("NTFY_DIST_QLFY_CD"));
            ZYPEZDItemValueSetter.setValue(bizAMsg.ntfyDistListAsgValTxt_A, (String) distListAsg.get("NTFY_DIST_LIST_ASG_VAL_TXT"));
            ZYPEZDItemValueSetter.setValue(bizAMsg.ntfyDistListAsgActvFlg_A, returnFlg((String) distListAsg.get("NTFY_DIST_LIST_ASG_ACTV_FLG")));
            ZYPEZDItemValueSetter.setValue(bizAMsg.ezUpTime_A, (String) distListAsg.get("EZUPTIME"));
            ZYPEZDItemValueSetter.setValue(bizAMsg.ezUpTimeZone_A, (String) distListAsg.get("EZUPTIMEZONE"));
            i++;
        }
        bizMsg.A.setValidCount(i);
        EZDMsg.copy(bizMsg.A, null, glblMsg.A, null);

    }

    /**
     * setNtfyHdr
     * @param bizMsg NWWL0050CMsg
     * @param ntfyHdrList List<Map<String, String>>
     * @param glblMsg NWWL0050SMsg
     */
    public static void setNtfyHdr(NWWL0050CMsg bizMsg, List<Map<String, String>> ntfyHdrList, NWWL0050SMsg glblMsg) {
        int i = 0;
        for (Map<String, String> ntfyHdr : ntfyHdrList) {
            NWWL0050_BCMsg bizBMsg = bizMsg.B.no(i);
            ZYPEZDItemValueSetter.setValue(bizBMsg.ntfyHdrId_B, ntfyHdr.get("NTFY_HDR_ID"));
            ZYPEZDItemValueSetter.setValue(bizBMsg.ntfyHdrNm_B, ntfyHdr.get("NTFY_HDR_NM"));
            ZYPEZDItemValueSetter.setValue(bizBMsg.ntfyHdrDescTxt_B, ntfyHdr.get("NTFY_HDR_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bizBMsg.ntfyBizAreaTpDescTxt_B, ntfyHdr.get("NTFY_BIZ_AREA_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bizBMsg.ntfySubAreaTpDescTxt_B, ntfyHdr.get("NTFY_SUB_AREA_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bizBMsg.effFromDt_B, ntfyHdr.get("EFF_FROM_DT"));
            ZYPEZDItemValueSetter.setValue(bizBMsg.effThruDt_B, ntfyHdr.get("EFF_THRU_DT"));
            ZYPEZDItemValueSetter.setValue(bizBMsg.ntfyHdrActvFlg_B, returnFlg(ntfyHdr.get("NTFY_HDR_ACTV_FLG")));
            i++;

            if (i >= bizMsg.B.length()) {
                break;
            }
        }

        EZDMsg.copy(bizMsg.B, null, glblMsg.B, null);
    }

    /**
     * isChangeDistList
     * @param bizMsg NWWL0050CMsg
     * @param glblMsg NWWL0050SMsg
     * @return boolean
     */
    public static boolean isChangeDistList(NWWL0050CMsg bizMsg, NWWL0050SMsg glblMsg) {

        if (checkChangeValue(bizMsg.ntfyDistListNm.getValue(), glblMsg.ntfyDistListNm.getValue()) //
                || checkChangeValue(bizMsg.ntfyDistListDescTxt.getValue(), glblMsg.ntfyDistListDescTxt.getValue()) //
                || checkChangeValue(bizMsg.ntfyBizAreaTpCd_D.getValue(), glblMsg.ntfyBizAreaTpCd_D.getValue()) //
                || checkChangeValue(bizMsg.ntfySubAreaTpCd_D.getValue(), glblMsg.ntfySubAreaTpCd_D.getValue()) //
                || checkChangeValue(bizMsg.effFromDt_D.getValue(), glblMsg.effFromDt_D.getValue()) //
                || checkChangeValue(bizMsg.effThruDt_D.getValue(), glblMsg.effThruDt_D.getValue()) //
                || checkChangeValue(bizMsg.ntfyDistListActvFlg.getValue(), glblMsg.ntfyDistListActvFlg.getValue()) //
        ) {
            return true;
        }

        return false;
    }

    /**
     * Changed Value Exist Check
     * @param str1 String
     * @param str2 String
     * @return boolean
     */
    private static Boolean checkChangeValue(String str1, String str2) {
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

    /**
     * setDistListTMsg
     * @param bizMsg NWWL0050CMsg
     * @param distTMsg StringNTFY_DIST_LISTTMsg
     */
    public static void setDistListTMsg(NWWL0050CMsg bizMsg, NTFY_DIST_LISTTMsg distTMsg) {
        ZYPEZDItemValueSetter.setValue(distTMsg.ntfyDistListNm, bizMsg.ntfyDistListNm.getValue());
        ZYPEZDItemValueSetter.setValue(distTMsg.ntfyDistListDescTxt, bizMsg.ntfyDistListDescTxt.getValue());
        ZYPEZDItemValueSetter.setValue(distTMsg.ntfyBizAreaTpCd, bizMsg.ntfyBizAreaTpCd_D.getValue());
        ZYPEZDItemValueSetter.setValue(distTMsg.ntfySubAreaTpCd, bizMsg.ntfySubAreaTpCd_D.getValue());
        ZYPEZDItemValueSetter.setValue(distTMsg.effFromDt, bizMsg.effFromDt_D.getValue());
        ZYPEZDItemValueSetter.setValue(distTMsg.effThruDt, bizMsg.effThruDt_D.getValue());
        ZYPEZDItemValueSetter.setValue(distTMsg.ntfyDistListActvFlg, setFlg(bizMsg.ntfyDistListActvFlg.getValue()));

    }

    /**
     * setDistListAsgTMsg
     * @param bizAMsg NWWL0050_ACMsg
     * @param asgTMsg NTFY_DIST_LIST_ASGTMsg
     */
    public static void setDistListAsgTMsg(NWWL0050_ACMsg bizAMsg, NTFY_DIST_LIST_ASGTMsg asgTMsg) {
        ZYPEZDItemValueSetter.setValue(asgTMsg.ntfyDistQlfyCd, bizAMsg.ntfyDistQlfyCd_A.getValue());
        ZYPEZDItemValueSetter.setValue(asgTMsg.ntfyDistListAsgValTxt, bizAMsg.ntfyDistListAsgValTxt_A.getValue());
        ZYPEZDItemValueSetter.setValue(asgTMsg.ntfyDistListAsgActvFlg, setFlg(bizAMsg.ntfyDistListAsgActvFlg_A.getValue()));

    }

    /**
     * isChangeDistListAsg
     * @param bizAMsg NWWL0050_ACMsg
     * @param glblMsg NWWL0050SMsg
     * @return boolean
     */
    public static boolean isChangeDistListAsg(NWWL0050_ACMsg bizAMsg, NWWL0050SMsg glblMsg) {
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            NWWL0050_ASMsg glblAMsg = glblMsg.A.no(i);

            if (bizAMsg.ntfyDistListAsgPk_A.getValue().compareTo(glblAMsg.ntfyDistListAsgPk_A.getValue()) == 0) {

                if (checkChangeValue(bizAMsg.ntfyDistQlfyCd_A.getValue(), glblAMsg.ntfyDistQlfyCd_A.getValue()) //
                        || checkChangeValue(bizAMsg.ntfyDistListAsgValTxt_A.getValue(), glblAMsg.ntfyDistListAsgValTxt_A.getValue()) //
                        || checkChangeValue(bizAMsg.ntfyDistListAsgActvFlg_A.getValue(), glblAMsg.ntfyDistListAsgActvFlg_A.getValue()) //
                ) {
                    return true;
                }

                return false;
            }
        }

        return false;
    }
}
