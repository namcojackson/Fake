/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6720;

import static business.servlet.NMAL6720.constant.NMAL6720Constant.EVENT_SHIP_TO_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL6720.common.NMAL6720CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/15   Fujitsu         C.Tanaka        Create          CSA
 * 2016/04/13   SRAA            Y.Chen          Update          QC#6011
 *</pre>
 */
public class NMAL6720Scrn00_OpenWin_ShipToSearch extends S21CommonHandler {

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

        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;

        scrnMsg.P.clear();

        scrnMsg.xxScrEventNm_P.setValue(EVENT_SHIP_TO_SEARCH);

        scrnMsg.dsAcctNm_P1.clear();

        scrnMsg.P.no(0).xxPopPrm_P.setValue(scrnMsg.dsAcctNum_H1.getValue());
        int index = getButtonSelectNumber();
        if (0 <= index) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(16).xxPopPrm_P, scrnMsg.D.no(index).dsDefShipToCd_D1.getValue());
        }
        scrnMsg.P.no(12).xxPopPrm_P.setValue("03"); // Ship To Only
        scrnMsg.P.no(24).xxPopPrm_P.setValue(ZYPConstant.FLG_OFF_N);
        scrnMsg.P.no(25).xxPopPrm_P.setValue(ZYPConstant.FLG_OFF_N);
        scrnMsg.P.no(33).xxPopPrm_P.setValue(ZYPConstant.FLG_ON_Y); // Category:
                                                                    // Active

        Object[] params = NMAL6720CommonLogic.toArray_popup(scrnMsg.P, 35);
        params[1] = scrnMsg.dsAcctNm_P1;

        setArgForSubScreen(params);
    }
}
