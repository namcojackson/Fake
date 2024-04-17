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
 * 02/11/2016   CSAI            D.Fukaya        Create          QC# 1598
 *</pre>
 */
public class NMAL6820Scrn00_OnChange_SourcingDefaultEmerSource extends S21CommonHandler {

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
        NMAL6820CommonLogic.cntrlSourcePullDownListForSourcingTab(scrnMsg);
//        NMAL6820CommonLogic.clearSourcePullDownListForSourcingTab(scrnMsg);
        scrnMsg.prntVndNm_SE.clear();
        scrnMsg.vndNm_SE.clear();
        scrnMsg.setFocusItem(scrnMsg.procrTpCd_E1);
    }
}
