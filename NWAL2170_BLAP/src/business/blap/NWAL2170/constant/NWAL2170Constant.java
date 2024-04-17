/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2170.constant;

/**
 *<pre>
 * NWAL2170Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/17   Fujitsu         M.Yamada        Create          N/A
 * 2018/03/12   Fujitsu         K.Ishizuka      Update          S21_NA#24090
 * 2018/04/11   Fujitsu         A.Kosai         Update          QC#10374
 * 2023/11/06   Hitachi         H.Watanabe      Update          QC#61924
 *</pre>
 */
public class NWAL2170Constant {

    // --------------------------------
    // Message ID
    // --------------------------------
    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    /** NWAM0181E : The entered @ does not exist in Master. */
    public static final String NWAM0181E = "NWAM0181E";

    /** <pre>NWAM0729E : Data update failure. (table name is [@])</pre> */
    public static final String NWAM0729E = "NWAM0729E";

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

    /** SQL_ID : 506 */ // 2018/03/12 S21_NA#24090 Mod
    // public static final String SQLID_GET_SELL_TO_CUST_FOR_NAME = "200";
    public static final String SQLID_GET_SELL_TO_CUST_FOR_NAME = "506";

    /** SQL_ID : 507 */ // 2018/03/12 S21_NA#24090 Mod
    // public static final String SQLID_GET_SELL_TO_CUST_FOR_CODE = "201";
    public static final String SQLID_GET_SELL_TO_CUST_FOR_CODE = "507";

    // 2018/04/11 QC#10374 Add Start
    /** Shell Mode Code : 1 */
    public static final String NWAL2370_SHELL_MODE_CD = "1";

    /** Import Mode Code : 2 */
    public static final String NWAL2370_IMPT_MODE_CD = "2";
    // 2018/04/11 QC#10374 Add End

    // 2023/11/06 QC#61924 Add Start
    /** Sold To# {@} is deactivated for new transactions. */
    public static final String NWAM0989E = "NWAM0989E";

    /** Account# {@} is deactivated for new transactions. */
    public static final String NWAM0990E = "NWAM0990E";
    // 2023/11/06 QC#61924 Add End

}
