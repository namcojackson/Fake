/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2080.common;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NFBL2080.NFBL2080BMsg;
import business.servlet.NFBL2080.constant.NFBL2080Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_INV_PROC_STS;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/** 
 *<pre>
 * AP Invoice I/F Error Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/29   CSAI            Y.Miyauchi      Create          N/A
 * 2016/08/01   Fujitsu         S.Yoshida       Update          QC#12575
 * 2016/08/04   Fujitsu         C.Tanaka        Update          QC#12632
 * 2016/12/22   Fujitsu         H.Ikeda         Update          QC#12865
 * 2018/12/18   Hitachi         J.Kim           Update          QC#29631
 * 2019/01/09   Fujitsu         Y.Matsui        Update          QC#29884
 * 2019/04/17   Fujitsu         H.Ikeda         Update          QC#31166
 * 2019/05/20   Hitachi         H.Umeda         Update          QC#50204
 * 2019/10/17   Hitachi         Y.Takeno        Update          QC#54123
 *</pre>
 */
public class NFBL2080CommonLogic implements NFBL2080Constant {

	/**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL2660BMsg
     */
    public static void initialize(EZDCommonHandler scrnAppli, NFBL2080BMsg scrnMsg, String scrnEvent) {

        if ( scrnEvent.equals(SCRN_EVENT.NFBL2080_INIT.name()) ) {
            scrnAppli.setButtonProperties("Check_All", "Check_All", "CheckAll", 1, null);
            scrnAppli.setButtonProperties("Un_Check_All", "Un_Check_All", "UncheckAll", 1, null);
            scrnAppli.setButtonProperties("Click_Search", "Click_Search", "Search", 1, null);
            //Mod Start QC#12632
            scrnAppli.setButtonProperties("OpenWin_Mdse", "OpenWin_Mdse", "I", 1, null);
            //Mod End QC#12632
            scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
            // Start 2016/12/22 H.Ikeda [QC#12865,MOD]
            if (hasUpdateFuncId(scrnMsg)) {
                scrnAppli.setButtonProperties("btn2", "CMN_Submit", "Submit", 1, null);
            } else {
                scrnAppli.setButtonProperties("btn2", "CMN_Submit", "Submit", 0, null);
            }
            // End  2016/12/22 H.Ikeda [QC#12865,MOD]
            scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
            scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
            scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
            // START 2018/10/12 J.Kim [QC#28723,MOD]
            // scrnAppli.setButtonProperties("btn6", "", "Download", 0, null);
            scrnAppli.setButtonProperties("btn6", "CMN_Download", "Download", 1, null);
            // END 2018/10/12 J.Kim [QC#28723,MOD]
            scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
            scrnAppli.setButtonProperties("btn8", "", "Clear", 0, null);
            scrnAppli.setButtonProperties("btn9", "CMN_Reset", "Reset", 1, null);
            scrnAppli.setButtonProperties("btn10", "CMN_Return", "Return", 1, null);
        }

        clearVisibility(scrnMsg);

    }

    /**
     * @param scrnMsg NFCL2660BMsg
     */
    private static void clearVisibility(NFBL2080BMsg scrnMsg) {

        EZDGUIAttribute xxbtn1 = new EZDGUIAttribute("NFCL2660Scrn00", "btn1");
        xxbtn1.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn1);

        EZDGUIAttribute xxbtn2 = new EZDGUIAttribute("NFCL2660Scrn00", "btn2");
        xxbtn2.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn2);

        EZDGUIAttribute xxbtn3 = new EZDGUIAttribute("NFCL2660Scrn00", "btn3");
        xxbtn3.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn3);

        EZDGUIAttribute xxbtn4 = new EZDGUIAttribute("NFCL2660Scrn00", "btn4");
        xxbtn4.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn4);

        EZDGUIAttribute xxbtn5 = new EZDGUIAttribute("NFCL2660Scrn00", "btn5");
        xxbtn5.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn5);

        EZDGUIAttribute xxbtn6 = new EZDGUIAttribute("NFCL2660Scrn00", "btn6");
        xxbtn6.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn6);

        EZDGUIAttribute xxbtn7 = new EZDGUIAttribute("NFCL2660Scrn00", "btn7");
        xxbtn7.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn7);

        EZDGUIAttribute xxbtn8 = new EZDGUIAttribute("NFCL2660Scrn00", "btn8");
        xxbtn8.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn8);

        EZDGUIAttribute xxbtn9 = new EZDGUIAttribute("NFCL2660Scrn00", "btn9");
        xxbtn9.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn9);

        EZDGUIAttribute xxbtn10 = new EZDGUIAttribute("NFCL2660Scrn00", "btn10");
        xxbtn10.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn10);

    }

    /**
     * check vendor invoice List
     * @param scrnMsg
     * @return
     */
    public static boolean checkVndInvList(NFBL2080BMsg scrnMsg) {
        //
        for ( int iCnt=0; iCnt<scrnMsg.A.getValidCount(); iCnt++ ){
            if (scrnMsg.A.no(iCnt).xxChkBox_A1.getValue().equals(FLG.Y.name())) {
                 return TRUE;
            }
        }
        scrnMsg.setMessageInfo(MSG_ID.NLAM0023E.name());

        return FALSE;
    }
    /**
     * Check VND_INV_PROC_STS_CD on detail records.
     * @param scrnMsg NFBL2080BMsg
     * @param eventNm String
     * @param vndInvProcStsCd String
     * @param vndInvProcStsNm String
     * @return boolean
     */
    public static boolean checkDetailVndInvProcStsCd(NFBL2080BMsg scrnMsg, String eventNm, String vndInvProcStsCd, String vndInvProcStsNm) {
        //
        for ( int iCnt=0; iCnt<scrnMsg.A.getValidCount(); iCnt++ ){
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(iCnt).xxChkBox_A1.getValue())
            &&  ZYPCommonFunc.hasValue(scrnMsg.A.no(iCnt).vndInvProcStsCd_A1.getValue())
            &&  scrnMsg.A.no(iCnt).xxChkBox_A1.getValue().equals(FLG.Y.name())
            && !scrnMsg.A.no(iCnt).vndInvProcStsCd_A1.getValue().equals(vndInvProcStsCd)) {
                scrnMsg.setMessageInfo(MSG_ID.NFBM0216E.name(), new String[] {eventNm, vndInvProcStsNm});
                return FALSE;
            }
        }
        return TRUE;
    }
    /**
     * @param scrnMsg NFCL2080BMsg
     * @return Object[]
     */
    public static Object[] otherBusConnectTo_NFCL2080Scrn00_OpenWin_Mdse(NFBL2080BMsg scrnMsg, int iCnt) {

        scrnMsg.P.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm_P1, scrnMsg.P.no(iCnt).xxPopPrm_P1);
        // START 2019/10/17 [QC#54123, MOD]
        // ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(9).xxPopPrm_P1, MDSE_CD_TARGET_ALL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(9).xxPopPrm_P1, MDSE_CD_TARGET_10_DIGIT);
        // END   2019/10/17 [QC#54123, MOD]

        Object[] params = new Object[10];
        params[0] = scrnMsg.P.no(0).xxPopPrm_P1;
        params[1] = scrnMsg.P.no(1).xxPopPrm_P1;
        params[2] = scrnMsg.P.no(2).xxPopPrm_P1;
        params[3] = scrnMsg.P.no(3).xxPopPrm_P1;
        params[4] = scrnMsg.P.no(4).xxPopPrm_P1;
        params[5] = scrnMsg.P.no(5).xxPopPrm_P1;
        params[6] = scrnMsg.P.no(6).xxPopPrm_P1;
        params[7] = scrnMsg.P.no(7).xxPopPrm_P1;
        params[8] = scrnMsg.P.no(8).xxPopPrm_P1;
        params[9] = scrnMsg.P.no(9).xxPopPrm_P1;

        return params;

    }

    /**
     * 
     * @param scrnMsg
     */
    public static void setHyoSettings(NFBL2080BMsg scrnMsg, S21CommonHandler handler) {

        // START 2019/01/09 Y.Matsui [QC#29884,MOD]
        for(int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).locNm_A1.setIndispensable(false);
            scrnMsg.A.no(i).locNm_A1.setInputProtected(true);
//Mod Start QC#12575
            scrnMsg.A.no(i).poOrdNum_A1.setIndispensable(false);
            scrnMsg.A.no(i).poOrdNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).ediPoOrdNum_A1.setIndispensable(false);
            scrnMsg.A.no(i).ediPoOrdNum_A1.setInputProtected(true);
//Mod End   QC#12575
            scrnMsg.A.no(i).batErrMsgTxt_AP.setIndispensable(false);
            scrnMsg.A.no(i).batErrMsgTxt_AP.setInputProtected(true);
        }

        scrnMsg.locNm_H1.setIndispensable(false);
        scrnMsg.locNm_H1.setInputProtected(true);
        scrnMsg.vndInvProcStsCd_H1.setIndispensable(false);
        scrnMsg.vndInvProcStsCd_H1.setInputProtected(true);
        scrnMsg.vndInvNum_H1.setIndispensable(false);
        scrnMsg.vndInvNum_H1.setInputProtected(true);
        scrnMsg.batErrMsgTxt_HP.setIndispensable(false);
        scrnMsg.batErrMsgTxt_HP.setInputProtected(true);
        // START 2018/12/18 J.Kim [QC#29631,ADD]
        boolean isEditable = false;
        String procStsCd = scrnMsg.vndInvProcStsCd_H1.getValue();
        if (VND_INV_PROC_STS.ERROR.equals(procStsCd) || VND_INV_PROC_STS.SAVED.equals(procStsCd)) {
            isEditable = true;
        }
        // END 2018/12/18 J.Kim [QC#29631,ADD]

        if (isEditable) {
//Mod Start QC#12575
// START 2019/05/20 H.Umeda [QC#50204,MOD]
//          scrnMsg.poOrdNum_H1.setIndispensable(false);
//          scrnMsg.poOrdNum_H1.setInputProtected(false);
//          scrnMsg.ediPoOrdNum_H1.setIndispensable(false);
//          scrnMsg.ediPoOrdNum_H1.setInputProtected(false);
            if(ZYPCommonFunc.hasValue(scrnMsg.xxInvCratFlg_H1)){
                scrnMsg.poOrdNum_H1.setIndispensable(false);
                scrnMsg.poOrdNum_H1.setInputProtected(true);
                scrnMsg.ediPoOrdNum_H1.setIndispensable(false);
                scrnMsg.ediPoOrdNum_H1.setInputProtected(true);
                scrnMsg.custIssPoNum_H1.setIndispensable(false);
                scrnMsg.custIssPoNum_H1.setInputProtected(false);
            }
            else{
                scrnMsg.poOrdNum_H1.setIndispensable(false);
                scrnMsg.poOrdNum_H1.setInputProtected(false);
                scrnMsg.ediPoOrdNum_H1.setIndispensable(false);
                scrnMsg.ediPoOrdNum_H1.setInputProtected(false);
                scrnMsg.custIssPoNum_H1.setIndispensable(false);
                scrnMsg.custIssPoNum_H1.setInputProtected(true);
            }
// END   2019/05/20 H.Umeda [QC#50204,MOD]
//Mod End   QC#12575
            // START 2019/04/17 H.Ikeda [QC#31166, DEL]
            //scrnMsg.soNum_H1.setInputProtected(false);
            //scrnMsg.soNum_H1.setIndispensable(false);
            //scrnMsg.custIssPoNum_H1.setIndispensable(false);
            //scrnMsg.custIssPoNum_H1.setInputProtected(false);
            // END T 2019/04/17 H.Ikeda [QC#31166, DEL]
        } else {
//Mod Start QC#12575
            scrnMsg.poOrdNum_H1.setIndispensable(false);
            scrnMsg.poOrdNum_H1.setInputProtected(true);
            scrnMsg.ediPoOrdNum_H1.setIndispensable(false);
            scrnMsg.ediPoOrdNum_H1.setInputProtected(true);
//Mod End   QC#12575
            // START 2019/04/17 H.Ikeda [QC#31166, DEL]
            //scrnMsg.soNum_H1.setIndispensable(false);
            //scrnMsg.soNum_H1.setInputProtected(true);
            //scrnMsg.custIssPoNum_H1.setIndispensable(false);
            //scrnMsg.custIssPoNum_H1.setInputProtected(true);
            // END T 2019/04/17 H.Ikeda [QC#31166, DEL]
// START 2019/05/20 H.Umeda [QC#50204,ADD]
            scrnMsg.custIssPoNum_H1.setIndispensable(false);
            scrnMsg.custIssPoNum_H1.setInputProtected(true);
// END   2019/05/20 H.Umeda [QC#50204,ADD]
        }
        // START 2019/04/17 H.Ikeda [QC#31166, ADD]
        scrnMsg.soNum_H1.setIndispensable(false);
        scrnMsg.soNum_H1.setInputProtected(true);
// START 2019/05/20 H.Umeda [QC#50204,DEL]
//      scrnMsg.custIssPoNum_H1.setIndispensable(false);
//      scrnMsg.custIssPoNum_H1.setInputProtected(true);
// END   2019/05/20 H.Umeda [QC#50204,DEL]
        // START 2019/04/17 H.Ikeda [QC#31166, ADD]
        scrnMsg.xxCratDt_H1.setIndispensable(false);
        scrnMsg.xxCratDt_H1.setInputProtected(true);

        for(int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.B.no(i).mdseCd_B1.setIndispensable(false);
            scrnMsg.B.no(i).mdseCd_B1.setInputProtected(true);
            scrnMsg.B.no(i).mdseNm_B1.setIndispensable(false);
            scrnMsg.B.no(i).mdseNm_B1.setInputProtected(true);
            scrnMsg.B.no(i).batErrMsgTxt_B1.setIndispensable(false);
            scrnMsg.B.no(i).batErrMsgTxt_B1.setInputProtected(true);
            // START 2019/04/18 H.Ikeda [QC#31166, ADD]
            scrnMsg.B.no(i).ediPoOrdDtlLineNum_B1.setIndispensable(false);
            // END   2019/04/18 H.Ikeda [QC#31166, ADD]
            // START 2018/12/18 J.Kim [QC#29631,ADD]
            if (isEditable) {
                scrnMsg.B.no(i).shipQty_B1.setIndispensable(true);
                scrnMsg.B.no(i).shipQty_B1.setInputProtected(false);
                // START 2019/04/18 H.Ikeda [QC#31166, ADD]
                scrnMsg.B.no(i).ediPoOrdDtlLineNum_B1.setInputProtected(false);
                // END   2019/04/18 H.Ikeda [QC#31166, ADD]
                handler.setButtonEnabled("OpenWin_Mdse", true);
            } else {
                scrnMsg.B.no(i).shipQty_B1.setIndispensable(false);
                scrnMsg.B.no(i).shipQty_B1.setInputProtected(true);
                // START 2019/04/18 H.Ikeda [QC#31166, ADD]
                scrnMsg.B.no(i).ediPoOrdDtlLineNum_B1.setInputProtected(true);
                // END   2019/04/18 H.Ikeda [QC#31166, ADD]
                handler.setButtonEnabled("OpenWin_Mdse", false);
            }
            // END 2018/12/18 J.Kim [QC#29631,ADD]
        }

        // START 2018/12/18 J.Kim [QC#29631,ADD]
        if (isEditable) {
            handler.setButtonProperties("btn2", "CMN_Submit", "Submit", 1, null);
        } else {
            handler.setButtonProperties("btn2", "CMN_Submit", "Submit", 0, null);
        }

        if (scrnMsg.A.getValidCount() > 0) {
            handler.setButtonProperties("btn6", "CMN_Download", "Download", 1, null);
        } else {
            handler.setButtonProperties("btn6", "CMN_Download", "Download", 0, null);
        }
        // END 2018/12/18 J.Kim [QC#29631,ADD]
        // END 2019/01/09 Y.Matsui [QC#29884,MOD]

        initAppFracDigit(scrnMsg);
    }

    /**
     * Method name: initAppFracDigit
     * <dd>The method explanation: Init App Frac Digit.
     * <dd>Remarks:
     * @param bMsg EZDBMsg
     */
    public static void initAppFracDigit(EZDBMsg bMsg) {

        NFBL2080BMsg scrnMsg = (NFBL2080BMsg) bMsg;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).invTotDealNetAmt_A1.setAppFracDigit(FRAC_DIGIT_2);
        }
        // Detail tab
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.B.no(i).dealGrsTotPrcAmt_B1.setAppFracDigit(FRAC_DIGIT_2);
        }
    }

    // Start 2016/12/21 H.Ikeda [QC#12865,ADD]
    /**
     * hasUpdateFuncId
     * @return boolean
     */
    private static boolean hasUpdateFuncId(NFBL2080BMsg scrnMsg) {
        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();

        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(BIZ_ID);
        if (funcList == null || funcList.isEmpty()) {
            scrnMsg.setMessageInfo(MSG_ID.ZZSM4300E.name(), new String[] {userProfSvc.getContextUserInfo().getUserId()});
            return false;
        }
        if (funcList.contains(FUNC_ID_UPDATE)) {
            return true;
        }
        return false;
    }
    // End  2016/12/21 H.Ikeda [QC#12865,ADD]
}