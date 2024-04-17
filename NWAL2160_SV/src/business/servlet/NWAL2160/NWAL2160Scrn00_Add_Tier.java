/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2160;

import static business.servlet.NWAL2160.constant.NWAL2160Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2160.NWAL2160CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2160Scrn00_Add_Tier
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/09   Fujitsu         M.Yamada        Create          N/A
 *</pre>
 */
public class NWAL2160Scrn00_Add_Tier extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NWAL2160BMsg scrnMsg = (NWAL2160BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2160BMsg scrnMsg = (NWAL2160BMsg) bMsg;

        NWAL2160CMsg bizMsg = new NWAL2160CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2160BMsg scrnMsg = (NWAL2160BMsg) bMsg;
        NWAL2160CMsg bizMsg = (NWAL2160CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        int ix = bizMsg.A.getValidCount() - 1;
        if (ix < 0) {
            return;
        }
        NWAL2160_ABMsg aScrnMsg = scrnMsg.A.no(ix);
        if (!ZYPCommonFunc.hasValue(aScrnMsg.minCopyVolCnt_A)) {
            scrnMsg.setFocusItem(aScrnMsg.minCopyVolCnt_A);
        } else if (!ZYPCommonFunc.hasValue(aScrnMsg.maxCopyVolCnt_A)) {
            scrnMsg.setFocusItem(aScrnMsg.maxCopyVolCnt_A);
        } else if (!ZYPCommonFunc.hasValue(aScrnMsg.xsMtrAmtRate_A)) {
            scrnMsg.setFocusItem(aScrnMsg.xsMtrAmtRate_A);
        }
    }
}
