/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3100.common;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NLBL3100.NLBL3100BMsg;
import business.servlet.NLBL3100.constant.NLBL3100Constant;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;

/**
 * <pre>
 * Business ID : NLBL3100 Call Coordinator Search Popup
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/13/2015   CITS            M.Ito           Create          N/A
 *</pre>
 */
public class NLBL3100CommonLogic implements NLBL3100Constant {

    /**
     * The method explanation: The display control of the screen item
     * for Initialized screen
     * @param handler EZDCommonHandler
     * @param scrnMsg NLBL3100BMsg
     */
    public static void cntrlScrnItemDispInit(EZDCommonHandler handler, NLBL3100BMsg scrnMsg) {

        // column input protection
        // true : block entry
        // false : input possible
        // Header
        scrnMsg.rtlWhCd_H1.setInputProtected(false);
        scrnMsg.rtlWhNm_H1.setInputProtected(false);
        scrnMsg.schdCoordPsnCd_H1.setInputProtected(false);
        scrnMsg.xxPsnNm_H1.setInputProtected(false);
        scrnMsg.mgrPsnCd_H1.setInputProtected(false);
        scrnMsg.xxPsnNm_H2.setInputProtected(false);
        scrnMsg.supvPsnCd_H1.setInputProtected(false);
        scrnMsg.xxPsnNm_H3.setInputProtected(false);
        scrnMsg.carrCd_H1.setInputProtected(false);
        scrnMsg.xxPsnNm_H4.setInputProtected(false);
        scrnMsg.stCd_H1.setInputProtected(false);
        scrnMsg.effFromDt_H1.setInputProtected(false);
        scrnMsg.effThruDt_H1.setInputProtected(false);

        // Detail
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).rtlWhNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxPsnNm_A2.setInputProtected(true);
            scrnMsg.A.no(i).xxPsnNm_A3.setInputProtected(true);
            scrnMsg.A.no(i).xxPsnNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).locNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).carrCtacEmlAddr_A1.setInputProtected(true);
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
     * @param scrnMsg NLBL3100BMsg
     */
    public static void cntrlScrnItemDispSearch(EZDCommonHandler handler, NLBL3100BMsg scrnMsg) {

        // column input protection
        // true : block entry
        // false : input possible
        // Header
        scrnMsg.rtlWhCd_H1.setInputProtected(false);
        scrnMsg.rtlWhNm_H1.setInputProtected(false);
        scrnMsg.schdCoordPsnCd_H1.setInputProtected(false);
        scrnMsg.xxPsnNm_H1.setInputProtected(false);
        scrnMsg.mgrPsnCd_H1.setInputProtected(false);
        scrnMsg.xxPsnNm_H2.setInputProtected(false);
        scrnMsg.supvPsnCd_H1.setInputProtected(false);
        scrnMsg.xxPsnNm_H3.setInputProtected(false);
        scrnMsg.carrCd_H1.setInputProtected(false);
        scrnMsg.xxPsnNm_H4.setInputProtected(false);
        scrnMsg.stCd_H1.setInputProtected(false);
        scrnMsg.effFromDt_H1.setInputProtected(false);
        scrnMsg.effThruDt_H1.setInputProtected(false);

        // Detail
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).rtlWhNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxPsnNm_A2.setInputProtected(true);
            scrnMsg.A.no(i).xxPsnNm_A3.setInputProtected(true);
            scrnMsg.A.no(i).xxPsnNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).locNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).carrCtacEmlAddr_A1.setInputProtected(true);
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
     * @param scrnMsg NLBL3100BMsg
     */
    public static void clearScreenMsg(NLBL3100BMsg scrnMsg) {

        // Header
        scrnMsg.rtlWhCd_H1.clear();
        scrnMsg.rtlWhNm_H1.clear();
        scrnMsg.schdCoordPsnCd_H1.clear();
        scrnMsg.psnFirstNm_H1.clear();
        scrnMsg.psnLastNm_H1.clear();
        scrnMsg.xxPsnNm_H1.clear();
        scrnMsg.mgrPsnCd_H1.clear();
        scrnMsg.psnFirstNm_H2.clear();
        scrnMsg.psnLastNm_H2.clear();
        scrnMsg.xxPsnNm_H2.clear();
        scrnMsg.supvPsnCd_H1.clear();
        scrnMsg.psnFirstNm_H3.clear();
        scrnMsg.psnLastNm_H3.clear();
        scrnMsg.xxPsnNm_H3.clear();
        scrnMsg.carrCd_H1.clear();
        scrnMsg.psnFirstNm_H4.clear();
        scrnMsg.psnLastNm_H4.clear();
        scrnMsg.xxPsnNm_H4.clear();
        scrnMsg.stCd_H1.clear();
        scrnMsg.effFromDt_H1.clear();
        scrnMsg.effThruDt_H1.clear();

        // Detail
        ZYPTableUtil.clear(scrnMsg.A);
    }

    /**
     * <pre>
     * The input scrnMsg is cleared. 
     * </pre>
     * @param arg0 NLBL3100BMsg
     */
    public static void setNameForMessage(NLBL3100BMsg arg0) {

        NLBL3100BMsg scrnMsg = (NLBL3100BMsg) arg0;

        scrnMsg.rtlWhCd_H1.setNameForMessage(NAME_FOR_MESSAGE_RTL_WH_CD);
        scrnMsg.rtlWhNm_H1.setNameForMessage(NAME_FOR_MESSAGE_RTL_WH_NM);
        scrnMsg.schdCoordPsnCd_H1.setNameForMessage(NAME_FOR_MESSAGE_SCHD_COORD_PSN_CD);
        scrnMsg.xxPsnNm_H1.setNameForMessage(NAME_FOR_MESSAGE_SCHD_COORD_PSN_NM);
        scrnMsg.mgrPsnCd_H1.setNameForMessage(NAME_FOR_MESSAGE_MGR_PSN_CD);
        scrnMsg.xxPsnNm_H2.setNameForMessage(NAME_FOR_MESSAGE_MGR_PSN_NM);
        scrnMsg.supvPsnCd_H1.setNameForMessage(NAME_FOR_MESSAGE_SUPV_PSN_CD);
        scrnMsg.xxPsnNm_H3.setNameForMessage(NAME_FOR_MESSAGE_SUPV_PSN_NM);
        scrnMsg.carrCd_H1.setNameForMessage(NAME_FOR_MESSAGE_CARR_CD);
        scrnMsg.xxPsnNm_H4.setNameForMessage(NAME_FOR_MESSAGE_CARR_NM);
        scrnMsg.stCd_H1.setNameForMessage(NAME_FOR_MESSAGE_ST);
        scrnMsg.effFromDt_H1.setNameForMessage(NAME_FOR_MESSAGE_EFF_FROM_DT);
        scrnMsg.effThruDt_H1.setNameForMessage(NAME_FOR_MESSAGE_EFF_THRU_DT);
    }

}
