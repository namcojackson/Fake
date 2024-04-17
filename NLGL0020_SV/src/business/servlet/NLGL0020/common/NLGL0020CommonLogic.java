/**
 * <pre>Copyright(c)2009 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLGL0020.common;

import java.text.SimpleDateFormat;
import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.blap.NLGL0020.NLGL0020CMsg;
import business.servlet.NLGL0020.NLGL0020BMsg;
import business.servlet.NLGL0020.constant.NLGL0020Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * PO Maintenance
 * 
 * Date           Company         Name              Create/Update     Defect No
 * ------------------------------------------------------------------------------------
 * 08/30/2013     CSAI            N.Sekine          Create             MW Replace Initial
 * 01/17/2017     CITS            T.Kikuhara        Update             QC#16256
 * 05/25/2017     CITS            S.Endo            Update             RS#3173
 * 07/03/2017     CITS            S.Endo            Update             QC#19042
 *</pre>
 */
public class NLGL0020CommonLogic implements NLGL0020Constant {

    /**
     * @return bizMsg
     */
    public static NLGL0020CMsg setRequestData_NLGL0020Scrn00_Function_20() {

        NLGL0020CMsg bizMsg;
        bizMsg = new NLGL0020CMsg();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNCTION_CODE_SEARCH);

        return bizMsg;
    }

    /**
     * @return bizMsg
     */
    public static NLGL0020CMsg setRequestData_NLGL0020Scrn00_Function_40() {

        NLGL0020CMsg bizMsg;
        bizMsg = new NLGL0020CMsg();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNCTION_CODE_UPDATE);

        return bizMsg;
    }

    /**
     * Button Control for History Tab displayed
     * @param scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_NLGL0020Scrn00_CMN_HIST(EZDCommonHandler scrnAppli) {

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
            scrnAppli.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
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
            scrnAppli.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
            scrnAppli.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        }
        // QC#16256 add end
    }

    /**
     * Button Control for Download Tab displayed
     * @param scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_NLGL0020Scrn00_CMN_DOWNLOAD(EZDCommonHandler scrnAppli) {

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
            scrnAppli.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
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
            scrnAppli.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
            scrnAppli.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        }
        // QC#16256 add end
    }

    /**
     * Button Control for Download Tab displayed On Click Search
     * Button
     * @param scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_NLGL0020Scrn00_DNLD_SEARCH(EZDCommonHandler scrnAppli) {

        // QC#16256 add start
        if (isUpdatable()) {
            scrnAppli.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            scrnAppli.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
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
            scrnAppli.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
            scrnAppli.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        }
        // QC#16256 add end
    }

    /**
     * Button Control for Download Tab displayed On Click Submit
     * Button
     * @param scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_NLGL0020Scrn00_CMN_SUBMIT(EZDCommonHandler scrnAppli) {

        // QC#16256 add start
        if (isUpdatable()) {
            scrnAppli.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
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
            scrnAppli.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
            scrnAppli.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        }
        // QC#16256 add end
    }

    /**
     * Button Control for PO List Tab diplayed
     * @param scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_NLGL0020Scrn00_POLIST_TAB(EZDCommonHandler scrnAppli) {

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
            scrnAppli.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
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
            scrnAppli.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
            scrnAppli.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        }
        // QC#16256 add end
    }

    /**
     * Button Control for PO STATUS Tab diplayed
     * @param scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_NLGL0020Scrn00_POSTATUS_TAB(EZDCommonHandler scrnAppli) {

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
     * Button Control for Download EDIT Tab diplayed
     * @param scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_NLGL0020Scrn00_DNLD_TAB(EZDCommonHandler scrnAppli) {

        // QC#16256 add start
        if (isUpdatable()) {
            scrnAppli.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
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
            scrnAppli.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
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
     * Button Control for Download EDIT Tab diplayed
     * @param scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_NLGL0020Scrn00_UPD_TAB(EZDCommonHandler scrnAppli) {

        // QC#16256 add start
        if (isUpdatable()) {
            scrnAppli.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            scrnAppli.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 1, null);
            scrnAppli.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        } else {
            scrnAppli.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            scrnAppli.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 1, null);
            scrnAppli.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        }
        // QC#16256 add end
    }

    /**
     * Button Control for EDIT Tab diplayed and after SUBMIT process
     * end.
     * @param scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_NLGL0020Scrn00_SUBMIT(EZDCommonHandler scrnAppli) {

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
     * Field Control for PO List Tab Screen showed up(PO List MODE)
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NLGL0020BMsg
     */
    public static void inputFieldControl_NLGL0020Scrn00_Header_Unprotect(EZDCommonHandler scrnAppli, NLGL0020BMsg scrnMsg) {

        scrnMsg.whCd_02.setInputProtected(false);
        scrnMsg.wmsPoId_01.setInputProtected(false);
        scrnMsg.xxSrchRqstDtTpCd_02.setInputProtected(false);
        scrnMsg.xxSoSrchFromDt_01.setInputProtected(false);
        scrnMsg.xxSoSrchThruDt_01.setInputProtected(false);
        scrnMsg.wmsMdseCd_01.setInputProtected(false);
        scrnMsg.sceOrdTpCd_02.setInputProtected(false);
        scrnMsg.wmsTrxCd_02.setInputProtected(false);
        scrnMsg.xxChkBox_01.setInputProtected(false);

        scrnMsg.rtlWhCd_01.setInputProtected(false);
        scrnMsg.invtyOwnrCd_01.setInputProtected(false);

        scrnMsg.rtlWhNm_01.setInputProtected(true);

        scrnAppli.setButtonEnabled(BTN_SEARCH[0], true);
        scrnMsg.setFocusItem(scrnMsg.whCd_02);
    }

    /**
     * Field Control for PO Status/Download/Upload Tab Screen showed
     * up(PO Status/Download/Upload MODE)
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NLGL0020BMsg
     */
    public static void inputFieldControl_NLGL0020Scrn00_Header_Protect(EZDCommonHandler scrnAppli, NLGL0020BMsg scrnMsg) {

        scrnMsg.whCd_02.setInputProtected(true);
        scrnMsg.wmsPoId_01.setInputProtected(true);
        scrnMsg.xxSrchRqstDtTpCd_02.setInputProtected(true);
        scrnMsg.xxSoSrchFromDt_01.setInputProtected(true);
        scrnMsg.xxSoSrchThruDt_01.setInputProtected(true);
        scrnMsg.wmsMdseCd_01.setInputProtected(true);
        scrnMsg.sceOrdTpCd_02.setInputProtected(true);
        scrnMsg.wmsTrxCd_02.setInputProtected(true);
        scrnMsg.xxChkBox_01.setInputProtected(true);

        scrnMsg.rtlWhCd_01.setInputProtected(true);
        scrnMsg.invtyOwnrCd_01.setInputProtected(true);
        scrnMsg.invtyOwnrCd_E1.setInputProtected(true);

        scrnAppli.setButtonEnabled(BTN_SEARCH[0], false);
        scrnMsg.setFocusItem(scrnMsg.B.no(0).wmsPoId_B2);

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.B.no(i).wmsVeslNm_B1.setInputProtected(true);
            scrnMsg.B.no(i).wmsResrcTxt_B1.setInputProtected(true);
            scrnMsg.B.no(i).xxMsgTxt_B1.setInputProtected(true);
            scrnMsg.B.no(i).rtlWhCd_B1.setInputProtected(true);
            scrnMsg.B.no(i).invtyOwnrCd_B1.setInputProtected(true);
            scrnMsg.B.no(i).svcConfigMstrPk_B1.setInputProtected(true);
        }

        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            scrnMsg.C.no(i).fill40Txt_C1.setInputProtected(true);
        }
        for (int i = 0; i < scrnMsg.G.getValidCount(); i++) {
            scrnMsg.G.no(i).wmsVeslNm_G1.setInputProtected(true);
            scrnMsg.G.no(i).wmsResrcTxt_G1.setInputProtected(true);
            scrnMsg.G.no(i).rtlWhCd_G1.setInputProtected(true);
            scrnMsg.G.no(i).invtyOwnrCd_G1.setInputProtected(true);
            scrnMsg.G.no(i).svcConfigMstrPk_G1.setInputProtected(true);
        }
        for (int i = 0; i < scrnMsg.H.getValidCount(); i++) {
            scrnMsg.H.no(i).fill40Txt_H1.setInputProtected(true);
        }
    }

    /**
     * Field Control for PO List Tab Screen showed up(PO List MODE)
     * @param scrnMsg NLGL0020BMsg
     */
    public static void inputFieldControl_NLGL0020Scrn00_Search_Protect(NLGL0020BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).xxMsgTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).wmsResrcTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).rtlWhCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).invtyOwnrCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).svcConfigMstrPk_A1.setInputProtected(true);
        }
    }

    /**
     * Field Control for PO Status/Download/Upload Tab Screen showed
     * up(PO Status/Download/Upload MODE)
     * @param scrnMsg NLGL0020BMsg
     */
    public static void inputFieldControl_NLGL0020Scrn00_Upload_Detail_Protect(NLGL0020BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.I.getValidCount(); i++) {
            scrnMsg.I.no(i).xxProcFlgNm_I1.setInputProtected(false);
            scrnMsg.I.no(i).inbdOrdLineNum_I1.setInputProtected(false);
            scrnMsg.I.no(i).wmsTaskCd_I2.setInputProtected(false);
            scrnMsg.I.no(i).wmsMdseCd_I1.setInputProtected(false);
            scrnMsg.I.no(i).wmsStkStsCd_I2.setInputProtected(false);
            scrnMsg.I.no(i).wmsOrdQty_I1.setInputProtected(false);
            scrnMsg.I.no(i).wmsOrdTpCd_I2.setInputProtected(true);
        }
    }

    /**
     * Field Control for Upload Tab Screen showed up(Upload MODE)
     * @param scrnMsg NLGL0020BMsg
     */
    public static void inputFieldControl_NLGL0020Scrn00_Upload_COPY(NLGL0020BMsg scrnMsg) {

        int copyRow = scrnMsg.I.getValidCount() - 1;
        scrnMsg.I.no(copyRow).xxProcFlgNm_I1.setInputProtected(true);
        scrnMsg.I.no(copyRow).otbdOrdLineNum_I2.setInputProtected(false);
        scrnMsg.I.no(copyRow).wmsTaskCd_I2.setInputProtected(false);
        scrnMsg.I.no(copyRow).wmsMdseCd_I1.setInputProtected(false);
        scrnMsg.I.no(copyRow).wmsStkStsCd_I2.setInputProtected(false);
        scrnMsg.I.no(copyRow).wmsOrdQty_I1.setInputProtected(false);
        scrnMsg.I.no(copyRow).wmsOrdTpCd_I2.setInputProtected(true);
    }

    /**
     * Field Control for PO Status/Download/Upload Tab Screen showed
     * up(PO Status/Download/Upload MODE)
     * @param scrnMsg NLGL0020BMsg
     */
    public static void inputFieldControl_NLGL0020Scrn00_Upload_Insert(NLGL0020BMsg scrnMsg) {

        int copyRow = scrnMsg.I.getValidCount() - 1;
        scrnMsg.I.no(copyRow).xxProcFlgNm_I1.setInputProtected(true);
        scrnMsg.I.no(copyRow).inbdOrdLineNum_I1.setInputProtected(false);
        scrnMsg.I.no(copyRow).otbdOrdLineNum_I2.setInputProtected(false);
        scrnMsg.I.no(copyRow).wmsTaskCd_I2.setInputProtected(false);
        scrnMsg.I.no(copyRow).wmsMdseCd_I1.setInputProtected(false);
        scrnMsg.I.no(copyRow).wmsStkStsCd_I2.setInputProtected(false);
        scrnMsg.I.no(copyRow).wmsOrdQty_I1.setInputProtected(false);
        scrnMsg.I.no(copyRow).wmsOrdTpCd_I2.setInputProtected(true);
    }

    /**
     * Field Control for PO Download Tab Screen when change the Submit
     * List to Copy (PO Download MODE)
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NLGL0020BMsg
     */
    public static void inputFieldControl_NLGL0020Scrn00_COPY_Click(EZDCommonHandler scrnAppli, NLGL0020BMsg scrnMsg) {

        scrnMsg.whCd_E2.setInputProtected(false);
        scrnMsg.F.setInputProtected(false);
        scrnAppli.setButtonEnabled(BTN_INSERT[0], true);
        scrnAppli.setButtonEnabled(BTN_DELETE[0], true);
        scrnAppli.setButtonEnabled(BTN_CHECKALL[0], true);
        scrnAppli.setButtonEnabled(BTN_UNCHECKALL[0], true);
    }

    /**
     * Field Control for PO Download Tab Screen when change the Submit
     * List to Resend (PO Download MODE)
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NLGL0020BMsg
     */
    public static void inputFieldControl_NLGL0020Scrn00_Resend_Click(EZDCommonHandler scrnAppli, NLGL0020BMsg scrnMsg) {

        scrnMsg.whCd_E2.setInputProtected(true);
        scrnMsg.F.setInputProtected(true);
        scrnAppli.setButtonEnabled(BTN_INSERT[0], false);
        scrnAppli.setButtonEnabled(BTN_DELETE[0], false);
        scrnAppli.setButtonEnabled(BTN_CHECKALL[0], false);
        scrnAppli.setButtonEnabled(BTN_UNCHECKALL[0], false);
        scrnMsg.whCd_E2.setValue(scrnMsg.whCd_02.getValue());
    }

    /**
     * Button Control for LIST Tab Screen show up(after search button
     * clicked) the LIST
     * @param scrnAppli EZDCommonHandler
     */
    public static void btnControl_NLGL0020Scrn00_Prev_Disable(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonEnabled(BTN_PREV, false);
        scrnAppli.setButtonEnabled(BTN_NEXT, false);
    }

    /**
     * Button Control for LIST Tab diplayed and dysplayed the LIST
     * @param scrnAppli EZDCommonHandler
     */
    public static void busBtnControl_NLGL0020Scrn00_Prev_Enable(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonEnabled(BTN_PREV, true);
        scrnAppli.setButtonEnabled(BTN_NEXT, true);
    }

    /**
     * Button Control for LIST Tab diplayed and dysplayed the LIST
     * @param scrnAppli EZDCommonHandler
     */
    public static void busBtnControl_NLGL0020Scrn00_Insert_Disable(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonEnabled(BTN_INSERT[0], false);
    }

    /**
     * Button Control for LIST Tab diplayed and dysplayed the LIST
     * @param scrnAppli EZDCommonHandler
     */
    public static void busBtnControl_NLGL0020Scrn00_Insert_Enable(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonEnabled(BTN_INSERT[0], true);
    }

    /**
     * To check the data that already closed or not(PO Upload)
     * @param scrnMsg NLGL0020BMsg
     * @return true/false
     */
    public static boolean isClosed(NLGL0020BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxChkBox_A1)) {

                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).wmsCloDtTmTs_A1)) {
                    return false;
                }
                break;
            }
        }
        return true;
    }

    /**
     * Check where dateStr is date format or not. If dateStr is null,
     * return true.
     * @param dateStr date string
     * @param fmt format
     * @return true / dateStr is date format, false / dateStr is not
     * date format
     */
    public static boolean isDateFormat(String dateStr, String fmt) {

        if (!ZYPCommonFunc.hasValue(dateStr)) {
            return true;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        try {
            sdf.parse(dateStr);
        } catch (Exception e) {
            return false;
        }
        return true;
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
