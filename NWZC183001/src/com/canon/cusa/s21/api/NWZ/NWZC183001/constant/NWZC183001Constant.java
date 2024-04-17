/* <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre> */
package com.canon.cusa.s21.api.NWZ.NWZC183001.constant;

/**
 * <pre>
 * NWZC1830 D&I / Site Survey / Contact API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/25   Fujitsu         C.Yokoi         Create          N/A
 * 2016/09/28   Fujitsu         Y.Kanefusa      Update          S21_NA#14593
 * 2018/01/10   Fujitsu         K.Ishizuka      Update          S21_NA#18460(Sol#087)
 *</pre>
 */

public class NWZC183001Constant {
    /** "Global Company Code" is not set. */
    public static final String NWZM0473E = "NWZM0473E";

    /** The parameter's "Order Number" is not set. */
    public static final String NWZM0912E = "NWZM0912E";

    /** Mode is required. */
    public static final String NWZM0012E = "NWZM0012E";

    /** Specified value for Mode is invalid. */
    public static final String NWZM0013E = "NWZM0013E";

    /** "Contact Person PK" is required. */
    public static final String NWZM1346E = "NWZM1346E";

    /** "Direct Sales Site Survey PK" is required. */
    public static final String NWZM1347E = "NWZM1347E";

    /** "CPO Install Information PK" is required. */
    public static final String NWZM1348E = "NWZM1348E";

    /** Contact Type Code is required. */
    public static final String NWZM1776E = "NWZM1776E";

    /** "CPO Delivery  Information PK" is required. */
    public static final String NWZM1349E = "NWZM1349E";

    /** DS_CPO_DELY_INFO for update target is not found. */
    public static final String NWZM1350E = "NWZM1350E";

    /** DS_CPO_ISTL_INFO for update target is not found. */
    public static final String NWZM1351E = "NWZM1351E";

    /** DS_SITE_SRVY for update target is not found. */
    public static final String NWZM1352E = "NWZM1352E";

    /** DS_CPO_CTAC_PSN for update target is not found. */
    public static final String NWZM1353E = "NWZM1353E";

    /** Failed to insert the DS_CPO_DELY_INFO. */
    public static final String NWZM1356E = "NWZM1356E";

    /** Failed to update the DS_CPO_DELY_INFO. */
    public static final String NWZM1357E = "NWZM1357E";

    /** Failed to delete the DS_CPO_DELY_INFO. */
    public static final String NWZM1358E = "NWZM1358E";

    /** Failed to insert the DS_CPO_INSTL_INFO. */
    public static final String NWZM1359E = "NWZM1359E";

    /** Failed to update the DS_CPO_INSTL_INFO. */
    public static final String NWZM1360E = "NWZM1360E";

    /** Failed to delete the DS_CPO_INSTL_INFO. */
    public static final String NWZM1361E = "NWZM1361E";

    /** Failed to insert the DS_SITE_SRVY. */
    public static final String NWZM1362E = "NWZM1362E";

    /** Failed to update the DS_SITE_SRVY. */
    public static final String NWZM1363E = "NWZM1363E";

    /** Failed to delete the DS_SITE_SRVY. */
    public static final String NWZM1364E = "NWZM1364E";

    /** Failed to insert the DS_CPO_CTAC_PSN. */
    public static final String NWZM1365E = "NWZM1365E";

    /** Failed to update the DS_CPO_CTAC_PSN. */
    public static final String NWZM1366E = "NWZM1366E";

    /** Failed to delete the DS_CPO_CTAC_PSN. */
    public static final String NWZM1367E = "NWZM1367E";

    /** It failed to update the CPO_DTL. */
    public static final String NWZM0081E = "NWZM0081E";

    /** It failed to update the SHPG_PLN. */
    public static final String NWZM0078E = "NWZM0078E";

    /** Failed to update the CPO. */
    public static final String NWZM1368E = "NWZM1368E";

    /** Direct Sales Delivery Type Code does not exist in the Master. */
    public static final String NWZM1369E = "NWZM1369E";

    /** Install Type Code does not exist in the Master. */
    public static final String NWZM1370E = "NWZM1370E";

    /** Install Division Code does not exist in the Master. */
    public static final String NWZM1371E = "NWZM1371E";

    /** Install Technician Code does not exist in the Master. */
    public static final String NWZM1372E = "NWZM1372E";

    /** Contact Type Code does not exist in the Master. */
    public static final String NWZM1373E = "NWZM1373E";

    /** Contact Type Code does not exist in the Master. */
    public static final String NWZM2021E = "NWZM2021E"; // QC#14593 2016/09/28 Add

    /** Failed to update DS_CPO_DTL. */
    public static final String NWZM2022E = "NWZM2022E"; // QC#14593 2016/09/28 Add

    /** Mode (New): */
    public static final String MODE_NEW = "01";

    /** Mode (Modify): */
    public static final String MODE_MOD = "02";

    /** Mode (Delete): */
    public static final String MODE_DEL = "03";

    /** Time Sub String From */
    public static final int TIME_SUBSTRING_FROM = 8; // 2018/01/10 S21_NA#18460(Sol#087) ADD

    /** Time Sub String To */
    public static final int TIME_SUBSTRING_TO = 12; // 2018/01/10 S21_NA#18460(Sol#087) ADD
}
