/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/03/08   Hitachi         Y.Takeno        Create          QC#22755
 * 2019/07/18   Hitachi         Y.Takeno        Update          QC#51675
 *</pre>
 */
public class NFBL2040Scrn00_OnClick_PoNumLink extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;

        // START 2019/07/18 [QC#51675, ADD]
        scrnMsg.xxWrnSkipFlg.clear();
        // END   2019/07/18 [QC#51675, ADD]

        Object[] params = new Object[1];
        params[0] = scrnMsg.A.no(getButtonSelectNumber()).poNum_A1.getValue();

        setArgForSubScreen(params);
    }
}
