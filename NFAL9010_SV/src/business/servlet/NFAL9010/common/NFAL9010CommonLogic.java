/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFAL9010.common;

import parts.common.EZDBMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import parts.servletcommon.EZDApplicationContext;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NFAL9010.NFAL9010BMsg;
import business.servlet.NFAL9010.constant.NFAL9010Constant;

import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.fujitsu.uji.http.HttpDispatchContext;

/**
 * Class name: NFAL9010CommonLogic
 * <dd>The class explanation: For screen component.
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */

/**
 * <pre>
 * COA Account Popup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/05   CSAI            K.Uramori       Update          CSA Modification
 * </pre>
 */
public class NFAL9010CommonLogic implements NFAL9010Constant {

    /**
     * Method name: setButtonProperties
     * <dd>The method explanation: Set Button Properties.
     * <dd>Remarks:
     * @param ctx Application Context
     * @param handler EZ Common Handler
     */
    public static void setButtonProperties(EZDApplicationContext ctx, EZDCommonHandler handler) {
        handler.setButtonProperties(BTN_CMN_BLANK8[0], BTN_CMN_BLANK8[1], BTN_CMN_BLANK8[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BLANK10[0], BTN_CMN_BLANK10[1], BTN_CMN_BLANK10[2], 1, null);
    }

    /**
     * Method name: setNameForMessage
     * <dd>The method explanation: Set Name For Message.
     * <dd>Remarks:
     * @param bMsg EZDBMsg
     */
    public static void setNameForMessage(EZDBMsg bMsg) {

        NFAL9010BMsg scrnMsg = (NFAL9010BMsg) bMsg;

        EZDI18NLabelConv labelConv = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

        scrnMsg.coaAcctCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Account Code"));
        scrnMsg.coaAcctNm.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Account Code Description"));
        scrnMsg.trialBalTpCd_3.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Trial Balance"));
        //scrnMsg.drCrTpCd_3.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Debit/Credit"));
        //scrnMsg.bsPlTpCd_3.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "BS/PL"));
        //scrnMsg.plCatgCd_3.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "PL Category"));
    }

    /**
     * Method name: checkItem
     * <dd>The method explanation: Add check item.
     * <dd>Remarks:
     * @param bMsg Screen Component Interface Message
     */
    public static void checkItem(NFAL9010BMsg bMsg) {

        NFAL9010BMsg scrnMsg = (NFAL9010BMsg) bMsg;

        // add check items.
        scrnMsg.addCheckItem(scrnMsg.coaAcctCd);
        scrnMsg.addCheckItem(scrnMsg.coaAcctNm);

        scrnMsg.putErrorScreen();
    }

    /**
     * Method name: setDisplayName
     * <dd>The method explanation: set Display Name.
     * <dd>Remarks:
     * @param bMsg Screen Component Interface Message
     */
    public static void setDisplayName(NFAL9010BMsg bMsg) {

        NFAL9010BMsg scrnMsg = (NFAL9010BMsg) bMsg;

        scrnMsg.coaAcctCd.setNameForMessage("Account Code");
        scrnMsg.coaAcctNm.setNameForMessage("Account Code Description");
    }

    /**
     * Method name: changeTableColorByRow
     * <dd>The method explanation: change Table Coloer by Row.
     * <dd>Remarks:
     * @param ctx Application Context
     * @param bMsg Screen Component Interface Message
     */
    public static void changeTableColorByRow(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL9010BMsg scrnMsg = (NFAL9010BMsg) bMsg;

        String screenID = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters().getSingleValue("pageID");

        S21TableColorController tblColor = new S21TableColorController(screenID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
    }

    /**
     * Method name: clearDisplayItem
     * <dd>The method explanation: clear Display Name.
     * <dd>Remarks:
     * @param bMsg Screen Component Interface Message
     */
    public static void clearDisplayItem(EZDBMsg bMsg) {

        NFAL9010BMsg scrnMsg = (NFAL9010BMsg) bMsg;

        scrnMsg.coaAcctCd.clear();
        scrnMsg.coaAcctNm.clear();
        scrnMsg.trialBalTpCd_3.clear();
        //scrnMsg.drCrTpCd_3.clear();
        //scrnMsg.bsPlTpCd_3.clear();
        //scrnMsg.plCatgCd_3.clear();
    }
}
