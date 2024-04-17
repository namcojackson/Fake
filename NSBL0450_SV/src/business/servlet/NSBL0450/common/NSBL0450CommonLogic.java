/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0450.common;

import static business.servlet.NSBL0450.constant.NSBL0450Constant.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import parts.common.EZDBDateItem;
import parts.common.EZDBStringItem;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSBL0450.NSBL0450BMsg;

/**
 *<pre>
 * Supplemental Task
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/05   Hitachi         T.Iwamoto         Create          N/A
 * 2017/03/01   Hitachi         A.Kohinata        Update          QC#17608
 *</pre>
 */
public class NSBL0450CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0450BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NSBL0450BMsg scrnMsg) {
        initCommonButton(handler, scrnMsg);
        controlScreenFields(handler, scrnMsg);
    }

    /**
     * Method name: initCommonButton <dd>The method explanation: init
     * Common Button Control. <dd>Remarks:
     * @param handler EZ Common Handler
     * @param scrnMsg NSBL0450BMsg
     */
    private static final void initCommonButton(EZDCommonHandler handler, NSBL0450BMsg scrnMsg) {

        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNROAD[0], BTN_CMN_DOWNROAD[1], BTN_CMN_DOWNROAD[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BLANK7[0], BTN_CMN_BLANK7[1], BTN_CMN_BLANK7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        if (hasUpdateFuncId()) {
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        }
    }

    /**
     * Control screen fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0450BMsg
     */
    private static final void controlScreenFields(EZDCommonHandler handler, NSBL0450BMsg scrnMsg) {
        controlScreenHeaderFields(handler, scrnMsg);
        controlScreenTableFields(handler, scrnMsg);
        controlScreenDetailFields(handler, scrnMsg);
    }

    /**
     * hasRegistFuncId
     * @return boolean true:upedate false:reference
     */
    private static boolean hasUpdateFuncId() {

        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BUSINESS_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException("You can't operate Supplemantal Task (" + BUSINESS_ID + "). UserID is -> " + S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }

        for (String func : funcList) {
            if (AUTH_UPDATE.equals(func)) {
                return true;
            }
        }

        return false;
    }

    /**
     * controlScreenHeaderFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0450BMsg
     */
    private static final void controlScreenHeaderFields(EZDCommonHandler handler, NSBL0450BMsg scrnMsg) {
    }

    /**
     * controlScreenTableFields
     * @param scrnMsg NSBL0450BMsg
     */
    private static final void controlScreenTableFields(EZDCommonHandler handler, NSBL0450BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).svcSupplTaskNum_A.setInputProtected(true);
            scrnMsg.A.no(i).svcSupplTaskTpDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).techPsnNm_A.setInputProtected(true);
            scrnMsg.A.no(i).mgrNm_A.setInputProtected(true);
            scrnMsg.A.no(i).xxCmntTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).svcSupplFromDt_A.setInputProtected(true);
            scrnMsg.A.no(i).xxDtTm_A1.setInputProtected(true);
            scrnMsg.A.no(i).svcSupplToDt_A.setInputProtected(true);
            scrnMsg.A.no(i).xxDtTm_A2.setInputProtected(true);
            scrnMsg.A.no(i).xxDtTm_A3.setInputProtected(true);
            scrnMsg.A.no(i).xxDtTm_A4.setInputProtected(true);
        }
    }

    /**
     * controlScreenDetailFields
     * @param scrnMsg NSBL0450BMsg
     */
    private static final void controlScreenDetailFields(EZDCommonHandler handler, NSBL0450BMsg scrnMsg) {
        scrnMsg.svcSupplTaskNum_D.setInputProtected(true);
        scrnMsg.svcSupplTaskTpDescTxt_D.setInputProtected(true);
        scrnMsg.techPsnNm_D.setInputProtected(true);
        scrnMsg.mgrNm_D.setInputProtected(true);
        scrnMsg.xxCmntTxt_D.setInputProtected(true);
        scrnMsg.svcTaskNum_D.setInputProtected(true);
        scrnMsg.xxDtTm_D4.setInputProtected(true);
        scrnMsg.xxAllPsnNm_D1.setInputProtected(true);
        scrnMsg.cratDt_D.setInputProtected(true);
        scrnMsg.xxDtTm_D6.setInputProtected(true);
        scrnMsg.xxAllPsnNm_D2.setInputProtected(true);
        scrnMsg.updDt_D.setInputProtected(true);
        scrnMsg.xxDtTm_D7.setInputProtected(true);
    }

    /**
     * checkMandatory
     * @param scrnMsg NSBL0450BMsg
     */
    public static void checkMandatory(NSBL0450BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.svcSupplTaskNum_S);
        scrnMsg.addCheckItem(scrnMsg.techPsnNm_S);
        scrnMsg.addCheckItem(scrnMsg.mgrNm_S);
        scrnMsg.addCheckItem(scrnMsg.svcSupplTaskTpCd_SS);
        scrnMsg.addCheckItem(scrnMsg.cratDt_S);

        if (!searchMandatory(scrnMsg)) {
            scrnMsg.setMessageInfo(NSBM0012E);
            scrnMsg.svcSupplTaskNum_S.setErrorInfo(1, NSBM0012E);
            scrnMsg.techPsnNm_S.setErrorInfo(1, NSBM0012E);
            scrnMsg.mgrNm_S.setErrorInfo(1, NSBM0012E);
            scrnMsg.svcSupplTaskTpCd_SS.setErrorInfo(1, NSBM0012E);
            scrnMsg.cratDt_S.setErrorInfo(1, NSBM0012E);
        }
    }

    /**
     * searchMandatory
     * @param scrnMsg NSBL0450BMsg
     * @return true/false
     */
    private static boolean searchMandatory(NSBL0450BMsg scrnMsg) {
        if (hasValue(scrnMsg.svcSupplTaskNum_S)) {
            return true;
        }
        if (hasValue(scrnMsg.techPsnNm_S)) {
            return true;
        }
        if (hasValue(scrnMsg.mgrNm_S)) {
            return true;
        }
        if (hasValue(scrnMsg.svcSupplTaskTpCd_SS)) {
            return true;
        }
        if (hasValue(scrnMsg.cratDt_S)) {
            return true;
        }
        return false;
    }

    /**
     * <pre>
     * checkInput for detail
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0450BMsg
     * @param eventName String
     */
    public static final void checkInputForDetail(EZDCommonHandler handler, NSBL0450BMsg scrnMsg, String eventName) {
        if (!hasValue(scrnMsg.svcSupplTaskNum_D)) {
            return;
        }
        scrnMsg.addCheckItem(scrnMsg.svcSupplFromDt_D);
        scrnMsg.addCheckItem(scrnMsg.xxDtTm_D1);
        scrnMsg.addCheckItem(scrnMsg.svcSupplToDt_D);
        scrnMsg.addCheckItem(scrnMsg.xxDtTm_D2);
        scrnMsg.addCheckItem(scrnMsg.xxDtTm_D3);
        scrnMsg.addCheckItem(scrnMsg.xxDtTm_D5);

        // start time mandatory check
        if (!hasValue(scrnMsg.svcSupplFromDt_D)) {
            scrnMsg.setMessageInfo(NSBM0036E, new String[] {NAME_START });
            scrnMsg.svcSupplFromDt_D.setErrorInfo(1, NSBM0036E, new String[] {NAME_START });
        }
        if (!hasValue(scrnMsg.xxDtTm_D1)) {
            scrnMsg.setMessageInfo(NSBM0036E, new String[] {NAME_START });
            scrnMsg.xxDtTm_D1.setErrorInfo(1, NSBM0036E, new String[] {NAME_START });
        }

        // end time check
        if (!CHANGE_TIME.equals(eventName)) {

            if (!hasValue(scrnMsg.svcSupplToDt_D) && hasValue(scrnMsg.xxDtTm_D2)) {
                scrnMsg.setMessageInfo(NSBM0134E, new String[] {END_TIME, END_DT });
                scrnMsg.svcSupplToDt_D.setErrorInfo(1, NSBM0134E, new String[] {END_TIME, END_DT });
            }
            if (hasValue(scrnMsg.svcSupplToDt_D) && !hasValue(scrnMsg.xxDtTm_D2)) {
                scrnMsg.setMessageInfo(NSBM0134E, new String[] {END_DT, END_TIME });
                scrnMsg.xxDtTm_D2.setErrorInfo(1, NSBM0134E, new String[] {END_DT, END_TIME });
            }
        }

        // end time check (status:closed only)
        if (SVC_TASK_STS.CLOSED.equals(scrnMsg.svcSupplTaskStsCd_D.getValue())) {
            if (!hasValue(scrnMsg.svcSupplToDt_D)) {
                scrnMsg.setMessageInfo(NSBM0130E, new String[] {NAME_END, CLOSED });
                scrnMsg.svcSupplToDt_D.setErrorInfo(1, NSBM0130E, new String[] {NAME_END, CLOSED });
            }
            if (!hasValue(scrnMsg.xxDtTm_D2)) {
                scrnMsg.setMessageInfo(NSBM0130E, new String[] {NAME_END, CLOSED });
                scrnMsg.xxDtTm_D2.setErrorInfo(1, NSBM0130E, new String[] {NAME_END, CLOSED });
            }
        }

        // format check(Start Time,End Time,Travel Time,Duration)
        if (!dayTimeFormatCheck(scrnMsg.xxDtTm_D1.getValue())) {
            scrnMsg.setMessageInfo(NSBM0004E, new String[] {DAY_TIME_FORMAT });
            scrnMsg.xxDtTm_D1.setErrorInfo(1, NSBM0004E, new String[] {DAY_TIME_FORMAT });
        }
        if (!dayTimeFormatCheck(scrnMsg.xxDtTm_D2.getValue())) {
            scrnMsg.setMessageInfo(NSBM0004E, new String[] {DAY_TIME_FORMAT });
            scrnMsg.xxDtTm_D2.setErrorInfo(1, NSBM0004E, new String[] {DAY_TIME_FORMAT });
        }
        if (!timeFormatCheck(scrnMsg.xxDtTm_D3.getValue())) {
            scrnMsg.setMessageInfo(NSBM0004E, new String[] {TIME_FORMAT });
            scrnMsg.xxDtTm_D3.setErrorInfo(1, NSBM0004E, new String[] {TIME_FORMAT });
        }
        if (!timeFormatCheck(scrnMsg.xxDtTm_D5.getValue())) {
            scrnMsg.setMessageInfo(NSBM0004E, new String[] {TIME_FORMAT });
            scrnMsg.xxDtTm_D5.setErrorInfo(1, NSBM0004E, new String[] {TIME_FORMAT });
        }

        if (hasValue(scrnMsg.svcSupplFromDt_D) && hasValue(scrnMsg.xxDtTm_D1) && hasValue(scrnMsg.svcSupplToDt_D) && hasValue(scrnMsg.xxDtTm_D2)) {
            // Start Time(Date + Time) > End Time(Date + Time) =>
            // Error
            long diffTime = getSupplTime(scrnMsg);
            if (diffTime < 0) {
                scrnMsg.setMessageInfo(NSBM0024E);
                scrnMsg.svcSupplFromDt_D.setErrorInfo(1, NSBM0024E);
                scrnMsg.xxDtTm_D1.setErrorInfo(1, NSBM0024E);
                scrnMsg.svcSupplToDt_D.setErrorInfo(1, NSBM0024E);
                scrnMsg.xxDtTm_D2.setErrorInfo(1, NSBM0024E);
            }

            // limit 99:59
            if (diffTime >= HOUR * LIMIT_100) {
                scrnMsg.setMessageInfo(NSBM0131E);
                scrnMsg.svcSupplFromDt_D.setErrorInfo(1, NSBM0131E);
                scrnMsg.xxDtTm_D1.setErrorInfo(1, NSBM0131E);
                scrnMsg.svcSupplToDt_D.setErrorInfo(1, NSBM0131E);
                scrnMsg.xxDtTm_D2.setErrorInfo(1, NSBM0131E);
            }

        }
    }

    /**
     * Time Formata Check
     * @param checkTarget String
     * @return
     */
    private static boolean dayTimeFormatCheck(String checkTarget) {
        if (!hasValue(checkTarget)) {
            return true;
        }
        if (checkTarget.length() != LENGTH_5) {
            return false;
        }
        if (checkTarget.matches(CHECK_DAY_TIME_FORMAT)) {
            return true;
        }
        return false;
    }

    /**
     * Time Formata Check
     * @param checkTarget String
     * @return
     */
    private static boolean timeFormatCheck(String checkTarget) {
        if (!hasValue(checkTarget)) {
            return true;
        }
        if (checkTarget.length() != LENGTH_5) {
            return false;
        }
        if (checkTarget.matches(CHECK_TIME_FORMAT)) {
            return true;
        }
        return false;
    }

    /**
     * <pre>
     * change time data
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0450BMsg
     */
    public static final void changeTimeData(EZDCommonHandler handler, NSBL0450BMsg scrnMsg) {

        // End Time(Date + time) - Start Time(Date + Time)
        if (hasValue(scrnMsg.svcSupplFromDt_D) && hasValue(scrnMsg.xxDtTm_D1) && hasValue(scrnMsg.svcSupplToDt_D) && hasValue(scrnMsg.xxDtTm_D2)) {
            long diffTime = getSupplTime(scrnMsg);
            long diffHourInt = diffTime / HOUR;
            String diffHour = ZYPCommonFunc.leftPad(String.valueOf(diffHourInt), 2, "0");
            String diffMinute = ZYPCommonFunc.leftPad(String.valueOf((diffTime - diffHourInt * HOUR) / MINUTES), 2, "0");
            String supplTime = ZYPCommonFunc.concatString(diffHour, ":", diffMinute);
            setValue(scrnMsg.xxDtTm_D4, supplTime);
        } else {
            scrnMsg.xxDtTm_D4.clear();
        }
    }

    /**
     * getSupplTime
     * @param scrnMsg NSBL0450BMsg
     * @return diffMinutes
     */
    private static long getSupplTime(NSBL0450BMsg scrnMsg) {
        String startTime = null;
        String endTime = null;
        startTime = ZYPCommonFunc.concatString(scrnMsg.svcSupplFromDt_D.getValue(), ZYPCommonFunc.subByteString(scrnMsg.xxDtTm_D1.getValue(), SUB_0, SUB_2), ZYPCommonFunc.subByteString(scrnMsg.xxDtTm_D1.getValue(), SUB_3, SUB_5)).concat(
                "00");
        endTime = ZYPCommonFunc.concatString(scrnMsg.svcSupplToDt_D.getValue(), ZYPCommonFunc.subByteString(scrnMsg.xxDtTm_D2.getValue(), SUB_0, SUB_2), ZYPCommonFunc.subByteString(scrnMsg.xxDtTm_D2.getValue(), SUB_3, SUB_5)).concat("00");

        SimpleDateFormat formatter = new SimpleDateFormat(TS_FORMAT);

        java.util.Date startDay = null;
        java.util.Date endDay = null;
        long lngStartTime = 0;
        long lngEndTime = 0;
        try {
            startDay = formatter.parse(startTime);
            endDay = formatter.parse(endTime);
        } catch (ParseException e) {
            return 0;
        }
        lngStartTime = startDay.getTime();
        lngEndTime = endDay.getTime();
        return lngEndTime - lngStartTime;
    }

    /**
     * <pre>
     * showDetail
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0450BMsg
     * @param tragetNo int
     */
    public static final void showDetail(EZDCommonHandler handler, NSBL0450BMsg scrnMsg, int tragetNo) {

        setValue(scrnMsg.svcSupplTaskNum_D, scrnMsg.A.no(tragetNo).svcSupplTaskNum_A);
        setValue(scrnMsg.svcSupplTaskTpDescTxt_D, scrnMsg.A.no(tragetNo).svcSupplTaskTpDescTxt_A);
        setValue(scrnMsg.techPsnNm_D, scrnMsg.A.no(tragetNo).techPsnNm_A);
        setValue(scrnMsg.mgrNm_D, scrnMsg.A.no(tragetNo).mgrNm_A);
        setValue(scrnMsg.xxCmntTxt_D, scrnMsg.A.no(tragetNo).xxCmntTxt_A);
        setValue(scrnMsg.svcTaskNum_D, scrnMsg.A.no(tragetNo).svcTaskNum_A);
        setValue(scrnMsg.svcSupplFromDt_D, scrnMsg.A.no(tragetNo).svcSupplFromDt_A);
        setValue(scrnMsg.xxDtTm_D1, scrnMsg.A.no(tragetNo).xxDtTm_A1);
        setValue(scrnMsg.svcSupplToDt_D, scrnMsg.A.no(tragetNo).svcSupplToDt_A);
        setValue(scrnMsg.xxDtTm_D2, scrnMsg.A.no(tragetNo).xxDtTm_A2);
        setValue(scrnMsg.xxDtTm_D4, scrnMsg.A.no(tragetNo).xxDtTm_A4);
        setValue(scrnMsg.xxDtTm_D3, scrnMsg.A.no(tragetNo).xxDtTm_A3);
        setValue(scrnMsg.xxDtTm_D5, scrnMsg.A.no(tragetNo).xxDtTm_A5);
        setValue(scrnMsg.xxAllPsnNm_D1, scrnMsg.A.no(tragetNo).xxAllPsnNm_A1);
        setValue(scrnMsg.cratDt_D, scrnMsg.A.no(tragetNo).cratDt_A);
        setValue(scrnMsg.xxDtTm_D6, scrnMsg.A.no(tragetNo).xxDtTm_A6);
        setValue(scrnMsg.xxAllPsnNm_D2, scrnMsg.A.no(tragetNo).xxAllPsnNm_A2);
        setValue(scrnMsg.updDt_D, scrnMsg.A.no(tragetNo).updDt_A);
        setValue(scrnMsg.xxDtTm_D7, scrnMsg.A.no(tragetNo).xxDtTm_A7);
        setValue(scrnMsg.svcSupplTaskStsCd_D, scrnMsg.A.no(tragetNo).svcSupplTaskStsCd_A);
        setValue(scrnMsg.xxRowNum_D, BigDecimal.valueOf(tragetNo));
    }

    /**
     * <pre>
     * reflect to line
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0450BMsg
     */
    public static final void reflectToLine(EZDCommonHandler handler, NSBL0450BMsg scrnMsg) {

        int tragetNo = scrnMsg.xxRowNum_D.getValueInt();

        if (isChange(scrnMsg, tragetNo)) {
            setValue(scrnMsg.A.no(tragetNo).svcSupplFromDt_A, scrnMsg.svcSupplFromDt_D);
            setValue(scrnMsg.A.no(tragetNo).xxDtTm_A1, scrnMsg.xxDtTm_D1);
            setValue(scrnMsg.A.no(tragetNo).svcSupplToDt_A, scrnMsg.svcSupplToDt_D);
            setValue(scrnMsg.A.no(tragetNo).xxDtTm_A2, scrnMsg.xxDtTm_D2);
            setValue(scrnMsg.A.no(tragetNo).xxDtTm_A4, scrnMsg.xxDtTm_D4);
            setValue(scrnMsg.A.no(tragetNo).xxDtTm_A3, scrnMsg.xxDtTm_D3);
            setValue(scrnMsg.A.no(tragetNo).xxDtTm_A5, scrnMsg.xxDtTm_D5);
            String startTs = scrnMsg.svcSupplFromDt_D.getValue().concat(scrnMsg.xxDtTm_D1.getValue());
            setValue(scrnMsg.A.no(tragetNo).xxDtTm_ST, startTs);
            String endTs = null;
            if (hasValue(scrnMsg.svcSupplToDt_D) && hasValue(scrnMsg.xxDtTm_D2)) {
                endTs = scrnMsg.svcSupplToDt_D.getValue().concat(scrnMsg.xxDtTm_D2.getValue());
            }
            setValue(scrnMsg.A.no(tragetNo).xxDtTm_ST, endTs);
            setValue(scrnMsg.A.no(tragetNo).submtFlg_A, ZYPConstant.FLG_ON_Y);
        }
    }

    private static boolean isChange(NSBL0450BMsg scrnMsg, int tragetNo) {

        if (isChangeDate(scrnMsg.A.no(tragetNo).svcSupplFromDt_A, scrnMsg.svcSupplFromDt_D)) {
            return true;
        }
        if (isChangeString(scrnMsg.A.no(tragetNo).xxDtTm_A1, scrnMsg.xxDtTm_D1)) {
            return true;
        }
        if (isChangeDate(scrnMsg.A.no(tragetNo).svcSupplToDt_A, scrnMsg.svcSupplToDt_D)) {
            return true;
        }
        if (isChangeString(scrnMsg.A.no(tragetNo).xxDtTm_A2, scrnMsg.xxDtTm_D2)) {
            return true;
        }
        if (isChangeString(scrnMsg.A.no(tragetNo).xxDtTm_A3, scrnMsg.xxDtTm_D3)) {
            return true;
        }
        if (isChangeString(scrnMsg.A.no(tragetNo).xxDtTm_A4, scrnMsg.xxDtTm_D4)) {
            return true;
        }
        if (isChangeString(scrnMsg.A.no(tragetNo).xxDtTm_A5, scrnMsg.xxDtTm_D5)) {
            return true;
        }
        return false;
    }

    private static boolean isChangeString(EZDBStringItem befObj, EZDBStringItem changeObj) {
        if (!hasValue(befObj) && !hasValue(changeObj)) {
            return false;
        }
        if (!hasValue(befObj) && hasValue(changeObj)) {
            return true;
        }

        if (!hasValue(befObj) && !hasValue(changeObj)) {
            return true;
        }

        if (!befObj.getValue().equals(changeObj.getValue())) {
            return true;
        }
        return false;
    }

    private static boolean isChangeDate(EZDBDateItem befObj, EZDBDateItem changeObj) {
        if (!hasValue(befObj) && !hasValue(changeObj)) {
            return false;
        }
        if (!hasValue(befObj) && hasValue(changeObj)) {
            return true;
        }

        if (!hasValue(befObj) && !hasValue(changeObj)) {
            return true;
        }

        if (!befObj.getValue().equals(changeObj.getValue())) {
            return true;
        }
        return false;
    }

    // add start 2017/03/01 CSA Defect#17608
    /**
     * initialCheckBox
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0450BMsg
     */
    public static final void initialCheckBox(EZDCommonHandler handler, NSBL0450BMsg scrnMsg) {
        setValue(scrnMsg.xxChkBox_S, ZYPConstant.CHKBOX_ON_Y);
        scrnMsg.xxChkBox_S.setInputProtected(false);
    }

    /**
     * protectCheckBox
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0450BMsg
     */
    public static final void protectCheckBox(EZDCommonHandler handler, NSBL0450BMsg scrnMsg) {
        if (scrnMsg.A.getValidCount() > 0) {
            scrnMsg.xxChkBox_S.setInputProtected(true);
        }
    }
    // add end 2017/03/01 CSA Defect#17608
}
