/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLCL0250;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NLCL0250.common.NLCL0250CommonLogic;
import business.blap.NLCL0250.constant.NLCL0250Constant;
import business.file.NLCL0250F00FMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
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
 * Onhand Inventory Inquiry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/14/2015   CSAI            Y.Imazu         Create          N/A
 * 01/20/2016   CSAI            Y.Imazu         Update          QC#3134
 * 01/20/2016   CSAI            Y.Imazu         Update          QC#3137
 * 07/08/2016   CSAI            Y.Imazu         Update          QC#6610
 * 09/14/2016   CSAI            Y.Imazu         Update          QC#13187
 * 10/20/2016   CSAI            Y.Imazu         Update          QC#14081
 * 12/18/2017   CITS            S.Katsuma       Update          QC#22469
 * 03/01/2018   CITS            K.Ogino         Update          QC#24286
 * 03/20/2018   CITS            S.Katsuma       Update          QC#24715
 * 08/23/2018   CITS            K.Ogino         Update          QC#27696
 * 05/12/2020   Fujitsu         T.Ogura         Update          QC#56668
 *</pre>
 */
public class NLCL0250BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        cMsg.setCommitSMsg(true);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NLCL0250_INIT".equals(screenAplID)) {

                doProcess_NLCL0250_INIT((NLCL0250CMsg) cMsg, (NLCL0250SMsg) sMsg);
                getColData((NLCL0250CMsg) cMsg, (NLCL0250SMsg) sMsg);

            } else if ("NLCL0250Scrn00_Search".equals(screenAplID)) {

                doProcess_NLCL0250Scrn00_Search((NLCL0250CMsg) cMsg, (NLCL0250SMsg) sMsg);

            } else if ("NLCL0250Scrn00_CMN_Reset".equals(screenAplID)) {

                doProcess_NLCL0250Scrn00_CMN_Reset((NLCL0250CMsg) cMsg, (NLCL0250SMsg) sMsg);

            } else if ("NLCL0250Scrn00_CMN_Clear".equals(screenAplID)) {

                doProcess_NLCL0250Scrn00_CMN_Clear((NLCL0250CMsg) cMsg, (NLCL0250SMsg) sMsg);

            } else if ("NLCL0250Scrn00_Search_RtlWHInfo".equals(screenAplID)) {

                doProcess_NLCL0250Scrn00_Search_RtlWHInfo((NLCL0250CMsg) cMsg, (NLCL0250SMsg) sMsg);

            } else if ("NLCL0250Scrn00_Search_RtlSWHInfo".equals(screenAplID)) {

                doProcess_NLCL0250Scrn00_Search_RtlSWHInfo((NLCL0250CMsg) cMsg, (NLCL0250SMsg) sMsg);

            } else if ("NLCL0250Scrn00_Search_ShipToLocInfo".equals(screenAplID)) {

                doProcess_NLCL0250Scrn00_Search_ShipToLocInfo((NLCL0250CMsg) cMsg, (NLCL0250SMsg) sMsg);

            } else if ("NLCL0250Scrn00_PageNext".equals(screenAplID)) {

                doProcess_NLCL0250Scrn00_PageNext((NLCL0250CMsg) cMsg, (NLCL0250SMsg) sMsg);

            } else if ("NLCL0250Scrn00_PagePrev".equals(screenAplID)) {

                doProcess_NLCL0250Scrn00_PagePrev((NLCL0250CMsg) cMsg, (NLCL0250SMsg) sMsg);

            } else if ("NLCL0250Scrn00_PageJump".equals(screenAplID)) {

                doProcess_NLCL0250Scrn00_PageJump((NLCL0250CMsg) cMsg, (NLCL0250SMsg) sMsg);

            } else if ("NLCL0250Scrn00_CMN_Download".equals(screenAplID)) {

                doProcess_NLCL0250Scrn00_CMN_Download((NLCL0250CMsg) cMsg, (NLCL0250SMsg) sMsg);

            } else if ("NLCL0250Scrn00_TBLColumnSort".equals(screenAplID)) {

                doProcess_NLCL0250Scrn00_TBLColumnSort((NLCL0250CMsg) cMsg, (NLCL0250SMsg) sMsg);

            } else if ("NLCL0250Scrn00_OnChangeSavedSearchOption".equals(screenAplID)) {

                doProcess_NLCL0250_OnChangeSavedSearchOption((NLCL0250CMsg) cMsg, (NLCL0250SMsg) sMsg);

            } else if ("NLCL0250Scrn00_SaveSearch".equals(screenAplID)) {
                doProcess_NLCL0250Scrn00_SaveSearch((NLCL0250CMsg) cMsg, (NLCL0250SMsg) sMsg);

            } else if ("NLCL0250Scrn00_DeleteSearch".equals(screenAplID)) {
                doProcess_NLCL0250Scrn00_DeleteSearch((NLCL0250CMsg) cMsg, (NLCL0250SMsg) sMsg);

            } else if ("NLCL0250Scrn00_CMN_ColClear".equals(screenAplID)) {

                doProcess_NNLCL0250Scrn00_CMN_ColClear((NLCL0250CMsg) cMsg, (NLCL0250SMsg) sMsg);

            } else if ("NLCL0250Scrn00_CMN_ColSave".equals(screenAplID)) {

                doProcess_NLCL0250Scrn00_CMN_ColSave((NLCL0250CMsg) cMsg, (NLCL0250SMsg) sMsg);

            } else {

                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {

            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * doProcess_NLCL0250_INIT
     * @param cMsg NLCL0250CMsg
     * @param sMsg NLCL0250SMsg
     */
    private void doProcess_NLCL0250_INIT(NLCL0250CMsg cMsg, NLCL0250SMsg sMsg) {

        NLCL0250CommonLogic.clearAll(cMsg, sMsg);
        NLCL0250CommonLogic.createInitHeader(cMsg, getContextUserInfo().getUserId());

        if (0 < cMsg.S.getValidCount()) {

            cMsg.S.no(0).xxChkBox_SS.setValue(ZYPConstant.CHKBOX_ON_Y);
        }

        for (int i = 0; i < cMsg.L.getValidCount(); i++) {

            if (LOC_STS.DC_STOCK.equals(cMsg.L.no(i).locStsCd_LS.getValue())) {

                cMsg.L.no(i).xxChkBox_LS.setValue(ZYPConstant.CHKBOX_ON_Y);
            }
        }

        // Parameter Check
        if (isInitParamHasVal(cMsg)) {

            ZYPEZDItemValueSetter.setValue(cMsg.xxMdseSrchTxt_H1, cMsg.xxMdseSrchTxt_BK);
            ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCdSrchTxt_RW, cMsg.rtlWhCdSrchTxt_BR);
            ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCdSrchTxt_SW, cMsg.rtlWhNmSrchTxt_BS);
            ZYPEZDItemValueSetter.setValue(cMsg.xxSerNumSrchTxt_H1, cMsg.xxSerNumSrchTxt_BK);
            ZYPEZDItemValueSetter.setValue(cMsg.srchOptTxt_CF, cMsg.srchOptTxt_BK);

            // Search
            doProcess_NLCL0250Scrn00_Search(cMsg, sMsg);
        }
    }

    /**
     * isInitParamHasVal
     * @param cMsg NLCL0250CMsg
     * @return boolean
     */
    private boolean isInitParamHasVal(NLCL0250CMsg cMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.xxMdseSrchTxt_BK)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(cMsg.rtlWhCdSrchTxt_BR)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(cMsg.rtlWhNmSrchTxt_BS)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(cMsg.xxSerNumSrchTxt_BK)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(cMsg.srchOptTxt_BK)) {

            return true;
        }

        return false;
    }

    /**
     * doProcess_NLCL0250Scrn00_Search
     * @param cMsg NLCL0250CMsg
     * @param sMsg NLCL0250SMsg
     */
    private void doProcess_NLCL0250Scrn00_Search(NLCL0250CMsg cMsg, NLCL0250SMsg sMsg) {

        // Copy Search Condition as Backup
        NLCL0250CommonLogic.setSearchCondToBackUp(cMsg);

        // Master Check
        Map<String, ArrayList<String>> srchCondListMap = new HashMap<String, ArrayList<String>>();

        if (NLCL0250CommonLogic.isChkReqSrchCond(cMsg)) {

            srchCondListMap = NLCL0250CommonLogic.getSrchCondListMap(cMsg, false);

            if (srchCondListMap == null || srchCondListMap.isEmpty()) {

                return;
            }
        }

        // START 2017/12/18 S.Katsuma [QC#22469,ADD]
        if (ZYPCommonFunc.hasValue(cMsg.rtrnCtrlTpVndRelnPk_HB)) {
            if (!NLCL0250CommonLogic.getRtrnCtrlTp(cMsg)) {
                return;
            }
        } else {
            cMsg.rtrnCtrlTpCd_H1.clear();
            cMsg.rtrnCtrlTpNm_H1.clear();
            cMsg.prntVndCd_H1.clear();
            cMsg.prntVndNm_H1.clear();
            cMsg.vndCd_H1.clear();
            cMsg.vndNm_H1.clear();
            cMsg.xxScrItem500Txt_H1.clear();
        }
        // END 2017/12/18 S.Katsuma [QC#22469,ADD]

        // Clear Table
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);

        // Display Option Check
        if (isDtlSrchParamHasVal(cMsg)) {

            ZYPEZDItemValueSetter.setValue(cMsg.xxDplyCtrlFlg_H1, NLCL0250Constant.DETAIL_SEARCH);
            ZYPEZDItemValueSetter.setValue(cMsg.xxDplyCtrlFlg_HB, NLCL0250Constant.DETAIL_SEARCH);
        }

        S21SsmEZDResult result = null;
        Map<String, Object> queryParams = null;

        // Detail Search
        if (NLCL0250Constant.DETAIL_SEARCH.equals(cMsg.xxDplyCtrlFlg_HB.getValue())) {

            queryParams = NLCL0250CommonLogic.createSearchCondDtl(cMsg, sMsg.A.length(), srchCondListMap);

            if (queryParams == null) {

                ZYPTableUtil.clear(cMsg.A);
                ZYPTableUtil.clear(sMsg.A);
                cMsg.xxPageShowFromNum_A.clear();
                cMsg.xxPageShowToNum_A.clear();
                cMsg.xxPageShowOfNum_A.clear();
                cMsg.setMessageInfo(NLCL0250Constant.NZZM0000E);
                return;
            }

            // QC#24286 Start
            PreparedStatement preparedStatement = null;
            ResultSet rs = null;

            try {
                S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(NLCL0250Query.getInstance().getClass());
                String sqlID = "searchDtl";

                S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
                execParam.setFetchSize(1000);
                execParam.setMaxRows(0);
                preparedStatement = ssmLlcClient.createPreparedStatement(sqlID, queryParams, execParam);
                rs = preparedStatement.executeQuery();

                if (!rs.next()) {

                    ZYPTableUtil.clear(cMsg.A);
                    ZYPTableUtil.clear(sMsg.A);
                    cMsg.xxPageShowFromNum_A.clear();
                    cMsg.xxPageShowToNum_A.clear();
                    cMsg.xxPageShowOfNum_A.clear();
                    cMsg.setMessageInfo(NLCL0250Constant.NZZM0000E);
                    return;
                }

                NLCL0250CommonLogic.createDtlSrchTbl(rs, cMsg, sMsg);
                // QC#27696 Performance Issue.
                // START 2018/03/20 S.Katsuma [QC#24715,ADD]
                // Get MRP info for 8 digit Item
                // As for 8 digit Item, to reduce the load on the database, following processing.
                //NLCL0250CommonLogic.setMrpInfoForOrdTakeMdse(sMsg, queryParams, getGlobalCompanyCode());
                // END 2018/03/20 S.Katsuma [QC#24715,ADD]

                S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
                S21SortKey sortKey = new S21SortKey();
                sortKey.add("mdseCd_A1", "ASC");
                sortKey.add("rtlWhCd_A1", "ASC");
                sortKey.add("rtlSwhCd_A1", "ASC");
                sortKey.add("locStsCd_A1", "ASC");
                sortKey.add("agingCnt_A1", "DESC");
                sortKey.add("stkStsCd_A1", "ASC");
                S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

                int queryResCnt = sMsg.A.getValidCount();

                if (queryResCnt > sMsg.A.length() - 1) {

                    cMsg.setMessageInfo(NLCL0250Constant.NZZM0001W);
                    queryResCnt = sMsg.A.length() - 1;

                }

                // Copy from SMsg to cMsg
                int i = 0;

                for (; i < queryResCnt; i++) {

                    if (i == cMsg.A.length()) {

                        break;
                    }

                    EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
                }

                cMsg.A.setValidCount(i);

                // Set page number
                cMsg.xxPageShowFromNum_A.setValue(1);
                cMsg.xxPageShowToNum_A.setValue(cMsg.A.getValidCount());
                cMsg.xxPageShowOfNum_A.setValue(queryResCnt);
                cMsg.setMessageInfo(NLCL0250Constant.ZZZM9003I, new String[]{"Detail Search"});

            } catch (SQLException e) {
                throw new S21AbendException(e);
            } finally {
                S21SsmLowLevelCodingClient.closeResource(preparedStatement, rs);
            }

        // Summary Search
        } else {

            // QC#27696 Performance Issue Start.
            PreparedStatement ps = null;
            ResultSet rs = null;

            try {
            	queryParams = NLCL0250CommonLogic.createSearchCondSmry(cMsg, sMsg.A.length(), srchCondListMap);

                S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
                execParam.setFetchSize(sMsg.A.length() - 1);
                execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
                execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
                execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
                S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NLCL0250Query.getInstance().getClass());

                ps = ssmLLClient.createPreparedStatement("searchSmry", queryParams, execParam);
                rs = ps.executeQuery();

                int cnt = 0;
                if (!rs.next()) {
                    ZYPTableUtil.clear(cMsg.A);
                    ZYPTableUtil.clear(sMsg.A);
                    cMsg.xxPageShowFromNum_A.clear();
                    cMsg.xxPageShowToNum_A.clear();
                    cMsg.xxPageShowOfNum_A.clear();
                    cMsg.setMessageInfo(NLCL0250Constant.NZZM0000E);
                    return;
                }

                else {

                    do {
                        // set global
                        if (cnt > sMsg.A.length() - 1) {
                            cMsg.setMessageInfo(NLCL0250Constant.NZZM0001W);
                            cnt = sMsg.A.length();
                            break;
                        } else {
                            cMsg.setMessageInfo(NLCL0250Constant.ZZZM9003I, new String[] {"Summary Search" });
                        }

                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).mdseCd_A1, rs.getString("MDSE_CD"));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).mdseDescShortTxt_A1, rs.getString("MDSE_DESC_SHORT_TXT"));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).rtlWhCatgDescTxt_A1, rs.getString("RTL_WH_CATG_DESC_TXT"));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).rtlWhNm_A1, rs.getString("RTL_WH_NM"));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).rtlSwhCd_A1, rs.getString("RTL_SWH_CD"));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).ropQty_A1, rs.getBigDecimal("ROP_QTY"));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).maxInvtyQty_A1, rs.getBigDecimal("MAX_INVTY_QTY"));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).invtyQty_A1, rs.getBigDecimal("INVTY_QTY"));
                        // START 05/12/2020 T.Ogura [QC#56668,MOD]
//                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).invtyAvalQty_A1, rs.getBigDecimal("INVTY_AVAL_QTY"));
                        String locStsCd = "";
                        String locStsDescTxt = rs.getString("LOC_STS_DESC_TXT");
                        if (ZYPCommonFunc.hasValue(locStsDescTxt) && locStsDescTxt.length() >= 2) {
                            locStsCd = locStsDescTxt.substring(0, 2);
                        }
                        BigDecimal invtyAvalQty = rs.getBigDecimal("INVTY_AVAL_QTY");
                        if (NLCL0250CommonLogic.isAvalQtyHideLocSts(cMsg.glblCmpyCd.getValue(), locStsCd)) {
                            invtyAvalQty =BigDecimal.ZERO;
                        }
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).invtyAvalQty_A1, invtyAvalQty);
                        // END   05/12/2020 T.Ogura [QC#56668,MOD]
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).xxScrItem61Txt_AS, rs.getString("STK_STS_DESC_TXT"));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).xxScrItem61Txt_AL, rs.getString("LOC_STS_DESC_TXT"));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).rtlWhCd_A1, rs.getString("RTL_WH_CD"));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).xxScrItem61Txt_A5, rs.getString("COA_PROJ_LONG_DESC_TXT"));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).xxScrItem61Txt_A6, rs.getString("MDSE_ITEM_TP_LONG_DESC_TXT"));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).xxScrItem61Txt_A0, rs.getString("ZEROTH_PROD_CTRL_DESC_TXT"));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).xxScrItem61Txt_A1, rs.getString("FIRST_PROD_CTRL_DESC_TXT"));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).xxScrItem61Txt_A2, rs.getString("SCD_PROD_CTRL_DESC_TXT"));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).xxScrItem61Txt_A3, rs.getString("THIRD_PROD_CTRL_DESC_TXT"));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).xxScrItem61Txt_A4, rs.getString("FRTH_PROD_CTRL_DESC_TXT"));
                        cnt++;

                    } while (rs.next());

                    if (cnt > sMsg.A.length() - 1) {
                        cMsg.setMessageInfo(NLCL0250Constant.NZZM0001W);
                        cnt = sMsg.A.length() - 1;
                    }

                    sMsg.A.setValidCount(cnt);
                    // set Screen
                    int i = 0;
                    for (; i < sMsg.A.getValidCount(); i++) {
                        if (i == cMsg.A.length()) {
                            break;
                        }
                        EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
                    }

                    cMsg.A.setValidCount(i);

                    cMsg.xxPageShowFromNum_A.setValue(1);
                    cMsg.xxPageShowToNum_A.setValue(cMsg.A.getValidCount());
                    cMsg.xxPageShowOfNum_A.setValue(cnt);
                }
            } catch (SQLException e) {
                throw new S21AbendException(e);

            } finally {
                S21SsmLowLevelCodingClient.closeResource(ps, rs);
            }
        }
        // QC#27696 Performance Issue End.
    }

    /**
     * isDtlSrchParamHasVal
     * @param cMsg NLCL0250CMsg
     * @return boolean
     */
    private boolean isDtlSrchParamHasVal(NLCL0250CMsg cMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.xxSerNumSrchTxt_HB)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(cMsg.srchOptTxt_HB)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(cMsg.xxFldValTxt_BC)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(cMsg.xxFromDt_HB)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(cMsg.xxThruDt_HB)) {

            return true;
        }

        return false;
    }

    /**
     * doProcess_NLCL0250Scrn00_CMN_Reset
     * @param cMsg NLCL0250CMsg
     * @param sMsg NLCL0250SMsg
     */
    private void doProcess_NLCL0250Scrn00_CMN_Reset(NLCL0250CMsg cMsg, NLCL0250SMsg sMsg) {

        doProcess_NLCL0250_INIT(cMsg, sMsg);
        getColData((NLCL0250CMsg) cMsg, (NLCL0250SMsg) sMsg);
    }

    /**
     * doProcess_NLCL0250Scrn00_CMN_Clear
     * @param cMsg NLCL0250CMsg
     * @param sMsg NLCL0250SMsg
     */
    private void doProcess_NLCL0250Scrn00_CMN_Clear(NLCL0250CMsg cMsg, NLCL0250SMsg sMsg) {

        String col = cMsg.xxComnColOrdTxt.getValue();
        NLCL0250CommonLogic.clearAll(cMsg, sMsg);
        NLCL0250CommonLogic.createInitHeader(cMsg, getContextUserInfo().getUserId());

        if (0 < cMsg.S.getValidCount()) {

            cMsg.S.no(0).xxChkBox_SS.setValue(ZYPConstant.CHKBOX_ON_Y);
        }

        for (int i = 0; i < cMsg.L.getValidCount(); i++) {

            if (LOC_STS.DC_STOCK.equals(cMsg.L.no(i).locStsCd_LS.getValue())) {

                cMsg.L.no(i).xxChkBox_LS.setValue(ZYPConstant.CHKBOX_ON_Y);
            }
        }

        ZYPEZDItemValueSetter.setValue(cMsg.xxComnColOrdTxt, col);
    }

    /**
     * doProcess_NLCL0250Scrn00_Search_RtlWHInfo
     * @param cMsg NLCL0250CMsg
     * @param sMsg NLCL0250SMsg
     */
    private void doProcess_NLCL0250Scrn00_Search_RtlWHInfo(NLCL0250CMsg cMsg, NLCL0250SMsg sMsg) {

        NLCL0250CommonLogic.getRtlWhCdList(cMsg, cMsg.rtlWhCdSrchTxt_RW, false);
    }

    /**
     * doProcess_NLCL0250Scrn00_Search_RtlSWHInfo
     * @param cMsg NLCL0250CMsg
     * @param sMsg NLCL0250SMsg
     */
    private void doProcess_NLCL0250Scrn00_Search_RtlSWHInfo(NLCL0250CMsg cMsg, NLCL0250SMsg sMsg) {

        NLCL0250CommonLogic.getSwhCdList(cMsg, cMsg.rtlWhCdSrchTxt_SW, false);
    }

    /**
     * doProcess_NLCL0250Scrn00_Search_ShipToLocInfo
     * @param cMsg NLCL0250CMsg
     * @param sMsg NLCL0250SMsg
     */
    private void doProcess_NLCL0250Scrn00_Search_ShipToLocInfo(NLCL0250CMsg cMsg, NLCL0250SMsg sMsg) {

        NLCL0250CommonLogic.getToLocCdList(cMsg, cMsg.xxFldValTxt_HC, false);
    }

    /**
     * doProcess_NLCL0250Scrn00_PageNext
     * @param cMsg NLCL0250CMsg
     * @param sMsg NLCL0250SMsg
     */
    private void doProcess_NLCL0250Scrn00_PageNext(NLCL0250CMsg cMsg, NLCL0250SMsg sMsg) {

        //copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum_A.getValueInt();
        int i = pagenationFrom;

        for (; i < pagenationFrom + cMsg.A.length(); i++) {

            if (i < sMsg.A.getValidCount()) {

                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);

            } else {

                break;
            }
        }

        cMsg.A.setValidCount(i - pagenationFrom);

        //set value to page items
        cMsg.xxPageShowFromNum_A.setValue(pagenationFrom + 1);
        cMsg.xxPageShowToNum_A.setValue(pagenationFrom + cMsg.A.getValidCount());
    }

    /**
     * doProcess_NLCL0250Scrn00_PagePrev
     * @param cMsg NLCL0250CMsg
     * @param sMsg NLCL0250SMsg
     */
    private void doProcess_NLCL0250Scrn00_PagePrev(NLCL0250CMsg cMsg, NLCL0250SMsg sMsg) {

        //copy data from SMsg to CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum_A.getValueInt();
        int i = pagenationFrom;

        for (; i < pagenationFrom + cMsg.A.length(); i++) {

            if (i < sMsg.A.getValidCount()) {

                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);

            } else {

                break;
            }
        }

        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to page items
        pagenationFrom = pagenationFrom + 1;
        cMsg.xxPageShowFromNum_A.setValue(pagenationFrom);
        cMsg.xxPageShowToNum_A.setValue(pagenationFrom + cMsg.A.getValidCount() - 1);
    }

    /**
     * doProcess_NLCL0250Scrn00_PageJump
     * @param cMsg NLCL0250CMsg
     * @param sMsg NLCL0250SMsg
     */
    private void doProcess_NLCL0250Scrn00_PageJump(NLCL0250CMsg cMsg, NLCL0250SMsg sMsg) {

        //copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum_A.getValueInt() - 1;
        int i = pagenationFrom;

        for (; i < pagenationFrom + cMsg.A.length(); i++) {

            if (i < sMsg.A.getValidCount()) {

                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);

            } else {

                break;
            }
        }

        cMsg.A.setValidCount(i - pagenationFrom);

        //set value to page items
        cMsg.xxPageShowToNum_A.setValue(pagenationFrom + cMsg.A.getValidCount());
    }

    /**
     * doProcess_NLCL0250_OnChangeSavedSearchOption
     * @param cMsg NLCL0250CMsg
     * @param sMsg NLCL0250SMsg
     */
    private void doProcess_NLCL0250_OnChangeSavedSearchOption(NLCL0250CMsg cMsg, NLCL0250SMsg sMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.srchOptPk_S)) {

            NLCL0250CommonLogic.callNszc0330forSearchOption(cMsg, sMsg, getContextUserInfo().getUserId());
        }
    }

    /**
     * doProcess_NLCL0250Scrn00_CMN_Download
     * @param cMsg NLCL0250CMsg
     * @param sMsg NLCL0250SMsg
     */
    private void doProcess_NLCL0250Scrn00_CMN_Download(NLCL0250CMsg cMsg, NLCL0250SMsg sMsg) {

        if (0 == cMsg.A.getValidCount()) {

            // Copy Search Condition as Backup
            NLCL0250CommonLogic.setSearchCondToBackUp(cMsg);
        }

        // Master Check
        Map<String, ArrayList<String>> srchCondListMap = new HashMap<String, ArrayList<String>>();

        if (NLCL0250CommonLogic.isChkReqSrchCond(cMsg)) {

            srchCondListMap = NLCL0250CommonLogic.getSrchCondListMap(cMsg, true);

            if (srchCondListMap == null || srchCondListMap.isEmpty()) {

                return;
            }
        }

        // Display Option Check
        if (isDtlSrchParamHasVal(cMsg)) {

            ZYPEZDItemValueSetter.setValue(cMsg.xxDplyCtrlFlg_H1, NLCL0250Constant.DETAIL_SEARCH);
            ZYPEZDItemValueSetter.setValue(cMsg.xxDplyCtrlFlg_HB, NLCL0250Constant.DETAIL_SEARCH);
        }

        ResultSet rs = null;
        PreparedStatement ps = null;

        try {

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(NLCL0250Constant.MAX_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NLCL0250Query.getInstance().getClass());

            //create csv file, parameters
            Map<String, Object> queryParams = null;

            if (NLCL0250Constant.DETAIL_SEARCH.equals(cMsg.xxDplyCtrlFlg_HB.getValue())) {

                queryParams = NLCL0250CommonLogic.createSearchCondDtl(cMsg, NLCL0250Constant.LIMIT_DL_ROWNUM + 1, srchCondListMap);

                if (queryParams == null) {

                    cMsg.setMessageInfo(NLCL0250Constant.NZZM0000E, null);

                } else {

                    cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(NLCL0250Constant.CSV_FILE_DTL_NAME), NLCL0250Constant.CSV_FILE_EXTENSION);
                    ps = ssmLLClient.createPreparedStatement("searchDtl", queryParams, execParam);
                    rs = ps.executeQuery();
                    // START 2018/03/20 S.Katsuma [QC#24715,MOD]
                    writeCsvFileForDtlAge(cMsg, rs, queryParams);
                    // END 2018/03/20 S.Katsuma [QC#24715,MOD]
                }

            } else {

                queryParams = NLCL0250CommonLogic.createSearchCondSmry(cMsg, NLCL0250Constant.LIMIT_DL_ROWNUM + 1, srchCondListMap);
                cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(NLCL0250Constant.CSV_FILE_SMRY_NAME), NLCL0250Constant.CSV_FILE_EXTENSION);
                ps = ssmLLClient.createPreparedStatement("searchSmry", queryParams, execParam);
                rs = ps.executeQuery();
                // START 2018/03/20 S.Katsuma [QC#24715,MOD]
                writeCsvFile(cMsg, rs, queryParams);
                // END 2018/03/20 S.Katsuma [QC#24715,MOD]
            }

        } catch (SQLException e) {

            throw new S21AbendException(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    /**
     * doProcess_NLCL0250Scrn00_TBLColumnSort
     * @param cMsg NLCL0250CMsg
     * @param sMsg NLCL0250SMsg
     */
    private void doProcess_NLCL0250Scrn00_TBLColumnSort(NLCL0250CMsg cMsg, NLCL0250SMsg sMsg) {

        String sortTblNm = cMsg.xxSortTblNm_A.getValue();
        String sortItemNm = cMsg.xxSortItemNm_A.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt_A.getValue();

        if (NLCL0250Constant.TABLE_A.equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

            // copy(SMsg -> CMsg)
            int i = 0;

            for (; i < sMsg.A.getValidCount(); i++) {

                if (i == cMsg.A.length()) {

                    break;
                }

                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }

            cMsg.A.setValidCount(i);

            //set page no 
            cMsg.xxPageShowFromNum_A.setValue(1);
            cMsg.xxPageShowToNum_A.setValue(cMsg.A.getValidCount());
        }
    }

    /**
     * doProcess_NLCL0250Scrn00_SaveSearch
     * @param cMsg NLCL0250CMsg
     * @param sMsg NLCL0250SMsg
     */
    private void doProcess_NLCL0250Scrn00_SaveSearch(NLCL0250CMsg cMsg, NLCL0250SMsg sMsg) {

        // There is no process.
        return;
    }

    /**
     * doProcess_NLCL0250Scrn00_DeleteSearch
     * @param cMsg NLCL0250CMsg
     * @param sMsg NLCL0250SMsg
     */
    private void doProcess_NLCL0250Scrn00_DeleteSearch(NLCL0250CMsg cMsg, NLCL0250SMsg sMsg) {

        // There is no process.
        return;
    }

    /**
     * doProcess_NNLCL0250Scrn00_CMN_ColClear
     * @param cMsg NLCL0250CMsg
     * @param sMsg NLCL0250SMsg
     */
    private void doProcess_NNLCL0250Scrn00_CMN_ColClear(NLCL0250CMsg cMsg, NLCL0250SMsg sMsg) {

        // There is no process.
        return;
    }

    /**
     * doProcess_NLCL0250Scrn00_CMN_ColSave
     * @param cMsg NLCL0250CMsg
     * @param sMsg NLCL0250SMsg
     */
    private void doProcess_NLCL0250Scrn00_CMN_ColSave(NLCL0250CMsg cMsg, NLCL0250SMsg sMsg) {

        // There is no process.
        return;
    }

    /**
     * getColData
     * @param cMsg NLCL0250CMsg
     * @param sMsg NLCL0250SMsg
     */
    private static void getColData(NLCL0250CMsg cMsg, NLCL0250SMsg sMsg) {

        ZYPGUITableColumn.getColData((NLCL0250CMsg) cMsg, (NLCL0250SMsg) sMsg, NLCL0250Constant.TABLE_A_HEAD);
    }

    /**
     * writeCsvFile
     * @param cMsg NLCL0250CMsg
     * @param rs ResultSet
     * @throws SQLException
     */
    private void writeCsvFile(NLCL0250CMsg cMsg, ResultSet rs, Map<String, Object> queryParams) throws SQLException {

        NLCL0250F00FMsg fMsg = new NLCL0250F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);
        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(cMsg));

        //write header
        csvOutFile.writeHeader(NLCL0250Constant.CSV_HDR, fMsg, ZYPGUITableColumn.getColOrder(cMsg));

        if (!rs.next()) {

            cMsg.setMessageInfo(NLCL0250Constant.NZZM0000E, null);
            csvOutFile.close();
            return;
        }

        // START 2018/03/20 S.Katsuma [QC#24715,ADD]
        List<Map<String, Object>> mrpInfoMap = NLCL0250CommonLogic.getMrpInfoForOrdTakeMdse(queryParams, getGlobalCompanyCode());
        Map<Map<String, Object>, Integer> searchMap = NLCL0250CommonLogic.setSearchMapForMrpInfo(mrpInfoMap);
        // END 2018/03/20 S.Katsuma [QC#24715,ADD]

        //write contents
        do {

            if (rs.getRow() >= NLCL0250Constant.LIMIT_DL_ROWNUM) {

                cMsg.setMessageInfo(NLCL0250Constant.NZZM0001W, null);
                break;
            }

            //resultSet -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.mdseCd_A1, rs.getString("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.mdseDescShortTxt_A1, rs.getString("MDSE_DESC_SHORT_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlWhCatgDescTxt_A1, rs.getString("RTL_WH_CATG_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlWhNm_A1, rs.getString("RTL_WH_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlSwhCd_A1, rs.getString("RTL_SWH_CD"));
            // START 2018/03/20 S.Katsuma [QC#24715,ADD]
            NLCL0250CommonLogic.setMrpInfoForOrdTakeMdse(fMsg, rs, mrpInfoMap, searchMap);
            // END 2018/03/20 S.Katsuma [QC#24715,MOD]
            ZYPEZDItemValueSetter.setValue(fMsg.invtyQty_A1, rs.getBigDecimal("INVTY_QTY"));
            // START 05/12/2020 T.Ogura [QC#56668,MOD]
//            ZYPEZDItemValueSetter.setValue(fMsg.invtyAvalQty_A1, rs.getBigDecimal("INVTY_AVAL_QTY"));
            String locStsCd = "";
            String locStsDescTxt = rs.getString("LOC_STS_DESC_TXT");
            if (ZYPCommonFunc.hasValue(locStsDescTxt) && locStsDescTxt.length() >= 2) {
                locStsCd = locStsDescTxt.substring(0, 2);
            }
            BigDecimal invtyAvalQty = rs.getBigDecimal("INVTY_AVAL_QTY");
            if (NLCL0250CommonLogic.isAvalQtyHideLocSts(cMsg.glblCmpyCd.getValue(), locStsCd)) {
                invtyAvalQty =BigDecimal.ZERO;
            }
            ZYPEZDItemValueSetter.setValue(fMsg.invtyAvalQty_A1, invtyAvalQty);
            // END   05/12/2020 T.Ogura [QC#56668,MOD]
            ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem61Txt_AS, rs.getString("STK_STS_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem61Txt_AL, rs.getString("LOC_STS_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlWhCd_A1, rs.getString("RTL_WH_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem61Txt_A5, rs.getString("COA_PROJ_LONG_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem61Txt_A6, rs.getString("MDSE_ITEM_TP_LONG_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem61Txt_A0, rs.getString("ZEROTH_PROD_CTRL_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem61Txt_A1, rs.getString("FIRST_PROD_CTRL_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem61Txt_A2, rs.getString("SCD_PROD_CTRL_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem61Txt_A3, rs.getString("THIRD_PROD_CTRL_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem61Txt_A4, rs.getString("FRTH_PROD_CTRL_DESC_TXT"));

            if (NLCL0250Constant.DETAIL_SEARCH.equals(cMsg.xxDplyCtrlFlg_HB.getValue())) {

                ZYPEZDItemValueSetter.setValue(fMsg.serNum_A1, rs.getString("SER_NUM"));
                ZYPEZDItemValueSetter.setValue(fMsg.svcConfigMstrPk_A1, rs.getBigDecimal("SVC_CONFIG_MSTR_PK"));
                ZYPEZDItemValueSetter.setValue(fMsg.agingCnt_A1, rs.getBigDecimal("AGING_CNT"));
                ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNm_A1, rs.getString("SHIP_FROM_ACCT_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.shipToAcctNm_A1, rs.getString("SHIP_TO_ACCT_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.sceOrdTpNm_A1, rs.getString("SCE_ORD_TP_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.trxHdrNum_A1, rs.getString("TRX_HDR_NUM"));
                ZYPEZDItemValueSetter.setValue(fMsg.dplyLineNum_A1, rs.getString("DPLY_LINE_NUM"));
                ZYPEZDItemValueSetter.setValue(fMsg.mainMachFlg_A1, rs.getString("MACH_FLG"));
                ZYPEZDItemValueSetter.setValue(fMsg.serNumFlg_A1, rs.getString("SER_NUM_FLG"));
            }

            csvOutFile.write();

        } while (rs.next());

        csvOutFile.close();
    }

    /**
     * writeCsvFileForDtlAge
     * @param cMsg NLCL0250CMsg
     * @param rs ResultSet
     * @throws SQLException
     */
    private void writeCsvFileForDtlAge(NLCL0250CMsg cMsg, ResultSet rs, Map<String, Object> queryParams) throws SQLException {

        NLCL0250F00FMsg fMsg = new NLCL0250F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);
        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(cMsg));

        //write header
        csvOutFile.writeHeader(NLCL0250Constant.CSV_HDR, fMsg, ZYPGUITableColumn.getColOrder(cMsg));

        if (!rs.next()) {

            cMsg.setMessageInfo(NLCL0250Constant.NZZM0000E, null);
            csvOutFile.close();
            return;
        }

        Map<String, BigDecimal> qtyChkMap = new HashMap<String, BigDecimal>();
        int indexFMsg = 0;

        // START 2018/03/20 S.Katsuma [QC#24715,ADD]
        List<Map<String, Object>> mrpInfoMap = NLCL0250CommonLogic.getMrpInfoForOrdTakeMdse(queryParams, getGlobalCompanyCode());
        Map<Map<String, Object>, Integer> searchMap = NLCL0250CommonLogic.setSearchMapForMrpInfo(mrpInfoMap);
        // END 2018/03/20 S.Katsuma [QC#24715,ADD]

        //write contents
        do {

            if (indexFMsg == NLCL0250Constant.LIMIT_DL_ROWNUM) {

                cMsg.setMessageInfo(NLCL0250Constant.NZZM0001W, null);
                break;
            }

            BigDecimal invtyAvalQty = rs.getBigDecimal("INVTY_AVAL_QTY");

            // START 05/12/2020 T.Ogura [QC#56668,ADD]
            if (NLCL0250CommonLogic.isAvalQtyHideLocSts(cMsg.glblCmpyCd.getValue(), rs.getString("LOC_STS_CD"))) {
                invtyAvalQty =BigDecimal.ZERO;
            }
            // END   05/12/2020 T.Ogura [QC#56668,ADD]

            // Not DC Stock
            if (!LOC_STS.DC_STOCK.equals(rs.getString("LOC_STS_CD"))) {

                NLCL0250CommonLogic.setCommonSrchRsltToFMsg(fMsg, rs, invtyAvalQty);
                csvOutFile.write();
                indexFMsg++;
                continue;

            } else if (BigDecimal.ZERO.compareTo(invtyAvalQty) == 0) {

                NLCL0250CommonLogic.setCommonSrchRsltToFMsg(fMsg, rs, invtyAvalQty);
                csvOutFile.write();
                indexFMsg++;
                continue;
            }

            String mdseCd = rs.getString("MDSE_CD");
            String invtylocCd = rs.getString("INVTY_LOC_CD");
            String stkStsCd = rs.getString("STK_STS_CD");
            BigDecimal invtyQty = rs.getBigDecimal("INVTY_QTY");
            String qtyChkKey = ZYPCommonFunc.concatString(ZYPCommonFunc.concatString(mdseCd, ":", invtylocCd), ":", stkStsCd);

            BigDecimal rmngInvtyAvalQty = qtyChkMap.get(qtyChkKey);

            if (rmngInvtyAvalQty == null) {

                rmngInvtyAvalQty = invtyAvalQty;
            }
            // QC#25726
            if (ZYPCommonFunc.hasValue(rs.getBigDecimal("SVC_CONFIG_MSTR_PK"))) {

                if (ZYPCommonFunc.hasValue(rs.getString("DPLY_LINE_NUM"))) {
                    invtyQty = BigDecimal.ZERO;
                } else {
                    invtyQty = BigDecimal.ONE;
                }

                NLCL0250CommonLogic.setCommonSrchRsltToFMsg(fMsg, rs, invtyQty);
                qtyChkMap.put(qtyChkKey, rmngInvtyAvalQty.subtract(invtyQty));
                csvOutFile.write();
                indexFMsg++;
            }  else if (ZYPCommonFunc.hasValue(rs.getBigDecimal("SVC_MACH_MSTR_PK"))) {

                if (ZYPCommonFunc.hasValue(rs.getString("DPLY_LINE_NUM"))) {
                    invtyQty = BigDecimal.ZERO;
                } else {
                    invtyQty = BigDecimal.ONE;
                }

                NLCL0250CommonLogic.setCommonSrchRsltToFMsg(fMsg, rs, invtyQty);
                qtyChkMap.put(qtyChkKey, rmngInvtyAvalQty.subtract(invtyQty));
                csvOutFile.write();
                indexFMsg++;
            } else if (BigDecimal.ZERO.compareTo(rmngInvtyAvalQty) >= 0) {

                NLCL0250CommonLogic.setCommonSrchRsltToFMsg(fMsg, rs, BigDecimal.ZERO);
                csvOutFile.write();
                indexFMsg++;

            } else if (rmngInvtyAvalQty.compareTo(invtyQty) > 0) {

                NLCL0250CommonLogic.setCommonSrchRsltToFMsg(fMsg, rs, invtyQty);
                qtyChkMap.put(qtyChkKey, rmngInvtyAvalQty.subtract(invtyQty));
                csvOutFile.write();
                indexFMsg++;

            } else {

                NLCL0250CommonLogic.setCommonSrchRsltToFMsg(fMsg, rs, rmngInvtyAvalQty);
                qtyChkMap.put(qtyChkKey, BigDecimal.ZERO);
                csvOutFile.write();
                indexFMsg++;
            }

        } while (rs.next());

        if (indexFMsg == 0) {

            cMsg.setMessageInfo(NLCL0250Constant.NZZM0000E, null);
        }

        csvOutFile.close();
    }
}
