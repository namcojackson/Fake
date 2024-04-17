/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFAL0220;

import static business.servlet.NFAL0220.constant.NFAL0220Constant.BIZ_ID;
import static business.servlet.NFAL0220.constant.NFAL0220Constant.PRM_NMAL2550;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFAL0220.common.NFAL0220CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NFAL0220Scrn00_OpenWin_GlCombnSearch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/03   Hitachi         J.Kim           Create          N/A
 * 2016/07/13   Hitachi         J.Kim           Create          QC#10324
 *</pre>
 */
public class NFAL0220Scrn00_OpenWin_GlCombnSearch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL0220BMsg scrnMsg = (NFAL0220BMsg) bMsg;

        int lineNum = getButtonSelectNumber();
        if (!NFAL0220CommonLogic.checkSearchPopupScreen(scrnMsg, lineNum)) {
            for (int i = 0; i < scrnMsg.A.length(); i++) {
                NFAL0220_ABMsg abMsg = scrnMsg.A.no(i);
                scrnMsg.addCheckItem(abMsg.xxScrItem61Txt_A);
                scrnMsg.putErrorScreen();
            }
        }

        Object[] params = new Object[PRM_NMAL2550];
        int index = 0;
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, BIZ_ID);
        params[index++] = scrnMsg.P.no(0).xxPopPrm;
        params[index++] = scrnMsg.P.no(1).xxPopPrm;
        params[index++] = scrnMsg.P.no(2).xxPopPrm;
        params[index++] = scrnMsg.P.no(3).xxPopPrm;
        params[index++] = scrnMsg.P.no(4).xxPopPrm;
        params[index++] = scrnMsg.P.no(5).xxPopPrm;
        params[index++] = scrnMsg.P.no(6).xxPopPrm;
        params[index++] = scrnMsg.P.no(7).xxPopPrm;
        params[index++] = scrnMsg.P.no(8).xxPopPrm;
        params[index++] = scrnMsg.P.no(9).xxPopPrm;
        params[index++] = scrnMsg.P.no(10).xxPopPrm;
        //START 2017/03/15 E.Kameishi [QC#15854,ADD]
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.glSendCpltFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(10).xxPopPrm, ZYPConstant.FLG_ON_Y);
        }
        //END 2017/03/15 E.Kameishi [QC#15854,ADD]
        // Sub screen transition

        setArgForSubScreen(params);
    }
}
