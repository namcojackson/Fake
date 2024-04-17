/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1320.constant;

/**
 *<pre>
 * NSAL1320Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/19   Hitachi         Y.Takeno        Create          N/A
 * 2017/06/20   Hitachi         K.Kojima        Update          QC#19053
 * 2017/07/24   Hitachi         Y.Takeno        Update          QC#20055
 * 2017/07/28   Hitachi         Y.Takeno        Update          QC#20088
 * 2017/08/16   Hitachi         Y.Takeno        Update          QC#20378
 * 2017/09/20   Fujitsu         S.Fujita        Update          QC#18534
 * 2018/04/16   Fujitsu         A.Kosai         Update          QC#20162
 * 2018/04/16   Fujitsu         A.Kosai         Update          QC#10374
 * 2018/05/07   Fujitsu         A.Kosai         Update          QC#22482
 * 2018/11/15   Hitachi         K.Kim           Update          QC#28638
 * 2019/11/29   Hitachi         K.Kitachi       Update          QC#53682
 * 2023/07/05   CITS            T.Kojima        Update          QC#61472
 * 2023/11/06   Hitachi         H.Watanabe      Update          QC#61924
 * 2023/11/08   CITS            R.Jin           Update          QC#62108
 * 2024/03/12   CITS            M.Kuroi         Update          QC#63638
 *</pre>
 */
public class NSAL1320Constant {

    // --------------------------------
    // Message ID
    // --------------------------------
    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /**
     * <pre>There are too many search results, there is data that cannot be displayed.</pre>
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** NSAM0628E :[@] already exists. Cannot add. */
    public static final String NSAM0628E = "NSAM0628E";

    /** NSAM0630E : Select at least one of [@]. */
    public static final String NSAM0630E = "NSAM0630E";

    /** NSAM0627E : The entered @ does not exist in Master. */
    public static final String NSAM0627E = "NSAM0627E";

    /** NSAM0035E : [@] is duplicated. */
    public static final String NSAM0035E = "NSAM0035E";

    /** NSAM0631E : You can not delete all the lines. */
    public static final String NSAM0631E = "NSAM0631E";

    /** <pre>NSAM0064E : The chronological sequence between the dates in the "@" field is wrong.</pre> */
    public static final String NSAM0064E = "NSAM0064E";

    /** <pre>NSAM0138E : Relation between "@" and "@" is not correct.</pre> */
    public static final String NSAM0138E = "NSAM0138E";

    /** <pre>NSAM0632E : There is an error in the continuity of the term.</pre> */
    public static final String NSAM0632E = "NSAM0632E";

    /** <pre>NSAM0634E : At first, please delete the all detail.</pre> */
    public static final String NSAM0634E = "NSAM0634E";

    /** <pre>NSAM0635E : Data update failure. (table name is [@])</pre> */
    public static final String NSAM0635E = "NSAM0635E";

    /** <pre>NSAM0636E : @ can't be determined. Please contact system administrator.</pre> */
    public static final String NSAM0636E = "NSAM0636E";

    /** <pre>NSAM0638E : Please input @ when you input the value to @.</pre> */
    public static final String NSAM0638E = "NSAM0638E";

    // START 2017/07/21 [QC#20055, ADD]
    /** The combination of [@] and [@] is incorrect. */
    public static final String NSZM0698E = "NSZM0698E";

    // END 2017/07/21 [QC#20055, ADD]

    // START 2017/08/16 [QC#20378, ADD]
    public static final String NSAM0695W = "NSAM0695W";

    // END 2017/08/16 [QC#20378, ADD]

    // START 2017/10/25 [QC#21556, ADD]
    /** You can not enter [@] later than [@]. */
    public static final String NSAM0347E = "NSAM0347E";

    // END 2017/10/25 [QC#21556, ADD]

    // START 2018/11/15 [QC#28638, ADD]
    /** Fixed months exceeds the contract term. */
    public static final String NSAM0742E = "NSAM0742E";

    // END 2018/11/15 [QC#28638, ADD]

    // START 2019/11/29 K.Kitachi [QC#53682, ADD]
    /**
     * This item cannot be added to the contract. Please confirm the
     * relation between the contract and the configuration.
     */
    public static final String NSAM0755E = "NSAM0755E";

    // END 2019/11/29 K.Kitachi [QC#53682, ADD]

    // START 2023/11/08 R.Jin [QC#62108, DEL]
    // START 2023/07/05 T.Kojima [QC#61472, ADD]
    // /** Contract detail status associated with entered contract#
    // and Config ID is inactive. Please reject this order and
    // correct/resend it from Deal Config. */
    // public static final String NSZM1406E = "NSZM1406E";
    //
    // /** Contract detail status associated with entered contract#
    // and Config ID is inactive. Please change the contract#. */
    // public static final String NSAM0778E = "NSAM0778E";
    // END 2023/07/05 T.Kojima [QC#61472, ADD]
    // END 2023/11/08 R.Jin [QC#62108, DEL]

    // 2023/11/06 QC#61924 Add Start
    /** Bill To# @} is deactivated for new transactions. */
    public static final String NSAM0781E = "NSAM0781E";

    /** Account# @} is deactivated for new transactions. */
    public static final String NSAM0782E = "NSAM0782E";

    // 2023/11/06 QC#61924 Add End

    /** DEL_DTL */
    public static final String DEL_DTL = "D";

    /** DEL_SHELL */
    public static final String DEL_SHELL = "S";

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

    // START 2017/06/20 K.Kojima [QC#19053,ADD]
    /** Usage Line Type : Parent */
    public static final String XX_FLG_PARENT = "P";

    /** Usage Line Type : Tier */
    public static final String XX_FLG_TIER = "T";

    /** Usage Line Type : Hard Counter */
    public static final String XX_FLG_HARD = "H";

    // END 2017/06/20 K.Kojima [QC#19053,ADD]

    // START 2017/07/28 [QC#20088, ADD]
    public static final String SVC_MEMO_TRX_NM_CPO_ORD_NUM = "CPO_ORD_NUM";

    // END 2017/07/28 [QC#20088, ADD]

    // START 2017/09/20 S.Fujita [QC#18534,ADD]
    /**
     * "CSA Warranty" cannot be specified as Service Program for Fleet
     * and Aggregate Contract.
     */
    public static final String NSAM0698E = "NSAM0698E";

    // END 2017/09/20 S.Fujita [QC#18534,ADD]

    // START 2018/04/16 A.Kosai [QC#20162,ADD]
    /** Please input @ and @ when you input the value to @. */
    public static final String NSAM0716E = "NSAM0716E";

    // END 2018/04/16 A.Kosai [QC#20162,ADD]

    // START 2018/04/16 A.Kosai [QC#10374,ADD]
    /** Shell Mode Code : 1 */
    public static final String NWAL2370_SHELL_MODE_CD = "1";

    /** Shell Mode Code : 2 */
    public static final String NWAL2370_IMPT_MODE_CD = "2";

    // END 2018/04/16 A.Kosai [QC#10374,ADD]

    // 2018/05/07 QC#22482 Add Start
    /** Manual Control Override Comment Text Length */
    public static final int MAN_CONTR_OVRD_CMNT_TXT_LENGTH = 2000;

    // 2018/05/07 QC#22482 Add End

    // 2024/03/12 QC#63638 Add Start
    /**
     * DI Check error has occurred. Please check it on DI Check
     * Screen.
     */
    public static final String NSAM0789W = "NSAM0789W";

    /**
     * Shipping Status has been changed. Please re-open the screen and
     * try again.
     */
    public static final String NSAM0792E = "NSAM0792E";

    /** Business ID */
    public static final String BIZ_ID = "NSAL1320";
    // 2024/03/12 QC#63638 Add End

}
