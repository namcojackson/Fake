/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB263001.constant;

/**
 *<pre>
 * Reassign Postal Codes Upload Batch Constant Class
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/16   Fujitsu         S.Ohki          Create
 * 2017/11/16   Fujitsu         N.Sugiura       Update          CSA-QC#20597
 *</pre>
 */
public class NMAB263001Constant {

    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** [@] is invalid value */
    public static final String ZZZM9026E = "ZZZM9026E";

    /** Parameter @ is required. */
    public static final String NMAM8228E = "NMAM8228E";

    /** The entered [@] does not exist in master. */
    public static final String NMAM8121E = "NMAM8121E";

    /** @ is invalid. */
    public static final String NMAM0071E = "NMAM0071E";

    /** Old Territory and New Territory can't be set up same value. */
    public static final String NMAM8578E = "NMAM8578E";

    /** [@] is not Active. */
    public static final String NMAM8497E = "NMAM8497E";

    /** [@] is an invalid date. */
    public static final String NMAM8459E = "NMAM8459E";

    /** A past date cannot be entered into the "Date Effective From". */
    public static final String NMAM0185E = "NMAM0185E";

    /**
     * [Effective Date of Territory Rule] cannot be entered [out of
     * territory effective date].
     */
    public static final String NMAM8599E = "NMAM8599E";

    /**
     * [Old territory rule effective date thru] cannot be updated
     * because of [Move Effective Date From is before old territory
     * rule's effective date From].
     */
    public static final String NMAM8600E = "NMAM8600E";

    /**
     * [Territory logic Type] cannot be entered [territory logic type
     * should be same between old territory and new territory].
     */
    public static final String NMAM8601E = "NMAM8601E";

    /**
     * Old Territory Rule has been updated by another user because Old
     * Territory Rule's last update time is after request date time.
     */
    public static final String NMAM8602E = "NMAM8602E";

    /**
     * New Territory Rule has been updated by another user because New
     * Territory Rule's last update time is after request date time.
     */
    public static final String NMAM8603E = "NMAM8603E";

    /** Target data could not be obtained.[@] */
    public static final String NMAM8604E = "NMAM8604E";

    /** Another Territory rules obtained error under same territory. */
    public static final String NMAM8609E = "NMAM8609E";

    /** The value for [Move from effective date] must be within [Move postal from territory's effective date]. */
    public static final String NMAM8619E = "NMAM8619E";

    /** The data @ does not exist in the master. */
    public static final String NMAM8186E = "NMAM8186E";

    /** 「@」 */
    public static final String NYXM0001I = "NYXM0001I";

    /** 「@」 */
    public static final String NYXM0002E = "NYXM0002E";

    /** It failed to update [@]. */
    public static final String NMAM0177E = "NMAM0177E";

    /** Data insert failure.@ */
    public static final String NMAM0282E = "NMAM0282E";

    /** MSG_UPLD_CSV_RQST_PK */
    public static final String UPLD_CSV_RQST_PK = "Upload CSV Request PK";

    /** MSG_UPLD_CSV_ID */
    public static final String UPLD_CSV_ID = "Upload Csv ID";

    /** Phone check Pattern */
    public static final String CHK_DATE_PATTERN = "\\d{1,2}/\\d{1,2}/\\d{4}";

    /** Time Stamp Format */
    public static final String TIME_STAMP_FORMAT = "yyyyMMddHHmmssSSS";

    /** Comma */
    public static final String COMMA = ",";

    /** Slash */
    public static final String SLASH = "/";

    /** Space */
    public static final String SPACE = " ";

    /** Slash */
    public static final String DATE_FORMAT_PADDING_ZERO = "0";

    /** DEFAULT_FETCH_SIZE */
    public static final int DEFAULT_FETCH_SIZE = 1000;

    /** Data Map Key(RQST_PK). */
    public static final String DATA_MAP_KEY_RQST_PK = "RQST_PK";

    /** Data Map Key(NEW_ORG_CD). */
    public static final String DATA_MAP_KEY_NEW_ORG_CD = "NEW_ORG_CD";

    /** Data Map Key(OLD_ORG_CD). */
    public static final String DATA_MAP_KEY_OLD_ORG_CD = "OLD_ORG_CD";

    /** Data Map Key(ERR_MSG). */
    public static final String DATA_MAP_KEY_ERR_MSG = "ERR_MSG";

    /** Data Map Key(ERR_MSG_LIST). */
    public static final String DATA_MAP_KEY_ERR_MSG_LIST = "ERR_MSG_LIST";

    /** Data Map Key(RQST_HDR_PK). */
    public static final String DATA_MAP_KEY_RQST_HDR_PK = "RQST_HDR_PK";

    /** Data Map Key(DTL_TRTY_RULE_OPRD_TP_DESC_TXT). */
    public static final String DATA_MAP_KEY_DTL_TRTY_RULE_OPRD_TP_DESC_TXT = "DTL_TRTY_RULE_OPRD_TP_DESC_TXT";

    /** Data Map Key(DTL_TRTY_RULE_FROM_VAL_TXT). */
    public static final String DATA_MAP_KEY_DTL_TRTY_RULE_FROM_VAL_TXT = "DTL_TRTY_RULE_FROM_VAL_TXT";

    /** Data Map Key(DTL_TRTY_RULE_THRU_VAL_TXT). */
    public static final String DATA_MAP_KEY_DTL_TRTY_RULE_THRU_VAL_TXT = "DTL_TRTY_RULE_THRU_VAL_TXT";

    /** Data Map Key(DTL_MOVE_EFF_FROM_DT_TXT). */
    public static final String DATA_MAP_KEY_DTL_MOVE_EFF_FROM_DT_TXT = "DTL_MOVE_EFF_FROM_DT_TXT";

    /** Data Map Key(DTL_MOVE_EFF_THRU_DT_TXT). */
    public static final String DATA_MAP_KEY_DTL_MOVE_EFF_THRU_DT_TXT = "DTL_MOVE_EFF_THRU_DT_TXT";

    /** Data Map Key(DTL_MASS_UPD_RSN_CMNT_TXT). */
    public static final String DATA_MAP_KEY_DTL_MASS_UPD_RSN_CMNT_TXT = "DTL_MASS_UPD_RSN_CMNT_TXT";
    // 2017/11/16 CSA-QC#20597 Add Start
    /** Warning Kind */
    public static final String MESSAGE_KIND_ERROR = "E";
    // 2017/11/16 CSA-QC#20597 Add End

}
