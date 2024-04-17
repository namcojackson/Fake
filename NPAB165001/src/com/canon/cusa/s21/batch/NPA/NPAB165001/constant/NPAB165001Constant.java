/**
 * <pre>
 * Copyright (c) 2015 Canon USA Inc. All rights reserved.
 * </pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB165001.constant;

/**
 * <pre>
 * Create Open Tech Request Work Data Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/07/2016   Hitachi         T.Iwamoto       Create          NA
 * 2019/04/26   CITS            K.Ogino         Update          QC#31201
 * 2023/10/26   Hitachi         T.Kuroda        Update          QC#61494
 * </pre>
 */
public class NPAB165001Constant {

    /** Global Company Code is required. */
    public static final String NPZM0001E = "NPZM0001E";

    /** No search results found of [@]. */
    public static final String NPAM0020E = "NPAM0020E";

    /** Error Message Text: Sales Date */
    public static final String MSG_TXT_SALES_DATE = "Sales Date";

    /** MAX_COMMIT_NUMBER:1000 */
    public static final int MAX_COMMIT_NUMBER = 1000;

    /** MAX_DATE:99991231 */
    public static final String MAX_DATE = "99991231";

    /** NPAB1650_PRCH_HDR */
    public static final String NPAB1650_PRCH_HDR = "NPAB1650_PRCH_HDR";

    /** NPAB1650_PRCH_LINE */
    public static final String NPAB1650_PRCH_LINE = "NPAB1650_PRCH_LINE";

    /** NPAB1650_PO_HDR */
    public static final String NPAB1650_PO_HDR = "NPAB1650_PO_HDR";

    /** NPAB1650_PO_LINE */
    public static final String NPAB1650_PO_LINE = "NPAB1650_PO_LINE";

    /** VAR_CHAR_POD_UPD_STS_CD */
    public static final String VAR_CHAR_POD_UPD_STS_CD = "POD_UPD_STS_CD";

    // START 2023/10/26 T.Kuroda [QC#61494, ADD]
    /** NPAB1650_NPAL1260_TARGET_POD */
    public static final String NPAB1650_NPAL1260_TARGET_POD = "NPAB1650_NPAL1260_TARGET_POD";

    /** PREMIUM_RUSH_CONDITION_TPL_CARR_NM */
    public static final String PREMIUM_RUSH_CONDITION_TPL_CARR_NM = "MNX%";

    /** RUSH_CONDITION_TPL_CARR_NM_FEDEX */
    public static final String RUSH_CONDITION_TPL_CARR_NM_FEDEX = "FEDEX";

    /** RUSH_CONDITION_TPL_CARR_NM_UPS */
    public static final String RUSH_CONDITION_TPL_CARR_NM_UPS = "UPS";
    // END 2023/10/26 T.Kuroda [QC#61494, ADD]

}
