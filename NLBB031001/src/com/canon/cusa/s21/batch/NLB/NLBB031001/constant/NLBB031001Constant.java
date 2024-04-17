/**
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLB.NLBB031001.constant;


/**
 * <pre>
 * Batch Program Constant Class for Receive EDI240
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/18/2016   CITS            N.Akaishi       Create          V1.0
 * 01/17/2018   CITS            S.Katsuma       Update          QC#23056
 * 03/29/2019   Fujitsu         T.Ogura         Update          QC#30594
 *</pre>
 */
public class NLBB031001Constant {

    // *********************************************************
    // Constants: Message ID
    // *********************************************************
    /**
     * Message ID: NLGM0041E
     *  [@] does not exist. Table:[@], Search Key:[@]
     */
    public static final String NLGM0041E = "NLGM0041E";

    /**
     * Message ID: NLGM0045E
     *  The record cannot be registered.
     *  Registration Table Name: [@], Table Name: [@], Key Field Name:[@], Key Value: [@]
     */
    public static final String NLGM0045E = "NLGM0045E";

    /**
     * Message ID: NLGM0049E
     *  Parameter has not been set.
     *  [@] Parameter has not been set.
     */
    public static final String NLGM0049E = "NLGM0049E";

    /**
     * Message ID: NLGM0060E 
     * [@] is an invalid parameter. 
     * Parameter Name: [@]
     **/
    public static final String NLGM0060E = "NLGM0060E";

    // *********************************************************
    // Constants: Unique
    // *********************************************************
    /** Business ID */
    public static final String BUSINESS_ID = "NLBB0310";

    /** Prameter Name: GLBL_CMPY_CD */
    public static final String PRAM_NM_GLBL_CMPY_CD = "Global Company Code";

    /** Prameter Name: INTERFACE_ID */
    public static final String PRAM_NM_INTERFACE_ID = "Interface Id";

    // *********************************************************
    // Constants: SSM set Key Name
    // *********************************************************
    /** SSM set Key Name: GLBL_CMPY_CD */
    public static final String KEY_GLBL_CMPY_CD = "glblCmpyCd";

    /** SSM set Key Name: interfaceId */
    public static final String KEY_INTERFACE_ID = "interfaceId";

    /** SSM set Key Name: transactionId */
    public static final String KEY_TRANSACTION_ID = "transactionId";

    // START 2018/01/17 S.Katsuma [QC#23056,ADD]
    /** SSM set Key Name: ediFldTxt55 */
    public static final String KEY_EDI_FLD_TXT_55 = "ediFldTxt55";

    /** SSM set Key Name: proNum */
    public static final String KEY_PRO_NUM = "proNum";

    /** SSM set Key Name: isPodWrk */
    public static final String KEY_IS_POD_WRK = "isPodWrk";

    /** SSM set Key Name: transactionId */
    public static final String KEY_CARR_TP_CD = "carrTpCd";
    // END 2018/01/17 S.Katsuma [QC#23056,ADD]

    // *********************************************************
    // Constants: Table Name
    // *********************************************************
    /** DB Table : POD_ADDR_WRK */
    public static final String TBL_POD_ADDR_WRK = "POD_ADDR_WRK";

    /** DB Table : POD_STS_WRK */
    public static final String TBL_POD_STS_WRK = "POD_STS_WRK";

    /** DB Table : NLBI1320_01 */
    public static final String TBL_NLBI1320_01 = "NLBI1320_01";

    // *********************************************************
    // Constants: Column name
    // *********************************************************
    // NLBI1320_01
    /** Column name of GLBL_CMPY_CD */
    public static final String COL_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** Column name of TRANSACTION_ID */
    public static final String COL_TRANSACTION_ID = "TRANSACTION_ID";

    /** Column name of INTERFACE_ID */
    public static final String COL_INTERFACE_ID = "INTERFACE_ID";

    /** Column name of UNIT_ID */
    public static final String COL_UNIT_ID = "UNIT_ID";

    /** Column name of EDI_FLD_TXT_01 (EDI DATE) */
    public static final String COL_EDI_FLD_TXT_01 = "EDI_FLD_TXT_01";

    /** Column name of EDI_FLD_TXT_02 (EDI TIME) */
    public static final String COL_EDI_FLD_TXT_02 = "EDI_FLD_TXT_02";

    /** Column name of EDI_FLD_TXT_03 (EDI NUMBER) */
    public static final String COL_EDI_FLD_TXT_03 = "EDI_FLD_TXT_03";

    /** Column name of EDI_FLD_TXT_04 (EDI SET NUMBER) */
    public static final String COL_EDI_FLD_TXT_04 = "EDI_FLD_TXT_04";

    /** Column name of EDI_FLD_TXT_38 (TRACKING NUMBER) */
    public static final String COL_EDI_FLD_TXT_38 = "EDI_FLD_TXT_38";

    /** Column name of EDI_FLD_TXT_09 (SHIPPER NAME) */
    public static final String COL_EDI_FLD_TXT_09 = "EDI_FLD_TXT_09";

    /** Column name of EDI_FLD_TXT_18 (SHIPTO NAME) */
    public static final String COL_EDI_FLD_TXT_18 = "EDI_FLD_TXT_18";

    /** Column name of EDI_FLD_TXT_23 (SHIPTO ADDRESS1) */
    public static final String COL_EDI_FLD_TXT_23 = "EDI_FLD_TXT_23";

    /** Column name of EDI_FLD_TXT_24 (SHIPTO ADDRESS2) */
    public static final String COL_EDI_FLD_TXT_24 = "EDI_FLD_TXT_24";

    /** Column name of EDI_FLD_TXT_25 (SHIPTO CITY) */
    public static final String COL_EDI_FLD_TXT_25 = "EDI_FLD_TXT_25";

    /** Column name of EDI_FLD_TXT_26 (SHIPTO STATE) */
    public static final String COL_EDI_FLD_TXT_26 = "EDI_FLD_TXT_26";

    /** Column name of EDI_FLD_TXT_27 (SHIPTO ZIPCODE) */
    public static final String COL_EDI_FLD_TXT_27 = "EDI_FLD_TXT_27";

    /** Column name of EDI_FLD_TXT_46 (SCAN CITY) */
    public static final String COL_EDI_FLD_TXT_46 = "EDI_FLD_TXT_46";

    /** Column name of EDI_FLD_TXT_48 (SCAN STATE) */
    public static final String COL_EDI_FLD_TXT_48 = "EDI_FLD_TXT_48";

    /** Column name of EDI_FLD_TXT_50 (PACKAGE UOM) */
    public static final String COL_EDI_FLD_TXT_50 = "EDI_FLD_TXT_50";

    /** Column name of EDI_FLD_TXT_51 ([L1102_4]) */
    public static final String COL_EDI_FLD_TXT_51 = "EDI_FLD_TXT_51";

    /** Column name of EDI_FLD_TXT_55 (STATUS CODE1) */
    public static final String COL_EDI_FLD_TXT_55 = "EDI_FLD_TXT_55";

    /** Column name of EDI_FLD_TXT_56 (STATUS CODE2) */
    public static final String COL_EDI_FLD_TXT_56 = "EDI_FLD_TXT_56";

    /** Column name of EDI_FLD_TXT_57 ([AT703_2]) */
    public static final String COL_EDI_FLD_TXT_57 = "EDI_FLD_TXT_57";

    /** Column name of EDI_FLD_TXT_59 (STATUS DATE, STATUS CODE1 is not null) */
    public static final String COL_EDI_FLD_TXT_59 = "EDI_FLD_TXT_59";

    /** Column name of EDI_FLD_TXT_60 (STATUS TIME, STATUS CODE1 is not null) */
    public static final String COL_EDI_FLD_TXT_60 = "EDI_FLD_TXT_60";

    /** Column name of EDI_FLD_TXT_62 ([CD301]) */
    public static final String COL_EDI_FLD_TXT_62 = "EDI_FLD_TXT_62";

    /** Column name of EDI_FLD_TXT_63 (PACKAGE WEIGHT, EDI_FLD_TXT_62 is "A3") */
    public static final String COL_EDI_FLD_TXT_63 = "EDI_FLD_TXT_63";

    /** Column name of EDI_FLD_TXT_64 (PACKAGE WEIGHT UOM, EDI_FLD_TXT_62 is "A3") */
    public static final String COL_EDI_FLD_TXT_64 = "EDI_FLD_TXT_64";

    /** Column name of EDI_FLD_TXT_76 ([Q701]) */
    public static final String COL_EDI_FLD_TXT_76 = "EDI_FLD_TXT_76";

    /** Column name of EDI_FLD_TXT_78 (PACKAGE QUANTITY, EDI_FLD_TXT_76 is "O") */
    public static final String COL_EDI_FLD_TXT_78 = "EDI_FLD_TXT_78";

    // POD_ADDR_WRK
    /** Column name of EDI_SQ_ID */
    public static final String COL_EDI_SQ_ID = "EDI_SQ_ID";

    /** Column name of ERR_MSG_CD */
    public static final String COL_ERR_MSG_CD = "ERR_MSG_CD";

    /** Column name of PROC_STS_CD */
    public static final String COL_PROC_STS_CD = "PROC_STS_CD";

    /** Column name of EDI_SHPPR_NM */
    public static final String COL_EDI_SHPPR_NM = "EDI_SHPPR_NM";

    /** Column name of EDI_CNSGN_NM */
    public static final String COL_EDI_CNSGN_NM = "EDI_CNSGN_NM";

    /** Column name of EDI_CNSGN_ADDR */
    public static final String COL_EDI_CNSGN_ADDR = "EDI_CNSGN_ADDR";

    /** Column name of EDI_CNSGN_CTY_NM */
    public static final String COL_EDI_CNSGN_CTY_NM = "EDI_CNSGN_CTY_NM";

    /** Column name of EDI_CNSGN_ST_CD */
    public static final String COL_EDI_CNSGN_ST_CD = "EDI_CNSGN_ST_CD";

    /** Column name of EDI_CNSGN_POST_CD */
    public static final String COL_EDI_CNSGN_POST_CD = "EDI_CNSGN_POST_CD";

    /** Column name of POD_SRC_TP_CD */
    public static final String COL_POD_SRC_TP_CD = "POD_SRC_TP_CD";

    // POD_STS_WRK
    /** Column name of EDI_TRX_ID */
    public static final String COL_EDI_TRX_ID = "EDI_TRX_ID";

    /** Column name of EDI_TP_CD */
    public static final String COL_EDI_TP_CD = "EDI_TP_CD";

    /** Column name of EDI_GS_CTRL_CD */
    public static final String COL_EDI_GS_CTRL_CD = "EDI_GS_CTRL_CD";

    /** Column name of EDI_ST_CTRL_CD */
    public static final String COL_EDI_ST_CTRL_CD = "EDI_ST_CTRL_CD";

    /** Column name of EDI_LN_CTRL_CD */
    public static final String COL_EDI_LN_CTRL_CD = "EDI_LN_CTRL_CD";

    /** Column name of EDI_STS_CD */
    public static final String COL_EDI_STS_CD = "EDI_STS_CD";

    /** Column name of EDI_STS_DT */
    public static final String COL_EDI_STS_DT = "EDI_STS_DT";

    /** Column name of EDI_STS_TM */
    public static final String COL_EDI_STS_TM = "EDI_STS_TM";

    /** Column name of EDI_PRO_NUM */
    public static final String COL_EDI_PRO_NUM = "EDI_PRO_NUM";

    /** Column name of EDI_TM_CD */
    public static final String COL_EDI_TM_CD = "EDI_TM_CD";

    /** Column name of EDI_STS_RSN_CD */
    public static final String COL_EDI_STS_RSN_CD = "EDI_STS_RSN_CD";

    /** Column name of EDI_STS_CTY_NM */
    public static final String COL_EDI_STS_CTY_NM = "EDI_STS_CTY_NM";

    /** Column name of EDI_STS_ST_CD */
    public static final String COL_EDI_STS_ST_CD = "EDI_STS_ST_CD";

    /** Column name of EDI_WT */
    public static final String COL_EDI_WT = "EDI_WT";

    /** Column name of EDI_WT_UNIT_CD */
    public static final String COL_EDI_WT_UNIT_CD = "EDI_WT_UNIT_CD";

    /** Column name of EDI_LOAD_QTY */
    public static final String COL_EDI_LOAD_QTY = "EDI_LOAD_QTY";

    /** Column name of EDI_PICK_UP_DT */
    public static final String COL_EDI_PICK_UP_DT = "EDI_PICK_UP_DT";

    /** Column name of EDI_PICK_UP_TM */
    public static final String COL_EDI_PICK_UP_TM = "EDI_PICK_UP_TM";

    // START 2018/01/17 S.Katsuma [QC#23056,ADD]
    /** DB COLUMN :TRX_REF_NUM **/
    public static final String COL_TRX_HDR_NUM = "TRX_HDR_NUM";

    /** DB COLUMN :TRX_REF_LINE_NUM **/
    public static final String COL_TRX_LINE_NUM = "TRX_LINE_NUM";

    /** COL_EDI_FLD_TXT_55 : PROOF_OF_PICKUP */
    public static final String TP_PROOF_OF_PICKUP = "CP";

    /** COL_EDI_FLD_TXT_55 : PROOF_OF_DELIVERY */
    public static final String TP_PROOF_OF_DELIVERY = "D1";
    // END 2018/01/17 S.Katsuma [QC#23056,ADD]

    /** Max Length Of EDI_ST_CTRL_CD */
    public static final int LG_EDI_ST_CTRL_CD = 4;

    /** Max Length Of EDI_LN_CTRL_CD */
    public static final int LG_EDI_LN_CTRL_CD = 4;

    /** start position for WMS_SHIP_DT(for POD) */
    public static final int LG_WMS_SHIP_DT_FROM_FOR_POD = 0;

    /** end position for WMS_SHIP_DT(for POD) */
    public static final int LG_WMS_SHIP_DT_TO_FOR_POD = 8;

    /** start position for WMS_SHIP_TM(for POD) */
    public static final int LG_WMS_SHIP_TM_FROM_FOR_POD = 8;

    /** end position for WMS_SHIP_TM(for POD) */
    public static final int LG_WMS_SHIP_TM_TO_FOR_POD = 14;

    /** 0 */
    public static final int ZERO = 0;

    /** 1 */
    public static final int ONE = 1;

    /** "0" */
    public static final String VAL_ZERO = "0";

    /** "" */
    public static final String VAL_EMPTY = "";

    /** "," */
    public static final String VAL_CAMMA = ",";

    /** delimitor "/" */
    public static final String VAL_SLASH = "/";

    /** Value Blank */
    public static final String VAL_BLANK = " ";    // 2019/03/29 T.Ogura [QC#30594,ADD]

    /** EDI_TP_CD : "UPS" */
    public static final String EDI_TP_CD_UPS = "UPS";

    /** EDI_FLD_TXT_51(L1102_4) : "QQ" */
    public static final String EDI_FLD_TXT_51_QQ = "QQ";

    /** EDI_FLD_TXT_57(AT703_2) : "AA" */
    public static final String EDI_FLD_TXT_57_AA = "AA";

    /** EDI_FLD_TXT_62(CD301) : "A3" */
    public static final String EDI_FLD_TXT_62_A3 = "A3";

    /** EDI_FLD_TXT_76(Q701) : "O" */
    public static final String EDI_FLD_TXT_76_O = "O";

    /** Function Return Status (Normal End) */
    public static final int ST_NORMAL_END = 0;

    /** Function Return Status (Application Exception) */
    public static final int ST_APPL_ERR_END = -1;

    /** Value CONST_EDI_TM_CD LT */
    public static final String VAL_CONST_EDI_TM_CD_LT = "LT";

    // *********************************************************
    // Constants: SQL Statement Id
    // *********************************************************
    /** getEdiRcvList */
    public static final String SQL_STMT_ID_GET_EDI_RCV_LIST = "getEdiRcvList";

    /** Value (EDI_LN_CTRL_CD)  : "0001" */
    public static final String VAL_EDI_LN_CTRL_CD = "0001";

    // START 2018/01/17 S.Katsuma [QC#23056,ADD]
    /** CARR_TP_CD_FEDEX */
    public static final String CARR_TP_CD_UPS = "2";
    // END 2018/01/17 S.Katsuma [QC#23056,ADD]

    public static final String UPS_ETA_STS_CD_FLD_57 = "UPS_ETA_STS_CD_FLD_57";

    /** SSM set Key Name: ediFldTxt57 */
    public static final String KEY_EDI_FLD_TXT_57 = "ediFldTxt57";
}
