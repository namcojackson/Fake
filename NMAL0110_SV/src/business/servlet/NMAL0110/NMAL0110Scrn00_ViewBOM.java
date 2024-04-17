package business.servlet.NMAL0110;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0110.NMAL0110CMsg;
import business.servlet.NMAL0110.NMAL0110BMsg;
import business.servlet.NMAL0110.constant.NMAL0110Constant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/16/2016   SRAA            K.Aratani       Create          QC#6748,9891,9916,9970
 *</pre>
 */
public class NMAL0110Scrn00_ViewBOM extends S21CommonHandler implements NMAL0110Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

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

        NMAL0110BMsg scrnMsg = (NMAL0110BMsg) bMsg;
        NMAL0110CMsg bizMsg = (NMAL0110CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        Object[] params = new Object[1];
        params[0] = scrnMsg.mdseCd_H1;
        setArgForSubScreen(params);
    }

}
