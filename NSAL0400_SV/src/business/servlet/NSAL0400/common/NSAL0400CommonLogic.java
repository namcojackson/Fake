/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0400.common;

import static business.servlet.NSAL0400.constant.NSAL0400Constant.*;

import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL0400.NSAL0400BMsg;
import business.servlet.NSAL0400.NSAL0400_ABMsg;
import business.servlet.NSAL0400.NSAL0400_BBMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
//START 2017/07/26 M.Kidokoro [QC#18122, ADD]
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
//END 2017/07/26 M.Kidokoro [QC#18122, ADD]
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/19   Fujitsu         M.Yamada        Create          N/A
 * 2017/07/26   Hitachi         M.Kidokoro      Update          QC#18122
 * 2017/10/04   Hitachi         K.Kim           Update          QC#21557
 * 2022/02/04   Hitachi         K.Kitachi       Update          QC#59684
 * 2022/09/15   CITS            E.Sanchez       Update          QC#59775
 *</pre>
 */
public class NSAL0400CommonLogic {

    /**
     * setRowColors
     * @param scrnMsg NSAL0400BMsg
     */
    public static void setRowColors(NSAL0400BMsg scrnMsg) {

//        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
//        try {
//            EZDBMsgArray table = (EZDBMsgArray) scrnMsg.getClass().getField("A").get(scrnMsg);
//            tblColor.setAlternateRowsBG("A", table);
//        } catch (Throwable e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
    }

    /**
     * initControlCommonButton
     * @param scrnAppli EZDCommonHandler
     */
    public static void initControlCommonButton(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonProperties("btn1", "", BTN_LBL.SAVE.getBtnLbl(), 0, null);
        scrnAppli.setButtonProperties("btn2", "CMN_Submit", BTN_LBL.SUBMIT.getBtnLbl(), 0, null);
        scrnAppli.setButtonProperties("btn3", "", BTN_LBL.APPLY.getBtnLbl(), 0, null);
        scrnAppli.setButtonProperties("btn4", "", BTN_LBL.APPROVE.getBtnLbl(), 0, null);
        scrnAppli.setButtonProperties("btn5", "", BTN_LBL.REJECT.getBtnLbl(), 0, null);
        scrnAppli.setButtonProperties("btn6", "", BTN_LBL.DOWNLOAD.getBtnLbl(), 0, null);
        scrnAppli.setButtonProperties("btn7", "", BTN_LBL.DELETE.getBtnLbl(), 0, null);
        scrnAppli.setButtonProperties("btn8", "", BTN_LBL.CLEAR.getBtnLbl(), 0, null);
        scrnAppli.setButtonProperties("btn9", "CMN_Reset", BTN_LBL.RESET.getBtnLbl(), 0, null);

        scrnAppli.setButtonProperties("btn10", "CMN_Return", BTN_LBL.RETURN.getBtnLbl(), 0, null);

        scrnAppli.setButtonEnabled(NSAL0400Btn.Apply.btnName(), true);
        scrnAppli.setButtonEnabled(NSAL0400Btn.Review.btnName(), true);
    }

    /**
     * initCommonButton
     * @param scrnAppli EZDCommonHandler
     */
    public static void initCommonButton(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonEnabled("btn2", true);
        scrnAppli.setButtonEnabled("btn9", true);
        scrnAppli.setButtonEnabled("btn10", true);
    }

    /**
     * hasUpdateFuncId
     * @return boolean
     */
    public static boolean hasUpdateFuncId() {

        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BIZ_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException("You can't operate Termination(" + BIZ_ID + "). UserID is -> " //
                    + S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }

        for (String func : funcList) {
            if (FUNC_CD.CONTR_MGR.getFuncCd().equals(func)) {
                return true;
            }
        }

        return false;
    }

    /**
     * hasUpdateFunc
     * @param scrnMsg NSAL0400BMsg
     * @return boolean
     */
//    public static boolean hasUpdateFunc(NSAL0400BMsg scrnMsg) {
//        return ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxTrmnFlg_UP.getValue());
//    }

    // START 2022/02/04 K.Kitachi [QC#59684, ADD]
    /**
     * hasAllPerTrmnFuncId
     * @return boolean
     */
    public static boolean hasAllPerTrmnFuncId() {

        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BIZ_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException("You can't operate Termination(" + BIZ_ID + "). UserID is -> " //
                    + S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }

        for (String func : funcList) {
            if (FUNC_CD.ALL_PER_TRMN.getFuncCd().equals(func)) {
                return true;
            }
        }

        return false;
    }
    // END 2022/02/04 K.Kitachi [QC#59684, ADD]

    /**
     * control Inactive Field
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NSAL0400BMsg
     * @param hasUpdateFunc boolean
     */
    public static void protectFieldsAndButtons(//
            EZDCommonHandler scrnAppli //
            , NSAL0400BMsg scrnMsg) {

        if (!hasUpdateFuncId()) {
            protectAllFieldsAndButtons(scrnAppli, scrnMsg);
        }

        // contract header level
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            //always disabled fields
            NSAL0400_BBMsg bbMsg = scrnMsg.B.no(i);
            bbMsg.xxScrItem40Txt_B.setInputProtected(true); //Contract#
            bbMsg.contrVrsnEffFromDt_B.setInputProtected(true);
            bbMsg.contrVrsnEffThruDt_B.setInputProtected(true);
            bbMsg.dsAcctNm_B.setInputProtected(true);
            bbMsg.dsContrCtrlStsNm_B.setInputProtected(true);
            bbMsg.trmnTotAmt_B.setInputProtected(true);

            if (!DS_CONTR_CATG.FLEET.equals(bbMsg.dsContrCatgCd_B.getValue())) {
                bbMsg.trmnOvrdTotAmt_B.setInputProtected(true);
            }
        }
        
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            //always disabled fields
            NSAL0400_ABMsg abMsg = scrnMsg.A.no(i);
            abMsg.mdseCd_AD.setInputProtected(true);
            abMsg.serNum_AD.setInputProtected(true);
            abMsg.t_MdlNm_AD.setInputProtected(true);
            abMsg.xxComnScrColValTxt_AD.setInputProtected(true); //Ship to address
            abMsg.dsContrCtrlStsNm_AD.setInputProtected(true);
            abMsg.contrEffFromDt_AD.setInputProtected(true);
            abMsg.contrEffThruDt_AD.setInputProtected(true);
            abMsg.mtrReadDt_AD.setInputProtected(true);
            abMsg.trmnTotAmt_AD.setInputProtected(true);
            abMsg.xxGenlFldAreaTxt_AD.setInputProtected(true);

            // START 2017/07/26 M.Kidokoro [QC#18122, ADD]
            if (!ZYPConstant.FLG_ON_Y.equals(abMsg.contrTrmnAvalFlg_AD.getValue())) {
                abMsg.xxChkBox_AD.setInputProtected(true);
            }
            // END 2017/07/26 M.Kidokoro [QC#18122, ADD]
            if (!DS_CONTR_CTRL_STS.ACTIVE.equals(abMsg.dsContrCtrlStsCd_AD.getValue())
                    && !DS_CONTR_CTRL_STS.SINGED.equals(abMsg.dsContrCtrlStsCd_AD.getValue())
                         // START 2017/10/04 K.Kim [QC#21557, ADD]
                         && !ZYPConstant.FLG_ON_Y.equals(abMsg.contrTrmnAvalFlg_AD.getValue())) {
                         // END 2017/10/04 K.Kim [QC#21557, ADD]
                // START 2017/07/26 M.Kidokoro [QC#18122, DEL]
//                abMsg.xxChkBox_AD.setInputProtected(true);
                // END 2017/07/26 M.Kidokoro [QC#18122, DEL]
                abMsg.contrCloDt_AD.setInputProtected(true);
                abMsg.trmnOvrdTotAmt_AD.setInputProtected(true);
                abMsg.supprCrFlg_AD.setInputProtected(true);
                // START 2022/02/04 K.Kitachi [QC#59684, ADD]
                abMsg.contrTrmnFlg_AD.setInputProtected(true);
                // END 2022/02/04 K.Kitachi [QC#59684, ADD]
            }

            // START 2022/09/15 E.Sanchez [QC#59775, ADD]
            if (ZYPCommonFunc.hasValue(abMsg.contrCloDt_DB)) {
                abMsg.xxChkBox_AD.setInputProtected(true);
                abMsg.contrCloDt_AD.setInputProtected(true);
                abMsg.trmnOvrdTotAmt_AD.setInputProtected(true);
                abMsg.supprCrFlg_AD.setInputProtected(true);
            }
            // END 2022/09/15 E.Sanchez [QC#59775, ADD]

            if (DS_CONTR_CATG.FLEET.equals(abMsg.dsContrCatgCd_AH.getValue())) {
                abMsg.trmnOvrdTotAmt_AD.setInputProtected(true);
                abMsg.supprCrFlg_AD.setInputProtected(true);
                // START 2022/02/04 K.Kitachi [QC#59684, ADD]
                abMsg.contrTrmnFlg_AD.setInputProtected(true);
                // END 2022/02/04 K.Kitachi [QC#59684, ADD]
            }

        }

        if (scrnMsg.A.getValidCount() <= 0) {
            protectAllFieldsAndButtons(scrnAppli, scrnMsg);
        }

        // START 2022/02/04 K.Kitachi [QC#59684, ADD]
        if (!hasAllPerTrmnFuncId()) {
            scrnMsg.contrTrmnFlg_H.setInputProtected(true);
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                NSAL0400_BBMsg bbMsg = scrnMsg.B.no(i);
                bbMsg.contrTrmnFlg_B.setInputProtected(true);
            }
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                NSAL0400_ABMsg abMsg = scrnMsg.A.no(i);
                abMsg.contrTrmnFlg_AD.setInputProtected(true);
            }
        }
        // END 2022/02/04 K.Kitachi [QC#59684, ADD]
    }

    /**
     * setBtnProperties
     * @param scrnAppli EZDCommonHandler
     * @param isReviewable boolean
     * @param isTerminatable boolean
     * @param isCancelable boolean
     */
//    public static void setBtnProperties(//
//            EZDCommonHandler scrnAppli //
//            , boolean isReviewable //
//            , boolean isTerminatable //
//            , boolean isCancelable) {
//
//        scrnAppli.setButtonEnabled(NSAL0400Btn.SelectAll.btnName(), isReviewable || isCancelable);
//        scrnAppli.setButtonEnabled(NSAL0400Btn.UnSelectAll.btnName(), isReviewable || isCancelable);
//
//        scrnAppli.setButtonEnabled(NSAL0400Btn.Apply.btnName(), isReviewable || isCancelable);
//        scrnAppli.setButtonEnabled(NSAL0400Btn.Review.btnName(), isReviewable);
//        scrnAppli.setButtonEnabled(NSAL0400Btn.Terminate.btnName(), isTerminatable);
//        scrnAppli.setButtonEnabled(NSAL0400Btn.Cancel.btnName(), isCancelable);
//    }

    /**
     * isReview
     * @param reviewFlg String
     * @return boolean
     */
//    public static boolean isReview(String reviewFlg) {
//        return ZYPConstant.FLG_ON_Y.equals(reviewFlg);
//    }

    /**
     * commonAddCheckItem
     * @param scrnMsg NSAL0400BMsg
     */
    public static void commonAddCheckItem(NSAL0400BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSAL0400_ABMsg abMsg = scrnMsg.A.no(i);
            scrnMsg.addCheckItem(abMsg.contrCloDt_AD);
            scrnMsg.addCheckItem(abMsg.trmnOvrdTotAmt_AD);
        }
        scrnMsg.addCheckItem(scrnMsg.svcCmntTxt_F);
    }

    private static void protectAllFieldsAndButtons( EZDCommonHandler scrnAppli, NSAL0400BMsg scrnMsg) {
        scrnMsg.contrCloDt_H.setInputProtected(true);
        scrnMsg.supprCrFlg_H.setInputProtected(true);
        // START 2022/02/04 K.Kitachi [QC#59684, ADD]
        scrnMsg.contrTrmnFlg_H.setInputProtected(true);
        // END 2022/02/04 K.Kitachi [QC#59684, ADD]
        scrnMsg.svcMemoRsnCd_FS.setInputProtected(true);
        scrnMsg.svcCmntTxt_F.setInputProtected(true);
        scrnAppli.setButtonEnabled(BTN_LBL.SUBMIT.getBtnLbl(), false);
        scrnAppli.setButtonEnabled(NSAL0400Btn.Apply.btnName(), false);
        scrnAppli.setButtonEnabled(NSAL0400Btn.Review.btnName(), false);

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NSAL0400_BBMsg bbMsg = scrnMsg.B.no(i);
            bbMsg.contrCloDt_B.setInputProtected(true);
            bbMsg.trmnOvrdTotAmt_B.setInputProtected(true);
            bbMsg.supprCrFlg_B.setInputProtected(true);
            // START 2022/02/04 K.Kitachi [QC#59684, ADD]
            bbMsg.contrTrmnFlg_B.setInputProtected(true);
            // END 2022/02/04 K.Kitachi [QC#59684, ADD]
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSAL0400_ABMsg abMsg = scrnMsg.A.no(i);
            abMsg.xxChkBox_AD.setInputProtected(true);
            abMsg.contrCloDt_AD.setInputProtected(true);
            abMsg.trmnOvrdTotAmt_AD.setInputProtected(true);
            abMsg.supprCrFlg_AD.setInputProtected(true);
            // START 2022/02/04 K.Kitachi [QC#59684, ADD]
            abMsg.contrTrmnFlg_AD.setInputProtected(true);
            // END 2022/02/04 K.Kitachi [QC#59684, ADD]
        }

        
    }

}
