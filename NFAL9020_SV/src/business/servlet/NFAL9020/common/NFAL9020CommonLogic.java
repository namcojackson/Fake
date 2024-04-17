/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFAL9020.common;

import parts.common.EZDBMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import parts.servletcommon.EZDApplicationContext;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NFAL9020.NFAL9020BMsg;
import business.servlet.NFAL9020.constant.NFAL9020Constant;

import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.fujitsu.uji.http.HttpDispatchContext;

/**
 * Class name: NFAL9020CommonLogic
 * <dd>The class explanation: For screen component.
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL9020CommonLogic implements NFAL9020Constant {

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

        NFAL9020BMsg scrnMsg = (NFAL9020BMsg) bMsg;

        EZDI18NLabelConv labelConv = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

        scrnMsg.ajeId.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "AJE ID"));
        scrnMsg.sysSrcCd_3.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "System Source Code"));
        scrnMsg.trxCd_3.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Transaction Code"));
        scrnMsg.trxRsnCd_3.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Transaction Reason Code"));
    }

    /**
     * Method name: codeSelectBoxAllClear
     * <dd>The method explanation:
     * <dd>Remarks:
     * @param scrnMsg NFAL9020BMsg
     * @return boolean
     */
    public boolean codeSelectBoxAllClear(NFAL9020BMsg scrnMsg) {
        if (!scrnMsg.sysSrcCd_3.isClear() || !scrnMsg.trxCd_3.isClear() || !scrnMsg.trxRsnCd_3.isClear()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Method name: changeTableColorByRow
     * <dd>The method explanation: change Table Coloer by Row.
     * <dd>Remarks:
     * @param ctx Application Context
     * @param bMsg Screen Component Interface Message
     */
    public static void changeTableColorByRow_BK(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL9020BMsg scrnMsg = (NFAL9020BMsg) bMsg;

        String screenID = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters().getSingleValue("pageID");

        S21TableColorController tblColor = new S21TableColorController(screenID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
    }

    /**
     * Method name: changeTableColorByRow02
     * <dd>The method explanation: change Table Coloer by Row.
     * <dd>Remarks:
     * @param scrnMsg NFAL9020BMsg
     */
    public static void changeTableColorByRow(NFAL9020BMsg scrnMsg) {
        // set alternate rows back-ground color
        int tempCnt = scrnMsg.A.getValidCount();
        scrnMsg.A.setValidCount(tempCnt + 1);

        S21TableColorController tblColor = new S21TableColorController("NFAL9020Scrn00", scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        scrnMsg.A.setValidCount(tempCnt);
    }
}
