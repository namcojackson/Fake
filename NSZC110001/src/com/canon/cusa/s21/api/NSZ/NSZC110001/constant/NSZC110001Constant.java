/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC110001.constant;

/**
 * <pre>
 * CFS Contract Uplift API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 29/09/2016   Hitachi         Y.Zhang         Create          N/A
 *</pre>
 */
public class NSZC110001Constant {

    /**
     * Input parameter "Global Company Code" is a mandatory field.
     */
    public static final String NSZM0001E = "NSZM0001E";

    /**
     * Input parameter "CFS_CONTR_PRC_UPLFT_PK" is a mandatory field.
     */
    public static final String NSZM1061E = "NSZM1061E";

    /**
     * the target data was processed.
     */
    public static final String NSZM1062E = "NSZM1062E";

    /**
     * Failed to update CFS_CONTR_PRC_UPLFT.
     */
    public static final String NSZM1063E = "NSZM1063E";

    /**
     *  the target data  does not exist in CFS_CONTR_PRC_UPLFT.
     */
    public static final String NSZM1064E = "NSZM1064E";

    /** Max Message Length */
    public static final int MAX_MSG_LEN = 300;
}
