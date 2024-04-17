/**
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB048001.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * Batch Program Class for Populate CUSA Retail Meter Reads
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/10/2016   CITS            T.Wada          Create          
 * 10/13/2016   Hitachi         N.Arai          Update          QC#12916
 *</pre>
 */
public class NSAB048001Constant {

    /** Business ID */
    public static final String BUSINESS_ID = "NSAB0480";

    /** [@] has no value. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Failed to update "@". */
    public static final String NSAM0031E = "NSAM0031E";

    /** Failed to insert "@". */
    public static final String NSAM0032E = "NSAM0032E";

    /** Failed to delete "@". */
    public static final String NSAM0033E = "NSAM0033E";

    /** "@" does not exist. */
    public static final String NSAM0045E = "NSAM0045E";

    /** message Item: GlobalCompanyCode */
    public static final String MSG_ITEM_GLOBAL_COMPANY_CODE = "Global Company Code";

    /** message Item: Sales Date */
    public static final String MSG_ITEM_SALES_DATE = "Sales Date";

    /** Date time format. */
    public static final String TIME_FORMAT_MM_DD_YYYY_HH_MM = "MM/dd/yyyy hh:mm";

    /** line feed code */
    public static final String LINE_FEED_CODE = "\r\n";

    /** Batch ID */
    public static final String BATCH_ID = "NSAB048001";

    /** Mail Template ID */
    public static final String MAIL_TEMPLATE_ID = "NSAB0480M001";

    /** Mail Group ID(From) */
    public static final String MAIL_FROM_GROUP_ID = "FROM0005";

    /** Mail Group ID(To) */
    public static final String MAIL_TO_GROUP_ID = "NSAB0480";

    /** Mail Bind. */
    public static final String EMAIL_PARAM_BATCH_ID = "batchId";

    /** Mail Bind. */
    public static final String EMAIL_PARAM_DATE = "errDate";

    /** Mail Bind. */
    public static final String EMAIL_PARAM_MSG = "message";

    /** DB COLUMN :GLBL_CMPY_CD **/
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** DB COLUMN :SER_NUM **/
    public static final String SER_NUM = "SER_NUM";

    /** DB COLUMN :MDSE_CD **/
    public static final String MDSE_CD = "MDSE_CD";

    /** DB COLUMN :BLLG_MSTR_REVN_CD **/
    public static final String BLLG_MSTR_REVN_CD = "BLLG_MSTR_REVN_CD";

    /** DB COLUMN :SVC_DLR_CD **/
    public static final String SVC_DLR_CD = "SVC_DLR_CD";

    /** DB COLUMN :MACH_MSTR_PK **/
    public static final String MACH_MSTR_PK = "MACH_MSTR_PK";

    /** DB COLUMN :PHYS_MTR_NUM **/
    public static final String PHYS_MTR_NUM = "PHYS_MTR_NUM";

    /** DB COLUMN :PHYS_MTR_NM **/
    public static final String PHYS_MTR_NM = "PHYS_MTR_NM";

    /** DB COLUMN :PHYS_MTR_READ_PK **/
    public static final String PHYS_MTR_READ_PK = "PHYS_MTR_READ_PK";

    /** DB COLUMN :MTR_DT **/
    public static final String MTR_DT = "MTR_DT";

    /** DB COLUMN :MTR_CNT **/
    public static final String MTR_CNT = "MTR_CNT";

    /** DB COLUMN :MTR_TEST_CNT **/
    public static final String MTR_TEST_CNT = "MTR_TEST_CNT";

    /** DB COLUMN :MRC_CRAT_DT **/
    public static final String MRC_CRAT_DT = "MRC_CRAT_DT";

    /** DB COLUMN :MRC_MOD_DT **/
    public static final String MRC_MOD_DT = "MRC_MOD_DT";

    /** NORMAL **/
    public static final String NORMAL = "N";

    /** SDAYS **/
    public static final int SDAYS = -40;

    /** DEFAULT_FETCH_SIZE */
    public static final String DEFAULT_FETCH_SIZE = "1000";

    /** DEFAULT_PURGE_DAYS */
    public static final BigDecimal DEFAULT_PURGE_DAYS = new BigDecimal("100");

    /** DEFAULT_PURGE_DAYS */
    public static final String CONST_ROSS_INTFC_MTR_DEL_VAL = "ROSS_INTFC_MTR_DEL_VAL";

    /** DB COLUMN :ROSS_INTFC_MTR_READ_PK **/
    public static final String ROSS_INTFC_MTR_READ_PK = "ROSS_INTFC_MTR_READ_PK";

// START 10/13/2016 N.Arai [QC#12916, MOD]
    /** BATCH_PROGRAM_ID(NSAB048001) */
    public static final String PROGRAM_ID = "NSAB048001";

    /** Time Stamp Format */
    public static final String TIME_STAMP_FORMAT = "yyyyMMddHHmmssSSS";

    /** Date Format */
    public static final String DATE_FORMAT = "yyyyMMdd";

    /** DS_BIZ_PROC_LOG */
    public static final String DS_BIZ_PROC_LOG = "DS_BIZ_PROC_LOG";
// END 10/13/2016 N.Arai [QC#12916, MOD]

}
