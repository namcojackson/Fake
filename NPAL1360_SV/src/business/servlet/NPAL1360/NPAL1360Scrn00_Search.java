/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1360;

import static business.servlet.NPAL1360.constant.NPAL1360Constant.BIZ_APP_ID;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.NPZM0002E;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1360.NPAL1360CMsg;
import business.servlet.NPAL1360.common.NPAL1360CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Business ID : NPAL1360 Kitting Work Order Entry
 * Function Name : Search
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/13/2016   CITS            Keiichi Masaki  Create          N/A
 *</pre>
 */
public class NPAL1360Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1360BMsg scrnMsg = (NPAL1360BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.wrkOrdNum)) {
            scrnMsg.wrkOrdNum.setErrorInfo(1, NPZM0002E);
        }

        scrnMsg.addCheckItem(scrnMsg.wrkOrdNum);
        scrnMsg.putErrorScreen();
   }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1360BMsg scrnMsg = (NPAL1360BMsg) bMsg;

        NPAL1360CMsg bizMsg = new NPAL1360CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1360BMsg scrnMsg = (NPAL1360BMsg) bMsg;
        NPAL1360CMsg bizMsg  = (NPAL1360CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.putErrorScreen();

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_APP_ID);
        NPAL1360CommonLogic.ctrlScrnItemDispInit(this, scrnMsg, funcList);

        // set focus
        scrnMsg.setFocusItem(scrnMsg.wrkOrdNum);

    }
}
