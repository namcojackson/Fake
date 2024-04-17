/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL2640;

import static business.servlet.NFCL2640.constant.NFCL2640Constant.BIZ_ID;
import static business.servlet.NFCL2640.constant.NFCL2640Constant.SCRN_ID_00;
import static business.servlet.NFCL2640.constant.NFCL2640Constant.NFCM0023E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL2640.NFCL2640CMsg;
import business.servlet.NFCL2640.common.NFCL2640CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Statement Schedule Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/25   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NFCL2640Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL2640BMsg scrnMsg = (NFCL2640BMsg) bMsg;
        if (ZYPCommonFunc.hasValue(scrnMsg.arStmtDt_FR) && ZYPCommonFunc.hasValue(scrnMsg.arStmtDt_TO)) {
            if (scrnMsg.arStmtDt_FR.getValue().compareTo(scrnMsg.arStmtDt_TO.getValue()) > 0) {
                scrnMsg.arStmtDt_FR.setErrorInfo(1, NFCM0023E);
                scrnMsg.arStmtDt_TO.setErrorInfo(1, NFCM0023E);
            }
        }

        scrnMsg.addCheckItem(scrnMsg.arStmtIssCycleCd);
        scrnMsg.addCheckItem(scrnMsg.arStmtDt_FR);
        scrnMsg.addCheckItem(scrnMsg.arStmtDt_TO);
        scrnMsg.addCheckItem(scrnMsg.arStmtStsCd);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL2640BMsg scrnMsg = (NFCL2640BMsg) bMsg;

        NFCL2640CMsg bizMsg = new NFCL2640CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL2640BMsg scrnMsg = (NFCL2640BMsg) bMsg;
        NFCL2640CMsg bizMsg = (NFCL2640CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (bizMsg.A.getValidCount() > 0) {
            NFCL2640CommonLogic.searchControlScreen(this, scrnMsg, getGlobalCompanyCode());
        }

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
        tblColor.setAlternateRowsBG("A1", scrnMsg.A);

    }
}
