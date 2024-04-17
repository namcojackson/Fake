package business.servlet.NWAL0140;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL0140.NWAL0140CMsg;
import business.servlet.NWAL0140.constant.NWAL0140Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NWAL0140_NMAL6010 extends S21CommonHandler implements NWAL0140Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NWAL0140BMsg scrnMsg = (NWAL0140BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL0140BMsg scrnMsg = (NWAL0140BMsg) bMsg;

        String lastGuard = getLastGuard();

        if ("CMN_Close".equals(lastGuard)) {
            return null;
        } else {

            NWAL0140CMsg bizMsg = new NWAL0140CMsg();
            bizMsg.setBusinessID("NWAL0140");
            bizMsg.setFunctionCode("20");
            EZDMsg.copy(scrnMsg, null, bizMsg, null);
            return bizMsg;
        }
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL0140BMsg scrnMsg = (NWAL0140BMsg) bMsg;
        NWAL0140CMsg bizMsg = (NWAL0140CMsg) cMsg;

        String lastGuard = getLastGuard();

        if (!"CMN_Close".equals(lastGuard)) {

            if (EVENT_OPEN_WIN_SHIP_TO.equals(scrnMsg.xxScrEventNm.getValue())) {

                EZDMsg.copy(bizMsg, null, scrnMsg, null);
                scrnMsg.setFocusItem(scrnMsg.locNm);

                if ("E".equals(bizMsg.getMessageKind())) {
                    throw new EZDFieldErrorException();
                }

            } else {
                throw new EZDFieldErrorException();
            }

        } else {

            scrnMsg.setFocusItem(scrnMsg.locNm);
        }

        scrnMsg.xxPopPrm.clear();
        scrnMsg.xxPopPrm_W1.clear();
        scrnMsg.xxPopPrm_W2.clear();
        scrnMsg.xxPopPrm_W3.clear();
        scrnMsg.xxPopPrm_W4.clear();
        scrnMsg.xxPopPrm_W5.clear();
        scrnMsg.xxPopPrm_W6.clear();
        scrnMsg.xxPopPrm_W7.clear();
    }
}
