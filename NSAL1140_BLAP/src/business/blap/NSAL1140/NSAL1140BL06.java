package business.blap.NSAL1140;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static business.blap.NSAL1140.constant.NSAL1140Constant.*;
import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDMsgCommons;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;

import business.blap.NSAL1140.common.NSAL1140CommonLogic;
import business.db.SVC_SPLY_ABUSE_ACTTMsg;
import business.db.SVC_SPLY_ABUSE_STAGETMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Supply Watch.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/12   Hitachi         T.Nishimura     Create          N/A
 * 2016/03/25   Hitachi         A.Kohinata      Update          QC#6051
 * 2016/03/28   Hitachi         A.Kohinata      Update          QC#6053
 * 2017/07/26   Hitachi         U.Kim           Update          QC#18316
 *</pre>
 */
public class NSAL1140BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        String screenAplId = cMsg.getScreenAplID();

        try {
            if ("NSAL1140Scrn00_CMN_ColClear".equals(screenAplId)) {
                ZYPGUITableColumn.clearColData((NSAL1140CMsg) cMsg, (NSAL1140SMsg) sMsg);
            } else if ("NSAL1140Scrn00_CMN_ColSave".equals(screenAplId)) {
                ZYPGUITableColumn.setColData((NSAL1140CMsg) cMsg, (NSAL1140SMsg) sMsg);
            } else if ("NSAL1140Scrn00_SaveSrchOpt".equals(screenAplId)) {
                doProcess_NSAL1140_SaveSearch((NSAL1140CMsg) cMsg, (NSAL1140SMsg) sMsg);
            } else if ("NSAL1140Scrn00_DelSrchOpt".equals(screenAplId)) {
                doProcess_NSAL1140_DeleteSearch((NSAL1140CMsg) cMsg, (NSAL1140SMsg) sMsg);
            } else if ("NSAL1140Scrn00_CMN_Submit".equals(screenAplId)) {
                doProcess_NSAL1140_CmnSubmit((NSAL1140CMsg) cMsg, (NSAL1140SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplId);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL1140_CmnSubmit(NSAL1140CMsg cMsg, NSAL1140SMsg sMsg) {
        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL1140CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int chkCount = 0;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSAL1140_ASMsg aSMsg = sMsg.A.no(i);
            if (ZYPConstant.FLG_ON_Y.equals(aSMsg.xxChkBox_A.getValue())) {
                chkCount++;
            }
        }
        if (chkCount == 0) {
            cMsg.setMessageInfo(NSAM0015E);
            return;
        }

        if (ZYPConstant.FLG_ON_Y.equals(cMsg.ovwrtAbuseFlg_2V.getValue())) {
            if (!hasValue(cMsg.abuseOvwrtRsnCd_1V)) {
                cMsg.ovwrtAbuseFlg_2V.setErrorInfo(1, NSAM0081E, new String[] {"Overwrite Enforcement", "Overwrite Reason" });
                cMsg.abuseOvwrtRsnCd_1V.setErrorInfo(1, NSAM0081E, new String[] {"Overwrite Enforcement", "Overwrite Reason" });
                return;
            }
            if (!hasValue(cMsg.abuseActCd_1V)) {
                cMsg.ovwrtAbuseFlg_2V.setErrorInfo(1, NSAM0081E, new String[] {"Overwrite Enforcement", "Action" });
                cMsg.abuseActCd_1V.setErrorInfo(1, NSAM0081E, new String[] {"Overwrite Enforcement", "Action" });
                return;
            }
        }

        // START 2017/07/26 U.Kim [QC#18316,ADD]
        if (!hasValue(cMsg.abuseActCd_1V) && hasValue(cMsg.abuseActCmntTxt)) {
            cMsg.abuseActCd_1V.setErrorInfo(1, NSAM0066E, new String[] {"Note", "Action" });
            return;
        }
        // END 2017/07/26 U.Kim [QC#18316,ADD]

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSAL1140_ASMsg aSMsg = sMsg.A.no(i);
            if (ZYPConstant.FLG_ON_Y.equals(aSMsg.xxChkBox_A.getValue())) {
                SVC_SPLY_ABUSE_STAGETMsg tMsg = checkUpdateSvcSplyAbuseStage(cMsg, aSMsg);
                if (tMsg == null) {
                    cMsg.setMessageInfo(ZZZM9004E);
                    return;
                }
                // update SVC_SPLY_ABUSE_STAGE
                if (!updateSvcSplyAbuseStage(cMsg, tMsg)) {
                    cMsg.setMessageInfo(NSAM0031E, new String[] {SVC_SPLY_ABUSE_STAGE });
                    return;
                }
                // insert SVC_SPLY_ABUSE_ACT
                if (hasValue(cMsg.ovwrtAbuseFlg_2V)) {
                    if (!insertSvcSplyAbuseAct(cMsg, sMsg, aSMsg)) {
                        cMsg.setMessageInfo(NSAM0032E, new String[] {SVC_SPLY_ABUSE_ACT });
                        return;
                    }
                // START 2017/07/26 U.Kim [QC#18316,ADD]
                } else if (hasValue(cMsg.abuseActCd_1V)) {
                    if (!insertSvcSplyAbuseActForNotes(cMsg, sMsg, aSMsg)) {
                        cMsg.setMessageInfo(NSAM0032E, new String[] {SVC_SPLY_ABUSE_ACT });
                        return;
                    }
                // END 2017/07/26 U.Kim [QC#18316,ADD]
                }
            }
        }

        cMsg.setMessageInfo(NZZM0002I);
        cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_COMMIT);

        NSAL1140CommonLogic.clearMsgForSearch(cMsg, sMsg);
        if (NSAL1140CommonLogic.isErrorSearchCondition(cMsg)) {
            return;
        }
        NSAL1140CommonLogic.getSearchData(cMsg, sMsg);
        NSAL1140CommonLogic.pagenation(cMsg, sMsg, 0);
    }

    private SVC_SPLY_ABUSE_STAGETMsg checkUpdateSvcSplyAbuseStage(NSAL1140CMsg cMsg, NSAL1140_ASMsg aSMsg) {
        SVC_SPLY_ABUSE_STAGETMsg tMsg = getSvcSplyAbuseStage(cMsg.glblCmpyCd.getValue(), aSMsg.svcSplyAbuseStagePk.getValue());
        if (tMsg == null) {
            return null;
        }
        if (!ZYPDateUtil.isSameTimeStamp(aSMsg.ezUpTime.getValue(), aSMsg.ezUpTimeZone.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
            return null;
        }
        return tMsg;
    }

    private boolean updateSvcSplyAbuseStage(NSAL1140CMsg cMsg, SVC_SPLY_ABUSE_STAGETMsg tMsg) {

        // TERM_COND_CHK_DT
        if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.xxChkBox_F.getValue())) {
            setValue(tMsg.termCondChkDt, cMsg.slsDt);
            setValue(tMsg.termCondChkFlg, ZYPConstant.FLG_ON_Y);
        } else {
            setValue(tMsg.termCondChkFlg, ZYPConstant.FLG_OFF_N);
        }
        if (hasValue(cMsg.ovwrtAbuseFlg_2V)) {
            // OVWRT_ABUSE_FLG
            setValue(tMsg.ovwrtAbuseFlg, cMsg.ovwrtAbuseFlg_2V);
            // ABUSE_OVWRT_VAR_PCT
            setValue(tMsg.abuseVarPct, cMsg.abuseVarPct);
            // ABUSE_OVWRT_RSN_CD
            setValue(tMsg.abuseOvwrtRsnCd, cMsg.abuseOvwrtRsnCd_1V);
        }
        if (hasValue(cMsg.svcSplyContrStsCd_1V)) {
            // SVC_SPLY_CONTR_STS_CD
            setValue(tMsg.svcSplyContrStsCd, cMsg.svcSplyContrStsCd_1V);
            // SVC_SPLY_CONTR_UPD_DT
            setValue(tMsg.svcSplyContrUpdDt, cMsg.slsDt);
        }
        EZDTBLAccessor.update(tMsg);
        String rtnCd = tMsg.getReturnCode();
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            return false;
        }
        return true;
    }

    private boolean insertSvcSplyAbuseAct(NSAL1140CMsg cMsg, NSAL1140SMsg sMsg, NSAL1140_ASMsg aSMsg) {
        SVC_SPLY_ABUSE_ACTTMsg tMsg = new SVC_SPLY_ABUSE_ACTTMsg();
        // GLBL_CMPY_CD
        setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
        // SVC_SPLY_ABUSE_ACT_PK
        setValue(tMsg.svcSplyAbuseActPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_SPLY_ABUSE_ACT_SQ));
        // SHIP_TO_CUST_ACCT_CD
        setValue(tMsg.shipToCustAcctCd, aSMsg.shipToCustAcctCd_A);
        // DS_CONTR_PK
        setValue(tMsg.dsContrPk, aSMsg.dsContrPk);
        // SVC_PGM_MDSE_CD
        setValue(tMsg.svcPgmMdseCd, aSMsg.svcPgmMdseCd);
        // CRAT_DT
        setValue(tMsg.cratDt, cMsg.slsDt);
        // CRAT_USR_ID
        setValue(tMsg.cratUsrId, getContextUserInfo().getUserId());
        // PROC_DT
        setValue(tMsg.procDt, cMsg.slsDt);
        // ABUSE_ACT_CD
        setValue(tMsg.abuseActCd, cMsg.abuseActCd_1V);
        // ABUSE_ACT_CMNT_TXT
        setValue(tMsg.abuseActCmntTxt, cMsg.abuseActCmntTxt);
        // SVC_SPLY_ABUSE_STAGE_PK
        setValue(tMsg.svcSplyAbuseStagePk, aSMsg.svcSplyAbuseStagePk);
        // APVL_USR_ID
        setValue(tMsg.apvlUsrId, cMsg.psnCd_1V);
        // SVC_SPLY_EXPR_DT
        setValue(tMsg.svcSplyExprDt, cMsg.svcSplyExprDt);
        // OVWRT_ABUSE_FLG
        if (hasValue(cMsg.ovwrtAbuseFlg_2V)) {
            setValue(tMsg.ovwrtAbuseFlg, cMsg.ovwrtAbuseFlg_2V);
        } else {
            setValue(tMsg.ovwrtAbuseFlg, aSMsg.ovwrtAbuseFlg_A);
        }
        // ABUSE_OVWRT_RSN_CD
        setValue(tMsg.abuseOvwrtRsnCd, cMsg.abuseOvwrtRsnCd_1V);

        EZDTBLAccessor.insert(tMsg);
        String rtnCd = tMsg.getReturnCode();
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            return false;
        }
        return true;
    }

    // START 2017/07/26 U.Kim [QC#18316,ADD]
    private boolean insertSvcSplyAbuseActForNotes(NSAL1140CMsg cMsg, NSAL1140SMsg sMsg, NSAL1140_ASMsg aSMsg) {
        SVC_SPLY_ABUSE_ACTTMsg tMsg = new SVC_SPLY_ABUSE_ACTTMsg();
        // GLBL_CMPY_CD
        setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
        // SVC_SPLY_ABUSE_ACT_PK
        setValue(tMsg.svcSplyAbuseActPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_SPLY_ABUSE_ACT_SQ));
        // SHIP_TO_CUST_ACCT_CD
        setValue(tMsg.shipToCustAcctCd, aSMsg.shipToCustAcctCd_A);
        // DS_CONTR_PK
        setValue(tMsg.dsContrPk, aSMsg.dsContrPk);
        // SVC_PGM_MDSE_CD
        setValue(tMsg.svcPgmMdseCd, aSMsg.svcPgmMdseCd);
        // CRAT_DT
        setValue(tMsg.cratDt, cMsg.slsDt);
        // CRAT_USR_ID
        setValue(tMsg.cratUsrId, getContextUserInfo().getUserId());
        // PROC_DT
        setValue(tMsg.procDt, cMsg.slsDt);
        // ABUSE_ACT_CD
        setValue(tMsg.abuseActCd, cMsg.abuseActCd_1V);
        // ABUSE_ACT_CMNT_TXT
        setValue(tMsg.abuseActCmntTxt, cMsg.abuseActCmntTxt);
        // SVC_SPLY_ABUSE_STAGE_PK
        setValue(tMsg.svcSplyAbuseStagePk, aSMsg.svcSplyAbuseStagePk);
        // OVWRT_ABUSE_FLG
        setValue(tMsg.ovwrtAbuseFlg, ZYPConstant.FLG_OFF_N);

        EZDTBLAccessor.insert(tMsg);
        String rtnCd = tMsg.getReturnCode();
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            return false;
        }
        return true;
    }
    // START 2017/07/26 U.Kim [QC#18316,ADD]

    /**
     * get ActualCounterInterfac By Primary Key
     * @param glblCmpyCd String
     * @param svcSplyAbuseStagePk BigDecimal
     * @return SVC_SPLY_ABUSE_STAGETMsg
     */
    public static SVC_SPLY_ABUSE_STAGETMsg getSvcSplyAbuseStage(String glblCmpyCd, BigDecimal svcSplyAbuseStagePk) {
        SVC_SPLY_ABUSE_STAGETMsg prmTMsg = new SVC_SPLY_ABUSE_STAGETMsg();
        setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        setValue(prmTMsg.svcSplyAbuseStagePk, svcSplyAbuseStagePk);
        return (SVC_SPLY_ABUSE_STAGETMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    private void doProcess_NSAL1140_SaveSearch(NSAL1140CMsg cMsg, NSAL1140SMsg sMsg) {
        if (!hasValue(cMsg.srchOptPk_1V) // 
                && !hasValue(cMsg.srchOptNm_02)) {
            cMsg.srchOptNm_02.setErrorInfo(1, ZZM9000E, new String[] {"Search Option Name" });
            return;
        }
        if (NSAL1140CommonLogic.isExistSaveSearchName(cMsg)) {
            cMsg.srchOptNm_02.setErrorInfo(1, NSAM0059E, new String[] {"Search Option Name" });
            return;
        }

        NSAL1140CommonLogic.callNszc0330forSaveSearch(cMsg, sMsg, getContextUserInfo().getUserId());
    }

    private void doProcess_NSAL1140_DeleteSearch(NSAL1140CMsg cMsg, NSAL1140SMsg sMsg) {
        if (!hasValue(cMsg.srchOptPk_1V)) {
            cMsg.srchOptPk_1V.setErrorInfo(1, NSAM0199E, new String[] {"Saved Search Options" });
            return;
        }

        NSAL1140CommonLogic.callNszc0330forDeleteSearch(cMsg, sMsg, getContextUserInfo().getUserId());
    }
}
