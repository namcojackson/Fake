/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC077001;

import static com.canon.cusa.s21.api.NSZ.NSZC077001.constant.NSZC077001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil.getVarCharConstValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDPBigDecimalItem;
import parts.common.EZDPDateItem;
import parts.common.EZDPStringItem;
import business.db.DS_CONTR_TRKTMsg;
import business.db.DS_CONTR_TRK_RSNTMsg;
import business.parts.NSZC077001PMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_TRK_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Contract Tracking API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/30/2015   Hitachi         K.Kishimoto     Create
 * 02/23/2015   Hitachi         K.Kishimoto     Update          QC#3687
 * 05/31/2016   Hitachi         T.Tomita        Update          QC#1523, 4624
 * 02/23/2018   CITS            M.Naito         Update          QC#21438
 * </pre>
 */
public class NSZC077001 extends S21ApiCommonBase {
    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap = null;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Process Mode */
    private ContrTrkProcMode procMode = null;

    /** Tracking Type */
    private String trkTp = null;

    /**
     * Constructor
     */
    public NSZC077001() {
        super();
    }

    /**
     * Contract Tracking API
     * @param list List<NSZC077001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(List<NSZC077001PMsg> list, final ONBATCH_TYPE onBatchType) {

        for (NSZC077001PMsg param : list) {
            execute(param, onBatchType);
        }
    }

    /**
     * Contract Tracking API
     * @param pMsg {@link NSZC077001PMsg}
     * @param onBatTp Online Batch Type
     */
    public void execute(NSZC077001PMsg pMsg, final ONBATCH_TYPE onBatTp) {

        init(pMsg, onBatTp);
        if (mandatoryCheck(pMsg)) {
            return;
        }
        doProcess(pMsg);
    }

    private void init(NSZC077001PMsg pMsg, final ONBATCH_TYPE onBatTp) {

        msgMap = new S21ApiMessageMap(pMsg);

        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    }

    private void doProcess(NSZC077001PMsg pMsg) {
        String befSts = getBefSts(pMsg);
        String aftSts = getAftSts(pMsg);
        if (befSts.equals(aftSts)) {
            return;
        }
        //Add Start 02/23/2016 <QC#3687>
        String rsnCd = getRsnCd(pMsg);
        //Add End   02/23/2016 <QC#3687>
        String notes = getNoets(pMsg);
        String psnNm = getPsnNm(pMsg);

        DS_CONTR_TRKTMsg inMsg = new DS_CONTR_TRKTMsg();
        setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inMsg.dsContrTrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_TRK_SQ));
        setValue(inMsg.dsContrTrkTpCd, pMsg.dsContrTrkTpCd);
        setValue(inMsg.dsContrPk, pMsg.dsContrPk);
        setValue(inMsg.dsContrDtlPk, pMsg.dsContrDtlPk);
        setValue(inMsg.dsContrBllgMtrPk, pMsg.dsContrBllgMtrPk);
        setValue(inMsg.dsContrPrcEffPk, pMsg.dsContrPrcEffPk);
        setValue(inMsg.dsContrFromStsCd, befSts);
        setValue(inMsg.dsContrToStsCd, aftSts);
        //Mod Start 02/23/2016 <QC#3687>
        setValue(inMsg.stsMemoRsnCd, rsnCd);
        //Mod End   02/23/2016 <QC#3687>
        setValue(inMsg.stsMemoTxt, notes);
        setValue(inMsg.stsMemoUpdTs, ZYPDateUtil.getCurrentSystemTime(PROCESS_TS_FMT));
        setValue(inMsg.stsMemoUpdPsnCd, pMsg.stsMemoUpdPsnCd);
        setValue(inMsg.stsMemoUpdFullPsnNm, psnNm);
        setValue(inMsg.contrPrcEffFromDt, pMsg.contrPrcEffFromDt);
        setValue(inMsg.contrPrcEffThruDt, pMsg.contrPrcEffThruDt);
        setValue(inMsg.prntDsContrTrkPk, pMsg.prntDsContrTrkPk);
        S21ApiTBLAccessor.insert(inMsg);
        if (DS_CONTR_TRK_TP.CONTRACT_HEADER.equals(pMsg.dsContrTrkTpCd.getValue())) {
            setValue(pMsg.prntDsContrTrkPk, inMsg.dsContrTrkPk);
        }
    }

    private String getBefSts(NSZC077001PMsg pMsg) {
        String sqlId = null;
        Map<String, Object> param = new HashMap<String, Object>();
        param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("dsContrTrkTpCd", this.trkTp);
        if (DS_CONTR_TRK_TP.CONTRACT_HEADER.equals(this.trkTp)) {
            sqlId = "getBefStsTp1";
            param.put("dsContrPk", pMsg.dsContrPk.getValue());
        } else if (DS_CONTR_TRK_TP.BASE_CHARGE.equals(this.trkTp)) {
            sqlId = "getBefStsTp2";
            param.put("dsContrDtlPk", pMsg.dsContrDtlPk.getValue());
        } else if (DS_CONTR_TRK_TP.USAGE_CHARGE.equals(this.trkTp)) {
            sqlId = "getBefStsTp3";
            param.put("dsContrDtlPk", pMsg.dsContrDtlPk.getValue());
            param.put("dsContrBllgMtrPk", pMsg.dsContrBllgMtrPk.getValue());
        } else if (DS_CONTR_TRK_TP.BASE_CHARGE_PE.equals(this.trkTp)) {
            sqlId = "getBefStsTp4";
            param.put("dsContrDtlPk", pMsg.dsContrDtlPk.getValue());
            param.put("contrPrcEffFromDt", pMsg.contrPrcEffFromDt.getValue());
        } else if (DS_CONTR_TRK_TP.USAGE_CHARGE_PE.equals(this.trkTp)) {
            sqlId = "getBefStsTp5";
            param.put("dsContrDtlPk", pMsg.dsContrDtlPk.getValue());
            param.put("dsContrBllgMtrPk", pMsg.dsContrBllgMtrPk.getValue());
            param.put("contrPrcEffFromDt", pMsg.contrPrcEffFromDt.getValue());
        }
        String retSts = (String) ssmBatchClient.queryObject(sqlId, param);
        if (retSts == null) {
            retSts = "";
        }
        return retSts;
    }

    private String getAftSts(NSZC077001PMsg pMsg) {
        String sqlId = null;
        Map<String, Object> param = new HashMap<String, Object>();
        param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("dsContrTrkTpCd", this.trkTp);
        if (DS_CONTR_TRK_TP.CONTRACT_HEADER.equals(this.trkTp)) {
            sqlId = "getAftStsTp1";
            param.put("dsContrPk", pMsg.dsContrPk.getValue());
        } else if (DS_CONTR_TRK_TP.BASE_CHARGE.equals(this.trkTp)) {
            sqlId = "getAftStsTp2";
            param.put("dsContrDtlPk", pMsg.dsContrDtlPk.getValue());
        } else if (DS_CONTR_TRK_TP.USAGE_CHARGE.equals(this.trkTp)) {
            sqlId = "getAftStsTp3";
            param.put("dsContrBllgMtrPk", pMsg.dsContrBllgMtrPk.getValue());
        } else if (DS_CONTR_TRK_TP.BASE_CHARGE_PE.equals(this.trkTp)) {
            sqlId = "getAftStsTp4";
            param.put("dsContrPrcEffPk", pMsg.dsContrPrcEffPk.getValue());
        } else if (DS_CONTR_TRK_TP.USAGE_CHARGE_PE.equals(this.trkTp)) {
            sqlId = "getAftStsTp4";
            param.put("dsContrPrcEffPk", pMsg.dsContrPrcEffPk.getValue());
        }
        String retSts = (String) ssmBatchClient.queryObject(sqlId, param);
        if (retSts == null) {
            retSts = "";
        }
        return retSts;
    }

    // Add Start 02/23/2016 <QC#3687>
    private String getRsnCd(NSZC077001PMsg pMsg) {
        String rsnCd = null;
        switch (this.procMode) {
            case AUTO_APPROVAL:
                rsnCd = getVarCharConstValue(NSZC0770_RSN_MODE_02, pMsg.glblCmpyCd.getValue());
                break;
            case CONTRACT_MODE_CHANGE:
                rsnCd = getVarCharConstValue(NSZC0770_RSN_MODE_03, pMsg.glblCmpyCd.getValue());
                break;
            case METER_READ_VALIDATION:
                rsnCd = getVarCharConstValue(NSZC0770_RSN_MODE_04, pMsg.glblCmpyCd.getValue());
                break;
            case METER_VALIDATION_STATUS_REVERT:
                rsnCd = getVarCharConstValue(NSZC0770_RSN_MODE_05, pMsg.glblCmpyCd.getValue());
                break;
            case ANNUAL_ESCALATION:
                rsnCd = getVarCharConstValue(NSZC0770_RSN_MODE_06, pMsg.glblCmpyCd.getValue());
                break;
            case AUTO_RENEWAL:
                rsnCd = getVarCharConstValue(NSZC0770_RSN_MODE_07, pMsg.glblCmpyCd.getValue());
                break;
            case BILLING_HOLD:
                rsnCd = getVarCharConstValue(NSZC0770_RSN_MODE_08, pMsg.glblCmpyCd.getValue());
                break;
            // add start 2016/05/31 CSA Defect#1523, 4624
            case PREVIEW_BILLING:
                rsnCd = getVarCharConstValue(NSZC0770_RSN_MODE_09, pMsg.glblCmpyCd.getValue());
                break;
            case PREVIEW_BILLING_ACTION:
                rsnCd = getVarCharConstValue(NSZC0770_RSN_MODE_10, pMsg.glblCmpyCd.getValue());
                break;
            // add end 2016/05/31 CSA Defect#1523, 4624
            default:
                rsnCd = pMsg.stsMemoRsnCd.getValue();
                break;
        }
        return rsnCd;
    }
    // Add End   02/23/2016 <QC#3687>

    private String getNoets(NSZC077001PMsg pMsg) {
        String notes = null;
        if (hasValue(pMsg.prntDsContrTrkPk)) {
            return notes;
        }
        switch (this.procMode) {
            case USER_OPERATION:
                if (!hasValue(pMsg.stsMemoTxt)) {
                    notes = getRsnDescTxt(pMsg);
                } else {
                    notes = pMsg.stsMemoTxt.getValue();
                }
                break;
            case AUTO_APPROVAL:
                notes = getVarCharConstValue(NSZC0770_NOTES_MODE_02, pMsg.glblCmpyCd.getValue());
                break;
            case CONTRACT_MODE_CHANGE:
                notes = getVarCharConstValue(NSZC0770_NOTES_MODE_03, pMsg.glblCmpyCd.getValue());
                break;
            case METER_READ_VALIDATION:
                notes = getVarCharConstValue(NSZC0770_NOTES_MODE_04, pMsg.glblCmpyCd.getValue());
                break;
            case METER_VALIDATION_STATUS_REVERT:
                notes = getVarCharConstValue(NSZC0770_NOTES_MODE_05, pMsg.glblCmpyCd.getValue());
                break;
            // Add Start 02/23/2016 <QC#3687>
            // add start 2018/02/23 CSA Defect#21438
//            case ANNUAL_ESCALATION:
            case AUTO_RENEWAL:
                notes = getVarCharConstValue(NSZC0770_NOTES_MODE_06, pMsg.glblCmpyCd.getValue());
                break;
//            case AUTO_RENEWAL:
            case ANNUAL_ESCALATION:
                notes = getVarCharConstValue(NSZC0770_NOTES_MODE_07, pMsg.glblCmpyCd.getValue());
                break;
            // add end 2018/02/23 CSA Defect#21438
            case BILLING_HOLD:
                notes = getVarCharConstValue(NSZC0770_NOTES_MODE_08, pMsg.glblCmpyCd.getValue());
                break;
            // Add End   02/23/2016 <QC#3687>
            // add start 2016/05/31 CSA Defect#1523, 4624
            case PREVIEW_BILLING:
                notes = getVarCharConstValue(NSZC0770_NOTES_MODE_09, pMsg.glblCmpyCd.getValue());
                break;
            case PREVIEW_BILLING_ACTION:
                notes = getVarCharConstValue(NSZC0770_NOTES_MODE_10, pMsg.glblCmpyCd.getValue());
                break;
            // add end 2016/05/31 CSA Defect#1523, 4624
            default:
                break;
        }
        return notes;
    }

    // mod start 2016/05/31 CSA Defect#1523, 4624
    private String getPsnNm(NSZC077001PMsg pMsg) {
        String psnCd = null;
        String psnNm = null;
        switch (this.procMode) {
            case USER_OPERATION:
                psnNm = getPsnNm(pMsg.glblCmpyCd.getValue(), pMsg.stsMemoUpdPsnCd.getValue());
                if (!hasValue(psnNm)) {
                    psnNm = pMsg.stsMemoUpdPsnCd.getValue();
                }
                break;
            case PREVIEW_BILLING:
                psnCd = S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId();
                psnNm = getPsnNm(pMsg.glblCmpyCd.getValue(), psnCd);
                if (!hasValue(psnNm)) {
                    psnNm = getVarCharConstValue(NSZC0770_PERSON_SYSTEM, pMsg.glblCmpyCd.getValue());
                }
                break;
            case PREVIEW_BILLING_ACTION:
                psnCd = S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId();
                psnNm = getPsnNm(pMsg.glblCmpyCd.getValue(), psnCd);
                if (!hasValue(psnNm)) {
                    psnNm = getVarCharConstValue(NSZC0770_PERSON_SYSTEM, pMsg.glblCmpyCd.getValue());
                }
                break;
            default:
                psnNm = getVarCharConstValue(NSZC0770_PERSON_SYSTEM, pMsg.glblCmpyCd.getValue());
                break;
        }
        return psnNm;
    }
    // mod end 2016/05/31 CSA Defect#1523, 4624

    // mod start 2016/05/31 CSA Defect#1523, 4624
    private String getRsnDescTxt(NSZC077001PMsg pMsg) {
        String dsnDescTxt = null;
        if (!hasValue(pMsg.stsMemoRsnCd)) {
            return dsnDescTxt;
        }

        DS_CONTR_TRK_RSNTMsg inMsg = new DS_CONTR_TRK_RSNTMsg();
        setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inMsg.dsContrTrkRsnCd, pMsg.stsMemoRsnCd);
        DS_CONTR_TRK_RSNTMsg rtnMsg = (DS_CONTR_TRK_RSNTMsg) S21ApiTBLAccessor.findByKey(inMsg);
        if (rtnMsg != null) {
            dsnDescTxt = rtnMsg.dsContrTrkRsnDescTxt.getValue();
        }
        return dsnDescTxt;
    }
    // mod end 2016/05/31 CSA Defect#1523, 4624

    private boolean mandatoryCheck(NSZC077001PMsg pMsg) {

        mandatoryCheck(pMsg, pMsg.glblCmpyCd, NSZM0401E, MSG_GLBL_CMPY_CD, MSG_INFO_IN_PRM);
        mandatoryCheck(pMsg, pMsg.xxModeCd, NSZM0401E, MSG_XX_MODE_CD, MSG_INFO_IN_PRM);
        mandatoryCheck(pMsg, pMsg.dsContrTrkTpCd, NSZM0401E, MSG_DS_CONTR_TRK_TP_CD, MSG_INFO_IN_PRM);
        mandatoryCheck(pMsg, pMsg.dsContrPk, NSZM0401E, MSG_DS_CONTR_PK, MSG_INFO_IN_PRM);
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            return true;
        }
        this.procMode = ContrTrkProcMode.codeOf(pMsg.xxModeCd.getValue());
        this.trkTp = pMsg.dsContrTrkTpCd.getValue();
        if (DS_CONTR_TRK_TP.BASE_CHARGE.equals(this.trkTp)) {
            mandatoryCheck(pMsg, pMsg.dsContrDtlPk, NSZM0401E, MSG_DS_CONTR_DTL_PK, MSG_INFO_IN_PRM);
        } else if (DS_CONTR_TRK_TP.USAGE_CHARGE.equals(this.trkTp)) {
            mandatoryCheck(pMsg, pMsg.dsContrDtlPk, NSZM0401E, MSG_DS_CONTR_DTL_PK, MSG_INFO_IN_PRM);
            mandatoryCheck(pMsg, pMsg.dsContrBllgMtrPk, NSZM0401E, MSG_DS_CONTR_BLLG_MTR_PK, MSG_INFO_IN_PRM);
        } else if (DS_CONTR_TRK_TP.BASE_CHARGE_PE.equals(this.trkTp)) {
            mandatoryCheck(pMsg, pMsg.dsContrDtlPk, NSZM0401E, MSG_DS_CONTR_DTL_PK, MSG_INFO_IN_PRM);
            mandatoryCheck(pMsg, pMsg.dsContrPrcEffPk, NSZM0401E, MSG_DS_CONTR_PRC_EFF_PK, MSG_INFO_IN_PRM);
            mandatoryCheck(pMsg, pMsg.contrPrcEffFromDt, NSZM0401E, MSG_CONTR_PRC_EFF_FROM_DT, MSG_INFO_IN_PRM);
            mandatoryCheck(pMsg, pMsg.contrPrcEffThruDt, NSZM0401E, MSG_CONTR_PRC_EFF_THRU_DT, MSG_INFO_IN_PRM);
        } else if (DS_CONTR_TRK_TP.USAGE_CHARGE_PE.equals(this.trkTp)) {
            mandatoryCheck(pMsg, pMsg.dsContrDtlPk, NSZM0401E, MSG_DS_CONTR_DTL_PK, MSG_INFO_IN_PRM);
            mandatoryCheck(pMsg, pMsg.dsContrBllgMtrPk, NSZM0401E, MSG_DS_CONTR_BLLG_MTR_PK, MSG_INFO_IN_PRM);
            mandatoryCheck(pMsg, pMsg.dsContrPrcEffPk, NSZM0401E, MSG_DS_CONTR_PRC_EFF_PK, MSG_INFO_IN_PRM);
            mandatoryCheck(pMsg, pMsg.contrPrcEffFromDt, NSZM0401E, MSG_CONTR_PRC_EFF_FROM_DT, MSG_INFO_IN_PRM);
            mandatoryCheck(pMsg, pMsg.contrPrcEffThruDt, NSZM0401E, MSG_CONTR_PRC_EFF_THRU_DT, MSG_INFO_IN_PRM);
        }

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            return true;
        }
        return false;
    }

    private boolean mandatoryCheck(NSZC077001PMsg pMsg, EZDPDateItem item, String msgId, String itemName, String infoInPrm) {
        if (!hasValue(item)) {
            setErrMsg(pMsg, msgId, itemName, infoInPrm);
            return false;
        }
        return true;
    }

    private boolean mandatoryCheck(NSZC077001PMsg pMsg, EZDPStringItem item, String msgId, String itemName, String infoInPrm) {
        if (!hasValue(item)) {
            setErrMsg(pMsg, msgId, itemName, infoInPrm);
            return false;
        }
        return true;
    }

    private boolean mandatoryCheck(NSZC077001PMsg pMsg, EZDPBigDecimalItem item, String msgId, String itemName, String infoInPrm) {
        if (!hasValue(item)) {
            setErrMsg(pMsg, msgId, itemName, infoInPrm);
            return false;
        }
        return true;
    }

    private void setErrMsg(NSZC077001PMsg pMsg, String msgId, String... msgPrm) {
        msgMap.addXxMsgIdWithPrm(msgId, msgPrm);
        msgMap.flush();
    }

    // add start 2016/05/31 CSA Defect#1523, 4624
    private String getPsnNm(String glblCmpyCd, String psnCd) {
        if (!hasValue(psnCd)) {
            return null;
        }
        String psnNm = null;
        Map<String, Object> param = new HashMap<String, Object>();
        param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("psnCd", psnCd);
        psnNm = (String) ssmBatchClient.queryObject("getPsnNm", param);
        return psnNm;
    }
    // add end 2016/05/31 CSA Defect#1523, 4624
}
