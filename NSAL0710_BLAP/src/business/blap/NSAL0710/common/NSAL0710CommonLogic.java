/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0710.common;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.*;
import static com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.*;
import static business.blap.NSAL0710.constant.NSAL0710Constant.*;
import static business.blap.NSAL0710.constant.NSAL0710Constant.NSAM0200I;
import static business.blap.NSAL0710.constant.NSAL0710Constant.NSAM0394W;
import static business.blap.NSAL0710.constant.NSAL0710Constant.RTRN_MSG_FAILED;

import parts.common.EZDMsg;
import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDTBLAccessor;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_READ_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

import business.blap.NSAL0710.NSAL0710CMsg;
import business.blap.NSAL0710.NSAL0710SMsg;
import business.blap.NSAL0710.NSAL0710_ASMsg;
import business.blap.NSAL0710.NSAL0710_ACMsg;
import business.blap.NSAL0710.NSAL0710_ACMsgArray;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_DTL_STS_VTMsg;
import business.db.DS_CONTR_DTL_STS_VTMsgArray;
import business.db.SVC_MEMOTMsg;
import business.db.SVC_MEMO_RSNTMsg;
import business.db.SVC_MEMO_RSNTMsgArray;

/**
 *<pre>
 * Update Read Methods
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/18   Hitachi         K.Kasai         Create          N/A
 * 2015/12/04   Hitachi         T.Tsuchida      Update          QC#990
 * 2015/12/07   Hitachi         T.Tsuchida      Update          QC#1145
 * 2015/12/15   Hitachi         T.Tsuchida      Update          QC#1659
 * 2015/12/15   Hitachi         T.Tsuchida      Update          QC#1577
 * 2016/08/08   Hitachi         A.Kohinata      Update          QC#12001
 * 2016/11/11   Hitachi         T.Mizuki        Update          QC#4210
 * 2017/06/19   Hitachi         T.Mizuki        Update          QC#19256
 * 2017/06/20   Hitachi         N.Arai          Update          QC#18265
 */
public class NSAL0710CommonLogic {

    /**
     * Create Pull Down
     * @param cMsg NSAL0710CMsg
     */
    public static void createPullDown(NSAL0710CMsg cMsg) {
        createSvcMemoRsnPullDown(cMsg);
        createMtrReadMethPullDown(cMsg);
    }

    private static void createSvcMemoRsnPullDown(NSAL0710CMsg cMsg) {
        SVC_MEMO_RSNTMsgArray tMsgAry = getSvcMemoRsnPulldownList(cMsg.glblCmpyCd.getValue(), SVC_MEMO_TP.READ_METHOD);
        Map<String, String> tMsgKeys = new HashMap<String, String>();
        tMsgKeys.put(KEY_VALUE, "svcMemoRsnCd");
        tMsgKeys.put(KEY_DISPLAY, "svcMemoRsnNm");
        set(tMsgAry, tMsgKeys, cMsg.svcMemoRsnCd_H1, cMsg.svcMemoRsnNm_H2);
    }

    private static SVC_MEMO_RSNTMsgArray getSvcMemoRsnPulldownList(String glblCmpyCd, String svcMemoTpCd) {
        SVC_MEMO_RSNTMsg inMsg = new SVC_MEMO_RSNTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcMemoTpCd01", svcMemoTpCd);
        return (SVC_MEMO_RSNTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    private static void createMtrReadMethPullDown(NSAL0710CMsg cMsg) {
        ZYPCodeDataUtil.createPulldownList(MTR_READ_METH.class, cMsg.mtrReadMethCd_H1, cMsg.mtrReadMethNm_H2);
    }

    // mod start 2016/11/18 CSA QC#4210
    /**
     * Change Billing Hold
     * @param cMsg NSAL0710CMsg
     * @param sMsg NSAL0710SMsg
     */
    public static void updateMtrReadMeth(NSAL0710CMsg cMsg, NSAL0710SMsg sMsg) {
        String mtrReadMethNm = "";
        for (int i = 0; i < cMsg.mtrReadMethCd_H1.length(); i++) {
            if (cMsg.mtrReadMethCd_H3.getValue().equals(cMsg.mtrReadMethCd_H1.no(i).getValue())) {
                mtrReadMethNm = cMsg.mtrReadMethNm_H2.no(i).getValue();
                break;
            }
        }

        BigDecimal preDsContrPk = BigDecimal.ZERO;
        boolean errFlg = false;
        boolean procHdr = false;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (i > 0 && preDsContrPk.equals(sMsg.A.no(i).dsContrPk_A1.getValue())) {
                procHdr = false;
            }
            NSAL0710_ASMsg aSMsg = sMsg.A.no(i);
            if (CHKBOX_ON_Y.equals(aSMsg.xxChkBox_A1.getValue())) {
                updateMtrReadMethHdr(cMsg, sMsg, aSMsg);
                if (RTRN_MSG_FAILED.equals(aSMsg.xxGenlFldAreaTxt_A1.getValue())) {
                    errFlg = true;
                }
                setValue(aSMsg.mtrReadMethNm_A2, mtrReadMethNm);
                procHdr = true;
            }
            if (CHKBOX_ON_Y.equals(aSMsg.xxChkBox_A2.getValue()) && !procHdr) {
                updateMtrReadMethDtl(cMsg, aSMsg);
                if (RTRN_MSG_FAILED.equals(aSMsg.xxGenlFldAreaTxt_A1.getValue())) {
                    errFlg = true;
                }
                setValue(aSMsg.mtrReadMethNm_A2, mtrReadMethNm);
            }
            preDsContrPk = sMsg.A.no(i).dsContrPk_A1.getValue();
        }
        if (errFlg) {
            cMsg.setMessageInfo(NSAM0394W);
        } else {
            cMsg.setMessageInfo(NSAM0200I);
        }
    }

    /**
     * Update Meter Read Method Header
     * @param cMsg NSAL0710CMsg
     * @param sMsg NSAL0710SMsg
     * @param aSMsg NSAL0710_ASMsg
     */
    public static void updateMtrReadMethHdr(NSAL0710CMsg cMsg, NSAL0710SMsg sMsg, NSAL0710_ASMsg aSMsg) {
        //DS_CONTR_DTL
        DS_CONTR_DTL_STS_VTMsg inDtlStsVTMsg = setParamForDtl(cMsg, aSMsg);
        DS_CONTR_DTL_STS_VTMsgArray outDtlStsVTMsgArray = (DS_CONTR_DTL_STS_VTMsgArray) EZDTBLAccessor.findByCondition(inDtlStsVTMsg);
        if (outDtlStsVTMsgArray.length() == 0) {
            setValue(aSMsg.xxGenlFldAreaTxt_A1, RTRN_MSG_FAILED);
            EZDConnectionMgr.getInstance().rollback();
            return;
        }
        //update DS_CONTR_DTL
        // add start 2016/08/08 CSA Defect#12001
        boolean dtlChk = false;
        // add end 2016/08/08 CSA Defect#12001
        for (int i = 0; i < outDtlStsVTMsgArray.length(); i++) {
            DS_CONTR_DTL_STS_VTMsg outDtlStsVTMsg = outDtlStsVTMsgArray.no(i);
            DS_CONTR_DTLTMsg inDtlTMsg = new DS_CONTR_DTLTMsg();
            setValue(inDtlTMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
            setValue(inDtlTMsg.dsContrDtlPk, outDtlStsVTMsg.dsContrDtlPk.getValue());
            DS_CONTR_DTLTMsg outDtlTMsg = (DS_CONTR_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(inDtlTMsg);
            if (outDtlTMsg == null) {
                setValue(aSMsg.xxGenlFldAreaTxt_A1, RTRN_MSG_FAILED);
                EZDConnectionMgr.getInstance().rollback();
                return;
            // START 2017/06/19 T.Mizuki [QC#19256,ADD]
            } else if (outDtlTMsg.dsContrDtlStsCd.getValue().equals(DS_CONTR_DTL_STS.ORDER)) {
                continue;
            }
            // END 2017/06/19 T.Mizuki [QC#19256,ADD]
            for (int j = 0; j < sMsg.A.getValidCount(); j++) {
                if (ZYPCommonFunc.hasValue(sMsg.A.no(j).dsContrDtlPk_A1)
                        && outDtlTMsg.dsContrDtlPk.getValue().compareTo(sMsg.A.no(j).dsContrDtlPk_A1.getValue()) == 0) {
                    // add start 2016/08/08 CSA Defect#12001
                    if (CHKBOX_ON_Y.equals(sMsg.A.no(j).xxChkBox_A2.getValue())) {
                        dtlChk = true;
                        break;
                    }
                    // add end 2016/08/08 CSA Defect#12001
                    if (!isSameTimeDsContrDtl(outDtlTMsg, sMsg.A.no(j), cMsg)) {
                        setValue(aSMsg.xxGenlFldAreaTxt_A1, getRtnMsg(ZZZM9004E));
                        EZDConnectionMgr.getInstance().rollback();
                        return;
                    }
                    break;
                }
            }
            // add start 2016/08/08 CSA Defect#12001
            if (dtlChk) {
                dtlChk = false;
                continue;
            }
            // add end 2016/08/08 CSA Defect#12001
            if (DS_CONTR_DTL_TP.FLEET.equals(outDtlTMsg.dsContrDtlTpCd.getValue())
                    || DS_CONTR_DTL_TP.AGGREGATE.equals(outDtlTMsg.dsContrDtlTpCd.getValue())) {
                continue;
            }
            if (DS_CONTR_DTL_TP.PRICE_OF_MODEL_LEVEL.equals(outDtlTMsg.dsContrDtlTpCd.getValue())) {
                continue;
            }
            setValue(outDtlTMsg.mtrReadMethCd, cMsg.mtrReadMethCd_H3);
            S21FastTBLAccessor.update(outDtlTMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(outDtlTMsg.getReturnCode())) {
                setValue(aSMsg.xxGenlFldAreaTxt_A1, RTRN_MSG_FAILED);
                EZDConnectionMgr.getInstance().rollback();
                return;
            }
        }
        //insert Service memo
        if (!insertSvcMemo(cMsg, aSMsg)) {
            EZDConnectionMgr.getInstance().rollback();
            return;
        }
        setValue(aSMsg.mtrReadMethCd_A2, cMsg.mtrReadMethCd_H3);
        setValue(aSMsg.xxGenlFldAreaTxt_A1, RTRN_MSG_SUCCESS);
        EZDConnectionMgr.getInstance().commit();
    }

    /**
     * Update Meter Read Method Detail
     * @param cMsg NSAL0710CMsg
     * @param aSMsg NSAL0710_ASMsg
     */
    public static void updateMtrReadMethDtl(NSAL0710CMsg cMsg, NSAL0710_ASMsg aSMsg) {
        //update DS_CONTR_DTL
        DS_CONTR_DTLTMsg inDtlTMsg = new DS_CONTR_DTLTMsg();
        setValue(inDtlTMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
        setValue(inDtlTMsg.dsContrDtlPk, aSMsg.dsContrDtlPk_A1.getValue());
        DS_CONTR_DTLTMsg outDtlTMsg = (DS_CONTR_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdate(inDtlTMsg);
        if (outDtlTMsg == null) {
            setValue(aSMsg.xxGenlFldAreaTxt_A1, RTRN_MSG_FAILED);
            EZDConnectionMgr.getInstance().rollback();
            return;
        }
        if (!isSameTimeDsContrDtl(outDtlTMsg, aSMsg, cMsg)) {
            setValue(aSMsg.xxGenlFldAreaTxt_A1, getRtnMsg(ZZZM9004E));
            EZDConnectionMgr.getInstance().rollback();
            return;
        }
        setValue(outDtlTMsg.mtrReadMethCd, cMsg.mtrReadMethCd_H3);
        S21FastTBLAccessor.update(outDtlTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(outDtlTMsg.getReturnCode())) {
            setValue(aSMsg.xxGenlFldAreaTxt_A1, RTRN_MSG_FAILED);
            EZDConnectionMgr.getInstance().rollback();
            return;
        }
        //insert Service memo
        if (!insertSvcMemo(cMsg, aSMsg)) {
            EZDConnectionMgr.getInstance().rollback();
            return;
        }
        setValue(aSMsg.mtrReadMethCd_A2, cMsg.mtrReadMethCd_H3);
        setValue(aSMsg.xxGenlFldAreaTxt_A1, RTRN_MSG_SUCCESS);
        EZDConnectionMgr.getInstance().commit();
    }

    /**
     * Insert Service memo
     * @param cMsg NSAL0710CMsg
     * @param aSMsg NSAL0710_ASMsg
     */
    private static boolean insertSvcMemo(NSAL0710CMsg cMsg, NSAL0710_ASMsg aSMsg) {
        SVC_MEMOTMsg tmsg = new SVC_MEMOTMsg();
        setValue(tmsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(tmsg.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MEMO_SQ));
        setValue(tmsg.svcMemoCatgCd, SVC_MEMO_CATG.CONTRACT_MEMO);
        setValue(tmsg.svcMemoTpCd, SVC_MEMO_TP.READ_METHOD);
        setValue(tmsg.svcCmntTxt, cMsg.svcCmntTxt_H1);
        setValue(tmsg.dsContrPk, aSMsg.dsContrPk_A1);
        if (aSMsg.dsContrDtlPk_A1.getValue() != BigDecimal.ZERO) {
            setValue(tmsg.dsContrDtlPk, aSMsg.dsContrDtlPk_A1);
        }
        setValue(tmsg.lastUpdUsrId, cMsg.getUserID());
        setValue(tmsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime(DATE_FORMAT));
        setValue(tmsg.svcMemoRsnCd, cMsg.svcMemoRsnCd_H3);
        S21FastTBLAccessor.insert(tmsg);

        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
            setValue(aSMsg.xxGenlFldAreaTxt_A1, RTRN_MSG_FAILED);
            return false;
        }
        return true;
    }

    /**
     * @param cMsg
     * @param aSMsg
     * @return inDtlStsVTMsg
     */
    private static DS_CONTR_DTL_STS_VTMsg setParamForDtl(NSAL0710CMsg cMsg, NSAL0710_ASMsg aSMsg) {
        DS_CONTR_DTL_STS_VTMsg inDtlStsVTMsg = new DS_CONTR_DTL_STS_VTMsg();
        inDtlStsVTMsg.setSQLID("007");
        inDtlStsVTMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        inDtlStsVTMsg.setConditionValue("dsContrPk01", aSMsg.dsContrPk_A1.getValue());
        // mod end 2016/11/18 CSA QC#4210
        inDtlStsVTMsg.setConditionValue("dsContrCtrlStsCd01", DS_CONTR_CTRL_STS.EXPIRED);
        inDtlStsVTMsg.setConditionValue("dsContrCtrlStsCd02", DS_CONTR_CTRL_STS.CANCELLED);
        inDtlStsVTMsg.setConditionValue("dsContrCtrlStsCd03", DS_CONTR_CTRL_STS.TERMINATED);
        inDtlStsVTMsg.setConditionValue("dsContrCtrlStsCd04", DS_CONTR_CTRL_STS.TERMINATED_HOLD);
        inDtlStsVTMsg.setConditionValue("dsContrCtrlStsCd05", DS_CONTR_CTRL_STS.EXPIRED_HOLD);
        return inDtlStsVTMsg;
    }

    /**
     * Check TimeStamp
     * @param currUpTime
     * @param currTimeZone
     * @param preUpTime
     * @param preTimeZone
     * @param cMsg NSAL0710CMsg
     * @return true/false
     */
    private static boolean isSameTimeStamp(String currUpTime, String currTimeZone, String preUpTime, String preTimeZone, NSAL0710CMsg cMsg) {

        if (!ZYPDateUtil.isSameTimeStamp(preUpTime, preTimeZone, currUpTime, currTimeZone)) {
            cMsg.setMessageInfo(NSAM0394W);
            return false;
        }
        return true;
    }

    // mod start 2016/11/18 CSA QC#4210
    private static boolean isSameTimeDsContrDtl(DS_CONTR_DTLTMsg tMsg, NSAL0710_ASMsg aSMsg, NSAL0710CMsg cMsg) {

        String currUpTime = tMsg.ezUpTime.getValue();
        String currTimeZone = tMsg.ezUpTimeZone.getValue();
        String preUpTime = aSMsg.ezUpTime_D1.getValue();
        String preTimeZone = aSMsg.ezUpTimeZone_D1.getValue();
        // mod end 2016/11/18 CSA QC#4210
        return isSameTimeStamp(currUpTime, currTimeZone, preUpTime, preTimeZone, cMsg);
    }

    /**
     * Get Return Message
     * @param msgId String
     * @return String
     */
    private static String getRtnMsg(String msgId) {
        String rtnVal = "";
        if (hasValue(msgId)) {
            rtnVal = S21MessageFunc.clspGetMessage(msgId);
            rtnVal = rtnVal.substring(msgId.length()).trim();
        }
        return rtnVal;
    }

    // mod start 2016/11/18 CSA QC#4210
    /**
     * copy To BSMsg for Current Page Info
     * @param cMsg NSAL0710CMsg
     * @param sMsg NSAL0710SMsg
     */
    public static void copyCurrentPageToSMsg(NSAL0710CMsg cMsg, NSAL0710SMsg sMsg) {

        // NSAL0710_ACMsg -> NSAL0710_BSMsg
        NSAL0710_ACMsgArray acMsgArray = cMsg.A;
        // START 2017/06/20 N.Arai [QC#18265, MOD]
        BigDecimal preDsContrPk = BigDecimal.ZERO;
        for (int i = 0; i < acMsgArray.getValidCount(); i++) {
            NSAL0710_ACMsg acMsg = acMsgArray.no(i);
            int targetIdxNum = acMsg.xxRowNum_A.getValueInt();
            NSAL0710_ASMsg asMsg = sMsg.A.no(targetIdxNum);
            if (preDsContrPk.compareTo(acMsg.dsContrPk_A1.getValue()) != 0){
                setValue(asMsg.xxChkBox_A1, acMsg.xxChkBox_A1);
            } else {
                if (ZYPCommonFunc.hasValue(asMsg.xxChkBox_A1)) {
                    asMsg.xxChkBox_A1.clear();
                }
            }
            setValue(asMsg.xxScrItem34Txt_A1, acMsg.xxScrItem34Txt_A1);
            setValue(asMsg.dsAcctNm_A1, acMsg.dsAcctNm_A1);
            setValue(asMsg.contrVrsnEffFromDt_A1, acMsg.contrVrsnEffFromDt_A1);
            setValue(asMsg.contrVrsnEffThruDt_A1, acMsg.contrVrsnEffThruDt_A1);
            setValue(asMsg.dsContrCtrlStsNm_A1, acMsg.dsContrCtrlStsNm_A1);
            if (preDsContrPk.compareTo(acMsg.dsContrPk_A1.getValue()) == 0){
                setValue(asMsg.xxChkBox_A2, acMsg.xxChkBox_A2);
            } else {
                if (ZYPCommonFunc.hasValue(asMsg.xxChkBox_A2)) {
                    asMsg.xxChkBox_A2.clear();
                }
            }
            setValue(asMsg.serNum_A1, acMsg.serNum_A1);
            setValue(asMsg.t_MdlNm_A1, acMsg.t_MdlNm_A1);
            setValue(asMsg.xxDplyCtrlFlg_A1, acMsg.xxDplyCtrlFlg_A1);
            setValue(asMsg.xxDplyCtrlFlg_A2, acMsg.xxDplyCtrlFlg_A2);
            setValue(asMsg.xxDplyCtrlFlg_A3, acMsg.xxDplyCtrlFlg_A3);
            setValue(asMsg.xxDplyCtrlFlg_A4, acMsg.xxDplyCtrlFlg_A4);
            setValue(asMsg.xxDplyCtrlFlg_A5, acMsg.xxDplyCtrlFlg_A5);
            setValue(asMsg.mtrReadMethNm_A1, acMsg.mtrReadMethNm_A1);
            setValue(asMsg.mtrReadMethNm_A2, acMsg.mtrReadMethNm_A2);
            setValue(asMsg.xxGenlFldAreaTxt_A1, acMsg.xxGenlFldAreaTxt_A1);
            preDsContrPk = acMsg.dsContrPk_A1.getValue();
        }
        // END 2017/06/20 N.Arai [QC#18265, MOD]
    }

    /**
     * @param cMsg NSAL0710CMsg
     * @param sMsg NSAL0710SMsg
     */
    public static void copyFromSMsgOntoCmsg(NSAL0710CMsg cMsg, NSAL0710SMsg sMsg) {

        int noCmsg = cMsg.A.no(0).xxRowNum_A.getValueInt();
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
    // mod end 2016/11/17 CSA QC#4210

}
