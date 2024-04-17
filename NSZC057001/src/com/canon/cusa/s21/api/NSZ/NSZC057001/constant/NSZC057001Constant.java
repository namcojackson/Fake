/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC057001.constant;

/**
 * <pre>
 * Contract Validation
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/09   Hitachi         A.Kohinata      Create          N/A
 * 2016/02/12   Hitachi         T.Aoyagi        Update          QC3274
 * 2016/02/16   Hitachi         A.Kohinata      Update          QC2263
 * 2016/04/19   Hitachi         K.Kishimoto     Update          QC9682
 * 2016/04/19   Hitachi         T.Kanasaka      Update          QC6525
 * 2016/04/28   Hitachi         M.Gotou         Update          QC#706
 * 2016/05/25   Hitachi         Y.Takeno        Update          QC#447
 * 2016/06/07   Hitachi         K.Yamada        Update          QC#3051
 * 2016/07/04   Hitachi         T.Tomita        Update          QC#9900
 * 2016/10/25   Hitachi         T.Tomita        Update          QC#3051
 * 2017/02/08   Hitachi         Y.Takeno        Update          QC#17275
 * 2017/02/20   Hitachi         T.Kanasaka      Update          QC#17614
 * 2017/07/26   Hitachi         K.Kasai         Update          QC#18882
 * 2017/08/09   Hitachi         K.Kitachi       Update          QC#19122
 * 2017/09/14   CITS            M.Naito         Update          QC#18534
 * 2017/09/22   Hitachi         K.Kitachi       Update          QC#21063
 * 2018/02/14   Hitachi         K.Kojima        Update          QC#24145
 * 2018/03/14   Hitachi         K.Kojima        Update          QC#24238
 * 2018/04/17   CITS            T.Wada          Update          QC#23378
 * 2018/05/11   CITS            T.Wada          Create          QC#24989
 * 2018/05/14   Hitachi         K.Kitachi       Update          QC#23541
 * 2018/06/13   Hitachi         A.Kohinata      Update          QC#26028
 * 2018/06/29   Hitachi         K.Kojima        Update          QC#17068
 * 2018/07/03   Hitachi         K.Kishimoto     Update          QC#15410
 * 2018/07/09   Hitachi         K.Kitachi       Update          QC#26834
 * 2018/08/03   Hitachi         K.Kim           Update          QC#14307(Sol#020)
 * 2018/10/29   Hitachi         K.Kitachi       Update          QC#28889
 * 2018/11/02   Hitachi         K.Kim           Update          QC#28627
 * 2019/03/25   Hitachi         A.Kohinata      Update          QC#30813
 * 2020/03/09   CITS            T.Wada          Update          QC#55830
 * 2020/03/24   Hitachi         A.Kohinata      Update          QC#54318
 * 2022/03/22   Hitachi         D.Yoshida       Update          QC#59683
 * 2022/06/22   CITS            E.Sanchez       Update          QC#59804
 * 2022/11/25   Hitachi         H.Watanabe      Update          QC#60398
 * 2023/06/23   Hitachi         T.Usui          Update          QC#61408
 * 2023/07/27   CITS            R.Kurahashi     Update          QC#61710
 * 2023/11/08   CITS            R.Kurahashi     Update          QC#61568
 * 2023/11/13   Hitachi         S.Moriai        Update          QC#61756
 *</pre>
 */
public class NSZC057001Constant {

    /**
     * Maximum Size
     */
    public static final int MAX_SIZE = 2000;

    /**
     * Key
     */
    public static final String KEY = "key";

    /**
     * Period
     */
    public static final String PERIOD = ".";

    /**
     * Comma
     */
    public static final String COMMA = ",";

    /**
     * Sequence Number
     */
    public static final String SQ_NUM = "00";

    /**
     * End Date
     */
    public static final String END_DT = "99991231";

    /**
     * NWZC203001 Process Mode : 03 (Get Authorization)
     */
    public static final String XX_PROC_MD_03 = "03";

    /**
     * Success
     */
    public static final String SUCCESS = "Success";

    /**
     * Error
     */
    public static final String ERROR = "Error";

    //START 2017/07/26 K.Kasai [QC#18882,ADD]
    /**
     * Warning
     */
    public static final String WARNING = "Warning";
    //END 2017/07/26 K.Kasai [QC#18882,ADD]

    /**
     * Not Validated
     */
    public static final String NOT_VALIDATED = "Not Validated";

    // START 2016/02/12 T.Aoyagi [QC3274, ADD]
    /**
     * Regex Message ID
     */
    public static final String REGEX_MSG_ID = "[A-Z]{3,4}[0-9]{4}[I|W|E]";
    // END 2016/02/12 T.Aoyagi [QC3274, ADD]

    /**
     * TERM_CONDITION_TONER_TYPE_NAME
     */
    public static final String TERM_COND_TONER_TYPE_NAME = "TERM_COND_TONER_TYPE_NAME";

    /**
     * TERM_CONDITION_TONER_TYPE_FOR_MPS
     */
    public static final String TERM_COND_TONER_TYPE_FOR_MPS = "TERM_COND_TONER_TYPE_FOR_MPS";

    /**
     * START_MTR_READ_WINDOW_BEF_DAYS
     */
    public static final String START_MTR_READ_WINDOW_BEF_DAYS = "START_MTR_READ_WINDOW_BEF_DAYS";

    // START 2017/09/22 K.Kitachi [QC#21063, MOD]
    /**
     * OUT_TRTY_SVC_BR_CD
     */
//    public static final String OUT_TRTY_BR_CD = "OUT_TRTY_BR_CD";
    public static final String OUT_TRTY_SVC_BR_CD = "OUT_TRTY_SVC_BR_CD";
    // END 2017/09/22 K.Kitachi [QC#21063, MOD]

    /**
     * FLEET_USAGE_READ_BEFORE_DAYS
     */
    public static final String FLEET_USAGE_READ_BEFORE_DAYS = "FLEET_USAGE_READ_BEFORE_DAYS";

    /**
     * FLEET_USAGE_READ_AFTER_DAYS
     */
    public static final String FLEET_USAGE_READ_AFTER_DAYS = "FLEET_USAGE_READ_AFTER_DAYS";

    /**
     * CFS_BILL_TO_CUST_CD
     */
    public static final String CFS_BILL_TO_CUST_CD = "CFS_BILL_TO_CUST_CD";

    /**
     * DS_CONTR_PK
     */
    public static final String DS_CONTR_PK = "DS_CONTR_PK";

    /**
     * DS_CONTR_DTL_PK
     */
    public static final String DS_CONTR_DTL_PK = "DS_CONTR_DTL_PK";

    /**
     * DS_CONTR_BLLG_MTR_PK
     */
    public static final String DS_CONTR_BLLG_MTR_PK = "DS_CONTR_BLLG_MTR_PK";

    /**
     * DS_CONTR_BLLG_SCHD_PK
     */
    public static final String DS_CONTR_BLLG_SCHD_PK = "DS_CONTR_BLLG_SCHD_PK";

    /**
     * DS_CONTR_PRC_EFF_PK
     */
    public static final String DS_CONTR_PRC_EFF_PK = "DS_CONTR_PRC_EFF_PK";

    /**
     * SVC_PHYS_MTR_READ_PK
     */
    public static final String SVC_PHYS_MTR_READ_PK = "SVC_PHYS_MTR_READ_PK";

    /**
     * Input parameter "Global Company Code" is a mandatory field.
     */
    public static final String NSZM0001E = "NSZM0001E";

    /**
     * Input parameter "Sales Date" is a mandatory field.
     */
    public static final String NSZM0002E = "NSZM0002E";

    /**
     * Input parameter "DS Contract Number" is a mandatory field.
     */
    public static final String NSZM0271E = "NSZM0271E";

    /**
     * Failed to insert the @.
     */
    public static final String NSZM0626E = "NSZM0626E";

    /**
     * If you select the Base & Usage, please set the same closing day
     * and Base.
     */
    public static final String NSZM0658E = "NSZM0658E";

    /**
     * If you select the Base & Usage, please set the same billing day
     * and Base.
     */
    public static final String NSZM0659E = "NSZM0659E";

    /**
     * Allocate Across All Machines can be selected only if the
     * contract type is Aggregate.
     */
    public static final String NSZM0666E = "NSZM0666E";

    /**
     * You can set only one of the following items:Minimum Volume,
     * Minimum Amount, Free Copies, Rollover%.
     */
    public static final String NSZM0681E = "NSZM0681E";

    /**
     * Allowance must be set 0 when Minimum volume is set.
     */
    public static final String NSZM0682E = "NSZM0682E";

    /**
     * Only tier1 rate can be set when Minimum amount is set.
     */
    public static final String NSZM0683E = "NSZM0683E";

    /**
     * The combination of [@] and [@] is incorrect.
     */
    public static final String NSZM0698E = "NSZM0698E";

    /**
     * the contract does not exist.[@]
     */
    public static final String NSZM0763E = "NSZM0763E";

    /**
     * Contract Validation information does not exist.
     */
    public static final String NSZM0764E = "NSZM0764E";

    /**
     * Lease Company should be CFS account.
     */
    public static final String NSZM0765E = "NSZM0765E";

    /**
     * End of the contract can not be blank
     */
    public static final String NSZM0766E = "NSZM0766E";

    /**
     * End of the serial number [@] can not be blank
     */
    public static final String NSZM0767E = "NSZM0767E";

    /**
     * Invalid end date for serial number [@]
     */
    public static final String NSZM0768E = "NSZM0768E";

    /**
     * Usage has already been billed up to [@], Service for machine &
     * SerialNumber cannot be added with a start date earlier than
     * this date.
     */
    public static final String NSZM0769E = "NSZM0769E";

    /**
     * Service Line [@] has a negative Amount on its Billing Schedule.
     * Please fix the Dollar amount
     */
    public static final String NSZM0770E = "NSZM0770E";

    // START 2016/02/16 A.Kohinata [QC2263, DEL]
//    /**
//     * Vendor Contact not Defined
//     */
//    public static final String NSZM0771E = "NSZM0771E";
    // END 2016/02/16 A.Kohinata [QC2263, DEL]

    /**
     * No billing meter read within @ to @ day window of the service
     * sub line start date for serial number [@]
     */
    public static final String NSZM0773E = "NSZM0773E";

    /**
     * Must use Arrears Billing for Base and Usage on FM Contract
     */
    public static final String NSZM0774E = "NSZM0774E";

    /**
     * FM rep used in not accurate for Customer Account Number
     */
    public static final String NSZM0775E = "NSZM0775E";

    /**
     * Can not find Out of Territory base price for model in the FM
     * price reference value set
     */
    public static final String NSZM0776E = "NSZM0776E";

    /**
     * Can not find In Territory base price for model in the FM price
     * reference value set
     */
    public static final String NSZM0777E = "NSZM0777E";

    /**
     * Can not find Out of Territory tier price for model in the FM
     * price reference value set
     */
    public static final String NSZM0778E = "NSZM0778E";

    /**
     * Can not find In Territory tier price for model in the FM price
     * reference value set
     */
    public static final String NSZM0779E = "NSZM0779E";

    /**
     * Customer [@] in on hold
     */
    public static final String NSZM0780E = "NSZM0780E";

    /**
     * Initial Meter Reading Not available for Contract Detail
     */
    public static final String NSZM0781E = "NSZM0781E";

    /**
     * Initial Meter Reading not within time frame for Contract Detail
     */
    public static final String NSZM0782E = "NSZM0782E";

    /**
     * MDS contracts require Toner type selection in T and C - OEM or
     * REMAN
     */
    public static final String NSZM0783E = "NSZM0783E";

    /**
     * If @ is the input, must input @.
     */
    public static final String NSZM0784E = "NSZM0784E";

    /**
     * Revenue Distributions are not allowed on FM CONTRACTS
     */
    public static final String NSZM0791E = "NSZM0791E";

    // START 2016/02/16 A.Kohinata [QC2263, ADD]
    /**
     * "Sales Rep Code" has not been entered.
     */
    public static final String NSZM0867E = "NSZM0867E";
    // END 2016/02/16 A.Kohinata [QC#2263, ADD]
    // Add Start 04/19/2016 <QC#9682>
    /**
     * "Rep" has not been entered.
     */
    public static final String NSZM0964E = "NSZM0964E";
    // Add End  04/19/2016 <QC#9682>

    // Add Start 04/19/2016 <QC#6525>
    /**
     * Billing Counter does not exist.
     */
    public static final String NSZM0965E = "NSZM0965E";
    // Add End  04/19/2016 <QC#6525>

    // Add Start 04/28/2016 <QC#706>
    /**
     * Invalid Transaction Type is set for FM Contract.
     */
    public static final String NSZM0969E = "NSZM0969E";
    /**
     * 320 EMSD Charge Back
     */
    public static final String EMSD_CHARGEBACK = "320";

    /**
     * FM_TRX_RSN_CD_01
     */
    public static final String FM_TRX_RSN_CD_01 = "01";

    /**
     * FM_TRX_RSN_CD_02
     */
    public static final String FM_TRX_RSN_CD_02 = "02";
    // Add End  04/28/2016 <QC#706>

    // START 05/25/2016 [QC#447, ADD]
    /**
     * Effective Term of Additional Charges for SLA is duplicated on Serial# {@}.
     * Please fix the Effective Term of the Serial#.
     */
    public static final String NSZM0980E = "NSZM0980E";

    /**
     * Current SLA Time on Term & Condition is different with Additional Charge on Serial# {@}.
     * Please fix the SLA Time on T & C based on the Additional Charge.
     */
    public static final String NSZM0981E = "NSZM0981E";

    /**
     * TERM_COND_RSP_TM_COMIT
     */
    public static final String TERM_COND_RSP_TM_COMIT = "TERM_COND_RSP_TM_COMIT";
    // END   05/25/2016 [QC#447, ADD]

    // START 2016/06/07 K.Yamada [QC#3051, ADD]
    /**
     * Serial Number [@] have machine start date mismatch with asset start date of Lease Company
     */
    public static final String NSZM0983E = "NSZM0983E";

    /**
     * These serial numbers have zero base price differences between CSA and Lease Company [@]
     */
    public static final String NSZM0984E = "NSZM0984E";

    /**
     * These serial numbers have non zero base price differences between CSA and Lease Company [@]
     */
    public static final String NSZM0985E = "NSZM0985E";

    /**
     * These serial numbers have different dealer codes in CSA from Lease Company [@]
     */
    public static final String NSZM0986E = "NSZM0986E";

    /**
     * These serial numbers that are in CSA contract are not found in Lease Company [@]
     */
    public static final String NSZM0987E = "NSZM0987E";

    /**
     * These serial numbers have base cycle differences between CSA and Lease Company [@]
     */
    public static final String NSZM0988E = "NSZM0988E";

    /**
     * These serial numbers have usage cycle differences between CSA and Lease Company [@]
     */
    public static final String NSZM0989E = "NSZM0989E";

    /**
     * These serial numbers have tier1 rate differences between CSA and CFS [@]
     */
    public static final String NSZM0990E = "NSZM0990E";

    /**
     * These serial numbers have tier2 rate differences between CSA and CFS [@]
     */
    public static final String NSZM0991E = "NSZM0991E";

    /**
     * These serial numbers have tier3 rate differences between CSA and CFS [@]
     */
    public static final String NSZM0992E = "NSZM0992E";

    /**
     * These serial numbers have tier4 rate differences between CSA and CFS [@]
     */
    public static final String NSZM0993E = "NSZM0993E";

    /**
     * These serial numbers have tier1 allowance differences between CSA and CFS [@]
     */
    public static final String NSZM0994E = "NSZM0994E";

    /**
     * These serial numbers have tier2 allowance differences between CSA and CFS [@]
     */
    public static final String NSZM0995E = "NSZM0995E";

    /**
     * These serial numbers have tier3 allowance differences between CSA and CFS [@]
     */
    public static final String NSZM0996E = "NSZM0996E";

    /**
     * These serial numbers have tier4 allowance differences between CSA and CFS [@]
     */
    public static final String NSZM0997E = "NSZM0997E";

    /**
     * Change the Supplies Inclusive for serial [@] to Yes
     */
    public static final String NSZM0998E = "NSZM0998E";

    /**
     * Change the Supplies Inclusive for serial [@] to No
     */
    public static final String NSZM0999E = "NSZM0999E";

    /**
     * These serial numbers in CSA fleet contract have different base charge compare to CFS contract [@]
     */
    public static final String NSZM1000E = "NSZM1000E";

    // START 2018/02/14 K.Kojima [QC#24145,ADD]
    /**
     * Contract data of CFS is not found.
     */
    public static final String NSZM1321E = "NSZM1321E";
    // END 2018/02/14 K.Kojima [QC#24145,ADD]

    // START 2018/04/18 T.Wada [QC#23378,ADD]
    /**
     * Total amount of the Revenue Distributions does not match the Base Charge amount.
     */
    public static final String NSZM1327E = "NSZM1327E";
    // END 2018/04/18 T.Wada [QC#23378,ADD]

    /**
     * CFS_BASE_PRICE_ERROR_ALLOWANCE
     */
    public static final String CFS_BASE_PRICE_ERROR_ALLOWANCE = "CFS_BASE_PRICE_ERROR_ALLOWANCE";

    /**
     * CSA_DEALER_CODE
     */
    public static final String CSA_DEALER_CODE = "CSA_DEALER_CODE";

    /**
     * USG_TP_BILL_CD_BW
     */
    public static final String USG_TP_BILL_CD_BW = "BW";

    /**
     * USG_TP_BILL_CD_CL
     */
    public static final String USG_TP_BILL_CD_CL = "CL";

    /**
     * USG_TP_BILL_CD_SM
     */
    public static final String USG_TP_BILL_CD_SM = "SM";

    /**
     * TIER_2 : 2
     */
    public static final int TIER_2 = 2;

    /**
     * TIER_3 : 3
     */
    public static final int TIER_3 = 3;

    /**
     * TIER_4 : 4
     */
    public static final int TIER_4 = 4;
    // END 2016/06/07 K.Yamada [QC#3051, ADD]
    // START 2017/11/16 [QC#21179, ADD]
    /** VAR_CHAR_CONST name */
    public static final String NSZC0570_CFS_FTR_CHC_CONTR_CLS = "NSZC0570_CFS_FTR_CHC_CONTR_CLS";
    // END 2017/11/16 [QC#21179, ADD]

    // START 2016/07/04 T.Tomita [QC#9900, ADD]
    /**
     * Relation between "@" and "@" are not correct.
     */
    public static final String NSZM1024E = "NSZM1024E";
    // END 2016/07/04 T.Tomita [QC#9900, ADD]

    // START 2016/09/28 T.Kanasaka [QC#9905, ADD]
    /**
     * "Ship To Customer Code" is not set.
     */
    public static final String NSZM1060E = "NSZM1060E";
    // END 2016/09/28 T.Kanasaka [QC#9905, ADD]

    // START 2016/10/25 T.Tomita [QC#3051, ADD]
    /**
     * End of Life control dates have been set prior to the contract end date.
     */
    public static final String NSZM1069W = "NSZM1069W";

    /**
     * End of Life control dates have been set prior to the contract end date.
     */
    public static final String NSZM1070E = "NSZM1070E";
    // END 2016/10/25 T.Tomita [QC#3051, ADD]

    //START 2017/07/26 K.Kasai [QC#18882,ADD]
    /**
     * A part of machine in the fleet contract does not arrive at the customer site yet.
     */
    public static final String NSAM0687W = "NSAM0687W";
    //END 2017/07/26 K.Kasai [QC#18882,ADD]

    // START 2017/02/08 [QC#17275, ADD]
    /**
     * NSAL0320_MTR_MULT_RATE_MIN_NUM
     */
    public static final String NSAL0320_MTR_MULT_RATE_MIN_NUM = "NSAL0320_MTR_MULT_RATE_MIN_NUM";

    /**
     * NSAL0320_MTR_MULT_RATE_MAX_NUM
     */
    public static final String NSAL0320_MTR_MULT_RATE_MAX_NUM = "NSAL0320_MTR_MULT_RATE_MAX_NUM";

    /**
     * NSAL0320_MTR_MULT_RATE_FCT_NUM
     */
    public static final String NSAL0320_MTR_MULT_RATE_FCT_NUM = "NSAL0320_MTR_MULT_RATE_FCT_NUM";

    /**
     * Multiple billing counter levels are not allowed. Please change billing counters.
     */
    public static final String NSZM0677E = "NSZM0677E";

    /**
     * The multiplier must be between [@] and [@].
     */
    public static final String NSZM0678E = "NSZM0678E";

    /**
     * The multiplier must be incremented by [@].
     */
    public static final String NSZM0679E = "NSZM0679E";

    /**
     * "Billing Counter" is not set.
     */
    public static final String NSZM1113E = "NSZM1113E";

    /**
     * Multiplier must be unique within the same billing counters.
     */
    public static final String NSZM1114E = "NSZM1114E";
    // END   2017/02/08 [QC#17275, ADD]

    // START 2017/02/20 T.Kanasaka [QC#17614, ADD]
    /**
     * "Sales Rep Code" is invalid.
     */
    public static final String NSZM1116E = "NSZM1116E";
    // END 2017/02/20 T.Kanasaka [QC#17614, ADD]

    // START 2017/08/09 K.Kitachi [QC#19122, ADD]
    /**
     * Billing Meter or Physical/Billing Meter Relation is not set for serial number [@].
     * Please submit on Change Billing Counters screen.
     */
    public static final String NSZM1292E = "NSZM1292E";
    // END 2017/08/09 K.Kitachi [QC#19122, ADD]

    // START 2017/09/14 M.Naito [QC#18534, ADD]
    /**
     * The Installation of Machine is not completed.
     */
    public static final String NSZM1298E = "NSZM1298E";

    /**
     * "CSA Warranty" cannot be specified as Service Program for Fleet and Aggregate Contract.
     */
    public static final String NSAM0698E = "NSAM0698E";
    // END 2017/09/14 M.Naito [QC#18534, ADD]
    
    // START 2018/03/14 K.Kojima [QC#24238,ADD]
    /**
     * Notes is not registered.
     */
    public static final String NSZM1322E = "NSZM1322E";
    // END 2018/03/14 K.Kojima [QC#24238,ADD]
    /**
     * Installed machine does not exist.
     */
    public static final String NSZM1331E = "NSZM1331E";

    // START 2018/05/14 K.Kitachi [QC#23541, ADD]
    /**
     * You can set only one of the following items for [@]:Minimum Volume, Minimum Amount, Free Copies, Rollover%.
     */
    public static final String NSZM1332E = "NSZM1332E";
    // END 2018/05/14 K.Kitachi [QC#23541, ADD]

    // add start 2018/06/13 QC#26028
    /**
     * EOL date for [@] have been set prior to [@].
     */
    public static final String NSZM1338E = "NSZM1338E";
    // add end 2018/06/13 QC#26028
    // START 2018/07/09 K.Kitachi [QC#26834, ADD]
    /**
     * Please submit on Change Billing Counters screen.
     */
    public static final String NSZM1339E = "NSZM1339E";
    // END 2018/07/09 K.Kitachi [QC#26834, ADD]
    // START 2018/07/03 [QC#15410, ADD]
    /**
     * The period of Machine is overlapped other contract.
     */
    public static final String NSZM1340W = "NSZM1340W";
    /**
     * The period of Billing counter is overlapped other contract.
     */
    public static final String NSZM1341E = "NSZM1341E";
    // START 2018/07/03 [QC#15410, ADD]
    // START 2018/06/29 K.Kojima [QC#17068,ADD]
    /**
     * Base Contact Person and Overage Contact Person are different.
     */
    public static final String NSZM1342E = "NSZM1342E";
    // END 2018/06/29 K.Kojima [QC#17068,ADD]
    // START 2018/08/03 K.Kim [QC#14307(Sol#020), ADD]
    /**
     * This customer has Special Instructions.  Please check.
     */
    public static final String NSZM1343W = "NSZM1343W";

    /**
     * Function Category ID for Check Special Instruction
     */
    public static final String FUNC_CATG_ID = "CONTRACT";

    /**
     * Business Id
     */
    // mod start 2019/03/25 QC#30813
    public static final String BIZ_ID_NSAL0300 = "NSAL0300";
    // mod end 2019/03/25 QC#30813
    // END 2018/08/03 K.Kim [QC#14307(Sol#020), ADD]

    // START 2018/10/29 K.Kitachi [QC#28889, ADD]
    /**
     * If the Contract Type is Aggregate and Base & Usage Together, All Bill To Code must be same.
     */
    public static final String NSZM1354E = "NSZM1354E";
    // END 2018/10/29 K.Kitachi [QC#28889, ADD]

    // START 2018/11/02 [QC#28627, ADD]
    /**
     * The parent contract # [@] does not exist.
     */
    public static final String NSZM1355E = "NSZM1355E";

    /**
     * The parent contract # [@] is not active.
     */
    public static final String NSZM1356E = "NSZM1356E";

    /**
     * Parent Contract's Link# must set its own Contract#.
     */
    public static final String NSZM1357E = "NSZM1357E";

    /**
     * Link contract # [@] Bill To Mis-Match with parent contract # [@] Bill To.
     */
    public static final String NSZM1358E = "NSZM1358E";
    // END 2018/11/02 [QC#28627, ADD]
    
    // Add Start 2018/12/10 K.Fujimoto QC#29079
    /**
     * Billing counters does not match with the one registered in sub contract. Please re-enter in Sub Contract Maintenance.
     */
    public static final String NSZM1360W = "NSZM1360W";
    // Add End   2018/12/10 K.Fujimoto QC#29079
    
    // Add QC#55830 Start
    public static final String NSZM1374E = "NSZM1374E";
    // Add QC#55830 End
    
    // add start 2020/03/24 QC#54318
    /**
     * Uplift Policy Date is not set.
     */
    public static final String NSZM1376W = "NSZM1376W";
    // add end 2020/03/24 QC#54318

    // START 2022/03/22 [QC#59683, ADD]
    /**
     * [@] cannot be entered when [@] is "@".
     */
    public static final String NSAM0448E = "NSAM0448E";
    // END   2022/03/22 [QC#59683, ADD]

    // START 2022/06/22 E.Sanchez [QC#59804,ADD]
    /**
     * CARRY_OVER_TP_CD_ON : 1
     */
    public static final String CARRY_OVER_TP_CD_ON = "1";
    // END 2022/06/22 E.Sanchez [QC#59804,ADD]
    // 2022/11/25 QC#60398 Add Start
    /**
     * This customer required Hard Copy of PO. Please confirm if you have done it.
     */
    public static final String NSZM1382W = "NSZM1382W";
    // 2022/11/25 QC#60398 Add End

    // QC#61408 2023/06/23 Add Start
    /**
     * DS_CONTR_MACH_LVL_NUM_1
     */
    public static final String DS_CONTR_MACH_LVL_NUM_1 = "1";

    /**
     * DS_CONTR_MACH_LVL_NUM_2
     */
    public static final String DS_CONTR_MACH_LVL_NUM_2 = "2";

    /**
     * DS_CONTR_MACH_LVL_NUM_3
     */
    public static final String DS_CONTR_MACH_LVL_NUM_3 = "3";

    /**
     * BASE
     */
    public static final String DS_CONTR_BASE_USG_NM_B = "BASE";

    /**
     * OVERAGE
     */
    public static final String DS_CONTR_BASE_USG_NM_U = "OVERAGE";

    /**
     * Uplift Policy Date does not match Base Schedule Start Date. Please modify Uplift settings.
     */
    public static final String NSZM1395E = "NSZM1395E";

    /**
     * Uplift Policy Date does not match Usage Schedule Start Date. Please modify Uplift settings.
     */
    public static final String NSZM1397E = "NSZM1397E";
    // QC#61408 2023/06/23 Add End
    
    // START 2023/07/27 R.Kurahashi [QC#61710, ADD]
    /**
     * End Date does not match Aggregate Line.
     */
    public static final String NSZM1396E = "NSZM1396E";
    // END 2023/07/27 R.Kurahashi [QC#61710, ADD]
    
    // START 2023/11/08 R.Kurahashi [QC#61568, ADD]
    /**
     * NO_CHECKED_BLLG_CYCLE_CD
     */
    public static final String VAR_CONST_BLLG_CYCLE_CD = "NO_CHECKED_BLLG_CYCLE_CD";

    /**
     * Base Billing Cycle does not match Aggregate Line.
     */
    public static final String NSZM1408E = "NSZM1408E";

    /**
     * Usage Billing Cycle does not match Aggregate Line. [@]
     */
    public static final String NSZM1409E = "NSZM1409E";
    // END 2023/11/08 R.Kurahashi [QC#61568, ADD]

    //START 2023/11/13 S.Moriai [QC#61756, ADD]
    /**
     * There is no Email Address in Machine Meter Read Contact
     */
    public static final String NSZM1410E = "NSZM1410E";
    //END 2023/11/13 S.Moriai [QC#61756, ADD]

}
