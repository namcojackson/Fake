/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSAL0600.common;

import static business.servlet.NSAL0600.constant.NSAL0600Constant.*;

import java.util.Arrays;
import java.util.List;

import parts.common.EZDBItem;
import parts.servletcommon.EZDCommonHandler;
import business.blap.NSAL0600.NSAL0600CMsg;
import business.servlet.NSAL0600.NSAL0600BMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Cascade Date
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/03   Hitachi         T.Tomita        Create          N/A
 * 2016/01/25   Hitachi         T.Tomita        Update          CSA QC#2721
 * 2016/02/16   Hitachi         T.Tomita        Update          CSA QC#3192
 *</pre>
 */
public class NSAL0600CommonLogic {

    private static S21UserProfileService getUserProfileService() {
        return S21UserProfileServiceFactory.getInstance().getService();
    }

    /**
     * createCMsgForSearch
     * @return NSAL0600CMsg
     */
    public static NSAL0600CMsg createCMsgForSearch() {
        NSAL0600CMsg bizMsg = new NSAL0600CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        return bizMsg;
    }

    /**
     * createCMsgForUpdate
     * @return NSAL0600CMsg
     */
    public static NSAL0600CMsg createCMsgForUpdate() {
        NSAL0600CMsg bizMsg = new NSAL0600CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("40");
        return bizMsg;
    }

    /**
     * screenControlProcess
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0600BMsg
     */
    public static void screenControlProcess(EZDCommonHandler handler, NSAL0600BMsg scrnMsg) {

        initialize(handler, scrnMsg);
        screenControlByFuncId(handler, scrnMsg);
        screenControlByItemValue(handler, scrnMsg);
        setTableBGColor(scrnMsg);
    }

    /**
     * Initialize the items and buttons on the screen.
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0600BMsg
     */
    private static void initialize(EZDCommonHandler handler, NSAL0600BMsg scrnMsg) {

        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
        scrnMsg.setInputProtected(false);

        // set button property
        // common button
        List<List<String>> cmnEnableButtons = Arrays.asList(SAVE, SUBMIT, APPLY, APPROVE, REJECT, DOWNLOAD, DELETE, CLEAR, RESET, RETURN);
        for (List<String> button : cmnEnableButtons) {
            handler.setButtonProperties(button.get(0), button.get(1), button.get(2), BTN_ACTIVE, null);
        }
        disableButtons(handler, SAVE, APPLY, APPROVE, REJECT, DOWNLOAD, DELETE, CLEAR);

        // optional button
        List<List<String>> optButtons = Arrays.asList(APPLY_TO_ALL, SELECTALL, UNSELECTALL);
        for (List<String> button : optButtons) {
            handler.setButtonProperties(button.get(0), button.get(1), button.get(2), BTN_ACTIVE, null);
        }
    }

    /**
     * Control items and buttons on the screen by Function ID.
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0600BMsg
     */
    private static void screenControlByFuncId(EZDCommonHandler handler, NSAL0600BMsg scrnMsg) {

        List<String> funcIdList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_ID);

        if (funcIdList == null || funcIdList.isEmpty()) {
            throw new S21AbendException("You can't operate FSR Link Error Inquiry(NSAL0600). UserID is -> " + getUserProfileService().getContextUserInfo().getUserId());
        }

        if (!funcIdList.contains(FUNC_ID_T020)) {
            // this user does not have Save/Submit authority.
            disableItemWithoutEdit(handler, scrnMsg);
        }
    }

    /**
     * Control items and buttons on the screen by Item value.
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0600BMsg
     */
    private static void screenControlByItemValue(EZDCommonHandler handler, NSAL0600BMsg scrnMsg) {

        disableItem(scrnMsg);

        if (scrnMsg.A.getValidCount() == 0) {
            disableButtons(handler, APPLY_TO_ALL, SELECTALL, UNSELECTALL, SUBMIT);
        }

        if (!chkStatus(scrnMsg.dsContrCtrlStsCd.getValue(), DS_CONTR_CTRL_STS.DRAFT, DS_CONTR_CTRL_STS.ENTERED, DS_CONTR_CTRL_STS.QA_HOLD)) {
            disableItemWithoutEdit(handler, scrnMsg);
        }
    }

    private static void disableItem(NSAL0600BMsg scrnMsg) {
        // Header
        disableItems(scrnMsg.contrVrsnEffFromDt, scrnMsg.contrVrsnEffThruDt, scrnMsg.contrVrsnEffFromDt_N, scrnMsg.contrVrsnEffThruDt_N);
        if (!chkStatus(scrnMsg.dsContrCtrlStsCd.getValue(), DS_CONTR_CTRL_STS.DRAFT, DS_CONTR_CTRL_STS.ENTERED)) {
            // START 2016/02/16 T.Tomita [QC#3192, MOD]
            if (chkStatus(scrnMsg.dsContrCtrlStsCd.getValue(), DS_CONTR_CTRL_STS.QA_HOLD)) {
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.invFlg_H.getValue())) {
                    disableItems(scrnMsg.xxFromDt_H);
                }
            } else {
                disableItems(scrnMsg.xxFromDt_H);
            }
            // END 2016/02/16 T.Tomita [QC#3192, MOD]
        }

        // Detail
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            disableItems(scrnMsg.A.no(i).contrEffFromDt_A, scrnMsg.A.no(i).contrEffThruDt_A, scrnMsg.A.no(i).contrEffFromDt_AN, scrnMsg.A.no(i).contrEffThruDt_AN);
            String dsContrDtlTpCd = scrnMsg.A.no(i).dsContrDtlTpCd_A.getValue();
            if (DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTpCd) || DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTpCd)) {
                disableItems(scrnMsg.A.no(i).xxChkBox_A);
            }
            if (!chkStatus(scrnMsg.A.no(i).dsContrCtrlStsCd_A.getValue(), DS_CONTR_CTRL_STS.DRAFT, DS_CONTR_CTRL_STS.ENTERED, DS_CONTR_CTRL_STS.QA_HOLD)) {
                disableItems(scrnMsg.A.no(i).xxChkBox_A);
            }
        }
    }

    private static void disableItemWithoutEdit(EZDCommonHandler handler, NSAL0600BMsg scrnMsg) {
        disableItems(scrnMsg.xxFromDt_H, scrnMsg.xxThruDt_H, scrnMsg.svcMemoRsnCd_HS, scrnMsg.svcCmntTxt_H);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            disableItems(scrnMsg.A.no(i).xxChkBox_A);
        }
        disableButtons(handler, APPLY_TO_ALL, SELECTALL, UNSELECTALL, SUBMIT, RESET);
    }

    /**
     * addCheckAllItem without search criteria.
     * @param scrnMsg NSAL0600BMsg
     */
    public static void addCheckAllItem(NSAL0600BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.xxFromDt_H);
        scrnMsg.addCheckItem(scrnMsg.xxThruDt_H);
        scrnMsg.addCheckItem(scrnMsg.svcMemoRsnCd_HS);
        scrnMsg.addCheckItem(scrnMsg.svcCmntTxt_H);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A);
        }
    }

    /**
     * addCheckAllItem without submit criteria.
     * @param scrnMsg NSAL0600BMsg
     */
    public static void addCheckAllItemSubmit(NSAL0600BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.svcMemoRsnCd_HS);
        scrnMsg.addCheckItem(scrnMsg.svcCmntTxt_H);
    }

    /**
     * Set table background color.
     * @param scrnMsg NSAL0600BMsg
     */
    private static void setTableBGColor(NSAL0600BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        if (scrnMsg.A.getValidCount() > 0) {
            tblColor.clearRowsBG("A", scrnMsg.A);
            tblColor.setAlternateRowsBG("A", scrnMsg.A);
        } else {
            tblColor.clearRowsBG("A", scrnMsg.A);
        }
    }

    /**
     * Protect items
     * @param items EZDBItem
     */
    private static void disableItems(EZDBItem... items) {
        for (EZDBItem item : items) {
            item.setInputProtected(true);
        }
    }

    /**
     * Protect buttons
     * @param handler EZDCommonHandler
     * @param btnHtmlNm List<String>
     */
    private static void disableButtons(EZDCommonHandler handler, List<String>... btnHtmlNm) {
        for (List<String> btnNm : btnHtmlNm) {
            handler.setButtonEnabled(btnNm.get(0), false);
        }
    }

    private static boolean chkStatus(String chkStsCd, String... stsCds) {
        for (String stsCd : stsCds) {
            if (stsCd.equals(chkStsCd)) {
                return true;
            }
        }
        return false;
    }
}
