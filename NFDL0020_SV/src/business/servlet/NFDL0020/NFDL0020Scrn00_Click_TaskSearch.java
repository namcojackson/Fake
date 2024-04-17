/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0020.NFDL0020CMsg;
import static business.servlet.NFDL0020.constant.NFDL0020Constant.SCRN_ID_00;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Collection Detail Screen
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 2018/07/25   Hitachi         Y.Takeno        Update          QC#25767
 * 2019/02/12   Fujitsu         S.Ohki          Update          QC#30143
 *</pre>
 */
public class NFDL0020Scrn00_Click_TaskSearch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;
        // START 2018/07/25 [QC#25757,MOD]
        scrnMsg.addCheckItem(scrnMsg.cltTaskRwsdDt_GF);
        scrnMsg.addCheckItem(scrnMsg.cltTaskRwsdDt_GT);
        // END   2018/07/25 [QC#25757,MOD]
        // START 2016/07/27 K.Kojima [QC#10199,ADD]
        scrnMsg.addCheckItem(scrnMsg.cltTaskSubjTxt_GS);
        // END 2016/07/27 K.Kojima [QC#10199,ADD]
        scrnMsg.putErrorScreen();
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
        NFDL0020CMsg bizMsg  = (NFDL0020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // START 2019/02/12 S.Ohki [QC#30143,ADD]
        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.G.no(0).getBaseContents());
        // END 2019/02/12 S.Ohki [QC#30143,ADD]
    }
}
