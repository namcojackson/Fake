/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLAL2040.constant;

/**
 *<pre>
 * Business ID : NLAL2040 MODELS-CLICKS Inventory Valuation Entry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/18/2016   CITS            T.Kikuhara      Create          N/A
 * 12/14/2016   CITS            R.Shimamoto     Update          QC#13056
 * 12/27/2016   CITS            T.Kikuhara      Update          QC#13056-2
 * 03/28/2018   CITS            K.Masaki        Update          QC#24622
 *</pre>
 */
public class NLAL2040Constant {

    /** . */
    public static final String TERMINATED_EFF_DT = "99991231";

    /** . */
    public static final int MAX_DISP_LINE = 20;

    /** Max Fetch Size */
    public static final int MAX_FETCH_SIZE = 1000;

    /** Limit Down load RowNumber */
    public static final int LIMIT_DL_ROWNUM = 65001;

    /** . */
    public static final String NLAL2040_DEF_EFF_THRU_DT = "NLAL2040_DEF_EFF_THRU_DT";

    /** . */
    public static final String EXTN_CSV = ".csv";

    /** . */
    public static final String CSV_FILE_NAME = "model2click";

    /** . */
    public static final String UPLOAD = "Upload";

    /** . */
    public static final String DISP_SUBMIT = "Submit";

    /** . */
    public static final String DT_FROM = "Date Active From";

    /** . */
    public static final String DT_ACT = "Date Active";

    /** . */
    public static final String MODEL = "Model";

    /** . */
    public static final String MODEL_SETUP = "Model Setup";

    /** . */
    public static final String SWH = "Sub Warehosue";

    /** . */
    public static final String CFS = "CFS Disposition";

    /** . */
    public static final String METER_FROM = "METER FROM";

    /** . */
    public static final String METER_TO = "METER TO";

    /** . */
    public static final String AGE_FROM = "AGE FROM";

    /** . */
    public static final String AGE_TO = "AGE TO";

    /** . */
    public static final String[] CSV_HDR = new String[] {
          "Model ID"
        , "SEQ ID"
        , "Date Active From"
        , "Date Active To"
        , "Model"
        , "Speed Segment"
        , "MT"
        , "PC"
        , "METER FROM"
        , "METER TO"
        , "AGE FROM (MONTHS)"
        , "AGE TO (MONTHS)"
        , "Owner"
        , "Sub WH Code"
        , "RMA Disposition"
        };
    
    /** . */
    public static final String AGE = "AGE";
    
    /** . */
    public static final String METER = "METER";

    // =================================================
    // SCREEN ID
    // =================================================
    /** . */
    public static final String INIT = "NLAL2040_INIT";
    /** . */
    public static final String SEARCH = "NLAL2040Scrn00_Search";
    /** . */
    public static final String IMPORT = "NLAL2040Scrn00_Import";
    /** . */
    public static final String DOWNLOAD = "NLAL2040Scrn00_CMN_Download";
    /** . */
    public static final String TEMPLETE = "NLAL2040Scrn00_TempleteFileForUpload";
    /** . */
    public static final String ADDLINE = "NLAL2040Scrn00_AddLine";
    /** . */
    public static final String PAGENEXT = "NLAL2040Scrn00_PageNext";
    /** . */
    public static final String PAGEPREV = "NLAL2040Scrn00_PagePrev";
    /** . */
    public static final String CLEAR = "NLAL2040Scrn00_CMN_Clear";
    /** . */
    public static final String SUBMIT = "NLAL2040Scrn00_CMN_Submit";
    /** . */
    public static final String DELETELINE = "NLAL2040Scrn00_DeleteLine";
    /** . */
    public static final String COLCLEAR = "NLAL2040Scrn00_CMN_ColClear";
    /** . */
    public static final String COLSAVE = "NLAL2040Scrn00_CMN_ColSave";

    // =================================================
    // DB COLUMN
    // =================================================
    /** . */
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";
    /** . */
    public static final String SALES_DATE = "SALES_DATE";
    /** . */
    public static final String RTL_WH_CATG_CD = "RTL_WH_CATG_CD";
    /** . */
    public static final String MDL_ID = "MDL_ID";
    /** . */
    public static final String SQ_ID = "SQ_ID";
    /** . */
    public static final String EFF_FROM_DT = "EFF_FROM_DT";
    /** . */
    public static final String EFF_THRU_DT = "EFF_THRU_DT";
    /** . */
    public static final String T_MDL_NM = "T_MDL_NM";
    /** . */
    public static final String FROM_MTR_CNT = "FROM_MTR_CNT";
    /** . */
    public static final String TO_MTR_CNT = "TO_MTR_CNT";
    /** . */
    public static final String FROM_ELPS_MTH_AOT = "FROM_ELPS_MTH_AOT";
    /** . */
    public static final String TO_ELPS_MTH_AOT = "TO_ELPS_MTH_AOT";
    /** . */
    public static final String DEST_RTL_SWH_CD = "DEST_RTL_SWH_CD";
    /** . */
    public static final String THIRD_PTY_DSP_TP_NM = "THIRD_PTY_DSP_TP_NM";
    /** . */
    public static final String MTR_REQ_MDL_FLG = "MTR_REQ_MDL_FLG";
    /** . */
    public static final String SVC_SEG_CD = "SVC_SEG_CD";
    /** . */
    public static final String SVC_SEG_DESC_TXT = "SVC_SEG_DESC_TXT";
    /** . */
    public static final String COA_MDSE_TP_CD = "COA_MDSE_TP_CD";
    /** . */
    public static final String COA_PROD_CD = "COA_PROD_CD";
    /** . */
    public static final String ROWNUM = "ROWNUM";
    /** . */
    public static final String INVTY_OWNR_CD = "INVTY_OWNR_CD";
    /** . */
    public static final String INVTY_OWNR_DESC_TXT = "INVTY_OWNR_DESC_TXT";

    // =================================================
    // ERROR ID
    // =================================================
    /** . */
    public static final String NLCM0025E = "NLCM0025E";
    /** . */
    public static final String NLCM0125E = "NLCM0125E";
    /** . */
    public static final String NLAM1295E = "NLAM1295E";
    /** . */
    public static final String ZYEM0004E = "ZYEM0004E";
    /** . */
    public static final String ZZM9000E = "ZZM9000E";
    /** . */
    public static final String ZZM9014E = "ZZM9014E";
    /** . */
    public static final String NZZM0000E = "NZZM0000E";
    /** . */
    public static final String NZZM0001W = "NZZM0001W";
    /** . */
    public static final String NLCM0123E = "NLCM0123E";
    /** . */
    public static final String NMAM0836E = "NMAM0836E";
    /** . */
    public static final String NLBM1231E = "NLBM1231E";
    /** . */
    public static final String NLAM1296E = "NLAM1296E";
    /** . */
    public static final String NLZM2278E = "NLZM2278E";
    /** . */
    public static final String NPAM1483E = "NPAM1483E";
    /** . */
    public static final String NPAM1484E = "NPAM1484E";
    /** . */
    public static final String NPAM1485E = "NPAM1485E";
    /** . */
    public static final String NPAM1486E = "NPAM1486E";
    /** . */
    public static final String NPAM1487E = "NPAM1487E";
    /** . */
    public static final String NPAM1212E = "NPAM1212E";
    /** . */
    public static final String NPAM1232E = "NPAM1232E";
    /** . */
    public static final String ZZZM9003I = "ZZZM9003I";
    /** . */
    public static final String NLAM1091E = "NLAM1091E";
    /** . */
    public static final String NLAM0014E = "NLAM0014E";
    /** . */
    public static final String NPAM0006E = "NPAM0006E";
    /** . */
    public static final String NMAM8181W = "NMAM8181W";
    /** . */
    public static final String NMAM0038I = "NMAM0038I";
    /** The date format is not "YYYYMMDD". Please check the format of [@]. */
    public static final String NLAM1346E = "NLAM1346E";

    /** Duplicate values of From and To. */
    public static final String NLAM1348E = "NLAM1348E";
    /** */
    public static final String NLAM1349E = "NLAM1349E";

}
