/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0390.common;

import java.util.List;

import static business.servlet.NSBL0390.constant.NSBL0390Constant.*;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSBL0390.NSBL0390BMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Meter Reading Counter for Interface Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/13   Hitachi         O.Okuma         Create          N/A
 *</pre>
 */
public class NSBL0390CommonLogic {

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
        handler.setButtonEnabled(BTN_CREATE, false);
        handler.setButtonEnabled(BTN_MDF_DTL, false);

        if (functionList == null || functionList.isEmpty()) {
            handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        } else if (functionList.contains(FUNC_ID_EDT)) {
            handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 1, null);
            handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
            handler.setButtonEnabled(BTN_CREATE, true);
        } else if (functionList.contains(FUNC_ID_INQ)) {
            handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 1, null);
            handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        }
    }

    /**
     * activateScreenItems
     * @param scrnMsg NSBL0390BMsg
     */
    public static void activateScreenItems(NSBL0390BMsg scrnMsg) {

        scrnMsg.svcModYr.setInputProtected(false);
        scrnMsg.svcModMth.setInputProtected(false);
        scrnMsg.svcModDay.setInputProtected(false);
        scrnMsg.svcMnfCd.setInputProtected(false);
        scrnMsg.svcModSqNum.setInputProtected(true);
        scrnMsg.svcModPlnId.setInputProtected(true);
    }

    /**
     * initControlCommonButton
     * @param scrnAppli EZDCommonHandler
     */
    public static void initControlButton(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonEnabled(BTN_CREATE, true);
        scrnAppli.setButtonEnabled(BTN_MDF_DTL, false);
    }

    /**
     * activeControlCreateButton
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NSBL0390BMsg
     */
    public static void activeControlCreateButton(EZDCommonHandler scrnAppli, NSBL0390BMsg scrnMsg) {

        scrnAppli.setButtonEnabled(BTN_CMN_SUBMIT[0], true);
        scrnAppli.setButtonEnabled(BTN_CREATE, false);
        scrnMsg.svcModYr.setInputProtected(true);
        scrnMsg.svcModMth.setInputProtected(true);
        scrnMsg.svcModDay.setInputProtected(true);
        scrnMsg.svcMnfCd.setInputProtected(true);
    }

    /**
     * activeControlSubmitButton
     * @param scrnAppli EZDCommonHandler
     */
   public static void activeControlSubmitButton(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonEnabled(BTN_MDF_DTL, true);
    }

   /**
    * addCheckItem
    * @param scrnMsg NSBL0390BMsg
    */
   public static void addCheckItem(NSBL0390BMsg scrnMsg) {
       scrnMsg.addCheckItem(scrnMsg.svcModYr);
       scrnMsg.addCheckItem(scrnMsg.svcModMth);
       scrnMsg.addCheckItem(scrnMsg.svcModDay);
       scrnMsg.addCheckItem(scrnMsg.svcMnfCd);
   }

   /**
    * checkDate
    * @param scrnMsg NSBL0390BMsg
    */
   public static void checkDate(NSBL0390BMsg scrnMsg) {

       String date = scrnMsg.svcModYr.getValue() + scrnMsg.svcModMth.getValue() + scrnMsg.svcModDay.getValue();

       if (!ZYPDateUtil.checkDate(date)) {
           scrnMsg.svcModYr.setErrorInfo(1, NSBM0023E);
           scrnMsg.svcModMth.setErrorInfo(1, NSBM0023E);
           scrnMsg.svcModDay.setErrorInfo(1, NSBM0023E);
       }
   }

   /**
    * isError
    * @param scrnMsg NSBL0390BMsg
    * @return boolean
    */
   public static boolean isError(NSBL0390BMsg scrnMsg) {

       if (scrnMsg.svcModYr.isError() || scrnMsg.svcModMth.isError() || scrnMsg.svcModDay.isError()) {
           return false;
       }

       return true;
   }
}

