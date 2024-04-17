package com.canon.cusa.s21.batch.NWA.NWAB221001.constant;

/** <pre>
 * NWAB221001Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/17/2016   Fujitsu         M.Hara          Create          N/A
 * </pre>
 */
public class NWAB221001Constant {

    /** Program ID */
    public static final String PROGRAM_ID = "NWAB2210";

    /** Mail Group ID (From) */
    public static final String CST_MAIL_GRP_ID_FROM         = "FROM0005";

    /**
     *  Message IDs
     *
     */
    public static enum MSG_ID {
        /** DB error occurred. */
        NMAM0283E,
        /** @ is mandatory value. */
        NWAM0298E,
        /** It doesn't exist [@] master. [@]. */
        NWAM0403E,
        /** It does not exist in {@} table. */
        NWAM0809E,
        /** No data found. [Table Name : @, PKey : @] */
        NWAM0525E,
        /** Data insert failure. (table name is [@]) */
        NWAM0728E,
        /** Data update failure. (table name is [@]) */
        NWAM0729E,
        /** This record cannot be imported because  header Status is not "Saved". */
        NWAM0798E,
        /** CPO Order Number does not exist. */
        NWAM0799E,
        /** The order was already cancelled or closed. */
        NWAM0862E,
        /** This order status could not be processed because of the '@'. */
        NWAM0863E,
        /** The corresponding data does not exist in "XTRNL_INTFC_XREF". */
        NWZM1906E,
        /** [@] is mandatory. */
        ZZZM9025E,
        /** No search results found. */
        ZZOM0011E,
        /** Base Price could not be obtained.{@}, {@}. */
        NWZM1328E,   // 10/112/2016 S21_NA#12145 add
        /** Scheduling Agreement Creation is skipped since Supply Agreement Doc Type is not SA. (DS_IMPT_SCHD_TMPL_PK=@) */
        NWAM0902E,    // QC#15187 2016/10/12 Add
        /** Some problem occurred, it failed to register the order. */
        NWZM2200E,
    }

}
