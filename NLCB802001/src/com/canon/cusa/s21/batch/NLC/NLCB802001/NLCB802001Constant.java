package com.canon.cusa.s21.batch.NLC.NLCB802001;

/**
 * Class name: NLCB802001Constant
 * <dd>The class explanation: Constant variable for NLCB802001.
 * <dd>Remarks:
 * @version 1.0
 * @author H.Naoi
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/01/2021   CITS            D.Morimoto      Update          QC#59166
 */
public interface NLCB802001Constant {

    // ** Fixed Value ** //
    /** Fixed Value : Error Message Parameter */
    static final String MSG_PARAM = "Merchandise Inventory Revaluation Entry Process";

    /** Fixed Value : AJE Interface Type Code for Merchandise Inventory Revaluation */
    static final String AJE_INTFC_TP_CD_VAL = "09";

    /** Fixed Value : Merchandise or Parts Code for Merchandise */
    static final String MDSE_OR_PRT_CD_M_VAL = "M";
    
    /** Fixed Value : Transaction Code Value for Cost Calculation */
    static final String TRX_CD_VAL = "190";

    /** Fixed Value : Transaction Reason Code Value for Revaluation */
    static final String TRX_RSN_CD_RVAL_VAL = "06";

    /** Fixed Value : Transaction Reason Code Value for Write Down */
    static final String TRX_RSN_CD_WRT_DOWN_VAL = "07";
    
    /** Fixed Value : Location Status Code: In transit */
    static final String LOC_STS_CD_IN_TRANSIT = "01";

    /** Fixed Value : Inventory Location Type Code: Warehouse */
    static final String INVTY_LOC_TP_CD_WAREHOUSE = "01";
    
    /** Fixed Value : Inventory Location Type Code: Consignee */
    static final String INVTY_LOC_TP_CD_CONSIGNEE = "05";
    
    /** Fixed Value : Inventory Location Type Code: Vendor */
    static final String INVTY_LOC_TP_CD_VENDOR = "03";
    
    /** Fixed Value : VendorType Code: Kitting */
    static final String VND_TP_CD_KITTING = "09";
    
    /** Fixed Value : Vendor Type Code: Refurbish */
    static final String VND_TP_CD_REFURBISH = "10";

    /** Fixed Value : Asset Code Value */
    static final String ASSET_CD_VAL = "I";

    /** Fixed Value : Each Bulk Insert Count */
    static final int BULK_INSERT_COUNT = 10000;

    // ** DB Item Column Name's Fixed Value ** //    
    /** DB Item Column Name */
    static final String AJE_MDSE_RVAL_INTFC_SQ = "AJE_MDSE_RVAL_INTFC_SQ";

    /** DB Item Column Name */
    static final String AJE_MDSE_RVAL_INTFC_PK = "AJE_MDSE_RVAL_INTFC_PK";

    /** DB Item Column Name */
    static final String PROC_DT = "PROC_DT";

    /** DB Item Column Name */
    static final String GL_DT = "GL_DT";

    /** DB Item Column Name */
    static final String INVTY_SNAP_SHOT_PK = "INVTY_SNAP_SHOT_PK";

    /** DB Item Column Name */
    static final String INVTY_SNAP_SHOT_DT = "INVTY_SNAP_SHOT_DT";

    /** DB Item Column Name */
    static final String MDSE_CD = "MDSE_CD";

    /** DB Item Column Name */
    static final String INVTY_LOC_CD = "INVTY_LOC_CD";

    /** DB Item Column Name */
    static final String LOC_STS_CD = "LOC_STS_CD";

    /** DB Item Column Name */
    static final String STK_STS_CD = "STK_STS_CD";

    /** DB Item Column Name */
    static final String MLY_CLO_YR_MTH = "MLY_CLO_YR_MTH";

    /** DB Item Column Name */
    static final String INVTY_QTY = "INVTY_QTY";

    /** DB Item Column Name */
    static final String ASSET_CD = "ASSET_CD";

    /** DB Item Column Name */
    static final String COA_PROD_CD = "COA_PROD_CD";

    /** DB Item Column Name */
    static final String WRT_DOWN_FLG = "WRT_DOWN_FLG";

    /** DB Item Column Name */
    static final String THIS_MTH_FOB_AMT = "THIS_MTH_FOB_AMT";

    /** DB Item Column Name */
    static final String THIS_MTH_INLND_FRT_AMT = "THIS_MTH_INLND_FRT_AMT";

    /** DB Item Column Name */
    static final String THIS_MTH_INTL_FRT_AMT = "THIS_MTH_INTL_FRT_AMT";

    /** DB Item Column Name */
    static final String THIS_MTH_IMPT_DTY_AMT = "THIS_MTH_IMPT_DTY_AMT";

    /** DB Item Column Name */
    static final String THIS_MTH_INS_AMT = "THIS_MTH_INS_AMT";

    /** DB Item Column Name */
    static final String THIS_MTH_TOT_STD_COST_AMT = "THIS_MTH_TOT_STD_COST_AMT";

    /** DB Item Column Name */
    static final String LAST_MTH_TOT_STD_COST_AMT = "LAST_MTH_TOT_STD_COST_AMT";
    
    /** DB Item Column Name */
    static final String NEXT_MTH_TOT_STD_COST_AMT = "NEXT_MTH_TOT_STD_COST_AMT";

    /** DB Item Column Name */
    static final String COST_CCY_CD = "COST_CCY_CD";
    
    /** DB Item Column Name */
    static final String RVAL_AMT = "RVAL_AMT";
    
    /** DB Item Column Name */
    static final String COA_ACCT_CD = "COA_ACCT_CD";
    
    /** DB Item Column Name */
    static final String MDSE_CATG_CD = "MDSE_CATG_CD";
    
    /** DB Item Column Name */
    static final String RTL_WH_CD = "RTL_WH_CD";
    
    /** DB Item Column Name */
    static final String MDSE_CST_UPD_EFF_FROM_DT = "MDSE_CST_UPD_EFF_FROM_DT";
    
    /** DB Item Column Name */
    static final String ASSET_RECOV_CST_EFF_FROM_DT = "ASSET_RECOV_CST_EFF_FROM_DT";
    
    /** DB Item Column Name */
    static final String ASSET_RECOV_COST_AMT = "ASSET_RECOV_COST_AMT";
    
    /** DB Item Column Name */
    static final String PREV_ASSET_RECOV_COST_AMT = "PREV_ASSET_RECOV_COST_AMT";
    
    /** DB Item Column Name */
    static final String NEXT_ASSET_RECOV_COST_AMT = "NEXT_ASSET_RECOV_COST_AMT";
    
    /** DB Item Column Name */
    static final String RTL_SWH_CD = "RTL_SWH_CD";
    
    /** DB Item Column Name */
    static final String MDSE_COST_TP_CD = "MDSE_COST_TP_CD";
    
    /** DB Item Column Name */
    static final String MDSE_INVTY_COST_PCT = "MDSE_INVTY_COST_PCT";
    
    /** DB Item Column Name */
    static final String MDSE_CST_UPD_TP_CD = "MDSE_CST_UPD_TP_CD";
    
    // START 2021/09/01 D.Morimoto [QC#59166, ADD]
    /** DB Item Column Name */
    static final String TYPE = "TYPE";
    // END   2021/09/01 D.Morimoto [QC#59166, ADD]
    
    static final String PROC_CD_NEXT_MONTH = "1";
    static final String PROC_CD_THIS_MONTH = "2";
    
    // START 2021/09/01 D.Morimoto [QC#59166, ADD]
    /** Fixed Value : TYPE: Standard */
    static final String TYPE_STD = "Std";
    
    /** Fixed Value : TYPE: Asset */
    static final String TYPE_ASSET = "Asset";
    
    /** Fixed Value : MDSE_COST_TP_CD: Asset Recovery Cost */
    static final String MDSE_COST_TP_CD_ASSET = "2";
    // END   2021/09/01 D.Morimoto [QC#59166, ADD]
    
}
