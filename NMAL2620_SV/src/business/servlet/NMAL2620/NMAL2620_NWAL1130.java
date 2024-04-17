/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2620;

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
 * 2013/03/26   Hitachi         T.Mizuki        Create          N/A
 *</pre>
 */
public class NMAL2620_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2620BMsg scrnMsg = (NMAL2620BMsg) bMsg;
        if (ZYPCommonFunc.hasValue(scrnMsg.B.no(0).xxComnScrColValTxt_B)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.rqstRsltCmntTxt, scrnMsg.B.no(0).xxComnScrColValTxt_B);
        }
        // focus
        scrnMsg.setFocusItem(scrnMsg.rqstRsltCmntTxt);
    }
}
