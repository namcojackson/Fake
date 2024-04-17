/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6710;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/17   Fujitsu         T.Murai         Create          CSA#2943
 *</pre>
 */
public class NMAL6710_NMAL6050 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL6710BMsg scrnMsg = (NMAL6710BMsg) bMsg;

        if (ZYPCommonFunc.hasValue(scrnMsg.P.no(9).xxPopPrm)) {
            scrnMsg.dsAcctGrpCd_DP.setValue(scrnMsg.P.no(9).xxPopPrm.getValue());
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.P.no(10).xxPopPrm)) {
            scrnMsg.dsAcctGrpDescTxt_DP.setValue(scrnMsg.P.no(10).xxPopPrm.getValue());
        }
        scrnMsg.setFocusItem(scrnMsg.dsAcctGrpCd_DP);
    }

}
