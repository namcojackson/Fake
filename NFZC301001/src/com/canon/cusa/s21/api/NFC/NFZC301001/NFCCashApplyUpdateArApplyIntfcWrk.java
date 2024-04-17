/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NFC.NFZC301001;

import java.util.List;

import parts.common.EZDDebugOutput;
import business.db.AR_APPLY_INTFC_WRKTMsg;
import business.parts.NFZC301002PMsg;

import com.canon.cusa.s21.common.NFX.NFXC308001.NFCConst;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.S21StringUtil;

/**
 * 
 * <pre>
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/05/2009   Canon           Y.Kondo         Create          N/A
 * 11/10/2009   Canon           Y.Kondo         Update          AR_APPLY_INTFC_WRK INTFC_ID, UPLD_CSV_ID, UPLD_CSV_RQST_PK add
 * 01/27/2010   Canon           H.Tokunaga      Update          ACC case add. it is DED case copy. And, The COA_PROD_CD item is added.
 *</pre>
 */
public class NFCCashApplyUpdateArApplyIntfcWrk extends S21ApiCommonBase {

    /**
     */
    public NFCCashApplyUpdateArApplyIntfcWrk() {
        super();
    }

    /**
     * 
     * <pre>
     * </pre>
     * 
     */
    protected void execute(final NFZC301002PMsg param, final ONBATCH_TYPE onBatchType, final String stsCd) {

        debugLog("execute : start");

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        updateCashApplyArApplyIntfcWrk(msgMap, onBatchType, stsCd);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            msgMap.flush();
        }

        debugLog("execute : end");
    }

    /**
     * 
     * <pre>
     * </pre>
     * 
     */
    protected void execute(final List<NFZC301002PMsg> params, final ONBATCH_TYPE onBatchType, final String stsCd) {

        for (NFZC301002PMsg msg : params) {
            execute(msg, onBatchType, stsCd);
            if (Integer.parseInt(msg.getReturnCode()) > Integer.parseInt(stsCd)) {
                break;
            }
        }
    }

    /**
     * 
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     */
    protected void updateCashApplyArApplyIntfcWrk(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType, String stsCd) {

        debugLog("updateCashApplyArApplyIntfcWrk : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();

        if (NFCConst.CST_XX_GRP_FLG_CRAT_INV_ON.equals(param.xxGrpFlg.getValue())) {

            insertApplyIntfcWrk(msgMap, stsCd);
        } else {

            AR_APPLY_INTFC_WRKTMsg intfcWrkT = findByKeyForUpdateArApplyIntfcWrk(msgMap);
            if (!(Integer.parseInt(NFZC301001.getRtnCdNFZC301002(msgMap)) > Integer.parseInt(stsCd))) {

                updApplyIntfcWrk(msgMap, intfcWrkT, stsCd);
            }
        }
        debugLog("updateCashApplyArApplyIntfcWrk : end");
    }

    /**
     * 
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @return intfcWrkT AR_APPLY_INTFC_WRKTMsg
     */
    private AR_APPLY_INTFC_WRKTMsg findByKeyForUpdateArApplyIntfcWrk(S21ApiMessageMap msgMap) {

        debugLog("findByKeyForUpdateArApplyIntfcWrk : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();

        AR_APPLY_INTFC_WRKTMsg intfcWrkT = new AR_APPLY_INTFC_WRKTMsg();
        intfcWrkT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        intfcWrkT.applyGrpKey.setValue(param.applyGrpKey.getValue());
        intfcWrkT.applyGrpSubPk.setValue(param.applyGrpSubPk.getValue());
        intfcWrkT = (AR_APPLY_INTFC_WRKTMsg) S21ApiTBLAccessor.findByKeyForUpdate(intfcWrkT);
        if (intfcWrkT == null) {

            msgMap.addXxMsgId(NFZC301001.NFCM0566E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            return intfcWrkT;
        }
        if (!param.xxIntfcUpdTs.getValue().equals(intfcWrkT.ezUpTime.getValue()) || !param.xxIntfcUpdTz.getValue().equals(intfcWrkT.ezUpTimeZone.getValue())) {

            msgMap.addXxMsgId(NFZC301001.NFCM0567E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_EXCLERR);
        }
        debugLog("findByKeyForUpdateArApplyIntfcWrk : end");

        return intfcWrkT;
    }

    /**
     * 
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param intfcWrkT AR_APPLY_INTFC_WRKTMsg
     */
    private void updApplyIntfcWrk(S21ApiMessageMap msgMap, AR_APPLY_INTFC_WRKTMsg intfcWrkT, String stsCd) {

        debugLog("updApplyIntfcWrk : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();

        intfcWrkT.procStsCd.setValue(stsCd);
        if (S21StringUtil.isNotEmpty(param.xxInvInvNum.getValue())) {
            intfcWrkT.invNum.setValue(param.xxInvInvNum.getValue());
        }
        if (NFCConst.CST_DB_INIT_VAL_NUM.compareTo(param.xxInvTrxBalPk.getValue()) != 0) {
            intfcWrkT.invTrxBalPk.setValue(param.xxInvTrxBalPk.getValue());
        }
        if (S21StringUtil.isNotEmpty(param.xxArAdjNum.getValue())) {
            intfcWrkT.arAdjNum.setValue(param.xxArAdjNum.getValue());
        }

        S21ApiTBLAccessor.update(intfcWrkT);
        debugLog("AR_APPLY_INTFC_WRK : UPDATE RETURN CODE : " + intfcWrkT.getReturnCode());
        debugLog("updApplyIntfcWrk : end");
    }

    /**
     * 
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     */
    private void insertApplyIntfcWrk(S21ApiMessageMap msgMap, String stsCd) {

        debugLog("insertApplyIntfcWrk : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();

        AR_APPLY_INTFC_WRKTMsg intfcWrkT = new AR_APPLY_INTFC_WRKTMsg();

        intfcWrkT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        intfcWrkT.applyGrpKey.setValue(param.applyGrpKey.getValue());
        intfcWrkT.applyGrpSubPk.setValue(param.applyGrpSubPk.getValue());
        intfcWrkT.bizAppId.setValue(param.bizAppId.getValue());
        intfcWrkT.procTpCd.setValue(param.procTpCd.getValue());
        intfcWrkT.dealSqNum.setValue(param.dealSqNum.getValue());
        intfcWrkT.dealSqDtlNum.setValue(param.dealSqDtlNum.getValue());
        intfcWrkT.procStsCd.setValue(stsCd);
        intfcWrkT.usrId.setValue(param.usrId.getValue());
        intfcWrkT.rcptNum.setValue(param.rcptNum.getValue());
        intfcWrkT.rcptDtlNum.setValue(param.rcptDtlNum.getValue());
        intfcWrkT.rcvFuncId.setValue(param.rcvFuncId.getValue());
        intfcWrkT.rcptInProcSqPk.setValue(param.rcptInProcSqPk.getValue());
        intfcWrkT.rcvHdrNum.setValue(param.rcvHdrNum.getValue());
        intfcWrkT.rcvDtlNum.setValue(param.rcvDtlNum.getValue());
        intfcWrkT.rcptGlDt.setValue(param.rcptGlDt.getValue());
        intfcWrkT.payerCustCd.setValue(param.payerCustCd.getValue());
        intfcWrkT.rcptTrxBalPk.setValue(param.rcptTrxBalPk.getValue());
        intfcWrkT.rcptHdrLastUpdTs.setValue(param.rcptHdrLastUpdTs.getValue());
        intfcWrkT.rcptHdrTz.setValue(param.rcptHdrTz.getValue());
        intfcWrkT.rcptTrxBalLastUpdTs.setValue(param.rcptTrxBalLastUpdTs.getValue());
        intfcWrkT.rcptTrxBalTz.setValue(param.rcptTrxBalTz.getValue());
        intfcWrkT.grpInvFlg.setValue(NFCConst.CST_GRP_INV_FLG_GRP_OFF);
        intfcWrkT.invNum.setValue(param.invNum.getValue());
        intfcWrkT.arTrxTpCd.setValue(param.arTrxTpCd.getValue());
        intfcWrkT.invTrxBalPk.setValue(param.invTrxBalPk.getValue());
        intfcWrkT.invTrxBalLastUpdTs.setValue(param.invTrxBalLastUpdTs.getValue());
        intfcWrkT.invTrxBalTz.setValue(param.invTrxBalTz.getValue());
        intfcWrkT.crNum.setValue(param.crNum.getValue());
        intfcWrkT.crTrxBalPk.setValue(param.crTrxBalPk.getValue());
        intfcWrkT.crTrxBalLastUpdTs.setValue(param.crTrxBalLastUpdTs.getValue());
        intfcWrkT.crTrxBalTz.setValue(param.crTrxBalTz.getValue());
        intfcWrkT.dealCcyCd.setValue(param.dealCcyCd.getValue());
        intfcWrkT.dealApplyAmt.setValue(param.dealApplyAmt.getValue());
        intfcWrkT.cashAppGlDt.setValue(param.cashAppGlDt.getValue());
        intfcWrkT.cashDiscPct.setValue(param.cashDiscPct.getValue());
        intfcWrkT.dealCashDiscAmt.setValue(param.dealCashDiscAmt.getValue());
        intfcWrkT.dealOnAcctCashAmt.setValue(param.dealOnAcctCashAmt.getValue());
        intfcWrkT.arAdjNum.setValue(param.arAdjNum.getValue());
        intfcWrkT.arAdjTrxTpCd.setValue(param.arAdjTrxTpCd.getValue());
        intfcWrkT.arAdjTpCd.setValue(param.arAdjTpCd.getValue());
        intfcWrkT.dealApplyAdjAmt.setValue(param.dealApplyAdjAmt.getValue());
        intfcWrkT.dealApplyAdjRsvdAmt.setValue(param.dealApplyAdjRsvdAmt.getValue());
        intfcWrkT.adjCmntTxt.setValue(param.adjCmntTxt.getValue());
        intfcWrkT.apvlPsnCd.setValue(param.apvlPsnCd.getValue());
        intfcWrkT.tocCd.setValue(param.tocCd.getValue());
        intfcWrkT.arCustRefNum.setValue(param.arCustRefNum.getValue());
        intfcWrkT.intfcId.setValue(param.intfcId.getValue());
        intfcWrkT.upldCsvId.setValue(param.upldCsvId.getValue());
        intfcWrkT.upldCsvRqstPk.setValue(param.upldCsvRqstPk.getValue());
        intfcWrkT.coaProdCd.setValue(param.coaProdCd.getValue());
        S21ApiTBLAccessor.insert(intfcWrkT);
        debugLog("AR_APPLY_INTFC_WRK : INSERT RETURN CODE : " + intfcWrkT.getReturnCode());
        if (!NFCConst.CST_EZ_RETURN_CODE_CPLT.equals(intfcWrkT.getReturnCode())) {

            msgMap.addXxMsgId(NFZC301001.NFCM0565E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("AR_APPLY_INTFC_WRK : INSERT ERROR");
            debugLog("GLBL_CMPY_CD : " + intfcWrkT.glblCmpyCd.getValue());
            debugLog("APPLY_GRP_KEY : " + intfcWrkT.applyGrpKey.getValue());
            debugLog("APPLY_GRP_SUB_PK : " + intfcWrkT.applyGrpSubPk.getValue());
        }
        debugLog("insertApplyIntfcWrk : end");
    }

    /**
     * @param logmsg String
     */
    protected void debugLog(String logmsg) {
        EZDDebugOutput.println(NFCConst.DEBUG_MSG_LVL, logmsg, this);
    }
}
