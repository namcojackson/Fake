/**
 * <Pre>Copyright(c)2018 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB097001;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.CONSL_BILLTMsg;
import business.db.CONSL_BILL_GRPTMsg;
import business.db.CONSL_BILL_LINETMsg;

import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001Mail;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001MailSubstituteString;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONSL_PRINT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONSL_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPMaxTenDigitsNumbering;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * Temporary Entitlement Consolidate Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/07/17   Fujitsu         T.Ogura         Create          QC#13309
 *</pre>
 */
public class NSBB097001 extends S21BatchMain {

    /**
     *  Message IDs
     * @author Q10628
     *
     */
    private static enum MSG_ID {
        /** [@] is mandatory. */
        ZZZM9025E
        /** @ ended abnormally. */
        , NLBM0024E
        /** It failed to register @ Table. */
        , NWCM0109E
    }

    /** Program Name */
    private static final String PROGRAM_NM = "Temporary Entitlement Consolidate Batch";

    /** Create Program ID */
    private static final String CRAT_PGM_ID = "NSBB097001";

    /** Create Person CD */
    private static final String CRAT_PSN_CD = "NSBB0970";

    /** Normal Record Count */
    private int normalRecCnt;

    /** Error Record Count */
    private int errRecCnt;

    /** Term Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** records */
    private int totalRecCnt;

    /** SSM */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String slsDt = null;

    /** Mail template ID */
    private  String mailTemplateId = null;

    /** error Info List */
    private List<String> errInfoList = new ArrayList<String>();

    /** digit consolidated bill line number */
    private static final int DIGIT_CONSL_BILL_LINE_NUM = new CONSL_BILL_LINETMsg().getAttr("conslBillLineNum").getDigit();

    /** Automatic Sequence Number Code (Consolidated Bill Number) */
    private static final String CONST_AUTO_SEQ_CD_CONSL_BILL_NUM = "CONSL_BILL_NUM";

    /** Line Break */
    private static final String CONST_LINE_BREAK = "\r\n";

    /**
     * main process
     * @param args String[] batch parameters
     */
    public static void main(String[] args) {
        new NSBB097001().executeBatch();
    }

    @Override
    protected void initRoutine() {
        this.normalRecCnt = 0;
        this.errRecCnt = 0;
        this.termCd = TERM_CD.NORMAL_END;

        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        // Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(MSG_ID.ZZZM9025E.toString(), toArray("Global Company Code"));
        }

        // mail template id
        mailTemplateId = getUserVariable1();
        if (!ZYPCommonFunc.hasValue(mailTemplateId)) {
            throw new S21AbendException(MSG_ID.ZZZM9025E.toString(), toArray("Mail Template Id"));
        }

        // Sales Date
        this.slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
    }

    @Override
    protected void mainRoutine() {

        // Search Target Data
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd",      this.glblCmpyCd);
        ssmParam.put("slsDt",           this.slsDt);

        boolean rslt = (Boolean) ssmBatchClient.queryObject("getTargetData", ssmParam, new ConsolidatedBillCreator());
        if (!rslt) {
            this.termCd = TERM_CD.WARNING_END;
        }
    }

    @Override
    protected void termRoutine() {
        this.totalRecCnt = this.normalRecCnt + this.errRecCnt;
        setTermState(this.termCd, this.normalRecCnt, this.errRecCnt, this.totalRecCnt);

        boolean isNeededMailInfo = TERM_CD.WARNING_END == this.termCd;
        isNeededMailInfo = isNeededMailInfo || TERM_CD.ABNORMAL_END == this.termCd;
        isNeededMailInfo = isNeededMailInfo || this.errRecCnt > 0;

        if (isNeededMailInfo) {
            // post error mail.
            if (!postErrorMail()) {
                throw new S21AbendException(MSG_ID.NLBM0024E.toString(), toArray(PROGRAM_NM + ":mail sending process"));
            }
        }
    }

    private boolean postErrorMail() {

        final List<NWXC001001MailSubstituteString> sbsStrList = new ArrayList<NWXC001001MailSubstituteString>();

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

        if (!errInfoList.isEmpty()) {
            StringBuffer sb = new StringBuffer("Invoice#, Bill To Customer Name, Error Message" + CONST_LINE_BREAK);
            for (String errInfo : errInfoList) {
                sb.append(errInfo).append(CONST_LINE_BREAK);
            }
            sbsStr = new NWXC001001MailSubstituteString();
            sbsStr.setSbstKey("errorInfo");
            sbsStr.setSbstStr(String.valueOf(sb.toString()));
            sbsStrList.add(sbsStr);
        }

        boolean isNormalEnd = new NWXC001001Mail().postMail(glblCmpyCd, "NSBB0970", mailTemplateId, sbsStrList);

        return isNormalEnd;

    }

    /**
     * ResultSet of SQL process.
     *
     */
    protected class ConsolidatedBillCreator extends S21SsmBooleanResultSetHandlerSupport {

        @Override
        protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            Boolean rslt = Boolean.TRUE;

            // Has no result data
            if (!rs.next()) {
                return true;
            }

            String prevBillToCustCd = null;
            List<ConslBillBean> beanList = new ArrayList<ConslBillBean>();
            List<String> errBillToCustCd = new ArrayList<String>();

            do {
                ConslBillBean bean = new ConslBillBean();
                bean.setResult(rs);

                String billToCustCd = bean.getBillToCustCd();

                if (errBillToCustCd.contains(billToCustCd)) {
                    continue;
                }

                if (prevBillToCustCd != null && !billToCustCd.equals(prevBillToCustCd)) {

                    if (!createConslBill(beanList)) {
                        errRecCnt +=  beanList.size();
                        rollback();
                    } else {
                        normalRecCnt += beanList.size();
                        commit();
                    }
                    beanList = new ArrayList<ConslBillBean>();
                }

                beanList.add(bean);
                prevBillToCustCd = billToCustCd;

            } while (rs.next());

            if (!beanList.isEmpty()) {
                if (!createConslBill(beanList)) {
                    errRecCnt += beanList.size();
                    rollback();
                } else {
                    normalRecCnt += beanList.size();
                    commit();
                }
            }

            if (errRecCnt > 0) {
                rslt = Boolean.FALSE;
            }
            return rslt;
        }

    }

    private boolean createConslBill(List<ConslBillBean> beanList) {

        // bill# grouping and set conslBillPk
        setConslBillPk(beanList);

        Map<BigDecimal, Integer> lineNumMap = new HashMap<BigDecimal, Integer>();
        List<BigDecimal> procPkList = new ArrayList<BigDecimal>();
        for (ConslBillBean bean : beanList) {

            BigDecimal conslBillPk = bean.getConslBillPk();
            // insert CONSL_BILL
            if (!procPkList.contains(conslBillPk)) {

                procPkList.add(conslBillPk);
                if (!insertConslBill(bean, beanList)) {
                    return false;
                }
                if (!insertConslBillGrp(bean)) {
                    return false;
                }
            }

            // insert CONSL_BILL_LINE
            Integer lineNum = lineNumMap.get(conslBillPk);
            if (lineNum == null) {
                lineNum = 0;
            }
            lineNumMap.put(conslBillPk, ++lineNum);
            String conslBillLineNum = ZYPCommonFunc.leftPad(Integer.toString(lineNum), DIGIT_CONSL_BILL_LINE_NUM, "0");
            if (!insertConslBillLine(bean, conslBillLineNum)) {
                return false;
            }
        }

        return true;
    }

    private boolean insertConslBill(ConslBillBean bean, List<ConslBillBean> beanList) {

        CONSL_BILLTMsg conslBillTMsg = new CONSL_BILLTMsg();
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.glblCmpyCd,        glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.conslBillPk,       bean.getConslBillPk());
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.billToCustAcctCd,  bean.getBillToCustAcctCd());
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.billToCustAcctNm,  bean.getBillToCustAcctNm());
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.billToCustCd,      bean.getBillToCustCd());
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.billToCustLocNum,  bean.getBillToCustLocNum());
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.conslStsCd,        CONSL_STS.ACCEPTED);
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.conslPrintStsCd,   CONSL_PRINT_STS.NOT_PRINTED);
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.ctoffDt,           slsDt);

        String pmtTermCashDiscCd = null;
        String pmtTermCd = null;
        String pmtTermCashDiscDescTxt = null;
        String dueDate = null;

        // minimum dueDate
        for (ConslBillBean lineBean : beanList) {
            if (!bean.getConslBillPk().equals(lineBean.getConslBillPk())) {
                continue;
            }
            if (dueDate == null || dueDate.compareTo(lineBean.getInvDueDt()) > 0) {
                pmtTermCashDiscCd       = lineBean.getPmtTermCashDiscCd();
                pmtTermCd               = lineBean.getPmtTermCd();
                pmtTermCashDiscDescTxt  = lineBean.getPmtTermCashDiscDescTxt();
                dueDate                 = lineBean.getInvDueDt();
            }
        }
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.pmtTermCashDiscCd, pmtTermCashDiscCd);
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.pmtTermCd,         pmtTermCd);
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.pmtTermCashDiscDescTxt, pmtTermCashDiscDescTxt);
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.dueDt,             dueDate);

        ZYPEZDItemValueSetter.setValue(conslBillTMsg.cratPgmId,         CRAT_PGM_ID);
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.cratPsnCd,         CRAT_PSN_CD);
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.cratDt,            slsDt);

        //aggregate amount of same pk
        BigDecimal conslTotDealNetAmt = BigDecimal.ZERO;
        BigDecimal conslTotFuncNetAmt = BigDecimal.ZERO;
        BigDecimal conslDueTotDealNetAmt = BigDecimal.ZERO;
        BigDecimal conslDueTotFuncNetAmt = BigDecimal.ZERO;
        for (ConslBillBean lineBean : beanList) {
            if (!bean.getConslBillPk().equals(lineBean.getConslBillPk())) {
                continue;
            }
            BigDecimal invTotDealNetAmt = lineBean.getInvTotDealNetAmt();
            BigDecimal invTotFuncNetAmt = lineBean.getInvTotFuncNetAmt();
            BigDecimal dealRmngBalGrsAmt = lineBean.getDealRmngBalGrsAmt();
            BigDecimal funcRmngBalGrsAmt = lineBean.getFuncRmngBalGrsAmt();

            if (dealRmngBalGrsAmt == null) {
                dealRmngBalGrsAmt = invTotDealNetAmt;
            }
            if (funcRmngBalGrsAmt == null) {
                funcRmngBalGrsAmt = invTotFuncNetAmt;
            }

            conslTotDealNetAmt    = conslTotDealNetAmt.add(invTotDealNetAmt);
            conslTotFuncNetAmt    = conslTotFuncNetAmt.add(invTotFuncNetAmt);
            conslDueTotDealNetAmt = conslDueTotDealNetAmt.add(dealRmngBalGrsAmt);
            conslDueTotFuncNetAmt = conslDueTotFuncNetAmt.add(funcRmngBalGrsAmt);
        }
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.conslTotDealNetAmt,    conslTotDealNetAmt);
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.conslTotFuncNetAmt,    conslTotFuncNetAmt);
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.conslDueTotDealNetAmt, conslDueTotDealNetAmt);
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.conslDueTotFuncNetAmt, conslDueTotFuncNetAmt);

        //CUST_INV_SRC_CD
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.custInvSrcCd, bean.getCustInvSrcCd());
        //INV_GRP_NUM
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.invGrpNum, "1");
        //EASY_PAC_FLG
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.easyPacFlg, ZYPConstant.FLG_OFF_N);

        EZDTBLAccessor.insert(conslBillTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(conslBillTMsg.getReturnCode())) {
            String msgId = MSG_ID.NWCM0109E.toString();
            String val = "billToCustCd:" + bean.getBillToCustCd() + ",conslBillPk:" + bean.getConslBillPk();
            String[] valArray = toArray(conslBillTMsg.getTableName(), val);
            S21InfoLogOutput.println(msgId, valArray);
            errInfoList.add(bean.getErrInfo(S21MessageFunc.clspGetMessage(msgId, valArray)));
            return false;
        }
        return true;
    }

    private boolean insertConslBillGrp(ConslBillBean bean) {

        CONSL_BILL_GRPTMsg conslBillGrpTMsg = new CONSL_BILL_GRPTMsg();
        ZYPEZDItemValueSetter.setValue(conslBillGrpTMsg.glblCmpyCd,        glblCmpyCd);
        BigDecimal grpSeq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CONSL_BILL_GRP_SQ);
        ZYPEZDItemValueSetter.setValue(conslBillGrpTMsg.conslBillGrpPk,    grpSeq);
        ZYPEZDItemValueSetter.setValue(conslBillGrpTMsg.conslBillPk,       bean.getConslBillPk());
        // DEF_INV_GRP_CD, DEF_INV_GRP_TXT is Not set.

        EZDTBLAccessor.insert(conslBillGrpTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(conslBillGrpTMsg.getReturnCode())) {
            String msgId = MSG_ID.NWCM0109E.toString();
            String val = "billToCustCd:" + bean.getBillToCustCd() + ",conslBillPk:" + bean.getConslBillPk();
            String[] valArray = toArray(conslBillGrpTMsg.getTableName(), val);
            S21InfoLogOutput.println(msgId, valArray);
            errInfoList.add(bean.getErrInfo(S21MessageFunc.clspGetMessage(msgId, valArray)));
            return false;
        }
        return true;
    }

    private boolean insertConslBillLine(ConslBillBean bean, String conslBillLineNum) {

        CONSL_BILL_LINETMsg conslBillLineTMsg = new CONSL_BILL_LINETMsg();
        ZYPEZDItemValueSetter.setValue(conslBillLineTMsg.glblCmpyCd,            glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(conslBillLineTMsg.conslBillPk,           bean.getConslBillPk());
        ZYPEZDItemValueSetter.setValue(conslBillLineTMsg.conslBillLineNum,      conslBillLineNum);
        ZYPEZDItemValueSetter.setValue(conslBillLineTMsg.conslBillTrxId,        bean.getInvNum());
        ZYPEZDItemValueSetter.setValue(conslBillLineTMsg.conslBillTrxNum,       bean.getInvNum());
        ZYPEZDItemValueSetter.setValue(conslBillLineTMsg.conslBillTrxTpCd,      bean.getInvTpCd());
        ZYPEZDItemValueSetter.setValue(conslBillLineTMsg.conslBillTrxDt,        bean.getInvDt());

        ZYPEZDItemValueSetter.setValue(conslBillLineTMsg.conslTotDealNetAmt,    bean.getInvTotDealNetAmt());
        ZYPEZDItemValueSetter.setValue(conslBillLineTMsg.conslTotFuncNetAmt,    bean.getInvTotFuncNetAmt());

        BigDecimal dealRmngBalGrsAmt = bean.getDealRmngBalGrsAmt();
        BigDecimal funcRmngBalGrsAmt = bean.getFuncRmngBalGrsAmt();
        if (dealRmngBalGrsAmt == null) {
            dealRmngBalGrsAmt = bean.getInvTotDealNetAmt();
        }
        if (funcRmngBalGrsAmt == null) {
            funcRmngBalGrsAmt = bean.getInvTotFuncNetAmt();
        }

        ZYPEZDItemValueSetter.setValue(conslBillLineTMsg.conslDueTotDealNetAmt, dealRmngBalGrsAmt);
        ZYPEZDItemValueSetter.setValue(conslBillLineTMsg.conslDueTotFuncNetAmt, funcRmngBalGrsAmt);

        EZDTBLAccessor.insert(conslBillLineTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(conslBillLineTMsg.getReturnCode())) {
            String msgId = MSG_ID.NWCM0109E.toString();
            String val = "billToCustCd:" + bean.getBillToCustCd() + ",conslBillPk:" + bean.getConslBillPk()
                       + ",consolBillLineNum:" + conslBillLineNum + ",invNum:" + bean.getInvNum();
            String[] valArray = toArray(conslBillLineTMsg.getTableName(), val);
            S21InfoLogOutput.println(msgId, valArray);
            errInfoList.add(bean.getErrInfo(S21MessageFunc.clspGetMessage(msgId, valArray)));

            return false;
        }

        return true;
    }

    private void setConslBillPk(List<ConslBillBean> beanList) {

        for (ConslBillBean bean : beanList) {

            BigDecimal conslBillPk = bean.getConslBillPk();
            if (conslBillPk == null) {
                conslBillPk = new BigDecimal(ZYPMaxTenDigitsNumbering.getUniqueID(this.glblCmpyCd, CONST_AUTO_SEQ_CD_CONSL_BILL_NUM));
                bean.setConslBillPk(conslBillPk);

                // set same pk
                for (ConslBillBean bean2 : beanList) {
                    if (bean2.getConslBillPk() == null
                     && bean.getTempEttlNum().equals(bean2.getTempEttlNum())) {
                        bean2.setConslBillPk(conslBillPk);
                    }
                }
            }
        }
    }

    private String[] toArray(String ...appendMsgList) {
        String[] msgArray = new String[appendMsgList.length];
        for (int i = 0; i < msgArray.length; i++) {
            msgArray[i] = appendMsgList[i];
        }
        return msgArray;
    }

}
