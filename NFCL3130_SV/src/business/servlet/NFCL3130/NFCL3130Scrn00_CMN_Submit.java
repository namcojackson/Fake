/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3130;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL3130.NFCL3130CMsg;
import business.servlet.NFCL3130.common.NFCL3130CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/07/11   Hitachi         K.Kojima        Update          QC#11576
 * 2016/07/12   Hitachi         K.Kojima        Update          QC#11576
 *</pre>
 */
public class NFCL3130Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3130BMsg scrnMsg = (NFCL3130BMsg) bMsg;
        
        scrnMsg.addCheckItem(scrnMsg.arRcptSrcNm);
        scrnMsg.addCheckItem(scrnMsg.arRcptSrcDescTxt);
        scrnMsg.addCheckItem(scrnMsg.dsBankAcctNm);
        // START 2016/07/12 K.Kojima [QC#11576,ADD]
        scrnMsg.addCheckItem(scrnMsg.arRcptSrcTpCd_S1);
        // END 2016/07/12 K.Kojima [QC#11576,ADD]
        
        // START 2016/07/11 K.Kojima [QC#11576,ADD]
        NFCL3130CommonLogic.checkItem(scrnMsg);
        // END 2016/07/11 K.Kojima [QC#11576,ADD]

        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3130BMsg scrnMsg = (NFCL3130BMsg) bMsg;

        NFCL3130CMsg bizMsg = new NFCL3130CMsg();
        bizMsg.setBusinessID("NFCL3130");
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3130BMsg scrnMsg = (NFCL3130BMsg) bMsg;
        NFCL3130CMsg bizMsg  = (NFCL3130CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}
