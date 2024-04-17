/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2190.constant;

import java.math.BigDecimal;

/**
 *<pre>
 *
 * Supply Agreement Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/12/02   Fujitsu         S.Iidaka        Create          N/A
 *</pre>
 */
public class NWAL2190Constant {
    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    /** No search results found. */
    public static final String ZZZM9005W = "ZZZM9005W";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** Search ended normally but not all data is displayed since the result exceeded the maximum.</pre> */
    public static final String NWAM0007W = "NWAM0007W";

    /** It failed to update [@]. */
    public static final String NMAM0177E = "NMAM0177E";

    /** Document Type [@] need to select @ */
    public static final String NWAM0933E = "NWAM0933E";

    /** Document Type [@] can't select @ */
    public static final String NWAM0934E = "NWAM0934E";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** Scheduling Create Template */
    public static final String SCHD_CRAT_TMPL_TABLE_NAME = "Scheduling Create Template";

    /** Scheduling Create Template Line */
    public static final String SCHD_CRAT_TMPL_LINE_TABLE_NAME = "Scheduling Create Template Line";

    /** number of months of one year */
    public static final BigDecimal MTH_12 = BigDecimal.valueOf(12);

    /** */
    public static enum XX_PAGE {
        PAGE_SHELL("S", "Order Number") //
        , PAGE_IMPT("I", "Source Reference#");

        private final String code;

        private final String name;

        private XX_PAGE(final String code, final String name) {
            this.code = code;
            this.name = name;
        }

        /** */
        public String getCode() {
            return code;
        }

        /** */
        public String getName() {
            return name;
        }
    }
}
