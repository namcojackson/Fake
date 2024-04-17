/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0340.common;

import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL0340.NSAL0340BMsg;
import business.servlet.NSAL0340.NSAL0340Bean;
import business.servlet.NSAL0340.NSAL0340_ABMsg;
import business.servlet.NSAL0340.constant.NSAL0340Constant;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/09   Fujitsu         T.Yoshida       Create          N/A
 *</pre>
 */
public class NSAL0340CommonLogic {

    /**
     * init Control CommonButton
     * @param scrnAppli EZDCommonHandler
     */
    public static void initControlCommonButton(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonProperties(NSAL0340Constant.BTN_CMN_SAVE_BTN_NM, "", NSAL0340Constant.BTN_CMN_SAVE_LABEL, 0, null);
        scrnAppli.setButtonProperties(NSAL0340Constant.BTN_CMN_SUBMIT_BTN_NM, NSAL0340Constant.BTN_CMN_SUBMIT_EVENT_NM, NSAL0340Constant.BTN_CMN_SUBMIT_LABEL, 0, null);
        scrnAppli.setButtonProperties(NSAL0340Constant.BTN_CMN_APPLY_BTN_NM, "", NSAL0340Constant.BTN_CMN_APPLY_LABEL, 0, null);
        scrnAppli.setButtonProperties(NSAL0340Constant.BTN_CMN_APPROVE_BTN_NM, "", NSAL0340Constant.BTN_CMN_APPROVE_LABEL, 0, null);
        scrnAppli.setButtonProperties(NSAL0340Constant.BTN_CMN_REJECT_BTN_NM, "", NSAL0340Constant.BTN_CMN_REJECT_LABEL, 0, null);
        scrnAppli.setButtonProperties(NSAL0340Constant.BTN_CMN_DOWNLOAD_BTN_NM, "", NSAL0340Constant.BTN_CMN_DOWNLOAD_LABEL, 0, null);
        scrnAppli.setButtonProperties(NSAL0340Constant.BTN_CMN_DELETE_BTN_NM, "", NSAL0340Constant.BTN_CMN_DELETE_LABEL, 0, null);
        scrnAppli.setButtonProperties(NSAL0340Constant.BTN_CMN_CLEAR_BTN_NM, "", NSAL0340Constant.BTN_CMN_CLEAR_LABEL, 0, null);
        scrnAppli.setButtonProperties(NSAL0340Constant.BTN_CMN_RESET_BTN_NM, NSAL0340Constant.BTN_CMN_RESET_EVENT_NM, NSAL0340Constant.BTN_CMN_RESET_LABEL, 0, null);
        scrnAppli.setButtonProperties(NSAL0340Constant.BTN_CMN_RETURN_BTN_NM, NSAL0340Constant.BTN_CMN_RETURN_EVENT_NM, NSAL0340Constant.BTN_CMN_RETURN_LABEL, 0, null);
    }

    /**
     * init CommonButton
     * @param scrnAppli EZDCommonHandler
     * @param isActive boolean
     */
    public static void initCommonButton(EZDCommonHandler scrnAppli, boolean isActive) {

        scrnAppli.setButtonEnabled(NSAL0340Constant.BTN_CMN_SUBMIT_BTN_NM, isActive);
        scrnAppli.setButtonEnabled(NSAL0340Constant.BTN_CMN_RESET_BTN_NM, isActive);
        scrnAppli.setButtonEnabled(NSAL0340Constant.BTN_CMN_RETURN_BTN_NM, true);
    }

    /**
     * control Inactive Field
     * @param scrnMsg NSAL0340BMsg
     */
    public static void inActiveDtlField(NSAL0340BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSAL0340_ABMsg abMsg = scrnMsg.A.no(i);
            abMsg.skipRecovTpCd_A0.setInputProtected(true);
        }
    }

    /**
     * set Table's Back Color
     * @param scrnMsg NSAL0340BMsg
     */
    public static final void setTblBackColor(NSAL0340BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(NSAL0340Constant.SCREEN_ID_00, scrnMsg);
        tblColor.clearRowsBG(NSAL0340Bean.A, scrnMsg.A);
        tblColor.setAlternateRowsBG(NSAL0340Bean.A, scrnMsg.A);
    }

    /**
     * hasRegistFuncId
     * @return boolean true:upedate false:reference
     */
    public static boolean hasUpdateFuncId() {

        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(NSAL0340Constant.BUSINESS_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException("You can't operate Skip Month(" + NSAL0340Constant.BUSINESS_ID + "). UserID is -> " + S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }

        for (String func : funcList) {
            if (NSAL0340Constant.AUTH_CONTR_MGR.equals(func)) {
                return true;
            }
        }

        return false;
    }
}
