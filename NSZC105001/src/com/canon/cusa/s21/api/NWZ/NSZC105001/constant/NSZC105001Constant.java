/* <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre> */
package com.canon.cusa.s21.api.NWZ.NSZC105001.constant;

/**
 * <pre>
 * NWZC1650 Order Header Workflow Rejection And Approval API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2019/02/04   Fujitsu         S.Takami        Create          CSA QC#28799
 * 2022/01/27   Hitachi         R.Onozuka       Update          CSA QC#56182
 * 2022/11/09   Hitachi         S.Fujita        Update          CSA QC#60406
 *</pre>
 */
public class NSZC105001Constant {
    /** Varchar Const Name: NWZC1650_CLT_DTL_NOTE_TXT  */
    public static final String VAR_CHAR_CONST_NSZC1050_CLT_DTL_NOTE_TXT = "NSZC1050_CLT_DTL_NOTE_TXT";
    // add start 2022/11/09 QC#60406
    /** Varchar Const Name: NSZC1050_APVL_CLT_DTL_NOTE_TXT  */
    public static final String VAR_CHAR_CONST_NSZC1050_APVL_CLT_DTL_NOTE_TXT = "NSZC1050_APVL_CLT_DTL_NOTE_TXT";
    // add end 2022/11/09 QC#60406
    /** Detail Note Text Template  */
    public static final String NSZC105001_DTL_NOTE_TXT_TMPL = "FSR Rejected.%nComment:%s%nFSR#:%s%nCall Amount:%,.2f%nEntered by:%s%nRejected by:%s";
    // add start 2022/11/09 QC#60406
    /** Detail Note Text Template  */
    public static final String NSZC105001_APVL_DTL_NOTE_TXT_TMPL = "FSR Approved.%nComment:%s%nFSR#:%s%nCall Amount:%,.2f%nEntered by:%s%nApproved by:%s";
    // add end 2022/11/09 QC#60406
    // START 2022/1/27 R.Onozuka [QC#56182, ADD]
    /** Format Time stamp **/
    public static final String FORMAT_TS = "yyyyMMddHHmmss";

    /** Date length */
    public static final int LEN_DT = 8;
    // END   2022/1/27 R.Onozuka [QC#56182, ADD]
}
