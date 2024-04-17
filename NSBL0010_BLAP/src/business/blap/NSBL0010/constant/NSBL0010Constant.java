/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0010.constant;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ORD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;

/**
 * <pre>
 * Service Dispatch Schedule/Dispatch Screen Constant
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/04/29   SRA             E.Inada         Create          N/A
 * 01/16/2014   Fujitsu         M.Fuji          Update          WDS Defect#3306
 * 2014/06/02   Hitachi         T.Aoyagi        Update          CSA-166
 * 2015/11/18   Hitachi         T.Harada        Update          CSA
 * 2016/10/19   Hitachi         N.Arai          Update          QC#13901
 * 2016/12/14   Hitachi         K.Kojima        Update          QC#15653
 * 2017/01/05   Hitachi         N.Arai          Update          QC#13901-2
 *</pre>
 */
public interface NSBL0010Constant {

    /** Business ID */
    String BUSINESS_ID = "NSBL0010";

    /** Max detail num */
    int MAX_DETAIL = 40;

    /** Max detail num */
    int MAX_SEARCH = 200;

    /** Max detail num */
    int MAX_CSV_ROW_COUNT = 65000;

    /** Max pulldown list num */
    int PULLDOWN_LIST_MAX = 99;

    /** 60 min */
    int HOUR_MIN = 60;

    /** 60 sec * 1000 */
    int HOUR_MIN_SEC = 60000;

    /** max 9999 */
    long MAX_RSP_TM = 599400;

    /** hour size */
    int AVAL_HOUR_SIZE = 4;

    /** Message Kind */
    String MSG_KIND_ERR = "E";

    /** Message Kind */
    String MSG_KIND_WARN = "W";

    /** Table Name: DS_SVC_CALL_TP */
    String TBL_DS_SVC_CALL_TP = "DS_SVC_CALL_TP";

    /** Table Name: SVC_BILL_TP */
    String TBL_SVC_BILL_TP = "SVC_BILL_TP";

    /** Table Name: SVC_TASK_STS */
    String TBL_SVC_TASK_STS = "SVC_TASK_STS";

    /** Table Name: TM_ZONE */
    String TBL_TM_ZONE = "TM_ZONE";

    /** String : Colon ":" */
    String COLON = ":";

    /** string */
    String CHK_A_CHK_BOX = "xxChkBox_A";

    /** string */
    String STR_DEF_DT = "        ";

    /** date length */
    int LEN_DT = 8;

    /** format */
    String FORMAT_SCHD_TM_FROM = "^([0-1][0-9]|2[0-3])([0-5][0-9]).*$";

    /** format */
    String FORMAT_SCHD_TM_TO = "$1:$2";

    /** format */
    String FORMAT_RCV_TM_FROM = "^([0-1][0-9]|2[0-3])([0-5][0-9])([0-5][0-9]).*$";

    /** format */
    String FORMAT_RCV_TM_TO = "$1:$2";

    /** format */
    String FORMAT_RSP_TM = "%02d:%02d";

    /** Format System Date Time */
    String FORMAT_SYS_DT_TM = "yyyyMMddHHmmss";

    /** format */
    String FORMAT_AVAL_HOUR_FROM = "^(..)(..)$";

    /** format */
    String FORMAT_AVAL_HOUR_TO = "$1:$2";

    /** CSV Header */
// START 2016/10/19 N.Arai [QC#13901, MOD]
//    String[] CSV_HEADER = {"Tech Code", "Layer", "Org Code", "Org Name", "Model", "Schedule Date(From)", "Schedule Time(From)", "Schedule Date(To)", "Schedule Time(To)", "Schedule Timezone", "Priority", "Original Priority",
    String[] CSV_HEADER = {"Tech Code", "Branch", "Branch Name", "BU", "Model", "Schedule Date(From)", "Schedule Time(From)", "Schedule Date(To)", "Schedule Time(To)", "Schedule Timezone", "Priority", "Original Priority",
// END 2016/10/19 N.Arai [QC#13901, MOD]
            "Call Type Code", "Call Type Name", "Bill Type Code", "Bill Type Name", "Symptom Code", "Symptom Name", "Task Received Date", "Task Received Time", "Task Received Timezone", "Serial#", "Available Hour From",
            "Available Hour To", "Response Group", "Response Time", "Task#", "FSR#", "Visit#", "Status", "ShipTo Code", "ShipTo Name", "Tech Accept Flag", "Hold Flag", "Tech Available Flag", "Machine Down Flag", "Memo Exists Flag",
            "Excessive Visit Flag", "Committed Order Flag", "Notify Flag" };

    /** SVC_TASK_STS_CD : Waiting for Scheduling */
    String SVC_TASK_STS_CD_WAITING_FOR_SCHEDULING = "ZX";

    /** SVC_TASK_STS_CD : Active Task/FSR */
    String SVC_TASK_STS_CD_ACTIVE_TASK_FSR = "ZY";

    /** SVC_TASK_STS_NM : Waiting for Scheduling */
    String SVC_TASK_STS_NM_WAITING_FOR_SCHEDULING = "Waiting for Scheduling";

    /** SVC_TASK_STS_NM : Active Task/FSR */
    String SVC_TASK_STS_NM_ACTIVE_TASK_FSR = "Active Task/FSR";

    /** Status List */
    // START 2015/11/18 T.Harada [CSA,CHANGE]
    //String[] WAITING_FOR_SCHEDULING_STS_CD_LIST = new String[] {SVC_TASK_STS.APPROVED, SVC_TASK_STS.CONTINUOUS_OPEN };
    String[] WAITING_FOR_SCHEDULING_STS_CD_LIST = new String[] {SVC_TASK_STS.TBO, SVC_TASK_STS.OPEN };
    // END 2015/11/18 T.Harada [CSA,CHANGE]

    /** Status List */
    String[] ACTIVE_TASK_FSR_STS_CD_LIST = new String[] {SVC_TASK_STS.COMPLETED, SVC_TASK_STS.CANCELLED };

    // ----------- API Mode Code ------------
    /** Mode: 01 : Schedule */
    String MODE_SCHEDULE = "01";

    /** Mode: 02 : Dispatch */
    String MODE_DISPATCH = "02";

    /** Mode: 03 : Cancel */
    String MODE_CANCEL = "03";

    // START 2015/11/18 T.Harada [CSA,ADD]
    /** Mode: 07 :Update Call */
    String UPDATE_CALL = "07";
    // END 2015/11/18 T.Harada [CSA,ADD]

    // ----------- Search Values ------------
    /** reject status list */
    String[] REJ_STS_LIST = {SVC_TASK_STS.WAITING_FOR_CUSTOMER_APPROVAL, SVC_TASK_STS.CUSTOMER_APPROVED, SVC_TASK_STS.CUSTOMER_REJECTED, SVC_TASK_STS.CUSTOMER_CANCELLED, SVC_TASK_STS.IN_PROCESS };

    /** format */
    String SQL_FORMAT_TM = "yyyymmddhh24miss";

    /** Status List */
    String[] PRCH_REQ_STS_CD_LIST = new String[] {PRCH_REQ_STS.RELEASED, PRCH_REQ_STS.CANCELLED };

    /** Status List */
    String[] PO_STS_CD_LIST = new String[] {PO_STS.CANCELLED, PO_STS.CLOSED };

    /** Status List */
    String[] INVTY_ORD_STS_LIST = new String[] {INVTY_ORD_STS.CANCEL, INVTY_ORD_STS.CLOSED };

    /** DT_ATTRB_VAL_CD : 1 : Calendar business day */
    String DT_ATTRB_VAL_CD = "1";

    /** TECH_BAK_TP_CD : Standby */
    String TECH_BAK_TP_CD = "1";

    /** SVC_MEMO_CATG_TP_CD : Dispatch Memo */
    String SVC_MEMO_CATG_TP_CD = "03";

    /** VarCharConst Key */
    String KEY_SVC_DISPT_CSV_DT_FORMAT = "SVC_DISPT_CSV_DT_FORMAT";

    /** VarCharConst Key */
    String KEY_SVC_DISPT_CSV_TM_FORMAT = "SVC_DISPT_CSV_TM_FORMAT";

    // ----------- DB Column Name -----------
    /** Column Name : TECH_CD */
    String TECH_CD = "TECH_CD";

    /** Column Name : ORG_LAYER_NUM */
    String ORG_LAYER_NUM = "ORG_LAYER_NUM";

    /** Column Name : ORG_CD */
    String ORG_CD = "ORG_CD";

    /** Column Name : ORG_NM */
    String ORG_NM = "ORG_NM";

    /** Column Name : MDL_NM */
    String MDL_NM = "MDL_NM";

    /** Column Name : TECH_SCHD_FROM_DT */
    String TECH_SCHD_FROM_DT = "TECH_SCHD_FROM_DT";

    /** Column Name : TECH_SCHD_FROM_TM */
    String TECH_SCHD_FROM_TM = "TECH_SCHD_FROM_TM";

    /** Column Name : TECH_SCHD_TO_DT */
    String TECH_SCHD_TO_DT = "TECH_SCHD_TO_DT";

    /** Column Name : TECH_SCHD_TO_TM */
    String TECH_SCHD_TO_TM = "TECH_SCHD_TO_TM";

    /** Column Name : TECH_SCHD_TZ */
    String TECH_SCHD_TZ = "TECH_SCHD_TZ";

    /** Column Name : SVC_CALL_PRTY_CD */
    String SVC_CALL_PRTY_CD = "SVC_CALL_PRTY_CD";

    /** Column Name : ORIG_SVC_CALL_PRTY_CD */
    String ORIG_SVC_CALL_PRTY_CD = "ORIG_SVC_CALL_PRTY_CD";

    /** Column Name : DS_SVC_CALL_TP_CD */
    String DS_SVC_CALL_TP_CD = "DS_SVC_CALL_TP_CD";

    /** Column Name : DS_SVC_CALL_TP_NM */
    String DS_SVC_CALL_TP_NM = "DS_SVC_CALL_TP_NM";

    /** Column Name : SVC_BILL_TP_CD */
    String SVC_BILL_TP_CD = "SVC_BILL_TP_CD";

    /** Column Name : SVC_BILL_TP_NM */
    String SVC_BILL_TP_NM = "SVC_BILL_TP_NM";

    /** Column Name : SVC_PBLM_SYMP_TP_CD */
    String SVC_PBLM_SYMP_TP_CD = "SVC_PBLM_SYMP_TP_CD";

    /** Column Name : SVC_PBLM_SYMP_TP_NM */
    String SVC_PBLM_SYMP_TP_NM = "SVC_PBLM_SYMP_TP_NM";

    /** Column Name : SVC_TASK_RCV_TZ */
    String SVC_TASK_RCV_TZ = "SVC_TASK_RCV_TZ";

    /** Column Name : SER_NUM */
    String SER_NUM = "SER_NUM";

    /** Column Name : CUST_AVAL_FROM_HOUR_MN */
    String CUST_AVAL_FROM_HOUR_MN = "CUST_AVAL_FROM_HOUR_MN";

    /** Column Name : CUST_AVAL_TO_HOUR_MN */
    String CUST_AVAL_TO_HOUR_MN = "CUST_AVAL_TO_HOUR_MN";

    /** Column Name : SVC_RSP_TM_MN_AOT */
    String SVC_RSP_TM_MN_AOT = "SVC_RSP_TM_MN_AOT";

    /** Column Name : SVC_RSP_TM_NUM */
    String SVC_RSP_TM_NUM = "SVC_RSP_TM_NUM";

    /** Column Name : SVC_TASK_NUM */
    String SVC_TASK_NUM = "SVC_TASK_NUM";

    /** Column Name : FSR_NUM */
    String FSR_NUM = "FSR_NUM";

    /** Column Name : FSR_VISIT_NUM */
    String FSR_VISIT_NUM = "FSR_VISIT_NUM";

    /** Column Name : SVC_TASK_STS_CD */
    String SVC_TASK_STS_CD = "SVC_TASK_STS_CD";

    /** Column Name : SVC_TASK_STS_NM */
    String SVC_TASK_STS_NM = "SVC_TASK_STS_NM";

    /** Column Name : SHIP_TO_CUST_CD */
    String SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /** Column Name : LOC_NM */
    String LOC_NM = "LOC_NM";

    /** Column Name : TECH_ACPT_FLG */
    String TECH_ACPT_FLG = "TECH_ACPT_FLG";

    /** Column Name : SVC_CR_HLD_FLG */
    String SVC_CR_HLD_FLG = "SVC_CR_HLD_FLG";

    /** Column Name : TECH_AVAL_FLG */
    String TECH_AVAL_FLG = "TECH_AVAL_FLG";

    /** Column Name : MACH_DOWN_FLG */
    String MACH_DOWN_FLG = "MACH_DOWN_FLG";

    /** Column Name : SCHD_DISPT_EML_FLG */
    String SCHD_DISPT_EML_FLG = "SCHD_DISPT_EML_FLG";

    /** Column Name : SVC_MEMO_FLG */
    String SVC_MEMO_FLG = "SVC_MEMO_FLG";

    /** Column Name : CO_FLG */
    String CO_FLG = "CO_FLG";

    /** Column Name : EX_VISIT_FLG */
    String EX_VISIT_FLG = "EX_VISIT_FLG";

    /** Column Name : XX_DT_TM_A1 */
    String XX_DT_TM_A1 = "XX_DT_TM_A1";

    /** Column Name : XX_DT_TM_A2 */
    String XX_DT_TM_A2 = "XX_DT_TM_A2";

    /** Column Name : XX_DT_TM_A3 */
    String XX_DT_TM_A3 = "XX_DT_TM_A3";

    /** Column Name : XX_DT_TM_A4 */
    String XX_DT_TM_A4 = "XX_DT_TM_A4";

    /** Column Name : XX_DT_TM_A5 */
    String XX_DT_TM_A5 = "XX_DT_TM_A5";

    /** Column Name : XX_DT_TM_A6 */
    String XX_DT_TM_A6 = "XX_DT_TM_A6";

    // START 2014/06/02 T.Aoyagi [CSA-166,ADD]
    /** Column Name : SVC_CALL_CPLT_TP_CD */
    String SVC_CALL_CPLT_TP_CD = "SVC_CALL_CPLT_TP_CD";
    // END 2014/06/02 T.Aoyagi [CSA-166,ADD]

    // ----------- Error Message ------------
    /** No search results found. */
    String NSBM0002E = "NSBM0002E";

    /** Search results exceeded [@]. Please modify search criteria. */
    String NSBM0003E = "NSBM0003E";

    /** This data has been updated by another user. */
    String NSBM0006E = "NSBM0006E";

    /** Please check at least 1 checkbox. */
    String NSBM0007E = "NSBM0007E";

    /** Search results exceeded [@]. Please modify search criteria. */
    String NSBM0009W = "NSBM0009W";

    /** "@" process is only available in [@] status. */
    String NSBM0010E = "NSBM0010E";

    /** The data @ does not exist in the master. */
    String NSBM0011E = "NSBM0011E";

    /** No training history. TechCd=[@], Task#[@]. If OK, push button again. */
    String NSBM0013W = "NSBM0013W";

    /** FSR# must be entered. */
    String NSBM0037E = "NSBM0037E";

    /** FSR is not found. */
    String NSBM0062E = "NSBM0062E";

    /** Please correct errored fields, and try again. */
    String NSBM0100E = "NSBM0100E";

    /** This Data is already accepted. */
    String NSBM0112E = "NSBM0112E";

    /** FSR Visit is not found. */
    String NSBM0113E = "NSBM0113E";

    /** field is mandatory. */
    String ZZM9000E = "ZZM9000E";

    // START ADD M.FUJI [Defect#3306]
    int FETCH_SIZE_MAX = 1000;
    // END ADD M.FUJI [Defect#3306]

    // START 2014/06/02 T.Aoyagi [CSA-166,ADD]
    /** Waiting part(s) have not shipped yet, If you would like to continue, click schedule again. */
    String NSBM0127W = "NSBM0127W";

    /** Technician have not received waiting part(s) yet, If you would like to continue, click schedule again. */
    String NSBM0128W = "NSBM0128W";
    // END 2014/06/02 T.Aoyagi [CSA-166,ADD]
    
    // START 2015/11/18 T.Harada [CSA,ADD]
    /** The technician is registered as a non preferred technician against this serial. */
    String NSBM0129W = "NSBM0129W";
    // END 2015/11/18 T.Harada [CSA,ADD]

    // START 2016/10/19 N.Arai [QC#13901, MOD]
    /** Table Name: LINE_BIZ_TP */
    String TBL_LINE_BIZ_TP = "LINE_BIZ_TP";

    /** Column Name : LINE_BIZ_TP_CD */
    String LINE_BIZ_TP_CD = "LINE_BIZ_TP_CD";

    /** Column Name : LINE_BIZ_TP_DESC_TXT */
    String LINE_BIZ_TP_DESC_TXT = "LINE_BIZ_TP_DESC_TXT";

    /** Column Name : FLD_SVC_BR_CD */
    String FLD_SVC_BR_CD = "FLD_SVC_BR_CD";

    /** Column Name : SVC_CONTR_BR_DESC_TXT */
    String SVC_CONTR_BR_DESC_TXT = "SVC_CONTR_BR_DESC_TXT";

    /** Column Name : SVC_BY_LINE_BIZ_TP_CD */
    String SVC_BY_LINE_BIZ_TP_CD = "SVC_BY_LINE_BIZ_TP_CD";

    /** Column Name : SVC_BILL_TP_DESC_TXT */
    String SVC_BILL_TP_DESC_TXT = "SVC_BILL_TP_DESC_TXT";
    // END 2016/10/19 N.Arai [QC#13901, MOD]

    // START 2016/12/14 K.Kojima [QC#15653,ADD]
    /** MAX_DT_VAL */
    public static final String MAX_DT_VAL = "29991231";
    // END 2016/12/14 K.Kojima [QC#15653,ADD]
}
