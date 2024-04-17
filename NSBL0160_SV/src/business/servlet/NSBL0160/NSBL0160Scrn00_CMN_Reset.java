/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0160;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0160.NSBL0160CMsg;
import business.servlet.NSBL0160.common.NSBL0160CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 *
 * Memo Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/29   Hitach          T.Tomita        Create          N/A
 * 2016/02/26   Hitachi         T.Tomita        Update          QC#3689
 * 2017/08/09   Hitachi         U.Kim           Update          QC#18151
 *</pre>
 */
public class NSBL0160Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0160BMsg scrnMsg = (NSBL0160BMsg) bMsg;

        NSBL0160CMsg bizMsg = new NSBL0160CMsg();
        bizMsg.setBusinessID("NSBL0160");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0160BMsg scrnMsg = (NSBL0160BMsg) bMsg;
        NSBL0160CMsg bizMsg  = (NSBL0160CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // mod start 2016/02/26 CSA Defect#3689
        scrnMsg.setFocusItem(scrnMsg.svcMemoRsnCd_SC);
        // mod end 2016/02/26 CSA Defect#3689
        // START 2017/08/09 U.Kim [QC#18151, ADD]
        NSBL0160CommonLogic.setProtected(scrnMsg, this);
        // END 2017/08/09 U.Kim [QC#18151, ADD]
    }
}
