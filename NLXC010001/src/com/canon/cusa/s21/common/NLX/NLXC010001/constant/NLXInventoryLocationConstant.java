/**
 * <Pre>
 * 
 * Copyright(c)2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
package com.canon.cusa.s21.common.NLX.NLXC010001.constant;

/**
 * <pre>
 *  Date         Company         Name            Create/Update   Defect No
 *  ----------------------------------------------------------------------
 *  07/24/2009   Fujitsu         FAP)D.Ushida    Create          N/A
 *  01/26/2010   Fujitsu         M.Yamada        Update          Message ID Change
 *  06/08/2010   Fujitsu         T.Ishii         Update          I/F Change
 *  05/01/2013   Fujitsu         Y.Taoka         Update          OCE WDS R-WH001
 * </pre>
 */
public interface NLXInventoryLocationConstant {

    /**
     * Error Message: You have no authorized WH.
     */
    String MSG_ID_GENERAL_ERROR = "NLXM1022E";

    /**
     * WH_CD
     */
    String WH_WH_CD = "whCd";

    /**
     * LOC_NM
     */
    String WH_LOC_NM = "locNm";

    /**
     * ASTERISK
     */
    String ASTERISK = "*";

    /**
     * COLON
     */
    String COLON = ":";

    /**
     * WH_CD length
     */
    int WH_CD_LENGTH = 20; //MOD 05/01/2013 R-WH001

    /**
     * VAR_CHAR_CONST_NM_ADDL_WH_ADJ
     */
    String VAR_CHAR_CONST_NM_ADDL_WH_ADJ = "ADDL_WH_ADJ";

    /**
     * VAR_CHAR_CONST_NM_ADDL_WH_ADJ_DELIMITER
     */
    String VAR_CHAR_CONST_NM_ADDL_WH_ADJ_DELIMITER = ",";
}
