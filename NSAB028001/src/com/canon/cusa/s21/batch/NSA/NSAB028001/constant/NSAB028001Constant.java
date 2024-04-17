/**
 * <Pre>Copyright(c)2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB028001.constant;

/**
 * <pre>
 * CFS Invoice Validation
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/27   Hitachi         T.Tsuchida      Created         N/A
 * 2018/02/13   Hitachi         Y.Takeno        Update          QC#21872
 * 2018/02/14   Hitachi         Y.Takeno        Update          QC#21872-1
 * </pre>
 */
public class NSAB028001Constant {

    /*****************************************************************
     * Other
     ****************************************************************/

    /**
     * MAX_COMMIT_NUMBER:1000
     */
    public static final int MAX_COMMIT_NUMBER = 1000;

    /*****************************************************************
     * Error Message
     ****************************************************************/

    /**
     * [@] is mandatory.
     */
    public static final String ZZZM9025E = "ZZZM9025E";

    /**
     * Failed to update @ table. [@]
     */
    public static final String NSAM0013E = "NSAM0013E";

    /**
     * CFS Transaction Number cannot be null.
     */
    public static final String NSAM0482E = "NSAM0482E";

    /**
     * Duplicate CFS Payable and Line Number.
     */
    public static final String NSAM0483E = "NSAM0483E";

    /**
     * CSA S21 Transaction Number mentioned is not valid.
     */
    public static final String NSAM0484E = "NSAM0484E";

    /**
     * Invalid Fleet Flag.
     */
    public static final String NSAM0485E = "NSAM0485E";

    /**
     * Invalid counter Transaction Number.
     */
    public static final String NSAM0486E = "NSAM0486E";

    /**
     * Invalid Contract Number.
     */
    public static final String NSAM0487E = "NSAM0487E";

    /**
     * Serial Number cannot be null.
     */
    public static final String NSAM0488E = "NSAM0488E";

    /**
     * Invalid Serial Number.
     */
    public static final String NSAM0489E = "NSAM0489E";

    /**
     * CSA Contract Number mentioned is not valid.
     */
    public static final String NSAM0490E = "NSAM0490E";

    /**
     * Invalid counter Serial Number.
     */
    //public static final String NSAM0491E = "NSAM0491E";

    /**
     * No contract identified for specified serial and billing period.
     */
    //public static final String NSAM0492E = "NSAM0492E";

    /**
     * CSA Contact Status is not valid.
     */
    public static final String NSAM0493E = "NSAM0493E";

    /**
     * Billing From/To Date cannot be null.
     */
    public static final String NSAM0494E = "NSAM0494E";

    /**
     * Billing from date Invalid.
     */
    public static final String NSAM0495E = "NSAM0495E";

    /**
     * Billing to date Invalid.
     */
    public static final String NSAM0496E = "NSAM0496E";

    /**
     * Billing Type cannot be null.
     */
    public static final String NSAM0497E = "NSAM0497E";

    /**
     * Invoice Billing Type is not valid.
     */
    public static final String NSAM0498E = "NSAM0498E";

    /**
     * CFS Dealer Code cannot be null.
     */
    //public static final String NSAM0499E = "NSAM0499E";

    /**
     * Invoice Date cannot be null.
     */
    public static final String NSAM0500E = "NSAM0500E";

    /**
     * Invalid Invoice Date.
     */
    public static final String NSAM0501E = "NSAM0501E";

    /**
     * Invalid Meter Type.
     */
    public static final String NSAM0502E = "NSAM0502E";

    /**
     * Invalid FTR Flag.
     */
    public static final String NSAM0503E = "NSAM0503E";

    /**
     * Invalid Credit Flag.
     */
    public static final String NSAM0504E = "NSAM0504E";

    /**
     * Usage Item Code Set Up No Defined.
     */
    public static final String NSAM0505E = "NSAM0505E";

    /**
     * FTR Item Code Set Up No Defined.
     */
    public static final String NSAM0506E = "NSAM0506E";

    /**
     * INV Invoice Type Not Defined for CFS CPC REBILL.
     */
    public static final String NSAM0507E = "NSAM0507E";

    /**
     * CM Invoice Type Not Defined for CFS CPC CREDIT.
     */
    public static final String NSAM0508E = "NSAM0508E";

    /**
     * Dealer Code Set Up No Defined.
     */
    public static final String NSAM0509E = "NSAM0509E";

    // START 2018/02/14 [QC#21872-1, ADD]
    /**
     * CSA Invoice Number cannot be null.
     */
    public static final String NSAM0712E = "NSAM0712E";
    // END 2018/02/14 [QC#21872-1, ADD]

    /**
     * [@] is not found.
     */
    public static final String ZZZM9006E = "ZZZM9006E";

    /*****************************************************************
     * Message Parameter
     ****************************************************************/

    /**
     * message Item: GlobalCompanyCode
     */
    public static final String MSG_ITEM_GLOBAL_COMPANY_CODE = "Global Company Code";

    /*****************************************************************
     * VarChar Constant
     ****************************************************************/

    /**
     * VarChar Constant Key: DS_INV_TP_CFS_CPC_REBILL
     */
    public static final String DS_INV_TP_CFS_CPC_REBILL = "DS_INV_TP_CFS_CPC_REBILL";

    /**
     * VarChar Constant Key: DS_INV_TP_CFS_CPC_CREDIT
     */
    public static final String DS_INV_TP_CFS_CPC_CREDIT = "DS_INV_TP_CFS_CPC_CREDIT";

    /**
     * VarChar Constant Key: SPLY_CD
     */
    public static final String SPLY_CD = "SPLY_CD";

    /**
     * VarChar Constant Key: SPCL_FLT_MDSE_CD
     */
    public static final String SPCL_FLT_MDSE_CD = "SPCL_FLT_MDSE_CD";

    /**
     * VarChar Constant Key: FTR_SVC_DUMMY_MDSE_CD
     */
    public static final String FTR_SVC_DUMMY_MDSE_CD = "FTR_SVC_DUMMY_MDSE_CD";

    /**
     * VarChar Constant Key: CFS_BLLG_TP_TXT
     */
    public static final String CFS_BLLG_TP_TXT = "CFS_BLLG_TP_TXT";

    /**
     * VarChar Constant Key: CFS_BLLG_MTR_LB_NM
     */
    public static final String CFS_BLLG_MTR_LB_NM = "CFS_BLLG_MTR_LB_NM";

    /*****************************************************************
     * Other Constant
     ****************************************************************/

    /**
     * DIV_COMMA : ,
     */
    public static final String DIV_COMMA = ",";

    /**
     * PARAM_00001 : 00001
     */
    public static final String PARAM_00001 = "00001";

    /**
     * PARAM_001 : 001
     */
    public static final String PARAM_001 = "001";

    /**
     * BASE
     */
    public static final String BASE = "BASE";

    /**
     * USAGE
     */
    public static final String USAGE = "USAGE";

    /**
     * BLLG_MTR_LB_NM : BW
     */
    public static final String BLLG_MTR_LB_NM_BW = "BW";
    
    /**
     * BLLG_MTR_LB_NM : CL
     */
    public static final String BLLG_MTR_LB_NM_CL = "CL";
    
}
