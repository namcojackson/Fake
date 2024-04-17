/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1090;

import static business.servlet.NSAL1090.constant.NSAL1090Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
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
public class NSAL1090Scrn00_Research extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2021/01/12 [QC#58177] ADD
        NSAL1090BMsg scrnMsg = (NSAL1090BMsg) bMsg;
        scrnMsg.xxWrnSkipFlg.clear();
        // END 2021/01/12 [QC#58177] ADD
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1090BMsg scrnMsg = (NSAL1090BMsg) bMsg;

        scrnMsg.svcInvNum_P2.clear();
        if (scrnMsg.A.getValidCount() > 0) {
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(0).conslBillPk_A)) {
                scrnMsg.dsContrNum_P2.clear();
                setValue(scrnMsg.conslBillPk_P2, scrnMsg.A.no(0).conslBillPk_A);
            } else {
                setValue(scrnMsg.dsContrNum_P2, scrnMsg.A.no(0).dsContrNum_A);
                scrnMsg.conslBillPk_P2.clear();
            }
        } else {
            scrnMsg.dsContrNum_P2.clear();
            scrnMsg.conslBillPk_P2.clear();
        }
        scrnMsg.serNum_P2.clear();
        scrnMsg.bllgPerFromDt_P2.clear();
        scrnMsg.bllgPerToDt_P2.clear();
        setValue(scrnMsg.svcCrRebilPk_P2, scrnMsg.svcCrRebilPk_H);

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
