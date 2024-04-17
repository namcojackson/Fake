/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/08/05   Fujitsu         S.Yoshida       Create          N/A
 * 2009/10/29   Fujitsu         S.Yoshida       Update          1306
 *</pre>
 */
package business.servlet.NLCL0230;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLCL0230.constant.NLCL0230Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NLCL0230Scrn00_Select_Location_Code extends S21CommonHandler implements NLCL0230Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

     @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // Get selected values.
        NLCL0230BMsg scrnMsg = (NLCL0230BMsg) bMsg;
        int index = getButtonSelectNumber();
        String invtyLocTpCd = scrnMsg.A.no(index).invtyLocTpCd_A1.getValue();
        String invtyLocCd   = scrnMsg.A.no(index).invtyLocCd_A1.getValue();
        String invtyLocNm   = scrnMsg.A.no(index).invtyLocNm_A1.getValue();
        String mdseCd       = scrnMsg.mdseCd.getValue();

        // Set return values.
        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            EZDBStringItem param1 = (EZDBStringItem) params[0];
            EZDBStringItem param2 = (EZDBStringItem) params[1];
            EZDBStringItem param3 = (EZDBStringItem) params[2];
            param1.setValue(invtyLocTpCd);
            param2.setValue(invtyLocCd);
            param3.setValue(invtyLocNm);
            if (params.length > 3) {
                EZDBStringItem param4 = (EZDBStringItem) params[3];
                if (ZYPCommonFunc.hasValue(param4)) {
                    param4.setValue(mdseCd);
                }
            }
        }
    }
}
