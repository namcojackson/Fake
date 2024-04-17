/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZML0071;


import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsgAccessor;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.ZZML0071.constant.ZZML0071Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZML0071Scrn00_CompanyLookup extends S21CommonHandler implements ZZML0071Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

//        ZZML0071BMsg scrnMsg = (ZZML0071BMsg) bMsg;
//
//        Object[] params = new Object[2];
//        params[0] = EZDMsgAccessor.createItem(scrnMsg, "xxScrNm");
//        ((EZDBStringItem) params[0]).setValue("Global Company Code");
//        params[1] = scrnMsg.glblCmpyCd;
//        setArgForSubScreen(params);
    }
}
