/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1130;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * NPAL1130 Replenishment Plan Inquiry (Detail)
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/05/21   Hitachi         A.Kohinata      Create          N/A
 * 2016/03/01   CITS            K.Masaki        Update          CSA Project
 *</pre>
 */
public class NPAL1130_NPAL1010 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1130BMsg scrnMsg = (NPAL1130BMsg) bMsg;
        if (ZYPCommonFunc.hasValue(scrnMsg.P.no(1).xxPopPrm)) {
            scrnMsg.rtlWhCd.setValue(scrnMsg.P.no(6).xxPopPrm.getValue());
            scrnMsg.rtlWhNm.setValue(scrnMsg.P.no(7).xxPopPrm.getValue());
            scrnMsg.rtlSwhCd.setValue(scrnMsg.P.no(8).xxPopPrm.getValue());
            scrnMsg.rtlSwhNm.setValue(scrnMsg.P.no(9).xxPopPrm.getValue());
        }
        scrnMsg.setFocusItem(scrnMsg.rtlWhCd);
    }
}
