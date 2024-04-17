/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6750;

import static business.servlet.NMAL6750.constant.NMAL6750Constant.BIZ_ID;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.CMN_CLOSE;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.FUNC_CD_SRCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6750.NMAL6750CMsg;
import business.servlet.NMAL6750.common.NMAL6750CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/10   Fujitsu         H.Nagashima     Create          CSA
 * 2017/12/18   Fujitsu         Hd.Sugawara     Update          QC#22286
 *</pre>
 */
public class NMAL6750_NMAL6770 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        if (CMN_CLOSE.equals(getLastGuard())) {
            return null;
        }

        NMAL6750BMsg scrnMsg = (NMAL6750BMsg) bMsg;

        NMAL6750CMsg bizMsg = new NMAL6750CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6750BMsg scrnMsg = (NMAL6750BMsg) bMsg;

        if (CMN_CLOSE.equals(getLastGuard())) {
            return;
        }

        NMAL6750CMsg bizMsg  = (NMAL6750CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL6750CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg);
        // Del Start 2017/12/18 QC#22286
        //NMAL6750CommonLogic.setDefaultContactType(scrnMsg);
        // Del End 2017/12/18 QC#22286
        scrnMsg.setFocusItem(scrnMsg.ctacPsnFirstNm_H1);
    }
}
