package business.servlet.NFDL0110.common;

import parts.servletcommon.EZDCommonHandler;
import business.blap.NFDL0110.NFDL0110CMsg;
import business.servlet.NFDL0110.NFDL0110BMsg;
import business.servlet.NFDL0110.constant.NFDL0110Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Collection Summary
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 2017/08/07   Hitachi         T.Tsuchida      Update          QC#19576
 * 2023/02/13   Hitachi         E.Watabe        Update          QC#61092
 *</pre>
 */
public class NFDL0110CommonLogic implements NFDL0110Constant{

    /**
     * @return bizMsg NFDL0110CMsg
     */
    public static NFDL0110CMsg setRequestData_NFDL0110_INIT() {

        NFDL0110CMsg bizMsg;

        bizMsg = new NFDL0110CMsg();
        bizMsg.setBusinessID("NFDL0110");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @return bizMsg NFDL0110CMsg
     */
    public static NFDL0110CMsg setRequestData_NFDL0110Scrn00_Select() {

        NFDL0110CMsg bizMsg;

        bizMsg = new NFDL0110CMsg();
        bizMsg.setBusinessID("NFDL0110");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @return bizMsg NFDL0110CMsg
     */
    public static NFDL0110CMsg setRequestData_NFDL0110Scrn00_TBLColumnSort() {

        NFDL0110CMsg bizMsg;

        bizMsg = new NFDL0110CMsg();
        bizMsg.setBusinessID("NFDL0110");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @return bizMsg NFDL0110CMsg
     */
    public static NFDL0110CMsg setRequestData_NFDL0110Scrn00_CMN_Reset() {

        NFDL0110CMsg bizMsg;

        bizMsg = new NFDL0110CMsg();
        bizMsg.setBusinessID("NFDL0110");
        bizMsg.setFunctionCode("20");

        return bizMsg;
     }

    /**
     * @return bizMsg NFDL0110CMsg
     */
    public static NFDL0110CMsg setRequestData_NFDL0110Scrn00_PagePrev() {

        NFDL0110CMsg bizMsg;

        bizMsg = new NFDL0110CMsg();
        bizMsg.setBusinessID("NFDL0110");
        bizMsg.setFunctionCode("20");

       return bizMsg;
    }

    /**
     * @return bizMsg NFDL0110CMsg
     */
    public static NFDL0110CMsg setRequestData_NFDL0110Scrn00_PageNext() {

        NFDL0110CMsg bizMsg;

        bizMsg = new NFDL0110CMsg();
        bizMsg.setBusinessID("NFDL0110");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    // START 2017/08/07 T.Tsuchida [QC#19576,MOD]
    /**
     * initialize
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg   NFDL0110BMsg
     */
    public static void initialize(EZDCommonHandler scrnAppli, NFDL0110BMsg scrnMsg) {

        scrnMsg.setInputProtected(false);

        initCommonButton(scrnAppli, scrnMsg);
        controlScreenHeaderFields(scrnMsg);
        if (scrnMsg.A.getValidCount() > 0) {
            controlScreenDetailFields(scrnMsg);
        }
        setRowColors(scrnMsg);
    }

    /**
     * initCommonButton
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFDL0110BMsg
     */
    public static final void initCommonButton(EZDCommonHandler scrnAppli, NFDL0110BMsg scrnMsg) {
        scrnAppli.setButtonProperties("OnClick_Search", "OnClick_Search", "Search", 1, null);
        scrnAppli.setButtonProperties("OpenWin_Payment", "OpenWin_Payment", "Payment", 0, null);
        scrnAppli.setButtonProperties("OnClick_Prev", "OnClick_Prev", "Prev", 1, null);
        scrnAppli.setButtonProperties("OnClick_Next", "OnClick_Next", "Next", 1, null);
        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn2", "", "Submit", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn6", "", "Download", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn8", "", "Clear", 0, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
        scrnAppli.setButtonProperties("btn10", "CMN_Return", "Return", 1, null);
        // START 2023/02/13 E.Watabe[QC#61092, ADD]
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxBtnFlg.getValue())) {
            scrnAppli.setButtonEnabled("OnClick_Create", true);
        } else {
            scrnAppli.setButtonEnabled("OnClick_Create", false);
        }
        // END 2023/02/13 E.Watabe[QC#61092, ADD]
    }

    /**
     * controlScreenHeaderFields
     * @param scrnMsg NFDL0110BMsg
     */
    private static final void controlScreenHeaderFields(NFDL0110BMsg scrnMsg) {
        scrnMsg.billToCustAcctCd_H.setInputProtected(true);
        scrnMsg.dsAcctNm_H.setInputProtected(true);
        scrnMsg.billToCustCd_H.setInputProtected(true);
        scrnMsg.locNm_H.setInputProtected(true);
    }

    /**
     * controlScreenDetailFields
     * @param scrnMsg NFDL0110BMsg
     */
    private static final void controlScreenDetailFields(NFDL0110BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).billToCustCd_A.setInputProtected(true);
            scrnMsg.A.no(i).xxDplyTrxNumTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).arTrxNum_A.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_A1.setInputProtected(true);
            scrnMsg.A.no(i).bllgPerFromDt_A.setInputProtected(true);
            scrnMsg.A.no(i).bllgPerToDt_A.setInputProtected(true);
            scrnMsg.A.no(i).dealOrigGrsAmt_A.setInputProtected(true);
            scrnMsg.A.no(i).dealRmngBalGrsAmt_A.setInputProtected(true);
            scrnMsg.A.no(i).invDueDt_A.setInputProtected(true);
            scrnMsg.A.no(i).pmtLateDaysAot_A.setInputProtected(true);
            scrnMsg.A.no(i).custCareTktNum_A.setInputProtected(true);
            scrnMsg.A.no(i).xxCratDt_A.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_A2.setInputProtected(true);
        }
    }

    /**
     * setRowColors
     * @param scrnMsg NFDL0110BMsg
     */
    private static void setRowColors(NFDL0110BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG(TABLE_A, scrnMsg.A);
        if (scrnMsg.A.getValidCount() > 0) {
            tblColor.setAlternateRowsBG(TABLE_A, scrnMsg.A);
        }
    }

    /**
     * controlTableBegin_NFDL0110Scrn00_A
     * @param scrnMsg   NFDL0110BMsg
     */
    public static void controlTableBegin_NFDL0110Scrn00_A(NFDL0110BMsg scrnMsg) {
        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum_A.clear();
        scrnMsg.xxPageShowToNum_A.clear();
        scrnMsg.xxPageShowOfNum_A.clear();
    }

    /**
     * controlTablePrev_NFDL0110Scrn00_A
     * @param scrnMsg   NFDL0110BMsg
     */
    public static void controlTablePrev_NFDL0110Scrn00_A(NFDL0110BMsg scrnMsg) {
        scrnMsg.xxPageShowFromNum_A.setValue(scrnMsg.xxPageShowFromNum_A.getValueInt() - scrnMsg.A.length() - 1);
    }

    /**
     * controlTableNext_NFDL0110Scrn00_A
     * @param scrnMsg   NFDL0110BMsg
     */
    public static void controlTableNext_NFDL0110Scrn00_A(NFDL0110BMsg scrnMsg) {
        scrnMsg.xxPageShowFromNum_A.setValue(scrnMsg.xxPageShowToNum_A.getValueInt());
    }
    // END 2017/08/07 T.Tsuchida [QC#19576,MOD]
}
