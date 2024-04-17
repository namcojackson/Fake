/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWWL0010.common;

import static business.servlet.NWWL0010.constant.NWWL0010Constant.BTN_CMN_APL;
import static business.servlet.NWWL0010.constant.NWWL0010Constant.BTN_CMN_APR;
import static business.servlet.NWWL0010.constant.NWWL0010Constant.BTN_CMN_CLR;
import static business.servlet.NWWL0010.constant.NWWL0010Constant.BTN_CMN_DEL;
import static business.servlet.NWWL0010.constant.NWWL0010Constant.BTN_CMN_DWL;
import static business.servlet.NWWL0010.constant.NWWL0010Constant.BTN_CMN_RJT;
import static business.servlet.NWWL0010.constant.NWWL0010Constant.BTN_CMN_RST;
import static business.servlet.NWWL0010.constant.NWWL0010Constant.BTN_CMN_RTN;
import static business.servlet.NWWL0010.constant.NWWL0010Constant.BTN_CMN_SAV;
import static business.servlet.NWWL0010.constant.NWWL0010Constant.BTN_CMN_SUB;
import static business.servlet.NWWL0010.constant.NWWL0010Constant.SCRN_ID_00;
import business.servlet.NWWL0010.NWWL0010BMsg;
import business.servlet.NWWL0010.NWWL0010_ABMsgArray;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NWWL0010CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/19   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NWWL0010CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {

        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 1, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);
    }

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
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NWWL0010BMsg
     * @param scrnAMsgAry NWWL0010_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NWWL0010BMsg scrnMsg, NWWL0010_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NWWL0010BMsg
     * @param scrnAMsgAry NWWL0010_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     * @param grpRows color reversal switch lines
     */
    public static void setRowsBGWithClear(NWWL0010BMsg scrnMsg, NWWL0010_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color
     * back to the initial color.
     * @param scrnMsg NWWL0010BMsg
     * @param scrnAMsgAry NWWL0010_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void clearRowsBG(NWWL0010BMsg scrnMsg, NWWL0010_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * Control Item
     * @param scrnMsg NWWL0010BMsg
     */
    public static void controlListItem(NWWL0010BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).ntfyHdrNm_A0.setInputProtected(true);
            scrnMsg.A.no(i).ntfyHdrDescTxt_A0.setInputProtected(true);
            scrnMsg.A.no(i).ntfyBizAreaTpNm_A0.setInputProtected(true);
            scrnMsg.A.no(i).ntfySubAreaTpNm_A0.setInputProtected(true);
            scrnMsg.A.no(i).effFromDt_A0.setInputProtected(true);
            scrnMsg.A.no(i).effThruDt_A0.setInputProtected(true);
        }
    }
}
