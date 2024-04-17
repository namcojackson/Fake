/**
 * <Pre>
 * Copyright(c)2015 Canon USA Inc. All rights reserved.
 * </Pre>
 */
package business.blap.NFDL0080;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;

import business.blap.NFDL0080.common.NFDL0080CommonLogic;
import business.blap.NFDL0080.constant.NFDL0080Constant;
import business.db.AR_APPLY_INTFC_WRKTMsg;
import business.db.AR_CASH_APPTMsg;
import business.db.AR_RCPTTMsg;
import business.db.AR_RCPT_DTLTMsg;
import business.db.AR_TRX_BALTMsg;
import business.db.AR_TRX_BALTMsgArray;
import business.db.CLT_NOTE_DTLTMsg;
import business.db.CLT_NOTE_TPTMsg;
import business.parts.NFZC202001PMsg;
import business.parts.NFZC301001PMsg;
import business.parts.NFZC310001PMsg;

import com.canon.cusa.s21.api.NFC.NFZC202001.NFZC202001;
import com.canon.cusa.s21.api.NFC.NFZC301001.NFZC301001;
import com.canon.cusa.s21.api.NFZ.NFZC310001.NFZC310001;
import com.canon.cusa.s21.common.NFX.NFXC306001.NFCNumbering;
import com.canon.cusa.s21.common.NFX.NFXC306001.NFXC3060Bean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CRAT_METH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_DS_WF_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_NOTE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPNumbering;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * NFDL0080BL06.
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/20   Fujitsu         M.Nakamura      Create          N/A
 * 2016/09/29   Hitachi         K.Kojima        Update          QC#11021
 * 2016/12/01   Fujitsu         H.Ikeda         Update          QC#15823
 * 2017/01/04   Fujitsu         T.Murai         Update          QC#16867
 * 2017/09/13   Hitachi         J.Kim           Update          QC#20514
 * 2018/04/06   Hitachi         J.Kim           Update          QC#25096
 * 2018/04/19   Hitachi         Y.Takeno        Update          QC#20940
 * 2018/05/11   Matsui          Y.Matsui        Update          QC#25509
 * 2018/07/13   Fujitsu         Y.Matsui        Update          QC#26993
 * 2018/07/11   Hitachi         Y.Takeno        Update          QC#26989
 * 2018/09/20   Fujitsu         T.Ogura         Update          QC#28097
 * 2019/12/19   Fujitsu         H.Ikeda         Update          QC#54619
 * 2020/01/31   Fujitsu         Y.Matsui        Update          QC#54826
 * 2020/04/16   CITS            R.Azucena       Update          QC#58672
 * 2022/02/21   CITS            K.Suzuki        Update          QC#59660
 * 2022/03/08   CITS            T.Omura         Update          QC#59660-1
 * 2022/03/14   CITS            K.Suzuki        Update          QC#59660-2
 * 2022/03/22   CITS            K.Suzuki        Update          QC#59660-3
 * </pre>
 */
public class NFDL0080BL06 extends S21BusinessHandler {

    // RCPT_TRX_BAL_PK
    private BigDecimal rcptTrxBalPk = null;

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            // NFDL0080Scrn00_CMN_Submit
            if ("NFDL0080Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFDL0080Scrn00_CMN_Submit(cMsg, sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0080Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0080Scrn00_CMN_Submit================================START", this);

        NFDL0080CMsg bizMsg = (NFDL0080CMsg) cMsg;
        NFDL0080SMsg globalMsg = (NFDL0080SMsg) sMsg;

        // Direct Input
        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt() - 1;

        NFDL0080CommonLogic.setCurrentPageIn(bizMsg, globalMsg, pagenationFrom);
        S21UserInfo userInfo = getContextUserInfo();
        if (!NFDL0080CommonLogic.checkCustCdLock(bizMsg, userInfo)) {
            NFDL0080CommonLogic.setCurrentPageOut(bizMsg, globalMsg, pagenationFrom);
            return;
        }

        // START 2021/04/16 R.Azucena [QC#58672, ADD]
        S21SsmEZDResult s21SsmEZDResult = NFDL0080Query.getInstance().findArDsWfStsCd(bizMsg);
        if (s21SsmEZDResult.isCodeNormal()) {
            String arDsWfStsCd = (String) s21SsmEZDResult.getResultObject();

            if (AR_DS_WF_STS.PENDING.equals(arDsWfStsCd) || AR_DS_WF_STS.APPROVED.equals(arDsWfStsCd) || AR_DS_WF_STS.NOAPPROVER.equals(arDsWfStsCd)) {
                bizMsg.setMessageInfo(NFDL0080Constant.NFDM0053E);
                NFDL0080CommonLogic.setCurrentPageOut(bizMsg, globalMsg, pagenationFrom);
                return;
            }
        }
        // END 2021/04/16 R.Azucena [QC#58672, ADD]

        int dataCnt = 0;
        BigDecimal totAmt = BigDecimal.ZERO;
        List<String> billToList = new ArrayList<String>();
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            if (!ZYPConstant.CHKBOX_ON_Y.equals(globalMsg.A.no(i).xxChkBox_A1.getValue())) {
                continue;
            }
            totAmt = totAmt.add(globalMsg.A.no(i).xxDealApplyAmtNum_A1.getValue());
            if (!NFDL0080CommonLogic.chkDtl(bizMsg, globalMsg, i)) {
                NFDL0080CommonLogic.setCurrentPageOut(bizMsg, globalMsg, pagenationFrom);
                return;
            }

            String billToCustCd = globalMsg.A.no(i).billToCustCd_A1.getValue();
            if (!billToList.contains(billToCustCd)) {
                billToList.add(billToCustCd);
            }
            dataCnt++;
        }

        if (dataCnt < 1) {
            // no line
            bizMsg.setMessageInfo("NFDM0002E", new String[] {"line(s)"});
            NFDL0080CommonLogic.setCurrentPageOut(bizMsg, globalMsg, pagenationFrom);
            return;
        }

        if (BigDecimal.ZERO.compareTo(totAmt.add(bizMsg.dealRmngBalGrsAmt_H1.getValue())) < 0) {
            bizMsg.setMessageInfo("NFDM0011E");
            NFDL0080CommonLogic.setCurrentPageOut(bizMsg, globalMsg, pagenationFrom);
            return;
        }

        // globalMsg -> bizMsg
        NFDL0080CommonLogic.setCurrentPageOut(bizMsg, globalMsg, pagenationFrom);

        NFDL0080CommonLogic.setCurrentPageIn(bizMsg, globalMsg, pagenationFrom);

        String userId = getContextUserInfo().getUserId();
        String btPrDt = ZYPDateUtil.getBatProcDate(bizMsg.glblCmpyCd_H1.getValue());

        this.setRcptChkNumBk(bizMsg);
        if (AR_TRX_TP.RECEIPT.equals(bizMsg.arTrxTpCd_H1.getValue())) {
            // Cash Application.
            createArApplyIntfcWrk(bizMsg, globalMsg);
        // START 2019/12/19 H.Ikeda [QC#54619,ADD]
        } else if (AR_TRX_TP.ON_ACCOUNT.equals(bizMsg.arTrxTpCd_H1.getValue())) {
            // Cash Application.(OnAcct → Invoice)
            createOnAcctApplyData(bizMsg, globalMsg, userId, btPrDt);
        // END   2019/12/19 H.Ikeda [QC#54619,ADD]
        } else {
            // Purge.
            submitNewly(bizMsg, globalMsg, userId, btPrDt);
        }

        for (String billToCustCd : billToList) {
            doCreditProfileUpdateAPI(bizMsg, billToCustCd, btPrDt);
            if ("E".equals(bizMsg.getMessageKind())) {
                return;
            }
        }

        if (AR_TRX_TP.CREDIT_MEMO.equals(bizMsg.arTrxTpCd_H1.getValue())) {
            if (!billToList.contains(bizMsg.billToCustCd_H1.getValue())) {
                doCreditProfileUpdateAPI(bizMsg, bizMsg.billToCustCd_H1.getValue(), btPrDt);
                if ("E".equals(bizMsg.getMessageKind())) {
                    return;
                }
            }
        }

        // START 2018/04/19 [QC#20940, ADD]
        createCltNoteDtl(bizMsg, globalMsg);
        // END   2018/04/19 [QC#20940, ADD]

        NFDL0080CommonLogic.setCurrentPageOut(bizMsg, globalMsg, pagenationFrom);

        if ("E".equals(bizMsg.getMessageKind())) {
            return;
        } else {
            NFDL0080CommonLogic.endCustCdUnlock(bizMsg, userInfo);
            // START 2016/12/01 H.Ikeda [QC#15823,ADD]
            if ("E".equals(bizMsg.getMessageKind())) {
                return;
            }
            // END   2016/12/01 H.Ikeda [QC#15823,ADD]
            NFDL0080CommonLogic.reSetGlobalMsgToBizMsg(globalMsg, bizMsg);
        }

        EZDDebugOutput.println(1, "doProcess_NFDL0080Scrn00_CMN_Submit================================END", this);
    }

    // START 2019/12/19 H.Ikeda [QC#54619,ADD]
    /**
     * createOnAcctApplyData
     * 
     * @param bizMsg    NFDL0080CMsg
     * @param globalMsg NFDL0080SMsg
     * @param userId    String
     * @param btPrDt    String
     */
    private void createOnAcctApplyData(NFDL0080CMsg bizMsg, NFDL0080SMsg globalMsg, String userId, String btPrDt) {

        String rcptNum = null;

        checkInvoiceEntryTimestamp(bizMsg, globalMsg);
        if ("E".equals(bizMsg.getMessageKind())) {
            return;
        }

        // START 2022/03/22 K.Suzuki [QC#59660-3,ADD]
        checkOnAccountStatusPartial(bizMsg, globalMsg);
        if ("E".equals(bizMsg.getMessageKind())) {
            return;
        }
        // END   2022/03/22 K.Suzuki [QC#59660-3,ADD]

        // AR_CASH_APPLY update canselFlg = Y
        rcptNum = updateCashApp(bizMsg);
        if (rcptNum == null) {
            return;
        }

        // AR_APPLY_INTFC_WRK Create All Cancel Data
        if (createAllCancelArApplyIntfcWrk(bizMsg, rcptNum, userId, btPrDt)) {
            return;
        }

        // AR_APPLY_INTFC_WRK Create Data
        createOnAcctPostArApplyIntfcWrk(bizMsg, globalMsg, rcptNum, userId, btPrDt);

        // START 2020/01/31 [QC#54826, ADD]
        if ("E".equals(bizMsg.getMessageKind())) {
            return;
        }
        updatePostApplyNoteTxt(bizMsg, rcptNum);
        // END 2020/01/31 [QC#54826, ADD]
    }

    /**
     * createOnAcctPostArApplyIntfcWrk
     * 
     * @param bizMsg    NFDL0080CMsg
     * @param globalMsg NFDL0080SMsg
     * @param rcptNum   String
     * @param userId    String
     * @param btPrDt    String
     */
    private void createOnAcctPostArApplyIntfcWrk(NFDL0080CMsg bizMsg, NFDL0080SMsg globalMsg, String rcptNum, String userId, String btPrDt) {
        // index
        int index = 1;
        // AR_APPLY_INTFC_WRKTMsg
        AR_APPLY_INTFC_WRKTMsg insertArApplyIntfcWrkMsg = new AR_APPLY_INTFC_WRKTMsg();
        // create group key
        String aGrKey = userId.concat(ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));

        // get AR_RCPT update time
        AR_RCPTTMsg arMsg = new AR_RCPTTMsg();
        arMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd_H1.getValue());
        arMsg.rcptNum.setValue(rcptNum);
        arMsg = (AR_RCPTTMsg) S21FastTBLAccessor.findByKey(arMsg);
        if (arMsg == null) {
            S21InfoLogOutput.println("createOnAcctPostArApplyIntfcWrk() Err:getAR_RCPT :" + arMsg.toString());
            bizMsg.setMessageInfo("NFDM0008E", null);
        }

        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {

            if (!ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(i).xxChkBox_A1.getValue())) {
                continue;
            }

            setArApplyIntfcWrkMsg(insertArApplyIntfcWrkMsg, bizMsg, aGrKey, index, userId, arMsg);
            insertArApplyIntfcWrkMsg.invNum.setValue(globalMsg.A.no(i).arTrxNum_A1.getValue());
            insertArApplyIntfcWrkMsg.arTrxTpCd.setValue(globalMsg.A.no(i).arTrxTpCd_A1.getValue());
            insertArApplyIntfcWrkMsg.invTrxBalPk.setValue(globalMsg.A.no(i).arTrxBalPk_A1.getValue());
            insertArApplyIntfcWrkMsg.invTrxBalLastUpdTs.setValue(globalMsg.A.no(i).ezUpTime_A1.getValue());
            insertArApplyIntfcWrkMsg.invTrxBalTz.setValue(globalMsg.A.no(i).ezUpTimeZone_A1.getValue());
            insertArApplyIntfcWrkMsg.dealApplyAmt.setValue(globalMsg.A.no(i).xxDealApplyAmtNum_A1.getValue());
            insertArApplyIntfcWrkMsg.cashDiscPct.setValue(globalMsg.A.no(i).cashDiscPct_A1.getValue());
            insertArApplyIntfcWrkMsg.dealCashDiscAmt.setValue(globalMsg.A.no(i).dealCashDiscAmt_A1.getValue());
            insertArApplyIntfcWrkMsg.dealApplyAdjAmt.setValue(BigDecimal.ZERO);
            insertArApplyIntfcWrkMsg.dealApplyAdjRsvdAmt.setValue(globalMsg.A.no(i).dealApplyAdjRsvdAmt_A1.getValue());
            insertArApplyIntfcWrkMsg.tocCd.setValue(globalMsg.A.no(i).tocCd_A1.getValue());
            insertArApplyIntfcWrkMsg.coaProdCd.setValue(globalMsg.A.no(i).coaProdCd_A1.getValue());

            AR_APPLY_INTFC_WRKTMsg outArApplyIntfcWrkMsg = NFDL0080CommonLogic.createArApplyIntfcWrk(insertArApplyIntfcWrkMsg);
            if (!RTNCD_NORMAL.equals(outArApplyIntfcWrkMsg.getReturnCode())) {
                S21InfoLogOutput.println("createOnAcctPostArApplyIntfcWrk() Err:createArApplyIntfcWrk :" + insertArApplyIntfcWrkMsg.toString());
                bizMsg.setMessageInfo("NFDM0008E", null);
            }

            index++;
        }

        // OnAcct Data Create
        if (BigDecimal.ZERO.compareTo(bizMsg.xxTotAmt_H1.getValue().negate()) < 0) {
            setArApplyIntfcWrkMsg(insertArApplyIntfcWrkMsg, bizMsg, aGrKey, index, userId, arMsg);
            insertArApplyIntfcWrkMsg.dealApplyAmt.setValue(bizMsg.xxTotAmt_H1.getValue().negate());
            insertArApplyIntfcWrkMsg.cashDiscPct.setValue(bizMsg.cashDiscPct_H1.getValue());
            insertArApplyIntfcWrkMsg.dealCashDiscAmt.setValue(BigDecimal.ZERO);
            insertArApplyIntfcWrkMsg.arAdjTrxTpCd.setValue(AR_TRX_TP.ON_ACCOUNT);
            insertArApplyIntfcWrkMsg.dealApplyAdjAmt.setValue(bizMsg.xxTotAmt_H1.getValue().negate());
            insertArApplyIntfcWrkMsg.dealApplyAdjRsvdAmt.setValue(BigDecimal.ZERO);
            insertArApplyIntfcWrkMsg.arCustRefNum.setValue(bizMsg.arCustRefNum_H1.getValue());

            AR_APPLY_INTFC_WRKTMsg outArApplyIntfcWrkMsg = NFDL0080CommonLogic.createArApplyIntfcWrk(insertArApplyIntfcWrkMsg);
            if (!RTNCD_NORMAL.equals(outArApplyIntfcWrkMsg.getReturnCode())) {
                S21InfoLogOutput.println("createOnAcctPostArApplyIntfcWrk() Err:createArApplyIntfcWrk :" + insertArApplyIntfcWrkMsg.toString());
                bizMsg.setMessageInfo("NFDM0008E", null);
            }
        }

        // NFZC301001 
        doApplyBatchAPI(bizMsg, aGrKey, btPrDt);
        if ("E".equals(bizMsg.getMessageKind())) {
            return;
        }
    }

    /**
     * createAllCancelArApplyIntfcWrk
     * 
     * @param bizMsg  NFDL0080CMsg
     * @param rcptNum String
     * @param userId  String
     * @param btPrDt  String
     * @return        boolean
     */
    private boolean createAllCancelArApplyIntfcWrk(NFDL0080CMsg bizMsg, String rcptNum, String userId, String btPrDt) {
        // create group key
        String aGrKey = userId.concat(ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));

        String ezUpTimeR = null;
        String ezUpdTimeZoneR = null;
        // START 2022/03/14 K.Suzuki [QC#59660-2,MOD]
        String ezUpTimeAR = null;
        String ezUpdTimeZoneAR = null;
        AR_TRX_BALTMsgArray outArTrxBalUpTmMsgArray = NFDL0080CommonLogic.findArTrxBalArray(bizMsg, rcptNum);
        if (outArTrxBalUpTmMsgArray == null) {
            EZDDebugOutput.println(1, "createAllCancelArApplyIntfcWrk() Err:findByCondition", this);
            bizMsg.setMessageInfo("NZZM0003E", null);
            return true;
        } else {
            ezUpTimeAR = outArTrxBalUpTmMsgArray.no(0).ezUpTime.getValue();
            ezUpdTimeZoneAR = outArTrxBalUpTmMsgArray.no(0).ezUpTimeZone.getValue();
        }
        // END   2022/03/14 K.Suzuki [QC#59660-2,MOD]

        // AR_RCPT TimeStamp -Get
        AR_RCPTTMsg inArRcptMsg = new AR_RCPTTMsg();
        inArRcptMsg.rcptNum.setValue(rcptNum);
        AR_RCPTTMsg outArRcptMsg = NFDL0080CommonLogic.findArRcptInfo(bizMsg, inArRcptMsg);
        if (outArRcptMsg == null) {
            EZDDebugOutput.println(1, "createAllCancelArApplyIntfcWrk() Err:findArRcptInfo", this);
            bizMsg.setMessageInfo("NZZM0003E", null);
            return true;
        } else {
            ezUpTimeR = outArRcptMsg.ezUpTime.getValue();
            ezUpdTimeZoneR = outArRcptMsg.ezUpTimeZone.getValue();
        }

        // AR_APPLY_INTFC_WRK Create cancel data
        S21SsmEZDResult s21SsmEZDResult = NFDL0080Query.getInstance().findCanselArApplyIntfcWrk(bizMsg, rcptNum);
        if (S21SsmEZDResult.RESULT_CODE_NORMAL.equals(s21SsmEZDResult.getResultCode())) {
            // Start QC#59660-1 Mod
            Map<String, Object> resMapRcpt = (Map<String, Object>) s21SsmEZDResult.getResultObject();
            if (resMapRcpt == null || resMapRcpt.size() == 0) {
                S21InfoLogOutput.println("createArApplyIntfcWrkForNewlyHeader() Err:createArApplyIntfcWrk :");
                bizMsg.setMessageInfo("NFDM0008E", null);
                return true;
            }
            AR_APPLY_INTFC_WRKTMsg arApplyIntfcWrkMsg = new AR_APPLY_INTFC_WRKTMsg();
            arApplyIntfcWrkMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd_H1.getValue());
            arApplyIntfcWrkMsg.applyGrpKey.setValue((String) resMapRcpt.get("APPLY_GRP_KEY"));
            arApplyIntfcWrkMsg.applyGrpSubPk.setValue((BigDecimal) resMapRcpt.get("APPLY_GRP_SUB_PK"));

            AR_APPLY_INTFC_WRKTMsg copyArApplyIntfcWrkMsg = (AR_APPLY_INTFC_WRKTMsg) S21FastTBLAccessor.findByKey(arApplyIntfcWrkMsg);
            AR_APPLY_INTFC_WRKTMsg insertArApplyIntfcWrkMsg = new AR_APPLY_INTFC_WRKTMsg();
            EZDMsg.copy(copyArApplyIntfcWrkMsg, null, insertArApplyIntfcWrkMsg, null);

            insertArApplyIntfcWrkMsg.applyGrpKey.setValue(aGrKey);
            insertArApplyIntfcWrkMsg.procTpCd.setValue("3");
            insertArApplyIntfcWrkMsg.procStsCd.setValue(PROC_STS.IN_COMPLETED);
            insertArApplyIntfcWrkMsg.arTrxTpCd.setValue(AR_TRX_TP.ON_ACCOUNT);
            insertArApplyIntfcWrkMsg.rcptHdrLastUpdTs.setValue(ezUpTimeR);
            insertArApplyIntfcWrkMsg.rcptHdrTz.setValue(ezUpdTimeZoneR);
            // START 2022/02/21 K.Suzuki [QC#59660,MOD]
            // insertArApplyIntfcWrkMsg.rcptTrxBalLastUpdTs.setValue(bizMsg.ezUpTime_H1.getValue());
            // insertArApplyIntfcWrkMsg.rcptTrxBalTz.setValue(bizMsg.ezUpTimeZone_H1.getValue());
            // START 2022/03/14 K.Suzuki [QC#59660-2,MOD]
            // insertArApplyIntfcWrkMsg.rcptTrxBalLastUpdTs.setValue(ezUpTimeR);
            // insertArApplyIntfcWrkMsg.rcptTrxBalTz.setValue(ezUpdTimeZoneR);
            insertArApplyIntfcWrkMsg.rcptTrxBalLastUpdTs.setValue(ezUpTimeAR);
            insertArApplyIntfcWrkMsg.rcptTrxBalTz.setValue(ezUpdTimeZoneAR);
            // END   2022/03/14 K.Suzuki [QC#59660-2,MOD]
            // END   2022/02/21 K.Suzuki [QC#59660,MOD]
            insertArApplyIntfcWrkMsg.invTrxBalLastUpdTs.setValue(bizMsg.ezUpTime_H1.getValue());
            insertArApplyIntfcWrkMsg.invTrxBalTz.setValue(bizMsg.ezUpTimeZone_H1.getValue());

            insertArApplyIntfcWrkMsg.dealApplyAmt.setValue(insertArApplyIntfcWrkMsg.dealApplyAmt.getValue().negate());
            insertArApplyIntfcWrkMsg.dealApplyAdjAmt.setValue(insertArApplyIntfcWrkMsg.dealApplyAdjAmt.getValue().negate());

            // rcptTrxBalPk
            rcptTrxBalPk = insertArApplyIntfcWrkMsg.rcptTrxBalPk.getValue();

            AR_APPLY_INTFC_WRKTMsg outArApplyIntfcWrkMsg = NFDL0080CommonLogic.createArApplyIntfcWrk(insertArApplyIntfcWrkMsg);
            if (!RTNCD_NORMAL.equals(outArApplyIntfcWrkMsg.getReturnCode())) {
                S21InfoLogOutput.println("createAllCancelArApplyIntfcWrk() Err:createArApplyIntfcWrk :" + insertArApplyIntfcWrkMsg.toString());
                bizMsg.setMessageInfo("NFDM0008E", null);
                return true;
            }
        } else if (S21SsmEZDResult.RESULT_CODE_NOT_FOUND.equals(s21SsmEZDResult.getResultCode())) {
            AR_TRX_BALTMsg inArTrxBalMsg = new AR_TRX_BALTMsg();
            ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.arTrxBalPk, bizMsg.arTrxBalPk_H1);
            AR_TRX_BALTMsg outArTrxBalMsg = NFDL0080CommonLogic.findArTrxBalInfo(bizMsg, inArTrxBalMsg);
            if (outArTrxBalMsg == null) {
                EZDDebugOutput.println(1, "createAllCancelArApplyIntfcWrk() Err:findArTrxBalInfo", this);
                bizMsg.setMessageInfo("NZZM0003E", null);
                return true;
            }

            AR_APPLY_INTFC_WRKTMsg insertArApplyIntfcWrkMsg = new AR_APPLY_INTFC_WRKTMsg();
            insertArApplyIntfcWrkMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd_H1.getValue());
            insertArApplyIntfcWrkMsg.applyGrpKey.setValue(aGrKey);
            insertArApplyIntfcWrkMsg.applyGrpSubPk.setValue(1);
            insertArApplyIntfcWrkMsg.bizAppId.setValue("NFDL0080");
            insertArApplyIntfcWrkMsg.dealSqNum.setValue("00000001");
            insertArApplyIntfcWrkMsg.dealSqDtlNum.setValue("0001");
            insertArApplyIntfcWrkMsg.procTpCd.setValue("3");
            insertArApplyIntfcWrkMsg.upldCsvRqstPk.setValue(BigDecimal.ZERO);
            insertArApplyIntfcWrkMsg.usrId.setValue(userId);
            insertArApplyIntfcWrkMsg.rcptNum.setValue(rcptNum);
            insertArApplyIntfcWrkMsg.rcptInProcSqPk.setValue(0);
            insertArApplyIntfcWrkMsg.rcptGlDt.setValue(outArTrxBalMsg.glDt.getValue());
            insertArApplyIntfcWrkMsg.payerCustCd.setValue(outArTrxBalMsg.payerCustCd.getValue());
            insertArApplyIntfcWrkMsg.rcptTrxBalPk.setValue(outArTrxBalUpTmMsgArray.no(0).arTrxBalPk.getValue());
            insertArApplyIntfcWrkMsg.procStsCd.setValue(PROC_STS.IN_COMPLETED);
            insertArApplyIntfcWrkMsg.arTrxTpCd.setValue(AR_TRX_TP.ON_ACCOUNT);
            insertArApplyIntfcWrkMsg.rcptHdrLastUpdTs.setValue(ezUpTimeR);
            insertArApplyIntfcWrkMsg.rcptHdrTz.setValue(ezUpdTimeZoneR);
            insertArApplyIntfcWrkMsg.rcptTrxBalLastUpdTs.setValue(outArTrxBalUpTmMsgArray.no(0).ezUpTime.getValue());
            insertArApplyIntfcWrkMsg.rcptTrxBalTz.setValue(outArTrxBalUpTmMsgArray.no(0).ezUpTimeZone.getValue());
            insertArApplyIntfcWrkMsg.grpInvFlg.setValue(ZYPConstant.FLG_OFF_N);
            insertArApplyIntfcWrkMsg.invNum.setValue(bizMsg.arTrxNum_H1.getValue());
            insertArApplyIntfcWrkMsg.arTrxTpCd.setValue(bizMsg.arTrxTpCd_H1.getValue());
            AR_TRX_BALTMsgArray outArTrxBalMsgArray = NFDL0080CommonLogic.findArTrxBalArray(bizMsg, bizMsg.arTrxNum_H1.getValue());
            insertArApplyIntfcWrkMsg.invTrxBalPk.setValue(outArTrxBalMsgArray.no(0).arTrxBalPk.getValue());
            insertArApplyIntfcWrkMsg.invTrxBalLastUpdTs.setValue(bizMsg.ezUpTime_H1.getValue());
            insertArApplyIntfcWrkMsg.invTrxBalTz.setValue(bizMsg.ezUpTimeZone_H1.getValue());
            insertArApplyIntfcWrkMsg.dealCcyCd.setValue(bizMsg.ccyCd_H1.getValue());
            insertArApplyIntfcWrkMsg.cashAppGlDt.setValue(bizMsg.glDt_H2.getValue());
            insertArApplyIntfcWrkMsg.crTrxBalPk.setValue(BigDecimal.ZERO);
            insertArApplyIntfcWrkMsg.dealApplyAmt.setValue(bizMsg.dealRmngBalGrsAmt_H1.getValue());
            insertArApplyIntfcWrkMsg.dealApplyAdjAmt.setValue(bizMsg.dealRmngBalGrsAmt_H1.getValue());
            insertArApplyIntfcWrkMsg.arAdjTrxTpCd.setValue(AR_TRX_TP.ON_ACCOUNT);
            insertArApplyIntfcWrkMsg.arCustRefNum.setValue(bizMsg.arCustRefNum_H1.getValue());

            // rcptTrxBalPk
            rcptTrxBalPk = insertArApplyIntfcWrkMsg.rcptTrxBalPk.getValue();
            AR_APPLY_INTFC_WRKTMsg outArApplyIntfcWrkMsg = NFDL0080CommonLogic.createArApplyIntfcWrk(insertArApplyIntfcWrkMsg);
            if (!RTNCD_NORMAL.equals(outArApplyIntfcWrkMsg.getReturnCode())) {
                S21InfoLogOutput.println("createAllCancelArApplyIntfcWrk() Err:createArApplyIntfcWrk :" + insertArApplyIntfcWrkMsg.toString());
                bizMsg.setMessageInfo("NFDM0008E", null);
                return true;
            }
        } else {
            bizMsg.setMessageInfo("ZZZM9001E");
            return true;
        }
        // End QC#59660-1 Mod

        // NFZC301001
        doApplyBatchAPI(bizMsg, aGrKey, btPrDt);
        if ("E".equals(bizMsg.getMessageKind())) {
            return true;
        }
        return false;
    }

    /**
     * updateCashApp
     * 
     * @param bizMsg NFDL0080CMsg
     * @return       String
     */
    private String updateCashApp(NFDL0080CMsg bizMsg) {
        // AR_CASH_APPLY update canselFlg = Y
        S21SsmEZDResult s21SsmEZDResult = NFDL0080Query.getInstance().findCanselCashApp(bizMsg);
        if (!S21SsmEZDResult.RESULT_CODE_NORMAL.equals(s21SsmEZDResult.getResultCode())) {
            bizMsg.setMessageInfo("ZZZM9001E");
            return null;
        }
        Map<String, Object> resMapRcpt = (Map<String, Object>) s21SsmEZDResult.getResultObject();
        if (resMapRcpt == null || resMapRcpt.size() == 0) {
            S21InfoLogOutput.println("updateCashApp() Err:getAR_CASH_APPLY");
            bizMsg.setMessageInfo("NFDM0008E", null);
            return null;
        }

        AR_CASH_APPTMsg arCashAppWrkMsg = new AR_CASH_APPTMsg();
        arCashAppWrkMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd_H1.getValue());
        arCashAppWrkMsg.arCashAppPk.setValue((BigDecimal) resMapRcpt.get("AR_CASH_APP_PK"));
        arCashAppWrkMsg.arCashAppSqNum.setValue((String) resMapRcpt.get("AR_CASH_APP_SQ_NUM"));
        AR_CASH_APPTMsg updateArCashAppWrkMsg = (AR_CASH_APPTMsg) S21FastTBLAccessor.findByKeyForUpdate(arCashAppWrkMsg);
        if (updateArCashAppWrkMsg == null) {
            S21InfoLogOutput.println("updateArCashAppWrk() Err:no data");
            bizMsg.setMessageInfo("NFDM0008E", null);
            return null;
        }
        updateArCashAppWrkMsg.arScrCancFlg.setValue(ZYPConstant.FLG_ON_Y);
        S21FastTBLAccessor.update(updateArCashAppWrkMsg);

        return (String) resMapRcpt.get("RCPT_NUM");
    }

    /**
     * setArApplyIntfcWrkMsg
     * 
     * @param insertArApplyIntfcWrkMsg AR_APPLY_INTFC_WRKTMsg
     * @param bizMsg                   NFDL0080CMsg
     * @param aGrKey                   String
     * @param index                    int
     * @param userId                   String
     * @param arMsg                    AR_RCPTTMsg
     */
    private void setArApplyIntfcWrkMsg(AR_APPLY_INTFC_WRKTMsg insertArApplyIntfcWrkMsg
                                       , NFDL0080CMsg bizMsg
                                       , String aGrKey
                                       , int index
                                       , String userId
                                       , AR_RCPTTMsg arMsg
                                       ) {
        insertArApplyIntfcWrkMsg.clear();
        insertArApplyIntfcWrkMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd_H1.getValue());
        insertArApplyIntfcWrkMsg.applyGrpKey.setValue(aGrKey);
        insertArApplyIntfcWrkMsg.applyGrpSubPk.setValue(index);
        insertArApplyIntfcWrkMsg.bizAppId.setValue(NFDL0080Constant.BUSINESS_ID);
        insertArApplyIntfcWrkMsg.upldCsvRqstPk.setValue(BigDecimal.ZERO);
        insertArApplyIntfcWrkMsg.procTpCd.setValue("1");
        insertArApplyIntfcWrkMsg.dealSqNum.setValue("00000001");
        insertArApplyIntfcWrkMsg.dealSqDtlNum.setValue(ZYPCommonFunc.leftPad(String.valueOf(index), NFDL0080Constant.BIZAPL_INT_NUM_4, NFDL0080Constant.PAD_STR_ZERO));
        insertArApplyIntfcWrkMsg.procStsCd.setValue(PROC_STS.IN_COMPLETED);
        insertArApplyIntfcWrkMsg.usrId.setValue(userId);
        insertArApplyIntfcWrkMsg.rcptNum.setValue(arMsg.rcptNum.getValue());
        insertArApplyIntfcWrkMsg.rcptInProcSqPk.setValue(BigDecimal.ZERO);
        insertArApplyIntfcWrkMsg.rcptGlDt.setValue(bizMsg.glDt_H1.getValue());
        insertArApplyIntfcWrkMsg.payerCustCd.setValue(bizMsg.dsAcctNum_H1.getValue());
        insertArApplyIntfcWrkMsg.rcptTrxBalPk.setValue(rcptTrxBalPk);
//        insertArApplyIntfcWrkMsg.rcptTrxBalPk.setValue(bizMsg.arTrxBalPk_H1.getValue());

        insertArApplyIntfcWrkMsg.rcptHdrLastUpdTs.setValue(arMsg.ezUpTime.getValue());
        insertArApplyIntfcWrkMsg.rcptHdrTz.setValue(arMsg.ezUpTimeZone.getValue());
        insertArApplyIntfcWrkMsg.rcptTrxBalLastUpdTs.setValue(arMsg.ezUpTime.getValue());
        insertArApplyIntfcWrkMsg.rcptTrxBalTz.setValue(arMsg.ezUpTimeZone.getValue());
        insertArApplyIntfcWrkMsg.grpInvFlg.setValue(ZYPConstant.FLG_OFF_N);

        insertArApplyIntfcWrkMsg.crTrxBalPk.setValue(BigDecimal.ZERO);
        insertArApplyIntfcWrkMsg.dealCcyCd.setValue(bizMsg.ccyCd_H1.getValue());
        insertArApplyIntfcWrkMsg.cashAppGlDt.setValue(bizMsg.glDt_H2.getValue());
        insertArApplyIntfcWrkMsg.dealOnAcctCashAmt.setValue(BigDecimal.ZERO);
    }
    // END   2019/12/19 H.Ikeda [QC#54619,ADD]

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NFDL0080CMsg
     * @param globalMsg NFDL0080SMsg
     * @param userId String
     * @param btPrDt String
     */
    private void submitNewly(NFDL0080CMsg bizMsg, NFDL0080SMsg globalMsg, String userId, String btPrDt) {

        checkArTrxBalTimestamp(bizMsg, "NFCM0079E");
        if ("E".equals(bizMsg.getMessageKind())) {
            return;
        }

        // actlExchRate -Get
        BigDecimal actlExchRate = getActlExchRate(bizMsg);
        if (actlExchRate == null) {
            bizMsg.setMessageInfo("NFDM0007E", new String[] {"Rate" });
            return;
        }

        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {

            if (!ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(i).xxChkBox_A1.getValue())) {
                continue;
            }

            checkArTrxBalTimestamp(bizMsg, globalMsg.A.no(i));
            if ("E".equals(bizMsg.getMessageKind())) {
                return;
            }

            // rcptNum -Get
            String rcptNum = getRcptNum();
            if (rcptNum == null) {
                bizMsg.setMessageInfo("NFDM0007E", new String[] {"Numbering" });
                return;
            }

            // arTrxBalPk -Get
            BigDecimal arTrxBalPk = getArTrxBalPk();
            if (arTrxBalPk == null) {
                bizMsg.setMessageInfo("NFDM0007E", new String[] {"Numbering" });
                return;
            }

            // AR_RCPT -create
            createArRcptForNewReceipt(bizMsg, rcptNum, actlExchRate);
            if ("E".equals(bizMsg.getMessageKind())) {
                return;
            }

            // AR_RCPT_DTL -create
            createArRcptDtlForNewReceipt(bizMsg, rcptNum);
            if ("E".equals(bizMsg.getMessageKind())) {
                return;
            }

            // AR_TRX_BAL -create
            createArTrxBalNewReceipt(bizMsg, rcptNum, arTrxBalPk, actlExchRate);
            if ("E".equals(bizMsg.getMessageKind())) {
                return;
            }

            // AR_APPLY_INTFC_WRK -create(Header)
            String aGrKey = userId.concat(ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));

            createArApplyIntfcWrkForNewlyHeader(bizMsg, rcptNum, arTrxBalPk, userId, aGrKey);
            if ("E".equals(bizMsg.getMessageKind())) {
                return;
            }

            // NFZC301001 -batch(Header)
            doApplyBatchAPI(bizMsg, aGrKey, btPrDt);
            if ("E".equals(bizMsg.getMessageKind())) {
                return;
            }

            // AR_APPLY_INTFC_WRK -create(Detail)
            String aGrKeyDtl = userId.concat(ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
            createArApplyIntfcWrkForNewlyDetail(bizMsg, globalMsg, rcptNum, arTrxBalPk, userId, aGrKeyDtl, i);
            if ("E".equals(bizMsg.getMessageKind())) {
                return;
            }

            // NFZC301001 -batch(Detail)
            doApplyBatchAPI(bizMsg, aGrKeyDtl, btPrDt);
            if ("E".equals(bizMsg.getMessageKind())) {
                return;
            }

            // START 2020/01/31 [QC#54826, ADD]
            updatePostApplyNoteTxt(bizMsg, rcptNum);
            if ("E".equals(bizMsg.getMessageKind())) {
                return;
            }
            // END 2020/01/31 [QC#54826, ADD]

            getArTrxBalUpTime(bizMsg, globalMsg);
            if ("E".equals(bizMsg.getMessageKind())) {
                return;
            }
        }

    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @return String
     */
    private String getRcptNum() {
        String rcptNum = null;

        try {
            rcptNum = ZYPNumbering.getUniqueID(NFDL0080Constant.BIZAPL_RCPTNUMKEY);
        } catch (IllegalArgumentException iae) {
            EZDDebugOutput.println(1, "getRcptNum() iae:" + iae, this);
        }
        return rcptNum;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @return BigDecimal
     */
    private BigDecimal getArTrxBalPk() {
        BigDecimal arTrxBalPk = null;

        NFCNumbering afcNumbering = new NFCNumbering();
        NFXC3060Bean outGetNumber = afcNumbering.getNumber(ZYPOracleSeqAccessor.AR_TRX_BAL_SQ, "", NFDL0080Constant.AR_TRX_BAL_SQ_START_NUM);
        if (NFDL0080Constant.AR_TRX_BAL_SQ_RTNCD_NORMAL.equals(outGetNumber.getRtrnNo())) {
            arTrxBalPk = outGetNumber.getOraSqNo();
        } else {
            EZDDebugOutput.println(1, "getArTrxBalPk() None Data", this);
        }
        return arTrxBalPk;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NFDL0080CMsg
     * @return BigDecimal
     */
    private BigDecimal getActlExchRate(NFDL0080CMsg bizMsg) {
        BigDecimal actlExchRate = null;

        actlExchRate = ZYPCodeDataUtil.getNumConstValue(NFDL0080Constant.AR_STD_EXCH_RATE, bizMsg.glblCmpyCd_H1.getValue());
        return actlExchRate;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NFDL0080CMsg
     * @param rcptNum String
     * @param actlExchRate BigDecimal
     * @return void
     */
    private void createArRcptForNewReceipt(NFDL0080CMsg bizMsg, String rcptNum, BigDecimal actlExchRate) {

        String arRcptBatNum = ZYPCodeDataUtil.getVarCharConstValue(NFDL0080Constant.AR_RCPT_CONST_KEY_RCPT_BAT_NUM, bizMsg.glblCmpyCd_H1.getValue());
        String arRcptBatSqNum = ZYPCodeDataUtil.getVarCharConstValue(NFDL0080Constant.AR_RCPT_CONST_KEY_RCPT_BAT_SQ_NUM, bizMsg.glblCmpyCd_H1.getValue());
        String arRcptTrxTpCd = AR_RCPT_TRX_TP.OFFSET;
        String arRcptTpCd = AR_RCPT_TP.OFFSET;
        String arBankAcctCd = ZYPCodeDataUtil.getVarCharConstValue(NFDL0080Constant.AR_RCPT_CONST_KEY_AR_BANK_ACCT_CD, bizMsg.glblCmpyCd_H1.getValue());
        String tocCd = ZYPCodeDataUtil.getVarCharConstValue(NFDL0080Constant.AR_RCPT_CONST_KEY_AR_RCPT_TOC_CD, bizMsg.glblCmpyCd_H1.getValue());
        String coaProdCd = ZYPCodeDataUtil.getVarCharConstValue(NFDL0080Constant.AR_RCPT_CONST_KEY_AR_RCPT_PROD_CD, bizMsg.glblCmpyCd_H1.getValue());

        AR_RCPTTMsg inArRcptMsg = new AR_RCPTTMsg();
        inArRcptMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd_H1.getValue());
        inArRcptMsg.rcptNum.setValue(rcptNum);
        inArRcptMsg.rcptBatNum.setValue(arRcptBatNum);
        inArRcptMsg.rcptBatSqNum.setValue(arRcptBatSqNum);
        inArRcptMsg.arRcptTrxTpCd.setValue(arRcptTrxTpCd);
        inArRcptMsg.arRcptTpCd.setValue(arRcptTpCd);
        inArRcptMsg.dealCcyCd.setValue(bizMsg.ccyCd_H1.getValue());
        inArRcptMsg.dealRcptAmt.setValue(BigDecimal.ZERO);
        inArRcptMsg.dealApplyAmt.setValue(BigDecimal.ZERO);
        inArRcptMsg.dealApplyAdjAmt.setValue(BigDecimal.ZERO);
        inArRcptMsg.dealRfAmt.setValue(BigDecimal.ZERO);
        inArRcptMsg.dealVoidAmt.setValue(BigDecimal.ZERO);
        inArRcptMsg.dealRcptRmngBalAmt.setValue(BigDecimal.ZERO);
        inArRcptMsg.exchRate.setValue(actlExchRate);
        inArRcptMsg.funcCcyCd.setValue(bizMsg.stdCcyCd_H1.getValue());
        inArRcptMsg.funcRcptAmt.setValue(BigDecimal.ZERO);
        inArRcptMsg.funcApplyAmt.setValue(BigDecimal.ZERO);
        inArRcptMsg.funcApplyAdjAmt.setValue(BigDecimal.ZERO);
        inArRcptMsg.funcRfAmt.setValue(BigDecimal.ZERO);
        inArRcptMsg.funcVoidAmt.setValue(BigDecimal.ZERO);
        inArRcptMsg.funcRvalVarAmt.setValue(BigDecimal.ZERO);
        inArRcptMsg.funcRcptRmngBalAmt.setValue(BigDecimal.ZERO);
        inArRcptMsg.rcptDt.setValue(bizMsg.glDt_H1.getValue());
        inArRcptMsg.glDt.setValue(bizMsg.glDt_H1.getValue());
        inArRcptMsg.payerCustCd.setValue(bizMsg.dsAcctNum_H1.getValue());
        inArRcptMsg.arBankAcctCd.setValue(arBankAcctCd);
        inArRcptMsg.arCashApplyStsCd.setValue(AR_CASH_APPLY_STS.APPLIED);
        inArRcptMsg.voidFlg.setValue(ZYPConstant.FLG_OFF_N);
        inArRcptMsg.tocCd.setValue(tocCd);
        inArRcptMsg.coaProdCd.setValue(coaProdCd);
        inArRcptMsg.rcptChkNum.clear();
        // inArRcptMsg.crAnlstPsnCd.setValue(bizMsg.crMgrPsnCd_H1.getValue()); // DEL 2017/01/04 [QC#16867]
        inArRcptMsg.rcptFirstCmntTxt.clear();
        inArRcptMsg.rcptScdCmntTxt.clear();

        // EXPT_FIRST_BANK_CHRG_CCY_CD
        inArRcptMsg.exptFirstBankChrgCcyCd.setValue(inArRcptMsg.dealCcyCd.getValue());
        // DEAL_FIRST_EXPT_CHRG_AMT
        inArRcptMsg.dealFirstExptChrgAmt.setValue(BigDecimal.ZERO);
        // FUNC_FIRST_EXPT_CHRG_AMT
        inArRcptMsg.funcFirstExptChrgAmt.setValue(BigDecimal.ZERO);
        // EXPT_SCD_BANK_CHRG_CCY_CD
        inArRcptMsg.exptScdBankChrgCcyCd.setValue(inArRcptMsg.dealCcyCd.getValue());
        // DEAL_SCD_EXPT_CHRG_AMT
        inArRcptMsg.dealScdExptChrgAmt.setValue(BigDecimal.ZERO);
        // FUNC_SCD_EXPT_CHRG_AMT
        inArRcptMsg.funcScdExptChrgAmt.setValue(BigDecimal.ZERO);
        // DEAL_NET_RCPT_AMT
        inArRcptMsg.dealNetRcptAmt.setValue(inArRcptMsg.dealRcptAmt.getValue());
        // FUNC_NET_RCPT_AMT
        inArRcptMsg.funcNetRcptAmt.setValue(inArRcptMsg.funcRcptAmt.getValue());
        // FGN_EXCH_LOSS_GAIN_AMT
        inArRcptMsg.fgnExchLossGainAmt.setValue(BigDecimal.ZERO);

        inArRcptMsg.cratMethTpCd.setValue(AR_CRAT_METH_TP.MANUAL_ENTRY);

        inArRcptMsg.exptFlg.setValue(ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.ajeCratCpltFlg, ZYPConstant.FLG_OFF_N);
        // START 2018/09/20 T.Ogura [QC#28097,ADD]
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.arRcptSrcCd, AR_RCPT_SRC.SYSTEM_CREATED);
        // END   2018/09/20 T.Ogura [QC#28097,ADD]

        AR_RCPTTMsg arRcptOutMsg = NFDL0080CommonLogic.createArRcptForNewReceipt(inArRcptMsg);
        if (!RTNCD_NORMAL.equals(arRcptOutMsg.getReturnCode())) {
            EZDDebugOutput.println(1, "createArRcptForNewReceipt() Err", this);
            bizMsg.setMessageInfo("NFDM0008E", null);
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NFDL0080CMsg
     * @param rcptNum String
     * @return void
     */
    private void createArRcptDtlForNewReceipt(NFDL0080CMsg bizMsg, String rcptNum) {

        AR_RCPT_DTLTMsg inArRcptDtlMsg = new AR_RCPT_DTLTMsg();
        inArRcptDtlMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd_H1.getValue());
        inArRcptDtlMsg.rcptNum.setValue(rcptNum);
        inArRcptDtlMsg.rcptDtlNum.setValue(ZYPCommonFunc.leftPad(NFDL0080Constant.STR_1, NFDL0080Constant.MAX_LENGTH_4, NFDL0080Constant.PAD_STR_0));
        inArRcptDtlMsg.dealRcptDtlAmt.setValue(BigDecimal.ZERO);
        inArRcptDtlMsg.funcRcptDtlAmt.setValue(BigDecimal.ZERO);
        inArRcptDtlMsg.autoCratFlg.setValue(ZYPConstant.FLG_OFF_N);
        inArRcptDtlMsg.arCustRefNum.setValue(bizMsg.rcptChkNum_H1.getValue());
        inArRcptDtlMsg.arCustRefTpCd.setValue(AR_TRX_TP.INVOICE);
        inArRcptDtlMsg.grpInvFlg.setValue(ZYPConstant.FLG_OFF_N);

        AR_RCPT_DTLTMsg arRcptDtlOutMsg = NFDL0080CommonLogic.createArRcptDtlForNewReceipt(inArRcptDtlMsg);
        if (!RTNCD_NORMAL.equals(arRcptDtlOutMsg.getReturnCode())) {
            EZDDebugOutput.println(1, "createArRcptDtlForNewReceipt() Err", this);
            bizMsg.setMessageInfo("NFDM0008E", null);
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NFDL0080CMsg
     * @param rcptNum String
     * @param arTrxBalPk BigDecimal
     * @param actlExchRate BigDecimal
     * @return void
     */
    private void createArTrxBalNewReceipt(NFDL0080CMsg bizMsg, String rcptNum, BigDecimal arTrxBalPk, BigDecimal actlExchRate) {

        String tocCd = ZYPCodeDataUtil.getVarCharConstValue(NFDL0080Constant.AR_RCPT_CONST_KEY_AR_RCPT_TOC_CD, bizMsg.glblCmpyCd_H1.getValue());
        String coaProdCd = ZYPCodeDataUtil.getVarCharConstValue(NFDL0080Constant.AR_RCPT_CONST_KEY_AR_RCPT_PROD_CD, bizMsg.glblCmpyCd_H1.getValue());

        AR_TRX_BALTMsg inArTrxBalMsg = new AR_TRX_BALTMsg();
        inArTrxBalMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd_H1.getValue());
        inArTrxBalMsg.arTrxBalPk.setValue(arTrxBalPk);
        inArTrxBalMsg.arTrxNum.setValue(rcptNum);
        inArTrxBalMsg.arTrxTpCd.setValue(AR_TRX_TP.RECEIPT);
        inArTrxBalMsg.dealCcyCd.setValue(bizMsg.ccyCd_H1.getValue());
        inArTrxBalMsg.dealOrigGrsAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.dealApplyGrsAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.dealApplyCashDiscAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.dealApplyCrAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.dealApplyAdjAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.dealApplyAdjRsvdAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.dealRmngBalGrsAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.dealNetSlsAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.dealFrtAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.dealTaxAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.dealInvDiscAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.dealPrmoDiscAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.dealRcptVoidAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.exchRate.setValue(actlExchRate);
        inArTrxBalMsg.funcCcyCd.setValue(bizMsg.stdCcyCd_H1.getValue());
        inArTrxBalMsg.funcOrigGrsAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.funcApplyGrsAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.funcApplyCashDiscAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.funcApplyCrAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.funcApplyAdjAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.funcApplyAdjRsvdAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.funcRvalVarAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.funcRmngBalGrsAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.funcNetSlsAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.funcFrtAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.funcTaxAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.funcInvDiscAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.funcPrmoDiscAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.funcRcptVoidAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.cashDiscPct.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.arCashApplyStsCd.setValue(AR_CASH_APPLY_STS.APPLIED);
        inArTrxBalMsg.arTrxDt.setValue(bizMsg.glDt_H1.getValue());
        inArTrxBalMsg.glDt.setValue(bizMsg.glDt_H1.getValue());
        inArTrxBalMsg.billToCustAcctCd.setValue(bizMsg.dsAcctNum_H1.getValue());
        // START 2016/09/28 K.Kojima [QC#11021,MOD]
        // inArTrxBalMsg.billToCustCd.setValue(bizMsg.dsAcctNum_H1.getValue());
        // END 2016/09/28 K.Kojima [QC#11021,MOD]
        inArTrxBalMsg.sellToCustCd.setValue(bizMsg.dsAcctNum_H1.getValue());
        inArTrxBalMsg.payerCustCd.setValue(bizMsg.dsAcctNum_H1.getValue());
        inArTrxBalMsg.tocCd.setValue(tocCd);
        inArTrxBalMsg.coaProdCd.setValue(coaProdCd);
        inArTrxBalMsg.arCustRefNum.clear();

        inArTrxBalMsg.exptFlg.setValue(ZYPConstant.FLG_OFF_N);
        inArTrxBalMsg.arAutoPurgeOfsFlg.setValue(ZYPConstant.FLG_OFF_N);

        AR_TRX_BALTMsg arTrxBalOutMsg = NFDL0080CommonLogic.createArTrxBalNewReceipt(bizMsg, inArTrxBalMsg);
        if (!RTNCD_NORMAL.equals(arTrxBalOutMsg.getReturnCode())) {
            EZDDebugOutput.println(1, "createArTrxBalNewReceipt() Err", this);
            bizMsg.setMessageInfo("NFDM0008E", null);
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NFDL0080CMsg
     * @param rcptNum String
     * @param arTrxBalPk BigDecimal
     * @param usrId String
     * @param aGrKey String
     */
    private void createArApplyIntfcWrkForNewlyHeader(NFDL0080CMsg bizMsg, String rcptNum, BigDecimal arTrxBalPk, String usrId, String aGrKey) {

        String ezUpTimeR = null;
        String ezUpdTimeZoneR = null;
        String ezUpTimeT = null;
        String ezUpdTimeZoneT = null;

        // AR_RCPT TimeStamp -Get
        AR_RCPTTMsg inArRcptMsg = new AR_RCPTTMsg();
        inArRcptMsg.rcptNum.setValue(rcptNum);
        AR_RCPTTMsg outArRcptMsg = NFDL0080CommonLogic.findArRcptInfo(bizMsg, inArRcptMsg);
        if (outArRcptMsg == null) {
            EZDDebugOutput.println(1, "createArApplyIntfcWrkForNewlyHeader() Err:findArRcptInfo", this);
            bizMsg.setMessageInfo("NZZM0003E", null);
            return;
        } else {
            ezUpTimeR = outArRcptMsg.ezUpTime.getValue();
            ezUpdTimeZoneR = outArRcptMsg.ezUpTimeZone.getValue();
        }

        // AR_TRX_BAL TimeStamp -Get
        AR_TRX_BALTMsg inArTrxBalMsg = new AR_TRX_BALTMsg();
        inArTrxBalMsg.arTrxBalPk.setValue(arTrxBalPk);
        AR_TRX_BALTMsg outArTrxBalMsg = NFDL0080CommonLogic.findArTrxBalInfo(bizMsg, inArTrxBalMsg);
        if (outArTrxBalMsg == null) {
            EZDDebugOutput.println(1, "createArApplyIntfcWrkForNewlyHeader() Err:findArTrxBalInfo", this);
            bizMsg.setMessageInfo("NZZM0003E", null);
            return;
        } else {
            ezUpTimeT = outArTrxBalMsg.ezUpTime.getValue();
            ezUpdTimeZoneT = outArTrxBalMsg.ezUpTimeZone.getValue();
        }

        // AR_APPLY_INTFC_WRK -Create
        AR_APPLY_INTFC_WRKTMsg inArApplyIntfcWrkMsg = new AR_APPLY_INTFC_WRKTMsg();
        inArApplyIntfcWrkMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd_H1.getValue());
        inArApplyIntfcWrkMsg.applyGrpKey.setValue(aGrKey);
        inArApplyIntfcWrkMsg.applyGrpSubPk.setValue(1);
        inArApplyIntfcWrkMsg.bizAppId.setValue(NFDL0080Constant.BUSINESS_ID);
        inArApplyIntfcWrkMsg.upldCsvRqstPk.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.procTpCd.setValue("1");
        inArApplyIntfcWrkMsg.dealSqNum.setValue("00000001");
        inArApplyIntfcWrkMsg.dealSqDtlNum.setValue(ZYPCommonFunc.leftPad(NFDL0080Constant.STR_1, NFDL0080Constant.MAX_LENGTH_4, NFDL0080Constant.PAD_STR_0));
        inArApplyIntfcWrkMsg.procStsCd.setValue(PROC_STS.IN_COMPLETED);
        inArApplyIntfcWrkMsg.usrId.setValue(usrId);
        inArApplyIntfcWrkMsg.rcptNum.setValue(rcptNum);
        inArApplyIntfcWrkMsg.rcptDtlNum.setValue("0");
        inArApplyIntfcWrkMsg.rcptInProcSqPk.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.rcptGlDt.setValue(bizMsg.glDt_H1.getValue());
        inArApplyIntfcWrkMsg.payerCustCd.setValue(bizMsg.dsAcctNum_H1.getValue());
        inArApplyIntfcWrkMsg.rcptTrxBalPk.setValue(arTrxBalPk);
        inArApplyIntfcWrkMsg.rcptHdrLastUpdTs.setValue(ezUpTimeR);
        inArApplyIntfcWrkMsg.rcptHdrTz.setValue(ezUpdTimeZoneR);
        inArApplyIntfcWrkMsg.rcptTrxBalLastUpdTs.setValue(ezUpTimeT);
        inArApplyIntfcWrkMsg.rcptTrxBalTz.setValue(ezUpdTimeZoneT);
        inArApplyIntfcWrkMsg.grpInvFlg.setValue(ZYPConstant.FLG_OFF_N);
        inArApplyIntfcWrkMsg.invTrxBalPk.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.crTrxBalPk.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.dealCcyCd.setValue(bizMsg.ccyCd_H1.getValue());
        inArApplyIntfcWrkMsg.dealApplyAmt.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.dealCashDiscAmt.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.dealOnAcctCashAmt.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.dealApplyAdjAmt.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.dealApplyAdjRsvdAmt.setValue(BigDecimal.ZERO);

        AR_APPLY_INTFC_WRKTMsg outArApplyIntfcWrkMsg = NFDL0080CommonLogic.createArApplyIntfcWrk(inArApplyIntfcWrkMsg);
        if (!RTNCD_NORMAL.equals(outArApplyIntfcWrkMsg.getReturnCode())) {
            S21InfoLogOutput.println("createArApplyIntfcWrkForNewlyHeader() Err:createArApplyIntfcWrk :" + inArApplyIntfcWrkMsg.toString());
            bizMsg.setMessageInfo("NFDM0008E", null);
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NFDL0080CMsg
     * @param globalMsg NFDL0080SMsg
     * @param rcptNum String
     * @param arTrxBalPk BigDecimal
     * @param usrId String
     * @param aGrKey String
     * @param index int
     * @return void
     */
    private void createArApplyIntfcWrkForNewlyDetail(NFDL0080CMsg bizMsg, NFDL0080SMsg globalMsg, String rcptNum, BigDecimal arTrxBalPk, String usrId, String aGrKey, int index) {

        String ezUpTimeR = null;
        String ezUpdTimeZoneR = null;
        String ezUpTimeT = null;
        String ezUpdTimeZoneT = null;
        AR_APPLY_INTFC_WRKTMsg inArApplyIntfcWrkMsg = null;

        // AR_RCPT TimeStamp -Get
        AR_RCPTTMsg inArRcptMsg = new AR_RCPTTMsg();
        inArRcptMsg.rcptNum.setValue(rcptNum);
        AR_RCPTTMsg outArRcptMsg = NFDL0080CommonLogic.findArRcptInfo(bizMsg, inArRcptMsg);
        if (outArRcptMsg == null) {
            EZDDebugOutput.println(1, "createArApplyIntfcWrkForNewlyHeader() Err:findArRcptInfo", this);
            bizMsg.setMessageInfo("NZZM0003E", null);
            return;
        } else {
            ezUpTimeR = outArRcptMsg.ezUpTime.getValue();
            ezUpdTimeZoneR = outArRcptMsg.ezUpTimeZone.getValue();
        }

        // AR_TRX_BAL TimeStamp -Get
        AR_TRX_BALTMsg inArTrxBalMsg = new AR_TRX_BALTMsg();
        inArTrxBalMsg.arTrxBalPk.setValue(arTrxBalPk);
        AR_TRX_BALTMsg outArTrxBalMsg = NFDL0080CommonLogic.findArTrxBalInfo(bizMsg, inArTrxBalMsg);
        if (outArTrxBalMsg == null) {
            EZDDebugOutput.println(1, "createArApplyIntfcWrkForNewlyHeader() Err:findArTrxBalInfo", this);
            bizMsg.setMessageInfo("NZZM0003E", null);
            return;
        } else {
            ezUpTimeT = outArTrxBalMsg.ezUpTime.getValue();
            ezUpdTimeZoneT = outArTrxBalMsg.ezUpTimeZone.getValue();
        }

        // AR_APPLY_INTFC_WRK -Create(Header Field)
        inArApplyIntfcWrkMsg = new AR_APPLY_INTFC_WRKTMsg();
        int aGrSubKey = 1;
        String dealSqDtlNum = String.valueOf(aGrSubKey);

        // Common
        inArApplyIntfcWrkMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd_H1.getValue());
        inArApplyIntfcWrkMsg.applyGrpKey.setValue(aGrKey);
        inArApplyIntfcWrkMsg.applyGrpSubPk.setValue(aGrSubKey);
        inArApplyIntfcWrkMsg.bizAppId.setValue(NFDL0080Constant.BUSINESS_ID);
        inArApplyIntfcWrkMsg.upldCsvRqstPk.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.procTpCd.setValue("1");
        inArApplyIntfcWrkMsg.dealSqNum.setValue("00000001");
        inArApplyIntfcWrkMsg.dealSqDtlNum.setValue(ZYPCommonFunc.leftPad(dealSqDtlNum, NFDL0080Constant.MAX_LENGTH_4, NFDL0080Constant.PAD_STR_0));
        inArApplyIntfcWrkMsg.procStsCd.setValue(PROC_STS.IN_COMPLETED);
        inArApplyIntfcWrkMsg.usrId.setValue(usrId);
        inArApplyIntfcWrkMsg.rcptNum.setValue(rcptNum);
        inArApplyIntfcWrkMsg.rcptInProcSqPk.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.rcptTrxBalPk.setValue(arTrxBalPk);
        inArApplyIntfcWrkMsg.rcptHdrLastUpdTs.setValue(ezUpTimeR);
        inArApplyIntfcWrkMsg.rcptHdrTz.setValue(ezUpdTimeZoneR);
        inArApplyIntfcWrkMsg.rcptTrxBalLastUpdTs.setValue(ezUpTimeT);
        inArApplyIntfcWrkMsg.rcptTrxBalTz.setValue(ezUpdTimeZoneT);
        inArApplyIntfcWrkMsg.grpInvFlg.setValue(ZYPConstant.FLG_OFF_N);
        inArApplyIntfcWrkMsg.crTrxBalPk.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.dealCcyCd.setValue(bizMsg.ccyCd_H1.getValue());
        inArApplyIntfcWrkMsg.dealApplyAmt.setValue(globalMsg.A.no(index).xxDealApplyAmtNum_A1.getValue().negate());
        // START 2018/05/11 [QC#25509, MOD]
        // inArApplyIntfcWrkMsg.cashAppGlDt.setValue(bizMsg.glDt_H1.getValue());
        inArApplyIntfcWrkMsg.cashAppGlDt.setValue(bizMsg.glDt_H2.getValue());
        // END   2018/05/11 [QC#25509, MOD]
        inArApplyIntfcWrkMsg.dealApplyAdjRsvdAmt.setValue(BigDecimal.ZERO);

        inArApplyIntfcWrkMsg.payerCustCd.setValue(bizMsg.dsAcctNum_H1.getValue());
        inArApplyIntfcWrkMsg.rcptGlDt.setValue(bizMsg.glDt_H1.getValue());

        inArApplyIntfcWrkMsg.invNum.setValue(bizMsg.arTrxNum_H1.getValue());
        inArApplyIntfcWrkMsg.arTrxTpCd.setValue(bizMsg.arTrxTpCd_H1.getValue());
        inArApplyIntfcWrkMsg.invTrxBalPk.setValue(bizMsg.arTrxBalPk_H1.getValue());
        inArApplyIntfcWrkMsg.invTrxBalLastUpdTs.setValue(bizMsg.ezUpTime_H1.getValue());
        inArApplyIntfcWrkMsg.invTrxBalTz.setValue(bizMsg.ezUpTimeZone_H1.getValue());
        inArApplyIntfcWrkMsg.cashDiscPct.setValue(bizMsg.cashDiscPct_H1.getValue());
        inArApplyIntfcWrkMsg.dealCashDiscAmt.setValue(bizMsg.dealCashDiscAmt_H1.getValue());
        inArApplyIntfcWrkMsg.dealOnAcctCashAmt.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.arAdjTpCd.clear();
        inArApplyIntfcWrkMsg.dealApplyAdjAmt.setValue(BigDecimal.ZERO);

        AR_APPLY_INTFC_WRKTMsg hdrArApplyIntfcWrkMsg = NFDL0080CommonLogic.createArApplyIntfcWrk(inArApplyIntfcWrkMsg);

        if (!RTNCD_NORMAL.equals(hdrArApplyIntfcWrkMsg.getReturnCode())) {
            S21InfoLogOutput.println("createArApplyIntfcWrkForNewlyDetail() Err:createArApplyIntfcWrk :" + inArApplyIntfcWrkMsg.toString());
            bizMsg.setMessageInfo("NFDM0008E", null);
            return;
        }

        inArApplyIntfcWrkMsg = new AR_APPLY_INTFC_WRKTMsg();
        aGrSubKey = aGrSubKey + 1;
        dealSqDtlNum = String.valueOf(aGrSubKey);

        // Common
        inArApplyIntfcWrkMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd_H1.getValue());
        inArApplyIntfcWrkMsg.applyGrpKey.setValue(aGrKey);
        inArApplyIntfcWrkMsg.applyGrpSubPk.setValue(aGrSubKey);
        inArApplyIntfcWrkMsg.bizAppId.setValue(NFDL0080Constant.BUSINESS_ID);
        inArApplyIntfcWrkMsg.upldCsvRqstPk.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.procTpCd.setValue("1");
        inArApplyIntfcWrkMsg.dealSqNum.setValue("00000001");
        inArApplyIntfcWrkMsg.dealSqDtlNum.setValue(ZYPCommonFunc.leftPad(dealSqDtlNum, NFDL0080Constant.MAX_LENGTH_4, NFDL0080Constant.PAD_STR_0));
        inArApplyIntfcWrkMsg.procStsCd.setValue(PROC_STS.IN_COMPLETED);
        inArApplyIntfcWrkMsg.usrId.setValue(usrId);
        inArApplyIntfcWrkMsg.rcptNum.setValue(rcptNum);
        inArApplyIntfcWrkMsg.rcptInProcSqPk.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.rcptTrxBalPk.setValue(arTrxBalPk);
        inArApplyIntfcWrkMsg.rcptHdrLastUpdTs.setValue(ezUpTimeR);
        inArApplyIntfcWrkMsg.rcptHdrTz.setValue(ezUpdTimeZoneR);
        inArApplyIntfcWrkMsg.rcptTrxBalLastUpdTs.setValue(ezUpTimeT);
        inArApplyIntfcWrkMsg.rcptTrxBalTz.setValue(ezUpdTimeZoneT);
        inArApplyIntfcWrkMsg.grpInvFlg.setValue(ZYPConstant.FLG_OFF_N);
        inArApplyIntfcWrkMsg.crTrxBalPk.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.dealCcyCd.setValue(bizMsg.ccyCd_H1.getValue());
        inArApplyIntfcWrkMsg.dealApplyAmt.setValue(globalMsg.A.no(index).xxDealApplyAmtNum_A1.getValue());
        // START 2018/05/11 [QC#25509, MOD]
        // inArApplyIntfcWrkMsg.cashAppGlDt.setValue(bizMsg.glDt_H1.getValue());
        inArApplyIntfcWrkMsg.cashAppGlDt.setValue(bizMsg.glDt_H2.getValue());
        // END   2018/05/11 [QC#25509, MOD]
        inArApplyIntfcWrkMsg.dealApplyAdjRsvdAmt.setValue(BigDecimal.ZERO);

        inArApplyIntfcWrkMsg.payerCustCd.setValue(bizMsg.dsAcctNum_H1.getValue());
        inArApplyIntfcWrkMsg.rcptGlDt.setValue(bizMsg.glDt_H1.getValue());

        // Invoice Detail
        inArApplyIntfcWrkMsg.invNum.setValue(globalMsg.A.no(index).arTrxNum_A1.getValue());
        inArApplyIntfcWrkMsg.arTrxTpCd.setValue(globalMsg.A.no(index).arTrxTpCd_A1.getValue());
        inArApplyIntfcWrkMsg.invTrxBalPk.setValue(globalMsg.A.no(index).arTrxBalPk_A1.getValue());
        inArApplyIntfcWrkMsg.invTrxBalLastUpdTs.setValue(globalMsg.A.no(index).ezUpTime_A1.getValue());
        inArApplyIntfcWrkMsg.invTrxBalTz.setValue(globalMsg.A.no(index).ezUpTimeZone_A1.getValue());
        inArApplyIntfcWrkMsg.cashDiscPct.setValue(globalMsg.A.no(index).cashDiscPct_A1.getValue());
        inArApplyIntfcWrkMsg.dealCashDiscAmt.setValue(globalMsg.A.no(index).dealCashDiscAmt_A1.getValue());
        inArApplyIntfcWrkMsg.dealOnAcctCashAmt.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.arAdjTpCd.clear();
        inArApplyIntfcWrkMsg.dealApplyAdjAmt.setValue(BigDecimal.ZERO);

        AR_APPLY_INTFC_WRKTMsg outArApplyIntfcWrkMsg = NFDL0080CommonLogic.createArApplyIntfcWrk(inArApplyIntfcWrkMsg);

        if (!RTNCD_NORMAL.equals(outArApplyIntfcWrkMsg.getReturnCode())) {
            S21InfoLogOutput.println("createArApplyIntfcWrkForNewlyDetail() Err:createArApplyIntfcWrk :" + inArApplyIntfcWrkMsg.toString());
            bizMsg.setMessageInfo("NFDM0008E", null);
            return;
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NFDL0080CMsg
     * @param aGrKey String
     * @param btPrDt String
     * @return boolean
     */
    private boolean doApplyBatchAPI(NFDL0080CMsg bizMsg, String aGrKey, String btPrDt) {

        boolean retVal = true;

        NFZC301001 api = new NFZC301001();
        NFZC301001PMsg apiMsg = new NFZC301001PMsg();
        apiMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd_H1.getValue());
        apiMsg.applyGrpKey.setValue(aGrKey);
        apiMsg.dealSqNum.setValue("00000001");
        apiMsg.batDt.setValue(btPrDt);
        api.execute(apiMsg, ONBATCH_TYPE.ONLINE);
        String result = apiMsg.getReturnCode();

        // result == "1"
        if (NFDL0080Constant.APPLY_RTNCD_NORMAL.equals(result)) {
            bizMsg.setMessageInfo("NZZM0002I", new String[] {"Cash Application" });

        } else {

            // result == "0"
            if (NFDL0080Constant.APPLY_RTNCD_UN_PROCCES.equals(result)) {
                bizMsg.setMessageInfo("NFDM0007E", new String[] {"Unprocessing" });

                // result == "2"
            } else if (NFDL0080Constant.APPLY_RTNCD_CASHAPP_ERROR.equals(result)) {
                bizMsg.setMessageInfo("NFDM0007E", new String[] {"Cash Application Error" });

                // result == "3"
            } else if (NFDL0080Constant.APPLY_RTNCD_EXCLUSION_ERROR.equals(result)) {
                bizMsg.setMessageInfo("NFDM0007E", new String[] {"Exclusion Error" });

                // result == "4"
            } else if (NFDL0080Constant.APPLY_RTNCD_OTHERS_ERROR.equals(result)) {
                bizMsg.setMessageInfo("NFDM0007E", new String[] {"Others Error" });

            } else {
                bizMsg.setMessageInfo("NFDM0007E", new String[] {"Others Error" });
                EZDDebugOutput.println(1, "doApplyBatchAPI() result:" + result, this);
            }

            retVal = false;
        }
        return retVal;
    }

    /**
     * setRcptChkNumBk.
     * @param bizMsg
     */
    private void setRcptChkNumBk(NFDL0080CMsg bizMsg) {
        String rcptChkNum = bizMsg.rcptChkNum_H1.getValue();
        if (!ZYPCommonFunc.hasValue(rcptChkNum)) {
            rcptChkNum = ZYPCodeDataUtil.getVarCharConstValue(NFDL0080Constant.AR_RCPT_CONST_KEY_AR_PUR_CHK_NUM, bizMsg.glblCmpyCd_H1.getValue());
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.rcptChkNum_H1, rcptChkNum);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global area information
     * @return void
     */
    private void createArApplyIntfcWrk(NFDL0080CMsg bizMsg, NFDL0080SMsg globalMsg) {

        checkReceiptTimestamp(bizMsg);
        if ("E".equals(bizMsg.getMessageKind())) {
            return;
        }

        checkInvoiceEntryTimestamp(bizMsg, globalMsg);
        if ("E".equals(bizMsg.getMessageKind())) {
            return;
        }

        S21UserProfileService userProfileService = S21UserProfileServiceFactory.getInstance().getService();
        S21UserInfo userInfo = userProfileService.getContextUserInfo();
        String userId = userInfo.getUserId();
        String currentSystemTime = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS");

        int index = 1;

        AR_APPLY_INTFC_WRKTMsg inArApplyIntfcWrkMsg = new AR_APPLY_INTFC_WRKTMsg();

        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {

            if (!ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(i).xxChkBox_A1.getValue())) {
                continue;
            }

            inArApplyIntfcWrkMsg.clear();
            inArApplyIntfcWrkMsg.applyGrpKey.setValue(userId + currentSystemTime);
            inArApplyIntfcWrkMsg.applyGrpSubPk.setValue(index);
            inArApplyIntfcWrkMsg.dealSqDtlNum.setValue(ZYPCommonFunc.leftPad(String.valueOf(index), NFDL0080Constant.MAX_LENGTH_4, NFDL0080Constant.PAD_STR_0));
            inArApplyIntfcWrkMsg.usrId.setValue(userId);
            inArApplyIntfcWrkMsg.upldCsvRqstPk.setValue(BigDecimal.ZERO);
            inArApplyIntfcWrkMsg.payerCustCd.setValue(bizMsg.dsAcctNum_H1.getValue());
            inArApplyIntfcWrkMsg.rcptGlDt.setValue(bizMsg.glDt_H1.getValue());
            setCreateData(inArApplyIntfcWrkMsg, bizMsg, globalMsg.A.no(i));

            AR_APPLY_INTFC_WRKTMsg outArApplyIntfcWrkMsg = NFDL0080CommonLogic.createArApplyIntfcWrkInfo(inArApplyIntfcWrkMsg);

            if (RTNCD_NORMAL.equals(outArApplyIntfcWrkMsg.getReturnCode())) {
                globalMsg.A.no(i).applyGrpKey_A1.setValue(userId + currentSystemTime);
                index++;

            } else {
                EZDDebugOutput.println(1, "createArApplyIntfcWrk() Err:createArApplyIntfcWrkInfo", this);
                S21InfoLogOutput.println("NFDL0080 AR_APPLY_INTFC_WRK:" + inArApplyIntfcWrkMsg.toString());
                globalMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, "NFDM0008E");
                bizMsg.setMessageInfo("NFDM0008E", null);
                return;
            }

        }

        // START 2018/04/03 J.Kim [QC#25096,MOD]
        // BigDecimal onAcct = bizMsg.dealRmngBalGrsAmt_H1.getValue().add(bizMsg.xxTotAmt_H1.getValue());
        BigDecimal onAcct = bizMsg.xxTotAmt_H1.getValue();
        // END 2018/04/03 J.Kim [QC#25096,MOD]
        if (0 > onAcct.signum()) {
            inArApplyIntfcWrkMsg.clear();
            inArApplyIntfcWrkMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd_H1.getValue());
            inArApplyIntfcWrkMsg.applyGrpKey.setValue(userId + currentSystemTime);
            inArApplyIntfcWrkMsg.applyGrpSubPk.setValue(index);
            inArApplyIntfcWrkMsg.dealSqDtlNum.setValue(ZYPCommonFunc.leftPad(String.valueOf(index), NFDL0080Constant.MAX_LENGTH_4, NFDL0080Constant.PAD_STR_0));
            inArApplyIntfcWrkMsg.bizAppId.setValue(NFDL0080Constant.BIZ_APP_ID);
            inArApplyIntfcWrkMsg.procTpCd.setValue("1");
            inArApplyIntfcWrkMsg.dealSqNum.setValue("00000001");
            inArApplyIntfcWrkMsg.procStsCd.setValue(PROC_STS.IN_COMPLETED);
            inArApplyIntfcWrkMsg.rcptNum.setValue(bizMsg.arTrxNum_H1.getValue());
            inArApplyIntfcWrkMsg.rcptInProcSqPk.setValue(BigDecimal.ZERO);
            inArApplyIntfcWrkMsg.usrId.setValue(userId);
            inArApplyIntfcWrkMsg.upldCsvRqstPk.setValue(BigDecimal.ZERO);

            inArApplyIntfcWrkMsg.rcptGlDt.setValue(bizMsg.glDt_H1.getValue());
            if (ZYPCommonFunc.hasValue(bizMsg.rcptTrxBalPk_H1)) {
                inArApplyIntfcWrkMsg.rcptTrxBalPk.setValue(bizMsg.rcptTrxBalPk_H1.getValue());
            } else {
                inArApplyIntfcWrkMsg.rcptTrxBalPk.setValue(BigDecimal.ZERO);
            }

            inArApplyIntfcWrkMsg.rcptHdrLastUpdTs.setValue(bizMsg.rcptHdrLastUpdTs_H1.getValue());
            inArApplyIntfcWrkMsg.rcptHdrTz.setValue(bizMsg.rcptHdrTz_H1.getValue());
            inArApplyIntfcWrkMsg.rcptTrxBalLastUpdTs.setValue(bizMsg.rcptTrxBalLastUpdTs_H1.getValue());
            inArApplyIntfcWrkMsg.rcptTrxBalTz.setValue(bizMsg.rcptTrxBalTz_H1.getValue());
            inArApplyIntfcWrkMsg.grpInvFlg.setValue(ZYPConstant.FLG_OFF_N);
            inArApplyIntfcWrkMsg.dealCcyCd.setValue(bizMsg.ccyCd_H1.getValue());
            inArApplyIntfcWrkMsg.dealOnAcctCashAmt.setValue(onAcct.negate());
            inArApplyIntfcWrkMsg.invTrxBalPk.setValue(BigDecimal.ZERO);
            inArApplyIntfcWrkMsg.crTrxBalPk.setValue(BigDecimal.ZERO);
            inArApplyIntfcWrkMsg.dealApplyAmt.setValue(BigDecimal.ZERO);
            inArApplyIntfcWrkMsg.cashAppGlDt.setValue(bizMsg.glDt_H2.getValue());
            inArApplyIntfcWrkMsg.cashDiscPct.setValue(BigDecimal.ZERO);
            inArApplyIntfcWrkMsg.dealCashDiscAmt.setValue(BigDecimal.ZERO);
            inArApplyIntfcWrkMsg.dealApplyAdjAmt.setValue(BigDecimal.ZERO);
            inArApplyIntfcWrkMsg.dealApplyAdjRsvdAmt.setValue(BigDecimal.ZERO);

            inArApplyIntfcWrkMsg.payerCustCd.setValue(bizMsg.dsAcctNum_H1.getValue());
            inArApplyIntfcWrkMsg.rcptGlDt.setValue(bizMsg.glDt_H1.getValue());

            EZDDebugOutput.printLog(1, "NFDL0080 ENTRY AR_APPLY_INTFC_WRK:" + inArApplyIntfcWrkMsg.toString(), null);
            AR_APPLY_INTFC_WRKTMsg outArApplyIntfcWrkMsg = NFDL0080CommonLogic.createArApplyIntfcWrkInfo(inArApplyIntfcWrkMsg);

            if (!RTNCD_NORMAL.equals(outArApplyIntfcWrkMsg.getReturnCode())) {
                EZDDebugOutput.println(1, "createArApplyIntfcWrk() Err:createArApplyIntfcWrkInfo", this);
                S21InfoLogOutput.println("NFDL0080 ENTRY AR_APPLY_INTFC_WRK:" + inArApplyIntfcWrkMsg.toString());
                bizMsg.setMessageInfo("NFDM0008E", null);
                return;
            }
        }

        execBatch(bizMsg, globalMsg);

        // START 2020/01/31 [QC#54826, ADD]
        if ("E".equals(bizMsg.getMessageKind())) {
            return;
        }
        updatePostApplyNoteTxt(bizMsg, bizMsg.arTrxNum_H1.getValue());
        // END 2020/01/31 [QC#54826, ADD]
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed. TMsg
     * @param inAR_APPLY_INTFC_WRKMsg AR_APPLY_INTFC_WRKMsg
     * @param bizMsg Business Component Interface Message
     * @param aSMsg Global area information
     * @return void
     */
    private void setCreateData(AR_APPLY_INTFC_WRKTMsg inArApplyIntfcWrkMsg, NFDL0080CMsg bizMsg, NFDL0080_ASMsg aSMsg) {

        inArApplyIntfcWrkMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd_H1.getValue());
        inArApplyIntfcWrkMsg.bizAppId.setValue(NFDL0080Constant.BUSINESS_ID);
        inArApplyIntfcWrkMsg.procTpCd.setValue("1");
        inArApplyIntfcWrkMsg.dealSqNum.setValue("00000001");
        inArApplyIntfcWrkMsg.procStsCd.setValue(PROC_STS.IN_COMPLETED);
        inArApplyIntfcWrkMsg.rcptNum.setValue(bizMsg.rcptNum_H1.getValue());
        inArApplyIntfcWrkMsg.rcptInProcSqPk.setValue(BigDecimal.ZERO);

        if (ZYPCommonFunc.hasValue(bizMsg.rcptTrxBalPk_H1)) {
            inArApplyIntfcWrkMsg.rcptTrxBalPk.setValue(bizMsg.rcptTrxBalPk_H1.getValue());
        } else {
            inArApplyIntfcWrkMsg.rcptTrxBalPk.setValue(BigDecimal.ZERO);
        }

        inArApplyIntfcWrkMsg.rcptHdrLastUpdTs.setValue(bizMsg.rcptHdrLastUpdTs_H1.getValue());
        inArApplyIntfcWrkMsg.rcptHdrTz.setValue(bizMsg.rcptHdrTz_H1.getValue());
        inArApplyIntfcWrkMsg.rcptTrxBalLastUpdTs.setValue(bizMsg.rcptTrxBalLastUpdTs_H1.getValue());
        inArApplyIntfcWrkMsg.rcptTrxBalTz.setValue(bizMsg.rcptTrxBalTz_H1.getValue());
        inArApplyIntfcWrkMsg.grpInvFlg.setValue(ZYPConstant.FLG_OFF_N);

        // START 2017/09/13 J.Kim [QC#20514,DEL]
        // if (!AR_TRX_TP.DEDUCTION.equals(aSMsg.arTrxTpCd_A1.getValue())) {
        // END 2017/09/13 J.Kim [QC#20514,DEL]
        if (ZYPCommonFunc.hasValue(aSMsg.arTrxNum_A1)) {
            inArApplyIntfcWrkMsg.invNum.setValue(aSMsg.arTrxNum_A1.getValue());
        }
        if (ZYPCommonFunc.hasValue(aSMsg.arTrxTpCd_A1)) {
            inArApplyIntfcWrkMsg.arTrxTpCd.setValue(aSMsg.arTrxTpCd_A1.getValue());
        }
        if (ZYPCommonFunc.hasValue(aSMsg.invTrxBalPk_A1)) {
            inArApplyIntfcWrkMsg.invTrxBalPk.setValue(aSMsg.invTrxBalPk_A1.getValue());
        }
        if (ZYPCommonFunc.hasValue(aSMsg.invTrxBalLastUpdTs_A1)) {
            inArApplyIntfcWrkMsg.invTrxBalLastUpdTs.setValue(aSMsg.invTrxBalLastUpdTs_A1.getValue());
        }
        if (ZYPCommonFunc.hasValue(aSMsg.invTrxBalTz_A1)) {
            inArApplyIntfcWrkMsg.invTrxBalTz.setValue(aSMsg.invTrxBalTz_A1.getValue());
        }
        if (ZYPCommonFunc.hasValue(aSMsg.tocCd_A1)) {
            inArApplyIntfcWrkMsg.tocCd.setValue(aSMsg.tocCd_A1.getValue());
        }
        if (ZYPCommonFunc.hasValue(aSMsg.coaProdCd_A1)) {
            inArApplyIntfcWrkMsg.coaProdCd.setValue(aSMsg.coaProdCd_A1.getValue());
        }
        // START 2017/09/13 J.Kim [QC#20514,DEL]
        // }
        // END 2017/09/13 J.Kim [QC#20514,DEL]

        inArApplyIntfcWrkMsg.crTrxBalPk.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.dealCcyCd.setValue(bizMsg.ccyCd_H1.getValue());
        inArApplyIntfcWrkMsg.dealApplyAmt.setValue(aSMsg.xxDealApplyAmtNum_A1.getValue());
        // START 2018/05/11 [QC#25509, MOD]
        // inArApplyIntfcWrkMsg.cashAppGlDt.setValue(bizMsg.glDt_H1.getValue());
        inArApplyIntfcWrkMsg.cashAppGlDt.setValue(bizMsg.glDt_H2.getValue());
        // END   2018/05/11 [QC#25509, MOD]

        if (ZYPCommonFunc.hasValue(aSMsg.cashDiscPct_A1)) {
            inArApplyIntfcWrkMsg.cashDiscPct.setValue(aSMsg.cashDiscPct_A1.getValue());
        } else {
            inArApplyIntfcWrkMsg.cashDiscPct.setValue(BigDecimal.ZERO);
        }

        if (ZYPCommonFunc.hasValue(aSMsg.dealCashDiscAmt_A1)) {
            inArApplyIntfcWrkMsg.dealCashDiscAmt.setValue(aSMsg.dealCashDiscAmt_A1.getValue());
        } else {
            inArApplyIntfcWrkMsg.dealCashDiscAmt.setValue(BigDecimal.ZERO);
        }

        inArApplyIntfcWrkMsg.dealOnAcctCashAmt.setValue(BigDecimal.ZERO);

        if (ZYPCommonFunc.hasValue(aSMsg.arAdjTpCd_A1)) {
            inArApplyIntfcWrkMsg.arAdjTpCd.setValue(aSMsg.arAdjTpCd_A1.getValue());
        }

        inArApplyIntfcWrkMsg.dealApplyAdjAmt.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.dealApplyAdjRsvdAmt.setValue(BigDecimal.ZERO);

    }
    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global area information
     * @return void
     */
    private void execBatch(NFDL0080CMsg bizMsg, NFDL0080SMsg globalMsg) {

        NFZC301001 api = new NFZC301001();
        NFZC301001PMsg apiMsg = new NFZC301001PMsg();
        apiMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd_H1.getValue());

        String applyGrpKey = getApplyGrpKey(globalMsg);
        apiMsg.applyGrpKey.setValue(applyGrpKey);

        apiMsg.dealSqNum.setValue("00000001");
        apiMsg.batDt.setValue(ZYPDateUtil.getBatProcDate(bizMsg.glblCmpyCd_H1.getValue()));
        api.execute(apiMsg, ONBATCH_TYPE.ONLINE);

        String rCd = apiMsg.getReturnCode();

        if (NFDL0080Constant.APPLY_RTNCD_NORMAL.equals(rCd)) {
            // START 2018/07/13 Y.Matsui [QC#26993, ADD]
            if (!updateArBatRcptStatus(bizMsg)) {
                return;
            }
            // END   2018/07/13 Y.Matsui [QC#26993, ADD]

            bizMsg.setMessageInfo("NZZM0002I", new String[] {"Purge" });
        } else {

            // result == "0"
            if (NFDL0080Constant.APPLY_RTNCD_UN_PROCCES.equals(rCd)) {
                bizMsg.setMessageInfo("NFDM0007E", new String[] {"Unprocessing" });

                // result == "2"
            } else if (NFDL0080Constant.APPLY_RTNCD_CASHAPP_ERROR.equals(rCd)) {
                bizMsg.setMessageInfo("NFDM0007E", new String[] {"Cash Application Error" });

                // result == "3"
            } else if (NFDL0080Constant.APPLY_RTNCD_EXCLUSION_ERROR.equals(rCd)) {
                bizMsg.setMessageInfo("NFDM0007E", new String[] {"Exclusion Error" });

                // result == "4"
            } else if (NFDL0080Constant.APPLY_RTNCD_OTHERS_ERROR.equals(rCd)) {
                bizMsg.setMessageInfo("NFDM0007E", new String[] {"Others Error" });

            } else {
                bizMsg.setMessageInfo("NFDM0007E", new String[] {"Others Error" });
                EZDDebugOutput.println(1, "doApplyBatchAPI() result:" + rCd, this);
            }

            return;
        }

    }
    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param globalMsg Global area information
     * @return String
     */
    private String getApplyGrpKey(NFDL0080SMsg globalMsg) {
        String applyGrpKey = null;

        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {

            if (!ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(i).xxChkBox_A1.getValue())) {
                continue;
            }

            applyGrpKey = globalMsg.A.no(i).applyGrpKey_A1.getValue();
            break;
        }
        return applyGrpKey;
    }

    /**
     * getArTrxBalUpTime
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global area information
     * @return void
     */
    private void getArTrxBalUpTime(NFDL0080CMsg bizMsg, NFDL0080SMsg globalMsg) {
        S21SsmEZDResult s21SsmEZDResult = NFDL0080Query.getInstance().findHeaderInfo(bizMsg);
        if (!S21SsmEZDResult.RESULT_CODE_NORMAL.equals(s21SsmEZDResult.getResultCode())) {
            bizMsg.setMessageInfo("ZZZM9001E");
            return;
        }

        Map<String, Object> resMap = (Map<String, Object>) s21SsmEZDResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime_H1, (String) resMap.get("EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone_H1, (String) resMap.get("EZUPTIMEZONE"));
    }

    /**
     * <dd>The method explanation: The business peculiarity processing
     * is executed.
     * @param bizMsg Business Component Interface Message
     * @return void
     */
    private void checkReceiptTimestamp(NFDL0080CMsg bizMsg) {

        AR_RCPTTMsg inArRcptMsg = new AR_RCPTTMsg();
        inArRcptMsg.rcptNum.setValue(bizMsg.arTrxNum_H1.getValue());
        AR_RCPTTMsg outArRcptMsg = NFDL0080CommonLogic.findArRcptInfo(bizMsg, inArRcptMsg);

        if (null == outArRcptMsg || !RTNCD_NORMAL.equals(outArRcptMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0080E", null);
            return;
        }

        if (!ZYPDateUtil.isSameTimeStamp(bizMsg.rcptHdrLastUpdTs_H1.getValue(), bizMsg.rcptHdrTz_H1.getValue(), outArRcptMsg.ezUpTime.getValue(), outArRcptMsg.ezUpTimeZone.getValue())) {
            bizMsg.setMessageInfo("NFCM0080E", null);
            return;
        }

        checkArTrxBalTimestamp(bizMsg, "NFCM0080E");
    }

    /**
     * <dd>The method explanation: The business peculiarity processing
     * is executed.
     * @param bizMsg Business Component Interface Message
     * @return void
     */
    private void checkArTrxBalTimestamp(NFDL0080CMsg bizMsg, String msgId) {
        AR_TRX_BALTMsg inArTrxBalMsg = new AR_TRX_BALTMsg();
        inArTrxBalMsg.arTrxBalPk.setValue(bizMsg.arTrxBalPk_H1.getValue());

        AR_TRX_BALTMsg outArTrxBalMsg = NFDL0080CommonLogic.findArTrxBalInfo(bizMsg, inArTrxBalMsg);

        if (null == outArTrxBalMsg || !RTNCD_NORMAL.equals(outArTrxBalMsg.getReturnCode())) {
            bizMsg.setMessageInfo(msgId, null);
            return;
        }

        if (!ZYPDateUtil.isSameTimeStamp(bizMsg.ezUpTime_H1.getValue(), bizMsg.ezUpTimeZone_H1.getValue(), outArTrxBalMsg.ezUpTime.getValue(), outArTrxBalMsg.ezUpTimeZone.getValue())) {
            bizMsg.setMessageInfo(msgId, null);
            return;
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity processing
     * is executed.
     * @param bizMsg Business Component Interface Message
     * @return void
     */
    private void checkArTrxBalTimestamp(NFDL0080CMsg bizMsg, NFDL0080_ASMsg aSMsg) {
        AR_TRX_BALTMsg inArTrxBalMsg = new AR_TRX_BALTMsg();
        inArTrxBalMsg.arTrxBalPk.setValue(aSMsg.invTrxBalPk_A1.getValue());

        AR_TRX_BALTMsg outArTrxBalMsg = NFDL0080CommonLogic.findArTrxBalInfo(bizMsg, inArTrxBalMsg);

        if (null == outArTrxBalMsg || !RTNCD_NORMAL.equals(outArTrxBalMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0079E", null);
            return;
        }

        if (!ZYPDateUtil.isSameTimeStamp(aSMsg.invTrxBalLastUpdTs_A1.getValue(), aSMsg.invTrxBalTz_A1.getValue(), outArTrxBalMsg.ezUpTime.getValue(), outArTrxBalMsg.ezUpTimeZone.getValue())) {
            bizMsg.setMessageInfo("NFCM0079E", null);
            return;
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity processing
     * is executed.
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global area information
     * @return void
     */
    private void checkInvoiceEntryTimestamp(NFDL0080CMsg bizMsg, NFDL0080SMsg globalMsg) {

        AR_TRX_BALTMsg inArTrxBalMsg = new AR_TRX_BALTMsg();
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {

            if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).arTrxNum_A1)) {

                inArTrxBalMsg.clear();
                inArTrxBalMsg.arTrxBalPk.setValue(globalMsg.A.no(i).invTrxBalPk_A1.getValue());

                AR_TRX_BALTMsg outArTrxBalMsg = NFDL0080CommonLogic.findArTrxBalInfo(bizMsg, inArTrxBalMsg);

                if (null == outArTrxBalMsg || !RTNCD_NORMAL.equals(outArTrxBalMsg.getReturnCode())) {
                    bizMsg.setMessageInfo("NFCM0079E", null);
                    return;
                }

                if (!ZYPDateUtil.isSameTimeStamp(globalMsg.A.no(i).invTrxBalLastUpdTs_A1.getValue(), globalMsg.A.no(i).invTrxBalTz_A1.getValue(), outArTrxBalMsg.ezUpTime.getValue(), outArTrxBalMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo("NFCM0079E", null);
                    return;
                }
            }
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity processing
     * is executed.
     * @param bizMsg NFDL0080CMsg
     * @param billToCustCd String
     * @param btPrDt String
     * @return boolean
     */
    private boolean doCreditProfileUpdateAPI(NFDL0080CMsg bizMsg, String billToCustCd, String btPrDt) {

        boolean retVal = true;

        NFZC202001PMsg apiMsg = new NFZC202001PMsg();
        apiMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd_H1.getValue());
        apiMsg.xxModeCd.setValue(NFZC202001.MODE_BILL_TO_CUST);
        apiMsg.sellToCustCd.clear();
        apiMsg.billToCustCd.setValue(billToCustCd);
        apiMsg.procDt.setValue(btPrDt);

        NFZC202001 api = new NFZC202001();
        api.execute(apiMsg, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(apiMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(apiMsg);
            S21ApiMessage msg = msgList.get(0);
            bizMsg.setMessageInfo("NFDM0017E", new String[] {S21MessageFunc.clspGetMessage(msg.getXxMsgid(), msg.getXxMsgPrmArray()) });
            retVal = false;
        }
        return retVal;
    }

    // START 2018/04/19 [QC#20940, ADD]
    private void createCltNoteDtl(NFDL0080CMsg bizMsg, NFDL0080SMsg globalMsg) {
        CLT_NOTE_TPTMsg cltNoteTp = new CLT_NOTE_TPTMsg();
        ZYPEZDItemValueSetter.setValue(cltNoteTp.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(cltNoteTp.cltNoteTpCd, CLT_NOTE_TP.POST_APPLICATION);
        cltNoteTp = (CLT_NOTE_TPTMsg) S21CodeTableAccessor.findByKey(cltNoteTp);

        int dataCnt = 0;
        StringBuilder invoiceNumbers = new StringBuilder();
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            if (!ZYPConstant.CHKBOX_ON_Y.equals(globalMsg.A.no(i).xxChkBox_A1.getValue())) {
                continue;
            }
            if (dataCnt > 0) {
                invoiceNumbers.append(",");
            }
            // START 2018/07/11 [QC#26989, MOD]
            // invoiceNumbers.append(globalMsg.A.no(i).arTrxNum_A1.getValue());
            invoiceNumbers.append(globalMsg.A.no(i).arCustRefNum_A1.getValue());
            // END   2018/07/11 [QC#26989, MOD]
            dataCnt++;
        }

        String appliedBy = getContextUserInfo().getFirstName() + " " + getContextUserInfo().getLastName();

        String dtlNoteTxtTmpl = ZYPCodeDataUtil.getVarCharConstValue(NFDL0080Constant.NFDL0080_CLT_DTL_NOTE_TXT, getGlobalCompanyCode());
        // START 2018/07/11 [QC#26989, MOD]
        // String dtlNoteTxt = String.format(dtlNoteTxtTmpl,
        //         bizMsg.dtlNoteTxt_H1.getValue(), bizMsg.arTrxNum_H1.getValue(), bizMsg.dealRmngBalGrsAmt_H1.getValue(), bizMsg.xxTotAmt_H1.getValue(),
        //         invoiceNumbers.toString(), appliedBy);
        String dtlNoteTxt = String.format(dtlNoteTxtTmpl,
                bizMsg.dtlNoteTxt_H1.getValue(), bizMsg.arCustRefNum_H1.getValue(), bizMsg.dealRmngBalGrsAmt_H1.getValue(), bizMsg.xxTotAmt_H1.getValue(),
                invoiceNumbers.toString(), appliedBy);
        // END   2018/07/11 [QC#26989, MOD]
        if (dtlNoteTxt.length() > NFDL0080Constant.MAX_LENGTH_CLT_DTL_NOTE_TXT) {
            dtlNoteTxt = dtlNoteTxt.substring(0, NFDL0080Constant.MAX_LENGTH_CLT_DTL_NOTE_TXT);
        }

        CLT_NOTE_DTLTMsg cltNoteDtlMsg = new CLT_NOTE_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.cltNoteDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal("CLT_NOTE_DTL_SQ"));
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.cltAcctCd, bizMsg.dsAcctNum_H1);
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.cltAcctTpCd, CLT_ACCT_TP.BILL_TO);
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.cratDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.cratTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.cratUsrId, getContextUserInfo().getUserId());
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.colNoteSubjTxt, cltNoteTp.cltNoteTpDescTxt);
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.colNoteTpCd, cltNoteTp.cltNoteTpCd);
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.dtlNoteTxt, dtlNoteTxt);

        EZDTBLAccessor.create(cltNoteDtlMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cltNoteDtlMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFDM0013E", new String[] {"CLT_NOTE_DTL" });
            return;
        }
    }
    // END   2018/04/19 [QC#20940, ADD]

    // START 2018/07/13 Y.Matsui [QC#26993, MOD]
    private boolean updateArBatRcptStatus(NFDL0080CMsg bizMsg) {
        AR_RCPTTMsg criteria = new AR_RCPTTMsg();
        criteria.glblCmpyCd.setValue(getGlobalCompanyCode());
        criteria.rcptNum.setValue(bizMsg.arTrxNum_H1.getValue());
        AR_RCPTTMsg arRcpt = (AR_RCPTTMsg) S21FastTBLAccessor.findByKey(criteria);

        if (arRcpt == null || !ZYPCommonFunc.hasValue(arRcpt.arBatRcptNm)) {
            return true;
        }

        NFZC310001PMsg pMsg = new NFZC310001PMsg();
        pMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        pMsg.arBatRcptNm.setValue(arRcpt.arBatRcptNm.getValue());
        new NFZC310001().execute(pMsg, ONBATCH_TYPE.ONLINE);
        List<S21ApiMessage> xxMsgList = S21ApiUtil.getXxMsgList(pMsg);
        if (!xxMsgList.isEmpty()) {
            for (S21ApiMessage xxMsg : xxMsgList) {
                bizMsg.setMessageInfo(xxMsg.getXxMsgid(), xxMsg.getXxMsgPrmArray());
                return false;
            }
        }
        return true;
    }
    // END 2018/07/13 Y.Matsui [QC#26993, MOD]

    // START 2020/01/31 [QC#54826, ADD]
    private void updatePostApplyNoteTxt(NFDL0080CMsg bizMsg, String rcptNum) {
        AR_RCPTTMsg criteria = new AR_RCPTTMsg();
        criteria.glblCmpyCd.setValue(getGlobalCompanyCode());
        criteria.rcptNum.setValue(rcptNum);
        AR_RCPTTMsg arRcpt = (AR_RCPTTMsg) S21FastTBLAccessor.findByKey(criteria);
        if (arRcpt != null) {
            arRcpt.arRcptMiscCmntTxt.setValue(appendPostApplyNoteTxt(arRcpt.arRcptMiscCmntTxt.getValue()));
            S21FastTBLAccessor.update(arRcpt);
        }
    }

    private String appendPostApplyNoteTxt(String existingNoteTxt) {
        String noteTxtTmpl = ZYPCodeDataUtil.getVarCharConstValue(NFDL0080Constant.NFDL0080_AR_RCPT_MISC_CMNT_TXT, getGlobalCompanyCode());
        if (!ZYPCommonFunc.hasValue(noteTxtTmpl)) {
            return existingNoteTxt;
        }
        String appliedDt = ZYPDateUtil.getCurrentSystemTime("MMddyy");
        String appliedBy = getContextUserInfo().getFirstName() + " " + getContextUserInfo().getLastName();
        StringBuilder noteTxt = new StringBuilder(String.format(noteTxtTmpl, appliedDt, appliedBy));
        if (ZYPCommonFunc.hasValue(existingNoteTxt)) {
            noteTxt.append(", ");
            noteTxt.append(existingNoteTxt);
        }
        return S21StringUtil.subStringByLength(noteTxt.toString(), 0, NFDL0080Constant.MAX_LENGTH_AR_RCPT_MISC_CMNT_TXT);
    }
    // END 2020/01/31 [QC#54826, ADD]

    // START 2022/03/22 K.Suzuki [QC#59660-3,ADD]
    /**
     * checkOnAccountStatus
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global area information
     * @return void
     */
    private void checkOnAccountStatusPartial(NFDL0080CMsg bizMsg, NFDL0080SMsg globalMsg) {

        AR_TRX_BALTMsg inArTrxBalMsg = new AR_TRX_BALTMsg();
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.glblCmpyCd, bizMsg.glblCmpyCd_H1);
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.arTrxBalPk, bizMsg.arTrxBalPk_H1);
        AR_TRX_BALTMsg outArTrxBalMsg = (AR_TRX_BALTMsg) EZDTBLAccessor.findByKey(inArTrxBalMsg);

        if (AR_CASH_APPLY_STS.PARTIAL.equals(outArTrxBalMsg.arCashApplyStsCd.getValue())) {
            bizMsg.setMessageInfo("NFDM0059E", null);
        }
    }
    // END   2022/03/22 K.Suzuki [QC#59660-3,ADD]
}
