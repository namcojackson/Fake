/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0560;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_OFF_N;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/26   Hitachi         K.Kasai         Create          N/A
 * 2016/05/17   Hitachi         T.Tomita        Update          QC#3891
 * 2017/08/21   Hitachi         K.Kitachi       Update          QC#20061
 *</pre>
 */
public class NSAL0560Scrn00_MoveWin_SkipMonths extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0560BMsg scrnMsg = (NSAL0560BMsg) bMsg;

        if (FLG_ON_Y.equals(scrnMsg.updCtrlFlg_A.getValue())) {
            setValue(scrnMsg.updCtrlFlg_A, FLG_OFF_N);
            return;
        }
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (!hasValueForTargetLine(scrnMsg.A.no(i))
                    || changeColumnForTargetLine(scrnMsg.A.no(i), scrnMsg.B.no(i))) {
                scrnMsg.setMessageInfo("NSAM0338W", new String[]{});

                setValue(scrnMsg.updCtrlFlg_A, FLG_ON_Y);
                scrnMsg.putErrorScreen();
                break;
            }
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0560BMsg scrnMsg = (NSAL0560BMsg) bMsg;

        scrnMsg.xxPopPrm_P0.setValue(SVC_INV_CHRG_TP.BASE_CHARGE);
        Object[] params = new Object[3];
        params[0] = scrnMsg.dsContrDtlPk_H1;
        params[1] = scrnMsg.xxPopPrm_P0;
        params[2] = scrnMsg.xxModeCd_H1;
        setArgForSubScreen(params);
    }

    /**
     * @param aBMsg NSAL0560_ABMsg
     * @return boolean
     */
    private boolean hasValueForTargetLine(NSAL0560_ABMsg aBMsg) {
        // mod start 2016/05/17 CSA Defect#3891
        // START 2017/08/21 K.Kitachi [QC#20061, MOD]
        if (hasValue(aBMsg.contrPrcEffFromDt_A1)
                && hasValue(aBMsg.contrPrcEffThruDt_A1)
                && hasValue(aBMsg.bllgCycleCd_A3)
                && hasValue(aBMsg.basePrcDealAmt_A1)
                && hasValue(aBMsg.basePrcTermDealAmtRate_A1)) {
            return true;
        }
        // END 2017/08/21 K.Kitachi [QC#20061, MOD]
        // mod end 2016/05/17 CSA Defect#3891
        return false;
    }

    /**
     * @param aBMsg NSAL0560_ABMsg
     * @param bBMsg NSAL0560_BBMsg
     * @return boolean
     */
    private boolean changeColumnForTargetLine(NSAL0560_ABMsg aBMsg, NSAL0560_BBMsg bBMsg) {
        // mod start 2016/05/17 CSA Defect#3891
        // START 2017/08/21 K.Kitachi [QC#20061, MOD]
        if (!aBMsg.contrPrcEffFromDt_A1.getValue().equals(bBMsg.contrPrcEffFromDt_B1.getValue())
                || !aBMsg.contrPrcEffThruDt_A1.getValue().equals(bBMsg.contrPrcEffThruDt_B1.getValue())
                || !aBMsg.bllgCycleCd_A3.getValue().equals(bBMsg.bllgCycleCd_B3.getValue())
                || aBMsg.basePrcDealAmt_A1.getValue().compareTo(bBMsg.basePrcDealAmt_B1.getValue()) != 0
                || aBMsg.basePrcTermDealAmtRate_A1.getValue().compareTo(bBMsg.basePrcTermDealAmtRate_B1.getValue()) != 0) {
            return true;
        }
        // END 2017/08/21 K.Kitachi [QC#20061, MOD]
        // mod end 2016/05/17 CSA Defect#3891
        return false;
    }
}
