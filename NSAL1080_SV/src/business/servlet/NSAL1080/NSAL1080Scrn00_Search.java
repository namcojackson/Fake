/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1080;

import static business.servlet.NSAL1080.constant.NSAL1080Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL1080.NSAL1080CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Credit Rebill Main Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/20   Hitachi         A.Kohinata      Create          N/A
 *</pre>
 */
public class NSAL1080Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1080BMsg scrnMsg = (NSAL1080BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.svcInvNum);
        scrnMsg.addCheckItem(scrnMsg.dsContrNum);
        scrnMsg.addCheckItem(scrnMsg.conslBillPk);
        scrnMsg.addCheckItem(scrnMsg.serNum);
        scrnMsg.addCheckItem(scrnMsg.bllgPerFromDt);
        scrnMsg.addCheckItem(scrnMsg.bllgPerToDt);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1080BMsg scrnMsg = (NSAL1080BMsg) bMsg;
        NSAL1080CMsg bizMsg = new NSAL1080CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1080BMsg scrnMsg = (NSAL1080BMsg) bMsg;
        NSAL1080CMsg bizMsg = (NSAL1080CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.svcInvNum);
        scrnMsg.addCheckItem(scrnMsg.dsContrNum);
        scrnMsg.addCheckItem(scrnMsg.conslBillPk);
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        setValue(scrnMsg.svcInvNum_P2, scrnMsg.svcInvNum);
        setValue(scrnMsg.dsContrNum_P2, scrnMsg.dsContrNum);
        setValue(scrnMsg.conslBillPk_P2, scrnMsg.conslBillPk);
        setValue(scrnMsg.serNum_P2, scrnMsg.serNum);
        setValue(scrnMsg.bllgPerFromDt_P2, scrnMsg.bllgPerFromDt);
        setValue(scrnMsg.bllgPerToDt_P2, scrnMsg.bllgPerToDt);
        scrnMsg.svcCrRebilPk_P2.clear();

        Object[] params = new Object[NSAL1030_PRM_LENGTH];
        int i = 0;
        params[i++] = scrnMsg.svcInvNum_P2;
        params[i++] = scrnMsg.dsContrNum_P2;
        params[i++] = scrnMsg.conslBillPk_P2;
        params[i++] = scrnMsg.serNum_P2;
        params[i++] = scrnMsg.bllgPerFromDt_P2;
        params[i++] = scrnMsg.bllgPerToDt_P2;
        params[i++] = scrnMsg.svcCrRebilPk_P2;

        setArgForSubScreen(params);
    }
}
