/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2330.common;

import static business.servlet.NWAL2330.constant.NWAL2330Constant.BTN_CMN_CLR;
import static business.servlet.NWAL2330.constant.NWAL2330Constant.BTN_CMN_CLS;
import static business.servlet.NWAL2330.constant.NWAL2330Constant.SCRN_ID_00;
import business.servlet.NWAL2330.NWAL2330BMsg;
import business.servlet.NWAL2330.NWAL2330_ABMsgArray;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NWAL2330CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/08   Fujitsu         H.Ikeda         Create          N/A
 *</pre>
 */
public class NWAL2330CommonLogic {

    /**
     * Initial Common Button properties.
     * 
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {
        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_CLS[0], BTN_CMN_CLS[1], BTN_CMN_CLS[2], 1, null);

    }

    /**
     * Set Common Button properties.
     * 
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
     * 
     * @param scrnMsg     NWAL2330BMsg
     * @param scrnAMsgAry NWAL2330_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NWAL2330BMsg scrnMsg, NWAL2330_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NWAL2330BMsg
     * @param scrnAMsgAry NWAL2330_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     * @param grpRows     color reversal switch lines
     */
    public static void setRowsBGWithClear(NWAL2330BMsg scrnMsg, NWAL2330_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color back to the initial color. 
     * 
     * @param scrnMsg     NWAL2330BMsg
     * @param scrnAMsgAry NWAL2330_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void clearRowsBG(NWAL2330BMsg scrnMsg, NWAL2330_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * scrnProtect
     * 
     * @param scrnMsg NWAL2330BMsg
     * @param clearFlg boolean
     */
    public static void scrnProtect(NWAL2330BMsg scrnMsg, boolean clearFlg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctNm_A2.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctNm_A3.setInputProtected(true);
            scrnMsg.A.no(i).cpoSrcTpDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsOrdCatgDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsOrdTpDescTxt_A1.setInputProtected(true);
        }
    }

    /**
     * appFracDigit
     * 
     * @param scrnMsg NWAL2330BMsg
     */
    public static void appFracDigit(NWAL2330BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).invTotFuncNetAmt_A1.setAppFracDigit(2);
        }
    }
}
