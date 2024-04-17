/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFBL2040.NFBL2040CMsg;
//import business.servlet.NFBL2040.constant.NFBL2040Constant;

import business.blap.NFBL2040.NFBL2040CMsg;
import business.servlet.NFBL2040.common.NFBL2040CommonLogic;
import business.servlet.NFBL2040.constant.NFBL2040Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   CUSA            Y.Aikawa        Create          N/A
 * 2016/07/29   Hitachi         K.Kojima        Update          QC#12665
 *</pre>
 */
public class NFBL2040Scrn00_OnChange_XX_ALLOC_TP_CD extends S21CommonHandler implements NFBL2040Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;

        NFBL2040CMsg bizMsg = new NFBL2040CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_20);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        NFBL2040CMsg bizMsg  = (NFBL2040CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        /** Initialize button control */ 
        NFBL2040CommonLogic.initControl(this, scrnMsg);
        /** Set alternate rows background color */
        NFBL2040CommonLogic.setAlternateRowsBGCommon(scrnMsg);
        /** Set focus when opening screen */
        scrnMsg.setFocusItem(scrnMsg.cmApInvTpCd_S);

        // START 2016/07/29 K.Kojima [QC#12665,ADD]
        NFBL2040CommonLogic.clearRowsBG_NoSelectTab(bMsg);
        // END 2016/07/29 K.Kojima [QC#12665,ADD]
    }
}
