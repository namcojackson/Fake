/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFAL0060;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFAL0060.common.NFAL0060CommonLogic;
import business.servlet.NFAL0060.constant.NFAL0060Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * Class name: Screen Component ID :
 * NFAL0060Scrn00_OnChange_AJE_CD_TBL_LIST_CD
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL0060Scrn00_OnChange_AJE_CD_TBL_LIST_CD extends S21CommonHandler implements NFAL0060Constant {

    /** Singleton instance. */
    private NFAL0060CommonLogic common = new NFAL0060CommonLogic();

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NFAL0060BMsg scrnMsg = (NFAL0060BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NFAL0060BMsg scrnMsg = (NFAL0060BMsg) bMsg;

        // NFAL0060CMsg bizMsg = new NFAL0060CMsg();
        // bizMsg.setBusinessID("NFAL0060");
        // bizMsg.setFunctionCode("20");
        // EZDMsg.copy(scrnMsg, null, bizMsg, null);

        // return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL0060BMsg scrnMsg = (NFAL0060BMsg) bMsg;
        // NFAL0060CMsg bizMsg = (NFAL0060CMsg) cMsg;
        // EZDMsg.copy(bizMsg, null, scrnMsg, null);
        if (common.isAllSelected(scrnMsg)) {
            common.enableAddRow(scrnMsg, this, true);
        } else {
            common.enableAddRow(scrnMsg, this, false);
        }
        common.clearHeaderFileds(scrnMsg);
        common.clearDisplaiedRows(scrnMsg, this);
        scrnMsg.setFocusItem(scrnMsg.ajeCdTblListCd_3);
    }

}
