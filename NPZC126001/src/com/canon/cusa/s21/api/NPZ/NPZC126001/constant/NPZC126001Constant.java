/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NPZ.NPZC126001.constant;

/**
 * <pre>
 * Reman Order Update API
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/26/2016   CITS            S.Tanikawa      Create          N/A
 * 01/29/2018   CITS            T.Wada          Update          QC#23072
 * </pre>
 */
public class NPZC126001Constant {

    /**
     * Mode 9:Completion Cancel
     */
    public static final String MODE_CANCEL = "9";

    // QC#23072
    /**
     * Mode 8:Completion 
     */
    public static final String MODE_COMPLETED = "8";

    /**
     * VAR_CHAR_CONST_NAME: RMNF_ORD_STS_W4C
     */
    public static final String VAR_RMNF_ORD_STS_W4C = "RMNF_ORD_STS_W4C";

    // =================================================
    // Message Code
    // =================================================
    /**
     * mandatory check: glblCmpyCd
     */
    public static final String NPZM0179E = "NPZM0179E";

    /**
     * mandatory check: xxModeCd
     */
    public static final String NPZM0219E = "NPZM0219E";

    /**
     * mandatory check: rmnfOrdNum
     */
    public static final String NPZM0220E = "NPZM0220E";

    /**
     * There is no Open Reman Order.
     */
    public static final String NPAM1371E = "NPAM1371E";

    /**
     * Failed to update a Remanufacturing Order record.
     */
    public static final String NPZM0215E = "NPZM0215E";

    /**
     * Failed to Insert a Remanufacturing Process Log record.
     */
    public static final String NPAM1491E = "NPAM1491E";

    /**
     * Sales Date is required.
     */
    public static final String NPZM0033E = "NPZM0033E";

    /**
     * SO Number is required.
     */
    public static final String NPZM0028E = "NPZM0028E";

    /**
     * There is no Shipping Order.
     */
    public static final String NPAM1545E = "NPAM1545E";
}
