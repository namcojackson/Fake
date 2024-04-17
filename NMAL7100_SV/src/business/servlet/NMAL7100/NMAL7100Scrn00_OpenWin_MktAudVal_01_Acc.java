/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7100;

import static business.servlet.NMAL7100.constant.NMAL7100Constant.EVT_OPENWIN_MKTAUDVAL_01_ACC;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL7100.NMAL7100CMsg;
//import business.servlet.NMAL7100.constant.NMAL7100Constant;

import business.servlet.NMAL7100.common.NMAL7100CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/07/2015   Fujitsu         M.Hara          Create          N/A
 *</pre>
 */
public class NMAL7100Scrn00_OpenWin_MktAudVal_01_Acc extends S21CommonHandler {

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
        //
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm_H1, EVT_OPENWIN_MKTAUDVAL_01_ACC);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxCellIdx_H1, NMAL7100CommonLogic.convToBigDecimal(rowIdx));

        Object[] argParam = NMAL7100CommonLogic.createArgumentNMWAL6760(scrnMsg.X.no(rowIdx).xxScrItem30Txt_X1, scrnMsg.X.no(rowIdx).xxRecNmTxt_X1);

        if (argParam != null) {
            setArgForSubScreen(argParam);
        }
    }
}
