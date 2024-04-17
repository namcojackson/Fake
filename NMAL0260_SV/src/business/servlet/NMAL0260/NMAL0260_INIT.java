/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0260;

import static business.servlet.NMAL0260.constant.NMAL0260Constant.BTN_08_CLE_GUARD;
import static business.servlet.NMAL0260.constant.NMAL0260Constant.BTN_08_CLE_LABEL;
import static business.servlet.NMAL0260.constant.NMAL0260Constant.BTN_08_CLE_NAME;
import static business.servlet.NMAL0260.constant.NMAL0260Constant.BTN_10_CLO_GUARD;
import static business.servlet.NMAL0260.constant.NMAL0260Constant.BTN_10_CLO_LABEL;
import static business.servlet.NMAL0260.constant.NMAL0260Constant.BTN_10_CLO_NAME;
import parts.common.EZDBDateItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NMAL0260_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/06   Fujitsu         q09083          Create          N/A
 *</pre>
 */
public class NMAL0260_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0260BMsg scrnMsg = (NMAL0260BMsg) bMsg;

        Object[] params = (Object[]) getArgForSubScreen();
        if (params instanceof Object[]) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.mdseCd, (EZDBStringItem) params[0]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.mdseDescShortTxt, (EZDBStringItem) params[1]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.effFromDt, (EZDBDateItem) params[2]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.effFromDt_A1, (EZDBDateItem) params[3]);
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL0260BMsg scrnMsg = (NMAL0260BMsg) bMsg;

        this.setButtonProperties(BTN_08_CLE_NAME, BTN_08_CLE_GUARD, BTN_08_CLE_LABEL, 1, null);
        this.setButtonProperties(BTN_10_CLO_NAME, BTN_10_CLO_GUARD, BTN_10_CLO_LABEL, 1, null);

        scrnMsg.mdseCd.setInputProtected(true);
        scrnMsg.mdseDescShortTxt.setInputProtected(true);

        scrnMsg.setFocusItem(scrnMsg.effFromDt);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NMAL0260BMsg scrnMsg = (NMAL0260BMsg) bMsg;

        scrnMsg.effFromDt.setNameForMessage("Effective Start Date");

    }
}
