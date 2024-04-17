/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB204001;

import static com.canon.cusa.s21.batch.NWA.NWAB204001.constant.NWAB204001Constant.AUTH_STS_MSG_CMNT_TXT;
import static com.canon.cusa.s21.batch.NWA.NWAB204001.constant.NWAB204001Constant.CRLF;
import static com.canon.cusa.s21.batch.NWA.NWAB204001.constant.NWAB204001Constant.DISCOUNT_AMOUNT;
import static com.canon.cusa.s21.batch.NWA.NWAB204001.constant.NWAB204001Constant.DISCOUNT_INDICATOR;
import static com.canon.cusa.s21.batch.NWA.NWAB204001.constant.NWAB204001Constant.ERR_MSG_KEY_NM_CPO_ORD_NUM;
import static com.canon.cusa.s21.batch.NWA.NWAB204001.constant.NWAB204001Constant.ERR_MSG_KEY_NM_FSR_NUM;
import static com.canon.cusa.s21.batch.NWA.NWAB204001.constant.NWAB204001Constant.ERR_MSG_KEY_NM_INV_NUM;
import static com.canon.cusa.s21.batch.NWA.NWAB204001.constant.NWAB204001Constant.GET_CR_CARD_TRX_ROW_NUM;
import static com.canon.cusa.s21.batch.NWA.NWAB204001.constant.NWAB204001Constant.GROSS_NET_INDICATOR;
import static com.canon.cusa.s21.batch.NWA.NWAB204001.constant.NWAB204001Constant.MAIL_GROUP_ID_FROM;
import static com.canon.cusa.s21.batch.NWA.NWAB204001.constant.NWAB204001Constant.MAIL_GROUP_ID_TO;
import static com.canon.cusa.s21.batch.NWA.NWAB204001.constant.NWAB204001Constant.MAIL_KEY_FROM;
import static com.canon.cusa.s21.batch.NWA.NWAB204001.constant.NWAB204001Constant.MAIL_KEY_TO;
import static com.canon.cusa.s21.batch.NWA.NWAB204001.constant.NWAB204001Constant.MAIL_OUT_TYPE_MM_DD_YYYY;
import static com.canon.cusa.s21.batch.NWA.NWAB204001.constant.NWAB204001Constant.MAIL_TEMPLATE_ID_M001;
import static com.canon.cusa.s21.batch.NWA.NWAB204001.constant.NWAB204001Constant.MAIL_TEMPLATE_ID_M002;
import static com.canon.cusa.s21.batch.NWA.NWAB204001.constant.NWAB204001Constant.MAIL_TEMPLATE_KEY_CONTENT;
import static com.canon.cusa.s21.batch.NWA.NWAB204001.constant.NWAB204001Constant.MAIL_TEMPLATE_KEY_SLS_DT;
import static com.canon.cusa.s21.batch.NWA.NWAB204001.constant.NWAB204001Constant.ONE_SPACE;
import static com.canon.cusa.s21.batch.NWA.NWAB204001.constant.NWAB204001Constant.PMTC_APVL_STS_APPLOVED;
import static com.canon.cusa.s21.batch.NWA.NWAB204001.constant.NWAB204001Constant.PMTC_PRFL_ORD_OVRD_CD_NO;
import static com.canon.cusa.s21.batch.NWA.NWAB204001.constant.NWAB204001Constant.PMTC_PROC_STS_SUCCESS;
import static com.canon.cusa.s21.batch.NWA.NWAB204001.constant.NWAB204001Constant.PMTC_TAX_INDEX_NUM_OFF;
import static com.canon.cusa.s21.batch.NWA.NWAB204001.constant.NWAB204001Constant.PMTC_TAX_INDEX_NUM_ON;
import static com.canon.cusa.s21.batch.NWA.NWAB204001.constant.NWAB204001Constant.PROC_RES_CD_ERROR;
import static com.canon.cusa.s21.batch.NWA.NWAB204001.constant.NWAB204001Constant.PROC_RES_CD_NOT_TARGET;
import static com.canon.cusa.s21.batch.NWA.NWAB204001.constant.NWAB204001Constant.PROC_RES_CD_SUCCESS;
import static com.canon.cusa.s21.batch.NWA.NWAB204001.constant.NWAB204001Constant.PROGRAM_NM;
import static com.canon.cusa.s21.batch.NWA.NWAB204001.constant.NWAB204001Constant.TAX_TYPE;
import static com.canon.cusa.s21.batch.NWA.NWAB204001.constant.NWAB204001Constant.UNIT_OF_MEASURE;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import parts.dbcommon.EZDTBLAccessor;
import business.db.CR_CARD_TRXTMsg;
import business.db.CR_CARD_TRX_DTLTMsg;
import business.db.INVTMsg;
import business.parts.NWZC203001PMsg;
import business.parts.NWZC203001_APMsg;

import com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001;
import com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001Constant;
import com.canon.cusa.s21.batch.NWA.NWAB204001.constant.NWAB204001Constant.MSG_ID;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001Mail;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001MailSubstituteString;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_AUTH_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_CHRG_CPLT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_DRCTN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 * <pre>
 * Credit Card Capture Batch.
 * program of BusinessID NWAB204001. 
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/26/2016   Fujitsu         S.Ohki          Create          N/A
 * 06/09/2016   Fujitsu         H.Nagashima     Update          QC#9426
 * 07/14/2016   Fujitsu         H.Nagashima     Update          QC#11733
 * 07/10/2018   Fujitsu         A.Kosai         Update          QC#25797
 * 05/20/2019   Fujitsu         C.Hara          Update          QC#50150
 * 04/01/2021   CITS            K.Ogino         Update          QC#58621
 * 04/30/2021   CITS            K.Ogino         Update          QC#58621-1
 * 01/24/2022   Hitachi         R.Onozuka       Update          QC#56129
 * 05/30/2022   CITS            K.Ogino         Update          QC#60127
 * </pre>
 */
public class NWAB204001 extends S21BatchMain {

    /** Global Company code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String slsDt = null;

    /** Term Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Error Record Count */
    private int errRecCnt = 0;

    /** records */
    private int totalRecCnt = 0;

    /** Normal Record Count */
    private int normalRecCnt = 0;

    /** SQL access parts */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Mail template ID */
    private String mailTemplateId = null;

    /** Mail Bean Map */
    private HashMap<String, NWAB204001MailBean> emailBodyMap = null;

    // Add QC#58621
    /** Payeezy Line Limit */
    private BigDecimal payeezyLmtCnt = null;

    /**
     * Main
     * @param args String[]
     */
    public static void main(String[] args) {
        new NWAB204001().executeBatch(NWAB204001.class.getSimpleName());
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

        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.emailBodyMap = new HashMap<String, NWAB204001MailBean>();
        // Add QC#58621
        BigDecimal constNum = ZYPCodeDataUtil.getNumConstValue("PAYEEZY_LINE_ITEM_LIMIT_NUM", getGlobalCompanyCode());
        if (ZYPCommonFunc.hasValue(constNum)) {
            this.payeezyLmtCnt = constNum;
        }
    }

    @Override
    protected void mainRoutine() {

        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("fnlzinvFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("dsOrdLineDrctnCd", DS_ORD_LINE_DRCTN.INBOUND);
        ssmParam.put("crCardChrgCpltCd", CR_CARD_CHRG_CPLT.WAITING_FOR_CREDIT_CARD_CHARGE);

        boolean rslt = (Boolean) ssmBatchClient.queryObject("getTargetInvoice", ssmParam, new CreditCardValidation());
        if (!rslt) {
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
    protected class CreditCardValidation extends S21SsmBooleanResultSetHandlerSupport {

        @Override
        protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            Boolean rslt = Boolean.TRUE;

            // Has No Result Data
            if (!rs.next()) {
                return true;
            }

            NWAB204001Bean bean = null;

            do {
                String procResCd = PROC_RES_CD_NOT_TARGET;

                String invNum = rs.getString("INV_NUM");
                String sysSrcCd = rs.getString("SYS_SRC_CD");
                String cpoOrdNum = rs.getString("CPO_ORD_NUM");
                String svcInvNum = rs.getString("SVC_INV_NUM");
                String svcInvSrcTpCd = rs.getString("SVC_INV_SRC_TP_CD");
                String fsrNum = rs.getString("FSR_NUM");
                String crCardCustRefNum = rs.getString("CR_CARD_CUST_REF_NUM");

                bean = new NWAB204001Bean();
                bean.setSysSrcCd(sysSrcCd);
                bean.setCpoOrdNum(cpoOrdNum);
                bean.setInvNum(rs.getString("INV_NUM"));
                bean.setBillToCustAcctCd(rs.getString("BILL_TO_CUST_ACCT_CD"));
                bean.setBillToCustCd(rs.getString("BILL_TO_CUST_CD"));
                bean.setSellToCustCd(rs.getString("SELL_TO_CUST_CD"));
                bean.setInvTpCd(rs.getString("INV_TP_CD"));
                bean.setInvDt(rs.getString("INV_DT"));
                bean.setSvcInvNum(rs.getString("SVC_INV_NUM"));
                bean.setSvcInvSrcTpCd(rs.getString("SVC_INV_SRC_TP_CD"));
                bean.setFsrNum(rs.getString("FSR_NUM"));
                bean.setCrCardCustRefNum(rs.getString("CR_CARD_CUST_REF_NUM"));
                bean.setInvTotDealNetAmt(rs.getBigDecimal("INV_TOT_DEAL_NET_AMT"));
                bean.setInvTotFuncNetAmt(rs.getBigDecimal("INV_TOT_FUNC_NET_AMT"));
                bean.setInvTotFuncTaxAmt(rs.getBigDecimal("INV_TOT_FUNC_TAX_AMT"));
                // Add Start 2022/1/24 QC#56129
                bean.setSellToLocNm(rs.getString("SELL_TO_LOC_NM"));
                bean.setCltPsnCd(rs.getString("CLT_PSN_CD"));
                bean.setCltPsnNm(rs.getString("CLT_PSN_NM"));
                // Add End   2022/1/24 QC#56129
                

                Map<String, Object> ssmParam = new HashMap<String, Object>();
                ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
                ssmParam.put("rowNum", GET_CR_CARD_TRX_ROW_NUM);

                // 2019/05/20 QC#50150 Add Start
                if (bean.getInvTotFuncNetAmt().doubleValue() == 0 && bean.getInvTotFuncTaxAmt().doubleValue() == 0){
                    if (updateCrCardChrgCpltCdOfDsInv(bean, CR_CARD_CHRG_CPLT.COMPLETED_CREDIT_CARD_CHARGE)) {
                        procResCd = PROC_RES_CD_SUCCESS;
                    } else {
                        procResCd = PROC_RES_CD_ERROR;
                    }
                // 2019/05/20 QC#50150 Add End

                // CPO
                // 2019/05/20 QC#50150 Mod Start
                // if (!SYS_SRC.S21_ACCOUNTING_AR.equals(sysSrcCd) && ZYPCommonFunc.hasValue(cpoOrdNum)) {
                } else if (!SYS_SRC.S21_ACCOUNTING_AR.equals(sysSrcCd) && ZYPCommonFunc.hasValue(cpoOrdNum)) {
                // 2019/05/20 QC#50150 Mod End
                    ssmParam.put("crCardTrxTpCd", CR_CARD_TRX_TP.CPO);
                    ssmParam.put("firstTrxInfoTxt", cpoOrdNum);
                    ssmParam.put("crCardAuthStsCd", CR_CARD_AUTH_STS.AUTHORIZED_COMPLETED);
                    ssmParam.put("setlCpltFlg", ZYPConstant.FLG_OFF_N);

                    if (getCrCardTrx(ssmParam, bean)) {
                        procResCd = callCreditCardValidationApi(bean);
                    } else {
//QC#16321 mod Start
////QC#11733 add Start
//                        ssmParam.put("crCardAuthStsCd", CR_CARD_AUTH_STS.VOID_COMPLETED);
////QC#11733 add End
                        ssmParam.remove("crCardAuthStsCd");
                        ssmParam.put("AuthStsVoidCompleted", CR_CARD_AUTH_STS.VOID_COMPLETED);
                        ssmParam.put("AuthStsVoidFailed",    CR_CARD_AUTH_STS.VOID_FAILED);
//QC#16321 mod End
                        ssmParam.put("setlCpltFlg", ZYPConstant.FLG_ON_Y);
                        if (getCrCardTrx(ssmParam, bean)) {
                            procResCd = callCreditCardValidationApi(bean);
                        } else {
                            S21InfoLogOutput.println(MSG_ID.ZZZM9006E.toString(), toArray(getCrCardTrxSearchErrorMsg(ERR_MSG_KEY_NM_CPO_ORD_NUM, cpoOrdNum)));
                            updateCrCardChrgCpltCdOfDsInv(bean, CR_CARD_CHRG_CPLT.FAILED_CREDIT_CARD_CHARGE);
                            insertCrCardTrx(bean);
                            procResCd = PROC_RES_CD_ERROR;
                        }
                    }
                // Service Request
                } else if (!SYS_SRC.S21_ACCOUNTING_AR.equals(sysSrcCd) && ZYPCommonFunc.hasValue(svcInvNum) && ZYPCommonFunc.hasValue(fsrNum) && SVC_INV_SRC_TP.DISPATCH.equals(svcInvSrcTpCd)) {
                    ssmParam.put("crCardTrxTpCd", CR_CARD_TRX_TP.SERVICE_REQUEST);
                    ssmParam.put("firstTrxInfoTxt", fsrNum);
                    ssmParam.put("crCardAuthStsCd", CR_CARD_AUTH_STS.AUTHORIZED_COMPLETED);
                    ssmParam.put("crCardCustRefNum", crCardCustRefNum);

                    if (getCrCardTrx(ssmParam, bean)) {
                        procResCd = callCreditCardValidationApi(bean);
                    } else {
                        ssmParam.put("crCardAuthStsCd", CR_CARD_AUTH_STS.SAVED);
                        ssmParam.put("selectNullFlg", ZYPConstant.FLG_ON_Y);
                        if (getCrCardTrx(ssmParam, bean)) {
                            procResCd = callCreditCardValidationApi(bean);
                        } else {
                            S21InfoLogOutput.println(MSG_ID.ZZZM9006E.toString(), toArray(getCrCardTrxSearchErrorMsg(ERR_MSG_KEY_NM_FSR_NUM, fsrNum)));
                            updateCrCardChrgCpltCdOfDsInv(bean, CR_CARD_CHRG_CPLT.FAILED_CREDIT_CARD_CHARGE);
                            insertCrCardTrx(bean);
                            procResCd = PROC_RES_CD_ERROR;
                        }
                    }
                // Invoice
                } else if (!SYS_SRC.S21_ACCOUNTING_AR.equals(sysSrcCd) && ZYPCommonFunc.hasValue(svcInvNum) && SVC_INV_SRC_TP.CONTRACT.equals(svcInvSrcTpCd)) {

                    bean.setCrCardTrxPk(null);
                    bean.setTokenNum(crCardCustRefNum);
                    bean.setAuthRefNum(null);
                    bean.setRefIdxNum(null);
                    bean.setSetlCpltFlg(null);
                    procResCd = callCreditCardValidationApi(bean);

                // Others
                } else {
                    ssmParam.put("crCardTrxTpCd", CR_CARD_TRX_TP.INVOICE);
                    ssmParam.put("firstTrxInfoTxt", invNum);
                    ssmParam.put("crCardAuthStsCd", CR_CARD_AUTH_STS.AUTHORIZED_COMPLETED);

                    if (getCrCardTrx(ssmParam, bean)) {
                        procResCd = callCreditCardValidationApi(bean);
                    } else {
                        ssmParam.put("crCardAuthStsCd", CR_CARD_AUTH_STS.SAVED);
                        ssmParam.put("selectNullFlg", ZYPConstant.FLG_ON_Y);
                        if (getCrCardTrx(ssmParam, bean)) {
                            procResCd = callCreditCardValidationApi(bean);
                        } else {
                            S21InfoLogOutput.println(MSG_ID.ZZZM9006E.toString(), toArray(getCrCardTrxSearchErrorMsg(ERR_MSG_KEY_NM_INV_NUM, invNum)));
                            updateCrCardChrgCpltCdOfDsInv(bean, CR_CARD_CHRG_CPLT.FAILED_CREDIT_CARD_CHARGE);
                            insertCrCardTrx(bean);
                            procResCd = PROC_RES_CD_ERROR;
                        }
                    }
                }

                if (PROC_RES_CD_SUCCESS.equals(procResCd)) {
                    normalRecCnt++;
                } else if (PROC_RES_CD_ERROR.equals(procResCd)) {
                    errRecCnt++;
                }
                totalRecCnt++;

            } while (rs.next());

            if (emailBodyMap.size() > 0) {
                sendMail();
            }

            if (errRecCnt > 0) {
                rslt = Boolean.FALSE;
            }
            return rslt;
        }
    }

    /**
     * <pre>
     * Get Credit Card Transaction
     * </pre>
     * @param ssmParam Map<String, Object>
     * @param bean NWAB204001Bean
     * @return boolean
     */
    private boolean getCrCardTrx(Map<String, Object> ssmParam, NWAB204001Bean bean) {

        Map<String, Object> map = (Map<String, Object>) ssmBatchClient.queryObject("getCrCardTrx", ssmParam);

        if (map == null) {
            return false;
        }

        bean.setCrCardTrxPk((BigDecimal) map.get("CR_CARD_TRX_PK"));
        bean.setTokenNum((String) map.get("CR_CARD_CUST_REF_NUM"));
        bean.setAuthRefNum((String) map.get("CR_CARD_AUTH_REF_NUM"));
        bean.setRefIdxNum((String) map.get("CR_CARD_REF_IDX_NUM"));
        bean.setSetlCpltFlg((String) map.get("SETL_CPLT_FLG"));
        // 2018/07/10 S21_NA#25797 Add Start
        bean.setAuthCpltAmt((BigDecimal) map.get("CR_CARD_AUTH_AMT"));
        // 2018/07/10 S21_NA#25797 Add End

        return true;
    }

    /**
     * <pre>
     * Call Credit Card Validation API
     * </pre>
     * @param bean NWAB204001Bean
     * @return String
     */
    private String callCreditCardValidationApi(NWAB204001Bean bean) {

        String mode = null;
        String authRefNum = bean.getAuthRefNum();
        String setlCpltFlg = bean.getSetlCpltFlg();
        String procResCd;

//QC#9426 mod Start
        if (ZYPCommonFunc.hasValue(authRefNum)) {

            if (ZYPConstant.FLG_OFF_N.equals(setlCpltFlg)) {
//                mode = NWZC203001Constant.PROC_MODE_CAPT_ONLY;
                BigDecimal authCrCarkTrxPk = bean.getCrCardTrxPk();
                // void mode
                procResCd = callCreditCardValidationApi(NWZC203001Constant.PROC_MODE_VOID, bean);
                if (!PROC_RES_CD_SUCCESS.equals(procResCd)) {
                    return procResCd;
                }
                // auth&capture mode
                procResCd = callCreditCardValidationApi(NWZC203001Constant.PROC_MODE_SETTLE, bean);
                if (!PROC_RES_CD_SUCCESS.equals(procResCd)) {
                    return procResCd;
                }
                //update SETL_CPLT_FLG
//                if (!updateSetlCpltFlgOfCrCardTrx(bean.getCrCardTrxPk())) {
                if (!updateSetlCpltFlgOfCrCardTrx(authCrCarkTrxPk)) {
                    return PROC_RES_CD_ERROR;
                }
            } else {
                // auth&capture mode
//                mode = NWZC203001Constant.PROC_MODE_SETTLE;
                procResCd = callCreditCardValidationApi(NWZC203001Constant.PROC_MODE_SETTLE, bean);
                if (!PROC_RES_CD_SUCCESS.equals(procResCd)) {
                    return procResCd;
                }
            }
        } else {
            // auth&capture mode
//            mode = NWZC203001Constant.PROC_MODE_SETTLE;
            procResCd = callCreditCardValidationApi(NWZC203001Constant.PROC_MODE_SETTLE, bean);
            if (!PROC_RES_CD_SUCCESS.equals(procResCd)) {
                return procResCd;
            }
        }
        return PROC_RES_CD_SUCCESS;

//QC#9426 mod End

//        // Call Credit Card Validation API
//        NWZC203001PMsg pMsg = new NWZC203001PMsg();
//        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, mode);
//        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, this.slsDt);
//        ZYPEZDItemValueSetter.setValue(pMsg.sellToCustCd, bean.getSellToCustCd());
//        ZYPEZDItemValueSetter.setValue(pMsg.crCardTrxTpCd, CR_CARD_TRX_TP.INVOICE);
//        ZYPEZDItemValueSetter.setValue(pMsg.firstTrxInfoTxt, bean.getInvNum());
//        ZYPEZDItemValueSetter.setValue(pMsg.origCrCardTrxPk, bean.getCrCardTrxPk());
//        ZYPEZDItemValueSetter.setValue(pMsg.xxPmtcPrflOrdOvrdCd, PMTC_PRFL_ORD_OVRD_CD_NO);
//        ZYPEZDItemValueSetter.setValue(pMsg.xxPmtcOrdId, bean.getInvNum());
//
//        if (INV_TP.INVOICE.equals(bean.getInvTpCd()) || INV_TP.DEBIT_MEMO.equals(bean.getInvTpCd())) {
//            ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthAmt, bean.getInvTotFuncNetAmt());
//            ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthTaxAmt, bean.getInvTotFuncTaxAmt());
//        } else {
//            ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthAmt, bean.getInvTotFuncNetAmt().negate());
//            ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthTaxAmt, bean.getInvTotFuncTaxAmt().negate());
//        }
//
//        if (!ZYPCommonFunc.hasValue(bean.getInvTotFuncTaxAmt()) || BigDecimal.ZERO.compareTo(bean.getInvTotFuncTaxAmt()) == 0) {
//            ZYPEZDItemValueSetter.setValue(pMsg.xxPmtcTaxIndNum, PMTC_TAX_INDEX_NUM_OFF);
//        } else {
//            ZYPEZDItemValueSetter.setValue(pMsg.xxPmtcTaxIndNum, PMTC_TAX_INDEX_NUM_ON);
//        }
//
//        if (NWZC203001Constant.PROC_MODE_SETTLE.equals(mode)) {
//            ZYPEZDItemValueSetter.setValue(pMsg.crCardCustRefNum, bean.getTokenNum());
//        } else {
//            ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthRefNum, bean.getAuthRefNum());
//            ZYPEZDItemValueSetter.setValue(pMsg.xxPmtcTrxRefIdxCd, bean.getRefIdxNum());
//        }
//
//        // Credit Card Validation API
//        new NWZC203001().execute(pMsg, ONBATCH_TYPE.ONLINE);
//
//        if (PMTC_APVL_STS_APPLOVED.equals(pMsg.xxPmtcApvlStsNum.getValue()) && PMTC_PROC_STS_SUCCESS.equals(pMsg.xxPmtcProcStsCd.getValue())) {
//
//            if (!updateCrCardChrgCpltCdOfDsInv(bean, CR_CARD_CHRG_CPLT.COMPLETED_CREDIT_CARD_CHARGE)) {
//                return PROC_RES_CD_ERROR;
//            }
//        } else {
//            String errMsg = "";
//
//            if (ZYPCommonFunc.hasValue(pMsg.authStsMsgCmntTxt)) {
//                S21InfoLogOutput.println(pMsg.authStsMsgCmntTxt.getValue());
//                errMsg = pMsg.authStsMsgCmntTxt.getValue();
//
//            } else if (pMsg.xxMsgIdList.getValidCount() > 0) {
//                for (int n = 0; n < pMsg.xxMsgIdList.getValidCount(); n++) {
//                    String errId = pMsg.xxMsgIdList.no(n).xxMsgId.getValue();
//                    S21InfoLogOutput.println(errId);
//                    errMsg = S21MessageFunc.clspGetMessage(errId);
//                }
//            }
//
//            // Error Message
//            String errPadMsg = getInvRefNumErrorMsg(bean);
//            S21InfoLogOutput.println(MSG_ID.NWAM0518E.toString(), toArray(errPadMsg));
//
//            StringBuilder sbErrMsg = new StringBuilder(errPadMsg);
//            sbErrMsg.append(", ErrorMsg:");
//            sbErrMsg.append(errMsg);
//
//            setDeclineInfoEmail(pMsg, bean, sbErrMsg.toString());
//
//            updateCrCardChrgCpltCdOfDsInv(bean, CR_CARD_CHRG_CPLT.FAILED_CREDIT_CARD_CHARGE);
//
//            return PROC_RES_CD_ERROR;
//        }
//        return PROC_RES_CD_SUCCESS;

    }
//QC#9426 add Start
    /**
     * <pre>
     * Call Credit Card Validation API
     * </pre>
     * @param bean NWAB204001Bean
     * @return boolean
     */
    private String callCreditCardValidationApi(String mode, NWAB204001Bean bean) {

        // Call Credit Card Validation API
        NWZC203001PMsg pMsg = new NWZC203001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, mode);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, this.slsDt);

        if (NWZC203001Constant.PROC_MODE_SETTLE.equals(mode) 
         || NWZC203001Constant.PROC_MODE_CAPT_ONLY.equals(mode)) {

//            ZYPEZDItemValueSetter.setValue(pMsg.sellToCustCd, bean.getSellToCustCd());
            ZYPEZDItemValueSetter.setValue(pMsg.sellToCustCd, bean.getBillToCustAcctCd());
            ZYPEZDItemValueSetter.setValue(pMsg.crCardTrxTpCd, CR_CARD_TRX_TP.INVOICE);
            ZYPEZDItemValueSetter.setValue(pMsg.firstTrxInfoTxt, bean.getInvNum());
            ZYPEZDItemValueSetter.setValue(pMsg.origCrCardTrxPk, bean.getCrCardTrxPk());
            ZYPEZDItemValueSetter.setValue(pMsg.xxPmtcPrflOrdOvrdCd, PMTC_PRFL_ORD_OVRD_CD_NO);
            ZYPEZDItemValueSetter.setValue(pMsg.xxPmtcOrdId, bean.getInvNum());

            if (INV_TP.INVOICE.equals(bean.getInvTpCd()) || INV_TP.DEBIT_MEMO.equals(bean.getInvTpCd())) {
                ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthAmt, bean.getInvTotFuncNetAmt());
                ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthTaxAmt, bean.getInvTotFuncTaxAmt());
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthAmt, bean.getInvTotFuncNetAmt().negate());
                ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthTaxAmt, bean.getInvTotFuncTaxAmt().negate());
            }

            if (!ZYPCommonFunc.hasValue(bean.getInvTotFuncTaxAmt()) || BigDecimal.ZERO.compareTo(bean.getInvTotFuncTaxAmt()) == 0) {
                ZYPEZDItemValueSetter.setValue(pMsg.xxPmtcTaxIndNum, PMTC_TAX_INDEX_NUM_OFF);
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.xxPmtcTaxIndNum, PMTC_TAX_INDEX_NUM_ON);
            }

            if (NWZC203001Constant.PROC_MODE_SETTLE.equals(mode)) {
                ZYPEZDItemValueSetter.setValue(pMsg.crCardCustRefNum, bean.getTokenNum());
                // Add QC#58621 Start
                boolean workFlg = false;
                int totalCnt = 0;
                BigDecimal totCrCardAuthTaxAmt = BigDecimal.ZERO;
                BigDecimal totCrCardAuthFrtAmt = BigDecimal.ZERO;
                BigDecimal totCrCardAuthDiscAmt = BigDecimal.ZERO;
                BigDecimal totCrCardAuthLineItemCnt = BigDecimal.ZERO;

                Map<String, Object> param = new HashMap<String, Object>();
                param.put("glblCmpyCd", glblCmpyCd);
                param.put("arTrxNumList", new String[] {bean.getInvNum() });
                if (ZYPCommonFunc.hasValue(this.payeezyLmtCnt)) {
                    param.put("constNum", payeezyLmtCnt);
                }
                List<Map<String, Object>> invInfoList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getInvInfo", param);
                if (invInfoList != null && !invInfoList.isEmpty()) {
                    workFlg = true;
                    ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthPostCd, (String) invInfoList.get(0).get("SHIP_TO_POST_CD")); // INV_BOL.SHIP_TO_POST_CD
                    ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthAddr, (String) invInfoList.get(0).get("ADDR")); // INV_BOL.SHIP_TO_FIRST_LINE_ADDR
                                                                                                                  // -
                                                                                                                  // NV_BOL.SHIP_TO_FRTH_LINE_ADDR
                    ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthCtyAddr, (String) invInfoList.get(0).get("SHIP_TO_CTY_ADDR")); // INV_BOL.SHIP_TO_CTY_ADDR
                    ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthStCd, (String) invInfoList.get(0).get("SHIP_TO_ST_CD")); // INV_BOL.SHIP_TO_ST_CD
                    ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthCtryCd, (String) invInfoList.get(0).get("SHIP_TO_CTRY_CD")); // INV_BOL.SHIP_TO_CTRY_CD
                    ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthFromZipCd, (String) invInfoList.get(0).get("POST_CD")); // INV_BOL.SHIP_FROM_INVTY_LOC_CD
                                                                                                                          // ->
                                                                                                                          // SHIP_TO_CUST.POST_CD

                    ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthTrxNum, bean.getInvNum());
                    ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthDtyAmt, BigDecimal.ZERO);
                    // ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthAltTaxId,
                    // null); // NULL
                    ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthAltTaxAmt, BigDecimal.ZERO);

                    param = new HashMap<String, Object>();
                    param.put("glblCmpyCd", glblCmpyCd);
                    param.put("crCardCustRefNum", bean.getTokenNum());

                    Map<String, Object> dsCardInfo = (Map<String, Object>) ssmBatchClient.queryObject("getDsCardInfo", param);
                    if (dsCardInfo != null) {
                        ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthHldNm, createName((String) dsCardInfo.get("CR_CARD_HLD_NM"))); // DS_CR_CARD.CR_CARD_HLD_NM
                    }

                    for (int j = 0; j < invInfoList.size(); j++) {

                        if (j == 0) {
                            totCrCardAuthFrtAmt = totCrCardAuthFrtAmt.add((BigDecimal) invInfoList.get(0).get("INV_TOT_DEAL_FRT_AMT")); // INV.INV_TOT_DEAL_FRT_AMT
                            totCrCardAuthDiscAmt = totCrCardAuthDiscAmt.add((BigDecimal) invInfoList.get(0).get("INV_TOT_DEAL_DISC_AMT")); // INV.INV_TOT_DEAL_DISC_AMT
                            totCrCardAuthLineItemCnt = totCrCardAuthLineItemCnt.add((BigDecimal) invInfoList.get(0).get("CNT")); // INV_LINE
                                                                                                                                 // Count
                            totCrCardAuthTaxAmt = totCrCardAuthTaxAmt.add((BigDecimal) invInfoList.get(0).get("INV_TOT_DEAL_TAX_AMT")); // INV.INV_TOT_DEAL_TAX_AMT
                        }

                        // QC#60127 Add
                        if (BigDecimal.ZERO.compareTo((BigDecimal) invInfoList.get(j).get("INV_LINE_DEAL_NET_AMT")) >= 0) {
                            continue;
                        }

                        NWZC203001_APMsg apMsg = pMsg.A.no(totalCnt);

                        ZYPEZDItemValueSetter.setValue(apMsg.crCardTrxDtlLineNum, String.valueOf(j)); // Serial
                                                                                                      // No
                        ZYPEZDItemValueSetter.setValue(apMsg.crCardAuthMdseNm, (String) invInfoList.get(j).get("MDSE_NM")); // INV_LINE.MDSE_NM
                        ZYPEZDItemValueSetter.setValue(apMsg.crCardAuthProdCd, (String) invInfoList.get(j).get("UPC_CD")); // INV_LINE.MDSE_CD
                                                                                                                           // ->
                                                                                                                           // MDSE.UPC_CD
                        ZYPEZDItemValueSetter.setValue(apMsg.crCardAuthOrdQty, (BigDecimal) invInfoList.get(j).get("ORD_QTY")); // INV_LINE.ORD_QTY
                        ZYPEZDItemValueSetter.setValue(apMsg.crCardAuthUomCd, UNIT_OF_MEASURE);
                        ZYPEZDItemValueSetter.setValue(apMsg.crCardAuthDtlTaxAmt, (BigDecimal) invInfoList.get(j).get("INV_LINE_DEAL_TAX_AMT")); // INV_LINE.INV_LINE_DEAL_TAX_AMT
                        ZYPEZDItemValueSetter.setValue(apMsg.crCardAuthDtlTaxPct, (BigDecimal) invInfoList.get(j).get("TAX_PCT")); // INV_LINE.TAX_PCT
                        ZYPEZDItemValueSetter.setValue(apMsg.crCardAuthDtlAmt, (BigDecimal) invInfoList.get(j).get("INV_LINE_DEAL_NET_AMT")); // INV_LINE.INV_LINE_DEAL_NET_AMT
                        ZYPEZDItemValueSetter.setValue(apMsg.crCardAuthDtlDiscAmt, DISCOUNT_AMOUNT);
                        ZYPEZDItemValueSetter.setValue(apMsg.crCardAuthCmdtyCd, ZYPCodeDataUtil.getVarCharConstValue("L_TICKET_TRN_COMMONDITY_CD", getGlobalCompanyCode())); // L_TICKET_TRN_COMMONDITY_CD
                        ZYPEZDItemValueSetter.setValue(apMsg.crCardAuthUnitPrcAmt, (BigDecimal) invInfoList.get(j).get("DEAL_NET_UNIT_PRC_AMT")); // INV_LINE.DEAL_NET_UNIT_PRC_AMT
                        ZYPEZDItemValueSetter.setValue(apMsg.crCardAuthGrsNetInd, GROSS_NET_INDICATOR);
                        ZYPEZDItemValueSetter.setValue(apMsg.crCardAuthTaxTpCd, TAX_TYPE);
                        ZYPEZDItemValueSetter.setValue(apMsg.crCardAuthDiscInd, DISCOUNT_INDICATOR);

                        totalCnt = totalCnt + 1;
                    }
                }

                if (workFlg) {
                    ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthTaxAmt, totCrCardAuthTaxAmt); // INV.INV_TOT_DEAL_TAX_AMT
                    ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthFrtAmt, totCrCardAuthFrtAmt); // INV.INV_TOT_DEAL_FRT_AMT
                    // QC#60127 Modify
                    ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthDiscAmt, totCrCardAuthDiscAmt.abs()); // INV.INV_TOT_DEAL_DISC_AMT
                    ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthLineItemCnt, totCrCardAuthLineItemCnt); // INV_LINE
                                                                                                          // Count
                    pMsg.A.setValidCount(totalCnt);
                }
                // Add QC#58621 End
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthRefNum, bean.getAuthRefNum());
                ZYPEZDItemValueSetter.setValue(pMsg.xxPmtcTrxRefIdxCd, bean.getRefIdxNum());
            }

        } else if (NWZC203001Constant.PROC_MODE_VOID.equals(mode)) {
            ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthRefNum, bean.getAuthRefNum());
            ZYPEZDItemValueSetter.setValue(pMsg.xxPmtcOrdId, bean.getInvNum());
            ZYPEZDItemValueSetter.setValue(pMsg.xxPmtcTrxRefIdxCd, bean.getRefIdxNum());
            ZYPEZDItemValueSetter.setValue(pMsg.crCardTrxPk, bean.getCrCardTrxPk());
            // 2018/07/10 S21_NA#25797 Add Start
            ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthAmt, bean.getAuthCpltAmt());
            // 2018/07/10 S21_NA#25797 Add End
        }

        // Credit Card Validation API
        new NWZC203001().execute(pMsg, ONBATCH_TYPE.ONLINE);

//        if (PMTC_APVL_STS_APPLOVED.equals(pMsg.xxPmtcApvlStsNum.getValue()) && PMTC_PROC_STS_SUCCESS.equals(pMsg.xxPmtcProcStsCd.getValue())) {
        boolean isApproved = PMTC_APVL_STS_APPLOVED.equals(pMsg.xxPmtcApvlStsNum.getValue());
        boolean isProcSuccess = PMTC_PROC_STS_SUCCESS.equals(pMsg.xxPmtcProcStsCd.getValue());
        if ((NWZC203001Constant.PROC_MODE_SETTLE.equals(mode)     && isProcSuccess && isApproved)
          || (NWZC203001Constant.PROC_MODE_CAPT_ONLY.equals(mode) && isProcSuccess && isApproved)
//          || (NWZC203001Constant.PROC_MODE_VOID.equals(mode)      && isProcSuccess)) {
          || (NWZC203001Constant.PROC_MODE_VOID.equals(mode))) {    //QC#16321 mod

            if (NWZC203001Constant.PROC_MODE_SETTLE.equals(mode)) {
                //set new pk
                bean.setCrCardTrxPk(pMsg.crCardTrxPk.getValue());
            }

            if (!updateCrCardChrgCpltCdOfDsInv(bean, CR_CARD_CHRG_CPLT.COMPLETED_CREDIT_CARD_CHARGE)) {
                return PROC_RES_CD_ERROR;
            }
        } else {
            // error returned by Paymentech
            String errMsg = "";
            // Add QC#58621
            if (NWZC203001Constant.PROC_MODE_SETTLE.equals(mode)) {
                insErrCrCartTrxDtl(pMsg, bean);
            }

            if (ZYPCommonFunc.hasValue(pMsg.authStsMsgCmntTxt)) {
                S21InfoLogOutput.println(pMsg.authStsMsgCmntTxt.getValue());
                errMsg = pMsg.authStsMsgCmntTxt.getValue();

            } else if (pMsg.xxMsgIdList.getValidCount() > 0) {
                for (int n = 0; n < pMsg.xxMsgIdList.getValidCount(); n++) {
                    String errId = pMsg.xxMsgIdList.no(n).xxMsgId.getValue();
                    S21InfoLogOutput.println(errId);
                    errMsg = S21MessageFunc.clspGetMessage(errId);
                }
            }

            // Error Message
            String errPadMsg = getInvRefNumErrorMsg(bean);
            S21InfoLogOutput.println(MSG_ID.NWAM0518E.toString(), toArray(errPadMsg));

            StringBuilder sbErrMsg = new StringBuilder(errPadMsg);
            sbErrMsg.append(", ErrorMsg:");
            sbErrMsg.append(errMsg);

            setDeclineInfoEmail(pMsg, bean, sbErrMsg.toString());

            updateCrCardChrgCpltCdOfDsInv(bean, CR_CARD_CHRG_CPLT.FAILED_CREDIT_CARD_CHARGE);

            return PROC_RES_CD_ERROR;
        }
        return PROC_RES_CD_SUCCESS;

    }
//QC#9426 add End
    /**
     * <pre>
     * Update Settle Complete Flag Of CR_CARD_TRX
     * </pre>
     * @param crCardTrxPk BigDecimal
     * @return boolean
     */
    private boolean updateSetlCpltFlgOfCrCardTrx(BigDecimal crCardTrxPk) {

        CR_CARD_TRXTMsg crCardTrxTMsg = new CR_CARD_TRXTMsg();
        ZYPEZDItemValueSetter.setValue(crCardTrxTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(crCardTrxTMsg.crCardTrxPk, crCardTrxPk);

        crCardTrxTMsg = (CR_CARD_TRXTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(crCardTrxTMsg);

        ZYPEZDItemValueSetter.setValue(crCardTrxTMsg.setlCpltFlg, ZYPConstant.FLG_ON_Y);

        // CR_CARD_TRX Update
        EZDTBLAccessor.update(crCardTrxTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(crCardTrxTMsg.getReturnCode())) {
            S21InfoLogOutput.println(MSG_ID.NWAM0791E.toString(), toArray(crCardTrxTMsg.getTableName() + " :" + crCardTrxPk.toString()));
            return false;
        }
        return true;
    }

    /**
     * <pre>
     * Insert Credit Card Transaction
     * </pre>
     * @param bean NWAB204001Bean
     */
    private void insertCrCardTrx(NWAB204001Bean bean) {

        CR_CARD_TRXTMsg crCardTrxTMsg = new CR_CARD_TRXTMsg();
        ZYPEZDItemValueSetter.setValue(crCardTrxTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(crCardTrxTMsg.crCardTrxPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CR_CARD_TRX_SQ));
        ZYPEZDItemValueSetter.setValue(crCardTrxTMsg.billToCustCd, bean.getBillToCustCd());
        ZYPEZDItemValueSetter.setValue(crCardTrxTMsg.billToCustAcctCd, bean.getBillToCustAcctCd());
        ZYPEZDItemValueSetter.setValue(crCardTrxTMsg.crCardAuthStsCd, CR_CARD_AUTH_STS.SETTLEMENT_FAILED);
        ZYPEZDItemValueSetter.setValue(crCardTrxTMsg.crCardAuthAmt, bean.getInvTotFuncNetAmt());
        ZYPEZDItemValueSetter.setValue(crCardTrxTMsg.crCardAuthTaxAmt, bean.getInvTotFuncTaxAmt());
        ZYPEZDItemValueSetter.setValue(crCardTrxTMsg.crCardTrxTpCd, CR_CARD_TRX_TP.INVOICE);
        ZYPEZDItemValueSetter.setValue(crCardTrxTMsg.firstTrxInfoTxt, bean.getInvNum());
        ZYPEZDItemValueSetter.setValue(crCardTrxTMsg.origCrCardTrxPk, bean.getCrCardTrxPk());
        ZYPEZDItemValueSetter.setValue(crCardTrxTMsg.authStsMsgCmntTxt, AUTH_STS_MSG_CMNT_TXT);
        ZYPEZDItemValueSetter.setValue(crCardTrxTMsg.setlCpltFlg, ZYPConstant.FLG_OFF_N);

        // CR_CARD_TRX Update
        EZDTBLAccessor.insert(crCardTrxTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(crCardTrxTMsg.getReturnCode())) {
            String errPadMsg = getInvRefNumErrorMsg(bean);
            S21InfoLogOutput.println(MSG_ID.NWAM0518E.toString(), toArray(errPadMsg));
        }
    }

    /**
     * <pre>
     * Update Credit Card Charge Complete Code Of INV
     * </pre>
     * @param bean NWAB204001Bean
     * @param crCardChrgCpltCd String
     * @return boolean
     */
    private boolean updateCrCardChrgCpltCdOfDsInv(NWAB204001Bean bean, String crCardChrgCpltCd) {

        INVTMsg invTMsg = new INVTMsg();
        ZYPEZDItemValueSetter.setValue(invTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(invTMsg.invNum, bean.getInvNum());

        invTMsg = (INVTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(invTMsg);

        ZYPEZDItemValueSetter.setValue(invTMsg.crCardChrgCpltCd, crCardChrgCpltCd);

        EZDTBLAccessor.update(invTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(invTMsg.getReturnCode())) {
            String errPadMsg = getInvRefNumErrorMsg(bean);
            S21InfoLogOutput.println(MSG_ID.NWAM0518E.toString(), toArray(errPadMsg));
            return false;
        }
        return true;
    }

    /**
     * <pre>
     * Get Credit Card Transaction Error Message
     * </pre>
     * @param keyName String
     * @param keyNum String
     * @return String
     */
    private String getCrCardTrxSearchErrorMsg(String keyName, String keyNum) {

        StringBuilder msg = new StringBuilder();
        msg.append("CR_CARD_TRX( GLBL_CMPY_CD: ");
        msg.append(this.glblCmpyCd);
        msg.append(", ");
        msg.append(keyName);
        msg.append(" : ");
        msg.append(keyNum);

        return msg.toString();
    }

    /**
     * <pre>
     * Get Invoice and Reference Number Error Message
     * </pre>
     * @param bean NWAB204001Bean
     * @return String
     */
    private String getInvRefNumErrorMsg(NWAB204001Bean bean) {

        StringBuilder msg = new StringBuilder();
        // Mod Start 2022/1/24 QC#56129
        //msg.append("Invoie#:");
        msg.append("Invoice#:");
        // Mod End   2022/1/24 QC#56129
        msg.append(cnvNullToSpace(bean.getInvNum()));
        msg.append(", CustomerReference#:");
        msg.append(cnvNullToSpace(bean.getTokenNum()));
        msg.append(", Authorization Reference#:");
        msg.append(cnvNullToSpace(bean.getAuthRefNum()));

        return msg.toString();
    }

    /**
     * <pre>
     * Set Decline Information Email
     * </pre>
     * @param crCardValidationPMsg NWZC203001PMsg
     * @param bean NWAB204001Bean
     * @param errMsg String
     */
    private void setDeclineInfoEmail(NWZC203001PMsg crCardValidationPMsg, NWAB204001Bean bean, String errMsg) {

        String sellToCustCd = bean.getSellToCustCd();
        // Add Start 2022/1/24 QC#56129
        String sellToLocNm = bean.getSellToLocNm();
        String cltPsnCd = bean.getCltPsnCd();
        String cltPsnNm = bean.getCltPsnNm();
        // Add End   2022/1/24 QC#56129
        NWAB204001MailBean mailBean;
        NWAB204001MailContentsLineBean lineBean = new NWAB204001MailContentsLineBean();

        lineBean.setInvNum(bean.getInvNum());
        if (crCardValidationPMsg != null) {
//            lineBean.setCustRefNum(crCardValidationPMsg.xxPmtcCustRefNum.getValue());
            lineBean.setCustRefNum(crCardValidationPMsg.crCardCustRefNum.getValue());
//            lineBean.setTrxRefNum(crCardValidationPMsg.xxPmtcTrxRefNum.getValue());
            lineBean.setTrxRefNum(crCardValidationPMsg.crCardAuthRefNum.getValue());
        } else {
            lineBean.setCustRefNum(bean.getCrCardCustRefNum());
            lineBean.setTrxRefNum(bean.getAuthRefNum());
        }

        lineBean.setInvDt(bean.getInvDt());
        lineBean.setInvTotFuncTaxAmt(bean.getInvTotFuncTaxAmt());
        lineBean.setInvTotFuncNetAmt(bean.getInvTotFuncNetAmt());
        lineBean.setInvTotDealNetAmt(bean.getInvTotDealNetAmt());

        if (this.emailBodyMap.containsKey(sellToCustCd)) {
            mailBean = this.emailBodyMap.get(sellToCustCd);
            mailBean.setContentsLine(lineBean);
            mailBean.setPreAuthHoldCnt(emailBodyMap.get(sellToCustCd).getPreAuthHoldCnt() + 1);
            mailBean.setMsgToAuthErrRepMsgList(errMsg);

        } else {
            mailBean = new NWAB204001MailBean();
            mailBean.setSellToCustCd(sellToCustCd);
            // Add Start 2022/1/24 QC#56129
            mailBean.setSellToLocNm(sellToLocNm);
            mailBean.setCltPsnCd(cltPsnCd);
            mailBean.setCltPsnNm(cltPsnNm);
            // Add End   2022/1/24 QC#56129
            mailBean.setSlsDt(slsDt);
            mailBean.setContentsLine(lineBean);
            mailBean.setPreAuthHoldCnt(BigDecimal.ONE.intValue());
            mailBean.setMsgToAuthErrRepMsgList(errMsg);
            this.emailBodyMap.put(sellToCustCd, mailBean);
        }
    }

    /**
     * <pre>
     * Send Mail
     * </pre>
     */
    private void sendMail() {

        StringBuilder contents = new StringBuilder();

        // Mail Contents
        for (Iterator<Entry<String, NWAB204001MailBean>> it = this.emailBodyMap.entrySet().iterator(); it.hasNext();) {
            Map.Entry<String, NWAB204001MailBean> entry = (Map.Entry<String, NWAB204001MailBean>) it.next();
            contents.append(((NWAB204001MailBean) entry.getValue()).getMailTxt());
            contents.append(CRLF).append(CRLF);
        }

        S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_FROM);
        groupFrom.setMailKey1(MAIL_KEY_FROM);

        S21MailAddress fromAddr = null;
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

        if (addrFromList != null && !addrFromList.isEmpty()) {

            fromAddr = addrFromList.get(0);
            S21Mail mail = new S21Mail(glblCmpyCd);
            mail.setFromAddress(fromAddr);

            S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_TO);
            groupTo.setMailKey1(MAIL_KEY_TO);

            List<S21MailAddress> addrToList = groupTo.getMailAddress();
            if (addrToList.isEmpty()) {
                String[] errMsg = new String[] {MAIL_GROUP_ID_TO, MAIL_KEY_TO };
                S21InfoLogOutput.println(MSG_ID.NWAM0516E.toString(), errMsg);
                throw new S21AbendException(MSG_ID.NWAM0516E.toString(), errMsg);
            }
            mail.setToAddressList(addrToList);

            S21MailTemplate mailTemp = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID_M001);

            if (mailTemp != null) {
                mailTemp.setTemplateParameter(MAIL_TEMPLATE_KEY_CONTENT, contents.toString());
                mailTemp.setTemplateParameter(MAIL_TEMPLATE_KEY_SLS_DT, ZYPDateUtil.DateFormatter(slsDt, ZYPDateUtil.TYPE_YYYYMMDD, MAIL_OUT_TYPE_MM_DD_YYYY));
                mail.setMailTemplate(mailTemp);
                String resCd = mail.postMail();
                if (!ZYPCommonFunc.hasValue(resCd)) {
                    throw new S21AbendException(MSG_ID.NWAM0268E.toString());
                }
            } else {
                String[] errMsg = new String[] {MAIL_TEMPLATE_ID_M001 };
                S21InfoLogOutput.println(MSG_ID.NWAM0319E.toString(), errMsg);
                throw new S21AbendException(MSG_ID.NWAM0319E.toString(), errMsg);
            }
        } else {
            String[] errMsg = new String[] {MAIL_GROUP_ID_FROM, MAIL_KEY_FROM };
            S21InfoLogOutput.println(MSG_ID.NWAM0516E.toString(), errMsg);
            throw new S21AbendException(MSG_ID.NWAM0516E.toString(), errMsg);
        }
    }

    /**
     * <pre>
     * Post Error Mail
     * </pre>
     * @return boolean
     */
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

        boolean isNormalEnd = new NWXC001001Mail().postMail(glblCmpyCd, "FROM0005", MAIL_TEMPLATE_ID_M002, sbsStrList);

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
     * <pre>
     * Conversion Null To Space
     * </pre>
     * @param cnvStr String
     * @return String
     */
    private String cnvNullToSpace(String cnvStr) {

        if (!ZYPCommonFunc.hasValue(cnvStr)) {
            return ONE_SPACE;
        }
        return cnvStr;
    }
    // Add QC#58621 Start
    private String createName (String name) {
        if (!name.contains(" ")) {
           name =  "*" + name;
        } else {
            int cnt = name.lastIndexOf(" ") + 1;
            StringBuilder sb = new StringBuilder(name);
            sb.insert(cnt, "*");
            return sb.toString();
        }
        return name;
    }

    private void insErrCrCartTrxDtl(NWZC203001PMsg pMsg, NWAB204001Bean bean) {

        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
            NWZC203001_APMsg apMsg = pMsg.A.no(i);

            // QC#58621-1
            if (ZYPCommonFunc.hasValue(bean.getCrCardTrxPk())) {
                CR_CARD_TRX_DTLTMsg tMsg = new CR_CARD_TRX_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.crCardTrxDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.CR_CARD_TRX_DTL_SQ));
                ZYPEZDItemValueSetter.setValue(tMsg.crCardTrxPk, bean.getCrCardTrxPk());
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(tMsg.crCardTrxDtlLineNum, apMsg.crCardTrxDtlLineNum);
                ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthMdseNm, apMsg.crCardAuthMdseNm);
                ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthProdCd, apMsg.crCardAuthProdCd);
                ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthOrdQty, apMsg.crCardAuthOrdQty);
                ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthUomCd, apMsg.crCardAuthUomCd);
                ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthDtlTaxAmt, apMsg.crCardAuthDtlTaxAmt);
                ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthDtlTaxPct, apMsg.crCardAuthDtlTaxPct);
                ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthDtlAmt, apMsg.crCardAuthDtlAmt);
                ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthDtlDiscAmt, apMsg.crCardAuthDtlDiscAmt);
                ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthCmdtyCd, apMsg.crCardAuthCmdtyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthUnitPrcAmt, apMsg.crCardAuthUnitPrcAmt);
                ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthGrsNetInd, apMsg.crCardAuthGrsNetInd);
                ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthTaxTpCd, apMsg.crCardAuthTaxTpCd);
                ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthDiscInd, apMsg.crCardAuthDiscInd);

                EZDTBLAccessor.insert(tMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    String errPadMsg = getInvRefNumErrorMsg(bean);
                    S21InfoLogOutput.println(MSG_ID.NWAM0518E.toString(), toArray(errPadMsg));
                }
            }
        }
    }
    // Add QC#58621 End
}
