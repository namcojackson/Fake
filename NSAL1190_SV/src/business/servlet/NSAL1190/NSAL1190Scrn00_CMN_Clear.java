/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1190;

import static business.servlet.NSAL1190.constant.NSAL1190Constant.BIZ_ID;
import static business.servlet.NSAL1190.constant.NSAL1190Constant.EZD_FUNC_CD_INQ;
import static business.servlet.NSAL1190.constant.NSAL1190Constant.SCREEN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1190.NSAL1190CMsg;
import business.servlet.NSAL1190.common.NSAL1190CommonLogic;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Counters Setup
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/01/20   Hitachi         K.Ochiai        Create          QC#16331
 *</pre>
 */
public class NSAL1190Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // do nothing

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1190BMsg scrnMsg = (NSAL1190BMsg) bMsg;

        NSAL1190CMsg bizMsg = new NSAL1190CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1190BMsg scrnMsg = (NSAL1190BMsg) bMsg;
        NSAL1190CMsg bizMsg = (NSAL1190CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSAL1190CommonLogic.initialControlScreen(this, scrnMsg);
        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());

    }
}
