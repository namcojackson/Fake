/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFAL0080.constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;

/**
 * Class name: NFAL0080Constant
 * <dd>The class explanation: Constant variable for business
 * component.
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public interface NFAL0080Constant {

    /** Checked */
    static final String CHECKED = ZYPConstant.CHKBOX_ON_Y;

    /** Checked */
    static final String BLANK = "";

    // Message ID
    /**
     * Another user has already updated target record. Please search
     * again.
     */
    static final String NFAM0004E = "NFAM0004E";

    /**
     * @ already exists in @
     */
    static final String NFAM0070E = "NFAM0070E";

    /**
     * Showing only first @ of total @ records. Please review your
     * search criteria.
     */
    static final String NFAM0001W = "NFAM0001W";

    /** The search ended normally. */
    static final String ZZM8002I = "ZZM8002I";

    /** Process ended normally. */
    static final String ZZM8100I = "ZZM8100I";

    // Message Argument
    /** Record */
    static final String RECORD = "Record";

    /** IMPT_INV_PROD_LINE table */
    static final String ELIG_COA_SEG_PTRN_TABLE = "ELIG_COA_SEG_PTRN table";

    // ** DB Item Column Name's Fixed Value ** //
    /** DB Item Column Name */
    static final String EZUPTIME = "EZUPTIME";

    /** DB Item Column Name */
    static final String EZUPTIMEZONE = "EZUPTIMEZONE";

    /** DB Item Column Name */
    static final String ELIG_COA_SEG_PTRN_CD = "ELIG_COA_SEG_PTRN_CD";

    /** DB Item Column Name */
    static final String COA_SEG_LKUP_TP_CD = "COA_SEG_LKUP_TP_CD";

}
