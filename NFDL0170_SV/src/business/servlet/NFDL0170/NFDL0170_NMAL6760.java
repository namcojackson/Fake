/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0170;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Customer Account Search Popup
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/07   Hitachi         T.Tsuchida      Create          QC#19574
 * 2019/09/26   Hitachi         H.Umeda         Update          QC#53691
 *</pre>
 */
public class NFDL0170_NMAL6760 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        
// START 2019/09/26 H.Umeda [QC#53691,ADD]
        if (!"CMN_Close".equals(getLastGuard())) {

            NFDL0170BMsg scrnMsg = (NFDL0170BMsg) bMsg;

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
