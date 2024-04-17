package business.servlet.NMAL6830;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Cost Update Group Search Popup
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/26   SRAA            K.Aratani       Create          N/A
 *</pre>
 */
public class NMAL6830Scrn00_Select_Group extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
        
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6830BMsg scrnMsg = (NMAL6830BMsg) bMsg;
        int index = getButtonSelectNumber();
        String mdseCstUpdTpCd = scrnMsg.A.no(index).mdseCstUpdTpCd_A1.getValue();
        String mdseCstUpdGrpTxt = scrnMsg.A.no(index).mdseCstUpdGrpTxt_A1.getValue();
        Object[] arg = (Object[]) getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            EZDBStringItem param0 = (EZDBStringItem) params[0];
            param0.setValue(mdseCstUpdTpCd);
            EZDBStringItem param1 = (EZDBStringItem) params[1];
            param1.setValue(mdseCstUpdGrpTxt);
        }
    }
}
