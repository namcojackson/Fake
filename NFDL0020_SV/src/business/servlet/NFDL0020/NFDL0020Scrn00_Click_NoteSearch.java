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
 * 2018/04/03   Hitachi         J.Kim           Update          QC#24729
 * 2019/02/12   Fujitsu         S.Ohki          Update          QC#30143
 * 2022/08/30   Hitachi         M.Hashino       Update          QC#60404
 *</pre>
 */
public class NFDL0020Scrn00_Click_NoteSearch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.cratDt_FF);
        scrnMsg.addCheckItem(scrnMsg.cratDt_FT);
        scrnMsg.putErrorScreen();
        // START 2022/08/30 M.Hashino [QC#60404,ADD]
        scrnMsg.xxListNum_LY.setValue(0);
        // END 2022/08/30 M.Hashino [QC#60404,ADD]
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

        for (int i = 0; i < scrnMsg.F.getValidCount(); i++) {
            scrnMsg.F.no(i).cratDt_FD.setInputProtected(true);
            scrnMsg.F.no(i).cratUsrId_FD.setInputProtected(true);
            // START 2018/04/03 J.Kim [QC#25096,MOD]
            // scrnMsg.F.no(i).dtlNoteTxt_FD.setInputProtected(true);
            scrnMsg.F.no(i).xxMlBodyTxt_FD.setInputProtected(true);
            // END 2018/04/03 J.Kim [QC#25096,MOD]
        }
        // START 2019/02/12 S.Ohki [QC#30143,ADD]
        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.F.no(0).getBaseContents());
        // END 2019/02/12 S.Ohki [QC#30143,ADD]
    }
}
