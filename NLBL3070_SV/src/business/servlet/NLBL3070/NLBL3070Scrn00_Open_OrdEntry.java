/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3070;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NLBL3070.constant.NLBL3070Constant;

import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Delivery Scheduling / Manage Deliveries
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/02/2015   Fujitsu         Y.Taoka         Create          N/A
 * 05/21/2021   CITS            A.Marte         Update          QC#58786
 *</pre>
 */
public class NLBL3070Scrn00_Open_OrdEntry extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3070BMsg scrnMsg = (NLBL3070BMsg) bMsg;

        int index = getButtonSelectNumber();

        if (NLBL3070Constant.TAB_ID_SCHD.equals(scrnMsg.xxDplyTab.getValue())) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, scrnMsg.A.no(index).trxHdrNum_A1);

            // START 2021/05/21 A.Marte [QC#58786, ADD]
            if (SCE_ORD_TP.DISPOSAL.equals(scrnMsg.A.no(index).sceOrdTpCd_A1.getValue())) {

                setResult(SCE_ORD_TP.DISPOSAL);

            } else if (SCE_ORD_TP.RETURN_TO_VENDOR_DOMESTIC.equals(scrnMsg.A.no(index).sceOrdTpCd_A1.getValue())) {

                setResult(SCE_ORD_TP.RETURN_TO_VENDOR_DOMESTIC);

            } else {
                //Default is open Order Entry Screen
                setResult(SCE_ORD_TP.DIRECT_SALES);
            }
            // END 2021/05/21 A.Marte [QC#58786, ADD]

        } else if (NLBL3070Constant.TAB_ID_DELV.equals(scrnMsg.xxDplyTab.getValue())) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, scrnMsg.B.no(index).trxHdrNum_B1);

            // START 2021/05/21 A.Marte [QC#58786, ADD]
            if (SCE_ORD_TP.DISPOSAL.equals(scrnMsg.B.no(index).sceOrdTpCd_B1.getValue())) {

                setResult(SCE_ORD_TP.DISPOSAL);

            } else if (SCE_ORD_TP.RETURN_TO_VENDOR_DOMESTIC.equals(scrnMsg.B.no(index).sceOrdTpCd_B1.getValue())) {

                setResult(SCE_ORD_TP.RETURN_TO_VENDOR_DOMESTIC);

            } else {
                //Default is open Order Entry Screen
                setResult(SCE_ORD_TP.DIRECT_SALES);
            }
            // END 2021/05/21 A.Marte [QC#58786, ADD]
        }

        Object[] params = new Object[1];
        params[0] = scrnMsg.P.no(0).xxPopPrm;
        setArgForSubScreen(params);
        openMultiModeScreen();
    }
}
