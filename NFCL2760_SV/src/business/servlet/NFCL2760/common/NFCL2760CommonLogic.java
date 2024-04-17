package business.servlet.NFCL2760.common;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import parts.common.EZDBStringItem;
import parts.common.EZDCBigDecimalItemArray;
import parts.common.EZDCStringItemArray;
import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDCommonHandler;
import business.blap.NFCL2760.NFCL2760CMsg;
import business.servlet.NFCL2760.NFCL2760BMsg;
import business.servlet.NFCL2760.NFCL2760_ABMsg;
import business.servlet.NFCL2760.constant.NFCL2760Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/27   Hitachi         K.Kojima        Update          QC#6461
 * 2016/08/04   Hitachi         K.Kojima        Update          QC#6466
 * 2016/12/08   Hitachi         E.Kameishi      Update          QC#16286
 * 2016/12/26   Fujitsu         H.Ikeda         Update          QC#12865
 * 2017/01/11   Fujitsu         T.Murai         Update          QC#16952
 * 2017/11/16   Fujitsu         M.Ohno          Update          QC#21402
 * 2017/12/07   Fujitsu         M.Ohno          Update          QC#21402-2
 * 2018/01/11   Fujitsu         T.Murai         Update          QC#23134
 * 2018/01/16   Fujitsu         T.Murai         Update          QC#20563
 * 2018/03/28   Fujitsu         H.Ikeda         Update          QC#21738
 * 2018/10/11   Fujitsu         T.Ogura         Update          QC#28168
 * 2018/10/24   Fujitsu         T.Ogura         Update          QC#28168-1
 * 2020/01/23   Fujitsu         H.Ikeda         Update          QC#54902
 * 2021/06/21   CITS            H.Dimay         Update          QC#58639
 * 2022/01/26   Hitachi         A.Kohinata      Update          QC#56155
 * 2022/04/22   CITS            D.Mamaril       Update          QC#59333
 * 2022/11/28	Hitachi			R.Takau			Update			QC#57252
 * 2023/06/13   Hitachi         S.Fujita        Update          QC#61487
 * </pre>
 */public class NFCL2760CommonLogic implements NFCL2760Constant {

    /**
     * @param scrnMsg NFCL2760BMsg
     * @return bizMsg NFCL2760BMsg
     */
    public static NFCL2760CMsg setRequestData_NFCL2760_INIT(NFCL2760BMsg scrnMsg) {

        NFCL2760CMsg bizMsg;

        bizMsg = new NFCL2760CMsg();
        bizMsg.setBusinessID("NFCL2760");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param params Object[]
     * @param scrnMsg NFCL2760BMsg
     */
    public static void otherBusConnectFrom_NFCL2760_INIT(Object[] params, NFCL2760BMsg scrnMsg) {

        scrnMsg.rcptNum.setValue(((EZDBStringItem) params[1]).getValue());
        scrnMsg.xxModeInd_H1.setValue(((EZDBStringItem) params[0]).getValue());

        scrnMsg.xxPgFlg.setValue(ZYPConstant.FLG_OFF_N);
    }

    /**
     * @param scrnMsg NFCL2760BMsg
     */
    public static void transMsgCheck(NFCL2760BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.xxModeNm23Txt);
        scrnMsg.addCheckItem(scrnMsg.rcptNum);
        scrnMsg.addCheckItem(scrnMsg.rcptDt);
        scrnMsg.addCheckItem(scrnMsg.glDt_H1);
        scrnMsg.addCheckItem(scrnMsg.arRcptTrxTpCd);
        scrnMsg.addCheckItem(scrnMsg.arRcptTrxTpNm);
        scrnMsg.addCheckItem(scrnMsg.arRcptTpCd);
        scrnMsg.addCheckItem(scrnMsg.arRcptTpNm);
        scrnMsg.addCheckItem(scrnMsg.rcptBatNum);
        scrnMsg.addCheckItem(scrnMsg.payerCustCd);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm);
        scrnMsg.addCheckItem(scrnMsg.ccyCd);

        scrnMsg.addCheckItem(scrnMsg.dealRcptAmt);
        scrnMsg.addCheckItem(scrnMsg.dealApplyAmt_H1);
        scrnMsg.addCheckItem(scrnMsg.dealApplyAdjAmt_H1);
        scrnMsg.addCheckItem(scrnMsg.dealRcptRmngBalAmt);
        scrnMsg.addCheckItem(scrnMsg.dealRfAmt);
        scrnMsg.addCheckItem(scrnMsg.applyAmt_3);
        scrnMsg.addCheckItem(scrnMsg.applyAmt_2);
        scrnMsg.addCheckItem(scrnMsg.dealCashDiscAmt);
        scrnMsg.addCheckItem(scrnMsg.trxApplyAdjAmt);
        scrnMsg.addCheckItem(scrnMsg.trxApplyGrsAmt);
        scrnMsg.addCheckItem(scrnMsg.applyAmt_1);
        scrnMsg.addCheckItem(scrnMsg.xxOnAcctAmt);
        scrnMsg.addCheckItem(scrnMsg.xxDedctAmt);
        scrnMsg.addCheckItem(scrnMsg.xxApplyGrsAmt);
        scrnMsg.addCheckItem(scrnMsg.xxBalApplyGrsAmt);

        scrnMsg.addCheckItem(scrnMsg.glblCmpyCd_H1);
        scrnMsg.addCheckItem(scrnMsg.xxModeInd_H1);
        scrnMsg.addCheckItem(scrnMsg.rcptTrxBalPk_H1);
        scrnMsg.addCheckItem(scrnMsg.rcptHdrLastUpdTs_H1);
        scrnMsg.addCheckItem(scrnMsg.rcptHdrTz_H1);
        scrnMsg.addCheckItem(scrnMsg.rcptTrxBalLastUpdTs_H1);
        scrnMsg.addCheckItem(scrnMsg.rcptTrxBalTz_H1);
        scrnMsg.addCheckItem(scrnMsg.voidFlg_H1);
        scrnMsg.addCheckItem(scrnMsg.xxRsltStsCd_H1);
        scrnMsg.A.setCheckParam(new String[] {"xxChkBox", "arCustRefNum", "arTrxTpCd", "arTrxDt", "dealRmngBalGrsAmt", "xxDealApplyAmtNum_A1", "xxDtlDiffAmt_A1", "dealCashDiscAmt_A1", "cashDiscPct_A1", "pmtTermCd_A1", "cashDiscTermCd_A1",
                "arAdjTpCd_A1", "xxDealApplyAdjAmtNum_A1", "tocCd_A1", "coaProdCd_BK", "dealOrigGrsAmt", "dealApplyGrsAmt", "dealApplyCrAmt", "dealNetSlsAmt", "dealFrtAmt", "dealTaxAmt", "cashAppGlDt_A1", "glDt_A1", "invDueDt",
                "billToCustCd", "grpInvNum", "cpoOrdNum", "custIssPoNum", "arTrxNum", "xxModeInd_BK", "applyGrpKey_BK", "applyGrpSubPk_BK", "arTrxBalPk_BK", "arCashDiscSchdSqNum_BK", "invTrxBalPk_BK", "invTrxBalLastUpdTs_BK",
                "invTrxBalTz_BK", "crNum_BK", "crTrxBalPk_BK", "crTrxBalLastUpdTs_BK", "crTrxBalTz_BK", "arAdjNum_BK", "arAdjTrxTpCd_BK", "arAdjTpCd_BK", "adjCmntTxt_BK", "dealApplyAmt_BK", "dealApplyAdjAmt_BK", "arCashAppPk_BK",
                "arCashApplyStsCd_BK", "arAdjTpCd_A3", "dealApplyAdjAmt_A3", "arCashAppSqNum_UP", "ezUpTime_UP", "ezUpTimeZone_UP", "dealApplyAdjAmt_B3", "arAdjTpNm_A1", "arAdjTpNm_A3", "arAdjTpNm_BK" }, 1);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxPgFlg_A1.getValue())) {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxDealApplyAmtNum_A1);
            }
        }

        scrnMsg.addCheckItem(scrnMsg.A);
        scrnMsg.addCheckItem(scrnMsg.xxModeInd_P2);
        scrnMsg.addCheckItem(scrnMsg.arTrxBalPk_P2);
        scrnMsg.addCheckItem(scrnMsg.arCashDiscSchdSqNum_P2);
        scrnMsg.addCheckItem(scrnMsg.cashDiscPct_P2);
        scrnMsg.addCheckItem(scrnMsg.dealCashDiscAmt_P2);
        scrnMsg.addCheckItem(scrnMsg.payerCustCd_P3);
        scrnMsg.addCheckItem(scrnMsg.ccyCd_P3);
        scrnMsg.addCheckItem(scrnMsg.arTrxNum_P3);
        scrnMsg.addCheckItem(scrnMsg.arTrxTpCd_P3);
        scrnMsg.addCheckItem(scrnMsg.arTrxBalPk_P3);
        scrnMsg.addCheckItem(scrnMsg.upldCsvId_P4);
        scrnMsg.addCheckItem(scrnMsg.xxFileData);
        scrnMsg.addCheckItem(scrnMsg.xxSelNum);
        scrnMsg.addCheckItem(scrnMsg.xxPageShowFromNum);
        scrnMsg.addCheckItem(scrnMsg.xxPageShowToNum);
        scrnMsg.addCheckItem(scrnMsg.xxPageShowOfNum);
        scrnMsg.addCheckItem(scrnMsg.acctYrMth);
        scrnMsg.addCheckItem(scrnMsg.acctDt);

        // START 2020/01/23 H.Ikeda [QC#54902, ADD]
        scrnMsg.addCheckItem(scrnMsg.arCustRefNum_H1);
        // END   2020/01/23 H.Ikeda [QC#54902, ADD]
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL2760BMsg
     */
    public static void initialize(EZDCommonHandler scrnAppli, NFCL2760BMsg scrnMsg) {

        scrnMsg.setInputProtected(false);
        scrnMsg.dsAcctNm.setInputProtected(true);
        // START 2017/01/11 [QC#16952,ADD]
        for (int i = 0 ; i < scrnMsg.A.length(); i ++) {
            scrnMsg.A.no(i).arAdjTpNm_A3.setInputProtected(true);
        }
        // END   2017/01/11 [QC#16952,ADD]

        scrnAppli.setButtonProperties("OpenWin_Detail", "OpenWin_Detail", "Detail", 1, null);
        scrnAppli.setButtonProperties("CalcGrossAmount", "CalcGrossAmount", "Calculation", 1, null);
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.exptFlg_H1.getValue())) {
            scrnAppli.setButtonProperties("OpenWin_Select", "OpenWin_Select", "CD", 0, null);
        } else {
            scrnAppli.setButtonProperties("OpenWin_Select", "OpenWin_Select", "CD", 1, null);
        }
        // START 2016/07/27 K.Kojima [QC#6461,MOD]
        // scrnAppli.setButtonProperties("OpenWin_New", "OpenWin_New",
        // "On ACC & ADJ", 1, null);
        // START 2023/06/13 S.Fujita [QC#61487,MOD]
        // scrnAppli.setButtonProperties("OpenWin_New", "OpenWin_New", "On Acc & Adj", 1, null);
        if (isCollectionReps(scrnMsg)) {
            scrnAppli.setButtonProperties("OpenWin_New", "OpenWin_New", "On Acc & Adj", 0, null);
        } else {
            scrnAppli.setButtonProperties("OpenWin_New", "OpenWin_New", "On Acc & Adj", 1, null);
        }
        // END 2023/06/13 S.Fujita [QC#61487,MOD]
        // END 2016/07/27 K.Kojima [QC#6461,MOD]
        scrnAppli.setButtonProperties("OpenWin_Search", "OpenWin_Search", "Search TRX", 1, null);
        scrnAppli.setButtonProperties("DeleteLines", "DeleteLines", "Delete", 1, null);
        scrnAppli.setButtonProperties("DownloadCSV", "DownloadCSV", "Download", 1, null);
        scrnAppli.setButtonProperties("OpenWin_Upload", "OpenWin_Upload", "Upload", 1, null);
        scrnAppli.setButtonProperties("OpenWin_MailEntry", "OpenWin_MailEntry", "Mail Entry", 0, null);
        scrnAppli.setButtonProperties("Check_All", "Check_All", "CheckAll", 1, null);
        scrnAppli.setButtonProperties("Un_Check_All", "Un_Check_All", "UncheckAll", 1, null);
        // Start 2016/12/26 H.Ikeda [QC#12865,MOD]
        if (hasUpdateFuncId(scrnMsg)) {
            scrnAppli.setButtonProperties("btn1", "", "Save", 1, null);
            scrnAppli.setButtonProperties("btn2", "CMN_Submit", "Submit", 1, null);
        } else {
            scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
            scrnAppli.setButtonProperties("btn2", "CMN_Submit", "Submit", 0, null);
        }
        // End   2016/12/26 H.Ikeda [QC#12865,MOD]
        scrnAppli.setButtonProperties("btn3", "", "Apply", 1, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 1, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 1, null);
        // START 2016/08/04 K.Kojima [QC#6466,MOD]
        // scrnAppli.setButtonProperties("btn6", "", "Download", 1,
        // null);
        scrnAppli.setButtonProperties("btn6", "DownloadCSV", "Download", 1, null);
        // END 2016/08/04 K.Kojima [QC#6466,MOD]
        scrnAppli.setButtonProperties("btn7", "", "Delete", 1, null);
        scrnAppli.setButtonProperties("btn8", "", "Clear", 1, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 1, null);
        scrnAppli.setButtonProperties("btn10", "CMN_Return", "Return", 1, null);
        scrnAppli.setButtonProperties("btn11", "AddOneLine", "Add (F11)", 1, null);

        // START 2021/06/21 H.Dimay [QC#58639, DEL]
        // START 2016/12/08 E.Kameishi [QC#16286,ADD]
        //if (scrnMsg.arRcptStsCd_H1.getValue().equals(AR_RCPT_STS.REFUND)) {
        //    scrnAppli.setButtonEnabled("btn2", false);            
        //    scrnAppli.setButtonEnabled("btn11", false);
        //    scrnAppli.setButtonEnabled("OpenWin_New", false);
        //} else {
        // END 2021/06/21 H.Dimay [QC#58639, DEL]
        if (hasUpdateFuncId(scrnMsg)) {
            scrnAppli.setButtonEnabled("btn2", true);
        } else {
            scrnAppli.setButtonEnabled("btn2", false);
        }
        scrnAppli.setButtonEnabled("btn11", true);
        // START 2023/06/13 S.Fujita [QC#61487,DEL]
        // scrnAppli.setButtonEnabled("OpenWin_New", true);
        // END 2023/06/13 S.Fujita [QC#61487,DEL]
        // START 2021/06/21 H.Dimay [QC#58639, DEL]
        //}
        // END 2021/06/21 H.Dimay [QC#58639, DEL]
        // END 2016/12/08 E.Kameishi [QC#16286,ADD]
        clearVisibility(scrnMsg);
        // add start 2022/01/26 QC#56155
        scrnAppli.setButtonConfirmMsgEx("CMN_Return", "NZZM0004W", null, 1);
        // add end 2022/01/26 QC#56155

    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL2760BMsg
     */
    public static void commonBtnControl_NFCL2760_INIT(EZDCommonHandler scrnAppli, NFCL2760BMsg scrnMsg) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        // START 2016/08/04 K.Kojima [QC#6466,DEL]
        // scrnAppli.setButtonProperties("btn6", "", "Download", 0,
        // null);
        // END 2016/08/04 K.Kojima [QC#6466,DEL]
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn8", "", "Clear", 0, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL2760BMsg
     */
    public static void busBtnControl_NFCL2760_INIT(EZDCommonHandler scrnAppli, NFCL2760BMsg scrnMsg) {

        scrnAppli.setButtonEnabled("OpenWin_Select", false);
    }

    /**
     * @param scrnMsg NFCL2760BMsg
     */
    private static void clearVisibility(NFCL2760BMsg scrnMsg) {

        EZDGUIAttribute xxbtn1 = new EZDGUIAttribute("NFCL2760Scrn00", "btn1");
        xxbtn1.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn1);

        EZDGUIAttribute xxbtn2 = new EZDGUIAttribute("NFCL2760Scrn00", "btn2");
        xxbtn2.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn2);

        EZDGUIAttribute xxbtn3 = new EZDGUIAttribute("NFCL2760Scrn00", "btn3");
        xxbtn3.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn3);

        EZDGUIAttribute xxbtn4 = new EZDGUIAttribute("NFCL2760Scrn00", "btn4");
        xxbtn4.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn4);

        EZDGUIAttribute xxbtn5 = new EZDGUIAttribute("NFCL2760Scrn00", "btn5");
        xxbtn5.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn5);

        EZDGUIAttribute xxbtn6 = new EZDGUIAttribute("NFCL2760Scrn00", "btn6");
        xxbtn6.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn6);

        EZDGUIAttribute xxbtn7 = new EZDGUIAttribute("NFCL2760Scrn00", "btn7");
        xxbtn7.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn7);

        EZDGUIAttribute xxbtn8 = new EZDGUIAttribute("NFCL2760Scrn00", "btn8");
        xxbtn8.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn8);

        EZDGUIAttribute xxbtn9 = new EZDGUIAttribute("NFCL2760Scrn00", "btn9");
        xxbtn9.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn9);

        EZDGUIAttribute xxbtn10 = new EZDGUIAttribute("NFCL2760Scrn00", "btn10");
        xxbtn10.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn10);
        
        EZDGUIAttribute xxbtn11 = new EZDGUIAttribute("NFCL2760Scrn00", "btn11");
        xxbtn11.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn11);

    }

    /**
     * @param scrnMsg NFCL2760BMsg
     * @return bizMsg NFCL2760CMsg
     */
    public static NFCL2760CMsg setRequestData_NFCL2760Scrn00_CalcGrossAmount(NFCL2760BMsg scrnMsg) {

        NFCL2760CMsg bizMsg;

        bizMsg = new NFCL2760CMsg();
        bizMsg.setBusinessID("NFCL2760");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL2760BMsg
     */
    public static void commonBtnControl_NFCL2760Scrn00_CalcGrossAmount(EZDCommonHandler scrnAppli, NFCL2760BMsg scrnMsg) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        // START 2016/08/04 K.Kojima [QC#6466,DEL]
        // scrnAppli.setButtonProperties("btn6", "", "Download", 0,
        // null);
        // END 2016/08/04 K.Kojima [QC#6466,DEL]
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn8", "", "Clear", 0, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL2760BMsg
     */
    public static void commonBtnControl_NFCL2760Scrn00_OpenWin_Detail(EZDCommonHandler scrnAppli, NFCL2760BMsg scrnMsg) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        // START 2016/08/04 K.Kojima [QC#6466,DEL]
        // scrnAppli.setButtonProperties("btn6", "", "Download", 0,
        // null);
        // END 2016/08/04 K.Kojima [QC#6466,DEL]
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn8", "", "Clear", 0, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
    }

    /**
     * @param scrnMsg NFCL2760BMsg
     * @return Object[]
     */
    public static Object[] otherBusConnectTo_NFCL2760Scrn00_OpenWin_Detail(NFCL2760BMsg scrnMsg) {

        Object[] params = new Object[1];
        params[0] = scrnMsg.rcptNum;

        return params;

    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL2760BMsg
     */
    public static void commonBtnControl_NFCL2760Scrn00_OpenWin_New(EZDCommonHandler scrnAppli, NFCL2760BMsg scrnMsg) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        // START 2016/08/04 K.Kojima [QC#6466,DEL]
        // scrnAppli.setButtonProperties("btn6", "", "Download", 0,
        // null);
        // END 2016/08/04 K.Kojima [QC#6466,DEL]
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn8", "", "Clear", 0, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
    }

    /**
     * @param scrnMsg NFCL2760BMsg
     * @return Object[]
     */
    public static Object[] otherBusConnectTo_NFCL2760Scrn00_OpenWin_New(NFCL2760BMsg scrnMsg) {

        // START 2022/11/28 R.Takau [QC#57272,MOD]
        //Object[] params = new Object[PARAMS_NFCL5140.LENGTH.getValue()];
        Object[] params = new Object[PARAMS_NFCL5140.ADD_LENGTH.getValue()];
        // END 2022/11/28 R.Takau [QC#57272,MOD]
        
        scrnMsg.xxModeInd_P1.clear();
        scrnMsg.dealApplyAmt_P1.clear();
        scrnMsg.arCustRefNum_P1.clear();
        scrnMsg.tocCd_P1.clear();
        scrnMsg.coaProdCd_P1.clear();
        scrnMsg.arAdjTpCd_P1.clear();
        scrnMsg.adjCmntTxt_P1.clear();
        // START 2022/11/28 R.Takau [QC#57272,ADD]
        scrnMsg.xxCmntTxt_P1.clear();
        // END   2022/11/28 R.Takau [QC#57272,ADD]
        
        params[PARAMS_NFCL5140.NUM_0.getValue()] = scrnMsg.xxModeInd_P1;
        params[PARAMS_NFCL5140.NUM_1.getValue()] = scrnMsg.dealApplyAmt_P1;
        params[PARAMS_NFCL5140.NUM_2.getValue()] = scrnMsg.arCustRefNum_P1;
        params[PARAMS_NFCL5140.NUM_3.getValue()] = scrnMsg.tocCd_P1;
        params[PARAMS_NFCL5140.NUM_4.getValue()] = scrnMsg.coaProdCd_P1;
        params[PARAMS_NFCL5140.NUM_5.getValue()] = scrnMsg.arAdjTpCd_P1;
        params[PARAMS_NFCL5140.NUM_6.getValue()] = scrnMsg.adjCmntTxt_P1;
        params[PARAMS_NFCL5140.NUM_8.getValue()] = scrnMsg.ccyCd.getValue();
        params[PARAMS_NFCL5140.NUM_9.getValue()] = scrnMsg.exptFlg_H1.getValue();
        // START 2018/03/28 H.Ikeda [QC#21738,ADD]
        params[PARAMS_NFCL5140.NUM_7.getValue()] = scrnMsg.xxTotAmt_H1.getValue();
        // END   2018/03/28 H.Ikeda [QC#21738,ADD]
        // START 2022/11/28 R.Takau [QC#57272,ADD]
        params[PARAMS_NFCL5140.ADD_NUM_10.getValue()] = "";
        params[PARAMS_NFCL5140.ADD_NUM_11.getValue()] = scrnMsg.xxCmntTxt_P1;
        // END   2022/11/28 R.Takau [QC#57272,ADD]
        return params;

    }

    /**
     * @param scrnMsg NFCL2760BMsg
     * @return bizMsg NFCL2760CMsg
     */
    public static NFCL2760CMsg setRequestData_NFCL2760_NFCL5140(NFCL2760BMsg scrnMsg) {

        NFCL2760CMsg bizMsg;

        bizMsg = new NFCL2760CMsg();
        bizMsg.setBusinessID("NFCL2760");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL2760BMsg
     */
    public static void commonBtnControl_NFCL2760_NFCL5140(EZDCommonHandler scrnAppli, NFCL2760BMsg scrnMsg) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        // START 2016/08/04 K.Kojima [QC#6466,DEL]
        // scrnAppli.setButtonProperties("btn6", "", "Download", 0,
        // null);
        // END 2016/08/04 K.Kojima [QC#6466,DEL]
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn8", "", "Clear", 0, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL2760BMsg
     */
    public static void commonBtnControl_NFCL2760Scrn00_OpenWin_Search(EZDCommonHandler scrnAppli, NFCL2760BMsg scrnMsg) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        // START 2016/08/04 K.Kojima [QC#6466,DEL]
        // scrnAppli.setButtonProperties("btn6", "", "Download", 0,
        // null);
        // END 2016/08/04 K.Kojima [QC#6466,DEL]
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn8", "", "Clear", 0, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
    }

    /**
     * @param bizMsg NFCL2760CMsg
     * @param scrnMsg NFCL2760BMsg
     * @return Object[]
     */
    public static Object[] setParams_NFCL2760Scrn00_OpenWin_Search(NFCL2760CMsg bizMsg, NFCL2760BMsg scrnMsg, int index) {

        Object[] params = new Object[PARAMS_NFCL5050.LENGTH.getValue()];

        // START 2018/08/02 Y.Matsui [QC#26985-1, MOD]
        String[] arTrxNumList = toArray(bizMsg.arTrxNum_B1);
        String[] arTrxTpCdList = toArray(bizMsg.arTrxTpCd_B1);
        BigDecimal[] arTrxBalPkList = toArray(bizMsg.arTrxBalPk_B1);
        // END   2018/08/02 Y.Matsui [QC#26985-1, MOD]

        scrnMsg.arTrxNum_B1.clear();
        scrnMsg.arTrxTpCd_B1.clear();
        scrnMsg.arTrxBalPk_B1.clear();

        // START 2018/08/02 Y.Matsui [QC#26985-1, DEL]
//        ArrayList<String> serchArTrxNum = new ArrayList<String>();
//        ArrayList<String> serchArTrxTpCd = new ArrayList<String>();
//        ArrayList<BigDecimal> serchArTrxBalPk = new ArrayList<BigDecimal>();
//
//        int count = bizMsg.A.getValidCount();
//        for (int i = 0; i < count; i++) {
//
//            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).arTrxTpCd.getValue())) {
//
//                serchArTrxNum.add(bizMsg.A.no(i).arTrxNum.getValue());
//                serchArTrxTpCd.add(bizMsg.A.no(i).arTrxTpCd.getValue());
//            }
//            
//            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).arTrxBalPk_BK.getValue())) {
//                serchArTrxBalPk.add(bizMsg.A.no(i).arTrxBalPk_BK.getValue());
//            }
//        }
//
//        String[] arTrxNumList = (String[]) serchArTrxNum.toArray(new String[serchArTrxNum.size()]);
//        String[] arTrxTpCdList = (String[]) serchArTrxTpCd.toArray(new String[serchArTrxTpCd.size()]);
//        BigDecimal[] arTrxBalPkList = (BigDecimal[]) serchArTrxBalPk.toArray(new BigDecimal[serchArTrxBalPk.size()]);
        // END   2018/08/02 Y.Matsui [QC#26985-1, DEL]

        params[PARAMS_NFCL5050.NUM_0.getValue()] = scrnMsg.payerCustCd_P3;
        params[PARAMS_NFCL5050.NUM_1.getValue()] = scrnMsg.ccyCd_P3;
        params[PARAMS_NFCL5050.NUM_2.getValue()] = scrnMsg.arTrxNum_B1;
        params[PARAMS_NFCL5050.NUM_3.getValue()] = scrnMsg.arTrxTpCd_B1;
        params[PARAMS_NFCL5050.NUM_4.getValue()] = scrnMsg.arTrxBalPk_B1;
        params[PARAMS_NFCL5050.NUM_5.getValue()] = arTrxNumList;
        params[PARAMS_NFCL5050.NUM_6.getValue()] = arTrxTpCdList;
        params[PARAMS_NFCL5050.NUM_7.getValue()] = arTrxBalPkList;
        params[PARAMS_NFCL5050.NUM_8.getValue()] = MODE_0610;
        params[PARAMS_NFCL5050.NUM_9.getValue()] = scrnMsg.grpInvNum_H1;
        params[PARAMS_NFCL5050.NUM_10.getValue()] = scrnMsg.glDt_H1;
        params[PARAMS_NFCL5050.NUM_11.getValue()] = scrnMsg.arRcptTrxTpCd;
        params[PARAMS_NFCL5050.NUM_12.getValue()] = scrnMsg.exptFlg_H1.getValue();
        params[PARAMS_NFCL5050.NUM_13.getValue()] = scrnMsg.A.no(index).arCustRefNum.getValue();

        return params;

    }

    /**
     * @param scrnMsg NFCL2760BMsg
     * @return bizMsg NFCL2760CMsg
     */
    public static NFCL2760CMsg setRequestData_NFCL2760_NFCL5050(NFCL2760BMsg scrnMsg) {

        NFCL2760CMsg bizMsg;

        bizMsg = new NFCL2760CMsg();
        bizMsg.setBusinessID("NFCL2760");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL2760BMsg
     */
    public static void commonBtnControl_NFCL2760_NFCL5050(EZDCommonHandler scrnAppli, NFCL2760BMsg scrnMsg) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        // START 2016/08/04 K.Kojima [QC#6466,DEL]
        // scrnAppli.setButtonProperties("btn6", "", "Download", 0,
        // null);
        // END 2016/08/04 K.Kojima [QC#6466,DEL]
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn8", "", "Clear", 0, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
    }

    /**
     * @param scrnMsg NFCL2760BMsg
     * @return bizMsg NFCL2760CMsg
     */
    public static NFCL2760CMsg setRequestData_NFCL2760Scrn00_DeleteLines(NFCL2760BMsg scrnMsg) {

        NFCL2760CMsg bizMsg;

        bizMsg = new NFCL2760CMsg();
        bizMsg.setBusinessID("NFCL2760");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL2760BMsg
     */
    public static void commonBtnControl_NFCL2760Scrn00_DeleteLines(EZDCommonHandler scrnAppli, NFCL2760BMsg scrnMsg) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        // START 2016/08/04 K.Kojima [QC#6466,DEL]
        // scrnAppli.setButtonProperties("btn6", "", "Download", 0,
        // null);
        // END 2016/08/04 K.Kojima [QC#6466,DEL]
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn8", "", "Clear", 0, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL2760BMsg
     */
    public static void commonBtnControl_NFCL2760Scrn00_OpenWin_Select(EZDCommonHandler scrnAppli, NFCL2760BMsg scrnMsg) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        // START 2016/08/04 K.Kojima [QC#6466,DEL]
        // scrnAppli.setButtonProperties("btn6", "", "Download", 0,
        // null);
        // END 2016/08/04 K.Kojima [QC#6466,DEL]
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn8", "", "Clear", 0, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
    }

    /**
     * @param scrnMsg NFCL2760BMsg
     * @return Object[]
     */
    public static Object[] otherBusConnectTo_NFCL2760Scrn00_OpenWin_Select(NFCL2760BMsg scrnMsg) {

        Object[] params = new Object[PARAMS_NFCL5040.LENGTH.getValue()];
        params[PARAMS_NFCL5040.NUM_0.getValue()] = scrnMsg.xxModeInd_P2;
        params[PARAMS_NFCL5040.NUM_1.getValue()] = scrnMsg.arTrxBalPk_P2;
        params[PARAMS_NFCL5040.NUM_2.getValue()] = scrnMsg.arCashDiscSchdSqNum_P2;
        params[PARAMS_NFCL5040.NUM_3.getValue()] = scrnMsg.cashDiscPct_P2;
        params[PARAMS_NFCL5040.NUM_4.getValue()] = scrnMsg.dealCashDiscAmt_P2;

        return params;

    }

    /**
     * @param scrnMsg NFCL2760BMsg
     * @return bizMsg NFCL2760CMsg
     */
    public static NFCL2760CMsg setRequestData_NFCL2760_NFCL5040(NFCL2760BMsg scrnMsg) {

        NFCL2760CMsg bizMsg;

        bizMsg = new NFCL2760CMsg();
        bizMsg.setBusinessID("NFCL2760");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL2760BMsg
     */
    public static void commonBtnControl_NFCL2760_NFCL5040(EZDCommonHandler scrnAppli, NFCL2760BMsg scrnMsg) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        // START 2016/08/04 K.Kojima [QC#6466,DEL]
        // scrnAppli.setButtonProperties("btn6", "", "Download", 0,
        // null);
        // END 2016/08/04 K.Kojima [QC#6466,DEL]
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn8", "", "Clear", 0, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
    }

    /**
     * @param scrnMsg NFCL2760BMsg
     * @return bizMsg NFCL2760CMsg
     */
    public static NFCL2760CMsg setRequestData_NFCL2760Scrn00_OpenWin_Upload(NFCL2760BMsg scrnMsg) {

        NFCL2760CMsg bizMsg;

        bizMsg = new NFCL2760CMsg();
        bizMsg.setBusinessID("NFCL2760");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param scrnMsg NFCL2760BMsg
     * @return Object[]
     */
    public static Object[] otherBusConnectTo_NFCL2760Scrn00_OpenWin_Upload(NFCL2760BMsg scrnMsg) {

        Object[] params = new Object[1];
        params[0] = scrnMsg.upldCsvId_P4;

        return params;

    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL2760BMsg
     */
    public static void commonBtnControl_NFCL2760_ZYPL0210(EZDCommonHandler scrnAppli, NFCL2760BMsg scrnMsg) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        // START 2016/08/04 K.Kojima [QC#6466,DEL]
        // scrnAppli.setButtonProperties("btn6", "", "Download", 0,
        // null);
        // END 2016/08/04 K.Kojima [QC#6466,DEL]
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn8", "", "Clear", 0, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
    }

    /**
     * @param scrnMsg NFCL2760BMsg
     * @return bizMsg NFCL2760CMsg
     */
    public static NFCL2760CMsg setRequestData_NFCL2760Scrn00_DownloadCSV(NFCL2760BMsg scrnMsg) {

        NFCL2760CMsg bizMsg;

        bizMsg = new NFCL2760CMsg();
        bizMsg.setBusinessID("NFCL2760");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL2760BMsg
     */
    public static void commonBtnControl_NFCL2760Scrn00_DownloadCSV(EZDCommonHandler scrnAppli, NFCL2760BMsg scrnMsg) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        // START 2016/08/04 K.Kojima [QC#6466,DEL]
        // scrnAppli.setButtonProperties("btn6", "", "Download", 0,
        // null);
        // END 2016/08/04 K.Kojima [QC#6466,DEL]
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn8", "", "Clear", 0, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
    }

    /**
     * @param scrnMsg NFCL2760BMsg
     * @return bizMsg NFCL2760CMsg
     */
    public static NFCL2760CMsg setRequestData_NFCL2760Scrn00_PagePrev(NFCL2760BMsg scrnMsg) {

        NFCL2760CMsg bizMsg;

        bizMsg = new NFCL2760CMsg();
        bizMsg.setBusinessID("NFCL2760");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL2760BMsg
     */
    public static void commonBtnControl_NFCL2760Scrn00_PagePrev(EZDCommonHandler scrnAppli, NFCL2760BMsg scrnMsg) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        // START 2016/08/04 K.Kojima [QC#6466,DEL]
        // scrnAppli.setButtonProperties("btn6", "", "Download", 0,
        // null);
        // END 2016/08/04 K.Kojima [QC#6466,DEL]
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn8", "", "Clear", 0, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
    }

    /**
     * @param scrnMsg NFCL2760BMsg
     * @return bizMsg NFCL2760CMsg
     */
    public static NFCL2760CMsg setRequestData_NFCL2760Scrn00_PageNext(NFCL2760BMsg scrnMsg) {

        NFCL2760CMsg bizMsg;

        bizMsg = new NFCL2760CMsg();
        bizMsg.setBusinessID("NFCL2760");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL2760BMsg
     */
    public static void commonBtnControl_NFCL2760Scrn00_PageNext(EZDCommonHandler scrnAppli, NFCL2760BMsg scrnMsg) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        // START 2016/08/04 K.Kojima [QC#6466,DEL]
        // scrnAppli.setButtonProperties("btn6", "", "Download", 0,
        // null);
        // END 2016/08/04 K.Kojima [QC#6466,DEL]
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn8", "", "Clear", 0, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
    }

    /**
     * @param scrnMsg NFCL2760BMsg
     * @return bizMsg NFCL2760CMsg
     */
    public static NFCL2760CMsg setRequestData_NFCL2760Scrn00_CMN_Submit(NFCL2760BMsg scrnMsg) {

        NFCL2760CMsg bizMsg;

        bizMsg = new NFCL2760CMsg();
        bizMsg.setBusinessID("NFCL2760");
        bizMsg.setFunctionCode("40");

        return bizMsg;
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL2760BMsg
     */
    public static void commonBtnControl_NFCL2760Scrn00_CMN_Submit(EZDCommonHandler scrnAppli, NFCL2760BMsg scrnMsg) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        // START 2016/08/04 K.Kojima [QC#6466,DEL]
        // scrnAppli.setButtonProperties("btn6", "", "Download", 0,
        // null);
        // END 2016/08/04 K.Kojima [QC#6466,DEL]
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn8", "", "Clear", 0, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
    }

    /**
     * @param scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_NFCL2760Scrn00_OpenWin_MailEntry(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        // START 2016/08/04 K.Kojima [QC#6466,DEL]
        // scrnAppli.setButtonProperties("btn6", "", "Download", 0,
        // null);
        // END 2016/08/04 K.Kojima [QC#6466,DEL]
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn8", "", "Clear", 0, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL2760BMsg
     */
    public static void controlTableBegin_NFCL2760_A(EZDCommonHandler scrnAppli, NFCL2760BMsg scrnMsg) {

        // set values to items of pagenation for next page pagenation
        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();
    }

// 2017/11/15 QC#21402 add start
    public static void commonInputCheck(NFCL2760BMsg scrnMsg) {
        commonInputCheck(scrnMsg, false); 
    }
// 2017/11/15 QC#21402 add end
    /**
     * @param scrnMsg NFCL2760BMsg
     */
    public static void commonInputCheck(NFCL2760BMsg scrnMsg, boolean isAddLine) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox);
            if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxPgFlg_A1.getValue())) {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxDealApplyAmtNum_A1);
            }
            scrnMsg.addCheckItem(scrnMsg.A.no(i).cashAppGlDt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).arAdjTpCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxDealApplyAdjAmtNum_A1);
        }

        if (SCRN_MODE_ENTRY.equals(scrnMsg.xxModeInd_H1.getValue())) {
            // Delete Check Box
            //List outGetSelectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, XX_CHK_BOX, ZYPConstant.CHKBOX_ON_Y);
            //if (!outGetSelectedRows.isEmpty()) {
            //    for (int i = 0; i < outGetSelectedRows.size(); i++) {
            //        scrnMsg.A.no((Integer) outGetSelectedRows.get(i)).xxChkBox.setErrorInfo(1, "NFCM0030E");
            //    }
            //    scrnMsg.setMessageInfo("ZZM9037E", null);
            //} else {
            //    // do nothing
            //}

        } else {
            // do nothing
        }

        // START 2018/10/24 T.Ogura [QC#28168-1,DEL]
//        // 2017/12/07 QC#21402-2 add start
//        if (scrnMsg.A.getValidCount() > 0 && // 2018/01/11 QC#23134 Add
//                !ZYPCommonFunc.hasValue(scrnMsg.A.no(scrnMsg.A.getValidCount() - 1).arCustRefNum) && isAddLine) {
//            scrnMsg.putErrorScreen();
//            return;
//        }
//        // 2017/12/07 QC#21402-2 add end
        // END   2018/10/24 T.Ogura [QC#28168-1,DEL]

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            if ( !(ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxChkBox) && scrnMsg.A.no(i).xxChkBox.getValue().equals("Y")
                    && ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxArCashApplyStsTxt) && RECORD_STS.NEW.getValue().equals(scrnMsg.A.no(i).xxArCashApplyStsTxt.getValue())) ) {
                continue;
            }

            if (!isAddLine) {// 2018/10/24 T.Ogura [QC#28168-1,ADD]
                if ( !(scrnMsg.A.no(i).arTrxTpCd.getValue().equals("ADJ") || scrnMsg.A.no(i).arTrxTpCd.getValue().equals(AR_TRX_TP.RECEIPT)) && !ZYPCommonFunc.hasValue(scrnMsg.A.no(i).arCustRefNum)) {
                    scrnMsg.A.no(i).arCustRefNum.setErrorInfo(1, "NFCM0030E");
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).arCustRefNum);
                    scrnMsg.setMessageInfo("ZZM9037E", null);
                }
            }// 2018/10/24 T.Ogura [QC#28168-1,ADD]

            // START 2018/10/11 T.Ogura [QC#28168,MOD]
//            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxDealApplyAmtNum_A1) && 0 == scrnMsg.A.no(i).xxDealApplyAmtNum_A1.getValue().signum() && !isAddLine) { // 2017/11/15 QC#21402 mod start
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxDealApplyAmtNum_A1) && 0 == scrnMsg.A.no(i).xxDealApplyAmtNum_A1.getValue().signum()
                    && (!isAddLine || (isAddLine && ZYPCommonFunc.hasValue(scrnMsg.A.no(i).arCustRefNum)))) {
            // END   2018/10/11 T.Ogura [QC#28168,MOD]
                scrnMsg.A.no(i).xxDealApplyAmtNum_A1.setErrorInfo(1, "NFCM0038E");
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxDealApplyAmtNum_A1);
                scrnMsg.setMessageInfo("ZZM9037E", null);
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxDealApplyAmtNum_A1) && scrnMsg.A.no(i).xxDealApplyAmtNum_A1.getValue().scale() >= 3 ) {
                scrnMsg.A.no(i).xxDealApplyAmtNum_A1.setErrorInfo(1, "NFCM0768E");
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxDealApplyAmtNum_A1);
                scrnMsg.setMessageInfo("ZZM9037E", null);
            }

            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxModeInd_BK)) {
                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxDealApplyAmtNum_A1)) {
                    if (0 < scrnMsg.A.no(i).xxDealApplyAmtNum_A1.getValue().signum()) {
                        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).dealRmngBalGrsAmt) && 0 > scrnMsg.A.no(i).dealRmngBalGrsAmt.getValue().signum()) {
                            scrnMsg.A.no(i).xxDealApplyAmtNum_A1.setErrorInfo(1, "NFCM0026E");
                            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxDealApplyAmtNum_A1);
                            scrnMsg.setMessageInfo("ZZM9037E", null);
                        }
                    } else if (0 > scrnMsg.A.no(i).xxDealApplyAmtNum_A1.getValue().signum()) {
                        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).dealRmngBalGrsAmt) && 0 < scrnMsg.A.no(i).dealRmngBalGrsAmt.getValue().signum()) {
                            scrnMsg.A.no(i).xxDealApplyAmtNum_A1.setErrorInfo(1, "NFCM0026E");
                            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxDealApplyAmtNum_A1);
                            scrnMsg.setMessageInfo("ZZM9037E", null);
                        }
                    }
                }

            } else if (DETAIL_MODE_DEDUCTION.equals(scrnMsg.A.no(i).xxModeInd_BK.getValue())) {
                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxDealApplyAmtNum_A1)) {
                    if (0 == scrnMsg.A.no(i).xxDealApplyAmtNum_A1.getValue().signum()) {
                        scrnMsg.A.no(i).xxChkBox.setErrorInfo(1, "NFCM0026E");
                        scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox);
                        scrnMsg.setMessageInfo("ZZM9037E", null);
                    }
                }

            } else if (DETAIL_MODE_ADJUSTMENT.equals(scrnMsg.A.no(i).xxModeInd_BK.getValue())) {
                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxDealApplyAmtNum_A1)) {
                    if (0 == scrnMsg.A.no(i).xxDealApplyAmtNum_A1.getValue().signum()) {
                        scrnMsg.A.no(i).xxChkBox.setErrorInfo(1, "NFCM0026E");
                        scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox);
                        scrnMsg.setMessageInfo("ZZM9037E", null);
                    }
                }
            } else if (DETAIL_MODE_ONACCOUNT.equals(scrnMsg.A.no(i).xxModeInd_BK.getValue())) {
                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxDealApplyAmtNum_A1)) {
                    if (0 == scrnMsg.A.no(i).xxDealApplyAmtNum_A1.getValue().signum()) {
                        scrnMsg.A.no(i).xxChkBox.setErrorInfo(1, "NFCM0026E");
                        scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox);
                        scrnMsg.setMessageInfo("ZZM9037E", null);
                    }
                }
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).cashAppGlDt_A1)) {

                String outGetBatProcDate = ZYPDateUtil.getBatProcDate(scrnMsg.glblCmpyCd_H1.getValue());

                if (0 < ZYPDateUtil.compare(scrnMsg.A.no(i).cashAppGlDt_A1.getValue(), outGetBatProcDate)) {
//                    scrnMsg.A.no(i).cashAppGlDt_A1.setErrorInfo(1, "NFCM0040E");
                    scrnMsg.A.no(i).xxChkBox.setErrorInfo(1, "NFCM0040E");
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox);
                    scrnMsg.setMessageInfo("ZZM9037E", null);
                } else {
                    // do nothing
                }

                if (0 > ZYPDateUtil.compare(scrnMsg.A.no(i).cashAppGlDt_A1.getValue(), scrnMsg.glDt_H1.getValue())) {
//                    scrnMsg.A.no(i).cashAppGlDt_A1.setErrorInfo(1, "NFCM0088E");
                    scrnMsg.A.no(i).xxChkBox.setErrorInfo(1, "NFCM0088E");
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox);
                    scrnMsg.setMessageInfo("ZZM9037E", null);
                } else {
                    // do nothing
                }
            } else {
                // do nothing
            }

            /*if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).dealCashDiscAmt_A1) && ZYPCommonFunc.hasValue(scrnMsg.A.no(i).cashDiscPct_A1)) {

                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).dealOrigGrsAmt) && ZYPCommonFunc.hasValue(scrnMsg.A.no(i).dealRmngBalGrsAmt)) {
                    if (0 != scrnMsg.A.no(i).dealOrigGrsAmt.getValue().compareTo(scrnMsg.A.no(i).dealRmngBalGrsAmt.getValue())) {
                        scrnMsg.A.no(i).xxChkBox.setErrorInfo(1, "NFCM0010E");
                        scrnMsg.setMessageInfo("ZZM9037E", null);
                    }
                } else {
                    scrnMsg.A.no(i).xxChkBox.setErrorInfo(1, "NFCM0010E");
                    scrnMsg.setMessageInfo("ZZM9037E", null);
                }

                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).dealApplyGrsAmt)) {
                    scrnMsg.A.no(i).xxChkBox.setErrorInfo(1, "NFCM0010E");
                    scrnMsg.setMessageInfo("ZZM9037E", null);
                }

                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).dealApplyCrAmt)) {
                    scrnMsg.A.no(i).xxChkBox.setErrorInfo(1, "NFCM0010E");
                    scrnMsg.setMessageInfo("ZZM9037E", null);
                }

            } else {
                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).dealCashDiscAmt_A1) || ZYPCommonFunc.hasValue(scrnMsg.A.no(i).cashDiscPct_A1)) {
                    // if(ZYPCommonFunc.hasValue(scrnMsg.A.no(i).arTrxTpCd))
                    // {
                    scrnMsg.A.no(i).xxChkBox.setErrorInfo(1, "NFCM0010E");
                    scrnMsg.setMessageInfo("ZZM9037E", null);
                    // }
                }
            }*/

            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).arAdjTpCd_A1)) {

                if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxDealApplyAdjAmtNum_A1) || 0 == scrnMsg.A.no(i).xxDealApplyAdjAmtNum_A1.getValue().signum()) {
                    scrnMsg.A.no(i).xxDealApplyAdjAmtNum_A1.setErrorInfo(1, "NFCM0038E");
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).xxDealApplyAdjAmtNum_A1);
                    scrnMsg.setMessageInfo("ZZM9037E", null);
                } else {
                    // do nothing
                }

            } else {
                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxDealApplyAdjAmtNum_A1)) {
                    if (0 == scrnMsg.A.no(i).xxDealApplyAdjAmtNum_A1.getValue().signum()) {
                        scrnMsg.A.no(i).xxDealApplyAdjAmtNum_A1.clear();
                    } else {
                        scrnMsg.A.no(i).arAdjTpCd_A1.setErrorInfo(1, "NFCM0028E");
                        scrnMsg.addCheckItem(scrnMsg.A.no(i).arAdjTpCd_A1);
                        scrnMsg.setMessageInfo("ZZM9037E", null);
                    }
                } else {
                    // do nothing
                }
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).dealCashDiscAmt_A1) && ZYPCommonFunc.hasValue(scrnMsg.A.no(i).cashDiscPct_A1)) {

                BigDecimal balance = BigDecimal.ZERO;
                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxDealApplyAmtNum_A1)) {
                    balance = balance.add(scrnMsg.A.no(i).xxDealApplyAmtNum_A1.getValue());
                } else {
                    // do nothing
                }

                balance = balance.add(scrnMsg.A.no(i).dealCashDiscAmt_A1.getValue());

                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxDealApplyAdjAmtNum_A1)) {
                    balance = balance.add(scrnMsg.A.no(i).xxDealApplyAdjAmtNum_A1.getValue());
                } else {
                    // do nothing
                }

                if (0 != balance.compareTo(scrnMsg.A.no(i).dealRmngBalGrsAmt.getValue())) {
                    scrnMsg.A.no(i).xxChkBox.setErrorInfo(1, "NFCM0010E");
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox);
                    scrnMsg.setMessageInfo("ZZM9037E", null);
                }
            }

            if (!isBalanceCheck(scrnMsg.A.no(i))) {
                scrnMsg.setMessageInfo("NFCM0066E", null);
            } else {
                // do nothing
            }
        }

        scrnMsg.putErrorScreen();
    }

    /**
     * @param msg NFCL2760_ABMsg
     * @return boolean
     */
    public static boolean isBalanceCheck(NFCL2760_ABMsg msg) {

        boolean flg = true;

        if (ZYPCommonFunc.hasValue(msg.dealRmngBalGrsAmt)) {

            BigDecimal balance = BigDecimal.ZERO;

            if (ZYPCommonFunc.hasValue(msg.xxDealApplyAmtNum_A1)) {
                balance = balance.add(msg.xxDealApplyAmtNum_A1.getValue());
            } else {
                // do nothing
            }

            if (ZYPCommonFunc.hasValue(msg.dealCashDiscAmt_A1)) {
                balance = balance.add(msg.dealCashDiscAmt_A1.getValue());
            } else {
                // do nothing
            }

            if (ZYPCommonFunc.hasValue(msg.xxDealApplyAdjAmtNum_A1)) {
                balance = balance.add(msg.xxDealApplyAdjAmtNum_A1.getValue());
            } else {
                // do nothing
            }

            if (0 < msg.dealRmngBalGrsAmt.getValue().signum()) {
                if (0 > msg.dealRmngBalGrsAmt.getValue().compareTo(balance)) {
                    msg.xxDealApplyAmtNum_A1.setErrorInfo(1, "NFCM0065E");
                    flg = false;
                } else {
                    // do nothing
                }

            } else if (0 > msg.dealRmngBalGrsAmt.getValue().signum()) {
                if (0 < msg.dealRmngBalGrsAmt.getValue().compareTo(balance)) {
                    msg.xxDealApplyAmtNum_A1.setErrorInfo(1, "NFCM0065E");
                    flg = false;
                } else {
                    // do nothing
                }
            } else {
                // do nothing.
            }

            // Add Start 2018/01/15 S21_NA#20563
            if (flg) {
                if (ZYPCommonFunc.hasValue(msg.dealApplyAdjRsvdAmt_A3)) {
                    balance = balance.add(msg.dealApplyAdjRsvdAmt_A3.getValue());
                }
                if (0 < msg.dealRmngBalGrsAmt.getValue().signum()) {
                    if (msg.dealRmngBalGrsAmt.getValue().compareTo(balance) < 0) {
                        msg.xxDealApplyAmtNum_A1.setErrorInfo(1, NFCM0880E);
                        return false;
                    }
                } else if (0 > msg.dealRmngBalGrsAmt.getValue().signum()) {
                    if (msg.dealRmngBalGrsAmt.getValue().compareTo(balance) > 0) {
                        msg.xxDealApplyAmtNum_A1.setErrorInfo(1, NFCM0880E);
                        return false;
                    }
                }
            }
            // Add End 2018/01/15 S21_NA#20563

        } else {
            // do nothing
        }

        return flg;
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL2760BMsg
     */
    public static void setErrorScreen_NFCL2760(EZDCommonHandler scrnAppli, NFCL2760BMsg scrnMsg) {

        scrnAppli.setButtonEnabled("OpenWin_Detail", false);
        scrnAppli.setButtonEnabled("OpenWin_New", false);
        scrnAppli.setButtonEnabled("OpenWin_Search", false);
        scrnAppli.setButtonEnabled("DeleteLines", false);
        scrnAppli.setButtonEnabled("DownloadCSV", false);
        scrnAppli.setButtonEnabled("OpenWin_Upload", false);
        scrnAppli.setButtonEnabled("btn2", false);
        scrnAppli.setButtonEnabled("Check_All", false);
        scrnAppli.setButtonEnabled("Un_Check_All", false);
        scrnAppli.setButtonEnabled("SearchTrxAllLine", false);
        scrnAppli.setButtonEnabled("SearchTrxLine", false);
        scrnAppli.setButtonEnabled("AddOneLine", false);
        scrnAppli.setButtonEnabled("AddFiveLine", false);
        scrnAppli.setButtonEnabled("AddTenLine", false);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).xxChkBox.setInputProtected(true);
            scrnMsg.A.no(i).arCustRefNum.setInputProtected(true);
            scrnMsg.A.no(i).xxDealApplyAmtNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).cashAppGlDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).arAdjTpCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxDealApplyAdjAmtNum_A1.setInputProtected(true);
            scrnAppli.setButtonEnabled("OpenWin_Select", i, false);
        }

    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL2760BMsg
     */
    public static void setEntryScreen_NFCL2760(EZDCommonHandler scrnAppli, NFCL2760BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            scrnMsg.A.no(i).arCustRefNum.setInputProtected(true);

            if (DETAIL_MODE_DEDUCTION.equals(scrnMsg.A.no(i).xxModeInd_BK.getValue()) || DETAIL_MODE_ADJUSTMENT.equals(scrnMsg.A.no(i).xxModeInd_BK.getValue()) || DETAIL_MODE_ONACCOUNT.equals(scrnMsg.A.no(i).xxModeInd_BK.getValue())) {

                scrnMsg.A.no(i).xxDealApplyAmtNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).cashAppGlDt_A1.setInputProtected(true);
                scrnMsg.A.no(i).arAdjTpCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(true);
                scrnMsg.A.no(i).xxDealApplyAdjAmtNum_A1.setInputProtected(true);
                scrnAppli.setButtonEnabled("OpenWin_Select", i, false);

            } else {
                // do nothing
            }
        }

        if (SCRN_MODE_CANCEL.equals(scrnMsg.xxModeInd_H1.getValue())) {
            scrnAppli.setButtonEnabled("DownloadCSV", false);
        } else {
            // do nothing
        }
        if (scrnMsg.A.getValidCount() == 0) {
            scrnAppli.setButtonEnabled("DownloadCSV", false);
            scrnAppli.setButtonEnabled("Check_All", false);
            scrnAppli.setButtonEnabled("Un_Check_All", false);
        } else {
            // do nothing
        }
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL2760BMsg
     */
    public static void setCancelScreen_NFCL2760(EZDCommonHandler scrnAppli, NFCL2760BMsg scrnMsg) {

        BigDecimal rcpt = scrnMsg.dealRcptAmt.getValue();

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            if (rcpt.signum() == -1) {
                scrnMsg.A.no(i).xxChkBox.setInputProtected(true);
            } else {
                // do nothing
            }
            scrnMsg.A.no(i).arCustRefNum.setInputProtected(true);
            scrnMsg.A.no(i).xxDealApplyAmtNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).cashAppGlDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).arAdjTpCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxDealApplyAdjAmtNum_A1.setInputProtected(true);
            scrnAppli.setButtonEnabled("OpenWin_Select", i, false);
            scrnAppli.setButtonEnabled("SearchTrxLine", i, false);

        }

        if (rcpt.signum() == -1) {
            scrnAppli.setButtonEnabled("OpenWin_Detail", false);
            scrnAppli.setButtonEnabled("CalcGrossAmount", false);
            scrnAppli.setButtonEnabled("OpenWin_Select", false);
        } else {
            // do nothing
        }
        scrnAppli.setButtonEnabled("OpenWin_New", false);
        scrnAppli.setButtonEnabled("OpenWin_Search", false);
        scrnAppli.setButtonEnabled("DeleteLines", false);
        scrnAppli.setButtonEnabled("DownloadCSV", false);
        scrnAppli.setButtonEnabled("OpenWin_MailEntry", false);
        if (CANCELERROR_STATUS_Y.equals(scrnMsg.xxRsltStsCd_H2.getValue())) {
            scrnAppli.setButtonEnabled("Check_All", true);
            scrnAppli.setButtonEnabled("Un_Check_All", true);
        } else {
            scrnAppli.setButtonEnabled("Check_All", false);
            scrnAppli.setButtonEnabled("Un_Check_All", false);
        }

        scrnAppli.setButtonEnabled("SearchTrxAllLine", false);
        scrnAppli.setButtonEnabled("AddOneLine", false);
        scrnAppli.setButtonEnabled("AddFiveLine", false);
        scrnAppli.setButtonEnabled("AddTenLine", false);

    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL2760BMsg
     */
    public static void setCancelScreen_NFCL2760Error(EZDCommonHandler scrnAppli, NFCL2760BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).arCustRefNum.setInputProtected(true);
            scrnMsg.A.no(i).xxDealApplyAmtNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).cashAppGlDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).arAdjTpCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxDealApplyAdjAmtNum_A1.setInputProtected(true);
            scrnAppli.setButtonEnabled("OpenWin_Select", i, false);
        }
        scrnAppli.setButtonProperties("btn2", "CMN_Submit", "Submit", 0, null);
        scrnAppli.setButtonEnabled("OpenWin_New", false);
        scrnAppli.setButtonEnabled("OpenWin_Search", false);
        scrnAppli.setButtonEnabled("DeleteLines", false);
        scrnAppli.setButtonEnabled("DownloadCSV", false);
        scrnAppli.setButtonEnabled("OpenWin_Upload", false);
        scrnAppli.setButtonEnabled("Check_All", false);
        scrnAppli.setButtonEnabled("Un_Check_All", false);

    }

    /**
     * @param scrnMsg NFCL2760BMsg
     */
    public static void setRowBg(NFCL2760BMsg scrnMsg) {
        scrnMsg.clearAllGUIAttribute(SCREEN_ID_NFCL2760);
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID_NFCL2760, scrnMsg);
        tblColor.setAlternateRowsBG(TABLE_ID1, scrnMsg.A);
        tblColor.setAlternateRowsBG(TABLE_ID2, scrnMsg.A);    }

    /**
     * @param scrnMsg NFCL2760BMsg
     * @return bizMsg NFCL2760CMsg
     */
    public static NFCL2760CMsg setRequestData_NFCL2760Scrn00_OpenWin_Search(NFCL2760BMsg scrnMsg) {

        NFCL2760CMsg bizMsg;

        bizMsg = new NFCL2760CMsg();
        bizMsg.setBusinessID("NFCL2760");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL2760BMsg
     */
    public static void busBtnControl_DownloadCsv(EZDCommonHandler scrnAppli, NFCL2760BMsg scrnMsg) {

        if (scrnMsg.dealRcptAmt.getValue().subtract(scrnMsg.dealRfAmt.getValue()).equals(scrnMsg.dealRcptRmngBalAmt.getValue())) {
            scrnAppli.setButtonEnabled("DownloadCSV", false);
        } else {
            // Nothing todo.
        }
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL2760BMsg
     */
    public static void setCheckAllBtn(EZDCommonHandler scrnAppli, NFCL2760BMsg scrnMsg) {

        int detailCount = scrnMsg.A.getValidCount();

        BigDecimal rcpt = scrnMsg.dealRcptAmt.getValue();

        if (detailCount > 0) {
            if (SCRN_MODE_CANCEL.equals(scrnMsg.xxModeInd_H1.getValue())) {
                if (rcpt.signum() == -1) {
                    scrnAppli.setButtonProperties("Check_All", "Check_All", "CheckAll", 0, null);
                    scrnAppli.setButtonProperties("Un_Check_All", "Un_Check_All", "UncheckAll", 0, null);
                } else {
                    scrnAppli.setButtonProperties("Check_All", "Check_All", "CheckAll", 1, null);
                    scrnAppli.setButtonProperties("Un_Check_All", "Un_Check_All", "UncheckAll", 1, null);
                }
            } else {
                scrnAppli.setButtonProperties("Check_All", "Check_All", "CheckAll", 1, null);
                scrnAppli.setButtonProperties("Un_Check_All", "Un_Check_All", "UncheckAll", 1, null);
            }
        } else {
            scrnAppli.setButtonProperties("Check_All", "Check_All", "CheckAll", 0, null);
            scrnAppli.setButtonProperties("Un_Check_All", "Un_Check_All", "UncheckAll", 0, null);
        }

    }

    /**
     * @param glDt String
     * @param limitMonths int
     * @return String
     */
    public static String getLimitGLDate(String glDt, int limitMonths) {

        Calendar calendar = Calendar.getInstance();

        if (glDt == null || glDt.length() != DATE_INFO.MONTH_END_POS.getValue()) {
            return glDt;
        } else {
            calendar.set(Integer.parseInt(glDt.substring(DATE_INFO.YEAR_START_POS.getValue(), DATE_INFO.YEAR_END_POS.getValue())),
                    Integer.parseInt(glDt.substring(DATE_INFO.MONTH_START_POS.getValue(), DATE_INFO.MONTH_END_POS.getValue())) - 1, 1);
        }

        calendar.add(Calendar.MONTH, -1 * limitMonths);
        SimpleDateFormat strDtFormat = new SimpleDateFormat("yyyyMMdd");

        return strDtFormat.format(calendar.getTime());
    }

    /**
     * @param scrnMsg NFCL2760BMsg
     * @return Object[]
     */
    public static Object[] otherBusConnectTo_NFCL2760Scrn00_OpenWin_MailEntry(NFCL2760BMsg scrnMsg) {

        scrnMsg.xxModeInd_U1.setValue(MEIL_PARAM_MODE);

        Object[] params = new Object[PARAMS_NFCL5010.LENGTH.getValue()];
        params[PARAMS_NFCL5010.NUM_0.getValue()] = scrnMsg.xxModeInd_U1;
        params[PARAMS_NFCL5010.NUM_1.getValue()] = scrnMsg.rcptNum;
        params[PARAMS_NFCL5010.NUM_2.getValue()] = scrnMsg.A.no(0).applyGrpKey_BK;

        return params;
    }

    /** Add On 2010/06/09 T.Tanaka * */

    /**
     * @param scrnMsg NFCL2760BMsg
     * @param eventName String
     */
    public static void chkSubmit(NFCL2760BMsg scrnMsg, String eventName) {
        // TODO
    }

    /**
     * @return bizMsg NFCL2760CMsg
     */
    public static NFCL2760CMsg setRequestData_NFCL2760Scrn00_AddOneLine() {

        NFCL2760CMsg bizMsg;

        bizMsg = new NFCL2760CMsg();
        bizMsg.setBusinessID("NFCL2760");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_NFCL2760Scrn00_AddOneLine(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        // START 2016/08/04 K.Kojima [QC#6466,DEL]
        // scrnAppli.setButtonProperties("btn6", "", "Download", 0,
        // null);
        // END 2016/08/04 K.Kojima [QC#6466,DEL]
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn8", "", "Clear", 0, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
    }

    /**
     * @return bizMsg NFCL2760CMsg
     */
    public static NFCL2760CMsg setRequestData_NFCL2760Scrn00_AddFiveLine() {

        NFCL2760CMsg bizMsg;

        bizMsg = new NFCL2760CMsg();
        bizMsg.setBusinessID("NFCL2760");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_NFCL2760Scrn00_AddFiveLine(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        // START 2016/08/04 K.Kojima [QC#6466,DEL]
        // scrnAppli.setButtonProperties("btn6", "", "Download", 0,
        // null);
        // END 2016/08/04 K.Kojima [QC#6466,DEL]
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn8", "", "Clear", 0, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
    }

    /**
     * @return bizMsg NFCL2760CMsg
     */
    public static NFCL2760CMsg setRequestData_NFCL2760Scrn00_AddTenLine() {

        NFCL2760CMsg bizMsg;

        bizMsg = new NFCL2760CMsg();
        bizMsg.setBusinessID("NFCL2760");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_NFCL2760Scrn00_AddTenLine(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        // START 2016/08/04 K.Kojima [QC#6466,DEL]
        // scrnAppli.setButtonProperties("btn6", "", "Download", 0,
        // null);
        // END 2016/08/04 K.Kojima [QC#6466,DEL]
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn8", "", "Clear", 0, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
    }

    /**
     * @param scrnMsg NFCL2760BMsg
     * @param scrnAppli EZDCommonHandler
     */
    public static void protectAddDetailLine(NFCL2760BMsg scrnMsg, EZDCommonHandler scrnAppli) {

        int num = scrnMsg.A.getValidCount();

        if (SCRN_MODE_SUBMIT.equals(scrnMsg.xxModeInd_H1.getValue())) {
            protectAddDetailLineSubmit(scrnMsg, scrnAppli);
            return;
        }

        if (scrnMsg.A.getValidCount() > 0) {
            scrnAppli.setButtonEnabled("DownloadCSV", true);
            scrnAppli.setButtonEnabled("SearchTrxAllLine", true);
        } else {
            scrnAppli.setButtonEnabled("DownloadCSV", false);
            scrnAppli.setButtonEnabled("SearchTrxAllLine", false);
        }

        // Adj, Adj Amt
        boolean adjFlg = true;
        for(int i = 0; i < scrnMsg.arAdjTpCd_B1.length(); i++ ){
           if(ZYPCommonFunc.hasValue(scrnMsg.arAdjTpCd_B1.no(i).getValue())) {
               adjFlg = false;
               break;
           }
        }

        for (int i = 0; i < num; i++) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.exptFlg_H1.getValue())) {
                scrnMsg.A.no(i).arAdjTpCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).xxDealApplyAdjAmtNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(true);
                scrnAppli.setButtonEnabled("OpenWin_Select", i, false);
            }
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxPgFlg_A1.getValue())) {
                scrnAppli.setButtonEnabled("SearchTrxLine", i, true);
                scrnMsg.A.no(i).xxChkBox.setInputProtected(false);
                scrnMsg.A.no(i).arCustRefNum.setInputProtected(false);
                scrnMsg.A.no(i).xxDealApplyAmtNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).arAdjTpCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(true);
                scrnMsg.A.no(i).xxDealApplyAdjAmtNum_A1.setInputProtected(true);
                scrnAppli.setButtonEnabled("OpenWin_Select", i, false);
            } else {
                // Search
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxPgFlg_A2.getValue())) {
                    scrnMsg.A.no(i).xxChkBox.setInputProtected(false);
                    scrnMsg.A.no(i).arCustRefNum.setInputProtected(true);
                    scrnMsg.A.no(i).xxDealApplyAmtNum_A1.setInputProtected(false);
                    if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.exptFlg_H1.getValue())) {
                        scrnMsg.A.no(i).arAdjTpCd_A1.setInputProtected(false);
                        scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(true);
                        scrnMsg.A.no(i).xxDealApplyAdjAmtNum_A1.setInputProtected(false);
                        scrnAppli.setButtonEnabled("OpenWin_Select", i, true);

                        if(adjFlg) {
                            scrnMsg.A.no(i).arAdjTpCd_A1.setInputProtected(true);
                            scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(true);
                            scrnMsg.A.no(i).xxDealApplyAdjAmtNum_A1.setInputProtected(true);
                        }
                    }
                    scrnAppli.setButtonEnabled("SearchTrxLine", i, false);
                    // New
                } else if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxPgFlg_A3.getValue())) {
                    scrnMsg.A.no(i).xxChkBox.setInputProtected(false);
                    scrnMsg.A.no(i).arCustRefNum.setInputProtected(true);
                    scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(true);
                    scrnMsg.A.no(i).xxDealApplyAmtNum_A1.setInputProtected(true);
                    scrnMsg.A.no(i).arAdjTpCd_A1.setInputProtected(true);
                    scrnMsg.A.no(i).xxDealApplyAdjAmtNum_A1.setInputProtected(true);
                    scrnAppli.setButtonEnabled("OpenWin_Select", i, false);
                    scrnAppli.setButtonEnabled("SearchTrxLine", i, false);
                } else {
                    scrnMsg.A.no(i).arCustRefNum.setInputProtected(false);
                    scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(true);
                    scrnAppli.setButtonEnabled("SearchTrxLine", i, true);
                }
            }
            //Def#6240
            scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(true);
        }
    }

    /**
     * @param scrnMsg NFCL2760BMsg
     * @param scrnAppli EZDCommonHandler
     */
    public static void protectAddDetailLineSubmit(NFCL2760BMsg scrnMsg, EZDCommonHandler scrnAppli) {

        int num = scrnMsg.A.getValidCount();

        scrnAppli.setButtonEnabled("DownloadCSV", false);
        scrnAppli.setButtonEnabled("SearchTrxAllLine", false);
        scrnAppli.setButtonEnabled("Check_All", false);
        scrnAppli.setButtonEnabled("Un_Check_All", false);

        for (int i = 0; i < num; i++) {
            scrnMsg.A.no(i).xxChkBox.setInputProtected(true);
            scrnMsg.A.no(i).arCustRefNum.setInputProtected(true);
            scrnMsg.A.no(i).xxDealApplyAmtNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).arAdjTpCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxDealApplyAdjAmtNum_A1.setInputProtected(true);
            scrnAppli.setButtonEnabled("SearchTrxLine", i, false);
            scrnAppli.setButtonEnabled("OpenWin_Select", i, false);

            //Def#6240
            scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(true);
        }
    }

    /**
     * @param scrnMsg NFCL2760BMsg
     * @param scrnAppli EZDCommonHandler
     */
    public static void protectCancelSubmit(NFCL2760BMsg scrnMsg, EZDCommonHandler scrnAppli) {

        int num = scrnMsg.A.getValidCount();

        scrnAppli.setButtonEnabled("DownloadCSV", false);
        scrnAppli.setButtonEnabled("SearchTrxAllLine", false);
        scrnAppli.setButtonEnabled("Check_All", true);
        scrnAppli.setButtonEnabled("Un_Check_All", true);

        for (int i = 0; i < num; i++) {
            scrnMsg.A.no(i).xxChkBox.setInputProtected(false);
            scrnMsg.A.no(i).arCustRefNum.setInputProtected(true);
            scrnMsg.A.no(i).xxDealApplyAmtNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).arAdjTpCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxDealApplyAdjAmtNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(true);
            scrnAppli.setButtonEnabled("SearchTrxLine", i, false);
            scrnAppli.setButtonEnabled("OpenWin_Select", i, false);

            //Def#6240
            scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(true);
        }
    }

    /**
     * @return bizMsg NFCL0430CMsg
     */
    public static NFCL2760CMsg setRequestData_NFCL2760Scrn00_SearchTrxLine() {

        NFCL2760CMsg bizMsg;

        bizMsg = new NFCL2760CMsg();
        bizMsg.setBusinessID("NFCL2760");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    // START 2020/01/23 H.Ikeda [QC#54902, ADD]
    /**
     * @return bizMsg NFCL2760CMsg
     */
    public static NFCL2760CMsg setRequestData_NFCL2760Scrn00_OnBlur_Inv() {

        NFCL2760CMsg bizMsg;

        bizMsg = new NFCL2760CMsg();
        bizMsg.setBusinessID("NFCL2760");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param scrnMsg NFCL2760BMsg
     * @return bizMsg NFCL2760CMsg
     */
    public static NFCL2760CMsg setRequestData_NFCL2760Scrn00_Search(NFCL2760BMsg scrnMsg) {

        NFCL2760CMsg bizMsg;

        bizMsg = new NFCL2760CMsg();
        bizMsg.setBusinessID("NFCL2760");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }
    // END   2020/01/23 H.Ikeda [QC#54902, ADD]

    /**
     * @param scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_NFCL2760Scrn00_SearchTrxLine(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        // START 2016/08/04 K.Kojima [QC#6466,DEL]
        // scrnAppli.setButtonProperties("btn6", "", "Download", 0,
        // null);
        // END 2016/08/04 K.Kojima [QC#6466,DEL]
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn8", "", "Clear", 0, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
    }

    /**
     * @return bizMsg NFCL0430CMsg
     */
    public static NFCL2760CMsg setRequestData_NFCL2760Scrn00_SearchTrxAllLine() {

        NFCL2760CMsg bizMsg;

        bizMsg = new NFCL2760CMsg();
        bizMsg.setBusinessID("NFCL2760");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_NFCL2760Scrn00_SearchTrxAllLine(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        // START 2016/08/04 K.Kojima [QC#6466,DEL]
        // scrnAppli.setButtonProperties("btn6", "", "Download", 0,
        // null);
        // END 2016/08/04 K.Kojima [QC#6466,DEL]
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn8", "", "Clear", 0, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
    }

    /**
     * @return bizMsg NFCL0430CMsg
     */
    public static NFCL2760CMsg setRequestData_NFCL2760Scrn00_OpenWin_New() {

        NFCL2760CMsg bizMsg;

        bizMsg = new NFCL2760CMsg();
        bizMsg.setBusinessID("NFCL2760");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @return bizMsg NFCL0430CMsg
     */
    public static NFCL2760CMsg setRequestData_NFCL2760Scrn00_OpenWin_Select() {

        NFCL2760CMsg bizMsg;

        bizMsg = new NFCL2760CMsg();
        bizMsg.setBusinessID("NFCL2760");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param scrnMsg NFCL2760BMsg
     */
    public static void setAppFracDigit(NFCL2760BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            //scrnMsg.A.no(i).xxDealApplyAmtNum_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum_H1.getValueInt());
            //scrnMsg.A.no(i).xxDealApplyAdjAmtNum_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum_H1.getValueInt());
            scrnMsg.A.no(i).xxDealApplyAmtNum_A1.setAppFracDigit(2);
            scrnMsg.A.no(i).xxDealApplyAdjAmtNum_A1.setAppFracDigit(2);
        }
    }

    /**
     * @param scrnMsg NFCL2760BMsg
     * @return boolean(true:batch/false:online)
     */
    public static boolean isApiExecuteBatchMsgCd(NFCL2760BMsg scrnMsg) {
        return "NFCM0205I".equals(scrnMsg.getMessageCode());
    }

    public static void protectModeOne(NFCL2760BMsg scrnMsg, EZDCommonHandler scrnAppli) {
        int num = scrnMsg.A.getValidCount();

        scrnAppli.setButtonEnabled("DownloadCSV", false);
        scrnAppli.setButtonEnabled("SearchTrxAllLine", false);
        // START 2016/12/08 E.Kameishi [QC#16286,MOD]
        if (scrnMsg.arRcptStsCd_H1.getValue().equals(AR_RCPT_STS.REFUND)) {
            scrnAppli.setButtonEnabled("OpenWin_New", false);
        // START 2023/06/13 S.Fujita [QC#61487,DEL]
        // } else {
        //     scrnAppli.setButtonEnabled("OpenWin_New", true);
        // END 2023/06/13 S.Fujita [QC#61487,DEL]
        }
        // END 2016/12/08 E.Kameishi [QC#16286,MOD]
        if (scrnMsg.A.getValidCount() == 0 ) {
            scrnAppli.setButtonEnabled("Check_All", false);
            scrnAppli.setButtonEnabled("Un_Check_All", false);
        } else {
        	scrnAppli.setButtonEnabled("Check_All", true);
            scrnAppli.setButtonEnabled("Un_Check_All", true);
        }

        for (int i = 0; i < num; i++) {
            if (scrnMsg.A.no(i).xxArCashApplyStsTxt.getValue().equals(RECORD_STS.APPLIED.getValue())) {
                scrnMsg.A.no(i).xxChkBox.setInputProtected(false);
                scrnMsg.A.no(i).arCustRefNum.setInputProtected(true);
                scrnMsg.A.no(i).xxDealApplyAmtNum_A1.setInputProtected(true);
                scrnAppli.setButtonEnabled("SearchTrxLine", i, false);
                scrnAppli.setButtonEnabled("OpenWin_Select", i, false);
                scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(true);
            } else {
                if ( ZYPCommonFunc.hasValue(scrnMsg.A.no(i).arTrxTpCd)
                       && (scrnMsg.A.no(i).arTrxTpCd.getValue().equals(AR_TRX_TP.DEBIT_MEMO.toString())
                               || scrnMsg.A.no(i).arTrxTpCd.getValue().equals(AR_TRX_TP.ON_ACCOUNT.toString())
                               || scrnMsg.A.no(i).arTrxTpCd.getValue().equals("ADJ"))) {
                    //Adjust, On Account, Deduction
                    scrnMsg.A.no(i).xxChkBox.setInputProtected(false);
                    scrnMsg.A.no(i).arCustRefNum.setInputProtected(true);
                    scrnMsg.A.no(i).xxDealApplyAmtNum_A1.setInputProtected(true);
                    scrnAppli.setButtonEnabled("SearchTrxLine", i, false);
                    scrnAppli.setButtonEnabled("OpenWin_Select", i, false);
                    scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(true);
                } else {
                    scrnMsg.A.no(i).xxChkBox.setInputProtected(false);
                    scrnMsg.A.no(i).arCustRefNum.setInputProtected(false);
                    scrnMsg.A.no(i).xxDealApplyAmtNum_A1.setInputProtected(false);
                    scrnAppli.setButtonEnabled("SearchTrxLine", i, true);
                    scrnAppli.setButtonEnabled("OpenWin_Select", i, true);
                    scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(true);
                }
            }
            // START 2021/06/21 H.Dimay [QC#58639, ADD]
            // Disable check for refund entry
            // START 2022/04/22 D.Mamaril [QC#59333,MOD]
            //if (DETAIL_MODE_REFUND.equals(scrnMsg.A.no(i).xxModeInd_BK.getValue())) {
            if (DETAIL_MODE_REFUND_ONGOING.equals(scrnMsg.A.no(i).xxModeInd_BK.getValue())) {
            // END 2022/04/22 D.Mamaril [QC#59333,MOD]
                scrnMsg.A.no(i).xxChkBox.setInputProtected(true);
            }
            // END 2021/06/21 H.Dimay [QC#58639, ADD]
        }
    }

    // START 2023/06/13 S.Fujita [QC#61487,ADD]
    /**
     * isCollectionReps
     * @param scrnMsg NFCL2760BMsg
     * @return boolean
     */
    private static boolean isCollectionReps(NFCL2760BMsg scrnMsg) {
        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();

        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(BIZ_ID);
        if (funcList == null || funcList.isEmpty()) {
            scrnMsg.setMessageInfo(ZZSM4300E, new String[] {userProfSvc.getContextUserInfo().getUserId()});
            return false;
        }
        if (funcList.contains(FUNC_ID_CLT_REPS)) {
            return true;
        }
        return false;
    }
    // END 2023/06/13 S.Fujita [QC#61487,ADD]

    // Start 2016/12/26 H.Ikeda [QC#12865,ADD]
    /**
     * hasUpdateFuncId
     * @return boolean
     */
    private static boolean hasUpdateFuncId(NFCL2760BMsg scrnMsg) {
        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();

        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(BIZ_ID);
        if (funcList == null || funcList.isEmpty()) {
            scrnMsg.setMessageInfo(ZZSM4300E, new String[] {userProfSvc.getContextUserInfo().getUserId()});
            return false;
        }
        // START 2023/06/13 S.Fujita [QC#61487,MOD]
        // if (funcList.contains(FUNC_ID_UPDATE)) {
        if (funcList.contains(FUNC_ID_UPDATE) || funcList.contains(FUNC_ID_CLT_REPS)) {
        // END 2023/06/13 S.Fujita [QC#61487,MOD]
            return true;
        }
        return false;
    }
    // End  2016/12/26 H.Ikeda [QC#12865,ADD]

    // START 2018/08/02 Y.Matsui [QC#26985-1, ADD]
    private static String[] toArray(EZDCStringItemArray ezdItemArray) {
        String[] array = new String[ezdItemArray.length()];
        for (int i = 0; i < ezdItemArray.length(); i++) {
            array[i] = ezdItemArray.no(i).getValue();
        }
        return array;
    }

    private static BigDecimal[] toArray(EZDCBigDecimalItemArray ezdItemArray) {
        BigDecimal[] array = new BigDecimal[ezdItemArray.length()];
        for (int i = 0; i < ezdItemArray.length(); i++) {
            array[i] = ezdItemArray.no(i).getValue();
        }
        return array;
    }
    // END   2018/08/02 Y.Matsui [QC#26985-1, ADD]

 }