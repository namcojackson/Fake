package com.canon.cusa.s21.common.NWX.NWXC220001.constant;

/**
 * <pre>
 * NWXC220001Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/24   Fujitsu         M.Hara          Create          N/A
 * 2016/11/02   Fujitsu         T.Ishii         Update          S21_NA#14815
 * 2017/01/06   Fujitsu         S.Ohki          Update          S21_NA#16845
 * 2017/10/25   CITS            T.Gotoda        Update          S21_NA#22013
 * 2017/11/10   Fujitsu         S.Takami        Update          S21_NA#22464
 * </pre>
 */
public class NWXC220001Constant {

    /** DS_EDI_TRD_PTNR_REF_CD(ARC) */
    public static final String DS_EDI_TRD_PTNR_REF_CD_ARC = "ARC";

    /** DS_EDI_TRD_PTNR_REF_CD(MG) */
    public static final String DS_EDI_TRD_PTNR_REF_CD_MG = "MG";

    /** DS_EDI_TRD_PTNR_REF_CD(JPMC) */
    public static final String DS_EDI_TRD_PTNR_REF_CD_JPMC = "JPMC";

    /** DS_EDI_TRD_PTNR_REF_CD(NCR) */
    public static final String DS_EDI_TRD_PTNR_REF_CD_NCR = "NCR";

    /** EDI Sold To Attribute3(PARTN) */
    public static final String EDI_CUST_ATTRB3_PARTN = "PARTN";

    /** EDI Sold To Attribute3(IHREZ) */
    public static final String EDI_CUST_ATTRB3_IHREZ = "IHREZ";

    /** EDI Sold To Attribute3(LIFNR) */
    public static final String EDI_CUST_ATTRB3_LIFNR = "LIFNR";

    /** EDI Sold To Attribute2 */
    public static final String EDI_SOLD_TO_ATTRB2 = "E2EDKA1 AG";

    /** EDI Bill To Attribute2 */
    public static final String EDI_BILL_TO_ATTRB2 = "E2EDKA1 RE";

    /** EDI Ship To Attribute2 */
    public static final String EDI_SHIP_TO_ATTRB2 = "E2EDKA1 WE";

    /** EDI FRT_COND_CD Attribute2 */
    public static final String EDI_FRT_COND_CD_ATTRB2 = "E2EDK17 001";

    /** EDI FRT_COND_CD Attribute3 */
    public static final String EDI_FRT_COND_CD_ATTRB3 = "LKOND";

    /** EDI SVC_LVL_CD Attribute2 */
    public static final String EDI_SVC_LVL_CD_ATTRB2 = "E2EDKA1 SP";

    /** EDI CARR_ACCT_NUM Attribute2 */
    public static final String EDI_CARR_ACCT_NUM_ATTRB2 = "E2EDKA1 SP";

    /** EDI CUST_UOM_CD Attribute2 */
    public static final String EDI_CUST_UOM_CD_ATTRB2 = "E2EDP01";

    /** EDI CUST_UOM_CD Attribute3 */
    public static final String EDI_CUST_UOM_CD_ATTRB3 = "MENEE";

    /** EDI DROP_SHIP_FLG ARC Text */
    public static final String EDI_DROP_SHIP_FLG_ARC_TXT = "OVERRIDE OF SHIP TO EXIST";

    /** EDI IDOC_PO_PTNR_TP_AP */
    public static final String EDI_IDOC_PO_PTNR_TP_AP = "AP";

    /** EDI IDOC_PO_PTNR_TP_YP */
    public static final String EDI_IDOC_PO_PTNR_TP_YP = "YP";

    // Mod Start 2017/10/25 QC#22013
    /** EDI CONTACT_MAPPING Attribute2(AP) */
    public static final String EDI_CONTACT_MAPPING_ATTRB2_AP = "E2EDKA1 AP";

    /** EDI CONTACT_MAPPING Attribute2(YP) */
    public static final String EDI_CONTACT_MAPPING_ATTRB2_YP = "E2EDKA1 YP";
    // Mod End 2017/10/25 QC#22013

    /** EDI CONTACT_MAPPING Attribute3 */
    public static final String EDI_CONTACT_MAPPING_ATTRB3 = "LIFNR";

    /** 0 to Z */
    public static final String ZERO_TO_Z = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /** last number : 1000 */
    public static final int THOUSAND = 1000;

    /** 999 + (26*36*36) = 34695 */
    public static final int MAX_LINE_NUM = 34695;

    /** A to Z */
    public static final String A_TO_Z = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /** First String : A00 */
    public static final String FIRST_STR = "A00";

    /** Base Number : 36 */
    public static final int BASE_NUMBER_36 = 36;

    /** BASE_MDSE */
    public static final String BASE_MDSE = "BASE_MDSE";

    /** CHILD_MDSE */
    public static final String CHILD_MDSE = "CHILD_MDSE";

    /**
     * Message IDs
     */
    public static enum MSG_ID {
        /** DB error occurred. */
        NMAM0283E,
        /** It does not exist in @} table. */
        NWAM0809E,
        /** "Customer P/O Number" is duplicated. */
        NWAM0810E,
        /** Does not exist in the Merchandise Master. */
        NWAM0104E,
        /** Failed to mapping of '@'. */
        NWAM0826E,
        /** Not found record. */
        ZZXL0002E,
        /** [@] is not found. */
        ZZZM9006E,
        /** [@] is mandatory. */
        ZZZM9025E,
        /**
         * The target data has already been processed. CPO#@,
         * Reference#@
         */
        NWAM2200E,
        /**
         * The relationship between 'Merchandise Code' and 'Customer
         * Merchandise Code' is incorrect.
         */
        NWZM1468E, // S21_NA#14815
        /**
         * Customer Item does not exist in the Customer Merchandise Cross Reference.
         */
        NWZM2205E, // S21_NA#16845
        /** The system was unable to specify one item from the master data. Please choose one item. */
        NWZM2249E, // S21_NA#22464
    }

    public static enum CUSTOMER_TABLE_ID {
        ALL, BILL_TO_CUST, SHIP_TO_CUST, SELL_TO_CUST,
    }
}
