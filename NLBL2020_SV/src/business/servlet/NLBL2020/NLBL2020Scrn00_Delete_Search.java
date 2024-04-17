/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL2020;

import static business.servlet.NLBL2020.constant.NLBL2020Constant.BUSINESS_ID;
import static business.servlet.NLBL2020.constant.NLBL2020Constant.DISPLAY_SAVE_SRCH_OPT;
import static business.servlet.NLBL2020.constant.NLBL2020Constant.FUNC_UPD;
import static business.servlet.NLBL2020.constant.NLBL2020Constant.NLZM2274E;
import static business.servlet.NLBL2020.constant.NLBL2020Constant.ZZM9037E;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL2020.NLBL2020CMsg;
import business.servlet.NLBL2020.common.NLBL2020CommonLogic;
import business.servlet.NLBL2020.constant.NLBL2020Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Manage Shipping Orders
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/16   CITS            T.Tokutomi      Create          N/A
 *</pre>
 */
public class NLBL2020Scrn00_Delete_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL2020BMsg scrnMsg = (NLBL2020BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.srchOptPk_PS)) {
            scrnMsg.srchOptPk_PS.setErrorInfo(1, NLZM2274E, new String[] {DISPLAY_SAVE_SRCH_OPT });
            scrnMsg.setMessageInfo(ZZM9037E);
        }

        // clear html attribute
        initScrenAttribute(scrnMsg);

        scrnMsg.addCheckItem(scrnMsg.srchOptPk_PS);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL2020BMsg scrnMsg = (NLBL2020BMsg) bMsg;

        NLBL2020CMsg bizMsg = new NLBL2020CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_UPD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL2020BMsg scrnMsg = (NLBL2020BMsg) bMsg;
        NLBL2020CMsg bizMsg = (NLBL2020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // reset html attribute
        initScrenAttribute(scrnMsg);

        if (scrnMsg.srchOptPk_PS.isError()) {
            scrnMsg.addCheckItem(scrnMsg.srchOptPk_PS);
            scrnMsg.putErrorScreen();
        } else {
            scrnMsg.setFocusItem(scrnMsg.srchOptPk_PS);

        }
    }

    private void initScrenAttribute(NLBL2020BMsg scrnMsg) {
        // reset html attribute
        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(NLBL2020Constant.BUSINESS_ID);
        NLBL2020CommonLogic.ctrlScrnItemProtection(scrnMsg, this, funcList);
    }
}
