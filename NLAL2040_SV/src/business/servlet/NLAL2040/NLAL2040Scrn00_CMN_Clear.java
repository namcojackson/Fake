/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLAL2040;

import static business.servlet.NLAL2040.constant.NLAL2040Constant.BIZ_APP_ID;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.FUNCTION_CD_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLAL2040.NLAL2040CMsg;
import business.servlet.NLAL2040.common.NLAL2040CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Business ID : NLAL2040 MODELS-CLICKS Inventory Valuation Entry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/18/2016   CITS            T.Kikuhara      Create          N/A
 *</pre>
 */
public class NLAL2040Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NLAL2040BMsg scrnMsg = (NLAL2040BMsg) bMsg;
        NLAL2040CMsg bizMsg = new NLAL2040CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NLAL2040BMsg scrnMsg = (NLAL2040BMsg) bMsg;
        NLAL2040CMsg bizMsg  = (NLAL2040CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NLAL2040CommonLogic.cntrlScreen(this, scrnMsg);
    }
}
