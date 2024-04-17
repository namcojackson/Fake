/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0390;

import java.util.List;
import static business.servlet.NSBL0390.constant.NSBL0390Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0390.NSBL0390CMsg;
import business.servlet.NSBL0390.common.NSBL0390CommonLogic;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/13   Hitachi         O.Okuma         Create          N/A
 * 2016/07/12   Hitachi         O.Okuma         Update          QC#11685
 *</pre>
 */
public class NSBL0390Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0390BMsg scrnMsg = (NSBL0390BMsg) bMsg;
        NSBL0390CMsg bizMsg = new NSBL0390CMsg();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;  }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0390BMsg scrnMsg = (NSBL0390BMsg) bMsg;
        NSBL0390CMsg bizMsg  = (NSBL0390CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_ID);

        NSBL0390CommonLogic.activateButtons(this, functionList);
        NSBL0390CommonLogic.activateScreenItems(scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.svcModYr);
    }
}
