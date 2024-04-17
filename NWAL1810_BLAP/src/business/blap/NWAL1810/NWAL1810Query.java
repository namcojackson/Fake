/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1810;

import static business.blap.NWAL1810.constant.NWAL1810Constant.*;
import static business.blap.NWAL1810.constant.NWAL1810Constant.LIST_NAME_CPO;
import static business.blap.NWAL1810.constant.NWAL1810Constant.LIST_NAME_CPO_DTL;
import static business.blap.NWAL1810.constant.NWAL1810Constant.LIST_NAME_CPO_RTRN_CALC_BASE;
import static business.blap.NWAL1810.constant.NWAL1810Constant.LIST_NAME_CPO_SLS;
import static business.blap.NWAL1810.constant.NWAL1810Constant.LIST_NAME_DS_CONTR;
import static business.blap.NWAL1810.constant.NWAL1810Constant.LIST_NAME_SVC_MEMO;
import static business.blap.NWAL1810.constant.NWAL1810Constant.LIST_NAME_DS_CONTR_DTL;
import static business.blap.NWAL1810.constant.NWAL1810Constant.LIST_NAME_DS_CONTR_ADDL_CHRG;
import static business.blap.NWAL1810.constant.NWAL1810Constant.LIST_NAME_DS_CPO_CONFIG;
import static business.blap.NWAL1810.constant.NWAL1810Constant.LIST_NAME_DS_CPO_RTRN_DTL;
import static business.blap.NWAL1810.constant.NWAL1810Constant.LIST_NAME_DS_CPO_SLS_CR;
import static business.blap.NWAL1810.constant.NWAL1810Constant.LIST_NAME_ORD_PRC_CALC_BASE;
import static business.blap.NWAL1810.constant.NWAL1810Constant.LIST_NAME_SCHD_AGMT;
import static business.blap.NWAL1810.constant.NWAL1810Constant.LIST_NAME_SCHD_AGMT_LINE;
import static business.blap.NWAL1810.constant.NWAL1810Constant.LIST_NAME_SCHD_AGMT_PLN;
import static business.blap.NWAL1810.constant.NWAL1810Constant.LIST_NAME_SCHD_AGMT_PRC_CALC;
import static business.blap.NWAL1810.constant.NWAL1810Constant.LIST_NAME_SCHD_AGMT_SLS_CR;
import static business.blap.NWAL1810.constant.NWAL1810Constant.LIST_NAME_SPLY_QUOTE;
import static business.blap.NWAL1810.constant.NWAL1810Constant.LIST_NAME_SPLY_QUOTE_CALC_BASE;
import static business.blap.NWAL1810.constant.NWAL1810Constant.LIST_NAME_SPLY_QUOTE_DTL;
import static business.blap.NWAL1810.constant.NWAL1810Constant.LIST_NAME_SPLY_QUOTE_SLS_CR;
import static business.blap.NWAL1810.constant.NWAL1810Constant.LVL_CD_ALL;
import static business.blap.NWAL1810.constant.NWAL1810Constant.LVL_CD_CONF;
import static business.blap.NWAL1810.constant.NWAL1810Constant.LVL_CD_LINE;
import static business.blap.NWAL1810.constant.NWAL1810Constant.PERCENT;
import static business.blap.NWAL1810.constant.NWAL1810Constant.PERIOD;
import static business.blap.NWAL1810.constant.NWAL1810Constant.PROC_ID_OM;
import static business.blap.NWAL1810.constant.NWAL1810Constant.SQL_LINE_STR_PTN;
import static business.blap.NWAL1810.constant.NWAL1810Constant.SQL_OUTPUT_FLG_ON;
import static business.blap.NWAL1810.constant.NWAL1810Constant.SQL_TXT_PAR_NUM_00;
import static business.blap.NWAL1810.constant.NWAL1810Constant.SQL_TXT_PAR_NUM_01;
import static business.blap.NWAL1810.constant.NWAL1810Constant.SQL_TXT_PAR_NUM_02;
import static business.blap.NWAL1810.constant.NWAL1810Constant.SQL_TXT_PAR_NUM_03;
import static business.blap.NWAL1810.constant.NWAL1810Constant.SQL_UNION_OUTPUT_FLG_OFF;
import static business.blap.NWAL1810.constant.NWAL1810Constant.SQL_UNION_OUTPUT_FLG_ON;
import static business.blap.NWAL1810.constant.NWAL1810Constant.DATE_TIME_FORMAT_FROM_DT;
import static business.blap.NWAL1810.constant.NWAL1810Constant.DATE_TIME_FORMAT_THRU_DT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.blap.NWAL1810.common.NWAL1810CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_PRFT_TRX_CATG;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NWAL1810Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/25   Fujitsu         S.Ohki          Create          N/A
 * 2016/04/01   Fujitsu         M.Yamada        Update          QC#5283
 * 2016/04/08   Fujitsu         M.Yamada        Update          QC#6731
 * 2016/04/13   Fujitsu         M.Yamada        Update          QC#6965
 * 2016/04/20   Fujitsu         M.Yamada        Update          QC#7245
 * 2022/10/20   Hitachi         H.Watanabe      Update          QC#60258
 *</pre>
 */
public final class NWAL1810Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NWAL1810Query MY_INSTANCE = new NWAL1810Query();

    /**
     * Private constructor
     */
    private NWAL1810Query() {
        super();
    }

    /**
     * Get the NWAL1810Query instance.
     * @return NWAL1810Query instance
     */
    public static NWAL1810Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get View Change Log Attribute List
     * @param bizMsg NWAL1810CMsg
     * @param recDbNm String
     * @param lvlNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getViewChngLogAttrbList(NWAL1810CMsg bizMsg, String recDbNm, String lvlNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("srcNm", bizMsg.viewChngLogSrcNm);
        ssmParam.put("lvlNum", lvlNum);
        ssmParam.put("recDbNm", recDbNm);

        return getSsmEZDClient().queryObjectList("getViewChngLogAttrbList", ssmParam);
    }

    /**
     * Get Summary List
     * @param bizMsg NWAL1810CMsg
     * @param glblMsg NWAL1810SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSummaryList(NWAL1810CMsg bizMsg, NWAL1810SMsg glblMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("rowNum", glblMsg.A.length() + 1);
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("prntDocId", bizMsg.xxViewChngLogSrchNum);
        ssmParam.put("procId", PROC_ID_OM);
        ssmParam.put("userOpsFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("pattern", SQL_LINE_STR_PTN);
        ssmParam.put("fltPattern", SQL_LINE_STR_FLT_PTN);
        ssmParam.put("docTpCdOM", DOC_TP_CD_OM);
        ssmParam.put("docTpCdRT", DOC_TP_CD_RT);
        ssmParam.put("ordPrftTrxCatgInbound", ORD_PRFT_TRX_CATG.INBOUND);
        ssmParam.put("ordPrftTrxCatgService", ORD_PRFT_TRX_CATG.SERVICE);
        ssmParam.put("ordPrftTrxCatgOutbound", ORD_PRFT_TRX_CATG.OUTBOUND);
        ssmParam.put("supplyFlg", ZYPConstant.FLG_OFF_N);

        if (bizMsg.xxTrnsnOrigScrnId.getValue().startsWith(SCRN_ID_ORDER_ENTRY)) {
            ssmParam.put("targetEntity", TRGT_ENTITY_ORDER);
            if (bizMsg.L.getValidCount() > 0) {
                ssmParam.put("docTpCd", DOC_TP_CD_OM);
            }
            if (bizMsg.R.getValidCount() > 0) {
                ssmParam.put("docTpCd", DOC_TP_CD_RT);
            }

        } else if (bizMsg.xxTrnsnOrigScrnId.getValue().startsWith(SCRN_ID_QUOTE_ENTRY)) {
            ssmParam.put("targetEntity", TRGT_ENTITY_QUOTE);
            ssmParam.put("docTpCd", DOC_TP_CD_QUOTE);

        } else if (bizMsg.xxTrnsnOrigScrnId.getValue().startsWith(SCRN_ID_SCHD_AGMT)) {
            ssmParam.put("targetEntity", TRGT_ENTITY_SCHEDULE);
            ssmParam.put("docTpCd", DOC_TP_CD_SCHEDULE_AGREEMENT);
            ssmParam.put("supplyFlg", ZYPConstant.FLG_ON_Y);

        } else {
            ssmParam.put("targetEntity", SPACE);
            ssmParam.put("docTpCd", DOC_TP_CD_OM);
        }

        // 2022/10/20 QC#60258 Add Start
        setSearchLogParam(bizMsg, ssmParam);
        // 2022/10/20 QC#60258 Add End

        // Config
        List<String> docIdConfigList = null;
        List<String> docIdConfigLineList = null;
        if (LVL_CD_CONF.equals(bizMsg.noteLvlCd.getValue())) {
            docIdConfigList = new ArrayList<String>();
            docIdConfigLineList = new ArrayList<String>();
            for (int i = 0; i < bizMsg.L.getValidCount(); i++) {
                //                StringBuilder paramLike = new StringBuilder();
                //                paramLike.append(bizMsg.L.no(i).dsOrdPosnNum_L0.getValue()).append(PERCENT); // QC#5283
                docIdConfigList.add(bizMsg.L.no(i).dsOrdPosnNum_L0.getValue());
                setDocIdConfigLineListFromOrder(docIdConfigLineList, bizMsg.L.no(i).dsOrdPosnNum_L0.getValue(), bizMsg.xxViewChngLogSrchNum.getValue());
            }
            for (int i = 0; i < bizMsg.R.getValidCount(); i++) {
                //                StringBuilder paramLike = new StringBuilder();
                //                paramLike.append(bizMsg.R.no(i).dsOrdPosnNum_R0.getValue()).append(PERCENT); // QC#5283
                docIdConfigList.add(bizMsg.R.no(i).dsOrdPosnNum_R0.getValue());
                setDocIdConfigLineListFromReturn(docIdConfigLineList, bizMsg.R.no(i).dsOrdPosnNum_R0.getValue(), bizMsg.xxViewChngLogSrchNum.getValue());
            }
            ssmParam.put("docIdConfigList", docIdConfigList);
            ssmParam.put("docIdConfigLineList", docIdConfigLineList);
        }

        // Line
        List<String> docIdLineList = null;
        if (LVL_CD_LINE.equals(bizMsg.noteLvlCd.getValue())) {
            docIdLineList = new ArrayList<String>();
            for (int i = 0; i < bizMsg.L.getValidCount(); i++) {
                docIdLineList.add(S21StringUtil.concatStrings(//
                        bizMsg.L.no(i).cpoDtlLineNum_L0.getValue() //
                        , PERIOD //
                        , PERCENT)); // QC#5283
            }
            for (int i = 0; i < bizMsg.R.getValidCount(); i++) {
                docIdLineList.add(S21StringUtil.concatStrings(//
                        bizMsg.R.no(i).dsCpoRtrnLineNum_R0.getValue() //
                        , PERIOD //
                        , PERCENT)); // QC#5283
            }
            ssmParam.put("docIdLineList", docIdLineList);
        }

        S21SsmEZDResult rslt = getSsmEZDClient().queryEZDMsgArray("getSummaryList", ssmParam, glblMsg.A);
        NWAL1810CommonLogic.formatTimestampForAGlblMsg(glblMsg.A);
        NWAL1810CommonLogic.convLineStrForAGlblMsg(//
                glblMsg.A, bizMsg.xxViewChngLogSrchNum.getValue(), bizMsg.xxTrnsnOrigScrnId.getValue());
        return rslt;
    }

    private void setDocIdConfigLineListFromReturn(List<String> docIdConfigLineList, String dsOrdPosnNum, String cpoOrdNum) {
        S21SsmEZDResult rslt //
        = getDocIdConfigLineListFromReturn(dsOrdPosnNum, cpoOrdNum);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rslt.getResultObject();
        for (Map<String, Object> rsMap : rsltList) {
            docIdConfigLineList.add(S21StringUtil.concatStrings((String) rsMap.get("LINE"), PERCENT));
        }
        return;
    }

    private S21SsmEZDResult getDocIdConfigLineListFromReturn(String dsOrdPosnNum, String cpoOrdNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("dsOrdPosnNum", dsOrdPosnNum);
        ssmParam.put("cpoOrdNum", cpoOrdNum);

        return getSsmEZDClient().queryObjectList("getDocIdConfigLineListFromReturn", ssmParam);
    }

    private void setDocIdConfigLineListFromOrder(List<String> docIdConfigLineList, String dsOrdPosnNum, String cpoOrdNum) {
        S21SsmEZDResult rslt //
        = getDocIdConfigLineListFromOrder(dsOrdPosnNum, cpoOrdNum);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rslt.getResultObject();
        for (Map<String, Object> rsMap : rsltList) {
            docIdConfigLineList.add(S21StringUtil.concatStrings((String) rsMap.get("LINE"), PERCENT));
        }
        return;
    }

    /**
     * @param dsOrdPosnNum
     * @param cpoOrdNum
     */
    private S21SsmEZDResult getDocIdConfigLineListFromOrder(String dsOrdPosnNum, String cpoOrdNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("dsOrdPosnNum", dsOrdPosnNum);
        ssmParam.put("cpoOrdNum", cpoOrdNum);

        return getSsmEZDClient().queryObjectList("getDocIdConfigLineListFromOrder", ssmParam);
    }

    /**
     * Get Detail Order List
     * @param bizMsg NWAL1810CMsg
     * @param glblMsg NWAL1810SMsg
     * @param configList List<String>
     * @param configOrdList List<String>
     * @param configRtnList List<String>
     * @param docIdConfigList List<String>
     * @param docIdLineList List<String>
     * @param sqlPrmMap Map<String, List<String>>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDetailOrderList(//
            NWAL1810CMsg bizMsg //
            , NWAL1810SMsg glblMsg //
            , List<String> configList //
            , List<String> configOrdList //
            , List<String> configRtnList //
            , List<String> docIdConfigList //
            , List<String> docIdLineList //
            , Map<String, List<String>> sqlPrmMap) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("rowNum", glblMsg.B.length() + 1);
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("prntDocId", bizMsg.xxViewChngLogSrchNum);
        ssmParam.put("procId", PROC_ID_OM);
        ssmParam.put("userOpsFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("pattern", SQL_LINE_STR_PTN);
        ssmParam.put("fltPattern", SQL_LINE_STR_FLT_PTN);
        ssmParam.put("docTpCdOM", DOC_TP_CD_OM);
        ssmParam.put("docTpCdRT", DOC_TP_CD_RT);
        ssmParam.put("ordPrftTrxCatgInbound", ORD_PRFT_TRX_CATG.INBOUND);
        ssmParam.put("ordPrftTrxCatgService", ORD_PRFT_TRX_CATG.SERVICE);
        ssmParam.put("ordPrftTrxCatgOutbound", ORD_PRFT_TRX_CATG.OUTBOUND);
        ssmParam.put("targetEntity", TRGT_ENTITY_ORDER);

        if (bizMsg.L.getValidCount() > 0) {
            ssmParam.put("docTpCd", DOC_TP_CD_OM);
        }
        if (bizMsg.R.getValidCount() > 0) {
            ssmParam.put("docTpCd", DOC_TP_CD_RT);
        }

        if (LVL_CD_ALL.equals(bizMsg.noteLvlCd.getValue())) {

            setSqlOrderHeader(bizMsg, ssmParam, sqlPrmMap);
            setSqlOrderConf(bizMsg, ssmParam, sqlPrmMap);
            setSqlOrderLine(bizMsg, ssmParam, sqlPrmMap);

            ssmParam.put("useAllFlg", ZYPConstant.FLG_ON_Y);

        } else if (LVL_CD_CONF.equals(bizMsg.noteLvlCd.getValue())) {

            setSqlOrderConf(bizMsg, ssmParam, sqlPrmMap);
            setSqlOrderLine(bizMsg, ssmParam, sqlPrmMap);
            if (configList == null || configList.size() == 0) {
                ssmParam.put("useAllFlg", ZYPConstant.FLG_ON_Y);
            } else {
                ssmParam.put("useAllFlg", ZYPConstant.FLG_OFF_N);
            }
            ssmParam.put("configList", configList);
            ssmParam.put("configOrdList", configOrdList);
            ssmParam.put("configRtnList", configRtnList);
            ssmParam.put("docIdConfigList", docIdConfigList);
            ssmParam.put("lvlConfFlg", ZYPConstant.FLG_ON_Y);

            List<String> docIdConfigLineList = null;
            docIdConfigLineList = new ArrayList<String>();
            for (int i = 0; i < bizMsg.L.getValidCount(); i++) {
                setDocIdConfigLineListFromOrder(//
                        docIdConfigLineList, bizMsg.L.no(i).dsOrdPosnNum_L0.getValue(), bizMsg.xxViewChngLogSrchNum.getValue());
            }
            for (int i = 0; i < bizMsg.R.getValidCount(); i++) {
                setDocIdConfigLineListFromReturn(//
                        docIdConfigLineList, bizMsg.R.no(i).dsOrdPosnNum_R0.getValue(), bizMsg.xxViewChngLogSrchNum.getValue());
            }
            ssmParam.put("docIdConfigLineList", docIdConfigLineList);

        } else {

            setSqlOrderLine(bizMsg, ssmParam, sqlPrmMap);
            ssmParam.put("docIdLineList", docIdLineList);
            ssmParam.put("lvlLineFlg", ZYPConstant.FLG_ON_Y);
            ssmParam.put("msgC", bizMsg.C);
            ssmParam.put("msgD", bizMsg.D);

            ssmParam.put("useAllFlg", ZYPConstant.FLG_ON_Y);
        }

        // Set Service Price
        setSqlServicePrice(bizMsg, ssmParam, sqlPrmMap);

        setUnionFlgOrder(bizMsg, ssmParam);

        // 2022/10/20 QC#60258 Add Start
        setSearchLogParam(bizMsg, ssmParam);
        // 2022/10/20 QC#60258 Add End

        S21SsmEZDResult rslt = getSsmEZDClient().queryEZDMsgArray("getDetailOrderList", ssmParam, glblMsg.B);
        NWAL1810CommonLogic.formatTimestampForBGlblMsg(glblMsg.B);
        NWAL1810CommonLogic.convLineStrForBGlblMsg(//
                glblMsg.B, bizMsg.xxViewChngLogSrchNum.getValue(), bizMsg.xxTrnsnOrigScrnId.getValue());
        return rslt;
    }

    /**
     * Set SQL Order All
     * @param bizMsg NWAL1810CMsg
     * @param ssmParam Map
     */
    private void setSqlOrderHeader(NWAL1810CMsg bizMsg, Map<String, Object> ssmParam, Map<String, List<String>> splPrmMap) {

        List<String> cpoAttrList = splPrmMap.get(LIST_NAME_CPO);
        if (cpoAttrList != null && !cpoAttrList.isEmpty()) {
            ssmParam.put("cpoRecFlg", SQL_OUTPUT_FLG_ON);
            ssmParam.put("cpoAttr", cpoAttrList.get(SQL_TXT_PAR_NUM_00));
            ssmParam.put("cpoCaseBf", cpoAttrList.get(SQL_TXT_PAR_NUM_01));
            ssmParam.put("cpoCaseAf", cpoAttrList.get(SQL_TXT_PAR_NUM_02));
            ssmParam.put("cpoComp", cpoAttrList.get(SQL_TXT_PAR_NUM_03));
        }

        List<String> cpoSlsAttrList = splPrmMap.get(LIST_NAME_CPO_SLS);
        if (cpoSlsAttrList != null && !cpoSlsAttrList.isEmpty()) {
            ssmParam.put("dsCpoSlsCpoRecFlg", SQL_OUTPUT_FLG_ON);
            ssmParam.put("dsCpoSlsCpoAttr", cpoSlsAttrList.get(SQL_TXT_PAR_NUM_00));
            ssmParam.put("dsCpoSlsCpoCaseBf", cpoSlsAttrList.get(SQL_TXT_PAR_NUM_01));
            ssmParam.put("dsCpoSlsCpoCaseAf", cpoSlsAttrList.get(SQL_TXT_PAR_NUM_02));
            ssmParam.put("dsCpoSlsCpoComp", cpoSlsAttrList.get(SQL_TXT_PAR_NUM_03));
        }
    }

    /**
     * Set SQL Order Config
     * @param bizMsg NWAL1810CMsg
     * @param ssmParam Map
     */
    private void setSqlOrderConf(NWAL1810CMsg bizMsg, Map<String, Object> ssmParam, Map<String, List<String>> splPrmMap) {

        List<String> dsCpoConfAttrList = splPrmMap.get(LIST_NAME_DS_CPO_CONFIG);
        if (dsCpoConfAttrList != null && !dsCpoConfAttrList.isEmpty()) {
            ssmParam.put("dsCpoConfRecFlg", SQL_OUTPUT_FLG_ON);
            ssmParam.put("dsCpoConfAttr", dsCpoConfAttrList.get(SQL_TXT_PAR_NUM_00));
            ssmParam.put("dsCpoConfCaseBf", dsCpoConfAttrList.get(SQL_TXT_PAR_NUM_01));
            ssmParam.put("dsCpoConfCaseAf", dsCpoConfAttrList.get(SQL_TXT_PAR_NUM_02));
            ssmParam.put("dsCpoConfComp", dsCpoConfAttrList.get(SQL_TXT_PAR_NUM_03));
        }

        List<String> dsCpoSlsCrAttrList = splPrmMap.get(LIST_NAME_DS_CPO_SLS_CR);
        if (dsCpoSlsCrAttrList != null && !dsCpoSlsCrAttrList.isEmpty()) {
            ssmParam.put("dsCpoSlsConfRecFlg", SQL_OUTPUT_FLG_ON);
            ssmParam.put("dsCpoSlsConfAttr", dsCpoSlsCrAttrList.get(SQL_TXT_PAR_NUM_00));
            ssmParam.put("dsCpoSlsConfCaseBf", dsCpoSlsCrAttrList.get(SQL_TXT_PAR_NUM_01));
            ssmParam.put("dsCpoSlsConfCaseAf", dsCpoSlsCrAttrList.get(SQL_TXT_PAR_NUM_02));
            ssmParam.put("dsCpoSlsConfComp", dsCpoSlsCrAttrList.get(SQL_TXT_PAR_NUM_03));
        }
    }

    /**
     * Set SQL Order Line
     * @param bizMsg NWAL1810CMsg
     * @param ssmParam Map
     */
    private void setSqlOrderLine(NWAL1810CMsg bizMsg, Map<String, Object> ssmParam, Map<String, List<String>> sqlPrmMap) {

        boolean orderLineOnFlg = true;
        boolean rtnOrderLineOnFlg = true;

        if (LVL_CD_LINE.equals(bizMsg.noteLvlCd.getValue()) || LVL_CD_CONF.equals(bizMsg.noteLvlCd.getValue())) {
            if (bizMsg.L.getValidCount() == 0) {
                orderLineOnFlg = false;
            }

            if (bizMsg.R.getValidCount() == 0) {
                rtnOrderLineOnFlg = false;
            }
        }

        List<String> cpoDtlAttrList = sqlPrmMap.get(LIST_NAME_CPO_DTL);
        if (orderLineOnFlg && cpoDtlAttrList != null && !cpoDtlAttrList.isEmpty()) {
            ssmParam.put("cpoDtlRecFlg", SQL_OUTPUT_FLG_ON);
            ssmParam.put("cpoDtlAttr", cpoDtlAttrList.get(SQL_TXT_PAR_NUM_00));
            ssmParam.put("cpoDtlCaseBf", cpoDtlAttrList.get(SQL_TXT_PAR_NUM_01));
            ssmParam.put("cpoDtlCaseAf", cpoDtlAttrList.get(SQL_TXT_PAR_NUM_02));
            ssmParam.put("cpoDtlComp", cpoDtlAttrList.get(SQL_TXT_PAR_NUM_03));
        }

        List<String> dsCpoRtrnDtlAttrList = sqlPrmMap.get(LIST_NAME_DS_CPO_RTRN_DTL);
        if (rtnOrderLineOnFlg && dsCpoRtrnDtlAttrList != null && !dsCpoRtrnDtlAttrList.isEmpty()) {
            ssmParam.put("dsCpoRtrnDtlRecFlg", SQL_OUTPUT_FLG_ON);
            ssmParam.put("dsCpoRtrnDtlAttr", dsCpoRtrnDtlAttrList.get(SQL_TXT_PAR_NUM_00));
            ssmParam.put("dsCpoRtrnDtlCaseBf", dsCpoRtrnDtlAttrList.get(SQL_TXT_PAR_NUM_01));
            ssmParam.put("dsCpoRtrnDtlCaseAf", dsCpoRtrnDtlAttrList.get(SQL_TXT_PAR_NUM_02));
            ssmParam.put("dsCpoRtrnDtlComp", dsCpoRtrnDtlAttrList.get(SQL_TXT_PAR_NUM_03));
        }

        List<String> ordPrcCalcBaseAttrList = sqlPrmMap.get(LIST_NAME_ORD_PRC_CALC_BASE);
        if (orderLineOnFlg && ordPrcCalcBaseAttrList != null && !ordPrcCalcBaseAttrList.isEmpty()) {
            ssmParam.put("ordPrcCalcBaseRecFlg", SQL_OUTPUT_FLG_ON);
            ssmParam.put("ordPrcCalcBaseAttr", ordPrcCalcBaseAttrList.get(SQL_TXT_PAR_NUM_00));
            ssmParam.put("ordPrcCalcBaseCaseBf", ordPrcCalcBaseAttrList.get(SQL_TXT_PAR_NUM_01));
            ssmParam.put("ordPrcCalcBaseCaseAf", ordPrcCalcBaseAttrList.get(SQL_TXT_PAR_NUM_02));
            ssmParam.put("ordPrcCalcBaseComp", ordPrcCalcBaseAttrList.get(SQL_TXT_PAR_NUM_03));
        }

        List<String> cpoRtrnCalcBaseAttrList = sqlPrmMap.get(LIST_NAME_CPO_RTRN_CALC_BASE);
        if (rtnOrderLineOnFlg && cpoRtrnCalcBaseAttrList != null && !cpoRtrnCalcBaseAttrList.isEmpty()) {
            ssmParam.put("cpoRtrnCalcBaseRecFlg", SQL_OUTPUT_FLG_ON);
            ssmParam.put("cpoRtrnCalcBaseAttr", cpoRtrnCalcBaseAttrList.get(SQL_TXT_PAR_NUM_00));
            ssmParam.put("cpoRtrnCalcBaseCaseBf", cpoRtrnCalcBaseAttrList.get(SQL_TXT_PAR_NUM_01));
            ssmParam.put("cpoRtrnCalcBaseCaseAf", cpoRtrnCalcBaseAttrList.get(SQL_TXT_PAR_NUM_02));
            ssmParam.put("cpoRtrnCalcBaseComp", cpoRtrnCalcBaseAttrList.get(SQL_TXT_PAR_NUM_03));
        }
    }

    /**
     * Set SQL Order Line
     * @param bizMsg NWAL1810CMsg
     * @param ssmParam Map
     */
    private void setSqlServicePrice(NWAL1810CMsg bizMsg, Map<String, Object> ssmParam, Map<String, List<String>> splPrmMap) {

        List<String> dsContrAttrList = splPrmMap.get(LIST_NAME_DS_CONTR);
        if (dsContrAttrList != null && !dsContrAttrList.isEmpty()) {
            ssmParam.put("dsContrRecFlg", SQL_OUTPUT_FLG_ON);
            ssmParam.put("dsContrAttr", dsContrAttrList.get(SQL_TXT_PAR_NUM_00));
            ssmParam.put("dsContrCaseBf", dsContrAttrList.get(SQL_TXT_PAR_NUM_01));
            ssmParam.put("dsContrCaseAf", dsContrAttrList.get(SQL_TXT_PAR_NUM_02));
            ssmParam.put("dsContrComp", dsContrAttrList.get(SQL_TXT_PAR_NUM_03));
        }

        List<String> svcMemoAttrList = splPrmMap.get(LIST_NAME_SVC_MEMO);
        if (svcMemoAttrList != null && !svcMemoAttrList.isEmpty()) {
            ssmParam.put("svcMemoRecFlg", SQL_OUTPUT_FLG_ON);
            ssmParam.put("svcMemoAttr", svcMemoAttrList.get(SQL_TXT_PAR_NUM_00));
            ssmParam.put("svcMemoCaseBf", svcMemoAttrList.get(SQL_TXT_PAR_NUM_01));
            ssmParam.put("svcMemoCaseAf", svcMemoAttrList.get(SQL_TXT_PAR_NUM_02));
            ssmParam.put("svcMemoComp", svcMemoAttrList.get(SQL_TXT_PAR_NUM_03));
        }

        List<String> dsContrDtlAttrList = splPrmMap.get(LIST_NAME_DS_CONTR_DTL);
        if (dsContrAttrList != null && !dsContrAttrList.isEmpty()) {
            ssmParam.put("dsContrDtlRecFlg", SQL_OUTPUT_FLG_ON);
            ssmParam.put("dsContrDtlAttr", dsContrDtlAttrList.get(SQL_TXT_PAR_NUM_00));
            ssmParam.put("dsContrDtlCaseBf", dsContrDtlAttrList.get(SQL_TXT_PAR_NUM_01));
            ssmParam.put("dsContrDtlCaseAf", dsContrDtlAttrList.get(SQL_TXT_PAR_NUM_02));
            ssmParam.put("dsContrDtlComp", dsContrDtlAttrList.get(SQL_TXT_PAR_NUM_03));
        }

        List<String> dsContrAddlChrgAttrList = splPrmMap.get(LIST_NAME_DS_CONTR_ADDL_CHRG);
        if (dsContrAddlChrgAttrList != null && !dsContrAddlChrgAttrList.isEmpty()) {
            ssmParam.put("dsContrAddlChrgRecFlg", SQL_OUTPUT_FLG_ON);
            ssmParam.put("dsContrAddlChrgAttr", dsContrAddlChrgAttrList.get(SQL_TXT_PAR_NUM_00));
            ssmParam.put("dsContrAddlChrgCaseBf", dsContrAddlChrgAttrList.get(SQL_TXT_PAR_NUM_01));
            ssmParam.put("dsContrAddlChrgCaseAf", dsContrAddlChrgAttrList.get(SQL_TXT_PAR_NUM_02));
            ssmParam.put("dsContrAddlChrgComp", dsContrAddlChrgAttrList.get(SQL_TXT_PAR_NUM_03));
        }
    }

    /**
     * Set Union Flag Order
     * @param bizMsg NWAL1810CMsg
     * @param ssmParam Map
     */
    private void setUnionFlgOrder(NWAL1810CMsg bizMsg, Map<String, Object> ssmParam) {

        boolean firstUnionFlg = true;

        if (SQL_OUTPUT_FLG_ON.equals(ssmParam.get("cpoRecFlg"))) {
            firstUnionFlg = false;
        }

        if (SQL_OUTPUT_FLG_ON.equals(ssmParam.get("dsCpoSlsCpoRecFlg"))) {
            if (firstUnionFlg) {
                ssmParam.put("dsCpoSlsCpoRecUnionFlg", SQL_UNION_OUTPUT_FLG_OFF);
                firstUnionFlg = false;
            } else {
                ssmParam.put("dsCpoSlsCpoRecUnionFlg", SQL_UNION_OUTPUT_FLG_ON);
            }
        }

        if (SQL_OUTPUT_FLG_ON.equals(ssmParam.get("dsCpoConfRecFlg"))) {
            if (firstUnionFlg) {
                ssmParam.put("dsCpoConfRecUnionFlg", SQL_UNION_OUTPUT_FLG_OFF);
                firstUnionFlg = false;
            } else {
                ssmParam.put("dsCpoConfRecUnionFlg", SQL_UNION_OUTPUT_FLG_ON);
            }
        }

        if (SQL_OUTPUT_FLG_ON.equals(ssmParam.get("dsCpoSlsConfRecFlg"))) {
            if (firstUnionFlg) {
                ssmParam.put("dsCpoSlsConfRecUnionFlg", SQL_UNION_OUTPUT_FLG_OFF);
                firstUnionFlg = false;
            } else {
                ssmParam.put("dsCpoSlsConfRecUnionFlg", SQL_UNION_OUTPUT_FLG_ON);
            }
        }

        if (SQL_OUTPUT_FLG_ON.equals(ssmParam.get("cpoDtlRecFlg"))) {
            if (firstUnionFlg) {
                ssmParam.put("cpoDtlRecUnionFlg", SQL_UNION_OUTPUT_FLG_OFF);
                firstUnionFlg = false;
            } else {
                ssmParam.put("cpoDtlRecUnionFlg", SQL_UNION_OUTPUT_FLG_ON);
            }
        }

        if (SQL_OUTPUT_FLG_ON.equals(ssmParam.get("dsCpoRtrnDtlRecFlg"))) {
            if (firstUnionFlg) {
                ssmParam.put("dsCpoRtrnDtlRecUnionFlg", SQL_UNION_OUTPUT_FLG_OFF);
                firstUnionFlg = false;
            } else {
                ssmParam.put("dsCpoRtrnDtlRecUnionFlg", SQL_UNION_OUTPUT_FLG_ON);
            }
        }

        if (SQL_OUTPUT_FLG_ON.equals(ssmParam.get("ordPrcCalcBaseRecFlg"))) {
            if (firstUnionFlg) {
                ssmParam.put("ordPrcCalcBaseRecUnionFlg", SQL_UNION_OUTPUT_FLG_OFF);
                firstUnionFlg = false;
            } else {
                ssmParam.put("ordPrcCalcBaseRecUnionFlg", SQL_UNION_OUTPUT_FLG_ON);
            }
        }

        if (SQL_OUTPUT_FLG_ON.equals(ssmParam.get("cpoRtrnCalcBaseRecFlg"))) {
            if (firstUnionFlg) {
                ssmParam.put("cpoRtrnCalcBaseRecUnionFlg", SQL_UNION_OUTPUT_FLG_OFF);
                firstUnionFlg = false;
            } else {
                ssmParam.put("cpoRtrnCalcBaseRecUnionFlg", SQL_UNION_OUTPUT_FLG_ON);
            }
        }

        if (SQL_OUTPUT_FLG_ON.equals(ssmParam.get("dsContrRecFlg"))) {
            if (firstUnionFlg) {
                ssmParam.put("dsContrRecUnionFlg", SQL_UNION_OUTPUT_FLG_OFF);
                firstUnionFlg = false;
            } else {
                ssmParam.put("dsContrRecUnionFlg", SQL_UNION_OUTPUT_FLG_ON);
            }
        }

        if (SQL_OUTPUT_FLG_ON.equals(ssmParam.get("svcMemoRecFlg"))) {
            if (firstUnionFlg) {
                ssmParam.put("svcMemoRecUnionFlg", SQL_UNION_OUTPUT_FLG_OFF);
                firstUnionFlg = false;
            } else {
                ssmParam.put("svcMemoRecUnionFlg", SQL_UNION_OUTPUT_FLG_ON);
            }
        }

        if (SQL_OUTPUT_FLG_ON.equals(ssmParam.get("dsContrDtlRecFlg"))) {
            if (firstUnionFlg) {
                ssmParam.put("dsContrDtlRecUnionFlg", SQL_UNION_OUTPUT_FLG_OFF);
                firstUnionFlg = false;
            } else {
                ssmParam.put("dsContrDtlRecUnionFlg", SQL_UNION_OUTPUT_FLG_ON);
            }
        }

        if (SQL_OUTPUT_FLG_ON.equals(ssmParam.get("dsContrAddlChrgRecFlg"))) {
            if (firstUnionFlg) {
                ssmParam.put("dsContrAddlChrgRecUnionFlg", SQL_UNION_OUTPUT_FLG_OFF);
                firstUnionFlg = false;
            } else {
                ssmParam.put("dsContrAddlChrgRecUnionFlg", SQL_UNION_OUTPUT_FLG_ON);
            }
        }

        if (firstUnionFlg) {
            ssmParam.put("compDummyOutputFlg", SQL_OUTPUT_FLG_ON);
        }
    }

    /**
     * Get Detail Quote List
     * @param bizMsg NWAL1810CMsg
     * @param glblMsg NWAL1810SMsg
     * @param configList List<String>
     * @param docIdConfigList List<String>
     * @param docIdLineList List<String>
     * @param sqlPrmMap Map<String, List<String>>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDetailQuoteList(//
            NWAL1810CMsg bizMsg //
            , NWAL1810SMsg glblMsg //
            , List<String> configList //
            , List<String> docIdConfigList //
            , List<String> docIdLineList //
            , Map<String, List<String>> sqlPrmMap) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("rowNum", glblMsg.B.length() + 1);
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("prntDocId", bizMsg.xxViewChngLogSrchNum);
        ssmParam.put("procId", PROC_ID_OM);
        ssmParam.put("userOpsFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("ordPrftTrxCatgOutbound", ORD_PRFT_TRX_CATG.OUTBOUND);
        ssmParam.put("docTpQuote", DOC_TP_CD_QUOTE);

        ssmParam.put("dbNameSplyQuoteRec", DB_NAME_SPLY_QUOTE_REC);
        ssmParam.put("dbNameSplyQuoteSlsCrRec", DB_NAME_SPLY_QUOTE_SLS_CR_REC);
        ssmParam.put("dbNameSplyQuoteDtlRec", DB_NAME_SPLY_QUOTE_DTL_REC);
        ssmParam.put("dbNameSplyQuoteCalcBaseRec", DB_NAME_SPLY_QUOTE_CALC_BASE_REC);

        //        if (bizMsg.L.getValidCount() > 0) {
        //            ssmParam.put("docTpCd", DOC_TP_CD_OM);
        //        }
        //        if (bizMsg.R.getValidCount() > 0) {
        //            ssmParam.put("docTpCd", DOC_TP_CD_RT);
        //        }

        if (LVL_CD_ALL.equals(bizMsg.noteLvlCd.getValue())) {

            setSqlQuoteHeader(bizMsg, ssmParam, sqlPrmMap);
            setSqlQuoteLine(bizMsg, ssmParam, sqlPrmMap);

        } else if (LVL_CD_CONF.equals(bizMsg.noteLvlCd.getValue())) {

            setSqlQuoteLine(bizMsg, ssmParam, sqlPrmMap);
            ssmParam.put("configList", configList);
            ssmParam.put("docIdConfigList", docIdConfigList);

        } else {

            setSqlQuoteLine(bizMsg, ssmParam, sqlPrmMap);
            ssmParam.put("docIdLineList", docIdLineList);
        }

        setUnionFlgQuote(bizMsg, ssmParam);

        S21SsmEZDResult rslt = getSsmEZDClient().queryEZDMsgArray("getDetailQuoteList", ssmParam, glblMsg.B);
        NWAL1810CommonLogic.formatTimestampForBGlblMsg(glblMsg.B);
        NWAL1810CommonLogic.convLineStrForBGlblMsg(//
                glblMsg.B, bizMsg.xxViewChngLogSrchNum.getValue(), bizMsg.xxTrnsnOrigScrnId.getValue());
        return rslt;

    }

    /**
     * Set SQL Quote All
     * @param bizMsg NWAL1810CMsg
     * @param ssmParam Map
     */
    private void setSqlQuoteHeader(NWAL1810CMsg bizMsg, Map<String, Object> ssmParam, Map<String, List<String>> sqlPrmMap) {

        List<String> splyQuoteAttrList = sqlPrmMap.get(LIST_NAME_SPLY_QUOTE);
        if (splyQuoteAttrList != null && !splyQuoteAttrList.isEmpty()) {
            ssmParam.put("splyQuoteRecFlg", SQL_OUTPUT_FLG_ON);
            ssmParam.put("splyQuoteAttr", splyQuoteAttrList.get(SQL_TXT_PAR_NUM_00));
            ssmParam.put("splyQuoteCaseBf", splyQuoteAttrList.get(SQL_TXT_PAR_NUM_01));
            ssmParam.put("splyQuoteCaseAf", splyQuoteAttrList.get(SQL_TXT_PAR_NUM_02));
            ssmParam.put("splyQuoteComp", splyQuoteAttrList.get(SQL_TXT_PAR_NUM_03));
        }

        List<String> splyQuoteSlsCrAttrList = sqlPrmMap.get(LIST_NAME_SPLY_QUOTE_SLS_CR);
        if (splyQuoteSlsCrAttrList != null && !splyQuoteSlsCrAttrList.isEmpty()) {
            ssmParam.put("splyQuoteSlsCrRecFlg", SQL_OUTPUT_FLG_ON);
            ssmParam.put("splyQuoteSlsCrAttr", splyQuoteSlsCrAttrList.get(SQL_TXT_PAR_NUM_00));
            ssmParam.put("splyQuoteSlsCrCaseBf", splyQuoteSlsCrAttrList.get(SQL_TXT_PAR_NUM_01));
            ssmParam.put("splyQuoteSlsCrCaseAf", splyQuoteSlsCrAttrList.get(SQL_TXT_PAR_NUM_02));
            ssmParam.put("splyQuoteSlsCrComp", splyQuoteSlsCrAttrList.get(SQL_TXT_PAR_NUM_03));
        }
    }

    /**
     * Set SQL Quote Line
     * @param bizMsg NWAL1810CMsg
     * @param ssmParam Map
     */
    private void setSqlQuoteLine(NWAL1810CMsg bizMsg, Map<String, Object> ssmParam, Map<String, List<String>> splPrmMap) {

        List<String> splyQuoteDtlAttrList = splPrmMap.get(LIST_NAME_SPLY_QUOTE_DTL);
        if (splyQuoteDtlAttrList != null && !splyQuoteDtlAttrList.isEmpty()) {
            ssmParam.put("splyQuoteDtlRecFlg", SQL_OUTPUT_FLG_ON);
            ssmParam.put("splyQuoteDtlAttr", splyQuoteDtlAttrList.get(SQL_TXT_PAR_NUM_00));
            ssmParam.put("splyQuoteDtlCaseBf", splyQuoteDtlAttrList.get(SQL_TXT_PAR_NUM_01));
            ssmParam.put("splyQuoteDtlCaseAf", splyQuoteDtlAttrList.get(SQL_TXT_PAR_NUM_02));
            ssmParam.put("splyQuoteDtlComp", splyQuoteDtlAttrList.get(SQL_TXT_PAR_NUM_03));
        }

        List<String> splyQuoteCalcBaseAttrList = splPrmMap.get(LIST_NAME_SPLY_QUOTE_CALC_BASE);
        if (splyQuoteCalcBaseAttrList != null && !splyQuoteCalcBaseAttrList.isEmpty()) {
            ssmParam.put("splyQuotePrcCalcBaseRecFlg", SQL_OUTPUT_FLG_ON);
            ssmParam.put("splyQuotePrcCalcBaseAttr", splyQuoteCalcBaseAttrList.get(SQL_TXT_PAR_NUM_00));
            ssmParam.put("splyQuotePrcCalcBaseCaseBf", splyQuoteCalcBaseAttrList.get(SQL_TXT_PAR_NUM_01));
            ssmParam.put("splyQuotePrcCalcBaseCaseAf", splyQuoteCalcBaseAttrList.get(SQL_TXT_PAR_NUM_02));
            ssmParam.put("splyQuotePrcCalcBaseComp", splyQuoteCalcBaseAttrList.get(SQL_TXT_PAR_NUM_03));
        }
    }

    /**
     * Set Union Flag QUOTE
     * @param bizMsg NWAL1810CMsg
     * @param ssmParam Map
     */
    private void setUnionFlgQuote(NWAL1810CMsg bizMsg, Map<String, Object> ssmParam) {

        boolean firstUnionFlg = true;

        if (SQL_OUTPUT_FLG_ON.equals(ssmParam.get("splyQuoteRecFlg"))) {
            firstUnionFlg = false;
        }

        if (SQL_OUTPUT_FLG_ON.equals(ssmParam.get("splyQuoteSlsCrRecFlg"))) {
            if (firstUnionFlg) {
                ssmParam.put("splyQuoteSlsCrRecUnionFlg", SQL_UNION_OUTPUT_FLG_OFF);
                firstUnionFlg = false;
            } else {
                ssmParam.put("splyQuoteSlsCrRecUnionFlg", SQL_UNION_OUTPUT_FLG_ON);
            }
        }

        if (SQL_OUTPUT_FLG_ON.equals(ssmParam.get("splyQuoteDtlRecFlg"))) {
            if (firstUnionFlg) {
                ssmParam.put("splyQuoteDtlRecUnionFlg", SQL_UNION_OUTPUT_FLG_OFF);
                firstUnionFlg = false;
            } else {
                ssmParam.put("splyQuoteDtlRecUnionFlg", SQL_UNION_OUTPUT_FLG_ON);
            }
        }

        if (SQL_OUTPUT_FLG_ON.equals(ssmParam.get("splyQuotePrcCalcBaseRecFlg"))) {
            if (firstUnionFlg) {
                ssmParam.put("splyQuotePrcCalcBaseRecUnionFlg", SQL_UNION_OUTPUT_FLG_OFF);
                firstUnionFlg = false;
            } else {
                ssmParam.put("splyQuotePrcCalcBaseRecUnionFlg", SQL_UNION_OUTPUT_FLG_ON);
            }
        }

        if (firstUnionFlg) {
            ssmParam.put("compDummyOutputFlg", SQL_OUTPUT_FLG_ON);
        }
    }

    /**
     * Get Detail Schedule List
     * @param bizMsg NWAL1810CMsg
     * @param glblMsg NWAL1810SMsg
     * @param configList List<String>
     * @param docIdConfigList List<String>
     * @param docIdLineList List<String>
     * @param splPrmMap Map<String, List<String>>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDetailScheduleList(//
            NWAL1810CMsg bizMsg //
            , NWAL1810SMsg glblMsg //
            , List<String> configList //
            , List<String> docIdConfigList //
            , List<String> docIdLineList //
            , Map<String, List<String>> splPrmMap) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("rowNum", glblMsg.B.length() + 1);
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("prntDocId", bizMsg.xxViewChngLogSrchNum);
        ssmParam.put("procId", PROC_ID_OM);
        ssmParam.put("userOpsFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("ordPrftTrxCatgOutbound", ORD_PRFT_TRX_CATG.OUTBOUND);
        ssmParam.put("docTpScheduleAgreement", DOC_TP_CD_SCHEDULE_AGREEMENT);

        ssmParam.put("dbNameSchdAgmtRec", DB_NAME_SCHD_AGMT_REC);
        ssmParam.put("dbNameSchdAgmtSlsCrRec", DB_NAME_SCHD_AGMT_SLS_CR_REC);
        ssmParam.put("dbNameSchdAgmtLineRec", DB_NAME_SCHD_AGMT_LINE_REC);
        ssmParam.put("dbNameSchdAgmtCalcBaseRec", DB_NAME_SCHD_AGMT_CALC_BASE_REC);
        ssmParam.put("dbNameSchdAgmtPlnRec", DB_NAME_SCHD_AGMT_PLN_REC);

        if (bizMsg.L.getValidCount() > 0) {
            ssmParam.put("docTpCd", DOC_TP_CD_OM);
        }
        if (bizMsg.R.getValidCount() > 0) {
            ssmParam.put("docTpCd", DOC_TP_CD_RT);
        }

        if (LVL_CD_ALL.equals(bizMsg.noteLvlCd.getValue())) {

            setSqlScheduleHeader(bizMsg, ssmParam, splPrmMap);
            setSqlScheduleConf(bizMsg, ssmParam, splPrmMap);
            setSqlScheduleLine(bizMsg, ssmParam, splPrmMap);

        } else if (LVL_CD_CONF.equals(bizMsg.noteLvlCd.getValue())) {

            setSqlScheduleConf(bizMsg, ssmParam, splPrmMap);
            setSqlScheduleLine(bizMsg, ssmParam, splPrmMap);
            ssmParam.put("configList", configList);
            ssmParam.put("docIdConfigList", docIdConfigList);

        } else {

            setSqlScheduleLine(bizMsg, ssmParam, splPrmMap);
            ssmParam.put("docIdLineList", docIdLineList);
        }

        setUnionFlgSchedule(bizMsg, ssmParam);

        S21SsmEZDResult rslt = getSsmEZDClient().queryEZDMsgArray("getDetailScheduleList", ssmParam, glblMsg.B);
        NWAL1810CommonLogic.formatTimestampForBGlblMsg(glblMsg.B);
        NWAL1810CommonLogic.convLineStrForBGlblMsg(//
                glblMsg.B, bizMsg.xxViewChngLogSrchNum.getValue(), bizMsg.xxTrnsnOrigScrnId.getValue());
        return rslt;
    }

    /**
     * Set SQL Schedule All
     * @param bizMsg NWAL1810CMsg
     * @param ssmParam Map
     */
    private void setSqlScheduleHeader(NWAL1810CMsg bizMsg, Map<String, Object> ssmParam, Map<String, List<String>> splPrmMap) {

        List<String> schdAgmtAttrList = splPrmMap.get(LIST_NAME_SCHD_AGMT);
        if (schdAgmtAttrList != null && !schdAgmtAttrList.isEmpty()) {
            ssmParam.put("schdAgmtRecFlg", SQL_OUTPUT_FLG_ON);
            ssmParam.put("schdAgmtAttr", schdAgmtAttrList.get(SQL_TXT_PAR_NUM_00));
            ssmParam.put("schdAgmtCaseBf", schdAgmtAttrList.get(SQL_TXT_PAR_NUM_01));
            ssmParam.put("schdAgmtCaseAf", schdAgmtAttrList.get(SQL_TXT_PAR_NUM_02));
            ssmParam.put("schdAgmtComp", schdAgmtAttrList.get(SQL_TXT_PAR_NUM_03));
        }

        List<String> schdAgmtSlsCrAttrList = splPrmMap.get(LIST_NAME_SCHD_AGMT_SLS_CR);
        if (schdAgmtSlsCrAttrList != null && !schdAgmtSlsCrAttrList.isEmpty()) {
            ssmParam.put("schdAgmtSlsCrRecFlg", SQL_OUTPUT_FLG_ON);
            ssmParam.put("schdAgmtSlsCrAttr", schdAgmtSlsCrAttrList.get(SQL_TXT_PAR_NUM_00));
            ssmParam.put("schdAgmtSlsCrCaseBf", schdAgmtSlsCrAttrList.get(SQL_TXT_PAR_NUM_01));
            ssmParam.put("schdAgmtSlsCrCaseAf", schdAgmtSlsCrAttrList.get(SQL_TXT_PAR_NUM_02));
            ssmParam.put("schdAgmtSlsCrComp", schdAgmtSlsCrAttrList.get(SQL_TXT_PAR_NUM_03));
        }
    }

    /**
     * Set SQL Schedule Conf
     * @param bizMsg NWAL1810CMsg
     * @param ssmParam Map
     */
    private void setSqlScheduleConf(NWAL1810CMsg bizMsg, Map<String, Object> ssmParam, Map<String, List<String>> splPrmMap) {

        List<String> schdAgmtLineAttrList = splPrmMap.get(LIST_NAME_SCHD_AGMT_LINE);
        if (schdAgmtLineAttrList != null && !schdAgmtLineAttrList.isEmpty()) {
            ssmParam.put("schdAgmtLineRecFlg", SQL_OUTPUT_FLG_ON);
            ssmParam.put("schdAgmtLineAttr", schdAgmtLineAttrList.get(SQL_TXT_PAR_NUM_00));
            ssmParam.put("schdAgmtLineCaseBf", schdAgmtLineAttrList.get(SQL_TXT_PAR_NUM_01));
            ssmParam.put("schdAgmtLineCaseAf", schdAgmtLineAttrList.get(SQL_TXT_PAR_NUM_02));
            ssmParam.put("schdAgmtLineComp", schdAgmtLineAttrList.get(SQL_TXT_PAR_NUM_03));
        }

        List<String> schdAgmtPrcCalcAttrList = splPrmMap.get(LIST_NAME_SCHD_AGMT_PRC_CALC);
        if (schdAgmtPrcCalcAttrList != null && !schdAgmtPrcCalcAttrList.isEmpty()) {
            ssmParam.put("schdAgmtPrcCalcRecFlg", SQL_OUTPUT_FLG_ON);
            ssmParam.put("schdAgmtPrcCalcAttr", schdAgmtPrcCalcAttrList.get(SQL_TXT_PAR_NUM_00));
            ssmParam.put("schdAgmtPrcCalcCaseBf", schdAgmtPrcCalcAttrList.get(SQL_TXT_PAR_NUM_01));
            ssmParam.put("schdAgmtPrcCalcCaseAf", schdAgmtPrcCalcAttrList.get(SQL_TXT_PAR_NUM_02));
            ssmParam.put("schdAgmtPrcCalcComp", schdAgmtPrcCalcAttrList.get(SQL_TXT_PAR_NUM_03));
        }
    }

    /**
     * Set SQL Schedule Line
     * @param bizMsg NWAL1810CMsg
     * @param ssmParam Map
     */
    private void setSqlScheduleLine(NWAL1810CMsg bizMsg, Map<String, Object> ssmParam, Map<String, List<String>> splPrmMap) {

        List<String> schdAgmtPlnAttrList = splPrmMap.get(LIST_NAME_SCHD_AGMT_PLN);
        if (schdAgmtPlnAttrList != null && !schdAgmtPlnAttrList.isEmpty()) {
            ssmParam.put("schdAgmtPlnRecFlg", SQL_OUTPUT_FLG_ON);
            ssmParam.put("schdAgmtPlnAttr", schdAgmtPlnAttrList.get(SQL_TXT_PAR_NUM_00));
            ssmParam.put("schdAgmtPlnCaseBf", schdAgmtPlnAttrList.get(SQL_TXT_PAR_NUM_01));
            ssmParam.put("schdAgmtPlnCaseAf", schdAgmtPlnAttrList.get(SQL_TXT_PAR_NUM_02));
            ssmParam.put("schdAgmtPlnComp", schdAgmtPlnAttrList.get(SQL_TXT_PAR_NUM_03));
        }
    }

    /**
     * Set Union Flag Schedule
     * @param bizMsg NWAL1810CMsg
     * @param ssmParam Map
     */
    private void setUnionFlgSchedule(NWAL1810CMsg bizMsg, Map<String, Object> ssmParam) {

        boolean firstUnionFlg = true;

        if (SQL_OUTPUT_FLG_ON.equals(ssmParam.get("schdAgmtRecFlg"))) {
            firstUnionFlg = false;
        }

        if (SQL_OUTPUT_FLG_ON.equals(ssmParam.get("schdAgmtSlsCrRecFlg"))) {
            if (firstUnionFlg) {
                ssmParam.put("schdAgmtSlsCrRecUnionFlg", SQL_UNION_OUTPUT_FLG_OFF);
                firstUnionFlg = false;
            } else {
                ssmParam.put("schdAgmtSlsCrRecUnionFlg", SQL_UNION_OUTPUT_FLG_ON);
            }
        }

        if (SQL_OUTPUT_FLG_ON.equals(ssmParam.get("schdAgmtLineRecFlg"))) {
            if (firstUnionFlg) {
                ssmParam.put("schdAgmtLineRecUnionFlg", SQL_UNION_OUTPUT_FLG_OFF);
                firstUnionFlg = false;
            } else {
                ssmParam.put("schdAgmtLineRecUnionFlg", SQL_UNION_OUTPUT_FLG_ON);
            }
        }

        if (SQL_OUTPUT_FLG_ON.equals(ssmParam.get("schdAgmtPrcCalcRecFlg"))) {
            if (firstUnionFlg) {
                ssmParam.put("schdAgmtPrcCalcRecUnionFlg", SQL_UNION_OUTPUT_FLG_OFF);
                firstUnionFlg = false;
            } else {
                ssmParam.put("schdAgmtPrcCalcRecUnionFlg", SQL_UNION_OUTPUT_FLG_ON);
            }
        }

        if (SQL_OUTPUT_FLG_ON.equals(ssmParam.get("schdAgmtPlnRecFlg"))) {
            if (firstUnionFlg) {
                ssmParam.put("schdAgmtPlnRecUnionFlg", SQL_UNION_OUTPUT_FLG_OFF);
                firstUnionFlg = false;
            } else {
                ssmParam.put("schdAgmtPlnRecUnionFlg", SQL_UNION_OUTPUT_FLG_ON);
            }
        }

        if (firstUnionFlg) {
            ssmParam.put("compDummyOutputFlg", SQL_OUTPUT_FLG_ON);
        }
    }

    // 2022/10/20 QC#60258 Add Start
    private void setSearchLogParam(NWAL1810CMsg bizMsg, Map<String, Object> ssmParam) {

        String activeTab = bizMsg.xxDplyTab.getValue();

        if(ZYPCommonFunc.hasValue(bizMsg.xxFromDt.getValue())){
            ssmParam.put("xxFromDt", (bizMsg.xxFromDt.getValue() + DATE_TIME_FORMAT_FROM_DT));
        } else {
            ssmParam.put("xxFromDt", null);
        }
        if(ZYPCommonFunc.hasValue(bizMsg.xxThruDt.getValue())){
            ssmParam.put("xxThruDt", (bizMsg.xxThruDt.getValue() + DATE_TIME_FORMAT_THRU_DT));
        } else {
            ssmParam.put("xxThruDt", null);
        }
        ssmParam.put("eventNm", (bizMsg.eventId.getValue()));
        ssmParam.put("ordPrftTrxCatgCd", (bizMsg.ordPrftTrxCatgCd.getValue()));
        if (bizMsg.docId.getValue().contains(PERCENT)) {
            ssmParam.put("lineSearchTypeFlg", SQL_LINE_SEARCH_TYPE_FLAG_ON);
            ssmParam.put("docId", bizMsg.docId.getValue());
        } else {
            ssmParam.put("lineSearchTypeFlg", SQL_LINE_SEARCH_TYPE_FLAG_OFF);
            String[] docIdList = NWAL1810CommonLogic.formatDocId(bizMsg.docId.getValue());
            if (docIdList == null || docIdList.length <= 0) {
                ssmParam.put("lineSearchTypeFlg", SQL_LINE_SEARCH_TYPE_FLAG_ON);
            }
            ssmParam.put("docId", bizMsg.docId.getValue());
            ssmParam.put("docIdList", docIdList);
        }
        ssmParam.put("xxSrUsrId", (bizMsg.xxSrUsrId.getValue()));
        ssmParam.put("xxPsnNm", bizMsg.xxPsnNm.getValue());
        ssmParam.put("bizProcCmntTxt", bizMsg.bizProcCmntTxt.getValue());
        if (TAB_DETAIL.equals(activeTab)) {
            ssmParam.put("recDbItemAttrbNm", bizMsg.recDbItemAttrbNm.getValue());
            ssmParam.put("xxRecValBefTxt", bizMsg.xxRecValBefTxt.getValue());
            ssmParam.put("xxRecValAftTxt", bizMsg.xxRecValAftTxt.getValue());
        }
    }
    // 2022/10/20 QC#60258 Add End

    /**
     * getDisplayLineStr
     * @param dtlLineNum String
     * @param dtlLineSubNum String
     * @param ordNum String
     * @param outboundFlg String
     * @param sqlId String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDisplayLineStr(//
            String dtlLineNum, String dtlLineSubNum, String ordNum, String outboundFlg, String sqlId) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("ordNum", ordNum);
        ssmParam.put("dtlLineNum", dtlLineNum);
        ssmParam.put("dtlLineSubNum", dtlLineSubNum);
        ssmParam.put("outboundFlg", outboundFlg);

        ssmParam.put("quoteConfigPreFix", QUOTE_CONFIG_PREFIX);

        return getSsmEZDClient().queryObjectList(sqlId, ssmParam);
    }

    //2022/10/20 QC#60258 Add Start
    /**
     * Set Change Event List
     * @param bizMsg NWAL1810CMsg
     * @param ssmParam Map
     */
    public S21SsmEZDResult getChangeEventList(NWAL1810CMsg bizMsg, NWAL1810SMsg glblMsg) {
        S21SsmEZDResult ssmResult = null;
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("procId", PROC_ID_OM);
        ssmResult = getSsmEZDClient().queryObjectList("getEventList", ssmParam);
        return ssmResult;
    }
    //2022/10/20 QC#60258 Add End
}
