/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7100;

import static business.servlet.NMAL7100.constant.NMAL7100Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NMAL7100.common.NMAL7100CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7100Scrn00_OpenWin_PrcCatg
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/24   Fujitsu         W.Honda         Create          CSA-QC#4043
 *</pre>
 */
public class NMAL7100Scrn00_OpenWin_PrcCatg extends S21CommonHandler {

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
        NMAL7100BMsg scrnMsg = (NMAL7100BMsg) bMsg;
        int rowIdx = getButtonSelectNumber();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm_H1, EVT_OPENWIN_PRCCATG);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxCellIdx_H1, NMAL7100CommonLogic.convToBigDecimal(rowIdx));
        String prcListTpCd = scrnMsg.Y.no(rowIdx).prcListTpCd_CX.getValue();

        Object[] argParam = NMAL7100CommonLogic.createArgumentNWAL1130ForPrcCatg(scrnMsg, rowIdx, prcListTpCd, getGlobalCompanyCode());

        if (argParam != null) {
            setArgForSubScreen(argParam);
        }
    }
}
