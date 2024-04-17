/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0300;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0300.NLCL0300CMsg;
import business.servlet.NLCL0300.common.NLCL0300CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/17   CSAI            K.Lee           Create          N/A
 * 2019/08/13   CITS            M.Naito         Update          QC#52185
 *</pre>
 */
public class NLCL0300Scrn00_Add_NewConfigFromModel extends S21CommonHandler {
	//NSZM0925E
    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0300BMsg scrnMsg = (NLCL0300BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd_H);
        scrnMsg.addCheckItem(scrnMsg.mdlDescTxt_H);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0300BMsg scrnMsg = (NLCL0300BMsg) bMsg;

        NLCL0300CMsg bizMsg = new NLCL0300CMsg();
        bizMsg.setBusinessID("NLCL0300");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0300BMsg scrnMsg = (NLCL0300BMsg) bMsg;
        NLCL0300CMsg bizMsg  = (NLCL0300CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd_H);
        scrnMsg.addCheckItem(scrnMsg.mdlDescTxt_H);
        scrnMsg.putErrorScreen();

        // START 2019/08/13 M.Naito [QC#52185,ADD]
        scrnMsg.xxWrnSkipFlg.clear();
        // END 2019/08/13 M.Naito [QC#52185,ADD]
        NLCL0300CommonLogic.initialControlScreen(this, scrnMsg, bizMsg.getUserID());

    }
}
