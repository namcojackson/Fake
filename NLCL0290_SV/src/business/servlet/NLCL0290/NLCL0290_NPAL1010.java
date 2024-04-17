/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0290;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NLCL0290.NLCL0290CMsg;
//import business.servlet.NLCL0290.constant.NLCL0290Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/10   CSAI            K.Lee           Create          N/A
 *</pre>
 */
public class NLCL0290_NPAL1010 extends S21CommonHandler {

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
        NLCL0290BMsg scrnMsg = (NLCL0290BMsg) bMsg;

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
