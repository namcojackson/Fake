/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3170;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3170.NLBL3170CMsg;
import business.servlet.NLBL3170.common.NLBL3170CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Tracking Number Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/02/28   CITS            T.Hakodate      Create          QC#21913
 *</pre>
 */
public class NLBL3170Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // do nothing

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3170BMsg scrnMsg = (NLBL3170BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.T);
        ZYPTableUtil.clear(scrnMsg.A);
        ZYPEZDItemValueSetter.setValue(scrnMsg.glblCmpyCd, getGlobalCompanyCode());

        NLBL3170CMsg bizMsg = new NLBL3170CMsg();
        bizMsg.setBusinessID("NLBL3170");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3170BMsg scrnMsg = (NLBL3170BMsg) bMsg;
        NLBL3170CMsg bizMsg = (NLBL3170CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLBL3170CommonLogic.setTableBGColor(scrnMsg);
        NLBL3170CommonLogic.initDisplayInfo(this, scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.A.no(0).mstrProNum_A1);

    }
}
