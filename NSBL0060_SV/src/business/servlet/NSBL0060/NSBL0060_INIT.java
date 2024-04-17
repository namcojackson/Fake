/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSBL0060;


import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.io.Serializable;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0060.NSBL0060CMsg;
import business.servlet.NSBL0060.common.NSBL0060CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/20/2013   Hitachi         T.Aoyagi        Create          N/A
 * 10/24/2016   Hitachi         Y.Zhang         Update          QC#13901
 * 06/22/2017   Hitachi         T.Mizuki        Update          QC#18613
 *</pre>
 */
public class NSBL0060_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0060BMsg scrnMsg = (NSBL0060BMsg) bMsg;
        NSBL0060CMsg bizMsg = NSBL0060CommonLogic.createCMsgForSearch();

        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            // START 2016/10/21 Y.Zhang [QC#13901, MOD]
            // mod start 2017/06/22 CSA QC#18613
            if (params != null) {
                EZDBBigDecimalItem svcMachMstrPk = (EZDBBigDecimalItem) params[0];
                if (params.length == 2) {
                    EZDBStringItem svcTaskNum = (EZDBStringItem) params[1];
                    setValue(scrnMsg.svcTaskNum_HD, svcTaskNum);
                }
                setValue(scrnMsg.svcMachMstrPk, svcMachMstrPk);
            // mod end 2017/06/22 CSA QC#18613
            }
        } else {
            scrnMsg.svcMachMstrPk.clear();
            scrnMsg.svcTaskNum_HD.clear();
         // END 2016/10/21 Y.Zhang [QC#13901, MOD]
        }
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0060BMsg scrnMsg = (NSBL0060BMsg) bMsg;
        NSBL0060CMsg bizMsg = (NSBL0060CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSBL0060CommonLogic.initialize(this, scrnMsg);
        NSBL0060CommonLogic.screenControlProcess(this, scrnMsg);
        NSBL0060CommonLogic.setCursorControl(scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.xxFromDt);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NSBL0060BMsg scrnMsg = (NSBL0060BMsg) bMsg;
        scrnMsg.xxFromDt.setNameForMessage("Period(From)");
        scrnMsg.xxToDt.setNameForMessage("Period(To)");
    }
}
