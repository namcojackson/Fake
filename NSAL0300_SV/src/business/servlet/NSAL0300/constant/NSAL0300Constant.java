/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0300.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            SRAA            Create          N/A
 * 2015/10/16   Hitachi         T.Kanasaka      Update          N/A
 * 2016/01/07   Hitachi         T.Tomita        Update          QC#1029
 * 2016/01/21   Hitachi         T.Tomita        Update          QC#2182
 * 2016/02/09   Hitachi         T.Aoyagi        Update          QC4081
 * 2016/02/17   Hitachi         T.Kanasaka      Update          QC2879
 * 2016/02/18   Hitachi         T.Aoyagi        Update          QC3700
 * 2016/02/24   Hitachi         A.Kohinata      Update          QC3697
 * 2016/04/07   Hitachi         M.Gotou         Update          QC#5312,5113
 * 2016/04/26   Hitachi         T.Tomita        Update          QC#3886, QC#4668
 * 2016/06/20   Hitachi         M.Gotou         Update          QC#8130
 * 2016/07/01   Hitachi         T.Kanasaka      Update          QC#11279
 * 2016/07/01   Hitachi         T.Aoyagi        Update          QC#11261
 * 2016/07/28   Hitachi         T.Kanasaka      Update          QC#4806
 * 2016/09/23   Hitachi         T.Kanasaka      Update          QC#9905
 * 2016/10/26   Hitachi         T.Mizuki        Update          QC#3581
 * 2018/01/15   Hitachi         T.Tomita        Update          QC#18552
 * 2018/04/26   Hitachi         K.Kojima        Update          QC#23630
 * 2018/06/18   Hitachi         K.Kitachi       Update          QC#18591
 * 2018/07/30   Hitachi         K.Kim           Update          QC#14307(Sol#020)
 * 2024/03/22   Hitachi         Y.Tamai         Update          QC#63463
 *</pre>
 */
public class NSAL0300Constant {
    /**
     * Business Id
     */
    public static final String BIZ_ID = "NSAL0300";

    /**
     * Screen Id
     */
    public static final String SCR_ID_00 = "NSAL0300Scrn00";

    /**
     * Common button 1
     */
    public static final String[] BTN_CMN_BTN_1 = {"btn1", "CMN_Save", "Save" };

    /**
     * ｓ Common button 2
     */
    public static final String[] BTN_CMN_SUBMIT = {"btn2", "CMN_Submit", "Submit" };

    /**
     * Common button 3
     */
    public static final String[] BTN_CMN_BTN_3 = {"btn3", "CMN_Apply", "Apply" };

    /**
     * Common button 4
     */
    public static final String[] BTN_CMN_BTN_4 = {"btn4", "CMN_Approve", "Approve" };

    /**
     * Common button 5
     */
    public static final String[] BTN_CMN_BTN_5 = {"btn5", "CMN_Reject", "Reject" };

    /**
     * Common button 6
     */
    public static final String[] BTN_CMN_BTN_6 = {"btn6", "CMN_Download", "Download" };

    /**
     * Common button 7
     */
    public static final String[] BTN_CMN_BTN_7 = {"btn7", "CMN_Delete", "Delete" };

    /**
     * Common button 8
     */
    public static final String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /**
     * Common button 9
     */
    public static final String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /**
     * Common button 10
     */
    public static final String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    // START 2016/02/09 T.Aoyagi [QC4081, ADD]
    /**
     * Common button Close
     */
    public static final String CMN_CLOSE = "CMN_Close";
    // END 2016/02/09 T.Aoyagi [QC4081, ADD]

    /**
     * Button
     */
    public static final String BTN_OPENWIN_CONTRACT_NUM = "OpenWin_ContractNum";

    public static final String BTN_OPENWIN_TERMINATE = "OpenWin_Terminate";

    public static final String BTN_OPENWIN_RENEW = "OpenWin_Renew";

    public static final String BTN_OPENWIN_STATUS_CHANGE = "OpenWin_StatusChange";

    public static final String BTN_OPEN_FOR_UPDATE = "OpenForUpdate";

    public static final String BTN_OPENWIN_CONTRACT_STATUS_HISTORY = "OpenWin_ContractStatusHistory";

    public static final String BTN_OPENWIN_ADD_NOTES = "OpenWin_AddNotes";

    public static final String BTN_OPENWIN_BRANCH = "OpenWin_Branch";

    public static final String BTN_OPENWIN_REP = "OpenWin_Rep";

    public static final String BTN_OPENWIN_SALES_REP = "OpenWin_SalesRep";

    public static final String BTN_OPENWIN_REPORT_GRP = "OpenWin_ReportGrp";

    public static final String BTN_OPENWIN_NEW_REPORT_GRP = "OpenWin_NewReportGrp";

    public static final String BTN_OPENWIN_CUSTOMER = "OpenWin_Customer";

    public static final String BTN_OPENWIN_BILL_TO_LOC = "OpenWin_BillToLoc";

    // START 2024/03/22 Y.Tamai [QC#63463, ADD]
    /**
     * BTN_REVERT
     */
    public static final String BTN_REVERT = "Revert";
    // END 2024/03/22 Y.Tamai [QC#63463, ADD]

    // START 2018/07/30 K.Kim [QC#14307(Sol#020), ADD]
    public static final String BTN_OPENWIN_SPECIAL_INSTRUCTION = "OpenWin_SpecialInstruction";

    public static final String BTN_OPENWIN_SPECIAL_INSTRUCTION_BASE = "OpenWin_SpecialInstruction_Base";

    public static final String BTN_OPENWIN_SPECIAL_INSTRUCTION_METER = "OpenWin_SpecialInstruction_Meter";
    // END 2018/07/30 K.Kim [QC#14307(Sol#020), ADD]
    // START 2018/06/18 K.Kitachi [QC#18591, DEL]
//    public static final String BTN_OPENWIN_BILL_TO_CONTACT = "OpenWin_BillToContact";
    // END 2018/06/18 K.Kitachi [QC#18591, DEL]

    public static final String BTN_OPENWIN_LEASECOMPANY = "OpenWin_LeaseCompany";

    public static final String BTN_OPENWIN_CREDIT_CARD = "OpenWin_CreditCard";

    public static final String BTN_OPENWIN_PAYMENT_TERM = "OpenWin_PaymentTerm";

    public static final String BTN_OPENWIN_SERVICE_PROGRAM = "OpenWin_ServiceProgram";


    public static final String BTN_DOWNLOAD_MACHINE = "DownloadMachine";

    public static final String BTN_OPENWIN_SERIAL = "OpenWin_Serial";

    public static final String BTN_ADD_DETAIL = "Add_Detail";

    public static final String BTN_OPENWIN_ADD_DETAIL = "OpenWin_Add_Detail";

    public static final String BTN_UPLOAD = "OpenWin_MachineUpload";

    public static final String BTN_FILTER = "Filter";


    public static final String BTN_OPENWIN_ADDITIONALCHARGE = "OpenWin_AdditionalCharge";

    // START 2016/09/23 T.Kanasaka [QC#9905, ADD]
    public static final String BTN_OPENWIN_SHIP_TO = "OpenWin_ShipTo";
    // END 2016/09/23 T.Kanasaka [QC#9905, ADD]

    public static final String BTN_OPENWIN_PRICING_EFFECTIVITY_BASE = "OpenWin_PricingEffectivity_Base";

    public static final String BTN_OPENWIN_SCHEDULE_BASE = "OpenWin_Schedule_Base";

    public static final String BTN_OPENWIN_BILL_TO_BASE = "OpenWin_BillTo_Base";

    // START 2018/06/18 K.Kitachi [QC#18591, DEL]
//    public static final String BTN_OPENWIN_CONTACT_BASE = "OpenWin_Contact_Base";
    // END 2018/06/18 K.Kitachi [QC#18591, DEL]

    public static final String BTN_OPENWIN_SERVICE_PROGRAM_BASE = "OpenWin_ServiceProgram";


    public static final String BTN_OPENWIN_BILLING_COUNTERS = "OpenWin_BillingCounters";

    public static final String BTN_OPENWIN_PRICING_EFFECTIVITY_METER = "OpenWin_PricingEffectivity_Meter";

    public static final String BTN_OPENWIN_SCHEDULE_USAGE = "OpenWin_Schedule_Usage";

    public static final String BTN_OPENWIN_BILL_TO_METER = "OpenWin_BillTo_Meter";

    // START 2018/06/18 K.Kitachi [QC#18591, DEL]
//    public static final String BTN_OPENWIN_CONTACT_METER = "OpenWin_Contact_Meter";
    // END 2018/06/18 K.Kitachi [QC#18591, DEL]


    public static final String BTN_OPENWIN_TERMS = "OpenWin_Terms";

    public static final String BTN_OPENWIN_SUB_CONTRACT = "OpenWin_SubContract";

    public static final String BTN_GO = "Go";

    public static final String BTN_OPENWIN_COMPLETECONTRACT = "OpenWin_CompleteContract";
    // mod start 2016/10/26 CSA QC#3581
    public static final String BTN_OPENWIN_ATTACHMENTS = "OpenWin_Attachments";

    // Add Start 2018/01/14 QC#18552
    public static final String BTN_OPENWIN_CREDITCARDPO = "OpenWin_CreditCardPo";
    public static final String BTN_OPENWIN_UPLIFTDETAIL = "OpenWin_UpliftDetail";
    public static final String BTN_OPENWIN_ESCALATION = "OpenWin_Escalation";
    // Add End 2018/01/14 QC#18552

    // START 2018/04/26 K.Kojima [QC#23630,ADD]
    public static final String BTN_OPENWIN_FREECOPY_ROLLOVER_HISTORY = "OpenWin_FreeCopyRollOverHistory";
    // END 2018/04/26 K.Kojima [QC#23630,ADD]

    /**
     * Parameter Display Mode
     */
    public static final String PARAMS_DISPLAY_MODE = "Modification";

    /**
     * Parameter Upper Key
     */
    public static final String PARAMS_UPPER_KEY = "AR_NSAL0300_ATT_LMT";

    /**
     * Parameter Extension Key
     */
    public static final String PARAMS_EXTENSION_KEY = "AR_NSAL0300_AUTH_FILE_EXT";

    /**
     * Parameter Size Key
     */
    public static final String PARAMS_SIZE_KEY = "AR_NSAL0300_AUTH_FILE_VOL";
    // mod end 2016/10/26 CSA QC#3581
    /**
     * Function Id: Inquiry
     */
    public static final String FUNC_ID_INQ = "NSAL0300T010";

    /**
     * Function Id: Update
     */
    public static final String FUNC_ID_UPD = "NSAL0300T020";

    // START 2016/09/23 T.Kanasaka [QC#9905, ADD]
    /**
     * Function Id: ShipTo
     */
    public static final String FUNC_ID_SHIP_TO = "NSAL0300T030";
    // END 2016/09/23 T.Kanasaka [QC#9905, ADD]

    /**
     * Function Code: Inquiry
     */
    public static final String EZD_FUNC_CD_INQ = "20";

    /**
     * Function Code: Update
     */
    public static final String EZD_FUNC_CD_UPD = "40";

    /**
     * Default fraction digit
     */
    public static final int DEF_FRAC_DIGIT_NUM = 2;

    public static final BigDecimal INVLD_DS_CONTR_BLLG_MTR_PK = BigDecimal.ONE.negate();

    public static final String INVLD_SER_NUM = "XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4";

    public static final String INVLD_MDSE_CD = "XXXXXXXXX1XXXXXXXXX2";

    public static final String RADIO_DAY_OTHER = "1";

    public static final String RADIO_LAST_DAY_OF_A_MONTH = "2";

    /** Go Action BIZ_APP_ID : NSAL0410 Additional Charges */
    public static final String BIZ_APP_ID_NSAL0410 = "NSAL0410";

    /** Go Action BIZ_APP_ID : NSAL0750 Update Invoicing Rule */
    public static final String BIZ_APP_ID_NSAL0750 = "NSAL0750";

    /** Go Action BIZ_APP_ID : NSAL0720 Update Bill To */
    public static final String BIZ_APP_ID_NSAL0720 = "NSAL0720";

    /** Go Action BIZ_APP_ID : NSAL0600 Cascade Date */
    public static final String BIZ_APP_ID_NSAL0600 = "NSAL0600";

    /** Go Action BIZ_APP_ID : NSAL0610 Copy Contract */
    public static final String BIZ_APP_ID_NSAL0610 = "NSAL0610";

    /** Go Action BIZ_APP_ID : NSAL0390 Capture CC */
    public static final String BIZ_APP_ID_NSAL0390 = "NSAL0390";

    /** Go Action BIZ_APP_ID : NSAL0740 Change Annual Escalation */
    public static final String BIZ_APP_ID_NSAL0740 = "NSAL0740";

    /** Go Action BIZ_APP_ID : NSAL0550 Contract Invoice Search */
    public static final String BIZ_APP_ID_NSAL0550 = "NSAL0550";

    /** Go Action BIZ_APP_ID : NSAL0730 Update PO Information */
    public static final String BIZ_APP_ID_NSAL0730 = "NSAL0730";

    /** Go Action BIZ_APP_ID : NSAL0710 Update Read Methods */
    public static final String BIZ_APP_ID_NSAL0710 = "NSAL0710";

    /** Go Action BIZ_APP_ID : NSAL0380 Renewal */
    public static final String BIZ_APP_ID_NSAL0380 = "NSAL0380";

    /** Go Action BIZ_APP_ID : NSAL0450 Revenue Distributions */
    public static final String BIZ_APP_ID_NSAL0450 = "NSAL0450";

    /** Go Action BIZ_APP_ID : NSAL0460 Start Read Capture */
    public static final String BIZ_APP_ID_NSAL0460 = "NSAL0460";

    /** Go Action BIZ_APP_ID : NSAL0440 Terms & Conditions */
    public static final String BIZ_APP_ID_NSAL0440 = "NSAL0440";

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

    /**
     * The index number of the attribute of ColumnList DS_CONTR_RPT_GRP_NUM
     */
    public static final int DS_CONTR_RPT_GRP_NUM_LENGTH = 10;

    /**
     * The index number of the attribute of ColumnList DS_CONTR_RPT_GRP_DESC_TXT
     */
    public static final int DS_CONTR_RPT_GRP_DESC_TXT_LENGTH = 50;

    /**
     * The index number of the attribute of ColumnList DS_CONTR_RPT_GRP_START_DT
     */
    public static final int DS_CONTR_RPT_GRP_START_DT_LENGTH = 8;

    /**
     * The index number of the attribute of ColumnList DS_CONTR_RPT_GRP_END_DT
     */
    public static final int DS_CONTR_RPT_GRP_END_DT_LENGTH = 8;

    // START 2016/04/26 T.Tomita [QC#4668, MOD]
    /**
     * The index number of the attribute of ColumnList TOC_CD
     */
    public static final int TOC_CD_LENGTH = 0;

    /**
     * The index number of the attribute of ColumnList TOC_NM
     */
    public static final int TOC_NM_LENGTH = 40;
    // END 2016/04/26 T.Tomita [QC#4668, MOD]

    // START 2016/04/26 T.Tomita [QC#4668, ADD]
    /**
     * The index number of the attribute of ColumnList PSN_CD
     */
    public static final int PSN_CD_LENGTH = 11;

    /**
     * The index number of the attribute of ColumnList ORG_FUNC_ROLE_TP_DESC_TXT
     */
    public static final int ORG_FUNC_ROLE_TP_DESC_TXT_LENGTH = 40;
    // END 2016/04/26 T.Tomita [QC#4668, ADD]

    // add start 2016/07/01 CSA Defect#11261
    /**
     * Length 16
     */
    public static final int LEN_16 = 16;

    /**
     * Length 50
     */
    public static final int LEN_50 = 50;
    // add end 2016/07/01 CSA Defect#11261

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    // START 2016/02/17 T.Kanasaka [QC2879, ADD]
    /** Please click on save button the contract before adding a new machine. */
    public static final String NSAM0426E = "NSAM0426E";
    // END 2016/02/17 T.Kanasaka [QC2879, ADD]

    // START 2016/07/01 Kanasaka [QC#11279, ADD]
    /** Please click on save button before this action. */
    public static final String NSAM0552E = "NSAM0552E";
    // END 2016/07/01 Kanasaka [QC#11279, ADD]

    /** Please select item(s). */
    public static final String NSAM0034E = "NSAM0034E";

    /** Display Mode : Update */
    public static final String DISPLAY_MODE_UPDATE = "1";

    /** Display Mode : Freeze */
    public static final String DISPLAY_MODE_FREEZE = "0";

    /** Summary/Detail Mode : Summary */
    public static final String SUMMARY_MODE = "0";

    /** Summary/Detail Mode : Detail */
    public static final String DETAIL_MODE = "1";

    // START 2016/01/07 T.Tomita [QC#1029, ADD]
    /** Display Hierarchy Accounts Code: 01 */
    public static final String DISP_HRCH_ACCT_CD_ALL = "01";

    /** Display Hierarchy Accounts Code: 02 */
    public static final String DISP_HRCH_ACCT_CD_BILL = "02";
    // END 2016/01/07 T.Tomita [QC#1029, ADD]

    // START 2016/09/23 T.Kanasaka [QC#9905, ADD]
    /** Display Hierarchy Accounts Code: 03 */
    public static final String DISP_HRCH_ACCT_CD_SHIP = "03";
    // END 2016/09/23 T.Kanasaka [QC#9905, ADD]

    // START 2016/01/21 T.Tomita [QC#2182, ADD]
    /** Date Format: Default Effective Through Date */
    public static final String DEF_EFF_THRU_DT = "29991231";
    // END 2016/01/21 T.Tomita [QC#2182, ADD]

    // add start 2016/04/07 CSA Defect#5312,5313
    /** Divide String  */
    public static final String DIV_STR = "-";
    // add end 2016/04/07 CSA Defect#5312,5313

    // START 2016/04/26 T.Tomita [QC#3886, ADD]
    /** Percent : % */
    public static final String PERCENT = "%";
    // END 2016/04/26 T.Tomita [QC#3886, ADD]

    // START 2016/06/20 M.Gotou [QC#8130, ADD]
    /** Display Related Accounts Code: 06 */
    public static final String DISP_RELN_ACCT_CD_LEASE_BILL = "06";
    // END 2016/06/20 M.Gotou [QC#8130, ADD]

    /** Expand/Cllapse Icon Close */
    public static final String IMG_CLOSE_ARROW = "./img/biz/rightarrow2.png";

    /** Expand/Cllapse Icon Open */
    public static final String IMG_OPEN_ARROW = "./img/biz/downarrow2.png";

    /** Expand(Add)/Cllapse(Del) Icon Close Red */
    public static final String IMG_CLOSE_MACHINE_RED = "./img/biz/add-red.png";

    /** Expand(Add)/Cllapse(Del) Icon Close Green */
    public static final String IMG_CLOSE_MACHINE_GREEN = "./img/biz/add.png";

    /** Expand(Add)/Cllapse(Del) Icon Open Red */
    public static final String IMG_OPEN_MACHINE_RED = "./img/biz/delete.png";

    /** Expand(Add)/Cllapse(Del) Icon Open Green */
    public static final String IMG_OPEN_MACHINE_GREEN = "./img/biz/delete-green.png";

    /** Machine List Icon Close */
    public static final String IMG_CLOSE_MACHINE_ALL = "./img/wfcomp/S21WfPlus.gif";

    /** Machine List Icon Open */
    public static final String IMG_OPEN_MACHINE_ALL = "./img/wfcomp/S21WfMinus.gif";

    /** Html <div> style Close */
    public static final String DIV_STYLE_NO_DISPLAY = "display:none;";

    /** Html <div> style Open */
    public static final String DIV_STYLE_DISPLAY = "display:block;";

    /** CONTR_BR_FIRST_ORG_CD */
    public static final String CONTR_BR_FIRST_ORG_CD = "CONTR_BR_FIRST_ORG_CD";

    // START 2018/07/30 K.Kim [QC#14307(Sol#020), ADD]
    /** Function Category ID for Check Special Instruction */
    public static final String FUNC_CATG_ID = "CONTRACT";
    // END 2018/07/30 K.Kim [QC#14307(Sol#020), ADD]
}
