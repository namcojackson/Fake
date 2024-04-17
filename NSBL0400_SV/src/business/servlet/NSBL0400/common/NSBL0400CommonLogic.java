/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0400.common;


import java.util.List;

import static business.servlet.NSBL0400.constant.NSBL0400Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.*;
import parts.common.EZDBMsgArray;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSBL0400.NSBL0400BMsg;
import business.servlet.NSBL0400.NSBL0400_ABMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Meter Reading Counter for Interface Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/14   Hitachi         M.Gotou         Create          N/A
 * 2016/04/11   Hitachi         M.Gotou         Update          QC#4716
 * 2018/05/24   Hitachi         U.Kim           Update          QC#22393
 *</pre>
 */
public class NSBL0400CommonLogic {

    /**
     * activateButtons
     * @param handler S21CommonHandler
     * @param functionList  List<String>
     */
    public static void activateButtons(S21CommonHandler handler, List<String> functionList) {

        handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 0, null);
        handler.setButtonEnabled(BTN_SEARCH, false);
        handler.setButtonEnabled(BTN_NEW, false);

        if (functionList == null || functionList.isEmpty()) {
            handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        } else if (functionList.contains(FUNC_ID_INQ)) {
            handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 1, null);
            handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
            handler.setButtonEnabled(BTN_SEARCH, true);
            handler.setButtonEnabled(BTN_NEW, true);
        } else {
            handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        }
    }

    /**
     * commonAddCheckItem
     * @param scrnMsg NSBL0400BMsg
     */
    public static void commonAddCheckItem(NSBL0400BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.svcModYr);
        scrnMsg.addCheckItem(scrnMsg.svcModMth);
        scrnMsg.addCheckItem(scrnMsg.svcModDay);
        scrnMsg.addCheckItem(scrnMsg.svcMnfCd);
        scrnMsg.addCheckItem(scrnMsg.svcModSqNum);
        scrnMsg.addCheckItem(scrnMsg.svcModNm);
        scrnMsg.addCheckItem(scrnMsg.mdseCd);
        scrnMsg.addCheckItem(scrnMsg.svcMnfModNum);
        // START 2018/05/24 U.Kim [QC#22393, ADD]
        scrnMsg.addCheckItem(scrnMsg.serNum);
        // END 2018/05/24 U.Kim [QC#22393, ADD]
    }

    /**
     * inputCheckMondatory
     * @param scrnMsg NSBL0400BMsg
     */
    public static final void inputCheckMondatory(NSBL0400BMsg scrnMsg) {
        boolean isError = true;
        if (hasValue(scrnMsg.svcModYr)) {
            isError = false;
        } else if (hasValue(scrnMsg.svcModMth)) {
            isError = false;
        } else if (hasValue(scrnMsg.svcModDay)) {
            isError = false;
        } else if (hasValue(scrnMsg.svcMnfCd)) {
            isError = false;
        } else if (hasValue(scrnMsg.svcModSqNum)) {
            isError = false;
        } else if (hasValue(scrnMsg.svcModNm)) {
            isError = false;
        } else if (hasValue(scrnMsg.mdseCd)) {
            isError = false;
        } else if (hasValue(scrnMsg.svcMnfModNum)) {
            isError = false;
        // START 2018/05/24 U.Kim [QC#22393, ADD]
        } else if (hasValue(scrnMsg.serNum)) {
            isError = false;
        // END 2018/05/24 U.Kim [QC#22393, ADD]
        }

        if (isError) {
            scrnMsg.svcModYr.setErrorInfo(1, NSBM0001E);
            scrnMsg.svcModMth.setErrorInfo(1, NSBM0001E);
            scrnMsg.svcModDay.setErrorInfo(1, NSBM0001E);
            scrnMsg.svcMnfCd.setErrorInfo(1, NSBM0001E);
            scrnMsg.svcModSqNum.setErrorInfo(1, NSBM0001E);
            scrnMsg.svcModNm.setErrorInfo(1, NSBM0001E);
            scrnMsg.mdseCd.setErrorInfo(1, NSBM0001E);
            scrnMsg.svcMnfModNum.setErrorInfo(1, NSBM0001E);
            // START 2018/05/24 U.Kim [QC#22393, ADD]
            scrnMsg.serNum.setErrorInfo(1, NSBM0001E);
            // END 2018/05/24 U.Kim [QC#22393, ADD]

            commonAddCheckItem(scrnMsg);
        }
    }

    /**
     * control Inactive Field
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NSBL0400BMsg
     */
    public static void protectFields(//
            EZDCommonHandler scrnAppli //
            , NSBL0400BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSBL0400_ABMsg abMsg = scrnMsg.A.no(i);

            // mod start 2016/04/11 CSA Defect#4716
            abMsg.xxDplyByCdNmCnctTxt_A.setInputProtected(true);
            // mod end 2016/04/11 CSA Defect#4716
            abMsg.svcModPlnId_A.setInputProtected(true);
            abMsg.svcModNm_A.setInputProtected(true);
            abMsg.svcModCmntTxt_A.setInputProtected(true);
        }
    }

    /**
     * setRowColors
     * @param scrnMsg NSBL0400BMsg
     */
    public static void setRowColors(NSBL0400BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        try {
            EZDBMsgArray table = (EZDBMsgArray) scrnMsg.getClass().getField("A").get(scrnMsg);
            tblColor.setAlternateRowsBG("A", table);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    // START 2018/05/24 U.Kim [QC#22393, ADD]
    /**
     * set Config Search Pop Up Input Param
     * @param scrnMsg NSAL0010BMsg
     * @param mode String
     * @param serNumSetFlag boolean
     * @return params
     */
    public static Object[] setConfigSearchPopUpInputParam(NSBL0400BMsg scrnMsg) {
        Object[] params = new Object[PARAM_LENGTH_NSAL1240];
        if (ZYPCommonFunc.hasValue(scrnMsg.serNum)) {
            params[PARAM_INDEX_02] = scrnMsg.serNum;
        }
        params[PARAM_INDEX_30] = scrnMsg.O;
        return params;
    }
    // END 2018/05/24 U.Kim [QC#22393, ADD]
}
