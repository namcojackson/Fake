/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NPAL1420;

import static business.servlet.NPAL1420.constant.NPAL1420Constant.BUSINESS_APPLICATION_ID;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1420.NPAL1420CMsg;
import business.servlet.NPAL1420.common.NPAL1420CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1420 Reman Task Entry
 * Function Name : 
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 5/17/2016   CITS       Yasushi Nomura       Create          N/A
 *</pre>
 */
public class NPAL1420Scrn00_CMN_Delete extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1420BMsg scrnMsg = (NPAL1420BMsg) bMsg;

        NPAL1420CMsg bizMsg = new NPAL1420CMsg();
        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1420BMsg scrnMsg = (NPAL1420BMsg) bMsg;
        NPAL1420CMsg bizMsg = (NPAL1420CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // init
        NPAL1420CommonLogic.setAppFracDigit(scrnMsg);
        NPAL1420CommonLogic.initCommonButton(this);
        NPAL1420CommonLogic.commonInit(scrnMsg);
        NPAL1420CommonLogic.clearGUIAttribute(scrnMsg, scrnMsg.A.no(0).getBaseContents());

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_APPLICATION_ID);
        NPAL1420CommonLogic.control(this, scrnMsg, funcList);
        NPAL1420CommonLogic.setTableColor(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.rmnfTaskDescTxt);
    }
}
