/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0570;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_OFF_N;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static business.servlet.NSAL0570.constant.NSAL0570Constant.*;
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
 * 2015/12/24   Hitachi         T.Tomita        Update          QC#2278
 * 2016/05/20   Hitachi         T.Tomita        Update          QC#4923
 *</pre>
 */
public class NSAL0570Scrn00_MoveWin_SkipMonths extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0570BMsg scrnMsg = (NSAL0570BMsg) bMsg;

        if (FLG_ON_Y.equals(scrnMsg.updCtrlFlg_A.getValue())) {
            setValue(scrnMsg.updCtrlFlg_A, FLG_OFF_N);
            return;
        }
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (!hasValueForTargetLine(scrnMsg.A.no(i))
                    || changeColumnForTargetLine(scrnMsg.A.no(i), scrnMsg.B.no(i))) {
                scrnMsg.setMessageInfo(NSAM0338W, new String[]{});

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

        NSAL0570BMsg scrnMsg = (NSAL0570BMsg) bMsg;
        // START 2015/12/24 T.Tomita [QC#2278, MOD]
        scrnMsg.xxPopPrm_P0.setValue(SVC_INV_CHRG_TP.METER_CHARGE);
        // END 2015/12/24 T.Tomita [QC#2278, MOD]
        Object[] params = new Object[3];
        params[0] = scrnMsg.dsContrDtlPk_H1;
        params[1] = scrnMsg.xxPopPrm_P0;
        params[2] = scrnMsg.xxModeCd_H1;
        setArgForSubScreen(params);
    }

    /**
     * @param aBMsg NSAL0570_ABMsg
     * @return boolean
     */
    private boolean hasValueForTargetLine(NSAL0570_ABMsg aBMsg) {
        // START 2016/05/20 T.Tomita [QC#4923, MOD]
        if (hasValue(aBMsg.contrPrcEffFromDt_A1)
                && hasValue(aBMsg.contrPrcEffThruDt_A1)
                && hasValue(aBMsg.bllgCycleCd_A3)
                && hasValue(aBMsg.xsMtrCopyQty_A1)
                && hasValue(aBMsg.xsMtrAmtRate_A1)) {
            return true;
        }
        // END 2016/05/20 T.Tomita [QC#4923, MOD]
        return false;
    }

    /**
     * @param aBMsg NSAL0570_ABMsg
     * @param bBMsg NSAL0570_BBMsg
     * @return boolean
     */
    private boolean changeColumnForTargetLine(NSAL0570_ABMsg aBMsg, NSAL0570_BBMsg bBMsg) {
        // START 2016/05/20 T.Tomita [QC#4923, MOD]
        if (!aBMsg.contrPrcEffFromDt_A1.getValue().equals(bBMsg.contrPrcEffFromDt_B1.getValue())
                || !aBMsg.contrPrcEffThruDt_A1.getValue().equals(bBMsg.contrPrcEffThruDt_B1.getValue())
                || !aBMsg.bllgCycleCd_A3.getValue().equals(bBMsg.bllgCycleCd_B3.getValue())
                || !aBMsg.xsMtrCopyQty_A1.getValue().equals(bBMsg.xsMtrCopyQty_B1.getValue())
                || !aBMsg.xsMtrAmtRate_A1.getValue().equals(bBMsg.xsMtrAmtRate_B1.getValue())) {
            return true;
        }
        // END 2016/05/20 T.Tomita [QC#4923, MOD]
        return false;
    }
}
