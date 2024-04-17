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
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZML0060.ZZML0060CMsg;
import business.servlet.ZZML0060.common.ZZML0060Scrn00CommonLogic;
import business.servlet.ZZML0060.common.ZZML0060Scrn01CommonLogic;
import business.servlet.ZZML0060.constant.ZZML0060Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * @author q02673
 */
public class ZZML0060Scrn00_Edit extends S21CommonHandler implements ZZML0060Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZML0060BMsg scrnMsg = (ZZML0060BMsg) bMsg;

        scrnMsg.glblCmpyCd_S.setValue(scrnMsg.glblCmpyCd.getValue());
        int editRowNo = scrnMsg.A.no(getButtonSelectNumber()).xxNum_A.getValueInt();
        scrnMsg.xxNum_01.setValue(editRowNo);

        ZZML0060CMsg bizMsg = new ZZML0060CMsg();
        bizMsg.setBusinessID("ZZML0060");
        bizMsg.setFunctionCode("20");

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZML0060BMsg scrnMsg = (ZZML0060BMsg) bMsg;
        ZZML0060CMsg bizMsg  = (ZZML0060CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if ("E".equals(bizMsg.getMessageKind())) {
            ZZML0060Scrn00CommonLogic.setButtonPropertiesSearchNotFound(this);
            scrnMsg.xxNum_01.setErrorInfo(1, bizMsg.getMessageCode());
            scrnMsg.addCheckItem(scrnMsg.xxNum_01);
            scrnMsg.putErrorScreen(bizMsg.getMessageCode(), bizMsg.getMessageInfo().getParameter());
            return;
        }

        scrnMsg.xxScrNm_S.setValue("ZZML0060Scrn01");
        scrnMsg.xxScrEventNm.setValue("Edit");
        ZZML0060Scrn01CommonLogic.setFormControleProtectCondition(scrnMsg, false);
        scrnMsg.glblCmpyCd_01.setInputProtected(true);
        scrnMsg.mlGrpId_01.setInputProtected(true);

        ZZML0060Scrn01CommonLogic.setButtonPropertiesInit(this);
        ZZML0060Scrn01CommonLogic.setNameForMessage(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.mlGrpNm_01);
    }
}
