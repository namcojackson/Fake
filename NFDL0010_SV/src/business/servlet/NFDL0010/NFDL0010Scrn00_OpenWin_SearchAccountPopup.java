/**
 * <Pre>Copyright(c)2012 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFDL0010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Collection Summary
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 2016/09/12   Hitachi         K.Kojima        Update          QC#13257
 *</pre>
 */
public class NFDL0010Scrn00_OpenWin_SearchAccountPopup extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0010BMsg scrnMsg = (NFDL0010BMsg) bMsg;

        // START 2016/09/12 K.Kojima [QC#13257,DEL]
        // NFDL0010CommonLogic.initialize(this, scrnMsg);
        //
        // Object[] params = setPopupParameter(scrnMsg);
        // setArgForSubScreen(params);
        // END 2016/09/12 K.Kojima [QC#13257,DEL]

        // START 2016/09/12 K.Kojima [QC#13257,ADD]
        scrnMsg.M.clear();
        scrnMsg.M.no(0).xxPopPrm.clear();
        scrnMsg.M.no(1).xxPopPrm.clear();
        scrnMsg.M.no(2).xxPopPrm.clear();
        scrnMsg.M.no(3).xxPopPrm.clear();
        scrnMsg.M.no(4).xxPopPrm.clear();
        scrnMsg.M.no(5).xxPopPrm.clear();
        scrnMsg.M.no(6).xxPopPrm.clear();
        scrnMsg.M.no(7).xxPopPrm.clear();
        scrnMsg.M.no(8).xxPopPrm.clear();
        scrnMsg.M.no(9).xxPopPrm.clear();
        scrnMsg.M.no(10).xxPopPrm.clear();
        scrnMsg.M.no(11).xxPopPrm.clear();
        scrnMsg.M.no(12).xxPopPrm.clear();
        scrnMsg.M.no(13).xxPopPrm.clear();
        scrnMsg.M.no(14).xxPopPrm.clear();
        scrnMsg.M.no(15).xxPopPrm.clear();
        scrnMsg.M.no(16).xxPopPrm.clear();

        // NMAL6760
        Object[] param = new Object[17];
        param[0] = scrnMsg.M.no(0).xxPopPrm;
        param[1] = scrnMsg.M.no(1).xxPopPrm;
        param[2] = scrnMsg.M.no(2).xxPopPrm;
        param[3] = scrnMsg.M.no(3).xxPopPrm;
        param[4] = scrnMsg.M.no(4).xxPopPrm;
        param[5] = scrnMsg.M.no(5).xxPopPrm;
        param[6] = scrnMsg.M.no(6).xxPopPrm;
        param[7] = scrnMsg.M.no(7).xxPopPrm;
        param[8] = scrnMsg.M.no(8).xxPopPrm;
        param[9] = scrnMsg.M.no(9).xxPopPrm;
        param[10] = scrnMsg.M.no(10).xxPopPrm;
        param[11] = scrnMsg.M.no(11).xxPopPrm;
        param[12] = scrnMsg.M.no(12).xxPopPrm;
        param[13] = scrnMsg.M.no(13).xxPopPrm;
        param[14] = scrnMsg.M.no(14).xxPopPrm;
        param[15] = scrnMsg.M.no(15).xxPopPrm;
        param[16] = scrnMsg.M.no(16).xxPopPrm;

        setArgForSubScreen(param);
        // END 2016/09/12 K.Kojima [QC#13257,ADD]

    }
}
