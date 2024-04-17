package business.blap.ZZVL0000.constant;

/** 
 *<pre>
 * ZZVL0000Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/11/22   Fujitsu         C.Ogaki         Create          N/A
 * 2017/01/27   Fujitsu         C.Ogaki         Update          ---
 *</pre>
 */
public class ZZVL0000Constant {

    /** Preferred View Setting max */
    public static final int PREFERRED_VIEW_SETTING_MAX = 5;

    /** Original Setting not found error */
    public static final int SETTING_NOT_FOUND = -2;

    // START 01/27/17 C.Ogaki [Add] Release 2000 byte length limit
    /** usrDefFlg */
    public static final int INDEX_USR_DEF_FLG = 6;

    /** orgUsr */
    public static final int INDEX_ORG_USR_ID = 11;
    // END   01/27/17 C.Ogaki [Add] Release 2000 byte length limit

    // Messages
    /** The process terminated. Table Name: [@], Deleted Records: [@] */
    public static final String ZZVM0001I = "ZZVM0001I";

    /** The number of settings exceeds the maximum. [roleId : @, scrAppId : @ ] */
    public static final String ZZVM0002W = "ZZVM0002W";

    /** Not found Preferred View Setting in SCR_TBL_COL_DEF table. [UsrId : @ , scrAppId : @ , scrTblNm : @, scrTblColDefNm: @ ] */
    public static final String ZZVM0003E = "ZZVM0003E";

    /** The search ended normally. */
    public static final String ZZM8002I = "ZZM8002I";

    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";
}
