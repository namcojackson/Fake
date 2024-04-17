/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3200;

import static business.servlet.NLBL3200.constant.NLBL3200Constant.BUSINESS_ID;
import static business.servlet.NLBL3200.constant.NLBL3200Constant.DISPLAY_MDSE_CD;
import static business.servlet.NLBL3200.constant.NLBL3200Constant.FUNC_SRCH;
import static business.servlet.NLBL3200.constant.NLBL3200Constant.ZZZM9007E;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3200.NLBL3200CMsg;
import business.servlet.NLBL3200.NLBL3200BMsg;
import business.servlet.NLBL3200.common.NLBL3200CommonLogic;
import business.servlet.NLBL3200.constant.NLBL3200Constant;

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
public class NLBL3200Scrn00_Search_MdseInfo extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3200BMsg scrnMsg = (NLBL3200BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.mdseCd_H1)) {

            scrnMsg.mdseCd_H1.setErrorInfo(1, ZZZM9007E, new String[] {DISPLAY_MDSE_CD });
        }

        // clear html attribute
        initScrenAttribute(scrnMsg);

        scrnMsg.addCheckItem(scrnMsg.mdseCd_H1);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3200BMsg scrnMsg = (NLBL3200BMsg) bMsg;

        NLBL3200CMsg bizMsg = new NLBL3200CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3200BMsg scrnMsg = (NLBL3200BMsg) bMsg;
        NLBL3200CMsg bizMsg = (NLBL3200CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // reset html attribute
        initScrenAttribute(scrnMsg);

        if (scrnMsg.mdseCd_H1.isError()) {

            scrnMsg.addCheckItem(scrnMsg.mdseCd_H1);
            scrnMsg.putErrorScreen();

        } else {

            scrnMsg.setFocusItem(scrnMsg.mdseCd_H1);
        }
    }

    private void initScrenAttribute(NLBL3200BMsg scrnMsg) {
        // reset html attribute
        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(NLBL3200Constant.BUSINESS_ID);
        NLBL3200CommonLogic.ctrlScrnItemProtection(scrnMsg, this, funcList);
    }
}
