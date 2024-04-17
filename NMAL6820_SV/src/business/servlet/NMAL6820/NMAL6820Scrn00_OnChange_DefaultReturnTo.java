/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6820;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL6820.common.NMAL6820CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/04/2016   CSAI            D.Fukaya        Create          QC# 2380
 * 02/11/2016   CSAI            D.Fukaya        Update          QC# 1598
 *</pre>
 */
public class NMAL6820Scrn00_OnChange_DefaultReturnTo extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6820BMsg scrnMsg = (NMAL6820BMsg) bMsg;
        int eventRowIndex = getButtonSelectNumber();
        NMAL6820CommonLogic.cntrlSourcePullDownListForSWHTab(this, scrnMsg);
//        NMAL6820CommonLogic.clearSourcePullDownListForSWHTab(scrnMsg);
        scrnMsg.A.no(eventRowIndex).prntVndNm_AR.clear();
        scrnMsg.A.no(eventRowIndex).vndNm_AR.clear();
        scrnMsg.setFocusItem(scrnMsg.A.no(eventRowIndex).procrTpCd_A2);
    }
}
