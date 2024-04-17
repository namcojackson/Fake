/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1500;

import static business.servlet.NPAL1500.constant.NPAL1500Constant.BIZ_ID;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.TAB_DETAIL;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1500.NPAL1500CMsg;
import business.servlet.NPAL1500.common.NPAL1500CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * <pre>
 * Business ID : NPAL1500 PO Entry
 * Function Name : Init
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/18/2015   CITS            N Akaishi       Create          n/a
 * 2016/05/03   CSAI            K.Lee           Update          QC#7300
 * 2016/09/09   CITS            R.Shimamoto     Update          QC#13985
 * 2019/10/04   CITS            R.Shimamoto     Update          QC#53392
 * 2023/02/15   Hitachi         S.Dong          Update          QC#60966
 *</pre>
 */
public class NPAL1500_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
         checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;

        Object[] params = (Object[]) getArgForSubScreen();

        // In case of transition from PO Search(NPAL1510)
        if (params != null && params.length > 0) {
            if (params.length == 1) {
                // from NPAL0120
                Object param01 = (Object) params[0];
                if (param01 instanceof EZDBStringItem) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poNum, (EZDBStringItem) param01);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poNum_HD, (EZDBStringItem) param01);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poNum_G1, (EZDBStringItem) param01);
                } else {
                    // String
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poNum, (String) param01);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poNum_HD, (String) param01);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poNum_G1, (String) param01);
                }
                ZYPEZDItemValueSetter.setValue(scrnMsg.glblCmpyCd, getGlobalCompanyCode());
            }
        } else {
            // To process the menu transition.
            scrnMsg.poNum.clear();
            scrnMsg.poNum_HD.clear();
            scrnMsg.poNum_G1.clear();
        }

        NPAL1500CMsg bizMsg = new NPAL1500CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;
        NPAL1500CMsg bizMsg = (NPAL1500CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.xxDplyTab.setValue(TAB_DETAIL);
        scrnMsg.xxChkBox_LO.setValue(ZYPConstant.FLG_ON_Y);

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_ID);
        NPAL1500CommonLogic.ctrlScreenItem(this, scrnMsg, funcList);

        //QC#18602(Sol#102) ADD Start
        if (PO_STS.CLOSED.equals(scrnMsg.poStsCd_H.getValue()) && PO_HDR_STS.CLOSED.equals(scrnMsg.poHdrStsCd.getValue())) {
            scrnMsg.xxChkBox_LO.setInputProtected(true);
            scrnMsg.xxChkBox_LO.setValue(ZYPConstant.FLG_OFF_N);
        } else {
            scrnMsg.xxChkBox_LO.setInputProtected(false);
            scrnMsg.xxChkBox_LO.setValue(ZYPConstant.FLG_ON_Y);
        }
        //QC#18602(Sol#102) ADD End

        scrnMsg.setFocusItem(scrnMsg.poNum);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;

        scrnMsg.poNum.setNameForMessage("PO Number");
        scrnMsg.dsPoTpCd.setNameForMessage("PO Type");
        scrnMsg.poHdrStsDescTxt.setNameForMessage("Header Status");
        scrnMsg.poApvlStsDescTxt.setNameForMessage("Approval Status");
        scrnMsg.poApvlDt.setNameForMessage("Approval Date");
        scrnMsg.poSubmtTs.setNameForMessage("Date Created");
        scrnMsg.rqstRcvDt.setNameForMessage("Date & Time Needed");
        // START 2023/02/15 S.Dong [QC#60966, ADD]
        scrnMsg.rqstShipDt.setNameForMessage("Vendor Ship Date");
        // END 2023/02/15 S.Dong [QC#60966, ADD]
        scrnMsg.poOrdSrcDescTxt.setNameForMessage("Document Source Type");
        scrnMsg.trxRefNum.setNameForMessage("Source Doc#");
        scrnMsg.poQlfyCd.setNameForMessage("Qualifier");
        scrnMsg.fullPsnNm.setNameForMessage("Buyer");
        scrnMsg.prchGrpCd.setNameForMessage("Planning Group");
        scrnMsg.poOrdCmntTxt.setNameForMessage("Descreption");
        scrnMsg.prntVndCd.setNameForMessage("Supplier");
        scrnMsg.prntVndNm.setNameForMessage("Supplier");
        scrnMsg.vndCd.setNameForMessage("Supplier Site");
        scrnMsg.vndNm.setNameForMessage("Supplier Site");
        scrnMsg.srcRtlWhCd_SC.setNameForMessage("Source WH");
        scrnMsg.rtlWhNm_SC.setNameForMessage("Source WH");
        scrnMsg.destRtlWhCd_DS.setNameForMessage("Destination WH");
        scrnMsg.rtlWhNm_DS.setNameForMessage("Destination WH");
        scrnMsg.shipToCustCd.setNameForMessage("Ship To Customer");
        scrnMsg.shipToLocNm.setNameForMessage("Ship To Customer");

        //Tab Addl Header
        scrnMsg.shipToAddlLocNm_ST.setNameForMessage("Ship to Location Addiotional Name");
        scrnMsg.xxAllLineAddr_ST.setNameForMessage("Ship to Location Address Line");
        scrnMsg.shipToPostCd_ST.setNameForMessage("Ship to Location Postal Code");
        scrnMsg.shipToCtyAddr_ST.setNameForMessage("Ship to Location City");
        scrnMsg.shipToCntyNm_ST.setNameForMessage("Ship to Location County");
        scrnMsg.shipToStCd_ST.setNameForMessage("Ship to Location State");
        scrnMsg.shipToProvNm_ST.setNameForMessage("Ship to Location Province");
        scrnMsg.shipToCtryCd_ST.setNameForMessage("Ship to Location Country");
        scrnMsg.ctryDescTxt_ST.setNameForMessage("Ship to Location Country");
        scrnMsg.ctacPsnNm.setNameForMessage("Ship to Location Contact Person");
        scrnMsg.varCharConstVal_B1.setNameForMessage("Bill to Customer Code");
        scrnMsg.varCharConstVal_B2.setNameForMessage("Bill to Customer Name");
        scrnMsg.varCharConstVal_B3.setNameForMessage("Bill to Customer Additional Name");
        scrnMsg.xxAllLineAddr_BT.setNameForMessage("Bill to Customer Address Line");
        scrnMsg.varCharConstVal_B4.setNameForMessage("Bill to Customer Postal Code");
        scrnMsg.varCharConstVal_B5.setNameForMessage("Bill to Customer City");
        scrnMsg.varCharConstVal_B6.setNameForMessage("Bill to Customer County");
        scrnMsg.varCharConstVal_B7.setNameForMessage("Bill to Customer State");
        scrnMsg.varCharConstVal_B8.setNameForMessage("Bill to Customer Province");
        scrnMsg.varCharConstVal_B9.setNameForMessage("Bill to Customer Country");
        scrnMsg.ctryDescTxt_BT.setNameForMessage("Bill to Customer Country");
        scrnMsg.rtrnShipToRtlWhCd_RW.setNameForMessage("CSA Return Address Code");
        scrnMsg.rtlWhNm_RW.setNameForMessage("CSA Return Address Name");
        scrnMsg.addlLocNm_RW.setNameForMessage("CSA Return Address Additional Name");
        scrnMsg.xxAllLineAddr_RW.setNameForMessage("CSA Return Address Addresss Line");
        scrnMsg.postCd_RW.setNameForMessage("CSA Return Address Postal Code");
        scrnMsg.ctyAddr_RW.setNameForMessage("CSA Return Address City");
        scrnMsg.cntyNm_RW.setNameForMessage("CSA Return Address County");
        scrnMsg.stCd_RW.setNameForMessage("CSA Return Address State");
        scrnMsg.provNm_RW.setNameForMessage("CSA Return Address Province");
        scrnMsg.ctryCd_RW.setNameForMessage("CSA Return Address Country");
        scrnMsg.ctryDescTxt_RW.setNameForMessage("CSA Return Address Country");
        scrnMsg.frtCondCd.setNameForMessage("Freight Term");
        scrnMsg.shpgSvcLvlCd.setNameForMessage("Service Level");
        scrnMsg.carrCd.setNameForMessage("Carrier");
        scrnMsg.carrNm.setNameForMessage("Carrier");
        scrnMsg.carrAcctNum.setNameForMessage("Carrier Account");
        scrnMsg.vndPmtTermDescTxt.setNameForMessage("PO Term");
        scrnMsg.xxDsMultMsgDplyTxt_SI.setNameForMessage("Special instructions");
        scrnMsg.xxDsMultMsgDplyTxt_RN.setNameForMessage("Receiver Note");
        scrnMsg.xxDsMultMsgDplyTxt_SN.setNameForMessage("Shipper Note");

        // Tab Detail
        scrnMsg.svcConfigMstrPk.setNameForMessage("Select from Config");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox_A1.setNameForMessage("Check Box");
            scrnMsg.A.no(i).poLineTpCd_A1.setNameForMessage("Line Type");
            scrnMsg.A.no(i).mdseCd_A1.setNameForMessage("Item#");
            scrnMsg.A.no(i).aslMdseCd_A1.setNameForMessage("Supplier Item#");
            scrnMsg.A.no(i).mdseDescShortTxt_A1.setNameForMessage("Item Description");
            scrnMsg.A.no(i).entDealNetUnitPrcAmt_A1.setNameForMessage("Line Price");
            scrnMsg.A.no(i).poDispQty_A1.setNameForMessage("Order Qty");
            scrnMsg.A.no(i).rqstRcvDt_A1.setNameForMessage("Date Needed");
            // START 2023/02/15 S.Dong [QC#60966, ADD]
            scrnMsg.A.no(i).rqstShipDt_A1.setNameForMessage("Vendor Ship Date");
            // END 2023/02/15 S.Dong [QC#60966, ADD]
            scrnMsg.A.no(i).destRtlSwhCd_A1.setNameForMessage("Dest SWH");
            scrnMsg.A.no(i).poLineStsDescTxt_A1.setNameForMessage("Line Status");
            scrnMsg.A.no(i).srcRtlSwhCd_A1.setNameForMessage("Src WH");
            scrnMsg.A.no(i).stkStsCd_A1.setNameForMessage("Stock Status");
            scrnMsg.A.no(i).serNumListTxt_A1.setNameForMessage("Serial");
            scrnMsg.A.no(i).poOrdDtlCmntTxt_A1.setNameForMessage("Text");
            scrnMsg.A.no(i).poDtlDiscPct_A1.setNameForMessage("Discount%");
            // QC#53392 2019/10/03 Add Start
            scrnMsg.A.no(i).poDtlDiscPrcAmt_A1.setNameForMessage("Disc Price");
            // QC#53392 2019/10/03 Add End

            scrnMsg.A.no(i).xxScrItem130Txt_CH.setNameForMessage("Charge Account");
            scrnMsg.A.no(i).xxScrItem130Txt_AC.setNameForMessage("Accrual Account");
            scrnMsg.A.no(i).xxScrItem130Txt_VA.setNameForMessage("Variance Account");

        }
    }
}
