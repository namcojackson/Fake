/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7100;

import static business.servlet.NMAL7100.constant.NMAL7100Constant.EVT_OPENWIN_PRMOQLFY_ITEM;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7100.common.NMAL7100CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/12/2015   Fujitsu         T.Murai         Create          #4032
 *</pre>
 */
public class NMAL7100Scrn00_OpenWin_PrmoQlfy_Item extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL7100BMsg scrnMsg = (NMAL7100BMsg) bMsg;
        int rowIdx = getButtonSelectNumber();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm_H1, EVT_OPENWIN_PRMOQLFY_ITEM);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxCellIdx_H1, NMAL7100CommonLogic.convToBigDecimal(rowIdx));

        Object[] argParam = NMAL7100CommonLogic.createArgumentNMWAL6800(scrnMsg, scrnMsg.xxPopPrm_0, scrnMsg.A.no(rowIdx).prcQlfyValTxt_DA);

        if (argParam != null) {
            setArgForSubScreen(argParam);
        }
    }
}
