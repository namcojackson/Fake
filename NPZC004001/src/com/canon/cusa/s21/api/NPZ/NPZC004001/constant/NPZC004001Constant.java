/*
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NPZ.NPZC004001.constant;

/**
 * <pre>
 * PO Create API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/04/05   CSAI            K.Lee           Create          Def.834(K25)
 * 2013/07/26   CSAI            K.Lee           Update          QC1444
 * 2015/09/30   CITS            H.Sugawara      Update          CSA Project
 * 2016/12/12   CITS            S.Endo          Update          QC#14453
 * 2018/01/16   CITS            K.Ogino         Update          QC#22412
 * 2022/04/28   Hitachi         A.Kohinata      Update          QC#57934
 * </pre>
 */
public class NPZC004001Constant {

    // Return Code of EZDTBLAccessor
    /** Normal end */
    public static final String RTNCD_NORMAL_END = "0000";

    // Mode IDs

    /** Receiving.*/
    public static final String MODE_ID_RECEIVING = "Receiving";

    /** Receiving Completion.*/
    public static final String MODE_ID_RECEIVING_COMPLETION = "ReceivingCompletion";

    /** Canceled.*/
    public static final String MODE_ID_CANCELED = "Canceled";

    /** Closed.*/
    public static final String MODE_ID_CLOSED = "Closed";

    /** PO Confirmed.*/
    public static final String MODE_ID_PO_CONFIRMED = "POConfirmed";

    /** Error.*/
    public static final String MODE_ID_ERROR = "Error";

    /** Invoice.*/
    public static final String MODE_ID_INVOICE = "Invoice";

    /** Approval.*/
    public static final String MODE_ID_APPROVAL = "Approval";

    // add start 2022/04/28 QC#57934
    /** Accrual Write Off Close.*/
    public static final String MODE_ID_ACRL_WRITE_OFF_CLOSE = "AccrualWriteOffClose";
    // add end 2022/04/28 QC#57934

    // Message IDs

    /** Global Company Code is required.*/
    public static final String MSG_ID_NPZM0001E = "NPZM0001E";

    /** Specified receiving Qty is incorrect.*/
    public static final String MSG_ID_NPZM0005E = "NPZM0005E";

    /** Specified receiving Qty exceeds the balance of receiving Qty.*/
    public static final String MSG_ID_NPZM0006E = "NPZM0006E";

    /** PO Number is required.*/
    public static final String MSG_ID_NPZM0018E = "NPZM0018E";

    /** Merchandise Code is required.*/
    public static final String MSG_ID_NPZM0020E = "NPZM0020E";

    /** PO Detail Line Number is required.*/
    public static final String MSG_ID_NPZM0175E = "NPZM0175E";

    /** Specified PO Information is invalid or does not exist.*/
    public static final String MSG_ID_NPZM0021E = "NPZM0021E";

    /** Since the value of PO Status does not meet the criteria, it cannot be received.*/
    public static final String MSG_ID_NPZM0022E = "NPZM0022E";

    /** Since PO Status is not Receiving, Receiving cannot be completed.*/
    public static final String MSG_ID_NPZM0023E = "NPZM0023E";

    /** Since PO Status does not meet the criteria, it cannot be canceled.*/
    public static final String MSG_ID_NPZM0026E = "NPZM0026E";

    /** Value for the specified PO Status is out of range.*/
    public static final String MSG_ID_NPZM0027E = "NPZM0027E";

    /** DB error occurred.*/
    public static final String MSG_ID_NPZM0014E = "NPZM0014E";

    /** for Debug */
    public static final int CST_DEBUG_MSG_LVL = 10;

    /** Event ID [PO Closed] */
    public static final String EVENT_ID_CLOSED = "PO Closed";

    /** Event ID [PO Cancelled] */
    public static final String EVENT_ID_CANCELLED = "PO Cancelled";

    /** Event ID [PO Receiving] */
    public static final String EVENT_ID_RECEIVING = "PO Receiving";

    /** Event ID [PO Received] */
    public static final String EVENT_ID_RECEIVED = "PO Received";

    /** Event ID [PO Confirmed] */
    public static final String EVENT_ID_CONFIRMED = "PO Confirmed";

    /** Event ID [PO Error] */
    public static final String EVENT_ID_ERROR = "PO Error";

    /** Event ID [PO Invoice Receiving] */
    public static final String EVENT_ID_INVOICE_RECEIVING = "PO Invoice Receiving";

    /** Event ID [PO Invoice Cancel] QC#22412 */
    public static final String EVENT_ID_INVOICE_CANCEL = "PO Invoice Cancel";

    /** Event ID [PO Approved] */
    public static final String EVENT_ID_APPROVED = "PO Approved";

    /** Event ID [PO Rejected] */
    public static final String EVENT_ID_REJECTED = "PO Rejected";

    /** Key Name updateStatus */
    public static final String KEY_NAME_UPDATE_STATUS = "updateStatus";
    /** Key Name poDtlTMsg */
    public static final String KEY_NAME_PO_DETAIL_MSG = "poDtlTMsg";
}
