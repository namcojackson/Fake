/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSBL0040.common.NSBL0040CommonLogic;
import business.servlet.NSBL0040.constant.NSBL0040Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/06/15   Hitachi         Y.Osawa         Create          QC#18970
 * 2019/10/02   Hitachi         K.Kitachi       Update          QC#53692
 *</pre>
 */
public class NSBL0040_NMAL6760 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // START 2019/10/02 K.Kitachi [QC#53692, ADD]
        if (NSBL0040Constant.BTN_CMN_CLOSE_EVENT_NM.equals(getLastGuard())) {
            return;
        }
        NSBL0040BMsg scrnMsg = (NSBL0040BMsg) bMsg;
        String xxScrEventNm = scrnMsg.xxScrEventNm.getValue();
        if (NSBL0040Constant.OPENWIN_BILLTO.equals(xxScrEventNm)) {
            NSBL0040CommonLogic.setReturnParamLocNm(scrnMsg.locNm_01, scrnMsg.xxPopPrm_01);
        }
        if (NSBL0040Constant.OPENWIN_SELLTO.equals(xxScrEventNm)) {
            NSBL0040CommonLogic.setReturnParamLocNm(scrnMsg.locNm_02, scrnMsg.xxPopPrm_01);
        }
        if (NSBL0040Constant.OPENWIN_SHIPTO.equals(xxScrEventNm)) {
            NSBL0040CommonLogic.setReturnParamLocNm(scrnMsg.locNm_03, scrnMsg.xxPopPrm_01);
        }
        // END 2019/10/02 K.Kitachi [QC#53692, ADD]
    }
}
