/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/23/2013   Fujitsu         M.Yaguchi      Create          N/A
 *</pre>
 */
package business.servlet.ZZIL0100;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZZIL0100.constant.ZZIL0100Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZIL0100_ZZIL0120 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // ZZIL0100BMsg scrnMsg = (ZZIL0100BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // ZZIL0100BMsg scrnMsg = (ZZIL0100BMsg) bMsg;

        // ZZIL0100CMsg bizMsg = new ZZIL0100CMsg();
        // bizMsg.setBusinessID("ZZIL0100");
        // bizMsg.setFunctionCode("20");
        // EZDMsg.copy(scrnMsg, null, bizMsg, null);

        // return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZIL0100BMsg scrnMsg = (ZZIL0100BMsg) bMsg;

        // scrnMsg.itrlIntfcId.setValue(scrnMsg.itrlIntfcId.getValue());
        
        String retItrlIntfcId = scrnMsg.P.no(0).xxPopPrm.getValue();
        String retitrlIntfcTrxConfigId = scrnMsg.P.no(1).xxPopPrm.getValue();
        
        if (ZYPCommonFunc.hasValue(retItrlIntfcId)) {
            scrnMsg.itrlIntfcId.setValue(retItrlIntfcId);
        }
        if (ZYPCommonFunc.hasValue(retitrlIntfcTrxConfigId)) {
            scrnMsg.itrlIntfcTrxConfigId_PS.setValue(retitrlIntfcTrxConfigId);
        }

        scrnMsg.setFocusItem(scrnMsg.itrlIntfcId);
    }
}
