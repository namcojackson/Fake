/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1500;

import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_CMN_CLOSE_EVENT_NM;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1500 PO Entry
 * Function Name : Return Action from NMAL6050(General Purpose Popup)
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/18/2015   CITS            N Akaishi       Create          N/A
 *</pre>
 */
public class NPAL1500_NMAL6050 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;

        if (!BTN_CMN_CLOSE_EVENT_NM.equals(getLastGuard())) {
            if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_P0) && "PO_SHIP_TO_CUST_V".equals(scrnMsg.xxPopPrm_P0.getValue())) {
                if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_P9)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustCd, scrnMsg.xxPopPrm_P9);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.shipToLocNm, scrnMsg.xxPopPrm_PA);
                }
                scrnMsg.setFocusItem(scrnMsg.shipToCustCd);
            } else if (ZYPCommonFunc.hasValue(scrnMsg.xxTblNm_P1) && "OTBD_CARR_V".equals(scrnMsg.xxTblNm_P1.getValue())) {

                if (ZYPCommonFunc.hasValue(scrnMsg.xxCondCd_P1)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.carrCd, scrnMsg.xxCondCd_P1);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.carrNm, scrnMsg.xxCondNm_P1);
                }
                scrnMsg.setFocusItem(scrnMsg.carrCd);
            }
        }
    }
}
