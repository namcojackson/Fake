/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3080;

import static business.servlet.NLBL3080.constant.NLBL3080Constant.DISP_HRCH_ACCT_CD_SHIP;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/20   CITS            H.Sugawara      Create          N/A
 * 2016/03/10   CITS            T.Tokutomi      Update          QC#5242
 *</pre>
 */
public class NLBL3080Scrn00_OpenWin_ShipToCustCode extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3080BMsg scrnMsg = (NLBL3080BMsg) bMsg;

        scrnMsg.P.no(0).xxPopPrm.clear();
        scrnMsg.P.no(1).xxPopPrm.clear();
        scrnMsg.P.no(2).xxPopPrm.clear();
        scrnMsg.P.no(3).xxPopPrm.clear();
        scrnMsg.P.no(4).xxPopPrm.clear();
        scrnMsg.P.no(5).xxPopPrm.clear();
        scrnMsg.P.no(6).xxPopPrm.clear();
        scrnMsg.P.no(7).xxPopPrm.clear();
        scrnMsg.P.no(8).xxPopPrm.clear();
        scrnMsg.P.no(9).xxPopPrm.clear();
        scrnMsg.P.no(10).xxPopPrm.clear();
        scrnMsg.P.no(11).xxPopPrm.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(12).xxPopPrm, DISP_HRCH_ACCT_CD_SHIP);
        scrnMsg.P.no(13).xxPopPrm.clear();
        scrnMsg.P.no(14).xxPopPrm.clear();
        scrnMsg.P.no(15).xxPopPrm.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(16).xxPopPrm, scrnMsg.shipToCustCd);
        scrnMsg.P.no(17).xxPopPrm.clear();
        scrnMsg.P.no(18).xxPopPrm.clear();
        scrnMsg.P.no(19).xxPopPrm.clear();
        scrnMsg.P.no(20).xxPopPrm.clear();
        scrnMsg.P.no(21).xxPopPrm.clear();
        scrnMsg.P.no(22).xxPopPrm.clear();
        scrnMsg.P.no(23).xxPopPrm.clear();
        scrnMsg.P.setValidCount(24);

        Object[] params = new Object[24];

        for (int j = 0; j < scrnMsg.P.getValidCount() && j < params.length; j++) {

            params[j] = scrnMsg.P.no(j).xxPopPrm;
        }

        setArgForSubScreen(params);
    }
}
