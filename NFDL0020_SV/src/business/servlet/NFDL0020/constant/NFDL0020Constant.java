package business.servlet.NFDL0020.constant;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2021/09/17   CITS            G.Delgado       Update          QC#59070
 *</pre>
 */
public class NFDL0020Constant {

    // START 2018/07/09 J.Kim [QC#26801, ADD]
    /** Business ID */
    public static final String BIZ_ID = "NFDL0020";

    /**
     * Screen Display Mode : Read-Only
     */
    public static String DISPLAY_MODE_READ_ONLY = "Read-Only";

    /**
     * Screen Display Mode : Modification
     */
    public static String DISPLAY_MODE_MODIFICATION = "Modification";
    // END 2018/07/09 J.Kim [QC#26801, ADD]

    // START 2019/02/12 S.Ohki [QC#30143,ADD]
    /** Screen ID */
    public static final String SCRN_ID_00 = "NFDL0020Scrn00";
    // END 2019/02/12 S.Ohki [QC#30143,ADD]

    // START 2021/09/17 G.Delgado [QC#59070,ADD]
    /**
     * Collection Work Item Codes for Dunning Letters
     */
    public static enum DUN_LTR_WRK_ITEM_CD {
        /** Canon First Dunning Letter */
        FIRST("22", "01"),
        /** Canon Second Dunning Letter */
        SECOND("23", "02"),
        /** Canon Third Dunning Letter */
        THIRD("24", "03"),
        /** CANON FINAL DUNNING LETTER NOTICE */
        FINAL("25", "04");

        /** Values list */
        private List<String> values;

        private DUN_LTR_WRK_ITEM_CD(String... values) {
            this.values = Collections.unmodifiableList(Arrays.asList(values));
        }

        /**
         * Get work item code values
         * @return values List<String>
         */
        public List<String> getValues() {
            return this.values;
        }

        /**
         * Get corresponding DUN_LTR_WRK_ITEM_CD enum
         * @param wrkItemCd String
         * @return DUN_LTR_WRK_ITEM_CD if dunning letter work item, else null
         */
        public static DUN_LTR_WRK_ITEM_CD fromValue(String wrkItemCd) {
            for (DUN_LTR_WRK_ITEM_CD item : DUN_LTR_WRK_ITEM_CD.values()) {
                if (item.values.contains(wrkItemCd)) {
                    return item;
                }
            }
            return null;
        }
    }

    /**
     * The same Work Item with Open / Pending Status exists.
     */
    public static final String NFDM0057E = "NFDM0057E";
    // END 2021/09/17 G.Delgado [QC#59070,ADD]
}
