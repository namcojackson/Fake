/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC073001;

import static com.canon.cusa.s21.api.NSZ.NSZC073001.constant.NSZC073001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsgArray;
import business.db.SVC_PBLM_CRCT_TPTMsg;
import business.db.SVC_PBLM_CRCT_TPTMsgArray;
import business.db.SVC_PBLM_LOC_TPTMsg;
import business.db.SVC_PBLM_LOC_TPTMsgArray;
import business.db.SVC_PBLM_RSN_TPTMsg;
import business.db.SVC_PBLM_RSN_TPTMsgArray;
import business.db.SVC_PBLM_TPTMsg;
import business.db.SVC_PBLM_TPTMsgArray;
import business.db.SVC_TASKTMsg;
import business.db.SVC_TASKTMsgArray;
import business.db.SVC_TSS_ESCLTMsg;
import business.db.SVC_TSS_ESCL_DTLTMsg;

import business.parts.NSZC073001PMsg;
import business.parts.NSZC073001_xxUsedPartsListPMsg;
import business.parts.NSZC073001_xxUsedPartsListPMsgArray;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDPItem;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * TTS Escalation API.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/18/2015   Hitachi         Y.Tsuchimoto    Create          NA#TTS Escalation API
 * 2015/12/24   Hitachi         K.Yamada        Update          CSA QC#2359
 * </pre>
 */
public class NSZC073001 extends S21ApiCommonBase {

    /** ssm batch client */
    private S21SsmBatchClient ssmBatchClient = null;

    /**
     * Constructor
     */
    public NSZC073001() {
        super();
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute
     * @param param NSZC073001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NSZC073001PMsg param, final ONBATCH_TYPE onBatchType) {

        if (!ZYPCommonFunc.hasValue(param.svcTssEsclDt)) {
            setValue(param.svcTssEsclDt, ZYPDateUtil.getCurrentSystemTime(ZYPDateUtil.TYPE_YYYYMMDD));
        }

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        if (!checkParameter(msgMap)) {
            msgMap.flush();
            return;
        }

        if (!checkMaster(msgMap)) {
            msgMap.flush();
            return;
        }

        insertTssEscalation(msgMap);

        // TODO:Insert Attachment data.

        msgMap.flush();
    }

    private boolean checkParameter(S21ApiMessageMap msgMap) {
        NSZC073001PMsg pMsg = (NSZC073001PMsg) msgMap.getPmsg();

        // mod start 2015/12/24 CSA Defect#2359
        //mandatoryCheck(msgMap, pMsg.glblCmpyCd, NZZM0012E, PARAM_GLBL_CMPY_CD);
        if (!hasValue(pMsg.glblCmpyCd)) {
            setValue(pMsg.glblCmpyCd, DEFAULT_GLBL_CMPY_CD);
        }
        if (!hasValue(pMsg.slsDt)) {
            setValue(pMsg.slsDt, (String) ZYPDateUtil.getSalesDate(pMsg.glblCmpyCd.getValue()));
        }
        //mandatoryCheck(msgMap, pMsg.svcTaskNum, NZZM0012E, PARAM_SVC_TASK_NUM);
        //mandatoryCheck(msgMap, pMsg.esclTpTxt, NZZM0012E, PARAM_ESCL_TP_TXT);
        //mandatoryCheck(msgMap, pMsg.dsAcctNm, NZZM0012E, PARAM_DS_ACCT_NM);
        //mandatoryCheck(msgMap, pMsg.firstLineAddr, NZZM0012E, PARAM_FIRST_LINE_ADDR);
        //mandatoryCheck(msgMap, pMsg.mdlNm, NZZM0012E, PARAM_MDL_NM);
        //mandatoryCheck(msgMap, pMsg.serNum, NZZM0012E, PARAM_SER_NUM);
        //mandatoryCheck(msgMap, pMsg.techNm, NZZM0012E, PARAM_TECH_NM);
        //mandatoryCheck(msgMap, pMsg.cellPhoNum, NZZM0012E, PARAM_CELL_PHO_NUM);
        //mandatoryCheck(msgMap, pMsg.svcPblmTpCd, NZZM0012E, PARAM_SVC_PBLM_TP_CD);
        //mandatoryCheck(msgMap, pMsg.svcPblmLocTpCd, NZZM0012E, PARAM_SVC_PBLM_LOC_TP_CD);
        //mandatoryCheck(msgMap, pMsg.svcPblmCrctTpCd, NZZM0012E, PARAM_SVC_PBLM_CRCT_TP_CD);
        //mandatoryCheck(msgMap, pMsg.svcPblmRsnTpCd, NZZM0012E, PARAM_SVC_PBLM_RSN_TP_CD);
        //mandatoryCheck(msgMap, pMsg.svcCmntTxt, NZZM0012E, PARAM_SVC_CMNT_TXT);
        //mandatoryCheck(msgMap, pMsg.techSprtGrpEmlAddr, NZZM0012E, PARAM_TECH_SPRT_GRP_EML_ADDR);

        //if (msgMap.getMsgMgr().isXxMsgId()) {
        //    return false;
        //}
        // mod end 2015/12/24 CSA Defect#2359
        return true;
    }

    private void mandatoryCheck(S21ApiMessageMap msgMap, EZDPItem targetItem, String messageId, String itemName) {
        if (!hasValue(targetItem)) {
            msgMap.addXxMsgIdWithPrm(messageId, new String[] {itemName });
        }
    }

    private boolean checkMaster(S21ApiMessageMap msgMap) {
        // Serial# Check
        if (!isExistSerialNumber(msgMap)) {
            return false;
        }

        // Problem Check
        if (!isExistProblem(msgMap)) {
            return false;
        }

        // Location Check
        if (!isExistLocation(msgMap)) {
            return false;
        }

        // Correction
        if (!isExistCorrection(msgMap)) {
            return false;
        }

        // Reason
        if (!isExistReason(msgMap)) {
            return false;
        }

        // Task#
        if (!isExistTaskNumber(msgMap)) {
            return false;
        }

        return true;
    }

    private boolean isExistSerialNumber(S21ApiMessageMap msgMap) {
        NSZC073001PMsg pMsg = (NSZC073001PMsg) msgMap.getPmsg();

        // add start 2015/12/24 CSA Defect#2359
        if (!hasValue(pMsg.serNum)) {
            return true;
        }
        // add end 2015/12/24 CSA Defect#2359
        SVC_MACH_MSTRTMsg tMsg = getSvcMachMstr(pMsg.glblCmpyCd.getValue(), pMsg.serNum.getValue());
        if (tMsg == null) {
            msgMap.addXxMsgIdWithPrm(NSZM0628E, new String[] {NSZM0628E_PARAM_SERIAL });
            return false;
        }

        return true;
    }

    private SVC_MACH_MSTRTMsg getSvcMachMstr(String glblCmpyCd, String serNum) {
        SVC_MACH_MSTRTMsg param = new SVC_MACH_MSTRTMsg();
        param.setSQLID("002");
        param.setConditionValue("glblCmpyCd01", glblCmpyCd);
        param.setConditionValue("serNum01", serNum);

        SVC_MACH_MSTRTMsgArray result = (SVC_MACH_MSTRTMsgArray) S21ApiTBLAccessor.findByCondition(param);

        if (result.getValidCount() > 0) {
            return (SVC_MACH_MSTRTMsg) result.get(0);
        }
        return null;
    }

    private boolean isExistProblem(S21ApiMessageMap msgMap) {
        NSZC073001PMsg pMsg = (NSZC073001PMsg) msgMap.getPmsg();

        // mod start 2015/12/24 CSA Defect#2359
        if (!hasValue(pMsg.xtrnlPblmTpRefTxt)) {
            return true;
        }
        String pblmTpCd = getSvcPblmTpCd(pMsg.glblCmpyCd.getValue(), pMsg.xtrnlPblmTpRefTxt.getValue());
        if (!hasValue(pblmTpCd)) {
            msgMap.addXxMsgIdWithPrm(NSZM0628E, new String[] {NSZM0628E_PARAM_PROBLEM });
            return false;
        }

        //SVC_PBLM_TPTMsg tMsg = getSvcPblmTp(pMsg.glblCmpyCd.getValue(), pMsg.svcPblmTpCd.getValue());
        //if (tMsg == null) {
        //    msgMap.addXxMsgIdWithPrm(NSZM0628E, new String[] {NSZM0628E_PARAM_PROBLEM });
        //    return false;
        //}
        // mod end 2015/12/24 CSA Defect#2359

        return true;
    }

    private SVC_PBLM_TPTMsg getSvcPblmTp(String glblCmpyCd, String svcPblmTpCd) {
        SVC_PBLM_TPTMsg param = new SVC_PBLM_TPTMsg();
        param.setSQLID("004");
        param.setConditionValue("glblCmpyCd01", glblCmpyCd);
        param.setConditionValue("svcPblmTpCd01", svcPblmTpCd);

        SVC_PBLM_TPTMsgArray result = (SVC_PBLM_TPTMsgArray) S21ApiTBLAccessor.findByCondition(param);

        if (result.getValidCount() > 0) {
            return (SVC_PBLM_TPTMsg) result.get(0);
        }
        return null;
    }

    private boolean isExistLocation(S21ApiMessageMap msgMap) {
        NSZC073001PMsg pMsg = (NSZC073001PMsg) msgMap.getPmsg();

        // mod start 2015/12/24 CSA Defect#2359
        if (!hasValue(pMsg.xtrnlPblmLocTpRefTxt)) {
            return true;
        }
        String pblmLocTpCd = getSvcPblmLocTpCd(pMsg.glblCmpyCd.getValue(), pMsg.xtrnlPblmLocTpRefTxt.getValue());
        if (!hasValue(pblmLocTpCd)) {
            msgMap.addXxMsgIdWithPrm(NSZM0628E, new String[] {NSZM0628E_PARAM_LOCATION });
            return false;
        }

        //SVC_PBLM_LOC_TPTMsg tMsg = getSvcPblmLocTp(pMsg.glblCmpyCd.getValue(), pMsg.svcPblmLocTpCd.getValue());
        //if (tMsg == null) {
        //    msgMap.addXxMsgIdWithPrm(NSZM0628E, new String[] {NSZM0628E_PARAM_LOCATION });
        //    return false;
        //}
        // mod end 2015/12/24 CSA Defect#2359

        return true;
    }

    private SVC_PBLM_LOC_TPTMsg getSvcPblmLocTp(String glblCmpyCd, String svcPblmLocTpCd) {
        SVC_PBLM_LOC_TPTMsg param = new SVC_PBLM_LOC_TPTMsg();
        param.setSQLID("004");
        param.setConditionValue("glblCmpyCd01", glblCmpyCd);
        param.setConditionValue("svcPblmLocTpCd01", svcPblmLocTpCd);

        SVC_PBLM_LOC_TPTMsgArray result = (SVC_PBLM_LOC_TPTMsgArray) S21ApiTBLAccessor.findByCondition(param);

        if (result.getValidCount() > 0) {
            return (SVC_PBLM_LOC_TPTMsg) result.get(0);
        }
        return null;
    }

    private boolean isExistCorrection(S21ApiMessageMap msgMap) {
        NSZC073001PMsg pMsg = (NSZC073001PMsg) msgMap.getPmsg();

        // mod start 2015/12/24 CSA Defect#2359
        if (!hasValue(pMsg.xtrnlPblmCrctTpRefTxt)) {
            return true;
        }
        String pblmCrctTpCd = getSvcPblmCrctTpCd(pMsg.glblCmpyCd.getValue(), pMsg.xtrnlPblmCrctTpRefTxt.getValue());
        if (!hasValue(pblmCrctTpCd)) {
            msgMap.addXxMsgIdWithPrm(NSZM0628E, new String[] {NSZM0628E_PARAM_CORRECTION });
            return false;
        }

        //SVC_PBLM_CRCT_TPTMsg tMsg = getSvcPblmCrctTp(pMsg.glblCmpyCd.getValue(), pMsg.svcPblmCrctTpCd.getValue());
        //if (tMsg == null) {
        //    msgMap.addXxMsgIdWithPrm(NSZM0628E, new String[] {NSZM0628E_PARAM_CORRECTION });
        //    return false;
        //}
        // mod end 2015/12/24 CSA Defect#2359

        return true;
    }

    private SVC_PBLM_CRCT_TPTMsg getSvcPblmCrctTp(String glblCmpyCd, String svcPblmCrctTpCd) {
        SVC_PBLM_CRCT_TPTMsg param = new SVC_PBLM_CRCT_TPTMsg();
        param.setSQLID("004");
        param.setConditionValue("glblCmpyCd01", glblCmpyCd);
        param.setConditionValue("svcPblmCrctTpCd01", svcPblmCrctTpCd);

        SVC_PBLM_CRCT_TPTMsgArray result = (SVC_PBLM_CRCT_TPTMsgArray) S21ApiTBLAccessor.findByCondition(param);

        if (result.getValidCount() > 0) {
            return (SVC_PBLM_CRCT_TPTMsg) result.get(0);
        }
        return null;
    }

    private boolean isExistReason(S21ApiMessageMap msgMap) {
        NSZC073001PMsg pMsg = (NSZC073001PMsg) msgMap.getPmsg();

        // mod start 2015/12/24 CSA Defect#2359
        if (!hasValue(pMsg.xtrnlPblmRsnTpRefTxt)) {
            return true;
        }
        String pblmRsnTpCd = getSvcPblmRsnTpCd(pMsg.glblCmpyCd.getValue(), pMsg.xtrnlPblmRsnTpRefTxt.getValue());
        if (!hasValue(pblmRsnTpCd)) {
            msgMap.addXxMsgIdWithPrm(NSZM0628E, new String[] {NSZM0628E_PARAM_REASON });
            return false;
        }

        //SVC_PBLM_RSN_TPTMsg tMsg = getSvcPblmRsnTp(pMsg.glblCmpyCd.getValue(), pMsg.svcPblmRsnTpCd.getValue());
        //if (tMsg == null) {
        //    msgMap.addXxMsgIdWithPrm(NSZM0628E, new String[] {NSZM0628E_PARAM_REASON });
        //    return false;
        //
        // mod end 2015/12/24 CSA Defect#2359}

        return true;
    }

    private SVC_PBLM_RSN_TPTMsg getSvcPblmRsnTp(String glblCmpyCd, String svcPblmRsnTpCd) {
        SVC_PBLM_RSN_TPTMsg param = new SVC_PBLM_RSN_TPTMsg();
        param.setSQLID("004");
        param.setConditionValue("glblCmpyCd01", glblCmpyCd);
        param.setConditionValue("svcPblmRsnTpCd01", svcPblmRsnTpCd);

        SVC_PBLM_RSN_TPTMsgArray result = (SVC_PBLM_RSN_TPTMsgArray) S21ApiTBLAccessor.findByCondition(param);

        if (result.getValidCount() > 0) {
            return (SVC_PBLM_RSN_TPTMsg) result.get(0);
        }
        return null;
    }

    private boolean isExistTaskNumber(S21ApiMessageMap msgMap) {
        NSZC073001PMsg pMsg = (NSZC073001PMsg) msgMap.getPmsg();

        // add start 2015/12/24 CSA Defect#2359
        if (!hasValue(pMsg.svcTaskNum)) {
            return true;
        }
        // add end 2015/12/24 CSA Defect#2359

        SVC_TASKTMsg tMsg = getSvcTask(pMsg.glblCmpyCd.getValue(), pMsg.svcTaskNum.getValue());
        if (tMsg == null) {
            msgMap.addXxMsgIdWithPrm(NSZM0628E, new String[] {NSZM0628E_PARAM_TASK });
            return false;
        }

        return true;
    }

    private SVC_TASKTMsg getSvcTask(String glblCmpyCd, String svcTaskNum) {
        SVC_TASKTMsg param = new SVC_TASKTMsg();
        param.setSQLID("004");
        param.setConditionValue("glblCmpyCd01", glblCmpyCd);
        param.setConditionValue("svcTaskNum01", svcTaskNum);

        SVC_TASKTMsgArray result = (SVC_TASKTMsgArray) S21ApiTBLAccessor.findByCondition(param);

        if (result.getValidCount() > 0) {
            return (SVC_TASKTMsg) result.get(0);
        }
        return null;
    }

    private void insertTssEscalation(S21ApiMessageMap msgMap) {
        NSZC073001PMsg pMsg = (NSZC073001PMsg) msgMap.getPmsg();

        // Insert SVC_TSS_ESCL
        BigDecimal seqSvcTssEsclPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_TSS_ESCL_SQ);

        // add start 2015/12/24 CSA Defect#2359
        String pblmTpCd = getSvcPblmTpCd(pMsg.glblCmpyCd.getValue(), pMsg.xtrnlPblmTpRefTxt.getValue());
        String pblmLocTpCd = getSvcPblmLocTpCd(pMsg.glblCmpyCd.getValue(), pMsg.xtrnlPblmLocTpRefTxt.getValue());
        String pblmCrctTpCd = getSvcPblmCrctTpCd(pMsg.glblCmpyCd.getValue(), pMsg.xtrnlPblmCrctTpRefTxt.getValue());
        String pblmRsnTpCd = getSvcPblmRsnTpCd(pMsg.glblCmpyCd.getValue(), pMsg.xtrnlPblmRsnTpRefTxt.getValue());
        // add end 2015/12/24 CSA Defect#2359

        SVC_TSS_ESCLTMsg svcTssEsclTMsg = new SVC_TSS_ESCLTMsg();
        setValue(svcTssEsclTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        setValue(svcTssEsclTMsg.svcTssEsclPk, seqSvcTssEsclPk);
        setValue(svcTssEsclTMsg.svcTssEsclDt, pMsg.svcTssEsclDt.getValue());
        setValue(svcTssEsclTMsg.svcTaskNum, pMsg.svcTaskNum.getValue());
        setValue(svcTssEsclTMsg.esclTpTxt, pMsg.esclTpTxt.getValue());
        setValue(svcTssEsclTMsg.dsAcctNm, pMsg.dsAcctNm.getValue());
        setValue(svcTssEsclTMsg.firstLineAddr, pMsg.firstLineAddr.getValue());
        setValue(svcTssEsclTMsg.mdlNm, pMsg.mdlNm.getValue());
        setValue(svcTssEsclTMsg.serNum, pMsg.serNum.getValue());
        setValue(svcTssEsclTMsg.techNm, pMsg.techNm.getValue());
        setValue(svcTssEsclTMsg.cellPhoNum, pMsg.cellPhoNum.getValue());
        // mod start 2015/12/24 CSA Defect#2359
        //setValue(svcTssEsclTMsg.svcPblmTpCd, pMsg.svcPblmTpCd.getValue());
        //setValue(svcTssEsclTMsg.svcPblmLocTpCd, pMsg.svcPblmLocTpCd.getValue());
        //setValue(svcTssEsclTMsg.svcPblmCrctTpCd, pMsg.svcPblmCrctTpCd.getValue());
        //setValue(svcTssEsclTMsg.svcPblmRsnTpCd, pMsg.svcPblmRsnTpCd.getValue());
        setValue(svcTssEsclTMsg.svcPblmTpCd, pblmTpCd);
        setValue(svcTssEsclTMsg.svcPblmLocTpCd, pblmLocTpCd);
        setValue(svcTssEsclTMsg.svcPblmCrctTpCd, pblmCrctTpCd);
        setValue(svcTssEsclTMsg.svcPblmRsnTpCd, pblmRsnTpCd);
        // mod end 2015/12/24 CSA Defect#2359
        setValue(svcTssEsclTMsg.helpDeskTktNum, pMsg.helpDeskTktNum.getValue());
        setValue(svcTssEsclTMsg.svcCmntTxt, pMsg.svcCmntTxt.getValue());
        setValue(svcTssEsclTMsg.attDataDescTxt, pMsg.attDataDescTxt.getValue());
        setValue(svcTssEsclTMsg.techSprtGrpEmlAddr, pMsg.techSprtGrpEmlAddr.getValue());
        setValue(svcTssEsclTMsg.svcTssEsclProcFlg, ZYPConstant.FLG_OFF_N);

        S21ApiTBLAccessor.create(svcTssEsclTMsg);
        if (!RETURN_CD_NORMAL.equals(svcTssEsclTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSZM0626E, new String[] {svcTssEsclTMsg.getTableName() });
        }
        // Insert SVC_TSS_ESCL_DTL
        NSZC073001_xxUsedPartsListPMsgArray mdseCdList = pMsg.xxUsedPartsList;
        for (int i = 0; i < mdseCdList.getValidCount(); i++) {
            NSZC073001_xxUsedPartsListPMsg usedPartsMsg = (NSZC073001_xxUsedPartsListPMsg) mdseCdList.get(i);

            BigDecimal seqSvcTssEsclDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_TSS_ESCL_DTL_SQ);

            SVC_TSS_ESCL_DTLTMsg svcTssEsclDtlTMsg = new SVC_TSS_ESCL_DTLTMsg();
            setValue(svcTssEsclDtlTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
            setValue(svcTssEsclDtlTMsg.svcTssEsclDtlPk, seqSvcTssEsclDtlPk);
            setValue(svcTssEsclDtlTMsg.svcTssEsclPk, seqSvcTssEsclPk);
            setValue(svcTssEsclDtlTMsg.mdseCd, usedPartsMsg.mdseCd.getValue());

            S21ApiTBLAccessor.create(svcTssEsclDtlTMsg);
            if (!RETURN_CD_NORMAL.equals(svcTssEsclDtlTMsg.getReturnCode())) {
                msgMap.addXxMsgIdWithPrm(NSZM0626E, new String[] {svcTssEsclDtlTMsg.getTableName() });
            }
        }
    }

    // add start 2015/12/24 CSA Defect#2359
    private String getSvcPblmTpCd(String glblCmpyCd, String value) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("xtrnlPblmTpRefTxt", value);
        return (String) this.ssmBatchClient.queryObject("getSvcPblmTpCd", param);
    }

    private String getSvcPblmLocTpCd(String glblCmpyCd, String value) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("xtrnlPblmLocTpRefTxt", value);
        return (String) this.ssmBatchClient.queryObject("getSvcPblmLocTpCd", param);
    }

    private String getSvcPblmCrctTpCd(String glblCmpyCd, String value) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("xtrnlPblmCrctTpRefTxt", value);
        return (String) this.ssmBatchClient.queryObject("getSvcPblmCrctTpCd", param);
    }

    private String getSvcPblmRsnTpCd(String glblCmpyCd, String value) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("xtrnlPblmRsnTpRefTxt", value);
        return (String) this.ssmBatchClient.queryObject("getSvcPblmRsnTpCd", param);
    }
    // add end 2015/12/24 CSA Defect#2359
}
