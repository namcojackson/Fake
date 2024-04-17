package business.servlet.ZZZL9900;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.PopupTestParameterSetter;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZZZL9900.common.ZZZL9900CommonLogic;
import business.servlet.ZZZL9900.constant.ZZZL9900Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZZL9900Scrn00_SetPopupParameter extends S21CommonHandler implements ZZZL9900Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZZL9900BMsg scrnMsg = (ZZZL9900BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.ezBusinessID_02);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZZL9900BMsg scrnMsg = (ZZZL9900BMsg) bMsg;
        String businessAplID = scrnMsg.ezBusinessID_02.getValue();

        Object[] params = PopupTestParameterSetter.getStubParameters(ZZZL9900CommonLogic.getStubDir(ctx), businessAplID);
        setArgForSubScreen(params);

        ctx.setAttribute("stub_dir", ZZZL9900CommonLogic.getStubDir(ctx) + businessAplID + ".properties");
        ctx.setAttribute("Stub_Parameters", params);
    }

}
