/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFCL0740;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NFCL0740.common.NFCL0740CommonLogic;
import business.blap.NFCL0740.constant.NFCL0740Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;

/**
 *<pre>
 * write-Off Request Creation
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/01   Fujitsu         T.Tanaka        Create          Initial
 * 2016/02/29   CSAI            K.Uramori       Update          Add submit process
 * 2016/05/13   Fujitsu         C.Naito         Update          QC#7971
 * 2016/07/08   Hitachi         K.Kojima        Update          QC#8784
 *</pre>
 */
public class NFCL0740BL02 extends S21BusinessHandler implements NFCL0740Constant, ZYPConstant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NFCL0740_INIT".equals(screenAplID)) {

                doProcess_NFCL0740_INIT(cMsg, sMsg);
            } else if ("NFCL0740_NMAL6760".equals(screenAplID)) {

                doProcess_NFCL0740_NMAL6760(cMsg, sMsg);
            } else if ("NFCL0740Scrn00_Click_SetCustomerName1".equals(screenAplID)) {

                // START 2016/07/08 K.Kojima [QC#8784,MOD]
                // doProcess_NFCL0740Scrn00_Click_SetCustomerName(cMsg,
                // sMsg, "H1");
                doProcess_NFCL0740Scrn00_Click_SetCustomerName(cMsg, sMsg);
                // END 2016/07/08 K.Kojima [QC#8784,MOD]
                // START 2016/07/08 K.Kojima [QC#8784,DEL]
                // } else if
                // ("NFCL0740Scrn00_Click_SetCustomerName2".equals(screenAplID))
                // {
                //
                // doProcess_NFCL0740Scrn00_Click_SetCustomerName(cMsg,
                // sMsg, "H2");
                // END 2016/07/08 K.Kojima [QC#8784,DEL]
            } else if ("NFCL0740Scrn00_CMN_Clear".equals(screenAplID)) {

                doProcess_NFCL0740Scrn00_CMN_Clear(cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * 
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL0740_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL0740CMsg bizMsg = (NFCL0740CMsg) cMsg;

        bizMsg.glblCmpyCd.setValue(getGlobalCompanyCode());

        S21UserInfo userInfo = getContextUserInfo();
        bizMsg.usrId.setValue(userInfo.getUserId());

        // [QC#7971] UPDATE start
        //Create AR_ADJ_TP(Activity)Pulldown
        NFCL0740CommonLogic.createPulldownListArAdjTp(bizMsg.glblCmpyCd.getValue(), bizMsg);
        // ZYPCodeDataUtil.createPulldownList(AR_ADJ_TP.class, bizMsg.arAdjTpCd_LC, bizMsg.arAdjTpDescTxt_LD);
        // [QC#7971] UPDATE end

//        ZYPCodeDataUtil.createPulldownList("AR_ADJ_RSN", bizMsg.arAdjRsnCd_LC, bizMsg.arAdjRsnNm_LD);
        //ZYPCodeDataUtil.createPulldownList(INV_TP.class, bizMsg.invTpCd_LC, bizMsg.invTpNm_LD);
        // since debit memo doesn't need to be shown, create the dropdown list manually
        NFCL0740CommonLogic.createPulldownForInvCls(bizMsg);

        NFCL0740CommonLogic.createPulldownListArAdjRsn(bizMsg.glblCmpyCd.getValue(), bizMsg);

        // get values from varchar const
        String arTrxTpInv = ZYPCodeDataUtil.getVarCharConstValue("AR_WRT_OFF_INV", bizMsg.glblCmpyCd.getValue());
        String arTrxTpCm = ZYPCodeDataUtil.getVarCharConstValue("AR_WRT_OFF_CRM", bizMsg.glblCmpyCd.getValue());

        ZYPEZDItemValueSetter.setValue(bizMsg.varCharConstVal_I, arTrxTpInv);
        ZYPEZDItemValueSetter.setValue(bizMsg.varCharConstVal_C, arTrxTpCm);

    }

    /**
     * 
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL0740_NMAL6760(EZDCMsg cMsg, EZDSMsg sMsg) {
    }

    // START 2016/07/08 K.Kojima [QC#8784,DEL]
    // /**
    // *
    // * @param cMsg
    // * @param sMsg
    // */
    // private void
    // doProcess_NFCL0740Scrn00_Click_SetCustomerName(EZDCMsg cMsg,
    // EZDSMsg sMsg, String nm) {
    //
    // NFCL0740CMsg bizMsg = (NFCL0740CMsg) cMsg;
    //
    // String dsAcctNum;
    // if (nm.equals("H1")) {
    // dsAcctNum = bizMsg.dsAcctNum_H1.getValue();
    // } else {
    // dsAcctNum = bizMsg.dsAcctNum_H2.getValue();
    // }
    //
    // String dsAcctNm =
    // NFCL0740CommonLogic.getDsAcctNm(bizMsg.glblCmpyCd.getValue(),
    // dsAcctNum);
    //
    // if (dsAcctNm == null) {
    // if (nm.equals("H1")) {
    // bizMsg.dsAcctNum_H1.setErrorInfo(1, "NFCM0763E");
    // } else {
    // bizMsg.dsAcctNum_H2.setErrorInfo(1, "NFCM0763E");
    // }
    // } else {
    // if (nm.equals("H1")) {
    // bizMsg.dsAcctNm_H1.setValue(dsAcctNm);
    // } else {
    // bizMsg.dsAcctNm_H2.setValue(dsAcctNm);
    // }
    // }
    // }
    // END 2016/07/08 K.Kojima [QC#8784,DEL]

    // START 2016/07/08 K.Kojima [QC#8784,ADD]
    /**
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL0740Scrn00_Click_SetCustomerName(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL0740CMsg bizMsg = (NFCL0740CMsg) cMsg;
        String dsAcctNum = bizMsg.dsAcctNum_H1.getValue();
        String dsAcctNm = NFCL0740CommonLogic.getDsAcctNm(bizMsg.glblCmpyCd.getValue(), dsAcctNum);
        if (dsAcctNm == null) {
            bizMsg.dsAcctNum_H1.setErrorInfo(1, "NFCM0763E");
        } else {
            bizMsg.dsAcctNm_H1.setValue(dsAcctNm);
        }
    }

    // END 2016/07/08 K.Kojima [QC#8784,ADD]

    // START 2016/07/08 K.Kojima [QC#8784,DEL]
    // /**
    // *
    // * @param cMsg
    // * @param sMsg
    // */
    // private void
    // doProcess_NFCL0740Scrn00_Click_SetCustomerName2(EZDCMsg cMsg,
    // EZDSMsg sMsg) {
    //
    // NFCL0740CMsg bizMsg = (NFCL0740CMsg) cMsg;
    //
    // String dsAcctNm =
    // NFCL0740CommonLogic.getDsAcctNm(bizMsg.glblCmpyCd.getValue(),
    // bizMsg.dsAcctNum_H2.getValue());
    //
    // if (dsAcctNm == null) {
    // bizMsg.dsAcctNum_H2.setErrorInfo(1, "NFCM0763E");
    // } else {
    // bizMsg.dsAcctNm_H2.setValue(dsAcctNm);
    // }
    // }
    // END 2016/07/08 K.Kojima [QC#8784,DEL]

    private void doProcess_NFCL0740Scrn00_CMN_Clear(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL0740CMsg bizMsg = (NFCL0740CMsg) cMsg;

        bizMsg.arAdjRsnCd_H1.clear();
        bizMsg.arAdjTpCd_H1.clear();
        bizMsg.dealRmngBalGrsAmt_H1.clear();
        bizMsg.dealRmngBalGrsAmt_H2.clear();
        bizMsg.arTrxNum_H1.clear();
        bizMsg.arTrxNum_H2.clear();
        bizMsg.invDueDt_H1.clear();
        bizMsg.invDueDt_H2.clear();
        bizMsg.dsAcctNum_H1.clear();
        // START 2016/07/08 K.Kojima [QC#8784,DEL]
        // bizMsg.dsAcctNum_H2.clear();
        // END 2016/07/08 K.Kojima [QC#8784,DEL]
        bizMsg.dsAcctNm_H1.clear();
        // START 2016/07/08 K.Kojima [QC#8784,DEL]
        // bizMsg.dsAcctNm_H2.clear();
        // END 2016/07/08 K.Kojima [QC#8784,DEL]
        bizMsg.invTpCd_H1.clear();
        bizMsg.xxChkBox_H.clear();
        bizMsg.xxRadioBtn.setValue(1);
    }

}