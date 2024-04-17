/**
 * <Pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC229001;

import static com.canon.cusa.s21.api.NWZ.NWZC229001.NWZC229001Constant.*;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPMaxTenDigitsNumbering;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

import business.db.DS_COND_CONSTTMsg;
import business.db.DS_COND_CONSTTMsgArray;
import business.db.ECHK_HOST_RSP_STSTMsg;
import business.db.ECHK_RCPT_TRXTMsg;
import business.db.ECHK_RCPT_TRX_DTLTMsg;
import business.db.MSTR_DEF_INFOTMsg;
import business.db.MSTR_DEF_INFOTMsgArray;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.parts.NWZC229001PMsg;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ECHK_PMT_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.usa.s21.integration.service.proxy.creditCardProcessingService.cpgm.S21CreditCardProcessingCpgmServiceProxy;
import com.canon.usa.s21.integration.service.proxy.creditCardProcessingService.cpgm.paymentechGateway.NewOrderRequestElement;
import com.canon.usa.s21.integration.service.proxy.creditCardProcessingService.cpgm.paymentechGateway.NewOrderResponseElement;

/**
 * <pre> 
 *  eCheck Interface API.
 *  
 *  
 *  
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/12/2023   Hitachi         M.Hashino       Create          QC#55645
 * 11/17/2023   Hitachi         Y.Ogura         Update          QC#62010
 *</pre>
 */

public class NWZC229001 extends S21ApiCommonBase {

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap;

    /** SQL access parts (BatchClient) */
    private S21SsmBatchClient ssmBatchClient = null;

    /** eCheck payment status code  */
    private String echkPmtStsCd = ECHK_PMT_STS.NORMAL;

    /** eCheck payment request ID */
    private String echkPmtReqId = null;

    /** Access mode check (LOG) */
    private String modeLogFlg = null;

    /** Access mode check (TEST) */
    private String modeTestFlg = null;

    /** Timeout Exception Flag */
    private boolean timeoutExceptionFlg = false;

    /** Other Exception Flag */
    private boolean otherExceptionFlg = false;

    /** Error count */
    private int errCnt = 0;

    /** CPGM Request Bean */
    NewOrderRequestElement reqBean = new NewOrderRequestElement();

    /** CPGM Response Bean */
    NewOrderResponseElement respBean = new NewOrderResponseElement();

    /**
     * <pre>
     * Constructor
     * </pre>
     */
    public NWZC229001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * <pre>
     * eCheck Interface API Main Flow
     * </pre>
     * @param inpPrmPMsg Input parameter
     * @param onBatchType Kind of Online or Batch.
     */
    public void execute(NWZC229001PMsg inpPrmPMsg, ONBATCH_TYPE onBatchType) {
        printDebugLog(PROGRAM_ID + "[ execute ] start");

        this.msgMap = new S21ApiMessageMap(inpPrmPMsg);

        doProcess(onBatchType);

        this.msgMap.flush();

        printDebugLog(PROGRAM_ID + "[ execute ] end");
    }

    /**
     * <pre>
     * The eCheck payment processing is executed.
     * </pre>
     * @param msgMap S21ApiMessageMap
     * @param onBatch ONBATCH_TYPE
     */
    private void doProcess(ONBATCH_TYPE onBatch) {
        NWZC229001PMsg pMsg = (NWZC229001PMsg) this.msgMap.getPmsg();
        
        // Reset xxMsgIdList validCount
        pMsg.xxMsgIdList.setValidCount(0);

        // Check Access Mode Test
        this.modeTestFlg = ZYPCodeDataUtil.getVarCharConstValue("ECHK_ACCESS_MODE_TEST_FLG", pMsg.glblCmpyCd.getValue());
        if (!ZYPCommonFunc.hasValue(this.modeTestFlg)) {
            this.modeTestFlg = ZYPConstant.FLG_OFF_N;
        } else if (ZYPConstant.FLG_ON_Y.equals(this.modeTestFlg)) {
            callCpgmTestModule();
            return;
        }

        // Check Access Mode Log
        this.modeLogFlg = ZYPCodeDataUtil.getVarCharConstValue("ECHK_ACCESS_MODE_LOG_FLG", pMsg.glblCmpyCd.getValue());
        if (!ZYPCommonFunc.hasValue(this.modeLogFlg)) {
            this.modeLogFlg = ZYPConstant.FLG_ON_Y;
        }

        // Check Mandatory Input Parameter
        chkInputParam(pMsg);
        if (hasError()) {
            this.echkPmtStsCd = ECHK_PMT_STS.ERROR;
            this.errCnt++;
            pMsg.xxMsgIdList.setValidCount(this.errCnt);
        }

        // Check Master Registration of Parameter
        if (ECHK_PMT_STS.NORMAL.equals(echkPmtStsCd)) {
            chkMstr(pMsg);
            if (hasError()) {
                this.echkPmtStsCd = ECHK_PMT_STS.ERROR;
                this.errCnt++;
                pMsg.xxMsgIdList.setValidCount(this.errCnt);
            }
        }

        // Check Digits of echkPmtAmt
        if (ECHK_PMT_STS.NORMAL.equals(echkPmtStsCd)) {
            chkDigit(pMsg);
            if (hasError()) {
                this.echkPmtStsCd = ECHK_PMT_STS.ERROR;
                this.errCnt++;
                pMsg.xxMsgIdList.setValidCount(this.errCnt);
            }
        }

        // Relation Check
        if (ECHK_PMT_STS.NORMAL.equals(echkPmtStsCd)) {
            chkReln(pMsg);
            if (hasError()) {
                this.echkPmtStsCd = ECHK_PMT_STS.ERROR;
                this.errCnt++;
                pMsg.xxMsgIdList.setValidCount(this.errCnt);
            }
        }

        // get echkPmtReqId, and set it to pMsg
        this.echkPmtReqId = ZYPMaxTenDigitsNumbering.getUniqueID("ECHK_PMT_REQ_ID");
        ZYPEZDItemValueSetter.setValue(pMsg.echkPmtReqId, this.echkPmtReqId);

        // Set Parameter to reqBean
        setParmToBean(pMsg);

        // Call CPGM
        if (ECHK_PMT_STS.NORMAL.equals(echkPmtStsCd)) {
            callCPGM(pMsg);
        }

        // Set Response data to PMsg
        setRespToPMsg(pMsg);

        // ECHK_RCPT_TRX, ECHK_RCPT_TRX_DTL
        insertEchkRcptTrx(pMsg);

    }

    /**
     * <pre>
     * Check the input parameters.
     * If an error occurs, add a message to the Message Map, and Change Status Code.
     * </pre>
     * @param msgMap Message Map
     */
    private void chkInputParam(NWZC229001PMsg pMsg) {
        printDebugLog(PROGRAM_ID + "[ chkInp ] start");

        // Common Mandatory Check
        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            this.msgMap.addXxMsgId(ZZZM9025E);
            pMsg.xxMsgIdList.no(this.errCnt).xxMsgTxt.setValue(getRtnMsg(ZZZM9025E, new String[] {"Global Company Code" }));
            return;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.slsDt)) {
            this.msgMap.addXxMsgId(ZZZM9025E);
            pMsg.xxMsgIdList.no(this.errCnt).xxMsgTxt.setValue(getRtnMsg(ZZZM9025E, new String[] {"Sales Date" }));
            return;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.bankRteNum)) {
            this.msgMap.addXxMsgId(ZZZM9025E);
            pMsg.xxMsgIdList.no(this.errCnt).xxMsgTxt.setValue(getRtnMsg(ZZZM9025E, new String[] {"Bank Routing Number" }));
            return;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.dsBankAcctNum)) {
            this.msgMap.addXxMsgId(ZZZM9025E);
            pMsg.xxMsgIdList.no(this.errCnt).xxMsgTxt.setValue(getRtnMsg(ZZZM9025E, new String[] {"Bank Account Number" }));
            return;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.dsCustBankAcctRelnPk)) {
            this.msgMap.addXxMsgId(ZZZM9025E);
            pMsg.xxMsgIdList.no(this.errCnt).xxMsgTxt.setValue(getRtnMsg(ZZZM9025E, new String[] {"Customer Bank Account Relation PK" }));
            return;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.billToCustAcctCd)) {
            this.msgMap.addXxMsgId(ZZZM9025E);
            pMsg.xxMsgIdList.no(this.errCnt).xxMsgTxt.setValue(getRtnMsg(ZZZM9025E, new String[] {"Sell To Customer Code" }));
            return;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.echkPmtAmt)) {
            this.msgMap.addXxMsgId(ZZZM9025E);
            pMsg.xxMsgIdList.no(this.errCnt).xxMsgTxt.setValue(getRtnMsg(ZZZM9025E, new String[] {"Total Amount of eCheck" }));
            return;
        }
        printDebugLog(PROGRAM_ID + "[ chkInp ] end");
    }

    /**
     * <pre>
     * Check Master for input Parameter.
     * If an error occurs, add a message to the Message Map, and Change Status Code.
     * </pre>
     * @param msgMap Message Map
     */
    private void chkMstr(NWZC229001PMsg pMsg) {
        printDebugLog(PROGRAM_ID + "[ chkMstr ] start");
        // Bank Routing Number
        if (ZYPCommonFunc.hasValue(pMsg.bankRteNum)) {
            final Map<String, Object> ssmParam1 = new HashMap<String, Object>();
            ssmParam1.put("glblCmpyCd", pMsg.glblCmpyCd);
            ssmParam1.put("dsBankAcctNum", pMsg.dsBankAcctNum);
            ssmParam1.put("bankRteNum", pMsg.bankRteNum);
            ssmParam1.put("slsDt", pMsg.slsDt);

            Map<String, Object> map1 = (Map<String, Object>) ssmBatchClient.queryObject("chkMstrBankAcct", ssmParam1);
            if (map1 == null) {
                this.msgMap.addXxMsgId(NWZM2315E);
                pMsg.xxMsgIdList.no(this.errCnt).xxMsgTxt.setValue(getRtnMsg(NWZM2315E, new String[] {"Bank Routing Number and Bank Account Number" }));
                return;
            }
        }

        // Sell To Customer Code
        if (ZYPCommonFunc.hasValue(pMsg.billToCustAcctCd)) {
            if (!existsSellToCustCd(pMsg.glblCmpyCd.getValue(), pMsg.billToCustAcctCd.getValue())) {
                this.msgMap.addXxMsgId(NWZM2315E);
                pMsg.xxMsgIdList.no(this.errCnt).xxMsgTxt.setValue(getRtnMsg(NWZM2315E, new String[] {"Sell To Customer Code" }));
                return;
            }
        }

        // get and check Location Name
        SELL_TO_CUSTTMsg sellToCustTMsg = getSellToCustArry(pMsg);
        ZYPEZDItemValueSetter.setValue(pMsg.locNm, sellToCustTMsg.locNm.getValue());
        if (!ZYPCommonFunc.hasValue(pMsg.locNm)) {
            this.msgMap.addXxMsgId(NWZM2315E);
            pMsg.xxMsgIdList.no(this.errCnt).xxMsgTxt.setValue(getRtnMsg(NWZM2315E, new String[] {"Location Name" }));
            return;
        }
        printDebugLog(PROGRAM_ID + "[ chkMstrRegist ] end");
    }

    /**
     * <pre>
     * Check Digit of echkPmtAmt.
     * If an error occurs, add a message to the Message Map, and Change Status Code.
     * </pre>
     * @param msgMap Message Map
     */
    private void chkDigit(NWZC229001PMsg pMsg) {
        printDebugLog(PROGRAM_ID + "[ chkDigit ] start");
        Integer intPartDigit = INTEGER_PART_DIGIT_13;
        Integer fracPartDigit = FRAC_PART_DIGIT_2;
        if (intPartDigit.compareTo(pMsg.echkPmtAmt.getValue().precision()) < 0 || fracPartDigit.compareTo(pMsg.echkPmtAmt.getValue().scale()) < 0) {
            this.msgMap.addXxMsgId(NWZM2318E);
            pMsg.xxMsgIdList.no(this.errCnt).xxMsgTxt.setValue(S21MessageFunc.clspGetMessage(NWZM2318E));
            return;
        }
        printDebugLog(PROGRAM_ID + "[ chkDigit ] end");
    }

    /**
     * <pre>
     * Check Relation between Bank and Account.
     * If an error occurs, add a message to the Message Map, and Change Status Code.
     * </pre>
     * @param msgMap Message Map
     */
    private void chkReln(NWZC229001PMsg pMsg) {
        printDebugLog(PROGRAM_ID + "[ chkReln ] start");
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd);
        ssmParam.put("dsCustBankAcctRelnPk", pMsg.dsCustBankAcctRelnPk);
        ssmParam.put("sellToCustCd", pMsg.billToCustAcctCd);

        Map<String, Object> map = (Map<String, Object>) ssmBatchClient.queryObject("relationCheck", ssmParam);
        if (map == null) {
            this.msgMap.addXxMsgId(NWZM2317E);
            pMsg.xxMsgIdList.no(this.errCnt).xxMsgTxt.setValue(getRtnMsg(NWZM2317E, new String[] {"Bank", "Account" }));
        }
        printDebugLog(PROGRAM_ID + "[ chkReln ] end");
    }

    /**
     * <pre>
     * Set Parameter to Order Request Bean
     * </pre>
     * @param pMsg NWZC229001PMsg
     */
    private void setParmToBean(NWZC229001PMsg pMsg) {
        String orderId = S21StringUtil.concatStrings(pMsg.billToCustAcctCd.getValue(), "-", this.echkPmtReqId);
        this.reqBean.setOrderID(orderId);
        this.reqBean.setEcpCheckDDA(pMsg.dsBankAcctNum.getValue());
        this.reqBean.setEcpCheckRT(pMsg.bankRteNum.getValue());
        // START 2023/11/16 Y.Ogura [QC#62010, MOD]
//        this.reqBean.setAvsName(pMsg.locNm.getValue());
        String locNm = null;
        locNm = prohibitCharCheck(pMsg.glblCmpyCd.getValue(),pMsg.locNm.getValue());
        
        int locNmMaxLength = ZYPCodeDataUtil.getNumConstValue(ECHK_LOC_NM_MAX_LEN, pMsg.glblCmpyCd.getValue()).intValue();
        if (locNm.length() > locNmMaxLength) {
            locNm = locNm.substring(0, locNmMaxLength);
        }
        
        this.reqBean.setAvsName(locNm);
        // END 2023/11/16 Y.Ogura [QC#62010, MOD]
        this.reqBean.setRetryTrace(orderId);
        if (ZYPCommonFunc.hasValue(pMsg.echkPmtAmt)) {
            this.reqBean.setAmount(pMsg.echkPmtAmt.getValue().multiply(ECHK_AMT_REQ_100).setScale(0).toString());
        }

        if (ECHK_PMT_STS.NORMAL.equals(echkPmtStsCd)) {
            Map<String, Object> sqlParam = new HashMap<String, Object>();
            sqlParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
            sqlParam.put("billToCustAcctCd", pMsg.billToCustAcctCd.getValue());
            sqlParam.put("ctacTpCdAp", CTAC_TP.ACCOUNT_PAYABLE);
            sqlParam.put("ctacTpCdBillToContact", CTAC_TP.BILL_TO_CONTACT);
            sqlParam.put("slsDt", pMsg.slsDt.getValue());
            sqlParam.put("dsCtacPntTpCd01", DS_CTAC_PNT_TP.PHONE_MOBILE);
            sqlParam.put("dsCtacPntTpCd02", DS_CTAC_PNT_TP.PHONE_WORK);
            sqlParam.put("dsCtacPntTpCd04", DS_CTAC_PNT_TP.EMAIL_WORK);
            sqlParam.put("actvFlg", ZYPConstant.FLG_ON_Y);

            Map<String, Object> map = (Map<String, Object>) ssmBatchClient.queryObject("getCustomerInfo", sqlParam);
            if (map != null) {
                String telNum = (String) map.get("TEL_NUM");
                String mailAddr = (String) map.get("MAIL");
                String postCd = (String) map.get("POST_CD");

                if (ZYPCommonFunc.hasValue(telNum)) {
                    if (telNum.length() > TELL_NUM_MAX_LENGTH) {
                        telNum = telNum.substring(0, TELL_NUM_MAX_LENGTH);
                    }
                    this.reqBean.setAvsPhone(telNum);
                }

                if (ZYPCommonFunc.hasValue(mailAddr)) {
                    if (ZYPCommonFunc.hasValue(mailAddr) && mailAddr.length() > EML_ADDR_MAX_LENGTH) {
                        mailAddr = mailAddr.substring(0, EML_ADDR_MAX_LENGTH);
                    }
                    this.reqBean.setCustomerEmail(mailAddr);
                }

                if (ZYPCommonFunc.hasValue(postCd)) {
                    // START 2023/11/16 Y.Ogura [QC#62010, MOD]
//                    this.reqBean.setAvsZip(map.get("POST_CD").toString());
                    int postCdMaxLength = ZYPCodeDataUtil.getNumConstValue(ECHK_POST_CD_MAX_LEN, pMsg.glblCmpyCd.getValue()).intValue();
                    if (postCd.length() > postCdMaxLength) {
                        postCd = postCd.substring(0, postCdMaxLength);
                    }
                    this.reqBean.setAvsZip(postCd);
                    // END 2023/11/16 Y.Ogura [QC#62010, MOD]
                }
            }

            // Set eCheck Constant Parameter to reqBean
            this.reqBean.setVersion(getEchkConstParam(pMsg.glblCmpyCd.getValue(), MSTR_FUNC_ID, MSTR_COL_ID_VRSN));
            this.reqBean.setIndustryType(getEchkConstParam(pMsg.glblCmpyCd.getValue(), MSTR_FUNC_ID, MSTR_COL_ID_INDY));
            this.reqBean.setTransType(getEchkConstParam(pMsg.glblCmpyCd.getValue(), MSTR_FUNC_ID, MSTR_COL_ID_TRX));
            this.reqBean.setBin(getEchkConstParam(pMsg.glblCmpyCd.getValue(), MSTR_FUNC_ID, MSTR_COL_ID_BIN));
            this.reqBean.setMerchantID(getEchkConstParam(pMsg.glblCmpyCd.getValue(), MSTR_FUNC_ID, MSTR_COL_ID_MRCNT));
            this.reqBean.setTerminalID(getEchkConstParam(pMsg.glblCmpyCd.getValue(), MSTR_FUNC_ID, MSTR_COL_ID_TRM));
            this.reqBean.setCardBrand(getEchkConstParam(pMsg.glblCmpyCd.getValue(), MSTR_FUNC_ID, MSTR_COL_ID_CARD_BRAND));
            this.reqBean.setCcCardVerifyPresenceInd(getEchkConstParam(pMsg.glblCmpyCd.getValue(), MSTR_FUNC_ID, MSTR_COL_ID_CARD_PRSN));
            this.reqBean.setEcpBankAcctType(getEchkConstParam(pMsg.glblCmpyCd.getValue(), MSTR_FUNC_ID, MSTR_COL_ID_BANK));
            this.reqBean.setEcpAuthMethod(getEchkConstParam(pMsg.glblCmpyCd.getValue(), MSTR_FUNC_ID, MSTR_COL_ID_AUTH));
            this.reqBean.setEcpDelvMethod(getEchkConstParam(pMsg.glblCmpyCd.getValue(), MSTR_FUNC_ID, MSTR_COL_ID_DELV));
            this.reqBean.setAddProfileFromOrder(getEchkConstParam(pMsg.glblCmpyCd.getValue(), MSTR_FUNC_ID, MSTR_COL_ID_PRFL_FROM_ORD));
            this.reqBean.setProfileOrderOverideInd(getEchkConstParam(pMsg.glblCmpyCd.getValue(), MSTR_FUNC_ID, MSTR_COL_ID_ORD_OVRD));
        }
    }

    /**
     * <pre>
     * get eCheck constant Parameter from MSTR_DEF_INFO
     * </pre>
     * @param glblCmpyCd String
     * @param funcId String
     * @param colId String
     * @param mstrValue String
     */
    private static String getEchkConstParam(String glblCmpyCd, String funcId, String colId) {
        final MSTR_DEF_INFOTMsg tMsg = new MSTR_DEF_INFOTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("mstrFuncId01", funcId);
        tMsg.setConditionValue("mstrColId01", colId);

        MSTR_DEF_INFOTMsgArray rsltArry = (MSTR_DEF_INFOTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);

        if (rsltArry.length() == 0) {
            return null;
        }
        return rsltArry.no(0).mstrDefInfoTxt.getValue();
    }

    /**
     * <pre>
     * call CPGM, and get respBean.
     * </pre>
     * @param pMsg NWZC229001PMsg
     */
    private void callCPGM(NWZC229001PMsg pMsg) {
        // call CPGM
        printDebugLog(PROGRAM_ID + "[ callCPGM ] start");
        try {
            String modeDevFlg = null;
            modeDevFlg = ZYPCodeDataUtil.getVarCharConstValue("ECHK_ACCESS_MODE_DEV_FLG", pMsg.glblCmpyCd.getValue());
            if (ZYPConstant.FLG_ON_Y.equals(modeDevFlg)) {
               getRespBeanForDevEnv();
            } else {
                S21CreditCardProcessingCpgmServiceProxy s21CreditCardProcessingCpgmServiceProxy = new S21CreditCardProcessingCpgmServiceProxy();
                this.respBean = s21CreditCardProcessingCpgmServiceProxy.newOrder(this.reqBean);
                if (ZYPConstant.FLG_ON_Y.equals(modeLogFlg)) {
                    printEchkRespLog();
                }
            }
        } catch (RemoteException e) {
            S21InfoLogOutput.println("Catch RemoteException (TimeoutException)");
            this.timeoutExceptionFlg = true;
            this.printErr(e, pMsg);
        } catch (Exception e) {
            S21InfoLogOutput.println("Catch Other Exception");
            this.otherExceptionFlg = true;
            this.printErr(e, pMsg);
        }
        printDebugLog(PROGRAM_ID + "[ callCPGM ] end");
    }

    /**
     * <pre>
     * print Error
     * </pre>
     * @param msgMap S21ApiMessage Map
     * @param e Exception
     * @param pMsg NWZC229001PMsg
     */
    private void printErr(Throwable e, NWZC229001PMsg pMsg) {

        if (this.timeoutExceptionFlg) {
            this.msgMap.addXxMsgId(NWZM2314E);
            pMsg.xxMsgIdList.no(this.errCnt).xxMsgTxt.setValue(S21MessageFunc.clspGetMessage(NWZM2314E));
        } else if (this.otherExceptionFlg) {
            this.msgMap.addXxMsgId(NWZM2319E);
            pMsg.xxMsgIdList.no(this.errCnt).xxMsgTxt.setValue(S21MessageFunc.clspGetMessage(NWZM2319E));
        }
        this.echkPmtStsCd = ECHK_PMT_STS.ERROR;
        this.errCnt++;
        pMsg.xxMsgIdList.setValidCount(this.errCnt);

        String enter = System.getProperty("line.separator");

        StringBuilder stackStr = new StringBuilder();

        stackStr.append(e.toString());
        stackStr.append(enter);

        StackTraceElement[] elements = e.getStackTrace();
        for (StackTraceElement emt : elements) {
            stackStr.append(emt.toString());
            stackStr.append(enter);
        }
        println(stackStr.toString());
    }

    /**
     * <pre>
     * Return RespBean For Development Environment
     * </pre>
     */
    private void getRespBeanForDevEnv() throws RemoteException {
        this.respBean.setTxRefIdx("0");
        this.respBean.setRespDateTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        this.respBean.setOrderID(this.reqBean.getOrderID());
        this.respBean.setHostRespCode("100");
    }

    /**
     * <pre>
     * Set Response Code to PMsg
     * </pre>
     * @param pMsg NWZC229001PMsg
     */
    private void setRespToPMsg(NWZC229001PMsg pMsg) {
        ZYPEZDItemValueSetter.setValue(pMsg.echkHostRspStsCd, this.respBean.getHostRespCode());
        if (ZYPCommonFunc.hasValue(pMsg.echkHostRspStsCd)) {
            ZYPEZDItemValueSetter.setValue(pMsg.echkHostRspStsDescTxt, getCPGMRspMsg(pMsg));
            if (!pMsg.echkHostRspStsCd.getValue().toString().equals(ZYPCodeDataUtil.getVarCharConstValue("ECHK_APVL_STS_APPROVED", pMsg.glblCmpyCd.getValue()))) {
                this.msgMap.addXxMsgId(NWZM2316E);
                pMsg.xxMsgIdList.no(this.errCnt).xxMsgTxt.setValue(getRtnMsg(NWZM2316E, new String[] {pMsg.echkHostRspStsDescTxt.getValue() }));
                this.echkPmtStsCd = ECHK_PMT_STS.ERROR;
                this.errCnt++;
                pMsg.xxMsgIdList.setValidCount(this.errCnt);
            }
        }
    }

    /**
     * <pre>
     * Get Response Message From CPGM.
     * </pre>
     * @param pMsg NWZC229001PMsg
     */
    private String getCPGMRspMsg(NWZC229001PMsg pMsg) {
        String rspMsg = null;
        ECHK_HOST_RSP_STSTMsg inMsg = new ECHK_HOST_RSP_STSTMsg();
        ECHK_HOST_RSP_STSTMsg outMsg = new ECHK_HOST_RSP_STSTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.echkHostRspStsCd, pMsg.echkHostRspStsCd);
        outMsg = (ECHK_HOST_RSP_STSTMsg) S21ApiTBLAccessor.findByKey(inMsg);
        if (outMsg != null && ZYPCommonFunc.hasValue(outMsg.echkHostRspStsDescTxt)) {
            rspMsg = outMsg.echkHostRspStsDescTxt.getValue();
        } else {
            // new error code
            StringBuilder sb = new StringBuilder();
            sb.append(ERROR_CODE_HDR);
            sb.append(pMsg.echkHostRspStsCd.getValue());
            rspMsg = sb.toString();
        }
        return rspMsg;
    }

    /**
     * <pre>
     * Insert eCheck Payment Result into transaction table.
     * </pre>
     * @param pMsg NWZC229001PMsg
     */
    private void insertEchkRcptTrx(NWZC229001PMsg pMsg) {
        printDebugLog(PROGRAM_ID + "[ insertEchkRcptTrx ] start");
        // ECHK_RCPT_TRX
        BigDecimal amt = new BigDecimal(this.reqBean.getAmount());
        ECHK_RCPT_TRXTMsg inTMsg = new ECHK_RCPT_TRXTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.echkPmtReqId, this.echkPmtReqId);
        ZYPEZDItemValueSetter.setValue(inTMsg.echkPmtStsCd, this.echkPmtStsCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.billToCustAcctCd, pMsg.billToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.dsCustBankAcctRelnPk, pMsg.dsCustBankAcctRelnPk);
        ZYPEZDItemValueSetter.setValue(inTMsg.dsBankAcctNum, this.reqBean.getEcpCheckDDA());
        ZYPEZDItemValueSetter.setValue(inTMsg.bankRteNum, this.reqBean.getEcpCheckRT());
        ZYPEZDItemValueSetter.setValue(inTMsg.locNm, this.reqBean.getAvsName());
        ZYPEZDItemValueSetter.setValue(inTMsg.echkPmtAmt, amt.divide(ECHK_AMT_REQ_100));
        ZYPEZDItemValueSetter.setValue(inTMsg.echkRtryTraceNum, this.reqBean.getRetryTrace());
        ZYPEZDItemValueSetter.setValue(inTMsg.postCd, this.reqBean.getAvsZip());
        ZYPEZDItemValueSetter.setValue(inTMsg.ctacPsnTelNum, this.reqBean.getAvsPhone());
        ZYPEZDItemValueSetter.setValue(inTMsg.ctacPsnEmlAddr, this.reqBean.getCustomerEmail());
        ZYPEZDItemValueSetter.setValue(inTMsg.echkHostRspStsCd, this.respBean.getHostRespCode());
        ZYPEZDItemValueSetter.setValue(inTMsg.echkTrxRefNum, this.respBean.getTxRefNum());
        ZYPEZDItemValueSetter.setValue(inTMsg.arCashApplyFlg, ZYPConstant.FLG_OFF_N);
        S21ApiTBLAccessor.insert(inTMsg);
        // DB Error
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            this.msgMap.addXxMsgId(NWDM0007E);
            pMsg.xxMsgIdList.setValidCount(this.errCnt + 1);
        }

        // ECHK_RCPT_TRX_DTL
        if (pMsg.InvList.getValidCount() > 0) {
            for (int i = 0; i < pMsg.InvList.getValidCount(); i++) {
                ECHK_RCPT_TRX_DTLTMsg inDtlTMsg = new ECHK_RCPT_TRX_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(inDtlTMsg.glblCmpyCd, pMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(inDtlTMsg.echkRcptTrxDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ECHK_RCPT_TRX_DTL_SQ));
                ZYPEZDItemValueSetter.setValue(inDtlTMsg.echkPmtReqId, this.echkPmtReqId);
                ZYPEZDItemValueSetter.setValue(inDtlTMsg.invNum, pMsg.InvList.no(i).invNum.getValue());
                ZYPEZDItemValueSetter.setValue(inDtlTMsg.invTotDealNetAmt, pMsg.InvList.no(i).invTotDealNetAmt.getValue());
                S21ApiTBLAccessor.insert(inDtlTMsg);
                // DB Error
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inDtlTMsg.getReturnCode())) {
                    this.msgMap.addXxMsgId(NWDM0007E);
                    pMsg.xxMsgIdList.setValidCount(pMsg.xxMsgIdList.getValidCount() + 1);
                }
            }
        }
        printDebugLog(PROGRAM_ID + "[ insertEchkRcptTrx ] end");
    }

    /**
     * Get Return Message
     * @param msgId String
     * @param prm String[]
     * @return rtnVal String
     */
    public static String getRtnMsg(String msgId, String[] prm) {
        String rtnVal = "";
        if (ZYPCommonFunc.hasValue(msgId)) {
            rtnVal = S21MessageFunc.clspGetMessage(msgId, prm);
            rtnVal = rtnVal.substring(msgId.length()).trim();
        }
        return rtnVal;
    }

    /**
     * existsSellToCustCd
     * @param glblCmpyCd String
     * @param cd String
     * @return boolean
     */
    private static boolean existsSellToCustCd(String glblCmpyCd, String cd) {
        if (!ZYPCommonFunc.hasValue(cd)) {
            return true;
        }

        final SELL_TO_CUSTTMsg tMsg = new SELL_TO_CUSTTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("sellToCustCd01", cd);

        SELL_TO_CUSTTMsgArray sellToCustArry = (SELL_TO_CUSTTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
        if (sellToCustArry.length() == 0) {
            return false;
        }
        return true;
    }

    /**
     * getSellToCustArry To check existence of locNm.
     * @param pMsg NWZC229001PMsg
     * @return sellToCustArry
     */
    private static SELL_TO_CUSTTMsg getSellToCustArry(NWZC229001PMsg pMsg) {
        final SELL_TO_CUSTTMsg tMsg = new SELL_TO_CUSTTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        tMsg.setConditionValue("sellToCustCd01", pMsg.billToCustAcctCd.getValue());
        // execute Query
        SELL_TO_CUSTTMsgArray sellToCustArry = (SELL_TO_CUSTTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
        if (sellToCustArry == null) {
            return null;
        }
        return sellToCustArry.no(0);
    }

    /**
     * <pre>
     * Check error message id exists or not.
     * </pre>
     */
    private boolean hasError() {
        if (!this.msgMap.getMsgMgr().isXxMsgId()) {
            return false;
        }
        return true;
    }

    /**
     * <pre>
     * Print debug message.
     * </pre>
     * @param debugMsg debug message
     */
    private void printDebugLog(String debugMsg) {
        if (ZYPConstant.FLG_ON_Y.equals(this.modeLogFlg)) {
            println(debugMsg);
        }
    }

    /**
     * <pre>
     * Print Response Bean contents.
     * </pre>
     */
    private void printEchkRespLog() {
        println(toString(this.respBean));
    }

    private static void println(String message) {
        // Abnormal ends if message is null or empty.
        if (ZYPCommonFunc.hasValue(message)) {
            S21InfoLogOutput.println(message);
        }
    }

    /**
     * <pre>
     * toStrig Response Bean contents.
     * </pre>
     * @param object Object
     */
    private static String toString(Object object) {
        if (object == null) {
            return "null";
        }

        StringBuilder buf = new StringBuilder();
        try {
            Field[] fields = object.getClass().getDeclaredFields();

            buf.append(object.getClass().getSimpleName());
            buf.append("{");

            for (Field field : fields) {
                buf.append(field.getName());
                buf.append("=");
                buf.append(field.get(object));
                buf.append(", ");
            }

            buf.append("}");
        } catch (Throwable e) {
            buf.setLength(0);
        }
        return buf.toString();
    }


    private static void callCpgmTestModule() {
        String[] st = null;
        PaymentechCpgmTransaction.main(st);
    }
    
    // START 2023/11/16 Y.Ogura [QC#62010, ADD]
    private String prohibitCharCheck(String glblCmpyCd, String locNum) {
        DS_COND_CONSTTMsg tMsg = new DS_COND_CONSTTMsg();
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("dsCondConstGrpId01", NWZC2290_FILTER_TXT);
        tMsg.setSQLID("003");
        DS_COND_CONSTTMsgArray tMsgArray = (DS_COND_CONSTTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);

        if (tMsgArray != null &&  0 < tMsgArray.getValidCount()) {
            for (int i = 0; i < tMsgArray.getValidCount(); i++) {
                locNum = locNum.replace(tMsgArray.no(i).dsCondConstValTxt_01.getValue()
                        ,tMsgArray.no(i).dsCondConstValTxt_02.getValue());
            }
        }
        
        return locNum;
    }
    // END 2023/11/16 Y.Ogura [QC#62010, ADD]
}
