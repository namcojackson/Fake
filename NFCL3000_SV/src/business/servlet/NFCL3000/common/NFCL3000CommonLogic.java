package business.servlet.NFCL3000.common;

import static com.canon.cusa.s21.common.NWX.NWXC150001.constant.NWXC150001ConstantsForOtherBiz.NSAL1240_MODE_02;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsgArray;
import parts.common.EZDGUIAttribute;
import parts.common.EZDMessageInfo;
import parts.servletcommon.EZDCommonHandler;
import business.blap.NFCL3000.NFCL3000CMsg;
import business.servlet.NFCL3000.NFCL3000BMsg;
import business.servlet.NFCL3000.NFCL3000_ABMsg;
import business.servlet.NFCL3000.constant.NFCL3000Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AJE_INV_ACCT_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_PMT_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.internal.calendar.S21CalendarUtilConstants;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/21   Fujitsu         T.Tanaka        Create          N/A
 * 2016/04/05   Fujitsu         T.Tanaka        Update          Def#6469
 * 2016/05/05   CSAI            K.Uramori       Update          QC#7863
 * 2016/05/12   Hitachi         K.Kojima        Update          QC#4492
 * 2016/05/19   Fujitsu         S.Fujita        Update          QC#8478
 * 2016/05/24   Fujitsu         S.Fujita        Update          QC#8522
 * 2016/06/03   Fujitsu         S.Fujita        Update          QC#9157
 * 2016/06/03   Fujitsu         S.Fujita        Update          QC#9452
 * 2016/06/16   Fujitsu         S.Fujita        Update          QC#10254
 * 2016/06/16   Fujitsu         S.Fujita        Update          QC#10254
 * 2016/06/20   Fujitsu         S.Fujita        Update          QC#9454
 * 2016/07/04   Fujitsu         S.Fujita        Update          QC#11165
 * 2016/07/05   Fujitsu         S.Fujita        Update          QC#9992
 * 2016/07/13   Fujitsu         S.Fujita        Update          QC#11445
 * 2016/07/13   Fujitsu         S.Fujita        Update          QC#11447
 * 2016/07/14   Fujitsu         S.Fujita        Update          QC#11157
 * 2016/07/20   Fujitsu         S.Fujita        Update          QC#10148
 * 2016/08/05   Fujitsu         S.Yoshida       Update          QC#12862,12867
 * 2016/08/22   Fujitsu         S.Fujita        Update          QC#12579
 * 2016/09/05   Fujitsu         S.Fujita        Update          QC#13648
 * 2016/09/06   Fujitsu         S.Fujita        Update          QC#13119
 * 2016/09/06   Fujitsu         S.Fujita        Update          QC#14111
 * 2016/09/08   Fujitsu         S.Fujita        Update          QC#14115
 * 2016/09/12   Fujitsu         S.Fujita        Update          QC#14112
 * 2016/09/15   Fujitsu         S.Yoshida       Update          QC#13956
 * 2016/09/16   Fujitsu         S.Yoshida       Update          QC#13491,14089
 * 2016/09/21   Fujitsu         S.Fujita        Update          QC#14481
 * 2016/09/26   Fujitsu         S.Fujita        Update          QC#13362
 * 2016/10/03   Fujitsu         S.Fujita        Update          QC#14120
 * 2016/10/03   Fujitsu         S.Fujita        Update          QC#13206
 * 2016/10/12   Fujitsu         S.Fujita        Update          QC#13652
 * 2016/10/13   Fujitsu         S.Fujita        Update          QC#13640
 * 2016/10/14   Fujitsu         S.Fujita        Update          QC#10281
 * 2016/10/24   Fujitsu         T.Murai         Update          QC#13656
 * 2016/10/31   Fujitsu         T.Murai         Update          QC#14546
 * 2016/11/29   Hitachi         Y.Tsuchimoto    Update          QC#16268
 * 2017/12/25   Hitachi         E.Kameishi      Update          QC#20312
 * 2018/02/23   Hitachi         E.Kameishi      Update          QC#24390
 * 2018/05/22   Fujitsu         Y.Matsui        Update          QC#21841
 * 2018/07/25   Fujitsu         S.Ohki          Update          QC#26968
 * 2018/08/13   Fujitsu         Y.Matsui        Update          QC#26968
 * 2018/09/14   Fujitsu         S.Ohki          Update          QC#28089
 * 2018/09/28   Fujitsu         T.Ogura         Update          QC#28526
 * 2018/10/24   Fujitsu         S.Takami        Update          QC#27069
 * 2018/11/06   Fujitsu         S.Ohki          Update          QC#29059-2
 * 2019/03/07   Fujitsu         S.Takami        Update          QC#30678
 * 2019/04/17   Fujitsu         S.Takami        Update          QC#31204
 * 2019/04/25   Fujitsu         S.Takami        Update          QC#50078
 * 2019/05/10   Fujitsu         S.Takami        Update          QC#50148
 * 2019/10/03   Fujitsu         S.Takami        Update          QC#53881
 *</pre>
 */
public class NFCL3000CommonLogic implements NFCL3000Constant {

    // START 2016/06/20 S.Fujita [QC#9454,MOD]
    /**
     * initialize
     * @param scrnAppli
     * @param scrnMsg
     * @param clearFlg
     */
    public static void initialize(EZDCommonHandler scrnAppli, NFCL3000BMsg scrnMsg, Boolean clearFlg) {

        if (BIZ_ID.equals(scrnMsg.appFuncId.getValue())) {

            if (ZYPCommonFunc.hasValue(scrnMsg.invNum_H1.getValue())) {
                // START 2016/09/06 S.Fujita [QC#13119,ADD]
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.fnlzInvFlg_H4.getValue())) {
                    NFCL3000CommonLogic.initialize_ReadOnly(scrnAppli, scrnMsg, true);
                // END   2016/09/06 S.Fujita [QC#13119,ADD]
                } else {
                    NFCL3000CommonLogic.initialize_Update(scrnAppli, scrnMsg, true);
                    scrnMsg.setFocusItem(scrnMsg.invTpCd_H1);
                }

            } else {
                NFCL3000CommonLogic.initialize_Init(scrnAppli, scrnMsg, clearFlg);
                scrnMsg.setFocusItem(scrnMsg.invTpCd_H1);
            }

        } else {

            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.fnlzInvFlg_H4.getValue())) {
                NFCL3000CommonLogic.initialize_ReadOnly(scrnAppli, scrnMsg, false);

            } else {
                if (SYS_SRC.S21_ACCOUNTING_AR.equals(scrnMsg.sysSrcCd_H1.getValue())) {
                    NFCL3000CommonLogic.initialize_Update(scrnAppli, scrnMsg, false);
                    scrnMsg.setFocusItem(scrnMsg.custIssPoNum_H1);
                } else {
                    NFCL3000CommonLogic.initialize_UpdateOtherInvoice(scrnAppli, scrnMsg);
                    scrnMsg.setFocusItem(scrnMsg.custIssPoNum_H1);
                }
            }
        }
    }
    // END   2016/06/20 S.Fujita [QC#9454,MOD]

    // START 2016/06/20 S.Fujita [QC#9454,MOD]
    /**
     * initialize_Init
     * @param scrnAppli
     * @param scrnMsg
     * @param clearFlg
     */
    public static void initialize_Init(EZDCommonHandler scrnAppli, NFCL3000BMsg scrnMsg, boolean clearFlg) {

        boolean hasUpdateFunc = NFCL3000CommonLogic.isFunction(scrnMsg);
        // Common Button
        scrnAppli.setButtonProperties("btn1" , ""            , "Save"    , 0, null);
        if (hasUpdateFunc) {
            scrnAppli.setButtonProperties("btn2" , "CMN_Submit"  , "Submit"  , 1, null);
        } else {
            scrnAppli.setButtonProperties("btn2" , "CMN_Submit"  , "Submit"  , 0, null);
        }
        scrnAppli.setButtonProperties("btn3" , ""            , "Apply"   , 0, null);
        scrnAppli.setButtonProperties("btn4" , ""            , "Approve" , 0, null);
        scrnAppli.setButtonProperties("btn5" , ""            , "Reject"  , 0, null);
        scrnAppli.setButtonProperties("btn6" , "CMN_Download", "Download", 0, null);
        scrnAppli.setButtonProperties("btn7" , ""            , "Delete"  , 0, null);
        scrnAppli.setButtonProperties("btn8" , "CMN_Clear"   , "Clear"   , 1, null);
        scrnAppli.setButtonProperties("btn9" , "CMN_Reset"   , "Reset"   , 0, null);
        scrnAppli.setButtonProperties("btn10", "CMN_Return"  , "Return"  , 1, null);

        scrnAppli.setButtonProperties("Search"   , "Search"   , "Search"  , 1, null);
        scrnAppli.setButtonProperties("Calc"     , "Calc"     , "Calc"    , 0, null);
        scrnAppli.setButtonProperties("SalesRepName", "SalesRepName", ">>", 1, null);
        scrnAppli.setButtonProperties("WarehouseName", "WarehouseName", ">>", 1, null);
        scrnAppli.setButtonProperties("ShipToCustName", "ShipToCustName", ">>", 1, null);
        scrnAppli.setButtonProperties("ShipToLocName", "ShipToLocName", ">>", 1, null);
        scrnAppli.setButtonProperties("BillToCustName", "BillToCustName", ">>", 1, null);
        scrnAppli.setButtonProperties("BillToLocName", "BillToLocName", ">>", 1, null);
        // START 2016/05/12 K.Kojima [QC#4492,ADD]
        scrnAppli.setButtonProperties("OpenWin_AttachFile", "OpenWin_AttachFile", "Attachment", 0, null);
        // END 2016/05/12 K.Kojima [QC#4492,ADD]

        scrnMsg.setInputProtected(false);

        // Set Protect
        // START 2016/07/05 S.Fujita [QC#9992,ADD]
        scrnMsg.grpInvNum_H1.setInputProtected(true);
        scrnMsg.sysSrcCd_H1.setInputProtected(true);
        scrnMsg.sysSrcDescTxt_H1.setInputProtected(true);
        // END   2016/07/05 S.Fujita [QC#9992,ADD]
        // START 2016/06/03 S.Fujita [QC#9452,MOD]
//        scrnMsg.invNum_H1.setInputProtected(false);
        scrnMsg.invNum_H1.setInputProtected(true);
        // END   2016/06/03 S.Fujita [QC#9452,MOD]

        scrnMsg.netDueDt_H1.setInputProtected(true);

        scrnMsg.xxDsMsgEntryTxt_H2.setInputProtected(true);
        scrnMsg.xxDsMsgEntryTxt_H3.setInputProtected(true);

        // START 2016/07/28 S.Fujita [QC#10148,DEL]
//        scrnMsg.shipToCustCd_H2.setInputProtected(true);
//        scrnMsg.billToCustCd_H3.setInputProtected(true);
        // END   2016/07/28 S.Fujita [QC#10148,DEL]

        // START 2016/07/28 S.Fujita [QC#10148,DEL]
//        scrnMsg.shipToCustAcctCd_H2.setInputProtected(true);
//        scrnMsg.shipToCustAcctNm_H2.setInputProtected(true);
//        scrnMsg.locNum_H2.setInputProtected(true);
//        scrnMsg.billToCustAcctCd_H3.setInputProtected(true);
//        scrnMsg.billToCustAcctNm_H3.setInputProtected(true);
//        scrnMsg.locNum_H3.setInputProtected(true);
        // END   2016/07/28 S.Fujita [QC#10148,DEL]

        scrnMsg.slsRepTocNm_H1.setInputProtected(true);
        scrnMsg.rtlWhNm_H4.setInputProtected(true);

        scrnMsg.xxTotAmt_T1.setInputProtected(true);
        scrnMsg.xxTotAmt_T2.setInputProtected(true);
        scrnMsg.xxTotAmt_T3.setInputProtected(true);
        scrnMsg.xxTotAmt_T4.setInputProtected(true);
        scrnMsg.xxTotAmt_T5.setInputProtected(true);

        scrnMsg.xxChkBox_H2.setInputProtected(true);

        scrnMsg.xxChkBox_H3.setInputProtected(false);
        scrnMsg.xxChkBox_H4.setInputProtected(true);
        scrnMsg.xxChkBox_H5.setInputProtected(true);
        scrnMsg.xxChkBox_H6.setInputProtected(true);

        scrnMsg.invErrMsgTxt_H4.setInputProtected(true);
        scrnMsg.invldValTxt_H4.setInputProtected(true);
        // START 2016/08/01 S.Fujita [QC#10148,ADD]
        scrnMsg.xxDtTm_IN.setInputProtected(true);
        scrnMsg.ezUserID_H1.setInputProtected(true);
        scrnMsg.xxDtTm_UP.setInputProtected(true);
        scrnMsg.ezUpUserID_H1.setInputProtected(true);
        // END   2016/08/01 S.Fujita [QC#10148,ADD]

        // Set Mandatory
        scrnMsg.invNum_H1.setIndispensable(true);
        scrnMsg.invTpCd_H1.setIndispensable(true);
        scrnMsg.dsInvTpCd_H1.setIndispensable(true);
        scrnMsg.invDt_H1.setIndispensable(true);
        scrnMsg.pmtTermCashDiscCd_H1.setIndispensable(true);
        scrnMsg.netDueDt_H1.setIndispensable(true);
        // START 2016/07/05 S.Fujita [QC#9992,DEL]
//        scrnMsg.dfrdInvRuleCd_H1.setIndispensable(true);
//        scrnMsg.srcSysDocNum_H1.setIndispensable(true);
        // END   2016/07/05 S.Fujita [QC#9992,DEL]
        // START 2016/07/14 S.Fujita [QC#11157,MOD]
//        scrnMsg.slsRepTocCd_H1.setIndispensable(true);
        scrnMsg.psnNum_H1.setIndispensable(true);
        // END   2016/07/14 S.Fujita [QC#11157,MOD]

        //Def#6469
        //        scrnMsg.shipToCustAcctCd_H2.setIndispensable(true);
        //        scrnMsg.locNum_H2.setIndispensable(true);

        scrnMsg.billToCustAcctCd_H3.setIndispensable(true);
        scrnMsg.locNum_H3.setIndispensable(true);

        // Set After Digit
        int aftDeclPntDigitNum = scrnMsg.aftDeclPntDigitNum.getValueInt();
        scrnMsg.xxTotAmt_T1.setAppFracDigit(aftDeclPntDigitNum);
        scrnMsg.xxTotAmt_T2.setAppFracDigit(aftDeclPntDigitNum);
        scrnMsg.xxTotAmt_T3.setAppFracDigit(aftDeclPntDigitNum);
        scrnMsg.xxTotAmt_T4.setAppFracDigit(aftDeclPntDigitNum);
        scrnMsg.xxTotAmt_T5.setAppFracDigit(aftDeclPntDigitNum);

        scrnMsg.xxTotAmt_T1.setValue(BigDecimal.ZERO);
        scrnMsg.xxTotAmt_T2.setValue(BigDecimal.ZERO);
        scrnMsg.xxTotAmt_T3.setValue(BigDecimal.ZERO);
        scrnMsg.xxTotAmt_T4.setValue(BigDecimal.ZERO);
        scrnMsg.xxTotAmt_T5.setValue(BigDecimal.ZERO);

        // Invoice Line
        if (clearFlg) {
            scrnAppli.setButtonProperties("ItemSearch_A", "ItemSearch_A", "I"           , 1, null);
            scrnAppli.setButtonProperties("ItemName_A"  , "ItemName_A"  , ">>"          , 1, null);

            // START 2018/10/24 S.Takami [QC#27069, Add]
            scrnAppli.setButtonProperties("SerNumSearch_A", "SerNumSearch_A", "S"       , 1, null);
            // END   2018/10/24 S.Takami [QC#27069, Add]
        }

        scrnAppli.setButtonProperties("AddLine_A"   , "AddLine_A"   , "Add Line"    , 1, null);
        scrnAppli.setButtonProperties("DeleteLine_A", "DeleteLine_A", "Delete Line" , 0, null);

        for(int i = 0; i < scrnMsg.A.length(); i++) {
            // Digit
            scrnMsg.A.no(i).dealNetUnitPrcAmt_A1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.A.no(i).invLineDealNetAmt_A1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.A.no(i).invLineDealTaxAmt_A1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.A.no(i).xxTotAmt_A1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.A.no(i).unitCostAmt_A1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.A.no(i).funcCsmpCrAmt_A1.setAppFracDigit(aftDeclPntDigitNum);
            //---- start 2016/05/05 QC#7863 need till five decimal places
            //scrnMsg.A.no(i).taxPct_A1.setAppFracDigit(2);
            //---- end 2016/05/05

            // Protect
            scrnMsg.A.no(i).invLineDealNetAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxTotAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).shipDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).shipFromInvtyLocCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsCustTaxCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).invErrMsgTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).invldValTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).invLineDealTaxAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).taxPct_A1.setInputProtected(true);
            scrnMsg.A.no(i).invLineNum_A1.setInputProtected(true);

            // START 2016/08/01 S.Fujita [QC#10148,ADD]
            scrnMsg.A.no(i).svcConfigMstrPk_A1.setInputProtected(true);
            // END   2016/08/01 S.Fujita [QC#10148,ADD]

            // START 2016/10/14 S.Fujita [QC#10281,ADD]
            scrnMsg.A.no(i).csmpContrNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).dlrRefNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).funcCsmpCrAmt_A1.setInputProtected(true);
            // END   2016/10/14 S.Fujita [QC#10281,ADD]

            // START 2016/10/26 T.Murai [QC#13639, ADD]
            scrnMsg.A.no(i).serNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).cpoOrdNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxDplyOrdLineNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).ordDt_A1.setInputProtected(true);
            if (i == 0 && isManualInvoice(scrnMsg)) {
                scrnMsg.A.no(i).ordDt_A1.setInputProtected(false);
            }
            // END   2016/10/26 T.Murai [QC#13639, ADD]

            // Mandatory
            scrnMsg.A.no(i).invBolLineNum_A1.setIndispensable(true);
            scrnMsg.A.no(i).invLineNum_A1.setIndispensable(true);
            // START 2018/05/22 Y.Matsui [QC#21841,ADD]
            scrnMsg.A.no(i).invLineCatgCd_A1.setIndispensable(true);
            // END   2018/05/22 Y.Matsui [QC#21841,ADD]
            scrnMsg.A.no(i).mdseCd_A1.setIndispensable(true);
            scrnMsg.A.no(i).pkgUomCd_A1.setIndispensable(true);
            // START 2019/04/25 S.Takami [QC#50078,MOD]
//            // START 2018/09/28 T.Ogura [QC#28526,MOD]
////            scrnMsg.A.no(i).shipQty_A1.setIndispensable(true);
//            scrnMsg.A.no(i).ordCustUomQty_A1.setIndispensable(true);
//            // END   2018/09/28 T.Ogura [QC#28526,MOD]
            setQtyIndispensable(scrnMsg.A.no(i));
            // END 2019/04/25 S.Takami [QC#50078,MOD]
            scrnMsg.A.no(i).dealNetUnitPrcAmt_A1.setIndispensable(true);
            scrnMsg.A.no(i).unitCostAmt_A1.setIndispensable(true);
            scrnMsg.A.no(i).invLineDealTaxAmt_A1.setIndispensable(true);
            scrnMsg.A.no(i).taxPct_A1.setIndispensable(true);

            // START 2019/04/17 S.Takami [QC#31204,ADD]
            scrnMsg.A.no(i).dsContrNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).mdlNm_A1.setInputProtected(true);
            // END 2019/04/17 S.Takami [QC#31204,ADD]
        }

        // Sales Credit
        if (clearFlg) {
            scrnAppli.setButtonProperties("SalesRepSearch_B", "SalesRepSearch_B", "S"  , 1, null);
            scrnAppli.setButtonProperties("SalesRepName_B"  , "SalesRepName_B"  , ">>" , 1, null);
        }

        scrnAppli.setButtonProperties("AddLine_B"   , "AddLine_B"   , "Add Line"    , 1, null);
        scrnAppli.setButtonProperties("DeleteLine_B", "DeleteLine_B", "Delete Line" , 0, null);
        for(int i = 0; i < scrnMsg.B.length(); i++) {
            // Digit
            scrnMsg.B.no(i).invLineSplPct_B2.setAppFracDigit(2);
            scrnMsg.B.no(i).slsRepCrPct_B2.setAppFracDigit(2);
            scrnMsg.B.no(i).dealSlsCrAmt_B1.setAppFracDigit(aftDeclPntDigitNum);
            // START 2016/05/24 S.Fujita [QC#8522,MOD]
//            scrnMsg.B.no(i).dfrdAcctgRuleDurnAot_B1.setAppFracDigit(4);
            scrnMsg.B.no(i).dfrdAcctgRuleDurnAot_B1.setAppFracDigit(0);
            // END   2016/05/24 S.Fujita [QC#8522,MOD]
            scrnMsg.B.no(i).xxTotAmt_B1.setAppFracDigit(aftDeclPntDigitNum);

            // Protect
            scrnMsg.B.no(i).xxLineNum_B1.setInputProtected(true);
            scrnMsg.B.no(i).mdseCd_B1.setInputProtected(true);
//            scrnMsg.B.no(i).invLineSplPct_B2.setInputProtected(true);
            scrnMsg.B.no(i).tocNm_B1.setInputProtected(true);
            scrnMsg.B.no(i).dealSlsCrAmt_B1.setInputProtected(true);
            scrnMsg.B.no(i).invErrMsgTxt_B1.setInputProtected(true);
            scrnMsg.B.no(i).invldValTxt_B1.setInputProtected(true);

            scrnMsg.B.no(i).xxTotAmt_B1.setInputProtected(true);
            // START 2016/06/03 S.Fujita [QC#9157,MOD]
//            scrnMsg.B.no(i).invBolLineNum_B1.setInputProtected(true);
            scrnMsg.B.no(i).invBolLineNum_B1.setInputProtected(false);
            // END   2016/06/03 S.Fujita [QC#9157,MOD]

            // START 2016/08/01 S.Fujita [QC#10148,ADD]
            scrnMsg.B.no(i).coaBrNm_B1.setInputProtected(true);
            // END   2016/08/01 S.Fujita [QC#10148,ADD]

            // START 2016/10/13 S.Fujita [QC#13640,ADD]
            scrnMsg.B.no(i).dfrdAcctgRuleDurnAot_B1.setInputProtected(true);
            scrnMsg.B.no(i).durnStartDt_B1.setInputProtected(true);
            // END   2016/10/13 S.Fujita [QC#13640,ADD]

            // START 2017/12/25 E.Kameishi [QC#20312,ADD]
            scrnMsg.B.no(i).dealDfrdBalAmt_B1.setInputProtected(true);
            scrnMsg.B.no(i).firstRevRecogDt_B1.setInputProtected(true);
            scrnMsg.B.no(i).nextRevRecogDt_B1.setInputProtected(true);
            scrnMsg.B.no(i).dealSchdRevAmt_B1.setInputProtected(true);
            scrnMsg.B.no(i).revRecogCnt_B1.setInputProtected(true);
            scrnMsg.B.no(i).trxCd_B1.setInputProtected(true);
            scrnMsg.B.no(i).trxRsnCd_B1.setInputProtected(true);
            // END 2017/12/25 E.Kameishi [QC#20312,ADD]
            // Mandatory
            scrnMsg.B.no(i).invBolLineNum_B1.setIndispensable(true);
            scrnMsg.B.no(i).invLineNum_B1.setIndispensable(true);
            // START 2016/07/14 S.Fujita [QC#11157,MOD]
//            scrnMsg.B.no(i).slsRepTocCd_B1.setIndispensable(true);
            scrnMsg.B.no(i).psnNum_B1.setIndispensable(true);
            // END   2016/07/14 S.Fujita [QC#11157,MOD]
            scrnMsg.B.no(i).slsRepCrPct_B2.setIndispensable(true);
            // START 2016/09/05 S.Fujita [QC#13648,DEL]
//            scrnMsg.B.no(i).invLineSplPct_B2.setIndispensable(true);
            // END   2016/09/05 S.Fujita [QC#13648,DEL]

            // START 2016/10/31 T.Murai [QC#14546, ADD]
            if ( ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).xxHldFlg_B1.getValue())) {
                scrnMsg.B.no(i).setInputProtected(true);
                
                scrnAppli.setButtonEnabled("SalesRepSearch_B", i, false);
                scrnAppli.setButtonEnabled("SalesRepName_B"  , i  ,false);
            }
            // END   2016/10/31 T.Murai [QC#14546, ADD]
        }

        // Accounting
        scrnMsg.xxTotAmt_C1.setInputProtected(true);
        scrnMsg.xxTotAmt_C2.setInputProtected(true);
        scrnMsg.xxTotAmt_C1.setAppFracDigit(aftDeclPntDigitNum);
        scrnMsg.xxTotAmt_C2.setAppFracDigit(aftDeclPntDigitNum);
        scrnMsg.C.setInputProtected(true);
        for(int i = 0; i < scrnMsg.C.length(); i++) {
            // Digit
            scrnMsg.C.no(i).jrnlDealAmt_C1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.C.no(i).jrnlDealAmt_C2.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.C.no(i).ajeInvAcctDistPct_C1.setAppFracDigit(2);
        }

        // Invoice BOL
        if (clearFlg) {
            scrnAppli.setButtonProperties("WarehouseSearch_D", "WarehouseSearch_D", "W" , 1, null);
            scrnAppli.setButtonProperties("ShipToSearch_D", "ShipToSearch_D"      , "S" , 1, null);
            scrnAppli.setButtonProperties("ShipToName_D", "ShipToName_D"         , ">>" , 1, null);
            scrnAppli.setButtonProperties("ShipToLocSearch_D", "ShipToLocSearch_D"         , "L" , 1, null);
            scrnAppli.setButtonProperties("ShipToLocName_D", "ShipToLocName_D"         , ">>" , 1, null);
            scrnAppli.setButtonProperties("ShipToContactSearch_D", "ShipToContactSearch_D"         , "C" , 1, null);
        }

        scrnAppli.setButtonProperties("AddLine_D"   , "AddLine_D"   , "Add Line"    , 1, null);
        scrnAppli.setButtonProperties("DeleteLine_D", "DeleteLine_D", "Delete Line" , 0, null);
        for(int i = 0; i < scrnMsg.D.length(); i++) {
            // Digit
            scrnMsg.D.no(i).shipDealNetAmt_D1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.D.no(i).shipDealSlsAmt_D1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.D.no(i).shipDealFrtAmt_D1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.D.no(i).frtDealTaxAmt_D1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.D.no(i).shipDealDiscAmt_D1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.D.no(i).shipDealHdlgChrgAmt_D1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.D.no(i).totBolDealTaxAmt_D1.setAppFracDigit(aftDeclPntDigitNum);
            //---- start 2016/05/05 QC#7863 need till five decimal places
            //scrnMsg.D.no(i).frtTaxPct_D1.setAppFracDigit(2);
            //---- end 2016/05/05

            // Protect
            scrnMsg.D.no(i).carrNm_D1.setInputProtected(true);
            scrnMsg.D.no(i).shipToLocNm_D1.setInputProtected(true);
            scrnMsg.D.no(i).shpgSvcLvlNm_D1.setInputProtected(true);
            scrnMsg.D.no(i).invErrMsgTxt_D1.setInputProtected(true);
            scrnMsg.D.no(i).invldValTxt_D1.setInputProtected(true);

            scrnMsg.D.no(i).shipToLocNm_D1.setInputProtected(true);
            // START 2016/08/01 S.Fujita [QC#10148,DEL]
//            scrnMsg.D.no(i).shipToCustAcctNm_D1.setInputProtected(true);
            // END   2016/08/01 S.Fujita [QC#10148,DEL]
            scrnMsg.D.no(i).shipToFirstLineAddr_D1.setInputProtected(true);
            scrnMsg.D.no(i).shipToScdLineAddr_D1.setInputProtected(true);
            scrnMsg.D.no(i).shipToCtyAddr_D1.setInputProtected(true);
            scrnMsg.D.no(i).shipToStCd_D1.setInputProtected(true);
            scrnMsg.D.no(i).shipToPostCd_D1.setInputProtected(true);

            scrnMsg.D.no(i).frtDealTaxAmt_D1.setInputProtected(true);
            scrnMsg.D.no(i).frtTaxPct_D1.setInputProtected(true);
            scrnMsg.D.no(i).totBolDealTaxAmt_D1.setInputProtected(true);

            scrnMsg.D.no(i).invBolLineNum_D1.setInputProtected(true);

            // Mandatory
            scrnMsg.D.no(i).invBolLineNum_D1.setIndispensable(true);
//Def#6469
//            scrnMsg.D.no(i).shipToCustCd_D1.setIndispensable(true);
            scrnMsg.D.no(i).shipDealNetAmt_D1.setIndispensable(true);
            scrnMsg.D.no(i).shipDealSlsAmt_D1.setIndispensable(true);
            scrnMsg.D.no(i).shipDealFrtAmt_D1.setIndispensable(true);
            scrnMsg.D.no(i).frtTaxPct_D1.setIndispensable(true);
            scrnMsg.D.no(i).frtDealTaxAmt_D1.setIndispensable(true);
            scrnMsg.D.no(i).shipDealDiscAmt_D1.setIndispensable(true);
            scrnMsg.D.no(i).shipDealHdlgChrgAmt_D1.setIndispensable(true);

        }
        // More Info
        // Digit
        scrnMsg.dealCltDsptAmt_E1.setAppFracDigit(aftDeclPntDigitNum);
        scrnMsg.prePmtAmt_E1.setAppFracDigit(aftDeclPntDigitNum);

        // Protect
        scrnMsg.contrRnwTotCnt_E1.setInputProtected(true);
        scrnMsg.invProcTpCd_E1.setInputProtected(true);
        scrnMsg.dsContrCatgCd_E1.setInputProtected(true);
        scrnMsg.invPrintProcDt_E1.setInputProtected(true);
        // START 2017/12/25 E.Kameishi [QC#20312,ADD]
        scrnMsg.invPrintCratStsCd_E1.setInputProtected(true);
        // END   2017/12/25 E.Kameishi [QC#20312,ADD]
        // START 2016/08/01 S.Fujita [QC#10148,MOD]
//        scrnMsg.dsOrdTpCd_E1.setInputProtected(true);
        scrnMsg.dsOrdCatgCd_E1.setInputProtected(true);
        // END   2016/08/01 S.Fujita [QC#10148,MOD]
        scrnMsg.arCashApplyStsCd_E1.setInputProtected(true);
        scrnMsg.origInvNum_E1.setInputProtected(true);
        scrnMsg.dealCltDsptAmt_E1.setInputProtected(true);
        scrnMsg.prePmtAmt_E1.setInputProtected(true);
        scrnMsg.cltDsptDt_E1.setInputProtected(true);

        scrnMsg.crCardTpCd_E1.setInputProtected(true);
        scrnMsg.crCardHldNm_E1.setInputProtected(true);
        // START 2016/09/16 S.Yoshida [QC#13956,MOD]
        scrnMsg.xxScrItem19Txt_E1.setInputProtected(true);
        // END   2016/09/16 S.Yoshida [QC#13956,MOD]
        scrnMsg.crCardExprYrMth_E1.setInputProtected(true);
        // START 2016/06/03 S.Fujita [QC#9157,ADD]
        scrnMsg.dsPmtMethCd_E1.setInputProtected(true);
        // END   2016/06/03 S.Fujita [QC#9157,ADD]

        // TAB Protect
        scrnMsg.xxTabProt_A.setInputProtected(false);
        scrnMsg.xxTabProt_B.setInputProtected(true);
        scrnMsg.xxTabProt_C.setInputProtected(true);
        scrnMsg.xxTabProt_D.setInputProtected(true);
        scrnMsg.xxTabProt_E.setInputProtected(false);

        // Function Button
        NFCL3000CommonLogic.setFuncButton(scrnAppli, scrnMsg);

        NFCL3000CommonLogic.setProtectLineInput(scrnMsg);

        NFCL3000CommonLogic.setProtectSlsCrInput(scrnMsg);

        NFCL3000CommonLogic.setProtectBOLInput(scrnAppli, scrnMsg);
    }
    // END   2016/06/20 S.Fujita [QC#9454,MOD]

    // START 2016/06/20 S.Fujita [QC#9454,MOD]
    /**
     * initialize_Update
     * @param scrnAppli
     * @param scrnMsg
     * @param newRegFlg
     */
    public static void initialize_Update(EZDCommonHandler scrnAppli, NFCL3000BMsg scrnMsg, boolean newRegFlg) {

        boolean hasUpdateFunc = NFCL3000CommonLogic.isFunction(scrnMsg);
        // Common Button
        scrnAppli.setButtonProperties("btn1" , ""            , "Save"    , 0, null);
        if (hasUpdateFunc) {
            scrnAppli.setButtonProperties("btn2" , "CMN_Submit"  , "Submit"  , 1, null);
        } else {
            scrnAppli.setButtonProperties("btn2" , "CMN_Submit"  , "Submit"  , 0, null);
        }
        scrnAppli.setButtonProperties("btn3" , ""            , "Apply"   , 0, null);
        scrnAppli.setButtonProperties("btn4" , ""            , "Approve" , 0, null);
        scrnAppli.setButtonProperties("btn5" , ""            , "Reject"  , 0, null);
        scrnAppli.setButtonProperties("btn6" , "CMN_Download", "Download", 1, null);
        scrnAppli.setButtonProperties("btn7" , ""            , "Delete"  , 0, null);
        // START 2016/09/08 S.Fujita [QC#14115,MOD]
//        scrnAppli.setButtonProperties("btn8" , "CMN_Clear"   , "Clear"   , 0, null);
        if (newRegFlg) {
            scrnAppli.setButtonProperties("btn8" , "CMN_Clear"   , "Clear"   , 1, null);
        } else {
            scrnAppli.setButtonProperties("btn8" , "CMN_Clear"   , "Clear"   , 0, null);
        }
        // END   2016/09/08 S.Fujita [QC#14115,MOD]
        scrnAppli.setButtonProperties("btn9" , "CMN_Reset"   , "Reset"   , 1, null);
        scrnAppli.setButtonProperties("btn10", "CMN_Return"  , "Return"  , 1, null);

        scrnAppli.setButtonProperties("Search"   , "Search"   , "Search"    , 0, null);
        scrnAppli.setButtonProperties("Calc"     , "Calc"     , "Calc"    , 1, null);
        scrnAppli.setButtonProperties("SalesRepName", "SalesRepName", ">>", 1, null);
        scrnAppli.setButtonProperties("WarehouseName", "WarehouseName", ">>", 1, null);
        scrnAppli.setButtonProperties("ShipToCustName", "ShipToCustName", ">>", 1, null);
        scrnAppli.setButtonProperties("ShipToLocName", "ShipToLocName", ">>", 1, null);
        scrnAppli.setButtonProperties("BillToCustName", "BillToCustName", ">>", 1, null);
        scrnAppli.setButtonProperties("BillToLocName", "BillToLocName", ">>", 1, null);
        // START 2016/05/12 K.Kojima [QC#4492,ADD]
        scrnAppli.setButtonProperties("OpenWin_AttachFile", "OpenWin_AttachFile", "Attachment", 1, null);
        // END 2016/05/12 K.Kojima [QC#4492,ADD]

        // Set Protect
        scrnMsg.setInputProtected(true);
        scrnMsg.A.setInputProtected(true);
        scrnMsg.B.setInputProtected(true);
        scrnMsg.C.setInputProtected(true);
        scrnMsg.D.setInputProtected(true);

        //----------------------------------------
        // Header
        //----------------------------------------
        // Set Protect
        scrnMsg.invTpCd_H1.setInputProtected(false);
        // START 2019/05/16 S.Takami [QC#50374,ADD]
        if (ZYPCommonFunc.hasValue(scrnMsg.prmCmntTxt_E1) && S21StringUtil.isEquals(INV_TP.INVOICE, scrnMsg.invTpCd_H1.getValue())) {
            scrnMsg.invTpCd_H1.setInputProtected(true);
        }
        // END 2019/05/16 S.Takami [QC#50374,ADD]
        scrnMsg.dsInvTpCd_H1.setInputProtected(false);
        scrnMsg.invDt_H1.setInputProtected(false);
        scrnMsg.pmtTermCashDiscCd_H1.setInputProtected(false);
        // START 2016/10/03 S.Fujita [QC#14120,DEL]
//        scrnMsg.dfrdInvRuleCd_H1.setInputProtected(false);
        // END   2016/10/03 S.Fujita [QC#14120,DEL]
        scrnMsg.srcSysDocNum_H1.setInputProtected(false);
        // START 2016/07/14 S.Fujita [QC#11157,MOD]
//        scrnMsg.slsRepTocCd_H1.setInputProtected(false);
        scrnMsg.psnNum_H1.setInputProtected(false);
        // END   2016/07/14 S.Fujita [QC#11157,MOD]
        scrnMsg.custIssPoNum_H1.setInputProtected(false);
        scrnMsg.custCareTktNum_H1.setInputProtected(false);
        if(!scrnMsg.fnlzInvFlg_H4.getValue().equals(ZYPConstant.FLG_ON_Y)) {
            scrnMsg.xxChkBox_H3.setInputProtected(false);
        }

        scrnMsg.slsRepTocCd_L1.setInputProtected(false);
        scrnMsg.shipToCustAcctCd_L2.setInputProtected(false);
        scrnMsg.locNum_L2.setInputProtected(false);
        scrnMsg.ctacPsnPk_L2.setInputProtected(false);
        scrnMsg.billToCustAcctCd_L3.setInputProtected(false);
        scrnMsg.locNum_L3.setInputProtected(false);
        scrnMsg.ctacPsnPk_L3.setInputProtected(false);

        scrnMsg.xxPsnNm_H2.setInputProtected(false);
        scrnMsg.xxPsnNm_H3.setInputProtected(false);

        scrnMsg.shipFromInvtyLocCd_L4.setInputProtected(false);
        scrnMsg.shipFromInvtyLocCd_H4.setInputProtected(false);
        scrnMsg.shipDt_H4.setInputProtected(false);

        // START 2016/08/01 S.Fujita [QC#10148,ADD]
        scrnMsg.shipToCustAcctCd_H2.setInputProtected(false);
        scrnMsg.shipToCustAcctNm_H2.setInputProtected(false);
        scrnMsg.locNum_H2.setInputProtected(false);
        scrnMsg.billToCustAcctCd_H3.setInputProtected(false);
        scrnMsg.billToCustAcctNm_H3.setInputProtected(false);
        scrnMsg.locNum_H3.setInputProtected(false);
        scrnMsg.acctDt_H1.setInputProtected(false);
        // END   2016/08/01 S.Fujita [QC#10148,ADD]

        // Set Mandatory
        scrnMsg.invNum_H1.setIndispensable(true);
        scrnMsg.invTpCd_H1.setIndispensable(true);
        scrnMsg.dsInvTpCd_H1.setIndispensable(true);
        scrnMsg.invDt_H1.setIndispensable(true);
        scrnMsg.pmtTermCashDiscCd_H1.setIndispensable(true);
        scrnMsg.netDueDt_H1.setIndispensable(true);
        // START 2016/07/05 S.Fujita [QC#9992,DEL]
//        scrnMsg.dfrdInvRuleCd_H1.setIndispensable(true);
//        scrnMsg.srcSysDocNum_H1.setIndispensable(true);
        // END   2016/07/05 S.Fujita [QC#9992,DEL]
        // START 2016/07/14 S.Fujita [QC#11157,MOD]
//        scrnMsg.slsRepTocCd_H1.setIndispensable(true);
        scrnMsg.psnNum_H1.setIndispensable(true);
        // END   2016/07/14 S.Fujita [QC#11157,MOD]

        // START 2018/07/25 S.Ohki [QC#26968,ADD]
        scrnMsg.xxComnColOrdTxt.setInputProtected(false);
        // END   2018/07/25 S.Ohki [QC#26968,ADD]

        // Set After Digit
        int aftDeclPntDigitNum = scrnMsg.aftDeclPntDigitNum.getValueInt();
        scrnMsg.xxTotAmt_T1.setAppFracDigit(aftDeclPntDigitNum);
        scrnMsg.xxTotAmt_T2.setAppFracDigit(aftDeclPntDigitNum);
        scrnMsg.xxTotAmt_T3.setAppFracDigit(aftDeclPntDigitNum);
        scrnMsg.xxTotAmt_T4.setAppFracDigit(aftDeclPntDigitNum);
        scrnMsg.xxTotAmt_T5.setAppFracDigit(aftDeclPntDigitNum);

        //----------------------------------------
        // Invoice Line TAB
        //----------------------------------------
        scrnAppli.setButtonProperties("AddLine_A"   , "AddLine_A"   , "Add Line"    , 1, null);
        scrnAppli.setButtonProperties("DeleteLine_A", "DeleteLine_A", "Delete Line" , 1, null);
        scrnMsg.xxChkBox_A.setInputProtected(false);
        scrnMsg.xxChkBox_AA.setInputProtected(false);

        // START 2016/10/26 T.Murai [QC#13639, ADD]
        if (isManualInvoice(scrnMsg)) {
            scrnMsg.A.no(0).ordDt_A1.setInputProtected(false);
        }
        // END   2016/10/26 T.Murai [QC#13639, ADD]

        for(int i = 0; i < scrnMsg.A.length(); i++) {
            // Set Protect
            scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
            scrnMsg.A.no(i).invBolLineNum_A1.setInputProtected(false);
            // START 2018/05/22 Y.Matsui [QC#21841,ADD]
            scrnMsg.A.no(i).invLineCatgCd_A1.setInputProtected(false);
            // END   2018/05/22 Y.Matsui [QC#21841,ADD]
            scrnMsg.A.no(i).mdseCd_A1.setInputProtected(false);
            scrnMsg.A.no(i).pkgUomCd_A1.setInputProtected(false);
            // START 2018/09/28 T.Ogura [QC#28526,MOD]
//            scrnMsg.A.no(i).shipQty_A1.setInputProtected(false);
            scrnMsg.A.no(i).ordCustUomQty_A1.setInputProtected(false);
            // END   2018/09/28 T.Ogura [QC#28526,MOD]
            // START 2018/11/06 S.Ohki [QC#29059-2,ADD]
            scrnMsg.A.no(i).shipQty_A1.setInputProtected(false);
            // END 2018/11/06 S.Ohki [QC#29059-2,ADD]
            scrnMsg.A.no(i).dealNetUnitPrcAmt_A1.setInputProtected(false);
            // START 2016/08/01 S.Fujita [QC#10148,DEL]
//            scrnMsg.A.no(i).taxPct_A1.setInputProtected(false);
            // END   2016/08/01 S.Fujita [QC#10148,DEL]
            //scrnMsg.A.no(i).cpoOrdNum_A1.setInputProtected(false); // 2016/10/27 T.Murai [QC#13639, DEL]
            //scrnMsg.A.no(i).ordDt_A1.setInputProtected(false); // 2016/10/27 T.Murai [QC#13639, DEL]
            scrnMsg.A.no(i).unitCostAmt_A1.setInputProtected(false);
            // START 2016/08/01 S.Fujita [QC#10148,ADD]
            scrnMsg.A.no(i).mdseNm_A1.setInputProtected(false);
            //scrnMsg.A.no(i).cpoDtlLineNum_A1.setInputProtected(false); // 2016/10/27 T.Murai [QC#13639, DEL]
            // START 2019/04/17 S.Takami [QC#31204,MOD]
//            scrnMsg.A.no(i).dsContrNum_A1.setInputProtected(false);
            scrnMsg.A.no(i).dsContrNum_A1.setInputProtected(true);
            // START 2019/04/17 S.Takami [QC#31204,MOD]
            scrnMsg.A.no(i).dsContrSqNum_A1.setInputProtected(false);
            // START 2019/04/17 S.Takami [QC#31204,MOD]
//            scrnMsg.A.no(i).mdlNm_A1.setInputProtected(false);
            scrnMsg.A.no(i).mdlNm_A1.setInputProtected(true);
            // END 2019/04/17 S.Takami [QC#31204,MOD]
            scrnMsg.A.no(i).svcInvChrgTpCd_A1.setInputProtected(false);
            scrnMsg.A.no(i).bllgPerFromDt_A1.setInputProtected(false);
            scrnMsg.A.no(i).bllgPerThruDt_A1.setInputProtected(false);
            scrnMsg.A.no(i).bllgCopyQty_A1.setInputProtected(false);
            scrnMsg.A.no(i).ordQty_A1.setInputProtected(false);
            scrnMsg.A.no(i).crDrRsnCd_A1.setInputProtected(false);
            scrnMsg.A.no(i).dsContrDtlPk_A1.setInputProtected(false);
            scrnMsg.A.no(i).manInvCratCmntTxt_A1.setInputProtected(false);
            // END   2016/08/01 S.Fujita [QC#10148,ADD]

            // START 2016/10/14 S.Fujita [QC#10281,ADD]
            scrnMsg.A.no(i).firstBllgAttrbValTxt_A1.setInputProtected(false);
            scrnMsg.A.no(i).scdBllgAttrbValTxt_A1.setInputProtected(false);
            scrnMsg.A.no(i).thirdBllgAttrbValTxt_A1.setInputProtected(false);
            scrnMsg.A.no(i).frthBllgAttrbValTxt_A1.setInputProtected(false);
            scrnMsg.A.no(i).fifthBllgAttrbValTxt_A1.setInputProtected(false);
            scrnMsg.A.no(i).sixthBllgAttrbValTxt_A1.setInputProtected(false);
            // END   2016/10/14 S.Fujita [QC#10281,ADD]

            // START 2019/04/25 S.Takami [QC#50078,ADD]
            scrnMsg.A.no(i).invDplyQty_A1.setInputProtected(false);
            scrnMsg.A.no(i).shipQty_A1.setInputProtected(false);
            scrnMsg.A.no(i).adjQtyDplyTxt_A1.setInputProtected(false);
            // END 2019/04/25 S.Takami [QC#50078,ADD]

            // Set Mandatory
            scrnMsg.A.no(i).invBolLineNum_A1.setIndispensable(true);
            scrnMsg.A.no(i).invLineNum_A1.setIndispensable(true);
            // START 2018/05/22 Y.Matsui [QC#21841,ADD]
            scrnMsg.A.no(i).invLineCatgCd_A1.setIndispensable(true);
            // END   2018/05/22 Y.Matsui [QC#21841,ADD]
            scrnMsg.A.no(i).mdseCd_A1.setIndispensable(true);
            scrnMsg.A.no(i).pkgUomCd_A1.setIndispensable(true);
            // START 2019/04/25 S.Takami [QC#50078,MOD]
//            // START 2018/09/28 T.Ogura [QC#28526,MOD]
////            scrnMsg.A.no(i).shipQty_A1.setIndispensable(true);
//            scrnMsg.A.no(i).ordCustUomQty_A1.setIndispensable(true);
//            // END   2018/09/28 T.Ogura [QC#28526,MOD]
            setQtyIndispensable(scrnMsg.A.no(i));
            // END 2019/04/25 S.Takami [QC#50078,MOD]
            scrnMsg.A.no(i).dealNetUnitPrcAmt_A1.setIndispensable(true);
            scrnMsg.A.no(i).unitCostAmt_A1.setIndispensable(true);
            scrnMsg.A.no(i).invLineDealTaxAmt_A1.setIndispensable(true);
            scrnMsg.A.no(i).taxPct_A1.setIndispensable(true);

            // Set After Digit
            scrnMsg.A.no(i).dealNetUnitPrcAmt_A1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.A.no(i).invLineDealNetAmt_A1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.A.no(i).invLineDealTaxAmt_A1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.A.no(i).xxTotAmt_A1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.A.no(i).unitCostAmt_A1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.A.no(i).funcCsmpCrAmt_A1.setAppFracDigit(aftDeclPntDigitNum);
        }

        //----------------------------------------
        // Sales Credit TAB
        //----------------------------------------
        scrnAppli.setButtonProperties("AddLine_B"   , "AddLine_B"   , "Add Line"    , 1, null);
        scrnAppli.setButtonProperties("DeleteLine_B", "DeleteLine_B", "Delete Line" , 1, null);
        scrnMsg.xxChkBox_B.setInputProtected(false);
        scrnMsg.xxChkBox_BA.setInputProtected(false);
        for(int i = 0; i < scrnMsg.B.length(); i++) {
            // Set Protect
            scrnMsg.B.no(i).xxChkBox_B1.setInputProtected(false);
            scrnMsg.B.no(i).invBolLineNum_B1.setInputProtected(false);
            scrnMsg.B.no(i).invLineNum_B1.setInputProtected(false);
            // START 2016/09/05 S.Fujita [QC#13648,DEL]
//            scrnMsg.B.no(i).invLineSplPct_B2.setInputProtected(false);
            // END   2016/09/05 S.Fujita [QC#13648,DEL]
            // START 2016/07/14 S.Fujita [QC#11157,MOD]
//            scrnMsg.B.no(i).slsRepTocCd_B1.setInputProtected(false);
            scrnMsg.B.no(i).psnNum_B1.setInputProtected(false);
            // END   2016/07/14 S.Fujita [QC#11157,MOD]
            scrnMsg.B.no(i).slsRepCrPct_B2.setInputProtected(false);
            scrnMsg.B.no(i).manInvCratCmntTxt_B1.setInputProtected(false);
            scrnMsg.B.no(i).dfrdAcctgRuleCd_B1.setInputProtected(false);

            // START 2016/10/13 S.Fujita [QC#13640,MOD]
//            scrnMsg.B.no(i).dfrdAcctgRuleDurnAot_B1.setInputProtected(false);
//            scrnMsg.B.no(i).durnStartDt_B1.setInputProtected(false);

            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).dfrdRevFlg_B1.getValue())) {
                scrnMsg.B.no(i).durnStartDt_B1.setInputProtected(false);
                if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.B.no(i).fixDurnFlg_B1.getValue())) {
                    scrnMsg.B.no(i).dfrdAcctgRuleDurnAot_B1.setInputProtected(false);
                }
            }
            // END   2016/10/13 S.Fujita [QC#13640,MOD]

            scrnMsg.B.no(i).invLineSplTpCd_B1.setInputProtected(false);

            // Set Mandatory
            scrnMsg.B.no(i).invBolLineNum_B1.setIndispensable(true);
            scrnMsg.B.no(i).invLineNum_B1.setIndispensable(true);
            // START 2016/07/14 S.Fujita [QC#11157,MOD]
//            scrnMsg.B.no(i).slsRepTocCd_B1.setIndispensable(true);
            scrnMsg.B.no(i).psnNum_B1.setIndispensable(true);
            // END   2016/07/14 S.Fujita [QC#11157,MOD]
            // START 2016/09/05 S.Fujita [QC#13648,DEL]
//            scrnMsg.B.no(i).invLineSplPct_B2.setIndispensable(true);
            // END   2016/09/05 S.Fujita [QC#13648,DEL]
            scrnMsg.B.no(i).slsRepCrPct_B2.setIndispensable(true);

            // Set After Digit
            scrnMsg.B.no(i).invLineSplPct_B1.setAppFracDigit(2);
            scrnMsg.B.no(i).slsRepCrPct_B1.setAppFracDigit(2);
            scrnMsg.B.no(i).dealSlsCrAmt_B1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.B.no(i).dfrdAcctgRuleDurnAot_B1.setAppFracDigit(0);
            scrnMsg.B.no(i).xxTotAmt_B1.setAppFracDigit(aftDeclPntDigitNum);

            // START 2016/10/31 T.Murai [QC#14546, ADD]
            if ( ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).xxHldFlg_B1.getValue())) {
                scrnMsg.B.no(i).setInputProtected(true);
                
                scrnAppli.setButtonEnabled("SalesRepSearch_B", i, false);
                scrnAppli.setButtonEnabled("SalesRepName_B"  , i  ,false);
            }
            // END   2016/10/31 T.Murai [QC#14546, ADD]
            
        }

        //----------------------------------------
        // Accounting TAB
        //----------------------------------------
        if(scrnMsg.C.getValidCount() > 0) {
            scrnAppli.setButtonProperties("AddLine_C"   , "AddLine_C"   , "Add Line"    , 0, null);
            scrnAppli.setButtonProperties("DeleteLine_C", "DeleteLine_C", "Delete Line" , 0, null);
        } else {
            scrnAppli.setButtonProperties("AddLine_C"   , "AddLine_C"   , "Add Line"    , 1, null);
            scrnAppli.setButtonProperties("DeleteLine_C", "DeleteLine_C", "Delete Line" , 1, null);
        }
        scrnMsg.xxRadioBtn_C.setInputProtected(false);
        // Set After Digit
        scrnMsg.xxTotAmt_C1.setAppFracDigit(aftDeclPntDigitNum);
        scrnMsg.xxTotAmt_C2.setAppFracDigit(aftDeclPntDigitNum);
        scrnMsg.C.setInputProtected(true);
        for(int i = 0; i < scrnMsg.C.length(); i++) {
            // Set After Digit
            scrnMsg.C.no(i).jrnlDealAmt_C1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.C.no(i).jrnlDealAmt_C2.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.C.no(i).ajeInvAcctDistPct_C1.setAppFracDigit(2);
        }

        //----------------------------------------
        // Invoice Shipping TAB
        //----------------------------------------
        scrnAppli.setButtonProperties("AddLine_D"   , "AddLine_D"   , "Add Line"    , 1, null);
        scrnAppli.setButtonProperties("DeleteLine_D", "DeleteLine_D", "Delete Line" , 1, null);
        scrnMsg.xxChkBox_D.setInputProtected(false);
        scrnMsg.xxChkBox_DA.setInputProtected(false);
        for(int i = 0; i < scrnMsg.D.length(); i++) {
            // Set Protect
            scrnMsg.D.no(i).xxChkBox_D1.setInputProtected(false);
            scrnMsg.D.no(i).shipDealFrtAmt_D1.setInputProtected(false);
            scrnMsg.D.no(i).shipFromInvtyLocCd_D1.setInputProtected(false);
            // START 2016/08/01 S.Fujita [QC#10148,MOD]
//            scrnMsg.D.no(i).shipToCustCd_D1.setInputProtected(false);
            scrnMsg.D.no(i).shipToCustAcctCd_D1.setInputProtected(false);
            scrnMsg.D.no(i).shipToCustAcctNm_D1.setInputProtected(false);
            scrnMsg.D.no(i).locNum_D1.setInputProtected(false);
            scrnMsg.D.no(i).xxPsnNm_D1.setInputProtected(false);
            // END   2016/08/01 S.Fujita [QC#10148,MOD]
            scrnMsg.D.no(i).shipDt_D1.setInputProtected(false);
            scrnMsg.D.no(i).soNum_D1.setInputProtected(false);
            scrnMsg.D.no(i).bolNum_D1.setInputProtected(false);

            // Set Mandatory
            scrnMsg.D.no(i).shipDealFrtAmt_D1.setIndispensable(true);
            scrnMsg.D.no(i).frtTaxPct_D1.setIndispensable(true);

            // Set After Digit
            scrnMsg.D.no(i).shipDealNetAmt_D1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.D.no(i).shipDealSlsAmt_D1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.D.no(i).shipDealFrtAmt_D1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.D.no(i).frtDealTaxAmt_D1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.D.no(i).shipDealDiscAmt_D1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.D.no(i).shipDealHdlgChrgAmt_D1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.D.no(i).totBolDealTaxAmt_D1.setAppFracDigit(aftDeclPntDigitNum);
        }

        //----------------------------------------
        // More Info.
        //----------------------------------------
        // Set Protect
        scrnMsg.invPrintStsCd_E1.setInputProtected(false);
        // START 2019/05/10 S.Takami [QC#50148,MOD]
//        scrnMsg.invFirstCmntTxt_E1.setInputProtected(false);
        scrnMsg.xxInvMemoTxt_E1.setInputProtected(false);
        // END 2019/05/10 S.Takami [QC#50148,MOD]
        scrnMsg.dsPmtMethCd_E1.setInputProtected(false);

        // Set After Digit
        scrnMsg.dealCltDsptAmt_E1.setAppFracDigit(aftDeclPntDigitNum);
        scrnMsg.prePmtAmt_E1.setAppFracDigit(aftDeclPntDigitNum);

        // TAB Protect
        scrnMsg.xxTabProt_A.setInputProtected(false);
        scrnMsg.xxTabProt_B.setInputProtected(false);
        scrnMsg.xxTabProt_C.setInputProtected(false);
        scrnMsg.xxTabProt_D.setInputProtected(false);
        scrnMsg.xxTabProt_E.setInputProtected(false);

        // Function Button
        NFCL3000CommonLogic.setFuncButton(scrnAppli, scrnMsg);

    }
    // END   2016/06/20 S.Fujita [QC#9454,MOD]

    // START 2016/06/20 S.Fujita [QC#9454,MOD]
    /**
     * except Manual Invoice
     * @param scrnAppli
     * @param scrnMsg
     */
    public static void initialize_UpdateOtherInvoice(EZDCommonHandler scrnAppli, NFCL3000BMsg scrnMsg) {

        boolean hasUpdateFunc = NFCL3000CommonLogic.isFunction(scrnMsg);
        // Common Button
        scrnAppli.setButtonProperties("btn1" , ""            , "Save"    , 0, null);
        if (hasUpdateFunc) {
            scrnAppli.setButtonProperties("btn2" , "CMN_Submit"  , "Submit"  , 1, null);
        } else {
            scrnAppli.setButtonProperties("btn2" , "CMN_Submit"  , "Submit"  , 0, null);
        }
        scrnAppli.setButtonProperties("btn3" , ""            , "Apply"   , 0, null);
        scrnAppli.setButtonProperties("btn4" , ""            , "Approve" , 0, null);
        scrnAppli.setButtonProperties("btn5" , ""            , "Reject"  , 0, null);
        scrnAppli.setButtonProperties("btn6" , "CMN_Download", "Download", 1, null);
        scrnAppli.setButtonProperties("btn7" , ""            , "Delete"  , 0, null);
        scrnAppli.setButtonProperties("btn8" , "CMN_Clear"   , "Clear"   , 0, null);
        scrnAppli.setButtonProperties("btn9" , "CMN_Reset"   , "Reset"   , 1, null);
        scrnAppli.setButtonProperties("btn10", "CMN_Return"  , "Return"  , 1, null);
        scrnAppli.setButtonProperties("Search"   , "Search"   , "Search" , 0, null);
        scrnAppli.setButtonProperties("Calc"     , "Calc"     , "Calc"    , 0, null);
        scrnAppli.setButtonProperties("SalesRepName", "SalesRepName", ">>", 0, null);
        scrnAppli.setButtonProperties("WarehouseName", "WarehouseName", ">>", 0, null);
        scrnAppli.setButtonProperties("ShipToCustName", "ShipToCustName", ">>", 0, null);
        scrnAppli.setButtonProperties("ShipToLocName", "ShipToLocName", ">>", 0, null);
        scrnAppli.setButtonProperties("BillToCustName", "BillToCustName", ">>", 0, null);
        scrnAppli.setButtonProperties("BillToLocName", "BillToLocName", ">>", 0, null);
        scrnAppli.setButtonProperties("OpenWin_AttachFile", "OpenWin_AttachFile", "Attachment", 1, null);

        // Set Protect All
        scrnMsg.setInputProtected(true);
        scrnMsg.A.setInputProtected(true);
        scrnMsg.B.setInputProtected(true);
        scrnMsg.C.setInputProtected(true);
        scrnMsg.D.setInputProtected(true);

        // START 2018/08/13 Y.Matsui [QC#26968,ADD]
        scrnMsg.xxComnColOrdTxt.setInputProtected(false);
        // END   2018/08/13 Y.Matsui [QC#26968,ADD]

        //----------------------------------------
        // Header
        //----------------------------------------

        // START 2016/09/06 S.Fujita [QC#14111,ADD]
        // Source Number
        scrnMsg.srcSysDocNum_H1.setInputProtected(false);
        // END   2016/09/06 S.Fujita [QC#14111,ADD]
        // PO Number
        scrnMsg.custIssPoNum_H1.setInputProtected(false);
        // Complete check box
        scrnMsg.xxChkBox_H3.setInputProtected(true);

        // Set After Digit
        int aftDeclPntDigitNum = scrnMsg.aftDeclPntDigitNum.getValueInt();
        scrnMsg.xxTotAmt_T1.setAppFracDigit(aftDeclPntDigitNum);
        scrnMsg.xxTotAmt_T2.setAppFracDigit(aftDeclPntDigitNum);
        scrnMsg.xxTotAmt_T3.setAppFracDigit(aftDeclPntDigitNum);
        scrnMsg.xxTotAmt_T4.setAppFracDigit(aftDeclPntDigitNum);
        scrnMsg.xxTotAmt_T5.setAppFracDigit(aftDeclPntDigitNum);

        //----------------------------------------
        // Invoice Line TAB
        //----------------------------------------
        scrnAppli.setButtonProperties("AddLine_A"   , "AddLine_A"   , "Add Line"    , 0, null);
        scrnAppli.setButtonProperties("DeleteLine_A", "DeleteLine_A", "Delete Line" , 0, null);
        scrnAppli.setButtonProperties("ItemSearch_A", "ItemSearch_A", "I"           , 0, null);
        scrnAppli.setButtonProperties("ItemName_A"  , "ItemName_A"  , ">>"          , 0, null);
        // START 2018/10/24 S.Takami [QC#27069, Add]
        scrnAppli.setButtonProperties("SerNumSearch_A", "SerNumSearch_A", "S"       , 0, null);
        // END   2018/10/24 S.Takami [QC#27069, Add]
        for(int i = 0; i < scrnMsg.A.length(); i++) {
            // Set After Digit
            scrnMsg.A.no(i).dealNetUnitPrcAmt_A1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.A.no(i).invLineDealNetAmt_A1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.A.no(i).invLineDealTaxAmt_A1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.A.no(i).xxTotAmt_A1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.A.no(i).unitCostAmt_A1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.A.no(i).funcCsmpCrAmt_A1.setAppFracDigit(aftDeclPntDigitNum);
        }

        //----------------------------------------
        // Sales Credit TAB
        //----------------------------------------

        scrnAppli.setButtonProperties("AddLine_B"   , "AddLine_B"   , "Add Line"    , 1, null);
        scrnAppli.setButtonProperties("DeleteLine_B", "DeleteLine_B", "Delete Line" , 1, null);
        scrnMsg.xxChkBox_B.setInputProtected(false);
        scrnMsg.xxChkBox_BA.setInputProtected(false);
        for(int i = 0; i < scrnMsg.B.length(); i++) {
            // Set Protect
            scrnMsg.B.no(i).xxChkBox_B1.setInputProtected(false);
            scrnMsg.B.no(i).invBolLineNum_B1.setInputProtected(false);
            scrnMsg.B.no(i).invLineNum_B1.setInputProtected(false);
            // START 2016/09/05 S.Fujita [QC#13648,DEL]
//            scrnMsg.B.no(i).invLineSplPct_B2.setInputProtected(false);
            // END   2016/09/05 S.Fujita [QC#13648,DEL]
            // START 2016/07/14 S.Fujita [QC#11157,MOD]
//            scrnMsg.B.no(i).slsRepTocCd_B1.setInputProtected(false);
            scrnMsg.B.no(i).psnNum_B1.setInputProtected(false);
            // END   2016/07/14 S.Fujita [QC#11157,MOD]
            scrnMsg.B.no(i).slsRepCrPct_B2.setInputProtected(false);
            scrnMsg.B.no(i).manInvCratCmntTxt_B1.setInputProtected(false);
            scrnMsg.B.no(i).dfrdAcctgRuleCd_B1.setInputProtected(false);

            // START 2016/10/13 S.Fujita [QC#13640,MOD]
//            scrnMsg.B.no(i).dfrdAcctgRuleDurnAot_B1.setInputProtected(false);
//            scrnMsg.B.no(i).durnStartDt_B1.setInputProtected(false);
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).dfrdRevFlg_B1.getValue())) {
                scrnMsg.B.no(i).durnStartDt_B1.setInputProtected(false);
                if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.B.no(i).fixDurnFlg_B1.getValue())) {
                    scrnMsg.B.no(i).dfrdAcctgRuleDurnAot_B1.setInputProtected(false);
                }
            }
            // END   2016/10/13 S.Fujita [QC#13640,MOD]

            scrnMsg.B.no(i).invLineSplTpCd_B1.setInputProtected(false);

            // Set Mandatory
            scrnMsg.B.no(i).invBolLineNum_B1.setIndispensable(true);
            scrnMsg.B.no(i).invLineNum_B1.setIndispensable(true);
            // START 2016/09/05 S.Fujita [QC#13648,DEL]
//            scrnMsg.B.no(i).invLineSplPct_B2.setIndispensable(true);
            // END   2016/09/05 S.Fujita [QC#13648,DEL]
            // START 2016/07/14 S.Fujita [QC#11157,MOD]
//            scrnMsg.B.no(i).slsRepTocCd_B1.setIndispensable(true);
            scrnMsg.B.no(i).psnNum_B1.setIndispensable(true);
            // END   2016/07/14 S.Fujita [QC#11157,MOD]
            scrnMsg.B.no(i).slsRepCrPct_B2.setIndispensable(true);

            // Set After Digit
            scrnMsg.B.no(i).invLineSplPct_B1.setAppFracDigit(2);
            scrnMsg.B.no(i).slsRepCrPct_B1.setAppFracDigit(2);
            scrnMsg.B.no(i).dealSlsCrAmt_B1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.B.no(i).dfrdAcctgRuleDurnAot_B1.setAppFracDigit(0);
            scrnMsg.B.no(i).xxTotAmt_B1.setAppFracDigit(aftDeclPntDigitNum);

            // START 2016/10/31 T.Murai [QC#14546, ADD]
            if ( ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).xxHldFlg_B1.getValue())) {
                scrnMsg.B.no(i).setInputProtected(true);
                
                scrnAppli.setButtonEnabled("SalesRepSearch_B", i, false);
                scrnAppli.setButtonEnabled("SalesRepName_B"  , i  ,false);
            }
            // END   2016/10/31 T.Murai [QC#14546, ADD]
            
        }

        //----------------------------------------
        // Accounting TAB
        //----------------------------------------
        scrnAppli.setButtonProperties("AddLine_C"   , "AddLine_C"   , "Add Line"    , 0, null);
        scrnAppli.setButtonProperties("DeleteLine_C", "DeleteLine_C", "Delete Line" , 0, null);
        scrnMsg.xxRadioBtn_C.setInputProtected(false);
        // Set After Digit
        scrnMsg.xxTotAmt_C1.setAppFracDigit(aftDeclPntDigitNum);
        scrnMsg.xxTotAmt_C2.setAppFracDigit(aftDeclPntDigitNum);
        for(int i = 0; i < scrnMsg.C.length(); i++) {
            // Set After Digit
            scrnMsg.C.no(i).jrnlDealAmt_C1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.C.no(i).jrnlDealAmt_C2.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.C.no(i).ajeInvAcctDistPct_C1.setAppFracDigit(2);
        }

        //----------------------------------------
        // Invoice BOL TAB
        //----------------------------------------
        scrnAppli.setButtonProperties("AddLine_D"   , "AddLine_D"   , "Add Line"    , 0, null);
        scrnAppli.setButtonProperties("DeleteLine_D", "DeleteLine_D", "Delete Line" , 0, null);
        scrnAppli.setButtonProperties("WarehouseSearch_D", "WarehouseSearch_D", "W" , 0, null);
        scrnAppli.setButtonProperties("ShipToSearch_D", "ShipToSearch_D"      , "S" , 0, null);
        scrnAppli.setButtonProperties("ShipToName_D", "ShipToName_D"         , ">>" , 0, null);
        scrnAppli.setButtonProperties("ShipToLocSearch_D", "ShipToLocSearch_D"         , "L" , 0, null);
        scrnAppli.setButtonProperties("ShipToLocName_D", "ShipToLocName_D"         , ">>" , 0, null);
        scrnAppli.setButtonProperties("ShipToContactSearch_D", "ShipToContactSearch_D"         , "C" , 0, null);
        for(int i = 0; i < scrnMsg.D.length(); i++) {
            // Set After Digit
            scrnMsg.D.no(i).shipDealNetAmt_D1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.D.no(i).shipDealSlsAmt_D1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.D.no(i).shipDealFrtAmt_D1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.D.no(i).frtDealTaxAmt_D1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.D.no(i).shipDealDiscAmt_D1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.D.no(i).shipDealHdlgChrgAmt_D1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.D.no(i).totBolDealTaxAmt_D1.setAppFracDigit(aftDeclPntDigitNum);
        }

        //----------------------------------------
        // More Info.
        //----------------------------------------
        // Set After Digit
        scrnMsg.dealCltDsptAmt_E1.setAppFracDigit(aftDeclPntDigitNum);
        scrnMsg.prePmtAmt_E1.setAppFracDigit(aftDeclPntDigitNum);

        // Set Protect
        // START 2016/09/06 S.Fujita [QC#14111,DEL]
//        scrnMsg.invPrintStsCd_E1.setInputProtected(false);
//        scrnMsg.dsPmtMethCd_E1.setInputProtected(false);
        // END   2016/09/06 S.Fujita [QC#14111,DEL]

        // Comments
        // START 2019/05/10 S.Takami [QC#50148,MOD]
//        scrnMsg.invFirstCmntTxt_E1.setInputProtected(false);
        scrnMsg.xxInvMemoTxt_E1.setInputProtected(false);
        // END 2019/05/10 S.Takami [QC#50148,MOD]

        // TAB Protect
        scrnMsg.xxTabProt_A.setInputProtected(false);
        scrnMsg.xxTabProt_B.setInputProtected(false);
        scrnMsg.xxTabProt_C.setInputProtected(false);
        scrnMsg.xxTabProt_D.setInputProtected(false);
        scrnMsg.xxTabProt_E.setInputProtected(false);

        // Function Button
        NFCL3000CommonLogic.setFuncButton(scrnAppli, scrnMsg);
    }
    // END   2016/06/20 S.Fujita [QC#9454,MOD]

    // START 2016/06/20 S.Fujita [QC#9454,MOD]
    /**
     * except Manual Invoice
     * @param scrnAppli
     * @param scrnMsg
     * @param newRegFlg
     */
    public static void initialize_ReadOnly(EZDCommonHandler scrnAppli, NFCL3000BMsg scrnMsg, boolean newRegFlg) {

        boolean hasUpdateFunc = NFCL3000CommonLogic.isFunction(scrnMsg);
        // Common Button
        scrnAppli.setButtonProperties("btn1" , ""            , "Save"    , 0, null);
        if (hasUpdateFunc) {
            scrnAppli.setButtonProperties("btn2" , "CMN_Submit"  , "Submit"  , 1, null);
        } else {
            scrnAppli.setButtonProperties("btn2" , "CMN_Submit"  , "Submit"  , 0, null);
        }
        scrnAppli.setButtonProperties("btn3" , ""            , "Apply"   , 0, null);
        scrnAppli.setButtonProperties("btn4" , ""            , "Approve" , 0, null);
        scrnAppli.setButtonProperties("btn5" , ""            , "Reject"  , 0, null);
        scrnAppli.setButtonProperties("btn6" , "CMN_Download", "Download", 1, null);
        scrnAppli.setButtonProperties("btn7" , ""            , "Delete"  , 0, null);
       // START 2016/09/08 S.Fujita [QC#14115,MOD]
//      scrnAppli.setButtonProperties("btn8" , "CMN_Clear"   , "Clear"   , 0, null);
        if (newRegFlg) {
            scrnAppli.setButtonProperties("btn8" , "CMN_Clear"   , "Clear"   , 1, null);
        } else {
            scrnAppli.setButtonProperties("btn8" , "CMN_Clear"   , "Clear"   , 0, null);
        }
        // END   2016/09/08 S.Fujita [QC#14115,MOD]
        scrnAppli.setButtonProperties("btn9" , "CMN_Reset"   , "Reset"   , 1, null);
        scrnAppli.setButtonProperties("btn10", "CMN_Return"  , "Return"  , 1, null);
        scrnAppli.setButtonProperties("Search"   , "Search"   , "Search" , 0, null);
        scrnAppli.setButtonProperties("Calc"     , "Calc"     , "Calc"    , 0, null);
        scrnAppli.setButtonProperties("SalesRepName", "SalesRepName", ">>", 0, null);
        scrnAppli.setButtonProperties("WarehouseName", "WarehouseName", ">>", 0, null);
        scrnAppli.setButtonProperties("ShipToCustName", "ShipToCustName", ">>", 0, null);
        scrnAppli.setButtonProperties("ShipToLocName", "ShipToLocName", ">>", 0, null);
        scrnAppli.setButtonProperties("BillToCustName", "BillToCustName", ">>", 0, null);
        scrnAppli.setButtonProperties("BillToLocName", "BillToLocName", ">>", 0, null);
        scrnAppli.setButtonProperties("OpenWin_AttachFile", "OpenWin_AttachFile", "Attachment", 1, null);

        // Set Protect All
        scrnMsg.setInputProtected(true);
        scrnMsg.A.setInputProtected(true);
        scrnMsg.B.setInputProtected(true);
        scrnMsg.C.setInputProtected(true);
        scrnMsg.D.setInputProtected(true);

        // START 2018/08/13 Y.Matsui [QC#26968,ADD]
        scrnMsg.xxComnColOrdTxt.setInputProtected(false);
        // END   2018/08/13 Y.Matsui [QC#26968,ADD]

        //----------------------------------------
        // Header
        //----------------------------------------

        // START 2016/09/06 S.Fujita [QC#14111,ADD]
        // Source Number
        // START 2017/12/25 E.Kameishi [QC#20312,DEL]
        //scrnMsg.srcSysDocNum_H1.setInputProtected(false);
        // END 2017/12/25 E.Kameishi [QC#20312,DEL]
        // PO Number
        scrnMsg.custIssPoNum_H1.setInputProtected(false);
        // END   2016/09/06 S.Fujita [QC#14111,ADD]

        // Set After Digit
        int aftDeclPntDigitNum = scrnMsg.aftDeclPntDigitNum.getValueInt();
        scrnMsg.xxTotAmt_T1.setAppFracDigit(aftDeclPntDigitNum);
        scrnMsg.xxTotAmt_T2.setAppFracDigit(aftDeclPntDigitNum);
        scrnMsg.xxTotAmt_T3.setAppFracDigit(aftDeclPntDigitNum);
        scrnMsg.xxTotAmt_T4.setAppFracDigit(aftDeclPntDigitNum);
        scrnMsg.xxTotAmt_T5.setAppFracDigit(aftDeclPntDigitNum);

        //----------------------------------------
        // Invoice Line TAB
        //----------------------------------------
        scrnAppli.setButtonProperties("AddLine_A"   , "AddLine_A"   , "Add Line"    , 0, null);
        scrnAppli.setButtonProperties("DeleteLine_A", "DeleteLine_A", "Delete Line" , 0, null);
        scrnAppli.setButtonProperties("ItemSearch_A", "ItemSearch_A", "I"           , 0, null);
        scrnAppli.setButtonProperties("ItemName_A"  , "ItemName_A"  , ">>"          , 0, null);
        // START 2018/10/24 S.Takami [QC#27069, Add]
        scrnAppli.setButtonProperties("SerNumSearch_A", "SerNumSearch_A", "S"       , 0, null);
        // END   2018/10/24 S.Takami [QC#27069, Add]
        for(int i = 0; i < scrnMsg.A.length(); i++) {
            // Set After Digit
            scrnMsg.A.no(i).dealNetUnitPrcAmt_A1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.A.no(i).invLineDealNetAmt_A1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.A.no(i).invLineDealTaxAmt_A1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.A.no(i).xxTotAmt_A1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.A.no(i).unitCostAmt_A1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.A.no(i).funcCsmpCrAmt_A1.setAppFracDigit(aftDeclPntDigitNum);
        }

        //----------------------------------------
        // Sales Credit TAB
        //----------------------------------------
        scrnAppli.setButtonProperties("AddLine_B"   , "AddLine_B"   , "Add Line"    , 0, null);
        scrnAppli.setButtonProperties("DeleteLine_B", "DeleteLine_B", "Delete Line" , 0, null);
        scrnAppli.setButtonProperties("SalesRepSearch_B", "SalesRepSearch_B", "S"  , 0, null);
        scrnAppli.setButtonProperties("SalesRepName_B"  , "SalesRepName_B"  , ">>" , 0, null);
        for(int i = 0; i < scrnMsg.B.length(); i++) {
            // Set After Digit
            scrnMsg.B.no(i).invLineSplPct_B1.setAppFracDigit(2);
            scrnMsg.B.no(i).slsRepCrPct_B1.setAppFracDigit(2);
            scrnMsg.B.no(i).dealSlsCrAmt_B1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.B.no(i).dfrdAcctgRuleDurnAot_B1.setAppFracDigit(0);
            scrnMsg.B.no(i).xxTotAmt_B1.setAppFracDigit(aftDeclPntDigitNum);
        }

        //----------------------------------------
        // Accounting TAB
        //----------------------------------------
        scrnAppli.setButtonProperties("AddLine_C"   , "AddLine_C"   , "Add Line"    , 0, null);
        scrnAppli.setButtonProperties("DeleteLine_C", "DeleteLine_C", "Delete Line" , 0, null);
        // START 2016/09/16 S.Yoshida [QC#13491,DEL]
//        scrnAppli.setButtonProperties("GlCombnSearch_C", "GlCombnSearch_C", "A"     , 0, null);
        // END   2016/09/16 S.Yoshida [QC#13491,DEL]
        scrnMsg.xxRadioBtn_C.setInputProtected(false);
        // Set After Digit
        scrnMsg.xxTotAmt_C1.setAppFracDigit(aftDeclPntDigitNum);
        scrnMsg.xxTotAmt_C2.setAppFracDigit(aftDeclPntDigitNum);
        for(int i = 0; i < scrnMsg.C.length(); i++) {
            // Set After Digit
            scrnMsg.C.no(i).jrnlDealAmt_C1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.C.no(i).jrnlDealAmt_C2.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.C.no(i).ajeInvAcctDistPct_C1.setAppFracDigit(2);
        }

        //----------------------------------------
        // Invoice Shipping TAB
        //----------------------------------------
        scrnAppli.setButtonProperties("AddLine_D"   , "AddLine_D"   , "Add Line"    , 0, null);
        scrnAppli.setButtonProperties("DeleteLine_D", "DeleteLine_D", "Delete Line" , 0, null);
        scrnAppli.setButtonProperties("WarehouseSearch_D", "WarehouseSearch_D", "W" , 0, null);
        scrnAppli.setButtonProperties("ShipToSearch_D", "ShipToSearch_D"      , "S" , 0, null);
        scrnAppli.setButtonProperties("ShipToName_D", "ShipToName_D"         , ">>" , 0, null);
        scrnAppli.setButtonProperties("ShipToLocSearch_D", "ShipToLocSearch_D"         , "L" , 0, null);
        scrnAppli.setButtonProperties("ShipToLocName_D", "ShipToLocName_D"         , ">>" , 0, null);
        scrnAppli.setButtonProperties("ShipToContactSearch_D", "ShipToContactSearch_D"         , "C" , 0, null);
        for(int i = 0; i < scrnMsg.D.length(); i++) {
            // Set After Digit
            scrnMsg.D.no(i).shipDealNetAmt_D1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.D.no(i).shipDealSlsAmt_D1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.D.no(i).shipDealFrtAmt_D1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.D.no(i).frtDealTaxAmt_D1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.D.no(i).shipDealDiscAmt_D1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.D.no(i).shipDealHdlgChrgAmt_D1.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.D.no(i).totBolDealTaxAmt_D1.setAppFracDigit(aftDeclPntDigitNum);
        }

        //----------------------------------------
        // More Info.
        //----------------------------------------

        // START 2016/09/06 S.Fujita [QC#14111,ADD]
        // Comments
        // START 2019/05/10 S.Takami [QC#50148,MOD]
//        scrnMsg.invFirstCmntTxt_E1.setInputProtected(false);
        scrnMsg.xxInvMemoTxt_E1.setInputProtected(false);
        // END 2019/05/10 S.Takami [QC#50148,MOD]
        // END   2016/09/06 S.Fujita [QC#14111,ADD]
        // START 2017/12/25 E.Kameishi [QC#20312,ADD]
        scrnMsg.invPrintStsCd_E1.setInputProtected(false);
        // END 2017/12/25 E.Kameishi [QC#20312,ADD]

        // Set After Digit
        scrnMsg.dealCltDsptAmt_E1.setAppFracDigit(aftDeclPntDigitNum);
        scrnMsg.prePmtAmt_E1.setAppFracDigit(aftDeclPntDigitNum);

        // TAB Protect
        scrnMsg.xxTabProt_A.setInputProtected(false);
        scrnMsg.xxTabProt_B.setInputProtected(false);
        scrnMsg.xxTabProt_C.setInputProtected(false);
        scrnMsg.xxTabProt_D.setInputProtected(false);
        scrnMsg.xxTabProt_E.setInputProtected(false);

        // Function Button
        NFCL3000CommonLogic.setFuncButton(scrnAppli, scrnMsg);
    }
    // END   2016/06/20 S.Fujita [QC#9454,MOD]

    /**
     * addCheckItem_AddLine
     * @param scrnMsg
     */
    public static void addCheckItem_AddLine(NFCL3000BMsg scrnMsg) {

        // Header
        scrnMsg.addCheckItem(scrnMsg.invTpCd_H1);
        scrnMsg.addCheckItem(scrnMsg.dsInvTpCd_H1);
        scrnMsg.addCheckItem(scrnMsg.invDt_H1);
        scrnMsg.addCheckItem(scrnMsg.pmtTermCashDiscCd_H1);
        // START 2016/10/03 S.Fujita [QC#14120,DEL]
//        scrnMsg.addCheckItem(scrnMsg.dfrdInvRuleCd_H1);
        // END   2016/10/03 S.Fujita [QC#14120,DEL]
        scrnMsg.addCheckItem(scrnMsg.srcSysDocNum_H1);
        scrnMsg.addCheckItem(scrnMsg.custIssPoNum_H1);
        scrnMsg.addCheckItem(scrnMsg.slsRepTocNm_H1);
        // START 2016/07/14 S.Fujita [QC#11157,MOD]
//        scrnMsg.addCheckItem(scrnMsg.slsRepTocCd_H1);
        scrnMsg.addCheckItem(scrnMsg.psnNum_H1);
        // END   2016/07/14 S.Fujita [QC#11157,MOD]
        scrnMsg.addCheckItem(scrnMsg.shipToCustAcctCd_H2);
        scrnMsg.addCheckItem(scrnMsg.locNum_H2);
        scrnMsg.addCheckItem(scrnMsg.xxPsnNm_H2);

        scrnMsg.addCheckItem(scrnMsg.billToCustAcctCd_H3);
        scrnMsg.addCheckItem(scrnMsg.locNum_H3);
        // START 2016/07/28 S.Fujita [QC#10148,DEL]
//        scrnMsg.addCheckItem(scrnMsg.billToCustCd_H3);
//        scrnMsg.addCheckItem(scrnMsg.shipToCustCd_H2);
        // END   2016/07/28 S.Fujita [QC#10148,DEL]
        scrnMsg.addCheckItem(scrnMsg.xxPsnNm_H3);
        scrnMsg.addCheckItem(scrnMsg.shipFromInvtyLocCd_H4);
        scrnMsg.addCheckItem(scrnMsg.shipDt_H4);
//Def#6469
//        if(!ZYPCommonFunc.hasValue(scrnMsg.shipToCustAcctCd_H2.getValue())) {
//            scrnMsg.shipToCustAcctCd_H2.setErrorInfo(1, "ZZM9000E", new String[]{"Ship to Account Number"});
//        }
//        if(!ZYPCommonFunc.hasValue(scrnMsg.shipToCustCd_H2.getValue())) {
//            scrnMsg.shipToCustCd_H2.setErrorInfo(1, "ZZM9000E", new String[]{"Ship to Customer Code"});
//        }
//        if(!ZYPCommonFunc.hasValue(scrnMsg.locNum_H2.getValue())) {
//            scrnMsg.locNum_H2.setErrorInfo(1, "ZZM9000E", new String[]{"Ship to Location"});
//        }
        if(!ZYPCommonFunc.hasValue(scrnMsg.billToCustAcctCd_H3.getValue())) {
            scrnMsg.billToCustAcctCd_H3.setErrorInfo(1, "ZZM9000E", new String[]{"Bill to Account Number"});
        }
        // START 2016/07/28 S.Fujita [QC#10148,DEL]
//        if(!ZYPCommonFunc.hasValue(scrnMsg.billToCustCd_H3.getValue())) {
//            scrnMsg.billToCustCd_H3.setErrorInfo(1, "ZZM9000E", new String[]{"Bill to Customer Code"});
//        }
        // END   2016/07/28 S.Fujita [QC#10148,DEL]
        if(!ZYPCommonFunc.hasValue(scrnMsg.locNum_H3.getValue())) {
            scrnMsg.locNum_H3.setErrorInfo(1, "ZZM9000E", new String[]{"Bill to Location"});
        }

        scrnMsg.A.setCheckParam(new String[] {
                "invBolLineNum_A1"
               ,"invLineNum_A1"
        }, 1);
        scrnMsg.addCheckItem(scrnMsg.A);
    }

    /**
     * addCheckItem_Header
     * @param scrnMsg
     */
    public static void addCheckItem_Header(NFCL3000BMsg scrnMsg) {

        //Mandatory
        scrnMsg.invTpCd_H1.setIndispensable(true);
        scrnMsg.dsInvTpCd_H1.setIndispensable(true);
        scrnMsg.invDt_H1.setIndispensable(true);
        scrnMsg.pmtTermCashDiscCd_H1.setIndispensable(true);
        scrnMsg.billToCustAcctCd_H3.setIndispensable(true);
        scrnMsg.locNum_H3.setIndispensable(true);
        // START 2016/07/14 S.Fujita [QC#11157,MOD]
//        scrnMsg.slsRepTocCd_H1.setIndispensable(true);
        scrnMsg.psnNum_H1.setIndispensable(true);
        // END   2016/07/14 S.Fujita [QC#11157,MOD]
        // START 2016/07/05 S.Fujita [QC#9992,DEL]
//        scrnMsg.dfrdInvRuleCd_H1.setIndispensable(true);
//        scrnMsg.srcSysDocNum_H1.setIndispensable(true);
        // END   2016/07/05 S.Fujita [QC#9992,DEL]

        if(!ZYPCommonFunc.hasValue(scrnMsg.billToCustAcctCd_H3.getValue())) {
            scrnMsg.billToCustAcctCd_H3.setErrorInfo(1, "ZZM9000E", new String[]{"Bill To Cust Account Number"});
        }
        // START 2016/07/28 S.Fujita [QC#10148,DEL]
//        if(!ZYPCommonFunc.hasValue(scrnMsg.billToCustCd_H3.getValue())) {
//            scrnMsg.billToCustCd_H3.setErrorInfo(1, "ZZM9000E", new String[]{"Bill To Cust Code"});
//        }
        // END   2016/07/28 S.Fujita [QC#10148,DEL]
        if(!ZYPCommonFunc.hasValue(scrnMsg.locNum_H3.getValue())) {
            scrnMsg.locNum_H3.setErrorInfo(1, "ZZM9000E", new String[]{"Bill To Location"});
        }
        
        // START 2016/07/05 S.Fujita [QC#9992,ADD]
        // Check Header
        if(!NFCL3000CommonLogic.check_InvDt(scrnMsg)) {
            scrnMsg.invDt_H1.setErrorInfo(1, "NFCM0847E", null);
        }
        // END   2016/07/05 S.Fujita [QC#9992,ADD]

        // Header
        scrnMsg.addCheckItem(scrnMsg.invTpCd_H1);
        scrnMsg.addCheckItem(scrnMsg.dsInvTpCd_H1);
        scrnMsg.addCheckItem(scrnMsg.invDt_H1);
        scrnMsg.addCheckItem(scrnMsg.pmtTermCashDiscCd_H1);
        // START 2016/10/03 S.Fujita [QC#14120,DEL]
//        scrnMsg.addCheckItem(scrnMsg.dfrdInvRuleCd_H1);
        // END   2016/10/03 S.Fujita [QC#14120,DEL]
        scrnMsg.addCheckItem(scrnMsg.srcSysDocNum_H1);
        scrnMsg.addCheckItem(scrnMsg.custIssPoNum_H1);
        scrnMsg.addCheckItem(scrnMsg.slsRepTocNm_H1);
        // START 2016/07/14 S.Fujita [QC#11157,MOD]
//        scrnMsg.addCheckItem(scrnMsg.slsRepTocCd_H1);
        scrnMsg.addCheckItem(scrnMsg.psnNum_H1);
        // END   2016/07/14 S.Fujita [QC#11157,MOD]
        // START 2016/07/28 S.Fujita [QC#10148,DEL]
//        scrnMsg.addCheckItem(scrnMsg.billToCustCd_H3);
        // END   2016/07/28 S.Fujita [QC#10148,DEL]
        scrnMsg.addCheckItem(scrnMsg.shipToCustAcctCd_H2);
        scrnMsg.addCheckItem(scrnMsg.locNum_H2);
        scrnMsg.addCheckItem(scrnMsg.xxPsnNm_H2);
        scrnMsg.addCheckItem(scrnMsg.billToCustAcctCd_H3);
        scrnMsg.addCheckItem(scrnMsg.locNum_H3);
        scrnMsg.addCheckItem(scrnMsg.xxPsnNm_H3);
        scrnMsg.addCheckItem(scrnMsg.shipDt_H4);

    }

    /**
     * addCheckItem_TAB
     * @param scrnMsg
     */
    public static void addCheckItem_TAB(NFCL3000BMsg scrnMsg, boolean checkInput) {
        if(scrnMsg.xxDplyTab.getValue().equals(TAB_Line)) {
            NFCL3000CommonLogic.addCheckItem_Line(scrnMsg, checkInput);
        } else if(scrnMsg.xxDplyTab.getValue().equals(TAB_BOL)) {
            NFCL3000CommonLogic.addCheckItem_BOL(scrnMsg);
         // START 2016/07/20 S.Fujita [QC#10148,ADD]
        } else if(scrnMsg.xxDplyTab.getValue().equals(TAB_SalesCredit)) {
            NFCL3000CommonLogic.addCheckItem_SlsCr(scrnMsg, checkInput);
        } else if(scrnMsg.xxDplyTab.getValue().equals(TAB_MoreInfo)) {
            NFCL3000CommonLogic.addCheckItem_MoreInfo(scrnMsg);
         // END   2016/07/20 S.Fujita [QC#10148,ADD]
        }
    }

    // START 2016/06/03 S.Fujita [QC#9452,ADD]
    /**
     * addCheckItem_TAB_Submit
     * @param scrnMsg
     */
    public static void addCheckItem_TAB_Submit(NFCL3000BMsg scrnMsg, boolean checkInput) {
        NFCL3000CommonLogic.addCheckItem_Line_Submit(scrnMsg, checkInput);
        NFCL3000CommonLogic.addCheckItem_SlsCr_Submit(scrnMsg, checkInput);
        NFCL3000CommonLogic.addCheckItem_BOL_Submit(scrnMsg);
        NFCL3000CommonLogic.addCheckItem_MoreInfo_Submit(scrnMsg);
        NFCL3000CommonLogic.addCheckItem_AcctDist_Submit(scrnMsg, checkInput);
    }

    /**
     * addCheckItem_TAB_Calc
     * @param scrnMsg
     */
    public static void addCheckItem_TAB_Calc(NFCL3000BMsg scrnMsg, boolean checkInput) {
        if(scrnMsg.xxDplyTab.getValue().equals(TAB_Line)) {
            NFCL3000CommonLogic.addCheckItem_Line(scrnMsg, checkInput);
        } else if(scrnMsg.xxDplyTab.getValue().equals(TAB_BOL)) {
            NFCL3000CommonLogic.addCheckItem_BOL(scrnMsg);
        } else if(scrnMsg.xxDplyTab.getValue().equals(TAB_SalesCredit)) {
          NFCL3000CommonLogic.addCheckItem_SlsCr(scrnMsg, checkInput);
        } else if(scrnMsg.xxDplyTab.getValue().equals(TAB_Accounting)) {
            NFCL3000CommonLogic.addCheckItem_AcctDist(scrnMsg, checkInput);
        } else if(scrnMsg.xxDplyTab.getValue().equals(TAB_MoreInfo)) {
            NFCL3000CommonLogic.addCheckItem_MoreInfo(scrnMsg);
        }
    }
    // END   2016/06/03 S.Fujita [QC#9452,ADD]

    /**
     * addCheckItem_Line
     * @param scrnMsg
     * @param thruFlg
     */
    public static void addCheckItem_Line(NFCL3000BMsg scrnMsg, boolean thruFlg) {
        // START 2019/04/25 S.Takami [QC#50078,ADD]
        setTextQtyToBigDecimalQty(scrnMsg);
        // END 2019/04/25 S.Takami [QC#50078,ADD]
        // Line TAB
        if(thruFlg) {
            scrnMsg.A.setCheckParam(new String[] {
                     // START 2016/09/12 S.Fujita [QC#14112,ADD]
                     "invBolLineNum_A1"
                     // END   2016/09/12 S.Fujita [QC#14112,ADD]
                    ,"invLineNum_A1"
                    // START 2018/05/22 Y.Matsui [QC#21841,ADD]
                    ,"invLineCatgCd_A1"
                    // END   2018/05/22 Y.Matsui [QC#21841,ADD]
                    ,"mdseCd_A1"
                    ,"pkgUomCd_A1"
                    // START 2018/09/28 T.Ogura [QC#28526,MOD]
//                    ,"shipQty_A1"
                    ,"ordCustUomQty_A1"
                    // END   2018/09/28 T.Ogura [QC#28526,MOD]
                    ,"dealNetUnitPrcAmt_A1"
                    ,"ordDt_A1"
                    ,"unitCostAmt_A1"
                    ,"invLineDealTaxAmt_A1"
                    ,"taxPct_A1"
                    // START 2016/08/01 S.Fujita [QC#10148,ADD]
                    ,"cpoOrdNum_A1"
                    ,"cpoDtlLineNum_A1"
                    ,"dsContrNum_A1"
                    ,"dsContrSqNum_A1"
                    ,"mdlNm_A1"
                    ,"svcInvChrgTpCd_A1"
                    ,"bllgPerFromDt_A1"
                    ,"bllgPerThruDt_A1"
                    ,"bllgCopyQty_A1"
                    ,"ordQty_A1"
                    ,"crDrRsnCd_A1"
                    ,"dsContrDtlPk_A1"
                    ,"manInvCratCmntTxt_A1"
                    // END   2016/08/01 S.Fujita [QC#10148,ADD]

                    // START 2016/10/14 S.Fujita [QC#10281,ADD]
                    ,"firstBllgAttrbValTxt_A1"
                    ,"scdBllgAttrbValTxt_A1"
                    ,"thirdBllgAttrbValTxt_A1"
                    ,"frthBllgAttrbValTxt_A1"
                    ,"fifthBllgAttrbValTxt_A1"
                    ,"sixthBllgAttrbValTxt_A1"
                    // END   2016/10/14 S.Fujita [QC#10281,ADD]
                    // START 2019/04/25 S.Takami [QC#50078,ADD]
                    ,"shipQty_A1"
                    ,"invDplyQty_A1"
                    ,"adjQtyDplyTxt_A1"
                    // END 2019/04/25 S.Takami [QC#50078,ADD]
           }, 1);
        } else {
        // Add check Sales Amount and Total Amount
            scrnMsg.A.setCheckParam(new String[] {
                    // START 2016/09/12 S.Fujita [QC#14112,ADD]
                    "invBolLineNum_A1"
                    // END   2016/09/12 S.Fujita [QC#14112,ADD]
                   ,"invLineNum_A1"
                   // START 2018/05/22 Y.Matsui [QC#21841,ADD]
                   ,"invLineCatgCd_A1"
                   // END   2018/05/22 Y.Matsui [QC#21841,ADD]
                   ,"mdseCd_A1"
                   ,"pkgUomCd_A1"
                   // START 2018/09/28 T.Ogura [QC#28526,MOD]
//                   ,"shipQty_A1"
                   ,"ordCustUomQty_A1"
                   // END   2018/09/28 T.Ogura [QC#28526,MOD]
                   ,"dealNetUnitPrcAmt_A1"
                   ,"ordDt_A1"
                   ,"unitCostAmt_A1"
                   ,"invLineDealNetAmt_A1"
                   ,"invLineDealTaxAmt_A1"
                   ,"taxPct_A1"
                   ,"xxTotAmt_A1"
                   // START 2016/08/01 S.Fujita [QC#10148,ADD]
                   ,"cpoOrdNum_A1"
                   ,"cpoDtlLineNum_A1"
                   ,"dsContrNum_A1"
                   ,"dsContrSqNum_A1"
                   ,"mdlNm_A1"
                   ,"svcInvChrgTpCd_A1"
                   ,"bllgPerFromDt_A1"
                   ,"bllgPerThruDt_A1"
                   ,"bllgCopyQty_A1"
                   ,"ordQty_A1"
                   ,"crDrRsnCd_A1"
                   ,"dsContrDtlPk_A1"
                   ,"manInvCratCmntTxt_A1"
                   // END   2016/08/01 S.Fujita [QC#10148,ADD]
                   
                   // START 2016/10/14 S.Fujita [QC#10281,ADD]
                   ,"firstBllgAttrbValTxt_A1"
                   ,"scdBllgAttrbValTxt_A1"
                   ,"thirdBllgAttrbValTxt_A1"
                   ,"frthBllgAttrbValTxt_A1"
                   ,"fifthBllgAttrbValTxt_A1"
                   ,"sixthBllgAttrbValTxt_A1"
                   // END   2016/10/14 S.Fujita [QC#10281,ADD]
                   // START 2019/04/25 S.Takami [QC#50078,ADD]
                   ,"shipQty_A1"
                   ,"invDplyQty_A1"
                   ,"adjQtyDplyTxt_A1"
                   // END 2019/04/25 S.Takami [QC#50078,ADD]
           }, 1);

           // START 2016/10/03 S.Fujita [QC#13206,ADD]
           scrnMsg.addCheckItem(scrnMsg.xxTotAmt_T1);
           scrnMsg.addCheckItem(scrnMsg.xxTotAmt_T3);
           scrnMsg.addCheckItem(scrnMsg.xxTotAmt_T4);
           // END   2016/10/03 S.Fujita [QC#13206,ADD]

        }
        scrnMsg.addCheckItem(scrnMsg.A);

        // START 2019/04/25 S.Takami [QC#50078,ADD]
        setErrorInfoOfQty(scrnMsg);
        // END 2019/04/25 S.Takami [QC#50078,ADD]

        NFCL3000CommonLogic.check_Line_Amount(scrnMsg);
    }

    // START 2016/06/03 S.Fujita [QC#9452,ADD]
    /**
     * addCheckItem_Line_Submit
     * @param scrnMsg
     * @param thruFlg
     */
    public static void addCheckItem_Line_Submit(NFCL3000BMsg scrnMsg, boolean thruFlg) {
        // START 2019/04/25 S.Takami [QC#50078,ADD]
        setTextQtyToBigDecimalQty(scrnMsg);
        // END 2019/04/25 S.Takami [QC#50078,ADD]
        // Line TAB
        if(thruFlg) {
            scrnMsg.A.setCheckParam(new String[] {
                     // START 2016/09/12 S.Fujita [QC#14112,ADD]
                     "invBolLineNum_A1"
                     // END   2016/09/12 S.Fujita [QC#14112,ADD]
                    ,"invLineNum_A1"
                    // START 2018/05/22 Y.Matsui [QC#21841,ADD]
                    ,"invLineCatgCd_A1"
                    // END   2018/05/22 Y.Matsui [QC#21841,ADD]
                    ,"mdseCd_A1"
                    ,"pkgUomCd_A1"
                    // START 2018/09/28 T.Ogura [QC#28526,MOD]
//                    ,"shipQty_A1"
                    ,"ordCustUomQty_A1"
                    // END   2018/09/28 T.Ogura [QC#28526,MOD]
                    ,"dealNetUnitPrcAmt_A1"
                    ,"ordDt_A1"
                    ,"unitCostAmt_A1"
                    ,"invLineDealTaxAmt_A1"
                    ,"taxPct_A1"
                    // START 2016/08/01 S.Fujita [QC#10148,ADD]
                    ,"cpoOrdNum_A1"
                    ,"cpoDtlLineNum_A1"
                    ,"dsContrNum_A1"
                    ,"dsContrSqNum_A1"
                    ,"mdlNm_A1"
                    ,"svcInvChrgTpCd_A1"
                    ,"bllgPerFromDt_A1"
                    ,"bllgPerThruDt_A1"
                    ,"bllgCopyQty_A1"
                    ,"ordQty_A1"
                    ,"crDrRsnCd_A1"
                    ,"dsContrDtlPk_A1"
                    ,"manInvCratCmntTxt_A1"
                    // END   2016/08/01 S.Fujita [QC#10148,ADD]

                    // START 2016/10/14 S.Fujita [QC#10281,ADD]
                    ,"firstBllgAttrbValTxt_A1"
                    ,"scdBllgAttrbValTxt_A1"
                    ,"thirdBllgAttrbValTxt_A1"
                    ,"frthBllgAttrbValTxt_A1"
                    ,"fifthBllgAttrbValTxt_A1"
                    ,"sixthBllgAttrbValTxt_A1"
                    // END   2016/10/14 S.Fujita [QC#10281,ADD]
                    // START 2019/04/25 S.Takami [QC#50078,ADD]
                    ,"shipQty_A1"
                    ,"invDplyQty_A1"
                    ,"adjQtyDplyTxt_A1"
                    // END 2019/04/25 S.Takami [QC#50078,ADD]
           }, 1);
        // Add check Sales Amount and Total Amount
        } else {
            scrnMsg.A.setCheckParam(new String[] {
                    // START 2016/09/12 S.Fujita [QC#14112,ADD]
                    "invBolLineNum_A1"
                    // END   2016/09/12 S.Fujita [QC#14112,ADD]
                   ,"invLineNum_A1"
                   // START 2018/05/22 Y.Matsui [QC#21841,ADD]
                   ,"invLineCatgCd_A1"
                   // END   2018/05/22 Y.Matsui [QC#21841,ADD]
                   ,"mdseCd_A1"
                   ,"pkgUomCd_A1"
                   // START 2018/09/28 T.Ogura [QC#28526,MOD]
//                   ,"shipQty_A1"
                   ,"ordCustUomQty_A1"
                   // END   2018/09/28 T.Ogura [QC#28526,MOD]
                   ,"dealNetUnitPrcAmt_A1"
                   ,"ordDt_A1"
                   ,"unitCostAmt_A1"
                   ,"invLineDealNetAmt_A1"
                   ,"invLineDealTaxAmt_A1"
                   ,"taxPct_A1"
                   ,"xxTotAmt_A1"
                   // START 2016/08/01 S.Fujita [QC#10148,ADD]
                   ,"cpoOrdNum_A1"
                   ,"cpoDtlLineNum_A1"
                   ,"dsContrNum_A1"
                   ,"dsContrSqNum_A1"
                   ,"mdlNm_A1"
                   ,"svcInvChrgTpCd_A1"
                   ,"bllgPerFromDt_A1"
                   ,"bllgPerThruDt_A1"
                   ,"bllgCopyQty_A1"
                   ,"ordQty_A1"
                   ,"crDrRsnCd_A1"
                   ,"dsContrDtlPk_A1"
                   ,"manInvCratCmntTxt_A1"
                   // END   2016/08/01 S.Fujita [QC#10148,ADD]

                   // START 2016/10/14 S.Fujita [QC#10281,ADD]
                   ,"firstBllgAttrbValTxt_A1"
                   ,"scdBllgAttrbValTxt_A1"
                   ,"thirdBllgAttrbValTxt_A1"
                   ,"frthBllgAttrbValTxt_A1"
                   ,"fifthBllgAttrbValTxt_A1"
                   ,"sixthBllgAttrbValTxt_A1"
                   // END   2016/10/14 S.Fujita [QC#10281,ADD]
                   // START 2019/04/17 S.Takami [QC#31204,ADD]
                   ,"xxChkBox_A1"
                   ,"serNum_A1"
                   // END 2019/04/17 S.Takami [QC#31204,ADD]
                   // START 2019/04/25 S.Takami [QC#50078,ADD]
                   ,"shipQty_A1"
                   ,"invDplyQty_A1"
                   ,"adjQtyDplyTxt_A1"
                   // END 2019/04/25 S.Takami [QC#50078,ADD]
           }, 1);

           // START 2016/10/03 S.Fujita [QC#13206,ADD]
           scrnMsg.addCheckItem(scrnMsg.xxTotAmt_T1);
           scrnMsg.addCheckItem(scrnMsg.xxTotAmt_T3);
           scrnMsg.addCheckItem(scrnMsg.xxTotAmt_T4);
           // END   2016/10/03 S.Fujita [QC#13206,ADD]

        }
        scrnMsg.addCheckItem(scrnMsg.A);

        // START 2019/04/25 S.Takami [QC#50078,ADD]
        setErrorInfoOfQty(scrnMsg);
        // END 2019/04/25 S.Takami [QC#50078,ADD]

        NFCL3000CommonLogic.check_Line_Amount(scrnMsg);
        scrnMsg.xxDplyTab.setValue(TAB_Line);
        scrnMsg.putErrorScreen();
    }
    // END   2016/06/03 S.Fujita [QC#9452,ADD]

    /**
     * addCheckItem_SlsCr
     * @param scrnMsg
     * @param thruFlg
     */
    public static void addCheckItem_SlsCr(NFCL3000BMsg scrnMsg, boolean thruFlg) {

        if(thruFlg) {
            scrnMsg.B.setCheckParam(new String[] {
                    "invBolLineNum_B1"
                   ,"invLineNum_B1"
                   // START 2016/09/05 S.Fujita [QC#13648,DEL]
//                   ,"invLineSplPct_B2"
                   // END   2016/09/05 S.Fujita [QC#13648,DEL]
                   // START 2016/07/14 S.Fujita [QC#11157,MOD]
//                   ,"slsRepTocCd_B1"
                   ,"psnNum_B1"
                   // END   2016/07/14 S.Fujita [QC#11157,MOD]
                   ,"slsRepCrPct_B2"
                   // START 2016/10/12 S.Fujita [QC#13652,DEL]
//                   ,"dealSlsCrAmt_B1"
                   // END   2016/10/12 S.Fujita [QC#13652,DEL]
                   ,"dfrdAcctgRuleDurnAot_B1"
                   ,"durnStartDt_B1"
                   // START 2016/08/01 S.Fujita [QC#10148,ADD]
                   ,"tocNm_B1"
                   ,"manInvCratCmntTxt_B1"
                   ,"invLineSplTpCd_B1"
                   ,"dfrdAcctgRuleCd_B1"
                   // END   2016/08/01 S.Fujita [QC#10148,ADD]
           }, 1);
        } else {
            // Add check Sales Credit Amount
            scrnMsg.B.setCheckParam(new String[] {
                    "invBolLineNum_B1"
                   ,"invLineNum_B1"
                   ,"psnNum_B1"
                   ,"slsRepCrPct_B2"
                   ,"dealSlsCrAmt_B1"
                   ,"dfrdAcctgRuleDurnAot_B1"
                   ,"durnStartDt_B1"
                   ,"tocNm_B1"
                   ,"manInvCratCmntTxt_B1"
                   ,"invLineSplTpCd_B1"
                   ,"dfrdAcctgRuleCd_B1"
           }, 1);
        }
       scrnMsg.addCheckItem(scrnMsg.B);
    }
 
    /**
     * addCheckItem_SlsCr_Submit
     * @param scrnMsg
     * @param thruFlg
     */
    public static void addCheckItem_SlsCr_Submit(NFCL3000BMsg scrnMsg, boolean thruFlg) {

        if(thruFlg) {
            scrnMsg.B.setCheckParam(new String[] {
                    "invBolLineNum_B1"
                   ,"invLineNum_B1"
                   // START 2016/09/05 S.Fujita [QC#13648,DEL]
//                   ,"invLineSplPct_B2"
                   // END   2016/09/05 S.Fujita [QC#13648,DEL]
                   // START 2016/07/14 S.Fujita [QC#11157,MOD]
//                 ,"slsRepTocCd_B1"
                   ,"psnNum_B1"
                   // END   2016/07/14 S.Fujita [QC#11157,MOD]
                   ,"slsRepCrPct_B2"
                   // START 2016/10/12 S.Fujita [QC#13652,DEL] 
//                   ,"dealSlsCrAmt_B1"
                   // END   2016/10/12 S.Fujita [QC#13652,DEL]
                   ,"dfrdAcctgRuleDurnAot_B1"
                   ,"durnStartDt_B1"
                   // START 2016/08/01 S.Fujita [QC#10148,ADD]
                   ,"tocNm_B1"
                   ,"manInvCratCmntTxt_B1"
                   ,"invLineSplTpCd_B1"
                   ,"dfrdAcctgRuleCd_B1"
                   // END   2016/08/01 S.Fujita [QC#10148,ADD]
           }, 1);
        } else {
            // Add check Sales Credit Amount
            scrnMsg.B.setCheckParam(new String[] {
                    "invBolLineNum_B1"
                   ,"invLineNum_B1"
                   ,"psnNum_B1"
                   ,"slsRepCrPct_B2"
                   ,"dealSlsCrAmt_B1"
                   ,"dfrdAcctgRuleDurnAot_B1"
                   ,"durnStartDt_B1"
                   ,"tocNm_B1"
                   ,"manInvCratCmntTxt_B1"
                   ,"invLineSplTpCd_B1"
                   ,"dfrdAcctgRuleCd_B1"
           }, 1);
        }
       scrnMsg.addCheckItem(scrnMsg.B);
       scrnMsg.xxDplyTab.setValue(TAB_SalesCredit);
       scrnMsg.putErrorScreen();
    }

    /**
     * addCheckItem_BOL
     * @param scrnMsg
     */
    public static void addCheckItem_BOL(NFCL3000BMsg scrnMsg) {

        scrnMsg.D.setCheckParam(new String[] {
                 "invBolLineNum_D1"
                ,"shipDealFrtAmt_D1"
                ,"frtDealTaxAmt_D1"
                ,"frtTaxPct_D1"
                ,"shipDt_D1"
                // START 2016/08/01 S.Fujita [QC#10148,ADD]
                ,"shipFromInvtyLocCd_D1"
                ,"shipToCustAcctCd_D1"
                ,"shipToCustAcctNm_D1"
                ,"locNum_D1"
                ,"xxPsnNm_D1"
                ,"soNum_D1"
                ,"bolNum_D1"
                // END   2016/08/01 S.Fujita [QC#10148,ADD]
        }, 1);
        scrnMsg.addCheckItem(scrnMsg.D);
    }

    /**
     * addCheckItem_BOL_Submit
     * @param scrnMsg
     */
    public static void addCheckItem_BOL_Submit(NFCL3000BMsg scrnMsg) {

        scrnMsg.D.setCheckParam(new String[] {
                 "invBolLineNum_D1"
                ,"shipDealFrtAmt_D1"
                ,"frtDealTaxAmt_D1"
                ,"frtTaxPct_D1"
                ,"shipDt_D1"
                // START 2016/08/01 S.Fujita [QC#10148,ADD]
                ,"shipFromInvtyLocCd_D1"
                ,"shipToCustAcctCd_D1"
                ,"shipToCustAcctNm_D1"
                ,"locNum_D1"
                ,"xxPsnNm_D1"
                ,"soNum_D1"
                ,"bolNum_D1"
                // END   2016/08/01 S.Fujita [QC#10148,ADD]
        }, 1);
        scrnMsg.addCheckItem(scrnMsg.D);
        scrnMsg.xxDplyTab.setValue(TAB_BOL);
        scrnMsg.putErrorScreen();
    }

    /**
     * addCheckItem_AcctDist
     * @param scrnMsg
     */
    public static void addCheckItem_AcctDist(NFCL3000BMsg scrnMsg, boolean thruFlg) {

        if(thruFlg && scrnMsg.xxRadioBtn_C.getValue().equals(ACCT_DIST_SMRY)) {
            return;
        }
        if(!thruFlg) {
            scrnMsg.addCheckItem(scrnMsg.xxTotAmt_T1);
            scrnMsg.addCheckItem(scrnMsg.xxTotAmt_T2);
            scrnMsg.addCheckItem(scrnMsg.xxTotAmt_T3);
            scrnMsg.addCheckItem(scrnMsg.xxTotAmt_T4);
            scrnMsg.addCheckItem(scrnMsg.xxTotAmt_C1);
            scrnMsg.addCheckItem(scrnMsg.xxTotAmt_C2);
        }

        scrnMsg.C.setCheckParam(new String[] {
                "xxLineNum_C1"
               ,"invBolLineNum_C1"
               ,"invLineNum_C1"
               ,"ajeInvAcctClsCd_C1"
               ,"xxScrItem61Txt_C1"
               ,"jrnlDealAmt_C1"
               ,"jrnlDealAmt_C2"
       }, 1);
       scrnMsg.addCheckItem(scrnMsg.C);
    }

    /**
     * addCheckItem_AcctDist
     * @param scrnMsg
     */
    public static void addCheckItem_AcctDist_Submit(NFCL3000BMsg scrnMsg, boolean thruFlg) {

        if(thruFlg && scrnMsg.xxRadioBtn_C.getValue().equals(ACCT_DIST_SMRY)) {
            return;
        }
        if(!thruFlg) {
            scrnMsg.addCheckItem(scrnMsg.xxTotAmt_T1);
            scrnMsg.addCheckItem(scrnMsg.xxTotAmt_T2);
            scrnMsg.addCheckItem(scrnMsg.xxTotAmt_T3);
            scrnMsg.addCheckItem(scrnMsg.xxTotAmt_T4);
            scrnMsg.addCheckItem(scrnMsg.xxTotAmt_C1);
            scrnMsg.addCheckItem(scrnMsg.xxTotAmt_C2);
        }

        scrnMsg.C.setCheckParam(new String[] {
                "xxLineNum_C1"
               ,"invBolLineNum_C1"
               ,"invLineNum_C1"
               ,"ajeInvAcctClsCd_C1"
               ,"xxScrItem61Txt_C1"
               ,"jrnlDealAmt_C1"
               ,"jrnlDealAmt_C2"
       }, 1);
       scrnMsg.addCheckItem(scrnMsg.C);
       scrnMsg.xxDplyTab.setValue(TAB_Accounting);
    }

    /**
     * checkItem_AcctDistOnChange
     * @param scrnMsg
     * @return
     */
    public static boolean checkItem_AcctDistOnChange(NFCL3000BMsg scrnMsg) {

        boolean isSuccess = true;

        for(int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            if(scrnMsg.C.no(i).drCrTpCd_C1.getValue().equals(DR_CR_TP_DEBIT)) {
                if(!ZYPCommonFunc.hasValue(scrnMsg.C.no(i).xxLineNum_C1.getValue())) {
                    scrnMsg.C.no(i).xxLineNum_C1.setErrorInfo(1, "ZZM9000E", new String[]{"Sales Credit Number"});
                    isSuccess = false;
                }
                if(!ZYPCommonFunc.hasValue(scrnMsg.C.no(i).ajeInvAcctClsCd_C1.getValue())) {
                    scrnMsg.C.no(i).ajeInvAcctClsCd_C1.setErrorInfo(1, "ZZM9000E", new String[]{"Accounting Class"});
                    isSuccess = false;
                }
                if(!ZYPCommonFunc.hasValue(scrnMsg.C.no(i).xxScrItem61Txt_C1.getValue())) {
                    scrnMsg.C.no(i).xxScrItem61Txt_C1.setErrorInfo(1, "ZZM9000E", new String[]{"Accounting Information"});
                    isSuccess = false;
                }
                if(!ZYPCommonFunc.hasValue(scrnMsg.C.no(i).jrnlDealAmt_C1.getValue())) {
                    scrnMsg.C.no(i).jrnlDealAmt_C1.setErrorInfo(1, "ZZM9000E", new String[]{"Debit Amount"});
                    isSuccess = false;
                }
                // START 2016/06/16 S.Fujita [QC#10254,ADD]
                if(scrnMsg.C.no(i).xxScrItem61Txt_C1.isError()) {
                    isSuccess = false;
                }
                // END   2016/06/16 S.Fujita [QC#10254,ADD]
            } else {
                if(!ZYPCommonFunc.hasValue(scrnMsg.C.no(i).xxLineNum_C1.getValue())) {
                    scrnMsg.C.no(i).xxLineNum_C1.setErrorInfo(1, "ZZM9000E", new String[]{"Sales Credit Number"});
                    isSuccess = false;
                }
                if(!ZYPCommonFunc.hasValue(scrnMsg.C.no(i).ajeInvAcctClsCd_C1.getValue())) {
                    scrnMsg.C.no(i).ajeInvAcctClsCd_C1.setErrorInfo(1, "ZZM9000E", new String[]{"Accounting Class"});
                    isSuccess = false;
                }
                if(!ZYPCommonFunc.hasValue(scrnMsg.C.no(i).xxScrItem61Txt_C1.getValue())) {
                    scrnMsg.C.no(i).xxScrItem61Txt_C1.setErrorInfo(1, "ZZM9000E", new String[]{"Accounting Information"});
                    isSuccess = false;
                }
                if(!ZYPCommonFunc.hasValue(scrnMsg.C.no(i).jrnlDealAmt_C2.getValue())) {
                    scrnMsg.C.no(i).jrnlDealAmt_C2.setErrorInfo(1, "ZZM9000E", new String[]{"Credit Amount"});
                    isSuccess = false;
                }
                // START 2016/06/16 S.Fujita [QC#10254,ADD]
                if(scrnMsg.C.no(i).xxScrItem61Txt_C1.isError()) {
                    isSuccess = false;
                }
                // END   2016/06/16 S.Fujita [QC#10254,ADD]
            }
        }
        return isSuccess;
    }

    /**
     * addCheckItem_AcctDistOnChange
     * @param scrnMsg
     */
    public static void addCheckItem_AcctDistOnChange(NFCL3000BMsg scrnMsg) {

        scrnMsg.C.setCheckParam(new String[] {
                "xxLineNum_C1"
               ,"invBolLineNum_C1"
               ,"invLineNum_C1"
               ,"ajeInvAcctClsCd_C1"
               ,"xxScrItem61Txt_C1"
               ,"jrnlDealAmt_C1"
               ,"jrnlDealAmt_C2"
       }, 1);
       scrnMsg.addCheckItem(scrnMsg.C);
    }

    /**
     * addCheckItem_AcctInfo
     * @param scrnMsg
     */
    public static void addCheckItem_AcctInfo(NFCL3000BMsg scrnMsg) {

        if(ZYPCommonFunc.hasValue(scrnMsg.shipToCustAcctCd_H2.getValue()) && ZYPCommonFunc.hasValue(scrnMsg.locNum_H2.getValue())) {
            if(!ZYPCommonFunc.hasValue(scrnMsg.shipToCustCd_H2.getValue())) {
                scrnMsg.shipToCustAcctCd_H2.setErrorInfo(1, "NFCM0029E");
                scrnMsg.locNum_H2.setErrorInfo(1, "NFCM0029E");
                scrnMsg.addCheckItem(scrnMsg.shipToCustAcctCd_H2);
                scrnMsg.addCheckItem(scrnMsg.locNum_H2);
            }
        }
        if(ZYPCommonFunc.hasValue(scrnMsg.billToCustAcctCd_H3.getValue()) && ZYPCommonFunc.hasValue(scrnMsg.locNum_H3.getValue())) {
            if(!ZYPCommonFunc.hasValue(scrnMsg.billToCustCd_H3.getValue())) {
                scrnMsg.billToCustAcctCd_H3.setErrorInfo(1, "NFCM0029E");
                scrnMsg.locNum_H3.setErrorInfo(1, "NFCM0029E");
                scrnMsg.addCheckItem(scrnMsg.billToCustAcctCd_H3);
                scrnMsg.addCheckItem(scrnMsg.locNum_H3);
            }
        }
    }

    /**
     * addCheckItem_A_Line
     * @param scrnMsg
     */
    public static void addCheckItem_A_Line(NFCL3000BMsg scrnMsg) {
        scrnMsg.A.setCheckParam(new String[] {
               "invBolLineNum_A1"
       }, 1);
       scrnMsg.addCheckItem(scrnMsg.A);
    }

    /**
     * addCheckItem_B_Line
     * @param scrnMsg
     */
    public static void addCheckItem_B_Line(NFCL3000BMsg scrnMsg) {
        scrnMsg.B.setCheckParam(new String[] {
               "invLineNum_B1"
       }, 1);
       scrnMsg.addCheckItem(scrnMsg.B);
    }

    /**
     * addCheckItem_C_Line
     * @param scrnMsg
     */
    public static void addCheckItem_C_Line(NFCL3000BMsg scrnMsg) {
        
        scrnMsg.addCheckItem(scrnMsg.xxTotAmt_T4);
        scrnMsg.addCheckItem(scrnMsg.xxTotAmt_C1);
        scrnMsg.addCheckItem(scrnMsg.xxTotAmt_C2);

        scrnMsg.B.setCheckParam(new String[] {
               "xxLineNum_C1"
       }, 1);
       scrnMsg.addCheckItem(scrnMsg.C);
    }

    /**
     * setDebitCredit
     * @param scrnMsg
     */
    public static void setDebitCredit(NFCL3000BMsg scrnMsg) {

        if(ZYPCommonFunc.hasValue(scrnMsg.invNum_H1.getValue())) {
            for(int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                if(scrnMsg.C.no(i).drCrTpCd_C1.getValue().equals(DR_CR_TP_DEBIT)) {
                    scrnMsg.C.no(i).jrnlDealAmt_C1.setIndispensable(true);
                    scrnMsg.C.no(i).jrnlDealAmt_C2.setIndispensable(false);
                    scrnMsg.C.no(i).jrnlDealAmt_C1.setInputProtected(false);
                    scrnMsg.C.no(i).jrnlDealAmt_C2.setInputProtected(true);
                } else {
                    scrnMsg.C.no(i).jrnlDealAmt_C1.setIndispensable(false);
                    scrnMsg.C.no(i).jrnlDealAmt_C2.setIndispensable(true);
                    scrnMsg.C.no(i).jrnlDealAmt_C1.setInputProtected(true);
                    scrnMsg.C.no(i).jrnlDealAmt_C2.setInputProtected(false);
                }
            }
        } else {
            for(int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                scrnMsg.C.no(i).jrnlDealAmt_C1.setInputProtected(true);
                scrnMsg.C.no(i).jrnlDealAmt_C2.setInputProtected(true);
            }
        }
    }

    /**
     * setDebitCredit_Line
     * @param scrnMsg
     */
    public static void setDebitCredit_Line(NFCL3000BMsg scrnMsg) {
        int idx = scrnMsg.xxCellIdx.getValueInt();
        
        if(ZYPCommonFunc.hasValue(scrnMsg.invNum_H1.getValue())) {
            if(scrnMsg.C.no(idx).drCrTpCd_C1.getValue().equals(DR_CR_TP_DEBIT)) {
                scrnMsg.C.no(idx).jrnlDealAmt_C1.setIndispensable(true);
                scrnMsg.C.no(idx).jrnlDealAmt_C2.setIndispensable(false);
                scrnMsg.C.no(idx).jrnlDealAmt_C1.setInputProtected(false);
                scrnMsg.C.no(idx).jrnlDealAmt_C2.setInputProtected(true);

            } else {
                scrnMsg.C.no(idx).jrnlDealAmt_C1.setIndispensable(false);
                scrnMsg.C.no(idx).jrnlDealAmt_C2.setIndispensable(true);
                scrnMsg.C.no(idx).jrnlDealAmt_C1.setInputProtected(true);
                scrnMsg.C.no(idx).jrnlDealAmt_C2.setInputProtected(false);
            }
        } else {
            scrnMsg.C.no(idx).jrnlDealAmt_C1.setInputProtected(true);
            scrnMsg.C.no(idx).jrnlDealAmt_C2.setInputProtected(true);
        }
        
    }

    /**
     * setDownloadButton
     * @param scrnAppli
     * @param scrnMsg
     */
    public static void setDownloadButton(EZDCommonHandler scrnAppli, NFCL3000BMsg scrnMsg) {

        scrnAppli.setButtonProperties("btn6" , "CMN_Download", "Download", 0, null);
        if(TAB_Line.equals(scrnMsg.xxDplyTab.getValue())) {
            if(scrnMsg.A.getValidCount() > 0) {
                scrnAppli.setButtonProperties("btn6" , "CMN_Download", "Download", 1, null);
            }
        } else if(TAB_SalesCredit.equals(scrnMsg.xxDplyTab.getValue())) {
            if(scrnMsg.B.getValidCount() > 0) {
                scrnAppli.setButtonProperties("btn6" , "CMN_Download", "Download", 1, null);
            }
        } else if(TAB_Accounting.equals(scrnMsg.xxDplyTab.getValue())) {
            if(scrnMsg.C.getValidCount() > 0) {
                scrnAppli.setButtonProperties("btn6" , "CMN_Download", "Download", 1, null);
            }
        } else if(TAB_BOL.equals(scrnMsg.xxDplyTab.getValue())) {
            if(scrnMsg.D.getValidCount() > 0) {
                scrnAppli.setButtonProperties("btn6" , "CMN_Download", "Download", 1, null);
            }
        } else if(TAB_MoreInfo.equals(scrnMsg.xxDplyTab.getValue())) {
            scrnAppli.setButtonProperties("btn6" , "CMN_Download", "Download", 0, null);
        }
    }

    /**
     * check_Line_Amount
     * @param scrnMsg
     */
    public static void check_Line_Amount(NFCL3000BMsg scrnMsg) {

        // START 2016/06/20 S.Fujita [QC#9454,MOD]
        //if(ZYPCommonFunc.hasValue(scrnMsg.invNum_H1.getValue())) {
        //    return;
        //}
        if(!scrnMsg.sysSrcCd_H1.getValue().equals(SYS_SRC.S21_ACCOUNTING_AR)) {
            return;
        }
        // END   2016/06/20 S.Fujita [QC#9454,MOD]

        for(int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if(!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).mdseCd_A1.getValue())) {
                continue;
            }
            // START 2016/07/13 S.Fujita [QC#11447,DEL]
//            if(ZYPCommonFunc.hasValue(scrnMsg.A.no(i).shipQty_A1.getValue())) {
//                if(scrnMsg.A.no(i).shipQty_A1.getValue().compareTo(BigDecimal.ZERO)==0) {
//                    scrnMsg.A.no(i).shipQty_A1.setErrorInfo(1, "NFCM0191E");
//                }
//            }
            // END   2016/07/13 S.Fujita [QC#11447,DEL]
            // START 2016/06/20 S.Fujita [QC#9454,MOD]
//            if(ZYPCommonFunc.hasValue(scrnMsg.A.no(i).dealNetUnitPrcAmt_A1.getValue())) {
//                if(scrnMsg.A.no(i).dealNetUnitPrcAmt_A1.getValue().compareTo(BigDecimal.ZERO)==0) {
//                    if(scrnMsg.A.no(i).unitCostAmt_A1.getValue().compareTo(BigDecimal.ZERO)==0) {
//                        scrnMsg.A.no(i).dealNetUnitPrcAmt_A1.setErrorInfo(1, "NFCM0191E");
//                    }
//                }
//            }
            // START 2016/07/13 S.Fujita [QC#11447,DEL]
//            if(ZYPCommonFunc.hasValue(scrnMsg.A.no(i).dealNetUnitPrcAmt_A1.getValue())) {
//                if(scrnMsg.A.no(i).dealNetUnitPrcAmt_A1.getValue().compareTo(BigDecimal.ZERO)==0) {
//                    scrnMsg.A.no(i).dealNetUnitPrcAmt_A1.setErrorInfo(1, "NFCM0191E");
//                }
//            }
            // END   2016/07/13 S.Fujita [QC#11447,DEL] 
            // END   2016/06/20 S.Fujita [QC#9454,MOD]
        }
    }

    /**
     * setFuncButton
     * @param scrnMsg
     */
    public static void setFuncButton(EZDCommonHandler scrnAppli, NFCL3000BMsg scrnMsg) {

        if(ZYPCommonFunc.hasValue(scrnMsg.invNum_H1.getValue())) {
            // START 2016/07/04 S.Fujita [QC#11165,MOD]
//            if(ZYPCommonFunc.hasValue(scrnMsg.arCashApplyStsCd_E1.getValue())) {
            // START 2016/10/24 T.Murai [QC13656 ,MOD]
//            if(ZYPCommonFunc.hasValue(scrnMsg.arCashApplyStsCd_E1.getValue()) && scrnMsg.istlPmtTermFlg_H1.getValue().equals(ZYPConstant.FLG_OFF_N)) {
                if(ZYPCommonFunc.hasValue(scrnMsg.arCashApplyStsCd_E1.getValue()) //
                        && scrnMsg.istlPmtTermFlg_H1.getValue().equals(ZYPConstant.FLG_OFF_N) //
                        && ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxBtnFlg.getValue())) {
            // END   2016/10/24 T.Murai [QC13656 ,MOD]
            // END   2016/07/04 S.Fujita [QC#11165,MOD]
                // START 2016/05/19 S.Fujita [QC#8478,DEL]
//                scrnAppli.setButtonProperties("Installments" , "Installments" , "Installments"  , 1, null);
                // END 2016/05/19 S.Fujita [QC#8478,DEL]
                scrnAppli.setButtonProperties("AdjustInvoice", "AdjustInvoice", "Adjust Invoice", 1, null);
                scrnAppli.setButtonProperties("Activity"     , "Activity"     , "Activity"      , 1, null);
            } else {
                // START 2016/05/19 S.Fujita [QC#8478,DEL]
//                scrnAppli.setButtonProperties("Installments" , "Installments" , "Installments"  , 0, null);
                // END 2016/05/19 S.Fujita [QC#8478,DEL]
                scrnAppli.setButtonProperties("AdjustInvoice", "AdjustInvoice", "Adjust Invoice", 0, null);
                scrnAppli.setButtonProperties("Activity"     , "Activity"     , "Activity"      , 0, null);
            }
            // START 2016/05/19 S.Fujita [QC#8478,MOD]
//            scrnAppli.setButtonProperties("MaterDetailes", "MaterDetailes", "Meter Detailes", 1, null);
            scrnAppli.setButtonProperties("MaterDetailes", "MaterDetailes", "Meter Details", 1, null);
            // END 2016/05/19 S.Fujita [QC#8478,MOD]
            // START 2016/07/13 S.Fujita [QC#11445,MOD]
//            scrnAppli.setButtonProperties("CreditRebill" , "CreditRebill" , "Credit Rebill" , 1, null);
            // START 2016/10/24 T.Murai [QC#14760 ,MOD]
//            if(scrnMsg.fnlzInvFlg_H4.getValue().equals(ZYPConstant.FLG_ON_Y)) {
            if(scrnMsg.fnlzInvFlg_H4.getValue().equals(ZYPConstant.FLG_ON_Y) 
                    && !INV_TP.CREDIT_MEMO.equals(scrnMsg.invTpCd_H1.getValue())) {
            // END   2016/10/24 T.Murai [QC#14760 ,MOD]
                scrnAppli.setButtonProperties("CreditRebill" , "CreditRebill" , "Credit Rebill" , 1, null);
            } else {
                scrnAppli.setButtonProperties("CreditRebill" , "CreditRebill" , "Credit Rebill" , 0, null);
            }
            // END   2016/07/13 S.Fujita [QC#11445,MOD]
            // START 2016/05/19 S.Fujita [QC#8478,ADD]
            scrnAppli.setButtonProperties("Installments" , "Installments" , "Installments"  , 1, null);
            // END 2016/05/19 S.Fujita [QC#8478,ADD]
            // START 2017/12/25 E.Kameishi [QC#20312,ADD]
            scrnAppli.setButtonProperties("PrintedInvoice" , "PrintedInvoice" , "Printed Invoice"  , 1, null);
            // END 2017/12/25 E.Kameishi [QC#20312,ADD]
            if(scrnMsg.dsPmtMethCd_E1.getValue().equals(DS_PMT_METH.CREDIT_CARD)) {
                scrnAppli.setButtonProperties("CreditCard"   , "CreditCard"   , "Credit Card"   , 1, null);
            } else {
                scrnAppli.setButtonProperties("CreditCard"   , "CreditCard"   , "Credit Card"   , 0, null);
            }
        } else {
            scrnAppli.setButtonProperties("Installments" , "Installments" , "Installments"  , 0, null);
            // START 2016/05/19 S.Fujita [QC#8478,MOD]
//            scrnAppli.setButtonProperties("MaterDetailes", "MaterDetailes", "Meter Detailes"  , 0, null);
            scrnAppli.setButtonProperties("MaterDetailes", "MaterDetailes", "Meter Details"  , 0, null);
            // END 2016/05/19 S.Fujita [QC#8478,MOD]
            scrnAppli.setButtonProperties("CreditRebill" , "CreditRebill" , "Credit Rebill" , 0, null);
            scrnAppli.setButtonProperties("AdjustInvoice", "AdjustInvoice", "Adjust Invoice", 0, null);
            scrnAppli.setButtonProperties("Activity"     , "Activity"     , "Activity"      , 0, null);
            // START 2017/12/25 E.Kameishi [QC#20312,ADD]
            scrnAppli.setButtonProperties("PrintedInvoice" , "PrintedInvoice" , "Printed Invoice"  , 0, null);
            // END 2017/12/25 E.Kameishi [QC#20312,ADD]
            if(scrnMsg.dsPmtMethCd_E1.getValue().equals(DS_PMT_METH.CREDIT_CARD)) {
                scrnAppli.setButtonProperties("CreditCard"   , "CreditCard"   , "Credit Card"   , 1, null);
            } else {
                scrnAppli.setButtonProperties("CreditCard"   , "CreditCard"   , "Credit Card"   , 0, null);
            }
        }
    }

    /**
     * setProtectLineInput
     * @param scrnAppli
     * @param scrnMsg
     */
    public static void setProtectLineInput(NFCL3000BMsg scrnMsg) {

        // START 2016/06/20 S.Fujita [QC#9454,MOD]
        //if(ZYPCommonFunc.hasValue(scrnMsg.invNum_H1.getValue())) {
        //    return;
        //}
        if(!scrnMsg.sysSrcCd_H1.getValue().equals(SYS_SRC.S21_ACCOUNTING_AR)) {
            return;
        }
        // END   2016/06/20 S.Fujita [QC#9454,MOD]
        for(int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // START 2017/12/25 E.Kameishi [QC#20312,ADD]
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxMstChkFlg_A1) && ZYPConstant.FLG_OFF_N.equals(scrnMsg.A.no(i).xxMstChkFlg_A1.getValue())) {
                scrnMsg.A.no(i).taxPct_A1.setInputProtected(true);
                scrnMsg.A.no(i).invLineDealTaxAmt_A1.setInputProtected(true);
            }
            // END 2017/12/25 E.Kameishi [QC#20312,ADD]
        }
    }

    /**
     * setProtectBOLInput
     * @param scrnAppli
     * @param scrnMsg
     */
    public static void setProtectBOLInput(EZDCommonHandler scrnAppli, NFCL3000BMsg scrnMsg) {

        // START 2016/06/20 S.Fujita [QC#9454,MOD]
        if(scrnMsg.fnlzInvFlg_H4.getValue().equals(ZYPConstant.FLG_ON_Y) || !scrnMsg.sysSrcCd_H1.getValue().equals(SYS_SRC.S21_ACCOUNTING_AR)) {
            scrnAppli.setButtonProperties("Calc"     , "Calc"     , "Calc"    , 0, null);
        } else {
            scrnAppli.setButtonProperties("Calc"     , "Calc"     , "Calc"    , 1, null);
        }
        // END   2016/06/20 S.Fujita [QC#9454,MOD]

    }

    /**
     * setProtectSlsCrInput
     * @param scrnAppli
     * @param scrnMsg
     */
    public static void setProtectSlsCrInput(NFCL3000BMsg scrnMsg) {

        // START 2016/06/20 S.Fujita [QC#9454,MOD]
        //if(ZYPCommonFunc.hasValue(scrnMsg.invNum_H1.getValue())) {
        //    return;
        //}
        // END   2016/06/20 S.Fujita [QC#9454,MOD]

        if(ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_BA.getValue())) {
            for(int i = 0; i < scrnMsg.B.getValidCount(); i++) {

                // START 2016/09/05 S.Fujita [QC#13648,DEL]
//                scrnMsg.B.no(i).invLineSplPct_B2.setInputProtected(true);
                // END   2016/09/05 S.Fujita [QC#13648,DEL]
                scrnMsg.B.no(i).slsRepCrPct_B2.setInputProtected(true);
                scrnMsg.B.no(i).dealSlsCrAmt_B1.setInputProtected(false);
                scrnMsg.B.no(i).dealSlsCrAmt_B1.setIndispensable(true);

                // START 2016/10/31 T.Murai [QC#14546, ADD]
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).xxHldFlg_B1.getValue())) {
                    scrnMsg.B.no(i).setInputProtected(true);
                }
                // END   2016/10/31 T.Murai [QC#14546, ADD]
            }
        } else {
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                // START 2016/09/05 S.Fujita [QC#13648,DEL]
                // scrnMsg.B.no(i).invLineSplPct_B2.setInputProtected(false);
                // END 2016/09/05 S.Fujita [QC#13648,DEL]
                scrnMsg.B.no(i).slsRepCrPct_B2.setInputProtected(false);
                scrnMsg.B.no(i).slsRepCrPct_B2.setIndispensable(true);
                scrnMsg.B.no(i).dealSlsCrAmt_B1.setInputProtected(true);

                // START 2016/10/31 T.Murai [QC#14546, ADD]
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).xxHldFlg_B1.getValue())) {
                    scrnMsg.B.no(i).setInputProtected(true);
                }
                // END   2016/10/31 T.Murai [QC#14546, ADD]
            }
        }
    }

    /**
     * setProtectAcctInput
     * @param scrnAppli
     * @param scrnMsg
     */
    public static void setProtectAcctInput(EZDCommonHandler scrnAppli, NFCL3000BMsg scrnMsg) {

        if(scrnMsg.xxRadioBtn_C.getValue().equals(ACCT_DIST_SMRY)) {
            scrnMsg.xxChkBox_C.setInputProtected(true);
            scrnMsg.C.setInputProtected(true);
            scrnAppli.setButtonProperties("Calc"     , "Calc"     , "Calc"    , 0, null);
            scrnAppli.setButtonProperties("AddLine_C"   , "AddLine_C"   , "Add Line"    , 0, null);
            scrnAppli.setButtonProperties("DeleteLine_C", "DeleteLine_C", "Delete Line" , 0, null);
            scrnAppli.setButtonProperties("btn9" , "CMN_Reset"   , "Reset"   , 1, null);
            // START 2016/09/16 S.Yoshida [QC#13491,DEL]
//            for(int i = 0; i < scrnMsg.C.getValidCount(); i++) {
//                scrnAppli.setButtonEnabled("GlCombnSearch_C", i, false);
//            }
            // END   2016/09/16 S.Yoshida [QC#13491,DEL]
        } else {
            scrnMsg.C.setInputProtected(true);

            scrnMsg.xxChkBox_C.setInputProtected(false);
            if(scrnMsg.fnlzInvFlg_H4.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                scrnMsg.xxChkBox_C.setInputProtected(true);
                scrnMsg.C.setInputProtected(true);
                scrnAppli.setButtonProperties("Calc"     , "Calc"     , "Calc"    , 0, null);
                scrnAppli.setButtonProperties("AddLine_C"   , "AddLine_C"   , "Add Line"    , 0, null);
                scrnAppli.setButtonProperties("DeleteLine_C", "DeleteLine_C", "Delete Line" , 0, null);
                scrnAppli.setButtonProperties("btn9" , "CMN_Reset"   , "Reset"   , 1, null);
                // START 2016/09/16 S.Yoshida [QC#13491,DEL]
//                for(int i = 0; i < scrnMsg.C.getValidCount(); i++) {
//                    scrnAppli.setButtonEnabled("GlCombnSearch_C", i, false);
//                }
                // END   2016/09/16 S.Yoshida [QC#13491,DEL]

                // START 2016/09/26 S.Fujita [QC#13362,ADD]
                for(int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                    if (AJE_INV_ACCT_CLS.REV_EARN_TMPLT.equals(scrnMsg.C.no(i).ajeInvAcctClsCd_C1.getValue()) || AJE_INV_ACCT_CLS.REVENUE_EARNED.equals(scrnMsg.C.no(i).ajeInvAcctClsCd_C1.getValue()) 
                            || (DR_CR_TP_DEBIT.equals(scrnMsg.C.no(i).drCrTpCd_C1.getValue()) && AJE_INV_ACCT_CLS.UNEARNED_REVENUE.equals(scrnMsg.C.no(i).ajeInvAcctClsCd_C1.getValue()))) {
                        if(ZYPCommonFunc.hasValue(scrnMsg.C.no(i).invErrMsgTxt_C1.getValue())) {
                            scrnMsg.C.no(i).xxScrItem61Txt_C1.setInputProtected(false);
                            scrnMsg.C.no(i).xxScrItem61Txt_C1.setIndispensable(true);
                            scrnAppli.setButtonProperties("Calc"     , "Calc"     , "Calc"    , 1, null);
                            continue;
                        } else {
                            continue;
                        }
                    }
                }
                // END   2016/09/26 S.Fujita [QC#13362,ADD]

            } else {
                for(int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                    // Protect
                    // START 2016/07/20 S.Fujita [QC#10148,DEL]
//                    if(i%2==0) {
//                        scrnMsg.C.no(i).xxChkBox_C1.setInputProtected(false);
//                        scrnMsg.C.no(i).xxLineNum_C1.setInputProtected(false);
//                        scrnMsg.C.no(i).xxLineNum_C1.setIndispensable(true);
//                        scrnMsg.C.no(i).ajeInvAcctClsCd_C1.setInputProtected(false);
//                    } else {
//                        scrnMsg.C.no(i).xxChkBox_C1.setInputProtected(true);
//                        scrnMsg.C.no(i).xxLineNum_C1.setInputProtected(true);
//                        scrnMsg.C.no(i).xxLineNum_C1.setIndispensable(false);
//                        scrnMsg.C.no(i).ajeInvAcctClsCd_C1.setInputProtected(true);
//                    }
                    // END   2016/07/20 S.Fujita [QC#10148,DEL]

                    // START 2016/09/26 S.Fujita [QC#13362,ADD]
                    if (AJE_INV_ACCT_CLS.REV_EARN_TMPLT.equals(scrnMsg.C.no(i).ajeInvAcctClsCd_C1.getValue()) || AJE_INV_ACCT_CLS.REVENUE_EARNED.equals(scrnMsg.C.no(i).ajeInvAcctClsCd_C1.getValue()) 
                            || (DR_CR_TP_DEBIT.equals(scrnMsg.C.no(i).drCrTpCd_C1.getValue()) && AJE_INV_ACCT_CLS.UNEARNED_REVENUE.equals(scrnMsg.C.no(i).ajeInvAcctClsCd_C1.getValue()))) {
                        if(ZYPCommonFunc.hasValue(scrnMsg.C.no(i).invErrMsgTxt_C1.getValue())) {
                            scrnMsg.C.no(i).xxScrItem61Txt_C1.setInputProtected(false);
                            scrnMsg.C.no(i).xxScrItem61Txt_C1.setIndispensable(true);
                            continue;
                        } else {
                            continue;
                        }
                    }
                    // END   2016/09/26 S.Fujita [QC#13362,ADD]

                    // START 2022/12/20 Y.Mochida [QC#56616,DEL]
//                    scrnMsg.C.no(i).xxScrItem61Txt_C1.setInputProtected(true);
                    // END   2022/12/20 Y.Mochida [QC#56616,DEL]
                    
                    if(scrnMsg.C.no(i).drCrTpCd_C1.getValue().equals(DR_CR_TP_DEBIT)) {
                        scrnMsg.C.no(i).jrnlDealAmt_C1.setInputProtected(false);
                        scrnMsg.C.no(i).jrnlDealAmt_C2.setInputProtected(true);

                        scrnMsg.C.no(i).jrnlDealAmt_C1.setIndispensable(true);
                        scrnMsg.C.no(i).jrnlDealAmt_C2.setIndispensable(false);

                        // START 2022/12/20 Y.Mochida [QC#56616,ADD]
                        scrnMsg.C.no(i).xxScrItem61Txt_C1.setInputProtected(true);
                        // END   2022/12/20 Y.Mochida [QC#56616,ADD]
                        
                        // START 2016/09/26 S.Fujita [QC#13362,DEL]
//                        scrnMsg.C.no(i).xxChkBox_C1.setInputProtected(false);
                        // END   2016/09/26 S.Fujita [QC#13362,DEL]
                    } else {
                        scrnMsg.C.no(i).jrnlDealAmt_C1.setInputProtected(true);
                        scrnMsg.C.no(i).jrnlDealAmt_C2.setInputProtected(false);

                        scrnMsg.C.no(i).jrnlDealAmt_C1.setIndispensable(false);
                        scrnMsg.C.no(i).jrnlDealAmt_C2.setIndispensable(true);
                        // START 2016/07/20 S.Fujita [QC#10148,ADD]
                        scrnMsg.C.no(i).xxChkBox_C1.setInputProtected(false);
                        scrnMsg.C.no(i).xxLineNum_C1.setInputProtected(false);
                        scrnMsg.C.no(i).xxLineNum_C1.setIndispensable(true);
                        scrnMsg.C.no(i).ajeInvAcctClsCd_C1.setInputProtected(false);
                        // END   2016/07/20 S.Fujita [QC#10148,ADD]
                        // START 2022/12/20 Y.Mochida [QC#56616,ADD]
                        scrnMsg.C.no(i).xxScrItem61Txt_C1.setInputProtected(false);
                        // END   2022/12/20 Y.Mochida [QC#56616,ADD]
                    }

                    scrnMsg.C.no(i).ajeInvAcctClsCd_C1.setIndispensable(true);
                    scrnMsg.C.no(i).xxScrItem61Txt_C1.setIndispensable(true);
                    // START 2016/09/16 S.Yoshida [QC#13491,DEL]
//                    scrnAppli.setButtonEnabled("GlCombnSearch_C", i, true);
                    // END   2016/09/16 S.Yoshida [QC#13491,DEL]
                }
                scrnAppli.setButtonProperties("AddLine_C"   , "AddLine_C"   , "Add Line"    , 1, null);
                if(scrnMsg.C.getValidCount() > 0) {
                    scrnAppli.setButtonProperties("DeleteLine_C", "DeleteLine_C", "Delete Line" , 1, null);
                } else {
                    scrnAppli.setButtonProperties("DeleteLine_C", "DeleteLine_C", "Delete Line" , 0, null);
                }
                scrnAppli.setButtonProperties("Calc"     , "Calc"     , "Calc"    , 1, null);
                scrnAppli.setButtonProperties("btn9" , "CMN_Reset"   , "Reset"   , 1, null);
            }
        }
    }

    /**
     * check_TAB
     * @param scrnMsg
     * @return
     */
    public static boolean check_TAB(NFCL3000BMsg scrnMsg) {

        //-----------------------------------
        // Detail Count
        //-----------------------------------
        if(scrnMsg.xxDplyTab.getValue().equals(TAB_Line)) {
            // Line TAB
            if(scrnMsg.A.getValidCount() < 1) {
                scrnMsg.setMessageInfo("NFCM0095E", null);
                scrnMsg.xxDplyTab.setValue(TAB_Line);
                return false;
            }
        }
        // START 2016/06/03 S.Fujita [QC#9452,DEL]
//        if(scrnMsg.xxDplyTab.getValue().equals(TAB_SalesCredit)) {
//            // Sales Credit TAB
//            if(scrnMsg.B.getValidCount() < 1) {
//                scrnMsg.setMessageInfo("NFCM0095E", null);
//                scrnMsg.xxDplyTab.setValue(TAB_SalesCredit);
//                return false;
//            }
//        }
        // END   2016/06/03 S.Fujita [QC#9452,DEL]
        if(scrnMsg.xxDplyTab.getValue().equals(TAB_BOL)) {
            // BOL Tab
            if(scrnMsg.D.getValidCount() < 1) {
                scrnMsg.setMessageInfo("NFCM0095E", null);
                scrnMsg.xxDplyTab.setValue(TAB_BOL);
                return false;
            }
        }
        // START 2016/06/03 S.Fujita [QC#9452,DEL]
//        if(scrnMsg.xxDplyTab.getValue().equals(TAB_Accounting)) {
//            // Accounting TAB
//            if(ZYPCommonFunc.hasValue(scrnMsg.invNum_H1.getValue())) {
//                if(scrnMsg.C.getValidCount() < 1) {
//                    scrnMsg.setMessageInfo("NFCM0095E", null);
//                    scrnMsg.xxDplyTab.setValue(TAB_Accounting);
//                    return false;
//                }
//            }
//        }
        // END   2016/06/03 S.Fujita [QC#9452,DEL]
        return true;
    }

    /**
     * check_TAB_Accounting
     * @param scrnMsg
     * @return
     */
    public static boolean check_TAB_Accounting(NFCL3000BMsg scrnMsg) {
        //-----------------------------------
        // Detail Count
        //-----------------------------------
        // Line TAB
        if(scrnMsg.A.getValidCount() < 1) {
            scrnMsg.setMessageInfo("NFCM0095E", null);
            scrnMsg.xxDplyTab.setValue(TAB_Line);
            return false;
        }
        // Sales Credit TAB
        if(scrnMsg.B.getValidCount() < 1) {
            scrnMsg.setMessageInfo("NFCM0095E", null);
            scrnMsg.xxDplyTab.setValue(TAB_SalesCredit);
            return false;
        }
        // BOL Tab
        if(scrnMsg.D.getValidCount() < 1) {
            scrnMsg.setMessageInfo("NFCM0095E", null);
            scrnMsg.xxDplyTab.setValue(TAB_BOL);
            return false;
        }
        return true;
    }

    /**
     * check_TAB_Submit
     * @param scrnMsg
     */
    public static boolean check_TAB_Submit(EZDCommonHandler scrnAppli, NFCL3000BMsg scrnMsg) {
        //-----------------------------------
        // Detail Count
        //-----------------------------------
        // Line TAB
        if(scrnMsg.A.getValidCount() < 1) {
            scrnMsg.setMessageInfo("NFCM0095E", null);
            scrnMsg.xxDplyTab.setValue(TAB_Line);
            return false;
        }

        if(ZYPCommonFunc.hasValue(scrnMsg.invNum_H1.getValue())) {
            // Accounting TAB
            if(scrnMsg.C.getValidCount() < 1) {
                scrnMsg.setMessageInfo("NFCM0095E", null);
                scrnMsg.xxDplyTab.setValue(TAB_Accounting);
                scrnMsg.xxRadioBtn_C.setValue(ACCT_DIST_EDIT);
                scrnMsg.xxRadioBtn_CB.setValue(ACCT_DIST_EDIT);
                scrnAppli.setButtonProperties("AddLine_C"   , "AddLine_C"   , "Add Line"    , 1, null);
                return false;
            }
            // START 2016/06/20 S.Fujita [QC#9454,ADD]
            // Sales Credit TAB
            if(scrnMsg.B.getValidCount() < 1) {
                scrnMsg.setMessageInfo("NFCM0095E", null);
                scrnMsg.xxDplyTab.setValue(TAB_SalesCredit);
                return false;
            }
            // BOL TAB
            if(scrnMsg.D.getValidCount() < 1) {
                scrnMsg.setMessageInfo("NFCM0095E", null);
                scrnMsg.xxDplyTab.setValue(TAB_BOL);
                return false;
            }
            // END   2016/06/20 S.Fujita [QC#9454,ADD]
        }
        return true;
    }

    /**
     * set_TAB
     * @param scrnMsg
     */
    public static void set_TAB(NFCL3000BMsg scrnMsg) {

        boolean isError = false;
        // Line
        for(int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if(scrnMsg.A.no(i).invBolLineNum_A1.isError()) {
                isError = true;
            }
            if(scrnMsg.A.no(i).invLineNum_A1.isError()) {
                isError = true;
            }
            if(scrnMsg.A.no(i).mdseCd_A1.isError()) {
                isError = true;
            }
            if(scrnMsg.A.no(i).dealNetUnitPrcAmt_A1.isError()) {
                isError = true;
            }
        }
        if(isError) {
            scrnMsg.xxDplyTab.setValue(TAB_Line);
            return;
        }

        // Sales Credit
        isError = false;
        for(int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            if(scrnMsg.B.no(i).invBolLineNum_B1.isError()) {
                isError = true;
            }
            if(scrnMsg.B.no(i).invLineNum_B1.isError()) {
                isError = true;
            }
            if(scrnMsg.B.no(i).invLineSplPct_B1.isError()) {
                isError = true;
            }
            // START 2016/07/14 S.Fujita [QC#11157,MOD]
//            if(scrnMsg.B.no(i).slsRepTocCd_B1.isError()) {
            if(scrnMsg.B.no(i).psnNum_B1.isError()) {
            // END   2016/07/14 S.Fujita [QC#11157,MOD]
                isError = true;
            }
            if(scrnMsg.B.no(i).slsRepCrPct_B2.isError()) {
                isError = true;
            }
            if(scrnMsg.B.no(i).dealSlsCrAmt_B1.isError()) {
                isError = true;
            }
        }
        if(isError) {
            scrnMsg.xxDplyTab.setValue(TAB_SalesCredit);
            return;
        }
    }
    // START 2016/05/24 S.Fujita [QC#8522,ADD]
    /**
     * setMandatorySalesCreditInput
     * @param scrnMsg
     */
    public static void setMandatorySalesCreditInput(NFCL3000BMsg scrnMsg) {

        for(int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            if(ZYPCommonFunc.hasValue(scrnMsg.B.no(i).dfrdRevFlg_B1.getValue())) {
                if(ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).dfrdRevFlg_B1.getValue())) {
                    scrnMsg.B.no(i).dfrdAcctgRuleDurnAot_B1.setIndispensable(true);
                    scrnMsg.B.no(i).durnStartDt_B1.setIndispensable(true);

                    scrnMsg.B.no(i).dfrdAcctgRuleDurnAot_B1.setInputProtected(false);
                    scrnMsg.B.no(i).durnStartDt_B1.setInputProtected(false);

                    if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).fixDurnFlg_B1.getValue())) {
                        scrnMsg.B.no(i).dfrdAcctgRuleDurnAot_B1.setInputProtected(true);
                    }
                } else {
                    scrnMsg.B.no(i).dfrdAcctgRuleDurnAot_B1.setIndispensable(false);
                    scrnMsg.B.no(i).durnStartDt_B1.setIndispensable(false);

                    scrnMsg.B.no(i).dfrdAcctgRuleDurnAot_B1.setInputProtected(true);
                    scrnMsg.B.no(i).durnStartDt_B1.setInputProtected(true);
                }
            }

            // START 2016/10/31 T.Murai [QC#14546, ADD]
            if ( ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).xxHldFlg_B1.getValue())) {
                scrnMsg.B.no(i).setInputProtected(true);
            }
            // END   2016/10/31 T.Murai [QC#14546, ADD]
        }
    }
    // END   2016/05/24 S.Fujita [QC#8522,ADD]

    // START 2016/07/05 S.Fujita [QC#9992,ADD]
    /**
     * check_InvDt
     * @param scrnMsg
     */
    public static boolean check_InvDt(NFCL3000BMsg scrnMsg) {
        boolean isSuccess = true;

        // START 2019/03/07 S.Takami [QC#30678,ADD]
        scrnMsg.addCheckItem(scrnMsg.invDt_H1);
        scrnMsg.putErrorScreen();
        // END   2019/03/07 S.Takami [QC#30678,ADD]
        int nextYear = NFCL3000CommonLogic.getNextYyyy(scrnMsg);
        // Header Check
        int intTm = Integer.parseInt(scrnMsg.invDt_H1.getValue());
        if(nextYear < intTm){
            isSuccess = false;
        }
        return isSuccess;
    }

    /**
     * <pre>
     * get NextYear
     * @param scrnMsg
     * </pre>
     */
    public static int getNextYyyy(NFCL3000BMsg scrnMsg){

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat inSdf = new SimpleDateFormat();
        inSdf.applyPattern(S21CalendarUtilConstants.TYPE_YYYYMMDD);

        try {
            cal.setTime(inSdf.parse((ZYPDateUtil.getSalesDate())));
        } catch (ParseException e) {
            return 0;
        }

        cal.add(Calendar.YEAR, 1);

        SimpleDateFormat outSdf = new SimpleDateFormat();
        outSdf.applyPattern("yyyyMMdd");

        return Integer.parseInt(outSdf.format(cal.getTime()));
    }
    // END   2016/07/05 S.Fujita [QC#9992,ADD]

    // START 2016/07/14 S.Fujita [QC#11157,MOD]
    /**
     * Get Param NWAL1130 For Salesrep
     * @param scrnMsg NFCL3000BMsg
     * @return Param NWAL1130 For Salesrep
     */
    public static Object[] getParamNWAL1130ForSlsrep(NFCL3000BMsg scrnMsg) {

        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Sales Rep Search";

        // START 2018/09/14 S.Ohki [QC#28089,MOD]
//        StringBuilder sb = new StringBuilder();
//        sb.append("SELECT");
//        sb.append("    T.GLBL_CMPY_CD");
//        sb.append("   ,T.EZCANCELFLAG");
//        sb.append("   ,SP.PSN_NUM");
//        sb.append("   ,T.TOC_NM");
//        sb.append("   ,SP.LINE_BIZ_TP_CD");
//        sb.append("   ,CB.COA_BR_NM");
//        sb.append("   ,OFRT.ORG_FUNC_ROLE_TP_NM");
//        sb.append("   ,T.TOC_CD ");
//        sb.append("FROM");
//        sb.append("    TOC T");
//        sb.append("   ,ORG_FUNC_ROLE_TP OFRT");
//        sb.append("   ,BIZ_AREA_ORG BA");
//        sb.append("   ,ORG_FUNC_ASG OFA");
//        sb.append("   ,S21_PSN SP");
//        sb.append("   ,COA_BR CB ");
//        sb.append("   ,S21_ORG SO ");
//        sb.append("WHERE");
//        sb.append("        T.GLBL_CMPY_CD        =  '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
//        sb.append("    AND T.EZCANCELFLAG        = '0'");
//        sb.append("    AND T.GLBL_CMPY_CD        = OFRT.GLBL_CMPY_CD");
//        sb.append("    AND T.ORG_FUNC_ROLE_TP_CD = OFRT.ORG_FUNC_ROLE_TP_CD");
//        sb.append("    AND OFRT.SLS_REP_FLG      = '").append(ZYPConstant.FLG_ON_Y).append("'");
//        sb.append("    AND OFRT.EZCANCELFLAG     = '0'");
//        sb.append("    AND OFRT.GLBL_CMPY_CD     = BA.GLBL_CMPY_CD");
//        sb.append("    AND OFRT.FIRST_ORG_CD     = BA.BIZ_AREA_ORG_CD");
//        sb.append("    AND BA.SLS_FLG            = '").append(ZYPConstant.FLG_ON_Y).append("'");
//        sb.append("    AND BA.ORG_STRU_TP_CD     = '").append(ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY).append("'");
//        sb.append("    AND BA.EZCANCELFLAG       = '0'");
//        sb.append("    AND T.GLBL_CMPY_CD        = OFA.GLBL_CMPY_CD");
//        sb.append("    AND T.TOC_CD              = OFA.TOC_CD");
//        sb.append("    AND OFA.EZCANCELFLAG      = '0'");
//        sb.append("    AND T.GLBL_CMPY_CD        = SO.GLBL_CMPY_CD ");
//        sb.append("    AND T.TOC_CD              = SO.TOC_CD");
//        sb.append("    AND SO.RGTN_STS_CD        = '").append(RGTN_STS.READY_FOR_ORDER_TAKING).append("'");
//        sb.append("    AND SO.EZCANCELFLAG        = '0'");
//        sb.append("    AND OFA.GLBL_CMPY_CD      = SP.GLBL_CMPY_CD");
//        sb.append("    AND OFA.PSN_CD            = SP.PSN_CD");
//        sb.append("    AND SP.EFF_FROM_DT        <= '").append(scrnMsg.procDt.getValue()).append("'");
//        sb.append("    AND (SP.EFF_THRU_DT       >= '").append(scrnMsg.procDt.getValue()).append("' OR SP.EFF_THRU_DT IS NULL)");
//        sb.append("    AND SP.EZCANCELFLAG       = '0'");
//        sb.append("    AND T.GLBL_CMPY_CD        = CB.GLBL_CMPY_CD (+)");
//        sb.append("    AND T.COA_BR_CD           = CB.COA_BR_CD (+)");
//        sb.append("    AND CB.EZCANCELFLAG (+)   = '0'");
//
//        params[2] = sb.toString();

        String sql = createSalesRepSearchSql(scrnMsg);
        params[2] = sql;
        // END 2018/09/14 S.Ohki [QC#28089,MOD]

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];
        // START 2016/11/28 Y.Tsuchimoto [QC#16268,MOD]
        //whereArray0[0] = "Resource Number";
        whereArray0[0] = "Sales Rep Number";
        // END   2016/11/28 Y.Tsuchimoto [QC#16268,MOD]
        whereArray0[1] = "PSN_NUM";
        whereArray0[2] = scrnMsg.psnNum_H1.getValue();

        if (ZYPCommonFunc.hasValue(scrnMsg.psnNum_H1) || ZYPCommonFunc.hasValue(scrnMsg.slsRepTocNm_H1) ) {
            whereArray0[2] = scrnMsg.psnNum_H1.getValue();
        } else {
            whereArray0[2] = PERCENT;
        }

        whereArray0[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Name";
        whereArray1[1] = "TOC_NM";
        whereArray1[2] = scrnMsg.slsRepTocNm_H1.getValue();
        whereArray1[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[4];
        // START 2016/11/28 Y.Tsuchimoto [QC#16268,MOD]
        //columnArray0[0] = "Resource Number";
        columnArray0[0] = "Sales Rep Number";
        // END   2016/11/28 Y.Tsuchimoto [QC#16268,MOD]
        columnArray0[1] = "PSN_NUM";
        columnArray0[2] = BigDecimal.valueOf(12);
        columnArray0[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Name";
        columnArray1[1] = "TOC_NM";
        columnArray1[2] = BigDecimal.valueOf(30);
        columnArray1[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[4];
        columnArray2[0] = "Line of Business";
        columnArray2[1] = "LINE_BIZ_TP_CD";
        columnArray2[2] = BigDecimal.valueOf(10);
        columnArray2[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[4];
        columnArray3[0] = "Branch";
        columnArray3[1] = "COA_BR_NM";
        columnArray3[2] = BigDecimal.valueOf(20);
        columnArray3[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[4];
        columnArray4[0] = "Role";
        columnArray4[1] = "ORG_FUNC_ROLE_TP_NM";
        columnArray4[2] = BigDecimal.valueOf(20);
        columnArray4[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray4);

        Object[] columnArray5 = new Object[4];
        columnArray5[0] = "TOC_CD";
        columnArray5[1] = "TOC_CD";
        columnArray5[2] = BigDecimal.valueOf(0);
        columnArray5[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray5);

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = "PSN_NUM";
        sortConditionArray0[1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[6] = scrnMsg.Z;

        return params;
    }

    /**
     * Get Param NWAL1130 For Salesrep_B
     * @param scrnMsg NFCL3000BMsg
     * @return Param NWAL1130 For Salesrep
     */
    public static Object[] getParamNWAL1130ForSlsrep_B(NFCL3000BMsg scrnMsg) {

        int idx = scrnMsg.xxCellIdx.getValueInt();
        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Sales Rep Search";

        // START 2018/09/14 S.Ohki [QC#28089,MOD]
//        StringBuilder sb = new StringBuilder();
//        sb.append("SELECT");
//        sb.append("    T.GLBL_CMPY_CD");
//        sb.append("   ,T.EZCANCELFLAG");
//        sb.append("   ,SP.PSN_NUM");
//        sb.append("   ,T.TOC_NM");
//        sb.append("   ,SP.LINE_BIZ_TP_CD");
//        sb.append("   ,CB.COA_BR_NM");
//        sb.append("   ,OFRT.ORG_FUNC_ROLE_TP_NM");
//        sb.append("   ,T.TOC_CD ");
//        sb.append("FROM");
//        sb.append("    TOC T");
//        sb.append("   ,ORG_FUNC_ROLE_TP OFRT");
//        sb.append("   ,BIZ_AREA_ORG BA");
//        sb.append("   ,ORG_FUNC_ASG OFA");
//        sb.append("   ,S21_PSN SP");
//        sb.append("   ,COA_BR CB ");
//        sb.append("   ,S21_ORG SO ");
//        sb.append("WHERE");
//        sb.append("        T.GLBL_CMPY_CD        =  '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
//        sb.append("    AND T.EZCANCELFLAG        = '0'");
//        sb.append("    AND T.GLBL_CMPY_CD        = OFRT.GLBL_CMPY_CD");
//        sb.append("    AND T.ORG_FUNC_ROLE_TP_CD = OFRT.ORG_FUNC_ROLE_TP_CD");
//        sb.append("    AND OFRT.SLS_REP_FLG      = '").append(ZYPConstant.FLG_ON_Y).append("'");
//        sb.append("    AND OFRT.EZCANCELFLAG     = '0'");
//        sb.append("    AND OFRT.GLBL_CMPY_CD     = BA.GLBL_CMPY_CD");
//        sb.append("    AND OFRT.FIRST_ORG_CD     = BA.BIZ_AREA_ORG_CD");
//        sb.append("    AND BA.SLS_FLG            = '").append(ZYPConstant.FLG_ON_Y).append("'");
//        sb.append("    AND BA.ORG_STRU_TP_CD     = '").append(ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY).append("'");
//        sb.append("    AND BA.EZCANCELFLAG       = '0'");
//        sb.append("    AND T.GLBL_CMPY_CD        = OFA.GLBL_CMPY_CD");
//        sb.append("    AND T.TOC_CD              = OFA.TOC_CD");
//        sb.append("    AND OFA.EZCANCELFLAG      = '0'");
//        sb.append("    AND T.GLBL_CMPY_CD        = SO.GLBL_CMPY_CD ");
//        sb.append("    AND T.TOC_CD              = SO.TOC_CD");
//        sb.append("    AND SO.RGTN_STS_CD        = '").append(RGTN_STS.READY_FOR_ORDER_TAKING).append("'");
//        sb.append("    AND SO.EZCANCELFLAG        = '0'");
//        sb.append("    AND OFA.GLBL_CMPY_CD      = SP.GLBL_CMPY_CD");
//        sb.append("    AND OFA.PSN_CD            = SP.PSN_CD");
//        sb.append("    AND SP.EFF_FROM_DT        <= '").append(scrnMsg.procDt.getValue()).append("'");
//        sb.append("    AND (SP.EFF_THRU_DT       >= '").append(scrnMsg.procDt.getValue()).append("' OR SP.EFF_THRU_DT IS NULL)");
//        sb.append("    AND SP.EZCANCELFLAG       = '0'");
//        sb.append("    AND T.GLBL_CMPY_CD        = CB.GLBL_CMPY_CD (+)");
//        sb.append("    AND T.COA_BR_CD           = CB.COA_BR_CD (+)");
//        sb.append("    AND CB.EZCANCELFLAG (+)   = '0'");
//
//        params[2] = sb.toString();

        String sql = createSalesRepSearchSql(scrnMsg);
        params[2] = sql;
        // END 2018/09/14 S.Ohki [QC#28089,MOD]


        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];
        // START 2016/11/28 Y.Tsuchimoto [QC#16268,MOD]
        //whereArray0[0] = "Resource Number";
        whereArray0[0] = "Sales Rep Number";
        // END   2016/11/28 Y.Tsuchimoto [QC#16268,MOD]
        whereArray0[1] = "PSN_NUM";
        whereArray0[2] = scrnMsg.B.no(idx).psnNum_B1.getValue();

        if (ZYPCommonFunc.hasValue(scrnMsg.B.no(idx).psnNum_B1) || ZYPCommonFunc.hasValue(scrnMsg.B.no(idx).tocNm_B1) ) {
            whereArray0[2] = scrnMsg.B.no(idx).psnNum_B1.getValue();
        } else {
            whereArray0[2] = PERCENT;
        }

        whereArray0[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Name";
        whereArray1[1] = "TOC_NM";
        whereArray1[2] = scrnMsg.B.no(idx).tocNm_B1.getValue();
        whereArray1[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[4];
        // START 2016/11/28 Y.Tsuchimoto [QC#16268,MOD]
        //columnArray0[0] = "Resource Number";
        columnArray0[0] = "Sales Rep Number";
        // END   2016/11/28 Y.Tsuchimoto [QC#16268,MOD]
        columnArray0[1] = "PSN_NUM";
        columnArray0[2] = BigDecimal.valueOf(12);
        columnArray0[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Name";
        columnArray1[1] = "TOC_NM";
        columnArray1[2] = BigDecimal.valueOf(30);
        columnArray1[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[4];
        columnArray2[0] = "Line of Business";
        columnArray2[1] = "LINE_BIZ_TP_CD";
        columnArray2[2] = BigDecimal.valueOf(10);
        columnArray2[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[4];
        columnArray3[0] = "Branch";
        columnArray3[1] = "COA_BR_NM";
        columnArray3[2] = BigDecimal.valueOf(20);
        columnArray3[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[4];
        columnArray4[0] = "Role";
        columnArray4[1] = "ORG_FUNC_ROLE_TP_NM";
        columnArray4[2] = BigDecimal.valueOf(20);
        columnArray4[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray4);

        Object[] columnArray5 = new Object[4];
        columnArray5[0] = "TOC_CD";
        columnArray5[1] = "TOC_CD";
        columnArray5[2] = BigDecimal.valueOf(0);
        columnArray5[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray5);

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = "PSN_NUM";
        sortConditionArray0[1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[6] = scrnMsg.Z;

        return params;
    }
    // END   2016/07/14 S.Fujita [QC#11157,MOD]

    // START 2016/08/01 S.Fujita [QC#10148,ADD]
    /**
     * @param scrnMsg NFCL3000BMsg
     */
    public static void setDisplayPattern(NFCL3000BMsg scrnMsg) {
        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();

        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(BIZ_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException("ZZSM4300E", new String[] {userProfSvc.getContextUserInfo().getUserId() });
        }

        if (funcList.contains(FUNC_T030)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab_H1, DIS_PAT_VISIBILITY_ERROR);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab_H1, DIS_PAT_INVISIBILITY_ERROR);
        }
    }

    /**
     * @param scrnMsg NFCL3000BMsg
     * @return boolean
     */
    private static boolean isFunction(NFCL3000BMsg scrnMsg) {
        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();

        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(BIZ_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException("ZZSM4300E", new String[] {userProfSvc.getContextUserInfo().getUserId() });
        }

        if (funcList.contains(FUNC_T020) || funcList.contains(FUNC_T030)) {
            scrnMsg.xxPgFlg_H1.setValue(ZYPConstant.FLG_ON_Y);
            return true;
        } else {
            scrnMsg.xxPgFlg_H1.setValue(ZYPConstant.FLG_OFF_N);
            return false;
        }
    }

    /**
     * addCheckItem_MoreInfo
     * @param scrnMsg
     */
    public static void addCheckItem_MoreInfo(NFCL3000BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.invPrintStsCd_E1);
        scrnMsg.addCheckItem(scrnMsg.acctDt_H1);
        // START 2019/05/10 S.Takami [QC#50148,MOD]
//        scrnMsg.addCheckItem(scrnMsg.invFirstCmntTxt_E1);
        scrnMsg.addCheckItem(scrnMsg.xxInvMemoTxt_E1);
        // END 2019/05/10 S.Takami [QC#50148,MOD]
        scrnMsg.addCheckItem(scrnMsg.dsPmtMethCd_E1);
    }

    /**
     * addCheckItem_MoreInfo_Submit
     * @param scrnMsg
     */
    public static void addCheckItem_MoreInfo_Submit(NFCL3000BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.invPrintStsCd_E1);
        scrnMsg.addCheckItem(scrnMsg.acctDt_H1);
        // START 2019/05/10 S.Takami [QC#50148,MOD]
//        scrnMsg.addCheckItem(scrnMsg.invFirstCmntTxt_E1);
        scrnMsg.addCheckItem(scrnMsg.xxInvMemoTxt_E1);
        // END 2019/05/10 S.Takami [QC#50148,MOD]
        scrnMsg.addCheckItem(scrnMsg.dsPmtMethCd_E1);

        scrnMsg.xxDplyTab.setValue(TAB_MoreInfo);
        scrnMsg.putErrorScreen();
    }
    // END   2016/08/01 S.Fujita [QC#10148,ADD]

    // START 2016/08/22 S.Fujita [QC#12579,ADD]
    /**
     * setAcctDistError
     * @param scrnMsg
     * @return
     */
    public static boolean setAcctDistError(NFCL3000BMsg scrnMsg) {
        boolean isNotError = true;
        for(int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            if(ZYPCommonFunc.hasValue(scrnMsg.C.no(i).invErrMsgTxt_C1.getValue())) {
                isNotError = false;
            }
        }
        return isNotError;
    }
    // END   2016/08/22 S.Fujita [QC#12579,ADD]

    // START 2016/09/16 S.Yoshida [QC#14089,ADD]
    /**
     * getBillPopPram
     * @param scrnMsg NFCL3000BMsg
     * @return Bill to Popup Prameters
     */
    public static Object[] getBillPopPram(NFCL3000BMsg scrnMsg) {
        scrnMsg.xxScrItem10Txt.setValue("BillTo");

        scrnMsg.P.clear();

        // Bill To Only
        scrnMsg.P.no(12).xxPopPrm.setValue("02");
        // Active Only
        scrnMsg.P.no(11).xxPopPrm.setValue("01");

        scrnMsg.P.no(24).xxPopPrm.setValue(ZYPConstant.FLG_OFF_N);
        scrnMsg.P.no(25).xxPopPrm.setValue(ZYPConstant.FLG_ON_Y);
//        if (ZYPCommonFunc.hasValue(scrnMsg.billToCustAcctNm_H3)) {
//            scrnMsg.P.no(25).xxPopPrm.setValue(ZYPConstant.FLG_OFF_N);
//        }
        scrnMsg.P.no(26).xxPopPrm.setValue(ZYPConstant.FLG_ON_Y);

        //NMAL6760
        Object[] param = new Object[27];
        param[0] = scrnMsg.billToCustAcctCd_H3;
        param[1] = scrnMsg.billToCustAcctNm_H3;
        param[2] = scrnMsg.locNum_H3;
        param[3] = scrnMsg.P.no(3).xxPopPrm;
        param[4] = scrnMsg.P.no(4).xxPopPrm;
        param[5] = scrnMsg.P.no(5).xxPopPrm;
        param[6] = scrnMsg.P.no(6).xxPopPrm;
        param[7] = scrnMsg.P.no(7).xxPopPrm;
        param[8] = scrnMsg.P.no(8).xxPopPrm;
        param[9] = scrnMsg.P.no(9).xxPopPrm;
        param[10] = scrnMsg.P.no(10).xxPopPrm;
        param[11] = scrnMsg.P.no(11).xxPopPrm; // Active Only
        param[12] = scrnMsg.P.no(12).xxPopPrm; // Bill To's Only
        param[13] = scrnMsg.P.no(13).xxPopPrm; // no used
        param[14] = scrnMsg.P.no(14).xxPopPrm; // no used
        param[15] = scrnMsg.P.no(15).xxPopPrm; // Bill To Location
        param[16] = scrnMsg.P.no(16).xxPopPrm; // no used
        param[17] = scrnMsg.P.no(17).xxPopPrm; // 2nd addr
        param[18] = scrnMsg.P.no(18).xxPopPrm; // 3rd addr
        param[19] = scrnMsg.P.no(19).xxPopPrm; // 4th addr
        param[20] = scrnMsg.P.no(20).xxPopPrm; // no used
        param[21] = scrnMsg.P.no(21).xxPopPrm; // no used
        param[22] = scrnMsg.P.no(22).xxPopPrm; // no used
        param[23] = scrnMsg.P.no(23).xxPopPrm; // no used
        param[24] = scrnMsg.P.no(24).xxPopPrm; // Display Hierarkey Active Flag
        param[25] = scrnMsg.P.no(25).xxPopPrm; // Account Number Active Flag
        param[26] = scrnMsg.P.no(26).xxPopPrm; // Status Active Flag

        return param;
    }

    /**
     * getShipPopPram
     * @param scrnMsg NFCL3000BMsg
     * @return Ship to Popup Prameters
     */
    public static Object[] getShipPopPram(NFCL3000BMsg scrnMsg) {
        scrnMsg.xxScrItem10Txt.setValue("ShipTo");

        scrnMsg.P.clear();

        // Bill To Only
        scrnMsg.P.no(12).xxPopPrm.setValue("03");
        // Active Only
        scrnMsg.P.no(11).xxPopPrm.setValue("01");

        scrnMsg.P.no(24).xxPopPrm.setValue(ZYPConstant.FLG_OFF_N);
        scrnMsg.P.no(25).xxPopPrm.setValue(ZYPConstant.FLG_ON_Y);
//        if (ZYPCommonFunc.hasValue(scrnMsg.shipToCustAcctCd_H2)) {
//            scrnMsg.P.no(25).xxPopPrm.setValue(ZYPConstant.FLG_OFF_N);
//        }
        scrnMsg.P.no(26).xxPopPrm.setValue(ZYPConstant.FLG_ON_Y);

        //NMAL6760
        Object[] param = new Object[27];
        param[0] = scrnMsg.shipToCustAcctCd_H2;
        param[1] = scrnMsg.shipToCustAcctNm_H2;
        param[2] = scrnMsg.locNum_H2;
        param[3] = scrnMsg.P.no(3).xxPopPrm;
        param[4] = scrnMsg.P.no(4).xxPopPrm;
        param[5] = scrnMsg.P.no(5).xxPopPrm;
        param[6] = scrnMsg.P.no(6).xxPopPrm;
        param[7] = scrnMsg.P.no(7).xxPopPrm;
        param[8] = scrnMsg.P.no(8).xxPopPrm;
        param[9] = scrnMsg.P.no(9).xxPopPrm;
        param[10] = scrnMsg.P.no(10).xxPopPrm;
        param[11] = scrnMsg.P.no(11).xxPopPrm; // Active Only
        param[12] = scrnMsg.P.no(12).xxPopPrm; // Ship To's Only
        param[13] = scrnMsg.P.no(13).xxPopPrm; // no used
        param[14] = scrnMsg.P.no(14).xxPopPrm; // no used
        param[15] = scrnMsg.P.no(15).xxPopPrm; // Ship To Location
        param[16] = scrnMsg.P.no(16).xxPopPrm; // no used
        param[17] = scrnMsg.P.no(17).xxPopPrm; // 2nd addr
        param[18] = scrnMsg.P.no(18).xxPopPrm; // 3rd addr
        param[19] = scrnMsg.P.no(19).xxPopPrm; // 4th addr
        param[20] = scrnMsg.P.no(20).xxPopPrm; // no used
        param[21] = scrnMsg.P.no(21).xxPopPrm; // no used
        param[22] = scrnMsg.P.no(22).xxPopPrm; // no used
        param[23] = scrnMsg.P.no(23).xxPopPrm; // no used
        param[24] = scrnMsg.P.no(24).xxPopPrm; // Display Hierarkey Active Flag
        param[25] = scrnMsg.P.no(25).xxPopPrm; // Account Number Active Flag
        param[26] = scrnMsg.P.no(26).xxPopPrm; // Status Active Flag

        return param;
    }
    // END   2016/09/16 S.Yoshida [QC#14089,ADD]

    // START 2016/09/21 S.Fujita [QC#14481,ADD]
    /**
     * setAcctDistError
     * @param bizMsg
     * @return
     */
    public static boolean checkAcctDistError(NFCL3000CMsg bizMsg) {
        boolean isNotError = true;
        for(int i = 0; i < bizMsg.C.getValidCount(); i++) {
            if(ZYPCommonFunc.hasValue(bizMsg.C.no(i).invErrMsgTxt_C1.getValue())) {
                isNotError = false;
            }
        }
        return isNotError;
    }
    // END   2016/09/21 S.Fujita [QC#14481,ADD]
    
    // START 2016/10/27 T.Murai [QC#13639, ADD]
    /**
     * isManualInvoice
     * @param scrnMsg
     * @return
     */
    public static boolean isManualInvoice(NFCL3000BMsg scrnMsg) {

        if(ZYPCommonFunc.hasValue(scrnMsg.invNum_H1.getValue())) {
            if(!ZYPCommonFunc.hasValue(scrnMsg.sysSrcCd_H1.getValue()) || scrnMsg.sysSrcCd_H1.getValue().equals(SYS_SRC.S21_ACCOUNTING_AR)) {
                return true;
            }
        } else {
            return true;
        }
        return false;
    }
    // END  2016/10/27 T.Murai [QC#13639, ADD]
    // START 2017/12/25 E.Kameishi [QC#20312,ADD]
    public static void taxAdjustmentProtect(NFCL3000BMsg scrnMsg){

        // START 2018/05/22 Y.Matsui [QC#21841,ADD]
        if(!scrnMsg.sysSrcCd_H1.getValue().equals(SYS_SRC.S21_ACCOUNTING_AR)) {
            return;
        }
        // END   2018/05/22 Y.Matsui [QC#21841,ADD]

        int idx = scrnMsg.xxCellIdx.getValueInt();

        if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.fnlzInvFlg_H4.getValue())) {
            // START 2018/02/07 E.Kameishi [QC#24014,MOD]
            if (idx >= 0 && ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).xxMstChkFlg_A1)) {
            // END 2018/02/07 E.Kameishi [QC#24014,MOD]
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(idx).xxMstChkFlg_A1.getValue())) {
                    scrnMsg.A.no(idx).setInputProtected(true);
                	// START 2018/02/23 E.Kameishi [QC#24390,MOD]
                    scrnMsg.A.no(idx).xxChkBox_A1.setInputProtected(false);
                	// END 2018/02/23 E.Kameishi [QC#24390,MOD]
                    scrnMsg.A.no(idx).invBolLineNum_A1.setInputProtected(false);
                    // START 2018/05/22 Y.Matsui [QC#21841,ADD]
                    scrnMsg.A.no(idx).invLineCatgCd_A1.setInputProtected(false);
                    // END   2018/05/22 Y.Matsui [QC#21841,ADD]
                    scrnMsg.A.no(idx).mdseCd_A1.setInputProtected(false);
                    scrnMsg.A.no(idx).mdseNm_A1.setInputProtected(false);
                    scrnMsg.A.no(idx).invLineDealTaxAmt_A1.setInputProtected(false);
                    scrnMsg.A.no(idx).manInvCratCmntTxt_A1.setInputProtected(false);
                    
                } else {
                    scrnMsg.A.no(idx).setInputProtected(false);
                    scrnMsg.A.no(idx).invLineDealNetAmt_A1.setInputProtected(true);
                    scrnMsg.A.no(idx).xxTotAmt_A1.setInputProtected(true);
                    scrnMsg.A.no(idx).shipDt_A1.setInputProtected(true);
                    scrnMsg.A.no(idx).shipFromInvtyLocCd_A1.setInputProtected(true);
                    scrnMsg.A.no(idx).dsCustTaxCd_A1.setInputProtected(true);
                    scrnMsg.A.no(idx).invErrMsgTxt_A1.setInputProtected(true);
                    scrnMsg.A.no(idx).invldValTxt_A1.setInputProtected(true);
                    scrnMsg.A.no(idx).invLineDealTaxAmt_A1.setInputProtected(true);
                    scrnMsg.A.no(idx).taxPct_A1.setInputProtected(true);
                    scrnMsg.A.no(idx).invLineNum_A1.setInputProtected(true);
                    scrnMsg.A.no(idx).svcConfigMstrPk_A1.setInputProtected(true);
                    scrnMsg.A.no(idx).csmpContrNum_A1.setInputProtected(true);
                    scrnMsg.A.no(idx).dlrRefNum_A1.setInputProtected(true);
                    scrnMsg.A.no(idx).funcCsmpCrAmt_A1.setInputProtected(true);
                    scrnMsg.A.no(idx).serNum_A1.setInputProtected(true);
                    scrnMsg.A.no(idx).cpoOrdNum_A1.setInputProtected(true);
                    scrnMsg.A.no(idx).xxDplyOrdLineNum_A1.setInputProtected(true);
                    scrnMsg.A.no(idx).ordDt_A1.setInputProtected(true);
                    // START 2019/04/17 S.Takami [QC#31204,ADD]
                    scrnMsg.A.no(idx).dsContrNum_A1.setInputProtected(true);
                    scrnMsg.A.no(idx).mdlNm_A1.setInputProtected(true);
                    // END 2019/04/17 S.Takami [QC#31204,ADD]
                    if (idx == 0 && isManualInvoice(scrnMsg)) {
                        scrnMsg.A.no(idx).ordDt_A1.setInputProtected(false);
                    }
                }
            }
        }
    }
    // END   2017/12/25 E.Kameishi [QC#20312,ADD]
    // START 2018/09/14 S.Ohki [QC#28089,ADD]
    /**
     * createSalesRepSearchSql
     * @param scrnMsg
     * @return
     */
    public static String createSalesRepSearchSql(NFCL3000BMsg scrnMsg) {
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
        sb.append("    AND (OFRT.SLS_REP_FLG      = '").append(ZYPConstant.FLG_ON_Y).append("'");
        sb.append("    OR OFRT.TECH_MSTR_REQ_FLG  = '").append(ZYPConstant.FLG_ON_Y).append("')");
        sb.append("    AND OFRT.EZCANCELFLAG     = '0'");
        sb.append("    AND OFRT.GLBL_CMPY_CD     = BA.GLBL_CMPY_CD");
        sb.append("    AND OFRT.FIRST_ORG_CD     = BA.BIZ_AREA_ORG_CD");
        sb.append("    AND (BA.SLS_FLG            = '").append(ZYPConstant.FLG_ON_Y).append("'");
        sb.append("    OR BA.SVC_FLG            = '").append(ZYPConstant.FLG_ON_Y).append("')");
        sb.append("    AND BA.ORG_STRU_TP_CD     = '").append(ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY).append("'");
        sb.append("    AND BA.EZCANCELFLAG       = '0'");
        sb.append("    AND T.GLBL_CMPY_CD        = OFA.GLBL_CMPY_CD");
        sb.append("    AND T.TOC_CD              = OFA.TOC_CD");
        sb.append("    AND OFA.EZCANCELFLAG      = '0'");
        sb.append("    AND T.GLBL_CMPY_CD        = SO.GLBL_CMPY_CD ");
        sb.append("    AND T.TOC_CD              = SO.TOC_CD");
        sb.append("    AND SO.RGTN_STS_CD        = '").append(RGTN_STS.READY_FOR_ORDER_TAKING).append("'");
        sb.append("    AND SO.EZCANCELFLAG        = '0'");
        sb.append("    AND OFA.GLBL_CMPY_CD      = SP.GLBL_CMPY_CD");
        sb.append("    AND OFA.PSN_CD            = SP.PSN_CD");
        sb.append("    AND SP.EFF_FROM_DT        <= '").append(scrnMsg.procDt.getValue()).append("'");
        sb.append("    AND (SP.EFF_THRU_DT       >= '").append(scrnMsg.procDt.getValue()).append("' OR SP.EFF_THRU_DT IS NULL)");
        sb.append("    AND SP.EZCANCELFLAG       = '0'");
        sb.append("    AND T.GLBL_CMPY_CD        = CB.GLBL_CMPY_CD (+)");
        sb.append("    AND T.COA_BR_CD           = CB.COA_BR_CD (+)");
        sb.append("    AND CB.EZCANCELFLAG (+)   = '0'");

        return sb.toString();
    }
    // END 2018/09/14 S.Ohki [QC#28089,ADD]
    // START 2018/10/24 S.Takami [QC#27069, Add]
    /**
     * Get Param NSAL1240 For Configuration ID
     * @param scrnMsg NFCL3000BMsg
     * @return Param NSAL1240
     */
    public static Object[] getParamNSAL1240(NFCL3000BMsg scrnMsg, NFCL3000_ABMsg lineAScrnMsg) {

        clearPopupParmNSAL1240(scrnMsg);

        List<Object> parameters = new ArrayList<Object>(IDX_31);

        // [0]: CONFIG_EXST_MODE_CD
        // Mod Start 2016/08/04 QC#9078
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_00, NSAL1240Constant.MODE_02);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_00, NSAL1240_MODE_02);
        // Mod End 2016/08/04 QC#9078
        parameters.add(scrnMsg.xxPopPrm_00);

        // [1]: SVC_CONFIG_MSTR_PK
        ZYPEZDItemValueSetter.setValue(scrnMsg.svcConfigMstrPk_Q, lineAScrnMsg.svcConfigMstrPk_A1);
        parameters.add(scrnMsg.svcConfigMstrPk_Q);

        // [2]: SER_NUM
        ZYPEZDItemValueSetter.setValue(scrnMsg.serNum_Q, lineAScrnMsg.serNum_A1);
        parameters.add(scrnMsg.serNum_Q);

        // [3]: SVC_MACH_MSTR_PK
        // ZYPEZDItemValueSetter.setValue(scrnMsg.svcMachMstrPk_Q, scrnMsg.svcMachMstrPk_Q);
        parameters.add(scrnMsg.svcMachMstrPk_Q);

        // [4]: MDSE_CD
        // ZYPEZDItemValueSetter.setValue(scrnMsg.mdseCd_Q, scrnMsg.mdseCd);
        parameters.add(scrnMsg.mdseCd_Q);

        // [5]: MDL_NM
        ZYPEZDItemValueSetter.setValue(scrnMsg.mdlNm_Q, lineAScrnMsg.mdlNm_A1);
        parameters.add(scrnMsg.mdlNm_Q);

        // [6]: SHIP_FEOM_DT
        // ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, scrnMsg.serNum);
        parameters.add(scrnMsg.xxPopPrm_01);

        // [7]: SHIP_THRU_DT
        // ZYPEZDItemValueSetter.setValue(scrnMsg.serNum_Q, scrnMsg.serNum);
        parameters.add(scrnMsg.xxPopPrm_02);

        // [8]: SVC_MACH_USG_STS_CD
        // ZYPEZDItemValueSetter.setValue(scrnMsg.serNum_Q, scrnMsg.serNum);
        parameters.add(scrnMsg.svcMachUsgStsCd_Q);

        // [9]: SVC_MACH_MSTR_STS_CD, Array
        parameters.add((EZDBMsgArray) scrnMsg.Z);

        // [10]: SVC_MACH_MSTR_STS_EDIT_FLG
        // ZYPEZDItemValueSetter.setValue(scrnMsg.serNum_Q, scrnMsg.serNum);
        parameters.add(scrnMsg.xxEdtModeFlg_Q1);

        // [11]: MACH_ALLOC_MODE_CODE
        // ZYPEZDItemValueSetter.setValue(scrnMsg.serNum_Q, scrnMsg.serNum);
        parameters.add(scrnMsg.xxPopPrm_03);

        // [12]: MAIN_UNIT_FLG
        // ZYPEZDItemValueSetter.setValue(scrnMsg.serNum_Q, scrnMsg.serNum);
        parameters.add(scrnMsg.xxEdtModeFlg_Q2);

        // [13]: STK_STS_CD
        // ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_04, scrnMsg.serNum);
        parameters.add(scrnMsg.xxPopPrm_04);

        // [14]: WH_CD
        // ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_05, scrnMsg.serNum);
        parameters.add(scrnMsg.xxPopPrm_05);

        // [15]: SUB_WH_CD
        // ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_06, scrnMsg.serNum);
        parameters.add(scrnMsg.xxPopPrm_06);

        // [16]: SVC_SLN_SQ
        // ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_07, scrnMsg.serNum);
        parameters.add(scrnMsg.xxPopPrm_07);

        // [17]: SVC_SLN_NM
        // ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_08, scrnMsg.serNum);
        parameters.add(scrnMsg.xxPopPrm_08);

        // [18]: CONTR_NUM
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_09, lineAScrnMsg.dsContrNum_A1);
        parameters.add(scrnMsg.xxPopPrm_09);

        // [19]: DS_OWNR_ACCT_NUM
        // ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_0A, scrnMsg.serNum);
        parameters.add(scrnMsg.xxPopPrm_0A);

        // [20]: OWNR_LOC_CD
        // ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_0B, scrnMsg.serNum);
        parameters.add(scrnMsg.xxPopPrm_0B);

        // [21]: DS_BILL_TO_ACCT_NUM
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_0C, scrnMsg.billToCustAcctCd_H3);
        parameters.add(scrnMsg.xxPopPrm_0C);

        // [22]: BILL_TO_LOC_CD
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_0D, scrnMsg.billToCustAcctCd_H3);
        parameters.add(scrnMsg.xxPopPrm_0D);

        // [23]: DS_CUR_LOC_ACCT_NUM
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_0E, scrnMsg.shipToCustAcctCd_H2);
        parameters.add(scrnMsg.xxPopPrm_0E);

        // [24]: CUR_LOC_NUM
        // ZYPEZDItemValueSetter.setValue(scrnMsg.curLocNum_Q, scrnMsg.serNum);
        parameters.add(scrnMsg.curLocNum_Q);

        // Output Parameter
        parameters.add(scrnMsg.mdlId_QO); // [25]: MDL_ID
        parameters.add(scrnMsg.mdlNm_QO); // [26]: MDL_NM
        parameters.add(scrnMsg.contrPk_QO); // [27]: CONTR_PK
        parameters.add(scrnMsg.contrNum_QO); // [28]: CONTR_NUM
        parameters.add(scrnMsg.svcConfigMstrPk_QO); // [29]: SVC_CONFIG_MSTR_PK
        parameters.add((EZDBMsgArray) scrnMsg.Y); // [30]:
        return parameters.toArray(new Object[parameters.size()]);
    }

    /**
     * Clear Pop param for NSAL1240
     * @param scrnMsg
     */
    private static void clearPopupParmNSAL1240(NFCL3000BMsg scrnMsg) {

        scrnMsg.xxPopPrm_00.clear();
        scrnMsg.xxPopPrm_01.clear();
        scrnMsg.xxPopPrm_02.clear();
        scrnMsg.xxPopPrm_03.clear();
        scrnMsg.xxPopPrm_04.clear();
        scrnMsg.xxPopPrm_05.clear();
        scrnMsg.xxPopPrm_06.clear();
        scrnMsg.xxPopPrm_07.clear();
        scrnMsg.xxPopPrm_08.clear();
        scrnMsg.xxPopPrm_09.clear();
        scrnMsg.xxPopPrm_0A.clear();
        scrnMsg.xxPopPrm_0B.clear();
        scrnMsg.xxPopPrm_0C.clear();
        scrnMsg.xxPopPrm_0D.clear();
        scrnMsg.xxPopPrm_0E.clear();
        scrnMsg.Y.clear();
//        scrnMsg.mdlId_Q.clear();
        scrnMsg.mdlNm_Q.clear();
        scrnMsg.serNum_Q.clear();
//        scrnMsg.contrPk_Q.clear();
//        scrnMsg.contrNum_Q.clear();
        scrnMsg.svcConfigMstrPk_Q.clear();
        scrnMsg.mdseCd_Q.clear();
        scrnMsg.curLocNum_Q.clear();
//        scrnMsg.locNm_Q.clear();
        scrnMsg.svcMachUsgStsCd_Q.clear();
        scrnMsg.svcMachMstrPk_Q.clear();
        scrnMsg.xxEdtModeFlg_Q1.clear();
        scrnMsg.xxEdtModeFlg_Q2.clear();
        scrnMsg.mdlId_QO.clear();
        scrnMsg.mdlNm_QO.clear();
        scrnMsg.contrPk_QO.clear();
        scrnMsg.contrNum_QO.clear();
        scrnMsg.svcConfigMstrPk_QO.clear();
        scrnMsg.Q.clear();
    }

    // END   2018/10/24 S.Takami [QC#27069, Add]

    // START 2019/04/25 S.Takami [QC#50078,ADD]
    /**
     * <pre>
     * set Qty elements as Mandatory or not.
     * @param scrnMsg Screen Message
     * </pre>
     */
    public static void setQtyIndispensable(NFCL3000BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            setQtyIndispensable(scrnMsg.A.no(i));
        }
    }

    private static void setQtyIndispensable(NFCL3000_ABMsg lineMsg) {

        String xxDplyCtrlFlg = lineMsg.xxDplyCtrlFlg_A1.getValue();
        if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, xxDplyCtrlFlg)) {
            lineMsg.invDplyQty_A1.setIndispensable(true);
            lineMsg.shipQty_A1.setIndispensable(false);
            lineMsg.ordCustUomQty_A1.setIndispensable(false);
        } else if (S21StringUtil.isEquals(ZYPConstant.FLG_OFF_N, xxDplyCtrlFlg)) {
            lineMsg.invDplyQty_A1.setIndispensable(false);
            lineMsg.shipQty_A1.setIndispensable(true);
            lineMsg.ordCustUomQty_A1.setIndispensable(false);
        } else {
            lineMsg.invDplyQty_A1.setIndispensable(false);
            lineMsg.shipQty_A1.setIndispensable(false);
            lineMsg.ordCustUomQty_A1.setIndispensable(true);
        }
        lineMsg.adjQtyDplyTxt_A1.setIndispensable(true);
    }

    private static boolean setTextQtyToBigDecimalQty(NFCL3000BMsg scrnMsg) {

        boolean rslt = true;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NFCL3000_ABMsg lineMsg = scrnMsg.A.no(i);
            String strQty = lineMsg.adjQtyDplyTxt_A1.getValue().replace(",", "");
            BigDecimal qty = null;
            EZDBBigDecimalItem item = null;
            // START 2019/10/03 S.Takami [QC#53881,ADD]
            int decimalDigits = 0;
            // END 2019/10/03 S.Takami [QC#53881,ADD]
            if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, lineMsg.xxDplyCtrlFlg_A1.getValue())) {
                item = lineMsg.invDplyQty_A1;
                // START 2019/10/03 S.Takami [QC#53881,ADD]
                decimalDigits = lineMsg.getAttribute("invDplyQty_A1").getFracDigit();
                // END 2019/10/03 S.Takami [QC#53881,ADD]
            } else if (S21StringUtil.isEquals(ZYPConstant.FLG_OFF_N, lineMsg.xxDplyCtrlFlg_A1.getValue())) {
                item = lineMsg.shipQty_A1;
                // START 2019/10/03 S.Takami [QC#53881,ADD]
                decimalDigits = lineMsg.getAttribute("shipQty_A1").getFracDigit();
                // END 2019/10/03 S.Takami [QC#53881,ADD]
            } else {
                item = lineMsg.ordCustUomQty_A1;
                // START 2019/10/03 S.Takami [QC#53881,ADD]
                decimalDigits = lineMsg.getAttribute("ordCustUomQty_A1").getFracDigit();
                // END 2019/10/03 S.Takami [QC#53881,ADD]
            }
            if (ZYPCommonFunc.hasValue(strQty)) {
                try {
                    qty = new BigDecimal(strQty);
                    // START 2019/10/03 S.Takami [QC#53881,DEL]
//                    item.setValue(qty);
                    // END 2019/10/03 S.Takami [QC#53881,DEL]
                } catch(Exception ex) {
                    String addStr = item.getNameForMessage();
                    lineMsg.adjQtyDplyTxt_A1.setErrorInfo(1, "ZZM9004E", new String[] {addStr});
                    rslt = false;
                }
                // START 2019/10/03 S.Takami [QC#53881,ADD]
                if (rslt) {
                    String[] devideDecimalArray = strQty.split("\\.");
                    if (devideDecimalArray.length > 1 && decimalDigits == 0) {
                        lineMsg.adjQtyDplyTxt_A1.setErrorInfo(1, "ZZM9004E", new String[] {item.getNameForMessage()});
                        rslt = false;
                    } else {
                        if (devideDecimalArray.length > 1 && devideDecimalArray[1].length() > decimalDigits) {
                            lineMsg.adjQtyDplyTxt_A1.setErrorInfo(1, "ZZM9015E", new String[] {item.getNameForMessage()});
                            rslt = false;
                        }
                    }
                }
                if (rslt) {
                    item.setValue(qty);
                }
                // END 2019/10/03 S.Takami [QC#53881,ADD]
            } else {
                item.clear();
            }
        }
        return rslt;
    }

    private static boolean setErrorInfoOfQty(NFCL3000BMsg scrnMsg) {

        boolean rslt = true;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NFCL3000_ABMsg lineMsg = scrnMsg.A.no(i);
            String itemName = null;
            EZDBBigDecimalItem item = null;
            if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, lineMsg.xxDplyCtrlFlg_A1.getValue())) {
                itemName = "invDplyQty_A1";
                item = lineMsg.invDplyQty_A1;
            } else if (S21StringUtil.isEquals(ZYPConstant.FLG_OFF_N, lineMsg.xxDplyCtrlFlg_A1.getValue())) {
                itemName = "shipQty_A1";
                item = lineMsg.shipQty_A1;
            } else {
                itemName = "ordCustUomQty_A1";
                item = lineMsg.ordCustUomQty_A1;
            }
            if (item.isError()) {
                EZDMessageInfo ezdMsgInfo = lineMsg.getErrorInfo(itemName);
                lineMsg.adjQtyDplyTxt_A1.setErrorInfo(1, ezdMsgInfo.getCode(), ezdMsgInfo.getParameter());
                rslt = false;
            }
        }
        return rslt;
    }
    // END 2019/04/25 S.Takami [QC#50078,ADD]
}
