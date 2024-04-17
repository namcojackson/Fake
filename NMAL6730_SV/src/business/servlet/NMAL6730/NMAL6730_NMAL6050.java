/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6730;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2015/11/05   Fujitsu         N.Sugiura       Update          N/A
 *</pre>
 */
public class NMAL6730_NMAL6050 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NMAL6730BMsg scrnMsg = (NMAL6730BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6730BMsg scrnMsg = (NMAL6730BMsg) bMsg;

        scrnMsg.cltCustTpCd_F1.setValue(scrnMsg.xxPopPrm_P9.getValue());
        scrnMsg.cltCustTpNm_F1.setValue(scrnMsg.xxPopPrm_PA.getValue());

        scrnMsg.setFocusItem(scrnMsg.cltCustTpCd_F1);

    }
}
