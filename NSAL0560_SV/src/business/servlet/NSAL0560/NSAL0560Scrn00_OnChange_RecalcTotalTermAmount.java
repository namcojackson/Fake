/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0560;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NSAL0560.common.NSAL0560CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/21   Hitachi         K.Kitachi       Create          QC#20061
 *</pre>
 */
public class NSAL0560Scrn00_OnChange_RecalcTotalTermAmount extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0560BMsg scrnMsg = (NSAL0560BMsg) bMsg;
        int targetRow = getButtonSelectNumber();
        scrnMsg.addCheckItem(scrnMsg.A.no(targetRow).basePrcTermDealAmtRate_A1);
        if (scrnMsg.A.no(targetRow).basePrcTermDealAmtRate_A1.isError()) {
            scrnMsg.basePrcTermDealAmtRate_H1.clear();
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0560BMsg scrnMsg = (NSAL0560BMsg) bMsg;
        NSAL0560CommonLogic.calcTotTermAmt(scrnMsg);
    }
}
