/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0150;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL0150.NSAL0150CMsg;
import business.servlet.NSAL0150.common.NSAL0150CommonLogic;
import business.servlet.NSAL0150.constant.NSAL0150Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/03   CUSA            SRAA            Create          N/A
 * 2016/02/23   Hitachi         T.Tsuchida      Update          QC#4117
 * 2016/10/25   Hitachi         Y.Takeno        Update          QC#15525
 * 2019/03/27   Hitachi         T.Tomita        Update          QC#30791
 *</pre>
 */
public class NSAL0150Scrn00_OpenWin_OrderSupplies extends S21CommonHandler {

    // START 2016/02/23 T.Tsuchida [QC#4117, MOD]
    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2016/10/25 [QC#15525, MOD]
        NSAL0150BMsg scrnMsg = (NSAL0150BMsg) bMsg;

        NSAL0150CMsg bizMsg = new NSAL0150CMsg();
        bizMsg.setBusinessID(NSAL0150Constant.BIZ_ID);
        bizMsg.setFunctionCode(NSAL0150Constant.EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
        // END 2016/10/25 [QC#15525, MOD]
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0150BMsg scrnMsg = (NSAL0150BMsg) bMsg;

        // START 2016/10/25 [QC#15525, ADD]
        NSAL0150CMsg bizMsg = (NSAL0150CMsg) cMsg;

        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(NSAL0150Constant.BIZ_ID);
        NSAL0150CommonLogic.activateButtons(this, scrnMsg, functionList);

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSAL0150CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        // END 2016/10/25 [QC#15525, ADD]

        Object[] prm = new Object[2];
        prm[0] = scrnMsg.svcMachMstrPk;
        // Mod Start 2019/03/27 QC#30791
        prm[1] = scrnMsg.dsContrDtlPk_SP;
        // Mod End 2019/03/27 QC#30791

        setArgForSubScreen(prm);
    }
    // END 2016/02/23 T.Tsuchida [QC#4117, MOD]
}
