/*
 * Copyright (c) 2008 Canon USA Inc. All rights reserved.
 * Original Author: ${user}
 * Company: Fujitsu Limited
 * Date: ${date}
 */

package business.servlet.ZZSL0000;

import java.text.SimpleDateFormat;
import java.util.Date;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * ZZSL0000_INIT class is used to initialize the Screen application.
 * @author $Author: Katsutoshi Takahashi $ Arti Kumari
 * @version $Revision: 1.8 $ $Date: 2009/07/23 15:57:48 $
 */
public class ZZSL0000_INIT extends S21CommonHandler {

    /**
     * checkInput method checks the request data from the client.
     * @param ctx Application Context
     * @param bMsg Input from the screen value of the object
     * @return void
     */
    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    /**
     * setRequestData is used to generate and control parts of the
     * conversation to call the server for a specific information.
     * @param ctx Application context
     * @param bMsg Input from the screen value of the object
     * @return EZDCMsg EZDCMsg class object
     */
    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    /**
     * doProcess method process the requests.It sets the required
     * buttons and login time in the application context.
     * @param ctx Application Context
     * @param bMsg Input from the screen value of the object
     * @param cMsg Business Info Message
     * @return void
     */
    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

         ZZSL0000BMsg scrnMsg = (ZZSL0000BMsg)bMsg;

        // Check for timeout
        if(ctx.getAttribute(LogoffTimeoutHandler.TIMEOUT_FLG) != null) {
            scrnMsg.setMessageInfo("ZZSM40001I");
            ctx.getHttpServletRequest().getSession().removeAttribute(LogoffTimeoutHandler.TIMEOUT_FLG);
        }

        // ///////////////////////////////////////////////////////
        // Processing logic description
        // ///////////////////////////////////////////////////////

        // set EZ common item
        Date date = new Date();
        ctx.setAttribute(LOGINTIME, new SimpleDateFormat("yyyyMMddHHmmssSSS").format(date));
        ctx.setAttribute(LOGINTIMEZONE, new SimpleDateFormat("Z").format(date));

        String ezBusinessID = (String) ctx.getAttribute("ezBusinessID");
        if (ezBusinessID != null) {
            scrnMsg.ezBusinessID.setValue(ezBusinessID);
        }

        // set button
        setButtonProperties("btn1", "CLOSE", "QUIT", 1, null);
        setButtonProperties("btn2", "", "", 0, null);
        setButtonProperties("btn3", "", "", 0, null);
        setButtonProperties("btn4", "", "", 0, null);
        setButtonProperties("btn5", "", "", 0, null);
        setButtonProperties("btn6", "", "", 0, null);
        setButtonProperties("btn7", "", "", 0, null);
        setButtonProperties("btn8", "", "", 0, null);
        setButtonProperties("btn9", "", "", 0, null);
        setButtonProperties("btn10", "", "", 0, null);

        //Set focus on the User ID input 
        scrnMsg.setFocusItem(scrnMsg.loginID);
        return;
    }
}
