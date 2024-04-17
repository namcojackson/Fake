/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZML0070;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZML0070.ZZML0070CMsg;
import business.servlet.ZZML0070.common.ZZML0070Scrn00CommonLogic;
import business.servlet.ZZML0070.constant.ZZML0070Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZML0070Scrn00_Search extends S21CommonHandler implements ZZML0070Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZML0070BMsg scrnMsg = (ZZML0070BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.glblCmpyCd_S);
        scrnMsg.addCheckItem(scrnMsg.mlGrpId_S);
        scrnMsg.addCheckItem(scrnMsg.mlKeyFirstCd_S);
        scrnMsg.addCheckItem(scrnMsg.mlKeyScdCd_S);
        scrnMsg.addCheckItem(scrnMsg.mlKeyThirdCd_S);
        scrnMsg.addCheckItem(scrnMsg.mlUsrId_S);
        scrnMsg.addCheckItem(scrnMsg.mlUsrAddr_S);

        try {
            scrnMsg.putErrorScreen();
        } catch (EZDFieldErrorException e) {
            ZZML0070Scrn00CommonLogic.setButtonPropertiesInit(this);
            throw e;
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZML0070BMsg scrnMsg = (ZZML0070BMsg) bMsg;
        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();

        ZZML0070CMsg bizMsg = new ZZML0070CMsg();
        bizMsg.setBusinessID("ZZML0070");
        bizMsg.setFunctionCode("20");

        bizMsg.glblCmpyCd_S.setValue(scrnMsg.glblCmpyCd_S.getValue());
        bizMsg.mlGrpId_S.setValue(scrnMsg.mlGrpId_S.getValue());
        bizMsg.mlKeyFirstCd_S.setValue(scrnMsg.mlKeyFirstCd_S.getValue());
        bizMsg.mlKeyScdCd_S.setValue(scrnMsg.mlKeyScdCd_S.getValue());
        bizMsg.mlKeyThirdCd_S.setValue(scrnMsg.mlKeyThirdCd_S.getValue());
        bizMsg.mlUsrId_S.setValue(scrnMsg.mlUsrId_S.getValue());
        bizMsg.mlUsrAddr_S.setValue(scrnMsg.mlUsrAddr_S.getValue());

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        //new RuntimeException("test").printStackTrace();
        ZZML0070BMsg scrnMsg = (ZZML0070BMsg) bMsg;
        ZZML0070CMsg bizMsg  = (ZZML0070CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
      
        if (scrnMsg.A.getValidCount() == 0) {
            ZZML0070Scrn00CommonLogic.setButtonPropertiesSearchNotFound(this);
            scrnMsg.setFocusItem(scrnMsg.glblCmpyCd_S);
        } else {
            ZZML0070Scrn00CommonLogic.setButtonPropertiesSearchFound(this);
        }
        
        for (int i=0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).mlUsrNm_A.setInputProtected(true);
            scrnMsg.A.no(i).mlUsrAddr_A.setInputProtected(true);
            scrnMsg.A.no(i).mlUsrDescTxt_A.setInputProtected(true);
        }
        // add check items.
        scrnMsg.addCheckItem(scrnMsg.glblCmpyCd_S);
        scrnMsg.putErrorScreen();
    }
}
