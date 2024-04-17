/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0470;

import static business.servlet.NSAL0470.constant.NSAL0470Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0470.NSAL0470CMsg;
import business.servlet.NSAL0470.common.NSAL0470CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Complete Contract
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/15   Hitachi         T.Aoyagi        Create          QC3050
 * 2017/01/25   Hitachi         K.Kitachi       Update          QC#17277
 * 2017/07/26   Hitachi         K.Kasai         Update          QC#18882
 *</pre>
 */
public class NSAL0470Scrn00_ActivateContract extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0470BMsg scrnMsg = (NSAL0470BMsg) bMsg;

        NSAL0470CMsg bizMsg = new NSAL0470CMsg();
        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // START 2017/01/25 K.Kitachi [QC#17277, ADD]
        this.setResult("no");
        if (NZZM0002I.equals(cMsg.getMessageCode())) {
            this.setResult("yes");
            return;
        }
        // END 2017/01/25 K.Kitachi [QC#17277, ADD]

        NSAL0470BMsg scrnMsg = (NSAL0470BMsg) bMsg;
        NSAL0470CMsg bizMsg = (NSAL0470CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        //START 2017/07/26 K.Kasai [QC#18882,ADD]
        if ("E".equals(bizMsg.getMessageKind()) || "W".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        //END 2017/07/26 K.Kasai [QC#18882,ADD]

        NSAL0470CommonLogic.screenItemControl(this, scrnMsg);

    }
}
