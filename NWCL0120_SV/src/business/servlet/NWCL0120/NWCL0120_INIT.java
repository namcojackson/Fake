/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0120;

import static business.servlet.NWCL0120.common.NWCL0120CommonLogic.initCmnBtnProp;
import static business.servlet.NWCL0120.common.NWCL0120CommonLogic.setSearchInvoiceDate;
import static business.servlet.NWCL0120.constant.NWCL0120Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWCL0120.NWCL0120CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NWCL0120_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWCL0120BMsg scrnMsg = (NWCL0120BMsg) bMsg;

        NWCL0120CMsg bizMsg = new NWCL0120CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWCL0120BMsg scrnMsg = (NWCL0120BMsg) bMsg;
        NWCL0120CMsg bizMsg = (NWCL0120CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        String glblCmpyCd = getGlobalCompanyCode();
        setSearchInvoiceDate(scrnMsg, glblCmpyCd);

        initCmnBtnProp(this);
        scrnMsg.setFocusItem(scrnMsg.invDt_FM);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NWCL0120BMsg scrnMsg = (NWCL0120BMsg) bMsg;

        scrnMsg.invDt_FM.setNameForMessage("Invoice Date From");
        scrnMsg.invDt_TO.setNameForMessage("Invoice Date To");
    }
}
