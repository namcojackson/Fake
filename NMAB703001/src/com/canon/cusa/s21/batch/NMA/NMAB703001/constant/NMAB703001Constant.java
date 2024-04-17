/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB703001.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * Price List Sync Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/28   Fujitsu         M.suzuki          Create          N/A
 * 2016/05/23   Fujitsu         M.suzuki          Update          QC#7769
 *</pre>
 */
public class NMAB703001Constant {

    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** [@] is invalid value */
    public static final String ZZZM9026E = "ZZZM9026E";

    /** It failed to update [@]. */
    public static final String NMAM0177E = "NMAM0177E";

    /** The value for [@] must be equal to or later than [@] */
    public static final String NMAM8061E = "NMAM8061E";

    /** @ is duplicated. */
    public static final String NMAM0072E = "NMAM0072E";

    /** It failed to register [@]. */
    public static final String NMAM0176E = "NMAM0176E";

    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    /** [@] is overflowed. */
    public static final String NMAM8464E = "NMAM8464E";

    /** [@] is a negative value. */
    public static final String NMAM8465E = "NMAM8465E";

    /** The process ended abnormally. */
    public static final String NMAM8020E = "NMAM8020E";

    /** DEFAULT_COMMIT_UNIT */
    public static final int DEFAULT_COMMIT_UNIT = 1000;

    /** DEFAULT_FETCH_SIZE */
    public static final int DEFAULT_FETCH_SIZE = 1000;

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
    //2016/05/23 QC#7769------ start
    /** PrcListEquipSts */
    //public static final String PRC_LIST_STS_EQUIP = "01";

    /** PrcListSts */
    //public static final String PRC_LIST_STS_SERVICE = "02";

    /** PrcListSts */
    //public static final String PRC_LIST_STS_SERVICE_TIERS = "03";

    /** PrcListSts */
    //public static final String PRC_LIST_STS_SERVICE_SPECIAL = "04";

    /** PrcListSts */
    //public static final String PRC_LIST_STS_EQUIP_SUPPLY = "05";

    /** PrcListSts */
    //public static final String PRC_LIST_STS_EQUIP_LEASE_RATES = "06";

    /** PrcListSts */
    //public static final String PRC_LIST_STS_EQUIP_LEASE_RETURN = "07";

    /** PrcListSts */
    //public static final String PRC_LIST_STS_EQUIP_TRADE = "08";
    //2016/05/23 QC#7769------ End

    /** globalCmpyCd01 */
    public static final String GLOBAL_CMPY_CD_01 = "glblCmpyCd01";

    /** Global Company Code */
    public static final String GLOBAL_CMPY_CODE = "glblCmpyCd";

    /** prcCatgCd01 */
    public static final String PRC_CATG_CD_01 = "prcCatgCd01";

    /** prcCatgCd */
    public static final String PRC_CATG_CD = "prcCatgCd";

    /** copyRsltTpCd01 */
    public static final String COPY_RSLT_TP_CD_01 = "copyRsltTpCd01";

    /** prcCatgNm */
    public static final String PRC_CATG_NM = "prcCatgNm";

    /** prcListEquipDtlPk */
    public static final String PRC_LIST_EQUIP_PK = "prcListEquipPk";

    /** BigDecimal MAX_SORT_NUM:999 */
    public static final BigDecimal MAX_SORT_NUM = new BigDecimal("999");

    /** Price List ID Extension Key */
    public static final String EXTN_KEY = "PRC_CTG_CD";

    /** MAX_AMT VALUE */
    public static final BigDecimal MAX_AMT = new BigDecimal("10000000000000");

    /** Message Param */
    public static final String MESSAGE_PARAM_CALC = "Apply Price Adjustment";

    /** effFromDate */
    public static final String EFF_FROM_DT = "effFromDt";

    /** effThruDate */
    public static final String EFF_THRU_DT = "effThruDt";

    /** Row Num */
    public static final String ROW_NUM = "rowNum";

    /** Ex Count */
    public static final String EX_COUNT = "exCount";

    /** PriceListTableNm **/
    public static final String PRICE_LIST_TABLE_NM = "priceListTableNm";

    /** DelFlag */
    public static final String DELFLG_N = "delFlgN";

    /** CompDtMax */
    public static final String COMPDTMAX = "compDtMax";

    /** CompDtMax Value */
    public static final String COMPDTMAX_VALUE = "99991231";
}
