/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1840;

import static business.servlet.NWAL1840.constant.NWAL1840Constant.BIZ_ID;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.FUNC_CD_SRCH;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.SCREEN_ID;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.TAB_CUSTOMER;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1840.NWAL1840CMsg;
import business.servlet.NWAL1840.common.NWAL1840CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/28   Fujitsu         T.Murai         Create          N/A
 * 2017/09/25   Fujitsu         H.Sugawara      Update          QC#19922
 * 2017/10/18   Fujitsu         S.Yamamoto      Update          QC#20246
 * 2022/06/01   Hitachi         D.Yoshida       Update          QC#59973
 * 2023/10/10   Hitachi         T.Fukuta        Update          CSA-QC#61730
 *</pre>
 */
public class NWAL1840_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // security violation check.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1840BMsg scrnMsg = (NWAL1840BMsg) bMsg;

        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            EZDBStringItem param0 = (EZDBStringItem) params[0];
            scrnMsg.schdAgmtNum.setValue(param0.getValue());

        } else {
            // To process the menu transition.
            scrnMsg.schdAgmtNum.clear();
        }

        NWAL1840CMsg bizMsg = new NWAL1840CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1840BMsg scrnMsg = (NWAL1840BMsg) bMsg;
        NWAL1840CMsg bizMsg = (NWAL1840CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.xxDplyTab.setValue(TAB_CUSTOMER);
        scrnMsg.setFocusItem(scrnMsg.schdAgmtNum);

        NWAL1840CommonLogic.initCommonButton(this);
        NWAL1840CommonLogic.setProtect(this, scrnMsg);
        scrnMsg.clearAllGUIAttribute(SCREEN_ID);

    }

    protected void setNameForMessage(EZDBMsg bMsg) {
        NWAL1840BMsg scrnMsg = (NWAL1840BMsg) bMsg;
        scrnMsg.schdAgmtNum.setNameForMessage("Schd. Agreement Number");
        scrnMsg.dsOrdCatgDescTxt.setNameForMessage("Category");
        scrnMsg.dsOrdTpCd.setNameForMessage("Reason");
        scrnMsg.prcCatgNm.setNameForMessage("Price List");
        scrnMsg.mdlNm.setNameForMessage("Model Name");
        scrnMsg.serNum.setNameForMessage("Serial #");
        scrnMsg.svcConfigMstrPk.setNameForMessage(" Configuration ID");
        scrnMsg.dsContrNum.setNameForMessage("Contract #");
        scrnMsg.contrVrsnEffThruDt.setNameForMessage("Contract End Date");
        scrnMsg.schdAgmtVldFromDt.setNameForMessage("Valid From");
        scrnMsg.schdAgmtVldThruDt.setNameForMessage("Valid To");
        scrnMsg.schdAgmtDelyHldCd.setNameForMessage("Delivery Hold");
        scrnMsg.pmtTermCashDiscDescTxt.setNameForMessage("Payment Terms");

        // Customer/Contact Tab (Customer)
        scrnMsg.billToCustAcctCd.setNameForMessage("Bill To Number");
        // Mod Start 2017/09/25 QC#19922
        //scrnMsg.billToCustLocCd.setNameForMessage("Bill To Location");
        scrnMsg.billToCustLocCd.setNameForMessage("Bill To Code");
        // Mod End 2017/09/25 QC#19922
        scrnMsg.billToCustAcctNm.setNameForMessage("Bill To Name");
        scrnMsg.shipToCustAcctCd.setNameForMessage("Ship To Number");
        // Mod Start 2017/09/25 QC#19922
        //scrnMsg.shipToCustLocCd.setNameForMessage("Ship To Location");
        scrnMsg.shipToCustLocCd.setNameForMessage("Ship To Code");
        // Mod End 2017/09/25 QC#19922
        scrnMsg.shipToCustAcctNm.setNameForMessage("Ship To Name");
        scrnMsg.sellToFirstRefCmntTxt.setNameForMessage("Attention");// S21_NA ADD QC#20246
        scrnMsg.sellToCustCd.setNameForMessage("Sold To Number");
        // Mod Start 2017/09/25 QC#19922
        //scrnMsg.soldToCustLocCd.setNameForMessage("Sold To Location");
        scrnMsg.soldToCustLocCd.setNameForMessage("Sold To Code");
        // Mod End 2017/09/25 QC#19922
        scrnMsg.soldToCustAcctNm.setNameForMessage("Sold To Name");

        for (int i = 0; i < scrnMsg.D.length(); i++) {
            NWAL1840_DBMsg contactMsg = scrnMsg.D.no(i);
            contactMsg.ctacPsnTpCd_D.setNameForMessage("Role");
            contactMsg.ctacPsnFirstNm_D.setNameForMessage("First Name");
            contactMsg.ctacPsnLastNm_D.setNameForMessage("Last Name");
            contactMsg.ctacPsnTelNum_D.setNameForMessage("Phone");
            contactMsg.ctacPsnExtnNum_D.setNameForMessage("EXT");
            contactMsg.ctacPsnEmlAddr_D.setNameForMessage("EMail");
            contactMsg.ctacPsnFaxNum_D.setNameForMessage("Fax");
        }

        // Header Tab
        scrnMsg.custIssPoNum.setNameForMessage("Customer PO");
        scrnMsg.custIssPoDt.setNameForMessage("Customer PO Date");
        scrnMsg.slsRepTocNm.setNameForMessage("Sales Rep Name");
        scrnMsg.psnNum.setNameForMessage("Sales Rep Number"); // S21_NA#7861 Mod slsRepPsnCd -> psnNum
        scrnMsg.frtCondDescTxt.setNameForMessage("Freight Terms");
        scrnMsg.shpgSvcLvlCd.setNameForMessage("Service Level");
        scrnMsg.carrSvcLvlDescTxt.setNameForMessage("Carrier Service Level");
        scrnMsg.carrAcctNum.setNameForMessage("Carrier Acct Num");
        scrnMsg.spclHdlgTpCd.setNameForMessage("Special Handling");

        // Schedule Line Tab
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NWAL1840_ABMsg lineMsg = scrnMsg.A.no(i);
            lineMsg.mdseCd_A.setNameForMessage("Item#");
            lineMsg.otmShipQty_A.setNameForMessage("UOM");
            lineMsg.sbstMdseCd_A.setNameForMessage("Substitute Item");
            lineMsg.schdAllwQty_A.setNameForMessage("Qty Allowed");
            lineMsg.ordQty_SC.setNameForMessage("Total Qty Scheduled");
            lineMsg.ordQty_DE.setNameForMessage("Qty Delivery");
            lineMsg.dealPrcListPrcAmt_A.setNameForMessage("Unit List");
            lineMsg.xxTotDiscAmt_A.setNameForMessage("Unit Discount");
            lineMsg.dealNetUnitPrcAmt_A.setNameForMessage("Unit Sell");
            lineMsg.schdAgmtLineDealNetAmt_A.setNameForMessage("Subtotal");
            lineMsg.schdAgmtLineDealFrtAmt_A.setNameForMessage("Charges");
            lineMsg.schdAgmtLineDealTaxAmt_A.setNameForMessage("Tax");
            lineMsg.xxTotAmt_A.setNameForMessage("Total Price");
            // START 2022/06/01 [QC#59973, ADD]
            lineMsg.shpgIntvlCd_A.setNameForMessage("Frequency");
            lineMsg.otmShipQty_A.setNameForMessage("Periodic Qty");
            // END   2022/06/01 [QC#59973, ADD]
        }
        for (int i = 0; i < scrnMsg.B.length(); i++) {
            NWAL1840_BBMsg schdLineMsg = scrnMsg.B.no(i);
            schdLineMsg.rddDt_B.setNameForMessage("Requested Delivery Date");
            schdLineMsg.ordQty_B.setNameForMessage("Order Qty");
            schdLineMsg.ordQty_BS.setNameForMessage("Qty Scheduled");
            schdLineMsg.ordQty_BD.setNameForMessage("Qty Delivered");
            schdLineMsg.cpoOrdNum_B.setNameForMessage("Order Nuber");
            schdLineMsg.ordMdseCd_B.setNameForMessage("Ordered Item#");
        }

        // Comments Tab
        scrnMsg.shpgCmntTxt.setNameForMessage("Shipping Comments");
        scrnMsg.itrlOrdCmntTxt.setNameForMessage("Internal Order Comments");
        scrnMsg.invQty_N.setNameForMessage("Invoice Comments");

        // Additional Tab
        scrnMsg.xxPsnNm_SV.setNameForMessage("Created By");
        scrnMsg.prcContrNum.setNameForMessage("Program");
        scrnMsg.xxTsDsp19Txt_SV.setNameForMessage("Created Date");
        scrnMsg.dsAcctClsDescTxt.setNameForMessage("Classification");
        scrnMsg.xxScrItem54Txt_GL.setNameForMessage("GL Acct Classification");
        scrnMsg.xxTsDsp19Txt_SB.setNameForMessage("Order Submitterd Date");

        for (int i = 0; i < scrnMsg.F.length(); i++) {
            NWAL1840_FBMsg addtionalDataMsg = scrnMsg.F.no(i);
            addtionalDataMsg.mdseCd_F.setNameForMessage("Item#");
            addtionalDataMsg.xxTotUnitNetWt_F.setNameForMessage("Total Weight");
            addtionalDataMsg.coaMdseTpDescTxt_F.setNameForMessage("Merchandise Type");
            addtionalDataMsg.coaProdDescTxt_F.setNameForMessage("Product Code");
            addtionalDataMsg.zerothProdCtrlNm_F.setNameForMessage("Product Level 1");
            addtionalDataMsg.firstProdCtrlNm_F.setNameForMessage("Product Level 2");
            addtionalDataMsg.scdProdCtrlNm_F.setNameForMessage("Product Level 3");
            addtionalDataMsg.thirdProdCtrlNm_F.setNameForMessage("Product Level 4");
            addtionalDataMsg.frthProdCtrlNm_F.setNameForMessage("Product Level 5");
        }

        // START 2023/10/10 T.Fukuta [CSA-QC#61730,ADD]
        // SA History Tab
        for (int i = 0; i < scrnMsg.H.length(); i++) {
            NWAL1840_HBMsg saHistoryMsg = scrnMsg.H.no(i);
            saHistoryMsg.schdAgmtNum_H.setNameForMessage("SA #");
            saHistoryMsg.serNum_H.setNameForMessage("Serial #");
            saHistoryMsg.dsContrNum_H.setNameForMessage("Contract #");
            saHistoryMsg.locNm_H.setNameForMessage("Sold To Customer");
            saHistoryMsg.schdAgmtCratDt_H.setNameForMessage("Created Date");
            saHistoryMsg.schdAgmtStsNm_H.setNameForMessage("Schedule Status");
            saHistoryMsg.schdAgmtVldFromDt_H.setNameForMessage("Valid From");
            saHistoryMsg.schdAgmtVldThruDt_H.setNameForMessage("Valid To");
        }
        // END 2023/10/10 T.Fukuta [CSA-QC#61730,ADD]
    }
}
