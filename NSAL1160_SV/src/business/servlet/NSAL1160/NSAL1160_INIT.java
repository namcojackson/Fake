/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1160;

import static business.servlet.NSAL1160.constant.NSAL1160Constant.*;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1160.NSAL1160CMsg;
import business.servlet.NSAL1160.common.NSAL1160CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/26/2016   Hitachi         J.Kim           Create          N/A
 * 03/25/2016   Hitachi         A.Kohinata      Update          QC#6051
 * 2017/02/08   Hitachi         K.Kojima        Update          QC#17511
 *</pre>
 */
public class NSAL1160_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL1160BMsg scrnMsg = (NSAL1160BMsg) bMsg;
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1160BMsg scrnMsg = (NSAL1160BMsg) bMsg;

        Serializable arg = getArgForSubScreen();
        NSAL1160CommonLogic.setArgForSubScreen(arg, scrnMsg);
        NSAL1160CMsg bizMsg = NSAL1160CommonLogic.setRequestData_SearchCommon();

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1160BMsg scrnMsg = (NSAL1160BMsg) bMsg;
        NSAL1160CMsg bizMsg = (NSAL1160CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL1160CommonLogic.screenControlProcess(this, scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NSAL1160BMsg scrnMsg = (NSAL1160BMsg) bMsg;

        scrnMsg.procDt_AF.setNameForMessage("View From");
        scrnMsg.procDt_AT.setNameForMessage("View To");
        scrnMsg.procDt_BF.setNameForMessage("View From");
        scrnMsg.procDt_BT.setNameForMessage("View To");
        scrnMsg.cratDt_CF.setNameForMessage("View From");
        scrnMsg.cratDt_CT.setNameForMessage("View To");
    }
}
