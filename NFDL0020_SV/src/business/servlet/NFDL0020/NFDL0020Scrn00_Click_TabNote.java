/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0020.NFDL0020CMsg;
import business.servlet.NFDL0020.common.NFDL0020CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Collection Detail Screen
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 2016/07/19   Hitachi         K.Kojima        Update          QC#11478
 * 2017/08/07   Hitachi         T.Tsuchida      Update          QC#19576
 * 2018/05/16   Fujitsu         Y.Matsui        Update          QC#24329
 * 2018/05/21   CITS            S.Katsuma       Update          QC#24793
 *</pre>
 */
public class NFDL0020Scrn00_Click_TabNote extends S21CommonHandler {

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
        NFDL0020CMsg bizMsg  = (NFDL0020CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // START 2016/07/19 K.Kojima [QC#11478,ADD]
        S21SortColumnIMGController.clearIMG("NFDL0020Scrn00", scrnMsg, scrnMsg.B.no(0).getBaseContents());
        // END 2016/07/19 K.Kojima [QC#11478,ADD]
        // START 2017/08/07 T.Tsuchida [QC#19576,ADD]
        S21SortColumnIMGController.clearIMG("NFDL0020Scrn00", scrnMsg, scrnMsg.A.no(0).getBaseContents());
        // END 2017/08/07 T.Tsuchida [QC#19576,ADD]
        // Start 2018/03/08 J.Kim [QC#20945,ADD]
        NFDL0020CommonLogic.initialize(this, scrnMsg);
        S21SortColumnIMGController.clearIMG("NFDL0020Scrn00", scrnMsg, scrnMsg.H.no(0).getBaseContents());
        // END 2018/03/08 J.Kim [QC#20945,ADD]
        // START 2018/06/21 [QC#, ADD]
        if ("1".equals(scrnMsg.xxYesNoCd_FH.getValue())) {
            scrnMsg.cltNoteTpCd_FH.setInputProtected(false);
            scrnMsg.colNoteSubjTxt_FH.setInputProtected(false);
            scrnMsg.xxMlBodyTxt_FH.setInputProtected(false);
            scrnMsg.setFocusItem(scrnMsg.cltNoteTpCd_FH);
        }
        // END   2018/06/21 [QC#, ADD]
        // START 2018/05/16 [QC#24329,ADD]
        S21SortColumnIMGController.clearIMG("NFDL0020Scrn00", scrnMsg, scrnMsg.J.no(0).getBaseContents());
        // END 2018/05/16 [QC#24329,ADD]
        // START 2018/05/21 S.Katsuma [QC#24793,ADD]
        S21SortColumnIMGController.clearIMG("NFDL0020Scrn00", scrnMsg, scrnMsg.F.no(0).getBaseContents());
        S21SortColumnIMGController.clearIMG("NFDL0020Scrn00", scrnMsg, scrnMsg.G.no(0).getBaseContents());
        S21SortColumnIMGController.clearIMG("NFDL0020Scrn00", scrnMsg, scrnMsg.C.no(0).getBaseContents());
        // END 2018/05/21 S.Katsuma [QC#24793,ADD]
    }
}
