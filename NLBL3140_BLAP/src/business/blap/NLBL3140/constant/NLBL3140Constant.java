/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLBL3140.constant;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/07/21   Fujitsu         K.Ishizuka      Create          N/A
 * 2017/10/24   CITS            T.Kikuhara      Update          QC#20772
 * </pre>
 */
public class NLBL3140Constant {

    /** Business ID */
    public static final String BIZ_ID = "NLBL3140";

    /** Checkbooks on A Name */
    public static final String CHKBOX_ON_A_NM = "xxChkBox_A";

    /** CheckboxFlg */
    public static final String CHKBOX_ON_Y = "Y";

    /** Shipping Order hold Flag N */
    public static final String SHIP_ORD_HLD_FLAG_N = "N";

    /** Asterisk */
    public static final String ASTERISK = "*";

    /** Set "TO" Description Event for Item Number */
    public static final String SET_DESC_ITEM = "TO";

    /** Set "FROM" Description Event for Supersede */
    public static final String SET_DESC_SUPD = "FROM";

    /** Supersede Description Physical Name */
    public static final String MDSE_DESC_SHORT_TXT_PHYSNM = "MDSE_DESC_SHORT_TXT";

    /** Merchandise Item Type Name Physical Name */
    public static final String MDSE_ITEM_TP_NM_PHYSNM = "MDSE_ITEM_TP_NM";

    /** Merchandise Item Class Type Name Physical Name */
    public static final String MDSE_ITEM_CLS_TP_NM_PHYSNM = "MDSE_ITEM_CLS_TP_NM";

    /** Allocation Type Auto */
    public static final String INVTY_HARD_ALLOC_TP_CD_10 = "10";

    /** Allocation Type Manual */
    public static final String INVTY_HARD_ALLOC_TP_CD_20 = "20";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** No search results found. */
    public static final String NLBM1232I = "NLBM1232I";

    /** This data has been updated by another user. */
    public static final String ZZZM9004E = "ZZZM9004E";

    /** The entered @ has already existed. */
    public static final String NLBM1298W = "NLBM1298W";

    /**
     * Narrow down search criteria. Data exceeded the maximum to
     * display.
     */
    public static final String NLBM0081E = "NLBM0081E";

    /** Has not been registered to the master. */
    public static final String NLBM1120E = "NLBM1120E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** '@ is duplicated. */
    public static final String NLBM1304W = "NLBM1304W";

    /**
     * No record exists in the Code Master. Table Name: @ Field Name: @
     * Search Value: @
     */
    public static final String NLBM1023E = "NLBM1023E";

    /**
     * @ is duplicated.
     */
    public static final String NLBM1265E = "NLBM1265E";

    /** [@] is invalid value */
    public static final String ZZZM9026E = "ZZZM9026E";
    
    /** The target data does not exist. */
    public static final String NLZM2494W = "NLZM2494W";

    // 2017/08/14 QC#20555 ADD BEGIN
    /** '@ is duplicated. */
    public static final String NLAM0240E = "NLAM0240E";

    /** Submit process ended normally. */
    public static final String NLBM0006I = "NLBM0006I";
    // 2017/08/14 QC#20555 ADD END

    // 2017/10/24 QC#20772 ADD START
    /** [@] is successfully processed. Please click [@] to be settled by your request. */
    public static final String NLBM1365I = "NLBM1365I";
    // 2017/10/24 QC#20772 ADD END

}
