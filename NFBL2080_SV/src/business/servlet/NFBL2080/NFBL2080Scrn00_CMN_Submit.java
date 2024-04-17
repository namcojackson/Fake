/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2080;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFBL2080.NFBL2080CMsg;
import business.servlet.NFBL2080.common.NFBL2080CommonLogic;
import business.servlet.NFBL2080.constant.NFBL2080Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * AP Invoice I/F Error Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/29   CSAI            Y.Miyauchi      Create          N/A
 * 2016/08/01   Fujitsu         S.Yoshida       Update          QC#12575
 * 2018/12/18   Hitachi         J.Kim           Update          QC#29631
 * 2019/03/25   Hitachi         Y.Takeno        Update          QC#30850
 * 2019/04/17   Fujitsu         H.Ikeda         Update          QC#31166
 *</pre>
 */
public class NFBL2080Scrn00_CMN_Submit extends S21CommonHandler implements NFBL2080Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2080BMsg scrnMsg = (NFBL2080BMsg) bMsg;

//Mod Start QC#12575
        scrnMsg.addCheckItem(scrnMsg.poOrdNum_H1);
        scrnMsg.addCheckItem(scrnMsg.ediPoOrdNum_H1);
//Mod End   QC#12575
        scrnMsg.addCheckItem(scrnMsg.soNum_H1);
        scrnMsg.addCheckItem(scrnMsg.custIssPoNum_H1);
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.B.no(i).mdseCd_B1);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).mdseNm_B1);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).shipQty_B1);
            // START 2019/4/17 [QC#31166, ADD]
            scrnMsg.addCheckItem(scrnMsg.B.no(i).ediPoOrdDtlLineNum_B1);
            // END   2019/4/17 [QC#31166, ADD]
        }

        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2080BMsg scrnMsg = (NFBL2080BMsg) bMsg;

        NFBL2080CMsg bizMsg = new NFBL2080CMsg();
        bizMsg.setBusinessID("NFBL2080");
        bizMsg.setFunctionCode("40");
        // START 2019/03/25 [QC#30850, DEL]
        // scrnMsg.xxDplyTab.clear();
        // END   2019/03/25 [QC#30850, DEL]
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2080BMsg scrnMsg = (NFBL2080BMsg) bMsg;
        NFBL2080CMsg bizMsg  = (NFBL2080CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2019/03/25 [QC#30850, ADD]
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.B.no(i).mdseCd_B1);
        }
        scrnMsg.putErrorScreen();
        // END   2019/03/25 [QC#30850, ADD]

        // START 2018/12/18 J.Kim [QC#29631,ADD]
        NFBL2080CommonLogic.setHyoSettings(scrnMsg, this);
        // END 2018/12/18 J.Kim [QC#29631,ADD]
    }
}
