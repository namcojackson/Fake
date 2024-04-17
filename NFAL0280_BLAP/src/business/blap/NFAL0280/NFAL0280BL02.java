package business.blap.NFAL0280;

import static business.blap.NFAL0280.constant.NFAL0280Constant.DATE_MONTH_MM_END;
import static business.blap.NFAL0280.constant.NFAL0280Constant.DATE_YEAR_YYYY_START;
import static business.blap.NFAL0280.constant.NFAL0280Constant.PREV_MTH;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NFAL0280.common.NFAL0280CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.internal.calendar.S21CalendarUtilConstants;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * NFAL0280 Service Accrual Reversal Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2024/01/30   CITS            M.Okamura       Create          QC#62449
 *</pre>
 */
public class NFAL0280BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NFAL0280_INIT".equals(screenAplID)) {
                doProcess_NFAL0280_INIT((NFAL0280CMsg) cMsg);
            } else if ("NFAL0280Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NFAL0280_INIT((NFAL0280CMsg) cMsg);
            } else if ("NFAL0280Scrn00_OnChange_ChargeType".equals(screenAplID)) {
                doProcess_NFAL0280Scrn00_OnChange_ChargeType((NFAL0280CMsg) cMsg);
            } else if ("NFAL0280Scrn00_OnBlur_TargetMonth".equals(screenAplID)) {
                doProcess_NFAL0280Scrn00_OnBlur_TargetMonth((NFAL0280CMsg) cMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * <pre>
     * doProcess_NFAL0280_INIT
     *  </pre>
     * @param cMsg
     */
    private void doProcess_NFAL0280_INIT(NFAL0280CMsg cMsg) {

        String glblCmpyCd = getGlobalCompanyCode();
        String lastMonthMMMYY = "";
        String lastMonthYYYYMM = "";

        // Charge Type
        NFAL0280CommonLogic.setPulldownSvcInvChrgTpCdList(glblCmpyCd, cMsg);

        ZYPEZDItemValueSetter.setValue(cMsg.svcInvChrgTpCd_A1, SVC_INV_CHRG_TP.BASE_CHARGE);

        lastMonthMMMYY = getLastMonthDate(ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(cMsg.glPerNm_A1, lastMonthMMMYY);

        // Target Month
        NFAL0280CommonLogic.setTargetMonth(lastMonthMMMYY, cMsg);

        // Target Month Convert
        lastMonthYYYYMM = NFAL0280CommonLogic.getYYYYMM(lastMonthMMMYY);

        // Target Data to be reversed
        NFAL0280CommonLogic.setPulldownTrgtDataToBeRvrsList(glblCmpyCd, lastMonthYYYYMM, cMsg);

        // Post GL Date
        ZYPEZDItemValueSetter.setValue(cMsg.xxTrgtDtCd_A1, "1");

    }

    /**
     * <pre>
     * doProcess_NFAL0280Scrn00_OnChange_ChargeType
     *  </pre>
     * @param cMsg
     */
    private void doProcess_NFAL0280Scrn00_OnChange_ChargeType(NFAL0280CMsg cMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        String strYYYYMM = "";

        // Target Month Convert
        strYYYYMM = NFAL0280CommonLogic.getYYYYMM(cMsg.glPerNm_A1.getValue());

        // Target Data to be reversed
        NFAL0280CommonLogic.setPulldownTrgtDataToBeRvrsList(glblCmpyCd, strYYYYMM, cMsg);
    }

    /**
     * <pre>
     * doProcess_NFAL0280Scrn00_OnBlur_TargetMonth
     *  </pre>
     * @param cMsg
     */
    private void doProcess_NFAL0280Scrn00_OnBlur_TargetMonth(NFAL0280CMsg cMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        String strYYYYMM = "";
        // Target Month Convert
        strYYYYMM = NFAL0280CommonLogic.getYYYYMM(cMsg.glPerNm_A1.getValue());

        // Target Data to be reversed
        NFAL0280CommonLogic.setPulldownTrgtDataToBeRvrsList(glblCmpyCd, strYYYYMM, cMsg);
    }

    /**
     * getLastMonthDate
     * @param String slsDt
     * @return String
     */
    private String getLastMonthDate(String slsDt) {
        SimpleDateFormat sdf = new SimpleDateFormat(S21CalendarUtilConstants.TYPE_YYYYMMDD);
        Calendar calLastMonthDate = Calendar.getInstance();
        int slsDtYyyy = Integer.valueOf(ZYPDateUtil.DateFormatter(slsDt, S21CalendarUtilConstants.TYPE_YYYYMMDD, S21CalendarUtilConstants.TYPE_YYYY));
        int slsDtMm = Integer.valueOf(ZYPDateUtil.DateFormatter(slsDt, S21CalendarUtilConstants.TYPE_YYYYMMDD, S21CalendarUtilConstants.TYPE_MM));
        int slsDtDd = Integer.valueOf(ZYPDateUtil.DateFormatter(slsDt, S21CalendarUtilConstants.TYPE_YYYYMMDD, S21CalendarUtilConstants.TYPE_DD));
        calLastMonthDate.set(slsDtYyyy, slsDtMm, slsDtDd);
        calLastMonthDate.add(Calendar.MONTH, PREV_MTH);

        return NFAL0280CommonLogic.convertGlPeriod(sdf.format(calLastMonthDate.getTime()).substring(DATE_YEAR_YYYY_START, DATE_MONTH_MM_END));
    }

}
