/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0110.common;

import static business.servlet.NWCL0110.constant.NWCL0110Constant.SCRN_ID;

import java.util.List;

import parts.servletcommon.EZDApplicationContext;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NWCL0110.NWCL0110BMsg;
import business.servlet.NWCL0110.NWCL0110Bean;
import business.servlet.NWCL0110.NWCL0110_ABMsg;
import business.servlet.NWCL0110.constant.NWCL0110Constant;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/24   Fujitsu         H.Nagashima     Create          N/A
 * 2017/02/27   Fujitsu         K.Ishizuka      Create          QC#16035
 *</pre>
 */
public class NWCL0110CommonLogic {

    /**
     * init Control CommonButton
     * @param scrnAppli EZDCommonHandler
     */
    public static void initControlCommonButton(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonProperties(NWCL0110Constant.BTN_CMN_SAVE_BTN_NM, "", NWCL0110Constant.BTN_CMN_SAVE_LABEL, 0, null);
        scrnAppli.setButtonProperties(NWCL0110Constant.BTN_CMN_SUBMIT_BTN_NM, NWCL0110Constant.BTN_CMN_SUBMIT_EVENT_NM, NWCL0110Constant.BTN_CMN_SUBMIT_LABEL, 0, null);
        scrnAppli.setButtonProperties(NWCL0110Constant.BTN_CMN_APPLY_BTN_NM, "", NWCL0110Constant.BTN_CMN_APPLY_LABEL, 0, null);
        scrnAppli.setButtonProperties(NWCL0110Constant.BTN_CMN_APPROVE_BTN_NM, "", NWCL0110Constant.BTN_CMN_APPROVE_LABEL, 0, null);
        scrnAppli.setButtonProperties(NWCL0110Constant.BTN_CMN_REJECT_BTN_NM, "", NWCL0110Constant.BTN_CMN_REJECT_LABEL, 0, null);
        scrnAppli.setButtonProperties(NWCL0110Constant.BTN_CMN_DOWNLOAD_BTN_NM, "", NWCL0110Constant.BTN_CMN_DOWNLOAD_LABEL, 0, null);
        scrnAppli.setButtonProperties(NWCL0110Constant.BTN_CMN_DELETE_BTN_NM, "", NWCL0110Constant.BTN_CMN_DELETE_LABEL, 0, null);
        scrnAppli.setButtonProperties(NWCL0110Constant.BTN_CMN_CLEAR_BTN_NM, NWCL0110Constant.BTN_CMN_CLEAR_EVENT_NM, NWCL0110Constant.BTN_CMN_CLEAR_LABEL, 0, null);
        scrnAppli.setButtonProperties(NWCL0110Constant.BTN_CMN_RESET_BTN_NM, NWCL0110Constant.BTN_CMN_RESET_EVENT_NM, NWCL0110Constant.BTN_CMN_RESET_LABEL, 0, null);
        scrnAppli.setButtonProperties(NWCL0110Constant.BTN_CMN_RETURN_BTN_NM, NWCL0110Constant.BTN_CMN_RETURN_EVENT_NM, NWCL0110Constant.BTN_CMN_RETURN_LABEL, 0, null);
    }

    /**
     * control CommonButton
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NWCL0110BMsg
     * @param isActive boolean
     */
    public static void controlCommonButton(EZDCommonHandler scrnAppli, NWCL0110BMsg scrnMsg, boolean isActive) {

        scrnAppli.setButtonEnabled(NWCL0110Constant.BTN_CMN_SUBMIT_BTN_NM, isActive);
        scrnAppli.setButtonEnabled(NWCL0110Constant.BTN_CMN_CLEAR_BTN_NM, true); // MOD S21_NA QC#16035
        // scrnAppli.setButtonEnabled(NWCL0110Constant.BTN_CMN_RESET_BTN_NM, true); // MOD S21_NA QC#16035
        scrnAppli.setButtonEnabled(NWCL0110Constant.BTN_CMN_RETURN_BTN_NM, true);
    }

    /**
     * set Table's Back Color
     * @param scrnMsg NWCL0110BMsg
     */
    public static final void setTblBackColor(NWCL0110BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
        tblColor.clearRowsBG(NWCL0110Bean.A, scrnMsg.A);
        tblColor.setAlternateRowsBG(NWCL0110Bean.A, scrnMsg.A);
    }

    /**
     * clear Header
     * @param scrnMsg NWCL0110BMsg
     */
    public static void clearHdr(NWCL0110BMsg scrnMsg) {

        scrnMsg.xxTpCd.clear();
        scrnMsg.invPrtBatTpCd.clear();
        scrnMsg.xxInvNum_FR.clear();
        scrnMsg.xxInvNum_TO.clear();
        scrnMsg.invPrtBrCd.clear();
        scrnMsg.conslBillNum_FR.clear();
        scrnMsg.conslBillNum_TO.clear();
        scrnMsg.procDt_FR.clear();
        scrnMsg.procDt_TO.clear();
        scrnMsg.invNum_FR.clear();
        scrnMsg.invNum_TO.clear();

    }

    /**
     * control Detail
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NWCL0110BMsg
     */
    public static void controlDtl(EZDCommonHandler scrnAppli, NWCL0110BMsg scrnMsg) {

        if (scrnMsg.A.getValidCount() > 0) {
            if (hasUpdateFuncId()) {
                controlCommonButton(scrnAppli, scrnMsg, true);
                scrnAppli.setButtonEnabled(NWCL0110Constant.BTN_SELECT_ALL, true);
                scrnAppli.setButtonEnabled(NWCL0110Constant.BTN_UN_SELECT_ALL, true);
            } else {
                controlCommonButton(scrnAppli, scrnMsg, false);
                inActiveDtlField(scrnAppli, scrnMsg);
                scrnAppli.setButtonEnabled(NWCL0110Constant.BTN_SELECT_ALL, false);
                scrnAppli.setButtonEnabled(NWCL0110Constant.BTN_UN_SELECT_ALL, false);
            }
        } else {
            controlCommonButton(scrnAppli, scrnMsg, false);
            scrnAppli.setButtonEnabled(NWCL0110Constant.BTN_SELECT_ALL, false);
            scrnAppli.setButtonEnabled(NWCL0110Constant.BTN_UN_SELECT_ALL, false);
        }

        //setTblBackColor(scrnMsg);
    }

    /**
     * hasRegistFuncId
     * @return boolean true:upedate false:reference
     */
    public static boolean hasUpdateFuncId() {

        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(NWCL0110Constant.BIZ_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException("You can't operate Reprocess Print Request(" + NWCL0110Constant.BIZ_ID + "). UserID is -> " + S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }

        for (String func : funcList) {
            if (NWCL0110Constant.AUTH_CONTR_MGR.equals(func)) {
                return true;
            }
        }

        return false;
    }

    /**
     * control Inactive Detail Field
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NWCL0110BMsg
     */
    public static void inActiveDtlField(EZDCommonHandler scrnAppli, NWCL0110BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NWCL0110_ABMsg abMsg = scrnMsg.A.no(i);
            abMsg.xxChkBox_A.setInputProtected(true);
            abMsg.reprRptBrNum_A.setInputProtected(true);
            abMsg.invPrtProcStsCd_A.setInputProtected(true);
            abMsg.invEmlProcStsCd_A.setInputProtected(true);
            abMsg.invFtpProcStsCd_A.setInputProtected(true);
            abMsg.invEdiProcStsCd_A.setInputProtected(true);
//            abMsg.invSpclBillProcStsCd_A.setInputProtected(true);
        }
    }
    public static void checkInput_Submit(EZDApplicationContext ctx, NWCL0110BMsg scrnMsg) {
    	
    }

}
