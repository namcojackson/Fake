/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZML0010;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZML0010.ZZML0010CMsg;
import business.servlet.ZZML0010.common.ZZML0010CommonLogic;
import business.servlet.ZZML0010.constant.ZZML0010Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * @author Q02673
 */
public class ZZML0010Scrn00_Search extends S21CommonHandler implements ZZML0010Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZML0010BMsg scrnMsg = (ZZML0010BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.glblCmpyCd_S);

        try {
            scrnMsg.putErrorScreen();
        } catch (EZDFieldErrorException e) {
            ZZML0010CommonLogic.clearFormControle(scrnMsg);
            ZZML0010CommonLogic.setFormControleProtectCondition(scrnMsg, true);
            ZZML0010CommonLogic.setButtonPropertiesInit(this);
            throw e;
        }
    }

     @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
         ZZML0010BMsg scrnMsg = (ZZML0010BMsg) bMsg;

         ZZML0010CMsg bizMsg = new ZZML0010CMsg();
         bizMsg.setBusinessID("ZZML0010");
         bizMsg.setFunctionCode("20");
         bizMsg.glblCmpyCd_S.setValue(scrnMsg.glblCmpyCd_S.getValue());

         return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZML0010BMsg scrnMsg = (ZZML0010BMsg) bMsg;
        ZZML0010CMsg bizMsg  = (ZZML0010CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if ("E".equals(bizMsg.getMessageKind())) {
            ZZML0010CommonLogic.setFormControleProtectCondition(scrnMsg, true);
            ZZML0010CommonLogic.setButtonPropertiesSearchNotFound(this);
            scrnMsg.setFocusItem(scrnMsg.glblCmpyCd_S);

            ZZML0010CommonLogic.setFormControleProtectCondition(scrnMsg, true);
            scrnMsg.glblCmpyCd_S.setInputProtected(false);
            this.setButtonEnabled("Search", true);
        } else {
            ZZML0010CommonLogic.setFormControleProtectCondition(scrnMsg, false);
            ZZML0010CommonLogic.setButtonPropertiesSearchFound(this);
            scrnMsg.setFocusItem(scrnMsg.mlSmtpHostNm);
            
            scrnMsg.glblCmpyCd_S.setInputProtected(true);
            this.setButtonEnabled("Search", false);
        }
        if (scrnMsg.glblCmpyCd_S.isError()) {
            ZZML0010CommonLogic.setFormControleProtectCondition(scrnMsg, true);
            scrnMsg.glblCmpyCd_S.setInputProtected(false);
            this.setButtonEnabled("Search", true);
        }
        scrnMsg.addCheckItem(scrnMsg.glblCmpyCd_S);
        scrnMsg.putErrorScreen();

    }
}
