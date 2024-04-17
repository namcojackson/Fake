/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC206001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import parts.common.EZDDebugOutput;
import parts.common.EZDPMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.RWS_PUT_AWAY_SER_WRKTMsg;
import business.db.RWS_PUT_AWAY_WRKTMsg;
import business.parts.NLZC002001PMsg;
import business.parts.NLZC003001PMsg;
import business.parts.NLZC206001PMsg;
import business.parts.NLZC206001_RWSPutAwayListPMsg;
import business.parts.NLZC206001_RcvSerNumListPMsg;
import business.parts.NLZC208001PMsg;
import business.parts.NLZC302001PMsg;
import business.parts.NLZC407001PMsg;
import business.parts.NSZC001001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC002001.NLZC002001;
import com.canon.cusa.s21.api.NLZ.NLZC003001.NLZC003001;
import com.canon.cusa.s21.api.NLZ.NLZC208001.NLZC208001;
import com.canon.cusa.s21.api.NLZ.NLZC302001.NLZC302001;
import com.canon.cusa.s21.api.NLZ.NLZC407001.NLZC407001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.NSZC001001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.ProcessMode;

import com.canon.cusa.s21.common.NLX.NLXC016001.NLXSceConst;
import com.canon.cusa.s21.common.NLX.NLXC017001.NLXCMsgHelper;
import com.canon.cusa.s21.common.NLX.NLXC025001.NLXC025001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ORD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRT_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SER_TRX_EVENT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SER_TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 *   Put Away Confirmation from S21 DC
 * </pre>
 * 
 * Date        Company     Name       Create/Update    Defect No
 * ----------------------------------------------------------------------
 * 06/09/2009  Fujitsu      H.Haga      Create          N/A
 * 05/15/2014  Fujitsu      Y.Taoka     Update          WDS-NA#203 Serial Capturing
 * 04/03/2018  CITS         T.Tokutomi  Update          QC#25198
 * 09/25/2019  CITS         M.Naito     Update          QC#53695
 * 09/25/2019  CITS         K.Ogino     Update          QC#53359
 *</pre>
 */
public class NLZC206001 extends S21ApiCommonBase {

    /**  */
    public static final String NLZM1001E = "NLZM1001E";

    /**  */
    public static final String NLAM1006E = "NLAM1006E";

    /**  */
    public static final String NLAM1133E = "NLAM1133E";

    /**  */
    public static final String NLAM1134E = "NLAM1134E";

    /* */
    private static final String PROGRAM_ID = "NLZC206001:";

    /* */
    private static final String SRC_TP_CD_S21 = "S";

    /* */
    private static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /* */
    private static final String RWS_NUM = "RWS_NUM";

    /* */
    private static final String RWS_DTL_LINE_NUM = "RWS_DTL_LINE_NUM";

    /* */
    private static final String INVTY_STK_STS_CD = "INVTY_STK_STS_CD";

    /* */
    private static final String SCE_ORD_TP_CD = "SCE_ORD_TP_CD";

    /* */
    private static final String RWS_STK_DT_TM_TS = "RWS_STK_DT_TM_TS";

    /* */
    private static final String RWS_STK_QTY = "RWS_STK_QTY";

    /* */
    private static final String WH_CD = "WH_CD";

    /* */
    private static final String MDSE_CD = "MDSE_CD";

    /* */
    private static final String RWS_REF_NUM = "RWS_REF_NUM";

    /* */
    private static final String SQ_ID = "SQ_ID";

    /* */
    private static final String WRK_TRX_ID = "WRK_TRX_ID";

    /* */
    private static final String RWSPUT_AWAY_LIST = "RWS_PUT_AWAY_LIST";

    /* */
    private static final String SLS_DT = "SLS_DT";

    /* */
    private static final String RWS_PUT_AWAY_WRK = "RWS_PUT_AWAY_WRK";

    /* */
    private static final String LF_PAD_CHAR = "0";

    /* */
    private static final int TRAN_ID_LENGTH = 30;

    /* */
    private static final int SEQ_ID_LENGTH = 10;

    /** column:SER_NUM */
    private static final String SER_NUM = "SER_NUM";

    /** table:RWS_PUT_AWAY_SER_WRK */
    private static final String RWS_PUT_AWAY_SER_WRK = "RWS_PUT_AWAY_SER_WRK";

    /** column length:SER_NUM */
    private static final int SER_LINE_NUM_LENGTH = 3;

    /** API Result Messege */
    private static final String WARNING_CODE = "W";

    /** Common first Line# */
    private static final int commonFirstLineNum = 1;

    /** BIND_GLBL_CMPY_CD */
    private static String BIND_GLBL_CMPY_CD = "glblCmpyCd";

    /** BIND_INVTY_LOC_CD */
    private static String BIND_INVTY_LOC_CD = "invtyLocCd";

    /** BIND_RWS_NUM */
    private static String BIND_RWS_NUM = "rwsNum";

    /** BIND_RWS_DTL_LINE_NUM */
    private static String BIND_RWS_DTL_LINE_NUM = "rwsDtlLineNum";

    /** SSM client */
    private S21SsmBatchClient ssmBatchClient = null;

    /**
     */
    public NLZC206001() {
        super();
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * @see com.canon.cusa.s21.api.NLZ.NLZC207001.execute
     */
    public void execute(final List<NLZC206001PMsg> params, final ONBATCH_TYPE onBatchType) {

        for (NLZC206001PMsg msg : params) {
            execute(msg, onBatchType);
        }
    }

    /**
     * @see com.canon.cusa.s21.api.NLZ.NLZC206001.execute
     */
    public void execute(final NLZC206001PMsg param, final ONBATCH_TYPE onBatchType) {

        EZDDebugOutput.println(1, PROGRAM_ID + "[ execute ] start", this);

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        updateRwsPutAway(msgMap, onBatchType);

        msgMap.flush();

        EZDDebugOutput.println(1, PROGRAM_ID + "[ execute ] end", this);
    }

    /**
     * @see com.canon.cusa.s21.api.NLZ.NLZC206001.updateRwsPutAway
     */
    protected void updateRwsPutAway(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        boolean errFlg = false;

        NLZC206001PMsg param = (NLZC206001PMsg) msgMap.getPmsg();

        if (!ZYPCommonFunc.hasValue(param.glblCmpyCd)) {
            NLXC025001.outputLog(1, NLAM1006E, new String[] {GLBL_CMPY_CD }, onBatchType, this);

            errFlg = true;
        }

        if (!ZYPCommonFunc.hasValue(param.slsDt)) {
            NLXC025001.outputLog(1, NLAM1006E, new String[] {SLS_DT }, onBatchType, this);
            errFlg = true;
        }

        if (param.RWSPutAwayList == null || param.RWSPutAwayList.getValidCount() == 0) {
            NLXC025001.outputLog(1, NLAM1006E, new String[] {RWSPUT_AWAY_LIST }, onBatchType, this);
            msgMap.addXxMsgId(NLZM1001E);
            return;
        }

        for (int i = 0; i < param.RWSPutAwayList.getValidCount(); i++) {

            if (!ZYPCommonFunc.hasValue(param.RWSPutAwayList.no(i).rwsNum)) {
                NLXC025001.outputLog(1, NLAM1006E, new String[] {RWS_NUM }, onBatchType, this);
                errFlg = true;
            }

            if (!ZYPCommonFunc.hasValue(param.RWSPutAwayList.no(i).rwsDtlLineNum)) {
                NLXC025001.outputLog(1, NLAM1006E, new String[] {RWS_DTL_LINE_NUM }, onBatchType, this);
                errFlg = true;
            }

            if (!ZYPCommonFunc.hasValue(param.RWSPutAwayList.no(i).invtyStkStsCd)) {
                NLXC025001.outputLog(1, NLAM1006E, new String[] {INVTY_STK_STS_CD }, onBatchType, this);
                errFlg = true;
            }

            if (!ZYPCommonFunc.hasValue(param.RWSPutAwayList.no(i).sceOrdTpCd)) {
                NLXC025001.outputLog(1, NLAM1006E, new String[] {SCE_ORD_TP_CD }, onBatchType, this);
                errFlg = true;
            }

            if (!ZYPCommonFunc.hasValue(param.RWSPutAwayList.no(i).rwsStkDtTmTs)) {
                NLXC025001.outputLog(1, NLAM1006E, new String[] {RWS_STK_DT_TM_TS }, onBatchType, this);
                errFlg = true;
            }

            if (!ZYPCommonFunc.hasValue(param.RWSPutAwayList.no(i).rwsStkQty)) {
                NLXC025001.outputLog(1, NLAM1006E, new String[] {RWS_STK_QTY }, onBatchType, this);
                errFlg = true;
            }

            if (!ZYPCommonFunc.hasValue(param.RWSPutAwayList.no(i).whCd)) {
                NLXC025001.outputLog(1, NLAM1006E, new String[] {WH_CD }, onBatchType, this);
                errFlg = true;
            }

            if (!ZYPCommonFunc.hasValue(param.RWSPutAwayList.no(i).mdseCd)) {
                NLXC025001.outputLog(1, NLAM1006E, new String[] {MDSE_CD }, onBatchType, this);
                errFlg = true;
            }

            if (!ZYPCommonFunc.hasValue(param.RWSPutAwayList.no(i).rwsRefNum)) {
                NLXC025001.outputLog(1, NLAM1006E, new String[] {RWS_REF_NUM }, onBatchType, this);
                errFlg = true;
            }

        }

        if (errFlg == true) {
            msgMap.addXxMsgId(NLZM1001E);
            return;
        }

        S21TransactionTableAccessor tranAccess = new S21TransactionTableAccessor();
        BigDecimal tranId = tranAccess.getNextTransactionId();

        // init Serial List
        HashMap<String, ArrayList<NLZC206001_RcvSerNumListPMsg>> paramSerialListMap = new HashMap<String, ArrayList<NLZC206001_RcvSerNumListPMsg>>();
        HashSet<String> mdseSerHs = new HashSet<String>();

        for (int i = 0; i < param.RcvSerNumList.getValidCount(); i++) {
            NLZC206001_RcvSerNumListPMsg rwsSerialParam = param.RcvSerNumList.no(i);
            String rwsDtlLineNum = rwsSerialParam.rwsDtlLineNum.getValue();

            if (paramSerialListMap.containsKey(rwsDtlLineNum)) {
                String mdseHsKey = rwsDtlLineNum + rwsSerialParam.mdseCd.getValue() + rwsSerialParam.serNum.getValue();

                if (!mdseSerHs.contains(mdseHsKey)) {
                    ArrayList<NLZC206001_RcvSerNumListPMsg> rcvSerNumParamList = paramSerialListMap.get(rwsDtlLineNum);
                    rcvSerNumParamList.add(rwsSerialParam);
                    mdseSerHs.add(mdseHsKey);
                }
            } else {
                ArrayList<NLZC206001_RcvSerNumListPMsg> rcvSerNumParamList = new ArrayList<NLZC206001_RcvSerNumListPMsg>();
                rcvSerNumParamList.add(rwsSerialParam);
                paramSerialListMap.put(rwsDtlLineNum, rcvSerNumParamList);
                mdseSerHs.add(rwsDtlLineNum + rwsSerialParam.mdseCd.getValue() + rwsSerialParam.serNum.getValue());
            }
        }

        // Init API
        NLZC208001 nlzc208001 = new NLZC208001();
        NLZC407001 nlzc407001 = new NLZC407001();
        NLZC003001 nlzc003001 = new NLZC003001();
        NLZC002001 nlzc002001 = new NLZC002001();
        NSZC001001 nszc001001 = new NSZC001001();
        NLZC302001 nlzc302001 = new NLZC302001();

        for (int i = 0; i < param.RWSPutAwayList.getValidCount(); i++) {
            // Init
            boolean processErrFlg = false;
            HashMap<BigDecimal, BigDecimal> svcMachMstrInfo = new HashMap<BigDecimal, BigDecimal>();

            // 1. Write RWS_PUT_AWAY_WRK
            RWS_PUT_AWAY_WRKTMsg rwsPutAwayWrkT = new RWS_PUT_AWAY_WRKTMsg();

            ZYPEZDItemValueSetter.setValue(rwsPutAwayWrkT.glblCmpyCd, param.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(rwsPutAwayWrkT.wrkTrxId, ZYPCommonFunc.leftPad(tranId.toPlainString(), TRAN_ID_LENGTH, LF_PAD_CHAR));
            rwsPutAwayWrkT.errMsgCd.clear();
            ZYPEZDItemValueSetter.setValue(rwsPutAwayWrkT.srcTpCd, SRC_TP_CD_S21); // S21DC
            ZYPEZDItemValueSetter.setValue(rwsPutAwayWrkT.procStsCd, PROC_STS.IN_COMPLETED);

            rwsPutAwayWrkT.s80OrdTpCd.clear();
            ZYPEZDItemValueSetter.setValue(rwsPutAwayWrkT.sqId, ZYPCommonFunc.leftPad(String.valueOf(i + 1), SEQ_ID_LENGTH, LF_PAD_CHAR));
            ZYPEZDItemValueSetter.setValue(rwsPutAwayWrkT.rwsNum, param.RWSPutAwayList.no(i).rwsNum.getValue());
            ZYPEZDItemValueSetter.setValue(rwsPutAwayWrkT.rwsDtlLineNum, param.RWSPutAwayList.no(i).rwsDtlLineNum.getValue());
            ZYPEZDItemValueSetter.setValue(rwsPutAwayWrkT.invtyStkStsCd, param.RWSPutAwayList.no(i).invtyStkStsCd.getValue());
            ZYPEZDItemValueSetter.setValue(rwsPutAwayWrkT.sceOrdTpCd, param.RWSPutAwayList.no(i).sceOrdTpCd.getValue());
            ZYPEZDItemValueSetter.setValue(rwsPutAwayWrkT.rwsStkDtTmTs, param.RWSPutAwayList.no(i).rwsStkDtTmTs.getValue());
            ZYPEZDItemValueSetter.setValue(rwsPutAwayWrkT.rwsStkQty, param.RWSPutAwayList.no(i).rwsStkQty.getValue());
            ZYPEZDItemValueSetter.setValue(rwsPutAwayWrkT.whCd, param.RWSPutAwayList.no(i).whCd.getValue());
            ZYPEZDItemValueSetter.setValue(rwsPutAwayWrkT.mdseCd, param.RWSPutAwayList.no(i).mdseCd.getValue());
            ZYPEZDItemValueSetter.setValue(rwsPutAwayWrkT.rwsRefNum, param.RWSPutAwayList.no(i).rwsRefNum.getValue());

            if (ZYPCommonFunc.hasValue(param.machMstrUpdFlg) //
                    && ZYPConstant.FLG_ON_Y.equals(param.machMstrUpdFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(rwsPutAwayWrkT.svcMachProcStsCd, PROC_STS.IN_COMPLETED);
            } else {
                ZYPEZDItemValueSetter.setValue(rwsPutAwayWrkT.svcMachProcStsCd, PROC_STS.COMPLEATED);
            }

            if (EZDDebugOutput.isDebug()) {
                EZDDebugOutput.println(1, PROGRAM_ID + "TMsg cnt:" + String.valueOf(i + 1), this);
                EZDDebugOutput.println(1, PROGRAM_ID + "GLBL_CMPY_CD:" + rwsPutAwayWrkT.glblCmpyCd.getValue(), this);
                EZDDebugOutput.println(1, PROGRAM_ID + "WRK_TRX_ID:" + rwsPutAwayWrkT.wrkTrxId.getValue(), this);
                EZDDebugOutput.println(1, PROGRAM_ID + "ERR_MSG_CD:" + rwsPutAwayWrkT.errMsgCd.getValue(), this);
                EZDDebugOutput.println(1, PROGRAM_ID + "SRC_TP_CD:" + rwsPutAwayWrkT.srcTpCd.getValue(), this);
                EZDDebugOutput.println(1, PROGRAM_ID + "PROC_STS_CD:" + rwsPutAwayWrkT.procStsCd.getValue(), this);
                EZDDebugOutput.println(1, PROGRAM_ID + "S80_ORD_TP_CD:" + rwsPutAwayWrkT.s80OrdTpCd.getValue(), this);

                EZDDebugOutput.println(1, PROGRAM_ID + "SQ_ID:" + rwsPutAwayWrkT.sqId.getValue(), this);
                EZDDebugOutput.println(1, PROGRAM_ID + "RWS_NUM:" + rwsPutAwayWrkT.rwsNum.getValue(), this);
                EZDDebugOutput.println(1, PROGRAM_ID + "RWS_DTL_LINE_NUM:" + rwsPutAwayWrkT.rwsDtlLineNum.getValue(), this);
                EZDDebugOutput.println(1, PROGRAM_ID + "INVTY_STK_STS_CD:" + rwsPutAwayWrkT.invtyStkStsCd.getValue(), this);
                EZDDebugOutput.println(1, PROGRAM_ID + "SCE_ORD_TP_CD:" + rwsPutAwayWrkT.sceOrdTpCd.getValue(), this);
                EZDDebugOutput.println(1, PROGRAM_ID + "RWS_STK_DT_TM_TS:" + rwsPutAwayWrkT.rwsStkDtTmTs.getValue(), this);
                EZDDebugOutput.println(1, PROGRAM_ID + "RWS_STK_QTY:" + rwsPutAwayWrkT.rwsStkQty.getValue(), this);
                EZDDebugOutput.println(1, PROGRAM_ID + "WH_CD:" + rwsPutAwayWrkT.whCd.getValue(), this);
                EZDDebugOutput.println(1, PROGRAM_ID + "MDSE_CD:" + rwsPutAwayWrkT.mdseCd.getValue(), this);
                EZDDebugOutput.println(1, PROGRAM_ID + "RWS_REF_NUM:" + rwsPutAwayWrkT.rwsRefNum.getValue(), this);
            }

            S21ApiTBLAccessor.insert(rwsPutAwayWrkT);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsPutAwayWrkT.getReturnCode())) {
                throw new S21AbendException(NLAM1133E, new String[] {RWS_PUT_AWAY_WRK, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, WRK_TRX_ID, SQ_ID),
                        NLXCMsgHelper.toListedString(rwsPutAwayWrkT.glblCmpyCd, rwsPutAwayWrkT.wrkTrxId, rwsPutAwayWrkT.sqId) });
            }

            // 2.Insert RWS_PUT_AWAY_SER_WRK
            String rwsDtlLineNum = rwsPutAwayWrkT.rwsDtlLineNum.getValue();
            if (paramSerialListMap.containsKey(rwsDtlLineNum)) {
                ArrayList<NLZC206001_RcvSerNumListPMsg> rcvSerNumParamList = paramSerialListMap.get(rwsDtlLineNum);
                int qty = rwsPutAwayWrkT.rwsStkQty.getValueInt();
                int serLineCnt = 0;
                int serLineNum = 1;

                for (NLZC206001_RcvSerNumListPMsg paramSerial : rcvSerNumParamList) {
                    if (serLineCnt > qty) {
                        break;
                    }
                    RWS_PUT_AWAY_SER_WRKTMsg rwsPutAwaySerWrkT = new RWS_PUT_AWAY_SER_WRKTMsg();
                    ZYPEZDItemValueSetter.setValue(rwsPutAwaySerWrkT.glblCmpyCd, param.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(rwsPutAwaySerWrkT.wrkTrxId, rwsPutAwayWrkT.wrkTrxId);
                    ZYPEZDItemValueSetter.setValue(rwsPutAwaySerWrkT.sqId, rwsPutAwayWrkT.sqId);
                    ZYPEZDItemValueSetter.setValue(rwsPutAwaySerWrkT.serLineNum, ZYPCommonFunc.leftPad(String.valueOf(serLineNum), SER_LINE_NUM_LENGTH, LF_PAD_CHAR));
                    ZYPEZDItemValueSetter.setValue(rwsPutAwaySerWrkT.rwsNum, rwsPutAwayWrkT.rwsNum);
                    ZYPEZDItemValueSetter.setValue(rwsPutAwaySerWrkT.rwsDtlLineNum, paramSerial.rwsDtlLineNum);
                    ZYPEZDItemValueSetter.setValue(rwsPutAwaySerWrkT.invtyStkStsCd, rwsPutAwayWrkT.invtyStkStsCd);
                    ZYPEZDItemValueSetter.setValue(rwsPutAwaySerWrkT.serNum, paramSerial.serNum);
                    ZYPEZDItemValueSetter.setValue(rwsPutAwaySerWrkT.mdseCd, paramSerial.mdseCd);

                    S21ApiTBLAccessor.insert(rwsPutAwaySerWrkT);

                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsPutAwaySerWrkT.getReturnCode())) {
                        throw new S21AbendException(NLAM1133E, new String[] {RWS_PUT_AWAY_SER_WRK, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, WRK_TRX_ID, SQ_ID, SER_NUM),
                                NLXCMsgHelper.toListedString(rwsPutAwaySerWrkT.glblCmpyCd, rwsPutAwaySerWrkT.wrkTrxId, rwsPutAwaySerWrkT.sqId, rwsPutAwaySerWrkT.serNum) });
                    }

                    serLineNum++;
                    serLineCnt++;
                }
            }

            // 3.Call Update put away confirmation API
            NLZC208001PMsg nlzc208001PMsg = new NLZC208001PMsg();
            ZYPEZDItemValueSetter.setValue(nlzc208001PMsg.glblCmpyCd, param.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(nlzc208001PMsg.slsDt, param.slsDt.getValue());
            ZYPEZDItemValueSetter.setValue(nlzc208001PMsg.wrkTrxId, rwsPutAwayWrkT.wrkTrxId.getValue());
            ZYPEZDItemValueSetter.setValue(nlzc208001PMsg.sqId, rwsPutAwayWrkT.sqId.getValue());

            if (EZDDebugOutput.isDebug()) {
                EZDDebugOutput.println(1, PROGRAM_ID + "PMsg cnt:" + String.valueOf(i + 1), this);
                EZDDebugOutput.println(1, PROGRAM_ID + "GLBL_CMPY_CD:" + nlzc208001PMsg.glblCmpyCd.getValue(), this);
                EZDDebugOutput.println(1, PROGRAM_ID + "WRK_TRX_ID:" + nlzc208001PMsg.wrkTrxId.getValue(), this);
                EZDDebugOutput.println(1, PROGRAM_ID + "SQ_ID:" + nlzc208001PMsg.sqId.getValue(), this);
                EZDDebugOutput.println(1, PROGRAM_ID + "SLS_DT:" + nlzc208001PMsg.slsDt.getValue(), this);
            }

            // Execute Update put away confirmation API
            nlzc208001.execute(nlzc208001PMsg, onBatchType);
            processErrFlg = chkAPIError(msgMap, nlzc208001PMsg, param, rwsPutAwayWrkT, processErrFlg);

            if (!processErrFlg) {
                // Success Update put away confirmation API
                ZYPEZDItemValueSetter.setValue(rwsPutAwayWrkT.procStsCd, PROC_STS.COMPLEATED);

                S21ApiTBLAccessor.update(rwsPutAwayWrkT);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsPutAwayWrkT.getReturnCode())) {
                    throw new S21AbendException(NLAM1134E, new String[] {RWS_PUT_AWAY_WRK, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, WRK_TRX_ID, SQ_ID),
                            NLXCMsgHelper.toListedString(rwsPutAwayWrkT.glblCmpyCd, rwsPutAwayWrkT.wrkTrxId, rwsPutAwayWrkT.sqId) });
                }
            }

            // 4.Call Update Serial for Put Away Confirmation And RWS
            // Completion API
            if (!processErrFlg && ZYPConstant.FLG_ON_Y.equals(param.machMstrUpdFlg.getValue())) {
                NLZC407001PMsg nlzc407001PMsg = new NLZC407001PMsg();
                int index = 0;

                ZYPEZDItemValueSetter.setValue(nlzc407001PMsg.glblCmpyCd, param.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(nlzc407001PMsg.slsDt, param.slsDt);
                ZYPEZDItemValueSetter.setValue(nlzc407001PMsg.rwsNum, rwsPutAwayWrkT.rwsNum);
                ZYPEZDItemValueSetter.setValue(nlzc407001PMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(nlzc407001PMsg.xxPrcCloFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(nlzc407001PMsg.inputList.no(index).wrkTrxId, rwsPutAwayWrkT.wrkTrxId);
                ZYPEZDItemValueSetter.setValue(nlzc407001PMsg.inputList.no(index).sqId, rwsPutAwayWrkT.sqId);
                ZYPEZDItemValueSetter.setValue(nlzc407001PMsg.inputList.no(index).rwsDtlLineNum, rwsPutAwayWrkT.rwsDtlLineNum);
                ZYPEZDItemValueSetter.setValue(nlzc407001PMsg.inputList.no(index).invtyStkStsCd, rwsPutAwayWrkT.invtyStkStsCd);
                ZYPEZDItemValueSetter.setValue(nlzc407001PMsg.inputList.no(index).mdseCd, rwsPutAwayWrkT.mdseCd);

                nlzc407001PMsg.inputList.setValidCount(index + 1);

                // Execute Update Serial for Put Away Confirmation And
                // RWS Completion API
                nlzc407001.execute(nlzc407001PMsg, onBatchType);
                processErrFlg = chkAPIErrorForSvcMachMstr(msgMap, nlzc407001PMsg, param, rwsPutAwayWrkT, processErrFlg);

                if (!processErrFlg) {
                    for (int j = 0; j < nlzc407001PMsg.outputList.getValidCount(); j++) {
                        BigDecimal svcMachMstrPK = nlzc407001PMsg.outputList.no(j).svcMachMstrPk.getValue();
                        BigDecimal svcConfigMstrPK = nlzc407001PMsg.outputList.no(j).svcConfigMstrPk.getValue();
                        svcMachMstrInfo.put(svcMachMstrPK, svcConfigMstrPK);
                    }
                }
            }

            // Error Check
            if (processErrFlg) {
                continue;
            }

            // Internal Transfer Process
            String destInvtyLocCd = param.RWSPutAwayList.no(i).destInvtyLocCd.getValue();
            String whCd = param.RWSPutAwayList.no(i).whCd.getValue();

            if (ZYPCommonFunc.hasValue(destInvtyLocCd) //
                    && ZYPCommonFunc.hasValue(whCd) && !destInvtyLocCd.equals(whCd)) {

                // 5.Call Inventory Order API
                // Create Header
                NLZC003001PMsg invtyOrdApiHdrPmsg = new NLZC003001PMsg();

                ZYPEZDItemValueSetter.setValue(invtyOrdApiHdrPmsg.xxProcTpCd, NLZC003001.PROC_TP_CRAT);
                ZYPEZDItemValueSetter.setValue(invtyOrdApiHdrPmsg.glblCmpyCd, param.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(invtyOrdApiHdrPmsg.xxDtTpCd, NLZC003001.DT_TP_HDR);
                ZYPEZDItemValueSetter.setValue(invtyOrdApiHdrPmsg.invtyOrdTpCd, INVTY_ORD_TP.INTERNAL_DC_TRANSFER);
                ZYPEZDItemValueSetter.setValue(invtyOrdApiHdrPmsg.invtyLocCd, param.RWSPutAwayList.no(i).whCd);
                ZYPEZDItemValueSetter.setValue(invtyOrdApiHdrPmsg.locStsCd, LOC_STS.DC_STOCK);
                ZYPEZDItemValueSetter.setValue(invtyOrdApiHdrPmsg.trxCd, TRX.MOVEMENT);
                ZYPEZDItemValueSetter.setValue(invtyOrdApiHdrPmsg.trxRsnCd, TRX_RSN.INTERNAL_WH_TRANSFER_STOCK_OUT);
                ZYPEZDItemValueSetter.setValue(invtyOrdApiHdrPmsg.shpgSvcLvlCd, SHPG_SVC_LVL.GROUND_SERVICE);
                ZYPEZDItemValueSetter.setValue(invtyOrdApiHdrPmsg.dcTrnsfRsdDt, param.slsDt);
                ZYPEZDItemValueSetter.setValue(invtyOrdApiHdrPmsg.dmgClmCpltFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(invtyOrdApiHdrPmsg.invtyOrdStsCd, INVTY_ORD_STS.CLOSED);
                ZYPEZDItemValueSetter.setValue(invtyOrdApiHdrPmsg.trxSrcTpCd, TRX_SRC_TP.WAREHOUSE_TRANSFER);
                ZYPEZDItemValueSetter.setValue(invtyOrdApiHdrPmsg.sysSrcCd, SYS_SRC.S21_LOGISTICS);

                // Create Detail
                NLZC003001PMsg invtyOrdApiDtlPmsg = new NLZC003001PMsg();

                ZYPEZDItemValueSetter.setValue(invtyOrdApiDtlPmsg.xxProcTpCd, NLZC003001.PROC_TP_CRAT);
                ZYPEZDItemValueSetter.setValue(invtyOrdApiDtlPmsg.glblCmpyCd, param.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(invtyOrdApiDtlPmsg.invtyOrdLineNum, ZYPCommonFunc.leftPad(Integer.toString(commonFirstLineNum), 3, "0"));
                ZYPEZDItemValueSetter.setValue(invtyOrdApiDtlPmsg.mdseCd, param.RWSPutAwayList.no(i).mdseCd);
                ZYPEZDItemValueSetter.setValue(invtyOrdApiDtlPmsg.stkStsCd, param.RWSPutAwayList.no(i).invtyStkStsCd);
                ZYPEZDItemValueSetter.setValue(invtyOrdApiDtlPmsg.invtyLocCd, param.RWSPutAwayList.no(i).whCd);
                ZYPEZDItemValueSetter.setValue(invtyOrdApiDtlPmsg.invtyLocCd_D1, param.RWSPutAwayList.no(i).destInvtyLocCd);
                ZYPEZDItemValueSetter.setValue(invtyOrdApiDtlPmsg.locStsCd_D1, LOC_STS.DC_STOCK);
                ZYPEZDItemValueSetter.setValue(invtyOrdApiDtlPmsg.toStkStsCd, param.RWSPutAwayList.no(i).invtyStkStsCd);
                ZYPEZDItemValueSetter.setValue(invtyOrdApiDtlPmsg.ordQty, param.RWSPutAwayList.no(i).rwsStkQty);
                ZYPEZDItemValueSetter.setValue(invtyOrdApiDtlPmsg.invtyOrdDtlStsCd, INVTY_ORD_STS.CLOSED);
                ZYPEZDItemValueSetter.setValue(invtyOrdApiDtlPmsg.xxDtTpCd, NLZC003001.DT_TP_DTL);
                ZYPEZDItemValueSetter.setValue(invtyOrdApiDtlPmsg.invtyOrdTpCd, INVTY_ORD_TP.INTERNAL_DC_TRANSFER);
                ZYPEZDItemValueSetter.setValue(invtyOrdApiDtlPmsg.trxRefNum, param.RWSPutAwayList.no(i).rwsNum);
                ZYPEZDItemValueSetter.setValue(invtyOrdApiDtlPmsg.trxRefLineNum, param.RWSPutAwayList.no(i).rwsDtlLineNum);

                if (paramSerialListMap.containsKey(rwsDtlLineNum)) {
                    ArrayList<NLZC206001_RcvSerNumListPMsg> rcvSerNumParamList = paramSerialListMap.get(rwsDtlLineNum);
                    int serIndex = 0;
                    for (NLZC206001_RcvSerNumListPMsg paramSerial : rcvSerNumParamList) {
                        ZYPEZDItemValueSetter.setValue(invtyOrdApiDtlPmsg.serialInfoList.no(serIndex).invtyOrdLineNum, invtyOrdApiDtlPmsg.invtyOrdLineNum);
                        ZYPEZDItemValueSetter.setValue(invtyOrdApiDtlPmsg.serialInfoList.no(serIndex).serNum, paramSerial.serNum);
                        serIndex++;
                    }
                }

                // Execute Inventory Order API for Header
                nlzc003001.execute(invtyOrdApiHdrPmsg, onBatchType);
                processErrFlg = chkAPIError(msgMap, invtyOrdApiHdrPmsg, param, rwsPutAwayWrkT, processErrFlg);

                // Execute Inventory Order API for Detail
                nlzc003001.execute(invtyOrdApiDtlPmsg, onBatchType);
                processErrFlg = chkAPIError(msgMap, invtyOrdApiDtlPmsg, param, rwsPutAwayWrkT, processErrFlg);

                if (processErrFlg) {
                    // Error
                    continue;
                }

                // 6.Call Inventory Update API
                NLZC002001PMsg ivtyUpdStkOutParam = setInvtyUpdApiPmsg(param, i, invtyOrdApiDtlPmsg, TRX_RSN.INTERNAL_WH_TRANSFER_STOCK_OUT);
                NLZC002001PMsg ivtyUpdStkInParam = setInvtyUpdApiPmsg(param, i, invtyOrdApiDtlPmsg, TRX_RSN.INTERNAL_WH_TRANSFER_STOCK_IN);

                // Execute Inventory Update API for Stock Out
                nlzc002001.execute(ivtyUpdStkOutParam, onBatchType);
                processErrFlg = chkAPIError(msgMap, ivtyUpdStkOutParam, param, rwsPutAwayWrkT, processErrFlg);

                // Execute Inventory Update API for Stock In
                nlzc002001.execute(ivtyUpdStkInParam, onBatchType);
                processErrFlg = chkAPIError(msgMap, ivtyUpdStkInParam, param, rwsPutAwayWrkT, processErrFlg);

                if (processErrFlg) {
                    // Error
                    continue;
                }

                // 7. Call Machine Master Update
                if (ZYPConstant.FLG_ON_Y.equals(param.machMstrUpdFlg.getValue()) && !svcMachMstrInfo.isEmpty()) {

                    for (Entry<BigDecimal, BigDecimal> smm : svcMachMstrInfo.entrySet()) {

                        BigDecimal svcMachMstrPK = smm.getKey();
                        BigDecimal svcConfigMstrPK = smm.getValue();

                        NSZC001001PMsg nszc001001PMsg = new NSZC001001PMsg();

                        ZYPEZDItemValueSetter.setValue(nszc001001PMsg.glblCmpyCd, param.glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(nszc001001PMsg.slsDt, param.slsDt);
                        ZYPEZDItemValueSetter.setValue(nszc001001PMsg.xxModeCd, ProcessMode.UPDATE_INVENTORY.code);
                        ZYPEZDItemValueSetter.setValue(nszc001001PMsg.svcMachMstrPk, svcMachMstrPK);
                        ZYPEZDItemValueSetter.setValue(nszc001001PMsg.svcConfigMstrPk, svcConfigMstrPK);
                        ZYPEZDItemValueSetter.setValue(nszc001001PMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.CREATED);
                        ZYPEZDItemValueSetter.setValue(nszc001001PMsg.stkStsCd, param.RWSPutAwayList.no(i).invtyStkStsCd);
                        ZYPEZDItemValueSetter.setValue(nszc001001PMsg.svcMachMstrLocStsCd, LOC_STS.DC_STOCK);
                        ZYPEZDItemValueSetter.setValue(nszc001001PMsg.curLocNum, param.RWSPutAwayList.no(i).destInvtyLocCd);

                        // Execute Machine Master Update API
                        nszc001001.execute(nszc001001PMsg, onBatchType);
                        processErrFlg = chkAPIError(msgMap, nszc001001PMsg, param, rwsPutAwayWrkT, processErrFlg);

                        if (processErrFlg) {
                            // Error
                            continue;
                        }
                    }
                }

                // 8. Call Serial Transaction API
                if (paramSerialListMap.containsKey(rwsDtlLineNum)) {
                    ArrayList<NLZC206001_RcvSerNumListPMsg> paramSerialList = paramSerialListMap.get(rwsDtlLineNum);

                    NLZC302001PMsg serTrxStkOutPMsg = setSerialTransactionApiPmsg(param, param.RWSPutAwayList.no(i), paramSerialList, invtyOrdApiDtlPmsg, SER_TRX_EVENT.FIELD_TRANSFER_STOCK_OUT);
                    NLZC302001PMsg serTrxStkInPMsg = setSerialTransactionApiPmsg(param, param.RWSPutAwayList.no(i), paramSerialList, invtyOrdApiDtlPmsg, SER_TRX_EVENT.FIELD_TRANSFER_STOCK_IN);

                    // Execute Serial Transaction API(Stock Out)
                    nlzc302001.execute(serTrxStkOutPMsg, onBatchType);
                    processErrFlg = chkAPIError(msgMap, serTrxStkOutPMsg, param, rwsPutAwayWrkT, processErrFlg);

                    // Execute Serial Transaction API(Stock In)
                    nlzc302001.execute(serTrxStkInPMsg, onBatchType);
                    processErrFlg = chkAPIError(msgMap, serTrxStkInPMsg, param, rwsPutAwayWrkT, processErrFlg);

                    if (processErrFlg) {
                        // Error
                        continue;
                    }
                }

                if (!processErrFlg) {
                    // Success
                    ZYPEZDItemValueSetter.setValue(rwsPutAwayWrkT.procStsCd, PROC_STS.COMPLEATED);
                    rwsPutAwayWrkT.errMsgCd.clear();

                    S21ApiTBLAccessor.update(rwsPutAwayWrkT);

                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsPutAwayWrkT.getReturnCode())) {
                        throw new S21AbendException(NLAM1134E, new String[] {RWS_PUT_AWAY_WRK, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, WRK_TRX_ID, SQ_ID),
                                NLXCMsgHelper.toListedString(rwsPutAwayWrkT.glblCmpyCd, rwsPutAwayWrkT.wrkTrxId, rwsPutAwayWrkT.sqId) });
                    }
                }
            }

            // QC#25198 Tool Item Expense Out
            if (expenseOutItem(param.glblCmpyCd.getValue(), param.RWSPutAwayList.no(i).mdseCd.getValue())) {
                if (ZYPCommonFunc.hasValue(whCd) && !expenseOutLocTp(param.glblCmpyCd.getValue(), whCd)) {
                    // not target data.
                    continue;
                }
                if (ZYPCommonFunc.hasValue(destInvtyLocCd) && !expenseOutLocTp(param.glblCmpyCd.getValue(), destInvtyLocCd)) {
                    // not target data.
                    continue;
                }

                Map<String, Object> rcvOrdNumList = getRcvOrdNumList(param.glblCmpyCd.getValue() //
                        , param.RWSPutAwayList.no(i).rwsNum.getValue(), param.RWSPutAwayList.no(i).rwsDtlLineNum.getValue());
                NLZC002001PMsg expenseOutPmsg = new NLZC002001PMsg();

                // Check Internal Transfer
                String invtyLocCd = param.RWSPutAwayList.no(i).whCd.getValue();
                if (ZYPCommonFunc.hasValue(destInvtyLocCd) //
                        && ZYPCommonFunc.hasValue(whCd) && !destInvtyLocCd.equals(whCd)) {
                    invtyLocCd = destInvtyLocCd;
                }

                if (NLXSceConst.SCE_ORD_TP_CD_DG.equals(rwsPutAwayWrkT.sceOrdTpCd.getValue()) //
                        || NLXSceConst.SCE_ORD_TP_CD_DP.equals(rwsPutAwayWrkT.sceOrdTpCd.getValue())) {

                    // START 2019/09/25 M.Naito [QC#53695, ADD]
                    if (rcvOrdNumList == null) {
                        rcvOrdNumList = getRcvOrdNumListFromPO(param.glblCmpyCd.getValue(), param.RWSPutAwayList.no(i).rwsNum.getValue(), param.RWSPutAwayList.no(i).rwsDtlLineNum.getValue());
                    }
                    // END 2019/09/25 M.Naito [QC#53695, ADD]

                    ZYPEZDItemValueSetter.setValue(expenseOutPmsg.xxRqstTpCd, NLZC002001.RQST_STOCK_OUT);
                    ZYPEZDItemValueSetter.setValue(expenseOutPmsg.glblCmpyCd, param.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(expenseOutPmsg.trxCd, TRX.ADJUSTMENT);
                    ZYPEZDItemValueSetter.setValue(expenseOutPmsg.trxRsnCd, TRX_RSN.REPLEN_TOOL_EXPENSE_OUT);
                    ZYPEZDItemValueSetter.setValue(expenseOutPmsg.xxTrxDtlCd, NLZC002001.TRX_DTL_RCV_PO);
                    ZYPEZDItemValueSetter.setValue(expenseOutPmsg.poOrdNum, (String) rcvOrdNumList.get("PO_ORD_NUM"));
                    ZYPEZDItemValueSetter.setValue(expenseOutPmsg.poOrdDtlLineNum, (String) rcvOrdNumList.get("PO_ORD_DTL_LINE_NUM"));
                    ZYPEZDItemValueSetter.setValue(expenseOutPmsg.mdseCd, param.RWSPutAwayList.no(i).mdseCd);
                    ZYPEZDItemValueSetter.setValue(expenseOutPmsg.invtyLocCd, invtyLocCd);
                    ZYPEZDItemValueSetter.setValue(expenseOutPmsg.locStsCd, LOC_STS.DC_STOCK);
                    ZYPEZDItemValueSetter.setValue(expenseOutPmsg.stkStsCd, STK_STS.GOOD);
                    // QC#53359
//                    ZYPEZDItemValueSetter.setValue(expenseOutPmsg.xxRqstQty, (BigDecimal) rcvOrdNumList.get("RWS_QTY"));
                    ZYPEZDItemValueSetter.setValue(expenseOutPmsg.xxRqstQty, param.RWSPutAwayList.no(i).rwsStkQty);
                    ZYPEZDItemValueSetter.setValue(expenseOutPmsg.invtyTrxDt, param.slsDt);
                    ZYPEZDItemValueSetter.setValue(expenseOutPmsg.xxSysTp, NLZC002001.SYS_TP_OTBD);
                    ZYPEZDItemValueSetter.setValue(expenseOutPmsg.sysSrcCd, SYS_SRC.S21_LOGISTICS);
                    ZYPEZDItemValueSetter.setValue(expenseOutPmsg.poRcvNum, (String) rcvOrdNumList.get("PO_RCV_NUM"));
                    ZYPEZDItemValueSetter.setValue(expenseOutPmsg.poRcvLineNum, (String) rcvOrdNumList.get("PO_RCV_LINE_NUM"));
                    ZYPEZDItemValueSetter.setValue(expenseOutPmsg.rwsNum, (String) rcvOrdNumList.get("RWS_NUM"));
                    ZYPEZDItemValueSetter.setValue(expenseOutPmsg.rwsRefNum, (String) rcvOrdNumList.get("RWS_REF_NUM"));
                    ZYPEZDItemValueSetter.setValue(expenseOutPmsg.rwsDtlLineNum, (String) rcvOrdNumList.get("RWS_DTL_LINE_NUM"));
                    ZYPEZDItemValueSetter.setValue(expenseOutPmsg.tocCd, (String) rcvOrdNumList.get("RQST_TECH_TOC_CD"));

                } else if (NLXSceConst.SCE_ORD_TP_CD_TR.equals(rwsPutAwayWrkT.sceOrdTpCd.getValue())) {

                    ZYPEZDItemValueSetter.setValue(expenseOutPmsg.xxRqstTpCd, NLZC002001.RQST_STOCK_OUT);
                    ZYPEZDItemValueSetter.setValue(expenseOutPmsg.glblCmpyCd, param.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(expenseOutPmsg.trxCd, TRX.ADJUSTMENT);
                    ZYPEZDItemValueSetter.setValue(expenseOutPmsg.trxRsnCd, TRX_RSN.REPLEN_TOOL_EXPENSE_OUT);
                    ZYPEZDItemValueSetter.setValue(expenseOutPmsg.xxTrxDtlCd, NLZC002001.TRX_DTL_RCV_WH_TRSF);
                    ZYPEZDItemValueSetter.setValue(expenseOutPmsg.invtyOrdNum, (String) rcvOrdNumList.get("INVTY_ORD_NUM"));
                    ZYPEZDItemValueSetter.setValue(expenseOutPmsg.invtyOrdLineNum, (String) rcvOrdNumList.get("INVTY_ORD_LINE_NUM"));
                    ZYPEZDItemValueSetter.setValue(expenseOutPmsg.invtyTrxSlpNum, (String) rcvOrdNumList.get("PRCH_REQ_NUM"));
                    ZYPEZDItemValueSetter.setValue(expenseOutPmsg.mdseCd, param.RWSPutAwayList.no(i).mdseCd);
                    ZYPEZDItemValueSetter.setValue(expenseOutPmsg.invtyLocCd, invtyLocCd);
                    ZYPEZDItemValueSetter.setValue(expenseOutPmsg.locStsCd, LOC_STS.DC_STOCK);
                    ZYPEZDItemValueSetter.setValue(expenseOutPmsg.stkStsCd, STK_STS.GOOD);
                    // QC#53359
//                    ZYPEZDItemValueSetter.setValue(expenseOutPmsg.xxRqstQty, (BigDecimal) rcvOrdNumList.get("RWS_QTY"));
                    ZYPEZDItemValueSetter.setValue(expenseOutPmsg.xxRqstQty, param.RWSPutAwayList.no(i).rwsStkQty);
                    ZYPEZDItemValueSetter.setValue(expenseOutPmsg.invtyTrxDt, param.slsDt);
                    ZYPEZDItemValueSetter.setValue(expenseOutPmsg.xxSysTp, NLZC002001.SYS_TP_OTBD);
                    ZYPEZDItemValueSetter.setValue(expenseOutPmsg.sysSrcCd, SYS_SRC.S21_LOGISTICS);
                    ZYPEZDItemValueSetter.setValue(expenseOutPmsg.poRcvNum, (String) rcvOrdNumList.get("PO_RCV_NUM"));
                    ZYPEZDItemValueSetter.setValue(expenseOutPmsg.poRcvLineNum, (String) rcvOrdNumList.get("PO_RCV_LINE_NUM"));
                    ZYPEZDItemValueSetter.setValue(expenseOutPmsg.rwsNum, (String) rcvOrdNumList.get("RWS_NUM"));
                    ZYPEZDItemValueSetter.setValue(expenseOutPmsg.rwsRefNum, (String) rcvOrdNumList.get("RWS_REF_NUM"));
                    ZYPEZDItemValueSetter.setValue(expenseOutPmsg.rwsDtlLineNum, (String) rcvOrdNumList.get("RWS_DTL_LINE_NUM"));
                    ZYPEZDItemValueSetter.setValue(expenseOutPmsg.tocCd, (String) rcvOrdNumList.get("RQST_TECH_TOC_CD"));
                    ZYPEZDItemValueSetter.setValue(expenseOutPmsg.invtyTrxSlpNum, (String) rcvOrdNumList.get("PRCH_REQ_NUM"));
                }

                // Execute Inventory Update API
                nlzc002001.execute(expenseOutPmsg, onBatchType);
                processErrFlg = chkAPIError(msgMap, expenseOutPmsg, param, rwsPutAwayWrkT, processErrFlg);

            }
        }
    }

    /**
     * setInvtyUpdApiPmsg
     * @param param NLZC206001PMsg
     * @param i int
     * @param invtyOrdApiDtlPmsg NLZC003001PMsg
     * @param trxRsnCd String
     * @return NLZC002001PMsg
     */
    private NLZC002001PMsg setInvtyUpdApiPmsg(NLZC206001PMsg param, int i, NLZC003001PMsg invtyOrdApiDtlPmsg, String trxRsnCd) {
        NLZC002001PMsg invtyUpdApiPmsg = new NLZC002001PMsg();

        String glblCmpyCd = param.glblCmpyCd.getValue();
        String destInvtyLocCd = param.RWSPutAwayList.no(i).destInvtyLocCd.getValue();
        String rwsNum = param.RWSPutAwayList.no(i).rwsNum.getValue();
        String rwsDtlLineNum = param.RWSPutAwayList.no(i).rwsDtlLineNum.getValue();

        if (TRX_RSN.INTERNAL_WH_TRANSFER_STOCK_OUT.equals(trxRsnCd)) {

            ZYPEZDItemValueSetter.setValue(invtyUpdApiPmsg.xxSysTp, NLZC002001.SYS_TP_OTBD);
            ZYPEZDItemValueSetter.setValue(invtyUpdApiPmsg.xxRqstTpCd, NLZC002001.RQST_STOCK_OUT);
            ZYPEZDItemValueSetter.setValue(invtyUpdApiPmsg.invtyLocCd, param.RWSPutAwayList.no(i).whCd);
            ZYPEZDItemValueSetter.setValue(invtyUpdApiPmsg.xxTrxDtlCd, NLZC002001.TRX_DTL_SHIP_CONF);
            ZYPEZDItemValueSetter.setValue(invtyUpdApiPmsg.shipToCustCd, param.RWSPutAwayList.no(i).destInvtyLocCd);
            ZYPEZDItemValueSetter.setValue(invtyUpdApiPmsg.shipToCustNm, getDestInvtyLocNm(glblCmpyCd, destInvtyLocCd));

        } else {

            ZYPEZDItemValueSetter.setValue(invtyUpdApiPmsg.xxSysTp, NLZC002001.SYS_TP_INBD);
            ZYPEZDItemValueSetter.setValue(invtyUpdApiPmsg.xxRqstTpCd, NLZC002001.RQST_STOCK_IN);
            ZYPEZDItemValueSetter.setValue(invtyUpdApiPmsg.invtyLocCd, param.RWSPutAwayList.no(i).destInvtyLocCd);
            ZYPEZDItemValueSetter.setValue(invtyUpdApiPmsg.xxTrxDtlCd, NLZC002001.TRX_DTL_RCV_CONF);
        }

        ZYPEZDItemValueSetter.setValue(invtyUpdApiPmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(invtyUpdApiPmsg.mdseCd, param.RWSPutAwayList.no(i).mdseCd);
        ZYPEZDItemValueSetter.setValue(invtyUpdApiPmsg.locStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(invtyUpdApiPmsg.stkStsCd, param.RWSPutAwayList.no(i).invtyStkStsCd);
        ZYPEZDItemValueSetter.setValue(invtyUpdApiPmsg.xxRqstQty, param.RWSPutAwayList.no(i).rwsStkQty);
        ZYPEZDItemValueSetter.setValue(invtyUpdApiPmsg.trxCd, TRX.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(invtyUpdApiPmsg.trxRsnCd, trxRsnCd);
        ZYPEZDItemValueSetter.setValue(invtyUpdApiPmsg.invtyTrxDt, param.slsDt);
        ZYPEZDItemValueSetter.setValue(invtyUpdApiPmsg.sysSrcCd, SYS_SRC.S21_LOGISTICS);
        ZYPEZDItemValueSetter.setValue(invtyUpdApiPmsg.invtyOrdNum, invtyOrdApiDtlPmsg.invtyOrdNum);
        ZYPEZDItemValueSetter.setValue(invtyUpdApiPmsg.invtyOrdLineNum, invtyOrdApiDtlPmsg.invtyOrdLineNum);
        ZYPEZDItemValueSetter.setValue(invtyUpdApiPmsg.invtyTrxSlpNum, getSrcOrdNum(glblCmpyCd, rwsNum));
        ZYPEZDItemValueSetter.setValue(invtyUpdApiPmsg.shipFromLocCustCd, param.RWSPutAwayList.no(i).whCd);
        ZYPEZDItemValueSetter.setValue(invtyUpdApiPmsg.uomCd, PKG_UOM.EACH);

        return invtyUpdApiPmsg;
    }

    /**
     * setSerialTransactionApiPmsg
     * @param param
     * @param paramRWSPutAway
     * @param paramSerialList
     * @param invtyOrdApiDtlPmsg
     * @param serTrxEventCd
     * @return
     */
    private NLZC302001PMsg setSerialTransactionApiPmsg(NLZC206001PMsg param, NLZC206001_RWSPutAwayListPMsg paramRWSPutAway, ArrayList<NLZC206001_RcvSerNumListPMsg> paramSerialList, NLZC003001PMsg invtyOrdApiDtlPmsg, String serTrxEventCd) {
        NLZC302001PMsg serTrxApiPMsg = new NLZC302001PMsg();

        ZYPEZDItemValueSetter.setValue(serTrxApiPMsg.glblCmpyCd, param.glblCmpyCd);

        int i = 0;
        for (NLZC206001_RcvSerNumListPMsg paramSerial : paramSerialList) {
            ZYPEZDItemValueSetter.setValue(serTrxApiPMsg.UpdateDetailList.no(i).serNum, paramSerial.serNum);
            ZYPEZDItemValueSetter.setValue(serTrxApiPMsg.UpdateDetailList.no(i).mdseCd, paramSerial.mdseCd);
            ZYPEZDItemValueSetter.setValue(serTrxApiPMsg.UpdateDetailList.no(i).serTrxTs, paramRWSPutAway.rwsStkDtTmTs);
            ZYPEZDItemValueSetter.setValue(serTrxApiPMsg.UpdateDetailList.no(i).serTrxEventCd, serTrxEventCd);
            ZYPEZDItemValueSetter.setValue(serTrxApiPMsg.UpdateDetailList.no(i).serTrxSrcTpCd, SER_TRX_SRC_TP.INVENTORY_ORDER);
            ZYPEZDItemValueSetter.setValue(serTrxApiPMsg.UpdateDetailList.no(i).serTrxSrcHdrNum, invtyOrdApiDtlPmsg.invtyOrdNum);
            ZYPEZDItemValueSetter.setValue(serTrxApiPMsg.UpdateDetailList.no(i).serTrxSrcLineNum, invtyOrdApiDtlPmsg.invtyOrdLineNum);
            ZYPEZDItemValueSetter.setValue(serTrxApiPMsg.UpdateDetailList.no(i).serTrxRefNum, paramRWSPutAway.rwsNum);
            ZYPEZDItemValueSetter.setValue(serTrxApiPMsg.UpdateDetailList.no(i).fromLocCd, paramRWSPutAway.whCd);
            ZYPEZDItemValueSetter.setValue(serTrxApiPMsg.UpdateDetailList.no(i).toLocCd, paramRWSPutAway.destInvtyLocCd);
            ZYPEZDItemValueSetter.setValue(serTrxApiPMsg.UpdateDetailList.no(i).manCratFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(serTrxApiPMsg.UpdateDetailList.no(i).toStkStsCd, paramRWSPutAway.invtyStkStsCd);
            ZYPEZDItemValueSetter.setValue(serTrxApiPMsg.UpdateDetailList.no(i).locStsCd, LOC_STS.DC_STOCK);

            i++;
        }
        serTrxApiPMsg.UpdateDetailList.setValidCount(i);

        return serTrxApiPMsg;
    }

    /**
     * getDestInvtyLocNm
     * @param glblCmpyCd String
     * @param destInvtyLocCd String
     * @return destInvtyLocNm String
     */
    private String getDestInvtyLocNm(String glblCmpyCd, String destInvtyLocCd) {
        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        paramMap.put(BIND_INVTY_LOC_CD, destInvtyLocCd);
        return (String) ssmBatchClient.queryObject("getDestInvtyLocNm", paramMap);
    }

    /**
     * getDestInvtyLocNm
     * @param glblCmpyCd String
     * @param rwsNum String
     * @param rwsDtlLineNum String
     * @return srcOrdNum String
     */
    private String getSrcOrdNum(String glblCmpyCd, String rwsNum) {
        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        paramMap.put(BIND_RWS_NUM, rwsNum);
        return (String) ssmBatchClient.queryObject("getSrcOrdNum", paramMap);
    }

    /**
     * chkAPIError
     * @param msgMap
     * @param pMsg
     * @param param
     * @param rwsPutAwayWrkT
     * @param processErrFlg
     * @return
     */
    private boolean chkAPIError(S21ApiMessageMap msgMap, EZDPMsg pMsg, NLZC206001PMsg param, RWS_PUT_AWAY_WRKTMsg rwsPutAwayWrkT, boolean processErrFlg) {

        if (!S21ApiUtil.getXxMsgIdList(pMsg).isEmpty()) {
            for (String xxMsgId : S21ApiUtil.getXxMsgIdList(pMsg)) {

                if (xxMsgId.endsWith(WARNING_CODE)) {
                    if (param.xxMsgIdList.length() > param.xxMsgIdList.getValidCount()) {
                        msgMap.addXxMsgId(xxMsgId);
                    }
                    EZDDebugOutput.println(1, PROGRAM_ID + "TABLE ERR_CD:" + xxMsgId, this);
                } else {
                    if (processErrFlg == false) {
                        ZYPEZDItemValueSetter.setValue(rwsPutAwayWrkT.procStsCd, PROC_STS.ERROR);
                        ZYPEZDItemValueSetter.setValue(rwsPutAwayWrkT.errMsgCd, xxMsgId);
                        EZDDebugOutput.println(1, PROGRAM_ID + "TABLE ERR_CD:" + xxMsgId, this);
                    }
                    processErrFlg = true;
                    if (param.xxMsgIdList.length() > param.xxMsgIdList.getValidCount()) {
                        msgMap.addXxMsgId(xxMsgId);
                    }
                    EZDDebugOutput.println(1, PROGRAM_ID + "ERR_CD:" + xxMsgId, this);
                }
            }
        }

        // Write Error Code
        if (processErrFlg) {
            S21ApiTBLAccessor.update(rwsPutAwayWrkT);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsPutAwayWrkT.getReturnCode())) {
                throw new S21AbendException(NLAM1134E, new String[] {RWS_PUT_AWAY_WRK, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, WRK_TRX_ID, SQ_ID),
                        NLXCMsgHelper.toListedString(rwsPutAwayWrkT.glblCmpyCd, rwsPutAwayWrkT.wrkTrxId, rwsPutAwayWrkT.sqId) });
            }
        }

        return processErrFlg;
    }

    /**
     * chkAPIErrorForSvcMachMstr
     * @param msgMap
     * @param pMsg
     * @param param
     * @param rwsPutAwayWrkT
     * @param processErrFlg
     * @return
     */
    private boolean chkAPIErrorForSvcMachMstr(S21ApiMessageMap msgMap, EZDPMsg pMsg, NLZC206001PMsg param, RWS_PUT_AWAY_WRKTMsg rwsPutAwayWrkT, boolean processErrFlg) {

        if (!S21ApiUtil.getXxMsgIdList(pMsg).isEmpty()) {
            for (String xxMsgId : S21ApiUtil.getXxMsgIdList(pMsg)) {

                if (xxMsgId.endsWith(WARNING_CODE)) {
                    if (processErrFlg == false) {
                        ZYPEZDItemValueSetter.setValue(rwsPutAwayWrkT.svcMachProcStsCd, SVC_MACH_PROC_STS.COMPLETED);
                    }
                    if (param.xxMsgIdList.length() > param.xxMsgIdList.getValidCount()) {
                        msgMap.addXxMsgId(xxMsgId);
                    }
                    EZDDebugOutput.println(1, PROGRAM_ID + "TABLE ERR_CD:" + xxMsgId, this);
                } else {

                    if (processErrFlg == false) {
                        ZYPEZDItemValueSetter.setValue(rwsPutAwayWrkT.svcMachProcStsCd, SVC_MACH_PROC_STS.ERROR);
                        ZYPEZDItemValueSetter.setValue(rwsPutAwayWrkT.errMsgCd, xxMsgId);
                        EZDDebugOutput.println(1, PROGRAM_ID + "TABLE ERR_CD:" + xxMsgId, this);
                    }
                    processErrFlg = true;
                    if (param.xxMsgIdList.length() > param.xxMsgIdList.getValidCount()) {
                        msgMap.addXxMsgId(xxMsgId);
                    }
                    EZDDebugOutput.println(1, PROGRAM_ID + "ERR_CD:" + xxMsgId, this);
                }
            }
        } else {
            ZYPEZDItemValueSetter.setValue(rwsPutAwayWrkT.svcMachProcStsCd, SVC_MACH_PROC_STS.COMPLETED);
        }

        S21ApiTBLAccessor.update(rwsPutAwayWrkT);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsPutAwayWrkT.getReturnCode())) {
            throw new S21AbendException(NLAM1134E, new String[] {RWS_PUT_AWAY_WRK, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, WRK_TRX_ID, SQ_ID),
                    NLXCMsgHelper.toListedString(rwsPutAwayWrkT.glblCmpyCd, rwsPutAwayWrkT.wrkTrxId, rwsPutAwayWrkT.sqId) });
        }

        return processErrFlg;
    }

    /**
     * expenseOutItem
     * QC#25198 Add.
     * @param glblCmpyCd
     * @param mdseCd
     * @return true(Expense Out )
     */
    private boolean expenseOutItem(String glblCmpyCd, String mdseCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("mdseCd", mdseCd);

        String prtItemTpCd = (String) this.ssmBatchClient.queryObject("expenseOutItem", queryParam);

        if (ZYPCommonFunc.hasValue(prtItemTpCd) && PRT_ITEM_TP.TOOL.equals(prtItemTpCd)) {
            return true;
        }
        return false;
    }

    /**
     * expenseOutLocTp
     * QC#25198 Add.
     * @param glblCmpyCd
     * @param mdseCd
     * @return
     */
    private boolean expenseOutLocTp(String glblCmpyCd, String invtyLocCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("invtyLocCd", invtyLocCd);

        String InvtylocTpCd = (String) this.ssmBatchClient.queryObject("expenseOutLocTp", queryParam);

        if (ZYPCommonFunc.hasValue(InvtylocTpCd) && LOC_TP.TECHNICIAN.equals(InvtylocTpCd)) {
            return true;
        }
        return false;
    }

    /**
     * getSrcOrdNumList
     * QC#12110 Add method
     * @param glblCmpyCd
     * @param rwsNum
     * @param rwsDtlLineNum
     * @return
     */
    private Map<String, Object> getRcvOrdNumList(String glblCmpyCd, String rwsNum, String rwsDtlLineNum) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("rwsNum", rwsNum);
        queryParam.put("rwsDtlLineNum", rwsDtlLineNum);

        return (Map<String, Object>) this.ssmBatchClient.queryObject("getRcvOrdNumList", queryParam);
    }

    // START 2019/09/25 M.Naito [QC#53695, ADD]
    /**
     * getRcvOrdNumListFromPO
     * @param glblCmpyCd
     * @param rwsNum
     * @param rwsDtlLineNum
     * @return
     */
    private Map<String, Object> getRcvOrdNumListFromPO(String glblCmpyCd, String rwsNum, String rwsDtlLineNum) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("rwsNum", rwsNum);
        queryParam.put("rwsDtlLineNum", rwsDtlLineNum);

        return (Map<String, Object>) this.ssmBatchClient.queryObject("getRcvOrdNumListFromPO", queryParam);
    }
    // END 2019/09/25 M.Naito [QC#53695, ADD]
}
