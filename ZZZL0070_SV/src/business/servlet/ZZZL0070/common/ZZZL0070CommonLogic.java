package business.servlet.ZZZL0070.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.ZZZL0070.ZZZL0070BMsg;
import business.servlet.ZZZL0070.constant.ZZZL0070Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

public class ZZZL0070CommonLogic implements ZZZL0070Constant {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    
    /**
     * Initial Common Button setting method.
     * @param handler EZDCommonHandler
     */
    public static void initCommonButton(EZDCommonHandler handler) {

        // Common Buttons.
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
     * Setting data to all pulldowns for initializing.
     * @param handler EZDCommonHandler
     */
    public static void initDate (ZZZL0070BMsg scrnMsg) {
        
        // Set Default Date for initializing.
        String today = new SimpleDateFormat("yyyyMMdd").format(new Date());
        Calendar calendar = Calendar.getInstance();
        int lastDay = calendar.getActualMaximum(Calendar.DATE);
        
        String fromDay = today.substring(0, 6) + "01"; 
        String endDay = today.substring(0, 6) + String.valueOf(lastDay);
        scrnMsg.xxFromDt.setValue(fromDay);
        scrnMsg.xxToDt.setValue(endDay);
          
    }
    
    /**
     * Method name: checkItem
     * <dd>The method explanation: Add check item.
     * <dd>Remarks:
     * @param scrnMsg Screen Component Interface Message
     */
    public static void checkItem(ZZZL0070BMsg scrnMsg) {

        // add check items.
        scrnMsg.addCheckItem(scrnMsg.glblCmpyCd);
        scrnMsg.addCheckItem(scrnMsg.xxFromDt);
        scrnMsg.addCheckItem(scrnMsg.xxToDt);
    }
    
    public static void checkMaxId(ZZZL0070BMsg scrnMsg) {
        if (scrnMsg.A.getValidCount() >= scrnMsg.A.length()) {
            String jobId = scrnMsg.batProcJobId.getNameForMessage();
            scrnMsg.batProcJobId.setErrorInfo(1, "ZZZM9054E", new String[]{jobId, String.valueOf(scrnMsg.A.length())});
        }
    }
    public static synchronized void correlativeTimeCheck(ZZZL0070BMsg scrnMsg) {
                
        String frDateNm = scrnMsg.xxFromDt.getNameForMessage();
        String toDateNm = scrnMsg.xxToDt.getNameForMessage();
        
        if (!scrnMsg.xxFromDt.isClear() && !scrnMsg.xxToDt.isClear()) {
            int cmpDate = ZYPDateUtil.compare(scrnMsg.xxFromDt.getValue(), scrnMsg.xxToDt.getValue());
            
            // Bigger the 'From Date' is error
            if (cmpDate > 0) {
                scrnMsg.xxFromDt.setErrorInfo(1, "ZZZM9010E", new String[]{frDateNm, toDateNm});
                scrnMsg.xxToDt.setErrorInfo(1, "ZZZM9010E", new String[]{frDateNm, toDateNm});
                return;
            }
            
            // Comparing From date and End date whether over 31days or not.
            long diff = 0;

            Date startDate = null;
            Date endDate = null;
            
            try {
                startDate = dateFormat.parse(scrnMsg.xxFromDt.getValue());
                endDate = dateFormat.parse(scrnMsg.xxToDt.getValue());
            } catch (ParseException e) {
            }
            long stTm = startDate.getTime();
            long edTm = endDate.getTime();
            long day31 = 1000L * 60L * 60L * 24L * 31L;
            long day2 = 1000L * 60L * 60L * 24L * 1L;

            diff = edTm - stTm;
            if (diff > day31) {
                scrnMsg.xxFromDt.setErrorInfo(1, "ZZZM9052E", new String[]{"Operation period", "31days"});
                scrnMsg.xxToDt.setErrorInfo(1, "ZZZM9052E", new String[]{"Operation period", "31days"});
            } else if (diff < day2) {
                scrnMsg.xxToDt.setErrorInfo(1, "ZZZM9053E", new String[]{"Operation period", "2days"});
            }
        }
    }
    
    public static void changeTableColorByRow(ZZZL0070BMsg bMsg) {

        // set alternate rows back-ground color
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, bMsg);
        tblColor.setAlternateRowsBG("A", bMsg.A);
    }
}
