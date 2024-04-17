/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0800.common;

import java.util.List;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

import parts.servletcommon.EZDCommonHandler;
import static business.servlet.NSAL0800.constant.NSAL0800Constant.*;
import business.servlet.NSAL0800.NSAL0800BMsg;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/11   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2017/08/15   CITS            T.Kikuhara      Update          QC#18799(L3)
 * 2018/05/17   Hitachi         K.Yamada        Update          QC#26180
 * 2018/05/31   CITS            M.Naito         Update          QC#22887
 * 2022/03/22   Hitachi         D.Yoshida       Update          QC#59683
 *</pre>
 */
public class NSAL0800CommonLogic {

    /**
     * init Control CommonButton
     * @param scrnAppli EZDCommonHandler
     */
    public static void initControlCommonButton(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonProperties(BTN_CMN_SAVE_BTN_NM, "", BTN_CMN_SAVE_LABEL, 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_SUBMIT_BTN_NM, BTN_CMN_SUBMIT_EVENT_NM, BTN_CMN_SUBMIT_LABEL, 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_APPLY_BTN_NM, "", BTN_CMN_APPLY_LABEL, 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_APPROVE_BTN_NM, "", BTN_CMN_APPROVE_LABEL, 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_REJECT_BTN_NM, "", BTN_CMN_REJECT_LABEL, 0, null);
        //Start 2018/05/17 QC#26180 MOD
        scrnAppli.setButtonProperties(BTN_CMN_DOWNLOAD_BTN_NM, BTN_CMN_DOWNLOAD_EVENT_NM, BTN_CMN_DOWNLOAD_LABEL, 1, null);
        scrnAppli.setButtonProperties(BTN_CMN_DELETE_BTN_NM, "", BTN_CMN_DELETE_LABEL, 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_CLEAR_BTN_NM, "", BTN_CMN_CLEAR_LABEL, 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_RESET_BTN_NM, BTN_CMN_RESET_EVENT_NM, BTN_CMN_RESET_LABEL, 1, null);
        scrnAppli.setButtonProperties(BTN_CMN_RETURN_BTN_NM, BTN_CMN_RETURN_EVENT_NM, BTN_CMN_RETURN_LABEL, 1, null);
        //End 2018/05/17 QC#26180 MOD
    }

    /**
     * control CommonButton
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NSAL0800BMsg
     * @param isActive boolean
     */
    public static void controlCommonButton(EZDCommonHandler scrnAppli, NSAL0800BMsg scrnMsg, boolean isActive) {

        scrnAppli.setButtonEnabled(BTN_CMN_SUBMIT_BTN_NM, isActive);
        //Start 2018/05/17 QC#26180 DEL
        //scrnAppli.setButtonEnabled(BTN_CMN_RESET_BTN_NM, true);
        //scrnAppli.setButtonEnabled(BTN_CMN_RETURN_BTN_NM, true);
        // QC#18799 ADD START
        //scrnAppli.setButtonEnabled(BTN_CMN_DOWNLOAD_BTN_NM, true);
        // QC#18799 ADD END
        //End 2018/05/17 QC#26180 DEL
    }


    /**
     * has Register FuncId
     * @return boolean true:upedate false:reference
     */
    public static boolean hasUpdateFuncId() {

        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BUSINESS_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException("You can't operate Model Maintenance(" + BUSINESS_ID + "). UserID is -> " + S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }

        for (String func : funcList) {
            if (AUTH_UPDATE.equals(func)) {
                return true;
            }
        }

        return false;
    }

    /**
     * check Header
     * @param scrnMsg NSAL0800BMsg
     */
    public static final void checkItem(NSAL0800BMsg scrnMsg) {

        // QC#18799 ADD and UPD START
        scrnMsg.addCheckItem(scrnMsg.contrIntfcSrcTpCd_HS);
        scrnMsg.addCheckItem(scrnMsg.dsContrClsCd_HS);
        scrnMsg.addCheckItem(scrnMsg.svcLineBizCd_HS);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_H1);
        scrnMsg.addCheckItem(scrnMsg.enblFlg_H1);
        scrnMsg.addCheckItem(scrnMsg.leaseCmpyCd_A1);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm_A1);
        scrnMsg.addCheckItem(scrnMsg.mtrReadMethCd_AS);
        scrnMsg.addCheckItem(scrnMsg.dsContrSlsCrTpCd_AS);
        scrnMsg.addCheckItem(scrnMsg.contrAutoRnwTpCd_BS);
        scrnMsg.addCheckItem(scrnMsg.rnwPrcMethCd_BS);
        scrnMsg.addCheckItem(scrnMsg.befEndRnwDaysAot_B1);
        scrnMsg.addCheckItem(scrnMsg.basePrcUpRatio_B1);
        scrnMsg.addCheckItem(scrnMsg.mtrPrcUpRatio_B1);
        scrnMsg.addCheckItem(scrnMsg.rnwExtMthAot_B1);
        scrnMsg.addCheckItem(scrnMsg.startReadVldTpCd_CS);
        scrnMsg.addCheckItem(scrnMsg.mtrReadWinDaysAot_C1);
        scrnMsg.addCheckItem(scrnMsg.bllgRollOverRatio_C1);
        scrnMsg.addCheckItem(scrnMsg.mtrReadWinMlyDaysAot_C1);
        scrnMsg.addCheckItem(scrnMsg.mtrReadWinOthDaysAot_C1);
        scrnMsg.addCheckItem(scrnMsg.bllgLimitAmt_C1);
        scrnMsg.addCheckItem(scrnMsg.allwDataCrctFlg_D1);
        scrnMsg.addCheckItem(scrnMsg.mtrEstTpCd_ES);
        scrnMsg.addCheckItem(scrnMsg.bllgCycleCd_E2);
        scrnMsg.addCheckItem(scrnMsg.bllgCycleCd_E4);
        // START 2022/03/22 [QC#59683, DEL]
//        scrnMsg.addCheckItem(scrnMsg.invSeptBaseUsgFlg_E1);
        // END   2022/03/22 [QC#59683, DEL]
        scrnMsg.addCheckItem(scrnMsg.prcAllocByMachQtyFlg_E1);
        scrnMsg.addCheckItem(scrnMsg.printDtlFlg_E1);
        scrnMsg.addCheckItem(scrnMsg.contrUplftTpCd_FS);
        scrnMsg.addCheckItem(scrnMsg.uplftPrcMethCd_FS);
        scrnMsg.addCheckItem(scrnMsg.befEndUplftDaysAot_F1);
        scrnMsg.addCheckItem(scrnMsg.uplftBasePrcUpRatio_F1);
        scrnMsg.addCheckItem(scrnMsg.uplftMtrPrcUpRatio_F1);
        scrnMsg.addCheckItem(scrnMsg.bllgThruTpCd_GS);
        scrnMsg.addCheckItem(scrnMsg.bllgThruDt_G1);
        scrnMsg.addCheckItem(scrnMsg.contrCloDay_GS);
        scrnMsg.addCheckItem(scrnMsg.contrBllgDay_GS);
        scrnMsg.addCheckItem(scrnMsg.stubPrepFromTpCd_IS);
        scrnMsg.addCheckItem(scrnMsg.stubPrepThruTpCd_IS);
        scrnMsg.addCheckItem(scrnMsg.autoEffFleetFlg_I1);
        scrnMsg.addCheckItem(scrnMsg.autoEffAggrFlg_I1);
        // QC#18799 ADD and UPD END
        // START 2022/03/22 [QC#59683, ADD]
        scrnMsg.addCheckItem(scrnMsg.dsInvTgtrTpCd_E1);
        // END   2022/03/22 [QC#59683, ADD]

        scrnMsg.putErrorScreen();
    }

    // START 2018/05/31 M.Naito [QC#22887, ADD]
    /**
     * control ScreenFields
     * @param scrnAppli EZDCommonHandler
     */
    public static void controlScreenFields(NSAL0800BMsg scrnMsg) {

        scrnMsg.basePrcUpRatio_B1.setAppFracDigit(2);
        scrnMsg.mtrPrcUpRatio_B1.setAppFracDigit(2);
        scrnMsg.uplftBasePrcUpRatio_F1.setAppFracDigit(2);
        scrnMsg.uplftMtrPrcUpRatio_F1.setAppFracDigit(2);
    }
    // END 2018/05/31 M.Naito [QC#22887, ADD]
}
