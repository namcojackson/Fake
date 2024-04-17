/*
 * <Pre>Copyright (c) 2019 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB270001.constant;

/**
 * <pre>
 * NPAB270001:CUSA Parts Cost Update IF for Venlo/Poing
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2019/11/07   CITS            K.Ogino         Create          QC#54157
 * 2019/12/20   CITS            K.Ogino         Update          QC#55168
 *</pre>
 */
public class NPAB270001Constant {

    /**
     * BATCH_PROGRAM_ID(NFBB1300)
     */
    public static final String BUSINESS_ID = "NPAB2700";

    /** Fetch size for SSM */
    public static final int FETCH_SIZE = 1000;

    /*
     * Message Code Definition
     */
    /** The field of [@] is not input. */
    public static final String MSG_ID_NPAM1173E = "NPAM1173E";

    /** Error of API [@] */
    public static final String MSG_ID_NPAM1178E = "NPAM1178E";

    /**
     * Target data does not exist. Table Name: [@], Key Item: [@], Key
     * Value: [@].
     */
    public static final String MSG_ID_NPAM1288E = "NPAM1288E";

    /** PO Price has been changed. */
    public static final String NPZM0309W = "NPZM0309W";

    // QC#55168
    /** Failed to register to MDSE Cost Update History Header. */
    public static final String NPZM0254E = "NPZM0254E";

    // QC#55168
    /** Failed to register to MDSE Cost Update History Detail. */
    public static final String NPZM0255E = "NPZM0255E";

    /** Error Message */
    public static final String ERR_MSG = "errMsg";

    /*
     * Mail Definition
     */
    /** Mail Group ID */
    public static final String MAIL_GROUP_ID = "NPAB2700";

    /** Mail Group ID Key From */
    public static final String MAIL_GROUP_KEY_FROM = "From";

    /** Mail Group ID Key To */
    public static final String MAIL_GROUP_KEY_TO = "To";

    /** Mail Group ID Key Cc */
    public static final String MAIL_GROUP_KEY_CC = "Cc";

    /** Mail Template ID */
    public static final String MAIL_TEMPLATE_ID = "NPAB2700M001";

    /** Mail Template Variable Update */
    public static final String MAIL_VAR_UPDATE = "updateMsg";

    /** Mail Template Variable Error */
    public static final String MAIL_VAR_ERROR = "errorMsg";

    /**
     * QC#55168 Add ASSET_RECOV_COST_AMT
     * ResultSet Column and Info Map Key
     */
    public enum Select {
        CD_PARTS, PR_SALES_PARTS1_TR, ASL_HDR_PK, ASL_NM, ASL_DESC_TXT, ASL_START_DT, ASL_END_DT, ASL_DTL_PK, PRNT_VND_CD, VND_CD, UNIT_PRC_AMT, THIS_MTH_TOT_STD_COST_AMT, ASSET_RECOV_COST_AMT
    }
}
