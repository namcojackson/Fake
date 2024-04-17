/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2550.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/28   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/11/24   Fujitsu         R.Nakamura      Update          QC#15438
 * 2017/03/15   Hitachi         E.Kameishi      Update          QC#15854
 *</pre>
 */
public class NMAL2550Constant {

    /** Screen ID */
    public static final String SCREEN_ID = "NMAL2550Scrn00";

    /** Business ID */
    public static final String APP_ID = "NMAL2550";

    /** Function Code */
    public static final String FUNCTION_CODE = "20";

    /** [@] does not exit. */
    public static final String NMAM8111E = "NMAM8111E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** MESSAGE KIND ERROR */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** Company **/
    public static final String NAME_FOR_MESSAGE_COMPANY = "Company";

    // Mod Start 2016/11/24 QC#15438
    /** Affiliate **/
//    public static final String NAME_FOR_MESSAGE_AFFILIATE = "Affiliate";
    public static final String NAME_FOR_MESSAGE_AFFILIATE = "Intercompany";
    // Mod End 2016/11/24 QC#15438

    /** Branch **/
    public static final String NAME_FOR_MESSAGE_BRANCH = "Branch";

    /** Cost Center **/
    public static final String NAME_FOR_MESSAGE_COST_CENTER = "Cost Center";

    /** Account **/
    public static final String NAME_FOR_MESSAGE_ACCOUNT = "Account";

    /** Product **/
    public static final String NAME_FOR_MESSAGE_PRODUCT = "Product";

    /** Channel **/
    public static final String NAME_FOR_MESSAGE_CHANNEL = "Channel";

    // Mod Start 2016/11/24 QC#15438
    /** Project **/
//    public static final String NAME_FOR_MESSAGE_PROJECT = "Project";
    public static final String NAME_FOR_MESSAGE_PROJECT = "Merchandise";

    /** Extension **/
//    public static final String NAME_FOR_MESSAGE_EXTENSION = "Extension";
    public static final String NAME_FOR_MESSAGE_EXTENSION = "Business Unit";
    // Mod End 2016/11/24 QC#15438

    /** COMPANY CODE */
    public static final String COMPANY_CODE = "Company Code";

    /** COMPANY NAME */
    public static final String COMPANY_NAME = "Company Name";

    // Mod Start 2016/11/24 QC#15438
    /** AFFILIATE CODE */
//    public static final String AFFILIATE_CODE = "Affiliate Code";
    public static final String AFFILIATE_CODE = "Intercompany Code";

    /** AFFILIATE NAME */
//    public static final String AFFILIATE_NAME = "Affiliate Name";
    public static final String AFFILIATE_NAME = "Intercompany Name";
    // Mod End 2016/11/24 QC#15438

    /** BRANCH CODE */
    public static final String BRANCH_CODE = "Branch Code";

    /** BRANCH NAME */
    public static final String BRANCH_NAME = "Branch Name";

    /** COST CENTER CODE */
    public static final String COST_CENTER_CODE = "Cost Center Code";

    /** COST CENTER NAME */
    public static final String COST_CENTER_NAME = "Cost Center Name";

    /** ACCOUNT CODE */
    public static final String ACCOUNT_CODE = "Account Code";

    /** ACCOUNT NAME */
    public static final String ACCOUNT_NAME = "Account Name";

    /** PRODUCT CODE */
    public static final String PRODUCT_CODE = "Product Code";

    /** PRODUCT NAME */
    public static final String PRODUCT_NAME = "Product Name";

    /** CHANNEL CODE */
    public static final String CHANNEL_CODE = "Channel Code";

    /** CHANNEL NAME */
    public static final String CHANNEL_NAME = "Channel Name";

    // Mod Start 2016/11/24 QC#15438
    /** PROJECT CODE */
//    public static final String PROJECT_CODE = "Project Code";
    public static final String PROJECT_CODE = "Merchandise Code";

    /** PROJECT NAME */
//    public static final String PROJECT_NAME = "Project Name";
    public static final String PROJECT_NAME = "Merchandise Name";

    /** EXTENSION CODE */
//    public static final String EXTENSION_CODE = "Extension Code";
    public static final String EXTENSION_CODE = "Business Unit Code";

    /** EXTENSION NAME */
//    public static final String EXTENSION_NAME = "Extension Name";
    public static final String EXTENSION_NAME = "Business Unit Name";
    // Mod End 2016/11/24 QC#15438

    /** COMPANY CODE */
    public static final int COMPANY = 0;

    /** AFFILIATE CODE */
    public static final int AFFILIATE = 1;

    /** BRANCH CODE */
    public static final int BRANCH = 2;

    /** COST CENTER CODE */
    public static final int COST_CENTER = 3;

    /** ACCOUNT CODE */
    public static final int ACCOUNT = 4;

    /** PRODUCT CODE */
    public static final int PRODUCT = 5;

    /** CHANNEL CODE */
    public static final int CHANNEL = 6;

    /** PROJECT CODE */
    public static final int PROJECT = 7;

    /** EXTENSION CODE */
    public static final int EXTENSION = 8;

    /** COA_CMPY_CD */
    public static final String COA_CMPY_CD = "COA_CMPY_CD";

    /** COA_AFFL_CD */
    public static final String COA_AFFL_CD = "COA_AFFL_CD";

    /** COA_BR_CD */
    public static final String COA_BR_CD = "COA_BR_CD";

    /** COA_CC_CD */
    public static final String COA_CC_CD = "COA_CC_CD";

    /** COA_ACCT_CD */
    public static final String COA_ACCT_CD = "COA_ACCT_CD";

    /** COA_PROD_CD */
    public static final String COA_PROD_CD = "COA_PROD_CD";

    /** COA_CH_CD */
    public static final String COA_CH_CD = "COA_CH_CD";

    /** COA_PROJ_CD */
    public static final String COA_PROJ_CD = "COA_PROJ_CD";

    /** COA_EXTN_CD */
    public static final String COA_EXTN_CD = "COA_EXTN_CD";

    //START 2017/03/15 E.Kameishi [QC#15854,ADD]
    /** Filter Button **/
    public static final String FILTER_BTN = "Filter";
    //END 2017/03/15 E.Kameishi [QC#15854,ADD]

    /**
     * << button An attribute
     */
    public enum SETTING_BTN {
        /** COMPANY **/
        COMPANY("Company_Setting")
        /** AFFILIATE **/
        , AFFILIATE("Affiliate_Setting")
        /** BRANCH **/
        , BRANCH("Branch_Setting")
        /** COST_CENTER **/
        , COST_CENTER("CostCenter_Setting")
        /** ACCOUNT **/
        , ACCOUNT("Account_Setting")
        /** PRODUCT **/
        , PRODUCT("Product_Setting")
        /** CHANNEL **/
        , CHANNEL("Channel_Setting")
        /** PROJECT **/
        , PROJECT("Project_Setting")
        /** EXTENSION **/
        , EXTENSION("Extension_Setting");

        /** name **/
        private String name;

        private SETTING_BTN(String name) {
            this.name = name;
        }

        /**
         * @return String
         */
        public String getName() {
            return name;
        }
    }

    /**
     * Common button An attribute
     */
    public enum CMN_BTN {

        /** CLEAR **/
        CLEAR("btn8", "CMN_Clear", "Clear"), CLOSE("btn10", "CMN_Close", "Close");

        /** htmlNm **/
        private String htmlNm;

        /** eventNm **/
        private String eventNm;

        /** label **/
        private String label;

        private CMN_BTN(String htmlNm, String eventNm, String label) {
            this.htmlNm = htmlNm;
            this.eventNm = eventNm;
            this.label = label;
        }

        /**
         * @return String
         */
        public String getHtmlNm() {
            return htmlNm;
        }

        /**
         * @return String
         */
        public String getEventNm() {
            return eventNm;
        }

        /**
         * @return String
         */
        public String getLabel() {
            return label;
        }
    }
}
