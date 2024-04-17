/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2340;

import static business.servlet.NWAL2340.constant.NWAL2340Constant.EVENT_CMN_CLOSE;
import static business.servlet.NWAL2340.constant.NWAL2340Constant.EVENT_OPEN_WIN_SHIP_TO_H;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NWAL2340.common.NWAL2340CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * SOM Address Mass Apply NMAL6760
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/22   CITS            S.Tanikawa      Create          N/A
 * 2017/09/07   Fujitsu         R.Nakamura      Update          QC#20974
 *</pre>
 */
public class NWAL2340_NMAL6760 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2340BMsg scrnMsg = (NWAL2340BMsg) bMsg;

        if (EVENT_CMN_CLOSE.equals(getLastGuard())) {
            return;
        }

        if (EVENT_OPEN_WIN_SHIP_TO_H.equals(scrnMsg.xxScrEventNm_H.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustAcctCd_H, scrnMsg.P.no(0).xxPopPrm_P);
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustAcctNm_H, scrnMsg.P.no(1).xxPopPrm_P);
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToFirstLineAddr_H, scrnMsg.P.no(4).xxPopPrm_P);
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustCd_H, scrnMsg.P.no(16).xxPopPrm_P);
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToScdLineAddr_H, scrnMsg.P.no(17).xxPopPrm_P);
            // Mod Start 2017/09/07 QC#20974
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToThirdLineAddr_H, scrnMsg.P.no(18).xxPopPrm_P);
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToFrthLineAddr_H, scrnMsg.P.no(19).xxPopPrm_P);
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCtyAddr_H, scrnMsg.P.no(5).xxPopPrm_P);
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToStCd_H, scrnMsg.P.no(6).xxPopPrm_P);
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToPostCd_H, scrnMsg.P.no(7).xxPopPrm_P);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.xxLocAddrNm_H, scrnMsg.shipToFirstLineAddr_H.getValue() + " " + scrnMsg.shipToScdLineAddr_H.getValue());
            String allLineAddr = NWAL2340CommonLogic.setAllLineAddressInfo(scrnMsg, -1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxAllLineAddr_H, allLineAddr);
            // Mod End 2017/09/07 QC#20974
        } else {
            int idx = scrnMsg.xxCellIdx_H.getValueInt();
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).shipToCustAcctCd_A, scrnMsg.P.no(0).xxPopPrm_P);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).shipToCustAcctNm_A, scrnMsg.P.no(1).xxPopPrm_P);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).shipToFirstLineAddr_A, scrnMsg.P.no(4).xxPopPrm_P);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).shipToCustCd_A, scrnMsg.P.no(16).xxPopPrm_P);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).shipToScdLineAddr_A, scrnMsg.P.no(17).xxPopPrm_P);
            // Mod Start 2017/09/07 QC#20974
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).shipToThirdLineAddr_A, scrnMsg.P.no(18).xxPopPrm_P);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).shipToFrthLineAddr_A, scrnMsg.P.no(19).xxPopPrm_P);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).shipToCtyAddr_A, scrnMsg.P.no(5).xxPopPrm_P);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).shipToStCd_A, scrnMsg.P.no(6).xxPopPrm_P);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).shipToPostCd_A, scrnMsg.P.no(7).xxPopPrm_P);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).xxLocAddrNm_A, scrnMsg.A.no(idx).shipToFirstLineAddr_A.getValue() + " " + scrnMsg.A.no(idx).shipToScdLineAddr_A.getValue());
            String allLineAddr = NWAL2340CommonLogic.setAllLineAddressInfo(scrnMsg, idx);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).xxAllLineAddr_A, allLineAddr);
            // Mod End 2017/09/07 QC#20974

        }
    }
}
