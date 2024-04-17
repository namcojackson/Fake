/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFB.NFBB221001.constant;

/**
 * <pre>
 * Invoice Import For EDI Vendor(TST/Dietzgen)
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/09/06   CITS            K.Kameoka       Create
 * 2023/03/13   Hitachi         E.Watabe        Update          QC#61128
 * 2023/09/25   Hitachi         TZ.Win          Update          QC#61608
</pre>
 */
public class NFBB221001Constant {

    // db column
    /** db column : VND_INV_NUM. */
    public static final String VND_INV_NUM = "VND_INV_NUM";

    /** CM_PROC_STS_CS: N */
    public static final String CM_PROC_STS_CD_N = "N";

    /** psIssRqstFlg: N */
    public static final String PS_ISS_RQST_FLG = "N";

    /** DEFAULT_FETCH_SIZE :1000 */
    public static final int DEFAULT_FETCH_SIZE = 1000;

    /** Global Company Code is required. */
    public static final String NFBM0199E = "NFBM0199E";

    /** The mail template cannot be acquired. <Template ID: [@]> */
    public static final String NFBM0184E = "NFBM0184E";

    /** There is no parameter in [@]. */
    public static final String NFBM0207E = "NFBM0207E";

    /** @ is invalid. */
    public static final String NFBM0041E = "NFBM0041E";

    /** E-mail address <Type: [@], Group: [@], Key: [@]> cannot be obtained. */
    public static final String NFBM0217E = "NFBM0217E";

    /** @ doesn't exist. */
    public static final String NFBM0044E = "NFBM0044E";

    /** Cannot process since the PO status is Saved.[@] */
    public static final String NFBM0218E = "NFBM0218E";

    /** Cannot process since the PO status is Waiting Approval.[@] */
    public static final String NFBM0219E = "NFBM0219E";

    /** The PO has been canceled.[@] */
    public static final String NFBM0220E = "NFBM0220E";

    /** The PO Status is not set.[@] */
    public static final String NFBM0221E = "NFBM0221E";

    /** No PO Ord Dtl data found.[@] */
    public static final String NFBM0222E = "NFBM0222E";

    /** The Mdse Code does not match.[@] */
    public static final String NFBM0223E = "NFBM0223E";

    /** The data @ does not exist in the master. */
    public static final String NFDM0012E = "NFDM0012E";

    /** Table : @  Insert Error. Return Code = @ */
    public static final String NFBM0073E = "NFBM0073E";
    /** Duplicate records exist. [ @ ] */
    public static final String NFBM0181E = "NFBM0181E";

    /** Business Error Mail group id for To. */
    public static final String MAIL_GROUP_ID_TO = "NFBB2210";

    /** Business Error Mail group id for Cc. */
    public static final String MAIL_GROUP_ID_CC = "NFBB2210";

    /** Mail Group Type Key: TO */
    public static final String MAIL_TYPE_TO = "TO";

    /** Mail Group Key: TO */
    public static final String MAIL_KEY_TO = "TO";

    /** Mail Group Type Key: CC */
    public static final String MAIL_TYPE_CC = "CC";

    /** Mail Group Key: CC */
    public static final String MAIL_KEY_CC = "CC";

    /** Mail Group Type Key: From */
    public static final String MAIL_TYPE_FROM = "FROM";

    /** Mail Group Id Key: From */
    public static final String MAIL_GROUP_ID_FROM = "FROM0003";

    /** Mail group id for To. */
    public static final String MAIL_TEMPLATE_ID_M001 = "NFBB2210M001";

    /** Mail String Field : TIMESTAMP */
    public static final String MAIL_FIELD_TIMESTAMP = "TIMESTAMP";

    /** Mail String Field : MESSAGE */
    public static final String MAIL_FIELD_MESSAGE = "MAIL_MESSAGE";

    /** Mail Timestamp Format */
    public static final String MAIL_TS_FMT = "MM/dd/yyyy HH:mm:ss.SSS";

    /** Mail Date Format Substr Length */
    public static final int MAIL_LEN_FMT_SUBSTR = 10;

    /** Error Massage: ITEM ERROR */
    public static final String ERR_MSG_ITEM = "ITEM ERROR";

    /** Error Massage: CRLF */
    public static final String ERR_MSG_CRLF = "\r\n";

    /** STRING_LENGTH_2 */
    public static final int STRING_LENGTH_2 = 2;

    /** STRING_LENGTH_4 */
    public static final int STRING_LENGTH_4 = 4;

    /** STRING_LENGTH_5 */
    public static final int STRING_LENGTH_5 = 5;

    /** STRING_LENGTH_15 */
    public static final int STRING_LENGTH_15 = 15;

    /** STRING_LENGTH_30 */
    public static final int STRING_LENGTH_30 = 30;

    /** STRING_LENGTH_100 */
    public static final int STRING_LENGTH_100 = 100;

    /** Error Message Text: hyphen */
    public static final String MSG_TXT_HYPHEN = "-";

    /** Error Message Text: delimiter */
    public static final String MSG_TXT_DELIMITER = ",";

    /** Error Message Text: equals */
    public static final String MSG_TXT_EQUALS = "=";

    /** Error Message Text: space */
    public static final String MSG_TXT_SPACE = " ";

    /** Error Message Text: EDI PO # */
    public static final String MSG_TXT_EDI_PO_NUM = "EDI PO#";

    /** Error Message Text: EDI LINE# */
    public static final String MSG_TXT_EDI_LINE_NUM = "EDI LINE#";

    /** Error Message Text: PO# */
    public static final String MSG_TXT_PO_NUM = "PO#";

    /** Error Message Text: LINE# */
    public static final String MSG_TXT_LINE_NUM = "PO LINE#";

    /** Error Message Text: MdseCD */
    public static final String MSG_TXT_PO_DTL_MDSE_CD = "PO Detail Mdse Cd";

    /** EDI CODE */
    public static final String EDI_INTFC_ITEM_LINE_TP_CD_FREIGHT = "FREIGHT";

    /** EDI CODE*/
    public static final String EDI_INTFC_ITEM_LINE_TP_CD_ITEM= "ITEM";

    /**VAR_CHAR_CONST_NM :NFBB2210_VND_INV_CCY_CD*/
    public static final String VAR_CHAR_CONST_NM_CCY = "NFBB2210_VND_INV_CCY_CD";
    
    /** SSM set Key Name: KEY_VND_CD_DIETZGEN */
    public static final String KEY_VND_CD_DIETZGEN = "vndCdDietzgen";
    
    /** SSM set Key Name: KEY_VND_CD_TST */
    public static final String KEY_VND_CD_TST = "vndCdTst";
    
    /** SSM set Key Name: KEY_INTERFACE_ID_TST */
    public static final String KEY_INTERFACE_ID_TST = "NFBI0430";
    
    /** SSM set Key Name: KEY_INTERFACE_ID_DIETZGEN */
    public static final String KEY_INTERFACE_ID_DIETZGEN = "NFBI0530";
    
    // START 2023/03/13 E.Watabe [QC#61128,ADD]
    /** SSM set Key Name: KEY_INTERFACE_ID_HYTEC */
    public static final String KEY_INTERFACE_ID_HYTEC = "NFBI0630";
    // END 2023/03/13 E.Watabe [QC#61128,ADD]
    
    // START 2023/09/25 TZ.Win [QC#61608,ADD]
    /** SSM set Key Name: KEY_INTERFACE_ID_ATRIX */
    public static final String KEY_INTERFACE_ID_ATRIX = "NFBI0830";
    // END 2023/09/25 TZ.Win [QC#61608,ADD]
    
    /** SSM set Key Name: GLBL_CMPY_CD */
    public static final String KEY_GLBL_CMPY_CD = "glblCmpyCd";

    /** SSM set Key Name: KEY_VND_SYS_TP_CD */
    public static final String KEY_VND_SYS_TP_CD = "vndSysTpCd";

    /** message id : NPAM1537E */
    public static final String NPAM1537E = "NPAM1537E";

    /** Comma */
    public static final String COMMA = ",";

    /**VAR_CHAR_CONST_NM :BILL_TO_NM_ESS*/
    public static final String BILL_TO_NM_ESS = "BILL_TO_NM_ESS";
    
    /**VAR_CHAR_CONST_NM :BILL_TO_ATTN_NM_ESS*/
    public static final String BILL_TO_ATTN_NM_ESS = "BILL_TO_ATTN_NM_ESS";
    
    /**VAR_CHAR_CONST_NM :BILL_TO_ADDR1_ESS*/
    public static final String BILL_TO_ADDR1_ESS = "BILL_TO_ADDR1_ESS";
    
    /**VAR_CHAR_CONST_NM :BILL_TO_ADDR2_ESS*/
    public static final String BILL_TO_ADDR2_ESS = "BILL_TO_ADDR2_ESS";
    
    /**VAR_CHAR_CONST_NM :BILL_TO_ADDR3_ESS*/
    public static final String BILL_TO_ADDR3_ESS = "BILL_TO_ADDR3_ESS";
    
    /**VAR_CHAR_CONST_NM :BILL_TO_ADDR4_ESS*/
    public static final String BILL_TO_ADDR4_ESS = "BILL_TO_ADDR4_ESS";
    
    /**VAR_CHAR_CONST_NM :BILL_TO_ADDR5_ESS*/
    public static final String BILL_TO_ADDR5_ESS = "BILL_TO_ADDR5_ESS";
    
    /**VAR_CHAR_CONST_NM :BILL_TO_ADDR6_ESS*/
    public static final String BILL_TO_ADDR6_ESS = "BILL_TO_ADDR6_ESS";
    
    /**VAR_CHAR_CONST_NM :BILL_TO_NM_LFS_PPS*/
    public static final String BILL_TO_NM_LFS_PPS = "BILL_TO_NM_LFS_PPS";
    
    /**VAR_CHAR_CONST_NM :BILL_TO_ATTN_NM_LFS_PPS*/
    public static final String BILL_TO_ATTN_NM_LFS_PPS = "BILL_TO_ATTN_NM_LFS_PPS";
    
    /**VAR_CHAR_CONST_NM :BILL_TO_ADDR1_LFS_PPS*/
    public static final String BILL_TO_ADDR1_LFS_PPS = "BILL_TO_ADDR1_LFS_PPS";
    
    /**VAR_CHAR_CONST_NM :BILL_TO_ADDR2_LFS_PPS*/
    public static final String BILL_TO_ADDR2_LFS_PPS = "BILL_TO_ADDR2_LFS_PPS";
    
    /**VAR_CHAR_CONST_NM :BILL_TO_ADDR3_LFS_PPS*/
    public static final String BILL_TO_ADDR3_LFS_PPS = "BILL_TO_ADDR3_LFS_PPS";
    
    /**VAR_CHAR_CONST_NM :BILL_TO_ADDR4_LFS_PPS*/
    public static final String BILL_TO_ADDR4_LFS_PPS = "BILL_TO_ADDR4_LFS_PPS";
    
    /**VAR_CHAR_CONST_NM :BILL_TO_ADDR5_LFS_PPS*/
    public static final String BILL_TO_ADDR5_LFS_PPS = "BILL_TO_ADDR5_LFS_PPS";
    
    /**VAR_CHAR_CONST_NM :BILL_TO_ADDR6_LFS_PPS*/
    public static final String BILL_TO_ADDR6_LFS_PPS = "BILL_TO_ADDR6_LFS_PPS";

    /** DB Column Name: PRCH_GRP_CD_ESS */
    public static final String PRCH_GRP_CD_ESS = "ESS";
    
}
