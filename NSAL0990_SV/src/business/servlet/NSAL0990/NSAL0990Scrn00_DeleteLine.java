/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0990;

import static business.servlet.NSAL0990.constant.NSAL0990Constant.BIZ_ID;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0990.NSAL0990CMsg;
import business.servlet.NSAL0990.common.NSAL0990CommonLogic;
import business.servlet.NSAL0990.constant.NSAL0990Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/20   Hitachi         K.Kasai         Create          N/A
 * 2016/04/27   Hitachi         K.Kasai         Update          QC#7380
 * 2016/07/06   Hitachi         O.Okuma         Update          QC#9630
 *</pre>
 */
public class NSAL0990Scrn00_DeleteLine extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0990BMsg scrnMsg = (NSAL0990BMsg) bMsg;
        boolean errFlg = true;
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.C.no(i).xxChkBox_C.getValue())) {
                errFlg = false;
                break;
            }
        }
        if (errFlg) {
            scrnMsg.setMessageInfo(NSAL0990Constant.NSAM0015E);
        }
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0990BMsg scrnMsg = (NSAL0990BMsg) bMsg;

        NSAL0990CMsg bizMsg = new NSAL0990CMsg();
        bizMsg.setBusinessID("NSAL0990");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0990BMsg scrnMsg = (NSAL0990BMsg) bMsg;
        NSAL0990CMsg bizMsg  = (NSAL0990CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_ID);
        NSAL0990CommonLogic.activateButtons(this, scrnMsg, functionList);
        NSAL0990CommonLogic.activateButtonsForAdd(this, scrnMsg, functionList);
        scrnMsg.putErrorScreen();
        scrnMsg.setFocusItem(scrnMsg.mdseCd);
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        NSAL0990CommonLogic.formatItem(scrnMsg);
        NSAL0990CommonLogic.alternateTableRowColor(scrnMsg);
        NSAL0990CommonLogic.setXxDplyCtrlFlg(scrnMsg);
    }
}
