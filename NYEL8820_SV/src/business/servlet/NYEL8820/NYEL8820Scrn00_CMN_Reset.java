/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8820;

import static business.servlet.NYEL8820.constant.NYEL8820Constant.BIZ_ID;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NYEL8820.NYEL8820CMsg;
import business.servlet.NYEL8820.common.NYEL8820CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NYEL8820Scrn00_CMN_Reset
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/17   Fujitsu         Q09079          Create          N/A
 * 2018/05/15   Fujitsu         Q10814          Update          QC#23516
 *</pre>
 */
public class NYEL8820Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NYEL8820BMsg scrnMsg = (NYEL8820BMsg) bMsg;

        NYEL8820CMsg bizMsg = new NYEL8820CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NYEL8820BMsg scrnMsg = (NYEL8820BMsg) bMsg;
        NYEL8820CMsg bizMsg = (NYEL8820CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // QC#23516 ADD START 2018/05/15
        scrnMsg.wfCmntTxt.clear();
        // QC#23516 ADD END 2018/05/15
        NYEL8820CommonLogic.init(scrnMsg, this, this.getGlobalCompanyCode());
        NYEL8820CommonLogic.btnCtrl(scrnMsg, this);
    }
}
