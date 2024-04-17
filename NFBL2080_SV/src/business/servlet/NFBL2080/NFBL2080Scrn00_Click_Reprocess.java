/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2080;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFBL2080.NFBL2080CMsg;
//import business.servlet.NFBL2080.constant.NFBL2080Constant;

import business.blap.NFBL2080.NFBL2080CMsg;
import business.servlet.NFBL2080.common.NFBL2080CommonLogic;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_INV_PROC_STS;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import parts.common.EZDFieldErrorException;

/** 
 *<pre>
 * AP Invoice I/F Error Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/29   CSAI            Y.Miyauchi      Create          N/A
 * 2018/12/18   Hitachi         J.Kim           Update          QC#29631
 *</pre>
 */
public class NFBL2080Scrn00_Click_Reprocess extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2080BMsg scrnMsg = (NFBL2080BMsg) bMsg;

        //Check vendor invoice list
        if (!NFBL2080CommonLogic.checkVndInvList(scrnMsg)){
            throw new EZDFieldErrorException();
        }
        // Check VND_INV_PROC_STS_CD on detail records.
        if (!NFBL2080CommonLogic.checkDetailVndInvProcStsCd(scrnMsg, "Reprocess", VND_INV_PROC_STS.ERROR, "Error")){
            throw new EZDFieldErrorException();
        }

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2080BMsg scrnMsg = (NFBL2080BMsg) bMsg;

        NFBL2080CMsg bizMsg = new NFBL2080CMsg();
        bizMsg.setBusinessID("NFBL2080");
        bizMsg.setFunctionCode("40");
        scrnMsg.xxDplyTab.clear();
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2080BMsg scrnMsg = (NFBL2080BMsg) bMsg;
        NFBL2080CMsg bizMsg  = (NFBL2080CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2018/12/18 J.Kim [QC#29631,ADD]
        NFBL2080CommonLogic.setHyoSettings(scrnMsg, this);
        // END 2018/12/18 J.Kim [QC#29631,ADD]
    }
}
