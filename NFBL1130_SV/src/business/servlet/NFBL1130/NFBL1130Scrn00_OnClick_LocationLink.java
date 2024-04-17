/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL1130;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFBL1130.NFBL1130CMsg;
//import business.servlet.NFBL1130.constant.NFBL1130Constant;

import business.servlet.NFBL1130.constant.NFBL1130Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   CUSA            Y.Aikawa        Create          N/A
 *</pre>
 */
public class NFBL1130Scrn00_OnClick_LocationLink extends S21CommonHandler implements NFBL1130Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

//        NFBL1130BMsg scrnMsg = (NFBL1130BMsg) bMsg;
//
//        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
//        scrnMsg.xxCellIdx.setValue(getButtonSelectNumber());
//
//        Object[] params = NFBL1130CommonLogic.getParamNWAL1130ForVndLocLink(scrnMsg, this.getGlobalCompanyCode());
//        setArgForSubScreen(params);

    }
}
