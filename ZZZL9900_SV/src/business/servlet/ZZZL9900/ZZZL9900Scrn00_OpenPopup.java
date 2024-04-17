package business.servlet.ZZZL9900;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.PopupTestParameterSetter;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZZZL9900.common.ZZZL9900CommonLogic;
import business.servlet.ZZZL9900.constant.ZZZL9900Constant;

import com.canon.cusa.s21.framework.common.logger.S21Logger;
import com.canon.cusa.s21.framework.common.logger.S21LoggerFactory;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandlerEx;
import com.canon.cusa.s21.framework.security.S21SecurityException;
import com.canon.cusa.s21.framework.security.helpers.S21AuthenticationHelper;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;
import com.canon.cusa.s21.framework.userprofile.impl.mock.UserProfileServiceLoginUserMockImpl;

public class ZZZL9900Scrn00_OpenPopup extends S21CommonHandlerEx implements ZZZL9900Constant {
    private static final S21Logger logger = S21LoggerFactory.getLogger(ZZZL9900Scrn00_OnBusiness.class);

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZZL9900BMsg scrnMsg = (ZZZL9900BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.ezBusinessID_02);
        scrnMsg.addCheckItem(scrnMsg.ezUserID_02);
        scrnMsg.addCheckItem(scrnMsg.ezUserName_02);
        scrnMsg.addCheckItem(scrnMsg.ezPassword_02);
        scrnMsg.addCheckItem(scrnMsg.ezScreenID_02);
        scrnMsg.putErrorScreen();

        boolean isAuthenticated = false;

        S21UserProfileService upService;

        try {
            // TODO: workaround : until security API is fixed.
            upService = S21UserProfileServiceFactory.getInstance().getService();
        } catch (Throwable th) {
            logger.error("S21UserProfileServiceFactory got exception.", th);
            if (th instanceof Exception) {
                throw new S21AbendException("S21UserProfileServiceFactory got an Exception. msg=" + (Exception) th);
            } else {
                throw new S21AbendException("S21UserProfileServiceFactory got a Throwable. msg=" + th.getMessage());
            }
        }

        if (S21UserProfileServiceFactory.getInstance().isMock()) {
            try {
                ((UserProfileServiceLoginUserMockImpl) upService).setCurrentUser(scrnMsg.ezUserID_02.getValue());
                isAuthenticated = true;
            } catch (Throwable th) {
                throw new S21AbendException("User, '" + scrnMsg.ezUserID_02.getValue() + "', is not defined in S21UserProfileMock.xml");
            }
        } else {
            try {
                // Get application context - can be NULL
                EZDApplicationContext appContext = null;
                // Specify user name
                String username = scrnMsg.ezUserID_02.getValue();
                // Specify password
                String password = scrnMsg.ezPassword_02.getValue();
                // Specify configuration - "default" if using default
                // configuration
                String securityConfiguration = "default";
                isAuthenticated = S21AuthenticationHelper.loginUser(appContext, username, password, securityConfiguration);
            } catch (S21SecurityException e) {
                logger.error("Failed to log in. user ID=" + scrnMsg.ezUserID_02.getValue());
                throw new S21AbendException(e, "ZZSM1025E");
            }
        }

        if (!isAuthenticated) {
            scrnMsg.setMessageInfo("ZZSM1061E");
            throw new EZDFieldErrorException();
        }
    }


    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZZL9900BMsg scrnMsg = (ZZZL9900BMsg) bMsg;

        // CHG START - 11/13/2009   T.Nakamatsu
        //ctx.setAttribute(USER_ID, scrnMsg.ezUserID_02.getValue());
        S21UserProfileService profileService = getUserProfileService();
        String userId = profileService.getContextUserInfo().getUserId();
        ctx.setAttribute(USER_ID, userId);

        // CHG END   - 11/13/2009   T.Nakamatsu
        ctx.setAttribute(USER_NAME, scrnMsg.ezUserName_02.getValue());
        ctx.setAttribute(PASSWORD, scrnMsg.ezPassword_02.getValue());

        String businessAplID = scrnMsg.ezBusinessID_02.getValue();
        Object[] params = PopupTestParameterSetter.getStubParameters(ZZZL9900CommonLogic.getStubDir(ctx), businessAplID);
        setArgForSubScreen(params);

        ctx.setAttribute("stub_dir", ZZZL9900CommonLogic.getStubDir(ctx) + businessAplID + ".properties");
        ctx.setAttribute("Stub_Parameters", params);

        overrideTransition(businessAplID);
    }

}
