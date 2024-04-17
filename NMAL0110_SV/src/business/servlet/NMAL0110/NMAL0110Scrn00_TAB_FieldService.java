package business.servlet.NMAL0110;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0110.NMAL0110CMsg;
import business.servlet.NMAL0110.NMAL0110BMsg;
import business.servlet.NMAL0110.common.NMAL0110CommonLogic;
import business.servlet.NMAL0110.constant.NMAL0110Constant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/12/2015   SRAA            K.Aratani       Create          
 *</pre>
 */
public class NMAL0110Scrn00_TAB_FieldService extends S21CommonHandler implements NMAL0110Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0110BMsg scrnMsg = (NMAL0110BMsg) bMsg;
        NMAL0110CommonLogic.checkInput_Tab(ctx, scrnMsg);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0110BMsg scrnMsg = (NMAL0110BMsg) bMsg;
        NMAL0110CMsg bizMsg = new NMAL0110CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL0110CMsg bizMsg = (NMAL0110CMsg) cMsg;
        NMAL0110BMsg scrnMsg = (NMAL0110BMsg) bMsg;
        bizMsg.xxDplyTab_H1.setValue(TAB_FIELD_SERVICE);
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
		scrnMsg.xxRsltFlg_H1.clear(); // Manufacture Item Duplication Warning Flag
		scrnMsg.xxRsltFlg_H2.clear(); // Mercury Code and Parts Code does not exists Warning Flag
		scrnMsg.xxRsltFlg_H3.clear(); // "Save as Tmpl" button Disabled Flag

        NMAL0110CommonLogic.changeActivation_Detail(this, getUserProfileService(), scrnMsg);
        NMAL0110CommonLogic.changeTableColorController(scrnMsg);
        
    }

}
