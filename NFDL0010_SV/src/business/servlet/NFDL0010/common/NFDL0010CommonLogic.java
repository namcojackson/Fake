package business.servlet.NFDL0010.common;

import parts.servletcommon.EZDCommonHandler;
import business.blap.NFDL0010.NFDL0010CMsg;
import business.servlet.NFDL0010.NFDL0010BMsg;
import business.servlet.NFDL0010.constant.NFDL0010Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_DISP_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;

/**
 *<pre>
 * Collection Summary
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 06/20/2016   Hitachi         J.Kim           Update          QC#10327
 * 2016/06/29   Hitachi         K.Kojima        Update          QC#10166
 * 2016/07/28   Hitachi         K.Kojima        Update          QC#10260
 * 2017/07/31   Hitachi         T.Tsuchida      Update          QC#19574
 * 2018/05/16   CITS            S.Katsuma       Update          QC#24793
 * 2018/07/30   Fujitsu         Y.Matsui        Update          QC#27081
 *</pre>
 */
public class NFDL0010CommonLogic implements NFDL0010Constant {

    /**
     * @return bizMsg NFDL0010CMsg
     */
    public static NFDL0010CMsg setRequestData_NFDL0010_INIT() {

        NFDL0010CMsg bizMsg;

        bizMsg = new NFDL0010CMsg();
        bizMsg.setBusinessID("NFDL0010");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @return bizMsg NFDL0010CMsg
     */
    public static NFDL0010CMsg setRequestData_NFDL0010Scrn00_Select() {

        NFDL0010CMsg bizMsg;

        bizMsg = new NFDL0010CMsg();
        bizMsg.setBusinessID("NFDL0010");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @return bizMsg NFDL0010CMsg
     */
    public static NFDL0010CMsg setRequestData_NFDL0010Scrn00_TBLColumnSort() {

        NFDL0010CMsg bizMsg;

        bizMsg = new NFDL0010CMsg();
        bizMsg.setBusinessID("NFDL0010");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @return bizMsg NFDL0010CMsg
     */
    public static NFDL0010CMsg setRequestData_NFDL0010Scrn00_CMN_Reset() {

        NFDL0010CMsg bizMsg;

        bizMsg = new NFDL0010CMsg();
        bizMsg.setBusinessID("NFDL0010");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @return bizMsg NFDL0010CMsg
     */
    public static NFDL0010CMsg setRequestData_NFDL0010Scrn00_PagePrev() {

        NFDL0010CMsg bizMsg;

        bizMsg = new NFDL0010CMsg();
        bizMsg.setBusinessID("NFDL0010");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @return bizMsg NFDL0010CMsg
     */
    public static NFDL0010CMsg setRequestData_NFDL0010Scrn00_PageNext() {

        NFDL0010CMsg bizMsg;

        bizMsg = new NFDL0010CMsg();
        bizMsg.setBusinessID("NFDL0010");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    // START 2017/07/31 T.Tsuchida [QC#19574,MOD]
    /**
     * @param scrnMsg NFDL0010BMsg
     */
    public static void checkInput_NFDL0010Scrn00_Search(NFDL0010BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.cltDispTpCd_H);
        scrnMsg.addCheckItem(scrnMsg.xxQueryFltrTxt_H1);
        scrnMsg.addCheckItem(scrnMsg.xxQueryFltrTxt_H2);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm_H);
        scrnMsg.addCheckItem(scrnMsg.cltPsnNm_H1); // Add 2018/02/20 S21_NA#21175
        scrnMsg.addCheckItem(scrnMsg.cltPtfoNm_H);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_H);
    }
    // END 2017/07/31 T.Tsuchida [QC#19574,MOD]

    /**
     * @param scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_NFDL0010(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn2", "", "Submit", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn6", "", "Download", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        // START 2018/05/16 S.Katsuma [QC#24793,MOD]
//        scrnAppli.setButtonProperties("btn8", "", "Clear", 0, null);
        scrnAppli.setButtonProperties("btn8", "CMN_Reset", "Clear", 1, null);
        // START 2018/05/16 S.Katsuma [QC#24793,MOD]

        scrnAppli.setButtonConfirmMsg("CMN_Reset", "ZZM8102I", new String[] {"Reset" }, 0);
        scrnAppli.setButtonConfirmMsg("CMN_Return", "NZZM0004W", null, 0);
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFDL0010BMsg
     */
    public static void initialize(EZDCommonHandler scrnAppli, NFDL0010BMsg scrnMsg) {

        scrnMsg.setInputProtected(false);

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
        scrnAppli.setButtonProperties("btn8", "CMN_Reset", "Clear", 1, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
        scrnAppli.setButtonProperties("btn10", "CMN_Return", "Return", 1, null);
        onChange_Display(scrnMsg);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).pmtTermDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsoAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).cltPsnNm_A1.setInputProtected(true);
            // START 2017/07/31 T.Tsuchida [QC#19574,ADD]
            scrnMsg.A.no(i).cltPtfoNm_A1.setInputProtected(true);
            // END 2017/07/31 T.Tsuchida [QC#19574,ADD]
            scrnMsg.A.no(i).dsoAmt_A2.setInputProtected(true);
            scrnMsg.A.no(i).totBalAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).netAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).dealCltDsptAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).dealCltPrmsAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).cltPrmsCratDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).cltPrmsDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).cltPrmsBrknFlg_A1.setInputProtected(true);
            // START 2016/06/29 K.Kojima [QC#10166,ADD]
            scrnMsg.A.no(i).xxFullNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsCtacPntValTxt_AT.setInputProtected(true);
            scrnMsg.A.no(i).dsCtacPntValTxt_AE.setInputProtected(true);
            // START 2017/07/31 T.Tsuchida [QC#19574,MOD]
            //scrnMsg.A.no(i).dtlNoteTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).cltHdrNoteTxt_A1.setInputProtected(true);
            // END 2017/07/31 T.Tsuchida [QC#19574,MOD]
            // END 2016/06/29 K.Kojima [QC#10166,ADD]
            scrnMsg.A.no(i).xxScrItem30Txt_A1.setInputProtected(true);
            scrnMsg.A.no(i).cltStrgyNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).cltWrkItemNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).rcptDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).dealRcptAmt_A1.setInputProtected(true);
            // START 2018/07/30 Y.Matsui [QC#27081,ADD]
            scrnMsg.A.no(i).cratTsDplyTxt_A1.setInputProtected(true);
            // END   2018/07/30 Y.Matsui [QC#27081,ADD]

        }

        if (scrnMsg.A.getValidCount() > 0) {
            scrnAppli.setButtonEnabled("OpenWin_Payment", true);
        }

        // START 2016/07/28 K.Kojima [QC#10260,DEL]
        // if (!scrnMsg.cltDispTpCd_H.getValue().equals("01")) {
        // scrnMsg.xxQueryFltrTxt_L1.setInputProtected(true);
        // } else {
        // scrnMsg.xxQueryFltrTxt_L1.setInputProtected(false);
        // }
        // END 2016/07/28 K.Kojima [QC#10260,DEL]
        // START 2016/07/28 K.Kojima [QC#10260,ADD]
        if (scrnMsg.cltDispTpCd_H.getValue().equals(CLT_DISP_TP.SELF)) {
            scrnMsg.xxQueryFltrTxt_L1.clear();
            scrnMsg.xxQueryFltrTxt_L1.setInputProtected(true);
        } else {
            scrnMsg.xxQueryFltrTxt_L1.setInputProtected(false);
            scrnMsg.xxQueryFltrTxt_L1.setValue(ZYPConstant.FLG_ON_1);
        }
        // END 2016/07/28 K.Kojima [QC#10260,ADD]
    }

    /**
     * @param scrnMsg NFDL0010BMsg
     */
    public static void controlTableBegin_NFDL0010Scrn00_A(NFDL0010BMsg scrnMsg) {
        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();
    }

    /**
     * @param scrnMsg NFDL0010BMsg
     */
    public static void controlTablePrev_NFDL0010Scrn00_A(NFDL0010BMsg scrnMsg) {
        scrnMsg.xxPageShowFromNum.setValue(scrnMsg.xxPageShowFromNum.getValueInt() - scrnMsg.A.length() - 1);
    }

    /**
     * @param scrnMsg NFDL0010BMsg
     */
    public static void controlTableNext_NFDL0010Scrn00_A(NFDL0010BMsg scrnMsg) {
        scrnMsg.xxPageShowFromNum.setValue(scrnMsg.xxPageShowToNum.getValueInt());
    }

    /**
     * @param scrnMsg NFDL0010BMsg
     */
    public static void clearScrnBackgroundColor(NFDL0010BMsg scrnMsg) {
        scrnMsg.clearAllGUIAttribute("NFDL0010Scrn00");
    }

    // START 2017/07/31 T.Tsuchida [QC#19574,MOD]
    public static void onChange_Display(NFDL0010BMsg scrnMsg) {
        if (scrnMsg.cltDispTpCd_H.getValue().equals(CLT_DISP_TP.SELF)) {
            scrnMsg.xxQueryFltrTxt_H1.clear();
            scrnMsg.xxQueryFltrTxt_H1.setInputProtected(true);
            scrnMsg.xxQueryFltrTxt_L1.clear();
            scrnMsg.xxQueryFltrTxt_L1.setInputProtected(true);
        } else {
            scrnMsg.xxQueryFltrTxt_H1.setInputProtected(false);
            scrnMsg.xxQueryFltrTxt_L1.setInputProtected(false);
            scrnMsg.xxQueryFltrTxt_L1.setValue(ZYPConstant.FLG_ON_1);
        }
    }
    // END 2017/07/31 T.Tsuchida [QC#19574,MOD]
}
