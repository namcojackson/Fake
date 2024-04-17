/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0270.common;

import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NLCL0270.NLCL0270BMsg;
import business.servlet.NLCL0270.constant.NLCL0270Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;

/**
 * <pre>
 * Business ID : NLCL0270 Supersession Inventory Inquiry Popup
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/20/2015   CITS            M.Ito           Create          N/A
 * 07/04/2017   CITS            R.Shimamoto     Update          QC#18187
 * 12/20/2018   CITS            T.Tokutomi      Update          QC#29214
 *</pre>
 */
public class NLCL0270CommonLogic implements NLCL0270Constant {

    /**
     * The method explanation: The display control of the screen item
     * for Initialized screen
     * @param handler EZDCommonHandler
     * @param scrnMsg NLCL0270BMsg
     */
    public static void cntrlScrnItemDispInit(EZDCommonHandler handler, NLCL0270BMsg scrnMsg) {

    	scrnMsg.clearAllGUIAttribute(SCREEN_ID);
        // column input protection
        // true : block entry
        // false : input possible
        // Header
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxDplyCtrlFlg_G1.getValue())) {
            scrnMsg.mdseCd_H2.setInputProtected(true);
            scrnMsg.mdseCd_H1.setInputProtected(true);
            scrnMsg.mdseDescShortTxt_H1.setInputProtected(true);
            scrnMsg.stkStsCd_H1.setInputProtected(true);
            scrnMsg.rtlWhNm_H2.setInputProtected(true);
            scrnMsg.rtlWhNm_H1.setInputProtected(true);
            scrnMsg.rtlSwhNm_H2.setInputProtected(true);
            scrnMsg.rtlSwhNm_H1.setInputProtected(true);
            
            // QC#29214 Add. Disable Link
            EZDGUIAttribute itemLink = new EZDGUIAttribute(SCREEN_ID, "itemLink");
            itemLink.setAttribute("style", "text-decoration: none; color:#000000;");
            scrnMsg.addGUIAttribute(itemLink);
            EZDGUIAttribute whLink = new EZDGUIAttribute(SCREEN_ID, "whLink");
            whLink.setAttribute("style", "text-decoration: none; color:#000000;");
            scrnMsg.addGUIAttribute(whLink);
            EZDGUIAttribute swhLink = new EZDGUIAttribute(SCREEN_ID, "swhLink");
            swhLink.setAttribute("style", "text-decoration: none; color:#000000;");
            scrnMsg.addGUIAttribute(swhLink);
        } else {
            scrnMsg.mdseCd_H2.setInputProtected(false);
            scrnMsg.mdseCd_H1.setInputProtected(false);
            scrnMsg.mdseDescShortTxt_H1.setInputProtected(true);
            scrnMsg.stkStsCd_H1.setInputProtected(false);
            scrnMsg.rtlWhNm_H2.setInputProtected(false);
            scrnMsg.rtlWhNm_H1.setInputProtected(false);
            scrnMsg.rtlSwhNm_H2.setInputProtected(false);
            scrnMsg.rtlSwhNm_H1.setInputProtected(false);
        }

        // Detail
        String headerMdseCd = scrnMsg.mdseCd_H1.getValue();
        if (MDSE_8_DIGIT < headerMdseCd.length()) {
        	headerMdseCd = headerMdseCd.substring(0, MDSE_8_DIGIT);
        }
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
        	String detailMdseCd = scrnMsg.A.no(i).mdseCd_A1.getValue();
            if (MDSE_8_DIGIT < detailMdseCd.length()) {
            	detailMdseCd = detailMdseCd.substring(0, MDSE_8_DIGIT);
            }
            scrnMsg.A.no(i).mdseCd_O1.setInputProtected(false);
            // 07/04/2017 R.Shimamoto QC#18187 Mod.
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxReadOnlyFlg_G1.getValue()) 
            		&& ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxOrdTakeMdseFlg_G1.getValue())) {

            	if (headerMdseCd.equals(detailMdseCd)) {
            		// Item Link Enabled.
                    scrnMsg.A.no(i).mdseCd_A1.setInputProtected(false);

            	} else {
            		// Item Link Disabled.
                    scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
                    detailSsItemDisable(scrnMsg, i);
            	}

            } else if (!ZYPCommonFunc.hasValue(scrnMsg.xxReadOnlyFlg_G1) 
                		&& ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxOrdTakeMdseFlg_G1.getValue())) {

                	if (headerMdseCd.equals(detailMdseCd)) {
                		// Item Link Enabled.
                        scrnMsg.A.no(i).mdseCd_A1.setInputProtected(false);

                	} else {
                		// Item Link Disabled.
                        scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
                        detailSsItemDisable(scrnMsg, i);
                	}

            } else if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxReadOnlyFlg_G1.getValue())) {
            	// Item Link Enabled.
                scrnMsg.A.no(i).mdseCd_A1.setInputProtected(false);

            } else {
            	// Item Link Disabled.
                scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
                detailSsItemDisable(scrnMsg, i);
            }

            scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).rtlWhNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).rtlSwhNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).stkStsCd_A1.setInputProtected(false);
            scrnMsg.A.no(i).invtyAvalQty_A1.setInputProtected(true);
            scrnMsg.A.no(i).invtyQty_A1.setInputProtected(true);
        }

        // button activation
        handler.setButtonEnabled(BTN_SEARCH, true);

        // common button protection
        // 0 : inactive
        // 1 : active
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_CLOSE[0], BTN_CMN_CLOSE[1], BTN_CMN_CLOSE[2], 1, null);
    }

    /**
     * The method explanation: The display control of the screen item
     * when the search button action is done.
     * @param handler EZDCommonHandler
     * @param scrnMsg NLCL0270BMsg
     */
    public static void cntrlScrnItemDispSearch(EZDCommonHandler handler, NLCL0270BMsg scrnMsg) {

    	scrnMsg.clearAllGUIAttribute(SCREEN_ID);
        // column input protection
        // true : block entry
        // false : input possible
        // Header
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxDplyCtrlFlg_G1.getValue())) {
            scrnMsg.mdseCd_H2.setInputProtected(true);
            scrnMsg.mdseCd_H1.setInputProtected(true);
            scrnMsg.mdseDescShortTxt_H1.setInputProtected(true);
            scrnMsg.stkStsCd_H1.setInputProtected(true);
            scrnMsg.rtlWhNm_H2.setInputProtected(true);
            scrnMsg.rtlWhNm_H1.setInputProtected(true);
            scrnMsg.rtlSwhNm_H2.setInputProtected(true);
            scrnMsg.rtlSwhNm_H1.setInputProtected(true);
            
            // QC#29214 Add. Disable Link
            EZDGUIAttribute itemLink = new EZDGUIAttribute(SCREEN_ID, "itemLink");
            itemLink.setAttribute("style", "text-decoration: none; color:#000000;");
            scrnMsg.addGUIAttribute(itemLink);
            EZDGUIAttribute whLink = new EZDGUIAttribute(SCREEN_ID, "whLink");
            whLink.setAttribute("style", "text-decoration: none; color:#000000;");
            scrnMsg.addGUIAttribute(whLink);
            EZDGUIAttribute swhLink = new EZDGUIAttribute(SCREEN_ID, "swhLink");
            swhLink.setAttribute("style", "text-decoration: none; color:#000000;");
            scrnMsg.addGUIAttribute(swhLink);
        } else {
            scrnMsg.mdseCd_H2.setInputProtected(false);
            scrnMsg.mdseCd_H1.setInputProtected(false);
            scrnMsg.mdseDescShortTxt_H1.setInputProtected(true);
            scrnMsg.stkStsCd_H1.setInputProtected(false);
            scrnMsg.rtlWhNm_H2.setInputProtected(false);
            scrnMsg.rtlWhNm_H1.setInputProtected(false);
            scrnMsg.rtlSwhNm_H2.setInputProtected(false);
            scrnMsg.rtlSwhNm_H1.setInputProtected(false);
        }

        // Detail
        String headerMdseCd = scrnMsg.mdseCd_H1.getValue();
        if (MDSE_8_DIGIT < headerMdseCd.length()) {//MDSE_8_DIGIT
        	headerMdseCd = headerMdseCd.substring(0, MDSE_8_DIGIT);
        }
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
        	String detailMdseCd = scrnMsg.A.no(i).mdseCd_A1.getValue();
            if (MDSE_8_DIGIT < detailMdseCd.length()) {//MDSE_8_DIGIT
            	detailMdseCd = detailMdseCd.substring(0, MDSE_8_DIGIT);
            }
            scrnMsg.A.no(i).mdseCd_O1.setInputProtected(false);
            // 07/04/2017 R.Shimamoto QC#18187 Mod.
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxReadOnlyFlg_G1.getValue()) 
            		&& ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxOrdTakeMdseFlg_G1.getValue())) {

            	if (headerMdseCd.equals(detailMdseCd)) {
            		// Item Link Enabled.
                    scrnMsg.A.no(i).mdseCd_A1.setInputProtected(false);

            	} else {
            		// Item Link Disabled.
                    scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
                    detailSsItemDisable(scrnMsg, i);
            	}

            } else if (!ZYPCommonFunc.hasValue(scrnMsg.xxReadOnlyFlg_G1) 
                		&& ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxOrdTakeMdseFlg_G1.getValue())) {

                	if (headerMdseCd.equals(detailMdseCd)) {
                		// Item Link Enabled.
                        scrnMsg.A.no(i).mdseCd_A1.setInputProtected(false);

                	} else {
                		// Item Link Disabled.
                        scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
                        detailSsItemDisable(scrnMsg, i);
                	}

            } else if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxReadOnlyFlg_G1.getValue())) {
            	// Item Link Enabled.
                scrnMsg.A.no(i).mdseCd_A1.setInputProtected(false);

            } else {
            	// Item Link Disabled.
                scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
                detailSsItemDisable(scrnMsg, i);
            }
            scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).rtlWhNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).rtlSwhNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).stkStsCd_A1.setInputProtected(false);
            scrnMsg.A.no(i).invtyAvalQty_A1.setInputProtected(true);
            scrnMsg.A.no(i).invtyQty_A1.setInputProtected(true);
        }

        // button activation
        handler.setButtonEnabled(BTN_SEARCH, true);

        // common button protection
        // 0 : inactive
        // 1 : active
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_CLOSE[0], BTN_CMN_CLOSE[1], BTN_CMN_CLOSE[2], 1, null);
    }

    /**
     * <pre>
     * The input scrnMsg is cleared. 
     * </pre>
     * @param scrnMsg NLCL0270BMsg
     */
    public static void clearScreenMsg(NLCL0270BMsg scrnMsg) {

    	scrnMsg.clearAllGUIAttribute(SCREEN_ID);
    	
        // Header
        scrnMsg.mdseCd_H2.clear();
        scrnMsg.mdseCd_H1.clear();
        scrnMsg.mdseDescShortTxt_H1.clear();
        scrnMsg.stkStsCd_H1.clear();
        scrnMsg.rtlWhNm_H2.clear();
        scrnMsg.rtlWhNm_H1.clear();
        scrnMsg.rtlSwhNm_H2.clear();
        scrnMsg.rtlSwhNm_H1.clear();

        // Detail
        ZYPTableUtil.clear(scrnMsg.A);
    }

    /**
     * <pre>
     * The input scrnMsg is cleared. 
     * </pre>
     * @param arg0 NLCL0270BMsg
     */
    public static void setNameForMessage(NLCL0270BMsg arg0) {

        NLCL0270BMsg scrnMsg = (NLCL0270BMsg) arg0;

        scrnMsg.mdseCd_H1.setNameForMessage(NAME_FOR_MESSAGE_MDSE_CD);
        scrnMsg.mdseDescShortTxt_H1.setNameForMessage(NAME_FOR_MESSAGE_MDSE_NM);
        scrnMsg.stkStsCd_H1.setNameForMessage(NAME_FOR_MESSAGE_STK_STS_CD);
        scrnMsg.rtlWhNm_H1.setNameForMessage(NAME_FOR_MESSAGE_RTL_WH_NM);
        scrnMsg.rtlSwhNm_H1.setNameForMessage(NAME_FOR_MESSAGE_RTL_SWH_NM);
    }

    /**
     * Get Param NMAL6800
     * @param scrnMsg NLCL0270BMsg
     * @return Param NMAL6800
     */
    public static Object[] getParamNMAL6800(NLCL0270BMsg scrnMsg) {

        scrnMsg.xxPopPrm_I0.clear();
        scrnMsg.xxPopPrm_I1.clear();
        scrnMsg.xxPopPrm_I2.clear();
        scrnMsg.xxPopPrm_I3.clear();
        scrnMsg.xxPopPrm_I4.clear();
        scrnMsg.xxPopPrm_I5.clear();
        scrnMsg.xxPopPrm_I6.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I0, scrnMsg.mdseCd_H1);

        Object[] params = new Object[7];
        params[0] = scrnMsg.xxPopPrm_I0;
        params[1] = scrnMsg.xxPopPrm_I1;
        params[2] = scrnMsg.xxPopPrm_I2;
        params[3] = scrnMsg.xxPopPrm_I3;
        params[4] = scrnMsg.xxPopPrm_I4;
        params[5] = scrnMsg.xxPopPrm_I5;
        params[6] = scrnMsg.xxPopPrm_I6;

        return params;
    }

    /**
     * @param scrnMsg NLCL0270BMsg
     * @return Object[]
     */
    public static Object[] getParamNPAL1010(NLCL0270BMsg scrnMsg) {

        scrnMsg.xxPopPrm_P0.clear();
        scrnMsg.xxPopPrm_P1.clear();
        scrnMsg.xxPopPrm_P2.clear();
        scrnMsg.xxPopPrm_P3.clear();
        scrnMsg.xxPopPrm_P4.clear();
        scrnMsg.xxPopPrm_P5.clear();
        scrnMsg.xxPopPrm_P6.clear();
        scrnMsg.xxPopPrm_P7.clear();
        scrnMsg.xxPopPrm_P8.clear();
        scrnMsg.xxPopPrm_P9.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P3, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, ZYPConstant.FLG_OFF_N);
        if (EVENT_NM_OPENWIN_LOC_INFO_FOR_WH.equals(scrnMsg.xxPopPrm_EV.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P7, scrnMsg.rtlWhNm_H1);
        } else if (EVENT_NM_OPENWIN_LOC_INFO_FOR_SWH.equals(scrnMsg.xxPopPrm_EV.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P9, scrnMsg.rtlSwhNm_H1);
        }

        Object[] params = new Object[11];
        params[0] = scrnMsg.xxPopPrm_P0;
        params[1] = scrnMsg.xxPopPrm_P1;
        params[2] = scrnMsg.xxPopPrm_P2;
        params[3] = scrnMsg.xxPopPrm_P3;
        params[4] = scrnMsg.xxPopPrm_P4;
        params[5] = scrnMsg.xxPopPrm_P5;
        params[6] = scrnMsg.xxPopPrm_P6;
        params[7] = scrnMsg.xxPopPrm_P7;
        params[8] = scrnMsg.xxPopPrm_P8;
        params[9] = scrnMsg.xxPopPrm_P9;
        params[10] = scrnMsg.xxPopPrm_PA;

       return params;
    }

    /**QC#18187 Add.
     * detailSsItemDisable
     * @param scrnMsg NLCL0270BMsg
     */
    private static void detailSsItemDisable(NLCL0270BMsg scrnMsg, int idx) {

    	EZDGUIAttribute guAttrb = new EZDGUIAttribute(SCREEN_ID, SUPERSESSION_ITEM_ID + idx);
    	guAttrb.setStyleAttribute("text-decoration", "none");
    	guAttrb.setStyleAttribute("color", "black");
    	scrnMsg.addGUIAttribute(guAttrb);

    }
}
