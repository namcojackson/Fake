
package business.blap.NFCL2760;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFCL2760.NFCL2760CMsg;
import business.blap.NFCL2760.NFCL2760Query;
import business.blap.NFCL2760.NFCL2760SMsg;
import business.blap.NFCL2760.NFCL2760_ASMsg;
import business.blap.NFCL2760.common.NFCL2760CommonLogic;
import business.blap.NFCL2760.constant.NFCL2760Constant;
import business.db.AR_ACCT_DTTMsg;
import business.db.AR_ADJ_COA_INFOTMsg;
import business.db.AR_ADJ_TPTMsgArray;
import business.db.AR_APPLY_INTFC_BATTMsg;
import business.db.AR_APPLY_INTFC_WRKTMsg;
import business.db.AR_CASH_APPTMsg;
import business.db.AR_CASH_APPTMsgArray;
import business.db.AR_RCPTTMsg;
import business.db.AR_TRX_BALTMsg;
import business.parts.NFZC202001PMsg;
import business.parts.NFZC301001PMsg;
import business.parts.NFZC310001PMsg;

import com.canon.cusa.s21.api.NFC.NFZC202001.NFZC202001;
import com.canon.cusa.s21.api.NFC.NFZC301001.NFZC301001;
import com.canon.cusa.s21.api.NFZ.NFZC310001.NFZC310001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_ADJ_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_APPLY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/13   Hitachi         K.Kojima        Update          QC#6465
 * 2016/09/07   Hitachi         J.Kim           Update          QC#13793
 * 2016/12/26   Fujitsu         S.Fujita        Update          QC#16335
 * 2017/12/07   Fujitsu         M.Ohno          Update          QC#21402-2
 * 2018/01/18   Fujitsu         H.Ikeda         Update          QC#23136
 * 2018/02/13   Fujitsu         H.Ikeda         Update          QC#20435
 * 2018/03/26   Hitachi         J.Kim           Update          QC#25017
 * 2018/05/11   Fujitsu         Y.Matsui        Update          QC#25509
 * 2018/06/21   Fujitsu         Y.Matsui        Update          QC#25862
 * 2018/07/13   Fujitsu         Y.Matsui        Update          QC#26993
 * 2018/07/19   Fujitsu         Y.Matsui        Update          QC#26871
 * 2018/07/20   Fujitsu         Y.Matsui        Update          QC#26985
 * 2018/08/22   Fujitsu         Y.Matsui        Update          QC#26604
 * 2018/10/09   Fujitsu         T.Ogura         Update          QC#28166
 * 2018/10/10   Fujitsu         T.Ogura         Update          QC#28167
 * 2018/10/11   Fujitsu         T.Ogura         Update          QC#28169
 * 2018/10/24   Fujitsu         T.Ogura         Update          QC#28168-1
 * 2019/09/02   Fujitsu         H.Ikeda         Update          QC#53152
 * 2021/06/24   CITS            D.Morimoto      Update          QC#57729
 * 2022/04/22   CITS            D.Mamaril       Update          QC#59333
 * 2022/11/30	Hitachi			R.Takau			Update			QC#57252
 * </pre>
 */
public class NFCL2760BL06 extends S21BusinessHandler implements NFCL2760Constant {

	private boolean blIni = false;

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        //cMsg.setCommitSMsg(true);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NFCL2760Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFCL2760Scrn00_CMN_Submit(cMsg, sMsg);
            } else if ("NFCL2760Scrn00_CMN_Return".equals(screenAplID)) {
                doProcess_NFCL2760Scrn00_CMN_Return(cMsg, sMsg);

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
     * 
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL2760Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(1, "doProcess_NFCL2760Scrn00_CMN_Submit================================START", this);

        NFCL2760CMsg bizMsg = (NFCL2760CMsg) cMsg;
        NFCL2760SMsg globalMsg = (NFCL2760SMsg) sMsg;
        bizMsg.setCommitSMsg(true);

        int pagenationFrom = NFCL2760CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());

        if (pagenationFrom > -1) {
            NFCL2760CommonLogic.setBizMsgToGlobalMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);
            // All>>
            if (!SCRN_MODE_CANCEL.equals(bizMsg.xxModeInd_H1.getValue())) {
                boolean ret = NFCL2760CommonLogic.setArTrxAllLine(bizMsg, globalMsg);
                if (!ret) {
                    NFCL2760CommonLogic.setGlobalMsgToBizMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);
                    return;
                }
            }
        }

        NFCL2760CommonLogic.setCurrentPageOut(bizMsg, globalMsg, pagenationFrom);
        if (!NFCL2760CommonLogic.isCancelBalanceZero(bizMsg, globalMsg)) {
            bizMsg.setMessageInfo("NFCM0842E");
            return;
        }

        // 2021/06/24 QC#57729 Add START
        NFCL2760CommonLogic.setCalc(bizMsg, globalMsg);
        if (NFCL2760CommonLogic.isOffset(bizMsg)) {
            if (BigDecimal.ZERO.compareTo(bizMsg.xxTotAmt_H1.getValue()) != 0) {
                bizMsg.setMessageInfo("NFCM0917E");
                return;
            }
        }
        // 2021/06/24 QC#57729 Add END

        //Check Submit
        //int iChkPage = NFCL2760CommonLogic.chkSubmitCommon(bizMsg, globalMsg, getGlobalCompanyCode());
        //if ( iChkPage > 0 ) {
        //    bizMsg.xxPageShowFromNum.setValue((iChkPage-1)*bizMsg.A.length());
        //    changePageforError( bizMsg, globalMsg );
        //    return;
        //}

        //Process Counts
        int iCancelCnt = 0;
        int iEntryCnt = 0;
        
        for (int iCnt=0; iCnt<globalMsg.A.getValidCount(); iCnt++) {

            // START 2018/10/24 T.Ogura [QC#28168-1,ADD]
            if (!ZYPCommonFunc.hasValue(globalMsg.A.no(iCnt).arCustRefNum) && !ZYPCommonFunc.hasValue(globalMsg.A.no(iCnt).arTrxTpCd) && ZYPConstant.CHKBOX_ON_Y.equals(globalMsg.A.no(iCnt).xxChkBox.getValue())){
                globalMsg.A.no(iCnt).xxChkBox.clear();
            }
            // END   2018/10/24 T.Ogura [QC#28168-1,ADD]

            if ( ROW_STS.CANCEL.toString().equals(NFCL2760CommonLogic.isStatusOfRow(globalMsg.A.no(iCnt).xxChkBox.getValue(), globalMsg.A.no(iCnt).xxArCashApplyStsTxt.getValue())) ) {
                // Cancel
                iCancelCnt ++;
            } else if ( ROW_STS.CASH_APPLICATION.toString().equals(NFCL2760CommonLogic.isStatusOfRow(globalMsg.A.no(iCnt).xxChkBox.getValue(), globalMsg.A.no(iCnt).xxArCashApplyStsTxt.getValue())) ) {
                iEntryCnt ++;
            }

            // START 2018/10/24 T.Ogura [QC#28168-1,DEL]
//            // 2018/01/18 QC#23136 mod start
//            // 2017/12/07 QC#21402-2 add start
//            //if (!ZYPCommonFunc.hasValue(globalMsg.A.no(iCnt).arCustRefNum) && ZYPConstant.CHKBOX_ON_Y.equals(globalMsg.A.no(iCnt).xxChkBox.getValue())){
//            if (!ZYPCommonFunc.hasValue(globalMsg.A.no(iCnt).arCustRefNum) && !ZYPCommonFunc.hasValue(globalMsg.A.no(iCnt).arTrxTpCd) && ZYPConstant.CHKBOX_ON_Y.equals(globalMsg.A.no(iCnt).xxChkBox.getValue())){
//                // 2018/01/18 QC#23136 mod end
//                globalMsg.A.no(iCnt).xxChkBox.clear();
//            }
//            // 2017/12/07 QC#21402-2 add end
            // END   2018/10/24 T.Ogura [QC#28168-1,DEL]
        }


        // Cancel Process
        if (iCancelCnt > 0) {
            bizMsg.xxModeInd_H1.setValue(MODE_CANCEL);
            execCancelCash(bizMsg, globalMsg);

            if (!"E".equals(bizMsg.getMessageKind())) {
                 bizMsg.xxRsltStsCd_H1.setValue(SCRN_STATUS_N);
                 bizMsg.A.no(0).applyGrpKey_BK.setValue(globalMsg.A.no(0).applyGrpKey_BK.getValue());
                 //bizMsg.dealRcptRmngBalAmt.setValue(bizMsg.xxBalApplyGrsAmt.getValue());
                 S21UserInfo userInfo = getContextUserInfo();
                 NFCL2760CommonLogic.endCustCdUnlock(bizMsg, userInfo);
                 //NFCL2760CommonLogic.delCancelRecords(globalMsg);
                 NFCL2760CommonLogic.reSetGlobalMsgToBizMsg(globalMsg, bizMsg);
            } else {
                 bizMsg.xxRsltStsCd_H1.setValue(SCRN_STATUS_Y);
            }
        }

        // START 2018/10/11 T.Ogura [QC#28169,DEL]
//        //Commit;
//        EZDConnectionMgr.getInstance().commit();
        // END   2018/10/11 T.Ogura [QC#28169,DEL]

        if (!checkReceiptNumber(bizMsg)) {
            bizMsg.xxRsltStsCd_H1.setValue(SCRN_STATUS_N);
            return;
        } else {
            // do nothing
        }

        // Receipt Transaction Type Name
        bizMsg.arRcptTrxTpNm.setValue(ZYPCodeDataUtil.getName(AR_RCPT_TRX_TP_PRM_NM, getGlobalCompanyCode(), bizMsg.arRcptTrxTpCd.getValue()));

        // Receipt Type Name
        bizMsg.arRcptTpNm.setValue(ZYPCodeDataUtil.getName(AR_RCPT_TP_PRM_NM, getGlobalCompanyCode(), bizMsg.arRcptTpCd.getValue()));

        // Payer Name
        //NFCL2760CommonLogic.findBillToCustList(bizMsg, new BILL_TO_CUSTTMsg());
        NFCL2760CommonLogic.setDsAcctNm(bizMsg);
        
        Map<String, String> inMap = new HashMap<String, String>();
        inMap.put("GLBL_CMPY_CD", bizMsg.glblCmpyCd_H1.getValue());
        inMap.put("AR_CASH_APP_MAN_ENTRY_TP_CD", "2");
        AR_ADJ_TPTMsgArray outArAdjTPMsg = (AR_ADJ_TPTMsgArray) ZYPCodeDataUtil.findByCondition(AR_ADJ_TP_PRM_NM, inMap);

        Map<String, String> tMsgKeys = new HashMap<String, String>();
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "arAdjTpCd");
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "arAdjTpCd");
        ZYPPulldownValueSetter.set(outArAdjTPMsg, tMsgKeys, bizMsg.arAdjTpCd_B1, bizMsg.arAdjTpCd_B2);

            // EntryCash Process
        if (iEntryCnt > 0) {
            bizMsg.xxModeInd_H1.setValue(MODE_ENTRY);
            execEntryCash(bizMsg, globalMsg);

            if (!"E".equals(bizMsg.getMessageKind())) {
                bizMsg.xxRsltStsCd_H1.setValue(SCRN_STATUS_N);
                bizMsg.A.no(0).applyGrpKey_BK.setValue(globalMsg.A.no(0).applyGrpKey_BK.getValue());
                S21UserInfo userInfo = getContextUserInfo();
                NFCL2760CommonLogic.endCustCdUnlock(bizMsg, userInfo);
                NFCL2760CommonLogic.reSetGlobalMsgToBizMsg(globalMsg, bizMsg);
            } else {
                bizMsg.xxRsltStsCd_H1.setValue(SCRN_STATUS_Y);
            }
         }

        if (!"E".equals(bizMsg.getMessageKind())) {
            bizMsg.xxModeInd_H1.setValue(MODE_ONE);
        }
        
        //Search the Transaction for Init
        
        EZDDebugOutput.println(1, "doProcess_NFCL2760Scrn00_CMN_Submit================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL2760Scrn00_CMN_Return(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL2760Scrn00_CMN_Return================================START", this);

        NFCL2760CMsg bizMsg = (NFCL2760CMsg) cMsg;
        NFCL2760SMsg globalMsg = (NFCL2760SMsg) sMsg;
        globalMsg.clear();
        ZYPTableUtil.clear(globalMsg.A);
        ZYPTableUtil.clear(globalMsg.B);
        ZYPTableUtil.clear(globalMsg.C);
        bizMsg.setCommitSMsg(true);

        S21UserInfo userInfo = getContextUserInfo();
        NFCL2760CommonLogic.endCustCdUnlock(bizMsg, userInfo);

        EZDDebugOutput.println(1, "doProcess_NFCL2760Scrn00_CMN_Return================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global area information
     * @return void
     */
    private void execEntryCash(NFCL2760CMsg bizMsg, NFCL2760SMsg globalMsg) {

        if (null == bizMsg.A || 0 == bizMsg.A.getValidCount()) {
            bizMsg.setMessageInfo("NFCM0149E", null);
            int pagenationFrom = NFCL2760CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
            NFCL2760CommonLogic.setGlobalMsgToBizMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);
            return;
        }


        AR_ACCT_DTTMsg outArAcctDtMsg = NFCL2760CommonLogic.findArAcctDtInfo(bizMsg, new AR_ACCT_DTTMsg());

        if (null == outArAcctDtMsg || !RTNCD_NORMAL.equals(outArAcctDtMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0041E", null);
            int pagenationFrom = NFCL2760CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
            NFCL2760CommonLogic.setGlobalMsgToBizMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);
            return;
        }
        bizMsg.acctDt.setValue(outArAcctDtMsg.acctDt.getValue());

        checkDepositDateAndTrxDate(bizMsg, globalMsg);
        if ("E".equals(bizMsg.getMessageKind())) {
            for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(i).xxPgFlg_A1.getValue())) {
                    continue;
                }
                if (globalMsg.A.no(i).arCustRefNum.isError()) {
                    NFCL2760CommonLogic.setPageData(globalMsg, bizMsg, i + 1);
                    break;
                }
            }
            return;
        } else {
            // do nothing.
        }

        checkEntryCash(bizMsg, globalMsg);
        if ("E".equals(bizMsg.getMessageKind())) {
            for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(i).xxPgFlg_A1.getValue())) {
                    continue;
                }
                if (globalMsg.A.no(i).cashAppGlDt_A1.isError()) {
                    NFCL2760CommonLogic.setPageData(globalMsg, bizMsg, i + 1);
                    break;
                }
            }
            return;
        } else {
            // do nothing.
        }

        // START 2018/10/09 T.Ogura [QC#28166,ADD]
        NFCL2760CommonLogic.checkApplyingRefund(bizMsg, globalMsg);
        if ("E".equals(bizMsg.getMessageKind())) {
            for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(i).xxPgFlg_A1.getValue())) {
                    continue;
                }
                if (globalMsg.A.no(i).arCustRefNum.isError()) {
                    NFCL2760CommonLogic.setPageData(globalMsg, bizMsg, i + 1);
                    break;
                }
            }
            return;
        } else {
            // do nothing.
        }
        // END   2018/10/09 T.Ogura [QC#28166,ADD]

        // START 2016/07/13 K.Kojima [QC#6465,ADD]
        NFCL2760CommonLogic.chckAdjApvlLimit(bizMsg, globalMsg, getContextUserInfo().getUserId());
        if ("E".equals(bizMsg.getMessageKind())) {
            for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(i).xxPgFlg_A1.getValue())) {
                    continue;
                }
                if (globalMsg.A.no(i).xxDealApplyAmtNum_A1.isError()) {
                    NFCL2760CommonLogic.setPageData(globalMsg, bizMsg, i + 1);
                    break;
                }
            }
            return;
        } else {
            // do nothing.
        }
        // END 2016/07/13 K.Kojima [QC#6465,ADD]

        NFCL2760CommonLogic.checkAdjTypeAndAdjAmtSelectOfAdjType(globalMsg, bizMsg);
        if ("E".equals(bizMsg.getMessageKind())) {
            for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(i).xxPgFlg_A1.getValue())) {
                    continue;
                }
                if (globalMsg.A.no(i).xxDealApplyAdjAmtNum_A1.isError()) {
                    NFCL2760CommonLogic.setPageData(globalMsg, bizMsg, i + 1);
                    break;
                }
            }
            return;
        } else {
            // do nothing.
        }

        NFCL2760CommonLogic.setApplyGrsAmt(globalMsg, bizMsg, blIni, ROW_STS.CASH_APPLICATION.toString());
        if ("E".equals(bizMsg.getMessageKind())) {
            int pagenationFrom = NFCL2760CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
            //NFCL2760CommonLogic.setGlobalMsgToBizMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);
            return;
        } else {
            // do nothing.
        }

        // START 2018/08/22 Y.Matsui [QC#26604,MOD]
        if (BigDecimal.ZERO.compareTo(bizMsg.dealRcptAmt.getValue()) != 0) {
            // START 2016/09/05 J.Kim [QC#13880,MOD]
            if (BigDecimal.ZERO.compareTo(bizMsg.applyAmt_1.getValue()) == 0 && BigDecimal.ZERO.compareTo(bizMsg.xxOnAcctAmt.getValue()) == 0 && BigDecimal.ZERO.compareTo(bizMsg.xxDedctAmt.getValue()) == 0
                    && BigDecimal.ZERO.compareTo(bizMsg.xxApplyGrsAmt.getValue()) == 0) {
                // END 2016/09/05 J.Kim [QC#13880,MOD]
                bizMsg.setMessageInfo("NFCM0149E", null);
                int pagenationFrom = NFCL2760CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
                //NFCL2760CommonLogic.setGlobalMsgToBizMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);
                return;
            }
        }
        // END 2018/08/22 Y.Matsui [QC#26604,MOD]

        if (NFCL2760CommonLogic.isTotalAmtHasToBeZeroMode(bizMsg)) {

            if (BigDecimal.ZERO.compareTo(bizMsg.xxBalApplyGrsAmt.getValue()) != 0) {
                bizMsg.setMessageInfo("NFCM0136E");
                int pagenationFrom = NFCL2760CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
                //NFCL2760CommonLogic.setGlobalMsgToBizMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);
                return;
            } else {
                // do nothing.
            }
        } else {
            boolean rcptMinusFlg;
            if (bizMsg.dealRcptAmt.getValue().compareTo(BigDecimal.ZERO) > 0) {
                rcptMinusFlg = false;
            } else {
                rcptMinusFlg = true;
            }

            if (rcptMinusFlg) {
                // If rcptAmt is Minus, then total amt has to be less
                // than 0.
                if (BigDecimal.ZERO.compareTo(bizMsg.xxBalApplyGrsAmt.getValue()) < 0) {
                    bizMsg.setMessageInfo("NFCM0198E");
                    int pagenationFrom = NFCL2760CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
                    //NFCL2760CommonLogic.setGlobalMsgToBizMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);
                    return;
                }

                // START 2016/12/26 S.Fujita [QC#16335,ADD]
                if (bizMsg.dealRcptAmt.getValue().compareTo(bizMsg.xxTotAmt_H1.getValue()) < 0) {
                    bizMsg.setMessageInfo("NFCM0874E");
                    int pagenationFrom = NFCL2760CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
                    return;
                }
                // END   2016/12/26 S.Fujita [QC#16335,ADD]

            } else {
                // If rcptAmt is Plus, then total amt has to be more
                // than 0.
                if (BigDecimal.ZERO.compareTo(bizMsg.xxBalApplyGrsAmt.getValue()) > 0) {
                    bizMsg.setMessageInfo("NFCM0199E");
                    int pagenationFrom = NFCL2760CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
                    //NFCL2760CommonLogic.setGlobalMsgToBizMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);
                    return;
                }

                // START 2016/12/26 S.Fujita [QC#16335,ADD]
                if (bizMsg.dealRcptAmt.getValue().compareTo(bizMsg.xxTotAmt_H1.getValue()) < 0) {
                    bizMsg.setMessageInfo("NFCM0874E");
                    int pagenationFrom = NFCL2760CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
                    return;
                }
                // END   2016/12/26 S.Fujita [QC#16335,ADD]
            }
        }

        BigDecimal rcpt = bizMsg.dealRcptAmt.getValue();
        BigDecimal rcptBalAmtapply = bizMsg.xxBalApplyGrsAmt.getValue();
        String exptFlg = bizMsg.exptFlg_H1.getValue();

        if (ZYPConstant.FLG_ON_Y.equals(exptFlg)) {
            if (rcpt.signum() < 0) {
                if (rcpt.compareTo(rcptBalAmtapply) > 0 || BigDecimal.ZERO.compareTo(rcptBalAmtapply) < 0) {
                    bizMsg.setMessageInfo("NFCM0089E", null);
                    int pagenationFrom = NFCL2760CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
                    //NFCL2760CommonLogic.setGlobalMsgToBizMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);
                    return;
                }
            } else {
                if (rcpt.compareTo(rcptBalAmtapply) < 0 || BigDecimal.ZERO.compareTo(rcptBalAmtapply) > 0) {
                    bizMsg.setMessageInfo("NFCM0089E", null);
                    int pagenationFrom = NFCL2760CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
                    //NFCL2760CommonLogic.setGlobalMsgToBizMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);
                    return;
                }
            }
        }

        //checkReceiptTimestamp(bizMsg);
        //if ("E".equals(bizMsg.getMessageKind())) {
        //    int pagenationFrom = NFCL2760CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
        //    NFCL2760CommonLogic.setGlobalMsgToBizMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);
        //    return;
        //} else {
        //    // do nothing
        //}
        int rcptBalAmtapplySignum = rcptBalAmtapply.signum();
        if (rcpt.signum() == -1 && rcptBalAmtapplySignum != 0) {
            bizMsg.setMessageInfo("NFCM0067E", null);
            int pagenationFrom = NFCL2760CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
            //NFCL2760CommonLogic.setGlobalMsgToBizMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);
            return;
        } else {
            // do nothing
        }

        checkInvoiceEntryTimestamp(bizMsg, globalMsg);
        if ("E".equals(bizMsg.getMessageKind())) {
            for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(i).xxPgFlg_A1.getValue())) {
                    continue;
                }
                if (globalMsg.A.no(i).xxChkBox.isError()) {
                    NFCL2760CommonLogic.setPageData(globalMsg, bizMsg, i + 1);
                    break;
                }
            }
            bizMsg.xxRsltStsCd_H1.setValue(SCRN_STATUS_Y);
            return;
        } else {
            // do nothing
        }

        createArApplyInfcWrk(bizMsg, globalMsg, true);
        if ("E".equals(bizMsg.getMessageKind())) {
            return;
        } else {
            // do nothing
        }

        if (NFCL2760CommonLogic.isApiExecuteOnline(getGlobalCompanyCode(), globalMsg.A.getValidCount())) {

            // Start 2022/11/30 R.Takau [QC#57252, MOD]
            //execBatch(bizMsg, globalMsg);
            execBatch(bizMsg, globalMsg, true);
            // Start 2022/11/30 R.Takau [QC#57252, MOD]
            
            if ("E".equals(bizMsg.getMessageKind())) {
                return;
            } else {
                // nothing
            }

            // Update Credit Profile
            if(!updateCreditProfile(bizMsg, globalMsg)) {
                return;
            }

            AR_RCPTTMsg inMsg = new AR_RCPTTMsg();
            AR_RCPTTMsg outArRcptMsg = NFCL2760CommonLogic.updateArRcptInfoCratMethTpCdToM(bizMsg, globalMsg, inMsg);

            if (outArRcptMsg == null) {
                bizMsg.setMessageInfo("NFCM0080E", null);
                return;
            } else {
                bizMsg.setMessageInfo("NZZM0002I", new String[] {MANUAL_CASH_APPLICATION_ENTRY });
            }

            
        } else {
            // Api will execute by batch.
            bizMsg.xxPgFlg.setValue(ZYPConstant.FLG_OFF_N);
            bizMsg.setMessageInfo("NFCM0205I");
        }

    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global area information
     * @return void
     */
    private void execCancelCash(NFCL2760CMsg bizMsg, NFCL2760SMsg globalMsg) {

        bizMsg.xxRsltStsCd_H2.setValue(CANCELERROR_STATUS_N);

        // Delete Check Box
        List selectedRows = ZYPTableUtil.getSelectedRows(globalMsg.A, XX_CHK_BOX, ZYPConstant.CHKBOX_ON_Y);
        //if (selectedRows.isEmpty()) {
        //    return;
        //}

        checkCancelCash(bizMsg, globalMsg);
        if ("E".equals(bizMsg.getMessageKind())) {
            for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
                if (!ROW_STS.CANCEL.toString().equals(NFCL2760CommonLogic.isStatusOfRow(globalMsg.A.no(i).xxChkBox.getValue(), globalMsg.A.no(i).xxArCashApplyStsTxt.getValue()))) {
                    continue;
                }
                if (globalMsg.A.no(i).xxChkBox.isError()) {
                    NFCL2760CommonLogic.setPageData(globalMsg, bizMsg, i + 1);
                    break;
                }
            }
            bizMsg.xxRsltStsCd_H2.setValue(CANCELERROR_STATUS_Y);
            return;
        } else {
            // do nothing
        }

        NFCL2760CommonLogic.setApplyGrsAmt(globalMsg, bizMsg, blIni, ROW_STS.CANCEL.toString());

        if ("E".equals(bizMsg.getMessageKind())) {
            int pagenationFrom = NFCL2760CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
            NFCL2760CommonLogic.setGlobalMsgToBizMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);
            bizMsg.xxRsltStsCd_H2.setValue(CANCELERROR_STATUS_Y);
            return;
        } else {
            // do nothing
        }

        BigDecimal rcpt = bizMsg.dealRcptAmt.getValue();
        BigDecimal rcptBalAmtapply = bizMsg.xxBalApplyGrsAmt.getValue();

        if (rcpt.signum() != -1 && rcptBalAmtapply.signum() < 0) {
            bizMsg.setMessageInfo("NFCM0089E", null);
            int pagenationFrom = NFCL2760CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
            NFCL2760CommonLogic.setGlobalMsgToBizMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);
            bizMsg.xxRsltStsCd_H2.setValue(CANCELERROR_STATUS_Y);
            return;
        } else {
            // do nothin
        }

        checkInvoiceCancelTimestamp(bizMsg, globalMsg);
        if ("E".equals(bizMsg.getMessageKind())) {
            for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(i).xxPgFlg_A1.getValue())) {
                    continue;
                }
                if (globalMsg.A.no(i).xxChkBox.isError()) {
                    NFCL2760CommonLogic.setPageData(globalMsg, bizMsg, i + 1);
                    break;
                }
            }
            bizMsg.xxRsltStsCd_H2.setValue(CANCELERROR_STATUS_Y);
            return;
        } else {
            // do nothing
        }

        createArApplyInfcWrk(bizMsg, globalMsg, false);
        if ("E".equals(bizMsg.getMessageKind())) {
            bizMsg.xxRsltStsCd_H2.setValue(CANCELERROR_STATUS_Y);
            return;
        } else {
            // do nothing
        }

        // START 2018/02/13 H.Ikeda [QC#20435,ADD]
        for (int z = 0; z < globalMsg.A.getValidCount(); z++) {
            if (ROW_STS.CANCEL.toString().equals( NFCL2760CommonLogic.isStatusOfRow(globalMsg.A.no(z).xxChkBox.getValue(), globalMsg.A.no(z).xxArCashApplyStsTxt.getValue()))) {
                // START 2018/10/10 T.Ogura [QC#28167,MOD]
//                if (NFCL2760CommonLogic.isArCashApp(bizMsg.glblCmpyCd_H1.getValue(), globalMsg.A.no(z).arTrxNum.getValue())) {
                if (NFCL2760CommonLogic.isArCashApp(bizMsg.glblCmpyCd_H1.getValue(), globalMsg.A.no(z).arTrxNum.getValue(), bizMsg.rcptNum.getValue())) {
                // END   2018/10/10 T.Ogura [QC#28167,MOD]
                    globalMsg.A.no(z).xxChkBox.setErrorInfo(1, "NFCM0079E");
                    bizMsg.setMessageInfo("ZZM9037E", null);
                    NFCL2760CommonLogic.setPageData(globalMsg, bizMsg, z + 1);
                    bizMsg.xxRsltStsCd_H2.setValue(CANCELERROR_STATUS_Y);
                    return;
                }
            }
        }
        // END   2018/02/13 H.Ikeda [QC#20435,ADD]        

        AR_CASH_APPTMsg inArCashAppMsg = new AR_CASH_APPTMsg();
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            if (!(ROW_STS.CANCEL.toString().equals(NFCL2760CommonLogic.isStatusOfRow(globalMsg.A.no(i).xxChkBox.getValue(), globalMsg.A.no(i).xxArCashApplyStsTxt.getValue())))) {
                continue;
            }
            if (ROW_STS.CANCEL.toString().equals( NFCL2760CommonLogic.isStatusOfRow(globalMsg.A.no(i).xxChkBox.getValue(), globalMsg.A.no(i).xxArCashApplyStsTxt.getValue()))) {

                inArCashAppMsg.clear();
                inArCashAppMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd_H1.getValue());
                inArCashAppMsg.arCashAppPk.setValue(globalMsg.A.no(i).arCashAppPk_BK.getValue());
                inArCashAppMsg.arCashAppSqNum.setValue(globalMsg.A.no(i).arCashAppSqNum_UP.getValue());
                inArCashAppMsg.ezUpTime.setValue(globalMsg.A.no(i).ezUpTime_UP.getValue());
                inArCashAppMsg.ezUpTimeZone.setValue(globalMsg.A.no(i).ezUpTimeZone_UP.getValue());
                inArCashAppMsg.arScrCancFlg.setValue(ZYPConstant.FLG_ON_Y);

                AR_CASH_APPTMsg outArCashAppMsg = NFCL2760CommonLogic.updateArCashAppInfoExclusive(inArCashAppMsg);

                if (null == outArCashAppMsg) {
                    globalMsg.A.no(i).xxChkBox.setErrorInfo(1, "NFCM0079E");
                    bizMsg.setMessageInfo("ZZM9037E", null);
                    NFCL2760CommonLogic.setPageData(globalMsg, bizMsg, i + 1);
                    bizMsg.xxRsltStsCd_H2.setValue(CANCELERROR_STATUS_Y);
                    return;

                } else {
                    // do nothing
                }

                if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).arAdjNum_BK) && ZYPCommonFunc.hasValue(outArCashAppMsg.arTrxNum.getValue())) {

                    AR_CASH_APPTMsg inTMsg = new AR_CASH_APPTMsg();
                    inTMsg.setConditionValue("glblCmpyCd01", outArCashAppMsg.glblCmpyCd.getValue());
                    inTMsg.setConditionValue("arCashAppPk01", outArCashAppMsg.arCashAppPk.getValue());
                    inTMsg.setConditionValue("arTrxNum01", outArCashAppMsg.arTrxNum.getValue());

                    AR_CASH_APPTMsgArray outFindArCashAppMsgArray = NFCL2760CommonLogic.findArCashAppList(bizMsg, globalMsg, inTMsg);

                    if (null == outFindArCashAppMsgArray) {
                        globalMsg.A.no(i).xxChkBox.setErrorInfo(1, "NFCM0079E");
                        bizMsg.setMessageInfo("ZZM9037E", null);
                        NFCL2760CommonLogic.setPageData(globalMsg, bizMsg, i + 1);
                        bizMsg.xxRsltStsCd_H2.setValue(CANCELERROR_STATUS_Y);
                        return;

                    } else {
                        // do nothing
                    }

                    for (int j = 0; j < outFindArCashAppMsgArray.length(); j++) {
                        if (AR_APPLY_TP.ADJUSTMENT.equals(outFindArCashAppMsgArray.no(j).arApplyTpCd.getValue())) {
                            inArCashAppMsg.clear();
                            inArCashAppMsg.glblCmpyCd.setValue(outFindArCashAppMsgArray.no(j).glblCmpyCd.getValue());
                            inArCashAppMsg.arCashAppPk.setValue(outFindArCashAppMsgArray.no(j).arCashAppPk.getValue());
                            inArCashAppMsg.arCashAppSqNum.setValue(outFindArCashAppMsgArray.no(j).arCashAppSqNum.getValue());
                            inArCashAppMsg.ezUpTime.setValue(outFindArCashAppMsgArray.no(j).ezUpTime.getValue());
                            inArCashAppMsg.ezUpTimeZone.setValue(outFindArCashAppMsgArray.no(j).ezUpTimeZone.getValue());
                            inArCashAppMsg.arScrCancFlg.setValue(ZYPConstant.FLG_ON_Y);

                            AR_CASH_APPTMsg outUpdateArCashAppMsg = NFCL2760CommonLogic.updateArCashAppInfoExclusive(inArCashAppMsg);

                            if (null == outUpdateArCashAppMsg) {
                                globalMsg.A.no(i).xxChkBox.setErrorInfo(1, "NFCM0079E");
                                bizMsg.setMessageInfo("ZZM9037E", null);
                                NFCL2760CommonLogic.setPageData(globalMsg, bizMsg, i + 1);
                                bizMsg.xxRsltStsCd_H2.setValue(CANCELERROR_STATUS_Y);
                                return;

                            } else {
                                // do nothing
                            }

                        } else {
                            // do nothing
                        }
                    }
                } else {

                }
            } else {
                // do nothing
            }
        }

        // START 2022/04/22 D.Mamaril [QC#59333, ADD]
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            if (ROW_STS.CANCEL.toString().equals(NFCL2760CommonLogic.isStatusOfRow(globalMsg.A.no(i).xxChkBox.getValue(), globalMsg.A.no(i).xxArCashApplyStsTxt.getValue()))
                    && globalMsg.A.no(i).xxModeInd_BK.getValue().equals(DETAIL_MODE_REFUND)) {
                NFCL2760CommonLogic.updateRefundDetails(globalMsg, bizMsg, i);
            }
        }
        // END 2022/04/22 D.Mamaril [QC#59333, ADD]

        if (NFCL2760CommonLogic.isApiExecuteOnline(getGlobalCompanyCode(), globalMsg.A.getValidCount())) {

            // Start 2022/11/30 R.Takau [QC#57252, MOD]
            //execBatch(bizMsg, globalMsg);
            execBatch(bizMsg, globalMsg, false);
            // Start 2022/11/30 R.Takau [QC#57252, MOD]
            if ("E".equals(bizMsg.getMessageKind())) {
                bizMsg.xxRsltStsCd_H2.setValue(CANCELERROR_STATUS_Y);
                return;
            } else {
                // do nothing
            }

            // ///////////////////////////////////////////////////////////Update Credit Profile
            if(!updateCreditProfile(bizMsg, globalMsg)) {
                return;
            }

            // START 2019/09/02 H.Ikeda [QC#53152, ADD]
            updateArAutoPurgeOfsFlg(bizMsg, globalMsg);
            if ("E".equals(bizMsg.getMessageKind())) {
                for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
                    if (globalMsg.A.no(i).xxChkBox.isError()) {
                        NFCL2760CommonLogic.setPageData(globalMsg, bizMsg, i + 1);
                        break;
                    }
                }
                bizMsg.xxRsltStsCd_H2.setValue(CANCELERROR_STATUS_Y);
                return;
            }
            // END   2019/09/02 H.Ikeda [QC#53152, ADD]

            AR_RCPTTMsg inMsg = new AR_RCPTTMsg();
            AR_RCPTTMsg outArRcptMsg = NFCL2760CommonLogic.updateArRcptInfoCratMethTpCdToM(bizMsg, globalMsg, inMsg);

            if (outArRcptMsg == null) {
                bizMsg.setMessageInfo("NFCM0080E", null);
                return;
            } else {
                bizMsg.setMessageInfo("NZZM0002I", new String[] {MANUAL_CASH_APPLICATION_ENTRY });
            }
            
        } else {
            // Api will execute by batch.
            bizMsg.xxPgFlg.setValue(ZYPConstant.FLG_OFF_N);
            bizMsg.setMessageInfo("NFCM0205I");
        }

    }

    // START 2019/09/02 H.Ikeda [QC#53152, ADD]
    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global area information
     * @return void
     */
    private void updateArAutoPurgeOfsFlg(NFCL2760CMsg bizMsg, NFCL2760SMsg globalMsg) {

        AR_TRX_BALTMsg inArTrxBalMsg = new AR_TRX_BALTMsg();
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            if (!ZYPConstant.CHKBOX_ON_Y.equals(globalMsg.A.no(i).xxChkBox.getValue()) && ZYPCommonFunc.hasValue(globalMsg.A.no(i).arTrxBalPk_BK)) {
                if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).arTrxTpCd) && AR_TRX_TP.CREDIT_MEMO.equals(globalMsg.A.no(i).arTrxTpCd.getValue())) {
                    inArTrxBalMsg.clear();
                    inArTrxBalMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd_H1.getValue());
                    inArTrxBalMsg.arTrxBalPk.setValue(globalMsg.A.no(i).arTrxBalPk_BK.getValue());

                    AR_TRX_BALTMsg outArTrxBalMsg = NFCL2760CommonLogic.findArTrxBalInfo(inArTrxBalMsg);

                    if (null == outArTrxBalMsg || !RTNCD_NORMAL.equals(outArTrxBalMsg.getReturnCode())) {
                        globalMsg.A.no(i).xxChkBox.setErrorInfo(1, "NFCM0079E");
                        bizMsg.setMessageInfo("ZZM9037E", null);
                        continue;
                    } else {
                        if (ZYPCommonFunc.hasValue(outArTrxBalMsg.arAutoPurgeOfsFlg) && ZYPConstant.FLG_ON_Y.equals(outArTrxBalMsg.arAutoPurgeOfsFlg.getValue())) {
                            outArTrxBalMsg.arAutoPurgeOfsFlg.setValue(ZYPConstant.FLG_OFF_N);
                            EZDTBLAccessor.update(outArTrxBalMsg);
                            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outArTrxBalMsg.getReturnCode())) {
                                globalMsg.A.no(i).xxChkBox.setErrorInfo(1, "NFCM0025E");
                                bizMsg.setMessageInfo("NFCM0032E", null);
                                continue;
                            }
                        }
                    }
                }
                
            }
        }
    }
    // END   2019/09/02 H.Ikeda [QC#53152, ADD]

    /**
     * 
     * @param bizMsg
     * @param globalMsg
     * @return
     */
    private boolean updateCreditProfile(NFCL2760CMsg bizMsg, NFCL2760SMsg globalMsg) {

        // START 2018/07/19 Y.Matsui [QC#26871, ADD] -- Performance Improvement
        List<String> processedBillToCustCdList = new ArrayList<String>();
        List<String> processedSellToCustCdList = new ArrayList<String>();
        // END   2018/07/19 Y.Matsui [QC#26871, ADD]

        for(int i = 0; i< globalMsg.A.getValidCount(); i++) {
            // START 2018/06/21 [QC#25862, MOD]
            if ( NFCL2760CommonLogic.isStatusOfRow(globalMsg.A.no(i).xxChkBox.getValue(), globalMsg.A.no(i).xxArCashApplyStsTxt.getValue()).equals(ROW_STS.CASH_APPLICATION.toString())
                    || NFCL2760CommonLogic.isStatusOfRow(globalMsg.A.no(i).xxChkBox.getValue(), globalMsg.A.no(i).xxArCashApplyStsTxt.getValue()).equals(ROW_STS.CANCEL.toString())) {
                NFZC202001 crPrflUpdApi = new NFZC202001();
                NFZC202001PMsg paramMsg = new NFZC202001PMsg();

                if(ZYPCommonFunc.hasValue(globalMsg.A.no(i).billToCustCd.getValue())) {
                    // START 2018/07/19 Y.Matsui [QC#26871, ADD] -- Performance Improvement
                    if (processedBillToCustCdList.contains(globalMsg.A.no(i).billToCustCd.getValue())) {
                        continue;
                    } else {
                        processedBillToCustCdList.add(globalMsg.A.no(i).billToCustCd.getValue());
                    }
                    // END   2018/07/19 Y.Matsui [QC#26871, ADD]

                    ZYPEZDItemValueSetter.setValue(paramMsg.xxModeCd, NFZC202001.MODE_BILL_TO_CUST);
                    ZYPEZDItemValueSetter.setValue(paramMsg.glblCmpyCd, bizMsg.glblCmpyCd_H1.getValue());
                    ZYPEZDItemValueSetter.setValue(paramMsg.billToCustCd, globalMsg.A.no(i).billToCustCd.getValue());
                    ZYPEZDItemValueSetter.setValue(paramMsg.procDt, bizMsg.acctDt.getValue());
                } else {
                    if(ZYPCommonFunc.hasValue(globalMsg.A.no(i).sellToCustCd.getValue())) {
                        // START 2018/07/19 Y.Matsui [QC#26871, ADD] -- Performance Improvement
                        if (processedSellToCustCdList.contains(globalMsg.A.no(i).sellToCustCd.getValue())) {
                            continue;
                        } else {
                            processedSellToCustCdList.add(globalMsg.A.no(i).sellToCustCd.getValue());
                        }
                        // END   2018/07/19 Y.Matsui [QC#26871, ADD]

                        ZYPEZDItemValueSetter.setValue(paramMsg.xxModeCd, NFZC202001.MODE_CUST_ACCT);
                        ZYPEZDItemValueSetter.setValue(paramMsg.glblCmpyCd, bizMsg.glblCmpyCd_H1.getValue());
                        ZYPEZDItemValueSetter.setValue(paramMsg.sellToCustCd, globalMsg.A.no(i).sellToCustCd.getValue());
                        ZYPEZDItemValueSetter.setValue(paramMsg.procDt, bizMsg.acctDt.getValue());
                    } else {
                        // START 2018/07/19 Y.Matsui [QC#26871, ADD] -- Performance Improvement
                        if (processedSellToCustCdList.contains(bizMsg.payerCustCd.getValue())) {
                            continue;
                        } else {
                            processedSellToCustCdList.add(bizMsg.payerCustCd.getValue());
                        }
                        // END   2018/07/19 Y.Matsui [QC#26871, ADD]

                        ZYPEZDItemValueSetter.setValue(paramMsg.xxModeCd, NFZC202001.MODE_CUST_ACCT);
                        ZYPEZDItemValueSetter.setValue(paramMsg.glblCmpyCd, bizMsg.glblCmpyCd_H1.getValue());
                        ZYPEZDItemValueSetter.setValue(paramMsg.sellToCustCd, bizMsg.payerCustCd.getValue());
                        ZYPEZDItemValueSetter.setValue(paramMsg.procDt, bizMsg.acctDt.getValue());
                    }
                }

                crPrflUpdApi.execute(paramMsg, ONBATCH_TYPE.BATCH);

                List<String> msgList = S21ApiUtil.getXxMsgIdList(paramMsg);
                for (String msgId : msgList) {
                    if (msgId.endsWith("E")) {
                        bizMsg.setMessageInfo(msgId);
                        return false;
                    }
                }
            }
            // END   2018/06/21 [QC#25862, MOD]
        }
        
        return true;

    }


    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global area information
     * @return void
     */
    private void checkDepositDateAndTrxDate(NFCL2760CMsg bizMsg, NFCL2760SMsg globalMsg) {

        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(i).xxPgFlg_A1.getValue())) {
                continue;
            }
        }

    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global area information
     * @return void
     */
    private void checkEntryCash(NFCL2760CMsg bizMsg, NFCL2760SMsg globalMsg) {

        //String acctYM = NFCL2760CommonLogic.getYearMonth(bizMsg.acctDt.getValue());
        String batProcDt = ZYPDateUtil.getBatProcDate(bizMsg.glblCmpyCd_H1.getValue());
        //String batProcYM = NFCL2760CommonLogic.getYearMonth(batProcDt);
        //String preBatProcYM = NFCL2760CommonLogic.getYearMonth(NFCL2760CommonLogic.getBeforeMonth(batProcDt, "yyyyMMdd"));

        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(i).xxPgFlg_A1.getValue())) {
                continue;
            }
        }

    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global area information
     * @return void
     */
    private void checkCancelCash(NFCL2760CMsg bizMsg, NFCL2760SMsg globalMsg) {

        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            
            if (!( ROW_STS.CANCEL.toString().equals( NFCL2760CommonLogic.isStatusOfRow(globalMsg.A.no(i).xxChkBox.getValue(), globalMsg.A.no(i).xxArCashApplyStsTxt.getValue())))) {
                 continue;
            }

            if (ZYPConstant.CHKBOX_ON_Y.equals(globalMsg.A.no(i).xxChkBox.getValue()) && DETAIL_MODE_DEDUCTION.equals(globalMsg.A.no(i).xxModeInd_BK.getValue())) {

                Map<String, Comparable> inMap = new HashMap<String, Comparable>();
                inMap.put("glblCmpyCd", bizMsg.glblCmpyCd_H1.getValue());
                inMap.put("arAdjNum", globalMsg.A.no(i).arAdjNum_BK.getValue());
                inMap.put("arAdjTrxTpCd", AR_ADJ_TRX_TP.DEDUCTION);
                inMap.put("arCashApplyStsCd01", AR_CASH_APPLY_STS.UNAPPLIED);
                inMap.put("dealApplyGrsAmt", BigDecimal.ZERO);
                inMap.put("dealApplyCashDiscAmt", BigDecimal.ZERO);
                inMap.put("dealApplyCrAmt", BigDecimal.ZERO);
                inMap.put("dealApplyAdjAmt", BigDecimal.ZERO);

                S21SsmEZDResult outDeductionData = NFCL2760Query.getInstance().findDeductionData(inMap, bizMsg);

                if (RTNCD_NOT_FOUND.equals(outDeductionData.getResultCode())) {
                	bizMsg.A.no(i).xxChkBox.setErrorInfo(1, "NFCM0078E");
                    bizMsg.setMessageInfo("ZZM9037E", null);
                } else {
                    // do nothing
                }
            } else if (ZYPConstant.CHKBOX_ON_Y.equals(globalMsg.A.no(i).xxChkBox.getValue()) && DETAIL_MODE_ONACOUNT.equals(globalMsg.A.no(i).xxModeInd_BK.getValue())) {

                Map<String, Comparable> inMap = new HashMap<String, Comparable>();
                inMap.put("glblCmpyCd", bizMsg.glblCmpyCd_H1.getValue());
                inMap.put("arAdjNum", globalMsg.A.no(i).arAdjNum_BK.getValue());
                inMap.put("arAdjTrxTpCd", AR_TRX_TP.ON_ACCOUNT);
                inMap.put("arCashApplyStsCd01", AR_CASH_APPLY_STS.UNAPPLIED);
                inMap.put("dealApplyGrsAmt", BigDecimal.ZERO);
                inMap.put("dealApplyCashDiscAmt", BigDecimal.ZERO);
                inMap.put("dealApplyCrAmt", BigDecimal.ZERO);
                inMap.put("dealApplyAdjAmt", BigDecimal.ZERO);

                S21SsmEZDResult outDeductionData = NFCL2760Query.getInstance().findDeductionData(inMap, bizMsg);

                if (RTNCD_NOT_FOUND.equals(outDeductionData.getResultCode())) {
                	bizMsg.A.no(i).xxChkBox.setErrorInfo(1, "NFCM0170E");
                    bizMsg.setMessageInfo("ZZM9037E", null);
                } else {
                    // do nothing
                }
                // do nothing
            } else {
                // do nothing
            }
        }

    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global area information
     * @return void
     */
    private void checkInvoiceEntryTimestamp(NFCL2760CMsg bizMsg, NFCL2760SMsg globalMsg) {

        AR_TRX_BALTMsg inArTrxBalMsg = new AR_TRX_BALTMsg();
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(i).xxPgFlg_A1.getValue())) {
                continue;
            }

            if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).arTrxNum)) {

                inArTrxBalMsg.clear();
                inArTrxBalMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd_H1.getValue());
                inArTrxBalMsg.arTrxBalPk.setValue(globalMsg.A.no(i).arTrxBalPk_BK.getValue());

                AR_TRX_BALTMsg outArTrxBalMsg = NFCL2760CommonLogic.findArTrxBalInfo(inArTrxBalMsg);

                if (null == outArTrxBalMsg || !RTNCD_NORMAL.equals(outArTrxBalMsg.getReturnCode())) {
                    globalMsg.A.no(i).xxChkBox.setErrorInfo(1, "NFCM0079E");
                    bizMsg.setMessageInfo("ZZM9037E", null);
                    continue;
                } else {
                    // do nothing
                }
            } else {
                // do nothing
            }
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global area information
     * @return void
     */
    private void checkInvoiceCancelTimestamp(NFCL2760CMsg bizMsg, NFCL2760SMsg globalMsg) {

        AR_TRX_BALTMsg inArTrxBalMsg = new AR_TRX_BALTMsg();
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(i).xxPgFlg_A1.getValue())) {
                continue;
            }

            if (!ZYPConstant.CHKBOX_ON_Y.equals(globalMsg.A.no(i).xxChkBox.getValue()) && ZYPCommonFunc.hasValue(globalMsg.A.no(i).arTrxBalPk_BK)) {

                inArTrxBalMsg.clear();
                inArTrxBalMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd_H1.getValue());
                inArTrxBalMsg.arTrxBalPk.setValue(globalMsg.A.no(i).arTrxBalPk_BK.getValue());

                AR_TRX_BALTMsg outArTrxBalMsg = NFCL2760CommonLogic.findArTrxBalInfo(inArTrxBalMsg);

                if (null == outArTrxBalMsg || !RTNCD_NORMAL.equals(outArTrxBalMsg.getReturnCode())) {
                    globalMsg.A.no(i).xxChkBox.setErrorInfo(1, "NFCM0079E");
                    bizMsg.setMessageInfo("ZZM9037E", null);
                    continue;
                } else {
                    // do nothing
                }
            } else {
                // do nothing
            }
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global area information
     * @return void
     */
    private void createArApplyInfcWrk(NFCL2760CMsg bizMsg, NFCL2760SMsg globalMsg, boolean blApply) {

        S21UserProfileService userProfileService = S21UserProfileServiceFactory.getInstance().getService();
        S21UserInfo userInfo = userProfileService.getContextUserInfo();
        String userId = userInfo.getUserId();
        String currentSystemTime = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS");

        int index = 1;

        AR_APPLY_INTFC_WRKTMsg inArApplyIntfcWrkMsg = new AR_APPLY_INTFC_WRKTMsg();

        AR_APPLY_INTFC_BATTMsg arApplyIntfcBatMsg = null;

        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            if ( blApply && ROW_STS.CANCEL.toString().equals(NFCL2760CommonLogic.isStatusOfRow(globalMsg.A.no(i).xxChkBox.getValue(), globalMsg.A.no(i).xxArCashApplyStsTxt.getValue())) ){
                //In case of Apply, skip the Cancel rows
                continue;
            }
            if ( !blApply && ROW_STS.CASH_APPLICATION.toString().equals(NFCL2760CommonLogic.isStatusOfRow(globalMsg.A.no(i).xxChkBox.getValue(), globalMsg.A.no(i).xxArCashApplyStsTxt.getValue()))) {
               //In case of Cancel, skip the Apply rows
               continue;
            }
            if ( (ROW_STS.CANCEL.toString().equals(NFCL2760CommonLogic.isStatusOfRow(globalMsg.A.no(i).xxChkBox.getValue(), globalMsg.A.no(i).xxArCashApplyStsTxt.getValue()))
                    || ROW_STS.CASH_APPLICATION.toString().equals(NFCL2760CommonLogic.isStatusOfRow(globalMsg.A.no(i).xxChkBox.getValue(), globalMsg.A.no(i).xxArCashApplyStsTxt.getValue())))) {

                inArApplyIntfcWrkMsg.clear();
                inArApplyIntfcWrkMsg.applyGrpKey.setValue(userId + currentSystemTime);
                inArApplyIntfcWrkMsg.applyGrpSubPk.setValue(index);
                inArApplyIntfcWrkMsg.dealSqDtlNum.setValue(ZYPCommonFunc.leftPad(String.valueOf(index), BIZAPL_INT_NUM_4, PAD_STR_ZERO));
                inArApplyIntfcWrkMsg.usrId.setValue(userId);
                inArApplyIntfcWrkMsg.upldCsvRqstPk.setValue(BigDecimal.ZERO);
                inArApplyIntfcWrkMsg.payerCustCd.setValue(bizMsg.payerCustCd.getValue());
                inArApplyIntfcWrkMsg.rcptGlDt.setValue(bizMsg.glDt_H1.getValue());
                // START 2018/03/26 J.Kim [QC#25017, MOD]
                // inArApplyIntfcWrkMsg.cashAppGlDt.setValue(bizMsg.glDt_H1.getValue());
                inArApplyIntfcWrkMsg.cashAppGlDt.setValue(bizMsg.acctDt.getValue());
                // END 2018/03/26 J.Kim [QC#25017, MOD]
                setCreateData(inArApplyIntfcWrkMsg, bizMsg, globalMsg.A.no(i));

                AR_APPLY_INTFC_WRKTMsg outArApplyIntfcWrkMsg = NFCL2760CommonLogic.createArApplyIntfcWrkInfo(inArApplyIntfcWrkMsg);

                if (RTNCD_NORMAL.equals(outArApplyIntfcWrkMsg.getReturnCode())) {
                    globalMsg.A.no(i).applyGrpKey_BK.setValue(userId + currentSystemTime);
                    globalMsg.A.no(i).applyGrpSubPk_BK.setValue(index);
                    index++;
                } else {
                    EZDDebugOutput.println(1, "createArApplyInfcWrk() Err:createArApplyIntfcWrkInfo", this);
                    S21InfoLogOutput.println("NFCL2760 AR_APPLY_INTFC_WRK:" + inArApplyIntfcWrkMsg.toString());
                    globalMsg.A.no(i).xxChkBox.setErrorInfo(1, "NFCM0032E");
                    bizMsg.setMessageInfo("NFCM0032E", null);
                    int pagenationFrom = NFCL2760CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
                    NFCL2760CommonLogic.setGlobalMsgToBizMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);
                    return;
                }

                arApplyIntfcBatMsg = setArApplyIntfcBat(outArApplyIntfcWrkMsg, arApplyIntfcBatMsg);
            }
        }

        if (!NFCL2760CommonLogic.isApiExecuteOnline(getGlobalCompanyCode(), globalMsg.A.getValidCount()) && null != arApplyIntfcBatMsg) {
            EZDTBLAccessor.insert(arApplyIntfcBatMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(arApplyIntfcBatMsg.getReturnCode())) {
                S21InfoLogOutput.println("Create AR_APPLY_INTFC_BAT  Error.");
                bizMsg.setMessageInfo("NFCM0032E", null);
                return;
            }
        }

        // START 2016/09/07 J.Kim [QC#13793,DEL]
//        if (MODE_ENTRY.equals(bizMsg.xxModeInd_H1.getValue())) {
//
//            // START 2016/09/07 J.Kim [QC#13793,MOD]
//            // BigDecimal onAcct = bizMsg.dealRcptRmngBalAmt.getValue().subtract(bizMsg.xxApplyGrsAmt.getValue());
//            Map<String, Object> dealRmngBalGrsAmtMap = getTotalDealRmngBalGrsAmt(globalMsg);
//            BigDecimal onAcct = (BigDecimal) dealRmngBalGrsAmtMap.get(AR_TRX_TP.ON_ACCOUNT);
//            // END 2016/09/07 J.Kim [QC#13793,MOD]
//            if (0 < onAcct.signum()) {
//
//                inArApplyIntfcWrkMsg.clear();
//                inArApplyIntfcWrkMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd_H1.getValue());
//                inArApplyIntfcWrkMsg.applyGrpKey.setValue(userId + currentSystemTime);
//                inArApplyIntfcWrkMsg.applyGrpSubPk.setValue(index);
//                inArApplyIntfcWrkMsg.dealSqDtlNum.setValue(ZYPCommonFunc.leftPad(String.valueOf(index), BIZAPL_INT_NUM_4, PAD_STR_ZERO));
//                inArApplyIntfcWrkMsg.bizAppId.setValue("NFCL2760");
//                inArApplyIntfcWrkMsg.procTpCd.setValue("1");
//                inArApplyIntfcWrkMsg.dealSqNum.setValue("00000001");
//                inArApplyIntfcWrkMsg.procStsCd.setValue(PROC_STS.IN_COMPLETED);
//                inArApplyIntfcWrkMsg.rcptNum.setValue(bizMsg.rcptNum.getValue());
//                inArApplyIntfcWrkMsg.rcptInProcSqPk.setValue(BigDecimal.ZERO);
//                inArApplyIntfcWrkMsg.usrId.setValue(userId);
//                inArApplyIntfcWrkMsg.upldCsvRqstPk.setValue(BigDecimal.ZERO);
//
//                if (ZYPCommonFunc.hasValue(bizMsg.rcptTrxBalPk_H1)) {
//                    inArApplyIntfcWrkMsg.rcptTrxBalPk.setValue(bizMsg.rcptTrxBalPk_H1.getValue());
//                } else {
//                    inArApplyIntfcWrkMsg.rcptTrxBalPk.setValue(BigDecimal.ZERO);
//                }
//
//                inArApplyIntfcWrkMsg.rcptHdrLastUpdTs.setValue(bizMsg.rcptHdrLastUpdTs_H1.getValue());
//                inArApplyIntfcWrkMsg.rcptHdrTz.setValue(bizMsg.rcptHdrTz_H1.getValue());
//                inArApplyIntfcWrkMsg.rcptTrxBalLastUpdTs.setValue(bizMsg.rcptTrxBalLastUpdTs_H1.getValue());
//                inArApplyIntfcWrkMsg.rcptTrxBalTz.setValue(bizMsg.rcptTrxBalTz_H1.getValue());
//                inArApplyIntfcWrkMsg.grpInvFlg.setValue(ZYPConstant.FLG_OFF_N);
//                inArApplyIntfcWrkMsg.dealCcyCd.setValue(bizMsg.ccyCd.getValue());
//                inArApplyIntfcWrkMsg.dealOnAcctCashAmt.setValue(onAcct);
//                inArApplyIntfcWrkMsg.invTrxBalPk.setValue(BigDecimal.ZERO);
//                inArApplyIntfcWrkMsg.crTrxBalPk.setValue(BigDecimal.ZERO);
//                inArApplyIntfcWrkMsg.dealApplyAmt.setValue(BigDecimal.ZERO);
//                inArApplyIntfcWrkMsg.cashDiscPct.setValue(BigDecimal.ZERO);
//                inArApplyIntfcWrkMsg.dealCashDiscAmt.setValue(BigDecimal.ZERO);
//                inArApplyIntfcWrkMsg.dealApplyAdjAmt.setValue(BigDecimal.ZERO);
//                inArApplyIntfcWrkMsg.dealApplyAdjRsvdAmt.setValue(BigDecimal.ZERO);
//
//                inArApplyIntfcWrkMsg.payerCustCd.setValue(bizMsg.payerCustCd.getValue());
//                inArApplyIntfcWrkMsg.rcptGlDt.setValue(bizMsg.glDt_H1.getValue());
//                inArApplyIntfcWrkMsg.cashAppGlDt.setValue(bizMsg.glDt_H1.getValue());
//
//                EZDDebugOutput.printLog(1, "NFCL2760 MODE_ENTRY AR_APPLY_INTFC_WRK:" + inArApplyIntfcWrkMsg.toString(), null);
//                AR_APPLY_INTFC_WRKTMsg outArApplyIntfcWrkMsg = NFCL2760CommonLogic.createArApplyIntfcWrkInfo(inArApplyIntfcWrkMsg);
//
//                if (RTNCD_NORMAL.equals(outArApplyIntfcWrkMsg.getReturnCode())) {
//                    // do nothing
//                } else {
//                    EZDDebugOutput.println(1, "createArApplyInfcWrk() Err:createArApplyIntfcWrkInfo", this);
//                    S21InfoLogOutput.println("NFCL2760 MODE_ENTRY AR_APPLY_INTFC_WRK:" + inArApplyIntfcWrkMsg.toString());
//                    bizMsg.setMessageInfo("NFCM0032E", null);
//                    int pagenationFrom = NFCL2760CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
//                    NFCL2760CommonLogic.setGlobalMsgToBizMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);
//                    return;
//                }
//            } else {
//                // do nothing
//            }
//        } else {
//            // do nothing
//        }
          // END 2016/09/07 J.Kim [QC#13793,DEL]
    }

    private static AR_APPLY_INTFC_BATTMsg setArApplyIntfcBat(AR_APPLY_INTFC_WRKTMsg wrkMsg, AR_APPLY_INTFC_BATTMsg batMsg) {

        if (null == batMsg) {
            batMsg = new AR_APPLY_INTFC_BATTMsg();
            setValue(batMsg.glblCmpyCd, wrkMsg.glblCmpyCd);
            setValue(batMsg.applyGrpKey, wrkMsg.applyGrpKey);
            setValue(batMsg.applyGrpSubPk, new BigDecimal(1));
            setValue(batMsg.bizAppId, wrkMsg.bizAppId);
            setValue(batMsg.intfcId, wrkMsg.intfcId);
            setValue(batMsg.upldCsvId, wrkMsg.upldCsvId);
            setValue(batMsg.upldCsvRqstPk, wrkMsg.upldCsvRqstPk);
            setValue(batMsg.procTpCd, wrkMsg.procTpCd);
            setValue(batMsg.dealSqNum, "00000001");
            setValue(batMsg.dealSqDtlNum, "0001");
            setValue(batMsg.procStsCd, wrkMsg.procStsCd);
            setValue(batMsg.usrId, wrkMsg.usrId);
            setValue(batMsg.rcptNum, wrkMsg.rcptNum);
            setValue(batMsg.rcptDtlNum, wrkMsg.rcptDtlNum);
            setValue(batMsg.rcvFuncId, wrkMsg.rcvFuncId);
            setValue(batMsg.rcptInProcSqPk, wrkMsg.rcptInProcSqPk);
            setValue(batMsg.rcvHdrNum, wrkMsg.rcvHdrNum);
            setValue(batMsg.rcvDtlNum, "0001");
            setValue(batMsg.rcptGlDt, wrkMsg.rcptGlDt);
            setValue(batMsg.payerCustCd, wrkMsg.payerCustCd);
            setValue(batMsg.grpInvFlg, "N");
            batMsg.invNum.clear();
            batMsg.arTrxTpCd.clear();
            setValue(batMsg.dealCcyCd, wrkMsg.dealCcyCd);
            setValue(batMsg.dealApplyAmt, BigDecimal.ZERO);
            setValue(batMsg.cashAppGlDt, wrkMsg.cashAppGlDt);
            setValue(batMsg.cashDiscPct, BigDecimal.ZERO);
            setValue(batMsg.dealCashDiscAmt, BigDecimal.ZERO);
            setValue(batMsg.dealOnAcctCashAmt, BigDecimal.ZERO);
            batMsg.arAdjNum.clear();
            batMsg.arAdjTrxTpCd.clear();
            batMsg.arAdjTpCd.clear();
            setValue(batMsg.dealApplyAdjAmt, BigDecimal.ZERO);
            batMsg.tocCd.clear();
            batMsg.coaProdCd.clear();
            batMsg.arCustRefNum.clear();
            setValue(batMsg.startDt, wrkMsg.ezInTime.getValue().substring(0, 8));
            setValue(batMsg.startTm, wrkMsg.ezInTime.getValue().substring(8, 14));
            batMsg.endDt.clear();
            batMsg.endTm.clear();
            setValue(batMsg.arTrxCnt, BigDecimal.ZERO);

        }

        if (!ZYPCommonFunc.hasValue(wrkMsg.arAdjTrxTpCd) || wrkMsg.dealApplyAmt.getValue().compareTo(wrkMsg.dealApplyAdjAmt.getValue()) != 0) {
            setValue(batMsg.dealApplyAmt, batMsg.dealApplyAmt.getValue().add(wrkMsg.dealApplyAmt.getValue()));
        }

        setValue(batMsg.dealApplyAdjAmt, batMsg.dealApplyAdjAmt.getValue().add(wrkMsg.dealApplyAdjAmt.getValue()));
        setValue(batMsg.dealCashDiscAmt, batMsg.dealCashDiscAmt.getValue().add(wrkMsg.dealCashDiscAmt.getValue()));
        setValue(batMsg.dealOnAcctCashAmt, batMsg.dealOnAcctCashAmt.getValue().add(wrkMsg.dealOnAcctCashAmt.getValue()));
        setValue(batMsg.arTrxCnt, new BigDecimal(batMsg.arTrxCnt.getValueInt() + 1));

        return batMsg;

    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed. TMsg
     * @param inAR_APPLY_INTFC_WRKMsg AR_APPLY_INTFC_WRKMsg
     * @param bizMsg Business Component Interface Message
     * @param aSMsg Global area information
     * @return void
     */
    private void setCreateData(AR_APPLY_INTFC_WRKTMsg inArApplyIntfcWrkMsg, NFCL2760CMsg bizMsg, NFCL2760_ASMsg aSMsg) {

        inArApplyIntfcWrkMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd_H1.getValue());
        inArApplyIntfcWrkMsg.bizAppId.setValue("NFCL2760");

        if (MODE_ENTRY.equals(bizMsg.xxModeInd_H1.getValue())) {
            inArApplyIntfcWrkMsg.procTpCd.setValue("1");
        } else {
            inArApplyIntfcWrkMsg.procTpCd.setValue("3");
        }
        inArApplyIntfcWrkMsg.dealSqNum.setValue("00000001");
        inArApplyIntfcWrkMsg.procStsCd.setValue(PROC_STS.IN_COMPLETED);
        inArApplyIntfcWrkMsg.rcptNum.setValue(bizMsg.rcptNum.getValue());
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

        if (MODE_ENTRY.equals(bizMsg.xxModeInd_H1.getValue())) {
            if (!DETAIL_MODE_DEDUCTION.equals(aSMsg.xxModeInd_BK.getValue()) && !DETAIL_MODE_ADJUSTMENT.equals(aSMsg.xxModeInd_BK.getValue()) && !DETAIL_MODE_ONACOUNT.equals(aSMsg.xxModeInd_BK.getValue())) {

                if (ZYPCommonFunc.hasValue(aSMsg.arTrxNum)) {
                    inArApplyIntfcWrkMsg.invNum.setValue(aSMsg.arTrxNum.getValue());
                } else {
                    // do nothing
                }
                if (ZYPCommonFunc.hasValue(aSMsg.arTrxTpCd)) {
                    inArApplyIntfcWrkMsg.arTrxTpCd.setValue(aSMsg.arTrxTpCd.getValue());
                } else {
                    // do nothing
                }
                if (ZYPCommonFunc.hasValue(aSMsg.invTrxBalPk_BK)) {
                    inArApplyIntfcWrkMsg.invTrxBalPk.setValue(aSMsg.invTrxBalPk_BK.getValue());
                } else {
                    inArApplyIntfcWrkMsg.invTrxBalPk.setValue(BigDecimal.ZERO);
                }
                if (ZYPCommonFunc.hasValue(aSMsg.invTrxBalLastUpdTs_BK)) {
                    inArApplyIntfcWrkMsg.invTrxBalLastUpdTs.setValue(aSMsg.invTrxBalLastUpdTs_BK.getValue());
                } else {
                    // do nothing
                }
                if (ZYPCommonFunc.hasValue(aSMsg.invTrxBalTz_BK)) {
                    inArApplyIntfcWrkMsg.invTrxBalTz.setValue(aSMsg.invTrxBalTz_BK.getValue());
                } else {
                    // do nothing
                }
                if (ZYPCommonFunc.hasValue(aSMsg.tocCd_A1)) {
                    inArApplyIntfcWrkMsg.tocCd.setValue(aSMsg.tocCd_A1.getValue());
                } else {
                    // do nothing
                }
                if (ZYPCommonFunc.hasValue(aSMsg.coaProdCd_BK)) {
                    inArApplyIntfcWrkMsg.coaProdCd.setValue(aSMsg.coaProdCd_BK.getValue());
                } else {
                    // do nothing
                }
            } else {
                inArApplyIntfcWrkMsg.invTrxBalPk.setValue(BigDecimal.ZERO);
                if (ZYPCommonFunc.hasValue(aSMsg.tocCd_A1)) {
                    inArApplyIntfcWrkMsg.tocCd.setValue(aSMsg.tocCd_A1.getValue());
                } else {
                    // do nothing
                }
                if (ZYPCommonFunc.hasValue(aSMsg.coaProdCd_BK)) {
                    inArApplyIntfcWrkMsg.coaProdCd.setValue(aSMsg.coaProdCd_BK.getValue());
                } else {
                    // do nothing
                }
            }
        } else {
            // START 2022/04/22 D.Mamaril [QC#59333,MOD]
            //if (!DETAIL_MODE_ADJUSTMENT.equals(aSMsg.xxModeInd_BK.getValue()) {
            if (!DETAIL_MODE_ADJUSTMENT.equals(aSMsg.xxModeInd_BK.getValue()) && !DETAIL_MODE_REFUND.equals(aSMsg.xxModeInd_BK.getValue())) {
            // END 2022/04/22 D.Mamaril [QC#59333,MOD]

                if (ZYPCommonFunc.hasValue(aSMsg.arTrxNum)) {
                    inArApplyIntfcWrkMsg.invNum.setValue(aSMsg.arTrxNum.getValue());
                } else {
                    // do nothing
                }
                if (ZYPCommonFunc.hasValue(aSMsg.arTrxTpCd)) {
                    inArApplyIntfcWrkMsg.arTrxTpCd.setValue(aSMsg.arTrxTpCd.getValue());
                } else {
                    // do nothing
                }
                if (ZYPCommonFunc.hasValue(aSMsg.invTrxBalPk_BK)) {
                    inArApplyIntfcWrkMsg.invTrxBalPk.setValue(aSMsg.invTrxBalPk_BK.getValue());
                } else {
                    inArApplyIntfcWrkMsg.invTrxBalPk.setValue(BigDecimal.ZERO);
                }
                if (ZYPCommonFunc.hasValue(aSMsg.invTrxBalLastUpdTs_BK)) {
                    inArApplyIntfcWrkMsg.invTrxBalLastUpdTs.setValue(aSMsg.invTrxBalLastUpdTs_BK.getValue());
                } else {
                    // do nothing
                }
                if (ZYPCommonFunc.hasValue(aSMsg.invTrxBalTz_BK)) {
                    inArApplyIntfcWrkMsg.invTrxBalTz.setValue(aSMsg.invTrxBalTz_BK.getValue());
                } else {
                    // do nothing
                }
            } else {
                inArApplyIntfcWrkMsg.invTrxBalPk.setValue(BigDecimal.ZERO);
            }
            if (ZYPCommonFunc.hasValue(aSMsg.tocCd_A1)) {
                inArApplyIntfcWrkMsg.tocCd.setValue(aSMsg.tocCd_A1.getValue());
            } else {
                // do nothing
            }
            if (ZYPCommonFunc.hasValue(aSMsg.coaProdCd_BK)) {
                inArApplyIntfcWrkMsg.coaProdCd.setValue(aSMsg.coaProdCd_BK.getValue());
            } else {
                // do nothing
            }
        }

        inArApplyIntfcWrkMsg.crTrxBalPk.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.dealCcyCd.setValue(bizMsg.ccyCd.getValue());

        if (MODE_ENTRY.equals(bizMsg.xxModeInd_H1.getValue())) {
            inArApplyIntfcWrkMsg.dealApplyAmt.setValue(aSMsg.xxDealApplyAmtNum_A1.getValue());
        } else {
            inArApplyIntfcWrkMsg.dealApplyAmt.setValue(aSMsg.xxDealApplyAmtNum_A1.getValue().negate());
        }

        // START 2018/05/11 [QC#25509, DEL]
//        if (ZYPCommonFunc.hasValue(aSMsg.cashAppGlDt_A1)) {
//            inArApplyIntfcWrkMsg.cashAppGlDt.setValue(aSMsg.cashAppGlDt_A1.getValue());
//        } else {
//            // do nothing
//        }
        // END   2018/05/11 [QC#25509, DEL]

        if (ZYPCommonFunc.hasValue(aSMsg.cashDiscPct_A1)) {
            inArApplyIntfcWrkMsg.cashDiscPct.setValue(aSMsg.cashDiscPct_A1.getValue());
        } else {
            inArApplyIntfcWrkMsg.cashDiscPct.setValue(BigDecimal.ZERO);
        }

        if (ZYPCommonFunc.hasValue(aSMsg.dealCashDiscAmt_A1)) {
            if (MODE_ENTRY.equals(bizMsg.xxModeInd_H1.getValue())) {
                inArApplyIntfcWrkMsg.dealCashDiscAmt.setValue(aSMsg.dealCashDiscAmt_A1.getValue());
            } else {
                inArApplyIntfcWrkMsg.dealCashDiscAmt.setValue(aSMsg.dealCashDiscAmt_A1.getValue().negate());
            }
        } else {
            inArApplyIntfcWrkMsg.dealCashDiscAmt.setValue(BigDecimal.ZERO);
        }

        inArApplyIntfcWrkMsg.dealOnAcctCashAmt.setValue(BigDecimal.ZERO);

        if (ZYPCommonFunc.hasValue(aSMsg.arAdjNum_BK)) {
            inArApplyIntfcWrkMsg.arAdjNum.setValue(aSMsg.arAdjNum_BK.getValue());
        } else {
            // do nothing
        }

        if (DETAIL_MODE_DEDUCTION.equals(aSMsg.xxModeInd_BK.getValue())) {
            inArApplyIntfcWrkMsg.arAdjTrxTpCd.setValue(AR_ADJ_TRX_TP.DEDUCTION);
        } else if (DETAIL_MODE_ADJUSTMENT.equals(aSMsg.xxModeInd_BK.getValue())) {
            inArApplyIntfcWrkMsg.arAdjTrxTpCd.setValue(AR_ADJ_TRX_TP.ADJUSTMENT);
        } else if (DETAIL_MODE_ONACOUNT.equals(aSMsg.xxModeInd_BK.getValue())) {
            inArApplyIntfcWrkMsg.arAdjTrxTpCd.setValue(AR_TRX_TP.ON_ACCOUNT);
        // START 2022/04/22 D.Mamaril [QC#59333,ADD]
        } else if (DETAIL_MODE_REFUND.equals(aSMsg.xxModeInd_BK.getValue())) {
            inArApplyIntfcWrkMsg.arAdjTrxTpCd.setValue(AR_ADJ_TRX_TP.REFUND);
        // END 2022/04/22 D.Mamaril [QC#59333,ADD]
        } else {
            if (ZYPCommonFunc.hasValue(aSMsg.arAdjTpCd_A1)) {
                inArApplyIntfcWrkMsg.arAdjTrxTpCd.setValue(AR_ADJ_TRX_TP.ADJUSTMENT);
            } else {
                // do nothing
            }
        }

        if (ZYPCommonFunc.hasValue(aSMsg.arAdjTpCd_A1)) {
            inArApplyIntfcWrkMsg.arAdjTpCd.setValue(aSMsg.arAdjTpCd_A1.getValue());
        } else {
            inArApplyIntfcWrkMsg.arAdjTpCd.setValue(aSMsg.arAdjTpCd_A3.getValue());
        }

        // START 2022/04/22 D.Mamaril [QC#59333,MOD]
        //if (DETAIL_MODE_DEDUCTION.equals(aSMsg.xxModeInd_BK.getValue()) || DETAIL_MODE_ADJUSTMENT.equals(aSMsg.xxModeInd_BK.getValue()) || DETAIL_MODE_ONACOUNT.equals(aSMsg.xxModeInd_BK.getValue())) {
        if (DETAIL_MODE_DEDUCTION.equals(aSMsg.xxModeInd_BK.getValue()) || DETAIL_MODE_ADJUSTMENT.equals(aSMsg.xxModeInd_BK.getValue())
                || DETAIL_MODE_ONACOUNT.equals(aSMsg.xxModeInd_BK.getValue()) || DETAIL_MODE_REFUND.equals(aSMsg.xxModeInd_BK.getValue())) {
        // END 2022/04/22 D.Mamaril [QC#59333,MOD]
            if (MODE_ENTRY.equals(bizMsg.xxModeInd_H1.getValue())) {
                inArApplyIntfcWrkMsg.dealApplyAdjAmt.setValue(aSMsg.xxDealApplyAmtNum_A1.getValue());
            } else {
                inArApplyIntfcWrkMsg.dealApplyAdjAmt.setValue(aSMsg.xxDealApplyAmtNum_A1.getValue().negate());
            }
        } else {
            if (ZYPCommonFunc.hasValue(aSMsg.xxDealApplyAdjAmtNum_A1)) {
                if (MODE_ENTRY.equals(bizMsg.xxModeInd_H1.getValue())) {
                    inArApplyIntfcWrkMsg.dealApplyAdjAmt.setValue(aSMsg.xxDealApplyAdjAmtNum_A1.getValue());
                } else {
                    inArApplyIntfcWrkMsg.dealApplyAdjAmt.setValue(aSMsg.xxDealApplyAdjAmtNum_A1.getValue().negate());
                }
            } else {
                inArApplyIntfcWrkMsg.dealApplyAdjAmt.setValue(BigDecimal.ZERO);
            }
        }

        inArApplyIntfcWrkMsg.dealApplyAdjRsvdAmt.setValue(BigDecimal.ZERO);

        if (ZYPCommonFunc.hasValue(aSMsg.adjCmntTxt_BK)) {
            inArApplyIntfcWrkMsg.adjCmntTxt.setValue(aSMsg.adjCmntTxt_BK.getValue());
        } else {
            // do nothing
        }

        if (ZYPCommonFunc.hasValue(aSMsg.xxModeInd_BK) && DETAIL_MODE_DEDUCTION.equals(aSMsg.xxModeInd_BK.getValue()) || ZYPCommonFunc.hasValue(aSMsg.xxModeInd_BK) && DETAIL_MODE_ONACOUNT.equals(aSMsg.xxModeInd_BK.getValue())) {
            if (ZYPCommonFunc.hasValue(aSMsg.arCustRefNum)) {
                inArApplyIntfcWrkMsg.arCustRefNum.setValue(aSMsg.arCustRefNum.getValue());
            } else {
                // do nothing
            }
            // START 2018/07/19 Y.Matsui [QC#26871, ADD]
            if (DETAIL_MODE_ONACOUNT.equals(aSMsg.xxModeInd_BK.getValue())) {
                inArApplyIntfcWrkMsg.arCustRefNum.setValue(bizMsg.rcptChkNum_H1.getValue());
            }
            // END   2018/07/19 Y.Matsui [QC#26871, ADD]
        } else {
            // do nothing
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global area information
     * @return void
     */
    // Start 2022/11/30 R.Takau [QC#57252, MOD]
    //private void execBatch(NFCL2760CMsg bizMsg, NFCL2760SMsg globalMsg) {
    private void execBatch(NFCL2760CMsg bizMsg, NFCL2760SMsg globalMsg, boolean isEntry) {
    // End 2022/11/30 R.Takau [QC#57252, MOD]
    
        NFZC301001 api = new NFZC301001();
        NFZC301001PMsg apiMsg = new NFZC301001PMsg();
        apiMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd_H1.getValue());

        if (MODE_ENTRY.equals(bizMsg.xxModeInd_H1.getValue())) {
            String applyGrpKey = getApplyGrpKey(globalMsg);
            apiMsg.applyGrpKey.setValue(applyGrpKey);
        } else {
            for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
                
                if (ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(i).xxPgFlg_A1.getValue())) {
                    continue;
                }
                if (!ZYPConstant.CHKBOX_ON_Y.equals(globalMsg.A.no(i).xxChkBox.getValue())) {
                    if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).applyGrpKey_BK)) {
                        apiMsg.applyGrpKey.setValue(globalMsg.A.no(i).applyGrpKey_BK.getValue());
                    } else {
                        // do nothing
                    }
                } else {
                    // do nothing
                }
            }
        }
        apiMsg.dealSqNum.setValue("00000001");
        apiMsg.batDt.setValue(ZYPDateUtil.getBatProcDate(bizMsg.glblCmpyCd_H1.getValue()));
        api.execute(apiMsg, ONBATCH_TYPE.ONLINE);

        String rCd = apiMsg.getReturnCode();

        if (!API_RTNCD_NORMAL.equals(rCd)) {

            String message = "";

            if (API_RTNCD_UNPROCESSING.equals(rCd)) {
                message = UNPROCESSING;
            } else if (API_RTNCD_CASH_APPLICATION_ERROR.equals(rCd)) {
                message = CASH_APPLICATION_ERROR;
            } else if (API_RTNCD_EXCLUSION_ERROR.equals(rCd)) {
                message = EXCLUSION_ERROR;
            } else if (API_RTNCD_OTHERS_ERROR.equals(rCd)) {
                message = OTHERS_ERROR;
            } else {
                // do nothing
            }

            // START 2016/09/07 J.Kim [QC#13793,ADD]
            if (!S21ApiUtil.getXxMsgIdList(apiMsg).isEmpty()) {
                List<S21ApiMessage> xxMsgIdList = S21ApiUtil.getXxMsgList(apiMsg);
                S21ApiMessage msg = xxMsgIdList.get(0);
                message = message.concat(":").concat(getRtnMsg(msg.getXxMsgid(), msg.getXxMsgPrmArray()));
            }
            // END 2016/09/07 J.Kim [QC#13793,ADD]

            bizMsg.setMessageInfo("NFCM0001E", new String[] {message });
            return;
        } else {
            // do nothing
        }

        // START 2018/07/13 Y.Matsui [QC#26993, ADD]
        updateArBatRcptStatus(bizMsg);
        // END 2018/07/13 Y.Matsui [QC#26993, ADD]
        // START 2022/11/30 R.Takau [QC#57272,ADD]
        createArAdjCoaInfo(bizMsg, globalMsg, apiMsg.applyGrpKey.getValue(), isEntry);
        // END 2022/11/30 R.Takau [QC#57272,ADD]
    }

    /**
     * Get Return Message
     * @param msgId String
     * @param prm String[]
     * @return String
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
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global area information
     * @return void
     */
    private String getApplyGrpKey(NFCL2760SMsg globalMsg) {
        String applyGrpKey = null;

        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            if ( !(  ROW_STS.CASH_APPLICATION.toString().equals( NFCL2760CommonLogic.isStatusOfRow(globalMsg.A.no(i).xxChkBox.getValue(), globalMsg.A.no(i).xxArCashApplyStsTxt.getValue()) )  ) ) {
                continue;
            }
            applyGrpKey = globalMsg.A.no(i).applyGrpKey_BK.getValue();
            break;
        }
        return applyGrpKey;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message
     * @return boolean
     */
    private boolean checkReceiptNumber(NFCL2760CMsg bizMsg) {

        Map<String, String> inFindRcpDataMap = new HashMap<String, String>();
        inFindRcpDataMap.put("glblCmpyCd", bizMsg.glblCmpyCd_H1.getValue());
        inFindRcpDataMap.put("rcptNum", bizMsg.rcptNum.getValue());
        inFindRcpDataMap.put("arApplyTp_Adjustment", AR_APPLY_TP.ADJUSTMENT);
        inFindRcpDataMap.put("arAdjTrxTp_OnAccount", AR_ADJ_TRX_TP.ON_ACCOUNT);
        inFindRcpDataMap.put("arCashApplyStsCd_U", AR_CASH_APPLY_STS.UNAPPLIED);
        inFindRcpDataMap.put("arCashApplyStsCd_P", AR_CASH_APPLY_STS.PARTIAL);
        S21SsmEZDResult outRcptData = NFCL2760Query.getInstance().findRcptData(inFindRcpDataMap, bizMsg);

        if (RTNCD_NOT_FOUND.equals(outRcptData.getResultCode())) {
            bizMsg.setMessageInfo("NFCM0029E", null);
            return false;
        } else {
            // do nothing
        }

        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.voidFlg_H1.getValue())) {
            bizMsg.setMessageInfo("NFCM0037E", null);
            return false;
        } else {
            // do nothing
        }

        //if (!bizMsg.dealRfAmt.isClear() && !NFCL2760CommonLogic.isZero(bizMsg.dealRfAmt.getValue())) {
        //    bizMsg.setMessageInfo("NFCM0036E", null);
        //    return false;
        //} else {
        //    // do nothing
        //}

        if (MODE_ENTRY.equals(bizMsg.xxModeInd_H1.getValue())) {

            if (bizMsg.dealRcptRmngBalAmt.isClear() || NFCL2760CommonLogic.isZero(bizMsg.dealRcptRmngBalAmt.getValue())) {
                bizMsg.setMessageInfo("NFCM0034E", null);
                return false;
            } else {
                // do nothing
            }
        } else {
            // do nothing
        }

        return true;
    }

    private void changePageforError (NFCL2760CMsg bizMsg, NFCL2760SMsg globalMsg) {
    	int xxPageShowFromNum = bizMsg.xxPageShowFromNum.getValueInt();
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum_H1.getValueInt());

        int pagenationFrom = NFCL2760CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum_H1.getValueInt());
        if (pagenationFrom != -1) {
            NFCL2760CommonLogic.setBizMsgToGlobalMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);

            // All>>
            if (!SCRN_MODE_CANCEL.equals(bizMsg.xxModeInd_H1.getValue())) {
                boolean ret = NFCL2760CommonLogic.setArTrxAllLine(bizMsg, globalMsg);
                if (!ret) {
                    NFCL2760CommonLogic.setGlobalMsgToBizMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);
                    return;
                }
            }
        }

        bizMsg.xxPageShowFromNum.setValue(xxPageShowFromNum);

        // copy data from SMsg onto CMsg
        pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < globalMsg.A.getValidCount()) {
                EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(i - pagenationFrom);

        // set value to page nation items
        bizMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount());
        
        NFCL2760CommonLogic.setCalc(bizMsg, globalMsg);
    }

    
    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void PageJump(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL2760CMsg bizMsg = (NFCL2760CMsg) cMsg;
        NFCL2760SMsg globalMsg = (NFCL2760SMsg) sMsg;
        int xxPageShowFromNum = bizMsg.xxPageShowFromNum.getValueInt();
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum_H1.getValueInt());

        int pagenationFrom = NFCL2760CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum_H1.getValueInt());
        if (pagenationFrom != -1) {
            NFCL2760CommonLogic.setBizMsgToGlobalMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);

            // All>>
            if (!SCRN_MODE_CANCEL.equals(bizMsg.xxModeInd_H1.getValue())) {
                boolean ret = NFCL2760CommonLogic.setArTrxAllLine(bizMsg, globalMsg);
                if (!ret) {
                    NFCL2760CommonLogic.setGlobalMsgToBizMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);
                    return;
                }
            }
        }
    }

    // START 2018/07/13 Y.Matsui [QC#26993, ADD]
    private boolean updateArBatRcptStatus(NFCL2760CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.arBatRcptNm_H1.getValue())) {
            return true;
        }

        NFZC310001PMsg pMsg = new NFZC310001PMsg();
        pMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd_H1.getValue());
        pMsg.arBatRcptNm.setValue(bizMsg.arBatRcptNm_H1.getValue());
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
    // END 2018/07/13 Y.Matsui [QC#26993, ADD]
    
    // START 2022/11/30 R.Takau [QC#57272,ADD]
    private boolean createArAdjCoaInfo(NFCL2760CMsg bizMsg, NFCL2760SMsg globalMsg, String applyGrpKey, boolean isEntry) {
        
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            if (isEntry 
                    && ROW_STS.CASH_APPLICATION.toString().equals(NFCL2760CommonLogic.isStatusOfRow(globalMsg.A.no(i).xxChkBox.getValue(), globalMsg.A.no(i).xxArCashApplyStsTxt.getValue()))
                    && OTHER_CODE.equals(globalMsg.A.no(i).arAdjTpCd_A3.getValue())) {

                // get AR_APPLY_INTFC_WRK
                BigDecimal applyGrpSubPk = BigDecimal.ONE;
                if(ZYPCommonFunc.hasValue(globalMsg.A.no(i).applyGrpSubPk_BK.getValue())){
                    applyGrpSubPk = globalMsg.A.no(i).applyGrpSubPk_BK.getValue();
                }
                AR_APPLY_INTFC_WRKTMsg outApplyIntfcWrkMsg = findApplyIntfcWrk(bizMsg, applyGrpKey, applyGrpSubPk);
                
                // Separate 9 segment
                int coaIndx = 0;
                String[] coa = globalMsg.A.no(i).xxCmntTxt_A1.getValue().split("\\.");
                
                // AR_ADJ_COA_INFOTMsg -Create
                AR_ADJ_COA_INFOTMsg inArAdjCoaInfoTMsg = new AR_ADJ_COA_INFOTMsg();
                inArAdjCoaInfoTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd_H1.getValue());
                inArAdjCoaInfoTMsg.arAdjNum.setValue(outApplyIntfcWrkMsg.arAdjNum.getValue());
                inArAdjCoaInfoTMsg.adjCoaCmpyCd.setValue(coa[coaIndx++]);
                inArAdjCoaInfoTMsg.adjCoaBrCd.setValue(coa[coaIndx++]);
                inArAdjCoaInfoTMsg.adjCoaCcCd.setValue(coa[coaIndx++]);
                inArAdjCoaInfoTMsg.adjCoaAcctCd.setValue(coa[coaIndx++]);
                inArAdjCoaInfoTMsg.adjCoaProdCd.setValue(coa[coaIndx++]);
                inArAdjCoaInfoTMsg.adjCoaChCd.setValue(coa[coaIndx++]);
                inArAdjCoaInfoTMsg.adjCoaAfflCd.setValue(coa[coaIndx++]);
                inArAdjCoaInfoTMsg.adjCoaProjCd.setValue(coa[coaIndx++]);
                inArAdjCoaInfoTMsg.adjCoaExtnCd.setValue(coa[coaIndx++]);
                
                EZDTBLAccessor.create(inArAdjCoaInfoTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inArAdjCoaInfoTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo("NFCM0032E", null);
                    return false;
                } else{
                    continue;
                }
            }
        }
        return true;
    }
    
    private AR_APPLY_INTFC_WRKTMsg findApplyIntfcWrk(NFCL2760CMsg bizMsg, String applyGrpKey, BigDecimal applyGrpSubPk) {
        AR_APPLY_INTFC_WRKTMsg arApplyIntfcWrkParam = new AR_APPLY_INTFC_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(arApplyIntfcWrkParam.glblCmpyCd, bizMsg.glblCmpyCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(arApplyIntfcWrkParam.applyGrpKey, applyGrpKey);
        ZYPEZDItemValueSetter.setValue(arApplyIntfcWrkParam.applyGrpSubPk, applyGrpSubPk);

        AR_APPLY_INTFC_WRKTMsg arApplyIntfcWrkTMsg = (AR_APPLY_INTFC_WRKTMsg) EZDTBLAccessor.findByKey(arApplyIntfcWrkParam);
        if (arApplyIntfcWrkTMsg == null) {
            bizMsg.setMessageInfo("NFCM0079E", null);
            return null;
        }

        return arApplyIntfcWrkTMsg;
    }
    // END 2022/11/28 R.Takau [QC#57272,ADD]
}

