/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL0750;

import static business.servlet.NFCL0750.constant.NFCL0750Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL0750.NFCL0750CMsg;
import business.servlet.NFCL0750.common.NFCL0750CommonLogic;
import business.servlet.NFCL0750.constant.NFCL0750Constant.FUNC;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/21   Hitachi         T.Tsuchida      Create          QC#19576
 *</pre>
 */
public class NFCL0750Scrn00_OnChange_WrtOffRqstTp extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL0750BMsg scrnMsg = (NFCL0750BMsg) bMsg;

        NFCL0750CMsg bizMsg = new NFCL0750CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC.SEARCH.getFunc());
        NFCL0750CommonLogic.initParam(scrnMsg);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NFCL0750BMsg scrnMsg = (NFCL0750BMsg) bMsg;
        NFCL0750CMsg bizMsg  = (NFCL0750CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NFCL0750CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
   }
}
