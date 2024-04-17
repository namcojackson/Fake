/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0610;

import static business.servlet.NSAL0610.common.NSAL0610CommonLogic.initialControlScreen;
import static business.servlet.NSAL0610.constant.NSAL0610Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0610.NSAL0610CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Copy Contract
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/01   Hitachi         T.Iwamoto         Create          N/A
 *</pre>
 */
public class NSAL0610_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);

        Object[] params = (Object[]) getArgForSubScreen();
        if (params == null || params.length == 0 || params[0] == null) {
            bMsg.setMessageInfo(NSAM0353E, new String[] {ERR_PRAM_NO_INPUT });
            bMsg.putErrorScreen();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0610BMsg scrnMsg = (NSAL0610BMsg) bMsg;

        Object[] params = (Object[]) getArgForSubScreen();
        if (params != null && params.length > 0 && params[0] != null && params[0] instanceof EZDBBigDecimalItem) {
            setValue(scrnMsg.dsContrPk, (EZDBBigDecimalItem) params[0]);
        }

        NSAL0610CMsg bizMsg = new NSAL0610CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0610BMsg scrnMsg = (NSAL0610BMsg) bMsg;
        NSAL0610CMsg bizMsg = (NSAL0610CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        setValue(scrnMsg.xxHldFlg, ZYPConstant.FLG_OFF_N);
        initialControlScreen(this, scrnMsg);

        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL0610BMsg scrnMsg = (NSAL0610BMsg) bMsg;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).xxChkBox_A.setNameForMessage("Select");
        }
        for (int i = 0; i < scrnMsg.N.getValidCount(); i++) {
            scrnMsg.N.no(i).xxChkBox_N.setNameForMessage("Select");
        }
    }
}
