/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLBL3080.common;

import static business.blap.NLBL3080.contant.NLBL3080Constant.MDSE_8_DIGIT;
import static business.blap.NLBL3080.contant.NLBL3080Constant.NLBL3080_NOT_ALLC_WH;
import static business.blap.NLBL3080.contant.NLBL3080Constant.TAB_ID_ORD;
import static business.blap.NLBL3080.contant.NLBL3080Constant.TAB_ID_ORD_LINE;
import static parts.common.EZDCommonFunc.isNumberCheck;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import parts.dbcommon.EZDFastTBLAccessor;
import parts.dbcommon.EZDTBLAccessor;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NLBL3080.NLBL3080CMsg;
import business.blap.NLBL3080.NLBL3080Query;
import business.blap.NLBL3080.NLBL3080SMsg;
import business.blap.NLBL3080.NLBL3080_ASMsg;
import business.blap.NLBL3080.NLBL3080_BSMsg;
import business.blap.NLBL3080.contant.NLBL3080Constant;
import business.db.DS_COND_CONSTTMsg;
import business.db.DS_COND_CONSTTMsgArray;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.RTL_WHTMsg;
import business.db.TOCTMsg;
import business.file.NLBL3080F00FMsg;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_PLN_DPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/20   CITS            H.Sugawara      Create          N/A
 * 03/18/2016   CITS            T.Tokutomi      Update          QC#4596
 * 07/26/2017   CITS            S.Endo          Update          Sol#072(QC#18386)
 * 08/15/2017   Fujitsu         T.Murai         Update          QC#20622
 * 02/05/2018   CITS            K.Ogino         Update          QC#23841
 * 2018/07/23   CITS            K.Ogino         Update          QC#27047
 * 12/21/2018   CITS            T.Tokutomi      Update          QC#29214
 *</pre>
 */
public class NLBL3080CommonLogic {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    /**
     * setSearchOrdResultToSMsg
     * @param ssmResult S21SsmEZDResult
     * @param sMsg NLBL3080SMsg
     */
    public static void setSearchOrdResultToSMsg(S21SsmEZDResult ssmResult, NLBL3080SMsg sMsg) {

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

        ZYPTableUtil.clear(sMsg.A);
        sMsg.A.setValidCount(0);

        for (int i = 0; i < resultList.size() && i < sMsg.A.length(); i++) {

            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
            NLBL3080_ASMsg sMsgALine = sMsg.A.no(i);

            ZYPEZDItemValueSetter.setValue(sMsgALine.xxNum_A1, new BigDecimal(i));
            ZYPEZDItemValueSetter.setValue(sMsgALine.cpoOrdNum_A1, (String) resultMap.get("CPO_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.dsOrdCatgDescTxt_A1, (String) resultMap.get("DS_ORD_CATG_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.dsOrdTpDescTxt_A1, (String) resultMap.get("DS_ORD_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.dsOrdPosnNum_A1, (String) resultMap.get("DS_ORD_POSN_NUM"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.dplyLineNum_A1, (String) resultMap.get("DS_ORD_POSN_NUM"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.xxSetFlg_A1, (String) resultMap.get("ALLOC_FLG"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.shipToCustLocCd_A1, (String) resultMap.get("SHIP_TO_CUST_LOC_CD"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.t_MdlNm_A1, (String) resultMap.get("T_MDL_NM"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.pickSvcConfigMstrPk_A1, (BigDecimal) resultMap.get("PICK_SVC_CONFIG_MSTR_PK"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.dsAcctNm_A1, (String) resultMap.get("DS_ACCT_NM"));

            if (ZYPCommonFunc.hasValue((String) resultMap.get("SHIP_TO_FIRST_LINE_ADDR"))) {

                StringBuffer allAddr = new StringBuffer((String) resultMap.get("SHIP_TO_FIRST_LINE_ADDR"));

                if (ZYPCommonFunc.hasValue((String) resultMap.get("SHIP_TO_SCD_LINE_ADDR"))) {

                    allAddr.append((String) resultMap.get("SHIP_TO_SCD_LINE_ADDR"));
                }

                ZYPEZDItemValueSetter.setValue(sMsgALine.xxAllLineAddr_A1, allAddr.toString());
            }

            ZYPEZDItemValueSetter.setValue(sMsgALine.shipToCtyAddr_A1, (String) resultMap.get("SHIP_TO_CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.shipToStCd_A1, (String) resultMap.get("SHIP_TO_ST_CD"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.xxSmryLineFlg_A1, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(sMsgALine.xxSmryLineFlg_A2, ZYPConstant.FLG_ON_Y);

            sMsg.A.setValidCount(i + 1);
        }
    }

    /**
     * setSearchOrdLineResultToSMsg
     * @param ssmResult S21SsmEZDResult
     * @param sMsg NLBL3080SMsg
     * @param glblCmpyCd String
     */
    public static void setSearchOrdLineResultToSMsg(S21SsmEZDResult ssmResult, NLBL3080SMsg sMsg, String glblCmpyCd) {

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

        ZYPTableUtil.clear(sMsg.B);
        sMsg.B.setValidCount(0);

        for (int i = 0; i < resultList.size() && i < sMsg.B.length(); i++) {

            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
            NLBL3080_BSMsg sMsgBLine = sMsg.B.no(i);

            ZYPEZDItemValueSetter.setValue(sMsgBLine.xxNum_B1, new BigDecimal(i));
            ZYPEZDItemValueSetter.setValue(sMsgBLine.cpoOrdNum_B1, (String) resultMap.get("CPO_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(sMsgBLine.dsOrdPosnNum_B1, (String) resultMap.get("DS_ORD_POSN_NUM"));
            ZYPEZDItemValueSetter.setValue(sMsgBLine.dplyLineNum_B1, (String) resultMap.get("DPLY_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(sMsgBLine.cpoDtlLineNum_B1, (String) resultMap.get("CPO_DTL_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(sMsgBLine.cpoDtlLineSubNum_B1, (String) resultMap.get("CPO_DTL_LINE_SUB_NUM"));
            ZYPEZDItemValueSetter.setValue(sMsgBLine.svcConfigMstrPk_B1, (BigDecimal) resultMap.get("SVC_CONFIG_MSTR_PK"));
            ZYPEZDItemValueSetter.setValue(sMsgBLine.mdseCd_B1, (String) resultMap.get("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(sMsgBLine.mdseDescShortTxt_B1, (String) resultMap.get("MDSE_DESC_SHORT_TXT"));
            ZYPEZDItemValueSetter.setValue(sMsgBLine.t_MdlNm_B1, (String) resultMap.get("T_MDL_NM"));
            ZYPEZDItemValueSetter.setValue(sMsgBLine.coaMdseTpCd_B1, (String) resultMap.get("COA_MDSE_TP_CD"));
            ZYPEZDItemValueSetter.setValue(sMsgBLine.setMdseCd_B1, (String) resultMap.get("SET_MDSE_CD"));
            // QC#23841
            ZYPEZDItemValueSetter.setValue(sMsgBLine.xxSupdFlg_B1, getSupdFlg(glblCmpyCd, sMsgBLine.mdseCd_B1.getValue(), (String) resultMap.get("ORD_TAKE_MDSE_CD"), (BigDecimal) resultMap.get("SUPD_CNT")));
            ZYPEZDItemValueSetter.setValue(sMsgBLine.backOrdImpctTpDescTxt_B1, (String) resultMap.get("BACK_ORD_IMPCT_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(sMsgBLine.stkStsCd_B1, (String) resultMap.get("STK_STS_CD"));

            // 08/15/2017 Fujitsu T.Murai Del QC#20622 START
//            //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) START
//            if (ZYPCommonFunc.hasValue((String) resultMap.get("T_MDL_NM"))) {
//                S21SsmEZDResult ssmResult2 = NLBL3080Query.getInstance().getModelCriticality(glblCmpyCd, (String) resultMap.get("CPO_ORD_NUM"), (String) resultMap.get("DS_ORD_POSN_NUM"), (String) resultMap.get("MDSE_CD"));
//                if (ssmResult.isCodeNormal()) {
//                    Map<String, Object> modelResult = (Map<String, Object>) ssmResult2.getResultObject();
//                    if (modelResult != null && modelResult.size() > 0) {
//                        ZYPEZDItemValueSetter.setValue(sMsgBLine.backOrdImpctTpDescTxt_B1, (String) modelResult.get("BACK_ORD_IMPCT_TP_DESC_TXT"));
//                    }
//                }
//            }
//            //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) END
            // 08/15/2017 Fujitsu T.Murai Del QC#20622 END
            if (SHPG_STS.VALIDATED.equals((String) resultMap.get("SHPG_STS_CD"))) {

                ZYPEZDItemValueSetter.setValue(sMsgBLine.ordQty_B1, (BigDecimal) resultMap.get("ORD_QTY"));

            } else {

                ZYPEZDItemValueSetter.setValue(sMsgBLine.ordQty_B1, BigDecimal.ZERO);
            }

            if (SHPG_STS.HARD_ALLOCATED.equals((String) resultMap.get("SHPG_STS_CD"))) {

                ZYPEZDItemValueSetter.setValue(sMsgBLine.ordQty_B2, (BigDecimal) resultMap.get("ORD_QTY"));

            } else {

                ZYPEZDItemValueSetter.setValue(sMsgBLine.ordQty_B2, BigDecimal.ZERO);
            }
            // QC#27047
            if (ZYPCommonFunc.hasValue((BigDecimal) resultMap.get("INVTY_AVAL_QTY"))) {
                ZYPEZDItemValueSetter.setValue(sMsgBLine.invtyAvalQty_B1, (BigDecimal) resultMap.get("INVTY_AVAL_QTY"));
            } else {
                ZYPEZDItemValueSetter.setValue(sMsgBLine.invtyAvalQty_B1, BigDecimal.ZERO);
            }
            ZYPEZDItemValueSetter.setValue(sMsgBLine.serNum_B1, (String) resultMap.get("SER_NUM"));
            ZYPEZDItemValueSetter.setValue(sMsgBLine.pickSvcConfigMstrPk_B1, (BigDecimal) resultMap.get("PICK_SVC_CONFIG_MSTR_PK"));
            ZYPEZDItemValueSetter.setValue(sMsgBLine.rddDt_B1, (String) resultMap.get("RDD_DT"));
            ZYPEZDItemValueSetter.setValue(sMsgBLine.dsOrdLineCatgDescTxt_B1, (String) resultMap.get("DS_ORD_LINE_CATG_DESC_TXT"));
            if (ZYPCommonFunc.hasValue((BigDecimal) resultMap.get("INVTY_AVAL_QTY"))) {
                BigDecimal invtyAvalQty = (BigDecimal) resultMap.get("INVTY_AVAL_QTY");
                if (BigDecimal.ZERO.compareTo(invtyAvalQty) == 0) {
                    ZYPEZDItemValueSetter.setValue(sMsgBLine.ordLineStsNm_B1, ZYPCodeDataUtil.getName(SHPG_PLN_DPLY_STS.class, glblCmpyCd, SHPG_PLN_DPLY_STS.BACK_ORDER));
                } else if (SHPG_STS.HARD_ALLOCATED.equals((String) resultMap.get("SHPG_STS_CD"))) {
                    ZYPEZDItemValueSetter.setValue(sMsgBLine.ordLineStsNm_B1, ZYPCodeDataUtil.getName(SHPG_PLN_DPLY_STS.class, glblCmpyCd, SHPG_PLN_DPLY_STS.WAITING_PICK));
                } else if (SHPG_STS.VALIDATED.equals((String) resultMap.get("SHPG_STS_CD"))) {
                    ZYPEZDItemValueSetter.setValue(sMsgBLine.ordLineStsNm_B1, ZYPCodeDataUtil.getName(SHPG_PLN_DPLY_STS.class, glblCmpyCd, SHPG_PLN_DPLY_STS.PENDING_ALLOCATION));
                } else {
                    ZYPEZDItemValueSetter.setValue(sMsgBLine.ordLineStsNm_B1, ZYPCodeDataUtil.getName(SHPG_PLN_DPLY_STS.class, glblCmpyCd, SHPG_PLN_DPLY_STS.PENDING_ALLOCATION));
                }
            } else {
                ZYPEZDItemValueSetter.setValue(sMsgBLine.ordLineStsNm_B1, ZYPCodeDataUtil.getName(SHPG_PLN_DPLY_STS.class, glblCmpyCd, SHPG_PLN_DPLY_STS.BACK_ORDER));
            }
            ZYPEZDItemValueSetter.setValue(sMsgBLine.rtlWhNm_B1, (String) resultMap.get("RTL_WH_NM"));
            ZYPEZDItemValueSetter.setValue(sMsgBLine.rtlSwhCd_B1, (String) resultMap.get("RTL_SWH_CD"));

            // Hidden
            ZYPEZDItemValueSetter.setValue(sMsgBLine.rtlWhCd_B1, (String) resultMap.get("RTL_WH_CD"));
            ZYPEZDItemValueSetter.setValue(sMsgBLine.rtlSwhNm_B1, (String) resultMap.get("RTL_SWH_NM"));
            ZYPEZDItemValueSetter.setValue(sMsgBLine.supdLockFlg_B1, (String) resultMap.get("SUPD_LOCK_FLG"));
            ZYPEZDItemValueSetter.setValue(sMsgBLine.shpgPlnNum_B1, (String) resultMap.get("SHPG_PLN_NUM"));
            ZYPEZDItemValueSetter.setValue(sMsgBLine.setShpgPlnNum_B1, (String) resultMap.get("SET_SHPG_PLN_NUM"));
            ZYPEZDItemValueSetter.setValue(sMsgBLine.trxSrcTpCd_B1, (String) resultMap.get("TRX_SRC_TP_CD"));
            ZYPEZDItemValueSetter.setValue(sMsgBLine.invtyLocCd_B1, (String) resultMap.get("INVTY_LOC_CD"));
            ZYPEZDItemValueSetter.setValue(sMsgBLine.reqShpgSvcLvlCd_B1, (String) resultMap.get("REQ_SHPG_SVC_LVL_CD"));
            ZYPEZDItemValueSetter.setValue(sMsgBLine.cpoOrdTs_B1, (String) resultMap.get("CPO_ORD_TS"));
            ZYPEZDItemValueSetter.setValue(sMsgBLine.mdlId_B1, (BigDecimal) resultMap.get("MDL_ID"));
            ZYPEZDItemValueSetter.setValue(sMsgBLine.invtyAcctCd_B1, (String) resultMap.get("INVTY_ACCT_CD"));
            ZYPEZDItemValueSetter.setValue(sMsgBLine.ezUpTime_SP, (String) resultMap.get("EZUPTIME_SP"));
            ZYPEZDItemValueSetter.setValue(sMsgBLine.ezUpTimeZone_SP, (String) resultMap.get("EZUPTIMEZONE_SP"));

            // Default Expand
            ZYPEZDItemValueSetter.setValue(sMsgBLine.xxSmryLineFlg_B1, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(sMsgBLine.xxSmryLineFlg_B2, ZYPConstant.FLG_ON_Y);

            sMsg.B.setValidCount(i + 1);
        }
    }

    /**
     * getSupdFlg Mod QC#23841
     * @param glblCmpyCd String
     * @param mdseCd String
     * @param ordTakeMdseCd String
     * @param supdRelnCnt BigDecimal
     */
    private static String getSupdFlg(String glblCmpyCd, String mdseCd, String ordTakeMdseCd, BigDecimal supdRelnCnt) {

        if (ZYPCommonFunc.hasValue(mdseCd) && mdseCd.length() >= MDSE_8_DIGIT) {

            // Check Order Take Merchandise
            if (ZYPCommonFunc.hasValue(ordTakeMdseCd)) {

                return ZYPConstant.FLG_ON_Y;
            }
        }

        if (supdRelnCnt != null && supdRelnCnt.intValue() > 0) {

            return ZYPConstant.FLG_ON_Y;
        }

        // QC#29214 Add
        if (checkCompatibleRelation(glblCmpyCd, mdseCd)) {
            return ZYPConstant.FLG_ON_Y;
        }

        return ZYPConstant.FLG_OFF_N;
    }

    /**
     * checkCompatibleRelation
     * @since QC#29214
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return true(include compatible Item)
     */
    public static boolean checkCompatibleRelation(String glblCmpyCd, String mdseCd) {

        String targetMdseRelnTpCsv = ZYPCodeDataUtil.getVarCharConstValue("NPZC1010_CMPT_MDSE_RELN_TP", glblCmpyCd);

        List<String> targetMdseRelnTpList = null;
        if (targetMdseRelnTpCsv != null) {
            targetMdseRelnTpList = Arrays.asList(targetMdseRelnTpCsv.split(","));
        }

        S21SsmEZDResult rs = NLBL3080Query.getInstance().getCompatibleItem(glblCmpyCd, mdseCd, targetMdseRelnTpList);

        if (rs.isCodeNormal()) {
            List<String> cmpItemList = (List<String>) rs.getResultObject();

            if (cmpItemList != null && !cmpItemList.isEmpty()) {
                return true;
            }
        }

        return false;
    }
    
    /**
     * checkSupdReln
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return boolean
     */
    private static boolean checkSupdReln(String glblCmpyCd, String mdseCd) {

        S21SsmEZDResult ssmResult = NLBL3080Query.getInstance().getSupdReln(glblCmpyCd, mdseCd);

        if (ssmResult.isCodeNormal()) {

            BigDecimal supdRelnCnt  = (BigDecimal) ssmResult.getResultObject();

            if (supdRelnCnt != null && supdRelnCnt.intValue() > 0) {

                return true;
            }
        }

        return false;
    }

    /**
     * It copy 'NLBL3080CMsg.A' to 'NLBL3080SMsg.A' .
     * @param cMsg bizMsgcMsg
     * @param sMsg globalMsg
     */
    public static void saveCurrentPageToSMsgOrd(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg) {

        cMsg.clearErrorInfo();
        cMsg.A.clearErrorInfo();
        sMsg.clearErrorInfo();
        sMsg.A.clearErrorInfo();

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {

            EZDMsg.copy(cMsg.A.no(i), null, sMsg.A.no(cMsg.A.no(i).xxNum_A1.getValueInt()), null);
        }
    }

    /**
     * It copy 'NLBL3080CMsg.B' to 'NLBL3080SMsg.B' .
     * @param cMsg bizMsg
     * @param sMsg globalMsg
     */
    public static void saveCurrentPageToSMsgOrdLine(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg) {

        cMsg.clearErrorInfo();
        cMsg.B.clearErrorInfo();
        sMsg.clearErrorInfo();
        sMsg.B.clearErrorInfo();

        for (int i = 0; i < cMsg.B.getValidCount(); i++) {

            EZDMsg.copy(cMsg.B.no(i), null, sMsg.B.no(cMsg.B.no(i).xxNum_B1.getValueInt()), null);
        }
    }

    /**
     * Pagenation.
     * @param bizMsg NLBL3080CMsg
     * @param globalMsg NLBL3080SMsg
     * @param fromIdx int
     */
    public static void pagenation(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg, int fromIdx) {

        int sMsgIdx = 0;
        int cMsgIdx = 0;

        if (TAB_ID_ORD.equals(cMsg.xxDplyTab.getValue())) {

            for (int i = 0; i < sMsg.A.getValidCount(); i++) {

                if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxSmryLineFlg_A2.getValue())) {

                    if (fromIdx <= sMsgIdx && cMsgIdx < cMsg.A.length()) {

                        EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(cMsgIdx), null);
                        cMsgIdx++;
                    }

                    sMsgIdx++;
                }
            }

            // set value to pagenation items
            cMsg.A.setValidCount(cMsgIdx);
            cMsg.xxPageShowFromNum_A.setValue(fromIdx + 1);
            cMsg.xxPageShowToNum_A.setValue(fromIdx + cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum_A.setValue(sMsgIdx);

        } else if (TAB_ID_ORD_LINE.equals(cMsg.xxDplyTab.getValue())) {

            for (int i = 0; i < sMsg.B.getValidCount(); i++) {

                if (ZYPConstant.FLG_ON_Y.equals(sMsg.B.no(i).xxSmryLineFlg_B2.getValue())) {

                    if (fromIdx <= sMsgIdx && cMsgIdx < cMsg.B.length()) {

                        EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(cMsgIdx), null);
                        cMsgIdx++;
                    }

                    sMsgIdx++;
                }
            }

            // set value to pagenation items
            cMsg.B.setValidCount(cMsgIdx);
            cMsg.xxPageShowFromNum_B.setValue(fromIdx + 1);
            cMsg.xxPageShowToNum_B.setValue(fromIdx + cMsg.B.getValidCount());
            cMsg.xxPageShowOfNum_B.setValue(sMsgIdx);
        }
    }

    /**
     * getPageStartRowIndex
     * @param index int
     * @param bizMsg NLBL3080CMsg
     * @param globalMsg NLBL3080SMsg
     * @return Index int
     */
    public static int getPageStartRowIndex(int index, NLBL3080CMsg bizMsg, NLBL3080SMsg globalMsg) {

        String preCpoNum = "";
        String predsOrdPosnNum = "";
        int startIndex = 0;
        int convIndex = 0;

        if (TAB_ID_ORD.equals(bizMsg.xxDplyTab.getValue())) { 

            for (int i = 0; i < globalMsg.A.getValidCount(); i++) {

                if (index == i) {

                    index = convIndex;
                    break;
                }

                if (!ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(i).xxSmryLineFlg_A1.getValue())) {

                    convIndex++;

                } else if (ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(i).xxSmryLineFlg_A1.getValue()) && !preCpoNum.equals(globalMsg.A.no(i).cpoOrdNum_A1.getValue())) {

                    // Summary Flag == Y
                    convIndex++;
                }

                preCpoNum = globalMsg.A.no(i).cpoOrdNum_A1.getValue();
            }

            startIndex = (index / bizMsg.A.length()) * bizMsg.A.length();

        } else if (TAB_ID_ORD_LINE.equals(bizMsg.xxDplyTab.getValue())) {

            for (int i = 0; i < globalMsg.B.getValidCount(); i++) {

                if (index == i) {

                    index = convIndex;
                    break;
                }

                if (!ZYPConstant.FLG_ON_Y.equals(globalMsg.B.no(i).xxSmryLineFlg_B1.getValue())) {

                    convIndex++;

                } else if (ZYPConstant.FLG_ON_Y.equals(globalMsg.B.no(i).xxSmryLineFlg_B1.getValue())
                        && !preCpoNum.equals(globalMsg.B.no(i).cpoOrdNum_B1.getValue())
                        && !predsOrdPosnNum.equals(globalMsg.B.no(i).dsOrdPosnNum_B1.getValue())) {

                    // Summary Flag == Y
                    convIndex++;
                }

                preCpoNum = globalMsg.B.no(i).cpoOrdNum_B1.getValue();
                predsOrdPosnNum = globalMsg.B.no(i).dsOrdPosnNum_B1.getValue();
            }

            startIndex = (index / bizMsg.B.length()) * bizMsg.B.length();
        }

        return startIndex;
    }

    /**
     * sort
     * @param sMsgArray EZDSMsgArray
     * @param sortItemNm String
     * @param sortOrdBy String
     * @param baseContents String[][]
     * @param nullLast boolean
     */
    public static void sort(EZDSMsgArray sMsgArray, String sortItemNm, String sortOrdBy, String[][] baseContents, boolean nullLast) {

        S21SortTarget sortTarget = new S21SortTarget(sMsgArray, baseContents);
        S21SortKey sortKey = new S21SortKey();
        sortKey.add(sortItemNm, sortOrdBy);

        if (nullLast) {

            S21EZDMsgArraySort.sortNullsLast(sortTarget, sortKey, 0, sMsgArray.getValidCount());

        } else {

            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsgArray.getValidCount());
        }
    }

    /**
     * allExpansion
     * @param globalMsg NLBL3080SMsg
     */
    public static void allExpansion(NLBL3080SMsg globalMsg) {

        for (int i = 0; i < globalMsg.B.getValidCount(); i++) {

            ZYPEZDItemValueSetter.setValue(globalMsg.B.no(i).xxSmryLineFlg_B1, ZYPConstant.FLG_OFF_N);
        }
    }

    /**
     * isExistSaveSearchName
     * @param bizMsg NLBL3080CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NLBL3080CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {

            return false;
        }

        for (int i = 0; i < bizMsg.srchOptNm_L.length(); i++) {

            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_L.no(i))) {

                return false;
            }

            if (bizMsg.srchOptNm_S.getValue().equals(bizMsg.srchOptNm_L.no(i).getValue())) {

                if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S) && bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_L.no(i).getValue()) == 0) {

                    return false;
                }

                return true;
            }
        }

        return false;
    }

    /**
     * callNszc0330forSaveSearch
     * @param bizMsg NLBL3080CMsg
     * @param glblMsg NLBL3080SMsg
     */
    public static void callNszc0330forSaveSearch(NLBL3080CMsg bizMsg, NLBL3080SMsg glblMsg) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_INSERT_UPDATE);

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S) || isSameSaveSearchName(bizMsg)) {

            ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        }

        if (ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {

            ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_S);

        } else {

            setSelectSaveSearchName(pMsg, bizMsg);
        }

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, NLBL3080Constant.BUSINESS_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, bizMsg.usrId.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, bizMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, bizMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, bizMsg.serNum);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, bizMsg.rtlWhCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, bizMsg.shipToCustCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, bizMsg.tocCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, bizMsg.mdseCd);

        if (ZYPCommonFunc.hasValue(bizMsg.xxOrdDt_FR)) {

            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_09, bizMsg.xxOrdDt_FR.getValue());
        }

        if (ZYPCommonFunc.hasValue(bizMsg.xxOrdDt_TO)) {

            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_10, bizMsg.xxOrdDt_TO.getValue());
        }

        if (ZYPCommonFunc.hasValue(bizMsg.rddDt_FR)) {

            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_11, bizMsg.rddDt_FR.getValue());
        }

        if (ZYPCommonFunc.hasValue(bizMsg.rddDt_TO)) {

            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_12, bizMsg.rddDt_TO.getValue());
        }

        if (ZYPCommonFunc.hasValue(bizMsg.svcConfigMstrPk)) {

            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_13, bizMsg.svcConfigMstrPk.getValue().toString());
        }

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_14, bizMsg.t_MdlNm);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_15, bizMsg.xxChkBox_BO);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_16, bizMsg.xxChkBox_NS);
        // QC#27047
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_17, bizMsg.xxChkBox_AL);

        if (callNszc0330(bizMsg, pMsg)) {

            createSavedSearchOptionsPullDown(bizMsg);
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptPk_S, pMsg.srchOptPk);
            bizMsg.setMessageInfo("ZZZM9003I", new String[] {converter.convLabel2i18nLabel(NLBL3080Constant.SCREEN_ID, "Save Search") });
        }
    }

    /**
     * isSameSaveSearchName
     * @param cMsg NLBL3080CMsg
     * @return boolean
     */
    private static boolean isSameSaveSearchName(NLBL3080CMsg cMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_S)) {

            return false;
        }

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_S)) {

            return false;
        }

        for (int i = 0; i < cMsg.srchOptNm_L.length(); i++) {

            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_L.no(i))) {

                return false;
            }

            if (cMsg.srchOptPk_S.getValue().compareTo(cMsg.srchOptPk_L.no(i).getValue()) == 0) {

                if (cMsg.srchOptNm_S.getValue().equals(cMsg.srchOptNm_L.no(i).getValue())) {

                    return true;
                }

                return false;
            }
        }

        return false;
    }

    /**
     * setSelectSaveSearchName
     * @param pMsg NSZC033001PMsg
     * @param bizMsg NLBL3080CMsg
     */
    public static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NLBL3080CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {

            return;
        }

        for (int i = 0; i < bizMsg.srchOptNm_L.length(); i++) {

            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_L.no(i))) {

                return;
            }

            if (bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_L.no(i).getValue()) == 0) {

                ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_L.no(i));
            }
        }

        return;
    }

    /**
     * callNszc0330
     * @param bizMsg NLBL3080CMsg
     * @param pMsg NSZC033001PMsg
     * @return boolean
     */
    private static boolean callNszc0330(NLBL3080CMsg bizMsg, NSZC033001PMsg pMsg) {

        NSZC033001 api = new NSZC033001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (!S21ApiUtil.getXxMsgIdList(pMsg).isEmpty()) {

            for (int j = 0; j < pMsg.xxMsgIdList.length(); j++) {

                if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(j).xxMsgId)) {

                    String msgId = pMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                    bizMsg.setMessageInfo(msgId);

                    if (msgId.endsWith("E")) {

                        bizMsg.srchOptPk_S.setErrorInfo(1, msgId);
                        bizMsg.srchOptNm_S.setErrorInfo(1, msgId);
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * createSavedSearchOptionsPullDown
     * @param bizMsg NLBL3080CMsg
     */
    public static void createSavedSearchOptionsPullDown(NLBL3080CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NLBL3080Query.getInstance().getSavedSearchOptionList(bizMsg);

        if (!ssmResult.isCodeNormal()) {

            bizMsg.srchOptPk_L.clear();
            bizMsg.srchOptNm_L.clear();
            return;
        }

        bizMsg.srchOptPk_L.clear();
        bizMsg.srchOptNm_L.clear();
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

        for (int i = 0; i < resultList.size() && i < bizMsg.srchOptPk_L.length(); i++) {

            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
            bizMsg.srchOptPk_L.no(i).setValue((BigDecimal) resultMap.get("SRCH_OPT_PK"));
            bizMsg.srchOptNm_L.no(i).setValue((String) resultMap.get("SRCH_OPT_NM"));
        }
    }

    /**
     * callNszc0330forDeleteSearch
     * @param bizMsg NLBL3080CMsg
     * @param glblMsg NLBL3080SMsg
     * @param userId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forDeleteSearch(NLBL3080CMsg bizMsg, NLBL3080SMsg glblMsg) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, NLBL3080Constant.BUSINESS_ID);

        if (callNszc0330(bizMsg, pMsg)) {

            createSavedSearchOptionsPullDown(bizMsg);
            bizMsg.srchOptNm_S.clear();
            bizMsg.setMessageInfo("ZZZM9003I", new String[] {converter.convLabel2i18nLabel(NLBL3080Constant.SCREEN_ID, "Delete Search") });
        }
    }

    /**
     * callNszc0330forSearchOption
     * @param bizMsg NLBL3080CMsg
     * @param glblMsg NLBL3080SMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSearchOption(NLBL3080CMsg bizMsg, NLBL3080SMsg glblMsg) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, bizMsg.usrId.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, NLBL3080Constant.BUSINESS_ID);

        if (!callNszc0330(bizMsg, pMsg)) {
            return; // error
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.srchOptNm_S, pMsg.srchOptNm);
        ZYPEZDItemValueSetter.setValue(bizMsg.cpoOrdNum, pMsg.srchOptTxt_01);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgCd, pMsg.srchOptTxt_02);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd, pMsg.srchOptTxt_03);
        ZYPEZDItemValueSetter.setValue(bizMsg.serNum, pMsg.srchOptTxt_04);
        ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhCd, pMsg.srchOptTxt_05);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustCd, pMsg.srchOptTxt_06);
        ZYPEZDItemValueSetter.setValue(bizMsg.tocCd, pMsg.srchOptTxt_07);
        ZYPEZDItemValueSetter.setValue(bizMsg.mdseCd, pMsg.srchOptTxt_08);
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_09.getValue(), "yyyyMMdd")) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxOrdDt_FR, pMsg.srchOptTxt_09.getValue());
        } else {
            bizMsg.xxOrdDt_FR.clear();
        }
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_10.getValue(), "yyyyMMdd")) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxOrdDt_TO, pMsg.srchOptTxt_10.getValue());
        } else {
            bizMsg.xxOrdDt_TO.clear();
        }
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_11.getValue(), "yyyyMMdd")) {
            ZYPEZDItemValueSetter.setValue(bizMsg.rddDt_FR, pMsg.srchOptTxt_11.getValue());
        } else {
            bizMsg.rddDt_FR.clear();
        }
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_12.getValue(), "yyyyMMdd")) {
            ZYPEZDItemValueSetter.setValue(bizMsg.rddDt_TO, pMsg.srchOptTxt_12.getValue());
        } else {
            bizMsg.rddDt_TO.clear();
        }
        if (isNumberCheck(pMsg.srchOptTxt_13.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.svcConfigMstrPk, new BigDecimal(pMsg.srchOptTxt_13.getValue()));
        } else {
            bizMsg.svcConfigMstrPk.clear();
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.t_MdlNm, pMsg.srchOptTxt_14);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_BO, pMsg.srchOptTxt_15);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_NS, pMsg.srchOptTxt_16);
        // QC#27047
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_AL, pMsg.srchOptTxt_17);
    }

    /**
     * getPageShowOfNumA
     * @param globalMsg NLBL3080SMsg
     * @return int
     */
    public static int getPageShowOfNumA(NLBL3080SMsg globalMsg) {

        int num = 0;
        String preCpoOrdNum = "";

        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            if (!ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(i).xxSmryLineFlg_A1.getValue())) {
                num++;
            } else if (ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(i).xxSmryLineFlg_A1.getValue()) && !preCpoOrdNum.equals(globalMsg.A.no(i).cpoOrdNum_A1.getValue())) {
                num++;
            }
            preCpoOrdNum = globalMsg.A.no(i).cpoOrdNum_A1.getValue();
        }
        return num;
    }

    /**
     * getPageShowOfNumB
     * @param globalMsg NLBL3080SMsg
     * @return int
     */
    public static int getPageShowOfNumB(NLBL3080SMsg globalMsg) {
        int num = 0;
        String preCpoOrdNum = "";
        for (int i = 0; i < globalMsg.B.getValidCount(); i++) {
            if (!ZYPConstant.FLG_ON_Y.equals(globalMsg.B.no(i).xxSmryLineFlg_B1.getValue())) {
                num++;
            } else if (ZYPConstant.FLG_ON_Y.equals(globalMsg.B.no(i).xxSmryLineFlg_B1.getValue()) && !preCpoOrdNum.equals(globalMsg.B.no(i).cpoOrdNum_B1.getValue())) {
                num++;
            }
            preCpoOrdNum = globalMsg.B.no(i).cpoOrdNum_B1.getValue();
        }
        return num;
    }

    /**
     * Get Not Allocation Warehouse List
     * @param glblCmpyCd Global Company Code
     * @return int
     */
    public static List<String> getNotAllocWhList(String glblCmpyCd) {

        List<String> result = new ArrayList<String>();
        DS_COND_CONSTTMsg dsCondConst = new DS_COND_CONSTTMsg();
        dsCondConst.setSQLID("002");
        dsCondConst.setConditionValue("glblCmpyCd01", glblCmpyCd);
        dsCondConst.setConditionValue("dsCondConstGrpId01", NLBL3080_NOT_ALLC_WH);
        DS_COND_CONSTTMsgArray dsCondConstArr = (DS_COND_CONSTTMsgArray) EZDTBLAccessor.findByCondition(dsCondConst);

        for (int i = 0; i < dsCondConstArr.getValidCount(); i++) {

            result.add(dsCondConstArr.no(i).dsCondConstCd.getValue());
        }

        return result;
    }

    /**
     * getMdseName
     * @param cMsg NLBL3080CMsg
     * @return boolean
     */
    public static boolean getMdseName(NLBL3080CMsg cMsg) {

        S21SsmEZDResult ssmResult = NLBL3080Query.getInstance().getMdseDescShortTxt(cMsg.glblCmpyCd.getValue(), cMsg.mdseCd.getValue());

        if (ssmResult.getQueryResultCount() == 0) {

            cMsg.mdseCd.setErrorInfo(1, "NLZM2278E", new String[] {"Item Number" });
            cMsg.mdseDescShortTxt.clear();
            return false;
        }

        ZYPEZDItemValueSetter.setValue(cMsg.mdseDescShortTxt, (String) ssmResult.getResultObject());
        return true;
    }

    /**
     * chkOrdTakeMdseCd
     * @param glblCmpyCd String
     * @param ordTakeMdseCd String
     * @return boolean
     */
    private static boolean chkOrdTakeMdseCd(String glblCmpyCd, String ordTakeMdseCd) {

        ORD_TAKE_MDSETMsg ordTakeMdseTMsg = new ORD_TAKE_MDSETMsg();
        ZYPEZDItemValueSetter.setValue(ordTakeMdseTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(ordTakeMdseTMsg.ordTakeMdseCd, ordTakeMdseCd);

        ordTakeMdseTMsg = (ORD_TAKE_MDSETMsg) EZDTBLAccessor.findByKey(ordTakeMdseTMsg);

        if (ordTakeMdseTMsg != null) {

            return true;
        }

        return false;
    }

    /**
     * writeCsvFileOrder
     * @param cMsg NLBL3080CMsg
     * @param rs ResultSet
     * @throws SQLException SQLException
     */
    public static void writeCsvFileOrderLine(NLBL3080CMsg cMsg, ResultSet rs) throws SQLException {

        NLBL3080F00FMsg fMsg = new NLBL3080F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        // set column sort
        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(cMsg));

        // set exclusion column
        fMsg.addExclusionItem("xxSmryLineFlg");
        fMsg.addExclusionItem("xxChkBox_1");
        fMsg.addExclusionItem("xxChkBox_2");

        // write header
        csvOutFile.writeHeader(NLBL3080Constant.CSV_HDR_ORD_LINE, fMsg, ZYPGUITableColumn.getColOrder(cMsg));

        if (!rs.next()) {
            cMsg.setMessageInfo("NZZM0000E", null);
            csvOutFile.close();
            return;
        }

        // write contents
        do {

            if (rs.getRow() >= NLBL3080Constant.LIMIT_DL_ROWNUM) {

                cMsg.setMessageInfo("NZZM0001W", null);
                break;
            }

            // resultSet -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.cpoOrdNum, rs.getString("CPO_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.dplyLineNum, rs.getString("DPLY_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.mdseCd, rs.getString("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.mdseDescShortTxt, rs.getString("MDSE_DESC_SHORT_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.coaMdseTpCd, rs.getString("COA_MDSE_TP_CD"));
            // QC#23841
            ZYPEZDItemValueSetter.setValue(fMsg.xxSupdFlg, getSupdFlg(cMsg.glblCmpyCd.getValue(), rs.getString("MDSE_CD"), (String) rs.getString("ORD_TAKE_MDSE_CD"), rs.getBigDecimal("SUPD_CNT")));
            ZYPEZDItemValueSetter.setValue(fMsg.setMdseCd, rs.getString("SET_MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.backOrdImpctTpDescTxt, rs.getString("BACK_ORD_IMPCT_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.stkStsCd, rs.getString("STK_STS_CD"));

            if (SHPG_STS.VALIDATED.equals(rs.getString("SHPG_STS_CD"))) {

                ZYPEZDItemValueSetter.setValue(fMsg.ordQty_BO, rs.getBigDecimal("ORD_QTY"));

            } else {

                ZYPEZDItemValueSetter.setValue(fMsg.ordQty_BO, BigDecimal.ZERO);
            }

            if (SHPG_STS.HARD_ALLOCATED.equals(rs.getString("SHPG_STS_CD"))) {

                ZYPEZDItemValueSetter.setValue(fMsg.ordQty_AL, rs.getBigDecimal("ORD_QTY"));

            } else {

                ZYPEZDItemValueSetter.setValue(fMsg.ordQty_AL, BigDecimal.ZERO);
            }

            if (ZYPCommonFunc.hasValue(rs.getBigDecimal("INVTY_AVAL_QTY"))) {

                ZYPEZDItemValueSetter.setValue(fMsg.invtyAvalQty, (BigDecimal) rs.getBigDecimal("INVTY_AVAL_QTY"));

            } else {

                ZYPEZDItemValueSetter.setValue(fMsg.invtyAvalQty, BigDecimal.ZERO);
            }

            ZYPEZDItemValueSetter.setValue(fMsg.serNum, rs.getString("SER_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.t_MdlNm, rs.getString("T_MDL_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.svcConfigMstrPk, rs.getBigDecimal("SVC_CONFIG_MSTR_PK"));
            ZYPEZDItemValueSetter.setValue(fMsg.pickSvcConfigMstrPk, rs.getBigDecimal("PICK_SVC_CONFIG_MSTR_PK"));
            ZYPEZDItemValueSetter.setValue(fMsg.rddDt, rs.getString("RDD_DT"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlWhNm, rs.getString("RTL_WH_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlSwhCd, rs.getString("RTL_SWH_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.dsOrdLineCatgDescTxt, rs.getString("DS_ORD_LINE_CATG_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.ordLineStsNm, rs.getString("ORD_LINE_STS_NM"));

            csvOutFile.write();

        } while (rs.next());

        csvOutFile.close();
    }

    /**
     * isOrdTakeMdse String
     * @param mdseCd
     * @return true:Order Take Mdse / false: Mdse
     */
    public static boolean isOrdTakeMdse(String mdseCd) {

        ORD_TAKE_MDSETMsg ordTakeMdse = new ORD_TAKE_MDSETMsg();

        if (BigDecimal.valueOf(mdseCd.length()).compareTo(BigDecimal.valueOf(ordTakeMdse.getSchema().getAttr("ordTakeMdseCd").getDigit())) < 1) {

            return true;

        } else {

            return false;
        }
    }

    /**
     * getRtlWHInfo
     * @param cMsg NLBL3080CMsg
     * @return boolean
     */
    public static boolean getRtlWHInfo(NLBL3080CMsg cMsg) {

        RTL_WHTMsg rtlWh = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(rtlWh.glblCmpyCd, cMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(rtlWh.rtlWhCd, cMsg.rtlWhCd);
        rtlWh = (RTL_WHTMsg) EZDFastTBLAccessor.findByKey(rtlWh);

        if (rtlWh == null) {

            cMsg.rtlWhCd.setErrorInfo(1, "NLZM2278E", new String[] {"Warehouse" });
            cMsg.rtlWhNm.clear();
            return false;
        }

        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm, rtlWh.rtlWhNm);
        return true;
    }

    /**
     * getRtlWHInfo
     * @param cMsg NLBL3080CMsg
     * @return boolean
     */
    public static boolean getShipToCustNm(NLBL3080CMsg cMsg) {

        S21SsmEZDResult ssmResult = NLBL3080Query.getInstance().getShipToCustNm(cMsg);

        if (ssmResult.getQueryResultCount() == 0) {

            cMsg.shipToCustCd.setErrorInfo(1, "NLZM2278E", new String[] {"Ship To Customer" });
            cMsg.dsAcctNm.clear();
            return false;
        }

        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm, (String) ssmResult.getResultObject());
        return true;
    }

    /**
     * getSlsRepInfo
     * @param cMsg NLBL3080CMsg
     * @return boolean
     */
    public static boolean getSlsRepInfo(NLBL3080CMsg cMsg) {

        TOCTMsg toc = new TOCTMsg();
        ZYPEZDItemValueSetter.setValue(toc.glblCmpyCd, cMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(toc.tocCd, cMsg.tocCd);
        toc = (TOCTMsg) EZDFastTBLAccessor.findByKey(toc);

        if (toc == null) {

            cMsg.tocCd.setErrorInfo(1, "NLZM2278E", new String[] {"Sales Rep" });
            cMsg.tocNm.clear();
            return false;
        }

        ZYPEZDItemValueSetter.setValue(cMsg.tocNm, toc.tocNm);
        return true;
    }
}
