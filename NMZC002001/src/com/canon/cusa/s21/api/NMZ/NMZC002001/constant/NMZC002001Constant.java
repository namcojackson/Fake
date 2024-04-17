/**
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NMZ.NMZC002001.constant;

/**
 *<pre>
 * Customer Update API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/02   Fujitsu         N.Sugiura       Create          N/A
 * 2016/04/08   SRAA            Y.Chen          Update          QC#6551
 * 2016/10/05   Fujitsu         Mz.Takahashi    Update          QC#14431
 * 2017/08/07   Fujitsu         N.Sugiura       Update          QC#8598
 * 2018/02/02   Fujitsu         Hd.Sugawara     Update          QC#23904
 * 2018/06/18   Hitachi         K.Kitachi       Update          QC#18591
 * 2018/12/14   Fujitsu         Hd.Sugawara     Update          QC#24022
 *</pre>
 */
public class NMZC002001Constant {

    /** PROCESS MODE 01 Contact Create. */
    public static final String PROCESS_MODE_CTAC_CRAT = "01";

    /** PROCESS MODE 02 Contact Create. */
    public static final String PROCESS_MODE_CTAC_UPD = "02";

    // START 2018/06/18 K.Kitachi [QC#18591, MOD]
    /** PROCESS MODE 03 Get Contact Person PK. */
    public static final String PROCESS_MODE_GET_CTAC_PSN_PK = "03";
    // END 2018/06/18 K.Kitachi [QC#18591, MOD]

    /**
     * Input parameter "Global Company Code" is a mandatory field.
     */
    public static final String NMZM0009E = "NMZM0009E";

    /**
     * Input parameter "Sales Date" is a mandatory field.
     */
    public static final String NMZM0011E = "NMZM0011E";

    /**
     * Process mode is not set.
     */
    public static final String NMZM0054E = "NMZM0054E";

    /**
     * Invalid value is set for process mode.
     */
    public static final String NMZM0055E = "NMZM0055E";

    /**
     * The effective date of the entered data is incorrect.
     */
    public static final String NMAM0803E = "NMAM0803E";

    /**
     * @ is not active.
     */
    public static final String NMAM8204E = "NMAM8204E";

    /**
     * Email of active state has not been set.
     */
    public static final String NMAM8205E = "NMAM8205E";

    /**
     * @ cannot be registered.
     */
    public static final String NMAM8206E = "NMAM8206E";

    /** Contact Person had already been registered. */
    public static final String NMAM8420E = "NMAM8420E";

    /**
     * Parameter for Contact Point Information is not registered yet.
     */
    public static final String NMAM8430E = "NMAM8430E";

    /**
     * Invalid combination.  Direct Sales Account Number & Location Number.
     */
    public static final String NMAM8466E = "NMAM8466E";

    /**
     * The entered [Account Number] does not exist in [SELL_TO_CUST].
     */
    public static final String NMAM8467E = "NMAM8467E";

    /**
     * The entered [Location Number] does not exist in [PTY_LOC_WRK].
     */
    public static final String NMAM8468E = "NMAM8468E";

    /**
     * The entered [Contact type code] does not exist in [CTAC_TP].
     */
    public static final String NMAM8469E = "NMAM8469E";

    /**
     * The entered [Direct Sales Contact Person Salutation Code] does not exist in [DS_CTAC_PSN_SALT].
     */
    public static final String NMAM8470E = "NMAM8470E";

    /**
     * The entered [Direct Sales Contact Person Department Code] does not exist in [DS_CTAC_PSN_DEPT].
     */
    public static final String NMAM8471E = "NMAM8471E";

    /**
     * The entered [Direct Sales Contact Person Title Code] does not exist in [DS_CTAC_PSN_TTL].
     */
    public static final String NMAM8472E = "NMAM8472E";

    /**
     * Contact Person field is not active.
     */
    public static final String NMAM8473E = "NMAM8473E";

    /**
     * Contact point type field is not active.
     */
    public static final String NMAM8474E = "NMAM8474E";

    /**
     * Primary contact person cannot be registered.
     */
    public static final String NMAM8475E = "NMAM8475E";

    /**
     * Parameter for Contact Person First Name is not registered yet.
     */
    public static final String NMZM0187E = "NMZM0187E";

    /**
     * Parameter for Contact Person Last Name is not registered yet.
     */
    public static final String NMZM0188E = "NMZM0188E";

    // Del Start 2018/02/02 QC#23904
    ///**
    // * Parameter for Contact type Code is not registered yet.
    // */
    //public static final String NMZM0189E = "NMZM0189E";
    // Del End 2018/02/02 QC#23904

    /**
     * Parameter for Contact Person Primary Key is not registered yet.
     */
    public static final String NMZM0190E = "NMZM0190E";

    /**
     * Parameter for Direct Sales Contact Point Type Code is not registered yet.
     */
    public static final String NMZM0191E = "NMZM0191E";

    /**
     * Parameter for Direct Sales Contact Point Value Text is not registered yet.
     */
    public static final String NMZM0192E = "NMZM0192E";

    /**
     * Parameter for Direct Sales Contact Point Primary Key is not registered yet.
     */
    public static final String NMZM0193E = "NMZM0193E";

    /**
     * Parameter for Contact Person First Name is not registered yet.
     */
    public static final String NMZM0227E = "NMZM0227E";

    /**
     * It failed to register to the [CTAC_PSN] table.
     */
    public static final String NMZM0228E = "NMZM0228E";

    /**
     * It failed to register to the [DS_CTAC_PNT] table.
     */
    public static final String NMZM0230E = "NMZM0230E";

    /**
     * The record cannot be updated. Table Name: [DS_CTAC_PSN_RELN]
     */
    public static final String NMZM0253E = "NMZM0253E";

    /**
     * The record cannot be updated. Table Name: [CTAC_PSN]
     */
    public static final String NMZM0254E = "NMZM0254E";

    /**
     * The record cannot be updated. Table Name: [DS_CTAC_PNT]
     */
    public static final String NMZM0255E = "NMZM0255E";

    /**
     * [DS_CTAC_PSN_RELN] for update target is not found.
     */
    public static final String NMZM0307E = "NMZM0307E";

    /**
     * [CTAC_PSN] for update target is not found.
     */
    public static final String NMZM0308E = "NMZM0308E";

    /**
     * [DS_CTAC_PNT] for update target is not found.
     */
    public static final String NMZM0309E = "NMZM0309E";

    /** Default Effective Date */
    public static final String DEFAULT_EFF_DT = "99991231";

    // QC#6551
    /** EMail check Pattern */
    // 2016/10/04 Mz.Takahashi [QC#14431, DEL]
    //public static final String CHK_EMAIL_PATTERN = "^[a-zA-Z0-9]+([\\w\\.\\-]*[\\w\\-])*@([\\w\\-]+\\.)+[\\w\\-]+$";

    /** Email format is incorrect. */
    public static final String NMAM8347E = "NMAM8347E";

    /** Phone number format is incorrect. Minimum length is 7digit, Maximum length is 20 digit. */
    public static final String NMAM8348E = "NMAM8348E";

    // Del Start 2018/12/14 QC#24022
    ///** Extention length has been exceeded. Maximum length is 4digit. */
    //public static final String NMAM8349E = "NMAM8349E";
    // Del End 2018/12/14 QC#24022

    // Add Start 2018/12/14 QC#24022
    /** Extention length has been exceeded. Maximum length is 10 digit. */
    public static final String NMAM8698E = "NMAM8698E";
    // Add End 2018/12/14 QC#24022

    /** Slash */
    public static final String SLASH = "/";

    /** Period */
    public static final String PERIOD = "\\.";

    /** Hyphen */
    public static final String HYPHEN = "-";

    // QC#8598 Add Start
    /** Contact Point Format Text */
    public static final String CTAC_PNT_FMT_US = "US";

    /** Max Effective Thru Date */
    public static final String MAX_EFF_THRU_DT = "99991231";

    /** Please enter telephone number. */
    public static final String NMZM0347E = "NMZM0347E";

    /** Please enter Email address. */
    public static final String NMZM0348E = "NMZM0348E";

    /** Please enter fax number. */
    public static final String NMZM0349E = "NMZM0349E";
    // QC#8598 Add End

    // Add Start 2018/12/14 QC#24022
    /** Max length of extension */
    public static final int EXT_MAX_LENGTH = 10;
    // Add End 2018/12/14 QC#24022
}
