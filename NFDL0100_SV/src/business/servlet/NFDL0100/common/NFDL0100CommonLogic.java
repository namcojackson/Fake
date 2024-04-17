package business.servlet.NFDL0100.common;

import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDCommonHandler;
import business.blap.NFDL0100.NFDL0100CMsg;
import business.servlet.NFDL0100.NFDL0100BMsg;
import business.servlet.NFDL0100.constant.NFDL0100Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;

/** 
 *<pre>
 * Print Invoice
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/14   CSAI            K.Lee           Create          N/A
 * 2016/08/01   Hitachi         K.Kojima        Update          QC#11570
 * 2022/08/03   CITS            D.Mamaril       Update          QC#60294
 * 2023/03/07   Hitachi         S.Fujita        Update          QC#61169
 * 2023/04/07   Hitachi         S.Nakatani      Update          QC#61270
 *</pre>
 */
public class NFDL0100CommonLogic implements NFDL0100Constant{

    /**
     * @return bizMsg NFDL0100CMsg
     */
    public static NFDL0100CMsg setRequestData_NFDL0100_INIT() {

        NFDL0100CMsg bizMsg;

        bizMsg = new NFDL0100CMsg();
        bizMsg.setBusinessID("NFDL0100");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @return bizMsg NFDL0100CMsg
     */
    public static NFDL0100CMsg setRequestData_NFDL0100Scrn00_Select() {

        NFDL0100CMsg bizMsg;

        bizMsg = new NFDL0100CMsg();
        bizMsg.setBusinessID("NFDL0100");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @return bizMsg NFDL0100CMsg
     */
    public static NFDL0100CMsg setRequestData_NFDL0100Scrn00_TBLColumnSort() {

        NFDL0100CMsg bizMsg;

        bizMsg = new NFDL0100CMsg();
        bizMsg.setBusinessID("NFDL0100");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @return bizMsg NFDL0100CMsg
     */
    public static NFDL0100CMsg setRequestData_NFDL0100Scrn00_CMN_Reset() {

        NFDL0100CMsg bizMsg;

        bizMsg = new NFDL0100CMsg();
        bizMsg.setBusinessID("NFDL0100");
        bizMsg.setFunctionCode("20");

        return bizMsg;
     }

    /**
     * @return bizMsg NFDL0100CMsg
     */
    public static NFDL0100CMsg setRequestData_NFDL0100Scrn00_PagePrev() {

        NFDL0100CMsg bizMsg;

        bizMsg = new NFDL0100CMsg();
        bizMsg.setBusinessID("NFDL0100");
        bizMsg.setFunctionCode("20");

       return bizMsg;
    }

    /**
     * @return bizMsg NFDL0100CMsg
     */
    public static NFDL0100CMsg setRequestData_NFDL0100Scrn00_PageNext() {

        NFDL0100CMsg bizMsg;

        bizMsg = new NFDL0100CMsg();
        bizMsg.setBusinessID("NFDL0100");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param  scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_NFDL0100(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn2", "", "Submit", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn6", "", "Download", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn8", "", "Clear", 0, null);

        scrnAppli.setButtonConfirmMsg("CMN_Reset", "ZZM8102I", new String[]{"Reset"}, 0);
        scrnAppli.setButtonConfirmMsg("CMN_Return", "NZZM0004W", null, 0);
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg   NFDL0100BMsg
     */
    public static void initialize(EZDCommonHandler scrnAppli, NFDL0100BMsg scrnMsg) {

        scrnMsg.setInputProtected(false);

        scrnAppli.setButtonProperties("Click_Btn_Refresh", "Click_Btn_Refresh", "Refresh", 1, null);
        scrnAppli.setButtonProperties("Click_Btn_Email", "Click_Btn_Email", "Email", 1, null);
        scrnAppli.setButtonProperties("Click_Btn_SelectAll", "Click_Btn_SelectAll", "Select All", 1, null);
        scrnAppli.setButtonProperties("Click_Btn_UnselectAll", "Click_Btn_UnselectAll", "UnSelect All", 1, null);
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

        scrnMsg.billToCustAcctCd_H.setInputProtected(true);
        scrnMsg.dsAcctNm_H.setInputProtected(true);
        scrnMsg.billToCustCd_H.setInputProtected(true);
        scrnMsg.locNm_H.setInputProtected(true);

        // START 2016/08/01 K.Kojima [QC#11570,ADD]
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).billToCustCd_A.setInputProtected(true);
            scrnMsg.A.no(i).xxDplyTrxNumTxt_A.setInputProtected(true);
            // START 2023/03/07 S.Fujita [QC#61169, MOD]
            // scrnMsg.A.no(i).arTrxNum_A.setInputProtected(true);
            scrnMsg.A.no(i).arCustRefNum_A.setInputProtected(true);
            // START 2023/03/07 S.Fujita [QC#61169, MOD]
            scrnMsg.A.no(i).grpInvNum_A.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_A.setInputProtected(true);
            scrnMsg.A.no(i).dealOrigGrsAmt_A.setInputProtected(true);
            scrnMsg.A.no(i).dealRmngBalGrsAmt_A.setInputProtected(true);
            scrnMsg.A.no(i).arTrxDt_A.setInputProtected(true);
            scrnMsg.A.no(i).invDueDt_A.setInputProtected(true);
            // START 2022/08/03 D.Mamaril [QC#60294, MOD]
            //scrnMsg.A.no(i).pmtLateDaysAot_A.setInputProtected(true);
            scrnMsg.A.no(i).daysPastDueAot_A.setInputProtected(true);
            // END 2022/08/03 D.Mamaril [QC#60294, MOD]
            scrnMsg.A.no(i).custIssPoNum_A.setInputProtected(true);
        }
        // END 2016/08/01 K.Kojima [QC#11570,ADD]
    }

    /**
     * @param scrnMsg   NFDL0100BMsg
     */
    public static void controlTableBegin_NFDL0100Scrn00_A(NFDL0100BMsg scrnMsg) {
        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum_A.clear();
        scrnMsg.xxPageShowToNum_A.clear();
        scrnMsg.xxPageShowOfNum_A.clear();
    }

    /**
     * @param scrnMsg   NFDL0100BMsg
     */
    public static void controlTablePrev_NFDL0100Scrn00_A(NFDL0100BMsg scrnMsg) {
        scrnMsg.xxPageShowFromNum_A.setValue(scrnMsg.xxPageShowFromNum_A.getValueInt() - scrnMsg.A.length() - 1);
    }

    /**
     * @param scrnMsg   NFDL0100BMsg
     */
    public static void controlTableNext_NFDL0100Scrn00_A(NFDL0100BMsg scrnMsg) {
        scrnMsg.xxPageShowFromNum_A.setValue(scrnMsg.xxPageShowToNum_A.getValueInt());
    }

    /**
     * @param scrnMsg  NFDL0100BMsg
     */
    public static void clearScrnBackgroundColor(NFDL0100BMsg scrnMsg) {
        scrnMsg.clearAllGUIAttribute("NFDL0100Scrn00");
    }

    // START 2016/08/01 K.Kojima [QC#11570,DEL]
//    public static void setTableColor(NFDL0100BMsg scrnMsg) {
//        S21TableColorController tblColor = new S21TableColorController("NFDL0100Scrn00", scrnMsg);
//        tblColor.setAlternateRowsBG(TABLE_A, scrnMsg.A);
//
//        for (int i = 0; i < scrnMsg.A.length(); i++) {
//            if (i < scrnMsg.A.getValidCount()) {
//                String color = "";
//                if (i % 2 == 0) {
//                    color = "#FFFAFA";
//                } else {
//                    color = "#F0F0F0";
//                }
//
//                addGUIBackgroundColor(scrnMsg, "xxChkBox_A#" + i, color);
//                addGUIBackgroundColor(scrnMsg, "billToCustCd_A#" + i, color);
//                addGUIBackgroundColor(scrnMsg, "xxDplyTrxNumTxt_A#" + i, color);
//                addGUIBackgroundColor(scrnMsg, "arTrxNum_A#" + i, color);
//                addGUIBackgroundColor(scrnMsg, "grpInvNum_A#" + i, color);
//                addGUIBackgroundColor(scrnMsg, "xxScrItem130Txt_A#" + i, color);
//                addGUIBackgroundColor(scrnMsg, "dealOrigGrsAmt_A#" + i, color);
//                addGUIBackgroundColor(scrnMsg, "dealRmngBalGrsAmt_A#" + i, color);
//                addGUIBackgroundColor(scrnMsg, "arTrxDt_A#" + i, color);
//                addGUIBackgroundColor(scrnMsg, "invDueDt_A#" + i, color);
//                addGUIBackgroundColor(scrnMsg, "pmtLateDaysAot_A#" + i, color);
//                addGUIBackgroundColor(scrnMsg, "custIssPoNum_A#" + i, color);
//
//            } else {
//                clearGUIAttribute(scrnMsg, "xxChkBox_A#" + i);
//                clearGUIAttribute(scrnMsg, "billToCustCd_A#" + i);
//                clearGUIAttribute(scrnMsg, "xxDplyTrxNumTxt_A#" + i);
//                clearGUIAttribute(scrnMsg, "arTrxNum_A#" + i);
//                clearGUIAttribute(scrnMsg, "grpInvNum_A#" + i);
//                clearGUIAttribute(scrnMsg, "xxScrItem130Txt_A#" + i);
//                clearGUIAttribute(scrnMsg, "dealOrigGrsAmt_A#" + i);
//                clearGUIAttribute(scrnMsg, "dealRmngBalGrsAmt_A#" + i);
//                clearGUIAttribute(scrnMsg, "arTrxDt_A#" + i);
//                clearGUIAttribute(scrnMsg, "invDueDt_A#" + i);
//                clearGUIAttribute(scrnMsg, "pmtLateDaysAot_A#" + i);
//                clearGUIAttribute(scrnMsg, "custIssPoNum_A#" + i);
//            }
//        }
//    }
    // END 2016/08/01 K.Kojima [QC#11570,DEL]

    private static void addGUIBackgroundColor(NFDL0100BMsg scrnMsg, String id, String color) {
        EZDGUIAttribute attr = new EZDGUIAttribute("NFDL0100Scrn00", id);
        attr.setStyleAttribute("background-color", color);
        scrnMsg.addGUIAttribute(attr);
    }

    private static void clearGUIAttribute(NFDL0100BMsg scrnMsg, String id) {
        scrnMsg.clearGUIAttribute("NFDL0100Scrn00", id);
    }

    /**
     * Set Item Visibility
     * @param scrnMsg NFDL0100BMsg
     * @param itemNm String
     * @param visibility boolean
     */
    public static void setItemVisibility(NFDL0100BMsg scrnMsg, String itemNm, boolean visibility) {
        EZDGUIAttribute itemVisibility = new EZDGUIAttribute("NFDL0100Scrn00", itemNm);
        itemVisibility.setVisibility(visibility);
        scrnMsg.addGUIAttribute(itemVisibility);
    }

    /**
     * set Parameter for Mail Entry
     * @param scrnMsg NFDL0100BMsg
     * @return Parameter for NMAL6050
     */
    public static Object[] getParamMailEntry(NFDL0100BMsg scrnMsg) {
        // START 2023/04/07 S.Nakatani [QC#61270,MOD]
//        Object[] params = new Object[6];
        Object[] params = new Object[7];
        // END 2023/04/07 S.Nakatani [QC#61270,MOD]
        params[0] = scrnMsg.B;
        params[1] = scrnMsg.xxSfxKeyTxt.getValue();
        scrnMsg.scrInvEmlTs.clear();
        scrnMsg.scrInvEmlAddr.clear();
        scrnMsg.scrInvEmlSubjTxt.clear();
        scrnMsg.scrInvEmlCmntTxt.clear();
        params[2] = scrnMsg.scrInvEmlTs;
        params[3] = scrnMsg.scrInvEmlAddr;
        params[4] = scrnMsg.scrInvEmlSubjTxt;
        params[5] = scrnMsg.scrInvEmlCmntTxt;
        // START 2023/04/07 S.Nakatani [QC#61270,ADD]
        params[6] = ZYPConstant.FLG_ON_Y;
        // END 2023/04/07 S.Nakatani [QC#61270,ADD]
        return params;
    }
}
