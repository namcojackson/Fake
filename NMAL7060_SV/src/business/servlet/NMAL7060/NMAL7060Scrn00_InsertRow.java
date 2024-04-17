/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7060;

import static business.servlet.NMAL7060.constant.NMAL7060Constant.BIZ_ID;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.NMAM8187E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7060.NMAL7060CMsg;
import business.servlet.NMAL7060.common.NMAL7060CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7060Scrn00_InsertRow
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/02   Fujitsu         W.Honda         Create          N/A
 * 2017/02/10   Fujitsu         R.Nakamura      Update          QC#17529
 *</pre>
 */
public class NMAL7060Scrn00_InsertRow extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL7060BMsg scrnMsg = (NMAL7060BMsg) bMsg;

        int len = scrnMsg.A.length();
        // Mod Start 2017/02/10 QC#17259
        // if (len < (scrnMsg.A.getValidCount() + 1)) {
        if (len < (scrnMsg.A.getValidCount() + 1) && !ZYPCommonFunc.hasValue(scrnMsg.xxEventFlgTxt_F1)) {
        // Mod End 2017/02/10 QC#17259
            scrnMsg.setMessageInfo(NMAM8187E, new String[] {"Price Meter Model", String.valueOf(scrnMsg.A.length()) });
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7060BMsg scrnMsg = (NMAL7060BMsg) bMsg;
        NMAL7060CMsg bizMsg = new NMAL7060CMsg();

        // Mod Start 2017/02/10 QC#17259
        if (scrnMsg.A.length() >= (scrnMsg.A.getValidCount() + 1)) {
            scrnMsg.A.no(scrnMsg.A.getValidCount()).effFromDt_A1.setValue(ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
            scrnMsg.A.setValidCount(scrnMsg.A.getValidCount() + 1);
        }
        // Mod End 2017/02/10 QC#17259

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7060BMsg scrnMsg = (NMAL7060BMsg) bMsg;

        // Add Start 2017/02/10 QC#17529
        EZDMsg.copy(cMsg, null, scrnMsg, null);
        // Add End 2017/02/10 QC#17529

        NMAL7060CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg);
    }
}
