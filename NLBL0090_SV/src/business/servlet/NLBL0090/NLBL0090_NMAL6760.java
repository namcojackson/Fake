/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL0090;

import static business.servlet.NLBL0090.constant.NLBL0090Constant.EVENT_NM_CMN_CLOSE;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.IDX_0;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.IDX_1;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.IDX_16;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.IDX_8;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.POP_PARAM_ACCTS_ONLY;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NLBL0090_NMAL6760 extends S21CommonHandler {

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
        NLBL0090BMsg scrnMsg = (NLBL0090BMsg) bMsg;

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            if (POP_PARAM_ACCTS_ONLY.equals(scrnMsg.O.no(IDX_8).xxPopPrm_O1.getValue())) {
                // Sell To
                ZYPEZDItemValueSetter.setValue(scrnMsg.sellToCustCd_H2, scrnMsg.O.no(IDX_0).xxPopPrm_O1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNm_H2, scrnMsg.O.no(IDX_1).xxPopPrm_O1);
            } else {
                // Ship To
                ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustCd_H2, scrnMsg.O.no(IDX_16).xxPopPrm_O1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNm_H3, scrnMsg.O.no(IDX_1).xxPopPrm_O1);
            }
        }
    }
}
