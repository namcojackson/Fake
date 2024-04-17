/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0320;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0320.NSAL0320CMsg;
import business.servlet.NSAL0320.common.NSAL0320CommonLogic;
import business.servlet.NSAL0320.constant.NSAL0320Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/06   CUSA            SRAA            Create          N/A
 * 2016/05/25   Hitachi         T.Tomita        Update          QC#4958
 * 2016/07/11   Hitachi         M.Gotou         Update          QC#11524
 * 2016/07/12   Hitachi         M.Gotou         Update          QC#11527
 *</pre>
 */
public class NSAL0320Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0320BMsg scrnMsg = (NSAL0320BMsg) bMsg;
        // START 2016/07/11 M.Gotou [QC#11524, DEL]
//        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
//            scrnMsg.A.no(i).bllblFlg.clearErrorInfo();
//            scrnMsg.A.no(i).contrMtrMultRate.clearErrorInfo();
//            scrnMsg.A.no(i).bllgMtrLbCd.clearErrorInfo();
//        }
        // END 2016/07/11 M.Gotou [QC#11524, DEL]
        NSAL0320CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0320BMsg scrnMsg = (NSAL0320BMsg) bMsg;
        NSAL0320CMsg bizMsg = new NSAL0320CMsg();
        bizMsg.setBusinessID(NSAL0320Constant.BIZ_ID);
        bizMsg.setFunctionCode(NSAL0320Constant.EZD_FUNC_CD_UPD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0320BMsg scrnMsg = (NSAL0320BMsg) bMsg;
        NSAL0320CMsg bizMsg = (NSAL0320CMsg) cMsg;

        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(NSAL0320Constant.BIZ_ID);
        NSAL0320CommonLogic.activateButtons(this, scrnMsg, functionList);

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSAL0320CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        NSAL0320CommonLogic.setupScreenItems(scrnMsg, functionList);
        // START 2016/07/12 M.Gotou [QC#11527, ADD]
        scrnMsg.setFocusItem(scrnMsg.prcMtrPkgPk_B);
        // END 2016/07/12 M.Gotou [QC#11527, ADD]
    }
}
