/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1030;

import static business.servlet.NSAL1030.constant.NSAL1030Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CR_REBIL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Contract Invoice Detail Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/29   Hitachi         A.Kohinata      Create          N/A
 * 2016/04/12   Hitachi         K.Yamada        Update          CSA QC#6646
 * 2016/05/11   Hitachi         T.Aoyagi        Update          QC#7734
 * 2017/10/02   Hitachi         U.Kim           Update          QC#18749
 *</pre>
 */
public class NSAL1030Scrn00_SeqLink extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1030BMsg scrnMsg = (NSAL1030BMsg) bMsg;
        int selectIdx = getButtonSelectNumber();

        // add start 2016/04/12 CSA Defect#6646
        scrnMsg.custIncdtId_H.clearErrorInfo();
        scrnMsg.svcCrRebilStsDescTxt_H.clearErrorInfo();
        // add end 2016/04/12 CSA Defect#6646

        if (!hasValue(scrnMsg.svcCrRebilPk_P) && !hasValue(scrnMsg.A.no(selectIdx).svcCrRebilStsCd_A)) {
            scrnMsg.custIncdtId_H.setErrorInfo(1, NSAM0422E);
        }
        if (hasValue(scrnMsg.svcCrRebilPk_P) && !hasValue(scrnMsg.A.no(selectIdx).svcCrRebilStsCd_A) && !SVC_CR_REBIL_STS.ENTERED.equals(scrnMsg.svcCrRebilStsCd_H.getValue())) {
            scrnMsg.svcCrRebilStsDescTxt_H.setErrorInfo(1, NSAM0065E);
        }
        // START 05/11/2016 T.Aoyagi [QC#7734, ADD]
        if (hasValue(scrnMsg.svcCrRebilPk_P) && hasValue(scrnMsg.A.no(selectIdx).svcCrRebilStsCd_A)) {
            scrnMsg.custIncdtId_H.setErrorInfo(1, NSAM0471E);
        }
        // END 05/11/2016 T.Aoyagi [QC#7734, ADD]

        scrnMsg.addCheckItem(scrnMsg.custIncdtId_H);
        scrnMsg.addCheckItem(scrnMsg.svcCrRebilStsDescTxt_H);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1030BMsg scrnMsg = (NSAL1030BMsg) bMsg;
        int selectIdx = getButtonSelectNumber();

        if (SVC_INV_CHRG_TP.BASE_CHARGE.equals(scrnMsg.A.no(selectIdx).svcInvChrgTpCd_A.getValue())) {
            setValue(scrnMsg.xxModeCd_P0, NSAL1120_MODE_BASE_CHARGE);
        } else if (SVC_INV_CHRG_TP.METER_CHARGE.equals(scrnMsg.A.no(selectIdx).svcInvChrgTpCd_A.getValue())) {
            setValue(scrnMsg.xxModeCd_P0, NSAL1120_MODE_METER_CHARGE);
        }
        // START 2017/10/02 U.Kim [QC#18749, MOD]
        // setValue(scrnMsg.svcCrRebilPk_P0, scrnMsg.svcCrRebilPk_H);
        // setValue(scrnMsg.svcCrRebilStsCd_P0, scrnMsg.A.no(selectIdx).svcCrRebilStsCd_A);
        // setValue(scrnMsg.svcCrRebilDtlPk_P0, scrnMsg.A.no(selectIdx).svcCrRebilDtlPk_A);
        // if (!hasValue(scrnMsg.svcCrRebilStsCd_P0)) {
        //     setValue(scrnMsg.svcInvLinePk_P0, scrnMsg.A.no(selectIdx).svcInvLinePk_A);
        // } else {
        //     scrnMsg.svcInvLinePk_P0.clear();
        // }
        if (!hasValue(scrnMsg.svcCrRebilPk_P)) {
            scrnMsg.svcCrRebilPk_P0.clear();
            setValue(scrnMsg.svcCrRebilStsCd_P0, scrnMsg.A.no(selectIdx).svcCrRebilStsCd_A);
            setValue(scrnMsg.svcCrRebilDtlPk_P0, scrnMsg.A.no(selectIdx).svcCrRebilDtlPk_A);
            scrnMsg.svcInvLinePk_P0.clear();
        } else {
            setValue(scrnMsg.svcCrRebilPk_P0, scrnMsg.svcCrRebilPk_H);
            scrnMsg.svcCrRebilStsCd_P0.clear();
            scrnMsg.svcCrRebilDtlPk_P0.clear();
            setValue(scrnMsg.svcInvLinePk_P0, scrnMsg.A.no(selectIdx).svcInvLinePk_A);
        }
        // END 2017/10/02 U.Kim [QC#18749, MOD]

        Object[] params = new Object[NSAL1120_PRM_LENGTH];
        int i = 0;
        params[i++] = scrnMsg.xxModeCd_P0;
        params[i++] = scrnMsg.svcCrRebilPk_P0;
        params[i++] = scrnMsg.svcCrRebilStsCd_P0;
        params[i++] = scrnMsg.svcCrRebilDtlPk_P0;
        params[i++] = scrnMsg.svcInvLinePk_P0;

        setArgForSubScreen(params);
    }
}
