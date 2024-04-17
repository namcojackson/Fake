/**
 *<pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 *</pre>
 */
package business.blap.NLCL1000.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * Stock Over View Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2012/07/17   Fujitsu         Y.Mori          Create          N/A
 * 2016/05/20   CSAI            K.Lee           Update          QC#7441
 *</pre>
 */
public interface NLCL1000Constant {
    /** Search Condition (%)*/
    String STR_SEARCH_PARAM_LIKE = "%";

    /** Column Check Result Code (Error) */
    int ERROR_CD_ERR = 1;

    /** WH/Tech check box (WH is checked) */
    String XX_CHK_BOX_WH_WH = "1";

    /** WH/Tech check box (Tech is checked) */
    String XX_CHK_BOX_WH_TECH = "2";

    /** WH/Tech check box (WH and Tech are checked) */
    String XX_CHK_BOX_WH_BOTH = "3";

    /** Internal/External radio button (Internal is selected) */
    BigDecimal XX_RADIO_BTN_IX_INTERNAL = BigDecimal.ONE;

    /** Internal/External radio button (External is selected) */
    BigDecimal XX_RADIO_BTN_IX_EXTERNAL = new BigDecimal("2");

    /** Internal/External radio button (Internal and External is selected) */
    BigDecimal XX_RADIO_BTN_IX_BOTH = new BigDecimal("3");

    /** Internal External Flag : Internal */
    String INTERNAL_EXTERNAL_FLAG_INTERNAL = "1";

    /** Internal External Flag : External */
    String INTERNAL_EXTERNAL_FLAG_EXTERNAL = "2";

    /** Internal External Flag : Internal and External */
    String INTERNAL_EXTERNAL_FLAG_BOTH = "3";

    /** Date Format */
    String DATE_FMT_PROC_DT = "yyyyMMdd";

    /** INVTY_OWNR_CD : CSA */
    String INVTY_OWNR_CD_CSA = "CSA";

    /** Availability (Yes) */
    String AVAILABILITY_YES = "YES";

    /** Availability (No) */
    String AVAILABILITY_NO = "NO";

    /** Global Company Code CUSA */
    String GLBL_CMPY_CD_CUSA = "ABR";
    // ---------------------------------------
    // Messages
    // ---------------------------------------
    /** The entered Merchandise Code does not exist in Master. */
    String NLCM0108E = "NLCM0108E";

    /** Search results exceeded 200.  Please modify search criteria. */
    String NLCM0001W = "NLCM0001W";

    /** No data found with this search criteria. */
    String NLCM0002I = "NLCM0002I";

    /** The search ended normally. */
    String ZZM8002I = "ZZM8002I";

    // ---------------------------------------
    // EVENT Name
    // ---------------------------------------
    /** Event (INIT) */
    String EVENT_NM_NLCL1000_INIT = "NLCL1000_INIT";

    /** Event (Search) */
    String EVENT_NM_NLCL1000_SEARCH = "NLCL1000Scrn00_Search";

    /** Event (PageNext) */
    String EVENT_NM_NLCL1000_PAGE_NEXT = "NLCL1000Scrn00_PageNext";

    /** Event (PagePrev) */
    String EVENT_NM_NLCL1000_PAGE_PREV = "NLCL1000Scrn00_PagePrev";

    /** Event (Clear) */
    String EVENT_NM_NLCL1000_CMN_CLEAR = "NLCL1000Scrn00_CMN_Clear";

    /** Event (Close) */
    String EVENT_NM_NLCL1000_CMN_CLOSE = "NLCL1000Scrn00_CMN_Close";

    // ---------------------------------------
    // SSM Map Binding Name
    // ---------------------------------------
    /** Binding Name : glblCmpyCd */
    String BIND_GLBL_CMPY_CD = "glblCmpyCd";

    /** Binding Name : cusaCmpyCd */
    String BIND_CUSA_CMPY_CD = "cusaCmpyCd";

    /** Binding Name : mdseCd */
    String BIND_MDSE_CD = "mdseCd";

    /** Binding Name : invtyLocCd */
    String BIND_INVTY_LOC_CD = "invtyLocCd";

    /** Binding Name : rgtnStsCd */
    String BIND_RGTN_STS_CD = "rgtnStsCd";

    /** Binding Name : dsWhSrcTp */
    String BIND_DS_WH_SRC_TP = "dsWhSrcTp";

    /** Binding Name : techLocCd */
    String BIND_TECH_LOC_CD = "techLocCd";

    /** Binding Name : ordTakeMdseCd */
    String BIND_ORD_TAKE_MDSE_CD = "ordTakeMdseCd";

    /** Binding Name : cmpyInvtyFlg */
    String BIND_CMPY_INVTY_FLG = "cmpyInvtyFlg";

    // ---------------------------------------
    // DB item
    // ---------------------------------------
    /** DB item : RTL_WH_CD */
    String RTL_WH_CD = "RTL_WH_CD";

    /** DB item : RTL_SWH_CD */
    String RTL_SWH_CD = "RTL_SWH_CD";

    /** DB item : INVTY_LOC_NM */
    String INVTY_LOC_NM = "INVTY_LOC_NM";
    
    // ---------------------------------------
    // Varchar Const
    // ---------------------------------------
    String VAR_CHAR_COSNT_KEY_NLCL1000_DROP_SHIP_RTL_WH_CD = "NLCL1000_DROP_SHIP_RTL_WH_CD";

    // ---------------------------------------
    // Default Constant
    // ---------------------------------------
    String DEF_DROP_SHIP_RTL_WH_CD = "DS";

}
