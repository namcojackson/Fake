/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1810;

import static business.blap.NWAL1810.constant.NWAL1810Constant.*;
import static business.blap.NWAL1810.constant.NWAL1810Constant.LIST_HEADER_NAME_DETAIL;
import static business.blap.NWAL1810.constant.NWAL1810Constant.LIST_HEADER_NAME_SUMMARY;
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
import static business.blap.NWAL1810.constant.NWAL1810Constant.NWAM0006I;
import static business.blap.NWAL1810.constant.NWAL1810Constant.NZZM0001W;
import static business.blap.NWAL1810.constant.NWAL1810Constant.PERCENT;
import static business.blap.NWAL1810.constant.NWAL1810Constant.PERIOD;
import static business.blap.NWAL1810.constant.NWAL1810Constant.SOURCE_ID_ORDER;
import static business.blap.NWAL1810.constant.NWAL1810Constant.SOURCE_ID_QUOTE;
import static business.blap.NWAL1810.constant.NWAL1810Constant.SPACE;
import static business.blap.NWAL1810.constant.NWAL1810Constant.SQL_AND;
import static business.blap.NWAL1810.constant.NWAL1810Constant.SQL_INEQUALITY_BOND;
import static business.blap.NWAL1810.constant.NWAL1810Constant.SQL_IS_NOT_NULL;
import static business.blap.NWAL1810.constant.NWAL1810Constant.SQL_IS_NULL;
import static business.blap.NWAL1810.constant.NWAL1810Constant.SQL_OR;
import static business.blap.NWAL1810.constant.NWAL1810Constant.SQL_TABLE_ALIAS_NAME_AFTER_01;
import static business.blap.NWAL1810.constant.NWAL1810Constant.SQL_TABLE_ALIAS_NAME_BEFORE_01;
import static business.blap.NWAL1810.constant.NWAL1810Constant.TAB_DETAIL;
import static business.blap.NWAL1810.constant.NWAL1810Constant.TAB_SUMMARY;
import static business.blap.NWAL1810.constant.NWAL1810Constant.DEF_CD_SUMMARY;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL1810.common.NWAL1810CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_PRFT_TRX_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;


/**
 *<pre>
 * NWAL1810BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/25   Fujitsu         S.Ohki          Create          N/A
 * 2016/04/01   Fujitsu         M.Yamada        Update          QC#5283
 * 2016/04/13   Fujitsu         M.Yamada        Update          QC#6965
 * 2016/04/20   Fujitsu         M.Yamada        Update          QC#7245
 * 2022/10/20   Hitachi         H.Watanabe      Update          QC#60258
 *</pre>
 */
public class NWAL1810BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL1810CMsg bizMsg = (NWAL1810CMsg) cMsg;
            NWAL1810SMsg glblMsg = (NWAL1810SMsg) sMsg;

            if ("NWAL1810_INIT".equals(screenAplID)) {
                doProcess_NWAL1810_INIT(bizMsg, glblMsg);
            // 2022/10/20 QC#60258 Add Start
            } else if ("NWAL1810Scrn00_Search".equals(screenAplID)) {
                doProcess_NWAL1810Scrn00_SEARCH(bizMsg, glblMsg);
            } else if ("NWAL1810Scrn00_CMN_Clear".equals(screenAplID)){
                doProcess_NWAL1810Scrn00_CMN_Clear(bizMsg, glblMsg);
            // 2022/10/20 QC#60258 Add End
            } else if ("NWAL1810Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NWAL1810Scrn00_PageNext(bizMsg, glblMsg);

            } else if ("NWAL1810Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NWAL1810Scrn00_PagePrev(bizMsg, glblMsg);

            } else if ("NWAL1810Scrn00_TAB_Detail".equals(screenAplID)) {
                doProcess_NWAL1810Scrn00_TAB_Detail(bizMsg, glblMsg);

            } else if ("NWAL1810Scrn00_TAB_Summary".equals(screenAplID)) {
                doProcess_NWAL1810Scrn00_TAB_Summary(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1810_INIT(NWAL1810CMsg bizMsg, NWAL1810SMsg glblMsg) {

        String activeTab = bizMsg.xxDplyTab.getValue();

        //2022/10/20 QC#60258 Add Start
        setUpEventList(bizMsg, glblMsg);
        ZYPCodeDataUtil.createCodePulldownList(ORD_PRFT_TRX_CATG.class,  bizMsg.ordPrftTrxCatgCd_PL, bizMsg.ordPrftTrxCatgNm_PL);
        //2022/10/20 QC#60258 Add End

        if (TAB_SUMMARY.equals(activeTab)) {
            ZYPGUITableColumn.getColData((NWAL1810CMsg) bizMsg, (NWAL1810SMsg) glblMsg, LIST_HEADER_NAME_SUMMARY);
            searchSummary(bizMsg, glblMsg);
        } else if (TAB_DETAIL.equals(activeTab)) {
            ZYPGUITableColumn.getColData((NWAL1810CMsg) bizMsg, (NWAL1810SMsg) glblMsg, LIST_HEADER_NAME_DETAIL);
            searchDetail(bizMsg, glblMsg);
        }
    }

    //2022/10/20 QC#60258 Add Start
    /**
     * Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1810Scrn00_SEARCH(NWAL1810CMsg bizMsg, NWAL1810SMsg glblMsg) {
        String activeTab = bizMsg.xxDplyTab.getValue();

        if (TAB_SUMMARY.equals(activeTab)) {
            ZYPGUITableColumn.getColData((NWAL1810CMsg) bizMsg, (NWAL1810SMsg) glblMsg, LIST_HEADER_NAME_SUMMARY);
            searchSummary(bizMsg, glblMsg);
        } else if (TAB_DETAIL.equals(activeTab)) {
            ZYPGUITableColumn.getColData((NWAL1810CMsg) bizMsg, (NWAL1810SMsg) glblMsg, LIST_HEADER_NAME_DETAIL);
            searchDetail(bizMsg, glblMsg);
        }
    }

    /**
     * Clear Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1810Scrn00_CMN_Clear(NWAL1810CMsg bizMsg, NWAL1810SMsg glblMsg) {
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(bizMsg.B);
        ZYPTableUtil.clear(bizMsg.C);
        ZYPTableUtil.clear(bizMsg.D);
        ZYPTableUtil.clear(bizMsg.L);
        ZYPTableUtil.clear(bizMsg.R);
        ZYPTableUtil.clear(bizMsg.S);
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(glblMsg.B);
        
        if (DEF_CD_SUMMARY.equals(bizMsg.xxDefSelTabCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab, TAB_SUMMARY);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab, TAB_DETAIL);
        }

        String activeTab = bizMsg.xxDplyTab.getValue();
        NWAL1810CommonLogic.clearSearchParam(bizMsg);

        if (TAB_SUMMARY.equals(activeTab)) {
            ZYPGUITableColumn.getColData((NWAL1810CMsg) bizMsg, (NWAL1810SMsg) glblMsg, LIST_HEADER_NAME_SUMMARY);
            searchSummary(bizMsg, glblMsg);
        } else if (TAB_DETAIL.equals(activeTab)) {
            ZYPGUITableColumn.getColData((NWAL1810CMsg) bizMsg, (NWAL1810SMsg) glblMsg, LIST_HEADER_NAME_DETAIL);
            searchDetail(bizMsg, glblMsg);
        }
    }

    //2022/10/20 QC#60258 Add End
    
    /**
     * PageNext Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1810Scrn00_PageNext(NWAL1810CMsg bizMsg, NWAL1810SMsg glblMsg) {

        String activeTab = bizMsg.xxDplyTab.getValue();

        if (TAB_SUMMARY.equals(activeTab)) {
            NWAL1810CommonLogic.pageSettingPageNextSummary(bizMsg, glblMsg);
        } else if (TAB_DETAIL.equals(activeTab)) {
            NWAL1810CommonLogic.pageSettingPageNextDetail(bizMsg, glblMsg);
        }
    }

    /**
     * PagePrev Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1810Scrn00_PagePrev(NWAL1810CMsg bizMsg, NWAL1810SMsg glblMsg) {

        String activeTab = bizMsg.xxDplyTab.getValue();

        if (TAB_SUMMARY.equals(activeTab)) {
            NWAL1810CommonLogic.pageSettingPagePrevSummary(bizMsg, glblMsg);
        } else if (TAB_DETAIL.equals(activeTab)) {
            NWAL1810CommonLogic.pageSettingPagePrevDetail(bizMsg, glblMsg);
        }
    }

    /**
     * TAB_Detail Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1810Scrn00_TAB_Detail(NWAL1810CMsg bizMsg, NWAL1810SMsg glblMsg) {
        ZYPGUITableColumn.getColData((NWAL1810CMsg) bizMsg, (NWAL1810SMsg) glblMsg, LIST_HEADER_NAME_DETAIL);
        searchDetail(bizMsg, glblMsg);
    }

    /**
     * TAB_Summary Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1810Scrn00_TAB_Summary(NWAL1810CMsg bizMsg, NWAL1810SMsg glblMsg) {
        ZYPGUITableColumn.getColData((NWAL1810CMsg) bizMsg, (NWAL1810SMsg) glblMsg, LIST_HEADER_NAME_SUMMARY);
        searchSummary(bizMsg, glblMsg);
    }

    /**
     * Get Attribute List
     * @param bizMsg NWAL1810CMsg
     * @param tblNm String
     * @return List
     */
    private List<Map<String, Object>> getAttrList(NWAL1810CMsg bizMsg, String tblNm, String lvlNum) {

        S21SsmEZDResult attrRes = NWAL1810Query.getInstance().getViewChngLogAttrbList(bizMsg, tblNm, lvlNum);
        if (attrRes.isCodeNotFound()) {
            return null;
        }
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> rtnVal = (List<Map<String, Object>>) attrRes.getResultObject();
        return rtnVal;
    }

    /**
     * Search Detail
     * @param bizMsg NWAL1810CMsg
     * @param glblMsg NWAL1810SMsg
     */
    private void searchDetail(NWAL1810CMsg bizMsg, NWAL1810SMsg glblMsg) {

        if (SOURCE_ID_ORDER.equals(bizMsg.xxViewChngLogSrcCd.getValue())) {
            searchDetailOrder(bizMsg, glblMsg);
        } else if (SOURCE_ID_QUOTE.equals(bizMsg.xxViewChngLogSrcCd.getValue())) {
            searchDetailQuote(bizMsg, glblMsg);
        } else {
            searchDetailSchedule(bizMsg, glblMsg);
        }
    }

    /**
     * Set Attribute Dual
     * @param attrList List
     * @return String
     */
    private String setAttrDualSql(List<Map<String, Object>> attrList) {

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < attrList.size(); i++) {
            Map<String, Object> attrMap = (Map<String, Object>) attrList.get(i);

            if (i != 0) {
                sb.append("UNION ");
            }
            sb.append("SELECT '");
            sb.append(attrMap.get("REC_DB_ITEM_NM")).append("' AS ATTR ,'");
            sb.append(attrMap.get("REC_DB_ITEM_ATTRB_NM")).append("' AS ATTR_NAME ,'");
            sb.append(attrMap.get("REC_DB_NM")).append("' AS ATTR_DB_NAME FROM DUAL ");
            sb.append(LINE_BREAK);
        }
        return sb.toString();
    }

    /**
     * Set Attribute Dual
     * @param attrList List
     * @param aliasNm String
     * @return String
     */
    private String setCaseWhenSql(List<Map<String, Object>> attrList, String aliasNm) {

        if (!ZYPCommonFunc.hasValue(aliasNm)) {
            aliasNm = SQL_TABLE_ALIAS_NAME_BEFORE_01;
        }

        StringBuffer sb = new StringBuffer();
        for (Map<String, Object> attrMap : attrList) {

            sb.append("WHEN A2.ATTR = '");
            sb.append(attrMap.get("REC_DB_ITEM_NM"));
            sb.append("' THEN TO_CHAR(");
            sb.append(aliasNm + PERIOD);
            sb.append(attrMap.get("REC_DB_ITEM_NM"));
            sb.append(")");
            sb.append(LINE_BREAK);
        }
        return sb.toString();
    }

    /**
     * Set Attribute Dual
     * @param attrList List
     * @param befAliasNm String
     * @param aftAliasNm String
     * @return String
     */
    private String setCompSql(List<Map<String, Object>> attrList) {

        return setCompSql(attrList, null, null);
    }

    /**
     * Set Attribute Dual
     * @param attrList List
     * @param befAliasNm String
     * @param aftAliasNm String
     * @return String
     */
    private String setCompSql(List<Map<String, Object>> attrList, String befAliasNm, String aftAliasNm) {

        if (!ZYPCommonFunc.hasValue(befAliasNm)) {
            befAliasNm = SQL_TABLE_ALIAS_NAME_BEFORE_01;
        }
        if (!ZYPCommonFunc.hasValue(aftAliasNm)) {
            aftAliasNm = SQL_TABLE_ALIAS_NAME_AFTER_01;
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < attrList.size(); i++) {
            Map<String, Object> attrMap = (Map<String, Object>) attrList.get(i);

            if (i != 0) {
                sb.append(SQL_OR + SPACE);
            }
            sb.append(befAliasNm + PERIOD);
            sb.append(attrMap.get("REC_DB_ITEM_NM"));
            sb.append(SPACE + SQL_INEQUALITY_BOND + SPACE);
            sb.append(aftAliasNm + PERIOD);
            sb.append(attrMap.get("REC_DB_ITEM_NM"));
            sb.append(LINE_BREAK);

            sb.append(SQL_OR + SPACE + "(");
            sb.append(befAliasNm + PERIOD);
            sb.append(attrMap.get("REC_DB_ITEM_NM"));
            sb.append(SPACE + SQL_IS_NULL);
            sb.append(SPACE + SQL_AND + SPACE);
            sb.append(aftAliasNm + PERIOD);
            sb.append(attrMap.get("REC_DB_ITEM_NM"));
            sb.append(SPACE + SQL_IS_NOT_NULL + ")");
            sb.append(LINE_BREAK);

            sb.append(SQL_OR + SPACE + "(");
            sb.append(befAliasNm + PERIOD);
            sb.append(attrMap.get("REC_DB_ITEM_NM"));
            sb.append(SPACE + SQL_IS_NOT_NULL);
            sb.append(SPACE + SQL_AND + SPACE);
            sb.append(aftAliasNm + PERIOD);
            sb.append(attrMap.get("REC_DB_ITEM_NM"));
            sb.append(SPACE + SQL_IS_NULL + ")");
            sb.append(LINE_BREAK);
        }
        return sb.toString();
    }

    /**
     * Search Summary
     * @param bizMsg NWAL1810CMsg
     * @param glblMsg NWAL1810SMsg
     */
    private void searchSummary(NWAL1810CMsg bizMsg, NWAL1810SMsg glblMsg) {

        S21SsmEZDResult ssmResult = NWAL1810Query.getInstance().getSummaryList(bizMsg, glblMsg);

        if (ssmResult.isCodeNormal()) {

            // Maximum acquisition number exceeded message
            int queryResCnt = ssmResult.getQueryResultCount();
            
            if (queryResCnt > glblMsg.A.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
                queryResCnt = glblMsg.A.length();
            }

            // Search results posting
            int i = 0;
            for (; i < glblMsg.A.getValidCount(); i++) {
                if (i == bizMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(glblMsg.A.no(i), null, bizMsg.A.no(i), null);
            }
            bizMsg.A.setValidCount(i);

            // Settings page item
            bizMsg.xxPageShowFromNum_A.setValue(1);
            bizMsg.xxPageShowToNum_A.setValue(bizMsg.A.getValidCount());
            bizMsg.xxPageShowOfNum_A.setValue(queryResCnt);

        } else {
            bizMsg.setMessageInfo(NWAM0006I);
            bizMsg.xxPageShowFromNum_A.clear();
            bizMsg.xxPageShowToNum_A.clear();
            bizMsg.xxPageShowOfNum_A.clear();
        }
    }

    /**
     * Search Detail Order
     * @param bizMsg NWAL1810CMsg
     * @param glblMsg NWAL1810SMsg
     */
    private void searchDetailOrder(NWAL1810CMsg bizMsg, NWAL1810SMsg glblMsg) {

        Map<String, List<String>> sqlPrmMap = new HashMap<String, List<String>>();

        // ALL(Header) LEVEL
        // Get Column Name CPO
        List<Map<String, Object>> cpoAttrList = getAttrList(bizMsg, "CPO_REC", LVL_CD_ALL);

        List<Map<String, Object>> cpoList = new ArrayList<Map<String, Object>>();

        if (cpoAttrList != null) {
            cpoList.addAll(cpoAttrList);
        }

        // Get Column Name CPO_REC/DS_CPO_REC
        String cpoSql = "";
        if (cpoList != null && cpoList.size() > 0) {
            List<String> sqlPrmList = new ArrayList<String>();
            cpoSql = setAttrDualSql(cpoList);
            sqlPrmList.add(cpoSql);
            cpoSql = setCaseWhenSql(cpoAttrList, SQL_TABLE_ALIAS_NAME_BEFORE_01);
            sqlPrmList.add(cpoSql);
            cpoSql = setCaseWhenSql(cpoAttrList, SQL_TABLE_ALIAS_NAME_AFTER_01);
            sqlPrmList.add(cpoSql);
            cpoSql = setCompSql(cpoAttrList) + SQL_OR + SPACE + setCompSql(cpoAttrList, SQL_TABLE_ALIAS_NAME_BEFORE_01, SQL_TABLE_ALIAS_NAME_AFTER_01);
            sqlPrmList.add(cpoSql);
            sqlPrmMap.put(LIST_NAME_CPO, sqlPrmList);
        }

        // Get Column Name DS_CPO_SLS_CR_REC(ALL)
        List<Map<String, Object>> cpoSlsAttrList = getAttrList(bizMsg, "DS_CPO_SLS_CR_REC", LVL_CD_ALL);
        if (cpoSlsAttrList != null && cpoSlsAttrList.size() > 0) {
            List<String> sqlPrmList = new ArrayList<String>();
            sqlPrmList.add(setAttrDualSql(cpoSlsAttrList));
            sqlPrmList.add(setCaseWhenSql(cpoSlsAttrList, SQL_TABLE_ALIAS_NAME_BEFORE_01));
            sqlPrmList.add(setCaseWhenSql(cpoSlsAttrList, SQL_TABLE_ALIAS_NAME_AFTER_01));
            sqlPrmList.add(setCompSql(cpoSlsAttrList));
            sqlPrmMap.put(LIST_NAME_CPO_SLS, sqlPrmList);
        }

        // Configuration LEVEL
        // Get Column Name DS_CPO_CONFIG_REC
        List<Map<String, Object>> dsCpoConfAttrList = getAttrList(bizMsg, "DS_CPO_CONFIG_REC", LVL_CD_CONF);
        if (dsCpoConfAttrList != null && dsCpoConfAttrList.size() > 0) {
            List<String> sqlPrmList = new ArrayList<String>();
            sqlPrmList.add(setAttrDualSql(dsCpoConfAttrList));
            sqlPrmList.add(setCaseWhenSql(dsCpoConfAttrList, SQL_TABLE_ALIAS_NAME_BEFORE_01));
            sqlPrmList.add(setCaseWhenSql(dsCpoConfAttrList, SQL_TABLE_ALIAS_NAME_AFTER_01));
            sqlPrmList.add(setCompSql(dsCpoConfAttrList));
            sqlPrmMap.put(LIST_NAME_DS_CPO_CONFIG, sqlPrmList);
        }

        // Get Column Name DS_CPO_SLS_CR_REC(CONF)
        List<Map<String, Object>> dsCpoSlsCrAttrList = getAttrList(bizMsg, "DS_CPO_SLS_CR_REC", LVL_CD_CONF);
        if (dsCpoSlsCrAttrList != null && dsCpoSlsCrAttrList.size() > 0) {
            List<String> sqlPrmList = new ArrayList<String>();
            sqlPrmList.add(setAttrDualSql(dsCpoSlsCrAttrList));
            sqlPrmList.add(setCaseWhenSql(dsCpoSlsCrAttrList, SQL_TABLE_ALIAS_NAME_BEFORE_01));
            sqlPrmList.add(setCaseWhenSql(dsCpoSlsCrAttrList, SQL_TABLE_ALIAS_NAME_AFTER_01));
            sqlPrmList.add(setCompSql(dsCpoSlsCrAttrList));
            sqlPrmMap.put(LIST_NAME_DS_CPO_SLS_CR, sqlPrmList);
        }

        // Line LEVEL
        // Get Column Name CPO_DTL_REC
        List<Map<String, Object>> cpoDtlAttrList = getAttrList(bizMsg, "CPO_DTL_REC", LVL_CD_LINE);
        if (cpoDtlAttrList != null && cpoDtlAttrList.size() > 0) {
            List<String> sqlPrmList = new ArrayList<String>();
            sqlPrmList.add(setAttrDualSql(cpoDtlAttrList));
            sqlPrmList.add(setCaseWhenSql(cpoDtlAttrList, SQL_TABLE_ALIAS_NAME_BEFORE_01));
            sqlPrmList.add(setCaseWhenSql(cpoDtlAttrList, SQL_TABLE_ALIAS_NAME_AFTER_01));
            sqlPrmList.add(setCompSql(cpoDtlAttrList));
            sqlPrmMap.put(LIST_NAME_CPO_DTL, sqlPrmList);
        }

        // Get Column Name DS_CPO_RTRN_DTL_REC
        List<Map<String, Object>> dsCpoRtrnDtlAttrList = getAttrList(bizMsg, "DS_CPO_RTRN_DTL_REC", LVL_CD_LINE);
        if (dsCpoRtrnDtlAttrList != null && dsCpoRtrnDtlAttrList.size() > 0) {
            List<String> sqlPrmList = new ArrayList<String>();
            sqlPrmList.add(setAttrDualSql(dsCpoRtrnDtlAttrList));
            sqlPrmList.add(setCaseWhenSql(dsCpoRtrnDtlAttrList, SQL_TABLE_ALIAS_NAME_BEFORE_01));
            sqlPrmList.add(setCaseWhenSql(dsCpoRtrnDtlAttrList, SQL_TABLE_ALIAS_NAME_AFTER_01));
            sqlPrmList.add(setCompSql(dsCpoRtrnDtlAttrList));
            sqlPrmMap.put(LIST_NAME_DS_CPO_RTRN_DTL, sqlPrmList);
        }

        // Get Column Name ORD_PRC_CALC_BASE_REC
        List<Map<String, Object>> ordPrcCalcBaseAttrList = getAttrList(bizMsg, "ORD_PRC_CALC_BASE_REC", LVL_CD_LINE);
        if (ordPrcCalcBaseAttrList != null && ordPrcCalcBaseAttrList.size() > 0) {
            List<String> sqlPrmList = new ArrayList<String>();
            sqlPrmList.add(setAttrDualSql(ordPrcCalcBaseAttrList));
            sqlPrmList.add(setCaseWhenSql(ordPrcCalcBaseAttrList, SQL_TABLE_ALIAS_NAME_BEFORE_01));
            sqlPrmList.add(setCaseWhenSql(ordPrcCalcBaseAttrList, SQL_TABLE_ALIAS_NAME_AFTER_01));
            sqlPrmList.add(setCompSql(ordPrcCalcBaseAttrList));
            sqlPrmMap.put(LIST_NAME_ORD_PRC_CALC_BASE, sqlPrmList);
        }

        // Get Column Name CPO_RTRN_CALC_BASE_REC
        List<Map<String, Object>> cpoRtrnCalcBaseAttrList = getAttrList(bizMsg, "CPO_RTRN_CALC_BASE_REC", LVL_CD_LINE);
        if (cpoRtrnCalcBaseAttrList != null && cpoRtrnCalcBaseAttrList.size() > 0) {
            List<String> sqlPrmList = new ArrayList<String>();
            sqlPrmList.add(setAttrDualSql(cpoRtrnCalcBaseAttrList));
            sqlPrmList.add(setCaseWhenSql(cpoRtrnCalcBaseAttrList, SQL_TABLE_ALIAS_NAME_BEFORE_01));
            sqlPrmList.add(setCaseWhenSql(cpoRtrnCalcBaseAttrList, SQL_TABLE_ALIAS_NAME_AFTER_01));
            sqlPrmList.add(setCompSql(cpoRtrnCalcBaseAttrList));
            sqlPrmMap.put(LIST_NAME_CPO_RTRN_CALC_BASE, sqlPrmList);
        }

        // Get Column Name DS_CONTR_REC
        List<Map<String, Object>> dsContrAttrList = getAttrList(bizMsg, "DS_CONTR_REC", LVL_CD_ALL);
        if (dsContrAttrList != null && dsContrAttrList.size() > 0) {
            List<String> sqlPrmList = new ArrayList<String>();
            sqlPrmList.add(setAttrDualSql(dsContrAttrList));
            sqlPrmList.add(setCaseWhenSql(dsContrAttrList, SQL_TABLE_ALIAS_NAME_BEFORE_01));
            sqlPrmList.add(setCaseWhenSql(dsContrAttrList, SQL_TABLE_ALIAS_NAME_AFTER_01));
            sqlPrmList.add(setCompSql(dsContrAttrList));
            sqlPrmMap.put(LIST_NAME_DS_CONTR, sqlPrmList);
        }

        // Get Column Name SVC_MEMO_REC
        List<Map<String, Object>> svcMemoAttrList = getAttrList(bizMsg, "SVC_MEMO_REC", LVL_CD_ALL);
        if (svcMemoAttrList != null && svcMemoAttrList.size() > 0) {
            List<String> sqlPrmList = new ArrayList<String>();
            sqlPrmList.add(setAttrDualSql(svcMemoAttrList));
            sqlPrmList.add(setCaseWhenSql(svcMemoAttrList, SQL_TABLE_ALIAS_NAME_BEFORE_01));
            sqlPrmList.add(setCaseWhenSql(svcMemoAttrList, SQL_TABLE_ALIAS_NAME_AFTER_01));
            sqlPrmList.add(setCompSql(svcMemoAttrList));
            sqlPrmMap.put(LIST_NAME_SVC_MEMO, sqlPrmList);
        }

        // Get Column Name DS_CONTR_DTL_REC
        List<Map<String, Object>> dsContrDtlAttrList = getAttrList(bizMsg, "DS_CONTR_DTL_REC", LVL_CD_ALL);
        if (dsContrDtlAttrList != null && dsContrDtlAttrList.size() > 0) {
            List<String> sqlPrmList = new ArrayList<String>();
            sqlPrmList.add(setAttrDualSql(dsContrDtlAttrList));
            sqlPrmList.add(setCaseWhenSql(dsContrDtlAttrList, SQL_TABLE_ALIAS_NAME_BEFORE_01));
            sqlPrmList.add(setCaseWhenSql(dsContrDtlAttrList, SQL_TABLE_ALIAS_NAME_AFTER_01));
            sqlPrmList.add(setCompSql(dsContrDtlAttrList));
            sqlPrmMap.put(LIST_NAME_DS_CONTR_DTL, sqlPrmList);
        }

        // Get Column Name DS_CONTR_ADDL_CHRG_REC
        List<Map<String, Object>> dsContrAddlChrgAttrList = getAttrList(bizMsg, "DS_CONTR_ADDL_CHRG_REC", LVL_CD_LINE);
        if (dsContrAddlChrgAttrList != null && dsContrAddlChrgAttrList.size() > 0) {
            List<String> sqlPrmList = new ArrayList<String>();
            sqlPrmList.add(setAttrDualSql(dsContrAddlChrgAttrList));
            sqlPrmList.add(setCaseWhenSql(dsContrAddlChrgAttrList, SQL_TABLE_ALIAS_NAME_BEFORE_01));
            sqlPrmList.add(setCaseWhenSql(dsContrAddlChrgAttrList, SQL_TABLE_ALIAS_NAME_AFTER_01));
            sqlPrmList.add(setCompSql(dsContrAddlChrgAttrList));
            sqlPrmMap.put(LIST_NAME_DS_CONTR_ADDL_CHRG, sqlPrmList);
        }

        List<String> configList = null;
        List<String> configOrdList = null;
        List<String> configRtnList = null;
        List<String> docIdConfigList = null;
        List<String> docIdLineList = null;

        if (LVL_CD_CONF.equals(bizMsg.noteLvlCd.getValue())) {

            configList = new ArrayList<String>();
            configOrdList = new ArrayList<String>();
            configRtnList = new ArrayList<String>();
            docIdConfigList = new ArrayList<String>();

            for (int i = 0; i < bizMsg.L.getValidCount(); i++) {
                configOrdList.add(bizMsg.L.no(i).dsOrdPosnNum_L0.getValue()); // QC#5283
                configList.add(bizMsg.L.no(i).dsOrdPosnNum_L0.getValue()); // QC#5283

                docIdConfigList.add(bizMsg.L.no(i).dsOrdPosnNum_L0.getValue());
            }

            for (int i = 0; i < bizMsg.R.getValidCount(); i++) {
                configRtnList.add(bizMsg.R.no(i).dsOrdPosnNum_R0.getValue()); // QC#5283
                configList.add(bizMsg.R.no(i).dsOrdPosnNum_R0.getValue()); // QC#5283

                docIdConfigList.add(bizMsg.R.no(i).dsOrdPosnNum_R0.getValue());
            }

        } else if (LVL_CD_LINE.equals(bizMsg.noteLvlCd.getValue())) {

            docIdLineList = new ArrayList<String>();

            // Order
            int index;
            for (index = 0; index < bizMsg.L.getValidCount();) {
                // QC#5283
                //                String stLine = bizMsg.L.no(index).xxLineNum_L0.getValue();
                //
                //                docIdLineList.add(stLine);
                //
                //                String[] lineValue = stLine.split(SPLIT_PERIOD);
                //
                //                if (lineValue.length == 1) {
                //                    ZYPEZDItemValueSetter.setValue(bizMsg.C.no(index).dsOrdPosnNum_C0, lineValue[0]);
                //                } else if (lineValue.length == 2) {
                //                    ZYPEZDItemValueSetter.setValue(bizMsg.C.no(index).dsOrdPosnNum_C0, lineValue[0]);
                //                    ZYPEZDItemValueSetter.setValue(bizMsg.C.no(index).xxLineNum_C0, lineValue[1]);
                //                } else if (lineValue.length == 3) {
                //                    ZYPEZDItemValueSetter.setValue(bizMsg.C.no(index).dsOrdPosnNum_C0, lineValue[0]);
                //                    ZYPEZDItemValueSetter.setValue(bizMsg.C.no(index).xxLineNum_C0, lineValue[1]);
                //                    ZYPEZDItemValueSetter.setValue(bizMsg.C.no(index).xxCpoLineSubNum_C0, lineValue[2]);
                //                }
                //--
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(index).dsOrdPosnNum_C0, bizMsg.L.no(index).dsOrdPosnNum_L0.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(index).xxLineNum_C0, bizMsg.L.no(index).cpoDtlLineNum_L0.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(index).xxCpoLineSubNum_C0, bizMsg.L.no(index).cpoDtlLineSubNum_L0.getValue());
                docIdLineList.add(//
                        S21StringUtil.concatStrings(//
                                PERCENT //
                                , bizMsg.L.no(index).cpoDtlLineNum_L0.getValue() //
                                , PERIOD //
                                , bizMsg.L.no(index).cpoDtlLineSubNum_L0.getValue()));
                // QC#5283
                index++;
            }
            bizMsg.C.setValidCount(index);

            // Return Order
            int indexRtn;
            for (indexRtn = 0; indexRtn < bizMsg.R.getValidCount();) {
                // QC#5283
                //                String stLine = bizMsg.R.no(indexRtn).xxLineNum_R0.getValue();
                //
                //                docIdLineList.add(stLine);
                //
                //                String[] lineValue = stLine.split(SPLIT_PERIOD);
                //
                //                if (lineValue.length == 1) {
                //                    ZYPEZDItemValueSetter.setValue(bizMsg.D.no(indexRtn).dsOrdPosnNum_D0, lineValue[0]);
                //                } else if (lineValue.length == 2) {
                //                    ZYPEZDItemValueSetter.setValue(bizMsg.D.no(indexRtn).dsOrdPosnNum_D0, lineValue[0]);
                //                    ZYPEZDItemValueSetter.setValue(bizMsg.D.no(indexRtn).xxLineNum_D0, lineValue[1]);
                //                } else if (lineValue.length == 3) {
                //                    ZYPEZDItemValueSetter.setValue(bizMsg.D.no(indexRtn).dsOrdPosnNum_D0, lineValue[0]);
                //                    ZYPEZDItemValueSetter.setValue(bizMsg.D.no(indexRtn).xxLineNum_D0, lineValue[1]);
                //                    ZYPEZDItemValueSetter.setValue(bizMsg.D.no(indexRtn).xxCpoLineSubNum_D0, lineValue[2]);
                //                }
                //--
                ZYPEZDItemValueSetter.setValue(bizMsg.D.no(indexRtn).dsOrdPosnNum_D0, bizMsg.R.no(indexRtn).dsOrdPosnNum_R0.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.D.no(indexRtn).xxLineNum_D0, bizMsg.R.no(indexRtn).dsCpoRtrnLineNum_R0.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.D.no(indexRtn).xxCpoLineSubNum_D0, bizMsg.R.no(indexRtn).dsCpoRtrnLineSubNum_R0.getValue());
                docIdLineList.add(//
                        S21StringUtil.concatStrings(//
                                PERCENT //
                                , bizMsg.R.no(indexRtn).dsCpoRtrnLineNum_R0.getValue() //
                                , PERIOD //
                                , bizMsg.R.no(indexRtn).dsCpoRtrnLineSubNum_R0.getValue()));
                // QC#5283
                indexRtn++;
            }
            bizMsg.D.setValidCount(indexRtn);
        }

        S21SsmEZDResult ssmResult //
        = NWAL1810Query.getInstance().getDetailOrderList(//
                bizMsg, glblMsg, configList, configOrdList, configRtnList, docIdConfigList, docIdLineList, sqlPrmMap);

        if (ssmResult.isCodeNormal()) {

            // Maximum acquisition number exceeded message
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > glblMsg.B.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
                queryResCnt = glblMsg.B.length();
            }

            // Search results posting
            int i = 0;
            for (; i < glblMsg.B.getValidCount(); i++) {
                if (i == bizMsg.B.length()) {
                    break;
                }
                EZDMsg.copy(glblMsg.B.no(i), null, bizMsg.B.no(i), null);
            }
            bizMsg.B.setValidCount(i);

            // Settings page item
            bizMsg.xxPageShowFromNum_B.setValue(1);
            bizMsg.xxPageShowToNum_B.setValue(bizMsg.B.getValidCount());
            bizMsg.xxPageShowOfNum_B.setValue(queryResCnt);

        } else {
            bizMsg.setMessageInfo(NWAM0006I);
            bizMsg.xxPageShowFromNum_B.clear();
            bizMsg.xxPageShowToNum_B.clear();
            bizMsg.xxPageShowOfNum_B.clear();
        }
    }

    /**
     * Search Detail Quote
     * @param bizMsg NWAL1810CMsg
     * @param glblMsg NWAL1810SMsg
     */
    private void searchDetailQuote(NWAL1810CMsg bizMsg, NWAL1810SMsg glblMsg) {

        Map<String, List<String>> sqlPrmMap = new HashMap<String, List<String>>();

        // ALL(Header) LEVEL
        // Get Column Name SPLY_QUOTE_REC
        List<Map<String, Object>> splyQuoteAttrList //
        = getAttrList(bizMsg, DB_NAME_SPLY_QUOTE_REC, LVL_CD_ALL);
        if (splyQuoteAttrList != null && splyQuoteAttrList.size() > 0) {
            List<String> sqlPrmList = new ArrayList<String>();
            sqlPrmList.add(setAttrDualSql(splyQuoteAttrList));
            sqlPrmList.add(setCaseWhenSql(splyQuoteAttrList, SQL_TABLE_ALIAS_NAME_BEFORE_01));
            sqlPrmList.add(setCaseWhenSql(splyQuoteAttrList, SQL_TABLE_ALIAS_NAME_AFTER_01));
            sqlPrmList.add(setCompSql(splyQuoteAttrList));
            sqlPrmMap.put(LIST_NAME_SPLY_QUOTE, sqlPrmList);
        }

        // Get Column Name SPLY_QUOTE_SLS_CR_REC
        List<Map<String, Object>> splyQuoteSlsCrAttrList //
        = getAttrList(bizMsg, DB_NAME_SPLY_QUOTE_SLS_CR_REC, LVL_CD_ALL);
        if (splyQuoteSlsCrAttrList != null && splyQuoteSlsCrAttrList.size() > 0) {
            List<String> sqlPrmList = new ArrayList<String>();
            sqlPrmList.add(setAttrDualSql(splyQuoteSlsCrAttrList));
            sqlPrmList.add(setCaseWhenSql(splyQuoteSlsCrAttrList, SQL_TABLE_ALIAS_NAME_BEFORE_01));
            sqlPrmList.add(setCaseWhenSql(splyQuoteSlsCrAttrList, SQL_TABLE_ALIAS_NAME_AFTER_01));
            sqlPrmList.add(setCompSql(splyQuoteSlsCrAttrList));
            sqlPrmMap.put(LIST_NAME_SPLY_QUOTE_SLS_CR, sqlPrmList);
        }

        // Line(Configuration) LEVEL
        // Get Column Name SPLY_QUOTE_DTL_REC
        List<Map<String, Object>> splyQuoteDtlAttrList //
        = getAttrList(bizMsg, DB_NAME_SPLY_QUOTE_DTL_REC, LVL_CD_LINE);
        if (splyQuoteDtlAttrList != null && splyQuoteDtlAttrList.size() > 0) {
            List<String> sqlPrmList = new ArrayList<String>();
            sqlPrmList.add(setAttrDualSql(splyQuoteDtlAttrList));
            sqlPrmList.add(setCaseWhenSql(splyQuoteDtlAttrList, SQL_TABLE_ALIAS_NAME_BEFORE_01));
            sqlPrmList.add(setCaseWhenSql(splyQuoteDtlAttrList, SQL_TABLE_ALIAS_NAME_AFTER_01));
            sqlPrmList.add(setCompSql(splyQuoteDtlAttrList));
            sqlPrmMap.put(LIST_NAME_SPLY_QUOTE_DTL, sqlPrmList);
        }

        // Get Column Name SPLY_QUOTE_CALC_BASE_REC
        List<Map<String, Object>> splyQuoteCalcBaseAttrList //
        = getAttrList(bizMsg, DB_NAME_SPLY_QUOTE_CALC_BASE_REC, LVL_CD_LINE);
        if (splyQuoteCalcBaseAttrList != null && splyQuoteCalcBaseAttrList.size() > 0) {
            List<String> sqlPrmList = new ArrayList<String>();
            sqlPrmList.add(setAttrDualSql(splyQuoteCalcBaseAttrList));
            sqlPrmList.add(setCaseWhenSql(splyQuoteCalcBaseAttrList, SQL_TABLE_ALIAS_NAME_BEFORE_01));
            sqlPrmList.add(setCaseWhenSql(splyQuoteCalcBaseAttrList, SQL_TABLE_ALIAS_NAME_AFTER_01));
            sqlPrmList.add(setCompSql(splyQuoteCalcBaseAttrList));
            sqlPrmMap.put(LIST_NAME_SPLY_QUOTE_CALC_BASE, sqlPrmList);
        }

        List<String> configList = null;
        List<String> docIdConfigList = null;
        List<String> docIdLineList = null;

        //        if (LVL_CD_CONF.equals(bizMsg.noteLvlCd.getValue())) {
        //
        //            configList = new ArrayList<String>();
        //            docIdConfigList = new ArrayList<String>();
        //
        //            for (int i = 0; i < bizMsg.L.getValidCount(); i++) {
        //                configList.add(bizMsg.L.no(i).dsOrdPosnNum_L0.getValue()); // QC#5283
        //
        //                StringBuilder paramLike = new StringBuilder();
        //                paramLike.append(bizMsg.L.no(i).dsOrdPosnNum_L0.getValue()).append(PERCENT); // QC#5283
        //                docIdConfigList.add(paramLike.toString());
        //            }
        //
        //            for (int i = 0; i < bizMsg.R.getValidCount(); i++) {
        //                configList.add(bizMsg.R.no(i).dsOrdPosnNum_R0.getValue()); // QC#5283
        //
        //                StringBuilder paramLike = new StringBuilder();
        //                paramLike.append(bizMsg.R.no(i).dsOrdPosnNum_R0.getValue()).append(PERCENT); // QC#5283
        //                docIdConfigList.add(paramLike.toString());
        //            }
        //
        //        } else if (LVL_CD_LINE.equals(bizMsg.noteLvlCd.getValue())) {
        //
        //            docIdLineList = new ArrayList<String>();
        //
        //            for (int i = 0; i < bizMsg.L.getValidCount(); i++) {
        //                docIdLineList.add(bizMsg.L.no(i).dsOrdPosnNum_L0.getValue()); // QC#5283
        //            }
        //
        //            for (int i = 0; i < bizMsg.R.getValidCount(); i++) {
        //                docIdLineList.add(bizMsg.R.no(i).dsOrdPosnNum_R0.getValue()); // QC#5283
        //            }
        //        }

        S21SsmEZDResult ssmResult //
        = NWAL1810Query.getInstance().getDetailQuoteList(bizMsg, glblMsg, configList, docIdConfigList, docIdLineList, sqlPrmMap);

        if (ssmResult.isCodeNormal()) {

            // Maximum acquisition number exceeded message
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > glblMsg.B.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
                queryResCnt = glblMsg.B.length();
            }

            // Search results posting
            int i = 0;
            for (; i < glblMsg.B.getValidCount(); i++) {
                if (i == bizMsg.B.length()) {
                    break;
                }
                EZDMsg.copy(glblMsg.B.no(i), null, bizMsg.B.no(i), null);
            }
            bizMsg.B.setValidCount(i);

            // Settings page item
            bizMsg.xxPageShowFromNum_B.setValue(1);
            bizMsg.xxPageShowToNum_B.setValue(bizMsg.B.getValidCount());
            bizMsg.xxPageShowOfNum_B.setValue(queryResCnt);

        } else {
            bizMsg.setMessageInfo(NWAM0006I);
            bizMsg.xxPageShowFromNum_B.clear();
            bizMsg.xxPageShowToNum_B.clear();
            bizMsg.xxPageShowOfNum_B.clear();
        }
    }

    /**
     * Search Detail Schedule
     * @param bizMsg NWAL1810CMsg
     * @param glblMsg NWAL1810SMsg
     */
    private void searchDetailSchedule(NWAL1810CMsg bizMsg, NWAL1810SMsg glblMsg) {

        Map<String, List<String>> splPrmMap = new HashMap<String, List<String>>();

        // ALL(Header) LEVEL
        // Get Column Name SCHD_AGMT_REC
        List<Map<String, Object>> schdAgmtAttrList = getAttrList(bizMsg, "SCHD_AGMT_REC", LVL_CD_ALL);
        if (schdAgmtAttrList != null && schdAgmtAttrList.size() > 0) {
            List<String> sqlPrmList = new ArrayList<String>();
            sqlPrmList.add(setAttrDualSql(schdAgmtAttrList));
            sqlPrmList.add(setCaseWhenSql(schdAgmtAttrList, SQL_TABLE_ALIAS_NAME_BEFORE_01));
            sqlPrmList.add(setCaseWhenSql(schdAgmtAttrList, SQL_TABLE_ALIAS_NAME_AFTER_01));
            sqlPrmList.add(setCompSql(schdAgmtAttrList));
            splPrmMap.put(LIST_NAME_SCHD_AGMT, sqlPrmList);
        }

        // Get Column Name SCHD_AGMT_SLS_CR_REC
        List<Map<String, Object>> schdAgmtSlsCrAttrList = getAttrList(bizMsg, "SCHD_AGMT_SLS_CR_REC", LVL_CD_ALL);
        if (schdAgmtSlsCrAttrList != null && schdAgmtSlsCrAttrList.size() > 0) {
            List<String> sqlPrmList = new ArrayList<String>();
            sqlPrmList.add(setAttrDualSql(schdAgmtSlsCrAttrList));
            sqlPrmList.add(setCaseWhenSql(schdAgmtSlsCrAttrList, SQL_TABLE_ALIAS_NAME_BEFORE_01));
            sqlPrmList.add(setCaseWhenSql(schdAgmtSlsCrAttrList, SQL_TABLE_ALIAS_NAME_AFTER_01));
            sqlPrmList.add(setCompSql(schdAgmtSlsCrAttrList));
            splPrmMap.put(LIST_NAME_SCHD_AGMT_SLS_CR, sqlPrmList);
        }

        // Get Column Name SCHD_AGMT_LINE_REC
        List<Map<String, Object>> schdAgmtLineAttrList = getAttrList(bizMsg, "SCHD_AGMT_LINE_REC", LVL_CD_CONF);
        if (schdAgmtLineAttrList != null && schdAgmtLineAttrList.size() > 0) {
            List<String> sqlPrmList = new ArrayList<String>();
            sqlPrmList.add(setAttrDualSql(schdAgmtLineAttrList));
            sqlPrmList.add(setCaseWhenSql(schdAgmtLineAttrList, SQL_TABLE_ALIAS_NAME_BEFORE_01));
            sqlPrmList.add(setCaseWhenSql(schdAgmtLineAttrList, SQL_TABLE_ALIAS_NAME_AFTER_01));
            sqlPrmList.add(setCompSql(schdAgmtLineAttrList));
            splPrmMap.put(LIST_NAME_SCHD_AGMT_LINE, sqlPrmList);
        }

        // Get Column Name SCHD_AGMT_CALC_BASE_REC
        List<Map<String, Object>> schdAgmtPrcCalcAttrList = getAttrList(bizMsg, "SCHD_AGMT_CALC_BASE_REC", LVL_CD_CONF);
        if (schdAgmtPrcCalcAttrList != null && schdAgmtPrcCalcAttrList.size() > 0) {
            List<String> sqlPrmList = new ArrayList<String>();
            sqlPrmList.add(setAttrDualSql(schdAgmtPrcCalcAttrList));
            sqlPrmList.add(setCaseWhenSql(schdAgmtPrcCalcAttrList, SQL_TABLE_ALIAS_NAME_BEFORE_01));
            sqlPrmList.add(setCaseWhenSql(schdAgmtPrcCalcAttrList, SQL_TABLE_ALIAS_NAME_AFTER_01));
            sqlPrmList.add(setCompSql(schdAgmtPrcCalcAttrList));
            splPrmMap.put(LIST_NAME_SCHD_AGMT_PRC_CALC, sqlPrmList);
        }

        // Get Column Name SCHD_AGMT_PLN_REC
        List<Map<String, Object>> schdAgmtPlnAttrList = getAttrList(bizMsg, "SCHD_AGMT_PLN_REC", LVL_CD_LINE);
        if (schdAgmtPlnAttrList != null && schdAgmtPlnAttrList.size() > 0) {
            List<String> sqlPrmList = new ArrayList<String>();
            sqlPrmList.add(setAttrDualSql(schdAgmtPlnAttrList));
            sqlPrmList.add(setCaseWhenSql(schdAgmtPlnAttrList, SQL_TABLE_ALIAS_NAME_BEFORE_01));
            sqlPrmList.add(setCaseWhenSql(schdAgmtPlnAttrList, SQL_TABLE_ALIAS_NAME_AFTER_01));
            sqlPrmList.add(setCompSql(schdAgmtPlnAttrList));
            splPrmMap.put(LIST_NAME_SCHD_AGMT_PLN, sqlPrmList);
        }

        List<String> configList = null;
        List<String> docIdConfigList = null;
        List<String> docIdLineList = null;

        //        if (LVL_CD_CONF.equals(bizMsg.noteLvlCd.getValue())) {
        //
        //            configList = new ArrayList<String>();
        //            docIdConfigList = new ArrayList<String>();
        //
        //            for (int i = 0; i < bizMsg.L.getValidCount(); i++) {
        //                configList.add(bizMsg.L.no(i).dsOrdPosnNum_L0.getValue()); // QC#5283
        //
        //                StringBuilder paramLike = new StringBuilder();
        //                paramLike.append(bizMsg.L.no(i).dsOrdPosnNum_L0.getValue()).append(PERCENT); // QC#5283
        //                docIdConfigList.add(paramLike.toString());
        //            }
        //
        //            for (int i = 0; i < bizMsg.R.getValidCount(); i++) {
        //                configList.add(bizMsg.R.no(i).dsOrdPosnNum_R0.getValue()); // QC#5283
        //
        //                StringBuilder paramLike = new StringBuilder();
        //                paramLike.append(bizMsg.R.no(i).dsOrdPosnNum_R0.getValue()).append(PERCENT); // QC#5283
        //                docIdConfigList.add(paramLike.toString());
        //            }
        //
        //        } else if (LVL_CD_LINE.equals(bizMsg.noteLvlCd.getValue())) {
        //
        //            docIdLineList = new ArrayList<String>();
        //
        //            for (int i = 0; i < bizMsg.L.getValidCount(); i++) {
        //                docIdLineList.add(bizMsg.L.no(i).dsOrdPosnNum_L0.getValue()); // QC#5283
        //            }
        //
        //            for (int i = 0; i < bizMsg.R.getValidCount(); i++) {
        //                docIdLineList.add(bizMsg.R.no(i).dsOrdPosnNum_R0.getValue()); // QC#5283
        //            }
        //        }

        S21SsmEZDResult ssmResult = NWAL1810Query.getInstance().getDetailScheduleList(bizMsg, glblMsg, configList, docIdConfigList, docIdLineList, splPrmMap);

        if (ssmResult.isCodeNormal()) {

            // Maximum acquisition number exceeded message
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > glblMsg.B.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
                queryResCnt = glblMsg.B.length();
            }

            // Search results posting
            int i = 0;
            for (; i < glblMsg.B.getValidCount(); i++) {
                if (i == bizMsg.B.length()) {
                    break;
                }
                EZDMsg.copy(glblMsg.B.no(i), null, bizMsg.B.no(i), null);
            }
            bizMsg.B.setValidCount(i);

            // Settings page item
            bizMsg.xxPageShowFromNum_B.setValue(1);
            bizMsg.xxPageShowToNum_B.setValue(bizMsg.B.getValidCount());
            bizMsg.xxPageShowOfNum_B.setValue(queryResCnt);

        } else {
            bizMsg.setMessageInfo(NWAM0006I);
            bizMsg.xxPageShowFromNum_B.clear();
            bizMsg.xxPageShowToNum_B.clear();
            bizMsg.xxPageShowOfNum_B.clear();
        }
    }

    //2022/10/20 QC#60258 Add Start
    private void setUpEventList(NWAL1810CMsg bizMsg, NWAL1810SMsg glblMsg){
        S21SsmEZDResult eventListResult = NWAL1810Query.getInstance().getChangeEventList(bizMsg, glblMsg);
        List<Map<String, String>> resultMap =(List<Map<String, String>>)eventListResult.getResultObject();
        for(int i = 0; i < resultMap.size(); i++){
            ZYPEZDItemValueSetter.setValue(bizMsg.eventNm_PL.no(i), resultMap.get(i).get("EVENT_NM"));
        }
    }
    //2022/10/20 QC#60258 Add End
}
