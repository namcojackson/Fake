/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3030;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NFCL3030.constant.NFCL3030Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NFCL3030Scrn00_Click_JournalInquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/16   Fujitsu         S.Fujita        Create          QC#6457
 *</pre>
 */
public class NFCL3030Scrn00_Click_JournalInquiry extends S21CommonHandler implements NFCL3030Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3030BMsg scrnMsg = (NFCL3030BMsg) bMsg;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeInd, MODE_JNL_ENT_INQ);

        Object[] params = new Object[2];
        params[0] = scrnMsg.xxModeInd;
        params[1] = scrnMsg.rcptNum_H;

        setArgForSubScreen(params);
    }
}
