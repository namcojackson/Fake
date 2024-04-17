/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSAL0540.common;

import static business.servlet.NSAL0540.constant.NSAL0540Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.util.Arrays;
import java.util.List;

import parts.common.EZDBItem;
import parts.servletcommon.EZDCommonHandler;
import business.blap.NSAL0540.NSAL0540CMsg;
import business.servlet.NSAL0540.NSAL0540BMsg;
import business.servlet.NSAL0540.NSAL0540Bean;
import business.servlet.NSAL0540.NSAL0540_ABMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUIFocusRule;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableFocusRule;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Solution Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/11   Hitachi         T.Aoyagi        Create          N/A
 * 2016/09/20   Hitachi         N.Arai          Update          QC#11616
 *</pre>
 */
public class NSAL0540CommonLogic implements ZYPConstant {

    private static S21UserProfileService getUserProfileService() {
        return S21UserProfileServiceFactory.getInstance().getService();
    }

    /**
     * createCMsgForSearch
     * @return NSAL0540CMsg
     */
    public static NSAL0540CMsg createCMsgForSearch() {
        NSAL0540CMsg bizMsg = new NSAL0540CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        return bizMsg;
    }

    /**
     * createCMsgForUpdate
     * @return NSAL0540CMsg
     */
    public static NSAL0540CMsg createCMsgForUpdate() {
        NSAL0540CMsg bizMsg = new NSAL0540CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("40");
        return bizMsg;
    }

    /**
     * screenControlProcess
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0540BMsg
     */
    public static void screenControlProcess(EZDCommonHandler handler, NSAL0540BMsg scrnMsg) {

        initialize(handler, scrnMsg);
        screenControlByFuncId(handler, scrnMsg);
        screenControlByItemValue(handler, scrnMsg);
        setTableBGColor(scrnMsg);
        setCursorControl(scrnMsg);
    }

    /**
     * Initialize the items and buttons on the screen.
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0540BMsg
     */
    @SuppressWarnings("unchecked")
    private static void initialize(EZDCommonHandler handler, NSAL0540BMsg scrnMsg) {

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
        List<List<String>> optButtons = Arrays.asList(SELECTALL, UNSELECTALL, LINKCONFIG, DELETECONFIG);
        for (List<String> button : optButtons) {
            handler.setButtonProperties(button.get(0), button.get(1), button.get(2), BTN_ACTIVE, null);
        }
    }

    /**
     * Control items and buttons on the screen by Function ID.
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0540BMsg
     */
    @SuppressWarnings("unchecked")
    private static void screenControlByFuncId(EZDCommonHandler handler, NSAL0540BMsg scrnMsg) {

        List<String> funcIdList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_ID);

        if (funcIdList == null || funcIdList.isEmpty()) {
            throw new S21AbendException("You can't operate Solution Maintenance(NSAL0540). UserID is -> " + getUserProfileService().getContextUserInfo().getUserId());
        }

        if (!funcIdList.contains(FUNC_ID_T020)) {
            // This user does not have Submit authority.
            disableButtons(handler, SUBMIT);
        }
    }

    /**
     * Control items and buttons on the screen by Item value.
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0540BMsg
     */
    @SuppressWarnings("unchecked")
    private static void screenControlByItemValue(EZDCommonHandler handler, NSAL0540BMsg scrnMsg) {

        disableItems(scrnMsg.svcSlnSq);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSAL0540_ABMsg lineMsg = scrnMsg.A.no(i);
            disableItems(lineMsg.svcSlnSq_A0
                        , lineMsg.svcConfigMstrPk_A0
                        , lineMsg.serNum_A0
                        , lineMsg.mdseCd_A0
                     // START 2016/09/20 N.Arai [QC#11616, MOD]
                     // , lineMsg.mdseNm_A0
                        , lineMsg.mdseDescShortTxt_A0
                     // END 2016/09/20 N.Arai [QC#11616, MOD]
                        , lineMsg.t_MdlNm_A0
                        , lineMsg.svcMachMstrPk_A0
                        , lineMsg.svcMachMstrPk_AP
                        , lineMsg.istlDt_A0
                        , lineMsg.locNm_A0
                        , lineMsg.locNm_A1
                        , lineMsg.locNm_A2);

            if (!lineMsg.svcMachMstrPk_A0.getValue().equals(lineMsg.svcMachMstrPk_AP.getValue())) {
                disableItems(lineMsg.xxChkBox_A0);
            }
        }

        if (scrnMsg.A.getValidCount() == 0) {
            disableButtons(handler, SELECTALL
                                    , UNSELECTALL
                                    , DELETECONFIG);

            if (!hasValue(scrnMsg.svcSlnSq)) {
                disableButtons(handler, SUBMIT);
            }
        }
        if (scrnMsg.xxPageShowOfNum.getValueInt() == DETAIL_MAX_SIZE) {
            disableButtons(handler, LINKCONFIG);
        }
    }

    /**
     * Set table background color.
     * @param scrnMsg DSBL0260BMsg
     */
    private static void setTableBGColor(NSAL0540BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        if (scrnMsg.A.getValidCount() > 0) {
            tblColor.clearRowsBG("A1", scrnMsg.A);
            tblColor.clearRowsBG("A2", scrnMsg.A);
            tblColor.setAlternateRowsBG("A1", scrnMsg.A);
            tblColor.setAlternateRowsBG("A2", scrnMsg.A);
        } else {
            tblColor.clearRowsBG("A1", scrnMsg.A);
            tblColor.clearRowsBG("A2", scrnMsg.A);
        }
    }

    private static void setCursorControl(NSAL0540BMsg scrnMsg) {

        ZYPGUITableFocusRule tblFcsRule = new ZYPGUITableFocusRule(SCREEN_ID, NSAL0540Bean.A);
        scrnMsg.addGUIAttribute(tblFcsRule);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // Service Model Name
            addRuleNext(tblFcsRule, NSAL0540Bean.t_MdlNm_A0 + "#" + i, NSAL0540Bean.svcMachMstrPk_A0 + "#" + i);
            addRulePrev(tblFcsRule, NSAL0540Bean.t_MdlNm_A0 + "#" + i, NSAL0540Bean.svcMachMstrPk_A0 + "#" + i);

            // IB Bill To
            if ((scrnMsg.A.getValidCount() - 1) > i) {
                // Not last line.
                addRuleNext(tblFcsRule, NSAL0540Bean.locNm_A2 + "#" + i, NSAL0540Bean.xxChkBox_A0 + "#" + (i + 1));
            }
            if (0 < i) {
                // Not First line.
                addRulePrev(tblFcsRule, NSAL0540Bean.locNm_A2 + "#" + (i - 1), NSAL0540Bean.xxChkBox_A0 + "#" + i);
            }
        }
    }

    private static void addRuleNext(ZYPGUITableFocusRule tblFcsRule, String item, String nextItem) {
        ZYPGUIFocusRule fRule = new ZYPGUIFocusRule(item);
        fRule.setNextId(nextItem);
        tblFcsRule.addRule(fRule);
    }

    private static void addRulePrev(ZYPGUITableFocusRule tblFcsRule, String prevItem, String item) {
        ZYPGUIFocusRule fRule = new ZYPGUIFocusRule(item);
        fRule.setPrevId(prevItem);
        tblFcsRule.addRule(fRule);
    }

    /**
     * addCheckAllItem without search criteria.
     * @param scrnMsg NSAL0540BMsg
     */
    public static void addCheckAllItem(NSAL0540BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.svcSlnNm);
        scrnMsg.addCheckItem(scrnMsg.svcCmntTxt);
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
}
