/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSAL0530.common;

import static business.servlet.NSAL0530.constant.NSAL0530Constant.*;

import java.util.Arrays;
import java.util.List;

import parts.common.EZDBItem;
import parts.common.EZDBMsgArray;
import parts.servletcommon.EZDCommonHandler;
import business.blap.NSAL0530.NSAL0530CMsg;
import business.servlet.NSAL0530.NSAL0530BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Solution Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/12   Hitachi         T.Tomita        Create          N/A
 * 2016/05/10   Hitachi         T.Tomita        Update          QC#7976
 *</pre>
 */
public class NSAL0530CommonLogic implements ZYPConstant {

    /**
     * createCMsgForSearch
     * @return NSAL0530CMsg
     */
    public static NSAL0530CMsg createCMsgForSearch() {
        NSAL0530CMsg bizMsg = new NSAL0530CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        return bizMsg;
    }

    /**
     * screenControlProcess
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0530BMsg
     */
    public static void screenControlProcess(EZDCommonHandler handler, NSAL0530BMsg scrnMsg) {
        initialize(handler, scrnMsg);
        protectFields(handler, scrnMsg);
    }

    /**
     * Initialize the items and buttons on the screen.
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0530BMsg
     */
    @SuppressWarnings("unchecked")
    private static void initialize(EZDCommonHandler handler, NSAL0530BMsg scrnMsg) {

        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
        scrnMsg.setInputProtected(false);

        // set button property
        // common button
        List<List<String>> cmnEnableButtons = Arrays.asList(SAVE, SUBMIT, APPLY, APPROVE, REJECT, DOWNLOAD, DELETE, CLEAR, RESET, RETURN);
        for (List<String> button : cmnEnableButtons) {
            handler.setButtonProperties(button.get(0), button.get(1), button.get(2), BTN_ACTIVE, null);
        }
        disableButtons(handler, SAVE, SUBMIT, APPLY, APPROVE, REJECT, DOWNLOAD, DELETE, RESET);

        // optional button
        List<List<String>> optButtons = Arrays.asList(SEARCH, SOLUTIONCREATENEW);
        for (List<String> button : optButtons) {
            handler.setButtonProperties(button.get(0), button.get(1), button.get(2), BTN_ACTIVE, null);
        }
    }

    /**
     * Control Inactive Field
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0530BMsg
     */
    public static void protectFields(EZDCommonHandler handler, NSAL0530BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            disableItems(scrnMsg.A.no(i).svcSlnNm_A, scrnMsg.A.no(i).xxLocNm_A1, scrnMsg.A.no(i).xxLocNm_A2, scrnMsg.A.no(i).xxLocNm_A3, scrnMsg.A.no(i).svcSlnCratPsnCd_A);
        }
    }

    /**
     * commonAddCheckItem
     * @param scrnMsg NSAL0530BMsg
     */
    public static void commonAddCheckItem(NSAL0530BMsg scrnMsg) {

        // START 2016/05/10 T.Tomita [QC#7976, MOD]
        scrnMsg.addCheckItem(scrnMsg.serNum_H);
        scrnMsg.addCheckItem(scrnMsg.xxScrItem29Txt_HS);
        scrnMsg.addCheckItem(scrnMsg.svcSlnNm_H);
        scrnMsg.addCheckItem(scrnMsg.xxScrItem29Txt_HC);
        scrnMsg.addCheckItem(scrnMsg.svcMachMstrPk_H);
        scrnMsg.addCheckItem(scrnMsg.xxLocNm_H1);
        scrnMsg.addCheckItem(scrnMsg.xxLocNm_H2);
        scrnMsg.addCheckItem(scrnMsg.xxLocNm_H3);
        scrnMsg.addCheckItem(scrnMsg.istlDt_H);
        scrnMsg.addCheckItem(scrnMsg.mdlNm_H);
        scrnMsg.addCheckItem(scrnMsg.mdseCd_H);
        scrnMsg.addCheckItem(scrnMsg.coaMdseTpCd_HS);
        scrnMsg.addCheckItem(scrnMsg.cpoOrdNum_H);
        scrnMsg.addCheckItem(scrnMsg.custMachCtrlNum_H);
        scrnMsg.addCheckItem(scrnMsg.custIssPoNum_H);
        scrnMsg.addCheckItem(scrnMsg.dsContrNum_H);
        // END 2016/05/10 T.Tomita [QC#7976, MOD]
    }

    /**
     * setRowColors
     * @param scrnMsg NSAL0530BMsg
     */
    public static void setRowColors(NSAL0530BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        try {
            EZDBMsgArray table = (EZDBMsgArray) scrnMsg.getClass().getField("A").get(scrnMsg);
            tblColor.setAlternateRowsBG("A", table);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException(e);
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
}
