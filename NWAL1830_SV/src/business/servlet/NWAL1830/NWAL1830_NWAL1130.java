/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1830;

import static business.servlet.NWAL1830.constant.NWAL1830Constant.IDX_0;
import static business.servlet.NWAL1830.constant.NWAL1830Constant.IDX_1;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1830_NWAL1130
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/29   Fujitsu         Y.Taoka         Create          N/A
 *</pre>
 */
public class NWAL1830_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1830BMsg scrnMsg = (NWAL1830BMsg) bMsg;
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        int index = getButtonSelectNumber();

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }
        if ("OpenWin_SalesrepSearch".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.slsRepTocNm_OL, scrnMsg.P.no(IDX_1).xxComnScrColValTxt_P.getValue());
            scrnMsg.setFocusItem(scrnMsg.slsRepTocNm_OL);

        }  else if ("OpenWin_Warehouse".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(index).rtlWhNm_B1, scrnMsg.P.no(IDX_1).xxComnScrColValTxt_P.getValue());
            scrnMsg.setFocusItem(scrnMsg.B.no(index).rtlWhNm_B1);

        }  else if ("OpenWin_OrderSearch".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.cpoOrdNum_OL, scrnMsg.P.no(IDX_0).xxComnScrColValTxt_P.getValue());
            scrnMsg.setFocusItem(scrnMsg.cpoOrdNum_OL);
        }
    }
}
