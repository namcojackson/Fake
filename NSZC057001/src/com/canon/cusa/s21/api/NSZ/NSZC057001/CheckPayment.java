/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC057001;

import static com.canon.cusa.s21.api.NSZ.NSZC057001.constant.NSZC057001Constant.*;
import static com.canon.cusa.s21.api.NSZ.NSZC057001.NSZC057001CommonLogic.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDMsg;

import com.canon.cusa.s21.api.NSZ.NSZC041001.NSZC041001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_VLD_STS;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_CR_CARD_POTMsgArray;
import business.db.DS_CONTR_VLD_RSLT_WRKTMsg;
import business.db.DS_CONTR_VLD_RSLT_WRKTMsgArray;
import business.db.PMT_TERM_CASH_DISCTMsg;
import business.parts.NSZC041001PMsg;
import business.parts.NSZC057001PMsg;

/**
 * <pre>
 * Contract Validation
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/09   Hitachi         A.Kohinata      Create          N/A
 * 2017/03/07   Hitachi         K.Ochiai        Update          QC#17453
 * 2018/02/23   Hitachi         M.Kidokoro      Update          QC#24237
 * 2018/04/20   Hitachi         K.Ochiai        Update          QC#25757
 * 2018/08/20   Hitachi         K.Kojima        Update          QC#27789
 * </pre>
 */
public class CheckPayment {

    /**
     * checkCustCreditHold
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkCustCreditHold(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
        setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
        // START 2018/08/20 K.Kojima [QC#27789,ADD]
        if (!DS_CONTR_STS.ENTERED.equals(dsContrTMsg.dsContrStsCd.getValue())) {
            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
            return rtrnTMsgArray;
        }
        // END 2018/08/20 K.Kojima [QC#27789,DEL]

        NSZC041001PMsg pMsg = createNSZC041001PMsg(param, dsContrTMsg);
        // START 2018/04/20 K.Ochiai [QC#25757 MOD]
        new NSZC041001().execute(pMsg, mainClass.onBatchType);
        // END 2018/04/20 K.Ochiai [QC#25757 MOD]
        if (pMsg.A.getValidCount() > 0) {
            setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0780E, new String[] {dsContrTMsg.dsAcctNum.getValue() }), DS_CONTR_PK, dsContrTMsg.dsContrPk.getValue());
        }
        EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
        rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);

        return rtrnTMsgArray;
    }

    private static NSZC041001PMsg createNSZC041001PMsg(NSZC057001PMsg param, DS_CONTRTMsg dsContrTMsg) {
        NSZC041001PMsg pMsg = new NSZC041001PMsg();
        setValue(pMsg.glblCmpyCd, param.glblCmpyCd);
        setValue(pMsg.slsDt, param.slsDt);
        setValue(pMsg.usrId, S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        setValue(pMsg.billToAcctNum, dsContrTMsg.dsAcctNum);
        setValue(pMsg.billToCustCd, dsContrTMsg.altPayerCustCd);
        return pMsg;
    }

    /**
     * checkCreditCardAuthorization
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkCreditCardAuthorization(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);

        if (!isPmtCc(param.glblCmpyCd.getValue(), dsContrTMsg.pmtTermCashDiscCd.getValue())) {
            return rtrnTMsgArray;
        }

        setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
        setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
        // get credit card info
        DS_CONTR_CR_CARD_POTMsgArray dsContrCrCardPoTMsgArray = getDsContrCrCardPo(param.glblCmpyCd.getValue(), dsContrTMsg.dsContrPk.getValue());
        // START 2018/02/23 M.Kidokoro [QC#24237,ADD]
        if (dsContrCrCardPoTMsgArray.getValidCount() == 0) {
            setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0784E, new String[] {"Payment Term:CC", "Credit Card Cust Ref Num" }), DS_CONTR_PK, dsContrTMsg.dsContrPk.getValue());
        }
        // END 2018/02/23 M.Kidokoro [QC#24237,ADD]
        for (int i = 0; i < dsContrCrCardPoTMsgArray.getValidCount(); i++) {
            // START 2017/03/07 K.Ochiai [QC#17453, ADD]
            if (!ZYPCommonFunc.hasValue(dsContrCrCardPoTMsgArray.no(i).crCardCustRefNum)) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0784E, new String[] {"Payment Term:CC", "Credit Card Cust Ref Num" }), DS_CONTR_PK, dsContrCrCardPoTMsgArray.no(i).dsContrPk.getValue());
            }
            // END 2017/03/07 K.Ochiai [QC#17453, ADD]
//            DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsg = dsContrCrCardPoTMsgArray.no(i);
//            NWZC203001PMsg pMsg = createNWZC203001PMsg(param, dsContrCrCardPoTMsg);
//            // TODO call Credit Card Validation API
////            new NWZC203001().execute(pMsg, mainClass.onBatchType);
//            // TODO error status code (90:Authorized Failed)
//            if (pMsg.crCardAuthStsCd.getValue().equals("90")) {
//                // TODO set error info
//
//                break;
//            }
        }
        EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
        rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);

        return rtrnTMsgArray;
    }

    private static boolean isPmtCc(String glblCmpyCd, String pmtTermCashDiscCd) {
        PMT_TERM_CASH_DISCTMsg pmtTermCashDiscTMsg = new PMT_TERM_CASH_DISCTMsg();
        setValue(pmtTermCashDiscTMsg.glblCmpyCd, glblCmpyCd);
        setValue(pmtTermCashDiscTMsg.pmtTermCashDiscCd, pmtTermCashDiscCd);
        pmtTermCashDiscTMsg = (PMT_TERM_CASH_DISCTMsg) S21ApiTBLAccessor.findByKey(pmtTermCashDiscTMsg);
        if (pmtTermCashDiscTMsg != null && pmtTermCashDiscTMsg.pmtCcFlg.getValue().equals(ZYPConstant.FLG_ON_Y)) {
            return true;
        }
        return false;
    }

//    private static NWZC203001PMsg createNWZC203001PMsg(NSZC057001PMsg param, DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsg) {
//        NWZC203001PMsg pMsg = new NWZC203001PMsg();
//        // TODO set input parameter
//        setValue(pMsg.glblCmpyCd, param.glblCmpyCd);
//        setValue(pMsg.xxProcMd, XX_PROC_MD_03);
//        setValue(pMsg.slsDt, param.slsDt);
//        setValue(pMsg.crCardCustRefNum, dsContrCrCardPoTMsg.crCardCustRefNum);
//        return pMsg;
//    }
}
