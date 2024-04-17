/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6140;

import static business.servlet.NMAL6140.constant.NMAL6140Constant.COL_CD;
import static business.servlet.NMAL6140.constant.NMAL6140Constant.COL_NM;
import static business.servlet.NMAL6140.constant.NMAL6140Constant.DISP_CD;
import static business.servlet.NMAL6140.constant.NMAL6140Constant.DISP_NM;
import static business.servlet.NMAL6140.constant.NMAL6140Constant.POP_LG;
import static business.servlet.NMAL6140.constant.NMAL6140Constant.POP_NM;
import static business.servlet.NMAL6140.constant.NMAL6140Constant.TBL;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL6140Scrn00_OpenWin_Ctry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/17   Fujitsu         C.Tanaka        Create          N/A
 *</pre>
 */
public class NMAL6140Scrn00_OpenWin_Ctry extends S21CommonHandler {

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

        NMAL6140BMsg scrnMsg = (NMAL6140BMsg) bMsg;

        scrnMsg.P.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, TBL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm, COL_CD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm, COL_NM);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(3).xxPopPrm, COL_CD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(4).xxPopPrm, POP_NM);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(5).xxPopPrm, DISP_CD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(6).xxPopPrm, DISP_NM);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(7).xxPopPrm, DISP_CD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(8).xxPopPrm, DISP_NM);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(9).xxPopPrm, scrnMsg.ctryCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(10).xxPopPrm, scrnMsg.ctryNm);

        Object[] params = new Object[POP_LG];
        for (int i = 0; i < POP_LG; i++) {
            params[i] = scrnMsg.P.no(i).xxPopPrm;
        }

        setArgForSubScreen(params);
    }
}
