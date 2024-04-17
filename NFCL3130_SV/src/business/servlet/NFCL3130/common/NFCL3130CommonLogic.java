package business.servlet.NFCL3130.common;

import java.util.List;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NFCL3130.NFCL3130BMsg;
import business.servlet.NFCL3130.constant.NFCL3130Constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/11   Hitachi         K.Kojima        Update          QC#11576
 * 2016/12/26   Fujitsu         H.Ikeda         Update          QC#12865
 *</pre>
 */
public class NFCL3130CommonLogic implements NFCL3130Constant {
	/**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg   NFDL0010BMsg
     */
    public static void initialize(EZDCommonHandler scrnAppli, NFCL3130BMsg scrnMsg) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        // Start 2016/12/26 H.Ikeda [QC#12865,MOD]
        if (hasUpdateFuncId(scrnMsg)) {
            scrnAppli.setButtonProperties("btn2", "CMN_Submit", "Submit", 1, null);
        } else {
            scrnAppli.setButtonProperties("btn2", "CMN_Submit", "Submit", 0, null);
        }
        // End   2016/12/26 H.Ikeda [QC#12865,MOD]
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn6", "", "Download", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn8", "CMN_Clear", "Clear", 1, null);
        scrnAppli.setButtonProperties("btn9", "CMN_Reset", "Reset", 0, null);
        scrnAppli.setButtonProperties("btn10", "CMN_Return", "Return", 1, null);
        
        scrnMsg.arRcptSrcNm.setInputProtected(false);
        scrnMsg.arRcptSrcDescTxt.setInputProtected(false);
        scrnMsg.arRcptSrcTpCd_S1.setInputProtected(false);
        scrnMsg.ezCancelFlag.setInputProtected(false);
        scrnMsg.dsBankAcctNm.setInputProtected(true);
        scrnMsg.xxScrItem61Txt_C1.setInputProtected(true);
        scrnMsg.xxScrItem61Txt_C2.setInputProtected(true);
        scrnMsg.xxScrItem61Txt_C3.setInputProtected(true);
        scrnMsg.xxScrItem61Txt_C4.setInputProtected(true);
        scrnMsg.xxScrItem61Txt_C5.setInputProtected(true);
        scrnMsg.xxScrItem61Txt_C6.setInputProtected(true);
        scrnMsg.xxSignaDataDescTxt.setInputProtected(true);

    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg   NFCL3130BMsg
     */
    public static String nineSegCoaLayout(NFCL3130BMsg scrnMsg) {

        String strCoaCmpyCd = "";
        String strCoaBrCd   = "";
        String strCoaCcCd   = "";
        String strCoaAcctCd = "";
        String strCoaPrdCd  = "";
        String strCoaChCd   = "";
        String strCoaAfflCd = "";
        String strCoaProjCd = "";
        String strCoaExtnCd = "";

        if (scrnMsg.appFuncId.getValue().equals(COA_EVENT.NFCL3130_CASH.toString())) {
            //Cash COA Return
            if (ZYPCommonFunc.hasValue(scrnMsg.cashCoaCmpyCd)) {
                strCoaCmpyCd = scrnMsg.cashCoaCmpyCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.cashCoaBrCd)) {
                strCoaBrCd = scrnMsg.cashCoaBrCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.cashCoaCcCd)) {
                strCoaCcCd = scrnMsg.cashCoaCcCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.cashCoaAcctCd)) {
                strCoaAcctCd = scrnMsg.cashCoaAcctCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.cashCoaProdCd)) {
                strCoaPrdCd = scrnMsg.cashCoaProdCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.cashCoaChCd)) {
                strCoaChCd = scrnMsg.cashCoaChCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.cashCoaAfflCd)) {
                strCoaAfflCd = scrnMsg.cashCoaAfflCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.cashCoaProjCd)) {
                strCoaProjCd = scrnMsg.cashCoaProjCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.cashCoaExtnCd)) {
                strCoaExtnCd = scrnMsg.cashCoaExtnCd.getValue();
            }
        } else if (scrnMsg.appFuncId.getValue().equals(COA_EVENT.NFCL3130_RCPT.toString())) {
            //Receipt COA Return
            if (ZYPCommonFunc.hasValue(scrnMsg.rcptConfCoaCmpyCd)) {
                strCoaCmpyCd = scrnMsg.rcptConfCoaCmpyCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.rcptConfCoaBrCd)) {
                strCoaBrCd = scrnMsg.rcptConfCoaBrCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.rcptConfCoaCcCd)) {
                strCoaCcCd = scrnMsg.rcptConfCoaCcCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.rcptConfCoaAcctCd)) {
                strCoaAcctCd = scrnMsg.rcptConfCoaAcctCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.rcptConfCoaProdCd)) {
                strCoaPrdCd = scrnMsg.rcptConfCoaProdCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.rcptConfCoaChCd)) {
                strCoaChCd = scrnMsg.rcptConfCoaChCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.rcptConfCoaAfflCd)) {
                strCoaAfflCd = scrnMsg.rcptConfCoaAfflCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.rcptConfCoaProjCd)) {
                strCoaProjCd = scrnMsg.rcptConfCoaProjCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.rcptConfCoaExtnCd)) {
                strCoaExtnCd = scrnMsg.rcptConfCoaExtnCd.getValue();
            }
        } else if (scrnMsg.appFuncId.getValue().equals(COA_EVENT.NFCL3130_RMTC.toString())) {
            //Remmittance COA Return
            if (ZYPCommonFunc.hasValue(scrnMsg.remCoaCmpyCd)) {
                strCoaCmpyCd = scrnMsg.remCoaCmpyCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.remCoaBrCd)) {
                strCoaBrCd = scrnMsg.remCoaBrCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.remCoaCcCd)) {
                strCoaCcCd = scrnMsg.remCoaCcCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.remCoaAcctCd)) {
                strCoaAcctCd = scrnMsg.remCoaAcctCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.remCoaProdCd)) {
                strCoaPrdCd = scrnMsg.remCoaProdCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.remCoaChCd)) {
                strCoaChCd = scrnMsg.remCoaChCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.remCoaAfflCd)) {
                strCoaAfflCd = scrnMsg.remCoaAfflCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.remCoaProjCd)) {
                strCoaProjCd = scrnMsg.remCoaProjCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.remCoaExtnCd)) {
                strCoaExtnCd = scrnMsg.remCoaExtnCd.getValue();
            }
        } else if (scrnMsg.appFuncId.getValue().equals(COA_EVENT.NFCL3130_UNAP.toString())) {
            //Unapplied COA Return
            if (ZYPCommonFunc.hasValue(scrnMsg.unaplCoaCmpyCd)) {
                strCoaCmpyCd = scrnMsg.unaplCoaCmpyCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.unaplCoaBrCd)) {
                strCoaBrCd = scrnMsg.unaplCoaBrCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.unaplCoaCcCd)) {
                strCoaCcCd = scrnMsg.unaplCoaCcCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.unaplCoaAcctCd)) {
                strCoaAcctCd = scrnMsg.unaplCoaAcctCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.unaplCoaProdCd)) {
                strCoaPrdCd = scrnMsg.unaplCoaProdCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.unaplCoaChCd)) {
                strCoaChCd = scrnMsg.unaplCoaChCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.unaplCoaAfflCd)) {
                strCoaAfflCd = scrnMsg.unaplCoaAfflCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.unaplCoaProjCd)) {
                strCoaProjCd = scrnMsg.unaplCoaProjCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.unaplCoaExtnCd)) {
                strCoaExtnCd = scrnMsg.unaplCoaExtnCd.getValue();
            }
        } else if (scrnMsg.appFuncId.getValue().equals(COA_EVENT.NFCL3130_UNDF.toString())) {
            //Unidentified COA Return
            if (ZYPCommonFunc.hasValue(scrnMsg.unidCoaCmpyCd)) {
                strCoaCmpyCd = scrnMsg.unidCoaCmpyCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.unidCoaBrCd)) {
                strCoaBrCd = scrnMsg.unidCoaBrCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.unidCoaCcCd)) {
                strCoaCcCd = scrnMsg.unidCoaCcCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.unidCoaAcctCd)) {
                strCoaAcctCd = scrnMsg.unidCoaAcctCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.unidCoaProdCd)) {
                strCoaPrdCd = scrnMsg.unidCoaProdCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.unidCoaChCd)) {
                strCoaChCd = scrnMsg.unidCoaChCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.unidCoaAfflCd)) {
                strCoaAfflCd = scrnMsg.unidCoaAfflCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.unidCoaProjCd)) {
                strCoaProjCd = scrnMsg.unidCoaProjCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.unidCoaExtnCd)) {
                strCoaExtnCd = scrnMsg.unidCoaExtnCd.getValue();
            }
        } else if (scrnMsg.appFuncId.getValue().equals(COA_EVENT.NFCL3130_OACC.toString())) {
            //Unidentified COA Return
            if (ZYPCommonFunc.hasValue(scrnMsg.oaccCoaCmpyCd)) {
                strCoaCmpyCd = scrnMsg.oaccCoaCmpyCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.oaccCoaBrCd)) {
                strCoaBrCd = scrnMsg.oaccCoaBrCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.oaccCoaCcCd)) {
                strCoaCcCd = scrnMsg.oaccCoaCcCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.oaccCoaAcctCd)) {
                strCoaAcctCd = scrnMsg.oaccCoaAcctCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.oaccCoaProdCd)) {
                strCoaPrdCd = scrnMsg.oaccCoaProdCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.oaccCoaChCd)) {
                strCoaChCd = scrnMsg.oaccCoaChCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.oaccCoaAfflCd)) {
                strCoaAfflCd = scrnMsg.oaccCoaAfflCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.oaccCoaProjCd)) {
                strCoaProjCd = scrnMsg.oaccCoaProjCd.getValue();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.oaccCoaExtnCd)) {
                strCoaExtnCd = scrnMsg.oaccCoaExtnCd.getValue();
            }
        }

        return strCoaCmpyCd + "." + strCoaBrCd + "." + strCoaCcCd + "." + strCoaAcctCd + "." + strCoaPrdCd  + "." + strCoaChCd + "." + strCoaAfflCd + "." + strCoaProjCd + "." + strCoaExtnCd;
    }

    // START 2016/07/11 K.Kojima [QC#11576,ADD]
    /**
     * @param scrnMsg NFCL3130BMsg
     */
    public static void checkItem(NFCL3130BMsg scrnMsg) {
        if(!ZYPCommonFunc.hasValue(scrnMsg.dsBankAcctNm)) {
            scrnMsg.dsBankAcctNm.setErrorInfo(1, ZZM9000E, new String[]{"Bank Account"});
        }
    }
    // END 2016/07/11 K.Kojima [QC#11576,ADD]

    // Start 2016/12/26 H.Ikeda [QC#12865,ADD]
    /**
     * hasUpdateFuncId
     * @return boolean
     */
    private static boolean hasUpdateFuncId(NFCL3130BMsg scrnMsg) {
        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();

        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(BIZ_ID);
        if (funcList == null || funcList.isEmpty()) {
            scrnMsg.setMessageInfo(ZZSM4300E, new String[] {userProfSvc.getContextUserInfo().getUserId()});
            return false;
        }
        if (funcList.contains(FUNC_ID_UPDATE)) {
            return true;
        }
        return false;
    }
    // End  2016/12/26 H.Ikeda [QC#12865,ADD]
}
