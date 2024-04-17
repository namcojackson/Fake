/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6710;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL6710.NMAL6710CMsg;
//import business.servlet.NMAL6710.constant.NMAL6710Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NMAL6710Scrn00_OnClickTreeViewNode extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NMAL6710BMsg scrnMsg = (NMAL6710BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NMAL6710BMsg scrnMsg = (NMAL6710BMsg) bMsg;

        //NMAL6710CMsg bizMsg = new NMAL6710CMsg();
        //bizMsg.setBusinessID("NMAL6710");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6710BMsg scrnMsg = (NMAL6710BMsg) bMsg;

        Parameters params = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String clickedTreeViewNodePath = params.getSingleValue("clickedTreeViewNodePath");

        scrnMsg.getTreeView().nodeExpandControl(clickedTreeViewNodePath);

    }
}
