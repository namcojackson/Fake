/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1430;

import static business.servlet.NPAL1430.constant.NPAL1430Constant.BIZ_APP_ID;
import static business.servlet.NPAL1430.constant.NPAL1430Constant.FUNCTION_CD_SEARCH;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1430.NPAL1430CMsg;
import business.servlet.NPAL1430.common.NPAL1430CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * <pre>
 * Business ID : NPAL1430 Reman Standard Parts Setup
 * Function Name : Init
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/18/2016   CITS            S.Tanikawa      Create          N/A
 *</pre>
 */
public class NPAL1430_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_APP_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1430BMsg scrnMsg = (NPAL1430BMsg) bMsg;

        NPAL1430CMsg bizMsg = new NPAL1430CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1430BMsg scrnMsg = (NPAL1430BMsg) bMsg;

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_APP_ID);

        NPAL1430CommonLogic.ctrlScrnItemDispInit(this, scrnMsg, funcList);

        // Set Forcus
        scrnMsg.setFocusItem(scrnMsg.t_MdlNm);
    }

    @Override
    protected void setNameForMessage(EZDBMsg scrnMsg) {
        // Search Condition
        NPAL1430BMsg scMsg = (NPAL1430BMsg) scrnMsg;
        scMsg.t_MdlNm.setNameForMessage("Model");
        for (int i = 0; i < scMsg.A.length(); i++) {
            scMsg.A.no(i).mdseCd_A1.setNameForMessage("Item Number");
            scMsg.A.no(i).rmnfReqQty_A1.setNameForMessage("Qty");
        }
    }
}
