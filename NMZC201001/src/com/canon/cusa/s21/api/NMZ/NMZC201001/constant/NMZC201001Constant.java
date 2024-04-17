/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NMZ.NMZC201001.constant;

/**
 *<pre>
 * Update Supplier API Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/19   CITS            T.Tokutomi      Create          N/A
 * 2016/09/15   CITS            T.Gotoda        UPDATE          QC#13313
 * 2016/10/27   CITS            T.Hakodate      UPDATE          QC#15314 
 * 2017/01/17   CITS            R.Shimamoto     UPDATE          QC#
 * 2018/01/17   CITS            T.Wada          UPDATE          QC#22164
 *</pre>
 */
public class NMZC201001Constant {

    /**
     * XX_MODE : Create
     */
    public static final String CREATE_MODE = "1";

    /**
     * XX_MODE : Update
     */
    public static final String UPDATE_MODE = "2";

    /** NMZC2010 PROCESS MODE 01 Contact Create. */
    public static final String NMZC2010_PROC_MODE_CRAT = "01";

    /** NMZC2010 PROCESS MODE 02 Contact Update. */
    public static final String NMZC2010_PROC_MODE_UPD = "02";

    /**
     * Maximum LOC_NM_LENGTH_60
     */
    public static final int LOC_NM_LENGTH_60 = 60;

    /**
     * Maximum loc_Nm length.
     */
    public static final int MAX_LOC_NM_LENGTH = 15;

    /**
     * Maximum check count of VND_CD
     */
    public static final int MAX_CNT_OF_VND_CD = 3;

    /**
     * Default thru date
     */
    public static final String DEF_THRU_DT = "99991231";

    /**
     * Type code :ALL
     */
    public static final String TP_CD_ALL = "ALL";

    /**
     * AUTO_SQ_EXTN_KEY : PRNT_VND_CD
     */
    public static final String AUTO_SQ_EXTN_KEY_PRNT_VND_CD = "PRNT_VND_CD";

    // 2016/05/11 CITS T.Hakodate UPDATE QC#6943 START
    /**
     * AUTO_SQ_EXTN_KEY : LOC_NUM
     */
    public static final String AUTO_SQ_EXTN_KEY_LOC_NUM = "LOC_NUM";

    // 2016/05/11 CITS T.Hakodate UPDATE QC#6943 END

    /**
     * AUTO_SQ_EXTN_KEY : VND_CD
     */
    public static final String AUTO_SQ_EXTN_KEY_VND_CD = "VND_CD";

    //  QC#14489
    /** Excluded TaxPayer Id */
    public static final String CUSA_EXCL_TAX_PAY_ID = "EXCLUDE_TAXPAYER_ID";

    /**
     * SSM_PRM_GLBL_CMPY_CD : glblCmpyCd
     */
    public static final String SSM_PRM_GLBL_CMPY_CD = "glblCmpyCd";

    /**
     * SSM_PRM_PRNT_VND_CD : prntVndCd
     */
    public static final String SSM_PRM_PRNT_VND_CD = "prntVndCd";

    /**
     * SSM_PRM_PRNT_VND_NM : prntVndNm
     */
    public static final String SSM_PRM_PRNT_VND_NM = "prntVndNm";

    // QC#22164 Add
    /**
     * SSM_PRM_PRNT_VND_NM : prntVndNm
     */
    public static final String SSM_PRNT_VND_TP_CD = "prntVndTpCd";

    /**
     * SSM_PRM_TAX_PAYER_ID : taxPayerId
     */
    public static final String SSM_PRM_TAX_PAYER_ID = "taxPayerId";

    /**
     * SSM_PRM_SHIP_TO_CUST_CD : shipToCustCd
     */
    public static final String SSM_PRM_SHIP_TO_CUST_CD = "shipToCustCd";

    /**
     * SSM_PRM_VND_CD : vndCd
     */
    public static final String SSM_PRM_VND_CD = "vndCd";

    /**
     * SSM_PRM_LOC_NM : locNm
     */
    public static final String SSM_PRM_LOC_NM = "locNm";

    // QC#14489
    /**
     *  SSM_PRM_EXCLUDE_TAXPAYER_ID : excludeTaxPayerId
     */
    public static final String SSM_PRM_EXCLUDE_TAXPAYER_ID = "excludeTaxPayerId";

    /**
     * Input parameter "Global Company Code" is a mandatory field.
     */
    public static final String NMZM0009E = "NMZM0009E";

    /**
     * Process mode is not set.
     */
    public static final String NMZM0054E = "NMZM0054E";

    /**
     * Invalid value is set for process mode.
     */
    public static final String NMZM0055E = "NMZM0055E";

    /**
     * Input Parameter First Line Address is mandatory field.
     */
    public static final String NMZM0035E = "NMZM0035E";

    /**
     * Input Parameter City Address is mandatory field.
     */
    public static final String NMZM0036E = "NMZM0036E";

    /**
     * Input Parameter State Code is mandatory field.
     */
    public static final String NMZM0037E = "NMZM0037E";

    /**
     * Input Parameter Post Code is mandatory field.
     */
    public static final String NMZM0038E = "NMZM0038E";

    /**
     * Failed to register to Party Location Work.
     */
    public static final String NMZM0048E = "NMZM0048E";

    /**
     * Failed to register to Direct Sales Party Location Work.
     */
    public static final String NMZM0094E = "NMZM0094E";

    /**
     *Failed to register to Location Usage.
     */
    public static final String NMZM0051E = "NMZM0051E";

    /**
     * Failed to register to Party.
     */
    public static final String NMZM0049E = "NMZM0049E";

    /**
     * Failed to register to Ship To Customer.
     */
    public static final String NMZM0050E = "NMZM0050E";

    /**
     * Failed to register to Direct Sales Ship To Customer.
     */
    public static final String NMZM0067E = "NMZM0067E";

    /**
     * Input parameter "Process Date" is a mandatory field.s
     */
    public static final String NMZM0095E = "NMZM0095E";

    /**
     * Input parameter "Supplier Number" is a mandatory field.
     */
    public static final String NMZM0096E = "NMZM0096E";

    /**
     * Input parameter "Parent Vendor name" is a mandatory field.
     */
    public static final String NMZM0097E = "NMZM0097E";

    /**
     * Input parameter "Parent Vendor Type Code" is a mandatory field.
     */
    public static final String NMZM0098E = "NMZM0098E";

    /**
     * Process mode is not set.（Supplier Site Interface)
     */
    public static final String NMZM0099E = "NMZM0099E";

    /**
     * Invalid value is set for process mode.（Supplier Site Interface)
     */
    public static final String NMZM0100E = "NMZM0100E";

    /**
     * Input parameter "Site Code" is a mandatory field.
     */
    public static final String NMZM0101E = "NMZM0101E";

    /**
     * Input parameter "Site Name" is a mandatory field.
     */
    public static final String NMZM0102E = "NMZM0102E";

    /**
     * Process mode is not set.（Contact Info Interface)
     */
    public static final String NMZM0103E = "NMZM0103E";

    /**
     * Invalid value is set for process mode.（Contact Info Interface)
     */
    public static final String NMZM0104E = "NMZM0104E";

    /**
     * Input parameter "Contact Person Primary Key" is a mandatory
     * field.
     */
    public static final String NMZM0105E = "NMZM0105E";

    /**
     * Input parameter "Location Number" is a mandatory field.
     */
    public static final String NMZM0106E = "NMZM0106E";

    /**
     * Input parameter "Effective From Date" is a mandatory field.
     */
    public static final String NMZM0107E = "NMZM0107E";

    /**
     * Input parameter "Contact Person First Name" is a mandatory
     * field.
     */
    public static final String NMZM0108E = "NMZM0108E";

    /**
     * Input parameter "Contact Person Last Name" is a mandatory
     * field.
     */
    public static final String NMZM0109E = "NMZM0109E";

    /**
     * Input parameter "Contact type Code" is a mandatory field.
     */
    public static final String NMZM0110E = "NMZM0110E";

    /**
     * Input parameter "Direct Sales Contact Point Type Code" is a
     * mandatory field.
     */
    public static final String NMZM0111E = "NMZM0111E";

    /**
     * Input parameter "Direct Sales Contact Point Value Text" is a
     * mandatory field.
     */
    public static final String NMZM0112E = "NMZM0112E";

    /**
     * Input Parameter "Site Name" to large length.(Maximum 15
     * characters.)
     */
    public static final String NMZM0113E = "NMZM0113E";

    /**
     * Duplicate Supplier.
     */
    public static final String NMZM0114E = "NMZM0114E";

    /**
     * Supplier does not exist.
     */
    public static final String NMZM0115E = "NMZM0115E";

    /**
     * The format of Supplier Inactive On Date (Effective Thru Date)
     * is incorrect.
     */
    public static final String NMZM0116E = "NMZM0116E";

    /**
     * Failed to register to Supplier.
     */
    public static final String NMZM0117E = "NMZM0117E";

    /**
     * Duplicate Ship To Customer.
     */
    public static final String NMZM0118E = "NMZM0118E";

    /**
     * Ship To Customer does not exist.
     */
    public static final String NMZM0119E = "NMZM0119E";

    /**
     * Duplicate Supplier Site.
     */
    public static final String NMZM0120E = "NMZM0120E";

    /**
     * Duplicate Supplier Site.
     */
    public static final String NMZM0121E = "NMZM0121E";

    /**
     * Duplicate Supplier Site Name.
     */
    public static final String NMZM0122E = "NMZM0122E";

    /**
     * Supplier Site Name does not exist.
     */
    public static final String NMZM0123E = "NMZM0123E";

    /**
     * Failed to register to Company.
     */
    public static final String NMZM0124E = "NMZM0124E";

    /**
     * Failed to register to Vendor.
     */
    public static final String NMZM0125E = "NMZM0125E";

    /**
     * Failed to register to Direct Sales Vendor.
     */
    public static final String NMZM0126E = "NMZM0126E";

    /**
     * Failed to register to Vendor Type Relation.
     */
    public static final String NMZM0127E = "NMZM0127E";

    /**
     * Company does not exist.
     */
    public static final String NMZM0128E = "NMZM0128E";

    /**
     * Party Location Work does not exist.
     */
    public static final String NMZM0129E = "NMZM0129E";

    /**
     * Vendor does not exist.
     */
    public static final String NMZM0130E = "NMZM0130E";

    /**
     * Direct Sales Vendor does not exist.
     */
    public static final String NMZM0131E = "NMZM0131E";

    /**
     * Ship To Customer does not exist.
     */
    public static final String NMZM0132E = "NMZM0132E";

    /**
     * Direct Sales Party Location Work does not exist.
     */
    public static final String NMZM0167E = "NMZM0167E";

    /**
     * Failed to remove to Vendor Type Relation.
     */
    public static final String NMZM0333E = "NMZM0333E";

}
