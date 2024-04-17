/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NFDL0020.NFDL0020CMsg;
import business.servlet.NFDL0020.common.NFDL0020CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Collection Detail Screen
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 2016/07/15   Hitachi         K.Kojima        Update          QC#11478
 *</pre>
 */
public class NFDL0020Scrn00_Click_ContractSearch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2016/07/15 K.Kojima [QC#11478,ADD]
        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;
        scrnMsg.putErrorScreen();
        // END 2016/07/15 K.Kojima [QC#11478,ADD]
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2016/07/15 K.Kojima [QC#11478,DEL]
        // return null;
        // END 2016/07/15 K.Kojima [QC#11478,DEL]
        // START 2016/07/15 K.Kojima [QC#11478,ADD]
        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;

        NFDL0020CMsg bizMsg = new NFDL0020CMsg();
        bizMsg.setBusinessID("NFDL0020");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
        // END 2016/07/15 K.Kojima [QC#11478,ADD]
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // START 2016/07/15 K.Kojima [QC#11478,ADD]
        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;
        NFDL0020CMsg bizMsg = (NFDL0020CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NFDL0020CommonLogic.setInputProtected_B(scrnMsg);
        S21SortColumnIMGController.clearIMG("NFDL0020Scrn00", scrnMsg, scrnMsg.B.no(0).getBaseContents());
        // END 2016/07/15 K.Kojima [QC#11478,ADD]
    }
}
