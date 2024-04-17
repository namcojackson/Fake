/*
 * <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC410001.constant;

/**
 * <pre>
 * Receive Field Request from Click Software API.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/01/11   Hitachi         K.Kojima        Update          QC#16905
 * 2017/01/23   Hitachi         K.Kojima        Update          QC#16905
 * 2017/07/31   Hitachi         K.Kojima        Update          QC#20212
 * 2018/02/07   Hitachi         U.Kim           Update          QC#17647
 * 2019/08/07   Hitachi         K.Fujimoto      Update          QC#52406
 * 2019/09/12   Hitachi         K.Fujimoto      Update          QC#53380
 * 2020/07/14   CITS            T.Wada          Update          QC#56895
 * </pre>
 */
public class NLZC410001Constant {

    /** ProcessMode : Daily */
    public static final String MODE_DAILIY = "01";

    /** ProcessMode : Nightly */
    public static final String MODE_NIGHTLY = "02";

    // START 2017/07/31 K.Kojima [QC#20212,ADD]
    /** Nighty Split Mode : Other */
    public static final String NIGHTY_SPLIT_MODE_OTHER = "99";

    // END 2017/07/31 K.Kojima [QC#20212,ADD]

    /** InterfaceID */
    public static final String INTERFACE_ID = "NSBI1080";

    /** Fetch Size */
    public static final int FETCH_SIZE = 1000;

    /** Num Counst Name */
    public static final String WMB_MAX_LENGTH = "NLAB311001_WMB_MAX_LENGTH";

    /** MessageID : NLZM2504E */
    public static final String NLZM2504E = "NLZM2504E";

    /** MessageID : NLZM2505E */
    public static final String NLZM2505E = "NLZM2505E";

    /** MessageID : NLZM2506E */
    public static final String NLZM2506E = "NLZM2506E";

    /** MessageID : NLZM2507E */
    public static final String NLZM2507E = "NLZM2507E";

    // START 2017/07/31 K.Kojima [QC#20212,ADD]
    /** MessageID : NLZM2512E */
    public static final String NLZM2512E = "NLZM2512E";
    // END 2017/07/31 K.Kojima [QC#20212,ADD]

    /** MessageID : NLAM1315E */
    public static final String NLAM1315E = "NLAM1315E";

    /** ColumnName : FIRST_MTH_INVTY_QTY */
    public static final String FIRST_MTH_INVTY_QTY = "FIRST_MTH_INVTY_QTY";

    /** ColumnName : INVTY_LOC_CD */
    public static final String INVTY_LOC_CD = "INVTY_LOC_CD";

    /** ColumnName : INVTY_QTY */
    public static final String INVTY_QTY = "INVTY_QTY";

    /** ColumnName : MDSE_CD */
    public static final String MDSE_CD = "MDSE_CD";

    /** ColumnName : MDSE_NM */
    public static final String MDSE_NM = "MDSE_NM";

    /** ColumnName : OVER_FRTH_MTH_INVTY_QTY */
    public static final String OVER_FRTH_MTH_INVTY_QTY = "OVER_FRTH_MTH_INVTY_QTY";

    /** ColumnName : PRT_SRVY_REQ_FLG */
    public static final String PRT_SRVY_REQ_FLG = "PRT_SRVY_REQ_FLG";

    /** ColumnName : RTL_SWH_CD */
    public static final String RTL_SWH_CD = "RTL_SWH_CD";

    /** ColumnName : RTL_WH_CD */
    public static final String RTL_WH_CD = "RTL_WH_CD";

    /** ColumnName : RTRN_CTRL_FLG */
    public static final String RTRN_CTRL_FLG = "RTRN_CTRL_FLG";

    /** ColumnName : SCD_MTH_INVTY_QTY */
    public static final String SCD_MTH_INVTY_QTY = "SCD_MTH_INVTY_QTY";

    /** ColumnName : SER_NUM */
    public static final String SER_NUM = "SER_NUM";

    /** ColumnName : SNAP_INVTY_QTY */
    public static final String SNAP_INVTY_QTY = "SNAP_INVTY_QTY";

    /** ColumnName : TECH_TOC_CD */
    public static final String TECH_TOC_CD = "TECH_TOC_CD";

    // START 2019/08/07 K.Fujimoto [QC#52406,ADD]
    /** ColumnName : TECH_TOC_CD_BEF */
    public static final String TECH_TOC_CD_BEF = "TECH_TOC_CD_BEF";
    // END   2019/08/07 K.Fujimoto [QC#52406,ADD]

    // START 2019/09/12 K.Fujimoto [QC#53380,ADD]
    /** ColumnName : RTL_WH_CATG_CD */
    public static final String RTL_WH_CATG_CD = "RTL_WH_CATG_CD";
    // END   2019/09/12 K.Fujimoto [QC#53380,ADD]
    
    /** ColumnName : THIRD_MTH_INVTY_QTY */
    public static final String THIRD_MTH_INVTY_QTY = "THIRD_MTH_INVTY_QTY";

    /** ColumnName : THIS_MTH_TOT_STD_COST_AMT */
    public static final String THIS_MTH_TOT_STD_COST_AMT = "THIS_MTH_TOT_STD_COST_AMT";

    /** ColumnName : WH_MGR_PSN_CD */
    public static final String WH_MGR_PSN_CD = "WH_MGR_PSN_CD";

    // START 2019/08/07 K.Fujimoto [QC#52406,ADD]
    /** ColumnName : TECH_TOC_CD_BEF */
    public static final String WH_MGR_PSN_CD_BEF = "WH_MGR_PSN_CD_BEF";
    // END   2019/08/07 K.Fujimoto [QC#52406,ADD]

    /** ColumnName : WH_NM */
    public static final String WH_NM = "WH_NM";

    // START 2017/01/23 K.Kojima [QC#16905,ADD]
    /** ColumnName : TECH_INVTY_AGING_WRK_PK */
    public static final String TECH_INVTY_AGING_WRK_PK = "TECH_INVTY_AGING_WRK_PK";
    // END 2017/01/23 K.Kojima [QC#16905,ADD]

    // START 2018/02/07 U.Kim [QC#, ADD]
    /** ColumnName : MAX_INVTY_QTY */
    public static final String MAX_INVTY_QTY = "MAX_INVTY_QTY";

    /** ColumnName : MIN_INVTY_QTY */
    public static final String MIN_INVTY_QTY = "MIN_INVTY_QTY";

    /** ColumnName : PRC_LIST_EQUIP_PRC_AMT */
    public static final String PRC_LIST_EQUIP_PRC_AMT = "PRC_LIST_EQUIP_PRC_AMT";
    // END 2018/02/07 U.Kim [QC#, ADD]

    // QC#56895 ADD Start
    public static final String DEF_INTVL_MINS = "60";
    // QC#56895 ADD End
}
