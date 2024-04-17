/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3080.common;

import static business.servlet.NFCL3080.constant.NFCL3080Constant.BIZ_ID;
import static business.servlet.NFCL3080.constant.NFCL3080Constant.BTN_01_SAV_GUARD;
import static business.servlet.NFCL3080.constant.NFCL3080Constant.BTN_01_SAV_LABEL;
import static business.servlet.NFCL3080.constant.NFCL3080Constant.BTN_01_SAV_NAME;
import static business.servlet.NFCL3080.constant.NFCL3080Constant.BTN_02_SUB_GUARD;
import static business.servlet.NFCL3080.constant.NFCL3080Constant.BTN_02_SUB_LABEL;
import static business.servlet.NFCL3080.constant.NFCL3080Constant.BTN_02_SUB_NAME;
import static business.servlet.NFCL3080.constant.NFCL3080Constant.BTN_03_APL_GUARD;
import static business.servlet.NFCL3080.constant.NFCL3080Constant.BTN_03_APL_LABEL;
import static business.servlet.NFCL3080.constant.NFCL3080Constant.BTN_03_APL_NAME;
import static business.servlet.NFCL3080.constant.NFCL3080Constant.BTN_04_APR_GUARD;
import static business.servlet.NFCL3080.constant.NFCL3080Constant.BTN_04_APR_LABEL;
import static business.servlet.NFCL3080.constant.NFCL3080Constant.BTN_04_APR_NAME;
import static business.servlet.NFCL3080.constant.NFCL3080Constant.BTN_05_REJ_GUARD;
import static business.servlet.NFCL3080.constant.NFCL3080Constant.BTN_05_REJ_LABEL;
import static business.servlet.NFCL3080.constant.NFCL3080Constant.BTN_05_REJ_NAME;
import static business.servlet.NFCL3080.constant.NFCL3080Constant.BTN_06_DWL_GUARD;
import static business.servlet.NFCL3080.constant.NFCL3080Constant.BTN_06_DWL_LABEL;
import static business.servlet.NFCL3080.constant.NFCL3080Constant.BTN_06_DWL_NAME;
import static business.servlet.NFCL3080.constant.NFCL3080Constant.BTN_07_DEL_GUARD;
import static business.servlet.NFCL3080.constant.NFCL3080Constant.BTN_07_DEL_LABEL;
import static business.servlet.NFCL3080.constant.NFCL3080Constant.BTN_07_DEL_NAME;
import static business.servlet.NFCL3080.constant.NFCL3080Constant.BTN_08_CLE_GUARD;
import static business.servlet.NFCL3080.constant.NFCL3080Constant.BTN_08_CLE_LABEL;
import static business.servlet.NFCL3080.constant.NFCL3080Constant.BTN_08_CLE_NAME;
import static business.servlet.NFCL3080.constant.NFCL3080Constant.BTN_09_RST_GUARD;
import static business.servlet.NFCL3080.constant.NFCL3080Constant.BTN_09_RST_LABEL;
import static business.servlet.NFCL3080.constant.NFCL3080Constant.BTN_09_RST_NAME;
import static business.servlet.NFCL3080.constant.NFCL3080Constant.BTN_10_RTR_GUARD;
import static business.servlet.NFCL3080.constant.NFCL3080Constant.BTN_10_RTR_LABEL;
import static business.servlet.NFCL3080.constant.NFCL3080Constant.BTN_10_RTR_NAME;
import static business.servlet.NFCL3080.constant.NFCL3080Constant.FUNC_ID_UPDATE;
import static business.servlet.NFCL3080.constant.NFCL3080Constant.SCRN_ID_00;
import static business.servlet.NFCL3080.constant.NFCL3080Constant.ZZSM4300E;

import java.util.List;

import business.servlet.NFCL3080.NFCL3080BMsg;
import business.servlet.NFCL3080.NFCL3080_ABMsg;
import business.servlet.NFCL3080.NFCL3080_ABMsgArray;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NFCL3080CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/1/13    Fujitsu         S.Fujita        Create          N/A
 *</pre>
 */
public class NFCL3080CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {

        handler.setButtonProperties(BTN_01_SAV_NAME, BTN_01_SAV_GUARD, BTN_01_SAV_LABEL, 0, null);
        handler.setButtonProperties(BTN_02_SUB_NAME, BTN_02_SUB_GUARD, BTN_02_SUB_LABEL, 0, null);
        handler.setButtonProperties(BTN_03_APL_NAME, BTN_03_APL_GUARD, BTN_03_APL_LABEL, 0, null);
        handler.setButtonProperties(BTN_04_APR_NAME, BTN_04_APR_GUARD, BTN_04_APR_LABEL, 0, null);
        handler.setButtonProperties(BTN_05_REJ_NAME, BTN_05_REJ_GUARD, BTN_05_REJ_LABEL, 0, null);
        handler.setButtonProperties(BTN_06_DWL_NAME, BTN_06_DWL_GUARD, BTN_06_DWL_LABEL, 0, null);
        handler.setButtonProperties(BTN_07_DEL_NAME, BTN_07_DEL_GUARD, BTN_07_DEL_LABEL, 0, null);
        handler.setButtonProperties(BTN_08_CLE_NAME, BTN_08_CLE_GUARD, BTN_08_CLE_LABEL, 0, null);
        handler.setButtonProperties(BTN_09_RST_NAME, BTN_09_RST_GUARD, BTN_09_RST_LABEL, 0, null);
        handler.setButtonProperties(BTN_10_RTR_NAME, BTN_10_RTR_GUARD, BTN_10_RTR_LABEL, 1, null);
    }

    /**
     * setGuiAttr
     * @param handler S21CommonHandler
     * @param scrnMsg BMAL0040BMsg
     */
    public static void setGuiAttr(S21CommonHandler handler, NFCL3080BMsg scrnMsg) {

        //Details
        NFCL3080_ABMsgArray lineMsgArray = scrnMsg.A;
        boolean hasUpdateFunc = hasUpdateFuncId();
        if (lineMsgArray.getValidCount() > 0) {
            for (int i = 0; i < lineMsgArray.getValidCount(); i++) {
                NFCL3080_ABMsg lineMsg = lineMsgArray.no(i);
                lineMsg.invLineNum_A.setInputProtected(true);
                lineMsg.firstBllgAttrbValTxt_A.setInputProtected(true);
                lineMsg.serNum_A.setInputProtected(true);
                lineMsg.mtrLbDescTxt_A.setInputProtected(true);
                lineMsg.testCopyQty_B.setInputProtected(true);
                lineMsg.contrMtrMultRate_A.setInputProtected(true);
                lineMsg.bllgCopyQty_A.setInputProtected(true);
                lineMsg.mtrChrgDealAmt_A.setInputProtected(true);
                lineMsg.shipToCustCd_A.setInputProtected(true);

                if (hasUpdateFunc) {
                    //Check print
                    if (scrnMsg.invNum_H.getValue().equals("NOT_EXIST")) {
                        lineMsg.prevTotCopyQty_A.setInputProtected(false);
                        lineMsg.totCopyQty_A.setInputProtected(false);
                        lineMsg.testCopyQty_A.setInputProtected(false);
                        lineMsg.copyInclQty_A.setInputProtected(false);
                        lineMsg.xsMtrAmtRate_A.setInputProtected(false);
                    } else {
                        lineMsg.prevTotCopyQty_A.setInputProtected(true);
                        lineMsg.totCopyQty_A.setInputProtected(true);
                        lineMsg.testCopyQty_A.setInputProtected(true);
                        lineMsg.copyInclQty_A.setInputProtected(true);
                        lineMsg.xsMtrAmtRate_A.setInputProtected(true);
                    }
                } else {
                    lineMsg.prevTotCopyQty_A.setInputProtected(true);
                    lineMsg.totCopyQty_A.setInputProtected(true);
                    lineMsg.testCopyQty_A.setInputProtected(true);
                    lineMsg.copyInclQty_A.setInputProtected(true);
                    lineMsg.xsMtrAmtRate_A.setInputProtected(true);
                }
            }
            if (hasUpdateFunc) {
                //Check print
                if (scrnMsg.invNum_H.getValue().equals("NOT_EXIST")) {
                handler.setButtonProperties(BTN_02_SUB_NAME, BTN_02_SUB_GUARD, BTN_02_SUB_LABEL, 1, null);
                }
            }
        }
    }

    /**
     * hasUpdateFuncId
     * @return boolean
     */
    public static boolean hasUpdateFuncId() {
        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();

        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(BIZ_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException(ZZSM4300E, new String[] {userProfSvc.getContextUserInfo().getUserId() });
        }

        if (funcList.contains(FUNC_ID_UPDATE)) {
            return true;
        }
        return false;
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NFCL3080BMsg
     * @param scrnAMsgAry NFCL3080_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NFCL3080BMsg scrnMsg, NFCL3080_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NFCL3080BMsg
     * @param scrnAMsgAry NFCL3080_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     * @param grpRows color reversal switch lines
     */
    public static void setRowsBGWithClear(NFCL3080BMsg scrnMsg, NFCL3080_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color
     * back to the initial color.
     * @param scrnMsg NFCL3080BMsg
     * @param scrnAMsgAry NFCL3080_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void clearRowsBG(NFCL3080BMsg scrnMsg, NFCL3080_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * addCheckAllItem.
     * @param scrnMsg NFCL3080BMsg
     */
    public static void addCheckAllItem(NFCL3080BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NFCL3080_ABMsg aBMsg = scrnMsg.A.no(i);
            scrnMsg.addCheckItem(aBMsg.prevTotCopyQty_A);
            scrnMsg.addCheckItem(aBMsg.totCopyQty_A);
            scrnMsg.addCheckItem(aBMsg.testCopyQty_A);
            scrnMsg.addCheckItem(aBMsg.copyInclQty_A);
            scrnMsg.addCheckItem(aBMsg.xsMtrAmtRate_A);
        }
    }
}
