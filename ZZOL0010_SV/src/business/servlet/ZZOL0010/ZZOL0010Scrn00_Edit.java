/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZOL0010;

import parts.common.*;
import parts.servletcommon.*;
import business.servlet.ZZOL0010.common.ZZOL0010CommonLogic;
import business.servlet.ZZOL0010.constant.ZZOL0010Constant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZOL0010Scrn00_Edit extends S21CommonHandler implements ZZOL0010Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        
        ZZOL0010BMsg scrnMsg = (ZZOL0010BMsg) bMsg;

        // set Button Properties
        ZZOL0010CommonLogic.changeCommonButton01(this);
        ZZOL0010CommonLogic.initPullDown01(scrnMsg);
        
        // Data Set
        int i = getButtonSelectNumber();
        scrnMsg.ezBusinessID_01.setValue(scrnMsg.A.no(i).ezBusinessID_A1.getValue());
        scrnMsg.ezCompanyCode_01.setValue(scrnMsg.A.no(i).ezCompanyCode_A1.getValue());

        // Flag
        if (scrnMsg.A.no(i).xxEzOnlStopFlagDisp_A1.getValue().equals(ONLINE_STOP_ON)) {
            scrnMsg.ezOnlStopFlag_01.setValue("1");
        } else {
            scrnMsg.ezOnlStopFlag_01.setValue("0");
        }

        if (scrnMsg.A.no(i).xxEzAcctInfoModeDisp_A1.getValue().equals(ACCOUNT_MODE_RUN)) {
            scrnMsg.ezAcctInfoMode_01.setValue("1");
        } else {
            scrnMsg.ezAcctInfoMode_01.setValue("0");
        }

        if (scrnMsg.A.no(i).xxEzDebugLevelDisp_A1.getValue().equals(DEBUG_MODE_RUN)) {
            scrnMsg.ezDebugLevel_01.setValue("1");
        } else {
            scrnMsg.ezDebugLevel_01.setValue("0");
        }

        // Start Time
        if (scrnMsg.A.no(i).xxHrsMnScd_AS.getValue().length() == 8) {
            String stHH = scrnMsg.A.no(i).xxHrsMnScd_AS.getValue().substring(0, 2);
            String stMM = scrnMsg.A.no(i).xxHrsMnScd_AS.getValue().substring(3, 5);
            scrnMsg.xxHrs_SV.setValue(stHH);
            scrnMsg.xxMn_SV.setValue(stMM);
        }

        // End Time
        if (scrnMsg.A.no(i).xxHrsMnScd_AE.getValue().length() == 8) {
            String enHH = scrnMsg.A.no(i).xxHrsMnScd_AE.getValue().substring(0, 2);
            String enMM = scrnMsg.A.no(i).xxHrsMnScd_AE.getValue().substring(3, 5);
            scrnMsg.xxHrs_EV.setValue(enHH);
            scrnMsg.xxMn_EV.setValue(enMM);
        }

        // Update Time and Update Timezone
        scrnMsg.ezUpTime_01.setValue(scrnMsg.A.no(i).ezUpTime_A1.getValue());
        scrnMsg.ezUpTimeZone_01.setValue(scrnMsg.A.no(i).ezUpTimeZone_A1.getValue());
        
        // Set protected
        scrnMsg.ezBusinessID_01.setInputProtected(true);
        scrnMsg.ezCompanyCode_01.setInputProtected(true);
        scrnMsg.xxAncrCtrlFlg.setInputProtected(true);
        scrnMsg.xxScrEventNm.setValue("Edit");
        
        if (scrnMsg.ezBusinessID_01.getValue().equals(aplID)) {
            scrnMsg.ezOnlStopFlag_01.setInputProtected(true);
        } else {
            scrnMsg.ezOnlStopFlag_01.setInputProtected(false);
        }
        
        
        ZZOL0010CommonLogic.setResetValue(scrnMsg);
        
        // Focus Controll
        scrnMsg.setFocusItem(scrnMsg.xxHrs_SV);
    }

}
