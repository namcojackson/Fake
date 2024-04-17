/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFAL0050.common;

import java.util.List;
import java.util.StringTokenizer;

import parts.common.EZDBMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import parts.servletcommon.EZDApplicationContext;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NFAL0050.NFAL0050BMsg;
import business.servlet.NFAL0050.constant.NFAL0050Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;
import com.fujitsu.uji.http.HttpDispatchContext;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   Fujitsu         N.Sasaki        Create          N/A
 * 2016/12/20   Fujitsu         H.Ikeda         Update          QC#12865
 * 2018/01/24   CITS            T.Gotoda        Update          QC#19964
 * 2019/09/20   Fujitsu         S.Takami        Update          QC#53616
 *</pre>
 */
public class NFAL0050CommonLogic implements NFAL0050Constant {

    /**
     * Method name: initCommonButton
     * <dd>The method explanation: init Common Button Control.
     * <dd>Remarks:
     * @param handler EZ Common Handler
     */
    // Start 2016/12/20 H.Ikeda [QC#12865,MOD]
    public static void initCommonButton(NFAL0050BMsg scrnMsg, EZDCommonHandler handler) {

        boolean updateFlg = hasUpdateFuncId(scrnMsg);
        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        if (updateFlg) {
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        }
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
        // START 2019/09/20 S.Takami [QC#53616,MOD]
//        handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 1, null);
        if (updateFlg) {
            handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
        }
        // END 2019/09/20 S.Takami [QC#53616,MOD]
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);

        // Common Button Confirm Message Settings
        if (updateFlg) {
            handler.setButtonConfirmMsg(BTN_CMN_SUBMIT[1], "ZZM8101I", new String[] {BTN_CMN_SUBMIT[2] }, 0);
            // START 2019/09/20 S.Takami [QC#53616,MOD], without any comments.
            handler.setButtonConfirmMsgEx(BTN_CMN_DELETE[1], "ZZM8101I", new String[] {BTN_CMN_DELETE[2] }, 0);
            // START 2019/09/20 S.Takami [QC#53616,MOD], without any comments
        }
        handler.setButtonConfirmMsg(BTN_CMN_CLEAR[1], "ZZM8101I", new String[] {BTN_CMN_CLEAR[2] }, 0);
        handler.setButtonConfirmMsg(BTN_CMN_RESET[1], "ZZM8101I", new String[] {BTN_CMN_RESET[2] }, 0);
        handler.setButtonConfirmMsg(BTN_CMN_RETURN[1], "ZZM8101I", new String[] {BTN_CMN_RETURN[2] }, 0);
    // End   2016/12/20 H.Ikeda [QC#12865,MOD]
    }

    /**
     * Method name: setNameForMessage
     * <dd>The method explanation: Set Name For Message.
     * <dd>Remarks:
     * @param bMsg EZDBMsg
     */
    public static void setNameForMessage(EZDBMsg bMsg) {

        NFAL0050BMsg scrnMsg = (NFAL0050BMsg) bMsg;

        EZDI18NLabelConv labelConv = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

        scrnMsg.sysSrcCd_3.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, SYS_SRC_CD));
        scrnMsg.trxCd_3.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, TRX_CD));
        scrnMsg.trxRsnCd_3.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, TRX_RSN_CD));

        scrnMsg.ajeId.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, AJE_ID));

        scrnMsg.ajePtrnIndTpCd_1V.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "AJE Pattern Indicator Type 1"));
        scrnMsg.ajePtrnIndTpCd_2V.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "AJE Pattern Indicator Type 2"));
        scrnMsg.ajePtrnIndTpCd_3V.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "AJE Pattern Indicator Type 3"));

        scrnMsg.ajePtrnActlCd_1V.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "AJE Code Value Type 1"));
        scrnMsg.ajePtrnActlCd_2V.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "AJE Code Value Type 2"));
        scrnMsg.ajePtrnActlCd_3V.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "AJE Code Value Type 3"));

        scrnMsg.jrnlCatgNm.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Journal Category Name"));
        scrnMsg.jrnlCatgCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Journal Category"));

        scrnMsg.ajeLineIdxNum_3.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Line Index and Debit/Credit Indicator"));
        scrnMsg.ajeCoaCmpyCd_3.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Company Code"));
        //---- start mod 2016/01/21
        scrnMsg.ajeCoaBrCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Branch Code"));
        //---- end 2016/01/21
        scrnMsg.ajeCoaCcCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Cost Center"));
        scrnMsg.ajeCoaAcctCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Account Code"));
        scrnMsg.ajeLineIndTpCd_3.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "AJE Line Indicator Type"));
        scrnMsg.ajeLineIdxDescTxt.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Line Index Description"));
        scrnMsg.ajeCoaProdCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Product Code"));
        scrnMsg.ajeCoaChCd_3.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Channel Code"));
        scrnMsg.ajeCoaAfflCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Intercompany Code"));
        scrnMsg.ajeCoaProjCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Merchandise Type"));
        scrnMsg.ajeCoaExtnCd_3.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Business Unit"));
    }

    /**
     * Method name: protectParmanentFields
     * <dd>Enable or Disable Button Property
     * @param scrnMsg NFAL0050BMsg
     */
    public static void protectParmanentFields(NFAL0050BMsg scrnMsg) {

        scrnMsg.sysSrcNm.setInputProtected(true);
        scrnMsg.trxNm.setInputProtected(true);
        scrnMsg.trxRsnNm.setInputProtected(true);
        scrnMsg.jrnlCatgNm.setInputProtected(true);
        scrnMsg.ajeLineIdxDescTxt.setInputProtected(true);
    }

    /**
     * Method name: enableSearchAje
     * <dd>Enable or Disable Button Property
     * @param scrnMsg NFAL0050BMsg
     * @param handler EZ Common Handler
     * @param enable boolean
     */
    public void enableSearchAje(NFAL0050BMsg scrnMsg, EZDCommonHandler handler, boolean enable) {
        handler.setButtonEnabled(EVT_ON_SEARCH, enable);
    }

    /**
     * Method name: enableCopyRow
     * <dd>Enable or Disable Button Property
     * @param scrnMsg NFAL0050BMsg
     * @param handler EZ Common Handler
     * @param flag boolean
     */
    public void enableCopyRow(NFAL0050BMsg scrnMsg, EZDCommonHandler handler, boolean flag) {
        handler.setButtonEnabled(EVT_COPY_ROW, flag);
    }

    /**
     * Method name: enablePasteRow
     * <dd>Enable or Disable Button Property
     * @param scrnMsg NFAL0050BMsg
     * @param handler EZ Common Handler
     * @param flag boolean
     */
    public void enablePasteRow(NFAL0050BMsg scrnMsg, EZDCommonHandler handler, boolean flag) {
        handler.setButtonEnabled(EVT_PASTE_ROW, flag);
    }

    /**
     * Method name: enableAddRow
     * <dd>Enable or Disable Button Property
     * @param scrnMsg NFAL0050BMsg
     * @param handler EZ Common Handler
     * @param flag boolean
     */
    private void enableAddRow(NFAL0050BMsg scrnMsg, EZDCommonHandler handler, boolean flag) {
        handler.setButtonEnabled(EVT_ADD_ROW, flag);
    }

    /**
     * Method name: enableDeleteRow
     * <dd>Enable or Disable Button Property
     * @param scrnMsg NFAL0050BMsg
     * @param handler EZ Common Handler
     * @param flag boolean
     */
    public void enableDeleteRow(NFAL0050BMsg scrnMsg, EZDCommonHandler handler, boolean flag) {
        handler.setButtonEnabled(EVT_DELETE_ROW, flag);
    }

    /**
     * Method name: enableSelectAll
     * <dd>Enable or Disable Button Property
     * @param scrnMsg NFAL0050BMsg
     * @param handler EZ Common Handler
     * @param flag boolean
     */
    public void enableSelectAll(NFAL0050BMsg scrnMsg, EZDCommonHandler handler, boolean flag) {
        handler.setButtonEnabled(EVT_SELECT_ALL, flag);
    }

    /**
     * Method name: enableUnSelectAll
     * <dd>Enable or Disable Button Property
     * @param scrnMsg NFAL0050BMsg
     * @param handler EZ Common Handler
     * @param flag boolean
     */
    public void enableUnSelectAll(NFAL0050BMsg scrnMsg, EZDCommonHandler handler, boolean flag) {
        handler.setButtonEnabled(EVT_UN_SELECT_ALL, flag);
    }

    /**
     * Method name: protectSearchableFileds
     * <dd>Enable or Disable Button Property
     * @param scrnMsg NFAL0050BMsg
     * @param handler EZ Common Handler
     * @param flag boolean
     */
    public void protectSearchableFileds(NFAL0050BMsg scrnMsg, EZDCommonHandler handler, boolean flag) {
        if (flag) {
            // Doesn't let you search
            scrnMsg.sysSrcCd_3.setInputProtected(true);
            scrnMsg.trxCd_3.setInputProtected(true);
            scrnMsg.trxRsnCd_3.setInputProtected(true);
            scrnMsg.ajeId.setInputProtected(true);
            enableSearchAje(scrnMsg, handler, false);
        } else {
            // Let you search
            scrnMsg.sysSrcCd_3.setInputProtected(false);
            scrnMsg.trxCd_3.setInputProtected(false);
            scrnMsg.trxRsnCd_3.setInputProtected(false);
            scrnMsg.ajeId.setInputProtected(false);
            enableSearchAje(scrnMsg, handler, true);
        }
    }

    private void enableSubmitDelete(NFAL0050BMsg scrnMsg, EZDCommonHandler handler, boolean enable) {
        // Start 2016/12/21 H.Ikeda [QC#12865,MOD]
        if (hasUpdateFuncId(scrnMsg)) {
            handler.setButtonEnabled(BTN_CMN_SUBMIT[0], enable);
            handler.setButtonEnabled(BTN_CMN_DELETE[0], enable);
        }
        // End   2016/12/21 H.Ikeda [QC#12865,MOD]
    }

    private void enableAddNewAJE(NFAL0050BMsg scrnMsg, EZDCommonHandler handler, boolean flag) {
        handler.setButtonEnabled(EVT_ADD_NEW_AJE_ID, flag);
    }

    /**
     * Method name: setUnEditableMode
     * <dd>Enable or Disable Button Property
     * @param scrnMsg NFAL0050BMsg
     * @param handler EZ Common Handler
     */
    public void setUnEditableMode(NFAL0050BMsg scrnMsg, EZDCommonHandler handler) {
        enableAddRow(scrnMsg, handler, false);
        enableSubmitDelete(scrnMsg, handler, false);
        enableSelectAll(scrnMsg, handler, false);
        setInputProtectChkBoxes(scrnMsg, handler, true);
        setInputProtectedAddRowFields(scrnMsg, true);
    }

    /**
     * Method name: setEditableMode
     * <dd>Enable or Disable Button Property
     * @param scrnMsg NFAL0050BMsg
     * @param handler EZ Common Handler
     */
    public void setEditableMode(NFAL0050BMsg scrnMsg, EZDCommonHandler handler) {
        //if (allIndPtrnSelected(scrnMsg) || scrnMsg.eventId_P.getValue().equals(EVT_PASTE)) {
        if (allIndPtrnSelected(scrnMsg)) {
            enableAddRow(scrnMsg, handler, true);
            setInputProtectChkBoxes(scrnMsg, handler, false);
            setInputProtectedAddRowFields(scrnMsg, false);

            if (scrnMsg.A.getValidCount() > 0) {
                enableSubmitDelete(scrnMsg, handler, true);
                enableSelectAll(scrnMsg, handler, true);
            } else {
                enableSubmitDelete(scrnMsg, handler, false);
                enableSelectAll(scrnMsg, handler, false);
            }
        } else {
            setUnEditableMode(scrnMsg, handler);
        }
        setInputProtectedIndicatorList(scrnMsg, false);
    }

    /**
     * Method name: enableAddRowButton
     * <dd>The method explanation:
     * <dd>Remarks:
     * @param scrnMsg NFAL0050BMsg
     * @param handler EZ Common Handler
     */
    public void enableAddRowButton(NFAL0050BMsg scrnMsg, EZDCommonHandler handler) {
        if (scrnMsg.A.getValidCount() > scrnMsg.A.length()) {
            handler.setButtonEnabled(EVT_ADD_ROW, false);
        } else {
            handler.setButtonEnabled(EVT_ADD_ROW, true);
        }
    }

    /**
     * Method name: enableAddRowButton
     * <dd>The method explanation: Button Control.
     * <dd>Remarks:
     * @param scrnMsg NFAL0050BMsg
     * @param handler EZ Common Handler
     * @param flag boolean
     */
    public void enableAddRowButton(NFAL0050BMsg scrnMsg, EZDCommonHandler handler, boolean flag) {
        if (scrnMsg.A.getValidCount() >= scrnMsg.A.length()) {
            handler.setButtonEnabled(EVT_ADD_ROW, false);
        } else {
            handler.setButtonEnabled(EVT_ADD_ROW, flag);
        }
    }

    /**
     * Method name: clearAddRow
     * <dd>The method explanation:
     * <dd>Remarks:
     * @param scrnMsg Screen Component Interface Message
     */
    public void clearAddRow(NFAL0050BMsg scrnMsg) {
        scrnMsg.ajeLineIdxNum_3.clear();
        scrnMsg.ajeCoaCmpyCd_3.clear();
      //---- start mod 2016/01/21
        scrnMsg.ajeCoaBrCd.clear();
        //---- end 2016/01/21

        scrnMsg.ajeCoaCcCd.clear();
        scrnMsg.ajeCoaAcctCd.clear();
        scrnMsg.ajeLineIndTpCd_3.clear();

        scrnMsg.ajeLineIdxDescTxt.clear();
        scrnMsg.ajeCoaProdCd.clear();

        scrnMsg.ajeCoaChCd_3.clear();
        scrnMsg.ajeCoaAfflCd.clear();
        scrnMsg.ajeCoaProjCd.clear();
        scrnMsg.ajeCoaExtnCd_3.clear();
    }

    /**
     * Method name: resetAllFileds
     * <dd>The method explanation:
     * <dd>Remarks:
     * @param scrnMsg Screen Component Interface Message
     * @param handler EZDCommonHandler
     */
    public void resetAllFileds(NFAL0050BMsg scrnMsg, EZDCommonHandler handler) {

        clearHeaderFields(scrnMsg);
        clearIndicatorFileds(scrnMsg);
        clearList(scrnMsg);
        clearAddRow(scrnMsg);
        protectCommonFiled(scrnMsg, handler);

        scrnMsg.eventId_P.clear();
    }

    /**
     * Method name: resetFiledsExceptHeader
     * <dd>The method explanation: TrxRsn
     * <dd>Remarks:
     * @param scrnMsg Screen Component Interface Message
     * @param handler EZDCommonHandler
     */
    public void resetFiledsExceptHeader(NFAL0050BMsg scrnMsg, EZDCommonHandler handler) {

        clearIndicatorFileds(scrnMsg);
        clearList(scrnMsg);
        clearAddRow(scrnMsg);
        protectCommonFiled(scrnMsg, handler);
    }

    /**
     * Method name: clearHeaderFields
     * <dd>The method explanation: TrxRsn
     * <dd>Remarks:
     * @param scrnMsg Screen Component Interface Message
     */
    public void clearHeaderFields(NFAL0050BMsg scrnMsg) {

        scrnMsg.sysSrcCd_3.clear();
        scrnMsg.sysSrcNm.clear();
        scrnMsg.trxCd_3.clear();
        scrnMsg.trxNm.clear();
        scrnMsg.trxRsnCd_3.clear();
        scrnMsg.trxRsnNm.clear();

        scrnMsg.ajeId.clear();

        scrnMsg.msgTxtInfoTxt_A.clear();
        scrnMsg.msgTxtInfoTxt_B.clear();

        if (!scrnMsg.eventId_P.getValue().equals(EVT_PASTE)) {
            scrnMsg.ajeId_T.clear();
        }
    }

    /**
     * Method name: clearIndicatorFileds
     * <dd>The method explanation: TrxRsn
     * <dd>Remarks:
     * @param scrnMsg Screen Component Interface Message
     */
    public void clearIndicatorFileds(NFAL0050BMsg scrnMsg) {

        // Clear Displaied filed for Indicator Type
        scrnMsg.ajePtrnIndTpCd_1V.clear();
        scrnMsg.ajePtrnIndTpCd_2V.clear();
        scrnMsg.ajePtrnIndTpCd_3V.clear();

        // Clear Displaied filed for Actual Type Code
        scrnMsg.ajePtrnActlCd_1V.clear();
        scrnMsg.ajePtrnActlCd_2V.clear();
        scrnMsg.ajePtrnActlCd_3V.clear();

        // Clear code value for Actual Type Code
        scrnMsg.ajePtrnActlCd_1C.clear();
        scrnMsg.ajePtrnActlCd_2C.clear();
        scrnMsg.ajePtrnActlCd_3C.clear();

        scrnMsg.jrnlCatgNm.clear();
        scrnMsg.jrnlCatgCd.clear();

        // START QC#19964
        scrnMsg.xxChkBox_AM.clear();
        scrnMsg.xxChkBox_QT.clear();
        // END   QC#19964

        scrnMsg.ezUpTime.clear();
        scrnMsg.ezUpTimeZone.clear();

        if (!scrnMsg.eventId_P.getValue().equals(EVT_PASTE)) {
            scrnMsg.ajePtrnIndTpNm_1T.clear();
            scrnMsg.ajePtrnIndTpNm_2T.clear();
            scrnMsg.ajePtrnIndTpNm_3T.clear();

            scrnMsg.ajePtrnActlNm_1T.clear();
            scrnMsg.ajePtrnActlNm_2T.clear();
            scrnMsg.ajePtrnActlNm_3T.clear();
        }
    }

    private void clearList(NFAL0050BMsg scrnMsg) {
        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);

        scrnMsg.C.clear();
        scrnMsg.C.setValidCount(0);
    }

    private void protectCommonFiled(NFAL0050BMsg scrnMsg, EZDCommonHandler handler) {

        scrnMsg.sysSrcCd_3.setInputProtected(false);
        scrnMsg.trxCd_3.setInputProtected(false);
        scrnMsg.trxRsnCd_3.setInputProtected(false);
        scrnMsg.ajeId.setInputProtected(false);

        enableAddNewAJE(scrnMsg, handler, false);
        enableSearchAje(scrnMsg, handler, true);

        enableCopyRow(scrnMsg, handler, false);
        enablePasteRow(scrnMsg, handler, false);
        enableAddRow(scrnMsg, handler, false);
        enableDeleteRow(scrnMsg, handler, false);
        enableSelectAll(scrnMsg, handler, false);
        enableUnSelectAll(scrnMsg, handler, false);

        enableSubmitDelete(scrnMsg, handler, false);

        setInputProtectedIndicatorList(scrnMsg, true);

        setInputProtectedAddRowFields(scrnMsg, true);
    }

    /**
     * Method name: setSubmitDeleteBtn
     * <dd>The method explanation: TrxRsn
     * <dd>Remarks:
     * @param scrnMsg Screen Component Interface Message
     * @param handler EZDCommonHandler
     */
    public void setSubmitDeleteBtn(NFAL0050BMsg scrnMsg, EZDCommonHandler handler) {
        if (scrnMsg.A.getValidCount() > 0) {
            enableSubmitDelete(scrnMsg, handler, true);
            enableSelectAll(scrnMsg, handler, true);
        } else {
            enableSubmitDelete(scrnMsg, handler, false);
            enableSelectAll(scrnMsg, handler, false);
        }
        enableDeleteRow(scrnMsg, handler, false);
        enableUnSelectAll(scrnMsg, handler, false);
        clearAddRow(scrnMsg);
    }

    /**
     * Method name: setSubmitDeleteBtn
     * <dd>The method explanation: Clear filed except SysCd, Trx,
     * TrxRsn
     * <dd>Remarks:
     * @param scrnMsg Screen Component Interface Message
     * @param handler EZDCommonHandler
     * @param enable boolean
     */
    public void setSubmitDeleteBtn(NFAL0050BMsg scrnMsg, EZDCommonHandler handler, boolean enable) {
        if (enable && scrnMsg.A.getValidCount() > 0) {
            enableSubmitDelete(scrnMsg, handler, true);
            enableSelectAll(scrnMsg, handler, true);
        } else {
            enableSubmitDelete(scrnMsg, handler, false);
            enableSelectAll(scrnMsg, handler, false);
        }
        enableDeleteRow(scrnMsg, handler, false);
        enableUnSelectAll(scrnMsg, handler, false);
        clearAddRow(scrnMsg);
    }

    /**
     * Method name: setSelelctUnSelectAllBtn
     * <dd>The method explanation: TrxRsn
     * <dd>Remarks:
     * @param scrnMsg Screen Component Interface Message
     * @param handler EZDCommonHandler
     */
    public void setSelelctUnSelectAllBtn(NFAL0050BMsg scrnMsg, EZDCommonHandler handler) {
        int count = 0;
        if (scrnMsg.A.getValidCount() == 0) {
            clearAddRow(scrnMsg);
            enableSelectAll(scrnMsg, handler, false);
            enableUnSelectAll(scrnMsg, handler, false);

            enableDeleteRow(scrnMsg, handler, false);
            enableCopyRow(scrnMsg, handler, false);
            enablePasteRow(scrnMsg, handler, false);
        } else {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (CHECKED.equals(scrnMsg.A.no(i).xxChkBox_A.getValue())) {
                    count++;
                }
            }
            if (count == 0) {
                clearAddRow(scrnMsg);
                enableSelectAll(scrnMsg, handler, true);
                enableUnSelectAll(scrnMsg, handler, false);

                enableDeleteRow(scrnMsg, handler, false);
                enableCopyRow(scrnMsg, handler, false);
                enablePasteRow(scrnMsg, handler, false);
            } else if (count == scrnMsg.A.getValidCount()) {
                enableSelectAll(scrnMsg, handler, false);
                enableUnSelectAll(scrnMsg, handler, true);

                enableDeleteRow(scrnMsg, handler, true);
                enableCopyRow(scrnMsg, handler, true);
                enablePasteRow(scrnMsg, handler, false);
            } else {
                enableSelectAll(scrnMsg, handler, true);
                enableUnSelectAll(scrnMsg, handler, true);

                enableDeleteRow(scrnMsg, handler, true);
                enableCopyRow(scrnMsg, handler, true);
                enablePasteRow(scrnMsg, handler, false);
            }
        }
    }

    /**
     * Method name: setInputProtectedAddRowFields
     * <dd>The method explanation: TrxRsn
     * <dd>Remarks:
     * @param scrnMsg Screen Component Interface Message
     * @param enable boolean
     */
    public void setInputProtectedAddRowFields(NFAL0050BMsg scrnMsg, boolean enable) {
        scrnMsg.ajeLineIdxNum_3.setInputProtected(enable);
        scrnMsg.ajeCoaCmpyCd_3.setInputProtected(enable);
      //---- start mod 2016/01/21
        scrnMsg.ajeCoaBrCd.setInputProtected(enable);
        scrnMsg.jrnlCatgCd.setInputProtected(enable);
        //---- end 2016/01/21
        scrnMsg.ajeCoaCcCd.setInputProtected(enable);
        scrnMsg.ajeCoaAcctCd.setInputProtected(enable);
        scrnMsg.ajeLineIndTpCd_3.setInputProtected(enable);
        // line index description is not editable
        // scrnMsg.ajeLineIdxDescTxt.setInputProtected(enable);
        scrnMsg.ajeCoaProdCd.setInputProtected(enable);
        scrnMsg.ajeCoaChCd_3.setInputProtected(enable);
        scrnMsg.ajeCoaAfflCd.setInputProtected(enable);
        scrnMsg.ajeCoaProjCd.setInputProtected(enable);
        scrnMsg.ajeCoaExtnCd_3.setInputProtected(enable);

        // START QC#19964
        scrnMsg.xxChkBox_AM.setInputProtected(enable);
        scrnMsg.xxChkBox_QT.setInputProtected(enable);
        // END   QC#19964
    }

    /**
     * Method name: setInputProtectedIndicatorList
     * <dd>The method explanation: TrxRsn
     * <dd>Remarks:
     * @param scrnMsg Screen Component Interface Message
     * @param enable boolean
     */
    public void setInputProtectedIndicatorList(NFAL0050BMsg scrnMsg, boolean enable) {

        if (enable) {
            // Set protected all fields
            scrnMsg.ajePtrnIndTpCd_1V.setInputProtected(enable);
            scrnMsg.ajePtrnActlCd_1V.setInputProtected(enable);
            scrnMsg.ajePtrnIndTpCd_2V.setInputProtected(enable);
            scrnMsg.ajePtrnActlCd_2V.setInputProtected(enable);
            scrnMsg.ajePtrnIndTpCd_3V.setInputProtected(enable);
            scrnMsg.ajePtrnActlCd_3V.setInputProtected(enable);
        } else {
            // Set Un-protected according to selected indicator type
            // Type1, Code1
            // Type2, Code2
            // Type3, Code3
            if (scrnMsg.ajePtrnIndTpCd_1V.getValue().equals(DEFAULT_VAL_CD_3) || scrnMsg.ajePtrnIndTpCd_1V.getValue().equals(BLANK)) {
                if (!scrnMsg.ajeId.isInputProtected()) {
                    // Type1 is NOT selected BUT
                    // AJE ID is not protected yet
                    setInputProtectIndTp(scrnMsg, 0);
                } else {
                    // Type1 is NOT selected
                    setInputProtectIndTp(scrnMsg, 1);
                }
            } else if (!scrnMsg.ajePtrnIndTpCd_1V.getValue().equals(DEFAULT_VAL_CD_3) && !scrnMsg.ajePtrnIndTpCd_1V.getValue().equals(BLANK)) {
                // Type1 is selected
                // What if Code1 is also selected?
                if (!scrnMsg.ajePtrnActlCd_1V.getValue().equals(BLANK)) {
                    // Type1, Code1 are selected
                    // What if Type2 is also selected?
                    if (!scrnMsg.ajePtrnIndTpCd_2V.getValue().equals(DEFAULT_VAL_CD_3) && !scrnMsg.ajePtrnIndTpCd_2V.getValue().equals(BLANK)) {
                        // Type1, Code1, Type2 are selected
                        // What if Code2 is also selected?
                        if (!scrnMsg.ajePtrnActlCd_2V.getValue().equals(BLANK)) {
                            // Type1, Code1, Type2, Code2 are selected
                            // What if Type3 is also selected?
                            if (!scrnMsg.ajePtrnIndTpCd_3V.getValue().equals(DEFAULT_VAL_CD_3) && !scrnMsg.ajePtrnIndTpCd_3V.getValue().equals(BLANK)) {
                                // Only Type1, Code1, Type2, Code2,
                                // Type3 are selected
                                setInputProtectIndTp(scrnMsg, 6);
                            } else {
                                // Only Type1, Code1, Type2, Code2 are
                                // selected
                                setInputProtectIndTp(scrnMsg, 5);
                            }
                        } else {
                            // Only Type1, Code1, Type2 are selected
                            setInputProtectIndTp(scrnMsg, 4);
                        }
                    } else {
                     // add 2016/01/22
                        if (DEFAULT_VAL_CD_3.equals(scrnMsg.ajePtrnIndTpCd_2V.getValue())) {
                            // enable till ajePtrnIndTpCd_3V
                            setInputProtectIndTp(scrnMsg, 5);
                        } else {
                     // end 2016/01/22
                            // Only Type1, Code1 are selected
                            setInputProtectIndTp(scrnMsg, 3);
                        }
                        
                    }
                } else {
                    // Only Type1 is selected
                    setInputProtectIndTp(scrnMsg, 2);
                }
            }
        }
    }

    private void setInputProtectIndTp(NFAL0050BMsg scrnMsg, int startProtectIdx) {
        boolean protect = false;
        for (int i = 0; i < 6; i++) {
            if (i == startProtectIdx) {
                protect = true;
            }
            if (i == 0) {
                scrnMsg.ajePtrnIndTpCd_1V.setInputProtected(protect);
            } else if (i == 1) {
                scrnMsg.ajePtrnActlCd_1V.setInputProtected(protect);
            } else if (i == 2) {
                scrnMsg.ajePtrnIndTpCd_2V.setInputProtected(protect);
            } else if (i == 3) {
                scrnMsg.ajePtrnActlCd_2V.setInputProtected(protect);
            } else if (i == 4) {
                scrnMsg.ajePtrnIndTpCd_3V.setInputProtected(protect);
            } else if (i == 5) {
                scrnMsg.ajePtrnActlCd_3V.setInputProtected(protect);
            }
        }
    }

    /**
     * Method name: setInputProtectChkBoxes
     * <dd>The method explanation: set check boxes
     * <dd>Remarks:
     * @param scrnMsg Screen Component Interface Message
     * @param handler EZDCommonHandler
     * @param enable boolean
     */
    public void setInputProtectChkBoxes(NFAL0050BMsg scrnMsg, EZDCommonHandler handler, boolean enable) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).xxChkBox_A.clear();
            scrnMsg.A.no(i).xxChkBox_A.setInputProtected(enable);
        }
        if (enable) {
            enableSelectAll(scrnMsg, handler, false);
        } else {
            enableSelectAll(scrnMsg, handler, true);
        }

    }

    /**
     * Method name: clearChkBoxes
     * <dd>The method explanation: set check boxes
     * <dd>Remarks:
     * @param scrnMsg Screen Component Interface Message
     */
    public void clearChkBoxes(NFAL0050BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).xxChkBox_A.clear();
            scrnMsg.A.no(i).xxChkBox_A.setInputProtected(false);
        }
    }

    /**
     * Method name: setInputProtectedTextField
     * <dd>The method explanation: Set input protected
     * <dd>Remarks:
     * @param scrnMsg Screen Component Interface Message
     */
    public static void setInputProtectedTextField(NFAL0050BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).ajeLineIndTpNm_A.setInputProtected(true);
            scrnMsg.A.no(i).ajeLineIdxDescTxt_A.setInputProtected(true);
        }
    }

    /**
     * Method name: setNewAjeId
     * <dd>The method explanation: set check boxes
     * <dd>Remarks:
     * @param scrnMsg Screen Component Interface Message
     * @param handler EZDCommonHandler
     */
    public void setNewAjeId(NFAL0050BMsg scrnMsg, EZDCommonHandler handler) {

        String sysSrcCd = scrnMsg.sysSrcCd_3.getValue();
        String trxCd = scrnMsg.trxCd_3.getValue();
        String trxRsnCd = scrnMsg.trxRsnCd_3.getValue();

        if (!scrnMsg.sysSrcCd_3.isClear() && scrnMsg.trxCd_3.isClear() && scrnMsg.trxRsnCd_3.isClear()) {
            scrnMsg.ajeId.setValue(sysSrcCd + HYPHEN);
        } else if (!scrnMsg.sysSrcCd_3.isClear() && !scrnMsg.trxCd_3.isClear() && scrnMsg.trxRsnCd_3.isClear()) {
            scrnMsg.ajeId.setValue(sysSrcCd + HYPHEN + trxCd + HYPHEN);
        } else if (!scrnMsg.sysSrcCd_3.isClear() && !scrnMsg.trxCd_3.isClear() && !scrnMsg.trxRsnCd_3.isClear()) {
            scrnMsg.ajeId.setValue(sysSrcCd + HYPHEN + trxCd + HYPHEN + trxRsnCd);
            // scrnMsg.ajeId_T.setValue(scrnMsg.ajeId.getValue());
        }
    }

    /**
     * Method name: isValidAjeIDFormat
     * <dd>The method explanation: Check format for AJE ID
     * <dd>Remarks:
     * @param scrnMsg Screen Component Interface Message
     * @return boolean
     */
    public boolean isValidAjeIDFormat(NFAL0050BMsg scrnMsg) {

        if (scrnMsg.ajeId.isClear()) {
            return true;
        } else {
            StringTokenizer tk = new StringTokenizer(scrnMsg.ajeId.getValue(), HYPHEN);
            if (tk.countTokens() != 3) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method name: idxDCExist
     * <dd>The method explanation:
     * <dd>Remarks:
     * @param scrnMsg Screen Component Interface Message
     * @return boolean
     */
    public boolean idxDCExist(NFAL0050BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            String ajeLineIdxNum = scrnMsg.A.no(i).ajeLineIdxNum_A.getValue();
            String drCrTpCd = scrnMsg.A.no(i).drCrTpCd_A.getValue();
            if (ZYPCommonFunc.hasValue(ajeLineIdxNum) && ajeLineIdxNum.length() > 0) {
                ajeLineIdxNum = Integer.toString(Integer.parseInt(ajeLineIdxNum));
            }
            String idxDc = ajeLineIdxNum + drCrTpCd;
            if (idxDc.equals(scrnMsg.ajeLineIdxNum_3.getValue())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method name: getLineIdxNum
     * <dd>The method explanation:
     * <dd>Remarks:
     * @param scrnMsg Screen Component Interface Message
     * @return String
     */
    public String getLineIdxNum(NFAL0050BMsg scrnMsg) {
        String ajeLineIdxNum = "0";
        if (!scrnMsg.ajeLineIdxNum_3.isClear() && scrnMsg.ajeLineIdxNum_3.getValue().length() == 2) {
            ajeLineIdxNum += scrnMsg.ajeLineIdxNum_3.getValue().charAt(0);
        }
        return ajeLineIdxNum;
    }

    /**
     * Method name: getLineIdxNum
     * <dd>The method explanation:
     * <dd>Remarks:
     * @param scrnMsg Screen Component Interface Message
     * @return String
     */
    public String getDrCrTpCd(NFAL0050BMsg scrnMsg) {
        String drCrTpCd = BLANK;
        if (!scrnMsg.ajeLineIdxNum_3.isClear() && scrnMsg.ajeLineIdxNum_3.getValue().length() == 2) {
            drCrTpCd += scrnMsg.ajeLineIdxNum_3.getValue().charAt(1);
        }
        return drCrTpCd;
    }

    /**
     * Method name: allIndPtrnSelected
     * <dd>The method explanation: Check all Ind type is selected
     * <dd>Remarks:
     * @param scrnMsg Screen Component Interface Message
     * @return boolean
     */
    private boolean allIndPtrnSelected(NFAL0050BMsg scrnMsg) {
        if (!scrnMsg.ajePtrnIndTpCd_1V.isClear() // Type_1
                && !scrnMsg.ajePtrnIndTpCd_2V.isClear() // Type_2
                && !scrnMsg.ajePtrnIndTpCd_3V.isClear() // Type_3
                && !scrnMsg.ajePtrnActlCd_1V.isClear() // Code_1
                && !scrnMsg.ajePtrnActlCd_2V.isClear() // Code_2
                && !scrnMsg.ajePtrnActlCd_3V.isClear() // Code_3
        ) {
            return true;
        }
        return false;
    }

    /**
     * Method name: isAddRowListBoxesFilled
     * <dd>The method explanation: Check pull down is all selected
     * for "Add Row" button
     * <dd>Remarks:
     * @param scrnMsg Screen Component Interface Message
     * @return boolean
     */
    public boolean isAddRowListBoxesFilled(NFAL0050BMsg scrnMsg) {

      //---- start mod 2016/01/21 remove branch
        if (!scrnMsg.ajeLineIdxNum_3.isClear() // Idx/DC
                && !scrnMsg.ajeCoaCmpyCd_3.isClear() // Co
                //&& !scrnMsg.ajeCoaBrCd_3.isClear() // Br
                && !scrnMsg.ajeCoaChCd_3.isClear() // Ch
                && !scrnMsg.ajeCoaExtnCd_3.isClear()) { // Ext
            return true;
        } else {
            return false;
        }
        //---- end 2016/01/21
    }

    /**
     * Method name: setFocus
     * <dd>The method explanation: Button Control.
     * <dd>Remarks:
     * @param scrnMsg Screen Component Interface Message
     */
    public void setFocus(NFAL0050BMsg scrnMsg) {
        if (!scrnMsg.ajeId.isInputProtected()) {
            scrnMsg.setFocusItem(scrnMsg.ajeId);
        } else if (!scrnMsg.ajePtrnIndTpCd_1V.isInputProtected()) {
            scrnMsg.setFocusItem(scrnMsg.ajePtrnIndTpCd_1V);
        } else if (!scrnMsg.ajePtrnActlCd_1V.isInputProtected()) {
            scrnMsg.setFocusItem(scrnMsg.ajePtrnActlCd_1V);
        } else if (!scrnMsg.ajePtrnIndTpCd_2V.isInputProtected()) {
            scrnMsg.setFocusItem(scrnMsg.ajePtrnIndTpCd_2V);
        } else if (!scrnMsg.ajePtrnActlCd_2V.isInputProtected()) {
            scrnMsg.setFocusItem(scrnMsg.ajePtrnActlCd_2V);
        } else if (!scrnMsg.ajePtrnIndTpCd_3V.isInputProtected()) {
            scrnMsg.setFocusItem(scrnMsg.ajePtrnIndTpCd_3V);
        } else if (!scrnMsg.ajePtrnActlCd_3V.isInputProtected()) {
            scrnMsg.setFocusItem(scrnMsg.ajePtrnActlCd_3V);
        } else {
            scrnMsg.setFocusItem(scrnMsg.ajeLineIdxNum_3);
        }
    }

    /**
     * Method name: clearMessageOnScrn
     * <dd>The method explanation: Clear Message on Screen
     * <dd>Remarks:
     * @param scrnMsg Screen Component Interface Message
     */
    public void clearMessageOnScrn(NFAL0050BMsg scrnMsg) {
        scrnMsg.msgTxtInfoTxt_A.clear();
        scrnMsg.msgTxtInfoTxt_B.clear();
    }

    /**
     * Method name: setPasteMode
     * <dd>The method explanation:
     * <dd>Remarks:
     * @param scrnMsg Screen Component Interface Message
     * @param handler EZDCommonHandler
     * @param enable boolean
     */
    public void setPasteMode(NFAL0050BMsg scrnMsg, EZDCommonHandler handler, boolean enable) {

        if (enable) {
            scrnMsg.ajeId.setInputProtected(false);
            scrnMsg.sysSrcCd_3.setInputProtected(false);
            scrnMsg.trxCd_3.setInputProtected(false);
            scrnMsg.trxRsnCd_3.setInputProtected(false);

            enableSearchAje(scrnMsg, handler, true);
            enableAddRow(scrnMsg, handler, false);

            scrnMsg.msgTxtInfoTxt_P.setValue(PASTE_MODE_TXT);
            scrnMsg.eventId_P.setValue(EVT_PASTE);
        } else {
            scrnMsg.msgTxtInfoTxt_P.clear();
            scrnMsg.eventId_P.clear();
        }
    }

    /**
     * Method name: afterSearch
     * <dd>The method explanation:
     * <dd>Remarks:
     * @param ctx EZDApplicationContext
     * @param scrnMsg Screen Component Interface Message
     * @param handler EZDCommonHandler
     */
    public void afterSearch(EZDApplicationContext ctx, NFAL0050BMsg scrnMsg, EZDCommonHandler handler) {

        // Clear checked box
        clearChkBoxes(scrnMsg);
        // Enable to input for TrxRsn when no error
        scrnMsg.trxRsnCd_3.setInputProtected(false);

        int meg = scrnMsg.getMessageType();
        if (meg == ERROR) {
            // AJE Code doesn't exist
            enableAddRow(scrnMsg, handler, false);
        } else {
            if (!scrnMsg.ajeId.isClear()) {
                scrnMsg.ajeId.setInputProtected(true);
            }
            setEditableMode(scrnMsg, handler);
            enableSearchAje(scrnMsg, handler, true);
        }
        setFocus(scrnMsg);
        setSelelctUnSelectAllBtn(scrnMsg, handler);
        changeTableColorByRow(ctx, scrnMsg);
    }

    /**
     * Method name: changeTableColorByRow
     * <dd>The method explanation: change Table Coloer by Row.
     * <dd>Remarks:
     * @param ctx Application Context
     * @param bMsg Screen Component Interface Message
     */
    public void changeTableColorByRow(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0050BMsg scrnMsg = (NFAL0050BMsg) bMsg;

        String screenID = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters().getSingleValue("pageID");

        S21TableColorController tblColor = new S21TableColorController(screenID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
    }

    // Start 2016/12/21 H.Ikeda [QC#12865,ADD]
    /**
     * hasUpdateFuncId
     * @return boolean
     */
    private static boolean hasUpdateFuncId(NFAL0050BMsg scrnMsg) {
        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();

        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(BIZ_ID);
        if (funcList == null || funcList.isEmpty()) {
            scrnMsg.setMessageInfo(ZZSM4300E, new String[] {userProfSvc.getContextUserInfo().getUserId()});
        }
        if (funcList.contains(FUNC_ID_UPDATE)) {
            return true;
        }
        return false;
    }
    // End  2016/12/21 H.Ikeda [QC#12865,ADD]
}
