/**
 * <Pre>Copyright(c)2013 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0580.common;

import static business.blap.NSAL0580.constant.NSAL0580Constant.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import com.canon.cusa.s21.api.NSZ.NSZC077001.ContrTrkProcMode;
import com.canon.cusa.s21.api.NSZ.NSZC077001.NSZC077001;
import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_TRK_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

import business.blap.NSAL0580.NSAL0580CMsg;
import business.blap.NSAL0580.NSAL0580Query;
import business.blap.NSAL0580.NSAL0580SMsg;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_PRC_EFFTMsg;
import business.db.SVC_MEMOTMsg;
import business.db.SVC_MEMO_RSNTMsgArray;
import business.parts.NSZC077001PMsg;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 *  2015/10/30   Hitachi        J.Kim           Create          N/A
 *  2015/12/15   Hitachi         K.Yamada        Update          CSA QC#2006
 *  2016/02/26   Hitachi        K.Kishimoto      Update         QC#2011
 *  2016/12/14   Hitachi        Y.Takeno        Update          QC#16285
 *  2018/08/16   Hitachi        K.Kim           Update          QC#27785
 *  2018/08/26   Hitachi        K.Kim           Update          QC#22987
 *  2019/07/18   Hitachi        A.Kohinata      Update          QC#51706
 *</pre>
 */
public class NSAL0580CommonLogic {

    /**
     * Search DS Contract Report Group Info.
     * @param cMsg NSAL0580CMsg
     * @param sMsg NSAL0580SMsg
     */
    public static void searchDSContractInfo(NSAL0580CMsg cMsg, NSAL0580SMsg sMsg) {

        NSAL0580Query query = NSAL0580Query.getInstance();

        S21SsmEZDResult ssmResult = query.getDSContractInfo(cMsg);
        if (ssmResult == null || !ssmResult.isCodeNormal()) {
            cMsg.setMessageInfo(NSAM0353E, new String[] {"No Contract Data" });
            return;
        } else {

            List<Map<String, Object>> dSContractInfoList = (List<Map<String, Object>>) ssmResult.getResultObject();
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrCtrlStsCd, (String) dSContractInfoList.get(0).get("DS_CONTR_CTRL_STS_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrCtrlStsNm, (String) dSContractInfoList.get(0).get("DS_CONTR_CTRL_STS_NM"));
            // mod start 2015/12/15 CSA Defect#2006
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrNum, (String) dSContractInfoList.get(0).get("DS_CONTR_NUM"));
            // mod end 2015/12/15 CSA Defect#2006

            ZYPEZDItemValueSetter.setValue(cMsg.dsContrPk, (BigDecimal) dSContractInfoList.get(0).get("DS_CONTR_PK"));
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrStsCd, (String) dSContractInfoList.get(0).get("DS_CONTR_STS_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.ezUpTime, (String) dSContractInfoList.get(0).get("DC_EZUPTIME"));
            ZYPEZDItemValueSetter.setValue(cMsg.ezUpTimeZone, (String) dSContractInfoList.get(0).get("DC_EZUPTIMEZONE"));
            
            // DS Contract Control Status Pulldown
            setContractControlStatus(cMsg, dSContractInfoList);
        }
    }

    private static void setContractControlStatus(NSAL0580CMsg cMsg, List<Map<String, Object>> dSContractInfoList) {

        int index = 0;
        // Add Start 02/26/2016 <QC#2011>
        cMsg.dsContrCtrlStsCd_L.clear();
        cMsg.dsContrCtrlStsNm_L.clear();
        // Add End   02/26/2016 <QC#2011>
        for (Map<String, Object> dSContractInfo : dSContractInfoList) {

            String ctrlStsCd = (String) dSContractInfo.get("DS_CONTR_CTRL_STS_CD");
            // Entered
            if (DS_CONTR_CTRL_STS.ENTERED.equals(ctrlStsCd)) {
                ZYPEZDItemValueSetter.setValue(cMsg.dsContrCtrlStsCd_L.no(index), DS_CONTR_CTRL_STS.CANCELLED);
                ZYPEZDItemValueSetter.setValue(cMsg.dsContrCtrlStsNm_L.no(index), STATUS_CANCELLED);
                break;
                // Active
            } else if (DS_CONTR_CTRL_STS.SINGED.equals(ctrlStsCd) || DS_CONTR_CTRL_STS.ACTIVE.equals(ctrlStsCd)) {
                ZYPEZDItemValueSetter.setValue(cMsg.dsContrCtrlStsCd_L.no(index), DS_CONTR_CTRL_STS.HOLD);
                ZYPEZDItemValueSetter.setValue(cMsg.dsContrCtrlStsNm_L.no(index), STATUS_HOLD);
                ZYPEZDItemValueSetter.setValue(cMsg.dsContrCtrlStsCd_L.no(index + 1), DS_CONTR_CTRL_STS.BILLING_HOLD);
                ZYPEZDItemValueSetter.setValue(cMsg.dsContrCtrlStsNm_L.no(index + 1), STATUS_BILLING_HOLD);
                break;
                // Hold, Bill Hold
            } else if (DS_CONTR_CTRL_STS.HOLD.equals(ctrlStsCd) || DS_CONTR_CTRL_STS.BILLING_HOLD.equals(ctrlStsCd)) {
                ZYPEZDItemValueSetter.setValue(cMsg.dsContrCtrlStsCd_L.no(index), DS_CONTR_CTRL_STS.ACTIVE);
                ZYPEZDItemValueSetter.setValue(cMsg.dsContrCtrlStsNm_L.no(index), STATUS_ACTIVE);
                break;
            }
        }
    }

    /**
     * Service Memo Reason Pull down
     * @param cMsg NSAL0580CMsg
     * @param sMsg NSAL0580SMsg
     */
    public static void setServiceMemoReasonInfo(NSAL0580CMsg cMsg, NSAL0580SMsg sMsg) {

        SVC_MEMO_RSNTMsgArray smrTMsgArray = NSAL0580Query.getInstance().getServiceMemoReasonInfo(cMsg);
        if (smrTMsgArray.getValidCount() > 0) {
            Map<String, String> tMsgKeys = new HashMap<String, String>();
            tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "svcMemoRsnCd");
            tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "svcMemoRsnDescTxt");
            ZYPPulldownValueSetter.set(smrTMsgArray, tMsgKeys, cMsg.svcMemoRsnCd_L, cMsg.svcMemoRsnDescTxt_L);
        }
    }


    /**
     * Update DS Contract Status Info
     * @param cMsg NSAL0580CMsg
     * @param sMsg NSAL0580SMsg
     * @return
     */
    public static boolean execStsChng(NSAL0580CMsg cMsg, NSAL0580SMsg sMsg) {
        setChngStsValue(cMsg);
       
        // DS_CONTR
        DS_CONTRTMsg dsContrInTMsg = new DS_CONTRTMsg();
        ZYPEZDItemValueSetter.setValue(dsContrInTMsg.glblCmpyCd, sMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsContrInTMsg.dsContrPk, sMsg.dsContrPk.getValue());

        DS_CONTRTMsg outMsg = (DS_CONTRTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(dsContrInTMsg);
        if (outMsg == null) {
            cMsg.setMessageInfo(NZZM0003E, null);
            return false;
        }
        if (!ZYPDateUtil.isSameTimeStamp(sMsg.ezUpTime.getValue(), sMsg.ezUpTimeZone.getValue(), outMsg.ezUpTime.getValue(), outMsg.ezUpTimeZone.getValue())) {
            cMsg.setMessageInfo(NZZM0003E, null);
            return false;
        }
        if (hasValue(cMsg.dsContrStsCd_TO)) {
            ZYPEZDItemValueSetter.setValue(outMsg.dsContrStsCd, cMsg.dsContrStsCd_TO);
        }
        if (hasValue(cMsg.contrHldFlg_TO)) {
            ZYPEZDItemValueSetter.setValue(outMsg.contrHldFlg, cMsg.contrHldFlg_TO);
        }
        if (hasValue(cMsg.bllgHldFlg_TO)) {
            ZYPEZDItemValueSetter.setValue(outMsg.bllgHldFlg, cMsg.bllgHldFlg_TO);
        }
        S21FastTBLAccessor.update(outMsg);
        resultUpdate(cMsg, outMsg.getReturnCode(), "DS_CONTR");
        if (!callContractTrackingAPI(cMsg, cMsg.dsContrPk.getValue(), null, null, null, null, null, null)) {
            return false;
        }

        S21SsmEZDResult ssmResult = NSAL0580Query.getInstance().getContrDtlList(sMsg.glblCmpyCd.getValue(), sMsg.dsContrPk.getValue());
        if (ssmResult == null || !ssmResult.isCodeNormal()) {
            return true;
        } else {
            List<Map<String, Object>> contrDtlList = (List<Map<String, Object>>) ssmResult.getResultObject();
            for (Map<String, Object>contrDtl : contrDtlList) {
                if (!updateContrDtlSts(cMsg, contrDtl)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void setChngStsValue(NSAL0580CMsg cMsg) {
        cMsg.dsContrStsCd_TO.clear();
        cMsg.dsContrDtlStsCd_TO.clear();
        cMsg.contrHldFlg_TO.clear();
        cMsg.bllgHldFlg_TO.clear();
        String fromStsCd = cMsg.dsContrCtrlStsCd.getValue();
        String toStsCd = cMsg.dsContrCtrlStsCd_H.getValue();
        if (DS_CONTR_CTRL_STS.ENTERED.equals(fromStsCd)) {
            if (DS_CONTR_CTRL_STS.CANCELLED.equals(toStsCd)) {
                ZYPEZDItemValueSetter.setValue(cMsg.dsContrStsCd_TO, DS_CONTR_STS.CANCELLED);
                ZYPEZDItemValueSetter.setValue(cMsg.dsContrDtlStsCd_TO, DS_CONTR_DTL_STS.CANCELLED);
            }
        } else if (DS_CONTR_CTRL_STS.SINGED.equals(fromStsCd)) {
            if (DS_CONTR_CTRL_STS.HOLD.equals(toStsCd)) {
                ZYPEZDItemValueSetter.setValue(cMsg.contrHldFlg_TO, FLG_ON_Y);
            }
            if (DS_CONTR_CTRL_STS.BILLING_HOLD.equals(toStsCd)) {
                ZYPEZDItemValueSetter.setValue(cMsg.bllgHldFlg_TO, FLG_ON_Y);
            }
        } else if (DS_CONTR_CTRL_STS.ACTIVE.equals(fromStsCd)) {
            if (DS_CONTR_CTRL_STS.HOLD.equals(toStsCd)) {
                ZYPEZDItemValueSetter.setValue(cMsg.contrHldFlg_TO, FLG_ON_Y);
            }
            if (DS_CONTR_CTRL_STS.BILLING_HOLD.equals(toStsCd)) {
                ZYPEZDItemValueSetter.setValue(cMsg.bllgHldFlg_TO, FLG_ON_Y);
            }
        } else if (DS_CONTR_CTRL_STS.HOLD.equals(fromStsCd)) {
            if (DS_CONTR_CTRL_STS.ACTIVE.equals(toStsCd)) {
                ZYPEZDItemValueSetter.setValue(cMsg.contrHldFlg_TO, FLG_OFF_N);
            }
        } else if (DS_CONTR_CTRL_STS.BILLING_HOLD.equals(fromStsCd)) {
            if (DS_CONTR_CTRL_STS.ACTIVE.equals(toStsCd)) {
                ZYPEZDItemValueSetter.setValue(cMsg.bllgHldFlg_TO, FLG_OFF_N);
            }
        }
    }

    private static boolean updateContrDtlSts(NSAL0580CMsg cMsg, Map<String, Object>contrDtl) {
        if (isUpdateSts(cMsg, contrDtl)) {
            DS_CONTR_DTLTMsg inMsg = new DS_CONTR_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, cMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrDtlPk, (BigDecimal)contrDtl.get("DS_CONTR_DTL_PK"));
             inMsg = (DS_CONTR_DTLTMsg) S21FastTBLAccessor.findByKey(inMsg);
             if (inMsg == null) {
                 return true;
             }
             if (hasValue(cMsg.dsContrDtlStsCd_TO)) {
                 ZYPEZDItemValueSetter.setValue(inMsg.dsContrDtlStsCd, cMsg.dsContrDtlStsCd_TO);
             }
             if (hasValue(cMsg.contrHldFlg_TO)) {
                 ZYPEZDItemValueSetter.setValue(inMsg.contrHldFlg, cMsg.contrHldFlg_TO);
             }
             if (hasValue(cMsg.bllgHldFlg_TO)) {
                 ZYPEZDItemValueSetter.setValue(inMsg.bllgHldFlg, cMsg.bllgHldFlg_TO);
             }
             S21FastTBLAccessor.update(inMsg);
             resultUpdate(cMsg, inMsg.getReturnCode(), "DS_CONTR_DTL");
             if (!callContractTrackingAPI(cMsg, cMsg.dsContrPk.getValue(), inMsg.dsContrDtlPk.getValue(), null, null, null, null, null)) {
                 return false;
             }
        }
        S21SsmEZDResult bllgMtrResult = NSAL0580Query.getInstance().getBllgMtrList(cMsg.glblCmpyCd.getValue(), (BigDecimal)contrDtl.get("DS_CONTR_DTL_PK"));
        if (bllgMtrResult != null && bllgMtrResult.isCodeNormal()) {
            List<Map<String, Object>> bllgMtrList = (List<Map<String, Object>>) bllgMtrResult.getResultObject();
            for (Map<String, Object> bllgMtr : bllgMtrList) {
                if (!isUpdateSts(cMsg, bllgMtr)) {
                    continue;
                }
                if (!updateBllgMtrSts(cMsg, bllgMtr)) {
                    return false;
                }
            }
        }
        S21SsmEZDResult prcEffResult = NSAL0580Query.getInstance().getPrcEffList(cMsg.glblCmpyCd.getValue(), (BigDecimal)contrDtl.get("DS_CONTR_DTL_PK"));
        if (prcEffResult != null && prcEffResult.isCodeNormal()) {
            List<Map<String, Object>> prcEffList = (List<Map<String, Object>>) prcEffResult.getResultObject();
            for (Map<String, Object> prcEff : prcEffList) {
                if (!isUpdateSts(cMsg, prcEff)) {
                    continue;
                }
                if (!updatePrcEffSts(cMsg, prcEff)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isUpdateSts(NSAL0580CMsg cMsg, Map<String, Object>targetMap) {
        if (!hasValue(cMsg.dsContrCtrlStsCd)) {
            return false;
        }
        // START 2018/08/16 K.Kim [QC#27785, MOD]
        // String contrCtrlStsCd = cMsg.dsContrCtrlStsCd.getValue();
        // if (contrCtrlStsCd.equals((String) targetMap.get("DS_CONTR_CTRL_STS_CD"))) {
        if (!DS_CONTR_CTRL_STS.CANCELLED.equals((String) targetMap.get("DS_CONTR_CTRL_STS_CD"))) {
        // END 2018/08/16 K.Kim [QC#27785, MOD]
            return true;
        }
        return false;
    }

    private static boolean updateBllgMtrSts(NSAL0580CMsg cMsg, Map<String, Object>bllgMtr) {
        DS_CONTR_BLLG_MTRTMsg inMsg = new DS_CONTR_BLLG_MTRTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.dsContrBllgMtrPk, (BigDecimal)bllgMtr.get("DS_CONTR_BLLG_MTR_PK"));
         inMsg = (DS_CONTR_BLLG_MTRTMsg) S21FastTBLAccessor.findByKey(inMsg);
         if (inMsg == null) {
             return true;
         }
         if (hasValue(cMsg.dsContrDtlStsCd_TO)) {
             ZYPEZDItemValueSetter.setValue(inMsg.dsContrBllgMtrStsCd, cMsg.dsContrDtlStsCd_TO);
         }
         if (hasValue(cMsg.contrHldFlg_TO)) {
             ZYPEZDItemValueSetter.setValue(inMsg.contrHldFlg, cMsg.contrHldFlg_TO);
         }
         if (hasValue(cMsg.bllgHldFlg_TO)) {
             ZYPEZDItemValueSetter.setValue(inMsg.bllgHldFlg, cMsg.bllgHldFlg_TO);
         }
         S21FastTBLAccessor.update(inMsg);
         resultUpdate(cMsg, inMsg.getReturnCode(), "DS_CONTR_BLLG_MTR");
         if (!callContractTrackingAPI(cMsg, cMsg.dsContrPk.getValue(), inMsg.dsContrDtlPk.getValue(), inMsg.dsContrBllgMtrPk.getValue(), null, null, null, null)) {
            return false;
        }

        return true;
    }

    private static boolean updatePrcEffSts(NSAL0580CMsg cMsg, Map<String, Object> bllgMtr) {
        DS_CONTR_PRC_EFFTMsg inMsg = new DS_CONTR_PRC_EFFTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.dsContrPrcEffPk, (BigDecimal) bllgMtr.get("DS_CONTR_PRC_EFF_PK"));
        inMsg = (DS_CONTR_PRC_EFFTMsg) S21FastTBLAccessor.findByKey(inMsg);
        if (inMsg == null) {
            return true;
        }
        if (hasValue(cMsg.dsContrDtlStsCd_TO)) {
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrPrcEffStsCd, cMsg.dsContrDtlStsCd_TO);
        }
        if (hasValue(cMsg.contrHldFlg_TO)) {
            ZYPEZDItemValueSetter.setValue(inMsg.contrHldFlg, cMsg.contrHldFlg_TO);
        }
        if (hasValue(cMsg.bllgHldFlg_TO)) {
            ZYPEZDItemValueSetter.setValue(inMsg.bllgHldFlg, cMsg.bllgHldFlg_TO);
        }
        S21FastTBLAccessor.update(inMsg);
        resultUpdate(cMsg, inMsg.getReturnCode(), "DS_CONTR_PRC_EFF");
        if (!callContractTrackingAPI(cMsg, cMsg.dsContrPk.getValue(), inMsg.dsContrDtlPk.getValue(), inMsg.dsContrBllgMtrPk.getValue(), inMsg.dsContrPrcEffPk.getValue(), inMsg.contrPrcEffFromDt.getValue(),
                inMsg.contrPrcEffThruDt.getValue(), inMsg.baseChrgFlg.getValue())) {
            return false;
        }

        return true;
    }

    /**
     * Return Result Code.
     * @param cMsg
     * @param returnCode
     * @param msg
     */
    private static void resultUpdate(NSAL0580CMsg cMsg, String returnCode, String msg) {

        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(returnCode)) {
            cMsg.setMessageInfo(NSAM0031E, new String[] {msg });
            return;
        }
    }

    /**
     * Submit Service Memo
     * @param cMsg NSAL0580CMsg
     * @param sMsg NSAL0580SMsg
     */
    public static void submitSvcMemo(NSAL0580CMsg cMsg, NSAL0580SMsg sMsg) {

        S21SsmEZDResult ssmResult = NSAL0580Query.getInstance().getSvcMemo(sMsg);

        List<Map<String, Object>> svcMemoMapList = (List<Map<String, Object>>) ssmResult.getResultObject();

        if (svcMemoMapList == null || svcMemoMapList.size() == 0) {

            SVC_MEMOTMsg insertTMsg = new SVC_MEMOTMsg();
            ZYPEZDItemValueSetter.setValue(insertTMsg.glblCmpyCd, sMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(insertTMsg.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MEMO_SQ));
            ZYPEZDItemValueSetter.setValue(insertTMsg.svcMemoCatgCd, SVC_MEMO_CATG.CONTRACT_MEMO);
            ZYPEZDItemValueSetter.setValue(insertTMsg.svcMemoTpCd, SVC_MEMO_TP.STATUS_CHANGE);
            ZYPEZDItemValueSetter.setValue(insertTMsg.svcCmntTxt, sMsg.svcCmntTxt);
            ZYPEZDItemValueSetter.setValue(insertTMsg.dsContrPk, sMsg.dsContrPk);
            ZYPEZDItemValueSetter.setValue(insertTMsg.lastUpdUsrId, cMsg.getUserID());
            ZYPEZDItemValueSetter.setValue(insertTMsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime(DT_FORMAT_TS));
            ZYPEZDItemValueSetter.setValue(insertTMsg.svcMemoRsnCd, sMsg.svcMemoRsnCd_H);
            S21FastTBLAccessor.insert(insertTMsg);
        } else {

            for (int idx = 0; idx < svcMemoMapList.size(); idx++) {

                Map<String, Object> svcMemoMap = (Map<String, Object>) svcMemoMapList.get(idx);
                SVC_MEMOTMsg inMsg = new SVC_MEMOTMsg();
                ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, (String) svcMemoMap.get("GLBL_CMPY_CD"));
                ZYPEZDItemValueSetter.setValue(inMsg.svcMemoPk, (BigDecimal) svcMemoMap.get("SVC_MEMO_PK"));

                SVC_MEMOTMsg outMsg = (SVC_MEMOTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(inMsg);
                ZYPEZDItemValueSetter.setValue(outMsg.svcCmntTxt, sMsg.svcCmntTxt);
                ZYPEZDItemValueSetter.setValue(outMsg.lastUpdUsrId, cMsg.getUserID());
                ZYPEZDItemValueSetter.setValue(outMsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime(DT_FORMAT_TS));
                ZYPEZDItemValueSetter.setValue(outMsg.svcMemoRsnCd, sMsg.svcMemoRsnCd_H);
                S21FastTBLAccessor.update(outMsg);
            }
        }
    }
    public static boolean callContractTrackingAPI(NSAL0580CMsg cMsg, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, BigDecimal dsContrPrcEffPk, String contrPrcEffFromDt, String contrPrcEffThruDt, String baseChrgFlg) {
        NSZC077001PMsg pMsg = new NSZC077001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, ContrTrkProcMode.USER_OPERATION.code);
        ZYPEZDItemValueSetter.setValue(pMsg.stsMemoRsnCd, cMsg.svcMemoRsnCd_H);
        ZYPEZDItemValueSetter.setValue(pMsg.stsMemoTxt, cMsg.svcCmntTxt);
        S21UserProfileService s21UserProfileService = S21UserProfileServiceFactory.getInstance().getService();
        S21UserInfo userInfo = s21UserProfileService.getContextUserInfo();
        String usrId = userInfo.getUserId();
        ZYPEZDItemValueSetter.setValue(pMsg.stsMemoUpdPsnCd, usrId);

        if (hasValue(dsContrPk)) {
            ZYPEZDItemValueSetter.setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.CONTRACT_HEADER);
            ZYPEZDItemValueSetter.setValue(pMsg.dsContrPk, dsContrPk);
        }

        if (hasValue(dsContrDtlPk)) {
            ZYPEZDItemValueSetter.setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.BASE_CHARGE);
            ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, dsContrDtlPk);
        }

        if (hasValue(dsContrBllgMtrPk)) {
            ZYPEZDItemValueSetter.setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.USAGE_CHARGE);
            ZYPEZDItemValueSetter.setValue(pMsg.dsContrBllgMtrPk, dsContrBllgMtrPk);
        }

        if (hasValue(dsContrPrcEffPk)) {
            if (FLG_ON_Y.equals(baseChrgFlg)) {
                ZYPEZDItemValueSetter.setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.BASE_CHARGE_PE);
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.USAGE_CHARGE_PE);
            }
            ZYPEZDItemValueSetter.setValue(pMsg.dsContrPrcEffPk, dsContrPrcEffPk);
            ZYPEZDItemValueSetter.setValue(pMsg.contrPrcEffFromDt, contrPrcEffFromDt);
            ZYPEZDItemValueSetter.setValue(pMsg.contrPrcEffThruDt, contrPrcEffThruDt);
        }

        NSZC077001 api = new NSZC077001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
            String msgId = msg.getXxMsgid();
            String[] msgPrms = msg.getXxMsgPrmArray();
            cMsg.setMessageInfo(msgId, msgPrms);
            return false;
        }
        return true;
    }

    // Add Start 12/14/2016 <QC#16285>
    /**
     * Submit Service Memo For Report
     * @param cMsg NSAL0580CMsg
     * @param sMsg NSAL0580SMsg
     */
    public static void submitSvcMemoForReport(NSAL0580CMsg cMsg, NSAL0580SMsg sMsg) {
        if (ZYPConstant.FLG_ON_Y.equals(cMsg.bllgHldFlg_TO.getValue())) {
            String svcCmntTxt = String.format(SVC_MEMO_BLLG_RSN_CMNT_TXT_HDR, sMsg.dsContrNum.getValue());

            SVC_MEMOTMsg insertTMsg = new SVC_MEMOTMsg();
            ZYPEZDItemValueSetter.setValue(insertTMsg.glblCmpyCd, sMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(insertTMsg.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MEMO_SQ));
            ZYPEZDItemValueSetter.setValue(insertTMsg.svcMemoCatgCd, SVC_MEMO_CATG.CONTRACT_MEMO);
            ZYPEZDItemValueSetter.setValue(insertTMsg.svcMemoTpCd, SVC_MEMO_TP.BILLING_HOLD_REASON);
            ZYPEZDItemValueSetter.setValue(insertTMsg.svcCmntTxt, svcCmntTxt);
            ZYPEZDItemValueSetter.setValue(insertTMsg.dsContrPk, sMsg.dsContrPk);
            ZYPEZDItemValueSetter.setValue(insertTMsg.lastUpdUsrId, cMsg.getUserID());
            ZYPEZDItemValueSetter.setValue(insertTMsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime(DT_FORMAT_TS));
            ZYPEZDItemValueSetter.setValue(insertTMsg.svcMemoRsnCd, SVC_MEMO_RSN.BILLING_HOLD_REASON);

            S21FastTBLAccessor.insert(insertTMsg);
        }
    }
    // Add End   12/14/2016 <QC#16285>

    // START 2018/08/26 [QC#22987, ADD]
    public static boolean existUnapprovedBllgHld(NSAL0580CMsg cMsg, NSAL0580SMsg sMsg) {

        if (existUnapprovedUsgChrg(sMsg.glblCmpyCd.getValue(), sMsg.dsContrPk.getValue())) {
            return true;
        }

        if (existUnapprovedCrRebil(sMsg.glblCmpyCd.getValue(), sMsg.dsContrPk.getValue())) {
            return true;
        }
        return false;
    }

    public static boolean existUnapprovedUsgChrg(String glblCmpyCd, BigDecimal dsContrPk) {

        NSAL0580Query query = NSAL0580Query.getInstance();

        List<BigDecimal> svcContrBllgPkList = query.getUnapprovedUsgChrg(glblCmpyCd, dsContrPk);
        if (svcContrBllgPkList.isEmpty()) {
            return false;
        }
        return true;
    }

    private static boolean existUnapprovedCrRebil(String glblCmpyCd, BigDecimal dsContrPk) {

        NSAL0580Query query = NSAL0580Query.getInstance();

        List<BigDecimal> svcCrRebilPkList = query.getUnapprovedCrRebil(glblCmpyCd, dsContrPk);
        if (svcCrRebilPkList.isEmpty()) {
            return false;
        }
        return true;
    }
    // END 2018/08/26 [QC#22987, ADD]

    // add start 2019/07/18 QC#51706
    /**
     * Submit Service Memo For Manual Billing Hold
     * @param cMsg NSAL0580CMsg
     * @param sMsg NSAL0580SMsg
     */
    public static void submitSvcMemoForManBllgHld(NSAL0580CMsg cMsg, NSAL0580SMsg sMsg) {
        if (ZYPConstant.FLG_ON_Y.equals(cMsg.bllgHldFlg_TO.getValue())) {
            deleteSvcMemoForManBllgHld(cMsg);
            insertSvcMemoForManBllgHld(cMsg, cMsg.dsContrPk.getValue(), null, null);

            S21SsmEZDResult ssmResult = NSAL0580Query.getInstance().getContrDtlList(cMsg.glblCmpyCd.getValue(), cMsg.dsContrPk.getValue());
            if (ssmResult != null && ssmResult.isCodeNormal()) {
                List<Map<String, Object>> contrDtlList = (List<Map<String, Object>>) ssmResult.getResultObject();
                for (Map<String, Object> contrDtl : contrDtlList) {
                    if (isUpdateSts(cMsg, contrDtl)) {
                        insertSvcMemoForManBllgHld(cMsg, cMsg.dsContrPk.getValue(), (BigDecimal) contrDtl.get("DS_CONTR_DTL_PK"), null);
                    }
                    S21SsmEZDResult bllgMtrResult = NSAL0580Query.getInstance().getBllgMtrList(cMsg.glblCmpyCd.getValue(), (BigDecimal) contrDtl.get("DS_CONTR_DTL_PK"));
                    if (bllgMtrResult != null && bllgMtrResult.isCodeNormal()) {
                        List<Map<String, Object>> bllgMtrList = (List<Map<String, Object>>) bllgMtrResult.getResultObject();
                        for (Map<String, Object> bllgMtr : bllgMtrList) {
                            if (isUpdateSts(cMsg, bllgMtr)) {
                                insertSvcMemoForManBllgHld(cMsg, cMsg.dsContrPk.getValue(), (BigDecimal) contrDtl.get("DS_CONTR_DTL_PK"), (BigDecimal) bllgMtr.get("DS_CONTR_BLLG_MTR_PK"));
                            }
                        }
                    }
                }
            }
        } else if (ZYPConstant.FLG_OFF_N.equals(cMsg.bllgHldFlg_TO.getValue())) {
            deleteSvcMemoForManBllgHld(cMsg);
        }
    }

    private static void insertSvcMemoForManBllgHld(NSAL0580CMsg cMsg, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk) {
        SVC_MEMOTMsg insertTMsg = new SVC_MEMOTMsg();
        ZYPEZDItemValueSetter.setValue(insertTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insertTMsg.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MEMO_SQ));
        ZYPEZDItemValueSetter.setValue(insertTMsg.svcMemoCatgCd, SVC_MEMO_CATG_FOR_MAN_BLLG_HLD);
        ZYPEZDItemValueSetter.setValue(insertTMsg.svcMemoTpCd, SVC_MEMO_TP_FOR_MAN_BLLG_HLD);
        ZYPEZDItemValueSetter.setValue(insertTMsg.svcCmntTxt, SVC_MEMO_CMNT_FOR_MAN_BLLG_HLD);
        ZYPEZDItemValueSetter.setValue(insertTMsg.dsContrPk, dsContrPk);
        ZYPEZDItemValueSetter.setValue(insertTMsg.dsContrDtlPk, dsContrDtlPk);
        ZYPEZDItemValueSetter.setValue(insertTMsg.lastUpdUsrId, cMsg.getUserID());
        ZYPEZDItemValueSetter.setValue(insertTMsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime(DT_FORMAT_TS));
        ZYPEZDItemValueSetter.setValue(insertTMsg.svcMemoRsnCd, SVC_MEMO_RSN_FOR_MAN_BLLG_HLD);
        if (hasValue(dsContrBllgMtrPk)) {
            ZYPEZDItemValueSetter.setValue(insertTMsg.svcMemoTrxNum, dsContrBllgMtrPk.toString());
            ZYPEZDItemValueSetter.setValue(insertTMsg.svcMemoTrxNm, "DS_CONTR_BLLG_MTR_PK");
        }
        S21FastTBLAccessor.insert(insertTMsg);
    }

    private static void deleteSvcMemoForManBllgHld(NSAL0580CMsg cMsg) {
        List<BigDecimal> svcMemoPkList = NSAL0580Query.getInstance().getSvcMemoForManBllgHld(cMsg.glblCmpyCd.getValue(), cMsg.dsContrPk.getValue());
        for (int i = 0; i < svcMemoPkList.size(); i++) {
            SVC_MEMOTMsg inTMsg = new SVC_MEMOTMsg();
            ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, cMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inTMsg.svcMemoPk, svcMemoPkList.get(i));
            EZDTBLAccessor.logicalRemove(inTMsg);
        }
    }
    // add end 2019/07/18 QC#51706
}
