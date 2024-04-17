/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1190;

import static business.servlet.NSAL1190.constant.NSAL1190Constant.NSAL1190_INTG_MDSE_ITEM_TP_CD;
import static business.servlet.NSAL1190.constant.NSAL1190Constant.PARAM_LENGTH_NMAL6800;
import static business.servlet.NSAL1190.constant.NSAL1190Constant.XX_MODE_CD_10;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/15   Hitachi         O.Okuma         Create          N/A
 *</pre>
 */
public class NSAL1190Scrn00_OpenWin_ItemMstrPop extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1190BMsg scrnMsg = (NSAL1190BMsg) bMsg;

        // event line
        final int eventLine = getButtonSelectNumber();
        scrnMsg.xxNum_EV.setValue(eventLine);

        String mdseItemTpCd = ZYPCodeDataUtil.getVarCharConstValue(NSAL1190_INTG_MDSE_ITEM_TP_CD, scrnMsg.glblCmpyCd.getValue());

        setValue(scrnMsg.xxPopPrm_00, scrnMsg.A.no(eventLine).intgMdseCd_A);
        scrnMsg.xxPopPrm_01.clear();
        setValue(scrnMsg.xxPopPrm_02, mdseItemTpCd);
        scrnMsg.xxPopPrm_03.clear();
        scrnMsg.xxPopPrm_04.clear();
        scrnMsg.xxPopPrm_05.clear();
        scrnMsg.xxPopPrm_06.clear();
        scrnMsg.xxPopPrm_07.clear();
        scrnMsg.xxPopPrm_08.clear();
        setValue(scrnMsg.xxPopPrm_09, XX_MODE_CD_10);

        Object[] params = new Object[PARAM_LENGTH_NMAL6800];
        int i = 0;
        params[i++] = scrnMsg.xxPopPrm_00;
        params[i++] = scrnMsg.xxPopPrm_01;
        params[i++] = scrnMsg.xxPopPrm_02;
        params[i++] = scrnMsg.xxPopPrm_03;
        params[i++] = scrnMsg.xxPopPrm_04;
        params[i++] = scrnMsg.xxPopPrm_05;
        params[i++] = scrnMsg.xxPopPrm_06;
        params[i++] = scrnMsg.xxPopPrm_07;
        params[i++] = scrnMsg.xxPopPrm_08;
        params[i++] = scrnMsg.xxPopPrm_09;
        setArgForSubScreen(params);
    }
}
