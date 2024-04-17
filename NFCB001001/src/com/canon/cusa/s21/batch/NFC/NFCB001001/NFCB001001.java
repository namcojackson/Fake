/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NFC.NFCB001001;

import static com.canon.cusa.s21.batch.NFC.NFCB001001.constant.NFCB001001Constant.BATCH_ID;
import static com.canon.cusa.s21.batch.NFC.NFCB001001.constant.NFCB001001Constant.CALC_VALUE;
import static com.canon.cusa.s21.batch.NFC.NFCB001001.constant.NFCB001001Constant.CASH_DISC_TERM_CD_ZYB;
import static com.canon.cusa.s21.batch.NFC.NFCB001001.constant.NFCB001001Constant.CNT_STR_DAY_FIRST_01;
import static com.canon.cusa.s21.batch.NFC.NFCB001001.constant.NFCB001001Constant.FETCH_SIZE;
import static com.canon.cusa.s21.batch.NFC.NFCB001001.constant.NFCB001001Constant.HYPHEN;
import static com.canon.cusa.s21.batch.NFC.NFCB001001.constant.NFCB001001Constant.MAX_COMMIT_NUMBER;
import static com.canon.cusa.s21.batch.NFC.NFCB001001.constant.NFCB001001Constant.MSG_STR_APPLY_GRP_KEY;
import static com.canon.cusa.s21.batch.NFC.NFCB001001.constant.NFCB001001Constant.MSG_STR_AR_APPLY_INTFC_WRK;
import static com.canon.cusa.s21.batch.NFC.NFCB001001.constant.NFCB001001Constant.MSG_STR_AR_CASH_DISC_SCHD;
import static com.canon.cusa.s21.batch.NFC.NFCB001001.constant.NFCB001001Constant.MSG_STR_AR_CCY_CNTL;
import static com.canon.cusa.s21.batch.NFC.NFCB001001.constant.NFCB001001Constant.MSG_STR_AR_TRX_BAL;
import static com.canon.cusa.s21.batch.NFC.NFCB001001.constant.NFCB001001Constant.MSG_STR_DEAL_SQ_NUM;
import static com.canon.cusa.s21.batch.NFC.NFCB001001.constant.NFCB001001Constant.MSG_STR_INV_SEQ_PK;
import static com.canon.cusa.s21.batch.NFC.NFCB001001.constant.NFCB001001Constant.MSG_STR_INV_TP_CD;
import static com.canon.cusa.s21.batch.NFC.NFCB001001.constant.NFCB001001Constant.MSG_STR_ISTL_PMT_TERM;
import static com.canon.cusa.s21.batch.NFC.NFCB001001.constant.NFCB001001Constant.MSG_STR_NFZC3010;
import static com.canon.cusa.s21.batch.NFC.NFCB001001.constant.NFCB001001Constant.MSG_STR_USER_ID;
import static com.canon.cusa.s21.batch.NFC.NFCB001001.constant.NFCB001001Constant.NFCM0522E;
import static com.canon.cusa.s21.batch.NFC.NFCB001001.constant.NFCB001001Constant.NFCM0531E;
import static com.canon.cusa.s21.batch.NFC.NFCB001001.constant.NFCB001001Constant.NFCM0532E;
import static com.canon.cusa.s21.batch.NFC.NFCB001001.constant.NFCB001001Constant.NFCM0576E;
import static com.canon.cusa.s21.batch.NFC.NFCB001001.constant.NFCB001001Constant.NFCM0602E;
import static com.canon.cusa.s21.batch.NFC.NFCB001001.constant.NFCB001001Constant.PRCS_STS_CD_VALUE;
import static com.canon.cusa.s21.batch.NFC.NFCB001001.constant.NFCB001001Constant.PROGRAM_ID;
import static com.canon.cusa.s21.batch.NFC.NFCB001001.constant.NFCB001001Constant.ROUND_METH_CD_D;
import static com.canon.cusa.s21.batch.NFC.NFCB001001.constant.NFCB001001Constant.ROUND_METH_CD_O;
import static com.canon.cusa.s21.batch.NFC.NFCB001001.constant.NFCB001001Constant.ROUND_METH_CD_U;
import static com.canon.cusa.s21.batch.NFC.NFCB001001.constant.NFCB001001Constant.RTN_CD_AR_CCY_CNTL_ERROR;
import static com.canon.cusa.s21.batch.NFC.NFCB001001.constant.NFCB001001Constant.RTN_CD_INS_ERROR;
import static com.canon.cusa.s21.batch.NFC.NFCB001001.constant.NFCB001001Constant.RTN_CD_INV_SEQ_PK_ERROR;
import static com.canon.cusa.s21.batch.NFC.NFCB001001.constant.NFCB001001Constant.RTN_CD_NORMAL;
import static com.canon.cusa.s21.batch.NFC.NFCB001001.constant.NFCB001001Constant.RTN_CD_PARTS_ERROR;
import static com.canon.cusa.s21.batch.NFC.NFCB001001.constant.NFCB001001Constant.RTN_CD_SKIP;
import static com.canon.cusa.s21.batch.NFC.NFCB001001.constant.NFCB001001Constant.RTN_CD_TP_CD_ERROR;
import static com.canon.cusa.s21.batch.NFC.NFCB001001.constant.NFCB001001Constant.TWO_LENGTH;
import static com.canon.cusa.s21.batch.NFC.NFCB001001.constant.NFCB001001Constant.ZERO;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.AR_ACCT_DTTMsg;
import business.db.AR_APPLY_INTFC_WRKTMsg;
import business.db.AR_CASH_DISC_SCHDTMsg;
import business.db.AR_CCY_CTRLTMsg;
import business.db.AR_GRACE_PERTMsg;
import business.db.AR_RCPTTMsg;
import business.db.AR_TRX_BALTMsg;
import business.db.AR_TRX_BALTMsgArray;
import business.db.CFS_INV_INTFCTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.INVTMsg;
import business.db.INV_PRMO_INFOTMsg;
import business.db.INV_PRMO_INFOTMsgArray;
import business.db.ISTL_PMT_TERMTMsg;
import business.db.ISTL_PMT_TERMTMsgArray;
import business.db.PMT_TERMTMsg;
import business.parts.NFZC301001PMsg;

import com.canon.cusa.s21.api.NFC.NFZC301001.NFZC301001;
import com.canon.cusa.s21.common.NFX.NFXC306001.NFCNumbering;
import com.canon.cusa.s21.common.NFX.NFXC306001.NFXC3060Bean;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCCmnMethod;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCConst;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCDbConst;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_LINE_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 * Create Transaction Balance From S21OM Invoice Data
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/30/2017   Hitachi         K.Kasai         Update          N/A
 * 2017/07/04   Hitachi         J.Kim           Update          QC#19640
 * 2017/07/21   Hitachi         J.Kim           Update          QC#20084
 * 2018/02/06   Hitachi         E.Kameishi      Update          QC#23018
 * 2018/05/21   Hitachi         E.Kameishi      Update          QC#21841
 * 2018/08/14   CITS            K.Ogino         Update          QC#27133
 * 
 * </pre>
 */
public class NFCB001001 extends S21BatchMain {

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Global Company code */
    private String glblCmpyCd = null;

    /** batProcDate */
    private String batProcDate = null;

    // START 2017/07/21 J.Kim [QC#20084,ADD]
    /** Account Date */
    private String acctDate = null;
    // END 2017/07/21 J.Kim [QC#20084,ADD]

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SSM Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Commit Count */
    private int commitCount = 0;

    /** AR_INV_DTL_SUB_NUM_SET */
    private String arInvDtlSubNumSet = null;

    /** stdCcyCd */
    private String stdCcyCd = null;

    /** arIpoSysSrcCd */
    private String arIpoSysSrcCd = null;

    /** arDiscDelayDt */
    private String arDiscDelayDt = null;

    /** userId */
    private String userId = null;

    /** Processing Count */
    private int procCount = 0;

    /** err Count */
    private int errCnt = 0;

    /** commit TrxCnt*/
    private int commitTrxCnt = 0;

    /** Add QC#27133. Cache Map for CFS_INV_INTFC */
    private S21LRUMap<String, Integer> cfsInvIntfcCacheMap = new S21LRUMap<String, Integer>();

    @Override
    protected void initRoutine() {

        // Initialize
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.termCd = TERM_CD.NORMAL_END;

        // Get GLBL_CMPY_CD
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NFCM0522E);
        }

        // Get Standard CCY Code
        this.stdCcyCd = getCcyCd();

        // Get Batch Proc Date.
        this.batProcDate = ZYPDateUtil.getBatProcDate(this.glblCmpyCd);
        // START 2017/07/21 J.Kim [QC#20084,ADD]
        getArAcctDt();
        if (!ZYPCommonFunc.hasValue(this.acctDate)) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NFCM0522E, new String[] {"Account Date" });
        }
        // END 2017/07/21 J.Kim [QC#20084,ADD]

        // Get commit count
        this.commitCount = getCommitCount();
        if (this.commitCount <= 0 || this.commitCount >= MAX_COMMIT_NUMBER) {
            this.commitCount = MAX_COMMIT_NUMBER;
        }

        // Get INV_DTL_DUB_NUM_SET
        this.arInvDtlSubNumSet = ZYPCodeDataUtil.getVarCharConstValue(NFCConst.AR_INV_DTL_SUB_NUM_SET, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.arInvDtlSubNumSet)) {
            this.arInvDtlSubNumSet = "000";
        }

        // Get AR_IPO_SYS_SRC_CD
        this.arIpoSysSrcCd = ZYPCodeDataUtil.getVarCharConstValue(NFCConst.AR_IPO_SYS_SRC_CD, this.glblCmpyCd);

        // Get AR_DISC_DELAY_DT
        this.arDiscDelayDt = ZYPCodeDataUtil.getVarCharConstValue(NFCConst.CST_VAR_CHAR_CONST_NAME_AR_DISC_DELAY_AOT, this.glblCmpyCd);

        // get User Id
        this.userId = ZYPCodeDataUtil.getVarCharConstValue(NFCConst.CST_VAR_CHAR_CONST_NAME_AR_BAT_USR_ID, this.glblCmpyCd);
        if (S21StringUtil.isEmpty(userId)) {
            throw new S21AbendException(NFCM0531E, MSG_STR_USER_ID);
        }
    }

    @Override
    protected void mainRoutine() {

        try {
            execute();
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
    }

    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.procCount - this.errCnt, this.errCnt, this.procCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {

        new NFCB001001().executeBatch(PROGRAM_ID);

    }

    /**
     * 
     */
    private void execute() throws SQLException {

        PreparedStatement stmtSelect = null;
        ResultSet rs = null;
        S21SsmExecutionParameter ssmParam = new S21SsmExecutionParameter();
        ssmParam.setFetchSize(FETCH_SIZE);
        ssmParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        AR_TRX_BALTMsg arTrxBalTMsg = new AR_TRX_BALTMsg();
        int rtnCd;

        Map<String, Object> inParam = setParam();

        try {

            // get OM Invoice
            stmtSelect = this.ssmLLClient.createPreparedStatement("getOmInv", inParam, ssmParam);
            rs = stmtSelect.executeQuery();

            // process per Invoice Header
            while (rs.next()) {
                arTrxBalTMsg.clear();
                // in case of regular invoice(Not split invoice)
                if (ZYPConstant.FLG_OFF_N.equals(rs.getString(NFCDbConst.ISTL_PMT_TERM_FLG))) {

                    // insert AR_TRX_BAL
                    rtnCd = insertArTrxBal(rs, rs.getString(NFCDbConst.INV_NUM), arTrxBalTMsg, null);
                    if (rtnCd == RTN_CD_PARTS_ERROR) {
                        throw new S21AbendException(NFCM0531E, MSG_STR_INV_SEQ_PK);
                    } else if (rtnCd == RTN_CD_TP_CD_ERROR) {
                        throw new S21AbendException(NFCM0532E, MSG_STR_INV_TP_CD);
                    } else if (rtnCd == RTN_CD_INS_ERROR) {
                        throw new S21AbendException(NFCM0532E, MSG_STR_AR_TRX_BAL);
                    }
                    this.procCount++;

                    // insert AR_CASH_DISC_SCHD
                    rtnCd = insertArCashDiscSchd(rs, arTrxBalTMsg);
                    switch (rtnCd) {
                        case RTN_CD_NORMAL:
                            break;
                        case RTN_CD_SKIP:
                            break;
                        case RTN_CD_AR_CCY_CNTL_ERROR:
                            throw new S21AbendException(NFCM0532E, MSG_STR_AR_CCY_CNTL);
                        default:
                            throw new S21AbendException(NFCM0532E, MSG_STR_AR_CASH_DISC_SCHD);
                    }

                    // insert AR_APPLY_INTFC_WRK
                    rtnCd = insertArApplyIntfcWrk(rs, arTrxBalTMsg);
                    switch (rtnCd) {
                        case RTN_CD_NORMAL:
                            break;
                        case RTN_CD_INV_SEQ_PK_ERROR:
                            throw new S21AbendException(NFCM0531E, MSG_STR_INV_SEQ_PK);
                        case RTN_CD_INS_ERROR:
                            throw new S21AbendException(NFCM0532E, MSG_STR_AR_APPLY_INTFC_WRK);
                        default:
                            throw new S21AbendException(NFCM0531E, MSG_STR_AR_APPLY_INTFC_WRK);
                    }

                // in case of split invoice
                } else {

                    INVTMsg calcInvTMsg = new INVTMsg();

                    String istlInvNum = "";
                    String istlArCustRefNum = "";
                    String istlPmtStartDt = "";
                    String istlNetDueDt = "";
                    String istlPmtTermDtlNum = "";
                    BigDecimal istlPmtTermAot = BigDecimal.ZERO;
                    BigDecimal istlPmtTermPct = BigDecimal.ZERO;

                    BigDecimal sumInvTotDealNetAmt  = BigDecimal.ZERO;
                    BigDecimal sumInvTotDealSlsAmt  = BigDecimal.ZERO;
                    BigDecimal sumInvTotDealFrtAmt  = BigDecimal.ZERO;
                    BigDecimal sumInvTotDealTaxAmt  = BigDecimal.ZERO;
                    BigDecimal sumInvTotDealDiscAmt = BigDecimal.ZERO;
                    BigDecimal sumInvTotDealInsAmt  = BigDecimal.ZERO;
                    BigDecimal sumInvTotFuncNetAmt  = BigDecimal.ZERO;
                    BigDecimal sumInvTotFuncSlsAmt  = BigDecimal.ZERO;
                    BigDecimal sumInvTotFuncFrtAmt  = BigDecimal.ZERO;
                    BigDecimal sumInvTotFuncTaxAmt  = BigDecimal.ZERO;
                    BigDecimal sumInvTotFuncDiscAmt = BigDecimal.ZERO;
                    BigDecimal sumInvTotFuncInsAmt  = BigDecimal.ZERO;

                    // get AR_CCY_CTRL(deal)
                    AR_CCY_CTRLTMsg dealArCcyCtrlTMsg = getArCcyCntl(rs.getString("DEAL_CCY_CD"));
                    if (dealArCcyCtrlTMsg == null) {
                        throw new S21AbendException(NFCM0531E, MSG_STR_AR_CCY_CNTL);
                    }
                    String dealRoundMethCd = dealArCcyCtrlTMsg.roundMethCd.getValue();
                    int dealAftDeclPntDigitNum = dealArCcyCtrlTMsg.aftDeclPntDigitNum.getValueInt();

                    // get AR_CCY_CTRL(func)
                    AR_CCY_CTRLTMsg funcArCcyCtrlTMsg = null;
                    String funcRoundMethCd = dealRoundMethCd;
                    int funcAftDeclPntDigitNum = dealAftDeclPntDigitNum;
                    if (!this.stdCcyCd.equals(rs.getString("DEAL_CCY_CD"))) {
                        funcArCcyCtrlTMsg = getArCcyCntl(rs.getString("DEAL_CCY_CD"));
                        if (funcArCcyCtrlTMsg == null) {
                            throw new S21AbendException(NFCM0531E, MSG_STR_AR_CCY_CNTL);
                        }
                        funcRoundMethCd = funcArCcyCtrlTMsg.roundMethCd.getValue();
                        funcAftDeclPntDigitNum = funcArCcyCtrlTMsg.aftDeclPntDigitNum.getValueInt();
                    }

                    // get ISTL_PMT_TERM
                    ISTL_PMT_TERMTMsgArray istlPmtTermArray = getIstlPmtTermArray(rs.getString("PMT_TERM_CD"));
                    if (istlPmtTermArray == null) {
                        throw new S21AbendException(NFCM0531E, MSG_STR_ISTL_PMT_TERM);
                    }

                    // process per ISTL_PMT_TERM
                    ISTL_PMT_TERMTMsg istlPmtTermTMsg;
                    for (int idx = 0; idx < istlPmtTermArray.getValidCount(); idx++) {
                        calcInvTMsg = new INVTMsg();
                        istlPmtTermTMsg = (ISTL_PMT_TERMTMsg) istlPmtTermArray.get(idx);
                        istlPmtTermDtlNum = istlPmtTermTMsg.istlPmtTermDtlNum.getValue();
                        istlPmtTermAot = istlPmtTermTMsg.istlPmtTermAot.getValue();
                        istlPmtTermPct = istlPmtTermTMsg.istlPmtTermPct.getValue();

                        int cntNum = idx + 1;

                        istlInvNum = rs.getString(NFCDbConst.INV_NUM) + formatDtlNum(istlPmtTermDtlNum);
                        istlArCustRefNum = rs.getString(NFCDbConst.INV_NUM) + " " + cntNum + "/" + istlPmtTermArray.getValidCount();

                        if (S21StringUtil.isEmpty(rs.getString(NFCDbConst.PMT_TERM_START_DT))) {
                            istlPmtStartDt = rs.getString(NFCDbConst.INV_DT);
                        } else {
                            istlPmtStartDt = rs.getString(NFCDbConst.PMT_TERM_START_DT);
                        }
                        istlNetDueDt = ZYPDateUtil.addDays(istlPmtStartDt, istlPmtTermAot.intValue());

                        setValue(calcInvTMsg.invNum, istlInvNum);
                        setValue(calcInvTMsg.netDueDt, istlNetDueDt);

                        if (idx == istlPmtTermArray.getValidCount() - 1) {
                            setValue(calcInvTMsg.invTotDealNetAmt, rs.getBigDecimal(NFCDbConst.INV_TOT_DEAL_NET_AMT).subtract(sumInvTotDealNetAmt));
                            setValue(calcInvTMsg.invTotDealSlsAmt, rs.getBigDecimal(NFCDbConst.INV_TOT_DEAL_SLS_AMT).subtract(sumInvTotDealSlsAmt));
                            setValue(calcInvTMsg.invTotDealFrtAmt, rs.getBigDecimal(NFCDbConst.INV_TOT_DEAL_FRT_AMT).subtract(sumInvTotDealFrtAmt));
                            setValue(calcInvTMsg.invTotDealTaxAmt, rs.getBigDecimal(NFCDbConst.INV_TOT_DEAL_TAX_AMT).subtract(sumInvTotDealTaxAmt));
                            setValue(calcInvTMsg.invTotDealDiscAmt, rs.getBigDecimal(NFCDbConst.INV_TOT_DEAL_DISC_AMT).subtract(sumInvTotDealDiscAmt));
                            setValue(calcInvTMsg.invTotDealInsAmt, rs.getBigDecimal(NFCDbConst.INV_TOT_DEAL_INS_AMT).subtract(sumInvTotDealInsAmt));
                            setValue(calcInvTMsg.invTotFuncNetAmt, rs.getBigDecimal(NFCDbConst.INV_TOT_FUNC_NET_AMT).subtract(sumInvTotFuncNetAmt));
                            setValue(calcInvTMsg.invTotFuncSlsAmt, rs.getBigDecimal(NFCDbConst.INV_TOT_FUNC_SLS_AMT).subtract(sumInvTotFuncSlsAmt));
                            setValue(calcInvTMsg.invTotFuncFrtAmt, rs.getBigDecimal(NFCDbConst.INV_TOT_FUNC_FRT_AMT).subtract(sumInvTotFuncFrtAmt));
                            setValue(calcInvTMsg.invTotFuncTaxAmt, rs.getBigDecimal(NFCDbConst.INV_TOT_FUNC_TAX_AMT).subtract(sumInvTotFuncTaxAmt));
                            setValue(calcInvTMsg.invTotFuncDiscAmt, rs.getBigDecimal(NFCDbConst.INV_TOT_FUNC_DISC_AMT).subtract(sumInvTotFuncDiscAmt));
                            setValue(calcInvTMsg.invTotFuncInsAmt, rs.getBigDecimal(NFCDbConst.INV_TOT_FUNC_INS_AMT).subtract(sumInvTotFuncInsAmt));
                        } else {
                            setValue(calcInvTMsg.invTotDealNetAmt, ccyCalc(rs.getBigDecimal(NFCDbConst.INV_TOT_DEAL_NET_AMT), istlPmtTermPct, dealRoundMethCd, dealAftDeclPntDigitNum));
                            setValue(calcInvTMsg.invTotDealSlsAmt, ccyCalc(rs.getBigDecimal(NFCDbConst.INV_TOT_DEAL_SLS_AMT), istlPmtTermPct, dealRoundMethCd, dealAftDeclPntDigitNum));
                            setValue(calcInvTMsg.invTotDealFrtAmt, ccyCalc(rs.getBigDecimal(NFCDbConst.INV_TOT_DEAL_FRT_AMT), istlPmtTermPct, dealRoundMethCd, dealAftDeclPntDigitNum));
                            setValue(calcInvTMsg.invTotDealTaxAmt, ccyCalc(rs.getBigDecimal(NFCDbConst.INV_TOT_DEAL_TAX_AMT), istlPmtTermPct, dealRoundMethCd, dealAftDeclPntDigitNum));
                            setValue(calcInvTMsg.invTotDealDiscAmt, ccyCalc(rs.getBigDecimal(NFCDbConst.INV_TOT_DEAL_DISC_AMT), istlPmtTermPct, dealRoundMethCd, dealAftDeclPntDigitNum));
                            setValue(calcInvTMsg.invTotDealInsAmt, ccyCalc(rs.getBigDecimal(NFCDbConst.INV_TOT_DEAL_INS_AMT), istlPmtTermPct, dealRoundMethCd, dealAftDeclPntDigitNum));
                            setValue(calcInvTMsg.invTotFuncNetAmt, ccyCalc(rs.getBigDecimal(NFCDbConst.INV_TOT_FUNC_NET_AMT), istlPmtTermPct, funcRoundMethCd, funcAftDeclPntDigitNum));
                            setValue(calcInvTMsg.invTotFuncSlsAmt, ccyCalc(rs.getBigDecimal(NFCDbConst.INV_TOT_FUNC_SLS_AMT), istlPmtTermPct, funcRoundMethCd, funcAftDeclPntDigitNum));
                            setValue(calcInvTMsg.invTotFuncFrtAmt, ccyCalc(rs.getBigDecimal(NFCDbConst.INV_TOT_FUNC_FRT_AMT), istlPmtTermPct, funcRoundMethCd, funcAftDeclPntDigitNum));
                            setValue(calcInvTMsg.invTotFuncTaxAmt, ccyCalc(rs.getBigDecimal(NFCDbConst.INV_TOT_FUNC_TAX_AMT), istlPmtTermPct, funcRoundMethCd, funcAftDeclPntDigitNum));
                            setValue(calcInvTMsg.invTotFuncDiscAmt, ccyCalc(rs.getBigDecimal(NFCDbConst.INV_TOT_FUNC_DISC_AMT), istlPmtTermPct, funcRoundMethCd, funcAftDeclPntDigitNum));
                            setValue(calcInvTMsg.invTotFuncInsAmt, ccyCalc(rs.getBigDecimal(NFCDbConst.INV_TOT_FUNC_INS_AMT), istlPmtTermPct, funcRoundMethCd, funcAftDeclPntDigitNum));

                            sumInvTotDealNetAmt  = addCalc(sumInvTotDealNetAmt, calcInvTMsg.invTotDealNetAmt.getValue());
                            sumInvTotDealSlsAmt  = addCalc(sumInvTotDealSlsAmt, calcInvTMsg.invTotDealSlsAmt.getValue());
                            sumInvTotDealFrtAmt  = addCalc(sumInvTotDealFrtAmt, calcInvTMsg.invTotDealFrtAmt.getValue());
                            sumInvTotDealTaxAmt  = addCalc(sumInvTotDealTaxAmt, calcInvTMsg.invTotDealTaxAmt.getValue());
                            sumInvTotDealDiscAmt = addCalc(sumInvTotDealDiscAmt, calcInvTMsg.invTotDealDiscAmt.getValue());
                            sumInvTotDealInsAmt  = addCalc(sumInvTotDealInsAmt, calcInvTMsg.invTotDealInsAmt.getValue());
                            sumInvTotFuncNetAmt  = addCalc(sumInvTotFuncNetAmt, calcInvTMsg.invTotFuncNetAmt.getValue());
                            sumInvTotFuncSlsAmt  = addCalc(sumInvTotFuncSlsAmt, calcInvTMsg.invTotFuncSlsAmt.getValue());
                            sumInvTotFuncFrtAmt  = addCalc(sumInvTotFuncFrtAmt, calcInvTMsg.invTotFuncFrtAmt.getValue());
                            sumInvTotFuncTaxAmt  = addCalc(sumInvTotFuncTaxAmt, calcInvTMsg.invTotFuncTaxAmt.getValue());
                            sumInvTotFuncDiscAmt = addCalc(sumInvTotFuncDiscAmt, calcInvTMsg.invTotFuncDiscAmt.getValue());
                            sumInvTotFuncInsAmt  = addCalc(sumInvTotFuncInsAmt, calcInvTMsg.invTotFuncInsAmt.getValue());
                        }

                        // insert AR_TRX_BAL
                        rtnCd = insertArTrxBal(rs, istlArCustRefNum, arTrxBalTMsg, calcInvTMsg);
                        if (rtnCd == RTN_CD_INS_ERROR) {
                            throw new S21AbendException(NFCM0532E, MSG_STR_AR_TRX_BAL);
                        }
                        this.procCount++;

                        // insert AR_APPLY_INTFC_WRK
                        rtnCd = insertArApplyIntfcWrk(rs, arTrxBalTMsg);
                        switch (rtnCd) {
                            case RTN_CD_NORMAL:
                                break;
                            case RTN_CD_INV_SEQ_PK_ERROR:
                                throw new S21AbendException(NFCM0531E, MSG_STR_INV_SEQ_PK);
                            case RTN_CD_INS_ERROR:
                                throw new S21AbendException(NFCM0532E, MSG_STR_AR_APPLY_INTFC_WRK);
                            default:
                                throw new S21AbendException(NFCM0531E, MSG_STR_AR_APPLY_INTFC_WRK);
                        }
                    }
                }
            }
            // Cash Application
            creationReceiptData();

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
    }

    private void creationReceiptData() throws SQLException {

        PreparedStatement stmtSelect = null;
        ResultSet rs = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

            // getArApplayIntfcWrk for cash apply
            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put("glblCmpyCd", this.glblCmpyCd);
            queryParam.put("bizAppId", BATCH_ID);
            queryParam.put("procStsCd", PRCS_STS_CD_VALUE);
            stmtSelect = this.ssmLLClient.createPreparedStatement("getArApplayIntfcWrk", queryParam, execParam);
            rs = stmtSelect.executeQuery();

            while (rs.next()) {

                // get applyGrpKey
                String applyGrpKey = rs.getString(NFCDbConst.APPLY_GRP_KEY);
                if (S21StringUtil.isEmpty(applyGrpKey)) {
                    this.errCnt++;
                    S21InfoLogOutput.println(NFCM0602E, MSG_STR_APPLY_GRP_KEY);
                    continue;
                }

                // get dealSqNum
                String dealSqNum = rs.getString(NFCDbConst.DEAL_SQ_NUM);
                if (S21StringUtil.isEmpty(dealSqNum)) {
                    this.errCnt++;
                    S21InfoLogOutput.println(NFCM0602E, MSG_STR_DEAL_SQ_NUM);
                    continue;
                }

                // set NFZC3010(Cash Application API)
                NFZC301001PMsg nfzc301001PMsg = new NFZC301001PMsg();
                nfzc301001PMsg.glblCmpyCd.setValue(this.glblCmpyCd);
                nfzc301001PMsg.applyGrpKey.setValue(applyGrpKey);
                nfzc301001PMsg.dealSqNum.setValue(dealSqNum);
                nfzc301001PMsg.batDt.setValue(this.batProcDate);

                // execute NFZC3010(Cash Application API)
                NFZC301001 nfzc301001 = new NFZC301001();
                nfzc301001.execute(nfzc301001PMsg, ONBATCH_TYPE.BATCH);

                String returnCd = nfzc301001PMsg.getReturnCode();
                if (NFCConst.CST_NFZC301001_RTN_CD_EXCLERR.equals(returnCd) || NFCConst.CST_NFZC301001_RTN_CD_ETCERR.equals(returnCd)) {
                    /* */
                    new S21AbendException(NFCM0576E, MSG_STR_NFZC3010);
                }

                if (++this.commitTrxCnt >= this.commitCount) {
                    commit();
                    this.commitTrxCnt = 0;
                }
            }

            if (this.commitTrxCnt > 0) {
                commit();
            }
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
    }

    private BigDecimal ccyCalc(BigDecimal beforeCalcAmt, BigDecimal calcPct, String roundMethCd, int aftDeclPntDigitNum) {

        if ((beforeCalcAmt == null) || (calcPct == null)) {
            return BigDecimal.ZERO;
        }

        BigDecimal wkcalcAmt = beforeCalcAmt.multiply(calcPct).divide(CALC_VALUE);

        BigDecimal calcAmt = BigDecimal.ZERO;
        if (ROUND_METH_CD_U.equals(roundMethCd)) {
            /* */
            calcAmt = wkcalcAmt.setScale(aftDeclPntDigitNum, BigDecimal.ROUND_UP);
        } else if (ROUND_METH_CD_O.equals(roundMethCd)) {
            /* */
            calcAmt = wkcalcAmt.setScale(aftDeclPntDigitNum, BigDecimal.ROUND_HALF_UP);
        } else if (ROUND_METH_CD_D.equals(roundMethCd)) {
            /* */
            calcAmt = wkcalcAmt.setScale(aftDeclPntDigitNum, BigDecimal.ROUND_DOWN);
        }

        return calcAmt;
    }

    private String formatDtlNum(String convDtlNum) {

        if (convDtlNum.length() == TWO_LENGTH) {
            return HYPHEN + convDtlNum;
        } else {
            return HYPHEN + ZERO + convDtlNum;
        }

    }

    private int insertArApplyIntfcWrk(ResultSet rs, AR_TRX_BALTMsg arTrxBalTMsg) throws SQLException {

        AR_APPLY_INTFC_WRKTMsg arApplyIntfcWrk = new AR_APPLY_INTFC_WRKTMsg();

        // check isAdvance
        AR_RCPTTMsg arRcptTMsg = isAdvance(rs.getString("ADV_RCPT_NUM"), arTrxBalTMsg);
        boolean isAdvance = false;
        if (arRcptTMsg != null) {
            isAdvance = true;
        }

        NFCNumbering afcNumbering = new NFCNumbering();
        NFXC3060Bean afcNumberingRtn = afcNumbering.getNumber(NFCConst.CST_SEQ_ID_APPLY_GRP_SUB, null, 0);
        if (!NFCConst.CST_RTN_CD_NORM.equals(afcNumberingRtn.getRtrnNo())) {
            return RTN_CD_INV_SEQ_PK_ERROR;
        }

        String oraSqNoStr = afcNumberingRtn.getOraSqNo().toString();

        String arTrxTpCd = convTypeCode(rs.getString("INV_TP_CD"));

        // get AR_TRX_BAL from origInvNum
        AR_TRX_BALTMsg arTrxBalTMsgWk = null;
        if (INV_TP.CREDIT_MEMO.equals(rs.getString("INV_TP_CD")) && S21StringUtil.isNotEmpty(rs.getString("ORIG_INV_NUM"))) {
            arTrxBalTMsgWk = getArTrxBal(rs.getString("ORIG_INV_NUM"));
        }

        arTrxBalTMsgWk = null;
        /* */
        setValue(arApplyIntfcWrk.invNum, arTrxBalTMsg.arTrxNum);
        setValue(arApplyIntfcWrk.arTrxTpCd, arTrxTpCd);
        setValue(arApplyIntfcWrk.invTrxBalPk, arTrxBalTMsg.arTrxBalPk);
        setValue(arApplyIntfcWrk.invTrxBalLastUpdTs, arTrxBalTMsg.ezUpTime);
        setValue(arApplyIntfcWrk.invTrxBalTz, arTrxBalTMsg.ezUpTimeZone);
        setValue(arApplyIntfcWrk.crNum, NFCConst.CST_DB_INIT_VAL_STR);
        setValue(arApplyIntfcWrk.crTrxBalPk, NFCConst.CST_DB_INIT_VAL_NUM);
        setValue(arApplyIntfcWrk.crTrxBalLastUpdTs, NFCConst.CST_DB_INIT_VAL_STR);
        setValue(arApplyIntfcWrk.crTrxBalTz, NFCConst.CST_DB_INIT_VAL_STR);
        setValue(arApplyIntfcWrk.dealApplyAmt, NFCConst.CST_DB_INIT_VAL_NUM);

        setValue(arApplyIntfcWrk.glblCmpyCd, this.glblCmpyCd);
        setValue(arApplyIntfcWrk.applyGrpKey, BATCH_ID + oraSqNoStr);
        setValue(arApplyIntfcWrk.applyGrpSubPk, BigDecimal.ONE);
        setValue(arApplyIntfcWrk.bizAppId, BATCH_ID);
        setValue(arApplyIntfcWrk.procTpCd, "1");
        setValue(arApplyIntfcWrk.dealSqNum, "1");
        setValue(arApplyIntfcWrk.dealSqDtlNum, "1");
        setValue(arApplyIntfcWrk.procStsCd, "0");
        setValue(arApplyIntfcWrk.usrId, this.userId);
        setValue(arApplyIntfcWrk.rcptDtlNum, NFCConst.CST_DB_INIT_VAL_STR);
        setValue(arApplyIntfcWrk.rcvFuncId, NFCConst.CST_DB_INIT_VAL_STR);
        setValue(arApplyIntfcWrk.rcptInProcSqPk, NFCConst.CST_DB_INIT_VAL_NUM);
        setValue(arApplyIntfcWrk.rcvHdrNum, NFCConst.CST_DB_INIT_VAL_STR);
        setValue(arApplyIntfcWrk.rcvDtlNum, NFCConst.CST_DB_INIT_VAL_STR);
        setValue(arApplyIntfcWrk.rcptGlDt, NFCConst.CST_DB_INIT_VAL_STR);
        setValue(arApplyIntfcWrk.payerCustCd, NFCConst.CST_DB_INIT_VAL_STR);
        setValue(arApplyIntfcWrk.grpInvFlg, NFCConst.CST_FLAG_OFF);
        setValue(arApplyIntfcWrk.dealCcyCd, arTrxBalTMsg.dealCcyCd);
        setValue(arApplyIntfcWrk.cashAppGlDt, rs.getString("INV_DT"));
        setValue(arApplyIntfcWrk.cashDiscPct, NFCConst.CST_DB_INIT_VAL_NUM);
        setValue(arApplyIntfcWrk.dealCashDiscAmt, NFCConst.CST_DB_INIT_VAL_NUM);
        setValue(arApplyIntfcWrk.dealOnAcctCashAmt, NFCConst.CST_DB_INIT_VAL_NUM);
        setValue(arApplyIntfcWrk.arAdjNum, NFCConst.CST_DB_INIT_VAL_STR);
        setValue(arApplyIntfcWrk.arAdjTrxTpCd, NFCConst.CST_DB_INIT_VAL_STR);
        setValue(arApplyIntfcWrk.arAdjTpCd, NFCConst.CST_DB_INIT_VAL_STR);
        setValue(arApplyIntfcWrk.dealApplyAdjAmt, NFCConst.CST_DB_INIT_VAL_NUM);
        setValue(arApplyIntfcWrk.dealApplyAdjRsvdAmt, NFCConst.CST_DB_INIT_VAL_NUM);
        setValue(arApplyIntfcWrk.adjCmntTxt, NFCConst.CST_DB_INIT_VAL_STR);
        setValue(arApplyIntfcWrk.apvlPsnCd, NFCConst.CST_DB_INIT_VAL_STR);
        setValue(arApplyIntfcWrk.tocCd, NFCConst.CST_DB_INIT_VAL_STR);
        setValue(arApplyIntfcWrk.coaProdCd, NFCConst.CST_DB_INIT_VAL_STR);
        setValue(arApplyIntfcWrk.arCustRefNum, NFCConst.CST_DB_INIT_VAL_STR);

        isAdvance = Boolean.FALSE;

        if (isAdvance) {

            // The AR_TRX_BAL data that corresponds to
            // AR_INV_INFO.ADV_RCPT_NUM is set.
            arTrxBalTMsgWk = getArTrxBal(rs.getString("ADV_RCPT_NUM"));
            if (arTrxBalTMsgWk != null) {
                setValue(arApplyIntfcWrk.rcptTrxBalPk, arTrxBalTMsgWk.arTrxBalPk);
                setValue(arApplyIntfcWrk.rcptTrxBalLastUpdTs, arTrxBalTMsgWk.ezUpTime);
                setValue(arApplyIntfcWrk.rcptTrxBalTz, arTrxBalTMsgWk.ezUpTimeZone);
            } else {
                setValue(arApplyIntfcWrk.rcptTrxBalPk, NFCConst.CST_DB_INIT_VAL_NUM);
                setValue(arApplyIntfcWrk.rcptTrxBalLastUpdTs, NFCConst.CST_DB_INIT_VAL_STR);
                setValue(arApplyIntfcWrk.rcptTrxBalTz, NFCConst.CST_DB_INIT_VAL_STR);
            }
            setValue(arApplyIntfcWrk.rcptNum, NFCCmnMethod.convertDbString(rs.getString("ADV_RCPT_NUM")));
            setValue(arApplyIntfcWrk.rcptHdrLastUpdTs, arRcptTMsg.ezUpTime);
            setValue(arApplyIntfcWrk.rcptHdrTz, arRcptTMsg.ezUpTimeZone);
            setValue(arApplyIntfcWrk.dealApplyAmt, arTrxBalTMsg.dealRmngBalGrsAmt);
        } else {
            setValue(arApplyIntfcWrk.rcptNum, NFCConst.CST_DB_INIT_VAL_STR);
            setValue(arApplyIntfcWrk.rcptTrxBalPk, NFCConst.CST_DB_INIT_VAL_NUM);
            setValue(arApplyIntfcWrk.rcptHdrLastUpdTs, NFCConst.CST_DB_INIT_VAL_STR);
            setValue(arApplyIntfcWrk.rcptHdrTz, NFCConst.CST_DB_INIT_VAL_STR);
            setValue(arApplyIntfcWrk.rcptTrxBalLastUpdTs, NFCConst.CST_DB_INIT_VAL_STR);
            setValue(arApplyIntfcWrk.rcptTrxBalTz, NFCConst.CST_DB_INIT_VAL_STR);
        }

        // insert AR_APPLY_INTFC_WRK. Mod QC#27133
        S21FastTBLAccessor.insert(arApplyIntfcWrk);

        if (NFCConst.CST_EZ_RETURN_CODE_CPLT.equals(arApplyIntfcWrk.getReturnCode())) {
            return RTN_CD_NORMAL;
        } else {
            return RTN_CD_INS_ERROR;
        }
    }

    private AR_RCPTTMsg isAdvance(String adcvRcptNum, AR_TRX_BALTMsg arTrxBalTMsg) {

        // check adcRcptNum
        if (S21StringUtil.isEmpty(adcvRcptNum)) {
            return null;
        }

        // get AR_RCPT
        AR_RCPTTMsg inTMsg = new AR_RCPTTMsg();
        setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inTMsg.rcptNum, adcvRcptNum);
        AR_RCPTTMsg arRcptTMsg = (AR_RCPTTMsg) EZDTBLAccessor.findByKey(inTMsg);

        if (arRcptTMsg == null) {
            return null;
        }

        BigDecimal dealRcptRmngBalAmt = arRcptTMsg.dealRcptRmngBalAmt.getValue();
        BigDecimal dealOrigGrsAmt = arTrxBalTMsg.dealOrigGrsAmt.getValue();
        int cmpRsult = dealRcptRmngBalAmt.compareTo(dealOrigGrsAmt);
        if (cmpRsult < 0) {
            return null;
        }

        return arRcptTMsg;
    }

    private int insertArCashDiscSchd(ResultSet rs, AR_TRX_BALTMsg arTrxBalTMsg) throws SQLException {

        /* get PMT_TERM_AOT */
        BigDecimal pmtTermAot = getPmtTermAot(rs.getString("PMT_TERM_CD"));
        if (pmtTermAot == null) {
            return RTN_CD_SKIP;
        }

        /* get DISC_DUE_GRACE_PER_DAYS_AOT */
        BigDecimal discDueGracePerDaysAot = getDiscDueGracePerDaysAot(rs.getString("BILL_TO_CUST_CD"));

        /* get AR_CCY_CTRL */
        AR_CCY_CTRLTMsg arCcyCtrlTMsg = getArCcyCntl(rs.getString("DEAL_CCY_CD"));
        if (arCcyCtrlTMsg == null) {
            return RTN_CD_AR_CCY_CNTL_ERROR;
        }

        /* insert AR_CASH_DISC_SCHD */
        int retStatus = regCashDiscSchd(pmtTermAot, discDueGracePerDaysAot, rs, arTrxBalTMsg, arCcyCtrlTMsg);
        switch (retStatus) {
            case RTN_CD_NORMAL:
                break;
            case RTN_CD_SKIP:
            case RTN_CD_INS_ERROR:
            default:
                return retStatus;
        }

        return RTN_CD_NORMAL;
    }

    private int regCashDiscSchd(BigDecimal pmtTermAot, BigDecimal discDueGracePerDaysAot, ResultSet rs, AR_TRX_BALTMsg arTrxBalTMsg, AR_CCY_CTRLTMsg arCcyCtrlTMsg) throws SQLException {

        // Check CASH_DISC_TERM_CD is null
        if (!ZYPCommonFunc.hasValue(rs.getString("CASH_DISC_TERM_CD"))) {
            return RTN_CD_SKIP;
        }

        /* set AR_CASH_DISC_SCHD */
        AR_CASH_DISC_SCHDTMsg arCashDiscSchdTMsg = new AR_CASH_DISC_SCHDTMsg();

        setValue(arCashDiscSchdTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(arCashDiscSchdTMsg.arTrxBalPk, arTrxBalTMsg.arTrxBalPk);
        setValue(arCashDiscSchdTMsg.dealCcyCd, rs.getString("DEAL_CCY_CD"));

        Map<String, String> queryParam = new HashMap<String, String>();
        List<Map<String, Object>> cashDiscTermList = new ArrayList<Map<String, Object>>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("cashDiscTermCd", rs.getString("CASH_DISC_TERM_CD"));
        queryParam.put("netDueDt", rs.getString("NET_DUE_DT"));
        // if CASH_DISC_TERM_CD is not ZYB
        if (!CASH_DISC_TERM_CD_ZYB.equals(rs.getString("CASH_DISC_TERM_CD"))) {
            queryParam.put("pmtTermAot", pmtTermAot.toString());
        }
        cashDiscTermList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getCashDiscTerm", queryParam);

        /* get Cash Disc Term data count*/
        int selectCount = 0;
        if (cashDiscTermList != null) {
            selectCount = cashDiscTermList.size();
        }
        if (selectCount == 0) {
            return RTN_CD_SKIP;
        }

        String cashDiscRangeThruDt = "";
        String cashDiscRangeFromDt = "";
        String extnCashDiscRangeThruDt = "";
        String extnCashDiscRangeFromDt = "";
        String wkInvDt = rs.getString("NET_DUE_DT");
        BigDecimal wkAmount = BigDecimal.ZERO;
        int arCashDiscSchdSqNum = 0;

        /* 0% record is made. */
        if (((BigDecimal) cashDiscTermList.get(0).get(NFCDbConst.CASH_DISC_FROM_AOT)).compareTo(BigDecimal.ZERO) != 0) {

            arCashDiscSchdSqNum = 1;
            wkAmount = ((BigDecimal) cashDiscTermList.get(0).get(NFCDbConst.CASH_DISC_FROM_AOT)).subtract(BigDecimal.ONE);
            cashDiscRangeFromDt = ZYPDateUtil.addDays(wkInvDt, wkAmount.negate().intValue());

            setValue(arCashDiscSchdTMsg.arCashDiscSchdSqNum, Integer.toString(arCashDiscSchdSqNum));
            setValue(arCashDiscSchdTMsg.cashDiscRngFromDt, cashDiscRangeFromDt);
            setValue(arCashDiscSchdTMsg.cashDiscRngThruDt, wkInvDt);
            setValue(arCashDiscSchdTMsg.cashDiscPct, BigDecimal.ZERO);
            setValue(arCashDiscSchdTMsg.dealCashDiscAmt, BigDecimal.ZERO);

            extnCashDiscRangeFromDt = ZYPDateUtil.addDays(cashDiscRangeFromDt, discDueGracePerDaysAot.intValue());
            setValue(arCashDiscSchdTMsg.extnCashDiscRngFromDt, extnCashDiscRangeFromDt);
            setValue(arCashDiscSchdTMsg.extnCashDiscRngThruDt, wkInvDt);
            setValue(arCashDiscSchdTMsg.extnCashDiscPct, BigDecimal.ZERO);
            setValue(arCashDiscSchdTMsg.dealExtnCashDiscAmt, BigDecimal.ZERO);
            // QC#27133
            S21FastTBLAccessor.insert(arCashDiscSchdTMsg);

            if (!NFCConst.CST_EZ_RETURN_CODE_CPLT.equals(arCashDiscSchdTMsg.getReturnCode())) {
                return RTN_CD_INS_ERROR;
            }
            selectCount++;
            wkAmount = ((BigDecimal) cashDiscTermList.get(0).get(NFCDbConst.CASH_DISC_FROM_AOT));

        }

        Map<String, Object> cashDiscTermMap = null;
        for (int i = 0; i < cashDiscTermList.size(); i++) {

            cashDiscTermMap = cashDiscTermList.get(i);
            arCashDiscSchdSqNum++;

            cashDiscRangeThruDt = ZYPDateUtil.addDays(wkInvDt, wkAmount.negate().intValue());

            if ((selectCount > 1) && arCashDiscSchdSqNum > 1) {
                extnCashDiscRangeThruDt = ZYPDateUtil.addDays(cashDiscRangeThruDt, discDueGracePerDaysAot.intValue());
            } else {
                extnCashDiscRangeThruDt = cashDiscRangeThruDt;
            }

            BigDecimal stractVal = subtractCalc((BigDecimal) cashDiscTermMap.get(NFCDbConst.CASH_DISC_THRU_AOT), (BigDecimal) cashDiscTermMap.get(NFCDbConst.CASH_DISC_FROM_AOT));
            wkAmount = addCalc(wkAmount, stractVal);

            cashDiscRangeFromDt = ZYPDateUtil.addDays(wkInvDt, wkAmount.negate().intValue());

            if ((selectCount > 1) && i < cashDiscTermList.size() - 1) {
                extnCashDiscRangeFromDt = ZYPDateUtil.addDays(cashDiscRangeFromDt, discDueGracePerDaysAot.intValue());
            } else {
                extnCashDiscRangeFromDt = cashDiscRangeFromDt;
            }

            wkAmount = incrementValue(wkAmount);

            BigDecimal cashDiscAmt = cashDiscAmtCalc(arTrxBalTMsg.dealNetSlsAmt.getValue(), (BigDecimal) cashDiscTermMap.get(NFCDbConst.CASH_DISC_PCT), arCcyCtrlTMsg.roundMethCd.getValue(), arCcyCtrlTMsg.aftDeclPntDigitNum.getValueInt());

            setValue(arCashDiscSchdTMsg.cashDiscPct, (BigDecimal) cashDiscTermMap.get(NFCDbConst.CASH_DISC_PCT));
            setValue(arCashDiscSchdTMsg.extnCashDiscPct, (BigDecimal) cashDiscTermMap.get(NFCDbConst.CASH_DISC_PCT));
            setValue(arCashDiscSchdTMsg.cashDiscRngFromDt, cashDiscRangeFromDt);
            setValue(arCashDiscSchdTMsg.cashDiscRngThruDt, cashDiscRangeThruDt);
            setValue(arCashDiscSchdTMsg.extnCashDiscRngFromDt, extnCashDiscRangeFromDt);
            setValue(arCashDiscSchdTMsg.extnCashDiscRngThruDt, extnCashDiscRangeThruDt);
            setValue(arCashDiscSchdTMsg.dealCashDiscAmt, cashDiscAmt);
            setValue(arCashDiscSchdTMsg.dealExtnCashDiscAmt, cashDiscAmt);
            setValue(arCashDiscSchdTMsg.arCashDiscSchdSqNum, Integer.toString(arCashDiscSchdSqNum));

            // insert AR_CASH_DISC_SCHD. Mod QC#27133
            S21FastTBLAccessor.insert(arCashDiscSchdTMsg);

            if (!NFCConst.CST_EZ_RETURN_CODE_CPLT.equals(arCashDiscSchdTMsg.getReturnCode())) {
                return RTN_CD_INS_ERROR;
            }
        }
        return RTN_CD_NORMAL;
    }

    private BigDecimal cashDiscAmtCalc(BigDecimal dealNetSlsAmt, BigDecimal cashDiscPct, String roundMethCd, int aftDeclPntDigitNum) {

        if ((dealNetSlsAmt == null) || (cashDiscPct == null)) {
            return BigDecimal.ZERO;
        }

        BigDecimal wkCashDiscAmt = dealNetSlsAmt.multiply(cashDiscPct).divide(CALC_VALUE);

        BigDecimal cashDiscAmt = BigDecimal.ZERO;
        if (ROUND_METH_CD_U.equals(roundMethCd)) {
            /* */
            cashDiscAmt = wkCashDiscAmt.setScale(aftDeclPntDigitNum, BigDecimal.ROUND_UP);
        } else if (ROUND_METH_CD_O.equals(roundMethCd)) {
            /* */
            cashDiscAmt = wkCashDiscAmt.setScale(aftDeclPntDigitNum, BigDecimal.ROUND_HALF_UP);
        } else if (ROUND_METH_CD_D.equals(roundMethCd)) {
            /* */
            cashDiscAmt = wkCashDiscAmt.setScale(aftDeclPntDigitNum, BigDecimal.ROUND_DOWN);
        }

        return cashDiscAmt;
    }

    private BigDecimal incrementValue(BigDecimal number) {
        if (number == null) {
            return BigDecimal.ONE;
        }

        return number.add(BigDecimal.ONE);
    }

    private BigDecimal addCalc(BigDecimal value, BigDecimal addValue) {

        /* */
        if (value == null) {
            return addValue;
        }

        if (addValue == null) {
            return value;
        }

        return value.add(addValue);
    }

    private BigDecimal subtractCalc(BigDecimal value, BigDecimal subtractValue) {

        if (subtractValue == null) {
            return value;
        }

        BigDecimal val;
        if (value == null) {
            val = BigDecimal.ZERO;
        } else {
            val = value;
        }

        return val.subtract(subtractValue);
    }

    private AR_CCY_CTRLTMsg getArCcyCntl(String dealCcyCd) {

        AR_CCY_CTRLTMsg inTMsg = new AR_CCY_CTRLTMsg();
        setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inTMsg.ccyCd, dealCcyCd);
        // QC#27133
        return (AR_CCY_CTRLTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
    }

    private BigDecimal getDiscDueGracePerDaysAot(String billToCustCd) {

        BigDecimal arDiscDelayDtBd = new BigDecimal(Integer.parseInt(this.arDiscDelayDt));
        if (S21StringUtil.isEmpty(billToCustCd)) {
            return arDiscDelayDtBd;
        }

        AR_GRACE_PERTMsg inTMsg = new AR_GRACE_PERTMsg();
        setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inTMsg.arGracePerCd, billToCustCd);
        // QC#27133
        AR_GRACE_PERTMsg arGracePer = (AR_GRACE_PERTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (arGracePer == null) {
            return arDiscDelayDtBd;
        }

        return arGracePer.discDueGracePerDaysAot.getValue();
    }

    private BigDecimal getPmtTermAot(String pmtTermCd) {
        BigDecimal pmtTermAot = null;

        if (S21StringUtil.isEmpty(pmtTermCd)) {
            return pmtTermAot;
        }

        PMT_TERMTMsg inTMsg = new PMT_TERMTMsg();
        setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inTMsg.pmtTermCd, pmtTermCd);
        // QC#27133
        PMT_TERMTMsg arLgcyTermTMsg = (PMT_TERMTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (arLgcyTermTMsg == null) {
            return null;
        }

        return arLgcyTermTMsg.pmtTermAot.getValue();
    }

    private Map<String, Object> setParam() {
        Map<String, Object> inParam = new HashMap<String, Object>();
        inParam.put("glblCmpyCd", this.glblCmpyCd);
        inParam.put("fnlzInvFlg", ZYPConstant.FLG_ON_Y);
        String[] invTpList = new String[] {INV_TP.INVOICE, INV_TP.CREDIT_MEMO};
        inParam.put("invTpList", invTpList);
        inParam.put("arInvDtlSubNumSet", this.arInvDtlSubNumSet);
        // START 2017/07/21 J.Kim [QC#20084,MOD]
        // inParam.put("batProcMthFirstDay", this.batProcDate.substring(0, LEN_YYYYMM) + CNT_STR_DAY_FIRST_01);
        inParam.put("batProcMthFirstDay", this.acctDate + CNT_STR_DAY_FIRST_01);
        // END 2017/07/21 J.Kim [QC#20084,MOD]
        inParam.put("openArFlg", ZYPConstant.FLG_ON_Y);
        // START 2018/03/27 J.Kim [QC#23571,ADD]
        inParam.put("fleet", DS_CONTR_CATG.FLEET);
        inParam.put("aggregate", DS_CONTR_CATG.AGGREGATE);
        // END 2018/03/27 J.Kim [QC#23571,ADD]
        // START 2018/05/21 E.Kameishi [QC#21841,ADD]
        inParam.put("invLineCatgItem", INV_LINE_CATG.ITEM);
        // END 2018/05/21 E.Kameishi [QC#21841,ADD]
        return inParam;
    }

    private int insertArTrxBal(ResultSet rs, String arCustRefNum, AR_TRX_BALTMsg arTrxBalTMsg, INVTMsg splitInvTMsg) throws SQLException {

        /* get new AR_TRX_BAL_SQ*/
        NFCNumbering afcNumbering = new NFCNumbering();
        NFXC3060Bean afcNumberingRtn = afcNumbering.getNumber(NFCConst.CST_SEQ_ID_AR_TRX_BAL, null, 0);
        if (!NFCConst.CST_RTN_CD_NORM.equals(afcNumberingRtn.getRtrnNo())) {
            return RTN_CD_PARTS_ERROR;
        }

        /* convert arTrxTpCd from invTpCd */
        String arTrxTpCd = convTypeCode(rs.getString("INV_TP_CD"));
        if (S21StringUtil.isEmpty(arTrxTpCd)) {
            return RTN_CD_TP_CD_ERROR;
        }

        /* get Sum of dealDiscAmt */
        BigDecimal dealDiscAmtSum = getDealDiscAmtSum(rs.getString(NFCDbConst.INV_NUM));

        /* get Sum of funcDiscAmt */
        BigDecimal funcDiscAmtSum = dealDiscAmtSum;
        if (!this.stdCcyCd.equals(rs.getString("DEAL_CCY_CD"))) {
            funcDiscAmtSum = getFuncDiscAmtSum(rs.getString(NFCDbConst.INV_NUM));
        }

        /* set AR_TRX_BAL */
        setArTrxBal(rs, arCustRefNum, afcNumberingRtn.getOraSqNo(), arTrxTpCd, dealDiscAmtSum, funcDiscAmtSum, arTrxBalTMsg, splitInvTMsg);

        /* insert AR_TRX_BAL. Mod QC#27133 */
        S21FastTBLAccessor.insert(arTrxBalTMsg);

        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(arTrxBalTMsg.getReturnCode())) {
            return RTN_CD_INS_ERROR;
        }

        return RTN_CD_NORMAL;
    }

    private void setArTrxBal(ResultSet rs, String arCustRefNum, BigDecimal arTrxBalPk, String arTrxTpCd, BigDecimal dealDiscAmtSum, BigDecimal funcDiscAmtSum, AR_TRX_BALTMsg arTrxBalTMsg, INVTMsg splitInvTMsg) throws SQLException {

        setValue(arTrxBalTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(arTrxBalTMsg.arTrxBalPk, arTrxBalPk);

        // Not split invoice
        if (splitInvTMsg == null) {
            setValue(arTrxBalTMsg.arTrxNum, rs.getString(NFCDbConst.INV_NUM));
            setValue(arTrxBalTMsg.dealOrigGrsAmt, rs.getBigDecimal("INV_TOT_DEAL_NET_AMT"));
            setValue(arTrxBalTMsg.dealRmngBalGrsAmt, rs.getBigDecimal("INV_TOT_DEAL_NET_AMT"));
            setValue(arTrxBalTMsg.dealNetSlsAmt, addCalc(rs.getBigDecimal("INV_TOT_DEAL_SLS_AMT"), rs.getBigDecimal("INV_TOT_DEAL_DISC_AMT")));
            setValue(arTrxBalTMsg.dealFrtAmt, rs.getBigDecimal("INV_TOT_DEAL_FRT_AMT"));
            setValue(arTrxBalTMsg.dealTaxAmt, addCalc(rs.getBigDecimal("INV_TOT_DEAL_TAX_AMT"), rs.getBigDecimal("INV_TOT_DEAL_INS_AMT")));
            setValue(arTrxBalTMsg.dealInvDiscAmt, rs.getBigDecimal("INV_TOT_DEAL_DISC_AMT"));
            setValue(arTrxBalTMsg.funcOrigGrsAmt, rs.getBigDecimal("INV_TOT_FUNC_NET_AMT"));
            setValue(arTrxBalTMsg.funcRmngBalGrsAmt, rs.getBigDecimal("INV_TOT_FUNC_NET_AMT"));
            setValue(arTrxBalTMsg.funcNetSlsAmt, addCalc(rs.getBigDecimal("INV_TOT_FUNC_SLS_AMT"), rs.getBigDecimal("INV_TOT_FUNC_DISC_AMT")));
            setValue(arTrxBalTMsg.funcFrtAmt, rs.getBigDecimal("INV_TOT_FUNC_FRT_AMT"));
            setValue(arTrxBalTMsg.funcTaxAmt, addCalc(rs.getBigDecimal("INV_TOT_FUNC_TAX_AMT"), rs.getBigDecimal("INV_TOT_FUNC_INS_AMT")));
            setValue(arTrxBalTMsg.funcInvDiscAmt, rs.getBigDecimal("INV_TOT_FUNC_DISC_AMT"));
            setValue(arTrxBalTMsg.invDueDt, rs.getString("NET_DUE_DT"));
            setValue(arTrxBalTMsg.origInvNum, rs.getString(NFCDbConst.ORIG_INV_NUM));
        // split invoice
        } else {
            setValue(arTrxBalTMsg.arTrxNum, splitInvTMsg.invNum);
            setValue(arTrxBalTMsg.dealOrigGrsAmt, splitInvTMsg.invTotDealNetAmt);
            setValue(arTrxBalTMsg.dealRmngBalGrsAmt, splitInvTMsg.invTotDealNetAmt);
            setValue(arTrxBalTMsg.dealNetSlsAmt, addCalc(splitInvTMsg.invTotDealSlsAmt.getValue(), splitInvTMsg.invTotDealDiscAmt.getValue()));
            setValue(arTrxBalTMsg.dealFrtAmt, splitInvTMsg.invTotDealFrtAmt);
            setValue(arTrxBalTMsg.dealTaxAmt, addCalc(splitInvTMsg.invTotDealTaxAmt.getValue(), splitInvTMsg.invTotDealInsAmt.getValue()));
            setValue(arTrxBalTMsg.dealInvDiscAmt, splitInvTMsg.invTotDealDiscAmt);
            setValue(arTrxBalTMsg.funcOrigGrsAmt, splitInvTMsg.invTotFuncNetAmt);
            setValue(arTrxBalTMsg.funcRmngBalGrsAmt, splitInvTMsg.invTotFuncNetAmt);
            setValue(arTrxBalTMsg.funcNetSlsAmt, addCalc(splitInvTMsg.invTotFuncSlsAmt.getValue(), splitInvTMsg.invTotFuncDiscAmt.getValue()));
            setValue(arTrxBalTMsg.funcFrtAmt, splitInvTMsg.invTotDealFrtAmt);
            setValue(arTrxBalTMsg.funcTaxAmt, addCalc(splitInvTMsg.invTotFuncTaxAmt.getValue(), splitInvTMsg.invTotFuncInsAmt.getValue()));
            setValue(arTrxBalTMsg.funcInvDiscAmt, splitInvTMsg.invTotFuncDiscAmt);
            setValue(arTrxBalTMsg.invDueDt, splitInvTMsg.netDueDt);
            setValue(arTrxBalTMsg.origInvNum, rs.getString(NFCDbConst.INV_NUM));
        }

        // START 2017/07/04 J.Kim [QC#19640,ADD]
        if (AR_TRX_TP.CREDIT_MEMO.equals(arTrxTpCd)) {
            setValue(arTrxBalTMsg.dealOrigGrsAmt, arTrxBalTMsg.dealOrigGrsAmt.getValue().negate());
            setValue(arTrxBalTMsg.dealRmngBalGrsAmt, arTrxBalTMsg.dealRmngBalGrsAmt.getValue().negate());
            setValue(arTrxBalTMsg.dealNetSlsAmt, arTrxBalTMsg.dealNetSlsAmt.getValue().negate());
            setValue(arTrxBalTMsg.dealFrtAmt, arTrxBalTMsg.dealFrtAmt.getValue().negate());
            setValue(arTrxBalTMsg.dealTaxAmt, arTrxBalTMsg.dealTaxAmt.getValue().negate());
            setValue(arTrxBalTMsg.dealInvDiscAmt, arTrxBalTMsg.dealInvDiscAmt.getValue().negate());
            setValue(arTrxBalTMsg.funcOrigGrsAmt, arTrxBalTMsg.funcOrigGrsAmt.getValue().negate());
            setValue(arTrxBalTMsg.funcRmngBalGrsAmt, arTrxBalTMsg.funcRmngBalGrsAmt.getValue().negate());
            setValue(arTrxBalTMsg.funcNetSlsAmt, arTrxBalTMsg.funcNetSlsAmt.getValue().negate());
            setValue(arTrxBalTMsg.funcFrtAmt, arTrxBalTMsg.funcFrtAmt.getValue().negate());
            setValue(arTrxBalTMsg.funcTaxAmt, arTrxBalTMsg.funcTaxAmt.getValue().negate());
            setValue(arTrxBalTMsg.funcInvDiscAmt, arTrxBalTMsg.funcInvDiscAmt.getValue().negate());
        }
        // START 2017/07/04 J.Kim [QC#19640,ADD]

        setValue(arTrxBalTMsg.arTrxTpCd, arTrxTpCd);
        setValue(arTrxBalTMsg.dealCcyCd, rs.getString("DEAL_CCY_CD"));
        setValue(arTrxBalTMsg.dealApplyGrsAmt, NFCConst.CST_DB_INIT_VAL_NUM);
        setValue(arTrxBalTMsg.dealApplyCashDiscAmt, NFCConst.CST_DB_INIT_VAL_NUM);
        setValue(arTrxBalTMsg.dealApplyCrAmt, NFCConst.CST_DB_INIT_VAL_NUM);
        setValue(arTrxBalTMsg.dealApplyAdjAmt, NFCConst.CST_DB_INIT_VAL_NUM);
        setValue(arTrxBalTMsg.dealApplyAdjRsvdAmt, NFCConst.CST_DB_INIT_VAL_NUM);
        setValue(arTrxBalTMsg.dealPrmoDiscAmt, dealDiscAmtSum);
        setValue(arTrxBalTMsg.dealRcptVoidAmt, NFCConst.CST_DB_INIT_VAL_NUM);
        setValue(arTrxBalTMsg.exchRate, rs.getBigDecimal("ACTL_APPLY_EXCH_RATE"));
        setValue(arTrxBalTMsg.funcCcyCd, this.stdCcyCd);
        setValue(arTrxBalTMsg.funcApplyGrsAmt, NFCConst.CST_DB_INIT_VAL_NUM);
        setValue(arTrxBalTMsg.funcApplyCashDiscAmt, NFCConst.CST_DB_INIT_VAL_NUM);
        setValue(arTrxBalTMsg.funcApplyCrAmt, NFCConst.CST_DB_INIT_VAL_NUM);
        setValue(arTrxBalTMsg.funcApplyAdjAmt, NFCConst.CST_DB_INIT_VAL_NUM);
        setValue(arTrxBalTMsg.funcApplyAdjRsvdAmt, NFCConst.CST_DB_INIT_VAL_NUM);
        setValue(arTrxBalTMsg.funcRvalVarAmt, NFCConst.CST_DB_INIT_VAL_NUM);
        setValue(arTrxBalTMsg.funcPrmoDiscAmt, funcDiscAmtSum);
        setValue(arTrxBalTMsg.funcRcptVoidAmt, NFCConst.CST_DB_INIT_VAL_NUM);
        setValue(arTrxBalTMsg.pmtTermCashDiscCd, rs.getString("PMT_TERM_CASH_DISC_CD"));
        setValue(arTrxBalTMsg.pmtTermCd, rs.getString("PMT_TERM_CD"));
        setValue(arTrxBalTMsg.cashDiscTermCd, rs.getString("CASH_DISC_TERM_CD"));
        setValue(arTrxBalTMsg.cashDiscPct, NFCConst.CST_DB_INIT_VAL_NUM);
        setValue(arTrxBalTMsg.invPmtMethCd, rs.getString("INV_PMT_METH_CD"));
        setValue(arTrxBalTMsg.invPmtCondCd, rs.getString("INV_PMT_COND_CD"));
        setValue(arTrxBalTMsg.arCashApplyStsCd, NFCConst.CST_AR_CASH_APPLY_STS_CD_UNAPPLY);
        setValue(arTrxBalTMsg.arTrxDt, rs.getString("INV_DT"));
        // START 2017/07/21 J.Kim [QC#20084,MOD]
        // setValue(arTrxBalTMsg.glDt, this.batProcDate);
        setValue(arTrxBalTMsg.glDt, rs.getString("ACCT_DT"));
        // END 2017/07/21 J.Kim [QC#20084,MOD]
        setValue(arTrxBalTMsg.cashAppDt, this.batProcDate);
        setValue(arTrxBalTMsg.billToCustCd, rs.getString("BILL_TO_CUST_CD"));
        setValue(arTrxBalTMsg.sellToCustCd, rs.getString("SELL_TO_CUST_CD"));
        setValue(arTrxBalTMsg.payerCustCd, rs.getString("PAYER_CUST_CD"));
        setValue(arTrxBalTMsg.tocCd, rs.getString("SLS_REP_TOC_CD"));
        setValue(arTrxBalTMsg.coaProdCd, rs.getString("COA_PROD_CD"));
        setValue(arTrxBalTMsg.grpInvNum, rs.getString("GRP_INV_NUM"));
        setValue(arTrxBalTMsg.cpoOrdNum, rs.getString("CPO_ORD_NUM"));

        if (isIPOData(rs.getString("SYS_SRC_CD"))) {
            setValue(arTrxBalTMsg.custIssPoNum, rs.getString("ORIG_INV_NUM"));
        } else {
            setValue(arTrxBalTMsg.custIssPoNum, rs.getString("CUST_ISS_PO_NUM"));
        }
        setValue(arTrxBalTMsg.sysSrcCd, rs.getString("SYS_SRC_CD"));
        String srcSysDocNum = rs.getString("SRC_SYS_DOC_NUM");
        if (splitInvTMsg == null && ZYPCommonFunc.hasValue(srcSysDocNum)) {
            if (existsCfsInvIntfcData(srcSysDocNum)) {
                arCustRefNum = srcSysDocNum;
            }
        }
        setValue(arTrxBalTMsg.arCustRefNum, arCustRefNum);
        setValue(arTrxBalTMsg.exptFlg, ZYPConstant.FLG_OFF_N);
        // START 2018/02/06 E.Kameishi [QC#23018, DEL]
        //setValue(arTrxBalTMsg.arAutoPurgeOfsFlg, ZYPConstant.FLG_OFF_N);
        // END  2018/02/06 E.Kameishi [QC#23018, DEL]

        if (isIPOData(rs.getString("SYS_SRC_CD"))) {
            setValue(arTrxBalTMsg.upperCustIssPoNum, rs.getString("ORIG_INV_NUM"));
        } else {
            setValue(arTrxBalTMsg.upperCustIssPoNum, rs.getString("CUST_ISS_PO_NUM"));
        }
        setValue(arTrxBalTMsg.billToCustAcctCd, rs.getString("BILL_TO_CUST_ACCT_CD"));
        setValue(arTrxBalTMsg.srcSysDocNum, rs.getString("SRC_SYS_DOC_NUM"));

        setValue(arTrxBalTMsg.dsInvTpCd, rs.getString("DS_INV_TP_CD"));
        setValue(arTrxBalTMsg.slsRepTocCd, rs.getString("SLS_REP_TOC_CD"));
        setValue(arTrxBalTMsg.custCareTktNum, rs.getString("CUST_CARE_TKT_NUM"));
        setValue(arTrxBalTMsg.exptFlg, ZYPConstant.FLG_OFF_N);
        // START 2018/02/06 E.Kameishi [QC#23018, MOD]
        setValue(arTrxBalTMsg.arAutoPurgeOfsFlg, rs.getString("AR_AUTO_PURGE_OFS_FLG"));
        //setValue(arTrxBalTMsg.arAutoPurgeOfsFlg, ZYPConstant.FLG_OFF_N);
        // END 2018/02/06 E.Kameishi [QC#23018, MOD]
        setValue(arTrxBalTMsg.exptFlg, ZYPConstant.FLG_OFF_N);
        setValue(arTrxBalTMsg.crRebilRsnCatgCd, rs.getString("CR_REBIL_RSN_CATG_CD"));
        setValue(arTrxBalTMsg.arTrxBillFromDt, rs.getString("BLLG_PER_FROM_DT"));
        setValue(arTrxBalTMsg.arTrxBillThruDt, rs.getString("BLLG_PER_TO_DT"));

    }

    private String convTypeCode(String beforeCode) {
        String afterCode = null;

        /* */
        if (NFCConst.CST_INV_TP_CD_INVOICE.equals(beforeCode)) {
            afterCode = AR_TRX_TP.INVOICE;
        } else if (NFCConst.CST_INV_TP_CD_CREDITMEMO.equals(beforeCode)) {
            afterCode = AR_TRX_TP.CREDIT_MEMO;
        } else if (NFCConst.CST_INV_TP_CD_DEBITMEMO.equals(beforeCode)) {
            afterCode = AR_TRX_TP.DEBIT_MEMO;
        } else {
            afterCode = NFCConst.CST_DB_INIT_VAL_STR;
        }

        return afterCode;
    }

    private BigDecimal getDealDiscAmtSum(String invNum) {

        BigDecimal dealDiscAmtSum = BigDecimal.ZERO;

        INV_PRMO_INFOTMsg prmTMsg = new INV_PRMO_INFOTMsg();
        prmTMsg.setSQLID("001");
        prmTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        prmTMsg.setConditionValue("invNum01", invNum);
        INV_PRMO_INFOTMsgArray outTMsgArray = (INV_PRMO_INFOTMsgArray) EZDTBLAccessor.findByCondition(prmTMsg);

        for (int i = 0; i < outTMsgArray.getValidCount(); i++) {
            INV_PRMO_INFOTMsg outTMsg = outTMsgArray.no(i);
            dealDiscAmtSum = addCalc(dealDiscAmtSum, outTMsg.dealDiscAmt.getValue());
        }

        return dealDiscAmtSum;
    }

    private BigDecimal getFuncDiscAmtSum(String invNum) {

        BigDecimal funcDiscAmtSum = BigDecimal.ZERO;

        INV_PRMO_INFOTMsg prmTMsg = new INV_PRMO_INFOTMsg();
        prmTMsg.setSQLID("001");
        prmTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        prmTMsg.setConditionValue("invNum01", invNum);
        INV_PRMO_INFOTMsgArray outTMsgArray = (INV_PRMO_INFOTMsgArray) EZDTBLAccessor.findByCondition(prmTMsg);

        for (int i = 0; i < outTMsgArray.getValidCount(); i++) {
            INV_PRMO_INFOTMsg outTMsg = outTMsgArray.no(i);
            funcDiscAmtSum = addCalc(funcDiscAmtSum, outTMsg.funcDiscAmt.getValue());
        }

        return funcDiscAmtSum;
    }

    private String getCcyCd() {

        GLBL_CMPYTMsg inTMsg = new GLBL_CMPYTMsg();
        setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
        GLBL_CMPYTMsg glblCmpyT = (GLBL_CMPYTMsg) EZDTBLAccessor.findByKey(inTMsg);
        if (glblCmpyT != null) {
            return glblCmpyT.stdCcyCd.getValue();
        }
        return null;
    }

    private boolean isIPOData(String sysSrcCd) {

        if (this.arIpoSysSrcCd != null && this.arIpoSysSrcCd.equals(sysSrcCd)) {
            return true;
        }
        return false;
    }

    private boolean existsCfsInvIntfcData(String cfsInvNum) {

        // QC#27133
        Integer outTMsgCnt = this.cfsInvIntfcCacheMap.get(cfsInvNum);

        if (outTMsgCnt == null) {
            CFS_INV_INTFCTMsg prmTMsg = new CFS_INV_INTFCTMsg();
            prmTMsg.setSQLID("001");
            prmTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
            prmTMsg.setConditionValue("cfsLeasePblNum01", cfsInvNum);
            outTMsgCnt = EZDTBLAccessor.count(prmTMsg);

            cfsInvIntfcCacheMap.put(cfsInvNum, outTMsgCnt);

        }

        if (outTMsgCnt == 0) {
            return false;
        }
        return true;
    }

    private AR_TRX_BALTMsg getArTrxBal(String arTrxNum) {

        AR_TRX_BALTMsg prmTMsg = new AR_TRX_BALTMsg();
        prmTMsg.setSQLID("001");
        prmTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        prmTMsg.setConditionValue("arTrxNum01", arTrxNum);
        AR_TRX_BALTMsgArray outTMsgArray = (AR_TRX_BALTMsgArray) EZDTBLAccessor.findByCondition(prmTMsg);

        if (outTMsgArray.getValidCount() == 0) {
            return null;
        }
        return outTMsgArray.no(0);
    }

    private ISTL_PMT_TERMTMsgArray getIstlPmtTermArray(String istlPmtTermCd) {

        ISTL_PMT_TERMTMsg prmTMsg = new ISTL_PMT_TERMTMsg();
        prmTMsg.setSQLID("001");
        prmTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        prmTMsg.setConditionValue("istlPmtTermCd01", istlPmtTermCd);
        ISTL_PMT_TERMTMsgArray outTMsgArray = (ISTL_PMT_TERMTMsgArray) EZDTBLAccessor.findByCondition(prmTMsg);

        if (outTMsgArray.getValidCount() == 0) {
            return null;
        }
        return outTMsgArray;
    }

    // START 2017/07/21 J.Kim [QC#20084,ADD]
    /**
     * getArAcctDt
     */
    private void getArAcctDt() {

        String arSubSysId = ZYPCodeDataUtil.getVarCharConstValue("AR_SUB_SYS_ID", this.glblCmpyCd);

        AR_ACCT_DTTMsg inMsg = new AR_ACCT_DTTMsg();
        inMsg.glblCmpyCd.setValue(this.glblCmpyCd);
        inMsg.subSysCd.setValue(arSubSysId);
        inMsg.onlBatTpCd.setValue("2");

        AR_ACCT_DTTMsg outMsg = (AR_ACCT_DTTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (outMsg == null) {
            this.acctDate = "";
        } else {
            this.acctDate = outMsg.acctYrMth.getValue();
        }
    }
    // END 2017/07/21 J.Kim [QC#20084,ADD]
}
