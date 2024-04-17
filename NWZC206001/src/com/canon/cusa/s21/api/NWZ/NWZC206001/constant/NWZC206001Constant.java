/**
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC206001.constant;

/**
 *<pre>
 * NWZC206001:Supersede API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/13/2012   Fujitsu         H.Mizutani       Create          N/A
 *</pre>
 */
public interface NWZC206001Constant {

    /** Merchandise Type Code for BOM */
    String MDSE_TP_BOM = "5";


    /** Mode of Getting Supersede List */
    String SUPD_LIST_MODE   = "1";

    /** Mode of Getting Latest Supersede */
    String SUPD_LATEST_MODE = "2";

    /**
     * Global Company Code is required.
     */
    String NWZM0188E = "NWZM0188E";

    /**
     * Sales Date is required.
     */
    String NWZM0346E = "NWZM0346E";

    /**
     * Merchandise Code is required.
     */
    String NWZM0021E = "NWZM0021E";

    /**
     * The entered Merchandise Code does not exist in Master.
     */
    String NWZM0036E = "NWZM0036E";

    /**
     * Merchandise code typed in has no Supersede relationship
     */
    String NWZM0972E = "NWZM0972E";

    /**
     * [@] exists twice in single Supersede relationship.
     */
    String NWZM0973E = "NWZM0973E";

    /**
     * Mode is required.
     */
    String NWZM0012E = "NWZM0012E";

    /**
     * Specified value for Mode is invalid.
     */
    String NWZM0013E = "NWZM0013E";

    /**
     * Available Order Flag is required.
     */
    String NWZM1299E = "NWZM1299E";

    /**
     * Available Purchase Flag is required.
     */
    String NWZM1292E = "NWZM1292E";

    /**
     * Merchandise Code does not exist in Supersede or Merchandise Master.
     */
    String NWZM1293E = "NWZM1293E";

    /**
     * Available Order Flag is invalid value.
     */
    String NWZM1294E = "NWZM1294E";

    /**
     * Available Purchase Flag is invalid value.
     */
    String NWZM1295E = "NWZM1295E";

    /**
     * Duplicated Supersede Items exist.
     */
    String NWZM1296E = "NWZM1296E";

    /**
     * Merchandise Code exists twice in single Supersede relationship.
     */
    String NWZM1297E = "NWZM1297E";
}
