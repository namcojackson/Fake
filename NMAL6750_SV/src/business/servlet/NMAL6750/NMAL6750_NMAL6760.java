/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6750;

import static business.servlet.NMAL6750.constant.NMAL6750Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/05   Fujitsu         C.Tanaka        Create          CSA
 * 2016/01/25   Hitachi         T.Tomita        Update          CSA QC#1029
 * 2016/04/06   Hitachi         C.Yokoi         Update          CSA QC#6633
 *</pre>
 */
public class NMAL6750_NMAL6760 extends S21CommonHandler {

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
        NMAL6750BMsg scrnMsg = (NMAL6750BMsg) bMsg;

        // START 2016/01/25 T.Tomita [QC#1029, DEL]
//        ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNum_H1, scrnMsg.P.no(0).xxPopPrm_P);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNm_H1, scrnMsg.P.no(1).xxPopPrm_P);
//        scrnMsg.setFocusItem(scrnMsg.dsAcctNum_H1);
        // END 2016/01/25 T.Tomita [QC#1029, DEL]
        // START 2016/01/25 T.Tomita [QC#1029, ADD]
        if (!CMN_CLOSE.equals(getLastGuard())) {
            if (OPENWIN_ACCT.equals(scrnMsg.xxScrEventNm.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNum_H1, scrnMsg.P.no(0).xxPopPrm_P);
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNm_H1, scrnMsg.dsAcctNm_P1); // 2016/04/06 CSA-QC#6633 Mod
                scrnMsg.setFocusItem(scrnMsg.dsAcctNum_H1);
            } else if (OPENWIN_LOC.equals(scrnMsg.xxScrEventNm.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.locNum_H1, scrnMsg.P.no(2).xxPopPrm_P);
                ZYPEZDItemValueSetter.setValue(scrnMsg.locNm_H1, scrnMsg.P.no(3).xxPopPrm_P);
                scrnMsg.setFocusItem(scrnMsg.locNum_H1);
            }
        }
        scrnMsg.xxScrEventNm.clear();
        // END 2016/01/25 T.Tomita [QC#1029, ADD]
    }
}
