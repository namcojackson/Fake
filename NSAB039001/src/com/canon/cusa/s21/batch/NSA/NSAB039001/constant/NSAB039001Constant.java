/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB039001.constant;

/**
 *<pre>
 *  CFS Contract Import
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/23/2016   CITS            T.Kikuhara      Create          N/A
 * 03/28/2017   Hitachi         T.Tomita        Update          QC#18073
 * 04/17/2017   Hitachi         T.Tomita        Update          QC#18247
 * 08/16/2017   Hitachi         T.Tomita        Update          QC#18761
 *</pre>
 */
public class NSAB039001Constant {

    /** [@] has no value. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Failed to update "@". */
    public static final String NSAM0031E = "NSAM0031E";

    /** Failed to insert "@". */
    public static final String NSAM0032E = "NSAM0032E";

    /** Failed to delete "@". */
    public static final String NSAM0033E = "NSAM0033E";

    // del start 2017/04/17 CSA Defect#18247
//    /** [@] is duplicated. */
//    public static final String NSAM0035E = "NSAM0035E";
    // del end 2017/04/17 CSA Defect#18247

    /** "@" does not exist. */
    public static final String NSAM0045E = "NSAM0045E";

    /** message Item: GlobalCompanyCode */
    public static final String MSG_ITEM_GLOBAL_COMPANY_CODE = "Global Company Code";

    /** message Item: Sales Date */
    public static final String MSG_ITEM_SALES_DATE = "Sales Date";

    /** message Item: InterfaceId */
    public static final String MSG_ITEM_INTERFACE_ID = "Interface ID";

    // add start 2017/03/28 CSA Defect#18073
    /** message Item: Transaction ID */
    public static final String MSG_ITEM_TRX_ID = "Transaction ID";
    // del start 2017/04/17 CSA Defect#18247
//    /** message Item: Transaction ID */
//    public static final String MSG_ITEM_SER_NUM = "Serial#";
    // del 0 2017/04/17 CSA Defect#18247
    /** message Item: colon(:) */
    public static final String MSG_ITEM_COLON = ":";
    // add end 2017/03/28 CSA Defect#18073

    /** Batch ID */
    public static final String BATCH_ID = "NSAB039001";

    /** Date time format. */
    public static final String TIME_FORMAT_MM_DD_YYYY_HH_MM = "MM/dd/yyyy hh:mm";

    // del start 2017/03/28 CSA Defect#18073
//    /** line feed code */
//    public static final String LINE_FEED_CODE = "\r\n";
    // del end 2017/03/28 CSA Defect#18073

    /** Mail Template ID */
    public static final String MAIL_TEMPLATE_ID = "NSAB0390M001";

    /** Mail Group ID(From) */
    public static final String MAIL_FROM_GROUP_ID = "FROM0005";

    /** Mail Group ID(To) */
    public static final String MAIL_TO_GROUP_ID = "NSAB0390";

    /** Mail Bind. */
    public static final String EMAIL_PARAM_BATCH_ID = "batchId";

    /** Mail Bind. */
    public static final String EMAIL_PARAM_DATE = "errDate";

    /** Mail Bind. */
    public static final String EMAIL_PARAM_MSG = "message";

    /** . */
    public static final String CFS_EQUIP_CD_10 = "10";
    /** . */
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";
    /** . */
    public static final String INTERFACE_ID = "INTERFACE_ID";
    /** . */
    public static final String TRANSACTION_ID = "TRANSACTION_ID";
    /** . */
    public static final String ENBL_FLG = "ENBL_FLG";
    /** . */
    public static final String CFS_LEASE_CONTR_PK = "CFS_LEASE_CONTR_PK";
    /** . */
    public static final String CFS_LEASE_NUM = "CFS_LEASE_NUM";
    /** . */
    public static final String CSA_CONTR_NUM = "CSA_CONTR_NUM";
    /** . */
    public static final String SER_NUM = "SER_NUM";
    /** . */
    public static final String CFS_FLEET_FLG = "CFS_FLEET_FLG";
    /** . */
    public static final String END_CUST_NM = "END_CUST_NM";
    /** . */
    public static final String LEASE_DLR_CD = "LEASE_DLR_CD";
    /** . */
    public static final String BASE_BILL_FLG = "BASE_BILL_FLG";
    /** . */
    public static final String USG_BILL_FLG = "USG_BILL_FLG";
    /** . */
    public static final String USG_TP_BILL_CD = "USG_TP_BILL_CD";
    /** . */
    public static final String BASE_BLLG_CYCLE_CD = "BASE_BLLG_CYCLE_CD";
    /** . */
    public static final String USG_BLLG_CYCLE_CD = "USG_BLLG_CYCLE_CD";
    /** . */
    public static final String USG_TRMN_DT = "USG_TRMN_DT";
    /** . */
    public static final String BASE_PER_CYCLE_DEAL_AMT = "BASE_PER_CYCLE_DEAL_AMT";
    // Mod Start 2017/08/16 QC#187611
    /** . */
    public static final String FIRST_XS_MTR_COPY_DPLY_QTY = "FIRST_XS_MTR_COPY_DPLY_QTY";
    // Mod End 2017/08/16 QC#18761
    /** . */
    public static final String FIRST_XS_MTR_RATE = "FIRST_XS_MTR_RATE";
    // Mod Start 2017/08/16 QC#187611
    /** . */
    public static final String SCD_XS_MTR_COPY_DPLY_QTY = "SCD_XS_MTR_COPY_DPLY_QTY";
    // Mod End 2017/08/16 QC#18761
    /** . */
    public static final String SCD_XS_MTR_RATE = "SCD_XS_MTR_RATE";
    // Mod Start 2017/08/16 QC#187611
    /** . */
    public static final String THIRD_XS_MTR_COPY_DPLY_QTY = "THIRD_XS_MTR_COPY_DPLY_QTY";
    // Mod End 2017/08/16 QC#18761
    /** . */
    public static final String THIRD_XS_MTR_RATE = "THIRD_XS_MTR_RATE";
    // Mod Start 2017/08/16 QC#187611
    /** . */
    public static final String FRTH_XS_MTR_COPY_DPLY_QTY = "FRTH_XS_MTR_COPY_DPLY_QTY";
    // Mod End 2017/08/16 QC#18761
    /** . */
    public static final String FRTH_XS_MTR_RATE = "FRTH_XS_MTR_RATE";
    /** . */
    public static final String ASSET_LAST_MOD_DT = "ASSET_LAST_MOD_DT";
    /** . */
    public static final String ASSET_START_DT = "ASSET_START_DT";
    /** . */
    public static final String ASSET_END_DT = "ASSET_END_DT";
    /** . */
    public static final String ASSET_TRMN_DT = "ASSET_TRMN_DT";
    /** . */
    public static final String HDR_START_DT = "HDR_START_DT";
    /** . */
    public static final String HDR_END_DT = "HDR_END_DT";
    /** . */
    public static final String HDR_TRMN_DT = "HDR_TRMN_DT";
    /** . */
    public static final String CFS_AGGR_CD = "CFS_AGGR_CD";
    /** . */
    public static final String CFS_LEGAL_FLG = "CFS_LEGAL_FLG";
    /** . */
    public static final String LAST_READ_MTR_CNT = "LAST_READ_MTR_CNT";
    /** . */
    public static final String LAST_TEST_MTR_CNT = "LAST_TEST_MTR_CNT";
    /** . */
    public static final String CFS_EQUIP_CD = "CFS_EQUIP_CD";
    /** . */
    public static final String HDR_BKNG_DT = "HDR_BKNG_DT";
    /** . */
    public static final String CFS_FTR_FLG = "CFS_FTR_FLG";
    /** . */
    public static final String INIT_CPLT_DT = "INIT_CPLT_DT";

}
