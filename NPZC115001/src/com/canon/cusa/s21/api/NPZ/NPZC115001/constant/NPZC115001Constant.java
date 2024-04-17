/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NPZ.NPZC115001.constant;

/**
 *<pre>
 * Update Supplier API Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/08   CITS            T.Gotoda        Create          N/A
 * 2019/01/11   CITS            M.Naito         Update          QC#29694
 *</pre>
 */
public class NPZC115001Constant {

    /** XX_MODE : Entry Mode */
    public static final String ENTRY_MODE = "1";
    /** XX_MODE : Cost Update Mode */
    public static final String COST_UPDATE_MODE = "2";

    /** Cost Round Option; Round up */
    public static final String ROUND_UP = "1";
    /** Cost Round Option; Round */
    public static final String ROUND = "2";

    /** Default thru date */
    public static final String DEF_THRU_DT = "99991231";

    /** Global Company Code is required. */
    public static final String NPZM0001E = "NPZM0001E";
    /** "Mode" must be entered. */
    public static final String NPZM0093E = "NPZM0093E";
    /** "Process Date" has not been configured. */
    public static final String NPZM0059E = "NPZM0059E";
    /** Vendor Code must be entered. */
    public static final String NPZM0079E = "NPZM0079E";
    /** ASL Name is required. */
    public static final String NPZM0236E = "NPZM0236E";
    /** ASL detail is required. */
    public static final String NPZM0237E = "NPZM0237E";
    /** MDSE Code is required. */
    public static final String NPZM0238E = "NPZM0238E";
    /** Unit Price Amount is required. */
    public static final String NPZM0239E = "NPZM0239E";
    /** Base PO Qty cannot be 0 or negative. */
    public static final String NPZM0240E = "NPZM0240E";
    /** Vendor UOM Qty cannot be 0 or negative. */
    public static final String NPZM0241E = "NPZM0241E";
    /** Vendor Incr Ord Qty cannot be 0 or negative. */
    public static final String NPZM0242E = "NPZM0242E";
    /** Vendor Min Ord Qty cannot be 0 or negative. */
    public static final String NPZM0243E = "NPZM0243E";
    /** Supplier Number cannot be obtained. */
    public static final String NPZM0244E = "NPZM0244E";
    /** ASL Header cannot have multiple Supplier Codes. */
    public static final String NPZM0245E = "NPZM0245E";
    /** ASL Qualifier Type Code is required. */
    public static final String NPZM0246E = "NPZM0246E";
    /** ASL Qualifier Reference Code is required. */
    public static final String NPZM0247E = "NPZM0247E";
    /** Supplier Item Number is required. */
    public static final String NPZM0248E = "NPZM0248E";
    /** Effective From Date is required. */
    public static final String NPZM0249E = "NPZM0249E";
    /** Vendor UOM Code is required. */
    public static final String NPZM0250E = "NPZM0250E";
    /** Failed to register to ASL Header. */
    public static final String NPZM0251E = "NPZM0251E";
    /** Failed to register to ASL Detail. */
    public static final String NPZM0252E = "NPZM0252E";
    /** Failed to register to ASL Qualifier Relation. */
    public static final String NPZM0253E = "NPZM0253E";
    /** Failed to register to MDSE Cost Update History Header. */
    public static final String NPZM0254E = "NPZM0254E";
    /** Failed to register to MDSE Cost Update History Detail. */
    public static final String NPZM0255E = "NPZM0255E";
    /** Supplier Item Number cannot be obtained from ASL Table. */
    public static final String NPZM0256W = "NPZM0256W";
    /** There is a discrepancy of Vendor UOM Code. */
    public static final String NPZM0257W = "NPZM0257W";
    // START 2019/01/11 M.Naito [QC#29694,ADD]
    /** PO Price has been changed. */
    public static final String NPZM0309W = "NPZM0309W";
    // END 2019/01/11 M.Naito [QC#29694,ADD]
}
