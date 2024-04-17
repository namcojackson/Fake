/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1050;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1050.NSAL1050CMsg;
import business.servlet.NSAL1050.common.NSAL1050CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * T&C Attributes Setup
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/01/20   Hitachi         K.Ochiai        Create          QC#16331
 *</pre>
 */
public class NSAL1050Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //do nothing

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1050BMsg scrnMsg = (NSAL1050BMsg) bMsg;

        NSAL1050CMsg bizMsg = new NSAL1050CMsg();
        bizMsg.setBusinessID("NSAL1050");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1050BMsg scrnMsg = (NSAL1050BMsg) bMsg;
        NSAL1050CMsg bizMsg = (NSAL1050CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        S21SortColumnIMGController.clearIMG("NSAL1050", scrnMsg, scrnMsg.A.no(0).getBaseContents());
        NSAL1050CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg);
        scrnMsg.xxChkBox_H.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.setFocusItem(scrnMsg.svcTermAttrbDescTxt);
        scrnMsg.A.setValidCount(0);
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

    }
}
