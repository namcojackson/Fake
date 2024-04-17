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
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZML0010.ZZML0010CMsg;
import business.servlet.ZZML0010.common.ZZML0010CommonLogic;
import business.servlet.ZZML0010.constant.ZZML0010Constant;

import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * @author q02673
 */
public class ZZML0010Scrn00_CMN_Reset extends S21CommonHandler implements ZZML0010Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
         ZZML0010BMsg scrnMsg = (ZZML0010BMsg) bMsg;

         ZZML0010CMsg bizMsg = new ZZML0010CMsg();
         bizMsg.setBusinessID("ZZML0010");
         bizMsg.setFunctionCode("20");
         EZDMsg.copy(scrnMsg, null, bizMsg, null);

         return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZML0010BMsg scrnMsg = (ZZML0010BMsg) bMsg;

        // search condition
        String gcc = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
        if (S21StringUtil.isNotEmpty(gcc)) {
            scrnMsg.glblCmpyCd_S.setValue(gcc);
        } else {
            scrnMsg.glblCmpyCd_S.clear();
        }

        // search result detail-PK
        scrnMsg.glblCmpyCd.clear();
        scrnMsg.mlSysConfigRecId.clear();

        // search result detail-other
        ZZML0010CommonLogic.clearFormControle(scrnMsg);
        
        ZZML0010CommonLogic.setLanguageList(scrnMsg);

        ZZML0010CommonLogic.setFormControleProtectCondition(scrnMsg, true);

        ZZML0010CommonLogic.setButtonPropertiesReset(this);

        scrnMsg.setFocusItem(scrnMsg.glblCmpyCd_S);
        
        scrnMsg.glblCmpyCd_S.setInputProtected(false);
        this.setButtonEnabled("Search", true);
        
        
    }
}
