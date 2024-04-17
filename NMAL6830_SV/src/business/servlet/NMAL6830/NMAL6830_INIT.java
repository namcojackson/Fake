package business.servlet.NMAL6830;

import java.io.Serializable;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6830.NMAL6830CMsg;
import business.servlet.NMAL6830.common.NMAL6830CommonLogic;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Cost Update Group Search Popup
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/26   SRAA            K.Aratani       Create          N/A
 *</pre>
 */
public class NMAL6830_INIT extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6830BMsg scrnMsg = (NMAL6830BMsg) bMsg;

        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {

            Object[] params = (Object[]) arg;
            if (params.length >= 1)
            	scrnMsg.mdseCstUpdTpCd_H1.setValue(((EZDBStringItem) params[0]).getValue());
            if (params.length >= 2)
	            scrnMsg.mdseCstUpdGrpTxt_H1.setValue(((EZDBStringItem) params[1]).getValue());
            if (params.length >= 3)
	            scrnMsg.mdseCstUpdStsCd_H1.setValue(((EZDBStringItem) params[2]).getValue());
            
        }

        NMAL6830CMsg bizMsg = new NMAL6830CMsg();
        bizMsg.setBusinessID("NMAL6830");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6830BMsg scrnMsg = (NMAL6830BMsg) bMsg;
        NMAL6830CMsg bizMsg = (NMAL6830CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL6830CommonLogic.initScrn(scrnMsg, scrnMsg.A.no(0).getBaseContents());
        NMAL6830CommonLogic.initCommonButton(this);
    }
}
