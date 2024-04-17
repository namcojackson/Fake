/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2590.common;

import static business.servlet.NMAL2590.constant.NMAL2590Constant.BTN_CMN_CLR;
import static business.servlet.NMAL2590.constant.NMAL2590Constant.BTN_CMN_CLS;
import static business.servlet.NMAL2590.constant.NMAL2590Constant.SCREEN_ID;
import static business.servlet.NMAL2590.constant.NMAL2590Constant.TBL_ID_A;
import business.servlet.NMAL2590.NMAL2590BMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NMAL2590 CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/11   Fujitsu         C.Yokoi         Create          CSA-QC#4096
 *</pre>
 */
public class NMAL2590CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {
        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_CLS[0], BTN_CMN_CLS[1], BTN_CMN_CLS[2], 1, null);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NMAL2590BMsg
     */
    public static void setRowsBGWithClear(NMAL2590BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);

        tblColor.setAlternateRowsBG(TBL_ID_A, scrnMsg.A);
    }

    /**
     * @param scrnMsg NMAL2590BMsg
     */
    public static void setInputProtect(NMAL2590BMsg scrnMsg) {
        scrnMsg.ctyAddr.setInputProtected(false);
        scrnMsg.stCd.setInputProtected(false);
        scrnMsg.postCd.setInputProtected(false);
        scrnMsg.cntyNm.setInputProtected(false);
    }
}
