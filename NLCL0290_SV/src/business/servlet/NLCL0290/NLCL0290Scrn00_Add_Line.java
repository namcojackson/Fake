/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0290;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0290.NLCL0290CMsg;
import business.servlet.NLCL0290.common.NLCL0290CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/10   CSAI            K.Lee           Create          N/A
 * 2018/04/06   CITS            K.Masaki        Update          QC#18490
 *</pre>
 */
public class NLCL0290Scrn00_Add_Line extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0290BMsg scrnMsg = (NLCL0290BMsg) bMsg;

        NLCL0290CMsg bizMsg = new NLCL0290CMsg();
        bizMsg.setBusinessID("NLCL0290");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0290BMsg scrnMsg = (NLCL0290BMsg) bMsg;
        NLCL0290CMsg bizMsg  = (NLCL0290CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NLCL0290CommonLogic.initialControlScreen(this, scrnMsg, bizMsg.getUserID());

        //QC:18490
        //QC:14488
        //scrnMsg.addCheckItem(scrnMsg.xxScrItem130Txt_H);
        scrnMsg.svcConfigMstrPk.clear();

        scrnMsg.putErrorScreen();

    }
}
