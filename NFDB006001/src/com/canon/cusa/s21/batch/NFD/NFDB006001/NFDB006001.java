/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFD.NFDB006001;

import static com.canon.cusa.s21.batch.NFD.NFDB006001.NFDB006001Constant.BUSINESS_ID;
import static com.canon.cusa.s21.batch.NFD.NFDB006001.NFDB006001Constant.COMPLETE_STRATEGY_ITEM_CD;
import static com.canon.cusa.s21.batch.NFD.NFDB006001.NFDB006001Constant.DEFAULT_WRT_OFF_RQST_FLG;
import static com.canon.cusa.s21.batch.NFD.NFDB006001.NFDB006001Constant.DEFAULT_WRT_OFF_RQST_USR_ID;
import static com.canon.cusa.s21.batch.NFD.NFDB006001.NFDB006001Constant.FETCH_SIZE_MAX;
import static com.canon.cusa.s21.batch.NFD.NFDB006001.NFDB006001Constant.MAIL_GROUP_ID_FROM;
import static com.canon.cusa.s21.batch.NFD.NFDB006001.NFDB006001Constant.MAIL_GROUP_ID_TO;
import static com.canon.cusa.s21.batch.NFD.NFDB006001.NFDB006001Constant.MAIL_TMPL_ID;
import static com.canon.cusa.s21.batch.NFD.NFDB006001.NFDB006001Constant.MAIL_TMPL_KEY_ACCT_NUM;
import static com.canon.cusa.s21.batch.NFD.NFDB006001.NFDB006001Constant.MAIL_TMPL_KEY_BILL_TO;
import static com.canon.cusa.s21.batch.NFD.NFDB006001.NFDB006001Constant.NFBM0184E;
import static com.canon.cusa.s21.batch.NFD.NFDB006001.NFDB006001Constant.NFBM0217E;
import static com.canon.cusa.s21.batch.NFD.NFDB006001.NFDB006001Constant.NFDB0060_WRT_OFF_RQST_FLG;
import static com.canon.cusa.s21.batch.NFD.NFDB006001.NFDB006001Constant.NFDM0004E;
import static com.canon.cusa.s21.batch.NFD.NFDB006001.NFDB006001Constant.NFDM0005E;
import static com.canon.cusa.s21.batch.NFD.NFDB006001.NFDB006001Constant.NFDM0013E;
import static com.canon.cusa.s21.batch.NFD.NFDB006001.NFDB006001Constant.UPDATE_CNT;
import static com.canon.cusa.s21.batch.NFD.NFDB006001.NFDB006001Constant.VAR_CHAR_CONST_NM_AR_SYS_BAT_USR_ID;
import static com.canon.cusa.s21.batch.NFD.NFDB006001.NFDB006001Constant.WRT_OFF_OPT_TP_CD_CRAT_ADJ;
import static com.canon.cusa.s21.batch.NFD.NFDB006001.NFDB006001Constant.ZZZM9025E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import parts.common.EZDMessageInfo;
import business.db.AR_TRX_BALTMsg;
import business.db.AR_WRT_OFF_RQSTTMsg;
import business.db.AR_WRT_OFF_RQST_DTLTMsg;
import business.db.BILL_TO_CUSTTMsg;
import business.db.CLT_STRGY_WRK_ITEM_TRXTMsg;

import com.canon.cusa.s21.api.NFD.NFZC500001.NFZC500001TokenObject;
import com.canon.cusa.s21.api.NFD.NFZC500001.NFZC500001TokenObjectLine;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_ADJ_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_DS_WF_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_WRT_OFF_RQST_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_CUST_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_STRGY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_WRK_ITEM_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_WRK_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContext;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContextFactory;
import com.canon.cusa.s21.framework.nwf.core.common.impl.S21NwfBatchContext;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfBizException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfSystemException;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcess;
import com.canon.cusa.s21.framework.nwf.core.token.S21NwfToken;
import com.canon.cusa.s21.framework.nwf.util.common.S21NwfUtilContextFactory;
import com.canon.cusa.s21.framework.nwf.util.process.S21NwfUtilProcessFactory;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 04/29/2015   Fujitsu         Y.Kamide        Create          N/A
 * 01/13/2016   CSAI            K.Uramori       Update          Apply finalized specification
 * 2016/09/12   Hitachi         K.Kojima        Update          QC#14038
 * 2016/09/26   Hitachi         K.Kojima        Update          QC#13004
 * 2016/11/07   Hitachi         J.Kim           Update          QC#15712
 * 2016/11/08   Hitachi         E.Kameishi      Update          QC#15747
 * 2016/11/19   Fujitsu         T.Murai         Update          QC#15747
 * 2017/01/05   Hitachi         E.Kameishi      Update          QC#16817
 * 2017/02/21   Fujitsu         T.Murai         Update          QC#16526
 * 2017/06/26   Hitachi         E.Kameishi      Update          QC#18754
 * 2017/08/21   Hitachi         T.Tsuchida      Update          QC#19573
 * 2018/03/13   Hitachi         J.Kim           Update          QC#24769
 * 2018/03/29   Hitachi         J.Kim           Update          QC#20944
 * 2018/05/24   Hitachi         J.Kim           Update          QC#25561
 * 2018/06/21   Hitachi         E.Kameishi      Update          QC#25560
 * 2021/07/09   CITS            G.Delgado       Update          QC#58939
 *</pre>
 */
public class NFDB006001 extends S21BatchMain implements ZYPConstant{

    /** Normal Counter */
    private int normCnt = 0;

    /** Error Counter */
    private int errCnt = 0;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Batch Process Date */
    private String batProcDt = null;

    /** wrtOffRqstUsrId */
    private String wrtOffRqstUsrId = null;

    /** wrtOffRqstGrpId */
    private String wrtOffRqstGrpId = null; // ADD 2016/02/20 [QC#16526]

    // START 2021/07/09 G.Delgado [QC#58939, ADD]
    /** wrtOffRqstFlg */
    private String wrtOffRqstFlg = null;
    // END 2021/07/09 G.Delgado [QC#58939, ADD]

    /** Term Code */
    private TERM_CD termCd = null;

    /** SSM-Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** List of Request PK */
    private BigDecimal rqstPk;

    @Override
    protected void initRoutine() {
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.termCd = TERM_CD.NORMAL_END;

        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Global Company Code" });
        }

        this.batProcDt = ZYPDateUtil.getBatProcDate(this.glblCmpyCd, BUSINESS_ID);

        this.wrtOffRqstUsrId = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NM_AR_SYS_BAT_USR_ID, this.glblCmpyCd);
        if (!hasValue(this.wrtOffRqstUsrId)) {
            this.wrtOffRqstUsrId = DEFAULT_WRT_OFF_RQST_USR_ID;
        }

        // START 2021/07/09 G.Delgado [QC#58939, ADD]
        this.wrtOffRqstFlg = ZYPCodeDataUtil.getVarCharConstValue(NFDB0060_WRT_OFF_RQST_FLG, this.glblCmpyCd);
        if (!hasValue(this.wrtOffRqstFlg)) {
            this.wrtOffRqstFlg = DEFAULT_WRT_OFF_RQST_FLG;
        }
        // END 2021/07/09 G.Delgado [QC#58939, ADD]
    }

    @Override
    protected void mainRoutine() {
        execute();
    }

    @Override
    protected void termRoutine() {
        setTermState(termCd, normCnt, errCnt, normCnt + errCnt);
    }

    /**
     * main
     * @param args String[]
     */
    public static void main(String[] args) {
        new NFDB006001().executeBatch(NFDB006001.class.getSimpleName());
    }

    /**
     * execute
     */
    private void execute() {

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getWorkItemTran();
            rs = ps.executeQuery();

            Map<String, BillToData> billToMap = new HashMap<String, BillToData>();
            while (rs.next()) {
                String billToCustCd = rs.getString("BILL_TO_CUST_CD");
                if (billToCustCd != null && !billToMap.containsKey(billToCustCd)) {
                    billToMap.put(billToCustCd, createBillToData(rs));
                }
            }

            executeWriteOff(billToMap);

            rs.beforeFirst();
            updateCltStrtgyWrkItemTrx(rs);

            // 2016/01/13 add
            commit();

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            setRecordCount(normCnt, errCnt, normCnt + errCnt);
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    /**
     * updateCltStrtgyWrkItemTrx
     * @throws SQLException
     */
    private void updateCltStrtgyWrkItemTrx(ResultSet rs) throws SQLException {

        List<CLT_STRGY_WRK_ITEM_TRXTMsg> updateList = new ArrayList<CLT_STRGY_WRK_ITEM_TRXTMsg>();
        while (rs.next()) {

            BigDecimal cltStrgyWorkItemTrxPk = rs.getBigDecimal("CLT_STRGY_WRK_ITEM_TRX_PK");
            CLT_STRGY_WRK_ITEM_TRXTMsg updateMsg = getCltStrtgyWrkItemTrxForUpdate(cltStrgyWorkItemTrxPk);
            if (updateMsg == null) {
                continue;
            }

            setValue(updateMsg.cltWrkItemWsrdDt, this.batProcDt);
            //---- start mod 2016/01/13 update to close
            // START 2016/09/26 K.Kojima [QC#13004,MOD]
            // setValue(updateMsg.cltWrkItemStsCd,
            // CLT_WRK_ITEM_STS.CLOSE);
            setValue(updateMsg.cltWrkItemStsCd, CLT_WRK_ITEM_STS.COMPLETED);
            // END 2016/09/26 K.Kojima [QC#13004,MOD]
            //---- end 2016/01/13
            // START 2017/01/05 E.Kameishi [QC#16817,ADD]
            setValue(updateMsg.cltWrkItemWerdDt, this.batProcDt);
            // END 2017/01/05 E.Kameishi [QC#16817,ADD]

            updateList.add(updateMsg);
            if (updateList.size() >= UPDATE_CNT) {
                updateCltStrtgyWrkItemTrx(updateList);
                updateList.clear();
            }

        }
        if (updateList.size() > 0) {
            updateCltStrtgyWrkItemTrx(updateList);
        }
    }

    /**
     * updateCltStrtgyWrkItemTrx
     * @param updateList List<CLT_STRGY_WRK_ITEM_TRXTMsg>
     */
    private void updateCltStrtgyWrkItemTrx(List<CLT_STRGY_WRK_ITEM_TRXTMsg> updateList) {
        int cnt = S21FastTBLAccessor.update(updateList.toArray(new CLT_STRGY_WRK_ITEM_TRXTMsg[updateList.size()]));
        if (cnt != updateList.size()) {
            String[] args = {"CLT_STRGY_WRK_ITEM_TRX" };
            throw new S21AbendException(NFDM0004E, args);
        }
        commit();
    }

    /**
     * createBillToData
     * @param rs ResultSet
     * @return BillToData
     * @throws SQLException
     */
    private BillToData createBillToData(ResultSet rs) throws SQLException {
        BillToData data = new BillToData();
        data.setBillToCustCd(rs.getString("BILL_TO_CUST_CD"));
        data.setBillToCustPk(rs.getBigDecimal("BILL_TO_CUST_PK"));
        data.setArAdjTpCdBill(rs.getString("AR_ADJ_TP_CD_BILL"));

        //---- start add 2016/01/13
        data.setArAdjTpCdAcct(rs.getString("AR_ADJ_TP_CD_ACCT"));
        data.setCltPtfoPkBill(rs.getBigDecimal("CLT_PTFO_PK_BILL"));
        data.setCltPtfoPkAcct(rs.getBigDecimal("CLT_PTFO_PK_ACCT"));
        data.setCltAcctCd(rs.getString("CLT_ACCT_CD"));
        data.setCltPsnCdBill(rs.getString("CLT_PSN_CD_BILL"));
        data.setCltPsnCdAcct(rs.getString("CLT_PSN_CD_ACCT"));
        //---- end 2016/01/13

        return data;
    }

    /**
     * executeWriteOff
     * @param billToMap Map<String, BillToData>
     */
    private void executeWriteOff(Map<String, BillToData> billToMap) {
        for (String bllgToCustCd : billToMap.keySet()) {
            List<AR_TRX_BALTMsg> trxBalList = executeWriteOff(bllgToCustCd, billToMap.get(bllgToCustCd));

            //----- add 2016/01/20
            // START 2021/07/09 G.Delgado [QC#58939, MOD]
            // startWorkflow(trxBalList, billToMap.get(bllgToCustCd));
            if (trxBalList != null) {
                // start WF
                startWorkflow(trxBalList, billToMap.get(bllgToCustCd));
            }
            // END 2021/07/09 G.Delgado [QC#58939, MOD]
            //---- end 2016/01/20

        }
    }

    /**
     * executeWriteOff
     * @param bllgToCustCd String
     * @param billToData BillToData
     * @return List<AR_TRX_BALTMsg>
     */
    private List<AR_TRX_BALTMsg> executeWriteOff(String bllgToCustCd, BillToData billToData) {

        updateBillToCust(billToData.getBillToCustPk());
        // START 2021/07/09 G.Delgado [QC#58939, ADD]
        if (ZYPConstant.FLG_ON_Y.equals(this.wrtOffRqstFlg)) {
        // END 2021/07/09 G.Delgado [QC#58939, ADD]

            List<AR_TRX_BALTMsg> trxBalList = createArWrtOffRqst(billToData);

            // mod 2016/01/13 commit only when entire process is successfully completed
            //commit();

            // this.normCnt++; // DEL 2016/11/22 [QC#15747]

            return trxBalList;
        // START 2021/07/09 G.Delgado [QC#58939, ADD]
        }
        return null;
        // END 2021/07/09 G.Delgado [QC#58939, ADD]
    }

    /**
     * createArWrtOffRqst
     * @param billToData BillToData
     * @return List<AR_TRX_BALTMsg>
     */
    private List<AR_TRX_BALTMsg> createArWrtOffRqst(BillToData billToData) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int cnt = 0;
        List<AR_WRT_OFF_RQST_DTLTMsg> dtlList = new ArrayList<AR_WRT_OFF_RQST_DTLTMsg>();
        List<AR_TRX_BALTMsg> trxBalList = new ArrayList<AR_TRX_BALTMsg>();

        BigDecimal wrtOffApplyAmt = BigDecimal.ZERO;
        try {
            ps = getArTrxBalList(billToData.getBillToCustCd());
            rs = ps.executeQuery();

            AR_WRT_OFF_RQSTTMsg arWrtOffRqstTMsg = createAR_WRT_OFF_RQSTTMsg(billToData);
            while (rs.next()) {
                dtlList.add(createAR_WRT_OFF_RQST_DTLTMsg(rs, arWrtOffRqstTMsg));

                // add 2016/01/20 for WF
                trxBalList.add(createAR_TRX_BALTMsg(rs));

                cnt++;
                // START 2018/05/28 J.Kim [QC#25561,ADD]
                wrtOffApplyAmt = wrtOffApplyAmt.add(rs.getBigDecimal("DEAL_RMNG_BAL_GRS_AMT"));
                // END 2018/05/28 J.Kim [QC#25561,ADD]
            }
            // START 2018/05/28 J.Kim [QC#25561,ADD]
            ZYPEZDItemValueSetter.setValue(arWrtOffRqstTMsg.wrtOffApplyAmt, wrtOffApplyAmt);
            // END 2018/05/28 J.Kim [QC#25561,ADD]
            if (dtlList.size() > 0) {
                createArWrtOffRqst(arWrtOffRqstTMsg, dtlList);
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
        // 2016/01/20 mod. return the list of detail
        return trxBalList;
    }

    /**
     * createAR_WRT_OFF_RQST_DTLTMsg
     * @param rs ResultSet
     * @param arWrtOffRqstTMsg AR_WRT_OFF_RQSTTMsg
     * @return AR_WRT_OFF_RQST_DTLTMsg
     * @throws SQLException
     */
    private AR_WRT_OFF_RQST_DTLTMsg createAR_WRT_OFF_RQST_DTLTMsg(ResultSet rs, AR_WRT_OFF_RQSTTMsg arWrtOffRqstTMsg) throws SQLException {
        AR_WRT_OFF_RQST_DTLTMsg arWrtOffRqstDtlTMsg = new AR_WRT_OFF_RQST_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(arWrtOffRqstDtlTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(arWrtOffRqstDtlTMsg.arWrtOffRqstPk, arWrtOffRqstTMsg.arWrtOffRqstPk);
        ZYPEZDItemValueSetter.setValue(arWrtOffRqstDtlTMsg.arTrxBalPk, rs.getBigDecimal("AR_TRX_BAL_PK"));
        ZYPEZDItemValueSetter.setValue(arWrtOffRqstDtlTMsg.invTrxBalLastUpdTs, rs.getString("EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(arWrtOffRqstDtlTMsg.invTrxBalTz, rs.getString("EZUPTIMEZONE"));
        //---- start add 2016/01/14 -->
        ZYPEZDItemValueSetter.setValue(arWrtOffRqstDtlTMsg.arTrxNum, rs.getString("AR_TRX_NUM"));
        ZYPEZDItemValueSetter.setValue(arWrtOffRqstDtlTMsg.billToCustCd, rs.getString("BILL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(arWrtOffRqstDtlTMsg.billToCustAcctCd, rs.getString("BILL_TO_CUST_ACCT_CD"));
        ZYPEZDItemValueSetter.setValue(arWrtOffRqstDtlTMsg.dealCcyCd, rs.getString("DEAL_CCY_CD"));
        ZYPEZDItemValueSetter.setValue(arWrtOffRqstDtlTMsg.dealOrigGrsAmt, rs.getBigDecimal("DEAL_ORIG_GRS_AMT"));
        ZYPEZDItemValueSetter.setValue(arWrtOffRqstDtlTMsg.funcOrigGrsAmt, rs.getBigDecimal("FUNC_ORIG_GRS_AMT"));
        ZYPEZDItemValueSetter.setValue(arWrtOffRqstDtlTMsg.dealRmngBalGrsAmt, rs.getBigDecimal("DEAL_RMNG_BAL_GRS_AMT"));
        ZYPEZDItemValueSetter.setValue(arWrtOffRqstDtlTMsg.funcRmngBalGrsAmt, rs.getBigDecimal("FUNC_RMNG_BAL_GRS_AMT"));
        ZYPEZDItemValueSetter.setValue(arWrtOffRqstDtlTMsg.arTrxDt, rs.getString("AR_TRX_DT"));
        ZYPEZDItemValueSetter.setValue(arWrtOffRqstDtlTMsg.invDueDt, rs.getString("INV_DUE_DT"));
        ZYPEZDItemValueSetter.setValue(arWrtOffRqstDtlTMsg.dsContrNum, rs.getString("DS_CONTR_NUM"));
        ZYPEZDItemValueSetter.setValue(arWrtOffRqstDtlTMsg.bllgPerFromDt, rs.getString("BLLG_PER_FROM_DT"));
        ZYPEZDItemValueSetter.setValue(arWrtOffRqstDtlTMsg.bllgPerToDt, rs.getString("BLLG_PER_TO_DT"));
        ZYPEZDItemValueSetter.setValue(arWrtOffRqstDtlTMsg.daysPastDueAot, rs.getBigDecimal("DAYS_PAST_DUE_AOT"));
        //---- end 2016/01/14
        return arWrtOffRqstDtlTMsg;
    }

    /**
     * createAR_TRX_BALTMsg
     * @param rs ResultSet
     * @return AR_TRX_BALTMsg
     * @throws SQLException
     */
    private AR_TRX_BALTMsg createAR_TRX_BALTMsg(ResultSet rs) throws SQLException {
        AR_TRX_BALTMsg tmsg = new AR_TRX_BALTMsg();

        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tmsg.arTrxBalPk, rs.getBigDecimal("AR_TRX_BAL_PK"));
        ZYPEZDItemValueSetter.setValue(tmsg.arTrxNum, rs.getString("AR_TRX_NUM"));
        ZYPEZDItemValueSetter.setValue(tmsg.billToCustCd, rs.getString("BILL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(tmsg.billToCustAcctCd, rs.getString("BILL_TO_CUST_ACCT_CD"));
        ZYPEZDItemValueSetter.setValue(tmsg.dealCcyCd, rs.getString("DEAL_CCY_CD"));
        ZYPEZDItemValueSetter.setValue(tmsg.dealOrigGrsAmt, rs.getBigDecimal("DEAL_ORIG_GRS_AMT"));
        ZYPEZDItemValueSetter.setValue(tmsg.funcOrigGrsAmt, rs.getBigDecimal("FUNC_ORIG_GRS_AMT"));
        ZYPEZDItemValueSetter.setValue(tmsg.dealRmngBalGrsAmt, rs.getBigDecimal("DEAL_RMNG_BAL_GRS_AMT"));
        ZYPEZDItemValueSetter.setValue(tmsg.funcRmngBalGrsAmt, rs.getBigDecimal("FUNC_RMNG_BAL_GRS_AMT"));
        ZYPEZDItemValueSetter.setValue(tmsg.arTrxDt, rs.getString("AR_TRX_DT"));
        ZYPEZDItemValueSetter.setValue(tmsg.invDueDt, rs.getString("INV_DUE_DT"));
        ZYPEZDItemValueSetter.setValue(tmsg.cpoOrdNum, rs.getString("CPO_ORD_NUM"));
        ZYPEZDItemValueSetter.setValue(tmsg.arTrxTpCd, rs.getString("AR_TRX_TP_CD"));

        return tmsg;
    }

    /**
     * createAR_WRT_OFF_RQSTTMsg
     * @param billToData BillToData
     * @return AR_WRT_OFF_RQSTTMsg
     */
    private AR_WRT_OFF_RQSTTMsg createAR_WRT_OFF_RQSTTMsg(BillToData billToData) {
        AR_WRT_OFF_RQSTTMsg arWrtOffRqstTMsg = new AR_WRT_OFF_RQSTTMsg();
        ZYPEZDItemValueSetter.setValue(arWrtOffRqstTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(arWrtOffRqstTMsg.arWrtOffRqstPk, ZYPOracleSeqAccessor.getNumberBigDecimal("AR_WRT_OFF_RQST_SQ"));
        ZYPEZDItemValueSetter.setValue(arWrtOffRqstTMsg.wrtOffRqstUsrId, this.wrtOffRqstUsrId);
        // START 2017/02/21 [QC#16526, ADD]
        this.wrtOffRqstGrpId = this.wrtOffRqstUsrId + ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS");
        ZYPEZDItemValueSetter.setValue(arWrtOffRqstTMsg.wrtOffRqstGrpCd, this.wrtOffRqstGrpId);
        // END   2017/02/21 [QC#16526, ADD]
        ZYPEZDItemValueSetter.setValue(arWrtOffRqstTMsg.arAdjTpCd, nullVal(billToData.getArAdjTpCdBill(), billToData.getArAdjTpCdAcct()));
        // START 2018/05/24 J.Kim [QC#25561,ADD]
        ZYPEZDItemValueSetter.setValue(arWrtOffRqstTMsg.arAdjRsnCd, AR_ADJ_RSN.WRITE_OFF);
        // END 2018/05/24 J.Kim [QC#25561,ADD]
        // START 2017/08/21 T.Tsuchida [QC#19573,ADD]
        ZYPEZDItemValueSetter.setValue(arWrtOffRqstTMsg.lowDsAcctNum, billToData.getCltAcctCd());
        ZYPEZDItemValueSetter.setValue(arWrtOffRqstTMsg.highDsAcctNum, billToData.getCltAcctCd());
        // END 2017/08/21 T.Tsuchida [QC#19573,ADD]
        ZYPEZDItemValueSetter.setValue(arWrtOffRqstTMsg.lowBillToCustCd, billToData.getBillToCustCd());
        ZYPEZDItemValueSetter.setValue(arWrtOffRqstTMsg.highBillToCustCd, billToData.getBillToCustCd());
        ZYPEZDItemValueSetter.setValue(arWrtOffRqstTMsg.inclConslInvFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(arWrtOffRqstTMsg.wrtOffOptTpCd, WRT_OFF_OPT_TP_CD_CRAT_ADJ);
        ZYPEZDItemValueSetter.setValue(arWrtOffRqstTMsg.arWrtOffRqstTpCd, AR_WRT_OFF_RQST_TP.COMPLETE_STRATEGY);
        ZYPEZDItemValueSetter.setValue(arWrtOffRqstTMsg.arDsWfStsCd, AR_DS_WF_STS.PENDING);
        ZYPEZDItemValueSetter.setValue(arWrtOffRqstTMsg.procStsCd, PROC_STS.IN_COMPLETED);
        return arWrtOffRqstTMsg;
    }

    private String nullVal(String val, String rpls) {

        if (!hasValue(val)) {
            return rpls;
        }

        return val;

    }

    // START 2018/03/29 J.Kim [QC#20944,DEL]
    // private BigDecimal nullVal(BigDecimal val, BigDecimal rpls) {
    //
    // if (!hasValue(val)) {
    // return rpls;
    // }
    //
    // return val;
    //
    // }
    // END 2018/03/29 J.Kim [QC#20944,DEL]

    /**
     * createArWrtOffRqst
     * @param arWrtOffRqstTMsg AR_WRT_OFF_RQSTTMsg
     * @param dtlList List<AR_WRT_OFF_RQST_DTLTMsg>
     */
    private void createArWrtOffRqst(AR_WRT_OFF_RQSTTMsg arWrtOffRqstTMsg, List<AR_WRT_OFF_RQST_DTLTMsg> dtlList) {
        S21FastTBLAccessor.insert(arWrtOffRqstTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(arWrtOffRqstTMsg.getReturnCode())) {
            String[] args = {"AR_WRT_OFF_RQST" };
            throw new S21AbendException(NFDM0013E, args);
        }

        // WF -- store PK 
        rqstPk = arWrtOffRqstTMsg.arWrtOffRqstPk.getValue();

        int updateCnt = S21FastTBLAccessor.insert(dtlList.toArray(new AR_WRT_OFF_RQST_DTLTMsg[dtlList.size()]));
        if (updateCnt != dtlList.size()) {
            String[] args = {"AR_WRT_OFF_RQST_DTL" };
            throw new S21AbendException(NFDM0013E, args);
        }
    }

    /**
     * updateBillToCust
     * @param bllgToCustPk BigDecimal
     */
    private void updateBillToCust(BigDecimal bllgToCustPk) {
        BILL_TO_CUSTTMsg billToCustTMsg = getBillToCustForUpdate(bllgToCustPk);
        if (billToCustTMsg == null) {
            String[] args = {"BILL_TO_CUST", "BILL_TO_CUST_PK=" + bllgToCustPk };
            throw new S21AbendException(NFDM0005E, args);
        }

        //--- start mod 2016/01/13
        setValue(billToCustTMsg.cltCustTpCd, CLT_CUST_TP.STRATEGY_COMPLETED);
        //---- end 2016/01/13

        S21FastTBLAccessor.update(billToCustTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(billToCustTMsg.getReturnCode())) {
            String[] args = {"BILL_TO_CUST" };
            throw new S21AbendException(NFDM0004E, args);
        }
    }

    /**
     * getWorkItemTran
     * @return PreparedStatement
     * @throws SQLException
     */
    private PreparedStatement getWorkItemTran() throws SQLException {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("batProcDt", this.batProcDt);
        queryParam.put("cltStrgyStsCd", CLT_STRGY_STS.OPEN);
        queryParam.put("cltWrkItemCd", COMPLETE_STRATEGY_ITEM_CD);
        queryParam.put("cltWrkItemStsCd", CLT_WRK_ITEM_STS.PENDING);
        // START 2017/06/26 E.Kameishi [QC#18754, ADD]
        queryParam.put("wrkItmStsOpen", CLT_WRK_ITEM_STS.OPEN);
        // END 2017/06/26 E.Kameishi [QC#18754, ADD]
        queryParam.put("flgY", FLG_ON_Y);
        // START 2017/08/03 J.Kim [QC#18754, ADD]
        queryParam.put("wrkTpCd", CLT_WRK_TP.MANUAL);
        // END 2017/08/03 J.Kim [QC#18754, ADD]

        return this.ssmLLClient.createPreparedStatement("getWorkItemTran", queryParam, getExecPrm());
    }

    /**
     * getArTrxBalList
     * @param billToCustCd String
     * @return PreparedStatement
     * @throws SQLException
     */
    private PreparedStatement getArTrxBalList(String billToCustCd) throws SQLException {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);

        // START 2017/08/21 T.Tsuchida [QC#19573,MOD]
        List<String> arTrxTpCdList = new ArrayList<String>();
        arTrxTpCdList.add(AR_TRX_TP.INVOICE);
        arTrxTpCdList.add(AR_TRX_TP.DEDUCTION);
        arTrxTpCdList.add(AR_TRX_TP.CREDIT_MEMO);
        arTrxTpCdList.add(AR_TRX_TP.ON_ACCOUNT);
        queryParam.put("arTrxTpCdList", arTrxTpCdList);
        // END 2017/08/21 T.Tsuchida [QC#19573,MOD]
        queryParam.put("batProcDt", this.batProcDt);

        queryParam.put("billToCustCd", billToCustCd);
        List<String> arCashApplyStsCdList = new ArrayList<String>();
        arCashApplyStsCdList.add(AR_CASH_APPLY_STS.UNAPPLIED);
        arCashApplyStsCdList.add(AR_CASH_APPLY_STS.PARTIAL);
        queryParam.put("arCashApplyStsCdList", arCashApplyStsCdList);

        return this.ssmLLClient.createPreparedStatement("getArTrxBalList", queryParam, getExecPrm());
    }

    /**
     * getCltStrtgyWrkItemTrxForUpdate
     * @param cltStrgyWorkItemTrxPk BigDecimal
     * @return CLT_STRGY_WRK_ITEM_TRXTMsg
     */
    private CLT_STRGY_WRK_ITEM_TRXTMsg getCltStrtgyWrkItemTrxForUpdate(BigDecimal cltStrgyWorkItemTrxPk) {
        CLT_STRGY_WRK_ITEM_TRXTMsg inMsg = new CLT_STRGY_WRK_ITEM_TRXTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.cltStrgyWrkItemTrxPk, cltStrgyWorkItemTrxPk);

        return (CLT_STRGY_WRK_ITEM_TRXTMsg) S21FastTBLAccessor.findByKeyForUpdate(inMsg);
    }

    /**
     * getDsBillToCustForUpdate
     * @param billToCustPk BigDecimal
     * @return BILL_TO_CUSTTMsg
     */
    private BILL_TO_CUSTTMsg getBillToCustForUpdate(BigDecimal billToCustPk) {
        BILL_TO_CUSTTMsg inMsg = new BILL_TO_CUSTTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.billToCustPk, billToCustPk);
        return (BILL_TO_CUSTTMsg) S21FastTBLAccessor.findByKeyForUpdate(inMsg);
    }

    /**
     * get S21SsmExecutionParameter
     * @return S21SsmExecutionParameter
     */
    private S21SsmExecutionParameter getExecPrm() {
        S21SsmExecutionParameter execPrm = new S21SsmExecutionParameter();
        execPrm.setFetchSize(FETCH_SIZE_MAX);
        execPrm.setFetchDirection(ResultSet.FETCH_FORWARD);
        execPrm.setResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE);
        execPrm.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return execPrm;
    }

    /**
     * startWorkflow
     * @param cMsg
     * @param 
     * @throws S21NwfException 
     */
    private void startWorkflow(List<AR_TRX_BALTMsg> dtlList, BillToData billToData) {

        String wfId  = getWorkflowId(NFDB006001Constant.CONST_KEY_VLD_APVL_WF_ID);
        String wfKey  = rqstPk.toString();
        if (isWorkflowStarted(wfId, wfKey)) {
            return;
        }

        // Business Token Object
        NFZC500001TokenObject tokenBiz = new NFZC500001TokenObject();

        // Calc Summary
        BigDecimal summaryWriteOffAmt = BigDecimal.ZERO;

        for (AR_TRX_BALTMsg rec : dtlList) {

            NFZC500001TokenObjectLine line = new NFZC500001TokenObjectLine();

            // Sum of remaining balance
            summaryWriteOffAmt = summaryWriteOffAmt.add(rec.dealRmngBalGrsAmt.getValue());

            // Line Data
            line.setBillToCustCd(rec.billToCustCd.getValue());
            line.setCpoOrdNum(rec.cpoOrdNum.getValue());
            line.setArTrxNum(rec.arTrxNum.getValue());
            line.setArTrxTpCd(rec.arTrxTpCd.getValue());
            line.setArTrxBalPk(rec.arTrxBalPk.getValue());
            line.setPk(rqstPk);
            tokenBiz.addLine(line);
        }

        //START 2018/03/29 J.Kim [QC#21721,MOD]
        /// Get CLT_PTFO PK
        // BigDecimal pk = nullVal(billToData.getCltPtfoPkBill(), billToData.getCltPtfoPkAcct());
        String userId = billToData.getCltPsnCdAcct();
        // END 2018/03/29 J.Kim [QC#21721,MOD]

        // Set Condition Data
        tokenBiz.setBizId(BUSINESS_ID);
        tokenBiz.setCondNum1(summaryWriteOffAmt);
        // START 2018/03/13 J.Kim [QC#21721,MOD]
        // tokenBiz.setCondNum2(pk);
        // // START 2018/03/13 J.Kim [QC#24769,ADD]
        // tokenBiz.setCltPtfoPk(pk);
        // // END 2018/03/13 J.Kim [QC#24769,ADD]
        tokenBiz.setCondStr1(userId);
        tokenBiz.setPsnNum(userId);
        // END 2018/03/13 J.Kim [QC#21721,MOD]

        // Set Attribute
        tokenBiz.setAttribute1Lbl("Request No");
        // START 2017/02/20 [QC#16526 ,MOD]
        // tokenBiz.setAttribute1(rqstPk.toString());
        tokenBiz.setAttribute1(this.wrtOffRqstGrpId);
        // START 2017/02/20 [QC#16526 ,MOD]

        tokenBiz.setAttribute2Lbl("Account No");
        tokenBiz.setAttribute2(billToData.getCltAcctCd());
        tokenBiz.setAttribute3Lbl("Request Amt");
        // START 2016/11/07 J.Kim [QC#15712,MOD]
        // tokenBiz.setAttribute3(summaryWriteOffAmt.toPlainString());
        tokenBiz.setAttribute3(formatAmount(summaryWriteOffAmt));
        // END 2016/11/07 J.Kim [QC#15712,MOD]
        tokenBiz.setAttribute4Lbl("Request Type");
        tokenBiz.setAttribute4("Complete Strategy");

        //--- start comment out. no need to move to online screen from WF screen.
        // Set Biz Screen ID
        // tokenBiz.setBizScreenId("NFDL0090");
        // Set Biz Screen Parameters
        // tokenBiz.setBizScreenParams(cMsg.dsAcctNum_H1);
        //--- end

        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfContext context = null;
        S21NwfProcess process = null;

        try {
            context = factory.getContex();
            if (context instanceof S21NwfBatchContext) {
                ((S21NwfBatchContext) context).setUserID(nullVal(billToData.getCltPsnCdBill(), billToData.getCltPsnCdAcct()));
            }

            context.setProcessFactory(S21NwfUtilProcessFactory.getInstance());

            process = context.newProcess(wfId);
            process.setDocumentId(wfKey);
            process.setGroup(wfKey);

        } catch (S21NwfException e) {
            // System Error
            // START 2016/11/08 E.Kameishi [QC#15747, MOD]
            EZDMessageInfo msgInfo = e.getMessageInfo();
            throw new S21AbendException(msgInfo.getCode(), msgInfo.getParameter());
            //throw new S21AbendException(NFDM0003E, new String[] {e.getMessage()});
            // END 2016/11/08 E.Kameishi [QC#15747, MOD]
        }

        S21NwfToken token = process.getToken();

        // START 2016/09/09 K.Kojima [QC#14038,ADD]
        token.setAutoDelegateUser(nullVal(billToData.getCltPsnCdBill(), billToData.getCltPsnCdAcct()));
        // END 2016/09/09 K.Kojima [QC#14038,ADD]

        token.setTokenObj(tokenBiz);

        try {
            // Start Workflow
            token.start();
            this.normCnt++; // ADD 2016/11/22 [QC#15747]

        } catch (S21NwfBizException e) {
            // START 2016/11/18 T.Murai [QC#15747, MOD]
            // // START 2016/11/08 E.Kameishi [QC#15747, MOD]
            // EZDMessageInfo msgInfo = e.getMessageInfo();
            // throw new S21AbendException(msgInfo.getCode(), msgInfo.getParameter());
            // //throw new S21AbendException(NFDM0003E, new String[] {e.getMessage()});
            // // END 2016/11/08 E.Kameishi [QC#15747, MOD]

            // START 2018/06/21 E.Kameishi [QC#25560, ADD]
            EZDMessageInfo msgInfo = e.getMessageInfo();
            AR_WRT_OFF_RQSTTMsg arWrtOffRqstTMsg = new AR_WRT_OFF_RQSTTMsg();
            setValue(arWrtOffRqstTMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(arWrtOffRqstTMsg.arWrtOffRqstPk, rqstPk);
            arWrtOffRqstTMsg = (AR_WRT_OFF_RQSTTMsg) S21FastTBLAccessor.findByKeyForUpdate(arWrtOffRqstTMsg);

            setValue(arWrtOffRqstTMsg.procStsCd, PROC_STS.ERROR);
            setValue(arWrtOffRqstTMsg.wrtOffErrMsgTxt, msgInfo.getMessage());
            S21FastTBLAccessor.update(arWrtOffRqstTMsg);
            // END 2018/06/21 E.Kameishi [QC#25560, ADD]

            sendMail(billToData.getBillToCustCd(), billToData.getCltAcctCd());
            this.errCnt++;
            // END   2016/11/18 T.Murai [QC#15747, MOD]


        } catch (S21NwfException e) {
            // System Error Logic
            // START 2016/11/08 E.Kameishi [QC#15747, MOD]
            EZDMessageInfo msgInfo = e.getMessageInfo();
            throw new S21AbendException(msgInfo.getCode(), msgInfo.getParameter());
            //throw new S21AbendException(NFDM0003E, new String[] {e.getMessage()});
            // END 2016/11/08 E.Kameishi [QC#15747, MOD]0
        }
    }

    // START 2016/11/07 J.Kim [QC#15712,ADD]
    private String formatAmount(BigDecimal amt) {
        if (!ZYPCommonFunc.hasValue(amt)) {
            return null;
        }
        NumberFormat df = NumberFormat.getCurrencyInstance(Locale.US);
        return df.format(amt);
    }
    // END 2016/11/07 J.Kim [QC#15712,ADD]

    private String getWorkflowId(String constKey) {

        String wfId = ZYPCodeDataUtil.getVarCharConstValue(constKey, this.glblCmpyCd);

        if (wfId == null) {
            throw new S21AbendException("[Error]Not Found 'VAR_CHAR_CONST' : varCharConstNm=" + constKey);
        }

        return wfId;
    }

    private boolean isWorkflowStarted(String wfId, String wfKey) {

        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfContext context = null;

        try {
            context = factory.getContex();

            List<S21NwfProcess>  processes = context.getProcess(wfId, wfKey);
            for (S21NwfProcess p : processes) {
                if (p.isActive()) {
                    return true;
                }
            }
        } catch (S21NwfSystemException e) {
            // System Error
            throw new S21AbendException(NFDB006001Constant.NFDM0008E);
        }

        return false;
    }

    // START 2016/11/18 [QC#15747, ADD]
    /**
     *<pre>
     * Send Mail
     * @param billToCode String
     * @param acctNum String
     *</pre>
     */
    public void sendMail(String billToCode, String acctNum) {

        // 1. Get From Address
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

        S21MailAddress from = null;
        if (!addrFromList.isEmpty()) {
            from = addrFromList.get(0);
        }

        // 2. Get To Address
        S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_TO);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();
        if (addrToList.isEmpty()) {
            throw new S21AbendException(NFBM0217E, new String[] {"TO", MAIL_GROUP_ID_TO, });
        }

        // 4. Create Subject and Body
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TMPL_ID);
        if (template == null) {
            throw new S21AbendException(NFBM0184E, new String[] {MAIL_TMPL_ID });
        }
        template.setTemplateParameter(MAIL_TMPL_KEY_BILL_TO, billToCode);
        template.setTemplateParameter(MAIL_TMPL_KEY_ACCT_NUM, acctNum);

        // 5. Call Mail API
        S21Mail mail = new S21Mail(glblCmpyCd);
        mail.setFromAddress(from);
        mail.setToAddressList(addrToList);
        mail.setMailTemplate(template);
        mail.postMail();
    }
    // END   2016/11/18 [QC#15747, ADD]

}
