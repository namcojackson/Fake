/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7260;

import static business.servlet.NMAL7260.constant.NMAL7260Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7260.NMAL7260CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7260Scrn00_OnChange_Attribute
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/08   Fujitsu         H.Ikeda         Create          N/A
 * 2016/10/11   Fujitsu         R.Nakamura      Update          QC#14936
 *</pre>
 */
public class NMAL7260Scrn00_OnChange_Attribute extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL7260BMsg scrnMsg = (NMAL7260BMsg) bMsg;
        // Del Start 2016/10/11 QC#14936
//        NMAL7260CommonLogic.onChangeCheck(scrnMsg);
        // Del End 2016/10/11 QC#14936

        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.C.no(i).inpReqFlg_C1);
        }

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7260BMsg scrnMsg = (NMAL7260BMsg) bMsg;

        NMAL7260CMsg bizMsg = new NMAL7260CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7260BMsg scrnMsg = (NMAL7260BMsg) bMsg;
        NMAL7260CMsg bizMsg  = (NMAL7260CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}
