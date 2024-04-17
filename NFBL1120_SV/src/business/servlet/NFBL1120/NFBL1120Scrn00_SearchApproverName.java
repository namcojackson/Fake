/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL1120;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFBL1120.constant.NFBL1120Constant;

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
 * 2016/09/13   Hitachi         K.Kojima        Update          QC#12725
 * </pre>
 */
public class NFBL1120Scrn00_SearchApproverName extends S21CommonHandler implements NFBL1120Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2016/09/13 K.Kojima [QC#12725,DEL]
        // NFBL1120BMsg scrnMsg = (NFBL1120BMsg) bMsg;
        //
        // if (scrnMsg.apvrUsrId.isClear()) {
        // scrnMsg.apvrUsrId.setErrorInfo(1, ZZM9000E, new String[]
        // {scrnMsg.apvrUsrId.getNameForMessage() });
        // scrnMsg.apvrUsrNm.clear();
        // }
        // scrnMsg.addCheckItem(scrnMsg.apvrUsrId);
        // scrnMsg.putErrorScreen();
        // END 2016/09/13 K.Kojima [QC#12725,DEL]
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2016/09/13 K.Kojima [QC#12725,DEL]
        // NFBL1120BMsg scrnMsg = (NFBL1120BMsg) bMsg;
        //
        // NFBL1120CMsg bizMsg = new NFBL1120CMsg();
        // bizMsg.setBusinessID(BIZ_ID);
        // bizMsg.setFunctionCode(FUNC_CD_20);
        // EZDMsg.copy(scrnMsg, null, bizMsg, null);
        //
        // return bizMsg;
        // END 2016/09/13 K.Kojima [QC#12725,DEL]
        // START 2016/09/13 K.Kojima [QC#12725,ADD]
        return null;
        // END 2016/09/13 K.Kojima [QC#12725,ADD]
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // START 2016/09/13 K.Kojima [QC#12725,DEL]
        // NFBL1120BMsg scrnMsg = (NFBL1120BMsg) bMsg;
        // NFBL1120CMsg bizMsg = (NFBL1120CMsg) cMsg;
        //
        // EZDMsg.copy(bizMsg, null, scrnMsg, null);
        //
        // scrnMsg.addCheckItem(scrnMsg.apvrUsrId);
        // scrnMsg.putErrorScreen();
        //
        // scrnMsg.setFocusItem(scrnMsg.apvrUsrId);
        // END 2016/09/13 K.Kojima [QC#12725,DEL]
    }
}
