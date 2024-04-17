package business.servlet.NFCL0770.common;

import static business.servlet.NFCL0770.constant.NFCL0770Constant.BIZ_ID;
import static business.servlet.NFCL0770.constant.NFCL0770Constant.CANCELERROR_STATUS_Y;
import static business.servlet.NFCL0770.constant.NFCL0770Constant.DATE_INFO;
import static business.servlet.NFCL0770.constant.NFCL0770Constant.DETAIL_MODE_ADJUSTMENT;
import static business.servlet.NFCL0770.constant.NFCL0770Constant.DETAIL_MODE_DEDUCTION;
import static business.servlet.NFCL0770.constant.NFCL0770Constant.DETAIL_MODE_ONACCOUNT;
import static business.servlet.NFCL0770.constant.NFCL0770Constant.FUNC_ID_UPDATE;
import static business.servlet.NFCL0770.constant.NFCL0770Constant.MEIL_PARAM_MODE;
import static business.servlet.NFCL0770.constant.NFCL0770Constant.MODE_0610;
import static business.servlet.NFCL0770.constant.NFCL0770Constant.NFCM0880E;
import static business.servlet.NFCL0770.constant.NFCL0770Constant.PARAMS_NFCL5010;
import static business.servlet.NFCL0770.constant.NFCL0770Constant.PARAMS_NFCL5040;
import static business.servlet.NFCL0770.constant.NFCL0770Constant.PARAMS_NFCL5050;
import static business.servlet.NFCL0770.constant.NFCL0770Constant.PARAMS_NFCL5140;
import static business.servlet.NFCL0770.constant.NFCL0770Constant.RECORD_STS;
import static business.servlet.NFCL0770.constant.NFCL0770Constant.SCREEN_ID_NFCL0770;
import static business.servlet.NFCL0770.constant.NFCL0770Constant.SCRN_MODE_CANCEL;
import static business.servlet.NFCL0770.constant.NFCL0770Constant.SCRN_MODE_SUBMIT;
import static business.servlet.NFCL0770.constant.NFCL0770Constant.TABLE_ID1;
import static business.servlet.NFCL0770.constant.NFCL0770Constant.TABLE_ID2;
import static business.servlet.NFCL0770.constant.NFCL0770Constant.ZZSM4300E;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import parts.common.EZDBStringItem;
import parts.common.EZDCBigDecimalItemArray;
import parts.common.EZDCStringItemArray;
import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDCommonHandler;
import business.blap.NFCL0770.NFCL0770CMsg;
import business.servlet.NFCL0770.NFCL0770BMsg;
import business.servlet.NFCL0770.NFCL0770_ABMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/11/02   Fujitsu         S.Takami        Create          QC#28289
 * 2022/09/02   Hitachi         A.Kohinata      Update          QC#60403
 * 2022/11/28   Hitachi         R.Takau         Update          QC#57252
 * </pre>
 */
public class NFCL0770CommonLogic {

    /**
     * @param scrnMsg NFCL0770BMsg
     * @return bizMsg NFCL0770BMsg
     */
    public static NFCL0770CMsg setRequestData_NFCL0770_INIT(NFCL0770BMsg scrnMsg) {

        NFCL0770CMsg bizMsg;

        bizMsg = new NFCL0770CMsg();
        bizMsg.setBusinessID("NFCL0770");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param params Object[]
     * @param scrnMsg NFCL0770BMsg
     */
    public static void otherBusConnectFrom_NFCL0770_INIT(Object[] params, NFCL0770BMsg scrnMsg) {

        scrnMsg.rcptNum.setValue(((EZDBStringItem) params[1]).getValue());
        scrnMsg.xxModeInd_H1.setValue(((EZDBStringItem) params[0]).getValue());

        scrnMsg.xxPgFlg.setValue(ZYPConstant.FLG_OFF_N);
    }

    /**
     * @param scrnMsg NFCL0770BMsg
     */
    public static void transMsgCheck(NFCL0770BMsg scrnMsg) {

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

        // mod start 2022/09/02 QC#60403
        scrnMsg.addCheckItem(scrnMsg.arCustRefNum_H1);
        // mod end 2022/09/02 QC#60403
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL0770BMsg
     */
    public static void initialize(EZDCommonHandler scrnAppli, NFCL0770BMsg scrnMsg) {

        scrnMsg.setInputProtected(false);
        scrnMsg.dsAcctNm.setInputProtected(true);
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).arAdjTpNm_A3.setInputProtected(true);
        }

        scrnAppli.setButtonProperties("OpenWin_Detail", "OpenWin_Detail", "Detail", 1, null);
        scrnAppli.setButtonProperties("CalcGrossAmount", "CalcGrossAmount", "Calculation", 1, null);
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.exptFlg_H1.getValue())) {
            scrnAppli.setButtonProperties("OpenWin_Select", "OpenWin_Select", "CD", 0, null);
        } else {
            scrnAppli.setButtonProperties("OpenWin_Select", "OpenWin_Select", "CD", 1, null);
        }
        scrnAppli.setButtonProperties("OpenWin_New", "OpenWin_New", "Adjust", 1, null);
        scrnAppli.setButtonProperties("OpenWin_Search", "OpenWin_Search", "Search TRX", 1, null);
        scrnAppli.setButtonProperties("DeleteLines", "DeleteLines", "Delete", 1, null);
        scrnAppli.setButtonProperties("DownloadCSV", "DownloadCSV", "Download", 1, null);
        scrnAppli.setButtonProperties("OpenWin_Upload", "OpenWin_Upload", "Upload", 1, null);
        scrnAppli.setButtonProperties("OpenWin_MailEntry", "OpenWin_MailEntry", "Mail Entry", 0, null);
        scrnAppli.setButtonProperties("Check_All", "Check_All", "CheckAll", 1, null);
        scrnAppli.setButtonProperties("Un_Check_All", "Un_Check_All", "UncheckAll", 1, null);
        if (hasUpdateFuncId(scrnMsg)) {
            scrnAppli.setButtonProperties("btn1", "", "Save", 1, null);
            scrnAppli.setButtonProperties("btn2", "CMN_Submit", "Submit", 1, null);
        } else {
            scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
            scrnAppli.setButtonProperties("btn2", "CMN_Submit", "Submit", 0, null);
        }
        scrnAppli.setButtonProperties("btn3", "", "Apply", 1, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 1, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 1, null);
        scrnAppli.setButtonProperties("btn6", "DownloadCSV", "Download", 1, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 1, null);
        scrnAppli.setButtonProperties("btn8", "CMN_Clear", "Clear", 1, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 1, null);
        scrnAppli.setButtonProperties("btn10", "CMN_Return", "Return", 1, null);
        scrnAppli.setButtonProperties("btn11", "AddOneLine", "Add (F11)", 1, null);

        if (scrnMsg.arRcptStsCd_H1.getValue().equals(AR_RCPT_STS.REFUND)) {
            scrnAppli.setButtonEnabled("btn2", false);
            scrnAppli.setButtonEnabled("btn11", false);
            scrnAppli.setButtonEnabled("OpenWin_New", false);
        } else {
            if (hasUpdateFuncId(scrnMsg)) {
                scrnAppli.setButtonEnabled("btn2", true);
            } else {
                scrnAppli.setButtonEnabled("btn2", false);
            }
            scrnAppli.setButtonEnabled("btn11", true);
            scrnAppli.setButtonEnabled("OpenWin_New", true);
        }
        clearVisibility(scrnMsg);

        // add start 2022/09/02 QC#60403
        scrnAppli.setButtonConfirmMsgEx("CMN_Return", "NZZM0004W", null, 1);
        // add end 2022/09/02 QC#60403

    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL0770BMsg
     */
    public static void commonBtnControl_NFCL0770_INIT(EZDCommonHandler scrnAppli, NFCL0770BMsg scrnMsg) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL0770BMsg
     */
    public static void busBtnControl_NFCL0770_INIT(EZDCommonHandler scrnAppli, NFCL0770BMsg scrnMsg) {

        scrnAppli.setButtonEnabled("OpenWin_Select", false);
    }

    /**
     * @param scrnMsg NFCL0770BMsg
     */
    private static void clearVisibility(NFCL0770BMsg scrnMsg) {

        EZDGUIAttribute xxbtn1 = new EZDGUIAttribute("NFCL0770Scrn00", "btn1");
        xxbtn1.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn1);

        EZDGUIAttribute xxbtn2 = new EZDGUIAttribute("NFCL0770Scrn00", "btn2");
        xxbtn2.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn2);

        EZDGUIAttribute xxbtn3 = new EZDGUIAttribute("NFCL0770Scrn00", "btn3");
        xxbtn3.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn3);

        EZDGUIAttribute xxbtn4 = new EZDGUIAttribute("NFCL0770Scrn00", "btn4");
        xxbtn4.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn4);

        EZDGUIAttribute xxbtn5 = new EZDGUIAttribute("NFCL0770Scrn00", "btn5");
        xxbtn5.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn5);

        EZDGUIAttribute xxbtn6 = new EZDGUIAttribute("NFCL0770Scrn00", "btn6");
        xxbtn6.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn6);

        EZDGUIAttribute xxbtn7 = new EZDGUIAttribute("NFCL0770Scrn00", "btn7");
        xxbtn7.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn7);

        EZDGUIAttribute xxbtn8 = new EZDGUIAttribute("NFCL0770Scrn00", "btn8");
        xxbtn8.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn8);

        EZDGUIAttribute xxbtn9 = new EZDGUIAttribute("NFCL0770Scrn00", "btn9");
        xxbtn9.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn9);

        EZDGUIAttribute xxbtn10 = new EZDGUIAttribute("NFCL0770Scrn00", "btn10");
        xxbtn10.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn10);

        EZDGUIAttribute xxbtn11 = new EZDGUIAttribute("NFCL0770Scrn00", "btn11");
        xxbtn11.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn11);

    }

    /**
     * @param scrnMsg NFCL0770BMsg
     * @return bizMsg NFCL0770CMsg
     */
    public static NFCL0770CMsg setRequestData_NFCL0770Scrn00_CalcGrossAmount(NFCL0770BMsg scrnMsg) {

        NFCL0770CMsg bizMsg;

        bizMsg = new NFCL0770CMsg();
        bizMsg.setBusinessID("NFCL0770");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL0770BMsg
     */
    public static void commonBtnControl_NFCL0770Scrn00_CalcGrossAmount(EZDCommonHandler scrnAppli, NFCL0770BMsg scrnMsg) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL0770BMsg
     */
    public static void commonBtnControl_NFCL0770Scrn00_OpenWin_Detail(EZDCommonHandler scrnAppli, NFCL0770BMsg scrnMsg) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
    }

    /**
     * @param scrnMsg NFCL0770BMsg
     * @return Object[]
     */
    public static Object[] otherBusConnectTo_NFCL0770Scrn00_OpenWin_Detail(NFCL0770BMsg scrnMsg) {

        Object[] params = new Object[1];
        params[0] = scrnMsg.rcptNum;

        return params;

    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL0770BMsg
     */
    public static void commonBtnControl_NFCL0770Scrn00_OpenWin_New(EZDCommonHandler scrnAppli, NFCL0770BMsg scrnMsg) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
    }

    /**
     * @param scrnMsg NFCL0770BMsg
     * @return Object[]
     */
    public static Object[] otherBusConnectTo_NFCL0770Scrn00_OpenWin_New(NFCL0770BMsg scrnMsg) {

        // START 2022/11/28 R.Takau [QC#57272,MOD]
        //Object[] params = new Object[PARAMS_NFCL5140.LENGTH.getValue()];
        Object[] params = new Object[PARAMS_NFCL5140.ADD_LENGTH.getValue()];
        // START 2022/11/28 R.Takau [QC#57272,MOD]

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
        params[PARAMS_NFCL5140.NUM_7.getValue()] = scrnMsg.xxTotAmt_H1.getValue();
        params[PARAMS_NFCL5140.NUM_8.getValue()] = scrnMsg.ccyCd.getValue();
        params[PARAMS_NFCL5140.NUM_9.getValue()] = scrnMsg.exptFlg_H1.getValue();
        params[PARAMS_NFCL5140.NUM_10.getValue()] = ZYPConstant.FLG_ON_Y;
        // START 2022/11/28 R.Takau [QC#57272,ADD]
        params[PARAMS_NFCL5140.ADD_NUM_11.getValue()] = scrnMsg.xxCmntTxt_P1;
        // END   2022/11/28 R.Takau [QC#57272,ADD]
        return params;

    }

    /**
     * @param scrnMsg NFCL0770BMsg
     * @return bizMsg NFCL0770CMsg
     */
    public static NFCL0770CMsg setRequestData_NFCL0770_NFCL5140(NFCL0770BMsg scrnMsg) {

        NFCL0770CMsg bizMsg;

        bizMsg = new NFCL0770CMsg();
        bizMsg.setBusinessID("NFCL0770");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL0770BMsg
     */
    public static void commonBtnControl_NFCL0770_NFCL5140(EZDCommonHandler scrnAppli, NFCL0770BMsg scrnMsg) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL0770BMsg
     */
    public static void commonBtnControl_NFCL0770Scrn00_OpenWin_Search(EZDCommonHandler scrnAppli, NFCL0770BMsg scrnMsg) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
    }

    /**
     * @param bizMsg NFCL0770CMsg
     * @param scrnMsg NFCL0770BMsg
     * @param index target index
     * @return Object[]
     */
    public static Object[] setParams_NFCL0770Scrn00_OpenWin_Search(NFCL0770CMsg bizMsg, NFCL0770BMsg scrnMsg, int index) {

        Object[] params = new Object[PARAMS_NFCL5050.LENGTH.getValue()];

        String[] arTrxNumList = toArray(bizMsg.arTrxNum_B1);
        String[] arTrxTpCdList = toArray(bizMsg.arTrxTpCd_B1);
        BigDecimal[] arTrxBalPkList = toArray(bizMsg.arTrxBalPk_B1);

        scrnMsg.arTrxNum_B1.clear();
        scrnMsg.arTrxTpCd_B1.clear();
        scrnMsg.arTrxBalPk_B1.clear();

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
     * @param scrnMsg NFCL0770BMsg
     * @return bizMsg NFCL0770CMsg
     */
    public static NFCL0770CMsg setRequestData_NFCL0770_NFCL5050(NFCL0770BMsg scrnMsg) {

        NFCL0770CMsg bizMsg;

        bizMsg = new NFCL0770CMsg();
        bizMsg.setBusinessID("NFCL0770");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL0770BMsg
     */
    public static void commonBtnControl_NFCL0770_NFCL5050(EZDCommonHandler scrnAppli, NFCL0770BMsg scrnMsg) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
    }

    /**
     * @param scrnMsg NFCL0770BMsg
     * @return bizMsg NFCL0770CMsg
     */
    public static NFCL0770CMsg setRequestData_NFCL0770Scrn00_DeleteLines(NFCL0770BMsg scrnMsg) {

        NFCL0770CMsg bizMsg;

        bizMsg = new NFCL0770CMsg();
        bizMsg.setBusinessID("NFCL0770");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL0770BMsg
     */
    public static void commonBtnControl_NFCL0770Scrn00_DeleteLines(EZDCommonHandler scrnAppli, NFCL0770BMsg scrnMsg) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL0770BMsg
     */
    public static void commonBtnControl_NFCL0770Scrn00_OpenWin_Select(EZDCommonHandler scrnAppli, NFCL0770BMsg scrnMsg) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
    }

    /**
     * @param scrnMsg NFCL0770BMsg
     * @return Object[]
     */
    public static Object[] otherBusConnectTo_NFCL0770Scrn00_OpenWin_Select(NFCL0770BMsg scrnMsg) {

        Object[] params = new Object[PARAMS_NFCL5040.LENGTH.getValue()];
        params[PARAMS_NFCL5040.NUM_0.getValue()] = scrnMsg.xxModeInd_P2;
        params[PARAMS_NFCL5040.NUM_1.getValue()] = scrnMsg.arTrxBalPk_P2;
        params[PARAMS_NFCL5040.NUM_2.getValue()] = scrnMsg.arCashDiscSchdSqNum_P2;
        params[PARAMS_NFCL5040.NUM_3.getValue()] = scrnMsg.cashDiscPct_P2;
        params[PARAMS_NFCL5040.NUM_4.getValue()] = scrnMsg.dealCashDiscAmt_P2;

        return params;

    }

    /**
     * @param scrnMsg NFCL0770BMsg
     * @return bizMsg NFCL0770CMsg
     */
    public static NFCL0770CMsg setRequestData_NFCL0770_NFCL5040(NFCL0770BMsg scrnMsg) {

        NFCL0770CMsg bizMsg;

        bizMsg = new NFCL0770CMsg();
        bizMsg.setBusinessID("NFCL0770");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL0770BMsg
     */
    public static void commonBtnControl_NFCL0770_NFCL5040(EZDCommonHandler scrnAppli, NFCL0770BMsg scrnMsg) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
    }

    /**
     * @param scrnMsg NFCL0770BMsg
     * @return bizMsg NFCL0770CMsg
     */
    public static NFCL0770CMsg setRequestData_NFCL0770Scrn00_OpenWin_Upload(NFCL0770BMsg scrnMsg) {

        NFCL0770CMsg bizMsg;

        bizMsg = new NFCL0770CMsg();
        bizMsg.setBusinessID("NFCL0770");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param scrnMsg NFCL0770BMsg
     * @return Object[]
     */
    public static Object[] otherBusConnectTo_NFCL0770Scrn00_OpenWin_Upload(NFCL0770BMsg scrnMsg) {

        Object[] params = new Object[1];
        params[0] = scrnMsg.upldCsvId_P4;

        return params;

    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL0770BMsg
     */
    public static void commonBtnControl_NFCL0770_ZYPL0210(EZDCommonHandler scrnAppli, NFCL0770BMsg scrnMsg) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
    }

    /**
     * @param scrnMsg NFCL0770BMsg
     * @return bizMsg NFCL0770CMsg
     */
    public static NFCL0770CMsg setRequestData_NFCL0770Scrn00_DownloadCSV(NFCL0770BMsg scrnMsg) {

        NFCL0770CMsg bizMsg;

        bizMsg = new NFCL0770CMsg();
        bizMsg.setBusinessID("NFCL0770");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL0770BMsg
     */
    public static void commonBtnControl_NFCL0770Scrn00_DownloadCSV(EZDCommonHandler scrnAppli, NFCL0770BMsg scrnMsg) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
    }

    /**
     * @param scrnMsg NFCL0770BMsg
     * @return bizMsg NFCL0770CMsg
     */
    public static NFCL0770CMsg setRequestData_NFCL0770Scrn00_PagePrev(NFCL0770BMsg scrnMsg) {

        NFCL0770CMsg bizMsg;

        bizMsg = new NFCL0770CMsg();
        bizMsg.setBusinessID("NFCL0770");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL0770BMsg
     */
    public static void commonBtnControl_NFCL0770Scrn00_PagePrev(EZDCommonHandler scrnAppli, NFCL0770BMsg scrnMsg) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
    }

    /**
     * @param scrnMsg NFCL0770BMsg
     * @return bizMsg NFCL0770CMsg
     */
    public static NFCL0770CMsg setRequestData_NFCL0770Scrn00_PageNext(NFCL0770BMsg scrnMsg) {

        NFCL0770CMsg bizMsg;

        bizMsg = new NFCL0770CMsg();
        bizMsg.setBusinessID("NFCL0770");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL0770BMsg
     */
    public static void commonBtnControl_NFCL0770Scrn00_PageNext(EZDCommonHandler scrnAppli, NFCL0770BMsg scrnMsg) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
    }

    /**
     * @param scrnMsg NFCL0770BMsg
     * @return bizMsg NFCL0770CMsg
     */
    public static NFCL0770CMsg setRequestData_NFCL0770Scrn00_CMN_Submit(NFCL0770BMsg scrnMsg) {

        NFCL0770CMsg bizMsg;

        bizMsg = new NFCL0770CMsg();
        bizMsg.setBusinessID("NFCL0770");
        bizMsg.setFunctionCode("40");

        return bizMsg;
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL0770BMsg
     */
    public static void commonBtnControl_NFCL0770Scrn00_CMN_Submit(EZDCommonHandler scrnAppli, NFCL0770BMsg scrnMsg) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn2", "CMN_Submit", "Submit", 2, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
    }

    /**
     * @param scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_NFCL0770Scrn00_OpenWin_MailEntry(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL0770BMsg
     */
    public static void controlTableBegin_NFCL0770_A(EZDCommonHandler scrnAppli, NFCL0770BMsg scrnMsg) {

        // set values to items of pagenation for next page pagenation
        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();
    }

    /**
     * <pre>
     * General Input data validation
     * @param scrnMsg Screen Message
     * </pre>
     */
    public static void commonInputCheck(NFCL0770BMsg scrnMsg) {
        commonInputCheck(scrnMsg, false);
    }
    /**
     * <pre>
     * General Input value Check
     * @param scrnMsg Screen Message
     * @param isAddLine true: add line, false: not
     */
    public static void commonInputCheck(NFCL0770BMsg scrnMsg, boolean isAddLine) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox);
            if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxPgFlg_A1.getValue())) {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxDealApplyAmtNum_A1);
            }
            scrnMsg.addCheckItem(scrnMsg.A.no(i).cashAppGlDt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).arAdjTpCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxDealApplyAdjAmtNum_A1);
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            if (!(ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxChkBox) && scrnMsg.A.no(i).xxChkBox.getValue().equals("Y")
                    && ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxArCashApplyStsTxt) && RECORD_STS.NEW.getValue().equals(scrnMsg.A.no(i).xxArCashApplyStsTxt.getValue()))) {
                continue;
            }

            if (!isAddLine) {
                if (!(scrnMsg.A.no(i).arTrxTpCd.getValue().equals("ADJ") || scrnMsg.A.no(i).arTrxTpCd.getValue().equals(AR_TRX_TP.RECEIPT)) && !ZYPCommonFunc.hasValue(scrnMsg.A.no(i).arCustRefNum)) {
                    scrnMsg.A.no(i).arCustRefNum.setErrorInfo(1, "NFCM0030E");
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).arCustRefNum);
                    scrnMsg.setMessageInfo("ZZM9037E", null);
                }
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxDealApplyAmtNum_A1) && 0 == scrnMsg.A.no(i).xxDealApplyAmtNum_A1.getValue().signum()
                    && (!isAddLine || (isAddLine && ZYPCommonFunc.hasValue(scrnMsg.A.no(i).arCustRefNum)))) {
                scrnMsg.A.no(i).xxDealApplyAmtNum_A1.setErrorInfo(1, "NFCM0038E");
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxDealApplyAmtNum_A1);
                scrnMsg.setMessageInfo("ZZM9037E", null);
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxDealApplyAmtNum_A1) && scrnMsg.A.no(i).xxDealApplyAmtNum_A1.getValue().scale() >= 3) {
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
                    scrnMsg.A.no(i).xxChkBox.setErrorInfo(1, "NFCM0040E");
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox);
                    scrnMsg.setMessageInfo("ZZM9037E", null);
                }

                if (0 > ZYPDateUtil.compare(scrnMsg.A.no(i).cashAppGlDt_A1.getValue(), scrnMsg.glDt_H1.getValue())) {
//                    scrnMsg.A.no(i).cashAppGlDt_A1.setErrorInfo(1, "NFCM0088E");
                    scrnMsg.A.no(i).xxChkBox.setErrorInfo(1, "NFCM0088E");
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox);
                    scrnMsg.setMessageInfo("ZZM9037E", null);
                }
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
                }
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).dealCashDiscAmt_A1) && ZYPCommonFunc.hasValue(scrnMsg.A.no(i).cashDiscPct_A1)) {

                BigDecimal balance = BigDecimal.ZERO;
                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxDealApplyAmtNum_A1)) {
                    balance = balance.add(scrnMsg.A.no(i).xxDealApplyAmtNum_A1.getValue());
                }

                balance = balance.add(scrnMsg.A.no(i).dealCashDiscAmt_A1.getValue());

                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxDealApplyAdjAmtNum_A1)) {
                    balance = balance.add(scrnMsg.A.no(i).xxDealApplyAdjAmtNum_A1.getValue());
                }

                if (0 != balance.compareTo(scrnMsg.A.no(i).dealRmngBalGrsAmt.getValue())) {
                    scrnMsg.A.no(i).xxChkBox.setErrorInfo(1, "NFCM0010E");
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox);
                    scrnMsg.setMessageInfo("ZZM9037E", null);
                }
            }

            if (!isBalanceCheck(scrnMsg.A.no(i))) {
                scrnMsg.setMessageInfo("NFCM0066E", null);
            }
        }

        scrnMsg.putErrorScreen();
    }

    /**
     * @param msg NFCL0770_ABMsg
     * @return boolean
     */
    public static boolean isBalanceCheck(NFCL0770_ABMsg msg) {

        boolean flg = true;

        if (ZYPCommonFunc.hasValue(msg.dealRmngBalGrsAmt)) {

            BigDecimal balance = BigDecimal.ZERO;

            if (ZYPCommonFunc.hasValue(msg.xxDealApplyAmtNum_A1)) {
                balance = balance.add(msg.xxDealApplyAmtNum_A1.getValue());
            }

            if (ZYPCommonFunc.hasValue(msg.dealCashDiscAmt_A1)) {
                balance = balance.add(msg.dealCashDiscAmt_A1.getValue());
            }

            if (ZYPCommonFunc.hasValue(msg.xxDealApplyAdjAmtNum_A1)) {
                balance = balance.add(msg.xxDealApplyAdjAmtNum_A1.getValue());
            }

            if (0 < msg.dealRmngBalGrsAmt.getValue().signum()) {
                if (0 > msg.dealRmngBalGrsAmt.getValue().compareTo(balance)) {
                    msg.xxDealApplyAmtNum_A1.setErrorInfo(1, "NFCM0065E");
                    flg = false;
                }

            } else if (0 > msg.dealRmngBalGrsAmt.getValue().signum()) {
                if (0 < msg.dealRmngBalGrsAmt.getValue().compareTo(balance)) {
                    msg.xxDealApplyAmtNum_A1.setErrorInfo(1, "NFCM0065E");
                    flg = false;
                }
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

        }

        return flg;
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL0770BMsg
     */
    public static void setErrorScreen_NFCL0770(EZDCommonHandler scrnAppli, NFCL0770BMsg scrnMsg) {

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
     * @param scrnMsg NFCL0770BMsg
     */
    public static void setEntryScreen_NFCL0770(EZDCommonHandler scrnAppli, NFCL0770BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            scrnMsg.A.no(i).arCustRefNum.setInputProtected(true);

            String modeIndBk = scrnMsg.A.no(i).xxModeInd_BK.getValue();
            if (DETAIL_MODE_DEDUCTION.equals(modeIndBk) //
                    || DETAIL_MODE_ADJUSTMENT.equals(modeIndBk) //
                    || DETAIL_MODE_ONACCOUNT.equals(modeIndBk)) {

                scrnMsg.A.no(i).xxDealApplyAmtNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).cashAppGlDt_A1.setInputProtected(true);
                scrnMsg.A.no(i).arAdjTpCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(true);
                scrnMsg.A.no(i).xxDealApplyAdjAmtNum_A1.setInputProtected(true);
                scrnAppli.setButtonEnabled("OpenWin_Select", i, false);
            }
        }

        if (SCRN_MODE_CANCEL.equals(scrnMsg.xxModeInd_H1.getValue())) {
            scrnAppli.setButtonEnabled("DownloadCSV", false);
        }
        if (scrnMsg.A.getValidCount() == 0) {
            scrnAppli.setButtonEnabled("DownloadCSV", false);
            scrnAppli.setButtonEnabled("Check_All", false);
            scrnAppli.setButtonEnabled("Un_Check_All", false);
        }
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL0770BMsg
     */
    public static void setCancelScreen_NFCL0770(EZDCommonHandler scrnAppli, NFCL0770BMsg scrnMsg) {

        BigDecimal rcpt = scrnMsg.dealRcptAmt.getValue();

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            if (rcpt.signum() == -1) {
                scrnMsg.A.no(i).xxChkBox.setInputProtected(true);
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
     * @param scrnMsg NFCL0770BMsg
     */
    public static void setCancelScreen_NFCL0770Error(EZDCommonHandler scrnAppli, NFCL0770BMsg scrnMsg) {

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
     * @param scrnMsg NFCL0770BMsg
     */
    public static void setRowBg(NFCL0770BMsg scrnMsg) {
        scrnMsg.clearAllGUIAttribute(SCREEN_ID_NFCL0770);
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID_NFCL0770, scrnMsg);
        tblColor.setAlternateRowsBG(TABLE_ID1, scrnMsg.A);
        tblColor.setAlternateRowsBG(TABLE_ID2, scrnMsg.A);
    }

    /**
     * @param scrnMsg NFCL0770BMsg
     * @return bizMsg NFCL0770CMsg
     */
    public static NFCL0770CMsg setRequestData_NFCL0770Scrn00_OpenWin_Search(NFCL0770BMsg scrnMsg) {

        NFCL0770CMsg bizMsg;

        bizMsg = new NFCL0770CMsg();
        bizMsg.setBusinessID("NFCL0770");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL0770BMsg
     */
    public static void busBtnControl_DownloadCsv(EZDCommonHandler scrnAppli, NFCL0770BMsg scrnMsg) {

        if (scrnMsg.dealRcptAmt.getValue().subtract(scrnMsg.dealRfAmt.getValue()).equals(scrnMsg.dealRcptRmngBalAmt.getValue())) {
            scrnAppli.setButtonEnabled("DownloadCSV", false);
        }
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL0770BMsg
     */
    public static void setCheckAllBtn(EZDCommonHandler scrnAppli, NFCL0770BMsg scrnMsg) {

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
     * @param scrnMsg NFCL0770BMsg
     * @return Object[]
     */
    public static Object[] otherBusConnectTo_NFCL0770Scrn00_OpenWin_MailEntry(NFCL0770BMsg scrnMsg) {

        scrnMsg.xxModeInd_U1.setValue(MEIL_PARAM_MODE);

        Object[] params = new Object[PARAMS_NFCL5010.LENGTH.getValue()];
        params[PARAMS_NFCL5010.NUM_0.getValue()] = scrnMsg.xxModeInd_U1;
        params[PARAMS_NFCL5010.NUM_1.getValue()] = scrnMsg.rcptNum;
        params[PARAMS_NFCL5010.NUM_2.getValue()] = scrnMsg.A.no(0).applyGrpKey_BK;

        return params;
    }

    /** Add On 2010/06/09 T.Tanaka * */

    /**
     * @param scrnMsg NFCL0770BMsg
     * @param eventName String
     */
    public static void chkSubmit(NFCL0770BMsg scrnMsg, String eventName) {
        // TODO
    }

    /**
     * @return bizMsg NFCL0770CMsg
     */
    public static NFCL0770CMsg setRequestData_NFCL0770Scrn00_AddOneLine() {

        NFCL0770CMsg bizMsg;

        bizMsg = new NFCL0770CMsg();
        bizMsg.setBusinessID("NFCL0770");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_NFCL0770Scrn00_AddOneLine(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
    }

    /**
     * @return bizMsg NFCL0770CMsg
     */
    public static NFCL0770CMsg setRequestData_NFCL0770Scrn00_AddFiveLine() {

        NFCL0770CMsg bizMsg;

        bizMsg = new NFCL0770CMsg();
        bizMsg.setBusinessID("NFCL0770");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_NFCL0770Scrn00_AddFiveLine(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
    }

    /**
     * @return bizMsg NFCL0770CMsg
     */
    public static NFCL0770CMsg setRequestData_NFCL0770Scrn00_AddTenLine() {

        NFCL0770CMsg bizMsg;

        bizMsg = new NFCL0770CMsg();
        bizMsg.setBusinessID("NFCL0770");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_NFCL0770Scrn00_AddTenLine(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
    }

    /**
     * @param scrnMsg NFCL0770BMsg
     * @param scrnAppli EZDCommonHandler
     */
    public static void protectAddDetailLine(NFCL0770BMsg scrnMsg, EZDCommonHandler scrnAppli) {

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
        for (int i = 0; i < scrnMsg.arAdjTpCd_B1.length(); i++) {
           if (ZYPCommonFunc.hasValue(scrnMsg.arAdjTpCd_B1.no(i).getValue())) {
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

                        if (adjFlg) {
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
     * @param scrnMsg NFCL0770BMsg
     * @param scrnAppli EZDCommonHandler
     */
    public static void protectAddDetailLineSubmit(NFCL0770BMsg scrnMsg, EZDCommonHandler scrnAppli) {

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
     * @param scrnMsg NFCL0770BMsg
     * @param scrnAppli EZDCommonHandler
     */
    public static void protectCancelSubmit(NFCL0770BMsg scrnMsg, EZDCommonHandler scrnAppli) {

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
    public static NFCL0770CMsg setRequestData_NFCL0770Scrn00_SearchTrxLine() {

        NFCL0770CMsg bizMsg;

        bizMsg = new NFCL0770CMsg();
        bizMsg.setBusinessID("NFCL0770");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_NFCL0770Scrn00_SearchTrxLine(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
    }

    /**
     * @return bizMsg NFCL0430CMsg
     */
    public static NFCL0770CMsg setRequestData_NFCL0770Scrn00_SearchTrxAllLine() {

        NFCL0770CMsg bizMsg;

        bizMsg = new NFCL0770CMsg();
        bizMsg.setBusinessID("NFCL0770");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_NFCL0770Scrn00_SearchTrxAllLine(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
    }

    /**
     * @return bizMsg NFCL0430CMsg
     */
    public static NFCL0770CMsg setRequestData_NFCL0770Scrn00_OpenWin_New() {

        NFCL0770CMsg bizMsg;

        bizMsg = new NFCL0770CMsg();
        bizMsg.setBusinessID("NFCL0770");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @return bizMsg NFCL0430CMsg
     */
    public static NFCL0770CMsg setRequestData_NFCL0770Scrn00_OpenWin_Select() {

        NFCL0770CMsg bizMsg;

        bizMsg = new NFCL0770CMsg();
        bizMsg.setBusinessID("NFCL0770");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param scrnMsg NFCL0770BMsg
     */
    public static void setAppFracDigit(NFCL0770BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            //scrnMsg.A.no(i).xxDealApplyAmtNum_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum_H1.getValueInt());
            //scrnMsg.A.no(i).xxDealApplyAdjAmtNum_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum_H1.getValueInt());
            scrnMsg.A.no(i).xxDealApplyAmtNum_A1.setAppFracDigit(2);
            scrnMsg.A.no(i).xxDealApplyAdjAmtNum_A1.setAppFracDigit(2);
        }
    }

    /**
     * @param scrnMsg NFCL0770BMsg
     * @return boolean(true:batch/false:online)
     */
    public static boolean isApiExecuteBatchMsgCd(NFCL0770BMsg scrnMsg) {
        return "NFCM0205I".equals(scrnMsg.getMessageCode());
    }

    /**
     * <pre>
     * protect elements.
     * @param scrnMsg Screen Message
     * @param scrnAppli Screen Application Object
     * </pre>
     */
    public static void protectModeOne(NFCL0770BMsg scrnMsg, EZDCommonHandler scrnAppli) {

        boolean isHeaderApplied = S21StringUtil.isEquals(AR_RCPT_STS.APPLIED, scrnMsg.arRcptStsCd_H1.getValue());
        int num = scrnMsg.A.getValidCount();

        scrnAppli.setButtonEnabled("DownloadCSV", false);
        scrnAppli.setButtonEnabled("SearchTrxAllLine", false);

        if (isHeaderApplied) {
            scrnMsg.payerCustCd.setInputProtected(true);
            scrnAppli.setButtonEnabled("CustomerName", false);
            scrnAppli.setButtonEnabled("OpenWin_SearchAccountByTrxPopup", false);
            scrnAppli.setButtonEnabled("CalcGrossAmount", false);
            scrnAppli.setButtonEnabled("btn11", false);
            scrnAppli.setButtonEnabled("OpenWin_New", false);
        } else {
            scrnMsg.payerCustCd.setInputProtected(false);
            scrnAppli.setButtonEnabled("CustomerName", true);
            scrnAppli.setButtonEnabled("OpenWin_SearchAccountByTrxPopup", true);
            scrnAppli.setButtonEnabled("CalcGrossAmount", true);
            scrnAppli.setButtonEnabled("btn11", true);
            if (scrnMsg.arRcptStsCd_H1.getValue().equals(AR_RCPT_STS.REFUND)) {
                scrnAppli.setButtonEnabled("OpenWin_New", false);
            } else {
                scrnAppli.setButtonEnabled("OpenWin_New", true);
            }
        }

        if (scrnMsg.A.getValidCount() == 0) {
            scrnAppli.setButtonEnabled("Check_All", false);
            scrnAppli.setButtonEnabled("Un_Check_All", false);
            // add start 2022/09/02 QC#60403
            scrnMsg.arCustRefNum_H1.setInputProtected(true);
            scrnAppli.setButtonEnabled("Search", false);
            // add end 2022/09/02 QC#60403
        } else {
            if (isHeaderApplied) {
                scrnAppli.setButtonEnabled("Check_All", false);
                scrnAppli.setButtonEnabled("Un_Check_All", false);
            } else {
                scrnAppli.setButtonEnabled("Check_All", true);
                scrnAppli.setButtonEnabled("Un_Check_All", true);
            }
            // add start 2022/09/02 QC#60403
            scrnMsg.arCustRefNum_H1.setInputProtected(false);
            scrnAppli.setButtonEnabled("Search", true);
            // add end 2022/09/02 QC#60403
        }

        for (int i = 0; i < num; i++) {
            if (scrnMsg.A.no(i).xxArCashApplyStsTxt.getValue().equals(RECORD_STS.APPLIED.getValue())) {
                scrnMsg.A.no(i).xxChkBox.setInputProtected(true);
                scrnMsg.A.no(i).arCustRefNum.setInputProtected(true);
                scrnMsg.A.no(i).xxDealApplyAmtNum_A1.setInputProtected(true);
                scrnAppli.setButtonEnabled("SearchTrxLine", i, false);
                scrnAppli.setButtonEnabled("OpenWin_Select", i, false);
                scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(true);
            } else {
                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).arTrxTpCd)
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
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.dsAcctNm)) {
            protectCustomer(scrnMsg, scrnAppli);
        }
    }

    /**
     * hasUpdateFuncId
     * @return boolean
     */
    private static boolean hasUpdateFuncId(NFCL0770BMsg scrnMsg) {
        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();

        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(BIZ_ID);
        if (funcList == null || funcList.isEmpty()) {
            scrnMsg.setMessageInfo(ZZSM4300E, new String[] {userProfSvc.getContextUserInfo().getUserId()});
            return false;
        }
        if (funcList.contains(FUNC_ID_UPDATE)) {
            return true;
        }
        return false;
    }

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

    /**
     * <pre>
     * Protect Customer name/>>/Search By Trx
     * @param scrnMsg Screen Message
     * @param scrnAppli Screeen Handler
     */
    public static void protectCustomer(NFCL0770BMsg scrnMsg, EZDCommonHandler scrnAppli) {

        scrnMsg.payerCustCd.setInputProtected(true);
        scrnAppli.setButtonEnabled("CustomerName", false);
        scrnAppli.setButtonEnabled("OpenWin_SearchAccountByTrxPopup", false);
    }

    /**
     * <pre>
     * Unprotect Customer name/>>/Search By Trx
     * @param scrnMsg Screen Message
     * @param scrnAppli Screeen Handler
     */
    public static void unProtectCustomer(NFCL0770BMsg scrnMsg, EZDCommonHandler scrnAppli) {

        scrnMsg.payerCustCd.setInputProtected(false);
        scrnAppli.setButtonEnabled("CustomerName", true);
        scrnAppli.setButtonEnabled("OpenWin_SearchAccountByTrxPopup", true);
    }

    // add start 2022/09/02 QC#60403
    /**
     * @return bizMsg NFCL0770CMsg
     */
    public static NFCL0770CMsg setRequestData_NFCL0770Scrn00_OnBlur_Inv() {

        NFCL0770CMsg bizMsg;

        bizMsg = new NFCL0770CMsg();
        bizMsg.setBusinessID("NFCL0770");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @return bizMsg NFCL0770CMsg
     */
    public static NFCL0770CMsg setRequestData_NFCL0770Scrn00_Search() {

        NFCL0770CMsg bizMsg;

        bizMsg = new NFCL0770CMsg();
        bizMsg.setBusinessID("NFCL0770");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }
    // add end 2022/09/02 QC#60403
}