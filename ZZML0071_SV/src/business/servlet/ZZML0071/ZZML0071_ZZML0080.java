/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZML0071;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZML0071.ZZML0071CMsg;
import business.blap.ZZML0071.ZZML0071_ACMsg;
import business.servlet.ZZML0071.constant.ZZML0071Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZML0071_ZZML0080 extends S21CommonHandler implements ZZML0071Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        //ZZML0071BMsg scrnMsg = (ZZML0071BMsg) bMsg;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        if ("Select".equals(getLastGuard())) {
            ZZML0071BMsg scrnMsg = (ZZML0071BMsg) bMsg;

            ZZML0071CMsg bizMsg = new ZZML0071CMsg();
            bizMsg.setBusinessID("ZZML0071");
            bizMsg.setFunctionCode("20");
            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            bizMsg.xxNum.setValue(getButtonSelectNumber());
            return bizMsg;
        } else {
            return null;
        }
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZML0071BMsg scrnMsg = (ZZML0071BMsg) bMsg;
        ZZML0071CMsg bizMsg = (ZZML0071CMsg) cMsg;

        if (!"Select".equals(getLastGuard())) {
            return;
        }

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.glblCmpyCd.setInputProtected(true);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            ZZML0071_ABMsg bmsgA = scrnMsg.A.no(i);
            ZZML0071_ACMsg cmsgA = bizMsg.A.no(i);

            if (cmsgA.mlGrpId_A.isClear()) {
                bmsgA.mlKeyFirstCd_A.setInputProtected(false);
                bmsgA.mlKeyScdCd_A.setInputProtected(false);
                bmsgA.mlKeyThirdCd_A.setInputProtected(false);
            }
//            bmsgA.mlGrpId_A.setInputProtected(true);
            bmsgA.mlGrpDescTxt_A.setInputProtected(true);
        }
    }
}
