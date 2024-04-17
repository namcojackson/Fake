/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB205001;

import static com.canon.cusa.s21.batch.NWA.NWAB205001.constant.NWAB205001Constant.FSR_STS_CD_CLOSED;
import static com.canon.cusa.s21.batch.NWA.NWAB205001.constant.NWAB205001Constant.PROGRAM_ID;
import static com.canon.cusa.s21.batch.NWA.NWAB205001.constant.NWAB205001Constant.PROGRAM_NM;
import static com.canon.cusa.s21.batch.NWA.NWAB205001.constant.NWAB205001Constant.VAR_CHAR_CONST_NM_NWAB2050_PURGE_MTH;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CR_CARD_TRXTMsg;
import business.db.CR_CARD_TRX_DTLTMsg;
import business.db.CR_CARD_TRX_DTL_LOGTMsg;
import business.db.CR_CARD_TRX_LOGTMsg;
import business.db.DS_CR_CARDTMsg;

import com.canon.cusa.s21.batch.NWA.NWAB205001.constant.NWAB205001Constant.MSG_ID;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001Mail;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001MailSubstituteString;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_AUTH_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 * Cycle Expiration Check Batch
 * program of BusinessID NWAB205001. 
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/20/2016   Fujitsu         S.Ohki          Create          N/A
 * 04/01/2021   CITS            K.Ogino         Update          QC#58621
 * 
 *</pre>
 */
public class NWAB205001 extends S21BatchMain {

    /** Global Company code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String slsDt = null;

    /** Purge Month */
    private String purgeMth = null;

    /** Term Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Error Record Count */
    private int errRecCnt = 0;

    /** records */
    private int totalRecCnt = 0;

    /** Normal Record Count */
    private int normalRecCnt = 0;

    /** Error Valid Count */
    private int errValidCnt = 0;

    /** Total Valid Count */
    private int totalValidCnt = 0;

    /** Normal Valid Count */
    private int normalValidCnt = 0;

    /** commit Count */
    private int commitCnt = 0;

    /** SQL access parts */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Mail template ID */
    private String mailTemplateId = null;

    /**
     * Main
     * @param args String[]
     */
    public static void main(String[] args) {
        new NWAB205001().executeBatch(NWAB205001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        // Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(MSG_ID.ZZZM9025E.toString(), toArray("Global Company Code"));
        }

        // Sales Date
        this.slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.slsDt)) {
            throw new S21AbendException(MSG_ID.ZZZM9025E.toString(), toArray("Sales Date"));
        }

        // Mail Template
        mailTemplateId = getUserVariable1();
        if (!ZYPCommonFunc.hasValue(mailTemplateId)) {
            throw new S21AbendException(MSG_ID.ZZZM9025E.toString(), toArray("Mail Template Id"));
        }

        // Var Char Constant
        this.purgeMth = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NM_NWAB2050_PURGE_MTH, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(purgeMth)) {
            throw new S21AbendException(MSG_ID.NWAM0373E.toString(), toArray("VAR_CHAR_CONST", "GLBL_CMPY_CD=" + this.glblCmpyCd));
        }

        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {

        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("slsDt", this.slsDt);

        ssmParam.put("svcInvSrcTpCd", SVC_INV_SRC_TP.CONTRACT);
        ssmParam.put("crCardAuthStsCdSaved", CR_CARD_AUTH_STS.SAVED);
        ssmParam.put("crCardAuthStsCdAuthCmp", CR_CARD_AUTH_STS.AUTHORIZED_COMPLETED);
        ssmParam.put("crCardAuthStsCdStlCmp", CR_CARD_AUTH_STS.SETTLEMENT_COMPLETED);
        ssmParam.put("crCardAuthStsCdRefCmp", CR_CARD_AUTH_STS.REFUND_COMPLETED);
        ssmParam.put("crCardAuthStsCdVoidCmp", CR_CARD_AUTH_STS.VOID_COMPLETED);
        ssmParam.put("dateFormat", "YYYYMMDD");
        ssmParam.put("dsinPeriodMonth", -Integer.parseInt(this.purgeMth));

        boolean rslt = (Boolean) ssmBatchClient.queryObject("getCrCardTrx", ssmParam, new CreditCardTransactionPurge());
        
        final Map<String, Object> ssmInvalidParam = new HashMap<String, Object>();
        ssmInvalidParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmInvalidParam.put("slsDtYrMth", this.slsDt.substring(0, 6));
        ssmInvalidParam.put("crCardValidFlg", ZYPConstant.FLG_ON_Y);

        boolean invalidRslt = (Boolean) ssmBatchClient.queryObject("getCrCardInvalidTarget", ssmInvalidParam, new CreditCardInvalid());

        if (!rslt || !invalidRslt) {
            this.termCd = TERM_CD.WARNING_END;
        }
    }

    @Override
    protected void termRoutine() {

        setTermState(this.termCd, this.normalRecCnt, this.errRecCnt, this.totalRecCnt);

        boolean isNeededMailInfo = TERM_CD.WARNING_END == this.termCd;
        isNeededMailInfo = isNeededMailInfo || TERM_CD.ABNORMAL_END == this.termCd;
        isNeededMailInfo = isNeededMailInfo || this.errRecCnt > 0;

        if (isNeededMailInfo) {
            if (!postErrorMail()) {
                throw new S21AbendException(MSG_ID.NWAM0447E.toString(), toArray(PROGRAM_NM, "Mail Sending Process"));
            }
        }
    }

    /**
     * ResultSet of SQL process.
     */
    protected class CreditCardTransactionPurge extends S21SsmBooleanResultSetHandlerSupport {

        @Override
        protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            Boolean rslt = Boolean.TRUE;

            // Has No Result Data
            if (!rs.next()) {
                return true;
            }

            int commitSize = getCommitCount();

            do {
                boolean successFlg = true;
                boolean targetFlg = false;

                BigDecimal crCardTrxPk = rs.getBigDecimal("CR_CARD_TRX_PK");
                String crCardTrxTpCd = rs.getString("CR_CARD_TRX_TP_CD");
                String stsCd = rs.getString("STS_CD");
                String cpoOrdNum = rs.getString("CPO_ORD_NUM");
                String fnlzInvFlg = rs.getString("FNLZ_INV_FLG");
                String fsrNum = rs.getString("FSR_NUM");

                // CPO
                if (CR_CARD_TRX_TP.CPO.equals(crCardTrxTpCd)) {

                    if (ORD_HDR_STS.CLOSED.equals(stsCd) || ORD_HDR_STS.CANCELLED.equals(stsCd)) {
                        successFlg = dataPurge(crCardTrxPk);
                        targetFlg = true;
                    }

                // FSR
                } else if (CR_CARD_TRX_TP.SERVICE_REQUEST.equals(crCardTrxTpCd)) {

                    if (FSR_STS_CD_CLOSED.equals(stsCd)) {
                        successFlg = dataPurge(crCardTrxPk);
                        targetFlg = true;
                    }

                // Invoice
                } else if (CR_CARD_TRX_TP.INVOICE.equals(crCardTrxTpCd) && ZYPConstant.FLG_ON_Y.equals(fnlzInvFlg)) {

                    if (ZYPCommonFunc.hasValue(cpoOrdNum)) {

                        Map<String, Object> ssmParam = new HashMap<String, Object>();
                        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
                        ssmParam.put("cpoOrdNum", cpoOrdNum);

                        List<String> ordHdrStsCdList = new ArrayList<String>(2);
                        ordHdrStsCdList.add(ORD_HDR_STS.CLOSED);
                        ordHdrStsCdList.add(ORD_HDR_STS.CANCELLED);
                        ssmParam.put("ordHdrStsCdList", ordHdrStsCdList);

                        BigDecimal count = (BigDecimal) ssmBatchClient.queryObject("cntCpoClose", ssmParam);

                        if (BigDecimal.ZERO.compareTo(count) < 0) {
                            successFlg = dataPurge(crCardTrxPk);
                            targetFlg = true;
                        }

                    } else if (ZYPCommonFunc.hasValue(fsrNum)) {

                        Map<String, Object> ssmParam = new HashMap<String, Object>();
                        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
                        ssmParam.put("fsrNum", fsrNum);
                        ssmParam.put("fsrStsCd", FSR_STS_CD_CLOSED);

                        BigDecimal count = (BigDecimal) ssmBatchClient.queryObject("cntFsrClose", ssmParam);

                        if (BigDecimal.ZERO.compareTo(count) < 0) {
                            successFlg = dataPurge(crCardTrxPk);
                            targetFlg = true;
                        }

                    } else {
                        successFlg = dataPurge(crCardTrxPk);
                        targetFlg = true;
                    }
                }

                if (targetFlg) {
                    if (!successFlg) {
                        rollback();
                        errRecCnt++;
                        commitCnt = 0;
                    } else {
                        normalRecCnt++;
                        commitCnt++;

                        if (commitCnt >= commitSize) {
                            commit();
                            commitCnt = 0;
                        }
                    }
                    totalRecCnt++;
                }

            } while (rs.next());

            if (commitCnt > 0) {
                commit();
            }

            if (errRecCnt > 0) {
                rslt = Boolean.FALSE;
            }
            return rslt;
        }
    }

    /**
     * <pre>
     * Data Purge
     * </pre>
     * @param crCardTrxPk BigDecimal
     */
    private boolean dataPurge(BigDecimal crCardTrxPk) {

        CR_CARD_TRXTMsg crCardTrxTMsg = new CR_CARD_TRXTMsg();
        ZYPEZDItemValueSetter.setValue(crCardTrxTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(crCardTrxTMsg.crCardTrxPk, crCardTrxPk);

        crCardTrxTMsg = (CR_CARD_TRXTMsg) EZDTBLAccessor.findByKey(crCardTrxTMsg);

        if (crCardTrxTMsg == null) {
            return true;
        }

        CR_CARD_TRX_LOGTMsg crCardTrxLogTMsg = new CR_CARD_TRX_LOGTMsg();

        EZDMsg.copy(crCardTrxTMsg, null, crCardTrxLogTMsg, null);

        // CR_CARD_TRX_LOG Insert
        S21FastTBLAccessor.insert(crCardTrxLogTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(crCardTrxLogTMsg.getReturnCode())) {
            S21InfoLogOutput.println(MSG_ID.NWAM0836E.toString(), toArray(crCardTrxPk.toString()));
            return false;
        }

        // CR_CARD_TRX Remove
        EZDTBLAccessor.remove(crCardTrxTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(crCardTrxTMsg.getReturnCode())) {
            S21InfoLogOutput.println(MSG_ID.NWAM0836E.toString(), toArray(crCardTrxPk.toString()));
            return false;
        }

        // Add QC#58621 Start
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("crCardTrxPk", crCardTrxPk);

        List<BigDecimal> dtlPkList = (List<BigDecimal>) ssmBatchClient.queryObjectList("getCrCardTrxDtlPks", ssmParam);

        if (dtlPkList == null) {
            return true;
        }

        for (BigDecimal dtlPk : dtlPkList) {

            CR_CARD_TRX_DTLTMsg crCardTrxDtlTMsg = new CR_CARD_TRX_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(crCardTrxDtlTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(crCardTrxDtlTMsg.crCardTrxDtlPk, dtlPk);

            crCardTrxDtlTMsg = (CR_CARD_TRX_DTLTMsg) EZDTBLAccessor.findByKey(crCardTrxDtlTMsg);

            if (crCardTrxDtlTMsg == null) {
                return true;
            }

            CR_CARD_TRX_DTL_LOGTMsg crCardTrxDtlLogTMsg = new CR_CARD_TRX_DTL_LOGTMsg();

            EZDMsg.copy(crCardTrxDtlTMsg, null, crCardTrxDtlLogTMsg, null);

            // CR_CARD_TRX_DTL_LOG Insert
            S21FastTBLAccessor.insert(crCardTrxDtlLogTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(crCardTrxDtlLogTMsg.getReturnCode())) {
                S21InfoLogOutput.println(MSG_ID.NWAM0836E.toString(), toArray(crCardTrxPk.toString()));
                return false;
            }

            // CR_CARD_TRX_DTL Remove
            EZDTBLAccessor.remove(crCardTrxDtlTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(crCardTrxDtlTMsg.getReturnCode())) {
                S21InfoLogOutput.println(MSG_ID.NWAM0836E.toString(), toArray(crCardTrxPk.toString()));
                return false;
            }
        }
        // Add QC#58621 End
        return true;
    }

    /**
     * <pre>
     * Post Error Mail
     * </pre>
     */
    private boolean postErrorMail() {

        final List<NWXC001001MailSubstituteString> sbsStrList = new ArrayList<NWXC001001MailSubstituteString>(3);

        NWXC001001MailSubstituteString sbsStr;

        sbsStr = new NWXC001001MailSubstituteString();
        sbsStr.setSbstKey("batchId");
        sbsStr.setSbstStr(this.getClass().getSimpleName());
        sbsStrList.add(sbsStr);

        sbsStr = new NWXC001001MailSubstituteString();
        sbsStr.setSbstKey("batchNm");
        sbsStr.setSbstStr(PROGRAM_NM);
        sbsStrList.add(sbsStr);

        sbsStr = new NWXC001001MailSubstituteString();
        sbsStr.setSbstKey("batchProcLogId");
        sbsStr.setSbstStr(super.getBatchProcessLogID());
        sbsStrList.add(sbsStr);

        boolean isNormalEnd = new NWXC001001Mail().postMail(glblCmpyCd, PROGRAM_ID, mailTemplateId, sbsStrList);

        return isNormalEnd;
    }

    /**
     * <pre>
     * To Array
     * </pre>
     * @param appendMsgList String[]
     * @return String[]
     */
    private String[] toArray(String... appendMsgList) {
        String[] msgArray = new String[appendMsgList.length];
        for (int i = 0; i < msgArray.length; i++) {
            msgArray[i] = appendMsgList[i];
        }
        return msgArray;
    }

    /**
     * ResultSet of SQL process.
     */
    protected class CreditCardInvalid extends S21SsmBooleanResultSetHandlerSupport {

        @Override
        protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            Boolean rslt = Boolean.TRUE;

            // Has No Result Data
            if (!rs.next()) {
                return true;
            }

            int commitSize = getCommitCount();

            do {

                BigDecimal dsCrCardPk = rs.getBigDecimal("DS_CR_CARD_PK");

                if (updateCrCardValidFlg(dsCrCardPk)) {
                    normalValidCnt++;
                    commitCnt++;

                    if (commitCnt >= commitSize) {
                        commit();
                        commitCnt = 0;
                    }
                } else {
                    rollback();
                    errValidCnt++;
                    commitCnt = 0;
                }

                totalValidCnt++;

            } while (rs.next());

            if (commitCnt > 0) {
                commit();
            }

            if (errValidCnt > 0) {
                rslt = Boolean.FALSE;
            }
            return rslt;
        }
    }

    /**
     * <pre>
     * Update Valid Flag Of DS_CR_CARD
     * </pre>
     * @param dsCrCardPk BigDecimal
     * @return boolean
     */
    private boolean updateCrCardValidFlg(BigDecimal dsCrCardPk) {

        DS_CR_CARDTMsg dsCrCardTMsg = new DS_CR_CARDTMsg();
        ZYPEZDItemValueSetter.setValue(dsCrCardTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsCrCardTMsg.dsCrCardPk, dsCrCardPk);

        dsCrCardTMsg = (DS_CR_CARDTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(dsCrCardTMsg);

        ZYPEZDItemValueSetter.setValue(dsCrCardTMsg.crCardValidFlg, ZYPConstant.FLG_OFF_N);

        // DA_CR_CARD Update
        EZDTBLAccessor.update(dsCrCardTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsCrCardTMsg.getReturnCode())) {
            S21InfoLogOutput.println(MSG_ID.NWZM1024E.toString(), toArray(dsCrCardTMsg.getTableName(), dsCrCardPk.toString()));
            return false;
        }
        return true;
    }

}
