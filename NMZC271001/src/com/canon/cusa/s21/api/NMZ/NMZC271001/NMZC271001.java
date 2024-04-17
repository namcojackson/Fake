/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NMZ.NMZC271001;

import static com.canon.cusa.s21.api.NMZ.NMZC271001.Constant.NMZC271001Constant.MODE_PROSPECT_TO_TERRITORY;
import static com.canon.cusa.s21.api.NMZ.NMZC271001.Constant.NMZC271001Constant.MODE_PROSPECT_TO_TERRITORY_BATCH;
import static com.canon.cusa.s21.api.NMZ.NMZC271001.Constant.NMZC271001Constant.NMZM0082E;
import static com.canon.cusa.s21.api.NMZ.NMZC271001.Constant.NMZC271001Constant.NMZM0090E;
import static com.canon.cusa.s21.api.NMZ.NMZC271001.Constant.NMZC271001Constant.NMZM0091E;
import static com.canon.cusa.s21.api.NMZ.NMZC271001.Constant.NMZC271001Constant.NMZM0092E;
import static com.canon.cusa.s21.api.NMZ.NMZC271001.Constant.NMZC271001Constant.SRCH_COND_KEY_DS_ACCT_NM;
import static com.canon.cusa.s21.api.NMZ.NMZC271001.Constant.NMZC271001Constant.SRCH_COND_KEY_DS_CUST_SIC_CD;
import static com.canon.cusa.s21.api.NMZ.NMZC271001.Constant.NMZC271001Constant.SRCH_COND_KEY_POST_CD;
import static com.canon.cusa.s21.api.NMZ.NMZC271001.Constant.NMZC271001Constant.SRCH_COND_KEY_ST_CD;
import static com.canon.cusa.s21.api.NMZ.NMZC271001.Constant.NMZC271001Constant.SRCH_COND_KEY_XX_ROW_NUM;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.parts.NMZC271001PMsg;
import business.parts.NMZC271001_searchListBatchPMsg;
import business.parts.NMZC271001_searchListBatchPMsgArray;
import business.parts.NMZC271001_searchListPMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_RULE_LOGIC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_RULE_OPRD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_RULE_TP;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;

/**
 *<pre>
 * NMZC271001 Account Territory Rule Relation API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/25   Fujitsu         K.Minamide       Create          N/A
 * 2016/01/21   Fujitsu         K.Koitabashi     Update          QC#3231
 * 2016/06/24   SRAA            Y.Chen           Update          QC#10858
 * 2016/08/24   SRAA            Y.Chen           Update          QC#5786
 * 2016/09/07   SRAA            Y.Chen           Update          QC#12258
 * 2018/06/01   Fujitsu         Hd.Sugawara      Update          QC#24293
 *</pre>
 */
public class NMZC271001 extends S21ApiCommonBase {

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap = null;

    /** SQL access parts */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /**
     * Constructor
     */
    public NMZC271001() {
        super();
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
    }

    /**
     * execute (This can be called method from external class.)
     * @param pMsg NMZC270001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NMZC271001PMsg pMsg, final ONBATCH_TYPE onBatchType) {

        this.msgMap = new S21ApiMessageMap(pMsg);

        this.doProcess(pMsg, onBatchType);

        this.msgMap.flush();
    }

    /**
     * Main process method.
     * @param pMsg NMZC271001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    private void doProcess(NMZC271001PMsg pMsg, ONBATCH_TYPE onBatchType) {

        if (!this.checkInput(pMsg)) {
            return;
        }

        String mode = pMsg.xxModeInd.getValue();
        if (MODE_PROSPECT_TO_TERRITORY.equals(mode)) {

            for (int i = 0; i < pMsg.searchList.getValidCount(); i++) {
                // QC#12258
                List<Map<String, String>> exactMatchTerritoryList = this.getAndCondMatch(pMsg, createWorkDataForSrch(pMsg, pMsg.searchList.no(i)));

                int count = 0;

                for (Map<String, String> exactMatchTerritory : exactMatchTerritoryList) {
                    if (count >= pMsg.territoryList.length()) {
                        break;
                    }
                    addTerritory(pMsg, exactMatchTerritory.get("ORG_CD"), exactMatchTerritory.get("ORG_NM"), count);
                    count++;
                }

                List<Map<String, String>> partialMatchTerritoryList = this.getOrCondMatch(pMsg, createWorkDataForSrch(pMsg, pMsg.searchList.no(i)));

                for (Map<String, String> partialMatchTerritory : partialMatchTerritoryList) {
                    if (count >= pMsg.territoryList.length()) {
                        break;
                    }
                    addTerritory(pMsg, partialMatchTerritory.get("ORG_CD"), partialMatchTerritory.get("ORG_NM"), count);
                    count++;
                }

                // QC#5786
                pMsg.territoryList.setValidCount(count);
            }
        // QC#12258
        } else if (MODE_PROSPECT_TO_TERRITORY_BATCH.equals(mode)) {
            int count = 0;
            List<Map<String, String>> exactMatchTerritoryList = this.getAndCondMatch(pMsg, createWorkDataForSrch(pMsg, pMsg.searchListBatch));

            for (Map<String, String> exactMatchTerritory : exactMatchTerritoryList) {
                if (count >= pMsg.territoryListBatch.length()) {
                    break;
                }
                addTerritory(pMsg, exactMatchTerritory.get("XX_ROW_NUM"), exactMatchTerritory.get("ORG_CD"), exactMatchTerritory.get("ORG_NM"), count);
                count++;
            }

            List<Map<String, String>> partialMatchTerritoryList = this.getOrCondMatch(pMsg, createWorkDataForSrch(pMsg, pMsg.searchListBatch));

            for (Map<String, String> partialMatchTerritory : partialMatchTerritoryList) {
                if (count >= pMsg.territoryListBatch.length()) {
                    break;
                }
                addTerritory(pMsg, partialMatchTerritory.get("XX_ROW_NUM"), partialMatchTerritory.get("ORG_CD"), partialMatchTerritory.get("ORG_NM"), count);
                count++;
            }

            pMsg.territoryListBatch.setValidCount(count);
        }
    }

    // QC#12258
    private List<Map<String, String>> getAndCondMatch(NMZC271001PMsg pMsg, List<Map<String, String>> wrkDataList) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        setComnSrchCond(pMsg, ssmParam, wrkDataList);
        ssmParam.put("trtyRuleLogicTpCd", TRTY_RULE_LOGIC_TP.OR);

        List<Map<String, String>> resultList = getMatchDirect("getExactMatchTerritory", ssmParam);
        return resultList;
    }

    private List<Map<String, String>> getOrCondMatch(NMZC271001PMsg pMsg, List<Map<String, String>> wrkDataList) {
        List<String> condTpList = new ArrayList<String>();
        condTpList.add("state_equal");
        // Del Start 2018/06/01 QC#24293
        //condTpList.add("state_like");
        //condTpList.add("state_in");
        //condTpList.add("state_between");
        // Del End 2018/06/01 QC#24293

        condTpList.add("sic_equal");
        // Del Start 2018/06/01 QC#24293
        //condTpList.add("sic_like");
        //condTpList.add("sic_in");
        //condTpList.add("sic_between");
        // Del End 2018/06/01 QC#24293

        condTpList.add("post_equal");
        // Del Start 2018/06/01 QC#24293
        //condTpList.add("post_like");
        // Del End 2018/06/01 QC#24293
        condTpList.add("post_between_sameHeader");
        condTpList.add("post_between_diffHeader");

        condTpList.add("account_equal");
        // Del Start 2018/06/01 QC#24293
        //condTpList.add("account_like");
        //condTpList.add("account_between");
        // Del End 2018/06/01 QC#24293

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        setComnSrchCond(pMsg, ssmParam, wrkDataList);
        ssmParam.put("trtyRuleLogicTpCd", TRTY_RULE_LOGIC_TP.AND);
        ssmParam.put("condTpList", condTpList);

        List<Map<String, String>> resultList = getMatchDirect("getPartialMatchTerritory", ssmParam);
        return resultList;
    }

    /**
     * 'executeQuery' is faster than 'queryObjectList'
     */
    private List<Map<String, String>> getMatchDirect(String sqlId, Map<String, Object> ssmParam) {
        PreparedStatement stmt = null;
        ResultSet rsSet = null;
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(1000);
            execParam.setMaxRows(0);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

            stmt = this.ssmLLClient.createPreparedStatement(sqlId, ssmParam, execParam);
            rsSet = stmt.executeQuery();
            int row = 0;

            String[] colNmArr = new String[] {"ORG_CD", "ORG_NM", "XX_ROW_NUM" };
            while (rsSet.next()) {

                Map<String, String> map = new HashMap<String, String>();
                for (String colNm : colNmArr) {
                    map.put(colNm, rsSet.getString(colNm));
                }

                list.add(map);
                row++;
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
        return list;
    }

    private List<Map<String, String>> createWorkDataForSrch(NMZC271001PMsg pMsg, NMZC271001_searchListPMsg subPMsg) {
        List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
        Map<String, String> map = new HashMap<String, String>();
        map.put(SRCH_COND_KEY_XX_ROW_NUM, Integer.toString(0));
        map.put(SRCH_COND_KEY_ST_CD, subPMsg.stCd.getValue());
        map.put(SRCH_COND_KEY_POST_CD, subPMsg.postCd.getValue());
        map.put(SRCH_COND_KEY_DS_ACCT_NM, subPMsg.dsAcctNm.getValue());
        map.put(SRCH_COND_KEY_DS_CUST_SIC_CD, subPMsg.dsCustSicCd.getValue());
        listMap.add(map);
        return listMap;
    }

    private List<Map<String, String>> createWorkDataForSrch(NMZC271001PMsg pMsg, NMZC271001_searchListBatchPMsgArray subPMsgArray) {
        List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
        for (int i = 0; i < subPMsgArray.getValidCount(); i++) {
            NMZC271001_searchListBatchPMsg subPMsg = subPMsgArray.no(i);
            Map<String, String> map = new HashMap<String, String>();
            map.put(SRCH_COND_KEY_XX_ROW_NUM, Integer.toString(i));
            map.put(SRCH_COND_KEY_ST_CD, subPMsg.stCd.getValue());
            map.put(SRCH_COND_KEY_POST_CD, subPMsg.postCd.getValue());
            map.put(SRCH_COND_KEY_DS_ACCT_NM, subPMsg.dsAcctNm.getValue());
            map.put(SRCH_COND_KEY_DS_CUST_SIC_CD, subPMsg.dsCustSicCd.getValue());
            listMap.add(map);
        }
        return listMap;
    }

    private void setComnSrchCond(NMZC271001PMsg pMsg, Map<String, Object> ssmParam, List<Map<String, String>> wrkDataList) {
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("slsDt", pMsg.slsDt.getValue());

        ssmParam.put("trtyRuleTpCdState", TRTY_RULE_TP.STATE);
        ssmParam.put("trtyRuleTpCdPostalCode", TRTY_RULE_TP.POSTAL_CODE);
        // Mod Start 2018/06/01 QC#24293
        //ssmParam.put("trtyRuleTpCdAccountName", TRTY_RULE_TP.ACCOUNT_NAME);
        ssmParam.put("trtyRuleTpCdAccountNumber", TRTY_RULE_TP.ACCOUNT_NUMBER);
        // Mod End 2018/06/01 QC#24293
        ssmParam.put("trtyRuleTpCdSicCode", TRTY_RULE_TP.SIC_CODE);

        ssmParam.put("trtyRuleOprdTpCdEqual", TRTY_RULE_OPRD_TP.EQUAL);
        ssmParam.put("trtyRuleOprdTpCdBetween", TRTY_RULE_OPRD_TP.BETWEEN);
        // Del Start 2018/06/01 QC#24293
        //ssmParam.put("trtyRuleOprdTpCdLike", TRTY_RULE_OPRD_TP.LIKE);
        //ssmParam.put("trtyRuleOprdTpCdNotEqual", TRTY_RULE_OPRD_TP.NOT_EQUAL);
        //ssmParam.put("trtyRuleOprdTpCdIn", TRTY_RULE_OPRD_TP.IN);
        // Del End 2018/06/01 QC#24293

        ssmParam.put("gnrnTpCurrent", GNRN_TP.CURRENT);
        ssmParam.put("gnrnTpFuture", GNRN_TP.FUTURE);
        // Add Start 2018/06/01 QC#24293
        ssmParam.put("flgOnY", ZYPConstant.FLG_ON_Y);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        // Add End 2018/06/01 QC#24293

        ssmParam.put("wrkDataList", wrkDataList);
    }

    /**
     * Check Input Parameter
     * @param pMsg NMZC271001PMsg
     * @return No Error : true
     */
    private boolean checkInput(NMZC271001PMsg pMsg) {
        if (!ZYPCommonFunc.hasValue(pMsg.xxModeInd)) {
            this.msgMap.addXxMsgId(NMZM0090E);
            return false;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            this.msgMap.addXxMsgId(NMZM0092E);
            return false;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.slsDt)) {
            this.msgMap.addXxMsgId(NMZM0082E);
            return false;
        }

        String mode = pMsg.xxModeInd.getValue();

        if (MODE_PROSPECT_TO_TERRITORY.equals(mode)) {
            for (int i = 0; i < pMsg.searchList.getValidCount(); i++) {
                if (!ZYPCommonFunc.hasValue(pMsg.searchList.no(i).stCd) && !ZYPCommonFunc.hasValue(pMsg.searchList.no(i).postCd) && !ZYPCommonFunc.hasValue(pMsg.searchList.no(i).dsAcctNm) && !ZYPCommonFunc.hasValue(pMsg.searchList.no(i).dsCustSicCd)) {
                    this.msgMap.addXxMsgId(NMZM0091E);
                    return false;
                }
            }
        // QC#12258
        } else if (MODE_PROSPECT_TO_TERRITORY_BATCH.equals(mode)) {
            for (int i = 0; i < pMsg.searchListBatch.getValidCount(); i++) {
                if (!ZYPCommonFunc.hasValue(pMsg.searchListBatch.no(i).stCd) && !ZYPCommonFunc.hasValue(pMsg.searchListBatch.no(i).postCd) && !ZYPCommonFunc.hasValue(pMsg.searchListBatch.no(i).dsAcctNm)
                        && !ZYPCommonFunc.hasValue(pMsg.searchListBatch.no(i).dsCustSicCd)) {
                    this.msgMap.addXxMsgId(NMZM0091E);
                    return false;
                }
            }
        }
        return true;
    }

    private static void addTerritory(NMZC271001PMsg pMsg, String orgCd, String orgNm, int count) {
        ZYPEZDItemValueSetter.setValue(pMsg.territoryList.no(count).orgCd, orgCd);
        ZYPEZDItemValueSetter.setValue(pMsg.territoryList.no(count).orgNm, orgNm);
    }

    // QC#11258
    private static void addTerritory(NMZC271001PMsg pMsg, String rowNum, String orgCd, String orgNm, int count) {
        ZYPEZDItemValueSetter.setValue(pMsg.territoryListBatch.no(count).xxRowNum, new BigDecimal(rowNum));
        ZYPEZDItemValueSetter.setValue(pMsg.territoryListBatch.no(count).orgCd, orgCd);
        ZYPEZDItemValueSetter.setValue(pMsg.territoryListBatch.no(count).orgNm, orgNm);
    }
}
