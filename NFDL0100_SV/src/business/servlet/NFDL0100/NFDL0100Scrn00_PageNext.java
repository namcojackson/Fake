/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0100;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0100.NFDL0100CMsg;
import business.servlet.NFDL0100.common.NFDL0100CommonLogic;
import business.servlet.NFDL0100.constant.NFDL0100Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/** 
 *<pre>
 * Print Invoice
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/14   CSAI            K.Lee           Create          N/A
 * 2016/08/01   Hitachi         K.Kojima        Update          QC#11570
 *</pre>
 */
public class NFDL0100Scrn00_PageNext extends S21CommonHandler implements NFDL0100Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0100BMsg scrnMsg = (NFDL0100BMsg) bMsg;
        NFDL0100CMsg bizMsg = new NFDL0100CMsg();
        bizMsg.setBusinessID("NFDL0100");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0100BMsg scrnMsg = (NFDL0100BMsg) bMsg;
        NFDL0100CMsg bizMsg  = (NFDL0100CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        S21TableColorController tblColor = new S21TableColorController("NFDL0100Scrn00", scrnMsg);
        tblColor.clearRowsBG(TABLE_A, scrnMsg.A);
        NFDL0100CommonLogic.initialize(this, scrnMsg);
        // START 2016/08/01 K.Kojima [QC#11570,DEL]
        // NFDL0100CommonLogic.setTableColor(scrnMsg);
        // END 2016/08/01 K.Kojima [QC#11570,DEL]
        tblColor.setAlternateRowsBG(TABLE_A, scrnMsg.A);
    }
}
