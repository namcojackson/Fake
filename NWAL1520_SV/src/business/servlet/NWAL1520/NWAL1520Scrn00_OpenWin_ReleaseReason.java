/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1520;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import business.servlet.NWAL1520.common.NWAL1520CommonLogic;


/**
 *<pre>
 * NWAL1520Scrn00_OpenWin_ReleaseReason
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/30   Fujitsu         A.Suda          Create          N/A
 *</pre>
 */
public class NWAL1520Scrn00_OpenWin_ReleaseReason extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1520BMsg scrnMsg = (NWAL1520BMsg) bMsg;

        String submitLinkNm = getSubmitedFieldNm(ctx);
        scrnMsg.xxScrEventNm.setValue(submitLinkNm);

        // Clear Params
        NWAL1520CommonLogic.clearPopUpParam(scrnMsg);

        scrnMsg.xxPopPrm_P1.setValue("HLD_REL_RSN");
        scrnMsg.xxPopPrm_P2.setValue("HLD_REL_RSN_CD");
        scrnMsg.xxPopPrm_P3.setValue("HLD_REL_RSN_DESC_TXT");
        scrnMsg.xxPopPrm_P4.setValue("HLD_REL_RSN_SORT_NUM");
        scrnMsg.xxPopPrm_P5.setValue("Released Reason Search");
        scrnMsg.xxPopPrm_P6.setValue("Released Reason Code");
        scrnMsg.xxPopPrm_P7.setValue("Released Reason Name");
        scrnMsg.xxPopPrm_P8.setValue("Released Reason Code");
        scrnMsg.xxPopPrm_P9.setValue("Released Reason Name");

        Object[] params = new Object[11];
        params[0] = scrnMsg.xxPopPrm_P1;
        params[1] = scrnMsg.xxPopPrm_P2;
        params[2] = scrnMsg.xxPopPrm_P3;
        params[3] = scrnMsg.xxPopPrm_P4;
        params[4] = scrnMsg.xxPopPrm_P5;
        params[5] = scrnMsg.xxPopPrm_P6;
        params[6] = scrnMsg.xxPopPrm_P7;
        params[7] = scrnMsg.xxPopPrm_P8;
        params[8] = scrnMsg.xxPopPrm_P9;
        params[9] = scrnMsg.xxPopPrm_PA;
        params[10] = scrnMsg.hldRelRsnDescTxt_RH;

        setArgForSubScreen(params);

    }
}
