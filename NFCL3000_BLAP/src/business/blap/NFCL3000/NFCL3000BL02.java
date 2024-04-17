package business.blap.NFCL3000;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCStringItemArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFCL3000.common.NFCL3000CommonLogic;
import business.blap.NFCL3000.constant.NFCL3000Constant;
import business.db.CCYTMsg;
import business.db.DFRD_ACCTG_RULETMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.SYS_SRCTMsg;
import business.file.NFCL3000F00FMsg;
import business.file.NFCL3000F01FMsg;
import business.file.NFCL3000F02FMsg;
import business.file.NFCL3000F03FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AJE_INV_ACCT_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SLS_TAX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/12/21   Fujitsu         T.Tanaka        Create          N/A
 * 2016/12/21   Fujitsu         T.Tanaka        Update          Def#6225
 * 2016/05/03   CSAI            K.Uramori       Update          QC#7862
 * 2016/05/24   Fujitsu         S.Fujita        Update          QC#8522
 * 2016/06/03   Fujitsu         S.Fujita        Update          QC#9157
 * 2016/06/03   Fujitsu         S.Fujita        Update          QC#9452
 * 2016/06/16   Fujitsu         S.Fujita        Update          QC#10176
 * 2016/06/20   Fujitsu         S.Fujita        Update          QC#9454
 * 2016/07/04   Fujitsu         S.Fujita        Update          QC#10742
 * 2016/07/05   Fujitsu         S.Fujita        Update          QC#10990
 * 2016/07/06   Fujitsu         S.Fujita        Update          QC#10870
 * 2016/07/14   Fujitsu         S.Fujita        Update          QC#11157
 * 2016/07/20   Fujitsu         S.Fujita        Update          QC#10148
 * 2016/08/22   Fujitsu         S.Fujita        Update          QC#12579
 * 2016/09/06   Fujitsu         S.Fujita        Update          QC#12656
 * 2016/09/06   Fujitsu         S.Fujita        Update          QC#14111
 * 2016/09/08   Fujitsu         S.Fujita        Update          QC#14115
 * 2016/09/12   Fujitsu         S.Fujita        Update          QC#14112
 * 2016/09/15   Fujitsu         S.Yoshida       Update          QC#13956
 * 2016/09/20   Fujitsu         S.Fujita        Update          QC#13795
 * 2016/09/21   Fujitsu         S.Fujita        Update          QC#14481
 * 2016/09/26   Fujitsu         S.Fujita        Update          QC#13362
 * 2016/10/12   Fujitsu         S.Fujita        Update          QC#13659
 * 2016/10/24   Fujitsu         T.Murai         Update          QC#13656,13639
 * 2017/03/17   Fujitsu         T.Murai         Update          QC#14205
 * 2017/12/25   Hitachi         E.Kameishi      Update          QC#20312
 * 2018/03/29   Hitachi         E.Kameishi      Update          QC#24390
 * 2018/05/22   Fujitsu         Y.Matsui        Update          QC#21841
 * 2018/07/17   Hitachi         E.Kameishi      Update          QC#27007
 * 2018/07/25   Fujitsu         S.Ohki          Update          QC#26968
 * 2018/08/13   Fujitsu         Y.Matsui        Update          QC#26968
 * 2018/08/15   Fujitsu         Y.Matsui        Update          QC#27651
 * 2018/08/20   Fujitsu         S.Ohki          Update          QC#24835
 * 2018/09/28   Fujitsu         T.Ogura         Update          QC#28526
 * 2018/10/24   Fujitsu         S.Takami        Update          QC#27069
 * 2019/04/11   Fujitsu         S.Takami        Update          QC#31165
 * 2019/04/15   Fujitsu         S.Takami        Update          QC#31191
 * 2019/04/25   Fujitsu         S.Takami        Update          QC#50078
 * 2019/05/31   Fujitsu         S.Takami        Update          QC#50633
 * 2019/06/05   Fujitsu         S.Takami        Update          QC#50683
 * 2021/01/04   CITS            R.Kurahashi     Update          QC#56282
 *</pre>
 */
public class NFCL3000BL02 extends S21BusinessHandler implements NFCL3000Constant {
    
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();
            // START 2018/07/25 S.Ohki [QC#26968,ADD]
            String box = "";
            // END   2018/07/25 S.Ohki [QC#26968,ADD]

            // START 2018/09/28 T.Ogura [QC#28526,ADD]
            NFCL3000CommonLogic.setEachQtyToShipQty(cMsg);
            // END   2018/09/28 T.Ogura [QC#28526,ADD]

            if ("NFCL3000_INIT".equals(screenAplID)) {
                doProcess_NFCL3000_INIT(cMsg, sMsg);
                // START 2018/07/25 S.Ohki [QC#26968,MOD]
//                ZYPGUITableColumn.getColData((NFCL3000CMsg) cMsg, (NFCL3000SMsg) sMsg, "parentBoxA");
                box = "AHEAD";
                // END   2018/07/25 S.Ohki [QC#26968,MOD]
            } else if ("NFCL3000Scrn00_Search".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_Search(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_Select_PmtTerm".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_Select_PmtTerm(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_Select_InvoiceType".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_Select_InvoiceType(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_Select_DsInvoiceType".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_Select_DsInvoiceType(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_AddLine_A".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_AddLine_A(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_DeleteLine_A".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_DeleteLine_A(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_SelectCheckBox_A".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_SelectCheckBox_A(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_SelectCheckBox_AA".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_SelectCheckBox_AA(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_AddLine_B".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_AddLine_B(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_DeleteLine_B".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_DeleteLine_B(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_SelectCheckBox_B".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_SelectCheckBox_B(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_SelectCheckBox_BA".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_SelectCheckBox_BA(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_AddLine_C".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_AddLine_C(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_DeleteLine_C".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_DeleteLine_C(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_SelectCheckBox_C".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_SelectCheckBox_C(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_AddLine_D".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_AddLine_D(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_DeleteLine_D".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_DeleteLine_D(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_SelectCheckBox_D".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_SelectCheckBox_D(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_SelectCheckBox_DA".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_SelectCheckBox_DA(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_ItemName_A".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_ItemName_A(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_Select_AccountingClass".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_Select_AccountingClass(cMsg, sMsg);
            } else if ("NFCL3000_NMAL6760".equals(screenAplID)) {
                doProcess_NFCL3000_NWAL6760(cMsg, sMsg);
            } else if ("NFCL3000_NMAL2550".equals(screenAplID)) {
                doProcess_NFCL3000_NMAL2550(cMsg, sMsg);
            } else if ("NFCL3000_NMAL6770".equals(screenAplID)) {
                doProcess_NFCL3000_NMAL6770(cMsg, sMsg);
            // START 2017/12/25 E.Kameishi [QC#20312,ADD]
            } else if ("NFCL3000_NMAL6800".equals(screenAplID)) {
                doProcess_NFCL3000_NMAL6800(cMsg, sMsg);
            // END   2017/12/25 E.Kameishi [QC#20312,ADD]
            }else if ("NFCL3000_NWAL1130".equals(screenAplID)) {
                doProcess_NFCL3000_NWAL1130(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_TAB_Line".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_TAB_Line(cMsg, sMsg);
                // START 2018/07/25 S.Ohki [QC#26968,MOD]
//                ZYPGUITableColumn.getColData((NFCL3000CMsg) cMsg, (NFCL3000SMsg) sMsg, "parentBoxA");
                box = "AHEAD";
                // END   2018/07/25 S.Ohki [QC#26968,MOD]
            } else if ("NFCL3000Scrn00_TAB_SalesCredit".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_TAB_SalesCredit(cMsg, sMsg);
                // START 2018/07/25 S.Ohki [QC#26968,MOD]
//                ZYPGUITableColumn.getColData((NFCL3000CMsg) cMsg, (NFCL3000SMsg) sMsg, "parentBoxB");
                box = "BHEAD";
                // END   2018/07/25 S.Ohki [QC#26968,MOD]
            } else if ("NFCL3000Scrn00_TAB_Accounting".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_TAB_Accounting(cMsg, sMsg);
                // START 2018/07/25 S.Ohki [QC#26968,MOD]
//                ZYPGUITableColumn.getColData((NFCL3000CMsg) cMsg, (NFCL3000SMsg) sMsg, "parentBoxC");
                box = "CHEAD";
                // END   2018/07/25 S.Ohki [QC#26968,MOD]
            } else if ("NFCL3000Scrn00_TAB_BOL".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_TAB_BOL(cMsg, sMsg);
                // START 2018/07/25 S.Ohki [QC#26968,MOD]
//                ZYPGUITableColumn.getColData((NFCL3000CMsg) cMsg, (NFCL3000SMsg) sMsg, "parentBoxD");
                box = "DHEAD";
                // END   2018/07/25 S.Ohki [QC#26968,MOD]
            } else if ("NFCL3000Scrn00_TAB_MoreInfo".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_TAB_MoreInfo(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_CMN_Submit(cMsg, sMsg);
                // START 2018/07/25 S.Ohki [QC#26968,MOD]
//                ZYPGUITableColumn.getColData((NFCL3000CMsg) cMsg, (NFCL3000SMsg) sMsg, "parentBoxA");
                box = "CHEAD";
                // END   2018/07/25 S.Ohki [QC#26968,MOD]
            } else if ("NFCL3000Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_CMN_Clear(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_CMN_Reset(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_Calc".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_Calc(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_Select_DebitCreditType".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_Select_DebitCreditType(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_CMN_Download(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_OnBlur_SalesRep".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_OnBlur_SalesRep(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_OnBlur_UnitPrice_A".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_OnBlur_UnitPrice_A(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_SalesRepName".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_SalesRepName(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_SalesRepName_B".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_SalesRepName_B(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_WarehouseName".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_WarehouseName(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_Select_InvLineNum_B".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_Select_InvLineNum_B(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_Select_InvBolLineNum_A".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_Select_InvBolLineNum_A(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_Select_InvBolLineNum_B".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_Select_InvBolLineNum_B(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_AdjustInvoice".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_AdjustInvoice(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_Installments".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_Installments(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_MaterDetailes".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_MaterDetailes(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_Activity".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_Activity(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_CreditRebill".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_CreditRebill(cMsg, sMsg);
            // START 2017/12/25 E.Kameishi [QC#20312,ADD]
            } else if ("NFCL3000Scrn00_PrintedInvoice".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_PrintedInvoice(cMsg, sMsg);
            // END   2017/12/25 E.Kameishi [QC#20312,ADD]
            } else if ("NFCL3000Scrn00_CreditCard".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_CreditCard(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_Select_PaymentMethod".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_Select_PaymentMethod(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_OnChangeRadio_C".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_OnChangeRadio_C(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_Select_SalesCreditLineNum_C".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_Select_SalesCreditLineNum_C(cMsg, sMsg);
            // START 2016/05/24 S.Fujita [QC#8522,ADD]
            } else if ("NFCL3000Scrn00_Select_DfrdAcctgRule_B".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_Select_DfrdAcctgRule_B(cMsg, sMsg);
            // END   2016/05/24 S.Fujita [QC#8522,ADD]
            } else if ("NFCL3000Scrn00_OnBlur_invLineNum_B".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_OnBlur_invLineNum_B(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_OnBlur_SalesCreditLineNum_C".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_OnBlur_SalesCreditLineNum_C(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_GlCombnSearch_C".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_GlCombnSearch_C(cMsg, sMsg);
            // START 2016/07/28 S.Fujita [QC#10148,ADD]
            } else if ("NFCL3000Scrn00_ShipToCustName".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_ShipToCustName(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_ShipToName_D".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_ShipToName_D(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_BillToCustName".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_BillToCustName(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_BillToLocName".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_BillToLocName(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_ShipToLocName".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_ShipToLocName(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_ShipToLocName_D".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_ShipToLocName_D(cMsg, sMsg);
            } else if ("NFCL3000Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_TBLColumnSort(cMsg, sMsg);
            // END   2016/07/28 S.Fujita [QC#10148,ADD]
            // START 2018/07/25 S.Ohki [QC#26968,ADD]
            } else if ("NFCL3000Scrn00_CMN_ColClear".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_CMN_ColClear(cMsg, sMsg);
                return;
            } else if ("NFCL3000Scrn00_CMN_ColSave".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_CMN_ColSave(cMsg, sMsg);
                return;
            // END   2018/07/25 S.Ohki [QC#26968,ADD]
            // START 2018/10/24 S.Takami [QC#27069, Add]
            } else if ("NFCL3000_NSAL1240".equals(screenAplID)) {
                doProcess_NFCL3000_NSAL1240(cMsg, sMsg);
            // END   2018/10/24 S.Takami [QC#27069, Add]
            // START 2019/04/25 S.Takami [QC#50078,ADD]
            } else if ("NFCL3000Scrn00_Select_PackageUom".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_Select_PackageUom((NFCL3000CMsg) cMsg, (NFCL3000SMsg) sMsg);
            // END 2019/04/25 S.Takami [QC#50078,ADD]
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

            // START 2018/07/25 S.Ohki [QC#26968,MOD]
            // START 2016/10/07 S.Yoshida [QC#14575,ADD]
//            NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;
//            String box = "parentBoxA";
//            if (TAB_SalesCredit.equals(bizMsg.xxDplyTab.getValue())) {
//                box = "parentBoxB";
//            } else if (TAB_Accounting.equals(bizMsg.xxDplyTab.getValue())) {
//                box = "parentBoxC";
//            } else if (TAB_BOL.equals(bizMsg.xxDplyTab.getValue())) {
//                box = "parentBoxD";
//            }
//            ZYPGUITableColumn.getColData((NFCL3000CMsg) cMsg, (NFCL3000SMsg) sMsg);
//            // END   2016/10/07 S.Yoshida [QC#14575,ADD]
            if (ZYPCommonFunc.hasValue(box)) {
            	ZYPGUITableColumn.getColData((NFCL3000CMsg) cMsg, (NFCL3000SMsg) sMsg, box);
            }
            // END   2018/07/25 S.Ohki [QC#26968,MOD]

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * doProcess_NFCL3000_INIT
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;
        NFCL3000SMsg globalMsg = (NFCL3000SMsg) sMsg;

        // START 2016/08/01 S.Fujita [QC#10148,ADD]
//        if (bizMsg.xxDplyTab_H1.getValue().equals(DIS_PAT_VISIBILITY_ERROR)) {
//            ZYPGUITableColumn.getColData(bizMsg, globalMsg, "A1HEAD");
//        } else {
//            ZYPGUITableColumn.getColData(bizMsg, globalMsg, "A2HEAD");
//        }
        // END   2016/08/01 S.Fujita [QC#10148,ADD]

        bizMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        bizMsg.procDt.setValue(ZYPDateUtil.getSalesDate(bizMsg.glblCmpyCd.getValue()));

        NFCL3000CommonLogic.getArAcctDt(bizMsg);

        // Global Company Code
        GLBL_CMPYTMsg glblCmpyTMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(glblCmpyTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        glblCmpyTMsg = (GLBL_CMPYTMsg) EZDTBLAccessor.findByKey(glblCmpyTMsg);
        if (glblCmpyTMsg!=null) {
            ZYPEZDItemValueSetter.setValue(bizMsg.stdCcyCd, glblCmpyTMsg.stdCcyCd.getValue());
        }

        // Standard Currency Code
        CCYTMsg ccyTMsg = new CCYTMsg();
        ZYPEZDItemValueSetter.setValue(ccyTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(ccyTMsg.ccyCd, bizMsg.stdCcyCd.getValue());
        ccyTMsg = (CCYTMsg) EZDTBLAccessor.findByKey(ccyTMsg);
        if(ccyTMsg!=null) {
            ZYPEZDItemValueSetter.setValue(bizMsg.aftDeclPntDigitNum, ccyTMsg.aftDeclPntDigitNum.getValue());
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.aftDeclPntDigitNum, new BigDecimal(2));
        }

        // VAR_CHAR_CONST DEFAULT_TAX_TRX_TP
        String taxTrxTp = ZYPCodeDataUtil.getVarCharConstValue("DEFAULT_TAX_TRX_TP", bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.svcAllocTrxTpNm, taxTrxTp);

        // Create Pulldown List Header
        NFCL3000CommonLogic.createPulldownListInvTp(bizMsg);
        // START 2016/07/05 S.Fujita [QC#10990,ADD]
        if (ZYPCommonFunc.hasValue(bizMsg.invNum_H1.getValue())) {
            NFCL3000CommonLogic.getInvTpCd(bizMsg);
        }
        // END   2016/07/05 S.Fujita [QC#10990,ADD]
        NFCL3000CommonLogic.createPulldownListDsInvTp(bizMsg);

        NFCL3000CommonLogic.createPulldownListPmtTermCashDisc(bizMsg);

        NFCL3000CommonLogic.createPulldownListDfrdInvRule(bizMsg);

        // Create Pulldown List TAB
        NFCL3000CommonLogic.createPulldownListBolLineNum(bizMsg);
        // START 2018/08/15 Y.Matsui [QC#27651,DEL]
//        NFCL3000CommonLogic.createPulldownListPkgUom(bizMsg);
        // END   2018/08/15 Y.Matsui [QC#27651,DEL]
        NFCL3000CommonLogic.createPulldownListDfrdAcctgRule(bizMsg);
        NFCL3000CommonLogic.createPulldownListInvLineSplTp(bizMsg);
        NFCL3000CommonLogic.createPulldownListShpgSvcLvl(bizMsg);
        NFCL3000CommonLogic.createPulldownListAjeInvAcctCls(bizMsg);
        // START 2016/09/16 S.Yoshida [QC#13956,DEL]
//        NFCL3000CommonLogic.createPulldownListDsPmtMeth(bizMsg);
        // END   2016/09/16 S.Yoshida [QC#13956,DEL]
        NFCL3000CommonLogic.createPulldownListDrCrTp(bizMsg);
        NFCL3000CommonLogic.createPulldownListinvPrintSts(bizMsg);
        // START 2016/08/01 S.Fujita [QC#10148,ADD]
        NFCL3000CommonLogic.createPulldownListChrgTp(bizMsg);
        NFCL3000CommonLogic.createPulldownListCrDrRsn(bizMsg);
        // END   2016/08/01 S.Fujita [QC#10148,ADD]
        // START 2018/05/22 Y.Matsui [QC#21841,ADD]
        NFCL3000CommonLogic.createPulldownListInvLineCatg(bizMsg);
        // END   2018/05/22 Y.Matsui [QC#21841,ADD]

        // More Info TAB
        NFCL3000CommonLogic.createPulldownListInvProcTp(bizMsg);
        NFCL3000CommonLogic.createPulldownListArCashApplySys(bizMsg);
        NFCL3000CommonLogic.createPulldownListDsContrCatg(bizMsg);
        // START 2016/08/01 S.Fujita [QC#10148,ADD]
//        NFCL3000CommonLogic.createPulldownListDsOrdTp(bizMsg);
        NFCL3000CommonLogic.createPulldownListDsOrdCatg(bizMsg);
        // END   2016/08/01 S.Fujita [QC#10148,ADD]
        // START 2017/12/25 E.Kameishi [QC#20312,ADD]
        NFCL3000CommonLogic.createPulldownListinvPrintCratSts(bizMsg);
        // END   2017/12/25 E.Kameishi [QC#20312,ADD]
        bizMsg.xxRadioBtn_C.setValue(ACCT_DIST_SMRY);
        bizMsg.xxRadioBtn_CB.setValue(ACCT_DIST_SMRY);
        bizMsg.xxPgFlg_C.clear();

        // Initial Search
        if(ZYPCommonFunc.hasValue(bizMsg.invNum_H1.getValue())) {
            doProcess_NFCL3000Scrn00_Search(cMsg, sMsg);
            // START 2017/12/25 E.Kameishi [QC#20312,ADD]
            NFCL3000CommonLogic.setUnitPrice(bizMsg);
            // END 2017/12/25 E.Kameishi [QC#20312,ADD]
        } else {
            bizMsg.invDt_H1.setValue(bizMsg.acctDt.getValue());
            bizMsg.xxNum_AL.setValue(BigDecimal.ONE);

            bizMsg.sysSrcCd_H1.setValue(SYS_SRC.S21_ACCOUNTING_AR);
            // START 2016/07/05 S.Fujita [QC#9992,ADD]
            // Get System Source Description
            SYS_SRCTMsg sysSrcTMsg = new SYS_SRCTMsg();
            ZYPEZDItemValueSetter.setValue(sysSrcTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(sysSrcTMsg.sysSrcCd, bizMsg.sysSrcCd_H1.getValue());
            sysSrcTMsg = (SYS_SRCTMsg) EZDTBLAccessor.findByKey(sysSrcTMsg);
            if (sysSrcTMsg!=null) {
                ZYPEZDItemValueSetter.setValue(bizMsg.sysSrcDescTxt_H1, sysSrcTMsg.sysSrcDescTxt.getValue());
            }
            // END   2016/07/05 S.Fujita [QC#9992,ADD]
            NFCL3000CommonLogic.calcNetDueDate(bizMsg);

            // START 2016/10/24 T.Murai [QC#13656, ADD]
            bizMsg.xxBtnFlg.setValue(ZYPConstant.FLG_OFF_N);
            // END   2016/10/24 T.Murai [QC#13656, ADD]

        }
        // After search, DS_INV_TP_CD is set
        NFCL3000CommonLogic.getDsInvTpInfo(bizMsg);
        bizMsg.crCardTrxTpCd_E1.setValue(CR_CARD_TRX_TP.INVOICE);

        // Multiple BOL
        bizMsg.xxChkBox_H2.clear();
        if(bizMsg.D.getValidCount() > 1) {
            bizMsg.xxChkBox_H2.setValue(ZYPConstant.FLG_ON_Y);
        }

        globalMsg.E.clear();
        globalMsg.E.setValidCount(0);
    }

    /**
     * doProcess_NFCL3000Scrn00_Search
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_Search(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;
        NFCL3000SMsg globalMsg = (NFCL3000SMsg) sMsg;

        if(!NFCL3000CommonLogic.searchInv(bizMsg)) {
            return;
        }
        if(!NFCL3000CommonLogic.searchInvLine(bizMsg)) {
            return;
        }
        if(!NFCL3000CommonLogic.searchInvSlsCr(bizMsg)) {
            return;
        }
        if(!NFCL3000CommonLogic.searchInvAcctDist(bizMsg, globalMsg)) {
            return;
        }
        if(!NFCL3000CommonLogic.searchInvBOL(bizMsg)) {
            return;
        }

        // START 2016/09/06 S.Fujita [QC#14111,ADD]
        NFCL3000CommonLogic.convertTimeToDisplay(bizMsg);
        // END   2016/09/06 S.Fujita [QC#14111,ADD]

        NFCL3000CommonLogic.setAddress(bizMsg);
        NFCL3000CommonLogic.setCheckBox(bizMsg);

        // START 2016/09/20 S.Fujita [QC#13795,DEL]
//        NFCL3000CommonLogic.calcAmount_InvLine(bizMsg);
        // END   2016/09/20 S.Fujita [QC#13795,DEL]

        NFCL3000CommonLogic.setDetailLineNumber(bizMsg);

        if(!NFCL3000CommonLogic.setAcctDistError(bizMsg)) {
            bizMsg.xxDplyTab.setValue(TAB_Accounting);
            bizMsg.xxRadioBtn_C.setValue(ACCT_DIST_EDIT);
            bizMsg.xxRadioBtn_CB.setValue(ACCT_DIST_EDIT);
         // START 2016/07/20 S.Fujita [QC#10148,MOD]
//          NFCL3000CommonLogic.setEditMode(bizMsg, globalMsg);
            NFCL3000CommonLogic.setAcctEditMode(bizMsg, globalMsg);
          // END   2016/07/20 S.Fujita [QC#10148,MOD]

            // START 2016/09/21 S.Fujita [QC#14481,DEL]
//            NFCL3000CommonLogic.check9Segment(bizMsg);
            // END   2016/09/21 S.Fujita [QC#14481,DEL]
        // START 2018/08/20 S.Ohki [QC#24835,ADD]
        } else {
        	if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_H3.getValue())) {
        		NFCL3000CommonLogic.setAcctEditMode(bizMsg, globalMsg);
        	}
        // END 2018/08/20 S.Ohki [QC#24835,ADD]
        }

        // START 2016/10/24 T.Murai [QC#13656, ADD]
        if (NFCL3000CommonLogic.hasTrxBal(bizMsg)) {
            bizMsg.xxBtnFlg.setValue(ZYPConstant.FLG_ON_Y);
        } else {
            bizMsg.xxBtnFlg.setValue(ZYPConstant.FLG_OFF_N);
        }
        // END   2016/10/24 T.Murai [QC#13656, ADD]

        // START 2017/03/17 T.Murai [QC#14205,ADD]
        NFCL3000CommonLogic.convertForDisplay(bizMsg);
        // END   2017/03/17 T.Murai [QC#14205,ADD]

        // START 2017/12/25 E.Kameishi [QC#20312,ADD]
        NFCL3000CommonLogic.setPercentageSplit(bizMsg);
        // END 2017/12/25 E.Kameishi [QC#20312,ADD]

        // START 2018/03/29 E.Kameishi [QC#24390,ADD]
        NFCL3000CommonLogic.setTaxAdjustmentItem(bizMsg);
        if (INV_TP.CREDIT_MEMO.equals(bizMsg.invTpCd_H1.getValue())) {
            for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).xxMstChkFlg_A1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).invLineDealTaxAmt_A1, bizMsg.A.no(i).invLineDealTaxAmt_A1.getValue().negate());
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxTotAmt_A1, bizMsg.A.no(i).invLineDealTaxAmt_A1.getValue().multiply(bizMsg.A.no(i).shipQty_A1.getValue()));
                }
            }
        }
        // END 2018/03/29 E.Kameishi [QC#24390,ADD]

        // START 2018/08/15 Y.Matsui [QC#27651,ADD]
        NFCL3000CommonLogic.createPulldownListPkgUom(bizMsg);
        // END   2018/08/15 Y.Matsui [QC#27651,ADD]
        // START 2019/06/05 S.Takami [QC#50683,ADD]
        resetFsrCommonData(bizMsg);
        // END 2019/06/05 S.Takami [QC#50683,ADD]
        globalMsg.E.clear();
        globalMsg.E.setValidCount(0);
    }

    /**
     * doProcess_NFCL3000Scrn00_Select_PmtTerm
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_Select_PmtTerm(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;

        NFCL3000CommonLogic.calcNetDueDate(bizMsg);
    }

    /**
     * doProcess_NFCL3000Scrn00_Select_InvoiceType
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_Select_InvoiceType(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;

        NFCL3000CommonLogic.createPulldownListDsInvTp(bizMsg);

        // START 2019/05/16 S.Takami [QC#50374,ADD]
        bizMsg.dsInvTpCd_H1.clear();
        // END 2019/05/16 S.Takami [QC#50374,ADD]

        NFCL3000CommonLogic.getDsInvTpInfo(bizMsg);
        // START 2016/05/24 S.Fujita [QC#8522,ADD]
        NFCL3000CommonLogic.createPulldownListDfrdAcctgRule(bizMsg);
        // END   2016/05/24 S.Fujita [QC#8522,ADD]
        // START 2016/06/16 S.Fujita [QC#10176,ADD]
        NFCL3000CommonLogic.createPulldownListPmtTermCashDisc(bizMsg);
        // END   2016/06/16 S.Fujita [QC#10176,ADD]

        // START 2016/07/06 S.Fujita [QC#10870,ADD]
        NFCL3000CommonLogic.calcNetDueDate(bizMsg);
        // END   2016/07/06 S.Fujita [QC#10870,ADD]

        // START 2019/05/16 S.Takami [QC#50374,ADD]
        resetPullDownListPkgUom(bizMsg);
        // END 2019/05/16 S.Takami [QC#50374,ADD]
    }

    /**
     * doProcess_NFCL3000Scrn00_Select_DsInvoiceType
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_Select_DsInvoiceType(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;
        NFCL3000CommonLogic.getDsInvTpInfo(bizMsg);

        // START 2019/05/16 S.Takami [QC#50374,ADD]
        resetPullDownListPkgUom(bizMsg);
        // END 2019/05/16 S.Takami [QC#50374,ADD]
        // START 2019/06/05 S.Takami [QC#50683,ADD]
        resetFsrCommonData(bizMsg);
        // END 2019/06/05 S.Takami [QC#50683,ADD]
    }

    /**
     * doProcess_NFCL3000_NWAL6760
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000_NWAL6760(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;

        int idx = bizMsg.xxCellIdx.getValueInt();

        if(bizMsg.xxScrItem10Txt.getValue().equals("ShipTo")) {
            NFCL3000CommonLogic.searchShipToAddr(bizMsg);
            NFCL3000CommonLogic.setAddress(bizMsg);

            if(!ZYPCommonFunc.hasValue(bizMsg.billToCustAcctCd_H3.getValue())) {
                if(ZYPCommonFunc.hasValue(bizMsg.P.no(15).xxPopPrm.getValue())) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctCd_H3, bizMsg.shipToCustAcctCd_H2.getValue());
                    ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctNm_H3, bizMsg.shipToCustAcctNm_H2.getValue());
                    ZYPEZDItemValueSetter.setValue(bizMsg.locNum_H3, bizMsg.locNum_H2.getValue());
                    ZYPEZDItemValueSetter.setValue(bizMsg.billToCustCd_H3, bizMsg.P.no(15).xxPopPrm.getValue());
                    ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustCd_H3, bizMsg.billToCustAcctCd_H3.getValue());
                    NFCL3000CommonLogic.searchBillToAddr(bizMsg);
                    NFCL3000CommonLogic.setAddress(bizMsg);
                    // START 2018/07/19 E.Kameishi [QC#27007,ADD]
                    NFCL3000CommonLogic.setPmtTerm(bizMsg);
                    // END 2018/07/19 E.Kameishi [QC#27007,ADD]
                }
            }
        } else if(bizMsg.xxScrItem10Txt.getValue().equals("BillTo")) {
            NFCL3000CommonLogic.searchBillToAddr(bizMsg);
            NFCL3000CommonLogic.setAddress(bizMsg);
            // START 2018/07/19 E.Kameishi [QC#27007,ADD]
            NFCL3000CommonLogic.setPmtTerm(bizMsg);
            // END 2018/07/19 E.Kameishi [QC#27007,ADD]
            bizMsg.sellToCustCd_H3.setValue(bizMsg.billToCustAcctCd_H3.getValue());
        } else if(bizMsg.xxScrItem10Txt.getValue().equals("ShipTo_D")) {
            NFCL3000CommonLogic.searchShipToAddr_D(bizMsg, idx);
        }
    }

    /**
     * doProcess_NFCL3000Scrn00_AddLine_A
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_AddLine_A(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;

        // START 2021/01/04 R.Kurahashi [QC#56282, ADD]
        if (!NFCL3000CommonLogic.checkContract(bizMsg)) {
            return;
        }
        // END 2021/01/04 R.Kurahashi [QC#56282, ADD]
        NFCL3000CommonLogic.calcNetDueDate(bizMsg);

        if(!NFCL3000CommonLogic.checkHeader(bizMsg)) {
            return;
        }

        if(bizMsg.D.getValidCount() < 1) {
            bizMsg.invBolLineNum_AC.no(0).setValue(INIT_INV_BOL_LINE_NUM);
            bizMsg.invBolLineNum_AD.no(0).setValue(INIT_INV_BOL_LINE_NUM);
            // START 2016/06/03 S.Fujita [QC#9157,ADD]
            NFCL3000CommonLogic.initialSetup_BOLTab(bizMsg);
            // END   2016/06/03 S.Fujita [QC#9157,ADD]
        }

        int count = bizMsg.A.getValidCount();
        int addCount = 0;
        if(ZYPCommonFunc.hasValue(bizMsg.xxNum_A.getValue())) {
            addCount = bizMsg.xxNum_A.getValueInt();
        } else {
            addCount = 1;
        }

        if(bizMsg.A.length() < (count + addCount)) {
            bizMsg.setMessageInfo("NFCM0110E", null);
            return;
        }
        bizMsg.A.setValidCount( count + addCount);

        // Add Line Initial setup
        for(int i = count; i < (count + addCount); i++) {
            bizMsg.A.no(i).xxChkBox_A1.setValue(ZYPConstant.FLG_OFF_N);
            bizMsg.A.no(i).invBolLineNum_A1.setValue(INIT_INV_BOL_LINE_NUM);

            // START 2018/02/23 E.Kameishi [QC#24390,MOD]
            if (INV_TP.CREDIT_MEMO.equals(bizMsg.invTpCd_H1.getValue())) {
                bizMsg.A.no(i).shipQty_A1.setValue(BigDecimal.ONE.negate());
            } else {
                bizMsg.A.no(i).shipQty_A1.setValue(BigDecimal.ONE);
            }
            // END 2018/02/23 E.Kameishi [QC#24390,MOD]
            bizMsg.A.no(i).dealNetUnitPrcAmt_A1.setValue(BigDecimal.ZERO);
            bizMsg.A.no(i).invLineDealNetAmt_A1.setValue(BigDecimal.ZERO);
            bizMsg.A.no(i).invLineDealTaxAmt_A1.setValue(BigDecimal.ZERO);
            bizMsg.A.no(i).taxPct_A1.setValue(BigDecimal.ZERO);
            bizMsg.A.no(i).xxTotAmt_A1.setValue(BigDecimal.ZERO);
            bizMsg.A.no(i).unitCostAmt_A1.setValue(BigDecimal.ZERO);

            bizMsg.A.no(i).dsSlsTaxTpCd_A1.setValue(DS_SLS_TAX_TP.STATE_TAX);
            bizMsg.A.no(i).dealSlsTaxAmt_A1.setValue(BigDecimal.ZERO);
            bizMsg.A.no(i).slsTaxPct_A1.setValue(BigDecimal.ZERO);
            bizMsg.A.no(i).dsSlsTaxTpCd_A2.setValue(DS_SLS_TAX_TP.COUNTY_TAX);
            bizMsg.A.no(i).dealSlsTaxAmt_A2.setValue(BigDecimal.ZERO);
            bizMsg.A.no(i).slsTaxPct_A2.setValue(BigDecimal.ZERO);
            bizMsg.A.no(i).dsSlsTaxTpCd_A3.setValue(DS_SLS_TAX_TP.CITY_TAX);
            bizMsg.A.no(i).dealSlsTaxAmt_A3.setValue(BigDecimal.ZERO);
            bizMsg.A.no(i).slsTaxPct_A3.setValue(BigDecimal.ZERO);

            bizMsg.A.no(i).xxPgFlg_A1.setValue(ZYPConstant.FLG_ON_Y);

            bizMsg.A.no(i).trxCd_A1.setValue(TRX_MANUAL_INVOICE);
            bizMsg.A.no(i).trxRsnCd_A1.setValue(TRX_RSN_MANUAL_INVOICE);
            // START 2017/03/17 T.Murai [QC#14205,ADD]
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxRecHistTblNm_A,"INV_LINE");
            // END   2017/03/17 T.Murai [QC#14205,ADD]

            // START 2019/04/25 S.Takami [QC#50078,DEL]
//            // START 2018/08/15 Y.Matsui [QC#27651,ADD]
//            NFCL3000CommonLogic.createPulldownListPkgUomDefault(bizMsg, i);
//            // END   2018/08/15 Y.Matsui [QC#27651,ADD]
            // END 2019/04/25 S.Takami [QC#50078,DEL]
            // START 2019/04/25 S.Takami [QC#50078,ADD]
            if (NFCL3000CommonLogic.isInvoiceWithContract(bizMsg)) {
                NFCL3000CommonLogic.createPulldownListPkgUomFromBllgCycleUOM(bizMsg, i);
            } else {
                NFCL3000CommonLogic.createPulldownListPkgUomDefault(bizMsg, i);
            }
            // END 2019/04/25 S.Takami [QC#50078,ADD]
            // START 2019/05/27 S.Takami [QC#50541,ADD]
            bizMsg.A.no(i).xxMstChkFlg_A1.setValue(ZYPConstant.FLG_OFF_N);
            // END 2019/05/27 S.Takami [QC#50541,ADD]
            // START 2019/05/31 S.Takami [QC#50633,ADD]
            bizMsg.A.no(i).mdseCd_AB.clear();
            // END 2019/05/31 S.Takami [QC#50633,ADD]
            // START 2019/06/05 S.Takami [QC#50683,ADD]
            bizMsg.A.no(i).xxYesNoCd_A1.setValue(ZYPConstant.FLG_OFF_N);
            bizMsg.A.no(i).xxYesNoCd_A1.setValue(ZYPConstant.FLG_OFF_N);
            if (NFCL3000CommonLogic.isDsInvTpFsr(bizMsg)) {
                setFsrCommonData(bizMsg, i);
            }
            // END 2019/06/05 S.Takami [QC#50683,ADD]
        }
        bizMsg.xxNum_AL.setValue(bizMsg.A.getValidCount());
        bizMsg.xxNum_A.clear();

        // Set Line Number
        NFCL3000CommonLogic.setLineNumber(bizMsg);

        // AR_COA_AFFL_CD
        if(ZYPCommonFunc.hasValue(bizMsg.sellToCustCd_H3.getValue())) {
            if(bizMsg.arCoaAfflCd_H1.getValue().contains("@")) {
                NFCL3000CommonLogic.getAcctAffl(bizMsg);
            }
        }

        // START 2016/09/12 S.Fujita [QC#14112,DEL]
        // BOL# check
//        if(!NFCL3000CommonLogic.check_BOL(bizMsg, false)) {
//            bizMsg.xxDplyTab.setValue(TAB_Line);
//            for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
//                bizMsg.A.no(i).invBolLineNum_A1.setErrorInfo(1, NOT_ASSIGNED_ERROR, new String[]{PRM_BOL_S, PRM_LINE});
//            }
//        }
        // END   2016/09/12 S.Fujita [QC#14112,DEL]

        // START 2016/09/05 S.Fujita [QC#12656,DEL]
        // Line# check
//        NFCL3000CommonLogic.check_SalesCredit(bizMsg, true);
        // END   2016/09/05 S.Fujita [QC#12656,DEL]
    }

    /**
     * doProcess_NFCL3000Scrn00_AddLine_B
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_AddLine_B(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;

        // START 2021/01/04 R.Kurahashi [QC#56282, ADD]
        if (!NFCL3000CommonLogic.checkContract(bizMsg)) {
            return;
        }
        // END 2021/01/04 R.Kurahashi [QC#56282, ADD]

        if(bizMsg.B.getValidCount() > 0 ) {
            int count = bizMsg.B.getValidCount();
            int addCount = 0;
            if(ZYPCommonFunc.hasValue(bizMsg.xxNum_B.getValue())) {
                addCount = bizMsg.xxNum_B.getValueInt();
            } else {
                addCount = 1;
            }

            if(bizMsg.B.length() < (count + addCount)) {
                bizMsg.setMessageInfo("NFCM0110E", null);
                return;
            }
            bizMsg.B.setValidCount(count + addCount);
            bizMsg.xxNum_BL.setValue(count + addCount);
            bizMsg.xxNum_B.clear();
        } else {
            NFCL3000CommonLogic.initialSetup_SlsCrTab(bizMsg);
            bizMsg.xxNum_BL.setValue(bizMsg.B.getValidCount());
            bizMsg.xxNum_B.clear();
        }
        NFCL3000CommonLogic.setSalesCreditLineNum_B(bizMsg);
    }

    /**
     * doProcess_NFCL3000Scrn00_AddLine_C
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_AddLine_C(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;
        
        // START 2021/01/04 R.Kurahashi [QC#56282, ADD]
        if (!NFCL3000CommonLogic.checkContract(bizMsg)) {
            return;
        }
        // END 2021/01/04 R.Kurahashi [QC#56282, ADD]
        
        int count = bizMsg.C.getValidCount();
        // START 2016/07/20 S.Fujita [QC#10148,MOD]
//        int addCount = 2;
        int addCount = 1;
        // END   2016/07/20 S.Fujita [QC#10148,MOD]

        if(bizMsg.C.length() < (count + addCount)) {
            bizMsg.setMessageInfo("NFCM0110E", null);
            return;
        }

        bizMsg.C.setValidCount( (count + addCount));

        bizMsg.C.no(count).clear();
        // START 2016/07/20 S.Fujita [QC#10148,MOD]
        if(bizMsg.C.getValidCount() == 1) {
            NFCL3000CommonLogic.setDefaultDebitLine(bizMsg, count);
        } else {
            bizMsg.C.no(count).drCrTpCd_C1.setValue(DR_CR_TP_CREDIT);
            // START 2017/03/17 T.Murai [QC#14205,ADD]
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(count).xxRecHistTblNm_C,"AJE_INV_ACCT_DIST");
            // END   2017/03/17 T.Murai [QC#14205,ADD]
        }
//        bizMsg.C.no(count + 1).clear();
//        bizMsg.C.no(count + 1).drCrTpCd_C1.setValue(DR_CR_TP_CREDIT);
        // END   2016/07/20 S.Fujita [QC#10148,MOD]
    }

    /**
     * doProcess_NFCL3000Scrn00_AddLine_D
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_AddLine_D(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;

        // START 2021/01/04 R.Kurahashi [QC#56282, ADD]
        if (!NFCL3000CommonLogic.checkContract(bizMsg)) {
            return;
        }
        // END 2021/01/04 R.Kurahashi [QC#56282, ADD]

        int count = bizMsg.D.getValidCount();
        int addCount = 0;
        if(ZYPCommonFunc.hasValue(bizMsg.xxNum_D.getValue())) {
            addCount = bizMsg.xxNum_D.getValueInt();
        } else {
            addCount = 1;
        }

        if(bizMsg.D.length() < (count + addCount)) {
            bizMsg.setMessageInfo("NFCM0110E", null);
            return;
        }
        bizMsg.D.setValidCount( count + addCount);

        // Initial setup
        for(int i = count; i < (count + addCount); i++) {
            NFCL3000CommonLogic.setDefaultBOL(bizMsg, i, false);
        }

        // START 2016/09/12 S.Fujita [QC#14112,ADD]
        if(bizMsg.D.getValidCount() > 0) {
            NFCL3000CommonLogic.setDefaultBOL(bizMsg, 0, true);
        }
        // END   2016/09/12 S.Fujita [QC#14112,ADD]

        bizMsg.xxNum_DL.setValue(count + addCount);
        bizMsg.xxNum_D.clear();
        // Multiple BOL
        bizMsg.xxChkBox_H2.clear();
        if(bizMsg.D.getValidCount() > 1) {
            bizMsg.xxChkBox_H2.setValue(ZYPConstant.FLG_ON_Y);
        }
        // BOL# check
        NFCL3000CommonLogic.check_BOL(bizMsg, true);

    }

    /**
     * doProcess_NFCL3000Scrn00_DeleteLine_A
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_DeleteLine_A(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;
        NFCL3000SMsg globalMsg = (NFCL3000SMsg) sMsg;

// START 2016/10/13 S.Yoshida [QC#14575,MOD]
//        if(bizMsg.A.getValidCount() < 1) {
//            return;
//        }
//        int chkCount = 0;
//        for( int i = 0; i < bizMsg.A.getValidCount(); i++) {
//            if(ZYPCommonFunc.hasValue(bizMsg.A.no(i).xxChkBox_A1.getValue())) {
//                chkCount++;
//            }
//        }
//        if(chkCount < 1) {
//            return;
//        }
//
//        globalMsg.A.clear();
//        EZDMsg.copy(bizMsg.A, null, globalMsg.A, null);
//
//        bizMsg.A.clear();
//        int cnt = 0;
//        // START 2016/06/20 S.Fujita [QC#9454,ADD]
//        for(int i = 0; i < globalMsg.A.getValidCount(); i++) {
//            if(!ZYPCommonFunc.hasValue(globalMsg.A.no(i).xxChkBox_A1.getValue())) {
//                EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(cnt), null);
//                cnt++;
//            }
//        }
//        // END  2016/06/20 S.Fujita [QC#9454,ADD]
//        bizMsg.A.setValidCount(cnt);
//        bizMsg.xxChkBox_A.clear();
//        globalMsg.A.clear();

        // START 2019/04/11 S.Takami [QC#31165,ADD]
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(bizMsg.A, "xxChkBox_A1", ZYPConstant.FLG_ON_Y);
        boolean delSlsCr = false;
        for (Integer delIdx : selectedRows) {
            NFCL3000_ACMsg lineMsg = bizMsg.A.no(delIdx);
            for (int idx4s = 0; idx4s < bizMsg.B.getValidCount(); idx4s++) {
                NFCL3000_BCMsg slsCrLineMsg = bizMsg.B.no(idx4s);
                if (S21StringUtil.isEquals(lineMsg.invBolLineNum_A1.getValue(), slsCrLineMsg.invBolLineNum_B1.getValue()) //
                        && S21StringUtil.isEquals(lineMsg.invLineNum_A1.getValue(), slsCrLineMsg.invLineNum_B1.getValue())) {
                    slsCrLineMsg.xxChkBox_B1.setValue(ZYPConstant.FLG_ON_Y);
                    delSlsCr = true;
                }
            }
        }
        // END 2019/04/11 S.Takami [QC#31165,ADD]
        bizMsg.xxChkBox_A.clear();
        NFCL3000CommonLogic.deleteMsgAry(bizMsg.A, "xxChkBox_A1", ZYPConstant.FLG_ON_Y);
// END   2016/10/13 S.Yoshida [QC#14575,MOD]

        if(bizMsg.A.getValidCount() < 1) {
            bizMsg.xxNum_AL.setValue(1);
        }
        // Multiple BOL
        bizMsg.xxChkBox_H2.clear();
        if(bizMsg.D.getValidCount() > 1) {
            bizMsg.xxChkBox_H2.setValue(ZYPConstant.FLG_ON_Y);
        }
        // START 2019/04/11 S.Takami [QC#31165,ADD]
        if (delSlsCr) {
            doProcess_NFCL3000Scrn00_DeleteLine_B(cMsg, sMsg);
        }
        // END 2019/04/11 S.Takami [QC#31165,ADD]
        // START 2016/06/03 S.Fujita [QC#9157,ADD]
        // Calc Line, BOL and Total Amount
        if(!NFCL3000CommonLogic.calcAmount(bizMsg, false)) {
            return;
        }
        // END   2016/06/03 S.Fujita [QC#9157,ADD]
        // START 2018/07/19 E.Kameishi [QC#27007,MOD]
        // Set Line Number
        NFCL3000CommonLogic.setLineNumber(bizMsg);
        // END 2018/07/19 E.Kameishi [QC#27007,MOD]

        // START 2019/06/05 S.Takami [QC#50683,ADD]
        if (NFCL3000CommonLogic.isDsInvTpFsr(bizMsg)) {
            resetFsrCommonData(bizMsg);
        }
        // END 2019/06/05 S.Takami [QC#50683,ADD]
    }

    /**
     * doProcess_NFCL3000Scrn00_DeleteLine_B
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_DeleteLine_B(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;
        NFCL3000SMsg globalMsg = (NFCL3000SMsg) sMsg;

// START 2016/10/13 S.Yoshida [QC#14575,MOD]
//        if(bizMsg.B.getValidCount() < 1) {
//            return;
//        }
//        int chkCount = 0;
//        for( int i = 0; i < bizMsg.B.getValidCount(); i++) {
//            if(ZYPCommonFunc.hasValue(bizMsg.B.no(i).xxChkBox_B1.getValue())) {
//                chkCount++;
//            }
//        }
//        if(chkCount < 1) {
//            return;
//        }
//
//        globalMsg.B.clear();
//        EZDMsg.copy(bizMsg.B, null, globalMsg.B, null);
//
//        bizMsg.B.clear();
//        int cnt = 0;
//        for(int i = 0; i < globalMsg.B.getValidCount(); i++) {
//            if(!ZYPCommonFunc.hasValue(globalMsg.B.no(i).xxChkBox_B1.getValue())) {
//                EZDMsg.copy(globalMsg.B.no(i), null, bizMsg.B.no(cnt), null);
//                cnt++;
//            }
//        }
//        bizMsg.B.setValidCount(cnt);
//        bizMsg.xxChkBox_B.clear();
//        globalMsg.B.clear();

        // START 2019/04/11 S.Takami [QC#31165,ADD]
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(bizMsg.B, "xxChkBox_B1", ZYPConstant.FLG_ON_Y);
        boolean delAcct = false;
        for (Integer delIdx : selectedRows) {
            if (!ZYPCommonFunc.hasValue(bizMsg.B.no(delIdx).dsInvSlsCrPk_B1) //
                    || BigDecimal.ZERO.compareTo(bizMsg.B.no(delIdx).dsInvSlsCrPk_B1.getValue()) == 0) {
                continue;
            }
            BigDecimal delDsInvSlsCrPk = bizMsg.B.no(delIdx).dsInvSlsCrPk_B1.getValue();
            for (int idx4c = 0; idx4c < bizMsg.C.getValidCount(); idx4c++) {
                NFCL3000_CCMsg acctLineMsg = bizMsg.C.no(idx4c);
                // START 2019/06/05 S.Takami [QC#50683,ADD]
                if (S21StringUtil.isEquals(DR_CR_TP_DEBIT, acctLineMsg.drCrTpCd_C1.getValue())) {
                    continue;
                }
                // END 2019/06/05 S.Takami [QC#50683,ADD]
                if (!ZYPCommonFunc.hasValue(acctLineMsg.dsInvSlsCrPk_C1) //
                        || BigDecimal.ZERO.compareTo(acctLineMsg.dsInvSlsCrPk_C1.getValue()) == 0) {
                    continue;
                }
                BigDecimal acctDsInvSlsCrPk = acctLineMsg.dsInvSlsCrPk_C1.getValue();
                if (delDsInvSlsCrPk.compareTo(acctDsInvSlsCrPk) == 0) {
                    acctLineMsg.xxChkBox_C1.setValue(ZYPConstant.FLG_ON_Y);
                    delAcct = true;
                }
            }
        }
        // END 2019/04/11 S.Takami [QC#31165,ADD]
        bizMsg.xxChkBox_B.clear();
        NFCL3000CommonLogic.deleteMsgAry(bizMsg.B, "xxChkBox_B1", ZYPConstant.FLG_ON_Y);
// END   2016/10/13 S.Yoshida [QC#14575,MOD]

        if(bizMsg.B.getValidCount() < 1) {
            bizMsg.xxNum_BL.setValue(1);
        }

        NFCL3000CommonLogic.setSalesCreditLineNum_B(bizMsg);

        // START 2016/06/03 S.Fujita [QC#9157,MOD]
//        if(!NFCL3000CommonLogic.check_SalesCredit(bizMsg, false)) {
//            bizMsg.xxDplyTab.setValue(TAB_SalesCredit);
//            for(int i = 0; i < bizMsg.B.getValidCount(); i++) {
//              //---- start 2016/05/06 QC#7860
//                //bizMsg.B.no(i).invLineNum_B1.setErrorInfo(1, "NFCM0517E", new String[]{"Line#"});
//                bizMsg.B.no(i).invLineNum_B1.setErrorInfo(1, NOT_ASSIGNED_ERROR, new String[]{PRM_LINE_S, PRM_SLS_CR});
//                //---- end 2016/05/06
//            }
////            bizMsg.setMessageInfo("NFCM0517E", new String[]{"Line#"});
//        }

        // START 2019/04/11 S.Takami [QC#31165,ADD]
        if (delAcct) {
            doProcess_NFCL3000Scrn00_DeleteLine_C(cMsg, sMsg);
        }
        // END 2019/04/11 S.Takami [QC#31165,ADD]

        // Calc Line, BOL and Total Amount
        if(!NFCL3000CommonLogic.calcAmount(bizMsg, false)) {
            return;
        }
        // END   2016/06/03 S.Fujita [QC#9157,MOD]
    }

    /**
     * doProcess_NFCL3000Scrn00_DeleteLine_C
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_DeleteLine_C(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;
        NFCL3000SMsg globalMsg = (NFCL3000SMsg) sMsg;

// START 2016/10/13 S.Yoshida [QC#14575,MOD]
//        if(bizMsg.C.getValidCount() < 1) {
//            return;
//        }
//        // START 2016/07/20 S.Fujita [QC#10148,MOD]
////        int chkCount = 0;
////        int no = globalMsg.E.getValidCount();
////        for( int i = 0; i < bizMsg.C.getValidCount(); i+=2) {
////            if(ZYPCommonFunc.hasValue(bizMsg.C.no(i).xxChkBox_C1.getValue())) {
////                if(ZYPCommonFunc.hasValue(bizMsg.C.no(i).ajeInvAcctDistPk_C1.getValue()) && bizMsg.C.no(i).ajeInvAcctDistPk_C1.getValue().compareTo(BigDecimal.ZERO)!=0) {
////                    globalMsg.E.no(no).ajeInvAcctDistPk_E1.setValue(bizMsg.C.no(i).ajeInvAcctDistPk_C1.getValue());
////                    no++;
////                    globalMsg.E.no(no).ajeInvAcctDistPk_E1.setValue(bizMsg.C.no(i+1).ajeInvAcctDistPk_C1.getValue());
////                    no++;
////                }
////                chkCount++;
////            }
////        }
////        globalMsg.E.setValidCount(no);
//
//        int chkCount = 0;
//        for( int i = 0; i < bizMsg.C.getValidCount(); i++) {
//            if(ZYPCommonFunc.hasValue(bizMsg.C.no(i).xxChkBox_C1.getValue())) {
//                chkCount++;
//            }
//            
//        }
//        // END   2016/07/20 S.Fujita [QC#10148,MOD]
//        if(chkCount < 1) {
//            return;
//        }
//
//        globalMsg.C.clear();
//        EZDMsg.copy(bizMsg.C, null, globalMsg.C, null);
//
//        bizMsg.C.clear();
//        int cnt = 0;
//        // START 2016/07/20 S.Fujita [QC#10148,MOD]
////        for(int i = 0; i < globalMsg.C.getValidCount(); i+=2) {
////            if(!ZYPCommonFunc.hasValue(globalMsg.C.no(i).xxChkBox_C1.getValue())) {
////                EZDMsg.copy(globalMsg.C.no(i), null, bizMsg.C.no(cnt), null);
////                EZDMsg.copy(globalMsg.C.no(i+1), null, bizMsg.C.no(cnt+1), null);
////                cnt+=2;
////            }
////        }
//        for(int i = 0; i < globalMsg.C.getValidCount(); i++) {
//            if(!ZYPCommonFunc.hasValue(globalMsg.C.no(i).xxChkBox_C1.getValue())) {
//                EZDMsg.copy(globalMsg.C.no(i), null, bizMsg.C.no(cnt), null);
//                cnt++;
//            }
//        }
//        // END   2016/07/20 S.Fujita [QC#10148,MOD]
//        bizMsg.C.setValidCount(cnt);
//        bizMsg.xxChkBox_C.clear();
//        globalMsg.C.clear();

        bizMsg.xxChkBox_C.clear();
        NFCL3000CommonLogic.deleteMsgAry(bizMsg.C, "xxChkBox_C1", ZYPConstant.FLG_ON_Y);
// END   2016/10/13 S.Yoshida [QC#14575,MOD]

        if(bizMsg.C.getValidCount() < 1) {
            bizMsg.xxNum_CL.setValue(1);
        }

        NFCL3000CommonLogic.copyCtoF(bizMsg, globalMsg);

        // START 2016/06/03 S.Fujita [QC#9157,MOD]
//        NFCL3000CommonLogic.checkAmount_AcctDist_DrCr_Totoal(bizMsg);
//
//        if(!NFCL3000CommonLogic.check_AccountDist(bizMsg, false)) {
//            bizMsg.xxDplyTab.setValue(TAB_Accounting);
//            for(int i = 0; i < bizMsg.C.getValidCount(); i++) {
//              //---- start 2016/05/06 QC#7860
//                //bizMsg.C.no(i).xxLineNum_C1.setErrorInfo(1, "NFCM0517E", new String[]{"SC#"});
//                bizMsg.C.no(i).xxLineNum_C1.setErrorInfo(1, NOT_ASSIGNED_ERROR, new String[]{PRM_SLS_CR_S, PRM_ACCT_DIST});
//                //---- end 2016/05/06
//            }
////            bizMsg.setMessageInfo("NFCM0517E", new String[]{"Line#"});
//        }
        NFCL3000CommonLogic.calcAmount_Acct_DrCr(bizMsg, false);
        // END   2016/06/03 S.Fujita [QC#9157,MOD]
    }

    /**
     * doProcess_NFCL3000Scrn00_DeleteLine_D
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_DeleteLine_D(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;
        // START 2019/04/15 S.Takami [QC#31191,DEL]
//        NFCL3000SMsg globalMsg = (NFCL3000SMsg) sMsg;
        // END 2019/04/15 S.Takami [QC#31191,DEL]

// START 2016/10/13 S.Yoshida [QC#14575,MOD]
//        if(bizMsg.D.getValidCount() < 1) {
//            return;
//        }
//        int chkCount = 0;
//        for( int i = 0; i < bizMsg.D.getValidCount(); i++) {
//            if(ZYPCommonFunc.hasValue(bizMsg.D.no(i).xxChkBox_D1.getValue())) {
//                chkCount++;
//            }
//        }
//        if(chkCount < 1) {
//            return;
//        }
//
//        globalMsg.D.clear();
//        EZDMsg.copy(bizMsg.D, null, globalMsg.D, null);
//
//        bizMsg.D.clear();
//        int cnt = 0;
//        // START 2016/06/20 S.Fujita [QC#9454,ADD]
//        for(int i = 0; i < globalMsg.D.getValidCount(); i++) {
//            if(!ZYPCommonFunc.hasValue(globalMsg.D.no(i).xxChkBox_D1.getValue())) {
//                EZDMsg.copy(globalMsg.D.no(i), null, bizMsg.D.no(cnt), null);
//                cnt++;
//            }
//        }
//        // END   2016/06/20 S.Fujita [QC#9454,ADD]
//        bizMsg.D.setValidCount(cnt);
//        bizMsg.xxChkBox_D.clear();
//        globalMsg.D.clear();

        bizMsg.xxChkBox_D.clear();
        // START 2019/04/15 S.Takami [QC#31191,ADD]
        List<Integer> delShippingList = ZYPTableUtil.getSelectedRows(bizMsg.D, "xxChkBox_D1", ZYPConstant.FLG_ON_Y);
        boolean delLine = false;
        for (Integer delShippingIdx : delShippingList) {
            String delBolLineNum = bizMsg.D.no(delShippingIdx.intValue()).invBolLineNum_D1.getValue();
            // Preparing deleting Line Tab
            for (int childIdx = 0; childIdx < bizMsg.A.getValidCount(); childIdx++) {
                if (S21StringUtil.isEquals(delBolLineNum, bizMsg.A.no(childIdx).invBolLineNum_A1.getValue())) {
                    bizMsg.A.no(childIdx).xxChkBox_A1.setValue(ZYPConstant.FLG_ON_Y);
                    delLine = true;
                }
            }
        }
        // END 2019/04/15 S.Takami [QC#31191,ADD]
        NFCL3000CommonLogic.deleteMsgAry(bizMsg.D, "xxChkBox_D1", ZYPConstant.FLG_ON_Y);
// END   2016/10/13 S.Yoshida [QC#14575,MOD]

        // START 2019/04/15 S.Takami [QC#31191,ADD]
        if (delLine) {
            doProcess_NFCL3000Scrn00_DeleteLine_A(cMsg, sMsg);
        }
        // END 2019/04/15 S.Takami [QC#31191,ADD]
        if(bizMsg.D.getValidCount() < 1) {
            bizMsg.xxNum_DL.setValue(1);
        }

        NFCL3000CommonLogic.reSetBolLineNumber(bizMsg);
        // START 2016/06/03 S.Fujita [QC#9157,MOD]
//        NFCL3000CommonLogic.setLineNumber(bizMsg);

        // Calc Line, BOL and Total Amount
        if(!NFCL3000CommonLogic.calcAmount(bizMsg, false)) {
            return;
        }
        // END   2016/06/03 S.Fujita [QC#9157,MOD]
    }

    /**
     * doProcess_NFCL3000Scrn00_SelectCheckBox_A
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_SelectCheckBox_A(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;

        if(ZYPCommonFunc.hasValue(bizMsg.xxChkBox_A.getValue())) {
            for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
                bizMsg.A.no(i).xxChkBox_A1.setValue(ZYPConstant.FLG_ON_Y);
            }
        } else {
            for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
                bizMsg.A.no(i).xxChkBox_A1.clear();
            }
        }
    }

    /**
     * doProcess_NFCL3000Scrn00_SelectCheckBox_AA
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_SelectCheckBox_AA(EZDCMsg cMsg, EZDSMsg sMsg) {
        // do nothing.
    }

    /**
     * doProcess_NFCL3000Scrn00_SelectCheckBox_B
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_SelectCheckBox_B(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;

        if (ZYPCommonFunc.hasValue(bizMsg.xxChkBox_B.getValue())) {
            for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
                bizMsg.B.no(i).xxChkBox_B1.setValue(ZYPConstant.FLG_ON_Y);

                // START 2016/10/31 T.Murai [QC#14546, ADD]
                if (ZYPConstant.FLG_ON_Y.equals(bizMsg.B.no(i).xxHldFlg_B1.getValue())) {
                    bizMsg.B.no(i).xxChkBox_B1.clear();
                }
                // END   2016/10/31 T.Murai [QC#14546, ADD]
            }

        } else {
            for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
                bizMsg.B.no(i).xxChkBox_B1.clear();
            }
        }
    }

    /**
     * doProcess_NFCL3000Scrn00_SelectCheckBox_BA
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_SelectCheckBox_BA(EZDCMsg cMsg, EZDSMsg sMsg) {
     // do nothing.
    }

    /**
     * doProcess_NFCL3000Scrn00_SelectCheckBox_C
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_SelectCheckBox_C(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;

        if (ZYPCommonFunc.hasValue(bizMsg.xxChkBox_C.getValue())) {
            for (int i = 0; i < bizMsg.C.getValidCount(); i++) {

                // START 2016/09/26 S.Fujita [QC#13362,ADD]
                if (AJE_INV_ACCT_CLS.REV_EARN_TMPLT.equals(bizMsg.C.no(i).ajeInvAcctClsCd_C1.getValue()) //
                        || AJE_INV_ACCT_CLS.REVENUE_EARNED.equals(bizMsg.C.no(i).ajeInvAcctClsCd_C1.getValue()) //
                        || AJE_INV_ACCT_CLS.RECEIVABLES.equals(bizMsg.C.no(i).ajeInvAcctClsCd_C1.getValue()) //
                        || (DR_CR_TP_DEBIT.equals(bizMsg.C.no(i).drCrTpCd_C1.getValue()) //
                                && AJE_INV_ACCT_CLS.UNEARNED_REVENUE.equals(bizMsg.C.no(i).ajeInvAcctClsCd_C1.getValue()))) {
                    continue;
                }
                // END   2016/09/26 S.Fujita [QC#13362,ADD]
                bizMsg.C.no(i).xxChkBox_C1.setValue(ZYPConstant.FLG_ON_Y);
            }

        } else {
            for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
                bizMsg.C.no(i).xxChkBox_C1.clear();
            }
        }
    }

    /**
     * doProcess_NFCL3000Scrn00_SelectCheckBox_D
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_SelectCheckBox_D(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;

        if(ZYPCommonFunc.hasValue(bizMsg.xxChkBox_D.getValue())) {
            for(int i = 0; i < bizMsg.D.getValidCount(); i++) {
                bizMsg.D.no(i).xxChkBox_D1.setValue(ZYPConstant.FLG_ON_Y);
            }
        } else {
            for(int i = 0; i < bizMsg.D.getValidCount(); i++) {
                bizMsg.D.no(i).xxChkBox_D1.clear();
            }
        }
    }

    /**
     * doProcess_NFCL3000Scrn00_SelectCheckBox_DA
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_SelectCheckBox_DA(EZDCMsg cMsg, EZDSMsg sMsg) {
        // do nothing.
    }

    /**
     * doProcess_NFCL3000Scrn00_ItemName_A
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_ItemName_A(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;

        // START 2018/08/15 Y.Matsui [QC#27651,MOD]
        int idx = bizMsg.xxCellIdx.getValueInt();
        if (!NFCL3000CommonLogic.getItemName_A(bizMsg, idx, true)) {
            NFCL3000CommonLogic.createPulldownListPkgUomDefault(bizMsg, idx);
            return;
        }
        bizMsg.A.no(idx).pkgUomCd_A1.clear();
        // START 2019/04/25 S.Takami [QC#50078,DEL]
//        NFCL3000CommonLogic.createPulldownListPkgUomByMdse(bizMsg, idx);
        // END 2019/04/25 S.Takami [QC#50078,DEL]
        // START 2019/04/25 S.Takami [QC#50078,ADD]
        if (NFCL3000CommonLogic.isInvoiceWithContract(bizMsg)) {
            NFCL3000CommonLogic.createPulldownListPkgUomFromBllgCycleUOM(bizMsg, idx);
        } else {
            NFCL3000CommonLogic.createPulldownListPkgUomByMdse(bizMsg, idx);
        }
        // END 2019/04/25 S.Takami [QC#50078,ADD]
        // END   2018/08/15 Y.Matsui [QC#27651,MOD]

        // START 2017/12/25 E.Kameishi [QC#20312,ADD]
        NFCL3000CommonLogic.isTaxAdjustmentItem(bizMsg, idx);
        // END   2017/12/25 E.Kameishi [QC#20312,ADD]

        // START 2019/04/25 S.Takami [QC#50078,ADD]
        NFCL3000CommonLogic.setDplyCtrlFlg(bizMsg, idx);
        // END 2019/04/25 S.Takami [QC#50078,ADD]
    }

    /**
     * doProcess_NFCL3000Scrn00_SalesRepName_B
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_SalesRepName_B(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;

        int idx = bizMsg.xxCellIdx.getValueInt();
        if(!ZYPCommonFunc.hasValue(bizMsg.B.no(idx).psnNum_B1.getValue())) {
            return;
        }
        NFCL3000CommonLogic.getSalesRepName(bizMsg, idx, true);
    }

    /**
     * doProcess_NFCL3000Scrn00_Select_AccountingClass
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_Select_AccountingClass(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;
        int idx = bizMsg.xxCellIdx.getValueInt();
        // START 2016/07/20 S.Fujita [QC#10148,DEL]
//        ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx+1).ajeInvAcctClsCd_C1, bizMsg.C.no(idx).ajeInvAcctClsCd_C1.getValue());
        // END   2016/07/20 S.Fujita [QC#10148,DEL]

        // START 2016/07/20 S.Fujita [QC#10148,ADD]
        // Set MDSE_CD and TOC_CD
        NFCL3000CommonLogic.setMdseAndSlsRep(bizMsg.B, bizMsg.C.no(idx));

        // Set AJE_INV_ACCT_DIST
        if(!NFCL3000CommonLogic.setDefaultAcctDist(bizMsg, bizMsg.C.no(idx), bizMsg.C.no(idx).ajeInvAcctClsCd_C1.getValue())) {
            return;
        }
        // END   2016/07/20 S.Fujita [QC#10148,ADD]
    }

    /**
     * doProcess_NFCL3000_NMAL2550
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000_NMAL2550(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;

        int lineNum = bizMsg.xxCellIdx.getValueInt();

        StringBuilder coaString = new StringBuilder();
        coaString.append(bizMsg.C.no(lineNum).ajeCoaCmpyCd_C1.getValue());
        coaString.append(".");
        coaString.append(bizMsg.C.no(lineNum).ajeCoaBrCd_C1.getValue());
        coaString.append(".");
        coaString.append(bizMsg.C.no(lineNum).ajeCoaCcCd_C1.getValue());
        coaString.append(".");
        coaString.append(bizMsg.C.no(lineNum).ajeCoaAcctCd_C1.getValue());
        coaString.append(".");
        coaString.append(bizMsg.C.no(lineNum).ajeCoaProdCd_C1.getValue());
        coaString.append(".");
        coaString.append(bizMsg.C.no(lineNum).ajeCoaChCd_C1.getValue());
        coaString.append(".");
        coaString.append(bizMsg.C.no(lineNum).ajeCoaAfflCd_C1.getValue());
        coaString.append(".");
        coaString.append(bizMsg.C.no(lineNum).ajeCoaProjCd_C1.getValue());
        coaString.append(".");
        coaString.append(bizMsg.C.no(lineNum).ajeCoaExtnCd_C1.getValue());

        bizMsg.C.no(lineNum).xxScrItem61Txt_C1.setValue(coaString.toString());

        // START 2016/09/21 S.Fujita [QC#14481,MOD]
//        NFCL3000CommonLogic.check9Segment(bizMsg);
        NFCL3000CommonLogic.check9Segment_idx(bizMsg, bizMsg.C.no(lineNum));
        // END   2016/09/21 S.Fujita [QC#14481,MOD]
    }

    /**
     * doProcess_NFCL3000_NMAL6770
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000_NMAL6770(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;

        if(bizMsg.xxScrItem10Txt.getValue().equals("ShipTo")) {
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtacPsnFirstNm_H2, bizMsg.P.no(6).xxPopPrm.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtacPsnLastNm_H2, bizMsg.P.no(7).xxPopPrm.getValue());
            //---- start 2016/05/03 QC#7862 avoid showing only comma.
            if (hasValue(bizMsg.shipToCtacPsnFirstNm_H2)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxPsnNm_H2, bizMsg.shipToCtacPsnFirstNm_H2.getValue().concat(",").concat(bizMsg.shipToCtacPsnLastNm_H2.getValue()));
            }
            //---- end 2016/05/03
        } else if(bizMsg.xxScrItem10Txt.getValue().equals("BillTo")) {
            ZYPEZDItemValueSetter.setValue(bizMsg.billToCtacPsnFirstNm_H3, bizMsg.P.no(6).xxPopPrm.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.billToCtacPsnLastNm_H3, bizMsg.P.no(7).xxPopPrm.getValue());
            //---- start 2016/05/03 QC#7862 avoid showing only comma.
            if (hasValue(bizMsg.billToCtacPsnFirstNm_H3)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxPsnNm_H3, bizMsg.billToCtacPsnFirstNm_H3.getValue().concat(",").concat(bizMsg.billToCtacPsnLastNm_H3.getValue()));
            }
            //---- end 2016/05/03
        // START 2016/08/01 S.Fujita [QC#10148,ADD]
        } else if(bizMsg.xxScrItem10Txt.getValue().equals("ShipTo_D")) {
            int idx = bizMsg.xxCellIdx.getValueInt();
            ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).shipToCtacPsnFirstNm_D1, bizMsg.P.no(6).xxPopPrm.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).shipToCtacPsnLastNm_D1, bizMsg.P.no(7).xxPopPrm.getValue());

            if (hasValue(bizMsg.D.no(idx).shipToCtacPsnFirstNm_D1)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).xxPsnNm_D1, bizMsg.D.no(idx).shipToCtacPsnFirstNm_D1.getValue().concat(",").concat(bizMsg.D.no(idx).shipToCtacPsnLastNm_D1.getValue()));
            }
        }
        // END   2016/08/01 S.Fujita [QC#10148,ADD]
    }

    // START 2017/12/25 E.Kameishi [QC#20312,ADD]
    /**
     * doProcess_NFCL3000_NMAL6800
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000_NMAL6800(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;

        int idx = bizMsg.xxCellIdx.getValueInt();

        // START 2018/08/15 Y.Matsui [QC#27651,ADD]
        bizMsg.A.no(idx).pkgUomCd_A1.clear();
        // START 2019/04/25 S.Takami [QC#50078,DEL]
//        NFCL3000CommonLogic.createPulldownListPkgUomByMdse(bizMsg, idx);
        // END 2019/04/25 S.Takami [QC#50078,DEL]
        // START 2019/04/25 S.Takami [QC#50078,ADD]
        if (NFCL3000CommonLogic.isInvoiceWithContract(bizMsg)) {
            NFCL3000CommonLogic.createPulldownListPkgUomFromBllgCycleUOM(bizMsg, idx);
        } else {
            NFCL3000CommonLogic.createPulldownListPkgUomByMdse(bizMsg, idx);
        }
        // END 2019/04/25 S.Takami [QC#50078,ADD]
        // END   2018/08/15 Y.Matsui [QC#27651,ADD]

        NFCL3000CommonLogic.isTaxAdjustmentItem(bizMsg, idx);

        // START 2019/04/25 S.Takami [QC#50078,ADD]
        NFCL3000CommonLogic.setDplyCtrlFlg(bizMsg, idx);
        // END 2019/04/25 S.Takami [QC#50078,ADD]

    }
    // END   2017/12/25 E.Kameishi [QC#20312,ADD]

    /**
     * doProcess_NFCL3000_NWAL1130
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000_NWAL1130(EZDCMsg cMsg, EZDSMsg sMsg) {
        // do nothing.
    }

    /**
     * doProcess_NFCL3000Scrn00_TAB_Line
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_TAB_Line(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;
        NFCL3000SMsg globalMsg = (NFCL3000SMsg) sMsg;

        // START 2016/08/01 S.Fujita [QC#10148,ADD]
//        if (bizMsg.xxDplyTab_H1.getValue().equals(DIS_PAT_VISIBILITY_ERROR)) {
//            ZYPGUITableColumn.getColData(bizMsg, globalMsg, "A1HEAD");
//        } else {
//            ZYPGUITableColumn.getColData(bizMsg, globalMsg, "A2HEAD");
//        }
        // END   2016/08/01 S.Fujita [QC#10148,ADD]

        // START 2016/09/12 S.Fujita [QC#14112,ADD]
        // if shipping line are deleted, line and sales credit line are deleted automatically. 
        NFCL3000CommonLogic.deleteLine_LineTab(bizMsg, globalMsg);
        // END   2016/09/12 S.Fujita [QC#14112,ADD]

        // Set Detail Line Number
        NFCL3000CommonLogic.setDetailLineNumber(bizMsg);
        // START 2017/12/25 E.Kameishi [QC#20312,ADD]
        NFCL3000CommonLogic.setTaxAdjustmentItem(bizMsg);
        // END 2017/12/25 E.Kameishi [QC#20312,ADD]
        // START 2016/09/12 S.Fujita [QC#14112,MOD]
//        NFCL3000CommonLogic.calcAmount_InvLine(bizMsg);
        // After lines are deleted, calc Line, BOL and Total Amount
        if(!NFCL3000CommonLogic.calcAmount(bizMsg, false)) {
            return;
        }
        // END   2016/09/12 S.Fujita [QC#14112,MOD]

        // START 2016/07/20 S.Fujita [QC#10148,ADD]
        if (ACCT_DIST_EDIT.equals(bizMsg.xxRadioBtn_C.getValue())) {
            NFCL3000CommonLogic.copyCtoF(bizMsg, globalMsg);
        }
        // END   2016/07/20 S.Fujita [QC#10148,ADD]
        // START 2017/12/25 E.Kameishi [QC#20312,ADD]
        NFCL3000CommonLogic.setUnitPrice(bizMsg);
        // END 2017/12/25 E.Kameishi [QC#20312,ADD]
    }

    /**
     * doProcess_NFCL3000Scrn00_TAB_SalesCredit
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_TAB_SalesCredit(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;
        NFCL3000SMsg globalMsg = (NFCL3000SMsg) sMsg;

        // START 2016/08/01 S.Fujita [QC#10148,ADD]
//        if (bizMsg.xxDplyTab_H1.getValue().equals(DIS_PAT_VISIBILITY_ERROR)) {
//            ZYPGUITableColumn.getColData(bizMsg, globalMsg, "B1HEAD");
//        } else {
//            ZYPGUITableColumn.getColData(bizMsg, globalMsg, "B2HEAD");
//        }
        // END   2016/08/01 S.Fujita [QC#10148,ADD]

        if(NFCL3000CommonLogic.isManualInvoice(bizMsg)) {

            // START 2016/09/12 S.Fujita [QC#14112,DEL]
//            // Line Tab Check
//            if(bizMsg.xxDplyTab.getValue().equals(TAB_Line)) {
//                if(!NFCL3000CommonLogic.check_LineTAB(bizMsg)) {
//                    return;
//                }
//            }
            // END   2016/09/12 S.Fujita [QC#14112,DEL]

            // Initial Setup
            NFCL3000CommonLogic.setDetailLineNumber(bizMsg);

            // Before adding lines, calc Line, BOL and Total Amount
            if (!NFCL3000CommonLogic.calcAmount(bizMsg, false)) {
                return;
            }

            // START 2016/07/20 S.Fujita [QC#10148,MOD]
//            NFCL3000CommonLogic.initialSetup_SlsCrTab(bizMsg);
            // if line are added, sales credit line are added automatically. 
            NFCL3000CommonLogic.defaultSetup_SlsCrTab(bizMsg);
            // END   2016/07/20 S.Fujita [QC#10148,MOD]

            // START 2016/09/12 S.Fujita [QC#14112,ADD]
            // if shipping line are deleted, line and sales credit line are deleted automatically. 
            NFCL3000CommonLogic.deleteLine_LineTab(bizMsg, globalMsg);
            NFCL3000CommonLogic.deleteLine_SlsCrTab(bizMsg, globalMsg);

            // START 2019/04/11 S.Takami [QC#31165,ADD]
            NFCL3000CommonLogic.deleteLine_AcctTab(bizMsg, globalMsg);
            // END 2019/04/11 S.Takami [QC#31165,ADD]

            // After lines are deleted, calc Line, BOL and Total Amount
            if (!NFCL3000CommonLogic.calcAmount(bizMsg, false)) {
                return;
            }
            // END   2016/09/12 S.Fujita [QC#14112,ADD]

            NFCL3000CommonLogic.setSalesCreditLineNum_B(bizMsg);

            NFCL3000CommonLogic.setupMdseCd(bizMsg);
        }

        // START 2016/07/20 S.Fujita [QC#10148,ADD]
        if (ACCT_DIST_EDIT.equals(bizMsg.xxRadioBtn_C.getValue())) {
            NFCL3000CommonLogic.copyCtoF(bizMsg, globalMsg);
        }
        // END   2016/07/20 S.Fujita [QC#10148,ADD]
    }

    /**
     * doProcess_NFCL3000Scrn00_TAB_Accounting
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_TAB_Accounting(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;
        NFCL3000SMsg globalMsg = (NFCL3000SMsg) sMsg;

        // START 2016/08/01 S.Fujita [QC#10148,ADD]
//        if (bizMsg.xxDplyTab_H1.getValue().equals(DIS_PAT_VISIBILITY_ERROR)) {
//            ZYPGUITableColumn.getColData(bizMsg, globalMsg, "C1HEAD");
//        } else {
//            ZYPGUITableColumn.getColData(bizMsg, globalMsg, "C2HEAD");
//        }
        // END   2016/08/01 S.Fujita [QC#10148,ADD]

        if(bizMsg.xxPgFlg.getValue().equals(ZYPConstant.FLG_ON_Y)) {
            NFCL3000CommonLogic.clearSubmit(cMsg, sMsg);
            doProcess_NFCL3000Scrn00_Search(cMsg, sMsg);
        } else {

            // START 2016/07/20 S.Fujita [QC#10148,MOD]
            // Before adding lines, calc Line, BOL and Total Amount
            if (!NFCL3000CommonLogic.calcAmount(bizMsg, false)) {
                return;
            }
            // START 2018/07/17 E.Kameishi [QC#27007,ADD]
            NFCL3000CommonLogic.deleteLine_SlsCrTab(bizMsg, globalMsg);
            NFCL3000CommonLogic.setupMdseCd(bizMsg);
            // END 2018/07/17 E.Kameishi [QC#27007,ADD]
            // if sales credit line are added, account dist line are added automatically. 
            NFCL3000CommonLogic.defaultSetup_SlsCrTabAndAcctTab(bizMsg, globalMsg);
            // END   2016/07/20 S.Fujita [QC#10148,MOD]

            // START 2016/09/12 S.Fujita [QC#14112,ADD]
            // if sales credit line are deleted, account dist line are deleted automatically. 
            NFCL3000CommonLogic.deleteLine_SlsCrTabAndAcctTab(bizMsg, globalMsg);
            // START 2018/07/17 E.Kameishi [QC#27007,ADD]
            NFCL3000CommonLogic.setAjeInvAcctDistPct(bizMsg);
            // END 2018/07/17 E.Kameishi [QC#27007,ADD]

            // After lines are deleted, calc Line, BOL and Total Amount
            if (!NFCL3000CommonLogic.calcAmount(bizMsg, false)) {
                return;
            }
            NFCL3000CommonLogic.calcAmount_Acct_DrCr(bizMsg, false);
            // END   2016/09/12 S.Fujita [QC#14112,ADD]
        }
        // START 2018/07/17 E.Kameishi [QC#27007,ADD]
        NFCL3000CommonLogic.delAcctDist(bizMsg);
        // END 2018/07/17 E.Kameishi [QC#27007,ADD]
        // START 2016/07/20 S.Fujita [QC#10148,ADD]
        if (ACCT_DIST_EDIT.equals(bizMsg.xxRadioBtn_C.getValue())) {
            NFCL3000CommonLogic.copyCtoF(bizMsg, globalMsg);
        }
        // END   2016/07/20 S.Fujita [QC#10148,ADD]
    }

    /**
     * doProcess_NFCL3000Scrn00_TAB_BOL
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_TAB_BOL(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;
        NFCL3000SMsg globalMsg = (NFCL3000SMsg) sMsg;
        // START 2016/06/03 S.Fujita [QC#9452,MOD]
//        if(NFCL3000CommonLogic.isManualInvoice(bizMsg)) {
//
//            if(!NFCL3000CommonLogic.check_BOL(bizMsg, true)) {
//            }
////20160601 Start
////            NFCL3000CommonLogic.initialSetup_SlsCrTab(bizMsg);
////20160601 End
//            NFCL3000CommonLogic.calcAmount_InvLine(bizMsg);
//            NFCL3000CommonLogic.calcAmount_SlsCr(bizMsg);
//            NFCL3000CommonLogic.initialSetup_BOLTab(bizMsg);
//            NFCL3000CommonLogic.setCheckBox(bizMsg);
//            NFCL3000CommonLogic.setDetailLineNumber(bizMsg);
//            
//            // BOL# check
//            if(!NFCL3000CommonLogic.check_BOL(bizMsg, false)) {
//                bizMsg.xxDplyTab.setValue(TAB_Line);
//                for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
//                  //---- start 2016/05/06 QC#7860
//                    //bizMsg.A.no(i).invBolLineNum_A1.setErrorInfo(1, "NFCM0517E", new String[]{"BOL Line#"});
//                    bizMsg.A.no(i).invBolLineNum_A1.setErrorInfo(1, NOT_ASSIGNED_ERROR, new String[]{PRM_BOL_S, PRM_LINE});
//                    //---- end 2016/05/06
//                }
////                bizMsg.setMessageInfo("NFCM0517E", new String[]{"BOL Line#"});
//            }
//        }
        if(NFCL3000CommonLogic.isManualInvoice(bizMsg)) {

            // Line Tab Check
            if(bizMsg.xxDplyTab.getValue().equals(TAB_Line)) {
                if(!NFCL3000CommonLogic.check_LineTAB(bizMsg)) {
                    return;
                }
            }

            // START 2016/09/12 S.Fujita [QC#14112,MOD]
            // Calc Line, BOL and Total Amount
//            NFCL3000CommonLogic.calcAmount_InvLine(bizMsg);
            if (!NFCL3000CommonLogic.calcAmount(bizMsg, false)) {
                return;
            }
            // END   2016/09/12 S.Fujita [QC#14112,MOD]

            // Initial Setup
            NFCL3000CommonLogic.initialSetup_BOLTab(bizMsg);
            // START 2016/09/12 S.Fujita [QC#14112,ADD]
            if(bizMsg.D.getValidCount() > 0) {
                NFCL3000CommonLogic.setDefaultBOL(bizMsg, 0, true);
            }
            // END   2016/09/12 S.Fujita [QC#14112,ADD]
        }
        // END   2016/06/03 S.Fujita [QC#9452,MOD]

        // START 2016/07/20 S.Fujita [QC#10148,ADD]
        if (ACCT_DIST_EDIT.equals(bizMsg.xxRadioBtn_C.getValue())) {
            NFCL3000CommonLogic.copyCtoF(bizMsg, globalMsg);
        }
        // END   2016/07/20 S.Fujita [QC#10148,ADD]
    }

    /**
     * doProcess_NFCL3000Scrn00_TAB_MoreInfo
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_TAB_MoreInfo(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;
        NFCL3000SMsg globalMsg = (NFCL3000SMsg) sMsg;
        // START 2016/06/03 S.Fujita [QC#9452,MOD]
//        if(NFCL3000CommonLogic.isManualInvoice(bizMsg)) {
////20160601 Start
////            NFCL3000CommonLogic.initialSetup_SlsCrTab(bizMsg);
////20160601 End
//            NFCL3000CommonLogic.calcAmount_InvLine(bizMsg);
//            NFCL3000CommonLogic.calcAmount_SlsCr(bizMsg);
//            NFCL3000CommonLogic.initialSetup_BOLTab(bizMsg);
//            NFCL3000CommonLogic.setCheckBox(bizMsg);
//            NFCL3000CommonLogic.setDetailLineNumber(bizMsg);
//        }

        if(NFCL3000CommonLogic.isManualInvoice(bizMsg)) {

            // Line Tab Check
            if(bizMsg.xxDplyTab.getValue().equals(TAB_Line)) {
                NFCL3000CommonLogic.check_LineTAB(bizMsg);
            }

            // START 2016/09/12 S.Fujita [QC#14112,MOD]
//            NFCL3000CommonLogic.calcAmount_InvLine(bizMsg);
            // Calc Line, BOL and Total Amount
            if (!NFCL3000CommonLogic.calcAmount(bizMsg, false)) {
                return;
            }
            // END   2016/09/12 S.Fujita [QC#14112,MOD]

        }
        // END   2016/06/03 S.Fujita [QC#9452,MOD]

        // START 2016/07/20 S.Fujita [QC#10148,ADD]
        if (ACCT_DIST_EDIT.equals(bizMsg.xxRadioBtn_C.getValue())) {
            NFCL3000CommonLogic.copyCtoF(bizMsg, globalMsg);
        }
        // END   2016/07/20 S.Fujita [QC#10148,ADD]
    }

    /**
     * doProcess_NFCL3000Scrn00_CMN_Submit
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;

        NFCL3000CommonLogic.clearSubmit(cMsg, sMsg);
        doProcess_NFCL3000Scrn00_Search(cMsg, sMsg);

        bizMsg.xxDplyTab.setValue(TAB_Accounting);
    }

    /**
     * doProcess_NFCL3000Scrn00_CMN_Clear
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_CMN_Clear(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;
        NFCL3000CommonLogic.clearNew(cMsg, sMsg);

        NFCL3000CommonLogic.createPulldownListInvTp(bizMsg);
        NFCL3000CommonLogic.createPulldownListDsInvTp(bizMsg);
        NFCL3000CommonLogic.getDsInvTpInfo(bizMsg);
        // START 2016/06/03 S.Fujita [QC#9452,ADD]
        NFCL3000CommonLogic.createPulldownListPmtTermCashDisc(bizMsg);
        NFCL3000CommonLogic.calcNetDueDate(bizMsg);
        // END   2016/06/03 S.Fujita [QC#9452,ADD]
    }

    /**
     * doProcess_NFCL3000Scrn00_CMN_Reset
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_CMN_Reset(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;
        NFCL3000SMsg globalMsg = (NFCL3000SMsg) sMsg;

        doProcess_NFCL3000Scrn00_Search(cMsg, sMsg);

        // START 2016/09/08 S.Fujita [QC#14115,ADD]
        NFCL3000CommonLogic.createPulldownListInvTp(bizMsg);
        NFCL3000CommonLogic.createPulldownListDsInvTp(bizMsg);
        NFCL3000CommonLogic.createPulldownListPmtTermCashDisc(bizMsg);
        NFCL3000CommonLogic.createPulldownListDfrdAcctgRule(bizMsg);
        // END   2016/09/08 S.Fujita [QC#14115,ADD]

        // START 2016/08/22 S.Fujita [QC#12579,ADD]
        bizMsg.xxRadioBtn_C.setValue(ACCT_DIST_EDIT);
        bizMsg.xxRadioBtn_CB.setValue(ACCT_DIST_EDIT);
        bizMsg.xxPgFlg_C.clear();
        NFCL3000CommonLogic.setAcctEditMode(bizMsg, globalMsg);
        // END   2016/08/22 S.Fujita [QC#12579,ADD]
    }

    /**
     * doProcess_NFCL3000Scrn00_Calc
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_Calc(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;
        // Tab Check
        if(bizMsg.xxDplyTab.getValue().equals(TAB_Line)){
            if(!NFCL3000CommonLogic.calcAmount(bizMsg, false)) {
                return;
            }
            if(!NFCL3000CommonLogic.check_LineTAB(bizMsg)) {
                return;
            }
        } else if(bizMsg.xxDplyTab.getValue().equals(TAB_SalesCredit)) {
            if(NFCL3000CommonLogic.check_SlsCrTAB(bizMsg)) {
                return;
            }
        } else if(bizMsg.xxDplyTab.getValue().equals(TAB_Accounting)) {
            if(!NFCL3000CommonLogic.calcAmount(bizMsg, false)) {
                return;
            }
            if(NFCL3000CommonLogic.check_AccountingTAB(bizMsg)) {
                return;
            }
        } else {
            if(!NFCL3000CommonLogic.calcAmount(bizMsg, false)) {
                return;
            }
        }
        NFCL3000CommonLogic.setCheckBox(bizMsg);
    }

    /**
     * doProcess_NFCL3000Scrn00_Select_DebitCreditType
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_Select_DebitCreditType(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;
        int idx = bizMsg.xxCellIdx.getValueInt();

        bizMsg.C.no(idx).invBolLineNum_C1.setValue(INIT_INV_BOL_LINE_NUM);
        bizMsg.C.no(idx).glDt_C1.setValue(bizMsg.procDt.getValue());

        if(bizMsg.C.no(idx).drCrTpCd_C1.getValue().equals(DR_CR_TP_DEBIT)) {
            bizMsg.C.no(idx).jrnlDealAmt_C1.setValue(BigDecimal.ZERO);
            bizMsg.C.no(idx).jrnlDealAmt_C2.clear();
        } else {
            bizMsg.C.no(idx).jrnlDealAmt_C1.clear();
            bizMsg.C.no(idx).jrnlDealAmt_C2.setValue(BigDecimal.ZERO);
        }
        bizMsg.C.no(idx).xxPgFlg_C1.setValue(ZYPConstant.FLG_ON_Y);
    }

    /**
     * doProcess_NFCL3000Scrn00_CMN_Download
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_CMN_Download(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;

        NFCL3000CommonLogic.setCodeName(bizMsg);

        String csvFileName = null;

        if(bizMsg.xxDplyTab.getValue().equals(TAB_Line)) {
            csvFileName = CSV_DOWNLOAD_FILE + "_" + CSV_DOWNLOAD_INV_LINE ;
        } else if(bizMsg.xxDplyTab.getValue().equals(TAB_SalesCredit)) {
            csvFileName = CSV_DOWNLOAD_FILE + "_" + CSV_DOWNLOAD_INV_SALES_CREDIT ;
        } else if(bizMsg.xxDplyTab.getValue().equals(TAB_Accounting)) {
            csvFileName = CSV_DOWNLOAD_FILE + "_" + CSV_DOWNLOAD_INV_ACCOUNTING ;
        } else if(bizMsg.xxDplyTab.getValue().equals(TAB_BOL)) {
            csvFileName = CSV_DOWNLOAD_FILE + "_" + CSV_DOWNLOAD_INV_BOL ;
        }

        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(csvFileName), ".csv");
        String csvOutTempPath = bizMsg.xxFileData.getTempFilePath();

        // Line
        if (TAB_Line.equals(bizMsg.xxDplyTab.getValue())) {
            NFCL3000F00FMsg fMsg = new NFCL3000F00FMsg();
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(csvOutTempPath, fMsg);

            // START 2018/08/13 Y.Matsui [QC#26968,MOD]
            int[] columnOrder = ZYPGUITableColumn.getColOrder(bizMsg);
            fMsg.setItemOrder(columnOrder);
            fMsg.addExclusionItem("xxChkBox_A1");

            if (bizMsg.xxDplyTab_H1.getValue().equals(DIS_PAT_VISIBILITY_ERROR)) {
                csvOutFile.writeHeader(LINE_HEADER, fMsg, columnOrder);
            } else {
                csvOutFile.writeHeader(LINE_HEADER_GENERAL, fMsg, columnOrder);
            }
            // END   2018/08/13 Y.Matsui [QC#26968,MOD]

            for(int i = 0 ; i < bizMsg.A.getValidCount(); i++) {
                EZDMsg.copy(bizMsg.A.no(i), null, fMsg, null);

                // START 2016/10/12 S.Fujita [QC#13659,ADD]
                ZYPEZDItemValueSetter.setValue(fMsg.dealNetUnitPrcAmt_A1, convertAmtFormat(bizMsg.A.no(i).dealNetUnitPrcAmt_A1.getValue()));
                ZYPEZDItemValueSetter.setValue(fMsg.invLineDealNetAmt_A1, convertAmtFormat(bizMsg.A.no(i).invLineDealNetAmt_A1.getValue()));
                ZYPEZDItemValueSetter.setValue(fMsg.invLineDealTaxAmt_A1, convertAmtFormat(bizMsg.A.no(i).invLineDealTaxAmt_A1.getValue()));
                ZYPEZDItemValueSetter.setValue(fMsg.xxTotAmt_A1, convertAmtFormat(bizMsg.A.no(i).xxTotAmt_A1.getValue()));
                ZYPEZDItemValueSetter.setValue(fMsg.funcCsmpCrAmt_A1, convertAmtFormat(bizMsg.A.no(i).funcCsmpCrAmt_A1.getValue()));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_OD, convertDateFormat(bizMsg.A.no(i).ordDt_A1.getValue()));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_SD, convertDateFormat(bizMsg.A.no(i).shipDt_A1.getValue()));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_BF, convertDateFormat(bizMsg.A.no(i).bllgPerFromDt_A1.getValue()));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_BT, convertDateFormat(bizMsg.A.no(i).bllgPerThruDt_A1.getValue()));
                // END   2016/10/12 S.Fujita [QC#13659,ADD]

                // START 2018/09/28 T.Ogura [QC#28526,ADD]
                if (ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).xxDplyCtrlFlg_A1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(fMsg.invDplyQty_A1, bizMsg.A.no(i).invDplyQty_A1.getValue());
                } else if (ZYPConstant.FLG_OFF_N.equals(bizMsg.A.no(i).xxDplyCtrlFlg_A1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(fMsg.invDplyQty_A1, bizMsg.A.no(i).shipQty_A1.getValue());
                } else {
                    ZYPEZDItemValueSetter.setValue(fMsg.invDplyQty_A1, bizMsg.A.no(i).ordCustUomQty_A1.getValue());
                }
                // END   2018/09/28 T.Ogura [QC#28526,ADD]

                csvOutFile.write();
            }
            csvOutFile.close();

        // Sales Credit
        } else if (TAB_SalesCredit.equals(bizMsg.xxDplyTab.getValue())) {
            NFCL3000F01FMsg fMsg = new NFCL3000F01FMsg();
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(csvOutTempPath, fMsg);

            // START 2018/08/13 Y.Matsui [QC#26968,MOD]
            int[] columnOrder = ZYPGUITableColumn.getColOrder(bizMsg);
            fMsg.setItemOrder(columnOrder);
            fMsg.addExclusionItem("xxChkBox_B1");

            if (bizMsg.xxDplyTab_H1.getValue().equals(DIS_PAT_VISIBILITY_ERROR)) {
                csvOutFile.writeHeader(SALES_CREDIT_HEADER, fMsg, columnOrder);
            } else {
                csvOutFile.writeHeader(SALES_CREDIT_HEADER_GENERAL, fMsg, columnOrder);
            }
            // END   2018/08/13 Y.Matsui [QC#26968,MOD]

            for(int i = 0 ; i < bizMsg.B.getValidCount(); i++) {
                EZDMsg.copy(bizMsg.B.no(i), null, fMsg, null);

                // START 2016/10/12 S.Fujita [QC#13659,ADD]
                ZYPEZDItemValueSetter.setValue(fMsg.slsRepCrPct_B2, convertAmtFormat(bizMsg.B.no(i).slsRepCrPct_B2.getValue()));
                ZYPEZDItemValueSetter.setValue(fMsg.dealSlsCrAmt_B1, convertAmtFormat(bizMsg.B.no(i).dealSlsCrAmt_B1.getValue()));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_DD, convertDateFormat(bizMsg.B.no(i).durnStartDt_B1.getValue()));
                // END   2016/10/12 S.Fujita [QC#13659,ADD]

                // START 2018/08/13 Y.Matsui [QC#26968,ADD]
                ZYPEZDItemValueSetter.setValue(fMsg.dealDfrdBalAmt_B1, convertAmtFormat(bizMsg.B.no(i).dealDfrdBalAmt_B1.getValue()));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_FD, convertDateFormat(bizMsg.B.no(i).firstRevRecogDt_B1.getValue()));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_ND, convertDateFormat(bizMsg.B.no(i).nextRevRecogDt_B1.getValue()));
                ZYPEZDItemValueSetter.setValue(fMsg.dealSchdRevAmt_B1, convertAmtFormat(bizMsg.B.no(i).dealSchdRevAmt_B1.getValue()));
                // END   2018/08/13 Y.Matsui [QC#26968,MOD]

                csvOutFile.write();
            }
            csvOutFile.close();

        // Accounting
        } else if (TAB_Accounting.equals(bizMsg.xxDplyTab.getValue())) {
            NFCL3000F02FMsg fMsg = new NFCL3000F02FMsg();
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(csvOutTempPath, fMsg);

            // START 2018/08/13 Y.Matsui [QC#26968,MOD]
            int[] columnOrder = ZYPGUITableColumn.getColOrder(bizMsg);
            fMsg.setItemOrder(columnOrder);
            fMsg.addExclusionItem("xxChkBox_C1");

            if (bizMsg.xxDplyTab_H1.getValue().equals(DIS_PAT_VISIBILITY_ERROR)) {
                csvOutFile.writeHeader(ACCOUNTING_HEADER, fMsg, columnOrder);
            } else {
                csvOutFile.writeHeader(ACCOUNTING_HEADER_GENERAL, fMsg, columnOrder);
            }
            // END   2018/08/13 Y.Matsui [QC#26968,MOD]

            for(int i = 0 ; i < bizMsg.C.getValidCount(); i++) {
                EZDMsg.copy(bizMsg.C.no(i), null, fMsg, null);

                // START 2016/10/12 S.Fujita [QC#13659,ADD]
                ZYPEZDItemValueSetter.setValue(fMsg.jrnlDealAmt_C1, convertAmtFormat(bizMsg.C.no(i).jrnlDealAmt_C1.getValue()));
                ZYPEZDItemValueSetter.setValue(fMsg.jrnlDealAmt_C2, convertAmtFormat(bizMsg.C.no(i).jrnlDealAmt_C2.getValue()));
                ZYPEZDItemValueSetter.setValue(fMsg.ajeInvAcctDistPct_C1, convertAmtFormat(bizMsg.C.no(i).ajeInvAcctDistPct_C1.getValue()));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_GD, convertDateFormat(bizMsg.C.no(i).glDt_C1.getValue()));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_JD, convertDateFormat(bizMsg.C.no(i).jrnlCratDt_C1.getValue()));
                // END   2016/10/12 S.Fujita [QC#13659,ADD]

                csvOutFile.write();
            }
            csvOutFile.close();

        // BOL
        } else if (TAB_BOL.equals(bizMsg.xxDplyTab.getValue())) {
            NFCL3000F03FMsg fMsg = new NFCL3000F03FMsg();
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(csvOutTempPath, fMsg);

            // START 2018/08/13 Y.Matsui [QC#26968,MOD]
            int[] columnOrder = ZYPGUITableColumn.getColOrder(bizMsg);
            fMsg.setItemOrder(columnOrder);
            fMsg.addExclusionItem("xxChkBox_D1");

            if (bizMsg.xxDplyTab_H1.getValue().equals(DIS_PAT_VISIBILITY_ERROR)) {
                csvOutFile.writeHeader(BOL_HEADER, fMsg, columnOrder);
            } else {
                csvOutFile.writeHeader(BOL_HEADER_GENERAL, fMsg, columnOrder);
            }
            // END   2018/08/13 Y.Matsui [QC#26968,MOD]

            for(int i = 0 ; i < bizMsg.D.getValidCount(); i++) {
                EZDMsg.copy(bizMsg.D.no(i), null, fMsg, null);

                // START 2016/10/12 S.Fujita [QC#13659,ADD]
                // START 2018/05/22 Y.Matsui [QC#21841,DEL]
                // ZYPEZDItemValueSetter.setValue(fMsg.shipDealFrtAmt_D1, convertAmtFormat(bizMsg.D.no(i).shipDealFrtAmt_D1.getValue()));
                // ZYPEZDItemValueSetter.setValue(fMsg.frtDealTaxAmt_D1, convertAmtFormat(bizMsg.D.no(i).frtDealTaxAmt_D1.getValue()));
                // ZYPEZDItemValueSetter.setValue(fMsg.frtTaxPct_D1, convertAmtFormat(bizMsg.D.no(i).frtTaxPct_D1.getValue()));
                // END   2018/05/22 Y.Matsui [QC#21841,DEL]
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_SD, convertDateFormat(bizMsg.D.no(i).shipDt_D1.getValue()));
                // END   2016/10/12 S.Fujita [QC#13659,ADD]

                csvOutFile.write();
            }
            csvOutFile.close();
        }
    }

    /**
     * doProcess_NFCL3000Scrn00_OnBlur_SalesRep
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_OnBlur_SalesRep(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;

        if(!ZYPCommonFunc.hasValue(bizMsg.slsRepTocNm_H1.getValue())) {
            return;
        }
        if(!NFCL3000CommonLogic.searchSalesRep(bizMsg)) {
            return;
        }
    }

    /**
     * doProcess_NFCL3000Scrn00_OnBlur_UnitPrice_A
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_OnBlur_UnitPrice_A(EZDCMsg cMsg, EZDSMsg sMsg) {
        // do nothing.
    }

    /**
     * doProcess_NFCL3000Scrn00_SalesRepName
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_SalesRepName(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;

        // START 2016/07/14 S.Fujita [QC#11157,MOD]
//        if(!ZYPCommonFunc.hasValue(bizMsg.slsRepTocCd_H1.getValue())) {
        if(!ZYPCommonFunc.hasValue(bizMsg.psnNum_H1.getValue())) {
        // END   2016/07/14 S.Fujita [QC#11157,MOD]
            return;
        }
        NFCL3000CommonLogic.searchSalesRep(bizMsg);
    }

    /**
     * doProcess_NFCL3000Scrn00_WarehouseName
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_WarehouseName(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;

        if(!ZYPCommonFunc.hasValue(bizMsg.shipFromInvtyLocCd_H4.getValue())) {
            return;
        }
        NFCL3000CommonLogic.searchWarehouse(bizMsg);
    }

    /**
     * doProcess_NFCL3000Scrn00_Select_InvLineNum_B
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_Select_InvLineNum_B(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;
        int idx = bizMsg.xxCellIdx.getValueInt();

        int no = 0;
        for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if(bizMsg.A.no(i).invLineNum_A1.getValue().equals(bizMsg.B.no(idx).invLineNum_B1.getValue())) {
                no = i;
                break;
            }
        }

        bizMsg.B.no(idx).clear();
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(idx).invBolLineNum_B1, bizMsg.A.no(no).invBolLineNum_A1.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(idx).invLineNum_B1, bizMsg.A.no(no).invLineNum_A1.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(idx).invLineSubNum_B1, bizMsg.A.no(no).invLineSubNum_A1.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(idx).invTrxLineSubNum_B1, bizMsg.A.no(no).invLineSubTrxNum_A1.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(idx).invLineSplPct_B2, new BigDecimal(100));
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(idx).mdseCd_B1, bizMsg.A.no(no).mdseCd_A1.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(idx).slsRepTocCd_B1, bizMsg.slsRepTocCd_H1.getValue());
        // START 2016/07/14 S.Fujita [QC#11157,ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(idx).psnNum_B1, bizMsg.psnNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(idx).coaBrNm_B1, bizMsg.coaBrNm_H1.getValue());
        // END   2016/07/14 S.Fujita [QC#11157,ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(idx).tocNm_B1, bizMsg.slsRepTocNm_H1.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(idx).dealSlsCrAmt_B1, bizMsg.A.no(no).invLineDealNetAmt_A1.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(idx).slsRepCrPct_B2, new BigDecimal(100));
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(idx).xxTotAmt_B1, bizMsg.A.no(no).invLineDealNetAmt_A1.getValue());

        NFCL3000CommonLogic.setSalesCreditLineNum_B(bizMsg);

        if(!NFCL3000CommonLogic.check_SalesCredit(bizMsg, false)) {
            bizMsg.xxDplyTab.setValue(TAB_SalesCredit);
            for(int i = 0; i < bizMsg.B.getValidCount(); i++) {
              //---- start 2016/05/06 QC#7860
                //bizMsg.B.no(i).invLineNum_B1.setErrorInfo(1, "NFCM0517E", new String[]{"Line#"});
                bizMsg.B.no(i).invLineNum_B1.setErrorInfo(1, NOT_ASSIGNED_ERROR, new String[]{PRM_LINE_S, PRM_SLS_CR});
                //---- end 2016/05/06
            }
        }
        NFCL3000CommonLogic.calcAmount_SlsCr(bizMsg);
    }

    /**
     * doProcess_NFCL3000Scrn00_Select_InvBolLineNum_A
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_Select_InvBolLineNum_A(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;

        // START 2016/06/03 S.Fujita [QC#9157,ADD]
        // Numbering Line# for each BOL Line#.
        NFCL3000CommonLogic.setLineNumber(bizMsg);
        // END   2016/06/03 S.Fujita [QC#9157,ADD]

        // START 2016/09/12 S.Fujita [QC#14112,DEL]
//        int idx = bizMsg.xxCellIdx.getValueInt();
//        if(bizMsg.B.getValidCount() < 1) {
//            return ;
//        }
//
//        // Re-set Sales Credit TAB invBolLineNum
//        String invLineNum = bizMsg.A.no(idx).invLineNum_A1.getValue();
//        String invBolLineNum = bizMsg.A.no(idx).invBolLineNum_A1.getValue();
//
//        for(int i = 0; i < bizMsg.B.getValidCount(); i++) {
//            if(bizMsg.B.no(i).invLineNum_B1.getValue().equals(invLineNum)) {
//                bizMsg.B.no(i).invBolLineNum_B1.setValue(invBolLineNum);
//            }
//        }
        // END   2016/09/12 S.Fujita [QC#14112,DEL]

        NFCL3000CommonLogic.setLineAmt(bizMsg);

        // BOL# check
        if(!NFCL3000CommonLogic.check_BOL(bizMsg, false)) {
            bizMsg.xxDplyTab.setValue(TAB_Line);
            for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
              //---- start 2016/05/06 QC#7860
                //bizMsg.A.no(i).invBolLineNum_A1.setErrorInfo(1, "NFCM0517E", new String[]{"BOL Line#"});
                bizMsg.A.no(i).invBolLineNum_A1.setErrorInfo(1, NOT_ASSIGNED_ERROR, new String[]{PRM_BOL_S, PRM_LINE});
                //---- end 2016/05/06
            }
        }
        // START 2019/06/05 S.Takami [QC#50683,ADD]
        if (NFCL3000CommonLogic.isDsInvTpFsr(bizMsg)) {
            int lineIdx = bizMsg.xxCellIdx.getValueInt();
            String currentStatus = bizMsg.A.no(lineIdx).xxYesNoCd_A1.getValue();
            setFsrCommonData(bizMsg, lineIdx);
            if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, currentStatus) //
                    && !S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, bizMsg.A.no(lineIdx).xxYesNoCd_A1.getValue())) {
                resetFsrCommonData(bizMsg);
            }
        }
        // END 2019/06/05 S.Takami [QC#50683,ADD]
    }

    // START 2016/06/03 S.Fujita [QC#9157,ADD]
    /**
     * doProcess_NFCL3000Scrn00_Select_InvBolLineNum_B
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_Select_InvBolLineNum_B(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;
        int idx = bizMsg.xxCellIdx.getValueInt();

        String invBolLineNum = bizMsg.B.no(idx).invBolLineNum_B1.getValue();
        bizMsg.B.no(idx).clear();
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(idx).invBolLineNum_B1, invBolLineNum);

        NFCL3000CommonLogic.setSalesCreditLineNum_B(bizMsg);
    }
    // END   2016/06/03 S.Fujita [QC#9157,ADD]

    /**
     * doProcess_NFCL3000Scrn00_AdjustInvoice
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_AdjustInvoice(EZDCMsg cMsg, EZDSMsg sMsg) {
        // do nothing.
    }

    /**
     * doProcess_NFCL3000Scrn00_Installments
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_Installments(EZDCMsg cMsg, EZDSMsg sMsg) {
        // do nothing.
    }

    /**
     * doProcess_NFCL3000Scrn00_MaterDetailes
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_MaterDetailes(EZDCMsg cMsg, EZDSMsg sMsg) {
        // do nothing.
    }

    /**
     * doProcess_NFCL3000Scrn00_Activity
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_Activity(EZDCMsg cMsg, EZDSMsg sMsg) {
        // do nothing.
    }

    // START 2017/12/25 E.Kameishi [QC#20312,ADD]
    /**
     * doProcess_NFCL3000Scrn00_CreditRebill
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_CreditRebill(EZDCMsg cMsg, EZDSMsg sMsg) {
        // do nothing.
    }
    // END 2017/12/25 E.Kameishi [QC#20312,ADD]

    /**
     * doProcess_NFCL3000Scrn00_CreditCard
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_CreditCard(EZDCMsg cMsg, EZDSMsg sMsg) {
        // do nothing.
    }

    /**
     * doProcess_NFCL3000Scrn00_Select_PaymentMethod
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_Select_PaymentMethod(EZDCMsg cMsg, EZDSMsg sMsg) {
        // do nothing.
    }
    //
    /**
     * 6000195
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_PrintedInvoice(EZDCMsg cMsg, EZDSMsg sMsg) {
        // do nothing.
    }

    /**
     * doProcess_NFCL3000Scrn00_OnChangeRadio_C
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_OnChangeRadio_C(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;
        NFCL3000SMsg globalMsg = (NFCL3000SMsg) sMsg;

        if (bizMsg.C.getValidCount() < 1) {
            bizMsg.setMessageInfo("NZZM0000E");
            return;
        }

        // move from edit to summary 
        if (ACCT_DIST_EDIT.equals(bizMsg.xxRadioBtn_CB.getValue()) && ACCT_DIST_SMRY.equals(bizMsg.xxRadioBtn_C.getValue())) {
            NFCL3000CommonLogic.copyCtoF(bizMsg, globalMsg);
            bizMsg.xxPgFlg_C.setValue(ZYPConstant.FLG_ON_Y);
            NFCL3000CommonLogic.setSummaryMode(bizMsg, globalMsg, false);
            bizMsg.xxRadioBtn_CB.setValue(ACCT_DIST_SMRY);

        } else if (ACCT_DIST_SMRY.equals(bizMsg.xxRadioBtn_CB.getValue()) && ACCT_DIST_EDIT.equals(bizMsg.xxRadioBtn_C.getValue())) { // move from summary to edit
            // START 2016/07/20 S.Fujita [QC#10148,MOD]
//            NFCL3000CommonLogic.setEditMode(bizMsg, globalMsg);
            NFCL3000CommonLogic.setAcctEditMode(bizMsg, globalMsg);
            bizMsg.xxRadioBtn_CB.setValue(ACCT_DIST_EDIT);
            // END   2016/07/20 S.Fujita [QC#10148,MOD]
        } else {  // move from summary to summary
            bizMsg.xxRadioBtn_CB.setValue(ACCT_DIST_SMRY);
        }
        NFCL3000CommonLogic.calcAmount_Acct_DrCr(bizMsg, false);

        // START 2016/06/20 S.Fujita [QC#9454,DEL]
//      NFCL3000CommonLogic.checkAmount_AcctDist_DrCr_Totoal(bizMsg);
        // END   2016/06/20 S.Fujita [QC#9454,DEL]

        if (!ACCT_DIST_SMRY.equals(bizMsg.xxRadioBtn_C.getValue())) {
            // SC# check
            if(!NFCL3000CommonLogic.check_AccountDist(bizMsg, false)) {
                bizMsg.xxDplyTab.setValue(TAB_Accounting);
                for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
                    //---- start 2016/05/06 QC#7860
                    //bizMsg.C.no(i).xxLineNum_C1.setErrorInfo(1, "NFCM0517E", new String[]{"SC#"});
                    bizMsg.C.no(i).xxLineNum_C1.setErrorInfo(1, NOT_ASSIGNED_ERROR, new String[]{PRM_SLS_CR_S, PRM_ACCT_DIST});
                    //---- end 2016/05/06
                }
            }

            // START 2016/06/20 S.Fujita [QC#9454,DEL]
//          if(!NFCL3000CommonLogic.checkAmount_AcctDist_DrCr_Line(bizMsg)) {
//              return;
//          }
//          if(!NFCL3000CommonLogic.checkAmount_AcctDist_DrCr_Line_SalesCredit(bizMsg)) {
//              return;
//          }
//          if(!NFCL3000CommonLogic.checkAmount_AcctDist_DrCr_Totoal(bizMsg)) {
//              return;
//          }
            // END   2016/06/20 S.Fujita [QC#9454,DEL]
        }
    }

    /**
     * doProcess_NFCL3000Scrn00_Select_SalesCreditLineNum_C
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_Select_SalesCreditLineNum_C(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;

        int idx = bizMsg.xxCellIdx.getValueInt();
        String xxLineNum = bizMsg.C.no(idx).xxLineNum_C1.getValue();
        for(int i = 0; i < bizMsg.B.getValidCount(); i++) {
            if(xxLineNum.equals(bizMsg.B.no(i).xxLineNum_B1.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).invBolLineNum_C1, bizMsg.B.no(i).invBolLineNum_B1.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).invLineNum_C1, bizMsg.B.no(i).invLineNum_B1.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).invLineSubNum_C1, bizMsg.B.no(i).invLineSubNum_B1.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).invLineSubTrxNum_C1, bizMsg.B.no(i).invTrxLineSubNum_B1.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).glDt_C1, bizMsg.procDt.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).jrnlDealAmt_C1, bizMsg.B.no(i).dealSlsCrAmt_B1.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).dsInvSlsCrPk_C1, bizMsg.B.no(i).dsInvSlsCrPk_B1);
 
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx+1).xxLineNum_C1, bizMsg.B.no(i).xxLineNum_B1.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx+1).invBolLineNum_C1, bizMsg.B.no(i).invBolLineNum_B1.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx+1).invLineNum_C1, bizMsg.B.no(i).invLineNum_B1.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx+1).invLineSubNum_C1, bizMsg.B.no(i).invLineSubNum_B1.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx+1).invLineSubTrxNum_C1, bizMsg.B.no(i).invTrxLineSubNum_B1.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx+1).glDt_C1, bizMsg.procDt.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx+1).jrnlDealAmt_C2, bizMsg.B.no(i).dealSlsCrAmt_B1.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx+1).dsInvSlsCrPk_C1, bizMsg.B.no(i).dsInvSlsCrPk_B1.getValue());
                
                break;
            }
        }
    }
    // START 2016/05/24 S.Fujita [QC#8522,ADD]
    /**
     * doProcess_NFCL3000Scrn00_Select_DfrdAcctgRule_B
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_Select_DfrdAcctgRule_B(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;
        int idx = bizMsg.xxCellIdx.getValueInt();

        if (!ZYPCommonFunc.hasValue(bizMsg.B.no(idx).dfrdAcctgRuleCd_B1.getValue())) {
            return;
        }

        DFRD_ACCTG_RULETMsg outMsg = NFCL3000CommonLogic.getDfrdAcctgrule(bizMsg, bizMsg.B.no(idx).dfrdAcctgRuleCd_B1.getValue());
        if (outMsg == null) {
            return;
        }
        bizMsg.B.no(idx).dfrdAcctgRuleDurnAot_B1.clear();
        bizMsg.B.no(idx).dfrdRevFlg_B1.clear();
        bizMsg.B.no(idx).durnStartDt_B1.clear();
        bizMsg.B.no(idx).fixDurnFlg_B1.clear();

        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(idx).dfrdRevFlg_B1, outMsg.dfrdRevFlg.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(idx).fixDurnFlg_B1, outMsg.fixDurnFlg.getValue());
        if (bizMsg.B.no(idx).dfrdRevFlg_B1.getValue().equals(ZYPConstant.FLG_ON_Y)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(idx).dfrdAcctgRuleDurnAot_B1, outMsg.dfrdAcctgRuleDurnAot.getValue());
            bizMsg.B.no(idx).durnStartDt_B1.setValue(bizMsg.invDt_H1.getValue());
        }
    }
    // END   2016/05/24 S.Fujita [QC#8522,ADD]

    // START 2016/06/03 S.Fujita [QC#9157,ADD]
    /**
     * doProcess_NFCL3000Scrn00_OnBlur_invLineNum_B
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_OnBlur_invLineNum_B(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;
        int idx = bizMsg.xxCellIdx.getValueInt();

        // BOL Line# Line# Combination Check
        if(!ZYPCommonFunc.hasValue(bizMsg.B.no(idx).invLineNum_B1.getValue())) {
           return;
        }

        String lineNum = NFCL3000CommonLogic.setNumStr(bizMsg.B.no(idx).invLineNum_B1.getValue(), 5);
        if(lineNum == null) {
            bizMsg.B.no(idx).invLineNum_B1.setErrorInfo(1, INVALID_ERROR, new String[]{PRM_SLS_CR_LINE_NUMBERE});
            return;
        }

        bizMsg.B.no(idx).invLineNum_B1.setValue(lineNum);

        if(!NFCL3000CommonLogic.checkLine_SlsCr_Idx(bizMsg, idx)) {
            return;
        }

        // get CoaBrNm
        NFCL3000CommonLogic.getCoaBrNm(bizMsg);

        NFCL3000CommonLogic.setSlsCr(bizMsg, idx);
    }

//TODO C.置き換え不要
    /**
     * doProcess_NFCL3000Scrn00_OnBlur_SalesCreditLineNum_C
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_OnBlur_SalesCreditLineNum_C(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;
        int idx = bizMsg.xxCellIdx.getValueInt();

        if (!ZYPCommonFunc.hasValue(bizMsg.C.no(idx).xxLineNum_C1.getValue())) {
            return;
        }

        String lineNum = NFCL3000CommonLogic.setNumStr(bizMsg.C.no(idx).xxLineNum_C1.getValue(), 5);
        if (lineNum == null) {
            bizMsg.C.no(idx).xxLineNum_C1.setErrorInfo(1, INVALID_ERROR, new String[]{PRM_SLS_CR_LINE_NUMBERE});
            return;
        }

        bizMsg.C.no(idx).xxLineNum_C1.setValue(lineNum);
        // START 2016/07/20 S.Fujita [QC#10148,DEL]
//        bizMsg.C.no(idx+1).xxLineNum_C1.setValue(lineNum);
        // END   2016/07/20 S.Fujita [QC#10148,DEL]

//TODO B.SMsgに置き換える
//TODO C.置き換え不要
        if (!NFCL3000CommonLogic.checkLine_AccountDist_Idx(bizMsg.B, bizMsg.C.no(idx))) {
            return;
        }

//TODO B.SMsgに置き換える
//TODO C.置き換え不要
        NFCL3000CommonLogic.setAccountDist(bizMsg.B, bizMsg.C.no(idx), bizMsg.procDt.getValue());

    }

    /**
     * doProcess_NFCL3000Scrn00_ShipToName_D
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_ShipToName_D(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;
        int idx = bizMsg.xxCellIdx.getValueInt();

        String dsAcctNum = bizMsg.D.no(idx).shipToCustAcctCd_D1.getValue();
        if (ZYPCommonFunc.hasValue(dsAcctNum)) {
            String dsAcctNm = NFCL3000CommonLogic.getDsAcctNm(bizMsg.glblCmpyCd.getValue(), dsAcctNum);
            if (dsAcctNm == null) {
                bizMsg.D.no(idx).shipToCustAcctCd_D1.setErrorInfo(1, "NFCM0763E");
                bizMsg.D.no(idx).shipToCustAcctNm_D1.clear();
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).shipToCustAcctNm_D1, dsAcctNm);
            }
        } else {
            bizMsg.D.no(idx).shipToCustAcctNm_D1.clear();
        }
    }
    // END   2016/06/03 S.Fujita [QC#9157,ADD]

    // START 2016/07/04 S.Fujita [QC#10742,ADD]
    /**
     * doProcess_NFCL3000Scrn00_GlCombnSearch_C
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_GlCombnSearch_C(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;
        int idx = bizMsg.xxCellIdx.getValueInt();

        // check and set 9 Segment
        // START 2016/07/20 S.Fujita [QC#10148,MOD]
//        if(!NFCL3000CommonLogic.SplitCOA9SegString(bizMsg, idx)) {
//TODO C.置き換え不要。
        if (!NFCL3000CommonLogic.splitCOA9SegString_PopUp(bizMsg.C.no(idx))) {
        // END   2016/07/20 S.Fujita [QC#10148,MOD]
            return;
        }
    }

    /**
     * doProcess_NFCL3000Scrn00_ShipToCustName
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_ShipToCustName(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;
        String dsAcctNum = bizMsg.shipToCustAcctCd_H2.getValue();
        if (ZYPCommonFunc.hasValue(dsAcctNum)) {
            String dsAcctNm = NFCL3000CommonLogic.getDsAcctNm(bizMsg.glblCmpyCd.getValue(), dsAcctNum);
            if (dsAcctNm == null) {
                bizMsg.shipToCustAcctCd_H2.setErrorInfo(1, "NFCM0763E");
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctNm_H2, dsAcctNm);
            }
        } else {
            bizMsg.shipToCustAcctNm_H2.clear();
        }
    }

    /**
     * doProcess_NFCL3000Scrn00_BillToCustName
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_BillToCustName(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;
        String dsAcctNum = bizMsg.billToCustAcctCd_H3.getValue();
        String dsAcctNm = NFCL3000CommonLogic.getDsAcctNm(bizMsg.glblCmpyCd.getValue(), dsAcctNum);
        if (dsAcctNm == null) {
            bizMsg.billToCustAcctCd_H3.setErrorInfo(1, "NFCM0763E");
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctNm_H3, dsAcctNm);
        }
    }

    /**
     * doProcess_NFCL3000Scrn00_ShipToLocName
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_ShipToLocName(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;

        NFCL3000CommonLogic.clearShipToAddr(bizMsg);
        NFCL3000CommonLogic.searchShipToAddr(bizMsg);
        NFCL3000CommonLogic.setAddress(bizMsg);
    }

    /**
     * doProcess_NFCL3000Scrn00_BillToLocName
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_BillToLocName(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;

        NFCL3000CommonLogic.clearBillToAddr(bizMsg);
        NFCL3000CommonLogic.searchBillToAddr(bizMsg);
        NFCL3000CommonLogic.setAddress(bizMsg);
        // START 2018/07/19 E.Kameishi [QC#27007,ADD]
        NFCL3000CommonLogic.setPmtTerm(bizMsg);
        // END 2018/07/19 E.Kameishi [QC#27007,ADD]
    }

    /**
     * doProcess_NFCL3000Scrn00_ShipToLocName_D
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_ShipToLocName_D(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;

        int idx = bizMsg.xxCellIdx.getValueInt();
        NFCL3000CommonLogic.clearShipToAddr_D(bizMsg);
        NFCL3000CommonLogic.searchShipToAddr_D(bizMsg, idx);
    }

    /**
     * TBLColumnSort Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFCL3000Scrn00_TBLColumnSort(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;
        NFCL3000SMsg globalMsg = (NFCL3000SMsg) sMsg;

        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy = bizMsg.xxSortOrdByTxt.getValue();

        if ("A".equals(sortTblNm)) {

            S21SortTarget sortTarget = new S21SortTarget(bizMsg.A, bizMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, bizMsg.A.getValidCount());
        }
    }
    // END   2016/07/04 S.Fujita [QC#10742,ADD]

    // START 2016/10/12 S.Fujita [QC#13659,ADD]
    /**
     * convertAmtFormat
     * @param data BigDecimal
     * @return String
     */
    private static BigDecimal convertAmtFormat(BigDecimal data) {
        if (hasValue(data)) {
            data = data.setScale(2);
        }
        return data;
    }

    /**
     * convertDateFormat
     * @param date String
     * @return String
     */
    private static String convertDateFormat(String date) {
        if (hasValue(date)) {
            date = ZYPDateUtil.formatEzd8ToDisp(date);
        }
        return date;
    }
    // END   2016/10/12 S.Fujita [QC#13659,ADD]
    // START 2018/07/25 S.Ohki [QC#26968,ADD]
    /**
     * CMN_ColClear Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFCL3000Scrn00_CMN_ColClear(EZDCMsg cMsg, EZDSMsg sMsg) {
        // do nothing.
    }

    /**
     * CMN_ColSave Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFCL3000Scrn00_CMN_ColSave(EZDCMsg cMsg, EZDSMsg sMsg) {
    	// do nothing.
    }
    // END   2018/07/25 S.Ohki [QC#26968,ADD]
    // START 2018/10/24 S.Takami [QC#27069, Add]
    private void doProcess_NFCL3000_NSAL1240(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;
        NFCL3000SMsg globalMsg = (NFCL3000SMsg) sMsg;

        int slctLineIdx = bizMsg.xxCellIdx.getValueInt();
        if (slctLineIdx < 0) {
            return;
        }

        if (bizMsg.Y.getValidCount() <= 0) {
            return;
        }
        NFCL3000_ACMsg lineMsg = bizMsg.A.no(slctLineIdx);
        ZYPEZDItemValueSetter.setValue(lineMsg.dsContrNum_A1, bizMsg.contrNum_QO);
        ZYPEZDItemValueSetter.setValue(lineMsg.svcConfigMstrPk_A1, bizMsg.svcConfigMstrPk_QO);
        ZYPEZDItemValueSetter.setValue(lineMsg.mdlNm_A1, bizMsg.mdlNm_QO);
        ZYPEZDItemValueSetter.setValue(lineMsg.mdlId_A1, bizMsg.mdlId_QO);
        if (bizMsg.Y.getValidCount() > 0) {
            NFCL3000_YCMsg rtrnMsg = bizMsg.Y.no(0);
            ZYPEZDItemValueSetter.setValue(lineMsg.serNum_A1, rtrnMsg.serNum_O);
            ZYPEZDItemValueSetter.setValue(lineMsg.svcMachMstrPk_A1, rtrnMsg.svcMachMstrPk_O);
        }

        NFCL3000_ASMsg glblLineMsg = globalMsg.A.no(slctLineIdx);

        ZYPEZDItemValueSetter.setValue(glblLineMsg.dsContrNum_A1, lineMsg.dsContrNum_A1);
        ZYPEZDItemValueSetter.setValue(glblLineMsg.mdlNm_A1, lineMsg.mdlNm_A1);
        ZYPEZDItemValueSetter.setValue(glblLineMsg.mdlId_A1, lineMsg.mdlId_A1);
        ZYPEZDItemValueSetter.setValue(glblLineMsg.serNum_A1, lineMsg.serNum_A1);
        ZYPEZDItemValueSetter.setValue(glblLineMsg.svcMachMstrPk_A1, lineMsg.svcMachMstrPk_A1);

        // START 2019/06/05 S.Takami [QC#50683,ADD]
        if (NFCL3000CommonLogic.isDsInvTpFsr(bizMsg) //
                && S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, lineMsg.xxYesNoCd_A1.getValue())) {
            copyFsrCommonDataParentToChild(bizMsg, slctLineIdx);
        }
        // END 2019/06/05 S.Takami [QC#50683,ADD]
    }
    // END   2018/10/24 S.Takami [QC#27069, Add]
    // START 2019/04/25 S.Takami [QC#50078,ADD]
    private void doProcess_NFCL3000Scrn00_Select_PackageUom(NFCL3000CMsg bizMsg, NFCL3000SMsg glblMsg) {

        int idx = bizMsg.xxCellIdx.getValueInt();
        NFCL3000CommonLogic.setDplyCtrlFlg(bizMsg, idx);
    }
    // END 2019/04/25 S.Takami [QC#50078,ADD]

    // START 2019/05/16 S.Takami [QC#50374,ADD]
    private void resetPullDownListPkgUom(NFCL3000CMsg bizMsg) {

        List<String> calUomCdList = new ArrayList<String>(0);
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NFCL3000_ACMsg lineMsg = bizMsg.A.no(i);
            if (ZYPCommonFunc.hasValue(lineMsg.pkgUomCd_A1)) {
                calUomCdList.add(lineMsg.pkgUomCd_A1.getValue());
            } else {
                calUomCdList.add("<null>");
            }
        }
        NFCL3000CommonLogic.createPulldownListPkgUom(bizMsg);
        for (int idx = 0; idx < bizMsg.A.getValidCount(); idx++) {
            if (hasCodeInPullDown(bizMsg.A.no(idx).pkgUomCd_AC, calUomCdList.get(idx))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).pkgUomCd_A1, calUomCdList.get(idx));
            }
            NFCL3000CommonLogic.setDplyCtrlFlg(bizMsg, idx);
        }
    }

    private boolean hasCodeInPullDown(EZDCStringItemArray pullDownArray, String code) {

        for (int i = 0; i < pullDownArray.length(); i++) {
            if (S21StringUtil.isEquals(pullDownArray.no(i).getValue(), code)) {
                return true;
            }
        }
        return false;
    }
    // END 2019/05/16 S.Takami [QC#50374,ADD]

    // START 2019/06/05 S.Takami [QC#50683,ADD]
    private void copyFsrCommonDataParentToChild(NFCL3000CMsg bizMsg, int prntLineIdx) {

        NFCL3000_ACMsg prntLineMsg = bizMsg.A.no(prntLineIdx);
        for (int childLineIdx = 0; childLineIdx <= bizMsg.A.getValidCount(); childLineIdx++) {
            NFCL3000_ACMsg lineMsg = bizMsg.A.no(childLineIdx);
            if (prntLineIdx != childLineIdx //
                    && S21StringUtil.isEquals(prntLineMsg.invBolLineNum_A1.getValue(), lineMsg.invBolLineNum_A1.getValue())) {
                copyFsrCommonDataParentToChild(prntLineMsg, lineMsg);
            }
        }
    }

    private void copyFsrCommonDataParentToChild(NFCL3000_ACMsg prntLineMsg, NFCL3000_ACMsg lineMsg) {

        if (S21StringUtil.isEquals(prntLineMsg.invLineNum_A1.getValue(), lineMsg.invLineNum_A1.getValue())) {
            return;
        }

        lineMsg.xxYesNoCd_A1.setValue(ZYPConstant.FLG_OFF_N);
        lineMsg.xxYesNoCd_A2.setValue(ZYPConstant.FLG_ON_Y);

        ZYPEZDItemValueSetter.setValue(lineMsg.dsContrNum_A1, prntLineMsg.dsContrNum_A1);
        ZYPEZDItemValueSetter.setValue(lineMsg.dsContrSqNum_A1, prntLineMsg.dsContrSqNum_A1);
        ZYPEZDItemValueSetter.setValue(lineMsg.dsContrDtlPk_A1, prntLineMsg.dsContrDtlPk_A1);
        ZYPEZDItemValueSetter.setValue(lineMsg.serNum_A1, prntLineMsg.serNum_A1);
        ZYPEZDItemValueSetter.setValue(lineMsg.mdlNm_A1, prntLineMsg.mdlNm_A1);
        ZYPEZDItemValueSetter.setValue(lineMsg.mdlId_A1, prntLineMsg.mdlId_A1);
        ZYPEZDItemValueSetter.setValue(lineMsg.svcMachMstrPk_A1, prntLineMsg.svcMachMstrPk_A1);
        ZYPEZDItemValueSetter.setValue(lineMsg.svcConfigMstrPk_A1, prntLineMsg.svcConfigMstrPk_A1);
    }

    private void setFsrCommonData(NFCL3000CMsg bizMsg, int lineIdx) {

        NFCL3000_ACMsg lineMsg = bizMsg.A.no(lineIdx);
        if (NFCL3000CommonLogic.isDsInvTpFsr(bizMsg) //
                && ZYPCommonFunc.hasValue(lineMsg.invBolLineNum_A1)) {
            NFCL3000_ACMsg prntLineMsg = getParentLineInBol(bizMsg, lineMsg.invBolLineNum_A1.getValue());
            if (prntLineMsg == null) {
                lineMsg.xxYesNoCd_A1.setValue(ZYPConstant.FLG_ON_Y);
                lineMsg.xxYesNoCd_A2.setValue(ZYPConstant.FLG_OFF_N);
            } else {
                copyFsrCommonDataParentToChild(prntLineMsg, lineMsg);
            }
        }
        
    }

    private NFCL3000_ACMsg getParentLineInBol(NFCL3000CMsg bizMsg, String invBolLineNum) {

        for (int lineIdx = 0; lineIdx < bizMsg.A.getValidCount(); lineIdx++) {
            NFCL3000_ACMsg lineMsg = bizMsg.A.no(lineIdx);
            if (S21StringUtil.isEquals(invBolLineNum, lineMsg.invBolLineNum_A1.getValue()) //
                    && S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, lineMsg.xxYesNoCd_A1.getValue())) {
                return lineMsg;
            }
        }
        return null;
    }

    private void resetFsrCommonData(NFCL3000CMsg bizMsg) {

        if (!NFCL3000CommonLogic.isDsInvTpFsr(bizMsg)) {
            for (int lineIdx = 0; lineIdx < bizMsg.A.getValidCount(); lineIdx++) {
                NFCL3000_ACMsg lineMsg = bizMsg.A.no(lineIdx);
                lineMsg.xxYesNoCd_A1.setValue(ZYPConstant.FLG_OFF_N);
                lineMsg.xxYesNoCd_A2.setValue(ZYPConstant.FLG_OFF_N);
            }
            return;
        }
        List<String> noPrntBolLineList = new ArrayList<String>(0);

        int digits = bizMsg.A.no(0).getAttr("invBolLineNum_A1").getDigit();

        String maxInvBolLineNum = ZYPCommonFunc.leftPad(String.valueOf(0), digits, "0");
        for (int lineIdx = 0; lineIdx < bizMsg.A.getValidCount(); lineIdx++) {
            NFCL3000_ACMsg lineMsg = bizMsg.A.no(lineIdx);
            if (maxInvBolLineNum.compareTo(lineMsg.invBolLineNum_A1.getValue()) < 0) {
                maxInvBolLineNum = lineMsg.invBolLineNum_A1.getValue();
            }
        }

        for (int bolNum = 1; ZYPCommonFunc.leftPad(String.valueOf(bolNum), digits, "0").compareTo(maxInvBolLineNum) <= 0; bolNum++) {
            String invBolLineNum = ZYPCommonFunc.leftPad(String.valueOf(bolNum), digits, "0");
            boolean hasParent = false;
            for (int lineIdx = 0; lineIdx < bizMsg.A.getValidCount(); lineIdx++) {
                NFCL3000_ACMsg lineMsg = bizMsg.A.no(lineIdx);
                if (S21StringUtil.isEquals(invBolLineNum, lineMsg.invBolLineNum_A1.getValue()) //
                        && S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, lineMsg.xxYesNoCd_A1.getValue())) {
                    hasParent = true;
                    break;
                }
            }
            if (!hasParent) {
                noPrntBolLineList.add(invBolLineNum);
            }
        }

        Map<String, String> noParentLine = new HashMap<String, String>(0);
        for (String invBolLineNum : noPrntBolLineList) {
            for (int lineIdx = 0; lineIdx < bizMsg.A.getValidCount(); lineIdx++) {
                NFCL3000_ACMsg lineMsg = bizMsg.A.no(lineIdx);
                if (S21StringUtil.isEquals(invBolLineNum, lineMsg.invBolLineNum_A1.getValue())) {
                    if (noParentLine.get(invBolLineNum) == null) {
                        lineMsg.xxYesNoCd_A1.setValue(ZYPConstant.FLG_ON_Y);
                        lineMsg.xxYesNoCd_A2.setValue(ZYPConstant.FLG_OFF_N);
                        noParentLine.put(invBolLineNum, ZYPConstant.FLG_ON_Y);
                    } else {
                        lineMsg.xxYesNoCd_A1.setValue(ZYPConstant.FLG_OFF_N);
                        lineMsg.xxYesNoCd_A2.setValue(ZYPConstant.FLG_ON_Y);
                    }
                }
            }
        }
    }
    // END 2019/06/05 S.Takami [QC#50683,ADD]
}
