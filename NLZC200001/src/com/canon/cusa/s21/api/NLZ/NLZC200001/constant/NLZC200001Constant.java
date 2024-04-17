/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC200001.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * DS Return RWS API
 * Constant Class.
 *</pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/16/2015   CITS            T.Tokutomi      Create
 * 06/23/2016   CSAI            Y.Imazu         Update          QC#10650
 * 03/22/2017   CITS            T.Tokutomi      Update          QC#18115
 * 06/13/2022   Hitachi         K.Kim           Update          QC#60022
 * 04/07/2023   Hitachi         M.Nakajima      Update          QC#61001
 *</pre>
 */
public class NLZC200001Constant {

    /**
     * Date Format RWS_SCHD_DTL_TRK_SQ
     */
    public static final String RWS_SCHD_DTL_TRK_SQ = "RWS_SCHD_DTL_TRK_SQ";

    /**
     * Date Format TIMESTAMP
     */
    public static final String FORMAT_TIMESTAMP = "yyyyMMddHHmmssSSS";

    /**
     * Date Format TIMESTAMP
     */
    public static final String FORMAT_DATE_TIME = "yyyyMMddHHmmss";

    /**
     * Date Format DATE
     */
    public static final String FORMAT_DATE = "yyyyMMdd";

    /**
     * 24 Hour
     */
    public static final BigDecimal HOUR_24 = new BigDecimal(24);

    /**
     * RWS Reference Number Length
     */
    public static final int RWS_REF_NUM_LEN = 15;

    /**
     * LF_PAD_CHAR
     */
    public static final String LF_PAD_CHAR = "0";

    /**
     * WMS_DROP_STS_CD Not Drop
     */
    public static final String WMS_DROP_STS_CD_NOT_DRP = "0";

    /**
     * MAX_CHAR_DATE
     */
    public static final int MAX_CHAR_DATE = 8;

    /**
     * MAX_CHAR_SUB_LINE_NUM
     */
    public static final int MAX_CHAR_SUB_LINE_NUM = 3;

    /**
     * MAX_CHAR_CTAC_PSN_NM
     */
    public static final int MAX_CHAR_CTAC_PSN_NM = 25;

    /**
     * MAX_RWS_MSG_TXT
     */
    public static final int MAX_RWS_MSG_TXT = 65;

    // VAR CHAR CONST

    /**
     * VAR CHAR CONST Name : DFF_RTRN_TRNSP_DAYS
     */
    public static final String DFF_RTRN_TRNSP_DAYS = "DFF_RTRN_TRNSP_DAYS";

    /**
     * VAR CHAR CONST Name : DFF_RTRN_FRT_CHRG_TO_CD
     */
    public static final String DFF_RTRN_FRT_CHRG_TO_CD = "DFF_RTRN_FRT_CHRG_TO_CD";

    /**
     * VAR CHAR CONST Name : DFF_RTRN_FRT_CHRG_METH_CD
     */
    public static final String DFF_RTRN_FRT_CHRG_METH_CD = "DFF_RTRN_FRT_CHRG_METH_CD";

    /**
     * VAR CHAR CONST Name : DFF_ADD_RTRN_PICK_UP_DAYS
     */
    public static final String DFF_ADD_RTRN_PICK_UP_DAYS = "DFF_ADD_RTRN_PICK_UP_DAYS";

    // START 2022/06/13 [QC#60022,ADD]
    /**
     * VAR CHAR CONST Name : STI_TARGET_SCE_ORD_TP_CD
     */
    public static final String VAR_CHAR_CONCT_NM_TG_SCE_ORD_TP = "STI_TARGET_SCE_ORD_TP_CD";
    // END   2022/06/13 [QC#60022,ADD]

    // START 2023/04/07 [QC#61001,ADD]
    /**
     * VAR CHAR CONST Name : AGW_TARGET_SCE_ORD_TP_CD
     */
    public static final String AGW_TARGET_SCE_ORD_TP_CD = "AGW_TARGET_SCE_ORD_TP_CD";
    // END   2023/04/07 [QC#61001,ADD]

    // Message ID
    /**
     * NLZM1001E : A value is not set in the required parameter field.
     */
    public static final String NLZM1001E = "NLZM1001E";

    /**
     * NLZM1025E : Specified RWS_REF_NUM,WH_CD has already been
     * registered.
     */
    public static final String NLZM1025E = "NLZM1025E";

    /**
     * NLZM2303E : There is different Warehouse code for the
     * details.Please unify Warehouse code of the details.
     */
    public static final String NLZM2303E = "NLZM2303E";

    /**
     * NLZM2304E : The data could not be registered to the RWS_DTL
     * Table.
     */
    public static final String NLZM2304E = "NLZM2304E";

    /**
     * NLZM2306E : The data could not be registered to the
     * RWS_SCHD_DTL Table.
     */
    public static final String NLZM2306E = "NLZM2306E";

    /**
     * NLZM2307E : The data could not be registered to the
     * RWS_SCHD_DTL_TRK Table.
     */
    public static final String NLZM2307E = "NLZM2307E";

    /**
     * NLZM2308E : The data could not be registered to the RWS_SER
     * Table.
     */
    public static final String NLZM2308E = "NLZM2308E";

    /**
     * NLZM2309E : The data could not be registered to the RWS_HDR
     * Table.
     */
    public static final String NLZM2309E = "NLZM2309E";

    /**
     * NLZM2311E : The data could not be registered to the RWS_MSG
     * Table.
     */
    public static final String NLZM2311E = "NLZM2311E";

}
