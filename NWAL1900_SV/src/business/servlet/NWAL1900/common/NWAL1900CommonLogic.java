package business.servlet.NWAL1900.common;

import static business.servlet.NWAL1900.constant.NWAL1900Constant.APPLIED;
import static business.servlet.NWAL1900.constant.NWAL1900Constant.BTN_CMN_CLR;
import static business.servlet.NWAL1900.constant.NWAL1900Constant.BTN_CMN_CLS;
import static business.servlet.NWAL1900.constant.NWAL1900Constant.DEF_ROW_BG_COLOR;
import static business.servlet.NWAL1900.constant.NWAL1900Constant.HTML_TBL_ID_A_LEFT;
import static business.servlet.NWAL1900.constant.NWAL1900Constant.HTML_TBL_ID_A_RIGHT;
import static business.servlet.NWAL1900.constant.NWAL1900Constant.QUALIFIED;
import static business.servlet.NWAL1900.constant.NWAL1900Constant.SCRN_ID_00;
import static business.servlet.NWAL1900.constant.NWAL1900Constant.HTML_COLOR_YELLOW;
import static business.servlet.NWAL1900.constant.NWAL1900Constant.TD_ID_DESCRIPTION;
import parts.common.EZDGUIAttribute;
import business.servlet.NWAL1900.NWAL1900BMsg;
import business.servlet.NWAL1900.NWAL1900_ABMsgArray;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

public class NWAL1900CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {
        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLS[0], BTN_CMN_CLS[1], BTN_CMN_CLS[2], 1, null);

    }

    /**
     * Set description of detail matrix
     * @param scrnMsg Screen Message of NWAL1900BMsg
     * @param baseContents Color of Raw
     */
    public static void setBgColor(NWAL1900BMsg scrnMsg) {

        // Set HTML Table background color.
        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        tblColor.clearRowsBG(HTML_TBL_ID_A_LEFT, scrnMsg.A);
        tblColor.clearRowsBG(HTML_TBL_ID_A_RIGHT, scrnMsg.A);
        tblColor.setAlternateRowsBG(HTML_TBL_ID_A_LEFT, scrnMsg.A);
        tblColor.setAlternateRowsBG(HTML_TBL_ID_A_RIGHT, scrnMsg.A);

        scrnMsg.clearAllGUIAttribute(SCRN_ID_00);

        NWAL1900CommonLogic.setDefaultRowBgColor(scrnMsg);
    }

    /**
     * <pre>
     * Details are initialized (sorting sign deletion and BG color control).
     * </pre>
     * @param scrnMsg NWAL1900BMsg
     */
    public static void setDefaultRowBgColor(NWAL1900BMsg scrnMsg) {

        // Set Default Row
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (scrnMsg.A.no(i).xxCmntTxt_A3.getValue().equals(APPLIED)) {
                S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
                tblColor.setRowStyle(HTML_TBL_ID_A_LEFT, i, DEF_ROW_BG_COLOR);
                tblColor.setRowStyle(HTML_TBL_ID_A_RIGHT, i, DEF_ROW_BG_COLOR);
            }

            if (scrnMsg.A.no(i).xxCmntTxt_A3.getValue().equals(QUALIFIED)) {

                EZDGUIAttribute setColorAttribute = null;
                setColorAttribute = new EZDGUIAttribute(SCRN_ID_00, TD_ID_DESCRIPTION + i);
                setColorAttribute.setStyleAttribute("background-color", HTML_COLOR_YELLOW);

                scrnMsg.addGUIAttribute(setColorAttribute);

            }
        }
    }

    // QC#29752 2018/12/28 Add Start
    /**
     * setProtect
     * @param scrnMsg
     */
    public static void setProtect(NWAL1900BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).prcRuleCondTpNm_A.setInputProtected(true);
            scrnMsg.A.no(i).xxCmntTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxCmntTxt_A2.setInputProtected(true);
            scrnMsg.A.no(i).defRulePrcdNum_A.setInputProtected(true);
            scrnMsg.A.no(i).effFromDt_A.setInputProtected(true);
            scrnMsg.A.no(i).effThruDt_A.setInputProtected(true);
            scrnMsg.A.no(i).xxCmntTxt_A3.setInputProtected(true);
            scrnMsg.A.no(i).prcRulePrcdPk_A.setInputProtected(true);
            scrnMsg.A.no(i).prcPrcdActTpNm_A.setInputProtected(true);
        }
    }
    // QC#29752 2018/12/28 Add End
}
