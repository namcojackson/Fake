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
public class ZZML0060Scrn01_CMN_Submit extends S21CommonHandler implements ZZML0060Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        ZZML0060BMsg scrnMsg = (ZZML0060BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.glblCmpyCd_01);
        scrnMsg.addCheckItem(scrnMsg.mlGrpId_01);
        scrnMsg.addCheckItem(scrnMsg.mlGrpNm_01);
        scrnMsg.addCheckItem(scrnMsg.mlKeyFirstNm_01);
        scrnMsg.addCheckItem(scrnMsg.mlKeyScdNm_01);
        scrnMsg.addCheckItem(scrnMsg.mlKeyThirdNm_01);
        scrnMsg.addCheckItem(scrnMsg.mlGrpDescTxt_01);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZML0060BMsg scrnMsg = (ZZML0060BMsg) bMsg;

        ZZML0060CMsg bizMsg = new ZZML0060CMsg();
        bizMsg.setBusinessID("ZZML0060");
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

//        if (scrnMsg.glblCmpyCd_01.isInputProtected()) {
//            bizMsg.xxScrEventNm.setValue("Edit");
//        } else {
//            bizMsg.xxScrEventNm.setValue("Add");
//        }

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZML0060BMsg scrnMsg = (ZZML0060BMsg) bMsg;
        ZZML0060CMsg bizMsg  = (ZZML0060CMsg) cMsg;
        
        // If error, no go back to the caller.
        if (bizMsg.getMessageKind().equals("E")) { // fail
            throw new EZDFieldErrorException();
        } else {
            ZZML0060Scrn00CommonLogic.setButtonPropertiesFromScrn01(this);
        }

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
       

        scrnMsg.addCheckItem(scrnMsg.glblCmpyCd_01);
        scrnMsg.addCheckItem(scrnMsg.mlGrpId_01);
        scrnMsg.addCheckItem(scrnMsg.mlGrpNm_01);
        scrnMsg.addCheckItem(scrnMsg.mlKeyFirstNm_01);
        scrnMsg.addCheckItem(scrnMsg.mlKeyScdNm_01);
        scrnMsg.addCheckItem(scrnMsg.mlKeyThirdNm_01);
        scrnMsg.addCheckItem(scrnMsg.mlGrpDescTxt_01);
        scrnMsg.putErrorScreen();
    }
}
