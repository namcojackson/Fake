/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSBL0370.common;

import static business.servlet.NSBL0370.constant.NSBL0370Constant.ROW_NAME_1;
import static business.servlet.NSBL0370.constant.NSBL0370Constant.ROW_NAME_10;
import static business.servlet.NSBL0370.constant.NSBL0370Constant.ROW_NAME_11;
import static business.servlet.NSBL0370.constant.NSBL0370Constant.ROW_NAME_12;
import static business.servlet.NSBL0370.constant.NSBL0370Constant.ROW_NAME_13;
import static business.servlet.NSBL0370.constant.NSBL0370Constant.ROW_NAME_14;
import static business.servlet.NSBL0370.constant.NSBL0370Constant.ROW_NAME_15;
import static business.servlet.NSBL0370.constant.NSBL0370Constant.ROW_NAME_16;
import static business.servlet.NSBL0370.constant.NSBL0370Constant.ROW_NAME_17;
import static business.servlet.NSBL0370.constant.NSBL0370Constant.ROW_NAME_2;
import static business.servlet.NSBL0370.constant.NSBL0370Constant.ROW_NAME_3;
import static business.servlet.NSBL0370.constant.NSBL0370Constant.ROW_NAME_4;
import static business.servlet.NSBL0370.constant.NSBL0370Constant.ROW_NAME_5;
import static business.servlet.NSBL0370.constant.NSBL0370Constant.ROW_NAME_6;
import static business.servlet.NSBL0370.constant.NSBL0370Constant.ROW_NAME_7;
import static business.servlet.NSBL0370.constant.NSBL0370Constant.ROW_NAME_8;
import static business.servlet.NSBL0370.constant.NSBL0370Constant.ROW_NAME_9;
import static business.servlet.NSBL0370.constant.NSBL0370Constant.SCREEN_ID;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsgArray;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSBL0370.constant.NSBL0370Constant.BTN_LBL;
import business.servlet.NSBL0370.NSBL0370BMsg;

import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Hourly Level Report
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/24   Hitachi         T.Mizuki        Create          N/A
 *</pre>
 */
public class NSBL0370CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0370BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NSBL0370BMsg scrnMsg) {
        initCommonButton(handler, scrnMsg);
        controlScreenFields(handler, scrnMsg);
        setRowNames(scrnMsg);
    }

    /**
     * Method name: initCommonButton <dd>The method explanation: init
     * Common Button Control. <dd>Remarks:
     * @param handler EZ Common Handler
     * @param scrnMsg NSBL0370BMsg
     */
    private static final void initCommonButton(EZDCommonHandler handler, NSBL0370BMsg scrnMsg) {

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

    }

    /**
     * Control screen fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0370BMsg
     */
    private static final void controlScreenFields(EZDCommonHandler handler, NSBL0370BMsg scrnMsg) {

        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
        scrnMsg.setInputProtected(false);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).cratTaskPerTechRate_A.setAppFracDigit(2);
            scrnMsg.A.no(i).aftHourTaskPerTechRate_A.setAppFracDigit(2);
            scrnMsg.A.no(i).cloTaskPerTechRate_A.setAppFracDigit(2);
        }
        scrnMsg.B.no(0).cratTaskPerTechRate_B.setAppFracDigit(2);
        scrnMsg.B.no(0).aftHourTaskPerTechRate_B.setAppFracDigit(2);
        scrnMsg.B.no(0).cloTaskPerTechRate_B.setAppFracDigit(2);
    }

    /**
     * set Row Colors
     * @param scrnMsg NSBL0370BMsg
     */
    public static void setRowColors(NSBL0370BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        try {

            EZDBMsgArray tableLeft = (EZDBMsgArray) scrnMsg.getClass().getField("L").get(scrnMsg);
            EZDBMsgArray tableRightB = (EZDBMsgArray) scrnMsg.getClass().getField("B").get(scrnMsg);
            EZDBMsgArray tableRight = (EZDBMsgArray) scrnMsg.getClass().getField("A").get(scrnMsg);
            tblColor.setAlternateRowsBG("L", tableLeft);
            tblColor.setAlternateRowsBG("B", tableRightB);
            tblColor.setAlternateRowsBG("A", tableRight);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * setRowColors
     * @param scrnMsg NSBL0370BMsg
     */
    private static void setRowNames(NSBL0370BMsg scrnMsg) {
        scrnMsg.L.setValidCount(1);
        setValue(scrnMsg.L.no(0).xxScrItem130Txt_L1, ROW_NAME_1);
        setValue(scrnMsg.L.no(0).xxScrItem130Txt_L2, ROW_NAME_2);
        setValue(scrnMsg.L.no(0).xxScrItem130Txt_L3, ROW_NAME_3);
        setValue(scrnMsg.L.no(0).xxScrItem130Txt_L4, ROW_NAME_4);
        setValue(scrnMsg.L.no(0).xxScrItem130Txt_L5, ROW_NAME_5);
        setValue(scrnMsg.L.no(0).xxScrItem130Txt_L6, ROW_NAME_6);
        setValue(scrnMsg.L.no(0).xxScrItem130Txt_L7, ROW_NAME_7);
        setValue(scrnMsg.L.no(0).xxScrItem130Txt_L8, ROW_NAME_8);
        setValue(scrnMsg.L.no(0).xxScrItem130Txt_L9, ROW_NAME_9);
        setValue(scrnMsg.L.no(0).xxScrItem130Txt_LA, ROW_NAME_10);
        setValue(scrnMsg.L.no(0).xxScrItem130Txt_LB, ROW_NAME_11);
        setValue(scrnMsg.L.no(0).xxScrItem130Txt_LC, ROW_NAME_12);
        setValue(scrnMsg.L.no(0).xxScrItem130Txt_LD, ROW_NAME_13);
        setValue(scrnMsg.L.no(0).xxScrItem130Txt_LE, ROW_NAME_14);
        setValue(scrnMsg.L.no(0).xxScrItem130Txt_LF, ROW_NAME_15);
        setValue(scrnMsg.L.no(0).xxScrItem130Txt_LG, ROW_NAME_16);
        setValue(scrnMsg.L.no(0).xxScrItem130Txt_LH, ROW_NAME_17);
    }
}
