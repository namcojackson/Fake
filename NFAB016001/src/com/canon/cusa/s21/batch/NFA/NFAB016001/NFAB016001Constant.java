package com.canon.cusa.s21.batch.NFA.NFAB016001;

import java.math.BigDecimal;

/**
 * <pre>
 * Class name: NFAB016001Constant <dd>The class explanation: Constant
 * variable for NFAB016001. <dd>Remarks:
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2022/04/05   Hitachi         K.Kim           Create          QC#57935
 *</pre>
 */
public interface NFAB016001Constant {

    /** Fixed Value : Error Message Parameter. */
    static final String MSG_PARAM = "Cost Management Journal Creation";

    /**
     * Fixed Value : Initial Exchange Rate Value. Loan Depreciation
     * Amount must be in dollars
     */
    static final BigDecimal INITIAL_EXCH_RATE_VAL = new BigDecimal("1.00");

    /** Fixed Value : Reference Text To Oracle */
    static final String ORCL_REF_10_TXT_PREFIX = "AJE ID : ";

    /** Fixed Value : Each Bulk Insert Count */
    static final int BULK_INSERT_COUNT = 1000;

    /** DB Item Column Name */
    static final String JRNL_ENTRY_SQ = "JRNL_ENTRY_SQ";

    /** DB Item Column Name for Interface */
    static final String AJE_CM_INTFC_PK = "AJE_CM_INTFC_PK";

    /** DB Item Column Name */
    static final String CM_ACRL_WRITE_OFF_PK = "CM_ACRL_WRITE_OFF_PK";

    /** DB Item Column Name for Interface */
    static final String GL_DT = "GL_DT";

    /** DB Item Column Name */
    static final String PO_NUM = "PO_NUM";

    /** DB Item Column Name */
    static final String PO_ORD_DTL_LINE_NUM = "PO_ORD_DTL_LINE_NUM";

    /** DB Item Column Name for Interface */
    static final String VND_CD = "VND_CD";

    /** DB Item Column Name */
    static final String AP_VND_INV_NUM = "AP_VND_INV_NUM";

    /** DB Item Column Name for Interface */
    static final String MDSE_CD = "MDSE_CD";

    /** DB Item Column Name for Interface */
    static final String MDSE_NM = "MDSE_NM";

    /** DB Item Column Name for Interface */
    static final String WRITE_OFF_QTY = "WRITE_OFF_QTY";

    /** DB Item Column Name for Interface */
    static final String CM_AJE_TOT_COST_AMT = "CM_AJE_TOT_COST_AMT";

    /** DB Item Column Attrb Name */
    static final String AP_INV_NUM_ATTRB_NM = "AP Invoice Number";

    /** DB Item Column Attrb Name */
    static final String CM_ACRL_WRITE_OFF_PK_ATTRB_NM = "Accrual Write Off PK";

    /** DB Item Column Attrb Name */
    static final String INVTY_TRX_SLP_NUM_ATTRB_NM = "Inventory Trx Ref Num";

    /** DB Item Column Attrb Name */
    static final String PO_ORD_DTL_LINE_NUM_ATTRB_NM = "PO Line#";

    /**
     * It failed to update the @ table.[@]
     */
    static final String NFAM0208E = "NFAM0208E";

}
