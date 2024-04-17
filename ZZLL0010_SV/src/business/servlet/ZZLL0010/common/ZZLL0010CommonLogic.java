package business.servlet.ZZLL0010.common;

import parts.servletcommon.EZDCommonHandler;
import business.blap.ZZLL0010.ZZLL0010CMsg;
import business.servlet.ZZLL0010.constant.ZZLL0010Constant;
import business.servlet.ZZLL0010.ZZLL0010BMsg;

public class ZZLL0010CommonLogic implements ZZLL0010Constant {

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
     * Setting data to pulldown in Scrn00.
     * @param handler EZDCommonHandler
     */
    public static void initPullDown00(ZZLL0010BMsg scrnMsg) {

        scrnMsg.mntTrxTp_L1.no(0).setValue(TRA_TYPE_CREATE);
        scrnMsg.mntTrxTp_L1.no(1).setValue(TRA_TYPE_UPDATE);
        scrnMsg.mntTrxTp_L1.no(2).setValue(TRA_TYPE_L_DELETE);
        scrnMsg.mntTrxTp_L1.no(3).setValue(TRA_TYPE_DELETE);
        
        scrnMsg.mntTrxTp_L2.no(0).setValue(TRA_TYPE_CREATE);
        scrnMsg.mntTrxTp_L2.no(1).setValue(TRA_TYPE_UPDATE);
        scrnMsg.mntTrxTp_L2.no(2).setValue(TRA_TYPE_L_DELETE);
        scrnMsg.mntTrxTp_L2.no(3).setValue(TRA_TYPE_DELETE);
        
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
    public static void convertTimeToDisplay(ZZLL0010BMsg scrnMsg, ZZLL0010CMsg bizMsg) {

        if (scrnMsg.A.getValidCount() < 1) {
            return;
        }
        
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // Log Date
            String logYear = bizMsg.A.no(i).ezInTime_TR.getValue().substring(0, 4);
            String logMonth = bizMsg.A.no(i).ezInTime_TR.getValue().substring(4, 6);
            String logDay = bizMsg.A.no(i).ezInTime_TR.getValue().substring(6, 8);
            String logDate = logYear + logMonth + logDay;
            scrnMsg.A.no(i).xxFromDt_TR.setValue(logDate);
            
            // Log Time
            String logHH = bizMsg.A.no(i).ezInTime_TR.getValue().substring(8, 10);
            String logMM = bizMsg.A.no(i).ezInTime_TR.getValue().substring(10, 12);
            String logSS = bizMsg.A.no(i).ezInTime_TR.getValue().substring(12, 14);
            String logTime = logHH + ":" + logMM + ":" + logSS;
            scrnMsg.A.no(i).xxHrsMnScd_TR.setValue(logTime);

        }
    }
    

}
