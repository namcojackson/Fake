/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1370.common;

import static business.servlet.NPAL1370.constant.NPAL1370Constant.BTN_CMN_BTN_10;
import static business.servlet.NPAL1370.constant.NPAL1370Constant.BTN_CMN_BTN_8;
import static business.servlet.NPAL1370.constant.NPAL1370Constant.BTN_SET_CPY_FRM_SWH_NM;
import static business.servlet.NPAL1370.constant.NPAL1370Constant.BTN_SET_CPY_FRM_WH_NM;
import static business.servlet.NPAL1370.constant.NPAL1370Constant.BTN_SET_CPY_TO_SWH_NM;
import static business.servlet.NPAL1370.constant.NPAL1370Constant.BTN_SET_CPY_TO_WH_NM;
import static business.servlet.NPAL1370.constant.NPAL1370Constant.DISPLAY_NM_CPY_FRM_SWH;
import static business.servlet.NPAL1370.constant.NPAL1370Constant.DISPLAY_NM_CPY_FRM_WH;
import static business.servlet.NPAL1370.constant.NPAL1370Constant.DISPLAY_NM_CPY_TO_SWH;
import static business.servlet.NPAL1370.constant.NPAL1370Constant.DISPLAY_NM_CPY_TO_WH;
import static business.servlet.NPAL1370.constant.NPAL1370Constant.DISPLAY_NM_ENBL_ITM_CHKBOX;
import static business.servlet.NPAL1370.constant.NPAL1370Constant.FLAG_ON_N;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NPAL1370.NPAL1370BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;

/**
 * <pre>
 * Business ID : NPAL1370 Min Max Planning Copy Popup
 * Function Name : Common Logic
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/03/2015   CITS       Takeshi Tokutomi     Create          N/A
 *</pre>
 */
public class NPAL1370CommonLogic {

    /**
     * Set Screen Item Enable / Disable
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1370BMsg
     */
    public static void setScrnItemProtection(EZDCommonHandler handler, NPAL1370BMsg scrnMsg) {

        // column input protection
        // true : block entry
        // false : input possible
        scrnMsg.rtlWhCd_FR.setInputProtected(false);
        scrnMsg.rtlWhNm_FR.setInputProtected(true);
        scrnMsg.rtlSwhCd_FR.setInputProtected(false);
        scrnMsg.rtlSwhNm_FR.setInputProtected(true);
        scrnMsg.rtlWhCd_TO.setInputProtected(false);
        scrnMsg.rtlWhNm_TO.setInputProtected(true);
        scrnMsg.rtlSwhCd_TO.setInputProtected(false);
        scrnMsg.rtlSwhNm_TO.setInputProtected(true);
        scrnMsg.mrpEnblFlg.setInputProtected(false);

        // button activation
        handler.setButtonEnabled(BTN_SET_CPY_FRM_WH_NM, true);
        handler.setButtonEnabled(BTN_SET_CPY_FRM_SWH_NM, true);
        handler.setButtonEnabled(BTN_SET_CPY_TO_WH_NM, true);
        handler.setButtonEnabled(BTN_SET_CPY_TO_SWH_NM, true);

        // common button protection
        // 0 : inactive
        // 1 : active
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);
    }

    /**
     * Sets the name for the error message.
     * @param scrnMsg NPAL1370BMsg
     */
    public static void setNameForMessage(NPAL1370BMsg scrnMsg) {

        scrnMsg.rtlWhCd_FR.setNameForMessage(DISPLAY_NM_CPY_FRM_WH);
        scrnMsg.rtlSwhCd_FR.setNameForMessage(DISPLAY_NM_CPY_FRM_SWH);
        scrnMsg.rtlWhCd_TO.setNameForMessage(DISPLAY_NM_CPY_TO_WH);
        scrnMsg.rtlSwhCd_TO.setNameForMessage(DISPLAY_NM_CPY_TO_SWH);
        scrnMsg.mrpEnblFlg.setNameForMessage(DISPLAY_NM_ENBL_ITM_CHKBOX);
    }

    /**
     * Set Location Popup param
     * @param scrnMsg NPAL1370BMsg
     * @return LocationPopup Param (NPAL1010) Object[]
     */
    public static Object[] setLocationPopupParam(NPAL1370BMsg scrnMsg) {

        scrnMsg.P.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(3).xxPopPrm, FLAG_ON_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(4).xxPopPrm, FLAG_ON_N);

        Object[] params = new Object[10];
        params[0] = scrnMsg.P.no(0).xxPopPrm;
        params[1] = scrnMsg.P.no(1).xxPopPrm;
        params[2] = scrnMsg.P.no(2).xxPopPrm;
        params[3] = scrnMsg.P.no(3).xxPopPrm;
        params[4] = scrnMsg.P.no(4).xxPopPrm;
        params[5] = scrnMsg.P.no(5).xxPopPrm;
        params[6] = scrnMsg.P.no(6).xxPopPrm;
        params[7] = scrnMsg.P.no(7).xxPopPrm;
        params[8] = scrnMsg.P.no(8).xxPopPrm;
        params[9] = scrnMsg.P.no(9).xxPopPrm;

        return params;
    }
}
