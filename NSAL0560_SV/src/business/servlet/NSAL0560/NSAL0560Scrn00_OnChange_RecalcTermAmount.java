/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0560;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0560.NSAL0560CMsg;
import business.servlet.NSAL0560.common.NSAL0560CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.*;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/26   Hitachi         K.Kasai         Create          N/A
 * 2015/12/07   Hitachi         T.Kanasaka      Update          QC#1472
 * 2016/08/09   Hitachi         K.Kishimoto     Update          QC#12310
 * 2017/08/21   Hitachi         K.Kitachi       Update          QC#20061
 * 2017/08/31   Hitachi         K.Kitachi       Update          QC#20838
 *</pre>
 */
public class NSAL0560Scrn00_OnChange_RecalcTermAmount extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0560BMsg scrnMsg = (NSAL0560BMsg) bMsg;

        int targetRow = getButtonSelectNumber();
        // START 2015/12/07 T.Kanasaka [QC#1472, MOD]
//        if (hasValueForTargetLine(scrnMsg.A.no(targetRow))) {
        if (!hasValueForTargetLine(scrnMsg.A.no(targetRow))) {
        // END 2015/12/07 T.Kanasaka [QC#1472, MOD]
            scrnMsg.addCheckItem(scrnMsg.A.no(targetRow).contrPrcEffFromDt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(targetRow).contrPrcEffThruDt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(targetRow).bllgCycleCd_A3);
            scrnMsg.addCheckItem(scrnMsg.A.no(targetRow).basePrcDealAmt_A1);

            if (scrnMsg.A.no(targetRow).contrPrcEffFromDt_A1.getValue().compareTo(scrnMsg.A.no(targetRow).contrPrcEffThruDt_A1.getValue()) > 0) {
                scrnMsg.A.no(targetRow).contrPrcEffFromDt_A1.setErrorInfo(1, "NSBM0024E", new String [] {});
            }
            scrnMsg.putErrorScreen();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0560BMsg scrnMsg = (NSAL0560BMsg) bMsg;
        NSAL0560CMsg bizMsg = new NSAL0560CMsg();

        int targetRow = getButtonSelectNumber();
        // START 2017/08/31 K.Kitachi [QC#20838, MOD]
//        if (!hasValueForTargetLine(scrnMsg.A.no(targetRow))
//                || !changeColumnForTargetLine(scrnMsg.A.no(targetRow), scrnMsg.B.no(targetRow))) {
        if (!hasValueForTargetLine(scrnMsg.A.no(targetRow))) {
        // END 2017/08/31 K.Kitachi [QC#20838, MOD]
            return null;
        }
        setValue(scrnMsg.rowSqNum_A, BigDecimal.valueOf(targetRow));

        bizMsg.setBusinessID("NSAL0560");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0560BMsg scrnMsg = (NSAL0560BMsg) bMsg;
        NSAL0560CMsg bizMsg  = (NSAL0560CMsg) cMsg;

        if (bizMsg == null) {
            return;
        }
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // Add Start 08/09/2016 <QC#12310>
        NSAL0560CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
        // Add End   08/09/2016 <QC#12310>

        // START 2015/12/07 T.Kanasaka [QC#1472, DEL]
//        NSAL0560CommonLogic.copyAMsgToBMsg(scrnMsg);
        // END 2015/12/07 T.Kanasaka [QC#1472, DEL]

        // START 2017/08/21 K.Kitachi [QC#20061, ADD]
        NSAL0560CommonLogic.calcTotTermAmt(scrnMsg);
        // END 2017/08/21 K.Kitachi [QC#20061, ADD]
    }

    /**
     * @param aBMsg NSAL0560_ABMsg
     * @return boolean
     */
    private boolean hasValueForTargetLine(NSAL0560_ABMsg aBMsg) {
        if (hasValue(aBMsg.contrPrcEffFromDt_A1)
                && hasValue(aBMsg.contrPrcEffThruDt_A1)
                && hasValue(aBMsg.bllgCycleCd_A3)
                && hasValue(aBMsg.basePrcDealAmt_A1)) {
            return true;
        }
        return false;
    }

    // START 2017/08/31 K.Kitachi [QC#20838, DEL]
//    /**
//     * @param aBMsg NSAL0560_ABMsg
//     * @param bBMsg NSAL0560_BBMsg
//     * @return boolean
//     */
//    private boolean changeColumnForTargetLine(NSAL0560_ABMsg aBMsg, NSAL0560_BBMsg bBMsg) {
//        if (!aBMsg.contrPrcEffFromDt_A1.getValue().equals(bBMsg.contrPrcEffFromDt_B1.getValue())
//                || !aBMsg.contrPrcEffThruDt_A1.getValue().equals(bBMsg.contrPrcEffThruDt_B1.getValue())
//                || !aBMsg.bllgCycleCd_A3.getValue().equals(bBMsg.bllgCycleCd_B3.getValue())
//                // mod start 2015/12/10 CSA Defect#1756
//                //|| !aBMsg.basePrcDealAmt_A1.getValue().equals(bBMsg.basePrcDealAmt_B1.getValue())) {
//                || aBMsg.basePrcDealAmt_A1.getValue().compareTo(bBMsg.basePrcDealAmt_B1.getValue()) != 0) {
//                // mod end 2015/12/10 CSA Defect#1756
//            return true;
//        }
//        return false;
//    }
    // END 2017/08/31 K.Kitachi [QC#20838, DEL]
}
