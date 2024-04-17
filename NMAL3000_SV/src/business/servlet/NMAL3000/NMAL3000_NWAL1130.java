/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL3000;

import static business.servlet.NMAL3000.constant.NMAL3000Constant.BIZ_ID;
import static business.servlet.NMAL3000.constant.NMAL3000Constant.HEADER_VALUE;
import static business.servlet.NMAL3000.constant.NMAL3000Constant.EVENT_NM_CMN_CLOSE;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import business.blap.NMAL3000.NMAL3000CMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL3000_NWAL1130
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/19   Fujitsu         M.Suzuki        Create          N/A
 * 2016/03/09   SRAA            Y.Chen          Update          QC#5169
 *</pre>
 */
public class NMAL3000_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL3000BMsg scrnMsg = (NMAL3000BMsg) bMsg;

        NMAL3000CMsg bizMsg = new NMAL3000CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL3000BMsg scrnMsg = (NMAL3000BMsg) bMsg;

        if (EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            return;
        }

        int btnidx = getButtonSelectNumber();
        if (btnidx == HEADER_VALUE) {
            // QC#5169
            ZYPEZDItemValueSetter.setValue(scrnMsg.mktMdlCd, scrnMsg.P.no(0).xxComnScrColValTxt.getValue());
        } else {
            // QC#5169
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(btnidx).mktMdlCd_A, scrnMsg.P.no(0).xxComnScrColValTxt.getValue());
        }
    }
}
