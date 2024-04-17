/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL1120;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFBL1120.NFBL1120CMsg;
//import business.servlet.NFBL1120.constant.NFBL1120Constant;

import business.blap.NFBL1120.NFBL1120CMsg;
import business.servlet.NFBL1120.common.NFBL1120CommonLogic;
import business.servlet.NFBL1120.constant.NFBL1120Constant;
import business.servlet.NFBL1120.NFBL1120BMsg;

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
public class NFBL1120Scrn00_PageNext extends S21CommonHandler implements NFBL1120Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL1120BMsg scrnMsg = (NFBL1120BMsg) bMsg;
        NFBL1120CMsg bizMsg = new NFBL1120CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_20);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL1120BMsg scrnMsg = (NFBL1120BMsg) bMsg;
        NFBL1120CMsg bizMsg  = (NFBL1120CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        /** Initialize button control */ 
        NFBL1120CommonLogic.initControl(this, scrnMsg);
        /** Set alternate rows background color */
        NFBL1120CommonLogic.setAlternateRowsBGCommon(scrnMsg);
        /** Set focus when opening screen */
        scrnMsg.setFocusItem(scrnMsg.apBatNum);

    }
}
