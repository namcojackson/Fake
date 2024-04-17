/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8850.common;

import static business.servlet.NYEL8850.constant.NYEL8850Constant.BTN_CMN_CLR;
import static business.servlet.NYEL8850.constant.NYEL8850Constant.BTN_CMN_CLS;
import parts.common.EZDGUIAttribute;
import static business.servlet.NYEL8850.constant.NYEL8850Constant.SCRN_ID_00;
import business.servlet.NYEL8850.NYEL8850BMsg;
import business.servlet.NYEL8850.NYEL8850_ABMsgArray;
import business.servlet.NYEL8850.NYEL8850_BBMsgArray;
import business.servlet.NYEL8850.NYEL8850_HBMsgArray;
import business.servlet.NYEL8850.constant.NYEL8850Constant;

import com.canon.cusa.s21.framework.nwf.core.model.S21NwfWorkItem;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NYEL8850CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/14   Fujitsu         Q09079          Create          N/A
 * 2018/10/24   Fujitsu         Q10814          Update          QC#28914
 *</pre>
 */
public class NYEL8850CommonLogic {

    /**
     * Set Common Button properties.
     * @param handler Event Handler
     * @param btnProp Button Properties in Constant class
     * @param sts Status (0:Inactive, 1:Active)
     */
    public static void setCmnBtnProp(S21CommonHandler handler, String[] btnProp, int sts) {
        handler.setButtonProperties(btnProp[0], btnProp[1], btnProp[2], sts, null);
    }

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
     * @param scrnMsg NYEL8850BMsg
     */
    public static void initRowCtrlProp(NYEL8850BMsg scrnMsg) {
        scrnMsg.clearAllGUIAttribute(SCRN_ID_00);

        headerListBtn(scrnMsg);

        inputProtect(scrnMsg);

        // BizLink,Detail Button
        cmnLinkProp(scrnMsg);
    }

    /**
     * @param scrnMsg
     */
    private static void headerListBtn(NYEL8850BMsg scrnMsg) {

        if (scrnMsg.H.getValidCount() == 1) {
            setVisibility(scrnMsg, NYEL8850Constant.VIEW_BTN_DISPLAY + "#0", false);
        }
    }

    /**
     * Common Link properties.
     * @param scrnMsg NYEL8850BMsg
     */
    private static void cmnLinkProp(NYEL8850BMsg scrnMsg) {
        for (int idx = 0; idx < scrnMsg.A.getValidCount(); idx++) {
            scrnMsg.A.no(idx).wfWrkItemNm_AL.clear();
        }
    }

    /**
     * Input Protect.
     * @param scrnMsg NYEL8850BMsg
     */
    private static void inputProtect(NYEL8850BMsg scrnMsg) {

        scrnMsg.wfDescTxt_PR.setInputProtected(true);
        scrnMsg.wfProcTagNm.setInputProtected(true);
        
        int max = scrnMsg.H.getValidCount();
        for (int idx = 0; idx < max; idx++) {
            scrnMsg.H.no(idx).wfProcDocId_H.setInputProtected(true);
            scrnMsg.H.no(idx).wfProcNm_H.setInputProtected(true);
        }

        max = scrnMsg.A.getValidCount();
        for (int idx = 0; idx < max; idx++) {
            scrnMsg.A.no(idx).xxWfAsgToNm_A.setInputProtected(true);

            String taskSts = scrnMsg.A.no(idx).wfWrkItemStsCd_A.getValue();
            if (S21NwfWorkItem.STATUS.RUN.getCode().equals(taskSts)){
                setVisibility(scrnMsg, "xxWfAsgToNm_A#" + Integer.toString(idx), true);
            } else {
                setVisibility(scrnMsg, "xxWfAsgToNm_A#" + Integer.toString(idx), false);
            }
        }

        max = scrnMsg.B.getValidCount();
        for (int idx = 0; idx < max; idx++) {
            scrnMsg.B.no(idx).xxRowNum_B.setInputProtected(true);
            scrnMsg.B.no(idx).wfWrkItemId_B.setInputProtected(true);
            scrnMsg.B.no(idx).wfWrkItemNm_B.setInputProtected(true);
            scrnMsg.B.no(idx).actWfCondNm_B.setInputProtected(true);
            scrnMsg.B.no(idx).xxWfActOpNm_B.setInputProtected(true);
            scrnMsg.B.no(idx).wfCmntTxt_B.setInputProtected(true);
            scrnMsg.B.no(idx).xxWfAsgFromNm_B.setInputProtected(true);
            scrnMsg.B.no(idx).xxWfAsgToNm_B.setInputProtected(true);
            scrnMsg.B.no(idx).wfBizAttrbTxt_B1.setInputProtected(true);
            scrnMsg.B.no(idx).wfBizAttrbTxt_B2.setInputProtected(true);
            scrnMsg.B.no(idx).wfBizAttrbTxt_B3.setInputProtected(true);
            scrnMsg.B.no(idx).wfBizAttrbTxt_B4.setInputProtected(true);
            scrnMsg.B.no(idx).wfBizAttrbTxt_B5.setInputProtected(true);
            scrnMsg.B.no(idx).wfBizAttrbLbTxt_B1.setInputProtected(true);
            scrnMsg.B.no(idx).wfBizAttrbLbTxt_B2.setInputProtected(true);
            scrnMsg.B.no(idx).wfBizAttrbLbTxt_B3.setInputProtected(true);
            scrnMsg.B.no(idx).wfBizAttrbLbTxt_B4.setInputProtected(true);
            scrnMsg.B.no(idx).wfBizAttrbLbTxt_B5.setInputProtected(true);
// 2018/10/24 QC#28914 ADD START
            scrnMsg.B.no(idx).xxScrItem130Txt_B1.setInputProtected(true);
            scrnMsg.B.no(idx).xxScrItem130Txt_B2.setInputProtected(true);
            scrnMsg.B.no(idx).xxScrItem130Txt_B3.setInputProtected(true);
            scrnMsg.B.no(idx).xxScrItem130Txt_B4.setInputProtected(true);
            scrnMsg.B.no(idx).xxScrItem130Txt_B5.setInputProtected(true);
// 2018/10/24 QC#28914 ADD END
        }
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NYEL8850BMsg
     * @param scrnHMsgAry NYEL8850_HBMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClearForHead(NYEL8850BMsg scrnMsg, NYEL8850_HBMsgArray scrnHMsgAry, String tblId) {
        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnHMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnHMsgAry, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NYEL8850BMsg
     * @param scrnAMsgAry NYEL8850_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClearForSts(NYEL8850BMsg scrnMsg, NYEL8850_ABMsgArray scrnAMsgAry, String tblId) {
        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NYEL8850BMsg
     * @param scrnBMsgAry NYEL8850_BBMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClearForHist(NYEL8850BMsg scrnMsg, NYEL8850_BBMsgArray scrnBMsgAry, String tblId) {
        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnBMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnBMsgAry, 1);
    }

    private static void setVisibility(NYEL8850BMsg scrnMsg, String id, boolean isVisible) {
        EZDGUIAttribute ctrl = new EZDGUIAttribute(SCRN_ID_00, id);
        ctrl.setVisibility(isVisible);
        scrnMsg.addGUIAttribute(ctrl);
    }
}
