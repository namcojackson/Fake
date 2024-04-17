/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0340;

import static business.servlet.NSBL0340.constant.NSBL0340Constant.BUSINESS_ID;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0340.NSBL0340CMsg;
import business.servlet.NSBL0340.common.NSBL0340CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Service Task Summary
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/19   Hitachi         J.Kim           Create          N/A
 * 2016/12/01   Hitachi         T.Mizuki        Update          QC#16116
 *</pre>
 */
public class NSBL0340_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // mod start 2016/12/01 CSA QC#16116
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
        // mod end 2016/12/01 CSA QC#16116
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0340BMsg scrnMsg = (NSBL0340BMsg) bMsg;

        Serializable arg = getArgForSubScreen();
        NSBL0340CommonLogic.setArgForSubScreen(arg, scrnMsg);

        NSBL0340CMsg bizMsg = NSBL0340CommonLogic.setRequestData_SearchCommon();
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0340BMsg scrnMsg = (NSBL0340BMsg) bMsg;
        NSBL0340CMsg bizMsg = (NSBL0340CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSBL0340CommonLogic.screenControlProcess(this, scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
    }
}
