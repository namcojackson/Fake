/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0490.constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/09   Fujitsu         T.Yoshida       Create          N/A
 * 2015/10/07   Hitachi         Y.Tsuchimoto    Update          N/A
 * 2016/03/15   Hitachi         M.Gotou         Update          QC#5081
 * 2016/04/22   Hitachi         T.Tomita        Update          QC#5407
 * 2016/05/09   Hitachi         A.Kohinata      Update          QC#8055
 * 2016/05/19   Hitachi         K.Kasai         Update          QC#447, 6675
 * 2017/08/01   CITS            S.Endo          Update          Sol#072(QC#18386)
 * 2018/02/16   Hitachi         M.Kidokoro      Update          QC#24249
 * 2024/03/14   Hitachi         K.Watanabe      Update          QC#63542
 *</pre>
 */
public class NSAL0490Constant {

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** Failed to update "@". */
    public static final String NSAM0031E = "NSAM0031E";

    /** Failed to insert "@". */
    public static final String NSAM0032E = "NSAM0032E";

    /** @ does not exist in @. */
    public static final String NSAM0063E = "NSAM0063E";

    /** @ has been already registered. */
    public static final String NSAM0079E = "NSAM0079E";

    /** @ cannot be added because it is exceeding the maximum number of row [@]. */
    public static final String NSAM0320E = "NSAM0320E";

    /** The selected record has already been registered and cannot be deleted. */
    public static final String NSAM0321E = "NSAM0321E";

    /** Parent item line is only one line and cannot be deleted. */
    public static final String NSAM0322E = "NSAM0322E";

    /** For "@", please enter a date earlier than "@". */
    public static final String NSAM0323E = "NSAM0323E";

    /** Duplicate records exist. [ @ ] */
    public static final String NSAM0324E = "NSAM0324E";

    /** Parent item periods must include Child item periods. Please correct it. */
    public static final String NSAM0325E = "NSAM0325E";

    /** The combination of MDSE Code has been registered in @ model. Please select a different combination. */
    public static final String NSAM0326E = "NSAM0326E";

    //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) START
    /** [@] is not selected. */
    public static final String NSAM0199E = "NSAM0199E";
    //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) END

    /** The process [@] has been successfully completed. */
    public static final String ZZZM9003I = "ZZZM9003I";

    /** This data has been updated by another user. */
    public static final String ZZZM9004E = "ZZZM9004E";

    // add start 2016/05/19 CSA Defect#447
    /** Failed to delete "@". */
    public static final String NSAM0033E = "NSAM0033E";
    // add end 2016/05/19 CSA Defect#447

    // 2015/10/07 CSA Y.Tsuchimoto Add Start
    /** Data of the machine only does not exist. */
    public static final String NSAM0365E = "NSAM0365E";

    /** ORD_TAKE_MDSE_CD DIGIT */
    public static final int ORD_TAKE_MDSE_CD_DIGIT = 8;
    // 2015/10/07 CSA Y.Tsuchimoto Add End

    // add start 2016/04/22 CSA Defect#5407
    /** This Mdse Code exists in Americas Mercury. Please enter 8-digit merchandise code. */
    public static final String NSAM0460E = "NSAM0460E";
    // add end 2016/04/22 CSA Defect#5407

    //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) START
    /** Model item's merchandise type is incorrect. */
    public static final String NSAM0029E = "NSAM0029E";
    //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) END

    // START 2024/03/14 K.Watanabe [QC#63542, ADD]
    /** [@] does not exist in Master. */
    public static final String NSAM0011E = "NSAM0011E";
    // END   2024/03/14 K.Watanabe [QC#63542, ADD]

    /** Process Name (Submit) */
    public static final String SUBMIT = "Submit";

    // START 2018/02/16 M.Kidokoro [QC#24249,ADD]
    /** Message Parameter (Parent item line) */
    public static final String PARENT_ITEM_LINE = "Parent item line";
    // END 2018/02/16 M.Kidokoro [QC#24249,ADD]

    /** Message Parameter (Child item line) */
    public static final String CHILD_ITEM_LINE = "Child item line";

    /** Message Parameter (Model Name) */
    public static final String MDL_NM = "Model Name";

    /** Message Parameter (Model Group) */
    public static final String MDL_GRP = "Model Group";

    // add start 2016/03/15 CSA Defect#5081
    /** Message Parameter (Model Group Master) */
    public static final String MDL_GRP_MSTR = "Model Group Master";
    // add end 2016/03/15 CSA Defect#5081

    /** Message Parameter (Mdse Code) */
    public static final String MDSE_CD = "Mdse Code";

    /** Message Parameter (Child Mdse Code) */
    public static final String CHILD_MDSE_CD = "Child Mdse Code";

    /** Message Parameter (Mdse) */
    public static final String MDSE = "Mdse";

    /** Message Parameter (DS Model) */
    public static final String DS_MDL = "DS Model";

    /** Message Parameter (DS Model Configuration) */
    public static final String DS_MDL_CONFIG = "DS Model Configuration";

    /** Message Parameter (DS Model Supply Relation) */
    public static final String DS_MDL_SPLY_RELN = "DS Model Supply Relation";

    /** Message Parameter (Start Date) */
    public static final String START_DT = "Start Date";

    /** Message Parameter (End Date) */
    public static final String END_DT = "End Date";

    /** Message Parameter (same) */
    public static final String SAME_MDL = "same";

    /** Message Parameter (other) */
    public static final String OTHER_MDL = "other";

    //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) START
    /** Message Parameter (Model BackOrder Impact) */
    public static final String MDL_BO_IMPCT = "Model BackOrder Impact";
    //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) END

    /** Tab Name (Item Configurations) */
    public static final String TAB_ITEM_CONFIG = "ItemConfig";

    /** Tab Name (Service Rules) */
    public static final String TAB_SVC_RULES = "SvcRules";

    /** Tab Name (Supply Mapping) */
    public static final String TAB_SUPPLY_MAP = "SupplyMap";

    // add start 2016/05/19 CSA Defect#447
    /** Tab Name (End Of Life) */
    public static final String TAB_END_OF_LIFE = "EndOfLife";
    // add end 2016/05/19 CSA Defect#447

    //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) START
    /** Tab Name (Criticality) */
    public static final String TAB_CRITICALITY = "Criticality";
    //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) END

    /** Detail Type (Parent) */
    public static final String DTL_TP_PRNT = "PRNT";

    /** Detail Type (Child) */
    public static final String DTL_TP_CHILD = "CHLD";

    /** Date Start Index */
    public static final int DT_START_INDEX = 0;

    /** Date End Index */
    public static final int DT_END_INDEX = 8;

    /** Max Date */
    public static final String MAX_DATE = "99991231";

    // add start 2016/05/19 CSA Defect#447, 6675
    /** Minute */
    public static final int INT_MINUTE = 60;

    /** Mode:Update */
    public static final String UPD = "UPD";

    /** Mode:Delete */
    public static final String DEL = "DEL";
    // add end 2016/05/19 CSA Defect#447, 6675

    /** Active Status List */
    public static enum ACTV_STS {

        /** Active Status */
        ACTV(ZYPConstant.FLG_ON_Y, "Active"), IN_ACTV(ZYPConstant.FLG_OFF_N, "In-Active");

        /** Value */
        private String value;

        /** Display */
        private String display;

        private ACTV_STS(String value, String display) {
            this.value = value;
            this.display = display;
        }

        /**
         * get Value
         * @return Value
         */
        public String getValue() {
            return value;
        }

        /**
         * get Display
         * @return Display
         */
        public String getDisplay() {
            return display;
        }
    }

    // add start 2016/05/09 CSA Defect#8055
    /** Referenced Entity Item */
    public static final String REF_ENTITY_ITEM = "MODEL";

    /** Key Name : MDL_ID*/
    public static final String KEY_NAME_MDL_ID = "MDL_ID";

    /** Key Name : T_MDL_NM*/
    public static final String KEY_NAME_T_MDL_NM = "T_MDL_NM";
    // add end 2016/05/09 CSA Defect#8055

    // START 2024/03/14 K.Watanabe [QC#63542, ADD]
    /** Supply Map for the entered model */
    public static final String NSAM0011E_SUPPLY_MAP = "Supply Map for the entered model";
    // END   2024/03/14 K.Watanabe [QC#63542, ADD]

}
