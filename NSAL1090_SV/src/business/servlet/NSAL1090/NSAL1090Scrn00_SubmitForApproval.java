/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1090;

import static business.servlet.NSAL1090.constant.NSAL1090Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL1090.NSAL1090CMsg;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CR_REBIL_STS;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Credit Rebill Detail Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/05   Hitachi         A.Kohinata      Create          N/A
 * 2021/01/12   CITS            R.Shimamoto     Update          QC#58177
 * 2023/07/06   CITS            T.Aizawa        Update          QC#59538
 *</pre>
 */
public class NSAL1090Scrn00_SubmitForApproval extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1090BMsg scrnMsg = (NSAL1090BMsg) bMsg;
        if (!SVC_CR_REBIL_STS.ENTERED.equals(scrnMsg.svcCrRebilStsCd_H.getValue())) {
            scrnMsg.setMessageInfo(NSAM0065E);
            scrnMsg.svcCrRebilStsDescTxt_H.setErrorInfo(1, NSAM0065E);
        }
        scrnMsg.addCheckItem(scrnMsg.svcCrRebilStsDescTxt_H);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1090BMsg scrnMsg = (NSAL1090BMsg) bMsg;
        NSAL1090CMsg bizMsg = new NSAL1090CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1090BMsg scrnMsg = (NSAL1090BMsg) bMsg;
        NSAL1090CMsg bizMsg = (NSAL1090CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        setValue(scrnMsg.xxModeCd_P1, NSAL1100_MODE_SUBMIT_FOR_APPROVAL);
        setValue(scrnMsg.svcCrRebilPk_P1, scrnMsg.svcCrRebilPk_H);
        setValue(scrnMsg.svcCrRebilProcCd_P1, scrnMsg.svcCrRebilProcCd_H);

        Object[] params = new Object[NSAL1100_PRM_LENGTH];
        int i = 0;
        params[i++] = scrnMsg.xxModeCd_P1;
        params[i++] = scrnMsg.svcCrRebilPk_P1;
        params[i++] = scrnMsg.svcCrRebilProcCd_P1;
        params[i++] = scrnMsg.P;

        setArgForSubScreen(params);

        // START 2023/07/06 t.aizawa [QC#59538,ADD]
        scrnMsg.addCheckItem(scrnMsg.custIncdtId_H);
        scrnMsg.putErrorScreen();
        // END   2023/07/06 t.aizawa [QC#59538,ADD]

        // START 2021/01/12 [QC#58177] ADD
        if ("E".equals(bizMsg.getMessageKind()) || "W".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        // END 2021/01/12 [QC#58177] ADD
    }
}
