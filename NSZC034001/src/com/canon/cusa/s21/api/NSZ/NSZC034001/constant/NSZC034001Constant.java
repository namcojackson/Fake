/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC034001.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * Aggregate Calculation API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/25   Hitachi         T.Kanasaka      Create          NA
 * 2016/01/08   Hitachi         T.Kanasaka      Create          QC2661
 * 2016/03/10   Hitachi         T.Kanasaka      Update          QC5207
 * 2018/04/05   Hitachi         K.Kitachi       Update          QC#23066
 * 2018/05/15   Hitachi         K.Kishimoto     Update          QC#23541
 * 2018/06/20   Hitachi         T.Tomita        Update          QC#26792
 * </pre>
 */
public final class NSZC034001Constant {

    /**
     * Length of Allocated Billable Copy Qty After Decimal Point
     */
    public static final int LENGTH_ALLOC_BLLBL_COPY_QTY = 4;

    // Add Start 2018/06/20 QC#26792
    /**
     * Ratio Scale
     */
    public static final int RATIO_SCALE = 8;
    // Add Start 2018/06/20 QC#26792

    /**
     * Size of ArrayList, Hashmap : 2
     */
    public static final int SIZE_2 = 2;

    /**
     * Size of ArrayList, Hashmap : 4
     */
    public static final int SIZE_4 = 4;

    // START 2018/04/05 K.Kitachi [QC#23066, ADD]
    /**
     * Size of ArrayList, Hashmap : 5
     */
    public static final int SIZE_5 = 5;

    /**
     * Size of ArrayList, Hashmap : 6
     */
    public static final int SIZE_6 = 6;
    // END 2018/04/05 K.Kitachi [QC#23066, ADD]

    /**
     * Rate Change
     */
    public static final BigDecimal RATE_CHANGE = new BigDecimal(100);

    /**
     * Input parameter "Global Company Code" is a mandatory field.
     */
    public static final String NSZM0001E = "NSZM0001E";

    /**
     * Input parameter "Sales Date" is a mandatory field.
     */
    public static final String NSZM0002E = "NSZM0002E";

    /**
     * Input parameter "DS Contract Number" is a mandatory field.
     */
    public static final String NSZM0271E = "NSZM0271E";

    /**
     * The corresponding data does not exist in "GLBL_CMPY".
     */
    public static final String NSZM0380E = "NSZM0380E";

    /**
     * The corresponding data does not exist in "DS_CONTR".
     */
    public static final String NSZM0639E = "NSZM0639E";

    /**
     * Input parameter "Billing Date" is a mandatory field.
     */
    public static final String NSZM0817E = "NSZM0817E";

    /**
     * Failed to insert the AGGR_USG_RECAL.
     */
    public static final String NSZM0818E = "NSZM0818E";

    /**
     * Failed to insert the AGGR_USG_RECAL_DTL.
     */
    public static final String NSZM0819E = "NSZM0819E";

    /**
     * Failed to update the AGGR_USG_RECAL_DTL.
     */
    public static final String NSZM0820E = "NSZM0820E";

    /**
     * Failed to insert the AGGR_USG_RECAL_XS_MTR.
     */
    public static final String NSZM0821E = "NSZM0821E";

    /**
     * Failed to update the AGGR_USG_RECAL_XS_MTR.
     */
    public static final String NSZM0822E = "NSZM0822E";

    /**
     * Failed to insert the SVC_CONTR_XS_MTR_BLLG.
     */
    public static final String NSZM0823E = "NSZM0823E";

    /**
     * Failed to update the SVC_CONTR_BLLG_ALLOC.
     */
    public static final String NSZM0824E = "NSZM0824E";

    /**
     * The corresponding data does not exist in "SVC_CONTR_MTR_BLLG".
     */
    public static final String NSZM0825E = "NSZM0825E";

    /**
     * Failed to update the SVC_CONTR_MTR_BLLG.
     */
    public static final String NSZM0826E = "NSZM0826E";

    /**
     * The corresponding data does not exist in "DS_CONTR_BLLG_SCHD".
     */
    public static final String NSZM0827E = "NSZM0827E";

    /**
     * Failed to update the DS_CONTR_BLLG_SCHD.
     */
    public static final String NSZM0828E = "NSZM0828E";

    // START 2016/03/10 T.Kanasaka [QC5207, ADD]
    /**
     * The corresponding data does not exist in "SVC_CONTR_ADDL_CHRG_BLLG".
     */
    public static final String NSZM0870E = "NSZM0870E";

    /**
     * Failed to update the SVC_CONTR_ADDL_CHRG_BLLG.
     */
    public static final String NSZM0871E = "NSZM0871E";
    // END 2016/03/10 T.Kanasaka [QC5207, ADD]

    /**
     * MAP_KEY : sumCopiesMade
     */
    public static final String MAP_KEY_SUM_COPIES_MADE = "sumCopiesMade";

    /**
     * MAP_KEY : sumTotalCopy
     */
    public static final String MAP_KEY_SUM_TOTAL_COPY = "sumTotalCopy";

    /**
    * MAP_KEY : sumAllowance
    */
   public static final String MAP_KEY_SUM_ALLOWANCE = "sumAllowance";

   /**
    * MAP_KEY : sumBillableCopy
    */
   public static final String MAP_KEY_SUM_BILLABLE_COPY = "sumBillableCopy";

    // Add Start 05/15/2018 <QC#23541>
    /**
     * MAP_KEY : sumMinAmtRate
     */
    public static final String MAP_KEY_SUM_MIN_AMT_RATE = "sumMinAmtRate";

    /**
     * MAP_KEY : sumMinCnt
     */
    public static final String MAP_KEY_SUM_MIN_CNT = "sumMinCnt";

    /** BigDecimal 100 */
    public static final BigDecimal BIGDECIMAL_100 = BigDecimal.valueOf(100);

    /** Days of Year */
    public static final BigDecimal DAYS_OF_YEAR = BigDecimal.valueOf(365);

    /** CCY Scale */ 
    public static final int ccyScale = 2;

    // Add End  05/15/2018 <QC#23541>

}
