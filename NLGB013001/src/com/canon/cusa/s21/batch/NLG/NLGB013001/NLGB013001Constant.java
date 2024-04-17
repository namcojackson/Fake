/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NLG.NLGB013001;

/**
 * <pre>
 * Translate Inventory Reconciliation from WMS
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/20/2013   CSAI            K.Hayashida     Create          MW Replace Initial
 * 02/21/2017   CSAI            T.Kikuhara      Update          QC#17699
 * 03/06/2017   CITS            Y.Fujii         Update          QC#17916
 * 10/20/2017   CITS            T.Wada          Update          QC#21969
 * 08/26/2019   CITS            M.Naito         Update          QC#53025
 * 10/13/2019   CITS            K.Ogino         Update          QC#57841
 *</pre>
 */
public interface NLGB013001Constant {

    /** Business ID */
    String BUSINESS_ID = "NLGB0130";

    /** Output Log Program ID */
    String PROGRAM_ID = "NLGB013001:";

    /** Prameter Name: GLBL_CMPY_CD */
    String PRAM_NM_GLBL_CMPY_CD = "Global Company Code";

    /** Prameter Name: WH_GRP_CD */
    String PRAM_NM_WH_GRP_CD = "Warehouse Group Code";

    /** Prameter Name: Process Date */
    String PRAM_NM_PROCESS_DATE = "Process Date";

    /**
     * The corresponding data does not exist. Table Name : [@], Key
     * Field Name: [@], Key Value: [@]
     */
    String NLGM0044E = "NLGM0044E";

    /**
     * The record cannot be registered. Registration Table Name: [@],
     * Table Name: [@], Key Field Name: [@], Key Value: [@]
     */
    String NLGM0045E = "NLGM0045E";

    /**
     * The record cannot be updated. Table Name: [@], Key Field Name:
     * [@], Key Value: [@]
     */
    String NLGM0046E = "NLGM0046E";

    /**
     * Warehouse code to be processed is not set. Please check the WMS
     * warehouse table. MW_REPL_TRGT_GRP_CD: [@]
     */
    String NLGM0047E = "NLGM0047E";

    /**
     * [@] Parameter has not been set.
     */
    String NLGM0049E = "NLGM0049E";

    /** [@] is an invalid parameter. Parameter Name: [@] */
    String NLGM0060E = "NLGM0060E";

    // START 2019/08/26 M.Naito [QC#53025,ADD]
    /** Data does not exist in Stock Status Code Master. WMS_INBD_TRX_PK[@]  MDSE_CD[@]  WH_CD[@] */
    String NLGM0086E = "NLGM0086E";

    /** The record cannot be updated. Table Name: [@], Key Field Name: [@], Key Value: [@] */
    String NLGM0008E = "NLGM0008E";
    // END 2019/08/26 M.Naito [QC#53025,ADD]

    /** DB Table: NLCI0050_01 */
    String TBL_NLCI0050_01 = "NLCI0050_01";

    /** DB Table: WMS_INBD_TRX */
    String TBL_WMS_INBD_TRX = "WMS_INBD_TRX";

    /** DB Table: WMS_INBD_INVTY */
    String TBL_WMS_INBD_INVTY = "WMS_INBD_INVTY";

    /** DB Column: GLBL_CMPY_CD */
    String COL_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** DB Column: PROC_STS_CD */
    String COL_PROC_STS_CD = "PROC_STS_CD";

    /** DB Column: WMS_INBD_TRX_PK */
    String COL_WMS_INBD_TRX_PK = "WMS_INBD_TRX_PK";

    /** DB Column: WMS_REC_ID */
    String COL_WMS_REC_ID = "WMS_REC_ID";

    /** DB Column: WH_CD */
    String COL_WH_CD = "WH_CD";

    /** DB Column: */
    String COL_WMS_MDSE_CD = "WMS_MDSE_CD";

    /** DB Column: */
    String COL_WMS_STK_STS_CD = "WMS_STK_STS_CD";

    /** DB Column: WMS_RESRC_TXT */
    String COL_WMS_RESRC_TXT = "WMS_RESRC_TXT";

    /** DB Column: WMS_ORD_QTY */
    String COL_WMS_ORD_QTY = "WMS_ORD_QTY";

    /** DB Column: WHMM_WMS_MDSE_CD */
    String COL_WHMM_WMS_MDSE_CD = "WHMM_WMS_MDSE_CD";

    /** DB Column: HOST_MDSE_CD */
    String COL_HOST_MDSE_CD = "HOST_MDSE_CD";

    /** DB Column: INVTY_DT */
    String COL_INVTY_DT = "INVTY_DT";

    /** DB Column: WMS_TASK_CD */
    String COL_WMS_TASK_CD = "WMS_TASK_CD";

    /** DB Column: WMS_PKG_CD */
    String COL_WMS_PKG_CD = "WMS_PKG_CD";

    /** DB Column: SER_NUM */
    String COL_SER_NUM = "SER_NUM";

    /** DB Column: SVC_CONFIG_MSTR_PK */
    String COL_SVC_CONFIG_MSTR_PK = "SVC_CONFIG_MSTR_PK";

    /** DB Column: WMS_CNTNR_ID */
    String COL_WMS_CNTNR_ID = "WMS_CNTNR_ID";

    /** DB Column: WMS_BAT_ID */
    String COL_WMS_BAT_ID = "WMS_BAT_ID";

    /** Value of : Interface Id */
    String VAL_INTERFACE_ID_INVTY = "NLCI0050";

    /** Value of : WMS_REC_ID */
    String VAL_WMS_REC_ID = "INV";

    /** Value of : WMS_REC_TP_ID */
    String VAL_WMS_REC_TP_ID = "A";

    /** Value of : INTFC_TP_ID */
    String VAL_INTFC_TP_ID = "30";

    /** Value of : RECORD_TYPE_IF */
    String VAL_RECORD_TYPE_IF = "13";

    /** Value of : DETAIL_TYPE_IF */
    String VAL_DETAIL_TYPE_IF = "1";

    /** Value of : COMPANY_CODE_IF */
    String VAL_COMPANY_CODE_IF = "01";

    /** Value of : S80_STK_STS_CD Prefix */
    String VAL_S80_STK_STS_CD_PFX = "S";

    /** Value of : S80_STK_STS_CD */
    String VAL_S80_STK_STS_CD = "1";

    /** Value of : TRANSACTION_SEQUENCE_IF */
    String VAL_TRANSACTION_SEQUENCE_IF_PFX = "0000000000";

    /** Value of : TRANSACTION_SEQUENCE_IF substring */
    int LEN_TRANSACTION_SEQUENCE_IF = 7;

    // QC#17699 START
    /** COL_WMS_PKG_CD Min Length */
    int COL_WMS_PKG_CD_MIN_LENGTH = 6;

    /** DETAIL_ERROR */
    String DETAIL_ERROR = "X";
    // QC#17699 END

    /** Value of : / */
    String VAL_SLASH = "/";

    /** Value of : Target WMS_TRX_DT */
    int VAL_TRGT_WMS_TRX_DT = 1;

    /** Foarmat : Date */
    String FMT_DATE = "yyyyMMdd";

    /** Foarmat : Date and time */
    String FMT_DATE_TIME = "yyyyMMddHHmmss";

    /** Format : yyyymmdd */
    String FMT_YYYYMMDD = "yyyymmdd";

    /** Format : yyyy/mm/dd */
    String FMT_YYYY_MM_DD = "yyyy/mm/dd";

    /** Key : GLBL_CMPY_CD */
    String KEY_GLBL_CMPY_CD = "glblCmpyCd";

    /** Key : EZCANCELFLAG */
    String KEY_EZCANCELFLAG = "ezCancelFlag";

    /** Key : WH_CD */
    String KEY_WH_CD = "whCd";

    /** Key : WMS_TASK_CD */
    String KEY_WMS_TASK_CD = "wmsTaskCd";

    /** Key : WMS_MDSE_CD */
    String KEY_WMS_MDSE_CD = "wmsMdseCd";

    /** Key : WMS_STK_STS_CD */
    String KEY_WMS_STK_STS_CD = "wmsStkStsCd";

    /** Key : WMS_RESRC_TXT */
    String KEY_WMS_RESRC_TXT = "wmsResrcTxt";

    /** Key : WMS_BAT_ID */
    String KEY_WMS_BAT_ID = "wmsBatId";

    /** Key : PROC_STS_CD */
    String KEY_PROC_STS_CD = "procStsCd";

    // QC#17699 START
    /** Key : KEY_ERR_MSG_CD */
    String KEY_ERR_MSG_CD = "errMsgCd";

    /** Key : KEY_WMS_REC_ID */
    String KEY_WMS_REC_ID = "wmsRecId";
    // QC#17699 END

    /** MW_REPL_TRG_GRP_CD : TECSYS */
    String MW_REPL_TRG_GRP_CD_TECSYS = "1";

    /** MW_REPL_TRG_GRP_CD : DBS */
    String MW_REPL_TRG_GRP_CD_DBS = "2";

    // QC#57841 Add
    /** KEY_WMS_INBD_TRX_PK */
    String KEY_WMS_INBD_TRX_PK = "wmsInbdTrxPk";
}
