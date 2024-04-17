/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFB.NFBB131001;

/**
 * <pre>
 * CSMP Credit Memo Data Validation Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/19   CSAI            Y.Miyauchi      Create          N/A
 * 2016/07/29   Fujitsu         S.Yoshida       Update          QC#12627
 * 2016/08/04   Fujitsu         C.Tanaka        Update          QC#12825
 * 09/20/2016   CSAI            Y.Imazu         Update          QC#14435
 * 2016/10/20   Hitachi         K.Kojima        Update          QC#13335
 * 2016/11/02   Hitachi         E.Kameishi      Update          QC#14821
 * 2016/12/26   Hitachi         K.Kasai         Update          QC#16700
 * 2017/12/19   Hitachi         Y.Takeno        Update          QC#23022
 * 2018/02/20   Hitachi         Y.Takeno        Update          QC#21703
 * 2018/03/27   CITS            T.Kikuhara      Update          QC#20316
 * 2019/05/16   Hitachi         Y.Takeno        Update          QC#50204
 * 2019/08/20   Hitachi         Y.Takeno        Update          QC#52280
 * 2020/05/20   Fujitsu         H.Ikeda         Update          QC#565270
 *</pre>
 */
public class NFBB131001Constant {

    // START 2017/12/19 [QC#23022, MOD]
    /**
     * AP_VND_INV_SQ_NUM_DEF
     */
    public static final String AP_VND_INV_SQ_NUM_DEF = "00";
    // public static final String AP_VND_INV_SQ_NUM_DEF = "02";
    // END   2017/12/19 [QC#23022, MOD]

    // START 2017/02/20 [QC#21703, ADD]
    /**
     * AP_VND_TP_CD_02
     */
    public static final String AP_VND_TP_CD_02 = "02";
    // END   2017/02/20 [QC#21703, ADD]

    /**
     * DEFAULT_DB_NONE
     */
    public static final String DEFAULT_DB_NONE = "NONE";

    // START 2016/11/01 E.Kameishi [QC#14821, add]
    /**
     * COA_CONST_GRP_ID
     */
    public static final String COA_CONST_GRP_ID = "CM_CR_DR_RSN_ACCT";

    /**
     * COA_CONST_RESRC_ID
     */
    public static final String COA_CONST_RESRC_ID = "ALL";

    // END 2016/11/01 E.Kameishi [QC#14821, add]

    // START 2016/12/26 [QC#16700, ADD]
    /** Error Msg : Customer Issue PO Line Number is not entered. */
    public static final String NAZM0173E = "NAZM0173E";

    /** Error Msg : No Vendor Return data found. */
    public static final String NFBM0202E = "NFBM0202E";

    /** Error Msg : No Vendor Return Detail data found. */
    public static final String NFBM0203E = "NFBM0203E";

    /** Error Msg : Table : @ Insert Error. Return Code = @. */
    public static final String NFBM0073E = "NFBM0073E";

    /** Error Msg : Table : @ Update Error. Return Code = @ */
    public static final String NFBM0224E = "NFBM0224E";

    /** Error Msg : @ doesn't exist. */
    public static final String NFBM0044E = "NFBM0044E";

    /** Error Msg : Data does not exist. */
    public static final String NFCM0508E = "NFCM0508E";

    /** Error Msg : Table : CM_INV_ACCT_DIST Insert Error. */
    public static final String NFBM0270E = "NFBM0270E";

    /** Error Msg : Table : CM_INV_ACCT_HDR Insert Error. */
    public static final String NFBM0271E = "NFBM0271E";

    /** Error Msg : Table : CM_AP_INV_HDR Insert Error. */
    public static final String NFBM0275E = "NFBM0275E";

    /** Error Msg : Table : CM_AP_INV_DTL Insert Error. */
    public static final String NFBM0276E = "NFBM0276E";

    /** Error Msg : Table : CM_INV_ACCT_DIST was not found. */
    public static final String NFBM0277E = "NFBM0277E";

    // START 2019/08/20 [QC#52280, ADD]
    /** Error Msg : Vendor Invoice already exists in the AP Invoice. */
    public static final String NFBM0288E = "NFBM0288E";

    /** Error Msg : Pay Site is not found. */
    public static final String NFBM0289E = "NFBM0289E";
    // END   2019/08/20 [QC#52280, ADD]

    /** LENGTH: LEN_CM_INV_ACCT_DIST_LINE_NUM */
    public static final int LEN_CM_INV_ACCT_DIST_LINE_NUM = 3;

    // START 2018/03/28 [QC#20316, ADD]
    /** LENGTH: LEN_AP_VND_INV_LINE_NUM */
    public static final int LEN_AP_VND_INV_LINE_NUM = 4;
    // END 2018/03/28 [QC#20316, ADD]

    // END 2016/12/26 [QC#16700, ADD]

    // START 2019/05/16 [QC#50204, ADD]
    /** Interface ID: USDA0300 */
    public static final String INTERFACE_ID_CUSA_PARTS = "USDA0300";
    // END   2019/05/16 [QC#50204, ADD]

    public static enum VAR_CHAR_CONST {//
        NFBB131001_VND_CD_DEF, //
        NFBB131001_VND_CSMP, //
        NFBB131001_VND_GROWTH_BONUS, //
        NFBB131001_VND_INV_TP_CD_INV, //
        NFBB131001_VND_INV_TP_CD_CR, //
        NFBB131001_VND_CR_DR_RSN_TAX, //
        NFBB131001_VND_CR_DR_RSN_FRT, //
        NFBB131001_VND_CR_DR_RSN_RTRN
        // START 2020/05/20 [QC#56270, ADD]
        ,NFBB131001_EXTRA_COA_AFFL_CD
        // END   2020/05/20 [QC#56270, ADD]
        //
    }

    public static enum AR_PROC_FLG { //
        PROCESS("P"), //
        ERROR("E");

        /** value */
        private String value;

        private AR_PROC_FLG(String value) {
            this.value = value;
        }

        /**
         * @return
         */
        public String getValue() {
            return value;
        }
    }

    public static enum DTO_COL_NAME {//
        VND_INV_NUM, //
        ORIG_VND_INV_NUM, //
        INV_DT, //
        INV_TP_CD, //
        VND_CD, //
        INV_TOT_DEAL_NET_AMT, //
        INV_TOT_DEAL_SLS_AMT, //
        INV_TOT_DEAL_FRT_AMT, //
        INV_TOT_DEAL_TAX_AMT, //
        INV_TOT_DEAL_DISC_AMT, //
        INV_TOT_DEAL_INS_AMT, //
        CUST_ISS_PO_NUM, //
        PAYER_CUST_CD, //
        INV_TOT_PKG_QTY, //
        INV_TOT_GRS_WT, //
        VND_INV_BOL_LINE_NUM, //
        VND_INV_LINE_NUM, //
        VND_INV_LINE_SUB_NUM, //
        VND_INV_LINE_SUB_TRX_NUM, //
        VND_CPO_ORD_NUM, //
        VND_MDSE_CD, //
        MDSE_CD, //
        MDSE_NM, //
        ORD_QTY, //
        SHIP_QTY, //
        BO_QTY, //
        COUNT_TOTAL, //
        VND_PK, //
        PRNT_VND_PK, //
        // START 2016/10/20 K.Kojima [QC#13335,ADD]
        PRNT_VND_CD, //
        // END 2016/10/20 K.Kojima [QC#13335,ADD]
        INV_VND_CD, //
        SPLY_COA_CMPY_CD, //
        SPLY_COA_BR_CD, //
        SPLY_COA_CC_CD, //
        SPLY_COA_ACCT_CD, //
        SPLY_COA_PROD_CD, //
        SPLY_COA_CH_CD, //
        SPLY_COA_AFFL_CD, //
        SPLY_COA_PROJ_CD, //
        SPLY_COA_EXTN_CD;//
    }
}
