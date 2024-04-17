package business.servlet.NFDL0060.common;

import java.util.List;

import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFDL0060.NFDL0060BMsg;
import business.servlet.NFDL0060.NFDL0060Bean;
import business.servlet.NFDL0060.constant.NFDL0060Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_DISP_TP;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/17   Fujitsu         Kamide          Create          N/A
 * 2016/06/24   Hitachi         K.Kojima        Update          QC#10260
 * 2016/06/28   Hitachi         K.Kojima        Update          QC#10260
 * 2016/07/08   Hitachi         K.Kojima        Update          QC#11417
 * 2016/07/28   Hitachi         K.Kojima        Update          QC#10260
 * 2016/09/13   Hitachi         K.Kojima        Update          QC#13257
 * 2017/07/31   Hitachi         T.Tsuchida      Update          QC#19575
 *</pre>
 */
public class NFDL0060CommonLogic implements NFDL0060Constant {

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

            handler.setButtonEnabled(SEARCH_BUTTON, false);

        } else if (functionList.contains("NFDL0060T010")) {

            // Inquiry
            handler.setButtonProperties(COMMON_BUTTON_1[0], COMMON_BUTTON_1[1], COMMON_BUTTON_1[2], 0, null); // Save
            handler.setButtonProperties(COMMON_BUTTON_2[0], COMMON_BUTTON_2[1], COMMON_BUTTON_2[2], 0, null); // Submit
            handler.setButtonProperties(COMMON_BUTTON_3[0], COMMON_BUTTON_3[1], COMMON_BUTTON_3[2], 0, null); // Apply
            handler.setButtonProperties(COMMON_BUTTON_4[0], COMMON_BUTTON_4[1], COMMON_BUTTON_4[2], 0, null); // Approve
            handler.setButtonProperties(COMMON_BUTTON_5[0], COMMON_BUTTON_5[1], COMMON_BUTTON_5[2], 0, null); // Reject
            // TODO Download function is not implemented.
            handler.setButtonProperties(COMMON_BUTTON_6[0], COMMON_BUTTON_6[1], COMMON_BUTTON_6[2], 0, null); // Download
            handler.setButtonProperties(COMMON_BUTTON_7[0], COMMON_BUTTON_7[1], COMMON_BUTTON_7[2], 0, null); // Delete
            handler.setButtonProperties(COMMON_BUTTON_8[0], COMMON_BUTTON_8[1], COMMON_BUTTON_8[2], 1, null); // Clear
            handler.setButtonProperties(COMMON_BUTTON_9[0], COMMON_BUTTON_9[1], COMMON_BUTTON_9[2], 1, null); // Reset
            handler.setButtonProperties(COMMON_BUTTON_10[0], COMMON_BUTTON_10[1], COMMON_BUTTON_10[2], 1, null); // Return

            handler.setButtonEnabled(SEARCH_BUTTON, true);
        }
    }

    /**
     * controlScreenFields
     * @param scrnMsg NFDL0060BMsg
     */
    public static final void controlScreenFields(NFDL0060BMsg scrnMsg) {
        // START 2016/07/08 K.Kojima [QC#11417,DEL]
        // if
        // (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxEdtModeFlg.getValue()))
        // {
        // END 2016/07/08 K.Kojima [QC#11417,DEL]
        scrnMsg.cltDispTpCd_H3.setInputProtected(false);
        // START 2016/07/08 K.Kojima [QC#11417,DEL]
        // } else {
        // scrnMsg.cltDispTpCd_H3.setInputProtected(true);
        // }
        // END 2016/07/08 K.Kojima [QC#11417,DEL]

        // START 2016/07/08 K.Kojima [QC#11417,MOD]
        // if
        // (CLT_DISP_TP.MYGROUP.equals(scrnMsg.cltDispTpCd_H3.getValue()))
        // {
        // scrnMsg.xxLinkProt_H1.setInputProtected(false);
        // scrnMsg.xxQueryFltrTxt_H1.setInputProtected(false);
        // scrnMsg.cltPsnNm_H1.setInputProtected(false);
        // } else {
        // scrnMsg.xxLinkProt_H1.setInputProtected(true);
        // scrnMsg.xxQueryFltrTxt_H1.setInputProtected(true);
        // scrnMsg.cltPsnNm_H1.setInputProtected(true);
        // }
        if (CLT_DISP_TP.SELF.equals(scrnMsg.cltDispTpCd_H3.getValue())) {
            // START 2016/07/28 K.Kojima [QC#10260,ADD]
            scrnMsg.xxLinkProt_H1.clear();
            // END 2016/07/28 K.Kojima [QC#10260,ADD]
            scrnMsg.xxLinkProt_H1.setInputProtected(true);
            scrnMsg.xxQueryFltrTxt_H1.setInputProtected(true);
            scrnMsg.cltPsnNm_H1.setInputProtected(true);
            scrnMsg.xxQueryFltrTxt_H1.clear();
            scrnMsg.cltPsnNm_H1.clear();
        } else {
            scrnMsg.xxLinkProt_H1.setInputProtected(false);
            // START 2016/07/28 K.Kojima [QC#10260,ADD]
            scrnMsg.xxLinkProt_H1.setValue(ZYPConstant.FLG_ON_1);
            // END 2016/07/28 K.Kojima [QC#10260,ADD]
            scrnMsg.xxQueryFltrTxt_H1.setInputProtected(false);
            scrnMsg.cltPsnNm_H1.setInputProtected(false);
        }
        // END 2016/07/08 K.Kojima [QC#11417,MOD]

    }

    /**
     * Set table column sort parameters
     * @param ctx EZDApplicationContext
     * @param scrnMsg NFDL0060BMsg
     */
    public static void setTableColumnSortParameters(EZDApplicationContext ctx, NFDL0060BMsg scrnMsg) {
        Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxSortTblNm, param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME));
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxSortItemNm, param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME));
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxSortOrdByTxt, param.getSingleValue(S21TableColumnSortConstant.ORDER_BY));
    }

    /**
     * Alternate table row colors
     * @param scrnMsg NPAL0190BMsg
     */
    public static void alternateTableRowColors(NFDL0060BMsg scrnMsg) {
        scrnMsg.clearAllGUIAttribute(SCREEN_ID);

        S21TableColorController control = new S21TableColorController(NFDL0060Constant.SCREEN_ID, scrnMsg);
        control.setAlternateRowsBG(NFDL0060Bean.A, scrnMsg.A);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).xxTotAmt_A1.setAppFracDigit(2);
        }
    }

    /**
     * Alternate table row colors
     * @param scrnMsg NPAL0190BMsg
     */
    public static void addCheckItem(NFDL0060BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.cltDispTpCd_H3);
        scrnMsg.addCheckItem(scrnMsg.xxQueryFltrTxt_H1);
        // START 2016/09/13 K.Kojima [QC#13257,MOD]
        // scrnMsg.addCheckItem(scrnMsg.dsAcctNum_H1);
        scrnMsg.addCheckItem(scrnMsg.xxQueryFltrTxt_H2);
        // END 2016/09/13 K.Kojima [QC#13257,MOD]
        scrnMsg.addCheckItem(scrnMsg.cltItemTpCd_H3);

        scrnMsg.addCheckItem(scrnMsg.cltPsnNm_H1);
        // START 2017/07/31 T.Tsuchida [QC#19575,ADD]
        scrnMsg.addCheckItem(scrnMsg.cltPtfoNm_H1);
        // END 2017/07/31 T.Tsuchida [QC#19575,ADD]
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm_H1);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_H1);

        scrnMsg.addCheckItem(scrnMsg.xxFromDt_H1);
        scrnMsg.addCheckItem(scrnMsg.xxToDt_H1);
    }

    /**
     * Check if popup return event is 'Select' event
     * @param lastPopupEventName Popup return event name
     * @return true: select event, false: close event
     */
    public static boolean isPopupSelectEvent(String lastPopupEventName) {
        if (!POPUP_CLOSE_EVENT.toLowerCase().equals(lastPopupEventName.toLowerCase())) {
            return true;
        }
        return false;
    }

    public static List<String> getFunctionCodeList(S21UserProfileService profileService) {
        return profileService.getFunctionCodeListForBizAppId(BUSINESS_ID);
    }

    // START 2016/06/24 K.Kojima [QC#10260,ADD]
    /**
     * @param scrnMsg NFDL0060BMsg
     */
    public static void setInputProtected(NFDL0060BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            // START 2016/06/28 K.Kojima [QC#10260,ADD]
            scrnMsg.A.no(i).cltTaskPk_A1.setInputProtected(true);
            // END 2016/06/28 K.Kojima [QC#10260,ADD]
            scrnMsg.A.no(i).cltStrgyNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_A1.setInputProtected(true);
            scrnMsg.A.no(i).cltPsnNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(true);
            // START 2017/07/31 T.Tsuchida [QC#19575,ADD]
            scrnMsg.A.no(i).cltPtfoNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctNum_A1.setInputProtected(true);
            // END 2017/07/31 T.Tsuchida [QC#19575,ADD]
            scrnMsg.A.no(i).xxScrItem130Txt_A3.setInputProtected(true);
        }
    }
    // END 2016/06/24 K.Kojima [QC#10260,ADD]
}
