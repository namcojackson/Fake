/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/6/23   CUSA            Fujitsu         Update          N/A
 *</pre>
 */
package business.servlet.NWAL0140;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL0140.NWAL0140CMsg;
import business.servlet.NWAL0140.constant.NWAL0140Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/20   SRAA            Y.Chen          Update          QC#4505
 *</pre>
 */
public class NWAL0140_NMAL6050 extends S21CommonHandler implements NWAL0140Constant {

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
        }

        if (EVENT_OPEN_WIN_SHIP_TO_COUNTRY.equals(scrnMsg.xxScrEventNm.getValue())) {
            return null;
        }

        NWAL0140CMsg bizMsg = new NWAL0140CMsg();
        bizMsg.setBusinessID("NWAL0140");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL0140BMsg scrnMsg = (NWAL0140BMsg) bMsg;
        NWAL0140CMsg bizMsg = (NWAL0140CMsg) cMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        String lastGuard = getLastGuard();

        if (!"CMN_Close".equals(lastGuard)) {

            if (EVENT_OPEN_WIN_SHIP_TO_STATE.equals(scrnMsg.xxScrEventNm.getValue())) {

                scrnMsg.setFocusItem(scrnMsg.stCd);

            } else if (EVENT_OPEN_WIN_SHIP_TO_COUNTRY.equals(scrnMsg.xxScrEventNm.getValue())) {

                scrnMsg.setFocusItem(scrnMsg.ctryCd);
// QC#4505
//            } else if (EVENT_OPEN_WIN_POST_CODE.equals(scrnMsg.xxScrEventNm.getValue())) {
//
//                scrnMsg.setFocusItem(scrnMsg.postCd);

            } else {
                throw new EZDFieldErrorException();
            }

        } else {

            if (EVENT_OPEN_WIN_SHIP_TO_STATE.equals(scrnMsg.xxScrEventNm.getValue())) {

                scrnMsg.setFocusItem(scrnMsg.stCd);

            } else if (EVENT_OPEN_WIN_SHIP_TO_COUNTRY.equals(scrnMsg.xxScrEventNm.getValue())) {

                scrnMsg.setFocusItem(scrnMsg.ctryCd);
// QC#4505
//            } else if (EVENT_OPEN_WIN_POST_CODE.equals(scrnMsg.xxScrEventNm.getValue())) {
//
//                scrnMsg.setFocusItem(scrnMsg.postCd);
            }
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
