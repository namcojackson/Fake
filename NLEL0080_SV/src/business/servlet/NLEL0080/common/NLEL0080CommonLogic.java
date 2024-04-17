/**
 * <Pre>Copyright(c)2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NLEL0080.common;

import static business.servlet.NLEL0080.constant.NLEL0080Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDCommonHandler;
import business.blap.NLEL0080.NLEL0080CMsg;
import business.servlet.NLEL0080.NLEL0080BMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASSET_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUIFocusRule;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableFocusRule;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/25   Hitachi         J.Kim           Create          N/A
 * 2016/04/08   Hitachi         T.Tsuchida      Update          QC#6769
 * 2016/05/10   Hitachi         K.Kojima        Update          QC#7065
 * 2016/05/20   Hitachi         T.Tsuchida      Update          QC#8096
 * 2016/07/20   Hitachi         T.Tsuchida      Update          QC#8092
 * 2016/08/02   Hitachi         K.Kojima        Update          QC#12766
 * 2016/08/09   Fujitsu         C.Tanaka        Update          QC#13195
 * 2016/09/15   Hitachi         J.Kim           Update          QC#10360
 * 2016/11/29   Hitachi         Y.Tsuchimoto    Update          QC#16268
 *</pre>
 */
public class NLEL0080CommonLogic implements ZYPConstant {

    private static S21UserProfileService getUserProfileService() {
        return S21UserProfileServiceFactory.getInstance().getService();
    }

    /**
     * setRequestData_SearchCommon
     * @return bizMsg NLEL0080CMsg
     */
    public static NLEL0080CMsg setRequestData_SearchCommon() {

        NLEL0080CMsg bizMsg = new NLEL0080CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * setRequestData_SubmitCommon
     * @return bizMsg NLEL0080CMsg
     */
    public static NLEL0080CMsg setRequestData_SubmitCommon() {

        NLEL0080CMsg bizMsg = new NLEL0080CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("40");

        return bizMsg;
    }

    /**
     * initialize
     * @param handler EZDCommonHandler handler
     * @param scrnMsg scrnMsg scrnMsg
     */
    public static void initialize(EZDCommonHandler handler, NLEL0080BMsg scrnMsg) {
        screenControlProcess(handler, scrnMsg);
        screenControlByFuncId(handler, scrnMsg);
        setListActive(handler, scrnMsg);
    }

    /**
     * initialize Add
     * @param handler EZDCommonHandler
     * @param scrnMsg scrnMsg
     */
    public static void initializeAdd(EZDCommonHandler handler, NLEL0080BMsg scrnMsg) {

        screenControlProcess(handler, scrnMsg);
        enableButtons(handler, SCRN_DELETE[0], SUBMIT[0], RESET[0], CLEAR[0], ADD[0], RETURN[0]);

        if (scrnMsg.A.getValidCount() > 0) {
            setRowColors(scrnMsg);
        }
    }

    /**
     * initialize Clear
     * @param handler EZDCommonHandler handler
     * @param scrnMsg scrnMsg scrnMsg
     */
    public static void initializeClear(EZDCommonHandler handler, NLEL0080BMsg scrnMsg) {
        screenControlProcess(handler, scrnMsg);
        screenControlByFuncId(handler, scrnMsg);
        setListActive(handler, scrnMsg);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrDply, ASSET_SRC_TP.LEASED);
    }

    /**
     * processAdd
     * @param handler EZDCommonHandler
     * @param scrnMsg NLEL0080BMsg
     */
    public static void processAdd(EZDCommonHandler handler, NLEL0080BMsg scrnMsg) {

        initializeAdd(handler, scrnMsg);
        scrnMsg.A.setValidCount(scrnMsg.A.getValidCount() + 1);

        if (scrnMsg.A.getValidCount() == ADD_LIMIT_COUNT) {
            handler.setButtonEnabled(ADD[0], false);
        }

        setListActive(handler, scrnMsg);
        initAppFracDigit(scrnMsg);
        setFocusRule(scrnMsg);
    }

    /**
     * processDelete
     * @param handler EZDCommonHandler
     * @param scrnMsg NLEL0080BMsg
     */
    public static void processDelete(EZDCommonHandler handler, NLEL0080BMsg scrnMsg) {

        if (scrnMsg.A.getValidCount() < ADD_LIMIT_COUNT) {
            handler.setButtonEnabled(ADD[0], true);
        }

        // QC#13195 ADD Start
        if (scrnMsg.A.getValidCount() == 0) {
            handler.setButtonEnabled(SUBMIT[0], false);
        }
        // QC#13195 ADD End

        setListActive(handler, scrnMsg);
        initAppFracDigit(scrnMsg);
        setFocusRule(scrnMsg);
    }

    private static void setRowColors(NLEL0080BMsg scrnMsg) {

        // set alternate rows back-ground color
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG("A1", scrnMsg.A);
        tblColor.clearRowsBG("A2", scrnMsg.A);
        tblColor.setAlternateRowsBG("A1", scrnMsg.A);
        tblColor.setAlternateRowsBG("A2", scrnMsg.A);
    }

    /**
     * setListActive
     * @param handler EZDCommonHandler
     * @param scrnMsg NLEL0080BMsg
     */
    public static void setListActive(EZDCommonHandler handler, NLEL0080BMsg scrnMsg) {

        if (ASSET_SRC_TP.PROCURED.equals(scrnMsg.xxScrDply.getValue())) {
            setListActivePurchase(handler, scrnMsg);
        } else {
            setListActiveLease(handler, scrnMsg);
        }
    }

    /**
     * setListActive
     * @param handler EZDCommonHandler
     * @param scrnMsg NLEL0080BMsg
     */
    public static void setListActiveLease(EZDCommonHandler handler, NLEL0080BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox.setInputProtected(false);
            // START 2016/09/14 J.Kim [QC#10360,MOD]
            // scrnMsg.A.no(i).dsAssetMstrPk.setInputProtected(false);
            scrnMsg.A.no(i).dsAssetMstrPk.setInputProtected(true);
            // END 2016/09/14 J.Kim [QC#10360,MOD] 
            scrnMsg.A.no(i).dsAssetDescTxt.setInputProtected(false);
            scrnMsg.A.no(i).totAssetQty.setInputProtected(false);
            scrnMsg.A.no(i).depcMthNum.setInputProtected(false);
            scrnMsg.A.no(i).serNum.setInputProtected(false);
            scrnMsg.A.no(i).curValAmt.setInputProtected(false);
            scrnMsg.A.no(i).prntDsAssetMstrPk.setInputProtected(false);
            scrnMsg.A.no(i).xxScrItem81Txt.setInputProtected(true);
            scrnMsg.A.no(i).slsRepTocCd.setInputProtected(false);
            scrnMsg.A.no(i).cpoOrdNum.setInputProtected(false);
            scrnMsg.A.no(i).cpoDplyLineNum.setInputProtected(false);
            // START 2018/06/25 J.Kim [QC#24844,MOD] 
            // scrnMsg.A.no(i).assetLeaseNum.setInputProtected(false);
            // scrnMsg.A.no(i).leaseStartDt.setInputProtected(false);
            // scrnMsg.A.no(i).leaseEndDt.setInputProtected(false);
            scrnMsg.A.no(i).dsContrNum.setInputProtected(false);
            scrnMsg.A.no(i).contrEffFromDt.setInputProtected(false);
            scrnMsg.A.no(i).contrEffThruDt.setInputProtected(false);
            // END 2016/09/14 J.Kim [QC#24844,MOD] 
            // START 2016/09/14 J.Kim [QC#10360,ADD]
            scrnMsg.A.no(i).bllgInvNum.setInputProtected(false);
            scrnMsg.A.no(i).lastBillDt.setInputProtected(false);
            // START 2018/06/25 J.Kim [QC#24844,MOD] 
            // scrnMsg.A.no(i).poOrdNum.setInputProtected(false);
            scrnMsg.A.no(i).custIssPoNum.setInputProtected(false);
            // END 2018/06/25 J.Kim [QC#24844,MOD] 
            scrnMsg.A.no(i).invNum.setInputProtected(false);
            scrnMsg.A.no(i).invDt.setInputProtected(false);
            scrnMsg.A.no(i).vndTpDescTxt.setInputProtected(false);
            // END 2016/09/14 J.Kim [QC#10360,ADD]
            // START 2016/09/14 J.Kim [QC#10360,ADD]
            // scrnMsg.A.no(i).sellToCustCd.setInputProtected(false);
            // scrnMsg.A.no(i).firstLineAddr.setInputProtected(false);
            // scrnMsg.A.no(i).ctyAddr.setInputProtected(false);
            // scrnMsg.A.no(i).stCd.setInputProtected(false);
            // scrnMsg.A.no(i).postCd.setInputProtected(false);
            scrnMsg.A.no(i).sellToCustCd.setInputProtected(true);
            scrnMsg.A.no(i).firstLineAddr.setInputProtected(true);
            scrnMsg.A.no(i).ctyAddr.setInputProtected(true);
            scrnMsg.A.no(i).stCd.setInputProtected(true);
            scrnMsg.A.no(i).postCd.setInputProtected(true);
            // END 2016/09/14 J.Kim [QC#10360,ADD]
    
            // C# Button
            // START 2016/09/14 J.Kim [QC#10360,ADD]
            // handler.setButtonEnabled("OpenWin_Customer", i, true);
            handler.setButtonEnabled("OpenWin_Customer", i, false);
            // END 2016/09/14 J.Kim [QC#10360,ADD]
            // GL# Button
            handler.setButtonEnabled("OpenWin_GL", i, true);
            // SR# Button
            handler.setButtonEnabled("OpenWin_SR", i, true);
            // START 2016/09/14 J.Kim [QC#10360,ADD]
            // V#
            handler.setButtonEnabled("OpenWin_V", i, true);
            // END 2016/09/14 J.Kim [QC#10360,ADD]
        }
    }

    // START 2016/09/14 J.Kim [QC#10360,ADD]
    /**
     * setListActivePurchase
     * @param handler EZDCommonHandler
     * @param scrnMsg NLEL0080BMsg
     */
    public static void setListActivePurchase(EZDCommonHandler handler, NLEL0080BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox.setInputProtected(false);
            scrnMsg.A.no(i).dsAssetMstrPk.setInputProtected(true);
            scrnMsg.A.no(i).dsAssetDescTxt.setInputProtected(false);
            scrnMsg.A.no(i).totAssetQty.setInputProtected(false);
            scrnMsg.A.no(i).depcMthNum.setInputProtected(false);
            scrnMsg.A.no(i).serNum.setInputProtected(false);
            scrnMsg.A.no(i).curValAmt.setInputProtected(false);
            scrnMsg.A.no(i).prntDsAssetMstrPk.setInputProtected(false);
            scrnMsg.A.no(i).xxScrItem81Txt.setInputProtected(true);
            scrnMsg.A.no(i).slsRepTocCd.setInputProtected(false);
            scrnMsg.A.no(i).cpoOrdNum.setInputProtected(false);
            scrnMsg.A.no(i).cpoDplyLineNum.setInputProtected(false);
            // START 2018/06/25 J.Kim [QC#24844,MOD]
            // scrnMsg.A.no(i).assetLeaseNum.setInputProtected(false);
            // scrnMsg.A.no(i).leaseStartDt.setInputProtected(false);
            // scrnMsg.A.no(i).leaseEndDt.setInputProtected(false);
            scrnMsg.A.no(i).dsContrNum.setInputProtected(false);
            scrnMsg.A.no(i).contrEffFromDt.setInputProtected(false);
            scrnMsg.A.no(i).contrEffThruDt.setInputProtected(false);
            // END 2018/06/25 J.Kim [QC#24844,MOD]
            scrnMsg.A.no(i).bllgInvNum.setInputProtected(false);
            scrnMsg.A.no(i).lastBillDt.setInputProtected(false);
            // START 2018/06/25 J.Kim [QC#24844,MOD]
            // scrnMsg.A.no(i).poOrdNum.setInputProtected(false);
            scrnMsg.A.no(i).custIssPoNum.setInputProtected(false);
            // END 2018/06/25 J.Kim [QC#24844,MOD]
            scrnMsg.A.no(i).invNum.setInputProtected(false);
            scrnMsg.A.no(i).invDt.setInputProtected(false);
            scrnMsg.A.no(i).vndTpDescTxt.setInputProtected(false);
            scrnMsg.A.no(i).sellToCustCd.setInputProtected(false);
            scrnMsg.A.no(i).firstLineAddr.setInputProtected(false);
            scrnMsg.A.no(i).ctyAddr.setInputProtected(false);
            scrnMsg.A.no(i).stCd.setInputProtected(false);
            scrnMsg.A.no(i).postCd.setInputProtected(false);

            // C# Button
            handler.setButtonEnabled("OpenWin_Customer", i, true);
            // GL# Button
            handler.setButtonEnabled("OpenWin_GL", i, true);
            // SR# Button
            handler.setButtonEnabled("OpenWin_SR", i, true);
            // V#
            handler.setButtonEnabled("OpenWin_V", i, true);
        }
    }
    // END 2016/09/14 J.Kim [QC#10360,ADD]

    /**
     * setListInactive
     * @param handler EZDCommonHandler
     * @param scrnMsg NLEL0080BMsg
     */
    public static void setListInactive(EZDCommonHandler handler, NLEL0080BMsg scrnMsg) {

        handler.setButtonEnabled(ADD[0], false);
        handler.setButtonEnabled(SUBMIT[0], false);
        handler.setButtonEnabled(SCRN_DELETE[0], false);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox.setInputProtected(true);
            scrnMsg.A.no(i).dsAssetMstrPk.setInputProtected(true);
            scrnMsg.A.no(i).dsAssetDescTxt.setInputProtected(true);
            scrnMsg.A.no(i).totAssetQty.setInputProtected(true);
            scrnMsg.A.no(i).depcMthNum.setInputProtected(true);
            scrnMsg.A.no(i).serNum.setInputProtected(true);
            scrnMsg.A.no(i).curValAmt.setInputProtected(true);
            scrnMsg.A.no(i).prntDsAssetMstrPk.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem81Txt.setInputProtected(true);
            scrnMsg.A.no(i).slsRepTocCd.setInputProtected(true);
            scrnMsg.A.no(i).cpoOrdNum.setInputProtected(true);
            scrnMsg.A.no(i).cpoDplyLineNum.setInputProtected(true);
            // START 2018/06/25 J.Kim [QC#24844,MOD]
            // scrnMsg.A.no(i).assetLeaseNum.setInputProtected(true);
            // scrnMsg.A.no(i).leaseStartDt.setInputProtected(true);
            // scrnMsg.A.no(i).leaseEndDt.setInputProtected(true);
            scrnMsg.A.no(i).dsContrNum.setInputProtected(true);
            scrnMsg.A.no(i).contrEffFromDt.setInputProtected(true);
            scrnMsg.A.no(i).contrEffThruDt.setInputProtected(true);
            // END 2018/06/25 J.Kim [QC#24844,MOD]
            scrnMsg.A.no(i).sellToCustCd.setInputProtected(true);
            scrnMsg.A.no(i).firstLineAddr.setInputProtected(true);
            scrnMsg.A.no(i).ctyAddr.setInputProtected(true);
            scrnMsg.A.no(i).stCd.setInputProtected(true);
            scrnMsg.A.no(i).postCd.setInputProtected(true);
            // START 2016/09/14 J.Kim [QC#10360,ADD]
            scrnMsg.A.no(i).bllgInvNum.setInputProtected(true);
            scrnMsg.A.no(i).lastBillDt.setInputProtected(true);
            // START 2018/06/25 J.Kim [QC#24844,MOD]
            // scrnMsg.A.no(i).poOrdNum.setInputProtected(true);
            scrnMsg.A.no(i).custIssPoNum.setInputProtected(true);
            // END 2018/06/25 J.Kim [QC#24844,MOD]
            scrnMsg.A.no(i).invNum.setInputProtected(true);
            scrnMsg.A.no(i).invDt.setInputProtected(true);
            scrnMsg.A.no(i).vndTpDescTxt.setInputProtected(true);

            // V#
            handler.setButtonEnabled("OpenWin_V", i, false);
            // END 2016/09/14 J.Kim [QC#10360,ADD]
            // C# Button
            handler.setButtonEnabled("OpenWin_Customer", i, false);
            // GL# Button
            handler.setButtonEnabled("OpenWin_GL", i, false);
            // SR# Button
            handler.setButtonEnabled("OpenWin_SR", i, false);
        }

        if (scrnMsg.A.getValidCount() > 0) {
            setRowColors(scrnMsg);
        }
    }

    /**
     * screenControlProcess
     * @param handler EZDCommonHandler
     * @param scrnMsg NLEL0080BMsg
     */
    public static void screenControlProcess(EZDCommonHandler handler, NLEL0080BMsg scrnMsg) {

        scrnMsg.clearAllGUIAttribute(SCREEN_ID);

        // Set Button Property
        List<String[]> cmnEnableButtons = Arrays.asList(SAVE, SUBMIT, APPLY, APPROVE, REJECT, DOWNLOAD, DELETE, CLEAR, RESET, RETURN, SCRN_DELETE, ADD);
        for (String[] button : cmnEnableButtons) {
            handler.setButtonProperties(button[0], button[1], button[2], BTN_INACTIVE, null);
            EZDGUIAttribute xxbtn = new EZDGUIAttribute(SCREEN_ID, button[0]);
            xxbtn.setVisibility(true);
            scrnMsg.addGUIAttribute(xxbtn);
        }
    }

    /**
     * addCheckItem
     * @param scrnMsg NLEL0080BMsg
     */
    public static void addCheckItem(NLEL0080BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.assetTpNm_S);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).dsAssetMstrPk);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).dsAssetDescTxt);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).totAssetQty);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).depcMthNum);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).serNum);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).curValAmt);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prntDsAssetMstrPk);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxScrItem81Txt);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).slsRepTocCd);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).cpoOrdNum);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).cpoDplyLineNum);
            // START 2018/06/25 J.Kim [QC#24844,MOD]
            // scrnMsg.addCheckItem(scrnMsg.A.no(i).assetLeaseNum);
            // scrnMsg.addCheckItem(scrnMsg.A.no(i).leaseStartDt);
            // scrnMsg.addCheckItem(scrnMsg.A.no(i).leaseEndDt);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).dsContrNum);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).contrEffFromDt);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).contrEffThruDt);
            // END 2018/06/25 J.Kim [QC#24844,MOD]
            scrnMsg.addCheckItem(scrnMsg.A.no(i).sellToCustCd);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).firstLineAddr);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).ctyAddr);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).stCd);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).postCd);
            // START 2016/09/15 J.Kim [QC#10360,ADD]
            scrnMsg.addCheckItem(scrnMsg.A.no(i).bllgInvNum);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).lastBillDt);
            // START 2018/06/25 J.Kim [QC#24844,MOD]
            // scrnMsg.addCheckItem(scrnMsg.A.no(i).poOrdNum);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).custIssPoNum);
            // END 2018/06/25 J.Kim [QC#24844,MOD]
            scrnMsg.addCheckItem(scrnMsg.A.no(i).invNum);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).invDt);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).vndTpDescTxt);
            // END 2016/09/15 J.Kim [QC#10360,ADD]

            // START 2016/05/10 K.Kojima [QC#7065,DEL]
            // // START 2016/05/09 C.Tanaka [QC#7065, ADD]
            // if
            // (!ZYPCommonFunc.isNumberCheck(scrnMsg.A.no(i).depcMthNum.getValue()))
            // {
            // scrnMsg.A.no(i).depcMthNum.setErrorInfo(1, ZZM9004E,
            // new String[] {LIFE_IN_MTH });
            // } else {
            // BigDecimal depcMthMun = new
            // BigDecimal(scrnMsg.A.no(i).depcMthNum.getValue());
            // // START 2016/05/10 K.Kojima [QC#7065,MOD]
            // // if (depcMthMun.compareTo(BigDecimal.ZERO) < 0) {
            // if (depcMthMun.compareTo(BigDecimal.ZERO) <= 0) {
            // // END 2016/05/10 K.Kojima [QC#7065,MOD]
            // scrnMsg.A.no(i).depcMthNum.setErrorInfo(1, ZZM9004E,
            // new String[] {LIFE_IN_MTH });
            // }
            // }
            // // END 2016/05/09 C.Tanaka [QC#7065, ADD]
            // END 2016/05/10 K.Kojima [QC#7065,DEL]
        }
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NLEL0080BMsg
     * @param i int
     * @return Object[]
     */
    public static Object[] setParamForOrganizationSearchPopup(NLEL0080BMsg scrnMsg, int i) {

        clearPopUpParam(scrnMsg);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, BUSINESS_ID);
        Object[] params = new Object[PRM_NMAL2550];
        int index = 0;
        params[index++] = scrnMsg.xxPopPrm_P0;
        params[index++] = scrnMsg.xxPopPrm_P1;
        params[index++] = scrnMsg.xxPopPrm_P2;
        params[index++] = scrnMsg.xxPopPrm_P3;
        params[index++] = scrnMsg.xxPopPrm_P4;
        params[index++] = scrnMsg.xxPopPrm_P5;
        params[index++] = scrnMsg.xxPopPrm_P6;
        params[index++] = scrnMsg.xxPopPrm_P7;
        params[index++] = scrnMsg.xxPopPrm_P8;
        params[index++] = scrnMsg.xxPopPrm_P9;

        return params;
    }

    /**
     * setParamNMAL2550
     * @param scrnMsg scrnMsg NLEL0080BMsg
     * @param index int
     */
    public static void setParamNMAL2550(NLEL0080BMsg scrnMsg, int index) {

        StringBuffer sb = new StringBuffer();
        sb.append(scrnMsg.xxPopPrm_P1.getValue());
        // START 2016/08/02 K.Kojima [QC#12766,DEL]
        // sb.append(STR_CNM);
        // sb.append(scrnMsg.xxPopPrm_P2.getValue());
        // END 2016/08/02 K.Kojima [QC#12766,DEL]
        sb.append(STR_CNM);
        sb.append(scrnMsg.xxPopPrm_P3.getValue());
        sb.append(STR_CNM);
        sb.append(scrnMsg.xxPopPrm_P4.getValue());
        sb.append(STR_CNM);
        sb.append(scrnMsg.xxPopPrm_P5.getValue());
        sb.append(STR_CNM);
        sb.append(scrnMsg.xxPopPrm_P6.getValue());
        sb.append(STR_CNM);
        sb.append(scrnMsg.xxPopPrm_P7.getValue());
        // START 2016/08/02 K.Kojima [QC#12766,ADD]
        sb.append(STR_CNM);
        sb.append(scrnMsg.xxPopPrm_P2.getValue());
        // END 2016/08/02 K.Kojima [QC#12766,ADD]
        sb.append(STR_CNM);
        sb.append(scrnMsg.xxPopPrm_P8.getValue());
        sb.append(STR_CNM);
        sb.append(scrnMsg.xxPopPrm_P9.getValue());

        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).xxScrItem81Txt, sb.toString());
        scrnMsg.setFocusItem(scrnMsg.A.no(index).xxScrItem81Txt);
    }


    /**
     * setParamNWAL1130
     * @param scrnMsg scrnMsg NLEL0080BMsg
     * @param index int
     */
    public static Object[] setParamNWAL1130(NLEL0080BMsg scrnMsg, int index) {

        String salesDate = ZYPDateUtil.getSalesDate();
        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Vender Search";

        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT");
        sb.append("    V.EZCANCELFLAG,");
        sb.append("    V.GLBL_CMPY_CD,");
        sb.append("    V.VND_CD,");
        sb.append("    V.LOC_NM,");
        sb.append("    V.INAC_DT,");
        sb.append("    PV.PRNT_VND_CD,");
        sb.append("    PV.PRNT_VND_NM,");
        sb.append("    PV.PRNT_VND_TP_CD");
        sb.append(" FROM");
        sb.append("    PRNT_VND PV,");
        sb.append("    VND      V,");
        sb.append("    VND_TP_RELN VTR");
        sb.append(" WHERE 1 = 1");
        sb.append("  AND V.GLBL_CMPY_CD = '").append(scrnMsg.glblCmpyCd.getValue()).append("' ");
        sb.append("  AND V.EZCANCELFLAG = '0'");
        sb.append("  AND V.RGTN_STS_CD  = '");
        sb.append(RGTN_STS.READY_FOR_ORDER_TAKING);
        sb.append("'");
        sb.append("  AND V.EFF_THRU_DT  >= '").append(salesDate).append("'");
        sb.append("  AND V.VND_CD        = VTR.VND_CD");
        sb.append("  AND V.GLBL_CMPY_CD  = VTR.GLBL_CMPY_CD");
        sb.append("  AND V.EZCANCELFLAG  = VTR.EZCANCELFLAG");
        sb.append("  AND VTR.VND_TP_CD   = '");
        sb.append(VND_TP.SUPPLIER);
        sb.append("'");
        sb.append("  AND V.GLBL_CMPY_CD = PV.GLBL_CMPY_CD");
        sb.append("  AND V.EZCANCELFLAG = PV.EZCANCELFLAG");
        sb.append("  AND V.PRNT_VND_PK  = PV.PRNT_VND_PK");
        sb.append("  AND (PV.INAC_DT IS NULL OR PV.INAC_DT > '").append(salesDate).append("')");
        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Vendor Number";
        whereArray1[IDX_1] = "PRNT_VND_CD";
        whereArray1[IDX_2] = "";
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = "Vendor Name";
        whereArray2[IDX_1] = "PRNT_VND_NM";
        whereArray2[IDX_2] = scrnMsg.A.no(index).vndTpDescTxt.getValue();
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] whereArray3 = new Object[IDX_4];
        whereArray3[IDX_0] = "Location Number";
        whereArray3[IDX_1] = "VND_CD";
        whereArray3[IDX_2] = "";
        whereArray3[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] whereArray4 = new Object[IDX_4];
        whereArray4[IDX_0] = "Location Name";
        whereArray4[IDX_1] = "LOC_NM";
        whereArray4[IDX_2] = "";
        whereArray4[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);
        whereList.add(whereArray2);
        whereList.add(whereArray3);
        whereList.add(whereArray4);
        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Vendor Number";
        columnArray1[IDX_1] = "PRNT_VND_CD";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "Vendor Name";
        columnArray2[IDX_1] = "PRNT_VND_NM";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] columnArray3 = new Object[IDX_4];
        columnArray3[IDX_0] = "Location Number";
        columnArray3[IDX_1] = "VND_CD";
        columnArray3[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray3[IDX_3] = ZYPConstant.FLG_OFF_N;
        Object[] columnArray4 = new Object[IDX_4];
        columnArray4[IDX_0] = "Location Name";
        columnArray4[IDX_1] = "LOC_NM";
        columnArray4[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray4[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);
        columnList.add(columnArray2);
        columnList.add(columnArray3);
        columnList.add(columnArray4);
        params[IDX_4] = columnList;

        List<Object[]> sortList = new ArrayList<Object[]>();
        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "PRNT_VND_CD";
        sortConditionArray1[IDX_1] = "ASC";
        Object[] sortConditionArray2 = new Object[IDX_2];
        sortConditionArray2[IDX_0] = "VND_CD";
        sortConditionArray2[IDX_1] = "ASC";
        sortList.add(sortConditionArray1);
        sortList.add(sortConditionArray2);
        params[IDX_5] = sortList;
        ZYPTableUtil.clear(scrnMsg.P);
        params[IDX_6] = scrnMsg.P;

        return params;
    }

    /**
     * Get Param NWAL1130 For Salesrep
     * @param scrnMsg NLEL0080BMsg
     * @param index int
     * @return Param NWAL1130 For Salesrep
     */
    public static Object[] getParamNWAL1130ForSlsrep(NLEL0080BMsg scrnMsg, int index) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Sales Rep Search";

        // START 2016/07/20 T.Tsuchida [QC#8092,MOD]
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append("    T.GLBL_CMPY_CD");
        sb.append("   ,T.EZCANCELFLAG");
        sb.append("   ,SP.PSN_NUM");
        sb.append("   ,T.TOC_NM");
        sb.append("   ,SP.LINE_BIZ_TP_CD");
        sb.append("   ,CB.COA_BR_NM");
        sb.append("   ,OFRT.ORG_FUNC_ROLE_TP_NM");
        sb.append("   ,T.TOC_CD ");
        sb.append("FROM");
        sb.append("    TOC T");
        sb.append("   ,ORG_FUNC_ROLE_TP OFRT");
        sb.append("   ,BIZ_AREA_ORG BA");
        sb.append("   ,ORG_FUNC_ASG OFA");
        sb.append("   ,S21_PSN SP");
        sb.append("   ,COA_BR CB ");
        sb.append("   ,S21_ORG SO ");
        sb.append("WHERE");
        sb.append("        T.GLBL_CMPY_CD        =  '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sb.append("    AND T.EZCANCELFLAG        = '0'");
        sb.append("    AND T.GLBL_CMPY_CD        = OFRT.GLBL_CMPY_CD");
        sb.append("    AND T.ORG_FUNC_ROLE_TP_CD = OFRT.ORG_FUNC_ROLE_TP_CD");
        sb.append("    AND OFRT.SLS_REP_FLG      = '").append(ZYPConstant.FLG_ON_Y).append("'");
        sb.append("    AND OFRT.EZCANCELFLAG     = '0'");
        sb.append("    AND OFRT.GLBL_CMPY_CD     = BA.GLBL_CMPY_CD");
        sb.append("    AND OFRT.FIRST_ORG_CD     = BA.BIZ_AREA_ORG_CD");
        sb.append("    AND BA.SLS_FLG            = '").append(ZYPConstant.FLG_ON_Y).append("'");
        sb.append("    AND BA.ORG_STRU_TP_CD     = '").append(ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY).append("'");
        sb.append("    AND BA.EZCANCELFLAG       = '0'");
        sb.append("    AND T.GLBL_CMPY_CD        = OFA.GLBL_CMPY_CD");
        sb.append("    AND T.TOC_CD              = OFA.TOC_CD");
        sb.append("    AND OFA.EZCANCELFLAG      = '0'");
        sb.append("    AND T.GLBL_CMPY_CD        = SO.GLBL_CMPY_CD ");
        sb.append("    AND T.TOC_CD              = SO.TOC_CD");
        sb.append("    AND SO.EZCANCELFLAG        = '0'");
        sb.append("    AND OFA.GLBL_CMPY_CD      = SP.GLBL_CMPY_CD");
        sb.append("    AND OFA.PSN_CD            = SP.PSN_CD");
        sb.append("    AND SP.EFF_FROM_DT        <= '").append(ZYPDateUtil.getSalesDate()).append("'");
        sb.append("    AND (SP.EFF_THRU_DT       >= '").append(ZYPDateUtil.getSalesDate()).append("' OR SP.EFF_THRU_DT IS NULL)");
        sb.append("    AND SP.EZCANCELFLAG       = '0'");
        sb.append("    AND T.GLBL_CMPY_CD        = CB.GLBL_CMPY_CD (+)");
        sb.append("    AND T.COA_BR_CD           = CB.COA_BR_CD (+)");
        sb.append("    AND CB.EZCANCELFLAG (+)   = '0'");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        // START 2016/11/28 Y.Tsuchimoto [QC#16268,MOD]
        //whereArray0[IDX_0] = "Resource Number";
        whereArray0[IDX_0] = "Sales Rep Number";
        // END   2016/11/28 Y.Tsuchimoto [QC#16268,MOD]
        whereArray0[IDX_1] = "PSN_NUM";
        // START 2016/09/15 J.Kim [QC#10360,MOD]
        // whereArray0[IDX_2] = "";
        if (hasValue(scrnMsg.A.no(index).slsRepTocCd)) {
            whereArray0[IDX_2] = scrnMsg.A.no(index).slsRepTocCd.getValue();
        } else {
            whereArray0[IDX_2] = PERCENT;
        }
        // END 2016/09/15 J.Kim [QC#10360,MOD]
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Name";
        whereArray1[IDX_1] = "TOC_NM";
        whereArray1[IDX_2] = "";
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = "Sales Rep";
        whereArray2[IDX_1] = "TOC_CD";
        // START 2016/09/15 J.Kim [QC#10360,MOD]
        //if (hasValue(scrnMsg.A.no(index).slsRepTocCd)) {
        //    whereArray2[IDX_2] = scrnMsg.A.no(index).slsRepTocCd.getValue();
        //} else {
        //    whereArray2[IDX_2] = PERCENT;
        //}
        whereArray2[IDX_2] = "";
        // END 2016/09/15 J.Kim [QC#10360,MOD]
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);
        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        // START 2016/11/28 Y.Tsuchimoto [QC#16268,MOD]
        //columnArray0[IDX_0] = "Resource Number";
        columnArray0[IDX_0] = "Sales Rep Number";
        // END   2016/11/28 Y.Tsuchimoto [QC#16268,MOD]
        columnArray0[IDX_1] = "PSN_NUM";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_12);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Name";
        columnArray1[IDX_1] = "TOC_NM";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "Line of Business";
        columnArray2[IDX_1] = "LINE_BIZ_TP_CD";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[IDX_4];
        columnArray3[IDX_0] = "Branch";
        columnArray3[IDX_1] = "COA_BR_NM";
        columnArray3[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray3[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[IDX_4];
        columnArray4[IDX_0] = "Role";
        columnArray4[IDX_1] = "ORG_FUNC_ROLE_TP_NM";
        columnArray4[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray4[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray4);

        Object[] columnArray5 = new Object[IDX_4];
        columnArray5[IDX_0] = "TOC_CD";
        columnArray5[IDX_1] = "TOC_CD";
        columnArray5[IDX_2] = BigDecimal.valueOf(IDX_0);
        columnArray5[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray5);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "PSN_NUM";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.P);
        params[IDX_6] = scrnMsg.P;
        // END 2016/07/20 T.Tsuchida [QC#8092,MOD]

        return params;
    }

    /**
     * initAppFracDigit
     * @param scrnMsg NLEL0080BMsg
     */
    public static void initAppFracDigit(NLEL0080BMsg scrnMsg) {

        for (int index = 0; index < scrnMsg.A.getValidCount(); index++) {
            scrnMsg.A.no(index).curValAmt.setAppFracDigit(2);
        }
    }

    /**
     * <pre>
     * The pop up parameter is cleared.
     * </pre>
     * @param scrnMsg Screen Msg
     */
    public static void clearPopUpParam(NLEL0080BMsg scrnMsg) {
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
        scrnMsg.xxPopPrm_PK.clear();
        scrnMsg.xxPopPrm_PL.clear();
        scrnMsg.xxPopPrm_PM.clear();
        scrnMsg.xxPopPrm_PN.clear();
    }

    /**
     * setFocusRule
     * @param scrnMsg NLEL0080BMsg
     */
    public static void setFocusRule(NLEL0080BMsg scrnMsg) {

        ZYPGUITableFocusRule tblFocusRule = new ZYPGUITableFocusRule(SCREEN_ID, "");
        ZYPGUIFocusRule focusRule = null;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            scrnMsg.A.no(i).dsAssetMstrPk.setInputProtected(true);
            focusRule = new ZYPGUIFocusRule("curValAmt#" + i);
            focusRule.setNextId("prntDsAssetMstrPk#" + i);
            tblFocusRule.addRule(focusRule);

            if ((i + 1) != scrnMsg.A.getValidCount()) {
                focusRule = new ZYPGUIFocusRule("postCd#" + i);
                focusRule.setNextId("xxChkBox#" + (i + 1));
                tblFocusRule.addRule(focusRule);
            }
        }
        scrnMsg.addGUIAttribute(tblFocusRule);
    }

    /**
     * Control items and buttons on the screen by Function ID.
     * @param handler EZDCommonHandler
     * @param scrnMsg NLEL0080BMsg
     * @return
     */
    private static void screenControlByFuncId(EZDCommonHandler handler, NLEL0080BMsg scrnMsg) {

        List<String> funcIdList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_ID);
        if (funcIdList == null || funcIdList.isEmpty()) {
            throw new S21AbendException("You can't operate Machine Master Inquiry Screen(NLEL0080). UserID is -> " + getUserProfileService().getContextUserInfo().getUserId());
        }

        if (funcIdList.contains(FUNC_ID_T020)) {
            enableButtons(handler, CLEAR[0], RETURN[0], ADD[0]);
        } else {
            enableButtons(handler, RETURN[0]);
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
