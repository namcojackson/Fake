/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0640.common;

import static business.blap.NSAL0640.constant.NSAL0640Constant.DS_CONTR;
import static business.blap.NSAL0640.constant.NSAL0640Constant.NSAM0015E;
import static business.blap.NSAL0640.constant.NSAL0640Constant.NSAM0031E;
import static business.blap.NSAL0640.constant.NSAL0640Constant.NSAM0045E;
import static business.blap.NSAL0640.constant.NSAL0640Constant.NSAM0200I;
import static business.blap.NSAL0640.constant.NSAL0640Constant.NSAM0394W;
import static business.blap.NSAL0640.constant.NSAL0640Constant.XX_CHK_BOX_A1;
import static business.blap.NSAL0640.constant.NSAL0640Constant.ZZZM9004E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDConnectionMgr;
import business.blap.NSAL0640.NSAL0640CMsg;
import business.blap.NSAL0640.NSAL0640Query;
import business.blap.NSAL0640.NSAL0640SMsg;
import business.blap.NSAL0640.NSAL0640_ACMsg;
import business.blap.NSAL0640.NSAL0640_ACMsgArray;
import business.blap.NSAL0640.NSAL0640_ASMsg;
import business.db.DS_CONTRTMsg;
import business.db.S21_PSNTMsg;
import business.db.SVC_CONTR_BRTMsg;
import business.db.SVC_MEMO_RSNTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 *<pre>
 * Update Contract Branch/Representative
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/06   Hitachi         T.Tsuchida      Create          N/A
 * 2016/03/28   Hitachi         M.Gotou         Update          QC#4702,4703,4915
 * 2016/04/04   Hitachi         M.Gotou         Update          QC#4916
 * 2016/12/08   Hitachi         T.Mizuki        Update          QC#4210
 * 2018/05/08   Hitachi         U.Kim           Update          QC#25852
 *</pre>
 */
public class NSAL0640CommonLogic {

    // mod start 2016/12/08 CSA QC#4210
    /**
     * check Select
     * @param cMsg NSAL0640CMsg
     * @param sMsg NSAL0640SMsg
     * @return boolean
     */
    public static boolean checkSelect(NSAL0640CMsg cMsg, NSAL0640SMsg sMsg) {
        boolean rtnVal = true;
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.A, XX_CHK_BOX_A1, ZYPConstant.CHKBOX_ON_Y);
        if (selectedRows.isEmpty()) {
            cMsg.setMessageInfo(NSAM0015E);
            rtnVal = false;
        }
        return rtnVal;
    }
    // mod end 2016/12/08 CSA QC#4210
    /**
     * Create Pull Down
     * @param cMsg NSAL0640CMsg
     */
    public static void createPullDown(NSAL0640CMsg cMsg) {
        createSvcMemoRsnPullDown(cMsg);
    }

    /**
     * Check Branch Name Info
     * @param glblCmpyCd String
     * @param svcContrBrCd String
     * @return boolean
     */
    public static boolean checkBranch(String glblCmpyCd, String svcContrBrCd) {
        boolean rtnVal = true;
        SVC_CONTR_BRTMsg tMsg = NSAL0640Query.getInstance().findBrNmInfo(glblCmpyCd, svcContrBrCd);
        if (tMsg == null) {
            rtnVal = false;
        }
        return rtnVal;
    }

// mod start 2016/03/28 CSA Defect#4702,4703,4915
    /**
     * Check Representative Info 
     * @param glblCmpyCd String
     * @param psnCd String
     * @return boolean
     */
    //public static boolean checkRep(String glblCmpyCd, String tocCd) {
    public static boolean checkRep(String glblCmpyCd, String psnCd) {
        boolean rtnVal = true;
        //TOCTMsg tMsg = NSAL0640Query.getInstance().findRepInfo(glblCmpyCd, tocCd);
        // START 2018/05/08 U.Kim [QC#25852, MOD]
        // S21_PSNTMsg tMsg = NSAL0640Query.getInstance().findRepInfo(glblCmpyCd, psnCd);
        // if (tMsg == null) {
        S21SsmEZDResult rslt = NSAL0640Query.getInstance().findRepInfo(glblCmpyCd, psnCd);
        if (rslt == null) {
        // END 2018/05/08 U.Kim [QC#25852, MOD]
            rtnVal = false;
        }
        return rtnVal;
    }
// mod end 2016/03/28 CSA Defect#4702,4703,4915

    /**
     * Check Relation Branch And Representative
     * @param glblCmpyCd String
     * @param svcContrBrCd String
     * @param tocCd String
     * @return boolean
     */
    public static boolean checkRelationBrAndRep(String glblCmpyCd, String svcContrBrCd, String tocCd) {
        boolean rtnVal = true;
        if (NSAL0640Query.getInstance().findSvcBrResrcReln(glblCmpyCd, svcContrBrCd, tocCd) == 0) {
            rtnVal = false;
        }
        return rtnVal;
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
    // mod start 2016/12/08 CSA QC#4210
    /**
     * Update Contract
     * @param cMsg NSAL0640CMsg
     * @param sMsg NSAL0640SMsg
     */
    public static void updateContract(NSAL0640CMsg cMsg, NSAL0640SMsg sMsg) {
        boolean checkWarn = true;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A1.getValue())) {
                updateContract(cMsg, sMsg, i);
                if (hasValue(sMsg.A.no(i).dsMsgTxt_A1)) {
                    checkWarn = false;
                    EZDConnectionMgr.getInstance().rollback();
                    continue;
                }

                NSAL0640Query.getInstance().insertSvcMemo(cMsg, sMsg, i);
                if (hasValue(sMsg.A.no(i).dsMsgTxt_A1)) {
                    checkWarn = false;
                    EZDConnectionMgr.getInstance().rollback();
                    continue;
                }

                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).dsMsgTxt_A1, getRtnMsg(NSAM0200I));
                EZDConnectionMgr.getInstance().commit();
                // mod end 2016/12/08 CSA QC#4210
            }
        }
        if (!checkWarn) {
            cMsg.setMessageInfo(NSAM0394W);
        }
        cMsg.setMessageInfo(NSAM0200I);
    }

    private static void createSvcMemoRsnPullDown(NSAL0640CMsg cMsg) {
        SVC_MEMO_RSNTMsgArray tMsgAry = NSAL0640Query.getInstance().findSvcMemoRsnPulldownList(cMsg.glblCmpyCd.getValue(), SVC_MEMO_TP.CONTRACT_INVOICE);
        Map<String, String> tMsgKeys = new HashMap<String, String>();
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "svcMemoRsnCd");
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "svcMemoRsnNm");
        ZYPPulldownValueSetter.set(tMsgAry, tMsgKeys, cMsg.svcMemoRsnCd_L, cMsg.svcMemoRsnNm_L);
    }
    // mod start 2016/12/08 CSA QC#4210
    private static void updateContract(NSAL0640CMsg cMsg, NSAL0640SMsg sMsg, int index) {
        NSAL0640_ASMsg asMsg = (NSAL0640_ASMsg) sMsg.A.no(index);
        if (hasValue(asMsg.dsContrPk_A1)) {
            NSAL0640Query query = NSAL0640Query.getInstance();
            DS_CONTRTMsg tMsg = query.getDsContr(cMsg.glblCmpyCd.getValue(), asMsg.dsContrPk_A1.getValue());
            if (tMsg == null) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).dsMsgTxt_A1, getRtnMsg(NSAM0045E, new String[] {DS_CONTR }));
                return;
            } else {
                if (!ZYPDateUtil.isSameTimeStamp(asMsg.ezUpTime_A1.getValue(), asMsg.ezUpTimeZone_A1.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).dsMsgTxt_A1, getRtnMsg(ZZZM9004E));
                    return;
                }

                // mod start 2016/03/28 CSA Defect#4702,4703,4915
                //setValue(tMsg.tocCd, acMsg.tocCd_A2);
                setValue(tMsg.contrAdminPsnCd, asMsg.psnCd_A2);
                setValue(tMsg.svcLineBizCd, asMsg.svcLineBizCd_A2);
                // mod end 2016/03/28 CSA Defect#4702,4703,4915
                setValue(tMsg.svcContrBrCd, asMsg.svcContrBrCd_A2);
                setValue(tMsg.dsContrLastUpdDt, ZYPDateUtil.getSalesDate());
                setValue(tMsg.dsContrLastUpdPsnCd, cMsg.getUserID());
                S21FastTBLAccessor.update(tMsg);

                String rtnCd = tMsg.getReturnCode();
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).dsMsgTxt_A1, getRtnMsg(NSAM0031E, new String[] {DS_CONTR }));
                    return;
                }
            }
        } else {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).dsMsgTxt_A1, getRtnMsg(NSAM0045E, new String[] {DS_CONTR }));
            // mod end 2016/12/08 CSA QC#4210
            return;
        }
    }

    // add start 2016/04/04 CSA Defect#4916
    /**
     * checkSvcBrResrcReln
     * @param glblCmpyCd String
     * @param svcContrBrCd String
     * @param psnCd String
     * @return boolean (true:check OK, false:check NG)
     */
    public static boolean checkSvcBrResrcReln(String glblCmpyCd, String svcContrBrCd, String psnCd) {
        NSAL0640Query query = NSAL0640Query.getInstance();
        S21SsmEZDResult rslt = query.countSvcBrResrcReln(glblCmpyCd, svcContrBrCd, psnCd);
        if (rslt != null && rslt.isCodeNormal()) {
            BigDecimal count = (BigDecimal) rslt.getResultObject();
            if (count == null || BigDecimal.ZERO.compareTo(count) == 0) {
                return false;
            }
        }
        return true;
    }
    // add end 2016/04/04 CSA Defect#4916
    // add start 2016/12/08 CSA QC#4210
    /**
     * copy To BSMsg for Current Page Info
     * @param cMsg NSAL0640CMsg
     * @param sMsg NSAL0640SMsg
     */
    public static void copyCurrentPageToSMsg(NSAL0640CMsg cMsg, NSAL0640SMsg sMsg) {

        // NSAL0640_ACMsg -> NSAL0640_BSMsg
        NSAL0640_ACMsgArray acMsgArray = cMsg.A;
        for (int i = 0; i < acMsgArray.getValidCount(); i++) {
            NSAL0640_ACMsg acMsg = acMsgArray.no(i);
            int targetIdxNum = acMsg.xxRowNum_A1.getValueInt();

            NSAL0640_ASMsg asMsg = sMsg.A.no(targetIdxNum);
            setValue(asMsg.xxChkBox_A1, acMsg.xxChkBox_A1);
            setValue(asMsg.xxGenlFldAreaTxt_A1, acMsg.xxGenlFldAreaTxt_A1);
            setValue(asMsg.xxGenlFldAreaTxt_A2, acMsg.xxGenlFldAreaTxt_A2);
            setValue(asMsg.svcContrBrCd_A2, acMsg.svcContrBrCd_A2);
            setValue(asMsg.svcContrBrDescTxt_A2, acMsg.svcContrBrDescTxt_A2);
            setValue(asMsg.svcLineBizCd_A2, acMsg.svcLineBizCd_A2);
            setValue(asMsg.xxPsnNm_A2, acMsg.xxPsnNm_A2);
            setValue(asMsg.psnCd_A2, acMsg.psnCd_A2);
        }
    }
    /**
     * copy To CMsg for This Page Info
     * @param cMsg NSAL0640CMsg
     * @param sMsg NSAL0640SMsg
     */
    public static void copyThisPageToCMsg(NSAL0640CMsg cMsg, NSAL0640SMsg sMsg) {

        // NSAL0640_BSMsg -> NSAL0640_ACMsg
        int from = cMsg.xxPageShowFromNum.getValueInt();
        int to = cMsg.xxPageShowToNum.getValueInt();
        NSAL0640_ACMsgArray acMsgArray = cMsg.A;
        for (int i = 0 ; from <= to ; i++) {
            EZDMsg.copy(sMsg.A.no(from - 1), null, acMsgArray.no(i), null);
            from++;
        }
    }

    public static void showTargetPage(NSAL0640CMsg cMsg, NSAL0640SMsg sMsg, int pageIdx) {
        NSAL0640_ACMsgArray acMsgArray = cMsg.A;
        ZYPTableUtil.clear(acMsgArray);

        int pageFrom = pageIdx / acMsgArray.length();
        pageFrom = pageFrom * acMsgArray.length();

        int i = pageFrom;
        for (; i < pageFrom + acMsgArray.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, acMsgArray.no(i - pageFrom), null);
            } else {
                break;
            }
        }
        acMsgArray.setValidCount(i - pageFrom);

        pageFrom = pageFrom + 1;
        cMsg.xxPageShowFromNum.setValue(pageFrom);
        cMsg.xxPageShowToNum.setValue(pageFrom + acMsgArray.getValidCount() - 1);
    }
    // add end 2016/12/08 CSA QC#4210
}
