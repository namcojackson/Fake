/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC101001;

import static com.canon.cusa.s21.api.NSZ.NSZC101001.constant.NSZC101001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDPItem;
import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_PRC_EFFTMsg;
import business.db.DS_CONTR_VLD_RSLT_WRKTMsg;
import business.db.SVC_MEMOTMsg;
import business.parts.NSZC077001PMsg;
import business.parts.NSZC101001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC077001.ContrTrkProcMode;
import com.canon.cusa.s21.api.NSZ.NSZC077001.NSZC077001;
import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_TRK_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_TRK_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_VLD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 *<pre>
 * Complete Contract Approval API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/25/2016   Hitachi         A.Kohinata      Create          QC#1088
 * 07/31/2017   Hitachi         M.Kidokoro      Update          QC#20116
 *</pre>
 */
public class NSZC101001 extends S21ApiCommonBase {

    /**
     * S21SsmBatchClient
     */
    private S21SsmBatchClient ssmBatchClient = null;

    /**
     * Constructor
     */
    public NSZC101001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute
     * @param pMsg NSZC101001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NSZC101001PMsg pMsg, final ONBATCH_TYPE onBatchType) {
        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);

        if (!checkParam(msgMap)) {
            msgMap.flush();
            return;
        }

        Map<String, Object> dsContrMap = getUpdDsContr(msgMap);
        if (dsContrMap == null) {
            msgMap.flush();
            return;
        }

        String processMode = pMsg.xxProcTpCd.getValue();
        if (PROC_TP_APPROVE.equals(processMode)) {
            doProcessApprove(msgMap, dsContrMap);
        } else if (PROC_TP_REJECT.equals(processMode)) {
            doProcessReject(msgMap, dsContrMap);
        }

        msgMap.flush();
    }

    private void doProcessApprove(S21ApiMessageMap msgMap, Map<String, Object> dsContrMap) {
        BigDecimal prntDsContrTrkPk = updDsContrApprove(msgMap, dsContrMap);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        List<DS_CONTR_DTLTMsg> dsContrDtlList = updDsContrDtlApprove(msgMap, dsContrMap, prntDsContrTrkPk);
        if (dsContrDtlList == null || dsContrDtlList.isEmpty()) {
            return;
        }
        List<DS_CONTR_BLLG_MTRTMsg> dsContrBllgMtrList = updDsContrBllgMtrApprove(msgMap, dsContrMap, dsContrDtlList, prntDsContrTrkPk);
        if (dsContrBllgMtrList == null || dsContrBllgMtrList.isEmpty()) {
            return;
        }
        if (!updDsContrPrcEffBaseApprove(msgMap, dsContrMap, dsContrDtlList, prntDsContrTrkPk)) {
            return;
        }
        if (!updDsContrPrcEffUsgApprove(msgMap, dsContrMap, dsContrBllgMtrList, prntDsContrTrkPk)) {
            return;
        }
        if (!createOverrideMemo(msgMap, dsContrMap)) {
            return;
        }
        if (!removeDsContrVldRslt(msgMap, dsContrMap)) {
            return;
        }
    }

    private void doProcessReject(S21ApiMessageMap msgMap, Map<String, Object> dsContrMap) {
        BigDecimal prntDsContrTrkPk = updDsContrReject(msgMap, dsContrMap);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        List<DS_CONTR_DTLTMsg> dsContrDtlList = updDsContrDtlReject(msgMap, dsContrMap, prntDsContrTrkPk);
        if (dsContrDtlList == null || dsContrDtlList.isEmpty()) {
            return;
        }
        List<DS_CONTR_BLLG_MTRTMsg> dsContrBllgMtrList = updDsContrBllgMtrReject(msgMap, dsContrDtlList, prntDsContrTrkPk);
        if (dsContrBllgMtrList == null || dsContrBllgMtrList.isEmpty()) {
            return;
        }
        if (!updDsContrPrcEffBaseReject(msgMap, dsContrDtlList, prntDsContrTrkPk)) {
            return;
        }
        if (!updDsContrPrcEffUsgReject(msgMap, dsContrMap, dsContrBllgMtrList, prntDsContrTrkPk)) {
            return;
        }
    }

    private BigDecimal updDsContrApprove(S21ApiMessageMap msgMap, Map<String, Object> dsContrMap) {
        NSZC101001PMsg pMsg = (NSZC101001PMsg) msgMap.getPmsg();
        BigDecimal dsContrPk = (BigDecimal) dsContrMap.get("DS_CONTR_PK");

        DS_CONTRTMsg inMsg = new DS_CONTRTMsg();
        setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        setValue(inMsg.dsContrPk, dsContrPk);

        inMsg = (DS_CONTRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
        if (inMsg == null) {
            msgMap.addXxMsgId(NSZM0873E);
            return null;
        }

        if (FLG_ON_Y.equals(inMsg.qltyAsrnHldFlg.getValue())) {
            setValue(inMsg.qltyAsrnHldFlg, FLG_OFF_N);
            setValue(inMsg.qltyAsrnHldPendApvlFlg, FLG_OFF_N);
        } else {
            // START 2017/07/31 M.Kidokoro [QC#20116, MOD]
//            if (DS_CONTR_STS.DRAFT.equals(inMsg.dsContrStsCd.getValue()) && pMsg.slsDt.getValue().compareTo(inMsg.contrVrsnEffThruDt.getValue()) <= 0) {
            String dsContrStsCd = inMsg.dsContrStsCd.getValue();
            if ((DS_CONTR_STS.DRAFT.equals(dsContrStsCd) || DS_CONTR_STS.ENTERED.equals(dsContrStsCd)) && pMsg.slsDt.getValue().compareTo(inMsg.contrVrsnEffThruDt.getValue()) <= 0) {
            // END 2017/07/31 M.Kidokoro [QC#20116, MOD]
                if (pMsg.slsDt.getValue().compareTo(inMsg.contrVrsnEffFromDt.getValue()) >= 0) {
                    setValue(inMsg.dsContrStsCd, DS_CONTR_STS.EFFECTIVE);
                } else {
                    setValue(inMsg.dsContrStsCd, DS_CONTR_STS.APPROVED);
                }
            }
            setValue(inMsg.qltyAsrnHldPendApvlFlg, FLG_OFF_N);
        }

        S21ApiTBLAccessor.update(inMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0873E);
            return null;
        }

        // call contract tracking api
        NSZC077001PMsg apiPMsg = new NSZC077001PMsg();
        setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        setValue(apiPMsg.xxModeCd, ContrTrkProcMode.USER_OPERATION.code);
        setValue(apiPMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.CONTRACT_HEADER);
        setValue(apiPMsg.dsContrPk, dsContrPk);
        setValue(apiPMsg.stsMemoRsnCd, DS_CONTR_TRK_RSN.COMPLETE_CONTRACT);
        setValue(apiPMsg.stsMemoUpdPsnCd, pMsg.xxUsrIdTxt.getValue());

        new NSZC077001().execute(apiPMsg, ONBATCH_TYPE.ONLINE);
        if (!checkApiMsg(msgMap, apiPMsg)) {
            return null;
        }
        return apiPMsg.prntDsContrTrkPk.getValue();
    }

    private List<DS_CONTR_DTLTMsg> updDsContrDtlApprove(S21ApiMessageMap msgMap, Map<String, Object> dsContrMap, BigDecimal prntDsContrTrkPk) {
        NSZC101001PMsg pMsg = (NSZC101001PMsg) msgMap.getPmsg();
        BigDecimal dsContrPk = (BigDecimal) dsContrMap.get("DS_CONTR_PK");
        String dsContrCtrlStsCd = (String) dsContrMap.get("DS_CONTR_CTRL_STS_CD");

        List<Map<String, Object>> dsContrDtlList = getUpdDsContrDtl(pMsg.glblCmpyCd.getValue(), dsContrPk);
        if (dsContrDtlList == null || dsContrDtlList.isEmpty()) {
            msgMap.addXxMsgId(NSZM0875E);
            return null;
        }

        List<DS_CONTR_DTLTMsg> updList = new ArrayList<DS_CONTR_DTLTMsg>(dsContrDtlList.size());
        List<NSZC077001PMsg> trkList = new ArrayList<NSZC077001PMsg>(dsContrDtlList.size());

        for (Map<String, Object> dsContrDtl : dsContrDtlList) {
            DS_CONTR_DTLTMsg inMsg = new DS_CONTR_DTLTMsg();
            setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
            setValue(inMsg.dsContrDtlPk, (BigDecimal) dsContrDtl.get("DS_CONTR_DTL_PK"));

            inMsg = (DS_CONTR_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
            if (inMsg == null) {
                msgMap.addXxMsgId(NSZM0875E);
                return null;
            }
            if (DS_CONTR_CTRL_STS.QA_HOLD.equals((String) dsContrDtl.get("DS_CONTR_CTRL_STS_CD"))) {
                setValue(inMsg.qltyAsrnHldFlg, FLG_OFF_N);
                setValue(inMsg.qltyAsrnHldPendApvlFlg, FLG_OFF_N);
            } else {
                String thruDt = inMsg.contrEffThruDt.getValue();
                if (ZYPCommonFunc.hasValue(inMsg.contrCloDt)) {
                    thruDt = inMsg.contrCloDt.getValue();
                }
                setValue(inMsg.dsContrDtlStsCd, getDsContrDtlStsCd(pMsg, dsContrCtrlStsCd, inMsg.contrEffFromDt.getValue(), thruDt));
                setValue(inMsg.qltyAsrnHldPendApvlFlg, FLG_OFF_N);
            }
            updList.add(inMsg);

            NSZC077001PMsg apiPMsg = new NSZC077001PMsg();
            setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
            setValue(apiPMsg.xxModeCd, ContrTrkProcMode.USER_OPERATION.code);
            setValue(apiPMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.BASE_CHARGE);
            setValue(apiPMsg.dsContrPk, dsContrPk);
            setValue(apiPMsg.dsContrDtlPk, inMsg.dsContrDtlPk);
            setValue(apiPMsg.prntDsContrTrkPk, prntDsContrTrkPk);
            setValue(apiPMsg.stsMemoUpdPsnCd, pMsg.xxUsrIdTxt.getValue());
            trkList.add(apiPMsg);
        }

        if (!updList.isEmpty()) {
            int updCnt = S21FastTBLAccessor.update(updList.toArray(new DS_CONTR_DTLTMsg[updList.size()]));
            if (updCnt != updList.size()) {
                msgMap.addXxMsgId(NSZM0875E);
                return null;
            }
        }

        // call contract tracking api
        new NSZC077001().execute(trkList, ONBATCH_TYPE.ONLINE);
        if (!checkApiMsg(msgMap, trkList)) {
            return null;
        }
        return updList;
    }

    private List<DS_CONTR_BLLG_MTRTMsg> updDsContrBllgMtrApprove(S21ApiMessageMap msgMap, Map<String, Object> dsContrMap, List<DS_CONTR_DTLTMsg> dsContrDtlList, BigDecimal prntDsContrTrkPk) {
        NSZC101001PMsg pMsg = (NSZC101001PMsg) msgMap.getPmsg();
        String dsContrCtrlStsCd = (String) dsContrMap.get("DS_CONTR_CTRL_STS_CD");

        List<DS_CONTR_BLLG_MTRTMsg> updList = new ArrayList<DS_CONTR_BLLG_MTRTMsg>();
        List<NSZC077001PMsg> trkList = new ArrayList<NSZC077001PMsg>();

        for (DS_CONTR_DTLTMsg dsContrDtl : dsContrDtlList) {
            List<Map<String, Object>> dsContrBllgMtrList = getUpdDsContrBllgMtr(pMsg.glblCmpyCd.getValue(), dsContrDtl.dsContrDtlPk.getValue());
            if (dsContrBllgMtrList == null || dsContrBllgMtrList.isEmpty()) {
                continue;
            }

            for (Map<String, Object> dsContrBllgMtr : dsContrBllgMtrList) {
                DS_CONTR_BLLG_MTRTMsg inMsg = new DS_CONTR_BLLG_MTRTMsg();
                setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
                setValue(inMsg.dsContrBllgMtrPk, (BigDecimal) dsContrBllgMtr.get("DS_CONTR_BLLG_MTR_PK"));

                inMsg = (DS_CONTR_BLLG_MTRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
                if (inMsg == null) {
                    msgMap.addXxMsgId(NSZM0874E);
                    return null;
                }
                if (DS_CONTR_CTRL_STS.QA_HOLD.equals((String) dsContrBllgMtr.get("DS_CONTR_CTRL_STS_CD"))) {
                    setValue(inMsg.qltyAsrnHldFlg, FLG_OFF_N);
                    setValue(inMsg.qltyAsrnHldPendApvlFlg, FLG_OFF_N);
                } else {
                    String thruDt = dsContrDtl.contrEffThruDt.getValue();
                    if (ZYPCommonFunc.hasValue(dsContrDtl.contrCloDt)) {
                        thruDt = dsContrDtl.contrCloDt.getValue();
                    }
                    setValue(inMsg.dsContrBllgMtrStsCd, getDsContrDtlStsCd(pMsg, dsContrCtrlStsCd, dsContrDtl.contrEffFromDt.getValue(), thruDt));
                    setValue(inMsg.qltyAsrnHldPendApvlFlg, FLG_OFF_N);
                }
                updList.add(inMsg);

                NSZC077001PMsg apiPMsg = new NSZC077001PMsg();
                setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
                setValue(apiPMsg.xxModeCd, ContrTrkProcMode.USER_OPERATION.code);
                setValue(apiPMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.USAGE_CHARGE);
                setValue(apiPMsg.dsContrPk, dsContrDtl.dsContrPk);
                setValue(apiPMsg.dsContrDtlPk, inMsg.dsContrDtlPk);
                setValue(apiPMsg.dsContrBllgMtrPk, inMsg.dsContrBllgMtrPk);
                setValue(apiPMsg.prntDsContrTrkPk, prntDsContrTrkPk);
                setValue(apiPMsg.stsMemoUpdPsnCd, pMsg.xxUsrIdTxt.getValue());
                trkList.add(apiPMsg);
            }
        }

        if (!updList.isEmpty()) {
            int updCnt = S21FastTBLAccessor.update(updList.toArray(new DS_CONTR_BLLG_MTRTMsg[updList.size()]));
            if (updCnt != updList.size()) {
                msgMap.addXxMsgId(NSZM0874E);
                return null;
            }
        }

        // call contract tracking api
        new NSZC077001().execute(trkList, ONBATCH_TYPE.ONLINE);
        if (!checkApiMsg(msgMap, trkList)) {
            return null;
        }
        return updList;
    }

    private boolean updDsContrPrcEffBaseApprove(S21ApiMessageMap msgMap, Map<String, Object> dsContrMap, List<DS_CONTR_DTLTMsg> dsContrDtlList, BigDecimal prntDsContrTrkPk) {
        NSZC101001PMsg pMsg = (NSZC101001PMsg) msgMap.getPmsg();
        String dsContrCtrlStsCd = (String) dsContrMap.get("DS_CONTR_CTRL_STS_CD");

        List<DS_CONTR_PRC_EFFTMsg> updList = new ArrayList<DS_CONTR_PRC_EFFTMsg>();
        List<NSZC077001PMsg> trkList = new ArrayList<NSZC077001PMsg>();

        for (DS_CONTR_DTLTMsg dsContrDtl : dsContrDtlList) {
            List<Map<String, Object>> dsContrPrcEffList = getUpdDsContrPrcEffBase(pMsg.glblCmpyCd.getValue(), dsContrDtl.dsContrDtlPk.getValue());
            if (dsContrPrcEffList == null || dsContrPrcEffList.isEmpty()) {
                continue;
            }

            for (Map<String, Object> dsContrPrcEff : dsContrPrcEffList) {
                DS_CONTR_PRC_EFFTMsg inMsg = new DS_CONTR_PRC_EFFTMsg();
                setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
                setValue(inMsg.dsContrPrcEffPk, (BigDecimal) dsContrPrcEff.get("DS_CONTR_PRC_EFF_PK"));

                inMsg = (DS_CONTR_PRC_EFFTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
                if (inMsg == null) {
                    msgMap.addXxMsgId(NSZM0876E);
                    return false;
                }
                if (DS_CONTR_CTRL_STS.QA_HOLD.equals((String) dsContrPrcEff.get("DS_CONTR_CTRL_STS_CD"))) {
                    setValue(inMsg.qltyAsrnHldFlg, FLG_OFF_N);
                    setValue(inMsg.qltyAsrnHldPendApvlFlg, FLG_OFF_N);
                } else {
                    String thruDt = dsContrDtl.contrEffThruDt.getValue();
                    if (ZYPCommonFunc.hasValue(dsContrDtl.contrCloDt)) {
                        thruDt = dsContrDtl.contrCloDt.getValue();
                    }
                    setValue(inMsg.dsContrPrcEffStsCd, getDsContrDtlStsCd(pMsg, dsContrCtrlStsCd, dsContrDtl.contrEffFromDt.getValue(), thruDt));
                    setValue(inMsg.qltyAsrnHldPendApvlFlg, FLG_OFF_N);
                }
                updList.add(inMsg);

                NSZC077001PMsg apiPMsg = new NSZC077001PMsg();
                setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
                setValue(apiPMsg.xxModeCd, ContrTrkProcMode.USER_OPERATION.code);
                setValue(apiPMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.BASE_CHARGE_PE);
                setValue(apiPMsg.dsContrPk, dsContrDtl.dsContrPk);
                setValue(apiPMsg.dsContrDtlPk, inMsg.dsContrDtlPk);
                setValue(apiPMsg.dsContrPrcEffPk, inMsg.dsContrPrcEffPk);
                setValue(apiPMsg.contrPrcEffFromDt, inMsg.contrPrcEffFromDt);
                setValue(apiPMsg.contrPrcEffThruDt, inMsg.contrPrcEffThruDt);
                setValue(apiPMsg.prntDsContrTrkPk, prntDsContrTrkPk);
                setValue(apiPMsg.stsMemoUpdPsnCd, pMsg.xxUsrIdTxt.getValue());
                trkList.add(apiPMsg);
            }
        }

        if (!updList.isEmpty()) {
            int updCnt = S21FastTBLAccessor.update(updList.toArray(new DS_CONTR_PRC_EFFTMsg[updList.size()]));
            if (updCnt != updList.size()) {
                msgMap.addXxMsgId(NSZM0876E);
                return false;
            }
        }

        // call contract tracking api
        new NSZC077001().execute(trkList, ONBATCH_TYPE.ONLINE);
        if (!checkApiMsg(msgMap, trkList)) {
            return false;
        }
        return true;
    }

    private boolean updDsContrPrcEffUsgApprove(S21ApiMessageMap msgMap, Map<String, Object> dsContrMap, List<DS_CONTR_BLLG_MTRTMsg> dsContrBllgMtrList, BigDecimal prntDsContrTrkPk) {
        NSZC101001PMsg pMsg = (NSZC101001PMsg) msgMap.getPmsg();
        BigDecimal dsContrPk = (BigDecimal) dsContrMap.get("DS_CONTR_PK");
        String dsContrCtrlStsCd = (String) dsContrMap.get("DS_CONTR_CTRL_STS_CD");

        List<DS_CONTR_PRC_EFFTMsg> updList = new ArrayList<DS_CONTR_PRC_EFFTMsg>();
        List<NSZC077001PMsg> trkList = new ArrayList<NSZC077001PMsg>();

        for (DS_CONTR_BLLG_MTRTMsg dsContrBllgMtr : dsContrBllgMtrList) {
            List<Map<String, Object>> dsContrPrcEffList = getUpdDsContrPrcEffUsg(pMsg.glblCmpyCd.getValue(), dsContrBllgMtr.dsContrBllgMtrPk.getValue());
            if (dsContrPrcEffList == null || dsContrPrcEffList.isEmpty()) {
                continue;
            }

            for (Map<String, Object> dsContrPrcEff : dsContrPrcEffList) {
                DS_CONTR_PRC_EFFTMsg inMsg = new DS_CONTR_PRC_EFFTMsg();
                setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
                setValue(inMsg.dsContrPrcEffPk, (BigDecimal) dsContrPrcEff.get("DS_CONTR_PRC_EFF_PK"));

                inMsg = (DS_CONTR_PRC_EFFTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
                if (inMsg == null) {
                    msgMap.addXxMsgId(NSZM0876E);
                    return false;
                }
                if (DS_CONTR_CTRL_STS.QA_HOLD.equals((String) dsContrPrcEff.get("DS_CONTR_CTRL_STS_CD"))) {
                    setValue(inMsg.qltyAsrnHldFlg, FLG_OFF_N);
                    setValue(inMsg.qltyAsrnHldPendApvlFlg, FLG_OFF_N);
                } else {
                    String thruDt = inMsg.contrPrcEffThruDt.getValue();
                    setValue(inMsg.dsContrPrcEffStsCd, getDsContrDtlStsCd(pMsg, dsContrCtrlStsCd, inMsg.contrPrcEffFromDt.getValue(), thruDt));
                    setValue(inMsg.qltyAsrnHldPendApvlFlg, FLG_OFF_N);
                }
                updList.add(inMsg);

                NSZC077001PMsg apiPMsg = new NSZC077001PMsg();
                setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
                setValue(apiPMsg.xxModeCd, ContrTrkProcMode.USER_OPERATION.code);
                setValue(apiPMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.USAGE_CHARGE_PE);
                setValue(apiPMsg.dsContrPk, dsContrPk);
                setValue(apiPMsg.dsContrDtlPk, inMsg.dsContrDtlPk);
                setValue(apiPMsg.dsContrBllgMtrPk, inMsg.dsContrBllgMtrPk);
                setValue(apiPMsg.dsContrPrcEffPk, inMsg.dsContrPrcEffPk);
                setValue(apiPMsg.contrPrcEffFromDt, inMsg.contrPrcEffFromDt);
                setValue(apiPMsg.contrPrcEffThruDt, inMsg.contrPrcEffThruDt);
                setValue(apiPMsg.prntDsContrTrkPk, prntDsContrTrkPk);
                setValue(apiPMsg.stsMemoUpdPsnCd, pMsg.xxUsrIdTxt.getValue());
                trkList.add(apiPMsg);
            }
        }

        if (!updList.isEmpty()) {
            int updCnt = S21FastTBLAccessor.update(updList.toArray(new DS_CONTR_PRC_EFFTMsg[updList.size()]));
            if (updCnt != updList.size()) {
                msgMap.addXxMsgId(NSZM0876E);
                return false;
            }
        }

        // call contract tracking api
        new NSZC077001().execute(trkList, ONBATCH_TYPE.ONLINE);
        if (!checkApiMsg(msgMap, trkList)) {
            return false;
        }
        return true;
    }

    private boolean createOverrideMemo(S21ApiMessageMap msgMap, Map<String, Object> dsContrMap) {
        NSZC101001PMsg pMsg = (NSZC101001PMsg) msgMap.getPmsg();
        BigDecimal dsContrPk = (BigDecimal) dsContrMap.get("DS_CONTR_PK");

        List<Map<String, Object>> errVldRsltList = getErrContrVldRslt(pMsg.glblCmpyCd.getValue(), pMsg.dsContrNum.getValue());
        if (errVldRsltList == null || errVldRsltList.isEmpty()) {
            return true;
        }

        StringBuilder sb = new StringBuilder();
        String separtor = System.getProperty("line.separator");

        sb.append("These validation results have been overridden as success by ");
        sb.append(pMsg.xxUsrIdTxt.getValue());
        sb.append(" on ");
        sb.append(pMsg.slsDt.getValue());
        sb.append(separtor);

        for (Map<String, Object> errVldRslt : errVldRsltList) {
            sb.append("Serial#:");
            sb.append((String) errVldRslt.get("SER_NUM"));
            sb.append("  Validation Name:");
            sb.append((String) errVldRslt.get("DS_CONTR_VLD_NM"));
            sb.append("  Result:");
            sb.append((String) errVldRslt.get("DS_CONTR_VLD_STS_DESC_TXT"));
            sb.append(separtor);
        }

        SVC_MEMOTMsg inMsg = new SVC_MEMOTMsg();
        setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        setValue(inMsg.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.SVC_MEMO_SQ));
        setValue(inMsg.dsContrPk, dsContrPk);
        setValue(inMsg.svcMemoCatgCd, SVC_MEMO_CATG.CONTRACT_MEMO);
        setValue(inMsg.svcMemoTpCd, SVC_MEMO_TP.COMPLETE_CONTRACT_APPROVAL);
        String svcMemoRsnCd = ZYPCodeDataUtil.getVarCharConstValue(SVC_MEMO_RSN_FOR_CPLT_CONTR, pMsg.glblCmpyCd.getValue());
        setValue(inMsg.svcMemoRsnCd, svcMemoRsnCd);

        String msg = sb.toString();
        if (msg.length() > SVC_MEMO_SIZE) {
            setValue(inMsg.svcCmntTxt, msg.substring(0, SVC_MEMO_SIZE));
        } else {
            setValue(inMsg.svcCmntTxt, msg);
        }

        S21ApiTBLAccessor.insert(inMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0081E);
            return false;
        }
        return true;
    }

    private boolean removeDsContrVldRslt(S21ApiMessageMap msgMap, Map<String, Object> dsContrMap) {
        NSZC101001PMsg pMsg = (NSZC101001PMsg) msgMap.getPmsg();
        BigDecimal dsContrPk = (BigDecimal) dsContrMap.get("DS_CONTR_PK");

        List<BigDecimal> vldRsltPkList = getContrVldRsltForRemove(pMsg.glblCmpyCd.getValue(), dsContrPk);
        if (vldRsltPkList == null || vldRsltPkList.isEmpty()) {
            return true;
        }

        List<DS_CONTR_VLD_RSLT_WRKTMsg> delList = new ArrayList<DS_CONTR_VLD_RSLT_WRKTMsg>();

        for (BigDecimal vldRsltPk : vldRsltPkList) {
            DS_CONTR_VLD_RSLT_WRKTMsg inMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
            setValue(inMsg.dsContrVldRsltWrkPk, vldRsltPk);

            inMsg = (DS_CONTR_VLD_RSLT_WRKTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
            if (inMsg == null) {
                msgMap.addXxMsgId(NSZM0967E);
                return false;
            }
            delList.add(inMsg);
        }

        if (!delList.isEmpty()) {
            int delCnt = S21FastTBLAccessor.removePhysical(delList.toArray(new DS_CONTR_VLD_RSLT_WRKTMsg[delList.size()]));
            if (delCnt != delList.size()) {
                msgMap.addXxMsgId(NSZM0967E);
                return false;
            }
        }
        return true;
    }

    private BigDecimal updDsContrReject(S21ApiMessageMap msgMap, Map<String, Object> dsContrMap) {
        NSZC101001PMsg pMsg = (NSZC101001PMsg) msgMap.getPmsg();
        BigDecimal dsContrPk = (BigDecimal) dsContrMap.get("DS_CONTR_PK");

        DS_CONTRTMsg inMsg = new DS_CONTRTMsg();
        setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        setValue(inMsg.dsContrPk, dsContrPk);

        inMsg = (DS_CONTRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
        if (inMsg == null) {
            msgMap.addXxMsgId(NSZM0873E);
            return null;
        }

        setValue(inMsg.qltyAsrnHldPendApvlFlg, FLG_OFF_N);
        S21ApiTBLAccessor.update(inMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0873E);
            return null;
        }

        // call contract tracking api
        NSZC077001PMsg apiPMsg = new NSZC077001PMsg();
        setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        setValue(apiPMsg.xxModeCd, ContrTrkProcMode.USER_OPERATION.code);
        setValue(apiPMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.CONTRACT_HEADER);
        setValue(apiPMsg.dsContrPk, dsContrPk);
        setValue(apiPMsg.stsMemoRsnCd, DS_CONTR_TRK_RSN.COMPLETE_CONTRACT);
        setValue(apiPMsg.stsMemoUpdPsnCd, pMsg.xxUsrIdTxt.getValue());

        new NSZC077001().execute(apiPMsg, ONBATCH_TYPE.ONLINE);
        if (!checkApiMsg(msgMap, apiPMsg)) {
            return null;
        }
        return apiPMsg.prntDsContrTrkPk.getValue();
    }

    private List<DS_CONTR_DTLTMsg> updDsContrDtlReject(S21ApiMessageMap msgMap, Map<String, Object> dsContrMap, BigDecimal prntDsContrTrkPk) {
        NSZC101001PMsg pMsg = (NSZC101001PMsg) msgMap.getPmsg();
        BigDecimal dsContrPk = (BigDecimal) dsContrMap.get("DS_CONTR_PK");

        List<Map<String, Object>> dsContrDtlList = getUpdDsContrDtl(pMsg.glblCmpyCd.getValue(), dsContrPk);
        if (dsContrDtlList == null || dsContrDtlList.isEmpty()) {
            msgMap.addXxMsgId(NSZM0875E);
            return null;
        }

        List<DS_CONTR_DTLTMsg> updList = new ArrayList<DS_CONTR_DTLTMsg>(dsContrDtlList.size());
        List<NSZC077001PMsg> trkList = new ArrayList<NSZC077001PMsg>(dsContrDtlList.size());

        for (Map<String, Object> dsContrDtl : dsContrDtlList) {
            DS_CONTR_DTLTMsg inMsg = new DS_CONTR_DTLTMsg();
            setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
            setValue(inMsg.dsContrDtlPk, (BigDecimal) dsContrDtl.get("DS_CONTR_DTL_PK"));

            inMsg = (DS_CONTR_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
            if (inMsg == null) {
                msgMap.addXxMsgId(NSZM0875E);
                return null;
            }
            setValue(inMsg.qltyAsrnHldPendApvlFlg, FLG_OFF_N);
            updList.add(inMsg);

            NSZC077001PMsg apiPMsg = new NSZC077001PMsg();
            setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
            setValue(apiPMsg.xxModeCd, ContrTrkProcMode.USER_OPERATION.code);
            setValue(apiPMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.BASE_CHARGE);
            setValue(apiPMsg.dsContrPk, dsContrPk);
            setValue(apiPMsg.dsContrDtlPk, inMsg.dsContrDtlPk);
            setValue(apiPMsg.prntDsContrTrkPk, prntDsContrTrkPk);
            setValue(apiPMsg.stsMemoUpdPsnCd, pMsg.xxUsrIdTxt.getValue());
            trkList.add(apiPMsg);
        }

        if (!updList.isEmpty()) {
            int updCnt = S21FastTBLAccessor.update(updList.toArray(new DS_CONTR_DTLTMsg[updList.size()]));
            if (updCnt != updList.size()) {
                msgMap.addXxMsgId(NSZM0875E);
                return null;
            }
        }

        // call contract tracking api
        new NSZC077001().execute(trkList, ONBATCH_TYPE.ONLINE);
        if (!checkApiMsg(msgMap, trkList)) {
            return null;
        }
        return updList;
    }

    private List<DS_CONTR_BLLG_MTRTMsg> updDsContrBllgMtrReject(S21ApiMessageMap msgMap, List<DS_CONTR_DTLTMsg> dsContrDtlList, BigDecimal prntDsContrTrkPk) {
        NSZC101001PMsg pMsg = (NSZC101001PMsg) msgMap.getPmsg();

        List<DS_CONTR_BLLG_MTRTMsg> updList = new ArrayList<DS_CONTR_BLLG_MTRTMsg>();
        List<NSZC077001PMsg> trkList = new ArrayList<NSZC077001PMsg>();

        for (DS_CONTR_DTLTMsg dsContrDtl : dsContrDtlList) {
            List<Map<String, Object>> dsContrBllgMtrList = getUpdDsContrBllgMtr(pMsg.glblCmpyCd.getValue(), dsContrDtl.dsContrDtlPk.getValue());
            if (dsContrBllgMtrList == null || dsContrBllgMtrList.isEmpty()) {
                continue;
            }

            for (Map<String, Object> dsContrBllgMtr : dsContrBllgMtrList) {
                DS_CONTR_BLLG_MTRTMsg inMsg = new DS_CONTR_BLLG_MTRTMsg();
                setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
                setValue(inMsg.dsContrBllgMtrPk, (BigDecimal) dsContrBllgMtr.get("DS_CONTR_BLLG_MTR_PK"));

                inMsg = (DS_CONTR_BLLG_MTRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
                if (inMsg == null) {
                    msgMap.addXxMsgId(NSZM0874E);
                    return null;
                }
                setValue(inMsg.qltyAsrnHldPendApvlFlg, FLG_OFF_N);
                updList.add(inMsg);

                NSZC077001PMsg apiPMsg = new NSZC077001PMsg();
                setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
                setValue(apiPMsg.xxModeCd, ContrTrkProcMode.USER_OPERATION.code);
                setValue(apiPMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.USAGE_CHARGE);
                setValue(apiPMsg.dsContrPk, dsContrDtl.dsContrPk);
                setValue(apiPMsg.dsContrDtlPk, inMsg.dsContrDtlPk);
                setValue(apiPMsg.dsContrBllgMtrPk, inMsg.dsContrBllgMtrPk);
                setValue(apiPMsg.prntDsContrTrkPk, prntDsContrTrkPk);
                setValue(apiPMsg.stsMemoUpdPsnCd, pMsg.xxUsrIdTxt.getValue());
                trkList.add(apiPMsg);
            }
        }

        if (!updList.isEmpty()) {
            int updCnt = S21FastTBLAccessor.update(updList.toArray(new DS_CONTR_BLLG_MTRTMsg[updList.size()]));
            if (updCnt != updList.size()) {
                msgMap.addXxMsgId(NSZM0874E);
                return null;
            }
        }

        // call contract tracking api
        new NSZC077001().execute(trkList, ONBATCH_TYPE.ONLINE);
        if (!checkApiMsg(msgMap, trkList)) {
            return null;
        }
        return updList;
    }

    private boolean updDsContrPrcEffBaseReject(S21ApiMessageMap msgMap, List<DS_CONTR_DTLTMsg> dsContrDtlList, BigDecimal prntDsContrTrkPk) {
        NSZC101001PMsg pMsg = (NSZC101001PMsg) msgMap.getPmsg();

        List<DS_CONTR_PRC_EFFTMsg> updList = new ArrayList<DS_CONTR_PRC_EFFTMsg>();
        List<NSZC077001PMsg> trkList = new ArrayList<NSZC077001PMsg>();

        for (DS_CONTR_DTLTMsg dsContrDtl : dsContrDtlList) {
            List<Map<String, Object>> dsContrPrcEffList = getUpdDsContrPrcEffBase(pMsg.glblCmpyCd.getValue(), dsContrDtl.dsContrDtlPk.getValue());
            if (dsContrPrcEffList == null || dsContrPrcEffList.isEmpty()) {
                continue;
            }

            for (Map<String, Object> dsContrPrcEff : dsContrPrcEffList) {
                DS_CONTR_PRC_EFFTMsg inMsg = new DS_CONTR_PRC_EFFTMsg();
                setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
                setValue(inMsg.dsContrPrcEffPk, (BigDecimal) dsContrPrcEff.get("DS_CONTR_PRC_EFF_PK"));

                inMsg = (DS_CONTR_PRC_EFFTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
                if (inMsg == null) {
                    msgMap.addXxMsgId(NSZM0876E);
                    return false;
                }
                setValue(inMsg.qltyAsrnHldPendApvlFlg, FLG_OFF_N);
                updList.add(inMsg);

                NSZC077001PMsg apiPMsg = new NSZC077001PMsg();
                setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
                setValue(apiPMsg.xxModeCd, ContrTrkProcMode.USER_OPERATION.code);
                setValue(apiPMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.BASE_CHARGE_PE);
                setValue(apiPMsg.dsContrPk, dsContrDtl.dsContrPk);
                setValue(apiPMsg.dsContrDtlPk, inMsg.dsContrDtlPk);
                setValue(apiPMsg.dsContrPrcEffPk, inMsg.dsContrPrcEffPk);
                setValue(apiPMsg.contrPrcEffFromDt, inMsg.contrPrcEffFromDt);
                setValue(apiPMsg.contrPrcEffThruDt, inMsg.contrPrcEffThruDt);
                setValue(apiPMsg.prntDsContrTrkPk, prntDsContrTrkPk);
                setValue(apiPMsg.stsMemoUpdPsnCd, pMsg.xxUsrIdTxt.getValue());
                trkList.add(apiPMsg);
            }
        }

        if (!updList.isEmpty()) {
            int updCnt = S21FastTBLAccessor.update(updList.toArray(new DS_CONTR_PRC_EFFTMsg[updList.size()]));
            if (updCnt != updList.size()) {
                msgMap.addXxMsgId(NSZM0876E);
                return false;
            }
        }

        // call contract tracking api
        new NSZC077001().execute(trkList, ONBATCH_TYPE.ONLINE);
        if (!checkApiMsg(msgMap, trkList)) {
            return false;
        }
        return true;
    }

    private boolean updDsContrPrcEffUsgReject(S21ApiMessageMap msgMap, Map<String, Object> dsContrMap, List<DS_CONTR_BLLG_MTRTMsg> dsContrBllgMtrList, BigDecimal prntDsContrTrkPk) {
        NSZC101001PMsg pMsg = (NSZC101001PMsg) msgMap.getPmsg();
        BigDecimal dsContrPk = (BigDecimal) dsContrMap.get("DS_CONTR_PK");

        List<DS_CONTR_PRC_EFFTMsg> updList = new ArrayList<DS_CONTR_PRC_EFFTMsg>();
        List<NSZC077001PMsg> trkList = new ArrayList<NSZC077001PMsg>();

        for (DS_CONTR_BLLG_MTRTMsg dsContrBllgMtr : dsContrBllgMtrList) {
            List<Map<String, Object>> dsContrPrcEffList = getUpdDsContrPrcEffUsg(pMsg.glblCmpyCd.getValue(), dsContrBllgMtr.dsContrBllgMtrPk.getValue());
            if (dsContrPrcEffList == null || dsContrPrcEffList.isEmpty()) {
                continue;
            }

            for (Map<String, Object> dsContrPrcEff : dsContrPrcEffList) {
                DS_CONTR_PRC_EFFTMsg inMsg = new DS_CONTR_PRC_EFFTMsg();
                setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
                setValue(inMsg.dsContrPrcEffPk, (BigDecimal) dsContrPrcEff.get("DS_CONTR_PRC_EFF_PK"));

                inMsg = (DS_CONTR_PRC_EFFTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
                if (inMsg == null) {
                    msgMap.addXxMsgId(NSZM0876E);
                    return false;
                }
                setValue(inMsg.qltyAsrnHldPendApvlFlg, FLG_OFF_N);
                updList.add(inMsg);

                NSZC077001PMsg apiPMsg = new NSZC077001PMsg();
                setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
                setValue(apiPMsg.xxModeCd, ContrTrkProcMode.USER_OPERATION.code);
                setValue(apiPMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.USAGE_CHARGE_PE);
                setValue(apiPMsg.dsContrPk, dsContrPk);
                setValue(apiPMsg.dsContrDtlPk, inMsg.dsContrDtlPk);
                setValue(apiPMsg.dsContrBllgMtrPk, inMsg.dsContrBllgMtrPk);
                setValue(apiPMsg.dsContrPrcEffPk, inMsg.dsContrPrcEffPk);
                setValue(apiPMsg.contrPrcEffFromDt, inMsg.contrPrcEffFromDt);
                setValue(apiPMsg.contrPrcEffThruDt, inMsg.contrPrcEffThruDt);
                setValue(apiPMsg.prntDsContrTrkPk, prntDsContrTrkPk);
                setValue(apiPMsg.stsMemoUpdPsnCd, pMsg.xxUsrIdTxt.getValue());
                trkList.add(apiPMsg);
            }
        }

        if (!updList.isEmpty()) {
            int updCnt = S21FastTBLAccessor.update(updList.toArray(new DS_CONTR_PRC_EFFTMsg[updList.size()]));
            if (updCnt != updList.size()) {
                msgMap.addXxMsgId(NSZM0876E);
                return false;
            }
        }

        // call contract tracking api
        new NSZC077001().execute(trkList, ONBATCH_TYPE.ONLINE);
        if (!checkApiMsg(msgMap, trkList)) {
            return false;
        }
        return true;
    }

    private String getDsContrDtlStsCd(NSZC101001PMsg pMsg, String dsContrCtrlStsCd, String effFromDt, String effThruDt) {
        String dsContrDtlStsCd = "";

        // Contract Header Status is QA Hold
        if (!DS_CONTR_CTRL_STS.QA_HOLD.equals(dsContrCtrlStsCd)) {
            if (pMsg.slsDt.getValue().compareTo(effThruDt) <= 0) {
                if (pMsg.slsDt.getValue().compareTo(effFromDt) >= 0) {
                    dsContrDtlStsCd = DS_CONTR_DTL_STS.ACTIVE;
                } else {
                    dsContrDtlStsCd = DS_CONTR_DTL_STS.SIGNED;
                }
            }
        }
        return dsContrDtlStsCd;
    }

    private Map<String, Object> getUpdDsContr(S21ApiMessageMap msgMap) {
        NSZC101001PMsg pMsg = (NSZC101001PMsg) msgMap.getPmsg();

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        queryParam.put("dsContrNum", pMsg.dsContrNum.getValue());
        queryParam.put("qaHold", DS_CONTR_CTRL_STS.QA_HOLD);
        queryParam.put("qaHoldPendingApproval", DS_CONTR_CTRL_STS.QA_HOLD_PENDING_APPROVAL);

        Map<String, Object> dsContrMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getUpdDsContr", queryParam);
        if (dsContrMap == null) {
            msgMap.addXxMsgId(NSZM0639E);
        }
        return dsContrMap;
    }

    private List<Map<String, Object>> getUpdDsContrDtl(String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("dsContrPk", dsContrPk);
        queryParam.put("qaHold", DS_CONTR_CTRL_STS.QA_HOLD);
        queryParam.put("qaHoldPendingApproval", DS_CONTR_CTRL_STS.QA_HOLD_PENDING_APPROVAL);

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getUpdDsContrDtl", queryParam);
    }

    private List<Map<String, Object>> getUpdDsContrBllgMtr(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("dsContrDtlPk", dsContrDtlPk);
        queryParam.put("qaHold", DS_CONTR_CTRL_STS.QA_HOLD);
        queryParam.put("qaHoldPendingApproval", DS_CONTR_CTRL_STS.QA_HOLD_PENDING_APPROVAL);

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getUpdDsContrBllgMtr", queryParam);
    }

    private List<Map<String, Object>> getUpdDsContrPrcEffBase(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("dsContrDtlPk", dsContrDtlPk);
        queryParam.put("qaHold", DS_CONTR_CTRL_STS.QA_HOLD);
        queryParam.put("qaHoldPendingApproval", DS_CONTR_CTRL_STS.QA_HOLD_PENDING_APPROVAL);

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getUpdDsContrPrcEffBase", queryParam);
    }

    private List<Map<String, Object>> getUpdDsContrPrcEffUsg(String glblCmpyCd, BigDecimal dsContrBllgMtrPk) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        queryParam.put("qaHold", DS_CONTR_CTRL_STS.QA_HOLD);
        queryParam.put("qaHoldPendingApproval", DS_CONTR_CTRL_STS.QA_HOLD_PENDING_APPROVAL);

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getUpdDsContrPrcEffUsg", queryParam);
    }

    private List<Map<String, Object>> getErrContrVldRslt(String glblCmpyCd, String dsContrNum) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("dsContrNum", dsContrNum);
        Set<String> vldStsSet = new LinkedHashSet<String>();
        vldStsSet.add(DS_CONTR_VLD_STS.ERROR);
        vldStsSet.add(DS_CONTR_VLD_STS.NOT_VALIDATED);
        queryParam.put("dsContrVldStsCd", vldStsSet);

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getErrContrVldRslt", queryParam);
    }

    private List<BigDecimal> getContrVldRsltForRemove(String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("dsContrPk", dsContrPk);

        return (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getContrVldRsltForRemove", queryParam);
    }

    private boolean checkParam(S21ApiMessageMap msgMap) {
        NSZC101001PMsg pMsg = (NSZC101001PMsg) msgMap.getPmsg();

        // check common mandatory parameter
        mandatoryCheck(msgMap, pMsg.glblCmpyCd, NSZM0001E);
        mandatoryCheck(msgMap, pMsg.xxUsrIdTxt, NSZM0293E);
        mandatoryCheck(msgMap, pMsg.dsContrNum, NSZM0271E);

        if (msgMap.getMsgMgr().isXxMsgId()) {
            return false;
        }
        return true;
    }

    private void mandatoryCheck(S21ApiMessageMap msgMap, EZDPItem item, String messageId) {
        if (!hasValue(item)) {
            msgMap.addXxMsgId(messageId);
        }
    }

    private boolean checkApiMsg(S21ApiMessageMap msgMap, NSZC077001PMsg apiPMsg) {
        if (ZYPCommonFunc.hasValue(apiPMsg.xxMsgIdList.no(0).xxMsgId)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(apiPMsg);
            S21ApiMessage msg = msgList.get(0);
            msgMap.addXxMsgIdWithPrm(msg.getXxMsgid(), msg.getXxMsgPrmArray());
            return false;
        }
        return true;
    }

    private boolean checkApiMsg(S21ApiMessageMap msgMap, List<NSZC077001PMsg> trkList) {
        for (NSZC077001PMsg apiPMsg : trkList) {
            if (ZYPCommonFunc.hasValue(apiPMsg.xxMsgIdList.no(0).xxMsgId)) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(apiPMsg);
                S21ApiMessage msg = msgList.get(0);
                msgMap.addXxMsgIdWithPrm(msg.getXxMsgid(), msg.getXxMsgPrmArray());
                return false;
            }
        }
        return true;
    }
}
