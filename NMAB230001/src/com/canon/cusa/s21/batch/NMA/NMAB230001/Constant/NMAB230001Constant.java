/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB230001.Constant;

/**
 * <pre>
 * HR Interface
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/06   SRA             Y.Chen          Create          ITG#425359
 * 2013/03/27   SRA             G.Fu            Update          ITG415963
 * 2013/08/20   Fujitsu         N.Sakai         Update          WDS
 * 2013/10/14   Fujitsu         M.Yamada        Update          Defect#2621
 * 2013/10/15   Fujitsu         N.Sakai         Update          Defect#2621
 * 2013/10/24   Fujitsu         A.Wada          Update          Defect#349
 * 2016/02/15   Fujitsu         K.Minamide      Update          CSA
 * 2016/03/07   SRA             Y.Chen          Update          QC#5075
 * 2016/03/07   SRA             Y.Chen          Update          QC#1988
 * 2017/08/08   Fujitsu         H.Ikeda         Update          QC#20223
 * 2017/10/13   Fujitsu         K.Ishizuka      Update          QC#21924
 * 2019/02/18   Fujitsu         Hd.Sugawara     Update          QC#29668
 * 2023/04/14   Hitachi         T.Usui          Update          QC#61266
 *</pre>
 */
public class NMAB230001Constant {

    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** Data insert failure. (table name is [@]) */
    public static final String MMAM0003E = "MMAM0003E";

    /** Data update failure. (table name is [@]) */ 
    public static final String MMAM0004E = "MMAM0004E";
    
    /** Data delete failure. (table name is [@]) */ //S21_NA ADD QC#21924
    public static final String MMAM0005E = "MMAM0005E";

    /** Global Company Code For People Soft I/F */
    public static final String NMAL2300_GLBL_CMPY_CD = "NMAL2300_GLBL_CMPY_CD";

    /** DEFAULT_COMMIT_UNIT */
    public static final int DEFAULT_COMMIT_UNIT = 1000;

    /** DEFAULT_FETCH_SIZE */
    public static final int DEFAULT_FETCH_SIZE = 1000;

    /** COA_CMPY_CD index = 0 */
    public static final int COA_CMPY_CD_INDEX = 0;

    /** COA_EXTN_CD index = 3 */
    public static final int COA_EXTN_CD_INDEX = 3;

    /** COA_BR_CD index = 6 */
    public static final int COA_BR_CD_INDEX = 6;

    /** COA_CC_CD index = 9 */
    public static final int COA_CC_CD_INDEX = 9;

    /** HR_PSN_INTFC_EMP_STS_CD : ACTIVE */
    public static final String HR_PSN_INTFC_EMP_STS_CD_ACTIVE = "A";

    /** HR_PSN_INTFC_EMP_STS_CD : TERMINATE */
    public static final String HR_PSN_INTFC_EMP_STS_CD_TERMINATE = "T";

    /** mode : TERMINATE */
    public static final String TERMINATE = "TERMINATE";

    /** mode : UPDATE */
    public static final String UPDATE = "UPDATE";

    /** mode : REHIRE */
    public static final String REHIRE = "REHIRE";

    /** TECH_MSTR.TECH_NM */
    public static final int TECH_NM_MAX_LENGTH = 45;

    // QC#5075
    /** Max Employee ID Length */
    public static final int MAX_EMP_ID_LEN = 8;

    /** Employee Consultant Type : Employee */
    public static final String EMPL_CNSLT_TP_EMP = "E";

    /** HR Data Source: Employee */
    public static final String DATA_SRC_CD_EMP = "01";

    /** HR Data Source: Non Employee */
    public static final String DATA_SRC_CD_NON_EMP = "02";
    
    // QC#1988
    /** Mail box employee ID prefix */
    public static final String MAIL_BOX_EMP_ID_PREFIX = "GM%";

    // Add Start 2017/08/08 QC#20223
    /** Global Company Code ADB */
    public static final String ADB_GLBL_CMPY_CD = "ADB";
    // Add End   2017/08/08 QC#20223

    // Add Start 2019/02/18 QC#29668
    /** MAX_END_DATE */
    public static final String MAX_END_DATE = "99991231";

    /** TOC_CD **/
    public static final String BIZAPL_TOCCDKEY = "TOC_CD";
    // Add End 2019/02/18 QC#29668

    // QC#61266 2023/04/14 Add Start
    /** BIZ_AREA_ORG_CD_SALES */
    public static final String BIZ_AREA_ORG_CD_SALES = "1";

    /** GRACE_PERIOD */
    public static final String GRACE_PERIOD = "GRACE_PERIOD";
    
    /** GRACE_PERIOD */
    public static final String INITIAL_GRACE_PERIOD = "30";
    // QC#61266 2023/04/14 Add End
}
