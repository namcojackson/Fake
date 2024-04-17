/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZOL0120;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZOL0120.ZZOL0120CMsg;
import business.servlet.ZZOL0120.common.ZZOL0120CommonLogic;
import business.servlet.ZZOL0120.constant.ZZOL0120Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZOL0120Scrn02_Upd extends S21CommonHandler implements ZZOL0120Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZOL0120BMsg scrnMsg = (ZZOL0120BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.upTabNm_C1);
        scrnMsg.addCheckItem(scrnMsg.upTabSortNum_C1);
        scrnMsg.addCheckItem(scrnMsg.bizAppId_C1);
        scrnMsg.addCheckItem(scrnMsg.bizAppNm_C1);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
        ZZOL0120BMsg scrnMsg = (ZZOL0120BMsg) bMsg;

        ZZOL0120CMsg bizMsg = new ZZOL0120CMsg();
        bizMsg.setBusinessID("ZZOL0120");
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZOL0120BMsg scrnMsg = (ZZOL0120BMsg) bMsg;
        ZZOL0120CMsg bizMsg  = (ZZOL0120CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        
        ZZOL0120CommonLogic.dspScrn02(scrnMsg, this);
        
        scrnMsg.setFocusItem(scrnMsg.upTabNm_C1);

        S21SortColumnIMGController.clearIMG(SCREEN_NAME_02, scrnMsg, scrnMsg.C.no(0).getBaseContents());

        scrnMsg.addCheckItem(scrnMsg.glblCmpyCd);
        scrnMsg.putErrorScreen();
    }

}
