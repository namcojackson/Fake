/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6700;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL6700.constant.NMAL6700Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/07   SRAA            Y.Chen          Create          QC#4708
 *</pre>
 */
public class NMAL6700Scrn00_OpenWin_CtacSearch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL6700BMsg scrnMsg = (NMAL6700BMsg) bMsg;

        scrnMsg.xxScrEventNm.setValue(NMAL6700Constant.BTN_OPEN_WIN_CTAC_SEARCH);

        scrnMsg.P.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(3).xxPopPrm, scrnMsg.dsAcctNum_H1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(11).xxPopPrm, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(21).xxPopPrm, ZYPConstant.FLG_ON_Y);

        Object[] params = new Object[24];
        params[0] = scrnMsg.P.no(0).xxPopPrm;
        params[1] = scrnMsg.P.no(1).xxPopPrm;
        params[2] = scrnMsg.dsAcctNm_H1;
        params[3] = scrnMsg.P.no(3).xxPopPrm;
        params[4] = scrnMsg.P.no(4).xxPopPrm;
        params[5] = scrnMsg.P.no(5).xxPopPrm;
        params[6] = scrnMsg.P.no(6).xxPopPrm;
        params[7] = scrnMsg.P.no(7).xxPopPrm;
        params[8] = scrnMsg.P.no(8).xxPopPrm;
        params[9] = scrnMsg.P.no(9).xxPopPrm;
        params[10] = scrnMsg.P.no(10).xxPopPrm;
        params[11] = scrnMsg.P.no(11).xxPopPrm;
        params[12] = scrnMsg.P.no(12).xxPopPrm;
        params[13] = scrnMsg.P.no(13).xxPopPrm;
        params[14] = scrnMsg.P.no(14).xxPopPrm;
        params[15] = scrnMsg.ctacPsnPk_15;
        params[16] = scrnMsg.ctacPsnPk_16;
        params[17] = scrnMsg.ctacPsnPk_17;
        // params[18] = null;
        params[19] = scrnMsg.ctacPsnPk_DH;
        params[20] = scrnMsg.P.no(20).xxPopPrm;
        params[21] = scrnMsg.P.no(21).xxPopPrm;
        params[22] = scrnMsg.P.no(22).xxPopPrm;
        params[23] = scrnMsg.P.no(23).xxPopPrm;

        setArgForSubScreen(params);
    }
}
