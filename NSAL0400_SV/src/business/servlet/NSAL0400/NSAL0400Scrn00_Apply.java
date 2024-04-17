/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0400;

import static business.servlet.NSAL0400.constant.NSAL0400Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0400.NSAL0400CMsg;
import business.servlet.NSAL0400.constant.NSAL0400Constant.FUNC;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/19   Fujitsu         M.Yamada        Create          N/A
 * 2022/02/04   Hitachi         K.Kitachi       Update          QC#59684
 *</pre>
 */
public class NSAL0400Scrn00_Apply extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0400BMsg scrnMsg = (NSAL0400BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.contrCloDt_H);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0400BMsg scrnMsg = (NSAL0400BMsg) bMsg;

        NSAL0400CMsg bizMsg = new NSAL0400CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC.SEARCH.getFunc());
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0400BMsg scrnMsg = (NSAL0400BMsg) bMsg;
        NSAL0400CMsg bizMsg = (NSAL0400CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.contrCloDt_H);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_AD);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).contrCloDt_AD);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).trmnOvrdTotAmt_AD);
            // START 2022/02/04 K.Kitachi [QC#59684, ADD]
            scrnMsg.addCheckItem(scrnMsg.A.no(i).contrTrmnFlg_AD);
            // END 2022/02/04 K.Kitachi [QC#59684, ADD]
        }
        scrnMsg.putErrorScreen();

    }
}
