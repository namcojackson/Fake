/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0060.common;

import static business.servlet.NSAL0060.constant.NSAL0060Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.util.List;

import parts.servletcommon.EZDCommonHandler;

import business.servlet.NSAL0060.NSAL0060BMsg;

import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/** 
 *<pre>
 *
 * Model Group Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/08/2013   Hitachi         Y.Igarashi         Create          N/A
 *</pre>
 */
public class NSAL0060CommonLogic {

    /**
     * 
     * checkAuth
     * 
     * @param scrnMsg NSAL0060BMsg
     * @param handler EZDCommonHandler
     */
    public static void checkAuth(NSAL0060BMsg scrnMsg, EZDCommonHandler handler) {
        S21UserProfileService userProfileService = S21UserProfileServiceFactory.getInstance().getService();
        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BUSINESS_APPLICATION_ID);
        if (!functionIds.contains(FUNCTION_ID_UPD)) {
            // All Protected
            handler.setButtonEnabled(BUTTON_NAME_INSERT_ROW, false);
        }
        if (!functionIds.contains(FUNCTION_ID_DEL)) {
            handler.setButtonEnabled(BUTTON_NAME_DELETE_ROW, false);
        }
        handler.setButtonEnabled(BUTTON_NAME_SUBMIT, false);
        if (functionIds.contains(FUNCTION_ID_UPD) || functionIds.contains(FUNCTION_ID_DEL)) {
            handler.setButtonEnabled(BUTTON_NAME_SUBMIT, true);
        }
    }

    /**
     * 
     * setBgColor
     * 
     * @param scrnMsg NSAL0060BMsg
     */
    public static void setBgColor(NSAL0060BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG(TBL_ID, scrnMsg.A);
        if (scrnMsg.A.getValidCount() > 0) {
            tblColor.setAlternateRowsBG(TBL_ID, scrnMsg.A);
        }
    }

    /**
     * 
     * setProtected
     * 
     * @param scrnMsg NSAL0060BMsg
     * @param handler EZDCommonHandler
     */
    public static void setProtected(NSAL0060BMsg scrnMsg, EZDCommonHandler handler) {
        S21UserProfileService userProfileService = S21UserProfileServiceFactory.getInstance().getService();
        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BUSINESS_APPLICATION_ID);

        scrnMsg.setInputProtected(false);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (hasValue(scrnMsg.A.no(i).mdlGrpId_SR)) {
                scrnMsg.A.no(i).mdlGrpNm_SR.setInputProtected(true);
            }
            if (!functionIds.contains(FUNCTION_ID_UPD)) {
                scrnMsg.A.no(i).mdlGrpDescTxt_SR.setInputProtected(true);
            }
        }
        if (scrnMsg.A.getValidCount() > 0) {
            handler.setButtonEnabled(BUTTON_NAME_DELETE_ROW, true);
            handler.setButtonEnabled(BUTTON_NAME_MODEL_LIST, true);
        } else {
            handler.setButtonEnabled(BUTTON_NAME_DELETE_ROW, false);
            handler.setButtonEnabled(BUTTON_NAME_MODEL_LIST, false);
        }
    }

}
