/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7260;

import static business.servlet.NMAL7260.constant.NMAL7260Constant.BIZ_ID;

import java.math.BigDecimal;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7260.NMAL7260CMsg;
import business.servlet.NMAL7260.common.NMAL7260CommonLogic;
import business.servlet.NMAL7260.constant.NMAL7260Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NMAL7260_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/08   Fujitsu         H.Ikeda         Create          N/A
 * 2015/04/06   Fujitsu         Y.Kanefusa      Update          QC#6397
 * 2016/05/19   Fujitsu         Y.Kanefusa      Update          QC#5934
 * 2016/09/12   Fujitsu         R.Nakamura      Update          QC#11615
 * 2016/09/29   Fujitsu         R.Nakamura      Update          QC#6924
 * 2016/10/11   Fujitsu         R.Nakamura      Update          QC#14936
 * 2016/11/11   Fujitsu         R.Nakamura      Update          QC#5940
 * 2016/02/27   Fujitsu         R.Nakamura      Update          QC#16011
 * 2017/08/24   Fujitsu         S.Ohki          Update          QC#20729
 * 2018/04/10   Fujitsu         H.Nagashima     Update          QC#22593
 * 2018/09/12   Fujitsu         M.Ohno          Update          QC#9700
 * 2018/09/20   Fujitsu         H.Kumagai       Update          QC#9700
 * 2018/10/09   Fujitsu         H.Kumagai       Update          QC#28690
 * 2019/12/18   Fujitsu         C.Hara          Update          QC#55108
 *</pre>
 */
public class NMAL7260_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7260BMsg scrnMsg = (NMAL7260BMsg) bMsg;
        NMAL7260CMsg bizMsg = new NMAL7260CMsg();

        // IN Parameter Get
        Object[] params = (Object[]) getArgForSubScreen();

        // 2018/10/09 Mod Start QC#28690
        // Mod Start 2018/09/20 QC#9700
        if (params != null) {
//            if (params[0] instanceof EZDBBigDecimalItem) {
            if (params.length == 1) {
                if (params[0] instanceof EZDBBigDecimalItem) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.prcRuleHdrPk_BK, (EZDBBigDecimalItem) params[0]);
                } else {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.prcRuleHdrPk_BK, (BigDecimal) params[0]);
                }
            }

            if (params.length == 3) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.prcRuleHdrPk_BK, (EZDBBigDecimalItem) params[0]);
                ZYPEZDItemValueSetter.setValue(scrnMsg.prcAdjDtlPk_BK, (EZDBBigDecimalItem) params[1]);
                ZYPEZDItemValueSetter.setValue(scrnMsg.prcRuleDtlPk_BK, (EZDBBigDecimalItem) params[2]);
            }
        }
        // Mod End 2018/09/20 QC#9700
        // 2018/10/09 Mod End QC#28690
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxWrnSkipFlg_H0, ZYPConstant.FLG_OFF_0); // QC#5934 2016/05/19 Add 
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxWrnSkipFlg_H1, ZYPConstant.FLG_OFF_0);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxWrnSkipFlg_H2, ZYPConstant.FLG_OFF_0);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxFlgNm_H1, ZYPConstant.FLG_OFF_0);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxFlgNm_H2, ZYPConstant.FLG_OFF_0);

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7260BMsg scrnMsg = (NMAL7260BMsg) bMsg;
        NMAL7260CMsg bizMsg = (NMAL7260CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        //QC#22593 mod Start
//        NMAL7260CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.B, "B");
//        NMAL7260CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.C, "C");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab_H1,  NMAL7260Constant.TAB_ADJ_TBL_DFN);
        NMAL7260CommonLogic.setTableColor(scrnMsg);
        //QC#22593 mod End
        NMAL7260CommonLogic.initCmnBtnProp(this, scrnMsg, getUserProfileService());
        NMAL7260CommonLogic.scrnProtect(scrnMsg, getUserProfileService());
        NMAL7260CommonLogic.setBtnProp(this, scrnMsg, getUserProfileService());
        // Mod End 2017/02/27 QC#16011
        setAppFracDigit(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.prcRuleHdrPk_H1);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NMAL7260BMsg scrnMsg = (NMAL7260BMsg) bMsg;

        scrnMsg.prcRuleHdrPk_H1.setNameForMessage("Adjustment Table ID");
        scrnMsg.defRulePrcdNum_H1.setNameForMessage("Default Rule Precedence#");
        scrnMsg.prcRuleNm_H1.setNameForMessage("Table Name");
        scrnMsg.prcRuleHdrDescTxt_H1.setNameForMessage("Table Description");
        scrnMsg.xxFldValTxt_H1.setNameForMessage("Comments");
        scrnMsg.applyAutoFlg_H1.setNameForMessage("Apply Aoutmatically");
        scrnMsg.ovrdFlg_H1.setNameForMessage("Override");
        scrnMsg.actvFlg_H1.setNameForMessage("Active");
        scrnMsg.effFromDt_H1.setNameForMessage("Effective Date From");
        scrnMsg.effThruDt_H1.setNameForMessage("Effective Date To");
        // 2018/09/12 QC#9700 add start
        scrnMsg.prcGrpTrxTpCd_H1.setNameForMessage("Transaction Type");
        // 2018/09/12 QC#9700 add end

        for (int j = 0; j < scrnMsg.B.length(); j++) {
            scrnMsg.B.no(j).xxChkBox_B1.setNameForMessage("Check Box");
            scrnMsg.B.no(j).prcRuleCondFromTxt_04.setNameForMessage("CSMP#");
            scrnMsg.B.no(j).csmpNumCmntTxt_04.setNameForMessage("CSMP# Name");
            scrnMsg.B.no(j).prcRuleCondFromTxt_05.setNameForMessage("Material Price Group");
            scrnMsg.B.no(j).prcGrpNm_05.setNameForMessage("Material Price Group Name");
            scrnMsg.B.no(j).prcRuleCondFromTxt_06.setNameForMessage("Prod Ctrl-1(BU)");
            scrnMsg.B.no(j).prodCtrlNm_06.setNameForMessage("Prod Ctrl-1(BU) Name");
            scrnMsg.B.no(j).prcRuleCondFromTxt_07.setNameForMessage("Prod Ctrl-2(Model Series)");
            scrnMsg.B.no(j).prodCtrlNm_07.setNameForMessage("Prod Ctrl-2(Model Series) Name");
            scrnMsg.B.no(j).prcRuleCondFromTxt_08.setNameForMessage("Mdse Type");
            scrnMsg.B.no(j).coaMdseTpDescTxt_08.setNameForMessage("Mdse Type Name");
            scrnMsg.B.no(j).prcRuleCondFromTxt_09.setNameForMessage("Product Code");
            scrnMsg.B.no(j).coaProdDescTxt_09.setNameForMessage("Product Code Name");
            scrnMsg.B.no(j).prcRuleCondFromTxt_10.setNameForMessage("Item Code");
            scrnMsg.B.no(j).mdseDescShortTxt_10.setNameForMessage("Item Name");
            scrnMsg.B.no(j).prcRuleCondFromTxt_11.setNameForMessage("Order Category");
            scrnMsg.B.no(j).dsOrdCatgDescTxt_11.setNameForMessage("Order Category Name");
            scrnMsg.B.no(j).prcRuleCondFromTxt_12.setNameForMessage("Order Reason");
            scrnMsg.B.no(j).dsOrdTpDescTxt_12.setNameForMessage("Order Reason Name");
            scrnMsg.B.no(j).prcRuleCondFromTxt_13.setNameForMessage("Order Line of Business");
            scrnMsg.B.no(j).lineBizTpDescTxt_13.setNameForMessage("Order Line of Business Name");
            scrnMsg.B.no(j).prcRuleCondFromTxt_14.setNameForMessage("Transaction Group");
            scrnMsg.B.no(j).prcGrpNm_14.setNameForMessage("Transaction Group Name");
            scrnMsg.B.no(j).prcRuleCondFromTxt_15.setNameForMessage("Total Transaction Weight From");
            scrnMsg.B.no(j).prcRuleCondThruTxt_15.setNameForMessage("Total Transaction Weight To");
            scrnMsg.B.no(j).prcRuleCondFromTxt_16.setNameForMessage("Bill To (Acct#)");
            scrnMsg.B.no(j).billToAcctNm_16.setNameForMessage("Bill To (Acct#) Name");
            scrnMsg.B.no(j).prcRuleCondFromTxt_17.setNameForMessage("Bill To Acct (Channel)");
            scrnMsg.B.no(j).coaChDescTxt_17.setNameForMessage("Bill To Acct (Channel) Name");
            scrnMsg.B.no(j).prcRuleCondFromTxt_18.setNameForMessage("Bill To Acct (Classification)");
            scrnMsg.B.no(j).dsAcctClsDescTxt_18.setNameForMessage("Bill To Acct (Classification) Name");
            scrnMsg.B.no(j).prcRuleCondFromTxt_19.setNameForMessage("Branch");
            scrnMsg.B.no(j).coaBrDescTxt_19.setNameForMessage("Branch Name");
            scrnMsg.B.no(j).prcRuleCondFromTxt_20.setNameForMessage("Call Type");
            scrnMsg.B.no(j).svcCallTpDescTxt_20.setNameForMessage("Call Type Name");
            scrnMsg.B.no(j).xxSvcCallDt_FR.setNameForMessage("Call Date From");
            scrnMsg.B.no(j).xxSvcCallDt_TH.setNameForMessage("Call Date To");
            scrnMsg.B.no(j).prcRuleCondFromTxt_22.setNameForMessage("Customer PO From");
            scrnMsg.B.no(j).prcRuleCondThruTxt_22.setNameForMessage("Customer PO To");
            scrnMsg.B.no(j).prcRuleCondFromTxt_24.setNameForMessage("Line Amount From");
            scrnMsg.B.no(j).prcRuleCondThruTxt_24.setNameForMessage("Line Amount Name");
            scrnMsg.B.no(j).prcRuleCondFromTxt_25.setNameForMessage("Line Category (Line Type)");
            scrnMsg.B.no(j).dsOrdLineCatgDescTxt_25.setNameForMessage("Line Category (Line Type) Name");
            scrnMsg.B.no(j).prcRuleCondFromTxt_26.setNameForMessage("Line Qty From");
            scrnMsg.B.no(j).prcRuleCondThruTxt_26.setNameForMessage("Line Qty To");
            scrnMsg.B.no(j).prcRuleCondFromTxt_27.setNameForMessage("Marketing Model Name");
            scrnMsg.B.no(j).mktMdlDescTxt_27.setNameForMessage("Marketing Model Name Name");
            scrnMsg.B.no(j).prcRuleCondFromTxt_28.setNameForMessage("Model Segment");
            scrnMsg.B.no(j).mktMdlSegDescTxt_28.setNameForMessage("Model Segment Name");
            scrnMsg.B.no(j).prcRuleCondFromTxt_29.setNameForMessage("Order Source");
            scrnMsg.B.no(j).cpoSrcTpDescTxt_29.setNameForMessage("Order Source Name");
            scrnMsg.B.no(j).prcRuleCondFromTxt_30.setNameForMessage("Order Subtotal From");
            scrnMsg.B.no(j).prcRuleCondThruTxt_30.setNameForMessage("Order Subtotal To");
            scrnMsg.B.no(j).prcRuleCondFromTxt_31.setNameForMessage("Payment Type");
            scrnMsg.B.no(j).dsPmtMethDescTxt_31.setNameForMessage("Payment Type Name");
            scrnMsg.B.no(j).prcRuleCondFromTxt_32.setNameForMessage("Price List");
            scrnMsg.B.no(j).prcCatgNm_32.setNameForMessage("Price List Name");
            scrnMsg.B.no(j).prcDt_FR.setNameForMessage("Pricing Date From");
            scrnMsg.B.no(j).prcDt_TH.setNameForMessage("Pricing Date To");
            scrnMsg.B.no(j).prcRuleCondFromTxt_34.setNameForMessage("Prod Ctrl -3(Product)");
            scrnMsg.B.no(j).prodCtrlNm_34.setNameForMessage("Prod Ctrl -3(Product) Name");
            scrnMsg.B.no(j).prcRuleCondFromTxt_35.setNameForMessage("Prod Ctrl -4(Product-Group)");
            scrnMsg.B.no(j).prodCtrlNm_35.setNameForMessage("Prod Ctrl -4(Product-Group) Name");
            scrnMsg.B.no(j).prcRuleCondFromTxt_36.setNameForMessage("Prod Ctrl -5(Product-Type)");
            scrnMsg.B.no(j).prodCtrlNm_36.setNameForMessage("Prod Ctrl -5(Product-Type) Name");
            scrnMsg.B.no(j).xxRqstDt_FR.setNameForMessage("Request Date From");
            scrnMsg.B.no(j).xxRqstDt_TH.setNameForMessage("Request Date To");
            scrnMsg.B.no(j).prcRuleCondFromTxt_38.setNameForMessage("Return Reason Code");
            scrnMsg.B.no(j).rtrnRsnDescTxt_38.setNameForMessage("Return Reason Code Name");
            scrnMsg.B.no(j).prcRuleCondFromTxt_39.setNameForMessage("Service Level");
            scrnMsg.B.no(j).shpgSvcLvlDescTxt_39.setNameForMessage("Service Level Name");
            scrnMsg.B.no(j).prcRuleCondFromTxt_40.setNameForMessage("Service Model");
            scrnMsg.B.no(j).mdlDescTxt_40.setNameForMessage("Service Model Name"); // 2017/08/24 QC#20729 Mod
            scrnMsg.B.no(j).prcRuleCondFromTxt_41.setNameForMessage("Service Zone");
            scrnMsg.B.no(j).prcSvcZoneDescTxt_41.setNameForMessage("Service Zone Name");
            scrnMsg.B.no(j).prcRuleCondFromTxt_42.setNameForMessage("Ship To Acct (Classification)");
            scrnMsg.B.no(j).dsAcctClsDescTxt_42.setNameForMessage("Ship To Acct (Classification) Name");
            scrnMsg.B.no(j).prcRuleCondFromTxt_44.setNameForMessage("Special Handling Type");
            scrnMsg.B.no(j).spclHdlgTpDescTxt_44.setNameForMessage("Special Handling Type Name");
            scrnMsg.B.no(j).prcRuleCondFromTxt_45.setNameForMessage("Total Qty From");
            scrnMsg.B.no(j).prcRuleCondThruTxt_45.setNameForMessage("Total Qty To");
            scrnMsg.B.no(j).prcRuleCondFromTxt_46.setNameForMessage("Business Unit");
            scrnMsg.B.no(j).coaExtnDescTxt_46.setNameForMessage("Business Unit Name");
            scrnMsg.B.no(j).prcRuleCondFromTxt_48.setNameForMessage("Freight Term");
            scrnMsg.B.no(j).frtCondDescTxt_48.setNameForMessage("Freight Term Name");
            scrnMsg.B.no(j).prcRuleCondFromTxt_49.setNameForMessage("Freight Zone");
            scrnMsg.B.no(j).fill40Txt_49.setNameForMessage("Freight Zone Name");
            scrnMsg.B.no(j).prcRuleCondFromTxt_53.setNameForMessage("Customer Price Group(Sold To)");
            scrnMsg.B.no(j).prcGrpNm_53.setNameForMessage("Customer Price Group(Sold To) Name");
            scrnMsg.B.no(j).prcRuleCondFromTxt_54.setNameForMessage("Sold To (Acct#)");
            scrnMsg.B.no(j).dsAcctNm_54.setNameForMessage("Sold To (Acct#)");
            scrnMsg.B.no(j).prcRuleCondFromTxt_55.setNameForMessage("Sold To Acct (Channel)");
            scrnMsg.B.no(j).coaChDescTxt_55.setNameForMessage("Sold To Acct (Channel) Name");
            scrnMsg.B.no(j).prcRuleCondFromTxt_56.setNameForMessage("Sold To Acct (Classification)");
            scrnMsg.B.no(j).dsAcctClsDescTxt_56.setNameForMessage("Sold To Acct (Classification) Name");
            // 2019/12/18 QC#55108 Add Start
            scrnMsg.B.no(j).prcRuleCondFromTxt_57.setNameForMessage("Material Price Group(Qty Break)");
            scrnMsg.B.no(j).prcGrpNm_57.setNameForMessage("Material Price Group(Qty Break) Name");
            scrnMsg.B.no(j).prcRuleCondFromTxt_58.setNameForMessage("Line Qty(Qty Break) From");
            scrnMsg.B.no(j).prcRuleCondThruTxt_58.setNameForMessage("Line Qty(Qty Break) To");
            scrnMsg.B.no(j).prcRuleCondFromTxt_59.setNameForMessage("Material Group 1");
            scrnMsg.B.no(j).slsMatGrpDescTxt_59.setNameForMessage("Material Group 1 Name");
            scrnMsg.B.no(j).prcRuleCondFromTxt_60.setNameForMessage("Material Group 2");
            scrnMsg.B.no(j).slsMatGrpDescTxt_60.setNameForMessage("Material Group 2 Name");
            scrnMsg.B.no(j).prcRuleCondFromTxt_61.setNameForMessage("Material Group 3");
            scrnMsg.B.no(j).slsMatGrpDescTxt_61.setNameForMessage("Material Group 3 Name");
            // 2019/12/18 QC#55108 Add End
            scrnMsg.B.no(j).prcFmlaPk_B1.setNameForMessage("Formula");
            scrnMsg.B.no(j).prcFmlaNm_B1.setNameForMessage("Formula Name");
            scrnMsg.B.no(j).prcRuleDtlRate_B1.setNameForMessage("Percent");
            scrnMsg.B.no(j).prcRuleDtlTxt_B1.setNameForMessage("Value");
            scrnMsg.B.no(j).effFromDt_B1.setNameForMessage("Effective Date From");
            scrnMsg.B.no(j).effThruDt_B1.setNameForMessage("Effective Date To");

        }

        for (int k = 0; k < scrnMsg.C.length(); k++) {
            scrnMsg.C.no(k).xxChkBox_C1.setNameForMessage("Check Box");
            scrnMsg.C.no(k).prcRuleCondNum_C1.setNameForMessage("Column#");
            scrnMsg.C.no(k).prcRuleAttrbCd_C1.setNameForMessage("Column Name");
            scrnMsg.C.no(k).inpObjTpDescTxt_C1.setNameForMessage("Data Type");
            scrnMsg.C.no(k).inpReqFlg_C1.setNameForMessage("Required");
        }

    }
    private void setAppFracDigit(NMAL7260BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).prcRuleDtlRate_B1.setAppFracDigit(2);
        }
    }
}
