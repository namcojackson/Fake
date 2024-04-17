/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0390.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static business.servlet.NSAL0390.constant.NSAL0390Constant.*;

import java.util.List;

import parts.common.EZDBDateItem;
import parts.common.EZDBMsgArray;
import parts.common.EZDBStringItem;
import parts.common.EZDMsg;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL0390.NSAL0390BMsg;
import business.servlet.NSAL0390.NSAL0390Bean;
import business.servlet.NSAL0390.NSAL0390_ABMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/18   Fujitsu         T.Yoshida       Create          N/A
 * 2016/05/18   Hitachi         A.Kohinata      Update          QC#4212
 * 2016/08/29   Hitachi         A.Kohinata      Update          QC#11243
 * 2017/01/17   Hitachi         K.Ochiai        Update          QC#16331
 * 2018/03/16   CITS            M.Naito         Update          QC#20496
 *</pre>
 */
public class NSAL0390CommonLogic {

    /**
     * get Input Parameter
     * @param scrnMsg NSAL0390BMsg
     * @param params Object[]
     */
    public static void getInputParam(NSAL0390BMsg scrnMsg, Object[] params) {

        if (params.length > 0 && params[0] != null && params[0] instanceof EZDBMsgArray) {
            EZDBMsgArray param0 = (EZDBMsgArray) params[0];
            EZDMsg.copy(param0, null, scrnMsg.P, null);
        }

        if (params.length > 1 && params[1] != null && params[1] instanceof EZDBStringItem) {
            EZDBStringItem param1 = (EZDBStringItem) params[1];
            setValue(scrnMsg.crCardCustRefNum_P, param1.getValue());
        }

        if (params.length > 2 && params[2] != null && params[2] instanceof EZDBDateItem) {
            EZDBDateItem param2 = (EZDBDateItem) params[2];
            setValue(scrnMsg.crCardExprYrMth_P, param2.getValue());
        }

        if (params.length > THREE && params[THREE] != null && params[THREE] instanceof EZDBStringItem) {
            EZDBStringItem param3 = (EZDBStringItem) params[THREE];
            setValue(scrnMsg.custPoNum_P, param3.getValue());
        }

        if (params.length > FOUR && params[FOUR] != null && params[FOUR] instanceof EZDBDateItem) {
            EZDBDateItem param4 = (EZDBDateItem) params[FOUR];
            setValue(scrnMsg.poDt_P, param4.getValue());
        }
    }

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
        scrnAppli.setButtonProperties(BTN_CMN_DOWNLOAD_BTN_NM, "", BTN_CMN_DOWNLOAD_LABEL, 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_DELETE_BTN_NM, "", BTN_CMN_DELETE_LABEL, 0, null);
        // mod start 2017/01/17 CSA Defect#16331
        scrnAppli.setButtonProperties(BTN_CMN_CLEAR_BTN_NM, "", BTN_CMN_CLEAR_LABEL, 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_RESET_BTN_NM, BTN_CMN_RESET_EVENT_NM, BTN_CMN_RESET_LABEL, 0, null);
        // mod end 2017/01/17 CSA Defect#16331
        scrnAppli.setButtonProperties(BTN_CMN_RETURN_BTN_NM, BTN_CMN_RETURN_EVENT_NM, BTN_CMN_RETURN_LABEL, 0, null);
    }

    /**
     * control CommonButton
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NSAL0390BMsg
     * @param isActive boolean
     */
    public static void controlCommonButton(EZDCommonHandler scrnAppli, NSAL0390BMsg scrnMsg, boolean isActive) {

        scrnAppli.setButtonEnabled(BTN_CMN_SUBMIT_BTN_NM, isActive);
        // mod start 2017/01/17 CSA Defect#16331
        scrnAppli.setButtonEnabled(BTN_CMN_RESET_BTN_NM, true);
        // mod end 2017/01/17 CSA Defect#16331
        scrnAppli.setButtonEnabled(BTN_CMN_RETURN_BTN_NM, true);
        scrnAppli.setButtonEnabled(BTN_APPLY_TO_ALL, isActive);
    }

    /**
     * control Detail
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NSAL0390BMsg
     */
    public static void controlDtl(EZDCommonHandler scrnAppli, NSAL0390BMsg scrnMsg) {

        if (scrnMsg.A.getValidCount() > 0) {
            if (hasUpdateFuncId()) {
                controlCommonButton(scrnAppli, scrnMsg, true);
                controlDtlField(scrnAppli, scrnMsg, true);
            } else {
                controlCommonButton(scrnAppli, scrnMsg, false);
                controlDtlField(scrnAppli, scrnMsg, false);
            }
        } else {
            controlCommonButton(scrnAppli, scrnMsg, false);
        }

        setTblBackColor(scrnMsg);
    }

    /**
     * Leasing Company control Field
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NSAL0390BMsg
     */
    public static void leasingCompanyControlField(EZDCommonHandler scrnAppli, NSAL0390BMsg scrnMsg) {

        // event line
        int eventLine = scrnMsg.xxNum_EV.getValueInt();
        NSAL0390_ABMsg targetAbMsg = scrnMsg.A.no(eventLine);

        if (ZYPConstant.CHKBOX_ON_Y.equals(targetAbMsg.leaseCmpyFlg_A0.getValue())) {
            targetAbMsg.crCardCustRefNum_A0.clear();
            targetAbMsg.crCardExprYrMth_A0.clear();
            scrnAppli.setButtonEnabled(BTN_OPEN_CC, eventLine, false);
        } else {
            scrnAppli.setButtonEnabled(BTN_OPEN_CC, eventLine, true);
        }
    }

    /**
     * set Table's Back Color
     * @param scrnMsg NSAL0390BMsg
     */
    public static final void setTblBackColor(NSAL0390BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID_00, scrnMsg);
        tblColor.clearRowsBG(NSAL0390Bean.A, scrnMsg.A);
        tblColor.setAlternateRowsBG(NSAL0390Bean.A, scrnMsg.A);
    }

    /**
     * hasRegistFuncId
     * @return boolean true:upedate false:reference
     */
    public static boolean hasUpdateFuncId() {

        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BUSINESS_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException("You can't operate Capture CC & PO#(" + BUSINESS_ID + "). UserID is -> " + S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }

        for (String func : funcList) {
            if (AUTH_CONTR_MGR.equals(func)) {
                return true;
            }
        }

        return false;
    }

    /**
     * control Detail Field
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0390BMsg
     * @param isActive boolean
     */
    public static void controlDtlField(EZDCommonHandler handler, NSAL0390BMsg scrnMsg, boolean isActive) {

        // Check Box(Serial#)
        scrnMsg.xxChkBox_H0.setInputProtected(true);
        // Check Box(Base/Overage)
        scrnMsg.xxChkBox_H1.setInputProtected(true);

        // START 2018/03/16 M.Naito [QC#20496, DEL]
        // Note Flag
//        if (isActive) {
//            scrnMsg.xxCntDplyFlg.setValue(ZYPConstant.FLG_ON_Y);
//        } else {
//            scrnMsg.xxCntDplyFlg.setValue(ZYPConstant.FLG_OFF_N);
//        }
        // END 2018/03/16 M.Naito [QC#20496, DEL]

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSAL0390_ABMsg abMsg = scrnMsg.A.no(i);

            // Contract#
            abMsg.dsContrNum_A0.setInputProtected(true);
            // Contract# Serial# Select
            abMsg.xxChkBox_A0.setInputProtected(true);
            // Serial#
            scrnMsg.A.no(i).serNum_A0.setInputProtected(true);
            // Base/Overage Select
            abMsg.xxChkBox_A1.setInputProtected(true);
            // Base/Overage
            abMsg.mtrLbDescTxt_A0.setInputProtected(true);
            // Reference#
            abMsg.crCardCustRefNum_A0.setInputProtected(true);
            // Exp Date
            abMsg.crCardExprYrMth_A0.setInputProtected(true);
            // CC Button
            handler.setButtonEnabled(BTN_OPEN_CC, i, false);
            // New Reference#
            abMsg.crCardCustRefNum_A1.setInputProtected(true);
            // New Exp Date
            abMsg.crCardExprYrMth_A1.setInputProtected(true);
            // Leasing
            abMsg.leaseCmpyFlg_A0.setInputProtected(true);
            // Return Message
            abMsg.xxDsMsgEntryTxt_A0.setInputProtected(true);

            if (isActive) {
                // Check Box(Serial#)
                scrnMsg.xxChkBox_H0.setInputProtected(false);
                // Check Box(Base/Overage)
                scrnMsg.xxChkBox_H1.setInputProtected(false);
                if (ZYPConstant.FLG_ON_Y.equals(abMsg.xxDplyCtrlFlg_A1.getValue())) {
                    String machLvlNum = abMsg.dsContrMachLvlNum_A0.getValue();
                    // Base/Overage Select
                    if (MACH_LVL_NUM_1.equals(machLvlNum) || MACH_LVL_NUM_2.equals(machLvlNum)) {
                        abMsg.xxChkBox_A0.setInputProtected(false);
                    }
                    // Base/Overage Select
                    if (MACH_LVL_NUM_3.equals(machLvlNum)) {
                        abMsg.xxChkBox_A1.setInputProtected(false);
                    }
                    // CC Button
                    if (!ZYPConstant.FLG_ON_Y.equals(abMsg.leaseCmpyFlg_A0.getValue())) {
                        handler.setButtonEnabled(BTN_OPEN_CC, i, true);
                        // add start 2016/08/29 CSA Defect#11243
                        // New Reference#
                        abMsg.crCardCustRefNum_A1.setInputProtected(false);
                        // add end 2016/08/29 CSA Defect#11243
                    }
                    // Leasing
                    abMsg.leaseCmpyFlg_A0.setInputProtected(false);
                }
            }
        }
    }

    /**
     * setConfigSearchPopUpInputParam
     * @param scrnMsg NSAL0390BMsg
     * @return Object[]
     */
    public static Object[] setConfigSearchPopUpInputParam(NSAL0390BMsg scrnMsg) {
        ZYPTableUtil.clear(scrnMsg.O);
        scrnMsg.O.setValidCount(0);

        Object[] params = new Object[PARAM_LENGTH_NSAL1240];

        params[PARAM_INDEX_0] = PARAM_CONFIG_EXST_MODE_CD_01;
        params[PARAM_INDEX_10] = PARAM_SVC_MACH_MSTR_STS_EDIT_FLG_Y;
        params[PARAM_INDEX_11] = PARAM_MACH_ALLOC_MODE_CODE_01;
        params[PARAM_INDEX_12] = PARAM_MAIN_UNIT_FLG_Y;
        params[PARAM_INDEX_30] = scrnMsg.O;
        return params;
    }
}
