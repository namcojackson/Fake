/**
 * <pre>
 * Date         Company         Name           Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/23/2013   Fujitsu         M.Yaguchi      Create          N/A
 *</pre>
 */
package business.servlet.ZZIL0100.common;

import java.util.HashMap;
import java.util.Map;

import parts.servletcommon.EZDCommonHandler;
import business.blap.ZZIL0100.ZZIL0100CMsg;
import business.servlet.ZZIL0100.ZZIL0100BMsg;
import business.servlet.ZZIL0100.ZZIL0100Bean;
import business.servlet.ZZIL0100.constant.ZZIL0100Constant;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

public class ZZIL0100CommonLogic extends ZZIL0100Constant{
    /**
     * Set Table color and exchange display value
     * @param scrnMsg ZZIL0100BMsg
     * @param bizMsg ZZIL0100CMsg
     */
    public static void setTableColor(ZZIL0100BMsg scrnMsg, ZZIL0100CMsg bizMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG(ZZIL0100Bean.A, scrnMsg.A);

        if (scrnMsg.A.getValidCount() > 0) {

            String styleClass = "";
            String procVal = null;
            String dispVal = null;

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

                // processed_flag name setting
                procVal = bizMsg.A.no(i).processedFlag_A.getValue();
                dispVal = procFlg.get(procVal);
                scrnMsg.A.no(i).xxProcFlgNm_A.setValue(dispVal);

                // register date setting
                scrnMsg.A.no(i).xxDtTm_AC.setValue(convYmd(bizMsg.A.no(i).xxDtTm_AC.getValue()));
                // updated date setting
                scrnMsg.A.no(i).xxDtTm_AU.setValue(convYmd(bizMsg.A.no(i).xxDtTm_AU.getValue()));

                for (int j = 0; j < PROC_LIST.length; j++) {
                    scrnMsg.A.no(i).processedFlag_AU.no(j).setValue(PROC_LIST[j][COL_PROC_CD]);
                    scrnMsg.A.no(i).xxProcFlgNm_AU.no(j).setValue(PROC_LIST[j][COL_PROC_NM]);
                }

                
                if (TABLE_BG_COLOR_CLASS.equals(styleClass)) {
                    styleClass = "";
                } else {
                    styleClass = TABLE_BG_COLOR_CLASS;
                }
                tblColor.setRowStyle(ZZIL0100Bean.A, i, styleClass);
            }

        }
    }

    /**
     * Set Table color and exchange display value
     * @param scrnMsg ZZIL0100BMsg
     * @param bizMsg ZZIL0100CMsg
     */
    public static void setTableColorForSubmit(ZZIL0100BMsg scrnMsg, ZZIL0100CMsg bizMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG(ZZIL0100Bean.A, scrnMsg.A);

        if (scrnMsg.A.getValidCount() > 0) {

            String styleClass = "";
            String procVal = null;
            String dispVal = null;

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

                // processed_flag name setting
                procVal = bizMsg.A.no(i).processedFlag_A.getValue();
                dispVal = procFlg.get(procVal);
                scrnMsg.A.no(i).xxProcFlgNm_A.setValue(dispVal);

                // updated date setting
                scrnMsg.A.no(i).xxDtTm_AU.setValue(convYmd(bizMsg.A.no(i).xxDtTm_AU.getValue()));

                for (int j = 0; j < PROC_LIST.length; j++) {
                    scrnMsg.A.no(i).processedFlag_AU.no(j).setValue(PROC_LIST[j][COL_PROC_CD]);
                    scrnMsg.A.no(i).xxProcFlgNm_AU.no(j).setValue(PROC_LIST[j][COL_PROC_NM]);
                }
                
                if (TABLE_BG_COLOR_CLASS.equals(styleClass)) {
                    styleClass = "";
                } else {
                    styleClass = TABLE_BG_COLOR_CLASS;
                }
                tblColor.setRowStyle(ZZIL0100Bean.A, i, styleClass);
            }

        }
    }
    
    /**
     * Set listbox
     * @param scrnMsg
     */
    public static void setListBox(ZZIL0100BMsg scrnMsg) {

        String hmCd = null;
        String hmDisp = null;

        // time listbox setting
        for (int i = 0; i < HH24; i++) {
            hmCd = String.format("%02d", i);
            hmDisp = String.format("%02d:00", i);

            scrnMsg.xxHrs_RF.no(i).setValue(hmCd);
            scrnMsg.xxHrsMn_RF.no(i).setValue(hmDisp);

            scrnMsg.xxHrs_RT.no(i).setValue(hmCd);
            scrnMsg.xxHrsMn_RT.no(i).setValue(hmDisp);

            scrnMsg.xxHrs_UF.no(i).setValue(hmCd);
            scrnMsg.xxHrsMn_UF.no(i).setValue(hmDisp);

            scrnMsg.xxHrs_UT.no(i).setValue(hmCd);
            scrnMsg.xxHrsMn_UT.no(i).setValue(hmDisp);
        }

        // processed_flag listbox setting
        for (int i = 0; i < PROC_LIST.length; i++) {
            scrnMsg.processedFlag_PC.no(i).setValue(PROC_LIST[i][COL_PROC_CD]);
            scrnMsg.xxProcFlgNm_PC.no(i).setValue(PROC_LIST[i][COL_PROC_NM]);
        }
    }
    
    /**
     * initial button setting
     * @param handler
     */
    public static void setInitButton(EZDCommonHandler handler){
        setButton(handler);
        handler.setButtonProperties(CMN_BTN8[0], CMN_BTN8[1], CMN_BTN8[2], 1, null);
    }
    
    /**
     * set submit button
     * @param handler
     */
    public static void setSubmitButton(EZDCommonHandler handler){
        setButton(handler);
        handler.setButtonProperties(CMN_BTN2[0], CMN_BTN2[1], CMN_BTN2[2], 1, null);
        handler.setButtonProperties(CMN_BTN8[0], CMN_BTN8[1], CMN_BTN8[2], 1, null);
    }   
    /**
     * common button setting
     * @param handler
     */
    private static void setButton(EZDCommonHandler handler) {
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
     * set message name
     * @param scrnMsg
     */
    public static void setNameForMessage(ZZIL0100BMsg scrnMsg){
        scrnMsg.itrlIntfcTrxConfigId_PS.setNameForMessage("Config Name");
        scrnMsg.itrlIntfcId.setNameForMessage("Internal Interface ID");
        scrnMsg.xxFromDt_RF.setNameForMessage("Transmit Data Creation Time From");
        scrnMsg.xxHrs_R1.setNameForMessage("Transmit Data Creation Time From");
        scrnMsg.xxToDt_RT.setNameForMessage("Transmit Data Creation Time To");
        scrnMsg.xxHrs_R2.setNameForMessage("Transmit Data Creation Time To");
        scrnMsg.xxFromDt_UF.setNameForMessage("Transmit Data Update Time From");
        scrnMsg.xxHrs_U1.setNameForMessage("Transmit Data Update Time From");
        scrnMsg.xxToDt_UT.setNameForMessage("Transmit Data Update Time To");
        scrnMsg.xxHrs_U2.setNameForMessage("Transmit Data Update Time To");
        scrnMsg.processedFlag_PS.setNameForMessage("Processed Flag");
    }
    
    /**
     * set check item
     * @param scrnMsg
     */
    public static void checkCommonInput(ZZIL0100BMsg scrnMsg){
        scrnMsg.addCheckItem(scrnMsg.itrlIntfcTrxConfigId_PS);
        scrnMsg.addCheckItem(scrnMsg.itrlIntfcId);
        scrnMsg.addCheckItem(scrnMsg.xxFromDt_RF);
        scrnMsg.addCheckItem(scrnMsg.xxHrs_R1);
        scrnMsg.addCheckItem(scrnMsg.xxToDt_RT);
        scrnMsg.addCheckItem(scrnMsg.xxHrs_R2);
        scrnMsg.addCheckItem(scrnMsg.xxFromDt_UF);
        scrnMsg.addCheckItem(scrnMsg.xxHrs_U1);
        scrnMsg.addCheckItem(scrnMsg.xxToDt_UT);
        scrnMsg.addCheckItem(scrnMsg.xxHrs_U2);
        scrnMsg.addCheckItem(scrnMsg.transactionId);
        scrnMsg.putErrorScreen();
    }
    
    /**
     * create popup parameter
     * @param scrnMsg
     * @return
     */
    public static Object[] createZZIL0120Parameters(ZZIL0100BMsg scrnMsg){
        ZYPTableUtil.clear(scrnMsg.P);
        Object[] param = new Object[2];
        scrnMsg.P.no(0).xxPopPrm.setValue("");
        scrnMsg.P.no(1).xxPopPrm.setValue("");
        param[0] = scrnMsg.P.no(0).xxPopPrm;
        param[1] = scrnMsg.P.no(1).xxPopPrm;
        return param;
    }

    /**
     * Exchange String to Date String 
     * @param ymd String
     * @return Date String (MM/DD/YYYY hh:mm:ss.SSS)
     */
    private static String convYmd(String ymd) {
        if (ymd.length() > 0) {
            return ymd.substring(4, 6) + "/" + ymd.substring(6, 8) + "/" + ymd.substring(0, 4) + " " + ymd.substring(8, 10) + ":" + ymd.substring(10, 12) + ":" + ymd.substring(12,14) + "." + ymd.substring(14,17);
        }
        return ymd;
    }

    /** processed flag value */
    private static final Map<String, String> procFlg;
    static {
        procFlg = new HashMap<String, String>();
        for (int i = 0; i < PROC_LIST.length; i++) {
            procFlg.put(PROC_LIST[i][COL_PROC_CD], PROC_LIST[i][COL_PROC_NM]);
        }
    }
}
