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
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZZML0060.constant.ZZML0060Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * @author q02673
 */
public class ZZML0060Scrn00_User extends S21CommonHandler implements ZZML0060Constant {

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

        String glblCmpyCd = scrnMsg.glblCmpyCd.getValue();
        String mlGrpId = scrnMsg.A.no(getButtonSelectNumber()).mlGrpId_A.getValue();
        int editRowNo = scrnMsg.A.no(getButtonSelectNumber()).xxNum_A.getValueInt();
        boolean isScreenTrans = true;
        Object[] args = new Object[]{glblCmpyCd, mlGrpId, isScreenTrans};
//        setArgForJump(args);
        setArgForSubScreen(args);
    }
}
