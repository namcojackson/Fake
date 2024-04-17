/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0160.common;

import static business.servlet.NSBL0160.constant.NSBL0160Constant.*;

import java.util.List;

import parts.i18n.EZDI18NContext;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSBL0160.NSBL0160BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/** 
 *<pre>
 *
 * Memo Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/11   Hitachi         Y.Igarashi         Create          N/A
 * 2013/11/21   Hitachi         T.Kawazu           Update          QC2852
 * 2015/10/29   Hitachi         T.Tomita           Update          N/A
 * 2016/02/19   Hitachi         K.Kasai            Update          QC#3689
 * 2016/12/27   Hitachi         K.Ochiai           Update          QC#16331
 * 2017/08/09   Hitachi         U.Kim              Update          QC#18151
 *</pre>
 */
public class NSBL0160CommonLogic {

    /**
     * 
     * setBgColor
     * 
     * @param scrnMsg NSBL0160BMsg
     */
    public static void setBgColor(NSBL0160BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG(TBL_ID, scrnMsg.B);
        // START 2015/10/29 T.Tomita [N/A, MOD]
        if (scrnMsg.B.length() > 0) {
            tblColor.setAlternateRowsBG(TBL_ID, scrnMsg.B, TBL_ROW_SPAN);
        }
        // END 2015/10/29 T.Tomita [N/A, MOD]
    }

    /**
     * 
     * setProtected
     * 
     * @param scrnMsg NSBL0160BMsg
     * @param handler EZDCommonHandler
     */
    public static void setProtected(NSBL0160BMsg scrnMsg, EZDCommonHandler handler) {
        scrnMsg.setInputProtected(false);
        // START 2015/10/29 T.Tomita [N/A, MOD]
        for (int i = 0; i < scrnMsg.B.length(); i++) {
            // START 2017/08/09 U.Kim [QC#18151, MOD]
            // scrnMsg.B.no(i).svcCmntTxt_B.setInputProtected(false);
            if (ZYPCommonFunc.hasValue(scrnMsg.B.no(i).svcMemoPk_B)) {
                scrnMsg.B.no(i).svcCmntTxt_B.setInputProtected(true);
            } else {
                scrnMsg.B.no(i).svcCmntTxt_B.setInputProtected(false);
            }
            // END 2017/08/09 U.Kim [QC#18151, MOD]
        }
        if (isProtectedScreenItem(scrnMsg)) {
            // All Protected
            scrnMsg.setInputProtected(true);
            handler.setButtonEnabled(BUTTON_NAME_SEARCH, false);
            handler.setButtonEnabled(BUTTON_NAME_ADD, false);
            handler.setButtonProperties(BUTTON_NAME_SAVE, BUTTON_GUARD_SAVE, BUTTON_LABEL_SAVE, 0, null);
            // del start 2016/12/27 CSA Defect#16331
            // handler.setButtonProperties(BUTTON_NAME_CLEAR, BUTTON_GUARD_CLEAR, BUTTON_LABEL_CLEAR, 0, null);
            // del end 2016/12/27 CSA Defect#16331
            // add start 2016/02/18 CSA Defect#3689
            handler.setButtonProperties(BUTTON_NAME_DOWNLOAD, BUTTON_GUARD_DOWNLOAD, BUTTON_LABEL_DOWNLOAD, 0, null);
            handler.setButtonProperties(BUTTON_NAME_RESET, BUTTON_GUARD_RESET, BUTTON_LABEL_RESET, 0, null);
            // add end 2016/02/18 CSA Defect#3689
        } else {
            handler.setButtonEnabled(BUTTON_NAME_SEARCH, true);
            handler.setButtonEnabled(BUTTON_NAME_ADD, true);
            handler.setButtonProperties(BUTTON_NAME_SAVE, BUTTON_GUARD_SAVE, BUTTON_LABEL_SAVE, 1, null);
            // del start 2016/12/27 CSA Defect#16331
            // handler.setButtonProperties(BUTTON_NAME_CLEAR, BUTTON_GUARD_CLEAR, BUTTON_LABEL_CLEAR, 1, null);
            // del end 2016/12/27 CSA Defect#16331
            //add start 2016/02/18 CSA Defect#3689
            handler.setButtonProperties(BUTTON_NAME_DOWNLOAD, BUTTON_GUARD_DOWNLOAD, BUTTON_LABEL_DOWNLOAD, 1, null);
            handler.setButtonProperties(BUTTON_NAME_RESET, BUTTON_GUARD_RESET, BUTTON_LABEL_RESET, 1, null);
            //add end 2016/02/18 CSA Defect#3689
        }
        // START 2015/10/29 T.Tomita [N/A, MOD]
    }

    private static boolean isProtectedScreenItem(NSBL0160BMsg scrnMsg) {
        // START 2015/10/29 T.Tomita [N/A, MOD]
        if (scrnMsg.A.getValidCount() < 1) {
            return true;
        }
        if (scrnMsg.C.getValidCount() < 1) {
            return true;
        }
//        if (!hasValue((EZDBStringItem) scrnMsg.svcMemoTpCd_CD.get(0))) {
//            return true;
//        }
        // END 2015/10/29 T.Tomita [N/A, MOD]
        return false;
    }

    /**
     * 
     * checkAuth
     * 
     * @param scrnMsg NSBL0160BMsg
     * @param handler EZDCommonHandler
     */
    public static void checkAuth(NSBL0160BMsg scrnMsg, EZDCommonHandler handler) {
        S21UserProfileService userProfileService = S21UserProfileServiceFactory.getInstance().getService();
        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BUSINESS_APPLICATION_ID);
        if (functionIds == null || functionIds.isEmpty()) {
            throw new S21AbendException("You can't operate Memo Entry(NSBL0160). UserID is -> " + S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }
        if (!functionIds.contains(FUNCTION_ID_UPD)
                || ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxYesNoCd.getValue())) {
            // All Protected
            scrnMsg.setInputProtected(true);
            handler.setButtonEnabled(BUTTON_NAME_ADD, false);
            //mod start 2016/02/18 CSA Defect#3689
            handler.setButtonEnabled(BUTTON_NAME_SEARCH, false);
            handler.setButtonProperties(BUTTON_NAME_SAVE, BUTTON_GUARD_SAVE, BUTTON_LABEL_SAVE, 0, null);
            handler.setButtonProperties(BUTTON_NAME_CLEAR, BUTTON_GUARD_CLEAR, BUTTON_LABEL_CLEAR, 0, null);
            handler.setButtonProperties(BUTTON_NAME_DOWNLOAD, BUTTON_GUARD_DOWNLOAD, BUTTON_LABEL_DOWNLOAD, 0, null);
            handler.setButtonProperties(BUTTON_NAME_RESET, BUTTON_GUARD_RESET, BUTTON_LABEL_RESET, 0, null);
            //mod end 2016/02/18 CSA Defect#3689
        }
    }

    /**
     * getI18NLabel
     * @param label String
     * @return String
     */
    public static String getI18NLabel(String label) {
        // Update QC2852 Start 2013/11/20
        //return EZDI18NContext.getInstance().getI18NAccessor().getLabelConv().convLabel2i18nLabel(
        //        SCREEN_ID, label);
        return EZDI18NContext.getInstance().getI18NAccessor().getLabelConv().convLabel2i18nLabel(
                "", label);
        // Update QC2852 End 2013/11/20
    }

}
