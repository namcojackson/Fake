/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC401001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.SO_SERTMsg;
import business.parts.NLZC401001PMsg;
import business.parts.NSZC001001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC401001.constant.NLZC401001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC001001.NSZC001001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.ProcessMode;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 *<pre>
 * SO Serial Update API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/04/2015   CSAI            K.Lee           Create          Initial
 * 02/15/2018   CITS            K.Ogino         Update          QC#24084
 *</pre>
 */
public class NLZC401001 extends S21ApiCommonBase {

    /** S21SsmBatchClient */
    private final S21SsmBatchClient ssmClient;

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap = null;

    /** S21ApiMessageMap */
    private ONBATCH_TYPE onBatchType = null;

    public NLZC401001() {
        super();
        this.ssmClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute This can be called method from external class.
     * @param NLZC401001PMsg parameter
     * @param ONBATCH_TYPE onBatchType
     */
    public void execute(NLZC401001PMsg param, final ONBATCH_TYPE onBatchType) {

        this.msgMap = new S21ApiMessageMap(param);
        this.onBatchType = onBatchType;
        doProcess();
        msgMap.flush();
    }

    /**
     * execute This can be called method from external class.
     * @param List<NLZC401001PMsg> list
     * @param ONBATCH_TYPE onBatchType
     */
    public void execute(List<NLZC401001PMsg> list, final ONBATCH_TYPE onBatchType) {

        for (NLZC401001PMsg param : list) {
            execute(param, onBatchType);
        }
    }

    /**
     * doProcess This is Main process Method.
     */
    private void doProcess() {

        NLZC401001PMsg pMsg = (NLZC401001PMsg) msgMap.getPmsg();

        // #######################################
        // 1-1. Mandatory Check
        // #######################################
        if (!checkMandatory(pMsg)) {
            return;
        }

        for (int i = 0; i < pMsg.serInfo.getValidCount(); i++) {

            BigDecimal svcMachMstrPk = null;

            Map<String, Object> ibMap = getIb(pMsg.glblCmpyCd.getValue(), pMsg.serInfo.no(i).mdseCd.getValue(), pMsg.serInfo.no(i).serNum.getValue());
            if (ibMap == null) {
                setWarningMsg(pMsg, i, NLZC401001Constant.NLZM2320W, new String[]{pMsg.serInfo.no(i).mdseCd.getValue(), pMsg.serInfo.no(i).mdseCd.getValue()});
            } else {
                svcMachMstrPk = (BigDecimal) ibMap.get("SVC_MACH_MSTR_PK");

                // QC#24084
                if (ZYPConstant.FLG_ON_Y.equals((String) ibMap.get("SVC_MACH_MAINT_AVAL_FLG"))) {
                    NSZC001001PMsg nszc001001PMsg = new NSZC001001PMsg();
                    ZYPEZDItemValueSetter.setValue(nszc001001PMsg.glblCmpyCd, pMsg.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(nszc001001PMsg.slsDt, pMsg.slsDt);
                    ZYPEZDItemValueSetter.setValue(nszc001001PMsg.xxModeCd, ProcessMode.ALLOCATION_ON.code);
                    ZYPEZDItemValueSetter.setValue(nszc001001PMsg.svcMachMstrPk, svcMachMstrPk);
                    ZYPEZDItemValueSetter.setValue(nszc001001PMsg.trxHdrNum, pMsg.serInfo.no(i).trxHdrNum);
                    ZYPEZDItemValueSetter.setValue(nszc001001PMsg.trxLineNum, pMsg.serInfo.no(i).trxLineNum);
                    ZYPEZDItemValueSetter.setValue(nszc001001PMsg.trxLineSubNum, pMsg.serInfo.no(i).trxLineSubNum);
                    NSZC001001 nszc001001 = new NSZC001001();
                    nszc001001.execute(nszc001001PMsg, onBatchType);
                    List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(nszc001001PMsg);
                    if (msgList.size() > 0) {
                        for (S21ApiMessage msg : msgList) {
                            String msgId = msg.getXxMsgid();
                            String[] prm = msg.getXxMsgPrmArray();
                            logOutput(msgId, prm);
                            if (msgId.endsWith("E")) {
                                setWarningMsg(pMsg, i, NLZC401001Constant.NLZM2322W, new String[]{pMsg.serInfo.no(i).mdseCd.getValue(), pMsg.serInfo.no(i).mdseCd.getValue()});
                                break;
                            }
                        }
                    }
                }
            }

            BigDecimal soSerPk = getSoSer(pMsg.glblCmpyCd.getValue(), pMsg.serInfo.no(i).serNum.getValue(), pMsg.serInfo.no(i).soNum.getValue(), pMsg.serInfo.no(i).soSlpNum.getValue());
            if (ZYPCommonFunc.hasValue(soSerPk)) {
                SO_SERTMsg soSerInMsg = new SO_SERTMsg();
                ZYPEZDItemValueSetter.setValue(soSerInMsg.glblCmpyCd, pMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(soSerInMsg.soSerPk, soSerPk);
                SO_SERTMsg soSerUpMsg = (SO_SERTMsg) EZDTBLAccessor.findByKey(soSerInMsg);
                if (soSerUpMsg != null) {
                    if (pMsg.serInfo.no(i).xxProcTpCd.getValue().equals(NLZC401001Constant.MODE_PICKUP_SERIAL)) {
                        ZYPEZDItemValueSetter.setValue(soSerUpMsg.whPickFlg, ZYPConstant.FLG_ON_Y);
                    } else  if (pMsg.serInfo.no(i).xxProcTpCd.getValue().equals(NLZC401001Constant.MODE_PICKUP_CANCEL)) {
                        ZYPEZDItemValueSetter.setValue(soSerUpMsg.whPickFlg, ZYPConstant.FLG_OFF_N);
                    }
                    EZDTBLAccessor.update(soSerUpMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(soSerUpMsg.getReturnCode())) {
                        setErrMsg(pMsg, i, NLZC401001Constant.NLAM1295E, new String[]{"SO_SER"});
                    }
                }
            } else {
                SO_SERTMsg soSerUpMsg = new SO_SERTMsg();
                ZYPEZDItemValueSetter.setValue(soSerUpMsg.glblCmpyCd, pMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(soSerUpMsg.soSerPk, ZYPOracleSeqAccessor.getNumberBigDecimal("SO_SER_SQ"));

                ZYPEZDItemValueSetter.setValue(soSerUpMsg.soNum, pMsg.serInfo.no(i).soNum);
                ZYPEZDItemValueSetter.setValue(soSerUpMsg.soSlpNum, pMsg.serInfo.no(i).soSlpNum);
                ZYPEZDItemValueSetter.setValue(soSerUpMsg.trxHdrNum, pMsg.serInfo.no(i).trxHdrNum);
                ZYPEZDItemValueSetter.setValue(soSerUpMsg.trxLineNum, pMsg.serInfo.no(i).trxLineNum);
                ZYPEZDItemValueSetter.setValue(soSerUpMsg.trxLineSubNum, pMsg.serInfo.no(i).trxLineSubNum);
                ZYPEZDItemValueSetter.setValue(soSerUpMsg.trxSrcTpCd, pMsg.serInfo.no(i).trxSrcTpCd);
                ZYPEZDItemValueSetter.setValue(soSerUpMsg.serNum, pMsg.serInfo.no(i).serNum);
                ZYPEZDItemValueSetter.setValue(soSerUpMsg.mdseCd, pMsg.serInfo.no(i).mdseCd);

                if (pMsg.serInfo.no(i).xxProcTpCd.getValue().equals(NLZC401001Constant.MODE_ORDER_SERIAL)) {
                    ZYPEZDItemValueSetter.setValue(soSerUpMsg.ordAsgFlg, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(soSerUpMsg.whPickFlg, ZYPConstant.FLG_OFF_N);
                } else {
                    ZYPEZDItemValueSetter.setValue(soSerUpMsg.ordAsgFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(soSerUpMsg.whPickFlg, ZYPConstant.FLG_ON_Y);
                }
                ZYPEZDItemValueSetter.setValue(soSerUpMsg.svcMachMstrPk, svcMachMstrPk);
                EZDTBLAccessor.create(soSerUpMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(soSerUpMsg.getReturnCode())) {
                    setErrMsg(pMsg, i, NLZC401001Constant.NLAM1295E, new String[]{"SO_SER"});
                }
            }
        }
    }

    /**
     * setHeaderErrMsg
     * @param NLZC401001PMsg pMsg
     * @param String msgId
     */
    private void setErrMsg(NLZC401001PMsg pMsg, int idx, String msgId, String[] errMsgPrm) {
        if (errMsgPrm == null) {
            msgMap.addXxMsgId(msgId);
        } else {
            msgMap.addXxMsgIdWithPrm(msgId, new String[] {pMsg.serInfo.no(idx).mdseCd.getValue(), pMsg.serInfo.no(idx).serNum.getValue() });
        }
        ZYPEZDItemValueSetter.setValue(pMsg.serInfo.no(idx).xxRsltStsCd, NLZC401001Constant.RETURN_CODE_ERROR);
        msgMap.flush();
    }

    /**
     * setHeaderErrMsg
     * @param NLZC401001PMsg pMsg
     * @param String msgId
     */
    private void setWarningMsg(NLZC401001PMsg pMsg, int idx, String msgId, String[] errMsgPrm) {
        if (errMsgPrm == null) {
            msgMap.addXxMsgId(msgId);
        } else {
            msgMap.addXxMsgIdWithPrm(msgId, new String[] {pMsg.serInfo.no(idx).mdseCd.getValue(), pMsg.serInfo.no(idx).serNum.getValue() });
        }
        ZYPEZDItemValueSetter.setValue(pMsg.serInfo.no(idx).xxRsltStsCd, NLZC401001Constant.RETURN_CODE_WARNING);
        msgMap.flush();
    }

    /**
     * www. Output Log and Stock Error Message ID for Mail
     * @param keyStr String[]
     * @param xxMsgId String
     * @param msgConvStr String[]
     */
    private void logOutput(String xxMsgId, String[] msgPrm) {
        String msg;
        if (msgPrm == null) {
            msg = S21MessageFunc.clspGetMessage(xxMsgId);
        } else {
            msg = S21MessageFunc.clspGetMessage(xxMsgId, msgPrm);
        }
        S21InfoLogOutput.println(msg);
    }

    /**
     * checkMandatory
     * @return boolean Normal:true, Error:false
     */
    private boolean checkMandatory(NLZC401001PMsg pMsg) {

        if (!hasValue(pMsg.glblCmpyCd)) {
            // Global Company Code is mandatory.
            setErrMsg(pMsg, 0, NLZC401001Constant.NLGM0012E, null);
            return false;
        }

        for (int i = 0; i < pMsg.serInfo.getValidCount(); i++) {
            if (!hasValue(pMsg.serInfo.no(i).xxProcTpCd)) {
                // Process mode is not set.
                setErrMsg(pMsg, i, NLZC401001Constant.NLZM2323E, new String[]{"Process Mode", String.valueOf(i)});
                return false;
            }

            if (!hasValue(pMsg.serInfo.no(i).mdseCd)) {
                // Item Code is not set.
                setErrMsg(pMsg, i, NLZC401001Constant.NLZM2323E, new String[]{"Item Code", String.valueOf(i)});
                return false;
            }

            if (!hasValue(pMsg.serInfo.no(i).serNum)) {
                // Serial Number is not set.
                setErrMsg(pMsg, i, NLZC401001Constant.NLZM2323E, new String[]{"Serial Number", String.valueOf(i)});
                return false;
            }

            if (!hasValue(pMsg.serInfo.no(i).soNum)) {
                // SO# is not set.
                setErrMsg(pMsg, i, NLZC401001Constant.NLZM2323E, new String[]{"SO#", String.valueOf(i)});
                return false;
            }

            if (!hasValue(pMsg.serInfo.no(i).soSlpNum)) {
                // SO Slip# is not set.
                setErrMsg(pMsg, i, NLZC401001Constant.NLZM2323E, new String[]{"SO Slip#", String.valueOf(i)});
                return false;
            }

            if (!hasValue(pMsg.serInfo.no(i).trxHdrNum)) {
                // Trx Hdr Num is not set.
                setErrMsg(pMsg, i, NLZC401001Constant.NLZM2323E, new String[]{"Trx Hdr Num", String.valueOf(i)});
                return false;
            }

            if (!hasValue(pMsg.serInfo.no(i).trxLineNum)) {
                // Trx Line Num is not set.
                setErrMsg(pMsg, i, NLZC401001Constant.NLZM2323E, new String[]{"Trx Line Num", String.valueOf(i)});
                return false;
            }
        }
        return true;
    }

    /**
     * getIB
     * @param pMsg NLZC401001PMsg
     * @return Map<String, Object>
     */
    private Map<String, Object> getIb(String glblCmpyCd, String mdseCd, String serNum) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd);
        ssmParam.put("serNum", serNum);

        return (Map<String, Object>) ssmClient.queryObject("getIb", ssmParam);
    }

    /**
     * getIB
     * @param pMsg NLZC401001PMsg
     * @return Map<String, Object>
     */
    private BigDecimal getSoSer(String glblCmpyCd, String serNum, String soNum, String soSlpNum) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("serNum", serNum);
        ssmParam.put("soNum", soNum);
        ssmParam.put("soSlpNum", soSlpNum);

        return (BigDecimal) ssmClient.queryObject("getSoSer", ssmParam);
    }
}
