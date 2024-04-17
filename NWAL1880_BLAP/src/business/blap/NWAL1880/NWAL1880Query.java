/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1880;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCStringItem;
import parts.common.EZDMsg;

import business.blap.NWAL1880.constant.NWAL1880Constant.SQL_COL;
import business.blap.NWAL1880.constant.NWAL1880Constant.SEARCH_STATUS;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_REBIL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_TEAM_ATTRB_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.common.S21SsmEZDOracleJDBCUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmVoidResultSetHandlerSupport;


import static business.blap.NWAL1880.constant.NWAL1880Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.concatString;
import static parts.common.EZDCommonFunc.trimTail;


/**
 *<pre>
 * NWAL1880Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/26   Fujitsu         K.Sato          Create          N/A
 * 2016/07/27   Fujitsu         K.Sato          Update          QC#11581
 * 2017/01/25   Fujitsu         W.Honda         Update          QC#16992
 * 2017/08/01   Hitachi         Y.Takeno        Update          QC#20098
 * 2018/01/15   Fujitsu         A.Kosai         Update          QC#22330
 *</pre>
 */
public final class NWAL1880Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NWAL1880Query MY_INSTANCE = new NWAL1880Query();

    /**
     * Private constructor
     */
    private NWAL1880Query() {
        super();
    }

    /**
     * Get the NWAL1880Query instance.
     * @return NWAL1880Query instance
     */
    public static NWAL1880Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getOrdTeamZoneByUser
     * @param bizMsg NWAL1880CMsg
     * @param userId String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrdTeamZoneByUser(NWAL1880CMsg bizMsg, String userId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("slsDt", bizMsg.slsDt.getValue());
        params.put("attrbTpCdUser", ORD_TEAM_ATTRB_TP.USER_ID);
        params.put("userId", userId);

        return getSsmEZDClient().queryObjectList("findOrderTeamZoneByUserId", params);
    }

    /**
     * find order list
     *
     * <pre>
     * statementId = "findOrderList"
     * </pre>
     *
     * @param cMsg NWAL1880CMsg
     * @param sMsg NWAL1880SMsg
     * @param maxSize use as sql rownum value.
     */
    public void findOrderList(final NWAL1880CMsg cMsg, final NWAL1880SMsg sMsg, final int maxSize) {
        S21InfoLogOutput.println("=== NWAL1880Query.setFindOrderList : start ===");

        final Map<String, Serializable> param = createSsmParam(cMsg, maxSize);

        if ("E".equals(cMsg.getMessageKind())) {
            return;
        }

        S21SsmExecutionParameter rsParam = new S21SsmExecutionParameter();
        rsParam.setFetchSize(FETCH_SIZE_MAX);
        rsParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        rsParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        rsParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        getSsmBatchClient().queryObject("findOrderList", param, rsParam, new ScreenResultSetHandler(cMsg, sMsg));

        S21InfoLogOutput.println("=== NWAL1880Query.setFindOrderList : end ===");
    }

    private S21SsmBatchClient getSsmBatchClient() {
        return S21SsmBatchClient.getClient(this.getClass());
    }

    private Map<String, Serializable> createSsmParam(final NWAL1880CMsg cMsg, final int maxSize) {

        final Map<String, Serializable> param = new HashMap<String, Serializable>();

        param.put("glblCmpyCd", getGlobalCompanyCode());
        param.put("cMsg", cMsg);
        param.put("rowNum", maxSize + 1);

        if (hasValue(cMsg.pairDplyByItemNm)) {
            param.put("pairDplyByItemNm_01", cMsg.pairDplyByItemNm.getValue());
        }

        // ------------------------
        // ** Constant Params
        // ------------------------
        setConstantParam(param);

        // --------------------------
        // ** Create Search Condition
        // --------------------------
        setSearchCondition(cMsg, param);

        return param;
    }

    private void setConstantParam(final Map<String, Serializable> param) {

        // header status
        param.put("stsPendImpt", STS_NM_PEND_IMPT);
        param.put("stsEnt", HDR_STS.ENT.getHdrStsNm());
        param.put("stsCrHld", HDR_STS.CR_HLD.getHdrStsNm());
        param.put("stsPrft", HDR_STS.PRFT.getHdrStsNm());
        param.put("stsVld", HDR_STS.VLD.getHdrStsNm());
        param.put("stsCanc", HDR_STS.CANC.getHdrStsNm());

        // line status
        param.put("stsBook", LINE_STS.BOOK.getLineStsNm());
        param.put("stsBllgHld", LINE_STS.BLLG_HLD.getLineStsNm());
        // QC#11581 MOD Start
//        param.put("stsInv", LINE_STS.INV.getLineStsNm());
        param.put("stsClo", RTRN_LINE_STS.CLO.getRtrnLineStsNm());
        param.put("stsCloLoanRtrn", LINE_STS.CLO_LOAN_RTRN.getLineStsNm());
        param.put("stsCloLoanSold", LINE_STS.CLO_LOAN_SOLD.getLineStsNm());
        // QC#11581 MOD End

        // Source Type
        // 2018/01/15 S21_NA#22330 Mod Start
        //param.put("cpoSrcTpCrReBill", CPO_SRC_TP.CREDIT_AND_REBILL_ENTRY);
        param.put("cpoSrcTpCr", CPO_SRC_TP.CREDIT);
        param.put("cpoSrcTpRb", CPO_SRC_TP.REBILL);
        // 2018/01/15 S21_NA#22330 Mod End
        param.put("cpoSrcTpSom", CPO_SRC_TP.SOM);
        param.put("cpoSrcTpDealConfig", CPO_SRC_TP.DEAL_CONFIGURATOR);
        param.put("crRebillRebill", CR_REBIL.REBILL);

        // Aging
        param.put("agingToday", ORD_AGING_BCKT_DESC_TXT_TODAY);
        param.put("agingFrom1To2", ORD_AGING_BCKT_DESC_TXT_1_2);
        param.put("agingFrom3To10", ORD_AGING_BCKT_DESC_TXT_3_10);
        param.put("agingFrom11To30", ORD_AGING_BCKT_DESC_TXT_11_30);
        param.put("agingFrom31To50", ORD_AGING_BCKT_DESC_TXT_31_50);
        param.put("agingOver50", ORD_AGING_BCKT_DESC_TXT_OVER_50);

        param.put("attrbTpCdUser", ORD_TEAM_ATTRB_TP.USER_ID);
        param.put("attrbTpCdCustomer", ORD_TEAM_ATTRB_TP.CUSTOMER_NUMBER);
        param.put("attrbTpCdLob", ORD_TEAM_ATTRB_TP.LINE_OF_BUSINESS);
        param.put("attrbTpCdBranch", ORD_TEAM_ATTRB_TP.BRANCH);
    }

    private void setSearchCondition(final NWAL1880CMsg cMsg, final Map<String, Serializable> param) {

        String[][] orderCriteria = null;
        orderCriteria = createOrderCriteria(cMsg);

        param.put("slsDt", cMsg.slsDt.getValue());
        param.put("slsDtYM", S21StringUtil.concatStrings(S21StringUtil.subStringByLength(cMsg.slsDt.getValue(), 0, LENGTH_YYYYMM), PERCENT));

        if (DISP_BY_ALL_CSA.equals(cMsg.xxScrItem50Txt.getValue())) {
            param.put("allCsaFlg", Y);
            param.put("allCsa", DISP_BY_ALL_CSA);
            param.put("teamSearchFlg", N);
            param.put("dispByTeamZnFlg", N);
        } else {
            param.put("allCsaFlg", N);

            if (DISP_BY_CRAT_BY_USR_ID.equals(cMsg.xxScrItem50Txt.getValue())) {
                param.put("searchCondition_OpenOrder", createSearchCondition(cMsg, param, SEARCH_STATUS.OPEN_ORD, orderCriteria));
                param.put("searchCondition_CloseOrder", createSearchCondition(cMsg, param, SEARCH_STATUS.CLO_ORD, orderCriteria));
                // START 2017/08/01 [QC#20098, ADD]
                param.put("dispByCratUsrId", Y);
                // END   2017/08/01 [QC#20098, ADD]
            }

            if (!DISP_BY_ZONE_NM.equals(cMsg.xxScrItem50Txt.getValue()) && hasValue(cMsg.xxOrdTeamSrchTxt)) {
//                param.put("ordTeamSrchTxt", cMsg.xxOrdTeamSrchTxt.getValue());
                String[][] orderCriteriaTeam = null;
                orderCriteriaTeam = createOrderCriteriaTeam(cMsg);
                param.put("searchCondition_Team", createSearchCondition(cMsg, param, SEARCH_STATUS.TEAM, orderCriteriaTeam));
            }
            if (hasValue(cMsg.xxOrdZnSrchTxt)) {
//                param.put("ordZnSrchTxt", cMsg.xxOrdZnSrchTxt.getValue());
                String[][] orderCriteriaZone = null;
                orderCriteriaZone = createOrderCriteriaZone(cMsg);
                param.put("searchCondition_Zone", createSearchCondition(cMsg, param, SEARCH_STATUS.ZONE, orderCriteriaZone));
            }

            if (DISP_BY_TEAM_NM.equals(cMsg.xxScrItem50Txt.getValue())
                    || DISP_BY_ZONE_NM.equals(cMsg.xxScrItem50Txt.getValue())
                    || hasValue(cMsg.xxOrdTeamSrchTxt)
                    || hasValue(cMsg.xxOrdZnSrchTxt)) {
                param.put("teamSearchFlg", Y);
            }

        }

   }

    private String[][] createOrderCriteria(final NWAL1880CMsg cMsg) {

        String[][] orderCriteria = new String[][] {

                // Order Processor
                {SEARCH_COLUMN.CREATED_BY.name()              , Y       , getValue(cMsg.xxCratByUsrSrchTxt)          , N, N},

        };

        return orderCriteria;
    }

    private String[][] createOrderCriteriaTeam(final NWAL1880CMsg cMsg) {

        String[][] orderCriteria = new String[][] {

                // Order Team
                {SEARCH_COLUMN.TEAM.name()              , Y       , getValue(cMsg.xxOrdTeamSrchTxt)          , N, N},

        };

        return orderCriteria;
    }

    private String[][] createOrderCriteriaZone(final NWAL1880CMsg cMsg) {

        String[][] orderCriteria = new String[][] {

                // Order Zone
                {SEARCH_COLUMN.ZONE.name()              , Y       , getValue(cMsg.xxOrdZnSrchTxt)          , N, N},

        };

        return orderCriteria;
    }

    private String getValue(EZDCStringItem item) {
        String value = item.getValue();
        return value.replaceAll("'", "''");
    }

    private String createSearchCondition(final NWAL1880CMsg cMsg, final Map<String, Serializable> param, final SEARCH_STATUS searchStatus, final String[][] orderCriteriaList) {

        setSearchStatus(param, searchStatus);

        if (N.equals((String) param.get(searchStatus.getValue()))) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        for (String[] orderCriteria : orderCriteriaList) {
            if (orderCriteria[0].endsWith("_DATE")) {
                append(sb, createSearchConditionByDate(param, searchStatus, orderCriteria[0], orderCriteria[2], orderCriteria[3]));
            } else {
                append(sb, createSearchCondition(param, searchStatus, orderCriteria[0], orderCriteria[1], orderCriteria[2], Y.equals(orderCriteria[3]), cMsg.xxSplCharTxt.getValue(), Y.equals(orderCriteria[4])));
            }

            if (N.equals((String) param.get(searchStatus.getValue()))) {
                break;
            }
        }

        if (N.equals((String) param.get(searchStatus.getValue()))) {
            return "";
        }

        append(sb, createOption(cMsg, param, searchStatus));

        if (N.equals((String) param.get(searchStatus.getValue()))) {
            return "";
        }

        return sb.toString();
    }

    private void setSearchStatus(final Map<String, Serializable> param, final SEARCH_STATUS searchStatus) {

        String searchStatusFlg = Y;

        param.put(searchStatus.getValue(), searchStatusFlg);
    }

    private String createSearchCondition(Map<String, Serializable> param, SEARCH_STATUS searchStatus, String searchColumn, String inclFlg, String searchCondition, boolean isEqualFlg, String splCharTxt, boolean inclIsNullFlg) {

        if (!hasValue(searchCondition)) {
            return "";
        }

        String column = getSearchColumn(searchStatus, searchColumn);

        if (SEARCH_COLUMN.RESULT_NOT_FOUND.name().equals(column)) {
            param.put(searchStatus.getValue(), N);
            return "";
        }

        if (!hasValue(column)) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        if (CONDITION_IS_NULL.equals(searchCondition)) {
            append(sb, " AND ");
            append(sb, column);
            append(sb, " IS NULL");
        } else {
            String mark = EQUQL;
            String[] srchTxtArray = getSrchTxt(trimTail(searchCondition), splCharTxt);

            if (!isEqualFlg) {
                mark = "LIKE";
            }

            if (Y.equals(inclFlg)) {
                if (srchTxtArray == null) {
                    if (inclIsNullFlg) {
                        append(sb, " AND (", column, " ", mark, " '", searchCondition, "'");
                        append(sb, " OR ");
                        append(sb, column, " ", "IS NULL");
                        append(sb, ")");
                    } else {
                        append(sb, " AND ", column, " ", mark, " '", searchCondition, "'");
                    }
                } else {
                    if (isEqualFlg && !inclIsNullFlg) {
                        append(sb, " AND ", column, " IN (");
                        addConditions(sb, srchTxtArray, splCharTxt);
                        append(sb, ")");
                    } else {
                        append(sb, " AND (");
                        for (int i = 0; i < srchTxtArray.length; i++) {
                            if (i > 0) {
                                append(sb, " OR ");
                            }
                            append(sb, column, " ", mark, " '", srchTxtArray[i], "'");
                        }
                        if (inclIsNullFlg) {
                            append(sb, " OR ");
                            append(sb, column, " ", "IS NULL");
                        }
                        append(sb, ")");
                    }
                }
            } else {
                if (srchTxtArray == null) {
                    if (isEqualFlg) {
                        append(sb, " AND NVL(", column, ",'XYZ') != '", searchCondition, "'");
                    } else {
                        append(sb, " AND NVL(", column, ",'XYZ') NOT LIKE '", searchCondition, "'");
                    }
                } else {
                    if (isEqualFlg) {
                        append(sb, " AND NVL(", column, ",'XYZ') NOT IN (");
                        addConditions(sb, srchTxtArray, splCharTxt);
                        append(sb, ")");
                    } else {
                        append(sb, " AND ((");

                        for (int i = 0; i < srchTxtArray.length; i++) {
                            if (i > 0) {
                                append(sb, " AND");
                            }
                            append(sb, " ", column, " NOT ", mark, " '", srchTxtArray[i], "'");
                        }

                        append(sb, ") OR ", column, " IS NULL)");
                    }
                }
            }
        }
        return sb.toString();
    }

    private String createSearchConditionByDate(Map<String, Serializable> param, SEARCH_STATUS searchStatus, String searchColumn, String searchConditionFrom, String searchConditionTo) {

        if (!hasValue(searchConditionFrom) && !hasValue(searchConditionTo)) {
            return "";
        }

        String column = getSearchColumn(searchStatus, searchColumn);

        if (SEARCH_COLUMN.RESULT_NOT_FOUND.name().equals(column)) {
            param.put(searchStatus.getValue(), N);
            return "";
        }

        if (!hasValue(column)) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        if (CONDITION_IS_NULL_DATE.equals(searchConditionFrom)) {
            append(sb, " AND ");
            append(sb, column);
            append(sb, " IS NULL");
        } else {
            if (hasValue(searchConditionFrom) && hasValue(searchConditionTo)) {
                append(sb, " AND ", column, " BETWEEN '", searchConditionFrom, "' AND '", searchConditionTo, "'");
            } else if (hasValue(searchConditionFrom)) {
                append(sb, " AND ", column, " >= '", searchConditionFrom, "'");
            } else {
                append(sb, " AND ", column, " <= '", searchConditionTo, "'");
            }
        }

        return sb.toString();
    }

    private String createOption(final NWAL1880CMsg cMsg, final Map<String, Serializable> param, final SEARCH_STATUS searchStatus) {

        StringBuilder sb = new StringBuilder();

//        // Inquiry Mode
//        if (RSLT_MODE.STATUS_SUMMARY.getRsltModeCd().equals(cMsg.xxRsltModeCd.getValue())) {
//
//            if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
//                    cMsg.slsDt.getValue();
//                    append(sb, " AND DCO.ACTL_SHIP_DT LIKE '", ZYPCommonFunc.subByteString(cMsg.slsDt.getValue(), 0, 6), "' || '%'");
//            }
//        }

        return sb.toString();
    }

    private String getSearchColumn(final SEARCH_STATUS searchStatus, final String searchColumn) {

        String column = "";

        // Header Search Criteria
        if (SEARCH_COLUMN.CREATED_BY.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO.CRAT_BY_USR_ID";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.CRAT_BY_USR_ID";
            }
        } else if (SEARCH_COLUMN.TEAM.name().equals(searchColumn)) {
            column = "OTMH.ORD_TEAM_MSTR_NM";
        } else if (SEARCH_COLUMN.ZONE.name().equals(searchColumn)) {
            column = "OZ.ORD_ZN_DESC_TXT";
        }

        return column;
    }

    /**
     * get array from search text if it has splCharTxt in text field.
     *
     * <pre>
     * statementId = "getSrchTxt"
     * </pre>
     *
     * @param String item
     * @return String[]
     */
    private String[] getSrchTxt(String item, String splCharTxt) {

        if (item != null && item.length() > 0) {
            if (item.indexOf(splCharTxt) != -1) {
                String[] itemArray = item.split(splCharTxt);
                boolean isExists = false;
                for (int i = 0; i < itemArray.length; i++) {
                    if (!itemArray[i].trim().equals("") && itemArray[i].length() > 0) {
                        isExists = true;
                        break;
                    }
                }
                if (isExists == true) {
                    return itemArray;
                }
            }
        }
        return null;
    }

   private void append(StringBuilder sb, String... strs) {
        for (String str : strs) {
            sb.append(str);
        }
    }

    private void addConditions(StringBuilder sb, String[] srchTxtArray, String splCharTxt) {
        for (int i = 0; i < srchTxtArray.length; i++) {
            if (i > 0) {
                append(sb, COMMA);
            }
            append(sb, "'", srchTxtArray[i], "'");
        }
    }

    /**
     * ScreenResultSetHandler
     *
     * <pre>
     * ScreenResultSetHandler
     * </pre>
     * @param cMsg NWAL1880CMsg
     */
    private class ScreenResultSetHandler extends CommonResultSetHandler {

        /**
         * NWAL1880CMsg
         */
        private NWAL1880CMsg cMsg = null;

        /**
         * NWAL1880SMsg
         */
        private NWAL1880SMsg sMsg = null;

        public ScreenResultSetHandler(NWAL1880CMsg cMsg, NWAL1880SMsg sMsg) {
            this.cMsg = cMsg;
            this.sMsg = sMsg;
        }

        @Override
        protected void doProcessQueryResult(ResultSet result) throws SQLException {
            S21InfoLogOutput.println("=== NWAL1880Query.ScreenResultSetHandler.doProcessQueryResult : start ===");

            if (!result.next()) {
                cMsg.setMessageInfo("NZZM0000E");
                return;
            }

            ZYPTableUtil.clear(sMsg.A);

            List<String> column = getColumnList(result);

            // data copy to FMsg.
            int index = 0;
            Map<String, BigDecimal> groupingMap = new HashMap<String, BigDecimal>();

            boolean prevFlag = false;
            while (true) {

                if (index >= sMsg.A.length()) {
                    cMsg.setMessageInfo("NZZM0001W");
                    break;
                }

                Map<String, Object> record = getResultMap(cMsg, column, result);

                mapResultToSMsg(record, sMsg.A.no(index), index, groupingMap);

                if (index < cMsg.A.length()) {
                    EZDMsg.copy(sMsg.A.no(index), null, cMsg.A.no(index), null);
                    cMsg.A.setValidCount(index + 1);
                }

                mapResultToSMsg(record, sMsg.B.no(index), index, groupingMap);

                if (index < cMsg.B.length()) {
                    EZDMsg.copy(sMsg.B.no(index), null, cMsg.B.no(index), null);
                    cMsg.B.setValidCount(index + 1);
                }

                mapResultToSMsg(record, sMsg.C.no(index), index, groupingMap);

                if (index < cMsg.C.length()) {
                    EZDMsg.copy(sMsg.C.no(index), null, cMsg.C.no(index), null);
                    cMsg.C.setValidCount(index + 1);
                }

                mapResultToSMsg(record, sMsg.D.no(index), index, groupingMap);

                if (index < cMsg.D.length()) {
                    EZDMsg.copy(sMsg.D.no(index), null, cMsg.D.no(index), null);
                    cMsg.D.setValidCount(index + 1);
                }

                mapResultToSMsg(record, sMsg.E.no(index), index, groupingMap);

                if (index < cMsg.E.length()) {
                    EZDMsg.copy(sMsg.E.no(index), null, cMsg.E.no(index), null);
                    cMsg.E.setValidCount(index + 1);
                }

                mapResultToSMsg(record, sMsg.F.no(index), index, groupingMap);

                if (index < cMsg.F.length()) {
                    EZDMsg.copy(sMsg.F.no(index), null, cMsg.F.no(index), null);
                    cMsg.F.setValidCount(index + 1);
                }

                mapResultToSMsg(record, sMsg.G.no(index), index, groupingMap);

                if (index < cMsg.G.length()) {
                    EZDMsg.copy(sMsg.G.no(index), null, cMsg.G.no(index), null);
                    cMsg.G.setValidCount(index + 1);
                }

                index++;
                if (prevFlag == true) {
                    prevFlag = false;
                    continue;
                }
                if (result.next() == false) { break; }
            }

            sMsg.A.setValidCount(index);
            sMsg.B.setValidCount(index);
            sMsg.C.setValidCount(index);
            sMsg.D.setValidCount(index);
            sMsg.E.setValidCount(index);
            sMsg.F.setValidCount(index);
            sMsg.G.setValidCount(index);

            S21InfoLogOutput.println("=== NWAL1880Query.ScreenResultSetHandler.doProcessQueryResult : end ===");
        }

        private void mapResultToSMsg(Map<String, Object> record, NWAL1880_ASMsg asMsg, int index, Map<String, BigDecimal> groupingMap) {

            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // + mapping : Display Fields
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String dplyByItemNm = getDplyByItemNm(record);
            setValue(asMsg.xxDplyByItemNm_A1, dplyByItemNm);
            setValue(asMsg.cratByUsrId_A1, getString(record, SQL_COL.USR_ID));// QC#16992 Add

            setValue(asMsg.xxEntCnt_A1, getBigDecimal(record, SQL_COL.ENT_CNT));
            setValue(asMsg.xxCrHldCnt_A1, getBigDecimal(record, SQL_COL.CR_HLD_CNT));
            setValue(asMsg.xxPrftCnt_A1, getBigDecimal(record, SQL_COL.PRFT_CNT));
            setValue(asMsg.xxVldCnt_A1, getBigDecimal(record, SQL_COL.VLD_CNT));
            setValue(asMsg.xxAllOtherCnt_A1, getBigDecimal(record, SQL_COL.ALL_OTHER_CNT));
            setValue(asMsg.xxBookCnt_A1, getBigDecimal(record, SQL_COL.BOOK_CNT));

        }

        private void mapResultToSMsg(Map<String, Object> record, NWAL1880_BSMsg bsMsg, int index, Map<String, BigDecimal> groupingMap) {

            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // + mapping : Display Fields
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String dplyByItemNm = getDplyByItemNm(record);
            setValue(bsMsg.xxDplyByItemNm_B1, dplyByItemNm);

            setValue(bsMsg.xxInvdCnt_B1, getBigDecimal(record, SQL_COL.INVD_TODAY_CNT));
            setValue(bsMsg.xxInvdCnt_B2, getBigDecimal(record, SQL_COL.INVD_CUR_MTH_CNT));
//            setValue(bsMsg.xxInvdCnt_B3, getBigDecimal(record, SQL_COL.INVD_TOT_CNT));
            BigDecimal totalCnt = BigDecimal.ZERO;
            totalCnt =
                totalCnt.add(getBigDecimal(record, SQL_COL.INVD_TODAY_CNT))
                .add(getBigDecimal(record, SQL_COL.INVD_CUR_MTH_CNT));
            setValue(bsMsg.xxInvdCnt_B3, totalCnt);

        }

        private void mapResultToSMsg(Map<String, Object> record, NWAL1880_CSMsg csMsg, int index, Map<String, BigDecimal> groupingMap) {

            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // + mapping : Display Fields
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String dplyByItemNm = getDplyByItemNm(record);
            setValue(csMsg.xxDplyByItemNm_C1, dplyByItemNm);

            setValue(csMsg.xxAgingCnt_C1, getBigDecimal(record, SQL_COL.AGING_TODAY_CNT));
            setValue(csMsg.xxAgingCnt_C2, getBigDecimal(record, SQL_COL.AGING_1_2_CNT));
            setValue(csMsg.xxAgingCnt_C3, getBigDecimal(record, SQL_COL.AGING_3_10_CNT));
            setValue(csMsg.xxAgingCnt_C4, getBigDecimal(record, SQL_COL.AGING_11_30_CNT));
            setValue(csMsg.xxAgingCnt_C5, getBigDecimal(record, SQL_COL.AGING_31_50_CNT));
            setValue(csMsg.xxAgingCnt_C6, getBigDecimal(record, SQL_COL.AGING_OVER_50_CNT));
//            setValue(csMsg.xxAgingCnt_C7, getBigDecimal(record, SQL_COL.AGING_TOT_CNT));
            BigDecimal totalCnt = BigDecimal.ZERO;
            totalCnt =
                totalCnt.add(getBigDecimal(record, SQL_COL.AGING_TODAY_CNT))
                .add(getBigDecimal(record, SQL_COL.AGING_1_2_CNT))
                .add(getBigDecimal(record, SQL_COL.AGING_3_10_CNT))
                .add(getBigDecimal(record, SQL_COL.AGING_11_30_CNT))
                .add(getBigDecimal(record, SQL_COL.AGING_31_50_CNT))
                .add(getBigDecimal(record, SQL_COL.AGING_OVER_50_CNT));
            setValue(csMsg.xxAgingCnt_C7, totalCnt);

        }

        private void mapResultToSMsg(Map<String, Object> record, NWAL1880_DSMsg dsMsg, int index, Map<String, BigDecimal> groupingMap) {

            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // + mapping : Display Fields
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String dplyByItemNm = getDplyByItemNm(record);
            setValue(dsMsg.xxDplyByItemNm_D1, dplyByItemNm);

            setValue(dsMsg.xxCrRebilCnt_D1, getBigDecimal(record, SQL_COL.CR_REBIL_TODAY_CNT));
            setValue(dsMsg.xxCrRebilCnt_D2, getBigDecimal(record, SQL_COL.CR_REBIL_CUR_MTH_CNT));
//            setValue(dsMsg.xxCrRebilCnt_D3, getBigDecimal(record, SQL_COL.CR_REBIL_TOT_CNT));
            BigDecimal totalCnt = BigDecimal.ZERO;
            totalCnt =
                totalCnt.add(getBigDecimal(record, SQL_COL.CR_REBIL_TODAY_CNT))
                .add(getBigDecimal(record, SQL_COL.CR_REBIL_CUR_MTH_CNT));
            setValue(dsMsg.xxCrRebilCnt_D3, totalCnt);

        }

        private void mapResultToSMsg(Map<String, Object> record, NWAL1880_ESMsg esMsg, int index, Map<String, BigDecimal> groupingMap) {

            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // + mapping : Display Fields
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String dplyByItemNm = getDplyByItemNm(record);
            setValue(esMsg.xxDplyByItemNm_E1, dplyByItemNm);

            setValue(esMsg.xxBllgHldCnt_E1, getBigDecimal(record, SQL_COL.BLLG_HLD_CNT));
            setValue(esMsg.xxBllgHldAmt_E1, getBigDecimal(record, SQL_COL.BLLG_HLD_AMT));

        }

        private void mapResultToSMsg(Map<String, Object> record, NWAL1880_FSMsg fsMsg, int index, Map<String, BigDecimal> groupingMap) {

            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // + mapping : Display Fields
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String dplyByItemNm = getDplyByItemNm(record);
            setValue(fsMsg.xxDplyByItemNm_F1, dplyByItemNm);

            setValue(fsMsg.xxRvwDealConfigCnt_F1, getBigDecimal(record, SQL_COL.RVW_DEAL_CONFIG_CNT));
            setValue(fsMsg.xxRvwSomCnt_F1, getBigDecimal(record, SQL_COL.RVW_SOM_CNT));

        }

        private void mapResultToSMsg(Map<String, Object> record, NWAL1880_GSMsg gsMsg, int index, Map<String, BigDecimal> groupingMap) {

            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // + mapping : Display Fields
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String dplyByItemNm = getDplyByItemNm(record);
            setValue(gsMsg.xxDplyByItemNm_G1, dplyByItemNm);

            setValue(gsMsg.xxAcptDealConfigCnt_G1, getBigDecimal(record, SQL_COL.ACPT_DEAL_CONFIG_CNT));
            setValue(gsMsg.xxAcptSomCnt_G1, getBigDecimal(record, SQL_COL.ACPT_SOM_CNT));

        }

        private String getDplyByItemNm(Map<String, Object> record) {
            String dplyByItemNm = "";
            String dplyBy01ItemCd = getString(record, SQL_COL.DPLY_BY_01_ITEM_CD);
            String dplyBy01ItemNm = getString(record, SQL_COL.DPLY_BY_01_ITEM_NM);
            dplyByItemNm = dplyBy01ItemCd;
            if (hasValue(dplyBy01ItemNm)) {
                dplyByItemNm = concatString(dplyByItemNm, HYPHEN, dplyBy01ItemNm);
            }
            return dplyByItemNm;
        }

    }

    /**
     * CommonResultSetHandler
     */
    private abstract class CommonResultSetHandler extends S21SsmVoidResultSetHandlerSupport {

        protected List<String> getColumnList(ResultSet result) throws SQLException {
            ResultSetMetaData metaData = result.getMetaData();

            List<String> column = new ArrayList<String>();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                column.add(metaData.getColumnName(i));
            }

            return column;
        }

        protected Map<String, Object> getResultMap(NWAL1880CMsg cMsg, List<String> column, ResultSet result) throws SQLException {
            Map<String, Object> resultMap = new HashMap<String, Object>();

            putResult(resultMap, column, result, SQL_COL.GLBL_CMPY_CD);

            putResult(resultMap, column, result, SQL_COL.DPLY_BY_01_ITEM_CD);
            if (hasValue(cMsg.pairDplyByItemNm)) {
                putResult(resultMap, column, result, SQL_COL.DPLY_BY_01_ITEM_NM);
            }

            // QC#16992 ADD Start
            if (DISP_BY_CRAT_BY_USR_ID.equals(cMsg.xxScrItem50Txt.getValue())) {
                putResult(resultMap, column, result, SQL_COL.USR_ID);
            }
            // QC#16992 ADD End

            putResultBigDecimal(resultMap, column, result, SQL_COL.ENT_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.CR_HLD_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.PRFT_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.VLD_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.ALL_OTHER_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.BOOK_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.INVD_TODAY_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.INVD_CUR_MTH_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.INVD_TOT_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.AGING_TODAY_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.AGING_1_2_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.AGING_3_10_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.AGING_11_30_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.AGING_31_50_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.AGING_OVER_50_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.AGING_TOT_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.CR_REBIL_TODAY_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.CR_REBIL_CUR_MTH_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.CR_REBIL_TOT_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.BLLG_HLD_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.BLLG_HLD_AMT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.RVW_DEAL_CONFIG_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.RVW_SOM_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.ACPT_DEAL_CONFIG_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.ACPT_SOM_CNT);

            return resultMap;

        }

        protected String getString(Map<String, Object> resultMap, SQL_COL key) {
            return (String) resultMap.get(key.name());
        }

        protected BigDecimal getBigDecimal(Map<String, Object> resultMap, SQL_COL key) {
            return (BigDecimal) resultMap.get(key.name());
        }

        private void putResult(Map<String, Object> resultMap, List<String> column, ResultSet result, SQL_COL key) throws SQLException {
            if (!column.contains(key.name())) {
                return;
            }
            resultMap.put(key.name(), S21SsmEZDOracleJDBCUtil.getInstance().getString(result, key.name()));
        }

        private void putResultBigDecimal(Map<String, Object> resultMap, List<String> column, ResultSet result, SQL_COL key) throws SQLException {
            if (!column.contains(key.name())) {
                return;
            }
            resultMap.put(key.name(), S21SsmEZDOracleJDBCUtil.getInstance().getBigDecimal(result, key.name()));
        }
    }

}
