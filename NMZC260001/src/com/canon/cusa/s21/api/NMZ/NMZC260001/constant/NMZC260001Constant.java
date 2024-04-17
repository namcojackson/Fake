/**
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NMZ.NMZC260001.constant;

/**
 *<pre>
 * NWZC206001:Supersede API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/07/27   Fujitsu         N.Sugiura       Create          Create
 * 2017/10/31   Fujitsu         H.Sugawara      Update          QC#20146(Sol#092)
 * 2020/04/23   CITS            K.Ogino         Update          QC#56638
 *</pre>
 */
public class NMZC260001Constant {

    /**
     * Global Company Code is required.
     */
    public static final String NMZM0001E = "NMZM0001E";

    /**
     * None of the parameter values for 'Ship To' or 'Bill To' are set.
     */
    public static final String NMAM8201E = "NMAM8201E";

    /**
     * The corresponding data does not exist. Table Name : [@], Key Field Name:  [@], Key Value:  [@]
     */
    public static final String NMAM8140W = "NMAM8140W";

    /**
     * Could not get the "@".
     */
    public static final String NMAM8203W = "NMAM8203W";

    // Add Start 2017/10/31 QC#20146(Sol#092)
    /**
     * Ship to Account is not set.
     */
    public static final String NMZM0350E = "NMZM0350E";

    /**
     * FM Dummy Rep does not exist.
     */
    public static final String NMZM0351W = "NMZM0351W";

    /**
     * There are multiple FM Dummy Rep.
     */
    public static final String NMZM0352W = "NMZM0352W";
    // Add End 2017/10/31 QC#20146(Sol#092)

    /**
     * lineBizTpCd
     */
    public static final String LINE_BIZ_TP_CD = "LOB (Line Of Business)";

    /**
     * lineBizRoleTpCd
     */
    public static final String LINE_BIZ_ROLE_TP_CD = "LOB Role";

    /**
     * orgCd
     */
    public static final String ORG_CD = "Territory";

    /**
     * tocCd
     */
    public static final String TOC_CD = "TOC";

    /**
     * psnCd
     */
    public static final String PSN_CD = "Resource";

    // Add Start 2017/10/31 QC#20146(Sol#092)
    /**
     * Mode Code : EMSD
     */
    public static final String XX_MODE_CD_EMSD = "01";
    // Add End 2017/10/31 QC#20146(Sol#092)

    // Add Start QC#56638
    /** Sales Rep is not assigned to the specified Ship-To customer. */
    public static final String NWAM0757W = "NWAM0757W";

    /** Sales Rep is not assigned to the specified Sold-To customer. */
    public static final String NWAM0981W = "NWAM0981W";
    // Add End QC#56638    
}
