/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC112001;

import static com.canon.cusa.s21.api.NSZ.NSZC112001.constant.NSZC112001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDPItem;
import parts.dbcommon.EZDTBLAccessor;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MEMOTMsg;
import business.parts.NSZC001001PMsg;
import business.parts.NSZC112001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC001001.NSZC001001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.ProcessMode;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_USG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Install Confirmation from eCarrier API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/02/10   Hitachi         A.Kohinata      Create          QC#16993
 *</pre>
 */
public class NSZC112001 extends S21ApiCommonBase {

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Online Batch Type */
    private ONBATCH_TYPE onBatTp = null;

    /**
     * Constructor
     */
    public NSZC112001() {
        super();
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute
     * @param pMsgList List<NSZC112001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(List<NSZC112001PMsg> pMsgList, final ONBATCH_TYPE onBatchType) {
        for (NSZC112001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatchType);
        }
    }

    /**
     * execute Install Confirmation from eCarrier API.
     * @param param NSZC112001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NSZC112001PMsg param, final ONBATCH_TYPE onBatchType) {
        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
        this.onBatTp = onBatchType;

        if (checkParameter(msgMap)) {
            execInstall(msgMap);
        }

        msgMap.flush();
    }

    private boolean checkParameter(S21ApiMessageMap msgMap) {
        NSZC112001PMsg pMsg = (NSZC112001PMsg) msgMap.getPmsg();

        mandatoryCheck(msgMap, pMsg.glblCmpyCd, NSZM0001E);
        mandatoryCheck(msgMap, pMsg.slsDt, NSZM0002E);
        mandatoryCheck(msgMap, pMsg.svcMachMstrPk, NSZM0074E);
        mandatoryCheck(msgMap, pMsg.istlDt, NSZM0029E);

        if (msgMap.getMsgMgr().isXxMsgId()) {
            return false;
        }
        return true;
    }

    private void mandatoryCheck(S21ApiMessageMap msgMap, EZDPItem targetItem, String messageId) {
        if (!hasValue(targetItem)) {
            msgMap.addXxMsgId(messageId);
        }
    }

    private void execInstall(S21ApiMessageMap msgMap) {
        NSZC112001PMsg pMsg = (NSZC112001PMsg) msgMap.getPmsg();

        SVC_MACH_MSTRTMsg svcMachMstrTMsg = getSvcMachMstr(pMsg);
        if (svcMachMstrTMsg == null) {
            msgMap.addXxMsgId(NSZM0006E);
            return;
        }

        NSZC001001PMsg apiPMsg = new NSZC001001PMsg();
        setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(apiPMsg.slsDt, pMsg.slsDt);
        setValue(apiPMsg.xxModeCd, ProcessMode.INSTALLATION.code);
        setValue(apiPMsg.svcConfigMstrPk, svcMachMstrTMsg.svcConfigMstrPk);
        setValue(apiPMsg.svcMachMstrPk, pMsg.svcMachMstrPk);
        setValue(apiPMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.INSTALLED);
        setValue(apiPMsg.stkStsCd, svcMachMstrTMsg.stkStsCd);
        setValue(apiPMsg.istlDt, pMsg.istlDt);
        setValue(apiPMsg.iwrCondCd, pMsg.iwrCondCd);
        setValue(apiPMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.AT_CUSTOMER);
        setValue(apiPMsg.svcMachMstrLocStsCd, svcMachMstrTMsg.svcMachMstrLocStsCd);

        new NSZC001001().execute(apiPMsg, onBatTp);
        if (S21ApiUtil.isXxMsgId(apiPMsg)) {
            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(apiPMsg);
            for (String xxMsgId : xxMsgIdList) {
                msgMap.addXxMsgId(xxMsgId);
            }
            return;
        }

        if (hasValue(pMsg.svcCmntTxt)) {
            insertSvcMemo(msgMap);
        }
    }

    private SVC_MACH_MSTRTMsg getSvcMachMstr(NSZC112001PMsg pMsg) {
        SVC_MACH_MSTRTMsg paramTMsg = new SVC_MACH_MSTRTMsg();
        setValue(paramTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        setValue(paramTMsg.svcMachMstrPk, pMsg.svcMachMstrPk.getValue());
        return (SVC_MACH_MSTRTMsg) S21ApiTBLAccessor.findByKey(paramTMsg);
    }

    private void insertSvcMemo(S21ApiMessageMap msgMap) {
        NSZC112001PMsg pMsg = (NSZC112001PMsg) msgMap.getPmsg();

        BigDecimal svcMachMstrTrkPk = getSvcMachMstrTrkPk(pMsg);
        if (svcMachMstrTrkPk == null) {
            msgMap.addXxMsgId(NSZM0725E);
            return;
        }

        SVC_MEMOTMsg svcMemoTMsg = new SVC_MEMOTMsg();
        setValue(svcMemoTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(svcMemoTMsg.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MEMO_SQ));
        setValue(svcMemoTMsg.svcMemoCatgCd, SVC_MEMO_CATG.MACHINE_MEMO);
        setValue(svcMemoTMsg.svcMemoTpCd, SVC_MEMO_TP.MACHINE);
        setValue(svcMemoTMsg.svcCmntTxt, pMsg.svcCmntTxt);
        setValue(svcMemoTMsg.svcMachMstrPk, pMsg.svcMachMstrPk);
        setValue(svcMemoTMsg.svcMemoRsnCd, SVC_MEMO_RSN.MACHINE_STATUS_CHANGE);
        setValue(svcMemoTMsg.svcMemoTrxNum, svcMachMstrTrkPk.toString());
        setValue(svcMemoTMsg.svcMemoTrxNm, SVC_MEMO_TRX_NM);

        S21ApiTBLAccessor.create(svcMemoTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(svcMemoTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0475E);
        }
    }

    private BigDecimal getSvcMachMstrTrkPk(NSZC112001PMsg pMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("svcMachMstrPk", pMsg.svcMachMstrPk.getValue());
        param.put("trxRgtnDt", pMsg.slsDt.getValue());
        param.put("updFldTxt", UPD_FLD_TXT);
        param.put("newValTxt", SVC_MACH_MSTR_STS.INSTALLED);
        return (BigDecimal) ssmBatchClient.queryObject("getSvcMachMstrTrkPk", param);
    }
}
