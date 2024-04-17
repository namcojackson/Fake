/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2090;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NWAL2090Scrn00_CMN_Clear extends S21CommonHandler {

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
        NWAL2090BMsg scrnMsg = (NWAL2090BMsg) bMsg;

        ZYPEZDItemValueSetter.setValue(scrnMsg.cpoOrdNum, scrnMsg.cpoOrdNum_BK);
        ZYPEZDItemValueSetter.setValue(scrnMsg.chngRsnTpCd, scrnMsg.chngRsnTpCd_BK);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxComnScrColValTxt, scrnMsg.xxComnScrColValTxt_BK);

        scrnMsg.setFocusItem(scrnMsg.cpoOrdNum);
    }
}
