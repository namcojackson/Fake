/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1570;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCStringItem;
import parts.common.EZDMsg;

import static business.blap.NWAL1570.constant.NWAL1570Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.concatString;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static parts.common.EZDCommonFunc.trimTail;


import business.blap.NWAL1570.constant.NWAL1570Constant;
import business.blap.NWAL1570.constant.NWAL1570Constant.HDR_STS;
import business.blap.NWAL1570.constant.NWAL1570Constant.LINE_STS;
import business.file.NWAL1570F00FMsg;
import business.file.NWAL1570F01FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_TEAM_ATTRB_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelColumnStyle;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.util.S21StopWatch;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.common.S21SsmEZDOracleJDBCUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmVoidResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NWAL1570Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/02   Fujitsu         K.Sato          Create          N/A
 * 2016/07/25   Fujitsu         K.Sato          Update          QC#11956
 * 2016/07/26   Fujitsu         K.Sato          Update          QC#11581
 * 2016/09/27   Fujitsu         K.Sato          Update          QC#13415
 * 2016/10/18   Fujitsu         K.Sato          Update          QC#15164
 * 2016/11/22   Fujitsu         K.Sato          Update          QC#15760
 * 2017/06/14   Fujitsu         S.Ohki          Update          QC#19117
 * 2017/06/22   Fujitsu         H.Sugawara      Update          QC#17893
 * 2017/09/21   Fujitsu         A.Kosai         Update          QC#21086
 * 2017/10/2    Fujitsu         T.Aoi           Update          QC#19913
 * 2017/11/21   Fujitsu         T.Aoi           Update          QC#22550
 * 2018/02/19   Fujitsu         K.Ishizuka      Update          QC#19913-2
 * 2018/02/22   Fujitsu         T.Aoi           Update          QC#21611
 * 2018/05/21   Fujitsu         M.Ohno          Update          S21_NA#23523
 * 2018/06/05   Fujitsu         H.Tomimatsu     Update          QC#24816
 * 2018/07/05   Fujitsu         M.Ishii         Update          QC#25786
 * 2018/08/01   Fujitsu         T.Aoi           Update          QC#26304
 * 2018/08/13   Fujitsu         S.Yamamoto      Update          QC#22893
 * 2018/10/09   Fujitsu         Mz.Takahashi    Update          QC#28696
 * 2018/10/11   Fujitsu         S.Kosaka        Update          QC#28601
 * 2018/12/06   Fujitsu         Mz.Takahash     Update          QC#29024 RollBack QC#22893
 * 2018/12/14   Fujitsu         Mz.Takahash     Update          QC#29286
 * 2019/02/13   Fujitsu         Mz.Takahash     Update          QC#30238
 * 2019/03/29   Fujitsu         Mz.Takahash     Update          QC#31001
 * 2019/04/03   Fujitsu         K.Kato          Update          QC#30756
 * 2019/05/07   Fujitsu         Mz.Takahash     Update          QC#50031
 * 2019/05/24   Fujitsu         R.Nakamura      Update          QC#50325
 * 2019/12/09   Fujitsu         Y.Kanefusa      Update          S21_NA#54228
 * 2020/01/14   CITS            M.Furugoori     Update          QC#55353
 *</pre>
 */
public final class NWAL1570Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NWAL1570Query MY_INSTANCE = new NWAL1570Query();

    /**
     * Private constructor
     */
    private NWAL1570Query() {
        super();
    }

    /**
     * Get the NWAL1570Query instance.
     * @return NWAL1570Query instance
     */
    public static NWAL1570Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Search
     * @param bizMsg NWAL1570CMsg
     * @param glblMsg NWAL1570SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(NWAL1570CMsg bizMsg, NWAL1570SMsg glblMsg) {
      Map<String, Object> params = new HashMap<String, Object>();

      params.put("glblCmpyCd", getGlobalCompanyCode());

      return getSsmEZDClient().queryEZDMsgArray("getSampleToSMsg", params, glblMsg.A);
    }

    /**
     * find order list
     *
     * <pre>
     * statementId = "findOrderList"
     * </pre>
     *
     * @param cMsg NWAL1570CMsg
     * @param sMsg NWAL1570SMsg
     * @param sMsgArraySize use as sql rownum value.
     * @param reSearchFlg String
     */
    public void findOrderList(NWAL1570CMsg cMsg, NWAL1570SMsg sMsg, int sMsgArraySize, String reSearchFlg) {
        findOrderList(cMsg, sMsg, sMsgArraySize, N, reSearchFlg);
    }

    /**
     * find order list
     * to download csv file.
     *
     * <pre>
     * statementId = "findOrderList"
     * </pre>
     *
     * @param cMsg NWAL1570CMsg
     * @param reSearchFlg String
     */
    public void findOrderListForCsvDnld(NWAL1570CMsg cMsg, String reSearchFlg) {
        findOrderList(cMsg, null, LIMIT_DL_ROWNUM, Y, reSearchFlg);
    }

    /**
     * find order list
     * and "INV".
     *
     * <pre>
     * statementId = "findOrderList"
     * </pre>
     *
     * @param cMsg NWAL1570CMsg
     * @param sMsg NWAL1570SMsg
     * @param maxSize use as sql rownum value.
     * @param csvDnldFlg use for create SQL for CSV DOWNLOAD
     * @param reSearchFlg String
     */
    private void findOrderList(final NWAL1570CMsg cMsg, final NWAL1570SMsg sMsg, final int maxSize, String csvDnldFlg, final String reSearchFlg) {
        S21InfoLogOutput.println("=== NWAL1570Query.setFindOrderList : start ===");

        // 2019/02/13 QC#30238 Add Start
        S21StopWatch sw = new S21StopWatch();
        sw.start();
        // 2019/02/13 QC#30238 Add End

        final Map<String, Serializable> param = createSsmParam(cMsg, maxSize, csvDnldFlg, reSearchFlg);

        if ("E".equals(cMsg.getMessageKind())) {
            // 2019/02/13 QC#30238 Add Start
            sw.stop();
            // 2019/02/13 QC#30238 Add End
            return;
        }

        S21SsmExecutionParameter rsParam = new S21SsmExecutionParameter();
        rsParam.setFetchSize(FETCH_SIZE_MAX);
        rsParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        rsParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        rsParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        if (Y.equals(csvDnldFlg)) {
            getSsmBatchClient().queryObject("findOrderList", param, rsParam, new CSVResultSetHandler(cMsg));
        } else {
            getSsmBatchClient().queryObject("findOrderList", param, rsParam, new ScreenResultSetHandler(cMsg, sMsg));
        }

        // 2019/02/13 QC#30238 Add Start
        sw.stop();

        String criteria_realtime;
        if (Y.equals(cMsg.xxPgFlg.getValue())) {
            criteria_realtime = "REAL";
        } else {
            criteria_realtime = "NON_REAL";
        }

        S21InfoLogOutput.println(
                String.format("@@NWAL1570 searchCondition@@ |%s|%s|%s|%s|%s|%s|%d"
                        , criteria_realtime
                        , getContextUserInfo().getUserId()
                        , param.get("ORDER_NUMBER")
                        , param.get("searchCondition_OpenOrder")
                        , param.get("searchQuickCondition")
                        , param.get("searchCondition_PendingImportOrder")
                        , sw.getElapsedMilliSec()));
        // 2019/02/13 QC#30238 Add End

        S21InfoLogOutput.println("=== NWAL1570Query.setFindOrderList : end ===");
    }

    private S21SsmBatchClient getSsmBatchClient() {
        return S21SsmBatchClient.getClient(this.getClass());
    }

    private Map<String, Serializable> createSsmParam(final NWAL1570CMsg cMsg, final int maxSize, final String csvDnldFlg, final String reSearchFlg) {

        final Map<String, Serializable> param = new HashMap<String, Serializable>();

        param.put("glblCmpyCd", getGlobalCompanyCode());
        param.put("cMsg", cMsg);
        param.put("rowNum", maxSize + 1);
        param.put("ReSearchFlg", reSearchFlg);

        if (hasValue(cMsg.pairDplyByItemNm_01)) {
            param.put("pairDplyByItemNm_01", cMsg.pairDplyByItemNm_01.getValue());
        }
        if (hasValue(cMsg.pairDplyByItemNm_02)) {
            param.put("pairDplyByItemNm_02", cMsg.pairDplyByItemNm_02.getValue());
        }
        if (hasValue(cMsg.pairDplyByItemNm_03)) {
            param.put("pairDplyByItemNm_03", cMsg.pairDplyByItemNm_03.getValue());
        }

        // ------------------------
        // ** Constant Params
        // ------------------------
        setConstantParam(param);

        // ------------------------------
        // ** Create SQL For CSV Upload
        // ------------------------------
        if (Y.equals(csvDnldFlg)) {
            param.put("csvDnldFlg", Y);

            // --------------------------------
            // ** Create SQL For Search Screen
            // --------------------------------
        } else {
            param.put("csvDnldFlg", N);
        }

        // --------------------------
        // ** Create Search Condition
        // --------------------------
        setSearchCondition(cMsg, param);

        return param;
    }

    /**
     * ScreenResultSetHandler
     *
     * <pre>
     * ScreenResultSetHandler
     * </pre>
     * @param cMsg NWAL1570CMsg
     */
    private class ScreenResultSetHandler extends CommonResultSetHandler {

        /**
         * NWAL1570CMsg
         */
        private NWAL1570CMsg cMsg = null;

        /**
         * NWAL1570SMsg
         */
        private NWAL1570SMsg sMsg = null;

        public ScreenResultSetHandler(NWAL1570CMsg cMsg, NWAL1570SMsg sMsg) {
            this.cMsg = cMsg;
            this.sMsg = sMsg;
        }

        @Override
        protected void doProcessQueryResult(ResultSet result) throws SQLException {
            S21InfoLogOutput.println("=== NWAL1570Query.ScreenResultSetHandler.doProcessQueryResult : start ===");

            if (!result.next()) {
                cMsg.setMessageInfo("NZZM0000E");
                return;
            }

            ZYPTableUtil.clear(sMsg.A);

            List<String> column = getColumnList(result);

            // data copy to FMsg & write csv record.
            int index = 0;
            Map<String, BigDecimal> groupingMap = new HashMap<String, BigDecimal>();
            groupingMap.put(KEY_GRP_ID_01, BigDecimal.ZERO);
            groupingMap.put(KEY_GRP_ID_02, BigDecimal.ZERO);
            groupingMap.put(KEY_GRP_ID_03, BigDecimal.ZERO);

            boolean prevFlag = false;
            while (true) {

                if (index >= sMsg.A.length()) {
                    cMsg.setMessageInfo("NZZM0001W");
                    break;
                }

                Map<String, Object> record = getResultMap(cMsg, column, result);

                mapResultToSMsg(record, sMsg, sMsg.A.no(index), index, groupingMap);

                if (index < cMsg.A.length()) {
                    EZDMsg.copy(sMsg.A.no(index), null, cMsg.A.no(index), null);
                    cMsg.A.setValidCount(index + 1);
                }

                index++;
                if (prevFlag == true) {
                    prevFlag = false;
                    continue;
                }
                if (result.next() == false) { break; }
            }

            sMsg.A.setValidCount(index);

            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());

            S21InfoLogOutput.println("=== NWAL1570Query.ScreenResultSetHandler.doProcessQueryResult : end ===");
        }

        private void mapResultToSMsg(Map<String, Object> record, NWAL1570SMsg sMsg, NWAL1570_ASMsg asMsg, int index, Map<String, BigDecimal> groupingMap) {

            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // + mapping : Display Fields
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            getDplyByItemNm(sMsg, index, record, asMsg, groupingMap);

            String dsOrdPosnNum = getString(record, SQL_COL.DS_ORD_POSN_NUM);
            String dsCpoDtlLineNum = "";
            if (hasValue(getBigDecimal(record, SQL_COL.DS_CPO_LINE_NUM))) {
                dsCpoDtlLineNum = getBigDecimal(record, SQL_COL.DS_CPO_LINE_NUM).toPlainString();
            }
            String dsCpoDtlLineSubNum = "";
            if (hasValue(getBigDecimal(record, SQL_COL.DS_CPO_LINE_SUB_NUM))) {
                dsCpoDtlLineSubNum = getBigDecimal(record, SQL_COL.DS_CPO_LINE_SUB_NUM).toPlainString();
            }

            String dplyOrdLineNum = "";
            if (hasValue(dsOrdPosnNum)) {
                dplyOrdLineNum = dsOrdPosnNum;
            }
            if (hasValue(dsCpoDtlLineNum)) {
                dplyOrdLineNum = concatString(dplyOrdLineNum, PERIOD, dsCpoDtlLineNum);
            }
            if (hasValue(dsCpoDtlLineSubNum)) {
                dplyOrdLineNum = concatString(dplyOrdLineNum, PERIOD, dsCpoDtlLineSubNum);
            }
            setValue(asMsg.xxDplyOrdLineNum_A1, dplyOrdLineNum);

            setValue(asMsg.srcRefOrCpoOrdNum_A1, getString(record, SQL_COL.SRC_REF_OR_CPO_ORD_NUM));
            setValue(asMsg.dsOrdPosnNum_A1, getString(record, SQL_COL.DS_ORD_POSN_NUM));
            setValue(asMsg.dsCpoLineNum_A1, getBigDecimal(record, SQL_COL.DS_CPO_LINE_NUM));
            setValue(asMsg.dsCpoLineSubNum_A1, getBigDecimal(record, SQL_COL.DS_CPO_LINE_SUB_NUM));
            setValue(asMsg.ordQty_A1, getBigDecimal(record, SQL_COL.ORD_QTY));
            setValue(asMsg.xxTotAmt_A1, getBigDecimal(record, SQL_COL.AMT));
            setValue(asMsg.xxHdrDplyStsNm_A1, getString(record, SQL_COL.ORD_HDR_DPLY_STS_NM));
            if (hasValue(getString(record, SQL_COL.ORD_LINE_DPLY_STS_NM))) {
                setValue(asMsg.xxLineDplyStsNm_A1, getString(record, SQL_COL.ORD_LINE_DPLY_STS_NM));
            } else {
                setValue(asMsg.xxLineDplyStsNm_A1, getString(record, SQL_COL.ORD_RTRN_LINE_DPLY_STS_NM));
            }
            setValue(asMsg.mdseCd_A1, getString(record, SQL_COL.MDSE_CD));
            setValue(asMsg.mdseDescShortTxt_A1, getString(record, SQL_COL.MDSE_DESC_SHORT_TXT));
            setValue(asMsg.rtlWhCd_A1, getString(record, SQL_COL.RTL_WH_CD));
            // 2018/12/14 QC#29286 Mod Start
            setValue(asMsg.rtlWhDescTxt_A1, getString(record, SQL_COL.RTL_WH_DESC_TXT));
            // 2018/12/14 QC#29286 Mod End
            setValue(asMsg.rtlSwhCd_A1, getString(record, SQL_COL.RTL_SWH_CD));
            setValue(asMsg.rtlSwhNm_A1, getString(record, SQL_COL.RTL_SWH_NM));
            setValue(asMsg.soldToCustAcctCd_A1, getString(record, SQL_COL.SOLD_TO_CUST_ACCT_CD));
            setValue(asMsg.soldToCustAcctNm_A1, getString(record, SQL_COL.SOLD_TO_CUST_ACCT_NM));
            setValue(asMsg.soldToCustLocCd_A1, getString(record, SQL_COL.SOLD_TO_CUST_LOC_CD));
            setValue(asMsg.shipToCustAcctCd_A1, getString(record, SQL_COL.SHIP_TO_CUST_ACCT_CD));
            setValue(asMsg.shipToCustAcctNm_A1, getString(record, SQL_COL.SHIP_TO_CUST_ACCT_NM));
            setValue(asMsg.shipToCustLocCd_A1, getString(record, SQL_COL.SHIP_TO_CUST_LOC_CD));
            setValue(asMsg.billToCustAcctCd_A1, getString(record, SQL_COL.BILL_TO_CUST_ACCT_CD));
            setValue(asMsg.billToCustAcctNm_A1, getString(record, SQL_COL.BILL_TO_CUST_ACCT_NM));
            setValue(asMsg.billToCustLocCd_A1, getString(record, SQL_COL.BILL_TO_CUST_LOC_CD));
            setValue(asMsg.xxCpoOrdDt_A1, getConvDate(getString(record, SQL_COL.CPO_ORD_TS), asMsg.getAttr("xxCpoOrdDt_A1").getDigit()));
            setValue(asMsg.xxBookDt_A1, getConvDate(getString(record, SQL_COL.ORD_BOOK_TS), asMsg.getAttr("xxBookDt_A1").getDigit()));
            setValue(asMsg.rddDt_A1, getString(record, SQL_COL.RDD_DT));
            setValue(asMsg.psdDt_A1, getString(record, SQL_COL.PSD_DT));
            setValue(asMsg.pddDt_A1, getString(record, SQL_COL.PDD_DT));
            setValue(asMsg.actlShipDt_A1, getString(record, SQL_COL.ACTL_SHIP_DT));
            setValue(asMsg.istlDt_A1, getString(record, SQL_COL.ISTL_DT));
            // 2018/12/06 QC#29024 Mod Start
            // 2018/08/16 QC#22893 Del Start
            setValue(asMsg.invDt_A1, getString(record, SQL_COL.INV_DT));
            // 2018/08/16 QC#22893 Del End
            // 2018/12/06 QC#29024 Mod End
            setValue(asMsg.dsOrdCatgCd_A1, getString(record, SQL_COL.DS_ORD_CATG_CD));
            setValue(asMsg.dsOrdCatgDescTxt_A1, getString(record, SQL_COL.DS_ORD_CATG_DESC_TXT));
            setValue(asMsg.dsOrdTpCd_A1, getString(record, SQL_COL.DS_ORD_TP_CD));
            setValue(asMsg.dsOrdTpDescTxt_A1, getString(record, SQL_COL.DS_ORD_TP_DESC_TXT));
            setValue(asMsg.dsOrdRsnCd_A1, getString(record, SQL_COL.DS_ORD_RSN_CD));
            setValue(asMsg.dsOrdRsnDescTxt_A1, getString(record, SQL_COL.DS_ORD_RSN_DESC_TXT));
            setValue(asMsg.cpoSrcTpCd_A1, getString(record, SQL_COL.CPO_SRC_TP_CD));
            setValue(asMsg.cpoSrcTpDescTxt_A1, getString(record, SQL_COL.CPO_SRC_TP_DESC_TXT));
            setValue(asMsg.ordSrcRefNum_A1, getString(record, SQL_COL.ORD_SRC_REF_NUM));
            setValue(asMsg.ordLineSrcCd_A1, getString(record, SQL_COL.ORD_LINE_SRC_CD));
            setValue(asMsg.ordLineSrcNm_A1, getString(record, SQL_COL.ORD_LINE_SRC_NM));
            setValue(asMsg.prcCatgCd_A1, getString(record, SQL_COL.PRC_CATG_CD));
            setValue(asMsg.prcCatgDescTxt_A1, getString(record, SQL_COL.PRC_CATG_DESC_TXT));
            setValue(asMsg.custIssPoNum_A1, getString(record, SQL_COL.CUST_ISS_PO_NUM));
            setValue(asMsg.leaseCmpyPoNum_A1, getString(record, SQL_COL.LEASE_CMPY_PO_NUM));
            setValue(asMsg.coaExtnCd_A1, getString(record, SQL_COL.COA_EXTN_CD));
            setValue(asMsg.coaExtnDescTxt_A1, getString(record, SQL_COL.COA_EXTN_DESC_TXT));
            setValue(asMsg.coaBrCd_A1, getString(record, SQL_COL.COA_BR_CD));
            setValue(asMsg.coaBrDescTxt_A1, getString(record, SQL_COL.COA_BR_DESC_TXT));
            // 2018/02/22 QC#21611 Mod Start
            //setValue(asMsg.slsRepTocCd_A1, getString(record, SQL_COL.SLS_REP_TOC_CD));
            setValue(asMsg.slsRepPsnNum_A1, getString(record, SQL_COL.SLS_REP_PSN_NUM));
            // 2018/02/22 QC#21611 Mod End
            setValue(asMsg.tocNm_A1, getString(record, SQL_COL.SLS_REP_TOC_NM));
            setValue(asMsg.csmpContrNum_A1, getString(record, SQL_COL.CSMP_CONTR_NUM));
            setValue(asMsg.prcContrNum_A1, getString(record, SQL_COL.PRC_CONTR_NUM));
            setValue(asMsg.prcContrNm_A1, getString(record, SQL_COL.PRC_CONTR_NM));
            setValue(asMsg.zerothProdCtrlCd_A1, getString(record, SQL_COL.ZEROTH_PROD_CTRL_CD));
            setValue(asMsg.zerothProdCtrlNm_A1, getString(record, SQL_COL.ZEROTH_PROD_CTRL_NM));
            setValue(asMsg.firstProdCtrlCd_A1, getString(record, SQL_COL.FIRST_PROD_CTRL_CD));
            setValue(asMsg.firstProdCtrlNm_A1, getString(record, SQL_COL.FIRST_PROD_CTRL_NM));
            setValue(asMsg.scdProdCtrlCd_A1, getString(record, SQL_COL.SCD_PROD_CTRL_CD));
            setValue(asMsg.scdProdCtrlNm_A1, getString(record, SQL_COL.SCD_PROD_CTRL_NM));
            setValue(asMsg.thirdProdCtrlCd_A1, getString(record, SQL_COL.THIRD_PROD_CTRL_CD));
            setValue(asMsg.thirdProdCtrlNm_A1, getString(record, SQL_COL.THIRD_PROD_CTRL_NM));
            setValue(asMsg.frthProdCtrlCd_A1, getString(record, SQL_COL.FRTH_PROD_CTRL_CD));
            setValue(asMsg.frthProdCtrlNm_A1, getString(record, SQL_COL.FRTH_PROD_CTRL_NM));
            setValue(asMsg.mdlId_A1, getBigDecimal(record, SQL_COL.MDL_ID));
            setValue(asMsg.t_MdlNm_A1, getString(record, SQL_COL.T_MDL_NM));
            setValue(asMsg.coaProdCd_A1, getString(record, SQL_COL.COA_PROD_CD));
            setValue(asMsg.coaProdDescTxt_A1, getString(record, SQL_COL.COA_PROD_DESC_TXT));
            setValue(asMsg.coaMdseTpCd_A1, getString(record, SQL_COL.COA_MDSE_TP_CD));
            setValue(asMsg.coaMdseTpDescTxt_A1, getString(record, SQL_COL.COA_MDSE_TP_DESC_TXT));
            setValue(asMsg.svcConfigMstrPk_A1, getBigDecimal(record, SQL_COL.SVC_CONFIG_MSTR_PK));
            setValue(asMsg.dsContrNum_A1, getString(record, SQL_COL.DS_CONTR_NUM));
            setValue(asMsg.serNum_A1, getString(record, SQL_COL.SER_NUM));
            setValue(asMsg.prchReqNum_A1, getString(record, SQL_COL.PRCH_REQ_NUM));
            setValue(asMsg.poNum_A1, getString(record, SQL_COL.PO_ORD_NUM));
            setValue(asMsg.soNum_A1, getString(record, SQL_COL.SO_NUM));
            setValue(asMsg.invNum_A1, getString(record, SQL_COL.INV_NUM));
            setValue(asMsg.invtyLocCd_A1, getString(record, SQL_COL.INVTY_LOC_CD));
            setValue(asMsg.vndNm_A1, getString(record, SQL_COL.VND_NM));
            setValue(asMsg.dplyVndNm_A1, getString(record, SQL_COL.DPLY_VND_NM));
            setValue(asMsg.lineBizTpCd_A1, getString(record, SQL_COL.LINE_BIZ_TP_CD));
            setValue(asMsg.lineBizTpDescTxt_A1, getString(record, SQL_COL.LINE_BIZ_TP_DESC_TXT));
            setValue(asMsg.dsOrdLineCatgCd_A1, getString(record, SQL_COL.DS_ORD_LINE_CATG_CD));
            setValue(asMsg.dsOrdLineCatgDescTxt_A1, getString(record, SQL_COL.DS_ORD_LINE_CATG_DESC_TXT));
            setValue(asMsg.slsRepOrSlsTeamTocCd_A1, getString(record, SQL_COL.SLS_REP_OR_SLS_TEAM_TOC_CD));
            setValue(asMsg.slsRepOrSlsTeamTocNm_A1, getString(record, SQL_COL.SLS_REP_OR_SLS_TEAM_TOC_NM));
            setValue(asMsg.svcMachMstrPk_A1, getBigDecimal(record, SQL_COL.SVC_MACH_MSTR_PK));
            setValue(asMsg.configCatgCd_A1, getString(record, SQL_COL.CONFIG_CATG_CD));
            setValue(asMsg.aquNum_A1, getString(record, SQL_COL.AQU_NUM)); // 2018/08/01 QC#26304 Add

            // summary amount
            setValue(asMsg.xxPendImptSmryAmt_A1, getBigDecimal(record, SQL_COL.PEND_IMPT_SMRY_AMT));
            setValue(asMsg.xxEntSmryAmt_A1, getBigDecimal(record, SQL_COL.ENT_SMRY_AMT));
            setValue(asMsg.xxDiHldSmryAmt_A1, getBigDecimal(record, SQL_COL.DI_HLD_SMRY_AMT));
            setValue(asMsg.xxVldSmryAmt_A1, getBigDecimal(record, SQL_COL.VLD_SMRY_AMT));
            setValue(asMsg.xxPrftSmryAmt_A1, getBigDecimal(record, SQL_COL.PRFT_SMRY_AMT));
            setValue(asMsg.xxCrHldSmryAmt_A1, getBigDecimal(record, SQL_COL.CR_HLD_SMRY_AMT));
            setValue(asMsg.xxSplyAbuseSmryAmt_A1, getBigDecimal(record, SQL_COL.SPLY_ABUSE_SMRY_AMT));
            setValue(asMsg.xxPendReSubmtSmryAmt_A1, getBigDecimal(record, SQL_COL.PEND_RE_SUBMT_SMRY_AMT));
            setValue(asMsg.xxBookSmryAmt_A1, getBigDecimal(record, SQL_COL.BOOK_SMRY_AMT));
            setValue(asMsg.xxAwaitDropShipSmryAmt_A1, getBigDecimal(record, SQL_COL.AWAIT_DROP_SHIP_SMRY_AMT)); // 2017/11/21 QC#22550 Add
            //setValue(asMsg.xxSoCancSmryAmt_A1, getBigDecimal(record, SQL_COL.SO_CANC_SMRY_AMT)); // 2017/11/21 QC#22550 Del
            setValue(asMsg.xxPendReAllocSmryAmt_A1, getBigDecimal(record, SQL_COL.PEND_RE_ALLOC_SMRY_AMT)); // 2017/11/21 QC#22550 Add
            setValue(asMsg.xxPoCancSmryAmt_A1, getBigDecimal(record, SQL_COL.PO_CANC_SMRY_AMT));
            setValue(asMsg.xxPendFuflSmryAmt_A1, getBigDecimal(record, SQL_COL.PEND_FUFL_SMRY_AMT));
            setValue(asMsg.xxPendAllocSmryAmt_A1, getBigDecimal(record, SQL_COL.PEND_ALLOC_SMRY_AMT));
            setValue(asMsg.xxBoSmryAmt_A1, getBigDecimal(record, SQL_COL.BO_SMRY_AMT));
            setValue(asMsg.xxPendPickSmryAmt_A1, getBigDecimal(record, SQL_COL.PEND_PICK_SMRY_AMT));
            setValue(asMsg.xxDelyToShopSmryAmt_A1, getBigDecimal(record, SQL_COL.DELY_TO_SHOP_SMRY_AMT));
            setValue(asMsg.xxInShopConfigSmryAmt_A1, getBigDecimal(record, SQL_COL.IN_SHOP_CONFIG_SMRY_AMT));
            setValue(asMsg.xxPendShipSmryAmt_A1, getBigDecimal(record, SQL_COL.PEND_SHIP_SMRY_AMT));
            setValue(asMsg.xxShipSmryAmt_A1, getBigDecimal(record, SQL_COL.SHIP_SMRY_AMT));
            setValue(asMsg.xxPendDelyConfSmryAmt_A1, getBigDecimal(record, SQL_COL.PEND_DELY_CONF_SMRY_AMT));
            setValue(asMsg.xxPendIstlSmryAmt_A1, getBigDecimal(record, SQL_COL.PEND_ISTL_SMRY_AMT));
            setValue(asMsg.xxOnLoanSmryAmt_A1, getBigDecimal(record, SQL_COL.ON_LOAN_SMRY_AMT));
            setValue(asMsg.xxWaitRcptSmryAmt_A1, getBigDecimal(record, SQL_COL.WAIT_RCPT_SMRY_AMT));
            setValue(asMsg.xxPendRtrnSmryAmt_A1, getBigDecimal(record, SQL_COL.PEND_RTRN_SMRY_AMT));
            setValue(asMsg.xxPendInspSmryAmt_A1, getBigDecimal(record, SQL_COL.PEND_INSP_SMRY_AMT));
            setValue(asMsg.xxRwsCancSmryAmt_A1, getBigDecimal(record, SQL_COL.RWS_CANC_SMRY_AMT));
            setValue(asMsg.xxPrtlRcvSmryAmt_A1, getBigDecimal(record, SQL_COL.PRTL_RCV_SMRY_AMT));
            setValue(asMsg.xxPendInvSmryAmt_A1, getBigDecimal(record, SQL_COL.PEND_INV_SMRY_AMT));
            setValue(asMsg.xxBllgHldSmryAmt_A1, getBigDecimal(record, SQL_COL.BLLG_HLD_SMRY_AMT));
            setValue(asMsg.xxPendDlrIstlSmryAmt_A1, getBigDecimal(record, SQL_COL.PEND_DLR_ISTL_SMRY_AMT)); // 2017/11/21 QC#22550 Add
            setValue(asMsg.xxShipCloSmryAmt_A1, getBigDecimal(record, SQL_COL.SHIP_CLO_SMRY_AMT));
            // QC#11581 DEL Start
//            setValue(asMsg.xxInvdSmryAmt_A1, getBigDecimal(record, SQL_COL.INV_SMRY_AMT));
            // QC#11581 DEL End
            setValue(asMsg.xxCloSmryAmt_A1, getBigDecimal(record, SQL_COL.CLO_SMRY_AMT));
            setValue(asMsg.xxCloLoanRtrnSmryAmt_A1, getBigDecimal(record, SQL_COL.CLO_LOAN_RTRN_SMRY_AMT));
            setValue(asMsg.xxCloLoanSoldSmryAmt_A1, getBigDecimal(record, SQL_COL.CLO_LOAN_SOLD_SMRY_AMT));
            setValue(asMsg.xxCancSmryAmt_A1, getBigDecimal(record, SQL_COL.CANC_SMRY_AMT));

            // summary count
            setValue(asMsg.xxPendImptSmryCnt_A1, getBigDecimal(record, SQL_COL.PEND_IMPT_SMRY_CNT));
            setValue(asMsg.xxEntSmryCnt_A1, getBigDecimal(record, SQL_COL.ENT_SMRY_CNT));
            setValue(asMsg.xxDiHldSmryCnt_A1, getBigDecimal(record, SQL_COL.DI_HLD_SMRY_CNT));
            setValue(asMsg.xxVldSmryCnt_A1, getBigDecimal(record, SQL_COL.VLD_SMRY_CNT));
            setValue(asMsg.xxPrftSmryCnt_A1, getBigDecimal(record, SQL_COL.PRFT_SMRY_CNT));
            setValue(asMsg.xxCrHldSmryCnt_A1, getBigDecimal(record, SQL_COL.CR_HLD_SMRY_CNT));
            setValue(asMsg.xxSplyAbuseSmryCnt_A1, getBigDecimal(record, SQL_COL.SPLY_ABUSE_SMRY_CNT));
            setValue(asMsg.xxPendReSubmtSmryCnt_A1, getBigDecimal(record, SQL_COL.PEND_RE_SUBMT_SMRY_CNT));
            setValue(asMsg.xxBookSmryCnt_A1, getBigDecimal(record, SQL_COL.BOOK_SMRY_CNT));
            setValue(asMsg.xxAwaitDropShipSmryCnt_A1, getBigDecimal(record, SQL_COL.AWAIT_DROP_SHIP_SMRY_CNT)); // 2017/11/21 QC#22550 Add
            //setValue(asMsg.xxSoCancSmryCnt_A1, getBigDecimal(record, SQL_COL.SO_CANC_SMRY_CNT)); // 2017/11/21 QC#22550 Del
            setValue(asMsg.xxPendReAllocSmryCnt_A1, getBigDecimal(record, SQL_COL.PEND_RE_ALLOC_SMRY_CNT)); // 2017/11/21 QC#22550 Add
            setValue(asMsg.xxPoCancSmryCnt_A1, getBigDecimal(record, SQL_COL.PO_CANC_SMRY_CNT));
            setValue(asMsg.xxPendFuflSmryCnt_A1, getBigDecimal(record, SQL_COL.PEND_FUFL_SMRY_CNT));
            setValue(asMsg.xxPendAllocSmryCnt_A1, getBigDecimal(record, SQL_COL.PEND_ALLOC_SMRY_CNT));
            setValue(asMsg.xxBoSmryCnt_A1, getBigDecimal(record, SQL_COL.BO_SMRY_CNT));
            setValue(asMsg.xxPendPickSmryCnt_A1, getBigDecimal(record, SQL_COL.PEND_PICK_SMRY_CNT));
            setValue(asMsg.xxDelyToShopSmryCnt_A1, getBigDecimal(record, SQL_COL.DELY_TO_SHOP_SMRY_CNT));
            setValue(asMsg.xxInShopConfigSmryCnt_A1, getBigDecimal(record, SQL_COL.IN_SHOP_CONFIG_SMRY_CNT));
            setValue(asMsg.xxPendShipSmryCnt_A1, getBigDecimal(record, SQL_COL.PEND_SHIP_SMRY_CNT));
            setValue(asMsg.xxShipSmryCnt_A1, getBigDecimal(record, SQL_COL.SHIP_SMRY_CNT));
            setValue(asMsg.xxPendDelyConfSmryCnt_A1, getBigDecimal(record, SQL_COL.PEND_DELY_CONF_SMRY_CNT));
            setValue(asMsg.xxPendIstlSmryCnt_A1, getBigDecimal(record, SQL_COL.PEND_ISTL_SMRY_CNT));
            setValue(asMsg.xxOnLoanSmryCnt_A1, getBigDecimal(record, SQL_COL.ON_LOAN_SMRY_CNT));
            setValue(asMsg.xxWaitRcptSmryCnt_A1, getBigDecimal(record, SQL_COL.WAIT_RCPT_SMRY_CNT));
            setValue(asMsg.xxPendRtrnSmryCnt_A1, getBigDecimal(record, SQL_COL.PEND_RTRN_SMRY_CNT));
            setValue(asMsg.xxPendInspSmryCnt_A1, getBigDecimal(record, SQL_COL.PEND_INSP_SMRY_CNT));
            setValue(asMsg.xxRwsCancSmryCnt_A1, getBigDecimal(record, SQL_COL.RWS_CANC_SMRY_CNT));
            setValue(asMsg.xxPrtlRcvSmryCnt_A1, getBigDecimal(record, SQL_COL.PRTL_RCV_SMRY_CNT));
            setValue(asMsg.xxPendInvSmryCnt_A1, getBigDecimal(record, SQL_COL.PEND_INV_SMRY_CNT));
            setValue(asMsg.xxBllgHldSmryCnt_A1, getBigDecimal(record, SQL_COL.BLLG_HLD_SMRY_CNT));
            setValue(asMsg.xxPendDlrIstlSmryCnt_A1, getBigDecimal(record, SQL_COL.PEND_DLR_ISTL_SMRY_CNT)); // 2017/11/21 QC#22550 Add
            setValue(asMsg.xxShipCloSmryCnt_A1, getBigDecimal(record, SQL_COL.SHIP_CLO_SMRY_CNT));
            // QC#11581 DEL Start
//            setValue(asMsg.xxInvdSmryCnt_A1, getBigDecimal(record, SQL_COL.INV_SMRY_CNT));
            // QC#11581 DEL End
            setValue(asMsg.xxCloSmryCnt_A1, getBigDecimal(record, SQL_COL.CLO_SMRY_CNT));
            setValue(asMsg.xxCloLoanRtrnSmryCnt_A1, getBigDecimal(record, SQL_COL.CLO_LOAN_RTRN_SMRY_CNT));
            setValue(asMsg.xxCloLoanSoldSmryCnt_A1, getBigDecimal(record, SQL_COL.CLO_LOAN_SOLD_SMRY_CNT));
            setValue(asMsg.xxCancSmryCnt_A1, getBigDecimal(record, SQL_COL.CANC_SMRY_CNT));

            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // + mapping : Hidden Fields
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // Order Number Anchor Control Flag
            if (hasValue(asMsg.xxDplyOrdInqRefNum_A1)) {
                asMsg.xxAncrCtrlFlg_P0.setValue(Y);
            }

            // Header Status Anchor Control Flag
            if (HDR_STS.DI_HLD.getHdrStsNm().equals(asMsg.xxHdrDplyStsNm_A1.getValue())
                    // 2018/07/05 QC#25786 mod start
//                    || HDR_STS.SPLY_ABUSE.getHdrStsNm().equals(asMsg.xxHdrDplyStsNm_A1.getValue())
                    || HDR_STS.SPLY_ENFORCEMENT.getHdrStsNm().equals(asMsg.xxHdrDplyStsNm_A1.getValue())
                    // 2018/07/05 QC#25786 mod end
                    || HDR_STS.PRFT.getHdrStsNm().equals(asMsg.xxHdrDplyStsNm_A1.getValue())
                    || HDR_STS.VLD.getHdrStsNm().equals(asMsg.xxHdrDplyStsNm_A1.getValue())
                    || HDR_STS.CR_HLD.getHdrStsNm().equals(asMsg.xxHdrDplyStsNm_A1.getValue())) {
                asMsg.xxAncrCtrlFlg_P1.setValue(Y);
            }

            // Line Status Anchor Control Flag
            if (LINE_STS.BO.getLineStsNm().equals(asMsg.xxLineDplyStsNm_A1.getValue())
                    || LINE_STS.PEND_ALLOC.getLineStsNm().equals(asMsg.xxLineDplyStsNm_A1.getValue())
                    || LINE_STS.BLLG_HLD.getLineStsNm().equals(asMsg.xxLineDplyStsNm_A1.getValue())) {
                asMsg.xxAncrCtrlFlg_P2.setValue(Y);
            }

            // SO Number Anchor Control Flag
            if (hasValue(asMsg.soNum_A1)) {
                asMsg.xxAncrCtrlFlg_P3.setValue(Y);
            }

            // PR Number Anchor Control Flag
            if (hasValue(asMsg.prchReqNum_A1)) {
                asMsg.xxAncrCtrlFlg_P4.setValue(Y);
            }

            // PO Number Anchor Control Flag
            if (hasValue(asMsg.poNum_A1)) {
                asMsg.xxAncrCtrlFlg_P5.setValue(Y);
            }

            // Invoice Number Anchor Control Flag
            if (hasValue(asMsg.invNum_A1)) {
                asMsg.xxAncrCtrlFlg_P6.setValue(Y);
            }

            // Contract Number Anchor Control Flag
            if (hasValue(asMsg.dsContrNum_A1)) {
                asMsg.xxAncrCtrlFlg_P7.setValue(Y);
            }

            // Config Number Anchor Control Flag
            if (hasValue(asMsg.svcConfigMstrPk_A1) && hasValue(asMsg.svcMachMstrPk_A1)) {
                asMsg.xxAncrCtrlFlg_P8.setValue(Y);
            }

        }

        private String getConvDate(String dateTs, int xxDtDigit) {

            if (hasValue(dateTs)) {
                if (dateTs.length() > xxDtDigit) {
                    dateTs = dateTs.substring(0, xxDtDigit);
                }
            }
            return dateTs;
        }

        private void getDplyByItemNm(NWAL1570SMsg sMsg, Integer intRowNum, Map<String, Object> data, NWAL1570_ASMsg asMsg, Map<String, BigDecimal> groupingMap) {

            String dplyByItemNm = "";
            String cpoOrdNum = getString(data, SQL_COL.SRC_REF_OR_CPO_ORD_NUM);

            String dplyBy01ItemCd = getString(data, SQL_COL.DPLY_BY_01_ITEM_CD);
            String dplyBy01ItemNm = getString(data, SQL_COL.DPLY_BY_01_ITEM_NM);
            String dplyBy02ItemCd = getString(data, SQL_COL.DPLY_BY_02_ITEM_CD);
            String dplyBy02ItemNm = getString(data, SQL_COL.DPLY_BY_02_ITEM_NM);
            String dplyBy03ItemCd = getString(data, SQL_COL.DPLY_BY_03_ITEM_CD);
            String dplyBy03ItemNm = getString(data, SQL_COL.DPLY_BY_03_ITEM_NM);

            if (hasValue(dplyBy01ItemCd)) {
                asMsg.xxOrdInqSrchTxt_C1.setValue(dplyBy01ItemCd);
            }
            if (hasValue(dplyBy01ItemNm)) {
                asMsg.xxOrdInqSrchTxt_N1.setValue(dplyBy01ItemNm);
            }
            if (hasValue(dplyBy02ItemCd)) {
                asMsg.xxOrdInqSrchTxt_C2.setValue(dplyBy02ItemCd);
            }
            if (hasValue(dplyBy02ItemNm)) {
                asMsg.xxOrdInqSrchTxt_N2.setValue(dplyBy02ItemNm);
            }
            if (hasValue(dplyBy03ItemCd)) {
                asMsg.xxOrdInqSrchTxt_C3.setValue(dplyBy03ItemCd);
            }
            if (hasValue(dplyBy03ItemNm)) {
                asMsg.xxOrdInqSrchTxt_N3.setValue(dplyBy03ItemNm);
                
            }

            String dplyBy02SumLineFlg = getString(data, SQL_COL.DPLY_BY_02_SUM_LINE_FLG);
            String dplyBy03SumLineFlg = getString(data, SQL_COL.DPLY_BY_03_SUM_LINE_FLG);

            BigDecimal groupingId = getBigDecimal(data, SQL_COL.GROUPING_ID);

            final String dplyByCtrlAncrLvl3 = "3";
            final String dplyByCtrlAncrLvl2 = "2";
            final String dplyByCtrlAncrLvl1 = "1";
            final String dplyByCtrlAncrLvl0 = "0";

            // ==================================
            // Overall Total Line
            // ==================================
            if (intRowNum == 0) {
                dplyByItemNm = DPLY_FIRST_RECORD;
                setValue(asMsg.xxDplyByItemNm_01, dplyByItemNm);
                asMsg.xxDplyByCtrlAncrLvlCd_A1.setValue(dplyByCtrlAncrLvl0);
            } else {
                if (groupingId.compareTo(BigDecimal.ZERO) != 0 && groupingMap.get(KEY_GRP_ID_01).compareTo(BigDecimal.ZERO) == 0) {
                    groupingMap.put(KEY_GRP_ID_01, groupingId);
                } else if (groupingId.compareTo(BigDecimal.ZERO) != 0 && groupingMap.get(KEY_GRP_ID_02).compareTo(BigDecimal.ZERO) == 0) {
                    if (groupingMap.get(KEY_GRP_ID_01).compareTo(groupingId) != 0) {
                        groupingMap.put(KEY_GRP_ID_02, groupingId);
                    }
                } else if (groupingId.compareTo(BigDecimal.ZERO) != 0 && groupingMap.get(KEY_GRP_ID_03).compareTo(BigDecimal.ZERO) == 0) {
                    if (groupingMap.get(KEY_GRP_ID_01).compareTo(groupingId) != 0 && groupingMap.get(KEY_GRP_ID_02).compareTo(groupingId) != 0) {
                        groupingMap.put(KEY_GRP_ID_03, groupingId);
                    }
                }

                // +++++++++++++++++++++++++++++++++++
                // Case : select display by 1,2,3
                // +++++++++++++++++++++++++++++++++++
                if (hasValue(dplyBy03SumLineFlg)) {
                    // Case : Display By 3 Summary Line
                    if (groupingId.compareTo(BigDecimal.ZERO) != 0 && groupingMap.get(KEY_GRP_ID_03).compareTo(groupingId) == 0) {
                        if (hasValue(sMsg.dplyBy03ItemNm)
                                && (sMsg.dplyBy03ItemNm.getValue().endsWith("_DT") || sMsg.dplyBy03ItemNm.getValue().endsWith("_TS"))) {
                            dplyByItemNm = getDate(dplyBy03ItemCd);
                        } else {
                            dplyByItemNm = dplyBy03ItemCd;
                        }
                        if (hasValue(dplyBy03ItemNm)) {
                            dplyByItemNm = concatString(dplyByItemNm, HYPHEN, dplyBy03ItemNm);
                        }
                        if (hasValue(dplyByItemNm) && dplyByItemNm.length() > DISP_BY_ITEM_MAX) {
                            dplyByItemNm = dplyByItemNm.substring(0, DISP_BY_ITEM_MAX);
                        }
                        setValue(asMsg.xxDplyByItemNm_03, dplyByItemNm);
                        setValue(asMsg.xxDplyByItemNm_01, dplyByItemNm);
                        asMsg.xxDplyByCtrlAncrLvlCd_A1.setValue(dplyByCtrlAncrLvl3);
                        if (hasValue(dplyBy03ItemCd) && dplyBy03ItemCd.equals(cpoOrdNum)) {
                            cpoOrdNum = "";
                        }
                        // Case : Display By 2 Summary Line
                    } else if (groupingId.compareTo(BigDecimal.ZERO) != 0 && groupingMap.get(KEY_GRP_ID_02).compareTo(groupingId) == 0) {
                        // Case : Display By 1 Summary Line
                        if (hasValue(sMsg.dplyBy02ItemNm)
                                && (sMsg.dplyBy02ItemNm.getValue().endsWith("_DT") || sMsg.dplyBy02ItemNm.getValue().endsWith("_TS"))) {
                            dplyByItemNm = getDate(dplyBy02ItemCd);
                        } else {
                            dplyByItemNm = dplyBy02ItemCd;
                        }
                        if (hasValue(dplyBy02ItemNm)) {
                            dplyByItemNm = concatString(dplyByItemNm, HYPHEN, dplyBy02ItemNm);
                        }
                        if (hasValue(dplyByItemNm) && dplyByItemNm.length() > DISP_BY_ITEM_MAX) {
                            dplyByItemNm = dplyByItemNm.substring(0, DISP_BY_ITEM_MAX);
                        }
                        setValue(asMsg.xxDplyByItemNm_02, dplyByItemNm);
                        setValue(asMsg.xxDplyByItemNm_01, dplyByItemNm);
                        asMsg.xxDplyByCtrlAncrLvlCd_A1.setValue(dplyByCtrlAncrLvl2);
                        if (hasValue(dplyBy02ItemCd) && dplyBy02ItemCd.equals(cpoOrdNum)) {
                            cpoOrdNum = "";
                        }
                    } else if (groupingId.compareTo(BigDecimal.ZERO) != 0 && groupingMap.get(KEY_GRP_ID_01).compareTo(groupingId) == 0) {
                        if (hasValue(sMsg.dplyBy01ItemNm)
                                && (sMsg.dplyBy01ItemNm.getValue().endsWith("_DT") || sMsg.dplyBy01ItemNm.getValue().endsWith("_TS"))) {
                            dplyByItemNm = getDate(dplyBy01ItemCd);
                        } else {
                            dplyByItemNm = dplyBy01ItemCd;
                        }
                        if (hasValue(dplyBy01ItemNm)) {
                            dplyByItemNm = concatString(dplyByItemNm, HYPHEN, dplyBy01ItemNm);
                        }
                        if (hasValue(dplyByItemNm) && dplyByItemNm.length() > DISP_BY_ITEM_MAX) {
                            dplyByItemNm = dplyByItemNm.substring(0, DISP_BY_ITEM_MAX);
                        }
                        setValue(asMsg.xxDplyByItemNm_01, dplyByItemNm);
                        asMsg.xxDplyByCtrlAncrLvlCd_A1.setValue(dplyByCtrlAncrLvl1);
                        if (hasValue(dplyBy01ItemCd) && dplyBy01ItemCd.equals(cpoOrdNum)) {
                            cpoOrdNum = "";
                        }
                    }
                    // +++++++++++++++++++++++++++++++++++
                    // Case : select display by 1,2
                    // +++++++++++++++++++++++++++++++++++
                } else if (hasValue(dplyBy02SumLineFlg)) {
                    // Case : Display By 2 Summary Line
                    if (groupingId.compareTo(BigDecimal.ZERO) != 0 && groupingMap.get(KEY_GRP_ID_02).compareTo(groupingId) == 0) {
                        // Case : Display By 1 Summary Line
                        if (hasValue(sMsg.dplyBy02ItemNm)
                                && (sMsg.dplyBy02ItemNm.getValue().endsWith("_DT") || sMsg.dplyBy02ItemNm.getValue().endsWith("_TS"))) {
                            dplyByItemNm = getDate(dplyBy02ItemCd);
                        } else {
                            dplyByItemNm = dplyBy02ItemCd;
                        }
                        if (hasValue(dplyBy02ItemNm)) {
                            dplyByItemNm = concatString(dplyByItemNm, HYPHEN, dplyBy02ItemNm);
                        }
                        if (hasValue(dplyByItemNm) && dplyByItemNm.length() > DISP_BY_ITEM_MAX) {
                            dplyByItemNm = dplyByItemNm.substring(0, DISP_BY_ITEM_MAX);
                        }
                        setValue(asMsg.xxDplyByItemNm_02, dplyByItemNm);
                        setValue(asMsg.xxDplyByItemNm_01, dplyByItemNm);
                        asMsg.xxDplyByCtrlAncrLvlCd_A1.setValue(dplyByCtrlAncrLvl2);
                        if (hasValue(dplyBy02ItemCd) && dplyBy02ItemCd.equals(cpoOrdNum)) {
                            cpoOrdNum = "";
                        }
                    } else if (groupingId.compareTo(BigDecimal.ZERO) != 0 && groupingMap.get(KEY_GRP_ID_01).compareTo(groupingId) == 0) {
                        if (hasValue(sMsg.dplyBy01ItemNm)
                                && (sMsg.dplyBy01ItemNm.getValue().endsWith("_DT") || sMsg.dplyBy01ItemNm.getValue().endsWith("_TS"))) {
                            dplyByItemNm = getDate(dplyBy01ItemCd);
                        } else {
                            dplyByItemNm = dplyBy01ItemCd;
                        }
                        if (hasValue(dplyBy01ItemNm)) {
                            dplyByItemNm = concatString(dplyByItemNm, HYPHEN, dplyBy01ItemNm);
                        }
                        if (hasValue(dplyByItemNm) && dplyByItemNm.length() > DISP_BY_ITEM_MAX) {
                            dplyByItemNm = dplyByItemNm.substring(0, DISP_BY_ITEM_MAX);
                        }
                        setValue(asMsg.xxDplyByItemNm_01, dplyByItemNm);
                        asMsg.xxDplyByCtrlAncrLvlCd_A1.setValue(dplyByCtrlAncrLvl1);
                        if (hasValue(dplyBy01ItemCd) && dplyBy01ItemCd.equals(cpoOrdNum)) {
                            cpoOrdNum = "";
                        }
                    }
                    // +++++++++++++++++++++++++++++++++++
                    // Case : select display by 1
                    // +++++++++++++++++++++++++++++++++++
                } else {
                    // Case : Display By 1 Summary Line
                    if (hasValue(sMsg.dplyBy01ItemNm)
                            && (sMsg.dplyBy01ItemNm.getValue().endsWith("_DT") || sMsg.dplyBy01ItemNm.getValue().endsWith("_TS"))) {
                        dplyByItemNm = getDate(dplyBy01ItemCd);
                    } else {
                        dplyByItemNm = dplyBy01ItemCd;
                    }
                    if (!groupingId.equals(BigDecimal.ZERO)) {
                        if (hasValue(dplyBy01ItemNm)) {
                            dplyByItemNm = concatString(dplyByItemNm, HYPHEN, dplyBy01ItemNm);
                        }
                        if (hasValue(dplyByItemNm) && dplyByItemNm.length() > DISP_BY_ITEM_MAX) {
                            dplyByItemNm = dplyByItemNm.substring(0, DISP_BY_ITEM_MAX);
                        }
                        setValue(asMsg.xxDplyByItemNm_01, dplyByItemNm);
                        asMsg.xxDplyByCtrlAncrLvlCd_A1.setValue(dplyByCtrlAncrLvl1);
                        if (hasValue(dplyBy01ItemCd) && dplyBy01ItemCd.equals(cpoOrdNum)) {
                            cpoOrdNum = "";
                        }
                    }
                }
            }
            String dplyOrdNum = "";
            if (hasValue(cpoOrdNum)) {
                dplyOrdNum = cpoOrdNum;
            }
            setValue(asMsg.xxDplyOrdInqRefNum_A1, dplyOrdNum);

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

        protected Map<String, Object> getResultMap(NWAL1570CMsg cMsg, List<String> column, ResultSet result) throws SQLException {
            Map<String, Object> resultMap = new HashMap<String, Object>();

            putResult(resultMap, column, result, SQL_COL.GLBL_CMPY_CD);
            putResultBigDecimal(resultMap, column, result, SQL_COL.GROUPING_ID);
            putResult(resultMap, column, result, SQL_COL.DPLY_BY_01_ITEM_CD);
            putResultBigDecimal(resultMap, column, result, SQL_COL.GROUPING01);

            if (hasValue(cMsg.pairDplyByItemNm_01)) {
                putResult(resultMap, column, result, SQL_COL.DPLY_BY_01_SUM_LINE_FLG);
                putResult(resultMap, column, result, SQL_COL.DPLY_BY_01_ITEM_NM);
            }

            if (hasValue(cMsg.dplyBy02ItemNm)) {
                putResult(resultMap, column, result, SQL_COL.DPLY_BY_02_SUM_LINE_FLG);
                putResult(resultMap, column, result, SQL_COL.DPLY_BY_02_ITEM_CD);
                putResultBigDecimal(resultMap, column, result, SQL_COL.GROUPING02);

                if (hasValue(cMsg.pairDplyByItemNm_02)) {
                    putResult(resultMap, column, result, SQL_COL.DPLY_BY_02_ITEM_NM);
                }
            }

            if (hasValue(cMsg.dplyBy03ItemNm)) {
                putResult(resultMap, column, result, SQL_COL.DPLY_BY_03_SUM_LINE_FLG);
                putResult(resultMap, column, result, SQL_COL.DPLY_BY_03_ITEM_CD);
                putResultBigDecimal(resultMap, column, result, SQL_COL.GROUPING03);

                if (hasValue(cMsg.pairDplyByItemNm_03)) {
                    putResult(resultMap, column, result, SQL_COL.DPLY_BY_03_ITEM_NM);
                }
            }

            putResult(resultMap, column, result, SQL_COL.SRC_REF_OR_CPO_ORD_NUM);
            putResult(resultMap, column, result, SQL_COL.DS_ORD_POSN_NUM);
            putResultBigDecimal(resultMap, column, result, SQL_COL.DS_CPO_LINE_NUM);
            putResultBigDecimal(resultMap, column, result, SQL_COL.DS_CPO_LINE_SUB_NUM);
            putResultBigDecimal(resultMap, column, result, SQL_COL.ORD_QTY);
            putResultBigDecimal(resultMap, column, result, SQL_COL.AMT);
            putResult(resultMap, column, result, SQL_COL.ORD_HDR_DPLY_STS_NM);
            putResult(resultMap, column, result, SQL_COL.ORD_LINE_DPLY_STS_NM);
            putResult(resultMap, column, result, SQL_COL.ORD_RTRN_LINE_DPLY_STS_NM);
            putResult(resultMap, column, result, SQL_COL.MDSE_CD);
            putResult(resultMap, column, result, SQL_COL.MDSE_DESC_SHORT_TXT);
            putResult(resultMap, column, result, SQL_COL.RTL_WH_CD);
            // 2018/12/14 QC#29286 Mod Start
            putResult(resultMap, column, result, SQL_COL.RTL_WH_DESC_TXT);
            //putResult(resultMap, column, result, SQL_COL.RTL_WH_NM);
            // 2018/12/14 QC#29286 Mod End
            putResult(resultMap, column, result, SQL_COL.RTL_SWH_CD);
            putResult(resultMap, column, result, SQL_COL.RTL_SWH_NM);
            putResult(resultMap, column, result, SQL_COL.SOLD_TO_CUST_ACCT_CD);
            putResult(resultMap, column, result, SQL_COL.SOLD_TO_CUST_ACCT_NM);
            putResult(resultMap, column, result, SQL_COL.SOLD_TO_CUST_LOC_CD);
            putResult(resultMap, column, result, SQL_COL.SHIP_TO_CUST_ACCT_CD);
            putResult(resultMap, column, result, SQL_COL.SHIP_TO_CUST_ACCT_NM);
            putResult(resultMap, column, result, SQL_COL.SHIP_TO_CUST_LOC_CD);
            putResult(resultMap, column, result, SQL_COL.BILL_TO_CUST_ACCT_CD);
            putResult(resultMap, column, result, SQL_COL.BILL_TO_CUST_ACCT_NM);
            putResult(resultMap, column, result, SQL_COL.BILL_TO_CUST_LOC_CD);
            putResult(resultMap, column, result, SQL_COL.CPO_ORD_TS);
            putResult(resultMap, column, result, SQL_COL.ORD_BOOK_TS);
            putResult(resultMap, column, result, SQL_COL.RDD_DT);
            putResult(resultMap, column, result, SQL_COL.PSD_DT);
            putResult(resultMap, column, result, SQL_COL.PDD_DT);
            putResult(resultMap, column, result, SQL_COL.ACTL_SHIP_DT);
            putResult(resultMap, column, result, SQL_COL.ISTL_DT);
            // 2018/12/06 QC#29024 Mod Start
            // 2018/08/16 QC#22893 Del Start
            putResult(resultMap, column, result, SQL_COL.INV_DT);
            // 2018/08/16 QC#22893 Del End
            // 2018/12/06 QC#29024 Mod End
            putResult(resultMap, column, result, SQL_COL.DS_ORD_CATG_CD);
            putResult(resultMap, column, result, SQL_COL.DS_ORD_CATG_DESC_TXT);
            putResult(resultMap, column, result, SQL_COL.DS_ORD_TP_CD);
            putResult(resultMap, column, result, SQL_COL.DS_ORD_TP_DESC_TXT);
            putResult(resultMap, column, result, SQL_COL.DS_ORD_RSN_CD);
            putResult(resultMap, column, result, SQL_COL.DS_ORD_RSN_DESC_TXT);
            putResult(resultMap, column, result, SQL_COL.CPO_SRC_TP_CD);
            putResult(resultMap, column, result, SQL_COL.CPO_SRC_TP_DESC_TXT);
            putResult(resultMap, column, result, SQL_COL.ORD_SRC_REF_NUM);
            putResult(resultMap, column, result, SQL_COL.ORD_LINE_SRC_CD);
            putResult(resultMap, column, result, SQL_COL.ORD_LINE_SRC_NM);
            putResult(resultMap, column, result, SQL_COL.PRC_CATG_CD);
            putResult(resultMap, column, result, SQL_COL.PRC_CATG_DESC_TXT);
            putResult(resultMap, column, result, SQL_COL.CUST_ISS_PO_NUM);
            putResult(resultMap, column, result, SQL_COL.LEASE_CMPY_PO_NUM);
            putResult(resultMap, column, result, SQL_COL.COA_EXTN_CD);
            putResult(resultMap, column, result, SQL_COL.COA_EXTN_DESC_TXT);
            putResult(resultMap, column, result, SQL_COL.COA_BR_CD);
            putResult(resultMap, column, result, SQL_COL.COA_BR_DESC_TXT);
            // 2018/02/22 QC#21611 Mod Start
            //putResult(resultMap, column, result, SQL_COL.SLS_REP_TOC_CD);
            putResult(resultMap, column, result, SQL_COL.SLS_REP_PSN_NUM);
            // 2018/02/22 QC#21611 Mod End
            putResult(resultMap, column, result, SQL_COL.SLS_REP_TOC_NM);
            putResult(resultMap, column, result, SQL_COL.CSMP_CONTR_NUM);
            putResult(resultMap, column, result, SQL_COL.PRC_CONTR_NUM);
            putResult(resultMap, column, result, SQL_COL.PRC_CONTR_NM);
            putResult(resultMap, column, result, SQL_COL.ZEROTH_PROD_CTRL_CD);
            putResult(resultMap, column, result, SQL_COL.ZEROTH_PROD_CTRL_NM);
            putResult(resultMap, column, result, SQL_COL.FIRST_PROD_CTRL_CD);
            putResult(resultMap, column, result, SQL_COL.FIRST_PROD_CTRL_NM);
            putResult(resultMap, column, result, SQL_COL.SCD_PROD_CTRL_CD);
            putResult(resultMap, column, result, SQL_COL.SCD_PROD_CTRL_NM);
            putResult(resultMap, column, result, SQL_COL.THIRD_PROD_CTRL_CD);
            putResult(resultMap, column, result, SQL_COL.THIRD_PROD_CTRL_NM);
            putResult(resultMap, column, result, SQL_COL.FRTH_PROD_CTRL_CD);
            putResult(resultMap, column, result, SQL_COL.FRTH_PROD_CTRL_NM);
            putResultBigDecimal(resultMap, column, result, SQL_COL.MDL_ID);
            putResult(resultMap, column, result, SQL_COL.T_MDL_NM);
            putResult(resultMap, column, result, SQL_COL.COA_PROD_CD);
            putResult(resultMap, column, result, SQL_COL.COA_PROD_DESC_TXT);
            putResult(resultMap, column, result, SQL_COL.COA_MDSE_TP_CD);
            putResult(resultMap, column, result, SQL_COL.COA_MDSE_TP_DESC_TXT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.SVC_CONFIG_MSTR_PK);
            putResult(resultMap, column, result, SQL_COL.DS_CONTR_NUM);
            putResult(resultMap, column, result, SQL_COL.SER_NUM);
            putResult(resultMap, column, result, SQL_COL.PRCH_REQ_NUM);
            putResult(resultMap, column, result, SQL_COL.PO_ORD_NUM);
            putResult(resultMap, column, result, SQL_COL.SO_NUM);
            putResult(resultMap, column, result, SQL_COL.INV_NUM);
            putResult(resultMap, column, result, SQL_COL.INVTY_LOC_CD);
            putResult(resultMap, column, result, SQL_COL.VND_NM);
            putResult(resultMap, column, result, SQL_COL.DPLY_VND_NM);
            putResult(resultMap, column, result, SQL_COL.LINE_BIZ_TP_CD);
            putResult(resultMap, column, result, SQL_COL.LINE_BIZ_TP_DESC_TXT);
            putResult(resultMap, column, result, SQL_COL.DS_ORD_LINE_CATG_CD);
            putResult(resultMap, column, result, SQL_COL.DS_ORD_LINE_CATG_DESC_TXT);
            putResult(resultMap, column, result, SQL_COL.SLS_REP_OR_SLS_TEAM_TOC_CD);
            putResult(resultMap, column, result, SQL_COL.SLS_REP_OR_SLS_TEAM_TOC_NM);
            putResultBigDecimal(resultMap, column, result, SQL_COL.SVC_MACH_MSTR_PK);
            putResult(resultMap, column, result, SQL_COL.CONFIG_CATG_CD);
            putResult(resultMap, column, result, SQL_COL.AQU_NUM); // 2018/08/01 QC#26304 Add

            // Status Summary
            putResultBigDecimal(resultMap, column, result, SQL_COL.PEND_IMPT_SMRY_AMT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.ENT_SMRY_AMT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.DI_HLD_SMRY_AMT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.VLD_SMRY_AMT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.PRFT_SMRY_AMT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.CR_HLD_SMRY_AMT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.SPLY_ABUSE_SMRY_AMT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.PEND_RE_SUBMT_SMRY_AMT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.BOOK_SMRY_AMT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.AWAIT_DROP_SHIP_SMRY_AMT); // 2017/11/21 QC#22550 Add
            //putResultBigDecimal(resultMap, column, result, SQL_COL.SO_CANC_SMRY_AMT); // 2017/11/21 QC#22550 Del
            putResultBigDecimal(resultMap, column, result, SQL_COL.PEND_RE_ALLOC_SMRY_AMT); // 2017/11/21 QC#22550 Add
            putResultBigDecimal(resultMap, column, result, SQL_COL.PO_CANC_SMRY_AMT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.PEND_FUFL_SMRY_AMT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.PEND_ALLOC_SMRY_AMT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.BO_SMRY_AMT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.PEND_PICK_SMRY_AMT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.DELY_TO_SHOP_SMRY_AMT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.IN_SHOP_CONFIG_SMRY_AMT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.PEND_SHIP_SMRY_AMT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.SHIP_SMRY_AMT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.PEND_DELY_CONF_SMRY_AMT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.PEND_ISTL_SMRY_AMT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.ON_LOAN_SMRY_AMT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.WAIT_RCPT_SMRY_AMT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.PEND_RTRN_SMRY_AMT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.PEND_INSP_SMRY_AMT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.RWS_CANC_SMRY_AMT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.PRTL_RCV_SMRY_AMT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.PEND_INV_SMRY_AMT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.BLLG_HLD_SMRY_AMT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.PEND_DLR_ISTL_SMRY_AMT); // 2017/11/21 QC#22550 Add
            putResultBigDecimal(resultMap, column, result, SQL_COL.SHIP_CLO_SMRY_AMT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.INV_SMRY_AMT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.CLO_SMRY_AMT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.CLO_LOAN_RTRN_SMRY_AMT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.CLO_LOAN_SOLD_SMRY_AMT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.CANC_SMRY_AMT);

            putResultBigDecimal(resultMap, column, result, SQL_COL.PEND_IMPT_SMRY_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.ENT_SMRY_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.DI_HLD_SMRY_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.VLD_SMRY_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.PRFT_SMRY_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.CR_HLD_SMRY_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.SPLY_ABUSE_SMRY_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.PEND_RE_SUBMT_SMRY_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.BOOK_SMRY_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.AWAIT_DROP_SHIP_SMRY_CNT); // 2017/11/21 QC#22550 Add
            //putResultBigDecimal(resultMap, column, result, SQL_COL.SO_CANC_SMRY_CNT); // 2017/11/21 QC#22550 Del
            putResultBigDecimal(resultMap, column, result, SQL_COL.PEND_RE_ALLOC_SMRY_CNT); // 2017/11/21 QC#22550 Add
            putResultBigDecimal(resultMap, column, result, SQL_COL.PO_CANC_SMRY_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.PEND_FUFL_SMRY_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.PEND_ALLOC_SMRY_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.BO_SMRY_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.PEND_PICK_SMRY_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.DELY_TO_SHOP_SMRY_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.IN_SHOP_CONFIG_SMRY_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.PEND_SHIP_SMRY_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.SHIP_SMRY_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.PEND_DELY_CONF_SMRY_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.PEND_ISTL_SMRY_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.ON_LOAN_SMRY_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.WAIT_RCPT_SMRY_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.PEND_RTRN_SMRY_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.PEND_INSP_SMRY_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.RWS_CANC_SMRY_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.PRTL_RCV_SMRY_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.PEND_INV_SMRY_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.BLLG_HLD_SMRY_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.PEND_DLR_ISTL_SMRY_CNT); // 2017/11/21 QC#22550 Add
            putResultBigDecimal(resultMap, column, result, SQL_COL.SHIP_CLO_SMRY_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.INV_SMRY_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.CLO_SMRY_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.CLO_LOAN_RTRN_SMRY_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.CLO_LOAN_SOLD_SMRY_CNT);
            putResultBigDecimal(resultMap, column, result, SQL_COL.CANC_SMRY_CNT);

            return resultMap;

        }

        protected String getString(Map<String, Object> resultMap, SQL_COL key) {
            return (String) resultMap.get(key.name());
        }

        protected BigDecimal getBigDecimal(Map<String, Object> resultMap, SQL_COL key) {
            return (BigDecimal) resultMap.get(key.name());
        }

        protected String getDate(String date) {

            if (hasValue(date) && date.length() == DATE_LENGTH) {
                date = formatDate(date);
            }
            return date;
        }

        protected String formatDate(String date) {

            // return ZYPDateUtil.DateFormatter(date, "yyyyMMdd", "MM/dd/yyyy");
            return ZYPDateUtil.formatEzd8ToDisp(date);

        }

        protected String getLineNo(Map<String, Object> data) {

            String lineNo = "";

            String cpoDtlLineNum = getString(data, SQL_COL.DS_CPO_LINE_NUM);
            String cpoDtlLineSubNum = getString(data, SQL_COL.DS_CPO_LINE_SUB_NUM);

            if (hasValue(cpoDtlLineNum) && hasValue(cpoDtlLineSubNum)) {
                lineNo = concatString(cpoDtlLineNum, ".", cpoDtlLineSubNum);
            }

            return lineNo;
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

    private void setConstantParam(final Map<String, Serializable> param) {

        // Status
        param.put("stsPendImpt", STS_NM_PEND_IMPT);
        param.put("stsEnt", HDR_STS.ENT.getHdrStsNm());
        param.put("stsDiHld", HDR_STS.DI_HLD.getHdrStsNm());
        param.put("stsVld", HDR_STS.VLD.getHdrStsNm());
        // 2018/07/05 QC#25786 mod start
//        param.put("stsSplyAbuse", HDR_STS.SPLY_ABUSE.getHdrStsNm());
        param.put("stsSplyAbuse", HDR_STS.SPLY_ENFORCEMENT.getHdrStsNm());
        // 2018/07/05 QC#25786 mod end
        param.put("stsPrft", HDR_STS.PRFT.getHdrStsNm());
        param.put("stsCrHld", HDR_STS.CR_HLD.getHdrStsNm());
        param.put("stsPendReSubmt", HDR_STS.PEND_RE_SUBMT.getHdrStsNm());

        param.put("stsBook", LINE_STS.BOOK.getLineStsNm());
        param.put("stsAwaitDropShip", LINE_STS.AWAIT_DROP_SHIP.getLineStsNm()); // 2017/11/21 QC#22550 Add
        //param.put("stsSoCanc", LINE_STS.SO_CANC.getLineStsNm()); // 2017/11/21 QC#22550 Del
        param.put("stsPendReAlloc", LINE_STS.PEND_RE_ALLOC.getLineStsNm()); // 2017/11/21 QC#22550 Add
        param.put("stsPoCanc", LINE_STS.PO_CANC.getLineStsNm());
        param.put("stsPendFufl", LINE_STS.PEND_FUFL.getLineStsNm());
        param.put("stsPendAlloc", LINE_STS.PEND_ALLOC.getLineStsNm());
        param.put("stsBo", LINE_STS.BO.getLineStsNm());
        param.put("stsPendPick", LINE_STS.PEND_PICK.getLineStsNm());
        param.put("stsDelyToShop", LINE_STS.DELY_TO_SHOP.getLineStsNm());
        param.put("stsInShopConfig", LINE_STS.IN_SHOP_CONFIG.getLineStsNm());
        param.put("stsPendShip", LINE_STS.PEND_SHIP.getLineStsNm());
        param.put("stsShip", LINE_STS.SHIP.getLineStsNm());
        param.put("stsPendDelyConf", LINE_STS.PEND_DELY_CONF.getLineStsNm());
        param.put("stsPendIstl", LINE_STS.PEND_ISTL.getLineStsNm());
        param.put("stsOnLoan", LINE_STS.ON_LOAN.getLineStsNm());
        param.put("stsWaitRcpt", LINE_STS.WAIT_RCPT.getLineStsNm());
        param.put("stsPendInv", LINE_STS.PEND_INV.getLineStsNm());
        param.put("stsBllgHld", LINE_STS.BLLG_HLD.getLineStsNm());
        param.put("stsPendDlrIstl", LINE_STS.PEND_DLR_ISTL.getLineStsNm()); // 2017/11/21 QC#22550 Add
        param.put("stsShipClo", LINE_STS.SHIP_CLO.getLineStsNm());
        param.put("stsCloLoanRtrn", LINE_STS.CLO_LOAN_RTRN.getLineStsNm());
        param.put("stsCloLoanSold", LINE_STS.CLO_LOAN_SOLD.getLineStsNm());
        // QC#11581 DEL Start
//        param.put("stsInv", LINE_STS.INV.getLineStsNm());
        // QC#11581 DEL End
        param.put("stsCanc", LINE_STS.CANC.getLineStsNm());
        param.put("stsPendInsp", RTRN_LINE_STS.PEND_INSP.getRtrnLineStsNm());
        param.put("stsPendRtrn", RTRN_LINE_STS.PEND_RTRN.getRtrnLineStsNm());
        param.put("stsRwsCanc", RTRN_LINE_STS.RWS_CANC.getRtrnLineStsNm());
        param.put("stsPrtlRcv", RTRN_LINE_STS.PRTL_RCV.getRtrnLineStsNm());
        param.put("stsClo", RTRN_LINE_STS.CLO.getRtrnLineStsNm());

        // 2019/05/07 QC#50031 Add Start
        param.put("stsLineEnt", LINE_STS.ENT.getLineStsNm());
        // 2019/05/07 QC#50031 Add End

        param.put("attrbTpCdUser", ORD_TEAM_ATTRB_TP.USER_ID);
        param.put("attrbTpCdCustomer", ORD_TEAM_ATTRB_TP.CUSTOMER_NUMBER);
        param.put("attrbTpCdLob", ORD_TEAM_ATTRB_TP.LINE_OF_BUSINESS);
        param.put("attrbTpCdBranch", ORD_TEAM_ATTRB_TP.BRANCH);
    }

   private void setSearchCondition(final NWAL1570CMsg cMsg, final Map<String, Serializable> param) {

       if (Y.equals(cMsg.xxPgFlg.getValue())) {
           param.put("realTimeInqFlg", Y);
       } else {
           param.put("realTimeInqFlg", N);
       }

       if (Y.equals(cMsg.xxOnlySlsOrdFlg.getValue())) {
           param.put("trxCd", TRX.SALES);
       }

        String[][] orderCriteria = null;

        // 2019/02/13 QC#30238 Mod Start
        boolean criteria_flg = true;

        // 2019/03/29 QC#31001 Add Start
        boolean isMultiOrder = false;
        // 2019/03/29 QC#31001 Add End

        if (hasValue(cMsg.xxCpoOrdNumSrchTxt_H1)){
            // 2019/03/29 QC#31001 Add Start

            String[] list = getSrchTxt(cMsg.xxCpoOrdNumSrchTxt_H1.getValue(), cMsg.xxSplCharTxt.getValue());

            if ((list != null) && (list.length > 0)){
                isMultiOrder = true;
            }
            // 2019/03/29 QC#31001 Add End

            if (!cMsg.xxCpoOrdNumSrchTxt_H1.getValue().contains(PERCENT)) {
                if (ZYPCommonFunc.hasValue(cMsg.xxDplyStsNm)) {
                    orderCriteria = createOrderCriteriaByOrderNumAndStatus(cMsg, isMultiOrder);
                } else {
                    orderCriteria = createOrderCriteriaByOrderNum(cMsg, isMultiOrder);
                }
                criteria_flg = false;
            } 

            // 2019/03/29 QC#31001 Mod Start
            if (!isMultiOrder) {
                param.put(SEARCH_COLUMN.ORDER_NUMBER.name(), cMsg.xxCpoOrdNumSrchTxt_H1.getValue());
            }
            //param.put(SEARCH_COLUMN.ORDER_NUMBER.name(), cMsg.xxCpoOrdNumSrchTxt_H1.getValue());
            // 2019/03/29 QC#31001 Mod End
        }

        if (criteria_flg) {
            orderCriteria = createOrderCriteria(cMsg, isMultiOrder);
        }

        //// QC#15164 MOD Start
        //// 2018/10/11 QC#28601 Mod Start
        ////if (hasValue(cMsg.xxCpoOrdNumSrchTxt_H1) && !S21StringUtil.isEquals(cMsg.xxCpoOrdNumSrchTxt_H1.getValue(), PERCENT)) {
        //if (hasValue(cMsg.xxCpoOrdNumSrchTxt_H1) && !cMsg.xxCpoOrdNumSrchTxt_H1.getValue().contains(PERCENT)) {
        //    // 2018/10/11 QC#28601 Mod End
        //    if (ZYPCommonFunc.hasValue(cMsg.xxDplyStsNm)) {
        //        orderCriteria = createOrderCriteriaByOrderNumAndStatus(cMsg);
        //    } else {
        //        orderCriteria = createOrderCriteriaByOrderNum(cMsg);
        //    }
        //} else {
        //    orderCriteria = createOrderCriteria(cMsg);
        //}
        //// QC#15164 MOD End

        // 2019/02/13 QC#30238 Mod End

        String searchByStatusFlg = N;
        for (int i = 0; i <  cMsg.H.length(); i++) {
            if (cMsg.H.no(i).xxOrdHdrStsSelFlg_HS.getValue().equals(Y)) {
                searchByStatusFlg = Y;
                break;
            }
        }
        for (int i = 0; i <  cMsg.L.length(); i++) {
            if (cMsg.L.no(i).xxLineStsSelFlg_LS.getValue().equals(Y)) {
                searchByStatusFlg = Y;
                break;
            }
        }
        for (int i = 0; i <  cMsg.R.length(); i++) {
            if (cMsg.R.no(i).xxRtrnLineStsSelFlg_RS.getValue().equals(Y)) {
                searchByStatusFlg = Y;
                break;
            }
        }
        if (RSLT_MODE.STATUS_SUMMARY.getRsltModeCd().equals(cMsg.xxRsltModeCd.getValue())
                || (RSLT_MODE.ORDER_INQUIRY.getRsltModeCd().equals(cMsg.xxRsltModeCd.getValue()) && (Y.equals(searchByStatusFlg) || hasValue(cMsg.xxCpoOrdNumSrchTxt_H1) || (SCRN_ID_OTH.equals(cMsg.xxScrId.getValue()) && N.equals(cMsg.xxInclPendImptOrdFlg.getValue()))))) {
            param.put("searchOrdFlg", Y);
        } else {
            param.put("searchOrdFlg", N);
        }
        if ((RSLT_MODE.STATUS_SUMMARY.getRsltModeCd().equals(cMsg.xxRsltModeCd.getValue()) && Y.equals(cMsg.xxInclPendImptOrdFlg.getValue()))
                || (RSLT_MODE.ORDER_INQUIRY.getRsltModeCd().equals(cMsg.xxRsltModeCd.getValue()) && (Y.equals(searchByStatusFlg) || hasValue(cMsg.xxCpoOrdNumSrchTxt_H1)) && Y.equals(cMsg.xxInclPendImptOrdFlg.getValue()))) {
            param.put("unionPendImptOrdFlg", Y);
        }

        // 2019/02/13 QC#30238 Mod Start
        if (orderCriteria != null){
            param.put("searchCondition_OpenOrder", createSearchCondition(cMsg, param, SEARCH_STATUS.OPEN_ORD, orderCriteria));
        }
        //param.put("searchCondition_OpenOrder", createSearchCondition(cMsg, param, SEARCH_STATUS.OPEN_ORD, orderCriteria));
        // 2019/02/13 QC#30238 Mod End

        // QC#29024 2018/12/19 Del Start
        //param.put("searchCondition_CloseOrder", createSearchCondition(cMsg, param, SEARCH_STATUS.CLO_ORD, orderCriteria));
        // QC#29024 2018/12/19 Del End

        // 2019/02/13 QC#30238 Mod Start
        // QC#22893 2018/08/13 Add Start
        if (orderCriteria != null){
            param.put("searchQuickCondition", createQuickSearchCondition(cMsg, param, SEARCH_STATUS.QUICK, orderCriteria));
        }
        //param.put("searchQuickCondition", createQuickSearchCondition(cMsg, param, SEARCH_STATUS.QUICK, orderCriteria));
        // QC#22893 2018/08/13 Add End
        // 2019/02/13 QC#30238 Mod End

        if (Y.equals(cMsg.xxInclPendImptOrdFlg.getValue())) {
            // 2019/02/13 QC#30238 Mod Start
            if (orderCriteria != null){
                param.put("searchCondition_PendingImportOrder", createSearchCondition(cMsg, param, SEARCH_STATUS.PEND_IMPT_ORD, orderCriteria));
            }
            //param.put("searchCondition_PendingImportOrder", createSearchCondition(cMsg, param, SEARCH_STATUS.PEND_IMPT_ORD, orderCriteria));
            // 2019/02/13 QC#30238 Mod End
        }

        if (hasValue(cMsg.xxOrdTeamSrchTxt)) {
            param.put("ordTeamSrchTxt", cMsg.xxOrdTeamSrchTxt.getValue());
        }
        if (hasValue(cMsg.xxOrdZnSrchTxt)) {
            param.put("ordZnSrchTxt", cMsg.xxOrdZnSrchTxt.getValue());
        }

        param.put("slsDt", cMsg.slsDt.getValue());

        if (DISP_BY_TEAM_NM.equals(cMsg.dplyBy01ItemNm.getValue())
                || DISP_BY_ZONE_NM.equals(cMsg.dplyBy01ItemNm.getValue())
                || DISP_BY_TEAM_NM.equals(cMsg.dplyBy02ItemNm.getValue())
                || DISP_BY_ZONE_NM.equals(cMsg.dplyBy02ItemNm.getValue())
                || DISP_BY_TEAM_NM.equals(cMsg.dplyBy03ItemNm.getValue())
                || DISP_BY_ZONE_NM.equals(cMsg.dplyBy03ItemNm.getValue())
                || hasValue(cMsg.xxOrdTeamSrchTxt)
                || hasValue(cMsg.xxOrdZnSrchTxt)
                ) {
            param.put("teamSearchFlg", Y);
        }

        if (!Y.equals(cMsg.xxInclPendImptOrdFlg.getValue()) && !Y.equals(param.get("searchOrdFlg"))) {
            cMsg.setMessageInfo("NZZM0000E");
        }
        // 2019/02/13 QC#30238 Del Start
        // QC#20080 Temp 2017/07/21 Add Start
        // S21InfoLogOutput.println("@@NWAL1570 searchCondition_OpenOrder@@ UserID:" + getContextUserInfo().getUserId() + ":" + param.get("searchCondition_OpenOrder"));
        // QC#20080 Temp 2017/07/21 Add End
        // 2019/02/13 QC#30238 Del End
   }

   private String[][] createOrderCriteriaByOrderNum(final NWAL1570CMsg cMsg, boolean isMultiOrder) {

       // 2019/03/29 QC#31001 Mod Start
       if (isMultiOrder) {
           return new String[][] {
                   // Header Search Criteria
                   {SEARCH_COLUMN.ORDER_NUMBER.name()            , Y       , getValue(cMsg.xxCpoOrdNumSrchTxt_H1), N, N, N},
           };
       } else {
           return null;
       }

       //// 2019/02/13 QC#30238 Mod Start
       
       //// 2019/02/13 QC#30238 Mod Start
       ////String[][] orderCriteria = new String[][] {
       ////        // Header Search Criteria
       ////        {SEARCH_COLUMN.ORDER_NUMBER.name()            , Y       , getValue(cMsg.xxCpoOrdNumSrchTxt_H1), N, N, N},
       ////};

       ////return orderCriteria;
       //return null;
       //// 2019/02/13 QC#30238 Mod End
       // 2019/03/29 QC#31001 Mod End
   }

   private String[][] createOrderCriteriaByOrderNumAndStatus(final NWAL1570CMsg cMsg, boolean isMultiOrder) {

       // 2019/03/29 QC#31001 Mod Start
       String[][] orderCriteria;

       if (isMultiOrder){
           orderCriteria = new String[][] {
                   // Order Number
                   {SEARCH_COLUMN.ORDER_NUMBER.name()            , Y       , getValue(cMsg.xxCpoOrdNumSrchTxt_H1), N, N, N},
                   // Status
                   {SEARCH_COLUMN.HEADER_STATUS.name()           , Y       , createStatusCondition(cMsg.H, cMsg.xxSplCharTxt.getValue()), N, N, N},
                   {SEARCH_COLUMN.LINE_STATUS.name()             , Y       , createStatusCondition(cMsg.L, cMsg.xxSplCharTxt.getValue()), N, getIncludeIsNullFlag(cMsg.L, cMsg.R), N},
                   {SEARCH_COLUMN.RETURN_LINE_STATUS.name()      , Y       , createStatusCondition(cMsg.R, cMsg.xxSplCharTxt.getValue()), N, getIncludeIsNullFlag(cMsg.L, cMsg.R), N}
           };
       } else {
           orderCriteria = new String[][] {
                   // Status
                   {SEARCH_COLUMN.HEADER_STATUS.name()           , Y       , createStatusCondition(cMsg.H, cMsg.xxSplCharTxt.getValue()), N, N, N},
                   {SEARCH_COLUMN.LINE_STATUS.name()             , Y       , createStatusCondition(cMsg.L, cMsg.xxSplCharTxt.getValue()), N, getIncludeIsNullFlag(cMsg.L, cMsg.R), N},
                   {SEARCH_COLUMN.RETURN_LINE_STATUS.name()      , Y       , createStatusCondition(cMsg.R, cMsg.xxSplCharTxt.getValue()), N, getIncludeIsNullFlag(cMsg.L, cMsg.R), N}
           };
       }

       //String[][] orderCriteria = new String[][] {

       //        // 2019/02/13 QC#30238 Del Start
       //        // Order Number
       //        // {SEARCH_COLUMN.ORDER_NUMBER.name()            , Y       , getValue(cMsg.xxCpoOrdNumSrchTxt_H1), N, N, N},
       //        // 2019/02/13 QC#30238 Del End
       //        // Status
       //        {SEARCH_COLUMN.HEADER_STATUS.name()           , Y       , createStatusCondition(cMsg.H, cMsg.xxSplCharTxt.getValue()), N, N, N},
       //        {SEARCH_COLUMN.LINE_STATUS.name()             , Y       , createStatusCondition(cMsg.L, cMsg.xxSplCharTxt.getValue()), N, getIncludeIsNullFlag(cMsg.L, cMsg.R), N},
       //        {SEARCH_COLUMN.RETURN_LINE_STATUS.name()      , Y       , createStatusCondition(cMsg.R, cMsg.xxSplCharTxt.getValue()), N, getIncludeIsNullFlag(cMsg.L, cMsg.R), N}
       //};
       // 2019/03/29 QC#31001 Mod End

       return orderCriteria;
   }

    private String[][] createOrderCriteria(final NWAL1570CMsg cMsg, boolean isMultiOrder) {

        // Add Start 2017/06/22 QC#17893
        String ordToDt = null;
        String xxBookFromDt = null;
        String xxBookToDt = null;
        String xxActlShipToDt = null;
        String invToDt = null;
        String xxOrdSrcImptToDt = null;
        if (hasValue(cMsg.ordFromDt) && !hasValue(cMsg.ordToDt)) {
            ordToDt = cMsg.slsDt.getValue();
        } else {
            ordToDt = cMsg.ordToDt.getValue();
        }

        // 2018/10/09 QC#28696 Mod Start
        if (hasValue(cMsg.xxBookFromDt)) {
            xxBookFromDt = cMsg.xxBookFromDt.getValue() + SRCH_COND_FROM_TIME;
        }

        if (hasValue(cMsg.xxBookFromDt) && !hasValue(cMsg.xxBookToDt)) {
            //xxBookToDt = cMsg.slsDt.getValue();
            xxBookToDt = cMsg.slsDt.getValue() + SRCH_COND_THRU_TIME;
            // 2018/10/17 QC#28601 Mod Start
        //} else {
        } else if (hasValue(cMsg.xxBookToDt)) {
            // 2018/10/17 QC#28601 Mod End
            //xxBookToDt = cMsg.xxBookToDt.getValue();
            xxBookToDt = cMsg.xxBookToDt.getValue() + SRCH_COND_THRU_TIME;
        }
        // 2018/10/09 QC#28696 Mod Start

        if (hasValue(cMsg.xxActlShipFromDt) && !hasValue(cMsg.xxActlShipToDt)) {
            xxActlShipToDt = cMsg.slsDt.getValue();
        } else {
            xxActlShipToDt = cMsg.xxActlShipToDt.getValue();
        }
        if (hasValue(cMsg.invFromDt) && !hasValue(cMsg.invToDt)) {
            invToDt = cMsg.slsDt.getValue();
        } else {
            invToDt = cMsg.invToDt.getValue();
        }
        if (hasValue(cMsg.xxOrdSrcImptFromDt) && !hasValue(cMsg.xxOrdSrcImptToDt)) {
            xxOrdSrcImptToDt = cMsg.slsDt.getValue();
        } else {
            xxOrdSrcImptToDt = cMsg.xxOrdSrcImptToDt.getValue();
        }
        // Add End 2017/06/22 QC#17893

        String[][] orderCriteria = new String[][] {

                // Header Search Criteria
                // 2019/02/13 QC#30238 Del Start
                //{SEARCH_COLUMN.ORDER_NUMBER.name()            , Y       , getValue(cMsg.xxCpoOrdNumSrchTxt_H1)       , N, N, N},
                // 2019/02/13 QC#30238 Mod End
                {SEARCH_COLUMN.ORIGINAL_ORDER_NUMBER.name()   , Y       , getValue(cMsg.xxCpoOrdNumSrchTxt_H2)       , N, N, N},
                {SEARCH_COLUMN.CUSTOMER_PO_NUMBER.name()      , Y       , getValue(cMsg.custIssPoNumSrchTxt)         , N, N, N},
                {SEARCH_COLUMN.LEASE_PO_NUMBER.name()         , Y       , getValue(cMsg.xxLeasePoNumSrchTxt)         , N, N, N},

                {SEARCH_COLUMN.SOLD_TO_NAME.name()            , Y       , getValue(cMsg.xxSoldToAcctNmSrchTxt)       , N, N, N},
                {SEARCH_COLUMN.SOLD_TO_ACCOUNT.name()         , Y       , getValue(cMsg.xxSoldToAcctCdSrchTxt)       , N, N, N},
                {SEARCH_COLUMN.SOLD_TO_LOCATION.name()        , Y       , getValue(cMsg.xxSoldToLocCdSrchTxt)        , N, N, N},
                {SEARCH_COLUMN.SHIP_TO_NAME.name()            , Y       , getValue(cMsg.xxShipToAcctNmSrchTxt)       , N, N, N},
                {SEARCH_COLUMN.SHIP_TO_ACCOUNT.name()         , Y       , getValue(cMsg.xxShipToAcctCdSrchTxt)       , N, N, N},
                {SEARCH_COLUMN.SHIP_TO_LOCATION.name()        , Y       , getValue(cMsg.xxShipToLocCdSrchTxt)        , N, N, N},
                {SEARCH_COLUMN.BILL_TO_NAME.name()            , Y       , getValue(cMsg.xxBillToAcctNmSrchTxt)       , N, N, N},
                {SEARCH_COLUMN.BILL_TO_ACCOUNT.name()         , Y       , getValue(cMsg.xxBillToAcctCdSrchTxt)       , N, N, N},
                {SEARCH_COLUMN.BILL_TO_LOCATION.name()        , Y       , getValue(cMsg.xxBillToLocCdSrchTxt)        , N, N, N},

                {SEARCH_COLUMN.BUSINESS_UNIT.name()           , Y       , getValue(cMsg.xxCoaExtnSrchTxt)            , N, N, N},
                {SEARCH_COLUMN.BRANCH.name()                  , Y       , getValue(cMsg.xxCoaBrSrchTxt)              , N, N, N},
                {SEARCH_COLUMN.SALES_REP.name()               , Y       , getValue(cMsg.xxSlsRepTocSrchTxt)          , N, N, N},

                {SEARCH_COLUMN.ORDER_SOURCE.name()            , Y       , getValue(cMsg.xxCpoSrcTpSrchTxt)           , N, N, N},
                {SEARCH_COLUMN.LOB.name()                     , Y       , getValue(cMsg.xxDsBizLineSrchTxt)          , N, N, N},
                {SEARCH_COLUMN.DS_ORDER_CATEGORY.name()       , Y       , getValue(cMsg.xxDsOrdCatgSrchTxt)          , N, N, N},
                {SEARCH_COLUMN.DS_ORDER_TYPE.name()           , Y       , getValue(cMsg.xxDsOrdTpSrchTxt)            , N, N, N},
                {SEARCH_COLUMN.DS_SUB_REASON.name()           , Y       , getValue(cMsg.xxDsOrdRsnSrchTxt)           , N, N, N},
                {SEARCH_COLUMN.CSMP_NUMBER.name()             , Y       , getValue(cMsg.xxCsmpContrNumSrchTxt)       , N, N, N},
                {SEARCH_COLUMN.PRICE_CONTRACT_NUMBER.name()   , Y       , getValue(cMsg.xxPrcContrNumSrchTxt)        , N, N, N},
                {SEARCH_COLUMN.IMPORT_SOURCE_NUMBER.name()    , Y       , getValue(cMsg.xxOrdSrcRefNumSrchTxt)       , N, N, N},
                // 2018/08/01 QC#26304 Add Start
                {SEARCH_COLUMN.AQU_NUMBER.name()              , Y       , getValue(cMsg.xxAquNumSrchTxt)             , N, N, N},
                // 2018/08/01 QC#26304 Add End

                // Line Search Criteria
                {SEARCH_COLUMN.ITEM_NAME.name()               , Y       , getValue(cMsg.xxMdseSrchTxt)               , N, N, N},
                {SEARCH_COLUMN.ITEM_CODE.name()               , Y       , getValue(cMsg.mdseForSlsSmrySrchTxt)       , N, N, N},
                {SEARCH_COLUMN.PRODUCT_LINE_GROUP.name()      , Y       , getValue(cMsg.zerothProdCtrlSrchTxt)       , N, N, N},
                {SEARCH_COLUMN.PRODUCT_LINE.name()            , Y       , getValue(cMsg.firstProdCtrlSrchTxt)        , N, N, N},
                {SEARCH_COLUMN.PRODUCT_LEVEL_2.name()         , Y       , getValue(cMsg.scdProdCtrlSrchTxt)          , N, N, N},
                {SEARCH_COLUMN.PRODUCT_LEVEL_3.name()         , Y       , getValue(cMsg.thirdProdCtrlSrchTxt)        , N, N, N},
                {SEARCH_COLUMN.PRODUCT_LEVEL_4.name()         , Y       , getValue(cMsg.frthProdCtrlSrchTxt)         , N, N, N},
                {SEARCH_COLUMN.COA_PRODUCT.name()             , Y       , getValue(cMsg.xxCoaProdSrchTxt)            , N, N, N},
                {SEARCH_COLUMN.COA_MERCHANDISE.name()         , Y       , getValue(cMsg.xxCoaMdseTpSrchTxt)          , N, N, N},
                {SEARCH_COLUMN.MODEL.name()                   , Y       , getValue(cMsg.xxMdlSrchTxt)                , N, N, N},
                {SEARCH_COLUMN.SERIAL_NUMBER.name()           , Y       , getValue(cMsg.xxSerNumSrchTxt)             , N, N, N},
                {SEARCH_COLUMN.RETURN_REASON.name()           , Y       , getValue(cMsg.rtrnRsnCd)                   , Y, N, N},
                {SEARCH_COLUMN.LINE_CATEGORY.name()           , Y       , getValue(cMsg.xxLineCatgSrchTxt)           , N, N, N},
                {SEARCH_COLUMN.LINE_SOURCE.name()             , Y       , getValue(cMsg.xxOrdLineSrcSrchTxt)         , N, N, N},
                {SEARCH_COLUMN.WH.name()                      , Y       , getValue(cMsg.xxRtlWhSrchTxt)              , N, N, N},
                {SEARCH_COLUMN.SUB_WH.name()                  , Y       , getValue(cMsg.xxRtlSwhSrchTxt)             , N, N, N},
                {SEARCH_COLUMN.PO_VENDOR.name()               , Y       , getValue(cMsg.xxVndSrchTxt)                , N, N, N},
                {SEARCH_COLUMN.PO_ORD_NUM.name()              , Y       , getValue(cMsg.xxCpoOrdNumSrchTxt)          , N, N, N},
                {SEARCH_COLUMN.SO_NUMBER.name()               , Y       , getValue(cMsg.soNumSrchTxt)                , N, N, N},
                {SEARCH_COLUMN.INVOICE_NUMBER.name()          , Y       , getValue(cMsg.invNumSrchTxt)               , N, N, N},
                {SEARCH_COLUMN.CONTRACT_NUMBER.name()         , Y       , getValue(cMsg.xxDsContrNumSrchTxt)         , N, N, N},
                {SEARCH_COLUMN.CONFIG_NUMBER.name()           , Y       , getValue(cMsg.xxSvcConfigMstrSrchTxt)      , N, N, N},
                // QC#15760 Add Start
                {SEARCH_COLUMN.SVC_MACH_MSTR_PK.name()        , Y       , getValue(cMsg.xxSvcMachMstrSrchTxt)        , Y, N, Y},
                // QC#15760 Add End

                // Order Team
                {SEARCH_COLUMN.CREATED_BY.name()              , Y       , getValue(cMsg.xxCratByUsrSrchTxt)          , N, N, N},

                // Status
                {SEARCH_COLUMN.HEADER_STATUS.name()           , Y       , createStatusCondition(cMsg.H, cMsg.xxSplCharTxt.getValue()), Y, N, N},
                {SEARCH_COLUMN.LINE_STATUS.name()             , Y       , createStatusCondition(cMsg.L, cMsg.xxSplCharTxt.getValue()), Y, getIncludeIsNullFlag(cMsg.L, cMsg.R), N},
                {SEARCH_COLUMN.RETURN_LINE_STATUS.name()      , Y       , createStatusCondition(cMsg.R, cMsg.xxSplCharTxt.getValue()), Y, getIncludeIsNullFlag(cMsg.L, cMsg.R), N},

                // Date Criteria
                // Mod Start 2017/06/22 QC#17893
                //{SEARCH_COLUMN.ORDERED_DATE.name()            , null    , cMsg.ordFromDt.getValue()                  , cMsg.ordToDt.getValue(),          N, N},
                //{SEARCH_COLUMN.BOOKED_DATE.name()             , null    , cMsg.xxBookFromDt.getValue()               , cMsg.xxBookToDt.getValue(),       N, N},
                //{SEARCH_COLUMN.SHIPPED_DATE.name()            , null    , cMsg.xxActlShipFromDt.getValue()           , cMsg.xxActlShipToDt.getValue(),   N, N},
                //{SEARCH_COLUMN.INVOICE_DATE.name()            , null    , cMsg.invFromDt.getValue()                  , cMsg.invToDt.getValue(),          N, N},
                //{SEARCH_COLUMN.IMPORT_DATE.name()             , null    , cMsg.xxOrdSrcImptFromDt.getValue()         , cMsg.xxOrdSrcImptToDt.getValue(), N, N},
                {SEARCH_COLUMN.ORDERED_DATE.name()            , null    , cMsg.ordFromDt.getValue()                  , ordToDt,          N, N},

                // 2018/10/09 QC#28696 Mod Start
                //{SEARCH_COLUMN.BOOKED_DATE.name()             , null    , cMsg.xxBookFromDt.getValue()               , xxBookToDt,       N, N},
                {SEARCH_COLUMN.BOOKED_DATE.name()             , null    , xxBookFromDt                               , xxBookToDt,       N, N},
                // 2018/10/09 QC#28696 Mod End

                {SEARCH_COLUMN.SHIPPED_DATE.name()            , null    , cMsg.xxActlShipFromDt.getValue()           , xxActlShipToDt,   N, N},
                {SEARCH_COLUMN.INVOICE_DATE.name()            , null    , cMsg.invFromDt.getValue()                  , invToDt,          N, N},
                {SEARCH_COLUMN.IMPORT_DATE.name()             , null    , cMsg.xxOrdSrcImptFromDt.getValue()         , xxOrdSrcImptToDt, N, N},
                // Mod End 2017/06/22 QC#17893

                // Aging
                {SEARCH_COLUMN.ORD_AGING.name()               , Y       , getValue(cMsg.ordAgingBcktDescTxt)          , Y, N, N}
        };

        // 2019/03/29 QC#31001 Add Start
        if (isMultiOrder){
            List<String[]> list = new ArrayList<String[]>(Arrays.asList(orderCriteria));
            list.add(0, new String[]{SEARCH_COLUMN.ORDER_NUMBER.name()            , Y       , getValue(cMsg.xxCpoOrdNumSrchTxt_H1)       , N, N, N});
            orderCriteria = list.toArray(orderCriteria);
        }
        // 2019/03/29 QC#31001 Add End

        return orderCriteria;
    }

    private String createStatusCondition(final NWAL1570_HCMsgArray hCMsgArray, String splCharTxt) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <  hCMsgArray.length(); i++) {
            if (hCMsgArray.no(i).xxOrdHdrStsSelFlg_HS.getValue().equals(Y)) {
                if (hasValue(sb.toString())) {
                    append(sb, splCharTxt);
                }
                append(sb, hCMsgArray.no(i).ordHdrStsNm_HS.getValue());
            }
        }

        return sb.toString();
    }

    private String createStatusCondition(final NWAL1570_LCMsgArray lCMsgArray, String splCharTxt) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <  lCMsgArray.length(); i++) {
            if (lCMsgArray.no(i).xxLineStsSelFlg_LS.getValue().equals(Y)) {
                if (hasValue(sb.toString())) {
                    append(sb, splCharTxt);
                }
                append(sb, lCMsgArray.no(i).ordLineStsNm_LS.getValue());
            }
        }

        return sb.toString();
    }

    private String createStatusCondition(final NWAL1570_RCMsgArray rCMsgArray, String splCharTxt) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <  rCMsgArray.length(); i++) {
            if (rCMsgArray.no(i).xxRtrnLineStsSelFlg_RS.getValue().equals(Y)) {
                if (hasValue(sb.toString())) {
                    append(sb, splCharTxt);
                }
                append(sb, rCMsgArray.no(i).rtrnLineStsNm_RS.getValue());
            }
        }

        return sb.toString();
    }

    private String getIncludeIsNullFlag(final NWAL1570_LCMsgArray lCMsgArray, final NWAL1570_RCMsgArray rCMsgArray) {

        boolean lineStsChkFlg = false;
        boolean rtrnLineStsChkFlg = false;
        String rtrnFlg = null;
        for (int i = 0; i <  lCMsgArray.length(); i++) {
            if (lCMsgArray.no(i).xxLineStsSelFlg_LS.getValue().equals(Y)) {
                lineStsChkFlg = true;
                break;
            }
        }
        for (int i = 0; i <  rCMsgArray.length(); i++) {
            if (rCMsgArray.no(i).xxRtrnLineStsSelFlg_RS.getValue().equals(Y)) {
                rtrnLineStsChkFlg = true;
                break;
            }
        }

        if (lineStsChkFlg && rtrnLineStsChkFlg) {
            rtrnFlg = Y;
        } else {
            rtrnFlg = N;
        }
        return rtrnFlg;
    }

   private String createSearchCondition(final NWAL1570CMsg cMsg, final Map<String, Serializable> param, final SEARCH_STATUS searchStatus, final String[][] orderCriteriaList) {

        setSearchStatus(param, searchStatus);

        if (N.equals((String) param.get(searchStatus.getValue()))) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        for (String[] orderCriteria : orderCriteriaList) {
            if (orderCriteria[0].endsWith("_DATE")) {
                append(sb, createSearchConditionByDate(param, searchStatus, orderCriteria[0], orderCriteria[2], orderCriteria[3]));
            } else {
                append(sb, createSearchCondition(param, searchStatus, orderCriteria[0], orderCriteria[1], orderCriteria[2], Y.equals(orderCriteria[3]), cMsg.xxSplCharTxt.getValue(), Y.equals(orderCriteria[4]), Y.equals(orderCriteria[5])));
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

    private String createSearchCondition(Map<String, Serializable> param, SEARCH_STATUS searchStatus, String searchColumn, String inclFlg, String searchCondition, boolean isEqualFlg, String splCharTxt, boolean inclIsNullFlg, boolean isNumberFlg) {

        if (!hasValue(searchCondition)) {
            return "";
        }
        // QC#13599 ADD Start
        String[] isUpper = new String[1];
        isUpper[0] = ZYPConstant.FLG_OFF_N; 
        // QC#13599 ADD End

        // 2018/12/06 QC#29024 Add Start
        Boolean isRealTime = false;

        if (param.containsKey("realTimeInqFlg")){
            isRealTime = Y.equals(param.get("realTimeInqFlg"));
        }
        // 2018/12/06 QC#29024 Add End

        String column = getSearchColumn(searchStatus, searchColumn,isUpper, isRealTime);

        if (SEARCH_COLUMN.RESULT_NOT_FOUND.name().equals(column)) {
            param.put(searchStatus.getValue(), N);
            return "";
        }

        if (!hasValue(column)) {
            return "";
        }

        // QC#13599 ADD Start
        if (ZYPConstant.FLG_ON_Y.equals(isUpper[0])){
            // QC#11956 ADD Start
            column = "UPPER(" + column + ")";
            // QC#11956 ADD End
        }
        // QC#13599 ADD End

        StringBuilder sb = new StringBuilder();
        if (CONDITION_IS_NULL.equals(searchCondition)) {
            append(sb, " AND ");
            append(sb, column);
            append(sb, " IS NULL");
        } else {
            String mark = EQUQL;
            String quotMark = "";
            String[] srchTxtArray = getSrchTxt(trimTail(searchCondition), splCharTxt);

            if (!isEqualFlg) {
                mark = "LIKE";
            }

            if (!isNumberFlg) {
                quotMark = "'";
            }

            if (Y.equals(inclFlg)) {
                if (srchTxtArray == null) {
                    if (inclIsNullFlg) {
                        append(sb, " AND (", column, " ", mark, " ", quotMark, searchCondition, quotMark);
                        append(sb, " OR ");
                        append(sb, column, " ", "IS NULL");
                        append(sb, ")");
                    } else {
                        append(sb, " AND ", column, " ", mark, " ", quotMark, searchCondition, quotMark);
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
                            append(sb, column, " ", mark, " ", quotMark, srchTxtArray[i], quotMark);
                        }
                        if (inclIsNullFlg) {
                            append(sb, " OR ");
                            append(sb, column, " ", "IS NULL");
                        }
                        append(sb, ")");
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
        String[] isUpper = new String[1];
        isUpper[0] = ZYPConstant.FLG_OFF_N;
        
        // 2018/12/06 QC#29024 Add Start
        Boolean isRealTime = false;

        if (param.containsKey("realTimeInqFlg")){
            isRealTime = Y.equals(param.get("realTimeInqFlg"));
        }
        // 2018/12/06 QC#29024 Add End

        String column = getSearchColumn(searchStatus, searchColumn,isUpper, isRealTime);

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

    private String createOption(final NWAL1570CMsg cMsg, final Map<String, Serializable> param, final SEARCH_STATUS searchStatus) {

        StringBuilder sb = new StringBuilder();

        // Summary Mode
        if (RSLT_MODE.STATUS_SUMMARY.getRsltModeCd().equals(cMsg.xxRsltModeCd.getValue())
                || (RSLT_MODE.ORDER_INQUIRY.getRsltModeCd().equals(cMsg.xxRsltModeCd.getValue()) && "NWAL1570Scrn02_toInquiryByStatus".equals(cMsg.getScreenAplID()))
                || (hasValue(cMsg.xxScrId.getValue()) && cMsg.xxScrId.getValue().equals(SCRN_ID_02) && RSLT_MODE.ORDER_INQUIRY.getRsltModeCd().equals(cMsg.xxRsltModeCd.getValue()) && "NWAL1570Scrn01_ReSearchOrder".equals(cMsg.getScreenAplID()))) {

            if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                    cMsg.slsDt.getValue();
                    append(sb, " AND DCO.ACTL_SHIP_DT LIKE '", ZYPCommonFunc.subByteString(cMsg.slsDt.getValue(), 0, 6), "' || '%'");
            }
        }

        if (Y.equals(getIncludeIsNullFlag(cMsg.L, cMsg.R))) {
            // not include "ENTERD" Header only data.
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                append(sb, " AND (DOO_IN.ORD_LINE_DPLY_STS_NM IS NOT NULL OR DOO_IN.ORD_RTRN_LINE_DPLY_STS_NM IS NOT NULL)");
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                append(sb, " AND (DCO.ORD_LINE_DPLY_STS_NM IS NOT NULL OR DCO.ORD_RTRN_LINE_DPLY_STS_NM IS NOT NULL)");
            }
        }

        return sb.toString();
    }

    /**
     * get array from search text if it has "," in text field.
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

    private String getValue(EZDCStringItem item) {
        String value = item.getValue();
        return value.replaceAll("'", "''");
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

    private String getSearchColumn(final SEARCH_STATUS searchStatus, final String searchColumn, String[] isUpper, Boolean isRealTime) {

        String column = "";
        // QC#13599 ADD Start
        isUpper[0] = ZYPConstant.FLG_OFF_N; 
        // QC#13599 ADD End

        // Header Search Criteria
        if (SEARCH_COLUMN.ORDER_NUMBER.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.SRC_REF_OR_CPO_ORD_NUM";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.SRC_REF_OR_CPO_ORD_NUM";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.SRC_REF_OR_CPO_ORD_NUM";
            } else if (SEARCH_STATUS.QUICK.getType() == searchStatus.getType()) {
                column = "C.CPO_ORD_NUM";
            }
        } else if (SEARCH_COLUMN.ORIGINAL_ORDER_NUMBER.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.ADD_ORIG_CPO_ORD_NUM";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.ADD_ORIG_CPO_ORD_NUM";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.ADD_ORIG_CPO_ORD_NUM";
            }
        } else if (SEARCH_COLUMN.CUSTOMER_PO_NUMBER.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                // 2019/04/03 QC#30756 Mod Start
                //column = "DOO_IN.CUST_ISS_PO_NUM";
                column = "UPPER(DOO_IN.CUST_ISS_PO_NUM)";
                // 2019/04/03 QC#30756 Mod End
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                // 2019/04/03 QC#30756 Mod Start
                //column = "DCO.CUST_ISS_PO_NUM";
                column = "UPPER(DCO.CUST_ISS_PO_NUM)";
                // 2019/04/03 QC#30756 Mod End
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                // 2019/04/03 QC#30756 Mod Start
                //column = "DOO2.CUST_ISS_PO_NUM";
                column = "UPPER(DOO2.CUST_ISS_PO_NUM)";
                // 2019/04/03 QC#30756 Mod End
            } else if (SEARCH_STATUS.QUICK.getType() == searchStatus.getType()) {
                // 2019/04/03 QC#30756 Mod Start
                //column = "C.CUST_ISS_PO_NUM";
                column = "UPPER(C.CUST_ISS_PO_NUM)";
                // 2019/04/03 QC#30756 Mod End
            }
        } else if (SEARCH_COLUMN.LEASE_PO_NUMBER.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                // 2019/04/03 QC#30756 Mod Start
                //column = "DOO_IN.LEASE_CMPY_PO_NUM";
                column = "UPPER(DOO_IN.LEASE_CMPY_PO_NUM)";
                // 2019/04/03 QC#30756 Mod End
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                // 2019/04/03 QC#30756 Mod Start
                //column = "DCO.LEASE_CMPY_PO_NUM";
                column = "UPPER(DCO.LEASE_CMPY_PO_NUM)";
                // 2019/04/03 QC#30756 Mod End
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                // 2019/04/03 QC#30756 Mod Start
                //column = "DOO2.LEASE_CMPY_PO_NUM";
                column = "UPPER(DOO2.LEASE_CMPY_PO_NUM)";
                // 2019/04/03 QC#30756 Mod End
            } else if (SEARCH_STATUS.QUICK.getType() == searchStatus.getType()) {
                // 2019/04/03 QC#30756 Mod Start
                //column = "C.LEASE_CMPY_PO_NUM";
                column = "UPPER(C.LEASE_CMPY_PO_NUM)";
                // 2019/04/03 QC#30756 Mod End
            }
        } else if (SEARCH_COLUMN.SOLD_TO_NAME.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.SOLD_TO_CUST_ACCT_NM";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.SOLD_TO_CUST_ACCT_NM";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.SOLD_TO_CUST_ACCT_NM";
            }
        } else if (SEARCH_COLUMN.SOLD_TO_ACCOUNT.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.SOLD_TO_CUST_ACCT_CD";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.SOLD_TO_CUST_ACCT_CD";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.ADD_ORIG_CPO_ORD_NUM";
            }
        } else if (SEARCH_COLUMN.SOLD_TO_LOCATION.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.SOLD_TO_CUST_LOC_CD";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.SOLD_TO_CUST_LOC_CD";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.SOLD_TO_CUST_LOC_CD";
            }
        } else if (SEARCH_COLUMN.SHIP_TO_NAME.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                // 2018/12/06 QC#29024 Mod Start
                if (isRealTime) {
                    //column = "DOO_IN.SHIP_TO_CUST_ACCT_NM";
                    StringBuilder sb = new StringBuilder();
                    sb.append("CASE WHEN I.INV_NUM IS NULL THEN");
                    sb.append(" DOO_IN.SHIP_TO_CUST_ACCT_NM ");
                    sb.append(" ELSE ");
                    sb.append(" IB.SHIP_TO_CUST_ACCT_NM ");
                    sb.append(" END ");
                    column = sb.toString();
                } else {
                    column = "DOO_IN.SHIP_TO_CUST_ACCT_NM";
                }
                // 2018/12/06 QC#29024 Mod End
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.SHIP_TO_CUST_ACCT_NM";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.SHIP_TO_CUST_ACCT_NM";
            }
        } else if (SEARCH_COLUMN.SHIP_TO_ACCOUNT.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.SHIP_TO_CUST_ACCT_CD";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.SHIP_TO_CUST_ACCT_NM";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.SHIP_TO_CUST_ACCT_CD";
            }
        } else if (SEARCH_COLUMN.SHIP_TO_LOCATION.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.SHIP_TO_CUST_LOC_CD";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.SHIP_TO_CUST_LOC_CD";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.SHIP_TO_CUST_LOC_CD";
            }
        } else if (SEARCH_COLUMN.BILL_TO_NAME.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                // 2018/12/06 QC#29024 Mod Start
                if (isRealTime) {
                    //column = "DOO_IN.BILL_TO_CUST_ACCT_NM";
                    StringBuilder sb = new StringBuilder();
                    sb.append("CASE WHEN I.INV_NUM IS NULL THEN");
                    sb.append(" DOO_IN.BILL_TO_CUST_ACCT_NM ");
                    sb.append(" ELSE ");
                    sb.append(" I.BILL_TO_CUST_ACCT_NM ");
                    sb.append(" END ");
                    column = sb.toString();
                } else {
                    column = "DOO_IN.BILL_TO_CUST_ACCT_NM";
                }
                // 2018/12/06 QC#29024 Mod End
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.BILL_TO_CUST_ACCT_NM";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.BILL_TO_CUST_ACCT_NM";
            }
        } else if (SEARCH_COLUMN.BILL_TO_ACCOUNT.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.BILL_TO_CUST_ACCT_CD";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.BILL_TO_CUST_ACCT_CD";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.BILL_TO_CUST_ACCT_CD";
            }
        } else if (SEARCH_COLUMN.BILL_TO_LOCATION.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.BILL_TO_CUST_LOC_CD";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.BILL_TO_CUST_LOC_CD";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.BILL_TO_CUST_LOC_CD";
            }
        } else if (SEARCH_COLUMN.BUSINESS_UNIT.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.COA_EXTN_DESC_TXT";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.COA_EXTN_DESC_TXT";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.COA_EXTN_DESC_TXT";
            }
            // QC#13599 ADD Start
            isUpper[0] = ZYPConstant.FLG_ON_Y;
            // QC#13599 ADD End
        } else if (SEARCH_COLUMN.BRANCH.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.COA_BR_DESC_TXT";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.COA_BR_DESC_TXT";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.COA_BR_DESC_TXT";
            }
            // QC#13599 ADD Start
            isUpper[0] = ZYPConstant.FLG_ON_Y;
            // QC#13599 ADD End
        } else if (SEARCH_COLUMN.SALES_REP.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.SLS_REP_TOC_NM";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.SLS_REP_TOC_NM";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.SLS_REP_TOC_NM";
            }
            // QC#13599 ADD Start
            isUpper[0] = ZYPConstant.FLG_ON_Y;
            // QC#13599 ADD End
        } else if (SEARCH_COLUMN.ORDER_SOURCE.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.CPO_SRC_TP_DESC_TXT";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.CPO_SRC_TP_DESC_TXT";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.CPO_SRC_TP_DESC_TXT";
            }
        } else if (SEARCH_COLUMN.LOB.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.LINE_BIZ_TP_DESC_TXT";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.LINE_BIZ_TP_DESC_TXT";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.LINE_BIZ_TP_DESC_TXT";
            }
        } else if (SEARCH_COLUMN.DS_ORDER_CATEGORY.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.DS_ORD_CATG_DESC_TXT";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.DS_ORD_CATG_DESC_TXT";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.DS_ORD_CATG_DESC_TXT";
            }
        } else if (SEARCH_COLUMN.DS_ORDER_TYPE.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.DS_ORD_TP_DESC_TXT";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.DS_ORD_TP_DESC_TXT";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.DS_ORD_TP_DESC_TXT";
            }
        } else if (SEARCH_COLUMN.DS_SUB_REASON.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.DS_ORD_RSN_DESC_TXT";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.DS_ORD_RSN_DESC_TXT";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.DS_ORD_RSN_DESC_TXT";
            }
        } else if (SEARCH_COLUMN.CSMP_NUMBER.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.CSMP_CONTR_NUM";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.CSMP_CONTR_NUM";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.CSMP_CONTR_NUM";
            }
        } else if (SEARCH_COLUMN.PRICE_CONTRACT_NUMBER.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.PRC_CONTR_NM";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.PRC_CONTR_NM";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.PRC_CONTR_NM";
            }
        } else if (SEARCH_COLUMN.IMPORT_SOURCE_NUMBER.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.ORD_SRC_REF_NUM";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.ORD_SRC_REF_NUM";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.ORD_SRC_REF_NUM";
            }
        // 2018/08/01 QC#26304 Add Start
        } else if (SEARCH_COLUMN.AQU_NUMBER.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.AQU_NUM";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.AQU_NUM";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.AQU_NUM";
            } else if (SEARCH_STATUS.QUICK.getType() == searchStatus.getType()) {
                column = "C.AQU_NUM";
            }
        // 2018/08/01 QC#26304 Add End
            // Line Search Criteria
        } else if (SEARCH_COLUMN.ITEM_NAME.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                // 2018/12/06 QC#29024 Mod Start
                if (isRealTime) {
                    //column = "DOO_IN.MDSE_DESC_SHORT_TXT";
                    StringBuilder sb = new StringBuilder();
                    sb.append("CASE WHEN I.INV_NUM IS NULL THEN");
                    sb.append(" DOO_IN.MDSE_DESC_SHORT_TXT ");
                    sb.append(" ELSE ");
                    sb.append(" IL.MDSE_NM ");
                    sb.append(" END ");
                    column = sb.toString();
                } else {
                    column = "DOO_IN.MDSE_DESC_SHORT_TXT";
                }
                // 2018/12/06 QC#29024 Mod End
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.MDSE_DESC_SHORT_TXT";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.MDSE_DESC_SHORT_TXT";
            }
        } else if (SEARCH_COLUMN.ITEM_CODE.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.MDSE_CD";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.MDSE_CD";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.MDSE_CD";
            }
        } else if (SEARCH_COLUMN.PRODUCT_LINE_GROUP.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                // 2018/12/06 QC#29024 Mod Start
                if (isRealTime) {
                    //column = "DOO_IN.ZEROTH_PROD_CTRL_NM";
                    StringBuilder sb = new StringBuilder();
                    sb.append("CASE WHEN I.INV_NUM IS NULL THEN");
                    sb.append(" DOO_IN.ZEROTH_PROD_CTRL_NM ");
                    sb.append(" ELSE ");
                    sb.append(" IL.ZEROTH_PROD_CTRL_NM ");
                    sb.append(" END ");
                    column = sb.toString();
                } else {
                    column = "DOO_IN.ZEROTH_PROD_CTRL_NM";
                }
                // 2018/12/06 QC#29024 Mod End
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.ZEROTH_PROD_CTRL_NM";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.ZEROTH_PROD_CTRL_NM";
            }
            // QC#13599 ADD Start
            isUpper[0] = ZYPConstant.FLG_ON_Y;
            // QC#13599 ADD End
        } else if (SEARCH_COLUMN.PRODUCT_LINE.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                // 2018/12/06 QC#29024 Mod Start
                if (isRealTime) {
                    //column = "DOO_IN.FIRST_PROD_CTRL_NM";                
                    StringBuilder sb = new StringBuilder();
                    sb.append("CASE WHEN I.INV_NUM IS NULL THEN");
                    sb.append(" DOO_IN.FIRST_PROD_CTRL_NM ");
                    sb.append(" ELSE ");
                    sb.append(" IL.FIRST_PROD_CTRL_NM ");
                    sb.append(" END ");
                    column = sb.toString();
                } else {
                    column = "DOO_IN.FIRST_PROD_CTRL_NM";
                }
                // 2018/12/06 QC#29024 Mod End
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.FIRST_PROD_CTRL_NM";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.FIRST_PROD_CTRL_NM";
            }
            // QC#13599 ADD Start
            isUpper[0] = ZYPConstant.FLG_ON_Y;
            // QC#13599 ADD End
        } else if (SEARCH_COLUMN.PRODUCT_LEVEL_2.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                // 2018/12/06 QC#29024 Mod Start
                if (isRealTime) {
                    //column = "DOO_IN.SCD_PROD_CTRL_NM";
                    StringBuilder sb = new StringBuilder();
                    sb.append("CASE WHEN I.INV_NUM IS NULL THEN");
                    sb.append(" DOO_IN.SCD_PROD_CTRL_NM ");
                    sb.append(" ELSE ");
                    sb.append(" IL.SCD_PROD_CTRL_NM ");
                    sb.append(" END ");
                    column = sb.toString();
                } else {
                    column = "DOO_IN.SCD_PROD_CTRL_NM";
                }
                // 2018/12/06 QC#29024 Mod End
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.SCD_PROD_CTRL_NM";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.SCD_PROD_CTRL_NM";
            }
            // QC#13599 ADD Start
            isUpper[0] = ZYPConstant.FLG_ON_Y;
            // QC#13599 ADD End
        } else if (SEARCH_COLUMN.PRODUCT_LEVEL_3.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                // 2018/12/06 QC#29024 Mod Start
                if (isRealTime) {
                    //column = "DOO_IN.THIRD_PROD_CTRL_NM";
                    StringBuilder sb = new StringBuilder();
                    sb.append("CASE WHEN I.INV_NUM IS NULL THEN");
                    sb.append(" DOO_IN.THIRD_PROD_CTRL_NM ");
                    sb.append(" ELSE ");
                    sb.append(" IL.THIRD_PROD_CTRL_NM ");
                    sb.append(" END ");
                    column = sb.toString();
                } else {
                    column = "DOO_IN.THIRD_PROD_CTRL_NM";
                }
                // 2018/12/06 QC#29024 Mod End
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.THIRD_PROD_CTRL_NM";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.THIRD_PROD_CTRL_NM";
            }
            // QC#13599 ADD Start
            isUpper[0] = ZYPConstant.FLG_ON_Y;
            // QC#13599 ADD End
        } else if (SEARCH_COLUMN.PRODUCT_LEVEL_4.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                // 2018/12/06 QC#29024 Mod Start
                if (isRealTime) {
                    //column = "DOO_IN.FRTH_PROD_CTRL_NM";
                    StringBuilder sb = new StringBuilder();
                    sb.append("CASE WHEN I.INV_NUM IS NULL THEN");
                    sb.append(" DOO_IN.FRTH_PROD_CTRL_NM ");
                    sb.append(" ELSE ");
                    sb.append(" IL.FRTH_PROD_CTRL_NM ");
                    sb.append(" END ");
                    column = sb.toString();
                } else {
                    column = "DOO_IN.FRTH_PROD_CTRL_NM";
                }
                // 2018/12/06 QC#29024 Mod End
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.FRTH_PROD_CTRL_NM";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.FRTH_PROD_CTRL_NM";
            }
            // QC#13599 ADD Start
            isUpper[0] = ZYPConstant.FLG_ON_Y;
            // QC#13599 ADD End
        } else if (SEARCH_COLUMN.COA_PRODUCT.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                // 2018/12/06 QC#29024 Mod Start
                if (isRealTime) {
                    //column = "DOO_IN.COA_PROD_DESC_TXT";
                    StringBuilder sb = new StringBuilder();
                    sb.append("CASE WHEN I.INV_NUM IS NULL THEN");
                    sb.append(" DOO_IN.COA_PROD_DESC_TXT ");
                    sb.append(" ELSE ");
                    sb.append(" IL.COA_PROD_NM ");
                    sb.append(" END ");
                    column = sb.toString();
                } else {
                    column = "DOO_IN.COA_PROD_DESC_TXT";
                }
                // 2018/12/06 QC#29024 Mod End
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.COA_PROD_DESC_TXT";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.COA_PROD_DESC_TXT";
            }
            // QC#13599 ADD Start
            isUpper[0] = ZYPConstant.FLG_ON_Y;
            // QC#13599 ADD End
        } else if (SEARCH_COLUMN.COA_MERCHANDISE.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.COA_MDSE_TP_DESC_TXT";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.COA_MDSE_TP_DESC_TXT";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.COA_MDSE_TP_DESC_TXT";
            }
            // QC#13599 ADD Start
            isUpper[0] = ZYPConstant.FLG_ON_Y;
            // QC#13599 ADD End
        } else if (SEARCH_COLUMN.MODEL.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.T_MDL_NM";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.T_MDL_NM";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.T_MDL_NM";
            }
        } else if (SEARCH_COLUMN.SERIAL_NUMBER.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.SER_NUM";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.SER_NUM";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.SER_NUM";
            }
        } else if (SEARCH_COLUMN.RETURN_REASON.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.RTRN_RSN_CD";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.RTRN_RSN_CD";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.RTRN_RSN_CD";
            }
        } else if (SEARCH_COLUMN.LINE_CATEGORY.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.DS_ORD_LINE_CATG_DESC_TXT";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.DS_ORD_LINE_CATG_DESC_TXT";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.DS_ORD_LINE_CATG_DESC_TXT";
            }
        } else if (SEARCH_COLUMN.LINE_SOURCE.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.ORD_LINE_SRC_NM";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.ORD_LINE_SRC_NM";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.ORD_LINE_SRC_NM";
            }
        } else if (SEARCH_COLUMN.WH.name().equals(searchColumn)) {
            // 2018/12/14 QC#29286 Mod Start
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.RTL_WH_DESC_TXT";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.RTL_WH_DESC_TXT";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.RTL_WH_DESC_TXT";
            }
            //if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
            //    column = "DOO_IN.RTL_WH_NM";
            //} else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
            //    column = "DCO.RTL_WH_NM";
            //} else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
            //    column = "DOO2.RTL_WH_NM";
            //}
            // 2018/12/14 QC#29286 Mod End
        } else if (SEARCH_COLUMN.SUB_WH.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.RTL_SWH_NM";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.RTL_SWH_NM";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.RTL_SWH_NM";
            }
        } else if (SEARCH_COLUMN.PO_VENDOR.name().equals(searchColumn)) {
            // QC#13415 MOD Start
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
//                column = "DOO.VND_NM";
                column = "DOO_IN.DPLY_VND_NM";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
//                column = "DCO.VND_NM";
                column = "DCO.DPLY_VND_NM";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
//                column = "DOO2.VND_NM";
                column = "DOO2.DPLY_VND_NM";
            }
            // QC#13415 MOD End
            // QC#13599 ADD Start
            isUpper[0] = ZYPConstant.FLG_ON_Y;
            // QC#13599 ADD End
        } else if (SEARCH_COLUMN.PO_ORD_NUM.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.PO_ORD_NUM";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.PO_ORD_NUM";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.PO_ORD_NUM";
            }
        } else if (SEARCH_COLUMN.SO_NUMBER.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.SO_NUM";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.SO_NUM";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.SO_NUM";
            }
        } else if (SEARCH_COLUMN.INVOICE_NUMBER.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                // 2018/12/06 QC#29024 Mod Start
                if (isRealTime) {
                    column = "I.INV_NUM";
                } else {
                    column = "DOO_IN.INV_NUM";
                }
                //column = "DOO.INV_NUM";
                // 2018/12/06 QC#29024 Mod End
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.INV_NUM";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.INV_NUM";
            }
        } else if (SEARCH_COLUMN.CONTRACT_NUMBER.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.DS_CONTR_NUM";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.DS_CONTR_NUM";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.DS_CONTR_NUM";
            }
        } else if (SEARCH_COLUMN.CONFIG_NUMBER.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.SVC_CONFIG_MSTR_PK";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.SVC_CONFIG_MSTR_PK";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.SVC_CONFIG_MSTR_PK";
            }
            // QC#15760 Add Start
        } else if (SEARCH_COLUMN.SVC_MACH_MSTR_PK.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.SVC_MACH_MSTR_PK";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.SVC_MACH_MSTR_PK";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.SVC_MACH_MSTR_PK";
            }
            // QC#15760 Add End
        } else if (SEARCH_COLUMN.CREATED_BY.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                // 2017/09/21 QC#21086 Mod Start
                //column = "DOO.CRAT_BY_USR_ID";
                column = "DOO_IN.DS_CPO_CRAT_USR_ID";
                // 2017/09/21 QC#21086 Mod End
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                // 2017/09/21 QC#21086 Mod Start
                //column = "DCO.CRAT_BY_USR_ID";
                column = "DCO.DS_CPO_CRAT_USR_ID";
                // 2017/09/21 QC#21086 Mod End
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                // 2017/09/21 QC#21086 Mod Start
                //column = "DOO2.CRAT_BY_USR_ID";
                column = "DOO2.DS_CPO_CRAT_USR_ID";
                // 2017/09/21 QC#21086 Mod End
            }
            // QC#13599 ADD Start
            isUpper[0] = ZYPConstant.FLG_ON_Y;
            // QC#13599 ADD End
            //Aging
        } else if (SEARCH_COLUMN.ORD_AGING.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.ORD_AGING_BCKT_DESC_TXT";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.ORD_AGING_BCKT_DESC_TXT";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.ORD_AGING_BCKT_DESC_TXT";
            }
            // Status
        } else if (SEARCH_COLUMN.HEADER_STATUS.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.ORD_HDR_DPLY_STS_NM";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.ORD_HDR_DPLY_STS_NM";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "";
            }
        } else if (SEARCH_COLUMN.LINE_STATUS.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.ORD_LINE_DPLY_STS_NM";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.ORD_LINE_DPLY_STS_NM";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "";
            }
        } else if (SEARCH_COLUMN.RETURN_LINE_STATUS.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.ORD_RTRN_LINE_DPLY_STS_NM";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.ORD_RTRN_LINE_DPLY_STS_NM";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "";
            }
            // Date Criteria
        } else if (SEARCH_COLUMN.ORDERED_DATE.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.CPO_ORD_TS";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.CPO_ORD_TS";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.CPO_ORD_TS";
            }
        } else if (SEARCH_COLUMN.BOOKED_DATE.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.ORD_BOOK_TS";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.ORD_BOOK_TS";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.ORD_BOOK_TS";
            }
        } else if (SEARCH_COLUMN.SHIPPED_DATE.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.ACTL_SHIP_DT";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.ACTL_SHIP_DT";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.ACTL_SHIP_DT";
            }
        } else if (SEARCH_COLUMN.INVOICE_DATE.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                // 2018/12/06 QC#29024 Mod Start
                if (isRealTime) {
                    column = "I.INV_DT";
                } else {
                    column = "DOO_IN.INV_DT";
                }
              //column = "DOO.INV_DT";
                // 2018/12/06 QC#29024 Mod End
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.INV_DT";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.INV_DT";
            }
        } else if (SEARCH_COLUMN.IMPORT_DATE.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.ORD_SRC_IMPT_DT";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.ORD_SRC_IMPT_DT";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.ORD_SRC_IMPT_DT";
            }
        } else if (SEARCH_COLUMN.PEND_IMPT_SMRY_AMT.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.PEND_IMPT_SMRY_AMT";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.PEND_IMPT_SMRY_AMT";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.PEND_IMPT_SMRY_AMT";
            }
         } else if (SEARCH_COLUMN.ENT_SMRY_AMT.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.ENT_SMRY_AMT";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.ENT_SMRY_AMT";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.ENT_SMRY_AMT";
            }
         } else if (SEARCH_COLUMN.DI_HLD_SMRY_AMT.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.DI_HLD_SMRY_AMT";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.DI_HLD_SMRY_AMT";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.DI_HLD_SMRY_AMT";
            }
         } else if (SEARCH_COLUMN.VLD_SMRY_AMT.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.VLD_SMRY_AMT";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.VLD_SMRY_AMT";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.VLD_SMRY_AMT";
            }
         } else if (SEARCH_COLUMN.PRFT_SMRY_AMT.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.PRFT_SMRY_AMT";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.PRFT_SMRY_AMT";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.PRFT_SMRY_AMT";
            }
         } else if (SEARCH_COLUMN.CR_HLD_SMRY_AMT.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.CR_HLD_SMRY_AMT";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.CR_HLD_SMRY_AMT";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.CR_HLD_SMRY_AMT";
            }
         } else if (SEARCH_COLUMN.SPLY_ABUSE_SMRY_AMT.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.SPLY_ABUSE_SMRY_AMT";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.SPLY_ABUSE_SMRY_AMT";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.SPLY_ABUSE_SMRY_AMT";
            }
         } else if (SEARCH_COLUMN.PEND_RE_SUBMT_SMRY_AMT.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.PEND_RE_SUBMT_SMRY_AMT";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.PEND_RE_SUBMT_SMRY_AMT";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.PEND_RE_SUBMT_SMRY_AMT";
            }
         } else if (SEARCH_COLUMN.BOOK_SMRY_AMT.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.BOOK_SMRY_AMT";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.BOOK_SMRY_AMT";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.BOOK_SMRY_AMT";
            }
         // 2017/11/21 QC#22550 Add Start
         } else if (SEARCH_COLUMN.AWAIT_DROP_SHIP_SMRY_AMT.name().equals(searchColumn)) {
             if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                 column = "DOO_IN.AWAIT_DROP_SHIP_SMRY_AMT";
             } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                 column = "DCO.AWAIT_DROP_SHIP_SMRY_AMT";
             } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                 column = "DOO2.AWAIT_DROP_SHIP_SMRY_AMT";
             }
         // 2017/11/21 QC#22550 Add End
         // 2017/11/21 QC#22550 Mod Start
         //} else if (SEARCH_COLUMN.SO_CANC_SMRY_AMT.name().equals(searchColumn)) {
         //   if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
         //       column = "DOO.SO_CANC_SMRY_AMT";
         //   } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
         //       column = "DCO.SO_CANC_SMRY_AMT";
         //   } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
         //       column = "DOO2.SO_CANC_SMRY_AMT";
         //   }
         } else if (SEARCH_COLUMN.PEND_RE_ALLOC_SMRY_AMT.name().equals(searchColumn)) {
             if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                 column = "DOO_IN.PEND_RE_ALLOC_SMRY_AMT";
             } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                 column = "DCO.PEND_RE_ALLOC_SMRY_AMT";
             } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                 column = "DOO2.PEND_RE_ALLOC_SMRY_AMT";
             }
         // 2017/11/21 QC#22550 Mod End
         } else if (SEARCH_COLUMN.PO_CANC_SMRY_AMT.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.PO_CANC_SMRY_AMT";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.PO_CANC_SMRY_AMT";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.PO_CANC_SMRY_AMT";
            }
         } else if (SEARCH_COLUMN.PEND_FUFL_SMRY_AMT.name().equals(searchColumn)) {
             if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                 column = "DOO_IN.PEND_FUFL_SMRY_AMT";
             } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                 column = "DCO.PEND_FUFL_SMRY_AMT";
             } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                 column = "DOO2.PEND_FUFL_SMRY_AMT";
             }
         } else if (SEARCH_COLUMN.PEND_ALLOC_SMRY_AMT.name().equals(searchColumn)) {
             if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                 column = "DOO_IN.PEND_ALLOC_SMRY_AMT";
             } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                 column = "DCO.PEND_ALLOC_SMRY_AMT";
             } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                 column = "DOO2.PEND_ALLOC_SMRY_AMT";
             }
         } else if (SEARCH_COLUMN.BO_SMRY_AMT.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.BO_SMRY_AMT";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.BO_SMRY_AMT";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.BO_SMRY_AMT";
            }
         } else if (SEARCH_COLUMN.PEND_PICK_SMRY_AMT.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.PEND_PICK_SMRY_AMT";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.PEND_PICK_SMRY_AMT";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.PEND_PICK_SMRY_AMT";
            }
         } else if (SEARCH_COLUMN.DELY_TO_SHOP_SMRY_AMT.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.DELY_TO_SHOP_SMRY_AMT";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.DELY_TO_SHOP_SMRY_AMT";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.DELY_TO_SHOP_SMRY_AMT";
            }
         } else if (SEARCH_COLUMN.IN_SHOP_CONFIG_SMRY_AMT.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.IN_SHOP_CONFIG_SMRY_AMT";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.IN_SHOP_CONFIG_SMRY_AMT";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.IN_SHOP_CONFIG_SMRY_AMT";
            }
         } else if (SEARCH_COLUMN.PEND_SHIP_SMRY_AMT.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.PEND_SHIP_SMRY_AMT";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.PEND_SHIP_SMRY_AMT";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.PEND_SHIP_SMRY_AMT";
            }
         } else if (SEARCH_COLUMN.SHIP_SMRY_AMT.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.SHIP_SMRY_AMT";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.SHIP_SMRY_AMT";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.SHIP_SMRY_AMT";
            }
         } else if (SEARCH_COLUMN.PEND_DELY_CONF_SMRY_AMT.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.PEND_DELY_CONF_SMRY_AMT";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.PEND_DELY_CONF_SMRY_AMT";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.PEND_DELY_CONF_SMRY_AMT";
            }
         } else if (SEARCH_COLUMN.PEND_ISTL_SMRY_AMT.name().equals(searchColumn)) {
             if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                 column = "DOO_IN.PEND_ISTL_SMRY_AMT";
             } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                 column = "DCO.PEND_ISTL_SMRY_AMT";
             } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                 column = "DOO2.PEND_ISTL_SMRY_AMT";
             }
         } else if (SEARCH_COLUMN.ON_LOAN_SMRY_AMT.name().equals(searchColumn)) {
             if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                 column = "DOO_IN.ON_LOAN_SMRY_AMT";
             } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                 column = "DCO.ON_LOAN_SMRY_AMT";
             } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                 column = "DOO2.ON_LOAN_SMRY_AMT";
             }
         } else if (SEARCH_COLUMN.WAIT_RCPT_SMRY_AMT.name().equals(searchColumn)) {
             if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                 column = "DOO_IN.WAIT_RCPT_SMRY_AMT";
             } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                 column = "DCO.WAIT_RCPT_SMRY_AMT";
             } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                 column = "DOO2.WAIT_RCPT_SMRY_AMT";
             }
         } else if (SEARCH_COLUMN.PEND_RTRN_SMRY_AMT.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.PEND_RTRN_SMRY_AMT";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.PEND_RTRN_SMRY_AMT";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.PEND_RTRN_SMRY_AMT";
            }
         } else if (SEARCH_COLUMN.PEND_INSP_SMRY_AMT.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.PEND_INSP_SMRY_AMT";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.PEND_INSP_SMRY_AMT";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.PEND_INSP_SMRY_AMT";
            }
         } else if (SEARCH_COLUMN.RWS_CANC_SMRY_AMT.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.RWS_CANC_SMRY_AMT";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.RWS_CANC_SMRY_AMT";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.RWS_CANC_SMRY_AMT";
            }
         } else if (SEARCH_COLUMN.PEND_INV_SMRY_AMT.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.PEND_INV_SMRY_AMT";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.PEND_INV_SMRY_AMT";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.PEND_INV_SMRY_AMT";
            }
         } else if (SEARCH_COLUMN.BLLG_HLD_SMRY_AMT.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.BLLG_HLD_SMRY_AMT";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.BLLG_HLD_SMRY_AMT";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.BLLG_HLD_SMRY_AMT";
            }
         // 2017/11/21 QC#22550 Add Start
         } else if (SEARCH_COLUMN.PEND_DLR_ISTL_SMRY_AMT.name().equals(searchColumn)) {
             if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                 column = "DOO_IN.PEND_DLR_ISTL_SMRY_AMT";
             } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                 column = "DCO.PEND_DLR_ISTL_SMRY_AMT";
             } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                 column = "DOO2.PEND_DLR_ISTL_SMRY_AMT";
             }
         // 2017/11/21 QC#22550 Add End
         } else if (SEARCH_COLUMN.SHIP_CLO_SMRY_AMT.name().equals(searchColumn)) {
             if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                 column = "DOO_IN.SHIP_CLO_SMRY_AMT";
             } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                 column = "DCO.SHIP_CLO_SMRY_AMT";
             } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                 column = "DOO2.SHIP_CLO_SMRY_AMT";
             }
         } else if (SEARCH_COLUMN.INV_SMRY_AMT.name().equals(searchColumn)) {
            if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                column = "DOO_IN.INV_SMRY_AMT";
            } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                column = "DCO.INV_SMRY_AMT";
            } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                column = "DOO2.INV_SMRY_AMT";
            }
         } else if (SEARCH_COLUMN.CLO_SMRY_AMT.name().equals(searchColumn)) {
             if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                 column = "DOO_IN.CLO_SMRY_AMT";
             } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                 column = "DCO.CLO_SMRY_AMT";
             } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                 column = "DOO2.CLO_SMRY_AMT";
             }
         } else if (SEARCH_COLUMN.CLO_SMRY_AMT.name().equals(searchColumn)) {
             if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                 column = "DOO_IN.CLO_SMRY_AMT";
             } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                 column = "DCO.CLO_SMRY_AMT";
             } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                 column = "DOO2.CLO_SMRY_AMT";
             }
         } else if (SEARCH_COLUMN.CLO_LOAN_RTRN_SMRY_AMT.name().equals(searchColumn)) {
             if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                 column = "DOO_IN.CLO_LOAN_RTRN_SMRY_AMT";
             } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                 column = "DCO.CLO_LOAN_RTRN_SMRY_AMT";
             } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                 column = "DOO2.CLO_LOAN_RTRN_SMRY_AMT";
             }
         } else if (SEARCH_COLUMN.CLO_LOAN_SOLD_SMRY_AMT.name().equals(searchColumn)) {
             if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                 column = "DOO_IN.CLO_LOAN_SOLD_SMRY_AMT";
             } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                 column = "DCO.CLO_LOAN_SOLD_SMRY_AMT";
             } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                 column = "DOO2.CLO_LOAN_SOLD_SMRY_AMT";
             }
         } else if (SEARCH_COLUMN.PEND_IMPT_SMRY_CNT.name().equals(searchColumn)) {
             if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                 column = "DOO_IN.PEND_IMPT_SMRY_CNT";
             } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                 column = "DCO.PEND_IMPT_SMRY_CNT";
             } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                 column = "DOO2.PEND_IMPT_SMRY_CNT";
             }
          } else if (SEARCH_COLUMN.ENT_SMRY_CNT.name().equals(searchColumn)) {
             if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                 column = "DOO_IN.ENT_SMRY_CNT";
             } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                 column = "DCO.ENT_SMRY_CNT";
             } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                 column = "DOO2.ENT_SMRY_CNT";
             }
          } else if (SEARCH_COLUMN.DI_HLD_SMRY_CNT.name().equals(searchColumn)) {
             if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                 column = "DOO_IN.DI_HLD_SMRY_CNT";
             } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                 column = "DCO.DI_HLD_SMRY_CNT";
             } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                 column = "DOO2.DI_HLD_SMRY_CNT";
             }
          } else if (SEARCH_COLUMN.VLD_SMRY_CNT.name().equals(searchColumn)) {
             if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                 column = "DOO_IN.VLD_SMRY_CNT";
             } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                 column = "DCO.VLD_SMRY_CNT";
             } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                 column = "DOO2.VLD_SMRY_CNT";
             }
          } else if (SEARCH_COLUMN.PRFT_SMRY_CNT.name().equals(searchColumn)) {
             if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                 column = "DOO_IN.PRFT_SMRY_CNT";
             } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                 column = "DCO.PRFT_SMRY_CNT";
             } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                 column = "DOO2.PRFT_SMRY_CNT";
             }
          } else if (SEARCH_COLUMN.CR_HLD_SMRY_CNT.name().equals(searchColumn)) {
             if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                 column = "DOO_IN.CR_HLD_SMRY_CNT";
             } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                 column = "DCO.CR_HLD_SMRY_CNT";
             } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                 column = "DOO2.CR_HLD_SMRY_CNT";
             }
          } else if (SEARCH_COLUMN.SPLY_ABUSE_SMRY_CNT.name().equals(searchColumn)) {
             if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                 column = "DOO_IN.SPLY_ABUSE_SMRY_CNT";
             } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                 column = "DCO.SPLY_ABUSE_SMRY_CNT";
             } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                 column = "DOO2.SPLY_ABUSE_SMRY_CNT";
             }
          } else if (SEARCH_COLUMN.PEND_RE_SUBMT_SMRY_CNT.name().equals(searchColumn)) {
             if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                 column = "DOO_IN.PEND_RE_SUBMT_SMRY_CNT";
             } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                 column = "DCO.PEND_RE_SUBMT_SMRY_CNT";
             } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                 column = "DOO2.PEND_RE_SUBMT_SMRY_CNT";
             }
          } else if (SEARCH_COLUMN.BOOK_SMRY_CNT.name().equals(searchColumn)) {
             if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                 column = "DOO_IN.BOOK_SMRY_CNT";
             } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                 column = "DCO.BOOK_SMRY_CNT";
             } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                 column = "DOO2.BOOK_SMRY_CNT";
             }
          // 2017/11/21 QC#22550 Add Start
          } else if (SEARCH_COLUMN.AWAIT_DROP_SHIP_SMRY_CNT.name().equals(searchColumn)) {
              if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                  column = "DOO_IN.AWAIT_DROP_SHIP_SMRY_CNT";
              } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                  column = "DCO.AWAIT_DROP_SHIP_SMRY_CNT";
              } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                  column = "DOO2.AWAIT_DROP_SHIP_SMRY_CNT";
              }
          // 2017/11/21 QC#22550 Add End
          // 2017/11/21 QC#22550 Mod Start
          //} else if (SEARCH_COLUMN.SO_CANC_SMRY_CNT.name().equals(searchColumn)) {
          //   if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
          //       column = "DOO.SO_CANC_SMRY_CNT";
          //   } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
          //       column = "DCO.SO_CANC_SMRY_CNT";
          //   } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
          //       column = "DOO2.SO_CANC_SMRY_CNT";
          //   }
          } else if (SEARCH_COLUMN.PEND_RE_ALLOC_SMRY_CNT.name().equals(searchColumn)) {
              if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                  column = "DOO_IN.PEND_RE_ALLOC_SMRY_CNT";
              } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                  column = "DCO.PEND_RE_ALLOC_SMRY_CNT";
              } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                  column = "DOO2.PEND_RE_ALLOC_SMRY_CNT";
              }
          // 2017/11/21 QC#22550 Mod End
          } else if (SEARCH_COLUMN.PO_CANC_SMRY_CNT.name().equals(searchColumn)) {
             if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                 column = "DOO_IN.PO_CANC_SMRY_CNT";
             } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                 column = "DCO.PO_CANC_SMRY_CNT";
             } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                 column = "DOO2.PO_CANC_SMRY_CNT";
             }
          } else if (SEARCH_COLUMN.PEND_FUFL_SMRY_CNT.name().equals(searchColumn)) {
             if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                 column = "DOO_IN.PEND_FUFL_SMRY_CNT";
             } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                 column = "DCO.PEND_FUFL_SMRY_CNT";
             } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                 column = "DOO2.PEND_FUFL_SMRY_CNT";
             }
          } else if (SEARCH_COLUMN.PEND_ALLOC_SMRY_CNT.name().equals(searchColumn)) {
              if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                  column = "DOO_IN.PEND_ALLOC_SMRY_CNT";
              } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                  column = "DCO.PEND_ALLOC_SMRY_CNT";
              } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                  column = "DOO2.PEND_ALLOC_SMRY_CNT";
              }
          } else if (SEARCH_COLUMN.BO_SMRY_CNT.name().equals(searchColumn)) {
             if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                 column = "DOO_IN.BO_SMRY_CNT";
             } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                 column = "DCO.BO_SMRY_CNT";
             } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                 column = "DOO2.BO_SMRY_CNT";
             }
          } else if (SEARCH_COLUMN.PEND_PICK_SMRY_CNT.name().equals(searchColumn)) {
             if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                 column = "DOO_IN.PEND_PICK_SMRY_CNT";
             } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                 column = "DCO.PEND_PICK_SMRY_CNT";
             } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                 column = "DOO2.PEND_PICK_SMRY_CNT";
             }
          } else if (SEARCH_COLUMN.DELY_TO_SHOP_SMRY_CNT.name().equals(searchColumn)) {
             if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                 column = "DOO_IN.DELY_TO_SHOP_SMRY_CNT";
             } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                 column = "DCO.DELY_TO_SHOP_SMRY_CNT";
             } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                 column = "DOO2.DELY_TO_SHOP_SMRY_CNT";
             }
          } else if (SEARCH_COLUMN.IN_SHOP_CONFIG_SMRY_CNT.name().equals(searchColumn)) {
             if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                 column = "DOO_IN.IN_SHOP_CONFIG_SMRY_CNT";
             } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                 column = "DCO.IN_SHOP_CONFIG_SMRY_CNT";
             } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                 column = "DOO2.IN_SHOP_CONFIG_SMRY_CNT";
             }
          } else if (SEARCH_COLUMN.PEND_SHIP_SMRY_CNT.name().equals(searchColumn)) {
             if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                 column = "DOO_IN.PEND_SHIP_SMRY_CNT";
             } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                 column = "DCO.PEND_SHIP_SMRY_CNT";
             } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                 column = "DOO2.PEND_SHIP_SMRY_CNT";
             }
          } else if (SEARCH_COLUMN.SHIP_SMRY_CNT.name().equals(searchColumn)) {
             if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                 column = "DOO_IN.SHIP_SMRY_CNT";
             } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                 column = "DCO.SHIP_SMRY_CNT";
             } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                 column = "DOO2.SHIP_SMRY_CNT";
             }
          } else if (SEARCH_COLUMN.PEND_DELY_CONF_SMRY_CNT.name().equals(searchColumn)) {
             if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                 column = "DOO_IN.PEND_DELY_CONF_SMRY_CNT";
             } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                 column = "DCO.PEND_DELY_CONF_SMRY_CNT";
             } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                 column = "DOO2.PEND_DELY_CONF_SMRY_CNT";
             }
          } else if (SEARCH_COLUMN.PEND_ISTL_SMRY_CNT.name().equals(searchColumn)) {
              if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                  column = "DOO_IN.PEND_ISTL_SMRY_CNT";
              } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                  column = "DCO.PEND_ISTL_SMRY_CNT";
              } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                  column = "DOO2.PEND_ISTL_SMRY_CNT";
              }
          } else if (SEARCH_COLUMN.ON_LOAN_SMRY_CNT.name().equals(searchColumn)) {
              if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                  column = "DOO_IN.ON_LOAN_SMRY_CNT";
              } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                  column = "DCO.ON_LOAN_SMRY_CNT";
              } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                  column = "DOO2.ON_LOAN_SMRY_CNT";
              }
          } else if (SEARCH_COLUMN.WAIT_RCPT_SMRY_CNT.name().equals(searchColumn)) {
              if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                  column = "DOO_IN.WAIT_RCPT_SMRY_CNT";
              } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                  column = "DCO.WAIT_RCPT_SMRY_CNT";
              } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                  column = "DOO2.WAIT_RCPT_SMRY_CNT";
              }
          } else if (SEARCH_COLUMN.PEND_RTRN_SMRY_CNT.name().equals(searchColumn)) {
             if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                 column = "DOO_IN.PEND_RTRN_SMRY_CNT";
             } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                 column = "DCO.PEND_RTRN_SMRY_CNT";
             } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                 column = "DOO2.PEND_RTRN_SMRY_CNT";
             }
          } else if (SEARCH_COLUMN.PEND_INSP_SMRY_CNT.name().equals(searchColumn)) {
             if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                 column = "DOO_IN.PEND_INSP_SMRY_CNT";
             } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                 column = "DCO.PEND_INSP_SMRY_CNT";
             } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                 column = "DOO2.PEND_INSP_SMRY_CNT";
             }
          } else if (SEARCH_COLUMN.RWS_CANC_SMRY_CNT.name().equals(searchColumn)) {
             if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                 column = "DOO_IN.RWS_CANC_SMRY_CNT";
             } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                 column = "DCO.RWS_CANC_SMRY_CNT";
             } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                 column = "DOO2.RWS_CANC_SMRY_CNT";
             }
          } else if (SEARCH_COLUMN.PEND_INV_SMRY_CNT.name().equals(searchColumn)) {
             if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                 column = "DOO_IN.PEND_INV_SMRY_CNT";
             } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                 column = "DCO.PEND_INV_SMRY_CNT";
             } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                 column = "DOO2.PEND_INV_SMRY_CNT";
             }
          } else if (SEARCH_COLUMN.BLLG_HLD_SMRY_CNT.name().equals(searchColumn)) {
             if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                 column = "DOO_IN.BLLG_HLD_SMRY_CNT";
             } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                 column = "DCO.BLLG_HLD_SMRY_CNT";
             } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                 column = "DOO2.BLLG_HLD_SMRY_CNT";
             }
          // 2017/11/21 QC#22550 Add Start
          } else if (SEARCH_COLUMN.PEND_DLR_ISTL_SMRY_CNT.name().equals(searchColumn)) {
              if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                  column = "DOO_IN.PEND_DLR_ISTL_SMRY_CNT";
              } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                  column = "DCO.PEND_DLR_ISTL_SMRY_CNT";
              } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                  column = "DOO2.PEND_DLR_ISTL_SMRY_CNT";
              }
          // 2017/11/21 QC#22550 Add End
          } else if (SEARCH_COLUMN.SHIP_CLO_SMRY_CNT.name().equals(searchColumn)) {
              if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                  column = "DOO_IN.SHIP_CLO_SMRY_CNT";
              } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                  column = "DCO.SHIP_CLO_SMRY_CNT";
              } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                  column = "DOO2.SHIP_CLO_SMRY_CNT";
              }
          } else if (SEARCH_COLUMN.INV_SMRY_CNT.name().equals(searchColumn)) {
             if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                 column = "DOO_IN.INV_SMRY_CNT";
             } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                 column = "DCO.INV_SMRY_CNT";
             } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                 column = "DOO2.INV_SMRY_CNT";
             }
          } else if (SEARCH_COLUMN.CLO_SMRY_CNT.name().equals(searchColumn)) {
              if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                  column = "DOO_IN.CLO_SMRY_CNT";
              } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                  column = "DCO.CLO_SMRY_CNT";
              } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                  column = "DOO2.CLO_SMRY_CNT";
              }
          } else if (SEARCH_COLUMN.CLO_LOAN_RTRN_SMRY_CNT.name().equals(searchColumn)) {
              if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                  column = "DOO_IN.CLO_LOAN_RTRN_SMRY_CNT";
              } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                  column = "DCO.CLO_LOAN_RTRN_SMRY_CNT";
              } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                  column = "DOO2.CLO_LOAN_RTRN_SMRY_CNT";
              }
          } else if (SEARCH_COLUMN.CLO_LOAN_SOLD_SMRY_CNT.name().equals(searchColumn)) {
              if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                  column = "DOO_IN.CLO_LOAN_SOLD_SMRY_CNT";
              } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                  column = "DCO.CLO_LOAN_SOLD_SMRY_CNT";
              } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                  column = "DOO2.CLO_LOAN_SOLD_SMRY_CNT";
              }
          } else if (SEARCH_COLUMN.CANC_SMRY_CNT.name().equals(searchColumn)) {
             if (SEARCH_STATUS.OPEN_ORD.getType() == searchStatus.getType()) {
                 column = "DOO_IN.CANC_SMRY_CNT";
             } else if (SEARCH_STATUS.CLO_ORD.getType() == searchStatus.getType()) {
                 column = "DCO.CANC_SMRY_CNT";
             } else if (SEARCH_STATUS.PEND_IMPT_ORD.getType() == searchStatus.getType()) {
                 column = "DOO2.CANC_SMRY_CNT";
             }
        }

        return column;
    }

    /**
     * getSavedSearchOptionList
     * @param usrId user id
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSavedSearchOptionList(String usrId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("srchOptAplId", BIZ_ID);
        params.put("srchOptUsrId", usrId);

        return getSsmEZDClient().queryObjectList("getSavedSearchOptionList", params);
    }

    /**
     * getSavedSearchOptionList
     * @param bizMsg NWAL1570CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDdsontrPk(NWAL1570CMsg bizMsg) {

        NWAL1570_ACMsg recordMsg = bizMsg.A.no(bizMsg.xxRowNum.getValueInt());

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsContrNum", recordMsg.dsContrNum_A1.getValue());

        return getSsmEZDClient().queryObjectList("getDdContrPk", params);
    }

    /**
     * getOrdTeamZoneByUser
     * @param usrId user id
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrdTeamZoneByUser(NWAL1570CMsg bizMsg, String userId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("slsDt", bizMsg.slsDt.getValue());
        params.put("attrbTpCdUser", ORD_TEAM_ATTRB_TP.USER_ID);
        params.put("userId", userId);

        return getSsmEZDClient().queryObjectList("findOrderTeamZoneByUserId", params);
    }

    private class CSVResultSetHandler extends CommonResultSetHandler {

        private NWAL1570CMsg cMsg = null;

        public CSVResultSetHandler(NWAL1570CMsg cMsg) {
            this.cMsg = cMsg;
        }

        @Override
        protected void doProcessQueryResult(ResultSet result) throws SQLException {

            if (!result.next()) {
                cMsg.setMessageInfo("NZZM0000E");
                return;
            }

            // set file path to write CSV file.
            cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm("OrderInquiry"), ".csv");

            createCsvByDetail(result);

        }

        private void createCsvByDetail(ResultSet result) throws SQLException {

            // create template FMsg & create ZYPCSVOutFile.
            NWAL1570F00FMsg fMsg = new NWAL1570F00FMsg();
            NWAL1570F01FMsg f01Msg = new NWAL1570F01FMsg(); // 2017/10/2 QC#19913 Add

            // 2017/10/2 QC#19913 Mod Start
            ZYPCSVOutFile csvOutFile = null;
            List<String> csvHeader = null;

            // write 'Search Criteria' lines.
            //final CsvHeaderWriter csvHdrWriter = new CsvHeaderWriter(cMsg.xxFileData.getTempFilePath());
            //csvHdrWriter.writeSearchCriteria(cMsg);

            if (RSLT_MODE.ORDER_INQUIRY.getRsltModeCd().equals(cMsg.xxRsltModeCd.getValue())) {
                // 2018/02/19 S21_NA#19913-2 Add Start
                int[] selectCol = convertArray(ZYPGUITableColumn.getColOrder(cMsg));
                // 2018/06/05 S21_NA#24816 Del Start
//                if(selectCol.length == 0){
//                    cMsg.setMessageInfo(NWAM0949E);
//                    return;
//                }
                // 2018/06/05 S21_NA#24816 Del End
                // 2018/02/19 S21_NA#19913-2 Add End
                csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);
                // create CSV header string & write
                csvHeader = createCsvHeaderForInquiry(cMsg, fMsg);
                //csvHdrWriter.writeHeader(csvHeader);
                // 2018/02/19 S21_NA#19913-2 Mod Start
                //csvOutFile.writeHeader(csvHeader.toArray(new String[csvHeader.size()]), fMsg, ZYPGUITableColumn.getColOrder(cMsg));
                //fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(cMsg));
                csvOutFile.writeHeader(csvHeader.toArray(new String[csvHeader.size()]), fMsg, selectCol);
                fMsg.setItemOrder(selectCol);
                // 2018/02/19 S21_NA#19913-2 Mod End
            } else if (RSLT_MODE.STATUS_SUMMARY.getRsltModeCd().equals(cMsg.xxRsltModeCd.getValue())) {
                csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), f01Msg);
                // create CSV header string & write
                csvHeader = createCsvHeaderForSummary(cMsg, f01Msg);
                //csvHdrWriter.writeHeader(csvHeader);
                csvOutFile.writeHeader(csvHeader.toArray(new String[csvHeader.size()]), f01Msg, ZYPGUITableColumn.getColOrder(cMsg));
                f01Msg.setItemOrder(ZYPGUITableColumn.getColOrder(cMsg));
            }
            // 2017/10/2 QC#19913 Mod End

            List<String> column = getColumnList(result);

            // data copy to FMsg & write csv record.
            int index = 0;
            Map<String, BigDecimal> groupingMap = new HashMap<String, BigDecimal>();
            groupingMap.put(KEY_GRP_ID_01, BigDecimal.ZERO);
            groupingMap.put(KEY_GRP_ID_02, BigDecimal.ZERO);
            groupingMap.put(KEY_GRP_ID_03, BigDecimal.ZERO);

            //boolean prevFlag = false;
            boolean prevFlag = true;
            while (true) {

//                if (index >= CSV_MAX_RECORDS) {
//                    cMsg.setMessageInfo("NZZM0001W");
//                    break;
//                }

                Map<String, Object> record = getResultMap(cMsg, column, result);

                if (RSLT_MODE.ORDER_INQUIRY.getRsltModeCd().equals(cMsg.xxRsltModeCd.getValue())) {
                    // 2018/02/19 S21_NA#19913-2 Mod Start
                    //mapResultToFMsgForInquiry(cMsg, record, fMsg, index, groupingMap);
                    BigDecimal groupingId = getBigDecimal(record, SQL_COL.GROUPING_ID);
                    if (groupingId.compareTo(BigDecimal.ZERO) != 0) {
                        prevFlag = false;
                    } else {
                        mapResultToFMsgForInquiry(cMsg, record, fMsg, index, groupingMap);
                    }
                    // 2018/02/19 S21_NA#19913-2 Mod End
                } else if (RSLT_MODE.STATUS_SUMMARY.getRsltModeCd().equals(cMsg.xxRsltModeCd.getValue())) {
                    mapResultToFMsgForSummary(cMsg, record, f01Msg, index, groupingMap); // 2017/10/2 QC#19913 Mod
                }
                //2018/02/19 S21_NA#19913-2 Mod Start
                if (prevFlag) {
                    csvOutFile.write();
                    index++;
                } else{
                    prevFlag = true;
                }

//                if (prevFlag == true) {
//                    prevFlag = false;
//                    continue;
//                }
                //2018/02/19 S21_NA#19913-2 Mod End
                if (result.next() == false) { break; }
            }

            // close I/O stream.
            csvOutFile.close();

            // 2019/05/07 QC#50031 Mod Start
            List<ZYPExcelColumnStyle> columnStyles = new ArrayList<ZYPExcelColumnStyle>();
            int[][] excelPriceColArray = null; 

            String excelFileFullPath = null;

            // Mod Start 2019/05/24 QC#50325
            int[] cols = null;
            if (RSLT_MODE.ORDER_INQUIRY.getRsltModeCd().equals(cMsg.xxRsltModeCd.getValue())) {
                excelPriceColArray = NWAL1570Constant.EXCEL_COLUMNTYPE_INQUIRY;
                cols = convertArray(ZYPGUITableColumn.getColOrder(cMsg));
            } else if (RSLT_MODE.STATUS_SUMMARY.getRsltModeCd().equals(cMsg.xxRsltModeCd.getValue())) {
                excelPriceColArray = NWAL1570Constant.EXCEL_COLUMNTYPE_SUMMARY;
                cols = ZYPGUITableColumn.getColOrder(cMsg);
            }

//            int[] cols = convertArray(ZYPGUITableColumn.getColOrder(cMsg));
            // Mod End 2019/05/24 QC#50325

            if (cols == null){
                cols = new int[0];
            }

            int pos;

            for (int[] val : excelPriceColArray){
                pos = val[0];

//                if (pos < cols.length) {  // Del 2019/05/24 QC#50325
                int ret = searchColPos(pos, cols);

                if (ret >= 0) {
                    pos = ret;
                }
//                }  // Del 2019/05/24 QC#50325
                columnStyles.add(new ZYPExcelColumnStyle(pos, val[1]));
            }

            excelFileFullPath = ZYPExcelUtil.csvFileToExcel(cMsg.xxFileData.getTempFilePath(), columnStyles);
            ZYPEZDItemValueSetter.setValue(cMsg.xxFilePathTxt, excelFileFullPath);

            // 2018/05/22 S21_NA#23523 add start
            //String excelFileFullPath = null;
            //// Got permission from the FW team.
            //if (RSLT_MODE.ORDER_INQUIRY.getRsltModeCd().equals(cMsg.xxRsltModeCd.getValue())) {
            //    excelFileFullPath = ZYPExcelUtil.csvFileToExcel(cMsg.xxFileData.getTempFilePath(), fMsg);
            //} else if (RSLT_MODE.STATUS_SUMMARY.getRsltModeCd().equals(cMsg.xxRsltModeCd.getValue())) {
            //    excelFileFullPath = ZYPExcelUtil.csvFileToExcel(cMsg.xxFileData.getTempFilePath(), f01Msg);
            //    excelFileFullPath = ZYPExcelUtil.csvf
            //}
            //ZYPEZDItemValueSetter.setValue(cMsg.xxFilePathTxt, excelFileFullPath);
            // 2018/05/22 S21_NA#23523 add end
            // 2019/05/07 QC#50031 Mod End
        }

        // 2019/05/07 QC#50031 Add Start
        private int searchColPos(int index, int[] cols){
            int ret = -1;

            if (cols == null){
                return ret;
            }

            int max = cols.length;

            for (int cnt = 0; cnt < max; cnt++){
                if (cols[cnt] == index){
                    ret = cnt;
                    break;
                }
            }

            return ret;
        }
        // 2019/05/07 QC#50031 Add End

        // 2018/02/19 S21_NA#19913 Add Start
        private int[] convertArray(int[] array) {

            // 2018/06/05 S21_NA#24816 Mod Start
//            int[] aftList = new int[array.length - 1];
//
//            int count = 0;
//            for (int i = 0; i < array.length; i++) {
//
//                if (i == 0) {
//                    continue;
//                }
//                aftList[count] = array[i];
//                count++;
//            }
//            return aftList;

            if (array == null) {
                return null;
            }

            // QC#54228 2019/12/09 Mod Start
            //int[] aftList = new int[array.length - 1];
            boolean flg = false;
            int[] aftList = null;
            // Search "Diplay Order#:index_0"
            for (int i = 0; i < array.length; i++) {
                if (array[i] == NWAL1570Constant.COLUMN_INDEX_0) {
                    flg = true;
                    break;
                }
            }
            if (flg) {
                aftList = new int[array.length - 1];
            } else {
                aftList = new int[array.length];
            }
            // QC#54228 2019/12/09 Mod End

            int count = 0;
            for (int i = 0; i < array.length; i++) {
                if (array[i] > NWAL1570Constant.COLUMN_INDEX_0) {
                  aftList[count] = array[i] - 1;
                  count++;
                }
            }

            return aftList;

            // 2018/06/05 S21_NA#24816 Mod End
        }
        //2018/02/19 S21_NA#19913 Add End

        private void mapResultToFMsgForInquiry(NWAL1570CMsg cMsg, Map<String, Object> record, NWAL1570F00FMsg fMsg, int index, Map<String, BigDecimal> groupingMap) {

            String dsOrdPosnNum = getString(record, SQL_COL.DS_ORD_POSN_NUM);
            String dsCpoDtlLineNum = "";
            if (hasValue(getBigDecimal(record, SQL_COL.DS_CPO_LINE_NUM))) {
                dsCpoDtlLineNum = getBigDecimal(record, SQL_COL.DS_CPO_LINE_NUM).toPlainString();
            }
            String dsCpoDtlLineSubNum = "";
            if (hasValue(getBigDecimal(record, SQL_COL.DS_CPO_LINE_SUB_NUM))) {
                dsCpoDtlLineSubNum = getBigDecimal(record, SQL_COL.DS_CPO_LINE_SUB_NUM).toPlainString();
            }

            // ==================================
            // Display Order  Line Number
            // ==================================
            String dplyOrdLineNum = "";
            if (hasValue(dsOrdPosnNum)) {
                dplyOrdLineNum = dsOrdPosnNum;
            }
            if (hasValue(dsCpoDtlLineNum)) {
                dplyOrdLineNum = concatString(dplyOrdLineNum, PERIOD, dsCpoDtlLineNum);
            }
            if (hasValue(dsCpoDtlLineSubNum)) {
                dplyOrdLineNum = concatString(dplyOrdLineNum, PERIOD, dsCpoDtlLineSubNum);
            }

            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // + mapping : Mandatory
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // Display by
            // 2018/06/05 S21_NA#24816 Mod Start
//            getDplyByItemNmForDownload(cMsg, index, record, fMsg, groupingMap);
            setValue(fMsg.xxDplyOrdInqRefNum_A1, getString(record, SQL_COL.SRC_REF_OR_CPO_ORD_NUM));
            // 2018/06/05 S21_NA#24816 Mod End

            // Line No.
            setValue(fMsg.xxDplyOrdLineNum_A1, dplyOrdLineNum);
            // Quantity
            setValue(fMsg.ordQty_A1, getBigDecimal(record, SQL_COL.ORD_QTY));
            // Amount
            setValue(fMsg.xxTotAmt_A1, getBigDecimal(record, SQL_COL.AMT));
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // + mapping : Status
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // Header Status
            setValue(fMsg.xxHdrDplyStsNm_A1, getString(record, SQL_COL.ORD_HDR_DPLY_STS_NM));
            // Line Status
            if (hasValue(getString(record, SQL_COL.ORD_LINE_DPLY_STS_NM))) {
                setValue(fMsg.xxLineDplyStsNm_A1, getString(record, SQL_COL.ORD_LINE_DPLY_STS_NM));
            } else {
                setValue(fMsg.xxLineDplyStsNm_A1, getString(record, SQL_COL.ORD_RTRN_LINE_DPLY_STS_NM));
            }
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // + mapping : Item
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // Product Group
            setValue(fMsg.zerothProdCtrlNm_A1, getString(record, SQL_COL.ZEROTH_PROD_CTRL_NM));
            // PL1
            setValue(fMsg.firstProdCtrlNm_A1, getString(record, SQL_COL.FIRST_PROD_CTRL_NM));
            // PL2
            setValue(fMsg.scdProdCtrlNm_A1, getString(record, SQL_COL.SCD_PROD_CTRL_NM));
            // PL3
            setValue(fMsg.thirdProdCtrlNm_A1, getString(record, SQL_COL.THIRD_PROD_CTRL_NM));
            // PL4
            setValue(fMsg.frthProdCtrlNm_A1, getString(record, SQL_COL.FRTH_PROD_CTRL_NM));
            // Item Code
            setValue(fMsg.mdseCd_A1, getString(record, SQL_COL.MDSE_CD));
            // Item Description
            setValue(fMsg.mdseDescShortTxt_A1, getString(record, SQL_COL.MDSE_DESC_SHORT_TXT));
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // + mapping : WH
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // WH Code
            setValue(fMsg.rtlWhCd_A1, getString(record, SQL_COL.RTL_WH_CD));
            // 2018/12/14 QC#29286 Mod Start
            // WH Name
            setValue(fMsg.rtlWhDescTxt_A1, getString(record, SQL_COL.RTL_WH_DESC_TXT));
            // 2018/12/14 QC#29286 Mod End
            // Sub WH Name
            setValue(fMsg.rtlSwhNm_A1, getString(record, SQL_COL.RTL_SWH_NM));
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // + mapping : Customer
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // ship to
            setValue(fMsg.shipToCustAcctCd_A1, getString(record, SQL_COL.SHIP_TO_CUST_ACCT_CD));
            setValue(fMsg.shipToCustAcctNm_A1, getString(record, SQL_COL.SHIP_TO_CUST_ACCT_NM));
            setValue(fMsg.shipToCustLocCd_A1, getString(record, SQL_COL.SHIP_TO_CUST_LOC_CD));
            // bill to
            setValue(fMsg.billToCustAcctCd_A1, getString(record, SQL_COL.BILL_TO_CUST_ACCT_CD));
            setValue(fMsg.billToCustAcctNm_A1, getString(record, SQL_COL.BILL_TO_CUST_ACCT_NM));
            setValue(fMsg.billToCustLocCd_A1, getString(record, SQL_COL.BILL_TO_CUST_LOC_CD));
            // sold to
            setValue(fMsg.soldToCustAcctCd_A1, getString(record, SQL_COL.SOLD_TO_CUST_ACCT_CD));
            setValue(fMsg.soldToCustAcctNm_A1, getString(record, SQL_COL.SOLD_TO_CUST_ACCT_NM));
            setValue(fMsg.soldToCustLocCd_A1, getString(record, SQL_COL.SOLD_TO_CUST_LOC_CD));
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // + mapping : Date
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // Order Date
            setValue(fMsg.xxDtTxt_OD, getDate(getString(record, SQL_COL.CPO_ORD_TS)));
            // Booked Date
            setValue(fMsg.xxDtTxt_BD, getDate(getString(record, SQL_COL.ORD_BOOK_TS)));
            // RDD
            setValue(fMsg.xxDtTxt_RD, getDate(getString(record, SQL_COL.RDD_DT)));
            // PSD
            setValue(fMsg.xxDtTxt_PD, getDate(getString(record, SQL_COL.PSD_DT)));
            // PDD
            setValue(fMsg.xxDtTxt_DD, getDate(getString(record, SQL_COL.PDD_DT)));
            // Ship Date
            setValue(fMsg.xxDtTxt_SD, getDate(getString(record, SQL_COL.ACTL_SHIP_DT)));
            // Install Date
            setValue(fMsg.xxDtTxt_TD, getDate(getString(record, SQL_COL.ISTL_DT)));
            // Invoice Date
            // 2018/12/06 QC#29024 Mod Start
            // 2018/08/16 QC#22893 Del Start
            setValue(fMsg.xxDtTxt_ID, getDate(getString(record, SQL_COL.INV_DT)));
            // 2018/08/16 QC#22893 Del End
            // 2018/12/06 QC#29024 Mod End
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // + mapping : Order Header
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // Order Category
            setValue(fMsg.dsOrdCatgDescTxt_A1, getString(record, SQL_COL.DS_ORD_CATG_DESC_TXT));
            // Order Reason
            setValue(fMsg.dsOrdTpDescTxt_A1, getString(record, SQL_COL.DS_ORD_TP_DESC_TXT));
            // Order Sub Reason
            setValue(fMsg.dsOrdRsnDescTxt_A1, getString(record, SQL_COL.DS_ORD_RSN_DESC_TXT));
            // Order Source
            setValue(fMsg.cpoSrcTpDescTxt_A1, getString(record, SQL_COL.CPO_SRC_TP_DESC_TXT));
            // Source#
            setValue(fMsg.ordSrcRefNum_A1, getString(record, SQL_COL.ORD_SRC_REF_NUM));
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // + mapping : Other
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // Line Category
            setValue(fMsg.dsOrdLineCatgDescTxt_A1, getString(record, SQL_COL.DS_ORD_LINE_CATG_DESC_TXT));
            // Line Source
            setValue(fMsg.ordLineSrcNm_A1, getString(record, SQL_COL.ORD_LINE_SRC_NM));
            // Price List
            setValue(fMsg.prcCatgDescTxt_A1, getString(record, SQL_COL.PRC_CATG_DESC_TXT));
            // Cust PO#
            setValue(fMsg.custIssPoNum_A1, getString(record, SQL_COL.CUST_ISS_PO_NUM));
            // Lease PO#
            setValue(fMsg.leaseCmpyPoNum_A1, getString(record, SQL_COL.LEASE_CMPY_PO_NUM));
            // Business Unit
            setValue(fMsg.coaExtnDescTxt_A1, getString(record, SQL_COL.COA_EXTN_DESC_TXT));
            // Branch
            setValue(fMsg.coaBrDescTxt_A1, getString(record, SQL_COL.COA_BR_DESC_TXT));
            // Sales Rep Code
            // 2018/02/22 QC#21611 Mod Start
            //setValue(fMsg.slsRepTocCd_A1, getString(record, SQL_COL.SLS_REP_TOC_CD));
            setValue(fMsg.slsRepPsnNum_A1, getString(record, SQL_COL.SLS_REP_PSN_NUM));
            // 2018/02/22 QC#21611 Mod End
            // Sales Rep Name
            setValue(fMsg.tocNm_A1, getString(record, SQL_COL.SLS_REP_TOC_NM));
            // CSMP#
            setValue(fMsg.csmpContrNum_A1, getString(record, SQL_COL.CSMP_CONTR_NUM));
            // Association Program Name
            setValue(fMsg.prcContrNm_A1, getString(record, SQL_COL.PRC_CONTR_NM));

            // Model
            setValue(fMsg.t_MdlNm_A1, getString(record, SQL_COL.T_MDL_NM));
            // COA Prod
            setValue(fMsg.coaProdDescTxt_A1, getString(record, SQL_COL.COA_PROD_DESC_TXT));
            // COA MDSE
            setValue(fMsg.coaMdseTpDescTxt_A1, getString(record, SQL_COL.COA_MDSE_TP_DESC_TXT));
            // Config ID
            setValue(fMsg.svcConfigMstrPk_A1, getBigDecimal(record, SQL_COL.SVC_CONFIG_MSTR_PK));
            // Contract#
            setValue(fMsg.dsContrNum_A1, getString(record, SQL_COL.DS_CONTR_NUM));
            // Serial#
            setValue(fMsg.serNum_A1, getString(record, SQL_COL.SER_NUM));
            // PR#
            setValue(fMsg.prchReqNum_A1, getString(record, SQL_COL.PRCH_REQ_NUM));
            // PO#
            setValue(fMsg.poNum_A1, getString(record, SQL_COL.PO_ORD_NUM));
            // SO#
            setValue(fMsg.soNum_A1, getString(record, SQL_COL.SO_NUM));
            // Invoice#
            setValue(fMsg.invNum_A1, getString(record, SQL_COL.INV_NUM));
            // QC#13415 MOD Start
            // Vendor Code
//            setValue(fMsg.vndNm_A1, getString(record, SQL_COL.VND_NM));
            setValue(fMsg.dplyVndNm_A1, getString(record, SQL_COL.DPLY_VND_NM));
            // QC#13415 MOD End
            // 2018/08/01 QC#26304 Add Start
            // Acquisition#
            setValue(fMsg.aquNum_A1, getString(record, SQL_COL.AQU_NUM));
            // 2018/08/01 QC#26304 Add End
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // + mapping : Summary
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // summary amount
            fMsg.addExclusionItem( "xxPendImptSmryAmt_A1" );
            fMsg.addExclusionItem( "xxEntSmryAmt_A1" );
            fMsg.addExclusionItem( "xxDiHldSmryAmt_A1" );
            fMsg.addExclusionItem( "xxVldSmryAmt_A1" );
            fMsg.addExclusionItem( "xxPrftSmryAmt_A1" );
            fMsg.addExclusionItem( "xxCrHldSmryAmt_A1" );
            fMsg.addExclusionItem( "xxSplyAbuseSmryAmt_A1" );
            fMsg.addExclusionItem( "xxPendReSubmtSmryAmt_A1" );
            fMsg.addExclusionItem( "xxBookSmryAmt_A1" );
            fMsg.addExclusionItem( "xxAwaitDropShipSmryAmt_A1" ); // 2017/11/21 QC#22550 Add
            //fMsg.addExclusionItem( "xxSoCancSmryAmt_A1" ); // 2017/11/21 QC#22550 Del
            fMsg.addExclusionItem( "xxPendReAllocSmryAmt_A1" ); // 2017/11/21 QC#22550 Add
            fMsg.addExclusionItem( "xxPoCancSmryAmt_A1" );
            fMsg.addExclusionItem( "xxPendFuflSmryAmt_A1" );
            fMsg.addExclusionItem( "xxPendAllocSmryAmt_A1" );
            fMsg.addExclusionItem( "xxBoSmryAmt_A1" );
            fMsg.addExclusionItem( "xxPendPickSmryAmt_A1" );
            fMsg.addExclusionItem( "xxDelyToShopSmryAmt_A1" );
            fMsg.addExclusionItem( "xxInShopConfigSmryAmt_A1" );
            fMsg.addExclusionItem( "xxPendShipSmryAmt_A1" );
            fMsg.addExclusionItem( "xxShipSmryAmt_A1" );
            fMsg.addExclusionItem( "xxPendDelyConfSmryAmt_A1" );
            fMsg.addExclusionItem( "xxPendIstlSmryAmt_A1" );
            fMsg.addExclusionItem( "xxOnLoanSmryAmt_A1" );
            fMsg.addExclusionItem( "xxWaitRcptSmryAmt_A1" );
            fMsg.addExclusionItem( "xxPendRtrnSmryAmt_A1" );
            fMsg.addExclusionItem( "xxPendInspSmryAmt_A1" );
            fMsg.addExclusionItem( "xxRwsCancSmryAmt_A1" );
            fMsg.addExclusionItem( "xxPrtlRcvSmryAmt_A1" );
            fMsg.addExclusionItem( "xxPendInvSmryAmt_A1" );
            fMsg.addExclusionItem( "xxBllgHldSmryAmt_A1" );
            fMsg.addExclusionItem( "xxPendDlrIstlSmryAmt_A1" ); // 2017/11/21 QC#22550 Add
            fMsg.addExclusionItem( "xxShipCloSmryAmt_A1" );
            fMsg.addExclusionItem( "xxInvdSmryAmt_A1" );
            fMsg.addExclusionItem( "xxCloSmryAmt_A1" );
            fMsg.addExclusionItem( "xxCloLoanRtrnSmryAmt_A1" );
            fMsg.addExclusionItem( "xxCloLoanSoldSmryAmt_A1" );
            fMsg.addExclusionItem( "xxCancSmryAmt_A1" );

            // summary count
            fMsg.addExclusionItem( "xxPendImptSmryCnt_A1" );
            fMsg.addExclusionItem( "xxEntSmryCnt_A1" );
            fMsg.addExclusionItem( "xxDiHldSmryCnt_A1" );
            fMsg.addExclusionItem( "xxVldSmryCnt_A1" );
            fMsg.addExclusionItem( "xxPrftSmryCnt_A1" );
            fMsg.addExclusionItem( "xxCrHldSmryCnt_A1" );
            fMsg.addExclusionItem( "xxSplyAbuseSmryCnt_A1" );
            fMsg.addExclusionItem( "xxPendReSubmtSmryCnt_A1" );
            fMsg.addExclusionItem( "xxBookSmryCnt_A1" );
            fMsg.addExclusionItem( "xxAwaitDropShipSmryCnt_A1" ); // 2017/11/21 QC#22550 Add
            //fMsg.addExclusionItem( "xxSoCancSmryCnt_A1" ); // 2017/11/21 QC#22550 Del
            fMsg.addExclusionItem( "xxPendReAllocSmryCnt_A1" ); // 2017/11/21 QC#22550 Add
            fMsg.addExclusionItem( "xxPoCancSmryCnt_A1" );
            fMsg.addExclusionItem( "xxPendFuflSmryCnt_A1" );
            fMsg.addExclusionItem( "xxPendAllocSmryCnt_A1" );
            fMsg.addExclusionItem( "xxBoSmryCnt_A1" );
            fMsg.addExclusionItem( "xxPendPickSmryCnt_A1" );
            fMsg.addExclusionItem( "xxDelyToShopSmryCnt_A1" );
            fMsg.addExclusionItem( "xxInShopConfigSmryCnt_A1" );
            fMsg.addExclusionItem( "xxPendShipSmryCnt_A1" );
            fMsg.addExclusionItem( "xxShipSmryCnt_A1" );
            fMsg.addExclusionItem( "xxPendDelyConfSmryCnt_A1" );
            fMsg.addExclusionItem( "xxPendIstlSmryCnt_A1" );
            fMsg.addExclusionItem( "xxOnLoanSmryCnt_A1" );
            fMsg.addExclusionItem( "xxWaitRcptSmryCnt_A1" );
            fMsg.addExclusionItem( "xxPendRtrnSmryCnt_A1" );
            fMsg.addExclusionItem( "xxPendInspSmryCnt_A1" );
            fMsg.addExclusionItem( "xxRwsCancSmryCnt_A1" );
            fMsg.addExclusionItem( "xxPrtlRcvSmryCnt_A1" );
            fMsg.addExclusionItem( "xxPendInvSmryCnt_A1" );
            fMsg.addExclusionItem( "xxBllgHldSmryCnt_A1" );
            fMsg.addExclusionItem( "xxPendDlrIstlSmryCnt_A1" ); // 2017/11/21 QC#22550 Add
            fMsg.addExclusionItem( "xxShipCloSmryCnt_A1" );
            fMsg.addExclusionItem( "xxInvdSmryCnt_A1" );
            fMsg.addExclusionItem( "xxCloSmryCnt_A1" );
            fMsg.addExclusionItem( "xxCloLoanRtrnSmryCnt_A1" );
            fMsg.addExclusionItem( "xxCloLoanSoldSmryCnt_A1" );
            fMsg.addExclusionItem( "xxCancSmryCnt_A1" );
        }

        // 2017/10/2 QC#19913 Mod Start
        private void mapResultToFMsgForSummary(NWAL1570CMsg cMsg, Map<String, Object> record, NWAL1570F01FMsg f01Msg, int index, Map<String, BigDecimal> groupingMap) {

            String dsOrdPosnNum = getString(record, SQL_COL.DS_ORD_POSN_NUM);
            String dsCpoDtlLineNum = "";
            if (hasValue(getBigDecimal(record, SQL_COL.DS_CPO_LINE_NUM))) {
                dsCpoDtlLineNum = getBigDecimal(record, SQL_COL.DS_CPO_LINE_NUM).toPlainString();
            }
            String dsCpoDtlLineSubNum = "";
            if (hasValue(getBigDecimal(record, SQL_COL.DS_CPO_LINE_SUB_NUM))) {
                dsCpoDtlLineSubNum = getBigDecimal(record, SQL_COL.DS_CPO_LINE_SUB_NUM).toPlainString();
            }

            // ==================================
            // Display Order  Line Number
            // ==================================
            String dplyOrdLineNum = "";
            if (hasValue(dsOrdPosnNum)) {
                dplyOrdLineNum = dsOrdPosnNum;
            }
            if (hasValue(dsCpoDtlLineNum)) {
                dplyOrdLineNum = concatString(dplyOrdLineNum, PERIOD, dsCpoDtlLineNum);
            }
            if (hasValue(dsCpoDtlLineSubNum)) {
                dplyOrdLineNum = concatString(dplyOrdLineNum, PERIOD, dsCpoDtlLineSubNum);
            }

            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // + mapping : Mandatory
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // Display by
            getDplyByItemNmForSummaryDownload(cMsg, index, record, f01Msg, groupingMap);

            // Order No.
            f01Msg.addExclusionItem( "xxDplyOrdInqRefNum_A1" );
            // Line No.
            f01Msg.addExclusionItem( "xxDplyOrdLineNum_A1" );
            // Quantity
            f01Msg.addExclusionItem( "ordQty_A1" );
            // Amount
            f01Msg.addExclusionItem( "cpoTotAmt_A1" );
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // + mapping : Status
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // Header Status
            f01Msg.addExclusionItem( "xxHdrDplyStsNm_A1" );
            // Line Status
            f01Msg.addExclusionItem( "xxLineDplyStsNm_A1" );
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // + mapping : Item
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // Product Group
            f01Msg.addExclusionItem( "zerothProdCtrlNm_A1" );
            // PL1
            f01Msg.addExclusionItem( "firstProdCtrlNm_A1" );
            // PL2
            f01Msg.addExclusionItem( "scdProdCtrlNm_A1" );
            // PL3
            f01Msg.addExclusionItem( "thirdProdCtrlNm_A1" );
            // PL4
            f01Msg.addExclusionItem( "frthProdCtrlNm_A1" );
            // Item Code
            f01Msg.addExclusionItem( "mdseCd_A1" );
            // Item Description
            f01Msg.addExclusionItem( "mdseDescShortTxt_A1" );
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // + mapping : WH
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // WH Code
            f01Msg.addExclusionItem( "rtlWhCd_A1" );
            // WH Name
            f01Msg.addExclusionItem( "rtlWhNm_A1" );
            // Sub WH Name
            f01Msg.addExclusionItem( "rtlSwhNm_A1" );
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // + mapping : Customer
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // ship to
            f01Msg.addExclusionItem( "shipToCustAcctCd_A1" );
            f01Msg.addExclusionItem( "shipToCustAcctNm_A1" );
            f01Msg.addExclusionItem( "shipToCustLocCd_A1" );
            // bill to
            f01Msg.addExclusionItem( "billToCustAcctCd_A1" );
            f01Msg.addExclusionItem( "billToCustAcctNm_A1" );
            f01Msg.addExclusionItem( "billToCustLocCd_A1" );
            // sold to
            f01Msg.addExclusionItem( "soldToCustAcctCd_A1" );
            f01Msg.addExclusionItem( "soldToCustAcctNm_A1" );
            f01Msg.addExclusionItem( "soldToCustLocCd_A1" );
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // + mapping : Date
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // Order Date
            f01Msg.addExclusionItem( "xxDtTxt_OD" );
            // Booked Date
            f01Msg.addExclusionItem( "xxDtTxt_BD" );
            // RDD
            f01Msg.addExclusionItem( "xxDtTxt_RD" );
            // PSD
            f01Msg.addExclusionItem( "xxDtTxt_PD" );
            // PDD
            f01Msg.addExclusionItem( "xxDtTxt_DD" );
            // Ship Date
            f01Msg.addExclusionItem( "xxDtTxt_SD" );
            // Install Date
            f01Msg.addExclusionItem( "xxDtTxt_TD" );
            // 2018/12/06 QC#29024 Mod Start
            // 2018/10/10 QC#22893 Del Start
            // Invoice Date
            f01Msg.addExclusionItem( "xxDtTxt_ID" );
            // 2018/10/10 QC#22893 Del End
            // 2018/12/06 QC#29024 Mod End
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // + mapping : Order Header
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // Order Category
            f01Msg.addExclusionItem( "dsOrdCatgDescTxt_A1" );
            // Order Reason
            f01Msg.addExclusionItem( "dsOrdTpDescTxt_A1" );
            // Order Sub Reason
            f01Msg.addExclusionItem( "dsOrdRsnDescTxt_A1" );
            // Order Source
            f01Msg.addExclusionItem( "cpoSrcTpDescTxt_A1" );
            // Source#
            f01Msg.addExclusionItem( "ordSrcRefNum_A1" );
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // + mapping : Other
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // Line Category
            f01Msg.addExclusionItem( "dsOrdLineCatgDescTxt_A1" );
            // Line Source
            f01Msg.addExclusionItem( "ordLineSrcNm_A1" );
            // Price List
            f01Msg.addExclusionItem( "prcCatgDescTxt_A1" );
            // Cust PO#
            f01Msg.addExclusionItem( "custIssPoNum_A1" );
            // Lease PO#
            f01Msg.addExclusionItem( "leaseCmpyPoNum_A1" );
            // Business Unit
            f01Msg.addExclusionItem( "coaExtnDescTxt_A1" );
            // Branch
            f01Msg.addExclusionItem( "coaBrDescTxt_A1" );
            // Sales Rep Code
            f01Msg.addExclusionItem( "slsRepTocCd_A1" );
            // Sales Rep Name
            f01Msg.addExclusionItem( "tocNm_A1" );
            // CSMP#
            f01Msg.addExclusionItem( "csmpContrNum_A1" );
            // Association Program Name
            f01Msg.addExclusionItem( "prcContrNm_A1" );

            // Model
            f01Msg.addExclusionItem( "t_MdlNm_A1" );
            // COA Prod
            f01Msg.addExclusionItem( "coaProdDescTxt_A1" );
            // COA MDSE
            f01Msg.addExclusionItem( "coaMdseTpDescTxt_A1" );
            // Config ID
            f01Msg.addExclusionItem( "svcConfigMstrPk_A1" );
            // Contract#
            f01Msg.addExclusionItem( "dsContrNum_A1" );
            // Serial#
            f01Msg.addExclusionItem( "serNum_A1" );
            // PR#
            f01Msg.addExclusionItem( "prchReqNum_A1" );
            // PO#
            f01Msg.addExclusionItem( "poNum_A1" );
            // SO#
            f01Msg.addExclusionItem( "soNum_A1" );
            // Invoice#
            f01Msg.addExclusionItem( "invNum_A1" );
            // QC#13415 MOD Start
            // Vendor Code
//            fMsg.addExclusionItem( "vndNm_A1" );
            f01Msg.addExclusionItem( "dplyVndNm_A1" );
            // 2018/08/01 QC#26304 Add Start
            // Acquisition#
            f01Msg.addExclusionItem( "aquNum_A1" );
            // 2018/08/01 QC#26304 Add End

            f01Msg.addExclusionItem( "xxTotAmt_A1" ); // 2017/06/14 S21_NA#19117 Add

            // 2019/05/07 QC#50031 Add Start
            //f01Msg.addExclusionItem( "xxBookSmryAmt_A1" );
            //f01Msg.addExclusionItem( "xxBookSmryCnt_A1" );
            // 2019/05/07 QC#50031 Add End

            // QC#13415 MOD End
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // + mapping : Summary
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // summary amount
            setValue(f01Msg.xxPendImptSmryAmt_A1, getBigDecimal(record, SQL_COL.PEND_IMPT_SMRY_AMT));
            setValue(f01Msg.xxEntSmryAmt_A1, getBigDecimal(record, SQL_COL.ENT_SMRY_AMT));
            setValue(f01Msg.xxDiHldSmryAmt_A1, getBigDecimal(record, SQL_COL.DI_HLD_SMRY_AMT));
            setValue(f01Msg.xxVldSmryAmt_A1, getBigDecimal(record, SQL_COL.VLD_SMRY_AMT));
            setValue(f01Msg.xxPrftSmryAmt_A1, getBigDecimal(record, SQL_COL.PRFT_SMRY_AMT));
            setValue(f01Msg.xxCrHldSmryAmt_A1, getBigDecimal(record, SQL_COL.CR_HLD_SMRY_AMT));
            setValue(f01Msg.xxSplyAbuseSmryAmt_A1, getBigDecimal(record, SQL_COL.SPLY_ABUSE_SMRY_AMT));
            setValue(f01Msg.xxPendReSubmtSmryAmt_A1, getBigDecimal(record, SQL_COL.PEND_RE_SUBMT_SMRY_AMT));
            // 2019/05/07 QC#50031 Del Start
            // setValue(f01Msg.xxBookSmryAmt_A1, getBigDecimal(record, SQL_COL.BOOK_SMRY_AMT));
            // 2019/05/07 QC#50031 Del End
            setValue(f01Msg.xxAwaitDropShipSmryAmt_A1, getBigDecimal(record, SQL_COL.AWAIT_DROP_SHIP_SMRY_AMT)); // 2017/11/21 QC#22550 Add
            //setValue(f01Msg.xxSoCancSmryAmt_A1, getBigDecimal(record, SQL_COL.SO_CANC_SMRY_AMT)); // 2017/11/21 QC#22550 Del
            setValue(f01Msg.xxPendReAllocSmryAmt_A1, getBigDecimal(record, SQL_COL.PEND_RE_ALLOC_SMRY_AMT)); // 2017/11/21 QC#22550 Add
            setValue(f01Msg.xxPoCancSmryAmt_A1, getBigDecimal(record, SQL_COL.PO_CANC_SMRY_AMT));
            setValue(f01Msg.xxPendFuflSmryAmt_A1, getBigDecimal(record, SQL_COL.PEND_FUFL_SMRY_AMT));
            setValue(f01Msg.xxPendAllocSmryAmt_A1, getBigDecimal(record, SQL_COL.PEND_ALLOC_SMRY_AMT));
            setValue(f01Msg.xxBoSmryAmt_A1, getBigDecimal(record, SQL_COL.BO_SMRY_AMT));
            setValue(f01Msg.xxPendPickSmryAmt_A1, getBigDecimal(record, SQL_COL.PEND_PICK_SMRY_AMT));
            setValue(f01Msg.xxDelyToShopSmryAmt_A1, getBigDecimal(record, SQL_COL.DELY_TO_SHOP_SMRY_AMT));
            setValue(f01Msg.xxInShopConfigSmryAmt_A1, getBigDecimal(record, SQL_COL.IN_SHOP_CONFIG_SMRY_AMT));
            setValue(f01Msg.xxPendShipSmryAmt_A1, getBigDecimal(record, SQL_COL.PEND_SHIP_SMRY_AMT));
            setValue(f01Msg.xxShipSmryAmt_A1, getBigDecimal(record, SQL_COL.SHIP_SMRY_AMT));
            setValue(f01Msg.xxPendDelyConfSmryAmt_A1, getBigDecimal(record, SQL_COL.PEND_DELY_CONF_SMRY_AMT));
            setValue(f01Msg.xxPendIstlSmryAmt_A1, getBigDecimal(record, SQL_COL.PEND_ISTL_SMRY_AMT));
            setValue(f01Msg.xxOnLoanSmryAmt_A1, getBigDecimal(record, SQL_COL.ON_LOAN_SMRY_AMT));
            setValue(f01Msg.xxWaitRcptSmryAmt_A1, getBigDecimal(record, SQL_COL.WAIT_RCPT_SMRY_AMT));
            setValue(f01Msg.xxPendRtrnSmryAmt_A1, getBigDecimal(record, SQL_COL.PEND_RTRN_SMRY_AMT));
            setValue(f01Msg.xxPendInspSmryAmt_A1, getBigDecimal(record, SQL_COL.PEND_INSP_SMRY_AMT));
            setValue(f01Msg.xxRwsCancSmryAmt_A1, getBigDecimal(record, SQL_COL.RWS_CANC_SMRY_AMT));
            setValue(f01Msg.xxPrtlRcvSmryAmt_A1, getBigDecimal(record, SQL_COL.PRTL_RCV_SMRY_AMT));
            setValue(f01Msg.xxPendInvSmryAmt_A1, getBigDecimal(record, SQL_COL.PEND_INV_SMRY_AMT));
            setValue(f01Msg.xxBllgHldSmryAmt_A1, getBigDecimal(record, SQL_COL.BLLG_HLD_SMRY_AMT));
            setValue(f01Msg.xxPendDlrIstlSmryAmt_A1, getBigDecimal(record, SQL_COL.PEND_DLR_ISTL_SMRY_AMT)); // 2017/11/21 QC#22550 Add
//            setValue(fMsg.xxShipCloSmryAmt_A1, getBigDecimal(record, SQL_COL.SHIP_CLO_SMRY_AMT));
            // QC#11581 DEL Start
//            setValue(fMsg.xxInvdSmryAmt_A1, getBigDecimal(record, SQL_COL.INV_SMRY_AMT));
            // QC#11581 DEL End
            setValue(f01Msg.xxCloSmryAmt_A1, getBigDecimal(record, SQL_COL.CLO_SMRY_AMT));
            setValue(f01Msg.xxCloLoanRtrnSmryAmt_A1, getBigDecimal(record, SQL_COL.CLO_LOAN_RTRN_SMRY_AMT));
            setValue(f01Msg.xxCloLoanSoldSmryAmt_A1, getBigDecimal(record, SQL_COL.CLO_LOAN_SOLD_SMRY_AMT));
            setValue(f01Msg.xxCancSmryAmt_A1, getBigDecimal(record, SQL_COL.CANC_SMRY_AMT));

            // summary count
            setValue(f01Msg.xxPendImptSmryCnt_A1, getBigDecimal(record, SQL_COL.PEND_IMPT_SMRY_CNT));
            setValue(f01Msg.xxEntSmryCnt_A1, getBigDecimal(record, SQL_COL.ENT_SMRY_CNT));
            setValue(f01Msg.xxDiHldSmryCnt_A1, getBigDecimal(record, SQL_COL.DI_HLD_SMRY_CNT));
            setValue(f01Msg.xxVldSmryCnt_A1, getBigDecimal(record, SQL_COL.VLD_SMRY_CNT));
            setValue(f01Msg.xxPrftSmryCnt_A1, getBigDecimal(record, SQL_COL.PRFT_SMRY_CNT));
            setValue(f01Msg.xxCrHldSmryCnt_A1, getBigDecimal(record, SQL_COL.CR_HLD_SMRY_CNT));
            setValue(f01Msg.xxSplyAbuseSmryCnt_A1, getBigDecimal(record, SQL_COL.SPLY_ABUSE_SMRY_CNT));
            setValue(f01Msg.xxPendReSubmtSmryCnt_A1, getBigDecimal(record, SQL_COL.PEND_RE_SUBMT_SMRY_CNT));
            // 2019/05/07 QC#50031 Del Start
            //setValue(f01Msg.xxBookSmryCnt_A1, getBigDecimal(record, SQL_COL.BOOK_SMRY_CNT));
            // 2019/05/07 QC#50031 Del End
            setValue(f01Msg.xxAwaitDropShipSmryCnt_A1, getBigDecimal(record, SQL_COL.AWAIT_DROP_SHIP_SMRY_CNT)); // 2017/11/21 QC#22550 Add
            //setValue(f01Msg.xxSoCancSmryCnt_A1, getBigDecimal(record, SQL_COL.SO_CANC_SMRY_CNT)); // 2017/11/21 QC#22550 Del
            setValue(f01Msg.xxPendReAllocSmryCnt_A1, getBigDecimal(record, SQL_COL.PEND_RE_ALLOC_SMRY_CNT)); // 2017/11/21 QC#22550 Add
            setValue(f01Msg.xxPoCancSmryCnt_A1, getBigDecimal(record, SQL_COL.PO_CANC_SMRY_CNT));
            setValue(f01Msg.xxPendFuflSmryCnt_A1, getBigDecimal(record, SQL_COL.PEND_FUFL_SMRY_CNT));
            setValue(f01Msg.xxPendAllocSmryCnt_A1, getBigDecimal(record, SQL_COL.PEND_ALLOC_SMRY_CNT));
            setValue(f01Msg.xxBoSmryCnt_A1, getBigDecimal(record, SQL_COL.BO_SMRY_CNT));
            setValue(f01Msg.xxPendPickSmryCnt_A1, getBigDecimal(record, SQL_COL.PEND_PICK_SMRY_CNT));
            setValue(f01Msg.xxDelyToShopSmryCnt_A1, getBigDecimal(record, SQL_COL.DELY_TO_SHOP_SMRY_CNT));
            setValue(f01Msg.xxInShopConfigSmryCnt_A1, getBigDecimal(record, SQL_COL.IN_SHOP_CONFIG_SMRY_CNT));
            setValue(f01Msg.xxPendShipSmryCnt_A1, getBigDecimal(record, SQL_COL.PEND_SHIP_SMRY_CNT));
            setValue(f01Msg.xxShipSmryCnt_A1, getBigDecimal(record, SQL_COL.SHIP_SMRY_CNT));
            setValue(f01Msg.xxPendDelyConfSmryCnt_A1, getBigDecimal(record, SQL_COL.PEND_DELY_CONF_SMRY_CNT));
            setValue(f01Msg.xxPendIstlSmryCnt_A1, getBigDecimal(record, SQL_COL.PEND_ISTL_SMRY_CNT));
            setValue(f01Msg.xxOnLoanSmryCnt_A1, getBigDecimal(record, SQL_COL.ON_LOAN_SMRY_CNT));
            setValue(f01Msg.xxWaitRcptSmryCnt_A1, getBigDecimal(record, SQL_COL.WAIT_RCPT_SMRY_CNT));
            setValue(f01Msg.xxPendRtrnSmryCnt_A1, getBigDecimal(record, SQL_COL.PEND_RTRN_SMRY_CNT));
            setValue(f01Msg.xxPendInspSmryCnt_A1, getBigDecimal(record, SQL_COL.PEND_INSP_SMRY_CNT));
            setValue(f01Msg.xxRwsCancSmryCnt_A1, getBigDecimal(record, SQL_COL.RWS_CANC_SMRY_CNT));
            setValue(f01Msg.xxPrtlRcvSmryCnt_A1, getBigDecimal(record, SQL_COL.PRTL_RCV_SMRY_CNT));
            setValue(f01Msg.xxPendInvSmryCnt_A1, getBigDecimal(record, SQL_COL.PEND_INV_SMRY_CNT));
            setValue(f01Msg.xxBllgHldSmryCnt_A1, getBigDecimal(record, SQL_COL.BLLG_HLD_SMRY_CNT));
            setValue(f01Msg.xxPendDlrIstlSmryCnt_A1, getBigDecimal(record, SQL_COL.PEND_DLR_ISTL_SMRY_CNT)); // 2017/11/21 QC#22550 Add
//            setValue(fMsg.xxShipCloSmryCnt_A1, getBigDecimal(record, SQL_COL.SHIP_CLO_SMRY_CNT));
            // QC#11581 DEL Start
//            setValue(fMsg.xxInvdSmryCnt_A1, getBigDecimal(record, SQL_COL.INV_SMRY_CNT));
            // QC#11581 DEL End
            setValue(f01Msg.xxCloSmryCnt_A1, getBigDecimal(record, SQL_COL.CLO_SMRY_CNT));
            setValue(f01Msg.xxCloLoanRtrnSmryCnt_A1, getBigDecimal(record, SQL_COL.CLO_LOAN_RTRN_SMRY_CNT));
            setValue(f01Msg.xxCloLoanSoldSmryCnt_A1, getBigDecimal(record, SQL_COL.CLO_LOAN_SOLD_SMRY_CNT));
            setValue(f01Msg.xxCancSmryCnt_A1, getBigDecimal(record, SQL_COL.CANC_SMRY_CNT));
        }
        // 2017/10/2 QC#19913 Mod End

        private List<String> createCsvHeaderForInquiry(NWAL1570CMsg cMsg, NWAL1570F00FMsg fMsg) {

            List<String> csvHeader = new ArrayList<String>();

            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // + mapping : Mandatory
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // 2018/06/05 S21_NA#24816 Del Start
            // Display 
//            csvHeader.add("Display By");
            // 2018/06/05 S21_NA#24816 Del End
            // Order#
            csvHeader.add("Order#");
            // Line#
            csvHeader.add("Line#");
            // Qty
            csvHeader.add("Qty");
            // Amount
            csvHeader.add("Amount");
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // + mapping : Status
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // Header Status
            csvHeader.add("Header Status");
            // Line Status
            csvHeader.add("Line Status");
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // + mapping : Item
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // Item Code
            csvHeader.add("Item Code");
            // Item Description
            csvHeader.add("Item Description");
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // + mapping : WH
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // WH Code
            csvHeader.add("WH Code");
            // WH Name
            csvHeader.add("WH Name");
            // Sub WH Name
            csvHeader.add("Sub WH Name");
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // + mapping : Customer
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // 2020/01/14 QC#55353 Mod Start
            // ship to
            csvHeader.add("Ship To Account Code");
            csvHeader.add("Ship To Account Name");
            //csvHeader.add("Ship To Location Code");
            csvHeader.add("Ship To Code");
            // bill to
            csvHeader.add("Bill To Account Code");
            csvHeader.add("Bill To Account Name");
            //csvHeader.add("Bill To Location Code");
            csvHeader.add("Bill To Code");
            // sold to
            csvHeader.add("Sold To Account Code");
            csvHeader.add("Sold To Account Name");
            //csvHeader.add("Sold To Location Code");
            csvHeader.add("Sold To Code");
            // 2020/01/14 QC#55353 Mod End
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // + mapping : Date
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // Ordered Date
            csvHeader.add("Ordered Date");
            // Booked Date
            csvHeader.add("Booked Date");
            // RDD
            csvHeader.add("RDD");
            // PSD
            csvHeader.add("PSD");
            // PDD
            csvHeader.add("PDD");
            // Ship Date
            csvHeader.add("Ship Date");
            // Install Date
            csvHeader.add("Install Date");
            // 2018/12/06 QC#29024 Mod Start
            // 2018/10/10 QC#22893 Del Start
            // Invoice Date
            csvHeader.add("Invoice Date");
            // 2018/10/10 QC#22893 Del End
            // 2018/12/06 QC#29024 Mod End
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // + mapping : Order Header
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // Order Category
            csvHeader.add("Order Category");
            // Order Reason
            csvHeader.add("Order Reason");
            // Order Sub Reason
            csvHeader.add("Order Sub Reason");
            // Order Source
            csvHeader.add("Order Source");
            // Source#
            csvHeader.add("Source #");
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // + mapping : Other
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // Line Category
            csvHeader.add("Line Cateogry");
            // Line Source
            csvHeader.add("Line Source");
            // Price List
            csvHeader.add("Price List");
            // Cust PO#
            csvHeader.add("Cust PO#");
            // Lease PO#
            csvHeader.add("Lease PO#");
            // Business Unit
            csvHeader.add("Business Unit");
            // Branch
            csvHeader.add("Branch");
            // Sales Rep Code
            csvHeader.add("Sales Rep");
            // Sales Rep Name
            csvHeader.add("Sales Rep Name");
            // CSMP#
            csvHeader.add("CSMP#");
            // Association Program Name
            csvHeader.add("Association Program");
            // QC#11956 MOD Start
            // Product Group
            csvHeader.add(cMsg.mdseStruElmntTpNm_L1.getValue());
            // PL1
            csvHeader.add(cMsg.mdseStruElmntTpNm_L2.getValue());
            // PL2
            csvHeader.add(cMsg.mdseStruElmntTpNm_L3.getValue());
            // PL3
            csvHeader.add(cMsg.mdseStruElmntTpNm_L4.getValue());
            // PL4
            csvHeader.add(cMsg.mdseStruElmntTpNm_L5.getValue());
            // QC#11956 MOD End
            // Model
            csvHeader.add("Model");
            // COA Prod
            csvHeader.add("COA Prod");
            // COA MDSE
            csvHeader.add("COA MDSE");
            // Config ID
            csvHeader.add("Config ID");
            // Contract#
            csvHeader.add("Contract#");
            // Serial#
            csvHeader.add("Serial#");
            // PR#
            csvHeader.add("PR#");
            // PO#
            csvHeader.add("PO#");
            // SO#
            csvHeader.add("SO#");
            // Invoice#
            csvHeader.add("Invoice#");
            // Vendor Code
            csvHeader.add("Vendor");
            // 2018/08/01 QC#26304 Add Start
            csvHeader.add("Acquisition#");
            // 2018/08/01 QC#26304 Add End

            return csvHeader;
        }

        private List<String> createCsvHeaderForSummary(NWAL1570CMsg cMsg, NWAL1570F01FMsg f01Msg) {

            List<String> csvHeader = new ArrayList<String>();

            // Display 
            csvHeader.add("Display By");
            // Pending Import Amount
            csvHeader.add("Pending Import Amount");
            // Entered Amount
            csvHeader.add("Entered Amount");
            // DI Hold Amount
            csvHeader.add("DI Hold Amount");
            // Validation Amount
            csvHeader.add("Validation Amount");
            // Profitability Amount
            csvHeader.add("Profitability Amount");
            // Credit Hold Amount
            csvHeader.add("Credit Review Amount");
            // 2018/07/05 QC#25786 mod start
//            // Supply Abuse Amount
//            csvHeader.add("Supply Abuse Amount");
            // Supply Enforcement Amount
            csvHeader.add("Supply Enforcement Amount");
            // 2018/07/05 QC#25786 mod end
            // Pending Re Submission Amount
            csvHeader.add("Pending Re Submission Amount");
            // 2019/05/07 QC#50031 Del Start
            //// Booked Amount
            //csvHeader.add("Booked Amount");
            // 2019/05/07 QC#50031 Del End
            // 2017/11/21 QC#22550 Add Start
            // Awaiting Drop Ship Amount
            csvHeader.add("Awaiting Drop Ship Amount");
            // 2017/11/21 QC#22550 Add End
            // 2017/11/21 QC#22550 Mod Start
            //// SO Canceled Amount
            //csvHeader.add("SO Canceled Amount");
            // Pending Re Allocation Amount
            csvHeader.add("Pending Re Allocation Amount");
            // 2017/11/21 QC#22550 Mod End
            // PO Canceled Amount
            csvHeader.add("PO Canceled Amount");
            // Pending Fulfillment Amount
            csvHeader.add("Pending Fulfillment Amount");
            // Pending Allocation Amount
            csvHeader.add("Pending Allocation Amount");
            // Back Ordered Amount
            csvHeader.add("Back Ordered Amount");
            // Pending Pick Amount
            csvHeader.add("Pending Pick Amount");
            // Delivered to Shop Amount
            csvHeader.add("Delivered to Shop Amount");
            // In Shop/ Config Amount
            csvHeader.add("In Shop/ Config Amount");
            // Pending Shipment Amount
            csvHeader.add("Pending Shipment Amount");
            // Shipped Amount
            csvHeader.add("Shipped Amount");
            // Pending Delivery Confirmation Amount
            csvHeader.add("Pending Delivery Confirmation Amount");
            // Pending Installation Amount
            csvHeader.add("Pending Installation Amount");
            // On Loan Amount
            csvHeader.add("On Loan Amount");
            // Waiting Receipt Amount
            csvHeader.add("Waiting Receipt Amount");
            // Pending Return Amount
            csvHeader.add("Pending Return Amount");
            // Pending Inspection Amount
            csvHeader.add("Pending Inspection Amount");
            // RWS Canceled Amount
            csvHeader.add("RWS Canceled Amount");
            // Partial Received Amount
            csvHeader.add("Partial Received Amount");
            // Pending Invoice Amount
            csvHeader.add("Pending Invoice Amount");
            // Billing Hold Amount
            csvHeader.add("Billing Hold Amount");
            // 2017/11/21 QC#22550 Add Start
            // Pending Dealer Install Amount
            csvHeader.add("Pending Dealer Install Amount");
            // 2017/11/21 QC#22550 Add End
//            // Shipped Closed Amount
//            csvHeader.add("Shipped Closed Amount");
            // QC#11581 DEL Start
//            // Invoiced Amount
//            csvHeader.add("Invoiced Amount");
            // QC#11581 DEL End
            // Closed Amount
            csvHeader.add("Closed Amount");
            // Closed Loan Return Amount
            csvHeader.add("Closed Loan Return Amount");
            // Closed Loan Sold Amount
            csvHeader.add("Closed Loan Sold Amount");
            // Canceled Amount
            csvHeader.add("Canceled Amount");

            // Pending Import Count
            csvHeader.add("Pending Import Count");
            // Entered Count
            csvHeader.add("Entered Count");
            // DI Hold Count
            csvHeader.add("DI Hold Count");
            // Validation Count
            csvHeader.add("Validation Count");
            // Profitability Count
            csvHeader.add("Profitability Count");
            // Credit Hold Count
            csvHeader.add("Credit Hold Count");
            // 2018/07/05 QC#25786 mod start
//            // Supply Abuse Count
//            csvHeader.add("Supply Abuse Count");
            // Supply Enforcement Count
            csvHeader.add("Supply Enforcement Count");
            // 2018/07/05 QC#25786 mod end
            // Pending Re Submission Count
            csvHeader.add("Pending Re Submission Count");
            // 2019/05/07 QC#50031 Del Start
            // Booked Count
            //csvHeader.add("Booked Count");
            // 2019/05/07 QC#50031 Del End
            // 2017/11/21 QC#22550 Add Start
            // Awaiting Drop Ship Count
            csvHeader.add("Awaiting Drop Ship Count");
            // 2017/11/21 QC#22550 Add End
            // 2017/11/21 QC#22550 Mod Start
            //// SO Canceled Count
            //csvHeader.add("SO Canceled Count");
            // Pending Re Allocation Count
            csvHeader.add("Pending Re Allocation Count");
            // 2017/11/21 QC#22550 Mod End
            // PO Canceled Count
            csvHeader.add("PO Canceled Count");
            // Pending Fulfillment Count
            csvHeader.add("Pending Fulfillment Count");
            // Pending Allocation Count
            csvHeader.add("Pending Allocation Count");
            // Back Ordered Count
            csvHeader.add("Back Ordered Count");
            // Pending Pick Count
            csvHeader.add("Pending Pick Count");
            // Delivered to Shop Count
            csvHeader.add("Delivered to Shop Count");
            // In Shop/ Config Count
            csvHeader.add("In Shop/ Config Count");
            // Pending Shipment Count
            csvHeader.add("Pending Shipment Count");
            // Shipped Count
            csvHeader.add("Shipped Count");
            // Pending Delivery Confirmation Count
            csvHeader.add("Pending Delivery Confirmation Count");
            // Pending Installation Count
            csvHeader.add("Pending Installation Count");
            // On Loan Count
            csvHeader.add("On Loan Count");
            // Waiting Receipt Count
            csvHeader.add("Waiting Receipt Count");
            // Pending Return Count
            csvHeader.add("Pending Return Count");
            // Pending Inspection Count
            csvHeader.add("Pending Inspection Count");
            // RWS Canceled Count
            csvHeader.add("RWS Canceled Count");
            // Partial Received Count
            csvHeader.add("Partial Received Count");
            // Pending Invoice Count
            csvHeader.add("Pending Invoice Count");
            // Billing Hold Count
            csvHeader.add("Billing Hold Count");
            // 2017/11/21 QC#22550 Add Start
            // Pending Dealer Install Count
            csvHeader.add("Pending Dealer Install Count");
            // 2017/11/21 QC#22550 Add End
//            // Shipped Closed Count
//            csvHeader.add("Shipped Closed Count");
            // QC#11581 DEL Start
//            // Invoiced Count
//            csvHeader.add("Invoiced Count");
            // QC#11581 DEL End
            // Closed Count
            csvHeader.add("Closed Count");
            // Closed Loan Return Count
            csvHeader.add("Closed Loan Return Count");
            // Closed Loan Sold Count
            csvHeader.add("Closed Loan Sold Count");
            // Canceled Count
            csvHeader.add("Canceled Count");

            return csvHeader;
        }

        // 2018/06/05 S21_NA#24816 Del Start
//        private void getDplyByItemNmForDownload(NWAL1570CMsg cMsg, Integer intRowNum, Map<String, Object> data, NWAL1570F00FMsg fMsg, Map<String, BigDecimal> groupingMap) {
//
//            String dplyByItemNm = "";
//            String cpoOrdNum = getString(data, SQL_COL.SRC_REF_OR_CPO_ORD_NUM);
//
//            String dplyBy01ItemCd = getString(data, SQL_COL.DPLY_BY_01_ITEM_CD);
//            String dplyBy01ItemNm = getString(data, SQL_COL.DPLY_BY_01_ITEM_NM);
//            String dplyBy02ItemCd = getString(data, SQL_COL.DPLY_BY_02_ITEM_CD);
//            String dplyBy02ItemNm = getString(data, SQL_COL.DPLY_BY_02_ITEM_NM);
//            String dplyBy03ItemCd = getString(data, SQL_COL.DPLY_BY_03_ITEM_CD);
//            String dplyBy03ItemNm = getString(data, SQL_COL.DPLY_BY_03_ITEM_NM);
//
//            String dplyBy02SumLineFlg = getString(data, SQL_COL.DPLY_BY_02_SUM_LINE_FLG);
//            String dplyBy03SumLineFlg = getString(data, SQL_COL.DPLY_BY_03_SUM_LINE_FLG);
//
//            BigDecimal groupingId = getBigDecimal(data, SQL_COL.GROUPING_ID);
//
//            // ==================================
//            // Overall Total Line
//            // ==================================
//            fMsg.xxDplyByItemNm_01.clear();
//            if (intRowNum == 0) {
//                dplyByItemNm = DPLY_FIRST_RECORD;
//                setValue(fMsg.xxDplyByItemNm_01, dplyByItemNm);
//            } else {
//
//                if (groupingId.compareTo(BigDecimal.ZERO) != 0 && groupingMap.get(KEY_GRP_ID_01).compareTo(BigDecimal.ZERO) == 0) {
//                    groupingMap.put(KEY_GRP_ID_01, groupingId);
//                } else if (groupingId.compareTo(BigDecimal.ZERO) != 0 && groupingMap.get(KEY_GRP_ID_02).compareTo(BigDecimal.ZERO) == 0) {
//                    if (groupingMap.get(KEY_GRP_ID_01).compareTo(groupingId) != 0) {
//                        groupingMap.put(KEY_GRP_ID_02, groupingId);
//                    }
//                } else if (groupingId.compareTo(BigDecimal.ZERO) != 0 && groupingMap.get(KEY_GRP_ID_03).compareTo(BigDecimal.ZERO) == 0) {
//                    if (groupingMap.get(KEY_GRP_ID_01).compareTo(groupingId) != 0 && groupingMap.get(KEY_GRP_ID_02).compareTo(groupingId) != 0) {
//                        groupingMap.put(KEY_GRP_ID_03, groupingId);
//                    }
//                }
//                // +++++++++++++++++++++++++++++++++++
//                // Case : select display by 1,2,3
//                // +++++++++++++++++++++++++++++++++++
//                if (hasValue(dplyBy03SumLineFlg)) {
//                    // Case : Display By 3 Summary Line
//                    if (groupingId.compareTo(BigDecimal.ZERO) != 0 && groupingMap.get(KEY_GRP_ID_03).compareTo(groupingId) == 0) {
//                        if (hasValue(cMsg.dplyBy03ItemNm)
//                                && (cMsg.dplyBy03ItemNm.getValue().endsWith("_DT") || cMsg.dplyBy03ItemNm.getValue().endsWith("_TS"))) {
//                            dplyByItemNm = getDate(dplyBy03ItemCd);
//                        } else {
//                            dplyByItemNm = dplyBy03ItemCd;
//                        }
//                        if (hasValue(dplyBy03ItemNm)) {
//                            dplyByItemNm = concatString(dplyByItemNm, HYPHEN, dplyBy03ItemNm);
//                        }
//                        if (hasValue(dplyByItemNm)) {
//                            dplyByItemNm = BLANK + BLANK + HYPHEN + dplyByItemNm;
//                        }
//                        if (hasValue(dplyByItemNm) && dplyByItemNm.length() > DISP_BY_ITEM_MAX) {
//                            dplyByItemNm = dplyByItemNm.substring(0, DISP_BY_ITEM_MAX);
//                        }
//                        if (hasValue(dplyByItemNm)) {
//                            setValue(fMsg.xxDplyByItemNm_01, dplyByItemNm);
//                        }
//                        if (hasValue(dplyBy03ItemCd) && dplyBy03ItemCd.equals(cpoOrdNum)) {
//                            cpoOrdNum = "";
//                        }
//                        // Case : Display By 2 Summary Line
//                    } else if (groupingId.compareTo(BigDecimal.ZERO) != 0 && groupingMap.get(KEY_GRP_ID_02).compareTo(groupingId) == 0) {
//                        // Case : Display By 1 Summary Line
//                        if (hasValue(cMsg.dplyBy02ItemNm)
//                                && (cMsg.dplyBy02ItemNm.getValue().endsWith("_DT") || cMsg.dplyBy02ItemNm.getValue().endsWith("_TS"))) {
//                            dplyByItemNm = getDate(dplyBy02ItemCd);
//                        } else {
//                            dplyByItemNm = dplyBy02ItemCd;
//                        }
//                        if (hasValue(dplyBy02ItemNm)) {
//                            dplyByItemNm = concatString(dplyByItemNm, HYPHEN, dplyBy02ItemNm);
//                        }
//                        if (hasValue(dplyByItemNm)) {
//                            dplyByItemNm = BLANK + HYPHEN + dplyByItemNm;
//                        }
//                        if (hasValue(dplyByItemNm) && dplyByItemNm.length() > DISP_BY_ITEM_MAX) {
//                            dplyByItemNm = dplyByItemNm.substring(0, DISP_BY_ITEM_MAX);
//                        }
//                        if (hasValue(dplyByItemNm)) {
//                            setValue(fMsg.xxDplyByItemNm_01, dplyByItemNm);
//                        }
//                        if (hasValue(dplyBy02ItemCd) && dplyBy02ItemCd.equals(cpoOrdNum)) {
//                            cpoOrdNum = "";
//                        }
//                    } else if (groupingId.compareTo(BigDecimal.ZERO) != 0 && groupingMap.get(KEY_GRP_ID_01).compareTo(groupingId) == 0) {
//                        if (hasValue(cMsg.dplyBy01ItemNm)
//                                && (cMsg.dplyBy01ItemNm.getValue().endsWith("_DT") || cMsg.dplyBy01ItemNm.getValue().endsWith("_TS"))) {
//                            dplyByItemNm = getDate(dplyBy01ItemCd);
//                        } else {
//                            dplyByItemNm = dplyBy01ItemCd;
//                        }
//                        if (hasValue(dplyBy01ItemNm)) {
//                            dplyByItemNm = concatString(dplyByItemNm, HYPHEN, dplyBy01ItemNm);
//                        }
//                        if (hasValue(dplyByItemNm)) {
//                            dplyByItemNm = HYPHEN + dplyByItemNm;
//                        }
//                        if (hasValue(dplyByItemNm) && dplyByItemNm.length() > DISP_BY_ITEM_MAX) {
//                            dplyByItemNm = dplyByItemNm.substring(0, DISP_BY_ITEM_MAX);
//                        }
//                        if (hasValue(dplyByItemNm)) {
//                            setValue(fMsg.xxDplyByItemNm_01, dplyByItemNm);
//                        }
//                        if (hasValue(dplyBy01ItemCd) && dplyBy01ItemCd.equals(cpoOrdNum)) {
//                            cpoOrdNum = "";
//                        }
//                    }
//                    // +++++++++++++++++++++++++++++++++++
//                    // Case : select display by 1,2
//                    // +++++++++++++++++++++++++++++++++++
//                } else if (hasValue(dplyBy02SumLineFlg)) {
//                    // Case : Display By 2 Summary Line
//                    if (groupingId.compareTo(BigDecimal.ZERO) != 0 && groupingMap.get(KEY_GRP_ID_02).compareTo(groupingId) == 0) {
//                        // Case : Display By 1 Summary Line
//                        if (hasValue(cMsg.dplyBy02ItemNm)
//                                && (cMsg.dplyBy02ItemNm.getValue().endsWith("_DT") || cMsg.dplyBy02ItemNm.getValue().endsWith("_TS"))) {
//                            dplyByItemNm = getDate(dplyBy02ItemCd);
//                        } else {
//                            dplyByItemNm = dplyBy02ItemCd;
//                        }
//                        if (hasValue(dplyBy02ItemNm)) {
//                            dplyByItemNm = concatString(dplyByItemNm, HYPHEN, dplyBy02ItemNm);
//                        }
//                        if (hasValue(dplyByItemNm)) {
//                            dplyByItemNm = BLANK + HYPHEN + dplyByItemNm;
//                        }
//                        if (hasValue(dplyByItemNm) && dplyByItemNm.length() > DISP_BY_ITEM_MAX) {
//                            dplyByItemNm = dplyByItemNm.substring(0, DISP_BY_ITEM_MAX);
//                        }
//                        if (hasValue(dplyByItemNm)) {
//                            setValue(fMsg.xxDplyByItemNm_01, dplyByItemNm);
//                        }
//                        if (hasValue(dplyBy02ItemCd) && dplyBy02ItemCd.equals(cpoOrdNum)) {
//                            cpoOrdNum = "";
//                        }
//                    } else if (groupingId.compareTo(BigDecimal.ZERO) != 0 && groupingMap.get(KEY_GRP_ID_01).compareTo(groupingId) == 0) {
//                        if (hasValue(cMsg.dplyBy01ItemNm)
//                                && (cMsg.dplyBy01ItemNm.getValue().endsWith("_DT") || cMsg.dplyBy01ItemNm.getValue().endsWith("_TS"))) {
//                            dplyByItemNm = getDate(dplyBy01ItemCd);
//                        } else {
//                            dplyByItemNm = dplyBy01ItemCd;
//                        }
//                        if (hasValue(dplyBy01ItemNm)) {
//                            dplyByItemNm = concatString(dplyByItemNm, HYPHEN, dplyBy01ItemNm);
//                        }
//                        if (hasValue(dplyByItemNm)) {
//                            dplyByItemNm = HYPHEN + dplyByItemNm;
//                        }
//                        if (hasValue(dplyByItemNm) && dplyByItemNm.length() > DISP_BY_ITEM_MAX) {
//                            dplyByItemNm = dplyByItemNm.substring(0, DISP_BY_ITEM_MAX);
//                        }
//                        if (hasValue(dplyByItemNm)) {
//                            setValue(fMsg.xxDplyByItemNm_01, dplyByItemNm);
//                        }
//                        if (hasValue(dplyBy01ItemCd) && dplyBy01ItemCd.equals(cpoOrdNum)) {
//                            cpoOrdNum = "";
//                        }
//                    }
//                    // +++++++++++++++++++++++++++++++++++
//                    // Case : select display by 1
//                    // +++++++++++++++++++++++++++++++++++
//                } else {
//                    // Case : Display By 1 Summary Line
//                    dplyByItemNm = dplyBy01ItemCd;
//                    if (!groupingId.equals(BigDecimal.ZERO)) {
//                        if (hasValue(cMsg.dplyBy01ItemNm)
//                                && (cMsg.dplyBy01ItemNm.getValue().endsWith("_DT") || cMsg.dplyBy01ItemNm.getValue().endsWith("_TS"))) {
//                            dplyByItemNm = getDate(dplyBy01ItemCd);
//                        } else {
//                            dplyByItemNm = dplyBy01ItemCd;
//                        }
//                        if (hasValue(dplyBy01ItemNm)) {
//                            dplyByItemNm = concatString(dplyByItemNm, HYPHEN, dplyBy01ItemNm);
//                        }
//                        if (hasValue(dplyByItemNm)) {
//                            dplyByItemNm = HYPHEN + dplyByItemNm;
//                        }
//                        if (hasValue(dplyByItemNm) && dplyByItemNm.length() > DISP_BY_ITEM_MAX) {
//                            dplyByItemNm = dplyByItemNm.substring(0, DISP_BY_ITEM_MAX);
//                        }
//                        if (hasValue(dplyByItemNm)) {
//                            setValue(fMsg.xxDplyByItemNm_01, dplyByItemNm);
//                        }
//                        if (hasValue(dplyBy01ItemCd) && dplyBy01ItemCd.equals(cpoOrdNum)) {
//                            cpoOrdNum = "";
//                        }
//                    }
//                }
//            }
//            // Order No.
//            String dplyOrdNum = "";
//            if (hasValue(cpoOrdNum)) {
//                dplyOrdNum = cpoOrdNum;
//            }
//            setValue(fMsg.xxDplyOrdInqRefNum_A1, dplyOrdNum);
//        }
        // 2018/06/05 S21_NA#24816 Del End

        // 2017/10/2 QC#19913 Add Start
        private void getDplyByItemNmForSummaryDownload(NWAL1570CMsg cMsg, Integer intRowNum, Map<String, Object> data, NWAL1570F01FMsg f01Msg, Map<String, BigDecimal> groupingMap) {

            String dplyByItemNm = "";
            String cpoOrdNum = getString(data, SQL_COL.SRC_REF_OR_CPO_ORD_NUM);

            String dplyBy01ItemCd = getString(data, SQL_COL.DPLY_BY_01_ITEM_CD);
            String dplyBy01ItemNm = getString(data, SQL_COL.DPLY_BY_01_ITEM_NM);
            String dplyBy02ItemCd = getString(data, SQL_COL.DPLY_BY_02_ITEM_CD);
            String dplyBy02ItemNm = getString(data, SQL_COL.DPLY_BY_02_ITEM_NM);
            String dplyBy03ItemCd = getString(data, SQL_COL.DPLY_BY_03_ITEM_CD);
            String dplyBy03ItemNm = getString(data, SQL_COL.DPLY_BY_03_ITEM_NM);

            String dplyBy02SumLineFlg = getString(data, SQL_COL.DPLY_BY_02_SUM_LINE_FLG);
            String dplyBy03SumLineFlg = getString(data, SQL_COL.DPLY_BY_03_SUM_LINE_FLG);

            BigDecimal groupingId = getBigDecimal(data, SQL_COL.GROUPING_ID);

            // ==================================
            // Overall Total Line
            // ==================================
            f01Msg.xxDplyByItemNm_01.clear();
            if (intRowNum == 0) {
                dplyByItemNm = DPLY_FIRST_RECORD;
                setValue(f01Msg.xxDplyByItemNm_01, dplyByItemNm);
            } else {

                if (groupingId.compareTo(BigDecimal.ZERO) != 0 && groupingMap.get(KEY_GRP_ID_01).compareTo(BigDecimal.ZERO) == 0) {
                    groupingMap.put(KEY_GRP_ID_01, groupingId);
                } else if (groupingId.compareTo(BigDecimal.ZERO) != 0 && groupingMap.get(KEY_GRP_ID_02).compareTo(BigDecimal.ZERO) == 0) {
                    if (groupingMap.get(KEY_GRP_ID_01).compareTo(groupingId) != 0) {
                        groupingMap.put(KEY_GRP_ID_02, groupingId);
                    }
                } else if (groupingId.compareTo(BigDecimal.ZERO) != 0 && groupingMap.get(KEY_GRP_ID_03).compareTo(BigDecimal.ZERO) == 0) {
                    if (groupingMap.get(KEY_GRP_ID_01).compareTo(groupingId) != 0 && groupingMap.get(KEY_GRP_ID_02).compareTo(groupingId) != 0) {
                        groupingMap.put(KEY_GRP_ID_03, groupingId);
                    }
                }
                // +++++++++++++++++++++++++++++++++++
                // Case : select display by 1,2,3
                // +++++++++++++++++++++++++++++++++++
                if (hasValue(dplyBy03SumLineFlg)) {
                    // Case : Display By 3 Summary Line
                    if (groupingId.compareTo(BigDecimal.ZERO) != 0 && groupingMap.get(KEY_GRP_ID_03).compareTo(groupingId) == 0) {
                        if (hasValue(cMsg.dplyBy03ItemNm)
                                && (cMsg.dplyBy03ItemNm.getValue().endsWith("_DT") || cMsg.dplyBy03ItemNm.getValue().endsWith("_TS"))) {
                            dplyByItemNm = getDate(dplyBy03ItemCd);
                        } else {
                            dplyByItemNm = dplyBy03ItemCd;
                        }
                        if (hasValue(dplyBy03ItemNm)) {
                            dplyByItemNm = concatString(dplyByItemNm, HYPHEN, dplyBy03ItemNm);
                        }
                        if (hasValue(dplyByItemNm)) {
                            dplyByItemNm = BLANK + BLANK + HYPHEN + dplyByItemNm;
                        }
                        if (hasValue(dplyByItemNm) && dplyByItemNm.length() > DISP_BY_ITEM_MAX) {
                            dplyByItemNm = dplyByItemNm.substring(0, DISP_BY_ITEM_MAX);
                        }
                        if (hasValue(dplyByItemNm)) {
                            setValue(f01Msg.xxDplyByItemNm_01, dplyByItemNm);
                        }
                        if (hasValue(dplyBy03ItemCd) && dplyBy03ItemCd.equals(cpoOrdNum)) {
                            cpoOrdNum = "";
                        }
                        // Case : Display By 2 Summary Line
                    } else if (groupingId.compareTo(BigDecimal.ZERO) != 0 && groupingMap.get(KEY_GRP_ID_02).compareTo(groupingId) == 0) {
                        // Case : Display By 1 Summary Line
                        if (hasValue(cMsg.dplyBy02ItemNm)
                                && (cMsg.dplyBy02ItemNm.getValue().endsWith("_DT") || cMsg.dplyBy02ItemNm.getValue().endsWith("_TS"))) {
                            dplyByItemNm = getDate(dplyBy02ItemCd);
                        } else {
                            dplyByItemNm = dplyBy02ItemCd;
                        }
                        if (hasValue(dplyBy02ItemNm)) {
                            dplyByItemNm = concatString(dplyByItemNm, HYPHEN, dplyBy02ItemNm);
                        }
                        if (hasValue(dplyByItemNm)) {
                            dplyByItemNm = BLANK + HYPHEN + dplyByItemNm;
                        }
                        if (hasValue(dplyByItemNm) && dplyByItemNm.length() > DISP_BY_ITEM_MAX) {
                            dplyByItemNm = dplyByItemNm.substring(0, DISP_BY_ITEM_MAX);
                        }
                        if (hasValue(dplyByItemNm)) {
                            setValue(f01Msg.xxDplyByItemNm_01, dplyByItemNm);
                        }
                        if (hasValue(dplyBy02ItemCd) && dplyBy02ItemCd.equals(cpoOrdNum)) {
                            cpoOrdNum = "";
                        }
                    } else if (groupingId.compareTo(BigDecimal.ZERO) != 0 && groupingMap.get(KEY_GRP_ID_01).compareTo(groupingId) == 0) {
                        if (hasValue(cMsg.dplyBy01ItemNm)
                                && (cMsg.dplyBy01ItemNm.getValue().endsWith("_DT") || cMsg.dplyBy01ItemNm.getValue().endsWith("_TS"))) {
                            dplyByItemNm = getDate(dplyBy01ItemCd);
                        } else {
                            dplyByItemNm = dplyBy01ItemCd;
                        }
                        if (hasValue(dplyBy01ItemNm)) {
                            dplyByItemNm = concatString(dplyByItemNm, HYPHEN, dplyBy01ItemNm);
                        }
                        if (hasValue(dplyByItemNm)) {
                            dplyByItemNm = HYPHEN + dplyByItemNm;
                        }
                        if (hasValue(dplyByItemNm) && dplyByItemNm.length() > DISP_BY_ITEM_MAX) {
                            dplyByItemNm = dplyByItemNm.substring(0, DISP_BY_ITEM_MAX);
                        }
                        if (hasValue(dplyByItemNm)) {
                            setValue(f01Msg.xxDplyByItemNm_01, dplyByItemNm);
                        }
                        if (hasValue(dplyBy01ItemCd) && dplyBy01ItemCd.equals(cpoOrdNum)) {
                            cpoOrdNum = "";
                        }
                    }
                    // +++++++++++++++++++++++++++++++++++
                    // Case : select display by 1,2
                    // +++++++++++++++++++++++++++++++++++
                } else if (hasValue(dplyBy02SumLineFlg)) {
                    // Case : Display By 2 Summary Line
                    if (groupingId.compareTo(BigDecimal.ZERO) != 0 && groupingMap.get(KEY_GRP_ID_02).compareTo(groupingId) == 0) {
                        // Case : Display By 1 Summary Line
                        if (hasValue(cMsg.dplyBy02ItemNm)
                                && (cMsg.dplyBy02ItemNm.getValue().endsWith("_DT") || cMsg.dplyBy02ItemNm.getValue().endsWith("_TS"))) {
                            dplyByItemNm = getDate(dplyBy02ItemCd);
                        } else {
                            dplyByItemNm = dplyBy02ItemCd;
                        }
                        if (hasValue(dplyBy02ItemNm)) {
                            dplyByItemNm = concatString(dplyByItemNm, HYPHEN, dplyBy02ItemNm);
                        }
                        if (hasValue(dplyByItemNm)) {
                            dplyByItemNm = BLANK + HYPHEN + dplyByItemNm;
                        }
                        if (hasValue(dplyByItemNm) && dplyByItemNm.length() > DISP_BY_ITEM_MAX) {
                            dplyByItemNm = dplyByItemNm.substring(0, DISP_BY_ITEM_MAX);
                        }
                        if (hasValue(dplyByItemNm)) {
                            setValue(f01Msg.xxDplyByItemNm_01, dplyByItemNm);
                        }
                        if (hasValue(dplyBy02ItemCd) && dplyBy02ItemCd.equals(cpoOrdNum)) {
                            cpoOrdNum = "";
                        }
                    } else if (groupingId.compareTo(BigDecimal.ZERO) != 0 && groupingMap.get(KEY_GRP_ID_01).compareTo(groupingId) == 0) {
                        if (hasValue(cMsg.dplyBy01ItemNm)
                                && (cMsg.dplyBy01ItemNm.getValue().endsWith("_DT") || cMsg.dplyBy01ItemNm.getValue().endsWith("_TS"))) {
                            dplyByItemNm = getDate(dplyBy01ItemCd);
                        } else {
                            dplyByItemNm = dplyBy01ItemCd;
                        }
                        if (hasValue(dplyBy01ItemNm)) {
                            dplyByItemNm = concatString(dplyByItemNm, HYPHEN, dplyBy01ItemNm);
                        }
                        if (hasValue(dplyByItemNm)) {
                            dplyByItemNm = HYPHEN + dplyByItemNm;
                        }
                        if (hasValue(dplyByItemNm) && dplyByItemNm.length() > DISP_BY_ITEM_MAX) {
                            dplyByItemNm = dplyByItemNm.substring(0, DISP_BY_ITEM_MAX);
                        }
                        if (hasValue(dplyByItemNm)) {
                            setValue(f01Msg.xxDplyByItemNm_01, dplyByItemNm);
                        }
                        if (hasValue(dplyBy01ItemCd) && dplyBy01ItemCd.equals(cpoOrdNum)) {
                            cpoOrdNum = "";
                        }
                    }
                    // +++++++++++++++++++++++++++++++++++
                    // Case : select display by 1
                    // +++++++++++++++++++++++++++++++++++
                } else {
                    // Case : Display By 1 Summary Line
                    dplyByItemNm = dplyBy01ItemCd;
                    if (!groupingId.equals(BigDecimal.ZERO)) {
                        if (hasValue(cMsg.dplyBy01ItemNm)
                                && (cMsg.dplyBy01ItemNm.getValue().endsWith("_DT") || cMsg.dplyBy01ItemNm.getValue().endsWith("_TS"))) {
                            dplyByItemNm = getDate(dplyBy01ItemCd);
                        } else {
                            dplyByItemNm = dplyBy01ItemCd;
                        }
                        if (hasValue(dplyBy01ItemNm)) {
                            dplyByItemNm = concatString(dplyByItemNm, HYPHEN, dplyBy01ItemNm);
                        }
                        if (hasValue(dplyByItemNm)) {
                            dplyByItemNm = HYPHEN + dplyByItemNm;
                        }
                        if (hasValue(dplyByItemNm) && dplyByItemNm.length() > DISP_BY_ITEM_MAX) {
                            dplyByItemNm = dplyByItemNm.substring(0, DISP_BY_ITEM_MAX);
                        }
                        if (hasValue(dplyByItemNm)) {
                            setValue(f01Msg.xxDplyByItemNm_01, dplyByItemNm);
                        }
                        if (hasValue(dplyBy01ItemCd) && dplyBy01ItemCd.equals(cpoOrdNum)) {
                            cpoOrdNum = "";
                        }
                    }
                }
            }
        }
        // 2017/10/2 QC#19913 Add End

        // 2017/10/2 QC#19913 Del Start
//        private final class CsvHeaderWriter {
//
//            private final String csvFilePath;
//
//            private CsvHeaderWriter(String csvFilePath) {
//                this.csvFilePath = csvFilePath;
//            }
//
//            private void writeSearchCriteria(NWAL1570CMsg cMsg) {
//
//                List<String> criteriaLst = new ArrayList<String>();
//
//                // --------------------------------------------------
//                // Screen Info
//                // --------------------------------------------------
//                String now = ZYPDateUtil.getCurrentSystemTime("yyyyMMddhhmmss");
//                criteriaLst.add(BIZ_ID + ":" + "Order Inquiry" + " (" + ZYPDateUtil.formatEzd14ToDisp(now) + ")");
//
//                // -----------------------------------------------------
//                // Header Search Criteria
//                // -----------------------------------------------------
//                //criteriaLst.add("Header Search Criteria:");
//                add(criteriaLst, getLineBySrchTxt("Order#:", cMsg.xxCpoOrdNumSrchTxt_H1));
//                add(criteriaLst, getLineBySrchTxt("Orig. Order#:", cMsg.xxCpoOrdNumSrchTxt_H2));
//                add(criteriaLst, getLineBySrchTxt("Cust. PO#:", cMsg.custIssPoNumSrchTxt));
//                add(criteriaLst, getLineBySrchTxt("Lease PO#:", cMsg.xxLeasePoNumSrchTxt));
//                add(criteriaLst, getLineBySrchTxt("Sold To Name:", cMsg.xxSoldToAcctNmSrchTxt));
//                add(criteriaLst, getLineBySrchTxt("Sold To Acct#:", cMsg.xxSoldToAcctCdSrchTxt));
//                add(criteriaLst, getLineBySrchTxt("Sold To Loc#:", cMsg.xxSoldToLocCdSrchTxt));
//                add(criteriaLst, getLineBySrchTxt("Ship To Name:", cMsg.xxShipToAcctNmSrchTxt));
//                add(criteriaLst, getLineBySrchTxt("Ship To Acct#:", cMsg.xxShipToAcctCdSrchTxt));
//                add(criteriaLst, getLineBySrchTxt("Ship To Loc#:", cMsg.xxShipToLocCdSrchTxt));
//                add(criteriaLst, getLineBySrchTxt("Bill To Name:", cMsg.xxBillToAcctNmSrchTxt));
//                add(criteriaLst, getLineBySrchTxt("Bill To Acct#:", cMsg.xxBillToAcctCdSrchTxt));
//                add(criteriaLst, getLineBySrchTxt("Bill To Loc#:", cMsg.xxBillToLocCdSrchTxt));
//                add(criteriaLst, getLineBySrchTxt("Business Unit:", cMsg.xxCoaExtnSrchTxt));
//                add(criteriaLst, getLineBySrchTxt("Branch:", cMsg.xxCoaBrSrchTxt));
//                add(criteriaLst, getLineBySrchTxt("Sales Rep:", cMsg.xxSlsRepTocSrchTxt));
//                add(criteriaLst, getLineBySrchTxt("Order Source:", cMsg.xxCpoSrcTpSrchTxt));
//                add(criteriaLst, getLineBySrchTxt("LOB:", cMsg.xxDsBizLineSrchTxt));
//                add(criteriaLst, getLineBySrchTxt("Order Category:", cMsg.xxDsOrdCatgSrchTxt));
//                add(criteriaLst, getLineBySrchTxt("Order Reason:", cMsg.xxDsOrdTpSrchTxt));
//                add(criteriaLst, getLineBySrchTxt("Sub Reason:", cMsg.xxDsOrdRsnSrchTxt));
//                add(criteriaLst, getLineBySrchTxt("CSMP#:", cMsg.xxCsmpContrNumSrchTxt));
//                add(criteriaLst, getLineBySrchTxt("Association Program Name:", cMsg.xxPrcContrNumSrchTxt));
//                add(criteriaLst, getLineBySrchTxt("Import Source#:", cMsg.xxOrdSrcRefNumSrchTxt));
//                add(criteriaLst, getLineBySrchTxt("Item Name:", cMsg.xxMdseSrchTxt));
//                add(criteriaLst, getLineBySrchTxt("Item Code:", cMsg.mdseForSlsSmrySrchTxt));
//                add(criteriaLst, getLineBySrchTxt("PL Group:", cMsg.zerothProdCtrlSrchTxt));
//                add(criteriaLst, getLineBySrchTxt("PL1:", cMsg.firstProdCtrlSrchTxt));
//                add(criteriaLst, getLineBySrchTxt("PL2:", cMsg.scdProdCtrlSrchTxt));
//                add(criteriaLst, getLineBySrchTxt("PL3:", cMsg.thirdProdCtrlSrchTxt));
//                add(criteriaLst, getLineBySrchTxt("PL4:", cMsg.frthProdCtrlSrchTxt));
//                add(criteriaLst, getLineBySrchTxt("COA Prod:", cMsg.xxCoaProdSrchTxt));
//                add(criteriaLst, getLineBySrchTxt("COA MDSE:", cMsg.xxCoaMdseTpSrchTxt));
//                add(criteriaLst, getLineBySrchTxt("Model:", cMsg.xxMdlSrchTxt));
//                add(criteriaLst, getLineBySrchTxt("Serial#:", cMsg.xxSerNumSrchTxt));
//                add(criteriaLst, getLineByList("Return Reason:", cMsg.rtrnRsnCd_CD, cMsg.rtrnRsnDescTxt_NM, cMsg.rtrnRsnCd));
//                
//                // -----------------------------------------------------
//                // Line Search Criteria
//                // -----------------------------------------------------
//                //criteriaLst.add("Line Search Criteria:");
//                add(criteriaLst, getLineBySrchTxt("Line Category:", cMsg.xxLineCatgSrchTxt));
//                add(criteriaLst, getLineBySrchTxt("Line Source:", cMsg.xxOrdLineSrcSrchTxt));
//                add(criteriaLst, getLineBySrchTxt("WH:", cMsg.xxRtlWhSrchTxt));
//                add(criteriaLst, getLineBySrchTxt("SUB WH", cMsg.xxRtlSwhSrchTxt));
//                add(criteriaLst, getLineBySrchTxt("PO Vendor:", cMsg.xxVndSrchTxt));
//                add(criteriaLst, getLineBySrchTxt("P/O#:", cMsg.xxCpoOrdNumSrchTxt));
//                add(criteriaLst, getLineBySrchTxt("S/O#:", cMsg.soNumSrchTxt));
//                add(criteriaLst, getLineBySrchTxt("Invoice#:", cMsg.invNumSrchTxt));
//                add(criteriaLst, getLineBySrchTxt("Contract#:", cMsg.xxDsContrNumSrchTxt));
//                add(criteriaLst, getLineBySrchTxt("Config#:", cMsg.xxSvcConfigMstrSrchTxt));
//                // QC#15760 Add Start
//                add(criteriaLst, getLineBySrchTxt("Install Base ID:", cMsg.xxSvcMachMstrSrchTxt));
//                // QC#15760 Add End
//
//                // -----------------------------------------------------
//                // Order Team
//                // -----------------------------------------------------
//                //criteriaLst.add("Order Team Criteria:");
//                add(criteriaLst, getLineBySrchTxt("Team:", cMsg.xxOrdTeamSrchTxt));
//                add(criteriaLst, getLineBySrchTxt("Zone:", cMsg.xxOrdZnSrchTxt));
//                add(criteriaLst, getLineBySrchTxt("Created By:", cMsg.xxCratByUsrSrchTxt));
//
//                // -----------------------------------------------------
//                // Search Mode
//                // -----------------------------------------------------
//                //criteriaLst.add("Search Mode:");
//                criteriaLst.add("Mode:" + "," + getSelectNm(cMsg.xxRsltModeCd_CD, cMsg.xxRsltModeNm_NM, cMsg.xxRsltModeCd));
//                add(criteriaLst, getLineBySrchTxt("Include Pending Import:", cMsg.xxInclPendImptOrdFlg));
//                add(criteriaLst, getLineBySrchTxt("Sales:", cMsg.xxOnlySlsOrdFlg));
//
//                // -----------------------------------------------------
//                // Inquiry Status
//                // -----------------------------------------------------
//                //criteriaLst.add("Status:");
//                add(criteriaLst, getInquiryHeaderStatus(cMsg));
//                add(criteriaLst, getInquiryLineStatus(cMsg));
//                add(criteriaLst, getInquiryReturnLineStatus(cMsg));
//                add(criteriaLst, getLineBySrchTxt("All Open Order:", cMsg.xxAllOpenOrdFlg));
//
//                // -----------------------------------------------------
//                // Date Criteria
//                // -----------------------------------------------------
//                //criteriaLst.add("Date Criteria:");
//                add(criteriaLst, getLineByDate("Ordered Date(From):", cMsg.ordFromDt));
//                add(criteriaLst, getLineByDate("Ordered Date(To):", cMsg.ordToDt));
//                add(criteriaLst, getLineByDate("Booked Date(From):", cMsg.xxBookFromDt));
//                add(criteriaLst, getLineByDate("Booked Date(To):", cMsg.xxBookToDt));
//                add(criteriaLst, getLineByDate("Shipped Date(From):", cMsg.xxActlShipFromDt));
//                add(criteriaLst, getLineByDate("Shipped Date(To):", cMsg.xxActlShipToDt));
//                add(criteriaLst, getLineByDate("Invoice Date(From):", cMsg.invFromDt));
//                add(criteriaLst, getLineByDate("Invoice Date(To):", cMsg.invToDt));
//                add(criteriaLst, getLineByDate("Import Creation Date(From):", cMsg.xxOrdSrcImptFromDt));
//                add(criteriaLst, getLineByDate("Import Creation Date(To):", cMsg.xxOrdSrcImptToDt));
//
//                // -----------------------------------------------------
//                // Display Option
//                // -----------------------------------------------------
//                //criteriaLst.add("Display Option:");
//                add(criteriaLst, getLineByList("Display By-1:", cMsg.dplyBy01ItemNm_CD, cMsg.dplyBy01ItemNm_NM, cMsg.dplyBy01ItemNm));
//                add(criteriaLst, getLineByList("Display By-2:", cMsg.dplyBy02ItemNm_CD, cMsg.dplyBy02ItemNm_NM, cMsg.dplyBy02ItemNm));
//                add(criteriaLst, getLineByList("Display By-3:", cMsg.dplyBy03ItemNm_CD, cMsg.dplyBy03ItemNm_NM, cMsg.dplyBy03ItemNm));
//                criteriaLst.add("Display Mode:" + "," + getSelectNm(cMsg.grpByDnldCd_CD, cMsg.dplyByItemNm_DM, cMsg.grpByDnldCd));
//                add(criteriaLst, getLineBySrchTxt("Real Time Inquiry:", cMsg.xxPgFlg));
//
//                writeLine(criteriaLst);
//            }
//
//            private void add(List<String> lst, String value) {
//                if (hasValue(value)) {
//                    lst.add(value);
//                }
//            }
//
//            private String getLineBySrchTxt(String label, EZDCStringItem value) {
//
//                if (!hasValue(value)) {
//                    return null;
//                }
//
//                String convLbl = label;
//
//                StringBuilder sb = new StringBuilder();
//                append(sb, convLbl);
//                append(sb, ",");
//                append(sb, "\"");
//                append(sb, value.getValue());
//
//                append(sb, "\"");
//
//                return sb.toString();
//            }
//
//            private String getLineByList(String label, EZDCStringItemArray code, EZDCStringItemArray name, EZDCStringItem value) {
//
//                if (!hasValue(value)) {
//                    return null;
//                }
//
//                String convLbl = label;
//
//                StringBuilder sb = new StringBuilder();
//                append(sb, convLbl);
//                append(sb, ",");
//                append(sb, getSelectNm(code, name, value));
//
//                return sb.toString();
//            }
//
//            private String getSelectNm(EZDCStringItemArray code, EZDCStringItemArray name, EZDCStringItem value) {
//
//                if (!hasValue(value)) {
//                    return null;
//                }
//
//                for (int i = 0; i < code.length(); i++) {
//                    if (value.getValue().equals(code.no(i).getValue())) {
//                        return name.no(i).getValue();
//                    }
//                }
//                return null;
//            }
//
//            private String getLineByDate(String label, EZDCDateItem value) {
//
//                if (!hasValue(value)) {
//                    return null;
//                }
//
//                String convLbl = label;
//
//                StringBuilder sb = new StringBuilder();
//                append(sb, convLbl);
//                append(sb, ",");
//                append(sb, formatDate(value.getValue()));
//
//                return sb.toString();
//            }
//
//            private String getInquiryHeaderStatus(NWAL1570CMsg cMsg) {
//
//                StringBuilder sb = new StringBuilder();
//
//                boolean selFlg = false;
//                for (int i = 0; i < cMsg.H.length(); i++) {
//                    if (Y.equals(cMsg.H.no(i).xxOrdHdrStsSelFlg_HS.getValue())) {
//                        if (!selFlg) {
//                            append(sb, "Header Status:");
//                            append(sb, ",");
//                            append(sb, "\"");
//                        } else {
//                            append(sb, ",");
//                        }
//                        append(sb, cMsg.H.no(i).ordHdrStsNm_HS.getValue());
//                        selFlg = true;
//                    }
//                }
//                if (selFlg) {
//                    append(sb, "\"");
//                }
//
//                return sb.toString();
//            }
//
//            private String getInquiryLineStatus(NWAL1570CMsg cMsg) {
//
//                StringBuilder sb = new StringBuilder();
//
//                boolean selFlg = false;
//                for (int i = 0; i < cMsg.L.length(); i++) {
//                    if (Y.equals(cMsg.L.no(i).xxLineStsSelFlg_LS.getValue())) {
//                        if (!selFlg) {
//                            append(sb, "Line Status:");
//                            append(sb, ",");
//                            append(sb, "\"");
//                        } else {
//                            append(sb, ",");
//                        }
//                        append(sb, cMsg.L.no(i).ordLineStsNm_LS.getValue());
//                        selFlg = true;
//                    }
//                }
//                if (selFlg) {
//                    append(sb, "\"");
//                }
//
//                return sb.toString();
//            }
//
//            private String getInquiryReturnLineStatus(NWAL1570CMsg cMsg) {
//
//                StringBuilder sb = new StringBuilder();
//
//                boolean selFlg = false;
//                for (int i = 0; i < cMsg.R.length(); i++) {
//                    if (Y.equals(cMsg.R.no(i).xxRtrnLineStsSelFlg_RS.getValue())) {
//                        if (!selFlg) {
//                            append(sb, "Return Line Status:");
//                            append(sb, ",");
//                            append(sb, "\"");
//                        } else {
//                            append(sb, ",");
//                        }
//                        append(sb, cMsg.R.no(i).rtrnLineStsNm_RS.getValue());
//                        selFlg = true;
//                    }
//                }
//                if (selFlg) {
//                    append(sb, "\"");
//                }
//
//                return sb.toString();
//            }
//
//            private void writeHeader(List<String> headerLst) {
//
//                StringBuilder sb = new StringBuilder();
//
//                for (String header : headerLst) {
//                    if (sb.length() > 0) {
//                        append(sb, ",");
//                    }
//                    append(sb, header);
//                }
//
//                writeLine(asList(sb.toString()));
//            }
//
//            /**
//             * write a lline.
//             * @param rcdStrList
//             */
//            private void writeLine(List<String> lineLst) {
//
//                try {
//
//                    final FileOutputStream csvHeaderStrm = new FileOutputStream(this.csvFilePath, true);
//                    final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(csvHeaderStrm, "UTF-8"));
//                    final String nlCd = EZDDBEnv.getString("ezd.file.nl_code");
//
//                    for (String line : lineLst) {
//
//                        bw.write(line);
//
//                        // line break.
//                        if ("LF".equals(nlCd)) {
//                            bw.write("\n");
//                        } else if ("CRLF".equals(nlCd)) {
//                            bw.write("\r\n");
//                        } else {
//                            bw.newLine();
//                        }
//                    }
//
//                    // flush & close.
//                    bw.flush();
//                    bw.close();
//
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
        // 2017/10/2 QC#19913 Del End

    }

    // QC#11956 ADD Start
    public S21SsmEZDResult getProductLevelName(NWAL1570CMsg cMsg, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        return getSsmEZDClient().queryObjectList("getProductLevelName", ssmParam);
    }
    // QC#11956 ADD End

    // QC#22893 2018/08/13 Add Start
    private String createQuickSearchCondition(final NWAL1570CMsg cMsg, final Map<String, Serializable> param, final SEARCH_STATUS searchStatus, final String[][] orderCriteriaList) {

        if (N.equals((String) param.get(searchStatus.getValue()))) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        List<String> quickColumn = new ArrayList<String>();
        quickColumn.add(SEARCH_COLUMN.ORDER_NUMBER.name());
        quickColumn.add(SEARCH_COLUMN.AQU_NUMBER.name());
        quickColumn.add(SEARCH_COLUMN.LEASE_PO_NUMBER.name());
        quickColumn.add(SEARCH_COLUMN.CUSTOMER_PO_NUMBER.name());

        for (String[] orderCriteria : orderCriteriaList) {
            if (quickColumn.contains(orderCriteria[0])) {
                append(sb, createSearchCondition(param, searchStatus, orderCriteria[0], orderCriteria[1], orderCriteria[2], Y.equals(orderCriteria[3]), cMsg.xxSplCharTxt.getValue(), Y.equals(orderCriteria[4]), Y.equals(orderCriteria[5])));
            }

            if (N.equals((String) param.get(searchStatus.getValue()))) {
                break;
            }
        }

        if (N.equals((String) param.get(searchStatus.getValue()))) {
            return "";
        }

        return sb.toString();
    }
    // QC#22893 2018/08/13 Add End

}
