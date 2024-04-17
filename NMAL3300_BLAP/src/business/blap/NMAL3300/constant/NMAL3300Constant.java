/**
 * <Pre>
 * 
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
package business.blap.NMAL3300.constant;

/**
 *<pre>
 *  Account Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/07   Fujitsu         N.Sugiura       Create          N/A
 * 2016/02/25   Fujitsu         H.Ikeda         Create          QC:2823
 * 2018/11/12   Fujitsu         Hd.Sugawara     Update          QC#28683
 *</pre>
 */
public class NMAL3300Constant {

    /** CUST_SPCL_INSTN_CTX_TP **/
    // Del Start 2018/11/12 QC#28683
    ///** Transaction Type **/
    //public static final String CUST_SPCL_INSTN_CTX_TP_TRX_TP = "01";
    // Del End 2018/11/12 QC#28683
    /** Business Area **/
    public static final String CUST_SPCL_INSTN_CTX_TP_BIZ_AREA = "02";
    // Del Start 2018/11/12 QC#28683
    ///** Transaction Driver Section **/
    //public static final String CUST_SPCL_INSTN_CTX_TP_TRANSACTION = "03";
    ///** Customer Reference Section **/
    //public static final String CUST_SPCL_INSTN_CTX_TP_REFERENCE = "04";
    // Del End 2018/11/12 QC#28683
    /** Special Instruction Section **/
    public static final String CUST_SPCL_INSTN_CTX_TP_INSTRUCTION = "05";
    // Del Start 2018/11/12 QC#28683
    ///** Special Handling Section **/
    //public static final String CUST_SPCL_INSTN_CTX_TP_HANDLING = "06";
    ///** TABLE_A **/
    //public static final String TABLE_A = "A";
    ///** TABLE_A **/
    //public static final String TABLE_B = "B";
    ///** TABLE_C **/
    //public static final String TABLE_C = "C";
    // Del End 2018/11/12 QC#28683
    /** TABLE_D **/
    public static final String TABLE_D = "D";
    /** Attachment Business Application ID - Account Level **/
    public static final String ATT_BIZ_APL_ID_ACCT = "NMAL6700";
    /** Attachment Business Application ID - Location Level **/
    public static final String ATT_BIZ_APL_ID_LOC = "NMAL6720";
    // 2016/2/25 QC#2823 ADD Start
    // Del Start 2018/11/12 QC#28683
    ///** NOT_SORT_NUM **/
    //public static final BigDecimal NOT_SORT_NUM = BigDecimal.ONE;
    // Del End 2018/11/12 QC#28683
    /** SELECT_CNT_SORT_NUM **/
    public static final int SELECT_CNT_SORT_NUM = 1;
    // 2016/2/25 QC#2823 ADD End

}
