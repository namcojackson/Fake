/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0300;

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
 * 2016/06/17   CSAI            K.Lee           Create          N/A
 *</pre>
 */
public class NLCL0300_NPAL1010 extends S21CommonHandler {


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
        NLCL0300BMsg scrnMsg = (NLCL0300BMsg) bMsg;

        String rtlWhCd = scrnMsg.P.no(6).xxPopPrm.getValue();
        String rtlWhNm = scrnMsg.P.no(7).xxPopPrm.getValue();

        if (ZYPCommonFunc.hasValue(rtlWhCd)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd_H, rtlWhCd);
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm_H, rtlWhNm);
        } else {
            scrnMsg.rtlWhCd_H.clear();
            scrnMsg.rtlWhNm_H.clear();
        }

        scrnMsg.setFocusItem(scrnMsg.rtlWhCd_H);
    }
}
