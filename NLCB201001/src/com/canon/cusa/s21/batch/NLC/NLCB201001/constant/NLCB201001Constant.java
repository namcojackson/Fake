/**
 * <pre>Copyright (c) 2021 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NLC.NLCB201001.constant;

/**
 * <pre>
 * Batch Program Class for Inventory Master to Baxter
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/13/2021   CITS            A.Marte         Create          QC#58267
 * 11/04/2021   CITS            A.Marte         Update          QC#58989-2
 * 06/22/2022   CITS            R.Azucena       Update          QC#60240
 * 07/06/2022   CITS            R.Azucena       Update          QC#60240-1
 * 07/21/2022   CITS            R.Azucena       Update          QC#60240-2
 * 04/03/2024   CITS            D.Quan          Update          QC#63814
 *</pre>
 */
public class NLCB201001Constant {
    // *********************************************************
    // Constants: Basic
    // *********************************************************

    /** Business ID */
    public static final String BUSINESS_ID = "NLCB2010";

    /** Fetch size */
    public static final int FETCH_SIZE = 1000;

    /** Debug level for debug */
    public static final int CST_DEBUG_MSG_LVL = 1;

    // *********************************************************
    // Constants: Message ID
    // *********************************************************
    /**
     * Message ID: ZZM9000E [@] field is mandatory.
     */
    public static final String ZZM9000E = "ZZM9000E";

    /**
     * Message ID: NLAM1295E Failed to register [@]
     */
    public static final String NLAM1295E = "NLAM1295E";

    // *********************************************************
    // Constants: Message Parameter
    // *********************************************************

    /** Parameter Name: GLBL_CMPY_CD */
    public static final String PARAM_NM_GLBL_CMPY_CD = "Global Company Code";

    /** Parameter Name: INTERFACE_ID */
    public static final String PARAM_NM_INTERFACE_ID = "Interface ID";

    /** Parameter Name: VAR_USER1 */
    public static final String PARAM_NM_VAR_USER1 = "VAR_USER1";

    /** Parameter Name: VAR_USER2 */
    public static final String PARAM_NM_VAR_USER2 = "VAR_USER2";

    // *********************************************************
    // Constants: Format pattern
    // *********************************************************

    /** Datetime format : yyyyMMddHHmmss */
    public static final String FMT_YYYYMMDDHHMMSS = "yyyyMMdd_HHmmss";

    // *********************************************************
    // Constants: Table Name
    // *********************************************************

    /** DB Table NLCI2010_01 */
    public static final String TBL_NLCI2010_01 = "NLCI2010_01";

    /** DB Table NLCI2011_01 */
    public static final String TBL_NLCI2011_01 = "NLCI2011_01";

    /** IF Name NLCI2010 */
    public static final String IF_NLCI2010 = "NLCI2010";

    /** IF Name NLCI2011 */
    public static final String IF_NLCI2011 = "NLCI2011";

 // *********************************************************
    // Constants: Column Name
    // *********************************************************

    /** Column name of MDSE_CD */
    public static final String MDSE_CD = "MDSE_CD";

    /** Column name of TRD_PTNR_WH_RG_NM */
    public static final String WH_RG_CD = "WH_RG_CD";

    /** Column name of RTL_WH_CD */
    public static final String RTL_WH_CD = "RTL_WH_CD";

    /** Column name of STK_LOC_CD */
    public static final String STK_LOC_CD = "STK_LOC_CD";

    /** Column name of STK_STS_NM */
    public static final String STK_STS_NM = "STK_STS_NM";

    /** Column name of INVTY_STS_CD */
    public static final String INVTY_STS_CD = "INVTY_STS_CD";

    /** Column name of VIS_QTY */
    public static final String VIS_QTY = "VIS_QTY";

    /** Column name of SER_NUM */
    public static final String SER_NUM = "SER_NUM";

    /** Column name of RSVD_FIRST_CD */
    public static final String RSVD_FIRST_CD = "RSVD_FIRST_CD";

    /** Column name of INVTY_SRC_CD */
    public static final String INVTY_SRC_CD = "INVTY_SRC_CD";

    // *********************************************************
    // Constants: Value
    // *********************************************************

    /** */
    public static final String VAL_INVTY_AVAL_QTY = "0";

    /** */
    public static final String VAL_STK_LOC_CD = "0";

    /** */
    public static final String VAL_INBD_INVTY_STS_CD = "INBOUND";

    /** */
    public static final String VAL_ONHND_INVTY_STS_CD = "OH";

    /** */
    public static final String VAL_ALLOCATED_INVTY_STS_CD = "ALLOCATED";

    /** */
    public static final String VAL_INVTY_SRC_CD = "S21";

    /** */
    public static final Integer VAL_MAX_CD_LEN = 12;

    /** */
    public static final Integer VAL_ZERO = 0;

    /** */
    public static final String S21 = "S21";

    /** */
    public static final String VAL_3Z1_WH = "3Z1";

    /** */
    public static final String VAL_2P1_WH = "2P1";

    // START 2021/11/04 A.Marte [QC#58989-2, ADD]
    /** */
    public static final String VAL_EMPTY = "";

    /** */
    public static final String VAL_MNX = "MNX";

    /** */
    public static final String VAR_CHAR_CONST_NLCB2010_CONVT_FROM_WH = "NLCB2010_CONVT_FROM_WH";

    /** */
    public static final String VAR_CHAR_CONST_NLCB2010_CONVT_INTO_WH = "NLCB2010_CONVT_INTO_WH";
    
    // 2024/04/03 QC#63814 Add Start
    /** VAR_CHAR_CONST_NLCB2010_CONVT_FROM_SWH - NLCB2010_CONVT_FROM_SWH */
    public static final String VAR_CHAR_CONST_NLCB2010_CONVT_FROM_SWH = "NLCB2010_CONVT_FROM_SWH";
    
    /** VAR_CHAR_CONST_NLCB2010_CMP_TO_SWH - NLCB2010_CMP_TO_SWH*/
    public static final String VAR_CHAR_CONST_NLCB2010_CMP_TO_SWH = "NLCB2010_CMP_TO_SWH";
    
    /** TRD_PTNR_WH_RG_TP_CD_CSA - CSA*/
    public static final String TRD_PTNR_WH_RG_TP_CD_CSA = "CSA";
    // 2024/04/03 QC#63814 Add End

    /** */
    public static final String VAR_CHAR_CONST_NLCB2010_STK_LOC_CD = "NLCB2010_STK_LOC_CD";

    /** */
    public static final String VAR_CHAR_CONST_NLCB2010_INBD_TRGT_WH = "NLCB2010_INBD_TRGT_WH";

    /** */
    public static final String VAR_CHAR_CONST_NLCB2010_INBD_IR_TRGT_WH = "NLCB2010_INBD_IR_TRGT_WH";

    /** */
    public static final String VAR_CHAR_CONST_NLCB2010_OH_TRGT_WH = "NLCB2010_OH_TRGT_WH";

    /** */
    public static final String VAR_CHAR_CONST_NLCB2010_ALLOC_TRGT_WH = "NLCB2010_ALLOC_TRGT_WH";
    // END 2021/11/04 A.Marte [QC#58989-2, ADD]

    // START 2022/06/22 R.Azucena [QC#60240 ADD]
    /** VAR_CHAR_CONST_NLCB2010_INBD_TRPO_TRGT_WH - NLCB2010_INBD_TRPO_TRGT_WH */
    public static final String VAR_CHAR_CONST_NLCB2010_INBD_TRPO_TRGT_WH = "NLCB2010_INBD_TRPO_TRGT_WH";

    /** VAL_SWH_CD_G - G */
    public static final String VAL_SWH_CD_G = "G";

    /** VAL_TECH - TECH */
    public static final String VAL_TECH = "TECH";

    // START 2022/07/06 R.Azucena [QC#60240-1 DEL]
    /** VAL_SUBWAREHOUSE - Subwarehouse */
    // public static final String VAL_SUBWAREHOUSE = "Subwarehouse";
    // END 2022/07/06 R.Azucena [QC#60240-1 DEL]

    /** VAL_CREATE_MATERIAL - CREATE MATERIAL */
    public static final String VAL_CREATE_MATERIAL = "CREATE MATERIAL";

    /** STK_STS_NM_MAX_LEN - 16 */
    public static final Integer STK_STS_NM_MAX_LEN = 20;
    // END 2022/06/22 R.Azucena [QC#60240 ADD]

    // START 2022/07/06 R.Azucena [QC#60240-2 ADD]
    /** VAL_GOOD - GOOD */
    public static final String VAL_GOOD = "GOOD";

    /** VAR_CHAR_CONST_NLCB2010_RTL_WH_CATG_CD - NLCB2010_RTL_WH_CATG_CD */
    public static final String VAR_CHAR_CONST_NLCB2010_RTL_WH_CATG_CD = "NLCB2010_RTL_WH_CATG_CD";
    // END 2022/07/06 R.Azucena [QC#60240-2 ADD]
}
