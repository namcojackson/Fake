/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NMZ.NMZC270001.Constant;

/**
 *<pre>
 * Territory Rule Validation API Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/16   Fujitsu         K.Minamide      Create          N/A
 * 2016/06/21   Hitachi         A.Kohinata      Update          CSA-QC#10593
 * 2016/07/12   Hitachi         Y.Tsuchimoto    Update          CSA-QC#11726
 * 2016/08/31   SRAA            Y.Chen          Update          QC#11728
 * 2017/11/16   Fujitsu         N.Sugiura       Update          CSA-QC#20597
 * 2018/06/01   Fujitsu         Hd.Sugawara     Update          QC#24293
 *</pre>
 */
public class NMZC270001Constant {

    /** DEFAULT_FETCH_SIZE */
    public static final int DEFAULT_FETCH_SIZE = 1000;

    /** MAX_IN_PARAM */
    public static final int MAX_IN_PARAM = 1000;

    /** Global Company Code is not set. */
    public static final String NMZM0092E = "NMZM0092E";

    /** The Postal Code format is incorrect. */
    public static final String NMZM0070E = "NMZM0070E";

    /** Input parameter "orgCd" is mandatary field. */
    public static final String NMZM0071E = "NMZM0071E";

    /** Input parameter "firstOrgCd" is mandatary field. */
    public static final String NMZM0072E = "NMZM0072E";

    /** Input parameter "trtyTpCd" is mandatary field. */
    public static final String NMZM0073E = "NMZM0073E";

    /** Input parameter "orgTierCd" is mandatary field. */
    public static final String NMZM0074E = "NMZM0074E";

    /** Input parameter "orgStruTpCd" is mandatary field. */
    public static final String NMZM0075E = "NMZM0075E";

    /** Input parameter "trtyGrpTpCd" is mandatary field. */
    public static final String NMZM0076E = "NMZM0076E";

    /** Input parameter "effFromDt_T" is mandatary field. */
    public static final String NMZM0077E = "NMZM0077E";

    /** Input parameter "trtyRuleTpCd" is mandatary field. */
    public static final String NMZM0078E = "NMZM0078E";

    /** Input parameter "trtyRuleOprdTpCd" is mandatary field. */
    public static final String NMZM0079E = "NMZM0079E";

    /** Input parameter "trtyRuleFromValTxt" is mandatary field. */
    public static final String NMZM0080E = "NMZM0080E";

    /** Input parameter "effFromDt_R" is mandatary field. */
    public static final String NMZM0081E = "NMZM0081E";

    /** Input parameter "slsDt" is mandatary field. */
    public static final String NMZM0082E = "NMZM0082E";

    /**
     * Territory Rule Assignment(s) can be active only within the
     * Territory Effective Dates.
     */
    public static final String NMZM0083E = "NMZM0083E";

    /** Input parameter "trtyRuleThruValTxt" is mandatary field. */
    public static final String NMZM0084E = "NMZM0084E";

    /** Input parameter "trtyRuleThruValTxt" field should be blank. */
    public static final String NMZM0085E = "NMZM0085E";

    /** Last territory rule "trtyRuleLogicTpCd" should be blank. */
    public static final String NMZM0086E = "NMZM0086E";

    /**
     * Postal Code range overlaps with postal code assignment for
     * territory <@>.
     */
    public static final String NMZM0087E = "NMZM0087E";

    /** Duplicate Territory Rule Assignment(s) */
    public static final String NMZM0088E = "NMZM0088E";

    // 2017/11/16 CSA-QC#20597 Add Start
    /** Duplicate Territory Rule Assignment(s). If you would like to continue, please click 'Submit' again. */
    public static final String NMZM0359W = "NMZM0359W";

    /** Duplicate territory rule assignment(s) exist in territory <@>.*/
    public static final String NMZM0360W = "NMZM0360W";

    /** Duplicate Territory Rule Assignment(s). */
    public static final String NMZM0361W = "NMZM0361W";
    // 2017/11/16 CSA-QC#20597 Add End

    // 2017/11/16 CSA-QC#20597 Del Start
    // QC#11728 
    // /** Duplicate territory rule assignment(s) exist in territory <@>.*/
    // public static final String NMZM0332E = "NMZM0332E";
    // 2017/11/16 CSA-QC#20597 Del End

    /**
     * Territory rules "trtyRuleLogicTpCd" should be all "AND" or all
     * "OR".
     */
    public static final String NMZM0089E = "NMZM0089E";

    /** The value for [Value To] must be bigger than [Value From]. */
    public static final String NMZM0185E = "NMZM0185E";

    /** The combination  of post code area is invalid.  */
    public static final String NMAM8401E = "NMAM8401E";

    /** Logic type must be entered to all rules for multiple territory rules. */
    public static final String NMAM8442E = "NMAM8442E";

    // 2016/06/21 CSA-QC#10593 Mod Start
    /** Logic type should be "OR" for single territory rule.  */
    // 2016/06/21 CSA-QC#10593 Mod End
    public static final String NMAM8443E = "NMAM8443E";

    /** Postal Code length */
    public static final int POSTAL_CODE_LENGTH = 10;

    /** Postal Code first half length */
    public static final int POSTAL_CODE_FIRST_HALF_LENGTH = 5;

    /** Postal Code format 5 */
    public static final String POSTAL_CODE_FORMAT5 = "[0-9]{5}+";

    /** Postal Code format 10 */
    public static final String POSTAL_CODE_FORMAT10 = "[0-9]{5}+-[0-9]{4}";

    /** Postal Code format LIKE */
    public static final String POSTAL_CODE_FORMAT_LIKE = "[(0-9)\\-\\%]*";

    // Del Start 2018/06/01 QC#24293
    ///** Postal Code min */
    //public static final String POSTAL_CODE_MIN = "00000-0000";
    //
    ///** Postal Code max */
    //public static final String POSTAL_CODE_MAX = "99999-9999";
    // Del End 2018/06/01 QC#24293

    /** Postal Code min second half */
    public static final String POSTAL_CODE_MIN_FIRST_HALF = "00000";

    /** Postal Code max second half */
    public static final String POSTAL_CODE_MAX_FIRST_HALF = "99999";

    /** Postal Code min second half */
    public static final String POSTAL_CODE_MIN_SECOND_HALF = "-0000";

    /** Postal Code max second half */
    public static final String POSTAL_CODE_MAX_SECOND_HALF = "-9999";

    /** Max Effective Date */
    public static final String DB_PARAM_MAX_EFF_DT = "99991231";

    /** Max Territory Rule Thru */
    public static final String DB_PARAM_MAX_TRTY_RULE_THRU = "99999";

    // 2016/07/12 CSA-QC#11726 Add start
    /** Default value for End Date  */
    public static final String DEFAULT_VALUE_FOR_END_DATE = "99991231";
    // 2016/07/12 CSA-QC#11726 Add end
    
    // QC#11728
    /** TRTY_RULE_TP_CD */
    public static final String TRTY_RULE_TP_CD = "TRTY_RULE_TP_CD";

    /** TRTY_RULE_OPRD_TP_CD */
    public static final String TRTY_RULE_OPRD_TP_CD = "TRTY_RULE_OPRD_TP_CD";

    /** TRTY_RULE_FROM_VAL_TXT */
    public static final String TRTY_RULE_FROM_VAL_TXT = "TRTY_RULE_FROM_VAL_TXT";

    /** TRTY_RULE_THRU_VAL_TXT */
    public static final String TRTY_RULE_THRU_VAL_TXT = "TRTY_RULE_THRU_VAL_TXT";

    /** EFF_FROM_DT */
    public static final String EFF_FROM_DT = "EFF_FROM_DT";

    /** EFF_THRU_DT */
    public static final String EFF_THRU_DT = "EFF_THRU_DT";

    /** TRTY_RULE_LOGIC_TP_CD */
    public static final String TRTY_RULE_LOGIC_TP_CD = "TRTY_RULE_LOGIC_TP_CD";
}
