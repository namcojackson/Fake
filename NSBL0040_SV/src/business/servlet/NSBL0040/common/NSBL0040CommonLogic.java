package business.servlet.NSBL0040.common;

import java.util.List;

import parts.common.EZDBStringItem;
import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSBL0040.NSBL0040BMsg;
import business.servlet.NSBL0040.NSBL0040Bean;
import business.servlet.NSBL0040.constant.NSBL0040Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/**
 * Credit Approval
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/01   SRA             Otsuji          Create          N/A
 * 2013/09/10   SRA             Yanada          Update          QC2131
 * 2017/06/14   Hitachi         Y.Osawa         Update          QC#18970
 * 2019/10/02   Hitachi         K.Kitachi       Update          QC#53692
 *</pre>
 */
public class NSBL0040CommonLogic implements NSBL0040Constant {

    /**
     * Activate buttons
     * @param handler S21CommonHandler
     * @param functionList List<String>
     */
    public static void activateButtons(S21CommonHandler handler, List<String> functionList) {

        if (functionList == null || functionList.isEmpty()) {

            handler.setButtonProperties(COMMON_BUTTON_1[0], COMMON_BUTTON_1[1], COMMON_BUTTON_1[2], 0, null); // Save
            handler.setButtonProperties(COMMON_BUTTON_2[0], COMMON_BUTTON_2[1], COMMON_BUTTON_2[2], 0, null); // Submit
            handler.setButtonProperties(COMMON_BUTTON_3[0], COMMON_BUTTON_3[1], COMMON_BUTTON_3[2], 0, null); // Apply
            handler.setButtonProperties(COMMON_BUTTON_4[0], COMMON_BUTTON_4[1], COMMON_BUTTON_4[2], 0, null); // Approve
            handler.setButtonProperties(COMMON_BUTTON_5[0], COMMON_BUTTON_5[1], COMMON_BUTTON_5[2], 0, null); // Reject
            handler.setButtonProperties(COMMON_BUTTON_6[0], COMMON_BUTTON_6[1], COMMON_BUTTON_6[2], 0, null); // Download
            handler.setButtonProperties(COMMON_BUTTON_7[0], COMMON_BUTTON_7[1], COMMON_BUTTON_7[2], 0, null); // Delete
            handler.setButtonProperties(COMMON_BUTTON_8[0], COMMON_BUTTON_8[1], COMMON_BUTTON_8[2], 0, null); // Clear
            handler.setButtonProperties(COMMON_BUTTON_9[0], COMMON_BUTTON_9[1], COMMON_BUTTON_9[2], 0, null); // Reset
            handler.setButtonProperties(COMMON_BUTTON_10[0], COMMON_BUTTON_10[1], COMMON_BUTTON_10[2], 1, null); // Return

            handler.setButtonEnabled(SEARCH_BILL_TO_BUTTON, false);
            handler.setButtonEnabled(SEARCH_SELL_TO_BUTTON, false);
            handler.setButtonEnabled(SEARCH_SHIP_TO_BUTTON, false);
            handler.setButtonEnabled(SEARCH_BUTTON, false);
            handler.setButtonEnabled(SELECT_ALL_BUTTON, false);
            handler.setButtonEnabled(UN_SELECT_ALL_BUTTON, false);
        } else if (functionList.contains("NSBL0040T020")) {

            // Approval
            handler.setButtonProperties(COMMON_BUTTON_1[0], COMMON_BUTTON_1[1], COMMON_BUTTON_1[2], 0, null); // Save
            handler.setButtonProperties(COMMON_BUTTON_2[0], COMMON_BUTTON_2[1], COMMON_BUTTON_2[2], 0, null); // Submit
            handler.setButtonProperties(COMMON_BUTTON_3[0], COMMON_BUTTON_3[1], COMMON_BUTTON_3[2], 0, null); // Apply
            handler.setButtonProperties(COMMON_BUTTON_4[0], COMMON_BUTTON_4[1], COMMON_BUTTON_4[2], 1, null); // Approve
            handler.setButtonProperties(COMMON_BUTTON_5[0], COMMON_BUTTON_5[1], COMMON_BUTTON_5[2], 1, null); // Reject
            handler.setButtonProperties(COMMON_BUTTON_6[0], COMMON_BUTTON_6[1], COMMON_BUTTON_6[2], 1, null); // Download
            handler.setButtonProperties(COMMON_BUTTON_7[0], COMMON_BUTTON_7[1], COMMON_BUTTON_7[2], 0, null); // Delete
            handler.setButtonProperties(COMMON_BUTTON_8[0], COMMON_BUTTON_8[1], COMMON_BUTTON_8[2], 1, null); // Clear
            handler.setButtonProperties(COMMON_BUTTON_9[0], COMMON_BUTTON_9[1], COMMON_BUTTON_9[2], 0, null); // Reset
            handler.setButtonProperties(COMMON_BUTTON_10[0], COMMON_BUTTON_10[1], COMMON_BUTTON_10[2], 1, null); // Return

            handler.setButtonEnabled(SEARCH_BILL_TO_BUTTON, true);
            handler.setButtonEnabled(SEARCH_SELL_TO_BUTTON, true);
            handler.setButtonEnabled(SEARCH_SHIP_TO_BUTTON, true);
            handler.setButtonEnabled(SEARCH_BUTTON, true);
            handler.setButtonEnabled(SELECT_ALL_BUTTON, true);
            handler.setButtonEnabled(UN_SELECT_ALL_BUTTON, true);
        } else if (functionList.contains("NSBL0040T010")) {

            // Inquiry
            handler.setButtonProperties(COMMON_BUTTON_1[0], COMMON_BUTTON_1[1], COMMON_BUTTON_1[2], 0, null); // Save
            handler.setButtonProperties(COMMON_BUTTON_2[0], COMMON_BUTTON_2[1], COMMON_BUTTON_2[2], 0, null); // Submit
            handler.setButtonProperties(COMMON_BUTTON_3[0], COMMON_BUTTON_3[1], COMMON_BUTTON_3[2], 0, null); // Apply
            handler.setButtonProperties(COMMON_BUTTON_4[0], COMMON_BUTTON_4[1], COMMON_BUTTON_4[2], 0, null); // Approve
            handler.setButtonProperties(COMMON_BUTTON_5[0], COMMON_BUTTON_5[1], COMMON_BUTTON_5[2], 0, null); // Reject
            handler.setButtonProperties(COMMON_BUTTON_6[0], COMMON_BUTTON_6[1], COMMON_BUTTON_6[2], 1, null); // Download
            handler.setButtonProperties(COMMON_BUTTON_7[0], COMMON_BUTTON_7[1], COMMON_BUTTON_7[2], 0, null); // Delete
            handler.setButtonProperties(COMMON_BUTTON_8[0], COMMON_BUTTON_8[1], COMMON_BUTTON_8[2], 1, null); // Clear
            handler.setButtonProperties(COMMON_BUTTON_9[0], COMMON_BUTTON_9[1], COMMON_BUTTON_9[2], 0, null); // Reset
            handler.setButtonProperties(COMMON_BUTTON_10[0], COMMON_BUTTON_10[1], COMMON_BUTTON_10[2], 1, null); // Return

            handler.setButtonEnabled(SEARCH_BILL_TO_BUTTON, true);
            handler.setButtonEnabled(SEARCH_SELL_TO_BUTTON, true);
            handler.setButtonEnabled(SEARCH_SHIP_TO_BUTTON, true);
            handler.setButtonEnabled(SEARCH_BUTTON, true);
            handler.setButtonEnabled(SELECT_ALL_BUTTON, false);
            handler.setButtonEnabled(UN_SELECT_ALL_BUTTON, false);
        }
    }

    /**
     * Activate screen items
     * @param scrnMsg NSBL0040BMsg
     * @param functionList List<String>
     */
    public static void activateScreenItems(NSBL0040BMsg scrnMsg, List<String> functionList) {

        scrnMsg.locNm_01.setInputProtected(true);
        scrnMsg.locNm_02.setInputProtected(true);
        scrnMsg.locNm_03.setInputProtected(true);

        boolean approver = functionList != null && functionList.contains("NSBL0040T020");

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            if (approver) {
                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
            } else {
                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(true);
            }

            // Bill to
            scrnMsg.A.no(i).xxLinkProt_A1.setInputProtected(false);
            scrnMsg.A.no(i).billToCustCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).locNm_A1.setInputProtected(true);

            // Rsn
            scrnMsg.A.no(i).xxLinkProt_A2.setInputProtected(false);
            scrnMsg.A.no(i).xxExstFlg_A1.setInputProtected(true);

            // Task
            scrnMsg.A.no(i).xxLinkProt_A3.setInputProtected(false);
            scrnMsg.A.no(i).svcTaskNum_A1.setInputProtected(true);

            // Rmk
            scrnMsg.A.no(i).xxLinkProt_A4.setInputProtected(false);
            scrnMsg.A.no(i).xxExstFlg_A2.setInputProtected(true);

            // Bill Type
            scrnMsg.A.no(i).svcBillTpCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).svcBillTpNm_A1.setInputProtected(true);

            // Term
            scrnMsg.A.no(i).pmtTermCashDiscCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).pmtTermCashDiscNm_A1.setInputProtected(true);

            // Tech
            scrnMsg.A.no(i).techCd_A1.setInputProtected(true);

            // CCY
            scrnMsg.A.no(i).origInvCcyCd_A1.setInputProtected(true);

            // Total Charge
            scrnMsg.A.no(i).svcLborDealAmt_A1.setInputProtected(true);

            // Task Created By
            scrnMsg.A.no(i).ezInUserID_A1.setInputProtected(true);
            scrnMsg.A.no(i).hrTtlNm_A1.setInputProtected(true);
        }
    }

    /**
     * Set table column sort parameters
     * @param ctx EZDApplicationContext
     * @param scrnMsg NSBL0040BMsg
     */
    public static void setTableColumnSortParameters(EZDApplicationContext ctx, NSBL0040BMsg scrnMsg) {
        Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxSortTblNm, param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME));
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxSortItemNm, param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME));
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxSortOrdByTxt, param.getSingleValue(S21TableColumnSortConstant.ORDER_BY));
    }

    /**
     * Alternate table row colors
     * @param scrnMsg NPAL0190BMsg
     */
    public static void alternateTableRowColors(NSBL0040BMsg scrnMsg) {
        // QC2131
        scrnMsg.clearAllGUIAttribute(SCREEN_ID);

        S21TableColorController control = new S21TableColorController(NSBL0040Constant.SCREEN_ID, scrnMsg);
        control.setAlternateRowsBG(NSBL0040Bean.A, scrnMsg.A);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A1.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxTblItemTxt_A1, "pEvenNumberBGcolor");
                control.setRowStyle(NSBL0040Bean.A, i, "checkLineBGcolor");
            }

            // QC2131
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).techCd_A1)) {
                scrnMsg.A.no(i).xxLinkProt_A5.setInputProtected(false);
            } else {
                scrnMsg.A.no(i).xxLinkProt_A5.setInputProtected(true);
                EZDGUIAttribute guiAttr = new EZDGUIAttribute(SCREEN_ID, "techLink#" + i);
                guiAttr.setVisibility(false);
                scrnMsg.addGUIAttribute(guiAttr);
            }
        }
    }

    // START 2017/06/14 Y.Osawa [QC#18970, ADD]
    /**
     * clear PopupData For ScrnMsg
     * @param scrnMsg NSBL0040BMsg
     */
    public static final void clearPopupDataForScrnMsg(NSBL0040BMsg scrnMsg) {
        scrnMsg.xxPopPrm_00.clear();
        scrnMsg.xxPopPrm_01.clear();
        scrnMsg.xxPopPrm_02.clear();
        scrnMsg.xxPopPrm_03.clear();
        scrnMsg.xxPopPrm_04.clear();
        scrnMsg.xxPopPrm_05.clear();
        scrnMsg.xxPopPrm_06.clear();
        scrnMsg.xxPopPrm_07.clear();
        scrnMsg.xxPopPrm_08.clear();
        scrnMsg.xxPopPrm_09.clear();
        scrnMsg.xxPopPrm_10.clear();
        scrnMsg.xxPopPrm_11.clear();
        scrnMsg.xxPopPrm_12.clear();
        scrnMsg.xxPopPrm_13.clear();
        scrnMsg.xxPopPrm_14.clear();
        scrnMsg.xxPopPrm_15.clear();
        scrnMsg.xxPopPrm_16.clear();
    }
    // END   2017/06/14 Y.Osawa [QC#18970, ADD]

    // START 2019/10/02 K.Kitachi [QC#53692, ADD]
    /**
     * set open parameter LocNm
     * @param locNmItem EZDBStringItem
     * @param xxPopPrmItem EZDBStringItem
     */
    public static final void setOpenParamLocNm(EZDBStringItem locNmItem, EZDBStringItem xxPopPrmItem) {
        String locNm = "";
        if (ZYPCommonFunc.hasValue(locNmItem)) {
            locNm = locNmItem.getValue();
            if (locNm.length() == 60) {
                locNm = locNm.substring(0, 59) + "%";
            }
        }
        ZYPEZDItemValueSetter.setValue(xxPopPrmItem, locNm);
    }

    /**
     * set return parameter LocNm
     * @param locNmItem EZDBStringItem
     * @param xxPopPrmItem EZDBStringItem
     */
    public static final void setReturnParamLocNm(EZDBStringItem locNmItem, EZDBStringItem xxPopPrmItem) {
        String xxPopPrm = "";
        if (ZYPCommonFunc.hasValue(xxPopPrmItem)) {
            xxPopPrm = xxPopPrmItem.getValue();
            if (xxPopPrm.length() > 60) {
                xxPopPrm = xxPopPrm.substring(0, 60);
            }
        }
        ZYPEZDItemValueSetter.setValue(locNmItem, xxPopPrm);
    }
    // END 2019/10/02 K.Kitachi [QC#53692, ADD]
}
