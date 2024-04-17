/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7290;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL7290.NMAL7290CMsg;
//import business.servlet.NMAL7290.constant.NMAL7290Constant;

import business.servlet.NMAL7290.common.NMAL7290CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/08   Fujitsu         Y.Kanefusa      Update          S21_NA#9694
 *</pre>
 */
public class NMAL7290Scrn00_OnChange_PrcdAction extends S21CommonHandler {

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

        NMAL7290BMsg scrnMsg = (NMAL7290BMsg) bMsg;
        NMAL7290CommonLogic.ctrlInputProp(scrnMsg);

    }
}
