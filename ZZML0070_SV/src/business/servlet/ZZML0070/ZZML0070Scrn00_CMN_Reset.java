/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZML0070;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZZML0070.common.ZZML0070Scrn00CommonLogic;
import business.servlet.ZZML0070.constant.ZZML0070Constant;

import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

public class ZZML0070Scrn00_CMN_Reset extends S21CommonHandler implements ZZML0070Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        ZZML0070BMsg scrnMsg = (ZZML0070BMsg) bMsg;

        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();

        scrnMsg.mlGrpId_S.clear();
        scrnMsg.mlKeyFirstCd_S.clear();
        scrnMsg.mlKeyScdCd_S.clear();
        scrnMsg.mlKeyThirdCd_S.clear();
        scrnMsg.mlUsrId_S.clear();
        scrnMsg.mlUsrAddr_S.clear();

        String gcc = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
        if (S21StringUtil.isNotEmpty(gcc)) {
            scrnMsg.glblCmpyCd_S.setValue(gcc);
        }

        scrnMsg.xxScrNm_S.setValue("ZZML0070Scrn00");

        ZZML0070Scrn00CommonLogic.setButtonPropertiesInit(this);

        scrnMsg.setFocusItem(scrnMsg.glblCmpyCd_S);
    }
}
