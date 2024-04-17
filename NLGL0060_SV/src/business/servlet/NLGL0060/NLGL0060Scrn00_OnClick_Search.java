/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLGL0060;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import static business.servlet.NLGL0060.constant.NLGL0060Constant.BUSINESS_ID;
import static business.servlet.NLGL0060.constant.NLGL0060Constant.FUNC_SRCH;

import business.blap.NLGL0060.NLGL0060CMsg;
import business.servlet.NLGL0060.common.NLGL0060CommonLogic;
import business.servlet.NLGL0060.constant.NLGL0060Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;


/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2020/01/09   CITS            M.Furugoori         Create          N/A
 *</pre>
 */
public class NLGL0060Scrn00_OnClick_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLGL0060BMsg scrnMsg = (NLGL0060BMsg) bMsg;

        NLGL0060CommonLogic.searchItemCheck(scrnMsg);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLGL0060BMsg scrnMsg = (NLGL0060BMsg) bMsg;

        NLGL0060CMsg bizMsg = new NLGL0060CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLGL0060BMsg scrnMsg = (NLGL0060BMsg) bMsg;
        NLGL0060CMsg bizMsg  = (NLGL0060CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_ID);
        NLGL0060CommonLogic.ctrlScrnItem(this, scrnMsg, funcList);
        NLGL0060CommonLogic.ctrlScrnItemSearch(this, scrnMsg, funcList);
        NLGL0060CommonLogic.searchItemCheck(scrnMsg);
        scrnMsg.putErrorScreen();

        NLGL0060CommonLogic.setTableScreen(scrnMsg);

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {

            throw new EZDFieldErrorException();
        }

        scrnMsg.setMessageInfo(NLGL0060Constant.ZZZM9003I, new String[] {"Search"});

    }
}
