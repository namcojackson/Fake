/*
 * <pre>Copyright (c) 2022 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1780;

import static business.servlet.NWAL1780.constant.NWAL1780Constant.BIZ_ID;
import static business.servlet.NWAL1780.constant.NWAL1780Constant.FUNC_CD_SRCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NWAL1780.NWAL1780CMsg;
import business.servlet.NWAL1780.common.NWAL1780CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * Supply Quote Search Clear
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2022/04/18   CITS            J.Evangelista   Create          QC#59934
 *</pre>
 */
public class NWAL1780Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1780BMsg scrnMsg = (NWAL1780BMsg) bMsg;

        NWAL1780CMsg bizMsg = new NWAL1780CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1780BMsg scrnMsg = (NWAL1780BMsg) bMsg;
        NWAL1780CMsg bizMsg = (NWAL1780CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.setFocusItem(scrnMsg.splyQuoteNum);

        NWAL1780CommonLogic.initCommonButton(this);
        NWAL1780CommonLogic.initProtect(scrnMsg);
    }

}
