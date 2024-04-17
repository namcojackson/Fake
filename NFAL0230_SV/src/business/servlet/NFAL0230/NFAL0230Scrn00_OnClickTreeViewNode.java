/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFAL0230;

import static business.servlet.NFAL0230.constant.NFAL0230Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/23   Fujitsu         T.Murai         Create          N/A
 *</pre>
 */
public class NFAL0230Scrn00_OnClickTreeViewNode extends S21CommonHandler {

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

        NFAL0230BMsg scrnMsg = (NFAL0230BMsg) bMsg;

        Parameters params = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String clickedTreeViewName = params.getSingleValue("clickedTreeViewName");
        String clickedTreeViewNodePath = params.getSingleValue("clickedTreeViewNodePath");
        if (TREEVIEWMOVEFTO.equals(clickedTreeViewName)) {
            scrnMsg.getTreeView().nodeExpandControl(clickedTreeViewNodePath);
        }
    }
}
