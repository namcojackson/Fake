/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSBL0420.common;

import static business.servlet.NSBL0420.constant.NSBL0420Constant.BTN_LBL;
import static business.servlet.NSBL0420.constant.NSBL0420Constant.BUSINESS_ID;
import static business.servlet.NSBL0420.constant.NSBL0420Constant.EDIT_MODE;
import static business.servlet.NSBL0420.constant.NSBL0420Constant.SCREEN_ID;
import static business.servlet.NSBL0420.constant.NSBL0420Constant.ZZSM4300E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.util.ArrayList;

import parts.common.EZDBMsgArray;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSBL0420.NSBL0420BMsg;
import business.servlet.NSBL0420.NSBL0420Bean;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/** 
 *<pre>
 * Mods Parts Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/18   Hitachi         T.Tsuchida      Create          N/A
 * 2016/02/03   Hitachi         T.Iwamoto       Update          QC#3993
 * 2016/07/13   Hitachi         O.Okuma         Update          QC#11685
 *</pre>
 */
public class NSBL0420CommonLogic {
    private static S21UserProfileService getUserProfileService() {
        return S21UserProfileServiceFactory.getInstance().getService();
    }

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0420BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NSBL0420BMsg scrnMsg) {
        ArrayList<String> functionList = (ArrayList<String>) getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_ID);

        if (functionList.contains("NSBL0420T020") && EDIT_MODE.equals(scrnMsg.xxModeCd.getValue()) && hasValue(scrnMsg.svcModDtlPk)) {
            initCommonButton(handler, scrnMsg);
        } else if (functionList.contains("NSBL0420T010") && EDIT_MODE.equals(scrnMsg.xxModeCd.getValue()) && hasValue(scrnMsg.svcModDtlPk)) {
            scrnMsg.setMessageInfo(ZZSM4300E, new String[]{S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId()});
            ZYPTableUtil.clear(scrnMsg.A);
            initInactiveCommonButton(handler, scrnMsg);
        } else {
            initInactiveCommonButton(handler, scrnMsg);
        }
        controlScreenFields(handler, scrnMsg);
        setRowColors(scrnMsg);
    }

    /**
     * Method name: initCommonButton <dd>The method explanation: init
     * Common Button Control. <dd>Remarks:
     * @param handler EZ Common Handler
     * @param scrnMsg NSBL0420BMsg
     */
    private static final void initCommonButton(EZDCommonHandler handler, NSBL0420BMsg scrnMsg) {
        handler.setButtonProperties(BTN_LBL.SAVE.getOrgNm(), BTN_LBL.SAVE.getGuardNm(), BTN_LBL.SAVE.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.SUBMIT.getOrgNm(), BTN_LBL.SUBMIT.getGuardNm(), BTN_LBL.SUBMIT.getLblNm(), 1, null);
        handler.setButtonProperties(BTN_LBL.APPLY.getOrgNm(), BTN_LBL.APPLY.getGuardNm(), BTN_LBL.APPLY.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.APPROVE.getOrgNm(), BTN_LBL.APPROVE.getGuardNm(), BTN_LBL.APPROVE.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.REJECT.getOrgNm(), BTN_LBL.REJECT.getGuardNm(), BTN_LBL.REJECT.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.DOWNLOAD.getOrgNm(), BTN_LBL.DOWNLOAD.getGuardNm(), BTN_LBL.DOWNLOAD.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.DELETE.getOrgNm(), BTN_LBL.DELETE.getGuardNm(), BTN_LBL.DELETE.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.CLEAR.getOrgNm(), BTN_LBL.CLEAR.getGuardNm(), BTN_LBL.CLEAR.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.RESET.getOrgNm(), BTN_LBL.RESET.getGuardNm(), BTN_LBL.RESET.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.RETURN.getOrgNm(), BTN_LBL.RETURN.getGuardNm(), BTN_LBL.RETURN.getLblNm(), 1, null);

        handler.setButtonProperties(BTN_LBL.ADD.getOrgNm(), BTN_LBL.ADD.getGuardNm(), BTN_LBL.ADD.getLblNm(), 1, null);
        handler.setButtonProperties(BTN_LBL.SELECT_ALL.getOrgNm(), BTN_LBL.SELECT_ALL.getGuardNm(), BTN_LBL.SELECT_ALL.getLblNm(), 1, null);
        handler.setButtonProperties(BTN_LBL.UNSELECT_ALL.getOrgNm(), BTN_LBL.UNSELECT_ALL.getGuardNm(), BTN_LBL.UNSELECT_ALL.getLblNm(), 1, null);
        handler.setButtonProperties(BTN_LBL.DELETE_DETAIL.getOrgNm(), BTN_LBL.DELETE_DETAIL.getGuardNm(), BTN_LBL.DELETE_DETAIL.getLblNm(), 1, null);
        handler.setButtonProperties(BTN_LBL.PREV.getOrgNm(), BTN_LBL.PREV.getGuardNm(), BTN_LBL.PREV.getLblNm(), 1, null);
        handler.setButtonProperties(BTN_LBL.NEXT.getOrgNm(), BTN_LBL.NEXT.getGuardNm(), BTN_LBL.NEXT.getLblNm(), 1, null);
    }

    /**
     * Method name: initInactiveCommonButton <dd>The method explanation: init_referencemode
     * Common Button Control. <dd>Remarks:
     * @param handler EZ Common Handler
     * @param scrnMsg NSBL0420BMsg
     */
    private static final void initInactiveCommonButton(EZDCommonHandler handler, NSBL0420BMsg scrnMsg) {
        handler.setButtonProperties(BTN_LBL.SAVE.getOrgNm(), BTN_LBL.SAVE.getGuardNm(), BTN_LBL.SAVE.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.SUBMIT.getOrgNm(), BTN_LBL.SUBMIT.getGuardNm(), BTN_LBL.SUBMIT.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.APPLY.getOrgNm(), BTN_LBL.APPLY.getGuardNm(), BTN_LBL.APPLY.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.APPROVE.getOrgNm(), BTN_LBL.APPROVE.getGuardNm(), BTN_LBL.APPROVE.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.REJECT.getOrgNm(), BTN_LBL.REJECT.getGuardNm(), BTN_LBL.REJECT.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.DOWNLOAD.getOrgNm(), BTN_LBL.DOWNLOAD.getGuardNm(), BTN_LBL.DOWNLOAD.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.DELETE.getOrgNm(), BTN_LBL.DELETE.getGuardNm(), BTN_LBL.DELETE.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.CLEAR.getOrgNm(), BTN_LBL.CLEAR.getGuardNm(), BTN_LBL.CLEAR.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.RESET.getOrgNm(), BTN_LBL.RESET.getGuardNm(), BTN_LBL.RESET.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.RETURN.getOrgNm(), BTN_LBL.RETURN.getGuardNm(), BTN_LBL.RETURN.getLblNm(), 1, null);

        handler.setButtonProperties(BTN_LBL.ADD.getOrgNm(), BTN_LBL.ADD.getGuardNm(), BTN_LBL.ADD.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.SELECT_ALL.getOrgNm(), BTN_LBL.SELECT_ALL.getGuardNm(), BTN_LBL.SELECT_ALL.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.UNSELECT_ALL.getOrgNm(), BTN_LBL.UNSELECT_ALL.getGuardNm(), BTN_LBL.UNSELECT_ALL.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.DELETE_DETAIL.getOrgNm(), BTN_LBL.DELETE_DETAIL.getGuardNm(), BTN_LBL.DELETE_DETAIL.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.PREV.getOrgNm(), BTN_LBL.PREV.getGuardNm(), BTN_LBL.PREV.getLblNm(), 1, null);
        handler.setButtonProperties(BTN_LBL.NEXT.getOrgNm(), BTN_LBL.NEXT.getGuardNm(), BTN_LBL.NEXT.getLblNm(), 1, null);

    }

    /**
     * Control screen fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0420BMsg
     */
    private static final void controlScreenFields(EZDCommonHandler handler, NSBL0420BMsg scrnMsg) {
        if (scrnMsg.xxModeCd.getValue().equals(EDIT_MODE) && hasValue(scrnMsg.svcModDtlPk)) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).xxChkBox_A.setInputProtected(false);
                scrnMsg.A.no(i).svcModQty_A.setInputProtected(false);
                // Start QC#3993 2016/02/03 T.Iwamoto Add
                scrnMsg.A.no(i).mdseDescShortTxt_A.setInputProtected(true);
                // End QC#3993 2016/02/03 T.Iwamoto Add
            }
            scrnMsg.mdseCd_AC.setInputProtected(false);
            scrnMsg.mdseCd_TF.setInputProtected(false);
        } else {
            for (int j = 0; j < scrnMsg.A.getValidCount(); j++) {
                scrnMsg.A.no(j).xxChkBox_A.setInputProtected(true);
                scrnMsg.A.no(j).svcModQty_A.setInputProtected(true);
                // Start QC#3993 2016/02/03 T.Iwamoto Add
                scrnMsg.A.no(j).mdseDescShortTxt_A.setInputProtected(true);
                // End QC#3993 2016/02/03 T.Iwamoto Add
            }
            scrnMsg.mdseCd_AC.setInputProtected(true);
            scrnMsg.mdseCd_TF.setInputProtected(true);
        }
    }

    /**
     * setRowColors
     * @param scrnMsg NSBL0420BMsg
     */
    private static void setRowColors(NSBL0420BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        try {
            EZDBMsgArray table = (EZDBMsgArray) scrnMsg.getClass().getField("A").get(scrnMsg);
            tblColor.setAlternateRowsBG("A", table);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NSBL0420BMsg
     */
    public static void initParam(NSBL0420BMsg scrnMsg) {
        scrnMsg.xxPopPrm_0.clear();
        scrnMsg.xxPopPrm_1.clear();
        scrnMsg.xxPopPrm_2.clear();
        scrnMsg.xxPopPrm_3.clear();
        scrnMsg.xxPopPrm_4.clear();
        scrnMsg.xxPopPrm_5.clear();
        scrnMsg.xxPopPrm_6.clear();
    }

    /**
     * addCheckItem
     * @param scrnMsg NSBL0420BMsg
     */
    public static void addCheckItem(NSBL0420BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.mdseCd_TF);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.setCheckParam(new String[] {NSBL0420Bean.xxChkBox_A, NSBL0420Bean.svcModQty_A }, 1);
            scrnMsg.addCheckItem(scrnMsg.A);
        }
    }

    /**
     * <pre>
     * checkInput for table
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0420BMsg
     */
    public static final void checkInputForTable(EZDCommonHandler handler, NSBL0420BMsg scrnMsg) {
        scrnMsg.A.setCheckParam(new String[] {NSBL0420Bean.xxChkBox_A, NSBL0420Bean.svcModQty_A }, 1);
        scrnMsg.addCheckItem(scrnMsg.A);
    }

    // add start 2016/07/13 CSA Defect#11685
    /**
    * addCheckItemDtl
    * @param scrnMsg NSBL0420BMsg
    */
   public static void setFocus(NSBL0420BMsg scrnMsg) {

       if (scrnMsg.A.getValidCount() > 0) {
           scrnMsg.setFocusItem(scrnMsg.A.no(0).svcModQty_A);
       } else {
           scrnMsg.setFocusItem(scrnMsg.mdseCd_TF);
       }
   }
   // add end 2016/07/13 CSA Defect#11685
}
