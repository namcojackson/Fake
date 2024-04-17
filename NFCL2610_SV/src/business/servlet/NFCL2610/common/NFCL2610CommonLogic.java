/**
 * <Pre>Copyright(c)2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFCL2610.common;

import static business.servlet.NFCL2610.constant.NFCL2610Constant.APPLY;
import static business.servlet.NFCL2610.constant.NFCL2610Constant.APPROVE;
import static business.servlet.NFCL2610.constant.NFCL2610Constant.ATTACHMENT;
import static business.servlet.NFCL2610.constant.NFCL2610Constant.BTN_INACTIVE;
import static business.servlet.NFCL2610.constant.NFCL2610Constant.BUSINESS_ID;
import static business.servlet.NFCL2610.constant.NFCL2610Constant.CHECKALL;
import static business.servlet.NFCL2610.constant.NFCL2610Constant.CLEAR;
import static business.servlet.NFCL2610.constant.NFCL2610Constant.DELETE;
import static business.servlet.NFCL2610.constant.NFCL2610Constant.DOWNLOAD;
import static business.servlet.NFCL2610.constant.NFCL2610Constant.ENTRY_SUPPLIER;
import static business.servlet.NFCL2610.constant.NFCL2610Constant.FUNC_ID_T020;
import static business.servlet.NFCL2610.constant.NFCL2610Constant.FUNC_ID_T030;
import static business.servlet.NFCL2610.constant.NFCL2610Constant.NEW_SUPPLIER;
import static business.servlet.NFCL2610.constant.NFCL2610Constant.REJECT;
import static business.servlet.NFCL2610.constant.NFCL2610Constant.RESET;
import static business.servlet.NFCL2610.constant.NFCL2610Constant.RETURN;
import static business.servlet.NFCL2610.constant.NFCL2610Constant.RE_ENTRY;
import static business.servlet.NFCL2610.constant.NFCL2610Constant.SAVE;
import static business.servlet.NFCL2610.constant.NFCL2610Constant.SCREEN_ID;
import static business.servlet.NFCL2610.constant.NFCL2610Constant.SEARCH;
import static business.servlet.NFCL2610.constant.NFCL2610Constant.SEARCH_BILL;
import static business.servlet.NFCL2610.constant.NFCL2610Constant.SEARCH_BILL_LOC;
import static business.servlet.NFCL2610.constant.NFCL2610Constant.SUBMIT;
import static business.servlet.NFCL2610.constant.NFCL2610Constant.UNCHECKALL;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBStringItem;
import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDCommonHandler;
import business.blap.NFCL2610.NFCL2610CMsg;
import business.servlet.NFCL2610.NFCL2610BMsg;
import business.servlet.NFCL2610.NFCL2610_ABMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_DS_WF_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RF_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUIFocusRule;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableFocusRule;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/10   Hitachi         J.Kim           Create          N/A
 * 2016/04/12   Hitachi         K.Kojima        Update          CSA QC#6871
 * 2016/04/19   Hitachi         T.Tsuchida      Update          QC#4758
 * 2016/04/19   Hitachi         T.Tsuchida      Update          QC#7267
 * 2016/04/25   Hitachi         K.Kojima        Update          QC#7532
 * 2016/07/05   Hitachi         J.Kim           Update          QC#9989
 * 2016/09/16   Hitachi         K.Kasai         Update          QC#14308
 * 2017/01/13   Fujitsu         T.Murai         Update          QC#16941
 * 2018/03/02   Hitachi         E.Kameishi      Update          QC#22765
 * 2018/03/12   Fujitsu         H.Ikeda         Update          QC#22762
 * 2018/07/11   Hitachi         Y.Takeno        Update          QC#26989
 * 2018/09/14   Fujitsu         T.Ogura         Update          QC#25091
 * 2020/09/18   CITS            R.Kurahashi     Update          QC#57703
 * 2021/09/09   CITS            G.Delgado       Update          QC#58793
 * 2022/01/26   CITS            D.Mamaril       Update          QC#59618
 * 2022/02/02   Hitachi         A.Kohinata      Update          QC#59612
 * 2022/07/25   Hitachi         A.Kohinata      Update          QC#57417
 * 2022/07/27   Hitachi         A.Kohinata      Update          QC#57418
 * 2023/06/20   Hitachi         S.Fujita        Update          QC#61486
 *</pre>
 */
public class NFCL2610CommonLogic implements ZYPConstant {

    private static S21UserProfileService getUserProfileService() {
        return S21UserProfileServiceFactory.getInstance().getService();
    }

    /**
     * SetArgForSubScreen
     * @param arg Serializable
     * @param scrnMsg NFCL2610BMsg
     */
    public static void setArgForSubScreen(Serializable arg, NFCL2610BMsg scrnMsg) {

        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            if (params[0] != null) {
                if (params[0] instanceof EZDBBigDecimalItem) {
                    EZDBBigDecimalItem param0 = (EZDBBigDecimalItem) params[0];
                    scrnMsg.arRfRqstPk.setValue(param0.getValue());
                } else if (params[0] instanceof String) {
                    scrnMsg.arRfRqstPk.setValue(new BigDecimal((String) params[0]));
                }
            }
            if (params[1] != null) {
                if (params[1] instanceof EZDBStringItem) {
                    EZDBStringItem param1 = (EZDBStringItem) params[1];
                    scrnMsg.billToCustAcctCd.setValue(param1.getValue());
                } else if (params[1] instanceof String) {
                    scrnMsg.billToCustAcctCd.setValue((String) params[1]);
                }
            }
        }
    }

    /**
     * setRequestData_SearchCommon
     * @return bizMsg NFCL2610CMsg
     */
    public static NFCL2610CMsg setRequestData_UpdateCommon() {

        NFCL2610CMsg bizMsg = new NFCL2610CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("40");

        return bizMsg;
    }

    /**
     * setRequestData_SearchCommon
     * @return bizMsg NFCL2610CMsg
     */
    public static NFCL2610CMsg setRequestData_SearchCommon() {

        NFCL2610CMsg bizMsg = new NFCL2610CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * initialize
     * @param handler EZDCommonHandler
     * @param scrnMsg scrnMsg
     */
    public static void initialize(EZDCommonHandler handler, NFCL2610BMsg scrnMsg) {

        screenControlProcess(handler, scrnMsg);
        if (ZYPCommonFunc.hasValue(scrnMsg.arRfRqstPk)) {
            completedScreenControl(handler, scrnMsg, false);
            // add start 2022/07/27 QC#57418
            if (AR_RF_STS.SAVED.equals(scrnMsg.arRfStsCd.getValue())) {
                handler.setButtonEnabled(SUBMIT[0], true);
            }
            // add end 2022/07/27 QC#57418
        }

        setRowColors(scrnMsg);
    }

    /**
     * screenControlProcess
     * @param handler EZDCommonHandler
     * @param scrnMsg NFCL2610BMsg
     */
    public static void screenControlProcess(EZDCommonHandler handler, NFCL2610BMsg scrnMsg) {

        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
        scrnMsg.arRfRqstPk.setInputProtected(true);
        scrnMsg.arRfTpCd_H.setInputProtected(false);
        scrnMsg.xxChkBox_1.setInputProtected(false);
        scrnMsg.xxChkBox_2.setInputProtected(false);
        // START 2018/03/12 H.Ikeda [QC#22762,ADD]
        scrnMsg.xxChkBox_5.setInputProtected(false);
        // END   2018/03/12 H.Ikeda [QC#22762,ADD
        scrnMsg.billToCustAcctCd.setInputProtected(false);
        scrnMsg.billToCustLocCd.setInputProtected(false);
        scrnMsg.dsAcctNm.setInputProtected(true);
        scrnMsg.billToCustNm.setInputProtected(true);
        scrnMsg.xxAllLineAddr.setInputProtected(true);
        scrnMsg.arRcptRfCmntTxt.setInputProtected(false);
        // del start 2022/07/25 QC#57417
        //scrnMsg.arRcptRfRsnDescTxt_H.setInputProtected(false);
        // del end 2022/07/25 QC#57417
        scrnMsg.arRfChkCmntTxt.setInputProtected(false);
        scrnMsg.xxTotRfAmt.setInputProtected(true);
        // START 2018/03/02 E.Kameishi [QC#,ADD]
        scrnMsg.arRfStsDescTxt.setInputProtected(true);
        // END 2018/03/02 E.Kameishi [QC#,ADD]
        // START 2018/07/11 [QC#26989, ADD]
        scrnMsg.xxQueryFltrTxt.setInputProtected(false);
        // END   2018/07/11 [QC#26989, ADD]
        handler.setButtonEnabled(ATTACHMENT[0], false);
        handler.setButtonEnabled(SEARCH_BILL[0], true);
        handler.setButtonEnabled(SEARCH_BILL_LOC[0], true);

        // Set Button Property
        // START 2020/10/28 R.Kurahashi [QC#57732,MOD]
        //List<String[]> cmnEnableButtons = Arrays.asList(SAVE, SUBMIT, APPLY, APPROVE, REJECT, DOWNLOAD, DELETE, CLEAR, RESET, RETURN, SEARCH, ENTRY_SUPPLIER, CHECKALL, UNCHECKALL);
        // mod start 2022/02/02 QC#59612
        //List<String[]> cmnEnableButtons = Arrays.asList(SAVE, SUBMIT, APPLY, APPROVE, REJECT, DOWNLOAD, DELETE, CLEAR, RESET, RETURN, SEARCH, ENTRY_SUPPLIER, NEW_SUPPLIER, CHECKALL, UNCHECKALL);
        List<String[]> cmnEnableButtons = Arrays.asList(SAVE, SUBMIT, APPLY, APPROVE, REJECT, DOWNLOAD, DELETE, CLEAR, RESET, RETURN, SEARCH, RE_ENTRY, ENTRY_SUPPLIER, NEW_SUPPLIER, CHECKALL, UNCHECKALL);
        // mod end 2022/02/02 QC#59612
        // END 2020/10/28 R.Kurahashi [QC#57732,MOD]
        for (String[] button : cmnEnableButtons) {
            handler.setButtonProperties(button[0], button[1], button[2], BTN_INACTIVE, null);
            EZDGUIAttribute xxbtn = new EZDGUIAttribute(SCREEN_ID, button[0]);
            xxbtn.setVisibility(true);
            scrnMsg.addGUIAttribute(xxbtn);
        }

        screenControlByFuncId(handler, scrnMsg);
    }

    /**
     * setListInactive
     * @param scrnMsg NFCL2610BMsg
     */
    public static void setListInactive(NFCL2610BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // START 2018/09/14 T.Ogura [QC#25091,ADD]
            scrnMsg.A.no(i).arCustRefNum_A.setInputProtected(true);
            // END   2018/09/14 T.Ogura [QC#25091,ADD]
            scrnMsg.A.no(i).arTrxNum_A.setInputProtected(true);
            scrnMsg.A.no(i).arTrxTpCd_A.setInputProtected(true);
            // START 2016/07/04 J.Kim [QC#9989,ADD]
            scrnMsg.A.no(i).dsInvTpDescTxt_A.setInputProtected(true);
            // END 2016/07/04 J.Kim [QC#9989,ADD]
            scrnMsg.A.no(i).arTrxDt_A.setInputProtected(true);
            // START 2018/03/12 H.Ikeda [QC#22762,ADD]
            scrnMsg.A.no(i).billToCustAcctCd_A.setInputProtected(true);
            // END   2018/03/12 H.Ikeda [QC#22762,ADD]
            scrnMsg.A.no(i).billToCustCd_A.setInputProtected(true);
            // START 2016/07/04 J.Kim [QC#9989,DEL]
            // scrnMsg.A.no(i).cpoOrdNum_A.setInputProtected(true);
            // scrnMsg.A.no(i).custIssPoNum_A.setInputProtected(true);
            // END 2016/07/04 J.Kim [QC#9989,DEL]
            scrnMsg.A.no(i).dealOrigGrsAmt_A.setInputProtected(true);
            scrnMsg.A.no(i).dealRmngBalGrsAmt_A.setInputProtected(true);
            scrnMsg.A.no(i).arCashApplyStsCd_A.setInputProtected(true);
            scrnMsg.A.no(i).arCashApplyStsDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).funcOrigGrsAmt_A.setInputProtected(true);
            scrnMsg.A.no(i).funcRmngBalGrsAmt_A.setInputProtected(true);
            scrnMsg.A.no(i).xxAllLineAddr_A.setInputProtected(true);
            scrnMsg.A.no(i).arTrxBalPk_A.setInputProtected(true);
            scrnMsg.A.no(i).origInvNum.setInputProtected(true);
        }

        for (int j = 0; j < scrnMsg.B.getValidCount(); j++) {
            scrnMsg.B.no(j).prntVndCd.setInputProtected(true);
            scrnMsg.B.no(j).prntVndPk.setInputProtected(true);
            scrnMsg.B.no(j).prntVndNm.setInputProtected(true);
            scrnMsg.B.no(j).xxScrItem61Txt.setInputProtected(true);
            scrnMsg.B.no(j).xxChkBox_4.setInputProtected(true);
            scrnMsg.B.no(j).xxLocNm_P.setInputProtected(true);
            scrnMsg.B.no(j).firstLineAddr.setInputProtected(true);
            scrnMsg.B.no(j).scdLineAddr.setInputProtected(true);
            scrnMsg.B.no(j).rtlCtrCtyAddr.setInputProtected(true);
            scrnMsg.B.no(j).stCd.setInputProtected(true);
            scrnMsg.B.no(j).postCd.setInputProtected(true);
            scrnMsg.B.no(j).xxToDt.setInputProtected(true);
        }
    }

    /**
     * setFocusRule
     * @param scrnMsg NFCL2610BMsg
     */
    public static void setFocusRule(NFCL2610BMsg scrnMsg) {

        ZYPGUITableFocusRule tblFocusRule = new ZYPGUITableFocusRule(SCREEN_ID, "");
        ZYPGUIFocusRule focusRule = null;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            focusRule = new ZYPGUIFocusRule("arTrxDt_A#" + i);
            focusRule.setNextId("billToCustCd_A#" + i);
            tblFocusRule.addRule(focusRule);

            if ((i + 1) != scrnMsg.A.getValidCount()) {
                focusRule = new ZYPGUIFocusRule("arCashApplyStsNm_A#" + i);
                focusRule.setNextId("xxChkBox_3#" + (i + 1));
                tblFocusRule.addRule(focusRule);
            }
        }

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {

            focusRule = new ZYPGUIFocusRule("prntVndTpCd#" + i);
            focusRule.setNextId("xxChkBox_4#" + i);
            tblFocusRule.addRule(focusRule);

            if ((i + 1) != scrnMsg.B.getValidCount()) {
                focusRule = new ZYPGUIFocusRule("xxToDt#" + i);
                focusRule.setNextId("prntVndCd#" + (i + 1));
                tblFocusRule.addRule(focusRule);
            }
        }
        scrnMsg.addGUIAttribute(tblFocusRule);
    }

    private static void completedScreenControl(EZDCommonHandler handler, NFCL2610BMsg scrnMsg, boolean clearButtonEnabled) {

        scrnMsg.arRfTpCd_H.setInputProtected(true);
        scrnMsg.xxChkBox_1.setInputProtected(true);
        scrnMsg.xxChkBox_2.setInputProtected(true);
        // START 2018/03/12 H.Ikeda [QC#22762,ADD]
        scrnMsg.xxChkBox_5.setInputProtected(true);
        // END   2018/03/12 H.Ikeda [QC#22762,ADD
        scrnMsg.billToCustAcctCd.setInputProtected(true);
        scrnMsg.billToCustLocCd.setInputProtected(true);
        scrnMsg.xxTotRfAmt.setInputProtected(true);
        scrnMsg.arRcptRfCmntTxt.setInputProtected(true);
        scrnMsg.arRfChkCmntTxt.setInputProtected(true);
        // del start 2022/07/25 QC#57417
        //scrnMsg.arRcptRfRsnDescTxt_H.setInputProtected(true);
        // del end 2022/07/25 QC#57417
        scrnMsg.xxRadioBtn.setInputProtected(true);
        // START 2018/03/02 E.Kameishi [QC#,ADD]
        scrnMsg.arRfStsDescTxt.setInputProtected(true);
        // END 2018/03/02 E.Kameishi [QC#,ADD]
        // START 2018/07/11 [QC#26989, ADD]
        scrnMsg.xxQueryFltrTxt.setInputProtected(true);
        // END   2018/07/11 [QC#26989, ADD]
        if (scrnMsg.B.getValidCount() > 0) {
            handler.setButtonEnabled(ENTRY_SUPPLIER[0], false);
            // START 2020/10/28 R.Kurahashi [QC#57732,ADD]
            handler.setButtonEnabled(NEW_SUPPLIER[0], false);
            // END 2020/10/28 R.Kurahashi [QC#57732,ADD]
        }

        if (scrnMsg.A.getValidCount() > 0) {
            handler.setButtonEnabled(CHECKALL[0], false);
            handler.setButtonEnabled(UNCHECKALL[0], false);
            for (int index = 0; index < scrnMsg.A.getValidCount(); index++) {
                scrnMsg.A.no(index).xxChkBox_3.setInputProtected(true);
                // add start 2022/07/25 QC#57417
                scrnMsg.A.no(index).arRcptRfRsnCd_A.setInputProtected(true);
                // add end 2022/07/25 QC#57417
            }
        }
        handler.setButtonEnabled(ATTACHMENT[0], true);
        handler.setButtonEnabled(SEARCH[0], false);
        handler.setButtonEnabled(CLEAR[0], clearButtonEnabled);
        handler.setButtonEnabled(SEARCH_BILL[0], false);
        handler.setButtonEnabled(SEARCH_BILL_LOC[0], false);
        // add start 2022/02/02 QC#59612
        handler.setButtonEnabled(RE_ENTRY[0], false);
        // add end 2022/02/02 QC#59612
    }

    /**
     * submitButtonsInactive
     * @param handler EZDCommonHandler
     * @param scrnMsg NFCL2610BMsg
     */
    public static void submitButtonsInactive(EZDCommonHandler handler, NFCL2610BMsg scrnMsg) {

        handler.setButtonEnabled(SUBMIT[0], false);
        // add start 2022/07/27 QC#57418
        handler.setButtonEnabled(SAVE[0], false);
        // add end 2022/07/27 QC#57418
        completedScreenControl(handler, scrnMsg, true);
        // add start 2022/02/02 QC#59612
        handler.setButtonEnabled(RE_ENTRY[0], true);
        // add end 2022/02/02 QC#59612
    }

    /**
     * @param handler EZDCommonHandler
     * @param scrnMsg NFCL2610BMsg
     */
    public static void submitButtonsActive(EZDCommonHandler handler, NFCL2610BMsg scrnMsg) {

        handler.setButtonEnabled(SUBMIT[0], true);
        // add start 2022/07/27 QC#57418
        handler.setButtonEnabled(SAVE[0], true);
        // add end 2022/07/27 QC#57418
        if (scrnMsg.B.getValidCount() > 0) {
            for (int index = 0; index < scrnMsg.B.getValidCount(); index++) {
                scrnMsg.B.no(index).xxChkBox_4.setInputProtected(true);
            }
        }

        if (scrnMsg.A.getValidCount() > 0) {
            handler.setButtonEnabled(CHECKALL[0], true);
            handler.setButtonEnabled(UNCHECKALL[0], true);
            // START 2020/10/28 R.Kurahashi [QC#57732,MOD]
            //handler.setButtonEnabled(ENTRY_SUPPLIER[0], true);
            if (scrnMsg.B.getValidCount() > 0) {
                handler.setButtonEnabled(ENTRY_SUPPLIER[0], true);
                // add start 2022/07/25 QC#57417
                scrnMsg.xxRadioBtn.setInputProtected(false);
                // add end 2022/07/25 QC#57417
            }
            handler.setButtonEnabled(NEW_SUPPLIER[0], true);
            // END 2020/10/28 R.Kurahashi [QC#57732,MOD]
            for (int index = 0; index < scrnMsg.A.getValidCount(); index++) {
                // START 2020/09/18 R.Kurahashi [QC#57703,MOD]
                //scrnMsg.A.no(index).xxChkBox_3.setInputProtected(false);
                // START 2022/01/26 D.Mamaril [QC#59618, MOD]
                //if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(index).arDsWfStsCd_A) || AR_DS_WF_STS.REJECTED.equals(scrnMsg.A.no(index).arDsWfStsCd_A.getValue())) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(index).arDsWfStsCd_A)
                        || AR_DS_WF_STS.REJECTED.equals(scrnMsg.A.no(index).arDsWfStsCd_A.getValue())
                        || AR_DS_WF_STS.APPROVED.equals(scrnMsg.A.no(index).arDsWfStsCd_A.getValue())) {
                // END 2022/01/26 D.Mamaril [QC#59618, MOD]
                    scrnMsg.A.no(index).xxChkBox_3.setInputProtected(false);
                    // add start 2022/07/25 QC#57417
                    scrnMsg.A.no(index).arRcptRfRsnCd_A.setInputProtected(false);
                    // add end 2022/07/25 QC#57417
                } else {
                    scrnMsg.A.no(index).xxChkBox_3.setInputProtected(true);
                    // add start 2022/07/25 QC#57417
                    scrnMsg.A.no(index).arRcptRfRsnCd_A.setInputProtected(true);
                    // add end 2022/07/25 QC#57417
                }
                // END 2020/09/18 R.Kurahashi [QC#57703,MOD]
            }
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.arRfRqstPk)) {
            handler.setButtonEnabled(ATTACHMENT[0], true);
        }

        scrnMsg.arRfTpCd_H.setInputProtected(true);
        scrnMsg.xxChkBox_1.setInputProtected(true);
        scrnMsg.xxChkBox_2.setInputProtected(true);
        // START 2018/03/12 H.Ikeda [QC#22762,ADD]
        scrnMsg.xxChkBox_5.setInputProtected(true);
        // END   2018/03/12 H.Ikeda [QC#22762,ADD
        scrnMsg.billToCustAcctCd.setInputProtected(true);
        scrnMsg.billToCustLocCd.setInputProtected(true);
        // START 2018/07/11 [QC#26989, ADD]
        scrnMsg.xxQueryFltrTxt.setInputProtected(true);
        // END   2018/07/11 [QC#26989, ADD]
        // START 2016/09/16 [QC#14308, ADD]
        handler.setButtonEnabled(SEARCH[0], false);
        // END 2016/09/16 [QC#14308, ADD]
        handler.setButtonEnabled(SEARCH_BILL[0], false);
        handler.setButtonEnabled(SEARCH_BILL_LOC[0], false);
        // add start 2022/02/02 QC#59612
        handler.setButtonEnabled(RE_ENTRY[0], false);
        // add end 2022/02/02 QC#59612

        setRowColors(scrnMsg);
    }

    /**
     * addCheckItem
     * @param handler EZDCommonHandler
     * @param scrnMsg NFCL2610BMsg
     */
    public static void addCheckItem(EZDCommonHandler handler, NFCL2610BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.arRfRqstPk);
        scrnMsg.addCheckItem(scrnMsg.billToCustAcctCd);
        scrnMsg.addCheckItem(scrnMsg.billToCustLocCd);
        scrnMsg.addCheckItem(scrnMsg.arRcptRfCmntTxt);
        scrnMsg.addCheckItem(scrnMsg.arRfChkCmntTxt);
        // del start 2022/07/25 QC#57417
        //scrnMsg.addCheckItem(scrnMsg.arRcptRfRsnDescTxt_H);
        // del end 2022/07/25 QC#57417
        scrnMsg.addCheckItem(scrnMsg.xxRadioBtn);

        // START 2017/01/13 [QC#16941,ADD]
        for (int i = 0 ; i < scrnMsg.A.getValidCount(); i++) {
            NFCL2610_ABMsg aBMsg = scrnMsg.A.no(i);
            scrnMsg.addCheckItem(aBMsg.xxChkBox_3);
            // add start 2022/07/25 QC#57417
            scrnMsg.addCheckItem(aBMsg.arRcptRfRsnCd_A);
            // add end 2022/07/25 QC#57417
        }
        // END   2017/01/13 [QC#16941,ADD]
    }

    /**
     * initAppFracDigit
     * @param scrnMsg NFCL2610BMsg
     */
    public static void initAppFracDigit(NFCL2610BMsg scrnMsg) {

        scrnMsg.xxTotRfAmt.setAppFracDigit(2);
        for (int index = 0; index < scrnMsg.A.length(); index++) {
            scrnMsg.A.no(index).dealOrigGrsAmt_A.setAppFracDigit(2);
            scrnMsg.A.no(index).dealRmngBalGrsAmt_A.setAppFracDigit(2);
        }
    }

    /**
     * <pre>
     * The pop up parameter is cleared.
     * </pre>
     * @param scrnMsg Screen Msg
     */
    public static void clearPopUpParam(NFCL2610BMsg scrnMsg) {
        scrnMsg.xxPopPrm_P0.clear();
        scrnMsg.xxPopPrm_P1.clear();
        scrnMsg.xxPopPrm_P2.clear();
        scrnMsg.xxPopPrm_P3.clear();
        scrnMsg.xxPopPrm_P4.clear();
        scrnMsg.xxPopPrm_P5.clear();
        scrnMsg.xxPopPrm_P6.clear();
        scrnMsg.xxPopPrm_P7.clear();
        scrnMsg.xxPopPrm_P8.clear();
        scrnMsg.xxPopPrm_P9.clear();
        scrnMsg.xxPopPrm_PA.clear();
        scrnMsg.xxPopPrm_PB.clear();
        scrnMsg.xxPopPrm_PC.clear();
        scrnMsg.xxPopPrm_PD.clear();
        scrnMsg.xxPopPrm_PE.clear();
        scrnMsg.xxPopPrm_PF.clear();
        scrnMsg.xxPopPrm_PG.clear();
        scrnMsg.xxPopPrm_PH.clear();
        scrnMsg.xxPopPrm_PI.clear();
        scrnMsg.xxPopPrm_PJ.clear();
    }

    /**
     * Control items and buttons on the screen by Function ID.
     * @param handler EZDCommonHandler
     * @param scrnMsg NFCL2610BMsg
     * @return
     */
    private static void screenControlByFuncId(EZDCommonHandler handler, NFCL2610BMsg scrnMsg) {

        List<String> funcIdList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_ID);
        if (funcIdList == null || funcIdList.isEmpty()) {
            throw new S21AbendException("You can't operate Machine Master Inquiry Screen(NFCL2610). UserID is -> " + getUserProfileService().getContextUserInfo().getUserId());
        }
        // START 2023/06/20 [QC#61486, MOD]
        // if (funcIdList.contains(FUNC_ID_T020)) {
        if (funcIdList.contains(FUNC_ID_T020) || funcIdList.contains(FUNC_ID_T030)) {
        // END 2023/06/20 [QC#61486, MOD]
            enableButtons(handler, CLEAR[0], RETURN[0], SEARCH[0]);
        } else {
            enableButtons(handler, RETURN[0]);
        }
    }

    private static void setRowColors(NFCL2610BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        if (scrnMsg.A.length() > 0) {
            tblColor.clearRowsBG("A1", scrnMsg.A);
            // START 2016/09/16 [QC#14308, DEL]
            //tblColor.clearRowsBG("A2", scrnMsg.A);
            // END 2016/09/16 [QC#14308, DEL]
            tblColor.setAlternateRowsBG("A1", scrnMsg.A);
            // START 2016/09/16 [QC#14308, DEL]
//            tblColor.setAlternateRowsBG("A2", scrnMsg.A);
            // END 2016/09/16 [QC#14308, DEL]
        }

        if (scrnMsg.B.length() > 0) {
            tblColor.clearRowsBG("B1", scrnMsg.B);
            // START 2016/09/16 [QC#14308, DEL]
//            tblColor.clearRowsBG("B2", scrnMsg.B);
          // END 2016/09/16 [QC#14308, DEL]
            tblColor.setAlternateRowsBG("B1", scrnMsg.B);
            // START 2016/09/16 [QC#14308, DEL]
//            tblColor.setAlternateRowsBG("B2", scrnMsg.B);
        // END 2016/09/16 [QC#14308, DEL]
        }
    }

    /**
     * Activate buttons
     * @param handler EZDCommonHandler
     * @param btnHtmlNm String
     */
    public static void enableButtons(EZDCommonHandler handler, String... btnHtmlNm) {
        for (String btnNm : btnHtmlNm) {
            handler.setButtonEnabled(btnNm, true);
        }
    }

    // START 2021/09/09 G.Delgado [QC#58793, ADD]
    /**
     * Set enabled rows for table A
     * @param handler EZDCommonHandler
     * @param scrnMsg NFCL2610BMsg
     */
    public static void setEnabledRowsTblA(EZDCommonHandler handler, NFCL2610BMsg scrnMsg) {
        // Set if Submit button is enabled
        if (!ZYPCommonFunc.hasValue(handler.getButtonStatus(SUBMIT[0]))) {
            for (int index = 0; index < scrnMsg.A.getValidCount(); index++) {
                // START 2022/01/26 D.Mamaril [QC#59618, MOD]
                //if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(index).arDsWfStsCd_A) || AR_DS_WF_STS.REJECTED.equals(scrnMsg.A.no(index).arDsWfStsCd_A.getValue())) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(index).arDsWfStsCd_A)
                        || AR_DS_WF_STS.REJECTED.equals(scrnMsg.A.no(index).arDsWfStsCd_A.getValue())
                        || AR_DS_WF_STS.APPROVED.equals(scrnMsg.A.no(index).arDsWfStsCd_A.getValue())) {
                // END 2022/01/26 D.Mamaril [QC#59618, MOD]    
                    scrnMsg.A.no(index).xxChkBox_3.setInputProtected(false);
                    // add start 2022/07/25 QC#57417
                    scrnMsg.A.no(index).arRcptRfRsnCd_A.setInputProtected(false);
                    // add end 2022/07/25 QC#57417
                } else {
                    scrnMsg.A.no(index).xxChkBox_3.setInputProtected(true);
                    // add start 2022/07/25 QC#57417
                    scrnMsg.A.no(index).arRcptRfRsnCd_A.setInputProtected(true);
                    // add end 2022/07/25 QC#57417
                }
            }
        }
    }
    // END 2021/09/09 G.Delgado [QC#58793, ADD]

    // add start 2022/07/27 QC#57418
    /**
     * saveButtonsInactive
     * @param handler EZDCommonHandler
     * @param scrnMsg NFCL2610BMsg
     */
    public static void saveButtonsInactive(EZDCommonHandler handler, NFCL2610BMsg scrnMsg) {
        handler.setButtonEnabled(SAVE[0], false);
        completedScreenControl(handler, scrnMsg, true);
    }
    // add end 2022/07/27 QC#57418
}
