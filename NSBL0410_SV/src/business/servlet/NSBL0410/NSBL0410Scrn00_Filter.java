/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0410;

import static business.servlet.NSBL0410.common.NSBL0410CommonLogic.addCheckItem;
import static business.servlet.NSBL0410.common.NSBL0410CommonLogic.initialControlScreen;
import static business.servlet.NSBL0410.common.NSBL0410CommonLogic.setFocus;
import static business.servlet.NSBL0410.constant.NSBL0410Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0410.NSBL0410CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/15   Hitachi         M.Gotou         Create          N/A
 * 2016/07/13   Hitachi         O.Okuma         Update          QC#11685
 * 2016/07/26   Hitachi         O.Okuma         Update          QC#11681
 * 2018/07/10   Fujitsu         T.Ogura         Update          QC#27080
 *</pre>
 */
public class NSBL0410Scrn00_Filter extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0410BMsg scrnMsg = (NSBL0410BMsg) bMsg;

        NSBL0410CMsg bizMsg = new NSBL0410CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0410BMsg scrnMsg = (NSBL0410BMsg) bMsg;
        NSBL0410CMsg bizMsg  = (NSBL0410CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        initialControlScreen(this, scrnMsg);
        addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
        setFocus(scrnMsg);
        // START 2018/07/10 T.Ogura [QC#27080,MOD]
//        if ("E".equals(bizMsg.getMessageKind()) || ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
//            return;
//        }
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        // END   2018/07/10 T.Ogura [QC#27080,MOD]
    }
}
