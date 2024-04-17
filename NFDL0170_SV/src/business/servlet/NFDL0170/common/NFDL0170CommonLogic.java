/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0170.common;

import static business.servlet.NFDL0170.constant.NFDL0170Constant.*;
import business.servlet.NFDL0170.NFDL0170BMsg;
import business.servlet.NFDL0170.NFDL0170Bean;
import business.servlet.NFDL0170.NFDL0170_ABMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/** 
 *<pre>
 * Customer Account Search Popup
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/07   Hitachi         T.Tsuchida      Create          QC#19574
 * 2018/01/15   Fujitsu         H.Ikeda         Update          QC#22759
 * 2018/06/05   Fujitsu         Y.Matsui        Update          QC#26456
 * 2018/10/18   Fujitsu         T.Noguchi       Update          QC#28434
 * 2024/02/23   Hitachi         S.Ikariya       Update          QC#63452
 *</pre>
 */
public class NFDL0170CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {

        handler.setButtonProperties(BTN_08_CLR_NAME, BTN_08_CLR_GUARD, BTN_08_CLR_LABEL, 1, null);
        handler.setButtonProperties(BTN_10_CLS_NAME, BTN_10_CLS_GUARD, BTN_10_CLS_LABEL, 1, null);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NFDL0170BMsg
     * @param scrnAMsgAry NFDL0170_ABMsgArray
     */
    public static void setRowsBGWithClear(NFDL0170BMsg scrnMsg, NFDL0170_ABMsgArray scrnAMsgAry) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
        tblColor.clearRowsBG(NFDL0170Bean.A, scrnAMsgAry);
        tblColor.setAlternateRowsBG(NFDL0170Bean.A, scrnAMsgAry);
    }

    /**
     * Set Common Button properties.
     * @param handler Event Handler
     * @param scrnMsg NFDL0170BMsg
     */
    public static void controlScreen(S21CommonHandler handler, NFDL0170BMsg scrnMsg) {

        NFDL0170_ABMsgArray lineMsgArray = scrnMsg.A;

        if (lineMsgArray.getValidCount() > 0) {
            controlScreenDetailFields(lineMsgArray);
        }
        scrnMsg.setFocusItem(scrnMsg.billToCustAcctNm_H);
   }

    /**
     * controlScreenDetailFields
     * @param scrnMsg NFDL0170BMsg
     */
    private static final void controlScreenDetailFields(NFDL0170_ABMsgArray lineMsgArray) {

        for (int i = 0; i < lineMsgArray.getValidCount(); i++) {
            lineMsgArray.no(i).billToCustAcctNm_A.setInputProtected(true);
            lineMsgArray.no(i).billToCustAcctCd_A.setInputProtected(true);
            // START 2018/01/15 H.Ikeda [QC#22759,ADD]
            lineMsgArray.no(i).locNum_A.setInputProtected(true);
            // END   2018/01/15 H.Ikeda [QC#22759,ADD]
        }
    }

    /**
     * addCheckItemHeader
     * @param scrnMsg NFDL0170BMsg
     */
    public static void addCheckItemHeader(NFDL0170BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.billToCustAcctNm_H);
        scrnMsg.addCheckItem(scrnMsg.billToCustAcctCd_H);
        scrnMsg.addCheckItem(scrnMsg.shipToLocNm_H);
        scrnMsg.addCheckItem(scrnMsg.shipToCustCd_H);
        scrnMsg.addCheckItem(scrnMsg.invNum_FR);
        scrnMsg.addCheckItem(scrnMsg.invNum_TO);
        scrnMsg.addCheckItem(scrnMsg.mdlNm_H);
        // START 2018/01/15 H.Ikeda [QC#22759,ADD]
        scrnMsg.addCheckItem(scrnMsg.grpInvNum_H);
        // END   2018/01/15 H.Ikeda [QC#22759,ADD]
        scrnMsg.addCheckItem(scrnMsg.serNum_H);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_CL);
    }

    /**
     * clearSrchCrit
     * @param scrnMsg NFDL0170BMsg
     */
    public static void clearSrchCrit(NFDL0170BMsg scrnMsg) {

        scrnMsg.billToCustAcctNm_H.clear();
        scrnMsg.billToCustAcctCd_H.clear();
        scrnMsg.shipToLocNm_H.clear();
        scrnMsg.shipToCustCd_H.clear();
        scrnMsg.invNum_FR.clear();
        scrnMsg.invNum_TO.clear();
        scrnMsg.mdlNm_H.clear();
        // START 2018/01/15 H.Ikeda [QC#22759,ADD]
        scrnMsg.grpInvNum_H.clear();
        // END   2018/01/15 H.Ikeda [QC#22759,ADD]
        scrnMsg.serNum_H.clear();
        // START 2018/06/05 Y.Matsui [QC#26456,ADD]
        scrnMsg.custIssPoNum_H.clear();
        // END   2018/06/05 Y.Matsui [QC#26456,ADD]
        scrnMsg.xxChkBox_CL.clear();
        // START 2024/02/23 S.Ikariya [QC#63452, ADD]
        scrnMsg.invNum_SR.clear();
        // END 2024/02/23 S.Ikariya [QC#63452, ADD]
    }

    // 2018/10/18 QC#28434 Add Start
    /**
     * Set To Search Condition
     * @param scrnMsg NFDL0170BMsg
     */
    public static void setToSearchCondition(NFDL0170BMsg scrnMsg) {

        // Invoice Number
        if (ZYPCommonFunc.hasValue(scrnMsg.invNum_FR) && !ZYPCommonFunc.hasValue(scrnMsg.invNum_TO)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.invNum_TO, scrnMsg.invNum_FR);
        }
    }
    // 2018/10/18 QC#28434 Add End
}
