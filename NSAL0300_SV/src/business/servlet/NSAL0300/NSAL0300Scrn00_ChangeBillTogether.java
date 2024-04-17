/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0300;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0300.NSAL0300CMsg;
import business.servlet.NSAL0300.common.NSAL0300CommonLogic;
import business.servlet.NSAL0300.constant.NSAL0300Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/10   Hitachi         T.Kanasaka      Create          QC3058
 * 2022/03/22   Hitachi         D.Yoshida       Update          QC#59683
 *</pre>
 */
public class NSAL0300Scrn00_ChangeBillTogether extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2022/03/22 [QC#59683, DEL]
//        return null;
        // END   2022/03/22 [QC#59683, DEL]
        // START 2022/03/22 [QC#59683, ADD]
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        NSAL0300CMsg bizMsg = new NSAL0300CMsg();
        bizMsg.setBusinessID(NSAL0300Constant.BIZ_ID);
        bizMsg.setFunctionCode(NSAL0300Constant.EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
        // END   2022/03/22 [QC#59683, ADD]
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        // START 2022/03/22 [QC#59683, ADD]
        NSAL0300CMsg bizMsg = (NSAL0300CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // END   2022/03/22 [QC#59683, ADD]
        NSAL0300CommonLogic.setupScreenItems(this, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.prcAllocByMachQtyFlg);
    }
}
