/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7080.constant;

/**
 *<pre>
 * Supply Agreement Plan Set Up
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/15   Fujitsu         M.Ohno          Create          N/A
 * 2016/05/06   SRAA            K.Aratani       Update          QC#8012
 * 2016/10/14   Fujitsu         M.Ohno          Update          S21_NA#13253
 *</pre>
 */
public class NMAL7080Constant {
    /** Active */
    public static final String STS_ACTIVE = "Active";

    /** Inactive */
    public static final String STS_INACTIVE = "Inactive";

    /** Deleted */
    public static final String STS_DELETED = "Deleted";

    /** [@] should be unique. */
    public static final String NMAM8296E = "NMAM8296E";

    /** Please entry 8 digits merchandise code. */
    public static final String NMAM8216E = "NMAM8216E";

    /** The entered [@] does not exist in [@]. */
    public static final String NMAM0163E = "NMAM0163E";

    /** @ is duplicated. */
    public static final String NMAM0072E = "NMAM0072E";

    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** It failed to register [@]. */
    public static final String NMAM0176E = "NMAM0176E";

    /** It failed to update [@]. */
    public static final String NMAM0177E = "NMAM0177E";

    /** Document Type [@]need to select [@] */
    public static final String NMAM8393E = "NMAM8393E";

    /** Document Type [@] can't select [@] */
    public static final String NMAM8394E = "NMAM8394E";

    /** Please select item(s). */
    public static final String NMAM8054E = "NMAM8054E";

    /** Remove the selected records. is it OK? */
    public static final String NMAM8234I = "NMAM8234I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    // 2016/10/14 S21_NA#13253 Add
    /** This Supply Plan is linked to Active Price List(s). Is it OK? */
    public static final String NMAM8656W = "NMAM8656W";

    /** Supply Agreement Plan */
    public static final String SPLY_AGMT_PLN_TABLE_NAME = "Supply Agreement Plan";

    /** Supply Agreement Plan Detail */
    public static final String SPLY_AGMT_PLN_DTL_TABLE_NAME = "Supply Agreement Plan Detail";

    /** Check Box Name */
    public static final String XX_CHKBOX_NAME = "xxChkBox_A";

    /** CSV file name */
    public static final String CSV_FILE_NM = "NMAL7080AgreementPlanDetailList";

    /** CSV file extension */
    public static final String CSV_FILE_EXTENSION = ".csv";

    /** CSV header upload */
    public static final String[] CSV_HEADER = new String[] {"Plan ID", //
            "Plan Name", //
            "Plan Short Name", //
            "Plan Type", //
            "Document Type", //
            "Annual Term Cap", //
            "Header Effective From", //
            "Header Effective Thru", //
            "Active", //
            "Item Code", //
            "Item Description", //
            "Frequency", //
            "First Quantity", //
            "Quantity", //
            "Sequence#", //
            "Effective From", //
            "Effective Thru" };

    /** CHK_ATTRB_NM_LIST */
    public static final String[] CHK_ATTRB_NM_LIST = new String[] {"mdseCd_A", "splyAgmtFreqTpCd_A", "splyAgmtPlnFirstQty_A", "splyAgmtPlnQty_A", "splyAgmtPlnSqNum_A", "effFromDt_A" };

    //QC#8012
    /** GRP_ATTRB_NM_LIST */
    //public static final String[] GRP_ATTRB_NM_LIST = new String[] {"mdseCd_A" };
    public static final String[] GRP_ATTRB_NM_LIST = new String[] {"mdseCd_A", "splyAgmtFreqTpCd_A", "splyAgmtPlnFirstQty_A", "splyAgmtPlnQty_A", "splyAgmtPlnSqNum_A" };

    /** Date format length */
    public static final int YYYYMMDD_LENGTH = 8;

}
