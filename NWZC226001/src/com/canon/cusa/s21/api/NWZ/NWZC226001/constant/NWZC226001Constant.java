/**
 * <Pre>Copyright (c) 2016-2017 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC226001.constant;

import static java.util.Arrays.asList;

import java.math.BigDecimal;
import java.util.List;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_ROLE_TP;

/**
 * <pre>
 * NWZC2260 : Order Import API Constant.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/11/21   Fujitsu         T.Ishii         Create          N/A
 * 2017/02/27   Fujitsu         S.Ohki          Create          QC#17842
 * 2017/06/30   Fujitsu         R.Nakamura      Update          QC#19068
 * 2017/07/20   Fujitsu         R.Nakamura      Update          QC#19802
 * 2017/08/02   Fujitsu         S.Iidaka        Update          S21_NA#19127
 * 2017/08/16   Fujitsu         S.Iidaka        Update          S21_NA#19088, 20627
 * 2017/08/21   Fujitsu         R.Nakamura      Update          S21_NA#19233
 * 2017/09/04   Fujitsu         M.Ohno          Update          S21_NA#19800(L3)
 * 2017/09/28   Fujitsu         R.Nakamura      Update          S21_NA#20689
 * 2018/01/23   Fujitsu         T.Aoi           Update          S21_NA#18798(Sol#173)
 * 2018/02/08   Fujitsu         A.Kosai         Update          S21_NA#20297(Sol#379)
 * 2018/03/13   Fujitsu         Hd.Sugawara     Update          QC#22967
 * 2018/03/20   Fujitsu         R.Nakamura      Update          S21_NA#23991
 * 2018/04/26   Fujitsu         S.Ohki          Update          S21_NA#25770
 * 2018/06/01   Fujitsu         M.Ohno          Update          S21_NA#26273
 * 2018/06/25   Fujitsu         Hd.Sugawara     Update          S21_NA#23726
 * 2018/08/21   Fujitsu         Hd.Sugawara     Update          S21_NA#27489
 * 2018/11/08   Fujitsu         K.Kato          Update          S21_NA#28951
 * 2018/11/29   Fujitsu         K.Ishizuka      Update          S21_NA#28899
 * 2019/01/05   Fujitsu         N.Sugiura       Update          S21_NA#29579
 * 2019/01/17   Fujitsu         K.Kato          Update          S21_NA#29942
 * 2019/02/14   Fujitsu         K.Ishizuka      Update          S21_NA#30340
 * 2019/12/05   Fujitsu         K.Kato          Update          QC#54219
 * 2020/01/06   Fujitsu         K.Kato          Update          QC#54219
 * 2023/04/04   Hitachi         T.Doi           Update          QC#60254
 *</pre>
 */
public class NWZC226001Constant {

    // ************************
    // mode
    // ************************

    /** mode : validate */
    public static final String MODE_VALIDATE = "1";

    /** mode : crate order */
    public static final String MODE_CREATE_ORDER = "2";

    // ************************
    // message id
    // ************************

    /** Program ID */
    public static final String PROGRAM_ID = "NWZC2260";

    /** PROCESS MODE 01 Default Transaction. */
    public static final String PROCESS_DEFAULT_TRANSACTION = "01";

    /** PROCESS MODE 03 Instruction. */
    public static final String PROCESS_INSTRUCTION = "03";

    /** PROCESS MODE 04 Default Bill To Ship To. */
    public static final String PROCESS_DEFAULT_BILL_SHIP = "04";

    // 2018/02/08 S21_NA#20297(Sol#379) Add Start
    /** PROCESS MODE 09 Shipping Default Information. */
    public static final String PROCESS_SHIPPING_DEFAULT_INFORMATION = "09";
    // 2018/02/08 S21_NA#20297(Sol#379) Add End

    /** MDSE_TP_CTX_TP MAIN_MACH */
    public static final String MDSE_TP_CTX_TP_MAIN_MACH = "MAIN_MACH";

    /** VARCHAR_CONST define: DROP_SHIP_RTL_WH_CD */
    public static final String DROP_SHIP_RTL_WH_CD = "DROP_SHIP_RTL_WH_CD";

    /** VARCHAR_CONST define: NOT_HARD_ALLOC_WH_CD */
    public static final String NOT_ALLOC_WH_CD = "NOT_HARD_ALLOC_WH_CD";

    /** Blank */
    public static final String BLANK = "";

    // START 2023/04/04 T.Doi [QC#60254, ADD]
    /** Comma */
    public static final String COMMA = ",";
    // END 2023/04/04 T.Doi [QC#60254, ADD]

    /** Contact Point Type Code (Phone) */
    public static final String CTAC_PT_TP_CD_PHONE = "02";

    /** Contact Point Type Code (EMail) */
    public static final String CTAC_PT_TP_CD_MAIL = "04";

    /** Contact Point Type Code (Fax) */
    public static final String CTAC_PT_TP_CD_FAX = "05";

    /** NEW */
    public static final String MODE_NEW = "01";

    /** MODIFY */
    public static final String MODE_MODIFY = "02";

    /** DELETE */
    public static final String MODE_DELETE = "03";

    /** Default Sub WH Code (NEW) */
    public static final String SUB_WH_CD_NEW = "NEW";

    /** Mail Group ID (From) */
    public static final String CST_MAIL_GRP_ID_FROM = "FROM0005";

    /** CONST_NM : THEREFORE_COND_FLD */
    // Mod Start 2018/03/20 QC#23991
//    public static final String CONST_NM_NWAB2210_THEREFORE_COND_FLD = "NWAB2210_THEREFORE_COND_FLD";
    public static final String CONST_NM_THEREFORE_COND_FLD = "THEREFORE_COND_FLD";
    // Mod End 2018/03/20 QC#23991

    /** CONST_NM : THEREFORE_CONN_AVAL_FLG */
    public static final String THEREFORE_CONN_AVAL_FLG = "THEREFORE_CONN_AVAL_FLG";

    /** Order Line Status Name (On Loan)  */
    public static final String ORD_LINE_STS_NM_ON_LOAN = "ON LOAN";

    /** Order Line Status Name (CLOSED)  */
    public static final String ORD_LINE_STS_NM_CLOSED = "CLOSED";

    // 2017/09/04 S21_NA#19800 add start
    /** Order Line Status Name (PENDING FULFILLMENT)  */
    public static final String ORD_LINE_STS_NM_PENDING_FULFILLMENT = "PENDING FULFILLMENT";
    // 2017/09/04 S21_NA#19800 add end

    /** Loan Order Action (Loan Sell) */
    public static final String LOAN_ORD_ACTION_LOAN_SELL = "LOAN SELL";

    /** Loan Order Action (Loan Return) */
    public static final String LOAN_ORD_ACTION_LOAN_RETURN = "LOAN RETURN";

    /** Return Reason Code */
    public static final String RTRN_RSN_REGR_RTRN = "01";

    /** Percent */
    public static final String PERCENT = "%";

    /** Line Status Billing Hold */
    public static final String LINE_STS_BILLING_HOLD = "BILLING HOLD";

    /** Merchandise Code Short Length (8) */
    public static final int MDSE_CD_SHORT_LENGTH = 8;

    /** VARCHAR_CONST define: LEASE_BYOT_MDSE_CD */
    public static final String LEASE_BYOT_MDSE_CD = "LEASE_BYOT_MDSE_CD";

    /** PROC_MODE_NEW */
    public static final String PROC_MODE_NEW = "1";

    /** PROC_MODE_MOD */
    public static final String PROC_MODE_MOD = "2";

    /** Service Install Rule Number(No Install:03) */
    public static final String SVC_ISTL_RULE_NUM_NO_INSTALL = "03";

    // 2017/09/04 S21_NA#19800 add start
    /** LOAN_CONV_LINE_CRAT(OrdLineValSetKey) */
    public static final String LOAN_CONV_LINE_CRAT = "LOAN_CONV_LINE_CRAT";
    // 2017/09/04 S21_NA#19800 add end

    // 2018/01/23 QC#18798 Add Start
    /** DS_ORD_POSN_NUM(for EOPS)*/
    public static final String DS_ORD_POSN_NUM_1 = "1";
    // 2018/01/23 QC#18798 Add End

    // 2018/04/26 QC#25770 Add Start
    /** Total weight threshold*/
    public static final BigDecimal TOTAL_WT_THRESHOLD = new BigDecimal(200);
    // 2018/01/23 QC#25770 Add End

    // 2018/11/08 S21_NA#28951 Add Start
    /** DS_ORD_TP INVENTORY TRANSFER */
    public static final String DS_ORD_TP_INVENTORY_TRANSFER ="0069";

    /** DS_ORD_TP INVENTORY TRANSFER(CONFIG) */
    public static final String DS_ORD_TP_INVENTORY_TRANSFER_CONFIG = "0140";
    // 2018/11/08 S21_NA#28951 Add End
    
    // 2018/11/29 S21_NA#28899 Add Start
    /** EDI City Mandatory Check Target */
    public static final String EDI_CTY_MND_CHK_TGT = "EDI_CTY_MND_CHK_TGT";
    
    /** EDI Post Mandatory/Validation Check Target */
    public static final String EDI_POST_MND_VALID_CHK_TGT = "EDI_POST_MND_VALID_CHK_TGT";
    
    /** EDI State Mandatory/Validation Check Target */
    public static final String EDI_ST_MND_VALID_CHK_RGT = "EDI_ST_MND_VALID_CHK_RGT";
    
    /** EDI City State Combination Check Target */
    public static final String EDI_CTY_ST_CMBN_CHK_TGT = "EDI_CTY_ST_CMBN_CHK_TGT";
    // 2018/11/29 S21_NA#28899 Add End

    // QC#50512 2019/05/30 Add Start
    public static final String LOAN_RTRN_RSN_CD = "LOAN_RTRN_RSN_CD";
    // QC#50512 2019/05/30 Add End

    /**
     * Message IDs
     */
    public static enum MSG_ID {

        /** "Global Company Code" is required. */
        NWZM0188E,

        /** "Sales Date" for the entered parameter is not set. */
        NWZM0978E,

        /** Mode is required. */
        NWZM0012E,

        /** Specified value for Mode is invalid. */
        NWZM0013E,

        /** "Order Source Reference Line Number" is required. */
        NWZM1905E,

        /** DB error occurred. */
        NMAM0283E,

        /** @ is mandatory value. */
        NWAM0298E,

        /** It doesn't exist [@] master. [@]. */
        NWAM0403E,

        /** It does not exist in @} table. */
        NWAM0809E,

        /** No data found. [Table Name : @, PKey : @] */
        NWAM0525E,

        /** Data insert failure. (table name is [@]) */
        NWAM0728E,

        /** Data update failure. (table name is [@]) */
        NWAM0729E,

        /**
         * This record cannot be imported because header Status is not
         * "Saved".
         */
        NWAM0798E,

        /** CPO Order Number does not exist. */
        NWAM0799E,

        /** The order was already canceled or closed. */
        NWAM0862E,

        /**
         * This order status could not be processed because of the
         * '@'.
         */
        NWAM0863E,

        /**
         * The corresponding data does not exist in
         * "XTRNL_INTFC_XREF".
         */
        NWZM1906E,

        /** [@] is mandatory. */
        ZZZM9025E,

        /** No search results found. */
        ZZOM0011E,

        /** Base Price could not be obtained. @}, @}. */
        NWZM1328E,

        /**
         * Scheduling Agreement Creation is skipped since Supply
         * Agreement Doc Type is not SA. (DS_IMPT_SCHD_TMPL_PK=@)
         */
        NWAM0902E,

        /**
         * The "Order Reason" is not for import.
         */
        NWZM2204E,

        // Del Start 2018/08/21 QC#27489
        ///** All 'ON LOAN' items of Original Order are not specified. */
        //NWZM2213E,
        // Del End 2018/08/21 QC#27489

        /** Items not covered are specified. */
        NWZM2214E,

        /** The target item does not exist in Original Order. */
        NWZM2215E,

        /** Conversion is not possible because there are items whose status is other than 'ON LOAN'. */
        NWZM2219E,

        /**
         * Base component is "Loan To Sales" or "Loan To Return",
         * specify same action for all items in the same config.
         */
        NWZM2209E,

        /** The original loan order is already closed or does not exist. */
        NWZM2221E,  // 2017/02/27 QC#17842 Add

        // Add Start 2017/06/29 QC#19068
        /** Parameter "Additional Charge Merchandise Code" does not exists in the Master. */
        NSZM1167E,

        /** Parameter "Additional Charge Merchandise Code" does not exists in the Master. (Shell# : [@], MDSE: [@]) */
        NWZM2233E,
        // Add End 2017/06/29 QC#19068

        // Add Start 2017/07/20 QC#19802
        /** There is no valid "Model ID" being set. */
        NWZM2234E,
        // Add End 2017/07/20 QC#19802

        /** No Price List Line associated with the Supply Plan @ found. Please check the Price List @. */
        NWZM2235E,

        // Del Start 2018/03/13 QC#22967
        ///** The relationship between 'Location' and 'Sold to' is incorrect. */
        //NWZM1455E,
        // Del End 2018/03/13 QC#22967

        /** The relationship between "Ship to Customer : [@] and Sold to Customer : [@]"  or "Ship to Customer : [@] and Bill to Customer : [@]" is incorrect. */
        NWZM2236E,

        // Add Start 2017/08/21 QC#19233
        NSZM1189E,

        /** This service meter package is not valid with the service price list/model (@ / @ / @). */
        NWZM2237E,
        // Add End 2017/08/21 QC#19233

        // Add Start 2017/09/28 QC#20689
        /** Cannot register Service Pricing information because declined it. */
        NWZM1998E,
        // Add End 2017/09/28 QC#20689

        // Add Start 2018/03/13 QC#22967
        /** The relationship between 'Ship To' and 'Sold To' is incorrect. */
        NWZM2254E,

        /** The relationship between 'Sold To' and 'Bill To' is incorrect. */
        NWZM2255E,

        /** The relationship between "Ship to Customer : [@] and Sold to Customer : [@]" is incorrect. */
        NWZM2257E,

        /** The relationship between "Sold to Customer : [@] and Bill to Customer : [@]" is incorrect. */
        NWZM2258E,
        // Add End 2018/03/13 QC#22967
        // 2018/06/01 S21_NA#26273 add start
        /** Order qty should be equal to minimum qty or more.(Mimimum Qty=[@]) */
        NWZM2023E,

        /** Order qty should be equal to maximum qty or less.(Maximum Qty=[@]) */
        NWZM2024E,

        /** Order qty should be multiple of increment qty.(Increment Qty=[@]) */
        NWZM2025E,
        // 2018/06/01 S21_NA#26273 add end

        // Add Start 2018/06/25 QC#23726
        /** The relationship between 'Ship To Account' and 'Carrier Service Level' is incorrect. */
        NWZM2267E,
        // Add End 2018/06/25 QC#23726
        
        // 2018/11/29 S21_NA#28899 Add Start
        /** Ship To Address City is mandatory. Please review and correct EDI transaction data. */
        NWZM2295E,
        
        /** Ship To Address Post is mandatory. Please review and correct EDI transaction data. */
        NWZM2296E,
        
        /** Ship To Address State is mandatory. Please review and correct EDI transaction data. */
        NWZM2297E,
        
        /** Ship To Address Post (@) is invalid. Please review and correct EDI transaction data. */
        NWZM2298E,
        
        /** Ship To Address State (@) is invalid. Please review and correct EDI transaction data. */
        NWZM2299E,
        
        /** City,County,Post and State combination is invalid. Please review and correct EDI transaction data. */
        NWZM2300E,
        
        /** More than 2 county names found. Please select county name and try again. */
        NMAM8501E,
        // 2018/11/29 S21_NA#28899 Add End
        // 2019/11/05 S21_NA#29579 Add Start
        /** The item code which entered to "Accessory Charge" is invalid. */
        NSZM1219E,
        /** The item code which entered to "Accessory Charge" is invalid. (Merchandise Code: [@]) */
        NWZM2304E,
        // 2019/11/05 S21_NA#29579 Add End

        // 2019/01/17 S21_NA#29942 Add Start
        /** UOM (@) is not a valid UOM for the item# @. */
        NWAM0968E,
        // 2019/01/17 S21_NA#29942 Add End

        // 2020/01/06 QC#54219 Del Start
        //// 2019/12/05 QC#54219 Add Start
        ///** Maintenance Only deals are not supported by order import process. Please directly maintain a service maintenance. */
        //NWZM2312E
        //// 2019/12/05 QC#54219 Add End
        // 2020/01/06 QC#54219 Del End
  }

    // ************************
    // other
    // ************************

    /** Line BIZ Role Writer Type List */
    // 2018/01/23 QC#18798 Mod Start
    //public static final List<String> WRITTER_LIST = asList(LINE_BIZ_ROLE_TP.ESS_WRITER, LINE_BIZ_ROLE_TP.LFS_WRITER, LINE_BIZ_ROLE_TP.PPS_WRITER);
    public static final List<String> WRITTER_LIST = asList(LINE_BIZ_ROLE_TP.ESS_WRITER, LINE_BIZ_ROLE_TP.LFS_WRITER, LINE_BIZ_ROLE_TP.PPS_WRITER, LINE_BIZ_ROLE_TP.EMSD_WRITER);
    // 2018/01/23 QC#18798 Mod End

    /** exclude error message code (not for screen) */
    public static final List<String> EXCLUDE_MESSAGE_CODE = asList("NWZM1621E", "NWZM1624E", "NWZM1626E");

    // Add Start 2018/05/21 QC#24244
    /** Time Stamp Format */
    public static final String TIME_STAMP_FORMAT = "yyyyMMddHHmmssSSS";
    // Add End 2018/05/21 QC#24244
    
    // 2019/02/14 S21_NA#30340 Add Start
    /** Time Sub String From */
    public static final int TIME_SUBSTRING_FROM = 8;

    /** Time Sub String To */
    public static final int TIME_SUBSTRING_TO = 12;
    // 2019/02/14 S21_NA#30340 Add End
}
