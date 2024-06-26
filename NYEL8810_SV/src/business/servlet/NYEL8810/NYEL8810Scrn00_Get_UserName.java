/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8810;

import static business.servlet.NYEL8810.constant.NYEL8810Constant.BIZ_ID;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.MESSAGE_KIND_ERROR;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NYEL8810.NYEL8810CMsg;
//import business.servlet.NYEL8810.constant.NYEL8810Constant;

import business.blap.NYEL8810.NYEL8810CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NYEL8810Scrn00_Get_UserName
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/02   Fujitsu         Q09079          Create          N/A
 *</pre>
 */
public class NYEL8810Scrn00_Get_UserName extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NYEL8810BMsg scrnMsg = (NYEL8810BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.usrId);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NYEL8810BMsg scrnMsg = (NYEL8810BMsg) bMsg;
        
        NYEL8810CMsg bizMsg = new NYEL8810CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NYEL8810BMsg scrnMsg = (NYEL8810BMsg) bMsg;
        NYEL8810CMsg bizMsg  = (NYEL8810CMsg) cMsg;
        
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.addCheckItem(scrnMsg.usrId);
        scrnMsg.putErrorScreen();

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        scrnMsg.setFocusItem(scrnMsg.usrId);


    }
}
