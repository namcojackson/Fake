/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1700.constant;

/**
 *<pre>
 * NWAL1700Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/14   Fujitsu         M.Suzuki        Create          N/A
 * 2016/04/05   Fujitsu         M.Suzuki        Update          S21_NA#6563
 * 2017/12/14   Fujitsu         Mz.Takahashi    Update          QC#19804(Sol349)
 *</pre>
 */
public class NWAL1700Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWAL1700";

    // --------------------------------
    // Message ID
    // --------------------------------
    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    /** You don’t have permission to delete existing line categories. */
    public static final String NWAM8443E = "NWAM8443E";

    /** Order Category Code @ has already existed. */
    public static final String NWAM8444E = "NWAM8444E";

    /** It failed to register [@]. */
    public static final String NWAM8448E = "NWAM8448E";

    /** No more lines can be added. */
    public static final String NWAM8441E = "NWAM8441E";

    /** The process ended abnormally. */
    public static final String NWAM8449E = "NWAM8449E";

    /** No corresponding data found in the master. */
    public static final String NWAM0008E = "NWAM0008E";

    // Add Start 2017/12/14 QC#19804(Sol349)
    /** The relationship between 'Line Of Business' and 'Territory Group' is incorrect. */
    public static final String NWAM0677E = "NWAM0677E";
    // Add End 2017/12/14 QC#19804(Sol349)

    /** Global Compy Code */
    public static final String GLOBAL_CMPY_CODE = "glblCmpyCd";

    /** Ord Proc Tp LvlCd */
    public static final String ORD_PROC_TP_LVL_CD = "ordProcTpLvlCd";

    /** Ord Proc Tp */
    public static final String ORD_PROC_TP = "ordProcTp";

    /** Ds Ord TpCd */
    public static final String DS_ORD_TP_CD = "dsOrdTpCd";

    /** DS Ord TPNM */
    public static final String DS_ORD_TP_NM = "dsOrdTpNm";

    /** Ds Ord CatgCd */
    public static final String DS_ORD_CATG_CD = "dsOrdCatgCd";

    /** Hld Rsn Cd */
    public static final String HLD_RSN_CD = "hldRsnCd";

    /** Apply DsOrd TpCd */
    public static final String APPLY_DS_ORD_TP_CD = "applyDsOrdTpCd";

    /** Ord Proc Node PrflCd */
    public static final String ORD_PROC_NODE_PRFL_CD = "ordProcNodePrflCd";

    /** locGrpCd */
    public static final String LOC_GRP_CD = "locGrpCd";

    /** Eff From */
    public static final String EFF_FROM = "effFrom";

    /** Eff Thru */
    public static final String EFF_THRU = "effThru";

    /** Level H */
    public static final String LEVEL_H = "H";
    //2016/04/04 S21_NA#6563 Add Start --------------
    /** Level L */
    public static final String LEVEL_L = "L";
    //2016/04/04 S21_NA#6563 Add Start --------------

    /** Ord Proc NodeCd */
    public static final String ORD_PROC_NODE_CD = "ordProcNodeCd";

    /** Validation Approval */
    public static final String VALIDATION_APPROVAL = "01";

    /** Di Check Required */
    public static final String DI_CHECK_REQUIRED = "02";

    /** Profitability */
    public static final String PROFITABILITY = "03";

    /** Credit Approval */
    public static final String CREDIT_APPROVAL = "04";

    /** Fixed Asset */
    public static final String FIXED_ASSET = "05";

    /** Out Of Warehouse */
    public static final String OUT_OF_WAREHOUSE = "06";

    /** Supply Abuse */
    public static final String SUPPLY_ABUSE = "07";

    /** Mode Create */
    public static final String MODE_CREATE = "0";

    /** Mode Edit */
    public static final String MODE_EDIT = "1";

    /** Loc Grp Cusa */
    public static final String LOC_GRP_CD_CUSA = "1";

    /** Function Read */
    public static final String FUNCTION_READ = "NWAL1700T010";

    /** Function INSERT */
    public static final String FUNCTION_INSERT = "NWAL1700T020";

    /** Function FULL */
    public static final String FUNCTION_FULL = "NWAL1700T030";

    /** Db Flag exist */
    public static final String DB_FLAG_EXIST = "E";

    /** DB Flag Insert */
    public static final String DB_FLAG_INSERT = "I";

    /** DB Flag Delete */
    public static final String DB_FLAG_DELETE = "D";

    /** chkBox */
    public static final String CHK_A = "xxChkBox_A";

    /** Eist Ck Row Num */
    public static final int EXIST_CK_ROW_NUM = 1;

    /** Row Num */
    public static final String ROW_NUM = "rowNum";

    /** Max Record Count */
    public static final int MAX_RECORD_COUNT = 9999;

    /** Bill To Account Number */
    public static final String BILL_TO_ACCT_NUM = "billToAccountNum";

    /** DS Order Line Category Code */
    public static final String DS_ORD_LINE_CATG_CD = "dsOrdlineCatgCd";

    /** Sales Date */
    public static final String SALES_DATE = "salesDate";

    /** Insert Retry Count */
    public static final int INSERT_RETRY_COUNT = 3;

    /** Global Cmpy Code01 */
    public static final String GLOBAL_CMPY_CODE01 = "glblCmpyCd01";

    /** Bill To Cust */
    public static final String BILL_TO_CUST01 = "billToCustCd01";

}
