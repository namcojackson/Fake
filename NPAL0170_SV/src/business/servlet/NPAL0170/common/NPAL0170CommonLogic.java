/**
 * <Pre>Copyright (c) 2011 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NPAL0170.common;

import java.util.List;

import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NPAL0170.NPAL0170BMsg;
import business.servlet.NPAL0170.NPAL0170_RBMsgArray;
import business.servlet.NPAL0170.constant.NPAL0170Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * PO Text Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/24/2012   SRA             N.Otsuji        Create          N/A
 * 02/05/2013   CSAI            K.Lee           Update          WDS#69
 * 03/26/2013   Hitachi         T.Kawazu        Update          QC934
 * 01/21/2016   CITS            K.Ogino         Update          CSA
 * 01/17/2017   CITS            T.Kikuhara      Update          QC#16256
 * </pre>
 */
public class NPAL0170CommonLogic implements NPAL0170Constant {

    /**
     * Control items on INIT event
     * @param handler EZDCommonHandler
     * @param bMsg NPAL0170BMsg
     */
    public static void controlItemsOnInit(EZDCommonHandler handler, NPAL0170BMsg bMsg) {
        controlItems(handler, bMsg);
        controlButtons(handler, bMsg);
        setFocus(bMsg);
    }

    /**
     * Control items on Clear event
     * @param handler EZDCommonHandler
     * @param bMsg NPAL0170BMsg
     */
    public static void controlItemsOnClear(EZDCommonHandler handler, NPAL0170BMsg bMsg) {
        bMsg.poMsgTxt_A0.clear();
        bMsg.poMsgTxt_A1.clear();
        bMsg.poMsgTxt_A2.clear();
        bMsg.poMsgTxt_A3.clear();
        bMsg.poMsgTxt_A4.clear();
        bMsg.poMsgTxt_A5.clear();
        bMsg.poMsgTxt_A6.clear();
        bMsg.poMsgTxt_A7.clear();
        bMsg.poMsgTxt_A8.clear();
        bMsg.poMsgTxt_A9.clear();
        controlItemsOnInit(handler, bMsg);
    }

    /**
     * set output parameter
     * @param arg Object[]
     * @param resultArray NPAL0170_RBMsgArray
     */
    public static void setOutputParam(Object[] arg, NPAL0170_RBMsgArray resultArray) {

        if (!(arg instanceof Object[])) {
            return;
        }

        if (arg.length < 7) {
            return;
        }

        if (arg[6] instanceof EZDMsgArray) {
            EZDMsg.copy(resultArray, null, (EZDMsgArray) arg[6], null);
        }
    }

    /**
     * Control items on Return event
     * @param scrnMsg NPAL0170BMsg
     * @param resultArray NPAL0170_RBMsgArray
     */
    public static void setResult(NPAL0170BMsg scrnMsg, NPAL0170_RBMsgArray resultArray) {
        for (int i = 0; i < resultArray.length(); i++) {
            switch (i) {
                case 0:
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).poMsgPk, scrnMsg.poMsgPk_A0);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).poMsgSegId, scrnMsg.poMsgSegId_A0);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).poMsgSubmtPsnCd, scrnMsg.poMsgSubmtPsnCd_A0);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).poMsgTxt, scrnMsg.poMsgTxt_A0);
                    break;
                case 1:
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).poMsgPk, scrnMsg.poMsgPk_A1);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).poMsgSegId, scrnMsg.poMsgSegId_A1);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).poMsgSubmtPsnCd, scrnMsg.poMsgSubmtPsnCd_A1);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).poMsgTxt, scrnMsg.poMsgTxt_A1);
                    break;
                case 2:
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).poMsgPk, scrnMsg.poMsgPk_A2);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).poMsgSegId, scrnMsg.poMsgSegId_A2);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).poMsgSubmtPsnCd, scrnMsg.poMsgSubmtPsnCd_A2);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).poMsgTxt, scrnMsg.poMsgTxt_A2);
                    break;
                case 3:
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).poMsgPk, scrnMsg.poMsgPk_A3);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).poMsgSegId, scrnMsg.poMsgSegId_A3);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).poMsgSubmtPsnCd, scrnMsg.poMsgSubmtPsnCd_A3);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).poMsgTxt, scrnMsg.poMsgTxt_A3);
                    break;
                case 4:
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).poMsgPk, scrnMsg.poMsgPk_A4);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).poMsgSegId, scrnMsg.poMsgSegId_A4);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).poMsgSubmtPsnCd, scrnMsg.poMsgSubmtPsnCd_A4);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).poMsgTxt, scrnMsg.poMsgTxt_A4);
                    break;
                case 5:
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).poMsgPk, scrnMsg.poMsgPk_A5);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).poMsgSegId, scrnMsg.poMsgSegId_A5);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).poMsgSubmtPsnCd, scrnMsg.poMsgSubmtPsnCd_A5);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).poMsgTxt, scrnMsg.poMsgTxt_A5);
                    break;
                case 6:
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).poMsgPk, scrnMsg.poMsgPk_A6);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).poMsgSegId, scrnMsg.poMsgSegId_A6);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).poMsgSubmtPsnCd, scrnMsg.poMsgSubmtPsnCd_A6);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).poMsgTxt, scrnMsg.poMsgTxt_A6);
                    break;
                case 7:
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).poMsgPk, scrnMsg.poMsgPk_A7);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).poMsgSegId, scrnMsg.poMsgSegId_A7);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).poMsgSubmtPsnCd, scrnMsg.poMsgSubmtPsnCd_A7);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).poMsgTxt, scrnMsg.poMsgTxt_A7);
                    break;
                case 8:
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).poMsgPk, scrnMsg.poMsgPk_A8);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).poMsgSegId, scrnMsg.poMsgSegId_A8);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).poMsgSubmtPsnCd, scrnMsg.poMsgSubmtPsnCd_A8);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).poMsgTxt, scrnMsg.poMsgTxt_A8);
                    break;
                case 9:
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).poMsgPk, scrnMsg.poMsgPk_A9);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).poMsgSegId, scrnMsg.poMsgSegId_A9);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).poMsgSubmtPsnCd, scrnMsg.poMsgSubmtPsnCd_A9);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).poMsgTxt, scrnMsg.poMsgTxt_A9);
                    break;
                default:
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).poMsgPk, scrnMsg.poMsgPk_A0);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).poMsgSegId, scrnMsg.poMsgSegId_A0);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).poMsgSubmtPsnCd, scrnMsg.poMsgSubmtPsnCd_A0);
                    ZYPEZDItemValueSetter.setValue(resultArray.no(i).poMsgTxt, scrnMsg.poMsgTxt_A0);
                    break;
            }
        }
    }

    /**
     * setInitMsg
     * @param scrnMsg NPAL0170BMsg
     * @param resultArray NPAL0170_RBMsgArray
     */
    public static void setInitMsg(NPAL0170BMsg scrnMsg, EZDMsgArray initArray) {
        
        EZDMsg.copy(initArray, null, scrnMsg.R, null);
        
        for (int i = 0; i < scrnMsg.R.length(); i++) {
            switch (i) {
                case 0:
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poMsgPk_A0, scrnMsg.R.no(i).poMsgPk);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poMsgSegId_A0, scrnMsg.R.no(i).poMsgSegId);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poMsgSubmtPsnCd_A0, scrnMsg.R.no(i).poMsgSubmtPsnCd);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poMsgTxt_A0, scrnMsg.R.no(i).poMsgTxt);
                    break;
                case 1:
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poMsgPk_A1, scrnMsg.R.no(i).poMsgPk);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poMsgSegId_A1, scrnMsg.R.no(i).poMsgSegId);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poMsgSubmtPsnCd_A1, scrnMsg.R.no(i).poMsgSubmtPsnCd);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poMsgTxt_A1, scrnMsg.R.no(i).poMsgTxt);
                    break;
                case 2:
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poMsgPk_A2, scrnMsg.R.no(i).poMsgPk);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poMsgSegId_A2, scrnMsg.R.no(i).poMsgSegId);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poMsgSubmtPsnCd_A2, scrnMsg.R.no(i).poMsgSubmtPsnCd);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poMsgTxt_A2, scrnMsg.R.no(i).poMsgTxt);
                    break;
                case 3:
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poMsgPk_A3, scrnMsg.R.no(i).poMsgPk);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poMsgSegId_A3, scrnMsg.R.no(i).poMsgSegId);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poMsgSubmtPsnCd_A3, scrnMsg.R.no(i).poMsgSubmtPsnCd);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poMsgTxt_A3, scrnMsg.R.no(i).poMsgTxt);
                    break;
                case 4:
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poMsgPk_A4, scrnMsg.R.no(i).poMsgPk);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poMsgSegId_A4, scrnMsg.R.no(i).poMsgSegId);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poMsgSubmtPsnCd_A4, scrnMsg.R.no(i).poMsgSubmtPsnCd);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poMsgTxt_A4, scrnMsg.R.no(i).poMsgTxt);
                    break;
                case 5:
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poMsgPk_A5, scrnMsg.R.no(i).poMsgPk);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poMsgSegId_A5, scrnMsg.R.no(i).poMsgSegId);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poMsgSubmtPsnCd_A5, scrnMsg.R.no(i).poMsgSubmtPsnCd);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poMsgTxt_A5, scrnMsg.R.no(i).poMsgTxt);
                    break;
                case 6:
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poMsgPk_A6, scrnMsg.R.no(i).poMsgPk);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poMsgSegId_A6, scrnMsg.R.no(i).poMsgSegId);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poMsgSubmtPsnCd_A6, scrnMsg.R.no(i).poMsgSubmtPsnCd);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poMsgTxt_A6, scrnMsg.R.no(i).poMsgTxt);
                    break;
                case 7:
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poMsgPk_A7, scrnMsg.R.no(i).poMsgPk);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poMsgSegId_A7, scrnMsg.R.no(i).poMsgSegId);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poMsgSubmtPsnCd_A7, scrnMsg.R.no(i).poMsgSubmtPsnCd);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poMsgTxt_A7, scrnMsg.R.no(i).poMsgTxt);
                    break;
                case 8:
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poMsgPk_A8, scrnMsg.R.no(i).poMsgPk);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poMsgSegId_A8, scrnMsg.R.no(i).poMsgSegId);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poMsgSubmtPsnCd_A8, scrnMsg.R.no(i).poMsgSubmtPsnCd);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poMsgTxt_A8, scrnMsg.R.no(i).poMsgTxt);
                    break;
                case 9:
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poMsgPk_A9, scrnMsg.R.no(i).poMsgPk);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poMsgSegId_A9, scrnMsg.R.no(i).poMsgSegId);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poMsgSubmtPsnCd_A9, scrnMsg.R.no(i).poMsgSubmtPsnCd);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poMsgTxt_A9, scrnMsg.R.no(i).poMsgTxt);
                    break;
                default:
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poMsgPk_A0, scrnMsg.R.no(i).poMsgPk);
                ZYPEZDItemValueSetter.setValue(scrnMsg.poMsgSegId_A0, scrnMsg.R.no(i).poMsgSegId);
                ZYPEZDItemValueSetter.setValue(scrnMsg.poMsgSubmtPsnCd_A0, scrnMsg.R.no(i).poMsgSubmtPsnCd);
                ZYPEZDItemValueSetter.setValue(scrnMsg.poMsgTxt_A0, scrnMsg.R.no(i).poMsgTxt);
                    break;
            }
        }
    }
    
    /**
     * hasExistMsgList
     * @param scrnMsg
     * @return true:exist / false: not exist
     */
    public static boolean hasExistMsgList(NPAL0170BMsg scrnMsg) {

        boolean msgExitsFlg = false;
        for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.R.no(i).poMsgTxt)) {
                msgExitsFlg = true;
                break;
            }
        }

        return msgExitsFlg;
    }
    
    
    
    private static void controlItems(EZDCommonHandler handler, NPAL0170BMsg bMsg) {

        bMsg.poOrdNum.setInputProtected(true);
        bMsg.xxLineNum.setInputProtected(true);
        bMsg.prchReqNum.setInputProtected(true);
        bMsg.poOrdDtlLineNum.setInputProtected(true);
        bMsg.poMsgTpDescTxt.setInputProtected(true);
        if (isPoMsgUpdatable(bMsg)) {
            controlButtonsForUpdate(handler);
        } else {
            bMsg.poMsgTxt_A0.setInputProtected(false);
            bMsg.poMsgTxt_A1.setInputProtected(false);
            bMsg.poMsgTxt_A2.setInputProtected(false);
            bMsg.poMsgTxt_A3.setInputProtected(false);
            bMsg.poMsgTxt_A4.setInputProtected(false);
            bMsg.poMsgTxt_A5.setInputProtected(false);
            bMsg.poMsgTxt_A6.setInputProtected(false);
            bMsg.poMsgTxt_A7.setInputProtected(false);
            bMsg.poMsgTxt_A8.setInputProtected(false);
            bMsg.poMsgTxt_A9.setInputProtected(false);
        }
    }

    private static void controlButtons(EZDCommonHandler handler, NPAL0170BMsg bMsg) {
        if (isPoMsgUpdatable(bMsg)) {
            controlButtonsForUpdate(handler);
        } else {
            controlButtonsForReference(handler);
        }
    }

    private static void controlButtonsForUpdate(EZDCommonHandler handler) {

        // QC#16256 add start
        if (isUpdatable()) {
            handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
            handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
            handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        }
        // QC#16256 add end
    }

    private static void controlButtonsForReference(EZDCommonHandler handler) {

        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
    }

    private static boolean isPoMsgUpdatable(NPAL0170BMsg bMsg) {

        return hasUpdateFunction();
    }

    private static boolean hasUpdateFunction() {
        List<String> functionList = getFunctionList();
        return functionList.contains(FUNCTION_ID_UPDATE);
    }

    private static void setFocus(NPAL0170BMsg bMsg) {
        bMsg.setFocusItem(bMsg.poMsgTxt_A0);
    }

    private static List<String> getFunctionList() {
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        List<String> functionList = profileService.getAuthorizedFunctionCodeListForBizAppId(BUSINESS_ID);
        return functionList;
    }

    // QC#16256 add start
    /**
     * Function check
     * @return true:edit false:reference
     */
    private static boolean isUpdatable() {
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        List<String> functionList = profileService.getAuthorizedFunctionCodeListForBizAppId(BUSINESS_ID);
        return functionList.contains(FUNCTION_UPDATE);
    }
    // QC#16256 add end

}
