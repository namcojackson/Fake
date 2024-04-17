/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0700.common;

import static business.blap.NSAL0700.constant.NSAL0700Constant.*;

import static business.blap.NSAL0700.constant.NSAL0700Constant.DATE_FORMAT;
import static business.blap.NSAL0700.constant.NSAL0700Constant.MTR_LB_NM;
import static business.blap.NSAL0700.constant.NSAL0700Constant.NSAM0200I;
import static business.blap.NSAL0700.constant.NSAL0700Constant.NSAM0394W;
import static business.blap.NSAL0700.constant.NSAL0700Constant.RTRN_MSG_FAILED;
import static business.blap.NSAL0700.constant.NSAL0700Constant.RTRN_MSG_SUCCESS;
import static business.blap.NSAL0700.constant.NSAL0700Constant.RTRN_MSG_UPDATE_FAILED;
import static business.blap.NSAL0700.constant.NSAL0700Constant.RTRN_MSG_UPDATE_FAILED_WF;
import static business.blap.NSAL0700.constant.NSAL0700Constant.SVC_MEMO_BLLG_RSN_CMNT_TXT_DTL;
import static business.blap.NSAL0700.constant.NSAL0700Constant.SVC_MEMO_BLLG_RSN_CMNT_TXT_HDR;
import static business.blap.NSAL0700.constant.NSAL0700Constant.SVC_MEMO_BLLG_RSN_CMNT_TXT_MTR;
import static business.blap.NSAL0700.constant.NSAL0700Constant.SVC_MEMO_TRX_NM_BLLG_MTR;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.CHKBOX_ON_Y;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_OFF_N;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0700.NSAL0700CMsg;
import business.blap.NSAL0700.NSAL0700Query;
import business.blap.NSAL0700.NSAL0700SMsg;
import business.blap.NSAL0700.NSAL0700_ACMsg;
import business.blap.NSAL0700.NSAL0700_ACMsgArray;
import business.blap.NSAL0700.NSAL0700_ASMsg;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_PRC_EFFTMsg;
import business.db.SVC_MEMOTMsg;
import business.db.SVC_MEMO_RSNTMsg;
import business.db.SVC_MEMO_RSNTMsgArray;
import business.parts.NSZC077001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC077001.ContrTrkProcMode;
import com.canon.cusa.s21.api.NSZ.NSZC077001.NSZC077001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
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
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 *<pre>
 * Contract On Billing Hold
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/04   Hitachi         K.Kasai         Create          N/A
 * 2015/11/27   Hitachi         T.Tsuchida      Update          QC#1210
 * 2015/12/11   Hitachi         J.Kim           Update          QC#1659
 * 2015/12/15   Hitachi         T.Tsuchida      Update          QC#1577
 * 2016/02/26   Hitachi         K.Kasai         Update          QC#2684
 * 2016/11/28   Hitachi         T.Mizuki        Update          QC#4210
 * 2016/12/13   Hitachi         Y.Takeno        Update          QC#16285
 * 2017/09/04   Hitachi         K.Kojima        Update          QC#20816
 * 2018/08/26   Hitachi         K.Kim           Update          QC#22987
 * 2019/07/18   Hitachi         A.Kohinata      Update          QC#51706
 *</pre>
 */
public class NSAL0700CommonLogic {

    /**
     * Create Pull Down
     * @param cMsg NSAL0700CMsg
     */
    public static void createPullDown(NSAL0700CMsg cMsg) {
        createSvcMemoRsnPullDown(cMsg);
    }

    private static void createSvcMemoRsnPullDown(NSAL0700CMsg cMsg) {
        SVC_MEMO_RSNTMsgArray tMsgAry = getSvcMemoRsnPulldownList(cMsg.glblCmpyCd.getValue(), SVC_MEMO_TP.PUT_CONTRACTS_ON_BILLING_HOLD);
        Map<String, String> tMsgKeys = new HashMap<String, String>();
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "svcMemoRsnCd");
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "svcMemoRsnNm");
        ZYPPulldownValueSetter.set(tMsgAry, tMsgKeys, cMsg.svcMemoRsnCd_H1, cMsg.svcMemoRsnNm_H2);
    }

    private static SVC_MEMO_RSNTMsgArray getSvcMemoRsnPulldownList(String glblCmpyCd, String svcMemoTpCd) {
        SVC_MEMO_RSNTMsg inMsg = new SVC_MEMO_RSNTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcMemoTpCd01", svcMemoTpCd);
        return (SVC_MEMO_RSNTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    // mod start 2016/11/28 CSA QC#4210
    /**
     * Change Billing Hold
     * @param cMsg NSAL0700CMsg
     * @param sMsg NSAL0700SMsg
     */
    public static void changeBllgHold(NSAL0700CMsg cMsg, NSAL0700SMsg sMsg) {
        boolean procHdrFlg = false;
        boolean procDtlFlg = false;
        boolean errFlg = false;
        BigDecimal preDsContrPK = BigDecimal.ZERO;
        BigDecimal preDsContrDtlPK = BigDecimal.ZERO;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            // START 2015/11/27 T.Tsuchida [QC#1210,MOD]
            if (!preDsContrPK.equals(sMsg.A.no(i).dsContrPk_A1.getValue())) {
                procHdrFlg = false;
            }
            if (!preDsContrDtlPK.equals(sMsg.A.no(i).dsContrDtlPk_A1.getValue())) {
                procDtlFlg = false;
            }
            preDsContrPK = sMsg.A.no(i).dsContrPk_A1.getValue();
            preDsContrDtlPK = sMsg.A.no(i).dsContrDtlPk_A1.getValue();

            if (procHdrFlg || procDtlFlg) {
                continue;
            }

            if (CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A1.getValue())) {
                if (!changeBillingHoldHdr(cMsg, sMsg.A.no(i))) {
                    errFlg = true;
                }
                procHdrFlg = true;
            } else if (CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A2.getValue())) {
                if (!changeBillingHoldDtl(cMsg, sMsg.A.no(i))) {
                    errFlg = true;
                }
                procDtlFlg = true;
            // mod start 2016/02/26 CSA Defect#2684
            } else if (CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A3.getValue()) && MTR_LB_NM.equals(sMsg.A.no(i).mtrLbDescTxt_A1.getValue())) {
            // mod end 2016/02/26 CSA Defect#2684
                if (!changeBillingHoldBllgMtrBase(cMsg, sMsg.A.no(i))) {
                    errFlg = true;
                }
            // mod start 2016/02/26 CSA Defect#2684
            } else if (CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A3.getValue()) && !MTR_LB_NM.equals(sMsg.A.no(i).mtrLbDescTxt_A1.getValue())) {
            // mod end 2016/02/26 CSA Defect#2684
                if (!changeBillingHoldBllgMtrUsg(cMsg, sMsg.A.no(i))) {
                    errFlg = true;
                }
            }
            // END 2015/11/27 T.Tsuchida [QC#1210,MOD]
        }
        if (errFlg) {
            cMsg.setMessageInfo(NSAM0394W);
        } else {
            cMsg.setMessageInfo(NSAM0200I);
        }
    }

    /**
     * Change Billing Hold for Contract Header
     * @param cMsg NSAL0700CMsg
     * @param aSMsg NSAL0700_ASMsg
     * @return boolean
     */
    public static boolean changeBillingHoldHdr(NSAL0700CMsg cMsg, NSAL0700_ASMsg aSMsg) {
        // START 2018/08/26 [QC#22987, ADD]
        if (existUnapprovedBllgHld(cMsg, aSMsg)) {
            setValue(aSMsg.xxGenlFldAreaTxt_A1, RTRN_MSG_UPDATE_FAILED_WF);
            return false;
        }
        // END 2018/08/26 [QC#22987, ADD]
        // START 2017/09/04 K.Kojima [QC#20816,ADD]
        String bllgHldFlg = changeBllgHldFLg(aSMsg.bllgHldFlg_A1.getValue());
        // END 2017/09/04 K.Kojima [QC#20816,ADD]

        // DS_CONTR
        // START 2017/09/04 K.Kojima [QC#20816,MOD]
        // if (!updateDsContr(cMsg, aSMsg)) {
        if (!updateDsContr(cMsg, aSMsg, bllgHldFlg)) {
        // END 2017/09/04 K.Kojima [QC#20816,MOD]
            return false;
        }

        // add start 2019/07/18 QC#51706
        updateSvcMemoForManBllgHld(cMsg, bllgHldFlg, aSMsg.dsContrPk_A1.getValue(), null, null);
        // add end 2019/07/18 QC#51706

        // START 2017/09/04 K.Kojima [QC#20816,DEL]
        // // DS_CONTR_DTL
        // DS_CONTR_DTL_STS_VTMsgArray outDtlStsVTMsgArray = getDtlStsV(cMsg, aSMsg);
        // if (outDtlStsVTMsgArray.getValidCount() == 0) {
        //     setValue(aSMsg.xxGenlFldAreaTxt_A1, RTRN_MSG_UPDATE_FAILED);
        //     EZDConnectionMgr.getInstance().rollback();
        //     return false;
        // }
        // // update DS_CONTR_DTL
        // if (!updateDsContrDtl(cMsg, aSMsg, outDtlStsVTMsgArray)) {
        //     return false;
        // }
        // END 2017/09/04 K.Kojima [QC#20816,DEL]

        // START 2017/09/04 K.Kojima [QC#20816,ADD]
        // DS_CONTR_DTL
        List<BigDecimal> dsContrDtlPkList = getDsContrDtlPkList(cMsg, aSMsg, bllgHldFlg);
        if (dsContrDtlPkList.size() > 0 && !updateDsContrDtl(cMsg, aSMsg, dsContrDtlPkList, bllgHldFlg)) {
            return false;
        }
        // END 2017/09/04 K.Kojima [QC#20816,ADD]

        // DS_CONTR_BLLG_MTR
        // START 2017/09/04 K.Kojima [QC#20816,MOD]
        // for (int i = 0; i < outDtlStsVTMsgArray.getValidCount(); i++) {
        for (int i = 0; i < dsContrDtlPkList.size(); i++) {
        // END 2017/09/04 K.Kojima [QC#20816,MOD]
            // START 2017/09/04 K.Kojima [QC#20816,ADD]
            BigDecimal dsContrDtlPk = dsContrDtlPkList.get(i);

            // DS_CONTR_PRC_EFF(Base)
            List<Map<String, Object>> dsContrPrcEffPkForBase = (List<Map<String, Object>>) getPrcEff(cMsg, dsContrDtlPk, null, FLG_ON_Y, null, bllgHldFlg);
            if (dsContrPrcEffPkForBase != null && !updateDsContrPrcEff(cMsg, aSMsg, dsContrPrcEffPkForBase, bllgHldFlg)) {
                return false;
            }
            // END 2017/09/04 K.Kojima [QC#20816,ADD]

            // START 2017/09/04 K.Kojima [QC#20816,MOD]
            // DS_CONTR_DTL_STS_VTMsg outDtlTMsg = outDtlStsVTMsgArray.no(i);
            // DS_CONTR_BLLG_MTR_STS_VTMsgArray outBllgMtrStsVTMsgArray = getBllgMtrStsV(cMsg, outDtlTMsg.dsContrDtlPk.getValue());
            // // update DS_CONTR_BLLG_MTR
            // if (outBllgMtrStsVTMsgArray != null && !updateDsContrBllgMtr(cMsg, aSMsg, outBllgMtrStsVTMsgArray)) {
            //     return false;
            // }
            List<BigDecimal> dsContrBllgMtrPkList = getDsContrBllgMtrPkList(cMsg, dsContrDtlPk, bllgHldFlg);
            if (dsContrBllgMtrPkList.size() > 0 && !updateDsContrBllgMtr(cMsg, aSMsg, dsContrBllgMtrPkList, bllgHldFlg)) {
                return false;
            }
            // END 2017/09/04 K.Kojima [QC#20816,MOD]
            // DS_CONTR_PRC_EFF
            // START 2017/09/04 K.Kojima [QC#20816,MOD]
            // for (int j = 0; j < outBllgMtrStsVTMsgArray.getValidCount(); j++) {
            for (int j = 0; j < dsContrBllgMtrPkList.size(); j++) {
            // END 2017/09/04 K.Kojima [QC#20816,MOD]
                // START 2017/09/04 K.Kojima [QC#20816,MOD]
                // DS_CONTR_BLLG_MTR_STS_VTMsg outBllgMtrStsVTMsg = outBllgMtrStsVTMsgArray.no(j);
                // List<Map<String, Object>> dsContrPrcEffPk = getPrcEff(cMsg, outBllgMtrStsVTMsg.dsContrDtlPk.getValue(), outBllgMtrStsVTMsg.dsContrBllgMtrPk.getValue(), null, null);
                BigDecimal dsContrBllgMtrPk = dsContrBllgMtrPkList.get(j);
                List<Map<String, Object>> dsContrPrcEffPk = getPrcEff(cMsg, dsContrDtlPk, dsContrBllgMtrPk, null, null, bllgHldFlg);
                // END 2017/09/04 K.Kojima [QC#20816,MOD]
                // update DS_CONTR_PRC_EFF
                // START 2017/09/04 K.Kojima [QC#20816,MOD]
                // if (dsContrPrcEffPk != null && !updateDsContrPrcEff(cMsg, aSMsg, dsContrPrcEffPk)) {
                if (dsContrPrcEffPk != null && !updateDsContrPrcEff(cMsg, aSMsg, dsContrPrcEffPk, bllgHldFlg)) {
                // END 2017/09/04 K.Kojima [QC#20816,MOD]
                    return false;
                }
            }
            // add start 2019/07/18 QC#51706
            updateSvcMemoForManBllgHld(cMsg, bllgHldFlg, aSMsg.dsContrPk_A1.getValue(), dsContrDtlPk, null);
            for (int j = 0; j < dsContrBllgMtrPkList.size(); j++) {
                updateSvcMemoForManBllgHld(cMsg, bllgHldFlg, aSMsg.dsContrPk_A1.getValue(), dsContrDtlPk, dsContrBllgMtrPkList.get(j));
            }
            // add end 2019/07/18 QC#51706
        }
        // insert SVC_MEMO
        if (!insertSvcMemo(cMsg, aSMsg)) {
            return false;
        }
// START 2016/12/13 [QC#16285, ADD]
        if (!insertSvcMemoHdr(cMsg, aSMsg)) {
            return false;
        }
// END   2016/12/13 [QC#16285, ADD]

        setValue(aSMsg.xxGenlFldAreaTxt_A1, RTRN_MSG_SUCCESS);
        EZDConnectionMgr.getInstance().commit();
        return true;
    }

    /**
     * Change Billing Hold for Contract Detail
     * @param cMsg NSAL0700CMsg
     * @param aSMsg NSAL0700_ASMsg
     * @return boolean
     */
    public static boolean changeBillingHoldDtl(NSAL0700CMsg cMsg, NSAL0700_ASMsg aSMsg) {
        // START 2017/09/04 K.Kojima [QC#20816,ADD]
        String bllgHldFlg = changeBllgHldFLg(aSMsg.bllgHldFlg_A2.getValue());
        // END 2017/09/04 K.Kojima [QC#20816,ADD]

        // update DS_CONTR_DTL
        // START 2017/09/04 K.Kojima [QC#20816,MOD]
        // if (!updateDsContrDtlByPk(cMsg, aSMsg)) {
        if (!updateDsContrDtlByPk(cMsg, aSMsg, bllgHldFlg)) {
        // END 2017/09/04 K.Kojima [QC#20816,MOD]
            return false;
        }

        // START 2017/09/04 K.Kojima [QC#20816,ADD]
        // DS_CONTR_PRC_EFF(Base)
        List<Map<String, Object>> dsContrPrcEffPkForBase = (List<Map<String, Object>>) getPrcEff(cMsg, aSMsg.dsContrDtlPk_A1.getValue(), null, FLG_ON_Y, null, bllgHldFlg);
        if (dsContrPrcEffPkForBase != null && !updateDsContrPrcEff(cMsg, aSMsg, dsContrPrcEffPkForBase, bllgHldFlg)) {
            return false;
        }
        // END 2017/09/04 K.Kojima [QC#20816,ADD]

        // DS_CONTR_BLLG_MTR
        // START 2017/09/04 K.Kojima [QC#20816,MOD]
        // DS_CONTR_BLLG_MTR_STS_VTMsgArray outBllgMtrStsVTMsgArray = getBllgMtrStsV(cMsg, aSMsg.dsContrDtlPk_A1.getValue());
        // // update DS_CONTR_BLLG_MTR
        // if (outBllgMtrStsVTMsgArray != null && !updateDsContrBllgMtr(cMsg, aSMsg, outBllgMtrStsVTMsgArray)) {
        //     return false;
        // }
        List<BigDecimal> dsContrBllgMtrPkList = getDsContrBllgMtrPkList(cMsg, aSMsg.dsContrDtlPk_A1.getValue(), bllgHldFlg);
        if (dsContrBllgMtrPkList.size() > 0 && !updateDsContrBllgMtr(cMsg, aSMsg, dsContrBllgMtrPkList, bllgHldFlg)) {
            return false;
        }
        // END 2017/09/04 K.Kojima [QC#20816,MOD]

        // DS_CONTR_PRC_EFF
        // START 2017/09/04 K.Kojima [QC#20816,MOD]
        // for (int j = 0; j < outBllgMtrStsVTMsgArray.getValidCount(); j++) {
        for (int j = 0; j < dsContrBllgMtrPkList.size(); j++) {
        // END 2017/09/04 K.Kojima [QC#20816,MOD]
            // START 2017/09/04 K.Kojima [QC#20816,MOD]
            // DS_CONTR_BLLG_MTR_STS_VTMsg outBllgMtrStsVTMsg = outBllgMtrStsVTMsgArray.no(j);
            // List<Map<String, Object>> dsContrPrcEffPk = getPrcEff(cMsg, outBllgMtrStsVTMsg.dsContrDtlPk.getValue(), outBllgMtrStsVTMsg.dsContrBllgMtrPk.getValue(), null, null);
            BigDecimal dsContrBllgMtrPk = dsContrBllgMtrPkList.get(j);
            List<Map<String, Object>> dsContrPrcEffPk = getPrcEff(cMsg, aSMsg.dsContrDtlPk_A1.getValue(), dsContrBllgMtrPk, null, null, bllgHldFlg);
            // END 2017/09/04 K.Kojima [QC#20816,MOD]
            // update DS_CONTR_PRC_EFF
            // START 2017/09/04 K.Kojima [QC#20816,MOD]
            // if (dsContrPrcEffPk != null && !updateDsContrPrcEff(cMsg, aSMsg, dsContrPrcEffPk)) {
            if (dsContrPrcEffPk != null && !updateDsContrPrcEff(cMsg, aSMsg, dsContrPrcEffPk, bllgHldFlg)) {
            // END 2017/09/04 K.Kojima [QC#20816,MOD]
                return false;
            }
        }
        // insert SVC_MEMO
        if (!insertSvcMemo(cMsg, aSMsg)) {
            return false;
        }
// START 2016/12/13 [QC#16285, ADD]
        if (!insertSvcMemoDtl(cMsg, aSMsg)) {
            return false;
        }
// END   2016/12/13 [QC#16285, ADD]

        // add start 2019/07/18 QC#51706
        updateSvcMemoForManBllgHld(cMsg, bllgHldFlg, aSMsg.dsContrPk_A1.getValue(), aSMsg.dsContrDtlPk_A1.getValue(), null);
        for (int i = 0; i < dsContrBllgMtrPkList.size(); i++) {
            updateSvcMemoForManBllgHld(cMsg, bllgHldFlg, aSMsg.dsContrPk_A1.getValue(), aSMsg.dsContrDtlPk_A1.getValue(), dsContrBllgMtrPkList.get(i));
        }
        // add end 2019/07/18 QC#51706

        setValue(aSMsg.xxGenlFldAreaTxt_A1, RTRN_MSG_SUCCESS);
        EZDConnectionMgr.getInstance().commit();
        return true;
    }

    /**
     * Change Billing Hold for Billing Meter Base
     * @param cMsg NSAL0700CMsg
     * @param aSMsg NSAL0700_ASMsg
     * @return boolean
     */
    public static boolean changeBillingHoldBllgMtrBase(NSAL0700CMsg cMsg, NSAL0700_ASMsg aSMsg) {
        // START 2017/09/04 K.Kojima [QC#20816,ADD]
        String bllgHldFlg = changeBllgHldFLg(aSMsg.bllgHldFlg_A3.getValue());
        // END 2017/09/04 K.Kojima [QC#20816,ADD]

        // START 2017/09/04 K.Kojima [QC#20816,DEL]
        // // update DS_CONTR_DTL
        // if (!updateDsContrDtlByPk(cMsg, aSMsg)) {
        //     return false;
        // }
        // END 2017/09/04 K.Kojima [QC#20816,DEL]

        // DS_CONTR_PRC_EFF
        // START 2017/09/04 K.Kojima [QC#20816,MOD]
        // List<Map<String, Object>> dsContrPrcEffPk = (List<Map<String, Object>>) getPrcEff(cMsg, aSMsg.dsContrDtlPk_A1.getValue(), null, FLG_ON_Y, null);
        List<Map<String, Object>> dsContrPrcEffPk = (List<Map<String, Object>>) getPrcEff(cMsg, aSMsg.dsContrDtlPk_A1.getValue(), null, FLG_ON_Y, null, bllgHldFlg);
        // END 2017/09/04 K.Kojima [QC#20816,MOD]
        // update DS_CONTR_PRC_EFF
        // START 2017/09/04 K.Kojima [QC#20816,MOD]
        // if (dsContrPrcEffPk != null && !updateDsContrPrcEff(cMsg, aSMsg, dsContrPrcEffPk)) {
        if (dsContrPrcEffPk != null && !updateDsContrPrcEff(cMsg, aSMsg, dsContrPrcEffPk, bllgHldFlg)) {
        // END 2017/09/04 K.Kojima [QC#20816,MOD]
            return false;
        }
        // insert SVC_MEMO
        if (!insertSvcMemo(cMsg, aSMsg)) {
            return false;
        }
// START 2016/12/13 [QC#16285, ADD]
        if (!insertSvcMemoDtl(cMsg, aSMsg)) {
            return false;
        }
// END   2016/12/13 [QC#16285, ADD]

        setValue(aSMsg.xxGenlFldAreaTxt_A1, RTRN_MSG_SUCCESS);
        EZDConnectionMgr.getInstance().commit();
        return true;
    }

    /**
     * Change Billing Hold for Billing Meter Usg
     * @param cMsg NSAL0700CMsg
     * @param aSMsg NSAL0700_ASMsg
     * @return boolean
     */
    public static boolean changeBillingHoldBllgMtrUsg(NSAL0700CMsg cMsg, NSAL0700_ASMsg aSMsg) {
        // START 2017/09/04 K.Kojima [QC#20816,ADD]
        String bllgHldFlg = changeBllgHldFLg(aSMsg.bllgHldFlg_A3.getValue());
        // END 2017/09/04 K.Kojima [QC#20816,ADD]

        // update DS_CONTR_BLLG_MTR
        // START 2017/09/04 K.Kojima [QC#20816,MOD]
        // if (!updateDsContrBllgMtrByPk(cMsg, aSMsg)) {
        if (!updateDsContrBllgMtrByPk(cMsg, aSMsg, bllgHldFlg)) {
        // END 2017/09/04 K.Kojima [QC#20816,MOD]
            return false;
        }
        // DS_CONTR_PRC_EFF
        // START 2017/09/04 K.Kojima [QC#20816,MOD]
        // List<Map<String, Object>> dsContrPrcEffPk = getPrcEff(cMsg, aSMsg.dsContrDtlPk_A1.getValue(), aSMsg.dsContrBllgMtrPk_A1.getValue(), null, ZYPConstant.FLG_ON_Y);
        List<Map<String, Object>> dsContrPrcEffPk = getPrcEff(cMsg, aSMsg.dsContrDtlPk_A1.getValue(), aSMsg.dsContrBllgMtrPk_A1.getValue(), null, ZYPConstant.FLG_ON_Y, bllgHldFlg);
        // END 2017/09/04 K.Kojima [QC#20816,MOD]
        // update DS_CONTR_PRC_EFF
        // START 2017/09/04 K.Kojima [QC#20816,MOD]
        // if (dsContrPrcEffPk != null && !updateDsContrPrcEff(cMsg, aSMsg, dsContrPrcEffPk)) {
        if (dsContrPrcEffPk != null && !updateDsContrPrcEff(cMsg, aSMsg, dsContrPrcEffPk, bllgHldFlg)) {
        // END 2017/09/04 K.Kojima [QC#20816,MOD]
            return false;
        }
        // insert SVC_MEMO
        if (!insertSvcMemo(cMsg, aSMsg)) {
            return false;
        }
// START 2016/12/13 [QC#16285, ADD]
        if (!insertSvcMemoBllgMtr(cMsg, aSMsg)) {
            return false;
        }
// END   2016/12/13 [QC#16285, ADD]

        // add start 2019/07/18 QC#51706
        updateSvcMemoForManBllgHld(cMsg, bllgHldFlg, aSMsg.dsContrPk_A1.getValue(), aSMsg.dsContrDtlPk_A1.getValue(), aSMsg.dsContrBllgMtrPk_A1.getValue());
        // add end 2019/07/18 QC#51706

        setValue(aSMsg.xxGenlFldAreaTxt_A1, RTRN_MSG_SUCCESS);
        EZDConnectionMgr.getInstance().commit();
        return true;
    }

    // START 2017/09/04 K.Kojima [QC#20816,DEL]
    // /**
    //  * @param cMsg
    //  * @param aSMsg
    //  * @param bllgHldFlg
    //  * @return inDtlStsVTMsg
    //  */
    // private static DS_CONTR_DTL_STS_VTMsg setParamForDtl(NSAL0700CMsg cMsg, NSAL0700_ASMsg aSMsg) {
    //     DS_CONTR_DTL_STS_VTMsg inDtlStsVTMsg = new DS_CONTR_DTL_STS_VTMsg();
    //     inDtlStsVTMsg.setSQLID("006");
    //     inDtlStsVTMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
    //     inDtlStsVTMsg.setConditionValue("dsContrPk01", aSMsg.dsContrPk_A1.getValue());
    //     inDtlStsVTMsg.setConditionValue("dsContrCtrlStsCd01", DS_CONTR_CTRL_STS.EXPIRED);
    //     inDtlStsVTMsg.setConditionValue("dsContrCtrlStsCd02", DS_CONTR_CTRL_STS.CANCELLED);
    //     inDtlStsVTMsg.setConditionValue("dsContrCtrlStsCd03", DS_CONTR_CTRL_STS.TERMINATED);
    //     inDtlStsVTMsg.setConditionValue("dsContrCtrlStsCd04", DS_CONTR_CTRL_STS.TERMINATED_HOLD);
    //     inDtlStsVTMsg.setConditionValue("dsContrCtrlStsCd05", DS_CONTR_CTRL_STS.EXPIRED_HOLD);
    //     inDtlStsVTMsg.setConditionValue("bllgHldFlg01", FLG_OFF_N);
    //     return inDtlStsVTMsg;
    // }
    // END 2017/09/04 K.Kojima [QC#20816,DEL]

    // START 2017/09/04 K.Kojima [QC#20816,DEL]
    // /**
    //  * @param cMsg
    //  * @param outDtlTMsg
    //  * @param bllgHldFlg
    //  * @return
    //  */
    // private static DS_CONTR_BLLG_MTR_STS_VTMsg setParamForBllgMtr(NSAL0700CMsg cMsg, BigDecimal dsContrDtlPk) {
    //     DS_CONTR_BLLG_MTR_STS_VTMsg inBllgMtrStsVTMsg = new DS_CONTR_BLLG_MTR_STS_VTMsg();
    //     inBllgMtrStsVTMsg.setSQLID("003");
    //     inBllgMtrStsVTMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
    //     inBllgMtrStsVTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
    //     inBllgMtrStsVTMsg.setConditionValue("dsContrCtrlStsCd01", DS_CONTR_CTRL_STS.EXPIRED);
    //     inBllgMtrStsVTMsg.setConditionValue("dsContrCtrlStsCd02", DS_CONTR_CTRL_STS.CANCELLED);
    //     inBllgMtrStsVTMsg.setConditionValue("dsContrCtrlStsCd03", DS_CONTR_CTRL_STS.TERMINATED);
    //     inBllgMtrStsVTMsg.setConditionValue("dsContrCtrlStsCd04", DS_CONTR_CTRL_STS.TERMINATED_HOLD);
    //     inBllgMtrStsVTMsg.setConditionValue("dsContrCtrlStsCd05", DS_CONTR_CTRL_STS.EXPIRED_HOLD);
    //     inBllgMtrStsVTMsg.setConditionValue("bllgHldFlg01", FLG_OFF_N);
    //     return inBllgMtrStsVTMsg;
    // }
    // END 2017/09/04 K.Kojima [QC#20816,DEL]

    /**
     * @param cMsg
     * @param aSMsg
     * @return
     */
    private static SVC_MEMOTMsg setParamForSvcMemo(NSAL0700CMsg cMsg, NSAL0700_ASMsg aSMsg) {
        SVC_MEMOTMsg tmsg = new SVC_MEMOTMsg();
        setValue(tmsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(tmsg.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MEMO_SQ));
        setValue(tmsg.svcMemoCatgCd, SVC_MEMO_CATG.CONTRACT_MEMO);
        setValue(tmsg.svcMemoTpCd, SVC_MEMO_TP.PUT_CONTRACTS_ON_BILLING_HOLD);
        setValue(tmsg.svcCmntTxt, cMsg.svcCmntTxt_H1);
        setValue(tmsg.dsContrPk, aSMsg.dsContrPk_A1);
        if (BigDecimal.ZERO.equals(aSMsg.dsContrDtlPk_A1.getValue())) {
            setValue(tmsg.dsContrDtlPk, aSMsg.dsContrDtlPk_A1);
        }
        setValue(tmsg.lastUpdUsrId, cMsg.getUserID());
        setValue(tmsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime(DATE_FORMAT));
        setValue(tmsg.svcMemoRsnCd, cMsg.svcMemoRsnCd_H3);
        return tmsg;
    }

// START 2016/12/13 [QC#16285, ADD]
    private static SVC_MEMOTMsg setParamForSvcMemoCommon(NSAL0700CMsg cMsg, NSAL0700_ASMsg aSMsg) {
        SVC_MEMOTMsg tmsg = new SVC_MEMOTMsg();
        setValue(tmsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(tmsg.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MEMO_SQ));
        setValue(tmsg.svcMemoCatgCd, SVC_MEMO_CATG.CONTRACT_MEMO);
        setValue(tmsg.svcMemoTpCd, SVC_MEMO_TP.BILLING_HOLD_REASON);
        setValue(tmsg.dsContrPk, aSMsg.dsContrPk_A1);
        setValue(tmsg.lastUpdUsrId, cMsg.getUserID());
        setValue(tmsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime(DATE_FORMAT));
        setValue(tmsg.svcMemoRsnCd, SVC_MEMO_RSN.BILLING_HOLD_REASON);

        return tmsg;
    }

    private static SVC_MEMOTMsg setParamForSvcMemoHdr(NSAL0700CMsg cMsg, NSAL0700_ASMsg aSMsg) {
        SVC_MEMOTMsg tMsg = setParamForSvcMemoCommon(cMsg, aSMsg);

        String dsContrNum = getContrNum(aSMsg);
        String svcCmntTxt = String.format(SVC_MEMO_BLLG_RSN_CMNT_TXT_HDR, dsContrNum);
        setValue(tMsg.svcCmntTxt, svcCmntTxt);

        return tMsg;
    }

    private static SVC_MEMOTMsg setParamForSvcMemoDtl(NSAL0700CMsg cMsg, NSAL0700_ASMsg aSMsg) {
        SVC_MEMOTMsg tMsg = setParamForSvcMemoCommon(cMsg, aSMsg);

        if (ZYPCommonFunc.hasValue(aSMsg.dsContrDtlPk_A1)) {
            setValue(tMsg.dsContrDtlPk, aSMsg.dsContrDtlPk_A1);
        }

        String dsContrNum = getContrNum(aSMsg);
        String svcCmntTxt = String.format(SVC_MEMO_BLLG_RSN_CMNT_TXT_DTL, aSMsg.serNum_A1.getValue(), dsContrNum);
        setValue(tMsg.svcCmntTxt, svcCmntTxt);

        return tMsg;
    }

    private static SVC_MEMOTMsg setParamForSvcMemoBllgMtr(NSAL0700CMsg cMsg, NSAL0700_ASMsg aSMsg) {
        SVC_MEMOTMsg tMsg = setParamForSvcMemoCommon(cMsg, aSMsg);

        if (ZYPCommonFunc.hasValue(aSMsg.dsContrDtlPk_A1)) {
            setValue(tMsg.dsContrDtlPk, aSMsg.dsContrDtlPk_A1);
        }
        if (ZYPCommonFunc.hasValue(aSMsg.dsContrBllgMtrPk_A1)) {
            setValue(tMsg.svcMemoTrxNum, aSMsg.dsContrBllgMtrPk_A1.getValue().toPlainString());
            setValue(tMsg.svcMemoTrxNm, SVC_MEMO_TRX_NM_BLLG_MTR);
        }

        String dsContrNum = getContrNum(aSMsg);
        String svcCmntTxt = String.format(SVC_MEMO_BLLG_RSN_CMNT_TXT_MTR,
                aSMsg.serNum_A1.getValue(), dsContrNum, aSMsg.mtrLbDescTxt_A1.getValue());
        setValue(tMsg.svcCmntTxt, svcCmntTxt);

        return tMsg;
    }

    private static String getContrNum(NSAL0700_ASMsg aSMsg) {
        String xxScrItem34Txt = aSMsg.xxScrItem34Txt_A1.getValue();
        return xxScrItem34Txt.substring(xxScrItem34Txt.indexOf("-") + 1);
    }
// END  2016/12/13 [QC#16285, ADD]

    // START 2017/09/04 K.Kojima [QC#20816,DEL]
    // /**
    //  * @param cMsg
    //  * @param aSMsg
    //  * @return
    //  */
    // private static DS_CONTR_DTL_STS_VTMsgArray getDtlStsV(NSAL0700CMsg cMsg, NSAL0700_ASMsg aSMsg) {
    //     DS_CONTR_DTL_STS_VTMsg inDtlStsVTMsg = setParamForDtl(cMsg, aSMsg);
    //     DS_CONTR_DTL_STS_VTMsgArray outDtlStsVTMsgArray = (DS_CONTR_DTL_STS_VTMsgArray) EZDTBLAccessor.findByCondition(inDtlStsVTMsg);
    //     return outDtlStsVTMsgArray;
    // }
    // END 2017/09/04 K.Kojima [QC#20816,DEL]

    /**
     * getDsContrDtlPkList
     * @param cMsg
     * @param aSMsg
     * @param bllgHldFlg
     * @return List<String>
     */
    private static List<BigDecimal> getDsContrDtlPkList(NSAL0700CMsg cMsg, NSAL0700_ASMsg aSMsg, String bllgHldFlg) {
        S21SsmEZDResult ssmResult = NSAL0700Query.getInstance().getDsContrDtlPkList(cMsg.glblCmpyCd.getValue(), aSMsg.dsContrPk_A1.getValue(), bllgHldFlg);
        if (!ssmResult.isCodeNormal()) {
            return new ArrayList<BigDecimal>();
        }
        return (List<BigDecimal>) ssmResult.getResultObject();
    }

    // START 2017/09/04 K.Kojima [QC#20816,DEL]
    // /**
    //  * @param cMsg NSAL0700CMsg
    //  * @param dsContrDtlPk BigDecimal
    //  * @return
    //  */
    // private static DS_CONTR_BLLG_MTR_STS_VTMsgArray getBllgMtrStsV(NSAL0700CMsg cMsg, BigDecimal dsContrDtlPk) {
    //     DS_CONTR_BLLG_MTR_STS_VTMsg inBllgMtrStsVTMsg = setParamForBllgMtr(cMsg, dsContrDtlPk);
    //     DS_CONTR_BLLG_MTR_STS_VTMsgArray outBllgMtrStsVTMsgArray = (DS_CONTR_BLLG_MTR_STS_VTMsgArray) EZDTBLAccessor.findByCondition(inBllgMtrStsVTMsg);
    //     return outBllgMtrStsVTMsgArray;
    // }
    // END 2017/09/04 K.Kojima [QC#20816,DEL]

    /**
     * getDsContrBllgMtrPkList
     * @param cMsg
     * @param dsContrDtlPk
     * @param bllgHldFlg
     * @return List<String>
     */
    private static List<BigDecimal> getDsContrBllgMtrPkList(NSAL0700CMsg cMsg, BigDecimal dsContrDtlPk, String bllgHldFlg) {
        S21SsmEZDResult ssmResult = NSAL0700Query.getInstance().getDsContrBllgMtrPkList(cMsg.glblCmpyCd.getValue(), dsContrDtlPk, bllgHldFlg);
        if (!ssmResult.isCodeNormal()) {
            return new ArrayList<BigDecimal>();
        }
        return (List<BigDecimal>) ssmResult.getResultObject();
    }

    /**
     * @param cMsg
     * @param outBllgMtrStsVTMsg
     * @param bllgHldFlg
     * @return
     */
    @SuppressWarnings("unchecked")
    // START 2017/09/04 K.Kojima [QC#20816,MOD]
    // private static List<Map<String, Object>> getPrcEff(NSAL0700CMsg cMsg, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String baseChrg, String usgChrg) {
    private static List<Map<String, Object>> getPrcEff(NSAL0700CMsg cMsg, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String baseChrg, String usgChrg, String bllgHldFlg) {
    // END 2017/09/04 K.Kojima [QC#20816,MOD]
        // START 2017/09/04 K.Kojima [QC#20816,MOD]
        // S21SsmEZDResult ssmResult = NSAL0700Query.getInstance().getDsContrPrcEffPk(cMsg.glblCmpyCd.getValue(), dsContrDtlPk, dsContrBllgMtrPk, baseChrg, usgChrg);
        S21SsmEZDResult ssmResult = NSAL0700Query.getInstance().getDsContrPrcEffPk(cMsg.glblCmpyCd.getValue(), dsContrDtlPk, dsContrBllgMtrPk, baseChrg, usgChrg, bllgHldFlg);
        // END 2017/09/04 K.Kojima [QC#20816,MOD]
        if (!ssmResult.isCodeNormal()) {
            return null;
        }
        return (List<Map<String, Object>>) ssmResult.getResultObject();
    }

    /**
     * @param cMsg
     * @param aSMsg
     * @param bllgHldFlg
     */
    // START 2017/09/04 K.Kojima [QC#20816,MOD]
    // private static boolean updateDsContr(NSAL0700CMsg cMsg, NSAL0700_ASMsg aSMsg) {
    private static boolean updateDsContr(NSAL0700CMsg cMsg, NSAL0700_ASMsg aSMsg, String bllgHldFlg) {
    // END 2017/09/04 K.Kojima [QC#20816,MOD]
        DS_CONTRTMsg inHdrTMsg = new DS_CONTRTMsg();
        inHdrTMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
        inHdrTMsg.dsContrPk.setValue(aSMsg.dsContrPk_A1.getValue());
        DS_CONTRTMsg outHdrTMsg = (DS_CONTRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inHdrTMsg);
        if (outHdrTMsg == null) {
            setValue(aSMsg.xxGenlFldAreaTxt_A1, RTRN_MSG_UPDATE_FAILED);
            EZDConnectionMgr.getInstance().rollback();
            return false;
        }

        // START 2015/12/11 J.Kim [QC#1659,ADD]
        if (!isSameTimeDsContr(outHdrTMsg, aSMsg, cMsg)) {
            setValue(aSMsg.xxGenlFldAreaTxt_A1, RTRN_MSG_FAILED);
            EZDConnectionMgr.getInstance().rollback();
            return false;
        }
        // END   2015/12/11 J.Kim [QC#1659,ADD]

        // update DS_CONTR
        // START 2017/09/04 K.Kojima [QC#20816,MOD]
        // setValue(outHdrTMsg.bllgHldFlg, FLG_ON_Y);
        setValue(outHdrTMsg.bllgHldFlg, bllgHldFlg);
        // END 2017/09/04 K.Kojima [QC#20816,MOD]
        EZDTBLAccessor.update(outHdrTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(outHdrTMsg.getReturnCode())) {
            setValue(aSMsg.xxGenlFldAreaTxt_A1, RTRN_MSG_UPDATE_FAILED);
            EZDConnectionMgr.getInstance().rollback();
            return false;
        }
        // insert DS_CONTR_TRK
        if (!insertDsContrTrkForHdr(cMsg, aSMsg)) {
            return false;
        }
        return true;
    }

    /**
     * @param cMsg
     * @param aSMsg
     * @param bllgHldFlg
     */
    // START 2017/09/04 K.Kojima [QC#20816,MOD]
    // private static boolean updateDsContrDtlByPk(NSAL0700CMsg cMsg, NSAL0700_ASMsg aSMsg) {
    private static boolean updateDsContrDtlByPk(NSAL0700CMsg cMsg, NSAL0700_ASMsg aSMsg, String bllgHldFlg) {
    // END 2017/09/04 K.Kojima [QC#20816,MOD]
        DS_CONTR_DTLTMsg inDtlTMsg = new DS_CONTR_DTLTMsg();
        setValue(inDtlTMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
        setValue(inDtlTMsg.dsContrDtlPk, aSMsg.dsContrDtlPk_A1.getValue());
        // START 2015/11/27 T.Tsuchida [QC#1210,MOD]
        //DS_CONTR_DTLTMsg outDtlTMsg = (DS_CONTR_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdate(inDtlTMsg);
        DS_CONTR_DTLTMsg outDtlTMsg = (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inDtlTMsg);
        // END 2015/11/27 T.Tsuchida [QC#1210,MOD]
        if (outDtlTMsg == null) {
            setValue(aSMsg.xxGenlFldAreaTxt_A1, RTRN_MSG_UPDATE_FAILED);
            EZDConnectionMgr.getInstance().rollback();
            return false;
        }

        // START 2015/12/11 J.Kim [QC#1659,ADD]
        if (!isSameTimeDsContrDtl(outDtlTMsg, aSMsg, cMsg)) {
            setValue(aSMsg.xxGenlFldAreaTxt_A1, RTRN_MSG_FAILED);
            EZDConnectionMgr.getInstance().rollback();
            return false;
        }
        // END   2015/12/11 J.Kim [QC#1659,ADD]

        // START 2017/09/04 K.Kojima [QC#20816,MOD]
        // setValue(outDtlTMsg.bllgHldFlg, FLG_ON_Y);
        setValue(outDtlTMsg.bllgHldFlg, bllgHldFlg);
        // END 2017/09/04 K.Kojima [QC#20816,MOD]
        S21FastTBLAccessor.update(outDtlTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(outDtlTMsg.getReturnCode())) {
            setValue(aSMsg.xxGenlFldAreaTxt_A1, RTRN_MSG_UPDATE_FAILED);
            EZDConnectionMgr.getInstance().rollback();
            return false;
        }
        // insert DS_CONTR_TRK
        if (!insertDsContrTrkForDtl(cMsg, aSMsg, aSMsg.dsContrDtlPk_A1.getValue())) {
            return false;
        }
        return true;
    }

    // START 2017/09/04 K.Kojima [QC#20816,DEL]
    // /**
    //  * @param cMsg
    //  * @param aSMsg
    //  * @param outBllgMtrStsVTMsgArray
    //  */
    // private static boolean updateDsContrBllgMtr(NSAL0700CMsg cMsg, NSAL0700_ASMsg aSMsg, DS_CONTR_BLLG_MTR_STS_VTMsgArray outBllgMtrStsVTMsgArray) {
    //     for (int j = 0; j < outBllgMtrStsVTMsgArray.getValidCount(); j++) {
    //         DS_CONTR_BLLG_MTR_STS_VTMsg outBllgMtrStsVTMsg = outBllgMtrStsVTMsgArray.no(j);
    //         DS_CONTR_BLLG_MTRTMsg inBllgMtrTMsg = new DS_CONTR_BLLG_MTRTMsg();
    //         setValue(inBllgMtrTMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
    //         setValue(inBllgMtrTMsg.dsContrBllgMtrPk, outBllgMtrStsVTMsg.dsContrBllgMtrPk.getValue());
    //         // START 2015/11/27 T.Tsuchida [QC#1210,MOD]
    //         //DS_CONTR_BLLG_MTRTMsg outBllgMtrTMsg = (DS_CONTR_BLLG_MTRTMsg) S21FastTBLAccessor.findByKeyForUpdate(inBllgMtrTMsg);
    //         DS_CONTR_BLLG_MTRTMsg outBllgMtrTMsg = (DS_CONTR_BLLG_MTRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inBllgMtrTMsg);
    //         // END 2015/11/27 T.Tsuchida [QC#1210,MOD]
    //         if (outBllgMtrTMsg == null) {
    //             setValue(aSMsg.xxGenlFldAreaTxt_A1, RTRN_MSG_UPDATE_FAILED);
    //             EZDConnectionMgr.getInstance().rollback();
    //             return false;
    //         }
    //         setValue(outBllgMtrTMsg.bllgHldFlg, FLG_ON_Y);
    //         S21FastTBLAccessor.update(outBllgMtrTMsg);
    //         if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(outBllgMtrTMsg.getReturnCode())) {
    //             setValue(aSMsg.xxGenlFldAreaTxt_A1, RTRN_MSG_UPDATE_FAILED);
    //             EZDConnectionMgr.getInstance().rollback();
    //             return false;
    //         }
    //         // insert DS_CONTR_TRK
    //         if (!insertDsContrTrkBllgMtr(cMsg, aSMsg, outBllgMtrTMsg.dsContrDtlPk.getValue(), outBllgMtrStsVTMsg.dsContrBllgMtrPk.getValue())) {
    //             return false;
    //         }
    //     }
    //     return true;
    // }
    // END 2017/09/04 K.Kojima [QC#20816,DEL]

    // START 2017/09/04 K.Kojima [QC#20816,ADD]
    /**
     * @param cMsg
     * @param aSMsg
     * @param dsContrBllgMtrPkList
     * @param bllgHldFlg
     * @return boolean
     */
    private static boolean updateDsContrBllgMtr(NSAL0700CMsg cMsg, NSAL0700_ASMsg aSMsg, List<BigDecimal> dsContrBllgMtrPkList, String bllgHldFlg) {
        for (int j = 0; j < dsContrBllgMtrPkList.size(); j++) {
            BigDecimal dsContrBllgMtrPk = dsContrBllgMtrPkList.get(j);
            
            DS_CONTR_BLLG_MTRTMsg inBllgMtrTMsg = new DS_CONTR_BLLG_MTRTMsg();
            setValue(inBllgMtrTMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
            setValue(inBllgMtrTMsg.dsContrBllgMtrPk, dsContrBllgMtrPk);
            DS_CONTR_BLLG_MTRTMsg outBllgMtrTMsg = (DS_CONTR_BLLG_MTRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inBllgMtrTMsg);
            if (outBllgMtrTMsg == null) {
                setValue(aSMsg.xxGenlFldAreaTxt_A1, RTRN_MSG_UPDATE_FAILED);
                EZDConnectionMgr.getInstance().rollback();
                return false;
            }
            setValue(outBllgMtrTMsg.bllgHldFlg, bllgHldFlg);
            S21FastTBLAccessor.update(outBllgMtrTMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(outBllgMtrTMsg.getReturnCode())) {
                setValue(aSMsg.xxGenlFldAreaTxt_A1, RTRN_MSG_UPDATE_FAILED);
                EZDConnectionMgr.getInstance().rollback();
                return false;
            }
            // insert DS_CONTR_TRK
            if (!insertDsContrTrkBllgMtr(cMsg, aSMsg, outBllgMtrTMsg.dsContrDtlPk.getValue(), dsContrBllgMtrPk)) {
                return false;
            }
        }
        return true;
    }
    // END 2017/09/04 K.Kojima [QC#20816,ADD]

    /**
     * @param cMsg
     * @param aSMsg
     * @param outBllgMtrStsVTMsgArray
     * @param bllgHldFlg
     */
    // START 2017/09/04 K.Kojima [QC#20816,MOD]
    // private static boolean updateDsContrBllgMtrByPk(NSAL0700CMsg cMsg, NSAL0700_ASMsg aSMsg) {
    private static boolean updateDsContrBllgMtrByPk(NSAL0700CMsg cMsg, NSAL0700_ASMsg aSMsg, String bllgHldFlg) {
    // END 2017/09/04 K.Kojima [QC#20816,MOD]
        DS_CONTR_BLLG_MTRTMsg inBllgMtrTMsg = new DS_CONTR_BLLG_MTRTMsg();
        setValue(inBllgMtrTMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
        setValue(inBllgMtrTMsg.dsContrBllgMtrPk, aSMsg.dsContrBllgMtrPk_A1.getValue());
        // START 2015/11/27 T.Tsuchida [QC#1210,MOD]
        //DS_CONTR_BLLG_MTRTMsg outBllgMtrTMsg = (DS_CONTR_BLLG_MTRTMsg) S21FastTBLAccessor.findByKeyForUpdate(inBllgMtrTMsg);
        DS_CONTR_BLLG_MTRTMsg outBllgMtrTMsg = (DS_CONTR_BLLG_MTRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inBllgMtrTMsg);
        // END 2015/11/27 T.Tsuchida [QC#1210,MOD]
        if (outBllgMtrTMsg == null) {
            setValue(aSMsg.xxGenlFldAreaTxt_A1, RTRN_MSG_UPDATE_FAILED);
            EZDConnectionMgr.getInstance().rollback();
            return false;
        }

        // START 2015/12/11 J.Kim [QC#1659,ADD]
        if (!isSameTimeDsContrBllgmtr(outBllgMtrTMsg, aSMsg, cMsg)) {
            setValue(aSMsg.xxGenlFldAreaTxt_A1, RTRN_MSG_FAILED);
            EZDConnectionMgr.getInstance().rollback();
            return false;
        }
        // END   2015/12/11 J.Kim [QC#1659,ADD]

        // START 2017/09/04 K.Kojima [QC#20816,MOD]
        // setValue(outBllgMtrTMsg.bllgHldFlg, FLG_ON_Y);
        setValue(outBllgMtrTMsg.bllgHldFlg, bllgHldFlg);
        // END 2017/09/04 K.Kojima [QC#20816,MOD]
        S21FastTBLAccessor.update(outBllgMtrTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(outBllgMtrTMsg.getReturnCode())) {
            setValue(aSMsg.xxGenlFldAreaTxt_A1, RTRN_MSG_UPDATE_FAILED);
            EZDConnectionMgr.getInstance().rollback();
            return false;
        }
        // insert DS_CONTR_TRK
        if (!insertDsContrTrkBllgMtr(cMsg, aSMsg, outBllgMtrTMsg.dsContrDtlPk.getValue(), aSMsg.dsContrBllgMtrPk_A1.getValue())) {
            return false;
        }
        return true;
    }

    /**
     * @param cMsg
     * @param aSMsg
     * @param dsContrPrcEffPkList
     * @param bllgHldFlg
     */
    // START 2017/09/04 K.Kojima [QC#20816,MOD]
    // private static boolean updateDsContrPrcEff(NSAL0700CMsg cMsg, NSAL0700_ASMsg aSMsg, List<Map<String, Object>> dsContrPrcEffPkList) {
    private static boolean updateDsContrPrcEff(NSAL0700CMsg cMsg, NSAL0700_ASMsg aSMsg, List<Map<String, Object>> dsContrPrcEffPkList, String bllgHldFlg) {
    // END 2017/09/04 K.Kojima [QC#20816,MOD]
        BigDecimal outprcEffPk = null;
        for (Map<String, Object> outMap : dsContrPrcEffPkList) {
            outprcEffPk = (BigDecimal) outMap.get("DS_CONTR_PRC_EFF_PK");
            DS_CONTR_PRC_EFFTMsg inPrcEffTMsg = new DS_CONTR_PRC_EFFTMsg();
            setValue(inPrcEffTMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
            setValue(inPrcEffTMsg.dsContrPrcEffPk, outprcEffPk);
            // START 2015/11/27 T.Tsuchida [QC#1210,MOD]
            //DS_CONTR_PRC_EFFTMsg outPrcEffTMsg = (DS_CONTR_PRC_EFFTMsg) S21FastTBLAccessor.findByKeyForUpdate(inPrcEffTMsg);
            DS_CONTR_PRC_EFFTMsg outPrcEffTMsg = (DS_CONTR_PRC_EFFTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inPrcEffTMsg);
            // END 2015/11/27 T.Tsuchida [QC#1210,MOD]
            if (outPrcEffTMsg == null) {
                setValue(aSMsg.xxGenlFldAreaTxt_A1, RTRN_MSG_UPDATE_FAILED);
                EZDConnectionMgr.getInstance().rollback();
                return false;
            }
            // START 2017/09/04 K.Kojima [QC#20816,MOD]
            // setValue(outPrcEffTMsg.bllgHldFlg, FLG_ON_Y);
            setValue(outPrcEffTMsg.bllgHldFlg, bllgHldFlg);
            // END 2017/09/04 K.Kojima [QC#20816,MOD]
            S21FastTBLAccessor.update(outPrcEffTMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(outPrcEffTMsg.getReturnCode())) {
                setValue(aSMsg.xxGenlFldAreaTxt_A1, RTRN_MSG_UPDATE_FAILED);
                EZDConnectionMgr.getInstance().rollback();
                return false;
            }
            // insert DS_CONTR_TRK
            if (!insertDsContrTrkPrcEff(cMsg, aSMsg, outPrcEffTMsg)) {
                return false;
            }
        }
        return true;
    }

    // START 2017/09/04 K.Kojima [QC#20816,DEL]
    // /**
    //  * @param cMsg
    //  * @param aSMsg
    //  * @param outDtlStsVTMsgArray
    //  */
    // private static boolean updateDsContrDtl(NSAL0700CMsg cMsg, NSAL0700_ASMsg aSMsg, DS_CONTR_DTL_STS_VTMsgArray outDtlStsVTMsgArray) {
    //     for (int i = 0; i < outDtlStsVTMsgArray.getValidCount(); i++) {
    //         DS_CONTR_DTL_STS_VTMsg outDtlStsVTMsg = outDtlStsVTMsgArray.no(i);
    //         DS_CONTR_DTLTMsg inDtlTMsg = new DS_CONTR_DTLTMsg();
    //         setValue(inDtlTMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
    //         setValue(inDtlTMsg.dsContrDtlPk, outDtlStsVTMsg.dsContrDtlPk.getValue());
    //         // START 2015/11/27 T.Tsuchida [QC#1210,MOD]
    //         //DS_CONTR_DTLTMsg outDtlTMsg = (DS_CONTR_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdate(inDtlTMsg);
    //         DS_CONTR_DTLTMsg outDtlTMsg = (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inDtlTMsg);
    //         // END 2015/11/27 T.Tsuchida [QC#1210,MOD]
    //         if (outDtlTMsg == null) {
    //             setValue(aSMsg.xxGenlFldAreaTxt_A1, RTRN_MSG_UPDATE_FAILED);
    //             EZDConnectionMgr.getInstance().rollback();
    //             return false;
    //         }
    //         setValue(outDtlTMsg.bllgHldFlg, FLG_ON_Y);
    //         S21FastTBLAccessor.update(outDtlTMsg);
    //         if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(outDtlTMsg.getReturnCode())) {
    //             setValue(aSMsg.xxGenlFldAreaTxt_A1, RTRN_MSG_UPDATE_FAILED);
    //             EZDConnectionMgr.getInstance().rollback();
    //             return false;
    //         }
    //         // insert DS_CONTR_TRK
    //         if (!insertDsContrTrkForDtl(cMsg, aSMsg, outDtlStsVTMsg.dsContrDtlPk.getValue())) {
    //             return false;
    //         }
    //     }
    //     return true;
    // }

    /**
     * @param cMsg
     * @param aSMsg
     * @param outDtlStsVTMsgArray
     */
    private static boolean updateDsContrDtl(NSAL0700CMsg cMsg, NSAL0700_ASMsg aSMsg, List<BigDecimal> dsContrDtlPkList, String bllgHldFlg) {
        for (int i = 0; i < dsContrDtlPkList.size(); i++) {
            BigDecimal dsContrDtlPk = dsContrDtlPkList.get(i);

            DS_CONTR_DTLTMsg inDtlTMsg = new DS_CONTR_DTLTMsg();
            setValue(inDtlTMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
            setValue(inDtlTMsg.dsContrDtlPk, dsContrDtlPk);
            DS_CONTR_DTLTMsg outDtlTMsg = (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inDtlTMsg);
            if (outDtlTMsg == null) {
                setValue(aSMsg.xxGenlFldAreaTxt_A1, RTRN_MSG_UPDATE_FAILED);
                EZDConnectionMgr.getInstance().rollback();
                return false;
            }
            setValue(outDtlTMsg.bllgHldFlg, bllgHldFlg);
            S21FastTBLAccessor.update(outDtlTMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(outDtlTMsg.getReturnCode())) {
                setValue(aSMsg.xxGenlFldAreaTxt_A1, RTRN_MSG_UPDATE_FAILED);
                EZDConnectionMgr.getInstance().rollback();
                return false;
            }
            // insert DS_CONTR_TRK
            if (!insertDsContrTrkForDtl(cMsg, aSMsg, dsContrDtlPk)) {
                return false;
            }
        }
        return true;
    }

    /**
     * insert SVC_MEMO
     * @param cMsg
     * @param aSMsg
     */
    private static boolean insertSvcMemo(NSAL0700CMsg cMsg, NSAL0700_ASMsg aSMsg) {
        SVC_MEMOTMsg tmsg = setParamForSvcMemo(cMsg, aSMsg);
        S21FastTBLAccessor.insert(tmsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
            EZDConnectionMgr.getInstance().rollback();
            setValue(aSMsg.xxGenlFldAreaTxt_A1, RTRN_MSG_UPDATE_FAILED);
            return false;
        }
        return true;
    }

// START 2016/12/13 [QC#16285, ADD]
    private static boolean insertSvcMemoHdr(NSAL0700CMsg cMsg, NSAL0700_ASMsg aSMsg) {
        SVC_MEMOTMsg tmsg = setParamForSvcMemoHdr(cMsg, aSMsg);
        S21FastTBLAccessor.insert(tmsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
            EZDConnectionMgr.getInstance().rollback();
            setValue(aSMsg.xxGenlFldAreaTxt_A1, RTRN_MSG_UPDATE_FAILED);
            return false;
        }
        return true;
    }

    private static boolean insertSvcMemoDtl(NSAL0700CMsg cMsg, NSAL0700_ASMsg aSMsg) {
        SVC_MEMOTMsg tmsg = setParamForSvcMemoDtl(cMsg, aSMsg);
        S21FastTBLAccessor.insert(tmsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
            EZDConnectionMgr.getInstance().rollback();
            setValue(aSMsg.xxGenlFldAreaTxt_A1, RTRN_MSG_UPDATE_FAILED);
            return false;
        }
        return true;
    }

    private static boolean insertSvcMemoBllgMtr(NSAL0700CMsg cMsg, NSAL0700_ASMsg aSMsg) {
        SVC_MEMOTMsg tmsg = setParamForSvcMemoBllgMtr(cMsg, aSMsg);
        S21FastTBLAccessor.insert(tmsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
            EZDConnectionMgr.getInstance().rollback();
            setValue(aSMsg.xxGenlFldAreaTxt_A1, RTRN_MSG_UPDATE_FAILED);
            return false;
        }
        return true;
    }
// END   2016/12/13 [QC#16285, MOD]

    /**
     * insert DS_CONTR_TRK(call NSZC0770)
     * @param cMsg
     * @param aSMsg
     */
    private static boolean insertDsContrTrkForHdr(NSAL0700CMsg cMsg, NSAL0700_ASMsg aSMsg) {
        NSZC077001PMsg pMsg = new NSZC077001PMsg();
        setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(pMsg.xxModeCd, ContrTrkProcMode.USER_OPERATION.code);
        setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.CONTRACT_HEADER);
        setValue(pMsg.dsContrPk, aSMsg.dsContrPk_A1);
        // START 2015/11/27 T.Tsuchida [QC#1210,ADD]
        setValue(pMsg.stsMemoUpdPsnCd, cMsg.getUserID());
        setValue(pMsg.stsMemoRsnCd, cMsg.svcMemoRsnCd_H3);
        setValue(pMsg.stsMemoTxt, cMsg.svcCmntTxt_H1);
        // END 2015/11/27 T.Tsuchida [QC#1210,ADD]

        return callApi(aSMsg, pMsg);
    }

    /**
     * insert DS_CONTR_TRK(call NSZC0770)
     * @param cMsg
     * @param aSMsg
     */
    private static boolean insertDsContrTrkForDtl(NSAL0700CMsg cMsg, NSAL0700_ASMsg aSMsg, BigDecimal dsContrDtlPk) {
        NSZC077001PMsg pMsg = new NSZC077001PMsg();
        setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(pMsg.xxModeCd, ContrTrkProcMode.USER_OPERATION.code);
        setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.BASE_CHARGE);
        setValue(pMsg.dsContrPk, aSMsg.dsContrPk_A1);
        setValue(pMsg.dsContrDtlPk, dsContrDtlPk);
        // START 2015/11/27 T.Tsuchida [QC#1210,ADD]
        setValue(pMsg.stsMemoUpdPsnCd, cMsg.getUserID());
        setValue(pMsg.stsMemoRsnCd, cMsg.svcMemoRsnCd_H3);
        setValue(pMsg.stsMemoTxt, cMsg.svcCmntTxt_H1);
        // END 2015/11/27 T.Tsuchida [QC#1210,ADD]

        return callApi(aSMsg, pMsg);
    }

    /**
     * insert DS_CONTR_TRK(call NSZC0770)
     * @param cMsg
     * @param aSMsg
     */
    private static boolean insertDsContrTrkBllgMtr(NSAL0700CMsg cMsg, NSAL0700_ASMsg aSMsg, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk) {
        NSZC077001PMsg pMsg = new NSZC077001PMsg();
        setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(pMsg.xxModeCd, ContrTrkProcMode.USER_OPERATION.code);
        setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.USAGE_CHARGE);
        setValue(pMsg.dsContrPk, aSMsg.dsContrPk_A1);
        setValue(pMsg.dsContrDtlPk, dsContrDtlPk);
        setValue(pMsg.dsContrBllgMtrPk, dsContrBllgMtrPk);
        // START 2015/11/27 T.Tsuchida [QC#1210,ADD]
        setValue(pMsg.stsMemoUpdPsnCd, cMsg.getUserID());
        setValue(pMsg.stsMemoRsnCd, cMsg.svcMemoRsnCd_H3);
        setValue(pMsg.stsMemoTxt, cMsg.svcCmntTxt_H1);
        // END 2015/11/27 T.Tsuchida [QC#1210,ADD]

        return callApi(aSMsg, pMsg);
    }

    /**
     * insert DS_CONTR_TRK(call NSZC0770)
     * @param cMsg
     * @param aSMsg
     */
    private static boolean insertDsContrTrkPrcEff(NSAL0700CMsg cMsg, NSAL0700_ASMsg aSMsg, DS_CONTR_PRC_EFFTMsg outPrcEffTMsg) {
        NSZC077001PMsg pMsg = new NSZC077001PMsg();
        setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(pMsg.xxModeCd, ContrTrkProcMode.USER_OPERATION.code);
        setValue(pMsg.dsContrPk, aSMsg.dsContrPk_A1);
        setValue(pMsg.dsContrDtlPk, outPrcEffTMsg.dsContrDtlPk);
        setValue(pMsg.dsContrBllgMtrPk, outPrcEffTMsg.dsContrBllgMtrPk);
        if (FLG_ON_Y.equals(outPrcEffTMsg.baseChrgFlg.getValue())) {
            setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.BASE_CHARGE_PE);
        } else {
            setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.USAGE_CHARGE_PE);
        }
        setValue(pMsg.dsContrPrcEffPk, outPrcEffTMsg.dsContrPrcEffPk);
        setValue(pMsg.contrPrcEffFromDt, outPrcEffTMsg.contrPrcEffFromDt);
        setValue(pMsg.contrPrcEffThruDt, outPrcEffTMsg.contrPrcEffThruDt);
        // START 2015/11/27 T.Tsuchida [QC#1210,ADD]
        setValue(pMsg.stsMemoUpdPsnCd, cMsg.getUserID());
        setValue(pMsg.stsMemoRsnCd, cMsg.svcMemoRsnCd_H3);
        setValue(pMsg.stsMemoTxt, cMsg.svcCmntTxt_H1);
        // END 2015/11/27 T.Tsuchida [QC#1210,ADD]

        return callApi(aSMsg, pMsg);
    }

    /**
     * @param aSMsg
     * @param pMsg
     * @return
     */
    private static boolean callApi(NSAL0700_ASMsg aSMsg, NSZC077001PMsg pMsg) {
        NSZC077001 api = new NSZC077001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
            String msgId = msg.getXxMsgid();
            String[] msgPrms = msg.getXxMsgPrmArray();
            setValue(aSMsg.xxGenlFldAreaTxt_A1, S21MessageFunc.clspGetMessage(msgId, msgPrms));
            EZDConnectionMgr.getInstance().rollback();
            return false;
        }
        return true;
    }

    // START 2015/12/11 J.Kim [QC#1659,ADD]
    /**
     * Check TimeStamp
     * @param currUpTime
     * @param currTimeZone
     * @param preUpTime
     * @param preTimeZone
     * @param cMsg NSAL0700CMsg
     * @return true/false
     */
    private static boolean isSameTimeStamp(String currUpTime, String currTimeZone, String preUpTime, String preTimeZone, NSAL0700CMsg cMsg) {

        if (!ZYPDateUtil.isSameTimeStamp(preUpTime, preTimeZone, currUpTime, currTimeZone)) {
            cMsg.setMessageInfo(NSAM0394W);
            return false;
        }
        return true;
    }

    private static boolean isSameTimeDsContr(DS_CONTRTMsg tMsg, NSAL0700_ASMsg aSMsg, NSAL0700CMsg cMsg) {

        String currUpTime = tMsg.ezUpTime.getValue();
        String currTimeZone = tMsg.ezUpTimeZone.getValue();
        String preUpTime = aSMsg.ezUpTime_D1.getValue();
        String preTimeZone = aSMsg.ezUpTimeZone_D1.getValue();

        return isSameTimeStamp(currUpTime, currTimeZone, preUpTime, preTimeZone, cMsg);
    }

    private static boolean isSameTimeDsContrDtl(DS_CONTR_DTLTMsg tMsg, NSAL0700_ASMsg aSMsg, NSAL0700CMsg cMsg) {

        String currUpTime = tMsg.ezUpTime.getValue();
        String currTimeZone = tMsg.ezUpTimeZone.getValue();
        String preUpTime = aSMsg.ezUpTime_D2.getValue();
        String preTimeZone = aSMsg.ezUpTimeZone_D2.getValue();

        return isSameTimeStamp(currUpTime, currTimeZone, preUpTime, preTimeZone, cMsg);
    }

    private static boolean isSameTimeDsContrBllgmtr(DS_CONTR_BLLG_MTRTMsg tMsg, NSAL0700_ASMsg aSMsg, NSAL0700CMsg cMsg) {

        String currUpTime = tMsg.ezUpTime.getValue();
        String currTimeZone = tMsg.ezUpTimeZone.getValue();
        String preUpTime = aSMsg.ezUpTime_D3.getValue();
        String preTimeZone = aSMsg.ezUpTimeZone_D3.getValue();
        return isSameTimeStamp(currUpTime, currTimeZone, preUpTime, preTimeZone, cMsg);
    }
    // END   2015/12/11 J.Kim [QC#1659,ADD]
    // mod end 2016/11/28 CSA QC#4210
    // add start 2016/11/28 CSA QC#4210
    /**
     * copy To BSMsg for Current Page Info
     * @param cMsg NSAL0700CMsg
     * @param sMsg NSAL0700SMsg
     */
    public static void copyCurrentPageToSMsg(NSAL0700CMsg cMsg, NSAL0700SMsg sMsg) {

        // NSAL0700_ACMsg -> NSAL0700_BSMsg
        NSAL0700_ACMsgArray acMsgArray = cMsg.A;
        for (int i = 0; i < acMsgArray.getValidCount(); i++) {
            NSAL0700_ACMsg acMsg = acMsgArray.no(i);
            int targetIdxNum = acMsg.xxRowNum_A1.getValueInt();

            NSAL0700_ASMsg asMsg = sMsg.A.no(targetIdxNum);
            setValue(asMsg.dsContrPk_A1, acMsg.dsContrPk_A1);
            setValue(asMsg.dsContrDtlPk_A1, acMsg.dsContrDtlPk_A1);
            setValue(asMsg.dsContrBllgMtrPk_A1, acMsg.dsContrBllgMtrPk_A1);
            setValue(asMsg.dsContrDtlTpCd_A1, acMsg.dsContrDtlTpCd_A1);
            setValue(asMsg.xxChkBox_A1, acMsg.xxChkBox_A1);
            setValue(asMsg.dsContrCatgCd_A1, acMsg.dsContrCatgCd_A1);
            setValue(asMsg.xxScrItem34Txt_A1, acMsg.xxScrItem34Txt_A1);
            setValue(asMsg.xxChkBox_A2, acMsg.xxChkBox_A2);
            setValue(asMsg.serNum_A1, acMsg.serNum_A1);
            setValue(asMsg.xxChkBox_A3, acMsg.xxChkBox_A3);
            setValue(asMsg.mtrLbDescTxt_A1, acMsg.mtrLbDescTxt_A1);
            setValue(asMsg.dsContrCtrlStsCd_A1, acMsg.dsContrCtrlStsCd_A1);
            setValue(asMsg.dsContrCtrlStsNm_A1, acMsg.dsContrCtrlStsNm_A1);
            setValue(asMsg.contrVrsnEffFromDt_A1, acMsg.contrVrsnEffFromDt_A1);
            setValue(asMsg.contrVrsnEffThruDt_A1, acMsg.contrVrsnEffThruDt_A1);
            setValue(asMsg.basePrcDealAmt_A1, acMsg.basePrcDealAmt_A1);
            setValue(asMsg.bllgCycleUomNm_A1, acMsg.bllgCycleUomNm_A1);
            setValue(asMsg.nextBllgDt_A1, acMsg.nextBllgDt_A1);
            setValue(asMsg.xxGenlFldAreaTxt_A1, acMsg.xxGenlFldAreaTxt_A1);
            setValue(asMsg.bllgHldFlg_A1, acMsg.bllgHldFlg_A1);
            setValue(asMsg.bllgHldFlg_A2, acMsg.bllgHldFlg_A2);
            setValue(asMsg.bllgHldFlg_A3, acMsg.bllgHldFlg_A3);
            setValue(asMsg.xxRefCseNum_A1, acMsg.xxRefCseNum_A1);
            setValue(asMsg.xxRowNum_A1, acMsg.xxRowNum_A1);
            setValue(asMsg.ezUpTime_D1, acMsg.ezUpTime_D1);
            setValue(asMsg.ezUpTimeZone_D1, acMsg.ezUpTimeZone_D1);
            setValue(asMsg.ezUpTime_D2, acMsg.ezUpTime_D2);
            setValue(asMsg.ezUpTimeZone_D2, acMsg.ezUpTimeZone_D2);
            setValue(asMsg.ezUpTime_D3, acMsg.ezUpTime_D3);
            setValue(asMsg.ezUpTimeZone_D3, acMsg.ezUpTimeZone_D3);
        }
    }

    /**
     * @param cMsg NSAL0700CMsg
     * @param sMsg NSAL0700SMsg
     */
    public static void copyFromSMsgOntoCmsg(NSAL0700CMsg cMsg, NSAL0700SMsg sMsg) {

        int noCmsg = cMsg.A.no(0).xxRowNum_A1.getValueInt();
        cMsg.A.clear();
        int count = 0;
        for (int i = noCmsg; i < sMsg.A.getValidCount(); i++) {
            if (count < cMsg.A.length()) {
                EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(count), null);
                count++;
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(count);
    }
    // add end 2016/11/28 CSA QC#4210

    // START 2017/09/04 K.Kojima [QC#20816,ADD]
    public static String changeBllgHldFLg(String bllgHldFlgBefore) {
        if (FLG_ON_Y.equals(bllgHldFlgBefore)) {
            return FLG_OFF_N;
        } else {
            return FLG_ON_Y;
        }
    }
    // END 2017/09/04 K.Kojima [QC#20816,ADD]
    // START 2018/08/26 [QC#22987, ADD]
    public static boolean existUnapprovedBllgHld(NSAL0700CMsg cMsg, NSAL0700_ASMsg aSMsg) {

        if (existUnapprovedUsgChrg(cMsg.glblCmpyCd.getValue(), aSMsg.dsContrPk_A1.getValue())) {
            return true;
        }

        if (existUnapprovedCrRebil(cMsg.glblCmpyCd.getValue(), aSMsg.dsContrPk_A1.getValue())) {
            return true;
        }
        return false;
    }

    public static boolean existUnapprovedUsgChrg(String glblCmpyCd, BigDecimal dsContrPk) {

        List<BigDecimal> svcContrBllgPkList = NSAL0700Query.getInstance().getUnapprovedUsgChrg(glblCmpyCd, dsContrPk);
        if (svcContrBllgPkList.isEmpty()) {
            return false;
        }
        return true;
    }

    private static boolean existUnapprovedCrRebil(String glblCmpyCd, BigDecimal dsContrPk) {

        List<BigDecimal> svcCrRebilPkList = NSAL0700Query.getInstance().getUnapprovedCrRebil(glblCmpyCd, dsContrPk);
        if (svcCrRebilPkList.isEmpty()) {
            return false;
        }
        return true;
    }
    // END 2018/08/26 [QC#22987, ADD]

    // add start 2019/07/18 QC#51706
    private static void updateSvcMemoForManBllgHld(NSAL0700CMsg cMsg, String bllgHldFlg, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk) {
        if (ZYPConstant.FLG_ON_Y.equals(bllgHldFlg)) {
            deleteSvcMemoForManBllgHld(cMsg, dsContrPk, dsContrDtlPk, dsContrBllgMtrPk);
            insertSvcMemoForManBllgHld(cMsg, dsContrPk, dsContrDtlPk, dsContrBllgMtrPk);
        } else if (ZYPConstant.FLG_OFF_N.equals(bllgHldFlg)) {
            deleteSvcMemoForManBllgHld(cMsg, dsContrPk, dsContrDtlPk, dsContrBllgMtrPk);
        }
    }

    private static void insertSvcMemoForManBllgHld(NSAL0700CMsg cMsg, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk) {
        SVC_MEMOTMsg insertTMsg = new SVC_MEMOTMsg();
        ZYPEZDItemValueSetter.setValue(insertTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insertTMsg.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MEMO_SQ));
        ZYPEZDItemValueSetter.setValue(insertTMsg.svcMemoCatgCd, SVC_MEMO_CATG_FOR_MAN_BLLG_HLD);
        ZYPEZDItemValueSetter.setValue(insertTMsg.svcMemoTpCd, SVC_MEMO_TP_FOR_MAN_BLLG_HLD);
        ZYPEZDItemValueSetter.setValue(insertTMsg.svcCmntTxt, SVC_MEMO_CMNT_FOR_MAN_BLLG_HLD);
        ZYPEZDItemValueSetter.setValue(insertTMsg.dsContrPk, dsContrPk);
        ZYPEZDItemValueSetter.setValue(insertTMsg.dsContrDtlPk, dsContrDtlPk);
        ZYPEZDItemValueSetter.setValue(insertTMsg.lastUpdUsrId, cMsg.getUserID());
        ZYPEZDItemValueSetter.setValue(insertTMsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime(DATE_FORMAT));
        ZYPEZDItemValueSetter.setValue(insertTMsg.svcMemoRsnCd, SVC_MEMO_RSN_FOR_MAN_BLLG_HLD);
        if (hasValue(dsContrBllgMtrPk)) {
            ZYPEZDItemValueSetter.setValue(insertTMsg.svcMemoTrxNum, dsContrBllgMtrPk.toString());
            ZYPEZDItemValueSetter.setValue(insertTMsg.svcMemoTrxNm, "DS_CONTR_BLLG_MTR_PK");
        }
        S21FastTBLAccessor.insert(insertTMsg);
    }

    private static void deleteSvcMemoForManBllgHld(NSAL0700CMsg cMsg, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk) {
        List<BigDecimal> svcMemoPkList = NSAL0700Query.getInstance().getSvcMemoForManBllgHld(cMsg.glblCmpyCd.getValue(), dsContrPk, dsContrDtlPk, dsContrBllgMtrPk);
        for (int i = 0; i < svcMemoPkList.size(); i++) {
            SVC_MEMOTMsg inTMsg = new SVC_MEMOTMsg();
            ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, cMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inTMsg.svcMemoPk, svcMemoPkList.get(i));
            EZDTBLAccessor.logicalRemove(inTMsg);
        }
    }
    // add end 2019/07/18 QC#51706
}
