/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0080.common;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDCommonHandler;
import parts.servletcommon.gui.EZDGUITableAttribute;
import business.blap.NFDL0080.NFDL0080CMsg;
import business.servlet.NFDL0080.NFDL0080BMsg;
import business.servlet.NFDL0080.NFDL0080Bean;
import business.servlet.NFDL0080.constant.NFDL0080Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUIFocusRule;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableFocusRule;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * NFDL0080CommonLogic.
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/20   Fujitsu         M.Nakamura       Create          N/A
 * 2017/01/04   Fujitsu         H.Ikeda          Update          QC#12865
 * 2018/01/18   Fujitsu         T.Murai          Update          QC#20563
 * 2018/07/24   Hitachi         Y.Takeno         Update          QC#26989-1
 * 2019/07/31   Fujitsu         M.Ishii          Update          QC#52217
 * 2022/03/31   CITS            D.Mamaril        Update          QC#59873
 * 2022/08/10   Hitachi         S.Naya           Update          QC#56154
* </pre>
 */
public class NFDL0080CommonLogic {

    /**
     * setRequestData_NFDL0080_Search
     * @return bizMsg NFDL0080CMsg
     */
    public static NFDL0080CMsg setRequestData_NFDL0080_Search() {

        NFDL0080CMsg bizMsg;

        bizMsg = new NFDL0080CMsg();
        bizMsg.setBusinessID(NFDL0080Constant.BUSINESS_ID);
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * setRequestData_NFDL0080_Update.
     * @return bizMsg NFDL0080CMsg
     */
    public static NFDL0080CMsg setRequestData_NFDL0080_Update() {

        NFDL0080CMsg bizMsg;

        bizMsg = new NFDL0080CMsg();
        bizMsg.setBusinessID(NFDL0080Constant.BUSINESS_ID);
        bizMsg.setFunctionCode("40");

        return bizMsg;
    }

    /**
     * setRequestData_NFDL0080_PageJump.
     * @return bizMsg NFDL0080CMsg
     */
    public static NFDL0080CMsg setRequestData_NFDL0080_PageJump() {

        NFDL0080CMsg bizMsg;

        bizMsg = new NFDL0080CMsg();
        bizMsg.setBusinessID(NFDL0080Constant.BUSINESS_ID);
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * transMsgCheck.
     * @param scrnMsg NFDL0080BMsg
     */
    public static void transMsgCheck(NFDL0080BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.dsAcctNum_H1);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_H1);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNum_H2);
        // 2019/07/31 QC#52217 Mod Start
//        scrnMsg.addCheckItem(scrnMsg.xxTrxCdSrchTxt_H1);
        scrnMsg.addCheckItem(scrnMsg.xxTrxNumSrchTxt_H1);
        // 2019/07/31 QC#52217 Mod End
        // START 2022/08/10 [QC#56154, ADD]
        scrnMsg.addCheckItem(scrnMsg.xxTrxNumSrchTxt_H2);
        // END   2022/08/10 [QC#56154, ADD]
        // START 2018/07/24 [QC#26989-1, ADD]
        scrnMsg.addCheckItem(scrnMsg.custIssPoNum_H2);
        // END   2018/07/24 [QC#26989-1, ADD]
        scrnMsg.A.setCheckParam(new String[] {"xxDealApplyAmtNum_A1"}, 1);
        scrnMsg.addCheckItem(scrnMsg.A);
        scrnMsg.addCheckItem(scrnMsg.xxPageShowFromNum);
        scrnMsg.addCheckItem(scrnMsg.xxPageShowToNum);
        scrnMsg.addCheckItem(scrnMsg.xxPageShowOfNum);
    }

    /**
     * initialize.
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFDL0080BMsg
     */
    public static void initialize(EZDCommonHandler scrnAppli, NFDL0080BMsg scrnMsg) {

        scrnMsg.setInputProtected(false);

        scrnAppli.setButtonProperties("Refresh", "Refresh", "Refresh", 1, null);
        scrnAppli.setButtonProperties("btn1", "", "Save", 1, null);
        // Start 2017/01/04 H.Ikeda [QC#12865,MOD]
        if (hasUpdateFuncId(scrnMsg)) {
            scrnAppli.setButtonProperties("btn2", "CMN_Submit", "Submit", 1, null);
        } else {
            scrnAppli.setButtonProperties("btn2", "CMN_Submit", "Submit", 0, null);
        }
        // End   2017/01/04 H.Ikeda [QC#12865,MOD]
        scrnAppli.setButtonProperties("btn3", "", "Apply", 1, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 1, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 1, null);
        scrnAppli.setButtonProperties("btn6", "CMN_Download", "Download", 1, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 1, null);
        scrnAppli.setButtonProperties("btn8", "CMN_Clear", "Clear", 1, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 1, null);
        scrnAppli.setButtonProperties("btn10", "CMN_Return", "Return", 1, null);

        clearVisibility(scrnMsg);

    }

    /**
     * commonBtnControl.
     * @param scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
    }

    /**
     * checkPagination.
     * @param scrnMsg NFDL0080BMsg
     * @param eventName String
     */
    public static void checkPagination(NFDL0080BMsg scrnMsg, String eventName) {

        scrnMsg.A.setCheckParam(new String[] {"xxDealApplyAmtNum_A1"}, 1);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            scrnMsg.addCheckItem(scrnMsg.A.no(i));
            // Add Start 2018/01/16 S21_NA#20563
            if (!ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A1.getValue())) {
                continue;
            }
            // Add End 2018/01/16 S21_NA#20563

            if (!scrnMsg.A.no(i).xxDealApplyAmtNum_A1.isClear()) {
                if (BigDecimal.ZERO.compareTo(scrnMsg.A.no(i).xxDealApplyAmtNum_A1.getValue()) == 0) {
                    scrnMsg.A.no(i).xxDealApplyAmtNum_A1.setErrorInfo(1, "NFDM0006E");
                } else if (BigDecimal.ZERO.compareTo(scrnMsg.A.no(i).xxDealApplyAmtNum_A1.getValue()) > 0) {
                    scrnMsg.A.no(i).xxDealApplyAmtNum_A1.setErrorInfo(1, "NFDM0016E");
                } else if (BigDecimal.ZERO.compareTo(scrnMsg.A.no(i).dealRmngBalGrsAmt_A1.getValue().subtract(scrnMsg.A.no(i).xxDealApplyAmtNum_A1.getValue())) > 0) {
                    scrnMsg.A.no(i).xxDealApplyAmtNum_A1.setErrorInfo(1, "NFDM0011E");
                // Add Start 2018/01/16 S21_NA#20563
                } else if (BigDecimal.ZERO.compareTo(scrnMsg.A.no(i).dealRmngBalGrsAmt_A1.getValue() //
                        .subtract(scrnMsg.A.no(i).xxDealApplyAmtNum_A1.getValue())
                        .subtract(scrnMsg.A.no(i).dealApplyAdjRsvdAmt_A1.getValue())) > 0) {
                    scrnMsg.A.no(i).xxDealApplyAmtNum_A1.setErrorInfo(1, NFDL0080Constant.NFCM0880E);
                }
                // Add End 2018/01/16 S21_NA#20563
            }
        }
        scrnMsg.putErrorScreen();

    }

    /**
     * @param scrnMsg NFDL0080BMsg
     * @return void
     */
    private static void clearVisibility(NFDL0080BMsg scrnMsg) {

        EZDGUIAttribute xxbtn1 = new EZDGUIAttribute(NFDL0080Constant.SCREEN_ID, "btn1");
        xxbtn1.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn1);

        EZDGUIAttribute xxbtn2 = new EZDGUIAttribute(NFDL0080Constant.SCREEN_ID, "btn2");
        xxbtn2.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn2);

        EZDGUIAttribute xxbtn3 = new EZDGUIAttribute(NFDL0080Constant.SCREEN_ID, "btn3");
        xxbtn3.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn3);

        EZDGUIAttribute xxbtn4 = new EZDGUIAttribute(NFDL0080Constant.SCREEN_ID, "btn4");
        xxbtn4.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn4);

        EZDGUIAttribute xxbtn5 = new EZDGUIAttribute(NFDL0080Constant.SCREEN_ID, "btn5");
        xxbtn5.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn5);

        EZDGUIAttribute xxbtn6 = new EZDGUIAttribute(NFDL0080Constant.SCREEN_ID, "btn6");
        xxbtn6.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn6);

        EZDGUIAttribute xxbtn7 = new EZDGUIAttribute(NFDL0080Constant.SCREEN_ID, "btn7");
        xxbtn7.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn7);

        EZDGUIAttribute xxbtn8 = new EZDGUIAttribute(NFDL0080Constant.SCREEN_ID, "btn8");
        xxbtn8.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn8);

        EZDGUIAttribute xxbtn9 = new EZDGUIAttribute(NFDL0080Constant.SCREEN_ID, "btn9");
        xxbtn9.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn9);

        EZDGUIAttribute xxbtn10 = new EZDGUIAttribute(NFDL0080Constant.SCREEN_ID, "btn10");
        xxbtn10.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn10);
    }

    /**
     * @param scrnMsg NFDL0080BMsg
     */
    public static void setDetailControl(NFDL0080BMsg scrnMsg) {

        setColorControl(scrnMsg);
        setTblFocusRule(scrnMsg);
    }

    /**
     * @param scrnMsg NFDL0080BMsg
     * @param handler EZDCommonHandler
     */
    public static void protectModeNewly(NFDL0080BMsg scrnMsg, EZDCommonHandler handler) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).xxDealApplyAmtNum_A1.setInputProtected(true);
        }

        // Add Detail Line Control
        NFDL0080CommonLogic.protectAddDetailLine(scrnMsg, handler);

    }

    /**
     * @param scrnMsg NFDL0080BMsg
     * @param handler EZDCommonHandler
     */
    public static void protectReturnOnly(NFDL0080BMsg scrnMsg, EZDCommonHandler handler) {

        protectHeader(scrnMsg, handler, true);
        protectDetail(scrnMsg, handler);
        setTblFocusRule(scrnMsg);
    }

    /**
     * @param scrnMsg NFDL0080BMsg
     * @param handler EZDCommonHandler
     */
    public static void protectReturnClearOnly(NFDL0080BMsg scrnMsg, EZDCommonHandler handler) {

        protectHeaderClearOnly(scrnMsg, handler, true);
        protectDetail(scrnMsg, handler);
        setTblFocusRule(scrnMsg);
    }

    /**
     * @param scrnMsg NFDL0080BMsg
     * @param handler EZDCommonHandler
     */
    public static void setButtonConfirmMsgCmnReturn(NFDL0080BMsg scrnMsg, EZDCommonHandler handler) {

        handler.setButtonConfirmMsg("CMN_Return", null, null, 1);
    }

    /**
     * @param scrnMsg NFDL0080BMsg
     * @param handler EZDCommonHandler
     * @param vdDtFlg boolean
     * @return void
     */
    private static void protectHeader(NFDL0080BMsg scrnMsg, EZDCommonHandler handler, boolean vdDtFlg) {

        // START 2022/03/31 D.Mamaril [QC#59873, MOD]
        //scrnMsg.xxChkBox_H1.setInputProtected(true);
        //scrnMsg.dsAcctNum_H2.setInputProtected(true);
        scrnMsg.xxChkBox_H1.setInputProtected(false);
        scrnMsg.dsAcctNum_H2.setInputProtected(false);
        // END 2022/03/31 D.Mamaril [QC#59873, MOD]
        // START 2022/03/31 D.Mamaril [QC#59873, MOD]
        // 2019/07/31 QC#52217 Mod Start
//        scrnMsg.xxTrxCdSrchTxt_H1.setInputProtected(true);
        //scrnMsg.xxTrxNumSrchTxt_H1.setInputProtected(true);
        // 2019/07/31 QC#52217 Mod End
        scrnMsg.xxTrxNumSrchTxt_H1.setInputProtected(false);
        // END 2022/03/31 D.Mamaril [QC#59873, MOD]
        // START 2022/03/31 D.Mamaril [QC#59873, MOD]
        // START 2018/07/24 [QC#26989-1, ADD]
        //scrnMsg.custIssPoNum_H2.setInputProtected(true);
        // END   2018/07/24 [QC#26989-1, ADD]
        scrnMsg.custIssPoNum_H2.setInputProtected(false);
        // END 2022/03/31 D.Mamaril [QC#59873, MOD]

        // Start 2017/01/04 H.Ikeda [QC#12865,MOD]
        if (hasUpdateFuncId(scrnMsg)) {
            if (scrnMsg.A.getValidCount() > 0
                    && scrnMsg.dealRmngBalGrsAmt_H1.getValue().compareTo(BigDecimal.ZERO) != 0) {
                handler.setButtonEnabled("btn2", true);
                handler.setButtonEnabled("btn8", true);
            } else {
                handler.setButtonEnabled("btn2", false);
                handler.setButtonEnabled("btn8", false);
            }
        } else {
            handler.setButtonEnabled("btn2", false);
            if (scrnMsg.A.getValidCount() > 0
                    && scrnMsg.dealRmngBalGrsAmt_H1.getValue().compareTo(BigDecimal.ZERO) != 0) {
                handler.setButtonEnabled("btn8", true);
            } else {
                handler.setButtonEnabled("btn8", false);
            }
        }
        // End   2017/01/04 H.Ikeda [QC#12865,MOD]
        handler.setButtonEnabled("Check_All", false);
        handler.setButtonEnabled("Un_Check_All", false);

    }

    /**
     * @param scrnMsg NFDL0080BMsg
     * @param handler EZDCommonHandler
     * @param vdDtFlg boolean
     * @return void
     */
    private static void protectHeaderClearOnly(NFDL0080BMsg scrnMsg, EZDCommonHandler handler, boolean vdDtFlg) {

        scrnMsg.xxChkBox_H1.setInputProtected(true);
        scrnMsg.dsAcctNum_H2.setInputProtected(true);
        // 2019/07/31 QC#52217 Mod Start
//        scrnMsg.xxTrxCdSrchTxt_H1.setInputProtected(true);
        scrnMsg.xxTrxNumSrchTxt_H1.setInputProtected(true);
        // 2019/07/31 QC#52217 Mod End
        // START 2018/07/24 [QC#26989-1, ADD]
        scrnMsg.custIssPoNum_H2.setInputProtected(true);
        // END   2018/07/24 [QC#26989-1, ADD]

        handler.setButtonEnabled("Refresh", true);

        // Start 2017/01/04 H.Ikeda [QC#12865,MOD]
        if (hasUpdateFuncId(scrnMsg)) {
            if (scrnMsg.A.getValidCount() > 0
                    && scrnMsg.dealRmngBalGrsAmt_H1.getValue().compareTo(BigDecimal.ZERO) != 0) {
                handler.setButtonEnabled("btn2", true);
                handler.setButtonEnabled("btn8", true);
            } else {
                handler.setButtonEnabled("btn2", false);
                handler.setButtonEnabled("btn8", false);
            }
        } else {
            handler.setButtonEnabled("btn2", false);
            if (scrnMsg.A.getValidCount() > 0
                    && scrnMsg.dealRmngBalGrsAmt_H1.getValue().compareTo(BigDecimal.ZERO) != 0) {
                handler.setButtonEnabled("btn8", true);
            } else {
                handler.setButtonEnabled("btn8", false);
            }
        }
        // End   2017/01/04 H.Ikeda [QC#12865,MOD]
    }

    /**
     * @param scrnMsg NFDL0080BMsg
     * @param handler EZDCommonHandler
     * @return void
     */
    private static void protectDetail(NFDL0080BMsg scrnMsg, EZDCommonHandler handler) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxDealApplyAmtNum_A1.setInputProtected(true);
        }
    }

    /**
     * @param scrnMsg NFDL0080BMsg
     * @return void
     */
    private static void setColorControl(NFDL0080BMsg scrnMsg) {

        scrnMsg.clearAllGUIAttribute(NFDL0080Constant.SCREEN_ID);

        EZDGUITableAttribute tblA1 = new EZDGUITableAttribute(NFDL0080Constant.SCREEN_ID, "A1");
        EZDGUITableAttribute tblA2 = new EZDGUITableAttribute(NFDL0080Constant.SCREEN_ID, "A2");

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if ((i + 1) % 2 == 0) {
                tblA1.setRowStyle(i, "pEvenNumberBGcolor");
                tblA2.setRowStyle(i, "pEvenNumberBGcolor");
            } else {
                tblA1.setRowStyle(i, "pOddNumberBGcolor");
                tblA2.setRowStyle(i, "pOddNumberBGcolor");
            }
        }
        scrnMsg.addGUIAttribute(tblA1);
        scrnMsg.addGUIAttribute(tblA2);

    }

    /**
     * @param scrnMsg NFDL0080BMsg
     */
    private static void setTblFocusRule(NFDL0080BMsg scrnMsg) {

        ZYPGUITableFocusRule tblFocusRule = new ZYPGUITableFocusRule(NFDL0080Constant.SCREEN_ID, NFDL0080Bean.A);
        scrnMsg.addGUIAttribute(tblFocusRule);

        // tHead.Right
        ZYPGUIFocusRule fRule = new ZYPGUIFocusRule("tHead.Right TBL - 5");
        fRule.setNextId(NFDL0080Bean.xxChkBox_A1 + "#0");
        tblFocusRule.addRule(fRule);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            fRule = new ZYPGUIFocusRule(NFDL0080Bean.xxChkBox_A1 + "#" + i);
            if (i > 0) {
                fRule.setPrevId(NFDL0080Bean.xxDealApplyAmtNum_A1 + "#" + (i - 1));
            }
            fRule.setNextId(NFDL0080Bean.xxDealApplyAmtNum_A1 + "#" + i);
            tblFocusRule.addRule(fRule);

            fRule = new ZYPGUIFocusRule(NFDL0080Bean.xxDealApplyAmtNum_A1 + "#" + i);
            fRule.setPrevId(NFDL0080Bean.xxChkBox_A1 + "#" + (i));
            tblFocusRule.addRule(fRule);

            fRule = new ZYPGUIFocusRule(NFDL0080Bean.xxDealApplyAmtNum_A1 + "#" + i);
            if (i < scrnMsg.A.getValidCount() - 1) {
                fRule.setNextId(NFDL0080Bean.xxChkBox_A1 + "#" + (i + 1));
            }
            tblFocusRule.addRule(fRule);
        }
    }

    /**
     * @param scrnMsg NFDL0080BMsg
     */
    public static void setAppFracDigit(NFDL0080BMsg scrnMsg) {
        scrnMsg.xxTotAmt_H1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum_H1.getValueInt());
        scrnMsg.dealRmngBalGrsAmt_H1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum_H1.getValueInt());
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).xxDealApplyAmtNum_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum_H1.getValueInt());
        }
    }

    /**
     * @param scrnMsg NFDL0080BMsg
     * @param scrnAppli EZDCommonHandler
     */
    public static void protectAddDetailLine(NFDL0080BMsg scrnMsg, EZDCommonHandler scrnAppli) {

       if (scrnMsg.A.getValidCount() > 0) {
           scrnAppli.setButtonEnabled("Check_All", true);
           scrnAppli.setButtonEnabled("Un_Check_All", true);
           // Start 2017/01/04 H.Ikeda [QC#12865,MOD]
           if (hasUpdateFuncId(scrnMsg)) {
               if (scrnMsg.dealRmngBalGrsAmt_H1.getValue().compareTo(BigDecimal.ZERO) != 0) {
                   scrnAppli.setButtonEnabled("btn2", true);
               } else {
                   scrnAppli.setButtonEnabled("btn2", false);
               }
           } else {
               scrnAppli.setButtonEnabled("btn2", false);
           }
           // End   2017/01/04 H.Ikeda [QC#12865,MOD]
       } else {
           scrnAppli.setButtonEnabled("Check_All", false);
           scrnAppli.setButtonEnabled("Un_Check_All", false);
           scrnAppli.setButtonEnabled("btn2", false);
           scrnAppli.setButtonEnabled("btn8", false);
       }

       for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
           scrnMsg.A.no(i).xxDealApplyAmtNum_A1.setInputProtected(false);
       }
    }

    // Start 2017/01/04 H.Ikeda [QC#12865,ADD]
    /**
     * hasUpdateFuncId
     * @return boolean
     */
    private static boolean hasUpdateFuncId(NFDL0080BMsg scrnMsg) {
        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();

        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(NFDL0080Constant.BUSINESS_ID);
        if (funcList == null || funcList.isEmpty()) {
            scrnMsg.setMessageInfo("ZZSM4300E", new String[] {userProfSvc.getContextUserInfo().getUserId()});
            return false;
        }
        if (funcList.contains("NFDL0080T020")) {
            return true;
        }
        return false;
    }
    // End  2017/01/04 H.Ikeda [QC#12865,ADD]
}
