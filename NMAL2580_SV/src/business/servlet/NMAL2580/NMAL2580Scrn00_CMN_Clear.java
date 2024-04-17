/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2580;

import static business.servlet.NMAL2580.constant.NMAL2580Constant.SCRN_ID_00;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2580.NMAL2580CMsg;
import business.servlet.NMAL2580.common.NMAL2580CommonLogic;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/08   Fujitsu         R.Nakamura      Create          N/A
 * 2016/08/03   Fujitsu         R.Nakamura      Update          QC#12174
 *</pre>
 */
public class NMAL2580Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2580BMsg scrnMsg = (NMAL2580BMsg) bMsg;

        NMAL2580CMsg bizMsg = new NMAL2580CMsg();
        bizMsg.setBusinessID("NMAL2580");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2580BMsg scrnMsg = (NMAL2580BMsg) bMsg;
        NMAL2580CMsg bizMsg = (NMAL2580CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // Add Start 2016/08/03 QC#12174
        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        // Add End 2016/08/03 QC#12174
        NMAL2580CommonLogic.initialControlScreen(this, scrnMsg);

    }
}
