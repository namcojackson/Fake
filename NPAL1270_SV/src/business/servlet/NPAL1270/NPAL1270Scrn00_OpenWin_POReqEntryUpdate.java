/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1270;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NPAL1270.NPAL1270CMsg;
//import business.servlet.NPAL1270.constant.NPAL1270Constant;

import business.servlet.NPAL1270.common.NPAL1270CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * PO Requisition List
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NPAL1270Scrn00_OpenWin_POReqEntryUpdate extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1270BMsg scrnMsg = (NPAL1270BMsg) bMsg;
        Object[] params = NPAL1270CommonLogic.setParamForPoReqEntry(scrnMsg, getButtonSelectNumber());

        // Subscreen transition
        setArgForSubScreen(params);
    }
}