/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2060;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFBL2060.NFBL2060CMsg;
//import business.servlet.NFBL2060.constant.NFBL2060Constant;

import business.blap.NFBL2060.NFBL2060CMsg;
import business.servlet.NFBL2060.constant.NFBL2060Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/14   CUSA            Y.Aikawa        Create          N/A
 * 2016/08/30   Fujitsu         C.Tanaka        Update          QC#5521
 * 2016/10/03   Hitachi         T.Tsuchida      Update          QC#13414
 *</pre>
 */
public class NFBL2060Scrn00_CMN_Clear extends S21CommonHandler implements NFBL2060Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2060BMsg scrnMsg = (NFBL2060BMsg) bMsg;

        NFBL2060CMsg bizMsg = new NFBL2060CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_20);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2060BMsg scrnMsg = (NFBL2060BMsg) bMsg;
        NFBL2060CMsg bizMsg  = (NFBL2060CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        /** Initialize tab position */
        // QC#5521 DEL Start
        //scrnMsg.xxDplyTab.setValue(TAB_DETAILED);
        // QC#5521 DEL End
        /** Set focus when opening screen */
        scrnMsg.setFocusItem(scrnMsg.dplyVndNm);

        // QC#5521 ADD Start
        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.D.no(0).getBaseContents());
        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.S.no(0).getBaseContents());
        // QC#5521 ADD End
    }
}
