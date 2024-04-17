/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NPAL1420.common;

import static business.servlet.NPAL1420.constant.NPAL1420Constant.BTN_CMN_BTN_APPLY;
import static business.servlet.NPAL1420.constant.NPAL1420Constant.BTN_CMN_BTN_APPROVE;
import static business.servlet.NPAL1420.constant.NPAL1420Constant.BTN_CMN_BTN_CLEAR;
import static business.servlet.NPAL1420.constant.NPAL1420Constant.BTN_CMN_BTN_DELETE;
import static business.servlet.NPAL1420.constant.NPAL1420Constant.BTN_CMN_BTN_DOWNLOAD;
import static business.servlet.NPAL1420.constant.NPAL1420Constant.BTN_CMN_BTN_REJECT;
import static business.servlet.NPAL1420.constant.NPAL1420Constant.BTN_CMN_BTN_RESET;
import static business.servlet.NPAL1420.constant.NPAL1420Constant.BTN_CMN_BTN_RETURN;
import static business.servlet.NPAL1420.constant.NPAL1420Constant.BTN_CMN_BTN_SAVE;
import static business.servlet.NPAL1420.constant.NPAL1420Constant.BTN_CMN_BTN_SUBMIT;
import static business.servlet.NPAL1420.constant.NPAL1420Constant.FUNC_ID_SUBMIT;
import static business.servlet.NPAL1420.constant.NPAL1420Constant.MODE_CREATE;
import static business.servlet.NPAL1420.constant.NPAL1420Constant.MODE_UPDATE;
import static business.servlet.NPAL1420.constant.NPAL1420Constant.SCREEN_ID;

import java.util.List;

import parts.common.EZDBItem;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NPAL1420.NPAL1420BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * <pre>
 * Business ID : NPAL1420 Reman Task Entry
 * Function Name : 
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 5/17/2016   CITS       Yasushi Nomura       Create          N/A
 * 1/29/2018   CITS       T.Wada               Update          QC#23072
 *</pre>
 */
public class NPAL1420CommonLogic {

    /**
     * @param handler S21CommonHandler
     * @param scrnMsg NPAL1420BMsg
     * @param funcList List<String>
     */
    public static void control(S21CommonHandler handler, NPAL1420BMsg scrnMsg, List<String> funcList) {
        boolean isReadOnly = true;
        for (String function : funcList) {
            if (FUNC_ID_SUBMIT.equals(function)) {
                isReadOnly = false;
                break;
            }
        }
        if (isReadOnly) {
            scrnMsg.xxModeCd.clear();
        }
        // common
        disable(scrnMsg.xxMsgTxt_WH);
        disable(scrnMsg.rmnfMainUnitSerNum);
        disable(scrnMsg.rmnfOrdNum);
        disable(scrnMsg.rmnfOrdStsDescTxt);
        disable(scrnMsg.techNm_H);
        disable(scrnMsg.xxMsgTxt_TS);
        disable(scrnMsg.techNm_L);
        disable(scrnMsg.rmnfCostPerHourAmt);
        disable(scrnMsg.stdCcyCd_PH);
        disable(scrnMsg.rmnfLborCostAmt);
        disable(scrnMsg.stdCcyCd_LC);
        disable(scrnMsg.rtlWhCd);
        disable(scrnMsg.rtlSwhCd);
        disable(scrnMsg.prtUnitCostAmt);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            disable(scrnMsg.A.no(i).mdseDescShortTxt_A1);
            disable(scrnMsg.A.no(i).prtUnitCostAmt_A1);
            disable(scrnMsg.A.no(i).invtyAvalQty_A1);
            disable(scrnMsg.A.no(i).prtUnitCostAmt_T);
        }
        // Footer
        disable(handler, BTN_CMN_BTN_APPLY[0]);
        disable(handler, BTN_CMN_BTN_APPROVE[0]);
        disable(handler, BTN_CMN_BTN_REJECT[0]);
        disable(handler, BTN_CMN_BTN_DOWNLOAD[0]);
        disable(handler, BTN_CMN_BTN_CLEAR[0]);
        enable(handler, BTN_CMN_BTN_RETURN[0]);

        if ((MODE_CREATE.equals(scrnMsg.xxModeCd.getValue())) || (MODE_UPDATE.equals(scrnMsg.xxModeCd.getValue()))) {
            enable(scrnMsg.rmnfTaskDescTxt);
            enable(scrnMsg.rmnfTaskStartDt);
            enable(scrnMsg.rmnfTaskEndDt);
            enable(scrnMsg.spclInstnCmntTxt);
            enable(scrnMsg.techTocCd_AC);
            enable(scrnMsg.techTocCd);
            enable(handler, "Search_Tech");
            enable(scrnMsg.rmnfLborAot);
            enable(scrnMsg.rmnfLborCmntTxt);
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).rmnfPrtUsgComitFlg_A1) 
                	        && ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).rmnfPrtUsgComitFlg_A1.getValue())) {
                	disable(scrnMsg.A.no(i).xxChkBox_A1);
                    handler.setButtonEnabled("OpenWin_Item", i, false);
                    handler.setButtonEnabled("Search_Item", i, false);
                    disable(scrnMsg.A.no(i).mdseCd_A1);
                	disable(scrnMsg.A.no(i).rmnfPrtQty_A1);
                	disable(scrnMsg.A.no(i).rmnfPrtRelQty_A1);
                } else {
                    enable(scrnMsg.A.no(i).xxChkBox_A1);
                    handler.setButtonEnabled("OpenWin_Item", i, true);
                    enable(scrnMsg.A.no(i).mdseCd_A1);
                    handler.setButtonEnabled("Search_Item", i, true);
                }
            }
            enable(handler, "AddLine");
            enable(handler, "DeleteLine");
            enable(handler, BTN_CMN_BTN_SAVE[0]);

            if (MODE_UPDATE.equals(scrnMsg.xxModeCd.getValue())) {
                enable(handler, BTN_CMN_BTN_DELETE[0]);
                enable(handler, BTN_CMN_BTN_SUBMIT[0]);
            } else {
                disable(handler, BTN_CMN_BTN_DELETE[0]);
                disable(handler, BTN_CMN_BTN_SUBMIT[0]);
            }
            enable(handler, BTN_CMN_BTN_RESET[0]);
        } else {
            disable(scrnMsg.rmnfTaskDescTxt);
            disable(scrnMsg.rmnfTaskStartDt);
            disable(scrnMsg.rmnfTaskEndDt);
            disable(scrnMsg.spclInstnCmntTxt);
            disable(scrnMsg.techTocCd_AC);
            disable(scrnMsg.techTocCd);
            disable(handler, "Search_Tech");
            disable(scrnMsg.rmnfLborAot);
            disable(scrnMsg.rmnfLborCmntTxt);
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                disable(scrnMsg.A.no(i).xxChkBox_A1);
                handler.setButtonEnabled("OpenWin_Item", i, false);
                disable(scrnMsg.A.no(i).mdseCd_A1);
                handler.setButtonEnabled("Search_Item", i, false);
                disable(scrnMsg.A.no(i).rmnfPrtQty_A1);
                disable(scrnMsg.A.no(i).rmnfPrtRelQty_A1);
            }
            disable(handler, "AddLine");
            disable(handler, "DeleteLine");
            disable(handler, BTN_CMN_BTN_SAVE[0]);
            disable(handler, BTN_CMN_BTN_DELETE[0]);
            disable(handler, BTN_CMN_BTN_RESET[0]);
        }
    }

    private static void enable(EZDBItem item) {
        item.setInputProtected(false);
    }

    private static void disable(EZDBItem item) {
        item.setInputProtected(true);
    }

    private static void enable(S21CommonHandler handler, String itemName) {
        handler.setButtonEnabled(itemName, true);
    }

    private static void disable(S21CommonHandler handler, String itemName) {
        handler.setButtonEnabled(itemName, false);
    }

    /**
     * <pre>
     * Initial common button
     * </pre>
     * @param handler EZCommandHandler
     */
    public static void initCommonButton(EZDCommonHandler handler) {
        handler.setButtonProperties(BTN_CMN_BTN_SAVE[0], BTN_CMN_BTN_SAVE[1], BTN_CMN_BTN_SAVE[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_SUBMIT[0], BTN_CMN_BTN_SUBMIT[1], BTN_CMN_BTN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_APPLY[0], BTN_CMN_BTN_APPLY[1], BTN_CMN_BTN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_APPROVE[0], BTN_CMN_BTN_APPROVE[1], BTN_CMN_BTN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_REJECT[0], BTN_CMN_BTN_REJECT[1], BTN_CMN_BTN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_DOWNLOAD[0], BTN_CMN_BTN_DOWNLOAD[1], BTN_CMN_BTN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_DELETE[0], BTN_CMN_BTN_DELETE[1], BTN_CMN_BTN_DELETE[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_CLEAR[0], BTN_CMN_BTN_CLEAR[1], BTN_CMN_BTN_CLEAR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_RESET[0], BTN_CMN_BTN_RESET[1], BTN_CMN_BTN_RESET[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_RETURN[0], BTN_CMN_BTN_RETURN[1], BTN_CMN_BTN_RETURN[2], 1, null);
    }

    /**
     * Init
     * @param scrnMsg NPAL1420BMsg
     */
    public static void commonInit(NPAL1420BMsg scrnMsg) {
    }

    /**
     * @param scrnMsg NPAL1420BMsg
     */
    public static void setAppFracDigit(NPAL1420BMsg scrnMsg) {
        scrnMsg.rmnfCostPerHourAmt.setAppFracDigit(2);
        scrnMsg.rmnfLborCostAmt.setAppFracDigit(2);
        scrnMsg.prtUnitCostAmt.setAppFracDigit(2);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).prtUnitCostAmt_A1.setAppFracDigit(2);
            scrnMsg.A.no(i).prtUnitCostAmt_T.setAppFracDigit(2);
        }
    }

    /**
     * Do addCheckItem for items
     * @param scrnMsg NPAL1420BMsg
     */
    public static void addCheckItem(NPAL1420BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.techTocCd);
        scrnMsg.addCheckItem(scrnMsg.rmnfTaskDescTxt);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).rmnfPrtQty_A1);
        }
    }

    /**
     * <pre>
     * Details are initialized (sorting sign deletion and BG color control).
     * </pre>
     * @param scrnMsg Screen Msg
     * @param baseContents String[][]
     */
    public static void clearGUIAttribute(NPAL1420BMsg scrnMsg, String[][] baseContents) {
        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, baseContents);
        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
    }

    /**
     * setTableColor
     * @param scrnMsg NPAL1420BMsg
     */
    public static void setTableColor(NPAL1420BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A_Right", scrnMsg.A);

    }
}
