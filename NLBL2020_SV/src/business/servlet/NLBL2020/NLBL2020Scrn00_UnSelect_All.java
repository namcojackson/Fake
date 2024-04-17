/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL2020;

import static business.servlet.NLBL2020.constant.NLBL2020Constant.BUSINESS_ID;
import static business.servlet.NLBL2020.constant.NLBL2020Constant.FUNC_SRCH;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL2020.NLBL2020CMsg;
import business.servlet.NLBL2020.common.NLBL2020CommonLogic;
import business.servlet.NLBL2020.constant.NLBL2020Constant;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Manage Shipping Orders
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/16   CITS            T.Tokutomi      Create          N/A
 * 06/19/2018   CITS            Y.Iwasaki       Update          QC#21717
 *</pre>
 */
public class NLBL2020Scrn00_UnSelect_All extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL2020BMsg scrnMsg = (NLBL2020BMsg) bMsg;

        NLBL2020CMsg bizMsg = new NLBL2020CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

        //return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL2020BMsg scrnMsg = (NLBL2020BMsg) bMsg;
        NLBL2020CMsg bizMsg = (NLBL2020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(NLBL2020Constant.BUSINESS_ID);
        NLBL2020CommonLogic.ctrlScrnItemProtection(scrnMsg, this, funcList);
    }
}
