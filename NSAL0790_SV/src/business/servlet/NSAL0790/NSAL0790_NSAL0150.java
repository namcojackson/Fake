/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0790;

import static business.servlet.NSAL0790.constant.NSAL0790Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL0790.NSAL0790CMsg;
import business.servlet.NSAL0790.common.NSAL0790CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Fleet Rollup Detail
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/22   Hitachi         A.Kohinata      Create          N/A
 * 2017/06/06   Hitachi         A.Kohinata      Update          QC#18770
 *</pre>
 */
public class NSAL0790_NSAL0150 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // mod start 2017/06/05 CSA Defect#18770
        NSAL0790BMsg scrnMsg = (NSAL0790BMsg) bMsg;
        NSAL0790CMsg bizMsg = new NSAL0790CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
        // mod end 2017/06/05 CSA Defect#18770
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // add start 2017/06/05 CSA Defect#18770
        NSAL0790BMsg scrnMsg = (NSAL0790BMsg) bMsg;
        NSAL0790CMsg bizMsg = (NSAL0790CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0790CommonLogic.initialControlScreen(this, scrnMsg);
        // add end 2017/06/05 CSA Defect#18770
    }
}
