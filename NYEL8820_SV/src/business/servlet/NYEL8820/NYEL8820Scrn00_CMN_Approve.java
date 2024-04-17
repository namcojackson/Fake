/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8820;

import static business.servlet.NYEL8820.constant.NYEL8820Constant.BIZ_ID;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.MESSAGE_KIND_ERROR;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NYEL8820.NYEL8820CMsg;
import business.servlet.NYEL8820.common.NYEL8820CommonLogic;
import business.servlet.NYEL8840.NYEL8840BMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NYEL8820Scrn00_CMN_Approve
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/17   Fujitsu         Q09079          Create          N/A
 *</pre>
 */
public class NYEL8820Scrn00_CMN_Approve extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NYEL8820BMsg scrnMsg = (NYEL8820BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.wfCmntTxt);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NYEL8820BMsg scrnMsg = (NYEL8820BMsg) bMsg;
        
        NYEL8820CMsg bizMsg = new NYEL8820CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NYEL8820BMsg scrnMsg = (NYEL8820BMsg) bMsg;
        NYEL8820CMsg bizMsg  = (NYEL8820CMsg) cMsg;
        
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        
        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        NYEL8820CommonLogic.init(scrnMsg, this, this.getGlobalCompanyCode());
    }
}
