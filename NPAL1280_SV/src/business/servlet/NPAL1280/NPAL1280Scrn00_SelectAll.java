/**
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 */
package business.servlet.NPAL1280;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Business ID : NPAL1280 PO Requisition Entry
 * Function Name : SelectAll
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/28/2016   CITS            K.Ogino          Create         N/A
 * 04/04/2016   CITS            K.Ogino            Update       QC#5964
 *</pre>
 */
public class NPAL1280Scrn00_SelectAll extends S21CommonHandler {

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
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (!scrnMsg.A.no(i).xxChkBox_A1.isInputProtected()) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxChkBox_A1, ZYPConstant.FLG_ON_Y);
            }
        }

    }
}
