package business.servlet.ZZZL9900;


import java.text.SimpleDateFormat;
import java.util.Date;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZZZL9900.common.ZZZL9900CommonLogic;
import business.servlet.ZZZL9900.constant.ZZZL9900Constant;

import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZZL9900_INIT extends S21CommonHandler implements ZZZL9900Constant {

    @Override
    protected void checkInput( EZDApplicationContext ctx, EZDBMsg bMsg ) {
    }

     @Override
    protected EZDCMsg setRequestData( EZDApplicationContext ctx, EZDBMsg bMsg ) {
         return null;
    }

    @Override
    protected void doProcess( EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg ) {

        ZZZL9900BMsg scrnMsg = (ZZZL9900BMsg)bMsg;
        String str = null;

        // Stub
        ctx.setAttribute("stub_dir", ZZZL9900CommonLogic.getStubDir(ctx));

        Date date = new Date();
        ctx.setAttribute(LOGINTIME, new SimpleDateFormat("yyyyMMddHHmmssSSS").format(date));
        ctx.setAttribute(LOGINTIMEZONE, new SimpleDateFormat("Z").format(date));

        // Main Screen Test
        String startBizId = ctx.getHttpServletRequest().getParameter("ezBusinessID");
        if (S21StringUtil.isNotEmpty(startBizId)) {
            scrnMsg.ezBusinessID_01.setValue(startBizId);
        } else {
            str = ZZZL9900CommonLogic.getProperty("ID");
            if (S21StringUtil.isNotEmpty(str)) {
                scrnMsg.ezBusinessID_01.setValue(str);
            } else {
                scrnMsg.ezBusinessID_01.setValue("ZZZL9100");
            }
        }

        String ezUserID = ctx.getHttpServletRequest().getParameter(USER_ID);
        if (S21StringUtil.isNotEmpty(ezUserID)) {
            scrnMsg.ezUserID_01.setValue(ezUserID);
        } else {
            str = ZZZL9900CommonLogic.getProperty("user.id");
            if (S21StringUtil.isNotEmpty(str)) {
                scrnMsg.ezUserID_01.setValue(str);
            } else {
                scrnMsg.ezUserID_01.setValue("testUserID");
            }
        }

        String ezPassword = ctx.getHttpServletRequest().getParameter(PASSWORD);
        if (S21StringUtil.isNotEmpty(ezPassword)) {
            scrnMsg.ezPassword_01.setValue(ezPassword);
        } else {
            str = ZZZL9900CommonLogic.getProperty("user.pass");
            if (S21StringUtil.isNotEmpty(str)) {
                scrnMsg.ezPassword_01.setValue(str);
            } else {
                scrnMsg.ezPassword_01.setValue("testUserPass");
            }
        }

        str = ZZZL9900CommonLogic.getProperty("user.name");
        if (S21StringUtil.isNotEmpty(str)) {
            scrnMsg.ezUserName_01.setValue(str);
        } else {
            scrnMsg.ezUserName_01.setValue("testUserNm");
        }

        // Popup Screen Test
        scrnMsg.ezScreenID_01.setValue(scrnMsg.ezBusinessID_01.getValue() + "Scrn00");
        scrnMsg.setFocusItem(scrnMsg.ezBusinessID_01);

        scrnMsg.ezBusinessID_02.setNameForMessage( scrnMsg.ezBusinessID_01.getNameForMessage() );
        scrnMsg.ezUserID_02.setNameForMessage( scrnMsg.ezUserID_01.getNameForMessage() );
        scrnMsg.ezUserName_02.setNameForMessage( scrnMsg.ezUserName_01.getNameForMessage() );
        scrnMsg.ezPassword_02.setNameForMessage( scrnMsg.ezPassword_01.getNameForMessage() );
        scrnMsg.ezBusinessID_02.setValue( scrnMsg.ezBusinessID_01.getValue() );
        scrnMsg.ezUserID_02.setValue( scrnMsg.ezUserID_01.getValue() );
        scrnMsg.ezUserName_02.setValue( scrnMsg.ezUserName_01.getValue() );
        scrnMsg.ezPassword_02.setValue( scrnMsg.ezPassword_01.getValue() );
        scrnMsg.ezScreenID_02.setValue( scrnMsg.ezScreenID_01.getValue() );

        setButtonProperties("btn1", "", "", 0, null);
        setButtonProperties("btn2", "", "", 0, null);
        setButtonProperties("btn3", "", "", 0, null);
        setButtonProperties("btn4", "", "", 0, null);
        setButtonProperties("btn5", "", "", 0, null);
        setButtonProperties("btn6", "", "", 0, null);
        setButtonProperties("btn7", "", "", 0, null);
        setButtonProperties("btn8", "", "", 0, null);
        setButtonProperties("btn9", "", "", 0, null);
        setButtonProperties("btn10",    "Exit", "Exit", 1, null);
     }

}