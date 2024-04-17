/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7010;

import static business.servlet.NMAL7010.constant.NMAL7010Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7010.NMAL7010CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/09   SRA             E.Inada         Create          QC#2174
 *</pre>
 */
public class NMAL7010_NMAL7030 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            NMAL7010BMsg scrnMsg = (NMAL7010BMsg) bMsg;
            NMAL7010CMsg bizMsg = new NMAL7010CMsg();

            bizMsg.setBusinessID(BIZ_ID);
            bizMsg.setFunctionCode("20");
            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            return bizMsg;
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7010BMsg scrnMsg = (NMAL7010BMsg) bMsg;
        NMAL7010CMsg bizMsg = (NMAL7010CMsg) cMsg;

        int idx = getButtonSelectNumber();

        if (bizMsg.Q.getValidCount() > 0) {
            bizMsg.A.no(idx).xxYesNoCd_PA.setValue(ZYPConstant.FLG_ON_Y);
        } else {
            bizMsg.A.no(idx).xxYesNoCd_PA.setValue(ZYPConstant.FLG_OFF_N);
        }

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
    }
}
