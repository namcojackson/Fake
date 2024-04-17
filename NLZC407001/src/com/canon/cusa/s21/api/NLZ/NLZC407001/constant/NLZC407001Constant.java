/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC407001.constant;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SER_TRX_EVENT;

/**
 *<pre>
 * Serial Validation API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/04/2015   CSAI            K.Lee           Create          Initial
 * 10/13/2017   CITS            T.Hakodate      UPDATE          QC#21857
 * 11/02/2017   CITS            M.Naito         UPDATE          QC#18216
 * 12/22/2017   CITS            M.Naito         UPDATE          QC#18216
 * 03/13/2018   CITS            K.Ogino         Update          QC#18794
 *</pre>
 */
public interface NLZC407001Constant {

    /**
     * 
     */
    public static final String KEY_SCE_ORD_TP_CD = "SCE_ORD_TP_CD";

    /**
     * 
     */
    public static final String KEY_SER_NUM = "SER_NUM";

    /**
     * 
     */
    public static final String KEY_MDSE_CD = "MDSE_CD";

    /**
     * 
     */
    public static final String KEY_RWS_NUM = "RWS_NUM";

    /**
     * 
     */
    public static final String KEY_RWS_DTL_LINE_NUM = "RWS_DTL_LINE_NUM";

    /**
     * 
     */
    public static final String KEY_RWS_STK_DT_TM_TS = "RWS_STK_DT_TM_TS";

    /**
     * 
     */
    public static final String KEY_RWS_REF_NUM = "RWS_REF_NUM";

    /**
     * 
     */
    public static final String KEY_RWS_STK_QTY = "RWS_STK_QTY";

    /**
     * 
     */
    public static final String KEY_INVTY_STK_STS_CD = "INVTY_STK_STS_CD";

    /**
     * 
     */
    public static final String KEY_SVC_MACH_MSTR_PK = "SVC_MACH_MSTR_PK";

    /**
     * 
     */
    public static final String KEY_SVC_CONFIG_MSTR_PK = "SVC_CONFIG_MSTR_PK";

    /**
     * 
     */
    public static final String KEY_PICK_SVC_CONFIG_MSTR_PK = "PICK_SVC_CONFIG_MSTR_PK";

    /**
     * 
     */
    public static final String KEY_RMV_CONFIG_FLG = "RMV_CONFIG_FLG";

    /**
     * 
     */
    public static final String KEY_INVTY_ACCT_CD = "INVTY_ACCT_CD";
    /**
     * 
     */
    public static final String KEY_SVC_MACH_MSTR_STS_CD = "SVC_MACH_MSTR_STS_CD";

    /**
     * 
     */
    public static final String KEY_CUR_LOC_NUM = "CUR_LOC_NUM";

    /**
     * 
     */
    public static final String KEY_SVC_MACH_MAINT_AVAL_FLG = "SVC_MACH_MAINT_AVAL_FLG";

    /**
     * 
     */
    public static final String KEY_TRX_HDR_NUM = "TRX_HDR_NUM";

    /**
     * 
     */
    public static final String KEY_TRX_LINE_NUM = "TRX_LINE_NUM";

    /**
     * 
     */
    public static final String KEY_TRX_LINE_SUB_NUM = "TRX_LINE_SUB_NUM";

    /**
     * 
     */
    public static final String KEY_PO_RCV_TRX_HDR_NUM = "PO_RCV_TRX_HDR_NUM";

    /**
     * 
     */
    public static final String KEY_PO_RCV_TRX_LINE_NUM = "PO_RCV_TRX_LINE_NUM";

    /**
     * 
     */
    public static final String KEY_INSTL_BASE_CTRL_FLG = "INSTL_BASE_CTRL_FLG";

    /**
     * 
     */
    public static final String KEY_SER_NUM_TAKE_FLG = "SER_NUM_TAKE_FLG";

    /**
     * 
     */
    public static final String KEY_WRK_TRX_ID = "WRK_TRX_ID";

    /**
     * 
     */
    public static final String KEY_SO_TRX_HDR_NUM = "SO_TRX_HDR_NUM";

    /**
     * 
     */
    public static final String KEY_SQ_ID = "SQ_ID";

    /**
     * 
     */
    public static final String KEY_INVTY_LOC_CD = "INVTY_LOC_CD";

    /**
     * 
     */
    public static final String KEY_SHIP_FROM_INVTY_LOC_CD = "SHIP_FROM_INVTY_LOC_CD";

    /**
     * 
     */
    public static final String KEY_IB_SVC_MACH_MSTR_PK = "IB_SVC_MACH_MSTR_PK";

    /**
     * 
     */
    public static final String KEY_IB_SVC_CONFIG_MSTR_PK = "IB_SVC_CONFIG_MSTR_PK";

    /**
     * 
     */
    public static final String KEY_IB_SVC_MACH_MSTR_STS_CD = "IB_SVC_MACH_MSTR_STS_CD";

    /**
     * 
     */
    public static final String KEY_IB_CUR_LOC_NUM = "IB_CUR_LOC_NUM";

    /**
     * 
     */
    public static final String KEY_IB_SVC_MACH_MAINT_AVAL_FLG = "IB_SVC_MACH_MAINT_AVAL_FLG";

    /**
     * 
     */
    public static final String KEY_IB_TRX_HDR_NUM = "IB_TRX_HDR_NUM";

    /**
     * 
     */
    public static final String KEY_IB_TRX_LINE_NUM = "IB_TRX_LINE_NUM";

    /**
     * 
     */
    public static final String KEY_IB_TRX_LINE_SUB_NUM = "IB_TRX_LINE_SUB_NUM";

    /**
     * 
     */
    public static final String KEY_IB_SVC_MACH_MSTR_LOC_STS_CD = "IB_SVC_MACH_MSTR_LOC_STS_CD";

    /**
     * 
     */
    public static final String KEY_IB_MDSE_CD = "IB_MDSE_CD";

    /**
     * 
     */
    public static final String KEY_IB_SER_NUM = "IB_SER_NUM";

    /**
     * 
     */
    public static final String KEY_IB_MDL_ID = "IB_MDL_ID";

    /**
     * 
     */
    public static final String KEY_SER_ERR_STS_CD = "SER_ERR_STS_CD";

    /**
     * 
     */
    public static final String KEY_TRX_ORD_NUM = "TRX_ORD_NUM";
    
    /**
     * 
     */
    public static final String KEY_SRC_ORD_NUM = "SRC_ORD_NUM";

    /**
     * 
     */
    public static final String KEY_CMPY_INVTY_FLG = "CMPY_INVTY_FLG";

    /**
     * 
     */
    public static final String KEY_FROM_LOC_CD = "FROM_LOC_CD";

    /**
     * 
     */
    public static final String KEY_RTL_WH_CD = "RTL_WH_CD";

    /**
     * 
     */
    public static final String KEY_RWS_QTY = "RWS_QTY";

    /**
     * 
     */
    public static final String KEY_RWS_PUT_AWAY_QTY = "RWS_PUT_AWAY_QTY";

    /**
     * 
     */
    public static final String KEY_TRX_CD = "TRX_CD";

    /**
     * 
     */
    public static final String KEY_MAIN_SVC_MACH_MSTR_PK = "MAIN_SVC_MACH_MSTR_PK";

    /**
     * 
     */
    public static final String KEY_MAIN_MDSE_CD = "MAIN_MDSE_CD";

    /**
     * 
     */
    public static final String KEY_SER_ALLOC_TRX_HDR_NUM = "SER_ALLOC_TRX_HDR_NUM";

    // 2017/11/2 ADD M.Naito QC#18216 START
    /**
     * 
     */
    public static final String KEY_SO_NUM = "SO_NUM";

    /**
     * 
     */
    public static final String KEY_SO_SLP_NUM = "SO_SLP_NUM";
    // 2017/11/2 ADD M.Naito QC#18216 END

    /**
     * 
     */
    public static final String ORD_SVC_CONFIG_MSTR_PK = "ORD_SVC_CONFIG_MSTR_PK";

    /**
     * 
     */
    public static final String ORD_MDL_ID = "ORD_MDL_ID";

    /**
     * 
     */
    public static final String TRX_CD = "TRX_CD";

    /**
     * 
     */
    public static final String IB_MODE_UPDATE_ITEM_CHANGE = "IB_MODE_UPDATE_ITEM_CHANGE";

    /**
     * 
     */
    public static final String IB_MODE_UPDATE_WH = "IB_MODE_UPDATE_WH";

    /**
     * 
     */
    public static final String IB_MODE_RMA = "IB_MODE_RMA";

    /**
     * 
     */
    public static final String IB_MODE_INS_WH_SER = "IB_MODE_INS_WH_SER";

    /**
     * 
     */
    public static final String IB_MODE_INS_WH_NON_SER_RMV = "IB_MODE_INS_WH_NON_SER_RMV";

    /**
     * 
     */
    public static final String IB_MODE_INS_WH_NON_SER = "IB_MODE_INS_WH_NON_SER";

    /**
     * 
     */
    public static final String IB_MODE_RWS = "IB_MODE_RWS";

    /**
     * 
     */
    public static final String IB_MODE_TRNSF_IN = "IB_MODE_TRNSF_IN";

    /**
     * 
     */
    public static final String IB_MODE_ALLOC_OFF = "IB_MODE_ALLOC_OFF";

    /**
     * 
     */
    public static final String IB_MODE_UPD_INVTY = "IB_MODE_UPD_INVTY";

    /**
     * Return Status Code 0 : Validated
     */
    public static final String RETURN_CODE_VALIDATED = "0";

    /**
     * Return Status Code 1 : IB Allocation Error
     */
    public static final String RETURN_CODE_WARNING = "1";

    /**
     * Return Status Code 9 : Error
     */
    public static final String RETURN_CODE_ERROR = "9";

    /**
     * Process Mode 01 : Order Serial Mode
     */
    public static final String MODE_ORDER_SERIAL = "1";

    /**
     * Process Mode 02 : Pickup Serial Mode
     */
    public static final String MODE_PICKUP_SERIAL = "2";

    /**
     * Mode 03 : Pickup Cancel Mode
     */
    public static final String MODE_PICKUP_CANCEL = "3";

    /**
     * Message ID : NLGM0012E Global Company Code is mandatory.
     */
    public static final String NLGM0012E = "NLGM0012E";

    /**
     * Message ID : NLZM2320W The entered Serial Number is not
     * registered on IB.[item:@, Serial#:@]
     */
    public static final String NLZM2320W = "NLZM2320W";

    /**
     * Message ID : NLZM2321W The entered Serial Number is already
     * allocated on IB.[item:@, Serial#:@]
     */
    public static final String NLZM2321W = "NLZM2321W";

    /**
     * Message ID : NLZM2322W The entered Serial Number is failed to
     * allocate IB.[item:@, Serial#:@]
     */
    public static final String NLZM2322W = "NLZM2322W";

    /**
     * Message ID : NLZM2323E A value is not set in the required
     * parameter field. Field Name:[@] List Index:[@]
     */
    public static final String NLZM2323E = "NLZM2323E";

    /**
     * Message ID : NLAM1295E Failed to register [@].
     */
    public static final String NLAM1295E = "NLAM1295E";

    /**
     * Message ID : NLBM1087E Target data for @1 is being used by
     * others. Cannot process.
     */
    public static final String NLBM1087E = "NLBM1087E";

    /**
     * Message ID : NLBM1077E An "unable to complete process" error
     * occurred. Please contact the IT Dept.
     */
    public static final String NLBM1077E = "NLBM1077E";

    /**
     * Message ID : NLZM2509E DB error occurred. (Insert Receiving
     * Work Sheet ShortageWork Table)
     */
    public static final String NLZM2509E = "NLZM2509E";

    // 2017/11/2 ADD M.Naito QC#18216 START
    /**
     * Message ID : NLZM2514E Failed to insert SVC_MACH_ROSS_SHIP_RELN.
     */
    public static final String NLZM2514E = "NLZM2514E";
    // 2017/11/2 ADD M.Naito QC#18216 END

    // 2017/12/22 ADD M.Naito QC#18216 START
    /**
     * Message ID : NLZM2517E Failed to remove SVC_MACH_ROSS_SHIP_RELN.
     */
    public static final String NLZM2517E = "NLZM2517E";
    // 2017/12/22 ADD M.Naito QC#18216 END

    /**
     * Conversion SCE Order Type to Serial TRX Type Key: SCE Order
     * Type Val:Serial TRX Type
     **/
    public static final Map<String, String[]> CONV_SCE_ORD_TP_MAP = new HashMap<String, String[]>() {
        {
            put(SCE_ORD_TP.DOMESTIC_PO_EXISTS, new String[] {SER_TRX_EVENT.PURCHASE_STOCK_IN, "" });
        }
        {
            put(SCE_ORD_TP.DOMESTIC_CANON_GROUP, new String[] {SER_TRX_EVENT.PURCHASE_STOCK_IN, "" });
        }
        {
            put(SCE_ORD_TP.DOMESTIC_NON_CSA_INVENTORY, new String[] {SER_TRX_EVENT.OFF_THE_BOOK_STOCK_IN, "" });
        }
        {
            put(SCE_ORD_TP.DC_TRANSFER, new String[] {SER_TRX_EVENT.WH_TRANSFER_STOCK_IN, "" });
        }
        {
            put(SCE_ORD_TP.TECH_REQUEST, new String[] {SER_TRX_EVENT.WH_TRANSFER_STOCK_IN, "" });
        }
        {
            put(SCE_ORD_TP.RETURN, new String[] {SER_TRX_EVENT.RETURN, SER_TRX_EVENT.OFF_THE_BOOK_STOCK_IN });
        }
        {
            put(SCE_ORD_TP.KITTING, new String[] {SER_TRX_EVENT.KITTING_STOCK_IN, "" });
        }
        {
            put(SCE_ORD_TP.UN_KITTING, new String[] {SER_TRX_EVENT.KITTING_STOCK_IN, "" });
        }
        {
            put(SCE_ORD_TP.KITTING_CANCEL, new String[] {SER_TRX_EVENT.KITTING_CANCEL_STOCK_IN, "" });
        }
        {
            put(SCE_ORD_TP.REPAIR_SUBCONTRACT, new String[] {SER_TRX_EVENT.REFURBISH_STOCK_IN, "" });
        }
        {
            put(SCE_ORD_TP.BUY_BACK, new String[] {SER_TRX_EVENT.BUY_BACK, "" });
        }
        {
            put(SCE_ORD_TP.SUB_WH_CHANGE, new String[] {SER_TRX_EVENT.SUB_WAREHOUSE_CHANGE, "" });
        }
        {
            put(SCE_ORD_TP.STOCK_STATUS_CHANGE, new String[] {SER_TRX_EVENT.STOCK_STATUS_CHANGE , "" });
        }
        // 2017/10/13 MOD T.Hakodate QC#21857 START
        {
            put(SCE_ORD_TP.REMAN_ITEM_CHANGE, new String[] {SER_TRX_EVENT.ITEM_CHANGE_STOCK_IN, "" });
        }
        // 2017/10/13 MOD T.Hakodate QC#21857 END
        // QC#18794
        {
            put(SCE_ORD_TP.ITEM_CHANGE, new String[] {SER_TRX_EVENT.ITEM_CHANGE_STOCK_IN, "" });
        }
    };

    /**
     * MSG_KIND_WARNING
     */
    public static final String MSG_KIND_WARNING = "W";

    /**
     * MSG_KIND_ERROR
     */
    public static final String MSG_KIND_ERROR = "E";
}
