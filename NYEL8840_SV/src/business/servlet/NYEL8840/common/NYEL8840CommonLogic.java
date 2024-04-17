/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8840.common;

import static business.servlet.NYEL8840.constant.NYEL8840Constant.SCRN_ID_00;
import static business.servlet.NYEL8840.constant.NYEL8840Constant.CMN_BTN.CMN_APL;
import static business.servlet.NYEL8840.constant.NYEL8840Constant.CMN_BTN.CMN_APR;
import static business.servlet.NYEL8840.constant.NYEL8840Constant.CMN_BTN.CMN_CLR;
import static business.servlet.NYEL8840.constant.NYEL8840Constant.CMN_BTN.CMN_DEL;
import static business.servlet.NYEL8840.constant.NYEL8840Constant.CMN_BTN.CMN_DWL;
import static business.servlet.NYEL8840.constant.NYEL8840Constant.CMN_BTN.CMN_RJT;
import static business.servlet.NYEL8840.constant.NYEL8840Constant.CMN_BTN.CMN_RST;
import static business.servlet.NYEL8840.constant.NYEL8840Constant.CMN_BTN.CMN_RTN;
import static business.servlet.NYEL8840.constant.NYEL8840Constant.CMN_BTN.CMN_SAV;
import static business.servlet.NYEL8840.constant.NYEL8840Constant.CMN_BTN.CMN_SUB;
import business.servlet.NYEL8840.NYEL8840BMsg;
import business.servlet.NYEL8840.NYEL8840_ABMsg;
import business.servlet.NYEL8840.NYEL8840_ABMsgArray;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NYEL8840CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/06/26   Fujitsu         M.Yamada        Create          N/A
 *</pre>
 */
public class NYEL8840CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {
        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(CMN_SAV.btnName(), CMN_SAV.event(), CMN_SAV.label(), 0, null);
        handler.setButtonProperties(CMN_SUB.btnName(), CMN_SUB.event(), CMN_SUB.label(), 1, null);
        handler.setButtonProperties(CMN_APL.btnName(), CMN_APL.event(), CMN_APL.label(), 0, null);
        handler.setButtonProperties(CMN_APR.btnName(), CMN_APR.event(), CMN_APR.label(), 0, null);
        handler.setButtonProperties(CMN_RJT.btnName(), CMN_RJT.event(), CMN_RJT.label(), 0, null);
        handler.setButtonProperties(CMN_DWL.btnName(), CMN_DWL.event(), CMN_DWL.label(), 0, null);
        handler.setButtonProperties(CMN_DEL.btnName(), CMN_DEL.event(), CMN_DEL.label(), 0, null);
        handler.setButtonProperties(CMN_CLR.btnName(), CMN_CLR.event(), CMN_CLR.label(), 0, null);
        handler.setButtonProperties(CMN_RST.btnName(), CMN_RST.event(), CMN_RST.label(), 1, null);
        handler.setButtonProperties(CMN_RTN.btnName(), CMN_RTN.event(), CMN_RTN.label(), 1, null);
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
     * @param scrnMsg NYEL8840BMsg
     * @param scrnAMsgAry NYEL8840_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NYEL8840BMsg scrnMsg, NYEL8840_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NYEL8840BMsg
     * @param scrnAMsgAry NYEL8840_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     * @param grpRows color reversal switch lines
     */
    public static void setRowsBGWithClear(NYEL8840BMsg scrnMsg, NYEL8840_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color
     * back to the initial color.
     * @param scrnMsg NYEL8840BMsg
     * @param scrnAMsgAry NYEL8840_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void clearRowsBG(NYEL8840BMsg scrnMsg, NYEL8840_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * protectFields
     * @param scrnMsg NYEL8840BMsg
     */
    public static void protectFields(NYEL8840BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NYEL8840_ABMsg scrnLineMsg = scrnMsg.A.no(i);

            scrnLineMsg.xxWfAsgCd_A1.setInputProtected(true);
            scrnLineMsg.xxWfAsgNm_A1.setInputProtected(true);
            scrnLineMsg.wfDescTxt_AP.setInputProtected(true);
            scrnLineMsg.wfDescTxt_A1.setInputProtected(true);
        }

    }

    /**
     * buttonCtrl
     * @param handler S21CommonHandler
     * @param scrnMsg NYEL8840BMsg
     */
    public static void buttonCtrl(S21CommonHandler handler, NYEL8840BMsg scrnMsg) {
        if (scrnMsg.xxPageShowOfNum.getValueInt() == scrnMsg.xxMaxSrchCnt.getValueInt()) {
              handler.setButtonEnabled("Add", false);
          } else {
              handler.setButtonEnabled("Add", true);
          }

          if (scrnMsg.A.getValidCount() == 0) {
              handler.setButtonEnabled("Delete", false);
          } else {
              handler.setButtonEnabled("Delete", true);
          }

    }

}
