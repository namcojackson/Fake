/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLAL1100;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLAL1100.constant.NLAL1100Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/21/2015   CSA             M.Ito           Create          N/A
 *</pre>
 */
public class NLAL1100_NMAL6760 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLAL1100BMsg scrnMsg = (NLAL1100BMsg) bMsg;

        if (!NLAL1100Constant.EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustCd_H1, scrnMsg.P.no(16).xxPopPrm);

            //QC:10498
            if (ZYPCommonFunc.hasValue(scrnMsg.P.no(16).xxPopPrm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNm_H1, scrnMsg.P.no(1).xxPopPrm);
            }

            scrnMsg.setFocusItem(scrnMsg.shipToCustCd_H1);
        }
    }
}
