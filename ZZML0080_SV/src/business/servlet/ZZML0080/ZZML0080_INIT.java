/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZML0080;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZML0080.ZZML0080CMsg;
import business.servlet.ZZML0080.common.ZZML0080CommonLogic;
import business.servlet.ZZML0080.constant.ZZML0080Constant;

import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

public class ZZML0080_INIT extends S21CommonHandler implements ZZML0080Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        ZZML0080BMsg scrnMsg = (ZZML0080BMsg) bMsg;

        Serializable arg = getArgForSubScreen();
        if (arg != null) {
            if (arg instanceof Object[]) {
               Object[] params = (Object[])arg;
               scrnMsg.xxScrNm.setValue(((EZDBStringItem) params[0]).getValue());
               scrnMsg.glblCmpyCd.setValue(((EZDBStringItem) params[1]).getValue());
            }
        } else {
            String gcc = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
            if (S21StringUtil.isNotEmpty(gcc)) {
                scrnMsg.glblCmpyCd.setValue(gcc);
            }
        }

        scrnMsg.addCheckItem(scrnMsg.glblCmpyCd);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZML0080BMsg scrnMsg = (ZZML0080BMsg) bMsg;
        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();

        ZZML0080CMsg bizMsg = new ZZML0080CMsg();
        bizMsg.setBusinessID("ZZML0080");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZML0080BMsg scrnMsg = (ZZML0080BMsg) bMsg;
        ZZML0080CMsg bizMsg = (ZZML0080CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        ZZML0080CommonLogic.setButtonPropertiesInit(this);

        scrnMsg.glblCmpyCd.setInputProtected(true);

        scrnMsg.setFocusItem(scrnMsg.mlGrpId);
    }
}
