/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL2020;

import static business.servlet.NLBL2020.constant.NLBL2020Constant.EVENT_NM_CMN_CLOSE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/10   CITS      Takeshi Tokutomi         Create          N/A
 *</pre>
 */
public class NLBL2020_NMAL6760 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //no process;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // no process.

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            NLBL2020BMsg scrnMsg = (NLBL2020BMsg) bMsg;

            // set return popup data
            // scrnMsg.P.no(1).xxPopPrm = dsAcctNm
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNm_H1, scrnMsg.P.no(1).xxPopPrm);
            // scrnMsg.P.no(16).xxPopPrm = shipToCustCd
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustCd_H1, scrnMsg.P.no(16).xxPopPrm);
        }
    }
}