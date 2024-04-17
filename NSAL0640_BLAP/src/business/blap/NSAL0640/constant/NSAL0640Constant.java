/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0640.constant;

/**
 *<pre>
 * Update Contract Branch/Representative
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/06   Hitachi         T.Tsuchida      Create          N/A
 * 2016/03/28   Hitachi         M.Gotou         Update          QC#4702,4703,4915
 * 2016/04/01   Hitachi         M.Gotou         Update          QC#4916
 * 2016/04/14   Hitachi         M.Gotou         Update          QC#4703
 * 2017/02/27   Hitachi         K.Ochiai        Update          QC#4210
 *</pre>
 */
public final class NSAL0640Constant {

    /** BUSINESS_ID */
    public static final String BUSINESS_ID = "NSAL0640";

    /** Divide String  */
    public static final String DIV_STR = "-";

    /** DS_CONTR */
    public static final String DS_CONTR = "Direct Sales Contract";

    /** SVC_MEMO */
    public static final String SVC_MEMO = "Service Memo";

    /** SVC_CONTR_BR */
    public static final String SVC_CONTR_BR = "Service Contract Branch";

 // add start 2016/04/01 CSA Defect#4916
    /** LOB_OR_BR */
    public static final String LOB_OR_BR = "LOB or Branch";

    /** Rep Name */
    public static final String REP_NAME = "Rep Name";

    /** Date Format: Default Effective Through Date */
    public static final String DEF_EFF_THRU_DT = "29991231";

// add end 2016/04/01 CSA Defect#4916

    /** TOC */
    public static final String TOC = "Transaction Organization Code";

// add start 2016/03/28 CSA Defect#4702,4703,4915
    /** S21_PSN */
    public static final String S21_PSN = "S21 Person Code";
// add end 2016/03/28 CSA Defect#4702,4703,4915

    /** DATE_FORMAT */
    public static final String DATE_FORMAT = "yyyyMMddHHmmssSSS";

    /** XX_CHK_BOX_A1 */
    public static final String XX_CHK_BOX_A1 = "xxChkBox_A1";

    /** No search results found. */
    public static final String NSAM0013E = "NSAM0013E";

    /** Please select at least 1 check box. */
    public static final String NSAM0015E = "NSAM0015E";

    /** Failed to update "@". */
    public static final String NSAM0031E = "NSAM0031E";

    /** Failed to insert "@". */
    public static final String NSAM0032E = "NSAM0032E";

    /** @ does not exist. */
    public static final String NSAM0045E = "NSAM0045E";

    /** Your request cannot be processed under this status. */
    public static final String NSAM0065E = "NSAM0065E";

    /** Relation between "@" and "@" is not correct. */
    public static final String NSAM0138E = "NSAM0138E";

    /** The process has been successfully completed. */
    public static final String NSAM0200I = "NSAM0200I";

    /** Input parameter "@" is a mandatory field. */
    public static final String NSAM0362E = "NSAM0362E";

    /** The process has been error in some data. Please check the data. */
    public static final String NSAM0394W = "NSAM0394W";

    /** This data has been updated by another user. */
    public static final String ZZZM9004E = "ZZZM9004E";

    // add start 2016/04/04 CSA Defect#4916
    /**
     * Relationships are incorrect among LOB, Branch and Rep. If OK, please click the "Save" button.
     */
    public static final String NSAM0419W = "NSAM0419W";

    // add end 2016/04/04 CSA Defect#4916

    // add start 2016/04/14 CSA Defect#4703
    /** New Branch Rep. is missing. */
    public static final String NSAM0453E = "NSAM0453E";
    // add end 2016/04/14 CSA Defect#4703
}
