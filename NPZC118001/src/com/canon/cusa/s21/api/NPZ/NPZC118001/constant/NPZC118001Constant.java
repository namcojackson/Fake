/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NPZ.NPZC118001.constant;

/**
 * <pre>
 * Tech Receive Parts API.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/11/2015   Hitachi         T.Iwamoto       Create          NA
 * 08/03/2016   Hitachi         A.Kohinata      Update          QC#10805
 * 2019/10/15   Hitachi         K.Fujimoto      Update          QC#53956
 * </pre>
 */
public final class NPZC118001Constant {

    /**
     * The field of [@] is not input.
     */
    public static final String ZZZM9007E = "ZZZM9007E";

    /**
     * [@] is not found.
     */
    public static final String ZZZM9006E = "ZZZM9006E";

    /**
     * [@] is invalid value
     */
    public static final String ZZZM9026E = "ZZZM9026E";

    /**
     * Specified Shipment # is already Received.
     */
    public static final String NPZM0196E = "NPZM0196E";

    /**
     * Specified "Assing Technician" and receiving destination does
     * not match.
     */
    public static final String NPZM0197E = "NPZM0197E";

    /**
     * Specified "Receiving Warehouse" and receiving destination does
     * not match.
     */
    public static final String NPZM0198E = "NPZM0198E";

    /**
     * The process receiving has been successfully completed.
     */
    public static final String NPZM0199I = "NPZM0199I";

    /**
     * Failed to insert the CLICK_TECH_RCV_PRT.
     */
    public static final String NPZM0208E = "NPZM0208E";

    // add start 2016/08/03 CSA Defect#10805
    /**
     * Specified "Receiving Technician" and receiving destination does
     * not match.
     */
    public static final String NPZM0273E = "NPZM0273E";

    /** Specified "Assigned Technician WH" does not exist. */
    public static final String NPZM0274E = "NPZM0274E";
    // add end 2016/08/03 CSA Defect#10805

    /**
     * param: DS_COND_CONST
     */
    public static final String PRAM_CONST = "DS_COND_CONST";

    /**
     * param:Global Company Code
     */
    public static final String PRAM_COMPANY = "Global Company Code";

    /**
     * param:SHIPMENT #
     */
    public static final String PRAM_SO_NUM = "SHIPMENT #";

    /**
     * param:STATUS
     */
    public static final String PRAM_STATUS = "STATUS";

    /**
     * param:RECEIVING TECHNICIAN
     */
    public static final String PRAM_TECH = "RECEIVING TECHNICIAN";

    /**
     * param:RECEIVING WAREHOUSE
     */
    public static final String PRAM_WH = "RECEIVING WAREHOUSE";

    /**
     * param:UPDATED DATE
     */
    public static final String PRAM_UPDATED_DATE = "UPDATED DATE";

    // add start 2016/08/03 CSA Defect#10805
    /** param:CLICK KEY */
    public static final String PRAM_CLICK_KEY = "CLICK KEY";
    // add end 2016/08/03 CSA Defect#10805

    /**
     * CLICK_COMMON
     */
    public static final String CLICK_COMMON = "CLICK_COMMON";

    /**
     * DATE_FORMAT
     */
    public static final String DATE_FORMAT = "DATE_FORMAT";

    /**
     * NPZC1180
     */
    public static final String NPZC1180 = "NPZC1180";

    /**
     * RECEIVE_STATUS
     */
    public static final String RECEIVE_STATUS = "RECEIVE_STATUS";

    /**
     * yyyyMMddHHmmss
     */
    public static final String DATE_TIME_FORMAT = "yyyyMMddHHmmss";

    /**
     * 100
     */
    public static final int MSG_LEN = 100;

    // add start 2016/08/03 CSA Defect#10805
    /** MAX_EFF_THRU_DT */
    public static final String MAX_EFF_THRU_DT = "99991231";

    /** yyyyMMdd */
    public static final String DATE_DT_FORMAT = "yyyyMMdd";

    /** yyyyMMddHHmmssSSS */
    public static final String DATE_TS_FORMAT = "yyyyMMddHHmmssSSS";
    // add end 2016/08/03 CSA Defect#10805

    // START 2019/10/15 K.Fujimoto [QC#53956, ADD]
    public static final String NSZM0299E = "NSZM0299E";
    // END   2019/10/15 K.Fujimoto [QC#53956, ADD]
}
