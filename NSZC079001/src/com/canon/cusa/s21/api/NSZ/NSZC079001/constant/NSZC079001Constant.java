/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC079001.constant;

/**
 * <pre>
 * Inventory Search API Constant.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/21/2015   Hitachi         T.Iwamoto       Create          NA#Inventory Search API
 * 11/07/2016   Hitachi         T.Tomita        Update          QC#15757
 * 11/10/2016   Hitachi         T.Tomita        Update          QC#15757
 * 10/24/2016   Hitachi         K.Fujimoto      Update          QC#53410
 * 09/06/2023   Hitachi         N.Takatsu       Update          QC#61661
 * </pre>
 */
public final class NSZC079001Constant {

    /**
     * Global Company Code
     */
    public static final String GLBL_CMPY_CD = "ADB";

    /**
     * Input parameter "Item Validation Flag" is a mandatory field.
     */
    public static final String NSZM0732E = "NSZM0732E";

    /**
     * Input parameter "Part Number" is a mandatory field.
     */
    public static final String NSZM0733E = "NSZM0733E";

    /**
     * Input parameter "Requesting Technician ID" is a mandatory
     * field.
     */
    public static final String NSZM0734E = "NSZM0734E";

    /**
     * Input parameter "Item Validation Flag" is incorrect. Please
     * check it.
     */
    public static final String NSZM0735E = "NSZM0735E";

    /**
     * Technician Location information is not correctly setup.
     */
    public static final String NSZM0736E = "NSZM0736E";

    /**
     * The corresponding data does not exist in "VAR_CHAR_CONST".
     */
    public static final String NSZM0102E = "NSZM0102E";

    /**
     * LOCAL for varCharConstant
     */
    public static final String LOCAL = "NSZC0790_LOCAL";

    /**
     * NewSubWarehouseForWarehouse for varCharConstant
     */
    public static final String NEW_SUBWH_WH = "NSZC0790_NEW_SUBWH_FOR_WH";

    /**
     * NewSubWarehouseForTechnician for varCharConstant
     */
    public static final String NEW_SUBWH_TECH = "NSZC0790_NEW_SUBWH_FOR_TECH";

    /**
     * Inventory CVI Location for varCharConstant
     */
    public static final String CVI_LOCATION = "DLZC3000_CVI_LOC_CD";

    /**
     * CVI for varCharConstant
     */
    public static final String CVI = "NSZC0790_CVI";

    /**
     * CUSA for varCharConstant
     */
    public static final String CUSA = "NSZC0790_CUSA";

    /**
     * NSZC0790_WH_OWNER_CUSA for varCharConstant
     */
    public static final String NSZC0790_WH_OWNER_CUSA = "NSZC0790_WH_OWNER_CUSA";

    /**
     * NSZC0790_WH_OWNER_CVI for varCharConstant
     */
    public static final String NSZC0790_WH_OWNER_CVI = "NSZC0790_WH_OWNER_CVI";

    /**
     * NSZC0790_WH_OWNER_CSA for varCharConstant
     */
    public static final String NSZC0790_WH_OWNER_CSA = "NSZC0790_WH_OWNER_CSA";

    /**
     * NSZC0790_INVTY_SRCH_ACT_ORD for varCharConstant
     */
    public static final String NSZC0790_INVTY_SRCH_ACT_ORD = "NSZC0790_INVTY_SRCH_ACT_ORD";

    /**
     * NSZC0790_INVTY_SRCH_ACT_NONE for varCharConstant
     */
    public static final String NSZC0790_INVTY_SRCH_ACT_NONE = "NSZC0790_INVTY_SRCH_ACT_NONE";

    /**
     * NSZC0790_INVTY_SRCH_ACT_TRSF for varCharConstant
     */
    public static final String NSZC0790_INVTY_SRCH_ACT_TRSF = "NSZC0790_INVTY_SRCH_ACT_TRSF";

    /**
     * NSZC0790_INVTY_SRCH_GRP_PRTS for varCharConstant
     */
    public static final String NSZC0790_INVTY_SRCH_GRP_PRTS = "NSZC0790_INVTY_SRCH_GRP_PRTS";

    /**
     * NSZC0790_INVTY_SRCH_GRP_WH for varCharConstant
     */
    public static final String NSZC0790_INVTY_SRCH_GRP_WH = "NSZC0790_INVTY_SRCH_GRP_WH";

    /**
     * NSZC0790_INVTY_SRCH_GRP_TEAM for varCharConstant
     */
    public static final String NSZC0790_INVTY_SRCH_GRP_TEAM = "NSZC0790_INVTY_SRCH_GRP_TEAM";

    // START 2023/09/06 N.Takatsu [QC#61661, ADD]
    /**
     * NSZC0790_ALL_PARTS_CENTERS for varCharConstant
     */
    public static final String NSZC0790_ALL_PARTS_CENTERS = "NSZC0790_ALL_PARTS_CENTERS";

    /**
     * ALL_PARTS_CENTERS
     */
    public static final String ALL_PARTS_CENTERS = "ALL PARTS CENTERS";
    // END   2023/09/06 N.Takatsu [QC#61661, ADD]

    /**
     * CUSA for varCharConstant
     */
    public static final String BRANK = " ";

    // add start 2016/11/07 CSA Defect#15757
    /**
     * NSZC0790_SEARCH for DS_COND_CONST
     */
    public static final String NSZC0790_SEARCH = "NSZC0790_SEARCH";

    /**
     * MAX_RECORDS for DS_COND_CONST(NSZC0790_SEARCH)
     */
    public static final String MAX_RECORDS = "MAX_RECORDS";

    /**
     * NO_RECORDS for DS_COND_CONST(NSZC0790_SEARCH)
     */
    public static final String NO_RECORDS = "NO_RECORDS";
    // add end 2016/11/07 CSA Defect#15757

    // add start 2016/11/10 CSA Defect#15757
    /**
     * The corresponding data does not exist in "DS_COND_CONST".
     */
    public static final String NSZM1076E = "NSZM1076E";

    /**
     * NSZC0790_LIST_01 for DS_COND_CONST_GRP_ID
     */
    public static final String NSZC0790_LIST_01 = "NSZC0790_LIST_01";

    /**
     * NSZC0790_LIST_02 for DS_COND_CONST_GRP_ID
     */
    public static final String NSZC0790_LIST_02 = "NSZC0790_LIST_02";

    /**
     * NSZC0790_LIST_03 for DS_COND_CONST_GRP_ID
     */
    public static final String NSZC0790_LIST_03 = "NSZC0790_LIST_03";

    /**
     * NSZC0790_LIST_04 for DS_COND_CONST_GRP_ID
     */
    public static final String NSZC0790_LIST_04 = "NSZC0790_LIST_04";

    /**
     * NSZC0790_LIST_05 for DS_COND_CONST_GRP_ID
     */
    public static final String NSZC0790_LIST_05 = "NSZC0790_LIST_05";

    /**
     * NSZC0790_LIST_06 for DS_COND_CONST_GRP_ID
     */
    public static final String NSZC0790_LIST_06 = "NSZC0790_LIST_06";
    // add end 2016/11/10 CSA Defect#15757
    // START 2019/10/24 K.Fujimoto [QC#53410,ADD]
    /**
     * NSZC0790_LIST_06 for NSZC0790_ALL_SATELLITES
     */
    public static final String NSZC0790_ALL_SATELLITES = "NSZC0790_ALL_SATELLITES";
    
    public static final String ALL_SATELLITES = "ALL SATELLITES";
    // END   2019/10/24 K.Fujimoto [QC#53410,ADD]
}
