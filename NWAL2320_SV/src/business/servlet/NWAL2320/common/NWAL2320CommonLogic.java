package business.servlet.NWAL2320.common;

import static business.servlet.NWAL2200.constant.NWAL2200Constant.BTN_CMN_APL;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.BTN_CMN_APR;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.BTN_CMN_CLR;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.BTN_CMN_DEL;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.BTN_CMN_DWL;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.BTN_CMN_RJT;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.BTN_CMN_RST;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.BTN_CMN_RTN;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.BTN_CMN_SAV;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.BTN_CMN_SUB;

import java.math.BigDecimal;

import business.servlet.NWAL2320.NWAL2320BMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/25/2016   Fujitsu         M.Hara          Create          N/A
 *</pre>
 */
public class NWAL2320CommonLogic {

    public static void setInitRequestData(NWAL2320BMsg scrnMsg) {

        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();

        scrnMsg.clear();
        ZYPTableUtil.clear(scrnMsg.A);
        ZYPTableUtil.clear(scrnMsg.B);
        ZYPTableUtil.clear(scrnMsg.C);
        ZYPTableUtil.clear(scrnMsg.D);
        ZYPEZDItemValueSetter.setValue(scrnMsg.glblCmpyCd, userProfSvc.getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(scrnMsg.slsDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxErrNum_UP, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxErrNum_UL, BigDecimal.ZERO);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPageShowFromNum_CM, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPageShowToNum_CM, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPageShowOfNum_CM, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPageShowCurNum_CM, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPageShowTotNum_CM, BigDecimal.ZERO);
    }

    public static void initCmnBtnProp(S21CommonHandler handler) {

        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);
    }

}
