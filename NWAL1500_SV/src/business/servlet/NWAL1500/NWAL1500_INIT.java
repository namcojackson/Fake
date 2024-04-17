/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.BIZ_ID;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_HEADER;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.servlet.NWAL1500.common.NWAL1500CommonLogic;
import business.servlet.NWAL1500.common.NWAL1500CommonLogicForScrnFields;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/24   Fujitsu         S.Takami        Create          N/A
 * 2016/05/11   Fujitsu         T.Murai         Update          S21_NA#7861
 * 2016/08/04   Fujitsu         T.Yoshdia       Update          S21_NA#11619,13026
 * 2016/08/09   Fujitsu         Y.Taoka         Update          S21_NA#8388
 * 2017/10/04   Fujitsu         H.Sugawara      Update          QC#19922
 * 2017/10/13   Fujitsu         S.Takami        Update          S21_NA#21267
 * 2017/11/02   Fujitsu         T.Aoi           Update          S21_NA#20146(Sol#92)
 * 2017/11/24   Fujitsu         K.Ishizuka      Update          S21_NA#20019
 * 2017/11/24   Fujitsu         Y.Kanefusa      Update          S21_NA#22789
 * 2017/12/12   Fujitsu         N.Sugiura       Update          S21_NA#20164
 * 2018/01/10   Fujitsu         Y.Kanefusa      Update          S21_NA#22372
 * 2018/02/01   Fujitsu         S.Takami        Update          S21_NA#19808
 * 2019/01/23   Fujitsu         M.Ishii         Update          S21_NA#29996
 *</pre>
 */
public class NWAL1500_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // security violation check.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);

        // For Performance QC#11619,13026 Add Start
        // Initialize EZDBMsg
        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        scrnMsg.setFlushAllArray(true);
        // For Performance QC#11619,13026 Add End
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            EZDBStringItem param0 = (EZDBStringItem) params[0];
            scrnMsg.cpoOrdNum.setValue(param0.getValue());

        } else {
            // To process the menu transition.
            scrnMsg.cpoOrdNum.clear();
        }

        NWAL1500CMsg bizMsg = new NWAL1500CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");

        // For Performance QC#11619,13026 Mod Start
        // EZDMsg.copy(scrnMsg, null, bizMsg, null);
        EZDMsg.forceCopy(scrnMsg, bizMsg);
        // For Performance QC#11619,13026 Mod End

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;

        // For Performance QC#11619,13026 Mod Start
        // EZDMsg.copy(bizMsg, null, scrnMsg, null);
        EZDMsg.forceCopy(bizMsg, scrnMsg);
        // For Performance QC#11619,13026 Mod End

        scrnMsg.xxDplyTab.setValue(TAB_HEADER);
        scrnMsg.setFocusItem(scrnMsg.cpoOrdNum);

        // 2017/10/13 S21_NA#21267 Mod Start
//        NWAL1500CommonLogic.initCommonButton(this);
        NWAL1500CommonLogic.initCommonButton(this, scrnMsg);
        // 2017/10/13 S21_NA#21267 Mod End
        NWAL1500CommonLogicForScrnFields.setProtect(this, scrnMsg);
        NWAL1500CommonLogic.inactiveAddButton(this);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

        // Order Information
        scrnMsg.cpoOrdNum.setNameForMessage("Order Number");
        scrnMsg.ordHdrStsDescTxt.setNameForMessage("Order Status");
        scrnMsg.dsOrdCatgDescTxt.setNameForMessage("Category");
        scrnMsg.dsOrdTpDescTxt.setNameForMessage("Reason");
        scrnMsg.dsOrdRsnCd.setNameForMessage("Sub Reason");
        scrnMsg.ordDt.setNameForMessage("Order Date");// TODO DELETE
        scrnMsg.ordDt_DP.setNameForMessage("Order Date");

        // Header Tab Customer Information
        // Bill To
        scrnMsg.billToCustAcctNm.setNameForMessage("Bill To Name");
        scrnMsg.billToCustAcctCd.setNameForMessage("Bill To Number");
        // Mod Start 2017/10/04 QC#19922
        //scrnMsg.billToCustCd.setNameForMessage("Bill To Location");
        scrnMsg.billToCustCd.setNameForMessage("Bill To Code");
        // Mod End 2017/10/04 QC#19922
        // Ship To
        scrnMsg.shipToCustAcctNm.setNameForMessage("Ship To Name");
        scrnMsg.dropShipFlg.setNameForMessage("Drop Ship");
        scrnMsg.shipToCustAcctCd.setNameForMessage("Ship To Number");
        // Mod Start 2017/10/04 QC#19922
        //scrnMsg.shipToCustCd.setNameForMessage("Ship To Location");
        scrnMsg.shipToCustCd.setNameForMessage("Ship To Code");
        // Mod End 2017/10/04 QC#19922
        scrnMsg.sellToFirstRefCmntTxt.setNameForMessage("Attention"); // 2017/12/12 S21_NA#20164 Add
        // Sold To
        scrnMsg.soldToCustAcctNm.setNameForMessage("Sold To Name");
        scrnMsg.sellToCustCd.setNameForMessage("Sold To Number");
        // Mod Start 2017/10/04 QC#19922
        //scrnMsg.soldToCustLocCd.setNameForMessage("Sold To Location");
        scrnMsg.soldToCustLocCd.setNameForMessage("Sold To Code");
        // Mod End 2017/10/04 QC#19922

        // Header Tab Header Details
        scrnMsg.negoDealAmt.setNameForMessage("Negotiated Deal");
        scrnMsg.slsRepTocNm.setNameForMessage("Salesrep Name");
        scrnMsg.psnNum.setNameForMessage("Salesrep Number"); // 2016/05/11 S21_NA#7861 Mod slsRepPsnCd -> psnNum
        scrnMsg.coaBrDescTxt.setNameForMessage("Salesrep Branch");
        scrnMsg.coaExtnDescTxt.setNameForMessage("Salesrep Bus Unit");
        scrnMsg.prcCatgNm.setNameForMessage("Price List");
        scrnMsg.custIssPoNum.setNameForMessage("Customer PO");
        scrnMsg.leaseCmpyPoNum.setNameForMessage("Lease Company PO");
        scrnMsg.custIssPoDt.setNameForMessage("Customer PO Date");
        scrnMsg.aquNum.setNameForMessage("Acquisition Number");
        // QC#1692
        // scrnMsg.ordLogTpCd.setNameForMessage("Log Type");
        scrnMsg.ordLogTpDescTxt_NM.setNameForMessage("Log Type");
        scrnMsg.xxInvCmntTxt.setNameForMessage("Invoice Comments");
        // 2019/01/23 QC#29996 Add Start
        scrnMsg.invCmntTxt.setNameForMessage("Invoice Comments");
        // 2019/01/23 QC#29996 Add End
        

        // Header Tab Order Source Details
        scrnMsg.cpoSrcTpDescTxt.setNameForMessage("Source Name");
        scrnMsg.ordSrcImptTsDplyTxt.setNameForMessage("Import Date");
        scrnMsg.ordSrcRefNum.setNameForMessage("Source Reference");

        // Addl Header Freight Information
        scrnMsg.frtCondDescTxt.setNameForMessage("Freight Terms");
        scrnMsg.carrSvcLvlDescTxt.setNameForMessage("Carrier Service Level");
        scrnMsg.shpgSvcLvlCd.setNameForMessage("Service Level");
        scrnMsg.carrAcctNum.setNameForMessage("Carrier Acct Num");
        scrnMsg.spclHdlgTpCd.setNameForMessage("Special Handling");

        // Addl Header Payment Information
        scrnMsg.pmtTermCashDiscDescTxt.setNameForMessage("Payment Terms");
        scrnMsg.dsPmtMethCd.setNameForMessage("Prepayment Method");
        scrnMsg.prePmtAmt.setNameForMessage("Amount");
        scrnMsg.prePmtTpCd.setNameForMessage("Prepayment Type");

        // Addl Header Additional Details
        //scrnMsg.addRddDt.setNameForMessage("Customer Request Date");// S21_NA#20019 MOD
        scrnMsg.addRddDt.setNameForMessage("Requested Delivery Date");// S21_NA#20019 MOD
        scrnMsg.prcContrNum.setNameForMessage("Association Program");
        scrnMsg.flPrcListNm.setNameForMessage("Floor Price List");
        scrnMsg.loanPerDaysAot.setNameForMessage("Loan Period (Days)");
        scrnMsg.csmpContrNum.setNameForMessage("CSMP Number");
        scrnMsg.dlrRefNum.setNameForMessage("CSA Number(Dealer Ref#)");
        scrnMsg.ordSgnDt.setNameForMessage("Customer Signed Date");
        scrnMsg.dclnSvcCd.setNameForMessage("Customer Declines Equipment Maintenance");  // S21_NA#8388 ADD

        // Addl Header Lease Details
        scrnMsg.leasePrchOptCd.setNameForMessage("End of Term Purchase Option");
        scrnMsg.leaseTermMthAot.setNameForMessage("Term");
        scrnMsg.leasePmtFreqCd.setNameForMessage("Payment Frequency");
        scrnMsg.leaseTotPmtAmt.setNameForMessage("Lease Total Payment Amount");

        scrnMsg.leaseTermMthAot_EM.setNameForMessage("Term"); // QC#22789 2017/11/28 Add
        // 2017/11/02 S21_NA#20146 Add Start
        // Addl Header GL Segments
        scrnMsg.psnNum_GS.setNameForMessage("Sales Rep Code");
        scrnMsg.tocNm_GS.setNameForMessage("Sales Rep Name");
        scrnMsg.coaExtnCd_GS.setNameForMessage("Bus Unit Code");
        scrnMsg.coaExtnDescTxt_GS.setNameForMessage("Bus Unit Name");
        scrnMsg.coaBrCd_GS.setNameForMessage("Branch Code");
        scrnMsg.coaBrDescTxt_GS.setNameForMessage("Branch Name");
        scrnMsg.coaCcCd_GS.setNameForMessage("Cost Center Code");
        scrnMsg.coaCcDescTxt_GS.setNameForMessage("Cost Center Name");
        // 2017/11/02 S21_NA#20146 Add End

        // Line Config Tab (Config)
        // For Performance QC#11619,13026 Mod Start
        // for (int i = 0; i < scrnMsg.A.length(); i++) {
        //     NWAL1500_ABMsg confMsg = scrnMsg.A.no(i);
        //     confMsg.svcConfigMstrPk_LC.setNameForMessage("Config ID");
        //     confMsg.mdlDescTxt_LC.setNameForMessage("Model");
        //     confMsg.shipToCustAcctCd_LC.setNameForMessage("Ship To Account");
        //     confMsg.shipToCustCd_LC.setNameForMessage("Ship To Location");
        //     confMsg.shipToCustAcctNm_LC.setNameForMessage("Ship To Name");
        //     confMsg.billToCustAcctCd_LC.setNameForMessage("Bill To Account");
        //     confMsg.billToCustCd_LC.setNameForMessage("Bill To Location");
        //     confMsg.billToCustAcctNm_LC.setNameForMessage("Bill To Name");
        // }
        scrnMsg.A.setNameForMessage("svcConfigMstrPk_LC", "Config ID");
        scrnMsg.A.setNameForMessage("mdlDescTxt_LC", "Model");
        scrnMsg.A.setNameForMessage("shipToCustAcctCd_LC", "Ship To Account");
        // Mod Start 2017/10/04 QC#19922
        //scrnMsg.A.setNameForMessage("shipToCustCd_LC", "Ship To Location");
        scrnMsg.A.setNameForMessage("shipToCustCd_LC", "Ship To Code");
        // Mod End 2017/10/04 QC#19922
        scrnMsg.A.setNameForMessage("shipToCustAcctNm_LC", "Ship To Name");
        scrnMsg.A.setNameForMessage("billToCustAcctCd_LC", "Bill To Account");
        // Mod Start 2017/10/04 QC#19922
        //scrnMsg.A.setNameForMessage("billToCustCd_LC", "Bill To Location");
        scrnMsg.A.setNameForMessage("billToCustCd_LC", "Bill To Code");
        // Mod End 2017/10/04 QC#19922
        scrnMsg.A.setNameForMessage("billToCustAcctNm_LC", "Bill To Name");
        scrnMsg.A.setNameForMessage("dclnSvcCd_LC", "Customer Declines Equipment Maintenance"); // S21_NA#8388 ADD
        // For Performance QC#11619,13026 Mod End

        // Line Config Tab (Line)
        // For Performance QC#11619,13026 Mod Start
        // for (int i = 0; i < scrnMsg.B.length(); i++) {
        //     NWAL1500_BBMsg lineMsg = scrnMsg.B.no(i);
        //     lineMsg.mdseCd_LL.setNameForMessage("Item#");
        //     lineMsg.ordCustUomQty_LL.setNameForMessage("Qty");
        //     lineMsg.custUomCd_LL.setNameForMessage("UOM");
        //     lineMsg.entCpoDtlDealSlsAmt_LL.setNameForMessage("Sell Price");
        //     lineMsg.prcCatgNm_LL.setNameForMessage("Sell Price List");
        //     lineMsg.prcListEquipConfigNum_LL.setNameForMessage("Price Config");
        //     lineMsg.rtlWhNm_LL.setNameForMessage("Warehouse");
        //     lineMsg.rtlSwhNm_LL.setNameForMessage("Sub Warehouse");
        //     lineMsg.serNum_LL.setNameForMessage("Serial#");
        //     lineMsg.sbstMdseCd_LL.setNameForMessage("Substitute Item");
        //     lineMsg.vndInvtyLocCd_LL.setNameForMessage("Vendor Warehouse");
        //     lineMsg.flPrcListNm_LL.setNameForMessage("Floor Price List");
        //     lineMsg.dplyLineRefNum_LL.setNameForMessage("Line Reference");
        //     lineMsg.prcBaseDt_LL.setNameForMessage("Price Date");
        //     lineMsg.rddDt_LL.setNameForMessage("Request Date");
        //     lineMsg.custMdseCd_LL.setNameForMessage("Customer Item Code");
        // }
        scrnMsg.B.setNameForMessage("mdseCd_LL", "Item#");
        scrnMsg.B.setNameForMessage("ordCustUomQty_LL", "Qty");
        scrnMsg.B.setNameForMessage("custUomCd_LL", "UOM");
        scrnMsg.B.setNameForMessage("entCpoDtlDealSlsAmt_LL", "Sell Price");
        scrnMsg.B.setNameForMessage("prcCatgNm_LL", "Sell Price List");
        scrnMsg.B.setNameForMessage("prcListEquipConfigNum_LL", "Price Config");
        scrnMsg.B.setNameForMessage("rtlWhNm_LL", "Warehouse");
        scrnMsg.B.setNameForMessage("rtlSwhNm_LL", "Sub Warehouse");
        scrnMsg.B.setNameForMessage("serNum_LL", "Serial#");
        scrnMsg.B.setNameForMessage("sbstMdseCd_LL", "Substitute Item");
        scrnMsg.B.setNameForMessage("vndInvtyLocCd_LL", "Vendor Warehouse");
        scrnMsg.B.setNameForMessage("flPrcListNm_LL", "Floor Price List");
        scrnMsg.B.setNameForMessage("funcUnitFlPrcAmt_LL", "Floor Price"); // QC#22372 2018/01/10 Add
        scrnMsg.B.setNameForMessage("dplyLineRefNum_LL", "Line Reference");
        scrnMsg.B.setNameForMessage("prcBaseDt_LL", "Price Date");
        scrnMsg.B.setNameForMessage("rddDt_LL", "Request Date");
        scrnMsg.B.setNameForMessage("custMdseCd_LL", "Customer Item Code");
        // For Performance QC#11619,13026 Mod End

        // RMA Tab (Config)
        // For Performance QC#11619,13026 Mod Start
        // for (int i = 0; i < scrnMsg.C.length(); i++) {
        //     NWAL1500_CBMsg confMsg = scrnMsg.C.no(i);
        //     confMsg.svcConfigMstrPk_RC.setNameForMessage("Config ID");
        //     confMsg.shipToCustAcctCd_RC.setNameForMessage("Ship To Account");
        //     confMsg.shipToCustCd_RC.setNameForMessage("Ship To Location");
        //     confMsg.shipToCustAcctNm_RC.setNameForMessage("Ship To Name");
        //     confMsg.billToCustAcctCd_RC.setNameForMessage("Bill To Account");
        //     confMsg.billToCustCd_RC.setNameForMessage("Bill To Location");
        //     confMsg.billToCustAcctNm_RC.setNameForMessage("Bill To Name");
        // }
        scrnMsg.C.setNameForMessage("svcConfigMstrPk_RC", "Config ID");
        scrnMsg.C.setNameForMessage("shipToCustAcctCd_RC", "Ship To Account");
        // Mod Start 2017/10/04 QC#19922
        //scrnMsg.C.setNameForMessage("shipToCustCd_RC", "Ship To Location");
        scrnMsg.C.setNameForMessage("shipToCustCd_RC", "Ship To Code");
        // Mod End 2017/10/04 QC#19922
        scrnMsg.C.setNameForMessage("shipToCustAcctNm_RC", "Ship To Name");
        scrnMsg.C.setNameForMessage("billToCustAcctCd_RC", "Bill To Account");
        // Mod Start 2017/10/04 QC#19922
        //scrnMsg.C.setNameForMessage("billToCustCd_RC", "Bill To Location");
        scrnMsg.C.setNameForMessage("billToCustCd_RC", "Bill To Code");
        // Mod End 2017/10/04 QC#19922
        scrnMsg.C.setNameForMessage("billToCustAcctNm_RC", "Bill To Name");
        // For Performance QC#11619,13026 Mod End

        // RMA Tab (Line)
        // For Performance QC#11619,13026 Mod Start
        // for (int i = 0; i < scrnMsg.D.length(); i++) {
        //     NWAL1500_DBMsg lineMsg = scrnMsg.D.no(i);
        //     lineMsg.mdseCd_RL.setNameForMessage("Item#");
        //     lineMsg.ordCustUomQty_RL.setNameForMessage("Qty");
        //     lineMsg.custUomCd_RL.setNameForMessage("UOM");
        //     lineMsg.entCpoDtlDealSlsAmt_RL.setNameForMessage("Sell Price");
        //     lineMsg.prcCatgNm_RL.setNameForMessage("Sell Price List");
        //     lineMsg.rtlWhNm_RL.setNameForMessage("Warehouse");
        //     lineMsg.rtlSwhNm_RL.setNameForMessage("Sub Warehouse");
        //     lineMsg.serNum_RL.setNameForMessage("Serial#");
        //     lineMsg.flPrcListNm_RL.setNameForMessage("Floor Price List");
        //     lineMsg.dplyLineRefNum_RL.setNameForMessage("Line Reference");
        //     lineMsg.prcBaseDt_RL.setNameForMessage("Price Date");
        //     lineMsg.rqstPickUpDt_RL.setNameForMessage("Requested Pick Up Date");
        //     lineMsg.rtrnRsnCd_RL.setNameForMessage("Return Reason Code");
        //     lineMsg.custMdseCd_RL.setNameForMessage("Customer Item Code");
        // }
        scrnMsg.D.setNameForMessage("mdseCd_RL", "Item#");
        scrnMsg.D.setNameForMessage("ordCustUomQty_RL", "Qty");
        scrnMsg.D.setNameForMessage("custUomCd_RL", "UOM");
        scrnMsg.D.setNameForMessage("entCpoDtlDealSlsAmt_RL", "Sell Price");
        scrnMsg.D.setNameForMessage("prcCatgNm_RL", "Sell Price List");
        scrnMsg.D.setNameForMessage("rtlWhNm_RL", "Warehouse");
        scrnMsg.D.setNameForMessage("rtlSwhNm_RL", "Sub Warehouse");
        scrnMsg.D.setNameForMessage("serNum_RL", "Serial#");
        scrnMsg.D.setNameForMessage("flPrcListNm_RL", "Floor Price List");
        scrnMsg.D.setNameForMessage("funcUnitFlPrcAmt_RL", "Floor Price"); // QC#22372 2018/01/10 Add
        scrnMsg.D.setNameForMessage("dplyLineRefNum_RL", "Line Reference");
        scrnMsg.D.setNameForMessage("prcBaseDt_RL", "Price Date");
        scrnMsg.D.setNameForMessage("rqstPickUpDt_RL", "Requested Pick Up Date");
        scrnMsg.D.setNameForMessage("rtrnRsnCd_RL", "Return Reason Code");
        scrnMsg.D.setNameForMessage("custMdseCd_RL", "Customer Item Code");
        // For Performance QC#11619,13026 Mod End
        // 2018/02/01 S21_NA#19808 Add Start
        scrnMsg.dsOrdPosnNum_AS.setNameForMessage("Current Position Number");
        scrnMsg.dsOrdPosnNum_CS.setNameForMessage("Current Position Number");
        scrnMsg.xxPageShowCurNum_LL.setNameForMessage("Current Page Number");
        scrnMsg.xxPageShowCurNum_RL.setNameForMessage("Current Page Number");
        // 2018/02/01 S21_NA#19808 Add End
    }
}
