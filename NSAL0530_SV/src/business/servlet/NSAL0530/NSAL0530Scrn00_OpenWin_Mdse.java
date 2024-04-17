/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0530;

import static business.servlet.NSAL0530.constant.NSAL0530Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NSAL0530.common.NSAL0530CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Solution Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/12   Hitachi         T.Tomita        Create          N/A
 * 2016/03/01   Hitachi         K.Kasai         Update          QC#3586
 *</pre>
 */
public class NSAL0530Scrn00_OpenWin_Mdse extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0530BMsg scrnMsg = (NSAL0530BMsg) bMsg;
        NSAL0530CommonLogic.commonAddCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0530BMsg scrnMsg = (NSAL0530BMsg) bMsg;

        scrnMsg.mdseCd_P2.clear();
        // mod start 2016/03/01 CSA Defect#3586
        scrnMsg.xxPopPrm_01.clear();
        scrnMsg.xxPopPrm_02.clear();
        scrnMsg.xxPopPrm_03.clear();
        scrnMsg.xxPopPrm_04.clear();
        scrnMsg.xxPopPrm_05.clear();
        scrnMsg.xxPopPrm_06.clear();
        // mod end 2016/03/01 CSA Defect#3586

        setValue(scrnMsg.mdseCd_P2, scrnMsg.mdseCd_H);

        // mod start 2016/03/01 CSA Defect#3586
        Object[] args = new Object[NMAL6800_PRM_LENGTH];

        args[0] = scrnMsg.mdseCd_P2;
        args[1] = scrnMsg.xxPopPrm_01;
        args[2] = scrnMsg.xxPopPrm_02;
        args[3] = scrnMsg.xxPopPrm_03;
        args[4] = scrnMsg.xxPopPrm_04;
        args[5] = scrnMsg.xxPopPrm_05;
        args[6] = scrnMsg.xxPopPrm_06;
        // mod end 2016/03/01 CSA Defect#3586

        setArgForSubScreen(args);
    }
}
