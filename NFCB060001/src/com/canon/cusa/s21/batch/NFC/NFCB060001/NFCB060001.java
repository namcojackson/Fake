package com.canon.cusa.s21.batch.NFC.NFCB060001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.AR_RCPT_RCV_WRKTMsg;
import business.db.ECHK_RCPT_TRXTMsg;

import com.canon.cusa.s21.common.NFX.NFXC306001.NFCNumbering;
import com.canon.cusa.s21.common.NFX.NFXC306001.NFXC3060Bean;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCConst;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCDbConst;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CUST_ID_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ECHK_PMT_STS;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Auto Cash Apply for eCheck Payment
 *
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 2023/03/09   HITACHI         R.Takau         Create          QC#55645
 *</pre>
 */
public class NFCB060001 extends S21BatchMain implements ZYPConstant, NFCB0600Constant {

    /** Ssm Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** SQL Access parts */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Processing Count */
    private int totalRecordCnt = 0;

    /** normal Count */
    private int normalRecordCnt = 0;

    /** err Count */
    private int errorRecordCnt = 0;

    /** Termination Code */
    private TERM_CD termCd;

    /** batProcDate */
    private String batProcDate = null;

    /** GLOBAL_COMPANY_CODE */
    private String glblCmpyCd = null;

    /** Time Stamp */
    private String sysTimeStamp = null;

    /** TimeStamp format for RCV_DT, RCV_TM and RCV_TS */
    private static final String TYPE_TIME_STAMP = "yyyyMMddHHmmss";

    /**  */
    private static final String RCV_HDR_NUM_FMT = "%1$08d";

    /**  */
    private static final String RCV_DTL_NUM_FMT = "%1$04d";

    @Override
    protected void initRoutine() {
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.termCd = TERM_CD.NORMAL_END;
        this.glblCmpyCd = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
        if (S21StringUtil.isEmpty(this.glblCmpyCd)) {
            setTermState(TERM_CD.ABNORMAL_END, this.normalRecordCnt, this.errorRecordCnt, this.totalRecordCnt);
            throw new S21AbendException(NFCM0501E, new String[] {NFCDbConst.GLBL_CMPY_CD });
        }
        this.batProcDate = ZYPDateUtil.getBatProcDate(this.glblCmpyCd);
        this.sysTimeStamp = ZYPDateUtil.getCurrentSystemTime(TYPE_TIME_STAMP);
        if (S21StringUtil.isEmpty(this.sysTimeStamp)) {
            setTermState(TERM_CD.ABNORMAL_END, this.normalRecordCnt, this.errorRecordCnt, this.totalRecordCnt);
            throw new S21AbendException(NFCM0501E, new String[] {"TimeStamp" });
        }
    }

    @Override
    protected void mainRoutine() {
        // get target data
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String rcptHdrNum = "";
        String rcptDtlNum = "";

        // get RCV_SQ_PK
        NFCNumbering generator = new NFCNumbering();
        NFXC3060Bean numberBean = generator.getNumber(NFCConst.CST_SEQ_ID_RCV, null, 0);
        if (!NFCConst.CST_RTN_CD_NORM.equals(numberBean.getRtrnNo())) {
            throw new S21AbendException(NFCM0638E, new String[] {NFCConst.CST_SEQ_ID_RCV });
        }
        BigDecimal rcvSqPk = BigDecimal.ZERO;
        String prevCustAcctCd = null;
        String prevReqId = null;
        int hdrCnt = 1;
        int dtlCnt = 1;
        BigDecimal cnt = BigDecimal.ZERO;
        BigDecimal totAmt = BigDecimal.ZERO;
        String seqNo = "";

        try {
            stmt = getTargetData();
            rs = stmt.executeQuery();

            while (rs.next()) {
                if (!hasValue(prevCustAcctCd)) {
                    prevCustAcctCd = rs.getString(BILL_TO_CUST_ACCT_CD);
                    rcvSqPk = numberBean.getOraSqNo();
                    Map<String, Object> result = getSumOfTargetData(rs.getString(BILL_TO_CUST_ACCT_CD));
                    cnt = (BigDecimal) result.get("CNT");
                    totAmt = (BigDecimal) result.get("TOT_AMT");
                } else {
                    if (!prevCustAcctCd.equals(rs.getString(BILL_TO_CUST_ACCT_CD))) {
                        prevCustAcctCd = rs.getString(BILL_TO_CUST_ACCT_CD);
                        Map<String, Object> result = getSumOfTargetData(rs.getString(BILL_TO_CUST_ACCT_CD));
                        cnt = (BigDecimal) result.get("CNT");
                        totAmt = (BigDecimal) result.get("TOT_AMT");
                        numberBean = generator.getNumber(NFCConst.CST_SEQ_ID_RCV, null, 0);
                        rcvSqPk = numberBean.getOraSqNo();
                        hdrCnt = 0;
                    }
                }

                if (!hasValue(prevReqId)) {
                    prevReqId = rs.getString(ECHK_PMT_REQ_ID);
                    seqNo = rs.getString(ECHK_PMT_REQ_ID);
                } else {
                    if (!prevReqId.equals(rs.getString(ECHK_PMT_REQ_ID))) {
                        // update UpadteApplyFlg ON Y
                        upadteApplyFlg(prevReqId);
                        dtlCnt = 1;
                        hdrCnt++;
                        seqNo = rs.getString(ECHK_PMT_REQ_ID);
                        prevReqId = rs.getString(ECHK_PMT_REQ_ID);
                    } else {
                        dtlCnt++;
                    }
                }
                rcptHdrNum = String.format(RCV_HDR_NUM_FMT, hdrCnt);
                rcptDtlNum = String.format(RCV_DTL_NUM_FMT, dtlCnt);

                // create AR_RCPT_RCV_WRK
                AR_RCPT_RCV_WRKTMsg tmsg = new AR_RCPT_RCV_WRKTMsg();

                setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
                setValue(tmsg.rcvSqPk, rcvSqPk);
                setValue(tmsg.rcvHdrNum, rcptHdrNum);
                setValue(tmsg.rcvDtlNum, rcptDtlNum);
                setValue(tmsg.rcvDt, this.batProcDate);
                setValue(tmsg.rcvFuncId, FUNC_ID);
                setValue(tmsg.entryDt_01, this.batProcDate);
                setValue(tmsg.entryTm_01, "000000");
                setValue(tmsg.chk30Num, EC.concat(seqNo));
                setValue(tmsg.batNum, "999");
                setValue(tmsg.depDt_01, this.batProcDate);
                setValue(tmsg.chkAmt, rs.getBigDecimal(ECHK_PMT_AMT));
                setValue(tmsg.ansiCrDrFlg, FLG_OFF_N);
                setValue(tmsg.apvlFlg_01, FLG_OFF_N);
                setValue(tmsg.apvlFlg_02, FLG_OFF_N);
                setValue(tmsg.inv30Num, rs.getString(INV_NUM));
                setValue(tmsg.inv30NumCd, "IV");
                setValue(tmsg.chkRcptTotAmt, rs.getBigDecimal(INV_TOT_DEAL_NET_AMT));
                setValue(tmsg.invOrigTotAmt, rs.getBigDecimal(INV_TOT_DEAL_NET_AMT));
                setValue(tmsg.invDiscTotAmt, BigDecimal.ZERO);
                setValue(tmsg.cashApplyAmt, BigDecimal.ZERO);
                setValue(tmsg.rcvStsCd, "0");
                setValue(tmsg.upldCratMethTpCd, "A");
                setValue(tmsg.exptFlg, FLG_OFF_N);
                setValue(tmsg.arBatRcptNm, S21StringUtil.concatStrings(ECHECK, "_", rs.getString(BILL_TO_CUST_ACCT_CD), "_", this.sysTimeStamp));
                setValue(tmsg.custDsBankAcctPk, rs.getBigDecimal(DS_BANK_ACCT_PK));
                setValue(tmsg.custAcctRefNum, rs.getString(INV_NUM));
                setValue(tmsg.payerCustCd, rs.getString(BILL_TO_CUST_ACCT_CD));
                setValue(tmsg.custRcptNum, EC.concat(seqNo));
                setValue(tmsg.custInvNum, rs.getString(INV_NUM));
                setValue(tmsg.arRcptSrcCd, AR_RCPT_SRC.CHECK_BY_PHONE);
                setValue(tmsg.arCustIdStsCd, AR_CUST_ID_STS.IDENTIFIED);
                setValue(tmsg.batRcptRecCnt, cnt);
                setValue(tmsg.batRcptTotAmt, totAmt);

                EZDTBLAccessor.insert(tmsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
                    S21InfoLogOutput.println(rs.getString(ECHK_PMT_REQ_ID) + "error");
                    throw new S21AbendException(NFDM0013E, new String[] {"AR_RCPT_RCV_WRK" });
                } else {
                    this.normalRecordCnt++;
                }
                this.totalRecordCnt++;
            }
            if (prevReqId != null) {
                // update upadteApplyFlg ON_Y
                upadteApplyFlg(prevReqId);
                commit();
            }

        } catch (SQLException sqlEx) {
            rollback();
            throw new S21AbendException(NFDM0003E, new String[] {sqlEx.getMessage() });
        } catch (S21AbendException ex) {
            rollback();
            throw new S21AbendException(ex.getMessage());
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }

    }

    private PreparedStatement getTargetData() throws SQLException {
        PreparedStatement stmt = null;

        Map<String, String> sqlParam = new HashMap<String, String>();
        sqlParam.put("glblCmpyCd", this.glblCmpyCd);
        sqlParam.put("echkPmtStsCd", ECHK_PMT_STS.NORMAL);
        sqlParam.put("cashApplyFlg", ZYPConstant.FLG_OFF_N);
        stmt = this.ssmLLClient.createPreparedStatement("getTargetData", sqlParam);

        return stmt;
    }

    private Map<String, Object> getSumOfTargetData(String billtoCustCd) {

        Map<String, String> sqlParam = new HashMap<String, String>();
        sqlParam.put("glblCmpyCd", this.glblCmpyCd);
        sqlParam.put("echkPmtStsCd", ECHK_PMT_STS.NORMAL);
        sqlParam.put("cashApplyFlg", ZYPConstant.FLG_OFF_N);
        sqlParam.put("custCd", billtoCustCd);
        return (Map<String, Object>) ssmBatchClient.queryObject("getSumOfTargetData", sqlParam);
    }

    private void upadteApplyFlg(String reqId) {
        ECHK_RCPT_TRXTMsg inMsg = new ECHK_RCPT_TRXTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.echkPmtReqId, reqId);
        ECHK_RCPT_TRXTMsg echecktrxtmsg = (ECHK_RCPT_TRXTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inMsg);
        if (echecktrxtmsg == null) {
            throw new S21AbendException(NFCM0594E, new String[] {"ECHK_RCPT_TRX" });
        }
        setValue(echecktrxtmsg.arCashApplyFlg, ZYPConstant.FLG_ON_Y);
        S21FastTBLAccessor.update(echecktrxtmsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(echecktrxtmsg.getReturnCode())) {
            throw new S21AbendException(NFCM0533E, new String[] {"ECHK_RCPT_TRX" });
        }
    }

    @Override
    protected void termRoutine() {
        S21InfoLogOutput.println("termRoutine Method Start");
        // Set termination code and total commit count.
        setTermState(this.termCd, normalRecordCnt, errorRecordCnt, totalRecordCnt);
        S21InfoLogOutput.println("termRoutine Method End");
    }

    /**
     * main
     * @param args String[]
     */
    public static void main(String[] args) {
        S21InfoLogOutput.println("Main Method Start");
        new NFCB060001().executeBatch(NFCB060001.class.getSimpleName());
        S21InfoLogOutput.println("Main Method End");

    }

}
