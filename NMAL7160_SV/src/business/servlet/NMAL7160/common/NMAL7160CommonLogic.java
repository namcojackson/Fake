/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7160.common;

import static business.servlet.NMAL7160.constant.NMAL7160Constant.AUTH_EDIT;
import static business.servlet.NMAL7160.constant.NMAL7160Constant.BTN_CMN_APL_BTN_NM;
import static business.servlet.NMAL7160.constant.NMAL7160Constant.BTN_CMN_APL_EVENT_NM;
import static business.servlet.NMAL7160.constant.NMAL7160Constant.BTN_CMN_APL_LABEL;
import static business.servlet.NMAL7160.constant.NMAL7160Constant.BTN_CMN_ARV_BTN_NM;
import static business.servlet.NMAL7160.constant.NMAL7160Constant.BTN_CMN_ARV_EVENT_NM;
import static business.servlet.NMAL7160.constant.NMAL7160Constant.BTN_CMN_ARV_LABEL;
import static business.servlet.NMAL7160.constant.NMAL7160Constant.BTN_CMN_CLR_BTN_NM;
import static business.servlet.NMAL7160.constant.NMAL7160Constant.BTN_CMN_CLR_EVENT_NM;
import static business.servlet.NMAL7160.constant.NMAL7160Constant.BTN_CMN_CLR_LABEL;
import static business.servlet.NMAL7160.constant.NMAL7160Constant.BTN_CMN_DEL_BTN_NM;
import static business.servlet.NMAL7160.constant.NMAL7160Constant.BTN_CMN_DEL_EVENT_NM;
import static business.servlet.NMAL7160.constant.NMAL7160Constant.BTN_CMN_DEL_LABEL;
import static business.servlet.NMAL7160.constant.NMAL7160Constant.BTN_CMN_DWL_BTN_NM;
import static business.servlet.NMAL7160.constant.NMAL7160Constant.BTN_CMN_DWL_EVENT_NM;
import static business.servlet.NMAL7160.constant.NMAL7160Constant.BTN_CMN_DWL_LABEL;
import static business.servlet.NMAL7160.constant.NMAL7160Constant.BTN_CMN_RJT_BTN_NM;
import static business.servlet.NMAL7160.constant.NMAL7160Constant.BTN_CMN_RJT_EVENT_NM;
import static business.servlet.NMAL7160.constant.NMAL7160Constant.BTN_CMN_RJT_LABEL;
import static business.servlet.NMAL7160.constant.NMAL7160Constant.BTN_CMN_RST_BTN_NM;
import static business.servlet.NMAL7160.constant.NMAL7160Constant.BTN_CMN_RST_EVENT_NM;
import static business.servlet.NMAL7160.constant.NMAL7160Constant.BTN_CMN_RST_LABEL;
import static business.servlet.NMAL7160.constant.NMAL7160Constant.BTN_CMN_RTN_BTN_NM;
import static business.servlet.NMAL7160.constant.NMAL7160Constant.BTN_CMN_RTN_EVENT_NM;
import static business.servlet.NMAL7160.constant.NMAL7160Constant.BTN_CMN_RTN_LABEL;
import static business.servlet.NMAL7160.constant.NMAL7160Constant.BTN_CMN_SAV_BTN_NM;
import static business.servlet.NMAL7160.constant.NMAL7160Constant.BTN_CMN_SAV_EVENT_NM;
import static business.servlet.NMAL7160.constant.NMAL7160Constant.BTN_CMN_SAV_LABEL;
import static business.servlet.NMAL7160.constant.NMAL7160Constant.BTN_CMN_SUB_BTN_NM;
import static business.servlet.NMAL7160.constant.NMAL7160Constant.BTN_CMN_SUB_EVENT_NM;
import static business.servlet.NMAL7160.constant.NMAL7160Constant.BTN_CMN_SUB_LABEL;
import static business.servlet.NMAL7160.constant.NMAL7160Constant.BTN_SEARCH;
import static business.servlet.NMAL7160.constant.NMAL7160Constant.IDX_7;
import static business.servlet.NMAL7160.constant.NMAL7160Constant.SCREEN_ID;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBItem;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL7160.NMAL7160BMsg;
import business.servlet.NMAL7160.NMAL7160Bean;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * CSMP Manual Claim Search&Entry NMAL7160CommonLogic
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/17   Fujitsu         T.Murai         Create          N/A
 *</pre>
 */

public class NMAL7160CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param profile S21UserProfileService
     * @param scrnMsg NMAL7160BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, S21UserProfileService profile, NMAL7160BMsg scrnMsg) {
        initCommonButton(handler);
        initButton(handler);
        changeActivation(handler, profile, scrnMsg);
        initProtect(scrnMsg);
    }

    /**
     * <pre>
     * Initial common button
     * </pre>
     * @param handler EZCommandHandler
     */
    public static void initCommonButton(EZDCommonHandler handler) {
        handler.setButtonProperties(BTN_CMN_SAV_BTN_NM, BTN_CMN_SAV_EVENT_NM, BTN_CMN_SAV_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_SUB_BTN_NM, BTN_CMN_SUB_EVENT_NM, BTN_CMN_SUB_LABEL, 1, null);
        handler.setButtonProperties(BTN_CMN_APL_BTN_NM, BTN_CMN_APL_EVENT_NM, BTN_CMN_APL_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_ARV_BTN_NM, BTN_CMN_ARV_EVENT_NM, BTN_CMN_ARV_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_RJT_BTN_NM, BTN_CMN_RJT_EVENT_NM, BTN_CMN_RJT_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_DWL_BTN_NM, BTN_CMN_DWL_EVENT_NM, BTN_CMN_DWL_LABEL, 1, null);
        handler.setButtonProperties(BTN_CMN_DEL_BTN_NM, BTN_CMN_DEL_EVENT_NM, BTN_CMN_DEL_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_CLR_BTN_NM, BTN_CMN_CLR_EVENT_NM, BTN_CMN_CLR_LABEL, 1, null);
        handler.setButtonProperties(BTN_CMN_RST_BTN_NM, BTN_CMN_RST_EVENT_NM, BTN_CMN_RST_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_RTN_BTN_NM, BTN_CMN_RTN_EVENT_NM, BTN_CMN_RTN_LABEL, 1, null);

    }

    /**
     * <pre>
     * protect common button
     * </pre>
     * @param handler EZCommandHandler
     */
    public static void protectCommonButton(EZDCommonHandler handler) {
        handler.setButtonProperties(BTN_CMN_SAV_BTN_NM, BTN_CMN_SAV_EVENT_NM, BTN_CMN_SAV_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_SUB_BTN_NM, BTN_CMN_SUB_EVENT_NM, BTN_CMN_SUB_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_APL_BTN_NM, BTN_CMN_APL_EVENT_NM, BTN_CMN_APL_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_ARV_BTN_NM, BTN_CMN_ARV_EVENT_NM, BTN_CMN_ARV_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_RJT_BTN_NM, BTN_CMN_RJT_EVENT_NM, BTN_CMN_RJT_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_DWL_BTN_NM, BTN_CMN_DWL_EVENT_NM, BTN_CMN_DWL_LABEL, 1, null);
        handler.setButtonProperties(BTN_CMN_DEL_BTN_NM, BTN_CMN_DEL_EVENT_NM, BTN_CMN_DEL_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_CLR_BTN_NM, BTN_CMN_CLR_EVENT_NM, BTN_CMN_CLR_LABEL, 1, null);
        handler.setButtonProperties(BTN_CMN_RST_BTN_NM, BTN_CMN_RST_EVENT_NM, BTN_CMN_RST_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_RTN_BTN_NM, BTN_CMN_RTN_EVENT_NM, BTN_CMN_RTN_LABEL, 1, null);
    }

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     */
    public static void initButton(EZDCommonHandler handler) {
        handler.setButtonEnabled(BTN_SEARCH, true);
    }

    /**
     * The initial protect
     * @param scrnMsg NMAL7160BMsg
     */
    public static void initProtect(NMAL7160BMsg scrnMsg) {
        scrnMsg.mdseCd_LK.setValue(ZYPConstant.FLG_ON_Y);
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).mdseDescShortTxt_A.setInputProtected(true);
        }
    }

    /**
     * protect by Auth
     * @param handler EZDCommonHandler
     * @param profile S21UserProfileService
     * @param scrnMsg NMAL7160BMsg
     */
    public static void changeActivation(EZDCommonHandler handler, S21UserProfileService profile, NMAL7160BMsg scrnMsg) {

        String user = profile.getContextUserInfo().getUserId();
        boolean authEdit = profile.isFunctionGranted(user, AUTH_EDIT);
        if (authEdit) {
            initCommonButton(handler);
        } else {
            protectCommonButton(handler);
        }
    }

    /**
     * set Table's Back Color
     * @param scrnMsg NMAL7160BMsg
     */
    public static final void setTblBackColor(NMAL7160BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG("A", scrnMsg.A);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
    }

    /**
     * Input Check 
     * @param scrnMsg NMAL7160BMsg
     */
    public static void allAddCheck(NMAL7160BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.cpoOrdNum);
        scrnMsg.addCheckItem(scrnMsg.mdseCd);
        scrnMsg.addCheckItem(scrnMsg.invNum);
        scrnMsg.addCheckItem(scrnMsg.csmpContrNum);
        scrnMsg.addCheckItem(scrnMsg.dlrRefNum);
        scrnMsg.addCheckItem(scrnMsg.csmpInvErrDt_FR);
        scrnMsg.addCheckItem(scrnMsg.csmpInvErrDt_TO);
        scrnMsg.A.setCheckParam(new String[] {NMAL7160Bean.xxChkBox_A}, 1);
        scrnMsg.addCheckItem(scrnMsg.A);

        scrnMsg.putErrorScreen();
    }

    /**
     * Get Param NMAL6800
     * @param scrnMsg NMAL7160BMsg
     * @return Param NMAL6800
     */
    public static Object[] getParamNMAL6800(NMAL7160BMsg scrnMsg) {

        scrnMsg.xxPopPrm_00.clear();

        List<EZDBItem> paramList = new ArrayList<EZDBItem>(IDX_7);
        paramList.add(scrnMsg.mdseCd);
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used

        return paramList.toArray(new EZDBItem[paramList.size()]);
    }

}
