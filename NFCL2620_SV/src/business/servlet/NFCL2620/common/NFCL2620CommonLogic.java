/**
 * <Pre>Copyright(c)2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFCL2620.common;

import static business.servlet.NFCL2620.constant.NFCL2620Constant.*;

import java.util.Arrays;
import java.util.List;

import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDCommonHandler;
import business.blap.NFCL2620.NFCL2620CMsg;
import business.servlet.NFCL2620.NFCL2620BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
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
 * 2016/02/23   Hitachi         J.Kim           Create          N/A
 * 2016/04/12   Hitachi         K.Kojima        Update          CSA QC#6871
 * 2016/04/19   Hitachi         T.Tsuchida      Update          QC#4759
 * 2016/08/09   Fijitsu         C.Tanaka        Update          QC#5521
 * 2018/08/09   Hitachi         E.Kameishi      Update          QC#27462
 * 2018/09/14   Fujitsu         T.Ogura         Update          QC#25091
 * 2022/07/20   Hitachi         A.Kohinata      Update          QC#60113
 * 2022/08/01   Hitachi         A.Kohinata      Update          QC#60113-1
 * 2023/06/20   Hitachi         S.Fujita        Update          QC#61486
 *</pre>
 */
public class NFCL2620CommonLogic implements ZYPConstant {

    private static S21UserProfileService getUserProfileService() {
        return S21UserProfileServiceFactory.getInstance().getService();
    }

    /**
     * setRequestData_SearchCommon
     * @return bizMsg NFCL2620CMsg
     */
    public static NFCL2620CMsg setRequestData_SearchCommon() {

        NFCL2620CMsg bizMsg = new NFCL2620CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * initialize
     * @param handler EZDCommonHandler
     * @param scrnMsg scrnMsg
     */
    public static void initialize(EZDCommonHandler handler, NFCL2620BMsg scrnMsg) {
        screenControlProcess(handler, scrnMsg);
    }

    private static void setRowColors(NFCL2620BMsg scrnMsg) {

        // set alternate rows back-ground color
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        // QC#5521 MOD Start
        tblColor.clearRowsBG("A", scrnMsg.A);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
        // QC#5521 MOD End
    }

    /**
     * setListInactive
     * @param handler EZDCommonHandler
     * @param scrnMsg NFCL2620BMsg
     */
    public static void setListInactive(EZDCommonHandler handler, NFCL2620BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).arRfTpDescTxt.setInputProtected(true);
            scrnMsg.A.no(i).billToCustAcctCd.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctNm.setInputProtected(true);
            scrnMsg.A.no(i).arRfStsDescTxt.setInputProtected(true);
            // START 2018/09/14 T.Ogura [QC#25091,ADD]
            scrnMsg.A.no(i).arCustRefNum.setInputProtected(true);
            // END   2018/09/14 T.Ogura [QC#25091,ADD]
            scrnMsg.A.no(i).arTrxNum.setInputProtected(true);
            scrnMsg.A.no(i).dealRfAmt.setInputProtected(true);
            // START 2018/08/09 E.Kameishi [QC#27462,ADD]
            scrnMsg.A.no(i).cratUsrNm.setInputProtected(true);
            scrnMsg.A.no(i).xxWfAsgNm.setInputProtected(true);
            // END 2018/08/091 E.Kameishi [QC#27462,ADD]
            scrnMsg.A.no(i).apVndInvNum.setInputProtected(true);
            scrnMsg.A.no(i).acctInvStsDescTxt.setInputProtected(true);
            scrnMsg.A.no(i).apPmtChkNum.setInputProtected(true);
        }

        if (scrnMsg.A.getValidCount() > 0) {
            setRowColors(scrnMsg);
        }
    }

    /**
     * screenControlProcess
     * @param handler EZDCommonHandler
     * @param scrnMsg NFCL2620BMsg
     */
    public static void screenControlProcess(EZDCommonHandler handler, NFCL2620BMsg scrnMsg) {

        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
        scrnMsg.dsAcctNm_H.setInputProtected(true);
        // add start 2022/07/20 QC#60113
        scrnMsg.dealRfAmt_H.setAppFracDigit(2);
        // add end 2022/07/20 QC#60113

        // Set Button Property
        List<String[]> cmnEnableButtons = Arrays.asList(SAVE, SUBMIT, APPLY, APPROVE, REJECT, DOWNLOAD, DELETE, CLEAR, RESET, RETURN, SEARCH);
        for (String[] button : cmnEnableButtons) {
            handler.setButtonProperties(button[0], button[1], button[2], BTN_INACTIVE, null);
            EZDGUIAttribute xxbtn = new EZDGUIAttribute(SCREEN_ID, button[0]);
            xxbtn.setVisibility(true);
            scrnMsg.addGUIAttribute(xxbtn);
        }

        screenControlByFuncId(handler, scrnMsg);
    }

    /**
     * addCheckItem
     * @param scrnMsg NFCL2620BMsg
     */
    public static void addCheckItem(NFCL2620BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.arRfRqstPk_H);
        scrnMsg.addCheckItem(scrnMsg.arRfTpCd_H);
        scrnMsg.addCheckItem(scrnMsg.billToCustAcctCd_H);
        scrnMsg.addCheckItem(scrnMsg.arRfStsCd_H);
        scrnMsg.addCheckItem(scrnMsg.arRfCratDt_F);
        scrnMsg.addCheckItem(scrnMsg.arRfCratDt_T);
        // add start 2022/07/20 QC#60113
        scrnMsg.addCheckItem(scrnMsg.apPmtChkNum_H);
        // add end 2022/07/20 QC#60113
        // add start 2022/08/01 QC#60113-1
        scrnMsg.addCheckItem(scrnMsg.dealRfAmt_H);
        // add end 2022/08/01 QC#60113-1
    }

    /**
     * initAppFracDigit
     * @param scrnMsg NFCL2620BMsg
     */
    public static void initAppFracDigit(NFCL2620BMsg scrnMsg) {

        for (int index = 0; index < scrnMsg.A.getValidCount(); index++) {
            scrnMsg.A.no(index).dealRfAmt.setAppFracDigit(2);
        }
    }

    /**
     * <pre>
     * The pop up parameter is cleared.
     * </pre>
     * @param scrnMsg Screen Msg
     */
    public static void clearPopUpParam(NFCL2620BMsg scrnMsg) {
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
     * setFocusRule
     * @param scrnMsg NFCL2620BMsg
     */
    public static void setFocusRule(NFCL2620BMsg scrnMsg) {

        ZYPGUITableFocusRule tblFocusRule = new ZYPGUITableFocusRule(SCREEN_ID, "");
        ZYPGUIFocusRule focusRule = null;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            focusRule = new ZYPGUIFocusRule("billToCustAcctCd#" + i);
            focusRule.setNextId("dsAcctNm#" + i);
            tblFocusRule.addRule(focusRule);

            if ((i + 1) != scrnMsg.A.getValidCount()) {
                focusRule = new ZYPGUIFocusRule("apPmtChkNum#" + i);
                focusRule.setNextId("arRfTpNm#" + (i + 1));
                tblFocusRule.addRule(focusRule);
            }
        }
        scrnMsg.addGUIAttribute(tblFocusRule);
    }

    /**
     * Control items and buttons on the screen by Function ID.
     * @param handler EZDCommonHandler
     * @param scrnMsg NFCL2620BMsg
     * @return
     */
    private static void screenControlByFuncId(EZDCommonHandler handler, NFCL2620BMsg scrnMsg) {

        List<String> funcIdList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_ID);
        if (funcIdList == null || funcIdList.isEmpty()) {
            throw new S21AbendException("You can't operate Machine Master Inquiry Screen(NFCL2620). UserID is -> " + getUserProfileService().getContextUserInfo().getUserId());
        }

        // START 2023/06/20 S.Fujita [QC#61486,MOD]
        // if (funcIdList.contains(FUNC_ID_T010)) {
        if (funcIdList.contains(FUNC_ID_T010) || funcIdList.contains(FUNC_ID_T020)) {
        // END 2023/06/20 S.Fujita [QC#61486,MOD]
            enableButtons(handler, CLEAR[0], RETURN[0], SEARCH[0]);
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

}
