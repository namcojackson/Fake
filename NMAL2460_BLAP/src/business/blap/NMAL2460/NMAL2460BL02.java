/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NMAL2460;

import static business.blap.NMAL2460.constant.NMAL2460Constant.CSV_FILE_NAME;
import static business.blap.NMAL2460.constant.NMAL2460Constant.MAX_FETCH_SIZE;
import static business.blap.NMAL2460.constant.NMAL2460Constant.MIN_SRCH_ORG_TIER;
import static business.blap.NMAL2460.constant.NMAL2460Constant.NMAM8121E;
import static business.blap.NMAL2460.constant.NMAL2460Constant.NMAM8646E;
import static business.blap.NMAL2460.constant.NMAL2460Constant.NZZM0001W;
import static business.blap.NMAL2460.constant.NMAL2460Constant.NZZM0002I;
import static business.blap.NMAL2460.constant.NMAL2460Constant.ZZZM9001E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL2460.common.NMAL2460CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SRCH_LYOT_TP;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * Account Owner Lookup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/27   Hitachi         O.Okuma         Create          N/A
 * 2016/09/21   Fujitsu         Mz.Takahashi    Update          QC#11068
 * 2017/10/19   Fujitsu         M.Ohno          Update          QC#21631
 * 2017/11/28   Fujitsu         Hd.Sugawara     Update          QC#21044
 *</pre>
 */
public class NMAL2460BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NMAL2460CMsg cMsg = (NMAL2460CMsg) arg0;
        NMAL2460SMsg sMsg = (NMAL2460SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        // Common Column Order Text
        String xxComnColOrdTxt = cMsg.xxComnColOrdTxt.getValue();
        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NMAL2460_INIT".equals(screenAplID)) {
                doProcess_NMAL2460_INIT(cMsg, sMsg);
                ZYPGUITableColumn.getColData(cMsg, sMsg, "BHEAD");
            } else if ("NMAL2460Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL2460Scrn00_CMN_Clear(cMsg, sMsg);
            } else if ("NMAL2460Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NMAL2460Scrn00_CMN_Download(cMsg, sMsg);
            } else if ("NMAL2460Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL2460Scrn00_CMN_Submit(cMsg, sMsg);
                if (SRCH_LYOT_TP.LAYOUT_1.equals(cMsg.srchLyotTpCd.getValue())) {
                    ZYPGUITableColumn.getColData(cMsg, sMsg, "AHEAD");
                } else {
                    ZYPGUITableColumn.getColData(cMsg, sMsg, "BHEAD");
                }
            } else if ("NMAL2460Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NMAL2460Scrn00_PageJump(cMsg, sMsg);
            } else if ("NMAL2460Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NMAL2460Scrn00_PageNext(cMsg, sMsg);
            } else if ("NMAL2460Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NMAL2460Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NMAL2460Scrn00_Search".equals(screenAplID)) {
                doProcess_NMAL2460Scrn00_Search(cMsg, sMsg);
                if (SRCH_LYOT_TP.LAYOUT_1.equals(cMsg.srchLyotTpCd.getValue())) {
                    ZYPGUITableColumn.getColData(cMsg, sMsg, "AHEAD");
                } else {
                    ZYPGUITableColumn.getColData(cMsg, sMsg, "BHEAD");
                }
            } else if ("NMAL2460_NMAL2630".equals(screenAplID)) {
                doProcess_NMAL2460_NMAL2630(cMsg, sMsg);
            } else if ("NMAL2460Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NMAL2460Scrn00_TBLColumnSort(cMsg, sMsg);
            } else if ("NMAL2460Scrn00_SearchTrtyInfo_01".equals(screenAplID)) {
                doProcess_NMAL2460Scrn00_SearchTrtyInfo(cMsg, sMsg);
            } else if ("NMAL2460Scrn00_SearchTrtyInfo_02".equals(screenAplID)) {
                doProcess_NMAL2460Scrn00_SearchTrtyInfo(cMsg, sMsg);
            } else if ("NMAL2460Scrn00_SearchTrtyInfo_03".equals(screenAplID)) {
                doProcess_NMAL2460Scrn00_SearchTrtyInfo(cMsg, sMsg);
            } else if ("NMAL2460Scrn00_SearchTrtyInfo_04".equals(screenAplID)) {
                doProcess_NMAL2460Scrn00_SearchTrtyInfo(cMsg, sMsg);
            } else if ("NMAL2460Scrn00_SearchTrtyInfo_05".equals(screenAplID)) {
                doProcess_NMAL2460Scrn00_SearchTrtyInfo(cMsg, sMsg);
            } else if ("NMAL2460Scrn00_SearchTrtyInfo_06".equals(screenAplID)) {
                doProcess_NMAL2460Scrn00_SearchTrtyInfo(cMsg, sMsg);
            } else if ("NMAL2460Scrn00_SearchTrtyInfo_07".equals(screenAplID)) {
                doProcess_NMAL2460Scrn00_SearchTrtyInfo(cMsg, sMsg);
            } else if ("NMAL2460Scrn00_SearchTrtyInfo_08".equals(screenAplID)) {
                doProcess_NMAL2460Scrn00_SearchTrtyInfo(cMsg, sMsg);
            } else if ("NMAL2460Scrn00_SearchTrtyInfo_09".equals(screenAplID)) {
                doProcess_NMAL2460Scrn00_SearchTrtyInfo(cMsg, sMsg);
            } else if ("NMAL2460Scrn00_SearchTrtyInfo_10".equals(screenAplID)) {
                doProcess_NMAL2460Scrn00_SearchTrtyInfo(cMsg, sMsg);
            } else if ("NMAL2460Scrn00_SearchTrtyInfo_11".equals(screenAplID)) {
                doProcess_NMAL2460Scrn00_SearchTrtyInfo(cMsg, sMsg);
            } else if ("NMAL2460Scrn00_SearchTrtyInfo_12".equals(screenAplID)) {
                doProcess_NMAL2460Scrn00_SearchTrtyInfo(cMsg, sMsg);
            } else if ("NMAL2460Scrn00_SearchTrtyInfo_13".equals(screenAplID)) {
                doProcess_NMAL2460Scrn00_SearchTrtyInfo(cMsg, sMsg);
            } else if ("NMAL2460Scrn00_SearchTrtyInfo_14".equals(screenAplID)) {
                doProcess_NMAL2460Scrn00_SearchTrtyInfo(cMsg, sMsg);
            } else if ("NMAL2460Scrn00_SearchTrtyInfo_15".equals(screenAplID)) {
                doProcess_NMAL2460Scrn00_SearchTrtyInfo(cMsg, sMsg);
            } else if ("NMAL2460Scrn00_SearchTrtyInfo_16".equals(screenAplID)) {
                doProcess_NMAL2460Scrn00_SearchTrtyInfo(cMsg, sMsg);
            } else if ("NMAL2460Scrn00_SearchTrtyInfo_17".equals(screenAplID)) {
                doProcess_NMAL2460Scrn00_SearchTrtyInfo(cMsg, sMsg);
            } else if ("NMAL2460Scrn00_SearchTrtyInfo_18".equals(screenAplID)) {
                doProcess_NMAL2460Scrn00_SearchTrtyInfo(cMsg, sMsg);
            } else if ("NMAL2460Scrn00_SearchTrtyInfo_19".equals(screenAplID)) {
                doProcess_NMAL2460Scrn00_SearchTrtyInfo(cMsg, sMsg);
            } else if ("NMAL2460Scrn00_SearchTrtyInfo_20".equals(screenAplID)) {
                doProcess_NMAL2460Scrn00_SearchTrtyInfo(cMsg, sMsg);
            } else if ("NMAL2460Scrn00_OnChangeSavedSearchOption".equals(screenAplID)) {
                doProcess_NMAL2460Scrn00_OnChangeSavedSearchOption(cMsg, sMsg);
            }
            
        } finally {
            // Set Common Column Order Text of SMsg
            sMsg.xxComnColOrdTxt.clear();
            ZYPEZDItemValueSetter.setValue(sMsg.xxComnColOrdTxt, xxComnColOrdTxt);
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * doProcess_NMAL2460_INIT
     * @param cMsg NMAL2460CMsg
     * @param sMsg NMAL2460SMsg
     */
    private void doProcess_NMAL2460_INIT(NMAL2460CMsg cMsg, NMAL2460SMsg sMsg) {

        NMAL2460CommonLogic.clearMsg(cMsg, sMsg);

        ZYPEZDItemValueSetter.setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue()));

        NMAL2460CommonLogic.createPullDown(cMsg);
        NMAL2460CommonLogic.createSavedSearchOptionsPullDown(cMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
        
        cMsg.xxPageShowOfNum_A.setValue(sMsg.A.getValidCount());
        cMsg.xxPageShowOfNum_B.setValue(sMsg.A.getValidCount());
    }

    /**
     * doProcess_NMAL2460Scrn00_CMN_Clear
     * @param cMsg NMAL2460CMsg
     * @param sMsg NMAL2460SMsg
     */
    private void doProcess_NMAL2460Scrn00_CMN_Clear(NMAL2460CMsg cMsg, NMAL2460SMsg sMsg) {
        doProcess_NMAL2460_INIT(cMsg, sMsg);
    }

    /**
     * doProcess_NMAL2460Scrn00_CMN_Download
     * @param cMsg NMAL2460CMsg
     * @param sMsg NMAL2460SMsg
     */
    private void doProcess_NMAL2460Scrn00_CMN_Download(NMAL2460CMsg cMsg, NMAL2460SMsg sMsg) {
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(MAX_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NMAL2460Query.getInstance().getClass());

            // create csv file
            cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), ".csv");

            if (SRCH_LYOT_TP.LAYOUT_1.equals(cMsg.srchLyotTpCd.getValue())) {
                // Mod Start 2017/09/21 QC#20586
//                Map<String, Object> ssMParam = NMAL2460Query.setSearchParam(cMsg, getGlobalCompanyCode());
                Map<String, Object> ssMParam = NMAL2460Query.setSearchParam(cMsg, getGlobalCompanyCode(), 5001);
                // Mod End 2017/09/21 QC#20586
                ps = ssmLLClient.createPreparedStatement("getSearchLayer1", ssMParam, execParam);
                rs = ps.executeQuery();
                NMAL2460CommonLogic.writeCsvFileForLayout1(cMsg, sMsg, rs);

            } else {
                // 2017/10/19 QC#21631 add start
                NMAL2460CommonLogic.defPostCd(cMsg);
                // 2017/10/19 QC#21631 add end
                List<Map<String, Object>> lineBizRoleTpList = NMAL2460CommonLogic.getLineBizRoleTp();
                // Mod Start 2017/09/21 QC#20586
//                Map<String, Object> ssMParam = NMAL2460Query.setSearchParamForLayout2(cMsg, getGlobalCompanyCode(), lineBizRoleTpList);
                Map<String, Object> ssMParam = NMAL2460Query.setSearchParamForLayout2(cMsg, getGlobalCompanyCode(), lineBizRoleTpList, 5001);
                // Mod End 2017/09/21 QC#20586
                ps = ssmLLClient.createPreparedStatement("getSearchLayer2", ssMParam, execParam);
                rs = ps.executeQuery();
                NMAL2460CommonLogic.writeCsvFileForLayout2(cMsg, sMsg, rs, getGlobalCompanyCode());
            }   
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    /**
     * doProcess_NMAL2460Scrn00_CMN_Submit
     * @param cMsg NMAL2460CMsg
     * @param sMsg NMAL2460SMsg
     */
    private void doProcess_NMAL2460Scrn00_CMN_Submit(NMAL2460CMsg cMsg, NMAL2460SMsg sMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.getMessageKind())) {
            return;
        }

        NMAL2460CommonLogic.initializeBackupTerritory(sMsg);
        NMAL2460CommonLogic.pagenationForJump(cMsg, sMsg);
        cMsg.setMessageInfo(NZZM0002I);
    }

    /**
     * doProcess_NMAL2460Scrn00_PageJump
     * @param cMsg NMAL2460CMsg
     * @param sMsg NMAL2460SMsg
     */
    private void doProcess_NMAL2460Scrn00_PageJump(NMAL2460CMsg cMsg, NMAL2460SMsg sMsg) {

        NMAL2460CommonLogic.setPagenation(cMsg, sMsg);
        NMAL2460CommonLogic.pagenationForJump(cMsg, sMsg);
    }

    /**
     * doProcess_NMAL2460Scrn00_PageNext
     * @param cMsg NMAL2460CMsg
     * @param sMsg NMAL2460SMsg
     */
    private void doProcess_NMAL2460Scrn00_PageNext(NMAL2460CMsg cMsg, NMAL2460SMsg sMsg) {

        NMAL2460CommonLogic.setPagenation(cMsg, sMsg);
        NMAL2460CommonLogic.pagenation(cMsg, sMsg, true);
    }

    /**
     * doProcess_NMAL2460Scrn00_PagePrev
     * @param cMsg NMAL2460CMsg
     * @param sMsg NMAL2460SMsg
     */
    private void doProcess_NMAL2460Scrn00_PagePrev(NMAL2460CMsg cMsg, NMAL2460SMsg sMsg) {

        NMAL2460CommonLogic.setPagenation(cMsg, sMsg);
        NMAL2460CommonLogic.pagenation(cMsg, sMsg, false);
    }

    /**
     * doProcess_NMAL2460Scrn00_Search
     * @param cMsg NMAL2460CMsg
     * @param sMsg NMAL2460SMsg
     */
    private void doProcess_NMAL2460Scrn00_Search(NMAL2460CMsg cMsg, NMAL2460SMsg sMsg) {

        // QC#12193
        if(!checkOrgTier(cMsg)){
            return;
        }

        NMAL2460CommonLogic.clearSearchResult(cMsg, sMsg);
        S21SsmEZDResult ssmResult = null;

        // Search for LAYOUT 1
        if (SRCH_LYOT_TP.LAYOUT_1.equals(cMsg.srchLyotTpCd_H.getValue())) {
            ssmResult = NMAL2460Query.getInstance().getSearchLayer1(cMsg, sMsg, getGlobalCompanyCode());

            if (ssmResult.isCodeNormal()) {
                // Result > sMsg.A.length()
                int queryResCnt = ssmResult.getQueryResultCount();
                if (queryResCnt > sMsg.A.length()) {
                    cMsg.setMessageInfo(NZZM0001W);
                    queryResCnt = sMsg.A.length();
                } else {
                    cMsg.setMessageInfo(NZZM0002I);
                }
                sMsg.A.setValidCount(queryResCnt);
            } else {
                // No result
                cMsg.setMessageInfo(ZZZM9001E);
                return;
            }

        // Search for LAYOUT 2
        } else {
            // 2017/10/19 QC#21631 add start
            NMAL2460CommonLogic.defPostCd(cMsg);
            // 2017/10/19 QC#21631 add end

            List<Map<String, Object>> lineBizRoleTpList = NMAL2460CommonLogic.getLineBizRoleTp();
            ssmResult = NMAL2460Query.getInstance().getSearchLayer2(cMsg, sMsg, getGlobalCompanyCode(), lineBizRoleTpList);

            if (ssmResult.isCodeNormal()) {
                // Result > sMsg.B.length()
                int queryResCnt = ssmResult.getQueryResultCount();
                if (queryResCnt > sMsg.B.length()) {
                    cMsg.setMessageInfo(NZZM0001W);
                    queryResCnt = sMsg.B.length();
                } else {
                    cMsg.setMessageInfo(NZZM0002I);
                }
                sMsg.B.setValidCount(queryResCnt);
                NMAL2460CommonLogic.setTitleLabel(cMsg);
            } else {
                // No result
                cMsg.setMessageInfo(ZZZM9001E);
                return;
            }
        }

        ZYPEZDItemValueSetter.setValue(cMsg.srchLyotTpCd, cMsg.srchLyotTpCd_H);
        ZYPEZDItemValueSetter.setValue(cMsg.orgNm_HB, cMsg.orgNm_H2);
        NMAL2460CommonLogic.pagenation(cMsg, sMsg, true);
    }

    /**
     * doProcess_NMAL2460Scrn00_SearchTrtyInfo
     * @param cMsg NMAL2460CMsg
     * @param sMsg NMAL2460SMsg
     */
    private void doProcess_NMAL2460Scrn00_SearchTrtyInfo(NMAL2460CMsg cMsg, NMAL2460SMsg sMsg) {
        NMAL2460_BCMsg bcMsg = cMsg.B.no(cMsg.xxNum_EV.getValueInt());

        // get Territory Detail
        String orgNm = NMAL2460CommonLogic.defineTerritoryName(bcMsg, cMsg.asgTrtyAttrbCd.getValue());
        // Add Start 2017/11/28 QC#21044
        String nonSlsRepFlg = NMAL2460CommonLogic.defineNonSalesRepFlag(cMsg);
        // Add End 2017/11/28 QC#21044

        // Mod Start 2017/11/28 QC#21044
        //S21SsmEZDResult ssmResult = NMAL2460Query.getInstance().getTerritoryDetail(orgNm);
        S21SsmEZDResult ssmResult = NMAL2460Query.getInstance().getTerritoryDetail(orgNm, nonSlsRepFlg);
        // Mod End 2017/11/28 QC#21044

        // set Territory Detail
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(0);

            NMAL2460CommonLogic.setTerritoryDetail(bcMsg, resultMap, cMsg.asgTrtyAttrbCd.getValue());
        } else {
            cMsg.setMessageInfo(NMAM8121E, new String[] {"Territory" });
        }
    }

    /**
     * doProcess_NMAL2460Scrn00_OnChangeSavedSearchOption
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NMAL2460Scrn00_OnChangeSavedSearchOption(NMAL2460CMsg cMsg, NMAL2460SMsg sMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.srchOptPk_S)) {
            NMAL2460CommonLogic.callNszc0330forSearchOption(cMsg, sMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
        }
    }

    /**
     * doProcess_NMAL2460Scrn00_TBLColumnSort
     * @param cMsg NMAL2460CMsg
     * @param sMsg NMAL2460SMsg
     */
    private void doProcess_NMAL2460Scrn00_TBLColumnSort(NMAL2460CMsg cMsg, NMAL2460SMsg sMsg) {

        String sortTblNm = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();

        if ("A".equals(sortTblNm)) {
            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

            // SMsg -> CMsg
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            // Show 1st page
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum_A, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum_A, new BigDecimal(cMsg.A.getValidCount()));
        } else if ("B".equals(sortTblNm)) {
            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.B, sMsg.B.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.B.getValidCount());

            // SMsg -> CMsg
            int i = 0;
            for (; i < sMsg.B.getValidCount(); i++) {
                if (i == cMsg.B.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i), null);
            }
            cMsg.A.setValidCount(i);

            // Show 1st page
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum_B, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum_B, new BigDecimal(cMsg.A.getValidCount()));
        }
    }

    /**
     * doProcess_NMAL2460_NMAL2630
     * @param cMsg NMAL2460CMsg
     * @param sMsg NMAL2460SMsg
     */
    private void doProcess_NMAL2460_NMAL2630(NMAL2460CMsg cMsg, NMAL2460SMsg sMsg) {
        // Mod Start 2017/11/28 QC#21044
        //S21SsmEZDResult ssmResult = NMAL2460Query.getInstance().getTerritoryDetail(cMsg.xxPopPrm_02.getValue());
        S21SsmEZDResult ssmResult = NMAL2460Query.getInstance().getTerritoryDetail(cMsg.xxPopPrm_02.getValue(), cMsg.xxPopPrm_11.getValue());
        // Mod End 2017/11/28 QC#21044

        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(0);

            NMAL2460_BCMsg bcMsg = cMsg.B.no(cMsg.xxNum_EV.getValueInt());
            NMAL2460CommonLogic.setTerritoryDetail(bcMsg, resultMap, cMsg.asgTrtyAttrbCd.getValue());
        }
    }

    // QC#12193
    private boolean checkOrgTier(NMAL2460CMsg cMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.orgNm_H2)) {
            S21SsmEZDResult ssmResult = NMAL2460Query.getInstance().getOrgTier(cMsg.orgNm_H2.getValue());
            if (ssmResult.isCodeNormal()) {
                List<String> list = (List<String>) ssmResult.getResultObject();
                for (String orgTierCd : list) {
                    if (new Integer(orgTierCd) < MIN_SRCH_ORG_TIER) {
                        cMsg.orgNm_H2.setErrorInfo(1, NMAM8646E, new String[] {Integer.toString(MIN_SRCH_ORG_TIER) });
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
