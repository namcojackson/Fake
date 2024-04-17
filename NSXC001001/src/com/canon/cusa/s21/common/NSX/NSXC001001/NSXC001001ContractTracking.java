/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.DS_CONTRTMsg;
import business.parts.NSZC077001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC077001.NSZC077001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_TRK_TP;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 * Contract Tracking
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/30   Hitachi         T.Tomita        Create          QC#1523, 4624
 * 2017/04/25   Hitachi         Y.Takeno        Update          RS#7237
 * 2017/06/19   Hitachi         T.Mizuki        Update          QC#19256
 * 2018/12/26   Hitachi         K.Fujimoto      Update          QC#29605
 *</pre>
 */
public class NSXC001001ContractTracking {

    /** An error occurred in Contract Tracking API. */
    public static final String ERR_MSG_ID = "NSZM0982E";

    /**
     * SSM Batch Client
     */
    private static final S21SsmBatchClient SSM_CLIENT = S21SsmBatchClient.getClient(NSXC001001ContractTracking.class);

    /**
     * callContrTrk
     * @param glblCmpyCd String
     * @param xxModeCd String
     * @param dsContrPk BigDecimal
     * @param userId String
     * @param slsDt String
     * @param stsMemoRsnCd String
     * @param stsMemoTxt String
     * @param onBatTp ONBATCH_TYPE
     * @return boolean
     */
    public static boolean callContrTrk(String glblCmpyCd, String xxModeCd, BigDecimal dsContrPk, String userId, String slsDt, String stsMemoRsnCd, String stsMemoTxt, ONBATCH_TYPE onBatTp) {

        DS_CONTRTMsg dsContr = getDsContr(glblCmpyCd, dsContrPk);
        if (dsContr == null) {
            return false;
        }
        // START 2017/04/25 [RS#7237, ADD]
        if (DS_CONTR_STS.ORDER.equals(dsContr.dsContrStsCd.getValue())) {
            return false;
        }
        // END   2017/04/25 [RS#7237, ADD]

        // Contract Header
        ContrTrkInfo contrTrkInfo = createContrTrkInfo(glblCmpyCd, xxModeCd, dsContrPk, null, null, null, null, null, null, userId, null, stsMemoRsnCd, stsMemoTxt);
        if (!callContrTrkForDsContr(contrTrkInfo, onBatTp)) {
            return false;
        }

        BigDecimal dsContrDtlPk;
        BigDecimal dsContrPrcEffPk;
        String contrPrcEffFromDt;
        String contrPrcEffThruDt;
        String baseChrgFlg;
        String usgChrgFlg;
        List<Map<String, Object>> dsContrDtlList = getDsContrDtl(glblCmpyCd, dsContrPk, slsDt);
        List<BigDecimal> dsContrBllgMtrList;
        List<Map<String, Object>> dsContrPrcEffList;
        for (Map<String, Object> dsContrDtl : dsContrDtlList) {
            // Contract Detail
            dsContrDtlPk = (BigDecimal) dsContrDtl.get("DS_CONTR_DTL_PK");
            baseChrgFlg = (String) dsContrDtl.get("BASE_CHRG_FLG");
            usgChrgFlg = (String) dsContrDtl.get("USG_CHRG_FLG");
            contrTrkInfo = createContrTrkInfo(glblCmpyCd, xxModeCd, dsContrPk, dsContrDtlPk, null, null, null, null, null, userId, null, stsMemoRsnCd, stsMemoTxt);
            if (!callContrTrkForDsContrDtl(contrTrkInfo, onBatTp)) {
                return false;
            }

            // Base Charge
            if (hasValue(baseChrgFlg) && ZYPConstant.FLG_ON_Y.equals(baseChrgFlg)) {
                // Contract Price Effectivity
                dsContrPrcEffList = getDsContrPrcEff(glblCmpyCd, dsContrDtlPk, null);
                for (Map<String, Object> dsContrPrcEff : dsContrPrcEffList) {
                    dsContrPrcEffPk = (BigDecimal) dsContrPrcEff.get("DS_CONTR_PRC_EFF_PK");
                    contrPrcEffFromDt = (String) dsContrPrcEff.get("CONTR_PRC_EFF_FROM_DT");
                    contrPrcEffThruDt = (String) dsContrPrcEff.get("CONTR_PRC_EFF_THRU_DT");
                    contrTrkInfo = createContrTrkInfo(glblCmpyCd, xxModeCd, dsContrPk, dsContrDtlPk, null, dsContrPrcEffPk, contrPrcEffFromDt, contrPrcEffThruDt, ZYPConstant.FLG_ON_Y, userId, null, stsMemoRsnCd, stsMemoTxt);
                    if (!callContrTrkForDsContrPrcEff(contrTrkInfo, onBatTp)) {
                        return false;
                    }
                }
            }

            // Usage Charge
            if (hasValue(usgChrgFlg) && ZYPConstant.FLG_ON_Y.equals(usgChrgFlg)) {
                // Contract Billing Meter
                dsContrBllgMtrList = getDsContrBllgMtr(glblCmpyCd, dsContrDtlPk);
                for (BigDecimal dsContrBllgMtrPk : dsContrBllgMtrList) {
                    contrTrkInfo = createContrTrkInfo(glblCmpyCd, xxModeCd, dsContrPk, dsContrDtlPk, dsContrBllgMtrPk, null, null, null, null, userId, null, stsMemoRsnCd, stsMemoTxt);
                    if (!callContrTrkForDsContrBllgMtr(contrTrkInfo, onBatTp)) {
                        return false;
                    }

                    // Contract Price Effectivity
                    dsContrPrcEffList = getDsContrPrcEff(glblCmpyCd, dsContrDtlPk, dsContrBllgMtrPk);
                    for (Map<String, Object> dsContrPrcEff : dsContrPrcEffList) {
                        dsContrPrcEffPk = (BigDecimal) dsContrPrcEff.get("DS_CONTR_PRC_EFF_PK");
                        contrPrcEffFromDt = (String) dsContrPrcEff.get("CONTR_PRC_EFF_FROM_DT");
                        contrPrcEffThruDt = (String) dsContrPrcEff.get("CONTR_PRC_EFF_THRU_DT");
                        contrTrkInfo = createContrTrkInfo(glblCmpyCd, xxModeCd, dsContrPk, dsContrDtlPk, dsContrBllgMtrPk, dsContrPrcEffPk, contrPrcEffFromDt, contrPrcEffThruDt, ZYPConstant.FLG_OFF_N, userId, null, stsMemoRsnCd, stsMemoTxt);
                        if (!callContrTrkForDsContrPrcEff(contrTrkInfo, onBatTp)) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    public static boolean callContrTrkForDsContr(ContrTrkInfo contrTrkInfo, ONBATCH_TYPE onBatTp) {
        NSZC077001PMsg pMsg = setPMsgForDsContr(contrTrkInfo);
        // Del Start 2018/12/26 K.Fujimoto QC#29605
        // NSZC077001 api = new NSZC077001();
        // api.execute(pMsg, onBatTp);
        // Del End   2018/12/26 K.Fujimoto QC#29605
        return callAPI(pMsg, onBatTp);
    }

    public static boolean callContrTrkForDsContrDtl(ContrTrkInfo contrTrkInfo, ONBATCH_TYPE onBatTp) {
        NSZC077001PMsg pMsg = setPMsgForDsContrDtl(contrTrkInfo);
        // Del Start 2018/12/26 K.Fujimoto QC#29605
        // NSZC077001 api = new NSZC077001();
        // api.execute(pMsg, onBatTp);
        // Del End   2018/12/26 K.Fujimoto QC#29605
        return callAPI(pMsg, onBatTp);
    }

    public static boolean callContrTrkForDsContrBllgMtr(ContrTrkInfo contrTrkInfo, ONBATCH_TYPE onBatTp) {
        NSZC077001PMsg pMsg = setPMsgForDsContrBllgMtr(contrTrkInfo);
        // Del Start 2018/12/26 K.Fujimoto QC#29605
        // NSZC077001 api = new NSZC077001();
        // api.execute(pMsg, onBatTp);
        // Del End   2018/12/26 K.Fujimoto QC#29605
        return callAPI(pMsg, onBatTp);
    }

    public static boolean callContrTrkForDsContrPrcEff(ContrTrkInfo contrTrkInfo, ONBATCH_TYPE onBatTp) {
        NSZC077001PMsg pMsg = setPMsgForDsContrPrcEff(contrTrkInfo);
        // Del Start 2018/12/26 K.Fujimoto QC#29605
        // NSZC077001 api = new NSZC077001();
        // api.execute(pMsg, onBatTp);
        // Del End   2018/12/26 K.Fujimoto QC#29605
        return callAPI(pMsg, onBatTp);
    }

    /**
     * createContrTrkInfo
     * @param glblCmpyCd String
     * @param xxModeCd String
     * @param dsContrPk String
     * @param dsContrDtlPk String
     * @param dsContrBllgMtrPk String
     * @param dsContrPrcEffPk String
     * @param contrEffFromDt String
     * @param contrEffFromDt String
     * @param baseChrgFlg String
     * @param prntDsContrTrkPk BigDecimal
     * @param stsMemoRsnCd String
     * @param stsMemoTxt String
     * @return NSZC077001PMsg
     */
    public static ContrTrkInfo createContrTrkInfo(String glblCmpyCd, String xxModeCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, BigDecimal dsContrPrcEffPk, String contrEffFromDt, String contrEffThruDt, String baseChrgFlg, String stsMemoUpdPsnCd, BigDecimal prntDsContrTrkPk, String stsMemoRsnCd, String stsMemoTxt) {
        ContrTrkInfo contrTrkInfo = new ContrTrkInfo();
        contrTrkInfo.setGlblCmpyCd(glblCmpyCd);
        contrTrkInfo.setXxModeCd(xxModeCd);
        contrTrkInfo.setDsContrPk(dsContrPk);
        contrTrkInfo.setDsContrDtlPk(dsContrDtlPk);
        contrTrkInfo.setDsContrBllgMtrPk(dsContrBllgMtrPk);
        contrTrkInfo.setDsContrPrcEffPk(dsContrPrcEffPk);
        contrTrkInfo.setContrEffFromDt(contrEffFromDt);
        contrTrkInfo.setContrEffThruDt(contrEffThruDt);
        contrTrkInfo.setBaseChrgFlg(baseChrgFlg);
        contrTrkInfo.setStsMemoUpdPsnCd(stsMemoUpdPsnCd);
        contrTrkInfo.setPrntDsContrTrkPk(prntDsContrTrkPk);
        contrTrkInfo.setStsMemoRsnCd(stsMemoRsnCd);
        contrTrkInfo.setStsMemoTxt(stsMemoTxt);
        return contrTrkInfo;
    }

    private static NSZC077001PMsg setPMsgForDsContr(ContrTrkInfo contrTrkInfo) {
        NSZC077001PMsg pMsg = new NSZC077001PMsg();
        setValue(pMsg.glblCmpyCd, contrTrkInfo.getGlblCmpyCd());
        setValue(pMsg.xxModeCd, contrTrkInfo.getXxModeCd());
        setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.CONTRACT_HEADER);
        setValue(pMsg.dsContrPk, contrTrkInfo.getDsContrPk());
        setValue(pMsg.stsMemoUpdPsnCd, getPsnCd(pMsg, contrTrkInfo.getStsMemoUpdPsnCd()));
        setValue(pMsg.stsMemoRsnCd, contrTrkInfo.getStsMemoRsnCd());
        setValue(pMsg.stsMemoTxt, contrTrkInfo.getStsMemoTxt());
        return pMsg;
    }

    private static NSZC077001PMsg setPMsgForDsContrDtl(ContrTrkInfo contrTrkInfo) {
        NSZC077001PMsg pMsg = new NSZC077001PMsg();
        setValue(pMsg.glblCmpyCd, contrTrkInfo.getGlblCmpyCd());
        setValue(pMsg.xxModeCd, contrTrkInfo.getXxModeCd());
        setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.BASE_CHARGE);
        setValue(pMsg.dsContrPk, contrTrkInfo.getDsContrPk());
        setValue(pMsg.dsContrDtlPk, contrTrkInfo.getDsContrDtlPk());
        setValue(pMsg.stsMemoUpdPsnCd, getPsnCd(pMsg, contrTrkInfo.getStsMemoUpdPsnCd()));
        setValue(pMsg.stsMemoRsnCd, contrTrkInfo.getStsMemoRsnCd());
        setValue(pMsg.stsMemoTxt, contrTrkInfo.getStsMemoTxt());
        return pMsg;
    }

    private static NSZC077001PMsg setPMsgForDsContrBllgMtr(ContrTrkInfo contrTrkInfo) {
        NSZC077001PMsg pMsg = new NSZC077001PMsg();
        setValue(pMsg.glblCmpyCd, contrTrkInfo.getGlblCmpyCd());
        setValue(pMsg.xxModeCd, contrTrkInfo.getXxModeCd());
        setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.USAGE_CHARGE);
        setValue(pMsg.dsContrPk, contrTrkInfo.getDsContrPk());
        setValue(pMsg.dsContrDtlPk, contrTrkInfo.getDsContrDtlPk());
        setValue(pMsg.dsContrBllgMtrPk, contrTrkInfo.getDsContrBllgMtrPk());
        setValue(pMsg.stsMemoUpdPsnCd, getPsnCd(pMsg, contrTrkInfo.getStsMemoUpdPsnCd()));
        setValue(pMsg.stsMemoRsnCd, contrTrkInfo.getStsMemoRsnCd());
        setValue(pMsg.stsMemoTxt, contrTrkInfo.getStsMemoTxt());
        return pMsg;
    }

    private static NSZC077001PMsg setPMsgForDsContrPrcEff(ContrTrkInfo contrTrkInfo) {
        NSZC077001PMsg pMsg = new NSZC077001PMsg();
        setValue(pMsg.glblCmpyCd, contrTrkInfo.getGlblCmpyCd());
        setValue(pMsg.xxModeCd, contrTrkInfo.getXxModeCd());
        if (ZYPConstant.FLG_ON_Y.equals(contrTrkInfo.getBaseChrgFlg())) {
            setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.BASE_CHARGE_PE);
        } else {
            setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.USAGE_CHARGE_PE);
        }
        setValue(pMsg.dsContrPk, contrTrkInfo.getDsContrPk());
        setValue(pMsg.dsContrDtlPk, contrTrkInfo.getDsContrDtlPk());
        setValue(pMsg.dsContrBllgMtrPk, contrTrkInfo.getDsContrBllgMtrPk());
        setValue(pMsg.dsContrPrcEffPk, contrTrkInfo.getDsContrPrcEffPk());
        setValue(pMsg.contrPrcEffFromDt, contrTrkInfo.getContrEffFromDt());
        setValue(pMsg.contrPrcEffThruDt, contrTrkInfo.getContrEffThruDt());
        setValue(pMsg.stsMemoUpdPsnCd, getPsnCd(pMsg, contrTrkInfo.getStsMemoUpdPsnCd()));
        setValue(pMsg.stsMemoRsnCd, contrTrkInfo.getStsMemoRsnCd());
        setValue(pMsg.stsMemoTxt, contrTrkInfo.getStsMemoTxt());
        return pMsg;
    }

    private static boolean callAPI(NSZC077001PMsg pMsg, ONBATCH_TYPE onBatTp) {
        NSZC077001 api = new NSZC077001();
        api.execute(pMsg, onBatTp);
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
            String msgId = msg.getXxMsgid();
            String[] msgPrms = msg.getXxMsgPrmArray();
            S21InfoLogOutput.println(msgId, msgPrms);
            return false;
        }
        return true;
    }

    private static DS_CONTRTMsg getDsContr(String glblCmpyCd, BigDecimal dsContrPk) {
        DS_CONTRTMsg inMsg = new DS_CONTRTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrPk, dsContrPk);
        return (DS_CONTRTMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    private static List<Map<String, Object>> getDsContrDtl(String glblCmpyCd, BigDecimal dsContrPk, String slsDt) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("slsDt", slsDt);
        // START 2017/04/25 [RS#7237, ADD]
        param.put("svcPrcFlg", ZYPConstant.FLG_OFF_N);
        // END   2017/04/25 [RS#7237, ADD]
        // START 2017/06/19 T.Mizuki [QC#19256,ADD]
        param.put("dsContrDtlStsOrdr", DS_CONTR_DTL_STS.ORDER);
        // END 2017/06/19 T.Mizuki [QC#19256,ADD]
        return (List<Map<String, Object>>) SSM_CLIENT.queryObjectList("getDsContrDtl", param);
    }

    private static List<BigDecimal> getDsContrBllgMtr(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrDtlPk", dsContrDtlPk);
        return (List<BigDecimal>) SSM_CLIENT.queryObjectList("getDsContrBllgMtr", param);
    }

    private static List<Map<String, Object>> getDsContrPrcEff(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        return (List<Map<String, Object>>) SSM_CLIENT.queryObjectList("getDsContrPrcEff", param);
    }

    private static String getPsnCd(NSZC077001PMsg pMsg, String psnCd) {
        int psnCdLen = pMsg.getAttr("stsMemoUpdPsnCd").getDigit();
        if (hasValue(psnCd) && psnCdLen >= psnCd.length()) {
            return psnCd;
        }
        return null;
    }
}
