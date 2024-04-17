/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1090;

import static business.servlet.NSAL1090.constant.NSAL1090Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CR_REBIL_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Credit Rebill Detail Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/05   Hitachi         A.Kohinata      Create          N/A
 * 2021/01/12   CITS            R.Shimamoto     Update          QC#58177
 *</pre>
 */
public class NSAL1090Scrn00_CancelCI extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1090BMsg scrnMsg = (NSAL1090BMsg) bMsg;

        // START 2021/01/12 [QC#58177] ADD
        scrnMsg.xxWrnSkipFlg.clear();
        // END 2021/01/12 [QC#58177] ADD
        
        if (!SVC_CR_REBIL_STS.ENTERED.equals(scrnMsg.svcCrRebilStsCd_H.getValue()) && !SVC_CR_REBIL_STS.PENDING_APPROVAL.equals(scrnMsg.svcCrRebilStsCd_H.getValue())) {
            scrnMsg.setMessageInfo(NSAM0065E);
            scrnMsg.svcCrRebilStsDescTxt_H.setErrorInfo(1, NSAM0065E);
        }
        scrnMsg.addCheckItem(scrnMsg.svcCrRebilStsDescTxt_H);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1090BMsg scrnMsg = (NSAL1090BMsg) bMsg;

        setValue(scrnMsg.xxModeCd_P1, NSAL1100_MODE_CANCEL_CI);
        setValue(scrnMsg.svcCrRebilPk_P1, scrnMsg.svcCrRebilPk_H);
        scrnMsg.svcCrRebilProcCd_P1.clear();
        ZYPTableUtil.clear(scrnMsg.P);

        Object[] params = new Object[NSAL1100_PRM_LENGTH];
        int i = 0;
        params[i++] = scrnMsg.xxModeCd_P1;
        params[i++] = scrnMsg.svcCrRebilPk_P1;
        params[i++] = scrnMsg.svcCrRebilProcCd_P1;
        params[i++] = scrnMsg.P;

        setArgForSubScreen(params);
    }
}
