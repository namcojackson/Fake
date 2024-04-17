/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0390;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL0390.common.NSAL0390CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/18   Fujitsu         T.Yoshida       Create          N/A
 * 2016/05/18   Hitachi         A.Kohinata      Update          QC#4212
 *</pre>
 */
public class NSAL0390Scrn00_LeasingCompany extends S21CommonHandler {

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

        NSAL0390BMsg scrnMsg = (NSAL0390BMsg) bMsg;
        final int eventLine = getButtonSelectNumber();
        NSAL0390_ABMsg abMsg = scrnMsg.A.no(eventLine);

        if (ZYPConstant.CHKBOX_ON_Y.equals(abMsg.leaseCmpyFlg_A0.getValue())) {
            abMsg.crCardCustRefNum_A1.clear();
            abMsg.crCardExprYrMth_A1.clear();
        }

        NSAL0390CommonLogic.controlDtl(this, scrnMsg);
    }
}
