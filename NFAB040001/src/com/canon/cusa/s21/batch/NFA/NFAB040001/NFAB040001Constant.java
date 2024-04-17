package com.canon.cusa.s21.batch.NFA.NFAB040001;

import java.math.BigDecimal;

/**
 * Class name: NFAB040001Constant
 * <dd>The class explanation: Constant variable for NFAB040001.
 * <dd>Remarks:
 * @version 1.0
 * @author H. Naoi
 */
public interface NFAB040001Constant {

    // ** Fixed Value ** //
    /** Fixed Value : Error Message Parameter. */
    static final String MSG_PARAM1 = "AJE GL Interface Entry Process";

    /** Fixed Value : Error Message Parameter. */
    static final String MSG_PARAM2 = "AJE Journal Entry Update Process";

    /** Fixed Value : Oracle Status Code. */
    static final String ORA_STATUS_VAL = "NEW";

    /** Fixed Value : Oracle Set of Book Id. */
    static final BigDecimal ORA_SOB_VAL = new BigDecimal("2");

    /** Fixed Value : Oracle Created by. */
    static final BigDecimal ORA_CREATE_VAL = new BigDecimal("101");

    /** Fixed Value : Oracle Actual Code. */
    static final String ORA_ACTL_CD_VAL = "A";
    
    // /** Fixed Value : AJE Interface Type Code for Loan
    // Depreciation. */
    // static final String AJE_INTFC_TP_CD_VAL = "06";

    /** Fixed Value : Each Bulk Insert Count */
    static final int BULK_INSERT_COUNT = 10000;
    
    /** Fixed Value : CCY Type */
    static final String CCY_TYPE_VAL_USER = "User";
    
    /** Fixed Value : symbol of division  */
    static final String SIMBOL_OF_DIV = "/";

    /** Fixed Value : symbol of division  */
    static final int SCALE_VAL = 5;
    
    /** Fixed Value : length of jrnl_catg  */
    static final int JRNL_CATG_NM_LEN = 25;
    
    /** Fixed Value : length of refference  */
    static final int ORA_REF_LEN = 20;
    
    /** Fixed Value : BILL_TO_CUST_CD required account code  */
    static final String BILL_TO_REQ_ACCT_CD1 = "11991005";
    
    /** Fixed Value : BILL_TO_CUST_CD required account code  */
    static final String BILL_TO_REQ_ACCT_CD2 = "21339001";
    
    /** Fixed Value : Mail From Group ID  */
    static final String MAIL_GRP_FROM_ID = "FROM0003";
    
    /** Fixed Value : Mail To Group ID  */
    static final String MAIL_GRP_TO_ID = "NFAB0010";
    
    /** Fixed Value : Mail Template ID  */
    static final String MAIL_TMPLT_ID = "NFAB0010M001";
    
    /** Fixed Value : Initial Exchange Rate Value. Loan Depreciation Amount must be in dollars */
    static final BigDecimal INITIAL_EXCH_RATE_VAL = new BigDecimal("1.00");
    
    /** Fixed Value : BigDecimal Amount 0 */
    static final BigDecimal AMT_VAL_0 = new BigDecimal("0");

    // ** DB Item Column Name's Fixed Value ** //
    /** DB Item Column Name */
    static final String AJE_INTFC_PK = "AJE_INTFC_PK";

    /** DB Item Column Name */
    static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** DB Item Column Name */
    static final String JRNL_ENTRY_PK = "JRNL_ENTRY_PK";

    /** DB Item Column Name */
    static final String GL_DT = "GL_DT";

    /** DB Item Column Name */
    static final String JRNL_SRC_CD = "JRNL_SRC_CD";

    /** DB Item Column Name */
    static final String JRNL_CATG_CD = "JRNL_CATG_CD";
    
    /** DB Item Column Name */
    static final String COA_CMPY_CD = "COA_CMPY_CD";

    /** DB Item Column Name */
    static final String COA_BR_CD = "COA_BR_CD";

    /** DB Item Column Name */
    static final String COA_CC_CD = "COA_CC_CD";

    /** DB Item Column Name */
    static final String COA_ACCT_CD = "COA_ACCT_CD";

    /** DB Item Column Name */
    static final String COA_PROD_CD = "COA_PROD_CD";

    /** DB Item Column Name */
    static final String COA_CH_CD = "COA_CH_CD";

    /** DB Item Column Name */
    static final String COA_AFFL_CD = "COA_AFFL_CD";

    /** DB Item Column Name */
    static final String COA_PROJ_CD = "COA_PROJ_CD";

    /** DB Item Column Name */
    static final String COA_EXTN_CD = "COA_EXTN_CD";

    /** DB Item Column Name */
    static final String DEAL_CCY_CD = "DEAL_CCY_CD";
    
    /** DB Item Column Name */
    static final String STD_CCY_CD = "STD_CCY_CD";

    /** DB Item Column Name */
    static final String EXCH_RATE = "EXCH_RATE";

    /** DB Item Column Name */
    static final String JRNL_DEAL_DR_AMT = "JRNL_DEAL_DR_AMT";

    /** DB Item Column Name */
    static final String JRNL_DEAL_CR_AMT = "JRNL_DEAL_CR_AMT";

    /** DB Item Column Name */
    static final String JRNL_FUNC_DR_AMT = "JRNL_FUNC_DR_AMT";

    /** DB Item Column Name */
    static final String JRNL_FUNC_CR_AMT = "JRNL_FUNC_CR_AMT";

    /** DB Item Column Name */
    static final String ORCL_REF_10_TXT = "ORCL_REF_10_TXT";

    /** DB Item Column Name */
    static final String ORCL_ATTRB_11_TXT = "ORCL_ATTRB_11_TXT";
    
    /** DB Item Column Name */
    static final String ACCT_ARTH_TP_CD = "ACCT_ARTH_TP_CD";
    
    /** DB Item Column Name */
    static final String TOC_CD = "TOC_CD";
    
    /** DB Item Column Name */
    static final String BILL_TO_CUST_CD = "BILL_TO_CUST_CD";
    
    /** DB Item Column Name */
    static final String AJE_ERR_MSG_TXT = "AJE_ERR_MSG_TXT";
   

}
