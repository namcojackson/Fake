/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0650.common;

import static business.blap.NSAL0650.constant.NSAL0650Constant.DATE_FORMAT;
import static business.blap.NSAL0650.constant.NSAL0650Constant.DS_CONTR;
import static business.blap.NSAL0650.constant.NSAL0650Constant.MTR_EST_TP_TBL;
import static business.blap.NSAL0650.constant.NSAL0650Constant.NSAM0015E;
import static business.blap.NSAL0650.constant.NSAL0650Constant.NSAM0031E;
import static business.blap.NSAL0650.constant.NSAL0650Constant.NSAM0032E;
import static business.blap.NSAL0650.constant.NSAL0650Constant.NSAM0045E;
import static business.blap.NSAL0650.constant.NSAL0650Constant.NSAM0065E;
import static business.blap.NSAL0650.constant.NSAL0650Constant.NSAM0200I;
import static business.blap.NSAL0650.constant.NSAL0650Constant.NSAM0394W;
import static business.blap.NSAL0650.constant.NSAL0650Constant.SVC_MEMO;
import static business.blap.NSAL0650.constant.NSAL0650Constant.XX_CHK_BOX_A1;
import static business.blap.NSAL0650.constant.NSAL0650Constant.ZZZM9004E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0640.NSAL0640CMsg;
import business.blap.NSAL0640.NSAL0640SMsg;
import business.blap.NSAL0640.NSAL0640_ACMsgArray;
import business.blap.NSAL0650.NSAL0650CMsg;
import business.blap.NSAL0650.NSAL0650Query;
import business.blap.NSAL0650.NSAL0650SMsg;
import business.blap.NSAL0650.NSAL0650_ACMsg;
import business.blap.NSAL0650.NSAL0650_ACMsgArray;
import business.blap.NSAL0650.NSAL0650_ASMsg;
import business.db.DS_CONTRTMsg;
import business.db.SVC_MEMOTMsg;
import business.db.SVC_MEMO_RSNTMsg;
import business.db.SVC_MEMO_RSNTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/** 
 * Update Auto Estimation Round
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/11   Hitachi         T.Tsuchida      Create          N/A
 * 2016/12/01   Hitachi         T.Mizuki        Update          QC#4210
 *</pre>
 */
public class NSAL0650CommonLogic {
    // mod start 2016/12/02 CSA QC#4210
    /**
     * Check Select
     * @param cMsg NSAL0650CMsg
     * @param sMsg NSAL0650SMsg
     * @return boolean
     */
    public static boolean checkSelect(NSAL0650CMsg cMsg, NSAL0650SMsg sMsg) {
        boolean rtnVal = true;
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.A, XX_CHK_BOX_A1, ZYPConstant.CHKBOX_ON_Y);
        // mod end 2016/12/02 CSA QC#4210
        if (selectedRows.isEmpty()) {
            cMsg.setMessageInfo(NSAM0015E);
            rtnVal = false;
        }
        return rtnVal;
    }

    /**
     * Create Pull Down
     * @param cMsg NSAL0650CMsg
     */
    public static void createPullDown(NSAL0650CMsg cMsg) {
        createMtrEstTpPullDown(cMsg);
        createSvcMemoRsnPullDown(cMsg);
    }

    // mod start 2016/12/02 CSA QC#4210
    /**
     * Update Contract
     * @param cMsg NSAL0650CMsg
     * @param sMsg NSAL0650SMsg
     */
    public static void updateContract(NSAL0650CMsg cMsg, NSAL0650SMsg sMsg) {
        boolean checkWarn = true;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A1.getValue())) {
                updateContract(cMsg, sMsg, i);
                if (hasValue(sMsg.A.no(i).dsMsgTxt_A1)) {
                    checkWarn = false;
                    EZDConnectionMgr.getInstance().rollback();
                    continue;
                }

                insertSvcMemo(cMsg, sMsg, i);
                if (hasValue(sMsg.A.no(i).dsMsgTxt_A1)) {
                    checkWarn = false;
                    EZDConnectionMgr.getInstance().rollback();
                    continue;
                }

                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).dsMsgTxt_A1, getRtnMsg(NSAM0200I));
                EZDConnectionMgr.getInstance().commit();
            }
        }
        if (!checkWarn) {
            cMsg.setMessageInfo(NSAM0394W);
        }
        NSAL0650CommonLogic.copyThisPageToCMsg(cMsg, sMsg);
        // mod end 2016/12/02 CSA QC#4210
        cMsg.setMessageInfo(NSAM0200I);
    }

    /**
     * Get Return Message
     * @param msgId String
     * @return String
     */
    public static String getRtnMsg(String msgId) {
        String rtnVal = "";
        if (hasValue(msgId)) {
            rtnVal = S21MessageFunc.clspGetMessage(msgId);
            rtnVal = rtnVal.substring(msgId.length()).trim();
        }
        return rtnVal;
    }

    /**
     * Get Return Message
     * @param msgId String
     * @param prm String[]
     * @return String
     */
    public static String getRtnMsg(String msgId, String[] prm) {
        String rtnVal = "";
        if (hasValue(msgId)) {
            rtnVal = S21MessageFunc.clspGetMessage(msgId, prm);
            rtnVal = rtnVal.substring(msgId.length()).trim();
        }
        return rtnVal;
    }

    private static void createSvcMemoRsnPullDown(NSAL0650CMsg cMsg) {
        SVC_MEMO_RSNTMsgArray tMsgAry = getSvcMemoRsnPulldownList(cMsg.glblCmpyCd.getValue(), SVC_MEMO_TP.SERVICE);
        Map<String, String> tMsgKeys = new HashMap<String, String>();
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "svcMemoRsnCd");
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "svcMemoRsnNm");
        ZYPPulldownValueSetter.set(tMsgAry, tMsgKeys, cMsg.svcMemoRsnCd_L, cMsg.svcMemoRsnNm_L);
    }

    private static void createMtrEstTpPullDown(NSAL0650CMsg cMsg) {
        ZYPCodeDataUtil.createPulldownList(MTR_EST_TP_TBL, cMsg.mtrEstTpCd_L, cMsg.mtrEstTpNm_L);
    }

    private static SVC_MEMO_RSNTMsgArray getSvcMemoRsnPulldownList(String glblCmpyCd, String svcMemoTpCd) {
        SVC_MEMO_RSNTMsg inMsg = new SVC_MEMO_RSNTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcMemoTpCd01", svcMemoTpCd);
        return (SVC_MEMO_RSNTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    // mod start 2016/12/02 CSA QC#4210
    private static void insertSvcMemo(NSAL0650CMsg cMsg, NSAL0650SMsg sMsg, int index) {
        SVC_MEMOTMsg tmsg = new SVC_MEMOTMsg();
        setValue(tmsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(tmsg.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MEMO_SQ));
        setValue(tmsg.svcMemoCatgCd, SVC_MEMO_CATG.CONTRACT_MEMO);
        setValue(tmsg.svcMemoTpCd, SVC_MEMO_TP.SERVICE);
        setValue(tmsg.svcCmntTxt, cMsg.svcCmntTxt_H);
        setValue(tmsg.dsContrPk, sMsg.A.no(index).dsContrPk_A1);
        setValue(tmsg.lastUpdUsrId, cMsg.getUserID());
        setValue(tmsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime(DATE_FORMAT));
        setValue(tmsg.svcMemoRsnCd, cMsg.svcMemoRsnCd_H);
        S21FastTBLAccessor.insert(tmsg);

        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).dsMsgTxt_A1, S21MessageFunc.clspGetMessage(NSAM0032E, new String[] {SVC_MEMO }));
        }
    }

    private static void updateContract(NSAL0650CMsg cMsg, NSAL0650SMsg sMsg, int index) {
        NSAL0650_ASMsg asMsg = (NSAL0650_ASMsg) sMsg.A.no(index);
        if (hasValue(asMsg.dsContrPk_A1)) {
            NSAL0650Query query = NSAL0650Query.getInstance();
            DS_CONTRTMsg tMsg = query.getDsContr(cMsg.glblCmpyCd.getValue(), asMsg.dsContrPk_A1.getValue());
            if (tMsg == null) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).dsMsgTxt_A1, S21MessageFunc.clspGetMessage(NSAM0045E, new String[] {DS_CONTR }));
                return;
            } else {
                if (!ZYPDateUtil.isSameTimeStamp(asMsg.ezUpTime_A1.getValue(), asMsg.ezUpTimeZone_A1.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).dsMsgTxt_A1, S21MessageFunc.clspGetMessage(ZZZM9004E));
                    return;
                }

                setValue(tMsg.mtrEstTpCd, asMsg.mtrEstTpCd_AH);
                setValue(tMsg.dsContrLastUpdDt, ZYPDateUtil.getSalesDate());
                setValue(tMsg.dsContrLastUpdPsnCd, cMsg.getUserID());
                S21FastTBLAccessor.update(tMsg);

                String rtnCd = tMsg.getReturnCode();
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).dsMsgTxt_A1, S21MessageFunc.clspGetMessage(NSAM0031E, new String[] {DS_CONTR }));
                    return;
                }
            }
        } else {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).dsMsgTxt_A1, S21MessageFunc.clspGetMessage(NSAM0045E, new String[] {DS_CONTR }));
            return;
        }
    }
    // mod end 2016/12/02 CSA QC#4210
    // add start 2016/12/01 CSA QC#4210
    /**
     * copy To BSMsg for Current Page Info
     * @param cMsg NSAL0650CMsg
     * @param sMsg NSAL0650SMsg
     */
    public static void copyCurrentPageToSMsg(NSAL0650CMsg cMsg, NSAL0650SMsg sMsg) {

        // NSAL0650_ACMsg -> NSAL0650_BSMsg
        NSAL0650_ACMsgArray acMsgArray = cMsg.A;
        for (int i = 0; i < acMsgArray.getValidCount(); i++) {
            NSAL0650_ACMsg acMsg = acMsgArray.no(i);
            int targetIdxNum = acMsg.xxRowNum_A1.getValueInt();

            NSAL0650_ASMsg asMsg = sMsg.A.no(targetIdxNum);
            setValue(asMsg.xxChkBox_A1, acMsg.xxChkBox_A1);
            setValue(asMsg.mtrEstTpCd_AH, acMsg.mtrEstTpCd_AH);
        }
    }
    /**
     * copy To CMsg for This Page Info
     * @param cMsg NSAL0650CMsg
     * @param sMsg NSAL0650SMsg
     */
    public static void copyThisPageToCMsg(NSAL0650CMsg cMsg, NSAL0650SMsg sMsg) {

        // NSAL0650_BSMsg -> NSAL0650_ACMsg
        int from = cMsg.xxPageShowFromNum.getValueInt();
        int to = cMsg.xxPageShowToNum.getValueInt();
        NSAL0650_ACMsgArray acMsgArray = cMsg.A;
        for (int i = 0 ; from <= to ; i++) {
            EZDMsg.copy(sMsg.A.no(from - 1), null, acMsgArray.no(i), null);
            if (ZYPConstant.FLG_ON_Y.equals(acMsgArray.no(i).xxDplyCtrlFlg_A1.getValue())) {
                ZYPEZDItemValueSetter.setValue(acMsgArray.no(i).dsMsgTxt_A1, NSAL0650CommonLogic.getRtnMsg(NSAM0065E));
            }
            from++;
        }
    }
    public static void showTargetPage(NSAL0650CMsg cMsg, NSAL0650SMsg sMsg, int pageIdx) {
        NSAL0650_ACMsgArray acMsgArray = cMsg.A;
        ZYPTableUtil.clear(acMsgArray);

        int pageFrom = pageIdx / acMsgArray.length();
        pageFrom = pageFrom * acMsgArray.length();

        int i = pageFrom;
        for (; i < pageFrom + acMsgArray.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, acMsgArray.no(i - pageFrom), null);
                ZYPCodeDataUtil.createPulldownList(MTR_EST_TP_TBL, acMsgArray.no(i - pageFrom).mtrEstTpCd_AL, acMsgArray.no(i - pageFrom).mtrEstTpNm_AL);
                if (ZYPConstant.FLG_ON_Y.equals(acMsgArray.no(i - pageFrom).xxDplyCtrlFlg_A1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(acMsgArray.no(i - pageFrom).dsMsgTxt_A1, NSAL0650CommonLogic.getRtnMsg(NSAM0065E));
                }
            } else {
                break;
            }
        }
        acMsgArray.setValidCount(i - pageFrom);

        pageFrom = pageFrom + 1;
        cMsg.xxPageShowFromNum.setValue(pageFrom);
        cMsg.xxPageShowToNum.setValue(pageFrom + acMsgArray.getValidCount() - 1);
    }
    // add end 2016/12/01 CSA QC#4210
}
