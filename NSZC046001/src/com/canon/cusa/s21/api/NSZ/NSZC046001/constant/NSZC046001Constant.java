/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC046001.constant;

/**
 * <pre>
 * Sub Contract Update API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/29   Hitachi         K.Kasai        Create           N/A
 * 2016/04/08   Hitachi         A.Kohinata      Update          QC#6622
 * 2016/06/20   Hitachi         T.Iwamoto       Update          QC#9944
 * 2016/06/21   Hitachi         Y.Tsuchimoto    Update          QC#9944
 * 2016/08/25   Hitachi         T.Tomita        Update          QC#12136
 * 2017/10/05   Hitachi         M.Kidokoro      Update          QC#21544
 * 2018/01/30   CITS            M.Naito         Update          QC#21349
 * 2018/04/06   Hitachi         U.Kim           Update          QC#23378(Sol320)
 * 2018/05/10   CITS            T.Wada          Update          QC#17645
 * 2018/06/04   CITS            T.Wada          Update          QC#17645-2
 * 2019/04/18   Hitachi         K.Fujimoto      Update          QC#31137
 *</pre>
 */
public class NSZC046001Constant {

    /**
     * Process Mode : Create
     */
    public static final String MODE_CREATE = "01";

    /**
     * Process Mode : Update
     */
    public static final String MODE_UPDATE = "02";

    /**
     * Process Mode : Add To Contract
     */
    public static final String MODE_ADD_TO_CONTR = "03";

    /**
     * Process Mode : Delete
     */
    public static final String MODE_DELETE = "04";

    /**
     * Process Mode : Terminate
     */
    public static final String MODE_TERMINATE = "05";

    /**
     * Process Mode : Terminate
     */
    public static final String MODE_RENEWAL = "06";

    /**
     * Xx Process Mode : Create
     */
    public static final String XX_MODE_CREATE = "C";

    /**
     * Xx Process Mode : Update
     */
    public static final String XX_MODE_UPDATE = "U";

    /**
     * Xx Process Mode : Delete
     */
    public static final String XX_MODE_DELETE = "D";

    /**
     * Xx CRUD Mode : Create
     */
    public static final String CRUD_MODE_CREATE = "C";

    /**
     * Xx CRUD Mode : Update
     */
    public static final String CRUD_MODE_UPDATE = "U";

    /**
     * Xx CRUD Mode : Delete
     */
    public static final String CRUD_MODE_DELETE = "D";

    /**
     * Billing closing minimum day
     */
    public static final String CLO_BLLG_MIN_DAY = "0";

    /**
     * Billing closing max day
     */
    public static final String CLO_BLLG_MAX_DAY = "27";

    /**
     * Billing closing last day
     */
    public static final String CLO_BLLG_LAST_DAY = "99";

    /**
     * Process ended abnormally.
     */
    public static final String NSZM0391E = "NSZM0391E";

    /**
     * A value is not set in the required parameter field. Field Name:  [@]
     */
    public static final String NSZM0589E = "NSZM0589E";

    /**
     * The corresponding data [@] does not exist in [@].
     */
    public static final String NSZM0652E = "NSZM0652E";

    /**
     * Effective from date is later than Effective through date.
     */
    public static final String NSAM0279E = "NSAM0279E";

    /**
     * Please specify at least one of the Base or Overage the case of the Lease contract.
     */
    public static final String NSZM0665E = "NSZM0665E";

    /**
     * Allocate Across All Machines can be selected only if the contract type is Aggregate.
     */
    public static final String NSZM0666E = "NSZM0666E";

    // START 2018/01/30 M.Naito [QC#21349, DEL]
//    /**
//     * If the Contract Type is Aggregate, You must select Bill Base & Usage Together.
//     */
//    public static final String NSZM0667E = "NSZM0667E";
    // END 2018/01/30 M.Naito [QC#21349, DEL]

    /**
     * You must set either the Bill To Location or Lease Company.
     */
    public static final String NSZM0668E = "NSZM0668E";

    /**
     * In Warranty, Lease Company can not be set.
     */
    public static final String NSZM0669E = "NSZM0669E";

    /**
     * In aggregate contract, the number of Tier is all must be the same.
     */
    public static final String NSZM0670E = "NSZM0670E";

    /**
     * No Contract detail found.
     */
    public static final String NSZM0671E = "NSZM0671E";

    /**
     * Closing day must be between [@] and [@] or [@].
     */
    public static final String NSZM0654E = "NSZM0654E";

    /**
     * Billing day must be between [@] and [@] or [@].
     */
    public static final String NSZM0655E = "NSZM0655E";

    /**
     * Price up ratio must be between 0 and 100.
     */
    public static final String NSZM0656E = "NSZM0656E";

    /**
     * If you specify @ as base billing cycle, The closing day must be set the Last day.
     */
    public static final String NSZM0657E = "NSZM0657E";

    /**
     * If you select the Base & Usage, please set the same closing day and Base.
     */
    public static final String NSZM0658E = "NSZM0658E";

    /**
     * If you select the Base & Usage, please set the same billing day and Base.
     */
    public static final String NSZM0659E = "NSZM0659E";

    /**
     * The contract detail period can not exceed the period of contract header.
     */
    public static final String NSZM0660E = "NSZM0660E";

    /**
     * The contract detail period can not exceed the period of machine contract.
     */
    public static final String NSZM0661E = "NSZM0661E";

    /**
     * Since the period is overlapped other contract, it can not be registered.
     */
    public static final String NSZM0662E = "NSZM0662E";

    /**
     * If the contract is lease, base bill to customer must be the same account.
     */
    public static final String NSZM0663E = "NSZM0663E";

    /**
     * If the contract is lease, usage bill to customer must be the same account.
     */
    public static final String NSZM0664E = "NSZM0664E";

    /**
     * Terminate date can not be set later than End date.
     */
    public static final String NSZM0672E = "NSZM0672E";

    /**
     * Terminate date can not be set later than contract end date.
     */
    public static final String NSZM0673E = "NSZM0673E";

    /**
     * Terminate date can not be set later than contract end date.
     */
    public static final String NSZM0674E = "NSZM0674E";

    /**
     * If you specify @ as bill by type, you cannot specify [@].
     */
    public static final String NSZM0675E = "NSZM0675E";

    /**
     * Combination of Serial# and Charge Type is duplicated.
     */
    public static final String NSZM0676E = "NSZM0676E";

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
     * The credit card information is not registered.
     */
    public static final String NWZM0971E = "NWZM0971E";

    /**
     * If you selected @, it is required to enter @.
     */
    public static final String NSAM0081E = "NSAM0081E";

    /**
     * Either [@] or [@] needs to be set.
     */
    public static final String NSBM0050E = "NSBM0050E";

    /**
     * Billing over ratio must be set between 0 and 100.
     */
    public static final String NSZM0680E = "NSZM0680E";

    /**
     * You can set only one of the following items:Minimum Volume, Minimum Amount, Free Copies, Rollover%.
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
     * [@] cannot be set if you don't select [@].
     */
    public static final String NSZM0684E = "NSZM0684E";

    /**
     * If [@] is entered, please enter [@], too.
     */
    public static final String NSBM0049E = "NSBM0049E";

    /**
     * If "@" is selected, cannot input "@".
     */
    public static final String NSAM0149E = "NSAM0149E";

    /**
     * The combination of [@] and [@] is incorrect.
     */
    public static final String NSZM0698E = "NSZM0698E";

    // START 2016/04/08 [QC#6622, ADD]
    /**
     * The process cannot be executed because [@] is [@].
     */
    public static final String NSZM0961E = "NSZM0961E";
    // END 2016/04/08 [QC#6622, ADD]

    /**
     * Due to the serial# which is already used in the same contract, you can't register by "Add Contract".
     */
    public static final String NSZM1022E = "NSZM1022E";

    // START 2016/06/21 [QC#9944, ADD]
    /**
     * The main unit of this accessory does not exist in the contract.
     */
    public static final String NSZM1023E = "NSZM1023E";
    // END   2016/06/21 [QC#9944, ADD]

    // START 2018/04/06 U.Kim [QC#23378(Sol320), ADD]
    /**
     * PRC_ALLOC_RATE or PRC_ALLOC_AMT is should be set in all field.
     * */
    public static final String NSZM1328E = "NSZM1328E";
    // END 2018/04/06 U.Kim [QC#23378(Sol320), ADD]

    // START 2018/04/06 U.Kim [QC#23378(Sol320), ADD]
    /**
     * PRC_ALLOC_RATE and PRC_ALLOC_AMT are should be set in only one side.
     * */
    public static final String NSZM1329E = "NSZM1329E";
    // END 2018/04/06 U.Kim [QC#23378(Sol320), ADD]

    /**
     * MTR_MULT_RATE_MIN_NUM
     */
    public static final String MTR_MULT_RATE_MIN_NUM = "NSAL0320_MTR_MULT_RATE_MIN_NUM";

    /**
     * MTR_MULT_RATE_MAX_NUM
     */
    public static final String MTR_MULT_RATE_MAX_NUM = "NSAL0320_MTR_MULT_RATE_MAX_NUM";

    /**
     * MTR_MULT_RATE_FCT_NUM
     */
    public static final String MTR_MULT_RATE_FCT_NUM = "NSAL0320_MTR_MULT_RATE_FCT_NUM";

    /**
     * DATE_TIME_PATTERN
     */
    public static final String DATE_TIME_PATTERN = "yyyyMMddHHmmss";

    /**
     * updating the column is available indicator
     */
    public static final int UPD_AVAILABLE = 1;
    /**
     * updating the column is available indicator
     */
    public static final int UPD_AVAILABLE_QA_HOLD = 2;

    /**
     * updating the columns do not acceptable indicator
     */
    public static final int UPD_NOT_AVAILABLE = 3;

    /**
     * Billing Schedule API:Contract Mode
     */
    public static final String BLLG_SCHD_CONTR_MODE = "01";

    /**
     * Billing Schedule API:Delete Mode
     */
    public static final String BLLG_SCHD_DEL_MODE = "09";

    /**
     * Billing Schedule API:Termination Mode
     */
    public static final String BLLG_SCHD_TERM_MODE = "06";

    /**
     * Billing Schedule API:Renewal Mode
     */
    public static final String BLLG_SCHD_RNW_MODE = "04";
    
    // Add Start 2019/04/15 K.Fujimoto QC#31137
    /**
     * Billing Schedule API:Price Effectivity Mode
     */
    public static final String BLLG_SCHD_PE_MODE = "08";
    // Add End   2019/04/15 K.Fujimoto QC#31137

    // add start 2016/08/25 CSA Defect#12136
    /**
     * Max End Date
     */
    public static final String MAX_END_DT = "29991231";
    // add end 2016/08/25 CSA Defect#12136

    // START 2017/10/05 M.Kidokoro [QC#21544, ADD]
    /** DS_CONTR_MACH_LVL_NUM : MachLvlNum1 */
    public static final String LVL_NUM_1 = "1";

    /** DS_CONTR_MACH_LVL_NUM : MachLvlNum2 */
    public static final String LVL_NUM_2 = "2";

    /** DS_CONTR_MACH_LVL_NUM : MachLvlNum3 */
    public static final String LVL_NUM_3 = "3";
    // END 2017/10/05 M.Kidokoro [QC#21544, ADD]
    // START 2018/04/06 U.Kim [QC#23378(Sol320), ADD]
    public static final String BUSINESS_ID = "NSZC0460";
    // END 2018/04/06 U.Kim [QC#23378(Sol320), ADD]
    // QC#17645 Add Start
    public static final int LVL_CONTRCT = 1;
    public static final int LVL_CONTRCT_DTL = 2;
    /** DS_CONTR_BASE_USG_NM:BASE */
    public static final String DS_CONTR_BASE_USG_NM_B = "BASE";
    /** DS_CONTR_BASE_USG_NM:OVERAGE */
    public static final String DS_CONTR_BASE_USG_NM_U = "OVERAGE";
    // QC#17645 Add End

}
