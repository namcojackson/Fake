/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0010.NSAL0010CMsg;
import business.servlet.NSAL0010.constant.NSAL0010Constant;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_ROLE_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NSAL0010_NMAL6000 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

//        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;
//        NSAL0010CMsg bizMsg = null;
//        if (!NSAL0010Constant.POPUP_CLOSE.equals(getLastGuard())) {
//            String event = scrnMsg.xxPopPrm_01.getValue();
//            if (LOC_ROLE_TP.SHIP_TO.equals(event)) {
//                bizMsg = new NSAL0010CMsg();
//                bizMsg.setBusinessID(NSAL0010Constant.BUSINESS_ID);
//                bizMsg.setFunctionCode("20");
//                EZDMsg.copy(scrnMsg, null, bizMsg, null);
//            }
//        }
//        return bizMsg;
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

//        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;
//        NSAL0010CMsg bizMsg = (NSAL0010CMsg) cMsg;
//
//        if (!NSAL0010Constant.POPUP_CLOSE.equals(getLastGuard())) {
//            String event = scrnMsg.xxPopPrm_01.getValue();
//            if (LOC_ROLE_TP.SELL_TO.equals(event)) {
//                EZDMsg.copy(bizMsg, null, scrnMsg, null);
//                scrnMsg.setFocusItem(scrnMsg.sellToCustCd_H1);
//            }
//            if (LOC_ROLE_TP.SHIP_TO.equals(event)) {
//                EZDMsg.copy(bizMsg, null, scrnMsg, null);
//                scrnMsg.setFocusItem(scrnMsg.shipToCustCd_H1);
//            }
//        }

    }
}
