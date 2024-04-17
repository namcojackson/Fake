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
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZML0070.ZZML0070CMsg;
import business.servlet.ZZML0070.common.ZZML0070Scrn00CommonLogic;
import business.servlet.ZZML0070.constant.ZZML0070Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZML0070_ZZML0071 extends S21CommonHandler implements ZZML0070Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // ZZML0070BMsg scrnMsg = (ZZML0070BMsg) bMsg;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZML0070BMsg scrnMsg = (ZZML0070BMsg) bMsg;
//        scrnMsg.A.clear();
//        scrnMsg.A.setValidCount(0);
//        scrnMsg.xxPageShowFromNum.clear();
//        scrnMsg.xxPageShowToNum.clear();
//        scrnMsg.xxPageShowOfNum.clear();

        ZZML0070CMsg bizMsg = new ZZML0070CMsg();
        bizMsg.setBusinessID("ZZML0070");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        bizMsg.mlUsrAddr_S.setValue(scrnMsg.mlUsrAddr_S.getValue());

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZML0070BMsg scrnMsg = (ZZML0070BMsg) bMsg;
        ZZML0070CMsg bizMsg  = (ZZML0070CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if ("E".equals(bizMsg.getMessageKind())) {
            ZZML0070Scrn00CommonLogic.setButtonPropertiesSearchNotFound(this);
            scrnMsg.setFocusItem(scrnMsg.glblCmpyCd_S);
        } else {
            ZZML0070Scrn00CommonLogic.setButtonPropertiesSearchFound(this);
            if (scrnMsg.A.getValidCount()==0) {
                ZZML0070Scrn00CommonLogic.setButtonPropertiesSearchNotFound(this);
            }
        }
        for (int i=0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).mlUsrNm_A.setInputProtected(true);
            scrnMsg.A.no(i).mlUsrAddr_A.setInputProtected(true);
            scrnMsg.A.no(i).mlUsrDescTxt_A.setInputProtected(true);
        }
    }
}
