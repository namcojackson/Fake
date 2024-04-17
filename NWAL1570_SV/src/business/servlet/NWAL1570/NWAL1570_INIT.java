/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1570;

import static business.servlet.NWAL1570.constant.NWAL1570Constant.BIZ_ID;
import static business.servlet.NWAL1570.constant.NWAL1570Constant.SCRN_ID_00;
import static business.servlet.NWAL1570.constant.NWAL1570Constant.SCRN_ID_OTH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1570.NWAL1570CMsg;
import business.servlet.NWAL1570.common.NWAL1570CommonLogic;
import business.servlet.NWAL1570.constant.NWAL1570Constant;
import business.servlet.NWAL1570.constant.NWAL1570Constant.RSLT_MODE;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NWAL1570_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/02   Fujitsu         K.Sato          Create          N/A
 * 2016/11/22   Fujitsu         K.Sato          Update          QC#15760
 * 2017/10/04   Fujitsu         H.Sugawara      Update          QC#19922
 * 2018/08/01   Fujitsu         T.Aoi           Update          QC#26304
 *</pre>
 */
public class NWAL1570_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //security violation check.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1570BMsg scrnMsg = (NWAL1570BMsg) bMsg;
        NWAL1570CMsg bizMsg = new NWAL1570CMsg();

        scrnMsg.glblCmpyCd.setValue(getGlobalCompanyCode());

        // IN Parameter Get
        Object[] params = (Object[]) getArgForSubScreen();

        NWAL1570CommonLogic.setInitRequestData(scrnMsg, params);

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1570BMsg scrnMsg = (NWAL1570BMsg) bMsg;
        NWAL1570CMsg bizMsg = (NWAL1570CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // transition from other screen
        if (SCRN_ID_OTH.equals(scrnMsg.xxScrId.getValue())) {
            NWAL1570CommonLogic.addCheckItemBizLayerErr(scrnMsg);
            scrnMsg.putErrorScreen();

            if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
                scrnMsg.setMessageInfo(NWAL1570Constant.ZZM8100I);
            }

            // 1. initialize GUI attribute.
            NWAL1570CommonLogic.initGuiAttrScrnRslt(this, scrnMsg);

            // 2. initialize GUI value.
            NWAL1570CommonLogic.initGuiValueScrnRslt(scrnMsg);

            // 3. set alternate rows back-ground color
            NWAL1570CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");

            // 4. set digit.
            NWAL1570CommonLogic.setAppFracDigit(scrnMsg);
            
            // 5. set next screen
            String resultId = "";
            if (bizMsg.xxRsltModeCd.getValue().equals(RSLT_MODE.ORDER_INQUIRY.getRsltModeCd())) {
                resultId = "toInquiryByStatus";
            } else {
                resultId = "toStatusSummary";
            }
            setResult(resultId);
        } else {
            // 1. initialize GUI attribute.
            NWAL1570CommonLogic.initGuiAttrScrn00(this, scrnMsg);

            // 2. initialize GUI value.
            NWAL1570CommonLogic.initGuiValueScrn00(scrnMsg);
            
            // 3. setProtectByAuthority
            NWAL1570CommonLogic.setProtectByAuthority(this, scrnMsg);

            // 4. set focus.
            scrnMsg.setFocusItem(scrnMsg.xxCpoOrdNumSrchTxt_H1);

            // 5. set digit.
            NWAL1570CommonLogic.setAppFracDigit(scrnMsg);
            
            // 6. set screen ID.
            scrnMsg.xxScrId.setValue(SCRN_ID_00);

            // 7. set next screen
            String resultId = "";
            resultId = "init";
            setResult(resultId);
        }

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NWAL1570BMsg scrnMsg = (NWAL1570BMsg) bMsg;

        // Saved Search Options
        scrnMsg.srchOptPk_H1.setNameForMessage("Saved Search Options");
        // Search Option Name
        scrnMsg.srchOptNm_H1.setNameForMessage("Search Option Name");

        //++++++++++++++++++++++++++++++++++++++++
        //+++++ Header Search Criteria
        //++++++++++++++++++++++++++++++++++++++++
        //Order#
        scrnMsg.xxCpoOrdNumSrchTxt_H1.setNameForMessage("Order#");
        //Orig. Order#
        scrnMsg.xxCpoOrdNumSrchTxt_H2.setNameForMessage("Orig. Order#");
        //Cust. PO#
        scrnMsg.custIssPoNumSrchTxt.setNameForMessage("Cust. PO#");
        //Lease PO#
        scrnMsg.xxLeasePoNumSrchTxt.setNameForMessage("Lease PO#");
        //Sold To
        scrnMsg.xxSoldToAcctNmSrchTxt.setNameForMessage("Sold To Name");
        scrnMsg.xxSoldToAcctCdSrchTxt.setNameForMessage("Sold To Acct#");
        // Mod Start 2017/10/04 QC#19922
        //scrnMsg.xxSoldToLocCdSrchTxt.setNameForMessage("Sold To Loc#");
        scrnMsg.xxSoldToLocCdSrchTxt.setNameForMessage("Sold To Code");
        // Mod End 2017/10/04 QC#19922
        //Ship To
        scrnMsg.xxShipToAcctNmSrchTxt.setNameForMessage("Ship To Name");
        scrnMsg.xxShipToAcctCdSrchTxt.setNameForMessage("Ship To Acct#");
        // Mod Start 2017/10/04 QC#19922
        //scrnMsg.xxShipToLocCdSrchTxt.setNameForMessage("Ship To Loc#");
        scrnMsg.xxShipToLocCdSrchTxt.setNameForMessage("Ship To Code");
        // Mod End 2017/10/04 QC#19922
        //Bill To
        scrnMsg.xxBillToAcctNmSrchTxt.setNameForMessage("Bill To Name");
        scrnMsg.xxBillToAcctCdSrchTxt.setNameForMessage("Bill To Acct#");
        // Mod Start 2017/10/04 QC#19922
        //scrnMsg.xxBillToLocCdSrchTxt.setNameForMessage("Bill To Loc#");
        scrnMsg.xxBillToLocCdSrchTxt.setNameForMessage("Bill To Code");
        // Mod End 2017/10/04 QC#19922
        //Business Unit
        scrnMsg.xxCoaExtnSrchTxt.setNameForMessage("Business Unit");
        //Branch
        scrnMsg.xxCoaBrSrchTxt.setNameForMessage("Branch");
        //Sales Rep
        scrnMsg.xxSlsRepTocSrchTxt.setNameForMessage("Sales Rep");
        //Order Source
        scrnMsg.xxCpoSrcTpSrchTxt.setNameForMessage("Order Source");
        //LOB
        scrnMsg.xxDsBizLineSrchTxt.setNameForMessage("LOB");
        //Order Category
        scrnMsg.xxDsOrdCatgSrchTxt.setNameForMessage("Order Category");
        //Order Reason
        scrnMsg.xxDsOrdTpSrchTxt.setNameForMessage("Order Reason");
        //Sub Reason
        scrnMsg.xxDsOrdRsnSrchTxt.setNameForMessage("Sub Reason");
        //CSMP#
        scrnMsg.xxCsmpContrNumSrchTxt.setNameForMessage("CSMP#");
        //Association Program#
        scrnMsg.xxPrcContrNumSrchTxt.setNameForMessage("Association Program Name");
        //Import Source#
        scrnMsg.xxOrdSrcRefNumSrchTxt.setNameForMessage("Import Source#");
        // 2018/08/01 QC#26304 Add Start
        //Acquisition#
        scrnMsg.xxAquNumSrchTxt.setNameForMessage("Acquisition#");
        // 2018/08/01 QC#26304 Add End

        //++++++++++++++++++++++++++++++++++++++++
        //+++++Line Search Criteria
        //++++++++++++++++++++++++++++++++++++++++
        //Item Name
        scrnMsg.xxMdseSrchTxt.setNameForMessage("Item Name");
        //Item Code
        scrnMsg.mdseForSlsSmrySrchTxt.setNameForMessage("Item Code");
        //PL Group
        scrnMsg.zerothProdCtrlSrchTxt.setNameForMessage("PL Group");
        //PL1
        scrnMsg.firstProdCtrlSrchTxt.setNameForMessage("PL1");
        //PL2
        scrnMsg.scdProdCtrlSrchTxt.setNameForMessage("PL2");
        //PL3
        scrnMsg.thirdProdCtrlSrchTxt.setNameForMessage("PL3");
        //PL4
        scrnMsg.frthProdCtrlSrchTxt.setNameForMessage("PL4");
        //COA Product
        scrnMsg.xxCoaProdSrchTxt.setNameForMessage("COA Prod");
        //COA MDSE
        scrnMsg.xxCoaMdseTpSrchTxt.setNameForMessage("COA MDSE");
        //Model
        scrnMsg.xxSerNumSrchTxt.setNameForMessage("Model");
        //Serial Number
        scrnMsg.xxMdlSrchTxt.setNameForMessage("Serial#");
        //Return Reason
        scrnMsg.rtrnRsnCd.setNameForMessage("Return Reason");
        //Line Category
        scrnMsg.xxLineCatgSrchTxt.setNameForMessage("Line Category");
        //Line Source
        scrnMsg.xxOrdLineSrcSrchTxt.setNameForMessage("Line Source");
        //WH
        scrnMsg.xxRtlWhSrchTxt.setNameForMessage("WH");
        //SUB WH
        scrnMsg.xxRtlSwhSrchTxt.setNameForMessage("SUB WH");
        //PO Vendor
        scrnMsg.xxVndSrchTxt.setNameForMessage("PO Vendor");
        //P/O#
        scrnMsg.xxCpoOrdNumSrchTxt.setNameForMessage("P/O#");
        //S/O#
        scrnMsg.soNumSrchTxt.setNameForMessage("S/O#");
        //Invoice#
        scrnMsg.invNumSrchTxt.setNameForMessage("Invoice#");
        //Contract#
        scrnMsg.xxDsContrNumSrchTxt.setNameForMessage("Contract#");
        //Config#
        scrnMsg.xxSvcConfigMstrSrchTxt.setNameForMessage("Config#");
        // QC#15760 Add Start
        //Install Base ID
        scrnMsg.xxSvcMachMstrSrchTxt.setNameForMessage("Install Base ID");
        // QC#15760 Add End

        //++++++++++++++++++++++++++++++++++++++++
        //+++++Order Team
        //++++++++++++++++++++++++++++++++++++++++
        //Team
        scrnMsg.xxOrdTeamSrchTxt.setNameForMessage("Team");
        //Zone
        scrnMsg.xxOrdZnSrchTxt.setNameForMessage("Zone");
        //Created By
        scrnMsg.xxCratByUsrSrchTxt.setNameForMessage("Created By");

        //++++++++++++++++++++++++++++++++++++++++
        //+++++Mode
        //++++++++++++++++++++++++++++++++++++++++
        //Mode
        scrnMsg.xxRsltModeCd.setNameForMessage("Mode");
        //Include Import
        scrnMsg.xxInclImptOrdFlg.setNameForMessage("Include Import");
        //Sales
        scrnMsg.xxOnlySlsOrdFlg.setNameForMessage("Sales");

        //++++++++++++++++++++++++++++++++++++++++
        //+++++Status
        //++++++++++++++++++++++++++++++++++++++++
        //Header Status
        scrnMsg.xxOrdHdrStsAllSelFlg.setNameForMessage("Header Status Select All");
        for (int i = 0; i < scrnMsg.H.length(); i++) {
            scrnMsg.H.no(i).xxOrdHdrStsSelFlg_HS.setNameForMessage("Header Status");
        }
        //Line Status
        scrnMsg.xxLineStsAllSelFlg.setNameForMessage("Line Status Select All");
        for (int i = 0; i < scrnMsg.L.length(); i++) {
            scrnMsg.L.no(i).xxLineStsSelFlg_LS.setNameForMessage("Line Status");
        }
        //Return Line Status
        scrnMsg.xxRtrnStsAllSelFlg.setNameForMessage("Return Line Status Select All");
        for (int i = 0; i < scrnMsg.R.length(); i++) {
            scrnMsg.R.no(i).xxRtrnLineStsSelFlg_RS.setNameForMessage("Return Line Status");
        }
        //Include Pending Import Order
        scrnMsg.xxInclPendImptOrdFlg.setNameForMessage("Include Pending Import Order");
        //All Open Order
        scrnMsg.xxAllOpenOrdFlg.setNameForMessage("All Open Order");

        //++++++++++++++++++++++++++++++++++++++++
        //+++++Date Criteria
        //++++++++++++++++++++++++++++++++++++++++
        //Order Date
        scrnMsg.ordFromDt.setNameForMessage("Order From Date");
        scrnMsg.ordToDt.setNameForMessage("Order To Date");
        //Booked Date
        scrnMsg.xxBookFromDt.setNameForMessage("Booked From Date");
        scrnMsg.xxBookToDt.setNameForMessage("Booked To Date");
        //Shipped Date
        scrnMsg.xxActlShipFromDt.setNameForMessage("Shipped From Date");
        scrnMsg.xxActlShipToDt.setNameForMessage("Shipped To Date");
        //Invoice Date
        scrnMsg.invFromDt.setNameForMessage("Invoice From Date");
        scrnMsg.invToDt.setNameForMessage("Invoice To Date");
        //Import Date
        scrnMsg.xxOrdSrcImptFromDt.setNameForMessage("Import From Date");
        scrnMsg.xxOrdSrcImptToDt.setNameForMessage("Import To Date");

        //++++++++++++++++++++++++++++++++++++++++
        //+++++Display Option
        //++++++++++++++++++++++++++++++++++++++++
        //Display By
        //Display By - 1
        scrnMsg.dplyBy01ItemNm.setNameForMessage("Display By");
        //Display By - 2
        scrnMsg.dplyBy02ItemNm.setNameForMessage("Display By");
        //Display By - 3
        scrnMsg.dplyBy03ItemNm.setNameForMessage("Display By");
        //Display Mode
        scrnMsg.grpByDnldCd.setNameForMessage("Display Mode");
        //Real Time Inquiry
        scrnMsg.xxPgFlg.setNameForMessage("Real Time Inquiry");

    }

}
