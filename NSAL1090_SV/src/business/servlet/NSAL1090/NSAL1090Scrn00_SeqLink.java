/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1090;

import static business.servlet.NSAL1090.constant.NSAL1090Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Credit Rebill Detail Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/05   Hitachi         A.Kohinata      Create          N/A
 * 2016/04/22   Hitachi         K.Yamada        Update          CSA QC#7403
 * 2021/01/12   CITS            R.Shimamoto     Update          QC#58177
 *</pre>
 */
public class NSAL1090Scrn00_SeqLink extends S21CommonHandler {

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
        int selectIdx = getButtonSelectNumber();

        // mod start 2016/04/22 CSA Defect#7403
        if (INV_TP_BASE.equals(scrnMsg.A.no(selectIdx).invTpNm_A.getValue())) {
            setValue(scrnMsg.xxModeCd_P3, NSAL1120_MODE_BASE_CHARGE);
        } else {
            setValue(scrnMsg.xxModeCd_P3, NSAL1120_MODE_METER_CHARGE);
        }
        // mod end 2016/04/22 CSA Defect#7403
        setValue(scrnMsg.svcCrRebilPk_P3, scrnMsg.svcCrRebilPk_H);
        setValue(scrnMsg.svcCrRebilStsCd_P3, scrnMsg.svcCrRebilStsCd_H);
        setValue(scrnMsg.svcCrRebilDtlPk_P3, scrnMsg.A.no(selectIdx).svcCrRebilDtlPk_A);
        scrnMsg.svcInvLinePk_P3.clear();

        Object[] params = new Object[NSAL1120_PRM_LENGTH];
        int i = 0;
        params[i++] = scrnMsg.xxModeCd_P3;
        params[i++] = scrnMsg.svcCrRebilPk_P3;
        params[i++] = scrnMsg.svcCrRebilStsCd_P3;
        params[i++] = scrnMsg.svcCrRebilDtlPk_P3;
        params[i++] = scrnMsg.svcInvLinePk_P3;

        setArgForSubScreen(params);
    }
}
