/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */


package business.servlet.NSBL0350.common;

import static business.servlet.NSBL0350.constant.NSBL0350Constant.SCREEN_ID;
import static business.servlet.NSBL0350.constant.NSBL0350Constant.ROW_NAME_1;
import static business.servlet.NSBL0350.constant.NSBL0350Constant.ROW_NAME_2;
import static business.servlet.NSBL0350.constant.NSBL0350Constant.ROW_NAME_3;
import static business.servlet.NSBL0350.constant.NSBL0350Constant.ROW_NAME_4;
import static business.servlet.NSBL0350.constant.NSBL0350Constant.ROW_NAME_5;
import static business.servlet.NSBL0350.constant.NSBL0350Constant.ROW_NAME_6;
import static business.servlet.NSBL0350.constant.NSBL0350Constant.ROW_NAME_7;
import static business.servlet.NSBL0350.constant.NSBL0350Constant.ROW_NAME_8;
import static business.servlet.NSBL0350.constant.NSBL0350Constant.ROW_NAME_9;
import static business.servlet.NSBL0350.constant.NSBL0350Constant.ROW_NAME_10;
import static business.servlet.NSBL0350.constant.NSBL0350Constant.ROW_NAME_11;
import static business.servlet.NSBL0350.constant.NSBL0350Constant.ROW_NAME_12;
import static business.servlet.NSBL0350.constant.NSBL0350Constant.ROW_NAME_13;
import static business.servlet.NSBL0350.constant.NSBL0350Constant.ROW_NAME_14;
import static business.servlet.NSBL0350.constant.NSBL0350Constant.ROW_NAME_15;
import static business.servlet.NSBL0350.constant.NSBL0350Constant.ROW_NAME_16;
import static business.servlet.NSBL0350.constant.NSBL0350Constant.ROW_NAME_17;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSBL0350.NSBL0350BMsg;
import business.servlet.NSBL0350.constant.NSBL0350Constant.BTN_LBL;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/** 
 *<pre>
 * Group Level Report
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/22   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public class NSBL0350CommonLogic {
    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0350BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NSBL0350BMsg scrnMsg) {
        initCommonButton(handler, scrnMsg);
        controlScreenFields(handler, scrnMsg);
        setRowNames(scrnMsg);
        setRowColors(scrnMsg);
    }

    /**
     * Method name: initCommonButton <dd>The method explanation: init
     * Common Button Control. <dd>Remarks:
     * @param handler EZ Common Handler
     * @param scrnMsg NSBL0350BMsg
     */
    private static final void initCommonButton(EZDCommonHandler handler, NSBL0350BMsg scrnMsg) {
        handler.setButtonProperties(BTN_LBL.SAVE.getOrgNm(), BTN_LBL.SAVE.getGuardNm(), BTN_LBL.SAVE.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.SUBMIT.getOrgNm(), BTN_LBL.SUBMIT.getGuardNm(), BTN_LBL.SUBMIT.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.APPLY.getOrgNm(), BTN_LBL.APPLY.getGuardNm(), BTN_LBL.APPLY.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.APPROVE.getOrgNm(), BTN_LBL.APPROVE.getGuardNm(), BTN_LBL.APPROVE.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.REJECT.getOrgNm(), BTN_LBL.REJECT.getGuardNm(), BTN_LBL.REJECT.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.DOWNLOAD.getOrgNm(), BTN_LBL.DOWNLOAD.getGuardNm(), BTN_LBL.DOWNLOAD.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.DELETE.getOrgNm(), BTN_LBL.DELETE.getGuardNm(), BTN_LBL.DELETE.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.CLEAR.getOrgNm(), BTN_LBL.CLEAR.getGuardNm(), BTN_LBL.CLEAR.getLblNm(), 1, null);
        handler.setButtonProperties(BTN_LBL.RESET.getOrgNm(), BTN_LBL.RESET.getGuardNm(), BTN_LBL.RESET.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.RETURN.getOrgNm(), BTN_LBL.RETURN.getGuardNm(), BTN_LBL.RETURN.getLblNm(), 1, null);

        handler.setButtonProperties(BTN_LBL.SEARCH_SVCGRP.getOrgNm(), BTN_LBL.SEARCH_SVCGRP.getGuardNm(), BTN_LBL.SEARCH_SVCGRP.getLblNm(), 1, null);
        handler.setButtonProperties(BTN_LBL.SEARCH.getOrgNm(), BTN_LBL.SEARCH.getGuardNm(), BTN_LBL.SEARCH.getLblNm(), 1, null);
        handler.setButtonProperties(BTN_LBL.PREV.getOrgNm(), BTN_LBL.PREV.getGuardNm(), BTN_LBL.PREV.getLblNm(), 1, null);
        handler.setButtonProperties(BTN_LBL.NEXT.getOrgNm(), BTN_LBL.NEXT.getGuardNm(), BTN_LBL.NEXT.getLblNm(), 1, null);
    }

    /**
     * Control screen fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0350BMsg
     */
    private static final void controlScreenFields(EZDCommonHandler handler, NSBL0350BMsg scrnMsg) {
        scrnMsg.orgCd_HL.setInputProtected(false);
        scrnMsg.orgCd_HT.setInputProtected(false);
        scrnMsg.orgDescTxt_H.setInputProtected(true);
        scrnMsg.xxFromDt_H.setInputProtected(false);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).orgDescTxt_AL.setInputProtected(false);
            scrnMsg.A.no(i).cratTaskPerTechRate_A.setAppFracDigit(2);
            scrnMsg.A.no(i).aftHourTaskPerTechRate_A.setAppFracDigit(2);
            scrnMsg.A.no(i).cloTaskPerTechRate_A.setAppFracDigit(2);
        }
    }

    /**
     * setRowColors
     * @param scrnMsg NSBL0350BMsg
     */
    private static void setRowColors(NSBL0350BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("L", scrnMsg.L, 1);
        tblColor.setAlternateRowsBG("A", scrnMsg.A, 1);
    }

    /**
     * setRowColors
     * @param scrnMsg NSBL0350BMsg
     */
    private static void setRowNames(NSBL0350BMsg scrnMsg) {
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

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NSBL0350BMsg
     */
    public static void initParam(NSBL0350BMsg scrnMsg) {
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
     * @param scrnMsg NSBL0350BMsg
     */
    public static void addCheckItem(NSBL0350BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.orgCd_HT);
        scrnMsg.addCheckItem(scrnMsg.xxFromDt_H);
    }
}
