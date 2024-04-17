/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7300;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7300.common.NMAL7300CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/11/10   Fujitsu         Y.Kanefusa      Create          N/A
 *</pre>
 */
public class NMAL7300Scrn00_Add_QtyDisc extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7300BMsg scrnMsg = (NMAL7300BMsg) bMsg;

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.B.no(i).qtyDiscDtlQty_B);
            if(ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxYesNoCd.getValue())){
                scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleDtlTxt_B);
            }else{
                scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleDtlRate_B);
            }
        }
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7300BMsg scrnMsg = (NMAL7300BMsg) bMsg;

        NMAL7300CommonLogic.addNewLine(scrnMsg);

        NMAL7300CommonLogic.setDetailProtect(scrnMsg);

    }
}
