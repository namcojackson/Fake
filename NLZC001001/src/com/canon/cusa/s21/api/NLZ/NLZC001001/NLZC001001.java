package com.canon.cusa.s21.api.NLZ.NLZC001001;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDAbendException;
import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDDBRecordLockedException;
import parts.dbcommon.EZDTBLAccessor;
import business.db.INVTYTMsg;
import business.parts.NLZC001001PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageIdMgr;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;

/**
 *<pre>
 * Inventory Allocation API
 *  1. Execute Allocation for Inventory.(Partial Allocation, Available QTY Check Free respond to)
 *  2. Execute Cancel Allocation for Inventory.
 *</pre>
 *
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/04/07   Fujitsu         M.Yamada        Create          N/A
 * 2009/06/08   Fujitsu         S.Yoshida       Update          N/A
 * 2009/08/20   Fujitsu         S.Yoshida       Update          N/A
 * 07/14/2010   Fujitsu         M.Yamada        Update          7232(allocation option)
 *</pre>
 */
public class NLZC001001 extends S21ApiCommonBase {

    /** Program ID */
    private static final String PROGRAM_ID = NLZC001001.class.getSimpleName();

    //-- Process Type --------------------------
    /** Process Type : Allocation */
    public static final String PROC_TP_ALLOC = "1";
    /** Process Type : Cancel Allocation */
    public static final String PROC_TP_CANC_ALLOC = "2";

    //-- Allocation Option ---------------------
    /** Allocation Option : Allocation */
    public static final String OPT_ALLOC = "0";
    /** Allocation Option : Partial Allocation */
    public static final String OPT_PRTL = "1";
    /** Allocation Option : Available QTY Check Free */
    public static final String OPT_CHK_FREE = "2";
    /** Allocation Option : Inventory QTY Check Free */
    public static final String OPT_CHK_FREE_OH = "3";

    //-- Error Message Code --------------------
    /** Invalid Process Type */
    public static final String MSG_ID_NLZM0001E = "NLZM0001E";
    /** null input parameter Data Company Code */
    public static final String MSG_ID_NLZM0003E = "NLZM0003E";
    /** null input parameter Merchandise Code */
    public static final String MSG_ID_NLZM0005E = "NLZM0005E";
    /** null input parameter Inventory Location Code */
    public static final String MSG_ID_NLZM0006E = "NLZM0006E";
    /** null input parameter Location Status Code */
    public static final String MSG_ID_NLZM0007E = "NLZM0007E";
    /** null input parameter Stock Status Code */
    public static final String MSG_ID_NLZM0008E = "NLZM0008E";
    /** null input parameter Request Qty */
    public static final String MSG_ID_NLZM0009E = "NLZM0009E";
    /** Request Qty is Greater than Available Qty */
    public static final String MSG_ID_NLZM0010E = "NLZM0010E";
    /** Request Qty is not Positive */
    public static final String MSG_ID_NLZM0011E = "NLZM0011E";
    /** Request Qty is Greater than Allocated Qty */
    public static final String MSG_ID_NLZM0012E = "NLZM0012E";
    /** Target record is updated by another user */
    public static final String MSG_ID_NLZM0013E = "NLZM0013E";
    /** Inventory Master is not found */
    public static final String MSG_ID_NLZM0014E = "NLZM0014E";
    /** Inventory Master record is already locked */
    public static final String MSG_ID_NLZM0015E = "NLZM0015E";
    /** DB error */
    public static final String MSG_ID_NLZM0044E = "NLZM0044E";
    /** The value you entered is incorrect */
    public static final String MSG_ID_NLZM0126E = "NLZM0126E";
    /** Overflow error (Available Qty) */
    public static final String MSG_ID_NLZM0118E = "NLZM0118E";
    /** Overflow error (Allocated Qty) */
    public static final String MSG_ID_NLZM0124E = "NLZM0124E";
    /** Overflow error (Unallocated Qty) */
    public static final String MSG_ID_NLZM0125E = "NLZM0125E";

    //-- Execute Type --------------------------
    /** Execute Type : 'A'llocation */
    private static final String EXEC_TYPE_A = "A";
    /** Execute Type : 'C'ancel Allocation */
    private static final String EXEC_TYPE_C = "C";

    //-- isPartialAlloc's result --------------- 
    /** Allocation */
    private static final int ALLOC = 1;
    /** Partial Allocation (Available) */
    private static final int PRTL_ALLOC_AVAL = 2;
    /** Partial Allocation (not Available) */
    private static final int PRTL_ALLOC_NOT_AVAL = 3;

    //-- Other Internal constants --------------
    /** Normal end */
    private static final String RTNCD_NORMAL_END = EZDTBLAccessor.RTNCD_NORMAL;
    /** for Debug */
    private static final int CST_DEBUG_MSG_LVL = 1;


    /**
     * <pre>
     * Constructor
     * </pre>
     */
    public NLZC001001() {
        super();
    }

    /**
     * <pre>
     * Inventory Allocation API (List)
     * </pre>
     * @param params      Input parameter list.
     * @param onBatchType Kind of Online or Batch.
     */
    public void execute(final List<NLZC001001PMsg> params, final ONBATCH_TYPE onBatchType) {
        for (NLZC001001PMsg param : params) {
            execute(param, onBatchType);
        }
    }

    /**
     * <pre>
     * Inventory Allocation API
     * </pre>
     * @param param       Input parameters.
     * @param onBatchType Kind of Online or Batch.
     */
    public void execute(final NLZC001001PMsg param, final ONBATCH_TYPE onBatchType) {

        // Message Manager
        final S21ApiMessageIdMgr messages = new S21ApiMessageIdMgr();

        try {

            String xxProcTpCd = param.xxProcTpCd.getValue();
            String execType = null;
            String debugMsg = null;

            // Allocation
            if (PROC_TP_ALLOC.equals(xxProcTpCd)) {
                execType = EXEC_TYPE_A;
                debugMsg = " allocation method Start.";

            // Cancel Allocation
            } else if (PROC_TP_CANC_ALLOC.equals(xxProcTpCd)) {
                execType = EXEC_TYPE_C;
                debugMsg = " cancel allocation method Start.";

            // Unexpected
            } else {
                setMsgId(messages, param, MSG_ID_NLZM0001E);
                return;
            }
            printDebugLog(PROGRAM_ID + debugMsg);

            // Execute Inventory Allocation.
            execute(param, messages, execType);

        } catch (EZDDBRecordLockedException e) {
            printDebugLog("The subjective Inventory Master record has been locked by another user.");
            throw e;

        } finally {
            super.updateMessage(param, messages);
        }
    }

    /**
     * <pre>
     * Execute Inventory Allocation.
     * </pre>
     * @param alzc001001PMsg Input parameter.
     * @param messages       Massage Manager.
     */
    private void execute(NLZC001001PMsg alzc001001PMsg, S21ApiMessageIdMgr messages, final String execTp) {

        // Initial output parameters.
        initOutputParameter(alzc001001PMsg);

        // Check parameters.
        chkParameter(alzc001001PMsg, messages);

        // If the message is set.
        if (messages.isXxMsgId()) {
            return;
        }

        // Find the Inventory Master for update.
        // Set key item value & table accessor call.
        INVTYTMsg invtyKey = new INVTYTMsg();
        INVTYTMsg invtyRcd;
        setInvtyKey(invtyKey, alzc001001PMsg);
        invtyRcd = (INVTYTMsg) S21ApiTBLAccessor.findByKeyForUpdate(invtyKey);
        if (invtyRcd == null) {
            setMsgId(messages, alzc001001PMsg, MSG_ID_NLZM0014E);
            return;
        }
        printDebugLog(PROGRAM_ID + "invtyRcd/GLBL_CMPY_CD:" + invtyRcd.glblCmpyCd.getValue());

        // Check the find result.
        String rtnCd = invtyRcd.getReturnCode();
        printDebugLog("rtncd:" + rtnCd);
        if (!RTNCD_NORMAL_END.equals(rtnCd)) {
            setMsgId(messages, alzc001001PMsg, MSG_ID_NLZM0044E);
            return;
        }

        // Initial output parameters.
        setInitOutputParameter(alzc001001PMsg, invtyRcd);

        // Request Timestamp Check
        chkRqstTs(alzc001001PMsg, invtyRcd, messages);

        // If the message is set.
        if (messages.isXxMsgId()) {
            return;
        }

        // Execute Type = Allocation
        if (EXEC_TYPE_A.equals(execTp)) {

            // Check the QTY.
            // If the Allocation Option is Allcoation case, Compare Request Qty to Available Qty
            // If the Request Qty > Available Qty case, Set the error message.
            chkQtyForAllocation(alzc001001PMsg, invtyRcd, messages);

            // If the message is set.
            if (messages.isXxMsgId()) {
                return;
            }

            // Set the Allocation values.
            setAllocationValue(invtyRcd, alzc001001PMsg, messages);

        // Execute Type = Cancel Allocation
        } else {

            // Check the QTY.
            // Comapre Request Qty to Allocation Qty.
            // If the Request Qty > Allocation Qty case, set the error message.
            chkQtyForCancelAllocation(alzc001001PMsg, invtyRcd, messages);

            // If the message is set.
            if (messages.isXxMsgId()) {
                return;
            }

            // Set the Cancel Allocation values.
            setCancelAllocationValue(invtyRcd, alzc001001PMsg, messages);
        }

        // If the message is set.
        if (messages.isXxMsgId()) {
            return;
        }

        // Update the Inventory.
        updateInvty(invtyRcd, alzc001001PMsg, messages);

        // If the message is set.
        if (messages.isXxMsgId()) {
            return;
        }

        // Set the output parameters.
        setOutputParameter(alzc001001PMsg, invtyRcd);
    }

    /**
     * <pre>
     * Initial output parameters.
     * </pre>
     * @param alzc001001PMsg Input parameter.
     */
    private void initOutputParameter(NLZC001001PMsg alzc001001PMsg) {
        alzc001001PMsg.invtyQty.clear();
        alzc001001PMsg.invtyAllocQty.clear();
        alzc001001PMsg.invtyAvalQty.clear();
        alzc001001PMsg.invtyAllocQty_PR.clear();
        alzc001001PMsg.invtyAvalQty_PR.clear();
        alzc001001PMsg.locTpCd.clear();
        alzc001001PMsg.xxIcpltAllocQty.clear();
        alzc001001PMsg.xxMsgIdList.clear();
    }

    /**
     * <pre>
     * Check the Data Company Code.
     * </pre>
     * @param alzc001001PMsg Input parameter.
     * @param messages       Massage Manager.
     */
    private void chkDataCmpyCd(NLZC001001PMsg alzc001001PMsg, S21ApiMessageIdMgr messages) {
        String dataCmpyCd = (String) alzc001001PMsg.glblCmpyCd.getValue();
        if (!ZYPCommonFunc.hasValue(dataCmpyCd)) {
            setMsgId(messages, alzc001001PMsg, MSG_ID_NLZM0003E);
        }
    }

    /**
     * <pre>
     * Check the Inventory Location Code.
     * </pre>
     * @param alzc001001PMsg Input parameter.
     * @param messages       Massage Manager.
     */
    private void chkInvtyLocCd(NLZC001001PMsg alzc001001PMsg, S21ApiMessageIdMgr messages) {
        String invtyLocCd = (String) alzc001001PMsg.invtyLocCd.getValue();
        if (!ZYPCommonFunc.hasValue(invtyLocCd)) {
            setMsgId(messages, alzc001001PMsg, MSG_ID_NLZM0006E);
        }
    }

    /**
     * <pre>
     * Check the Location Statsu Code.
     * </pre>
     * @param alzc001001PMsg Input parameter.
     * @param messages       Massage Manager.
     */
    private void chkLocStsCd(NLZC001001PMsg alzc001001PMsg, S21ApiMessageIdMgr messages) {
        String locStsCd = (String) alzc001001PMsg.locStsCd.getValue();
        if (!ZYPCommonFunc.hasValue(locStsCd)) {
            setMsgId(messages, alzc001001PMsg, MSG_ID_NLZM0007E);
        }
    }

    /**
     * <pre>
     * Check the Merchandise Code.
     * </pre>
     * @param alzc001001PMsg Input parameter.
     * @param messages       Massage Manager.
     */
    private void chkMdseCd(NLZC001001PMsg alzc001001PMsg, S21ApiMessageIdMgr messages) {
        String mdseCd = (String) alzc001001PMsg.mdseCd.getValue();
        if (!ZYPCommonFunc.hasValue(mdseCd)) {
            setMsgId(messages, alzc001001PMsg, MSG_ID_NLZM0005E);
        }
    }

    /**
     * <pre>
     * Check the input parameters.
     * </pre>
     * @param alzc001001PMsg Input parameter.
     * @param messages       Massage Manager.
     */
    private void chkParameter(NLZC001001PMsg alzc001001PMsg, S21ApiMessageIdMgr messages) {

        // Data Company Code Check
        chkDataCmpyCd(alzc001001PMsg, messages);

        // Merchandise Code Check
        chkMdseCd(alzc001001PMsg, messages);

        // Inventory Location Code Check
        chkInvtyLocCd(alzc001001PMsg, messages);

        // Location Statsu Code Check
        chkLocStsCd(alzc001001PMsg, messages);

        // Stock Statsu Code Check
        chkStkStsCd(alzc001001PMsg, messages);

        // Request Qty Check
        chkRqstQty(alzc001001PMsg, messages);
    }

    /**
     * <pre>
     * Compare to Request Qty vs Available Qty.
     * if Request Qty greater than Available Qty, it is error.
     * </pre> 
     * @param alzc001001PMsg Input parameter.
     * @param invtyRcd       Processing inventory record.
     * @param messages       Massage Manager.
     */
    private void chkQtyForAllocation(NLZC001001PMsg alzc001001PMsg, INVTYTMsg invtyRcd, S21ApiMessageIdMgr messages) {

//TODO add 8/20
        // Request Qty > Inventory Qty
        BigDecimal xxRqstQty = alzc001001PMsg.xxRqstQty.getValue();
        if (!OPT_CHK_FREE_OH.equals(alzc001001PMsg.xxAllocOpt.getValue())){
            if (xxRqstQty.compareTo(invtyRcd.invtyQty.getValue()) > 0) {
                setMsgId(messages, alzc001001PMsg, MSG_ID_NLZM0126E);
                return;
            }
        }

        // is Allocation option Off
        if (isAllocOptOff(alzc001001PMsg)) {
            return;
        }

        // Compare Request Qty to Available Qty.
        // Request Qty > Available Qty is error.
        if (xxRqstQty.compareTo(invtyRcd.invtyAvalQty.getValue()) > 0) {
            setMsgId(messages, alzc001001PMsg, MSG_ID_NLZM0010E);
        }
    }

    /**
     * <pre>
     * Compare to Request Qty vs Allocated Qty.
     * if Request Qty greater than Allocated Qty, it is error.
     * </pre> 
     * @param alzc001001PMsg Input parameter.
     * @param invtyRcd       Processing inventory record.
     * @param messages       Massage Manager.
     */
    private void chkQtyForCancelAllocation(NLZC001001PMsg alzc001001PMsg, INVTYTMsg invtyRcd, S21ApiMessageIdMgr messages) {

        // Compare Request Qty to Allocation Qty.
        // Request Qty > Allocation Qty is error.
        BigDecimal xxRqstQty = alzc001001PMsg.xxRqstQty.getValue();
        BigDecimal invtyAllocQty = invtyRcd.invtyAllocQty.getValue();
        if (xxRqstQty.compareTo(invtyAllocQty) > 0) {
            setMsgId(messages, alzc001001PMsg, MSG_ID_NLZM0012E);
        }
    }

    /**
     * <pre>
     * Check the Request QTY.
     * </pre>
     * @param alzc001001PMsg Input parameter.
     * @param messages       Massage Manager.
     */
    private void chkRqstQty(NLZC001001PMsg alzc001001PMsg, S21ApiMessageIdMgr messages) {

        // Request Qty is null
        BigDecimal xxRqstQty = alzc001001PMsg.xxRqstQty.getValue();
        if (!ZYPCommonFunc.hasValue(xxRqstQty)) {
            setMsgId(messages, alzc001001PMsg, MSG_ID_NLZM0009E);

        // Request Qty <= 0
        } else if (BigDecimal.ZERO.compareTo(xxRqstQty) >= 0) {
            setMsgId(messages, alzc001001PMsg, MSG_ID_NLZM0011E);
        }
    }

    /**
     * <pre>
     * Check the Request Timestamp.
     * </pre>
     * @param alzc001001PMsg Input parameter.
     * @param invtyRcd       Processing inventory record.
     * @param messages       Massage Manager.
     */
    private void chkRqstTs(NLZC001001PMsg alzc001001PMsg, INVTYTMsg invtyRcd, S21ApiMessageIdMgr messages) {

        // Request Timestamp is null.
        String rqstTs = alzc001001PMsg.xxRqstTs.getValue();
        if (!ZYPCommonFunc.hasValue(rqstTs)) {
            return;
        }

        // Compare update timestamp to request timestamp.
        if (!rqstTs.equals(invtyRcd.ezUpTime.getValue())) {
            setMsgId(messages, alzc001001PMsg, MSG_ID_NLZM0013E);
        }
    }

    /**
     * <pre>
     * Check the Stock Statsu Code.
     * </pre>
     * @param alzc001001PMsg Input parameter.
     * @param messages       Massage Manager.
     */
    private void chkStkStsCd(NLZC001001PMsg alzc001001PMsg, S21ApiMessageIdMgr messages) {
        String stkStsCd = (String) alzc001001PMsg.stkStsCd.getValue();
        if (!ZYPCommonFunc.hasValue(stkStsCd)) {
            setMsgId(messages, alzc001001PMsg, MSG_ID_NLZM0008E);
        }
    }

    /**
     * <pre>
     * Check the Allocation Option.
     * </pre>
     * @param  alzc001001PMsg Input parameter.
     * @return Result (true:allocation option = allocation, false:other)
     */
    private boolean isAllocOptOff(NLZC001001PMsg alzc001001PMsg) {

        String allocOpt = alzc001001PMsg.xxAllocOpt.getValue();

        // Partial Allocation or Available Check Free
        if (OPT_PRTL.equals(allocOpt) //
                || OPT_CHK_FREE.equals(allocOpt) //
                || OPT_CHK_FREE_OH.equals(allocOpt)) {
            return true;
        }
        return false;
    }

    /**
     * <pre>
     * Check the Partial Allocation.
     * </pre>
     * @param  alzc001001PMsg Input parameters.
     * @param  INVTYTMsg      Processing inventory record.
     * @return Check Result
     */
    private int isPartialAlloc(NLZC001001PMsg alzc001001PMsg, INVTYTMsg invtyRcd) {

        // get Allocation Option
        String xxAllocOpt = alzc001001PMsg.xxAllocOpt.getValue();

        // Allocation Option is not Partial Allocation.
        if (!OPT_PRTL.equals(xxAllocOpt)) {
            return ALLOC;
        }

        // Compare Request Qty to Available Qty.
        BigDecimal xxRqstQty = alzc001001PMsg.xxRqstQty.getValue();
        BigDecimal invtyAvalQty = invtyRcd.invtyAvalQty.getValue();

        // Request Qty <= Available Qty
        if (invtyAvalQty.compareTo(xxRqstQty) >= 0) {
            return ALLOC;

        // Available Qty > 0
        } else if (BigDecimal.ZERO.compareTo(invtyAvalQty) < 0) {
            return PRTL_ALLOC_AVAL;

        // Unexpected
        } else {
            return PRTL_ALLOC_NOT_AVAL;
        }
    }

    /**
     * <pre>
     * Set the Inventory's key.
     * </pre>
     * @param INVTYTMsg      Processing inventory record.
     * @param alzc001001PMsg Input parameters.
     */
    private void setInvtyKey(INVTYTMsg invtyKey, NLZC001001PMsg alzc001001PMsg) {
        invtyKey.glblCmpyCd.setValue(alzc001001PMsg.glblCmpyCd.getValue());
        invtyKey.mdseCd.setValue(alzc001001PMsg.mdseCd.getValue());
        invtyKey.invtyLocCd.setValue(alzc001001PMsg.invtyLocCd.getValue());
        invtyKey.locStsCd.setValue(alzc001001PMsg.locStsCd.getValue());
        invtyKey.stkStsCd.setValue(alzc001001PMsg.stkStsCd.getValue());
    }

    /**
     * <pre>
     * Update the Inventory.
     * </pre>
     * @param invtyRcd       Processing inventory record.
     * @param alzc001001PMsg Input parameters.
     * @param messages       Massage Manager.
     */
    private void updateInvty(INVTYTMsg invtyRcd, NLZC001001PMsg alzc001001PMsg, S21ApiMessageIdMgr messages) {

        // Execute update.
        S21ApiTBLAccessor.update(invtyRcd);
        if (!RTNCD_NORMAL_END.equals(invtyRcd.getReturnCode())) {
            setMsgId(messages, alzc001001PMsg, MSG_ID_NLZM0044E);
        }
    }

    /**
     * <pre>
     * Set the message id.
     * </pre>
     * @param messages       Message Manager.
     * @param alzc001001PMsg Input parameters.
     * @param val            setting value for Message ID
     */
    private void setMsgId(S21ApiMessageIdMgr messages, NLZC001001PMsg alzc001001PMsg, String val) {
        messages.addXxMsgId(val, alzc001001PMsg);
        printDebugLog("setMsgId:" + val);
    }

    /**
     * <pre>
     * Initial output parameters.
     * </pre>
     * @param alzc001001PMsg Input parameters.
     * @param invtyRcd       Processing inventory record.
     */
    private void setInitOutputParameter(NLZC001001PMsg alzc001001PMsg, INVTYTMsg invtyRcd) {
        alzc001001PMsg.invtyQty.setValue(invtyRcd.invtyQty.getValue());
        alzc001001PMsg.invtyAllocQty.setValue(invtyRcd.invtyAllocQty.getValue());
        alzc001001PMsg.invtyAvalQty.setValue(invtyRcd.invtyAvalQty.getValue());
        alzc001001PMsg.locTpCd.setValue(invtyRcd.locTpCd.getValue());
        alzc001001PMsg.invtyAllocQty_PR.setValue(invtyRcd.invtyAllocQty.getValue());
        alzc001001PMsg.invtyAvalQty_PR.setValue(invtyRcd.invtyAvalQty.getValue());
        alzc001001PMsg.xxIcpltAllocQty.setValue(BigDecimal.ZERO);
    }

    /**
     * <pre>
     * Set the output parameters.
     * </pre>
     * @param alzc001001PMsg Input parameters.
     * @param invtyRcd       Processing inventory record.
     */
    private void setOutputParameter(NLZC001001PMsg alzc001001PMsg, INVTYTMsg invtyRcd) {
        alzc001001PMsg.invtyAllocQty.setValue(invtyRcd.invtyAllocQty.getValue());
        alzc001001PMsg.invtyAvalQty.setValue(invtyRcd.invtyAvalQty.getValue());
    }

    /**
     * <pre>
     * Set the Allocation Value.
     * </pre>
     * @param invtyRcd       Processing inventory record.
     * @param alzc001001PMsg Input parameters.
     * @param message        Message Manager.
     */
    private void setAllocationValue(INVTYTMsg invtyRcd, NLZC001001PMsg alzc001001PMsg, S21ApiMessageIdMgr messages) {

        BigDecimal rqstQty = null;
        BigDecimal invtyAvalQty = null;
        BigDecimal icpltAllocQtyCal = null;
        BigDecimal invtyAllocQtyCal = null;
        BigDecimal invtyAvalQtyCal = null;

        switch (isPartialAlloc(alzc001001PMsg, invtyRcd)) {

            // Allocation, Available Check Free
            //   or Partial Allocation & Request Qty <= Available Qty
            case ALLOC:
                rqstQty = alzc001001PMsg.xxRqstQty.getValue();

                // Allocation Qty = Allocation Qty + Request Qty
                invtyAllocQtyCal = invtyRcd.invtyAllocQty.getValue().add(rqstQty);

                // Available Qty = Available Qty - Request Qty
                invtyAvalQtyCal = invtyRcd.invtyAvalQty.getValue().subtract(rqstQty);

                // Incomplete Allocation Qty = 0
                icpltAllocQtyCal = BigDecimal.ZERO;
                break;

            // Partial Allocation & Available Qty > 0
            case PRTL_ALLOC_AVAL:
                rqstQty = alzc001001PMsg.xxRqstQty.getValue();
                invtyAvalQty = invtyRcd.invtyAvalQty.getValue();

                // Allocation Qty = Allocation Qty + Available Qty
                invtyAllocQtyCal = invtyRcd.invtyAllocQty.getValue().add(invtyAvalQty);

                // Available Qty = 0
                invtyAvalQtyCal = BigDecimal.ZERO;

                // Incomplete Allocation Qty = Request Qty - Available Qty
                icpltAllocQtyCal = rqstQty.subtract(invtyAvalQty);
                break;

            // Partial Allocation & Available Qty <= 0
            case PRTL_ALLOC_NOT_AVAL:

                // Incomplete Allocation Qty = Request Qty
                icpltAllocQtyCal = alzc001001PMsg.xxRqstQty.getValue();

                // Allocation Qty、Available Qty
                invtyAllocQtyCal = invtyRcd.invtyAllocQty.getValue();
                invtyAvalQtyCal  = invtyRcd.invtyAvalQty.getValue();
                break;

            // Unexpected
            default:
                icpltAllocQtyCal = BigDecimal.ZERO;
                invtyAllocQtyCal = BigDecimal.ZERO;
                invtyAvalQtyCal  = BigDecimal.ZERO;
        }

        // set Unallocated Qty
        try {
            alzc001001PMsg.xxIcpltAllocQty.setValue(icpltAllocQtyCal);
        } catch (EZDAbendException e) {
            setMsgId(messages, alzc001001PMsg, MSG_ID_NLZM0125E);
        }

        // set Allocated Qty
        try {
            invtyRcd.invtyAllocQty.setValue(invtyAllocQtyCal);
        } catch (EZDAbendException e) {
            setMsgId(messages, alzc001001PMsg, MSG_ID_NLZM0124E);
        }

        // set Available Qty
        try {
            invtyRcd.invtyAvalQty.setValue(invtyAvalQtyCal);
        } catch (EZDAbendException e) {
            setMsgId(messages, alzc001001PMsg, MSG_ID_NLZM0118E);
        }
    }

    /**
     * <pre>
     * Set the Cancel Allocation Value.
     * </pre>
     * @param invtyRcd       Processing inventory record.
     * @param alzc001001PMsg Input parameters.
     */
    private void setCancelAllocationValue(INVTYTMsg invtyRcd, NLZC001001PMsg alzc001001PMsg, S21ApiMessageIdMgr messages) {
        BigDecimal rqstQty = alzc001001PMsg.xxRqstQty.getValue();

        // Allocation Qty = Allocation Qty - Request Qty
        try {
            invtyRcd.invtyAllocQty.setValue(invtyRcd.invtyAllocQty.getValue().subtract(rqstQty));
        } catch (EZDAbendException e) {
            setMsgId(messages, alzc001001PMsg, MSG_ID_NLZM0124E);
        }

        // Available Qty = Available Qty + Request Qty
        try {
            invtyRcd.invtyAvalQty.setValue(invtyRcd.invtyAvalQty.getValue().add(rqstQty));
        } catch (EZDAbendException e) {
            setMsgId(messages, alzc001001PMsg, MSG_ID_NLZM0118E);
        }
    }

    /**
     * <pre>
     * Print debug message.
     * </pre>
     * @param debugMsg debug messsage
     */
    private void printDebugLog(String debugMsg) {
        if (EZDDebugOutput.isDebug()) {
            EZDDebugOutput.println(CST_DEBUG_MSG_LVL, debugMsg, this);
        }
    }
}
