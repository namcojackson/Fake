package business.parts;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_AFFL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_PROD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_PROJ;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;

/**
 * Class name: AFACCommonJrnlEntry
 * <dd>The class explanation: Constant variable for AFACCommonJrnlEntry.
 * <dd>Remarks:
 * @version 1.0
 * @author H. Naoi
 */
public interface NFACommonJrnlEntryConstant {

     public enum CoaSegment {
                CMPY, BR, CC, ACCT, PROD, CH, AFFL, PROJ, EXTN, DEF
        }
     
    /** Fixed Value : number of AJE Pattern indicator type */
    static final int AJE_PTRN_IND_TP_CNT = 3;

    /** Fixed Value : loop count 1 */
    static final int AJE_PTRN_IND_TP_CNT_1 = 1;

    /** Fixed Value : loop count 2 */
    static final int AJE_PTRN_IND_TP_CNT_2 = 2;

    /** Fixed Value : loop count 3 */
    static final int AJE_PTRN_IND_TP_CNT_3 = 3;
    
    /** Fixed Value : COA AFFL CD 000 */
    static final String COA_AFFL_CD_VAL_000 = COA_AFFL.NON_AFFILIATED_COMPANY;
    
    /** Fixed Value : COA PROD CD ZZ */
    static final String COA_PROD_CD_VAL_ZZ = COA_PROD.ADMINISTRATION;
    
    /** Fixed Value : ZZZ */
    static final String COA_PROJ_CD_DEFAULT = COA_PROJ.DEFAULT;
    
    /** Fixed Value : RGTN_STS_CD - Ready For Order Taking */
    static final String RGTN_STS_CD_READY_FOR_ORDER_TAKING = RGTN_STS.READY_FOR_ORDER_TAKING;

    /** Fixed Value : RGTN_STS_CD - Terminated */
    static final String RGTN_STS_CD_TERMINATED = RGTN_STS.TERMINATED;
    
    /** Fixed Value : RGTN_STS_CD - Ready for customs clearance and receiving */
    static final String RGTN_STS_CD_READY_FOR_RECEIVING = RGTN_STS.READY_FOR_CUSTOMS_CLEARANCE_AND_RECEIVING;
    
    /** Fixed Value : RGTN_STS_CD - Pending Completion */
    static final String RGTN_STS_CD_PENDING_COMPLETION = RGTN_STS.PENDING_COMPLETION;
    
    /** Fixed Value : ZZZ */
    static final String SYS_SRC_CD_DEFAULT = "ZZZ";
    
    /** Fixed Value : Length of JRNL_SRC_NM */
    static final int JRNL_SRC_NM_LEN = 25;
    
    // ** DB Item Column Name's Fixed Value ** //    
    /** DB Item Column Name */
    static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** DB Item Column Name */
    static final String AJE_INTFC_TP_CD = "AJE_INTFC_TP_CD";

    /** DB Item Column Name */
    static final String AJE_INTFC_PK = "AJE_INTFC_PK";

    /** DB Item Column Name */
    static final String SYS_SRC_CD = "SYS_SRC_CD";

    /** DB Item Column Name */
    static final String TRX_CD = "TRX_CD";

    /** DB Item Column Name */
    static final String TRX_RSN_CD = "TRX_RSN_CD";

    /** Fixed Value : CoA Segment Lookup Type Value for ITEM */
    static final String COA_SEG_LKUP_TP_ITEM_VAL = "@ITEM";

    /** Fixed Value : CoA Segment Lookup Type Value for TOC */
    static final String COA_SEG_LKUP_TP_TOC_VAL = "@TOC";

    /** Fixed Value : CoA Segment Lookup Type Value for CUST */
    static final String COA_SEG_LKUP_TP_CUST_VAL = "@CUST";

    /** Fixed Value : CoA Segment Lookup Type Value for ITEM */
    static final String COA_SEG_LKUP_TP_LINK_VAL = "@LINK";

    /** Fixed Value : CoA Segment Lookup Type Value for VND */
    static final String COA_SEG_LKUP_TP_VND_VAL = "@VND";
    
    /** Fixed Value : CoA Segment Lookup Type Value for AFFL */
    static final String COA_SEG_LKUP_TP_AFFL_VAL = "@AFFL";

    // START 2014/04/16 T.Tozuka [CSA AJE#84 Add]
    /** Fixed Value : CoA Segment Lookup Type Value for ST */
    static final String COA_SEG_LKUP_TP_ST_VAL = "@ST";
    // END   2014/04/16 T.Tozuka [CSA AJE#84 Add]
    
    /** Fixed Value : CoA Segment Lookup Type Value for ITEM-INV */
    static final String COA_SEG_LKUP_TP_ITEM_INV = "@ITEM-INV";
    
    /** Fixed Value : CoA Segment Lookup Type Value for WH-INV */
    static final String COA_SEG_LKUP_TP_WH_INV = "@WH-INV";
    

    /** Fixed Value : CoA Segment Lookup Type Value for BANK-LC */
    static final String COA_SEG_LKUP_TP_BANK_LC_VAL = "@BANK-LC";

    /** Fixed Value : CoA Segment Lookup Type Value for BANK-RM */
    static final String COA_SEG_LKUP_TP_BANK_RM_VAL = "@BANK-RM";

    /** Fixed Value : CoA Segment Lookup Type Value for BANK-DA */
    static final String COA_SEG_LKUP_TP_BANK_DA_VAL = "@BANK-DA";

    /** Fixed Value : CoA Segment Lookup Type Value for MC */
    static final String COA_SEG_LKUP_TP_MC_VAL = "@MC";
    
    /** Fixed Value : CoA Segment Lookup Type Value for #PR in COA_SEG_PROD_PTRN */
    static final String COA_SEG_LKUP_TP_PR_VAL = "#PR";

    /** Fixed Value : CoA Segment Lookup Type Value for #PRBR in COA_SEG_PROD_PR_PTRN */
    static final String COA_SEG_LKUP_TP_PRBR_VAL = "#PRBR";

    /** Fixed Value : CoA Segment Lookup Type Value for #WH in  COA_WH_PTRN */
    static final String COA_SEG_LKUP_TP_WH_VAL = "#WH";

    /** Fixed Value : Valid COA Segment Lookup Type Cd */
    static final String INVALID_MSG_FOR_COA_SEG = "Invalid COA Segment Lookup Type Code";

    /** Fixed Value : Valid COA Segment Lookup Type Cd */
    static final String INVALID_MSG_FOR_COA_CC = "Cost Centor by TOC and the one by PRBR don't match";

    /** Fixed Value : COA Account Code of sales */
    static final String AJE_LINE_IND_TP_CD_SALES = "01";
    
    /** Fixed Value : COA Account Code of sales */
    static final String AJE_LINE_IND_TP_CD_EXPENSE = "04";
    
    /** Fixed Value : Dr Cr Type Code 'D' */
    static final String DR_CR_TP_CD_VALUE_D = "D";

    /** Fixed Value : Dr Cr Type Code 'C' */
    static final String DR_CR_TP_CD_VALUE_C = "C";
    
    /** Fixed Value : Blank */
    static final String BLANK = "";
    
    
    /** -------- for AJE Pattern ------- **/
    /** DB Item Column Name */
    static final String AJE_ID = "AJE_ID";

    /** DB Item Column Name */
    static final String AJE_PTRN_IND_TP_CD_01 = "AJE_PTRN_IND_TP_CD_01";

    /** DB Item Column Name */
    static final String AJE_PTRN_IND_TP_NM_01 = "AJE_PTRN_IND_TP_NM_01";

    /** DB Item Column Name */
    static final String AJE_PTRN_ACTL_CD_01 = "AJE_PTRN_ACTL_CD_01";

    /** DB Item Column Name */
    static final String AJE_PTRN_ACTL_NM_01 = "AJE_PTRN_ACTL_NM_01";

    /** DB Item Column Name */
    static final String AJE_PTRN_IND_TP_CD_02 = "AJE_PTRN_IND_TP_CD_02";

    /** DB Item Column Name */
    static final String AJE_PTRN_IND_TP_NM_02 = "AJE_PTRN_IND_TP_NM_02";

    /** DB Item Column Name */
    static final String AJE_PTRN_ACTL_CD_02 = "AJE_PTRN_ACTL_CD_02";

    /** DB Item Column Name */
    static final String AJE_PTRN_ACTL_NM_02 = "AJE_PTRN_ACTL_NM_02";

    /** DB Item Column Name */
    static final String AJE_PTRN_IND_TP_CD_03 = "AJE_PTRN_IND_TP_CD_03";

    /** DB Item Column Name */
    static final String AJE_PTRN_IND_TP_NM_03 = "AJE_PTRN_IND_TP_NM_03";

    /** DB Item Column Name */
    static final String AJE_PTRN_ACTL_CD_03 = "AJE_PTRN_ACTL_CD_03";

    /** DB Item Column Name */
    static final String AJE_PTRN_ACTL_NM_03 = "AJE_PTRN_ACTL_NM_03";

    /** DB Item Column Name */
    static final String AJE_INTFC_COL_TXT_01 = "AJE_INTFC_COL_TXT_01";

    /** DB Item Column Name */
    static final String AJE_INTFC_COL_TXT_02 = "AJE_INTFC_COL_TXT_02";

    /** DB Item Column Name */
    static final String AJE_INTFC_COL_TXT_03 = "AJE_INTFC_COL_TXT_03";

    /** DB Item Column Name */
    static final String SYS_SRC_NM = "SYS_SRC_NM";

    /** DB Item Column Name */
    static final String TRX_NM = "TRX_NM";

    /** DB Item Column Name */
    static final String TRX_RSN_NM = "TRX_RSN_NM";

    /** DB Item Column Name */
    static final String JRNL_SRC_CD = "JRNL_SRC_CD";

    /** DB Item Column Name */
    static final String JRNL_SRC_NM = "JRNL_SRC_NM";

    /** DB Item Column Name */
    static final String JRNL_CATG_CD = "JRNL_CATG_CD";

    /** DB Item Column Name */
    static final String JRNL_CATG_NM = "JRNL_CATG_NM";

    /** DB Item Column Name */
    static final String DR_CR_TP_CD = "DR_CR_TP_CD";

    /** DB Item Column Name */
    static final String AJE_LINK_FLG = "AJE_LINK_FLG";

    /** DB Item Column Name */
    static final String AJE_LINE_IDX_NUM = "AJE_LINE_IDX_NUM";

    /** DB Item Column Name */
    static final String AJE_LINE_IDX_DESC_TXT = "AJE_LINE_IDX_DESC_TXT";

    /** DB Item Column Name */
    static final String AJE_COA_CMPY_CD = "AJE_COA_CMPY_CD";

    /** DB Item Column Name */
    static final String AJE_COA_BR_CD = "AJE_COA_BR_CD";

    /** DB Item Column Name */
    static final String AJE_COA_CC_CD = "AJE_COA_CC_CD";

    /** DB Item Column Name */
    static final String AJE_COA_ACCT_CD = "AJE_COA_ACCT_CD";

    /** DB Item Column Name */
    static final String AJE_COA_PROD_CD = "AJE_COA_PROD_CD";

    /** DB Item Column Name */
    static final String AJE_COA_CH_CD = "AJE_COA_CH_CD";

    /** DB Item Column Name */
    static final String AJE_COA_AFFL_CD = "AJE_COA_AFFL_CD";

    /** DB Item Column Name */
    static final String AJE_COA_PROJ_CD = "AJE_COA_PROJ_CD";

    /** DB Item Column Name */
    static final String AJE_COA_EXTN_CD = "AJE_COA_EXTN_CD";

    /** DB Item Column Name */
    static final String AJE_LINE_IND_TP_NM = "AJE_LINE_IND_TP_NM";

    /** DB Item Column Name */
    static final String AJE_LINE_IND_TP_CD = "AJE_LINE_IND_TP_CD";
    
    /** DB Item Column Name */
    static final String CPO_ORD_NUM = "CPO_ORD_NUM";
    
    /** DB Item Column Name */
    static final String SO_NUM = "SO_NUM";
    
    /** DB Item Column Name */
    static final String  INVTY_VAL_FLG = "INVTY_VAL_FLG";

    /** Fixed Value : Error ID for Journal data was not determiend */
    static final String DEF_ERROR_MSG_ID = "NFAM0038E";

    /** Fixed Value : Error message for Journal data was not determiend */
    static final String DEF_ERROR_MSG_TXT = "Journal data was not determiend. Please check the target transaction in AJE interface.";

    /** Fixed Value : COA */
    static final String COA_ERROR_MSG_ID = "NFAM0063E";

    /** Fixed Value : Error Message for COA */
    static final String COA_ERROR_MSG_TXT = "Value for @ could not be retrieved. Please check the target transaction in AJE interface.";

    /** Error Message (No Target AJE Pattern.) */
    public static final String NFAM0037E = "NFAM0037E";

    /** Fixed Value : Error Message for No Aje Pattern found */
    static final String NO_AJE_PTRN_ERROR_MSG_TXT = "No Target AJE Pattern.";

    /** Fixed Value : COA description in error message */
    static final String COA_ERROR_CMPY = "COA Company";
    
    /** Fixed Value : COA description in error message */
    static final String COA_ERROR_BR = "COA Branch";

    /** Fixed Value : COA description in error message */
    static final String COA_ERROR_CC = "COA Cost Center";

    /** Fixed Value : COA description in error message */
    static final String COA_ERROR_PROD = "COA Product";

    /** Fixed Value : COA description in error message */
    static final String COA_ERROR_CH = "COA Channel";

    /** Fixed Value : COA description in error message */
    static final String COA_ERROR_AFFL = "COA Affiliate";

    /** Fixed Value : COA description in error message */
    static final String COA_ERROR_PROJ = "COA Project";

    /** Fixed Value : COA description in error message */
    static final String COA_ERROR_ACCT = "COA Account Code";
    
    /** Fixed Value : COA description in error message */
    static final String COA_ERROR_EXTN = "COA Extension Code";

    /** Fixed Value : Error ID for null error */
    static final String NULL_ERROR_MSG_ID = "NFAM0064E";

    /** Fixed Value : Error ID for Journal Insert Error */
    static final String JRNL_INSERT_ERROR_MSG_ID = "NFAM0065E";

    /** Fixed Value : Error ID for Journal Insert Error */
    static final String JRNL_INSERT_ERROR_MSG_TXT = "Journal Entry cannot be created.";

    /** Fixed Value : Error ID for Cost Center Error */
    static final String COA_CC_ERROR_MSG_ID = "NFAM0071E";
    
    /** Error Message ( @ couldn't be completed by unexpected reason.) */
    static final String NFAM0035E = "NFAM0035E";
    
    /** Error Message (Unique Constraint Violation) */
    static final String ZZBM0074E = "ZZBM0074E";
    
    /** Information Message (No Transaction in AJE @ Interface File.) */
    public static final String NFAM0036I = "NFAM0036I";
    
    /** Error Message (Cannot get currency information.) */
    public static final String NFAM0048E = "NFAM0048E";
    
    /** Error Message (GL_DT error) */
    public static final String NFAM0076E = "NFAM0076E";
    
    /** Error Message (Cannot get interface id.) */
    public static final String NFAM0039E = "NFAM0039E";

    /** Error Message (No results in AJE Interface Control Table by unexpected reason.) */
    public static final String NFAM0040E = "NFAM0040E";

    // START 2018/03/12 E.Kameishi [QC#23689,ADD]
    /** Error Message (Unexpected Error Occurred.) */
    static final String NFAM0032E = "NFAM0032E";
    // END 2018/03/12 E.Kameishi [QC#23689,ADD]

    /** 08/26/2015 CSA */
    /** */
    public static final String AJE_PTRN_IND_TP_CD_INVOICE_INVENTORY = "001";
    
    /** */
    public static final String AJE_PTRN_ACTRL_CD_INVOICE = "I";
    

    /** */
    static final String COA_SEG_LKUP_TP_IB_VAL = "@IB";

    /** */
    static final String COA_SEG_LKUP_TP_MACHINE_VAL = "@MACH";

    /** */
    public static final String SRC_TBL_CD = "SRC_TBL_CD";

    /** */
    public static final String SRC_TBL_NM = "SRC_TBL_NM";

    /** */
    public static final String SRC_ATTRB_CD = "SRC_ATTRB_CD";

    /** */
    public static final String SRC_ATTRB_NM_01 = "SRC_ATTRB_NM_01";

    /** */
    public static final String SRC_ATTRB_NM_02 = "SRC_ATTRB_NM_02";

    /** */
    public static final String COA_SEG_LKUP_TP_CD = "COA_SEG_LKUP_TP_CD";

    /** */
    public static final String COA_ATTRB_NM_01 = "COA_ATTRB_NM_01";

    /** */
    public static final String COA_ATTRB_NM_02 = "COA_ATTRB_NM_02";

    /** */
    public static final String COA_ATTRB_NM_03 = "COA_ATTRB_NM_03";

    /** */
    public static final String COA_ATTRB_NM_04 = "COA_ATTRB_NM_04";

    /** */
    public static final String COA_ATTRB_NM_05 = "COA_ATTRB_NM_05";

    /** */
    public static final String COA_ATTRB_NM_06 = "COA_ATTRB_NM_06";

    /** */
    public static final String COA_ATTRB_NM_07 = "COA_ATTRB_NM_07";

    /** */
    public static final String COA_ATTRB_NM_08 = "COA_ATTRB_NM_08";

    /** */
    public static final String COA_ATTRB_NM_09 = "COA_ATTRB_NM_09";
    
    /** */
    public static final String JRNL_QTY_FLIP_FLG = "JRNL_QTY_FLIP_FLG";
    
    /** */
    public static final String JRNL_AMT_FLIP_FLG = "JRNL_AMT_FLIP_FLG";

    /** Default COA Value*/
    public static final String DEF_COA_VAL = "#DEF";
    
    /** Default COA Value*/
    public static final String AJE_COA_DEF_VALUES = "AJE_COA_DEF_VALUES";
    
    /** Out Of Terrytory Value*/
    public static final String OUT_OF_TERRITORY_BR = "OUT_TRTY_BR_CD";
    
    /** divider*/
    public static final String COA_DIV = ".";
    
    /** Length of reference text */
    public static final int REF_TXT_LEN = 1000;

    // START 2016/11/29 S.Fujita [QC#16075,ADD]
    /** Mail Template */
    public static final String MAIL_NFAB0020M001 = "NFAB0020M001";

    /** Mail Group Id Key: From*/
    public static final String MAIL_GRP_ID_FROM = "FROM0003";

    /** Mail Group Id Key: To */
    public static final String MAIL_GRP_ID_TO = "NFAB0010";

    /** Mail Key (From) */
    public static final String MAIL_KEY_1_FROM = "NFA";

    /** Mail Group Type: From */
    public static final String MAIL_TYPE_FROM = "FROM";

    /** Mail Group Type: To */
    public static final String MAIL_TYPE_TO = "TO";

    /** Error Message (E-mail address <Type: [@], Group: [@], Key: [@]>  cannot be obtained.) */
    public static final String NFAM0182E = "NFAM0182E";

    /** Error Message (The mail template cannot be acquired.  <Template ID: [@]>) */
    public static final String NFAM0183E = "NFAM0183E";
    // END   2016/11/29 S.Fujita [QC#16075,ADD]
    // START 2018/04/12 E.Kameishi [QC#23378,ADD]
    /** */
    public static final String SEG_BS_PL_TP_CD_BS = "B";

    /** */
    public static final String SEG_COA_ACCT_CD_ITEM_REV = "@ITEM-REV";

    /** */
    public static final String SEG_COA_CMPY_CD = "COA_CMPY_CD";

    /** */
    public static final String SEG_COA_AFFL_CD = "COA_AFFL_CD";

    /** */
    public static final String SEG_COA_BR_CD = "COA_BR_CD";

    /** */
    public static final String SEG_COA_CH_CD = "COA_CH_CD";

    /** */
    public static final String SEG_COA_CC_CD = "COA_CC_CD";

    /** */
    public static final String SEG_COA_ACCT_CD = "COA_ACCT_CD";

    /** */
    public static final String SEG_COA_PROD_CD = "COA_PROD_CD";

    /** */
    public static final String SEG_COA_PROJ_CD = "COA_PROJ_CD";

    /** */
    public static final String SEG_COA_EXTN_CD = "COA_EXTN_CD";

    /** */
    static final String SEG_DR_CD = "D";

    /** */
    static final String SEG_CR_CD = "C";

    /** */
    static final String SEG_AJE_INTFC_CMNT_TXT = "AJE_INTFC_CMNT_TXT";

    /** */
    static final String SEG_DS_CONTR_PK = "DS_CONTR_PK";

    /** */
    static final String SEG_DS_CONTR_DTL_PK = "DS_CONTR_DTL_PK";
    // END 2018/04/12 E.Kameishi [QC#23378,ADD]

}
