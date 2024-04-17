/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1210.common;

import static business.servlet.NPAL1210.constant.NPAL1210Constant.BTN_CMN_BTN_10;
import static business.servlet.NPAL1210.constant.NPAL1210Constant.BTN_CMN_BTN_8;
import static business.servlet.NPAL1210.constant.NPAL1210Constant.DISPLAY_NM_DOCUMENT_SOURCE_TYPE;
import static business.servlet.NPAL1210.constant.NPAL1210Constant.DISPLAY_NM_ORDER_NUMBER;
import static business.servlet.NPAL1210.constant.NPAL1210Constant.HTML_ID_NOTE;
import static business.servlet.NPAL1210.constant.NPAL1210Constant.SCRN_ID;
import static business.servlet.NPAL1210.constant.NPAL1210Constant.STYLE_BG_COLOR;
import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NPAL1210.NPAL1210BMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * PO/Inventory Approval History
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/20/2016   CITS            R Shimamoto     Create          N/A
 * 03/08/2016   CITS            K.Ogino         Update          QC#5148
 * 03/24/2016   CITS            T.Tokutomi      Update          QC#5763
 *</pre>
 */
public class NPAL1210CommonLogic {

    /**
     * The method explanation: The display control of the screen item
     * for Initialized screen
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1210BMsg
     */
    public static void cntrlScrnItemDispInit(EZDCommonHandler handler, NPAL1210BMsg scrnMsg) {

        // clear html attribute
        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        // Header
        scrnMsg.apvlHistSrcTpDescTxt.setInputProtected(true);
        scrnMsg.trxRefNum.setInputProtected(true);

        // Detail
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).seqNumber_A1.setInputProtected(true);
            scrnMsg.A.no(i).apvlFullPsnNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).apvlHistActTpDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxDtTm_A1.setInputProtected(true);
            scrnMsg.A.no(i).apvlHistTxt_A1.setInputProtected(true);
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxDtTm_A1)) {
                String formatDateAndTime = ZYPDateUtil.formatEzd14ToDisp(scrnMsg.A.no(i).xxDtTm_A1.getValue().substring(0, 14));
                formatDateAndTime = formatDateAndTime.substring(0,formatDateAndTime.lastIndexOf(':'));

                //set yyyy/MM/dd HH:mm
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxDtTm_A1, formatDateAndTime);
            }
            scrnMsg.A.no(i).apvlHistTxt_A1.setInputProtected(true);

            EZDGUIAttribute note = new EZDGUIAttribute(SCRN_ID, HTML_ID_NOTE + i);
            if((i + 1) % 2 == 0){
                note.setStyleAttribute(STYLE_BG_COLOR, EZDGUIAttribute.pEvenNumberBGcolor);
            } else {
                note.setStyleAttribute(STYLE_BG_COLOR, EZDGUIAttribute.pOddNumberBGcolor);
            }
            scrnMsg.addGUIAttribute(note);
        }

        // common button protection
        // 0 : inactive
        // 1 : active
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);
    }

    /**
     * <pre>
     * The input scrnMsg is cleared. 
     * </pre>
     * @param scrnMsg NPAL1210BMsg
     */
    public static void clearScreenMsg(NPAL1210BMsg scrnMsg) {

        // Header
        scrnMsg.apvlHistSrcTpCd.clear();
        scrnMsg.apvlHistSrcTpDescTxt.clear();
        scrnMsg.trxRefNum.clear();

        // Detail
        ZYPTableUtil.clear(scrnMsg.A);
    }

    /**
     * <pre>
     * The input scrnMsg is cleared. 
     * </pre>
     * @param arg0 NPAL1210BMsg
     */
    public static void setNameForMessage(NPAL1210BMsg arg0) {

        NPAL1210BMsg scrnMsg = (NPAL1210BMsg) arg0;

        scrnMsg.apvlHistSrcTpDescTxt.setNameForMessage(DISPLAY_NM_DOCUMENT_SOURCE_TYPE);
        scrnMsg.trxRefNum.setNameForMessage(DISPLAY_NM_ORDER_NUMBER);
    }

}
