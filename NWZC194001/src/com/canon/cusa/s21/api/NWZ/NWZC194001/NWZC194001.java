/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC194001;

import static com.canon.cusa.s21.api.NWZ.NWZC194001.constant.NWZC194001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.SCHD_AGMT_ADJ_CONTRTMsg;
import business.parts.NWZC194001PMsg;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SCHD_AGMT_ADJ_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SCHD_AGMT_ADJ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/01/2022   Hitachi         D.Yoshida       Create          QC#59973
 * </pre>
 */
public class NWZC194001 extends S21ApiCommonBase {

    /** S21SsmBatchClient */
    private final S21SsmBatchClient ssmClient;

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap = null;

    /**
     * Constructor
     */
    public NWZC194001() {
        super();
        this.ssmClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute
     * @param param NWZC194001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NWZC194001PMsg param, final ONBATCH_TYPE onBatchType) {

        this.msgMap = new S21ApiMessageMap(param);

        // Service Machine Master PK
        if (!hasValue(param.svcMachMstrPk)) {
            return;
        }

        // parameter Check
        if (paramCheck(param)) {
            return;
        }

        // process by mode
        if (S21StringUtil.isEquals(MODE_RENEWAL, param.xxModeCd.getValue())) {
            doProcessRenewal(param);
        } else if (S21StringUtil.isEquals(MODE_TERMINATE, param.xxModeCd.getValue())) {
            doProcessTerminate(param);
        } else if (S21StringUtil.isEquals(MODE_SERVICE_EXCHANGE, param.xxModeCd.getValue())) {
            doProcessServiceExchange(param);
        }
    }

    /**
     * execute
     * @param params List<NWZC194001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(List<NWZC194001PMsg> params, final ONBATCH_TYPE onBatchType) {

        for (NWZC194001PMsg param : params) {
            execute(param, onBatchType);
        }
    }

    /**
     * Parameter Check
     * @param param NWZC194001PMsg
     * @return Check NG : true
     */
    private boolean paramCheck(NWZC194001PMsg param) {

        // Global Company Code
        if (!hasValue(param.glblCmpyCd)) {
            setMsgMap(NWZM0011E, null);
            return true;
        }

        // Mode
        if (!S21StringUtil.isEquals(MODE_RENEWAL, param.xxModeCd.getValue()) && !S21StringUtil.isEquals(MODE_TERMINATE, param.xxModeCd.getValue()) && !S21StringUtil.isEquals(MODE_SERVICE_EXCHANGE, param.xxModeCd.getValue())) {
            setMsgMap(NWZM0977E, null);
            return true;
        }

        // DS Contract PK
        if (!hasValue(param.dsContrPk)) {
            setMsgMap(NWZM2227E, null);
            return true;
        }

        return false;
    }

    /**
     * @param param
     */
    private void doProcessRenewal(NWZC194001PMsg param) {
        List<SCHD_AGMT_ADJ_CONTRTMsg> schdAgmtAdjContr = getTargetData(param, SCHD_AGMT_ADJ_TP.RENEWAL);
        if (schdAgmtAdjContr == null) {
            return;
        }
        if (!registSchdAgmtAdjContr(schdAgmtAdjContr)) {
            return;
        }
    }

    /**
     * @param param
     */
    private void doProcessTerminate(NWZC194001PMsg param) {
        List<SCHD_AGMT_ADJ_CONTRTMsg> schdAgmtAdjContr = getTargetData(param, SCHD_AGMT_ADJ_TP.TERMINATE);
        if (schdAgmtAdjContr == null) {
            return;
        }
        if (!registSchdAgmtAdjContr(schdAgmtAdjContr)) {
            return;
        }
    }

    /**
     * @param param
     */
    private void doProcessServiceExchange(NWZC194001PMsg param) {
        List<SCHD_AGMT_ADJ_CONTRTMsg> schdAgmtAdjContr = getTargetData(param, SCHD_AGMT_ADJ_TP.EXCHANGE);
        if (schdAgmtAdjContr == null) {
            return;
        }
        if (!registSchdAgmtAdjContr(schdAgmtAdjContr)) {
            return;
        }
    }

    /**
     * @param param
     * @param schdAgmtAdjTpCd
     * @return
     */
    @SuppressWarnings("unchecked")
    private List<SCHD_AGMT_ADJ_CONTRTMsg> getTargetData(NWZC194001PMsg param, String schdAgmtAdjTpCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", param.glblCmpyCd.getValue());
        params.put("dsContrPk", param.dsContrPk.getValue());
        params.put("svcMachMstrPk", param.svcMachMstrPk.getValue());
        params.put("svcMachTpCd", SVC_MACH_TP.MACHINE);
        params.put("schdAgmtAdjTpCd", schdAgmtAdjTpCd);
        params.put("stsUnprocessed", SCHD_AGMT_ADJ_STS.UNPROCESSED);
        return (List<SCHD_AGMT_ADJ_CONTRTMsg>) ssmClient.queryObjectList("getSchdAgmtAdjTgt", params);
    }

    /**
     * @param schdAgmtAdjContr
     * @return
     */
    private boolean registSchdAgmtAdjContr(List<SCHD_AGMT_ADJ_CONTRTMsg> schdAgmtAdjContrList) {
        for (SCHD_AGMT_ADJ_CONTRTMsg schdAgmtAdjContr : schdAgmtAdjContrList) {
            BigDecimal schdAgmtAdjContrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SCHD_AGMT_ADJ_CONTR_SQ);
            setValue(schdAgmtAdjContr.schdAgmtAdjContrPk, schdAgmtAdjContrPk);
            S21ApiTBLAccessor.insert(schdAgmtAdjContr);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(schdAgmtAdjContr.getReturnCode())) {
                setMsgMap(NSZM0626E, new String[] {"SCHD_AGMT_ADJ_CONTR" });
                return false;
            }
        }
        return true;
    }

    /**
     * @param msgId
     * @param msgPrm
     */
    private void setMsgMap(String msgId, String[] msgPrm) {
        if (msgPrm != null) {
            this.msgMap.addXxMsgIdWithPrm(msgId, msgPrm);
        } else {
            this.msgMap.addXxMsgId(msgId);
        }
        this.msgMap.flush();
    }
}
