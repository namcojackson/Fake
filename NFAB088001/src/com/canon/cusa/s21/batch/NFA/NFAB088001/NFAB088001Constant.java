/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFA.NFAB088001;

/**
 * Class name: NFAB088001Constant
 * <dd>The class explanation: Constant variable for NFAB088001.
 * <dd>Remarks:
 * @version 1.0
 * @author N. Sasaki
 */
public interface NFAB088001Constant {

    /** Fixed Value : Error Message Parameter. */
    static final String MSG_PARAM = "Inventory Report: Detail Amount";

    /** Fixed Value : Valid date format */
    static final String YYYYMMDD = "yyyyMMdd";

    /** Fixed Value : Each Bulk Insert Count */
    static final int BULK_INSERT_COUNT = 10000;

    /** Fixed Value : Location Status Code: In transit */
    static final String LOC_STS_CD_IN_TRANSIT = "01";

    /** Fixed Value : Inventory Location Type Code: Warehouse */
    static final String INVTY_LOC_TP_CD_WAREHOUSE = "01";
    
    /** Fixed Value : Inventory Location Type Code: Consignee */
    static final String INVTY_LOC_TP_CD_CONSIGNEE = "05";
    
    /** Fixed Value : Inventory Location Type Code: Vendor */
    static final String INVTY_LOC_TP_CD_VENDOR = "03";
    
    /** Fixed Value : VendorType Code: Kitting */
    static final String VND_TP_CD_KITTING = "09";
    
    /** Fixed Value : Vendor Type Code: Refurbish */
    static final String VND_TP_CD_REFURBISH = "10";

    /** Fixed Value : Asset Code */
    static final String ASSET_CD_F = "F";

    /** Fixed Value : Unknown product code */
    static final String UNKNOWN = "UNKNOWN";

    // ** DB Item Column Name's Fixed Value ** //
    /** DB Item Column Name */
    static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** DB Item Column Name */
    static final String INVTY_SNAP_SHOT_DT = "INVTY_SNAP_SHOT_DT";

    /** DB Item Column Name */
    static final String MDSE_CD = "MDSE_CD";

    /** DB Item Column Name */
    static final String COA_PROD_CD = "COA_PROD_CD";

    /** DB Item Column Name */
    static final String RGTN_STS_CD = "RGTN_STS_CD";

    /** DB Item Column Name */
    static final String INVTY_TOT_QTY = "INVTY_TOT_QTY";

    /** DB Item Column Name */
    static final String THIS_MTH_TOT_STD_COST_AMT = "THIS_MTH_TOT_STD_COST_AMT";

    /** DB Item Column Name */
    static final String DLY_MDSE_SMRY_AMT = "DLY_MDSE_SMRY_AMT";

    /** DB Item Column Name */
    static final String LAST_MTH_TOT_STD_COST_AMT = "LAST_MTH_TOT_STD_COST_AMT";

    /** DB Item Column Name */
    static final String LAST_DLY_MDSE_SMRY_AMT = "LAST_DLY_MDSE_SMRY_AMT";

    /** DB Item Column Name */
    static final String DT_PREV = "DT_PREV";
    
    /** DB Item Column Name */
    static final String COA_ACCT_CD = "COA_ACCT_CD";
}
