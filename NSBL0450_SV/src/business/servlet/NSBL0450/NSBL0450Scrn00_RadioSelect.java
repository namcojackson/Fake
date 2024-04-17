/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0450;

import static business.servlet.NSBL0450.common.NSBL0450CommonLogic.initialControlScreen;
import static business.servlet.NSBL0450.common.NSBL0450CommonLogic.checkInputForDetail;
import static business.servlet.NSBL0450.common.NSBL0450CommonLogic.reflectToLine;
import static business.servlet.NSBL0450.common.NSBL0450CommonLogic.showDetail;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMessageInfo;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Supplemental Task
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/05   Hitachi         T.Iwamoto         Create          N/A
 *</pre>
 */
public class NSBL0450Scrn00_RadioSelect extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSBL0450BMsg scrnMsg = (NSBL0450BMsg) bMsg;
        checkInputForDetail(this, scrnMsg, ctx.getEventName());
        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_A, scrnMsg.xxRowNum_D);
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0450BMsg scrnMsg = (NSBL0450BMsg) bMsg;
        reflectToLine(this, scrnMsg);

        initialControlScreen(this, scrnMsg);
        int targetRow = scrnMsg.xxRadioBtn_A.getValueInt();
        showDetail(this, scrnMsg, targetRow);
        scrnMsg.setFocusItem(scrnMsg.A.no(targetRow).svcSupplTaskNum_A);

    }
}
