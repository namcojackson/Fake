/**
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 */
package business.servlet.NPAL1280;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NPAL1280.common.NPAL1280CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Business ID : NPAL1280 PO Requisition Entry
 * Function Name : OpenWin_Supplier
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/28/2016   CITS            K.Ogino          Create          N/A
 * 05/26/2016   CITS            K.Ogino          Update          QC#8964
 *</pre>
 */
public class NPAL1280Scrn00_OpenWin_Supplier extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1280BMsg scrnMsg = (NPAL1280BMsg) bMsg;
        scrnMsg.xxPopPrm_PA.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PA, "OpenWin_Supplier");
        int lineNum = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxLineNum, String.valueOf(lineNum));
        Object[] params = NPAL1280CommonLogic.getParamNWAL1130ForSupplier(scrnMsg, lineNum);
        setArgForSubScreen(params);

    }
}