/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSBL0060.common;

import static business.servlet.NSBL0060.constant.NSBL0060Constant.*;
import parts.common.EZDBItem;
import parts.servletcommon.EZDCommonHandler;
import business.blap.NSBL0060.NSBL0060CMsg;
import business.servlet.NSBL0060.NSBL0060BMsg;
import business.servlet.NSBL0060.NSBL0060Bean;
import business.servlet.NSBL0060.NSBL0060_ABMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUIFocusRule;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableFocusRule;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/20/2013   Hitachi         T.Aoyagi        Create          N/A
 * 10/24/2016   Hitachi         Y.Zhang         Update          QC#13901
 *</pre>
 */
public class NSBL0060CommonLogic {

    /**
     * createCMsgForSearch
     * @return NSBL0060CMsg
     */
    public static NSBL0060CMsg createCMsgForSearch() {
        NSBL0060CMsg bizMsg = new NSBL0060CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        return bizMsg;
    }

    /**
     * Initialize the items and buttons on the screen.
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0060BMsg
     */
    public static void initialize(EZDCommonHandler handler, NSBL0060BMsg scrnMsg) {

        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
        scrnMsg.setInputProtected(false);

        // set button property
        // common button
        handler.setButtonProperties(CLEAR.get(0), CLEAR.get(1), CLEAR.get(2), BTN_INACTIVE, null);
        handler.setButtonProperties(CLOSE.get(0), CLOSE.get(1), CLOSE.get(2), BTN_ACTIVE, null);

        disableItems(scrnMsg.serNum,
                        scrnMsg.custMachCtrlNum,
                        scrnMsg.mdlNm,
                        scrnMsg.shipToCustCd,
                        scrnMsg.locNm,
                        scrnMsg.istlDt,
                        scrnMsg.mtrCnt,
                        scrnMsg.xxTotCnt,
                        scrnMsg.xxDtTm_HD,
                        scrnMsg.orgNm,
                        // START 2016/10/17 Y.Zhang [QC#13901, MOD]
                        scrnMsg.svcByLineBizTpCd,
                        scrnMsg.svcTeamTxt,
                        // END 2016/10/17 Y.Zhang [QC#13901, MOD]
                        scrnMsg.tmZoneCd);
    }

    /**
     * screenControlProcess
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0060BMsg
     */
    public static void screenControlProcess(EZDCommonHandler handler, NSBL0060BMsg scrnMsg) {

        if (!ZYPCommonFunc.hasValue(scrnMsg.svcMachMstrPk)) {
            handler.setButtonEnabled(SEARCH.get(0), false);
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSBL0060_ABMsg aBMsg = scrnMsg.A.no(i);
            disableItems(aBMsg.xxEdtCdNm_ST,
                            aBMsg.xxEdtCdNm_CA,
                            aBMsg.xxEdtCdNm_SY,
                            aBMsg.xxEdtCdNm_PR,
                            aBMsg.xxEdtCdNm_RE,
                            aBMsg.xxEdtCdNm_LO,
                            aBMsg.xxEdtCdNm_CO,
                            aBMsg.xxEdtCdNm_TE,
                            aBMsg.xxEdtCdNm_BI,
                            aBMsg.xxInpAmtNum_PA,
                            aBMsg.xxInpAmtNum_LA,
                            aBMsg.xxInpAmtNum_TR);
        }
    }

    /**
     * set Cursor Control
     * @param scrnMsg NSBL0060BMsg
     */
    public static void setCursorControl(NSBL0060BMsg scrnMsg) {

        ZYPGUITableFocusRule tblFcsRule = new ZYPGUITableFocusRule(SCREEN_ID, NSBL0060Bean.A);
        scrnMsg.addGUIAttribute(tblFcsRule);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            addRuleNext(tblFcsRule, NSBL0060Bean.xxEdtCdNm_ST + "#" + i, NSBL0060Bean.xxEdtCdNm_CA + "#" + i);
            addRulePrev(tblFcsRule, NSBL0060Bean.xxEdtCdNm_ST + "#" + i, NSBL0060Bean.xxEdtCdNm_CA + "#" + i);
            addRuleNext(tblFcsRule, NSBL0060Bean.xxEdtCdNm_CA + "#" + i, NSBL0060Bean.xxEdtCdNm_SY + "#" + i);
            addRulePrev(tblFcsRule, NSBL0060Bean.xxEdtCdNm_CA + "#" + i, NSBL0060Bean.xxEdtCdNm_SY + "#" + i);
            addRuleNext(tblFcsRule, NSBL0060Bean.xxEdtCdNm_SY + "#" + i, NSBL0060Bean.xxEdtCdNm_PR + "#" + i);
            addRulePrev(tblFcsRule, NSBL0060Bean.xxEdtCdNm_SY + "#" + i, NSBL0060Bean.xxEdtCdNm_PR + "#" + i);
            addRuleNext(tblFcsRule, NSBL0060Bean.xxEdtCdNm_PR + "#" + i, NSBL0060Bean.xxEdtCdNm_RE + "#" + i);
            addRulePrev(tblFcsRule, NSBL0060Bean.xxEdtCdNm_PR + "#" + i, NSBL0060Bean.xxEdtCdNm_RE + "#" + i);
            addRuleNext(tblFcsRule, NSBL0060Bean.xxEdtCdNm_RE + "#" + i, NSBL0060Bean.xxEdtCdNm_LO + "#" + i);
            addRulePrev(tblFcsRule, NSBL0060Bean.xxEdtCdNm_RE + "#" + i, NSBL0060Bean.xxEdtCdNm_LO + "#" + i);
            addRuleNext(tblFcsRule, NSBL0060Bean.xxEdtCdNm_LO + "#" + i, NSBL0060Bean.xxEdtCdNm_CO + "#" + i);
            addRulePrev(tblFcsRule, NSBL0060Bean.xxEdtCdNm_LO + "#" + i, NSBL0060Bean.xxEdtCdNm_CO + "#" + i);
            addRuleNext(tblFcsRule, NSBL0060Bean.xxEdtCdNm_CO + "#" + i, NSBL0060Bean.xxEdtCdNm_TE + "#" + i);
            addRulePrev(tblFcsRule, NSBL0060Bean.xxEdtCdNm_CO + "#" + i, NSBL0060Bean.xxEdtCdNm_TE + "#" + i);
            addRuleNext(tblFcsRule, NSBL0060Bean.xxEdtCdNm_TE + "#" + i, NSBL0060Bean.xxEdtCdNm_BI + "#" + i);
            addRulePrev(tblFcsRule, NSBL0060Bean.xxEdtCdNm_TE + "#" + i, NSBL0060Bean.xxEdtCdNm_BI + "#" + i);
            addRuleNext(tblFcsRule, NSBL0060Bean.xxEdtCdNm_BI + "#" + i, NSBL0060Bean.xxInpAmtNum_PA + "#" + i);
            addRulePrev(tblFcsRule, NSBL0060Bean.xxEdtCdNm_BI + "#" + i, NSBL0060Bean.xxInpAmtNum_PA + "#" + i);
            addRuleNext(tblFcsRule, NSBL0060Bean.xxInpAmtNum_PA + "#" + i, NSBL0060Bean.xxInpAmtNum_LA + "#" + i);
            addRulePrev(tblFcsRule, NSBL0060Bean.xxInpAmtNum_PA + "#" + i, NSBL0060Bean.xxInpAmtNum_LA + "#" + i);
            addRuleNext(tblFcsRule, NSBL0060Bean.xxInpAmtNum_LA + "#" + i, NSBL0060Bean.xxInpAmtNum_TR + "#" + i);
            addRulePrev(tblFcsRule, NSBL0060Bean.xxInpAmtNum_LA + "#" + i, NSBL0060Bean.xxInpAmtNum_TR + "#" + i);

            // Not last line.
            if (scrnMsg.A.getValidCount() - 1 > i) {
                addRuleNext(tblFcsRule, NSBL0060Bean.xxInpAmtNum_TR + "#" + i, NSBL0060Bean.xxEdtCdNm_ST + "#" + (i + 1));
            }
            // Not first line.
            if (0 < i) {
                addRulePrev(tblFcsRule, NSBL0060Bean.xxInpAmtNum_TR + "#" + (i - 1), NSBL0060Bean.xxEdtCdNm_ST + "#" + i);
            }
            // Last line.
            if (scrnMsg.A.getValidCount() - 1 == i) {
                addRuleNext(tblFcsRule, NSBL0060Bean.xxInpAmtNum_TR + "#" + i, CLOSE.get(0));
                addRulePrev(tblFcsRule, NSBL0060Bean.xxInpAmtNum_TR + "#" + i, CLOSE.get(0));
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

    private static void disableItems(EZDBItem... items) {
        for (EZDBItem item : items) {
            item.setInputProtected(true);
        }
    }
}
