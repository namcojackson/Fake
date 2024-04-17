package business.blap.NFAL0280;

import static business.blap.NFAL0280.constant.NFAL0280Constant.AJE_ACRL_RVSL_INTFC_SQ;
import static business.blap.NFAL0280.constant.NFAL0280Constant.DATE_MONTH_MM_END;
import static business.blap.NFAL0280.constant.NFAL0280Constant.DATE_MONTH_MM_START;
import static business.blap.NFAL0280.constant.NFAL0280Constant.DATE_YEAR_YYYY_END;
import static business.blap.NFAL0280.constant.NFAL0280Constant.DATE_YEAR_YYYY_START;
import static business.blap.NFAL0280.constant.NFAL0280Constant.NFAL0280_ACRL_RVSL_AVAL_BIZ_DAYS;
import static business.blap.NFAL0280.constant.NFAL0280Constant.NFAM0213E;
import static business.blap.NFAL0280.constant.NFAL0280Constant.NZZM0003E;
import static business.blap.NFAL0280.constant.NFAL0280Constant.POST_GL_DATE_PREVIOUS_MONTH_DATE;
import static business.blap.NFAL0280.constant.NFAL0280Constant.ZZM8100I;
import static business.blap.NFAL0280.constant.NFAL0280Constant.ZZZM9015E;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDDBRecordLockedException;
import business.blap.NFAL0280.common.NFAL0280CommonLogic;
import business.db.AJE_ACRL_RVSL_INTFCTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.calendar.S21CalendarUtilConstants;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 * <pre>
 * NFAL0280 Service Accrual Reversal Screen
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2024/01/30   CITS            M.Okamura       Create          QC#62449
 * 2024/04/03   CITS            M.Okamura       Update          QC#63837
 *</pre>
 */
public class NFAL0280BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NFAL0280Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFAL0280Scrn00_CMN_Submit((NFAL0280CMsg) cMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * <pre>
     * NFAL0280Scrn00_CMN_Submit
     * </pre>
     * @param cMsg NFAL0280CMsg
     */
    private void doProcess_NFAL0280Scrn00_CMN_Submit(NFAL0280CMsg cMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        String slsDt = ZYPDateUtil.getSalesDate();
        final S21UserProfileService profileService = getUserProfileService();
        // Target Month Convert
        String strYYYYMM = NFAL0280CommonLogic.getYYYYMM(cMsg.glPerNm_A1.getValue());
        if (!validate(glblCmpyCd, cMsg, slsDt)) {
            return;
        }
        // START 2024/04/04 M.Okamura [QC#63837,MOD]
        String trgtSlsDt = "";
        if (cMsg.xxTrgtDtCd_A1.getValue().equals(POST_GL_DATE_PREVIOUS_MONTH_DATE)) {
            SimpleDateFormat sdf = new SimpleDateFormat(S21CalendarUtilConstants.TYPE_YYYYMMDD);
            Calendar calTrgtSlsDt = Calendar.getInstance();
            int slsDtYyyy = Integer.valueOf(ZYPDateUtil.DateFormatter(slsDt, S21CalendarUtilConstants.TYPE_YYYYMMDD, S21CalendarUtilConstants.TYPE_YYYY));
            int slsDtMm = Integer.valueOf(ZYPDateUtil.DateFormatter(slsDt, S21CalendarUtilConstants.TYPE_YYYYMMDD, S21CalendarUtilConstants.TYPE_MM));
            slsDtMm--;
            int slsDtDd = Integer.valueOf(ZYPDateUtil.DateFormatter(slsDt, S21CalendarUtilConstants.TYPE_YYYYMMDD, S21CalendarUtilConstants.TYPE_DD));
            calTrgtSlsDt.set(slsDtYyyy, slsDtMm, slsDtDd);
            calTrgtSlsDt.add(Calendar.MONTH, -1);
            calTrgtSlsDt.set(calTrgtSlsDt.get(Calendar.YEAR), calTrgtSlsDt.get(Calendar.MONTH), calTrgtSlsDt.getActualMaximum(Calendar.DATE));
            trgtSlsDt = sdf.format(calTrgtSlsDt.getTime());
        } else {
            trgtSlsDt = slsDt;
        }
        // END 2024/04/04 M.Okamura [QC#63837,MOD]
        try {
            // Copy AJE_ACRL_INTFC Records And For Reversal
            NFAL0280CommonLogic.copyAjeAcrlIntfc(glblCmpyCd, cMsg, trgtSlsDt, strYYYYMM);

            // Get AJE_ACRL_INTFC(Reversal) EZINTIME
            String ajeAcrlIntfcEzInTime = NFAL0280CommonLogic.getAjeAcrlIntfcEzInTime(glblCmpyCd, cMsg, trgtSlsDt);

            // Insert AJE_ACRL_RVSL_INTFC(Reversal)
            BigDecimal seqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(AJE_ACRL_RVSL_INTFC_SQ);
            AJE_ACRL_RVSL_INTFCTMsg insertTMsg = new AJE_ACRL_RVSL_INTFCTMsg();
            ZYPEZDItemValueSetter.setValue(insertTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(insertTMsg.ajeAcrlRvslIntfcPk, seqNum);
            ZYPEZDItemValueSetter.setValue(insertTMsg.svcInvChrgTpCd, cMsg.svcInvChrgTpCd_A1);
            ZYPEZDItemValueSetter.setValue(insertTMsg.glDt, trgtSlsDt);
            ZYPEZDItemValueSetter.setValue(insertTMsg.rvslTrgtTs, cMsg.xxDtNm_A1);
            ZYPEZDItemValueSetter.setValue(insertTMsg.rqstUsrId, profileService.getContextUserInfo().getUserId());
            ZYPEZDItemValueSetter.setValue(insertTMsg.rqstCratTs, ajeAcrlIntfcEzInTime);
            S21FastTBLAccessor.insert(insertTMsg);
            cMsg.setMessageInfo(ZZM8100I);
            return;
        } catch (EZDDBRecordLockedException e) {
            cMsg.setMessageInfo(NZZM0003E);
            return;
        }
    }

    /**
     * validate
     * @param glblCmpyCd glblCmpyCd
     * @param cMsg NFAL0280CMsg
     * @param slsDt String
     * @return boolean
     */
    private boolean validate(String glblCmpyCd, NFAL0280CMsg cMsg, String slsDt) {
        // Post GL Date Check
        if (POST_GL_DATE_PREVIOUS_MONTH_DATE.compareTo(cMsg.xxTrgtDtCd_A1.getValue()) == 0) {
            int acrlRvslAvalBizDays = ZYPCodeDataUtil.getNumConstValue(NFAL0280_ACRL_RVSL_AVAL_BIZ_DAYS, glblCmpyCd).intValue();
            String acrlRvslAftrDayStr = ZYPCodeDataUtil.getNumConstValue(NFAL0280_ACRL_RVSL_AVAL_BIZ_DAYS, glblCmpyCd).toString();
            String startBizDt = ZYPDateUtil.getStartOfMonthBusinessDay(glblCmpyCd, slsDt.substring(DATE_YEAR_YYYY_START, DATE_YEAR_YYYY_END), slsDt.substring(DATE_MONTH_MM_START, DATE_MONTH_MM_END));

            String acrlUnAvalDay = ZYPDateUtil.addBusinessDay(glblCmpyCd, startBizDt, acrlRvslAvalBizDays);

            if (acrlUnAvalDay.compareTo(slsDt) <= 0) {
                cMsg.setMessageInfo(NFAM0213E, new String[] {acrlRvslAftrDayStr });
                return false;
            }

        }

        // AJE_ACRL_RVSL_INTFC Duplicate Check
        if (chkAjeAcrlRvslIntfcDup(glblCmpyCd, cMsg)) {
            cMsg.setMessageInfo(ZZZM9015E);
            return false;
        }

        return true;
    }

    /**
     * setPulldownSvcInvChrgTpCdList
     * @param glblCmpyCd glblCmpyCd
     * @param cMsg NFAL0280CMsg
     * @return boolean
     */
    public static boolean chkAjeAcrlRvslIntfcDup(String glblCmpyCd, NFAL0280CMsg cMsg) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("rvslTrgtTs", cMsg.xxDtNm_A1);
        param.put("svcInvChrgTpCd", cMsg.svcInvChrgTpCd_A1);
        // Execute
        S21SsmEZDResult result = NFAL0280Query.getInstance().chkAjeAcrlRvslIntfcDup(param);
        BigDecimal cnt = (BigDecimal) result.getResultObject();
        if (!result.isCodeNotFound() && cnt.compareTo(BigDecimal.ZERO) == 1) {
            return true;
        }

        return false;
    }

}
