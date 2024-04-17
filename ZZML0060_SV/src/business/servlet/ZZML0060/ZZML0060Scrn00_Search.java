/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZML0060;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZML0060.ZZML0060CMsg;
import business.servlet.ZZML0060.common.ZZML0060Scrn00CommonLogic;
import business.servlet.ZZML0060.constant.ZZML0060Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * @author q02673
 */
public class ZZML0060Scrn00_Search extends S21CommonHandler implements ZZML0060Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZML0060BMsg scrnMsg = (ZZML0060BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.glblCmpyCd_S);
        scrnMsg.addCheckItem(scrnMsg.mlGrpId_S);
        scrnMsg.addCheckItem(scrnMsg.mlGrpNm_S);

        try {
            scrnMsg.putErrorScreen();
        } catch (EZDFieldErrorException e) {
            ZZML0060Scrn00CommonLogic.setButtonPropertiesInit(this);
            throw e;
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZML0060BMsg scrnMsg = (ZZML0060BMsg) bMsg;
        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();

        ZZML0060CMsg bizMsg = new ZZML0060CMsg();
        bizMsg.setBusinessID("ZZML0060");
        bizMsg.setFunctionCode("20");

        bizMsg.glblCmpyCd_S.setValue(scrnMsg.glblCmpyCd_S.getValue());
        bizMsg.mlGrpId_S.setValue(scrnMsg.mlGrpId_S.getValue());
        bizMsg.mlGrpNm_S.setValue(scrnMsg.mlGrpNm_S.getValue());

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZML0060BMsg scrnMsg = (ZZML0060BMsg) bMsg;
        ZZML0060CMsg bizMsg  = (ZZML0060CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        //scrnMsg.A.no(0).mlGrpId_A.setValue("12345678901234567890");

        if (scrnMsg.A.getValidCount() == 0) {
            ZZML0060Scrn00CommonLogic.setButtonPropertiesSearchNotFound(this);
            scrnMsg.setFocusItem(scrnMsg.glblCmpyCd_S);
        } else {
            ZZML0060Scrn00CommonLogic.setButtonPropertiesSearchFound(this);
        }
        
        // add check items.
        scrnMsg.addCheckItem(scrnMsg.glblCmpyCd_S);
        scrnMsg.putErrorScreen();
    }
}
