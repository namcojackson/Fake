/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1500;

import java.util.LinkedHashMap;
import java.util.Map;

import parts.common.EZDCItem;
import parts.common.EZDSItem;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/05   Fujitsu         S.Takami        Create          n/a
 * 2016/06/08   Fujitsu         T.Yoshida       Update          S21_NA#8166 For Performance(Not Use Singleton)
 * 2016/06/27   Fujitsu         S.Takami        Update          S21_NA#10841
 * 2016/08/09   Fujitsu         Y.Taoka         Update          S21_NA#8388
 * 2016/08/30   Fujitsu         S.Takami        Update          S21_NA#9806
 * 2016/10/11   Fujitsu         S.Takami        Update          S21_NA#7924-2
 * 2016/10/17   Fujitsu         K.Sato          Update          S21_NA#13011
 * 2017/08/15   Fujitsu         S.Takami        Update          S21_NA#20377
 * 2017/12/12   Fujitsu         N.Sugiura       Update          S21_NA#20164
 * 2018/01/31   Fujitsu         S.Takami        Update          S21_NA#19808
 * </pre>
 */
public class NWAL1500ItemNameList {

    /** Item Map Key: EZDItem Value: Item Name */
    private Map<EZDCItem, String> itemList = null;

    // 2018/01/31 S21_NA#19808 Add Start
    /** Item Map Key: EZDSItem Value: Item Name */
    private Map<EZDSItem, String> glblItemList = null;
    // 2018/01/31 S21_NA#19808 Add End
    // For Performance QC#8166 Del Start
    // /**
    //  * Singleton instance.
    //  */
    // private static final NWAL1500ItemNameList MY_INSTANCE = new NWAL1500ItemNameList();

    // private NWAL1500CMsg bizMsg = null;
    // For Performance QC#8166 Del End

    /**
     * Constructor.
     * @param bizMsg NWAL1500CMsg
     */
    public NWAL1500ItemNameList(NWAL1500CMsg bizMsg) { // For Performance QC#8166 Mod [private -> public]
        itemList = new LinkedHashMap<EZDCItem, String>();
        setBizMsg(bizMsg);
    }


    // 2018/01/31 S21_NA#19808 Add Start
    /**
     * Constructor.
     * @param glblMsg NWAL1500SMsg
     */
    public NWAL1500ItemNameList(NWAL1500SMsg glblMsg) {
        glblItemList = new LinkedHashMap<EZDSItem, String>();
        setBizMsg(glblMsg);
    }
    // 2018/01/31 S21_NA#19808 Add End
    // For Performance QC#8166 Del Start
    // /**
    //  * Singleton instance getter.
    //  * @return NWAL1500Query
    //  */
    // public static NWAL1500ItemNameList getInstance() {
    //     return MY_INSTANCE;
    // }
    // For Performance QC#8166 Del End

    private void setBizMsg(final NWAL1500CMsg bizMsg) { // For Performance QC#8166 Mod [public -> private]

        // For Performance QC#8166 Del Start
        // itemList = null;
        // itemList = new LinkedHashMap<EZDCItem, String>();

        // bizMsg = bizMsg;
        // For Performance QC#8166 Del End

        itemList.put(bizMsg.dsOrdCatgDescTxt, "Category");
        itemList.put(bizMsg.dsOrdTpCd, "Reason Code");
        itemList.put(bizMsg.ordDt, "Order Date");

        // Customer Information
        itemList.put(bizMsg.billToCustAcctNm, "Bill To Name");
        itemList.put(bizMsg.billToCustAcctCd, "Bill To Number");
        itemList.put(bizMsg.billToCustCd, "Bill To Location");
        itemList.put(bizMsg.shipToCustAcctNm, "Ship To Name");
        itemList.put(bizMsg.shipToCustAcctCd, "Ship To Number");
        itemList.put(bizMsg.shipToCustCd, "Ship To Location");
        itemList.put(bizMsg.sellToFirstRefCmntTxt, "Attention"); // 2017/12/12 S21_NA#20164 Add
        itemList.put(bizMsg.soldToCustAcctNm, "Sold To Name");
        itemList.put(bizMsg.sellToCustCd, "Sold To Number");
        itemList.put(bizMsg.soldToCustLocCd, "Sold To Location");

        // Header Details
        itemList.put(bizMsg.negoDealAmt, "Negotiated Deal");
        itemList.put(bizMsg.slsRepTocNm, "Salesreps");
        itemList.put(bizMsg.slsRepTocCd, "Salesrep ID");
        itemList.put(bizMsg.prcCatgNm, "Price List");
        itemList.put(bizMsg.custIssPoNum, "Customer PO");
        itemList.put(bizMsg.custIssPoDt, "Customer PO Date");

        // Freight Information
        itemList.put(bizMsg.frtCondDescTxt, "Freight Terms");
        itemList.put(bizMsg.shpgSvcLvlCd, "Service Level");
        itemList.put(bizMsg.carrSvcLvlDescTxt, "Carrier Service Level");

        // Payment Terms Information
        itemList.put(bizMsg.pmtTermCashDiscDescTxt, "Payment Terms");
        itemList.put(bizMsg.dsPmtMethCd, "Payment Method");

        // Additional Details
        itemList.put(bizMsg.flPrcListNm, "Floor Price List");
        itemList.put(bizMsg.ordSgnDt, "Customer Signed Date");
        itemList.put(bizMsg.prcContrNum, "Association Program");
        itemList.put(bizMsg.leaseTermMthAot, "Lease Term");
        itemList.put(bizMsg.dclnSvcCd, "Customer Declines Equipment Maintenance");  // S21_NA#8388 ADD
        itemList.put(bizMsg.psnNum, "Salerep Number");  // 2016/10/11 S21_NA#7924-2 Add

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            itemList.put(bizMsg.A.no(i).shipToCustAcctCd_LC, "Ship To Account");
            itemList.put(bizMsg.A.no(i).shipToCustCd_LC, "Ship To Location");
            itemList.put(bizMsg.A.no(i).shipToCustAcctNm_LC, "Ship To Name");
            itemList.put(bizMsg.A.no(i).billToCustAcctCd_LC, "Bill To Account");
            itemList.put(bizMsg.A.no(i).billToCustCd_LC, "Bill To Location");
            itemList.put(bizMsg.A.no(i).billToCustAcctNm_LC, "Bill To Name");

            itemList.put(bizMsg.A.no(i).configTpCd_LC, "Configuratin Type");
            itemList.put(bizMsg.A.no(i).svcConfigMstrPk_LC, "Config ID");
            itemList.put(bizMsg.A.no(i).mdlNm_LC, "Config ID");

            itemList.put(bizMsg.A.no(i).dclnSvcCd_LC, "Customer Declines Equipment Maintenance"); // S21_NA#8388 ADD

            // 2016/08/30 S21_NA#9806 Add Start
            itemList.put(bizMsg.A.no(i).csmpContrNum_LC, "CSMP Number");
            itemList.put(bizMsg.A.no(i).dlrRefNum_LC, "CSA Dealer Ref#");
            // 2016/08/30 S21_NA#9806 Add End
        }

        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            itemList.put(bizMsg.B.no(i).mdseCd_LL, "Item#");
            itemList.put(bizMsg.B.no(i).ordCustUomQty_LL, "Order Qty");
            itemList.put(bizMsg.B.no(i).entCpoDtlDealSlsAmt_LL, "Sell Price");
            itemList.put(bizMsg.B.no(i).prcCatgNm_LL, "Sell Price List");
            // S21_NA#13011 MOD Start
//            itemList.put(bizMsg.B.no(i).rtlWhCd_LL, "Warehouse");
//            itemList.put(bizMsg.B.no(i).rtlSwhCd_LL, "Sub Warehouse");
            itemList.put(bizMsg.B.no(i).rtlWhNm_LL, "Warehouse");
            itemList.put(bizMsg.B.no(i).rtlSwhNm_LL, "Sub Warehouse");
            // S21_NA#13011 MOD End
            itemList.put(bizMsg.B.no(i).serNum_LL, "Serial#");
            itemList.put(bizMsg.B.no(i).sbstMdseCd_LL, "Substitute Item");
            itemList.put(bizMsg.B.no(i).flPrcListNm_LL, "Floor Price List");
            itemList.put(bizMsg.B.no(i).ordLineSrcCd_LL, "Line Source"); // 2016/06/27 S21_NA#10841 Add
            itemList.put(bizMsg.B.no(i).dsOrdLineCatgCd_LL, "Line Category"); // 2016/06/27 S21_NA#10841 Add
            // 2017/08/15 S21_NA#20377 Add Start
            itemList.put(bizMsg.B.no(i).prcBaseDt_LL, "Pricing Date");
            // 2017/08/15 S21_NA#20377 Add End
        }

        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            itemList.put(bizMsg.C.no(i).shipToCustAcctCd_RC, "Ship To Account");
            itemList.put(bizMsg.C.no(i).shipToCustCd_RC, "Ship To Location");
            itemList.put(bizMsg.C.no(i).shipToCustAcctNm_RC, "Ship To Name");
            itemList.put(bizMsg.C.no(i).billToCustAcctCd_RC, "Bill To Account");
            itemList.put(bizMsg.C.no(i).billToCustCd_RC, "Bill To Location");
            itemList.put(bizMsg.C.no(i).billToCustAcctNm_RC, "Bill To Name");

            itemList.put(bizMsg.C.no(i).configTpCd_RC, "Configuratin Type");
            itemList.put(bizMsg.C.no(i).svcConfigMstrPk_RC, "Config ID");
            itemList.put(bizMsg.C.no(i).mdlNm_RC, "Config ID");

            // 2016/08/30 S21_NA#9806 Add Start
            itemList.put(bizMsg.C.no(i).csmpContrNum_RC, "CSMP Number");
            itemList.put(bizMsg.C.no(i).dlrRefNum_RC, "CSA Dealer Ref#");
            // 2016/08/30 S21_NA#9806 Add End
        }

        for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
            itemList.put(bizMsg.D.no(i).mdseCd_RL, "Item#");
            itemList.put(bizMsg.D.no(i).ordCustUomQty_RL, "Order Qty");
            itemList.put(bizMsg.D.no(i).entCpoDtlDealSlsAmt_RL, "Sell Price");
            itemList.put(bizMsg.D.no(i).prcCatgNm_RL, "Price List");
            itemList.put(bizMsg.D.no(i).rtlWhNm_RL, "Warehouse");
            // S21_NA#13011 MOD Start
//            itemList.put(bizMsg.D.no(i).rtlSwhCd_RL, "Sub Warehouse");
            itemList.put(bizMsg.D.no(i).rtlSwhNm_RL, "Sub Warehouse");
            // S21_NA#13011 MOD End
            itemList.put(bizMsg.D.no(i).serNum_RL, "Serial#");
            itemList.put(bizMsg.D.no(i).flPrcListNm_RL, "Floor Price List");
            itemList.put(bizMsg.D.no(i).rtrnRsnCd_RL, "Return Reason");
            // 2017/08/15 S21_NA#20377 Add Start
            itemList.put(bizMsg.D.no(i).prcBaseDt_RL, "Pricing Date");
            // 2017/08/15 S21_NA#20377 Add End
        }
    }

    // For Performance QC#8166 Del Start
    // public Map<EZDCItem, String> getItemList() {
    //     return itemList;
    // }
    // For Performance QC#8166 Del End

    /**
     * Getting Item Name
     * @param item EZDItem
     * @return Item Name
     */
    public String getItemName(EZDCItem item) {
        return this.itemList.get(item);
    }

    // 2018/01/31 S21_NA#19808 Add Start

    private void setBizMsg(final NWAL1500SMsg glblMsg) { // For Performance QC#8166 Mod [public -> private]

        // Header data, need?
//        itemList.put(bizMsg.dsOrdCatgDescTxt, "Category");
//        itemList.put(bizMsg.dsOrdTpCd, "Reason Code");
//        itemList.put(bizMsg.ordDt, "Order Date");
//
//        // Customer Information
//        itemList.put(bizMsg.billToCustAcctNm, "Bill To Name");
//        itemList.put(bizMsg.billToCustAcctCd, "Bill To Number");
//        itemList.put(bizMsg.billToCustCd, "Bill To Location");
//        itemList.put(bizMsg.shipToCustAcctNm, "Ship To Name");
//        itemList.put(bizMsg.shipToCustAcctCd, "Ship To Number");
//        itemList.put(bizMsg.shipToCustCd, "Ship To Location");
//        itemList.put(bizMsg.sellToFirstRefCmntTxt, "Attention");
//        itemList.put(bizMsg.soldToCustAcctNm, "Sold To Name");
//        itemList.put(bizMsg.sellToCustCd, "Sold To Number");
//        itemList.put(bizMsg.soldToCustLocCd, "Sold To Location");
//
//        // Header Details
//        itemList.put(bizMsg.negoDealAmt, "Negotiated Deal");
//        itemList.put(bizMsg.slsRepTocNm, "Salesreps");
//        itemList.put(bizMsg.slsRepTocCd, "Salesrep ID");
//        itemList.put(bizMsg.prcCatgNm, "Price List");
//        itemList.put(bizMsg.custIssPoNum, "Customer PO");
//        itemList.put(bizMsg.custIssPoDt, "Customer PO Date");
//
//        // Freight Information
//        itemList.put(bizMsg.frtCondDescTxt, "Freight Terms");
//        itemList.put(bizMsg.shpgSvcLvlCd, "Service Level");
//        itemList.put(bizMsg.carrSvcLvlDescTxt, "Carrier Service Level");
//
//        // Payment Terms Information
//        itemList.put(bizMsg.pmtTermCashDiscDescTxt, "Payment Terms");
//        itemList.put(bizMsg.dsPmtMethCd, "Payment Method");
//
//        // Additional Details
//        itemList.put(bizMsg.flPrcListNm, "Floor Price List");
//        itemList.put(bizMsg.ordSgnDt, "Customer Signed Date");
//        itemList.put(bizMsg.prcContrNum, "Association Program");
//        itemList.put(bizMsg.leaseTermMthAot, "Lease Term");
//        itemList.put(bizMsg.dclnSvcCd, "Customer Declines Equipment Maintenance");
//        itemList.put(bizMsg.psnNum, "Salerep Number");

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            glblItemList.put(glblMsg.A.no(i).shipToCustAcctCd_LC, "Ship To Account");
            glblItemList.put(glblMsg.A.no(i).shipToCustCd_LC, "Ship To Location");
            glblItemList.put(glblMsg.A.no(i).shipToCustAcctNm_LC, "Ship To Name");
            glblItemList.put(glblMsg.A.no(i).billToCustAcctCd_LC, "Bill To Account");
            glblItemList.put(glblMsg.A.no(i).billToCustCd_LC, "Bill To Location");
            glblItemList.put(glblMsg.A.no(i).billToCustAcctNm_LC, "Bill To Name");

            glblItemList.put(glblMsg.A.no(i).configTpCd_LC, "Configuratin Type");
            glblItemList.put(glblMsg.A.no(i).svcConfigMstrPk_LC, "Config ID");
            glblItemList.put(glblMsg.A.no(i).mdlNm_LC, "Config ID");

            glblItemList.put(glblMsg.A.no(i).dclnSvcCd_LC, "Customer Declines Equipment Maintenance");

            glblItemList.put(glblMsg.A.no(i).csmpContrNum_LC, "CSMP Number");
            glblItemList.put(glblMsg.A.no(i).dlrRefNum_LC, "CSA Dealer Ref#");
        }

        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            glblItemList.put(glblMsg.B.no(i).mdseCd_LL, "Item#");
            glblItemList.put(glblMsg.B.no(i).ordCustUomQty_LL, "Order Qty");
            glblItemList.put(glblMsg.B.no(i).entCpoDtlDealSlsAmt_LL, "Sell Price");
            glblItemList.put(glblMsg.B.no(i).prcCatgNm_LL, "Sell Price List");
            glblItemList.put(glblMsg.B.no(i).rtlWhNm_LL, "Warehouse");
            glblItemList.put(glblMsg.B.no(i).rtlSwhNm_LL, "Sub Warehouse");
            glblItemList.put(glblMsg.B.no(i).serNum_LL, "Serial#");
            glblItemList.put(glblMsg.B.no(i).sbstMdseCd_LL, "Substitute Item");
            glblItemList.put(glblMsg.B.no(i).flPrcListNm_LL, "Floor Price List");
            glblItemList.put(glblMsg.B.no(i).ordLineSrcCd_LL, "Line Source");
            glblItemList.put(glblMsg.B.no(i).dsOrdLineCatgCd_LL, "Line Category");
            glblItemList.put(glblMsg.B.no(i).prcBaseDt_LL, "Pricing Date");
        }

        for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
            glblItemList.put(glblMsg.C.no(i).shipToCustAcctCd_RC, "Ship To Account");
            glblItemList.put(glblMsg.C.no(i).shipToCustCd_RC, "Ship To Location");
            glblItemList.put(glblMsg.C.no(i).shipToCustAcctNm_RC, "Ship To Name");
            glblItemList.put(glblMsg.C.no(i).billToCustAcctCd_RC, "Bill To Account");
            glblItemList.put(glblMsg.C.no(i).billToCustCd_RC, "Bill To Location");
            glblItemList.put(glblMsg.C.no(i).billToCustAcctNm_RC, "Bill To Name");

            glblItemList.put(glblMsg.C.no(i).configTpCd_RC, "Configuratin Type");
            glblItemList.put(glblMsg.C.no(i).svcConfigMstrPk_RC, "Config ID");
            glblItemList.put(glblMsg.C.no(i).mdlNm_RC, "Config ID");

            glblItemList.put(glblMsg.C.no(i).csmpContrNum_RC, "CSMP Number");
            glblItemList.put(glblMsg.C.no(i).dlrRefNum_RC, "CSA Dealer Ref#");
        }

        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            glblItemList.put(glblMsg.D.no(i).mdseCd_RL, "Item#");
            glblItemList.put(glblMsg.D.no(i).ordCustUomQty_RL, "Order Qty");
            glblItemList.put(glblMsg.D.no(i).entCpoDtlDealSlsAmt_RL, "Sell Price");
            glblItemList.put(glblMsg.D.no(i).prcCatgNm_RL, "Price List");
            glblItemList.put(glblMsg.D.no(i).rtlWhNm_RL, "Warehouse");
            glblItemList.put(glblMsg.D.no(i).rtlSwhNm_RL, "Sub Warehouse");
            glblItemList.put(glblMsg.D.no(i).serNum_RL, "Serial#");
            glblItemList.put(glblMsg.D.no(i).flPrcListNm_RL, "Floor Price List");
            glblItemList.put(glblMsg.D.no(i).rtrnRsnCd_RL, "Return Reason");
            glblItemList.put(glblMsg.D.no(i).prcBaseDt_RL, "Pricing Date");
        }
    }
    /**
     * Getting Item Name
     * @param item EZDItem
     * @return Item Name
     */
    public String getItemName(EZDSItem item) {
        return this.glblItemList.get(item);
    }
    // 2018/01/31 S21_NA#19808 Add End
}
