/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2600;

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
 * 2015/12/01   Fujitsu         K.Koitabashi    Create          N/A
 *</pre>
 */
public class NMAL2600Scrn00_OnClickTreeViewNode extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL2600BMsg scrnMsg = (NMAL2600BMsg) bMsg;

        Parameters params = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String clickedTreeViewNodePath = params.getSingleValue("clickedTreeViewNodePath");
        scrnMsg.getTreeView().nodeExpandControl(clickedTreeViewNodePath);

    }
}
