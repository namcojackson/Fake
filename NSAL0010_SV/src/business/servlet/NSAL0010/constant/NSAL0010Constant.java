/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0010.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2015/11/12   Hitachi         T.Tomita        Update          QC#431, 566
 * 2015/11/20   Hitachi         T.Tomita        Update          QC#959
 * 2016/01/04   Hitachi         T.Tomita        Update          QC#1029
 * 2016/02/04   Hitachi         T.Tomita        Update          QC#3312
 * 2016/02/09   Hitachi         A.Kohinata      Update          QC#947
 * 2016/02/26   Hitachi         T.Tomita        Update          QC#942
 * 2016/04/04   Hitachi         M.Gotou         Update          QC#4889
 * 2016/05/12   Hitachi         T.Tomita        Update          QC#7832
 * 2016/05/12   Hitachi         M.Gotou         Update          QC#7856
 * 2016/07/05   Hitachi         T.Tomita        Update          QC#10464
 * 2016/07/22   Hitachi         T.Tomita        Update          QC#11161
 * 2016/12/14   Hitachi         K.Kojima        Update          QC#15653
 * 2017/01/17   Hitachi         N.Arai          Update          QC#14614
 * 2017/01/25   Hitachi         N.Arai          Update          QC#14614-2
 * 2017/01/27   Hitachi         K.Kojima        Update          QC#16600
 * 2018/02/19   Hitachi         M.Kidokoro      Update          QC#23753
 * 2019/02/13   Hitachi         K.Kim           Update          QC#30309
 * 2023/07/10   Hitachi         Y.Nagasawa      Update          QC#61524
 *</pre>
 */
public class NSAL0010Constant {

    // [0]:Button Name [1]:Event Name [2]:Button Lavel
    /** Function Button 1 */
    public static final String[] BTN_CMN_SAVE = {"btn1", "CMN_Save", "Save" };

    /** Function Button 2 */
    public static final String[] BTN_CMN_SUBMIT = {"btn2", "CMN_Submit", "Submit" };

    /** Function Button 3 */
    public static final String[] BTN_CMN_APPLY = {"btn3", "CMN_Apply", "Apply" };

    /** Function Button 4 */
    public static final String[] BTN_CMN_APPROVE = {"btn4", "CMN_Approve", "Approve" };

    /** Function Button 5 */
    public static final String[] BTN_CMN_REJECT = {"btn5", "CMN_Reject", "Reject" };

    /** Function Button 6 */
    public static final String[] BTN_CMN_BLANK6 = {"btn6", "CMN_Download", "Download" };

    /** Function Button 7 */
    public static final String[] BTN_CMN_BLANK7 = {"btn7", "CMN_Delete", "Delete" };

    /** Function Button 8 */
    public static final String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /** Function Button 9 */
    public static final String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /** Function Button 10 */
    public static final String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    /** Button Search */
    public static final String[] BTN_SEARCH = {"Search", "Search" };

    /** Button SearchMdseName */
    public static final String[] BTN_SEARCH_MDSE_NAME = {"SearchMdseName", "SearchMdseName" };

    /** Button OpenWin_OwnerAcct */
    public static final String[] BTN_OPEN_WIN_OWNER_ACCT = {"OpenWin_OwnerAcct", "OpenWin_OwnerAcct" };

    /** Button OpenWin_BillToCust */
    public static final String[] BTN_OPEN_WIN_BILL_TO_CUST = {"OpenWin_BillToCust", "OpenWin_BillToCust" };

    /** Button OpenWin_CurLoc */
    public static final String[] BTN_OPEN_WIN_CUR_LOC = {"OpenWin_CurLoc", "OpenWin_CurLoc" };

    // START 2015/11/12 T.Tomita [QC#431, MOD]
    /** Button MoveWin_ConfigHist */
    public static final String[] BTN_MOVE_WIN_CONFIG_HIST = {"OpenWin_ConfigHist", "OpenWin_ConfigHist" };
    // END 2015/11/12 T.Tomita [QC#431, MOD]

    /** Button MoveWin_MtrReadHist */
    public static final String[] BTN_MOVE_WIN_MTR_READ_HIST = {"MoveWin_MtrReadHist", "MoveWin_MtrReadHist" };

    /** Button MoveWin_UpldContact */
    public static final String[] BTN_MOVE_WIN_UPLOAD_CONTACT = {"MoveWin_UpldContact", "MoveWin_UpldContact" };

    /** Button OpenWin_Attach */
    public static final String[] BTN_OPEN_WIN_ATTACH = {"OpenWin_Attach", "OpenWin_Attach" };

    /** Button InsertContractLine */
    public static final String[] BTN_INSERT_CONTRACT_LINE = {"InsertContractLine", "InsertContractLine" };

    /** Button DeleteContractLine */
    public static final String[] BTN_DELETE_CONTRACT_LINE = {"DeleteContractLine", "DeleteContractLine" };

    /** Button InsertChildMachineLine */
    public static final String[] BTN_INSERT_CHILD_LINE = {"InsertChildMachineLine", "InsertChildMachineLine" };

    /** Button InsertChildMachineLine */
    public static final String[] BTN_INSERT_PARENT_LINE = {"InsertParentMachineLine", "InsertParentMachineLine" };

    /** Button DeleteMachineLIne */
    public static final String[] BTN_DELETE_MACHINE_LINE = {"DeleteMachineLine", "DeleteMachineLine" };

    /** Button ConfigMnt */
    public static final String[] BTN_CONFIG_MNT = {"ConfigMnt", "ConfigMnt" };

    /** Button LinkNewConfig */
    public static final String[] BTN_OPEN_WIN_LINK_NEW_CONFIG = {"OpenWin_LinkNewConfig", "OpenWin_LinkNewConfig" };

    /** Button DeleteMachineLIne */
    public static final String[] BTN_REMOVE_CONFIG = {"RemoveConfig", "RemoveConfig" };

    /** Button Open Contact */
    public static final String[] BTN_OPEN_WIN_CONTACT = {"OpenWin_Contact", "OpenWin_Contact" };

    /** Button Open Contact */
    public static final String[] BTN_INACTIVE_CONTACT = {"InactiveContacts", "InactiveContacts" };

    /** Button Open Contact */
    public static final String[] BTN_INSERT_CONTACT = {"InsertContactLine", "InsertContactLine" };

    public static final String[] BTN_DELETE_CONTACT = {"DeleteContactLine", "DeleteContactLine" };

    // START 2015/11/12 T.Tomita [QC#566, ADD]
    /** Button Addl Attrb Tab : Insert */
    public static final String[] BTN_INSERT_DONOTSENDTECH = {"InsertDoNotSendTech", "InsertDoNotSendTech" };

    /** Button Addl Attrb Tab : Delete */
    public static final String[] BTN_DELETE_DONOTSENDTECH = {"DeleteDoNotSendTech", "DeleteDoNotSendTech" };

    // START 2023/07/10 Y.Nagasawa [QC#61524, DEL]
    /** Button Current Loc Tab : >> */
    //public static final String[] BTN_SELECT_POSTALCODE = {"SelectPostalCode", "SelectPostalCode" };
    // END 2023/07/10 Y.Nagasawa [QC#61524, DEL]
    // END 2015/11/12 T.Tomita [QC#566, ADD]

    /** Screen ID */
    public static final String SCREEN_ID = "NSAL0010Scrn00";

    /** Business ID */
    public static final String BUSINESS_ID = "NSAL0010";

    /** TAB_MachConfig */
    public static final String TAB_MACH_CONFIG = "MachConfig";

    /** TAB_Solution */
    public static final String TAB_SOLUTION = "Solution";

    /** TAB_Contacts */
    public static final String TAB_CONTACTS = "Contacts";

    /** TAB_AddlAttrb */
    public static final String TAB_ADDL_ATTRB = "AddlAttrb";

    // START 2023/07/10 Y.Nagasawa [QC#61524, DEL]
    /** TAB_CurrentLoc */
    //public static final String TAB_CURRENT_LOC = "CurrentLoc";
    // END 2023/07/10 Y.Nagasawa [QC#61524, DEL]

    /** TAB_SlsOrdHist */
    public static final String TAB_SLS_ORD_HIST = "SlsOrdHist";

    /** TAB_IBHistory */
    public static final String TAB_IB_HISTORY = "IBHistory";

    /** TAB_ContrSmry */
    public static final String TAB_CONTR_SMRY = "ContrSmry";

    /** TAB_SvcCallHist */
    public static final String TAB_SVC_CALL_HIST = "SvcCallHist";

    /** TAB_InvoiceHist */
    public static final String TAB_INVOICE_HIST = "InvoiceHist";

    /**
     * Close Button Name
     */
    public static final String POPUP_CLOSE = "CMN_Close";

    /** "@" is not entered. */
    public static final String NSAM0006E = "NSAM0006E";

    /** MODE_ALL_SERIALIZED_ITEMS */
    public static final String MODE_ALL_SERIALIZED_ITEMS = "1";

    /** READ */
    public static final String FUNC_ID_READ = BUSINESS_ID + "T010";

    /** UPDATE */
    public static final String FUNC_ID_UPDATE = BUSINESS_ID + "T020";

    /** SERIAL_CHANGE */
    public static final String FUNC_ID_SER_CHANGE = BUSINESS_ID + "T030";

    // START 2016/02/26 T.Tomita [QC#942 ADD]
    /** LGSC_USER */
    public static final String FUNC_ID_LGSC = BUSINESS_ID + "T040";
    // END 2016/02/26 T.Tomita [QC#942 ADD]

    // START 2016/05/12 M.Gotou [QC#7856 ADD]
    /** MDSE_EDIT_USER */
    public static final String FUNC_ID_MDSE_EDIT = BUSINESS_ID + "T050";
    // END 2016/05/12 M.Gotou [QC#7856 ADD]

    // START 2019/02/13 [QC#30309, ADD]
    /** DISPLAY_LIMITED_IB */
    public static final String FUNC_ID_DISPLAY_LIMITED_IB = BUSINESS_ID + "T060";
    // END 2019/02/13 [QC#30309, ADD]

    /** CONFIG_NUM_TITLE */
    public static final String CONFIG_NUM_TITLE = "Configuration#";

    /** CONFIG_TP_TITLE */
    public static final String CONFIG_TP_TITLE = "Configuration Type";

    /** SERIAL_NUM_TITLE */
    public static final String SERIAL_NUM_TITLE = "Serial#";

    /** MDSE_CD_TITLE */
    public static final String MDSE_CD_TITLE = "MdseCode";

    /** CLOSE */
    public static final String CLOSE = "CLOSE";

    // START 2016/02/26 T.Tomita [QC#942 ADD]
    /** Stock Status Title Name */
    public static final String STK_STS_TITLE_NM = "Stock Status";

    /** Location Status Title Name */
    public static final String LOC_STS_TITLE_NM = "Location Status";
    // END 2016/02/26 T.Tomita [QC#942 ADD]

    // add start 2016/02/09 QC#947
    /**
     * Configuration List Max size
     */
 // START 2017/01/25 N.Arai [QC#14614-2, MOD]
 // START 2017/01/17 N.Arai [QC#14614, MOD]
    // public static final int CONFIG_LIST_MAX_SIZE = 200;
    // public static final int CONFIG_LIST_MAX_SIZE = 1000;
    public static final int CONFIG_LIST_MAX_SIZE = 2000;
 // END 2017/01/17 N.Arai [QC#14614, MOD]
 // END 2017/01/25 N.Arai [QC#14614-2, MOD]

    /**
     * The number of the attribute of WhereList
     */
    public static final int ATTR_NUM_WHERE_LIST = 4;

    /**
     * The index number of the attribute of WhereList
     */
    public static final int WLIST_DSP_OBJ_NM = 0;

    /**
     * The index number of the attribute of WhereList
     */
    public static final int WLIST_OBJ_ID = 1;

    /**
     * The index number of the attribute of WhereList
     */
    public static final int WLIST_OBJ_VALUE = 2;

    /**
     * The index number of the attribute of WhereList
     */
    public static final int WLIST_WHERE_FLG = 3;

    /**
     * The number of the attribute of ColumnList
     */
    public static final int ATTR_NUM_CLMN_LIST = 4;

    /**
     * The index number of the attribute of ColumnList
     */
    public static final int CLIST_DSP_OBJ_NM = 0;

    /**
     * The index number of the attribute of ColumnList
     */
    public static final int CLIST_OBJ_ID = 1;

    /**
     * The index number of the attribute of ColumnList
     */
    public static final int CLIST_OBJ_LENGTH = 2;

    /**
     * The index number of the attribute of ColumnList
     */
    public static final int CLIST_LINK_FLG = 3;

    // START 2016/07/05 T.Tomita [QC#10464, ADD]
    /**
     * The number of the attribute of SortList
     */
    public static final int ATTR_NUM_SORT_LIST = 2;

    /**
     * The index number of the attribute of SortList
     */
    public static final int SLIST_OBJ_ID = 0;

    /**
     * The index number of the attribute of SortList
     */
    public static final int SLIST_ORD_BY = 1;
    // END 2016/07/05 T.Tomita [QC#10464, ADD]

    /**
     * TECH_CD LENGTH 
     */
    public static final int TECH_CD_LENGTH = 22;

    /**
     * TECH_NM LENGTH 
     */
    public static final int TECH_NM_LENGTH = 50;
    // add end 2016/02/09 QC#947

    // START 2016/02/04 T.Tomita [QC#3312, DEL]
//    /**
//     * SER_NUM Length : 30
//     */
//    public static final int SER_NUM_LENGTH = 30;
//
//    /**
//     * MDSE_CD Length : 16
//     */
//    public static final int MDSE_CD_LENGTH = 16;
//
//    /**
//     * MDSE_NM Length : 30
//     */
//    public static final int MDSE_NM_LENGTH = 30;
//
//    /**
//     * SVC_MACH_MSTR_STS_CD Length : 5
//     */
//    public static final int SVC_MACH_MSTR_STS_CD_LENGTH = 5;
//
//    /**
//     * SVC_CONFIG_MSTR_PK Length : 28
//     */
//    public static final int SVC_CONFIG_MSTR_PK_LENGTH = 28;
//
//    /**
//     * SVC_MACH_MSTR_PK Length : 28
//     */
//    public static final int SVC_MACH_MSTR_PK_LENGTH = 28;
//
//    /**
//     * T_MDL_NM Length : 50
//     */
//    public static final int T_MDL_NM_LENGTH = 50;
//
//    /**
//     * CUR_LOC_NUM Length : 30
//     */
//    public static final int CUR_LOC_NUM_LENGTH = 30;
    // END 2016/02/04 T.Tomita [QC#3312, DEL]

    // START 2016/07/05 T.Tomita [QC#10464, ADD]
    /**
     * PRC_CONTR_NUM LENGTH
     */
    public static final int PRC_CONTR_NUM_LENGTH = 33;

    /**
     * PRC_CONTR_NM LENGTH
     */
    public static final int PRC_CONTR_NM_LENGTH = 33;

    /**
     * ACTV_FLG LENGTH
     */
    public static final int ACTV_FLG_LENGTH = 8;

    /**
     * EFF_FROM_DT LENGTH
     */
    public static final int EFF_FROM_DT_LENGTH = 9;

    /**
     * EFF_THRU_DT LENGTH
     */
    public static final int EFF_THRU_DT_LENGTH = 9;
    // END 2016/07/05 T.Tomita [QC#10464, ADD]

    /**
     * Search
     */
    public static final String SEARCH = "Search";

    /**
     * OpenWin_SerialNum
     */
    public static final String OPENWIN_SERIALNUM = "OpenWin_SerialNum";

    /**
     * OpenWin_ParentSerialNum
     */
    public static final String OPENWIN_PARENTSERIALNUM = "OpenWin_ParentSerialNum";

    /**
     * OpenWin_MachIdA
     */
    public static final String OPENWIN_MACHIDA = "OpenWin_MachIdA";

    /**
     * OpenWin_LinkNewConfig
     */
    public static final String OPENWIN_LINKNEWCONFIG = "OpenWin_LinkNewConfig";

    /**
     * OpenWin_FieldServiceBranch
     */
    public static final String OPENWIN_FIELDSVCBR = "OpenWin_FieldServiceBranch";

    /**
     * OpenWin_PreferredTech
     */
    public static final String OPENWIN_PREFTECH = "OpenWin_PreferredTech";

    /**
     * OpenWin_RequestTech
     */
    public static final String OPENWIN_REQTECH = "OpenWin_RequestTech";

    /**
     * OpenWin_NonPreferredTech
     */
    public static final String OPENWIN_NONPREFTECH = "OpenWin_NonPreferredTech";

    // START 2016/01/04 T.Tomita [QC#1029, ADD]
    /**
     * OpenWin_OwnerAcct
     */
    public static final String OPENWIN_OWNERACCT = "OpenWin_OwnerAcct";

    /**
     * OpenWin_BillToCust
     */
    public static final String OPENWIN_BILLTOCUST = "OpenWin_BillToCust";

    /**
     * OpenWin_CurLoc
     */
    public static final String OPENWIN_CURLOC = "OpenWin_CurLoc";
    // END 2016/01/04 T.Tomita [QC#1029, ADD]

    // START 2016/07/05 T.Tomita [QC#10464, ADD]
    /**
     * OpenWin_PrcContr
     */
    public static final String OPENWIN_PRCCONTR = "OpenWin_PrcContr";
    // END 2016/07/05 T.Tomita [QC#10464, ADD]

    // START 2019/02/13 [QC#30309, DEL]
    // /**
    //  * Parameter Display Mode
    //  */
    // public static final String PARAMS_DISPLAY_MODE = "Upload-Only";
    // END 2019/02/13 [QC#30309, DEL]

    // START 2019/02/13 [QC#30309, ADD]
    /**
     * Parameter Display Mode : Modification
     */
    public static final String PARAMS_DISPLAY_MODE_MODIFICATION = "Modification";
    // END 2019/02/13 [QC#30309, ADD]

    // START 2018/02/19 M.Kidokoro [QC#23753, ADD]
    /**
     * Parameter Display Mode : Read-Only
     */
    public static final String PARAMS_DISPLAY_MODE_READ_ONLY = "Read-Only";
    // END 2018/02/19 M.Kidokoro [QC#23753, ADD]

    /**
     * Parameter Upper Key
     */
    public static final String PARAMS_UPPER_KEY = "AR_NSAL0010_ATT_LMT";

    /**
     * Parameter Extension Key
     */
    public static final String PARAMS_EXTENSION_KEY = "AR_NSAL0010_AUTH_FILE_EXT";

    /**
     * Parameter Size Key
     */
    public static final String PARAMS_SIZE_KEY = "AR_NSAL0010_AUTH_FILE_VOL";

    // START 2016/01/04 T.Tomita [QC#1029, ADD]
    /**
     * Parameter Bill To's Only
     */
    public static final String PARAMS_BILL_TO_ONLY = "02";

    /**
     * Parameter Ship To's Only
     */
    public static final String PARAMS_SHIP_TO_ONLY = "03";
    // END 2016/01/04 T.Tomita [QC#1029, ADD]

    /** Please select item(s). */
    public static final String NSAM0034E = "NSAM0034E";

    // START 2023/07/10 Y.Nagasawa [QC#61524, MOD]
    // public static final String[] NOT_DOWNLOAD_TAB = {TAB_MACH_CONFIG, TAB_SOLUTION, TAB_ADDL_ATTRB, TAB_CURRENT_LOC };
    public static final String[] NOT_DOWNLOAD_TAB = {TAB_MACH_CONFIG, TAB_SOLUTION, TAB_ADDL_ATTRB };
    // END 2023/07/10 Y.Nagasawa [QC#61524, MOD]

    public static final String[] NOT_SUBMIT_TAB = {TAB_SLS_ORD_HIST, TAB_IB_HISTORY, TAB_CONTR_SMRY, TAB_SVC_CALL_HIST, TAB_INVOICE_HIST };

    /** colon */
    public static final String CLN = ":";

    // START 2015/11/20 T.Tomita [QC#959, ADD]
    /** String Item : space */
    public static final String SPACE = " ";

    /** String Item : comma */
    public static final String COMMA = ",";
    // END 2015/11/20 T.Tomita [QC#959, ADD]

    // START 2016/07/05 T.Tomita [QC#10464, ADD]
    /** String Item : percent */
    public static final String PERCENT = "%";
    // END 2016/07/05 T.Tomita [QC#10464, ADD]

    // START 2016/02/04 T.Tomita [QC#3312, ADD]
    // START 2016/05/12 T.Tomita [QC#7832, MOD]
    /** parameter length (NSAL1240) */
    public static final int PARAM_LENGTH_NSAL1240 = 33;
    // END 2016/05/12 T.Tomita [QC#7832, MOD]

    // START 2016/07/05 T.Tomita [QC#10464, ADD]
    /** parameter length (NWAL1130) */
    public static final int PARAM_LENGTH_NWAL1130 = 7;
    // END 2016/07/05 T.Tomita [QC#10464, ADD]

    // START 2017/01/27 K.Kojima [QC#16600,ADD]
    /** parameter index : 2 */
    public static final int PARAM_INDEX_02 = 2;
    // END 2017/01/27 K.Kojima [QC#16600,ADD]

    /** parameter index : 26 */
    public static final int PARAM_INDEX_26 = 26;

    /** parameter index : 29 */
    public static final int PARAM_INDEX_29 = 29;

    /** parameter index : 30 */
    public static final int PARAM_INDEX_30 = 30;
    // END 2016/02/04 T.Tomita [QC#3312, ADD]

    // START 2016/04/04 M.Gotou [QC#4889, ADD]
    /** parameter index : 0 */
    public static final int PARAM_INDEX_0 = 0;

    /** MODE_CD : 03 */
    public static final String MODE_03 = "03";
    // END 2016/04/04 M.Gotou [QC#4889, ADD]

    // START 2016/05/12 T.Tomita [QC#7832, ADD]
    /** parameter index : 31 */
    public static final int PARAM_INDEX_31 = 31;

    /** parameter index : 32 */
    public static final int PARAM_INDEX_32 = 32;

    /** MODE_CD : 01 */
    public static final String MODE_01 = "01";

    /** MODE_CD : 02 */
    public static final String MODE_02 = "02";
    // END 2016/05/12 T.Tomita [QC#7832, ADD]

    // START 2016/07/22 T.Tomita [QC#11161, ADD]
    /**
     * SVC_BR_CD LENGTH
     */
    public static final int SVC_BR_CD_LENGTH = 12;

    /**
     * SVC_BR_CD_DESC_TXT LENGTH
     */
    public static final int SVC_BR_CD_DESC_TXT_LENGTH = 50;
    // END 2016/07/22 T.Tomita [QC#11161, ADD]

    // START 2016/12/14 K.Kojima [QC#15653,ADD]
    /** MAX_DT_VAL */
    public static final String MAX_DT_VAL = "29991231";
    // END 2016/12/14 K.Kojima [QC#15653,ADD]
}
