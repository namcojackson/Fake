/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZML0060;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZZML0060.common.ZZML0060Scrn01CommonLogic;
import business.servlet.ZZML0060.constant.ZZML0060Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * @author q02673
 */
public class ZZML0060Scrn00_Add extends S21CommonHandler implements ZZML0060Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZML0060BMsg scrnMsg = (ZZML0060BMsg) bMsg;

        scrnMsg.xxScrNm_S.setValue("ZZML0060Scrn01");
        scrnMsg.xxScrEventNm.setValue("Add");
        scrnMsg.glblCmpyCd_01.setValue(scrnMsg.glblCmpyCd_S.getValue());
        scrnMsg.mlGrpId_01.clear();
        scrnMsg.mlGrpNm_01.clear();
        scrnMsg.mlKeyFirstNm_01.clear();
        scrnMsg.mlKeyScdNm_01.clear();
        scrnMsg.mlKeyThirdNm_01.clear();
        scrnMsg.mlGrpDescTxt_01.clear();

        scrnMsg.glblCmpyCd_01.setInputProtected(true);
        scrnMsg.mlGrpId_01.setInputProtected(false);
        ZZML0060Scrn01CommonLogic.setFormControleProtectCondition(scrnMsg, false);

        ZZML0060Scrn01CommonLogic.setButtonPropertiesInit(this);
        ZZML0060Scrn01CommonLogic.setNameForMessage(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.mlGrpId_01);
    }
}
