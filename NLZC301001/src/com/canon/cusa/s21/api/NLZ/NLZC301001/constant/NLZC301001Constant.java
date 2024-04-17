/*
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */

package com.canon.cusa.s21.api.NLZ.NLZC301001.constant;

/**
 *<pre>
 * Warehouse Transfer API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2012/10/22   Hitachi         T.Aoyagi        Create          N/A
 * 2013/02/05   Hitachi         T.Tomita        Update          QC525
 * 2013/03/25   Hitachi         T.Aoyagi        Update          QC847
 * 2013/05/21   Hitachi         T.Aoyagi        Update          QC1224
 * 2015/10/13   CITS            H.Sugawara      Update          CSA Project
 *</pre>
 */
public class NLZC301001Constant {

    /**
     * Ship Inventory Location Code
     */
    public static final String FROM_LOC_CD = "fromLocCd";

    /**
     * Delivery Inventory Location Code
     */
    public static final String TO_LOC_CD = "toLocCd";

    /**
     * Drop Ship Flag
     */
    public static final String DROP_SHIP_FLG = "dropShipFlg";

    /**
     * Ship To Customer Code
     */
    public static final String SHIP_TO_CUST_CD = "shipToCustCd";

    /**
     * Shipping Service Level Code
     */
    public static final String SHPG_SVC_LVL_CD = "shpgSvcLvlCd";

    /**
     * Shipment Order Type
     */
    public static final String SO_TP_CD = "soTpCd";

    /**
     * Request Delivery Date
     */
    public static final String DELY_DT = "delyDt";

    /**
     * FSR Number
     */
    public static final String FSR_NUM = "fsrNum";

    /**
     * Reference Number
     */
    public static final String PRCH_REQ_NUM = "prchReqNum";

    /**
     * Reference Line Number
     */
    public static final String PRCH_REQ_LINE_NUM = "prchReqLineNum";

    /**
     * ASC
     */
    public static final int ASC = 0;

    /**
     * DESC
     */
    public static final int DESC = 1;

    /**
     * Data Company Code is not entered.
     */
    public static final String NLZM0003E = "NLZM0003E";

    /**
     * Inventory Order Create Flag is not set.
     */
    public static final String NLZM2272E = "NLZM2272E";

    /**
     * Sales Date is not set.
     */
    public static final String NLZM2079E = "NLZM2079E";

    /**
     * From Inventory Location Code is not set.
     */
    public static final String NLZM2080E = "NLZM2080E";

    /**
     * To Inventory Location Code is not set.
     */
    public static final String NLZM2081E = "NLZM2081E";

    /**
     * From Location Code is not set.
     */
    public static final String NLZM2285E = "NLZM2285E";

    /**
     * To Location Code is not set.
     */
    public static final String NLZM2286E = "NLZM2286E";

    /**
     * Location Status Code is not entered.
     */
    public static final String NLZM0007E = "NLZM0007E";

    /**
     * Shipping Service Level Code is not set.
     */
    public static final String NLZM2082E = "NLZM2082E";

    /**
     * Merchandise Code is not entered.
     */
    public static final String NLZM0005E = "NLZM0005E";

    /**
     * Order Qty is not set.
     */
    public static final String NLZM2083E = "NLZM2083E";

    /**
     * Stock Status Code is not entered.
     */
    public static final String NLZM0008E = "NLZM0008E";

    /**
     * Ship To Customer Code does not exist in master.
     */
    public static final String NLZM2084E = "NLZM2084E";

    /**
     * Enter either Request Delivery Date or Request Ship Date.
     */
    public static final String NLZM2085E = "NLZM2085E";

    /**
     * Failed to register to DS_INVTY_ORD_DTL.
     */
    public static final String NLZM2086E = "NLZM2086E";

    /**
     * DetailList Max Size
     */
    public static final int DETAILLIST_MAX_SIZE = 100;

    /**
     * FSR Number Length
     */
    public static final int LENGTH_FSR_NUM = 10;

    /**
     * Inventory Order Line Number Length
     */
    public static final int LENGTH_LINE_NUM = 3;

    /**
     * yyyyMMddhhmmssSSS
     */
    public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddhhmmssSSS";

    /**
     * START_NUM
     */
    public static final String START_NUM = "001";

    /**
     * ZERO
     */
    public static final String ZERO = "0";

    /**
     * BLANK
     */
    public static final String BLANK = "";

    /**
     * varchar const code for default shipping service level
     */
    public static final String TECH_TO_WH_DEF_SHPG_SVC_LVL_CD = "TECH_TO_WH_DEF_SHPG_SVC_LVL_CD";

}
