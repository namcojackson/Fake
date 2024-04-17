/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2240;

import static business.servlet.NWAL2240.constant.NWAL2240Constant.CMN_CLOSE;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.POP_PAR_10;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.POP_PAR_9;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/13   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NWAL2240_NMAL6050 extends S21CommonHandler {

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

        NWAL2240BMsg scrnMsg = (NWAL2240BMsg) bMsg;

        if (!CMN_CLOSE.equals(getLastGuard())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.istlTechCd_DI, scrnMsg.P.no(POP_PAR_9).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.techNm_DI, scrnMsg.P.no(POP_PAR_10).xxPopPrm);
            scrnMsg.setFocusItem(scrnMsg.techNm_DI);
        } else {
            scrnMsg.setFocusItem(scrnMsg.ordSrcRefNum_H0);
        }
    }
}
