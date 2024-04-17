/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0020.NFDL0020CMsg;
import business.servlet.NFDL0020.common.NFDL0020CommonLogic;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/05/16   Fujisu          Y.Matsui        Create          QC#24329
 * 2018/05/21   CITS            S.Katsuma       Update          QC#24793
 *</pre>
 */
public class NFDL0020Scrn00_Click_TabStatement extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;

        NFDL0020CMsg bizMsg = new NFDL0020CMsg();
        bizMsg.setBusinessID("NFDL0020");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;
        NFDL0020CMsg bizMsg = (NFDL0020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NFDL0020CommonLogic.initialize(this, scrnMsg);

        S21SortColumnIMGController.clearIMG("NFDL0020Scrn00", scrnMsg, scrnMsg.B.no(0).getBaseContents());
        S21SortColumnIMGController.clearIMG("NFDL0020Scrn00", scrnMsg, scrnMsg.A.no(0).getBaseContents());
        S21SortColumnIMGController.clearIMG("NFDL0020Scrn00", scrnMsg, scrnMsg.H.no(0).getBaseContents());
        S21SortColumnIMGController.clearIMG("NFDL0020Scrn00", scrnMsg, scrnMsg.J.no(0).getBaseContents());
        // START 2018/05/21 S.Katsuma [QC#24793,ADD]
        S21SortColumnIMGController.clearIMG("NFDL0020Scrn00", scrnMsg, scrnMsg.F.no(0).getBaseContents());
        S21SortColumnIMGController.clearIMG("NFDL0020Scrn00", scrnMsg, scrnMsg.G.no(0).getBaseContents());
        S21SortColumnIMGController.clearIMG("NFDL0020Scrn00", scrnMsg, scrnMsg.C.no(0).getBaseContents());
        // END 2018/05/21 S.Katsuma [QC#24793,ADD]

    }
}