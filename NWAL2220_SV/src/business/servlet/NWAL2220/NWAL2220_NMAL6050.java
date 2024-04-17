/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2220;

import static business.servlet.NWAL2220.constant.NWAL2220Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL2220.common.NWAL2220CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Import  Search & Result
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/17   Hitachi         T.Tsuchida      Create          N/A
 * 2016/10/04   Fujitsu         S.Ohki          Update          S21_NA#12893
 *</pre>
 */
public class NWAL2220_NMAL6050 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2220BMsg scrnMsg = (NWAL2220BMsg) bMsg;
        getArgForSubScreen();

        if (!NWAL2220CommonLogic.isClosedEvent(getLastGuard())) {
            // 2016/10/04 S21_NA#12893 Del Start
//            if (EVNT_NM_SLS_REP.equals(scrnMsg.xxScrEventNm.getValue())) {
//                setValue(scrnMsg.tocNm, scrnMsg.xxPopPrm_10);
//                scrnMsg.setFocusItem(scrnMsg.tocNm);
//            }
            // 2016/10/04 S21_NA#12893 Del End
            if (EVNT_NM_SLS_BRANCH.equals(scrnMsg.xxScrEventNm.getValue())) {
                setValue(scrnMsg.coaBrDescTxt, scrnMsg.xxPopPrm_10);
                scrnMsg.setFocusItem(scrnMsg.coaBrDescTxt);
            }
        }
    }
}
