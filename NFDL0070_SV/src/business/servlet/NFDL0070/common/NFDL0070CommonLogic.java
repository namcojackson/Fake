/**
 * <Pre>
 * 
 * Copyright(c)2015 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
package business.servlet.NFDL0070.common;

import parts.common.EZDBMsgArray;
import parts.common.EZDBStringItem;
import parts.servletcommon.EZDCommonHandler;
import business.blap.NFDL0070.NFDL0070CMsg;
import business.servlet.NFDL0070.NFDL0070BMsg;
import business.servlet.NFDL0070.constant.NFDL0070Constant.PARAMS;

import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * <pre>
 * NFDL0070CommonLogic.
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/16   Fujitsu         M.Yamada        Create          N/A
 *</pre>
 */
public class NFDL0070CommonLogic {

    /**
     * @param scrnMsg NFDL0070BMsg
     * @return bizMsg NFDL0070CMsg
     */
    public static NFDL0070CMsg setRequestData_NFDL0070_INIT(NFDL0070BMsg scrnMsg) {

        NFDL0070CMsg bizMsg;

        bizMsg = new NFDL0070CMsg();
        bizMsg.setBusinessID("NFDL0070");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param params Object[]
     * @param scrnMsg NFDL0070BMsg
     */
    public static void otherBusConnectFrom_NFDL0070_INIT(Object[] params, NFDL0070BMsg scrnMsg) {

        scrnMsg.billToCustAcctCd.setValue(((EZDBStringItem) params[PARAMS.NUM_0.getValue()]).getValue());
    }

    /**
     * @param scrnMsg NFDL0070BMsg
     */
    public static void transMsgCheck(NFDL0070BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.xxPageShowOfNum);
        scrnMsg.addCheckItem(scrnMsg.xxRadioBtn);

    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFDL0070BMsg
     */
    public static void initialize(EZDCommonHandler scrnAppli, NFDL0070BMsg scrnMsg) {

        scrnMsg.setInputProtected(false);
        scrnMsg.dsAcctNm.setInputProtected(true);
        scrnAppli.setButtonProperties("SelectInvoice", "SelectInvoice", "Select", 1, null);

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn2", "", "Submit", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn6", "CMN_Download", "Download", 1, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn8", "", "Clear", 0, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
        scrnAppli.setButtonProperties("btn10", "CMN_Return", "Return", 1, null);

    }

    /**
     * @param scrnMsg NFDL0070BMsg
     */
    public static void setScrnItemValue_NFDL0070_INIT(NFDL0070BMsg scrnMsg) {

        scrnMsg.billToCustAcctCd.clear();
        scrnMsg.dsAcctNm.clear();
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();
        scrnMsg.xxPageShowCurNum.clear();
        scrnMsg.xxPageShowFromNum_H1.clear();
        scrnMsg.xxPageShowTotNum.clear();
        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);

    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFDL0070BMsg
     */
    public static void busBtnControl_NFDL0070_INIT(EZDCommonHandler scrnAppli, NFDL0070BMsg scrnMsg) {

        scrnAppli.setButtonEnabled("Continue", scrnMsg.A.getValidCount() > 0);

    }

    /**
     * @param scrnMsg NFDL0070BMsg
     * @return bizMsg NFDL0070CMsg
     */
    public static NFDL0070CMsg setRequestData_NFDL0070Scrn00_PageNext(NFDL0070BMsg scrnMsg) {

        NFDL0070CMsg bizMsg;

        bizMsg = new NFDL0070CMsg();
        bizMsg.setBusinessID("NFDL0070");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param scrnMsg NFDL0070BMsg
     * @return bizMsg NFDL0070CMsg
     */
    public static NFDL0070CMsg setRequestData_NFDL0070Scrn00_PagePrev(NFDL0070BMsg scrnMsg) {

        NFDL0070CMsg bizMsg;

        bizMsg = new NFDL0070CMsg();
        bizMsg.setBusinessID("NFDL0070");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param scrnMsg NFDL0070BMsg
     * @return bizMsg NFDL0070CMsg
     */
    public static NFDL0070CMsg setRequestData_NFDL0070Scrn00_PageJump(NFDL0070BMsg scrnMsg) {

        NFDL0070CMsg bizMsg;

        bizMsg = new NFDL0070CMsg();
        bizMsg.setBusinessID("NFDL0070");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFDL0070BMsg
     */
    public static void controlTablePrev_NFDL0070Scrn00_A(EZDCommonHandler scrnAppli, NFDL0070BMsg scrnMsg) {

        scrnMsg.xxPageShowFromNum.setValue(scrnMsg.xxPageShowFromNum.getValueInt() - scrnMsg.A.length() - 1);
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFDL0070BMsg
     */
    public static void controlTableNext_NFDL0070Scrn00_A(EZDCommonHandler scrnAppli, NFDL0070BMsg scrnMsg) {

        scrnMsg.xxPageShowFromNum.setValue(scrnMsg.xxPageShowToNum.getValueInt());
    }

    /**
     * @param scrnMsg
     * @param scrnMsg NFDL0070BMsg
     * @return bizMsg NFDL0070CMsg
     */
    public static NFDL0070CMsg setRequestData_NFDL0070Scrn00_Continue(NFDL0070BMsg scrnMsg) {
        NFDL0070CMsg bizMsg;

        bizMsg = new NFDL0070CMsg();
        bizMsg.setBusinessID("NFDL0070");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * setRowColors
     * @param scrnMsg NFDL0070BMsg
     */
    public static void setRowColors(NFDL0070BMsg scrnMsg) {

        scrnMsg.clearAllGUIAttribute("NFDL0070Scrn00");
        S21TableColorController tblColor = new S21TableColorController("NFDL0070Scrn00", scrnMsg);
        try {
            EZDBMsgArray table = (EZDBMsgArray) scrnMsg.getClass().getField("A").get(scrnMsg);
            tblColor.setAlternateRowsBG("A1", table);
            tblColor.setAlternateRowsBG("A2", table);

        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
