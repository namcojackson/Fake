/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7420;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7420.common.NMAL7420CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/07   Fujitsu         K.Ishizuka         Create          N/A
 * 2018/07/20   Fujitsu         W.Honda            Update          QC#20267
 * 2018/12/04   Fujitsu         Hd.Sugawara     Update          QC#29321
 * 2019/01/08   Fujitsu         Hd.Sugawara     Update          QC#29751
 *</pre>
 */
public class NMAL7420_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7420BMsg scrnMsg = (NMAL7420BMsg) bMsg;

        Object[] arg = (Object[]) getArgForSubScreen();
        NMAL7420CommonLogic.setInputParam(scrnMsg, arg);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7420BMsg scrnMsg = (NMAL7420BMsg) bMsg;
        NMAL7420CommonLogic.initCmnBtnProp(this);
        NMAL7420CommonLogic.scrnProtect(scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg msg) {
        NMAL7420BMsg scrnMsg = (NMAL7420BMsg) msg;

        scrnMsg.prcRuleCondFromTxt_04.setNameForMessage("CSMP#");
        scrnMsg.csmpNumCmntTxt_04.setNameForMessage("CSMP# Name");
        scrnMsg.prcRuleCondFromTxt_05.setNameForMessage("Material Price Group");
        scrnMsg.prcGrpNm_05.setNameForMessage("Material Price Group Name");
        scrnMsg.prcRuleCondFromTxt_06.setNameForMessage("Prod Ctrl-1(BU)");
        scrnMsg.prodCtrlNm_06.setNameForMessage("Prod Ctrl-1(BU) Name");
        scrnMsg.prcRuleCondFromTxt_07.setNameForMessage("Prod Ctrl-2(Model Series)");
        scrnMsg.prodCtrlNm_07.setNameForMessage("Prod Ctrl-2(Model Series) Name");
        scrnMsg.prcRuleCondFromTxt_08.setNameForMessage("Mdse Type");
        scrnMsg.coaMdseTpDescTxt_08.setNameForMessage("Mdse Type Name");
        scrnMsg.prcRuleCondFromTxt_09.setNameForMessage("Product Code");
        scrnMsg.coaProdDescTxt_09.setNameForMessage("Product Code Name");
        // Mod Start 2018/12/04 QC#29321
        //scrnMsg.prcRuleCondFromTxt_10.setNameForMessage("Item Code");
        //scrnMsg.mdseDescShortTxt_10.setNameForMessage("Item Name");
        //scrnMsg.mnfItemCd_10.setNameForMessage("Manufacture#"); // QC#20267 2018/07/20 Add
        scrnMsg.xxPrcQlfyValSrchTxt_J1.setNameForMessage("Item Code");
        scrnMsg.xxPrcQlfyValSrchTxt_J2.setNameForMessage("Item Name");
        scrnMsg.xxPrcQlfyValSrchTxt_J3.setNameForMessage("Manufacture#");
        // Mod End 2018/12/04 QC#29321
        scrnMsg.prcRuleCondFromTxt_11.setNameForMessage("Order Category");
        scrnMsg.dsOrdCatgDescTxt_11.setNameForMessage("Order Category Name");
        scrnMsg.prcRuleCondFromTxt_12.setNameForMessage("Order Reason");
        scrnMsg.dsOrdTpDescTxt_12.setNameForMessage("Order Reason Name");
        scrnMsg.prcRuleCondFromTxt_13.setNameForMessage("Order Line of Business");
        scrnMsg.lineBizTpDescTxt_13.setNameForMessage("Order Line of Business Name");
        scrnMsg.prcRuleCondFromTxt_14.setNameForMessage("Transaction Group");
        scrnMsg.prcGrpNm_14.setNameForMessage("Transaction Group Name");
        scrnMsg.prcRuleCondFromTxt_AF.setNameForMessage("Total Transaction Weight From");
        scrnMsg.prcRuleCondFromTxt_AT.setNameForMessage("Total Transaction Weight From");
        scrnMsg.prcRuleCondThruTxt_AF.setNameForMessage("Total Transaction Weight To");
        scrnMsg.prcRuleCondThruTxt_AT.setNameForMessage("Total Transaction Weight To");
        scrnMsg.prcRuleCondFromTxt_16.setNameForMessage("Bill To (Acct#)");
        scrnMsg.billToAcctNm_16.setNameForMessage("Bill To (Acct#) Name");
        scrnMsg.prcRuleCondFromTxt_17.setNameForMessage("Bill To Acct (Channel)");
        scrnMsg.coaChDescTxt_17.setNameForMessage("Bill To Acct (Channel) Name");
        scrnMsg.prcRuleCondFromTxt_18.setNameForMessage("Bill To Acct (Classification)");
        scrnMsg.dsAcctClsDescTxt_18.setNameForMessage("Bill To Acct (Classification) Name");
        scrnMsg.prcRuleCondFromTxt_19.setNameForMessage("Branch");
        scrnMsg.coaBrDescTxt_19.setNameForMessage("Branch Name");
        scrnMsg.prcRuleCondFromTxt_20.setNameForMessage("Call Type");
        scrnMsg.svcCallTpDescTxt_20.setNameForMessage("Call Type Name");
        scrnMsg.xxSvcCallDt_FF.setNameForMessage("Call Date From");
        scrnMsg.xxSvcCallDt_FT.setNameForMessage("Call Date From");
        scrnMsg.xxSvcCallDt_TF.setNameForMessage("Call Date To");
        scrnMsg.xxSvcCallDt_TT.setNameForMessage("Call Date To");
        scrnMsg.prcRuleCondFromTxt_BF.setNameForMessage("Customer PO From");
        scrnMsg.prcRuleCondFromTxt_BT.setNameForMessage("Customer PO From");
        scrnMsg.prcRuleCondThruTxt_BF.setNameForMessage("Customer PO To");
        scrnMsg.prcRuleCondThruTxt_BT.setNameForMessage("Customer PO To");
        scrnMsg.prcRuleCondFromTxt_CF.setNameForMessage("Line Amount From");
        scrnMsg.prcRuleCondFromTxt_CT.setNameForMessage("Line Amount From");
        scrnMsg.prcRuleCondThruTxt_CF.setNameForMessage("Line Amount Name");
        scrnMsg.prcRuleCondThruTxt_CT.setNameForMessage("Line Amount Name");
        scrnMsg.prcRuleCondFromTxt_25.setNameForMessage("Line Category (Line Type)");
        scrnMsg.dsOrdLineCatgDescTxt_25.setNameForMessage("Line Category (Line Type) Name");
        scrnMsg.prcRuleCondFromTxt_DF.setNameForMessage("Line Qty From");
        scrnMsg.prcRuleCondFromTxt_DT.setNameForMessage("Line Qty From");
        scrnMsg.prcRuleCondThruTxt_DF.setNameForMessage("Line Qty To");
        scrnMsg.prcRuleCondThruTxt_DT.setNameForMessage("Line Qty To");
        scrnMsg.prcRuleCondFromTxt_27.setNameForMessage("Marketing Model Name");
        scrnMsg.mktMdlDescTxt_27.setNameForMessage("Marketing Model Name Name");
        scrnMsg.prcRuleCondFromTxt_28.setNameForMessage("Model Segment");
        scrnMsg.mktMdlSegDescTxt_28.setNameForMessage("Model Segment Name");
        scrnMsg.prcRuleCondFromTxt_29.setNameForMessage("Order Source");
        scrnMsg.cpoSrcTpDescTxt_29.setNameForMessage("Order Source Name");
        scrnMsg.prcRuleCondFromTxt_EF.setNameForMessage("Order Subtotal From");
        scrnMsg.prcRuleCondFromTxt_ET.setNameForMessage("Order Subtotal From");
        scrnMsg.prcRuleCondThruTxt_EF.setNameForMessage("Order Subtotal To");
        scrnMsg.prcRuleCondThruTxt_ET.setNameForMessage("Order Subtotal To");
        scrnMsg.prcRuleCondFromTxt_31.setNameForMessage("Payment Type");
        scrnMsg.dsPmtMethDescTxt_31.setNameForMessage("Payment Type Name");
        scrnMsg.prcRuleCondFromTxt_32.setNameForMessage("Price List");
        scrnMsg.prcCatgNm_32.setNameForMessage("Price List Name");
        scrnMsg.prcDt_FF.setNameForMessage("Pricing Date From");
        scrnMsg.prcDt_FT.setNameForMessage("Pricing Date From");
        scrnMsg.prcDt_TF.setNameForMessage("Pricing Date To");
        scrnMsg.prcDt_TT.setNameForMessage("Pricing Date To");
        scrnMsg.prcRuleCondFromTxt_34.setNameForMessage("Prod Ctrl -3(Product)");
        scrnMsg.prodCtrlNm_34.setNameForMessage("Prod Ctrl -3(Product) Name");
        scrnMsg.prcRuleCondFromTxt_35.setNameForMessage("Prod Ctrl -4(Product-Group)");
        scrnMsg.prodCtrlNm_35.setNameForMessage("Prod Ctrl -4(Product-Group) Name");
        scrnMsg.prcRuleCondFromTxt_36.setNameForMessage("Prod Ctrl -5(Product-Type)");
        scrnMsg.prodCtrlNm_36.setNameForMessage("Prod Ctrl -5(Product-Type) Name");
        scrnMsg.xxRqstDt_FF.setNameForMessage("Request Date From");
        scrnMsg.xxRqstDt_FT.setNameForMessage("Request Date From");
        scrnMsg.xxRqstDt_TF.setNameForMessage("Request Date To");
        scrnMsg.xxRqstDt_TT.setNameForMessage("Request Date To");
        scrnMsg.prcRuleCondFromTxt_38.setNameForMessage("Return Reason Code");
        scrnMsg.rtrnRsnDescTxt_38.setNameForMessage("Return Reason Code Name");
        scrnMsg.prcRuleCondFromTxt_39.setNameForMessage("Service Level");
        scrnMsg.shpgSvcLvlDescTxt_39.setNameForMessage("Service Level Name");
        scrnMsg.prcRuleCondFromTxt_40.setNameForMessage("Service Model");
        scrnMsg.mdlNm_40.setNameForMessage("Service Model Name");
        scrnMsg.prcRuleCondFromTxt_41.setNameForMessage("Service Zone");
        scrnMsg.prcSvcZoneDescTxt_41.setNameForMessage("Service Zone Name");
        scrnMsg.prcRuleCondFromTxt_42.setNameForMessage("Ship To Acct (Classification)");
        scrnMsg.dsAcctClsDescTxt_42.setNameForMessage("Ship To Acct (Classification) Name");
        scrnMsg.prcRuleCondFromTxt_44.setNameForMessage("Special Handling Type");
        scrnMsg.spclHdlgTpDescTxt_44.setNameForMessage("Special Handling Type Name");
        scrnMsg.prcRuleCondFromTxt_FF.setNameForMessage("Total Qty From");
        scrnMsg.prcRuleCondFromTxt_FT.setNameForMessage("Total Qty From");
        scrnMsg.prcRuleCondThruTxt_FF.setNameForMessage("Total Qty To");
        scrnMsg.prcRuleCondThruTxt_FT.setNameForMessage("Total Qty To");
        scrnMsg.prcRuleCondFromTxt_46.setNameForMessage("Business Unit");
        scrnMsg.coaExtnDescTxt_46.setNameForMessage("Business Unit Name");
        scrnMsg.prcRuleCondFromTxt_48.setNameForMessage("Freight Term");
        scrnMsg.frtCondDescTxt_48.setNameForMessage("Freight Term Name");
        scrnMsg.prcRuleCondFromTxt_49.setNameForMessage("Freight Zone");
        scrnMsg.fill40Txt_49.setNameForMessage("Freight Zone Name");
        // Mod Start 2019/01/08 QC#29751
        //scrnMsg.prcRuleCondFromTxt_53.setNameForMessage("Customer Price Group(Sold To)");
        scrnMsg.xxPrcQlfyValSrchTxt_53.setNameForMessage("Customer Price Group(Sold To)");
        // Mod End 2019/01/08 QC#29751
        scrnMsg.prcGrpNm_53.setNameForMessage("Customer Price Group(Sold To) Name");
        // Mod Start 2018/12/04 QC#29321
        //scrnMsg.prcRuleCondFromTxt_54.setNameForMessage("Sold To (Acct#)");
        //scrnMsg.dsAcctNm_54.setNameForMessage("Sold To (Acct#)");
        scrnMsg.xxPrcQlfyValSrchTxt_K1.setNameForMessage("Sold To (Acct#)");
        scrnMsg.xxPrcQlfyValSrchTxt_K2.setNameForMessage("Sold To (Acct#)");
        // Mod End 2018/12/04 QC#29321
        scrnMsg.prcRuleCondFromTxt_55.setNameForMessage("Sold To Acct (Channel)");
        scrnMsg.coaChDescTxt_55.setNameForMessage("Sold To Acct (Channel) Name");
        scrnMsg.prcRuleCondFromTxt_56.setNameForMessage("Sold To Acct (Classification)");
        scrnMsg.dsAcctClsDescTxt_56.setNameForMessage("Sold To Acct (Classification) Name");
        scrnMsg.prcFmlaPk.setNameForMessage("Formula");
        scrnMsg.prcFmlaNm.setNameForMessage("Formula Name");
        scrnMsg.prcRuleDtlRate.setNameForMessage("Percent");
        scrnMsg.prcRuleDtlTxt.setNameForMessage("Value");
        scrnMsg.effFromDt_H1.setNameForMessage("Effective Date From");
        scrnMsg.effFromDt_H2.setNameForMessage("Effective Date From");
        scrnMsg.effThruDt_H1.setNameForMessage("Effective Date To");
        scrnMsg.effThruDt_H2.setNameForMessage("Effective Date To");
        scrnMsg.cratDt_H1.setNameForMessage("Create Date");
        scrnMsg.cratDt_H2.setNameForMessage("Create Date");
        scrnMsg.xxFullNm_H1.setNameForMessage("Create By");
        scrnMsg.lastUpdDt_H1.setNameForMessage("Update Date");
        scrnMsg.lastUpdDt_H2.setNameForMessage("Update Date");
        scrnMsg.xxFullNm_H2.setNameForMessage("Update By");

    }
}
