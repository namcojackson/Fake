/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7010;

import static business.servlet.NMAL7010.constant.NMAL7010Constant.UPLD_CSV_ID_CUST_AUDC_WRK;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7010Scrn00_MoveWin_UploadCustAudc
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/27   Fujitsu         W.Honda         Create          QC#8505
 *</pre>
 */
public class NMAL7010Scrn00_MoveWin_UploadCustAudc extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7010BMsg scrnMsg = (NMAL7010BMsg) bMsg;
        Object[] params = new Object[1];
        scrnMsg.xxPopPrm_Z0.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z0, UPLD_CSV_ID_CUST_AUDC_WRK);
        params[0] = scrnMsg.xxPopPrm_Z0;

        setArgForSubScreen(params);

    }
}
