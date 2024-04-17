/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL1120;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFBL1120.NFBL1120CMsg;
//import business.servlet.NFBL1120.constant.NFBL1120Constant;

import business.servlet.NFBL1110.NFBL1110BMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * AP Invoice Maintenance Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/23   CUSA            Y.Aikawa        Create          N/A
 * </pre>
 */
public class NFBL1120Scrn00_OnClick_InvoiceNumberLink extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL1120BMsg scrnMsg = (NFBL1120BMsg) bMsg;

        Object[] params = new Object[2];
        params[0] = scrnMsg.A.no(getButtonSelectNumber()).apInvNum_A1;
        params[1] = scrnMsg.A.no(getButtonSelectNumber()).apVndCd_A1;

        setArgForSubScreen(params);

    }
}
