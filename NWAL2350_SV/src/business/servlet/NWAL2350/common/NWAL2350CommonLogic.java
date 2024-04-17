/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2350.common;

import static business.servlet.NWAL2350.constant.NWAL2350Constant.BTN_CMN_BTN_10;
import static business.servlet.NWAL2350.constant.NWAL2350Constant.BTN_CMN_BTN_8;
import static business.servlet.NWAL2350.constant.NWAL2350Constant.DISPLAY_NM_DS_IMPT_ORD_PK;
import static business.servlet.NWAL2350.constant.NWAL2350Constant.SCRN_ID;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NWAL2350.NWAL2350BMsg;
import business.servlet.NWAL2350.constant.NWAL2350Constant;

/**
 *<pre>
 * SOM Profitability Common Logic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/29   CITS            S.Tanikawa      Create          N/A
 * 2017/02/06   Fujitsu         H.Nagashima     Update          QC#17120
 *</pre>
 */

public class NWAL2350CommonLogic {

    /**
     * cntrlScrnItemDispInit
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL2350BMsg
     */
    public static void cntrlScrnItemDispInit(EZDCommonHandler handler, NWAL2350BMsg scrnMsg) {

        // clear html attribute
        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        scrnMsg.setInputProtected(true);

        setAppFracDigit(scrnMsg);

        // common button protection
        // 0 : inactive
        // 1 : active
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);

    }

    /**
     * setNameForMessage
     * @param arg0 NWAL2350BMsg
     */
    public static void setNameForMessage(NWAL2350BMsg arg0) {

        NWAL2350BMsg scrnMsg = (NWAL2350BMsg) arg0;

        scrnMsg.dsImptOrdPk.setNameForMessage(DISPLAY_NM_DS_IMPT_ORD_PK);
    }

    /**
     * setAppFracDigit
     * @param scrnMsg NWAL2350BMsg
     */
    public static void setAppFracDigit(NWAL2350BMsg scrnMsg) {

        scrnMsg.negoDealAmt.setAppFracDigit(NWAL2350Constant.APP_FRAC_DIGIT);
        scrnMsg.negoDealAmt.setAppFracDigit(NWAL2350Constant.APP_FRAC_DIGIT);
        scrnMsg.totRepRevAmt.setAppFracDigit(NWAL2350Constant.APP_FRAC_DIGIT);
        scrnMsg.somFinalFlAmt.setAppFracDigit(NWAL2350Constant.APP_FRAC_DIGIT);
        scrnMsg.somMkupAmt.setAppFracDigit(NWAL2350Constant.APP_FRAC_DIGIT);
        //QC#17120 mod Start
//        scrnMsg.gpWotCostTrnsfAmt.setAppFracDigit(NWAL2350Constant.APP_FRAC_DIGIT);
//        scrnMsg.somPctMkupRate.setAppFracDigit(NWAL2350Constant.APP_FRAC_DIGIT);
        scrnMsg.gpWotCostTrnsfRate.setAppFracDigit(NWAL2350Constant.APP_FRAC_DIGIT);
        scrnMsg.gpCostPctTrnsfRate.setAppFracDigit(NWAL2350Constant.APP_FRAC_DIGIT);
        //QC#17120 mod End
        scrnMsg.leasePmtAmt.setAppFracDigit(NWAL2350Constant.APP_FRAC_DIGIT);
        scrnMsg.leaseTermMthAot.setAppFracDigit(NWAL2350Constant.APP_FRAC_DIGIT);
        scrnMsg.somTotFinAmt.setAppFracDigit(NWAL2350Constant.APP_FRAC_DIGIT);
        scrnMsg.msrpListPrcAmt.setAppFracDigit(NWAL2350Constant.APP_FRAC_DIGIT);
        scrnMsg.extFlPrcAmt.setAppFracDigit(NWAL2350Constant.APP_FRAC_DIGIT);
        scrnMsg.flAdjAmt.setAppFracDigit(NWAL2350Constant.APP_FRAC_DIGIT);
        scrnMsg.csmpCrAmt.setAppFracDigit(NWAL2350Constant.APP_FRAC_DIGIT);
        scrnMsg.somByotFinAmt.setAppFracDigit(NWAL2350Constant.APP_FRAC_DIGIT);
        scrnMsg.somSvcRevTrnsfAmt.setAppFracDigit(NWAL2350Constant.APP_FRAC_DIGIT);
        scrnMsg.svcCostTrnsfAmt.setAppFracDigit(NWAL2350Constant.APP_FRAC_DIGIT);
        scrnMsg.sbscrSvcAmt.setAppFracDigit(NWAL2350Constant.APP_FRAC_DIGIT);
        scrnMsg.somSvcFinAmt.setAppFracDigit(NWAL2350Constant.APP_FRAC_DIGIT);
        scrnMsg.somSplyFinAmt.setAppFracDigit(NWAL2350Constant.APP_FRAC_DIGIT);
        scrnMsg.somPrmoAmt.setAppFracDigit(NWAL2350Constant.APP_FRAC_DIGIT);
        scrnMsg.somTotCostTrnsfAmt.setAppFracDigit(NWAL2350Constant.APP_FRAC_DIGIT);
        scrnMsg.proSvcAmt.setAppFracDigit(NWAL2350Constant.APP_FRAC_DIGIT);
        scrnMsg.somCbsInvAmt.setAppFracDigit(NWAL2350Constant.APP_FRAC_DIGIT);
        scrnMsg.somTaxAbleAmt.setAppFracDigit(NWAL2350Constant.APP_FRAC_DIGIT);
        scrnMsg.somTaxAmt.setAppFracDigit(NWAL2350Constant.APP_FRAC_DIGIT);
        scrnMsg.nonTaxAbleAmt.setAppFracDigit(NWAL2350Constant.APP_FRAC_DIGIT);
        scrnMsg.gpEquipRevAmt.setAppFracDigit(NWAL2350Constant.APP_FRAC_DIGIT);
        scrnMsg.interRgRevAmt.setAppFracDigit(NWAL2350Constant.APP_FRAC_DIGIT);
        scrnMsg.spiffRevAmt.setAppFracDigit(NWAL2350Constant.APP_FRAC_DIGIT);
        scrnMsg.interTrtyRevAmt.setAppFracDigit(NWAL2350Constant.APP_FRAC_DIGIT);
        scrnMsg.unitSldRevAmt.setAppFracDigit(NWAL2350Constant.APP_FRAC_DIGIT);
        scrnMsg.gpEquipEarnAmt.setAppFracDigit(NWAL2350Constant.APP_FRAC_DIGIT);
        scrnMsg.interRgEarnAmt.setAppFracDigit(NWAL2350Constant.APP_FRAC_DIGIT);
        scrnMsg.spiffEarnAmt.setAppFracDigit(NWAL2350Constant.APP_FRAC_DIGIT);
        scrnMsg.interTrtyEarnAmt.setAppFracDigit(NWAL2350Constant.APP_FRAC_DIGIT);
        scrnMsg.unitSldEarnAmt.setAppFracDigit(NWAL2350Constant.APP_FRAC_DIGIT);
        scrnMsg.somTotEarnAmt.setAppFracDigit(NWAL2350Constant.APP_FRAC_DIGIT);
    }

}
