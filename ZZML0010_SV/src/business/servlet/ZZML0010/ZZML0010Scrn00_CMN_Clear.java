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
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZZML0010.common.ZZML0010CommonLogic;
import business.servlet.ZZML0010.constant.ZZML0010Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * @author q02673
 */
public class ZZML0010Scrn00_CMN_Clear extends S21CommonHandler implements ZZML0010Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

     @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

//        ZZML0010BMsg scrnMsg = (ZZML0010BMsg) bMsg;
//        
//        ZZML0010CommonLogic.setLanguageList(scrnMsg);
//
//        scrnMsg.glblCmpyCd_S.setValue(scrnMsg.glblCmpyCd.getValue());
//
//        ZZML0010CommonLogic.clearFormControle(scrnMsg);
//
//        scrnMsg.setFocusItem(scrnMsg.mlSmtpHostNm);
//        
//        scrnMsg.glblCmpyCd_S.setInputProtected(true);
//        this.setButtonEnabled("Search", false);
    }
}
