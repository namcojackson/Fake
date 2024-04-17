/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0870;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0870.NSAL0870CMsg;
import business.servlet.NSAL0870.common.NSAL0870CommonLogic;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Meter Interface Status Inquiry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/01/23   Hitachi         K.Ochiai        Create          QC#16331
 *</pre>
 */
public class NSAL0870Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // do nothing

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0870BMsg scrnMsg = (NSAL0870BMsg) bMsg;

        NSAL0870CMsg bizMsg = new NSAL0870CMsg();
        bizMsg.setBusinessID("NSAL0870");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0870BMsg scrnMsg = (NSAL0870BMsg) bMsg;
        NSAL0870CMsg bizMsg = (NSAL0870CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        S21SortColumnIMGController.clearIMG("NSAL0870", scrnMsg, scrnMsg.A.no(0).getBaseContents());
        NSAL0870CommonLogic.initialControlScreen(this, scrnMsg);

    }
}
