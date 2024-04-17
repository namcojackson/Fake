/**
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NLGL0050.common;

import java.math.BigDecimal;
import java.util.List;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

import business.servlet.NLGL0050.NLGL0050BMsg;
import business.blap.NLGL0050.NLGL0050CMsg;
import business.servlet.NLGL0050.constant.NLGL0050Constant;
import parts.servletcommon.EZDCommonHandler;

/**
 * <pre>
 * Ship Via Mapping from WMS to HOST
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/08/16   CSAI            Y.Miyauchi      Create          MW Replace Initial
 * 2017/01/17   CITS            T.Kikuhara      Update          QC#16256
 * 2018/03/09   CITS            M.Naito         Update          QC#24696
 *</pre>
 */
public class NLGL0050CommonLogic implements NLGL0050Constant {

    /**
     * The method explanation: This method sets BusinessId and FunctionCode checked data of cMsg.(Search)
     * @return bizMsg NLGL0050CMsg
     */
    public static NLGL0050CMsg setRequestData_NLGL0050Scrn00_Function_20() {

        NLGL0050CMsg bizMsg;
        bizMsg = new NLGL0050CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNCTION_CODE_SEARCH);

        return bizMsg;
    }

    /**
     * The method explanation: This method sets BusinessId and FunctionCode checked data of cMsg.(Update/Insert/Delete)
     * @return bizMsg NLGL0050CMsg
     */
    public static NLGL0050CMsg setRequestData_NLGL0050Scrn00_Function_40() {

        NLGL0050CMsg bizMsg;
        bizMsg = new NLGL0050CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNCTION_CODE_UPDATE);

        return bizMsg;
    }

    /**
     * The method explanation: This method sets Footer Button Propterties when Tab is List.
     * @param scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_NLGL0050Scrn00_CMN_LIST(EZDCommonHandler scrnAppli) {

        // QC#16256 add start
        if (isUpdatable()) {
            scrnAppli.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 1, null);
            scrnAppli.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
            scrnAppli.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        } else {
            scrnAppli.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 1, null);
            scrnAppli.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
            scrnAppli.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        }
        // QC#16256 add end
    }

    /**
     * The method explanation: This method sets Footer Button Propterties when Tab is Edit(Update).
     * @param scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_NLGL0050Scrn00_CMN_EDIT(EZDCommonHandler scrnAppli) {

        // QC#16256 add start
        if (isUpdatable()) {
            scrnAppli.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            scrnAppli.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
            scrnAppli.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
            scrnAppli.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        } else {
            scrnAppli.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            scrnAppli.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
            scrnAppli.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
            scrnAppli.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        }
        // QC#16256 add end
    }

    /**
     * The method explanation: This method sets Footer Button Propterties when Tab is Edit(Insert).
     * @param scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_NLGL0050Scrn00_CMN_NEW(EZDCommonHandler scrnAppli) {

        // QC#16256 add start
        if (isUpdatable()) {
            scrnAppli.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            scrnAppli.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
            scrnAppli.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        } else {
            scrnAppli.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            scrnAppli.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
            scrnAppli.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        }
        // QC#16256 add end
    }

    /**
     * The method explanation: This method sets Footer Button Propterties when Tab is Edit(Insert).
     * @param scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_SUBMIT(EZDCommonHandler scrnAppli) {

        // QC#16256 add start
        // This Screen is Read only Screen
        if (isUpdatable()) {
            scrnAppli.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        } else {
            scrnAppli.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        }
        // QC#16256 add end
    }

    /**
     * The method explanation: This method sets to disable of PagerButton.
     * @param scrnAppli EZDCommonHandler
     */
    public static void busBtnControl_NLGL0050Scrn00_Prev_Disable(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonEnabled(BTN_PREV, false);
        scrnAppli.setButtonEnabled(BTN_NEXT, false);
        scrnAppli.setButtonEnabled(TAB_ID_LIST, false);
    }

    /**
     * The method explanation: This method sets to enable of PagerButton.
     * @param scrnAppli EZDCommonHandler
     */
    public static void busBtnControl_NLGL0050Scrn00_Prev_Enable(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonEnabled(BTN_PREV, true);
        scrnAppli.setButtonEnabled(BTN_NEXT, true);
        scrnAppli.setButtonEnabled(TAB_ID_LIST, true);
    }

    /**
     * The method explanation: This method sets PagerButton to enable.
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NLGL0050BMsg
     */
    public static void inputFieldControl_NLGL0050Scrn00_List(EZDCommonHandler scrnAppli, NLGL0050BMsg scrnMsg) {

        scrnMsg.wmsOrgHostId_P1.setInputProtected(false);
        scrnMsg.wmsTrnspTpCd_P1.setInputProtected(false);
        // START 2018/03/09 M.Naito [QC#24696,ADD]
        scrnMsg.wmsCarrCd_T1.setInputProtected(false);
        // END 2018/03/09 M.Naito [QC#24696,ADD]
        scrnMsg.carrScacCd_T1.setInputProtected(false);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).wmsDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).wmsCarrNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).carrSvcTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).shpgSvcLvlDescTxt_A1.setInputProtected(true);
        }
        scrnAppli.setButtonEnabled(BTN_SEARCH[0], true);
    }

    /**
     * The method explanation: This method sets protected of control when Tab is Edit(Update).
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NLGL0050BMsg
     */
    public static void inputFieldControl_NLGL0050Scrn00_Edit(EZDCommonHandler scrnAppli, NLGL0050BMsg scrnMsg) {

        scrnMsg.wmsOrgHostId_P1.setInputProtected(true);
        scrnMsg.wmsTrnspTpCd_P1.setInputProtected(true);
        // START 2018/03/09 M.Naito [QC#24696,ADD]
        scrnMsg.wmsCarrCd_T1.setInputProtected(true);
        // END 2018/03/09 M.Naito [QC#24696,ADD]
        scrnMsg.carrScacCd_T1.setInputProtected(true);
        scrnMsg.wmsTrnspTpCd_P2.setInputProtected(false);
        scrnMsg.wmsCarrCd_T2.setInputProtected(true);
        scrnMsg.wmsDescTxt_T2.setInputProtected(false);
        scrnMsg.wmsCarrNm_T2.setInputProtected(false);
        scrnMsg.carrSvcTxt_T2.setInputProtected(false);
        scrnMsg.carrScacCd_T2.setInputProtected(false);
        scrnMsg.shpgSvcLvlCd_T2.setInputProtected(false);
        scrnMsg.fuelUpchgTpCd_T2.setInputProtected(false);
        scrnMsg.fuelUpchgAmt_T2.setInputProtected(false);
        scrnMsg.addlUpchgTpCd_T2.setInputProtected(false);
        scrnMsg.addlUpchgAmt_T2.setInputProtected(false);
        scrnAppli.setButtonEnabled(BTN_SEARCH[0], false);
        scrnMsg.setFocusItem(scrnMsg.wmsTrnspTpCd_P2);
    }

    /**
     * The method explanation: This method sets protected of control when Tab is Edit(Insert).
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NLGL0050BMsg
     */
    public static void inputFieldControl_NLGL0050Scrn00_New(EZDCommonHandler scrnAppli, NLGL0050BMsg scrnMsg) {

        scrnMsg.wmsOrgHostId_P1.setInputProtected(true);
        scrnMsg.wmsTrnspTpCd_P1.setInputProtected(true);
        // START 2018/03/09 M.Naito [QC#24696,ADD]
        scrnMsg.wmsCarrCd_T1.setInputProtected(true);
        // END 2018/03/09 M.Naito [QC#24696,ADD]
        scrnMsg.carrScacCd_T1.setInputProtected(true);
        scrnMsg.wmsTrnspTpCd_P2.setInputProtected(false);
        scrnMsg.wmsCarrCd_T2.setInputProtected(false);
        scrnMsg.wmsDescTxt_T2.setInputProtected(false);
        scrnMsg.wmsCarrNm_T2.setInputProtected(false);
        scrnMsg.carrSvcTxt_T2.setInputProtected(false);
        scrnMsg.carrScacCd_T2.setInputProtected(false);
        scrnMsg.shpgSvcLvlCd_T2.setInputProtected(false);
        scrnMsg.fuelUpchgTpCd_T2.setInputProtected(false);
        scrnMsg.fuelUpchgAmt_T2.setInputProtected(false);
        scrnMsg.addlUpchgTpCd_T2.setInputProtected(false);
        scrnMsg.addlUpchgAmt_T2.setInputProtected(false);
        scrnAppli.setButtonEnabled(BTN_SEARCH[0], false);
        scrnMsg.setFocusItem(scrnMsg.wmsTrnspTpCd_P2);
    }

    /**
     * The method explanation: This method sets protected of control when Tab is Edit(Insert).
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NLGL0050BMsg
     */
    public static void commonBtnControl_NLGL0050Scrn00_CMN_ClearforEdit(EZDCommonHandler scrnAppli, NLGL0050BMsg scrnMsg) {

        scrnMsg.wmsTrnspTpCd_P2.setValue(scrnMsg.wmsTrnspTpCd_T2.no(0).getValue());
        scrnMsg.wmsDescTxt_T2.setValue(FLD_VALUE_BLANK);
        scrnMsg.wmsCarrNm_T2.setValue(FLD_VALUE_BLANK);
        scrnMsg.carrSvcTxt_T2.setValue(FLD_VALUE_BLANK);
        scrnMsg.carrScacCd_T2.setValue(FLD_VALUE_BLANK);
        scrnMsg.shpgSvcLvlCd_P2.setValue(FLD_VALUE_BLANK);
        scrnMsg.fuelUpchgTpCd_P2.setValue(FLD_VALUE_BLANK);
        scrnMsg.fuelUpchgAmt_T2.setValue(FLD_VALUE_ZERO);
        scrnMsg.addlUpchgTpCd_P2.setValue(FLD_VALUE_BLANK);
        scrnMsg.addlUpchgAmt_T2.setValue(FLD_VALUE_ZERO);
    }

    /**
     * The method explanation: This method clear Edit data.
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NLGL0050BMsg
     */
    public static void commonBtnControl_NLGL0050Scrn00_CMN_ClearforNew(EZDCommonHandler scrnAppli, NLGL0050BMsg scrnMsg) {

        scrnMsg.wmsTrnspTpCd_P2.setValue(scrnMsg.wmsTrnspTpCd_T2.no(0).getValue());
        scrnMsg.wmsCarrCd_T2.setValue(FLD_VALUE_BLANK);
        scrnMsg.wmsDescTxt_T2.setValue(FLD_VALUE_BLANK);
        scrnMsg.wmsCarrNm_T2.setValue(FLD_VALUE_BLANK);
        scrnMsg.carrSvcTxt_T2.setValue(FLD_VALUE_BLANK);
        scrnMsg.carrScacCd_T2.setValue(FLD_VALUE_BLANK);
        scrnMsg.shpgSvcLvlCd_P2.setValue(FLD_VALUE_BLANK);
        scrnMsg.fuelUpchgTpCd_P2.setValue(FLD_VALUE_BLANK);
        scrnMsg.fuelUpchgAmt_T2.setValue(FLD_VALUE_ZERO);
        scrnMsg.addlUpchgTpCd_P2.setValue(FLD_VALUE_BLANK);
        scrnMsg.addlUpchgAmt_T2.setValue(FLD_VALUE_ZERO);
    }

    /**
     * The method explanation: This method check to bigger more than 0.
     * @param value BigDecimal
     * @return boolean
     */
    public static boolean inputField_Minus_Check(BigDecimal value) {

        if (ZYPCommonFunc.hasValue(value)) {
            if (value.compareTo(BigDecimal.ZERO) < 0) {
                return true;
            }
        }
        return false;
    }

    // QC#16256 add start
    /**
     * Function check
     * @return true:edit false:reference
     */
    private static boolean isUpdatable() {
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        List<String> functionList = profileService.getAuthorizedFunctionCodeListForBizAppId(BIZ_ID);
        return functionList.contains(FUNCTION_UPDATE);
    }
    // QC#16256 add end

}
