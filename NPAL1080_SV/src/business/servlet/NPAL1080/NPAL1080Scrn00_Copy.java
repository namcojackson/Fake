/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1080;

import static business.servlet.NPAL1080.constant.NPAL1080Constant.BUSINESS_APPLICATION_ID;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.MODE_BTN_COPY;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.NAMM0027E;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.RUN_MODE_COPIED;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1080.NPAL1080CMsg;
import business.servlet.NPAL1080.common.NPAL1080CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1080 Tech Parts Request Entry
 * Function Name : Button Action to Copy
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/16/2015   CITS       Yasushi Nomura        Create          N/A
 *</pre>
 */
public class NPAL1080Scrn00_Copy extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
        if (!ZYPCommonFunc.hasValue(scrnMsg.prchReqNum_H1)) {
            scrnMsg.prchReqNum_H1.setErrorInfo(1, NAMM0027E, new String[] {"Requisition#" });
            scrnMsg.addCheckItem(scrnMsg.prchReqNum_H1);
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;

        NPAL1080CMsg bizMsg = new NPAL1080CMsg();
        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
        NPAL1080CMsg bizMsg = (NPAL1080CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.putErrorScreen();

        NPAL1080CommonLogic.commonInit(scrnMsg);
        scrnMsg.xxEdtModeFlg.setValue(RUN_MODE_COPIED);
        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_APPLICATION_ID);
        NPAL1080CommonLogic.control(this, scrnMsg, MODE_BTN_COPY, funcList);

    }
}
