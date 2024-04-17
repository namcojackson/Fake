/**
 * <Pre>Copyright (c) 2011 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NMAL0250.common;

import java.util.List;

import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL0250.NMAL0250BMsg;
import business.servlet.NMAL0250.NMAL0250_RBMsgArray;
import business.servlet.NMAL0250.constant.NMAL0250Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * BOM Text Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/02/28   CITS            K.Kameoka       Create          #22324
 */
public class NMAL0250CommonLogic implements NMAL0250Constant {

    /**
     * Control items on INIT event
     * @param handler EZDCommonHandler
     * @param bMsg NMAL0250BMsg
     */
    public static void controlItemsOnInit(EZDCommonHandler handler, NMAL0250BMsg bMsg) {
        controlItems(handler, bMsg);
        controlButtons(handler, bMsg);
        setFocus(bMsg);
    }

    /**
     * Control items on Clear event
     * @param handler EZDCommonHandler
     * @param bMsg NMAL0250BMsg
     */
    public static void controlItemsOnClear(EZDCommonHandler handler, NMAL0250BMsg bMsg) {
        bMsg.cmpsnMsgTxt_A0.clear();
        bMsg.cmpsnMsgTxt_A1.clear();
        bMsg.cmpsnMsgTxt_A2.clear();
        bMsg.cmpsnMsgTxt_A3.clear();
        bMsg.cmpsnMsgTxt_A4.clear();
        bMsg.cmpsnMsgTxt_A5.clear();
        bMsg.cmpsnMsgTxt_A6.clear();
        bMsg.cmpsnMsgTxt_A7.clear();
        bMsg.cmpsnMsgTxt_A8.clear();
        bMsg.cmpsnMsgTxt_A9.clear();
        bMsg.cmpsnMsgTxt_AA.clear();
        bMsg.cmpsnMsgTxt_AB.clear();
        bMsg.cmpsnMsgTxt_AC.clear();
        bMsg.cmpsnMsgTxt_AD.clear();
        bMsg.cmpsnMsgTxt_AE.clear();
        controlItemsOnInit(handler, bMsg);
    }

    /**
     * set output parameter
     * @param arg Object[]
     * @param resultArray NMAL0250_RBMsgArray
     */
    public static void setOutputParam(Object[] arg, NMAL0250_RBMsgArray resultArray) {

        if (!(arg instanceof Object[])) {
            return;
        }

        if (arg[1] instanceof EZDMsgArray) {
            EZDMsg.copy(resultArray, null, (EZDMsgArray) arg[1], null);
        }
    }

    /**
     * Control items on Return event
     * @param scrnMsg NMAL0250BMsg
     * @param resultArray NMAL0250_RBMsgArray
     */
    public static void setResult(NMAL0250BMsg scrnMsg, NMAL0250_RBMsgArray resultArray) {
        for (int i = 0; i < resultArray.length(); i++) {
            switch (i) {
                case 0:
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).prntMdseCd_R, scrnMsg.mdseCd);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgPk, scrnMsg.cmpsnMsgPk_A0);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgSegId, scrnMsg.cmpsnMsgSegId_A0);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgTxt, scrnMsg.cmpsnMsgTxt_A0);
                    break;
                case 1:
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).prntMdseCd_R, scrnMsg.mdseCd);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgPk, scrnMsg.cmpsnMsgPk_A1);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgSegId, scrnMsg.cmpsnMsgSegId_A1);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgTxt, scrnMsg.cmpsnMsgTxt_A1);
                    break;
                case 2:
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).prntMdseCd_R, scrnMsg.mdseCd);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgPk, scrnMsg.cmpsnMsgPk_A2);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgSegId, scrnMsg.cmpsnMsgSegId_A2);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgTxt, scrnMsg.cmpsnMsgTxt_A2);
                    break;
                case 3:
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).prntMdseCd_R, scrnMsg.mdseCd);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgPk, scrnMsg.cmpsnMsgPk_A3);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgSegId, scrnMsg.cmpsnMsgSegId_A3);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgTxt, scrnMsg.cmpsnMsgTxt_A3);
                    break;
                case 4:
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).prntMdseCd_R, scrnMsg.mdseCd);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgPk, scrnMsg.cmpsnMsgPk_A4);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgSegId, scrnMsg.cmpsnMsgSegId_A4);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgTxt, scrnMsg.cmpsnMsgTxt_A4);
                    break;
                case 5:
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).prntMdseCd_R, scrnMsg.mdseCd);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgPk, scrnMsg.cmpsnMsgPk_A5);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgSegId, scrnMsg.cmpsnMsgSegId_A5);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgTxt, scrnMsg.cmpsnMsgTxt_A5);
                    break;
                case 6:
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).prntMdseCd_R, scrnMsg.mdseCd);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgPk, scrnMsg.cmpsnMsgPk_A6);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgSegId, scrnMsg.cmpsnMsgSegId_A6);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgTxt, scrnMsg.cmpsnMsgTxt_A6);
                    break;
                case 7:
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).prntMdseCd_R, scrnMsg.mdseCd);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgPk, scrnMsg.cmpsnMsgPk_A7);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgSegId, scrnMsg.cmpsnMsgSegId_A7);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgTxt, scrnMsg.cmpsnMsgTxt_A7);
                    break;
                case 8:
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).prntMdseCd_R, scrnMsg.mdseCd);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgPk, scrnMsg.cmpsnMsgPk_A8);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgSegId, scrnMsg.cmpsnMsgSegId_A8);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgTxt, scrnMsg.cmpsnMsgTxt_A8);
                    break;
                case 9:
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).prntMdseCd_R, scrnMsg.mdseCd);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgPk, scrnMsg.cmpsnMsgPk_A9);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgSegId, scrnMsg.cmpsnMsgSegId_A9);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgTxt, scrnMsg.cmpsnMsgTxt_A9);
                    break;
                case 10:
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).prntMdseCd_R, scrnMsg.mdseCd);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgPk, scrnMsg.cmpsnMsgPk_AA);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgSegId, scrnMsg.cmpsnMsgSegId_AA);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgTxt, scrnMsg.cmpsnMsgTxt_AA);
                    break;
                case 11:
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).prntMdseCd_R, scrnMsg.mdseCd);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgPk, scrnMsg.cmpsnMsgPk_AB);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgSegId, scrnMsg.cmpsnMsgSegId_AB);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgTxt, scrnMsg.cmpsnMsgTxt_AB);
                    break;
                case 12:
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).prntMdseCd_R, scrnMsg.mdseCd);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgPk, scrnMsg.cmpsnMsgPk_AC);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgSegId, scrnMsg.cmpsnMsgSegId_AC);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgTxt, scrnMsg.cmpsnMsgTxt_AC);
                    break;
                case 13:
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).prntMdseCd_R, scrnMsg.mdseCd);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgPk, scrnMsg.cmpsnMsgPk_AD);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgSegId, scrnMsg.cmpsnMsgSegId_AD);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgTxt, scrnMsg.cmpsnMsgTxt_AD);
                    break;
                case 14:
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).prntMdseCd_R, scrnMsg.mdseCd);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgPk, scrnMsg.cmpsnMsgPk_AE);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgSegId, scrnMsg.cmpsnMsgSegId_AE);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgTxt, scrnMsg.cmpsnMsgTxt_AE);
                    break;
                default:
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).prntMdseCd_R, scrnMsg.mdseCd);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgPk, scrnMsg.cmpsnMsgPk_A0);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgSegId, scrnMsg.cmpsnMsgSegId_A0);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).cmpsnMsgTxt, scrnMsg.cmpsnMsgTxt_A0);
                    break;
            }
        }
    }

    /**
     * setInitMsg
     * @param scrnMsg NMAL0250BMsg
     * @param resultArray NMAL0250_RBMsgArray
     */
    public static void setInitMsg(NMAL0250BMsg scrnMsg, EZDMsgArray initArray) {

        EZDMsg.copy(initArray, null, scrnMsg.R, null);

        for (int i = 0; i < scrnMsg.R.length(); i++) {
            switch (i) {
                case 0:
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgPk_A0, scrnMsg.R.no(i).cmpsnMsgPk);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgSegId_A0, scrnMsg.R.no(i).cmpsnMsgSegId);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgTxt_A0, scrnMsg.R.no(i).cmpsnMsgTxt);
                    break;
                case 1:
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgPk_A1, scrnMsg.R.no(i).cmpsnMsgPk);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgSegId_A1, scrnMsg.R.no(i).cmpsnMsgSegId);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgTxt_A1, scrnMsg.R.no(i).cmpsnMsgTxt);
                    break;
                case 2:
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgPk_A2, scrnMsg.R.no(i).cmpsnMsgPk);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgSegId_A2, scrnMsg.R.no(i).cmpsnMsgSegId);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgTxt_A2, scrnMsg.R.no(i).cmpsnMsgTxt);
                    break;
                case 3:
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgPk_A3, scrnMsg.R.no(i).cmpsnMsgPk);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgSegId_A3, scrnMsg.R.no(i).cmpsnMsgSegId);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgTxt_A3, scrnMsg.R.no(i).cmpsnMsgTxt);
                    break;
                case 4:
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgPk_A4, scrnMsg.R.no(i).cmpsnMsgPk);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgSegId_A4, scrnMsg.R.no(i).cmpsnMsgSegId);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgTxt_A4, scrnMsg.R.no(i).cmpsnMsgTxt);
                    break;
                case 5:
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgPk_A5, scrnMsg.R.no(i).cmpsnMsgPk);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgSegId_A5, scrnMsg.R.no(i).cmpsnMsgSegId);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgTxt_A5, scrnMsg.R.no(i).cmpsnMsgTxt);
                    break;
                case 6:
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgPk_A6, scrnMsg.R.no(i).cmpsnMsgPk);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgSegId_A6, scrnMsg.R.no(i).cmpsnMsgSegId);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgTxt_A6, scrnMsg.R.no(i).cmpsnMsgTxt);
                    break;
                case 7:
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgPk_A7, scrnMsg.R.no(i).cmpsnMsgPk);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgSegId_A7, scrnMsg.R.no(i).cmpsnMsgSegId);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgTxt_A7, scrnMsg.R.no(i).cmpsnMsgTxt);
                    break;
                case 8:
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgPk_A8, scrnMsg.R.no(i).cmpsnMsgPk);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgSegId_A8, scrnMsg.R.no(i).cmpsnMsgSegId);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgTxt_A8, scrnMsg.R.no(i).cmpsnMsgTxt);
                    break;
                case 9:
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgPk_A9, scrnMsg.R.no(i).cmpsnMsgPk);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgSegId_A9, scrnMsg.R.no(i).cmpsnMsgSegId);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgTxt_A9, scrnMsg.R.no(i).cmpsnMsgTxt);
                    break;
                case 10:
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgPk_AA, scrnMsg.R.no(i).cmpsnMsgPk);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgSegId_AA, scrnMsg.R.no(i).cmpsnMsgSegId);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgTxt_AA, scrnMsg.R.no(i).cmpsnMsgTxt);
                    break;
                case 11:
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgPk_AB, scrnMsg.R.no(i).cmpsnMsgPk);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgSegId_AB, scrnMsg.R.no(i).cmpsnMsgSegId);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgTxt_AB, scrnMsg.R.no(i).cmpsnMsgTxt);
                    break;
                case 12:
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgPk_AC, scrnMsg.R.no(i).cmpsnMsgPk);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgSegId_AC, scrnMsg.R.no(i).cmpsnMsgSegId);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgTxt_AC, scrnMsg.R.no(i).cmpsnMsgTxt);
                    break;
                case 13:
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgPk_AD, scrnMsg.R.no(i).cmpsnMsgPk);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgSegId_AD, scrnMsg.R.no(i).cmpsnMsgSegId);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgTxt_AD, scrnMsg.R.no(i).cmpsnMsgTxt);
                    break;
                case 14:
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgPk_AE, scrnMsg.R.no(i).cmpsnMsgPk);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgSegId_AE, scrnMsg.R.no(i).cmpsnMsgSegId);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgTxt_AE, scrnMsg.R.no(i).cmpsnMsgTxt);
                    break;
                default:
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgPk_A0, scrnMsg.R.no(i).cmpsnMsgPk);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgSegId_A0, scrnMsg.R.no(i).cmpsnMsgSegId);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cmpsnMsgTxt_A0, scrnMsg.R.no(i).cmpsnMsgTxt);
                    break;
            }
        }
    }

    /**
     * hasExistMsgList
     * @param scrnMsg
     * @return true:exist / false: not exist
     */
    public static boolean hasExistMsgList(NMAL0250BMsg scrnMsg) {

        boolean msgExitsFlg = false;
        for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.R.no(i).cmpsnMsgTxt)) {
                msgExitsFlg = true;
                break;
            }
        }

        return msgExitsFlg;
    }

    private static void controlItems(EZDCommonHandler handler, NMAL0250BMsg bMsg) {

        bMsg.mdseCd.setInputProtected(true);
        bMsg.mdseDescShortTxt.setInputProtected(true);
        if (iscmpsnMsgUpdatable(bMsg)) {
            bMsg.cmpsnMsgTxt_A0.setInputProtected(false);
            bMsg.cmpsnMsgTxt_A1.setInputProtected(false);
            bMsg.cmpsnMsgTxt_A2.setInputProtected(false);
            bMsg.cmpsnMsgTxt_A3.setInputProtected(false);
            bMsg.cmpsnMsgTxt_A4.setInputProtected(false);
            bMsg.cmpsnMsgTxt_A5.setInputProtected(false);
            bMsg.cmpsnMsgTxt_A6.setInputProtected(false);
            bMsg.cmpsnMsgTxt_A7.setInputProtected(false);
            bMsg.cmpsnMsgTxt_A8.setInputProtected(false);
            bMsg.cmpsnMsgTxt_A9.setInputProtected(false);
            bMsg.cmpsnMsgTxt_AA.setInputProtected(false);
            bMsg.cmpsnMsgTxt_AB.setInputProtected(false);
            bMsg.cmpsnMsgTxt_AC.setInputProtected(false);
            bMsg.cmpsnMsgTxt_AD.setInputProtected(false);
            bMsg.cmpsnMsgTxt_AE.setInputProtected(false);
        } else {
            bMsg.cmpsnMsgTxt_A0.setInputProtected(true);
            bMsg.cmpsnMsgTxt_A1.setInputProtected(true);
            bMsg.cmpsnMsgTxt_A2.setInputProtected(true);
            bMsg.cmpsnMsgTxt_A3.setInputProtected(true);
            bMsg.cmpsnMsgTxt_A4.setInputProtected(true);
            bMsg.cmpsnMsgTxt_A5.setInputProtected(true);
            bMsg.cmpsnMsgTxt_A6.setInputProtected(true);
            bMsg.cmpsnMsgTxt_A7.setInputProtected(true);
            bMsg.cmpsnMsgTxt_A8.setInputProtected(true);
            bMsg.cmpsnMsgTxt_A9.setInputProtected(true);
            bMsg.cmpsnMsgTxt_AA.setInputProtected(true);
            bMsg.cmpsnMsgTxt_AB.setInputProtected(true);
            bMsg.cmpsnMsgTxt_AC.setInputProtected(true);
            bMsg.cmpsnMsgTxt_AD.setInputProtected(true);
            bMsg.cmpsnMsgTxt_AE.setInputProtected(true);
        }
    }

    private static void controlButtons(EZDCommonHandler handler, NMAL0250BMsg bMsg) {
        if (iscmpsnMsgUpdatable(bMsg)) {
            controlButtonsForUpdate(handler);
        } else {
            controlButtonsForReference(handler);
        }
    }

    private static void controlButtonsForUpdate(EZDCommonHandler handler) {

        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
    }

    private static void controlButtonsForReference(EZDCommonHandler handler) {

        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
    }

    private static boolean iscmpsnMsgUpdatable(NMAL0250BMsg bMsg) {
        if (ZYPConstant.FLG_ON_Y.equals(bMsg.xxReadOnlyFlg.getValue())) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean hasUpdateFunction() {
        List<String> functionList = getFunctionList();
        return functionList.contains(FUNCTION_ID_UPDATE);
    }

    private static void setFocus(NMAL0250BMsg bMsg) {
        bMsg.setFocusItem(bMsg.cmpsnMsgTxt_A0);
    }

    private static List<String> getFunctionList() {
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        List<String> functionList = profileService.getAuthorizedFunctionCodeListForBizAppId(BUSINESS_ID);
        return functionList;
    }
}
