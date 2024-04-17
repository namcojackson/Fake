/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWC.NWCB726001.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * CSMP Send Claim Batch Constant
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/21   Fujitsu         H.Ikeda         Create          N/A
 * 2017/06/08   Fujitsu         A.Kosai         Update          QC#18589
 *</pre>
 */
public class NWCB726001Constant {

    /** Global Company Code */
    public static final String GLBL_CMPY_CD = "Global Company Code";

    /** Interface Id */
    public static final String INTFC_ID = "Interface Id";

    /** DEFAULT_FETCH_SIZE */
    public static final int DEFAULT_FETCH_SIZE = 1000;

    /** segment Id */
    public static final BigDecimal SEGMENT_ID = BigDecimal.ONE;

    /** unit Id */
    public static final BigDecimal UNIT_ID = BigDecimal.ONE;

    /** DS_INV */
    public static final String INV = "INV";

    // 2017/06/08 QC#18589 Add Start
    /** Default Serial Number */
    public static final String DEFAULT_SER_NUM = "-";
    // 2017/06/08 QC#18589 Add End

    /** CSMP_TRD_PTNR_CD */
    public static final String CSMP_TRD_PTNR_CD = "810CBSCLSE";

    /** CSMP_REC_TP_CD */
    public static final String CSMP_REC_TP_CD = "CLM";

    /** Shell Variable "@" has not been set. */
    public static final String NWCM0059E = "NWCM0059E";

    /** There is no parameter in [@]. */
    public static final String NWCM0144E = "NWCM0144E";

    /** It failed to update [@]. */
    public static final String NWCM0110E = "NWCM0110E";

}
