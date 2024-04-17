/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3050;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/16   Fujitsu         S.Fujita        Create          N/A
 * 2019/09/26   Hitachi         H.Umeda         Update          QC#53691
 *</pre>
 */
public class NFCL3050_NMAL6760 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

// START 2019/09/26 H.Umeda [QC#53691,ADD]
        if (!"CMN_Close".equals(getLastGuard())) {

            NFCL3050BMsg scrnMsg = (NFCL3050BMsg) bMsg;

            String shipToCustLocNm = "";
            if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_P1)) {
                shipToCustLocNm = scrnMsg.xxPopPrm_P1.getValue();
                if (shipToCustLocNm.length() > 60) {
                    shipToCustLocNm = shipToCustLocNm.substring(0, 59) + "%";
                }
            }
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToLocNm_H, shipToCustLocNm);

            scrnMsg.setFocusItem(scrnMsg.shipToLocNm_H);
        }
// END   2019/09/26 H.Umeda [QC#53691,ADD]
    }
}
