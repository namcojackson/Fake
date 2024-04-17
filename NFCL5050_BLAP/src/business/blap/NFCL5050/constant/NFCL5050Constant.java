/**
 * <Pre>
 * 
 * Copyright(c)2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/24/2009   Fujitsu         FXS)Z.Wang      Create          N/A
 * 10/29/2009   Fujitsu         FAP)D.Kato      Update          DefID 0346
 * 07/13/2010   Fujitsu         I.Kondo         Update          DefID 7731 No.198
 * 08/06/2010   Fujitsu         I.Kondo         Update          Merge.
 * 08/10/2010   Fujitsu         I.Kondo         Update          For EXPT_FLG.
 * 04/11/2011   Fujitsu         K.Kimura        Update          DefID:2017
 *</pre>
 */
package business.blap.NFCL5050.constant;

/**
 * NFCL5050Constant interface.
 */
public interface NFCL5050Constant {

    /**
     */
    String BIZAPL_RTNCD_NORMAL = "0000";

    /**
     */
    String BIZAPL_RTNCD_LOGIC_RM = "0010";

    /**
     */
    String BIZAPL_RTNCD_NOT_FOUND = "2000";

    /**
     */
    String BIZAPL_RTNCD_DUPLICATE = "2300";

    /**
     */
    String BIZAPL_RTNCD_RESULT_MAX_OVER = "3000";
    
    /** MAX_SEARCH_CNT */
    int MAX_SEARCH_CNT = 999;

    // /**
    // * "MAX NUMBER "
    // */
    // static final int CHECKBOX_MAX = 99;

    /**
     * "MAX NUMBER "
     */
    int CHECKBOX_0430SEARCH_MAX = 1;

    /**
     * "MODE "
     */
    String MOD_0430SEARCH = "1";

    /**
     */
    String SUMMARY_STATUS_Y = "Y";

    /**
     */
    String SUMMARY_STATUS_N = "N";

    /** GL_DT_LENGTH */
    int GL_DT_LENGTH = 6;

    /** end of month day */
    String END_MTH_DAY = "31";

    /** AR_RCPT_TRX_TP_CD:01 Advance Receipt */
    String AR_RCPT_TRX_TP_CD_ADVANCE = "01";

    // START 2018/07/20 Y.Matsui [QC#26985,ADD]
    String TRX_NUM_SEPARATOR = ",";
    // END   2018/07/20 Y.Matsui [QC#26985,ADD]
}
