/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL1010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLCL1010.common.NLCL1010CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SER_TRX_EVENT;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NLCL1010Scrn00_Select_AddSerEvt extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NLCL1010BMsg scrnMsg = (NLCL1010BMsg) bMsg;

        NLCL1010CommonLogic.fireAddSerEvtChanged(scrnMsg);

        // 10/16/2015 add start
        if (ZYPCommonFunc.hasValue(scrnMsg.serTrxEventCd_P1)
        &&  SER_TRX_EVENT.STOCK_STATUS_CHANGE.equals(scrnMsg.serTrxEventCd_P1.getValue())) {
            scrnMsg.stkStsCd_F1.setInputProtected(false);
        } else {
            scrnMsg.stkStsCd_F1.clear();
            scrnMsg.stkStsCd_F1.setInputProtected(true);
        }
        // 10/16/2015 add end
    }
}
