package com.canon.cusa.s21.batch.NWA.NWAB265001.constant;

/** <pre>
 * NWAB265001constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/04/2016   Fujitsu         M.Hara          Create          N/A
 * </pre>
 */
public class NWAB265001constant {

    /** Program ID */
    public static final String PROGRAM_ID           = "NWAB2650";

    /** DS_EDI_TRD_PTNR_REF_CD(MG) */
    public static final String INTERFACE_ID_MG      = "NWAI1200";

    /** DS_EDI_TRD_PTNR_REF_CD(ARC) */
    public static final String INTERFACE_ID_ARC     = "NWAI1210";

    /** DS_EDI_TRD_PTNR_REF_CD(NCR) */
    public static final String INTERFACE_ID_NCR     = "NWAI1220";

    /** DS_EDI_TRD_PTNR_REF_CD(JPMC) */
    public static final String INTERFACE_ID_JPMC    = "NWAI1230";


    /** IDOC_PO_PTNR_TP_CD_AG */
    public static final String IDOC_PO_PTNR_TP_CD_AG    = "AG";

    /** IDOC_PO_PTNR_TP_CD_RE */
    public static final String IDOC_PO_PTNR_TP_CD_RE    = "RE";

    /** IDOC_PO_PTNR_TP_CD_WE */
    public static final String IDOC_PO_PTNR_TP_CD_WE    = "WE";

    /** IDOC_PO_PTNR_TP_CD_SP */
    public static final String IDOC_PO_PTNR_TP_CD_SP    = "SP";

    /** IDOC_PO_PTNR_TP_CD_AP */
    public static final String IDOC_PO_PTNR_TP_CD_AP    = "AP";

    /** IDOC_PO_PTNR_TP_CD_YP */
    public static final String IDOC_PO_PTNR_TP_CD_YP    = "YP";

    /** IDOC_PO_PTNR_TP_CD_ZG */
    public static final String IDOC_PO_PTNR_TP_CD_ZG    = "ZG";

    /** Mail Group ID (From) */
    public static final String MAIL_GRP_ID_FROM         = "FROM0005";

    /** yyyyMMddHHmmssSSS */
    public static final String YYYYMMDDHHMNSSFFF = "yyyyMMddHHmmssSSS";

    /**
     *  Message IDs
     *
     */
    public static enum MSG_ID {
        /** Access: [@] to Table: [@] failed. */
        NMAM8010E,
        /** No data found. [Table Name : @, PKey : @] */
        NWAM0525E,
        /** It does not exist in {@} table. */
        NWAM0809E,
        /** [@] is mandatory. */
        ZZZM9025E,
    }
}
