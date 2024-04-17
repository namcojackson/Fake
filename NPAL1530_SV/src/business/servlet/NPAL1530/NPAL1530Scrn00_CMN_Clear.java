/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1530;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1530.NPAL1530CMsg;
import business.servlet.NPAL1530.common.NPAL1530CommonLogic;
import static business.servlet.NPAL1530.constant.NPAL1530Constant.BIZ_APP_ID;
import static business.servlet.NPAL1530.constant.NPAL1530Constant.FUNCTION_CD_SEARCH;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Business ID : NPAL1530 Min-Max Planning Report Run Screen
 * Function Name : Common Clear
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/24/2016   CITS            Keiichi Masaki  Create          N/A
 *</pre>
 */
public class NPAL1530Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1530BMsg scrnMsg = (NPAL1530BMsg) bMsg;
        scrnMsg.clear();

        NPAL1530CMsg bizMsg = new NPAL1530CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1530BMsg scrnMsg = (NPAL1530BMsg) bMsg;
        NPAL1530CMsg bizMsg  = (NPAL1530CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_APP_ID);
        NPAL1530CommonLogic.ctrlScrnItemDispInit(this, scrnMsg, funcList);

        // Set Focus (Plan Name)
        scrnMsg.setFocusItem(scrnMsg.xxCmntTxt_SL);

    }
}
