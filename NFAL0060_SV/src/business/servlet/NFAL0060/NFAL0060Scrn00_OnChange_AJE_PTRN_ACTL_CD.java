/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFAL0060;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFAL0060.NFAL0060CMsg;
import business.servlet.NFAL0060.common.NFAL0060CommonLogic;
import business.servlet.NFAL0060.constant.NFAL0060Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * Class name: Screen Component ID :
 * NFAL0060Scrn00_OnChange_AJE_PTRN_ACTL_CD
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL0060Scrn00_OnChange_AJE_PTRN_ACTL_CD extends S21CommonHandler implements NFAL0060Constant {

    /** Singleton instance. */
    private NFAL0060CommonLogic common = new NFAL0060CommonLogic();

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NFAL0060BMsg scrnMsg = (NFAL0060BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0060BMsg scrnMsg = (NFAL0060BMsg) bMsg;
        NFAL0060CMsg bizMsg = new NFAL0060CMsg();
        bizMsg.setBusinessID("NFAL0060");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;

        // return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL0060BMsg scrnMsg = (NFAL0060BMsg) bMsg;
        NFAL0060CMsg bizMsg = (NFAL0060CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFAL0060CommonLogic.setInputProtectedTextFiled(scrnMsg);

        if (scrnMsg.A.getValidCount() > 0) {
            common.enableSubmitDelete(scrnMsg, this, true);
        } else {
            common.enableSubmitDelete(scrnMsg, this, false);
        }
        common.clearDeletedRow(scrnMsg);
        common.clearAddRow(scrnMsg, this);

        scrnMsg.setFocusItem(scrnMsg.ajePtrnActlCd_3);
    }

}
