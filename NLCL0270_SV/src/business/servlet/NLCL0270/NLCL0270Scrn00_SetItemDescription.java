/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0270;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0270.NLCL0270CMsg;
import business.servlet.NLCL0270.common.NLCL0270CommonLogic;
import business.servlet.NLCL0270.constant.NLCL0270Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/20/2015   CITS            M.Ito           Create          N/A
 * 12/21/2018   CITS            T.Tokutomi      Update          QC#29214
 *</pre>
 */
public class NLCL0270Scrn00_SetItemDescription extends S21CommonHandler implements NLCL0270Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0270BMsg scrnMsg = (NLCL0270BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.mdseCd_H1)) {
            scrnMsg.mdseCd_H1.setErrorInfo(1, ZZZM9007E, new String[] {NAME_FOR_MESSAGE_MDSE_CD });
            scrnMsg.putErrorScreen();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0270BMsg scrnMsg = (NLCL0270BMsg) bMsg;
        NLCL0270CMsg bizMsg = new NLCL0270CMsg();

        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0270BMsg scrnMsg = (NLCL0270BMsg) bMsg;
        NLCL0270CMsg bizMsg = (NLCL0270CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // QC#29214 Update.
        NLCL0270CommonLogic.cntrlScrnItemDispSearch(this, scrnMsg);

        if (scrnMsg.mdseCd_H1.isError()) {
            scrnMsg.addCheckItem(scrnMsg.mdseCd_H1);
            scrnMsg.putErrorScreen();
        } else {
            // set focus
            scrnMsg.setFocusItem(scrnMsg.mdseCd_H1);
        }
    }
}
