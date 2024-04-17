package business.blap.NFCL3130;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFCL3130.common.NFCL3130CommonLogic;
import business.blap.NFCL3130.constant.NFCL3130Constant.SCREEN_EVENT_NAME;
import business.db.AR_RCPT_SRCTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/11   Hitachi         K.Kojima        Update          QC#11576
 * 2016/07/14   Hitachi         K.Kojima        Update          QC#11576
 * 2016/12/01   Fujitsu         H.Ikeda         Update          QC#15823
 *</pre>
 */
public class NFCL3130BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if (SCREEN_EVENT_NAME.NFCL3130Scrn00_CMN_Submit.toString().equals(screenAplID)) {
                doProcess_NFCL3130_Submit(cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /***
     * 
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3130_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3130CMsg bizMsg = (NFCL3130CMsg) cMsg;
        NFCL3130SMsg globalMsg = (NFCL3130SMsg) sMsg;

        //AR_RCPT_SRC
        AR_RCPT_SRCTMsg insUpTMsg = new AR_RCPT_SRCTMsg();
        
        //Validation
        if (!NFCL3130CommonLogic.isVal(bizMsg, globalMsg, getGlobalCompanyCode())) {
            bizMsg.setMessageInfo("NFCM0834E", null);
            return;
        }

        if (ZYPCommonFunc.hasValue(bizMsg.arRcptSrcCd)) {
            //Update
        
            ZYPEZDItemValueSetter.setValue(insUpTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(insUpTMsg.arRcptSrcCd, bizMsg.arRcptSrcCd);

            insUpTMsg = (AR_RCPT_SRCTMsg) EZDTBLAccessor.findByKey(insUpTMsg);

            if ( insUpTMsg != null  ) {

                // START 2016/07/11 K.Kojima [QC#11576,ADD]
                ZYPEZDItemValueSetter.setValue(insUpTMsg.arRcptSrcNm, bizMsg.arRcptSrcNm);
                // END 2016/07/11 K.Kojima [QC#11576,ADD]
                ZYPEZDItemValueSetter.setValue(insUpTMsg.arRcptSrcDescTxt, bizMsg.arRcptSrcDescTxt);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.arRcptSrcTpCd, bizMsg.arRcptSrcTpCd_S1);
                //Cash
                ZYPEZDItemValueSetter.setValue(insUpTMsg.cashCoaCmpyCd, bizMsg.cashCoaCmpyCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.cashCoaAcctCd, bizMsg.cashCoaAcctCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.cashCoaAfflCd, bizMsg.cashCoaAfflCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.cashCoaBrCd, bizMsg.cashCoaBrCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.cashCoaCcCd, bizMsg.cashCoaCcCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.cashCoaChCd, bizMsg.cashCoaChCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.cashCoaExtnCd, bizMsg.cashCoaExtnCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.cashCoaProdCd, bizMsg.cashCoaProdCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.cashCoaProjCd, bizMsg.cashCoaProjCd);
                //Receipt
                ZYPEZDItemValueSetter.setValue(insUpTMsg.rcptConfCoaCmpyCd, bizMsg.rcptConfCoaCmpyCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.rcptConfCoaAcctCd, bizMsg.rcptConfCoaAcctCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.rcptConfCoaAfflCd, bizMsg.rcptConfCoaAfflCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.rcptConfCoaBrCd, bizMsg.rcptConfCoaBrCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.rcptConfCoaCcCd, bizMsg.rcptConfCoaCcCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.rcptConfCoaChCd, bizMsg.rcptConfCoaChCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.rcptConfCoaExtnCd, bizMsg.rcptConfCoaExtnCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.rcptConfCoaProdCd, bizMsg.rcptConfCoaProdCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.rcptConfCoaProjCd, bizMsg.rcptConfCoaProjCd);
                //Remmittance
                ZYPEZDItemValueSetter.setValue(insUpTMsg.remCoaCmpyCd, bizMsg.remCoaCmpyCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.remCoaAcctCd, bizMsg.remCoaAcctCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.remCoaAfflCd, bizMsg.remCoaAfflCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.remCoaBrCd, bizMsg.remCoaBrCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.remCoaCcCd, bizMsg.remCoaCcCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.remCoaChCd, bizMsg.remCoaChCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.remCoaExtnCd, bizMsg.remCoaExtnCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.remCoaProdCd, bizMsg.remCoaProdCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.remCoaProjCd, bizMsg.remCoaProjCd);
                //Unapplied
                ZYPEZDItemValueSetter.setValue(insUpTMsg.unaplCoaCmpyCd, bizMsg.unaplCoaCmpyCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.unaplCoaAcctCd, bizMsg.unaplCoaAcctCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.unaplCoaAfflCd, bizMsg.unaplCoaAfflCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.unaplCoaBrCd, bizMsg.unaplCoaBrCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.unaplCoaCcCd, bizMsg.unaplCoaCcCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.unaplCoaChCd, bizMsg.unaplCoaChCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.unaplCoaExtnCd, bizMsg.unaplCoaExtnCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.unaplCoaProdCd, bizMsg.unaplCoaProdCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.unaplCoaProjCd, bizMsg.unaplCoaProjCd);
                //Unidentified
                ZYPEZDItemValueSetter.setValue(insUpTMsg.unidCoaCmpyCd, bizMsg.unidCoaCmpyCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.unidCoaAcctCd, bizMsg.unidCoaAcctCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.unidCoaAfflCd, bizMsg.unidCoaAfflCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.unidCoaBrCd, bizMsg.unidCoaBrCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.unidCoaCcCd, bizMsg.unidCoaCcCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.unidCoaChCd, bizMsg.unidCoaChCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.unidCoaExtnCd, bizMsg.unidCoaExtnCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.unidCoaProdCd, bizMsg.unidCoaProdCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.unidCoaProjCd, bizMsg.unidCoaProjCd);
                //On Account
                ZYPEZDItemValueSetter.setValue(insUpTMsg.oaccCoaCmpyCd, bizMsg.oaccCoaCmpyCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.oaccCoaAcctCd, bizMsg.oaccCoaAcctCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.oaccCoaAfflCd, bizMsg.oaccCoaAfflCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.oaccCoaBrCd, bizMsg.oaccCoaBrCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.oaccCoaCcCd, bizMsg.oaccCoaCcCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.oaccCoaChCd, bizMsg.oaccCoaChCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.oaccCoaExtnCd, bizMsg.oaccCoaExtnCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.oaccCoaProdCd, bizMsg.oaccCoaProdCd);
                ZYPEZDItemValueSetter.setValue(insUpTMsg.oaccCoaProjCd, bizMsg.oaccCoaProjCd);

                if (!bizMsg.ezCancelFlag.getValue().toString().equals("Y")) {
                    S21FastTBLAccessor.removeLogical(new AR_RCPT_SRCTMsg[]{insUpTMsg});
                } else {
                    EZDTBLAccessor.update(insUpTMsg);
                    // START 2016/12/01 H.Ikeda [QC#15823,ADD]
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insUpTMsg.getReturnCode())) {
                        bizMsg.setMessageInfo("NFCM0615E", new String[] {"AR_RCPT_SRC"});
                        return;
                    }
                    // END   2016/12/01 H.Ikeda [QC#15823,ADD]
                }
                // START 2016/07/11 K.Kojima [QC#11576,ADD]
                // update DS_BANK_ACCT
                boolean updateResult = NFCL3130CommonLogic.updateDsBankAcct(getGlobalCompanyCode(), bizMsg.dsBankAcctPk.getValue(), bizMsg.arRcptSrcCd.getValue(), true);
                if (updateResult == false) {
                    bizMsg.setMessageInfo("NFCM0032E", null);
                    return;
                }
                // END 2016/07/11 K.Kojima [QC#11576,ADD]
            }
        } else {

            //Max Receipt Sorce Code
            BigDecimal rcptMasNum = NFCL3130CommonLogic.findMaxCd(bizMsg, globalMsg, getGlobalCompanyCode());
            // START 2016/07/14 K.Kojima [QC#11576,ADD]
            String arRcptSrcCd = ZYPCommonFunc.leftPad(rcptMasNum.toString(), 2, "0");
            // END 2016/07/14 K.Kojima [QC#11576,ADD]

            //New

            ZYPEZDItemValueSetter.setValue(insUpTMsg.glblCmpyCd, getGlobalCompanyCode());
            // START 2016/07/14 K.Kojima [QC#11576,MOD]
            // ZYPEZDItemValueSetter.setValue(insUpTMsg.arRcptSrcCd,
            // rcptMasNum.toString());
            // ZYPEZDItemValueSetter.setValue(bizMsg.arRcptSrcCd,
            // rcptMasNum.toString());
            ZYPEZDItemValueSetter.setValue(insUpTMsg.arRcptSrcCd, arRcptSrcCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.arRcptSrcCd, arRcptSrcCd);
            // END 2016/07/14 K.Kojima [QC#11576,MOD]
            ZYPEZDItemValueSetter.setValue(insUpTMsg.arRcptSrcNm, bizMsg.arRcptSrcNm);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.arRcptSrcSortNum, rcptMasNum);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.arRcptSrcDescTxt, bizMsg.arRcptSrcDescTxt);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.arRcptSrcTpCd, bizMsg.arRcptSrcTpCd_S1);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.arRcptManCratFlg, "Y");
            
            //Cash
            ZYPEZDItemValueSetter.setValue(insUpTMsg.cashCoaCmpyCd, bizMsg.cashCoaCmpyCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.cashCoaAcctCd, bizMsg.cashCoaAcctCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.cashCoaAfflCd, bizMsg.cashCoaAfflCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.cashCoaBrCd, bizMsg.cashCoaBrCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.cashCoaCcCd, bizMsg.cashCoaCcCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.cashCoaChCd, bizMsg.cashCoaChCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.cashCoaExtnCd, bizMsg.cashCoaExtnCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.cashCoaProdCd, bizMsg.cashCoaProdCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.cashCoaProjCd, bizMsg.cashCoaProjCd);
            //Receipt
            ZYPEZDItemValueSetter.setValue(insUpTMsg.rcptConfCoaCmpyCd, bizMsg.rcptConfCoaCmpyCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.rcptConfCoaAcctCd, bizMsg.rcptConfCoaAcctCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.rcptConfCoaAfflCd, bizMsg.rcptConfCoaAfflCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.rcptConfCoaBrCd, bizMsg.rcptConfCoaBrCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.rcptConfCoaCcCd, bizMsg.rcptConfCoaCcCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.rcptConfCoaChCd, bizMsg.rcptConfCoaChCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.rcptConfCoaExtnCd, bizMsg.rcptConfCoaExtnCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.rcptConfCoaProdCd, bizMsg.rcptConfCoaProdCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.rcptConfCoaProjCd, bizMsg.rcptConfCoaProjCd);
            //Remmittance
            ZYPEZDItemValueSetter.setValue(insUpTMsg.remCoaCmpyCd, bizMsg.remCoaCmpyCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.remCoaAcctCd, bizMsg.remCoaAcctCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.remCoaAfflCd, bizMsg.remCoaAfflCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.remCoaBrCd, bizMsg.remCoaBrCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.remCoaCcCd, bizMsg.remCoaCcCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.remCoaChCd, bizMsg.remCoaChCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.remCoaExtnCd, bizMsg.remCoaExtnCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.remCoaProdCd, bizMsg.remCoaProdCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.remCoaProjCd, bizMsg.remCoaProjCd);
            //Unapplied
            ZYPEZDItemValueSetter.setValue(insUpTMsg.unaplCoaCmpyCd, bizMsg.unaplCoaCmpyCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.unaplCoaAcctCd, bizMsg.unaplCoaAcctCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.unaplCoaAfflCd, bizMsg.unaplCoaAfflCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.unaplCoaBrCd, bizMsg.unaplCoaBrCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.unaplCoaCcCd, bizMsg.unaplCoaCcCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.unaplCoaChCd, bizMsg.unaplCoaChCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.unaplCoaExtnCd, bizMsg.unaplCoaExtnCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.unaplCoaProdCd, bizMsg.unaplCoaProdCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.unaplCoaProjCd, bizMsg.unaplCoaProjCd);
            //Unidentified
            ZYPEZDItemValueSetter.setValue(insUpTMsg.unidCoaCmpyCd, bizMsg.unidCoaCmpyCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.unidCoaAcctCd, bizMsg.unidCoaAcctCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.unidCoaAfflCd, bizMsg.unidCoaAfflCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.unidCoaBrCd, bizMsg.unidCoaBrCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.unidCoaCcCd, bizMsg.unidCoaCcCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.unidCoaChCd, bizMsg.unidCoaChCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.unidCoaExtnCd, bizMsg.unidCoaExtnCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.unidCoaProdCd, bizMsg.unidCoaProdCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.unidCoaProjCd, bizMsg.unidCoaProjCd);
            //On Account
            ZYPEZDItemValueSetter.setValue(insUpTMsg.oaccCoaCmpyCd, bizMsg.oaccCoaCmpyCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.oaccCoaAcctCd, bizMsg.oaccCoaAcctCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.oaccCoaAfflCd, bizMsg.oaccCoaAfflCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.oaccCoaBrCd, bizMsg.oaccCoaBrCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.oaccCoaCcCd, bizMsg.oaccCoaCcCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.oaccCoaChCd, bizMsg.oaccCoaChCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.oaccCoaExtnCd, bizMsg.oaccCoaExtnCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.oaccCoaProdCd, bizMsg.oaccCoaProdCd);
            ZYPEZDItemValueSetter.setValue(insUpTMsg.oaccCoaProjCd, bizMsg.oaccCoaProjCd);
            
            //Insert
            EZDTBLAccessor.insert(insUpTMsg);
            
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insUpTMsg.getReturnCode())) {
                bizMsg.setMessageInfo("NFCM0032E", null);
                return;
            }

            // START 2016/07/11 K.Kojima [QC#11576,ADD]
            // update DS_BANK_ACCT
            boolean updateResult = NFCL3130CommonLogic.updateDsBankAcct(getGlobalCompanyCode(), bizMsg.dsBankAcctPk.getValue(), bizMsg.arRcptSrcCd.getValue(), false);
            if (updateResult == false) {
                bizMsg.setMessageInfo("NFCM0032E", null);
                return;
            }
            // END 2016/07/11 K.Kojima [QC#11576,ADD]
        }
        // START 2016/07/11 K.Kojima [QC#11576,ADD]
        bizMsg.setMessageInfo("NZZM0002I");
        // END 2016/07/11 K.Kojima [QC#11576,ADD]
    }
}
