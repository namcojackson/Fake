/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7060.common;

import static business.blap.NMAL7060.constant.NMAL7060Constant.NMAM0007I;
import static business.blap.NMAL7060.constant.NMAL7060Constant.NMAM0096I;
import static business.blap.NMAL7060.constant.NMAL7060Constant.NZZM0001W;
import static business.blap.NMAL7060.constant.NMAL7060Constant.ZZZM9003I;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDItemAttrDefines;
import parts.common.EZDItemAttribute;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import business.blap.NMAL7060.NMAL7060CMsg;
import business.blap.NMAL7060.NMAL7060Query;
import business.blap.NMAL7060.NMAL7060SMsg;
import business.blap.NMAL7060.NMAL7060_ACMsg;
import business.blap.NMAL7060.NMAL7060_ASMsg;
import business.blap.NMAL7060.NMAL7060_BCMsg;
import business.blap.NMAL7060.NMAL7060_BSMsg;
import business.blap.NMAL7060.NMAL7060_CCMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * NMAL7060CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/01   Fujitsu         W.Honda         Create          N/A
 * 2016/04/26   Fujitsu         W.Honda         Update          QC#6738
 * 2017/02/10   Fujitsu         R.Nakamura      Update          QC#17529
 * 2018/08/20   Fujitsu         W.Honda         Update          QC#24307
 *</pre>
 */
public class NMAL7060CommonLogic {

    /**
     * setResultForMdlToGlblMsg
     * @param ssmResult S21SsmEZDResult
     * @param sMsg NMAL7050SMsg
     * @param cMsg NMAL7050CMsg
     * @param glblCmpyCd String
     */
    public static void setResultForMdlToGlblMsg(S21SsmEZDResult ssmResult, NMAL7060SMsg sMsg, NMAL7060CMsg cMsg, String glblCmpyCd) {
        // SSM Result
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

        // Header
        Map<String, Object> resultMap = (Map<String, Object>) resultList.get(0);
        // Hidden
        ZYPEZDItemValueSetter.setValue(sMsg.prcMtrPkgPk, (BigDecimal) resultMap.get("PRC_MTR_PKG_PK"));
        // Screen Field
        ZYPEZDItemValueSetter.setValue(sMsg.prcMtrPkgNm, (String) resultMap.get("PRC_MTR_PKG_NM"));
        ZYPEZDItemValueSetter.setValue(sMsg.prcMtrPkgDescTxt, (String) resultMap.get("PRC_MTR_PKG_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(sMsg.effFromDt, (String) resultMap.get("PMP_EFF_FROM_DT"));
        ZYPEZDItemValueSetter.setValue(sMsg.effThruDt, (String) resultMap.get("PMP_EFF_THRU_DT"));
        ZYPEZDItemValueSetter.setValue(sMsg.corpAdvPrcFlg, (String) resultMap.get("CORP_AD_PRC_FLG"));
        ZYPEZDItemValueSetter.setValue(sMsg.ezInTime, (String) resultMap.get("PMP_EZINTIME"));
        ZYPEZDItemValueSetter.setValue(sMsg.ezInTimeZone, (String) resultMap.get("PMP_EZINTIMEZONE"));
        ZYPEZDItemValueSetter.setValue(sMsg.ezUpTime, (String) resultMap.get("PMP_EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(sMsg.ezUpTimeZone, (String) resultMap.get("PMP_EZUPTIMEZONE"));

        resultMap = null;
        int reccount = 0;
        ZYPTableUtil.clear(sMsg.A);
        sMsg.A.setValidCount(0);
        for (int i = 0; i < resultList.size() && i < sMsg.A.length(); i++) {
            resultMap = (Map<String, Object>) resultList.get(i);
            NMAL7060_ASMsg sMsgALine = sMsg.A.no(i);

            // Hidden
            ZYPEZDItemValueSetter.setValue(sMsgALine.prcMtrPkgMdlPk_A1, (BigDecimal) resultMap.get("PRC_MTR_PKG_MDL_PK"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.mdlId_A1, (BigDecimal) resultMap.get("MDL_ID"));
            // Screen Field
            ZYPEZDItemValueSetter.setValue(sMsgALine.mdlNm_A1, (String) resultMap.get("MDL_NM"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.effFromDt_A1, (String) resultMap.get("PMPM_EFF_FROM_DT"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.effThruDt_A1, (String) resultMap.get("PMPM_EFF_THRU_DT"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.ezInTime_AK, (String) resultMap.get("EZINTIME"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.xxDt10Dt_AI, sMsgALine.ezInTime_AK.getValue().substring(0, 8));
            ZYPEZDItemValueSetter.setValue(sMsgALine.xxFullNm_AI, (String) resultMap.get("EZINUSERNM"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.ezInTimeZone_A1, (String) resultMap.get("EZINTIMEZONE"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.ezUpTime_AK, (String) resultMap.get("EZUPTIME"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.xxDt10Dt_AU, sMsgALine.ezUpTime_AK.getValue().substring(0, 8));
            ZYPEZDItemValueSetter.setValue(sMsgALine.xxFullNm_AU, (String) resultMap.get("EZUPUSERNM"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.ezUpTimeZone_A1, (String) resultMap.get("EZUPTIMEZONE"));

            reccount++;
        }
        sMsg.A.setValidCount(reccount);
    }
    /**
     * setResultForBllgToGlblMsg
     * @param ssmResult S21SsmEZDResult
     * @param sMsg NMAL7050SMsg
     * @param cMsg NMAL7050CMsg
     * @param glblCmpyCd String
     */
    public static void setResultForBllgToGlblMsg(S21SsmEZDResult ssmResult, NMAL7060SMsg sMsg, NMAL7060CMsg cMsg, String glblCmpyCd) {
        // SSM Result
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

        int reccount = 0;
        ZYPTableUtil.clear(sMsg.B);
        sMsg.B.setValidCount(0);
        for (int i = 0; i < resultList.size() && i < sMsg.B.length(); i++) {
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
            NMAL7060_BSMsg sMsgBLine = sMsg.B.no(i);
          ZYPEZDItemValueSetter.setValue(sMsgBLine.prcMtrPkgBllgMtrPk_B1, (BigDecimal) resultMap.get("PRC_MTR_PKG_BLLG_MTR_PK"));
          ZYPEZDItemValueSetter.setValue(sMsgBLine.mtrLbCd_B1, (String) resultMap.get("MTR_LB_CD"));
          ZYPEZDItemValueSetter.setValue(sMsgBLine.bllgMtrLvlNum_B1, (String) resultMap.get("BLLG_MTR_LVL_NUM"));
          ZYPEZDItemValueSetter.setValue(sMsgBLine.mtrLbDescTxt_B1, (String) resultMap.get("MTR_LB_DESC_TXT"));
          ZYPEZDItemValueSetter.setValue(sMsgBLine.mtrLbNm_B1, (String) resultMap.get("MTR_LB_NM"));
          ZYPEZDItemValueSetter.setValue(sMsgBLine.mdseCd_B1, (String) resultMap.get("MDSE_CD"));
          ZYPEZDItemValueSetter.setValue(sMsgBLine.ezUpTime_B1, (String) resultMap.get("PMPBM_EZUPTIME"));
          ZYPEZDItemValueSetter.setValue(sMsgBLine.ezUpTimeZone_B1, (String) resultMap.get("PMPBM_EZUPTIMEZONE"));

            reccount++;
        }
        sMsg.B.setValidCount(reccount);
    }

    /**
     * setSearchResultToBizMsg
     * @param ssmResult S21SsmEZDResult
     * @param sMsg NMAL7050SMsg
     * @param cMsg NMAL7050CMsg
     * @param glblCmpyCd String
     * @param index int
     */
    public static void setSearchResultToBizMsg(S21SsmEZDResult ssmResult, NMAL7060SMsg sMsg, NMAL7060CMsg cMsg, String glblCmpyCd, int index) {
        int queryResCnt = ssmResult.getQueryResultCount();

        if (queryResCnt > cMsg.C.length()) {
            queryResCnt = cMsg.C.length();
        }

        // SSM Result
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        int i = 0;

        for (; i < queryResCnt; i++) {
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
            NMAL7060_CCMsg cMsgCLine = cMsg.C.no(i);

            if (ZYPCommonFunc.hasValue((BigDecimal) resultMap.get("PRC_MTR_PKG_PHYS_MTR_PK"))) {
                ZYPEZDItemValueSetter.setValue(cMsgCLine.xxCellIdx_CB, BigDecimal.valueOf(index));
                ZYPEZDItemValueSetter.setValue(cMsgCLine.prcMtrPkgBllgMtrPk_C1, (BigDecimal) resultMap.get("PRC_MTR_PKG_BLLG_MTR_PK"));
                ZYPEZDItemValueSetter.setValue(cMsgCLine.prcMtrPkgPhysMtrPk_C1, (BigDecimal) resultMap.get("PRC_MTR_PKG_PHYS_MTR_PK"));
                ZYPEZDItemValueSetter.setValue(cMsgCLine.mtrLbDescTxt_C1, (String) resultMap.get("ML_MTR_LB_NM"));
                ZYPEZDItemValueSetter.setValue(cMsgCLine.mtrLbCd_C1, (String) resultMap.get("MTR_LB_CD"));
                // QC#6738 2016/04/26 Mod start
//                ZYPEZDItemValueSetter.setValue(cMsgCLine.bllblFlg_C1, (String) resultMap.get("BLLBL_FLG"));
                ZYPEZDItemValueSetter.setValue(cMsgCLine.bllblFlg_C1, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(cMsgCLine.mtrEntryMndFlg_C1, (String) resultMap.get("MTR_ENTRY_MND_FLG"));
//                ZYPEZDItemValueSetter.setValue(cMsgCLine.actvFlg_C1, (String) resultMap.get("ACTV_FLG"));
                ZYPEZDItemValueSetter.setValue(cMsgCLine.actvFlg_C1, ZYPConstant.FLG_ON_Y);
                // QC#6738 2016/04/26 Mod end
                ZYPEZDItemValueSetter.setValue(cMsgCLine.mtrMultRate_C1, (BigDecimal) resultMap.get("MTR_MULT_RATE"));
                ZYPEZDItemValueSetter.setValue(cMsgCLine.ezInTime_CK, (String) resultMap.get("EZINTIME"));
                // QC#6738 2016/04/26 Del start
//                ZYPEZDItemValueSetter.setValue(cMsgCLine.xxDt10Dt_CI, cMsgCLine.ezInTime_CK.getValue().substring(0, 8));
                ZYPEZDItemValueSetter.setValue(cMsgCLine.ezInTimeZone_C1, (String) resultMap.get("EZINTIMEZONE"));
//                ZYPEZDItemValueSetter.setValue(cMsgCLine.xxFullNm_CI, (String) resultMap.get("EZINUSERNM"));
                ZYPEZDItemValueSetter.setValue(cMsgCLine.ezUpTime_CK, (String) resultMap.get("EZUPTIME"));
//                ZYPEZDItemValueSetter.setValue(cMsgCLine.xxDt10Dt_CU, cMsgCLine.ezUpTime_CK.getValue().substring(0, 8));
                ZYPEZDItemValueSetter.setValue(cMsgCLine.ezUpTimeZone_C1, (String) resultMap.get("EZUPTIMEZONE"));
//                ZYPEZDItemValueSetter.setValue(cMsgCLine.xxFullNm_CU, (String) resultMap.get("EZUPUSERNM"));
                // QC#6738 2016/04/26 Del end
            } else {
                ZYPEZDItemValueSetter.setValue(cMsgCLine.xxCellIdx_CB, BigDecimal.valueOf(index));
                ZYPEZDItemValueSetter.setValue(cMsgCLine.mtrLbDescTxt_C1, (String) resultMap.get("MDL_MTR_LB_NOTE_TXT"));
                ZYPEZDItemValueSetter.setValue(cMsgCLine.mtrLbCd_C1, (String) resultMap.get("PHYS_MTR_LB_CD"));
                // QC#6738 2016/04/26 Mod start
//                ZYPEZDItemValueSetter.setValue(cMsgCLine.bllblFlg_C1, (String) resultMap.get("BLLG_MTR_AVAL_FLG"));
                ZYPEZDItemValueSetter.setValue(cMsgCLine.bllblFlg_C1, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(cMsgCLine.mtrEntryMndFlg_C1, (String) resultMap.get("MTR_ENTRY_MND_FLG"));
//                ZYPEZDItemValueSetter.setValue(cMsgCLine.actvFlg_C1, (String) resultMap.get("ACTV_FLG"));
                ZYPEZDItemValueSetter.setValue(cMsgCLine.actvFlg_C1, ZYPConstant.FLG_ON_Y);
                // QC#6738 2016/04/26 Mod end
                ZYPEZDItemValueSetter.setValue(cMsgCLine.mtrMultRate_C1, (BigDecimal) resultMap.get("MTR_MULT_RATE"));
            }
        }
        cMsg.bllblFlg_LB.no(0).setValue(ZYPConstant.FLG_ON_Y);
        cMsg.xxFlgNm_LB.no(0).setValue(ZYPConstant.FLG_ON_Y);
        cMsg.bllblFlg_LB.no(1).setValue(ZYPConstant.FLG_OFF_N);
        cMsg.xxFlgNm_LB.no(1).setValue(ZYPConstant.FLG_OFF_N);

        cMsg.C.setValidCount(i);
    }

    // QC#24307 2018/08/20 Add start
    /**
     * setSearchResultToBizMsg
     * @param ssmResult S21SsmEZDResult
     * @param sMsg NMAL7050SMsg
     * @param cMsg NMAL7050CMsg
     * @param glblCmpyCd String
     * @param index int
     */
    public static void setSearchResultToBizMsgFromMdl(S21SsmEZDResult ssmResult, NMAL7060SMsg sMsg, NMAL7060CMsg cMsg, String glblCmpyCd, int index) {
        ZYPTableUtil.clear(cMsg.C);
        int queryResCnt = ssmResult.getQueryResultCount();

        if (queryResCnt > cMsg.C.length()) {
            queryResCnt = cMsg.C.length();
        }

        // SSM Result
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        int i = 0;

        for (; i < queryResCnt; i++) {
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
            NMAL7060_CCMsg cMsgCLine = cMsg.C.no(i);

            boolean matchFlg = false;
            int hardCounterIndex = 0;
            for (; hardCounterIndex < sMsg.C.getValidCount(); hardCounterIndex++) {
                if (sMsg.C.no(hardCounterIndex).xxCellIdx_CB.getValueInt() != index) {
                    continue;
                }

                if (ZYPCommonFunc.hasValue(sMsg.C.no(hardCounterIndex).mtrLbCd_C1)
                        && ZYPCommonFunc.hasValue((String) resultMap.get("PHYS_MTR_LB_CD"))
                        && sMsg.C.no(hardCounterIndex).mtrLbCd_C1.getValue().equals((String) resultMap.get("PHYS_MTR_LB_CD"))) {
                    matchFlg = true;
                    break;
                }
            }

            if (matchFlg) {
                EZDMsg.copy(sMsg.C.no(hardCounterIndex), null, cMsg.C.no(i), null);
            } else {
                ZYPEZDItemValueSetter.setValue(cMsgCLine.xxCellIdx_CB, BigDecimal.valueOf(index));
                ZYPEZDItemValueSetter.setValue(cMsgCLine.mtrLbDescTxt_C1, (String) resultMap.get("MDL_MTR_LB_NOTE_TXT"));
                ZYPEZDItemValueSetter.setValue(cMsgCLine.mtrLbCd_C1, (String) resultMap.get("PHYS_MTR_LB_CD"));
                ZYPEZDItemValueSetter.setValue(cMsgCLine.bllblFlg_C1, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(cMsgCLine.mtrEntryMndFlg_C1, (String) resultMap.get("MTR_ENTRY_MND_FLG"));
                ZYPEZDItemValueSetter.setValue(cMsgCLine.actvFlg_C1, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(cMsgCLine.mtrMultRate_C1, (BigDecimal) resultMap.get("MTR_MULT_RATE"));
            }
        }

        cMsg.bllblFlg_LB.no(0).setValue(ZYPConstant.FLG_ON_Y);
        cMsg.xxFlgNm_LB.no(0).setValue(ZYPConstant.FLG_ON_Y);
        cMsg.bllblFlg_LB.no(1).setValue(ZYPConstant.FLG_OFF_N);
        cMsg.xxFlgNm_LB.no(1).setValue(ZYPConstant.FLG_OFF_N);

        cMsg.C.setValidCount(i);
    }
    // QC#24307 2018/08/20 Add end

    /**
     * setSearchResultToBizMsgForSubmit
     * @param ssmResult S21SsmEZDResult
     * @param sMsg NMAL7050SMsg
     * @param cMsg NMAL7050CMsg
     * @param glblCmpyCd String
     * @param index int
     */
    public static void setSearchResultToBizMsgForSubmit(S21SsmEZDResult ssmResult, NMAL7060SMsg sMsg, NMAL7060CMsg cMsg, String glblCmpyCd, int index) {
        int queryResCnt = ssmResult.getQueryResultCount();

        if (queryResCnt > cMsg.C.length()) {
            queryResCnt = cMsg.C.length();
        }

        // SSM Result
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        BigDecimal prePK = null;
        int indexBLine = 0;
        int i = 0;
        NMAL7060_BCMsg preCMsgBLine = null;

        for (; i < queryResCnt; i++) {
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
            NMAL7060_BCMsg cMsgBLine = null;

            if (ZYPCommonFunc.hasValue((BigDecimal) resultMap.get("PRC_MTR_PKG_BLLG_MTR_PK"))) {
                if (!ZYPCommonFunc.hasValue(prePK)
                        || (((BigDecimal) resultMap.get("PRC_MTR_PKG_BLLG_MTR_PK")).compareTo(prePK) != 0)) {
                    cMsgBLine = cMsg.B.no(indexBLine);
//                    ZYPEZDItemValueSetter.setValue(cMsgBLine.xxCellIdx_BA, BigDecimal.valueOf(index));
//                    ZYPEZDItemValueSetter.setValue(cMsgBLine.prcMtrPkgMdlPk_B1, (BigDecimal) resultMap.get("PRC_MTR_PKG_PK"));
                    ZYPEZDItemValueSetter.setValue(cMsgBLine.prcMtrPkgBllgMtrPk_B1, (BigDecimal) resultMap.get("PRC_MTR_PKG_BLLG_MTR_PK"));
                    ZYPEZDItemValueSetter.setValue(cMsgBLine.bllgMtrLvlNum_B1, (String) resultMap.get("BLLG_MTR_LVL_NUM"));
                    ZYPEZDItemValueSetter.setValue(cMsgBLine.mtrLbDescTxt_B1, (String) resultMap.get("MTR_LB_DESC_TXT"));
                    ZYPEZDItemValueSetter.setValue(cMsgBLine.mtrLbNm_B1, (String) resultMap.get("PMPBM_MTR_LB_NM"));
                    ZYPEZDItemValueSetter.setValue(cMsgBLine.mdseCd_B1, (String) resultMap.get("MDSE_CD"));
                    ZYPEZDItemValueSetter.setValue(cMsgBLine.mtrLbCd_B1, (String) resultMap.get("MTR_LB_CD"));
                    indexBLine++;
                    prePK = cMsgBLine.prcMtrPkgBllgMtrPk_B1.getValue();
                }
            } else {
                if (preCMsgBLine == null
                        || !(preCMsgBLine.mtrLbCd_B1.getValue().equals((String) resultMap.get("BLLG_MTR_LB_CD"))
                                && preCMsgBLine.mtrLbNm_B1.getValue().equals((String) resultMap.get("MTR_LB_NM")))) {
                    cMsgBLine = cMsg.B.no(indexBLine);
//                    ZYPEZDItemValueSetter.setValue(cMsgBLine.xxCellIdx_BA, BigDecimal.valueOf(index));
//                    ZYPEZDItemValueSetter.setValue(cMsgBLine.prcMtrPkgMdlPk_B1, (BigDecimal) resultMap.get("PRC_MTR_PKG_PK"));
                    ZYPEZDItemValueSetter.setValue(cMsgBLine.bllgMtrLvlNum_B1, (String) resultMap.get("BLLG_MTR_MAP_LVL_NUM"));
                    ZYPEZDItemValueSetter.setValue(cMsgBLine.mtrLbDescTxt_B1, (String) resultMap.get("MTR_LB_DESC_TXT"));
                    ZYPEZDItemValueSetter.setValue(cMsgBLine.mtrLbNm_B1, (String) resultMap.get("MTR_LB_NM"));
                    ZYPEZDItemValueSetter.setValue(cMsgBLine.mtrLbCd_B1, (String) resultMap.get("BLLG_MTR_LB_CD"));
                    indexBLine++;
                    preCMsgBLine = cMsgBLine;
                }
            }

            NMAL7060_CCMsg cMsgCLine = cMsg.C.no(i);

            if (indexBLine == 0) {
                continue;
            }
            if (ZYPCommonFunc.hasValue((BigDecimal) resultMap.get("PRC_MTR_PKG_PHYS_MTR_PK"))) {
//                ZYPEZDItemValueSetter.setValue(cMsgCLine.xxCellIdx_CA, BigDecimal.valueOf(index));
                ZYPEZDItemValueSetter.setValue(cMsgCLine.xxCellIdx_CB, BigDecimal.valueOf(indexBLine - 1));
//                ZYPEZDItemValueSetter.setValue(cMsgCLine.prcMtrPkgMdlPk_C1, (BigDecimal) resultMap.get("PRC_MTR_PKG_PK"));
                ZYPEZDItemValueSetter.setValue(cMsgCLine.prcMtrPkgBllgMtrPk_C1, (BigDecimal) resultMap.get("PRC_MTR_PKG_BLLG_MTR_PK"));
                ZYPEZDItemValueSetter.setValue(cMsgCLine.prcMtrPkgPhysMtrPk_C1, (BigDecimal) resultMap.get("PRC_MTR_PKG_PHYS_MTR_PK"));
                ZYPEZDItemValueSetter.setValue(cMsgCLine.mtrLbDescTxt_C1, (String) resultMap.get("ML_MTR_LB_NM"));
                ZYPEZDItemValueSetter.setValue(cMsgCLine.mtrLbCd_C1, (String) resultMap.get("MTR_LB_CD"));
                // QC#6738 2016/04/26 Mod start
//                ZYPEZDItemValueSetter.setValue(cMsgCLine.bllblFlg_C1, (String) resultMap.get("BLLBL_FLG"));
//                ZYPEZDItemValueSetter.setValue(cMsgCLine.actvFlg_C1, (String) resultMap.get("ACTV_FLG"));
                ZYPEZDItemValueSetter.setValue(cMsgCLine.bllblFlg_C1, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(cMsgCLine.actvFlg_C1, ZYPConstant.FLG_ON_Y);
                // QC#6738 2016/04/26 Mod start
                ZYPEZDItemValueSetter.setValue(cMsgCLine.mtrMultRate_C1, (BigDecimal) resultMap.get("MTR_MULT_RATE"));
                ZYPEZDItemValueSetter.setValue(cMsgCLine.ezInTime_CK, (String) resultMap.get("EZINTIME"));
                // QC#6738 2016/04/26 Del start
//                ZYPEZDItemValueSetter.setValue(cMsgCLine.xxDt10Dt_CI, cMsgCLine.ezInTime_CK.getValue().substring(0, 8));
                ZYPEZDItemValueSetter.setValue(cMsgCLine.ezInTimeZone_C1, (String) resultMap.get("EZINTIMEZONE"));
//                ZYPEZDItemValueSetter.setValue(cMsgCLine.xxFullNm_CI, (String) resultMap.get("EZINUSERID"));
                ZYPEZDItemValueSetter.setValue(cMsgCLine.ezUpTime_CK, (String) resultMap.get("EZUPTIME"));
//                ZYPEZDItemValueSetter.setValue(cMsgCLine.xxDt10Dt_CU, cMsgCLine.ezUpTime_CK.getValue().substring(0, 8));
                ZYPEZDItemValueSetter.setValue(cMsgCLine.ezUpTimeZone_C1, (String) resultMap.get("EZUPTIMEZONE"));
//                ZYPEZDItemValueSetter.setValue(cMsgCLine.xxFullNm_CU, (String) resultMap.get("EZUPUSERID"));
                // QC#6738 2016/04/26 Del end
            } else {
//                ZYPEZDItemValueSetter.setValue(cMsgCLine.xxCellIdx_CA, BigDecimal.valueOf(index));
                ZYPEZDItemValueSetter.setValue(cMsgCLine.xxCellIdx_CB, BigDecimal.valueOf(indexBLine - 1));
                ZYPEZDItemValueSetter.setValue(cMsgCLine.mtrLbDescTxt_C1, (String) resultMap.get("MDL_MTR_LB_NOTE_TXT"));
                ZYPEZDItemValueSetter.setValue(cMsgCLine.mtrLbCd_C1, (String) resultMap.get("PHYS_MTR_LB_CD"));
                // QC#6738 2016/04/26 Mod start
//                ZYPEZDItemValueSetter.setValue(cMsgCLine.bllblFlg_C1, (String) resultMap.get("BLLG_MTR_AVAL_FLG"));
//                ZYPEZDItemValueSetter.setValue(cMsgCLine.actvFlg_C1, (String) resultMap.get("ACTV_FLG"));
                ZYPEZDItemValueSetter.setValue(cMsgCLine.bllblFlg_C1, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(cMsgCLine.actvFlg_C1, ZYPConstant.FLG_ON_Y);
                // QC#6738 2016/04/26 Mod end
                ZYPEZDItemValueSetter.setValue(cMsgCLine.mtrMultRate_C1, (BigDecimal) resultMap.get("MTR_MULT_RATE"));
            }
        }
        cMsg.bllblFlg_LB.no(0).setValue(ZYPConstant.FLG_ON_Y);
        cMsg.xxFlgNm_LB.no(0).setValue(ZYPConstant.FLG_ON_Y);
        cMsg.bllblFlg_LB.no(1).setValue(ZYPConstant.FLG_OFF_N);
        cMsg.xxFlgNm_LB.no(1).setValue(ZYPConstant.FLG_OFF_N);

        cMsg.B.setValidCount(indexBLine);
        cMsg.C.setValidCount(i);
    }

    /**
     * setToBizMsgFromGlblMsg
     * @param sMsg NMAL7050SMsg
     * @param cMsg NMAL7050CMsg
     * @param glblCmpyCd String
     * @param index int
     */
    public static void setToBizMsgFromGlblMsg(NMAL7060SMsg sMsg, NMAL7060CMsg cMsg, String glblCmpyCd, int index) {
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(cMsg.B);
        ZYPTableUtil.clear(cMsg.C);

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
        }
        cMsg.A.setValidCount(sMsg.A.getValidCount());

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i), null);
        }
        cMsg.B.setValidCount(sMsg.B.getValidCount());

        int indexCLine = 0;
        ZYPTableUtil.clear(cMsg.C);
        for (int i = 0; i < sMsg.C.getValidCount(); i++) {
            if (sMsg.C.no(i).xxCellIdx_CB.getValueInt() == index) {
                EZDMsg.copy(sMsg.C.no(i), null, cMsg.C.no(indexCLine), null);
                indexCLine++;
            }
        }

        cMsg.bllblFlg_LB.no(0).setValue(ZYPConstant.FLG_ON_Y);
        cMsg.xxFlgNm_LB.no(0).setValue(ZYPConstant.FLG_ON_Y);
        cMsg.bllblFlg_LB.no(1).setValue(ZYPConstant.FLG_OFF_N);
        cMsg.xxFlgNm_LB.no(1).setValue(ZYPConstant.FLG_OFF_N);

        cMsg.C.setValidCount(indexCLine);
    }

    /**
     * saveCurrentPageToSMsgA
     * @param cMsg NMAL7060CMsg
     * @param sMsg NMAL7060SMsg
     * @param glblCmpyCd
     */
    public static void saveCurrentPageToSMsgA(NMAL7060CMsg cMsg, NMAL7060SMsg sMsg, String glblCmpyCd) {

        cMsg.clearErrorInfo();
        cMsg.A.clearErrorInfo();
        sMsg.clearErrorInfo();
        sMsg.A.clearErrorInfo();

        // Add Start 2017/02/10 QC#17529
        if (ZYPCommonFunc.hasValue(cMsg.xxEventFlgTxt_F1)) {
            if (ZYPConstant.FLG_OFF_0.equals(cMsg.xxWrnSkipFlg.getValue())) {
                if (checkChangedMdlValue(cMsg, sMsg)) {
                    cMsg.setMessageInfo(NMAM0096I);
                    ZYPEZDItemValueSetter.setValue(cMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_1);
                    return;
                }
            }
            ZYPTableUtil.clear(cMsg.A);
            ZYPTableUtil.clear(sMsg.A);
            cMsg.xxEventFlgTxt_F1.clear();
            cMsg.A.no(0).effFromDt_A1.setValue(ZYPDateUtil.getSalesDate(glblCmpyCd));
            cMsg.A.setValidCount(1);
        }
        // Add End 2017/02/10 QC#17529

        // QC#24307 2018/08/20 Add start
        int i = 0;
        // QC#24307 2018/08/20 Add end
        for (; i < cMsg.A.getValidCount(); i++) {
            EZDMsg.copy(cMsg.A.no(i), null, sMsg.A.no(i), null);
        }
        // QC#24307 2018/08/20 Add start
        sMsg.A.setValidCount(i);
        // QC#24307 2018/08/20 Add end
    }

    /**
     * saveCurrentPageToSMsgB
     * @param cMsg NMAL7060CMsg
     * @param sMsg NMAL7060SMsg
     */
    public static void saveCurrentPageToSMsgB(NMAL7060CMsg cMsg, NMAL7060SMsg sMsg) {

        cMsg.clearErrorInfo();
        cMsg.B.clearErrorInfo();
        sMsg.clearErrorInfo();
        sMsg.B.clearErrorInfo();

        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            EZDMsg.copy(cMsg.B.no(i), null, sMsg.B.no(i), null);
        }
    }

    /**
     * saveCurrentCMsgToSMsgALL
     * @param cMsg NMAL7060CMsg
     * @param sMsg NMAL7060SMsg
     */
    public static void saveCurrentCMsgToSMsgALL(NMAL7060CMsg cMsg, NMAL7060SMsg sMsg) {

        cMsg.clearErrorInfo();
        cMsg.A.clearErrorInfo();
        sMsg.clearErrorInfo();
        sMsg.A.clearErrorInfo();
        sMsg.B.clearErrorInfo();
        sMsg.C.clearErrorInfo();

        ZYPEZDItemValueSetter.setValue(sMsg.xxRadioBtn, cMsg.xxRadioBtn.getValue());
        ZYPEZDItemValueSetter.setValue(sMsg.prcMtrPkgNm, cMsg.prcMtrPkgNm.getValue());
        ZYPEZDItemValueSetter.setValue(sMsg.prcMtrPkgPk, cMsg.prcMtrPkgPk.getValue());
        ZYPEZDItemValueSetter.setValue(sMsg.corpAdvPrcFlg, cMsg.corpAdvPrcFlg.getValue());
        ZYPEZDItemValueSetter.setValue(sMsg.prcMtrPkgDescTxt, cMsg.prcMtrPkgDescTxt.getValue());
        ZYPEZDItemValueSetter.setValue(sMsg.effFromDt, cMsg.effFromDt.getValue());
        ZYPEZDItemValueSetter.setValue(sMsg.effThruDt, cMsg.effThruDt.getValue());
        ZYPEZDItemValueSetter.setValue(sMsg.ezInTime, cMsg.ezInTime.getValue());
        ZYPEZDItemValueSetter.setValue(sMsg.ezInTimeZone, cMsg.ezInTimeZone.getValue());
        ZYPEZDItemValueSetter.setValue(sMsg.ezUpTime, cMsg.ezUpTime.getValue());
        ZYPEZDItemValueSetter.setValue(sMsg.ezUpTimeZone, cMsg.ezUpTimeZone.getValue());
        int aIndex = 0;
        for (; aIndex < cMsg.A.getValidCount(); aIndex++) {
            EZDMsg.copy(cMsg.A.no(aIndex), null, sMsg.A.no(aIndex), null);
        }
        sMsg.A.setValidCount(aIndex);

        int bIndex = 0;
        for (; bIndex < cMsg.B.getValidCount(); bIndex++) {
            EZDMsg.copy(cMsg.B.no(bIndex), null, sMsg.B.no(bIndex), null);
        }
        sMsg.B.setValidCount(bIndex);

        boolean matchFlg = false;
        List<Integer> deleteIndexs = new ArrayList<Integer>();
        if (cMsg.C.getValidCount() > 0) {
            for (int i = 0; i < sMsg.C.getValidCount(); i++) {
                if (cMsg.C.no(0).xxCellIdx_CB.getValueInt() == sMsg.C.no(i).xxCellIdx_CB.getValueInt()) {
                    matchFlg = true;
                    deleteIndexs.add(Integer.valueOf(i));
                }
            }
            if (matchFlg) {
                ZYPTableUtil.deleteRows(sMsg.C, deleteIndexs);
                matchFlg = false;
            }
            int cIndex = sMsg.C.getValidCount();
            for (int i = 0; i < cMsg.C.getValidCount(); i++) {
                EZDMsg.copy(cMsg.C.no(i), null, sMsg.C.no(cIndex), null);
                cIndex++;
            }
            sMsg.C.setValidCount(cIndex);
        }
    }

    /**
     * saveCurrentPageToSMsgDelete
     * @param cMsg NMAL7060CMsg
     * @param sMsg NMAL7060SMsg
     */
    public static void saveToSMsgForDelete(NMAL7060CMsg cMsg, NMAL7060SMsg sMsg) {

        int delIdx = cMsg.xxRadioBtn_B1.getValueInt();
        int nValCnt = sMsg.N.getValidCount();
        List<Integer> selectedBRows = new ArrayList<Integer>();
        selectedBRows.add(Integer.valueOf(delIdx));

        EZDMsg.copy(cMsg.B.get(delIdx), null, sMsg.N.no(nValCnt), null);
        ZYPEZDItemValueSetter.setValue(sMsg.N.no(nValCnt).prcMtrPkgBllgMtrPk_N1, cMsg.B.no(delIdx).prcMtrPkgBllgMtrPk_B1.getValue());
        ZYPEZDItemValueSetter.setValue(sMsg.N.no(nValCnt).ezUpTime_NU, cMsg.B.no(delIdx).ezUpTime_B1.getValue());
        ZYPEZDItemValueSetter.setValue(sMsg.N.no(nValCnt).ezUpTimeZone_NU, cMsg.B.no(delIdx).ezUpTimeZone_B1.getValue());
        sMsg.N.setValidCount(sMsg.N.getValidCount() + 1);

        List<Integer> selectedCRows = new ArrayList<Integer>();
        int mValCnt = sMsg.M.getValidCount();
        for (int i = 0; i < sMsg.C.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(sMsg.C.no(i).prcMtrPkgBllgMtrPk_C1)
                    && sMsg.C.no(i).prcMtrPkgBllgMtrPk_C1.getValue().compareTo(cMsg.B.no(delIdx).prcMtrPkgBllgMtrPk_B1.getValue()) == 0) {
                selectedCRows.add(Integer.valueOf(i));
                ZYPEZDItemValueSetter.setValue(sMsg.M.no(mValCnt).prcMtrPkgPhysMtrPk_M1, sMsg.C.no(i).prcMtrPkgPhysMtrPk_C1.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.M.no(mValCnt).ezUpTime_MU, sMsg.C.no(i).ezUpTime_CK);
                ZYPEZDItemValueSetter.setValue(sMsg.M.no(mValCnt).ezUpTimeZone_MU, sMsg.C.no(i).ezUpTimeZone_C1);
                mValCnt++;
            }
        }
        sMsg.M.setValidCount(mValCnt);
        ZYPTableUtil.deleteRows(sMsg.C, selectedCRows);

        for (; delIdx + 1 < sMsg.B.getValidCount(); delIdx++) {
            for (int j = 0; j < sMsg.C.getValidCount(); j++) {
                if (sMsg.C.no(j).xxCellIdx_CB.getValueInt() == delIdx + 1) {
                    sMsg.C.no(j).xxCellIdx_CB.setValue(delIdx);
                }
            }
        }

        ZYPTableUtil.deleteRows(sMsg.B, selectedBRows);
    }

    /**
     * saveCurrentPageToSMsgDelete
     * @param cMsg NMAL7060CMsg
     * @param sMsg NMAL7060SMsg
     */
    public static void saveToSMsgForDeleteNewRow(NMAL7060CMsg cMsg, NMAL7060SMsg sMsg) {

        int delIdx = cMsg.xxRadioBtn_B1.getValueInt();
        List<Integer> selectedBRows = new ArrayList<Integer>(Integer.valueOf(delIdx));
        selectedBRows.add(Integer.valueOf(delIdx));
        ZYPTableUtil.deleteRows(sMsg.B, selectedBRows);

        List<Integer> selectedCRows = new ArrayList<Integer>();
        for (int i = 0; i < sMsg.C.getValidCount(); i++) {
            if (sMsg.C.no(i).xxCellIdx_CB.getValueInt() == delIdx) {
                selectedCRows.add(Integer.valueOf(i));
            }
        }
        ZYPTableUtil.deleteRows(sMsg.C, selectedCRows);

        for (; delIdx + 1 < sMsg.B.getValidCount(); delIdx++) {
            for (int j = 0; j < sMsg.C.getValidCount(); j++) {
                if (sMsg.C.no(j).xxCellIdx_CB.getValueInt() == delIdx + 1) {
                    sMsg.C.no(j).xxCellIdx_CB.setValue(delIdx);
                }
            }
        }
    }

    /**
     * searchCounters
     * @param bizMsg NMAL7060CMsg
     * @param glblMsg NMAL7060SMsg
     * @param glblCmpyCd String
     * @param index int
     */
    public static void searchCounters(NMAL7060CMsg bizMsg, NMAL7060SMsg glblMsg, String glblCmpyCd, int index) {
        NMAL7060CommonLogic.saveCurrentCMsgToSMsgALL(bizMsg, glblMsg);

        boolean matchFlg = existenceCheck(bizMsg, glblMsg, index);
        ZYPTableUtil.clear(bizMsg.C);

        if (matchFlg) {
            NMAL7060CommonLogic.setToBizMsgFromGlblMsg(glblMsg, bizMsg, glblCmpyCd, index);
            bizMsg.setMessageInfo(ZZZM9003I, new String[]{"Search"});
        } else {
            S21SsmEZDResult ssmResult = null;
            if (ZYPCommonFunc.hasValue(bizMsg.B.no(index).prcMtrPkgBllgMtrPk_B1)) {
                ssmResult = NMAL7060Query.getInstance().getRegisteredSelectHardMeter(bizMsg, glblMsg, index);
            } else {
                ssmResult = NMAL7060Query.getInstance().getUnregisteredSelectHardMeter(bizMsg, glblMsg, index);
            }

            if (ssmResult.isCodeNormal()) {
                NMAL7060CommonLogic.setSearchResultToBizMsg(ssmResult, glblMsg, bizMsg, glblCmpyCd, index);
                NMAL7060CommonLogic.saveCurrentCMsgToSMsgALL(bizMsg, glblMsg);

                int queryResCnt = ssmResult.getQueryResultCount();

                if (queryResCnt > bizMsg.C.length()) {
                    bizMsg.setMessageInfo(NZZM0001W);
                }

                bizMsg.setMessageInfo(ZZZM9003I, new String[]{"Search"});
            } else {
                bizMsg.setMessageInfo(NMAM0007I);
            }
        }
    }

    // QC#24307 2018/08/20 Add start
    /**
     * searchCounters
     * @param bizMsg NMAL7060CMsg
     * @param glblMsg NMAL7060SMsg
     * @param glblCmpyCd String
     * @param index int
     */
    public static void refreshCountersFromMdl(NMAL7060CMsg bizMsg, NMAL7060SMsg glblMsg, BigDecimal mdlId, String glblCmpyCd) {
        ZYPTableUtil.clear(bizMsg.C);

        S21SsmEZDResult rsBllgMtr = NMAL7060Query.getInstance().getBllgMtrFromMdl(mdlId, ZYPDateUtil.getSalesDate());
        if (rsBllgMtr.isCodeNormal()) {
            List<Map<String, Object>> bllgMtrList = (List<Map<String, Object>>) rsBllgMtr.getResultObject();

            for (int bllgMtrIdx = 0; bllgMtrIdx < bizMsg.B.getValidCount(); bllgMtrIdx++) {

                for(Map<String, Object> bllgMtrMap : bllgMtrList) {

                    if (ZYPCommonFunc.hasValue(bizMsg.B.no(bllgMtrIdx).mtrLbCd_B1)
                            && ZYPCommonFunc.hasValue((String) bllgMtrMap.get("BLLG_MTR_LB_CD"))
                            && bizMsg.B.no(bllgMtrIdx).mtrLbCd_B1.getValue().equals((String) bllgMtrMap.get("BLLG_MTR_LB_CD"))) {

                        NMAL7060CommonLogic.searchCounters(bizMsg, glblMsg, glblCmpyCd, bllgMtrIdx);

                        S21SsmEZDResult ssmResult = NMAL7060Query.getInstance().getUnregisteredSelectHardMeter(bizMsg, glblMsg, bllgMtrIdx);

                        if (ssmResult.isCodeNormal()) {
                            NMAL7060CommonLogic.setSearchResultToBizMsgFromMdl(ssmResult, glblMsg, bizMsg, glblCmpyCd, bllgMtrIdx);
                            NMAL7060CommonLogic.saveCurrentCMsgToSMsgALL(bizMsg, glblMsg);

                            int queryResCnt = ssmResult.getQueryResultCount();

                            if (queryResCnt > bizMsg.C.length()) {
                                bizMsg.setMessageInfo(NZZM0001W);
                            }

                        }
                    }
                }
            }
        }
    }
    // QC#24307 2018/08/20 Add end

    /**
     * existenceCheck
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     * @param index int
     * @return Check result
     */
    public static boolean existenceCheck(NMAL7060CMsg bizMsg, NMAL7060SMsg glblMsg, int index) {

        boolean matchFlg = false;
        for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
            if (glblMsg.C.no(i).xxCellIdx_CB.getValueInt() == index) {
                matchFlg = true;
                break;
            }
        }
        return matchFlg;
    }

    // QC#24307 2018/08/20 Add start
    /**
     * clearHardCounter
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     * @param index int
     * @return Check result
     */
    public static void clearHardCounter(NMAL7060CMsg bizMsg, NMAL7060SMsg glblMsg, int index) {

        List<Integer> clearList = new ArrayList<Integer>();
        for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
            if (glblMsg.C.no(i).xxCellIdx_CB.getValueInt() == index) {
                clearList.add(i);
            }
        }
        ZYPTableUtil.deleteRows(glblMsg.C, clearList);
    }
    // QC#24307 2018/08/20 Add end

    /**
     * <pre>
     * checkDupAttrb
     * </pre>
     * @param msgAry EZDMsgArray
     * @param chkItemNmList Message Item Name for check.
     * @return Duplicate Index
     */
    public static Integer[] checkDupAttrb(EZDMsgArray msgAry, String[] chkItemNmList) {
        return checkDupAttrbByGrp(msgAry, chkItemNmList, null);
    }

    /**
     * <pre>
     * checkDupAttrbByGrp
     * </pre>
     * @param msgAry EZDMsgArray
     * @param chkItemNmList Message Item Name for check
     * @param grpItemNmList Message Item name for grouping
     * @return Duplicate Index
     */
    public static Integer[] checkDupAttrbByGrp(EZDMsgArray msgAry, String[] chkItemNmList, String[] grpItemNmList) {

        if (msgAry.getValidCount() <= 1) {
            return new Integer[0];
        }

        if (chkItemNmList == null
                || chkItemNmList.length == 0) {
            Integer[] errIdxList = new Integer[msgAry.getValidCount()];
            for (int i = 0; i < errIdxList.length; i++) {
                errIdxList[i] = i;
            }
            return errIdxList;
        }

        List<Integer> errIdxList = new ArrayList<Integer>(msgAry.getValidCount());

        for (int i = 0; i < msgAry.getValidCount() - 1; i++) {

            if (errIdxList.contains(i)) {
                continue;
            }

            EZDMsg msg1 = msgAry.get(i);
            String grpKey1 = makeCompVal(msg1, grpItemNmList);
            String compVal1 = makeCompVal(msg1, chkItemNmList);
            boolean dupFlg = false;

            for (int j = i + 1; j < msgAry.getValidCount(); j++) {

                EZDMsg msg2 = msgAry.get(j);
                String grpKey2 = makeCompVal(msg2, grpItemNmList);
                String compVal2 = makeCompVal(msg2, chkItemNmList);

                if (!grpKey1.equals(grpKey2)) {
                    continue;
                }

                if (compVal1.equals(compVal2)) {
                    errIdxList.add(j);
                    dupFlg = true;
                }
            }

            if (dupFlg) {
                errIdxList.add(0, i);
            }
        }

        return errIdxList.toArray(new Integer[0]);
    }

    /**
     * <pre>
     * checkDupAttrbByGrp
     * </pre>
     * @param msgAry EZDMsgArray
     * @param effFromItemNm Effective From Item Name
     * @param effThruItemNm Effective Thru Item Name
     * @param grpItemNmList Message Item name for grouping
     * @return Duplicate Index
     */
    public static Integer[] checkDupTermByGrp(EZDMsgArray msgAry, String effFromItemNm, String effThruItemNm, String[] grpItemNmList) {

        if (msgAry.getValidCount() <= 1) {
            return new Integer[0];
        }

        if (!ZYPCommonFunc.hasValue(effFromItemNm)
                || !ZYPCommonFunc.hasValue(effThruItemNm)) {
            Integer[] errIdxList = new Integer[msgAry.getValidCount()];
            for (int i = 0; i < errIdxList.length; i++) {
                errIdxList[i] = i;
            }
            return errIdxList;
        }

        List<Integer> errIdxList = new ArrayList<Integer>(msgAry.getValidCount());

        for (int i = 0; i < msgAry.getValidCount() - 1; i++) {

            if (errIdxList.contains(i)) {
                continue;
            }

            EZDMsg msg1 = msgAry.get(i);
            String grpKey1 = makeCompVal(msg1, grpItemNmList);
            String effFromDt1 = msg1.getValueString(effFromItemNm, 0);
            String effThruDt1 = msg1.getValueString(effThruItemNm, 0);
            if (!ZYPCommonFunc.hasValue(effThruDt1)) {
                effThruDt1 = "99991231";
            }

            boolean dupFlg = false;
            for (int j = i + 1; j < msgAry.getValidCount(); j++) {

                EZDMsg msg2 = msgAry.get(j);
                String grpKey2 = makeCompVal(msg2, grpItemNmList);
                String effFromDt2 = msg2.getValueString(effFromItemNm, 0);
                String effThruDt2 = msg2.getValueString(effThruItemNm, 0);
                if (!ZYPCommonFunc.hasValue(effThruDt2)) {
                    effThruDt2 = "99991231";
                }

                if (!grpKey1.equals(grpKey2)) {
                    continue;
                }

                if (effFromDt1.compareTo(effFromDt2) < 0) {

                    if (effThruDt1.compareTo(effFromDt2) >= 0) {
                        // error
                        errIdxList.add(j);
                        dupFlg = true;
                    }

                } else if (effFromDt1.compareTo(effFromDt2) > 0) {

                    if (effFromDt1.compareTo(effThruDt2) <= 0) {
                        // error
                        errIdxList.add(j);
                        dupFlg = true;
                    }

                } else {
                    // error
                    errIdxList.add(j);
                    dupFlg = true;
                }

            }

            if (dupFlg) {
                errIdxList.add(0, i);
            }
        }

        return errIdxList.toArray(new Integer[0]);
    }



    /**
     * <pre>
     * makeCompVal
     * </pre>
     * @param msg EZDMsg
     * @param itemNmList Message Item Name
     * @return
     */
    private static String makeCompVal(EZDMsg msg, String[] itemNmList) {

        if (itemNmList == null) {
            return "";
        }

        StringBuilder compVal = new StringBuilder();

        for (String chkAttrbNm : itemNmList) {
            EZDItemAttribute itemAttrb = msg.getAttr(chkAttrbNm);

            String attrbVal = "";
            switch (itemAttrb.getType()) {
                case EZDItemAttrDefines.TYPE_SEISU_SYOSU:
                    attrbVal = msg.getValueBigDecimal(chkAttrbNm, 0).toString();
                    break;
                default:
                    attrbVal = msg.getValueString(chkAttrbNm, 0);
            }
            compVal.append(attrbVal);
            compVal.append(",");
        }

        return compVal.toString();
    }

    // Add Start 2017/02/14 QC#17529
    /**
     * <pre>
     * Check Message Value
     * </pre>
     * @param bizMsg NMAL7060CMsg
     * @param glblMsg NMAL7060SMsg
     * @return
     */
    private static boolean checkChangedMdlValue(NMAL7060CMsg bizMsg, NMAL7060SMsg glblMsg) {

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

            NMAL7060_ACMsg cMsg = bizMsg.A.no(i);
            NMAL7060_ASMsg sMsg = glblMsg.A.no(i);

            if (!cMsg.effThruDt_A1.getValue().equals(sMsg.effThruDt_A1.getValue())) {
                return true;
            }
        }

        return false;
    }
    // Add End 2017/02/14 QC#17529
}
