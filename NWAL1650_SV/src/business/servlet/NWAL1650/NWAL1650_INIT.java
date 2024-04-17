/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1650;

import static business.servlet.NWAL1650.constant.NWAL1650Constant.NWAL1650_PRM_CNT;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1650.NWAL1650CMsg;
import business.servlet.NWAL1650.common.NWAL1650CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/12   Fujitsu         M.Yamada        Create          N/A
 * 2015/02/23   Fujitsu         M.suzuki        Update          #2140
 *</pre>
 */
public class NWAL1650_INIT extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1650BMsg scrnMsg = (NWAL1650BMsg) bMsg;

        NWAL1650CMsg bizMsg = new NWAL1650CMsg();

        // IN Parameter Get
        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null && params.length == NWAL1650_PRM_CNT) {
            NWAL1650CommonLogic.getInputParam(scrnMsg, params);
        }

        bizMsg.setBusinessID("NWAL1650");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1650BMsg scrnMsg = (NWAL1650BMsg) bMsg;
        NWAL1650CMsg bizMsg = (NWAL1650CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL1650CommonLogic.protectedInput(scrnMsg);
        NWAL1650CommonLogic.setColumnWidth(scrnMsg);
        // MOD START 2016/02/03 #2140
        NWAL1650CommonLogic.initCommonButton(this, scrnMsg);
        // MOD START 2016/02/03 #2140
    }
}
