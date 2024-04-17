/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8820;

import static business.servlet.NYEL8820.constant.NYEL8820Constant.BIZ_ID;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0005E;

import java.io.Serializable;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NYEL8820.NYEL8820CMsg;
import business.servlet.NYEL8820.common.NYEL8820CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NYEL8820_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/17   Fujitsu         Q09079          Create          N/A
 * 2018/04/24   Fujitsu         Q10814          Update          QC#23516
 *</pre>
 */
public class NYEL8820_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NYEL8820BMsg scrnMsg = (NYEL8820BMsg) bMsg;
        NYEL8820CMsg bizMsg = new NYEL8820CMsg();

        Serializable arg = getArgForSubScreen();
        if (arg instanceof EZDMsgArray) {
            EZDMsg.copy((EZDMsgArray) arg, null, scrnMsg.P, null);
        } else {
            scrnMsg.setMessageInfo(NYEM0005E, new String[] {""});
            return null;
        }

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

        NYEL8820CommonLogic.init(scrnMsg, this, this.getGlobalCompanyCode());

        scrnMsg.setFocusItem(scrnMsg.wfProcNm);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        // QC#23516 ADD START 2018/04/24
        NYEL8820BMsg scrnMsg = (NYEL8820BMsg) bMsg;

        scrnMsg.wfCmntTxt.setNameForMessage("Comment");
        // QC#23516 ADD END 2018/04/24

    }
}
