/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/05/2009   Canon           M.Mimura        Create          N/A
 * 06/10/2009   Canon           Y.Koiwai        Update          N/A
 * 06/18/2009   Canon           Y.Koiwai        Update/del      N/A
 * 06/26/2009   Canon           Y.Koiwai        Update/del      N/A
 *
 * 07/10/2009   Canon           Y.Koiwai        Update          N/A
 * 07/30/2009   Canon           Y.Koiwai        Update/del      N/A
 *   ID NFXC001001=>NFXC308001
 * 11/03/2009   Canon           Y.Kondo         Update          DefID 1336
 * 11/10/2009   Canon           Y.Kondo         Update          DefID 463
 * 11/30/2009   Canon           Y.koiwai        Update          DefID 2072
 * 12/17/2009   Canon           K.Usui          Update          DefID-2698,2734
 * 03/29/2010   Fujitsu         K.Kimura        Update          DefID 5064
 * 04/01/2010   Canon           H.Tokunaga      Update          stmt_info Statement Print Parameter STMT_SQ_PK add
 * 04/13/2010   FSSL            H.Tokunaga      Update          Defid 5617
 * 07/09/2010   Canon           I.Kondo         Update          Def:7653 No.181
 * 08/02/2010   Canon           I.Kondo         Update          Merge.
 * 10/28/2010   Canon           I.Kondo         Update          R2 -> R3 Merge.
 * 12/17/2010   Canon           K.Kimura        Update          M92
 * 03/09/2011   Canon           Y.Suga          Update          DefID 1654
 * 08/11/2011   Fujitsu         K.Kimura        Update          ITG#353895
 * 09/26/2011   Fujitsu         T.Tanaka        Update          ITG#363113
 * 07/10/2013   Fujitsu         K.Kimura        Update          WDS#1368 Hard-coding modification
 * 04/06/2015   Fujitsu         T.Yoshida       Update          for North America(CSA)
 * 02/22/2017   Hitachi         E.Kameishi      Update          QC#16802
 * </pre>
 */
package com.canon.cusa.s21.common.NFX.NFXC308001;

import java.math.BigDecimal;

import parts.dbcommon.EZDTBLAccessor;

/**
 */
public class NFCConst {
    /** DEBUG_MSG_LVL */
    public static final int DEBUG_MSG_LVL = 8;

    /** */
    public static final String CST_VOUCHER_TYPE_CODE_INVOICE = "I";

    /** */
    public static final String CST_VOUCHER_TYPE_CODE_CREDITMEMO = "C";

    /** */
    public static final String CST_VOUCHER_TYPE_CODE_DEBITMEMO = "D";

    /** */
    public static final String CST_PROC_TP_CD_NEW = "1";

    /** */
    public static final String CST_PROC_TP_CD_UPD = "2";

    /** */
    public static final String CST_PROC_TP_CD_CANC = "3";

    /** */
    public static final String CST_PROC_TP_CD_APVL = "4";

    /** */
    public static final String CST_PROC_STS_CD_UNPROC = "0";

    /** */
    public static final String CST_PROC_STS_CD_CPLT = "1";

    /** */
    public static final String CST_PROC_STS_CD_ERR = "2";

    /** */
    public static final String CST_PROC_STS_CD_EXCLERR = "3";

    /** */
    public static final String CST_PROC_STS_CD_ETCERR = "4";

    /** */
    public static final String CST_PROC_STS_CD_EDIERR = "5";

    // for North America(CSA) 2015/04/06 Add Start
    /** Receipt Only */
    public static final String CST_PROC_STS_CD_RCPT_ONLY = "6";

    /** Receipt Only Completed */
    public static final String CST_PROC_STS_CD_RCPT_ONLY_CPLT = "7";
    // for North America(CSA) 2015/04/06 Add End

    /** */
    public static final String CST_NFZC301001_RTN_CD_UNPROC = "0";

    /** */
    public static final String CST_NFZC301001_RTN_CD_CPLT = "1";

    /** */
    public static final String CST_NFZC301001_RTN_CD_ERR = "2";

    /** */
    public static final String CST_NFZC301001_RTN_CD_EXCLERR = "3";

    /** */
    public static final String CST_NFZC301001_RTN_CD_ETCERR = "4";

    /** */
    public static final String CST_INV_TP_CD_INVOICE = "1";

    /** */
    public static final String CST_INV_TP_CD_CREDITMEMO = "2";

    /** */
    public static final String CST_INV_TP_CD_DEBITMEMO = "3";

    /** */
    public static final int CST_SSM_CURSOR_PARAMETER_FETCH_SIZE = 1000;

    /** */
    public static final int CST_SSM_CURSOR_PARAMETER_MAXROWS = 0;

    /** */
    public static final String CST_ROUND_METH_CD_ROUND_UP = "U";

    /** */
    public static final String CST_ROUND_METH_CD_ROUND_OFF = "O";

    /** */
    public static final String CST_ROUND_METH_CD_ROUND_DOWN = "D";

    // @2009626 del/20090708 upd
    /** */
    public static final String CST_AR_TRX_TP_CD_INVOICE = "INV";

    /** */
    public static final String CST_AR_TRX_TP_CD_CREDITMEMO = "CRM";

    /** */
    public static final String CST_AR_TRX_TP_CD_DEBITMEMO = "DEM";

    /** */
    public static final String CST_AR_TRX_TP_CD_RECEIPT = "RCP";

    /** */
    public static final String CST_AR_TRX_TP_CD_DEDUCTION = "DED";

    /** AR_TRX_TP_CD character for On_Account */
    public static final String CST_AR_TRX_TP_CD_ACCOUNT = "ACC";

    /** */
    public static final String CST_AR_TRX_TP_CD_SQL_INV = "AR_TRX_TP_CD_INV";

    /** */
    public static final String CST_AR_TRX_TP_CD_SQL_CRM = "AR_TRX_TP_CD_CRM";

    /** */
    public static final String CST_AR_TRX_TP_CD_SQL_DEM = "AR_TRX_TP_CD_DEM";

    /** */
    public static final String CST_AR_TRX_TP_CD_SQL_RCP = "AR_TRX_TP_CD_RCP";

    /** */
    public static final String CST_AR_TRX_TP_CD_SQL_DED = "AR_TRX_TP_CD_DED";

    /** AR_TRX_TP_CD SQL character for On_Account */
    public static final String CST_AR_TRX_TP_CD_SQL_ACC = "AR_TRX_TP_CD_ACC";

    /** */
    public static final String CST_BAT_STS_CD_UNPROC = "0";

    /** */
    public static final String CST_ADJ_STS_CD_APPROVED = "A";

    /** */
    public static final String CST_RTN_CD_NORM = "0";

    /** */
    public static final String CST_RTN_CD_ERR = "1";

    /** */
    public static final String CST_OB_FLG_ONLINE = "";

    /** */
    public static final String CST_OB_FLG_BATCH = "1";

    // @2009618 del
    // public static final String CST_SEQ_ID_RCPTNUM = "RCPT_NUM";
    /** */
    public static final String CST_SEQ_ID_AR_TRX_BAL = "AR_TRX_BAL_SQ";

    /** */
    public static final String CST_SEQ_ID_AR_CASH_APP = "AR_CASH_APP_SQ";

    /** */
    public static final String CST_SEQ_ID_AR_STMT_LINK_SQ = "AR_STMT_LINK_SQ";

    // @2009618 del
    // public static final String CST_NUM_PREFIX_RCPT = "RC";
    // @2009626 del/20090708 upd
    /** AR_CASH_APPLY_STS_CD UNAPPLY */
    public static final String CST_AR_CASH_APPLY_STS_CD_UNAPPLY = "U";

    /** AR_CASH_APPLY_STS_CD APPLY */
    public static final String CST_AR_CASH_APPLY_STS_CD_APPLY = "A";

    /** AR_CASH_APPLY_STS_CD VOID */
    public static final String CST_AR_CASH_APPLY_STS_CD_VOID = "V";

    /** SQL BINDPARAM AR_CASH_APPLY_STS_CD PARTIAL */
    public static final String CST_AR_CASH_APPLY_STS_CD_PARTIAL = "P";

    /** SQL BINDPARAM AR_CASH_APPLY_STS_CD UNAPPLY */
    public static final String CST_AR_CASH_APPLY_STS_CD_SQL_U = "AR_CASH_APPLY_STS_CD_U";

    /** SQL BINDPARAM AR_CASH_APPLY_STS_CD APPLY */
    public static final String CST_AR_CASH_APPLY_STS_CD_SQL_A = "AR_CASH_APPLY_STS_CD_A";

    /** SQL BINDPARAM AR_CASH_APPLY_STS_CD VOID */
    public static final String CST_AR_CASH_APPLY_STS_CD_SQL_V = "AR_CASH_APPLY_STS_CD_V";

    /** AR_CASH_APPLY_STS_CD PARTIAL */
    public static final String CST_AR_CASH_APPLY_STS_CD_SQL_P = "AR_CASH_APPLY_STS_CD_P";

    /** */
    public static final String CST_PROC_STS_RCV = "1";

    /** */
    public static final String CST_PROC_STS_CHK = "2";

    /** */
    public static final String CST_PROC_STS_APPLY = "3";

    // @20090708 upd
    /** */
    public static final String CST_DB_INIT_VAL_STR = "";

    /** */
    public static final BigDecimal CST_DB_INIT_VAL_NUM = BigDecimal.ZERO;

    /** */
    public static final String CST_INV_STS_CD_UNPROC = "00";

    /** */
    public static final String CST_ISS_CTRY_DT_TWENTY = "20";

    /** */
    public static final String CST_CCY_CD_USD = "USD";

    /** */
    public static final String CST_FLAG_ON = "Y";

    /** */
    public static final String CST_FLAG_OFF = "N";

    // @2009626 del
    // public static final String CST_BILL_SELL_TO_FLG_BILL_TO = "1";
    // public static final String CST_BILL_SELL_TO_FLG_SELL_TO = "2";
    /** */
    public static final String CST_AGING_PERIOD_TP_CD_FIXATION_LAST_MONTH = "2";

    /** Invoice */
    public static final String CST_AGING_TRX_TP_INV = "INV";

    /** Debit Memo */
    public static final String CST_AGING_TRX_TP_DEM = "DEM";

    /** Credit Memo */
    public static final String CST_AGING_TRX_TP_CRM = "CRM";

    /** Deduction */
    public static final String CST_AGING_TRX_TP_DED = "DED";

    /** On Account */
    public static final String CST_AGING_TRX_TP_ACC = "ACC";

    /** Receipt */
    public static final String CST_AGING_TRX_TP_RCP = "RCP";

    /** */
    public static final String CST_LOC_ROLE_TP_CD_RRECEIPT = "CUSA_PRIN";

    /** */
    public static final String CST_ONL_BAT_TP_CD_BAT = "2";

    /** */
    public static final String CST_XX_HDR_NUM_GET_FLG_ON = "Y";

    /** */
    public static final String CST_XX_HDR_NUM_GET_FLG_OFF = "N";

    /** */
    public static final String CST_XX_PROC_CASE_TP_CD_ERR = "9000";

    /** */
    public static final String CST_XX_PROC_CASE_TP_CD_PMT = "1010";

    /** */
    public static final String CST_XX_PROC_CASE_TP_CD_WRK = "1020";

    /** */
    public static final String CST_XX_PROC_CASE_TP_CD_NON = "1030";

    /** */
    public static final String CST_XX_PROC_CASE_TP_CD_ADD_DED_OR_ACC = "1040";

    /** */
    public static final String CST_XX_PROC_CASE_TP_CD_UPD_DED = "1041";

    /** */
    public static final String CST_XX_PROC_CASE_TP_CD_ADD_ADJ = "1050";

    /** */
    public static final String CST_XX_PROC_CASE_TP_CD_ADD_ACC = "1060";

    /** */
    public static final String CST_XX_PROC_CASE_TP_CD_ADJ_AUTO_APVL = "1070";

    /** */
    public static final String CST_XX_PROC_CASE_TP_CD_ADJ_APVL_WAIT = "1071";

    /** */
    public static final String CST_XX_PROC_CASE_TP_CD_CNT_BAL = "1080";

    /** */
    public static final String CST_XX_PROC_CASE_TP_CD_CR_MEMO = "1090";

    /** */
    public static final String CST_XX_PROC_CASE_TP_CD_NEW_RCPT = "1100";

    /** */
    public static final String CST_XX_PROC_CASE_TP_CD_NEW_INV = "1110";

    /** */
    public static final String CST_XX_PROC_CASE_TP_CD_CHNG_RCPT = "2200";

    /** */
    public static final String CST_XX_PROC_CASE_TP_CD_RF = "2100";

    /** */
    public static final String CST_XX_PROC_CASE_TP_CD_CANC_PMT = "3010";

    /** */
    public static final String CST_XX_PROC_CASE_TP_CD_CANC_ADD_DED_OR_ACC = "3040";

    /** */
    public static final String CST_XX_PROC_CASE_TP_CD_CANC_ADD_ADJ = "3050";

    /** */
    public static final String CST_XX_PROC_CASE_TP_CD_CANC_ADJ_APVL = "3070";

    /** */
    public static final String CST_XX_PROC_CASE_TP_CD_CANC_ADJ_APVL_WAIT = "3071";

    /** */
    public static final String CST_XX_PROC_CASE_TP_CD_CANC_CNT_BAL = "3080";

    /** */
    public static final String CST_XX_PROC_CASE_TP_CD_CANC_RCPT = "3200";

    /** */
    public static final String CST_XX_PROC_CASE_TP_CD_ADJ_APVL = "4070";

    /** */
    public static final String CST_XX_PROC_TP_CD_NON = "0";

    /** */
    public static final String CST_XX_PROC_TP_CD_ADD = "1";

    /** */
    public static final String CST_XX_PROC_TP_CD_UPD = "2";

    /** */
    public static final String CST_GRP_INV_FLG_GRP_ON = "Y";

    /** */
    public static final String CST_GRP_INV_FLG_GRP_OFF = "N";

    /** */
    public static final String CST_XX_GRP_FLG_CRAT_INV_ON = "Y";

    /** */
    public static final String CST_XX_GRP_FLG_CRAT_INV_OFF = "N";

    /** */
    public static final String CST_AUTO_CRAT_FLG_AUTO_ON = "Y";

    /** */
    public static final String CST_AUTO_CRAT_FLG_AUTO_OFF = "N";

    /** */
    public static final String CST_XX_INTFC_GRACE_PER_GET_FLG_ON = "Y";

    /** */
    public static final String CST_XX_INTFC_GRACE_PER_GET_FLG_OFF = "N";

    // @20090709 upd
    /** */
    public static final String CST_AR_CUST_AGING_BILL_TO_TOTAL_RECORD = "*";

    /** */
    public static final String CST_BILL_SELL_TP_CD_BILL_TO = "1";

    /** */
    public static final String CST_BILL_SELL_TP_CD_SELL_TO = "2";

    /** */
    public static final String CST_STMT_TRX_TP_CD_INVOICE = "IN";

    /** */
    public static final String CST_STMT_TRX_TP_CD_CREDITMEMO = "CM";

    /** */
    public static final String CST_STMT_TRX_TP_CD_DEBITMEMO = "DM";

    /** */
    public static final String CST_STMT_TRX_TP_CD_DEDUCTION = "DE";

    /** On Account */
    public static final String CST_STMT_TRX_TP_CD_ACCOUNT = "AC";

    /** */
    public static final String CST_AGING_PER_TP_CD_REALTIME = "1";

    /** */
    public static final String CST_AGING_PER_TP_CD_DAILY = "2";

    /** */
    public static final String CST_AGING_PER_TP_CD_LAST_MONTH = "3";

    /** */
    public static final String CST_ERR_RSN_CD_DOUBLE = "1";

    /** Error Reason Code --- BILL_TO_CUST_CD */
    public static final String CST_ERR_RSN_CD_BILL_TO = "2";

    /** Error Reason Code --- SELL_TO_CUST_CD */
    public static final String CST_ERR_RSN_CD_SELL_TO = "3";

    /** Error Reason Code --- PAYER_CUST_CD */
    public static final String CST_ERR_RSN_CD_PAYER = "4";

    /** Error Reason Code --- TOC_CD */
    public static final String CST_ERR_RSN_CD_TOC_CD = "5";

    /** Error Reason Code --- Credit Memo */
    public static final String CST_ERR_RSN_CD_CREDITMEMO = "6";

    /** */
    public static final String CST_EXTN_DISC_FLG_ON = "Y";

    /** */
    public static final String CST_EXTN_DISC_FLG_OFF = "N";

    // @2009626 del
    // /** FRT_CHRG_TO_NM --- CANON */
    // public static final String CST_FRT_CHRG_TO_NM_CANON = "CANON";
    // /** FRT_CHRG_TO_NM --- CUSTOMER */
    // public static final String CST_FRT_CHRG_TO_NM_CUSTOMER =
    // "Customer";
    /** */
    public static final String CST_INV_STS_CD_PROC = "01";

    /** */
    public static final String CST_INV_LINE_NUM_START = "001";

    // @20090730 upd
    /** */
    public static final String CST_SEQ_ID_BAL_INSTN = "AR_BAL_INSTN_SQ";

    /** */
    public static final String CST_SEQ_ID_AJE_INTFC = "AJE_INTFC_SQ";

    /** */
    public static final String CST_SEQ_ID_APPLY_GRP_SUB = "APPLY_GRP_SUB_SQ";

    /** */
    public static final String CST_SEQ_ID_AR_INV = "AR_INV_SQ";

    // @20090730 del
    // public static final String CST_SEQ_ID_RCPT_IN_PROC =
    // "RCPT_IN_PROC_SQ";
    // public static final String CST_SEQ_ID_RCV_HIST = "RCV_HIST_SQ";

    // @20090730 upd
    /** */
    public static final String CST_SEQ_ID_RCV = "AR_RCV_SQ";

    /** */
    public static final String CST_SEQ_ID_STMT = "AR_STMT_SQ";

    /** */
    public static final String CST_NUMBERING_KEY_RC = "RC#";

    /** */
    public static final String CST_NUMBERING_KEY_DD = "DD#";

    /** */
    public static final String CST_NUMBERING_KEY_AC = "AC#";

    /** */
    public static final String CST_NUMBERING_KEY_AD = "AD#";

    /** */
    public static final String CST_NUMBERING_KEY_RC_BAT = "RC#_BAT";

    /** */
    public static final String CST_NUMBERING_KEY_DD_BAT = "DD#_BAT";

    /** */
    public static final String CST_NUMBERING_KEY_AC_BAT = "AC#_BAT";

    /** */
    public static final String CST_NUMBERING_KEY_AD_BAT = "AD#_BAT";

    /** */
    public static final String CST_EZ_RETURN_CODE_CPLT = EZDTBLAccessor.RTNCD_NORMAL;

    /** */
    public static final String CST_EZ_RETURN_CODE_NODATA = EZDTBLAccessor.RTNCD_NOT_FOUND;

    /** */
    public static final String CST_EZ_RETURN_CODE_UNIEQU_CONSTRAINT_VIOLATE = EZDTBLAccessor.RTNCD_DUPLICATE;

    /** */
    public static final int CST_S21FASTTBL_RETURN_CODE_ERR = -1;

    /** */
    public static final String CST_CREATE_METH_TP_CD_AUTO = "A";

    /** */
    public static final String CST_CREATE_METH_TP_CD_CSV = "C";

    /** */
    public static final String CST_REC_TP_USE_FLG_ON = "Y";

    /** */
    public static final String CST_REC_TP_USE_FLG_OFF = "N";

    /** */
    public static final String CST_REC_ORD_TYPE_FLG_ON = "Y";

    /** */
    public static final String CST_REC_ORD_TYPE_FLG_OFF = "N";

    /** ACCT_MA_CD(Paymentech) --- PAYTEC */
    public static final String CST_ACCT_MA_CD_PAYTEC = "PAYT";

    /** */
    public static final String CST_SUB_SYS_CD_NFC = "NFC";

    /** */
    public static final String CST_RCV_STS_CD_UNPROC = "0";

    /** */
    public static final String CST_RCV_STS_CD_PROC = "1";

    /** */
    public static final String CST_RCPT_STS_CD_NORM = "00";

    /** */
    public static final String CST_RCPT_STS_CD_ERR = "01";

    // @2009701 del/20090708 upd
    /** */
    public static final String CST_AR_RCPT_TRX_TP_CD_REGULAR_RECEIPT = "00";

    /** */
    public static final String CST_VOID_FLG_OFF = "N";

    /** CST_RCPT_BAT_NUM_ALL_NINE */
    public static final String CST_RCPT_BAT_NUM_ALL_NINE = "999";

    /** */
    public static final String CST_AR_CUST_REF_TP_CD_REF = "REF";

    /** CST_AR_CUST_REF_TP_CD --- OTHERS */
    public static final String CST_AR_CUST_REF_TP_CD_OTHERS = "OTH";

    /** */
    public static final String CST_AR_CRAT_FLG_ON = "Y";

    /** */
    public static final String CST_AR_CRAT_FLG_OFF = "N";

    /** */
    public static final String CST_AR_UN_APPLY_STS_CD_NEW = "1";

    /** */
    public static final String CST_AR_UN_APPLY_STS_CD_CASH = "2";

    /** */
    public static final String CST_AR_UN_APPLY_STS_CD_CASH_CANC = "3";

    /** */
    public static final String CST_AR_UN_APPLY_STS_CD_VOID = "4";

    /** */
    public static final String CST_AR_UN_APPLY_STS_CD_RF = "5";

    /** */
    public static final String CST_ONL_BAT_TP_CD_ONL = "1";

    /** */
    public static final String CST_PRC_TP_CD_DAY = "0";

    /** */
    public static final String CST_PRC_TP_CD_MNTH = "1";

    /** */
    public static final String CST_ADJ_STS_CD_SUBMITTED = "S";

    /** */
    public static final String CST_XX_PROC_CASE_TP_CD_BANK_FEE = "1120";

    // @2009701 del
    // public static final String CST_ONL_BAT_TP_CD_BATCH = "2";
    /** */
    public static final String CST_AR_STMT_FLG_ON = "Y";

    /** */
    public static final String CST_AR_STMT_FLG_OFF = "N";

    /** */
    public static final String CST_XX_PROC_CASE_TP_CD_UPLOAD = "1130";

    /** */
    public static final String CST_ERR_FLG_ON = "Y";

    /** */
    public static final String CST_ERR_FLG_OFF = "N";

    /** */
    public static final String CST_INV_NUM_EDIT_PROC_TP_CD_ADD_HEAD = "1";

    /** */
    public static final String CST_INV_NUM_EDIT_PROC_TP_CD_DEL_FORWARD = "2";

    /** */
    public static final String CST_INV_NUM_EDIT_PROC_TP_CD_DEL_REAR_SIDE = "3";

    /** */
    public static final String CST_RCV_TRX_TP_CD_INVOICE = "INV";

    /** */
    public static final String CST_VAR_CHAR_CONST_NAME_SUB_SYS_ID = "AR_SUB_SYS_ID";

    /** VAR_CHAR_CONST_NM = AR_PMT_NONE_VALUE. */
    public static final String CST_AR_PMT_NONE_VALUE = "AR_PMT_NONE_VALUE";

    /** VAR_CHAR_CONST_NM = AR_CASH_DISC_NONE_VALUE. */
    public static final String CST_AR_CASH_DISC_NONE_VALUE = "AR_CASH_DISC_NONE_VALUE";

    /** VAR_CHAR_CONST_NM = CST_VAR_CHAR_CONST_NAME_DPLCT_PCT. */
    public static final String CST_VAR_CHAR_CONST_NAME_DPLCT_PCT = "AR_DPLCT_PCT";

    /** */
    public static final String CST_XX_PROC_CASE_TP_CD_GRP_INV = "8010";

    /** AR_ADJ_TRX_TP_CD DED --- */
    public static final String CST_AR_ADJ_TRX_TP_CD_DEDUCTION = "DED";

    /** AR_ADJ_TRX_TP_CD RFD --- */
    public static final String CST_AR_ADJ_TRX_TP_CD_REFUND = "RFD";

    /** AR_ADJ_TRX_TP_CD ACC --- */
    public static final String CST_AR_ADJ_TRX_TP_CD_ACCOUNT = "ACC";

    /** AR_ADJ_TP_CD --- */
    public static final String CST_AR_ADJ_TP_CD_BANK_COMMISSION_FEE = "D";

    /** AR_ADJ_TP_CD --- */
    public static final String CST_AR_ADJ_TP_CD_A_OR_R_REFUND = "J";

    /** AR_ADJ_TP_CD --- */
    public static final String CST_AR_ADJ_TP_CD_FREIGHT_OUT = "A";

    /** AR_ADJ_TP_CD --- */
    public static final String CST_AR_ADJ_TP_CD_NON_OPERATING_MISCELLANEOUS = "B";

    /** AR_ADJ_TP_CD : MISCELLANEOUS INCOME */
    public static final String CST_AR_ADJ_TP_CD_MISCELLANEOUS_INCOME = "C";

    /** AR_ADJ_TP_CD --- */
    public static final String CST_AR_ADJ_TP_CD_ACCRUED_SALES_TAX = "I";

    /** AR_ADJ_TP_CD --- */
    public static final String CST_AR_ADJ_TP_CD_CM_RFND = "P";

    /** AR_ADJ_TP_CD --- */
    public static final String CST_AR_ADJ_TP_CD_CC_RFND = "CC";

    /** */
    public static final String CST_TOT_PER_SEG_CD_PAST = "1";

    /** */
    public static final String CST_TOT_PER_SEG_CD_FUTURE = "2";

    /** */
    public static final String CST_USE_SEG_CD_SCREENSLIP = "1";

    /** */
    public static final String CST_USE_SEG_CD_INFOANALYSIS = "2";

    /** USE_SEG_CD : MONTHLY */
    public static final String CST_USE_SEG_CD_MONTHLY = "3";

    /** */
    public static final String CST_XX_INV_NUM_GET_FLG_ON = "Y";

    /** */
    public static final String CST_XX_INV_NUM_GET_FLG_OFF = "N";

    /** */
    public static final String CST_GL_DT_MAX_DATE = "29991231";

    /** */
    public static final String CST_NFZC301001_RTN_CD_EXCLLOCKERR = "5";

    /** */
    public static final String CST_ADJ_STS_CD_REJECTED = "R";

    /** */
    public static final String CST_AR_UN_APPLY_TP_CD_UNIDENTIFED = "U";

    /** */
    public static final String CST_AR_UN_APPLY_TP_CD_ON_ACCOUNT = "A";

    /** */
    public static final String CST_AR_ADJ_TRX_TP_CD_ADJUSTMENT = "ADJ";

    /** */
    public static final String CST_AR_APPLY_TP_CD_CASH = "CSH";

    /** */
    public static final String CST_AR_APPLY_TP_CD_ADJUSTMENT = "ADJ";

    /** */
    public static final String CST_AR_APPLY_TP_CD_CREDITMEMO = "CRM";

    /** */
    public static final String CST_AR_APPLY_TP_CD_REFUND = "RFD";

    /** */
    public static final String CST_AR_APPLY_TP_CD_REVALUATION = "RVL";

    /** */
    /** greater than */
    public static final int CST_COMPARE_GREATER = 1;

    /** less than */
    public static final int CST_COMPARE_LESS = -1;

    /** equal */
    public static final int CST_COMPARE_EQUAL = 0;

    /** */
    public static final String CST_VAR_CHAR_CONST_NAME_AR_DISC_GRACE_PER_FROM_DT = "AR_DISC_GRACE_PER_FROM_DT";

    /** */
    public static final String CST_VAR_CHAR_CONST_NAME_AR_DISC_GRACE_PER_TO_DT = "AR_DISC_GRACE_PER_TO_DT";

    /** */
    public static final String CST_VAR_CHAR_CONST_NAME_AR_BAT_USR_ID = "AR_BAT_USR_ID";

    /** */
    public static final String CST_VAR_CHAR_CONST_NAME_AR_CUST_NM_CONV_DICTIONARY_PER = "AR_CUST_NM_CONV_DICTIONARY_PER";

    /** */
    public static final String CST_VAR_CHAR_CONST_NAME_AR_DISC_DELAY_AOT = "AR_DISC_DELAY_AOT";

    /** AR_STD_EXCH_RATE */
    public static final String CST_NUM_CONST_NAME_AR_STD_EXCH_RATE = "AR_STD_EXCH_RATE";

    /** Printer type */
    public static final String CST_AR_STMT_PRT_TYPE_A = "PRT01";

    /** Printer type */
    public static final String CST_AR_STMT_PRT_TYPE_B = "PRT02";

    /** Printer type */
    public static final String CST_AR_STMT_PRT_TYPE_C = "PRT03";

    /** ChangePrinter Page */
    public static final int CST_AR_STMT_PRT_CHANGE_PAGE0 = 1;

    /** ChangePrinter Page */
    public static final int CST_AR_STMT_PRT_CHANGE_PAGE1 = 8;

    /** ChangePrinter Page */
    public static final int CST_AR_STMT_PRT_CHANGE_PAGE2 = 48;

    /** AR_CLT_RELN_TP_CD Transaction */
    public static final String CST_AR_CLT_RELN_TP_CD_TRANSACTION = "T";

    /** AR_CLT_RELN_TP_CD Credit */
    public static final String CST_AR_CLT_GRP_TP_CD_CREDIT = "C";

    /** AR_CLT_RELN_TP_CD Logistics */
    public static final String CST_AR_CLT_GRP_TP_CD_LOGISTICS = "L";

    /** AR_CLT_RELN_TP_CD Sales */
    public static final String CST_AR_CLT_GRP_TP_CD_SALES = "S";

    /** AR_RCPT_TP_CD Check */
    public static final String CST_AR_RCPT_TP_CD_CHECK = "01";

    /** AR_RCPT_TP_CD ACH */
    public static final String CST_AR_RCPT_TP_CD_ACH = "02";

    /** AR_RCPT_TP_CD CREDIT_CARD */
    public static final String CST_AR_RCPT_TP_CD_CREDIT_CARD = "04";

    /** AR_RCPT_TP_CD PURGE */
    public static final String CST_AR_RCPT_TP_CD_PURGE = "07";

    /** AR_RCPT_TP_CD CROSS PURGE */
    public static final String CST_AR_RCPT_TP_CD_CROSS_PURGE = "12";

    /** */
    public static final String CST_RECEIPT_PRINT_PARAM_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** */
    public static final String CST_RECEIPT_PRINT_PARAM_GL_DT_FROM = "GL_DT_FROM";

    /** */
    public static final String CST_RECEIPT_PRINT_PARAM_GL_DT_THRU = "GL_DT_THRU";

    /** */
    public static final String CST_RECEIPT_PRINT_PARAM_AR_BANK_ACCT_CD = "AR_BANK_ACCT_CD";

    /** */
    public static final String CST_RECEIPT_PRINT_PARAM_USER_ID = "USER_ID";

    /** */
    public static final String CST_RECEIPT_PRINT_STMT_SQ_PK = "STMT_SQ_PK";

    /** ANSI_PMT_METH_TP_CD ACH */
    public static final String CST_ANSI_PMT_METH_TP_CD_ACH = "ACH";

    /** ANSI_PMT_METH_TP_CD CHK */
    public static final String CST_ANSI_PMT_METH_TP_CD_CHK = "CHK";

    /** ANSI_PMT_METH_TP_CD CR */
    public static final String CST_ANSI_PMT_METH_TP_CD_CR = "CR";

    /** AR_RCPT_TRX_TP_CD 00 */
    public static final String CST_AR_RCPT_TRX_TP_CD_00 = "00";

    /** AR_RCPT_TRX_TP_CD 01 */
    public static final String CST_AR_RCPT_TRX_TP_CD_01 = "01";

    /** AR_RCPT_TRX_TP_CD 02 */
    public static final String CST_AR_RCPT_TRX_TP_CD_02 = "02";

    /** AR_RCPT_TRX_TP_CD 03 */
    public static final String CST_AR_RCPT_TRX_TP_CD_03 = "03";

    /** AR_RCPT_TRX_TP_CD 04 */
    public static final String CST_AR_RCPT_TRX_TP_CD_04 = "04";

    /** TRX_CD AR_RECEIPT */
    public static final String CST_TRX_CD_AR_RECEIPT = "110";

    /** TRX_CD AR_RECEIPT_ADJUSTMENT */
    public static final String CST_TRX_CD_AR_RECEIPT_ADJUSTMENT = "120";

    /** TRX_CD AR_INVOICE_ADJUSTMENT */
    public static final String CST_TRX_CD_AR_INVOICE_ADJUSTMENT = "130";

    /** TRX_CD AR_CASH_DISCOUNT */
    public static final String CST_TRX_CD_AR_CASH_DISCOUNT = "140";

    /** TRX_CD AR_DEDUCTION */
    public static final String CST_TRX_CD_AR_DEDUCTION = "150";

    /** TRX_CD AR_CASH_APPLICATION */
    public static final String CST_TRX_CD_AR_CASH_APPLICATION = "160";

    /** TRX_CD AR_CASH_APPLICATION */
    public static final String CST_TRX_CD_AR_OFFSET = "170";

    /** TRX_CD AR_REVALUATION */
    public static final String CST_TRX_CD_AR_REVALUATION = "180";

    /** TRX_CD AR_RECLASS */
    public static final String CST_TRX_CD_AR_RECLASS = "900";

    /** TRX_RSN_CD ON_ACCOUNT */
    public static final String CST_TRX_RSN_CD_ON_ACCOUNT = "01";

    /** TRX_RSN_CD UN_IDENTIFIED */
    public static final String CST_TRX_RSN_CD_UN_IDENTIFIED = "02";

    /** TRX_RSN_CD UN_IDENTIFIED_TO_ON_ACCOUNT */
    public static final String CST_TRX_RSN_CD_UN_IDENTIFIED_TO_ON_ACCOUNT = "03";

    /** TRX_RSN_CD RECEIPT */
    public static final String CST_TRX_RSN_CD_RECEIPT = "02";

    /** TRX_RSN_CD UN_IDENTIFIED */
    public static final String CST_TRX_RSN_CD_TRANSACTION = "01";

    /** INTERFACE_ID UPLOAD */
    public static final String CST_INTERFACE_ID_UPLD = "UPLD";

    /** AR_ADJ_TRX_RSN_CD */
    public static final String CST_AR_ADJ_TRX_RSN_CD_OPEN_REFUND = "07";

    /** TRX_RSN_CD INSUFFICIENT */
    public static final String CST_TRX_RSN_CD_INSUFFICIENT = "01";

    /** TRX_RSN_CD DEDUCTION */
    public static final String CST_TRX_RSN_CD_DEDUCTION = "01";

    /** TRX_RSN_CD BANK_COMMISSION_FEE */
    public static final String CST_TRX_RSN_CD_BANK_COMMISSION_FEE = "02";

    /** START_DATE_OF_MONTH */
    public static final String CST_START_DATE_OF_MONTH = "01";

    /** CST_OVD_STS_CD_ON */
    public static final String CST_OVD_STS_CD_ON = "1";

    /** CST_OVD_STS_CD_OFF */
    public static final String CST_OVD_STS_CD_OFF = "0";

    /** STMT_PRT_ID A */
    public static final String CST_STMT_PRT_ID_TYPE_01 = "STMT_PRTR_ID_01";

    /** STMT_PRT_ID B */
    public static final String CST_STMT_PRT_ID_TYPE_02 = "STMT_PRTR_ID_02";

    /** STMT_PRT_ID B */
    public static final String CST_STMT_PRT_ID_TYPE_03 = "STMT_PRTR_ID_03";

    /** CST_COA_PROD_CD_ADMINISTRATION */
    public static final String CST_COA_PROD_CD_ADMINISTRATION = "ZZ";

    /** AMT_INFO_ENTY_NM : AR_RCPT */
    public static final String CST_AMT_INFO_ENTY_NM_AR_RCPT = "AR_RCPT";

    /** AR_CASH_APP_MAN_ENTRY_TP_CD : DISABLED */
    public static final String CST_AR_CASH_APP_MAN_ENTRY_TP_CD_DISABLED = "0";

    /** AR_CASH_APP_MAN_ENTRY_TP_CD : RECEIPT */
    public static final String CST_AR_CASH_APP_MAN_ENTRY_TP_CD_RECEIPT = "1";

    /** AR_CASH_APP_MAN_ENTRY_TP_CD : INVOICE */
    public static final String CST_AR_CASH_APP_MAN_ENTRY_TP_CD_INVOICE = "2";

    /** AR_CASH_APP_MAN_ENTRY_TP_CD : BOTH */
    public static final String CST_AR_CASH_APP_MAN_ENTRY_TP_CD_BOTH = "3";

    /** AR_BANK_ACCT_CD_ZPO : ZPO */
    public static final String CST_AR_BANK_ACCT_CD_ZPO = "ZPO";

    /** _CHK_30_NUM ALL9 */
    public static final String CST_CHK_30_NUM_NUMBER_ALL9 = "9999999999";

    /** NULL Conversion INVALID_DATE */
    public static final String CST_NULL_CONV_DATE = "30001231";

    /** EZ standard: Cancellation flag unlogical deletion. */
    public static final String CST_TABLE_DATA_LIVE = "0";

    /** EZ standard: The cancellation flag logic has been deleted. */
    public static final String CST_TABLE_DATA_DEATH = "1";

    /** VAR_CHAR_CONST name of TOC_CD */
    public static final String CST_VAR_CHAR_CONST_NAME_TOC_CD = "AR_RCPT_TOC_CD";

    /** VAR_CHAR_CONST name of PROD_CD */
    public static final String CST_VAR_CHAR_CONST_NAME_PROD_CD = "AR_RCPT_PROD_CD";

    /** VAR_CHAR_CONST name of AR_CUST_CD_NONE_VALUE */
    public static final String CST_VAR_CHAR_CONST_NAME_AR_CUST_CD_NONE_VALUE = "AR_CUST_CD_NONE_VALUE";

    /** TRX_RSN_CD MISCELLANEOUS INCOME */
    public static final String CST_120_TRX_RSN_CD_MISCELLANEOUS_INCOME = "01";

    /** TRX_RSN_CD INTEREST OTHERS */
    public static final String CST_120_TRX_RSN_CD_INTEREST_OTHERS = "02";

    /** TRX_RSN_CD A/R CASH REFUND */
    public static final String CST_120_TRX_RSN_CD_AR_CASH_REFUND = "03";

    /** TRX_RSN_CD FREIGHT OUT */
    public static final String CST_130_TRX_RSN_CD_FREIGHT_OUT = "01";

    /** TRX_RSN_CD NON-OPERATING MISCELLANEOUS */
    public static final String CST_130_TRX_RSN_CD_NON_OPERATING_MISCELLANEOUS = "02";

    /** TRX_RSN_CD PROFESSIONAL FEE */
    public static final String CST_130_TRX_RSN_CD_PROFESSIONAL_FEE = "03";

    /** TRX_RSN_CD WRITE OFF */
    public static final String CST_130_TRX_RSN_CD_WRITE_OFF = "04";

    /** TRX_RSN_CD ACCRUED SALES TAX */
    public static final String CST_130_TRX_RSN_CD_ACCRUED_SALES_TAX = "05";

    /** TRX_RSN_CD A/R TO AFFILIATES */
    public static final String CST_130_TRX_RSN_CD_AR_TO_AFFILIATES = "06";

    /** TRX_RSN_CD OPEN A/R REFUND */
    public static final String CST_130_TRX_RSN_CD_OPEN_AR_REFUND = "07";

    /** TRX_RSN_CD CASH DISCOUNT */
    public static final String CST_130_TRX_RSN_CD_CASH_DISCOUNT = "08";

    /** TRX_RSN_CD CASH DISCOUNT */
    public static final String CST_140_TRX_RSN_CD_CASH_DISCOUNT = "01";

    /** TRX_RSN_CD DEDUCTION */
    public static final String CST_150_TRX_RSN_CD_DEDUCTION = "01";

    /** TRX_RSN_CD BANK COMMISSION FEE */
    public static final String CST_150_TRX_RSN_CD_BANK_COMMISSION_FEE = "02";

    /** TRX_RSN_CD ACCOUNT */
    public static final String CST_150_TRX_RSN_CD_ACCOUNT = "03";

    /** TRX_RSN_CD CASH APPLICATION */
    public static final String CST_160_TRX_RSN_CD_CASH_APPLICATION = "01";

    /** TRX_RSN_CD OFFSET */
    public static final String CST_170_TRX_RSN_CD_OFFSET = "01";

    /** TRX_RSN_CD A/R TRANSACTION REVALUATION */
    public static final String CST_180_TRX_RSN_CD_AR_TRANSACTION_REVALUATION = "01";

    /** TRX_RSN_CD A/R RECEIPT REVALUATION */
    public static final String CST_180_TRX_RSN_CD_AR_RECEIPT_REVALUATION = "02";

    /** AR_BANK_ACCT_CD BOS */
    public static final String AR_BANK_ACCT_CD_BOS = "BOS";

    /** AR_BANK_ACCT_CD BOT */
    public static final String AR_BANK_EXPORT_ACCT_CD_BOA = "BOA";

    /** DUP_ERR_CD DUP_ERR_CD_NOMAL */
    public static final String DUP_ERR_CD_NOMAL = "0";

    /** DUP_ERR_CD DUP_ERR_CD_INVOICE_REPETITION */
    public static final String DUP_ERR_CD_INVOICE_REPETITION = "1";

    /** AR_RCPT_TP_CD 01 */
    public static final String CST_AR_RCPT_TP_CD_01 = "01";

    /** AR_RCPT_TP_CD 02 */
    public static final String CST_AR_RCPT_TP_CD_02 = "02";

    /** AR_RCPT_TP_CD 03 */
    public static final String CST_AR_RCPT_TP_CD_03 = "03";

    /** AR_RCPT_TP_CD 04 */
    public static final String CST_AR_RCPT_TP_CD_04 = "04";

    /** AR_RCPT_TP_CD 05 */
    public static final String CST_AR_RCPT_TP_CD_05 = "05";

    /** AR_RCPT_TP_CD 06 */
    public static final String CST_AR_RCPT_TP_CD_06 = "06";

    /** AR_RCPT_TP_CD 08 */
    public static final String CST_AR_RCPT_TP_CD_08 = "08";

    /** AR_RCPT_TP_CD 09 */
    public static final String CST_AR_RCPT_TP_CD_09 = "09";

    /** AR_RCPT_TP_CD 10 */
    public static final String CST_AR_RCPT_TP_CD_10 = "10";

    /** AR_RCPT_TP_CD 11 */
    public static final String CST_AR_RCPT_TP_CD_11 = "11";

    /** VAR_CHAR_CONST name of AR_AUTO_CRAT_REF_NUM */
    public static final String CST_VAR_CHAR_CONST_NAME_AR_AUTO_CRAT_REF_NUM = "AR_AUTO_CRAT_REF_NUM";

    /** INTERFACEID CBS */
    public static final String CST_INTERFACEID_CBS = "NFCI0170";

    /** INTERFACEID CFS */
    public static final String CST_INTERFACEID_CFS = "NFCI0470";

    /** INTERFACEID LOCKBOCK */
    public static final String CST_INTERFACEID_BOA = "NFCI0300";

    // for North America(CSA) 2015/04/06 Add Start
    /** INTERFACEID BOA CHICAGO */
    public static final String CST_INTERFACEID_BOA_CHICAGO = "NFCI1010";

    /** INTERFACEID BOA LA */
    public static final String CST_INTERFACEID_BOA_LA = "NFCI1020";
    
    /** INTERFACEID CFS */
    public static final String CST_INTERFACEID_CFS_REGULAR = "NFCI1030";

    /** INTERFACEID CFS */
    public static final String CST_INTERFACEID_CFS_CPC = "NFCI1040";

    /** INTERFACEID CFS */
    public static final String CST_INTERFACEID_CFS_MD = "NFCI1050";
    
    /** INTERFACEID ECHECK */
    public static final String CST_INTERFACEID_ECHECK = "NFCI1110";
    // START 2017/02/22 E.Kameishi [QC#16802,ADD]
    /** INTERFACEID EMANAGE */
    public static final String CST_INTERFACEID_EMANAGE = "NFZC6010";
    // END 2017/02/22 E.Kameishi [QC#16802,ADD]
    // START 2017/01/29 [QC#23110,ADD]
    /** INTERFACE BOZ SAP */
    public static final String CST_INTERFACEID_BOA_SAP = "NFCI1140";
    // END 2017/01/29 [QC#23110,ADD]
    // for North America(CSA) 2015/04/06 Add End

    /** AR_AUTO_RCPT_REF_NUM */
    public static final String CST_VAR_CHAR_CONST_NAME_AR_AUTO_RCPT_REF_NUM = "AR_AUTO_RCPT_REF_NUM";

    /** LOC_ROLE_TP_CD DUNS_NUM KEY */
    public static final String CST_LOC_ROLE_TP_CD_DUNS_NUM_KEY = "CUSA_PRIN";

    /** TOT_PER_CD_365_OVER */
    public static final String TOT_PER_CD_365_OVER = "01";

    /** TOT_PER_CD_181_364 */
    public static final String TOT_PER_CD_181_364 = "02";

    /** TOT_PER_CD_91_180 */
    public static final String TOT_PER_CD_91_180 = "03";

    /** TOT_PER_CD_61_90 */
    public static final String TOT_PER_CD_61_90 = "04";

    /** TOT_PER_CD_31_60 */
    public static final String TOT_PER_CD_31_60 = "05";

    /** TOT_PER_CD_16_30 */
    public static final String TOT_PER_CD_16_30 = "06";

    /** TOT_PER_CD_1_15 */
    public static final String TOT_PER_CD_1_15 = "07";

    /** TOT_PER_CD_CURRENT */
    public static final String TOT_PER_CD_CURRENT = "08";

    /** TOT_PER_CD_FUTURE */
    public static final String TOT_PER_CD_FUTURE = "09";

    // ////////////////////////////////////////////////////////////////
    // ADDITIONAL FIELD FOR R3.
    // ////////////////////////////////////////////////////////////////

    /** TRX_RSN_CD ADVANCE RECEIPT */
    public static final String CST_TRX_RSN_CD_ADVANCE_RECEIPT = "04";

    /** TRX_RSN_CD AR TO CFS */
    public static final String CST_TRX_RSN_CD_AR_TO_CFS = "11";

    /** TRX_RSN_CD EXPORT BANK COMMISSION */
    public static final String CST_TRX_RSN_CD_EXPT_BANK_COMM = "20";

    /** TRX_RSN_CD FOREX LOSS/GAIN */
    public static final String CST_TRX_RSN_CD_EXPT_FOREX_LOSS_GAIN = "21";

    /** TRX_RSN_CD RECEIPT REVALUATION (ADVANCE) */
    public static final String CST_TRX_RSN_CD_ADV_RCPT_REVAL = "03";

    /** TRX_RSN_CD MISCELLANEOUS INCOME (ADVANCE) */
    public static final String CST_120_TRX_RSN_CD_MISCE_INCOME_ADV = "04";

    /** TRX_RSN_CD INTEREST OTHERS (ADVANCE) */
    public static final String CST_120_TRX_RSN_CD_INTEREST_OTHERS_ADV = "05";

    /** TRX_RSN_CD CASH REFUND (ADVANCE) */
    public static final String CST_120_TRX_RSN_CD_AR_CASH_REFUND_ADV = "06";

    /** TRX_RSN_CD BACK COMMISSION FEE */
    public static final String CST_130_TRX_RSN_CD_BANK_COMM_FEE = "10";

    /** TRX_RSN_CD CASH APPLICATION (ADVANCE) */
    public static final String CST_160_TRX_RSN_CD_CASH_APPL_ADV = "02";

    /** TRX_RSN_CD NON AFFL Company */
    public static final String CST_TRX_RSN_CD_NON_AFFL_COMP = "26";

    /** TRX_RSN_CD EXPORT MISCELLANEOUS EXPENSE */
    public static final String CST_120_TRX_RSN_CD_EXPT_MISC_EXP = "20";

    /** TRX_RSN_CD EXPORT MISCELLANEOUS EXPENSE */
    public static final String CST_120_21_TRX_RSN_CD_EXPT_MISC_EXP = "21";

    /** TRX_RSN_CD AFFL Company */
    public static final String CST_TRX_RSN_CD_AFFL_COMP = "20";

    /** TRX_RSN_CD RECIPT TO AFIILIATE */
    public static final String CST_TRX_RSN_CD_RCPT_TO_AFFL = "10";

    /** TRX_RSN_CD DEPOSIT ADJUSTMENT ADVANCE */
    public static final String CST_TRX_RSN_CD_DEPOSIT_ADJ_ADV = "22";

    /** TRX_RSN_CD DEPOSIT ADJUSTMENT */
    public static final String CST_TRX_RSN_CD_DEPOSIT_ADJ = "23";

    /** SYS_SRC_CD:AT */
    public static final String SYS_SRC_CD_AT = "NT";

    /** SYS_SRC_CD:AS2 */
    public static final String SYS_SRC_CD_AS2 = "NS2";

    /** SYS_SRC_CD:AE5 */
    public static final String SYS_SRC_CD_AE5 = "NE5";

    /** AR_INV_DTL_SUB_NUM_SET */
    public static final String AR_INV_DTL_SUB_NUM_SET = "AR_INV_DTL_SUB_NUM_SET";

    /** AR_CASH_APPLY_BATCH_ID */
    public static final String AR_CASH_APPLY_BATCH_ID = "NFCB030001";

    /** AR_NFCL0210_RCPT_TRX_TP_DOM */
    public static final String AR_NFCL0210_RCPT_TRX_TP_DOM = "AR_NFCL0210_RCPT_TRX_TP_DOM";

    /** AR_NFCL0210_RCPT_TRX_TP_EXPT */
    public static final String AR_NFCL0210_RCPT_TRX_TP_EXPT = "AR_NFCL0210_RCPT_TRX_TP_EXPT";

    /** AR_NFCL0660_RCPT_TRX_TP_DOM */
    public static final String AR_NFCL0660_RCPT_TRX_TP_DOM = "AR_NFCL0660_RCPT_TRX_TP_DOM";

    /** AR_NFCL0660_RCPT_TRX_TP_EXPT */
    public static final String AR_NFCL0660_RCPT_TRX_TP_EXPT = "AR_NFCL0660_RCPT_TRX_TP_EXPT";

    /** AR_NFCL0840_RCPT_TRX_TP_DOM */
    public static final String AR_NFCL0840_RCPT_TRX_TP_DOM = "AR_NFCL0840_RCPT_TRX_TP_DOM";

    /** AR_NFCL0840_RCPT_TRX_TP_EXPT */
    public static final String AR_NFCL0840_RCPT_TRX_TP_EXPT = "AR_NFCL0840_RCPT_TRX_TP_EXPT";

    /** AR_NFCL0210_RCPT_TP_DOM */
    public static final String AR_NFCL0210_RCPT_TP_DOM = "AR_NFCL0210_RCPT_TP_DOM";

    /** AR_NFCL0210_RCPT_TP_EXPT */
    public static final String AR_NFCL0210_RCPT_TP_EXPT = "AR_NFCL0210_RCPT_TP_EXPT";

    /** AR_NFCL0660_RCPT_TP_DOM */
    public static final String AR_NFCL0660_RCPT_TP_DOM = "AR_NFCL0660_RCPT_TP_DOM";

    /** AR_NFCL0660_RCPT_TP_EXPT */
    public static final String AR_NFCL0660_RCPT_TP_EXPT = "AR_NFCL0660_RCPT_TP_EXPT";

    /** AR_NFCL0840_RCPT_TP_DOM */
    public static final String AR_NFCL0840_RCPT_TP_DOM = "AR_NFCL0840_RCPT_TP_DOM";

    /** AR_NFCL0840_RCPT_TP_EXPT */
    public static final String AR_NFCL0840_RCPT_TP_EXPT = "AR_NFCL0840_RCPT_TP_EXPT";

    /** AR_NFCL0210_BANK_ACCT_DOM */
    public static final String AR_NFCL0210_BANK_ACCT_DOM = "AR_NFCL0210_BANK_ACCT_DOM";

    /** AR_NFCL0210_BANK_ACCT_EXPT */
    public static final String AR_NFCL0210_BANK_ACCT_EXPT = "AR_NFCL0210_BANK_ACCT_EXPT";

    // NFCL0210 Hard Code Modify
    /** NFCL0210_AR_RCTRX_TP_AR_AFFL */
    public static final String AR_RCPT_TRX_TP_AR_AFFL = "NFCL0210_AR_RCTRX_TP_AR_AFFL";

    /** NFCL0210_AR_BA_AR_AFFL */
    public static final String AR_BANK_ACCT_AR_AFFL = "NFCL0210_AR_BA_AR_AFFL";

    // NFCL0670 Hard Code Modify
    /** NFCL0670_AR_PR_CREDIT_DRCTR */
    public static final String AR_PURGE_ROLE_CREDIT_DRCTR = "NFCL0670_AR_PR_CREDIT_DRCTR";

    /** NFCL0670_AR_PR_CREDIT_MGR */
    public static final String AR_PURGE_ROLE_CREDIT_MGR = "NFCL0670_AR_PR_CREDIT_MGR";

    /** AR_COA_AFFL_NON_AFFL_CMPY */
    public static final String AR_COA_AFFL_NON_AFFL_CMPY = "AR_COA_AFFL_NON_AFFL_CMPY";

    /** AR_IPO_SYS_SRC_CD */
    public static final String AR_IPO_SYS_SRC_CD = "AR_IPO_SYS_SRC_CD";

    /** AR_EXCEPT_SYS_SRC_CD */
    public static final String AR_EXCEPT_SYS_SRC_CD = "AR_EXCEPT_SYS_SRC_CD";

}
