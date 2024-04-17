/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0250;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0250.NLCL0250CMsg;
import business.servlet.NLCL0250.common.NLCL0250CommonLogic;
import business.servlet.NLCL0250.constant.NLCL0250Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/14/2015   CSAI            Y.Imazu         Create          N/A
 * 09/14/2016   CSAI            Y.Imazu         Update          QC#13187
 *</pre>
 */
public class NLCL0250Scrn00_Search_RtlWHInfo extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0250BMsg scrnMsg = (NLCL0250BMsg) bMsg;

        NLCL0250CommonLogic.chkMndFld(scrnMsg.rtlWhCdSrchTxt_RW, "Warehouse Code");
        scrnMsg.addCheckItem(scrnMsg.rtlWhCdSrchTxt_RW);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0250BMsg scrnMsg = (NLCL0250BMsg) bMsg;
        NLCL0250CMsg bizMsg = new NLCL0250CMsg();

        bizMsg.setBusinessID(NLCL0250Constant.BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0250BMsg scrnMsg = (NLCL0250BMsg) bMsg;
        NLCL0250CMsg bizMsg = (NLCL0250CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLCL0250CommonLogic.controlScreen(getUserProfileService(), this, scrnMsg);
        scrnMsg.addCheckItem(scrnMsg.rtlWhCdSrchTxt_RW);
        scrnMsg.putErrorScreen();

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {

            throw new EZDFieldErrorException();
        }

        // Set Focus
        scrnMsg.setFocusItem(scrnMsg.rtlWhCdSrchTxt_RW);
    }
}