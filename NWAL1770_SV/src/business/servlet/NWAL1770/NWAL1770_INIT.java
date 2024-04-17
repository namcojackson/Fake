/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1770;

import static business.servlet.NWAL1770.constant.NWAL1770Constant.BIZ_ID;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.FUNC_CD_SRCH;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.SCREEN_ID;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.TAB_CUSTOMER;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1770.NWAL1770CMsg;
import business.servlet.NWAL1770.common.NWAL1770CommonLogic;
import business.servlet.NWAL1770.common.NWAL1770CommonLogicForScrnFields;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/07   Fujitsu         T.Yoshida       Create          N/A
 * 2016/05/16   Fujitsu         T.Murai         Update          S21_NA#7861
 * 2016/08/31   Fujitsu         T.Murai         Update          S21_NA#11547
 * 2017/08/17   Fujitsu         Y.Kanefusa      Update          S21_NA#20580
 * 2017/09/25   Fujitsu         H.Sugawara      Update          QC#19922
 * 2017/10/18   Fujitsu         W.Honda         Update          S21_NA#20246-1(L3 Sol#454)
 * 2018/02/09   Fujitsu         T.Aoi           Update          S21_NA#21165
 * 2018/03/02   Fujitsu         K.Ishizuka      Update          S21_NA#22956
 * 2018/05/11   Fujitsu         T.Aoi           Update          S21_NA#22139
 * </pre>
 */
public class NWAL1770_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // security violation check.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;

        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            EZDBStringItem param0 = (EZDBStringItem) params[0];
            ZYPEZDItemValueSetter.setValue(scrnMsg.splyQuoteNum, param0.getValue());
        } else {
            // To process the menu transition.
            scrnMsg.splyQuoteNum.clear();
        }

        NWAL1770CMsg bizMsg = new NWAL1770CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;
        NWAL1770CMsg bizMsg = (NWAL1770CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, TAB_CUSTOMER);
        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
        scrnMsg.setFocusItem(scrnMsg.splyQuoteNum);

        NWAL1770CommonLogic.initCommonButton(this);
        NWAL1770CommonLogicForScrnFields.setProtect(this, scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;

        // Header
        scrnMsg.splyQuoteNum.setNameForMessage("Quote Number");
        scrnMsg.dsOrdCatgDescTxt.setNameForMessage("Category");
        scrnMsg.dsOrdTpCd.setNameForMessage("Reason Code");
        scrnMsg.splyQuoteSrcTpCd.setNameForMessage("Source Name");
        scrnMsg.splyQuoteDt.setNameForMessage("Quote Date");
        scrnMsg.splyQuoteVldDaysAot.setNameForMessage("Days Valid");
        scrnMsg.custIssPoNum.setNameForMessage("Customer PO Number");
        scrnMsg.custIssPoDt.setNameForMessage("Customer PO Date");
        scrnMsg.slsRepTocNm.setNameForMessage("Sales Rep Name");
        scrnMsg.psnNum.setNameForMessage("Sales Rep Number"); // S21_NA#7861
        scrnMsg.prcCatgNm.setNameForMessage("Price List");
        scrnMsg.splyQuoteNm.setNameForMessage("Quote Name"); // 2018/03/02 S21_NA#22956 Add

        // Customer/Contact Tab (Customer)
        scrnMsg.billToCustAcctCd.setNameForMessage("Bill To Number");
        // Mod Start 2017/09/25 QC#19922
        //scrnMsg.billToCustCd.setNameForMessage("Bill To Location");
        scrnMsg.billToCustCd.setNameForMessage("Bill To Code");
        // Mod End 2017/09/25 QC#19922
        scrnMsg.billToCustAcctNm.setNameForMessage("Bill To Name");
        scrnMsg.shipToCustAcctCd.setNameForMessage("Ship To Number");
        // Mod Start 2017/09/25 QC#19922
        //scrnMsg.shipToCustCd.setNameForMessage("Ship To Location");
        scrnMsg.shipToCustCd.setNameForMessage("Ship To Code");
        // Mod End 2017/09/25 QC#19922
        scrnMsg.shipToCustAcctNm.setNameForMessage("Ship To Name");
        scrnMsg.sellToCustCd.setNameForMessage("Sold To Number");
        // Mod Start 2017/09/25 QC#19922
        //scrnMsg.soldToCustLocCd.setNameForMessage("Sold To Location");
        scrnMsg.soldToCustLocCd.setNameForMessage("Sold To Code");
        // Mod End 2017/09/25 QC#19922
        scrnMsg.soldToCustAcctNm.setNameForMessage("Sold To Name");

        // Add Start 2017/10/13 QC#20246-1(L3 Sol#454)
        scrnMsg.sellToFirstRefCmntTxt.setNameForMessage("Attention");
        // Add End 2017/10/13 QC#20246-1(L3 Sol#454)

        // Customer/Contact Tab (Contact)
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NWAL1770_ABMsg contactMsg = scrnMsg.A.no(i);
            contactMsg.ctacPsnTpCd_A.setNameForMessage("Role");
            contactMsg.ctacPsnFirstNm_A.setNameForMessage("First Name");
            contactMsg.ctacPsnLastNm_A.setNameForMessage("Last Name");
            contactMsg.ctacPsnTelNum_A.setNameForMessage("Phone");
            contactMsg.ctacPsnExtnNum_A.setNameForMessage("EXT");
            contactMsg.ctacPsnEmlAddr_A.setNameForMessage("EMail");
            contactMsg.ctacPsnFaxNum_A.setNameForMessage("Fax");
        }

        // Delivery / Payment Tab
        scrnMsg.frtCondDescTxt.setNameForMessage("Freight Terms");
        scrnMsg.carrSvcLvlDescTxt.setNameForMessage("Carrier Service Level");
        scrnMsg.shpgSvcLvlCd.setNameForMessage("Service Level");
        scrnMsg.spclHdlgTpCd.setNameForMessage("Special Handling");
        scrnMsg.carrAcctNum.setNameForMessage("Carrier Acct Number");
        scrnMsg.rddDt.setNameForMessage("Future Delivery Date");
        // Add 2016/08/30 S21_NA#11547
        scrnMsg.pmtTermCashDiscDescTxt.setNameForMessage("Payment Terms");

        // Item Tab
        for (int i = 0; i < scrnMsg.B.length(); i++) {
            NWAL1770_BBMsg itemLineMsg = scrnMsg.B.no(i);
            itemLineMsg.mdseCd_B.setNameForMessage("Item #");
            itemLineMsg.ordCustUomQty_B.setNameForMessage("Qty");
            itemLineMsg.custUomCd_B.setNameForMessage("UOM");
            itemLineMsg.prcCatgNm_B.setNameForMessage("Price List");
            itemLineMsg.dsOrdLineCatgCd_B.setNameForMessage("Line Category");
            itemLineMsg.rddDt_B.setNameForMessage("Future Delivery Date"); // 2018/02/09 QC#21165 Add 
            itemLineMsg.prcBaseDt_B.setNameForMessage("Pricing Date"); // QC#20580 2017/08/17 Add
            itemLineMsg.ordLineSrcCd_B.setNameForMessage("Line Source");
            itemLineMsg.rtlWhNm_B.setNameForMessage("Warehouse");
            itemLineMsg.rtlSwhNm_B.setNameForMessage("Sub Warehouse");
            itemLineMsg.dealSplyQuoteDtlSlsAmt_B.setNameForMessage("Sell Price");
            itemLineMsg.supdLockFlg_B.setNameForMessage("Supersede Lock");
        }

        // Comments Tab
        // 2018/05/11 QC#22139 Add Start
        scrnMsg.quotePrintCmntTxt.setNameForMessage("Quote Comments");
        scrnMsg.ordPrintCmntTxt.setNameForMessage("Order Comments");
        scrnMsg.shpgCmntPrintCd.setNameForMessage("Print on Confirmation");
        // 2018/05/11 QC#22139 Add End
        scrnMsg.shpgCmntTxt.setNameForMessage("Shipping Comments");
        scrnMsg.splyQuoteCmntTxt.setNameForMessage("Internal Order Comments");
        scrnMsg.invCmntTxt.setNameForMessage("Invoice Comments");

        // Additional Data Tab
        scrnMsg.firstBllgAttrbValTxt.setNameForMessage("Reference 1");
        scrnMsg.scdBllgAttrbValTxt.setNameForMessage("Reference 2");
        scrnMsg.thirdBllgAttrbValTxt.setNameForMessage("Reference 3");
        scrnMsg.frthBllgAttrbValTxt.setNameForMessage("Reference 4");
        scrnMsg.fifthBllgAttrbValTxt.setNameForMessage("Reference 5");
        scrnMsg.sixthBllgAttrbValTxt.setNameForMessage("Reference 6");
        scrnMsg.prcContrNum.setNameForMessage("Program");
    }
}
