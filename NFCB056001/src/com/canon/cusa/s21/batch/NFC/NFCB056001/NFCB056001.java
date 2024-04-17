package com.canon.cusa.s21.batch.NFC.NFCB056001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.AR_CR_CARD_TRX_INTFC_MNGTMsg;
import business.db.AR_RCPT_RCV_WRKTMsg;

import com.canon.cusa.s21.common.NFX.NFXC306001.NFCNumbering;
import com.canon.cusa.s21.common.NFX.NFXC306001.NFXC3060Bean;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCConst;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCDbConst;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CUST_ID_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_AUTH_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPExtnNumbering;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21BatchUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Auto Cash Apply for Credit Card Payment
 *
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 12/21/2015   CSAI            K.Uramori       Create          N/A
 * 07/27/2018   Hitachi         E.Kameishi      Update          QC#27419
 * 08/27/2018   CITS            T.Gotoda        Update          QC#27471
 *</pre>
 */
public class NFCB056001 extends S21BatchMain implements ZYPConstant, NFCB0560Constant {

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

    /** Receipt Source */
    private String rcptSrc = null;

    /** Batch Name Prefix */
    private String batNmPrfx = null;

    /** Payment Method Code */
    private String pmtMethCd = null;

    /** List of TMsgs */
    private List<AR_RCPT_RCV_WRKTMsg> listWrkTmsg;

    /** List of TMsgs */
    private List<AR_CR_CARD_TRX_INTFC_MNGTMsg> listMngTmsg;

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

        // START 2018/07/27 E.Kameishi [QC#27419, DEL]
        // obtain receipt source from parameter
        //rcptSrc = S21BatchUtil.getUserVariable1();

        //if (!hasValue(rcptSrc)) {
        //    setTermState(TERM_CD.ABNORMAL_END, this.normalRecordCnt, this.errorRecordCnt, this.totalRecordCnt);
        //    throw new S21AbendException(NFCM0501E, new String[] {"Receipt Source" });
        //}
        // START 2018/07/27 E.Kameishi [QC#27419, DEL]

        // obtain batch name prefix
        batNmPrfx = S21BatchUtil.getUserVariable2();

        if (!hasValue(batNmPrfx)) {
            setTermState(TERM_CD.ABNORMAL_END, this.normalRecordCnt, this.errorRecordCnt, this.totalRecordCnt);
            throw new S21AbendException(NFCM0501E, new String[] {"Batch Name Prefix" });
        }

        // obtain payment method code
        pmtMethCd = S21BatchUtil.getUserVariable3();

        // initialization
        listWrkTmsg = new ArrayList<AR_RCPT_RCV_WRKTMsg>();
        listMngTmsg = new ArrayList<AR_CR_CARD_TRX_INTFC_MNGTMsg>();

    }

    @Override
    protected void mainRoutine() {

        // get target data as summary
        Map<String, Object> result = getSumOfTargetData();

        if (result == null) {
            return; // nothing to be processed
        }

        BigDecimal cnt = (BigDecimal) result.get("CNT");

        BigDecimal totAmt = (BigDecimal) result.get(CR_CARD_AUTH_AMT);

        // get target data
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String rcptHdrNum = "";
        String rcptDtlNum = String.format(RCV_DTL_NUM_FMT, 1);

        // get RCV_SQ_PK
        NFCNumbering generator = new NFCNumbering();
        NFXC3060Bean numberBean = generator.getNumber(NFCConst.CST_SEQ_ID_RCV, null, 0);
        if (!NFCConst.CST_RTN_CD_NORM.equals(numberBean.getRtrnNo())) {
            throw new S21AbendException(NFCM0638E, new String[] {NFCConst.CST_SEQ_ID_RCV });
        }
        BigDecimal rcvSqPk = numberBean.getOraSqNo();

        int hdrCnt = 1;
        
        try {
            stmt = getTargetData();
            rs = stmt.executeQuery();

            while (rs.next()) {

                if (BigDecimal.ZERO.compareTo(rs.getBigDecimal(CR_CARD_AUTH_AMT)) >= 0) {

                    // create interface management record
                    createMngTbl(rs, null);

                    submitMngTbl(false);

                    // if amt is not greater than 0, skip
                    continue;
                }

                rcptHdrNum = String.format(RCV_HDR_NUM_FMT, hdrCnt);
                hdrCnt += 1;
                
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
                setValue(tmsg.batNum, "999");
                setValue(tmsg.depDt_01, rs.getString(CR_CARD_SETL_DT));
                setValue(tmsg.chkAmt, rs.getBigDecimal(CR_CARD_AUTH_AMT));
                setValue(tmsg.ansiCrDrFlg, FLG_OFF_N);
                setValue(tmsg.ansiPmtMethCd, this.pmtMethCd);
                setValue(tmsg.apvlFlg_01, FLG_OFF_N);
                setValue(tmsg.apvlFlg_02, FLG_OFF_N);
                setValue(tmsg.inv30Num, rs.getString(AR_TRX_NUM));
                setValue(tmsg.inv30NumCd, "IV");
                setValue(tmsg.inv30OrigNum, rs.getString(GRP_INV_NUM));
                setValue(tmsg.chkRcptTotAmt, rs.getBigDecimal(DEAL_RMNG_BAL_GRS_AMT));
                setValue(tmsg.invOrigTotAmt, rs.getBigDecimal(DEAL_ORIG_GRS_AMT));
                setValue(tmsg.invDiscTotAmt, BigDecimal.ZERO);
                setValue(tmsg.cashApplyAmt, BigDecimal.ZERO);
                setValue(tmsg.rcvStsCd, "0");
                setValue(tmsg.upldCratMethTpCd, "A");
                setValue(tmsg.exptFlg, FLG_OFF_N);
                setValue(tmsg.arBatRcptNm, batNmPrfx.concat(this.sysTimeStamp));

                setValue(tmsg.custAcctRefNum, rs.getString(AR_TRX_NUM));
                setValue(tmsg.payerCustCd, rs.getString(BILL_TO_CUST_ACCT_CD));
                setValue(tmsg.custInvNum, rs.getString(AR_TRX_NUM));
                // START 2018/07/27 E.Kameishi [QC#27419, MOD]
                //setValue(tmsg.arRcptSrcCd, this.rcptSrc);
                setValue(tmsg.arRcptSrcCd, AR_RCPT_SRC.CREDIT_CARD_PAYMENT);
                String custRcptNum = S21StringUtil.concatStrings(AR_RCPT_CHK_NUM_HDR, ZYPExtnNumbering.getUniqueID(getGlobalCompanyCode(), AUTO_SQ_EXTN_KEY_AR_RCPT_CHK_NUM));
                setValue(tmsg.custRcptNum, custRcptNum);
                setValue(tmsg.chk30Num, custRcptNum);
                // END 2018/07/27 E.Kameishi [QC#27419, MOD]

                if (hasValue(rs.getString(BILL_TO_CUST_ACCT_CD))) {
                    setValue(tmsg.arCustIdStsCd, AR_CUST_ID_STS.IDENTIFIED);
                } else {
                    setValue(tmsg.arCustIdStsCd, AR_CUST_ID_STS.UN_IDENTIFIED);
                }

                setValue(tmsg.batRcptRecCnt, cnt);
                setValue(tmsg.batRcptTotAmt, totAmt);

                setValue(tmsg.crCardApvlCd, rs.getString(CR_CARD_AUTH_REF_NUM));

                listWrkTmsg.add(tmsg);
                
                submitWrkTbl(false);

                // create interface management record
                createMngTbl(rs, rcvSqPk);

                submitMngTbl(false);

            }

            // process rest of TMessages
            if (listWrkTmsg.size() != 0) {
                submitWrkTbl(true);
            }

            // process rest of TMessages
            if (listMngTmsg.size() != 0) {
                submitMngTbl(true);
            }

            commit();

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
        sqlParam.put("crCardTrxTp", CR_CARD_TRX_TP.INVOICE);
        //QC#27471 ADD START
        sqlParam.put("crAuthStsCd", CR_CARD_AUTH_STS.SETTLEMENT_COMPLETED);
        //QC#27471 ADD END
        sqlParam.put("rcp", AR_TRX_TP.RECEIPT);

        stmt = this.ssmLLClient.createPreparedStatement("getTargetData", sqlParam);

        return stmt;
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getSumOfTargetData() {
        Map<String, String> sqlParam = new HashMap<String, String>();
        sqlParam.put("glblCmpyCd", this.glblCmpyCd);
        sqlParam.put("crCardTrxTp", CR_CARD_TRX_TP.INVOICE);
        sqlParam.put("rcp", AR_TRX_TP.RECEIPT);

        return (Map<String, Object>) ssmBatchClient.queryObject("getSumOfTargetData", sqlParam);
    }

    private void createMngTbl(ResultSet rs, BigDecimal rcvSqPk) throws SQLException {

        AR_CR_CARD_TRX_INTFC_MNGTMsg tmsg = new AR_CR_CARD_TRX_INTFC_MNGTMsg();

        setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tmsg.crCardTrxPk, rs.getBigDecimal(CR_CARD_TRX_PK));
        setValue(tmsg.arTrxBalPk, rs.getBigDecimal(AR_TRX_BAL_PK));
        setValue(tmsg.rcvSqPk, rcvSqPk);

        listMngTmsg.add(tmsg);
    }

    private boolean submitWrkTbl(boolean isLast) {
        // AR_RCPT_RCV_WRKTMsg[] tmsgs;

        if (listWrkTmsg.size() == BULK_CNT || isLast) {
            AR_RCPT_RCV_WRKTMsg[] tmsgs = listWrkTmsg.toArray(new AR_RCPT_RCV_WRKTMsg[listWrkTmsg.size()]);

            // insert using fast table accessor
            int retCnt = S21FastTBLAccessor.insert(tmsgs);

            if (retCnt != listWrkTmsg.size()) {
                throw new S21AbendException(NFDM0013E, new String[] {"AR_RCPT_RCV_WRK" });
            } else {
                // initialize
                listWrkTmsg = new ArrayList<AR_RCPT_RCV_WRKTMsg>();
                normalRecordCnt += retCnt;
            }
            return true;
        } else {
            return true;
        }

    }

    private boolean submitMngTbl(boolean isLast) {

        if (listMngTmsg.size() == BULK_CNT || isLast) {
            AR_CR_CARD_TRX_INTFC_MNGTMsg[] tmsgs = listMngTmsg.toArray(new AR_CR_CARD_TRX_INTFC_MNGTMsg[listMngTmsg.size()]);

            // insert using fast table accessor
            int retCnt = S21FastTBLAccessor.insert(tmsgs);

            if (retCnt != listMngTmsg.size()) {
                throw new S21AbendException(NFDM0013E, new String[] {"AR_CR_CARD_TRX_INTFC_MNG" });
            } else {
                // initialize
                listMngTmsg = new ArrayList<AR_CR_CARD_TRX_INTFC_MNGTMsg>();
            }
            return true;
        } else {
            return true;
        }

    }

    @Override
    protected void termRoutine() {

        S21InfoLogOutput.println("termRoutine Method Start");

        // Set termination code and total commit count.
        setTermState(this.termCd, normalRecordCnt, errorRecordCnt, normalRecordCnt);

        S21InfoLogOutput.println("termRoutine Method End");

    }

    /**
     * main
     * @param args
     */
    public static void main(String[] args) {
        S21InfoLogOutput.println("Main Method Start");

        new NFCB056001().executeBatch(NFCB056001.class.getSimpleName());

        S21InfoLogOutput.println("Main Method End");

    }

}
