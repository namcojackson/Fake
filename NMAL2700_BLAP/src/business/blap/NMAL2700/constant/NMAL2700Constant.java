/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2700.constant;

/**
 *<pre>
 * NMAL2700Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/05   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NMAL2700Constant {

    //--------------------------------
    // Message ID
    //--------------------------------
    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** Please select at least 1 checkbox.*/
    public static final String NMAM0835E = "NMAM0835E";

    /** Role Code(@) cannot be deleted because it is being used by another Request.*/
    public static final String NMAM8337E = "NMAM8337E";

    /** This data has been updated by another user. */
    public static final String ZZZM9004E = "ZZZM9004E";

    /** The process ended abnormally. */
    public static final String NMAM8020E = "NMAM8020E";

    /** Business area can not be change, because selected role is set to TOC. */
    public static final String NMAM8576E = "NMAM8576E";

    /** bizMsg  */
    public static final String DB_PARAM_BIZMSG = "bizMsg";

    /** Global Compy Code  */
    public static final String GLOBAL_CMPY_CODE = "glblCmpyCd";

    /** Territory Structure Flag  */
    public static final String TRTY_STRU_FLG = "trtyStruFlg";

    /** Biz Area Org Nm*/
    public static final String BIZ_AREA_ORG_NM  = "bizAreaOrgNm";

    /** OrgfuncRoleTpCd*/
    public static final String ORG_FUNC_ROLE_TP_CD = "orgfuncRoleTpCd";

    /** OrgfuncRoleTpNm*/
    public static final String ORG_FUNC_ROLE_TP_NM = "orgfuncRoleTpNm";

    /** OrgfuncRoleTpDesc */
    public static final String ORG_FUNC_ROLE_TP_DESC = "orgfuncRoleTpDesc";

    /** BIZ_AREA_ORG_CD */
    public static final String BIZ_AREA_ORG_CD_DBCOLUMN = "BIZ_AREA_ORG_CD";

    /** BIZ_AREA_ORG_NM */
    public static final String BIZ_AREA_ORG_NM_DBCOLUMN = "BIZ_AREA_ORG_NM";

    /** MgrFlag */
    public static final String MGR_FLAG  = "mgrFlag";

    /** SpclFlg*/
    public static final String SPCL_FLG  = "spclFlg";

    /** EquipFlg*/
    public static final String EQUIP_FLG  = "equipFlg";

    /** CmsnFlg*/
    public static final String CMSN_FLAG  = "cmsnFlg";

    /** ActvFlg */
    public static final String ACTV_FLAG  = "actvFlg";

    /** Name  */
    public static final String NAME = "name";

    /** Max Row Size */
    public static final int MAX_ROW_SIZE = 201;

    /** Row Num */
    public static final String ROW_NUM = "rowNum";

    /** List size 200 */
    public static final int LIST_SIZE_200 = 200;

    /** Role List*/
    public static final String ROLE_LIST = "roleList";

    /** Check Box */
    public static final String CHK_A = "xxChkBox_A";

    /** [@] is duplicated */
    public static final String NMAM0072E = "NMAM0072E";

    /** It failed to register [@].*/
    public static final String NMAM0176E = "NMAM0176E";

    /** ORG FUNC ROLE TP */
    public static final String ORG_FUNC_ROLE_TP = "ORG_FUNC_ROLE_TP";

    /** Max Fetch Size */
    public static final int MAX_FETCH_SIZE = 1000;

    /** Csv FileName */
    public static final String CSV_FILE_NAME =  "NMAL2700 Role Maintenance";

    /** Limit Down load RowNumber */
    public static final int LIMIT_DL_ROWNUM = 65001;

    /** Glblcmpycd */
    public static final String GLBLCMPYCD01 = "glblCmpyCd01";

    /** Org Func Role Tp 01 */
    public static final String ORG_FUNC_ROLE_TP01 = "orgFuncRoleTpCd01";

    /** Sales */
    public static final String SALES = "1";

    /** Service */
    public static final String SERVICE = "2";

    /** Bos */
    public static final String BOS = "BOS";

    /** Rgtn Sts Cd */
    public static final String RGTN_STS_CD = "rgtnStsCd";

}
