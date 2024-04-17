/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7020.constant;

/**
 *<pre>
 * NMAL7020Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/11   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NMAL7020Constant {

    // --------------------------------
    // Message ID
    // --------------------------------
    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** The entered [@] does not exist. */
    public static final String NMAM0009E = "NMAM0009E";

    /** The data @ does not exist in the master. */
    public static final String NMAM8186E = "NMAM8186E";

    /** @ is duplicated. */
    public static final String NMAM0072E = "NMAM0072E";

    /** The value for [@] must be equal to or later than [@] */
    public static final String NMAM8061E = "NMAM8061E";

    /** It failed to register [@]. */
    public static final String NMAM0176E = "NMAM0176E";

    /**
     * Apply Price Adjustment can not be applied 
     * when From Price List Structure Type is Equipment.
     */
    public static final String NMAM8447E = "NMAM8447E";

    /** VarCharConstNmMemo */
    public static final String VAR_CHAR_CONST_NM_MEMO = "NMAL7020_MEMO";

    /** VarCharConstNmMemoLine2 */
    public static final String VAR_CHAR_CONST_NM_MEMO_LINE2 = "NMAL7020_MEMO_2";

    /** GlobalCompyCode */
    public static final String GLOBAL_CMPY_CODE = "glblCmpyCd";

    /** PrcCatgNm */
    public static final String PRC_CATG_NM = "prcCatgNm";

    /** PriceListTableNm */
    public static final String PRICE_LIST_TABLE_NM = "priceListTableNm";

    /** PrcListTable */
    public static final String PRC_LIST_TABLE_EQUIP = "PRC_LIST_EQUIP";

    /** PrcListTable */
    public static final String PRC_LIST_TABLE_SVC = "PRC_LIST_SVC";

    /** PrcListTable */
    public static final String PRC_LIST_TABLE_SVC_TIER = "PRC_LIST_SVC_TIER";

    /** PrcListTable */
    public static final String PRC_LIST_TABLE_ADDL_CHRG = "PRC_LIST_ADDL_CHRG";

    /** PrcListTable */
    public static final String PRC_LIST_TABLE_SPLY = "PRC_LIST_SPLY_PGM";

    /** PrcListTable */
    public static final String PRC_LIST_TABLE_LEASE_RATE = "PRC_LIST_LEASE_RATE";

    /** PrcListTable */
    public static final String PRC_LIST_TABLE_LEASE_RTRN = "PRC_LIST_LEASE_RTRN";

    /** PrcListTable */
    public static final String PRC_LIST_TABLE_TI_VAL = "PRC_LIST_TI_VAL";

    /** PrcListEquipSts */
    public static final String PRC_LIST_STS_EQUIP = "01";

    /** PrcListSts */
    public static final String PRC_LIST_STS_SERVICE = "02";

    /** PrcListSts */
    public static final String PRC_LIST_STS_SERVICE_TIERS = "03";

    /** PrcListSts */
    public static final String PRC_LIST_STS_SERVICE_SPECIAL = "04";

    /** PrcListSts */
    public static final String PRC_LIST_STS_EQUIP_SUPPLY = "05";

    /** PrcListSts */
    public static final String PRC_LIST_STS_EQUIP_LEASE_RATES = "06";

    /** PrcListSts */
    public static final String PRC_LIST_STS_EQUIP_LEASE_RETURN = "07";

    /** PrcListSts */
    public static final String PRC_LIST_STS_EQUIP_TRADE = "08";

}
