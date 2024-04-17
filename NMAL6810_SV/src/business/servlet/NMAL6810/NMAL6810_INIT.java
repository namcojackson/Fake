/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6810;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6810.NMAL6810CMsg;
import business.servlet.NMAL6810.common.NMAL6810CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Item Master Template Popup
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/17   Hitachi         K.Kishimoto     Create          N/A
 *</pre>
 */
public class NMAL6810_INIT extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NMAL6810BMsg scrnMsg = (NMAL6810BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6810BMsg scrnMsg = (NMAL6810BMsg) bMsg;

        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            EZDBStringItem param1 = (EZDBStringItem) params[1];
            scrnMsg.mdseItemTpCd_HS.setValue(param1.getValue());
            EZDBStringItem param2 = (EZDBStringItem) params[2];
            scrnMsg.mdseCratTmplNm_H.setValue(param2.getValue());
        }

        NMAL6810CMsg bizMsg = new NMAL6810CMsg();
        bizMsg.setBusinessID("NMAL6810");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
        
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6810BMsg scrnMsg = (NMAL6810BMsg) bMsg;
        NMAL6810CMsg bizMsg = (NMAL6810CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NMAL6810CommonLogic.initScrn(scrnMsg, scrnMsg.A.no(0).getBaseContents());
        NMAL6810CommonLogic.initCommonButton(this);
        
    }
}
