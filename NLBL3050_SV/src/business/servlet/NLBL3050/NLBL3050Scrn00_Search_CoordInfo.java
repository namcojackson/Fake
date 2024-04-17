/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3050;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3050.NLBL3050CMsg;
import business.servlet.NLBL3050.constant.NLBL3050Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/06   CUSA            T.Tokutomi      Create          #9760
 *</pre>
 */
public class NLBL3050Scrn00_Search_CoordInfo extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3050BMsg scrnMsg = (NLBL3050BMsg) bMsg;
        if (!ZYPCommonFunc.hasValue(scrnMsg.psnCd_H)) {
            scrnMsg.psnCd_H.setErrorInfo(1, "ZZM9000E", new String[] {"Coordinator" });
        }

        scrnMsg.addCheckItem(scrnMsg.psnCd_H);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3050BMsg scrnMsg = (NLBL3050BMsg) bMsg;

        NLBL3050CMsg bizMsg = new NLBL3050CMsg();
        bizMsg.setBusinessID(NLBL3050Constant.BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3050BMsg scrnMsg = (NLBL3050BMsg) bMsg;
        NLBL3050CMsg bizMsg = (NLBL3050CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.psnCd_H);
        scrnMsg.putErrorScreen();

        scrnMsg.setFocusItem(scrnMsg.psnCd_H);
    }
}
