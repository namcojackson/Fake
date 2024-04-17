/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWWL0060.common;

import static business.servlet.NWWL0060.constant.NWWL0060Constant.BTN_CMN_APL;
import static business.servlet.NWWL0060.constant.NWWL0060Constant.BTN_CMN_APR;
import static business.servlet.NWWL0060.constant.NWWL0060Constant.BTN_CMN_CLR;
import static business.servlet.NWWL0060.constant.NWWL0060Constant.BTN_CMN_DEL;
import static business.servlet.NWWL0060.constant.NWWL0060Constant.BTN_CMN_DWL;
import static business.servlet.NWWL0060.constant.NWWL0060Constant.BTN_CMN_RJT;
import static business.servlet.NWWL0060.constant.NWWL0060Constant.BTN_CMN_RST;
import static business.servlet.NWWL0060.constant.NWWL0060Constant.BTN_CMN_RTN;
import static business.servlet.NWWL0060.constant.NWWL0060Constant.BTN_CMN_SAV;
import static business.servlet.NWWL0060.constant.NWWL0060Constant.BTN_CMN_SUB;
import static business.servlet.NWWL0060.constant.NWWL0060Constant.SCRN_ID_00;
import business.servlet.NWWL0060.NWWL0060BMsg;
import business.servlet.NWWL0060.NWWL0060_ABMsgArray;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NWWL0060CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/05   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NWWL0060CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {
        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 1, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 1, null);
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
     * @param scrnMsg NWWL0060BMsg
     * @param scrnAMsgAry NWWL0060_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NWWL0060BMsg scrnMsg, NWWL0060_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NWWL0060BMsg
     * @param scrnAMsgAry NWWL0060_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     * @param grpRows color reversal switch lines
     */
    public static void setRowsBGWithClear(NWWL0060BMsg scrnMsg, NWWL0060_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color
     * back to the initial color.
     * @param scrnMsg NWWL0060BMsg
     * @param scrnAMsgAry NWWL0060_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void clearRowsBG(NWWL0060BMsg scrnMsg, NWWL0060_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * controlScreenFields
     * @param scrnMsg NWWL0060BMsg
     */
    public static void controlScreenFields(NWWL0060BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).ntfyHdrNm_A.setInputProtected(true);
            scrnMsg.A.no(i).ntfyHdrDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).ntfyBizAreaTpDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).ntfySubAreaTpDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).ntfyDistListNmListTxt_A.setInputProtected(true);

        }
    }
}
