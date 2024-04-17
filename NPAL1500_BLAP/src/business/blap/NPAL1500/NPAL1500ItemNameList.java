/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1500;

import java.util.LinkedHashMap;
import java.util.Map;

import parts.common.EZDItem;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/13   XXXX            N.Akaishi       Create          n/a
 * 2016/05/03   CSAI            K.Lee           Update          QC#7300
 * 2023/02/14   Hitachi         S.Dong          Update          QC#60966
 * </pre>
 */
public class NPAL1500ItemNameList {

    private Map<EZDItem, String> itemList = null;

    /**
     * Singleton instance.
     */
    private static final NPAL1500ItemNameList MY_INSTANCE = new NPAL1500ItemNameList();

    private NPAL1500SMsg sMsg = null;
    private NPAL1500CMsg cMsg = null;

    /**
     * Constructor.
     */
    private NPAL1500ItemNameList() {
        this.itemList = new LinkedHashMap<EZDItem, String>();
    }

    /**
     * Singleton instance getter.
     * @return NPAL1500Query
     */
    public static NPAL1500ItemNameList getInstance() {
        return MY_INSTANCE;
    }

    public void setMsgInfo(final NPAL1500CMsg cMsg, final NPAL1500SMsg sMsg) {
        this.itemList = null;
        this.itemList = new LinkedHashMap<EZDItem, String>();
        this.sMsg = sMsg;
        this.cMsg = cMsg;

        // Header
        itemList.put(this.cMsg.poNum, "PO Number");
        itemList.put(this.cMsg.dsPoTpCd, "PO type");
        itemList.put(this.cMsg.poHdrStsCd, "Header Status");
        itemList.put(this.cMsg.poApvlStsCd, "Approval Status");
        itemList.put(this.cMsg.poApvlDt, "Initial Approval Date");
        itemList.put(this.cMsg.poSubmtTs, "Date Created");
        itemList.put(this.cMsg.rqstRcvDt, "Date & Time Needed(Date)");
        itemList.put(this.cMsg.rqstRcvTm, "Date & Time Needed(Time)");
        // START 2023/02/14 S.Dong [QC#60966, ADD]
        itemList.put(this.cMsg.rqstShipDt, "Vendor Ship Date");
        // END 2023/02/14 S.Dong [QC#60966, ADD]
        itemList.put(this.cMsg.poOrdSrcCd, "Document Source type");
        itemList.put(this.cMsg.trxRefNum, "Source Doc#");
        itemList.put(this.cMsg.poQlfyCd, "Qualifier");
        itemList.put(this.cMsg.poSubmtPsnCd, "Buyer");
        itemList.put(this.cMsg.prchGrpCd, "Business Unit");
        itemList.put(this.cMsg.poOrdCmntTxt, "Description");
        itemList.put(this.cMsg.prntVndCd, "Supplier");
        itemList.put(this.cMsg.prntVndNm, "Supplier(Name)");
        itemList.put(this.cMsg.vndCd, "Supplier Site");
        itemList.put(this.cMsg.vndNm, "Supplier Site(Name)");
        itemList.put(this.cMsg.srcRtlWhCd_SC, "Source WH");
        itemList.put(this.cMsg.rtlWhNm_SC, "Source WH(Name)");
        itemList.put(this.cMsg.destRtlWhCd_DS, "Destionation WH");
        itemList.put(this.cMsg.rtlWhNm_DS, "Destionation WH(Name)");
        itemList.put(this.cMsg.shipToCustCd, "Ship To Customer");
        // sMsg
        itemList.put(this.sMsg.poNum, "PO Number");
        itemList.put(this.sMsg.dsPoTpCd, "PO type");
        itemList.put(this.sMsg.poHdrStsCd, "Header Status");
        itemList.put(this.sMsg.poApvlStsCd, "Approval Status");
        itemList.put(this.sMsg.poApvlDt, "Initial Approval Date");
        itemList.put(this.sMsg.poSubmtTs, "Date Created");
        itemList.put(this.sMsg.rqstRcvDt, "Date & Time Needed(Date)");
        itemList.put(this.sMsg.rqstRcvTm, "Date & Time Needed(Time)");
        // START 2023/02/14 S.Dong [QC#60966, ADD]
        itemList.put(this.sMsg.rqstShipDt, "Vendor Ship Date");
        // END 2023/02/14 S.Dong [QC#60966, ADD]
        itemList.put(this.sMsg.poOrdSrcCd, "Document Source type");
        itemList.put(this.sMsg.trxRefNum, "Source Doc#");
        itemList.put(this.sMsg.poQlfyCd, "Qualifier");
        itemList.put(this.sMsg.poSubmtPsnCd, "Buyer");
        itemList.put(this.sMsg.prchGrpCd, "Business Unit");
        itemList.put(this.sMsg.poOrdCmntTxt, "Description");
        itemList.put(this.sMsg.prntVndCd, "Supplier");
        itemList.put(this.sMsg.prntVndNm, "Supplier(Name)");
        itemList.put(this.sMsg.vndCd, "Supplier Site");
        itemList.put(this.sMsg.vndNm, "Supplier Site(Name)");
        itemList.put(this.sMsg.srcRtlWhCd_SC, "Source WH");
        itemList.put(this.sMsg.rtlWhNm_SC, "Source WH(Name)");
        itemList.put(this.sMsg.destRtlWhCd_DS, "Destionation WH");
        itemList.put(this.sMsg.rtlWhNm_DS, "Destionation WH(Name)");
        itemList.put(this.sMsg.shipToCustCd, "Ship To Customer");

        // Additional Header Tab
        // Ship To
        itemList.put(this.cMsg.shipToAddlLocNm_ST, "Additional Name(Ship To)");
        itemList.put(this.cMsg.xxAllLineAddr_ST, "Address Line(Ship To)");
        itemList.put(this.cMsg.shipToPostCd_ST, "Post Code(Ship To)");
        itemList.put(this.cMsg.shipToCtyAddr_ST, "City(Ship To)");
        itemList.put(this.cMsg.shipToCntyNm_ST, "County(Ship To)");
        itemList.put(this.cMsg.shipToStCd_ST, "State(Ship To)");
        itemList.put(this.cMsg.shipToProvNm_ST, "Province(Ship To)");
        itemList.put(this.cMsg.shipToCtryCd_ST, "Country(Ship To)");
        itemList.put(this.cMsg.ctryDescTxt_ST, "Country(name)(Ship To)");
        // sMsg
        itemList.put(this.sMsg.shipToAddlLocNm_ST, "Additional Name(Ship To)");
        itemList.put(this.sMsg.xxAllLineAddr_ST, "Address Line(Ship To)");
        itemList.put(this.sMsg.shipToPostCd_ST, "Post Code(Ship To)");
        itemList.put(this.sMsg.shipToCtyAddr_ST, "City(Ship To)");
        itemList.put(this.sMsg.shipToCntyNm_ST, "County(Ship To)");
        itemList.put(this.sMsg.shipToStCd_ST, "State(Ship To)");
        itemList.put(this.sMsg.shipToProvNm_ST, "Province(Ship To)");
        itemList.put(this.sMsg.shipToCtryCd_ST, "Country(Ship To)");
        itemList.put(this.sMsg.ctryDescTxt_ST, "Country(name)(Ship To)");
        // Bill To
        itemList.put(this.cMsg.varCharConstVal_B1, "Code(Bill To)");
        itemList.put(this.cMsg.varCharConstVal_B2, "Name(Bill To)");
        itemList.put(this.cMsg.varCharConstVal_B3, "Additional Name(Bill To)");
        itemList.put(this.cMsg.varCharConstVal_B4, "Address Line(Bill To)");
        itemList.put(this.cMsg.varCharConstVal_B5, "Post Code(Bill To)");
        itemList.put(this.cMsg.varCharConstVal_B6, "City(Bill To)");
        itemList.put(this.cMsg.varCharConstVal_B7, "County(Bill To)");
        itemList.put(this.cMsg.varCharConstVal_B8, "State(Bill To)");
        itemList.put(this.cMsg.varCharConstVal_B9, "Province(Bill To)");
        itemList.put(this.cMsg.varCharConstVal_BA, "Country(Bill To)");
        itemList.put(this.cMsg.ctryDescTxt_BT, "Country(name)(Bill To)");
        // sMsg
        itemList.put(this.sMsg.varCharConstVal_B1, "Code(Bill To)");
        itemList.put(this.sMsg.varCharConstVal_B2, "Name(Bill To)");
        itemList.put(this.sMsg.varCharConstVal_B3, "Additional Name(Bill To)");
        itemList.put(this.sMsg.varCharConstVal_B4, "Address Line(Bill To)");
        itemList.put(this.sMsg.varCharConstVal_B5, "Post Code(Bill To)");
        itemList.put(this.sMsg.varCharConstVal_B6, "City(Bill To)");
        itemList.put(this.sMsg.varCharConstVal_B7, "County(Bill To)");
        itemList.put(this.sMsg.varCharConstVal_B8, "State(Bill To)");
        itemList.put(this.sMsg.varCharConstVal_B9, "Province(Bill To)");
        itemList.put(this.sMsg.varCharConstVal_BA, "Country(Bill To)");
        itemList.put(this.sMsg.ctryDescTxt_BT, "Country(name)(Bill To)");
        // CSA Return Address
        itemList.put(this.cMsg.rtrnShipToRtlWhCd_RW, "Code(CSA)");
        itemList.put(this.cMsg.rtlWhNm_RW, "Name(CSA)");
        itemList.put(this.cMsg.addlLocNm_RW, "Additional Name(CSA)");
        itemList.put(this.cMsg.xxAllLineAddr_RW, "Address Line(CSA)");
        itemList.put(this.cMsg.postCd_RW, "Post Code(CSA)");
        itemList.put(this.cMsg.ctyAddr_RW, "City(CSA)");
        itemList.put(this.cMsg.cntyNm_RW, "County(CSA)");
        itemList.put(this.cMsg.stCd_RW, "State(CSA)");
        itemList.put(this.cMsg.provNm_RW, "Province(CSA)");
        itemList.put(this.cMsg.ctryCd_RW, "Country(CSA)");
        itemList.put(this.cMsg.ctryDescTxt_RW, "Country(name)(CSA)");
        // sMsg
        itemList.put(this.sMsg.rtrnShipToRtlWhCd_RW, "Code(CSA)");
        itemList.put(this.sMsg.rtlWhNm_RW, "Name(CSA)");
        itemList.put(this.sMsg.addlLocNm_RW, "Additional Name(CSA)");
        itemList.put(this.sMsg.xxAllLineAddr_RW, "Address Line(CSA)");
        itemList.put(this.sMsg.postCd_RW, "Post Code(CSA)");
        itemList.put(this.sMsg.ctyAddr_RW, "City(CSA)");
        itemList.put(this.sMsg.cntyNm_RW, "County(CSA)");
        itemList.put(this.sMsg.stCd_RW, "State(CSA)");
        itemList.put(this.sMsg.provNm_RW, "Province(CSA)");
        itemList.put(this.sMsg.ctryCd_RW, "Country(CSA)");
        itemList.put(this.sMsg.ctryDescTxt_RW, "Country(name)(CSA)");
        // Freight Information
        itemList.put(this.cMsg.frtCondCd, "Freight Term");
        itemList.put(this.cMsg.shpgSvcLvlCd, "Service Level");
        itemList.put(this.cMsg.carrCd, "Carrier");
        itemList.put(this.cMsg.carrNm, "Carrier(Name)");
        itemList.put(this.cMsg.carrAcctNum, "Carrier Account");
        // sMsg
        itemList.put(this.sMsg.frtCondCd, "Freight Term");
        itemList.put(this.sMsg.shpgSvcLvlCd, "Service Level");
        itemList.put(this.sMsg.carrCd, "Carrier");
        itemList.put(this.sMsg.carrNm, "Carrier(Name)");
        itemList.put(this.sMsg.carrAcctNum, "Carrier Account");
        // Header Details
        itemList.put(this.cMsg.carrAcctNum, "PO Term");
        itemList.put(this.cMsg.xxDsMultMsgDplyTxt_SI, "Special Instructions");
        itemList.put(this.cMsg.xxDsMultMsgDplyTxt_RN, "Receiver Note");
        itemList.put(this.cMsg.xxDsMultMsgDplyTxt_SN, "Shipper Note");
        // sMsg
        itemList.put(this.sMsg.carrAcctNum, "PO Term");
        itemList.put(this.sMsg.xxDsMultMsgDplyTxt_SI, "Special Instructions");
        itemList.put(this.sMsg.xxDsMultMsgDplyTxt_RN, "Receiver Note");
        itemList.put(this.sMsg.xxDsMultMsgDplyTxt_SN, "Shipper Note");

        // Detail Tab
        // Detail Header
        itemList.put(this.cMsg.xxTotAmt, "Total Amount");
        itemList.put(this.cMsg.ccyCd, "Currency");
        itemList.put(this.cMsg.svcConfigMstrPk, "ConfigId");
        // sMsg
        itemList.put(this.sMsg.xxTotAmt, "Total Amount");
        itemList.put(this.sMsg.ccyCd, "Currency");
        itemList.put(this.sMsg.svcConfigMstrPk, "ConfigId");
        
        // Detail List
        for (int i = 0; i < this.sMsg.A.getValidCount(); i++) {
            itemList.put(this.sMsg.A.no(i).dispPoDtlLineNum_A1, "Line#");
            itemList.put(this.sMsg.A.no(i).poLineTpCd_A1, "Line Type");
            itemList.put(this.sMsg.A.no(i).mdseCd_A1, "Item#");
            itemList.put(this.sMsg.A.no(i).aslMdseCd_A1, "Supplier Item#");
            itemList.put(this.sMsg.A.no(i).mdseDescShortTxt_A1, "Item Description");
            itemList.put(this.sMsg.A.no(i).entDealNetUnitPrcAmt_A1, "Line Price");
            itemList.put(this.sMsg.A.no(i).poDispQty_A1, "Order Qty");
            itemList.put(this.sMsg.A.no(i).poDispUomCd_A1, "UOM");
            itemList.put(this.sMsg.A.no(i).rqstRcvDt_A1, "Date Needed");
            // START 2023/02/14 S.Dong [QC#60966, ADD]
            itemList.put(this.sMsg.A.no(i).rqstShipDt_A1, "Vendor Ship Date");
            // END 2023/02/14 S.Dong [QC#60966, ADD]
            itemList.put(this.sMsg.A.no(i).destRtlSwhCd_A1, "Dest SWH");
            itemList.put(this.sMsg.A.no(i).poMatchTpCd_A1, "Match Opt");
            itemList.put(this.sMsg.A.no(i).poLineStsCd_A1, "Line Status");
            itemList.put(this.sMsg.A.no(i).entPoDtlDealNetAmt_A1, "Ext. Total");
            itemList.put(this.sMsg.A.no(i).poRcvQty_A1, "Received Qty");
            itemList.put(this.sMsg.A.no(i).poCancQty_A1, "Cancelled Qty");
            itemList.put(this.sMsg.A.no(i).poInvQty_A1, "Invoiced Qty");
            itemList.put(this.sMsg.A.no(i).vndInvtyLocCd_A1, "Vendor WH");
            itemList.put(this.sMsg.A.no(i).srcRtlSwhCd_A1, "Source SWH");
            itemList.put(this.sMsg.A.no(i).stkStsCd_A1, "Stock Status");
            itemList.put(this.sMsg.A.no(i).serNumListTxt_A1, "Serial#");
            itemList.put(this.sMsg.A.no(i).svcConfigMstrPk_A1, "Config ID");
            itemList.put(this.sMsg.A.no(i).xxScrItem130Txt_CH, "Charge ACC");
            itemList.put(this.sMsg.A.no(i).xxScrItem130Txt_AC, "Accrual ACC");
            itemList.put(this.sMsg.A.no(i).xxScrItem130Txt_VA, "Variance ACC");
            itemList.put(this.sMsg.A.no(i).poOrdDtlCmntTxt_A1, "Text");
            itemList.put(this.sMsg.A.no(i).prchReqNum_A1, "PO Req#");
            itemList.put(this.sMsg.A.no(i).prchReqLineNum_A1, "PO Req Line#");
        }
        // Detail Footer
        itemList.put(this.cMsg.trsmtMethTpCd, "Transmit Method Type");
        itemList.put(this.cMsg.sendPoFaxNum, "Transmit Method Text(FAX)");
        itemList.put(this.cMsg.sendPoEmlAddr, "Transmit Method Text(E-Mail)");
        //sMsg
        itemList.put(this.sMsg.trsmtMethTpCd, "Transmit Method Type");
        itemList.put(this.sMsg.sendPoFaxNum, "Transmit Method Text(FAX)");
        itemList.put(this.sMsg.sendPoEmlAddr, "Transmit Method Text(E-Mail)");
    }

    public Map<EZDItem, String> getItemList() {
        return this.itemList;
    }

    public String getItemName(EZDItem item) {
        return this.itemList.get(item);
    }
}
