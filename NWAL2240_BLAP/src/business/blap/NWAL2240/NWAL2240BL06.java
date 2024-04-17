/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2240;

import static business.blap.NWAL2240.constant.NWAL2240Constant.CTAC_PT_TP_CD_FAX;
import static business.blap.NWAL2240.constant.NWAL2240Constant.CTAC_PT_TP_CD_MAIL;
import static business.blap.NWAL2240.constant.NWAL2240Constant.CTAC_PT_TP_CD_PHONE;
import static business.blap.NWAL2240.constant.NWAL2240Constant.MESSAGE_KIND_ERROR;
import static business.blap.NWAL2240.constant.NWAL2240Constant.MODE_NEW;
import static business.blap.NWAL2240.constant.NWAL2240Constant.NZZM0002I;
import static business.blap.NWAL2240.constant.NWAL2240Constant.ZZZM9004E;
import static business.blap.NWAL2240.constant.NWAL2240Constant.ZZZM9006E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL2240.common.NWAL2240CommonLogic;
import business.db.DS_CPO_CTAC_PSNTMsg;
import business.db.DS_IMPT_ORD_CTAC_PSNTMsg;
import business.db.DS_IMPT_ORD_DELY_INFOTMsg;
import business.db.DS_IMPT_ORD_ISTL_INFOTMsg;
import business.db.DS_IMPT_ORD_SITE_SRVYTMsg;
import business.db.TECH_MSTRTMsg;
import business.parts.NMZC002001PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC002001.NMZC002001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_DELY_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NWAL2240BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/13   Fujitsu         S.Ohki          Create          N/A
 * 2017/08/25   Fujitsu         S.Iidaka        Update          QC#20740-1
 *</pre>
 */
public class NWAL2240BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
//          Delete because it is unused.
//            String screenAplID = cMsg.getScreenAplID();
//            NWAL2240CMsg bizMsg = (NWAL2240CMsg) cMsg;
//            NWAL2240SMsg glblMsg = (NWAL2240SMsg) sMsg;
//
//
//            if ("NWAL2240Scrn00_CMN_Submit".equals(screenAplID)) {
//                doProcess_NWAL2240Scrn00_CMN_Submit(bizMsg, glblMsg);
//
//            } else {
//                return;
//            }
            return;
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }
//  Delete because it is unused.
//    private void doProcess_NWAL2240Scrn00_CMN_Submit(NWAL2240CMsg bizMsg, NWAL2240SMsg sMsg) {
//
//        BigDecimal dsImptOrdPk = null;
//        BigDecimal dsImptOrdConfigPk = null;
//
//        S21SsmEZDResult dsImptOrd = NWAL2240Query.getInstance().getDsImptOrd(bizMsg);
//
//        if (dsImptOrd.isCodeNormal()) {
//            // Delivery Date
//            Map<String, Object> map = (Map<String, Object>) dsImptOrd.getResultObject();
//
//            dsImptOrdPk = (BigDecimal) map.get("DS_IMPT_ORD_PK");
//        } else {
//            bizMsg.ordSrcRefNum_H0.setErrorInfo(1, ZZZM9006E, new String[] {"Order Source Reference Number" });
//            return;
//        }
//
//        // Check Configuration Number
//        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdPosnNum_H0)) {
//            S21SsmEZDResult ssmResult = NWAL2240Query.getInstance().getConfigurationNum(bizMsg);
//
//            if (ssmResult.isCodeNotFound()) {
//                bizMsg.dsOrdPosnNum_H0.setErrorInfo(1, ZZZM9006E, new String[] {"Configuration Num" });
//                return;
//            } else {
//                Map<String, Object> resultMap = (Map<String, Object>) ssmResult.getResultObject();
//
//                dsImptOrdConfigPk = (BigDecimal) resultMap.get("DS_IMPT_ORD_CONFIG_PK");
//            }
//        }
//
//        // Check Technician Code
//        if (ZYPCommonFunc.hasValue(bizMsg.istlTechCd_DI)) {
//            TECH_MSTRTMsg techTMsg = NWAL2240Query.getInstance().getTechMstr(bizMsg);
//            if (techTMsg == null) {
//                bizMsg.setMessageInfo(ZZZM9006E, new String[] {"Install Technician" });
//                return;
//            }
//        }
//
//        // Delivery
//        if (ZYPCommonFunc.hasValue(bizMsg.dsImptOrdDelyInfoPk_DI)) {
//
//            // Exclusive process
//            DS_IMPT_ORD_DELY_INFOTMsg dsImptOrdDelyInfoMsg = new DS_IMPT_ORD_DELY_INFOTMsg();
//            ZYPEZDItemValueSetter.setValue(dsImptOrdDelyInfoMsg.glblCmpyCd, getGlobalCompanyCode());
//            ZYPEZDItemValueSetter.setValue(dsImptOrdDelyInfoMsg.dsImptOrdDelyInfoPk, bizMsg.dsImptOrdDelyInfoPk_DI);
//            ZYPEZDItemValueSetter.setValue(dsImptOrdDelyInfoMsg.ezUpTime, bizMsg.ezUpTime_DI);
//            ZYPEZDItemValueSetter.setValue(dsImptOrdDelyInfoMsg.ezUpTimeZone, bizMsg.ezUpTimeZone_DI);
//            DS_IMPT_ORD_DELY_INFOTMsg updDsImptOrdDelyInfoMsg = (DS_IMPT_ORD_DELY_INFOTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(dsImptOrdDelyInfoMsg);
//
//            if (updDsImptOrdDelyInfoMsg != null) {
//
//                if (!ZYPDateUtil.isSameTimeStamp(updDsImptOrdDelyInfoMsg.ezUpTime.getValue(), updDsImptOrdDelyInfoMsg.ezUpTimeZone.getValue(), dsImptOrdDelyInfoMsg.ezUpTime.getValue(), dsImptOrdDelyInfoMsg.ezUpTimeZone.getValue())) {
//                    bizMsg.setMessageInfo(ZZZM9004E);
//                    return;
//                }
//
//                setDsImptOrdDelyInfo(updDsImptOrdDelyInfoMsg, bizMsg, dsImptOrdPk, dsImptOrdConfigPk);
//
//                EZDTBLAccessor.update(updDsImptOrdDelyInfoMsg);
//
//            } else {
//                bizMsg.setMessageInfo(ZZZM9004E);
//                return;
//            }
//        } else {
//
//            DS_IMPT_ORD_DELY_INFOTMsg insDsImptOrdDelyInfoMsg = new DS_IMPT_ORD_DELY_INFOTMsg();
//
//            ZYPEZDItemValueSetter.setValue(insDsImptOrdDelyInfoMsg.dsImptOrdDelyInfoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_DELY_INFO_SQ));
//            setDsImptOrdDelyInfo(insDsImptOrdDelyInfoMsg, bizMsg, dsImptOrdPk, dsImptOrdConfigPk);
//
//            EZDTBLAccessor.insert(insDsImptOrdDelyInfoMsg);
//        }
//
//        // Install
//        if (ZYPCommonFunc.hasValue(bizMsg.dsImptOrdIstlInfoPk_DI)) {
//
//            DS_IMPT_ORD_ISTL_INFOTMsg dsImptOrdIstlInfoMsg = new DS_IMPT_ORD_ISTL_INFOTMsg();
//            ZYPEZDItemValueSetter.setValue(dsImptOrdIstlInfoMsg.glblCmpyCd, getGlobalCompanyCode());
//            ZYPEZDItemValueSetter.setValue(dsImptOrdIstlInfoMsg.dsImptOrdIstlInfoPk, bizMsg.dsImptOrdIstlInfoPk_DI);
//            ZYPEZDItemValueSetter.setValue(dsImptOrdIstlInfoMsg.ezUpTime, bizMsg.ezUpTime_IS);
//            ZYPEZDItemValueSetter.setValue(dsImptOrdIstlInfoMsg.ezUpTimeZone, bizMsg.ezUpTimeZone_IS);
//
//            DS_IMPT_ORD_ISTL_INFOTMsg updDsImptOrdIstlInfoMsg = (DS_IMPT_ORD_ISTL_INFOTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(dsImptOrdIstlInfoMsg);
//            if (updDsImptOrdIstlInfoMsg != null) {
//                if (!ZYPDateUtil.isSameTimeStamp(updDsImptOrdIstlInfoMsg.ezUpTime.getValue(), updDsImptOrdIstlInfoMsg.ezUpTimeZone.getValue(), dsImptOrdIstlInfoMsg.ezUpTime.getValue(), dsImptOrdIstlInfoMsg.ezUpTimeZone.getValue())) {
//                    bizMsg.setMessageInfo(ZZZM9004E);
//                    return;
//                }
//
//                setDsImptOrdIstlInfo(updDsImptOrdIstlInfoMsg, bizMsg, dsImptOrdPk, dsImptOrdConfigPk);
//
//                EZDTBLAccessor.update(updDsImptOrdIstlInfoMsg);
//
//            } else {
//                bizMsg.setMessageInfo(ZZZM9004E);
//                return;
//            }
//        } else {
//            DS_IMPT_ORD_ISTL_INFOTMsg insDsImptOrdIstlInfoMsg = new DS_IMPT_ORD_ISTL_INFOTMsg();
//
//            ZYPEZDItemValueSetter.setValue(insDsImptOrdIstlInfoMsg.dsImptOrdIstlInfoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_ISTL_INFO_SQ));
//            setDsImptOrdIstlInfo(insDsImptOrdIstlInfoMsg, bizMsg, dsImptOrdPk, dsImptOrdConfigPk);
//
//            EZDTBLAccessor.insert(insDsImptOrdIstlInfoMsg);
//        }
//
//        // Site Survey
//        if (ZYPCommonFunc.hasValue(bizMsg.dsImptOrdSiteSrvyPk_SS)) {
//
//            DS_IMPT_ORD_SITE_SRVYTMsg dsImptOrdSiteSrvyMsg = new DS_IMPT_ORD_SITE_SRVYTMsg();
//            ZYPEZDItemValueSetter.setValue(dsImptOrdSiteSrvyMsg.glblCmpyCd, getGlobalCompanyCode());
//            ZYPEZDItemValueSetter.setValue(dsImptOrdSiteSrvyMsg.dsImptOrdSiteSrvyPk, bizMsg.dsImptOrdSiteSrvyPk_SS);
//            ZYPEZDItemValueSetter.setValue(dsImptOrdSiteSrvyMsg.ezUpTime, bizMsg.ezUpTime_SS);
//            ZYPEZDItemValueSetter.setValue(dsImptOrdSiteSrvyMsg.ezUpTimeZone, bizMsg.ezUpTimeZone_SS);
//
//            DS_IMPT_ORD_SITE_SRVYTMsg updDsImptOrdSiteSrvyMsg = (DS_IMPT_ORD_SITE_SRVYTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(dsImptOrdSiteSrvyMsg);
//            if (updDsImptOrdSiteSrvyMsg != null) {
//                if (!ZYPDateUtil.isSameTimeStamp(updDsImptOrdSiteSrvyMsg.ezUpTime.getValue(), updDsImptOrdSiteSrvyMsg.ezUpTimeZone.getValue(), dsImptOrdSiteSrvyMsg.ezUpTime.getValue(), dsImptOrdSiteSrvyMsg.ezUpTimeZone.getValue())) {
//                    bizMsg.setMessageInfo(ZZZM9004E);
//                    return;
//                }
//
//                setDsImptOrdSiteSurvey(updDsImptOrdSiteSrvyMsg, bizMsg, dsImptOrdPk, dsImptOrdConfigPk);
//
//                EZDTBLAccessor.update(updDsImptOrdSiteSrvyMsg);
//
//            } else {
//                bizMsg.setMessageInfo(ZZZM9004E);
//                return;
//            }
//        } else {
//            DS_IMPT_ORD_SITE_SRVYTMsg insDsImptOrdSiteSrvyMsg = new DS_IMPT_ORD_SITE_SRVYTMsg();
//
//            ZYPEZDItemValueSetter.setValue(insDsImptOrdSiteSrvyMsg.dsImptOrdSiteSrvyPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_SITE_SRVY_SQ));
//
//            setDsImptOrdSiteSurvey(insDsImptOrdSiteSrvyMsg, bizMsg, dsImptOrdPk, dsImptOrdConfigPk);
//
//            EZDTBLAccessor.insert(insDsImptOrdSiteSrvyMsg);
//        }
//
//        // Contact
//        NMZC002001PMsg ctacApiPMsg;
//        for (int idx = 0; idx < bizMsg.C.getValidCount(); idx++) {
//
//            if (ZYPCommonFunc.hasValue(bizMsg.C.no(idx).dsImptOrdCtacPsnPk_C0)) {
//
//                DS_IMPT_ORD_CTAC_PSNTMsg dsImptOrdCtacPsnMsg = new DS_IMPT_ORD_CTAC_PSNTMsg();
//                ZYPEZDItemValueSetter.setValue(dsImptOrdCtacPsnMsg.glblCmpyCd, getGlobalCompanyCode());
//                ZYPEZDItemValueSetter.setValue(dsImptOrdCtacPsnMsg.dsImptOrdCtacPsnPk, bizMsg.C.no(idx).dsImptOrdCtacPsnPk_C0);
//                ZYPEZDItemValueSetter.setValue(dsImptOrdCtacPsnMsg.ezUpTime, bizMsg.C.no(idx).ezUpTime_C0);
//                ZYPEZDItemValueSetter.setValue(dsImptOrdCtacPsnMsg.ezUpTimeZone, bizMsg.C.no(idx).ezUpTimeZone_C0);
//
//                DS_IMPT_ORD_CTAC_PSNTMsg updDsImptOrdCtacPsnMsg = (DS_IMPT_ORD_CTAC_PSNTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(dsImptOrdCtacPsnMsg);
//                if (updDsImptOrdCtacPsnMsg != null) {
//
//                    // Change Check
//                    if (!isChangeCheck(bizMsg.C.no(idx), updDsImptOrdCtacPsnMsg)) {
//                        continue;
//                    }
//
//                    if (!ZYPDateUtil.isSameTimeStamp(updDsImptOrdCtacPsnMsg.ezUpTime.getValue(), updDsImptOrdCtacPsnMsg.ezUpTimeZone.getValue(), dsImptOrdCtacPsnMsg.ezUpTime.getValue(), dsImptOrdCtacPsnMsg.ezUpTimeZone.getValue())) {
//                        bizMsg.setMessageInfo(ZZZM9004E);
//                        return;
//                    }
//
//                    setDsImptOrdCtacPsn(updDsImptOrdCtacPsnMsg, bizMsg, dsImptOrdPk, dsImptOrdConfigPk, idx);
//
//                    EZDTBLAccessor.update(updDsImptOrdCtacPsnMsg);
//                }
//
//            } else {
//
//                ctacApiPMsg = new NMZC002001PMsg();
//                DS_IMPT_ORD_CTAC_PSNTMsg insDsImptOrdCtacPsnMsg = new DS_IMPT_ORD_CTAC_PSNTMsg();
//
//                if (!ZYPCommonFunc.hasValue(bizMsg.C.no(idx).dsAcctNum_C0) && !ZYPCommonFunc.hasValue(bizMsg.C.no(idx).locNum_C0)) {
//
//                    if (registContactMst(ctacApiPMsg, bizMsg, idx)) {
//                        ZYPEZDItemValueSetter.setValue(insDsImptOrdCtacPsnMsg.ctacPsnPk, ctacApiPMsg.ctacPsnPk);
//                    } else {
//                        return;
//                    }
//
//                } else if (!bizMsg.C.no(idx).dsAcctNum_C0.getValue().equals(bizMsg.shipToCustAcctCd_H0.getValue()) && !bizMsg.C.no(idx).locNum_C0.getValue().equals(bizMsg.shipToCustLocCd_H0.getValue())) {
//
//                    if (registContactMst(ctacApiPMsg, bizMsg, idx)) {
//                        ZYPEZDItemValueSetter.setValue(insDsImptOrdCtacPsnMsg.ctacPsnPk, ctacApiPMsg.ctacPsnPk);
//                    } else {
//                        return;
//                    }
//
//                } else {
//                    ZYPEZDItemValueSetter.setValue(insDsImptOrdCtacPsnMsg.ctacPsnPk, bizMsg.C.no(idx).ctacPsnPk_C0);
//                }
//
//                if ("E".equals(bizMsg.getMessageKind())) {
//                    return;
//                }
//
//                ZYPEZDItemValueSetter.setValue(insDsImptOrdCtacPsnMsg.dsImptOrdCtacPsnPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_CTAC_PSN_SQ));
//
//                setDsImptOrdCtacPsn(insDsImptOrdCtacPsnMsg, bizMsg, dsImptOrdPk, dsImptOrdConfigPk, idx);
//
//                EZDTBLAccessor.insert(insDsImptOrdCtacPsnMsg);
//            }
//        }
//
//        // Contact Delete
//        List<DS_IMPT_ORD_CTAC_PSNTMsg> delTMsgList = new ArrayList<DS_IMPT_ORD_CTAC_PSNTMsg>();
//
//        for (int idx = 0; idx < sMsg.D.getValidCount(); idx++) {
//
//            DS_CPO_CTAC_PSNTMsg rmvTMsgKey = new DS_CPO_CTAC_PSNTMsg();
//            ZYPEZDItemValueSetter.setValue(rmvTMsgKey.dsCpoCtacPsnPk, sMsg.D.no(idx).dsImptOrdCtacPsnPk_D0);
//            ZYPEZDItemValueSetter.setValue(rmvTMsgKey.glblCmpyCd, getGlobalCompanyCode());
//
//            DS_IMPT_ORD_CTAC_PSNTMsg dsImptOrdCtacPsnMsg = new DS_IMPT_ORD_CTAC_PSNTMsg();
//            ZYPEZDItemValueSetter.setValue(dsImptOrdCtacPsnMsg.glblCmpyCd, getGlobalCompanyCode());
//            ZYPEZDItemValueSetter.setValue(dsImptOrdCtacPsnMsg.dsImptOrdCtacPsnPk, sMsg.D.no(idx).dsImptOrdCtacPsnPk_D0);
//            ZYPEZDItemValueSetter.setValue(dsImptOrdCtacPsnMsg.ezUpTime, sMsg.D.no(idx).ezUpTime_D0);
//            ZYPEZDItemValueSetter.setValue(dsImptOrdCtacPsnMsg.ezUpTimeZone, sMsg.D.no(idx).ezUpTimeZone_D0);
//
//            DS_IMPT_ORD_CTAC_PSNTMsg delDsImptOrdCtacPsnMsg = (DS_IMPT_ORD_CTAC_PSNTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(dsImptOrdCtacPsnMsg);
//
//            if (!ZYPDateUtil.isSameTimeStamp(delDsImptOrdCtacPsnMsg.ezUpTime.getValue(), delDsImptOrdCtacPsnMsg.ezUpTimeZone.getValue(), dsImptOrdCtacPsnMsg.ezUpTime.getValue(), dsImptOrdCtacPsnMsg.ezUpTimeZone.getValue())) {
//                bizMsg.setMessageInfo(ZZZM9004E);
//                return;
//            }
//
//            delTMsgList.add(delDsImptOrdCtacPsnMsg);
//        }
//
//        if (delTMsgList.size() > 0) {
//            int delCnt = S21FastTBLAccessor.removeLogical(delTMsgList.toArray(new DS_IMPT_ORD_CTAC_PSNTMsg[0]));
//            if (delCnt != delTMsgList.size()) {
//                bizMsg.setMessageInfo(ZZZM9004E);
//                return;
//            }
//        }
//
//        if (!bizMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR)) {
//            bizMsg.setMessageInfo(NZZM0002I);
//        }
//    }
//
//    /**
//     * Set DsImptOrdIstl Information Data
//     * @param tMsg DS_IMPT_ORD_ISTL_INFOTMsg
//     * @param bizMsg NWAL2240CMsg
//     * @param dsImptOrdPk BigDecimal
//     * @param dsImptOrdConfigPk BigDecimal
//     */
//    private void setDsImptOrdIstlInfo(DS_IMPT_ORD_ISTL_INFOTMsg tMsg, NWAL2240CMsg bizMsg, BigDecimal dsImptOrdPk, BigDecimal dsImptOrdConfigPk) {
//
//        String chngRqstIstlTmDi = NWAL2240CommonLogic.changeTime24h(bizMsg.rqstIstlTm_DI.getValue(), bizMsg.rqstIstlTm_AP.getValue());
//
//        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdPosnNum_H0)) {
//            ZYPEZDItemValueSetter.setValue(tMsg.dsImptOrdConfigPk, dsImptOrdConfigPk);
//        } else {
//            ZYPEZDItemValueSetter.setValue(tMsg.dsImptOrdConfigPk, (BigDecimal) null);
//        }
//        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
//        ZYPEZDItemValueSetter.setValue(tMsg.dsImptOrdPk, dsImptOrdPk);
//        ZYPEZDItemValueSetter.setValue(tMsg.svcIstlRuleNum, bizMsg.svcIstlRuleNum_DI);
//        ZYPEZDItemValueSetter.setValue(tMsg.istlDivCd, bizMsg.istlDivCd_DI);
//        ZYPEZDItemValueSetter.setValue(tMsg.istlTechCd, bizMsg.istlTechCd_DI);
//        ZYPEZDItemValueSetter.setValue(tMsg.rqstIstlDt, bizMsg.rqstIstlDt_DI);
//        ZYPEZDItemValueSetter.setValue(tMsg.rqstIstlTm, chngRqstIstlTmDi);
//        ZYPEZDItemValueSetter.setValue(tMsg.istlAddlCmntTxt, bizMsg.istlAddlCmntTxt_DI);
//    }
//
//    /**
//     * Set DsImptOrdDely Information Data
//     * @param tMsg DS_IMPT_ORD_DELY_INFOTMsg
//     * @param bizMsg NWAL2240CMsg
//     * @param dsImptOrdPk BigDecimal
//     * @param dsImptOrdConfigPk BigDecimal
//     */
//    private void setDsImptOrdDelyInfo(DS_IMPT_ORD_DELY_INFOTMsg tMsg, NWAL2240CMsg bizMsg, BigDecimal dsImptOrdPk, BigDecimal dsImptOrdConfigPk) {
//
//        String chngOpsFromHourMnDi = NWAL2240CommonLogic.changeTime24h(bizMsg.opsFromHourMn_DI.getValue(), bizMsg.opsFromHourMn_AP.getValue());
//        String chngOpsToHourMnDi = NWAL2240CommonLogic.changeTime24h(bizMsg.opsToHourMn_DI.getValue(), bizMsg.opsToHourMn_AP.getValue());
//
//        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdPosnNum_H0)) {
//            ZYPEZDItemValueSetter.setValue(tMsg.dsImptOrdConfigPk, dsImptOrdConfigPk);
//        } else {
//            ZYPEZDItemValueSetter.setValue(tMsg.dsImptOrdConfigPk, (BigDecimal) null);
//        }
//        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
//        ZYPEZDItemValueSetter.setValue(tMsg.dsImptOrdPk, dsImptOrdPk);
//        ZYPEZDItemValueSetter.setValue(tMsg.dsDelyTpCd, DS_DELY_TP.INSTALLATION);
////        ZYPEZDItemValueSetter.setValue(tMsg.rddDt, bizMsg.rddDt_DI); // 2017/08/25 S21_NA#20740-1 Del
//        ZYPEZDItemValueSetter.setValue(tMsg.opsFromHourMn, chngOpsFromHourMnDi);
//        ZYPEZDItemValueSetter.setValue(tMsg.opsToHourMn, chngOpsToHourMnDi);
//        ZYPEZDItemValueSetter.setValue(tMsg.loadDockAvalFlg, bizMsg.loadDockAvalFlg_DI);
//        ZYPEZDItemValueSetter.setValue(tMsg.stairCrawReqFlg, bizMsg.stairCrawReqFlg_DI);
//        ZYPEZDItemValueSetter.setValue(tMsg.stairCrawNum, bizMsg.stairCrawNum_DI);
//        ZYPEZDItemValueSetter.setValue(tMsg.elevAvalFlg, bizMsg.elevAvalFlg_DI);
//        ZYPEZDItemValueSetter.setValue(tMsg.delyAddlCmntTxt, bizMsg.delyAddlCmntTxt_DI);
//    }
//
//    /**
//     * Set DsImptOrdSiteSurvey Data
//     * @param tMsg DS_IMPT_ORD_SITE_SRVYTMsg
//     * @param bizMsg NWAL2240CMsg
//     * @param dsImptOrdPk BigDecimal
//     * @param dsImptOrdConfigPk BigDecimal
//     */
//    private void setDsImptOrdSiteSurvey(DS_IMPT_ORD_SITE_SRVYTMsg tMsg, NWAL2240CMsg bizMsg, BigDecimal dsImptOrdPk, BigDecimal dsImptOrdConfigPk) {
//
//        String chngLoadDockAvalFromHourMnSs = NWAL2240CommonLogic.changeTime24h(bizMsg.loadDockAvalFromHourMn_SS.getValue(), bizMsg.loadDockAvalFromHourMn_AP.getValue());
//        String chngLoadDockAvalToHourMnSs = NWAL2240CommonLogic.changeTime24h(bizMsg.loadDockAvalToHourMn_SS.getValue(), bizMsg.loadDockAvalToHourMn_AP.getValue());
//        String chngCarrDelyTmHourMnSs = NWAL2240CommonLogic.changeTime24h(bizMsg.carrDelyTmHourMn_SS.getValue(), bizMsg.carrDelyTmHourMn_AP.getValue());
//        String chngElevAvalFromHourMnSs = NWAL2240CommonLogic.changeTime24h(bizMsg.elevAvalFromHourMn_SS.getValue(), bizMsg.elevAvalFromHourMn_AP.getValue());
//        String chngElevAvalToHourMnSs = NWAL2240CommonLogic.changeTime24h(bizMsg.elevAvalToHourMn_SS.getValue(), bizMsg.elevAvalToHourMn_AP.getValue());
//
//        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdPosnNum_H0)) {
//            ZYPEZDItemValueSetter.setValue(tMsg.dsImptOrdConfigPk, dsImptOrdConfigPk);
//        } else {
//            ZYPEZDItemValueSetter.setValue(tMsg.dsImptOrdConfigPk, (BigDecimal) null);
//        }
//        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
//        ZYPEZDItemValueSetter.setValue(tMsg.dsImptOrdPk, dsImptOrdPk);
//        ZYPEZDItemValueSetter.setValue(tMsg.cmpyInfoAptBldgNm, bizMsg.cmpyInfoAptBldgNm_SS);
//        ZYPEZDItemValueSetter.setValue(tMsg.cmpyInfoFlNm, bizMsg.cmpyInfoFlNm_SS);
//        ZYPEZDItemValueSetter.setValue(tMsg.cmpyInfoDeptNm, bizMsg.cmpyInfoDeptNm_SS);
//        ZYPEZDItemValueSetter.setValue(tMsg.otsdStepNum, bizMsg.otsdStepNum_SS);
//        ZYPEZDItemValueSetter.setValue(tMsg.insdStepNum, bizMsg.insdStepNum_SS);
//        ZYPEZDItemValueSetter.setValue(tMsg.stairCrawReqFlg, bizMsg.stairCrawReqFlg_SS);
//        ZYPEZDItemValueSetter.setValue(tMsg.flgtStairNum, bizMsg.flgtStairNum_SS);
//        ZYPEZDItemValueSetter.setValue(tMsg.elevAvalFlg, bizMsg.elevAvalFlg_SS);
//        ZYPEZDItemValueSetter.setValue(tMsg.elevAvalFromHourMn, chngElevAvalFromHourMnSs);
//        ZYPEZDItemValueSetter.setValue(tMsg.elevAvalToHourMn, chngElevAvalToHourMnSs);
//        ZYPEZDItemValueSetter.setValue(tMsg.elevApptReqFlg, bizMsg.elevApptReqFlg_SS);
//        ZYPEZDItemValueSetter.setValue(tMsg.elevCtacPsnNm, bizMsg.elevCtacPsnNm_SS);
//        ZYPEZDItemValueSetter.setValue(tMsg.elevCtacTelNum, bizMsg.elevCtacTelNum_SS);
//        ZYPEZDItemValueSetter.setValue(tMsg.elevProtReqFlg, bizMsg.elevProtReqFlg_SS);
//        ZYPEZDItemValueSetter.setValue(tMsg.elevWdt, bizMsg.elevWdt_SS);
//        ZYPEZDItemValueSetter.setValue(tMsg.elevDepthNum, bizMsg.elevDepthNum_SS);
//        ZYPEZDItemValueSetter.setValue(tMsg.elevCtacPsnNm, bizMsg.elevCtacPsnNm_SS);
//        ZYPEZDItemValueSetter.setValue(tMsg.elevCapWt, bizMsg.elevCapWt_SS);
//        ZYPEZDItemValueSetter.setValue(tMsg.elevDoorHgt, bizMsg.elevDoorHgt_SS);
//        ZYPEZDItemValueSetter.setValue(tMsg.elevDoorWdt, bizMsg.elevDoorWdt_SS);
//        ZYPEZDItemValueSetter.setValue(tMsg.stairAndLdgWdt, bizMsg.stairAndLdgWdt_SS);
//        ZYPEZDItemValueSetter.setValue(tMsg.crdrWdt, bizMsg.crdrWdt_SS);
//        ZYPEZDItemValueSetter.setValue(tMsg.doorWdt, bizMsg.doorWdt_SS);
//        ZYPEZDItemValueSetter.setValue(tMsg.loadDockAvalFlg, bizMsg.loadDockAvalFlg_SS);
//        ZYPEZDItemValueSetter.setValue(tMsg.loadDockHgt, bizMsg.loadDockHgt_SS);
//        ZYPEZDItemValueSetter.setValue(tMsg.trctrAndTrailAccsFlg, bizMsg.trctrAndTrailAccsFlg_SS);
//        ZYPEZDItemValueSetter.setValue(tMsg.bldgEntDoorHgt, bizMsg.bldgEntDoorHgt_SS);
//        ZYPEZDItemValueSetter.setValue(tMsg.bldgEntDoorWdt, bizMsg.bldgEntDoorWdt_SS);
//        ZYPEZDItemValueSetter.setValue(tMsg.loadDockAvalFromHourMn, chngLoadDockAvalFromHourMnSs);
//        ZYPEZDItemValueSetter.setValue(tMsg.loadDockAvalToHourMn, chngLoadDockAvalToHourMnSs);
//        ZYPEZDItemValueSetter.setValue(tMsg.carrDelyTmHourMn, chngCarrDelyTmHourMnSs);
//        ZYPEZDItemValueSetter.setValue(tMsg.delyTrnspOptCd, bizMsg.delyTrnspOptCd_SS);
//        ZYPEZDItemValueSetter.setValue(tMsg.siteSrvyAddlCmntTxt, bizMsg.siteSrvyAddlCmntTxt_SS);
//    }
//
//    /**
//     * Set DsImptOrdCtacPsn Data
//     * @param tMsg DS_IMPT_ORD_CTAC_PSNTMsg
//     * @param bizMsg NWAL2240CMsg
//     * @param dsImptOrdPk BigDecimal
//     * @param dsImptOrdConfigPk BigDecimal
//     */
//    private void setDsImptOrdCtacPsn(DS_IMPT_ORD_CTAC_PSNTMsg tMsg, NWAL2240CMsg bizMsg, BigDecimal dsImptOrdPk, BigDecimal dsImptOrdConfigPk, int idx) {
//
//        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdPosnNum_H0)) {
//            ZYPEZDItemValueSetter.setValue(tMsg.dsImptOrdConfigPk, dsImptOrdConfigPk);
//        } else {
//            ZYPEZDItemValueSetter.setValue(tMsg.dsImptOrdConfigPk, (BigDecimal) null);
//        }
//        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
//        ZYPEZDItemValueSetter.setValue(tMsg.dsImptOrdPk, dsImptOrdPk);
//        ZYPEZDItemValueSetter.setValue(tMsg.dsImptOrdConfigPk, dsImptOrdConfigPk);
//        ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnTpCd, bizMsg.C.no(idx).ctacPsnTpCd_C0);
//        ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnFirstNm, bizMsg.C.no(idx).ctacPsnFirstNm_C0);
//        ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnLastNm, bizMsg.C.no(idx).ctacPsnLastNm_C0);
//        ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnEmlAddr, bizMsg.C.no(idx).ctacPsnEmlAddr_C0);
//        ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnTelNum, bizMsg.C.no(idx).ctacPsnTelNum_C0);
//        ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnFaxNum, bizMsg.C.no(idx).ctacPsnFaxNum_C0);
//        ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnExtnNum, bizMsg.C.no(idx).ctacPsnExtnNum_C0);
//        ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnOvrdFlg, ZYPConstant.FLG_OFF_N);
//    }
//
//    /**
//     * Regist Contact Person Information
//     * @param apiPMsg NMZC002001PMsg
//     * @param bizMsg NWAL2240CMsg
//     * @param idx Contact Person List index
//     * @return boolean
//     */
//    private boolean registContactMst(NMZC002001PMsg apiPMsg, NWAL2240CMsg bizMsg, int idx) {
//
//        S21SsmEZDResult ssmResult = NWAL2240Query.getInstance().getShipToCust(bizMsg);
//        if (ssmResult.isCodeNotFound()) {
//            bizMsg.setMessageInfo(ZZZM9006E, new String[] {"Ship To Cust" });
//            return false;
//        }
//        Map<String, Object> shipToCust = (Map<String, Object>) ssmResult.getResultObject();
//
//        ZYPEZDItemValueSetter.setValue(apiPMsg.xxModeCd, MODE_NEW);
//        ZYPEZDItemValueSetter.setValue(apiPMsg.glblCmpyCd, bizMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(apiPMsg.dsAcctNum, (String) shipToCust.get("SELL_TO_CUST_CD"));
//        ZYPEZDItemValueSetter.setValue(apiPMsg.locNum, (String) shipToCust.get("LOC_NUM"));
//        ZYPEZDItemValueSetter.setValue(apiPMsg.slsDt, ZYPDateUtil.getSalesDate(bizMsg.glblCmpyCd.getValue()));
//        ZYPEZDItemValueSetter.setValue(apiPMsg.ctacPsnFirstNm, bizMsg.C.no(idx).ctacPsnFirstNm_C0);
//        ZYPEZDItemValueSetter.setValue(apiPMsg.ctacPsnLastNm, bizMsg.C.no(idx).ctacPsnLastNm_C0);
//        ZYPEZDItemValueSetter.setValue(apiPMsg.ctacTpCd, bizMsg.C.no(idx).ctacPsnTpCd_C0);
//
//        int cpIdx = 0;
//
//        if (ZYPCommonFunc.hasValue(bizMsg.C.no(idx).ctacPsnTelNum_C0)) {
//            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).xxModeCd, MODE_NEW);
//            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPntTpCd, CTAC_PT_TP_CD_PHONE);
//            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPntValTxt, bizMsg.C.no(idx).ctacPsnTelNum_C0);
//            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPsnExtnNum, bizMsg.C.no(idx).ctacPsnExtnNum_C0);
//            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsOpsOutFlg, ZYPConstant.FLG_OFF_N);
//            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPntActvFlg, ZYPConstant.FLG_ON_Y);
//            cpIdx++;
//        }
//
//        if (ZYPCommonFunc.hasValue(bizMsg.C.no(idx).ctacPsnEmlAddr_C0)) {
//            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).xxModeCd, MODE_NEW);
//            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPntTpCd, CTAC_PT_TP_CD_MAIL);
//            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPntValTxt, bizMsg.C.no(idx).ctacPsnEmlAddr_C0);
//            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsOpsOutFlg, ZYPConstant.FLG_OFF_N);
//            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPntActvFlg, ZYPConstant.FLG_ON_Y);
//            cpIdx++;
//        }
//
//        if (ZYPCommonFunc.hasValue(bizMsg.C.no(idx).ctacPsnFaxNum_C0)) {
//            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).xxModeCd, MODE_NEW);
//            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPntTpCd, CTAC_PT_TP_CD_FAX);
//            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPntValTxt, bizMsg.C.no(idx).ctacPsnFaxNum_C0);
//            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsOpsOutFlg, ZYPConstant.FLG_OFF_N);
//            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPntActvFlg, ZYPConstant.FLG_ON_Y);
//            cpIdx++;
//        }
//        apiPMsg.ContactPointInfoList.setValidCount(cpIdx);
//        new NMZC002001().execute(apiPMsg, ONBATCH_TYPE.ONLINE);
//
//        List<String> errList = S21ApiUtil.getXxMsgIdList(apiPMsg);
//
//        if (!errList.isEmpty()) {
//            for (String xxMsgId : errList) {
//                if (xxMsgId.endsWith("E")) {
//                    bizMsg.setMessageInfo(xxMsgId);
//                    return false;
//                }
//            }
//        }
//
//        return true;
//    }
//
//    private boolean isChangeCheck(NWAL2240_CCMsg ccMsg, DS_IMPT_ORD_CTAC_PSNTMsg updDsImptOrdCtacPsnMsg) {
//
//        if (!S21StringUtil.isEquals(ccMsg.ctacPsnTpCd_C0.getValue(), updDsImptOrdCtacPsnMsg.ctacPsnTpCd.getValue()) || !S21StringUtil.isEquals(ccMsg.ctacPsnFirstNm_C0.getValue(), updDsImptOrdCtacPsnMsg.ctacPsnFirstNm.getValue())
//                || !S21StringUtil.isEquals(ccMsg.ctacPsnLastNm_C0.getValue(), updDsImptOrdCtacPsnMsg.ctacPsnLastNm.getValue()) || !S21StringUtil.isEquals(ccMsg.ctacPsnEmlAddr_C0.getValue(), updDsImptOrdCtacPsnMsg.ctacPsnEmlAddr.getValue())
//                || !S21StringUtil.isEquals(ccMsg.ctacPsnTelNum_C0.getValue(), updDsImptOrdCtacPsnMsg.ctacPsnTelNum.getValue()) || !S21StringUtil.isEquals(ccMsg.ctacPsnFaxNum_C0.getValue(), updDsImptOrdCtacPsnMsg.ctacPsnFaxNum.getValue())
//                || !S21StringUtil.isEquals(ccMsg.ctacPsnExtnNum_C0.getValue(), updDsImptOrdCtacPsnMsg.ctacPsnExtnNum.getValue())) {
//            return true;
//        }
//
//        return false;
//    }
}
