/**
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 */
package business.servlet.NPAL1510;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Business ID : NPAL1510 PO Search
 * Function Name : NPAL1010
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/08   CUSA            K.Ogino         Create          N/A
 *</pre>
 */
public class NPAL1510_NPAL1010 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }
        NPAL1510BMsg scrnMsg = (NPAL1510BMsg) bMsg;
        ZYPEZDItemValueSetter.setValue(scrnMsg.destRtlWhCd, scrnMsg.xxPopPrm_P6);
        ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm, scrnMsg.xxPopPrm_P7);
        ZYPEZDItemValueSetter.setValue(scrnMsg.destRtlSwhCd, scrnMsg.xxPopPrm_P8);
        ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhNm, scrnMsg.xxPopPrm_P9);
        scrnMsg.setFocusItem(scrnMsg.destRtlSwhCd);

    }
}
