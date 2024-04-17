/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0740;

import static business.servlet.NSAL0740.common.NSAL0740CommonLogic.initialControlScreen;
import static business.servlet.NSAL0740.constant.NSAL0740Constant.BUSINESS_ID;
import static business.servlet.NSAL0740.constant.NSAL0740Constant.FUCNTION_CD_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0740.NSAL0740CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/12/05   Hitachi         T.Mizuki        Create          QC#4210
 *</pre>
 */
public class NSAL0740Scrn00_PageNext extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0740BMsg scrnMsg = (NSAL0740BMsg) bMsg;

        NSAL0740CMsg bizMsg = new NSAL0740CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUCNTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0740BMsg scrnMsg = (NSAL0740BMsg) bMsg;
        NSAL0740CMsg bizMsg  = (NSAL0740CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        initialControlScreen(this, scrnMsg);

    }
}
