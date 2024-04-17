/**
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC203001;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDDebugOutput;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CR_CARD_AUTH_STSTMsg;
import business.db.CR_CARD_TPTMsg;
import business.db.CR_CARD_TRXTMsg;
import business.db.CR_CARD_TRX_DTLTMsg;
import business.db.CR_CARD_TRX_TPTMsg;
import business.db.CTRYTMsg;
import business.db.DS_CR_CARDTMsg;
import business.db.DS_CR_CARDTMsgArray;
import business.db.MSTR_DEF_INFOTMsg;
import business.db.MSTR_DEF_INFOTMsgArray;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.db.STTMsg;
import business.parts.NWZC203001PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_AUTH_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre> 
 *  Credit Card Validation API.
 *  This API calls S21 Credit Card Processing Service's 'New Order Request' function, 
 *  which calls CHASE Paymentech API connected with IBM Websphere MQ server.
 *  The result updates Retail Credit Card Table.    
 *  
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/16   Fujitsu         C.Yokoi         Create          N/A
 * 2016/03/15   SRAA            K.Aratani       UPdate          QC#5527
 * 2016/06/03   Fujitsu         H.Nagashima     UPdate          QC#9429
 * 2018/07/10   Fujitsu         A.Kosai         Update          S21_NA#25797
 * 2018/08/27   CITS            T.Gotoda        Update          QC#27471
 * 2020/05/28   Fujitsu         C.Hara          Update          QC#56866
 * 2020/09/28   CITS            K.Ogino         Update          QC#56866
 *</pre>
 */
public class NWZC203001 extends S21ApiCommonBase implements NWZC203001Constant {

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap;

    private String pmtcVrsnCd = null;
    private String pmtcBinNum = null;
    private String pmtcMrcntId = null;
    private String pmtcTrmId = null;
    private String pmtcIndyTp = null;

    /**
     * <pre>
     * Constructor
     * </pre>
     */
    public NWZC203001() {
        super();
    }

    /**
     * <pre>
     * Credit Card Validation API Main Flow
     * </pre>
     * @param inpPrmPMsg Input parameter
     * @param onBatchType Kind of Online or Batch.
     */
    public void execute(NWZC203001PMsg inpPrmPMsg, ONBATCH_TYPE onBatchType) {
        printDebugLog(PROGRAM_ID + "[ execute ] start");

        this.msgMap = new S21ApiMessageMap(inpPrmPMsg);

        doProcess(onBatchType);

        this.msgMap.flush();

        printDebugLog(PROGRAM_ID + "[ execute ] end");
    }

    /**
     * <pre>
     * Credit Card Validation API Main Flow
     * </pre>
     * @param inpPrmMsg Input parameter list
     * @param onBatchType Kind of Online or Batch.
     */
    public void execute(List<NWZC203001PMsg> inpPrmMsg, ONBATCH_TYPE onBatchType) {
        for (int i = 0; i < inpPrmMsg.size(); i++) {
            execute(inpPrmMsg.get(i), onBatchType);
        }
    }

    /**
     * <pre>
     * The create processing is executed. 
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * @param msgMap S21ApiMessageMap
     * @param onBatch ONBATCH_TYPE
     */
    private void doProcess(ONBATCH_TYPE onBatch) {
        NWZC203001PMsg pMsg = (NWZC203001PMsg) this.msgMap.getPmsg();

        // Check Mandantory Input Parameter
        chkInputParam(pMsg);
        if (hasError()) {
            return;
        }

        // Check Master Registration of Parameter
        chkMstrRegist(pMsg);
        if (hasError()) {
            return;
        }

        // set Parameter
        // QC#56866 Add
        S21InfoLogOutput.println(pMsg.toString());
        BigDecimal constNum = ZYPCodeDataUtil.getNumConstValue("PAYEEZY_LINE_ITEM_LIMIT_NUM", pMsg.glblCmpyCd.getValue());
        NWZC203001PmtcBean pmtcBean = new NWZC203001PmtcBean(pMsg, constNum);
        // QC#56866 End
        setChasePmntRqstParam(pmtcBean, pMsg);

//        set Mode For DEV TEST
//        pmtcBean.setPmtcAccesMode(CR_CARD_ACCESS_MODE_DEV);
// TODO: QC#25797 Log Mode : ON
        pmtcBean.setPmtcAccesMode(CR_CARD_ACCESS_MODE_LOG);

        String mode = pMsg.xxModeCd.getValue();
        // ------------------------------------------------------------------------
        // Write CC Master Mode
//        if (PROC_MODE_WRITE_CC_MSTR.equals(pmtcBean.getXxProcMd())) {
        if (PROC_MODE_WRITE_CC_MSTR.equals(mode)) {
            insertDsCrCard(pMsg, pmtcBean);
            return;
//        }

        // ------------------------------------------------------------------------
        // Write CC Transaction Mode
//        if (PROC_MODE_WRITE_CC_TRX.equals(pmtcBean.getXxProcMd())) {
        } else if (PROC_MODE_WRITE_CC_TRX.equals(mode)) {
            if (ZYPCommonFunc.hasValue(pmtcBean.getCrCardTrxPk())) {
                updateCrCardTrxForWriteCCTrx(pMsg, pmtcBean);
            } else {
                insertCrCardTrxModeForWriteCCTrx(pMsg, pmtcBean);
            }
            return;
//        }

        // ------------------------------------------------------------------------
        // Get Authorization Mode
//        if (PROC_MODE_GET_AUTH.equals(pmtcBean.getXxProcMd())) {
        } else if (PROC_MODE_GET_AUTH.equals(mode)) {

            // Call CHASE Paymentech
            vldCrCard(pmtcBean);

            if (hasError()) {
                pmtcBean.setPmtcAuthStsCd(CR_CARD_AUTH_STS.AUTHORIZED_FAILED);
            } else {
                pmtcBean.setPmtcAuthStsCd(CR_CARD_AUTH_STS.AUTHORIZED_COMPLETED);
            }

            insertCrCardTrx(pMsg, pmtcBean);
//        }

        // ------------------------------------------------------------------------
        // Settlement Mode
//        if (PROC_MODE_SETTLE.equals(pmtcBean.getXxProcMd())) {
        } else if (PROC_MODE_SETTLE.equals(mode)) {
            vldCrCard(pmtcBean);

            if (hasError()) {
                pmtcBean.setPmtcAuthStsCd(CR_CARD_AUTH_STS.SETTLEMENT_FAILED);
            } else {
                pmtcBean.setPmtcAuthStsCd(CR_CARD_AUTH_STS.SETTLEMENT_COMPLETED);
            }

            insertCrCardTrx(pMsg, pmtcBean);
//        }

        // ------------------------------------------------------------------------
        // Refund Mode
//        if (PROC_MODE_REFUND.equals(pmtcBean.getXxProcMd())) {
        } else if (PROC_MODE_REFUND.equals(mode)) {

            // Call CHASE Paymentech
            vldCrCard(pmtcBean);

            if (hasError()) {
                pmtcBean.setPmtcAuthStsCd(CR_CARD_AUTH_STS.REFUND_FAILED);
            } else {
                pmtcBean.setPmtcAuthStsCd(CR_CARD_AUTH_STS.REFUND_COMPLETED);
            }

            insertCrCardTrx(pMsg, pmtcBean);
//        }

        // ------------------------------------------------------------------------
        // Void Mode
//        if (PROC_MODE_VOID.equals(pmtcBean.getXxProcMd())) {
        } else if (PROC_MODE_VOID.equals(mode)) {
            CR_CARD_TRXTMsg upCrCardTrxTMsg = getUpdCrCardTrxKey(pMsg, pmtcBean);
            if (upCrCardTrxTMsg == null) {
                msgMap.addXxMsgId(NWDM0007E);
                return;
            }

            // Call CHASE Paymentech
            vldCrCard(pmtcBean);

            if (hasError()) {
                pmtcBean.setPmtcAuthStsCd(CR_CARD_AUTH_STS.VOID_FAILED);
            } else {
                pmtcBean.setPmtcAuthStsCd(CR_CARD_AUTH_STS.VOID_COMPLETED);
            }

            updateCrCardTrxForVoid(pMsg, pmtcBean, upCrCardTrxTMsg);
//        }

        // ------------------------------------------------------------------------
        // Profile Change Mode
//        if (PROC_MODE_PROF_CHNG.equals(pmtcBean.getXxProcMd())) {
        } else if (PROC_MODE_PROF_CHNG.equals(mode)) {
            // Call CHASE Paymentech
            vldCrCard(pmtcBean);

            if (hasError()) {
                return;
            }

            updateDsCrCard(pMsg, pmtcBean);
            ZYPEZDItemValueSetter.setValue(pMsg.authStsMsgCmntTxt, pmtcBean.getPmtcProcStsMsgTxt());
            ZYPEZDItemValueSetter.setValue(pMsg.xxPmtcProcStsCd, pmtcBean.getPmtcProcStsCd());
//        }

        // ------------------------------------------------------------------------
        // Capture Only Mode
//        if (PROC_MODE_CAPT_ONLY.equals(pmtcBean.getXxProcMd())) {
        } else if (PROC_MODE_CAPT_ONLY.equals(mode)) {

            // Call CHASE Paymentech
            vldCrCard(pmtcBean);

            if (hasError()) {
                pmtcBean.setPmtcAuthStsCd(CR_CARD_AUTH_STS.SETTLEMENT_FAILED);
            } else {
                pmtcBean.setPmtcAuthStsCd(CR_CARD_AUTH_STS.SETTLEMENT_COMPLETED);
            }

            insertCrCardTrx(pMsg, pmtcBean);
        }

        printDebugLog(PROGRAM_ID + "[ doCrCardVldApi ] end");
    }

    /**
     * <pre>
     * Check the input parameters.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * @param msgMap Message Map
     */
    private void chkInputParam(NWZC203001PMsg pMsg) {
        printDebugLog(PROGRAM_ID + "[ chkInp ] start");
        String xxProcMd = pMsg.xxModeCd.getValue();

        // Common Mandatory Check
        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            this.msgMap.addXxMsgId(NWZM0789E);
            return;
        }
        if (!ZYPCommonFunc.hasValue(xxProcMd)) {
            this.msgMap.addXxMsgId(NWZM0012E);
            return;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.slsDt)) {
            this.msgMap.addXxMsgId(NWZM0978E);
            return;
        }

        if (!PROC_MODE_WRITE_CC_MSTR.equals(xxProcMd) && !PROC_MODE_WRITE_CC_TRX.equals(xxProcMd)
                && !PROC_MODE_GET_AUTH.equals(xxProcMd) && !PROC_MODE_SETTLE.equals(xxProcMd)
                && !PROC_MODE_REFUND.equals(xxProcMd) && !PROC_MODE_VOID.equals(xxProcMd)
                && !PROC_MODE_PROF_CHNG.equals(xxProcMd) && !PROC_MODE_CAPT_ONLY.equals(xxProcMd)) {
            this.msgMap.addXxMsgId(NWZM0013E);
            return;
        }

        if (PROC_MODE_WRITE_CC_MSTR.equals(xxProcMd)) {
            if (!chkInputForCommonCrCardRef(pMsg)) {
                return;
            }
            chkInputForWriteCCMstrMode(pMsg);
            printDebugLog(PROGRAM_ID + "[ chkInp ] end");
            return;
//        }

//        if (PROC_MODE_WRITE_CC_TRX.equals(xxProcMd)) {
        } else if (PROC_MODE_WRITE_CC_TRX.equals(xxProcMd)) {
            if (!chkInputForCommonCrCardRef(pMsg)) {
                return;
            }
            chkInputForWriteCCTrxMode(pMsg);
            printDebugLog(PROGRAM_ID + "[ chkInp ] end");
            return;
//        }

//        if (PROC_MODE_GET_AUTH.equals(xxProcMd)) {
        } else if (PROC_MODE_GET_AUTH.equals(xxProcMd)) {
            if (!chkInputForCommonCrCardRef(pMsg)) {
                return;
            }
            chkInputForGetAuthMode(pMsg);
            printDebugLog(PROGRAM_ID + "[ chkInp ] end");
            return;
//        }

//        if (PROC_MODE_SETTLE.equals(xxProcMd)) {
        } else if (PROC_MODE_SETTLE.equals(xxProcMd)) {
            if (!chkInputForCommonCrCardRef(pMsg)) {
                return;
            }
            if (!chkInputForCommonPaymentReq(pMsg)) {
                return;
            }
            chkInputForSettleMode(pMsg);
            printDebugLog(PROGRAM_ID + "[ chkInp ] end");
            return;
//        }

//        if (PROC_MODE_REFUND.equals(xxProcMd)) {
        } else if (PROC_MODE_REFUND.equals(xxProcMd)) {
            if (!chkInputForCommonCrCardRef(pMsg)) {
                return;
            }
            if (!chkInputForCommonPaymentReq(pMsg)) {
                return;
            }
            chkInputForRefundMode(pMsg);
            printDebugLog(PROGRAM_ID + "[ chkInp ] end");
            return;
//        }

//        if (PROC_MODE_VOID.equals(xxProcMd)) {
        } else if (PROC_MODE_VOID.equals(xxProcMd)) {
            chkInputForVoidMode(pMsg);
            printDebugLog(PROGRAM_ID + "[ chkInp ] end");
            return;
//        }

//        if (PROC_MODE_PROF_CHNG.equals(xxProcMd)) {
        } else if (PROC_MODE_PROF_CHNG.equals(xxProcMd)) {
            if (!chkInputForCommonCrCardRef(pMsg)) {
                return;
            }
            chkInputForProfChngMode(pMsg);
            printDebugLog(PROGRAM_ID + "[ chkInp ] end");
            return;
//        }

//        if (PROC_MODE_CAPT_ONLY.equals(xxProcMd)) {
        } else if (PROC_MODE_CAPT_ONLY.equals(xxProcMd)) {
            if (!chkInputForCommonPaymentReq(pMsg)) {
                return;
            }
            chkInputForCaptOnlyMode(pMsg);
            printDebugLog(PROGRAM_ID + "[ chkInp ] end");
        }
    }

    /**
     * chkInputForWriteCCMstrMode
     * 
     * <pre>
     * Check the input parameters for Mode Write CC Master.
     * 
     * <pre>
     * @param msgMap Message Map
     * @param pMsg NWZC203001PMsg
     */
    private void chkInputForWriteCCMstrMode(NWZC203001PMsg pMsg) {
        if (!ZYPCommonFunc.hasValue(pMsg.xxPmtcAcctNum)) {
            this.msgMap.addXxMsgId(NWZM1052E);
            return;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.xxPmtcExprDtTxt)) {
            this.msgMap.addXxMsgId(NWZM1053E);
            return;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.xxPmtcAvsNm)) {
            this.msgMap.addXxMsgId(NWZM1060E);
            return;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.crCardTpCd)) {
            this.msgMap.addXxMsgId(NWZM1289E);
        }
    }

    /**
     * chkInputForWriteCCTrxMode
     * 
     * <pre>
     * Check the input parameters for Mode Write CC Transaction.
     * 
     * <pre>
     * @param msgMap Message Map
     * @param pMsg NWZC203001PMsg
     */
    private void chkInputForWriteCCTrxMode(NWZC203001PMsg pMsg) {
        if (!ZYPCommonFunc.hasValue(pMsg.crCardTrxTpCd)) {
            this.msgMap.addXxMsgId(NWZM1723E);
        }
    }

    /**
     * chkInputForGetAuthMode
     * 
     * <pre>
     * Check the input parameters for Mode Get Authorization.
     * 
     * <pre>
     * @param msgMap Message Map
     * @param pMsg NWZC203001PMsg
     */
    private void chkInputForGetAuthMode(NWZC203001PMsg pMsg) {
        if (!ZYPCommonFunc.hasValue(pMsg.crCardAuthAmt)) {
            this.msgMap.addXxMsgId(NWZM1724E);
            return;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.xxPmtcPrflOrdOvrdCd)) {
            this.msgMap.addXxMsgId(NWZM1064E);
            return;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.xxPmtcOrdId)) {
            this.msgMap.addXxMsgId(NWZM1065E);
        }
    }

    /**
     * chkInputForSettleMode
     * 
     * <pre>
     * Check the input parameters for Mode Settlement.
     * 
     * <pre>
     * @param msgMap Message Map
     * @param pMsg NWZC203001PMsg
     */
    private void chkInputForSettleMode(NWZC203001PMsg pMsg) {
        if (!ZYPCommonFunc.hasValue(pMsg.xxPmtcPrflOrdOvrdCd)) {
            this.msgMap.addXxMsgId(NWZM1064E);
        }
    }

    /**
     * chkInputForRefundMode
     * 
     * <pre>
     * Check the input parameters for Mode Settlement.
     * 
     * <pre>
     * @param msgMap Message Map
     * @param pMsg NWZC203001PMsg
     */
    private void chkInputForRefundMode(NWZC203001PMsg pMsg) {
        if (!ZYPCommonFunc.hasValue(pMsg.crCardAuthRefNum)) {
            this.msgMap.addXxMsgId(NWZM1725E);
            return;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.xxPmtcPrflOrdOvrdCd)) {
            this.msgMap.addXxMsgId(NWZM1064E);
        }
    }

    /**
     * chkInputForVoidMode
     * 
     * <pre>
     * Check the input parameters for Mode Void.
     * 
     * <pre>
     * @param msgMap Message Map
     * @param pMsg NWZC203001PMsg
     */
    private void chkInputForVoidMode(NWZC203001PMsg pMsg) {
        if (!ZYPCommonFunc.hasValue(pMsg.crCardAuthRefNum)) {
            this.msgMap.addXxMsgId(NWZM1725E);
            return;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.xxPmtcOrdId)) {
            this.msgMap.addXxMsgId(NWZM1065E);
            return;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.xxPmtcTrxRefIdxCd)) {
            this.msgMap.addXxMsgId(NWZM1727E);
            return;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.crCardTrxPk)) {
            this.msgMap.addXxMsgId(NWZM1726E);
        }
    }

    /**
     * chkInputForProfChngMode
     * 
     * <pre>
     * Check the input parameters for Mode Profile Change.
     * 
     * <pre>
     * @param msgMap Message Map
     * @param pMsg NWZC203001PMsg
     */
    private void chkInputForProfChngMode(NWZC203001PMsg pMsg) {
        if (!ZYPCommonFunc.hasValue(pMsg.xxPmtcExprDtTxt)) {
            this.msgMap.addXxMsgId(NWZM1053E);
        }
    }

    /**
     * chkInputForCaptOnlyMode
     * 
     * <pre>
     * Check the input parameters for Mode Capture Only.
     * 
     * <pre>
     * @param msgMap Message Map
     * @param pMsg NWZC203001PMsg
     */
    private void chkInputForCaptOnlyMode(NWZC203001PMsg pMsg) {
        if (!ZYPCommonFunc.hasValue(pMsg.crCardAuthRefNum)) {
            this.msgMap.addXxMsgId(NWZM1725E);
            return;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.sellToCustCd)) {
            this.msgMap.addXxMsgId(NWZM1050E);
        }
    }

    /**
     * chkInputForCommonCrCardRef
     * 
     * <pre>
     * Check the input parameters for Mode
     * which used for any payment request to Chase Paymentech.
     * 
     * <pre>
     * @param msgMap Message Map
     * @param pMsg NWZC203001PMsg
     */
    private boolean chkInputForCommonCrCardRef(NWZC203001PMsg pMsg) {
        if (!ZYPCommonFunc.hasValue(pMsg.crCardCustRefNum)) {
            this.msgMap.addXxMsgId(NWZM1063E);
            return false;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.sellToCustCd)) {
            this.msgMap.addXxMsgId(NWZM1050E);
            return false;
        }
        return true;
    }

    /**
     * chkInputForCommonRgstedCrCard
     * 
     * <pre>
     * Check the input parameters for Mode
     * which used for any payment request to Chase Paymentech.
     * 
     * <pre>
     * @param msgMap Message Map
     * @param pMsg NWZC203001PMsg
     */
    private boolean chkInputForCommonPaymentReq(NWZC203001PMsg pMsg) {
        if (!ZYPCommonFunc.hasValue(pMsg.crCardAuthAmt)) {
            this.msgMap.addXxMsgId(NWZM1724E);
            return false;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.crCardTrxTpCd)) {
            this.msgMap.addXxMsgId(NWZM1723E);
            return false;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.xxPmtcTaxIndNum)) {
            this.msgMap.addXxMsgId(NWZM1082E);
            return false;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.xxPmtcOrdId)) {
            this.msgMap.addXxMsgId(NWZM1065E);
            return false;
        }
        return true;
    }

    /**
     * chkInputForWriteCCTrxMode
     * 
     * <pre>
     * Check the input parameters for Mode Write CC Transaction.
     * 
     * <pre>
     * @param msgMap Message Map
     * @param pMsg NWZC203001PMsg
     */
//    private void setChasePmntRqstParam(NWZC203001PmtcBean pmtcBean, NWZC203001PMsg pMsg) {
//        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
//
//        pmtcBean.setGlblCmpyCd(glblCmpyCd);
//        pmtcBean.setXxProcMd(pMsg.xxModeCd.getValue());
//        pmtcBean.setPmtcVrsnCd(getPmntCachParam(glblCmpyCd, MSTR_FUNC_ID, MSTR_COL_ID_VRSN));
//        pmtcBean.setPmtcBinNum(getPmntCachParam(glblCmpyCd, MSTR_FUNC_ID, MSTR_COL_ID_BIN));
//        pmtcBean.setPmtcMrcntId(getPmntCachParam(glblCmpyCd, MSTR_FUNC_ID, MSTR_COL_ID_MRCHT));
//
//        if (PROC_MODE_PROF_CHNG.equals(pmtcBean.getXxProcMd())) {
//            pmtcBean.setPmtcExprDtTxt(pMsg.xxPmtcExprDtTxt.getValue());
//            return;
//        }
//
//        pmtcBean.setPmtcTrmId(getPmntCachParam(glblCmpyCd, MSTR_FUNC_ID, MSTR_COL_ID_TRMNL));
//        if (PROC_MODE_VOID.equals(pmtcBean.getXxProcMd())) {
//            pmtcBean.setPmtcTrxRefIdxCd(pMsg.xxPmtcTrxRefIdxCd.getValue());
//            return;
//        }
//
//        pmtcBean.setPmtcTaxIndNum(pMsg.xxPmtcTaxIndNum.getValue());
//        pmtcBean.setPmtcAuthTaxAmt(pMsg.crCardAuthTaxAmt.getValue());
//        pmtcBean.setPmtcTrxRefIdxCd(pMsg.xxPmtcTrxRefIdxCd.getValue());
//        if (PROC_MODE_CAPT_ONLY.equals(pmtcBean.getXxProcMd())) {
//            return;
//        }
//
//        pmtcBean.setPmtcIndyTp(getPmntCachParam(glblCmpyCd, MSTR_FUNC_ID, MSTR_COL_ID_INDY));
//        pmtcBean.setPmtcPrflOrdOvrdCd(pMsg.xxPmtcPrflOrdOvrdCd.getValue());
//
//        if (PROC_MODE_GET_AUTH.equals(pmtcBean.getXxProcMd())) {
//            pmtcBean.setPmtcTrxTpCd(PMTC_TRNSF_TP_A);
//        }
//        if (PROC_MODE_SETTLE.equals(pmtcBean.getXxProcMd())) {
//            pmtcBean.setPmtcTrxTpCd(PMTC_TRNSF_TP_AC);
//        }
//        if (PROC_MODE_REFUND.equals(pmtcBean.getXxProcMd())) {
//            pmtcBean.setPmtcTrxTpCd(PMTC_TRNSF_TP_R);
//        }
//    }
    private void setChasePmntRqstParam(NWZC203001PmtcBean pmtcBean, NWZC203001PMsg pMsg) {

        String mode = pMsg.xxModeCd.getValue();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        pmtcVrsnCd  = getPmntCachParam(glblCmpyCd, MSTR_FUNC_ID, MSTR_COL_ID_VRSN, pmtcVrsnCd);
        pmtcIndyTp  = getPmntCachParam(glblCmpyCd, MSTR_FUNC_ID, MSTR_COL_ID_INDY, pmtcIndyTp);
        pmtcBinNum  = getPmntCachParam(glblCmpyCd, MSTR_FUNC_ID, MSTR_COL_ID_BIN, pmtcBinNum);
        pmtcMrcntId = getPmntCachParam(glblCmpyCd, MSTR_FUNC_ID, MSTR_COL_ID_MRCHT, pmtcMrcntId);
        pmtcTrmId   = getPmntCachParam(glblCmpyCd, MSTR_FUNC_ID, MSTR_COL_ID_TRMNL, pmtcTrmId);

        pmtcBean.setGlblCmpyCd(glblCmpyCd);
        pmtcBean.setXxProcMd(pMsg.xxModeCd.getValue());
        pmtcBean.setPmtcVrsnCd(pmtcVrsnCd);
        pmtcBean.setPmtcBinNum(pmtcBinNum);
        pmtcBean.setPmtcMrcntId(pmtcMrcntId);

        if (PROC_MODE_GET_AUTH.equals(mode)) {
            pmtcBean.setPmtcIndyTp(pmtcIndyTp);
            pmtcBean.setPmtcTrxTpCd(PMTC_TRNSF_TP_A);
            pmtcBean.setPmtcTrmId(pmtcTrmId);
            pmtcBean.setPmtcCustRefNum(pMsg.crCardCustRefNum.getValue());
            pmtcBean.setPmtcPrflOrdOvrdCd(pMsg.xxPmtcPrflOrdOvrdCd.getValue());
            pmtcBean.setPmtcOrdId(pMsg.xxPmtcOrdId.getValue());
            pmtcBean.setPmtcAuthAmt(null2zero(pMsg.crCardAuthAmt.getValue()));

        } else if (PROC_MODE_SETTLE.equals(mode)) {
            pmtcBean.setPmtcIndyTp(pmtcIndyTp);
            pmtcBean.setPmtcTrxTpCd(PMTC_TRNSF_TP_AC);
            pmtcBean.setPmtcTrmId(pmtcTrmId);
            pmtcBean.setPmtcCustRefNum(pMsg.crCardCustRefNum.getValue());
            pmtcBean.setPmtcPrflOrdOvrdCd(pMsg.xxPmtcPrflOrdOvrdCd.getValue());
            pmtcBean.setPmtcOrdId(pMsg.xxPmtcOrdId.getValue());
            pmtcBean.setPmtcAuthAmt(null2zero(pMsg.crCardAuthAmt.getValue()));
            pmtcBean.setPmtcTaxIndNum(pMsg.xxPmtcTaxIndNum.getValue());
            pmtcBean.setPmtcAuthTaxAmt(null2zero(pMsg.crCardAuthTaxAmt.getValue()));

        } else if (PROC_MODE_REFUND.equals(pmtcBean.getXxProcMd())) {
            pmtcBean.setPmtcIndyTp(pmtcIndyTp);
            pmtcBean.setPmtcTrxTpCd(PMTC_TRNSF_TP_R);
            pmtcBean.setPmtcTrmId(pmtcTrmId);
            pmtcBean.setPmtcCustRefNum(pMsg.crCardCustRefNum.getValue());
            pmtcBean.setPmtcPrflOrdOvrdCd(pMsg.xxPmtcPrflOrdOvrdCd.getValue());
            pmtcBean.setPmtcOrdId(pMsg.xxPmtcOrdId.getValue());
            pmtcBean.setPmtcAuthAmt(null2zero(pMsg.crCardAuthAmt.getValue()));
            pmtcBean.setPmtcTaxIndNum(pMsg.xxPmtcTaxIndNum.getValue());
            pmtcBean.setPmtcAuthTaxAmt(null2zero(pMsg.crCardAuthTaxAmt.getValue()));
            pmtcBean.setPmtcAuthRefNum(pMsg.crCardAuthRefNum.getValue());

        } else if (PROC_MODE_VOID.equals(mode)) {
            pmtcBean.setPmtcTrmId(pmtcTrmId);
            pmtcBean.setPmtcOrdId(pMsg.xxPmtcOrdId.getValue());
            pmtcBean.setPmtcAuthRefNum(pMsg.crCardAuthRefNum.getValue());
            pmtcBean.setPmtcTrxRefIdxCd(pMsg.xxPmtcTrxRefIdxCd.getValue());
            // 2018/07/10 S21_NA#25797 Add Start
            pmtcBean.setPmtcAuthAmt(null2zero(pMsg.crCardAuthAmt.getValue()));
            // 2018/07/10 S21_NA#25797 Add End

        } else if (PROC_MODE_PROF_CHNG.equals(mode)) {
            pmtcBean.setPmtcExprDtTxt(pMsg.xxPmtcExprDtTxt.getValue());
            pmtcBean.setPmtcCustRefNum(pMsg.crCardCustRefNum.getValue());

        } else if (PROC_MODE_CAPT_ONLY.equals(mode)) {
            pmtcBean.setPmtcTrmId(pmtcTrmId);
            pmtcBean.setPmtcOrdId(pMsg.xxPmtcOrdId.getValue());
            pmtcBean.setPmtcAuthAmt(pMsg.crCardAuthAmt.getValue());
            pmtcBean.setPmtcTaxIndNum(pMsg.xxPmtcTaxIndNum.getValue());
            pmtcBean.setPmtcAuthTaxAmt(null2zero(pMsg.crCardAuthTaxAmt.getValue()));
            pmtcBean.setPmtcAuthRefNum(pMsg.crCardAuthRefNum.getValue());

        }

    }

    /**
     * <pre>
     * Check Master Registerations for input Parameter.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * @param msgMap Message Map
     */
    private void chkMstrRegist(NWZC203001PMsg pMsg) {

        printDebugLog(PROGRAM_ID + "[ chkInp ] start");

        // Sell To Customer Code
        if (ZYPCommonFunc.hasValue(pMsg.sellToCustCd)) {
            if (!existsSellToCustCd(pMsg.glblCmpyCd.getValue(), pMsg.sellToCustCd.getValue())) {
                this.msgMap.addXxMsgId(NWZM1133E);
                return;
            }
        }

        // Credit Card Transaction Type Code
        if (ZYPCommonFunc.hasValue(pMsg.crCardTrxTpCd)) {
            if (!existsCrCardTrxTpCd(pMsg.glblCmpyCd.getValue(), pMsg.crCardTrxTpCd.getValue())) {
                this.msgMap.addXxMsgId(NWZM1729E);
                return;
            }
        }

//        // Post Code
//        if (ZYPCommonFunc.hasValue(pMsg.xxPmtcAvsZipTxt)) {
//            if (!existsPostCd(pMsg.glblCmpyCd.getValue(), pMsg.xxPmtcAvsZipTxt.getValue())) {
//                this.msgMap.addXxMsgId(NWZM1728E);
//                return;
//            }
//        }

        // State Code
        if (ZYPCommonFunc.hasValue(pMsg.xxPmtcAvsStCd)) {
            if (!existsStCd(pMsg.glblCmpyCd.getValue(), pMsg.xxPmtcAvsStCd.getValue())) {
                this.msgMap.addXxMsgId(NWZM1730E);
                return;
            }
        }

        // Country Code
        if (ZYPCommonFunc.hasValue(pMsg.xxPmtcAvsCtryCdTxt)) {
            if (!existsCtryCd(pMsg.glblCmpyCd.getValue(), pMsg.xxPmtcAvsCtryCdTxt.getValue())) {
                this.msgMap.addXxMsgId(NWZM1731E);
                return;
            }
        }

        // Credit Card Type Code
        if (ZYPCommonFunc.hasValue(pMsg.crCardTpCd)) {
            if (!existsCrCardTpCd(pMsg.glblCmpyCd.getValue(), pMsg.crCardTpCd.getValue())) {
                this.msgMap.addXxMsgId(NWZM1733E);
                return;
            }
        }

        // Credit Card Auth Status Code
        if (ZYPCommonFunc.hasValue(pMsg.crCardAuthStsCd)) {
            if (!existscrCardAuthStsCd(pMsg.glblCmpyCd.getValue(), pMsg.crCardAuthStsCd.getValue())) {
                this.msgMap.addXxMsgId(NWZM1732E);
            }
        }

        printDebugLog(PROGRAM_ID + "[ chkInp ] end");
    }

    /**
     * <pre>
     * insert DS_CR_CARD.
     * </pre>
     * @param msgMap Message Map
     */
    private void insertDsCrCard(NWZC203001PMsg pMsg, NWZC203001PmtcBean pmtcBean) {
        DS_CR_CARDTMsg inTMsg = setDsCrCardTMsg(pmtcBean);

        S21ApiTBLAccessor.insert(inTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            this.msgMap.addXxMsgId(NWDM0007E);
            return;
        }

        //QC#9429 add Start
        ZYPEZDItemValueSetter.setValue(pMsg.dsCrCardPk, inTMsg.dsCrCardPk);
        //QC#9429 add End
    }

    /**
     * <pre>
     * update DS_CR_CARD.
     * </pre>
     * @param msgMap Message Map
     */
    private void updateDsCrCard(NWZC203001PMsg pMsg, NWZC203001PmtcBean pmtcBean) {
        DS_CR_CARDTMsg updDsCrCardKey = new DS_CR_CARDTMsg();

        updDsCrCardKey.setSQLID("002");
        updDsCrCardKey.setConditionValue("glblCmpyCd01", pmtcBean.getGlblCmpyCd());
        updDsCrCardKey.setConditionValue("crCardCustRefNum01", pmtcBean.getPmtcCustRefNum());
        updDsCrCardKey.setConditionValue("sellToCustCd01", pmtcBean.getSellToCust());

        DS_CR_CARDTMsgArray updDsCrCardArry =
            (DS_CR_CARDTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(updDsCrCardKey);
        if (updDsCrCardArry.length() == 0) {
            this.msgMap.addXxMsgId(NWDM0007E);
            return;
        }

        for (int i = 0; i < updDsCrCardArry.length(); i++) {
            DS_CR_CARDTMsg dsCrCardTMsg = updDsCrCardArry.no(i);

            ZYPEZDItemValueSetter.setValue(dsCrCardTMsg.crCardValidFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(dsCrCardTMsg.crCardExprYrMth, pmtcBean.getPmtcExprDtTxt());
            ZYPEZDItemValueSetter.setValue(dsCrCardTMsg.crCardLastAuthDt, pmtcBean.getSlsDt());

            S21ApiTBLAccessor.update(dsCrCardTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsCrCardTMsg.getReturnCode())) {
                this.msgMap.addXxMsgId(NWDM0007E);
            }
        }
    }

    /**
     * <pre>
     * set DS_CR_CARDTMsg.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * @param msgMap Message Map
     * @param pmtcBean NWZC203001 Paymentech Bean
     */
    private DS_CR_CARDTMsg setDsCrCardTMsg(NWZC203001PmtcBean pmtcBean) {
        DS_CR_CARDTMsg tMsg = new DS_CR_CARDTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pmtcBean.getGlblCmpyCd());
        pmtcBean.setDsCrCardPk(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_CR_CARD_SQ));
        ZYPEZDItemValueSetter.setValue(tMsg.dsCrCardPk, pmtcBean.getDsCrCardPk());
        ZYPEZDItemValueSetter.setValue(tMsg.sellToCustCd, pmtcBean.getSellToCust());
        ZYPEZDItemValueSetter.setValue(tMsg.crCardCustRefNum, pmtcBean.getPmtcCustRefNum());
        ZYPEZDItemValueSetter.setValue(tMsg.crCardTpCd, pmtcBean.getCrCardTpCd());

        String lastDigitNum = pmtcBean.getPmtcAcctNum().substring(pmtcBean.getPmtcAcctNum().length() - 4);
        ZYPEZDItemValueSetter.setValue(tMsg.crCardLastDigitNum, lastDigitNum);

        ZYPEZDItemValueSetter.setValue(tMsg.crCardValidFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(tMsg.crCardExprYrMth, pmtcBean.getPmtcExprDtTxt());
        if (ZYPCommonFunc.hasValue(pmtcBean.getCrCardCratDt())) {
            ZYPEZDItemValueSetter.setValue(tMsg.crCardCratDt, pmtcBean.getCrCardCratDt());
            ZYPEZDItemValueSetter.setValue(tMsg.crCardLastAuthDt, pmtcBean.getCrCardCancDt());
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.crCardCratDt, pmtcBean.getSlsDt());
            ZYPEZDItemValueSetter.setValue(tMsg.crCardLastAuthDt, pmtcBean.getSlsDt());
        }
        ZYPEZDItemValueSetter.setValue(tMsg.crCardHldNm, pmtcBean.getPmtcAvsNm());
        ZYPEZDItemValueSetter.setValue(tMsg.firstLineAddr, pmtcBean.getPmtcAvsAddr01());
        ZYPEZDItemValueSetter.setValue(tMsg.scdLineAddr, pmtcBean.getPmtcAvsAddr02());
        ZYPEZDItemValueSetter.setValue(tMsg.ctyAddr, pmtcBean.getPmtcAvsCtyTxt());
        ZYPEZDItemValueSetter.setValue(tMsg.stCd, pmtcBean.getPmtcAvsStCd());
        ZYPEZDItemValueSetter.setValue(tMsg.postCd, pmtcBean.getPmtcAvsZipTxt());
        ZYPEZDItemValueSetter.setValue(tMsg.ctryCd, pmtcBean.getPmtcAvsCtryCdTxt());

        return tMsg;
    }

    /**
     * <pre>
     * Set return parameter.
     * If an error occurs, add a message to  the Message Map.
     * </pre>
     * @param msgMap Message Map
     */
//    private void setRtrnParam(NWZC203001PMsg pMsg, NWZC203001PmtcBean pmtcBean) {
//        ZYPEZDItemValueSetter.setValue(pMsg.crCardTrxPk, pmtcBean.getCrCardTrxPk());
//        ZYPEZDItemValueSetter.setValue(pMsg.xxPmtcProcStsCd, pmtcBean.getPmtcProcStsCd());
//
//        ZYPEZDItemValueSetter.setValue(pMsg.authStsMsgCmntTxt, pmtcBean.getPmtcProcStsMsgTxt());
//        ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthStsCd, pmtcBean.getPmtcAuthStsCd());
//        if (PROC_MODE_VOID.equals(pmtcBean.getXxProcMd())) {
//            return;
//        }
//
//        ZYPEZDItemValueSetter.setValue(pMsg.xxPmtcApvlStsNum, pmtcBean.getPmtcApvlStsNum());
//    }
    private void setRtrnParam(NWZC203001PMsg pMsg, NWZC203001PmtcBean pmtcBean) {

        String mode = pMsg.xxModeCd.getValue();
        if (PROC_MODE_WRITE_CC_TRX.equals(mode)) {
            ZYPEZDItemValueSetter.setValue(pMsg.crCardTrxPk,        pmtcBean.getCrCardTrxPk());

        } else if (PROC_MODE_GET_AUTH.equals(mode)
                || PROC_MODE_SETTLE.equals(mode)
                || PROC_MODE_REFUND.equals(mode)
                || PROC_MODE_CAPT_ONLY.equals(mode)
                ) {
            ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthStsCd,    pmtcBean.getPmtcAuthStsCd());
            ZYPEZDItemValueSetter.setValue(pMsg.authStsMsgCmntTxt,  pmtcBean.getPmtcProcStsMsgTxt());
            ZYPEZDItemValueSetter.setValue(pMsg.crCardTrxPk,        pmtcBean.getCrCardTrxPk());
            ZYPEZDItemValueSetter.setValue(pMsg.xxPmtcProcStsCd,    pmtcBean.getPmtcProcStsCd());
            ZYPEZDItemValueSetter.setValue(pMsg.xxPmtcApvlStsNum,   pmtcBean.getPmtcApvlStsNum());

        } else if (PROC_MODE_VOID.equals(mode)) {
            ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthStsCd,    pmtcBean.getPmtcAuthStsCd());
            ZYPEZDItemValueSetter.setValue(pMsg.authStsMsgCmntTxt,  pmtcBean.getPmtcProcStsMsgTxt());
            ZYPEZDItemValueSetter.setValue(pMsg.xxPmtcProcStsCd,    pmtcBean.getPmtcProcStsCd());

        } else if (PROC_MODE_PROF_CHNG.equals(mode)) {
            ZYPEZDItemValueSetter.setValue(pMsg.authStsMsgCmntTxt,  pmtcBean.getPmtcProcStsMsgTxt());
            ZYPEZDItemValueSetter.setValue(pMsg.xxPmtcProcStsCd,    pmtcBean.getPmtcProcStsCd());

        }
    }

    /**
     * <pre>
     * update CR_CARD_TRXTMsg.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * @param msgMap Message Map
     */
    private void updateCrCardTrxForWriteCCTrx(NWZC203001PMsg pMsg, NWZC203001PmtcBean pmtcBean) {
        CR_CARD_TRXTMsg upCrCardTrxTMsg = getUpdCrCardTrxKey(pMsg, pmtcBean);
        if (upCrCardTrxTMsg == null) {
            this.msgMap.addXxMsgId(NWDM0007E);
            return;
        }
        upCrCardTrxTMsg = setCrCardTrxTMsgForWriteCCTrx(pmtcBean, upCrCardTrxTMsg, pMsg, false);

        S21ApiTBLAccessor.update(upCrCardTrxTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(upCrCardTrxTMsg.getReturnCode())) {
            this.msgMap.addXxMsgId(NWDM0007E);
            return;
        }
    }

    /**
     * <pre>
     * update CR_CARD_TRXTMsg.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * @param msgMap Message Map
     */
    private void updateCrCardTrxForVoid(NWZC203001PMsg pMsg, NWZC203001PmtcBean pmtcBean, CR_CARD_TRXTMsg upCrCardTrxTMsg) {
        upCrCardTrxTMsg = setCrCardTrxForVoid(pmtcBean, upCrCardTrxTMsg);

        S21ApiTBLAccessor.update(upCrCardTrxTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(upCrCardTrxTMsg.getReturnCode())) {
            this.msgMap.addXxMsgId(NWDM0007E);
            return;
        }

        setRtrnParam(pMsg, pmtcBean);
    }

    /**
     * <pre>
     * update CR_CARD_TRXTMsg.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * @param msgMap Message Map
     */
    private CR_CARD_TRXTMsg getUpdCrCardTrxKey(NWZC203001PMsg pMsg, NWZC203001PmtcBean pmtcBean) {
        CR_CARD_TRXTMsg upCrCardTrxTMsg = new CR_CARD_TRXTMsg();

        ZYPEZDItemValueSetter.setValue(upCrCardTrxTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(upCrCardTrxTMsg.crCardTrxPk, pMsg.crCardTrxPk);

        return (CR_CARD_TRXTMsg) S21ApiTBLAccessor.findByKeyForUpdate(upCrCardTrxTMsg);
    }

    /**
     * <pre>
     * set DS_CR_CARDTMsg.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * @param msgMap Message Map\
     * @return CR_CARD_TRXTMsg
     */
    private CR_CARD_TRXTMsg setInsCrCardTrxTMsgModeForWriteCCTrx(NWZC203001PMsg pMsg, NWZC203001PmtcBean pmtcBean) {
        CR_CARD_TRXTMsg tMsg = new CR_CARD_TRXTMsg();
        pmtcBean.setCrCardTrxPk(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.CR_CARD_TRX_SQ));
        ZYPEZDItemValueSetter.setValue(tMsg.crCardTrxPk, pmtcBean.getCrCardTrxPk());

        tMsg = setCrCardTrxTMsgForWriteCCTrx(pmtcBean, tMsg, pMsg, true);
        return tMsg;
    }

    /**
     * <pre>
     * set DS_CR_CARDTMsg.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * @param msgMap Message Map\
     * @return CR_CARD_TRXTMsg
     */
    private CR_CARD_TRXTMsg setInsCrCardTrxTMsg(NWZC203001PMsg pMsg, NWZC203001PmtcBean pmtcBean) {
        CR_CARD_TRXTMsg tMsg = new CR_CARD_TRXTMsg();
        pmtcBean.setCrCardTrxPk(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.CR_CARD_TRX_SQ));
        ZYPEZDItemValueSetter.setValue(tMsg.crCardTrxPk, pmtcBean.getCrCardTrxPk());

        tMsg = setCrCardTrxTMsg(pMsg, pmtcBean, tMsg);
        return tMsg;
    }

    // 2020/05/28 QC#56866 Add Start
    /**
     * <pre>
     * set CARD_TRX_DTLTMsg.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * @param msgMap Message Map\
     * @return CARD_TRX_DTLTMsg
     */
    private CR_CARD_TRX_DTLTMsg setInsCrCardTrxDtlTMsg(NWZC203001PMsg pMsg, NWZC203001PmtcBean pmtcBean, NWZC203001PmtcDtlBean pmtcDtlBean) {
        CR_CARD_TRX_DTLTMsg tMsg = new CR_CARD_TRX_DTLTMsg();
        pmtcDtlBean.setCrCardTrxDtlPk(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.CR_CARD_TRX_DTL_SQ));
        ZYPEZDItemValueSetter.setValue(tMsg.crCardTrxDtlPk, pmtcDtlBean.getCrCardTrxDtlPk());
        ZYPEZDItemValueSetter.setValue(tMsg.crCardTrxPk, pmtcBean.getCrCardTrxPk());

        tMsg = setCrCardTrxDtlTMsg(pMsg, pmtcDtlBean, tMsg);
        return tMsg;
    }
    // 2020/05/28 QC#56866 Add End

    /**
     * <pre>
     * set DS_CR_CARDTMsg for Void mode.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * @param pMsg NWZC203001PMsg
     * @param tMsg CR_CARD_TRXTMsg
     * @return CR_CARD_TRXTMsg
     */
    private CR_CARD_TRXTMsg setCrCardTrxForVoid(NWZC203001PmtcBean pmtcBean, CR_CARD_TRXTMsg tMsg) {
        //ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthStsCd, pmtcBean.getPmtcAuthDt());
        ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthStsCd, pmtcBean.getPmtcAuthStsCd());
        ZYPEZDItemValueSetter.setValue(tMsg.authStsMsgCmntTxt, pmtcBean.getPmtcProcStsMsgTxt());
        ZYPEZDItemValueSetter.setValue(tMsg.crCardTrxProcStsCd, pmtcBean.getPmtcProcStsCd());

        if (CR_CARD_AUTH_STS.VOID_COMPLETED.equals(pmtcBean.getPmtcAuthStsCd())) {
            ZYPEZDItemValueSetter.setValue(tMsg.crCardCancDt, pmtcBean.getSlsDt());
        }

        return tMsg;
    }

    /**
     * <pre>
     * insert CR_CARD_TRX.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * @param msgMap Message Map
     */
    private void insertCrCardTrxModeForWriteCCTrx(NWZC203001PMsg pMsg, NWZC203001PmtcBean pmtcBean) {
        CR_CARD_TRXTMsg inTMsg = setInsCrCardTrxTMsgModeForWriteCCTrx(pMsg, pmtcBean);

        S21ApiTBLAccessor.insert(inTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            this.msgMap.addXxMsgId(NWDM0007E);
            return;
        }
        setRtrnParam(pMsg, pmtcBean);
    }

    /**
     * <pre>
     * insert CR_CARD_TRX.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * @param msgMap Message Map
     */
    private void insertCrCardTrx(NWZC203001PMsg pMsg, NWZC203001PmtcBean pmtcBean) {
        CR_CARD_TRXTMsg inTMsg = setInsCrCardTrxTMsg(pMsg, pmtcBean);

        S21ApiTBLAccessor.insert(inTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            this.msgMap.addXxMsgId(NWDM0007E);
            return;
        }

        // 2020/05/28 QC#56866 Add Start
        for (int i = 0; i < pmtcBean.getPmtcDtlList().size(); i++) {
            NWZC203001PmtcDtlBean pmtcDtlBean = pmtcBean.getPmtcDtlList().get(i);
            CR_CARD_TRX_DTLTMsg inDtlTMsg = setInsCrCardTrxDtlTMsg(pMsg, pmtcBean, pmtcDtlBean);
            
            S21ApiTBLAccessor.insert(inDtlTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                this.msgMap.addXxMsgId(NWDM0007E);
                return;
            }
        }
        // 2020/05/28 QC#56866 Add End

        setRtrnParam(pMsg, pmtcBean);
    }

    /**
     * <pre>
     * set DS_CR_CARDTMsg.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * @param pMsg NWZC203001PMsg
     * @param tMsg CR_CARD_TRXTMsg
     * @return CR_CARD_TRXTMsg
     */
    private CR_CARD_TRXTMsg setCrCardTrxTMsg(NWZC203001PMsg pMsg, NWZC203001PmtcBean pmtcBean, CR_CARD_TRXTMsg tMsg) {
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pmtcBean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(tMsg.crCardCustRefNum, pmtcBean.getPmtcCustRefNum());
        ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthRefNum, pmtcBean.getPmtcAuthRefNum());
        ZYPEZDItemValueSetter.setValue(tMsg.billToCustAcctCd, pmtcBean.getSellToCust());
        //ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthStsCd, pmtcBean.getPmtcAuthDt());
        ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthStsCd, pmtcBean.getPmtcAuthStsCd());
        ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthAmt, pmtcBean.getPmtcAuthAmt());
        ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthTaxAmt, pmtcBean.getPmtcAuthTaxAmt());
        //ZYPEZDItemValueSetter.setValue(tMsg.crCardTrxTpCd, pmtcBean.getPmtcTrxTpCd());
        ZYPEZDItemValueSetter.setValue(tMsg.crCardTrxTpCd, pMsg.crCardTrxTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.firstTrxInfoTxt, pmtcBean.getPmtcFirstTrxInfoTxt());
        ZYPEZDItemValueSetter.setValue(tMsg.scdTrxInfoTxt, pmtcBean.getPmtcScdTrxInfoTxt());
        ZYPEZDItemValueSetter.setValue(tMsg.thirdTrxInfoTxt, pmtcBean.getPmtcThirdTrxInfoTxt());
        ZYPEZDItemValueSetter.setValue(tMsg.frthTrxInfoTxt, pmtcBean.getPmtcFrthTrxInfoTxt());
        ZYPEZDItemValueSetter.setValue(tMsg.fifthTrxInfoTxt, pmtcBean.getPmtcFifthTrxInfoTxt());
        ZYPEZDItemValueSetter.setValue(tMsg.firstTrxInfoNum, pmtcBean.getPmtcFirstTrxInfoNum());
        ZYPEZDItemValueSetter.setValue(tMsg.scdTrxInfoNum, pmtcBean.getPmtcScdTrxInfoNum());
        ZYPEZDItemValueSetter.setValue(tMsg.thirdTrxInfoNum, pmtcBean.getPmtcThirdTrxInfoNum());
        ZYPEZDItemValueSetter.setValue(tMsg.frthTrxInfoNum, pmtcBean.getPmtcFrthTrxInfoNum());
        ZYPEZDItemValueSetter.setValue(tMsg.fifthTrxInfoNum, pmtcBean.getPmtcFifthTrxInfoNum());
        ZYPEZDItemValueSetter.setValue(tMsg.authStsMsgCmntTxt, pmtcBean.getPmtcProcStsMsgTxt());
        ZYPEZDItemValueSetter.setValue(tMsg.origCrCardTrxPk, pmtcBean.getOrigCrCardTrxPk());
        ZYPEZDItemValueSetter.setValue(tMsg.crCardTrxProcStsCd, pmtcBean.getPmtcProcStsCd());
        ZYPEZDItemValueSetter.setValue(tMsg.crCardTrxApvlStsCd, pmtcBean.getPmtcApvlStsNum());
        ZYPEZDItemValueSetter.setValue(tMsg.crCardRefIdxNum, pmtcBean.getPmtcTrxRefIdxCd());
        ZYPEZDItemValueSetter.setValue(tMsg.setlCpltFlg, ZYPConstant.FLG_OFF_N);  //QC#5527
        // 2020/05/28 QC#56866 Add Start
        ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthTrxNum, pmtcBean.getPmtcAuthTrxNum());
        ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthPostCd, pmtcBean.getPmtcAuthPostCd());
        ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthHldNm, pmtcBean.getPmtcAuthHldNm());
        ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthAddr, pmtcBean.getPmtcAuthAddr());
        ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthCtyAddr, pmtcBean.getPmtcAuthCtyAddr());
        ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthStCd, pmtcBean.getPmtcAuthStCd());
        ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthFrtAmt, pmtcBean.getPmtcAuthFrtAmt());
        ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthDtyAmt, pmtcBean.getPmtcAuthDtyAmt());
        ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthCtryCd, pmtcBean.getPmtcAuthCtryCd());
        ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthFromZipCd, pmtcBean.getPmtcAuthFromZipCd());
        ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthDiscAmt, pmtcBean.getPmtcAuthDiscAmt());
        ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthAltTaxId, pmtcBean.getPmtcAuthAltTaxId());
        ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthAltTaxAmt, pmtcBean.getPmtcAuthAltTaxAmt());
        ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthLineItemCnt, pmtcBean.getPmtcAuthLineItemCnt());
        // 2020/05/28 QC#56866 Add End

        if (PROC_MODE_GET_AUTH.equals(pmtcBean.getXxProcMd())) {
            ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthDt, pmtcBean.getSlsDt());
        } else {
            //QC#27471 MOD START
            if (CR_CARD_AUTH_STS.SETTLEMENT_COMPLETED.equals(pmtcBean.getPmtcAuthStsCd())) {
                ZYPEZDItemValueSetter.setValue(tMsg.crCardSetlDt, pmtcBean.getSlsDt());
            }
            //QC#27471 MOD END
        }

        return tMsg;
    }

    // 2020/05/28 QC#56866 Add Start
    /**
     * <pre>
     * set CR_CARD_TRX_DTLTMsg.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * @param pMsg NWZC203001PMsg
     * @param pmtcDtlBean NWZC203001PmtcDtlBean
     * @param tMsg CR_CARD_TRX_DTLTMsg
     * @return CR_CARD_TRX_DTLTMsg
     */
    private CR_CARD_TRX_DTLTMsg setCrCardTrxDtlTMsg(NWZC203001PMsg pMsg, NWZC203001PmtcDtlBean pmtcDtlBean, CR_CARD_TRX_DTLTMsg tMsg) {
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pmtcDtlBean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(tMsg.crCardTrxDtlLineNum, pmtcDtlBean.getPmtcDtlLineNum());
        ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthMdseNm, pmtcDtlBean.getPmtcAuthMdseNm());
        ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthProdCd, pmtcDtlBean.getPmtcAuthProdCd());
        ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthOrdQty, pmtcDtlBean.getPmtcAuthOrdQty());
        ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthUomCd, pmtcDtlBean.getPmtcAuthUomCd());
        ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthDtlTaxAmt, pmtcDtlBean.getPmtcAuthDtlTaxAmt());
        ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthDtlTaxPct, pmtcDtlBean.getPmctAuthDtlTaxPct());
        ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthDtlAmt, pmtcDtlBean.getPmtcAuthDtlAmt());
        ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthDtlDiscAmt, pmtcDtlBean.getPmtcAuthDtlDiscAmt());
        ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthCmdtyCd, pmtcDtlBean.getPmtcAuthCmdtyCd());
        ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthUnitPrcAmt, pmtcDtlBean.getPmtcAuthUnitPrcAmt());
        ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthGrsNetInd, pmtcDtlBean.getPmtcAuthGrsNetInd());
        ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthTaxTpCd, pmtcDtlBean.getPmtcAuthTaxTpCd());
        ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthDiscInd, pmtcDtlBean.getPmtcAuthDiscInd());

        return tMsg;
    }
    // 2020/05/28 QC#56866 Add End

    /**
     * <pre>
     * set DS_CR_CARDTMsg.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * @param pMsg NWZC203001PMsg
     * @param tMsg CR_CARD_TRXTMsg
     * @return CR_CARD_TRXTMsg
     */
//    private CR_CARD_TRXTMsg setCrCardTrxTMsgForWriteCCTrx(NWZC203001PmtcBean pmtcBean, CR_CARD_TRXTMsg tMsg, NWZC203001PMsg pMsg, boolean insFlg) {
//        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pmtcBean.getGlblCmpyCd());
//        ZYPEZDItemValueSetter.setValue(tMsg.crCardCustRefNum, pmtcBean.getPmtcCustRefNum());
//        ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthRefNum, pmtcBean.getPmtcAuthRefNum());
//        ZYPEZDItemValueSetter.setValue(tMsg.billToCustAcctCd, pmtcBean.getSellToCust());
//        //ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthStsCd, pmtcBean.getPmtcAuthDt());
//        ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthStsCd, pmtcBean.getPmtcAuthStsCd());
//        ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthAmt, pmtcBean.getPmtcAuthAmt());
//        ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthTaxAmt, pmtcBean.getPmtcAuthTaxAmt());
//        ZYPEZDItemValueSetter.setValue(tMsg.crCardTrxTpCd, pmtcBean.getPmtcTrxTpCd());
//        ZYPEZDItemValueSetter.setValue(tMsg.firstTrxInfoTxt, pmtcBean.getPmtcFirstTrxInfoTxt());
//        ZYPEZDItemValueSetter.setValue(tMsg.scdTrxInfoTxt, pmtcBean.getPmtcScdTrxInfoTxt());
//        ZYPEZDItemValueSetter.setValue(tMsg.thirdTrxInfoTxt, pmtcBean.getPmtcThirdTrxInfoTxt());
//        ZYPEZDItemValueSetter.setValue(tMsg.frthTrxInfoTxt, pmtcBean.getPmtcFrthTrxInfoTxt());
//        ZYPEZDItemValueSetter.setValue(tMsg.fifthTrxInfoTxt, pmtcBean.getPmtcFifthTrxInfoTxt());
//        ZYPEZDItemValueSetter.setValue(tMsg.firstTrxInfoNum, pmtcBean.getPmtcFirstTrxInfoNum());
//        ZYPEZDItemValueSetter.setValue(tMsg.scdTrxInfoNum, pmtcBean.getPmtcScdTrxInfoNum());
//        ZYPEZDItemValueSetter.setValue(tMsg.thirdTrxInfoNum, pmtcBean.getPmtcThirdTrxInfoNum());
//        ZYPEZDItemValueSetter.setValue(tMsg.frthTrxInfoNum, pmtcBean.getPmtcFrthTrxInfoNum());
//        ZYPEZDItemValueSetter.setValue(tMsg.fifthTrxInfoNum, pmtcBean.getPmtcFifthTrxInfoNum());
//        ZYPEZDItemValueSetter.setValue(tMsg.authStsMsgCmntTxt, pmtcBean.getPmtcProcStsMsgTxt());
//        ZYPEZDItemValueSetter.setValue(tMsg.origCrCardTrxPk, pmtcBean.getOrigCrCardTrxPk());
//        ZYPEZDItemValueSetter.setValue(tMsg.crCardTrxProcStsCd, pmtcBean.getPmtcProcStsCd());
//        ZYPEZDItemValueSetter.setValue(tMsg.crCardTrxApvlStsCd, pmtcBean.getPmtcApvlStsNum());
//        ZYPEZDItemValueSetter.setValue(tMsg.crCardRefIdxNum, pmtcBean.getPmtcTrxRefIdxCd());
//        ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthDt, pmtcBean.getPmtcAuthDt());
//        ZYPEZDItemValueSetter.setValue(tMsg.crCardSetlDt, pmtcBean.getPmtcStlDt());
//        ZYPEZDItemValueSetter.setValue(tMsg.crCardCancDt, pmtcBean.getCrCardCancDt());
//        //QC#5527
//        if (insFlg) {
//        	ZYPEZDItemValueSetter.setValue(tMsg.setlCpltFlg, ZYPConstant.FLG_OFF_N);
//        }
//        return tMsg;
//    }
    private CR_CARD_TRXTMsg setCrCardTrxTMsgForWriteCCTrx(NWZC203001PmtcBean pmtcBean, CR_CARD_TRXTMsg tMsg, NWZC203001PMsg pMsg, boolean insFlg) {
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd,         pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.crCardCustRefNum,   pMsg.crCardCustRefNum);
        ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthRefNum,   pMsg.crCardAuthRefNum);
        ZYPEZDItemValueSetter.setValue(tMsg.billToCustAcctCd,   pMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthStsCd,    pMsg.crCardAuthStsCd);
        ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthAmt,      pMsg.crCardAuthAmt);
        ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthTaxAmt,   pMsg.crCardAuthTaxAmt);
        ZYPEZDItemValueSetter.setValue(tMsg.crCardTrxTpCd,      pMsg.crCardTrxTpCd);
        ZYPEZDItemValueSetter.setValue(tMsg.firstTrxInfoTxt,    pMsg.firstTrxInfoTxt);
        ZYPEZDItemValueSetter.setValue(tMsg.scdTrxInfoTxt,      pMsg.scdTrxInfoTxt);
        ZYPEZDItemValueSetter.setValue(tMsg.thirdTrxInfoTxt,    pMsg.thirdTrxInfoTxt);
        ZYPEZDItemValueSetter.setValue(tMsg.frthTrxInfoTxt,     pMsg.frthTrxInfoTxt);
        ZYPEZDItemValueSetter.setValue(tMsg.fifthTrxInfoTxt,    pMsg.fifthTrxInfoTxt);
        ZYPEZDItemValueSetter.setValue(tMsg.firstTrxInfoNum,    pMsg.firstTrxInfoNum);
        ZYPEZDItemValueSetter.setValue(tMsg.scdTrxInfoNum,      pMsg.scdTrxInfoNum);
        ZYPEZDItemValueSetter.setValue(tMsg.thirdTrxInfoNum,    pMsg.thirdTrxInfoNum);
        ZYPEZDItemValueSetter.setValue(tMsg.frthTrxInfoNum,     pMsg.frthTrxInfoNum);
        ZYPEZDItemValueSetter.setValue(tMsg.fifthTrxInfoNum,    pMsg.fifthTrxInfoNum);
        ZYPEZDItemValueSetter.setValue(tMsg.authStsMsgCmntTxt,  pMsg.authStsMsgCmntTxt);
        ZYPEZDItemValueSetter.setValue(tMsg.origCrCardTrxPk,    pMsg.origCrCardTrxPk);
        ZYPEZDItemValueSetter.setValue(tMsg.crCardTrxProcStsCd, pMsg.xxPmtcProcStsCd);
        ZYPEZDItemValueSetter.setValue(tMsg.crCardTrxApvlStsCd, pMsg.xxPmtcApvlStsNum);
        ZYPEZDItemValueSetter.setValue(tMsg.crCardRefIdxNum,    pMsg.xxPmtcTrxRefIdxCd);
        ZYPEZDItemValueSetter.setValue(tMsg.crCardAuthDt,       pMsg.crCardAuthDt);
        ZYPEZDItemValueSetter.setValue(tMsg.crCardSetlDt,       pMsg.crCardSetlDt);
        ZYPEZDItemValueSetter.setValue(tMsg.crCardCancDt,       pMsg.crCardCancDt);
        //QC#5527
        if (insFlg) {
            ZYPEZDItemValueSetter.setValue(tMsg.setlCpltFlg, ZYPConstant.FLG_OFF_N);
        }
        return tMsg;
    }

    /**
     * <pre>
     * Valid Credit Card by CHASE Paymentech.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * @param pmtcBean NWZC203001PmtcBean
     */
    private void vldCrCard(NWZC203001PmtcBean pmtcBean) {
        printDebugLog(PROGRAM_ID + "[ vldCrCard ] start");

        NWZC203001PmtcHelper pmtcHelp = new NWZC203001PmtcHelper();

        if (PROC_MODE_VOID.equals(pmtcBean.getXxProcMd())) {
            pmtcHelp.reversal(msgMap, pmtcBean);
            printDebugLog(PROGRAM_ID + "[ vldCrCard ] end");
            return;
        }

        if (PROC_MODE_PROF_CHNG.equals(pmtcBean.getXxProcMd())) {
            pmtcHelp.prflChng(msgMap, pmtcBean);
            printDebugLog(PROGRAM_ID + "[ vldCrCard ] end");
            return;
        }
        if (PROC_MODE_CAPT_ONLY.equals(pmtcBean.getXxProcMd())) {
            pmtcHelp.markForCapture(msgMap, pmtcBean);
            printDebugLog(PROGRAM_ID + "[ vldCrCard ] end");
            return;
        }

        pmtcHelp.validCrCard(msgMap, pmtcBean);
        printDebugLog(PROGRAM_ID + "[ vldCrCard ] end");
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
     * @param debugMsg debug messsage
     */
    private void printDebugLog(String debugMsg) {
        EZDDebugOutput.println(DEBUG_MSG_LVL, debugMsg, this);
    }

    /**
     * findByKeyWithCache
     * @param reqTMsg EZDTMsg
     * @return EZDTMsg
     */
    private static EZDTMsg findByKey(EZDTMsg reqTMsg) {
        return S21FastTBLAccessor.findByKey(reqTMsg);
    }

    /**
     * existsCrCardTrxTpTMsg
     * @param glblCmpyCd String
     * @param sellToCustCd String
     * @return boolean
     */
    private static boolean existsCrCardTrxTpCd(String glblCmpyCd, String cd) {
        if (!ZYPCommonFunc.hasValue(cd)) {
            return true;
        }

        final CR_CARD_TRX_TPTMsg tMsg = new CR_CARD_TRX_TPTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.crCardTrxTpCd.setValue(cd);

        return findByKey(tMsg) != null;
    }

    /**
     * existsSellToCustTMsg
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

        SELL_TO_CUSTTMsgArray sellToCustArry = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (sellToCustArry.length() == 0) {
            return false;
        }
        return true;
    }

//    /**
//     * existsPostTMsg
//     * @param glblCmpyCd String
//     * @param sellToCustCd String
//     * @return boolean
//     */
//    private static boolean existsPostCd(String glblCmpyCd, String cd) {
//        if (!ZYPCommonFunc.hasValue(cd)) {
//            return true;
//        }
//
//        final POSTTMsg tMsg = new POSTTMsg();
//        tMsg.glblCmpyCd.setValue(glblCmpyCd);
//        tMsg.postCd.setValue(cd);
//
//        return findByKey(tMsg) != null;
//    }

    /**
     * existsStTMsg
     * @param glblCmpyCd String
     * @param cd String
     * @return EZDTMsg
     */
    private static boolean existsStCd(String glblCmpyCd, String cd) {
        if (!ZYPCommonFunc.hasValue(cd)) {
            return true;
        }

        final STTMsg tMsg = new STTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.stCd.setValue(cd);

        return findByKey(tMsg) != null;
    }

    /**
     * existsCtryTMsg
     * @param glblCmpyCd String
     * @param sellToCustCd String
     * @return boolean
     */
    private static boolean existsCtryCd(String glblCmpyCd, String cd) {
        if (!ZYPCommonFunc.hasValue(cd)) {
            return true;
        }

        final CTRYTMsg tMsg = new CTRYTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.ctryCd.setValue(cd);

        return findByKey(tMsg) != null;
    }

    /**
     * existsCrCardTpTMsg
     * @param glblCmpyCd String
     * @param cd String
     * @return boolean
     */
    private static boolean existsCrCardTpCd(String glblCmpyCd, String cd) {
        if (!ZYPCommonFunc.hasValue(cd)) {
            return true;
        }

        final CR_CARD_TPTMsg tMsg = new CR_CARD_TPTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.crCardTpCd.setValue(cd);

        return findByKey(tMsg) != null;
    }

    /**
     * existscrCardAuthStsTMsg
     * @param glblCmpyCd String
     * @param sellToCustCd String
     * @return boolean
     */
    private static boolean existscrCardAuthStsCd(String glblCmpyCd, String cd) {
        if (!ZYPCommonFunc.hasValue(cd)) {
            return true;
        }

        final CR_CARD_AUTH_STSTMsg tMsg = new CR_CARD_AUTH_STSTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.crCardAuthStsCd.setValue(cd);

        return findByKey(tMsg) != null;
    }

    /**
     * getPmntCachParam
     * @param glblCmpyCd String
     * @param funcId String
     * @param colId String
     * @return String
     */
//    private static String getPmntCachParam(String glblCmpyCd, String funcId, String colId) {
    private static String getPmntCachParam(String glblCmpyCd, String funcId, String colId, String mstrValue) {
//        if (!ZYPCommonFunc.hasValue(funcId) || !ZYPCommonFunc.hasValue(funcId)) {
//            return null;
//        }
        if (ZYPCommonFunc.hasValue(mstrValue)) {
            return mstrValue;
        }

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

    private static BigDecimal null2zero(BigDecimal val) {

        if (ZYPCommonFunc.hasValue(val)) {
            return val;
        } else {
            return BigDecimal.ZERO;
        }
    }
}
