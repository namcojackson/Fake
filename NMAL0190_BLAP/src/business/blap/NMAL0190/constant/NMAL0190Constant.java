/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL0190.constant;

/**
 *<pre>
 * NMAL0190Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/07   Fujitsu         T.Arima         Create          N/A
 * 2016/03/07   CITS            S.Tanikawa      Update          QC#2669
 *</pre>
 */
public class NMAL0190Constant {

    // --------------------------------
    // Message
    // --------------------------------
    /** Message Kind Error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** Message Kind Warning */
    public static final String MESSAGE_KIND_WARN = "W";

    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** Data insert failure. [ TableName = @ , key = @, value = @ ] */

    public static final String ZZMM0001E = "ZZMM0001E";

    /** Data update failure. [ TableName = @ , key = @, value = @ ] */
    public static final String ZZMM0015E = "ZZMM0015E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** This data has been updated by another user. [ TableName = @ , key = @, value = @ ] */
    public static final String NMAM8175E = "NMAM8175E";

    /** [@] already exists in [@] */
    public static final String NMAM0834E = "NMAM0834E";

    /** No search results found. */
    public static final String NMAM0007I = "NMAM0007I";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** Message Parameter : Replace String (Item Number) */
    public static final String MSG_PRM_ITEM_NUM = "Item Number";

    /** Message Parameter : Replace String (MDSE_CD) */
    public static final String MSG_PRM_MDSE_CD = "MDSE_CD";

    /** Message Parameter : Table Name (MDSE) */
    public static final String MSG_PRM_TBL_MDSE = "MDSE";

    /** Message Parameter : Table Name (SUPD) */
    public static final String MSG_PRM_TBL_SUPD = "SUPD";

    /** Message Parameter : Table Name (SUPD_RELN) */
    public static final String MSG_PRM_TBL_SUPD_RELN = "SUPD_RELN";

    /** Message Parameter : Table Name (SUPD_RELN_STAGE) */
    public static final String MSG_PRM_TBL_SUPD_RELN_STAGE = "SUPD_RELN_STAGE";

    /** Message Parameter : Table Name (MDSE_ITEM_FLIP_SET) */
    public static final String MSG_PRM_TBL_MDSE_ITEM_FLIP_SET = "MDSE_ITEM_FLIP_SET";

    // ADD START 2016/03/07 QC#2669
    /** [@] is not exists in master. */
    public static final String NMAM8121E = "NMAM8121E";

    /** [@] is existed in master. */
    public static final String NMAM8440E = "NMAM8440E";

    /** This Item Code already registerd as Supersession */
    public static final String NMAM8377E = "NMAM8377E";

    /** Please avoid supersede circulation setting */
    public static final String NMAM8378E = "NMAM8378E";

    /** Message Parameter : Table Name (MDSE) */
    public static final String MSG_PRM_TBL_DS_MDSE_INFO = "MDSE";

    // ADD END 2016/03/07 QC#2669

    // --------------------------------
    // TABLE Display Item
    // --------------------------------
    /** Display Item : xxCheckbox */
    public static final String TBL_ITEM_CHECKBOX = "xxChkBox";

    // --------------------------------
    // SQL Parameter
    // --------------------------------
    /** SQL Parameter : glblCmpyCd */
    public static final String SQL_PRM_GLBLCMPYCD = "glblCmpyCd";

    /** SQL Parameter : rowNum */
    public static final String SQL_PRM_ROWNUM = "rowNum";

    /** SQL Parameter : supdRelnStagePk */
    public static final String SQL_PRM_SUPD_RELN_STAGE_PK = "supdRelnStagePk";

    /** SQL Parameter : itemNumber */
    public static final String SQL_PRM_ITEM_NUMBER = "itemNumber";

    /** SQL Parameter : itemRelnTpCd */
    public static final String SQL_PRM_ITEM_RELN_TP_CD = "itemRelnTpCd";

    /** SQL Parameter : compatible */
    public static final String SQL_PRM_COMPATIBLE = "compatible";

    /** SQL Parameter : mdseCd */
    public static final String SQL_PRM_MDSE_CD = "mdseCd";

    /** SQL Parameter : isForward */
    public static final String SQL_PRM_FORWARD = "isForward";

    /** SQL Parameter : mrpEnblFlg = 'N'(OLD) 'Y' (NEW) */
    public static final String SQL_PRM_MRP_ENBLE_FLG = "mrpEnblFlg";

    /** SQL ID : searchSupersedesStage */
    public static final String SQL_ID_SEARCH = "searchSupersedesStage";

    /** SQL ID : csvSupersession */
    public static final String SQL_CSV_SUPD = "csvSupersession";

    /** SQL ID : csvRelationShip */
    public static final String SQL_CSV_RELN = "csvRelationShip";

    /** SQL ID : csvRevision */
    public static final String SQL_CSV_REV = "csvRevision";

    /** SQL Constant : CP */
    public static final String SQL_CONST_COMPATIBLE = "CP";

    /** SQL Max Count : 1000 */
    public static final int SQL_MAX_COUNT = 1000;

    // ADD START 2016/03/04 QC#2669
    /** SQL ID : supdFromMdseCd */
    public static final String SQL_PRM_SUPD_FROM_MDSE_CD = "supdFromMdseCd";

    /** SQL ID : mdseItemRelnTpCd */
    public static final String SQL_PRM_MDSE_ITEM_RELN_TP_CD = "mdseItemRelnTpCd";

    /** SQL ID : mdseItemRelnTpCd */
    public static final String SQL_PRM_RELN_MDSE_CD = "relnMdseCd";

    // -------------------------------
    // DB COLUMN Parameter
    // -------------------------------
    /** DB COLUMN : SUPD_RELN_STAGE_PK */
    public static final String COL_SUPD_RELN_STAGE_PK = "SUPD_RELN_STAGE_PK";

    /** DB COLUMN : EZUPTIME */
    public static final String COL_EZUPTIME = "EZUPTIME";

    /** DB COLUMN : EZUPTIMEZONE */
    public static final String COL_EZUPTIMEZONE = "EZUPTIMEZONE";

    /** DB COLUMN : EZUPUSERID */
    public static final String COL_USR_NM = "USR_NM";

    /** DB COLUMN : FIRST_NM */
    public static final String COL_FIRST_NM = "FIRST_NM";

    /** DB COLUMN : LAST_NM */
    public static final String COL_LAST_NM = "LAST_NM";

    /** DB COLUMN : SUPD_FROM_MDSE_CD */
    public static final String COL_SUPD_FROM_MDSE_CD = "SUPD_FROM_MDSE_CD";

    /** DB COLUMN : SUPD_TO_MDSE_CD */
    public static final String COL_SUPD_TO_MDSE_CD = "SUPD_TO_MDSE_CD";

    /** DB COLUMN : SUPD_RELN_STAGE_DT */
    public static final String COL_SUPD_RELN_STAGE_DT = "SUPD_RELN_STAGE_DT";

    /** DB COLUMN : MDSE_DESC_SHORT_TXT_TO */
    public static final String COL_MDSE_DESC_SHORT_TXT_TO = "MDSE_DESC_SHORT_TXT_TO";

    /** DB COLUMN : MDSE_ITEM_STS_CD */
    public static final String COL_MDSE_ITEM_STS_CD = "MDSE_ITEM_STS_CD";

    /** DB COLUMN : MDSE_DESC_SHORT_TXT_FR */
    public static final String COL_MDSE_DESC_SHORT_TXT_FR = "MDSE_DESC_SHORT_TXT_FR";

    /** DB COLUMN : MDSE_ITEM_STS_NM */
    public static final String COL_MDSE_ITEM_STS_NM = "MDSE_ITEM_STS_NM";

    /** MAX SIZE OF UPDATE USER INFO  */
    public static final int UP_USER_MAX_TEXT_SIZE = 64;
    // ADD END 2016/03/04 QC#2669

    // -------------------------------
    // CSV Parameter
    // -------------------------------
    /** NMAL0190_ItemMasterSetupUpdates */
    public static final String CSV_FILE_NAME = "NMAL0190_ItemMasterSetupUpdates";

    /** CSV File Extension : .csv */
    public static final String CSV_FILE_EXTENSION = ".csv";

    /** CSV Parameter : CSV_RPM_CART_DT */
    public static final String CSV_PRM_ITEMNUMBER = "SUPD_TO_MDSE_CD";

    /** CSV Parameter : CSV_RPM_CART_DT */
    public static final String CSV_PRM_SUPD = "SUPD_FROM_MDSE_CD";

    /** CSV Parameter : CSV_RPM_CART_DT */
    public static final String CSV_PRM_DESC = "MDSE_DESC_SHORT_TXT";

    /** CSV Parameter : MDSE_ITEM_RELN_TP_NM */
    public static final String CSV_PRM_RELN_TP_NM = "MDSE_ITEM_RELN_TP_NM";

    /** CSV Parameter : CSV_RPM_CART_DT */
    public static final String CSV_RPM_CART_DT = "SUPD_CRAT_DT";

    /** CSV Parameter : MDSE_CD */
    public static final String CSV_RPM_MDSE_CD = "MDSE_CD";

    // ADD START 2016/03/24 QC#5660
    /** CSV Parameter : RELN_MDSE_CD */
    public static final String CSV_PRM_RELN_MDSE_CD = "RELN_MDSE_CD";

    /** CSV Parameter : EZINTIME */
    public static final String CSV_PRM_EZINTIME = "EZINTIME";
    // ADD END 2016/03/24 QC#5660
    /** CSV Parameter : RTL_WH_NM */
    public static final String CSV_PRM_RTL_WH_NM = "RTL_WH_NM";

    /** CSV Parameter : RTL_SWH_NM */
    public static final String CSV_PRM_RTL_SWH_NM = "RTL_SWH_NM";

    /** CSV Parameter : MRP_ENBL_FLG */
    public static final String CSV_PRM_MRP_ENBL_FLG = "MRP_ENBL_FLG";

    /** CSV Parameter : ROP_QTY */
    public static final String CSV_PRM_ROP_QTY = "ROP_QTY";

    /** CSV Parameter : MAX_INVTY_QTY */
    public static final String CSV_PRM_MAX_INVTY_QTY = "MAX_INVTY_QTY";

    /** CSV Constant String : Supersession */
    public static final String CSV_CONST_SUDP = "Supersession";

    /** CSV Constant String : New */
    public static final String CSV_CONST_NEW = "New";

    /** CSV Constant String : Disabled */
    public static final String CSV_CONST_DISABLED = "Disabled";

    /** Referenced Entity Item */
    public static String REF_ENTITY_ITEM = "ITEM";

    /** MDSE Code key Name */
    public static String MODE_CD_KEY_NAME = "MDSE_CD";

}
