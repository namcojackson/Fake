/**
 * <Pre>Copyright(c)2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWC.NWCB009001;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.CONSL_BILLTMsg;
import business.db.CONSL_BILL_GRPTMsg;
import business.db.CONSL_BILL_LINETMsg;
import business.db.CUST_CR_PRFLTMsg;
import business.db.CUST_CR_PRFLTMsgArray;
import business.db.DS_ACCT_CR_PRFLTMsg;
import business.db.INVTMsg;
import business.db.AR_TRX_BALTMsg;
import business.db.AR_TRX_BALTMsgArray;
import business.db.PMT_TERM_CASH_DISCTMsg;
import business.parts.NFZC309001PMsg;
import business.parts.NMZC610001PMsg;
import business.parts.NMZC610001_InvoiceRuleListPMsg;

import com.canon.cusa.s21.api.NFC.NFZC309001.NFZC309001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001Mail;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001MailSubstituteString;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONSL_PRINT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONSL_RGNR_PROC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONSL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CUST_BLLG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DEF_INV_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PMT_TERM_CASH_DISC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPMaxTenDigitsNumbering;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * Invoice Consolidation Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/15   Fujitsu         H.Nagashima     Create          N/A
 * 2016/04/15   Fujitsu         H.Nagashima     Update          QC#7000
 * 2016/08/16   SRAA            K.Aratani       Update          QC#13107
 * 2017/06/13   Fujitsu         A.Kosai         Update          QC#18632
 * 2017/11/01   Fujitsu         H.Ikeda         Update          QC#20141
 * 2017/11/22   SRAA            K.Aratani       Update          QC#22278
 * 2018/05/15   Fujitsu         H.Nagashima     Update          QC#23604
 * 2018/06/25   Hitachi         U.Kim           Update          QC#26703
 * 2018/08/14   Fujitsu         T.Aoi           Update          QC#27443
 * 2018/11/06   Hitachi         T.Tomita        Update          QC#28627
 * 2021/04/13   CITS            K.Ogino         Update          QC#57811
 * 2022/09/09   Hitachi         S.Naya          Update          QC#60140
 * 2022/11/29   Hitachi         S.Nakatani      Update          QC#60140
 *</pre>
 */
public class NWCB009001 extends S21BatchMain {

    /**
     *  Message IDs
     * @author Q09081
     *
     */
    private static enum MSG_ID {
        /** [@] is mandatory. */
        ZZZM9025E
        /** @ ended abnormally. */
        , NLBM0024E
        /** It failed to register @ Table. */
        , NWCM0109E
        /** It failed to update @ Table. */
        , NWCM0110E
        /** It failed to get [@].(@) */
        , NWCM0112E
        /** Could not get the "@" */
        , NWCM0115E
    }

    /** Program Name */
    private static final String PROGRAM_NM = "Invoice Consolidation Batch";

    /** Create Program ID */
    private static final String CRAT_PGM_ID = "NWCB009001";

    /** Create Person CD */
    private static final String CRAT_PSN_CD = "NWCB0090";

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

    /** previous month last date */
    private String endOfPrevMonth = null;

    /** Mail template ID */
    private  String mailTemplateId = null;

    /** error Info List */
    private List<String> errInfoList = new ArrayList<String>();

    /** digit consolidated bill line number */
    private static final int DIGIT_CONSL_BILL_LINE_NUM = new CONSL_BILL_LINETMsg().getAttr("conslBillLineNum").getDigit();

    /** end of month payment term cash discount code */
    private static final String CONST_KEY_EOM_PMT_TERM_CASH_DISC_CD = "EOM_PMT_TERM_CASH_DISC_CD";

    /** Order Category Context Type (EASY_PACK1) */
    private static final String CONST_ORD_CATG_CTX_TP_CD_EASY_PACK1 = "EASY_PACK1";

    // 2017/06/13 QC#18632 Add Start
    /** Automatic Sequence Number Code (Consolidated Bill Number) */
    private static final String CONST_AUTO_SEQ_CD_CONSL_BILL_NUM = "CONSL_BILL_NUM";
    // 2017/06/13 QC#18632 Add End

    /** Line Break */
    private static final String CONST_LINE_BREAK = "\r\n";

    /** cache map */
    private S21LRUMap<String, Object> cache = new S21LRUMap<String, Object>();

    /**
     * main process
     * @param args String[] batch parameters
     */
    public static void main(String[] args) {
        new NWCB009001().executeBatch();
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
        slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);

        // end of previous month
        endOfPrevMonth = getEndOfMonth(slsDt, -1);

    }

    @Override
    protected void mainRoutine() {

        // Search Target Data
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd",      this.glblCmpyCd);
        ssmParam.put("slsDt",           this.slsDt);
        ssmParam.put("conslRgnrProcCd", CONSL_RGNR_PROC.UN_PROCESSED);
        ssmParam.put("ordCatgCtxTpCdEasyPAC",  CONST_ORD_CATG_CTX_TP_CD_EASY_PACK1);
        ssmParam.put("excludeSysSrcCd", SYS_SRC.S21_ACCOUNTING_AR);
        ssmParam.put("easyPacInvDt",    endOfPrevMonth);

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

        boolean isNormalEnd = new NWXC001001Mail().postMail(glblCmpyCd, "NWCB0090", mailTemplateId, sbsStrList);

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
            // Add Start 2018/11/06 QC#28627
            String prevContrLinkNum = null;
            // Add End 2018/11/06 QC#28627
            String prevBillToCustCd = null;
            String prevEasyPacFlg = null;
            List<String> invGrpNumList = new ArrayList<String>();
            List<ConslBillBean> beanList = new ArrayList<ConslBillBean>();
            PMT_TERM_CASH_DISCTMsg easyPacPmtTermCashDiscTMsg = null;
            List<String> errKey = new ArrayList<String>();
            // QC#7000 2016/04/15 add Start
            List<String> invNumList = new ArrayList<String>();
            // QC#7000 2016/04/15 add End

            do {
                ConslBillBean bean = new ConslBillBean();
                bean.setResult(rs);
                // Add Start 2018/11/06 QC#28627
                String contrLinkNum = bean.getContrLinkNum();
                // Add End 2018/11/06 QC#28627
                String billToCustCd = bean.getBillToCustCd();
                String easyPacFlg   = bean.getEasyPackFlg();

                if (errKey.contains(bean.getKey())) {
                    continue;
                }

                // QC#57811
                if (ZYPConstant.FLG_OFF_N.equals(rs.getString("CONS_BILL_STATUS"))) {
                    continue;
                }

                // Mod Start 2018/11/06 QC#28627
//                if ((prevEasyPacFlg != null && !easyPacFlg.equals(prevEasyPacFlg)) || (prevBillToCustCd != null && !billToCustCd.equals(prevBillToCustCd))) {
                if ((prevContrLinkNum != null && !prevContrLinkNum.equals(contrLinkNum))
                        || (prevEasyPacFlg != null && !easyPacFlg.equals(prevEasyPacFlg))
                        || (prevBillToCustCd != null && !billToCustCd.equals(prevBillToCustCd))) {
                // Mod End 2018/11/06 QC#28627
//                    if (!createConslBill(beanList, invGrpNumList)) {
                    if (!createConslBill(beanList, invNumList, invGrpNumList)) {
                        errRecCnt +=  beanList.size();
                        rollback();
                    } else {
                        normalRecCnt += beanList.size();
                        commit();
                    }
                    beanList = new ArrayList<ConslBillBean>();
                    invGrpNumList = new ArrayList<String>();
                    // QC#7000 2016/04/15 add Start
                    invNumList = new ArrayList<String>();
                    // QC#7000 2016/04/15 add End
                }

                // Mod Start 2018/11/06 QC#28627
//                if (ZYPConstant.FLG_OFF_N.equals(bean.getEasyPackFlg())) {
                if (ZYPCommonFunc.hasValue(contrLinkNum)) {
                    PMT_TERM_CASH_DISCTMsg pmtTermCashDiscTMsg = getPmtTermCashDisc(bean);
                    if (pmtTermCashDiscTMsg == null) {
                        // skip this key
                        errKey.add(bean.getKey());
                        continue;
                    }
                    bean.setPmtTermCashDiscCd(pmtTermCashDiscTMsg.pmtTermCashDiscCd.getValue());
                    bean.setPmtTermCd(pmtTermCashDiscTMsg.pmtTermCd.getValue());
                    bean.setPmtTermCashDiscDescTxt(pmtTermCashDiscTMsg.pmtTermCashDiscDescTxt.getValue());
                    bean.setPmtTermConslLastDomFlg(pmtTermCashDiscTMsg.pmtTermConslLastDomFlg.getValue());
                    bean.setPmtTermConslDueDay(pmtTermCashDiscTMsg.pmtTermConslDueDay.getValue());

                } else if (ZYPConstant.FLG_OFF_N.equals(bean.getEasyPackFlg())) {
                // Mod End 2018/11/06 QC#28627
                    // get customer info
                    NMZC610001PMsg custInfoPMsg = getCustomerInfo(bean);
                    if (custInfoPMsg == null) {
                        // skip this key
                        errKey.add(bean.getKey());
                        continue;
                    }

                    NMZC610001_InvoiceRuleListPMsg invoiceRule = custInfoPMsg.InvoiceRuleList.no(0);
                    bean.setInvGrpNum(invoiceRule.invGrpNum.getValue());
                    bean.setDefInvGrpCd(invoiceRule.defInvGrpCd.getValue());

//                    bean.setPmtTermCashDiscCd(invoiceRule.custConslTermCd.getValue());
                    bean.setCustConslTermCd(invoiceRule.custConslTermCd.getValue());    //QC#23604 mod
                    //QC#23604 move
//                    PMT_TERM_CASH_DISCTMsg pmtTermCashDiscTMsg = getPmtTermCashDisc(bean);
//                    if (pmtTermCashDiscTMsg == null) {
//                        // skip this key
//                        errKey.add(bean.getKey());
//                        continue;
//                    }
//                    bean.setPmtTermCd(pmtTermCashDiscTMsg.pmtTermCd.getValue());
//                    bean.setPmtTermCashDiscDescTxt(pmtTermCashDiscTMsg.pmtTermCashDiscDescTxt.getValue());
//                    bean.setPmtTermConslLastDomFlg(pmtTermCashDiscTMsg.pmtTermConslLastDomFlg.getValue());
//                    bean.setPmtTermConslDueDay(pmtTermCashDiscTMsg.pmtTermConslDueDay.getValue());

                    if (!CUST_BLLG_TP.CONSOLIDATED.equals(invoiceRule.custBllgTpCd.getValue())) {
                        //skip this record
                        continue;
                    }

                    //QC#23604 add Start
//                    if (PMT_TERM_CASH_DISC.DAILY.equals(bean.getCustConslTermCd())) {
//                        if (!DS_CONTR_CATG.REGULAR.equals(bean.getDsContrCatgCd())) {
//                            if (DEF_INV_GRP.IB_CONTROL_FIELDS.equals(bean.getDefInvGrpCd())) {
//                                //skip this record
//                                continue;
//                            }
//                        }
//                    } else {
                    if (!PMT_TERM_CASH_DISC.DAILY.equals(bean.getCustConslTermCd())) {
                        bean.setPmtTermCashDiscCd(invoiceRule.custConslTermCd.getValue());

                        PMT_TERM_CASH_DISCTMsg pmtTermCashDiscTMsg = getPmtTermCashDisc(bean);
                        if (pmtTermCashDiscTMsg == null) {
                            // skip this key
                            errKey.add(bean.getKey());
                            continue;
                        }
                        bean.setPmtTermCd(pmtTermCashDiscTMsg.pmtTermCd.getValue());
                        bean.setPmtTermCashDiscDescTxt(pmtTermCashDiscTMsg.pmtTermCashDiscDescTxt.getValue());
                        bean.setPmtTermConslLastDomFlg(pmtTermCashDiscTMsg.pmtTermConslLastDomFlg.getValue());
                        bean.setPmtTermConslDueDay(pmtTermCashDiscTMsg.pmtTermConslDueDay.getValue());
                    //QC#23604 add End

                        // judge sales date
                        if (ZYPConstant.FLG_ON_Y.equals(bean.getPmtTermConslLastDomFlg())) {
                            //end of month
                            String lastDt = getEndOfMonth(slsDt, 0);
                            if (!slsDt.equals(lastDt)) {
                                //skip this record
                                continue;
                            }

                        } else {
                            String day = ZYPDateUtil.DateFormatter(slsDt, "yyyyMMdd", "dd");
                            if (!day.equals(bean.getPmtTermConslDueDay())) {
                                //skip this record
                                continue;
                            }
                        }
                    } //QC#23604

                    // START 2022/11/29 S.Nakatani [QC#60140,ADD]
                    String paymentTermFlag = getPaymentTermFlag(bean);
                    if ("N".equals(paymentTermFlag)) {
                        String pmtTermCashDiscCd = getDsAcctCrPrfl(null, bean.getBillToCustAcctCd());
                        if (ZYPCommonFunc.hasValue(pmtTermCashDiscCd)) {
                            bean.setPmtTermCashDiscCd(pmtTermCashDiscCd);
                            PMT_TERM_CASH_DISCTMsg pmtTermCashDiscTMsg = getPmtTermCashDisc(bean);
                            if (pmtTermCashDiscTMsg == null) {
                                // skip this key
                                errKey.add(bean.getKey());
                                continue;
                            }
                            bean.setPmtTermCd(pmtTermCashDiscTMsg.pmtTermCd.getValue());
                            bean.setPmtTermCashDiscDescTxt(pmtTermCashDiscTMsg.pmtTermCashDiscDescTxt.getValue());
                            bean.setPmtTermConslLastDomFlg(pmtTermCashDiscTMsg.pmtTermConslLastDomFlg.getValue());
                            bean.setPmtTermConslDueDay(pmtTermCashDiscTMsg.pmtTermConslDueDay.getValue());
                        }
                    }
                    // END 2022/11/29 S.Nakatani [QC#60140,ADD]

                } else {
                    //easy pac
                    if (easyPacPmtTermCashDiscTMsg == null) {
                        String eomPmtTermCashDiscCd = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_EOM_PMT_TERM_CASH_DISC_CD, glblCmpyCd);
                        if (eomPmtTermCashDiscCd == null) {
                            eomPmtTermCashDiscCd = PMT_TERM_CASH_DISC.END_OF_MONTH;
                        }
                        // QC#20141 2017/11/01 add Start
                        eomPmtTermCashDiscCd = getEomPmtTermCashDiscCd(eomPmtTermCashDiscCd, bean);
                        // QC#20141 2017/11/01 add End

                        bean.setPmtTermCashDiscCd(eomPmtTermCashDiscCd);

                        easyPacPmtTermCashDiscTMsg = getPmtTermCashDisc(bean);
                        if (easyPacPmtTermCashDiscTMsg == null) {
                            String msgId = MSG_ID.NWCM0112E.toString();
                            String[] val = toArray("PMT_TERM_CASH_DISC", eomPmtTermCashDiscCd);
                            S21InfoLogOutput.println(msgId, val);
                            errInfoList.add(bean.getErrInfo(S21MessageFunc.clspGetMessage(msgId, val)));
                            continue;
                        }
                    }
                    bean.setPmtTermCashDiscCd(easyPacPmtTermCashDiscTMsg.pmtTermCashDiscCd.getValue());
                    bean.setPmtTermCd(easyPacPmtTermCashDiscTMsg.pmtTermCd.getValue());
                    bean.setPmtTermCashDiscDescTxt(easyPacPmtTermCashDiscTMsg.pmtTermCashDiscDescTxt.getValue());
                }

                beanList.add(bean);
                // QC#7000 2016/04/15 add Start
                invNumList.add(bean.getInvNum());
                // QC#7000 2016/04/15 add End

                String invGrpNum = bean.getInvGrpNum();
                if (ZYPCommonFunc.hasValue(invGrpNum)) {
                    if (!invGrpNumList.contains(invGrpNum)) {
                        invGrpNumList.add(invGrpNum);
                    }
                }

                // Add Start 2018/11/06 QC#28627
                prevContrLinkNum = contrLinkNum;
                // Add End 2018/11/06 QC#28627
                prevBillToCustCd = billToCustCd;
                prevEasyPacFlg = easyPacFlg;

            } while (rs.next());

            if (!beanList.isEmpty()) {
//                if (!createConslBill(beanList, invGrpNumList)) {
                if (!createConslBill(beanList, invNumList, invGrpNumList)) {
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

    // QC#20141 2017/11/01 add Start
    private String getEomPmtTermCashDiscCd(String eomPmtTermCashDiscCd, ConslBillBean bean) {
        CUST_CR_PRFLTMsgArray custCrPrflList = getCustCrPrfl(bean.getBillToCustCd());
        if (custCrPrflList.length() > 0 && ZYPCommonFunc.hasValue(custCrPrflList.no(0).pmtTermCashDiscCd)) {
            if (ZYPCommonFunc.hasValue(custCrPrflList.no(0).pmtTermCashDiscCd.getValue())) {
                return custCrPrflList.no(0).pmtTermCashDiscCd.getValue();
            }
        }

        return getDsAcctCrPrfl(eomPmtTermCashDiscCd, bean.getBillToCustAcctCd());
    }

    private CUST_CR_PRFLTMsgArray getCustCrPrfl(String billToCustCd) {
        CUST_CR_PRFLTMsg tMsg = new CUST_CR_PRFLTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        tMsg.setConditionValue("billToCustCd01", billToCustCd);
        return (CUST_CR_PRFLTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
    }

    private String getDsAcctCrPrfl(String eomPmtTermCashDiscCd, String billToCustAcctCd) {
        DS_ACCT_CR_PRFLTMsg dsAcctCrPrfl = new DS_ACCT_CR_PRFLTMsg();
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrfl.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrfl.dsAcctNum, billToCustAcctCd);
        dsAcctCrPrfl = (DS_ACCT_CR_PRFLTMsg) S21ApiTBLAccessor.findByKey(dsAcctCrPrfl);
        if (dsAcctCrPrfl != null) {
            eomPmtTermCashDiscCd = dsAcctCrPrfl.pmtTermCashDiscCd.getValue();
        }
        return eomPmtTermCashDiscCd;
    }
    // QC#20141 2017/11/01 add End

//    private boolean createConslBill(List<ConslBillBean> beanList, List<String> invGrpNumList) {
    private boolean createConslBill(List<ConslBillBean> beanList, List<String> invNumList, List<String> invGrpNumList) {

        // QC#7000 2016/04/15 add Start
        // Credit Memo Exclusion
        beanList = excludeCreditMemoOnlyData(beanList, invNumList);
        if (beanList == null) {
            return false;

        } else if (beanList.isEmpty()) {
            return true;
        }
        // QC#7000 2016/04/15 add End

        // bill# grouping and set conslBillPk
        setConslBillPk(beanList, invGrpNumList);

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
                //QC#13107
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

//        ZYPEZDItemValueSetter.setValue(conslBillTMsg.pmtTermCashDiscCd, bean.getPmtTermCashDiscCd());
//        ZYPEZDItemValueSetter.setValue(conslBillTMsg.pmtTermCd,         bean.getPmtTermCd());
//        ZYPEZDItemValueSetter.setValue(conslBillTMsg.pmtTermCashDiscDescTxt, bean.getPmtTermCashDiscDescTxt());

        // cutoff date
        if (ZYPConstant.FLG_OFF_N.equals(bean.getEasyPackFlg())) {
            ZYPEZDItemValueSetter.setValue(conslBillTMsg.ctoffDt,       slsDt);
        } else {
            ZYPEZDItemValueSetter.setValue(conslBillTMsg.ctoffDt,       endOfPrevMonth);
        }

        // due date
        //QC#23604 mod Start
//        String dueDate = getDueDate(bean);
//        if (dueDate == null) {
//            return false;
//        }
        String pmtTermCashDiscCd = null;
        String pmtTermCd = null;
        String pmtTermCashDiscDescTxt = null;
        String dueDate = null;
        // START 2018/06/25 U.Kim [QC#26703,MOD]
        // if (DEF_INV_GRP.IB_CONTROL_FIELDS.equals(bean.getDefInvGrpCd())) {
        if (checkIBContrlField(bean.getDefInvGrpCd())) {
        // END 2018/06/25 U.Kim [QC#26703,MOD]
            //minimum dueDate
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

        } else {
            pmtTermCashDiscCd       = bean.getPmtTermCashDiscCd();
            pmtTermCd               = bean.getPmtTermCd();
            pmtTermCashDiscDescTxt  = bean.getPmtTermCashDiscDescTxt();
            dueDate                 = getDueDate(bean);
            if (dueDate == null) {
                return false;
            }
        }
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.pmtTermCashDiscCd, pmtTermCashDiscCd);
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.pmtTermCd,         pmtTermCd);
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.pmtTermCashDiscDescTxt, pmtTermCashDiscDescTxt);
        //QC#23604 mod End
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

            if (INV_TP.CREDIT_MEMO.equals(lineBean.getInvTpCd())) {
                invTotDealNetAmt = invTotDealNetAmt.negate();
                invTotFuncNetAmt = invTotFuncNetAmt.negate();
            }
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

        //QC#13107
        //CUST_INV_SRC_CD
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.custInvSrcCd, bean.getCustInvSrcCd());
        //INV_GRP_NUM
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.invGrpNum, bean.getInvGrpNum());
        //EASY_PAC_FLG
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.easyPacFlg, bean.getEasyPackFlg());

        EZDTBLAccessor.insert(conslBillTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(conslBillTMsg.getReturnCode())) {
            String msgId = MSG_ID.NWCM0109E.toString();
            String val = "billToCustCd:" + bean.getBillToCustCd() + ",conslBillPk:" + bean.getConslBillPk();
            String[] valArray = toArray(conslBillTMsg.getTableName(), val);
            S21InfoLogOutput.println(msgId, valArray);
            errInfoList.add(bean.getErrInfo(S21MessageFunc.clspGetMessage(msgId, valArray)));
            return false;
        }
        // START 2022/09/09 S.Naya [QC#60140,ADD]
        for (ConslBillBean lineBean : beanList) {
            if (!bean.getConslBillPk().equals(lineBean.getConslBillPk())) {
                continue;
            }
            if (!ZYPCommonFunc.hasValue(bean.getContrLinkNum()) && ZYPConstant.FLG_OFF_N.equals(bean.getEasyPackFlg())) {
                // Reflect DueDate to INV.NET_DUE_DT
                if (!updateInvDueDate(dueDate, lineBean.getInvNum())) {
                    return false;
                }
                // Reflect DueDate to AR_TRX_VAL.NET_DUE_DT
                if (!updateArTrxBalDueDate(dueDate, lineBean.getInvNum())) {
                    return false;
                }
            }
        }
        // END   2022/09/09 S.Naya [QC#60140,ADD]
        return true;
    }

    //QC#13107
    private boolean insertConslBillGrp(ConslBillBean bean) {

        List<String> defInvGrpCdList = bean.getDefInvGrpCdList();
        if (defInvGrpCdList == null) {
            return true;
        }
        for (String defInvGrpCd : defInvGrpCdList) {
            CONSL_BILL_GRPTMsg conslBillGrpTMsg = new CONSL_BILL_GRPTMsg();
            ZYPEZDItemValueSetter.setValue(conslBillGrpTMsg.glblCmpyCd,        glblCmpyCd);
            BigDecimal grpSeq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CONSL_BILL_GRP_SQ);
            ZYPEZDItemValueSetter.setValue(conslBillGrpTMsg.conslBillGrpPk,    grpSeq);
            ZYPEZDItemValueSetter.setValue(conslBillGrpTMsg.conslBillPk,       bean.getConslBillPk());
            //DEF_INV_GRP_CD
            ZYPEZDItemValueSetter.setValue(conslBillGrpTMsg.defInvGrpCd,       defInvGrpCd);
            //DEF_INV_GRP_TXT
            if (DEF_INV_GRP.PO_NUM.equals(defInvGrpCd)) {
                ZYPEZDItemValueSetter.setValue(conslBillGrpTMsg.defInvGrpTxt,   bean.getCustIssPoNum());
            } else if (DEF_INV_GRP.CONTRACT_NUM.equals(defInvGrpCd)) {
                ZYPEZDItemValueSetter.setValue(conslBillGrpTMsg.defInvGrpTxt,   bean.getDsContrNum());
            } else if (DEF_INV_GRP.BILLING_PERIOD.equals(defInvGrpCd)) {
                ZYPEZDItemValueSetter.setValue(conslBillGrpTMsg.defInvGrpTxt,   bean.getBllgPer());
            } else if (DEF_INV_GRP.SERIAL_NUM.equals(defInvGrpCd)) {
                ZYPEZDItemValueSetter.setValue(conslBillGrpTMsg.defInvGrpTxt,   bean.getSerNum());
            } else if (DEF_INV_GRP.MODEL.equals(defInvGrpCd)) {
                ZYPEZDItemValueSetter.setValue(conslBillGrpTMsg.defInvGrpTxt,   bean.getMdlNm());
//QC#23604 add Start
            // START 2018/06/25 U.Kim [QC#26703,MOD]
            // } else if (DEF_INV_GRP.IB_CONTROL_FIELDS.equals(defInvGrpCd)) {
            //     if (DS_CONTR_CATG.REGULAR.equals(bean.getDsContrCatgCd())) {
            //         ZYPEZDItemValueSetter.setValue(conslBillGrpTMsg.defInvGrpTxt,   bean.getIbControlFields());
            //     } else {
            //         // not consolidate(fleet,aggregate)
            //         ZYPEZDItemValueSetter.setValue(conslBillGrpTMsg.defInvGrpTxt,   bean.getInvNum());
            //     }
            } else if (checkIBContrlField(defInvGrpCd)) {
                if (DS_CONTR_CATG.REGULAR.equals(bean.getDsContrCatgCd())) {
                    if (DEF_INV_GRP.IB_CONTROL_FIELDS1.equals(defInvGrpCd)) {
                        ZYPEZDItemValueSetter.setValue(conslBillGrpTMsg.defInvGrpTxt, bean.getFirstBllgAttrbValTxt());
                    } else if (DEF_INV_GRP.IB_CONTROL_FIELDS2.equals(defInvGrpCd)) {
                        ZYPEZDItemValueSetter.setValue(conslBillGrpTMsg.defInvGrpTxt, bean.getScdBllgAttrbValTxt());
                    } else if (DEF_INV_GRP.IB_CONTROL_FIELDS3.equals(defInvGrpCd)) {
                        ZYPEZDItemValueSetter.setValue(conslBillGrpTMsg.defInvGrpTxt, bean.getThirdBllgAttrbValTxt());
                    } else if (DEF_INV_GRP.IB_CONTROL_FIELDS4.equals(defInvGrpCd)) {
                        ZYPEZDItemValueSetter.setValue(conslBillGrpTMsg.defInvGrpTxt, bean.getFrthBllgAttrbValTxt());
                    } else if (DEF_INV_GRP.IB_CONTROL_FIELDS5.equals(defInvGrpCd)) {
                        ZYPEZDItemValueSetter.setValue(conslBillGrpTMsg.defInvGrpTxt, bean.getFifthBllgAttrbValTxt());
                    } else if (DEF_INV_GRP.IB_CONTROL_FIELDS6.equals(defInvGrpCd)) {
                        ZYPEZDItemValueSetter.setValue(conslBillGrpTMsg.defInvGrpTxt, bean.getSixthBllgAttrbValTxt());
                    }
                } else {
                    // not consolidate(fleet,aggregate)
                    ZYPEZDItemValueSetter.setValue(conslBillGrpTMsg.defInvGrpTxt,   bean.getInvNum());
                }
            // END 2018/06/25 U.Kim [QC#26703,MOD]
//QC#23604 add End
            }
            EZDTBLAccessor.insert(conslBillGrpTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(conslBillGrpTMsg.getReturnCode())) {
                String msgId = MSG_ID.NWCM0109E.toString();
                String val = "billToCustCd:" + bean.getBillToCustCd() + ",conslBillPk:" + bean.getConslBillPk();
                String[] valArray = toArray(conslBillGrpTMsg.getTableName(), val);
                S21InfoLogOutput.println(msgId, valArray);
                errInfoList.add(bean.getErrInfo(S21MessageFunc.clspGetMessage(msgId, valArray)));
                return false;
            }
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
            if (INV_TP.CREDIT_MEMO.equals(bean.getInvTpCd())) {
                dealRmngBalGrsAmt = dealRmngBalGrsAmt.negate();
            }
        }
        if (funcRmngBalGrsAmt == null) {
            funcRmngBalGrsAmt = bean.getInvTotFuncNetAmt();
            if (INV_TP.CREDIT_MEMO.equals(bean.getInvTpCd())) {
                funcRmngBalGrsAmt = funcRmngBalGrsAmt.negate();
            }
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

    // START 2022/09/09 S.Naya [QC#60140,ADD]
    private boolean updateInvDueDate(String dueDate, String invNum) {

        boolean isSuccess = true;

        INVTMsg invTMsg = new INVTMsg();
        ZYPEZDItemValueSetter.setValue(invTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(invTMsg.invNum, invNum);
        invTMsg = (INVTMsg) EZDTBLAccessor.findByKey(invTMsg);

        if (EZDTBLAccessor.RTNCD_NORMAL.equals(invTMsg.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(invTMsg.netDueDt, dueDate);
            EZDTBLAccessor.update(invTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(invTMsg.getReturnCode())) {
                isSuccess = false;
                S21InfoLogOutput.println(MSG_ID.NWCM0110E.toString(), toArray(invTMsg.getTableName()));
            }
        } else {
            isSuccess = false;
            String val = "invNum:" + invNum;
            S21InfoLogOutput.println(MSG_ID.NWCM0112E.toString(), toArray(invTMsg.getTableName(), val));
        }

        return isSuccess;
    }

    private boolean updateArTrxBalDueDate(String dueDate, String invNum) {

        boolean isSuccess = true;

        AR_TRX_BALTMsg arTrxBalTMsg = new AR_TRX_BALTMsg();
        arTrxBalTMsg.setSQLID("001");
        arTrxBalTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        arTrxBalTMsg.setConditionValue("arTrxNum01", invNum);
        AR_TRX_BALTMsgArray arTrxBalTMsgArray = (AR_TRX_BALTMsgArray) EZDTBLAccessor.findByCondition(arTrxBalTMsg);

        if (arTrxBalTMsgArray.getValidCount() > 0) {
            arTrxBalTMsg = arTrxBalTMsgArray.no(0);
            ZYPEZDItemValueSetter.setValue(arTrxBalTMsg.invDueDt, dueDate);

            EZDTBLAccessor.update(arTrxBalTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(arTrxBalTMsg.getReturnCode())) {
                isSuccess = false;
                S21InfoLogOutput.println(MSG_ID.NWCM0110E.toString(), toArray(arTrxBalTMsg.getTableName()));
            }
        }

        return isSuccess;
    }
    // END   2022/09/09 S.Naya [QC#60140,ADD]

    private String getDueDate(ConslBillBean bean) {

        NFZC309001PMsg pmsg = new NFZC309001PMsg();
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd,           glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pmsg.trxDt,                slsDt);
        // pmt term cash disc cd is setting by getTargetData SQL
        ZYPEZDItemValueSetter.setValue(pmsg.pmtTermCashDiscCd,    bean.getPmtTermCashDiscCd());

        if (ZYPConstant.FLG_ON_Y.equals(bean.getEasyPackFlg())) {
            ZYPEZDItemValueSetter.setValue(pmsg.startDt,          endOfPrevMonth);
        }

        NFZC309001 dueDateCalculationAPI = new NFZC309001();
        dueDateCalculationAPI.execute(pmsg, ONBATCH_TYPE.BATCH);
        if (pmsg.xxMsgIdList.getValidCount() > 0) {
            for (int i = 0; i < pmsg.xxMsgIdList.getValidCount(); i++) {
                String msgId = pmsg.xxMsgIdList.no(i).xxMsgId.getValue();
                S21InfoLogOutput.println(msgId);
                errInfoList.add(bean.getErrInfo(S21MessageFunc.clspGetMessage(msgId)));
            }
            return null;
        }

        return pmsg.dueDt.getValue();
    }

    private void setConslBillPk(List<ConslBillBean> beanList, List<String> invGrpNumList) {

        setGroupingKey(beanList, invGrpNumList);

        for (ConslBillBean bean : beanList) {

            String grpKeyStr = bean.getGrpKeyStr();
            BigDecimal conslBillPk = bean.getConslBillPk();
            if (conslBillPk == null) {
                // 2017/06/13 QC#18632 Mod Start
//                conslBillPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CONSL_BILL_SQ);
                conslBillPk = new BigDecimal(ZYPMaxTenDigitsNumbering.getUniqueID(this.glblCmpyCd, CONST_AUTO_SEQ_CD_CONSL_BILL_NUM));
                // 2017/06/13 QC#18632 Mod End
                bean.setConslBillPk(conslBillPk);

                // set same pk
                for (ConslBillBean bean2 : beanList) {
                    if (bean2.getConslBillPk() == null
                     && grpKeyStr.equals(bean2.getGrpKeyStr())) {
                        bean2.setConslBillPk(conslBillPk);
                    }
                }
            }
        }
    }

    private void setGroupingKey(List<ConslBillBean> beanList, List<String> invGrpNumList) {

        // create grouping key
        if (!invGrpNumList.isEmpty()) {
            // get default group cd by same invoice group#
            Map<String, List<String>> keyMap = new HashMap<String, List<String>>();
            for (String invGrpNum : invGrpNumList) {

                for (ConslBillBean bean : beanList) {
                    List<String> defInvGrpCdList = (ArrayList<String>) keyMap.get(invGrpNum);
                    if (defInvGrpCdList == null) {
                        defInvGrpCdList = new ArrayList<String>();
                    }
                    if (ZYPCommonFunc.hasValue(bean.getInvGrpNum()) && invGrpNum.equals(bean.getInvGrpNum())) {
                        if (!defInvGrpCdList.contains(bean.getDefInvGrpCd())) {
                            defInvGrpCdList.add(bean.getDefInvGrpCd());
                        }
                    }
                    keyMap.put(invGrpNum, defInvGrpCdList);
                }
            }

            // set key
            for (ConslBillBean bean : beanList) {

                StringBuffer keySb = new StringBuffer(bean.getBillToCustCd());
//                keySb.append(bean.getPmtTermCashDiscCd()).append("-");
                keySb.append(bean.getCustConslTermCd()).append("-");    //QC#23604 mod
                if (ZYPCommonFunc.hasValue(bean.getInvGrpNum())) {
                    keySb.append(bean.getInvGrpNum()).append("-");
                    for (String invGrpNum : invGrpNumList) {

                        if (!invGrpNum.equals(bean.getInvGrpNum())) {
                            continue;
                        }
                        // same invGrpNum
                        List<String> defInvGrpCdList = keyMap.get(invGrpNum);
                        //QC#13107
                        bean.setDefInvGrpCdList(defInvGrpCdList);
                        for (String defInvGrpCd : defInvGrpCdList) {
                            addDefInvGrpCd(keySb, bean, defInvGrpCd);
                        }
                    }
                } else {
                    // add default invoice group if invGrpNum is null
                    keySb.append(bean.getCustInvSrcCd()).append("-");
                    //QC#13107
                    List<String> defInvGrpCdList = new ArrayList<String>();
                    defInvGrpCdList.add(bean.getDefInvGrpCd());
                    bean.setDefInvGrpCdList(defInvGrpCdList);
                    addDefInvGrpCd(keySb, bean, bean.getDefInvGrpCd());
                }
                bean.setGrpKeyStr(keySb.toString());
            }

        } else {
            // invGrpNumList is empty
            for (ConslBillBean bean : beanList) {

                if (ZYPConstant.FLG_OFF_N.equals(bean.getEasyPackFlg())) {
                    StringBuffer keySb = new StringBuffer();
                    keySb.append(bean.getCustInvSrcCd());
                    //QC#13107
                    List<String> defInvGrpCdList = new ArrayList<String>();
                    defInvGrpCdList.add(bean.getDefInvGrpCd());
                    bean.setDefInvGrpCdList(defInvGrpCdList);
                    addDefInvGrpCd(keySb, bean, bean.getDefInvGrpCd());
                    bean.setGrpKeyStr(keySb.toString());

                } else {
                    //easy pac
                    bean.setGrpKeyStr(bean.getBillToCustCd());
                }
            }
        }
    }

    private void addDefInvGrpCd(StringBuffer sb, ConslBillBean bean, String defInvGrpCd) {

        if (DEF_INV_GRP.PO_NUM.equals(defInvGrpCd)) {
            sb.append("-").append(bean.getCustIssPoNum());
        } else if (DEF_INV_GRP.CONTRACT_NUM.equals(defInvGrpCd)) {
            sb.append("-").append(bean.getDsContrNum());
        } else if (DEF_INV_GRP.BILLING_PERIOD.equals(defInvGrpCd)) {
            sb.append("-").append(bean.getBllgPer());
        } else if (DEF_INV_GRP.SERIAL_NUM.equals(defInvGrpCd)) {
            sb.append("-").append(bean.getSerNum());
        } else if (DEF_INV_GRP.MODEL.equals(defInvGrpCd)) {
            sb.append("-").append(bean.getMdlNm());
//QC#23604 add Start
        // START 2018/06/25 U.Kim [QC#26703,MOD]
        // } else if (DEF_INV_GRP.IB_CONTROL_FIELDS.equals(defInvGrpCd)) {
        //     if (DS_CONTR_CATG.REGULAR.equals(bean.getDsContrCatgCd())) {
        //         sb.append("-").append(bean.getIbControlFields());
        //     } else {
        //         // not consolidate(fleet,aggregate)
        //         sb.append("-").append(bean.getInvNum());
        //     }
        } else if (checkIBContrlField(defInvGrpCd)) {
            if (DS_CONTR_CATG.REGULAR.equals(bean.getDsContrCatgCd())) {
                if (DEF_INV_GRP.IB_CONTROL_FIELDS1.equals(defInvGrpCd)) {
                    sb.append("-").append(bean.getFirstBllgAttrbValTxt());
                } else if (DEF_INV_GRP.IB_CONTROL_FIELDS2.equals(defInvGrpCd)) {
                    sb.append("-").append(bean.getScdBllgAttrbValTxt());
                } else if (DEF_INV_GRP.IB_CONTROL_FIELDS3.equals(defInvGrpCd)) {
                    sb.append("-").append(bean.getThirdBllgAttrbValTxt());
                } else if (DEF_INV_GRP.IB_CONTROL_FIELDS4.equals(defInvGrpCd)) {
                    sb.append("-").append(bean.getFrthBllgAttrbValTxt());
                } else if (DEF_INV_GRP.IB_CONTROL_FIELDS5.equals(defInvGrpCd)) {
                    sb.append("-").append(bean.getFifthBllgAttrbValTxt());
                } else if (DEF_INV_GRP.IB_CONTROL_FIELDS6.equals(defInvGrpCd)) {
                    sb.append("-").append(bean.getSixthBllgAttrbValTxt());
                }
            } else {
                // not consolidate(fleet,aggregate)
                sb.append("-").append(bean.getInvNum());
            }
        // END 2018/06/25 U.Kim [QC#26703,MOD]
//QC#23604 add End
        }
    }

    private NMZC610001PMsg getCustomerInfo(ConslBillBean bean) {

        String key = createCacheKey("NWZC6100", bean.getCustInvSrcCd(), bean.getBillToCustCd());
        NMZC610001PMsg pMsg = (NMZC610001PMsg) cache.get(key);

        if (pMsg != null) {
            return pMsg;
        }

        pMsg = new NMZC610001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd,     glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd,       NMZC610001Constant.PROCESS_MODE_INVOICE);
        ZYPEZDItemValueSetter.setValue(pMsg.custInvSrcCd,   bean.getCustInvSrcCd());
        ZYPEZDItemValueSetter.setValue(pMsg.billToCustCd,   bean.getBillToCustCd());

        NMZC610001 customerInfoGetApi = new NMZC610001();
        customerInfoGetApi.execute(pMsg, ONBATCH_TYPE.BATCH);

        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                String msgId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                S21InfoLogOutput.println(msgId);
                errInfoList.add(bean.getErrInfo(S21MessageFunc.clspGetMessage(msgId)));
            }
            return null;
        }

        if (pMsg.InvoiceRuleList.getValidCount() == 0) {
            String msgId = MSG_ID.NWCM0115E.toString();
            String[] val = toArray("Invoice Rule");
            S21InfoLogOutput.println(msgId, val);
            errInfoList.add(bean.getErrInfo(S21MessageFunc.clspGetMessage(msgId, val)));
            return null;
        }

        cache.put(key, pMsg);

        return pMsg;
    }

    private PMT_TERM_CASH_DISCTMsg getPmtTermCashDisc(ConslBillBean bean) {

        PMT_TERM_CASH_DISCTMsg pmtTermCashDiscTMsg = new PMT_TERM_CASH_DISCTMsg();
        ZYPEZDItemValueSetter.setValue(pmtTermCashDiscTMsg.glblCmpyCd,        glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pmtTermCashDiscTMsg.pmtTermCashDiscCd, bean.getPmtTermCashDiscCd());

        pmtTermCashDiscTMsg = (PMT_TERM_CASH_DISCTMsg) S21CacheTBLAccessor.findByKey(pmtTermCashDiscTMsg);

        if (pmtTermCashDiscTMsg == null) {
            String msgId = MSG_ID.NWCM0112E.toString();
            String[] val = toArray("PMT_TERM_CASH_DISC", bean.getPmtTermCashDiscCd());
            S21InfoLogOutput.println(msgId, val);
            errInfoList.add(bean.getErrInfo(S21MessageFunc.clspGetMessage(msgId, val)));
            return null;
        }

        return pmtTermCashDiscTMsg;
    }

    private String[] toArray(String ...appendMsgList) {
        String[] msgArray = new String[appendMsgList.length];
        for (int i = 0; i < msgArray.length; i++) {
            msgArray[i] = appendMsgList[i];
        }
        return msgArray;
    }

    private String getEndOfMonth(String date, int m) {

        Calendar cal = Calendar.getInstance();
        cal.set(Integer.valueOf(ZYPDateUtil.DateFormatter(date, "yyyyMMdd", "yyyy")),
                Integer.valueOf(ZYPDateUtil.DateFormatter(date, "yyyyMMdd", "MM")) - 1,
                Integer.valueOf(ZYPDateUtil.DateFormatter(date, "yyyyMMdd", "dd")));

        cal.add(Calendar.MONTH, m);

        String yyyy = String.valueOf(cal.get(Calendar.YEAR));
        String mm = String.valueOf(cal.get(Calendar.MONTH) + 1);
        if (mm != null && mm.length() == 1) {
            mm = "0" + mm;
        }
        String dd = String.valueOf(cal.getActualMaximum(Calendar.DATE));

        return yyyy + mm + dd;
    }
    // QC#7000 2016/04/15 add Start
    private List<ConslBillBean> excludeCreditMemoOnlyData(List<ConslBillBean> beanList, List<String> invNumList) {

        List<ConslBillBean> rtnList = new ArrayList<ConslBillBean>();

        // Credit Memo Exclusion
        for (ConslBillBean bean : beanList) {

            String origInvNum = bean.getOrigInvNum();
            String invTpCd = bean.getInvTpCd();
            String easyPacFlg = bean.getEasyPackFlg(); // 2018/08/14 QC#27443 Add
            // 2018/08/14 QC#27443 Mod Start
            //if (INV_TP.CREDIT_MEMO.equals(invTpCd)) {
            if (INV_TP.CREDIT_MEMO.equals(invTpCd) && ZYPConstant.FLG_OFF_N.equals(easyPacFlg)) {
            // 2018/08/14 QC#27443 Mod End

                //QC#22278
                Map<String, Object> ssmParam = new HashMap<String, Object>();
                ssmParam.put("glblCmpyCd", glblCmpyCd);
                ssmParam.put("arTrxNum", bean.getInvNum());
                List<Map<String, Object>> applyInvList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getAppliedInvoice", ssmParam);
                boolean applyInvoiceinSameBillFlg = false;
                if (applyInvList != null && applyInvList.size() > 0) {
                    for (Map<String, Object> applyInvMap : applyInvList) {
                        if (applyInvMap != null && applyInvMap.get("AR_TRX_NUM") != null && invNumList.contains((String) applyInvMap.get("AR_TRX_NUM"))) {
                            applyInvoiceinSameBillFlg = true;
                            break;
                        }
                    }
                }

                //(Doesn't have Original Invoice# and credit didn't apply to invoice in same bill)
                //if (!ZYPCommonFunc.hasValue(origInvNum) || (ZYPCommonFunc.hasValue(origInvNum) && !invNumList.contains(origInvNum))) {
                if (!ZYPCommonFunc.hasValue(origInvNum) && !applyInvoiceinSameBillFlg) {
                    //update INV_PRINT_STS_CD
                    INVTMsg invTMsg = new INVTMsg();
                    ZYPEZDItemValueSetter.setValue(invTMsg.glblCmpyCd,      glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(invTMsg.invNum,          bean.getInvNum());
                    ZYPEZDItemValueSetter.setValue(invTMsg.invPrintStsCd,   "3");

                    S21ApiTBLAccessor.updateSelectionField(invTMsg, new String[]{"invPrintStsCd"});

                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(invTMsg.getReturnCode())) {
                        String msgId = MSG_ID.NWCM0110E.toString();
                        String[] valArray = toArray(invTMsg.getTableName(),  "invNum:" + bean.getInvNum());
                        S21InfoLogOutput.println(msgId, valArray);
                        errInfoList.add(bean.getErrInfo(S21MessageFunc.clspGetMessage(msgId, valArray)));
                        return null;
                    }

                    continue;
                }
            }
            rtnList.add(bean);
        }

        return rtnList;
    }
    // QC#7000 2016/04/15 add End

    // START 2018/06/25 U.Kim [QC#26703,ADD]
    private boolean checkIBContrlField(String defInvGrpCd) {
        if (!ZYPCommonFunc.hasValue(defInvGrpCd)) {
            return false;
        }
        if (DEF_INV_GRP.IB_CONTROL_FIELDS1.equals(defInvGrpCd)) {
            return true;
        }
        if (DEF_INV_GRP.IB_CONTROL_FIELDS2.equals(defInvGrpCd)) {
            return true;
        }
        if (DEF_INV_GRP.IB_CONTROL_FIELDS3.equals(defInvGrpCd)) {
            return true;
        }
        if (DEF_INV_GRP.IB_CONTROL_FIELDS4.equals(defInvGrpCd)) {
            return true;
        }
        if (DEF_INV_GRP.IB_CONTROL_FIELDS5.equals(defInvGrpCd)) {
            return true;
        }
        if (DEF_INV_GRP.IB_CONTROL_FIELDS6.equals(defInvGrpCd)) {
            return true;
        }
        return false;
    }
    // END 2018/06/25 U.Kim [QC#26703,ADD]

    // START 2022/11/29 S.Nakatani [QC#60140,ADD]
    private String getPaymentTermFlag(ConslBillBean bean) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("billToCustCd", bean.getBillToCustCd());

      return (String) ssmBatchClient.queryObject("getPaymentTermFlag", ssmParam);
    }
    // END 2022/11/29 S.Nakatani [QC#60140,ADD]
}
