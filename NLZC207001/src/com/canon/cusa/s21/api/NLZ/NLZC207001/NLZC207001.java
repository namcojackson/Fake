/*
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
*/
package com.canon.cusa.s21.api.NLZ.NLZC207001;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDTBLAccessor;
import business.db.RWS_CPLT_WRKTMsg;
import business.parts.NLZC207001PMsg;
import business.parts.NLZC209001PMsg;
import business.parts.NLZC407001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC209001.NLZC209001;
import com.canon.cusa.s21.api.NLZ.NLZC407001.NLZC407001;
import com.canon.cusa.s21.common.NLX.NLXC017001.NLXCMsgHelper;
import com.canon.cusa.s21.common.NLX.NLXC025001.NLXC025001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_PROC_STS;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.internal.calendar.S21SystemDate;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 *
 * Date          Company     Name         Create/Update  Defect No
 * ----------------------------------------------------------------------
 * 06/09/2009     Fujitsu     H.Haga      Create           N/A
 * 04/27/2010     Fujitsu     H.Nagashima Update          4825
 *</pre>
 */
public class NLZC207001 extends S21ApiCommonBase {

    /**  */
    public static final String NLZM1001E = "NLZM1001E";

    /**  */
    public static final String NLAM1006E = "NLAM1006E";

    /**  */
    public static final String NLAM1133E = "NLAM1133E";

    /**  */
    public static final String NLAM1134E = "NLAM1134E";

    /* */
    private static final String PROGRAM_ID = "NLZC207001:";

    /* */
    private static final String SRC_TP_CD_S21 = "S";

    /* */
    private static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /* */
    private static final String RWS_NUM = "RWS_NUM";

    /* */
    private static final String SCE_ORD_TP_CD = "SCE_ORD_TP_CD";

    /* */
    private static final String RWS_CLO_DT_TM_TS = "RWS_CLO_DT_TM_TS";

    /* */
    private static final String WH_CD = "WH_CD";

    /* */
    private static final String RWS_REF_NUM = "RWS_REF_NUM";

    /* */
    private static final String SLS_DT = "SLS_DT";

    /* */
    private static final String RWS_CPLT_WRK = "RWS_CPLT_WRK";

    /* */
    private static final String WRK_TRX_ID = "WRK_TRX_ID";

    /* */
    private static final String SQ_ID = "SQ_ID";

    /* */
    private static final String LF_PAD_CHAR = "0";

    /* */
    private static final int TRAN_ID_LENGTH = 30;

    /* */
    private static final int SEQ_ID_LENGTH = 10;

    /**
     */
    public NLZC207001() {
        super();
    }

    /**
     * @see com.canon.cusa.s21.api.NLZ.NLZC207001.execute
     * @throws SQLException
     */
    public void execute(final List<NLZC207001PMsg> params, final ONBATCH_TYPE onBatchType) {

        for (NLZC207001PMsg msg : params) {
            execute(msg, onBatchType);
        }
    }

    /**
     * @see com.canon.cusa.s21.api.NLZ.NLZC207001.execute
     * @throws SQLException
     */
    public void execute(final NLZC207001PMsg param, final ONBATCH_TYPE onBatchType) {

        EZDDebugOutput.println(1, PROGRAM_ID + "[ execute ] start", this);

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        updateRwsCplt(msgMap, onBatchType);

        msgMap.flush();

        EZDDebugOutput.println(1, PROGRAM_ID + "[ execute ] end", this);

    }

    /**
     * @see com.canon.cusa.s21.api.NLZ.NLZC207001.execute
     * @throws SQLException
     */
    protected void updateRwsCplt(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        boolean errFlg = false;

        NLZC207001PMsg param = (NLZC207001PMsg) msgMap.getPmsg();

        if (!ZYPCommonFunc.hasValue(param.glblCmpyCd)) {
            NLXC025001.outputLog(1, NLAM1006E, new String[] {GLBL_CMPY_CD }, onBatchType, this);
            errFlg = true;
        }

        if (!ZYPCommonFunc.hasValue(param.slsDt)) {
            NLXC025001.outputLog(1, NLAM1006E, new String[] {SLS_DT }, onBatchType, this);
            errFlg = true;
        }

        if (!ZYPCommonFunc.hasValue(param.rwsNum)) {
            NLXC025001.outputLog(1, NLAM1006E, new String[] {RWS_NUM }, onBatchType, this);
            errFlg = true;
        }

        if (!ZYPCommonFunc.hasValue(param.sceOrdTpCd)) {
            NLXC025001.outputLog(1, NLAM1006E, new String[] {SCE_ORD_TP_CD }, onBatchType, this);
            errFlg = true;
        }

        if (!ZYPCommonFunc.hasValue(param.rwsCloDtTmTs)) {
            NLXC025001.outputLog(1, NLAM1006E, new String[] {RWS_CLO_DT_TM_TS }, onBatchType, this);
            errFlg = true;
        }

        if (!ZYPCommonFunc.hasValue(param.whCd)) {
            NLXC025001.outputLog(1, NLAM1006E, new String[] {WH_CD }, onBatchType, this);
            errFlg = true;
        }

        if (!ZYPCommonFunc.hasValue(param.rwsRefNum)) {
            NLXC025001.outputLog(1, NLAM1006E, new String[] {RWS_REF_NUM}, onBatchType, this);
            errFlg = true;
        }

        if (errFlg == true) {
            msgMap.addXxMsgId(NLZM1001E);
            return;
        }

        S21TransactionTableAccessor tranAccess = new S21TransactionTableAccessor();
        BigDecimal seqNum = tranAccess.getNextTransactionId();

        RWS_CPLT_WRKTMsg rwsCpltWrkT = new RWS_CPLT_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(rwsCpltWrkT.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(rwsCpltWrkT.wrkTrxId, ZYPCommonFunc.leftPad(seqNum.toPlainString(), TRAN_ID_LENGTH, LF_PAD_CHAR));
        ZYPEZDItemValueSetter.setValue(rwsCpltWrkT.sqId, ZYPCommonFunc.leftPad("1", SEQ_ID_LENGTH, LF_PAD_CHAR));
        rwsCpltWrkT.errMsgCd.clear();
        ZYPEZDItemValueSetter.setValue(rwsCpltWrkT.rwsNum, param.rwsNum.getValue());
        ZYPEZDItemValueSetter.setValue(rwsCpltWrkT.sceOrdTpCd, param.sceOrdTpCd.getValue());
        rwsCpltWrkT.s80OrdTpCd.clear();
        String ts = param.rwsCloDtTmTs.getValue();
        if (ts.length() == 8) {
            ts += S21SystemDate.getCurrentSystemTime("HHmmss");
        }
        ZYPEZDItemValueSetter.setValue(rwsCpltWrkT.rwsCloDtTmTs, ts);
        ZYPEZDItemValueSetter.setValue(rwsCpltWrkT.whCd, param.whCd.getValue());
        ZYPEZDItemValueSetter.setValue(rwsCpltWrkT.rwsRefNum, param.rwsRefNum.getValue());
        ZYPEZDItemValueSetter.setValue(rwsCpltWrkT.procStsCd, PROC_STS.IN_COMPLETED);
        ZYPEZDItemValueSetter.setValue(rwsCpltWrkT.srcTpCd, SRC_TP_CD_S21); // S21DC

        if (EZDDebugOutput.isDebug()) {
            EZDDebugOutput.println(1, PROGRAM_ID + "TMsg cnt:" + "1", this);
            EZDDebugOutput.println(1, PROGRAM_ID + "GLBL_CMPY_CD:" + rwsCpltWrkT.glblCmpyCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "WRK_TRX_ID:" + rwsCpltWrkT.wrkTrxId.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "SQ_ID:" + rwsCpltWrkT.sqId.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "ERR_MSG_CD:" + rwsCpltWrkT.errMsgCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "SRC_TP_CD:" + rwsCpltWrkT.rwsNum.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "SCE_ORD_TP_CD:" + rwsCpltWrkT.sceOrdTpCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "S80_ORD_TP_CD:" + rwsCpltWrkT.s80OrdTpCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "RWS_CLO_DT_TM_TS:" + rwsCpltWrkT.rwsCloDtTmTs.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "WH_CD:" + rwsCpltWrkT.whCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "RWS_REF_NUM:" + rwsCpltWrkT.rwsRefNum.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "PROC_STS_CD:" + rwsCpltWrkT.procStsCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "SRC_TP_CD:" + rwsCpltWrkT.srcTpCd.getValue(), this);
        }
        S21ApiTBLAccessor.insert(rwsCpltWrkT);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsCpltWrkT.getReturnCode())) {
            throw new S21AbendException(NLAM1133E, new String[] {RWS_CPLT_WRK, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, WRK_TRX_ID, SQ_ID),
                    NLXCMsgHelper.toListedString(rwsCpltWrkT.glblCmpyCd.getValue(), rwsCpltWrkT.wrkTrxId.getValue(), rwsCpltWrkT.sqId.getValue()) });
        }

        NLZC209001PMsg pmsg = new NLZC209001PMsg();
        NLZC209001 api = new NLZC209001();

        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.slsDt, param.slsDt.getValue());

        ZYPEZDItemValueSetter.setValue(pmsg.wrkTrxId, rwsCpltWrkT.wrkTrxId.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.sqId, rwsCpltWrkT.sqId.getValue());

        if (EZDDebugOutput.isDebug()) {
            EZDDebugOutput.println(1, PROGRAM_ID + "PMsg cnt:" + "1", this);
            EZDDebugOutput.println(1, PROGRAM_ID + "GLBL_CMPY_CD:" + pmsg.glblCmpyCd.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "WRK_TRX_ID:" + pmsg.wrkTrxId.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "SQ_ID:" + pmsg.sqId.getValue(), this);
            EZDDebugOutput.println(1, PROGRAM_ID + "SLS_DT:" + pmsg.slsDt.getValue(), this);
        }

        List<NLZC209001PMsg> params = new ArrayList<NLZC209001PMsg>();
        params.add(pmsg);
        api.execute(params, onBatchType);

        if (S21ApiUtil.isXxMsgId(pmsg) == true) {
            for (String xxMsgId : S21ApiUtil.getXxMsgIdList(pmsg)) {
                if (xxMsgId.endsWith("W")) {
                    if (errFlg == false) {
                        ZYPEZDItemValueSetter.setValue(rwsCpltWrkT.procStsCd, PROC_STS.COMPLEATED);
                    }
                    msgMap.addXxMsgId(xxMsgId);
                    EZDDebugOutput.println(1, PROGRAM_ID + "TABLE ERR_CD:" + xxMsgId, this);
                } else {
                    if (errFlg == false) {
                        ZYPEZDItemValueSetter.setValue(rwsCpltWrkT.errMsgCd, xxMsgId);
                        ZYPEZDItemValueSetter.setValue(rwsCpltWrkT.procStsCd, PROC_STS.ERROR);
                        EZDDebugOutput.println(1, PROGRAM_ID + "TABLE ERR_CD:" + xxMsgId, this);
                    }
                    errFlg = true;
                    msgMap.addXxMsgId(xxMsgId);
                    EZDDebugOutput.println(1, PROGRAM_ID + "ERR_CD:" + xxMsgId, this);
                }
            }
        } else {
            ZYPEZDItemValueSetter.setValue(rwsCpltWrkT.procStsCd, PROC_STS.COMPLEATED);
        }

        if (!errFlg) {
            // Call Update Serial for Put Away Confirmation And RWS
            // Completion API
            NLZC407001PMsg upSerPutAwayPmsg = new NLZC407001PMsg();
            ZYPEZDItemValueSetter.setValue(upSerPutAwayPmsg.glblCmpyCd, param.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(upSerPutAwayPmsg.slsDt, param.slsDt);
            ZYPEZDItemValueSetter.setValue(upSerPutAwayPmsg.rwsNum, param.rwsNum);
            ZYPEZDItemValueSetter.setValue(upSerPutAwayPmsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(upSerPutAwayPmsg.xxPrcCloFlg, ZYPConstant.FLG_ON_Y);

            NLZC407001 upSerPutAwayAPI = new NLZC407001();
            upSerPutAwayAPI.execute(upSerPutAwayPmsg, onBatchType);

            if (S21ApiUtil.isXxMsgId(upSerPutAwayPmsg) == true) {
                for (String xxMsgId : S21ApiUtil.getXxMsgIdList(upSerPutAwayPmsg)) {
                    if (xxMsgId.endsWith("W")) {
                        if (errFlg == false) {
                            ZYPEZDItemValueSetter.setValue(rwsCpltWrkT.svcMachProcStsCd, SVC_MACH_PROC_STS.COMPLETED);
                        }
                        msgMap.addXxMsgId(xxMsgId);
                        EZDDebugOutput.println(1, PROGRAM_ID + "TABLE ERR_CD:" + xxMsgId, this);
                    } else {
                        if (errFlg == false) {
                            ZYPEZDItemValueSetter.setValue(rwsCpltWrkT.errMsgCd, xxMsgId);
                            ZYPEZDItemValueSetter.setValue(rwsCpltWrkT.svcMachProcStsCd, SVC_MACH_PROC_STS.ERROR);
                            EZDDebugOutput.println(1, PROGRAM_ID + "TABLE ERR_CD:" + xxMsgId, this);
                        }
                        errFlg = true;
                        msgMap.addXxMsgId(xxMsgId);
                        EZDDebugOutput.println(1, PROGRAM_ID + "ERR_CD:" + xxMsgId, this);
                    }
                }
            } else {
                ZYPEZDItemValueSetter.setValue(rwsCpltWrkT.svcMachProcStsCd, SVC_MACH_PROC_STS.COMPLETED);
            }
        }

        S21ApiTBLAccessor.update(rwsCpltWrkT);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsCpltWrkT.getReturnCode())) {
                throw new S21AbendException(NLAM1134E,
                        new String[] {RWS_CPLT_WRK, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, WRK_TRX_ID, SQ_ID), NLXCMsgHelper.toListedString(rwsCpltWrkT.glblCmpyCd, rwsCpltWrkT.wrkTrxId, rwsCpltWrkT.sqId) });
        }
    }
}
