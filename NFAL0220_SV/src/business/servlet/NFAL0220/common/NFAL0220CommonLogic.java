/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFAL0220.common;

import static business.servlet.NFAL0220.constant.NFAL0220Constant.*;

import java.util.List;

import parts.common.EZDGUIAttribute;
import business.blap.NFAL0220.NFAL0220CMsg;
import business.servlet.NFAL0220.NFAL0220BMsg;
import business.servlet.NFAL0220.NFAL0220_ABMsg;
import business.servlet.NFAL0220.NFAL0220_ABMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NFAL0220CommonLogic
 * Manual Journal Entry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/02   Fujitsu         S.Fujita        Create          N/A
 * 2016/06/21   Hitachi         J.Kim           Update          QC#10325
 * 2016/07/13   Hitachi         J.Kim           Update          QC#10324
 * 2016/08/02   Hitachi         K.Kojima        Update          QC#12766
 * 2016/08/07   Hitachi         J.Kim           Update          QC#12851
 * 2016/08/22   Hitachi         J.Kim           Update          QC#13537
 * 2016/03/15   Hitachi         E.Kameishi      Update          QC#15854
 *</pre>
 */
public class NFAL0220CommonLogic {

    /**
     * setRequestData_SearchCommon
     * @return bizMsg NFAL0220CMsg
     */
    public static NFAL0220CMsg setRequestData_UpdateCommon() {

        NFAL0220CMsg bizMsg = new NFAL0220CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_UPD);

        return bizMsg;
    }

    /**
     * setRequestData_SearchCommon
     * @return bizMsg NFAL0220CMsg
     */
    public static NFAL0220CMsg setRequestData_SearchCommon() {

        NFAL0220CMsg bizMsg = new NFAL0220CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);

        return bizMsg;
    }

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {

        handler.setButtonProperties(BTN_01_SAV_NAME, BTN_01_SAV_GUARD, BTN_01_SAV_LABEL, 0, null);
        handler.setButtonProperties(BTN_02_SUB_NAME, BTN_02_SUB_GUARD, BTN_02_SUB_LABEL, 1, null);
        handler.setButtonProperties(BTN_03_APL_NAME, BTN_03_APL_GUARD, BTN_03_APL_LABEL, 0, null);
        handler.setButtonProperties(BTN_04_APR_NAME, BTN_04_APR_GUARD, BTN_04_APR_LABEL, 0, null);
        handler.setButtonProperties(BTN_05_REJ_NAME, BTN_05_REJ_GUARD, BTN_05_REJ_LABEL, 0, null);
        handler.setButtonProperties(BTN_06_DWL_NAME, BTN_06_DWL_GUARD, BTN_06_DWL_LABEL, 1, null);
        handler.setButtonProperties(BTN_07_DEL_NAME, BTN_07_DEL_GUARD, BTN_07_DEL_LABEL, 0, null);
        handler.setButtonProperties(BTN_08_CLE_NAME, BTN_08_CLE_GUARD, BTN_08_CLE_LABEL, 1, null);
        handler.setButtonProperties(BTN_09_RST_NAME, BTN_09_RST_GUARD, BTN_09_RST_LABEL, 0, null);
        handler.setButtonProperties(BTN_10_RTR_NAME, BTN_10_RTR_GUARD, BTN_10_RTR_LABEL, 1, null);

        handler.setButtonEnabled(CATG_BTN[0], true);
        handler.setButtonEnabled(COPY_JOURNAL[0], true);
        handler.setButtonEnabled(REVERSE[0], true);
        handler.setButtonEnabled(UPLOAD_FILE[0], true);
        handler.setButtonEnabled(ADD_LINE[0], true);
        handler.setButtonEnabled(DELETE[0], true);
    }

    /**
     * setGuiAttr
     * @param handler S21CommonHandler
     * @param scrnMsg BMAL0040BMsg
     */
    public static void setGuiAttr(S21CommonHandler handler, NFAL0220BMsg scrnMsg) {

        boolean hasUpdateFunc = hasUpdateFuncId();

        if (!hasUpdateFunc) {
            allInputsProtect(handler, scrnMsg);
            // New MODE
        } else if (!ZYPCommonFunc.hasValue(scrnMsg.manJrnlEntryHdrPk)) {
            setVisibilityItem(scrnMsg, "glSendCpltFlg", false);
            setVisibilityItem(scrnMsg, "rvslCpltFlg", false);

            // START 2016/08/04 J.Kim [QC#12851,MOD]
            // handler.setButtonEnabled(BTN_06_DWL_NAME, false);
            // END 2016/08/04 J.Kim [QC#12851,MOD]
            handler.setButtonEnabled(REVERSE[0], false);
            handler.setButtonEnabled(COPY_JOURNAL[0], false);
            scrnMsg.A.setValidCount(INITROW);
            // Copy
        } else if (NEW.equals(scrnMsg.xxModeCd_R.getValue())) {
            setVisibilityItem(scrnMsg, "glSendCpltFlg", false);
            setVisibilityItem(scrnMsg, "rvslCpltFlg", false);
            allInputsProtectActive(handler, scrnMsg);
            scrnMsg.xxModeCd_R.clear();
            // Read MODE
        } else if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.glSendCpltFlg.getValue())) {
            allInputsProtect(handler, scrnMsg);
            setVisibilityItem(scrnMsg, "glSendCpltFlg", true);

            // START 2016/08/04 J.Kim [QC#12851,DEL]
            // handler.setButtonEnabled(BTN_06_DWL_NAME, false);
            // END 2016/08/04 J.Kim [QC#12851,DEL]
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.rvslCpltFlg.getValue())) {
                handler.setButtonEnabled(REVERSE[0], false);
                handler.setButtonEnabled(COPY_JOURNAL[0], true);
            } else {
                handler.setButtonEnabled(REVERSE[0], true);
                handler.setButtonEnabled(COPY_JOURNAL[0], true);
            }
            // Edit MODE
        } else {
            setVisibilityItem(scrnMsg, "glSendCpltFlg", true);
            setVisibilityItem(scrnMsg, "rvslCpltFlg", false);
            scrnMsg.glSendCpltFlg.setInputProtected(true);

            // START 2016/08/04 J.Kim [QC#12851,MOD]
            // handler.setButtonEnabled(BTN_06_DWL_NAME, true);
            // if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.rvslCpltFlg.getValue())) {
            //     handler.setButtonEnabled(REVERSE[0], false);
            //     handler.setButtonEnabled(COPY_JOURNAL[0], false);
            // } else {
            //     handler.setButtonEnabled(REVERSE[0], true);
            //     handler.setButtonEnabled(COPY_JOURNAL[0], true);
            // }
            handler.setButtonEnabled(REVERSE[0], false);
            handler.setButtonEnabled(COPY_JOURNAL[0], false);
            // END 2016/08/04 J.Kim [QC#12851,MOD]

            // START 2016/08/22 J.Kim [QC#13537,ADD]
            setButtonEnabledReverseJournal(handler, scrnMsg);
            // END 2016/08/22 J.Kim [QC#13537,ADD]
        }

        // START 2016/08/22 J.Kim [QC#13537,ADD]
        scrnMsg.glPerNm.setInputProtected(true);
        // END 2016/08/22 J.Kim [QC#13537,ADD]
        scrnMsg.jrnlCatgDescTxt.setInputProtected(true);
        scrnMsg.jrnlDealAmt_T1.setInputProtected(true);
        scrnMsg.jrnlDealAmt_T2.setInputProtected(true);
    }

    // START 2016/08/22 J.Kim [QC#13537,ADD]
    private static void setButtonEnabledReverseJournal(S21CommonHandler handler, NFAL0220BMsg scrnMsg) {

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.manJrnlCpltFlg.getValue())) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.rvslCpltFlg.getValue())) {
                handler.setButtonEnabled(REVERSE[0], false);
                handler.setButtonEnabled(COPY_JOURNAL[0], true);
            } else {
                handler.setButtonEnabled(REVERSE[0], true);
                handler.setButtonEnabled(COPY_JOURNAL[0], true);
            }
        }
    }
    // END 2016/08/22 J.Kim [QC#13537,ADD]

    /**
     * hasUpdateFuncId
     * @return boolean
     */
    public static boolean hasUpdateFuncId() {
        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();

        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(BIZ_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException(ZZSM4300E, new String[] {userProfSvc.getContextUserInfo().getUserId() });
        }
        if (funcList.contains(FUNC_ID_UPDATE)) {
            return true;
        }
        return false;
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NFAL0220BMsg
     * @param scrnAMsgAry NFAL0220_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NFAL0220BMsg scrnMsg, NFAL0220_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NFAL0220BMsg
     * @param scrnAMsgAry NFAL0220_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     * @param grpRows color reversal switch lines
     */
    public static void setRowsBGWithClear(NFAL0220BMsg scrnMsg, NFAL0220_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color
     * back to the initial color.
     * @param scrnMsg NFAL0220BMsg
     * @param scrnAMsgAry NFAL0220_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void clearRowsBG(NFAL0220BMsg scrnMsg, NFAL0220_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * allInputsProtect
     * @param handler S21CommonHandler
     * @param scrnMsg NFAL0220BMsg
     */
    public static void allInputsProtect(S21CommonHandler handler, NFAL0220BMsg scrnMsg) {

        // Header
        scrnMsg.manJrnlNm.setInputProtected(true);
        scrnMsg.glSendCpltFlg.setInputProtected(true);
        scrnMsg.manJrnlCpltFlg.setInputProtected(true);
        scrnMsg.manJrnlDescTxt.setInputProtected(true);
        scrnMsg.rsvdRvslDt.setInputProtected(true);
        scrnMsg.autoRvslFlg.setInputProtected(true);
        scrnMsg.rvslCpltFlg.setInputProtected(true);
        scrnMsg.xxChkBox_C.setInputProtected(true);
        scrnMsg.jrnlCatgCd.setInputProtected(true);
        scrnMsg.jrnlCatgDescTxt.setInputProtected(true);
        scrnMsg.glPerNm.setInputProtected(true);
        scrnMsg.glDt.setInputProtected(true);
        scrnMsg.xxChkBox_V.setInputProtected(true);

        handler.setButtonEnabled(CATG_BTN[0], false);
        handler.setButtonEnabled(COPY_JOURNAL[0], false);
        handler.setButtonEnabled(REVERSE[0], false);
        handler.setButtonEnabled(UPLOAD_FILE[0], false);
        handler.setButtonEnabled(ADD_LINE[0], false);
        handler.setButtonEnabled(DELETE[0], false);

        handler.setButtonProperties(BTN_02_SUB_NAME, BTN_02_SUB_GUARD, BTN_02_SUB_LABEL, 0, null);
        // START 2016/08/04 J.Kim [QC#12851,MOD]
        // handler.setButtonProperties(BTN_06_DWL_NAME, BTN_06_DWL_GUARD, BTN_06_DWL_LABEL, 0, null);
        handler.setButtonProperties(BTN_06_DWL_NAME, BTN_06_DWL_GUARD, BTN_06_DWL_LABEL, 1, null);
        // END 2016/08/04 J.Kim [QC#12851,MOD]
        handler.setButtonProperties(BTN_08_CLE_NAME, BTN_08_CLE_GUARD, BTN_08_CLE_LABEL, 0, null);

        // Details
        NFAL0220_ABMsgArray lineMsgArray = scrnMsg.A;
        if (lineMsgArray.getValidCount() > 0) {
            for (int i = 0; i < lineMsgArray.getValidCount(); i++) {
                scrnMsg.A.no(i).xxChkBox_A.setInputProtected(true);
                scrnMsg.A.no(i).xxScrItem61Txt_A.setInputProtected(true);
                scrnMsg.A.no(i).jrnlDealAmt_A1.setInputProtected(true);
                scrnMsg.A.no(i).jrnlDealAmt_A2.setInputProtected(true);
                scrnMsg.A.no(i).manJrnlLineTxt_A.setInputProtected(true);
                scrnMsg.A.no(i).dsAcctNum_A.setInputProtected(true);
                scrnMsg.A.no(i).serNum_A.setInputProtected(true);
                scrnMsg.A.no(i).jrnlEntrySrcTpCd_LS.setInputProtected(true);
                scrnMsg.A.no(i).jrnlEntrySrcValTxt_A.setInputProtected(true);

                //START 2017/03/15 E.Kameishi [QC#15854,MOD]
                handler.setButtonEnabled(A_BTN[0], true);
                //END 2017/03/15 E.Kameishi [QC#15854,MOD]
                handler.setButtonEnabled(C_BTN[0], false);
            }
        }
        scrnMsg.jrnlDealAmt_T1.setInputProtected(true);
        scrnMsg.jrnlDealAmt_T2.setInputProtected(true);
    }

    /**
     * allInputsProtectActive
     * @param handler S21CommonHandler
     * @param scrnMsg NFAL0220BMsg
     */
    public static void allInputsProtectActive(S21CommonHandler handler, NFAL0220BMsg scrnMsg) {

        // Header
        scrnMsg.manJrnlNm.setInputProtected(false);
        scrnMsg.glSendCpltFlg.setInputProtected(false);
        scrnMsg.manJrnlCpltFlg.setInputProtected(false);
        scrnMsg.manJrnlDescTxt.setInputProtected(false);
        scrnMsg.rsvdRvslDt.setInputProtected(false);
        scrnMsg.autoRvslFlg.setInputProtected(false);
        scrnMsg.rvslCpltFlg.setInputProtected(false);
        scrnMsg.xxChkBox_C.setInputProtected(false);
        scrnMsg.jrnlCatgCd.setInputProtected(false);
        scrnMsg.jrnlCatgDescTxt.setInputProtected(false);
        scrnMsg.glPerNm.setInputProtected(false);
        scrnMsg.glDt.setInputProtected(false);
        scrnMsg.xxChkBox_V.setInputProtected(false);

        handler.setButtonEnabled(CATG_BTN[0], true);
        handler.setButtonEnabled(COPY_JOURNAL[0], false);
        handler.setButtonEnabled(REVERSE[0], false);
        handler.setButtonEnabled(UPLOAD_FILE[0], true);
        handler.setButtonEnabled(ADD_LINE[0], true);
        handler.setButtonEnabled(DELETE[0], true);

        handler.setButtonProperties(BTN_02_SUB_NAME, BTN_02_SUB_GUARD, BTN_02_SUB_LABEL, 1, null);
        handler.setButtonProperties(BTN_08_CLE_NAME, BTN_08_CLE_GUARD, BTN_08_CLE_LABEL, 1, null);

        // Details
        NFAL0220_ABMsgArray lineMsgArray = scrnMsg.A;
        if (lineMsgArray.getValidCount() > 0) {
            for (int i = 0; i < lineMsgArray.getValidCount(); i++) {
                scrnMsg.A.no(i).xxChkBox_A.setInputProtected(false);
                scrnMsg.A.no(i).xxScrItem61Txt_A.setInputProtected(false);
                scrnMsg.A.no(i).jrnlDealAmt_A1.setInputProtected(false);
                scrnMsg.A.no(i).jrnlDealAmt_A2.setInputProtected(false);
                scrnMsg.A.no(i).manJrnlLineTxt_A.setInputProtected(false);
                scrnMsg.A.no(i).dsAcctNum_A.setInputProtected(false);
                scrnMsg.A.no(i).serNum_A.setInputProtected(false);
                scrnMsg.A.no(i).jrnlEntrySrcTpCd_LS.setInputProtected(false);
                scrnMsg.A.no(i).jrnlEntrySrcValTxt_A.setInputProtected(false);

                handler.setButtonEnabled(A_BTN[0], true);
                handler.setButtonEnabled(C_BTN[0], true);
            }
        }
    }

    /**
     * screenControlCopyJournal
     * @param handler S21CommonHandler
     * @param scrnMsg NFAL0220BMsg
     */
    public static void screenControlCopyJournal(S21CommonHandler handler, NFAL0220BMsg scrnMsg) {

        setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
        setGuiAttr(handler, scrnMsg);
        initAppFracDigit(scrnMsg);
    }

    /**
     * screenControlCopyJournal
     * @param handler S21CommonHandler
     * @param scrnMsg NFAL0220BMsg
     */
    public static void screenControlReverse(S21CommonHandler handler, NFAL0220BMsg scrnMsg) {

        setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
        setGuiAttr(handler, scrnMsg);
        initAppFracDigit(scrnMsg);
    }

    /**
     * screenControlCopyJournal
     * @param handler S21CommonHandler
     * @param scrnMsg NFAL0220BMsg
     */
    public static void screenControlClear(S21CommonHandler handler, NFAL0220BMsg scrnMsg) {

        initCmnBtnProp(handler);
        setGuiAttr(handler, scrnMsg);
    }

    /**
     * screenControlInsert
     * @param handler S21CommonHandler
     * @param scrnMsg NFAL0220BMsg
     */
    public static void screenControlAddLine(S21CommonHandler handler, NFAL0220BMsg scrnMsg) {

        setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
        scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.A.getValidCount()).xxChkBox_A);
    }

    /**
     * screenControlDownload
     * @param handler S21CommonHandler
     * @param scrnMsg NFAL0220BMsg
     */
    public static void screenControlDownload(S21CommonHandler handler, NFAL0220BMsg scrnMsg) {

        initCmnBtnProp(handler);
        setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
        scrnMsg.setFocusItem(scrnMsg.manJrnlNm);
    }

    /**
     * screenControlDelete
     * @param handler S21CommonHandler
     * @param scrnMsg NFAL0220BMsg
     */
    public static void screenControlDelete(S21CommonHandler handler, NFAL0220BMsg scrnMsg) {

        setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
    }

    /**
     * screenControlPage
     * @param handler S21CommonHandler
     * @param scrnMsg NFAL0220BMsg
     */
    public static void screenControlPage(S21CommonHandler handler, NFAL0220BMsg scrnMsg) {

        setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
        scrnMsg.setFocusItem(scrnMsg.A.no(0).xxChkBox_A);
    }

    /**
     * screenControlSubmit
     * @param handler S21CommonHandler
     * @param scrnMsg NFAL0220BMsg
     */
    public static void screenControlSubmit(S21CommonHandler handler, NFAL0220BMsg scrnMsg) {

        setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
        setGuiAttr(handler, scrnMsg);
        initAppFracDigit(scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.manJrnlNm);
    }

    /**
     * addCheckInputItem
     * @param scrnMsg NFAL0220BMsg
     */
    public static void addCheckInputItem(NFAL0220BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.manJrnlNm);
        scrnMsg.addCheckItem(scrnMsg.glSendCpltFlg);
        scrnMsg.addCheckItem(scrnMsg.manJrnlCpltFlg);
        scrnMsg.addCheckItem(scrnMsg.manJrnlDescTxt);
        scrnMsg.addCheckItem(scrnMsg.rsvdRvslDt);
        scrnMsg.addCheckItem(scrnMsg.autoRvslFlg);
        scrnMsg.addCheckItem(scrnMsg.rvslCpltFlg);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_C);
        scrnMsg.addCheckItem(scrnMsg.jrnlCatgCd);
        scrnMsg.addCheckItem(scrnMsg.jrnlCatgDescTxt);
        scrnMsg.addCheckItem(scrnMsg.glPerNm);
        scrnMsg.addCheckItem(scrnMsg.glDt);
        scrnMsg.addCheckItem(scrnMsg.xxFileData);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_V);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NFAL0220_ABMsg abMsg = scrnMsg.A.no(i);
            scrnMsg.addCheckItem(abMsg.xxChkBox_A);
            scrnMsg.addCheckItem(abMsg.xxScrItem61Txt_A);
            scrnMsg.addCheckItem(abMsg.jrnlDealAmt_A1);
            scrnMsg.addCheckItem(abMsg.jrnlDealAmt_A2);
            scrnMsg.addCheckItem(abMsg.manJrnlLineTxt_A);
            scrnMsg.addCheckItem(abMsg.dsAcctNum_A);
            scrnMsg.addCheckItem(abMsg.serNum_A);
            scrnMsg.addCheckItem(abMsg.jrnlEntrySrcTpCd_LS);
            scrnMsg.addCheckItem(abMsg.jrnlEntrySrcValTxt_A);
        }
    }

    /**
     * addCheckItem
     * @param scrnMsg NFAL0220BMsg
     */
    public static void addCheckItem(NFAL0220BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.manJrnlNm);
        scrnMsg.addCheckItem(scrnMsg.glSendCpltFlg);
        scrnMsg.addCheckItem(scrnMsg.manJrnlCpltFlg);
        scrnMsg.addCheckItem(scrnMsg.manJrnlDescTxt);
        scrnMsg.addCheckItem(scrnMsg.rsvdRvslDt);
        scrnMsg.addCheckItem(scrnMsg.autoRvslFlg);
        scrnMsg.addCheckItem(scrnMsg.rvslCpltFlg);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_C);
        scrnMsg.addCheckItem(scrnMsg.jrnlCatgCd);
        scrnMsg.addCheckItem(scrnMsg.jrnlCatgDescTxt);
        scrnMsg.addCheckItem(scrnMsg.glPerNm);
        scrnMsg.addCheckItem(scrnMsg.glDt);
        scrnMsg.addCheckItem(scrnMsg.xxFileData);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_V);
        scrnMsg.addCheckItem(scrnMsg.jrnlDealAmt_T1);
        scrnMsg.addCheckItem(scrnMsg.jrnlDealAmt_T2);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NFAL0220_ABMsg abMsg = scrnMsg.A.no(i);
            scrnMsg.addCheckItem(abMsg.xxChkBox_A);
            scrnMsg.addCheckItem(abMsg.xxScrItem61Txt_A);
            scrnMsg.addCheckItem(abMsg.jrnlDealAmt_A1);
            scrnMsg.addCheckItem(abMsg.jrnlDealAmt_A2);
            scrnMsg.addCheckItem(abMsg.manJrnlLineTxt_A);
            scrnMsg.addCheckItem(abMsg.dsAcctNum_A);
            scrnMsg.addCheckItem(abMsg.serNum_A);
            scrnMsg.addCheckItem(abMsg.jrnlEntrySrcTpCd_LS);
            scrnMsg.addCheckItem(abMsg.jrnlEntrySrcValTxt_A);
        }
    }

    /**
     * Set Item Visibility
     * @param scrnMsg NFAL0220BMsg
     * @param itemNm String
     * @param visibility boolean
     */
    private static void setVisibilityItem(NFAL0220BMsg scrnMsg, String itemId, boolean visible) {
        EZDGUIAttribute xxItem = new EZDGUIAttribute(SCRN_ID_00, itemId);
        xxItem.setVisibility(visible);
        scrnMsg.addGUIAttribute(xxItem);
    }

    /**
     * initAppFracDigit
     * @param scrnMsg NFAL0220BMsg
     */
    public static void initAppFracDigit(NFAL0220BMsg scrnMsg) {

        for (int index = 0; index < scrnMsg.A.getValidCount(); index++) {
            scrnMsg.A.no(index).jrnlDealAmt_A1.setAppFracDigit(2);
            scrnMsg.A.no(index).jrnlDealAmt_A2.setAppFracDigit(2);
        }
    }

    private static boolean editAccountingString(NFAL0220BMsg scrnMsg, int index) {

        String xxScrItem61Txt = scrnMsg.A.no(index).xxScrItem61Txt_A.getValue();
        String[] accStr = xxScrItem61Txt.split(STR_DOT);
        if (accStr.length != 9) {
            scrnMsg.A.no(index).xxScrItem61Txt_A.setErrorInfo(1, NFAM0159E);
            return false;
        }

        if (!checkLength(scrnMsg, accStr, index)) {
            // START 2016/08/10 J.Kim [QC#12851,MOD]
            // scrnMsg.A.no(index).xxScrItem61Txt_A.setErrorInfo(1, NFAM0043E, new String[] {"Accounting String" });
            // END 2016/08/10 J.Kim [QC#12851,MOD]
            return false;
        }

        // START 2016/08/02 K.Kojima [QC#12766,ADD]
        // ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm,
        // accStr[0]);
        // ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm,
        // accStr[1]);
        // ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(3).xxPopPrm,
        // accStr[2]);
        // ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(4).xxPopPrm,
        // accStr[3]);
        // ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(5).xxPopPrm,
        // accStr[4]);
        // ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(6).xxPopPrm,
        // accStr[5]);
        // ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(7).xxPopPrm,
        // accStr[6]);
        // ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(8).xxPopPrm,
        // accStr[7]);
        // ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(9).xxPopPrm,
        // accStr[8]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(PARAM_NUM_CMPY_CD).xxPopPrm, accStr[ARRAY_NUM_CMPY_CD]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(PARAM_NUM_BR_CD).xxPopPrm, accStr[ARRAY_NUM_BR_CD]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(PARAM_NUM_CC_CD).xxPopPrm, accStr[ARRAY_NUM_CC_CD]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(PARAM_NUM_ACCT_CD).xxPopPrm, accStr[ARRAY_NUM_ACCT_CD]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(PARAM_NUM_PROD_CD).xxPopPrm, accStr[ARRAY_NUM_PROD_CD]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(PARAM_NUM_CH_CD).xxPopPrm, accStr[ARRAY_NUM_CH_CD]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(PARAM_NUM_AFFL_CD).xxPopPrm, accStr[ARRAY_NUM_AFFL_CD]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(PARAM_NUM_PROJ_CD).xxPopPrm, accStr[ARRAY_NUM_PROJ_CD]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(PARAM_NUM_EXTN_CD).xxPopPrm, accStr[ARRAY_NUM_EXTN_CD]);
        // END 2016/08/02 K.Kojima [QC#12766,MOD]
        return true;
    }

    private static boolean checkLength(NFAL0220BMsg scrnMsg, String[] asStr, int index) {

        // START 2016/08/10 J.Kim [QC#12851,MOD]
        // int coaCmpyCdLen = scrnMsg.A.no(index).getAttr("coaCmpyCd_A").getDigit();
        // int coaBrCdLen = scrnMsg.A.no(index).getAttr("coaBrCd_A").getDigit();
        // int coaCcCdLen = scrnMsg.A.no(index).getAttr("coaCcCd_A").getDigit();
        // int coaAcctCdLen = scrnMsg.A.no(index).getAttr("coaAcctCd_A").getDigit();
        // int coaProdCdLen = scrnMsg.A.no(index).getAttr("coaProdCd_A").getDigit();
        // int coaChCdLen = scrnMsg.A.no(index).getAttr("coaChCd_A").getDigit();
        // int coaAfflCdLen = scrnMsg.A.no(index).getAttr("coaAfflCd_A").getDigit();
        // int coaProjCdLen = scrnMsg.A.no(index).getAttr("coaProjCd_A").getDigit();
        // int coaExtnCdLen = scrnMsg.A.no(index).getAttr("coaExtnCd_A").getDigit();
        // START 2016/08/10 J.Kim [QC#12851,MOD]
        // // START 2016/08/02 K.Kojima [QC#12766,ADD]
        // // if (asStr[0].length() <= coaCmpyCdLen && asStr[1].length()
        // // <= coaAfflCdLen && asStr[2].length() <= coaBrCdLen &&
        // // asStr[3].length() <= coaCcCdLen && asStr[4].length() <=
        // // coaAcctCdLen && asStr[5].length() <= coaProdCdLen
        // // && asStr[6].length() <= coaChCdLen && asStr[7].length() <=
        // // coaProjCdLen && asStr[8].length() <= coaExtnCdLen) {
        // // return true;
        // // }
        // if (asStr[ARRAY_NUM_CMPY_CD].length() <= coaCmpyCdLen && asStr[ARRAY_NUM_BR_CD].length() <= coaBrCdLen && asStr[ARRAY_NUM_CC_CD].length() <= coaCcCdLen && asStr[ARRAY_NUM_ACCT_CD].length() <= coaAcctCdLen
        //        && asStr[ARRAY_NUM_PROD_CD].length() <= coaProdCdLen && asStr[ARRAY_NUM_CH_CD].length() <= coaChCdLen && asStr[ARRAY_NUM_AFFL_CD].length() <= coaAfflCdLen && asStr[ARRAY_NUM_PROJ_CD].length() <= coaProjCdLen
        //        && asStr[ARRAY_NUM_EXTN_CD].length() <= coaExtnCdLen) {
        //    return true;
        // }
        // // END 2016/08/02 K.Kojima [QC#12766,MOD]

        if (COA_LENGTH[0] != asStr[ARRAY_NUM_CMPY_CD].length()) {
            scrnMsg.A.no(index).xxScrItem61Txt_A.setErrorInfo(1, NFAM0043E, new String[] {"Company format." });
            return false;
        }
        if (COA_LENGTH[1] != asStr[ARRAY_NUM_BR_CD].length()) {
            scrnMsg.A.no(index).xxScrItem61Txt_A.setErrorInfo(1, NFAM0043E, new String[] {"Branch format." });
            return false;
        }
        if (COA_LENGTH[2] != asStr[ARRAY_NUM_CC_CD].length()) {
            scrnMsg.A.no(index).xxScrItem61Txt_A.setErrorInfo(1, NFAM0043E, new String[] {"Cost Center format." });
            return false;
        }
        if (COA_LENGTH[3] != asStr[ARRAY_NUM_ACCT_CD].length()) {
            scrnMsg.A.no(index).xxScrItem61Txt_A.setErrorInfo(1, NFAM0043E, new String[] {"Account format." });
            return false;
        }
        if (COA_LENGTH[4] != asStr[ARRAY_NUM_PROD_CD].length()) {
            scrnMsg.A.no(index).xxScrItem61Txt_A.setErrorInfo(1, NFAM0043E, new String[] {"Product format." });
            return false;
        }
        if (COA_LENGTH[5] != asStr[ARRAY_NUM_CH_CD].length()) {
            scrnMsg.A.no(index).xxScrItem61Txt_A.setErrorInfo(1, NFAM0043E, new String[] {"Channel format." });
            return false;
        }
        if (COA_LENGTH[6] != asStr[ARRAY_NUM_AFFL_CD].length()) {
            scrnMsg.A.no(index).xxScrItem61Txt_A.setErrorInfo(1, NFAM0043E, new String[] {"Intercompany format." });
            return false;
        }
        if (COA_LENGTH[7] != asStr[ARRAY_NUM_PROJ_CD].length()) {
            scrnMsg.A.no(index).xxScrItem61Txt_A.setErrorInfo(1, NFAM0043E, new String[] {"Merchandise format." });
            return false;
        }
        if (COA_LENGTH[8] != asStr[ARRAY_NUM_EXTN_CD].length()) {
            scrnMsg.A.no(index).xxScrItem61Txt_A.setErrorInfo(1, NFAM0043E, new String[] {"Business Unit format." });
            return false;
        }
        return true;
        // END 2016/08/10 J.Kim [QC#12851,MOD]
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NFAL0220BMsg
     * @param lineNum int
     * @return Object[]
     */
    public static boolean checkSearchPopupScreen(NFAL0220BMsg scrnMsg, int lineNum) {

        scrnMsg.P.clear();
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(lineNum).xxScrItem61Txt_A)) {
            if (!editAccountingString(scrnMsg, lineNum)) {
                return false;
            }
        }
        return true;
    }

    /**
     * setParamNFAL0220
     * @param scrnMsg scrnMsg NFAL0220BMsg
     * @param index int
     */
    public static void setParamNMAL2550(NFAL0220BMsg scrnMsg, int index) {

        if (!ZYPCommonFunc.hasValue(scrnMsg.P.no(1).xxPopPrm)) {
            return;
        }

        StringBuffer sb = new StringBuffer();
        // START 2016/08/02 K.Kojima [QC#12766,ADD]
        // sb.append(scrnMsg.P.no(1).xxPopPrm.getValue());
        // sb.append(DOT);
        // sb.append(scrnMsg.P.no(2).xxPopPrm.getValue());
        // sb.append(DOT);
        // sb.append(scrnMsg.P.no(3).xxPopPrm.getValue());
        // sb.append(DOT);
        // sb.append(scrnMsg.P.no(4).xxPopPrm.getValue());
        // sb.append(DOT);
        // sb.append(scrnMsg.P.no(5).xxPopPrm.getValue());
        // sb.append(DOT);
        // sb.append(scrnMsg.P.no(6).xxPopPrm.getValue());
        // sb.append(DOT);
        // sb.append(scrnMsg.P.no(7).xxPopPrm.getValue());
        // sb.append(DOT);
        // sb.append(scrnMsg.P.no(8).xxPopPrm.getValue());
        // sb.append(DOT);
        // sb.append(scrnMsg.P.no(9).xxPopPrm.getValue());
        sb.append(scrnMsg.P.no(PARAM_NUM_CMPY_CD).xxPopPrm.getValue());
        sb.append(DOT);
        sb.append(scrnMsg.P.no(PARAM_NUM_BR_CD).xxPopPrm.getValue());
        sb.append(DOT);
        sb.append(scrnMsg.P.no(PARAM_NUM_CC_CD).xxPopPrm.getValue());
        sb.append(DOT);
        sb.append(scrnMsg.P.no(PARAM_NUM_ACCT_CD).xxPopPrm.getValue());
        sb.append(DOT);
        sb.append(scrnMsg.P.no(PARAM_NUM_PROD_CD).xxPopPrm.getValue());
        sb.append(DOT);
        sb.append(scrnMsg.P.no(PARAM_NUM_CH_CD).xxPopPrm.getValue());
        sb.append(DOT);
        sb.append(scrnMsg.P.no(PARAM_NUM_AFFL_CD).xxPopPrm.getValue());
        sb.append(DOT);
        sb.append(scrnMsg.P.no(PARAM_NUM_PROJ_CD).xxPopPrm.getValue());
        sb.append(DOT);
        sb.append(scrnMsg.P.no(PARAM_NUM_EXTN_CD).xxPopPrm.getValue());
        // END 2016/08/02 K.Kojima [QC#12766,MOD]

        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).xxScrItem61Txt_A, sb.toString());
        initAppFracDigit(scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.A.no(index).xxScrItem61Txt_A);
    }

}
