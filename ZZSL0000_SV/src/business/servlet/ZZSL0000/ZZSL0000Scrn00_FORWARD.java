/*
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * Original Author: Q01487
 * Company: Fujitsu Limited
 * Date: Jul 22, 2009
 */

package business.servlet.ZZSL0000;

import java.lang.reflect.Field;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDLog;
import parts.servletcommon.EZDApplicationContext;
import parts.servletcommon.EZDCommonHandler;
import parts.transitioncommon.EZDTransition;
import parts.transitioncommon.EZDTransitionContext;
import business.servlet.ZZSL0000.common.ZZSL0000CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandlerEx;
import com.canon.cusa.s21.framework.security.S21SecurityException;
import com.canon.cusa.s21.framework.security.helpers.S21AuthenticationHelper;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 * ZZSL0000Scrn00_FORWARD class is used to authenticate the user. All
 * processings are taken place in this class once user provides the
 * username and the password in the user interface and click on the
 * login button.
 * @author $Author: Katsutoshi Takahashi $ Arti Kumari
 * @version $Revision: 1.9 $ $Date: 2009/07/23 15:57:48 $
 */
public class ZZSL0000Scrn00_FORWARD extends S21CommonHandlerEx {

    /**
     * checkInput method checks the request data from the client. This
     * method checks the authentication of user by calling the
     * loginuser method of S21AuthenticationHelper class based on the
     * loginID and the LoginPW provided on the screen. Once user gets
     * authenticated, then the request is sent for further processing,
     * else an exception message is provided to the user.
     * @param ctx Application context
     * @param bMsg Input from the screen value of the object
     * @return void
     */
    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // ///////////////////////////////////////////////////////
        // Local variable declarations
        // ///////////////////////////////////////////////////////

        ZZSL0000BMsg scrnMsg = (ZZSL0000BMsg) bMsg;

        // ///////////////////////////////////////////////////////
        // Processing logic description
        // ///////////////////////////////////////////////////////
        scrnMsg.addCheckItem(scrnMsg.loginID);
        scrnMsg.addCheckItem(scrnMsg.loginPW);
        scrnMsg.putErrorScreen();

        boolean authSuccess = false;
        String msgStr = null;
        try {
            String securityConfig = ZZSL0000CommonLogic.getProperty(ZZSL0000CommonLogic.ZZSL0000_SECURITY_CONFIG);

            authSuccess = S21AuthenticationHelper.loginUser(ctx, scrnMsg.loginID.getValue(), scrnMsg.loginPW.getValue(), securityConfig);
            if (authSuccess == false) {
                String errorMessage = S21AuthenticationHelper.getLastErrorMessage();

                if (!errorMessage.equalsIgnoreCase(null)) {
                    EZDLog.println(EZDLog.LEVEL_MSG_DUMP, "NOTTTT  NULL" + errorMessage.toString());
                    msgStr = errorMessage;
                    scrnMsg.putErrorScreen();
                } else {
                    EZDLog.println(EZDLog.LEVEL_MSG_DUMP, "NULLL" + errorMessage.toString());
                    msgStr = "Login failed - cause unknown";
                    scrnMsg.putErrorScreen();
                }
                EZDLog.println(EZDLog.LEVEL_MSG_DUMP, "msgStr->" + msgStr);
                bMsg.setMessageInfo("ZZSM0001E", new String[] {msgStr });
                throw new EZDFieldErrorException();
            }
        } catch (S21SecurityException ex) {
            msgStr = ex.getMessage();
            scrnMsg.putErrorScreen();
            EZDLog.println(EZDLog.LEVEL_MSG_DUMP, "msgStr ... ->" + msgStr);
            bMsg.setMessageInfo("ZZSM0001E", new String[] {msgStr });
            throw new EZDFieldErrorException();
        }
    }

    /**
     * setRequestData is used to generate and control parts of the
     * conversation to call the server for a specific information.
     * @param ctx Application context
     * @param bMsg Input from the screen value of the object
     * @return EZDCMsg EZDCMsg class
     */
    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    /**
     * doProcess method process the requests and provides the landing
     * page based on the landing page ID provided in the file
     * ZZSL0000.properties.
     * @param ctx Application Context
     * @param bMsg Input from the screen value of the object
     * @param cMsg Business Info Message
     * @return void
     */
    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // ///////////////////////////////////////////////////////
        // Local variable declarations
        // ///////////////////////////////////////////////////////
        ZZSL0000BMsg scrnMsg = (ZZSL0000BMsg) bMsg;

        // ///////////////////////////////////////////////////////
        // Processing logic description
        // ///////////////////////////////////////////////////////

        String transId = scrnMsg.ezBusinessID.getValue();
        if (transId == null || transId.length() == 0) {
            transId = ZZSL0000CommonLogic.getProperty(ZZSL0000CommonLogic.ZZSL0000_LANDING_PAGE_ID);
        }

        try {
            
            // ADD START - 09/04/2009   T.Nakamatsu
            S21UserProfileService profileService = getUserProfileService();
            String userId = profileService.getContextUserInfo().getUserId();
            String userNm = profileService.getContextUserInfo().getFullName();

            ctx.setAttribute(USER_ID, userId);
            ctx.setAttribute(USER_NAME, userNm);
            ctx.setAttribute(PASSWORD, scrnMsg.loginPW.getValue());
            // ADD END   - 09/04/2009   T.Nakamatsu

            Field transField = EZDCommonHandler.class.getDeclaredField("_trans");
            transField.setAccessible(true);
            EZDTransition transition = (EZDTransition) transField.get(this);

            Field transctxField = EZDCommonHandler.class.getDeclaredField("_transctx");
            transctxField.setAccessible(true);
            EZDTransitionContext transctx = (EZDTransitionContext) transctxField.get(this);

            if (transctx.isRootTrans(transition)) {
                overrideTransition(transId);
            } else {
                // Root業務ではない＝Router経由の場合
                setJumpTransition(transId);
            }
        } catch (Exception e) {
            overrideTransition(transId);
        }
    }
}
