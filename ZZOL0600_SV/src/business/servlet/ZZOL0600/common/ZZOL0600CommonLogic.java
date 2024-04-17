package business.servlet.ZZOL0600.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import parts.servletcommon.EZDCommonHandler;
import business.blap.ZZOL0600.ZZOL0600CMsg;
import business.blap.ZZOL0600.ZZOL0600Query;
import business.servlet.ZZOL0600.ZZOL0600BMsg;
import business.servlet.ZZOL0600.constant.ZZOL0600Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

public class ZZOL0600CommonLogic implements ZZOL0600Constant {

    /**
     * Initial Common Button setting method.
     * @param handler EZDCommonHandler
     */
    public static void initCommonButton(EZDCommonHandler handler) {

        // Common Buttons.
        handler.setButtonProperties(CMN_BTN1[0], CMN_BTN1[1], CMN_BTN1[2], 0, null);
        handler.setButtonProperties(CMN_BTN2[0], CMN_BTN2[1], CMN_BTN2[2], 0, null);
        handler.setButtonProperties(CMN_BTN3[0], CMN_BTN3[1], CMN_BTN3[2], 0, null);
        handler.setButtonProperties(CMN_BTN4[0], CMN_BTN4[1], CMN_BTN4[2], 0, null);
        handler.setButtonProperties(CMN_BTN5[0], CMN_BTN5[1], CMN_BTN5[2], 0, null);
        handler.setButtonProperties(CMN_BTN6[0], CMN_BTN6[1], CMN_BTN6[2], 0, null);
        handler.setButtonProperties(CMN_BTN7[0], CMN_BTN7[1], CMN_BTN7[2], 0, null);
        handler.setButtonProperties(CMN_BTN8[0], CMN_BTN8[1], CMN_BTN8[2], 1, null);
        handler.setButtonProperties(CMN_BTN9[0], CMN_BTN9[1], CMN_BTN9[2], 0, null);
        handler.setButtonProperties(CMN_BTN10[0], CMN_BTN10[1], CMN_BTN10[2], 1, null);
    }

    /**
     * Inits the common button for scrn01.
     * @param handler the handler
     */
    public static void initCommonButtonForScrn01(EZDCommonHandler handler) {

        // Common Buttons.
        handler.setButtonProperties(CMN_BTN1[0], CMN_BTN1[1], CMN_BTN1[2], 0, null);
        handler.setButtonProperties(CMN_BTN2[0], CMN_BTN2[1], CMN_BTN2[2], 0, null);
        handler.setButtonProperties(CMN_BTN3[0], CMN_BTN3[1], CMN_BTN3[2], 0, null);
        handler.setButtonProperties(CMN_BTN4[0], CMN_BTN4[1], CMN_BTN4[2], 0, null);
        handler.setButtonProperties(CMN_BTN5[0], CMN_BTN5[1], CMN_BTN5[2], 0, null);
        handler.setButtonProperties(CMN_BTN6[0], CMN_BTN6[1], CMN_BTN6[2], 0, null);
        handler.setButtonProperties(CMN_BTN7[0], CMN_BTN7[1], CMN_BTN7[2], 0, null);
        handler.setButtonProperties(CMN_BTN8[0], CMN_BTN8[1], CMN_BTN8[2], 0, null);
        handler.setButtonProperties(CMN_BTN9[0], CMN_BTN9[1], CMN_BTN9[2], 0, null);
        handler.setButtonProperties(CMN_BTN10[0], CMN_BTN10[1], CMN_BTN10[2], 1, null);
    }

    /**
     * Setting data to pulldown in Scrn00.
     * @param handler EZDCommonHandler
     */
    public static void initPullDown00(ZZOL0600BMsg scrnMsg) {

        scrnMsg.xxChkBox.setValue(BIZAPL_CHKBOX_ON_Y);
        scrnMsg.xxAlmsOnlBatFlgTxt.no(0).setValue(ALL_FLG);
        scrnMsg.xxAlmsOnlBatFlgTxt.no(1).setValue(ONLINE_FLG);
        scrnMsg.xxAlmsOnlBatFlgTxt.no(2).setValue(BATCH_FLG);

        scrnMsg.almsOnlBatFlg_F1.no(0).setValue(ALL_CHAR);
        scrnMsg.almsOnlBatFlg_F1.no(1).setValue(ONLINE_CHAR);
        scrnMsg.almsOnlBatFlg_F1.no(2).setValue(BATCH_CHAR);

        SimpleDateFormat dateFmt = new SimpleDateFormat(SH_DATE_FMT, Locale.US);
        String currentDate = dateFmt.format(new Date());

        if (currentDate != null)
            scrnMsg.xxFromDt.setValue(currentDate);
        scrnMsg.xxHrs_FS.setValue("00");
        scrnMsg.xxMn_FS.setValue("00");

        scrnMsg.xxToDt.setValue(currentDate);
        scrnMsg.xxHrs_TS.setValue("23");
        scrnMsg.xxMn_TS.setValue("59");

        // Start Hours
        for (int i = 0; i < Hour.length; i++) {
            scrnMsg.xxHrs_FC.no(i).setValue(Hour[i]);
            scrnMsg.xxHrs_FD.no(i).setValue(Hour[i]);
        }
        // Start Min
        for (int i = 0; i < Min.length; i++) {
            scrnMsg.xxMn_FC.no(i).setValue(Min[i]);
            scrnMsg.xxMn_FD.no(i).setValue(Min[i]);
        }
        // End Hour
        for (int i = 0; i < Hour.length; i++) {
            scrnMsg.xxHrs_TC.no(i).setValue(Hour[i]);
            scrnMsg.xxHrs_TD.no(i).setValue(Hour[i]);
        }
        // End Min
        for (int i = 0; i < Min.length; i++) {
            scrnMsg.xxMn_TC.no(i).setValue(Min[i]);
            scrnMsg.xxMn_TD.no(i).setValue(Min[i]);
        }

    }

    /**
     * Converting the time data to display on Scrn00 .
     * @param handler EZDCommonHandler
     */
    public static void convertTimeToDisplay(ZZOL0600BMsg scrnMsg, ZZOL0600CMsg bizMsg) {

        if (scrnMsg.A.getValidCount() < 1) {
            return;
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // Log Date
            String logYear = bizMsg.A.no(i).ezInTime_TR.getValue().substring(0, 4);
            String logMonth = bizMsg.A.no(i).ezInTime_TR.getValue().substring(4, 6);
            String logDay = bizMsg.A.no(i).ezInTime_TR.getValue().substring(6, 8);
            String logDate = logYear + logMonth + logDay;

            // Log Time
            String logHH = bizMsg.A.no(i).ezInTime_TR.getValue().substring(8, 10);
            String logMM = bizMsg.A.no(i).ezInTime_TR.getValue().substring(10, 12);
            String logSS = bizMsg.A.no(i).ezInTime_TR.getValue().substring(12, 14);
            String logSss = bizMsg.A.no(i).ezInTime_TR.getValue().substring(14, bizMsg.A.no(i).ezInTime_TR.getValue().length());
            String logTime = logHH + ":" + logMM + ":" + logSS + ":" + logSss;

            String convDate = ZYPDateUtil.convertFormat(logDate, ZYPDateUtil.TYPE_YYYYMMDD, ZYPDateUtil.TYPE_MMDDYYYY, '/');
            String logDateTime = convDate + " " + logTime;
            scrnMsg.A.no(i).xxDtTm_FR.setValue(logDateTime);
        }
    }

    /**
     * Gets the job log path.
     * @param scrnMsg the scrn msg
     * @return the job log path
     */
    public static String getJobLogPath(ZZOL0600BMsg scrnMsg) {
        String jobLogPath = "";
        // Get Batch Process Job Log Path from BAT_PROC_LOG table.
        HashMap<String, String> param = new HashMap<String, String>();
        param.put(glbl_cmpy_cd, scrnMsg.glblCmpyCd_B.getValue());

        String batch_Proc_Id = Integer.valueOf(scrnMsg.almsBatProcId_B.getValueInt()).toString();
        S21InfoLogOutput.println("BATCH PROCESS ID :" + batch_Proc_Id);

        param.put(bat_proc_log, batch_Proc_Id);
        S21SsmEZDResult ssmResult = ZZOL0600Query.getInstance().getJobLogPathName(param);

        if (ssmResult.isCodeNormal()) {
            // Normal
            List resultList = (List) ssmResult.getResultObject();
            for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
                Map resultMap = (Map) iterator.next();
                jobLogPath = nvl((String) resultMap.get(bat_proc_joblog_path));
            }
        }
        return jobLogPath;
    }

    /**
     * Null value check.
     * @param str the str
     * @return the string
     */
    private static String nvl(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }

}
