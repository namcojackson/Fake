package business.servlet.ZZOL0010.common;

import business.blap.ZZOL0010.ZZOL0010CMsg;
import business.servlet.ZZOL0010.ZZOL0010BMsg;
import business.servlet.ZZOL0010.constant.ZZOL0010Constant;
import parts.servletcommon.EZDCommonHandler;

public class ZZOL0010CommonLogic implements ZZOL0010Constant {

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
     * Button properties for ZZOL0010Scrn01.
     * @param handler EZDCommonHandler
     */
    public static void changeCommonButton01(EZDCommonHandler handler){
        ZZOL0010CommonLogic.initCommonButton(handler);
        handler.setButtonProperties(CMN_BTN2[0], CMN_BTN2[1], CMN_BTN2[2], 1, null);
        handler.setButtonProperties(CMN_BTN9[0], CMN_BTN9[1], CMN_BTN9[2], 1, null);
        handler.setButtonProperties(CMN_BTN8[0], null, null, 0, null);
        handler.setButtonConfirmMsg(CMN_BTN10[1], "ZZM8102I", new String[] {CMN_BTN10[2]}, 0);
    }

    /**
     * 'Delete' Common Button Setting Method.
     * @param handler EZDCommonHandler
     */
    public static void deleteCommonButton(EZDCommonHandler handler) {

        handler.setButtonProperties(CMN_BTN7[0], CMN_BTN7[1], CMN_BTN7[2], 1, null);
        handler.setButtonConfirmMsg(CMN_BTN7[1], "ZZM8102I", new String[] {CMN_BTN7[2]}, 0);
    }
    
    /**
     * 'Delete' Common Button Setting Method.
     * @param handler EZDCommonHandler
     */
    public static void unDisdeleteCommonButton(EZDCommonHandler handler) {

        handler.setButtonProperties(CMN_BTN7[0], CMN_BTN7[1], CMN_BTN7[2], 0, null);
    }

    /**
     * Setting data to pulldown in Scrn00.
     * @param handler EZDCommonHandler
     */
    public static void initPullDown00(ZZOL0010BMsg scrnMsg) {

        scrnMsg.ezOnlStopFlag_CD.no(0).setValue("1");
        scrnMsg.ezOnlStopFlag_CD.no(1).setValue("0");
        scrnMsg.xxFlgNm_OB.no(0).setValue(ONLINE_STOP_ON);
        scrnMsg.xxFlgNm_OB.no(1).setValue(ONLINE_STOP_OFF);

        scrnMsg.ezAcctInfoMode_CD.no(0).setValue("1");
        scrnMsg.ezAcctInfoMode_CD.no(1).setValue("0");
        scrnMsg.xxFlgNm_BI.no(0).setValue(ACCOUNT_MODE_RUN);
        scrnMsg.xxFlgNm_BI.no(1).setValue(ACCOUNT_MODE_STOP);

        scrnMsg.ezDebugLevel_CD.no(0).setValue("1");
        scrnMsg.ezDebugLevel_CD.no(1).setValue("0");
        scrnMsg.xxFlgNm_DL.no(0).setValue(DEBUG_MODE_RUN);
        scrnMsg.xxFlgNm_DL.no(1).setValue(DEBUG_MODE_STOP);
    }

    /**
     * Setting data to the pulldown in Scrn01.
     * @param handler EZDCommonHandler
     */
    public static void initPullDown01(ZZOL0010BMsg scrnMsg) {

        // Start Hours
        for (int i = 0; i < 25; i++) {
            scrnMsg.xxHrs_SC.no(i).setValue(Hour[i]);
            scrnMsg.xxHrs_SD.no(i).setValue(Hour[i]);
        }

        // Start Min
        for (int i = 0; i < Min.length; i++) {
            scrnMsg.xxMn_SC.no(i).setValue(Min[i]);
            scrnMsg.xxMn_SD.no(i).setValue(Min[i]);
        }

        // End Hour
        for (int i = 0; i < Hour.length; i++) {
            scrnMsg.xxHrs_EC.no(i).setValue(Hour[i]);
            scrnMsg.xxHrs_ED.no(i).setValue(Hour[i]);
        }

        // End Time
        for (int i = 0; i < Min.length; i++) {
            scrnMsg.xxMn_EC.no(i).setValue(Min[i]);
            scrnMsg.xxMn_ED.no(i).setValue(Min[i]);
        }
    }

    
    /**
     * Setting default data for Scrn01.
     * @param handler EZDCommonHandler
     */
    public static void initValue01(ZZOL0010BMsg scrnMsg) {

        scrnMsg.ezOnlStopFlag_01.setValue(ONLINE_STOP_OFF_CD);
        scrnMsg.ezAcctInfoMode_01.setValue(ACCOUNT_MODE_RUN_CD);
        scrnMsg.ezDebugLevel_01.setValue(DEBUG_MODE_STOP_CD);
        scrnMsg.xxHrs_SV.setValue("00");
        scrnMsg.xxMn_SV.setValue("00");
        scrnMsg.xxHrs_EV.setValue("24");
        scrnMsg.xxMn_EV.setValue("00");
    }
    
    /**
     * Converting the flag data to display on Scrn00 .
     * @param handler EZDCommonHandler
     */
    public static void convertFlagDisplay(ZZOL0010BMsg scrnMsg, ZZOL0010CMsg bizMsg) {

        if (scrnMsg.A.getValidCount() < 1) {
            return;
        }
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            // Online Stop Flag
            if (bizMsg.A.no(i).ezOnlStopFlag_A1.getValue().equals("0")) {
                scrnMsg.A.no(i).xxEzOnlStopFlagDisp_A1.setValue(ONLINE_STOP_OFF);
            } else {
                scrnMsg.A.no(i).xxEzOnlStopFlagDisp_A1.setValue(ONLINE_STOP_ON);
            }

            // Account Info Mode
            if (bizMsg.A.no(i).ezAcctInfoMode_A1.getValue().equals("0")) {
                scrnMsg.A.no(i).xxEzAcctInfoModeDisp_A1.setValue(ACCOUNT_MODE_STOP);
            } else {
                scrnMsg.A.no(i).xxEzAcctInfoModeDisp_A1.setValue(ACCOUNT_MODE_RUN);
            }

            // Debug level
            if (bizMsg.A.no(i).ezDebugLevel_A1.getValue().equals("0")) {
                scrnMsg.A.no(i).xxEzDebugLevelDisp_A1.setValue(DEBUG_MODE_STOP);
            } else {
                scrnMsg.A.no(i).xxEzDebugLevelDisp_A1.setValue(DEBUG_MODE_RUN);
            }
        }

    }

    /**
     * Converting the time data to display on Scrn00 .
     * @param handler EZDCommonHandler
     */
    public static void convertTimeDisplay(ZZOL0010BMsg scrnMsg) {

        if (scrnMsg.A.getValidCount() < 1) {
            return;
        }
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            // Start Time
            if (scrnMsg.A.no(i).xxHrsMnScd_AS.getValue().length() < 6) {
                continue;
            }
            String stHH = scrnMsg.A.no(i).xxHrsMnScd_AS.getValue().substring(0, 2);
            String stMM = scrnMsg.A.no(i).xxHrsMnScd_AS.getValue().substring(2, 4);
            String stSS = scrnMsg.A.no(i).xxHrsMnScd_AS.getValue().substring(4, 6);
            String startTime = stHH + ":" + stMM + ":" + stSS;
            scrnMsg.A.no(i).xxHrsMnScd_AS.setValue(startTime);

            // End Time
            if (scrnMsg.A.no(i).xxHrsMnScd_AE.getValue().length() < 6) {
                break;
            }
            String enHH = scrnMsg.A.no(i).xxHrsMnScd_AE.getValue().substring(0, 2);
            String enMM = scrnMsg.A.no(i).xxHrsMnScd_AE.getValue().substring(2, 4);
            String enSS = scrnMsg.A.no(i).xxHrsMnScd_AE.getValue().substring(4, 6);
            String endTime = enHH + ":" + enMM + ":" + enSS;
            scrnMsg.A.no(i).xxHrsMnScd_AE.setValue(endTime);

        }

    }
    
    /**
     * Set bkup value for Reset event
     * @param handler EZDCommonHandler
     */
    public static void setResetValue(ZZOL0010BMsg scrnMsg) {

        // Store the value for when Reset button is pressed
        scrnMsg.ezBusinessID_BK.setValue(scrnMsg.ezBusinessID_01.getValue());
        scrnMsg.ezCompanyCode_BK.setValue(scrnMsg.ezCompanyCode_01.getValue());
        scrnMsg.ezOnlStopFlag_BK.setValue(scrnMsg.ezOnlStopFlag_01.getValue());
        scrnMsg.ezAcctInfoMode_BK.setValue(scrnMsg.ezAcctInfoMode_01.getValue());
        scrnMsg.ezDebugLevel_BK.setValue(scrnMsg.ezDebugLevel_01.getValue());
        scrnMsg.xxHrs_SB.setValue(scrnMsg.xxHrs_SV.getValue());
        scrnMsg.xxMn_SB.setValue(scrnMsg.xxMn_SV.getValue());
        scrnMsg.xxHrs_EB.setValue(scrnMsg.xxHrs_EV.getValue());
        scrnMsg.xxMn_EB.setValue(scrnMsg.xxMn_EV.getValue());
    }
    
    /**
     * get bkup value for Reset event
     * @param handler EZDCommonHandler
     */
    public static void getResetValue(ZZOL0010BMsg scrnMsg) {

        scrnMsg.ezBusinessID_01.setValue(scrnMsg.ezBusinessID_BK.getValue());
        scrnMsg.ezCompanyCode_01.setValue(scrnMsg.ezCompanyCode_BK.getValue());
        scrnMsg.ezOnlStopFlag_01.setValue(scrnMsg.ezOnlStopFlag_BK.getValue());
        scrnMsg.ezAcctInfoMode_01.setValue(scrnMsg.ezAcctInfoMode_BK.getValue());
        scrnMsg.ezDebugLevel_01.setValue(scrnMsg.ezDebugLevel_BK.getValue());
        scrnMsg.xxHrs_SV.setValue(scrnMsg.xxHrs_SB.getValue());
        scrnMsg.xxMn_SV.setValue(scrnMsg.xxMn_SB.getValue());
        scrnMsg.xxHrs_EV.setValue(scrnMsg.xxHrs_EB.getValue());
        scrnMsg.xxMn_EV.setValue(scrnMsg.xxMn_EB.getValue());
    }
}
