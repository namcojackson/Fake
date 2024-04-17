/*
 *  * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7100.constant;

/**
 *<pre>
 * NMAL7100Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/15/2015   Fujitsu         M.Hara          Create          CSA
 * 01/19/2016   Fujitsu         M.Hara          Create          QC#3089
 * 2016/02/24   Fujitsu         W.Honda         Create          CSA-QC#4043
 * 2016/12/09   Fujitsu         M.Ohno          Update          S21_NA#16485
 * 2019/12/09   Fujitsu         S.Kosaka        Update          QC#54215
*</pre>
 */
public class NMAL7100Constant {

    /** Business ID */
    public static final String BIZ_ID = "NMAL7100";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NMAL7100Scrn00";

    public static final String UPDATE_AUTHORITY = "NMAL7100T020";

    // --------------------------------
    // Tab
    // --------------------------------
    /** TAB : Marketing Program Audidence */
    public static final String TAB_MKT_AUDC = "MktAud";

    /** TAB : CanNotBeUsed */
    public static final String TAB_CAN_NOT_BE_USED = "CanNotBeUsed";

    // --------------------------------
    // Common button
    // --------------------------------
    /** F1 : Save - Button Name */
    public static final String BTN_01_SAV_NAME = "btn1";

    /** F1 : Save - Guard Condition */
    public static final String BTN_01_SAV_GUARD = "CMN_Save";

    /** F1 : Save - Label Name */
    public static final String BTN_01_SAV_LABEL = "Save";

    /** F2 : Submit - Button Name */
    public static final String BTN_02_SUB_NAME = "btn2";

    /** F2 : Submit - Guard Condition */
    public static final String BTN_02_SUB_GUARD = "CMN_Submit";

    /** F2 : Submit - Label Name */
    public static final String BTN_02_SUB_LABEL = "Submit";

    // 2019/12/09 QC#54215 Mod Start
//    /** F3 : Apply - Button Name */
//    public static final String BTN_03_APL_NAME = "btn3";
//
//    /** F3 : Apply - Guard Condition */
//    public static final String BTN_03_APL_GUARD = "CMN_Apply";
//
//    /** F3 : Apply - Label Name */
//    public static final String BTN_03_APL_LABEL = "Apply";
    /** F3 : Filter - Button Name */
    public static final String BTN_03_APL_NAME = "btn3";

    /** F3 : Filter - Guard Condition */
    public static final String BTN_03_APL_GUARD = "CMN_Filter";

    /** F3 : Filter - Label Name */
    public static final String BTN_03_APL_LABEL = "Filter";
    // 2019/12/09 QC#54215 Mod End

    /** F4 : Approve - Button Name */
    public static final String BTN_04_APR_NAME = "btn4";

    /** F4 : Approve - Guard Condition */
    public static final String BTN_04_APR_GUARD = "CMN_Approve";

    /** F4 : Approve - Label Name */
    public static final String BTN_04_APR_LABEL = "Approve";

    /** F5 : Reject - Button Name */
    public static final String BTN_05_REJ_NAME = "btn5";

    /** F5 : Reject - Guard Condition */
    public static final String BTN_05_REJ_GUARD = "CMN_Reject";

    /** F5 : Reject - Label Name */
    public static final String BTN_05_REJ_LABEL = "Reject";

    /** F6 : Download - Button Name */
    public static final String BTN_06_DWL_NAME = "btn6";

    /** F6 : Download - Guard Condition */
    public static final String BTN_06_DWL_GUARD = "CMN_Download";

    /** F6 : Download - Label Name */
    public static final String BTN_06_DWL_LABEL = "Download";

    /** F7 : Delete - Button Name */
    public static final String BTN_07_DEL_NAME = "btn7";

    /** F7 : Delete - Guard Condition */
    public static final String BTN_07_DEL_GUARD = "CMN_Delete";

    /** F7 : Delete - Label Name */
    public static final String BTN_07_DEL_LABEL = "Delete";

    /** F8 : Clear - Button Name */
    public static final String BTN_08_CLE_NAME = "btn8";

    /** F8 : Clear - Guard Condition */
    public static final String BTN_08_CLE_GUARD = "CMN_Clear";

    /** F8 : Clear - Label Name */
    public static final String BTN_08_CLE_LABEL = "Clear";

    /** F9 : Reset - Button Name */
    public static final String BTN_09_RST_NAME = "btn9";

    /** F9 : Reset - Guard Condition */
    public static final String BTN_09_RST_GUARD = "CMN_Reset";

    /** F9 : Reset - Label Name */
    public static final String BTN_09_RST_LABEL = "Reset";

    /** F10 : Return - Button Name */
    public static final String BTN_10_RTR_NAME = "btn10";

    /** F10 : Return - Guard Condition */
    public static final String BTN_10_RTR_GUARD = "CMN_Return";

    /** F10 : Return - Label Name */
    public static final String BTN_10_RTR_LABEL = "Return";

    // --------------------------------
    // Event
    // --------------------------------
    /** OpenWin_MktPrmo */
    public static final String EVT_OPENWIN_MKTPRMO = "OpenWin_MktPrmo";

    /** OpenWin_MktAudVal_01_Cmn */
    public static final String EVT_OPENWIN_MKTAUDVAL_01_CMN = "OpenWin_MktAudVal_01_Cmn";

    /** OpenWin_MktAudVal_02_Cmn */
    public static final String EVT_OPENWIN_MKTAUDVAL_02_CMN = "OpenWin_MktAudVal_02_Cmn";

    /** OpenWin_MktAudVal_03_Cmn */
    public static final String EVT_OPENWIN_MKTAUDVAL_03_CMN = "OpenWin_MktAudVal_03_Cmn";

    /** OpenWin_MktAudVal_01_Acc */
    public static final String EVT_OPENWIN_MKTAUDVAL_01_ACC = "OpenWin_MktAudVal_01_Acc";

    /** OpenWin_MktAudVal_02_Acc */
    public static final String EVT_OPENWIN_MKTAUDVAL_02_ACC = "OpenWin_MktAudVal_02_Acc";

    /** OpenWin_MktAudVal_03_Acc */
    public static final String EVT_OPENWIN_MKTAUDVAL_03_ACC = "OpenWin_MktAudVal_03_Acc";

    // Add Start #4032 02/12/2016
    /** OpenWin_PrmoQlfy_Item */
    public static final String EVT_OPENWIN_PRMOQLFY_ITEM = "OpenWin_PrmoQlfy_Item";

    /** OpenWin_PrmoQlfy_Mdl */
    public static final String EVT_OPENWIN_PRMOQLFY_MDL = "OpenWin_PrmoQlfy_Mdl";

    /** OpenWin_Prmo_Item */
    public static final String EVT_OPENWIN_PRMO_ITEM = "OpenWin_Prmo_Item";
    // Add End #4032 02/12/2016

    // Add Start #4043 02/24/2016
    /** OpenWin_PrcCatg */
    public static final String EVT_OPENWIN_PRCCATG = "OpenWin_PrcCatg";
    // Add End #4045 02/24/2016

    // Add Start 2017/02/24 QC#16011
    /** EVT_UPLOAD_MKT_AUD */
    public static final String EVT_UPLOAD_MKT_AUD = "Upload_MktAud";

    /** EVT_ADD_MKT_AUD */
    public static final String EVT_ADD_MKT_AUD = "Add_MktAud";

    /** EVT_DEL_MKT_AUD */
    public static final String EVT_DEL_MKT_AUD = "Del_MktAud";

    /** EVT_ADD_CAN_NOT_BE_USED */
    public static final String EVT_ADD_CAN_NOT_BE_USED = "Add_CanNotBeUsed";

    /** EVT_DEL_CAN_NOT_BE_USED */
    public static final String EVT_DEL_CAN_NOT_BE_USED = "Del_CanNotBeUsed";

    /** EVT_SELECT_ALL */
    public static final String EVT_SELECT_ALL = "SelectAll";

    /** EVT_UN_SELECT_ALL */
    public static final String EVT_UN_SELECT_ALL = "UnselectAll";

    /** EVT_ADD_DETAIL */
    public static final String EVT_ADD_DETAIL = "Add_Detail";

    /** EVT_DEL_DETAIL */
    public static final String EVT_DEL_DETAIL = "Del_Detail";

    /** EVT_MASS_UPD */
    public static final String EVT_MASS_UPD = "MassUpd_MktList";

    /** EVT_UPLOAD_MKT_PROGRAM */
    public static final String EVT_UPLOAD_MKT_PROGRAM = "Upload_MktPgm";

    /** EVT_DOWNLOAD_TEMPLATE */
    public static final String EVT_DOWNLOAD_TEMPLATE = "Download_Temlate";
    // Add End 2017/02/24 QC#16011

    // --------------------------------
    // Page button
    // --------------------------------

    /** TXT_MKT_AUD_01 */
    public static final String TXT_MKT_AUD_01 = "xxScrItem30Txt_X1";

    /** BTN_MKT_AUD_01 */
    public static final String BTN_MKT_AUD_01 = "OpenWin_MktAudVal_01";

    /** BTN_MKT_AUD_02 */
    public static final String BTN_MKT_AUD_02 = "OpenWin_MktAudVal_02";

    /** BTN_MKT_AUD_03 */
    public static final String BTN_MKT_AUD_03 = "OpenWin_MktAudVal_03";
    
    // Add Start #4032 02/12/2016 
    /** PROM_QLFY : OpenWin_PrmoQlfy */
    public static final String PRMO_QLFY  = "OpenWin_PrmoQlfy";
    // Add End #4032 02/12/2016 

    // --------------------------------
    // Other
    // --------------------------------
    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** Common */
    public static final String BTN_CMN = "_Cmn";

    /** Account Number*/
    public static final String BTN_ACC = "_Acc";
    
    // Add Start #4032 02/12/2016 
    /** Account Number*/
    public static final String BTN_ITEM = "_Item";
    
    /** Account Number*/
    public static final String BTN_MDL = "_Mdl";
    
    /** Button Flag : Model*/
    public static final String BTN_FLG_MDL = "M";
    
    /** Button Flag : Item*/
    public static final String BTN_FLG_ITEM = "I";
    
    /** Button Flag : Null*/
    public static final String BTN_FLG_NULL = "N";

    /** Common Pop-up Parameter/Where */
    public static final int CMN_PRM_WHERE_NUM = 4;

    /** Common Pop-up Parameter/Where */
    public static final int CMN_PRM_COLUMN_NUM = 4;
    // Add End #4032 02/12/2016 

    // Add Start #4032 02/12/2016 
    /** Common Pop-up Parameter/Where */
    public static final String HIGH_VAL_DT = "99991231";
    // Add End #4032 02/12/2016 

    // Add 2016/12/09 M.Ohno S21_NA#16485
    /** MDSE_CD_MAX_LENGTH*/
    public static final int MDSE_CD_MAX_LENGTH = 16;

    // --------------------------------
    // Message ID
    // --------------------------------

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Remove the selected records. is it OK? */
    public static final String NMAM8234I = "NMAM8234I";

    /** The file with other than the file extension "csv" "txt" can't be uploaded. */
    public static final String ZYEM0096E = "ZYEM0096E";

    /** Detail requires at least one line.  Please enter. */
    public static final String NMAM8190E = "NMAM8190E";

    /** Please select item(s). */
    public static final String NMAM8054E = "NMAM8054E";

    // START QC#3089 01/19/2016 ADD
    /** Do you want to execute [@]?  If 'yes', click again. */
    public static final String NMAM8327I = "NMAM8327I";
    // END QC#3089 01/19/2016 ADD

    // Add 2016/12/09 M.Ohno S21_NA#16485
    /** The entered [@] does not exist in [@].*/
    public static final String NMAM0163E = "NMAM0163E";
}
