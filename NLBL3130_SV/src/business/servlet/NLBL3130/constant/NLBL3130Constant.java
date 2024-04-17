/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */

package business.servlet.NLBL3130.constant;

/**
 *<pre>
 * Delivery Instruction
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/18/2015   CITS            M.Ito           Create          N/A
 * 04/13/2016   CSAI            Y.Imazu         Update          QC#5868
 * 01/23/2018   CITS            K.Ogino         Update          QC#23044
 * 03/24/2020   Fujitsu         T.Ogura         Update          QC#56288
 *</pre>
 */
public interface NLBL3130Constant {

    /**
     * SCREEN_ID
     */
    String SCREEN_ID = "NLBL3130Scrn00";

    /**
     * BUSINESS_ID
     */
    String BUSINESS_ID = "NLBL3130";

    /**
     * FUNCID_REFERENCE
     */
    String FUNCID_REFERENCE = "NLBL3130T010";

    /**
     * FUNCID_INSERT_UPDATE
     */
    String FUNCID_INSERT_UPDATE = "NLBL3130T020";

    /**
     * FUNCTION_CD_SEARCH
     */
    String FUNCTION_CD_SEARCH = "20";

    /**
     * FUNCTION_CD_SEARCH
     */
    String FUNCTION_CD_UPDATE = "40";

    /**
     * Common button 1
     */
    String[] BTN_CMN_SAVE = {"btn1", "CMN_Save", "Save" };

    /**
     * Common button 2
     */
    String[] BTN_CMN_SUBMIT = {"btn2", "CMN_Submit", "Submit" };

    /**
     * Common button 3
     */
    String[] BTN_CMN_APPLY = {"btn3", "CMN_Apply", "Apply" };

    /**
     * Common button 4
     */
    String[] BTN_CMN_APPROVE = {"btn4", "CMN_Approve", "Approve" };

    /**
     * Common button 5
     */
    String[] BTN_CMN_REJECT = {"btn5", "CMN_Reject", "Reject" };

    /**
     * Common button 6
     */
    String[] BTN_CMN_DOWNLOAD = {"btn6", "CMN_Download", "Download" };

    /**
     * Common button 7
     */
    String[] BTN_CMN_DELETE = {"btn7", "CMN_Delete", "Delete" };

    /**
     * Common button 8
     */
    String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /**
     * Common button 9
     */
    String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /**
     * Common button 10
     */
    String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    // =================================================
    // Constant Value for Time Check
    // =================================================
    /** Hour minute string length : HHMM */
    public static final int HOUR_MINUTE_STR_LENGTH = 4;

    /** One day hours */
    public static final int ONE_DAY_HOURS = 24;

    /** One hour minutes */
    public static final int ONE_HOUR_MINUTES = 60;

    // QC#18460_Sol#087 Add
    /** Half day hours */
    public static final int HALF_DAY_HOURS = 12;

    /** Half day hours */
    public static final String TIME_AM = "AM";
    
    /** Half day hours */
    public static final String TIME_PM = "PM";

    // =================================================
    // Display character string for message
    // =================================================
    /** Order Number */
    public static final String NAME_FOR_MESSAGE_TRX_HDR_NUM = "Order Number";

    /** Shipping Order Number */
    public static final String NAME_FOR_MESSAGE_SO_NUM = "Shipping Order Number";

    /** QC#23044 RWS Number */
    public static final String NAME_FOR_MESSAGE_RWS_NUM = "RWS Number";

    /** Customer Name */
    public static final String NAME_FOR_MESSAGE_DS_ACCT_NM = "Customer Name";

    /** Hours From */
    public static final String NAME_FOR_MESSAGE_OPS_FROM_HOUR_MN = "Hours From";

    /** Hours To */
    public static final String NAME_FOR_MESSAGE_OPS_TO_HOUR_MN = "Hours To";

    /** Security Clearance Required */
    public static final String NAME_FOR_MESSAGE_SEC_CLNC_REQ_FLG = "Security Clearance Required";

    /** Insurance Certificate Required */
    public static final String NAME_FOR_MESSAGE_INS_CERT_REQ_FLG = "Insurance Certificate Required";

    /** Floor Covering */
    public static final String NAME_FOR_MESSAGE_FL_COV_TXT = "Floor Covering";

    /** Tractor Trailer Accessible? */
    public static final String NAME_FOR_MESSAGE_TRCTR_AND_TRAIL_ACCS_FLG = "Tractor Trailer Accessible?";

    /** Loading Dock? */
    public static final String NAME_FOR_MESSAGE_LOAD_DOCK_AVAL_FLG = "Loading Dock?";

    /** Ramp? */
    public static final String NAME_FOR_MESSAGE_RAMP_AVAL_FLG = "Ramp?";

    /** Steps? */
    public static final String NAME_FOR_MESSAGE_STEP_AVAL_FLG = "Steps?";

    /** Step Inside Count */
    public static final String NAME_FOR_MESSAGE_INSD_STEP_NUM = "Step Inside Count";

    /** Step Outside Count */
    public static final String NAME_FOR_MESSAGE_OTSD_STEP_NUM = "Step Outside Count";

    /** Door Front */
    public static final String NAME_FOR_MESSAGE_FRONT_DOOR_AVAL_FLG = "Door Front";

    /** Door Back */
    public static final String NAME_FOR_MESSAGE_BACK_DOOR_AVAL_FLG = "Door Back";

    /** Power Outlet Configured */
    public static final String NAME_FOR_MESSAGE_PWR_OTLT_CONFIG_FLG = "Power Outlet Configured";

    /** Sign On Building Roadside */
    public static final String NAME_FOR_MESSAGE_SGN_ON_BLDG_RDSD_FLG = "Sign On Building Roadside";

    /** Building # of Storeys */
    public static final String NAME_FOR_MESSAGE_BLDG_STRY_NUM = "Building # of Storeys";

    /** Building Guard Notified */
    public static final String NAME_FOR_MESSAGE_BLDG_GURD_NTFY_FLG = "Building Guard Notified";

    /** Industrial Park */
    public static final String NAME_FOR_MESSAGE_INDL_PARK_FLG = "Industrial Park";

    /** Business Park */
    public static final String NAME_FOR_MESSAGE_BIZ_PARK_FLG = "Business Park";

    /** Professional Building */
    public static final String NAME_FOR_MESSAGE_PRO_BLDG_FLG = "Professional Building";

    /** Shopping Center */
    public static final String NAME_FOR_MESSAGE_SHPNG_CTR_FLG = "Shopping Center";

    /** Private Residence */
    public static final String NAME_FOR_MESSAGE_PVT_RES_FLG = "Private Residence";

    /** Height(in.) */
    public static final String NAME_FOR_MESSAGE_BLDG_ENT_DOOR_HGT = "Height(in.)";

    /** Width(in.) */
    public static final String NAME_FOR_MESSAGE_BLDG_ENT_DOOR_WDT = "Width(in.)";

    /** Corridor Width(in.) */
    public static final String NAME_FOR_MESSAGE_CRDR_WDT = "Corridor Width(in.)";

    /** Door Width(in.) */
    public static final String NAME_FOR_MESSAGE_DOOR_WDT = "Door Width(in.)";

    /** Shipping Instructions */
    public static final String NAME_FOR_MESSAGE_SHPG_INSTN_CMNT_TXT = "Shipping Instructions";

    /** Delivery Instructions */
    public static final String NAME_FOR_MESSAGE_DELY_INSTN_CMNT_TXT = "Delivery Instructions";

    /** Install Instructions */
    public static final String NAME_FOR_MESSAGE_ISTL_INSTN_CMNT_TXT = "Install Instructions";

    /** Technician Instructions */
    public static final String NAME_FOR_MESSAGE_TECH_INSTN_CMNT_TXT = "Technician Instructions";

    /** Elevator Available? */
    public static final String NAME_FOR_MESSAGE_ELEV_AVAL_FLG = "Elevator Available?";

    /** Elevator Hours From */
    public static final String NAME_FOR_MESSAGE_ELEV_AVAL_FROM_HOUR_MN = "Elevator Hours From";

    /** Elevator Hours To */
    public static final String NAME_FOR_MESSAGE_ELEV_AVAL_TO_HOUR_MN = "Elevator Hours To";

    /** Elevator Appointment Needed */
    public static final String NAME_FOR_MESSAGE_ELEV_APPT_REQ_FLG = "Elevator Appointment Needed";

    /** Elevator Contact */
    public static final String NAME_FOR_MESSAGE_ELEV_CTAC_PSN_NM = "Elevator Contact";

    /** Elevator Phone */
    public static final String NAME_FOR_MESSAGE_ELEV_CTAC_TEL_NUM = "Elevator Phone";

    /** Width(in.) */
    public static final String NAME_FOR_MESSAGE_ELEV_WDT = "Width(in.)";

    /** Depth(in.) */
    public static final String NAME_FOR_MESSAGE_ELEV_DEPTH_NUM = "Depth(in.)";

    /** Capacity(Lbs.) */
    public static final String NAME_FOR_MESSAGE_ELEV_CAP_WT = "Capacity(Lbs.)";

    /** Height(in.) */
    public static final String NAME_FOR_MESSAGE_ELEV_DOOR_HGT = "Height(in.)";

    /** Width(in.) */
    public static final String NAME_FOR_MESSAGE_ELEV_DOOR_WDT = "Width(in.)";

    /** Additional Comments */
    public static final String NAME_FOR_MESSAGE_DELY_INSTN_ADDL_CMNT_TXT = "Additional Comments";

    /** Validated With */
    public static final String NAME_FOR_MESSAGE_VLD_USR_ID = "Validated With";

    /** Validation Date/Time */
    public static final String NAME_FOR_MESSAGE_VLD_TS = "Validation Date/Time";

    /** DS CPO Config Primary Key */
    public static final String NAME_FOR_MESSAGE_DS_CPO_CONFIG_PK = "DS CPO Config Primary Key";

    /** CPO Order Number */
    public static final String NAME_FOR_MESSAGE_CPO_ORD_NUM = "CPO Order Number";

    /** Direct Sales Order Position Number */
    public static final String NAME_FOR_MESSAGE_DS_ORD_POSN_NUM = "Direct Sales Order Position Number";

    /** Configuration Category Code */
    public static final String NAME_FOR_MESSAGE_CONFIG_CATG_CD = "Configuration Category Code";

    // QC#18460_Sol#087 Add column

    /** Time Zone */
    public static final String NAME_FOR_MESSAGE_TIME_ZONE_CD = "Time Zone";

    /** Floor */
    public static final String NAME_FOR_MESSAGE_CMPY_INFO_FL_NM = "Floor";

    /** Dock Height(in.) */
    public static final String NAME_FOR_MESSAGE_LOAD_DOCK_HGT = "Dock Height(in.)";

    /** Transport Option */
    public static final String NAME_FOR_MESSAGE_DELY_TRNSP_OPT_CD = "Transport Option";

    /** Step Crawler Required */
    public static final String NAME_FOR_MESSAGE_STAIR_CRAW_REQ_FLG = "Step Crawler Required";

    /** Width of stairs and landings(in.) */
    public static final String NAME_FOR_MESSAGE_STAIR_AND_LDG_WDT = "Width of stairs and landings(in.)";

    /** Customer informed about B/O */
    public static final String NAME_FOR_MESSAGE_CUST_INFO_BO_FLG = "Customer informed about B/O";

    /** Pickup extra Toner */
    public static final String NAME_FOR_MESSAGE_PICK_UP_XTR_TONER_FLG = "Pickup extra Toner";

    /** Pickup at same time */
    public static final String NAME_FOR_MESSAGE_PICK_UP_AT_SAME_TM_FLG = "Pickup at same time";

    // =================================================
    // Message ID
    // =================================================

    /** Entry format is incorrect. */
    public static final String NLBM0060E = "NLBM0060E";

    /** '@ can not be multiple check. Check either one of the boxes only. */
    public static final String NMAM8287E ="NMAM8287E";

    /** The value for @ must be greater than or equal to @. */
    public static final String NFCM0780E = "NFCM0780E";

    /** [@] field has too many digits entered. */
    public static final String ZZM9001E = "ZZM9001E";    // 03/24/2020 T.Ogura [QC#56288,ADD]
}
