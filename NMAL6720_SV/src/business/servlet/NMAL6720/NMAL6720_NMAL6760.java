/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6720;

import static business.servlet.NMAL6720.constant.NMAL6720Constant.EVENT_BILL_TO_SEARCH;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.EVENT_BILL_TO_SEARCH_TRX;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.EVENT_SHIP_TO_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NMAL6720.common.NMAL6720CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/15   Fujitsu         C.Tanaka        Create          CSA
 * 2016/02/26   Fujitsu         C.Tanaka        Update          QC#4349
 * 2016/04/08   Fujitsu         C.Yokoi         Update          QC#6633
 * 2016/04/13   SRAA            Y.Chen          Update          QC#6011
 * 2016/10/21   Fujitsu         C.Yokoi         Update          QC#14982
 * </pre>
 */
public class NMAL6720_NMAL6760 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;

        // QC#6011
        if (EVENT_BILL_TO_SEARCH_TRX.equals(scrnMsg.xxScrEventNm_P.getValue())) {
            int index = getButtonSelectNumber();
            ZYPEZDItemValueSetter.setValue(scrnMsg.D.no(index).dsDefBillToCd_D1, scrnMsg.P.no(15).xxPopPrm_P);
            scrnMsg.setFocusItem(scrnMsg.D.no(index).dsDefBillToCd_D1);

        } else if (EVENT_SHIP_TO_SEARCH.equals(scrnMsg.xxScrEventNm_P.getValue())) {
            int index = getButtonSelectNumber();
            ZYPEZDItemValueSetter.setValue(scrnMsg.D.no(getButtonSelectNumber()).dsDefShipToCd_D1, scrnMsg.P.no(16).xxPopPrm_P);
            scrnMsg.setFocusItem(scrnMsg.D.no(index).dsDefShipToCd_D1);

        } else if (EVENT_BILL_TO_SEARCH.equals(scrnMsg.xxScrEventNm_P.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.billToCustCd_SH, scrnMsg.P.no(15).xxPopPrm_P);
            scrnMsg.setFocusItem(scrnMsg.billToCustCd_SH);

        } else {
            NMAL6720_ABMsg aBMsg = scrnMsg.A.no(scrnMsg.xxCellIdx_AI.getValueInt());
            ZYPEZDItemValueSetter.setValue(aBMsg.dsAcctNum_A1, scrnMsg.P.no(0).xxPopPrm_P);
            ZYPEZDItemValueSetter.setValue(aBMsg.dsAcctNm_A1, scrnMsg.dsAcctNm_P1);
            scrnMsg.setFocusItem(aBMsg.dsAcctNum_A1);
        }

        // 2016/10/21 CSA-QC#14982 Add Start
        NMAL6720CommonLogic.controlProtectShipTo(scrnMsg, this);
        // 2016/10/21 CSA-QC#14982 Add End
    }
}
