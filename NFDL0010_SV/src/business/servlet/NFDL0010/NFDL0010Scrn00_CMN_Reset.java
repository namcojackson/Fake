/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0010.NFDL0010CMsg;
import business.servlet.NFDL0010.common.NFDL0010CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Collection Summary
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 2016/06/29   Hitachi         K.Kojima        Update          QC#10166
 *</pre>
 */
public class NFDL0010Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0010BMsg scrnMsg = (NFDL0010BMsg) bMsg;

        NFDL0010CMsg bizMsg = new NFDL0010CMsg();
        bizMsg.setBusinessID("NFDL0010");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0010BMsg scrnMsg = (NFDL0010BMsg) bMsg;
        NFDL0010CMsg bizMsg = (NFDL0010CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        S21TableColorController tblColor = new S21TableColorController("NFDL0010Scrn00", scrnMsg);
        tblColor.clearRowsBG("A", scrnMsg.A);
        NFDL0010CommonLogic.initialize(this, scrnMsg);
        // START 2016/06/29 K.Kojima [QC#10166,DEL]
        // NFDL0010CommonLogic.setTableColor(scrnMsg);
        // END 2016/06/29 K.Kojima [QC#10166,DEL]
    }
}
