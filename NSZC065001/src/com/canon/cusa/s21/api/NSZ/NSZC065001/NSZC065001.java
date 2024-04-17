/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC065001;

import static com.canon.cusa.s21.api.NSZ.NSZC065001.constant.NSZC065001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDPItem;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTRTMsgArray;
import business.db.DS_CONTR_DTL_STS_VTMsg;
import business.db.DS_CONTR_DTL_STS_VTMsgArray;
import business.db.DS_CONTR_STS_VTMsg;
import business.db.DS_CONTR_STS_VTMsgArray;
import business.db.HLDTMsg;
import business.parts.NWXC005001PMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.CovTmplInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetCovTmpl;
import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.common.NWX.NWXC005001.NWXC005001ValidationBean;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_RSN;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Contract Status Validation API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/09/2015   Hitachi         Y.Tsuchimoto    Create          NA#Contract Status Validation API
 * 03/15/2016   Hitachi         K.Kasai         Update          QC#5282
 * 03/16/2016   Hitachi         A.Kohinata      Update          QC#5540
 * 09/28/2016   Hitachi         A.Kohinata      Update          QC#12898
 * 10/19/2016   Hitachi         A.Kohinata      Update          QC#15344
 * 08/16/2018   CITS            M.Naito         Update          QC#27249
 * 2018/10/12   Hitachi         K.Kitachi       Update          QC#28754
 * </pre>
 */
public class NSZC065001 extends S21ApiCommonBase {

    /**
     * S21SsmBatchClient
     */
    private S21SsmBatchClient ssmBatchClient = null;

    /**
     * Constructor
     */
    public NSZC065001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute
     * @param param NWXC005001ValidationBean
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NWXC005001ValidationBean param, final ONBATCH_TYPE onBatchType) {

        NWXC005001PMsg pMsg = param.getInputPMsg();
        if (pMsg != null) {
            ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum_O, (String) null);
            ZYPEZDItemValueSetter.setValue(pMsg.cpoDtlLineNum_O, (String) null);
            ZYPEZDItemValueSetter.setValue(pMsg.cpoDtlLineSubNum_O, (String) null);
            ZYPEZDItemValueSetter.setValue(pMsg.shpgPlnNum_O, (String) null);
            ZYPEZDItemValueSetter.setValue(pMsg.hldRsnCd, (String) null);
        }

        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);
        if (checkParameter(msgMap, param)) {
            createRtrnHoldInfo(msgMap, param);
        }
        msgMap.flush();
    }

    private boolean checkParameter(S21ApiMessageMap msgMap, NWXC005001ValidationBean param) {
        NWXC005001PMsg pMsg = (NWXC005001PMsg) msgMap.getPmsg();

        mandatoryCheck(msgMap, pMsg.glblCmpyCd, NSZM0001E);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return false;
        }

        HLDTMsg condition = new HLDTMsg();
        condition.setSQLID("015");
        condition.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        condition.setConditionValue("hldRsnCd01", HLD_RSN.CONTRACT_STATUS_HOLD);
        condition.setConditionValue("cpoOrdNum01", param.getInputPMsg().cpoOrdNum_I.getValue());
        condition.setConditionValue("cpoDtlLineNum01", param.getInputPMsg().cpoDtlLineNum_I.getValue());
        condition.setConditionValue("cpoDtlLineSubNum01", param.getInputPMsg().cpoDtlLineSubNum_I.getValue());
        condition.setConditionValue("relFlg01", ZYPConstant.FLG_OFF_N);
        int count = EZDTBLAccessor.count(condition);
        if (count > 0) {
            return false;
        }

        // CPO_DTLTMsg Check
        CPO_DTLTMsg cdTmsg = param.getCdTMsg();
        // START 2016/09/28 A.Kohinata [QC#12898, MOD]
        if (cdTmsg == null) {
            msgMap.addXxMsgId(NSZM0615E);
            return false;
        }
        if (!hasValue(cdTmsg.dsContrNum)) {
            msgMap.addXxMsgId(NSZM0271E);
            return false;
        }
        if (!isFleetContract(pMsg.glblCmpyCd.getValue(), cdTmsg.dsContrNum.getValue())) {
            if (!hasValue(cdTmsg.svcMachMstrPk)) {
                msgMap.addXxMsgId(NSZM0615E);
                return false;
            }
        }
        // END 2016/09/28 A.Kohinata [QC#12898, MOD]

        // CPOTMsg Check
        CPOTMsg cpoTMsg = param.getCTMsg();
        if (!(cpoTMsg != null && ZYPCommonFunc.hasValue(cpoTMsg.cpoOrdTs))) {
            msgMap.addXxMsgId(NSZM0616E);
            return false;
        }

        return true;
    }

    private void mandatoryCheck(S21ApiMessageMap msgMap, EZDPItem targetItem, String messageId) {
        if (!hasValue(targetItem)) {
            msgMap.addXxMsgId(messageId);
        }
    }

    private void createRtrnHoldInfo(S21ApiMessageMap msgMap, NWXC005001ValidationBean param) {
        NWXC005001PMsg pMsg = (NWXC005001PMsg) msgMap.getPmsg();

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String cpoOrdTsYYYYMMDD = param.getCTMsg().cpoOrdTs.getValue().substring(0, YYYYMMDD_LENGTH);
        // get Contract
        // START 2018/08/16 M.Naito [QC#27249, MOD]
        // START 2016/09/28 A.Kohinata [QC#12898, MOD]
//        Map<String, Object> contractInfo;
        List<Map<String, Object>> contractInfoList = new ArrayList<Map<String, Object>>();
        if (isFleetContract(glblCmpyCd, param.getDscdTMsg().dsContrNum.getValue())) {
//            contractInfo = getFleetContractInfoMap(glblCmpyCd, param.getDscdTMsg().dsContrNum.getValue(), cpoOrdTsYYYYMMDD);
            contractInfoList = getFleetContractInfoMap(glblCmpyCd, param.getDscdTMsg().dsContrNum.getValue(), cpoOrdTsYYYYMMDD);
        } else {
//            contractInfo = getContractInfoMap(glblCmpyCd, param.getDscdTMsg().svcMachMstrPk.getValue(), cpoOrdTsYYYYMMDD);
            contractInfoList = getContractInfoMap(glblCmpyCd, param.getDscdTMsg().svcMachMstrPk.getValue(), cpoOrdTsYYYYMMDD);
        }
        // END 2016/09/28 A.Kohinata [QC#12898, MOD]
//        if (contractInfo == null) {
        if (contractInfoList == null) {
            msgMap.addXxMsgId(NSZM0617E);
            return;
        }
        // check contract supply inclusive
        List<Map<String, Object>> suplInclContrList = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> contrInfo : contractInfoList) {
            NSXC001001GetCovTmpl covTmpl = new NSXC001001GetCovTmpl();
            CovTmplInfo tmplInfo = new CovTmplInfo();
            tmplInfo.setGlblCmpyCd(glblCmpyCd);
            tmplInfo.setSlsDt(pMsg.slsDt.getValue());
            tmplInfo.setSvcPgmMdseCd((String) contrInfo.get(CLM_SVC_PGM_MDSE_CD));
            boolean isSuplIncl = covTmpl.isSuplIncl(tmplInfo);
            if (isSuplIncl) {
                suplInclContrList.add(contrInfo);
            }
        }
        if (suplInclContrList == null) {
            msgMap.addXxMsgId(NSZM0617E);
            return;
        }
        boolean hldFlg = true;
        for (Map<String, Object> contractInfo : suplInclContrList) {
            // Contract Status check
            if (!isCheckContractStatus(glblCmpyCd, (BigDecimal) contractInfo.get(CLM_DS_CONTR_PK))) {
                continue;
            }
            // Machine Status check
            if (!isCheckMachineStatus(glblCmpyCd, (BigDecimal) contractInfo.get(CLM_DS_CONTR_DTL_PK))) {
                continue;
            }
            // START 2018/10/12 K.Kitachi [QC#28754, DEL]
//            // Price Effective(Base) Status check
//            if (ZYPConstant.FLG_ON_Y.equals((String) contractInfo.get(CLM_BASE_CHRG_FLG))) {
//                Map<String, Object> result = getPriceEffectiveBaseMap(glblCmpyCd, (BigDecimal) contractInfo.get(CLM_DS_CONTR_DTL_PK), cpoOrdTsYYYYMMDD);
//                if (result == null) {
//                    msgMap.addXxMsgId(NSZM0618E);
//                    return;
//                }
//
//                if (!isCheckPriceEffectiveBaseStatus(result)) {
//                    continue;
//                }
//            }
//            // Meter Status check
//            if (ZYPConstant.FLG_ON_Y.equals((String) contractInfo.get(CLM_USG_CHRG_FLG))) {
//                List<Map<String, Object>> resultList = getMeterList(glblCmpyCd, (BigDecimal) contractInfo.get(CLM_DS_CONTR_DTL_PK));
//                if (resultList == null || resultList.size() == 0) {
//                    msgMap.addXxMsgId(NSZM0619E);
//                    return;
//                } else {
//                    if (!isCheckMeterStatus(resultList)) {
//                        continue;
//                    }
//                }
//            }
//            // Price Effective(Usage) Status check
//            List<Map<String, Object>> resultList = getPriceEffectiveUsageList(glblCmpyCd, (BigDecimal) contractInfo.get(CLM_DS_CONTR_DTL_PK), cpoOrdTsYYYYMMDD);
//            if (resultList == null || resultList.size() == 0) {
//                msgMap.addXxMsgId(NSZM0618E);
//                return;
//            } else {
//                if (!isCheckPriceEffectiveUsageStatus(resultList)) {
//                    continue;
//                }
//            }
            // END 2018/10/12 K.Kitachi [QC#28754, DEL]
            hldFlg = false;
        }
        if (hldFlg) {
            setOutputHoldInfo(param);
            return;
        }
        // END 2018/08/16 M.Naito [QC#27249, MOD]

        // START 2018/08/16 M.Naito [QC#27249, DEL]
//        // Contract Status check
//        if (!isCheckContractStatus(glblCmpyCd, (BigDecimal) contractInfo.get(CLM_DS_CONTR_PK))) {
//            contrStsFlg = true;
//            setOutputHoldInfo(param);
//            return;
//        }
//
//        // Machine Status check
//        if (!isCheckMachineStatus(glblCmpyCd, (BigDecimal) contractInfo.get(CLM_DS_CONTR_DTL_PK))) {
//            setOutputHoldInfo(param);
//            return;
//        }
//
//        // Price Effective(Base) Status check
//        if (ZYPConstant.FLG_ON_Y.equals((String) contractInfo.get(CLM_BASE_CHRG_FLG))) {
//            Map<String, Object> result = getPriceEffectiveBaseMap(glblCmpyCd, (BigDecimal) contractInfo.get(CLM_DS_CONTR_DTL_PK), cpoOrdTsYYYYMMDD);
//            if (result == null) {
//                msgMap.addXxMsgId(NSZM0618E);
//                return;
//            }
//
//            if (!isCheckPriceEffectiveBaseStatus(result)) {
//                setOutputHoldInfo(param);
//                return;
//            }
//        }
//
//        // Meter Status check
//        if (ZYPConstant.FLG_ON_Y.equals((String) contractInfo.get(CLM_USG_CHRG_FLG))) {
//            List<Map<String, Object>> resultList = getMeterList(glblCmpyCd, (BigDecimal) contractInfo.get(CLM_DS_CONTR_DTL_PK));
//            if (resultList == null || resultList.size() == 0) {
//                msgMap.addXxMsgId(NSZM0619E);
//                return;
//            } else {
//                if (!isCheckMeterStatus(resultList)) {
//                    setOutputHoldInfo(param);
//                    return;
//                }
//            }
//        } else {
//            return;
//        }
//
//        // Price Effective(Usage) Status check
//        List<Map<String, Object>> resultList = getPriceEffectiveUsageList(glblCmpyCd, (BigDecimal) contractInfo.get(CLM_DS_CONTR_DTL_PK), cpoOrdTsYYYYMMDD);
//        if (resultList == null || resultList.size() == 0) {
//            msgMap.addXxMsgId(NSZM0618E);
//            return;
//        } else {
//            if (!isCheckPriceEffectiveUsageStatus(resultList)) {
//                setOutputHoldInfo(param);
//                return;
//            }
//        }
        // END 2018/08/16 M.Naito [QC#27249, DEL]
    }

    // START 2018/08/16 M.Naito [QC#27249, MOD]
//    private Map<String, Object> getContractInfoMap(String glblCmpyCd, BigDecimal svcMachMstrPk, String cpoOrdTsYYYYMMDD) {
    private List<Map<String, Object>> getContractInfoMap(String glblCmpyCd, BigDecimal svcMachMstrPk, String cpoOrdTsYYYYMMDD) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("svcMachMdtrPk", svcMachMstrPk);
        param.put("cpoOrdTsYYYYMMDD", cpoOrdTsYYYYMMDD);
        // add start 2016/10/19 CSA Defect#15344
        param.put("dsContrCatgWty", DS_CONTR_CATG.WARRANTY);
        // add end 2016/10/19 CSA Defect#15344

//        return (Map<String, Object>) this.ssmBatchClient.queryObject("getContractInfo", param);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getContractInfo", param);
    }
    // END 2018/08/16 M.Naito [QC#27249, MOD]

    private boolean isCheckContractStatus(String glblCmpyCd, BigDecimal dsContrPk) {
        DS_CONTR_STS_VTMsg tMsg = getDsContrStsV(glblCmpyCd, dsContrPk);

        if (tMsg != null) {
            if (!isDsContrCtrlStsEntered(tMsg.dsContrCtrlStsCd.getValue())) {
                return false;
            }
        }
        return true;
    }

    private DS_CONTR_STS_VTMsg getDsContrStsV(String glblCmpyCd, BigDecimal dsContrPk) {
        DS_CONTR_STS_VTMsg param = new DS_CONTR_STS_VTMsg();
        param.setSQLID("001");
        param.setConditionValue("glblCmpyCd01", glblCmpyCd);
        param.setConditionValue("dsContrPk01", dsContrPk);
        param.setConditionValue("ettlAvalFlg01", ZYPConstant.FLG_OFF_N);

        DS_CONTR_STS_VTMsgArray result = (DS_CONTR_STS_VTMsgArray) S21ApiTBLAccessor.findByCondition(param);

        if (result.getValidCount() > 0) {
            return (DS_CONTR_STS_VTMsg) result.get(0);
        }
        return null;
    }

    private boolean isCheckMachineStatus(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_DTL_STS_VTMsg tMsg = getDsContrDtlStsV(glblCmpyCd, dsContrDtlPk);

        if (tMsg != null) {
            if (!isDsContrCtrlStsEntered(tMsg.dsContrCtrlStsCd.getValue())) {
                return false;
            }
        }
        return true;
    }

    private DS_CONTR_DTL_STS_VTMsg getDsContrDtlStsV(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_DTL_STS_VTMsg param = new DS_CONTR_DTL_STS_VTMsg();
        param.setSQLID("001");
        param.setConditionValue("glblCmpyCd01", glblCmpyCd);
        param.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        param.setConditionValue("ettlAvalFlg01", ZYPConstant.FLG_OFF_N);

        DS_CONTR_DTL_STS_VTMsgArray result = (DS_CONTR_DTL_STS_VTMsgArray) S21ApiTBLAccessor.findByCondition(param);

        if (result.getValidCount() > 0) {
            return (DS_CONTR_DTL_STS_VTMsg) result.get(0);
        }
        return null;
    }

    private boolean isCheckPriceEffectiveBaseStatus(Map<String, Object> result) {

        boolean rtnFlag = true;
        if (result != null) {
            if (ZYPConstant.FLG_OFF_N.equals((String) result.get(CLM_ETTL_AVAL_FLG))) {
                if (!isDsContrCtrlStsEntered((String) result.get(CLM_DS_CONTR_CTRL_STS_CD))) {
                    return false;
                }
            }
        }
        return rtnFlag;
    }

    private Map<String, Object> getPriceEffectiveBaseMap(String glblCmpyCd, BigDecimal dsContrDtlPk, String cpoOrdTsYYYYMMDD) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("cpoOrdTsYYYYMMDD", cpoOrdTsYYYYMMDD);

        return (Map<String, Object>) this.ssmBatchClient.queryObject("getPriceEffectiveBase", param);
    }

    private boolean isCheckMeterStatus(List<Map<String, Object>> resultList) {

        boolean rtnFlag = true;
        if (resultList != null) {
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, Object> result = resultList.get(i);
                if (ZYPConstant.FLG_OFF_N.equals((String) result.get(CLM_ETTL_AVAL_FLG)) && !isDsContrCtrlStsEntered((String) result.get(CLM_DS_CONTR_CTRL_STS_CD))) {
                    rtnFlag = false;
                    break;
                }
            }
        }
        return rtnFlag;
    }

    private List<Map<String, Object>> getMeterList(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrDtlPk", dsContrDtlPk);
        return this.ssmBatchClient.queryObjectList("getMeter", param);
    }

    private List<Map<String, Object>> getPriceEffectiveUsageList(String glblCmpyCd, BigDecimal dsContrDtlPk, String cpoOrdTsYYYYMMDD) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("cpoOrdTsYYYYMMDD", cpoOrdTsYYYYMMDD);
        return this.ssmBatchClient.queryObjectList("getPriceEffectiveUsage", param);
    }

    private boolean isCheckPriceEffectiveUsageStatus(List<Map<String, Object>> resultList) {

        boolean rtnFlag = true;
        if (resultList != null) {
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, Object> result = resultList.get(i);
                if (ZYPConstant.FLG_OFF_N.equals((String) result.get(CLM_ETTL_AVAL_FLG)) && !isDsContrCtrlStsEntered((String) result.get(CLM_DS_CONTR_CTRL_STS_CD))) {
                    rtnFlag = false;
                    break;
                }
            }
        }
        return rtnFlag;
    }

    private void setOutputHoldInfo(NWXC005001ValidationBean param) {
        ZYPEZDItemValueSetter.setValue(param.getInputPMsg().cpoOrdNum_O, param.getInputPMsg().cpoOrdNum_I.getValue());
        ZYPEZDItemValueSetter.setValue(param.getInputPMsg().cpoDtlLineNum_O, param.getInputPMsg().cpoDtlLineNum_I.getValue());
        ZYPEZDItemValueSetter.setValue(param.getInputPMsg().cpoDtlLineSubNum_O, param.getInputPMsg().cpoDtlLineSubNum_I.getValue());
        ZYPEZDItemValueSetter.setValue(param.getInputPMsg().hldRsnCd, HLD_RSN.CONTRACT_STATUS_HOLD);
    }

    private boolean isDsContrCtrlStsEntered(String dsContrCtrlSts) {
        if (DS_CONTR_CTRL_STS.ENTERED.equals(dsContrCtrlSts)) {
            return true;
        }
        return false;
    }

    // START 2016/09/28 A.Kohinata [QC#12898, ADD]
    private boolean isFleetContract(String glblCmpyCd, String dsContrNum) {
        DS_CONTRTMsg dsContrTMsg = new DS_CONTRTMsg();
        dsContrTMsg.setSQLID("003");
        dsContrTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        dsContrTMsg.setConditionValue("dsContrNum01", dsContrNum);
        DS_CONTRTMsgArray dsContrTMsgArray = (DS_CONTRTMsgArray) S21ApiTBLAccessor.findByCondition(dsContrTMsg);
        if (dsContrTMsgArray.getValidCount() > 0 && DS_CONTR_CATG.FLEET.equals(dsContrTMsgArray.no(0).dsContrCatgCd.getValue())) {
            return true;
        }
        return false;
    }

    // START 2018/08/16 M.Naito [QC#27249, MOD]
//    private Map<String, Object> getFleetContractInfoMap(String glblCmpyCd, String dsContrNum, String cpoOrdTsYYYYMMDD) {
    private List<Map<String, Object>> getFleetContractInfoMap(String glblCmpyCd, String dsContrNum, String cpoOrdTsYYYYMMDD) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrNum", dsContrNum);
        param.put("cpoOrdTsYYYYMMDD", cpoOrdTsYYYYMMDD);
        param.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.FLEET);

//        return (Map<String, Object>) this.ssmBatchClient.queryObject("getFleetContractInfo", param);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getFleetContractInfo", param);
    }
    // END 2018/08/16 M.Naito [QC#27249, MOD]
    // END 2016/09/28 A.Kohinata [QC#12898, ADD]
}
