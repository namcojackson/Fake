/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFDL0090.common;

import com.canon.cusa.s21.api.NFA.NFZC102001.NFZC102001;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFDL0090.NFDL0090CMsg;
import business.blap.NFDL0090.NFDL0090SMsg;
import business.db.AR_ACCT_DTTMsg;
import business.db.AR_WRT_OFF_RQSTTMsg;
import business.db.DEF_DPLY_COA_INFOTMsg;
import business.parts.NFZC102001PMsg;
import static business.blap.NFDL0090.constant.NFDL0090Constant.*;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/12   Fujitsu         M.Nakamura      Create          N/A
 * 2022/11/24   Hitachi         S.Naya          Update          QC#57252
 *</pre>
 */
public class NFDL0090CommonLogic {

    /**
     * @param glblCmpyCd String
     * @param inMsg AR_ACCT_DTTMsg
     * @return AR_ACCT_DTTMsg
     */
    public static AR_ACCT_DTTMsg findArAcctDtInfo(String glblCmpyCd, AR_ACCT_DTTMsg inMsg) {

        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.onlBatTpCd.setValue("1");

        AR_ACCT_DTTMsg outMsg = (AR_ACCT_DTTMsg) EZDTBLAccessor.findByKey(inMsg);
        return outMsg;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg NFDL0090CMsg
     * @param sMsg NFDL0090SMsg
     * @param pageFrom int
     */
    public static void setCurrentPageIn(NFDL0090CMsg cMsg, NFDL0090SMsg sMsg, int pageFrom) {

        int cnt = pageFrom;

        for (; cnt < pageFrom + cMsg.A.length(); cnt++) {

            if (cnt < pageFrom + cMsg.A.getValidCount()) {

                EZDMsg.copy(cMsg.A.no(cnt - pageFrom), null, sMsg.A.no(cnt), null);

            } else {
                break;
            }
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg NFDL0090CMsg
     * @param sMsg NFDL0090SMsg
     * @param pageFrom int
     */
    public static void setCurrentPageOut(NFDL0090CMsg cMsg, NFDL0090SMsg sMsg, int pageFrom) {

        if (pageFrom < 0) {
            pageFrom = 0;
        }

        int cnt = pageFrom;
        cMsg.A.clear();

        for (; cnt < pageFrom + cMsg.A.length(); cnt++) {
            if (cnt < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(cnt), null, cMsg.A.no(cnt - pageFrom), null);
            } else {
                break;
            }
        }

        cMsg.A.setValidCount(cnt - pageFrom);
        cMsg.xxPageShowFromNum.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum.setValue(pageFrom + cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
        clearErrorOtherPage(cMsg, sMsg);
    }

    /**
     * @param cMsg NFDL0090CMsg
     * @param sMsg NFDL0090SMsg
     * @return void
     */
    private static void clearErrorOtherPage(NFDL0090CMsg cMsg, NFDL0090SMsg sMsg) {
        int fromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        int thruNum = cMsg.xxPageShowToNum.getValueInt() - 1;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (fromNum <= i && i <= thruNum) {
                continue;
            } else {
                sMsg.A.no(i).xxChkBox_A1.clearErrorInfo();
                sMsg.A.no(i).xxDealApplyAmtNum_A1.clearErrorInfo();
            }
        }
        cMsg.setCommitSMsg(true);
    }
    // START 2022/11/24 S.Naya [QC#57252,ADD]
    /**
     * glCodeCombinationCheck
     * @param bizMsg NFDL0090CMsg
     * @param coa String[]
     * @return boolean
     */
    public static boolean checkGlCodeCombination(NFDL0090CMsg bizMsg, String[] coa, String glblCmpyCd) {
        if (!check9Seg(bizMsg, coa)) {
            return false;
        }

        NFZC102001 api = new NFZC102001();
        NFZC102001PMsg apiMsg = new NFZC102001PMsg();

        ZYPEZDItemValueSetter.setValue(apiMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(apiMsg.xxMstChkFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(apiMsg.xxArcsApiChkFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(apiMsg.xxGlCmbnChkFlg, ZYPConstant.FLG_ON_Y);

        int coaIdx = 0;
        ZYPEZDItemValueSetter.setValue(apiMsg.coaCmpyCd, coa[coaIdx++]);
        ZYPEZDItemValueSetter.setValue(apiMsg.coaBrCd, coa[coaIdx++]);
        ZYPEZDItemValueSetter.setValue(apiMsg.coaCcCd, coa[coaIdx++]);
        ZYPEZDItemValueSetter.setValue(apiMsg.coaAcctCd, coa[coaIdx++]);
        ZYPEZDItemValueSetter.setValue(apiMsg.coaProdCd, coa[coaIdx++]);
        ZYPEZDItemValueSetter.setValue(apiMsg.coaChCd, coa[coaIdx++]);
        ZYPEZDItemValueSetter.setValue(apiMsg.coaAfflCd, coa[coaIdx++]);
        ZYPEZDItemValueSetter.setValue(apiMsg.coaProjCd, coa[coaIdx++]);
        ZYPEZDItemValueSetter.setValue(apiMsg.coaExtnCd, coa[coaIdx++]);
        ZYPEZDItemValueSetter.setValue(apiMsg.resrcObjNm, BIZ_ID);

        api.execute(apiMsg, ONBATCH_TYPE.ONLINE);
        if (apiMsg.xxMsgIdList.getValidCount() > 0) {
            String msgId;
            String msgTxt;
            msgId = apiMsg.xxMsgIdList.no(0).xxMsgId.getValue();
            msgTxt = apiMsg.xxMsgIdList.no(0).xxMsgPrmTxt_0.getValue();
            bizMsg.xxCmntTxt_FS.clearErrorInfo();
            bizMsg.xxCmntTxt_FS.setErrorInfo(1, msgId, new String[] {msgTxt });
            return false;
        } else {
            bizMsg.xxCmntTxt_FS.clearErrorInfo();
        }
        return true;
    }

    /**
     * Check 9 Segment
     * @param bizMsg NFDL0090CMsg
     * @param coa String[]
     * @return boolean
     */
    public static boolean check9Seg(NFDL0090CMsg bizMsg, String[] coa) {

        AR_WRT_OFF_RQSTTMsg arWrtOffRqstTMsg = new AR_WRT_OFF_RQSTTMsg();
        int coaCmpyCdDigit = arWrtOffRqstTMsg.getAttr("adjCoaCmpyCd").getDigit();
        int coaBrCdDigit = arWrtOffRqstTMsg.getAttr("adjCoaBrCd").getDigit();
        int coaCcCdDigit = arWrtOffRqstTMsg.getAttr("adjCoaCcCd").getDigit();
        int coaAcctCdDigit = arWrtOffRqstTMsg.getAttr("adjCoaAcctCd").getDigit();
        int coaProdCdDigit = arWrtOffRqstTMsg.getAttr("adjCoaProdCd").getDigit();
        int coaChCdDigit = arWrtOffRqstTMsg.getAttr("adjCoaChCd").getDigit();
        int coaAfflCdDigit = arWrtOffRqstTMsg.getAttr("adjCoaAfflCd").getDigit();
        int coaProjCdDigit = arWrtOffRqstTMsg.getAttr("adjCoaProjCd").getDigit();
        int coaExtnCdDigit = arWrtOffRqstTMsg.getAttr("adjCoaExtnCd").getDigit();
        int coaIdx = 0;

        if (coa.length != 9) {
            String errMsg = "9 segments";
            errMsg = errMsg.concat("(");
            errMsg = errMsg.concat(String.valueOf(coa.length));
            errMsg = errMsg.concat(")");
            bizMsg.xxCmntTxt_FS.setErrorInfo(1, NFCM0833E, new String[] {errMsg });
            return false;
        }
        if (coa[coaIdx++].length() > coaCmpyCdDigit) {
            bizMsg.xxCmntTxt_FS.setErrorInfo(1, NFCM0833E, new String[] {"Company" });
            return false;
        }
        if (coa[coaIdx++].length() > coaBrCdDigit) {
            bizMsg.xxCmntTxt_FS.setErrorInfo(1, NFCM0833E, new String[] {"Branch" });
            return false;
        }
        if (coa[coaIdx++].length() > coaCcCdDigit) {
            bizMsg.xxCmntTxt_FS.setErrorInfo(1, NFCM0833E, new String[] {"Cost Center" });
            return false;
        }
        if (coa[coaIdx++].length() > coaAcctCdDigit) {
            bizMsg.xxCmntTxt_FS.setErrorInfo(1, NFCM0833E, new String[] {"Account" });
            return false;
        }
        if (coa[coaIdx++].length() > coaProdCdDigit) {
            bizMsg.xxCmntTxt_FS.setErrorInfo(1, NFCM0833E, new String[] {"Product" });
            return false;
        }
        if (coa[coaIdx++].length() > coaChCdDigit) {
            bizMsg.xxCmntTxt_FS.setErrorInfo(1, NFCM0833E, new String[] {"Channel" });
            return false;
        }
        if (coa[coaIdx++].length() > coaAfflCdDigit) {
            bizMsg.xxCmntTxt_FS.setErrorInfo(1, NFCM0833E, new String[] {"Intercompany" });
            return false;
        }
        if (coa[coaIdx++].length() > coaProjCdDigit) {
            bizMsg.xxCmntTxt_FS.setErrorInfo(1, NFCM0833E, new String[] {"Merchandise" });
            return false;
        }
        if (coa[coaIdx++].length() > coaExtnCdDigit) {
            bizMsg.xxCmntTxt_FS.setErrorInfo(1, NFCM0833E, new String[] {"Business Unit" });
            return false;
        }
        return true;
    }

    /**
     * get9SegDefault
     * @param bizMsg
     */
    public static void get9SegDefault(NFDL0090CMsg bizMsg, String glblCmpyCd) {

        DEF_DPLY_COA_INFOTMsg tMsg = getDefDplyCoaInfo(bizMsg, glblCmpyCd);

        if (tMsg == null) {
            bizMsg.coaCmpyCd_DF.clear();
            bizMsg.coaAfflCd_DF.clear();
            bizMsg.coaBrCd_DF.clear();
            bizMsg.coaCcCd_DF.clear();
            bizMsg.coaAcctCd_DF.clear();
            bizMsg.coaProdCd_DF.clear();
            bizMsg.coaChCd_DF.clear();
            bizMsg.coaProjCd_DF.clear();
            bizMsg.coaExtnCd_DF.clear();
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.coaCmpyCd_DF, tMsg.coaCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.coaAfflCd_DF, tMsg.coaAfflCd.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.coaBrCd_DF, tMsg.coaBrCd.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.coaCcCd_DF, tMsg.coaCcCd.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.coaAcctCd_DF, tMsg.coaAcctCd.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.coaProdCd_DF, tMsg.coaProdCd.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.coaChCd_DF, tMsg.coaChCd.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.coaProjCd_DF, tMsg.coaProjCd.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.coaExtnCd_DF, tMsg.coaExtnCd.getValue());
        }

        return;
    }

    /**
     * Get Default Display COA Information
     * @param bizAppId String
     * @return DEF_DPLY_COA_INFOTMsg
     */
    public static DEF_DPLY_COA_INFOTMsg getDefDplyCoaInfo(NFDL0090CMsg bizMsg, String glblCmpyCd) {

        DEF_DPLY_COA_INFOTMsg tMsg = new DEF_DPLY_COA_INFOTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.appFuncId.setValue(bizMsg.getBusinessID());

        return (DEF_DPLY_COA_INFOTMsg) S21FastTBLAccessor.findByKey(tMsg);
    }
    // END   2022/11/24 S.Naya [QC#57252,ADD]
}
