/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3070;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLBL3070.constant.NLBL3070Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/25/2016   Hitachi         T.Tomita        Create          CSA QC#1029
 * 09/26/2019   Fujitsu         T.Ogura         Update          QC#52362
 *</pre>
 */
public class NLBL3070_NMAL6760 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NLBL3070BMsg scrnMsg = (NLBL3070BMsg) bMsg;
        if (!NLBL3070Constant.EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustCd, scrnMsg.P.no(16).xxPopPrm);
            // START 2019/09/26 T.Ogura [QC#52362,MOD]
//            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustNm, scrnMsg.P.no(1).xxPopPrm);
            String shipToCustLocNm = "";
            if (ZYPCommonFunc.hasValue(scrnMsg.P.no(1).xxPopPrm)) {
                shipToCustLocNm = scrnMsg.P.no(1).xxPopPrm.getValue();
                if (shipToCustLocNm.length() > 60) {
                    shipToCustLocNm = shipToCustLocNm.substring(0, 60);
                }
            }
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustNm, shipToCustLocNm);
            // END   2019/09/26 T.Ogura [QC#52362,MOD]
        }
        scrnMsg.setFocusItem(scrnMsg.shipToCustCd);
    }
}
