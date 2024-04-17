package business.servlet.NFAL0280.common;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import parts.servletcommon.EZDCommonHandler;

import business.servlet.NFAL0280.NFAL0280BMsg;
import static business.servlet.NFAL0280.constant.NFAL0280Constant.*;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;

import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * NFAL0280 Service Accrual Reversal Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2024/01/30   CITS            M.Okamura       Create          QC#62449
 *</pre>
 */
public class NFAL0280CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NFAL0270BMsg
     * @param userId userId
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NFAL0280BMsg scrnMsg, String userId) {

        initCommonButton(handler, userId);
        setTblBackColor(scrnMsg);
    }

    /**
     * Method name: initCommonButton <dd>The method explanation:
     * Initialize Common Button Control. <dd>Remarks:
     * @param handler EZ Common Handler
     * @param userId Login authority
     */
    public static final void initCommonButton(EZDCommonHandler handler, String userId) {

        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        if (hasUpdateAuthority(userId)) {
            // For Updated by
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
        } else {
            // For Referencer
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        }
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);

    }

    /**
     * Method name: afterSubmitCommonButton <dd>The method
     * explanation: Initialize Common Button Control. <dd>Remarks:
     * @param handler EZ Common Handler
     * @param userId Login authority
     */
    public static final void afterSubmitCommonButton(EZDCommonHandler handler, String userId) {

        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
    }

    /**
     * Set table's back color
     * @param scrnMsg NFAL0270BMsg
     */
    public static final void setTblBackColor(NFAL0280BMsg scrnMsg) {

    }

    /**
     * Return true if userId have update authority.
     * @param userId userId
     * @return update authority : true
     */
    public static final boolean hasUpdateAuthority(String userId) {
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        if (profileService.isFunctionGranted(userId, new String[] {FUNC_UPDATE })) {
            return true;
        }
        return false;
    }

    /**
     * Check Date (Period Style)
     * @param scrnMsg NFAL0280BMsg
     */
    public static void chkPerNm(NFAL0280BMsg scrnMsg) {
        scrnMsg.glPerNm_A1.clearErrorInfo();
        if (scrnMsg.glPerNm_A1.getValue().length() != DATE_MONTH_MM_END) {
            scrnMsg.glPerNm_A1.setErrorInfo(1, NFAM0043E, new String[] {"Target Month" });
            scrnMsg.addCheckItem(scrnMsg.glPerNm_A1);
            return;
        }

        if (!"-".equals(scrnMsg.glPerNm_A1.getValue().substring(PERIOD_MONTH_MMM_END, PERIOD_YEAR_YY_START))) {
            scrnMsg.glPerNm_A1.setErrorInfo(1, NFAM0043E, new String[] {"Target Month" });
            scrnMsg.addCheckItem(scrnMsg.glPerNm_A1);
            return;
        }

        String monthNm = scrnMsg.glPerNm_A1.getValue().substring(PERIOD_MONTH_MMM_START, PERIOD_MONTH_MMM_END);
        monthNm = monthNm.toUpperCase(Locale.US);

        Set<String> monthSet = new HashSet<String>();

        monthSet.add("JAN");
        monthSet.add("FEB");
        monthSet.add("MAR");
        monthSet.add("APR");
        monthSet.add("MAY");
        monthSet.add("JUN");
        monthSet.add("JUL");
        monthSet.add("AUG");
        monthSet.add("SEP");
        monthSet.add("OCT");
        monthSet.add("NOV");
        monthSet.add("DEC");

        if (!monthSet.contains(monthNm)) {
            scrnMsg.glPerNm_A1.setErrorInfo(1, NFAM0043E, new String[] {"Target Month" });
            scrnMsg.addCheckItem(scrnMsg.glPerNm_A1);
            return;
        }

        String year = scrnMsg.glPerNm_A1.getValue().substring(PERIOD_YEAR_YY_START, PERIOD_YEAR_YY_END);

        try {
            Integer.parseInt(year);
        } catch (NumberFormatException ex) {
            scrnMsg.glPerNm_A1.setErrorInfo(1, NFAM0043E, new String[] {"Target Month" });
            scrnMsg.addCheckItem(scrnMsg.glPerNm_A1);
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.glPerNm_A1, (String) monthNm + "-" + year);
    }

}
