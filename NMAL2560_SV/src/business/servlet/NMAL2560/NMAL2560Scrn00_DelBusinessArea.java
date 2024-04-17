/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2560;

import static business.servlet.NMAL2560.constant.NMAL2560Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2560.NMAL2560CMsg;
import business.servlet.NMAL2560.common.NMAL2560CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL2560Scrn00_DelBusinessArea
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/13   Fujitsu         T.Ogura         Create          N/A
 *</pre>
 */
public class NMAL2560Scrn00_DelBusinessArea extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // doNothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2560BMsg scrnMsg = (NMAL2560BMsg) bMsg;

        NMAL2560CMsg bizMsg = new NMAL2560CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2560BMsg scrnMsg = (NMAL2560BMsg) bMsg;
        NMAL2560CMsg bizMsg = (NMAL2560CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL2560CommonLogic.controlScreenFields(getUserProfileService(), ZYPDateUtil.getSalesDate(getGlobalCompanyCode()), this, scrnMsg, getLastGuard());

        scrnMsg.setFocusItem(scrnMsg.bizAreaOrgCd);
    }
}
