/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZML0080;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZML0080.ZZML0080CMsg;
import business.servlet.ZZML0080.constant.ZZML0080Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZML0080Scrn00_PagePrev extends S21CommonHandler implements ZZML0080Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        ZZML0080BMsg scrnMsg = (ZZML0080BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.glblCmpyCd);
        scrnMsg.addCheckItem(scrnMsg.mlGrpId);
        scrnMsg.addCheckItem(scrnMsg.mlGrpNm);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZML0080BMsg scrnMsg = (ZZML0080BMsg) bMsg;

        scrnMsg.xxPageShowFromNum.setValue(scrnMsg.xxPageShowFromNum.getValueInt() - scrnMsg.A.length() - 1);
        scrnMsg.xxPageShowToNum.clear();

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

        scrnMsg.setFocusItem(scrnMsg.mlGrpId);
    }
}
