/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1220.common;

import static business.servlet.NSAL1220.constant.NSAL1220Constant.SCREEN_ID;
import static business.servlet.NSAL1220.constant.NSAL1220Constant.*;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

import parts.common.EZDBMsgArray;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL1220.NSAL1220BMsg;
import business.servlet.NSAL1220.NSAL1220Bean;

/**
 *<pre>
 * Contract Branch Revenue Distribution
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/14   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public class NSAL1220CommonLogic {
    private static S21UserProfileService getUserProfileService() {
        return S21UserProfileServiceFactory.getInstance().getService();
    }

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL1220BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NSAL1220BMsg scrnMsg) {
        ArrayList<String> functionList = (ArrayList<String>) getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_ID);

        if (functionList.contains("NSAL1220T020") && EDIT_MODE.equals(scrnMsg.xxModeCd.getValue())) {
            initCommonButton(handler, scrnMsg);
        } else if (functionList.contains("NSAL1220T010") && EDIT_MODE.equals(scrnMsg.xxModeCd.getValue())) {
            scrnMsg.setMessageInfo(ZZSM4300E, new String[]{S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId()});
            ZYPTableUtil.clear(scrnMsg.A);
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcAllocRate_T, BigDecimal.ZERO);
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
     * @param scrnMsg NSAL1220BMsg
     */
    public static final void initCommonButton(EZDCommonHandler handler, NSAL1220BMsg scrnMsg) {
        handler.setButtonProperties(BTN_SELECTALL[0], BTN_SELECTALL[1], BTN_SELECTALL[2], 1, null);
        handler.setButtonProperties(BTN_UNSELECTALL[0], BTN_UNSELECTALL[1], BTN_UNSELECTALL[2], 1, null);
        handler.setButtonProperties(BTN_OPENPOPUP[0], BTN_OPENPOPUP[1], BTN_OPENPOPUP[2], 1, null);
        handler.setButtonProperties(BTN_ADDROW[0], BTN_ADDROW[1], BTN_ADDROW[2], 1, null);
        handler.setButtonProperties(BTN_DELETEROW[0], BTN_DELETEROW[1], BTN_DELETEROW[2], 1, null);
        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK6[0], BTN_CMN_BLANK6[1], BTN_CMN_BLANK6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK7[0], BTN_CMN_BLANK7[1], BTN_CMN_BLANK7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
    }

    /**
     * Method name: initInactiveCommonButton <dd>The method explanation: init_referencemode
     * Common Button Control. <dd>Remarks:
     * @param handler EZ Common Handler
     * @param scrnMsg NSAL1220BMsg
     */
    public static final void initInactiveCommonButton(EZDCommonHandler handler, NSAL1220BMsg scrnMsg) {
        handler.setButtonProperties(BTN_SELECTALL[0], BTN_SELECTALL[1], BTN_SELECTALL[2], 0, null);
        handler.setButtonProperties(BTN_UNSELECTALL[0], BTN_UNSELECTALL[1], BTN_UNSELECTALL[2], 0, null);
        handler.setButtonProperties(BTN_OPENPOPUP[0], BTN_OPENPOPUP[1], BTN_OPENPOPUP[2], 0, null);
        handler.setButtonProperties(BTN_ADDROW[0], BTN_ADDROW[1], BTN_ADDROW[2], 0, null);
        handler.setButtonProperties(BTN_DELETEROW[0], BTN_DELETEROW[1], BTN_DELETEROW[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK6[0], BTN_CMN_BLANK6[1], BTN_CMN_BLANK6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK7[0], BTN_CMN_BLANK7[1], BTN_CMN_BLANK7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
    }

    /**
     * Control screen fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL1220BMsg
     */
    private static final void controlScreenFields(EZDCommonHandler handler, NSAL1220BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (scrnMsg.xxModeCd.getValue().equals(REF_MODE)) {
                scrnMsg.A.no(i).xxChkBox_A.setInputProtected(true);
                scrnMsg.A.no(i).coaBrCd_A.setInputProtected(true);
                scrnMsg.A.no(i).prcAllocRate_A.setInputProtected(true);
                scrnMsg.A.no(i).prcAllocRate_A.setAppFracDigit(2);
            } else {
                scrnMsg.A.no(i).xxChkBox_A.setInputProtected(false);
                scrnMsg.A.no(i).coaBrCd_A.setInputProtected(false);
                scrnMsg.A.no(i).prcAllocRate_A.setInputProtected(false);
                scrnMsg.A.no(i).prcAllocRate_A.setAppFracDigit(2);
            }
        }
        scrnMsg.prcAllocRate_T.setAppFracDigit(2);
    }

    /**
     * setRowColors
     * @param scrnMsg NSAL0630BMsg
     */
    private static void setRowColors(NSAL1220BMsg scrnMsg) {

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
     * @param scrnMsg NMAL1220BMsg
     * @param i int
     * @return Object[]
     */
    public static Object[] setParamForOrganizationSearchPopup(NSAL1220BMsg scrnMsg, int i) {

        initParam(scrnMsg);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_0, BUSINESS_ID);
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).coaBrCd_A)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_3, scrnMsg.A.no(i).coaBrCd_A);
        }
        Object[] params = new Object[POP_PARAM_NUM];
        int index = 0;
        params[index++] = scrnMsg.xxPopPrm_0;
        params[index++] = scrnMsg.xxPopPrm_1;
        params[index++] = scrnMsg.xxPopPrm_2;
        params[index++] = scrnMsg.xxPopPrm_3;
        params[index++] = scrnMsg.xxPopPrm_4;
        params[index++] = scrnMsg.xxPopPrm_5;
        params[index++] = scrnMsg.xxPopPrm_6;
        params[index++] = scrnMsg.xxPopPrm_7;
        params[index++] = scrnMsg.xxPopPrm_8;
        params[index++] = scrnMsg.xxPopPrm_9;

        return params;
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NSAL1220BMsg
     */
    private static void initParam(NSAL1220BMsg scrnMsg) {
        scrnMsg.xxPopPrm_0.clear();
        scrnMsg.xxPopPrm_1.clear();
        scrnMsg.xxPopPrm_2.clear();
        scrnMsg.xxPopPrm_3.clear();
        scrnMsg.xxPopPrm_4.clear();
        scrnMsg.xxPopPrm_5.clear();
        scrnMsg.xxPopPrm_6.clear();
        scrnMsg.xxPopPrm_7.clear();
        scrnMsg.xxPopPrm_8.clear();
        scrnMsg.xxPopPrm_9.clear();
    }

    /**
     * addCheckItem
     * @param scrnMsg NSAL1220BMsg
     */
    public static void addCheckItem(NSAL1220BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.setCheckParam(new String[] {NSAL1220Bean.coaBrCd_A, NSAL1220Bean.prcAllocRate_A }, 1);
            scrnMsg.addCheckItem(scrnMsg.A);
        }
   }

}
