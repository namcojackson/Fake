/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0480;

import static business.servlet.NSAL0480.constant.NSAL0480Constant.NSAL0490_PRM_LENGTH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL0480.constant.NSAL0480Constant.MSG_ID;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/10   Fujitsu         M.Yamada        Create          N/A
 *</pre>
 */
public class NSAL0480Scrn00_ViewItem extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0480BMsg scrnMsg = (NSAL0480BMsg) bMsg;

        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());

        if (scrnMsg.xxRadioBtn.isClear()) {
            EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();
            scrnMsg.xxRadioBtn.setErrorInfo(1, MSG_ID.NSAM0199E.toString() //
                    , new String[] {converter.convLabel2i18nLabel("", "Target Item") });
            scrnMsg.addCheckItem(scrnMsg.xxRadioBtn);
            scrnMsg.putErrorScreen();
            return;
        }
        int n = scrnMsg.xxRadioBtn.getValueInt();
        ZYPEZDItemValueSetter.setValue(scrnMsg.t_MdlNm_P, scrnMsg.A.no(n).t_MdlNm_A);

        Object[] params = new Object[NSAL0490_PRM_LENGTH];
        int i = 0;
        params[i] = scrnMsg.t_MdlNm_P;

        setArgForSubScreen(params);
    }
}
